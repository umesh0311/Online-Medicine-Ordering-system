/*
 * CustomerEJB  handle create account, login and check Customer profile, update profile
 * it get the data from the Controller and workout with database using EntityManager
 * EntityManager talk with Customer entity
 */
package com.ejb;

import com.entity.Customer;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author Badal
 */
@Stateless
@LocalBean
public class CustomerEJB {
    @PersistenceContext(unitName = "MyPU")
    private EntityManager em;
    Logger log = Logger.getLogger(this.getClass().getName());
    
    // get all customers
    public List<Customer> selectAll(){        
        Query query = em.createNamedQuery(Customer.FIND_ALL, Customer.class);
        return query.getResultList();
    }
    
     // get customer's profile
    public List<Customer> selectUserProfile(String Email){        
        Query query = em.createNamedQuery(Customer.FIND_CUSTOMER, Customer.class);
        query.setParameter(1,Email);
        return query.getResultList();
    }
    
     // get username and password and compare with entered username and password
    public List<Customer> login(String username, String password){        
        Query query = em.createNamedQuery(Customer.LOGIN_VERIFICATION, Customer.class);
        query.setParameter(1,username);
        query.setParameter(2,password);

        return query.getResultList();
    }
    
     // update customer's profile
    public List<Customer> updateCustomer(String firstName , String lastName,String medicareNumber,String stateName,String city,String postCode,String address,Date dateOfBirth,String phone,String password,String email){        
        Query query = em.createNamedQuery(Customer.UPDATE_CUSTOMER, Customer.class);
        query.setParameter(1,firstName);
        query.setParameter(2,lastName);
        query.setParameter(3,medicareNumber);
        query.setParameter(4,stateName);
        query.setParameter(5,city);
        query.setParameter(6,dateOfBirth);
        query.setParameter(7,postCode);
        query.setParameter(8,address);
        query.setParameter(9,phone);
        query.setParameter(10,password);
        query.setParameter(11,email);
        query.executeUpdate();
        
        return null;
    }
   
    //create a customer's account
    public void create(Customer obj){
        em.persist(obj); // Error message for integrity constraint violation
    }
   
    // not in use - for future
    public void delete(long id){        
        em.remove(em.find(Customer.class, id));        
    }
    
}
