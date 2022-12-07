package org.acme.hibernate.orm.auftragsmanagement.gateway.repo;

import org.acme.hibernate.orm.auftragsmanagement.boundary.acl.*;
import org.acme.hibernate.orm.auftragsmanagement.entity.Auftrag;
import org.acme.hibernate.orm.auftragsmanagement.entity.AuftragsCatalog;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import java.util.*;

@ApplicationScoped
public class AuftragsRepository implements AuftragsCatalog {

    @Inject
    EntityManager em;

    private static final Logger LOG = Logger.getLogger(AuftragsRepository.class);
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

        Auftrag auftrag1 = em.find(Auftrag.class, patchAuftragDTO.id);
        auftrag1.setBeschreibung(patchAuftragDTO.beschreibung);
        auftrag1.setEingangsDatum(patchAuftragDTO.eingangsDatum);
        auftrag1.setSchiffURL(patchAuftragDTO.SchiffURL);
        em.flush();
        /*
        ReturnAuftragDTO auftrag = findAuftragByID(patchAuftragDTO.id);
        if(auftrag != null){
            em.createNamedQuery("Auftrag.update").setParameter("id",patchAuftragDTO.id).setParameter("beschreibung", patchAuftragDTO.beschreibung).setParameter("eingangsdatum",patchAuftragDTO.eingangsDatum).setParameter("schiffURL",patchAuftragDTO.SchiffURL).getResultList();
            return auftrag;
        }else {
            throw new NotFoundException();
        }*/

        return new ReturnAuftragDTO(auftrag1);
    }

    @Transactional
    @Override
    public Boolean deleteAuftrag(long id) {
        em.remove(em.find(Auftrag.class, id));
        /*
        if (findAuftragByID(id) != null) {
            em.createNamedQuery("Auftrag.delete").setParameter("id", id).getResultList();
            return true;
        }*/
        return true;

    }


    @Override
    public ReturnAuftragDTO findAuftragByID(long id) {

        return new ReturnAuftragDTO(em.find(Auftrag.class, id));
    }

}
