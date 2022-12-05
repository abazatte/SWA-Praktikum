package org.acme.hibernate.orm.auftragsmanagement.entity;

import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.PatchAuftragDTO;
import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.PostAuftragDTO;
import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.ReturnAuftragDTO;

public interface AuftragsCatalog {
    ReturnAuftragDTO addAuftrag(PostAuftragDTO postAuftragDTO);
    ReturnAuftragDTO editAuftrag(PatchAuftragDTO patchAuftragDTO);
    Boolean deleteAuftag(long id);
    ReturnAuftragDTO findAuftragByID(long id);
}
