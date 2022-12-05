package org.acme.hibernate.orm.flottenmanagement.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import org.acme.hibernate.orm.flottenmanagement.boundary.acl.PostSchiffDTO;

import javax.persistence.*;

@Entity
@Cacheable
public class Schiff extends PanacheEntity {
   @Id
   @GeneratedValue
    private long id;

    @Column(length = 100, unique = true)
    private String name;
    private boolean hatAuftrag;

    public Schiff(long id, String name){
        this.setId(id);
        this.setName(name);
        this.setHatAuftrag(false);
    }

    public Schiff(PostSchiffDTO postSchiffDTO){
        this.setName(postSchiffDTO.name);
        this.setHatAuftrag(false);
    }

    public Schiff() {

    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isHatAuftrag() {
        return hatAuftrag;
    }

    public void setHatAuftrag(boolean hatAuftrag) {
        this.hatAuftrag = hatAuftrag;
    }
}
