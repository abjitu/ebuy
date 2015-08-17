package com.ebuy.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;


/**
 * The Class OrderDetailsEntity.
 */
@Entity
@Table(name = "order_details")
@DynamicUpdate
public class OrderDetailsEntity extends AbstractEntity {

    /** The product. */
    private ProductEntity product;
    
    /** The sales order. */
    private SalesOrderEntity salesOrder;
    
    /** The quantity. */
    private Integer quantity;
    
    /** The unit price. */
    private Double unitPrice;
    
    /** The discount. */
    private Double discount;
    
    /** The total price. */
    private Double totalPrice;

    /**
     * Gets the product.
     *
     * @return the product
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    public ProductEntity getProduct() {
        return this.product;
    }

    /**
     * Sets the product.
     *
     * @param product the new product
     */
    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    /**
     * Gets the sales order.
     *
     * @return the sales order
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sales_order_id", nullable = false)
    public SalesOrderEntity getSalesOrder() {
        return this.salesOrder;
    }

    /**
     * Sets the sales order.
     *
     * @param salesOrder the new sales order
     */
    public void setSalesOrder(SalesOrderEntity salesOrder) {
        this.salesOrder = salesOrder;
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
     * Gets the unit price.
     *
     * @return the unit price
     */
    @Column(name = "unit_price", nullable = false, length = 45)
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
     * Gets the discount.
     *
     * @return the discount
     */
    @Column(name = "discount", length = 45)
    public Double getDiscount() {
        return this.discount;
    }

    /**
     * Sets the discount.
     *
     * @param discount the new discount
     */
    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    /**
     * Gets the total price.
     *
     * @return the total price
     */
    @Column(name = "total_price", nullable = false, length = 45)
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
    
    

}
