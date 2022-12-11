package de.hsos.swa.ab06.boundary.rs;

import com.oracle.svm.core.annotate.Delete;
import de.hsos.swa.ab06.control.KundenService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/kundenverwaltung")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class KundenResource {
    @Inject
    KundenService kundenService;
    @GET
    public Response kundenAbfragen(){
        return Response.ok(kundenService.kundenAbfragen()).build();
    }

    @GET
    @Path("/{id}")
    public Response kundeAbfragen(long id){
        return Response.ok(kundenService.kundeAbfragen(id)).build();
    }

    @POST
    public Response kundeAnlegen(PostKundeDTO postKundeDTO){
        return Response.ok(kundenService.kundeAnlegen(postKundeDTO)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response kundeLoeschen(long id) {
        if(kundenService.kundeLoeschen(id)){
            return Response.ok().build();
        }else {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/kunden/{id}")
    public Response adresseAbfragen(long id){
        return Response.ok(kundenService.adresseAbfragen(id)).build();
    }

    @POST
    @Path("/kunden/{id}")
    public Response adresseAnlegen(long id, AdresseDTO adresseDTO){
        return Response.ok(kundenService.adresseAnlegen(id,adresseDTO)).build();
    }

    @PUT
    @Path("/kunden/{id}")
    public Response adresseAendern(long id, AdresseDTO adresseDTO){
        return Response.ok(kundenService.adresseAendern(id,adresseDTO)).build();
    }

    @DELETE
    @Path("/kunden/{id}")
    public Response adresseLoeschen(long id){
        return Response.ok(kundenService.adresseLoeschen(id)).build();
    }


}
