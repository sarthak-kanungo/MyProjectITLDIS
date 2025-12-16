/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kundan.rastogi
 */
@Entity
@Table(name = "SW_warranty_claim")
@NamedQueries({
    @NamedQuery(name = "Warrantyclaim.findAll", query = "SELECT w FROM Warrantyclaim w"),
    @NamedQuery(name = "Warrantyclaim.findByWarrantyClaimNo", query = "SELECT w FROM Warrantyclaim w WHERE w.warrantyClaimNo = :warrantyClaimNo"),
    @NamedQuery(name = "Warrantyclaim.findByClaimDate", query = "SELECT w FROM Warrantyclaim w WHERE w.claimDate = :claimDate"),
    @NamedQuery(name = "Warrantyclaim.findByTmsRefNo", query = "SELECT w FROM Warrantyclaim w WHERE w.tmsRefNo = :tmsRefNo"),
    @NamedQuery(name = "Warrantyclaim.findByJobCardNo", query = "SELECT w FROM Warrantyclaim w WHERE w.jobCardNo = :jobCardNo"),
    @NamedQuery(name = "Warrantyclaim.findByDealerCode", query = "SELECT w FROM Warrantyclaim w WHERE w.dealerCode = :dealerCode"),
    @NamedQuery(name = "Warrantyclaim.findByVinno", query = "SELECT w FROM Warrantyclaim w WHERE w.vinno = :vinno"),
    @NamedQuery(name = "Warrantyclaim.findByEngineno", query = "SELECT w FROM Warrantyclaim w WHERE w.engineno = :engineno"),
    @NamedQuery(name = "Warrantyclaim.findByVinid", query = "SELECT w FROM Warrantyclaim w WHERE w.vinid = :vinid"),
    @NamedQuery(name = "Warrantyclaim.findByDeliverySaleDate", query = "SELECT w FROM Warrantyclaim w WHERE w.deliverySaleDate = :deliverySaleDate"),
    @NamedQuery(name = "Warrantyclaim.findByFailureDate", query = "SELECT w FROM Warrantyclaim w WHERE w.failureDate = :failureDate"),
    @NamedQuery(name = "Warrantyclaim.findByJobCardDate", query = "SELECT w FROM Warrantyclaim w WHERE w.jobCardDate = :jobCardDate"),
    @NamedQuery(name = "Warrantyclaim.findByReplacementDate", query = "SELECT w FROM Warrantyclaim w WHERE w.replacementDate = :replacementDate"),
    @NamedQuery(name = "Warrantyclaim.findByHmr", query = "SELECT w FROM Warrantyclaim w WHERE w.hmr = :hmr"),
    @NamedQuery(name = "Warrantyclaim.findByFailedPartsFileAttached1", query = "SELECT w FROM Warrantyclaim w WHERE w.failedPartsFileAttached1 = :failedPartsFileAttached1"),
    @NamedQuery(name = "Warrantyclaim.findByFailedPartsFileAttached2", query = "SELECT w FROM Warrantyclaim w WHERE w.failedPartsFileAttached2 = :failedPartsFileAttached2"),
    @NamedQuery(name = "Warrantyclaim.findByFailedPartsFileAttached3", query = "SELECT w FROM Warrantyclaim w WHERE w.failedPartsFileAttached3 = :failedPartsFileAttached3"),
    @NamedQuery(name = "Warrantyclaim.findByFailedPartsFileAttached4", query = "SELECT w FROM Warrantyclaim w WHERE w.failedPartsFileAttached4 = :failedPartsFileAttached4"),
    @NamedQuery(name = "Warrantyclaim.findByFailedPartsFileAttached5", query = "SELECT w FROM Warrantyclaim w WHERE w.failedPartsFileAttached5 = :failedPartsFileAttached5"),
    @NamedQuery(name = "Warrantyclaim.findByTotalAmount", query = "SELECT w FROM Warrantyclaim w WHERE w.totalAmount = :totalAmount"),
    @NamedQuery(name = "Warrantyclaim.findByLessAmount", query = "SELECT w FROM Warrantyclaim w WHERE w.lessAmount = :lessAmount"),
    @NamedQuery(name = "Warrantyclaim.findByCstvat", query = "SELECT w FROM Warrantyclaim w WHERE w.cstvat = :cstvat"),
    @NamedQuery(name = "Warrantyclaim.findByHandlingCharges", query = "SELECT w FROM Warrantyclaim w WHERE w.handlingCharges = :handlingCharges"),
    @NamedQuery(name = "Warrantyclaim.findByTotalClaimAmount", query = "SELECT w FROM Warrantyclaim w WHERE w.totalClaimAmount = :totalClaimAmount"),
    @NamedQuery(name = "Warrantyclaim.findByClaimStatus", query = "SELECT w FROM Warrantyclaim w WHERE w.claimStatus = :claimStatus"),
    @NamedQuery(name = "Warrantyclaim.findByApprovedAmount", query = "SELECT w FROM Warrantyclaim w WHERE w.approvedAmount = :approvedAmount"),
    @NamedQuery(name = "Warrantyclaim.findByApprovedLessAmount", query = "SELECT w FROM Warrantyclaim w WHERE w.approvedLessAmount = :approvedLessAmount"),
    @NamedQuery(name = "Warrantyclaim.findByApprovedCSTVAT", query = "SELECT w FROM Warrantyclaim w WHERE w.approvedCSTVAT = :approvedCSTVAT"),
    @NamedQuery(name = "Warrantyclaim.findByApprovedHandlingCharges", query = "SELECT w FROM Warrantyclaim w WHERE w.approvedHandlingCharges = :approvedHandlingCharges"),
    @NamedQuery(name = "Warrantyclaim.findByTotalApprovedAmount", query = "SELECT w FROM Warrantyclaim w WHERE w.totalApprovedAmount = :totalApprovedAmount"),
    @NamedQuery(name = "Warrantyclaim.findByCreatedBy", query = "SELECT w FROM Warrantyclaim w WHERE w.createdBy = :createdBy"),
    @NamedQuery(name = "Warrantyclaim.findByCreatedOn", query = "SELECT w FROM Warrantyclaim w WHERE w.createdOn = :createdOn"),
    @NamedQuery(name = "Warrantyclaim.findByLastModifiedBy", query = "SELECT w FROM Warrantyclaim w WHERE w.lastModifiedBy = :lastModifiedBy"),
    @NamedQuery(name = "Warrantyclaim.findByLastModifiedOn", query = "SELECT w FROM Warrantyclaim w WHERE w.lastModifiedOn = :lastModifiedOn"),
    @NamedQuery(name = "Warrantyclaim.findByIsServerSync", query = "SELECT w FROM Warrantyclaim w WHERE w.isServerSync = :isServerSync"),
    @NamedQuery(name = "Warrantyclaim.findByLastSyncDate", query = "SELECT w FROM Warrantyclaim w WHERE w.lastSyncDate = :lastSyncDate")})
public class Warrantyclaim implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "WarrantyClaimNo")
    private String warrantyClaimNo;
    @Column(name = "ClaimDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date claimDate;
    @Column(name = "TMS_REF_NO")
    private String tmsRefNo;
    @Basic(optional = false)
    @Column(name = "JobCardNo")
    private String jobCardNo;
    @Column(name = "DealerCode")
    private String dealerCode;
    @Column(name = "vinno")
    private String vinno;
    @Column(name = "engineno")
    private String engineno;
    @Basic(optional = false)
    @Column(name = "vinid")
    private String vinid;
    @Column(name = "DeliverySaleDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliverySaleDate;
    @Column(name = "FailureDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date failureDate;
    @Column(name = "JobCardDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jobCardDate;
    @Column(name = "ReplacementDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date replacementDate;
    @Column(name = "HMR")
    private Long hmr;
    @Column(name = "FailedPartsFileAttached1")
    private String failedPartsFileAttached1;
    @Column(name = "FailedPartsFileAttached2")
    private String failedPartsFileAttached2;
    @Column(name = "FailedPartsFileAttached3")
    private String failedPartsFileAttached3;
    @Column(name = "FailedPartsFileAttached4")
    private String failedPartsFileAttached4;
    @Column(name = "FailedPartsFileAttached5")
    private String failedPartsFileAttached5;
    @Column(name = "TotalAmount")
    private BigDecimal totalAmount;
    @Column(name = "LessAmount")
    private BigDecimal lessAmount;
    @Column(name = "CSTVAT")
    private BigDecimal cstvat;
    @Column(name = "HandlingCharges")
    private BigDecimal handlingCharges;
    @Column(name = "GSTOnHandling")
    private BigDecimal gstOnHandling;
    @Column(name = "InsuranceCharges")
    private BigDecimal insuranceCharges;
    @Column(name = "GSTOnInsurance")
    private BigDecimal gstOnInsurance;
    
    @Column(name = "ApprovedGSTOnHandling")
    private BigDecimal approvedgstOnHandling;
    @Column(name = "ApprovedInsuranceCharges")
    private BigDecimal approvedinsuranceCharges;
    @Column(name = "ApprovedGSTOnInsurance")
    private BigDecimal approvedgstOnInsurance;

    @Column(name = "TotalClaimAmount")
    private BigDecimal totalClaimAmount;
    @Column(name = "ClaimStatus")
    private String claimStatus;
    @Column(name = "ApprovedAmount")
    private BigDecimal approvedAmount;
    @Column(name = "ApprovedLessAmount")
    private BigDecimal approvedLessAmount;
    @Column(name = "ApprovedCSTVAT")
    private BigDecimal approvedCSTVAT;
    @Column(name = "ApprovedHandlingCharges")
    private BigDecimal approvedHandlingCharges;
    @Column(name = "TotalApprovedAmount")
    private BigDecimal totalApprovedAmount;
    @Column(name = "CreatedBy")
    private String createdBy;
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "LastModifiedBy")
    private String lastModifiedBy;
    @Column(name = "LastModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedOn;
    @Column(name = "Is_ServerSync")
    private Character isServerSync;
    @Column(name = "LastSyncDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSyncDate;
    @Column(name = "SAP_SyncStatus")
    private String sapSyncStatus;
    @Column(name = "SAP_SyncDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sapSyncDate;
    @Column(name = "SAP_CreditDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date sapCreditDate;
    @Column(name = "SAP_CreditNo")
    private String sapCreditNo;
    @Column(name = "SAP_CreditAmount")
    private BigDecimal sapCreditAmount;
    @Column(name = "SAP_WarrantyClaim")
    private String sapWarrantyClaim;
    @Column(name="approvedOn")
    private Date approvedOn;
    @Column(name="approvedBy")
    private String approvedBy;
    @Column(name="taxInvoiceStatus")
    private String taxInvoiceStatus;
    @Column(name="taxInvoiceStatusDate")
    private Date taxInvoiceStatusDate;
    @Column(name="uploadSignedpdfName")
    private String uploadSignedpdfName;
    @Column(name="uploadSignedpdfDate")
    private Date uploadSignedpdfDate;
    @Column(name="uploadSignedpdfStatus")
    private String uploadSignedpdfStatus;
    @Column(name="uuid")
    private String uuid;
    @Column(name="SIGN_STATUS")
    private String signStatus;


    public Warrantyclaim() {
    }

    public Warrantyclaim(String warrantyClaimNo) {
        this.warrantyClaimNo = warrantyClaimNo;
    }

    public Warrantyclaim(String warrantyClaimNo, String jobCardNo, String vinid) {
        this.warrantyClaimNo = warrantyClaimNo;
        this.jobCardNo = jobCardNo;
        this.vinid = vinid;
    }

    public BigDecimal getApprovedgstOnHandling() {
        return approvedgstOnHandling;
    }

    public void setApprovedgstOnHandling(BigDecimal approvedgstOnHandling) {
        this.approvedgstOnHandling = approvedgstOnHandling;
    }

    public BigDecimal getApprovedgstOnInsurance() {
        return approvedgstOnInsurance;
    }

    public void setApprovedgstOnInsurance(BigDecimal approvedgstOnInsurance) {
        this.approvedgstOnInsurance = approvedgstOnInsurance;
    }

    public BigDecimal getApprovedinsuranceCharges() {
        return approvedinsuranceCharges;
    }

    public void setApprovedinsuranceCharges(BigDecimal approvedinsuranceCharges) {
        this.approvedinsuranceCharges = approvedinsuranceCharges;
    }

    
    public BigDecimal getGstOnHandling() {
        return gstOnHandling;
    }

    public void setGstOnHandling(BigDecimal gstOnHandling) {
        this.gstOnHandling = gstOnHandling;
    }

    public BigDecimal getGstOnInsurance() {
        return gstOnInsurance;
    }

    public void setGstOnInsurance(BigDecimal gstOnInsurance) {
        this.gstOnInsurance = gstOnInsurance;
    }

    public BigDecimal getInsuranceCharges() {
        return insuranceCharges;
    }

    public void setInsuranceCharges(BigDecimal insuranceCharges) {
        this.insuranceCharges = insuranceCharges;
    }

    
    public String getWarrantyClaimNo() {
        return warrantyClaimNo;
    }

    public void setWarrantyClaimNo(String warrantyClaimNo) {
        this.warrantyClaimNo = warrantyClaimNo;
    }

    public Date getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(Date claimDate) {
        this.claimDate = claimDate;
    }

    public String getTmsRefNo() {
        return tmsRefNo;
    }

    public void setTmsRefNo(String tmsRefNo) {
        this.tmsRefNo = tmsRefNo;
    }

    public String getJobCardNo() {
        return jobCardNo;
    }

    public void setJobCardNo(String jobCardNo) {
        this.jobCardNo = jobCardNo;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getVinno() {
        return vinno;
    }

    public void setVinno(String vinno) {
        this.vinno = vinno;
    }

    public String getEngineno() {
        return engineno;
    }

    public void setEngineno(String engineno) {
        this.engineno = engineno;
    }

    public String getVinid() {
        return vinid;
    }

    public void setVinid(String vinid) {
        this.vinid = vinid;
    }

    public Date getDeliverySaleDate() {
        return deliverySaleDate;
    }

    public void setDeliverySaleDate(Date deliverySaleDate) {
        this.deliverySaleDate = deliverySaleDate;
    }

    public Date getFailureDate() {
        return failureDate;
    }

    public void setFailureDate(Date failureDate) {
        this.failureDate = failureDate;
    }

    public Date getJobCardDate() {
        return jobCardDate;
    }

    public void setJobCardDate(Date jobCardDate) {
        this.jobCardDate = jobCardDate;
    }

    public Date getReplacementDate() {
        return replacementDate;
    }

    public void setReplacementDate(Date replacementDate) {
        this.replacementDate = replacementDate;
    }

    public Long getHmr() {
        return hmr;
    }

    public void setHmr(Long hmr) {
        this.hmr = hmr;
    }

    public String getFailedPartsFileAttached1() {
		return failedPartsFileAttached1;
	}

	public void setFailedPartsFileAttached1(String failedPartsFileAttached1) {
		this.failedPartsFileAttached1 = failedPartsFileAttached1;
	}

	public String getFailedPartsFileAttached2() {
		return failedPartsFileAttached2;
	}

	public void setFailedPartsFileAttached2(String failedPartsFileAttached2) {
		this.failedPartsFileAttached2 = failedPartsFileAttached2;
	}

	public String getFailedPartsFileAttached3() {
		return failedPartsFileAttached3;
	}

	public void setFailedPartsFileAttached3(String failedPartsFileAttached3) {
		this.failedPartsFileAttached3 = failedPartsFileAttached3;
	}

	public String getFailedPartsFileAttached4() {
		return failedPartsFileAttached4;
	}

	public void setFailedPartsFileAttached4(String failedPartsFileAttached4) {
		this.failedPartsFileAttached4 = failedPartsFileAttached4;
	}

	public String getFailedPartsFileAttached5() {
		return failedPartsFileAttached5;
	}

	public void setFailedPartsFileAttached5(String failedPartsFileAttached5) {
		this.failedPartsFileAttached5 = failedPartsFileAttached5;
	}

	public BigDecimal getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }

    public BigDecimal getLessAmount() {
        return lessAmount;
    }

    public void setLessAmount(BigDecimal lessAmount) {
        this.lessAmount = lessAmount;
    }

    public BigDecimal getCstvat() {
        return cstvat;
    }

    public void setCstvat(BigDecimal cstvat) {
        this.cstvat = cstvat;
    }

    public BigDecimal getHandlingCharges() {
        return handlingCharges;
    }

    public void setHandlingCharges(BigDecimal handlingCharges) {
        this.handlingCharges = handlingCharges;
    }

    public BigDecimal getTotalClaimAmount() {
        return totalClaimAmount;
    }

    public void setTotalClaimAmount(BigDecimal totalClaimAmount) {
        this.totalClaimAmount = totalClaimAmount;
    }

    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

    public BigDecimal getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(BigDecimal approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public BigDecimal getApprovedLessAmount() {
        return approvedLessAmount;
    }

    public void setApprovedLessAmount(BigDecimal approvedLessAmount) {
        this.approvedLessAmount = approvedLessAmount;
    }

    public BigDecimal getApprovedCSTVAT() {
        return approvedCSTVAT;
    }

    public void setApprovedCSTVAT(BigDecimal approvedCSTVAT) {
        this.approvedCSTVAT = approvedCSTVAT;
    }

    public BigDecimal getApprovedHandlingCharges() {
        return approvedHandlingCharges;
    }

    public void setApprovedHandlingCharges(BigDecimal approvedHandlingCharges) {
        this.approvedHandlingCharges = approvedHandlingCharges;
    }

    public BigDecimal getTotalApprovedAmount() {
        return totalApprovedAmount;
    }

    public void setTotalApprovedAmount(BigDecimal totalApprovedAmount) {
        this.totalApprovedAmount = totalApprovedAmount;
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

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    public Character getIsServerSync() {
        return isServerSync;
    }

    public void setIsServerSync(Character isServerSync) {
        this.isServerSync = isServerSync;
    }

    public Date getLastSyncDate() {
        return lastSyncDate;
    }

    public void setLastSyncDate(Date lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }

    public Date getSapCreditDate() {
        return sapCreditDate;
    }

    public void setSapCreditDate(Date sapCreditDate) {
        this.sapCreditDate = sapCreditDate;
    }

    public String getSapCreditNo() {
        return sapCreditNo;
    }

    public void setSapCreditNo(String sapCreditNo) {
        this.sapCreditNo = sapCreditNo;
    }

    public Date getSapSyncDate() {
        return sapSyncDate;
    }

    public void setSapSyncDate(Date sapSyncDate) {
        this.sapSyncDate = sapSyncDate;
    }

    public String getSapSyncStatus() {
        return sapSyncStatus;
    }

    public void setSapSyncStatus(String sapSyncStatus) {
        this.sapSyncStatus = sapSyncStatus;
    }

    public BigDecimal getSapCreditAmount() {
        return sapCreditAmount;
    }

    public void setSapCreditAmount(BigDecimal sapCreditAmount) {
        this.sapCreditAmount = sapCreditAmount;
    }

    public String getSapWarrantyClaim() {
        return sapWarrantyClaim;
    }
    public void setSapWarrantyClaim(String sapWarrantyClaim) {
        this.sapWarrantyClaim = sapWarrantyClaim;
    }
    
       @Override
    public int hashCode() {
        int hash = 0;
        hash += (warrantyClaimNo != null ? warrantyClaimNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Warrantyclaim)) {
            return false;
        }
        Warrantyclaim other = (Warrantyclaim) object;
        if ((this.warrantyClaimNo == null && other.warrantyClaimNo != null) || (this.warrantyClaimNo != null && !this.warrantyClaimNo.equals(other.warrantyClaimNo))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Warrantyclaim [warrantyClaimNo=" + warrantyClaimNo + ", claimDate=" + claimDate + ", tmsRefNo="
				+ tmsRefNo + ", jobCardNo=" + jobCardNo + ", dealerCode=" + dealerCode + ", vinno=" + vinno
				+ ", engineno=" + engineno + ", vinid=" + vinid + ", deliverySaleDate=" + deliverySaleDate
				+ ", failureDate=" + failureDate + ", jobCardDate=" + jobCardDate + ", replacementDate="
				+ replacementDate + ", hmr=" + hmr + ", failedPartsFileAttached1=" + failedPartsFileAttached1
				+ ", failedPartsFileAttached2=" + failedPartsFileAttached2 + ", failedPartsFileAttached3="
				+ failedPartsFileAttached3 + ", failedPartsFileAttached4=" + failedPartsFileAttached4
				+ ", failedPartsFileAttached5=" + failedPartsFileAttached5 + ", totalAmount=" + totalAmount
				+ ", lessAmount=" + lessAmount + ", cstvat=" + cstvat + ", handlingCharges=" + handlingCharges
				+ ", gstOnHandling=" + gstOnHandling + ", insuranceCharges=" + insuranceCharges + ", gstOnInsurance="
				+ gstOnInsurance + ", approvedgstOnHandling=" + approvedgstOnHandling + ", approvedinsuranceCharges="
				+ approvedinsuranceCharges + ", approvedgstOnInsurance=" + approvedgstOnInsurance
				+ ", totalClaimAmount=" + totalClaimAmount + ", claimStatus=" + claimStatus + ", approvedAmount="
				+ approvedAmount + ", approvedLessAmount=" + approvedLessAmount + ", approvedCSTVAT=" + approvedCSTVAT
				+ ", approvedHandlingCharges=" + approvedHandlingCharges + ", totalApprovedAmount="
				+ totalApprovedAmount + ", createdBy=" + createdBy + ", createdOn=" + createdOn + ", lastModifiedBy="
				+ lastModifiedBy + ", lastModifiedOn=" + lastModifiedOn + ", isServerSync=" + isServerSync
				+ ", lastSyncDate=" + lastSyncDate + ", sapSyncStatus=" + sapSyncStatus + ", sapSyncDate=" + sapSyncDate
				+ ", sapCreditDate=" + sapCreditDate + ", sapCreditNo=" + sapCreditNo + ", sapCreditAmount="
				+ sapCreditAmount + ", sapWarrantyClaim=" + sapWarrantyClaim + ", approvedOn=" + approvedOn
				+ ", approvedBy=" + approvedBy + ", taxInvoiceStatus=" + taxInvoiceStatus + ", taxInvoiceStatusDate="
				+ taxInvoiceStatusDate + "]";
	}

	public Date getApprovedOn() {
		return approvedOn;
	}

	public void setApprovedOn(Date approvedOn) {
		this.approvedOn = approvedOn;
	}

	public String getApprovedBy() {
		return approvedBy;
	}

	public void setApprovedBy(String approvedBy) {
		this.approvedBy = approvedBy;
	}

	public String getTaxInvoiceStatus() {
		return taxInvoiceStatus;
	}

	public void setTaxInvoiceStatus(String taxInvoiceStatus) {
		this.taxInvoiceStatus = taxInvoiceStatus;
	}

	public Date getTaxInvoiceStatusDate() {
		return taxInvoiceStatusDate;
	}

	public void setTaxInvoiceStatusDate(Date taxInvoiceStatusDate) {
		this.taxInvoiceStatusDate = taxInvoiceStatusDate;
	}

	public String getUploadSignedpdfName() {
		return uploadSignedpdfName;
	}

	public void setUploadSignedpdfName(String uploadSignedpdfName) {
		this.uploadSignedpdfName = uploadSignedpdfName;
	}

	public Date getUploadSignedpdfDate() {
		return uploadSignedpdfDate;
	}

	public void setUploadSignedpdfDate(Date uploadSignedpdfDate) {
		this.uploadSignedpdfDate = uploadSignedpdfDate;
	}

	public String getUploadSignedpdfStatus() {
		return uploadSignedpdfStatus;
	}

	public void setUploadSignedpdfStatus(String uploadSignedpdfStatus) {
		this.uploadSignedpdfStatus = uploadSignedpdfStatus;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	
}
