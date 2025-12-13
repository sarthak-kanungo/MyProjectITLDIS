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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author avinash.pandey
 */

@Entity
@Table(name = "SP_ORD_HDR_EXP")
public class SPOrderHeaderEXP implements Serializable {
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
    @Column(name = "DEALER_REF_NO")
    private String dealerRefNo;
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
    @Basic(optional = false)
    @Column(name = "PAYMENT_TERMS")
    private String  paymentTerms;
    @Basic(optional = false)
    @Column(name = "INCO_TERMS")
    private String  incoTerms;
    @Basic(optional = false)
    @Column(name = "CONSIGNEE_CODE")
    private String  consigneeCode;
    @Column(name = "CONSIGNEE_NAME")
    private String  consigneeName;
    @Basic(optional = false)
    @Column(name = "CONSIGNEE_ADDRESS")
    private String consigneeAddress;
    @Basic(optional = false)
    @Column(name = "CONSIGNEE_COUNTRY")
    private String consigneeCountry;
    @Basic(optional = false)
    @Column(name = "DISCHARGE_PORT")
    private String dischargePort;
    @Basic(optional = false)
    @Column(name = "DESTINATION_PLACE")
    private String destinationPlace;
    @Column(name = "PRE_CARRIAGE_BY")
    private String precarriageBy;
    @Column(name = "PLACE_PRE_CARRIER")
    private String placePreCarrier;
    @Basic(optional = false)
    @Column(name = "TOTAL_VALUE")
    private double totalValue;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @Column(name = "IS_DOCUMENT_UPLOAD")
    private String isDocumentUpload;
    @Basic(optional = false)
    @Column(name = "DOCUMENT_NAME")
    private String documentName;
    @Basic(optional = false)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Basic(optional = false)
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "HO_PROCESS_STATUS")
    private String hoProcessStatus;
    @Basic(optional = false)
    @Column(name = "HO_REMARKS")
    private String hoRemarks;
    @Basic(optional = false)
    @Column(name = "LAST_UPDATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedOn;
    @Column(name = "Is_ServerSync")
    private Character isServerSync;
    @Column(name = "LastSyncDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSyncDate;
    @Basic(optional = false)
    @Column(name = "ORDER_PRICELIST_CODE")
    private String priceListCode;
    @Basic(optional = false)
    @Column(name = "ORDER_CURRENCY")
    private String currency;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "SPOrderHeaderEXP1", fetch = FetchType.LAZY)
    private SPOrderHeaderEXP SPOrderHeaderEXP;
    @JoinColumn(name = "CUST_PO_NO", referencedColumnName = "CUST_PO_NO", insertable = false, updatable = false)
    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private SPOrderHeaderEXP SPOrderHeaderEXP1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "spOrderHeaderEXP", fetch = FetchType.LAZY)
    private List<SpOrderDetailsEXP> spOrderDetailsEXPList;

    public SPOrderHeaderEXP() {
    }

    public SPOrderHeaderEXP(String custPoNo) {
        this.custPoNo = custPoNo;
    }
    public SPOrderHeaderEXP(String custPoNo, String ordType, String dealerCode, Date custPoDate, String deliveryTerms, String paymentTerms, String incoTerms,String dischargePort ,String destinationPlace,String dealerRefNo,String isDocumentUpload,String documentName,double totalValue,  String status, String createdBy, Date createdOn) {
        this.custPoNo = custPoNo;
        this.ordType = ordType;
        this.dealerCode = dealerCode;
        this.custPoDate = custPoDate;
        this.dealerRefNo = dealerRefNo;
        this.deliveryTerms = deliveryTerms;
        this.paymentTerms = paymentTerms;
        this.incoTerms = incoTerms;
        this.dischargePort = dischargePort;
        this.destinationPlace = destinationPlace;
        this.totalValue = totalValue;
        this.status = status;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
        this.documentName = documentName;
        this.isDocumentUpload = isDocumentUpload;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (getCustPoNo() != null ? getCustPoNo().hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SPOrderHeaderEXP)) {
            return false;
        }
        SPOrderHeaderEXP other = (SPOrderHeaderEXP) object;
        if ((this.getCustPoNo() == null && other.getCustPoNo() != null) || (this.getCustPoNo() != null && !this.custPoNo.equals(other.custPoNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SPOrderHeaderEXP[custPoNo=" + custPoNo + "]";
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

    /**
     * @return the ordType
     */
    public String getOrdType() {
        return ordType;
    }

    /**
     * @param ordType the ordType to set
     */
    public void setOrdType(String ordType) {
        this.ordType = ordType;
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
     * @return the custPoDate
     */
    public Date getCustPoDate() {
        return custPoDate;
    }

    /**
     * @param custPoDate the custPoDate to set
     */
    public void setCustPoDate(Date custPoDate) {
        this.custPoDate = custPoDate;
    }

    /**
     * @return the comments
     */
    public String getComments() {
        return comments;
    }

    /**
     * @param comments the comments to set
     */
    public void setComments(String comments) {
        this.comments = comments;
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
     * @return the paymentTerms
     */
    public String getPaymentTerms() {
        return paymentTerms;
    }

    /**
     * @param paymentTerms the paymentTerms to set
     */
    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
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
    public double getTotalValue() {
        return totalValue;
    }

    /**
     * @param totalValue the totalValue to set
     */
    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
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
     * @return the isDocumentUpload
     */
    public String getIsDocumentUpload() {
        return isDocumentUpload;
    }

    /**
     * @param isDocumentUpload the isDocumentUpload to set
     */
    public void setIsDocumentUpload(String isDocumentUpload) {
        this.isDocumentUpload = isDocumentUpload;
    }

    /**
     * @return the documentName
     */
    public String getDocumentName() {
        return documentName;
    }

    /**
     * @param documentName the documentName to set
     */
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
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
     * @return the hoProcessStatus
     */
    public String getHoProcessStatus() {
        return hoProcessStatus;
    }

    /**
     * @param hoProcessStatus the hoProcessStatus to set
     */
    public void setHoProcessStatus(String hoProcessStatus) {
        this.hoProcessStatus = hoProcessStatus;
    }

    /**
     * @return the hoRemarks
     */
    public String getHoRemarks() {
        return hoRemarks;
    }

    /**
     * @param hoRemarks the hoRemarks to set
     */
    public void setHoRemarks(String hoRemarks) {
        this.hoRemarks = hoRemarks;
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
     * @return the dealerRefNo
     */
    public String getDealerRefNo() {
        return dealerRefNo;
    }

    /**
     * @param dealerRefNo the dealerRefNo to set
     */
    public void setDealerRefNo(String dealerRefNo) {
        this.dealerRefNo = dealerRefNo;
    }

    /**
     * @return the SPOrderHeaderEXP
     */
    public SPOrderHeaderEXP getSPOrderHeaderEXP() {
        return SPOrderHeaderEXP;
    }

    /**
     * @param SPOrderHeaderEXP the SPOrderHeaderEXP to set
     */
    public void setSPOrderHeaderEXP(SPOrderHeaderEXP SPOrderHeaderEXP) {
        this.SPOrderHeaderEXP = SPOrderHeaderEXP;
    }

    /**
     * @return the SPOrderHeaderEXP1
     */
    public SPOrderHeaderEXP getSPOrderHeaderEXP1() {
        return SPOrderHeaderEXP1;
    }

    /**
     * @param SPOrderHeaderEXP1 the SPOrderHeaderEXP1 to set
     */
    public void setSPOrderHeaderEXP1(SPOrderHeaderEXP SPOrderHeaderEXP1) {
        this.SPOrderHeaderEXP1 = SPOrderHeaderEXP1;
    }

    /**
     * @return the spOrderDetailsEXPList
     */
    public List<SpOrderDetailsEXP> getSpOrderDetailsEXPList() {
        return spOrderDetailsEXPList;
    }

    /**
     * @param spOrderDetailsEXPList the spOrderDetailsEXPList to set
     */
    public void setSpOrderDetailsEXPList(List<SpOrderDetailsEXP> spOrderDetailsEXPList) {
        this.spOrderDetailsEXPList = spOrderDetailsEXPList;
    }

    /**
     * @return the consigneeCode
     */
    public String getConsigneeCode() {
        return consigneeCode;
    }

    /**
     * @param consigneeCode the consigneeCode to set
     */
    public void setConsigneeCode(String consigneeCode) {
        this.consigneeCode = consigneeCode;
    }

    /**
     * @return the consigneeName
     */
    public String getConsigneeName() {
        return consigneeName;
    }

    /**
     * @param consigneeName the consigneeName to set
     */
    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    /**
     * @return the consigneeAddress
     */
    public String getConsigneeAddress() {
        return consigneeAddress;
    }

    /**
     * @param consigneeAddress the consigneeAddress to set
     */
    public void setConsigneeAddress(String consigneeAddress) {
        this.consigneeAddress = consigneeAddress;
    }

    /**
     * @return the consigneeCountry
     */
    public String getConsigneeCountry() {
        return consigneeCountry;
    }

    /**
     * @param consigneeCountry the consigneeCountry to set
     */
    public void setConsigneeCountry(String consigneeCountry) {
        this.consigneeCountry = consigneeCountry;
    }

    /**
     * @return the precarriageBy
     */
    public String getPrecarriageBy() {
        return precarriageBy;
    }

    /**
     * @param precarriageBy the precarriageBy to set
     */
    public void setPrecarriageBy(String precarriageBy) {
        this.precarriageBy = precarriageBy;
    }

    /**
     * @return the placePreCarrier
     */
    public String getPlacePreCarrier() {
        return placePreCarrier;
    }

    /**
     * @param placePreCarrier the placePreCarrier to set
     */
    public void setPlacePreCarrier(String placePreCarrier) {
        this.placePreCarrier = placePreCarrier;
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
