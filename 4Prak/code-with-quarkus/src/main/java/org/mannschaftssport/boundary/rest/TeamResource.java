package org.mannschaftssport.boundary.rest;

import org.boundary.CocktailDTO;
import org.boundary.rest.CocktailResource;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.jboss.logging.Logger;
import org.mannschaftssport.boundary.ACL.CreateTeamDTO;
import org.mannschaftssport.boundary.ACL.ManagerDTO;
import org.mannschaftssport.boundary.ACL.PlayerDTO;
import org.mannschaftssport.boundary.ACL.TeamDTO;
import org.mannschaftssport.control.PersonInterface;
import org.mannschaftssport.control.TeamInterface;
import org.mannschaftssport.entity.Team;
import org.mannschaftssport.shared.ResourceUriBuilder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Optional;

@ApplicationScoped
@Path("/team")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TeamResource {

    @Inject
    PersonInterface personManagement;
    @Inject
    TeamInterface teamManagement;
    @Inject
    ResourceUriBuilder resourceUriBuilder;
    @Context
    UriInfo uriInfo;

    private static final Logger LOG = Logger.getLogger(TeamResource.class);


    @PostConstruct
    private void init(){

    }

    @GET
    @Path("/teams")
    @Retry(maxRetries = 4)
    @Timeout(250)
    public Response getAllTeams(){

        Collection<TeamDTO> allTeamDTO = teamManagement.getAllTeams();

        if(allTeamDTO==null) {
            return Response.noContent().build();
        }

        return Response.ok().entity(allTeamDTO).build();
    }

    @GET
    @Path("/teams/{id}")
    @Retry(maxRetries = 4)
    @Timeout(250)
    public Response getTeamByID(@PathParam("id")int id){
        TeamDTO optTeamDTO = this.teamManagement.getTeamByID(id);

        if(optTeamDTO != null){
            try{
                return Response.ok(optTeamDTO).build();
            } catch (RuntimeException e){
                return Response.noContent().build();
            }
        }
        return Response.noContent().build();
    }

    @POST
    @Path("/teams")
    @Retry(maxRetries = 4)
    @Timeout(250)
    public Response createTeam(CreateTeamDTO createTeamDTO){
        try{
            TeamDTO dto = this.teamManagement.createTeam(new TeamDTO(createTeamDTO));
            return Response.ok(dto).build();
        } catch (RuntimeException e){
            String message = e.getClass().getSimpleName() + ": " + e.getMessage();
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(message)
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .build();
        }
    }
    /*
    @PATCH
    @Path("/teams")
    @Retry(maxRetries = 4)
    @Timeout(250)//Queryparam ist haram, lieber json übergeben
    public Response updateTeam(@QueryParam("id")long id, @QueryParam("attributes")Map<String,String> attributes){
        TeamDTO optTeamDTO = this.teamManagement.getTeamByID(id);
        if (optTeamDTO != null){
            return Response.ok(this.teamManagement.updateTeam(id,attributes)).build();
        }
        return Response.noContent().build();
    }*/
    @DELETE
    @Path("/{id}")
    @Retry(maxRetries = 4)
    @Timeout(250)
    public Response deleteTeamById(@PathParam("id")int id){
        TeamDTO dto = this.teamManagement.getTeamByID(id);
        if(dto != null){
           this.teamManagement.deleteTeamByID(id);
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
    @GET
    @Path("/{id}/manager")
    @Retry(maxRetries = 4)
    @Timeout(250)
    public Response getManagerFromTeam(@PathParam("id")int id){
        ArrayList<Object> data = new ArrayList<>();
        TeamDTO optTeamDTO = this.teamManagement.getTeamByID(id);
/**
 *  HIER KÖNNTE HARAM SEIN!
 */
        if(optTeamDTO != null){
            ManagerDTO managerDTO = this.teamManagement.getManagerFromTeam(id);
            data.add(optTeamDTO);
            data.add(managerDTO);
            return Response.ok(data).build();
        }
        return Response.noContent().build();
    }
    @PUT
    @Path("/{id}/relationships/manager")
    @Retry(maxRetries = 4)
    @Timeout(250)
    public Response setManagerToTeam(@PathParam("id")int id,ManagerDTO managerDTO){
        TeamDTO optTeamDTO = this.teamManagement.getTeamByID(id);
        if(optTeamDTO != null){
            return Response.ok(this.teamManagement.setManagerToTeam(id,managerDTO)).build();
        }
        return Response.noContent().build();
    }
    @PUT
    @Path("/{id}/relationships/players")
    @Retry(maxRetries = 4)
    @Timeout(250)
    public Response setPlayersToTeam(@PathParam("id")int id,Collection<PlayerDTO> playerDTO){
        TeamDTO optTeamDTO = this.teamManagement.getTeamByID(id);

        if(optTeamDTO != null){
            return Response.ok(this.teamManagement.setPlayersToTeam(playerDTO,id)).build();
        }
        return Response.noContent().build();
    }

    private void addSelfLinkToTeamDTO(TeamDTO teamDTO) {
        URI selfUri = this.resourceUriBuilder.forTeam(teamDTO.id,this.uriInfo);
        Link link = Link.fromUri(selfUri)
                .rel("self")
                .type(MediaType.APPLICATION_JSON)
                .param("get studentin", "GET")
                .param("change studentin", "PUT")
                .param("delete studentin", "DELETE")
                .build();
        teamDTO.addLink("self", link);
    }
}
