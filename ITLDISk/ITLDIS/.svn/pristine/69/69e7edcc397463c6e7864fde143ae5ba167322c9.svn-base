/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "SW_jobspares_actualcharges")
@NamedQueries({
    @NamedQuery(name = "JobcardSparesActualcharges.findAll", query = "SELECT j FROM JobcardSparesActualcharges j"),
    @NamedQuery(name = "JobcardSparesActualcharges.findByJobSpareID", query = "SELECT j FROM JobcardSparesActualcharges j WHERE j.jobSpareID = :jobSpareID"),
    @NamedQuery(name = "JobcardSparesActualcharges.findByJobCardNo", query = "SELECT j FROM JobcardSparesActualcharges j WHERE j.jobCardNo = :jobCardNo"),
    @NamedQuery(name = "JobcardSparesActualcharges.findByPartCategory", query = "SELECT j FROM JobcardSparesActualcharges j WHERE j.partCategory = :partCategory"),
    @NamedQuery(name = "JobcardSparesActualcharges.findByPartNo", query = "SELECT j FROM JobcardSparesActualcharges j WHERE j.partNo = :partNo"),
    @NamedQuery(name = "JobcardSparesActualcharges.findByPartDesc", query = "SELECT j FROM JobcardSparesActualcharges j WHERE j.partDesc = :partDesc"),
    @NamedQuery(name = "JobcardSparesActualcharges.findByCmpNo", query = "SELECT j FROM JobcardSparesActualcharges j WHERE j.cmpNo = :cmpNo"),
    @NamedQuery(name = "JobcardSparesActualcharges.findByCausalOrConseq", query = "SELECT j FROM JobcardSparesActualcharges j WHERE j.causalOrConseq = :causalOrConseq"),
    @NamedQuery(name = "JobcardSparesActualcharges.findByUnit", query = "SELECT j FROM JobcardSparesActualcharges j WHERE j.unit = :unit"),
    @NamedQuery(name = "JobcardSparesActualcharges.findByUnitPrice", query = "SELECT j FROM JobcardSparesActualcharges j WHERE j.unitPrice = :unitPrice"),
    @NamedQuery(name = "JobcardSparesActualcharges.findByQty", query = "SELECT j FROM JobcardSparesActualcharges j WHERE j.qty = :qty"),
    @NamedQuery(name = "JobcardSparesActualcharges.findByAmount", query = "SELECT j FROM JobcardSparesActualcharges j WHERE j.amount = :amount"),
    @NamedQuery(name = "JobcardSparesActualcharges.findByBillID", query = "SELECT j FROM JobcardSparesActualcharges j WHERE j.billID = :billID"),
    @NamedQuery(name = "JobcardSparesActualcharges.findByDiscInPerc", query = "SELECT j FROM JobcardSparesActualcharges j WHERE j.discInPerc = :discInPerc"),
    @NamedQuery(name = "JobcardSparesActualcharges.findByFinalAmount", query = "SELECT j FROM JobcardSparesActualcharges j WHERE j.finalAmount = :finalAmount")})
public class JobcardSparesActualcharges implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Job_Spare_ID")
    private String jobSpareID;
    @Basic(optional = false)
    @Column(name = "JobCardNo")
    private String jobCardNo;
    @Column(name = "part_category")
    private String partCategory;
    @Basic(optional = false)
    @Column(name = "part_no")
    private String partNo;
    @Column(name = "part_desc")
    private String partDesc;
    @Column(name = "CMP_NO")
    private String cmpNo;
    @Column(name = "CausalOrConseq")
    private String causalOrConseq;
    @Column(name = "unit")
    private String unit;
    @Column(name = "unit_price")
    private Double unitPrice;
    @Column(name = "qty")
    private Double qty;
    @Column(name = "amount")
    private Double amount;
    @Column(name = "BillID")
    private Integer billID;
    @Column(name = "disc_in_perc")
    private Double discInPerc;
    @Column(name = "final_amount")
    private Double finalAmount;
    @Column(name = "modifiedPartsUsed")
    private String modifiedPartsUsed;
    @Column(name = "vendorName")
    private String vendorName;

    public JobcardSparesActualcharges() {
    }

    public JobcardSparesActualcharges(String jobSpareID) {
        this.jobSpareID = jobSpareID;
    }

    public JobcardSparesActualcharges(String jobSpareID, String jobCardNo, String partNo) {
        this.jobSpareID = jobSpareID;
        this.jobCardNo = jobCardNo;
        this.partNo = partNo;
    }

    public String getJobSpareID() {
        return jobSpareID;
    }

    public void setJobSpareID(String jobSpareID) {
        this.jobSpareID = jobSpareID;
    }

    public String getJobCardNo() {
        return jobCardNo;
    }

    public void setJobCardNo(String jobCardNo) {
        this.jobCardNo = jobCardNo;
    }

    public String getPartCategory() {
        return partCategory;
    }

    public void setPartCategory(String partCategory) {
        this.partCategory = partCategory;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getPartDesc() {
        return partDesc;
    }

    public void setPartDesc(String partDesc) {
        this.partDesc = partDesc;
    }

    public String getCmpNo() {
        return cmpNo;
    }

    public void setCmpNo(String cmpNo) {
        this.cmpNo = cmpNo;
    }

    public String getCausalOrConseq() {
        return causalOrConseq;
    }

    public void setCausalOrConseq(String causalOrConseq) {
        this.causalOrConseq = causalOrConseq;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public Double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Integer getBillID() {
        return billID;
    }

    public void setBillID(Integer billID) {
        this.billID = billID;
    }

    public Double getDiscInPerc() {
        return discInPerc;
    }

    public void setDiscInPerc(Double discInPerc) {
        this.discInPerc = discInPerc;
    }

    public Double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(Double finalAmount) {
        this.finalAmount = finalAmount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobSpareID != null ? jobSpareID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobcardSparesActualcharges)) {
            return false;
        }
        JobcardSparesActualcharges other = (JobcardSparesActualcharges) object;
        if ((this.jobSpareID == null && other.jobSpareID != null) || (this.jobSpareID != null && !this.jobSpareID.equals(other.jobSpareID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.JobcardSparesActualcharges[jobSpareID=" + jobSpareID + "]";
    }

	public String getModifiedPartsUsed() {
		return modifiedPartsUsed;
	}

	public void setModifiedPartsUsed(String modifiedPartsUsed) {
		this.modifiedPartsUsed = modifiedPartsUsed;
	}

	public String getVendorName() {
		return vendorName;
	}

	public void setVendorName(String vendorName) {
		this.vendorName = vendorName;
	}
    
    

}
