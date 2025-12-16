/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author mahendra.rawat
 */
@Entity
@Table (name="SP_ORD_PI_EXP")
public class SP_ORD_PI_EXP implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name="PI_NO")
    private String piNo;
    @Basic(optional = false)
    @Column(name="ORD_TYPE")
    private String ordtype;
    @Basic(optional = false)
    @Column(name="DEALER_CODE")
    private String dealerCode;
    @Basic(optional = false)
    @Column(name="PI_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date piDate;
    @Column(name="REMARKS")
    private String remarks;
    @Column(name="ENGINE_NO")
    private String engineNo;
    @Column(name="CHASSIS_NO")
    private String chassisNo;
    @Column(name="MODEL_NO")
    private String modelNo;
    @Column(name="JOB_CARD_NO")
    private String jobCardNo;
    @Basic(optional = false)
    @Column(name="DELIVERY_TERMS")
    private String deliveryTerms;
    @Basic(optional = false)
    @Column(name="PAYMENT_TERMS")
    private String paymentTerm;
    @Basic(optional = false)
    @Column(name="INCO_TERMS")
    private String incoTerms;
    @Basic(optional = false)
    @Column(name="CONSIGNEE_ADDRESS")
    private String consigneeddress;
    @Basic(optional = false)
    @Column(name="DISCHARGE_PORT")
    private String dischargePort;
    @Basic(optional = false)
    @Column(name="CONSIGNEE_COUNTRY")
    private String consigneeCountry;
    @Basic(optional = false)
    @Column(name="CONSIGNEE_CODE")
    private String consigneeCode;
    @Column(name="CONSIGNEE_NAME")
    private String consigneeName;
    @Basic(optional = false)
    @Column(name="DESTINATION_PLACE")
    private String destinationPlace;
    @Basic(optional = false)
    @Column(name="TOTAL_VALUE")
    private Float totalValue;
    @Column(name="PARTS_VALUE")
    private Float partValue;
    @Basic(optional = false)
    @Column(name="STATUS")
    private String status;
    @Column(name="OTHER_CHARGES")
    private float otherCharges;
    @Column(name="PRE_CARRIAGE_BY")
    private String precarriageBy;
    @Column(name = "PLACE_PRE_CARRIER")
    private String placePreCarrier;
    @Column(name="BUYER_EDIT_ALLOWED")
    private String buyerEditAllowed;
    @Basic(optional = false)
    @Column(name="Created_By")
    private String createdBy;
    @Basic(optional = false)
    @Column(name="CreatedOn")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name="ERP_ORD_NO")
    private String erpOrderNo;
    @Column(name="ERP_ORD_DATE")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date erpOrderDate;
    @Column(name="ERP_UPLOAD_STATUS")
    private String erpUploadStatus;
    @Column(name="ERP_PROCESS_STATUS")
    private String erpProcessStatus;
    @Column(name="ERP_REMARKS")
    private String erpRemark;
    @Column(name="ERP_LAST_UPDATED_ON")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date erpLastUpdatedOn;
    @Column(name="ModifiedBy")
    private String lastupdatedBy;
    @Column(name="ModifiedOn")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastupdatedOn;
    @Column(name="Is_ServerSync")
    private Character isServerSync;
    @Column(name="LastSyncDate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastSyncDate;
    @Basic(optional = false)
    @Column(name = "PI_PRICELIST_CODE")
    private String priceListCode;
    @Basic(optional = false)
    @Column(name = "PI_CURRENCY")
    private String currency;
    @OneToMany(mappedBy="sp_ORD_PI_EXP")
    private Collection<SP_ORD_PI_DTL_EXP> sp_ORD_PI_DTL_EXP;

    public String getPiNo() {
        return piNo;
    }

    public void setPiNo(String piNo) {
        this.piNo = piNo;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piNo != null ? piNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SP_ORD_PI_EXP)) {
            return false;
        }
        SP_ORD_PI_EXP other = (SP_ORD_PI_EXP) object;
        if ((this.piNo == null && other.piNo != null) || (this.piNo != null && !this.piNo.equals(other.piNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SP_ORD_PI_EXP[id=" + piNo + "]";
    }

    /**
     * @return the ordtype
     */
    public String getOrdtype() {
        return ordtype;
    }

    /**
     * @param ordtype the ordtype to set
     */
    public void setOrdtype(String ordtype) {
        this.ordtype = ordtype;
    }

    /**
     * @return the dealerCode
     */
    public String getDealerCode() {
        return dealerCode;
    }

    /**
     * @param dealerCode the dealerCode to set
     */
    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    /**
     * @return the piDate
     */
    public Date getPiDate() {
        return piDate;
    }

    /**
     * @param piDate the piDate to set
     */
    public void setPiDate(Date piDate) {
        this.piDate = piDate;
    }

    /**
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    /**
     * @return the engineNo
     */
    public String getEngineNo() {
        return engineNo;
    }

    /**
     * @param engineNo the engineNo to set
     */
    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    /**
     * @return the chassisNo
     */
    public String getChassisNo() {
        return chassisNo;
    }

    /**
     * @param chassisNo the chassisNo to set
     */
    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    /**
     * @return the modelNo
     */
    public String getModelNo() {
        return modelNo;
    }

    /**
     * @param modelNo the modelNo to set
     */
    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
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

    /**
     * @return the deliveryTerms
     */
    public String getDeliveryTerms() {
        return deliveryTerms;
    }

    /**
     * @param deliveryTerms the deliveryTerms to set
     */
    public void setDeliveryTerms(String deliveryTerms) {
        this.deliveryTerms = deliveryTerms;
    }

    /**
     * @return the paymentTerm
     */
    public String getPaymentTerm() {
        return paymentTerm;
    }

    /**
     * @param paymentTerm the paymentTerm to set
     */
    public void setPaymentTerm(String paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    /**
     * @return the incoTerms
     */
    public String getIncoTerms() {
        return incoTerms;
    }

    /**
     * @param incoTerms the incoTerms to set
     */
    public void setIncoTerms(String incoTerms) {
        this.incoTerms = incoTerms;
    }

    /**
     * @return the dischargePort
     */
    public String getDischargePort() {
        return dischargePort;
    }

    /**
     * @param dischargePort the dischargePort to set
     */
    public void setDischargePort(String dischargePort) {
        this.dischargePort = dischargePort;
    }

    /**
     * @return the destinationPlace
     */
    public String getDestinationPlace() {
        return destinationPlace;
    }

    /**
     * @param destinationPlace the destinationPlace to set
     */
    public void setDestinationPlace(String destinationPlace) {
        this.destinationPlace = destinationPlace;
    }

    /**
     * @return the totalValue
     */
    public Float getTotalValue() {
        return totalValue;
    }

    /**
     * @param totalValue the totalValue to set
     */
    public void setTotalValue(Float totalValue) {
        this.totalValue = totalValue;
    }

    public Float getPartValue() {
        return partValue;
    }

    public void setPartValue(Float partValue) {
        this.partValue = partValue;
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

    public float getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(float otherCharges) {
        this.otherCharges = otherCharges;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the createdOn
     */
    public Date getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn the createdOn to set
     */
    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * @return the erpUploadStatus
     */
    public String getErpUploadStatus() {
        return erpUploadStatus;
    }

    /**
     * @param erpUploadStatus the erpUploadStatus to set
     */
    public void setErpUploadStatus(String erpUploadStatus) {
        this.erpUploadStatus = erpUploadStatus;
    }

    /**
     * @return the erpProcessStatus
     */
    public String getErpProcessStatus() {
        return erpProcessStatus;
    }

    /**
     * @param erpProcessStatus the erpProcessStatus to set
     */
    public void setErpProcessStatus(String erpProcessStatus) {
        this.erpProcessStatus = erpProcessStatus;
    }

    /**
     * @return the erpRemark
     */
    public String getErpRemark() {
        return erpRemark;
    }

    /**
     * @param erpRemark the erpRemark to set
     */
    public void setErpRemark(String erpRemark) {
        this.erpRemark = erpRemark;
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
     * @return the sp_ORD_PI_DTL_EXP
     */
    public Collection<SP_ORD_PI_DTL_EXP> getSp_ORD_PI_DTL_EXP() {
        return sp_ORD_PI_DTL_EXP;
    }

    /**
     * @param sp_ORD_PI_DTL_EXP the sp_ORD_PI_DTL_EXP to set
     */
    public void setSp_ORD_PI_DTL_EXP(Collection<SP_ORD_PI_DTL_EXP> sp_ORD_PI_DTL_EXP) {
        this.sp_ORD_PI_DTL_EXP = sp_ORD_PI_DTL_EXP;
    }

    public String getConsigneeCode() {
        return consigneeCode;
    }

    public void setConsigneeCode(String consigneeCode) {
        this.consigneeCode = consigneeCode;
    }

    public String getConsigneeCountry() {
        return consigneeCountry;
    }

    public void setConsigneeCountry(String consigneeCountry) {
        this.consigneeCountry = consigneeCountry;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getConsigneeddress() {
        return consigneeddress;
    }

    public void setConsigneeddress(String consigneeddress) {
        this.consigneeddress = consigneeddress;
    }

    public String getPlacePreCarrier() {
        return placePreCarrier;
    }

    public void setPlacePreCarrier(String placePreCarrier) {
        this.placePreCarrier = placePreCarrier;
    }

    public String getPrecarriageBy() {
        return precarriageBy;
    }

    public void setPrecarriageBy(String precarriageBy) {
        this.precarriageBy = precarriageBy;
    }

    public String getBuyerEditAllowed() {
        return buyerEditAllowed;
    }

    public void setBuyerEditAllowed(String buyerEditAllowed) {
        this.buyerEditAllowed = buyerEditAllowed;
    }

    public Date getErpLastUpdatedOn() {
        return erpLastUpdatedOn;
    }

    public void setErpLastUpdatedOn(Date erpLastUpdatedOn) {
        this.erpLastUpdatedOn = erpLastUpdatedOn;
    }

    public Date getErpOrderDate() {
        return erpOrderDate;
    }

    public void setErpOrderDate(Date erpOrderDate) {
        this.erpOrderDate = erpOrderDate;
    }

    public String getErpOrderNo() {
        return erpOrderNo;
    }

    public void setErpOrderNo(String erpOrderNo) {
        this.erpOrderNo = erpOrderNo;
    }

    public String getLastupdatedBy() {
        return lastupdatedBy;
    }

    public void setLastupdatedBy(String lastupdatedBy) {
        this.lastupdatedBy = lastupdatedBy;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPriceListCode() {
        return priceListCode;
    }

    public void setPriceListCode(String priceListCode) {
        this.priceListCode = priceListCode;
    }

}
