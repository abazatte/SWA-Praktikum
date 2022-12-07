package org.acme.hibernate.orm.auftragsmanagement.boundary.rest;

import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.*;
import org.acme.hibernate.orm.auftragsmanagement.control.AuftragsInterface;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.EventDTO;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
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

     @Inject
     @Eingegangen
     Event<Long> auftragEingegangen;

    @Inject
    Event<Long> auftragGeändert; //hat keine funktion bisher

    @Inject
    @Storniert
    Event<Long> auftragStorniert;

    @GET
    public Collection<ReturnAuftragDTO> getAllAuftraege() {
        return auftragsInterface.getAllAuftraege();
    }

    @POST
    public ReturnAuftragDTO addAuftrag(PostAuftragDTO postAuftragDTO) {
        ReturnAuftragDTO returnAuftragDTO = auftragsInterface.addAuftrag(postAuftragDTO);
        Long auftragID = returnAuftragDTO.id;
        auftragEingegangen.fire(auftragID);
        return returnAuftragDTO;
    }

    @PATCH
    public ReturnAuftragDTO editAuftrag(PatchAuftragDTO patchAuftragDTO) {
        return auftragsInterface.editAuftrag(patchAuftragDTO);
    }


    @DELETE
    @Path("/{id}")
    public Boolean deleteAuftrag(long id) {
        auftragStorniert.fire(id); //der link oder id vom schiff muss rein
        return auftragsInterface.deleteAuftrag(id);
    }


    @GET
    @Path("/{id}")
    public ReturnAuftragDTO findAuftragByID(long id) {
        return auftragsInterface.findAuftragByID(id);
    }

    public void schiffEmpfangen(@Observes EventDTO eventDTO){
        ReturnAuftragDTO returnAuftragDTO = findAuftragByID(eventDTO.auftragsid);
        returnAuftragDTO.schiffURL = eventDTO.selfuri;
        PatchAuftragDTO patchAuftragDTO = new PatchAuftragDTO(returnAuftragDTO);
        editAuftrag(patchAuftragDTO);
    }
}
