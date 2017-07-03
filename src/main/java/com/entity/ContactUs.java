/*
 * 
 * 
 * 
 */
package com.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Badal
 */
@Entity
@NamedQueries({
    @NamedQuery(name = ContactUs.FIND_ALL_CONTACTS, query = "SELECT c FROM ContactUs c"),
    @NamedQuery(name = ContactUs.SEARCH_CONTACT_BY_ID, query = "SELECT c FROM ContactUs c WHERE c.id LIKE ?1"),
    @NamedQuery(name = ContactUs.SEARCH_CONTACT_BY_NAME, query = "SELECT c FROM ContactUs c WHERE c.username LIKE ?1")
})
public class ContactUs implements Serializable {

    
    private static final long serialVersionUID = 1L;
    public static final String FIND_ALL_CONTACTS = "ContactUs.findAllContacts";
    public static final String SEARCH_CONTACT_BY_ID = "ContactUs.searchContactId";
    public static final String SEARCH_CONTACT_BY_NAME = "ContactUs.searchContactByName";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String email;    
    private String message;
    
    // used timestamp to store the current date and time for Gust customer
    @Basic(optional = false)
    @Column(name = "DATE_CREATED", insertable = false, updatable = false, nullable = false,columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @Temporal(TemporalType.TIMESTAMP)
    private Date creationDate;
    
      
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ContactUs)) {
            return false;
        }
        ContactUs other = (ContactUs) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.entity.ContactUs[ id=" + id + " ]";
    }
    
}
