package org.acme.hibernate.orm.auftragsmanagement.entity;

import javax.persistence.*;
import javax.ws.rs.core.Link;
import java.net.URI;
import java.sql.Date;

@Entity
@Table(name = "auftraege")
@NamedQuery(name = "Auftrag.findAll", query = "SELECT f FROM Fruit f ORDER BY f.name", hints = @QueryHint(name = "org.hibernate.cacheable", value = "true"))
@Cacheable
public class Auftrag {
    @Id
    @SequenceGenerator(name = "AuftragsSequence", sequenceName = "known_auftrags_id_seq", allocationSize = 1, initialValue = 1)
    @GeneratedValue(generator = "AuftragsSequence")
    private Long id;

    @Column(length = 1000)
    private String name;

    @Column
    private Date eingangsDatum;

    @Column
    private URI schiffURL;

    public Auftrag(){}

}
