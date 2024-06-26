package de.hsos.swa.ab06pers.control;

import de.hsos.swa.ab06pers.boundary.acl.AdressePersistentDTO;
import de.hsos.swa.ab06pers.boundary.acl.PostKundePersistentDTO;
import de.hsos.swa.ab06pers.boundary.acl.ReturnKundePersistentDTO;

import javax.enterprise.context.ApplicationScoped;
import java.util.Collection;


public interface KundenCatalog {
    Collection<ReturnKundePersistentDTO> kundenAbfragen();
    ReturnKundePersistentDTO kundeAnlegen(PostKundePersistentDTO postKundePersistentDTO);
    ReturnKundePersistentDTO kundeAbfragen(long kundenNr);
    boolean kundeLoeschen(long kundenNr);
    AdressePersistentDTO adresseAnlegen(long kundenNr, AdressePersistentDTO adr);
    AdressePersistentDTO adresseAendern(long kundenNr, AdressePersistentDTO neueAdr);
    AdressePersistentDTO adresseAbfragen(long kundenNr);
    boolean adresseLoeschen(long kundenNr);
}
