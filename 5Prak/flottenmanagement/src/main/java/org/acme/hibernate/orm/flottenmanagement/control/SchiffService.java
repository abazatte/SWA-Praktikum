package org.acme.hibernate.orm.flottenmanagement.control;

import org.acme.hibernate.orm.flottenmanagement.boundary.acl.DeleteSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.PostSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.ReturnSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.entity.SchiffCatalog;

import javax.inject.Inject;

public class SchiffService {
    @Inject
    SchiffCatalog schiffRepository;



    public ReturnSchiffDTO auftragAnSchiffUebergeben(long id) {
        return schiffRepository.auftragAnSchiffUebergeben(id);
    }


    public ReturnSchiffDTO addSchiff(PostSchiffDTO postSchiffDTO) {
        return schiffRepository.addSchiff(postSchiffDTO);
    }


    public boolean deleteSchiff(DeleteSchiffDTO deleteSchiffDTO) {
        return schiffRepository.deleteSchiff(deleteSchiffDTO);
    }
}
