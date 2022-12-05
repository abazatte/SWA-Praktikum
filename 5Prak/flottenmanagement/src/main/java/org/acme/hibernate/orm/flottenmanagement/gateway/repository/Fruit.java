package org.acme.hibernate.orm.flottenmanagement.gateway.repository;

import javax.persistence.*;

@Entity
@Cacheable
public class Fruit {

    @Id

    @GeneratedValue
    public Long id;

    @Column(length = 40, unique = true)
    public String name;

    public Fruit() {
    }

    public Fruit(String name) {
        this.name = name;
    }
}
