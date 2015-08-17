package com.crossover.ebuy.dao;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ebuy.config.DatabaseConfig;
import com.ebuy.dao.ProductDao;
import com.ebuy.entity.ProductEntity;


/**
 * The Class ProductDaoTest.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DatabaseConfig.class})
public class ProductDaoTest  {

    /** The product dao. */
    private @Autowired ProductDao productDao;
    
    /**
     * Customer save test.
     */
    @Test
    @Transactional
    public void customerSaveTest(){
        ProductEntity entity = new ProductEntity();
        entity.setDescription("test");
        entity.setCode("TEST01");
        entity.setQuantity(10);
        entity.setStatus('A');
        entity.setUnitPrice(Double.valueOf("10.00"));
        productDao.persist(entity);
    }
    
    /**
     * Customer find by code test.
     */
    @Test
    @Transactional
    public void customerFindByCodeTest(){
        ProductEntity entity = new ProductEntity();
        entity.setDescription("test");
        entity.setCode("TEST01");
        entity.setQuantity(10);
        entity.setStatus('A');
        entity.setUnitPrice(Double.valueOf("10.00"));
        productDao.persist(entity);
        ProductEntity fromDB = productDao.findByCode("TEST01");
        assertNotNull(fromDB);
        assertTrue(entity.getCode().equals(fromDB.getCode()));
    }
    
    /**
     * Customer find all test.
     */
    @Test
    @Transactional
    public void customerFindAllTest(){
        ProductEntity entity = new ProductEntity();
        entity.setDescription("test");
        entity.setCode("TEST01");
        entity.setQuantity(10);
        entity.setStatus('A');
        entity.setUnitPrice(Double.valueOf("10.00"));
        productDao.persist(entity);
        List<ProductEntity> fromDB = productDao.findAll();
        assertNotNull(fromDB);
        assertTrue(fromDB.contains(entity));
    }
    
    /**
     * Customer update test.
     */
    @Test
    @Transactional
    public void customerUpdateTest(){
        ProductEntity entity = new ProductEntity();
        entity.setDescription("test");
        entity.setCode("TEST01");
        entity.setQuantity(10);
        entity.setStatus('A');
        entity.setUnitPrice(Double.valueOf("10.00"));
        productDao.persist(entity);
        entity.setStatus('I');
        productDao.update(entity);
        ProductEntity fromDB = productDao.findByCode("TEST01");
        assertNull(fromDB);
    }

}
