package org.acme.hibernate.orm.flottenmanagement.control;

import org.acme.hibernate.orm.flottenmanagement.boundary.acl.DeleteSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.PostSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.ReturnSchiffDTO;

public interface Schiffinterface {
    ReturnSchiffDTO AuftragAnSchiffUebergeben(long id);
    ReturnSchiffDTO addSchiff(PostSchiffDTO postSchiffDTO);
    boolean deleteSchiff(DeleteSchiffDTO deleteSchiffDTO);
}
