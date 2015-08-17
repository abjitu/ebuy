package com.crossover.ebuy.service;

import static org.junit.Assert.assertTrue;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.crossover.commons.request.Customer;
import com.ebuy.dao.CustomerDao;
import com.ebuy.entity.CustomerEntity;
import com.ebuy.service.CustomerService;


/**
 * The Class CustomerServiceTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceTest {

    /** The customer service. */
    @InjectMocks
    CustomerService customerService;
    
    /** The customer dao. */
    @Mock
    CustomerDao customerDao;

    /**
     * Creates the test.
     */
    @Test
    public void createTest() {
        doNothing().when(customerDao).persist((CustomerEntity) anyObject());
        when(customerDao.findByCode((String) anyObject())).thenReturn(null);
        CustomerEntity entity = new CustomerEntity();
        entity.setName("test");
        entity.setCode("TEST01");
        entity.setAddress("bangalore");
        entity.setPhone1("1234567890");
        entity.setPhone2("1234567891");
        entity.setCreditLimit(Double.valueOf("100.00"));
        Customer resp = customerService.create(entity);
        assertTrue(resp.getCurrentCredit().equals(Double.valueOf("0.0")));
    }
}
