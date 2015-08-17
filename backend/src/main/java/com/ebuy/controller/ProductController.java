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

import com.crossover.commons.request.Product;
import com.crossover.commons.response.ProductResponse;
import com.crossover.commons.response.Response;
import com.crossover.commons.response.Response.ResultType;
import com.ebuy.entity.ProductEntity;
import com.ebuy.service.ProductService;
import com.ebuy.util.BeanUtils;


/**
 * The Class ProductController.
 */
@RestController
@RequestMapping(value = "/product", produces = MediaType.APPLICATION_JSON_VALUE,
        consumes = MediaType.APPLICATION_JSON_VALUE)
public class ProductController {
    
    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    /** The product service. */
    private @Autowired ProductService productService;

    /**
     * Gets the.
     *
     * @param code the code
     * @return the list
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Product> get(@RequestParam(value = "code", required = false) String code) {
        log.info("Inside get");
        List<Product> list = productService.get(code);
        log.info("Exiting get");
        return list;
    }

    /**
     * Creates the.
     *
     * @param product the product
     * @return the product response
     */
    @RequestMapping(method = RequestMethod.POST)
    public ProductResponse create(@RequestBody ProductEntity product) {
        ProductResponse resp = null;
        log.info("Inside create");
        try {
            Product prod = productService.create(product);
            resp = new ProductResponse(Response.get(ResultType.SUCCESS), prod);
        } catch (Exception e) {
            log.error("Error while creating Product : {}", e.getMessage(), e);
            resp = new ProductResponse(Response.get(ResultType.FAILURE, e.getMessage()), BeanUtils.convert(product));
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
        productService.delete(code);
        log.info("Exiting delete");
    }

}
