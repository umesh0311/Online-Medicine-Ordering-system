/*
 * NonPrescribedMedicineController can handle the order of the Non PrescribedMedicines, file upload and file download for prescription file
 *It store the order data in the its entity tables
 * it get the data from the view and send it to EJB for processing
 * Controller use HttpSession to retrive the order details customer's account and all details for admin
 */
package com.controller;

import com.ejb.PrescribedMedicineEJB;
import com.entity.Customer;
import com.entity.PrescribedMedicine;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

/**
 *
 * @author Badal
 */
@Named(value = "prescribedMedicineController")
@RequestScoped
public class PrescribedMedicineController {

    @EJB
    private PrescribedMedicineEJB prescribedMedicineEJB;
    private List<PrescribedMedicine> prescribedMedicines;
    private PrescribedMedicine prescribedMedicine = new PrescribedMedicine();
    private Part file;
    private String fileLink;
    static private String fileName, fileNameLink;
    Logger log = Logger.getLogger(this.getClass().getName());

    /**
     * Creates a new instance of PrescribedMedicineController
     */
    public PrescribedMedicineController() {
    }

    // this method is used to upload the file into server. the logic is used from webstie http://www.ramkitech.com/2013/06/file-upload-is-easy-in-jsf22.html
    public String upload() throws IOException {
        InputStream inputStream = file.getInputStream();

        fileNameLink = getFilename(file);
        fileLink = "/Users/Badal/NetBeansProjects/7DaysChemist/7DaysChemist/src/main/webapp/Files/" + fileNameLink;
        FileOutputStream outputStream = new FileOutputStream(fileLink);

        byte[] buffer = new byte[4096];
        int bytesRead = 0;
        while (true) {
            bytesRead = inputStream.read(buffer);
            if (bytesRead > 0) {
                outputStream.write(buffer, 0, bytesRead);
            } else {
                break;
            }
        }
        outputStream.close();
        inputStream.close();
        prescribedMedicine.setFileUploadLink(fileLink);
        prescribedMedicine.setCustomer(getCustomerID());

        prescribedMedicineEJB.create(prescribedMedicine);

        return "userAccount.xhtml";
    }

    // this method download the file from the server, the logic is used from website http://stackoverflow.com/questions/9391838/how-to-provide-a-file-download-from-a-jsf-backing-bean
    public String download() throws IOException {
        // get the reference of this code from webstie
        FacesContext fc = FacesContext.getCurrentInstance();
        HttpServletResponse response = (HttpServletResponse) fc.getExternalContext().getResponse();
        final ExternalContext externalContext = fc.getExternalContext();

        response.reset(); // Some JSF component library or some Filter might have set some headers in the buffer beforehand. We want to get rid of them, else it may collide.
        response.setContentType(externalContext.getMimeType(fileName)); // Check http://www.iana.org/assignments/media-types for all types. Use if necessary ServletContext#getMimeType() for auto-detection based on filename.
     //   response.setContentLength("content-Length",String.valueOf(file.)); // Set it with the file size. This header is optional. It will work if it's omitted, but the download progress will be unknown.
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\""); // The Save As popup magic is done here. You can give it any file name you want, this only won't work in MSIE, it will use current request URL as file name instead.

        ServletOutputStream output = response.getOutputStream();
        
        // Now you can write the InputStream of the file to the above OutputStream the usual way.
     
          InputStream inputStream = file.getInputStream();
                byte[] bytesBuffer = new byte[2048];
                int bytesRead;
                while ((bytesRead = inputStream.read(bytesBuffer)) > 0) {
                  output.write(bytesBuffer, 0, bytesRead);
                }
                
                output.flush();
           
            output.close();
        fc.responseComplete(); // Important! Otherwise JSF will attempt to render the response which obviously will fail since it's already written with a file and closed.
        return "userAccount.xhtml";
    }

    // this method retrive customer's ID from database by comparing session ID for prescritionmedicine's order
    public Customer getCustomerID() {

        Long customerID;
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Customer customer = (Customer) session.getAttribute("customer");
        customerID = customer.getId();

        return customer;
    }

    // it will remove the customer's session from the browser
    public String logout() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        session.removeAttribute("customer");
        return "login.xhtml";
    }

    // for admin, it retrive all prescribedmedicine list 
    public List<PrescribedMedicine> getAllPrescribedMedicines() {
        return prescribedMedicineEJB.selectAll();
    }

    // this method is used for customer/ user to retrive customer's order only
    public List<PrescribedMedicine> getAllPrescribedMedicinesByUser() {
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
        Customer customerProfile = (Customer) session.getAttribute("customer");
        if (customerProfile != null) {
            return prescribedMedicineEJB.selectUser(customerProfile.getId());
        } else {
            return new ArrayList<>();
        }
    }

    public PrescribedMedicine getPrescribedMedicine() {
        return prescribedMedicine;
    }

    public void setPrescribedMedicine(PrescribedMedicine obj) {
        this.prescribedMedicine = obj;
    }

    public List<PrescribedMedicine> getPrescribedMedicines() {
        return prescribedMedicines;
    }

    // use to get file from view
    public Part getFile() {
        return file;
    }
    //use to set file from view
    public void setFile(Part file) {
        this.file = file;
    }

    public static String getFileName() {
        return fileName;
    }

    public static void setFileName(String fileName) {
        PrescribedMedicineController.fileName = fileName;
    }

    // this method is used to retrive the filename forom the HTTPS header
    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                fileName = filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1);
                return filename; // MSIE fix.
            }
        }
        return null;
    }

}   // end prescribedMedicineController

