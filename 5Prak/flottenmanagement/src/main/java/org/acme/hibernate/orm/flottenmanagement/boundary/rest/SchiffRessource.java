package org.acme.hibernate.orm.flottenmanagement.boundary.rest;

import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.Eingegangen;
import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.Storniert;
import org.acme.hibernate.orm.auftragsmanagement.gateway.repo.AuftragsRepository;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.EventDTO;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.PostSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.ReturnSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.control.Schiffinterface;
import org.acme.hibernate.orm.flottenmanagement.shared.ResourceUriBuilder;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Collection;

@Path("/schiffe")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class SchiffRessource {

    private static final Logger LOG = Logger.getLogger(SchiffRessource.class);
    @Inject
    Schiffinterface schiffinterface;

    @Inject
    Event<EventDTO> uriSendEvent;

    @Inject
    ResourceUriBuilder resourceUriBuilder;
    @Context
    UriInfo uriInfo;

    @GET
    public Collection<ReturnSchiffDTO> getAll() {
        return schiffinterface.getAll();
    }

    @GET
    @Path("/{id}")
    public ReturnSchiffDTO getByID(long id) {
        return schiffinterface.getSchiffByID(id);
    }

    @POST
    @Transactional
    public Response postSchiff(PostSchiffDTO postSchiffDTO) {
        return Response.ok(schiffinterface.addSchiff(postSchiffDTO)).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public ReturnSchiffDTO updateSchiff(@PathParam("id") long id, @QueryParam("hatAuftrag") boolean hatAuftrag) {
        return schiffinterface.auftragAnSchiffUebergeben(id, hatAuftrag);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(long id) {
        boolean deleted = schiffinterface.deleteSchiff(id);
        if (deleted) {
            return Response.status(204).build();
        }
        return Response.status(404).build();
    }

    public void schiffNimmtAuftragAn(@Observes @Eingegangen Long auftragID){
        LOG.info("is angekomm");
        ReturnSchiffDTO returnSchiffDTO = getFreeShip();
        schiffinterface.auftragAnSchiffUebergeben(returnSchiffDTO.id, true);

        EventDTO eventDTO = new EventDTO();
        eventDTO.selfuri = addSelfLinkToSchiff(returnSchiffDTO).getUri();
        eventDTO.auftragsid = auftragID;


        uriSendEvent.fire(eventDTO);


    }

    //das ist noch haram, hier muss das schiff gefunden werden
    public void auftragDesSchiffsStorniert(@Observes @Storniert Long schiffsID){
        schiffinterface.auftragAnSchiffUebergeben(schiffsID, false);
    }

    private ReturnSchiffDTO getFreeShip() {
        Collection<ReturnSchiffDTO> returnSchiffDTOS = schiffinterface.getAll();
        for (ReturnSchiffDTO s : returnSchiffDTOS) {
            if (!s.hatAuftrag) {
                return s;
            }
        }
        throw new NotFoundException("Alle Schiffe haben momentan einen Auftrag!");
    }
    private Link addSelfLinkToSchiff(ReturnSchiffDTO returnSchiffDTO) {
        URI selfUri = this.resourceUriBuilder.forSchiff(returnSchiffDTO.id,this.uriInfo);
        Link link = Link.fromUri(selfUri)
                .rel("self")
                .type(MediaType.APPLICATION_JSON)
                .param("get Team", "GET")
                .param("change Team", "PUT")
                .param("delete Team", "DELETE")
                .build();
        return link;
    }
}
