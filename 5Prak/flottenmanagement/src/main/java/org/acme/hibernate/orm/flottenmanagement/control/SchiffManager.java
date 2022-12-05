package org.acme.hibernate.orm.flottenmanagement.control;

import javax.inject.Inject;

public class SchiffManager implements Schiffinterface{
    @Inject
    SchiffService schiffService;
    @Override
    public SchiffDTO AuftragAnSchiffUebergeben(long id) {
        return schiffService.AuftragAnSchiffUebergeben(id);
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
