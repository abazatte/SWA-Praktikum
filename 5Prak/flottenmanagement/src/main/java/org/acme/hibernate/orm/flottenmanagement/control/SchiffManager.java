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
        return null;
    }

    @Override
    public ReturnSchiffDTO getSchiffByID(long id) {
        return null;
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
    public boolean deleteSchiff(DeleteSchiffDTO deleteSchiffDTO) {
        return schiffService.deleteSchiff(deleteSchiffDTO);
    }
}
