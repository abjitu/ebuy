package com.crossover.commons.response;

import com.crossover.commons.request.SalesOrder;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SalesOrderResponse {

    private final Response response;

    private final SalesOrder salesOrder;

    public SalesOrderResponse() {
        this.response = null;
        this.salesOrder = null;
    }

    public SalesOrderResponse(Response response, SalesOrder salesOrder) {
        super();
        this.response = response;
        this.salesOrder = salesOrder;
    }

    public Response getResponse() {
        return response;
    }

    public SalesOrder getSalesOrder() {
        return salesOrder;
    }

}
