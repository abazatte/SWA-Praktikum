package org.acme.hibernate.orm.auftragsmanagement.control;


import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.PatchAuftragDTO;
import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.PostAuftragDTO;
import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.ReturnAuftragDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;

@ApplicationScoped
public class AuftragsManager implements AuftragsInterface{

    @Inject
    AuftragsService auftragsService;

    @Override
    public Collection<ReturnAuftragDTO> getAllAuftraege() {
        return auftragsService.getAllAuftraege();
    }

    @Override
    public ReturnAuftragDTO addAuftrag(PostAuftragDTO postAuftragDTO) {
        return auftragsService.addAuftrag(postAuftragDTO);
    }

    @Override
    public ReturnAuftragDTO editAuftrag(PatchAuftragDTO patchAuftragDTO) {
        return auftragsService.editAuftrag(patchAuftragDTO);
    }

    @Override
    public Boolean deleteAuftrag(long id) {
        return auftragsService.deleteAuftrag(id);
    }

    @Override
    public ReturnAuftragDTO findAuftragByID(long id) {
        return auftragsService.findAuftragByID(id);
    }
}
