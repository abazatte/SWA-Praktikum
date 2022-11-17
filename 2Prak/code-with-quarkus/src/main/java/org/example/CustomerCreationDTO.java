package org.example;

import java.util.Objects;

import org.jboss.logging.Logger;

public final class CustomerCreationDTO {
    private String name;
    private double deposite;

    public CustomerCreationDTO() {
    }

    public CustomerCreationDTO(String name, double deposite) {
        this.setName(name);
        this.setDeposite(deposite);
    }

    public final void setName(String name) {
        Objects.requireNonNull(name);
        this.name = name;
    }


    public String getName() {
        return this.name;
    }
    public final void setDeposite(double deposite) {
        Logger logger = Logger.getLogger(CustomerCreationDTO.class);
        logger.info("Deposite: " + deposite);
        if(deposite < 0) {
            deposite = 0.;
        }
        this.deposite = deposite;
    }

    public double getDeposite() {
        return this.deposite;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        long temp;
        temp = Double.doubleToLongBits(deposite);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        CustomerCreationDTO other = (CustomerCreationDTO) obj;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return (Double.doubleToLongBits(deposite) == Double.doubleToLongBits(other.deposite));
    }

    @Override
    public String toString() {
        return "CustomerCreationDTO [name=" + name + ", deposite=" + deposite + "]";
    }
}
