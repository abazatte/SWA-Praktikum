package org.acme.hibernate.orm.flottenmanagement.control;

import org.acme.hibernate.orm.flottenmanagement.boundary.acl.PostSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.ReturnSchiffDTO;

import java.util.Collection;

public interface Schiffinterface {
    Collection<ReturnSchiffDTO> getAll();
    ReturnSchiffDTO getSchiffByID(long id);
    ReturnSchiffDTO auftragAnSchiffUebergeben(long id, boolean hatAuftrag);
    ReturnSchiffDTO addSchiff(PostSchiffDTO postSchiffDTO);
    boolean deleteSchiff(long id);
}
