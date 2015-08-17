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

import com.crossover.commons.request.Customer;
import com.crossover.commons.response.CustomerResponse;
import com.crossover.commons.response.Response;
import com.crossover.commons.response.Response.ResultType;
import com.ebuy.entity.CustomerEntity;
import com.ebuy.service.CustomerService;
import com.ebuy.util.BeanUtils;


/**
 * The Class CustomerController.
 */
@RestController
@RequestMapping(value = "/customer", produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class CustomerController {
    
    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(CustomerController.class);

    /** The customer service. */
    private @Autowired CustomerService customerService;

    /**
     * Gets the.
     *
     * @param code the code
     * @return the list
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Customer> get(@RequestParam(value = "code", required = false) String code) {
        log.info("Inside get");
        List<Customer> list = customerService.get(code);
        log.info("Exiting get");
        return list;
    }

    /**
     * Creates the.
     *
     * @param customer the customer
     * @return the customer response
     */
    @RequestMapping(method = RequestMethod.POST)
    public CustomerResponse create(@RequestBody CustomerEntity customer) {
        CustomerResponse resp = null;
        log.info("Inside create");
        try {
            Customer cust = customerService.create(customer);
            resp = new CustomerResponse(Response.get(ResultType.SUCCESS), cust);
        } catch (Exception e) {
            log.error("Error while creating customer : {}", e.getMessage(), e);
            resp = new CustomerResponse(Response.get(ResultType.FAILURE, e.getMessage()), BeanUtils.convert(customer));
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
        customerService.delete(code);
        log.info("Exiting delete");
    }

}
