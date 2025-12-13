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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "SW_pdi_details")

public class PdiDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PDI_No")
    private String pDINo;
    @Column(name = "Dealercode")
    private String dealercode;
//    @Column(name = "DealerName")
//    private String dealerName;
//    @Column(name = "Dealer_location")
//    private String dealerlocation;
    @Basic(optional = false)
    @Column(name = "vinNo")
    private String vinNo;
    @Column(name = "EngineNo")
    private String engineNo;
    @Column(name = "ITL_InvoiceDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;
    @Column(name = "PDI_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pDIDate;
    @Column(name = "TractorReceivedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tractorReceivedDate;
    @Column(name = "Is_ServerSync")
    private Character isServerSync;
    @Column(name = "LastSyncDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSyncDate;
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
    @JoinColumn(name = "vinid", referencedColumnName = "VINID")
    @ManyToOne(optional = false)
    private Vehicledetails vinid;
    @Column(name = "ITL_InvoiceNo")
    private String itlInvoiceNo;
    @Column(name = "Remarks")
    private String remarks;
    @Column(name = "isTMSSync")
    private Character isTMSSync;
    @Column(name = "TMSSyncDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date tmsSyncDate;
    @Column(name = "Ref_PDI_No")
    private String ref_pdi_no;


     @JoinColumn(name = "MechanicDealerKey", referencedColumnName = "MechanicDealerKey")
    @ManyToOne
    private MSWDmechanicmaster mechanicDealerKey;


    public PdiDetails() {
    }

    public PdiDetails(String pDINo) {
        this.pDINo = pDINo;
    }

    public PdiDetails(String pDINo, String vinNo) {
        this.pDINo = pDINo;
        this.vinNo = vinNo;
    }

    public String getPDINo() {
        return pDINo;
    }

    public void setPDINo(String pDINo) {
        this.pDINo = pDINo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getItlInvoiceNo() {
        return itlInvoiceNo;
    }

    public void setItlInvoiceNo(String itlInvoiceNo) {
        this.itlInvoiceNo = itlInvoiceNo;
    }

    public String getDealercode() {
        return dealercode;
    }

    public void setDealercode(String dealercode) {
        this.dealercode = dealercode;
    }

    public String getVinNo() {
        return vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getPDIDate() {
        return pDIDate;
    }

    public void setPDIDate(Date pDIDate) {
        this.pDIDate = pDIDate;
    }

    public Date getTractorReceivedDate() {
        return tractorReceivedDate;
    }

    public void setTractorReceivedDate(Date tractorReceivedDate) {
        this.tractorReceivedDate = tractorReceivedDate;
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

    public Vehicledetails getVinid() {
        return vinid;
    }

    public void setVinid(Vehicledetails vinid) {
        this.vinid = vinid;
    }

    public Character getIsTMSSync() {
        return isTMSSync;
    }

    public void setIsTMSSync(Character isTMSSync) {
        this.isTMSSync = isTMSSync;
    }

    public Date getTmsSyncDate() {
        return tmsSyncDate;
    }

    public void setTmsSyncDate(Date tmsSyncDate) {
        this.tmsSyncDate = tmsSyncDate;
    }

    public MSWDmechanicmaster getMechanicDealerKey() {
        return mechanicDealerKey;
    }

    public void setMechanicDealerKey(MSWDmechanicmaster mechanicDealerKey) {
        this.mechanicDealerKey = mechanicDealerKey;
    }

    public String getRef_pdi_no() {
        return ref_pdi_no;
    }

    public void setRef_pdi_no(String ref_pdi_no) {
        this.ref_pdi_no = ref_pdi_no;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pDINo != null ? pDINo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PdiDetails)) {
            return false;
        }
        PdiDetails other = (PdiDetails) object;
        if ((this.pDINo == null && other.pDINo != null) || (this.pDINo != null && !this.pDINo.equals(other.pDINo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.PdiDetails[pDINo=" + pDINo + "]";
    }

}
