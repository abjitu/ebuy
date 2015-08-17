package com.ebuy.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;


/**
 * The Class CustomerEntity.
 */
@NamedQueries({
    @NamedQuery(name = "findCustomerByCode",
            query = "from CustomerEntity where code=:code and status = 'A'"),
    @NamedQuery(name = "findCustomers", query = "from CustomerEntity where status = 'A' order by id desc") })
@Entity
@Table(name = "customer")
@DynamicUpdate
public class CustomerEntity extends AbstractEntity {

    /** The name. */
    private String name;
    
    /** The address. */
    private String address;
    
    /** The phone1. */
    private String phone1;
    
    /** The phone2. */
    private String phone2;
    
    /** The credit limit. */
    private Double creditLimit;
    
    /** The current credit. */
    private Double currentCredit;
    
    /** The code. */
    private String code;
    
    /** The status. */
    private char status;
    
    /** The sales orders. */
    private Set<SalesOrderEntity> salesOrders = new HashSet<SalesOrderEntity>(0);

    /**
     * Gets the name.
     *
     * @return the name
     */
    @Column(name = "name", nullable = false, length = 256)
    public String getName() {
        return this.name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the address.
     *
     * @return the address
     */
    @Column(name = "address", nullable = false, length = 512)
    public String getAddress() {
        return this.address;
    }

    /**
     * Sets the address.
     *
     * @param address the new address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Gets the phone1.
     *
     * @return the phone1
     */
    @Column(name = "phone1", nullable = false, length = 16)
    public String getPhone1() {
        return this.phone1;
    }

    /**
     * Sets the phone1.
     *
     * @param phone1 the new phone1
     */
    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    /**
     * Gets the phone2.
     *
     * @return the phone2
     */
    @Column(name = "phone2", length = 16)
    public String getPhone2() {
        return this.phone2;
    }

    /**
     * Sets the phone2.
     *
     * @param phone2 the new phone2
     */
    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    /**
     * Gets the credit limit.
     *
     * @return the credit limit
     */
    @Column(name = "credit_limit", length = 45)
    public Double getCreditLimit() {
        return this.creditLimit;
    }

    /**
     * Sets the credit limit.
     *
     * @param creditLimit the new credit limit
     */
    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    /**
     * Gets the current credit.
     *
     * @return the current credit
     */
    @Column(name = "current_credit", length = 45)
    public Double getCurrentCredit() {
        return this.currentCredit;
    }

    /**
     * Sets the current credit.
     *
     * @param currentCredit the new current credit
     */
    public void setCurrentCredit(Double currentCredit) {
        this.currentCredit = currentCredit;
    }

    /**
     * Gets the code.
     *
     * @return the code
     */
    @Column(name = "code", nullable = false, length = 45)
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
     * Gets the sales orders.
     *
     * @return the sales orders
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "customer")
    public Set<SalesOrderEntity> getSalesOrders() {
        return this.salesOrders;
    }

    /**
     * Sets the sales orders.
     *
     * @param salesOrders the new sales orders
     */
    public void setSalesOrders(Set<SalesOrderEntity> salesOrders) {
        this.salesOrders = salesOrders;
    }

}
