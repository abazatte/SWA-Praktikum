package de.hsos.swa.ab06pers.boundary.rs;

import de.hsos.swa.ab06pers.boundary.acl.AdressePersistentDTO;
import de.hsos.swa.ab06pers.boundary.acl.PostKundePersistentDTO;
import de.hsos.swa.ab06pers.boundary.acl.ReturnKundePersistentDTO;
import de.hsos.swa.ab06pers.control.KundenCatalog;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;

@Path("/kundenpersistent")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RequestScoped
public class KundenPersistentResource {

    @Inject
    KundenCatalog kundenCatalog;
    @GET
    public Response kundenAbfragen() {
        return Response.ok(kundenCatalog.kundenAbfragen()).build();
    }
    @GET
    @Path("/{id}")
    public Response kundeAbfragen(long id){
        return Response.ok(kundenCatalog.kundeAbfragen(id)).build();
    }


    @POST
    public Response kundeAnlegen(PostKundePersistentDTO postKundePersistentDTO) {
        return Response.ok(kundenCatalog.kundeAnlegen(postKundePersistentDTO)).build();
    }

    @DELETE
    @Path("/{id}")
    public Response kundeLoeschen(long kundenNr) {
        if(kundenCatalog.kundeLoeschen(kundenNr)){
            return Response.ok().build();
        }else {
            return Response.serverError().build();
        }
    }

    @GET
    @Path("/adresse/{id}")
    public Response adresseAbfragen(long kundenNr) {
        return Response.ok(kundenCatalog.adresseAbfragen(kundenNr)).build();
    }

    @POST
    @Path("/adresse/{id}")
    public Response adresseAnlegen(long kundenNr, AdressePersistentDTO adr) {
        return Response.ok(kundenCatalog.adresseAnlegen(kundenNr,adr)).build();
    }

    @PUT
    @Path("/adresse/{id}")
    public Response adresseAendern(long kundenNr, AdressePersistentDTO neueAdr) {
        return Response.ok(kundenCatalog.adresseAendern(kundenNr,neueAdr)).build();
    }
    @DELETE
    @Path("/adresse/{id}")
    public Response adresseLoeschen(long kundenNr) {
        return Response.ok(kundenCatalog.adresseLoeschen(kundenNr)).build();
    }
}
