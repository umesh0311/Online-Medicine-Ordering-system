/*
 * Under development
 * OrderController will hanlde the orderLine data
 * it get the data from the view and send it to EJB for processing
 */
package com.controller;

import com.ejb.OrderLineEJB;
import com.entity.OrderLine;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Badal
 */
@Named(value = "orderLineController")
@RequestScoped
public class OrderLineController {

    @EJB
    private OrderLineEJB orderLineEJB;
    
    //private ProductEJB productEJB;
    private List<OrderLine> orderLineList;
    private OrderLine orderLine = new OrderLine();

    /**
     * Creates a new instance of OrderlineController
     */
    public OrderLineController(){
    }

    public String doCreate(){
        // TODO: use EJB to create orderline and save data into database here...
        orderLineEJB.createOrderLine(orderLine);
        return "listOrderLines.xhtml";
    }

    public String findByOrderLineId(){
        orderLine = orderLineEJB.getOrderLineById(orderLine.getId());
        return "viewOrderLine.xhtml";
    }
    
    public String findByOrderId(){
        orderLineList = orderLineEJB.searchByOrderId(orderLine.getOrderId());
        return "searchOrderLineResults.xhtml";
    }
    
    public List<OrderLine> getAllOrderLines(){
        return orderLineEJB.selectAllOrderLines();
    }

    public OrderLine getOrderLine() {
        return orderLine;
    }

    public void setOrderLine(OrderLine orderLine) {
        this.orderLine = orderLine;
    }
    
    
    public String gotoHome(){
        return "home.xhtml";
    }
    
}   // end orderline Controller
