package com.crossover.ebuy.service;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.crossover.commons.request.Product;
import com.ebuy.dao.ProductDao;
import com.ebuy.entity.ProductEntity;
import com.ebuy.service.ProductService;


/**
 * The Class ProductServiceTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class ProductServiceTest {

    /** The product service. */
    @InjectMocks
    ProductService productService;
    
    /** The product dao. */
    @Mock
    ProductDao productDao;

    /**
     * Creates the test.
     */
    @Test
    public void createTest() {
        doNothing().when(productDao).persist((ProductEntity) anyObject());
        when(productDao.findByCode((String) anyObject())).thenReturn(null);
        ProductEntity entity = new ProductEntity();
        entity.setDescription("test");
        entity.setCode("TEST01");
        entity.setQuantity(10);
        entity.setUnitPrice(Double.valueOf("10.00"));
        Product resp = productService.create(entity);
        assertNotNull(resp);
    }
}
