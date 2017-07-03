/*
 * This class is under developing
 * ContactUsController hadle simple insert function to get query from the guest customer
 * it get the data from the view and send it to EJB for processing
 */
package com.controller;

import com.ejb.ContactUsEJB;
import com.entity.ContactUs;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author Badal
 */
@Named(value = "contactUsController")
@RequestScoped
public class ContactUsController {

    @EJB
    private ContactUsEJB contactUsEJB;
    private List<ContactUs> contacts;
    private ContactUs contact = new ContactUs();
    
    /**
     * Creates a new instance of ContactUsController
     */

    public String doCreate(){
        // TODO: use EJB to create customer contact and save data into database.
        contactUsEJB.create(contact);
        return "contactUs.xhtml";
    }

    public String doFindById(){
        contact = contactUsEJB.getById(contact.getId());
        return "viewContact.xhtml";
    }
    
    public String doSearch(){
        contacts = contactUsEJB.searchByUsername(contact.getUsername());
        return "searchContactResult.xhtml";
    }
    
    public String doSearchByName() {
        String username = contact.getUsername();
        List<ContactUs> contactsList = new ArrayList<>();
        contacts = contactUsEJB.searchByUsername(username);
        for(ContactUs obj : contacts){
            if(obj.getUsername().equals(username)){
               contactsList.add(obj);
            }
        }      
        contacts = contactsList;
        return "searchContactResult.xhtml";
    }
    
    public List<ContactUs> getAllContacts(){
        return contactUsEJB.selectAll();
    }
    
    public ContactUs getContact(){
        return contact;
    }
    
    public void setContact(ContactUs obj){
        this.contact = obj;
    }

    public List<ContactUs> getContacts(){
        return contacts;
    }
 
    
}   // end ContactUsController

