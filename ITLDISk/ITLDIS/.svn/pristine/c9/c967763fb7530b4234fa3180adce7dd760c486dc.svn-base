/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HibernateMapping;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author kundan.rastogi
 */
@Entity
@Table(name = "SW_warranty_claim_details")
@NamedQueries({
    @NamedQuery(name = "Warrantyclaimdetails.findAll", query = "SELECT w FROM Warrantyclaimdetails w"),
    @NamedQuery(name = "Warrantyclaimdetails.findByClaimSpareID", query = "SELECT w FROM Warrantyclaimdetails w WHERE w.claimSpareID = :claimSpareID"),
    @NamedQuery(name = "Warrantyclaimdetails.findByWarrantyClaimNo", query = "SELECT w FROM Warrantyclaimdetails w WHERE w.warrantyClaimNo = :warrantyClaimNo"),
    @NamedQuery(name = "Warrantyclaimdetails.findByPartCategory", query = "SELECT w FROM Warrantyclaimdetails w WHERE w.partCategory = :partCategory"),
    @NamedQuery(name = "Warrantyclaimdetails.findByPartNo", query = "SELECT w FROM Warrantyclaimdetails w WHERE w.partNo = :partNo"),
    @NamedQuery(name = "Warrantyclaimdetails.findByPartDesc", query = "SELECT w FROM Warrantyclaimdetails w WHERE w.partDesc = :partDesc"),
    @NamedQuery(name = "Warrantyclaimdetails.findByVendorCode", query = "SELECT w FROM Warrantyclaimdetails w WHERE w.vendorCode = :vendorCode"),
    @NamedQuery(name = "Warrantyclaimdetails.findByCmpNo", query = "SELECT w FROM Warrantyclaimdetails w WHERE w.cmpNo = :cmpNo"),
    @NamedQuery(name = "Warrantyclaimdetails.findByCausalOrConseq", query = "SELECT w FROM Warrantyclaimdetails w WHERE w.causalOrConseq = :causalOrConseq"),
    @NamedQuery(name = "Warrantyclaimdetails.findByUnitPrice", query = "SELECT w FROM Warrantyclaimdetails w WHERE w.unitPrice = :unitPrice"),
    @NamedQuery(name = "Warrantyclaimdetails.findByQty", query = "SELECT w FROM Warrantyclaimdetails w WHERE w.qty = :qty"),
    @NamedQuery(name = "Warrantyclaimdetails.findByClaimAmount", query = "SELECT w FROM Warrantyclaimdetails w WHERE w.claimAmount = :claimAmount"),
    @NamedQuery(name = "Warrantyclaimdetails.findByQtyApproved", query = "SELECT w FROM Warrantyclaimdetails w WHERE w.qtyApproved = :qtyApproved"),
    @NamedQuery(name = "Warrantyclaimdetails.findByQtyRejected", query = "SELECT w FROM Warrantyclaimdetails w WHERE w.qtyRejected = :qtyRejected"),
    @NamedQuery(name = "Warrantyclaimdetails.findByApprovedAmount", query = "SELECT w FROM Warrantyclaimdetails w WHERE w.approvedAmount = :approvedAmount"),
    @NamedQuery(name = "Warrantyclaimdetails.findByApprovalStatus", query = "SELECT w FROM Warrantyclaimdetails w WHERE w.approvalStatus = :approvalStatus"),
    @NamedQuery(name = "Warrantyclaimdetails.findByRejectionCode", query = "SELECT w FROM Warrantyclaimdetails w WHERE w.rejectionCode = :rejectionCode")
//@NamedQuery(name = "Warrantyclaimdetails.findByRemarks", query = "SELECT w FROM Warrantyclaimdetails w WHERE w.remarks = :remarks")
})
public class Warrantyclaimdetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ClaimSpareID")
    private String claimSpareID;
    @Basic(optional = false)
    @Column(name = "WarrantyClaimNo")
    private String warrantyClaimNo;
    @Column(name = "part_category")
    private String partCategory;
    @Basic(optional = false)
    @Column(name = "part_no")
    private String partNo;
    @Column(name = "part_desc")
    private String partDesc;
    @Column(name = "VendorCode")
    private String vendorCode;
    @Column(name = "CMP_NO")
    private String cmpNo;
    @Column(name = "CausalOrConseq")
    private String causalOrConseq;
    @Column(name = "unit_price")
    private BigDecimal unitPrice;
    @Column(name = "qty")
    private Double qty;
    @Column(name = "ClaimAmount")
    private Double claimAmount;
    @Column(name = "QtyApproved")
    private Double qtyApproved;
    @Column(name = "QtyRejected")
    private Double qtyRejected;
    @Column(name = "ApprovedAmount")
    private Double approvedAmount;
    @Column(name = "ApprovalStatus")
    private String approvalStatus;
    @Column(name = "RejectionCode")
    private String rejectionCode;
    @Column(name = "RejectionRemarks")
    private String rejectionRemarks;
    @Column(name = "Dispatch_status")
    private String dispatchStatus;
    @Column(name = "PackingNo")
    private String packingNo;
    @Column(name = "DispatchedQty")
    private Double dispatchedQty;
    @Column(name = "BoxNo")
    private String boxNo;
    @Column(name = "QtyReceived")
    private Double qtyReceived;
    @Column(name = "ScrapValue")
    private String scrapValue;
    @Column(name = "VendorCodeAdmin")
    private String vendorCodeAdmin;
    @Column(name = "HSNCode")
    private String hsnCode;
    @Column(name = "DealerDiscPercentage")
    private BigDecimal DealerDisc;


    @Column(name = "ClaimTaxableValue")
    private BigDecimal claimTaxableValue;
    @Column(name = "ClaimCGSTRate")
    private BigDecimal claimCGSTRate;
    @Column(name = "ClaimCGSTAmt")
    private BigDecimal claimCGSTAmt;
    @Column(name = "ClaimSGSTRate")
    private BigDecimal claimSGSTRate;
    @Column(name = "ClaimSGSTAmt")
    private BigDecimal claimSGSTAmt;
    @Column(name = "ClaimUGSTRate")
    private BigDecimal claimUGSTRate;
    @Column(name = "ClaimUGSTAmt")
    private BigDecimal claimUGSTAmt;
    @Column(name = "ClaimIGSTRate")
    private BigDecimal claimIGSTRate;
    @Column(name = "ClaimIGSTAmt")
    private BigDecimal claimIGSTAmt;
    @Column(name = "TotalClaimValue")
    private BigDecimal totalClaimValue;




    @Column(name = "ApprovedTaxableValue")
    private BigDecimal approvedTaxableValue;
    @Column(name = "ApprovedCGSTRate")
    private BigDecimal approvedCGSTRate;
    @Column(name = "ApprovedCGSTAmt")
    private BigDecimal approvedCGSTAmt;
    @Column(name = "ApprovedSGSTRate")
    private BigDecimal approvedSGSTRate;
    @Column(name = "ApprovedSGSTAmt")
    private BigDecimal approvedSGSTAmt;
    @Column(name = "ApprovedUGSTRate")
    private BigDecimal approvedUGSTRate;
    @Column(name = "ApprovedUGSTAmt")
    private BigDecimal approvedUGSTAmt;
    @Column(name = "ApprovedIGSTRate")
    private BigDecimal approvedIGSTRate;
    @Column(name = "ApprovedIGSTAmt")
    private BigDecimal approvedIGSTAmt;
    @Column(name = "TotalApprovedValue")
    private BigDecimal TotalApprovedValue;
    @Column(name = "partNoFailed")
    private String partNoFailed;

    public Warrantyclaimdetails() {
    }

    public Warrantyclaimdetails(String claimSpareID) {
        this.claimSpareID = claimSpareID;
    }

    public Warrantyclaimdetails(String claimSpareID, String warrantyClaimNo, String partNo) {
        this.claimSpareID = claimSpareID;
        this.warrantyClaimNo = warrantyClaimNo;
        this.partNo = partNo;
    }

    public BigDecimal getDealerDisc() {
        return DealerDisc;
    }

    public void setDealerDisc(BigDecimal DealerDisc) {
        this.DealerDisc = DealerDisc;
    }

    public BigDecimal getTotalApprovedValue() {
        return TotalApprovedValue;
    }

    public void setTotalApprovedValue(BigDecimal TotalApprovedValue) {
        this.TotalApprovedValue = TotalApprovedValue;
    }

    public BigDecimal getApprovedCGSTAmt() {
        return approvedCGSTAmt;
    }

    public void setApprovedCGSTAmt(BigDecimal approvedCGSTAmt) {
        this.approvedCGSTAmt = approvedCGSTAmt;
    }

    public BigDecimal getApprovedCGSTRate() {
        return approvedCGSTRate;
    }

    public void setApprovedCGSTRate(BigDecimal approvedCGSTRate) {
        this.approvedCGSTRate = approvedCGSTRate;
    }

    public BigDecimal getApprovedIGSTAmt() {
        return approvedIGSTAmt;
    }

    public void setApprovedIGSTAmt(BigDecimal approvedIGSTAmt) {
        this.approvedIGSTAmt = approvedIGSTAmt;
    }

    public BigDecimal getApprovedIGSTRate() {
        return approvedIGSTRate;
    }

    public void setApprovedIGSTRate(BigDecimal approvedIGSTRate) {
        this.approvedIGSTRate = approvedIGSTRate;
    }

    public BigDecimal getApprovedSGSTAmt() {
        return approvedSGSTAmt;
    }

    public void setApprovedSGSTAmt(BigDecimal approvedSGSTAmt) {
        this.approvedSGSTAmt = approvedSGSTAmt;
    }

    public BigDecimal getApprovedSGSTRate() {
        return approvedSGSTRate;
    }

    public void setApprovedSGSTRate(BigDecimal approvedSGSTRate) {
        this.approvedSGSTRate = approvedSGSTRate;
    }

    public BigDecimal getApprovedTaxableValue() {
        return approvedTaxableValue;
    }

    public void setApprovedTaxableValue(BigDecimal approvedTaxableValue) {
        this.approvedTaxableValue = approvedTaxableValue;
    }

    public BigDecimal getApprovedUGSTAmt() {
        return approvedUGSTAmt;
    }

    public void setApprovedUGSTAmt(BigDecimal approvedUGSTAmt) {
        this.approvedUGSTAmt = approvedUGSTAmt;
    }

    public BigDecimal getApprovedUGSTRate() {
        return approvedUGSTRate;
    }

    public void setApprovedUGSTRate(BigDecimal approvedUGSTRate) {
        this.approvedUGSTRate = approvedUGSTRate;
    }

    public BigDecimal getClaimCGSTAmt() {
        return claimCGSTAmt;
    }

    public void setClaimCGSTAmt(BigDecimal claimCGSTAmt) {
        this.claimCGSTAmt = claimCGSTAmt;
    }

    public BigDecimal getClaimCGSTRate() {
        return claimCGSTRate;
    }

    public void setClaimCGSTRate(BigDecimal claimCGSTRate) {
        this.claimCGSTRate = claimCGSTRate;
    }

    public BigDecimal getClaimIGSTAmt() {
        return claimIGSTAmt;
    }

    public void setClaimIGSTAmt(BigDecimal claimIGSTAmt) {
        this.claimIGSTAmt = claimIGSTAmt;
    }

    public BigDecimal getClaimIGSTRate() {
        return claimIGSTRate;
    }

    public void setClaimIGSTRate(BigDecimal claimIGSTRate) {
        this.claimIGSTRate = claimIGSTRate;
    }

    public BigDecimal getClaimSGSTAmt() {
        return claimSGSTAmt;
    }

    public void setClaimSGSTAmt(BigDecimal claimSGSTAmt) {
        this.claimSGSTAmt = claimSGSTAmt;
    }

    public BigDecimal getClaimSGSTRate() {
        return claimSGSTRate;
    }

    public void setClaimSGSTRate(BigDecimal claimSGSTRate) {
        this.claimSGSTRate = claimSGSTRate;
    }

    public BigDecimal getClaimTaxableValue() {
        return claimTaxableValue;
    }

    public void setClaimTaxableValue(BigDecimal claimTaxableValue) {
        this.claimTaxableValue = claimTaxableValue;
    }

    public BigDecimal getClaimUGSTAmt() {
        return claimUGSTAmt;
    }

    public void setClaimUGSTAmt(BigDecimal claimUGSTAmt) {
        this.claimUGSTAmt = claimUGSTAmt;
    }

    public BigDecimal getClaimUGSTRate() {
        return claimUGSTRate;
    }

    public void setClaimUGSTRate(BigDecimal claimUGSTRate) {
        this.claimUGSTRate = claimUGSTRate;
    }

    public BigDecimal getTotalClaimValue() {
        return totalClaimValue;
    }

    public void setTotalClaimValue(BigDecimal totalClaimValue) {
        this.totalClaimValue = totalClaimValue;
    }

    

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }


    public Double getQtyReceived() {
        return qtyReceived;
    }

    public void setQtyReceived(Double qtyReceived) {
        this.qtyReceived = qtyReceived;
    }


    public String getClaimSpareID() {
        return claimSpareID;
    }

    public void setClaimSpareID(String claimSpareID) {
        this.claimSpareID = claimSpareID;
    }

    public String getWarrantyClaimNo() {
        return warrantyClaimNo;
    }

    public void setWarrantyClaimNo(String warrantyClaimNo) {
        this.warrantyClaimNo = warrantyClaimNo;
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

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
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

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public Double getQty() {
        return qty;
    }

    public void setQty(Double qty) {
        this.qty = qty;
    }

    public Double getClaimAmount() {
        return claimAmount;
    }

    public void setClaimAmount(Double claimAmount) {
        this.claimAmount = claimAmount;
    }

    public Double getQtyApproved() {
        return qtyApproved;
    }

    public void setQtyApproved(Double qtyApproved) {
        this.qtyApproved = qtyApproved;
    }

    public Double getQtyRejected() {
        return qtyRejected;
    }

    public void setQtyRejected(Double qtyRejected) {
        this.qtyRejected = qtyRejected;
    }

    public Double getApprovedAmount() {
        return approvedAmount;
    }

    public void setApprovedAmount(Double approvedAmount) {
        this.approvedAmount = approvedAmount;
    }

    public String getApprovalStatus() {
        return approvalStatus;
    }

    public void setApprovalStatus(String approvalStatus) {
        this.approvalStatus = approvalStatus;
    }

    public String getRejectionCode() {
        return rejectionCode;
    }

    public void setRejectionCode(String rejectionCode) {
        this.rejectionCode = rejectionCode;
    }

    public String getRejectionRemarks() {
        return rejectionRemarks;
    }

    public void setRejectionRemarks(String rejectionRemarks) {
        this.rejectionRemarks = rejectionRemarks;
    }

    public String getDispatchStatus() {
        return dispatchStatus;
    }

    public void setDispatchStatus(String dispatchStatus) {
        this.dispatchStatus = dispatchStatus;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public Double getDispatchedQty() {
        return dispatchedQty;
    }

    public void setDispatchedQty(Double dispatchedQty) {
        this.dispatchedQty = dispatchedQty;
    }

    public String getPackingNo() {
        return packingNo;
    }

    public void setPackingNo(String packingNo) {
        this.packingNo = packingNo;
    }

    public String getScrapValue() {
        return scrapValue;
    }

    public void setScrapValue(String scrapValue) {
        this.scrapValue = scrapValue;
    }

    public String getVendorCodeAdmin() {
        return vendorCodeAdmin;
    }

    public void setVendorCodeAdmin(String vendorCodeAdmin) {
        this.vendorCodeAdmin = vendorCodeAdmin;
    }
    
    public String getPartNoFailed() {
		return partNoFailed;
	}

	public void setPartNoFailed(String partNoFailed) {
		this.partNoFailed = partNoFailed;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (claimSpareID != null ? claimSpareID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Warrantyclaimdetails)) {
            return false;
        }
        Warrantyclaimdetails other = (Warrantyclaimdetails) object;
        if ((this.claimSpareID == null && other.claimSpareID != null) || (this.claimSpareID != null && !this.claimSpareID.equals(other.claimSpareID))) {
            return false;
        }
        return true;
    }

	@Override
	public String toString() {
		return "Warrantyclaimdetails [claimSpareID=" + claimSpareID + ", warrantyClaimNo=" + warrantyClaimNo
				+ ", partCategory=" + partCategory + ", partNo=" + partNo + ", partDesc=" + partDesc + ", vendorCode="
				+ vendorCode + ", cmpNo=" + cmpNo + ", causalOrConseq=" + causalOrConseq + ", unitPrice=" + unitPrice
				+ ", qty=" + qty + ", claimAmount=" + claimAmount + ", qtyApproved=" + qtyApproved + ", qtyRejected="
				+ qtyRejected + ", approvedAmount=" + approvedAmount + ", approvalStatus=" + approvalStatus
				+ ", rejectionCode=" + rejectionCode + ", rejectionRemarks=" + rejectionRemarks + ", dispatchStatus="
				+ dispatchStatus + ", packingNo=" + packingNo + ", dispatchedQty=" + dispatchedQty + ", boxNo=" + boxNo
				+ ", qtyReceived=" + qtyReceived + ", scrapValue=" + scrapValue + ", vendorCodeAdmin=" + vendorCodeAdmin
				+ ", hsnCode=" + hsnCode + ", DealerDisc=" + DealerDisc + ", claimTaxableValue=" + claimTaxableValue
				+ ", claimCGSTRate=" + claimCGSTRate + ", claimCGSTAmt=" + claimCGSTAmt + ", claimSGSTRate="
				+ claimSGSTRate + ", claimSGSTAmt=" + claimSGSTAmt + ", claimUGSTRate=" + claimUGSTRate
				+ ", claimUGSTAmt=" + claimUGSTAmt + ", claimIGSTRate=" + claimIGSTRate + ", claimIGSTAmt="
				+ claimIGSTAmt + ", totalClaimValue=" + totalClaimValue + ", approvedTaxableValue="
				+ approvedTaxableValue + ", approvedCGSTRate=" + approvedCGSTRate + ", approvedCGSTAmt="
				+ approvedCGSTAmt + ", approvedSGSTRate=" + approvedSGSTRate + ", approvedSGSTAmt=" + approvedSGSTAmt
				+ ", approvedUGSTRate=" + approvedUGSTRate + ", approvedUGSTAmt=" + approvedUGSTAmt
				+ ", approvedIGSTRate=" + approvedIGSTRate + ", approvedIGSTAmt=" + approvedIGSTAmt
				+ ", TotalApprovedValue=" + TotalApprovedValue + ", partNoFailed=" + partNoFailed + "]";
	}
   
}
