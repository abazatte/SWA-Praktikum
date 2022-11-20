package org.boundary.rest;

import org.boundary.CocktailDTO;
import org.cocktailapp.controlcocktail.CocktailInterface;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.cocktailapp.controlcocktail.CocktailNutzerIn;

/** Aufgabe 3.2 */
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

import org.jboss.logging.Logger;
import org.mocktailapp.control.NutzerIn;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Path("/cocktail")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CocktailResource {
    CocktailInterface cocktailNutzerIn = new CocktailNutzerIn();
    private int counter = 0;
    private static final Logger LOG = Logger.getLogger(CocktailResource.class);

    @PostConstruct
    @Timed(name = "initTimer", description = "Wie lange braucht die init-Methode", unit = MetricUnits.MILLISECONDS)
    public void init(){
        counter++;
        CocktailDTO test = new CocktailDTO();
        test.id = 0;
        test.name = "test";
        test.anleitung = "nicht trinken, hoch giftig.";
        List<String> testArray = new ArrayList<>();
        testArray.add("Gift");
        test.zutatenList = testArray;
        this.cocktailNutzerIn.addCocktail(test);
    }

    @GET
    @Operation(summary = "Gets all Cocktails", description = "Lists all available cocktails")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NutzerIn.class))))
    @Counted(name = "getAllCount", description = "Wie oft wurde die cocktailList-Methode ausgefuehrt?")
    @Timed(name = "getAllTimer", description = "Wie lange braucht die cocktailList-Methode", unit = MetricUnits.MILLISECONDS)
    public Collection<CocktailDTO> cocktailList(){
        counter++;
        LOG.info("getCocktails aufgerufen\n");
        return this.cocktailNutzerIn.getCocktails();
    }

    @GET
    @Path("/{id}")
    @Operation(summary = "Gets Cocktail per ID", description = "Lists the Cocktail with same ID as typed")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NutzerIn.class))))
    @Counted(name = "getByIDCount", description = "Wie oft wurde die getCocktail-Methode ausgefuehrt?")
    @Timed(name = "getByIDTimer", description = "Wie lange braucht die getCocktail-Methode", unit = MetricUnits.MILLISECONDS)
    public Response getCocktail(@PathParam("id") int id){
        counter++;
        Optional<CocktailDTO> optCocktailDTO = this.cocktailNutzerIn.findById(id);
        if (optCocktailDTO.isPresent()){
            return Response.ok(optCocktailDTO.get()).build();
        }
        return Response.noContent().build();
    }

    @POST
    @Path("/post")
    @Operation(summary = "Post a CocktailDTO", description = "Creates new Cocktail")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NutzerIn.class))))
    @Counted(name = "postCount", description = "Wie oft wurde die newCocktail-Methode ausgefuehrt?")
    @Timed(name = "postTimer", description = "Wie lange braucht die newCocktail-Methode", unit = MetricUnits.MILLISECONDS)
    public Response newCocktail(CocktailDTO cocktailDTO){
        counter++;
        return Response.ok(this.cocktailNutzerIn.addCocktail(cocktailDTO)).build();
    }

    @DELETE
    @Path("/{id}")
    @Operation(summary = "Delete Cocktail", description = "Delete Cocktail per ID")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NutzerIn.class))))
    @Counted(name = "deleteCount", description = "Wie oft wurde die deleteCocktail-Methode ausgefuehrt?")
    @Timed(name = "deleteTimer", description = "Wie lange braucht die deleteCocktail-Methode", unit = MetricUnits.MILLISECONDS)
    public Response deleteCocktail(@PathParam("id") int id){
        counter++;
        Optional<CocktailDTO> optCocktailDTO = this.cocktailNutzerIn.findById(id);
        if (optCocktailDTO.isPresent()){
            this.cocktailNutzerIn.deleteCocktail(id);
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @PUT
    @Path("/put")
    @Operation(summary = "Edit Cocktail", description = "Edit Cocktail with new name, beschreibung")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NutzerIn.class))))
    @Counted(name = "putCount", description = "Wie oft wurde die editCocktail-Methode ausgefuehrt?")
    @Timed(name = "putTimer", description = "Wie lange braucht die editCocktail-Methode", unit = MetricUnits.MILLISECONDS)
    public Response editCocktail(@QueryParam("oldname") String oldname, @QueryParam("newname") String newname, @QueryParam("beschreibung") String beschreibung){
        counter++;
        return Response.ok(this.cocktailNutzerIn.editCocktail(oldname, newname, beschreibung)).build();
    }

    @PATCH
    @Path("/{id}")
    @Operation(summary = "Add Zutat", description = "Added new Zutat to given Cocktail per ID")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NutzerIn.class))))
    @Counted(name = "patchAddCount", description = "Wie oft wurde die addZutat-Methode ausgefuehrt?")
    @Timed(name = "patchAddTimer", description = "Wie lange braucht die addZutat-Methode", unit = MetricUnits.MILLISECONDS)
    public Response addZutat(@QueryParam("zutat") String zutat, @PathParam("id") int id){
        counter++;
        Optional<CocktailDTO> optCocktailDTO = this.cocktailNutzerIn.findById(id);
        if (optCocktailDTO.isPresent()){
            return Response.ok(this.cocktailNutzerIn.addZutat(zutat, id)).build();
        }
        return Response.noContent().build();
    }

    @PATCH
    @Path("/deleteZutat/{id}")
    @Operation(summary = "Delete 1 Zutat", description = "Delete Zutat from Cocktail per ID")
    @APIResponses(value = @APIResponse(responseCode = "200", description = "Success",
            content = @Content(mediaType = "application/json", schema = @Schema(implementation = NutzerIn.class))))
    @Counted(name = "patchDeleteCount", description = "Wie oft wurde die deleteZutat-Methode ausgefuehrt?")
    @Timed(name = "patchDeleteTimer", description = "Wie lange braucht die deleteZutat-Methode?", unit = MetricUnits.MILLISECONDS)
    public Response deleteZutat(@QueryParam("zutat") String zutat, @PathParam("id") int id){
        counter++;
        Optional<CocktailDTO> optCocktailDTO = this.cocktailNutzerIn.findById(id);
        if (optCocktailDTO.isPresent()){
            return Response.ok(this.cocktailNutzerIn.deleteZutat(zutat, id)).build();
        }
        return Response.noContent().build();
    }

    @Gauge(name = "actionsPerformed", unit = MetricUnits.NONE, description = "Wie viele Aktionen wurden durchgefuehrt.")
    public int actionsPerformed() {
        return counter;
    }
}
