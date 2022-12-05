package org.acme.hibernate.orm.flottenmanagement.gateway.repository;

import org.acme.hibernate.orm.flottenmanagement.boundary.acl.DeleteSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.PostSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.ReturnSchiffDTO;
import org.acme.hibernate.orm.flottenmanagement.control.Schiffinterface;
import org.acme.hibernate.orm.flottenmanagement.entity.Schiff;
import org.acme.hibernate.orm.flottenmanagement.entity.SchiffCatalog;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class SchiffRepository implements SchiffCatalog {

    public SchiffRepository() {

    }

    @Override
    public Collection<ReturnSchiffDTO> getAll() {
        return (Collection<ReturnSchiffDTO>) Schiff.findAll();
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
        return new ReturnSchiffDTO(schiff);
    }

    @Override
    public boolean deleteSchiff(DeleteSchiffDTO deleteSchiffDTO) {
        Schiff schiff = Schiff.findById(deleteSchiffDTO.id);
        if(schiff != null){
            schiff.delete();
            return true;
        }else {
            return false;
        }
    }
}
