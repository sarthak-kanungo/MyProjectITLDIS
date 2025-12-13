/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.math.BigDecimal;
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
 * @author prashant.kumar
 */
@Entity
@Table(name = "EWM_Loader_detail")
public class EWMLoaderDetail implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "EW_REF_NO")
    private String ewLoaderId;
    @Column(name = "DEALER_CODE")
    private String dealerCode;
    @Column(name = "CHASSIS_NO")
    private String chassisNo;
    @Column(name = "CUSTOMER_TITLE")
    private String customerTitle;
    @Column(name = "CUSTOMER_NAME")
    private String customerName;
    @Column(name = "ADDRESS")
    private String address;
    @Column(name = "STATE")
    private String state;
    @Column(name = "CITY")
    private String city;
    @Column(name = "PINCODE")
    private Long pincode;
    @Column(name = "MOBILE")
    private Long mobile;
    @Column(name = "TEL_NO")
    private String telNo;
    @Column(name = "EMAIL")
    private String email;
    @Column(name = "EXT_WARRANTY_BOOKLET_NO")
    private String extWarrantyBookletNo;
    @Column(name = "DATE_OF_SALE_OF_CERTIFICATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfSaleOfCertificate;
    @Column(name = "TYPE_NAME")
    private String typeName;
    @Column(name = "MAKE_NAME")
    private String makeName;
    @Column(name = "MODEL_NAME")
    private String modelName;
    @Column(name = "FUEL_TYPE")
    private String fuelType;    
    @Column(name = "ENGINE_NO")
    private String engineNo;   
    @Column(name = "VEHICLE_REG_NO")
    private String vehicleRegNo;
    @Column(name = "SALE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date saleDate;
    @Column(name = "DELIVERY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;
    @Column(name = "IMD_CODE")
    private String imdCode;    
    @Column(name = "SUM_INSURED")
    private Long sumInsured;
    @Column(name = "FLOAT_TYPE")
    private String floatType;
    @Column(name = "PPID")
    private long ppId;
    @Column(name = "HMR")
    private long hmr;
    @Column(name = "POLICY_TERM_ID")
    private Integer policyTermId;
    @Column(name = "POLICY_TYPE_ID")
    private Integer policyTypeId;
    @Column(name = "PREMIUM_AMOUNT")
    private BigDecimal premiumAmount;
    @Column(name = "AMOUNT_TO_BAJAJ")
    private BigDecimal amountToBajaj;
    @Column(name = "BAJAJ_POLICY_NO")
    private String bajajPolicyNo;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "CREATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "MODIFIED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "BAJAJ_POLICY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date bajajPolicyDate;

    @JoinColumn(name = "MechanicDealerKey", referencedColumnName = "MechanicDealerKey")
    @ManyToOne
    private MSWDmechanicmaster mechanicDealerKey;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Date getDateOfSaleOfCertificate() {
        return dateOfSaleOfCertificate;
    }

    public void setDateOfSaleOfCertificate(Date dateOfSaleOfCertificate) {
        this.dateOfSaleOfCertificate = dateOfSaleOfCertificate;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }
    
    public String getExtWarrantyBookletNo() {
        return extWarrantyBookletNo;
    }

    public void setExtWarrantyBookletNo(String extWarrantyBookletNo) {
        this.extWarrantyBookletNo = extWarrantyBookletNo;
    }

    public String getFloatType() {
        return floatType;
    }

    public void setFloatType(String floatType) {
        this.floatType = floatType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getImdCode() {
        return imdCode;
    }

    public void setImdCode(String imdCode) {
        this.imdCode = imdCode;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }


    public String getVehicleRegNo() {
        return vehicleRegNo;
    }

    public void setVehicleRegNo(String vehicleRegNo) {
        this.vehicleRegNo = vehicleRegNo;
    }

    public Long getMobile() {
        return mobile;
    }

    public void setMobile(Long mobile) {
        this.mobile = mobile;
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

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
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

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerTitle() {
        return customerTitle;
    }

    public void setCustomerTitle(String customerTitle) {
        this.customerTitle = customerTitle;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
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

    public Date getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(Date saleDate) {
        this.saleDate = saleDate;
    }

    public BigDecimal getAmountToBajaj() {
        return amountToBajaj;
    }

    public void setAmountToBajaj(BigDecimal amountToBajaj) {
        this.amountToBajaj = amountToBajaj;
    }

    public String getBajajPolicyNo() {
        return bajajPolicyNo;
    }

    public void setBajajPolicyNo(String bajajPolicyNo) {
        this.bajajPolicyNo = bajajPolicyNo;
    }

    public String getEwLoaderId() {
        return ewLoaderId;
    }

    public void setEwLoaderId(String ewLoaderId) {
        this.ewLoaderId = ewLoaderId;
    }

    public Integer getPolicyTermId() {
        return policyTermId;
    }

    public void setPolicyTermId(Integer policyTermId) {
        this.policyTermId = policyTermId;
    }

    public Integer getPolicyTypeId() {
        return policyTypeId;
    }

    public void setPolicyTypeId(Integer policyTypeId) {
        this.policyTypeId = policyTypeId;
    }

    public BigDecimal getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(BigDecimal premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public Long getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(Long sumInsured) {
        this.sumInsured = sumInsured;
    }

    public long getHmr() {
        return hmr;
    }

    public void setHmr(long hmr) {
        this.hmr = hmr;
    }

    public long getPpId() {
        return ppId;
    }

    public void setPpId(long ppId) {
        this.ppId = ppId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getBajajPolicyDate() {
        return bajajPolicyDate;
    }

    public void setBajajPolicyDate(Date bajajPolicyDate) {
        this.bajajPolicyDate = bajajPolicyDate;
    }

    /**
     * @return the mechanicDealerKey
     */
    public MSWDmechanicmaster getMechanicDealerKey() {
        return mechanicDealerKey;
    }

    /**
     * @param mechanicDealerKey the mechanicDealerKey to set
     */
    public void setMechanicDealerKey(MSWDmechanicmaster mechanicDealerKey) {
        this.mechanicDealerKey = mechanicDealerKey;
    }

    
    
}