/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.math.BigInteger;
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

/**
 *
 * @author mahendra.rawat
 */
@Entity
@Table (name="SP_ORD_PI_DTL_EXP")
public class SP_ORD_PI_DTL_EXP implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="PI_DTL_ID")
    private BigInteger id;
    @Basic(optional=false)
    @Column(name="PO_DTL_ID")
    private BigInteger poDetailId;
    @Basic(optional=false)
    @Column(name="ORD_PART_NO")
    private String orderPartNo;
    @Basic(optional=false)
    @Column(name="AVL_PART_NO")
    private String avlPartNo;
    @Basic(optional=false)
    @Column(name="LEAD_TIME")
    private String leadTime;
    @Basic(optional=false)
    @Column(name="ACCEPTED_QTY")
    private Integer avlQty;
    @Basic(optional=false)
    @Column(name="FINAL_PI_QTY")
    private Integer finalPiQty;
    @Basic(optional=false)
    @Column(name="PRICE")
    private Float price;
    @Basic(optional=false)
    @Column(name="STATUS")
    private String status;
    @Column(name="SUB_STATUS")
    private String subStatus;
    @Basic(optional=false)
    @Column(name="LastUpdatedBy")
    private String lastupdatedBy;
    @Column(name="ERP_BOOKED_QTY")
    private Integer bookesQty;
    @Column(name="ERP_PENDING_QTY")
    private Integer pendingQty;
    @Column(name="ERP_POS_NO")
    private Integer posNo;
    @Basic(optional=false)
    @Column(name="BASE_PI_QTY")
    private Integer basePiQty;
    @Column(name="LastUpdatedOn")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastupdatedOn;
    @Column(name="ERP_LAST_UPDATED_ON")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date erpLastUpdatedOn;
    @Column(name="Is_ServerSync")
    private Character isServerSync;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    @Column(name="LastSyncDate")
    private Date lastSyncDate;

    @ManyToOne
    @JoinColumn(name="PI_NO", referencedColumnName = "PI_NO")
    private SP_ORD_PI_EXP sp_ORD_PI_EXP;


    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getId() != null ? getId().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SP_ORD_PI_DTL_EXP)) {
            return false;
        }
        SP_ORD_PI_DTL_EXP other = (SP_ORD_PI_DTL_EXP) object;
        if ((this.getId() == null && other.getId() != null) || (this.getId() != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SP_ORD_PI_DTL_EXP[id=" + getId() + "]";
    }

    /**
     * @return the poDetailId
     */
    public BigInteger getPoDetailId() {
        return poDetailId;
    }

    /**
     * @param poDetailId the poDetailId to set
     */
    public void setPoDetailId(BigInteger poDetailId) {
        this.poDetailId = poDetailId;
    }

    /**
     * @return the orderPartNo
     */
    public String getOrderPartNo() {
        return orderPartNo;
    }

    /**
     * @param orderPartNo the orderPartNo to set
     */
    public void setOrderPartNo(String orderPartNo) {
        this.orderPartNo = orderPartNo;
    }

    /**
     * @return the avlPartNo
     */
    public String getAvlPartNo() {
        return avlPartNo;
    }

    /**
     * @param avlPartNo the avlPartNo to set
     */
    public void setAvlPartNo(String avlPartNo) {
        this.avlPartNo = avlPartNo;
    }

    /**
     * @return the leadTime
     */
    public String getLeadTime() {
        return leadTime;
    }

    /**
     * @param leadTime the leadTime to set
     */
    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }

    /**
     * @return the avlQty
     */
    public Integer getAvlQty() {
        return avlQty;
    }

    /**
     * @param avlQty the avlQty to set
     */
    public void setAvlQty(Integer avlQty) {
        this.avlQty = avlQty;
    }

    /**
     * @return the price
     */
    public Float getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Float price) {
        this.price = price;
    }

    /**
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * @return the bookesQty
     */
    public Integer getBookesQty() {
        return bookesQty;
    }

    /**
     * @param bookesQty the bookesQty to set
     */
    public void setBookesQty(Integer bookesQty) {
        this.bookesQty = bookesQty;
    }

    /**
     * @return the pendingQty
     */
    public Integer getPendingQty() {
        return pendingQty;
    }

    /**
     * @param pendingQty the pendingQty to set
     */
    public void setPendingQty(Integer pendingQty) {
        this.pendingQty = pendingQty;
    }

    /**
     * @return the lastupdatedOn
     */
    public Date getLastupdatedOn() {
        return lastupdatedOn;
    }

    /**
     * @param lastupdatedOn the lastupdatedOn to set
     */
    public void setLastupdatedOn(Date lastupdatedOn) {
        this.lastupdatedOn = lastupdatedOn;
    }

    /**
     * @return the isServerSync
     */
    public Character getIsServerSync() {
        return isServerSync;
    }

    /**
     * @param isServerSync the isServerSync to set
     */
    public void setIsServerSync(Character isServerSync) {
        this.isServerSync = isServerSync;
    }

    /**
     * @return the lastSyncDate
     */
    public Date getLastSyncDate() {
        return lastSyncDate;
    }

    /**
     * @param lastSyncDate the lastSyncDate to set
     */
    public void setLastSyncDate(Date lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }

    /**
     * @return the sp_ORD_PI_EXP
     */
    public SP_ORD_PI_EXP getSp_ORD_PI_EXP() {
        return sp_ORD_PI_EXP;
    }

    /**
     * @param sp_ORD_PI_EXP the sp_ORD_PI_EXP to set
     */
    public void setSp_ORD_PI_EXP(SP_ORD_PI_EXP sp_ORD_PI_EXP) {
        this.sp_ORD_PI_EXP = sp_ORD_PI_EXP;
    }

    public String getLastupdatedBy() {
        return lastupdatedBy;
    }

    public void setLastupdatedBy(String lastupdatedBy) {
        this.lastupdatedBy = lastupdatedBy;
    }

    public Integer getBasePiQty() {
        return basePiQty;
    }

    public void setBasePiQty(Integer basePiQty) {
        this.basePiQty = basePiQty;
    }

    public Date getErpLastUpdatedOn() {
        return erpLastUpdatedOn;
    }

    public void setErpLastUpdatedOn(Date erpLastUpdatedOn) {
        this.erpLastUpdatedOn = erpLastUpdatedOn;
    }

    public Integer getFinalPiQty() {
        return finalPiQty;
    }

    public void setFinalPiQty(Integer finalPiQty) {
        this.finalPiQty = finalPiQty;
    }

    public String getSubStatus() {
        return subStatus;
    }

    public void setSubStatus(String subStatus) {
        this.subStatus = subStatus;
    }

    public Integer getPosNo() {
        return posNo;
    }

    public void setPosNo(Integer posNo) {
        this.posNo = posNo;
    }

    }
