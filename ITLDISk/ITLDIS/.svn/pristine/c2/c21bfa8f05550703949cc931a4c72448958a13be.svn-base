/**
 * 
 */
package HibernateMapping;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author aman.khan
 *
 */
@Entity
@Table(name = "ITLEWM_Loader_detail")
public class ITLEWMLoaderDetail {
	
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "ITLEW_REF_NO", nullable = false, length = 50)
    private String itlewRefNo;

    @Column(name = "DEALER_CODE", nullable = false, length = 50)
    private String dealerCode;

    @Column(name = "CHASSIS_NO", nullable = false, length = 50)
    private String chassisNo;

    @Column(name = "DELIVERY_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;

    @Column(name = "ENGINE_NO", nullable = false, length = 50)
    private String engineNo;

    @Column(name = "ITL_EXT_REG_DATE", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date itlExtRegDate;

    @Column(name = "ITL_EXT_REG_STATUS", nullable = false, length = 50)
    private String itlExtRegStatus;

    @Column(name = "PRODUCT_CATEGORY", nullable = false, length = 50)
    private String productCategory;

    @Column(name = "ModelCode", nullable = false, length = 50)
    private String modelCode;

    @Column(name = "ModelCodeDesc", nullable = false, length = 50)
    private String modelCodeDesc;

    @Column(name = "ModelFamily", nullable = false, length = 50)
    private String modelFamily;

    @Column(name = "ModelFamilyDesc", nullable = false, length = 50)
    private String modelFamilyDesc;

    @Column(name = "MakeName", nullable = false, length = 50)
    private String makeName;

    @Column(name = "FUEL_TYPE", nullable = false, length = 30)
    private String fuelType;

    @Column(name = "TRACTOR_REG_NO", length = 30)
    private String tractorRegNo;

    @Column(name = "HMR", nullable = false)
    private long hmr;

    @Column(name = "EW_TYPE", nullable = false, length = 50)
    private String ewType;

    @Column(name = "EW_REGISTRATION_AMOUNT", precision = 18, scale = 2)
    private BigDecimal ewRegistrationAmount;

    @Column(name = "CUSTOMER_NAME", nullable = false, length = 250)
    private String customerName;

    @Column(name = "STATE", nullable = false, length = 50)
    private String state;

    @Column(name = "DISTRICT", nullable = false, length = 50)
    private String district;

    @Column(name = "TEHSIL", nullable = false, length = 50)
    private String tehsil;

    @Column(name = "VILLAGE", nullable = false, length = 50)
    private String village;

    @Column(name = "PINCODE", nullable = false)
    private Long pincode;

    @Column(name = "ADDRESS", nullable = false, length = 250)
    private String address;

    @Column(name = "MOBILE", nullable = false)
    private Long mobile;

    @Column(name = "LANDLINE", length = 50)
    private String landline;

    @Column(name = "EMAIL", length = 50)
    private String email;

    @Column(name = "OF_LAND_HOLDING", length = 50)
    private String ofLandHolding;

    @Column(name = "MAIN_CROPS")
    private String mainCrops;

    @Column(name = "OCCUPATION", length = 50)
    private String occupation;

    @Column(name = "PAYEE_NAME", nullable = false, length = 250)
    private String payeeName;

    @Column(name = "PAYEE_STATE", nullable = false, length = 50)
    private String payeeState;

    @Column(name = "PAYEE_DISTRICT", nullable = false, length = 50)
    private String payeeDistrict;

    @Column(name = "PAYEE_CITY", length = 50)
    private String payeeCity;

    @Column(name = "PAYEE_TEHSIL", nullable = false, length = 50)
    private String payeeTehsil;

    @Column(name = "PAYEE_VILLAGE", nullable = false, length = 50)
    private String payeeVillage;

    @Column(name = "PAYEE_PINCODE", nullable = false)
    private Long payeePincode;

    @Column(name = "PAYEE_ADDRESS", nullable = false, length = 250)
    private String payeeAddress;

    @Column(name = "PAYEE_MOBILE", nullable = false)
    private Long payeeMobile;

    @Column(name = "GST_INVOICE_DOC_NAME", nullable = true, columnDefinition = "nvarchar(max)")
    private String gstInvoiceDocName;

    @Column(name = "ITL_EW_CERTIFICATE_NAME", nullable = true, columnDefinition = "nvarchar(max)")
    private String itlEwCertificateName;

    @Column(name = "ITL_EW_DEBIT_INVOICE", nullable = true, columnDefinition = "nvarchar(max)")
    private String itlEwDebitInvoice;

    @Column(name = "DATE_OF_SALE_OF_CERTIFICATE", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateOfSaleOfCertificate;

    @Column(name = "ITL_POLICY_NO", length = 50)
    private String itlPolicyNo;

    @Column(name = "ITL_POLICY_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date itlPolicyDate;
    
    @Column(name = "INVOICE_NO", length = 50)
    private String invoiceNo;

    @Column(name = "INVOICE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;

    @Column(name = "CREATED_BY", nullable = false, length = 50)
    private String createdBy;

    @Column(name = "CREATED_ON", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Column(name = "MODIFIED_BY", length = 50)
    private String modifiedBy;

    @Column(name = "MODIFIED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    
    @Column(name = "Service_Record_Engine")
    private String serviceRecordEngine;
    @Column(name = "Gear_Oil_Change_Transmission")
    private String gearOilChangeTransmission;
    @Column(name = "Engine")
    private String engine;
    @Column(name = "Clutch")
    private String clutch;
    @Column(name = "Transmission")
    private String transmission;
    @Column(name = "Brakes")
    private String brakes;
    @Column(name = "Hydraulic")
    private String hydraulic;
    @Column(name = "TPL")
    private String tpl;
    @Column(name = "CheckExternal_Crack_Damage")
    private String checkExternalCrackDamage;
    @Column(name = "Status")
    private String status;
    @Column(name = "ITL_EmployeeId")
    private String itlEmployeeId;
    @Column(name = "FirstName")
    private String firstName;
   

	public String getItlewRefNo() {
		return itlewRefNo;
	}

	public void setItlewRefNo(String itlewRefNo) {
		this.itlewRefNo = itlewRefNo;
	}

	public String getDealerCode() {
		return dealerCode;
	}

	public void setDealerCode(String dealerCode) {
		this.dealerCode = dealerCode;
	}

	public String getChassisNo() {
		return chassisNo;
	}

	public void setChassisNo(String chassisNo) {
		this.chassisNo = chassisNo;
	}

	public Date getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(Date deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public String getEngineNo() {
		return engineNo;
	}

	public void setEngineNo(String engineNo) {
		this.engineNo = engineNo;
	}

	public Date getItlExtRegDate() {
		return itlExtRegDate;
	}

	public void setItlExtRegDate(Date itlExtRegDate) {
		this.itlExtRegDate = itlExtRegDate;
	}

	public String getItlExtRegStatus() {
		return itlExtRegStatus;
	}

	public void setItlExtRegStatus(String itlExtRegStatus) {
		this.itlExtRegStatus = itlExtRegStatus;
	}

	public String getProductCategory() {
		return productCategory;
	}

	public void setProductCategory(String productCategory) {
		this.productCategory = productCategory;
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

	public String getMakeName() {
		return makeName;
	}

	public void setMakeName(String makeName) {
		this.makeName = makeName;
	}

	public String getFuelType() {
		return fuelType;
	}

	public void setFuelType(String fuelType) {
		this.fuelType = fuelType;
	}

	public String getTractorRegNo() {
		return tractorRegNo;
	}

	public void setTractorRegNo(String tractorRegNo) {
		this.tractorRegNo = tractorRegNo;
	}

	public long getHmr() {
		return hmr;
	}

	public void setHmr(long hmr) {
		this.hmr = hmr;
	}

	public String getEwType() {
		return ewType;
	}

	public void setEwType(String ewType) {
		this.ewType = ewType;
	}

	public BigDecimal getEwRegistrationAmount() {
		return ewRegistrationAmount;
	}

	public void setEwRegistrationAmount(BigDecimal ewRegistrationAmount) {
		this.ewRegistrationAmount = ewRegistrationAmount;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getTehsil() {
		return tehsil;
	}

	public void setTehsil(String tehsil) {
		this.tehsil = tehsil;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public Long getPincode() {
		return pincode;
	}

	public void setPincode(Long pincode) {
		this.pincode = pincode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}

	public String getLandline() {
		return landline;
	}

	public void setLandline(String landline) {
		this.landline = landline;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getOfLandHolding() {
		return ofLandHolding;
	}

	public void setOfLandHolding(String ofLandHolding) {
		this.ofLandHolding = ofLandHolding;
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

	public String getPayeeName() {
		return payeeName;
	}

	public void setPayeeName(String payeeName) {
		this.payeeName = payeeName;
	}

	public String getPayeeState() {
		return payeeState;
	}

	public void setPayeeState(String payeeState) {
		this.payeeState = payeeState;
	}

	public String getPayeeDistrict() {
		return payeeDistrict;
	}

	public void setPayeeDistrict(String payeeDistrict) {
		this.payeeDistrict = payeeDistrict;
	}

	public String getPayeeCity() {
		return payeeCity;
	}

	public void setPayeeCity(String payeeCity) {
		this.payeeCity = payeeCity;
	}

	public String getPayeeTehsil() {
		return payeeTehsil;
	}

	public void setPayeeTehsil(String payeeTehsil) {
		this.payeeTehsil = payeeTehsil;
	}

	public String getPayeeVillage() {
		return payeeVillage;
	}

	public void setPayeeVillage(String payeeVillage) {
		this.payeeVillage = payeeVillage;
	}

	public Long getPayeePincode() {
		return payeePincode;
	}

	public void setPayeePincode(Long payeePincode) {
		this.payeePincode = payeePincode;
	}

	public String getPayeeAddress() {
		return payeeAddress;
	}

	public void setPayeeAddress(String payeeAddress) {
		this.payeeAddress = payeeAddress;
	}

	public Long getPayeeMobile() {
		return payeeMobile;
	}

	public void setPayeeMobile(Long payeeMobile) {
		this.payeeMobile = payeeMobile;
	}

	public String getGstInvoiceDocName() {
		return gstInvoiceDocName;
	}

	public void setGstInvoiceDocName(String gstInvoiceDocName) {
		this.gstInvoiceDocName = gstInvoiceDocName;
	}

	public String getItlEwCertificateName() {
		return itlEwCertificateName;
	}

	public void setItlEwCertificateName(String itlEwCertificateName) {
		this.itlEwCertificateName = itlEwCertificateName;
	}

	public String getItlEwDebitInvoice() {
		return itlEwDebitInvoice;
	}

	public void setItlEwDebitInvoice(String itlEwDebitInvoice) {
		this.itlEwDebitInvoice = itlEwDebitInvoice;
	}

	public Date getDateOfSaleOfCertificate() {
		return dateOfSaleOfCertificate;
	}

	public void setDateOfSaleOfCertificate(Date dateOfSaleOfCertificate) {
		this.dateOfSaleOfCertificate = dateOfSaleOfCertificate;
	}

	public String getItlPolicyNo() {
		return itlPolicyNo;
	}

	public void setItlPolicyNo(String itlPolicyNo) {
		this.itlPolicyNo = itlPolicyNo;
	}

	public Date getItlPolicyDate() {
		return itlPolicyDate;
	}

	public void setItlPolicyDate(Date itlPolicyDate) {
		this.itlPolicyDate = itlPolicyDate;
	}
	
	public String getInvoiceNo() {
		return invoiceNo;
	}

	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
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

	public String getServiceRecordEngine() {
		return serviceRecordEngine;
	}

	public void setServiceRecordEngine(String serviceRecordEngine) {
		this.serviceRecordEngine = serviceRecordEngine;
	}

	public String getGearOilChangeTransmission() {
		return gearOilChangeTransmission;
	}

	public void setGearOilChangeTransmission(String gearOilChangeTransmission) {
		this.gearOilChangeTransmission = gearOilChangeTransmission;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getClutch() {
		return clutch;
	}

	public void setClutch(String clutch) {
		this.clutch = clutch;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getBrakes() {
		return brakes;
	}

	public void setBrakes(String brakes) {
		this.brakes = brakes;
	}

	public String getHydraulic() {
		return hydraulic;
	}

	public void setHydraulic(String hydraulic) {
		this.hydraulic = hydraulic;
	}

	public String getTpl() {
		return tpl;
	}

	public void setTpl(String tpl) {
		this.tpl = tpl;
	}

	public String getCheckExternalCrackDamage() {
		return checkExternalCrackDamage;
	}

	public void setCheckExternalCrackDamage(String checkExternalCrackDamage) {
		this.checkExternalCrackDamage = checkExternalCrackDamage;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getItlEmployeeId() {
		return itlEmployeeId;
	}

	public void setItlEmployeeId(String itlEmployeeId) {
		this.itlEmployeeId = itlEmployeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	

	
	
  
}
