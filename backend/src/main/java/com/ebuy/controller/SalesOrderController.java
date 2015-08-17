package com.ebuy.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crossover.commons.request.SalesOrder;
import com.crossover.commons.response.Response;
import com.crossover.commons.response.Response.ResultType;
import com.crossover.commons.response.SalesOrderResponse;
import com.ebuy.service.SalesOrderService;


/**
 * The Class SalesOrderController.
 */
@RestController
@RequestMapping(value = "/sales", produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class SalesOrderController {
    
    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(SalesOrderController.class);

    /** The sales order service. */
    private @Autowired SalesOrderService salesOrderService;

    /**
     * Gets the.
     *
     * @param code the code
     * @return the list
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<SalesOrder> get(@RequestParam(value = "code", required = false) String code) {
        log.info("Inside get");
        List<SalesOrder> list = salesOrderService.get(code);
        log.info("Exiting get");
        return list;
    }

    /**
     * Creates the.
     *
     * @param salesOrder the sales order
     * @return the sales order response
     */
    @RequestMapping(method = RequestMethod.POST)
    public SalesOrderResponse create(@RequestBody SalesOrder salesOrder) {
        log.info("Inside create");
        SalesOrderResponse resp = null;
        try {
            salesOrderService.create(salesOrder);
            resp = new SalesOrderResponse(Response.get(ResultType.SUCCESS), salesOrder);
        } catch (Exception e) {
            log.error("Error while creating customer : {}", e.getMessage(), e);
            resp = new SalesOrderResponse(Response.get(ResultType.FAILURE, e.getMessage()), salesOrder);
        }
        log.info("Exiting create");
        return resp;
    }

    /**
     * Delete.
     *
     * @param code the code
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public void delete(@RequestParam("code") String code) {
        log.info("Inside delete");
        salesOrderService.delete(code);
        log.info("Outside delete");
    }

}
