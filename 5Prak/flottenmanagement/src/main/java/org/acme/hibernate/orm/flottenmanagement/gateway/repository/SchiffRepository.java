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
    private final Map<Integer, Schiff> schiffMap;

    public SchiffRepository() {
        schiffMap = new HashMap<>();
    }

    @Override
    public Collection<ReturnSchiffDTO> getAll() {
        return null;
    }

    @Override
    public ReturnSchiffDTO getSchiffByID(long id) {
        return null;
    }

    @Override
    public ReturnSchiffDTO auftragAnSchiffUebergeben(long id) {
        return null;
    }

    @Override
    public ReturnSchiffDTO addSchiff(PostSchiffDTO postSchiffDTO) {
        return null;
    }

    @Override
    public boolean deleteSchiff(DeleteSchiffDTO deleteSchiffDTO) {
        return false;
    }
}
