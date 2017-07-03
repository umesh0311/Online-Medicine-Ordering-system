/*
 * Under Development
 * NonPrescribedMedicineEJB  will hanlde all product related functions
 * it get the data from the Controller and workout with database using EntityManager
 * EntityManager talk with Admin entity
 */
package com.ejb;

import com.entity.NonPrescribedMedicine;
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
public class NonPrescribedMedicineEJB {
    @PersistenceContext(unitName = "MyPU")
    private EntityManager em;
    // Add business logic below. 
    
    // get all medicines whicih are prescribed
    public List<NonPrescribedMedicine> selectAll(){        
        Query query = em.createNamedQuery(NonPrescribedMedicine.FIND_ALL_NON_PRESCRIBED_MEDICINE, NonPrescribedMedicine.class);
        return query.getResultList();
    }
    
    public List<NonPrescribedMedicine> searchById(int id){        
        Query query = em.createNamedQuery(NonPrescribedMedicine.SEARCH_BY_ID, NonPrescribedMedicine.class);
        query.setParameter(1, "%" + id +  "%");
        return query.getResultList();
    }
    
    public List<NonPrescribedMedicine> searchByName(String medicineName){        
        Query query = em.createNamedQuery(NonPrescribedMedicine.SEARCH_BY_MEDICINE_NAME, NonPrescribedMedicine.class);
        query.setParameter(1, "%" + medicineName +  "%");
        return query.getResultList();
    }
    
    public void create(NonPrescribedMedicine obj){
        em.persist(obj);
    }
    
    public void update(NonPrescribedMedicine obj){
        em.merge(obj);        
    }
    
    public void delete(long id){        
        em.remove(em.find(NonPrescribedMedicine.class, id));        
    }
    
    public NonPrescribedMedicine getById(Long id){
        return em.find(NonPrescribedMedicine.class, id);
    }
    
    
}   // end of NonPrescribedMedicineEJB class
