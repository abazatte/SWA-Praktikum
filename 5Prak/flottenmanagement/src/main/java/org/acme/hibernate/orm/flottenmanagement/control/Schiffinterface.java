package org.acme.hibernate.orm.flottenmanagement.control;

public interface Schiffinterface {
    SchiffDTO AuftragAnSchiffUebergeben(long id);
    ReturnSchiffDTO addSchiff(PostSchiffDTO postSchiffDTO);
    boolean deleteSchiff(DeleteSchiffDTO deleteSchiffDTO);
}
