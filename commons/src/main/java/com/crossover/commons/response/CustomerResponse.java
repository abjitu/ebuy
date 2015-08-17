package com.crossover.commons.response;

import com.crossover.commons.request.Customer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CustomerResponse {

    private final Response response;

    private final Customer customer;

    public CustomerResponse(Response response, Customer customer) {
        super();
        this.response = response;
        this.customer = customer;
    }

    public CustomerResponse() {
        super();
        this.response = null;
        this.customer = null;
    }

    public Response getResponse() {
        return response;
    }

    public Customer getCustomer() {
        return customer;
    }

}
