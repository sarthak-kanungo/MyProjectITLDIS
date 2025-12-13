/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "SW_vehicledetails")
@org.hibernate.annotations.Entity(
		dynamicUpdate = true
)

public class Vehicledetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "VINID")
    private String vinid;
    @Column(name = "DealerCode")
    private String dealerCode;
    @Column(name = "vinNo")
    private String vinNo;
    @Column(name = "EngineNo")
    private String engineNo;
    @Column(name = "RegNo")
    private String regNo;
    @Column(name = "SerBookNo")
    private String serBookNo;
    @Column(name = "KeyIdentification")
    private String keyIdentification;
    @Column(name = "OwnerDriven")
    private String ownerDriven;
    @Column(name = "ModelFamily")
    private String modelFamily;
    @Column(name = "ModelFamilyDesc")
    private String modelFamilyDesc;
    @Column(name = "ModelCode")
    private String modelCode;
    @Column(name = "ModelCodeDesc")
    private String modelCodeDesc;
    @Column(name = "CustomerName")
    private String customerName;
    @Column(name = "VillageName")
    private String villageName;
    @Column(name = "Tehsil")
    private String tehsil;
    @Column(name = "District")
    private String district;
    @Column(name = "Pincode")
    private Long pincode;
    @Column(name = "State")
    private String state;
    @Column(name = "Country")
    private String country;
    @Column(name = "MobilePh")
    private Long mobilePh;
    @Column(name = "LandlineNo")
    private String landlineNo;
    @Column(name = "emailId")
    private String emailId;
    @Column(name = "Is_ServerSync")
    private Character isServerSync;
    @Column(name = "NextService")
    private String nextService;
    @Column(name = "sizeLandHolding")
    private String sizeLandHolding;
    @Column(name = "mainCrops")
    private String mainCrops;
    @Column(name = "occupation")
    private String occupation;
    @Column(name = "PDI_STATUS")
    private Character pdiStatus;
    @Column(name = "INS_STATUS")
    private Character insStatus;
    @Column(name = "DealerInvoiceDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date DealerInvoiceDate;

    @Column(name = "ITL_InvoiceNo")
    private String ItlInvoiceNo;
    @Column(name = "DealerInvoiceNo")
    private String DealerInvoiceNo;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "ITL_InvoiceDate")
    private Date ItlInvoiceDate;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DeliveryDate")
    private Date deliveryDate;
    @Column(name = "vin_details_type")
    private String vinDetailsType;
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "PDI_PENDING_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date pdiPendingDate;
    @Column(name = "ContactName")
    private String contactName;//Remarks
    @Column(name = "ContactNo")
    private String contactNo;//Remarks
    @Column(name = "RelationshipCustomer")
    private String relationshipCustomer;//Remarks


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vinid")
    private Collection<PdiDetails> pdiDetailsCollection;

    public String getContactName()
    {
        return contactName;
    }

    public void setContactName(String contactName)
    {
        this.contactName = contactName;
    }

    public String getContactNo()
    {
        return contactNo;
    }

    public void setContactNo(String contactNo)
    {
        this.contactNo = contactNo;
    }

    public String getRelationshipCustomer()
    {
        return relationshipCustomer;
    }

    public void setRelationshipCustomer(String relationshipCustomer)
    {
        this.relationshipCustomer = relationshipCustomer;
    }

    

    public Date getDealerInvoiceDate() {
        return DealerInvoiceDate;
    }

    public void setDealerInvoiceDate(Date DealerInvoiceDate) {
        this.DealerInvoiceDate = DealerInvoiceDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public Vehicledetails() {
    }

    public Vehicledetails(String vinid) {
        this.vinid = vinid;
    }

    public String getVinid() {
        return vinid;
    }

    public void setVinid(String vinid) {
        this.vinid = vinid;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
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

   
    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getSerBookNo() {
        return serBookNo;
    }

    public void setSerBookNo(String serBookNo) {
        this.serBookNo = serBookNo;
    }

    public String getKeyIdentification() {
        return keyIdentification;
    }

    public void setKeyIdentification(String keyIdentification) {
        this.keyIdentification = keyIdentification;
    }

    public String getOwnerDriven() {
        return ownerDriven;
    }

    public void setOwnerDriven(String ownerDriven) {
        this.ownerDriven = ownerDriven;
    }

    public String getModelFamily() {
        return modelFamily;
    }

    public void setModelFamily(String modelFamily) {
        this.modelFamily = modelFamily;
    }

    public String getModelFamilyDesc() {
        return modelFamilyDesc;
    }

    public void setModelFamilyDesc(String modelFamilyDesc) {
        this.modelFamilyDesc = modelFamilyDesc;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public String getModelCodeDesc() {
        return modelCodeDesc;
    }

    public void setModelCodeDesc(String modelCodeDesc) {
        this.modelCodeDesc = modelCodeDesc;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getTehsil() {
        return tehsil;
    }

    public void setTehsil(String tehsil) {
        this.tehsil = tehsil;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public Long getPincode() {
        return pincode;
    }

    public void setPincode(Long pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Long getMobilePh() {
        return mobilePh;
    }

    public void setMobilePh(Long mobilePh) {
        this.mobilePh = mobilePh;
    }

    public String getLandlineNo() {
        return landlineNo;
    }

    public void setLandlineNo(String landlineNo) {
        this.landlineNo = landlineNo;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public Character getIsServerSync() {
        return isServerSync;
    }

    public void setIsServerSync(Character isServerSync) {
        this.isServerSync = isServerSync;
    }

    public String getNextService() {
        return nextService;
    }

    public void setNextService(String nextService) {
        this.nextService = nextService;
    }

    public String getSizeLandHolding() {
        return sizeLandHolding;
    }

    public void setSizeLandHolding(String sizeLandHolding) {
        this.sizeLandHolding = sizeLandHolding;
    }

    public String getMainCrops() {
        return mainCrops;
    }

    public void setMainCrops(String mainCrops) {
        this.mainCrops = mainCrops;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Character getPdiStatus() {
        return pdiStatus;
    }

    public void setPdiStatus(Character pdiStatus) {
        this.pdiStatus = pdiStatus;
    }

    public Character getInsStatus() {
        return insStatus;
    }

    public void setInsStatus(Character insStatus) {
        this.insStatus = insStatus;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Collection<PdiDetails> getPdiDetailsCollection() {
        return pdiDetailsCollection;
    }

    public String getDealerInvoiceNo() {
        return DealerInvoiceNo;
    }

    public void setDealerInvoiceNo(String DealerInvoiceNo) {
        this.DealerInvoiceNo = DealerInvoiceNo;
    }

    public Date getItlInvoiceDate() {
        return ItlInvoiceDate;
    }

    public void setItlInvoiceDate(Date ItlInvoiceDate) {
        this.ItlInvoiceDate = ItlInvoiceDate;
    }

    public String getItlInvoiceNo() {
        return ItlInvoiceNo;
    }

    public void setItlInvoiceNo(String ItlInvoiceNo) {
        this.ItlInvoiceNo = ItlInvoiceNo;
    }

     

    public void setPdiDetailsCollection(Collection<PdiDetails> pdiDetailsCollection) {
        this.pdiDetailsCollection = pdiDetailsCollection;
    }

    public String getVinDetailsType() {
        return vinDetailsType;
    }

    public void setVinDetailsType(String vinDetailsType) {
        this.vinDetailsType = vinDetailsType;
    }

    public Date getPdiPendingDate() {
        return pdiPendingDate;
    }

    public void setPdiPendingDate(Date pdiPendingDate) {
        this.pdiPendingDate = pdiPendingDate;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vinid != null ? vinid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vehicledetails)) {
            return false;
        }
        Vehicledetails other = (Vehicledetails) object;
        if ((this.vinid == null && other.vinid != null) || (this.vinid != null && !this.vinid.equals(other.vinid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Vehicledetails[vinid=" + vinid + "]";
    }

}
