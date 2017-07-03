/*
 * This class is under developing
 * CategoryController hadle simple add, update and delete functions to add category of the product
 * it get the data from the view and send it to EJB for processing
 */
package com.controller;

import com.ejb.CategoryEJB;
import com.entity.Category;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Badal
 */
@Named(value = "categoryController")
@RequestScoped
public class CategoryController {

    @EJB
    private CategoryEJB categoryEJB;
    private List<Category> categoryList;
    private Category category = new Category();

    /**
     * Creates a new instance of ContactUsController
     */
    public CategoryController() {
    }

    public String doCreate(){
        // TODO: use EJB to create category and save data into database.
        categoryEJB.create(category);
        return "listCategories.xhtml";
    }

    public String findByCategoryId(){
        category = categoryEJB.getByCategoryId(category.getId());
        return "viewCategory.xhtml";
    }
    
    public String doSearchByCategoryName(){
        categoryList = categoryEJB.searchByCategoryName(category.getCategoryName());
        return "searchCategoryResult.xhtml";
    }
    
    
    public List<Category> getAllCategories(){
        return categoryEJB.selectAll();
    }
    
    public Category getCategory(){
        return category;
    }
    
    public void setCategory(Category category){
        this.category = category;
    }

}   // end CategoryController

