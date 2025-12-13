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

/**
 *
 * @author prashant.kumar
 */
@Entity
@Table(name = "CM_TAXCHARG_OEM")
public class CmTaxCharg_OEM implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "taxcharge_oem_id")
    private Integer taxOemId;
    @Column(name = "TaxChargeCode")
    private String taxChargeCode;
    @Basic(optional = false)
    @Column(name = "TaxChargeDesc")
    private String taxChargeDesc;
    @Column(name = "IsActive")
    private String isActive;
    @Column(name = "IsTaxOrCharge")
    private String isTaxOrCharge;
    @Column(name = "CreatedDate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdDate;
    @Column(name = "CreatedBy")
    private String createdBy;
    @Column(name = "ModifiedDate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date modifiedDate;
    @Column(name = "ModifiedBy")
    private String modifiedBy;

//    @ManyToOne(optional = false, fetch = FetchType.LAZY)
//    private CmChargeBranch cmChargeBranch;

    public CmTaxCharg_OEM(Integer taxOemId, String taxChargeCode, String taxChargeDesc, String isActive, String isTaxOrCharge, Date createdDate, String createdBy, Date modifiedDate, String modifiedBy) {
        this.taxOemId = taxOemId;
        this.taxChargeCode = taxChargeCode;
        this.taxChargeDesc = taxChargeDesc;
        this.isActive = isActive;
        this.isTaxOrCharge = isTaxOrCharge;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
        this.modifiedDate = modifiedDate;
        this.modifiedBy = modifiedBy;
    }
    
    public CmTaxCharg_OEM() {
    }
       
    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    
    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getIsTaxOrCharge() {
        return isTaxOrCharge;
    }

    public void setIsTaxOrCharge(String isTaxOrCharge) {
        this.isTaxOrCharge = isTaxOrCharge;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public String getTaxChargeCode() {
        return taxChargeCode;
    }

    public void setTaxChargeCode(String taxChargeCode) {
        this.taxChargeCode = taxChargeCode;
    }

    public String getTaxChargeDesc() {
        return taxChargeDesc;
    }

    public void setTaxChargeDesc(String taxChargeDesc) {
        this.taxChargeDesc = taxChargeDesc;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Integer getTaxOemId() {
        return taxOemId;
    }

    public void setTaxOemId(Integer taxOemId) {
        this.taxOemId = taxOemId;
    }    
}
