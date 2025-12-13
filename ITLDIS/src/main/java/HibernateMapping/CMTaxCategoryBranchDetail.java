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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author avinash.pandey
 */
@Entity
@Table(name = "CM_TAXCTGRY_BRANCH_DTL")
public class CMTaxCategoryBranchDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "taxctgry_dtl_id")
    private Integer taxctgry_dtl_id;
//    @Basic(optional = false)
//    @Column(name = "taxctgry_branch_id")
//    private Integer taxctgry_branch_id;
    @Basic(optional = false)
    @Column(name = "charge_branch_id")
    private Integer charge_branch_id;
    @Basic(optional = false)
    @Column(name = "SaleType")
    private String saleType;
    @Column(name = "IsPrimaryTax")
    private String isPrimaryTax;
    @Column(name = "DisplayOrder")
    private Integer displayOrder;
    @Column(name = "CreatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "CreatedBy")
    private String createdBy;
    @Column(name = "ModifiedBy")
    private String modifiedBy;
    @Column(name = "ModifiedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedDate;

    @ManyToOne
    @JoinColumn(name="taxctgry_branch_id", referencedColumnName = "taxctgry_branch_id")
    private CMTaxCategoryBranch cMTaxCategoryBranch;

    public CMTaxCategoryBranchDetail() {
    }

    public CMTaxCategoryBranchDetail(Integer taxctgry_dtl_id) {
        this.taxctgry_dtl_id = taxctgry_dtl_id;
    }

    public CMTaxCategoryBranchDetail(Integer taxctgry_dtl_id,  Integer charge_branch_id,String saleType) {
        this.taxctgry_dtl_id = taxctgry_dtl_id;
        //this.taxctgry_branch_id = taxctgry_branch_id;
        this.charge_branch_id = charge_branch_id;
        this.saleType = saleType;
    }

   

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getTaxctgry_dtl_id() != null ? getTaxctgry_dtl_id().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CMTaxCategoryBranchDetail)) {
            return false;
        }
        CMTaxCategoryBranchDetail other = (CMTaxCategoryBranchDetail) object;
        if ((this.getTaxctgry_dtl_id() == null && other.getTaxctgry_dtl_id() != null) || (this.getTaxctgry_dtl_id() != null && !this.taxctgry_dtl_id.equals(other.taxctgry_dtl_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.CMTaxCategoryBranchDetail[taxctgry_dtl_id=" + getTaxctgry_dtl_id() + "]";
    }

    public CMTaxCategoryBranch getcMTaxCategoryBranch() {
        return cMTaxCategoryBranch;
    }

    public void setcMTaxCategoryBranch(CMTaxCategoryBranch cMTaxCategoryBranch) {
        this.cMTaxCategoryBranch = cMTaxCategoryBranch;
    }
    
    public Integer getTaxctgry_dtl_id() {
        return taxctgry_dtl_id;
    }

    public void setTaxctgry_dtl_id(Integer taxctgry_dtl_id) {
        this.taxctgry_dtl_id = taxctgry_dtl_id;
    }

//    public Integer getTaxctgry_branch_id() {
//        return taxctgry_branch_id;
//    }
//    public void setTaxctgry_branch_id(Integer taxctgry_branch_id) {
//        this.taxctgry_branch_id = taxctgry_branch_id;
//    }
    public Integer getCharge_branch_id() {
        return charge_branch_id;
    }

    public void setCharge_branch_id(Integer charge_branch_id) {
        this.charge_branch_id = charge_branch_id;
    }
    public String getSaleType() {
        return saleType;
    }
    public void setSaleType(String saleType) {
        this.saleType = saleType;
    }
    public String getIsPrimaryTax() {
        return isPrimaryTax;
    }
    public void setIsPrimaryTax(String isPrimaryTax) {
        this.isPrimaryTax = isPrimaryTax;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }
    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }
    public Date getCreatedOn() {
        return createdOn;
    }
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }
    public String getCreatedBy() {
        return createdBy;
    }
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

}
