package com.ebuy.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crossover.commons.request.OrderDetails;
import com.crossover.commons.request.SalesOrder;
import com.ebuy.dao.CustomerDao;
import com.ebuy.dao.ProductDao;
import com.ebuy.dao.SalesOrderDao;
import com.ebuy.entity.CustomerEntity;
import com.ebuy.entity.OrderDetailsEntity;
import com.ebuy.entity.ProductEntity;
import com.ebuy.entity.SalesOrderEntity;
import com.ebuy.exception.CustomException;


/**
 * The Class SalesOrderService.
 */
@Service
public class SalesOrderService {
    
    /** The Constant log. */
    private static final Logger log = LoggerFactory.getLogger(SalesOrderService.class);

    /** The sales order dao. */
    private @Autowired SalesOrderDao salesOrderDao;

    /** The customer dao. */
    private @Autowired CustomerDao customerDao;

    /** The product dao. */
    private @Autowired ProductDao productDao;

    /**
     * Creates the.
     *
     * @param salesOrder the sales order
     * @return the sales order entity
     * @throws CustomException the custom exception
     */
    @Transactional(rollbackFor = Exception.class)
    public SalesOrderEntity create(SalesOrder salesOrder) throws CustomException {
        log.debug("Inside create");
        SalesOrderEntity entity = readByCode(salesOrder.getOrderNumber());
        CustomerEntity customerEntity = customerDao.findByCode(salesOrder.getCustomerCode());
        if (null == customerEntity) {
            throw new CustomException("Customer with the code " + salesOrder.getCustomerCode() + " does not exist");
        } else {
            Double credit = customerEntity.getCurrentCredit() + salesOrder.getTotalPrice();
            if (null != entity) {
                credit = credit - entity.getTotalPrice();
            }
            if (credit.compareTo(customerEntity.getCreditLimit()) > 0) {
                throw new CustomException("Total price of sales order is beyond customer credit limit");
            } else {
                if (null != entity) {
                    log.info("Sales order already exists for this order number : {}", salesOrder.getOrderNumber());
                    convert(salesOrder, entity);
                    entity.setCustomer(customerEntity);
                    customerEntity.setCurrentCredit(credit);
                    salesOrderDao.update(entity);
                } else {
                    entity = convert(salesOrder);
                    entity.setCustomer(customerEntity);
                    customerEntity.setCurrentCredit(credit);
                    entity.setStatus('A');
                    salesOrderDao.persist(entity);
                }
            }
        }
        return entity;
    }

    /**
     * Read by code.
     *
     * @param code the code
     * @return the sales order entity
     */
    @Transactional(readOnly = true)
    private SalesOrderEntity readByCode(String code) {
        log.debug("Inside readByCode method. code : {}", code);
        return salesOrderDao.findByCode(code);
    }

    /**
     * Gets the.
     *
     * @param code the code
     * @return the list
     */
    @Transactional(readOnly = true)
    public List<SalesOrder> get(String code) {
        log.debug("Inside get method. code : {}", code);
        List<SalesOrder> result = new ArrayList<SalesOrder>();
        if (StringUtils.isBlank(code)) {
            List<SalesOrderEntity> entities = salesOrderDao.findAll();
            if (null != entities && entities.size() > 0) {
                for (SalesOrderEntity entity : entities) {
                    result.add(convert(entity));
                }
            }
        } else {
            result.add(convert(readByCode(code)));
        }
        log.debug("Exiting get method. code : {}", code);
        return result;
    }

    /**
     * Delete.
     *
     * @param code the code
     */
    @Transactional(rollbackFor = Exception.class)
    public void delete(String code) {
        SalesOrderEntity entity = salesOrderDao.findByCode(code);
        entity.setStatus('I');
    }

    /**
     * Convert.
     *
     * @param entity the entity
     * @return the sales order
     */
    private SalesOrder convert(SalesOrderEntity entity) {
        SalesOrder salesOrder = new SalesOrder();
        CustomerEntity cust = entity.getCustomer();
        salesOrder.setCustomerCode(cust.getCode());
        salesOrder.setCustomerName(cust.getName());
        salesOrder.setOrderNumber(entity.getCode());
        salesOrder.setTotalPrice(entity.getTotalPrice());
        List<OrderDetails> ods = new ArrayList<OrderDetails>();
        Iterator<OrderDetailsEntity> iter = entity.getOrderDetails().iterator();
        while (iter.hasNext()) {
            OrderDetailsEntity ode = iter.next();
            ods.add(convert(ode));
        }
        salesOrder.setOrderDetails(ods);
        return salesOrder;

    }

    /**
     * Convert.
     *
     * @param ode the ode
     * @return the order details
     */
    private OrderDetails convert(OrderDetailsEntity ode) {
        OrderDetails od = new OrderDetails();
        od.setDiscount(ode.getDiscount());
        od.setProductCode(ode.getProduct().getCode());
        od.setQuantity(ode.getQuantity());
        od.setTotalPrice(ode.getTotalPrice());
        od.setUnitPrice(ode.getUnitPrice());
        return od;
    }

    /**
     * Convert.
     *
     * @param salesOrder the sales order
     * @return the sales order entity
     * @throws CustomException the custom exception
     */
    private SalesOrderEntity convert(SalesOrder salesOrder) throws CustomException {
        SalesOrderEntity entity = new SalesOrderEntity();
        entity.setCode(salesOrder.getOrderNumber());
        entity.setTotalPrice(salesOrder.getTotalPrice());
        Set<OrderDetailsEntity> ode = new HashSet<OrderDetailsEntity>();
        Iterator<OrderDetails> iter = salesOrder.getOrderDetails().iterator();
        while (iter.hasNext()) {
            OrderDetails od = iter.next();
            ode.add(convert(od, entity));
        }
        entity.setOrderDetails(ode);
        return entity;

    }

    /**
     * Convert.
     *
     * @param salesOrder the sales order
     * @param entity the entity
     * @throws CustomException the custom exception
     */
    private void convert(SalesOrder salesOrder, SalesOrderEntity entity) throws CustomException {
        entity.setCode(salesOrder.getOrderNumber());
        entity.setTotalPrice(salesOrder.getTotalPrice());
        List<OrderDetails> newOD = salesOrder.getOrderDetails();
        if (null != entity.getOrderDetails() && entity.getOrderDetails().size() > 0) {
            Iterator<OrderDetailsEntity> iter = entity.getOrderDetails().iterator();
            while (iter.hasNext()) {
                newOD.remove(convert(iter.next()));
            }
        }
        Set<OrderDetailsEntity> ode = entity.getOrderDetails();
        Iterator<OrderDetails> iter = newOD.iterator();
        while (iter.hasNext()) {
            OrderDetails od = iter.next();
            ode.add(convert(od, entity));
        }
    }

    /**
     * Convert.
     *
     * @param od the od
     * @param order the order
     * @return the order details entity
     * @throws CustomException the custom exception
     */
    private OrderDetailsEntity convert(OrderDetails od, SalesOrderEntity order) throws CustomException {
        OrderDetailsEntity ode = new OrderDetailsEntity();
        ode.setSalesOrder(order);
        ProductEntity product = productDao.findByCode(od.getProductCode());
        ode.setProduct(product);
        ode.setDiscount(od.getDiscount());

        if (product.getQuantity() < od.getQuantity()) {
            throw new CustomException(
                    "Quantities that have been requested are less than or equal current inventory balance for product : "
                            + product.getDescription() + " is not available");
        }
        product.setQuantity(product.getQuantity() - od.getQuantity());
        log.info("Inventory for product {} : {}", product.getDescription(), product.getQuantity());
        ode.setQuantity(od.getQuantity());
        ode.setTotalPrice(od.getTotalPrice());
        ode.setUnitPrice(od.getUnitPrice());
        return ode;
    }
}
