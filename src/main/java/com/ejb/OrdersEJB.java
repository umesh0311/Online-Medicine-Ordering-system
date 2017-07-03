/*
 * Under Developemnt
 * OrderLineEJB will  handle all Order functions
 * it get the data from the Controller and workout with database using EntityManager
 * EntityManager talk with Admin entity
 */
package com.ejb;

import com.entity.EOrder;
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
public class OrdersEJB {

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    @PersistenceContext(unitName = "MyPU")
    private EntityManager em;
    
    // get all customers
    public List<EOrder> selectAllOrders(){        
        Query query = em.createNamedQuery(EOrder.FIND_ALL, EOrder.class);
        return query.getResultList();
    }
    
    public List<EOrder> searchById(long id){        
        Query query = em.createNamedQuery(EOrder.SEARCH, EOrder.class);
        query.setParameter(1, "%" + id +  "%");
        return query.getResultList();
    }
    
    public List<EOrder> searchByName(String name){        
        Query query = em.createNamedQuery(EOrder.SEARCH_BY_NAME, EOrder.class);
        query.setParameter(1, "%" + name +  "%");
        return query.getResultList();
    }
    
    public void createOrder(EOrder obj){
        em.persist(obj);
    }
    
    public void updateOrder(EOrder obj){
        em.merge(obj);
    }
    
    public void deleteOrder(long id){
        em.remove(em.find(EOrder.class, id));
    }
    
    public EOrder getOrderById(Long id){
        return em.find(EOrder.class, id);
    }
}
