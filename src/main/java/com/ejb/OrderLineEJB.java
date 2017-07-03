/*
 * Under Developemnt
 * OrderLineEJB will  handle all oderLine functions
 * it get the data from the Controller and workout with database using EntityManager
 * EntityManager talk with Admin entity
 */
package com.ejb;

import com.entity.OrderLine;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Badal
 */
@Stateless
public class OrderLineEJB {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "MyPU")
    private EntityManager em;
    
    // get all orderLines
    public List<OrderLine> selectAllOrderLines(){        
        Query query = em.createNamedQuery(OrderLine.FIND_ALL, OrderLine.class);
        return query.getResultList();
    }
    
    public List<OrderLine> searchById(long id){        
        Query query = em.createNamedQuery(OrderLine.SEARCH, OrderLine.class);
        query.setParameter(1, "%" + id +  "%");
        return query.getResultList();
    }
    
    public List<OrderLine> searchByOrderId(int orderId){        
        Query query = em.createNamedQuery(OrderLine.SEARCH_BY_ORDERID, OrderLine.class);
        query.setParameter(1, "%" + orderId +  "%");
        return query.getResultList();
    }
    
    public void createOrderLine(OrderLine obj){
        em.persist(obj);
    }
    
    public void updateOrderLine(OrderLine obj){
        em.merge(obj);
    }
    
    public void deleteOrderLine(long id){
        em.remove(em.find(OrderLine.class, id));
    }
    
    public OrderLine getOrderLineById(Long id){
        return em.find(OrderLine.class, id);
    }
}

