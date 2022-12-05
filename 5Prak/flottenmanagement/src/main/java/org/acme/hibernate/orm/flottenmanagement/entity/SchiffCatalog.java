package org.acme.hibernate.orm.flottenmanagement.entity;

import org.acme.hibernate.orm.flottenmanagement.boundary.acl.DeleteSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.PostSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.ReturnSchiffDTO;

public interface SchiffCatalog {
    ReturnSchiffDTO auftragAnSchiffUebergeben(long id);
    ReturnSchiffDTO addSchiff(PostSchiffDTO postSchiffDTO);
    boolean deleteSchiff(DeleteSchiffDTO deleteSchiffDTO);
}
