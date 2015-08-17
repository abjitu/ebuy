package com.crossover.ebuy.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.crossover.commons.request.OrderDetails;
import com.crossover.commons.request.SalesOrder;
import com.ebuy.dao.CustomerDao;
import com.ebuy.dao.ProductDao;
import com.ebuy.dao.SalesOrderDao;
import com.ebuy.entity.CustomerEntity;
import com.ebuy.entity.ProductEntity;
import com.ebuy.entity.SalesOrderEntity;
import com.ebuy.exception.CustomException;
import com.ebuy.service.SalesOrderService;


/**
 * The Class SalesOrderServiceTest.
 */
@RunWith(MockitoJUnitRunner.class)
public class SalesOrderServiceTest {

    /** The sales order service. */
    @InjectMocks
    private SalesOrderService salesOrderService;
    
    /** The product dao. */
    @Mock
    private ProductDao productDao;
    
    /** The customer dao. */
    @Mock
    private CustomerDao customerDao;
    
    /** The sales order dao. */
    @Mock
    private SalesOrderDao salesOrderDao;

    /**
     * Creates the test.
     *
     * @throws CustomException the custom exception
     */
    @Test
    public void createTest() throws CustomException {
        CustomerEntity ce = new CustomerEntity();
        ce.setName("test");
        ce.setCode("TEST01");
        ce.setAddress("bangalore");
        ce.setPhone1("1234567890");
        ce.setPhone2("1234567891");
        ce.setStatus('A');
        ce.setCreditLimit(Double.valueOf("100.00"));
        ce.setCurrentCredit(Double.valueOf("0.00"));
        ProductEntity pe = new ProductEntity();
        pe.setDescription("test");
        pe.setCode("TEST01");
        pe.setQuantity(10);
        pe.setStatus('A');
        pe.setUnitPrice(Double.valueOf("10.00"));
        when(salesOrderDao.findByCode((String) anyObject())).thenReturn(null);
        when(customerDao.findByCode((String) anyObject())).thenReturn(ce);
        when(productDao.findByCode((String) anyObject())).thenReturn(pe);
        doNothing().when(salesOrderDao).persist((SalesOrderEntity) anyObject());

        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setCustomerCode("TEST01");
        salesOrder.setOrderNumber("TEST01");
        salesOrder.setTotalPrice(Double.valueOf("50.00"));
        OrderDetails ode = new OrderDetails();
        ode.setProductCode("TEST01");
        ode.setQuantity(5);
        ode.setTotalPrice(Double.valueOf("50.00"));
        ode.setUnitPrice(Double.valueOf("10.00"));
        List<OrderDetails> odes = new ArrayList<OrderDetails>();
        odes.add(ode);
        salesOrder.setOrderDetails(odes);

        salesOrderService.create(salesOrder);
        assertEquals(ce.getCurrentCredit(), Double.valueOf("50.00"));
        assertEquals(pe.getQuantity(), Integer.valueOf(5));

    }

    /**
     * Creates the with customer credit limit test.
     *
     * @throws CustomException the custom exception
     */
    @Test(expected = CustomException.class)
    public void createWithCustomerCreditLimitTest() throws CustomException {
        CustomerEntity ce = new CustomerEntity();
        ce.setName("test");
        ce.setCode("TEST01");
        ce.setAddress("bangalore");
        ce.setPhone1("1234567890");
        ce.setPhone2("1234567891");
        ce.setStatus('A');
        ce.setCreditLimit(Double.valueOf("100.00"));
        ce.setCurrentCredit(Double.valueOf("0.00"));
        ProductEntity pe = new ProductEntity();
        pe.setDescription("test");
        pe.setCode("TEST01");
        pe.setQuantity(10);
        pe.setStatus('A');
        pe.setUnitPrice(Double.valueOf("10.00"));
        when(salesOrderDao.findByCode((String) anyObject())).thenReturn(null);
        when(customerDao.findByCode((String) anyObject())).thenReturn(ce);
        when(productDao.findByCode((String) anyObject())).thenReturn(pe);
        doNothing().when(salesOrderDao).persist((SalesOrderEntity) anyObject());

        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setCustomerCode("TEST01");
        salesOrder.setOrderNumber("TEST01");
        salesOrder.setTotalPrice(Double.valueOf("150.00"));
        OrderDetails ode = new OrderDetails();
        ode.setProductCode("TEST01");
        ode.setQuantity(15);
        ode.setTotalPrice(Double.valueOf("150.00"));
        ode.setUnitPrice(Double.valueOf("10.00"));
        List<OrderDetails> odes = new ArrayList<OrderDetails>();
        odes.add(ode);
        salesOrder.setOrderDetails(odes);

        salesOrderService.create(salesOrder);
        assertEquals(ce.getCurrentCredit(), Double.valueOf("50.00"));
        assertEquals(pe.getQuantity(), Integer.valueOf(5));

    }
    
    /**
     * Creates the with item inventory test.
     *
     * @throws CustomException the custom exception
     */
    @Test(expected = CustomException.class)
    public void createWithItemInventoryTest() throws CustomException {
        CustomerEntity ce = new CustomerEntity();
        ce.setName("test");
        ce.setCode("TEST01");
        ce.setAddress("bangalore");
        ce.setPhone1("1234567890");
        ce.setPhone2("1234567891");
        ce.setStatus('A');
        ce.setCreditLimit(Double.valueOf("100.00"));
        ce.setCurrentCredit(Double.valueOf("0.00"));
        ProductEntity pe = new ProductEntity();
        pe.setDescription("test");
        pe.setCode("TEST01");
        pe.setQuantity(5);
        pe.setStatus('A');
        pe.setUnitPrice(Double.valueOf("10.00"));
        when(salesOrderDao.findByCode((String) anyObject())).thenReturn(null);
        when(customerDao.findByCode((String) anyObject())).thenReturn(ce);
        when(productDao.findByCode((String) anyObject())).thenReturn(pe);
        doNothing().when(salesOrderDao).persist((SalesOrderEntity) anyObject());

        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setCustomerCode("TEST01");
        salesOrder.setOrderNumber("TEST01");
        salesOrder.setTotalPrice(Double.valueOf("80.00"));
        OrderDetails ode = new OrderDetails();
        ode.setProductCode("TEST01");
        ode.setQuantity(8);
        ode.setTotalPrice(Double.valueOf("80.00"));
        ode.setUnitPrice(Double.valueOf("10.00"));
        List<OrderDetails> odes = new ArrayList<OrderDetails>();
        odes.add(ode);
        salesOrder.setOrderDetails(odes);

        salesOrderService.create(salesOrder);
        assertEquals(ce.getCurrentCredit(), Double.valueOf("50.00"));
        assertEquals(pe.getQuantity(), Integer.valueOf(5));

    }

    /**
     * Update test.
     *
     * @throws CustomException the custom exception
     */
    @Test
    public void updateTest() throws CustomException {
        CustomerEntity ce = new CustomerEntity();
        ce.setName("test");
        ce.setCode("TEST01");
        ce.setAddress("bangalore");
        ce.setPhone1("1234567890");
        ce.setPhone2("1234567891");
        ce.setStatus('A');
        ce.setCreditLimit(Double.valueOf("100.00"));
        ce.setCurrentCredit(Double.valueOf("0.00"));
        ProductEntity pe = new ProductEntity();
        pe.setDescription("test");
        pe.setCode("TEST01");
        pe.setQuantity(10);
        pe.setStatus('A');
        pe.setUnitPrice(Double.valueOf("10.00"));
        when(salesOrderDao.findByCode((String) anyObject())).thenReturn(null);
        when(customerDao.findByCode((String) anyObject())).thenReturn(ce);
        when(productDao.findByCode((String) anyObject())).thenReturn(pe);
        doNothing().when(salesOrderDao).persist((SalesOrderEntity) anyObject());

        SalesOrder salesOrder = new SalesOrder();
        salesOrder.setCustomerCode("TEST01");
        salesOrder.setOrderNumber("TEST01");
        salesOrder.setTotalPrice(Double.valueOf("50.00"));
        OrderDetails ode = new OrderDetails();
        ode.setProductCode("TEST01");
        ode.setQuantity(5);
        ode.setTotalPrice(Double.valueOf("50.00"));
        ode.setUnitPrice(Double.valueOf("10.00"));
        List<OrderDetails> odes = new ArrayList<OrderDetails>();
        odes.add(ode);
        salesOrder.setOrderDetails(odes);

        SalesOrderEntity soe = salesOrderService.create(salesOrder);
        assertEquals(ce.getCurrentCredit(), Double.valueOf("50.00"));
        assertEquals(pe.getQuantity(), Integer.valueOf(5));

        when(salesOrderDao.findByCode((String) anyObject())).thenReturn(soe);

        salesOrder = new SalesOrder();
        salesOrder.setCustomerCode("TEST01");
        salesOrder.setOrderNumber("TEST01");
        salesOrder.setTotalPrice(Double.valueOf("70.00"));
        odes = new ArrayList<OrderDetails>();
        ode = new OrderDetails();
        ode.setProductCode("TEST01");
        ode.setQuantity(5);
        ode.setTotalPrice(Double.valueOf("50.00"));
        ode.setUnitPrice(Double.valueOf("10.00"));
        odes.add(ode);
        ode = new OrderDetails();
        ode.setProductCode("TEST01");
        ode.setQuantity(2);
        ode.setTotalPrice(Double.valueOf("20.00"));
        ode.setUnitPrice(Double.valueOf("10.00"));
        odes.add(ode);
        salesOrder.setOrderDetails(odes);

        soe = salesOrderService.create(salesOrder);
        assertEquals(ce.getCurrentCredit(), Double.valueOf("70.00"));
        assertEquals(pe.getQuantity(), Integer.valueOf(3));
    }
}
