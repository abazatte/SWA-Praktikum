package org.mannschaftssport.boundary.rest;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.jboss.logging.Logger;
import org.mannschaftssport.boundary.ACL.*;
import org.mannschaftssport.control.PersonInterface;
import org.mannschaftssport.control.TeamInterface;
import org.mannschaftssport.entity.Person;
import org.mannschaftssport.entity.Team;
import org.mannschaftssport.shared.ResourceUriBuilder;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.*;

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
        int id = 100;
        Map<String, String> attributes = new HashMap<>();
        attributes.put("verein","nein");
        PlayerDTO playerDTO = new PlayerDTO(id,attributes);
        ManagerDTO coach = new ManagerDTO(id,attributes);
        Collection<PlayerDTO> players = new ArrayList<>();
        players.add(playerDTO);
        TeamDTO teamDTO = new TeamDTO(id,coach,players,attributes);
        addSelfLinkToTeamDTO(teamDTO);
        createTeamInit(teamDTO);

    }

    @GET
    @Path("/teams")
    @Retry(maxRetries = 4)
    @Timeout(250)
    public Response getAllTeams(){

        Collection<TeamDTO> allTeamDTO = teamManagement.getAllTeams();

        for(TeamDTO dto: allTeamDTO){
            addSelfLinkToTeamDTO(dto);
        }



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

        addSelfLinkToTeamDTO(optTeamDTO);

        if(optTeamDTO != null){
            try{
                addSelfLinkToManagerDTO(optTeamDTO.coach);
                for (PlayerDTO playerDTO: optTeamDTO.players){
                    addSelfLinkToPlayerDTO(playerDTO);
                }
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
        LOG.info(createTeamDTO);
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

    private Response createTeamInit(TeamDTO teamDTO){
        try{
            TeamDTO dto = this.teamManagement.createTeam(teamDTO);
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
        LOG.info(optTeamDTO);
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
    public Response setManagerToTeam(@PathParam("id")int id, PUTManagerDTO putManagerDTO){
        TeamDTO optTeamDTO = this.teamManagement.getTeamByID(id);
        ManagerDTO managerDTO = new ManagerDTO(putManagerDTO);
        if(optTeamDTO != null){
            return Response.ok(this.teamManagement.setManagerToTeam(id,managerDTO)).build();
        }
        return Response.noContent().build();
    }
    @PUT
    @Path("/{id}/relationships/players")
    @Retry(maxRetries = 4)
    @Timeout(250)
    public Response setPlayersToTeam(@PathParam("id")int id,Collection<PlayerRelationshipDTO> playerRelationshipDTO){
        TeamDTO optTeamDTO = this.teamManagement.getTeamByID(id);
        Collection<PlayerDTO> playerDTOS = new ArrayList<>();

        for (PlayerRelationshipDTO s:playerRelationshipDTO) {

            playerDTOS.add(new PlayerDTO(s));

        }



        if(optTeamDTO != null){
            return Response.ok(this.teamManagement.setPlayersToTeam(playerDTOS,id)).build();
        }
        return Response.noContent().build();
    }

    private void addSelfLinkToTeamDTO(TeamDTO teamDTO) {
        URI selfUri = this.resourceUriBuilder.forTeam(teamDTO.id,this.uriInfo);
        Link link = Link.fromUri(selfUri)
                .rel("self")
                .type(MediaType.APPLICATION_JSON)
                .param("get Team", "GET")
                .param("change Team", "PUT")
                .param("delete Team", "DELETE")
                .build();
        teamDTO.addLink("self", link);
    }

    private void addSelfLinkToPlayerDTO(PlayerDTO playerDTO) {
        URI selfUri = this.resourceUriBuilder.forPlayer((long) playerDTO.id,this.uriInfo);
        Link link = Link.fromUri(selfUri)
                .rel("self")
                .type(MediaType.APPLICATION_JSON)
                .param("get Player", "GET")
                .param("change Player", "PUT")
                .param("delete Player", "DELETE")
                .build();
        playerDTO.addLink("self", link);
    }

    private void addSelfLinkToManagerDTO(ManagerDTO managerDTO) {
        URI selfUri = this.resourceUriBuilder.forManager((long) managerDTO.id,this.uriInfo);
        Link link = Link.fromUri(selfUri)
                .rel("self")
                .type(MediaType.APPLICATION_JSON)
                .param("get Manager", "GET")
                .param("change Manager", "PUT")
                .param("delete Manager", "DELETE")
                .build();
        managerDTO.addLink("self", link);
    }

}
