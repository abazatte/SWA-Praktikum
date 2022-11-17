package org.example;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerManagement {
    private final Map<String, Customer> customers = new ConcurrentHashMap<>();
    
    public  Customer add(String name, double deposite) {
        Customer customer = new Customer(this.createId(), name, deposite);
        this.customers.put(customer.getId(), customer);
        return customer;
    }

    public  Customer add(CustomerCreationDTO customerDto) {
        return this.add(customerDto.getName(), customerDto.getDeposite());
    }

    private String createId() {
        return UUID.randomUUID().toString();
    }
    
    public Collection<Customer> getAll() {
        return this.customers.values();
    }

    public boolean delete(final Customer customer) {
        return this.customers.remove(customer.getId(), customer);
    }

    public Optional<Customer> findById(String customerId) {
        return Optional.ofNullable(this.customers.get(customerId));
    }

    public Optional<Customer> setDeposite(String customerId, Double deposite) {
        Customer customer = this.customers.get(customerId);
        if(customer == null) return Optional.empty();
        customer.setDeposite(deposite);
        return Optional.of(customer);
    }

    public void updateCustomer(CustomerDTO customerDTO) {
        this.customers.remove(customerDTO.id);
        this.customers.put(customerDTO.id, CustomerDTO.fromDto(customerDTO));
    }


}
