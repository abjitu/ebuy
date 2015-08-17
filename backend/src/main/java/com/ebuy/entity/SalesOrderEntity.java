package com.ebuy.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicUpdate;


/**
 * The Class SalesOrderEntity.
 */
@NamedQueries({
        @NamedQuery(name = "findSalesOrderByCode",
                query = "from SalesOrderEntity where code=:code and status = 'A'"),
        @NamedQuery(name = "findSalesOrders", query = "from SalesOrderEntity where status = 'A' order by id desc") })
@Entity
@Table(name = "sales_order", uniqueConstraints = @UniqueConstraint(columnNames = "code"))
@DynamicUpdate
public class SalesOrderEntity extends AbstractEntity {

    /** The customer. */
    private CustomerEntity customer;
    
    /** The code. */
    private String code;
    
    /** The total price. */
    private Double totalPrice;
    
    /** The status. */
    private char status;
    
    /** The order details. */
    private Set<OrderDetailsEntity> orderDetails = new HashSet<OrderDetailsEntity>(0);

    /**
     * Gets the customer.
     *
     * @return the customer
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    public CustomerEntity getCustomer() {
        return this.customer;
    }

    /**
     * Sets the customer.
     *
     * @param customer the new customer
     */
    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    @Column(name = "code", unique = true, nullable = false, length = 45)
    public String getCode() {
        return this.code;
    }

    /**
     * Sets the code.
     *
     * @param code the new code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Gets the total price.
     *
     * @return the total price
     */
    @Column(name = "total_price")
    public Double getTotalPrice() {
        return this.totalPrice;
    }

    /**
     * Sets the total price.
     *
     * @param totalPrice the new total price
     */
    public void setTotalPrice(Double totalPrice) {
        this.totalPrice = totalPrice;
    }

    /**
     * Gets the status.
     *
     * @return the status
     */
    @Column(name = "status", nullable = false, length = 1)
    public char getStatus() {
        return this.status;
    }

    /**
     * Sets the status.
     *
     * @param status the new status
     */
    public void setStatus(char status) {
        this.status = status;
    }

    /**
     * Gets the order details.
     *
     * @return the order details
     */
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "salesOrder")
    @Cascade(value = { CascadeType.ALL })
    public Set<OrderDetailsEntity> getOrderDetails() {
        return orderDetails;
    }

    /**
     * Sets the order details.
     *
     * @param orderDetails the new order details
     */
    public void setOrderDetails(Set<OrderDetailsEntity> orderDetails) {
        this.orderDetails = orderDetails;
    }

}
