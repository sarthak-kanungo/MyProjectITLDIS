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
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author ashutosh.kumar
 */
@Entity
@Table(name = "UM_CustomerCategoryDiscount")

public class CustomerCategoryDiscount implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CustCategoryID")
    private Integer custcategoryID;
    @Basic(optional = false)
    @Column(name = "DealerCode")
    private String dealerCode;
    @Basic(optional = false)
    @Column(name = "DiscountPercentage")
    private String discountPercentage;
   
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


    public CustomerCategoryDiscount() {
    }

    public CustomerCategoryDiscount(Integer custcategoryID) {
        this.custcategoryID = custcategoryID;
    }

    public CustomerCategoryDiscount(Integer custcategoryID, String dealerCode, String discountPercentage, String CreatedBy, Date createdOn) {
        this.custcategoryID = custcategoryID;
        this.dealerCode = dealerCode;
        this.discountPercentage = discountPercentage;
        this.CreatedBy = CreatedBy;
        this.createdOn = createdOn;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
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

    public Integer getCustcategoryID() {
        return custcategoryID;
    }

    public void setCustcategoryID(Integer custcategoryID) {
        this.custcategoryID = custcategoryID;
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
