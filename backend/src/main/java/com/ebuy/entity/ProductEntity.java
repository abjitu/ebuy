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
 * The Class ProductEntity.
 */
@NamedQueries({
    @NamedQuery(name = "findProductByCode",
            query = "from ProductEntity where code=:code and status = 'A'"),
    @NamedQuery(name = "findProducts", query = "from ProductEntity where status = 'A' order by id desc") })
@Entity
@Table(name = "product")
@DynamicUpdate
public class ProductEntity extends AbstractEntity {

    /** The code. */
    private String code;
    
    /** The description. */
    private String description;
    
    /** The unit price. */
    private Double unitPrice;
    
    /** The quantity. */
    private Integer quantity;
    
    /** The status. */
    private char status;
    
    /** The order detailses. */
    private Set<OrderDetailsEntity> orderDetailses = new HashSet<OrderDetailsEntity>(0);

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
     * Gets the description.
     *
     * @return the description
     */
    @Column(name = "description", length = 256)
    public String getDescription() {
        return this.description;
    }

    /**
     * Sets the description.
     *
     * @param description the new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets the unit price.
     *
     * @return the unit price
     */
    @Column(name = "unit_price", nullable = false)
    public Double getUnitPrice() {
        return this.unitPrice;
    }

    /**
     * Sets the unit price.
     *
     * @param unitPrice the new unit price
     */
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * Gets the quantity.
     *
     * @return the quantity
     */
    @Column(name = "quantity", nullable = false)
    public Integer getQuantity() {
        return this.quantity;
    }

    /**
     * Sets the quantity.
     *
     * @param quantity the new quantity
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
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
     * Gets the order detailses.
     *
     * @return the order detailses
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "product")
    public Set<OrderDetailsEntity> getOrderDetailses() {
        return this.orderDetailses;
    }

    /**
     * Sets the order detailses.
     *
     * @param orderDetailses the new order detailses
     */
    public void setOrderDetailses(Set<OrderDetailsEntity> orderDetailses) {
        this.orderDetailses = orderDetailses;
    }

}
