package org.boundary.rest;



import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/** Aufgabe 3.2 */
import org.boundary.CocktailDTO;
import org.boundary.acl.APIRequestService;
import org.cocktailapp.controlcocktail.CocktailInterface;
import org.cocktailapp.controlcocktail.CocktailNutzerIn;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.media.Content;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;

/** Aufgabe 3.3 */
import org.eclipse.microprofile.metrics.MetricUnits;
import org.eclipse.microprofile.metrics.annotation.Counted;
import org.eclipse.microprofile.metrics.annotation.Gauge;
import org.eclipse.microprofile.metrics.annotation.Timed;

import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.jboss.logging.Logger;

import java.util.*;

@ApplicationScoped
@Path("/cocktail")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CocktailResource {
    //könnte auch inject sein
    CocktailInterface cocktailNutzerIn = new CocktailNutzerIn();
    private int counter = 0;
    private static final Logger LOG = Logger.getLogger(CocktailResource.class);

    @Inject
    @RestClient
    APIRequestService apiRequestService;

    @PostConstruct
    @Timed(name = "initTimer", description = "Wie lange braucht die init-Methode", unit = MetricUnits.MILLISECONDS)
    public void init(){
        LOG.info("init aufgerufen");
        counter++;
        CocktailDTO test = new CocktailDTO();
        test.id = 0;
        test.name = "test";
        test.anleitung = "nicht trinken, hoch giftig.";
        List<String> testArray = new ArrayList<>();
        testArray.add("Gift");
        test.zutatenList = testArray;
        this.cocktailNutzerIn.addCocktail(test);
        LOG.info("init beendet");
    }

    @GET
    @Retry(maxRetries = 4)
    @Timeout(250)
    @Operation(summary = "Gets all Cocktails", description = "Lists all available cocktails")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CocktailNutzerIn.class))))
    @Counted(name = "getAllCount", description = "Wie oft wurde die cocktailList-Methode ausgefuehrt?")
    @Timed(name = "getAllTimer", description = "Wie lange braucht die cocktailList-Methode", unit = MetricUnits.MILLISECONDS)
    public Collection<CocktailDTO> cocktailList(){
        LOG.info("getCocktails aufgerufen\n");
        if (new Random().nextBoolean()) {
            LOG.error("cocktailResourceFailed absichtlich");
            throw new RuntimeException("Resource failure.");
        }
        return this.cocktailNutzerIn.getCocktails();
    }

    @GET
    @Path("/cocktailDB")
    public Response getAllCocktailsFromDB(){
        return apiRequestService.getCocktailOrMocktail("Alcoholic");
    }

    @GET
    @Path("/cocktailDB/searchByName")
    public Response getCocktailsByNameFromDB(@QueryParam("s") String name){
        return apiRequestService.getCMocktailByName(name);
    }

    @GET
    @Path("/{id}")
    @Retry(maxRetries = 4)
    @Fallback(fallbackMethod = "fallbackGetCocktail")
    @Timeout(250)
    //timeout ist dafür da, das bei überladenen servern lieber abgebrochen wird anstatt die server weiter zu belasten
    @Operation(summary = "Gets Cocktail per ID", description = "Lists the Cocktail with same ID as typed")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CocktailNutzerIn.class))))
    @Counted(name = "getByIDCount", description = "Wie oft wurde die getCocktail-Methode ausgefuehrt?")
    @Timed(name = "getByIDTimer", description = "Wie lange braucht die getCocktail-Methode", unit = MetricUnits.MILLISECONDS)
    public Response getCocktail(@PathParam("id") int id){
        LOG.info("getCocktail aufgerufen");
        counter++;
        long started = System.currentTimeMillis();

        Optional<CocktailDTO> optCocktailDTO = this.cocktailNutzerIn.findById(id);
        if (optCocktailDTO.isPresent()){

            //random delay ausprobieren
            try {
                randomDelay();
                LOG.infof("CoffeeResource#recommendations() invocation  returning successfully");
                return Response.ok(optCocktailDTO.get()).build();
            } catch (InterruptedException e) {
                LOG.errorf("CoffeeResource#recommendations() invocation  timed out after %d ms", System.currentTimeMillis() - started);
                return Response.noContent().build();
            }
        }
        LOG.errorf("Cocktail under id:{ " + id + "} not found");
        return Response.noContent().build();
    }

    private void randomDelay() throws InterruptedException {
        Thread.sleep(new Random().nextInt(500));
    }

    public Response fallbackGetCocktail(int id){
        CocktailDTO fallbackDTO = new CocktailDTO();
        fallbackDTO.id = 0;
        fallbackDTO.name = "fallback Cocktail";
        fallbackDTO.anleitung = "ganz einfach";
        fallbackDTO.zutatenList = new ArrayList<>();
        fallbackDTO.zutatenList.add("2 shots of Vodka");
        return Response.ok(fallbackDTO).build();
    }

    @POST
    @Path("/post")
    @Retry(maxRetries = 4)
    @Timeout(250)
    @Operation(summary = "Post a CocktailDTO", description = "Creates new Cocktail")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CocktailNutzerIn.class))))
    @Counted(name = "postCount", description = "Wie oft wurde die newCocktail-Methode ausgefuehrt?")
    @Timed(name = "postTimer", description = "Wie lange braucht die newCocktail-Methode", unit = MetricUnits.MILLISECONDS)
    public Response newCocktail(CocktailDTO cocktailDTO){
        counter++;
        try {
            CocktailDTO dto = this.cocktailNutzerIn.addCocktail(cocktailDTO);
            LOG.infof("CocktailResource#newCocktail() invocation returning successfully");
            return Response.ok(dto).build();
        } catch (RuntimeException e) {
            String message = e.getClass().getSimpleName() + ": " + e.getMessage();
            LOG.errorf("CocktailResource#newCocktail() invocation failed: %s", message);
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(message)
                    .type(MediaType.TEXT_PLAIN_TYPE)
                    .build();
        }
        //return Response.ok(this.cocktailNutzerIn.addCocktail(cocktailDTO)).build();
    }

    @DELETE
    @Path("/{id}")
    @Retry(maxRetries = 4)
    @Timeout(250)
    @Operation(summary = "Delete Cocktail", description = "Delete Cocktail per ID")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CocktailNutzerIn.class))))
    @Counted(name = "deleteCount", description = "Wie oft wurde die deleteCocktail-Methode ausgefuehrt?")
    @Timed(name = "deleteTimer", description = "Wie lange braucht die deleteCocktail-Methode", unit = MetricUnits.MILLISECONDS)
    public Response deleteCocktail(@PathParam("id") int id){
        LOG.info("deleteCocktail aufgerufen");
        counter++;
        Optional<CocktailDTO> optCocktailDTO = this.cocktailNutzerIn.findById(id);
        if (optCocktailDTO.isPresent()){
            LOG.info("deleteCocktail Cocktail gefunden");
            this.cocktailNutzerIn.deleteCocktail(id);
            return Response.ok().build();
        }
        LOG.info("deleteCocktail Cocktail nicht gefunden");
        return Response.noContent().build();
    }

    @PUT
    @Path("/put")
    @Retry(maxRetries = 4)
    @Timeout(250)
    @Operation(summary = "Edit Cocktail", description = "Edit Cocktail with new name, beschreibung")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CocktailNutzerIn.class))))
    @Counted(name = "putCount", description = "Wie oft wurde die editCocktail-Methode ausgefuehrt?")
    @Timed(name = "putTimer", description = "Wie lange braucht die editCocktail-Methode", unit = MetricUnits.MILLISECONDS)
    public Response editCocktail(@QueryParam("oldname") String oldname, @QueryParam("newname") String newname, @QueryParam("beschreibung") String beschreibung){
        LOG.info("editCocktail aufgerufen");
        counter++;
        return Response.ok(this.cocktailNutzerIn.editCocktail(oldname, newname, beschreibung)).build();
    }

    @PATCH
    @Path("/{id}")
    @Retry(maxRetries = 4)
    @Timeout(250)
    @Operation(summary = "Add Zutat", description = "Added new Zutat to given Cocktail per ID")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CocktailNutzerIn.class))))
    @Counted(name = "patchAddCount", description = "Wie oft wurde die addZutat-Methode ausgefuehrt?")
    @Timed(name = "patchAddTimer", description = "Wie lange braucht die addZutat-Methode", unit = MetricUnits.MILLISECONDS)
    public Response addZutat(@QueryParam("zutat") String zutat, @PathParam("id") int id){
        LOG.info("addZutat aufgerufen");
        counter++;
        Optional<CocktailDTO> optCocktailDTO = this.cocktailNutzerIn.findById(id);
        if (optCocktailDTO.isPresent()){
            LOG.info("addZutat Cocktail gefunden");
            return Response.ok(this.cocktailNutzerIn.addZutat(zutat, id)).build();
        }
        LOG.info("addZutat Cocktail nicht gefunden");
        return Response.noContent().build();
    }

    @PATCH
    @Path("/deleteZutat/{id}")
    @Retry(maxRetries = 4)
    @Timeout(250)
    @Operation(summary = "Delete 1 Zutat", description = "Delete Zutat from Cocktail per ID")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = CocktailNutzerIn.class))))
    @Counted(name = "patchDeleteCount", description = "Wie oft wurde die deleteZutat-Methode ausgefuehrt?")
    @Timed(name = "patchDeleteTimer", description = "Wie lange braucht die deleteZutat-Methode?", unit = MetricUnits.MILLISECONDS)
    public Response deleteZutat(@QueryParam("zutat") String zutat, @PathParam("id") int id){
        LOG.info("deleteZutat aufgerufen");
        counter++;
        Optional<CocktailDTO> optCocktailDTO = this.cocktailNutzerIn.findById(id);
        if (optCocktailDTO.isPresent()){
            LOG.info("deleteZutat Cocktail gefunden");
            return Response.ok(this.cocktailNutzerIn.deleteZutat(zutat, id)).build();
        }
        LOG.info("deleteZutat Cocktail nicht gefunden");
        return Response.noContent().build();
    }

    @Gauge(name = "actionsPerformed", unit = MetricUnits.NONE, description = "Wie viele Aktionen wurden durchgefuehrt.")
    public int actionsPerformed() {
        return counter;
    }
}
