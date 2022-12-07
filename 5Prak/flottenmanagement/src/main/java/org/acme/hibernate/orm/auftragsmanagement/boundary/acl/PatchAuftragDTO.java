package org.acme.hibernate.orm.auftragsmanagement.boundary.acl;

import org.acme.hibernate.orm.auftragsmanagement.entity.Auftrag;

import javax.ws.rs.core.Link;
import java.net.URI;
import java.sql.Date;

public class PatchAuftragDTO {
    public long id;
    public String beschreibung;
    public Date eingangsDatum;
    public URI schiffURL;

    public PatchAuftragDTO(ReturnAuftragDTO auftrag){
        this.id = auftrag.id;
        this.beschreibung = auftrag.beschreibung;
        this.eingangsDatum = auftrag.eingangsDatum;
        this.schiffURL = auftrag.schiffURL;
    }
}
