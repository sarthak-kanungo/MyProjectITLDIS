/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HibernateMapping;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author avinash.pandey
 */
@Entity
@Table(name = "CM_TAXCTGRY_BRANCH")
public class CMTaxCategoryBranch implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "taxctgry_branch_id")
    private Integer taxctgry_branch_id;
    @Basic(optional = false)
    @Column(name = "branch_id")
    private Integer branch_id;
    @Column(name = "TaxCtgryCode")
    private String taxCtgryCode;
    @Column(name = "TaxCtgryDesc")
    private String taxCtgryDesc;
    @Column(name = "IsActive")
    private String isActive;
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
    @OneToMany(mappedBy = "cMTaxCategoryBranch", cascade = CascadeType.REMOVE,orphanRemoval = true)
    private Collection<CMTaxCategoryBranchDetail> cMTaxCategoryBranchDetail;

    public CMTaxCategoryBranch() {
    }

    public CMTaxCategoryBranch(Integer taxctgry_branch_id) {
        this.taxctgry_branch_id = taxctgry_branch_id;
    }

    public CMTaxCategoryBranch(Integer taxctgry_branch_id, Integer branch_id, String taxCtgryCode, String taxCtgryDesc) {
        this.taxctgry_branch_id = taxctgry_branch_id;
        this.branch_id = branch_id;
        this.taxCtgryCode = taxCtgryCode;
        this.taxCtgryDesc = taxCtgryDesc;
    }

    public Collection<CMTaxCategoryBranchDetail> getcMTaxCategoryBranchDetail() {
        return cMTaxCategoryBranchDetail;
    }

    public void setcMTaxCategoryBranchDetail(Collection<CMTaxCategoryBranchDetail> cMTaxCategoryBranchDetail) {
        this.cMTaxCategoryBranchDetail = cMTaxCategoryBranchDetail;
    }

    public Integer getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(Integer branch_id) {
        this.branch_id = branch_id;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (taxctgry_branch_id != null ? taxctgry_branch_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CMTaxCategoryBranch)) {
            return false;
        }
        CMTaxCategoryBranch other = (CMTaxCategoryBranch) object;
        if ((this.taxctgry_branch_id == null && other.taxctgry_branch_id != null) || (this.taxctgry_branch_id != null && !this.taxctgry_branch_id.equals(other.taxctgry_branch_id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.CMTaxCategoryBranch[taxctgry_branch_id=" + taxctgry_branch_id + "]";
    }

    public Integer getTaxctgry_branch_id() {
        return taxctgry_branch_id;
    }

    public void setTaxctgry_branch_id(Integer taxctgry_branch_id) {
        this.taxctgry_branch_id = taxctgry_branch_id;
    }

    public String getTaxCtgryCode() {
        return taxCtgryCode;
    }

    public void setTaxCtgryCode(String taxCtgryCode) {
        this.taxCtgryCode = taxCtgryCode;
    }

    public String getTaxCtgryDesc() {
        return taxCtgryDesc;
    }

    public void setTaxCtgryDesc(String taxCtgryDesc) {
        this.taxCtgryDesc = taxCtgryDesc;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
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
}
