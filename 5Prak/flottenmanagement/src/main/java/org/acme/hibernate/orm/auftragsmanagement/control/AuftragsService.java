package org.acme.hibernate.orm.auftragsmanagement.control;

import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.PatchAuftragDTO;
import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.PostAuftragDTO;
import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.ReturnAuftragDTO;
import org.acme.hibernate.orm.auftragsmanagement.entity.AuftragsCatalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;

@ApplicationScoped
public class AuftragsService{
    @Inject
    AuftragsCatalog auftragsRepository;


    public Collection<ReturnAuftragDTO> getAllAuftraege() {
        return auftragsRepository.getAllAuftraege();
    }

    public ReturnAuftragDTO addAuftrag(PostAuftragDTO postAuftragDTO) {
        return auftragsRepository.addAuftrag(postAuftragDTO);
    }

    public ReturnAuftragDTO editAuftrag(PatchAuftragDTO patchAuftragDTO) {
        return auftragsRepository.editAuftrag(patchAuftragDTO);
    }

    public Boolean deleteAuftrag(long id) {
        return auftragsRepository.deleteAuftrag(id);
    }

    public ReturnAuftragDTO findAuftragByID(long id) {
        return auftragsRepository.findAuftragByID(id);
    }
}
