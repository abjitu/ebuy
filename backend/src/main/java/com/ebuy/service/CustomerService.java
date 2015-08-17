package com.ebuy.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.commons.request.Customer;
import com.ebuy.dao.CustomerDao;
import com.ebuy.entity.CustomerEntity;
import com.ebuy.util.BeanUtils;


/**
 * The Class CustomerService.
 */
@Service
public class CustomerService {
    
    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(CustomerService.class);

    /** The customer dao. */
    private @Autowired CustomerDao customerDao;

    /**
     * Creates the.
     *
     * @param entity the entity
     * @return the customer
     */
    @Transactional(rollbackFor = Exception.class)
    public Customer create(CustomerEntity entity) {
        log.debug("Inside create method");
        CustomerEntity customer = readByCode(entity.getCode());
        if (null != customer) {
            log.info("Customer already exists. Hence updating");
            customer.setName(entity.getName());
            customer.setAddress(entity.getAddress());
            customer.setPhone1(entity.getPhone1());
            customer.setPhone2(entity.getPhone2());
            customer.setCreditLimit(entity.getCreditLimit());
            customerDao.update(customer);
            entity.setCurrentCredit(customer.getCurrentCredit());
        } else {
            log.info("Customer doesnot exists. Hence creating new");
            if (null == entity.getCurrentCredit()) {
                entity.setStatus('A');
                entity.setCurrentCredit(Double.valueOf("0.0"));
            }
            customerDao.persist(entity);
        }
        return BeanUtils.convert(entity);
    }

    /**
     * Read by code.
     *
     * @param code the code
     * @return the customer entity
     */
    @Transactional(readOnly = true)
    private CustomerEntity readByCode(String code) {
        log.debug("Inside readByCode method. code : {}", code);
        return customerDao.findByCode(code);
    }

    /**
     * Gets the.
     *
     * @param code the code
     * @return the list
     */
    @Transactional(readOnly = true)
    public List<Customer> get(String code) {
        log.debug("Inside get method. code : {}", code);
        List<Customer> result = new ArrayList<Customer>();
        if (StringUtils.isBlank(code)) {
            List<CustomerEntity> customers = customerDao.findAll();
            if (null != customers && customers.size() > 0) {
                for (CustomerEntity entity : customers) {
                    result.add(BeanUtils.convert(entity));
                }
            }
        } else {
            result.add(BeanUtils.convert(readByCode(code)));
        }
        return result;
    }

    /**
     * Delete.
     *
     * @param code the code
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(String code) {
        CustomerEntity entity = customerDao.findByCode(code);
        entity.setStatus('I');
    }
}
