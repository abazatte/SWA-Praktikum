package org.example;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.eclipse.microprofile.config.inject.ConfigProperty;


@ApplicationScoped
@Path("/customers")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CustomerResource {
    @Inject
    CustomerManagement customerMgmt;
    @ConfigProperty(name = "deposite", defaultValue = "3000.00") 
    Double defaultDeposite;

    @PostConstruct
    public void init() {
        this.customerMgmt.add("Dr. Hans A. Plast", defaultDeposite);
        this.customerMgmt.add("Simsa Labim", 2000.0);
    }

    @GET
    public Collection<Customer> getAllCustomers() {
        return this.customerMgmt.getAll();
    }

    @GET @Path("{id}")
    public Response getCustomer(@PathParam("id")String id) {
        Optional<Customer> optCustomer = this.customerMgmt.findById(id);
        if(optCustomer.isPresent()) {
            return Response.ok(optCustomer.get()).build();
        }
        return Response.noContent().build();
    }

    @POST
    public Response newCustomer(CustomerCreationDTO customerDto) {
        Customer customer = this.customerMgmt.add(customerDto.getName(), customerDto.getDeposite());
        return Response.status(Status.CREATED).entity(customer).build();
    }
    
    @PUT @Path("{id}")
    public Response updateCustomerDeposite(CustomerDTO customerDto) {
        this.customerMgmt.updateCustomer(customerDto);
        Optional<Customer> optCustomer = this.customerMgmt.findById(customerDto.id);
        if(optCustomer.isPresent()) {
            return Response.ok(optCustomer.get()).build();
        }
        return Response.noContent().build();
    }

    @PATCH @Path("{id}")
    public Response updateCustomerDeposite(@PathParam("id")String id,
                                           @QueryParam("deposite")Double deposite) {
        Optional<Customer> optionalCustomer = this.customerMgmt.findById(id);
        if(!optionalCustomer.isPresent()) {
            return Response.noContent().build();
        }
        Customer customer = optionalCustomer.get();
        customer.setDeposite(deposite);
        
        return Response.ok(customer).build();
    }

    @DELETE @Path("{id}")
    public Response deleteCustomer(@PathParam("id")String id) {
        Optional<Customer> optCostumer = this.customerMgmt.findById(id);
        if(optCostumer.isPresent()) {
            this.customerMgmt.delete(optCostumer.get());
            return Response.ok().build();
        }
        return Response.noContent().build();
    }
}
