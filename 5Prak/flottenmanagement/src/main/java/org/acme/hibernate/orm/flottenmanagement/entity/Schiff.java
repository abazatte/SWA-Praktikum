package org.acme.hibernate.orm.flottenmanagement.entity;

public class Schiff {
    private long id;
    private String name;
    private boolean hatAuftrag;

    public Schiff(long id, String name){
        this.setId(id);
        this.setName(name);
        this.setHatAuftrag(false);
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
