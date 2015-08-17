package com.ebuy.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.ebuy.entity.CustomerEntity;


/**
 * The Class CustomerDao.
 */
@Repository
public class CustomerDao extends BaseDao<CustomerEntity, Integer> {
    
    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(CustomerDao.class);

    /**
     * Instantiates a new customer dao.
     */
    public CustomerDao() {
        super(CustomerEntity.class);
    }

    /**
     * Find by code.
     *
     * @param code the code
     * @return the customer entity
     */
    public CustomerEntity findByCode(String code) {
        log.debug("Inside findByCode. code {}", code);
        CustomerEntity entity = null;
        Map<String, Object> queryParams = new HashMap<String, Object>();
        queryParams.put("code", code);
        List<CustomerEntity> customers = findByNamedQuery("findCustomerByCode", queryParams);
        if (null != customers && customers.size() == 1) {
            entity = customers.get(0);
        }
        log.debug("Exiting findByCode. code {}", code);
        return entity;
    }

    /* (non-Javadoc)
     * @see com.crossover.ebuy.dao.BaseDao#findAll()
     */
    @Override
    public List<CustomerEntity> findAll() {
        log.debug("Inside findAll");
        return findByNamedQuery("findCustomers");
    }
}
