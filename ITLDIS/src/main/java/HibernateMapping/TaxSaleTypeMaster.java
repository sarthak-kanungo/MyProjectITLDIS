
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
@Table(name = "CM_TAX_SALETYPE_MST")

public class TaxSaleTypeMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SaleTypeID")
    private Integer saleTypeID;

    @Basic(optional = false)
    @Column(name = "SaleTypeCode")
    private String saleTypeCode;

    @Basic(optional = false)
    @Column(name = "SaleTypeDesc")
    private String saleTypeDesc;

     
    @Basic(optional = false)
    @Column(name = "isActive")
    private char isActive;
    @Basic(optional = false)
    @Column(name = "CreatedBy")
    private String CreatedBy;

    @Basic(optional = false)
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "ModifiedBy")
    private String modifiedBy;

    @Column(name = "ModifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;


    public TaxSaleTypeMaster() {
    }

    public TaxSaleTypeMaster(Integer saleTypeID) {
        this.saleTypeID = saleTypeID;
    }

    public TaxSaleTypeMaster(Integer saleTypeID, String saleTypeCode, String saleTypeDesc, char isActive,String CreatedBy, Date createdDate) {
        this.saleTypeID = saleTypeID;
        this.saleTypeCode = saleTypeCode;
        this.saleTypeDesc = saleTypeDesc;
        this.isActive = isActive;
        this.CreatedBy = CreatedBy;
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String CreatedBy) {
        this.CreatedBy = CreatedBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
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

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public String getSaleTypeCode() {
        return saleTypeCode;
    }

    public void setSaleTypeCode(String saleTypeCode) {
        this.saleTypeCode = saleTypeCode;
    }

    public Integer getSaleTypeID() {
        return saleTypeID;
    }

    public void setSaleTypeID(Integer saleTypeID) {
        this.saleTypeID = saleTypeID;
    }

    public String getSaleTypeDesc() {
        return saleTypeDesc;
    }

    public void setSaleTypeDesc(String saleTypeDesc) {
        this.saleTypeDesc = saleTypeDesc;
    }





}
