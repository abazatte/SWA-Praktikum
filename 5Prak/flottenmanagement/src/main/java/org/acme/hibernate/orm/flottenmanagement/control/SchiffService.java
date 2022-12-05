package org.acme.hibernate.orm.flottenmanagement.control;

import javax.inject.Inject;

public class SchiffService {
    @Inject
    SchiffCatalog schiffRepository;


    @Override
    public SchiffDTO AuftragAnSchiffUebergeben(long id) {
        return schiffRepository.AuftragAnSchiffUebergeben(id);
    }

    @Override
    public ReturnSchiffDTO addSchiff(PostSchiffDTO postSchiffDTO) {
        return schiffRepository.addSchiff(postSchiffDTO);
    }

    @Override
    public boolean deleteSchiff(DeleteSchiffDTO deleteSchiffDTO) {
        return schiffRepository.deleteSchiff(deleteSchiffDTO);
    }
}
