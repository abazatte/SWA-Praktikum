package org.acme.hibernate.orm.flottenmanagement.boundary.rest;

import org.acme.hibernate.orm.flottenmanagement.boundary.acl.PostSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.ReturnSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.control.Schiffinterface;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/schiffe")
@ApplicationScoped
@Produces("application/json")
@Consumes("application/json")
public class SchiffRessource {

    @Inject
    Schiffinterface schiffinterface;
    @GET
    public Collection<ReturnSchiffDTO> getAll(){
        return schiffinterface.getAll();
    }

    @GET
    @Path("/{id}")
    public ReturnSchiffDTO getByID(long id){
        return schiffinterface.getSchiffByID(id);
    }

    @POST
    @Transactional
    public Response postSchiff(PostSchiffDTO postSchiffDTO){
        return Response.ok(schiffinterface.addSchiff(postSchiffDTO)).build();
    }

    @PATCH
    @Path("/{id}")
    @Transactional
    public ReturnSchiffDTO updateSchiff(@PathParam("id") long id, @QueryParam("hatAuftrag") boolean hatAuftrag){
        return schiffinterface.AuftragAnSchiffUebergeben(id, hatAuftrag);
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response delete(long id){
        boolean deleted = schiffinterface.deleteSchiff(id);
        if (deleted){
            return Response.status(204).build();
        }
        return Response.status(404).build();
    }
}
