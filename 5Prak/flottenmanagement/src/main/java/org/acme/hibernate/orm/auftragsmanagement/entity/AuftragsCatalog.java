package org.acme.hibernate.orm.auftragsmanagement.entity;

import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.*;

import java.util.Collection;

public interface AuftragsCatalog {
    Collection<ReturnAuftragDTO> getAllAuftraege();
    ReturnAuftragDTO addAuftrag(PostAuftragDTO postAuftragDTO);
    ReturnAuftragDTO editAuftrag(PatchAuftragDTO patchAuftragDTO);
    Boolean deleteAuftrag(long id);
    ReturnAuftragDTO findAuftragByID(long id);
}
