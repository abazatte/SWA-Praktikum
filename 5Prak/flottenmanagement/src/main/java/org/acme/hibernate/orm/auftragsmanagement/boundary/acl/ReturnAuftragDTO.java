package org.acme.hibernate.orm.auftragsmanagement.boundary.acl;

import javax.ws.rs.core.Link;
import java.sql.Date;

public class ReturnAuftragDTO {
    public long id;
    public String beschreibung;
    public Date eingangsDatum;
    public Link schiffURL;
}