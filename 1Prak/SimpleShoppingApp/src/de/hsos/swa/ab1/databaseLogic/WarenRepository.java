package de.hsos.swa.ab1.databaseLogic;

import de.hsos.swa.ab1.businessLogic.*;
import de.hsos.swa.ab1.warenkorb.Warenkorb;
import de.hsos.swa.ab1.warenkorb.WarenkorbFuerSuche;
import de.hsos.swa.ab1.warenkorb.WarenkorbStaender;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class WarenRepository implements Katalog,KatalogVerwalten, WarenkorbStaender, WarenkorbFuerSuche {
    /* Quelle: https://db.apache.org/derby/papers/DerbyTut/embedded_intro.html */
    /* the default framework is embedded */
    private String framework;
    private String protocol;

    private ArrayList<Statement> statements;
    private Connection conn;
    private PreparedStatement psInsert;
    private ResultSet rs;

    private static WarenRepository dbConnInstance = null;

    private WarenRepository() {


        framework = "embedded";
        protocol = "jdbc:derby:";

        statements = new ArrayList<>();

        System.out.println("Datenbank starting in " + framework + " mode");

        try {
            Properties props = new Properties(); // connection properties
            // providing a user name and password is optional in the embedded
            // and derbyclient frameworks
            props.put("user", "user1");
            props.put("password", "user1");
            /* By default, the schema APP will be used when no username is
             * provided.
             * Otherwise, the schema name is the same as the user name (in this
             * case "user1" or USER1.)
             *
             * Note that user authentication is off by default, meaning that any
             * user can connect to your database using any password. To enable
             * authentication, see the Derby Developer's Guide.
             */
            String dbName = "derbyDBv2"; // the name of the database
            /*
             * This connection specifies create=true in the connection URL to
             * cause the database to be created when connecting for the first
             * time. To remove the database, remove the directory derbyDB (the
             * same as the database name) and its contents.
             *
             * The directory derbyDB will be created under the directory that
             * the system property derby.system.home points to, or the current
             * directory (user.dir) if derby.system.home is not set.
             */
            conn = DriverManager.getConnection(protocol + dbName
                    + ";create=true", props);
            System.out.println("Connected to and created database " + dbName);
            // We want to control transactions manually. Autocommit is on by
            // default in JDBC.
            conn.setAutoCommit(false);
            /*
            dropRezension();
            dropNutzerBesitzt();
            dropSpiel();
            dropNutzer();
*/
            Statement statement = conn.createStatement();
            statements.add(statement);
            //Quelle sql skript laden: https://howtodoinjava.com/java/io/java-read-file-to-string-examples/#1-using-filesreadstring-java-11
            //read file line by line: https://stackoverflow.com/questions/5868369/how-can-i-read-a-large-text-file-line-by-line-using-java
            //Path filePath = Path.of("src/main/resources/sqlFiles/SpielDatenbank.sql");
            FileInputStream fileInputStream = new FileInputStream("resources/sqlFiles/SpielDatenbank.sql");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
            String sqlLine;

            while ((sqlLine = bufferedReader.readLine()) != null) {
                statement.execute(sqlLine);
            }
            fileInputStream.close();


        } catch (SQLException sqlException) {

            printSQLException(sqlException);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public static WarenRepository getInstance() {
        if (dbConnInstance == null) {
            dbConnInstance = new WarenRepository();
        }
        return dbConnInstance;
    }

    public void closeDB() {
        if (framework.equals("embedded")) {
            try {
                // the shutdown=true attribute shuts down Derby
                DriverManager.getConnection("jdbc:derby:;shutdown=true");

                // To shut down a specific database only, but keep the
                // engine running (for example for connecting to other
                // databases), specify a database in the connection URL:
                //DriverManager.getConnection("jdbc:derby:" + dbName + ";shutdown=true");
            } catch (SQLException se) {
                if (((se.getErrorCode() == 50000)
                        && ("XJ015".equals(se.getSQLState())))) {
                    // we got the expected exception
                    System.out.println("Derby shut down normally");
                    // Note that for single database shutdown, the expected
                    // SQL state is "08006", and the error code is 45000.
                } else {
                    // if the error code or SQLState is different, we have
                    // an unexpected exception (shutdown failed)
                    System.err.println("Derby did not shut down normally");
                    printSQLException(se);
                }
            }
        }
        // release all open resources to avoid unnecessary memory usage

        // ResultSet
        try {
            if (rs != null) {
                rs.close();
                rs = null;
            }
        } catch (SQLException sqle) {
            printSQLException(sqle);
        }

        // Statements and PreparedStatements
        int i = 0;
        while (!statements.isEmpty()) {
            // PreparedStatement extend Statement
            Statement st = (Statement) statements.remove(i);
            try {
                if (st != null) {
                    st.close();
                    st = null;
                }
            } catch (SQLException sqle) {
                printSQLException(sqle);
            }
        }

        //Connection
        try {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        } catch (SQLException sqle) {
            printSQLException(sqle);
        }
    }

    public static void printSQLException(SQLException e) {
        // Unwraps the entire exception chain to unveil the real cause of the
        // Exception.
        while (e != null) {
            if (e.getSQLState().equals("X0Y32")) {
                System.out.println("tables existieren bereits.");
            } else {
                System.err.println("\n----- SQLException -----");
                System.err.println("  SQL State:  " + e.getSQLState());
                System.err.println("  Error Code: " + e.getErrorCode());
                System.err.println("  Message:    " + e.getMessage());
            }
            // for stack traces, refer to derby.log or uncomment this:
            //e.printStackTrace(System.err);
            e = e.getNextException();
        }
    }

    public void insertWare(Ware ware) {
        try{
            PreparedStatement insert =
                    conn.prepareStatement("insert into Ware values(?,?,?,?)");
            statements.add(insert);
            insert.setLong(1, ware.getWarennummer());
            insert.setString(2, ware.getName());
            insert.setString(3, ware.getBeschreibung());
            insert.setDouble(4, ware.getPreis().doubleValue());

            insert.executeUpdate();
        } catch (SQLException sqlException){
            printSQLException(sqlException);
        }

    }
    public void insertWarenImKorb(WarenImKorb warenImKorb)  {
        try{
            PreparedStatement insert =
                    conn.prepareStatement("insert into WarenImKorb values(?,?)");
            statements.add(insert);

            insert.setInt(1,warenImKorb.getWarenkorbNr());
            insert.setLong(2,warenImKorb.getWarenNr());

            insert.executeUpdate();
        } catch (SQLException sqlException){
            printSQLException(sqlException);
        }

    }

    public void insertProduktinformation(Produktinformation produktinformation) {
        try{
            PreparedStatement insert =
                    conn.prepareStatement("insert into Produktinformation values(?,?,?)");
            statements.add(insert);

            insert.setString(1, produktinformation.getBezeichnung());
            insert.setObject(2, produktinformation.getInformation());
            insert.setLong(3, produktinformation.getWarennummer());
            insert.executeUpdate();
        } catch (SQLException sqlException){
            printSQLException(sqlException);
        }
    }

    public void insertWarenkorb(Warenkorb warenkorb) throws SQLException {
        PreparedStatement insert =
                conn.prepareStatement("insert into warenkorb values(?,?)");
        statements.add(insert);

        insert.setInt(1, warenkorb.getWarenkorbNr());
        insert.setString(2, warenkorb.getUser());
        insert.executeUpdate();
    }

    public boolean selectWareName(String warenName) {
        try {
            String query = "Select * From Ware WHERE Name like '" + warenName + "'";
            Statement select = conn.createStatement();
            statements.add(select);
            ResultSet everything = select.executeQuery(query);
            while (everything.next()) {
                System.out.println(everything.getString(""));

            }
            everything.close();
        } catch (SQLException s) {
            printSQLException(s);
        }
        return false;
    }

    public void selectQuery(String warenName) {
        try {
            String query = "Select * From Ware WHERE Name like '" + warenName + "'";
            //String query = "select ware.Warennummer, ware.name, ware.beschreibung, ware.preis from ware join  warenImKorb on Ware.warennummer=warenimkorb.warennr where warenimkorb.warenkorbnr = 1";
            Statement select = conn.createStatement();
            statements.add(select);
            ResultSet everything = select.executeQuery(query);
            ResultSetMetaData rsmd = everything.getMetaData();
            System.out.println(query);
            int columnNumber = rsmd.getColumnCount();
            while (everything.next()) {
                for (int i = 1; i <= columnNumber; i++) {
                    if (i > 1) {
                        System.out.print(",  ");
                    }
                    String columnValue = everything.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
            everything.close();
        } catch (SQLException s) {
            printSQLException(s);
        }

    }

    @Override
    public void addArtikel(Ware ware, Produktinformation produktinformation) {

        insertWare(ware);
        insertProduktinformation(produktinformation);
    }

    @Override
    public void removeArtikel(int warennummer) {

        deleteProduktinformation(warennummer);
        deleteWare(warennummer);
    }

    @Override
    public void updateArtikel(int warennummerAlt, Ware wareNeu) {
        updateWare(warennummerAlt, wareNeu);
    }

    public void dropAll() {
        try{
            Statement s = conn.createStatement();
            statements.add(s);
            s.execute("drop table WarenImKorb");
            s.execute("drop table Warenkorb");
            s.execute("drop table Produktinformation");
            s.execute("drop table Ware");

        }catch (SQLException e){
            printSQLException(e);
        }

    }

    @Override
    public void legeSuchalgorithmusFest(SuchAlgorithmus algorithmus) {

    }

    @Override
    public Ware[] suchen(String warenname) {
        Ware[] tmp;
        try {
            String query = "Select * From Ware WHERE Name like '%" + warenname + "%'";
            Statement select = conn.createStatement();
            statements.add(select);
            ResultSet everything = select.executeQuery(query);
            tmp = warenHinzu(everything);
            everything.close();
            return tmp;
        } catch (SQLException s) {
            printSQLException(s);
        }
        return null;
    }

    public Ware[] warenHinzu(ResultSet resultSet) throws SQLException {
        ArrayList<Ware> warenList = new ArrayList<>();
        while (resultSet.next()) {
            long warennummer = resultSet.getLong("Warennummer");
            String name = resultSet.getString("Name");
            String beschreibung = resultSet.getString("Beschreibung");
            BigDecimal preis = BigDecimal.valueOf(resultSet.getDouble("Preis"));

            Ware e = new Ware(warennummer, name, preis, beschreibung);
            warenList.add(e);
        }
        Ware[] stockArr = new Ware[warenList.size()];
        return warenList.toArray(stockArr);
    }
    public Ware eineWareHinzu(ResultSet resultSet) throws SQLException{
        //System.out.println(resultSet.toString());
        while (resultSet.next()) {
            long warennummer = resultSet.getLong("Warennummer");
            String name = resultSet.getString("Name");
            String beschreibung = resultSet.getString("Beschreibung");
            BigDecimal preis = BigDecimal.valueOf(resultSet.getDouble("Preis"));

            return new Ware(warennummer,name,preis,beschreibung);
        }
        return null;
    }

    @Override
    public Ware suchen(Long warenNummer) {
        Ware tmp;
        try {
            String query = "Select * From Ware WHERE Warennummer = " + warenNummer;
            Statement select = conn.createStatement();
            statements.add(select);
            ResultSet everything = select.executeQuery(query);
            tmp = eineWareHinzu(everything);
            everything.close();
            return tmp;
        } catch (SQLException s) {
            printSQLException(s);
        }
        return null;
    }

    @Override
    public Produktinformation gebeProduktinformationen(Ware ware) {
        Produktinformation produktinformation = null;
        try {
            String query = "Select * From Produktinformation WHERE Warennummer =" + ware.getWarennummer();
            Statement select = conn.createStatement();
            statements.add(select);
            ResultSet everything = select.executeQuery(query);
            if (everything.next()) {
                String bezeichnung = everything.getString("Bezeichnung");
                produktinformation = new Produktinformation(ware.getWarennummer(), bezeichnung, null);
            }
            everything.close();
            return produktinformation;
        } catch (SQLException s) {
            printSQLException(s);
        }

        return null;
    }

    /*
    * update, mit warennummer holen, dann alles ändern können(einfach ne ware übergeben)
    *
    * */

    public void updateWare(long warennummer, Ware neueWare){
        try{
            PreparedStatement ps = conn.prepareStatement("update Ware set Name = ?, Beschreibung = ?, Preis = ? where warennummer = ?");
            statements.add(ps);

            ps.setString(1,neueWare.getName());
            ps.setString(2, neueWare.getBeschreibung());
            ps.setBigDecimal(3,neueWare.getPreis());
            ps.setLong(4,warennummer);
            ps.executeUpdate();
        }catch (SQLException s){
            printSQLException(s);
        }
    }

    public void updateProduktinformation(long warennummer, Produktinformation neueProduktinfo){
        try{
            PreparedStatement ps = conn.prepareStatement("update Produktinformation set Bezeichnung = ? where Warennummer = ?" );
            statements.add(ps);
            ps.setString(1,neueProduktinfo.getBezeichnung());
            ps.setLong(2,neueProduktinfo.getWarennummer());

            ps.executeUpdate();
        }catch (SQLException s){
            printSQLException(s);
        }
    }

    /*
     * delete, mit warennummer holen, dann alles ändern können(einfach ne ware übergeben)
     *
     * */

    public void deleteWare(long warennummer){
        try {
            PreparedStatement ps = conn.prepareStatement("delete FROM Ware Where warennummer =" + warennummer);
            statements.add(ps);
            ps.executeUpdate();
        } catch (SQLException s){
            printSQLException(s);
        }
    }

    public void deleteProduktinformation(long warennummer){
        try {
            PreparedStatement ps = conn.prepareStatement("delete FROM Produktinformation Where warennummer =" + warennummer);
            statements.add(ps);
            ps.executeUpdate();
        } catch (SQLException s){
            printSQLException(s);
        }
    }

    public void deleteWarenImKorb(long warennummer, String user){
        try {
            PreparedStatement ps = conn.prepareStatement("delete FROM warenImKorb Where warennummer =" + warennummer + "and nutzer like '" + user + "'");
            statements.add(ps);
            ps.executeUpdate();
        } catch (SQLException s) {
            printSQLException(s);
        }
    }

    public void deleteWarenkorb(int warenkorbnr){
        try {
            PreparedStatement ps = conn.prepareStatement("delete from warenkorb where warenkorbnr = " + warenkorbnr);
            statements.add(ps);
            ps.executeUpdate();
        } catch (SQLException s) {
            printSQLException(s);
        }
    }

    @Override
    public void wareHinzufuegen(Ware ware) {
        insertWarenImKorb(new WarenImKorb(1,ware.getWarennummer()));
    }

    @Override
    public long gebeWarenkorbnummer() {
        return 1L;
    }

    @Override
    public Ware[] holeWarenkorb() {
        //ist simpler weil wir nur einen warenkorb haben!
        try {
            String query = "select ware.Warennummer, ware.name, ware.beschreibung, " +
                    "ware.preis from ware join  warenImKorb on Ware.warennummer=warenimkorb." +
                    "warennr where warenimkorb.warenkorbnr = 1";
            Statement select = conn.createStatement();
            statements.add(select);
            ResultSet everything = select.executeQuery(query);
            Ware[] helpArray = warenHinzu(everything);
            everything.close();
            return helpArray;
        } catch (SQLException s) {
            printSQLException(s);
        }
        return null;
    }

    @Override
    public Ware[] holeWarenkorb(long warenkorbnummer) {
        return holeWarenkorb();
    }
}
