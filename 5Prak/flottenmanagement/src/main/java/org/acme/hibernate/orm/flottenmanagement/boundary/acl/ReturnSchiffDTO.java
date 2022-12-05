package org.acme.hibernate.orm.flottenmanagement.boundary.acl;

import org.acme.hibernate.orm.flottenmanagement.entity.Schiff;

public class ReturnSchiffDTO {
    public long id;
    public String name;
    public boolean hatAuftrag;
    public ReturnSchiffDTO(Schiff schiff){
        this.id = schiff.getId();
        this.name = schiff.getName();
        this.hatAuftrag = schiff.isHatAuftrag();
    }
}
