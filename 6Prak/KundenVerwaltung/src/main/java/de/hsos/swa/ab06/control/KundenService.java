package de.hsos.swa.ab06.control;

import de.hsos.swa.ab06.boundary.rs.AdresseDTO;
import de.hsos.swa.ab06.boundary.rs.PostKundeDTO;
import de.hsos.swa.ab06.boundary.rs.ReturnKundeDTO;
import de.hsos.swa.ab06.entity.Adresse;
import de.hsos.swa.ab06.entity.Kunde;

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@ApplicationScoped
public class KundenService {
    private Map<Long, Kunde> kundeMap = new ConcurrentHashMap<>();
    AtomicLong idGen = new AtomicLong();

    private Long createID(){
        return idGen.getAndIncrement();
    }
    public ReturnKundeDTO kundeAnlegen(PostKundeDTO postKundeDTO) {
        Kunde kunde = new Kunde();
        kunde.setVorname(postKundeDTO.vorname);
        kunde.setNachname(postKundeDTO.nachname);
        kunde.setKundenNummer(createID());
        kundeMap.put(kunde.getKundenNummer(), kunde);
        return kundeAbfragen(kunde.getKundenNummer());
    }

    public Collection<ReturnKundeDTO> kundenAbfragen() {
        Collection<Kunde> kundeCollection = kundeMap.values();
        Collection<ReturnKundeDTO> returnKundeDTOS = new ArrayList<>();
        for (Kunde kunde: kundeCollection) {
            returnKundeDTOS.add(new ReturnKundeDTO(kunde));
        }
        return returnKundeDTOS;
    }

    public ReturnKundeDTO kundeAbfragen(long kundenNr) {
        return new ReturnKundeDTO(kundeMap.get(kundenNr));
    }

    public boolean kundeLoeschen(long kundenNr) {
        Kunde kunde = kundeMap.remove(kundenNr);
        return kunde != null;
    }

    public AdresseDTO adresseAnlegen(long kundenNr, AdresseDTO adr) {
        Adresse adresse = new Adresse();
        adresse.setHausnr(adr.hausnr);
        adresse.setOrt(adr.ort);
        adresse.setPlz(adr.plz);
        adresse.setStrasse(adr.strasse);
        Kunde kunde = kundeMap.get(kundenNr);
        kunde.setAdresse(adresse);
        kundeMap.replace(kundenNr, kunde);
        return adr;
    }

    public AdresseDTO adresseAendern(long kundenNr, AdresseDTO neueAdr) {
        Adresse adresse = new Adresse();
        adresse.setHausnr(neueAdr.hausnr);
        adresse.setOrt(neueAdr.ort);
        adresse.setPlz(neueAdr.plz);
        adresse.setStrasse(neueAdr.strasse);
        Kunde kunde = kundeMap.get(kundenNr);
        kunde.setAdresse(adresse);
        kundeMap.replace(kundenNr, kunde);
        return neueAdr;
    }

    public AdresseDTO adresseAbfragen(long kundenNr) {
        Kunde kunde = kundeMap.get(kundenNr);
        return new AdresseDTO(kunde.getAdresse());
    }

    public boolean adresseLoeschen(long kundenNr) {
        kundeMap.get(kundenNr).setAdresse(new Adresse());
        return kundeMap.get(kundenNr).getAdresse().getHausnr() == null;
    }
}
