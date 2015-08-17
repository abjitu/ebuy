package com.ebuy.util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.crossover.commons.request.Customer;
import com.crossover.commons.request.OrderDetails;
import com.crossover.commons.request.Product;
import com.crossover.commons.request.SalesOrder;
import com.ebuy.entity.CustomerEntity;
import com.ebuy.entity.OrderDetailsEntity;
import com.ebuy.entity.ProductEntity;
import com.ebuy.entity.SalesOrderEntity;


/**
 * The Class BeanUtils.
 */
public class BeanUtils {
    
    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(BeanUtils.class);

    /**
     * Convert.
     *
     * @param entity the entity
     * @return the customer
     */
    public static Customer convert(CustomerEntity entity) {

        Customer customer = new Customer();
        customer.setName(entity.getName());
        customer.setCode(entity.getCode());
        customer.setAddress(entity.getAddress());
        customer.setPhone1(entity.getPhone1());
        customer.setPhone2(entity.getPhone2());
        customer.setCreditLimit(entity.getCreditLimit());
        customer.setCurrentCredit(entity.getCurrentCredit());
        return customer;

    }

    /**
     * Convert.
     *
     * @param customer the customer
     * @return the customer entity
     */
    public static CustomerEntity convert(Customer customer) {
        CustomerEntity entity = new CustomerEntity();
        entity.setName(customer.getName());
        entity.setCode(customer.getCode());
        entity.setAddress(customer.getAddress());
        entity.setPhone1(customer.getPhone1());
        entity.setPhone2(customer.getPhone2());
        entity.setCreditLimit(customer.getCreditLimit());
        entity.setCurrentCredit(customer.getCurrentCredit());
        return entity;
    }

    /**
     * Convert.
     *
     * @param entity the entity
     * @return the product
     */
    public static Product convert(ProductEntity entity) {
        Product product = new Product();
        product.setDescription(entity.getDescription());
        product.setCode(entity.getCode());
        product.setQuantity(entity.getQuantity());
        product.setUnitPrice(entity.getUnitPrice());
        return product;

    }

    /**
     * Convert.
     *
     * @param product the product
     * @return the product entity
     */
    public static ProductEntity convert(Product product) {
        ProductEntity entity = new ProductEntity();
        entity.setDescription(product.getDescription());
        entity.setCode(product.getCode());
        entity.setQuantity(product.getQuantity());
        entity.setUnitPrice(product.getUnitPrice());

        return entity;
    }

}
