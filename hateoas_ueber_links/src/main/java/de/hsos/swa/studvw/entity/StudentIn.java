package de.hsos.swa.studvw.entity;

public class StudentIn {
    private long matnr;
    private String name;

    public StudentIn(long matnr, String name) {
        this.matnr = matnr;
        this.name = name;
    }

    public long getMatnr() {
        return matnr;
    }

    public String getName() {
        return name;
    }

    public void changeName(final String newName) {
        this.name = newName;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (matnr ^ (matnr >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentIn other = (StudentIn) obj;
        if (matnr != other.matnr)
            return false;
        return true;
    }    
    
}
