package org.acme.hibernate.orm.flottenmanagement.gateway.repository;

import io.quarkus.panache.common.Sort;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.DeleteSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.PostSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.ReturnSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.control.Schiffinterface;
import org.acme.hibernate.orm.flottenmanagement.entity.Schiff;
import org.acme.hibernate.orm.flottenmanagement.entity.SchiffCatalog;

import javax.enterprise.context.ApplicationScoped;
import java.util.*;

@ApplicationScoped
public class SchiffRepository implements SchiffCatalog {

    public SchiffRepository() {

    }

    @Override
    public Collection<ReturnSchiffDTO> getAll() {
        List<Schiff> schiffList = Schiff.listAll(Sort.by("id"));
        Collection<ReturnSchiffDTO> returnSchiffDTOS = new ArrayList<>();
        for (Schiff s: schiffList){
            returnSchiffDTOS.add(new ReturnSchiffDTO(s));
        }
        return returnSchiffDTOS;
    }

    @Override
    public ReturnSchiffDTO getSchiffByID(long id) {
        Schiff schiff = Schiff.findById(id);
        ReturnSchiffDTO returnSchiffDTO = new ReturnSchiffDTO(schiff);
        return returnSchiffDTO;
    }

    @Override
    public ReturnSchiffDTO auftragAnSchiffUebergeben(long id, boolean hatAuftrag) {
        Schiff schiff = Schiff.findById(id);
        schiff.setHatAuftrag(hatAuftrag);
        ReturnSchiffDTO returnSchiffDTO = new ReturnSchiffDTO(schiff);
        return returnSchiffDTO;
    }

    @Override
    public ReturnSchiffDTO addSchiff(PostSchiffDTO postSchiffDTO) {
        Schiff schiff = new Schiff(postSchiffDTO);
        schiff.persist();
        return new ReturnSchiffDTO(schiff);
    }

    @Override
    public boolean deleteSchiff(long id) {
        Schiff schiff = Schiff.findById(id);
        if(schiff != null){
            schiff.delete();
            return true;
        }else {
            return false;
        }
    }
}
