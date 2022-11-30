package org.mannschaftssport.shared;

import org.mannschaftssport.boundary.rest.TeamResource;

import java.net.URI;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.UriInfo;



@ApplicationScoped
public class ResourceUriBuilder {

    public URI forTeam(Long id, UriInfo uriInfo) {
        return createResourceUri(TeamResource.class, "getTeamByID", id, uriInfo);
    }

    private URI createResourceUri(Class<?> resourceClass, String method, Long id, UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().path(resourceClass).path(resourceClass, method).build(id);
    }
    
}
