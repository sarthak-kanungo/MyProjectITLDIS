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
import javax.persistence.FetchType;
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
@Table(name = "SP_ORD_DTL_EXP")

public class SpOrderDetailsEXP implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "PO_DTL_ID")
    private Integer poDetailId;
    @Basic(optional = false)
    @Column(name = "CUST_PO_NO")
    private String custPoNo;
    @Basic(optional = false)
    @Column(name = "POSITION_NO")
    private int positionNo;
    @Basic(optional = false)
    @Column(name = "PART_NO")
    private String partNo;
    @Basic(optional = false)
    @Column(name = "QTY")
    private int qty;
    @Basic(optional = false)
    @Column(name = "PRICE")
    private double price;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;
    @Column(name = "PI_QTY")
    private int piQty;
    @Column(name = "PENDING_QTY")
    private int pendingQty;
    @Column(name = "LAST_UPDATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedOn;
    @Column(name = "Is_ServerSync")
    private Character isServerSync;
    @Column(name = "LastSyncDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSyncDate;
    @JoinColumn(name = "CUST_PO_NO", referencedColumnName = "CUST_PO_NO", insertable = false, updatable = false)
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private SPOrderHeaderEXP spOrderHeaderEXP;

    public SpOrderDetailsEXP() {
    }
        public SpOrderDetailsEXP(Integer poDetailId, String custPoNo, Integer positionNo,String partNo, Integer qty,float price,String status,int piQty, int pendingQty) {
        this.poDetailId = poDetailId;
        this.custPoNo = custPoNo;
        this.positionNo = positionNo;
        this.partNo = partNo;
        this.qty = qty;
        this.price = price;
        this.status = status;
        this.piQty = piQty;
        this.pendingQty = pendingQty;
    }
   

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
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

   
    
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpOrderDetailsEXP)) {
            return false;
        }
        SpOrderDetailsEXP other = (SpOrderDetailsEXP) object;
        if ((this.poDetailId == null && other.poDetailId != null) || (this.poDetailId != null && !this.poDetailId.equals(other.poDetailId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpOrderDetailsEXP[id=" + poDetailId + "]";
    }

    /**
     * @return the piQty
     */
    public int  getPiQty() {
        return piQty;
    }

    /**
     * @param piQty the piQty to set
     */
    public void setPiQty(int piQty) {
        this.piQty = piQty;
    }

    /**
     * @return the pendingQty
     */
    public int getPendingQty() {
        return pendingQty;
    }

    /**
     * @param pendingQty the pendingQty to set
     */
    public void setPendingQty(int pendingQty) {
        this.pendingQty = pendingQty;
    }

    /**
     * @return the lastUpdatedOn
     */
    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    /**
     * @param lastUpdatedOn the lastUpdatedOn to set
     */
    public void setLastUpdatedOn(Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

   

    /**
     * @return the positionNo
     */
    public int getPositionNo() {
        return positionNo;
    }

    /**
     * @param positionNo the positionNo to set
     */
    public void setPositionNo(int positionNo) {
        this.positionNo = positionNo;
    }

    /**
     * @return the partNo
     */
    public String getPartNo() {
        return partNo;
    }

    /**
     * @param partNo the partNo to set
     */
    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    /**
     * @return the spOrderHeaderEXP
     */
    public SPOrderHeaderEXP getSpOrderHeaderEXP() {
        return spOrderHeaderEXP;
    }

    /**
     * @param spOrderHeaderEXP the spOrderHeaderEXP to set
     */
    public void setSpOrderHeaderEXP(SPOrderHeaderEXP spOrderHeaderEXP) {
        this.spOrderHeaderEXP = spOrderHeaderEXP;
    }

    /**
     * @return the custPoNo
     */
    public String getCustPoNo() {
        return custPoNo;
    }

    /**
     * @param custPoNo the custPoNo to set
     */
    public void setCustPoNo(String custPoNo) {
        this.custPoNo = custPoNo;
    }

    public Integer getPODetailId() {
        return poDetailId;
    }

    public void setPODetailId(Integer poDetailId) {
        this.poDetailId = poDetailId;
    }

}
