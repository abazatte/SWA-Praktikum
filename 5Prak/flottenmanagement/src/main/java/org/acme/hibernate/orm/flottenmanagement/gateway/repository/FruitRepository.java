package org.acme.hibernate.orm.flottenmanagement.gateway.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class FruitRepository implements PanacheRepository<Fruit> {
}