package org.acme.hibernate.orm.auftragsmanagement.boundary.acl;

import javax.ws.rs.core.Link;
import java.net.URI;
import java.sql.Date;

public class PatchAuftragDTO {
    public String beschreibung;
    public Date eingangsDatum;
    public URI SchiffURL;
}
