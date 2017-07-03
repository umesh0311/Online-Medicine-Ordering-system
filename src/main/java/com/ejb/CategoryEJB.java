/*
 * Under Development
 * AdminEJB  handle insert, update and detele functions 
 * it get the data from the Controller and workout with database using EntityManager
 * EntityManager talk with Category entity
 */
package com.ejb;

import com.entity.Category;
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
public class CategoryEJB {
    
    @PersistenceContext(unitName = "MyPU")
    private EntityManager em;
    

    public void create(Category obj){
        em.persist(obj);
    }
    
    public void update(Category obj){
        em.merge(obj);        
    }
    
    public void delete(long id){        
        em.remove(em.find(Category.class, id));        
    }

    public Category getByCategoryId(Long id) {
        return em.find(Category.class, id);
    }

    public List<Category> searchByCategoryName(String categoryName) {
        Query query = em.createNamedQuery(Category.SEARCH_BY_CATEGORY_NAME, Category.class);
        query.setParameter(1, "%" + categoryName +  "%");
        return query.getResultList();
    }

    public List<Category> selectAll() {
        Query query = em.createNamedQuery(Category.FIND_ALL, Category.class);
        return query.getResultList();
    }
    
}
