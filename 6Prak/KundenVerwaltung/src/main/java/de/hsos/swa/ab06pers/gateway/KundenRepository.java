package de.hsos.swa.ab06pers.gateway;

import de.hsos.swa.ab06pers.boundary.acl.AdressePersistentDTO;
import de.hsos.swa.ab06pers.boundary.acl.PostKundePersistentDTO;
import de.hsos.swa.ab06pers.boundary.acl.ReturnKundePersistentDTO;
import de.hsos.swa.ab06pers.control.KundenCatalog;
import de.hsos.swa.ab06pers.entity.AdressePersistent;
import de.hsos.swa.ab06pers.entity.KundePersistent;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;

@ApplicationScoped
public class KundenRepository implements KundenCatalog {
    @Inject
    EntityManager em;

    @Override
    public Collection<ReturnKundePersistentDTO> kundenAbfragen() {
        Collection<KundePersistent> kunden = em.createNamedQuery("KundePersistent.findAll", KundePersistent.class).getResultList();
        Collection<ReturnKundePersistentDTO> returnKundePersistentDTOS = new ArrayList<>();
        for(KundePersistent kunde: kunden){
            returnKundePersistentDTOS.add(new ReturnKundePersistentDTO(kunde));
        }
        return returnKundePersistentDTOS;
    }

    @Transactional
    @Override
    public ReturnKundePersistentDTO kundeAnlegen(PostKundePersistentDTO postKundePersistentDTO) {
        KundePersistent kunde = new KundePersistent();
        kunde.setNachname(postKundePersistentDTO.nachname);
        kunde.setVorname(postKundePersistentDTO.vorname);
        AdressePersistent adressePersistent = new AdressePersistent();
        adressePersistent.setKundenNr(kunde.getKundenNummer());
        kunde.setAdresse(adressePersistent);

        em.persist(kunde);
        return new ReturnKundePersistentDTO(kunde);
    }

    @Override
    public ReturnKundePersistentDTO kundeAbfragen(long kundenNr) {
        return new ReturnKundePersistentDTO(em.find(KundePersistent.class, kundenNr));
    }

    @Transactional
    @Override
    public boolean kundeLoeschen(long kundenNr) {
        em.remove(em.find(KundePersistent.class, kundenNr));
        return true;
    }

    @Transactional
    @Override
    public AdressePersistentDTO adresseAnlegen(long kundenNr, AdressePersistentDTO adr) {
        KundePersistent kunde = em.find(KundePersistent.class, kundenNr);
        AdressePersistent adresse = new AdressePersistent();
        adresse.setKundenNr(kundenNr);
        adresse.setHausnr(adr.hausnr);
        adresse.setOrt(adr.ort);
        adresse.setPlz(adr.plz);
        adresse.setStrasse(adr.strasse);
        kunde.setAdresse(adresse);

        em.flush();
        return new AdressePersistentDTO(adresse);
    }

    @Transactional
    @Override
    public AdressePersistentDTO adresseAendern(long kundenNr, AdressePersistentDTO neueAdr) {
        KundePersistent kunde = em.find(KundePersistent.class, kundenNr);
        AdressePersistent adresse = new AdressePersistent();
        adresse.setKundenNr(kundenNr);
        adresse.setHausnr(neueAdr.hausnr);
        adresse.setOrt(neueAdr.ort);
        adresse.setPlz(neueAdr.plz);
        adresse.setStrasse(neueAdr.strasse);
        kunde.setAdresse(adresse);

        em.flush();
        return new AdressePersistentDTO(adresse);
    }

    @Override
    public AdressePersistentDTO adresseAbfragen(long kundenNr) {
        KundePersistent kunde = em.find(KundePersistent.class, kundenNr);

        return new AdressePersistentDTO(kunde.getAdresse());
    }

    @Transactional
    @Override
    public boolean adresseLoeschen(long kundenNr) {
        em.remove(em.find(AdressePersistent.class, kundenNr));
        return true;
    }
}
