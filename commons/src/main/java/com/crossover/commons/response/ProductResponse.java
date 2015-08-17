package com.crossover.commons.response;

import com.crossover.commons.request.Product;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ProductResponse {

    private final Response response;

    private final Product product;

    public ProductResponse(Response response, Product product) {
        super();
        this.response = response;
        this.product = product;
    }

    public ProductResponse() {
        this.response = null;
        this.product = null;
    }

    public Response getResponse() {
        return response;
    }

    public Product getProduct() {
        return product;
    }

}
