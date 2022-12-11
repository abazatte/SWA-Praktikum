package de.hsos.swa.ab06.control;

import de.hsos.swa.ab06.boundary.rs.AdresseDTO;
import de.hsos.swa.ab06.boundary.rs.PostKundeDTO;
import de.hsos.swa.ab06.boundary.rs.ReturnKundeDTO;

import java.util.Collection;

public class KundenService {
    public ReturnKundeDTO kundeAnlegen(PostKundeDTO postKundeDTO) {
        return null;
    }

    public Collection<ReturnKundeDTO> kundenAbfragen() {
        return null;
    }

    public ReturnKundeDTO kundeAbfragen(long kundenNr) {
        return null;
    }

    public boolean kundeLoeschen(long kundenNr) {
        return false;
    }

    public AdresseDTO adresseAnlegen(long kundenNr, AdresseDTO adr) {
        return null;
    }

    public AdresseDTO adresseAendern(long kundenNr, AdresseDTO neueAdr) {
        return null;
    }

    public AdresseDTO adresseAbfragen(long kundenNr) {
        return null;
    }

    public boolean adresseLoeschen(long kundenNr) {
        return false;
    }
}
