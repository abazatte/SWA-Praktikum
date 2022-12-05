package org.acme.hibernate.orm.auftragsmanagement.gateway.repo;

import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.*;
import org.acme.hibernate.orm.auftragsmanagement.entity.AuftragsCatalog;

import java.util.Collection;

public class AuftragsRepository implements AuftragsCatalog {
    @Override
    public Collection<ReturnAuftragDTO> getAllAuftraege() {
        return null;
    }

    @Override
    public ReturnAuftragDTO addAuftrag(PostAuftragDTO postAuftragDTO) {
        return null;
    }

    @Override
    public ReturnAuftragDTO editAuftrag(PatchAuftragDTO patchAuftragDTO) {
        return null;
    }

    @Override
    public Boolean deleteAuftrag(long id) {
        return null;
    }

    @Override
    public ReturnAuftragDTO findAuftragByID(long id) {
        return null;
    }
}
