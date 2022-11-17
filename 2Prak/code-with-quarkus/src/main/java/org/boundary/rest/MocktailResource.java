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

import org.mocktailapp.control.AddMocktail;
import org.mocktailapp.control.DeleteMocktail;
import org.mocktailapp.control.EditMocktail;
import org.mocktailapp.control.GetMocktail;
import org.mocktailapp.control.NutzerIn;
import org.mocktailapp.entity.Mocktail;
import java.util.Collection;
import java.util.Optional;

@ApplicationScoped
@Path("/mocktail")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MocktailResource {
    AddMocktail addNutzerIn = new NutzerIn();
    EditMocktail editNutzerIn = new NutzerIn();
    DeleteMocktail deleteNutzerIn = new NutzerIn();
    GetMocktail getNutzerIn = new NutzerIn();

    @PostConstruct
    public void init(){
        Mocktail mangoMule = new Mocktail("Mango Mule mong", "Lecker.");
        mangoMule.addZutaten("4 Gurken Scheiben");
        mangoMule.addZutaten("1 Loeffel Honigsirup");
        mangoMule.addZutaten("1/2 Loeffel Mangopuree");
        this.addNutzerIn.addMocktail(mangoMule);
        Mocktail mangoSule = new Mocktail("Mango Sule song", "Nicht so Lecker.");
        mangoSule.addZutaten("4 Gurken Scheiben");
        mangoSule.addZutaten("1 Loeffel Honigsirup");
        mangoSule.addZutaten("1/2 Loeffel Mangopuree");
        this.addNutzerIn.addMocktail(mangoSule);
    }

    @GET
    public Collection<Mocktail> mocktailList() {        
        return this.getNutzerIn.getMocktails();
    }

    @GET
    @Path("/{id}")
    public Response geMocktail(int id){

        Optional<Mocktail> optMocktail = this.getNutzerIn.findById(id);
        if(optMocktail.isPresent()){
            return Response.ok(optMocktail.get()).build();
        }
        return Response.noContent().build();
    }

    @POST
    @Path("/post")
    public Response newMocktail(Mocktail mocktail){
        this.addNutzerIn.addMocktail(mocktail);
        return Response.status(Status.CREATED).entity(mocktail).build();
    }

    @DELETE @Path("/{id}")
    public Response deleteMocktail(int id){
        Optional<Mocktail> optMocktail = this.getNutzerIn.findById(id);
        if(optMocktail.isPresent()){
            //System.out.println("delete Object: " + optMocktail.get().getId());
            this.deleteNutzerIn.deleteMocktail(id);
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
 

    @PUT 
    @Path("/put")
    public Response editMocktail(@QueryParam("oldname") String oldname,@QueryParam("newname") String newname,@QueryParam("beschreibung") String beschreibung){
        this.editNutzerIn.editMocktail(oldname, newname, beschreibung);
        return Response.ok(this.getNutzerIn.getMocktail(this.getNutzerIn.getMocktailID(newname))).build();
    }

    @PATCH @Path("/{id}")
    public Response addZutat(@QueryParam("zutat") String zutat,@PathParam("id") int id){
        Optional<Mocktail> optMocktail = this.getNutzerIn.findById(id);
        if(!optMocktail.isPresent()){
            return Response.noContent().build();
        }
        this.editNutzerIn.addZutat(zutat, id);
        return Response.ok(this.getNutzerIn.getMocktail(id)).build();
    }
    
    @PATCH @Path("/deleteZutat/{id}")
    public Response deleteZutat(@QueryParam("zutat") String zutat, @PathParam("id") int id){
        Optional<Mocktail> optMocktail = this.getNutzerIn.findById(id);
        if(!optMocktail.isPresent()){
            return Response.noContent().build();
        }
        this.editNutzerIn.deleteZutat(zutat, id);
        return Response.ok(this.getNutzerIn.getMocktail(id)).build();
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