package de.hsos.swa.studvw.shared;

import java.net.URI;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.UriInfo;

import de.hsos.swa.studvw.boundary.StudentInResource;

@ApplicationScoped
public class ResourceUriBuilder {

    public URI forStudentIn(Long matnr, UriInfo uriInfo) {
        return createResourceUri(StudentInResource.class, "getStud", matnr, uriInfo);
    }

    private URI createResourceUri(Class<?> resourceClass, String method, Long id, UriInfo uriInfo) {
        return uriInfo.getBaseUriBuilder().path(resourceClass).path(resourceClass, method).build(id);
    }
    
}
