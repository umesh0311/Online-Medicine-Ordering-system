/*
 * This is the Order history table for Prescribed Medicine 
 * PrescribedMedicineEJB will  handle all function regarding Prescribed Medicine for user and admin
 * it get the data from the Controller and workout with database using EntityManager
 * EntityManager talk with Admin entity
 */
package com.ejb;

import com.entity.Customer;
import com.entity.PrescribedMedicine;
import java.util.ArrayList;
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
public class PrescribedMedicineEJB {
    @PersistenceContext(unitName = "MyPU")
    private EntityManager em;
    
    // get all prescribed medicine
    public List<PrescribedMedicine> selectAll(){        
        Query query = em.createNamedQuery(PrescribedMedicine.FIND_ALL_PRESCRIBED_MEDICINE, PrescribedMedicine.class);
        return query.getResultList();
    }
    
    // get all prescribed medicine by user
    public List<PrescribedMedicine> selectUser(Long Id){        
        Query query = em.createNamedQuery(PrescribedMedicine.FIND_ALL_PRESCRIBED_MEDICINE_BY_CUSTOMER, PrescribedMedicine.class);
        query.setParameter(1, Id);
        return query.getResultList();
    }
    
    // used for create an order
    public void create(PrescribedMedicine obj){
        em.persist(obj);
    }
  
}   // end of PrescribedMedicineEJB class
