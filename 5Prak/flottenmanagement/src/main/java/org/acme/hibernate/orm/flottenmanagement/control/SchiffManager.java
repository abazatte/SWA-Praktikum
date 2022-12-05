package org.acme.hibernate.orm.flottenmanagement.control;

import org.acme.hibernate.orm.flottenmanagement.boundary.acl.DeleteSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.PostSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.ReturnSchiffDTO;

import javax.inject.Inject;

public class SchiffManager implements Schiffinterface{
    @Inject
    SchiffService schiffService;
    @Override
    public ReturnSchiffDTO AuftragAnSchiffUebergeben(long id) {
        return schiffService.auftragAnSchiffUebergeben(id);
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
