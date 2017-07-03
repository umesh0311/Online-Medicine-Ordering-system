/*
 * CustomerController can handle create account, login, logout, update user profile, get all customer, check session and check Customer profile
 * it get the data from the view and send it to EJB for processing
 * Controller haldle HttpSession independently with the help of view
 */
package com.controller;

import com.ejb.CustomerEJB;
import com.entity.Customer;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Badal
 */
@Named(value = "customerController")
@RequestScoped
public class CustomerController {

    @EJB
    private CustomerEJB customerEJB;
    private List<Customer> customers;
    private Customer customer = new Customer();
    Logger log = Logger.getLogger(this.getClass().getName());
    /**
     * Creates a new instance of CustomerController
     */
    public CustomerController() {
    }

    // this method call the customerEJB method and return usserAccount.xhtml page 
    public String doCreate(){
        // TODO: use EJB to create customer and save data into database here...
        customerEJB.create(customer);
        return "usserAccount.xhtml";
    }
    
    //this method check the login details in database and create a customer session if entered username and password will be matched
    public String doLogin(){
        List<Customer> login = customerEJB.login(customer.getEmail(), customer.getPassword());
        boolean isLogin=false;
        if(login != null) {
            
            if(login.size() != 0){
                isLogin = true;
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("customer", login.get(0));
                
            }
            else{
                isLogin = false;
            }
        }
        else{
            isLogin = false;
        }
        
        // redirect the appropriate page depends after login
        if (isLogin)
        return "userAccount.xhtml";
        else
        return "login.xhtml";
    }

      //check the session for customer   
    public boolean checkSession() {
        
        boolean isLogin=true;
            
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Customer customer = (Customer) session.getAttribute("customer");
      
        if (customer == null){
            isLogin = false;
        }
        else
        {
            isLogin = true;
        }
        return isLogin;
    }
    
    // remove admin session from the browser 
    public String logout(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute("customer");
        return "login.xhtml";
    }
    
    // Update the user profile
    public String doUpdate(){
        
        // TODO: use EJB to create customer and save data into database here...
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Customer customerUpdate = (Customer) session.getAttribute("customer");
        customerEJB.updateCustomer(customer.getFirstName(),customer.getLastName(),customer.getMedicareNumber(),customer.getStateName(),customer.getCity(),customer.getPostCode(),customer.getAddress(),customer.getDateOfBirth(),customer.getPhone(),customer.getPassword(),customerUpdate.getEmail());
        return "userProfile.xhtml";
    }
    
    // retrive all customer data. Used to show customer's profile
    public List<Customer> getAllCustomers(){
        return customerEJB.selectAll();
    }
    
    //retrive particular customer's data for order history
    public List<Customer> getAllCustomerProfile(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Customer customerProfile = (Customer) session.getAttribute("customer");
        if (customerProfile != null) {
            return customerEJB.selectUserProfile(customerProfile.getEmail());
        } else {
            return new ArrayList<>();
        }
    }

    public Customer getCustomer(){
        return customer;
    }
    
    public void setCustomer(Customer obj){
        this.customer = obj;
    }

    public List<Customer> getCustomers(){
        return customers;
    }
  
}   // end CustomerController
