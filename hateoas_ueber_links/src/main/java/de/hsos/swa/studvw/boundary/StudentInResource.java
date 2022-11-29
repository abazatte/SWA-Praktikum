package de.hsos.swa.studvw.boundary;

import java.net.URI;
import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import de.hsos.swa.studvw.control.StudentInMgr;
import de.hsos.swa.studvw.entity.StudentIn;
import de.hsos.swa.studvw.shared.ResourceUriBuilder;

@ApplicationScoped
@Path("/studs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class StudentInResource {
    @Inject
    StudentInMgr manager;

    @Inject
    ResourceUriBuilder uriBuilder;

    @Context
    UriInfo uriInfo;

    @PostConstruct
    private void init() {
        this.manager.addStudent("Eva");
        this.manager.addStudent("Anna");
    }

    @GET
    public Response getStuds() {
        Collection<StudentIn> allStuds = manager.getAll();
        Collection<StudentInDTO> allStudDTOWithSelfLinks = allStuds.stream()
            .map(StudentInDTO.Converter::toDTO)
            .peek(this::addSelfLinkToStudentInDTO) ////HATEOAS - Alternative 1: Link im Body (siehe aufgerufene Methode)
            .collect(Collectors.toList());
            
            
        if(allStudDTOWithSelfLinks==null) {
            return Response.noContent().build();
        }
        return Response.ok().entity(allStudDTOWithSelfLinks).build();

    }

    // @GET //http://localhost:8080/studentvw/api/studs?matnr=12345
    // public Resonse getStudByQueryParam(@QueryParam("matnr")Long matnr) {

    // }

    @GET @Path("/{matnr}") //http://localhost:8080/studentvw/api/studs/12345
    public Response getStud(@PathParam("matnr")Long matnr)  {        
        Optional<StudentIn> optStud = this.manager.getStudentByMatnr(matnr);
        if(optStud.isPresent()) {
            URI selfUri = this.uriBuilder.forStudentIn(matnr, this.uriInfo);
            Link linkToStudent = Link.fromUri(selfUri)
                                    .rel("self")
                                    .type(MediaType.APPLICATION_JSON)
                                    .param("method", "GET")
                                    .build();
            return Response.ok().entity(optStud.get()).links(linkToStudent).build(); //HATEOAS - Alternative 2: Link im Header
        }
        return Response.noContent().build();
    }

    private void addSelfLinkToStudentInDTO(StudentInDTO studDTO) {
        URI selfUri = this.uriBuilder.forStudentIn(studDTO.matnr, this.uriInfo);
        Link link = Link.fromUri(selfUri)
                        .rel("self")
                        .type(MediaType.APPLICATION_JSON)
                        .param("get studentin", "GET")
                        .param("change studentin", "PUT")
                        .param("delete studentin", "DELETE")
                        .build();
        studDTO.addLink("self", link);
    }
}
