package org.boundary.rest;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.boundary.MocktailDTO;
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Timed;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.jboss.logging.Logger;
import org.mocktailapp.control.*;

import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Path("/mocktail")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MocktailResource {
    private int counter = 0;
    AddMocktail addNutzerIn = new NutzerIn();
    EditMocktail editNutzerIn = new NutzerIn();
    DeleteMocktail deleteNutzerIn = new NutzerIn();
    GetMocktail getNutzerIn = new NutzerIn();

    private static final Logger LOG = Logger.getLogger(MocktailResource.class);

    @PostConstruct
    @Timed(name = "initTimer", description = "Wie lange braucht die init-Methode", unit = MetricUnits.MILLISECONDS)
    public void init(){
        counter++;
        MocktailDTO mangoMule = new MocktailDTO("Mango Mule mong", "Lecker.");
        mangoMule.addZutaten("4 Gurken Scheiben");
        mangoMule.addZutaten("1 Loeffel Honigsirup");
        mangoMule.addZutaten("1/2 Loeffel Mangopuree");
        this.addNutzerIn.addMocktail(mangoMule);
        MocktailDTO mangoSule = new MocktailDTO("Mango Sule song", "Nicht so Lecker.");
        mangoSule.addZutaten("4 Gurken Scheiben");
        mangoSule.addZutaten("1 Loeffel Honigsirup");
        mangoSule.addZutaten("1/2 Loeffel Mangopuree");
        this.addNutzerIn.addMocktail(mangoSule);
    }

    @GET
    @Operation(summary = "Gets all Mocktails", description = "Lists all available mocktails")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NutzerIn.class))))
    @Counted(name = "getAllCount", description = "Wie oft wurde die mocktailList-Methode ausgefuehrt?")
    @Timed(name = "getAllTimer", description = "Wie lange braucht die mocktailList-Methode", unit = MetricUnits.MILLISECONDS)
    public Collection<MocktailDTO> mocktailList() {
        counter++;
        LOG.info("getMocktails aufgerufen\n");
        return this.getNutzerIn.getMocktails();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Gets Mocktail per ID", description = "Lists the Mocktail with same ID as typed")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NutzerIn.class))))
    @Counted(name = "getByIDCount", description = "Wie oft wurde die getMocktail-Methode ausgefuehrt?")
    @Timed(name = "getByIDTimer", description = "Wie lange braucht die getMocktail-Methode", unit = MetricUnits.MILLISECONDS)
    public Response geMocktail(int id){
        counter++;
        Optional<MocktailDTO> optMocktail = this.getNutzerIn.findById(id);
        if(optMocktail.isPresent()){
            return Response.ok(optMocktail.get()).build();
        }
        return Response.noContent().build();
    }

    @POST
    @Path("/post")
    @Operation(summary = "Post a MocktailDTO", description = "Creates new Mocktail")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NutzerIn.class))))
    @Counted(name = "postCount", description = "Wie oft wurde die newMocktail-Methode ausgefuehrt?")
    @Timed(name = "postTimer", description = "Wie lange braucht die newMocktail-Methode", unit = MetricUnits.MILLISECONDS)
    public Response newMocktail(MocktailDTO mocktail){
        counter++;
        this.addNutzerIn.addMocktail(mocktail);
        return Response.status(Status.CREATED).entity(mocktail).build();
    }

    @DELETE @Path("/{id}")
    @Operation(summary = "Delete Mocktail", description = "Delete Mocktail per ID")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NutzerIn.class))))
    @Counted(name = "deleteCount", description = "Wie oft wurde die deleteMocktail-Methode ausgefuehrt?")
    @Timed(name = "deleteTimer", description = "Wie lange braucht die deleteMocktail-Methode", unit = MetricUnits.MILLISECONDS)
    public Response deleteMocktail(int id){
        counter++;
        Optional<MocktailDTO> optMocktail = this.getNutzerIn.findById(id);
        if(optMocktail.isPresent()){
            //System.out.println("delete Object: " + optMocktail.get().getId());
            this.deleteNutzerIn.deleteMocktail(id);
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
 

    @PUT 
    @Path("/put")
    @Operation(summary = "Edit Mocktail", description = "Edit Mocktail with new name, beschreibung")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NutzerIn.class))))
    @Counted(name = "putCount", description = "Wie oft wurde die editMocktail-Methode ausgefuehrt?")
    @Timed(name = "putTimer", description = "Wie lange braucht die editMocktail-Methode", unit = MetricUnits.MILLISECONDS)
    public Response editMocktail(@QueryParam("oldname") String oldname,@QueryParam("newname") String newname,@QueryParam("beschreibung") String beschreibung){
        counter++;
        this.editNutzerIn.editMocktail(oldname, newname, beschreibung);
        return Response.ok(this.getNutzerIn.getMocktail(this.getNutzerIn.getMocktailID(newname))).build();
    }

    @PATCH @Path("/{id}")
    @Operation(summary = "Add Zutat", description = "Added new Zutat to given Mocktail per ID")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NutzerIn.class))))
    @Counted(name = "patchAddCount", description = "Wie oft wurde die addZutat-Methode ausgefuehrt?")
    @Timed(name = "patchAddTimer", description = "Wie lange braucht die addZutat-Methode", unit = MetricUnits.MILLISECONDS)
    public Response addZutat(@QueryParam("zutat") String zutat,@PathParam("id") int id){
        counter++;
        Optional<MocktailDTO> optMocktail = this.getNutzerIn.findById(id);
        if(!optMocktail.isPresent()){
            return Response.noContent().build();
        }
        this.editNutzerIn.addZutat(zutat, id);
        return Response.ok(this.getNutzerIn.getMocktail(id)).build();
    }
    
    @PATCH @Path("/deleteZutat/{id}")
    @Operation(summary = "Delete 1 Zutat", description = "Delete Zutat from Mocktail per ID")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NutzerIn.class))))
    @Counted(name = "patchDeleteCount", description = "Wie oft wurde die deleteZutat-Methode ausgefuehrt?")
    @Timed(name = "patchDeleteTimer", description = "Wie lange braucht die deleteZutat-Methode?", unit = MetricUnits.MILLISECONDS)
    public Response deleteZutat(@QueryParam("zutat") String zutat, @PathParam("id") int id){
        counter++;
        Optional<MocktailDTO> optMocktail = this.getNutzerIn.findById(id);
        if(!optMocktail.isPresent()){
            return Response.noContent().build();
        }
        this.editNutzerIn.deleteZutat(zutat, id);
        return Response.ok(this.getNutzerIn.getMocktail(id)).build();
    }

    @Gauge(name = "actionsPerformed", unit = MetricUnits.NONE, description = "Wie viele Aktionen wurden durchgefuehrt.")
    public int actionsPerformed() {
        return counter;
    }
/* 
    @POST
    @Path("/post")
    //public void postMocktail(String anleitung, String name, ArrayList<String> zutatenList){
    public void postMocktail(Mocktail mocktail){
        //Mocktail toAdd = new Mocktail(name, anleitung);
        //for (int i = 0; i < zutatenList.size(); i++) {
        //    toAdd.addZutaten(zutatenList.get(i));
        //}
        this.nutzerIn.add(mocktail);
    }

    @GET
    @Path("/delete/{id}")
    public void deleteMocktail(int id){
        mocktails.remove(id);
    }

*/
}