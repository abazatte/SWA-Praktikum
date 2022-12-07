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
        ReturnAuftragDTO auftrag = findAuftragByID(patchAuftragDTO.id);
        if(auftrag != null){
            em.createNamedQuery("Auftrag.update", Auftrag.class).setParameter("beschreibung", patchAuftragDTO.beschreibung).setParameter("eingangsdatum",patchAuftragDTO.eingangsDatum).setParameter("schiffURL",patchAuftragDTO.SchiffURL).getResultList();
            return auftrag;
        }else {
            throw new NotFoundException();
        }
    }

    @Transactional
    @Override
    public Boolean deleteAuftrag(long id) {
        if (findAuftragByID(id) != null) {
            em.createNamedQuery("Auftrag.delete", Auftrag.class).setParameter("id", id).getResultList();
            return true;
        }
        return false;

    }


    @Override
    public ReturnAuftragDTO findAuftragByID(long id) {
        List<Auftrag> retAuftrag = em.createNamedQuery("Auftrag.findByID").setParameter("id",id).getResultList();
        if(retAuftrag.size()>1){
            LOG.info("findAuftragByID kriegt zuviel");
        }
        return new ReturnAuftragDTO(retAuftrag.get(0));
    }
}
