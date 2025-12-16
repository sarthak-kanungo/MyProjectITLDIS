/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ashutosh.kumar
 */
@Entity
@Table(name = "UM_CustomerCategoryMaster")

public class CustomerCategoryMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CustCategoryID")
    private Integer custcategoryID;
    @Basic(optional = false)
    @Column(name = "CustCategory")
    private String custCategory;
    @Basic(optional = false)
    @Column(name = "isActive")
    private char isActive;
    @Basic(optional = false)
    @Column(name = "CreatedBy")
    private String CreatedBy;

    @Basic(optional = false)
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "ModifiedBy")
    private String modifiedBy;

    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;


    public CustomerCategoryMaster() {
    }

    public CustomerCategoryMaster(Integer custcategoryID) {
        this.custcategoryID = custcategoryID;
    }

    public CustomerCategoryMaster(Integer custcategoryID, String custCategory, char isActive,String CreatedBy, Date createdOn) {
        this.custcategoryID = custcategoryID;
        this.custCategory = custCategory;
        this.isActive = isActive;
        this.CreatedBy = CreatedBy;
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCustCategory() {
        return custCategory;
    }

    public void setCustCategory(String custCategory) {
        this.custCategory = custCategory;
    }

    public Integer getCustcategoryID() {
        return custcategoryID;
    }

    public void setCustcategoryID(Integer custcategoryID) {
        this.custcategoryID = custcategoryID;
    }

    public char getIsActive() {
        return isActive;
    }

    public void setIsActive(char isActive) {
        this.isActive = isActive;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    

   

}
