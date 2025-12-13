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
@Table(name = "SW_warranty_packing_master")
@NamedQueries({
    @NamedQuery(name = "SWwarrantypackingmaster.findAll", query = "SELECT s FROM SWwarrantypackingmaster s"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByPackingNo", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.packingNo = :packingNo"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByDealerCode", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.dealerCode = :dealerCode"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByPackingDate", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.packingDate = :packingDate"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByPackingRemarks", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.packingRemarks = :packingRemarks"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByPackingStatus", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.packingStatus = :packingStatus"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByPartsDispatchedFor", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.partsDispatchedFor = :partsDispatchedFor"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByDispatchedThrough", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.dispatchedThrough = :dispatchedThrough"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByDispatchedDate", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.dispatchedDate = :dispatchedDate"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByCourierNo", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.courierNo = :courierNo"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByCourierName", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.courierName = :courierName"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByCourierCopyFile", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.courierCopyFile = :courierCopyFile"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByCreatedBy", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.createdBy = :createdBy"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByCreatedOn", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.createdOn = :createdOn"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByReceivedBy", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.receivedBy = :receivedBy"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByReceivedOn", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.receivedOn = :receivedOn"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByReceiverRemarks", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.receiverRemarks = :receiverRemarks"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByLastModifiedBy", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.lastModifiedBy = :lastModifiedBy"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByLastModifiedOn", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.lastModifiedOn = :lastModifiedOn"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByIsServerSync", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.isServerSync = :isServerSync"),
    @NamedQuery(name = "SWwarrantypackingmaster.findByLastSyncDate", query = "SELECT s FROM SWwarrantypackingmaster s WHERE s.lastSyncDate = :lastSyncDate")})
public class SWwarrantypackingmaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PackingNo")
    private String packingNo;
    @Basic(optional = false)
    @Column(name = "DealerCode")
    private String dealerCode;
    @Basic(optional = false)
    @Column(name = "PackingDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date packingDate;
    @Column(name = "PackingRemarks")
    private String packingRemarks;
    @Basic(optional = false)
    @Column(name = "PackingStatus")
    private String packingStatus;
    @Column(name = "PartsDispatchedFor")   ///
    private String partsDispatchedFor;
    @Column(name = "DispatchedThrough")
    private String dispatchedThrough;
    @Column(name = "DispatchedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dispatchedDate;
    @Column(name = "CourierNo")
    private String courierNo;
    @Column(name = "CourierName")
    private String courierName;
    @Column(name = "CourierCopyFile")
    private String courierCopyFile;
    @Basic(optional = false)
    @Column(name = "CreatedBy")
    private String createdBy;
    @Basic(optional = false)
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "ReceivedBy")
    private String receivedBy;
    @Column(name = "ReceivedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receivedOn;
    @Column(name = "ReceiverRemarks")
    private String receiverRemarks;
    @Column(name = "LastModifiedBy")
    private String lastModifiedBy;
    @Column(name = "LastModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedOn;
    @Basic(optional = false)
    @Column(name = "Is_ServerSync")
    private char isServerSync;
    @Column(name = "LastSyncDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSyncDate;
    @Column(name = "StoreNoOfPackages ")
    private Integer storeNoOfPackages;
    @Column(name = "DeliveryChallan_EwayBill")
    private String DeliveryChallanForEwayBill;
    @Column(name="taxInvoiceFile")
    private String taxInvoiceFile;
    @Column(name="taxInvoiceStatus")
    private String taxInvoiceStatus;
    @Column(name="taxInvoiceAcknowledgeDate")
    private Date taxInvoiceAcknowledgeDate;
    @Column(name="taxInvoiceFileDate")
    private Date taxInvoiceFileDate;

    public SWwarrantypackingmaster() {
    }

    public SWwarrantypackingmaster(String packingNo) {
        this.packingNo = packingNo;
    }

    public SWwarrantypackingmaster(String packingNo, String dealerCode, Date packingDate, String packingStatus, String createdBy, Date createdOn, char isServerSync,String DeliveryChallanForEwayBill) {
        this.packingNo = packingNo;
        this.dealerCode = dealerCode;
        this.packingDate = packingDate;
        this.packingStatus = packingStatus;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.isServerSync = isServerSync;
        this.DeliveryChallanForEwayBill = DeliveryChallanForEwayBill;
    }

    public Integer getStoreNoOfPackages() {
        return storeNoOfPackages;
    }

    public void setStoreNoOfPackages(Integer storeNoOfPackages) {
        this.storeNoOfPackages = storeNoOfPackages;
    }

  
    public String getPackingNo() {
        return packingNo;
    }

    public void setPackingNo(String packingNo) {
        this.packingNo = packingNo;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public Date getPackingDate() {
        return packingDate;
    }

    public void setPackingDate(Date packingDate) {
        this.packingDate = packingDate;
    }

    public String getPackingRemarks() {
        return packingRemarks;
    }

    public void setPackingRemarks(String packingRemarks) {
        this.packingRemarks = packingRemarks;
    }

    public String getPackingStatus() {
        return packingStatus;
    }

    public void setPackingStatus(String packingStatus) {
        this.packingStatus = packingStatus;
    }

    public String getPartsDispatchedFor() {
        return partsDispatchedFor;
    }

    public void setPartsDispatchedFor(String partsDispatchedFor) {
        this.partsDispatchedFor = partsDispatchedFor;
    }

    public String getDispatchedThrough() {
        return dispatchedThrough;
    }

    public void setDispatchedThrough(String dispatchedThrough) {
        this.dispatchedThrough = dispatchedThrough;
    }

    public Date getDispatchedDate() {
        return dispatchedDate;
    }

    public void setDispatchedDate(Date dispatchedDate) {
        this.dispatchedDate = dispatchedDate;
    }

    public String getCourierNo() {
        return courierNo;
    }

    public void setCourierNo(String courierNo) {
        this.courierNo = courierNo;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public String getCourierCopyFile() {
        return courierCopyFile;
    }

    public void setCourierCopyFile(String courierCopyFile) {
        this.courierCopyFile = courierCopyFile;
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

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public Date getReceivedOn() {
        return receivedOn;
    }

    public void setReceivedOn(Date receivedOn) {
        this.receivedOn = receivedOn;
    }

    public String getReceiverRemarks() {
        return receiverRemarks;
    }

    public void setReceiverRemarks(String receiverRemarks) {
        this.receiverRemarks = receiverRemarks;
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

    public char getIsServerSync() {
        return isServerSync;
    }

    public void setIsServerSync(char isServerSync) {
        this.isServerSync = isServerSync;
    }

    public Date getLastSyncDate() {
        return lastSyncDate;
    }

    public void setLastSyncDate(Date lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }
    
    public String getDeliveryChallanForEwayBill() {
		return DeliveryChallanForEwayBill;
	}

	public void setDeliveryChallanForEwayBill(String deliveryChallanForEwayBill) {
		DeliveryChallanForEwayBill = deliveryChallanForEwayBill;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (packingNo != null ? packingNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SWwarrantypackingmaster)) {
            return false;
        }
        SWwarrantypackingmaster other = (SWwarrantypackingmaster) object;
        if ((this.packingNo == null && other.packingNo != null) || (this.packingNo != null && !this.packingNo.equals(other.packingNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SWwarrantypackingmaster[packingNo=" + packingNo + "]";
    }

	public String getTaxInvoiceFile() {
		return taxInvoiceFile;
	}

	public void setTaxInvoiceFile(String taxInvoiceFile) {
		this.taxInvoiceFile = taxInvoiceFile;
	}

	public String getTaxInvoiceStatus() {
		return taxInvoiceStatus;
	}

	public void setTaxInvoiceStatus(String taxInvoiceStatus) {
		this.taxInvoiceStatus = taxInvoiceStatus;
	}

	public Date getTaxInvoiceAcknowledgeDate() {
		return taxInvoiceAcknowledgeDate;
	}

	public void setTaxInvoiceAcknowledgeDate(Date taxInvoiceAcknowledgeDate) {
		this.taxInvoiceAcknowledgeDate = taxInvoiceAcknowledgeDate;
	}

	public Date getTaxInvoiceFileDate() {
		return taxInvoiceFileDate;
	}

	public void setTaxInvoiceFileDate(Date taxInvoiceFileDate) {
		this.taxInvoiceFileDate = taxInvoiceFileDate;
	}
	
	
}
