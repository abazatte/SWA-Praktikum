package org.boundary.rest;

import org.boundary.CocktailDTO;
import org.cocktailapp.controlcocktail.CocktailInterface;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.cocktailapp.controlcocktail.CocktailNutzerIn;
import org.jboss.logging.Logger;

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

    private static final Logger LOG = Logger.getLogger(CocktailResource.class);

    @PostConstruct
    public void init(){
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
    public Collection<CocktailDTO> cocktailList(){
        LOG.info("getCocktails aufgerufen\n");
        return this.cocktailNutzerIn.getCocktails();
    }

    @GET
    @Path("/{id}")
    public Response getCocktail(@PathParam("id") int id){
        Optional<CocktailDTO> optCocktailDTO = this.cocktailNutzerIn.findById(id);
        if (optCocktailDTO.isPresent()){
            return Response.ok(optCocktailDTO.get()).build();
        }
        return Response.noContent().build();
    }

    @POST
    @Path("/post")
    public Response newCocktail(CocktailDTO cocktailDTO){
        return Response.ok(this.cocktailNutzerIn.addCocktail(cocktailDTO)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteCocktail(@PathParam("id") int id){
        Optional<CocktailDTO> optCocktailDTO = this.cocktailNutzerIn.findById(id);
        if (optCocktailDTO.isPresent()){
            this.cocktailNutzerIn.deleteCocktail(id);
            return Response.ok().build();
        }
        return Response.noContent().build();
    }

    @PUT
    @Path("/put")
    public Response editCocktail(@QueryParam("oldname") String oldname, @QueryParam("newname") String newname, @QueryParam("beschreibung") String beschreibung){
        return Response.ok(this.cocktailNutzerIn.editCocktail(oldname, newname, beschreibung)).build();
    }

    @PATCH
    @Path("/{id}")
    public Response addZutat(@QueryParam("zutat") String zutat, @PathParam("id") int id){
        Optional<CocktailDTO> optCocktailDTO = this.cocktailNutzerIn.findById(id);
        if (optCocktailDTO.isPresent()){
            return Response.ok(this.cocktailNutzerIn.addZutat(zutat, id)).build();
        }
        return Response.noContent().build();
    }

    @PATCH
    @Path("/deleteZutat/{id}")
    public Response deleteZutat(@QueryParam("zutat") String zutat, @PathParam("id") int id){
        Optional<CocktailDTO> optCocktailDTO = this.cocktailNutzerIn.findById(id);
        if (optCocktailDTO.isPresent()){
            return Response.ok(this.cocktailNutzerIn.deleteZutat(zutat, id)).build();
        }
        return Response.noContent().build();
    }
}
