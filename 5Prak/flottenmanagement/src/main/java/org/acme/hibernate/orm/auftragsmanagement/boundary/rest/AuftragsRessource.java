package org.acme.hibernate.orm.auftragsmanagement.boundary.rest;

import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.*;
import org.acme.hibernate.orm.auftragsmanagement.entity.AuftragsCatalog;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;

@ApplicationScoped
public class AuftragsRessource {

    public Collection<ReturnAuftragDTO> getAllAuftraege() {
        return null;
    }


    public ReturnAuftragDTO addAuftrag(PostAuftragDTO postAuftragDTO) {
        return null;
    }


    public ReturnAuftragDTO editAuftrag(PatchAuftragDTO patchAuftragDTO) {
        return null;
    }


    public Boolean deleteAuftrag(long id) {
        return null;
    }


    public ReturnAuftragDTO findAuftragByID(long id) {
        return null;
    }
}
