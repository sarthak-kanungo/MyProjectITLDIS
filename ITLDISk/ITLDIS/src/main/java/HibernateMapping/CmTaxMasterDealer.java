/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author prashant.kumar
 */
@Entity
@Table(name = "CM_TAXMASTER_DEALER")

public class CmTaxMasterDealer implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private CmTaxMasterDealerPK cmTaxMasterDealerPK;    
    @Column(name = "taxctgry_branch_id")
    private String taxctgry_branch_id;
    @Column(name = "CreatedBy")
    private String createdBy;
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "ModifiedBy")
    private String modifiedBy;
    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    

    public CmTaxMasterDealer() {
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getTaxctgry_branch_id() {
        return taxctgry_branch_id;
    }

    public void setTaxctgry_branch_id(String taxctgry_branch_id) {
        this.taxctgry_branch_id = taxctgry_branch_id;
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

    public CmTaxMasterDealerPK getCmTaxMasterDealerPK() {
        return cmTaxMasterDealerPK;
    }

    public void setCmTaxMasterDealerPK(CmTaxMasterDealerPK cmTaxMasterDealerPK) {
        this.cmTaxMasterDealerPK = cmTaxMasterDealerPK;
    }
    
    public CmTaxMasterDealer(String commodityCode, String dealerCode, Date effectiveDate)
    {
        this.cmTaxMasterDealerPK = new CmTaxMasterDealerPK(commodityCode, dealerCode, effectiveDate);
    }
   
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + (this.cmTaxMasterDealerPK != null ? this.cmTaxMasterDealerPK.hashCode() : 0);
        hash = 97 * hash + (this.taxctgry_branch_id != null ? this.taxctgry_branch_id.hashCode() : 0);
        hash = 97 * hash + (this.createdBy != null ? this.createdBy.hashCode() : 0);
        hash = 97 * hash + (this.createdOn != null ? this.createdOn.hashCode() : 0);
        hash = 97 * hash + (this.modifiedBy != null ? this.modifiedBy.hashCode() : 0);
        hash = 97 * hash + (this.modifiedOn != null ? this.modifiedOn.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CmTaxMasterDealer other = (CmTaxMasterDealer) obj;
        if (this.cmTaxMasterDealerPK != other.cmTaxMasterDealerPK && (this.cmTaxMasterDealerPK == null || !this.cmTaxMasterDealerPK.equals(other.cmTaxMasterDealerPK))) {
            return false;
        }
        if ((this.taxctgry_branch_id == null) ? (other.taxctgry_branch_id != null) : !this.taxctgry_branch_id.equals(other.taxctgry_branch_id)) {
            return false;
        }
        if ((this.createdBy == null) ? (other.createdBy != null) : !this.createdBy.equals(other.createdBy)) {
            return false;
        }
        if (this.createdOn != other.createdOn && (this.createdOn == null || !this.createdOn.equals(other.createdOn))) {
            return false;
        }
        if ((this.modifiedBy == null) ? (other.modifiedBy != null) : !this.modifiedBy.equals(other.modifiedBy)) {
            return false;
        }
        if (this.modifiedOn != other.modifiedOn && (this.modifiedOn == null || !this.modifiedOn.equals(other.modifiedOn))) {
            return false;
        }
        return true;
    }

     @Override
    public String toString() {
        return "HibernateMapping.CmTaxMasterDealer[cmTaxMasterDealerPK=" + cmTaxMasterDealerPK + "]";
    }
}
