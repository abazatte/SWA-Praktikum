package org.boundary.acl;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/json/v1/1")
@ApplicationScoped
@RegisterRestClient(configKey="extensions-api")
public interface APIRequestService {

    @GET
    @Path("/filter.php")
    Response getCocktailOrMocktail(@QueryParam("a") String alcoholic);


    @GET
    @Path("/search.php")
    Response getCMocktailByName(@QueryParam("s") String name);

    @GET
    @Path("/filter.php")
    Response getCMocktailByIngredient(@QueryParam("i")List<String> ingredients);
}
