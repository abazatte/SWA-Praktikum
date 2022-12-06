package org.acme.hibernate.orm.auftragsmanagement.gateway.repo;

import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.*;
import org.acme.hibernate.orm.auftragsmanagement.entity.Auftrag;
import org.acme.hibernate.orm.auftragsmanagement.entity.AuftragsCatalog;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.*;

@ApplicationScoped
public class AuftragsRepository implements AuftragsCatalog {

    @Inject
    EntityManager em;
    @Override
    public Collection<ReturnAuftragDTO> getAllAuftraege() {
        Collection<Auftrag> auftraege = em.createNamedQuery("Auftrag.findAll", Auftrag.class).getResultList();
        Collection<ReturnAuftragDTO> returnAuftragDTOS = new ArrayList<>();
        for (Auftrag a: auftraege){
            returnAuftragDTOS.add(new ReturnAuftragDTO(a));
        }

        return returnAuftragDTOS;
    }

    @Transactional
    @Override
    public ReturnAuftragDTO addAuftrag(PostAuftragDTO postAuftragDTO) {
        Auftrag auftrag = new Auftrag();
        auftrag.setBeschreibung(postAuftragDTO.beschreibung);
        java.sql.Date currentDate = new java.sql.Date(Calendar.getInstance(TimeZone.getTimeZone("GMT+1")).getTimeInMillis());
        auftrag.setEingangsDatum(currentDate);
        em.persist(auftrag);
        return new ReturnAuftragDTO(auftrag);
    }
    @Transactional
    @Override
    public ReturnAuftragDTO editAuftrag(PatchAuftragDTO patchAuftragDTO) {

        return null;
    }
    @Transactional
    @Override
    public Boolean deleteAuftrag(long id) {
        return null;
    }

    @Override
    public ReturnAuftragDTO findAuftragByID(long id) {
        return null;
    }
}
