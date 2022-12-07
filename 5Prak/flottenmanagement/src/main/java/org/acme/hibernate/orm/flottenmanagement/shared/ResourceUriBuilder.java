package org.acme.hibernate.orm.flottenmanagement.shared;

import org.acme.hibernate.orm.auftragsmanagement.entity.Auftrag;
import org.acme.hibernate.orm.flottenmanagement.boundary.rest.SchiffRessource;
import org.acme.hibernate.orm.flottenmanagement.entity.Schiff;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.UriInfo;
import java.net.URI;


@ApplicationScoped
public class ResourceUriBuilder {

    public URI forSchiff(Long id, UriInfo uriInfo) {
        return createResourceUri(SchiffRessource.class, "getByID", id, uriInfo);
    }

    private URI createResourceUri(Class<?> resourceClass, String method, Long id, UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().path(resourceClass).path(resourceClass, method).build(id);
    }
    
}
