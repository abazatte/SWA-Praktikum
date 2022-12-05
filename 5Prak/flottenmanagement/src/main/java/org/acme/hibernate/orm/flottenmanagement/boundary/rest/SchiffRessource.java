package org.acme.hibernate.orm.flottenmanagement.boundary.rest;

import org.acme.hibernate.orm.flottenmanagement.boundary.acl.ReturnSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.control.Schiffinterface;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import java.util.Collection;

@Path("/schiff")
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
}
