/*
 * Under development
 * OrderController will hanlde the order data
 * it get the data from the view and send it to EJB for processing
 */
package com.controller;

import com.ejb.CustomerEJB;
import com.ejb.NonPrescribedMedicineEJB;
import com.ejb.OrdersEJB;
import com.entity.Customer;
import com.entity.EOrder;
import com.entity.NonPrescribedMedicine;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Badal
 */
@Named(value = "orderController")
@RequestScoped
public class OrderController {

    @EJB
    private OrdersEJB orderEJB;
    private CustomerEJB customerEJB;
    private NonPrescribedMedicineEJB nonPrescribedMedicineEJB;
    private List<EOrder> orders;
    private EOrder order = new EOrder();

    /**
     * Creates a new instance of CustomerController
     */
    public OrderController(){
    }

    public String doCreate(){
        // TODO: use EJB to create customer and save data into database here...
        orderEJB.createOrder(order);
        return "listOrders.xhtml";
    }

    public String doFindById(){
        order = orderEJB.getOrderById(order.getId());        
        return "viewOrder.xhtml";
    }
    
    public String doSearchByName(){
        orders = orderEJB.searchByName(order.getCustomerName());
        return "searchCustomerResults.xhtml";
    }
    
    public List<EOrder> getAllOrders(){
        return orderEJB.selectAllOrders();
    }
    
    public List<Customer> getAllCustomers(){
        return customerEJB.selectAll();
    }
    
    public int searchByName(String customerName){
        System.out.println("Customer name : "+customerName);
        orders = orderEJB.searchByName(customerName);
        System.out.println("Order size : "+orders.size());
        return orders.size();
    }
    
    public List<NonPrescribedMedicine> getAllProducts(){
        return nonPrescribedMedicineEJB.selectAll();
    }
    
    public EOrder getOrder(){
        return order;
    }

    public List<EOrder> getOrders(){
        return orders;
    }

    
}   // end CustomerController