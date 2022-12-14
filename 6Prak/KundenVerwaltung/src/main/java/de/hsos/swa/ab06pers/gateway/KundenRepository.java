package de.hsos.swa.ab06pers.gateway;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class KundenRepository {
    @Inject
    EntityManager em;

}
