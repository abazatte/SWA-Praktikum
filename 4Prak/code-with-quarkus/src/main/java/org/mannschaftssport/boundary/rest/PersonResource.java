package org.mannschaftssport.boundary.rest;

import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.mannschaftssport.boundary.ACL.*;
import org.mannschaftssport.control.PersonInterface;
import org.mannschaftssport.control.TeamInterface;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
@Path("/person")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PersonResource {

    @Inject
    PersonInterface personManagement;
    @Inject
    TeamInterface teamManagement;
    @Inject
    SpielerpassDummy spielerpassDummy;

    @PostConstruct
    private void init(){
        SpielerpassDTO spielerpassDTO = spielerpassDummy.generateSpielerpassDTO();
        PlayerDTO player = new PlayerDTO(spielerpassDTO.id, new ConcurrentHashMap<>());

        this.personManagement.addPlayer(player,spielerpassDTO);
        /*
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
*/
    }

    @POST
    @Path("/player")
    @Retry(maxRetries = 4)
    @Timeout(250)
    //Spielerpass haram, name wird nicht übergeben
    public Response addPerson(AddPersonDTO addPersonDTO){

        if(addPersonDTO != null){
            SpielerpassDTO spielerpassDTO =spielerpassDummy.generateSpielerpassDTO();
            PlayerDTO playerDTO = new PlayerDTO(addPersonDTO,spielerpassDTO);
            return Response.ok(personManagement.addPlayer(playerDTO,spielerpassDTO)).build();
        }
        return Response.noContent().build();
    }
/*
    @POST
    @Path("/player/{id}")
    @Retry(maxRetries = 4)
    @Timeout(250)
    public Response addAttributes(@PathParam("id")long id, Map<String,String> attributes){

        PlayerDTO playerDTO = this.personManagement.getPlayerByID(id);

        if(playerDTO != null){
            return Response.ok(this.personManagement.addAttributes(id, attributes)).build();
        }

        return Response.noContent().build();
    }*/
    @POST
    @Path("/player/attributes")
    @Retry(maxRetries = 4)
    @Timeout(250)
    public Response addAttributes(AddAttribDTO addAttribDTO){

        PlayerDTO playerDTO = this.personManagement.getPlayerByID(addAttribDTO.id);

        if(playerDTO != null){
            return Response.ok(this.personManagement.addAttributes(addAttribDTO.id, addAttribDTO.attributes)).build();
        }

        return Response.noContent().build();
    }


    @GET
    @Path("/player/{id}")
    @Retry(maxRetries = 4)
    @Timeout(250)
    public Response getPlayerByID(@PathParam("id")int id){
        PlayerDTO playerDTO = this.personManagement.getPlayerByID(id);
        if(playerDTO != null){
            return Response.ok(playerDTO).build();
        }
        return Response.noContent().build();
    }
    @GET
    @Path("/player/players")
    @Retry(maxRetries = 4)
    @Timeout(250)
    public Response getAllPlayer(){
        Collection<PlayerDTO> playerDTOS = this.personManagement.getAllPlayer();
        if (!playerDTOS.isEmpty()){
            return Response.ok(playerDTOS).build();
        }
        return Response.noContent().build();
    }
}
