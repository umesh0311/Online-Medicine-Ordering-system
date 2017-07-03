/*
*Under Development
 * EOrder Entity has all variables and it's setter and getter methods as well the NamedQueries
 * It handles the relationship based notataion not using the queries
 * 
 */
package com.entity;

import java.io.Serializable;
import java.util.Date;
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
    @NamedQuery(name = EOrder.FIND_ALL, query = "SELECT o FROM EOrder o"),
    @NamedQuery(name = EOrder.SEARCH, query = "SELECT o FROM EOrder o WHERE o.id LIKE ?1"),
    @NamedQuery(name = EOrder.SEARCH_BY_NAME, query = "SELECT o FROM EOrder o WHERE o.customerName LIKE ?1")
})
public class EOrder implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL = "EOrder.findAll";
    public static final String SEARCH = "EOrder.search";
    public static final String SEARCH_BY_NAME = "EOrder.searchByName";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long customerId;
    private String customerName;    // user's name
    private int quantity;
    private double price;
    private Date orderDate;
    private int orderStatus;    // 0 -pending, 1- confirmed, 2 -delivered
    
    @ManyToOne
    private Customer customer;    
    
    //@ManyToMany(targetEntity = Product.class)    
    //private List<Product> productsList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public int getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(int orderStatus) {
        this.orderStatus = orderStatus;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof EOrder)) {
            return false;
        }
        EOrder other = (EOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.Orders[ id=" + id + " ]";
    }
    
}
