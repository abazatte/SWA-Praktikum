package org.acme.hibernate.orm.auftragsmanagement.boundary.acl;

import org.acme.hibernate.orm.auftragsmanagement.entity.Auftrag;

import javax.ws.rs.core.Link;
import java.net.URI;
import java.sql.Date;

public class ReturnAuftragDTO {
    public long id;
    public String beschreibung;
    public Date eingangsDatum;
    public URI schiffURL;

    public ReturnAuftragDTO(Auftrag auftrag){
        this.id = auftrag.getId();
        this.beschreibung = auftrag.getBeschreibung();
        this.eingangsDatum = auftrag.getEingangsDatum();
        this.schiffURL = auftrag.getSchiffURL();
    }
}
