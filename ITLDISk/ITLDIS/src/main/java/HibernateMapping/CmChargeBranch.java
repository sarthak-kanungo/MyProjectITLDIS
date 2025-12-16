/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "CM_TAXCHARG_BRANCH")
public class CmChargeBranch implements Serializable {
   private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "charge_branch_id")
    private Integer charge_branch_id;
    @Column(name = "branch_id")
    private Integer branch_id;
    @Column(name = "taxcharge_oem_id")
    private Integer taxcharge_oem_id;
    @Column(name = "ChargeCode")
    private String chargeCode;
    @Column(name = "ChargeDesc")
    private String chargeDesc;
    @Column(name = "IsActualOrPerc")
    private String isActualOrPerc;
    @Column(name = "ChargeRatePerc")
    private Double chargeRatePerc;
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
    
//    @OneToMany(cascade = CascadeType.ALL, mappedBy = "cmTaxCharg_OEM", fetch = FetchType.LAZY)
//    private List<CmTaxCharg_OEM> cmTaxCharg_OEM_list;

    public Integer getBranch_id() {
        return branch_id;
    }

    public void setBranch_id(Integer branch_id) {
        this.branch_id = branch_id;
    }

    public String getChargeCode() {
        return chargeCode;
    }

    public void setChargeCode(String chargeCode) {
        this.chargeCode = chargeCode;
    }

    public String getChargeDesc() {
        return chargeDesc;
    }

    public void setChargeDesc(String chargeDesc) {
        this.chargeDesc = chargeDesc;
    }

    public Double getChargeRatePerc() {
        return chargeRatePerc;
    }

    public void setChargeRatePerc(Double chargeRatePerc) {
        this.chargeRatePerc = chargeRatePerc;
    }

    public Integer getCharge_branch_id() {
        return charge_branch_id;
    }

    public void setCharge_branch_id(Integer charge_branch_id) {
        this.charge_branch_id = charge_branch_id;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getIsActualOrPerc() {
        return isActualOrPerc;
    }

    public void setIsActualOrPerc(String isActualOrPerc) {
        this.isActualOrPerc = isActualOrPerc;
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

    public Integer getTaxcharge_oem_id() {
        return taxcharge_oem_id;
    }

    public void setTaxcharge_oem_id(Integer taxcharge_oem_id) {
        this.taxcharge_oem_id = taxcharge_oem_id;
    }
}
