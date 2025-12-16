/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kundan.rastogi
 */
@Entity
@Table(name = "SP_ORDER_MASTER")
@NamedQueries({
    @NamedQuery(name = "SpOrderMaster.findAll", query = "SELECT s FROM SpOrderMaster s"),
    @NamedQuery(name = "SpOrderMaster.findByCustPoNo", query = "SELECT s FROM SpOrderMaster s WHERE s.custPoNo = :custPoNo"),
    @NamedQuery(name = "SpOrderMaster.findByOrdType", query = "SELECT s FROM SpOrderMaster s WHERE s.ordType = :ordType"),
 //   @NamedQuery(name = "SpOrderMaster.findByCustCode", query = "SELECT s FROM SpOrderMaster s WHERE s.custCode = :custCode"),
    @NamedQuery(name = "SpOrderMaster.findByCustPoDate", query = "SELECT s FROM SpOrderMaster s WHERE s.custPoDate = :custPoDate"),
    @NamedQuery(name = "SpOrderMaster.findByComments", query = "SELECT s FROM SpOrderMaster s WHERE s.comments = :comments"),
    @NamedQuery(name = "SpOrderMaster.findByEngineNo", query = "SELECT s FROM SpOrderMaster s WHERE s.engineNo = :engineNo"),
    @NamedQuery(name = "SpOrderMaster.findByChassisNo", query = "SELECT s FROM SpOrderMaster s WHERE s.chassisNo = :chassisNo"),
    @NamedQuery(name = "SpOrderMaster.findByModelNo", query = "SELECT s FROM SpOrderMaster s WHERE s.modelNo = :modelNo"),
    @NamedQuery(name = "SpOrderMaster.findByFirNo", query = "SELECT s FROM SpOrderMaster s WHERE s.jobCardNo = :firNo"),
    @NamedQuery(name = "SpOrderMaster.findByDeliveryTerms", query = "SELECT s FROM SpOrderMaster s WHERE s.deliveryTerms = :deliveryTerms"),
    @NamedQuery(name = "SpOrderMaster.findByDeliveryAddress", query = "SELECT s FROM SpOrderMaster s WHERE s.deliveryAddress = :deliveryAddress"),
    @NamedQuery(name = "SpOrderMaster.findByShipmentLotSingle", query = "SELECT s FROM SpOrderMaster s WHERE s.shipmentLotSingle = :shipmentLotSingle"),
    @NamedQuery(name = "SpOrderMaster.findByTotalValue", query = "SELECT s FROM SpOrderMaster s WHERE s.totalValue = :totalValue"),
    @NamedQuery(name = "SpOrderMaster.findByStockistId", query = "SELECT s FROM SpOrderMaster s WHERE s.stockistId = :stockistId"),
    @NamedQuery(name = "SpOrderMaster.findByStatus", query = "SELECT s FROM SpOrderMaster s WHERE s.status = :status"),
    @NamedQuery(name = "SpOrderMaster.findByCreatedBy", query = "SELECT s FROM SpOrderMaster s WHERE s.createdBy = :createdBy"),
   // @NamedQuery(name = "SpOrderMaster.findByLastUpdatedOn", query = "SELECT s FROM SpOrderMaster s WHERE s.lastUpdatedOn = :lastUpdatedOn"),
    @NamedQuery(name = "SpOrderMaster.findByErpUploadStatus", query = "SELECT s FROM SpOrderMaster s WHERE s.erpUploadStatus = :erpUploadStatus"),
    @NamedQuery(name = "SpOrderMaster.findByErpProcessStatus", query = "SELECT s FROM SpOrderMaster s WHERE s.erpProcessStatus = :erpProcessStatus"),
    @NamedQuery(name = "SpOrderMaster.findByErpOrderNo", query = "SELECT s FROM SpOrderMaster s WHERE s.erpOrderNo = :erpOrderNo"),
    @NamedQuery(name = "SpOrderMaster.findByErpOrderDate", query = "SELECT s FROM SpOrderMaster s WHERE s.erpOrderDate = :erpOrderDate"),
    @NamedQuery(name = "SpOrderMaster.findByErpLastUpdatedOn", query = "SELECT s FROM SpOrderMaster s WHERE s.erpLastUpdatedOn = :erpLastUpdatedOn"),
    @NamedQuery(name = "SpOrderMaster.findByIsServerSync", query = "SELECT s FROM SpOrderMaster s WHERE s.isServerSync = :isServerSync"),
    @NamedQuery(name = "SpOrderMaster.findByLastSyncDate", query = "SELECT s FROM SpOrderMaster s WHERE s.lastSyncDate = :lastSyncDate")})
public class SpOrderMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CUST_PO_NO")
    private String custPoNo;
    @Basic(optional = false)
    @Column(name = "ORD_TYPE")
    private String ordType;
    @Basic(optional = false)
    @Column(name = "DEALER_CODE")
    private String dealerCode;
    @Basic(optional = false)
    @Column(name = "CUST_PO_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date custPoDate;
    @Column(name = "COMMENTS")
    private String comments;
    @Column(name = "ENGINE_NO")
    private String engineNo;
    @Column(name = "CHASSIS_NO")
    private String chassisNo;
    @Column(name = "MODEL_NO")
    private String modelNo;
    @Column(name = "JOB_CARD_NO")
    private String jobCardNo;
    @Basic(optional = false)
    @Column(name = "DELIVERY_TERMS")
    private String deliveryTerms;
    @Column(name = "DELIVERY_ADDRESS")
    private String deliveryAddress;
    @Column(name = "DELIVERY_DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date deliveryDate;
    @Basic(optional = false)
    @Column(name = "SHIPMENT_LOT_SINGLE")
    private String shipmentLotSingle;
    @Basic(optional = false)
    @Column(name = "TOTAL_VALUE")
    private double totalValue;
    @Basic(optional = false)
    @Column(name = "STOCKIST_ID")
    private String stockistId;
    @Column(name = "STOCKIST_NAME")
    private String stockistName;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Basic(optional = false)
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "ERP_UPLOAD_STATUS")
    private String erpUploadStatus;
    @Column(name = "ERP_PROCESS_STATUS")
    private String erpProcessStatus;
    @Column(name = "ERP_ORDER_NO")
    private String erpOrderNo;
    @Column(name = "ERP_ORDER_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date erpOrderDate;
    @Column(name = "ERP_LAST_UPDATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date erpLastUpdatedOn;
    @Column(name = "Is_ServerSync")
    private Character isServerSync;
    @Column(name = "LastSyncDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSyncDate;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "spOrderMaster1", fetch = FetchType.LAZY)
    private SpOrderMaster spOrderMaster;
    @JoinColumn(name = "CUST_PO_NO", referencedColumnName = "CUST_PO_NO", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private SpOrderMaster spOrderMaster1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "spOrderMaster", fetch = FetchType.LAZY)
    private List<SpOrderDetails> spOrderDetailsList;
    //Added by satendtra
    @Column(name="ERP_REMARKS")
    private String erpRemarks;


    public SpOrderMaster() {
    }

    public SpOrderMaster(String custPoNo) {
        this.custPoNo = custPoNo;
    }

    public SpOrderMaster(String custPoNo, String ordType, String dealerCode, Date custPoDate, String deliveryTerms, String shipmentLotSingle, double totalValue, String stockistId,String stockistName, String status, String createdBy, Date createdOn) {
        this.custPoNo = custPoNo;
        this.ordType = ordType;
        this.dealerCode = dealerCode;
        this.custPoDate = custPoDate;
        this.deliveryTerms = deliveryTerms;
        this.shipmentLotSingle = shipmentLotSingle;
        this.totalValue = totalValue;
        this.stockistId = stockistId;
        this.stockistName = stockistName;
        this.status = status;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
    }

    public String getCustPoNo() {
        return custPoNo;
    }

    public void setCustPoNo(String custPoNo) {
        this.custPoNo = custPoNo;
    }

    public String getOrdType() {
        return ordType;
    }

    public void setOrdType(String ordType) {
        this.ordType = ordType;
    }

    public String getCustCode() {
        return dealerCode;
    }

    public void setCustCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public Date getCustPoDate() {
        return custPoDate;
    }

    public void setCustPoDate(Date custPoDate) {
        this.custPoDate = custPoDate;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    

    public String getDeliveryTerms() {
        return deliveryTerms;
    }

    public void setDeliveryTerms(String deliveryTerms) {
        this.deliveryTerms = deliveryTerms;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getShipmentLotSingle() {
        return shipmentLotSingle;
    }

    public void setShipmentLotSingle(String shipmentLotSingle) {
        this.shipmentLotSingle = shipmentLotSingle;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public String getStockistId() {
        return stockistId;
    }

    public void setStockistId(String stockistId) {
        this.stockistId = stockistId;
    }

    public String getStockistName() {
        return stockistName;
    }

    public void setStockistName(String stockistName) {
        this.stockistName = stockistName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

   

    public String getErpUploadStatus() {
        return erpUploadStatus;
    }

    public void setErpUploadStatus(String erpUploadStatus) {
        this.erpUploadStatus = erpUploadStatus;
    }

    public String getErpProcessStatus() {
        return erpProcessStatus;
    }

    public void setErpProcessStatus(String erpProcessStatus) {
        this.erpProcessStatus = erpProcessStatus;
    }

    public String getErpOrderNo() {
        return erpOrderNo;
    }

    public void setErpOrderNo(String erpOrderNo) {
        this.erpOrderNo = erpOrderNo;
    }

    public Date getErpOrderDate() {
        return erpOrderDate;
    }

    public void setErpOrderDate(Date erpOrderDate) {
        this.erpOrderDate = erpOrderDate;
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

    public SpOrderMaster getSpOrderMaster1() {
        return spOrderMaster1;
    }

    public void setSpOrderMaster1(SpOrderMaster spOrderMaster1) {
        this.spOrderMaster1 = spOrderMaster1;
    }

    public List<SpOrderDetails> getSpOrderDetailsList() {
        return spOrderDetailsList;
    }

    public void setSpOrderDetailsList(List<SpOrderDetails> spOrderDetailsList) {
        this.spOrderDetailsList = spOrderDetailsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (custPoNo != null ? custPoNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpOrderMaster)) {
            return false;
        }
        SpOrderMaster other = (SpOrderMaster) object;
        if ((this.custPoNo == null && other.custPoNo != null) || (this.custPoNo != null && !this.custPoNo.equals(other.custPoNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpOrderMaster[custPoNo=" + custPoNo + "]";
    }

    /**
     * @return the deliveryDate
     */
    public Date getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * @param deliveryDate the deliveryDate to set
     */
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * @return the jobCardNo
     */
    public String getJobCardNo() {
        return jobCardNo;
    }

    /**
     * @param jobCardNo the jobCardNo to set
     */
    public void setJobCardNo(String jobCardNo) {
        this.jobCardNo = jobCardNo;
    }

     public String getErpRemarks() {
        return erpRemarks;
    }
    public void setErpRemarks(String erpRemarks) {
        this.erpRemarks = erpRemarks;
    }

}
