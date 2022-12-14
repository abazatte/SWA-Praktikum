package de.hsos.swa.ab06pers.control;

import de.hsos.swa.ab06pers.boundary.acl.ReturnKundePersistentDTO;

import java.util.Collection;

public interface KundenCatalog {
    Collection<ReturnKundePersistentDTO> kundenAbfragen();

}
