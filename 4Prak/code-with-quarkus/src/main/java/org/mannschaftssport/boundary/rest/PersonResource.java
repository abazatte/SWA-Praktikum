package org.mannschaftssport.boundary.rest;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.mannschaftssport.boundary.ACL.PlayerDTO;
import org.mannschaftssport.boundary.ACL.SpielerpassDTO;
import org.mannschaftssport.boundary.ACL.TeamDTO;
import org.mannschaftssport.control.PersonInterface;
import org.mannschaftssport.control.TeamInterface;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Map;

@ApplicationScoped
@Path("/team")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonInterface personManagement;
    @Inject
    TeamInterface teamManagement;

    @POST
    @Path("/player")
    @Retry(maxRetries = 4)
    @Timeout(250)
    public Response addPerson(PlayerDTO playerDTO, SpielerpassDTO spielerpassDTO){

        if(playerDTO != null && spielerpassDTO != null){
            return Response.ok(personManagement.addPlayer(playerDTO,spielerpassDTO)).build();
        }
        return Response.noContent().build();
    }
    @POST
    @Path("/player/{id}")
    @Retry(maxRetries = 4)
    @Timeout(250)
    public Response addAttributes(@PathParam("id")long id, Map<String,String> attributes){



        return Response.noContent().build();
    }
}
