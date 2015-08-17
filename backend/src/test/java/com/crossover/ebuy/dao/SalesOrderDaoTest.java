package com.crossover.ebuy.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ebuy.config.DatabaseConfig;
import com.ebuy.dao.CustomerDao;
import com.ebuy.dao.ProductDao;
import com.ebuy.dao.SalesOrderDao;
import com.ebuy.entity.CustomerEntity;
import com.ebuy.entity.OrderDetailsEntity;
import com.ebuy.entity.ProductEntity;
import com.ebuy.entity.SalesOrderEntity;


/**
 * The Class SalesOrderDaoTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DatabaseConfig.class })
public class SalesOrderDaoTest {

    /** The sales order dao. */
    private @Autowired SalesOrderDao salesOrderDao;
    
    /** The customer dao. */
    private @Autowired CustomerDao customerDao;
    
    /** The product dao. */
    private @Autowired ProductDao productDao;

    /**
     * Customer save test.
     */
    @Test
    @Transactional
    public void customerSaveTest() {
        CustomerEntity ce = new CustomerEntity();
        ce.setName("test");
        ce.setCode("TEST01");
        ce.setAddress("bangalore");
        ce.setPhone1("1234567890");
        ce.setPhone2("1234567891");
        ce.setStatus('A');
        ce.setCreditLimit(Double.valueOf("100.00"));
        customerDao.persist(ce);
        CustomerEntity cFromDB = customerDao.findByCode("TEST01");

        ProductEntity pe = new ProductEntity();
        pe.setDescription("test");
        pe.setCode("TEST01");
        pe.setQuantity(10);
        pe.setStatus('A');
        pe.setUnitPrice(Double.valueOf("10.00"));
        productDao.persist(pe);
        ProductEntity peFromDB = productDao.findByCode("TEST01");

        SalesOrderEntity entity = new SalesOrderEntity();
        entity.setCustomer(cFromDB);
        entity.setCode("TEST01");
        entity.setStatus('A');
        entity.setTotalPrice(Double.valueOf("50.00"));
        OrderDetailsEntity ode = new OrderDetailsEntity();
        ode.setProduct(peFromDB);
        ode.setSalesOrder(entity);
        ode.setQuantity(5);
        ode.setTotalPrice(Double.valueOf("50.00"));
        ode.setUnitPrice(Double.valueOf("10.00"));
        Set<OrderDetailsEntity> odes = new HashSet<OrderDetailsEntity>();
        odes.add(ode);
        entity.setOrderDetails(odes);
        salesOrderDao.persist(entity);

        SalesOrderEntity aoeFromDB = salesOrderDao.findByCode("TEST01");
        assertNotNull(aoeFromDB);
        assertTrue(entity.getCode().equals(aoeFromDB.getCode()));
    }

}
