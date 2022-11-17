package org.example;

public final class CustomerDTO {
    public String id;
    public String name; 
    public double deposite;

    public CustomerDTO() {
    }

    public CustomerDTO(String id, String name, double deposite) {
        this.id = id;
        this.name = name;
        this.deposite = deposite;
    }

    public static Customer fromDto(CustomerDTO customerDTO) {
        return new Customer(customerDTO.id, customerDTO.name, customerDTO.deposite);
    }

    public static CustomerDTO toDto(Customer customer) {
        return new CustomerDTO(customer.getId(), customer.getName(), customer.getDeposite());
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
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
        CustomerDTO other = (CustomerDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "CustomerDTO [id=" + id + ", name=" + name + ", deposite=" + deposite + "]";
    }
}
