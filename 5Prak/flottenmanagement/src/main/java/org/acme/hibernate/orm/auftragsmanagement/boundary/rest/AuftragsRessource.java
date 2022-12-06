package org.acme.hibernate.orm.auftragsmanagement.boundary.rest;

import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.PatchAuftragDTO;
import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.PostAuftragDTO;
import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.ReturnAuftragDTO;
import org.acme.hibernate.orm.auftragsmanagement.control.AuftragsInterface;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import java.util.Collection;

@Path("/auftraege")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class AuftragsRessource {

    @Inject
    AuftragsInterface auftragsInterface;

    @GET
    public Collection<ReturnAuftragDTO> getAllAuftraege() {
        return auftragsInterface.getAllAuftraege();
    }

    @POST
    public ReturnAuftragDTO addAuftrag(PostAuftragDTO postAuftragDTO) {
        return auftragsInterface.addAuftrag(postAuftragDTO);
    }

    @PATCH
    public ReturnAuftragDTO editAuftrag(PatchAuftragDTO patchAuftragDTO) {
        return auftragsInterface.editAuftrag(patchAuftragDTO);
    }


    @DELETE
    public Boolean deleteAuftrag(long id) {
        return auftragsInterface.deleteAuftrag(id);
    }


    @GET
    public ReturnAuftragDTO findAuftragByID(long id) {
        return auftragsInterface.findAuftragByID(id);
    }
}
