package de.hsos.swa.studvw.boundary;

import java.util.HashMap;
import java.util.Map;

import javax.json.bind.annotation.JsonbProperty;
import javax.ws.rs.core.Link;

import de.hsos.swa.studvw.entity.StudentIn;

public class StudentInDTO {
    public Long matnr;
    public String name;
    @JsonbProperty("_links")
    public Map<String, Link> links = new HashMap<>();

    public StudentInDTO() {
    }

    public StudentInDTO(Long matnr, String name) {
        this.matnr = matnr;
        this.name = name;
    }
    
    public static class Converter {

        public static StudentInDTO toDTO(StudentIn stud) {
            return new StudentInDTO(stud.getMatnr(), stud.getName());
        }

        public static StudentIn toStudent(StudentInDTO studDTO) {
            return new StudentIn(studDTO.matnr, studDTO.name);
        }
    }

    public void addLink(String name, Link link) {
        this.links.put(name, link);
    }
}
