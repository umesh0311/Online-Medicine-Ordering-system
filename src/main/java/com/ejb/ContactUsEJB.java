/*
 * Under Development
 * ContactUsEJB  handle create, view functions
 * it get the data from the Controller and workout with database using EntityManager
 * EntityManager talk with Admin entity
 */
package com.ejb;

import com.entity.ContactUs;
import java.util.List;
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
public class ContactUsEJB {
    @PersistenceContext(unitName = "MyPU")
    private EntityManager em;
    // get all Contacts
    public List<ContactUs> selectAll(){        
        Query query = em.createNamedQuery(ContactUs.FIND_ALL_CONTACTS, ContactUs.class);
        return query.getResultList();
    }
    
    public List<ContactUs> searchById(String name){        
        Query query = em.createNamedQuery(ContactUs.SEARCH_CONTACT_BY_ID, ContactUs.class);
        query.setParameter(1, "%" + name +  "%");
        return query.getResultList();
    }
    
    public List<ContactUs> searchByUsername(String name){        
        Query query = em.createNamedQuery(ContactUs.SEARCH_CONTACT_BY_NAME, ContactUs.class);
        query.setParameter(1, "%" + name +  "%");
        return query.getResultList();
    }
    
    public void create(ContactUs obj){
        em.persist(obj);
    }
    
    public void update(ContactUs obj){
        em.merge(obj);        
    }
    
    public void delete(long id){        
        em.remove(em.find(ContactUs.class, id));        
    }
    
    public ContactUs getById(Long id){
        return em.find(ContactUs.class, id);
    }
    
    
    
}
