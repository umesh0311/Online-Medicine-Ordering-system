/*
*Under Development
 * OrderLine Entity has all variables and it's setter and getter methods as well the NamedQueries
 * It handles the relationship based notataion not using the queries
 * 
 */
package com.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Badal
 */
@Entity
@NamedQueries({
    @NamedQuery(name = OrderLine.FIND_ALL, query = "SELECT o FROM OrderLine o"),
    @NamedQuery(name = OrderLine.SEARCH, query = "SELECT o FROM OrderLine o WHERE o.id LIKE ?1"),
    @NamedQuery(name = OrderLine.SEARCH_BY_ORDERID, query = "SELECT o FROM OrderLine o WHERE o.orderId LIKE ?1")
})
public class OrderLine implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "OrderLine.findAll";
    public static final String SEARCH = "OrderLine.search";
    public static final String SEARCH_BY_ORDERID = "OrderLine.searchByOrderId";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int orderId;
    private int nonPrescrptionMedicineId;  
    private int quantity;
    private double price;
    private double originalPrice;
    
    
    @ManyToOne
    private EOrder order;    
    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }
    
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getNonPrescrptionMedicineId() {
        return nonPrescrptionMedicineId;
    }

    public void setNonPrescrptionMedicineId(int nonPrescrptionMedicineId) {
        this.nonPrescrptionMedicineId = nonPrescrptionMedicineId;
    }

    public double getOriginalPrice() {
        return originalPrice;
    }

    public void setOriginalPrice(double originalPrice) {
        this.originalPrice = originalPrice;
    }

    public EOrder getOrder() {
        return order;
    }

    public void setOrder(EOrder order) {
        this.order = order;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /*
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderLine)) {
            return false;
        }
        OrderLine other = (OrderLine) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Orders[ id=" + id + " ]";
    } */
    
}

