package com.crossover.ebuy.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ebuy.config.DatabaseConfig;
import com.ebuy.dao.CustomerDao;
import com.ebuy.entity.CustomerEntity;

import static org.junit.Assert.*;


/**
 * The Class CustomerDaoTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DatabaseConfig.class})
public class CustomerDaoTest  {

    /** The customer dao. */
    private @Autowired CustomerDao customerDao;
    
    /**
     * Customer save test.
     */
    @Test
    @Transactional
    public void customerSaveTest(){
        CustomerEntity entity = new CustomerEntity();
        entity.setName("test");
        entity.setCode("TEST01");
        entity.setAddress("bangalore");
        entity.setPhone1("1234567890");
        entity.setPhone2("1234567891");
        entity.setStatus('A');
        entity.setCreditLimit(Double.valueOf("100.00"));
        customerDao.persist(entity);
    }
    
    /**
     * Customer find by code test.
     */
    @Test
    @Transactional
    public void customerFindByCodeTest(){
        CustomerEntity entity = new CustomerEntity();
        entity.setName("test");
        entity.setCode("TEST01");
        entity.setAddress("bangalore");
        entity.setPhone1("1234567890");
        entity.setPhone2("1234567891");
        entity.setStatus('A');
        entity.setCreditLimit(Double.valueOf("100.00"));
        customerDao.persist(entity);
        CustomerEntity fromDB = customerDao.findByCode("TEST01");
        assertNotNull(fromDB);
        assertTrue(entity.getCode().equals(fromDB.getCode()));
    }
    
    /**
     * Customer find all test.
     */
    @Test
    @Transactional
    public void customerFindAllTest(){
        CustomerEntity entity = new CustomerEntity();
        entity.setName("test");
        entity.setCode("TEST01");
        entity.setAddress("bangalore");
        entity.setPhone1("1234567890");
        entity.setPhone2("1234567891");
        entity.setStatus('A');
        entity.setCreditLimit(Double.valueOf("100.00"));
        customerDao.persist(entity);
        List<CustomerEntity> fromDB = customerDao.findAll();
        assertNotNull(fromDB);
        assertTrue(fromDB.contains(entity));
    }
    
    /**
     * Customer update test.
     */
    @Test
    @Transactional
    public void customerUpdateTest(){
        CustomerEntity entity = new CustomerEntity();
        entity.setName("test");
        entity.setCode("TEST01");
        entity.setAddress("bangalore");
        entity.setPhone1("1234567890");
        entity.setPhone2("1234567891");
        entity.setStatus('A');
        entity.setCreditLimit(Double.valueOf("100.00"));
        customerDao.persist(entity);
        entity.setStatus('I');
        customerDao.update(entity);
        CustomerEntity fromDB = customerDao.findByCode("TEST01");
        assertNull(fromDB);
    }

}
