/*
 * AdminEJB  handle create admin account, manage login, find all admin and find admin profile process using EntityManager
 * it get the data from the Controller and workout with database using EntityManager
 * EntityManager talk with Admin entity
 */
package com.ejb;

import com.entity.Admin;
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
public class AdminEJB {
    
    @PersistenceContext(unitName = "MyPU")
    private EntityManager em;
    Logger log = Logger.getLogger(this.getClass().getName());
    
    // get all admin accounts
    public List<Admin> selectAll(){        
        Query query = em.createNamedQuery(Admin.FIND_ALL, Admin.class);
        return query.getResultList();
    }
    
     // get admin's profile for login
    public List<Admin> selectAdminProfile(String Email){        
        Query query = em.createNamedQuery(Admin.FIND_ADMIN, Admin.class);
        query.setParameter(1,Email);
        return query.getResultList();
    }
    
    // get admin's profile for login
    public List<Admin> login(String username, String password){        
        Query query = em.createNamedQuery(Admin.LOGIN_VERIFICATION, Admin.class);
        query.setParameter(1,username);
        query.setParameter(2,password);
        
        return query.getResultList();
    }
    // create admin's account 
    public void create(Admin obj){
        em.persist(obj);
    }
    
}
