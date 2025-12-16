/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "SP_ORDER_DETAILS")
@NamedQueries({
    @NamedQuery(name = "SpOrderDetails.findAll", query = "SELECT s FROM SpOrderDetails s"),
    @NamedQuery(name = "SpOrderDetails.findByCustPoNo", query = "SELECT s FROM SpOrderDetails s WHERE s.spOrderDetailsPK.custPoNo = :custPoNo"),
    @NamedQuery(name = "SpOrderDetails.findByPositionNo", query = "SELECT s FROM SpOrderDetails s WHERE s.spOrderDetailsPK.positionNo = :positionNo"),
    @NamedQuery(name = "SpOrderDetails.findByPartNo", query = "SELECT s FROM SpOrderDetails s WHERE s.spOrderDetailsPK.partNo = :partNo"),
    @NamedQuery(name = "SpOrderDetails.findByQty", query = "SELECT s FROM SpOrderDetails s WHERE s.qty = :qty"),
    @NamedQuery(name = "SpOrderDetails.findByPrice", query = "SELECT s FROM SpOrderDetails s WHERE s.price = :price"),
    @NamedQuery(name = "SpOrderDetails.findByStatus", query = "SELECT s FROM SpOrderDetails s WHERE s.status = :status"),
    @NamedQuery(name = "SpOrderDetails.findByErpUpload", query = "SELECT s FROM SpOrderDetails s WHERE s.erpUpload = :erpUpload"),
    @NamedQuery(name = "SpOrderDetails.findByErpPartOrderNo", query = "SELECT s FROM SpOrderDetails s WHERE s.erpPartOrderNo = :erpPartOrderNo"),
    @NamedQuery(name = "SpOrderDetails.findByErpBookedPart", query = "SELECT s FROM SpOrderDetails s WHERE s.erpBookedPart = :erpBookedPart"),
    @NamedQuery(name = "SpOrderDetails.findByErpBookedQty", query = "SELECT s FROM SpOrderDetails s WHERE s.erpBookedQty = :erpBookedQty"),
    @NamedQuery(name = "SpOrderDetails.findByErpPendingQty", query = "SELECT s FROM SpOrderDetails s WHERE s.erpPendingQty = :erpPendingQty"),
    @NamedQuery(name = "SpOrderDetails.findByErpLastUpdatedOn", query = "SELECT s FROM SpOrderDetails s WHERE s.erpLastUpdatedOn = :erpLastUpdatedOn"),
    @NamedQuery(name = "SpOrderDetails.findByIsServerSync", query = "SELECT s FROM SpOrderDetails s WHERE s.isServerSync = :isServerSync"),
    @NamedQuery(name = "SpOrderDetails.findByLastSyncDate", query = "SELECT s FROM SpOrderDetails s WHERE s.lastSyncDate = :lastSyncDate"),
    @NamedQuery(name = "SpOrderDetails.findByColorId", query = "SELECT s FROM SpOrderDetails s WHERE s.colorId = :colorId")})
public class SpOrderDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SpOrderDetailsPK spOrderDetailsPK;
    @Basic(optional = false)
    @Column(name = "QTY")
    private int qty;
    @Basic(optional = false)
    @Column(name = "PRICE")
    private double price;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "ERP_UPLOAD")
    private String erpUpload;
    @Column(name = "ERP_PART_ORDER_NO")
    private String erpPartOrderNo;
    @Column(name = "ERP_BOOKED_PART")
    private String erpBookedPart;
    @Column(name = "ERP_BOOKED_QTY")
    private Integer erpBookedQty;
    @Column(name = "ERP_PENDING_QTY")
    private Integer erpPendingQty;
    @Column(name = "ERP_LAST_UPDATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date erpLastUpdatedOn;
    @Column(name = "Is_ServerSync")
    private Character isServerSync;
    @Column(name = "LastSyncDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSyncDate;
    @Column(name = "COLOR_ID")
    private Integer colorId;
    @JoinColumn(name = "CUST_PO_NO", referencedColumnName = "CUST_PO_NO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SpOrderMaster spOrderMaster;

    public SpOrderDetails() {
    }

    public SpOrderDetails(SpOrderDetailsPK spOrderDetailsPK) {
        this.spOrderDetailsPK = spOrderDetailsPK;
    }

    public SpOrderDetails(SpOrderDetailsPK spOrderDetailsPK, int qty, double price, String status) {
        this.spOrderDetailsPK = spOrderDetailsPK;
        this.qty = qty;
        this.price = price;
        this.status = status;
    }

    public SpOrderDetails(String custPoNo, int positionNo, String partNo) {
        this.spOrderDetailsPK = new SpOrderDetailsPK(custPoNo, positionNo, partNo);
    }

    public SpOrderDetailsPK getSpOrderDetailsPK() {
        return spOrderDetailsPK;
    }

    public void setSpOrderDetailsPK(SpOrderDetailsPK spOrderDetailsPK) {
        this.spOrderDetailsPK = spOrderDetailsPK;
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getErpUpload() {
        return erpUpload;
    }

    public void setErpUpload(String erpUpload) {
        this.erpUpload = erpUpload;
    }

    public String getErpPartOrderNo() {
        return erpPartOrderNo;
    }

    public void setErpPartOrderNo(String erpPartOrderNo) {
        this.erpPartOrderNo = erpPartOrderNo;
    }

    public String getErpBookedPart() {
        return erpBookedPart;
    }

    public void setErpBookedPart(String erpBookedPart) {
        this.erpBookedPart = erpBookedPart;
    }

    public Integer getErpBookedQty() {
        return erpBookedQty;
    }

    public void setErpBookedQty(Integer erpBookedQty) {
        this.erpBookedQty = erpBookedQty;
    }

    public Integer getErpPendingQty() {
        return erpPendingQty;
    }

    public void setErpPendingQty(Integer erpPendingQty) {
        this.erpPendingQty = erpPendingQty;
    }

    public Date getErpLastUpdatedOn() {
        return erpLastUpdatedOn;
    }

    public void setErpLastUpdatedOn(Date erpLastUpdatedOn) {
        this.erpLastUpdatedOn = erpLastUpdatedOn;
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

    public SpOrderMaster getSpOrderMaster() {
        return spOrderMaster;
    }

    public void setSpOrderMaster(SpOrderMaster spOrderMaster) {
        this.spOrderMaster = spOrderMaster;
    }
    public Integer getColorId() {
        return colorId;
    }

    public void setColorId(Integer colorId) {
        this.colorId = colorId;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spOrderDetailsPK != null ? spOrderDetailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpOrderDetails)) {
            return false;
        }
        SpOrderDetails other = (SpOrderDetails) object;
        if ((this.spOrderDetailsPK == null && other.spOrderDetailsPK != null) || (this.spOrderDetailsPK != null && !this.spOrderDetailsPK.equals(other.spOrderDetailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpOrderDetails[spOrderDetailsPK=" + spOrderDetailsPK + "]";
    }

}
