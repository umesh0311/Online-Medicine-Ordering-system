/*
 * AdminController can handle create account, login, logout, check session and check admin profile
 * it get the data from the view and send it to EJB for processing
 * Controller haldle HttpSession independently with the help of view
 */
package com.controller;

import com.ejb.AdminEJB;
import com.entity.Admin;
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
@Named(value = "adminController")
@RequestScoped
public class AdminController {
    
    @EJB
    private AdminEJB adminEJB;
    private List<Admin> admins;
    private Admin admin = new Admin();
    Logger log = Logger.getLogger(this.getClass().getName());
    
    /**
     * Creates a new instance of AdminController
     */
    public AdminController() {
    }
    
    // this method call the adminEJB method and return adminLogin.xhtml page 
    public String doCreate(){
        // TODO: use EJB to create customer and save data into database here...
        adminEJB.create(admin);
        return "adminLogin.xhtml";
    }
    
    //this method check the login details in database and create a admin session if entered username and password will be matched
    public String doLogin(){
        List<Admin> login = adminEJB.login(admin.getEmail(), admin.getPassword());
        boolean isLogin=false;
        if(login != null) {
            
            if(login.size() != 0){
                isLogin = true;
                HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
                session.setAttribute("admin", login.get(0));
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
            return "adminPanel.xhtml";
        else
            return "login.xhtml";
    }
    
    //check the session for admin
     public boolean checkSession() {
        
        boolean isLogin=true;
            
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Admin admin = (Admin) session.getAttribute("admin");
      
        if (admin == null){
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
        session.removeAttribute("admin");
        return "adminLogin.xhtml";
    }
    // not in used now - for future
    public List<Admin> getAllAdminProfile(){
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Admin adminProfile = (Admin) session.getAttribute("admin");
        return adminEJB.selectAdminProfile(adminProfile.getEmail());
    }
    
    
    public Admin getAdmin(){
        return admin;
    }
    
    public void setAdmin(Admin obj){
        this.admin = obj;
    }

    public List<Admin> getAdmins(){
        return admins;
    }

}
