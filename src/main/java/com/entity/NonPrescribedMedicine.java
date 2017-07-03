/*
*Under Development
 * NonPrescribedMedicine Entity has all variables and it's setter and getter methods as well the NamedQueries
 * It handles the relationship based notataion not using the queries
 * 
 */
package com.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author Badal
 */
@Entity
@NamedQueries({
    @NamedQuery(name = NonPrescribedMedicine.FIND_ALL_NON_PRESCRIBED_MEDICINE, query = "SELECT p FROM NonPrescribedMedicine p"),
    @NamedQuery(name = NonPrescribedMedicine.SEARCH_BY_ID, query = "SELECT p FROM NonPrescribedMedicine p WHERE p.id LIKE ?1"),
    @NamedQuery(name = NonPrescribedMedicine.SEARCH_BY_MEDICINE_NAME, query = "SELECT p FROM NonPrescribedMedicine p WHERE p.medicineName LIKE ?1")
})
public class NonPrescribedMedicine implements Serializable {

    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL_NON_PRESCRIBED_MEDICINE = "NonPrescribedMedicine.findAllMedicine";
    public static final String SEARCH_BY_ID = "NonPrescribedMedicine.searchById";
    public static final String SEARCH_BY_MEDICINE_NAME = "NonPrescribedMedicine.searchByMedicineName";
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;    
    private String medicineName;
    private String medicineDescrption;
    private String medicineImage;
    private int quantity;    
    private double price;    
    private int categoryId; // Foreign Key from Category Entity
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMedicineName() {
        return medicineName;
    }

    public void setMedicineName(String medicineName) {
        this.medicineName = medicineName;
    }

    public String getMedicineDescrption() {
        return medicineDescrption;
    }

    public void setMedicineDescrption(String medicineDescrption) {
        this.medicineDescrption = medicineDescrption;
    }

    public String getMedicineImage() {
        return medicineImage;
    }

    public void setMedicineImage(String medicineImage) {
        this.medicineImage = medicineImage;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }



    @Override
    public String toString() {
        return "com.entity.NonPrescribedMedicine[ id=" + id + " ]";
    }
    
}