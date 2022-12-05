package org.acme.hibernate.orm.flottenmanagement.control;

import org.acme.hibernate.orm.flottenmanagement.boundary.acl.DeleteSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.PostSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.ReturnSchiffDTO;

import javax.inject.Inject;
import java.util.Collection;

public class SchiffManager implements Schiffinterface{
    @Inject
    SchiffService schiffService;


    @Override
    public Collection<ReturnSchiffDTO> getAll() {
        return schiffService.getAll();
    }

    @Override
    public ReturnSchiffDTO getSchiffByID(long id) {
        return schiffService.getSchiffByID(id);
    }

    @Override
    public ReturnSchiffDTO AuftragAnSchiffUebergeben(long id, boolean hatAuftrag) {
        return schiffService.auftragAnSchiffUebergeben(id, hatAuftrag);
    }

    @Override
    public ReturnSchiffDTO addSchiff(PostSchiffDTO postSchiffDTO) {
        return schiffService.addSchiff(postSchiffDTO);
    }

    @Override
    public boolean deleteSchiff(long id) {
        return schiffService.deleteSchiff(id);
    }
}
