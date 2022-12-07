package org.acme.hibernate.orm.auftragsmanagement.entity;

import javax.persistence.*;
import java.net.URI;
import java.sql.Date;

@Entity
@Table(name = "auftraege")
@NamedQuery(name = "Auftrag.findAll", query = "SELECT a FROM Auftrag a ORDER BY a.id", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@NamedQuery(name = "Auftrag.findByID", query =  "SELECT a FROM Auftrag a WHERE a.id = :id" , hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@NamedQuery(name = "Auftrag.update", query = "UPDATE Auftrag a set a.beschreibung = :beschreibung, a.eingangsDatum = :eingangsdatum, a.schiffURL = :schiffURL where a.id = :id")
@NamedQuery(name = "Auftrag.delete", query = "DELETE FROM Auftrag a WHERE a.id = :id")
@Cacheable
public class Auftrag {
    @Id
    @SequenceGenerator(name = "AuftragsSequence", sequenceName = "known_auftrags_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "AuftragsSequence")
    private Long id;
    @Column(length = 1000)
    private String beschreibung;

    @Column
    private Date eingangsDatum;

    @Column
    private URI schiffURL;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBeschreibung() {
        return beschreibung;
    }

    public void setBeschreibung(String name) {
        this.beschreibung = name;
    }

    public Date getEingangsDatum() {
        return eingangsDatum;
    }

    public void setEingangsDatum(Date eingangsDatum) {
        this.eingangsDatum = eingangsDatum;
    }

    public URI getSchiffURL() {
        return schiffURL;
    }

    public void setSchiffURL(URI schiffURL) {
        this.schiffURL = schiffURL;
    }



    public Auftrag(){}

}
