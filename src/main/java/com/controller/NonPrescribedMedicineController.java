/*
 *Under development
 * NonPrescribedMedicineController can handle the product 
 * it get the data from the view and send it to EJB for processing
 */
package com.controller;

import com.ejb.NonPrescribedMedicineEJB;
import com.entity.NonPrescribedMedicine;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Badal
 */
@Named(value = "nonPrescribedMedicineController")
@RequestScoped
public class NonPrescribedMedicineController {

    @EJB
    private NonPrescribedMedicineEJB nonPrescribedMedicineEJB;
    private List<NonPrescribedMedicine> nonPrescribedMedicinesList;
    private NonPrescribedMedicine nonPrescribedMedicine = new NonPrescribedMedicine();

    /**
     * Creates a new instance of PrescribedMedicineController
     */
    public NonPrescribedMedicineController() {
    }

    public String doCreate(){
        // TODO: use EJB to create prescribed medicine and save data into database here...
        nonPrescribedMedicineEJB.create(nonPrescribedMedicine);
        return "listNonPrescribedMedicines.xhtml";
    }

    public String searchById(){
        nonPrescribedMedicine = nonPrescribedMedicineEJB.getById(nonPrescribedMedicine.getId());
        return "viewNonPrescribedMedicine.xhtml";
    }
    
    public String doSearchByName() {
        String name = nonPrescribedMedicine.getMedicineName();
        List<NonPrescribedMedicine> npmList = new ArrayList<>();
        nonPrescribedMedicinesList = nonPrescribedMedicineEJB.searchByName(name);
        for(NonPrescribedMedicine obj : nonPrescribedMedicinesList){
            if(obj.getMedicineName().equals(name)){
               npmList.add(obj);
            }
        }      
        nonPrescribedMedicinesList = npmList;
        return "searchNonPrescribedMedicineResult.xhtml";
    }
    
    public List<NonPrescribedMedicine> getAllNonPrescribedMedicines(){
        return nonPrescribedMedicineEJB.selectAll();
    }
    
    public NonPrescribedMedicine getNonPrescribedMedicine(){
        return nonPrescribedMedicine;
    }
    
    public void setNonPrescribedMedicine(NonPrescribedMedicine obj){
        this.nonPrescribedMedicine = obj;
    }

    public List<NonPrescribedMedicine> getNonPrescribedMedicinesList(){
        return nonPrescribedMedicinesList;
    }
      
}   // end of NonPrescribedMedicineController
