/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.util.Date;
import java.util.LinkedHashSet;

import org.apache.struts.action.ActionForm;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author yogasmita.patel
 */
public class ManageCustomerForm extends ActionForm {

    private LinkedHashSet<LabelValueBean> customerCatgryList;
    private LinkedHashSet<LabelValueBean> paymentModeList;
    private String dealerCode;
    private Integer custCategoryID;
    private String customerCode;
    private String customerName;
    private String customerLocation;
    private String customerTehsil;
    private String customerDistrict;
    private String customerState;
    private String customerCountry;
    private String contactPerson;
    private String contactMobile;
    private String contactEmail;
    private String contactDOB;
    private Float discountPercentage;
    private Double customerTarget;
    private Double creditLimit;
    private Double availableCreditLimit;
    private Double paymentDue;
    private String transporterInUse;
    private String isActive;
    private String createdBy;
    private String createdOn;
    private String modifiedBy;
    private Date modifiedOn;
    private String tms_CustID;
    private String custCategory;
    private String customerId;
    private String paymentDate;
    private Double amount;
    private String paymentMode;
    private String remarks;
    private String searchName;
    private String searchLoc;
    private String searchCont;
    private String fromdate;
    private String todate;
    private Integer paymentId;
    private Float totalDiscountPercentage;
    private String searchCode;
    private String panNo;
    private String tinNo;
    private String sonalikaId;
    private Integer blockID;
    private Integer countryID;
    private String customerBlock;
    private Integer districtID;
    private Integer locationVillageID;
    private String pincode;
    private LinkedHashSet<LabelValueBean> countryList;
    private LinkedHashSet<LabelValueBean> stateList;
    private LinkedHashSet<LabelValueBean> districtList;
    private LinkedHashSet<LabelValueBean> tehsilList;
    private LinkedHashSet<LabelValueBean> blockList;
    private LinkedHashSet<LabelValueBean> villageList;
    private Integer stateID;
    private Integer tehsilID;
    private String stateName;
    private LinkedHashSet<LabelValueBean> stateNameList;
    private String commodityCodeTax;
    private String effectiveDate;
    private String taxCategory;
    private String gstNo;

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    
    public Integer getBlockID() {
        return blockID;
    }

    public void setBlockID(Integer blockID) {
        this.blockID = blockID;
    }

    public Integer getCountryID() {
        return countryID;
    }

    public void setCountryID(Integer countryID) {
        this.countryID = countryID;
    }

    public String getCustomerBlock() {
        return customerBlock;
    }

    public void setCustomerBlock(String customerBlock) {
        this.customerBlock = customerBlock;
    }

    public Integer getDistrictID() {
        return districtID;
    }

    public void setDistrictID(Integer districtID) {
        this.districtID = districtID;
    }

    public Integer getLocationVillageID() {
        return locationVillageID;
    }

    public void setLocationVillageID(Integer locationVillageID) {
        this.locationVillageID = locationVillageID;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public Integer getStateID() {
        return stateID;
    }

    public void setStateID(Integer stateID) {
        this.stateID = stateID;
    }

    public Integer getTehsilID() {
        return tehsilID;
    }

    public void setTehsilID(Integer tehsilID) {
        this.tehsilID = tehsilID;
    }
 
    public String getContactDOB() {
        return contactDOB;
    }

    public void setContactDOB(String contactDOB) {
        this.contactDOB = contactDOB;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public Double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public Integer getCustCategoryID() {
        return custCategoryID;
    }

    public void setCustCategoryID(Integer custCategoryID) {
        this.custCategoryID = custCategoryID;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerCountry() {
        return customerCountry;
    }

    public void setCustomerCountry(String customerCountry) {
        this.customerCountry = customerCountry;
    }

    public String getCustomerDistrict() {
        return customerDistrict;
    }

    public void setCustomerDistrict(String customerDistrict) {
        this.customerDistrict = customerDistrict;
    }

    public LinkedHashSet<LabelValueBean> getCustomerCatgryList() {
        return customerCatgryList;
    }

    public void setCustomerCatgryList(LinkedHashSet<LabelValueBean> customerCatgryList) {
        this.customerCatgryList = customerCatgryList;
    }

    public String getCustomerLocation() {
        return customerLocation;
    }

    public void setCustomerLocation(String customerLocation) {
        this.customerLocation = customerLocation;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerState() {
        return customerState;
    }

    public void setCustomerState(String customerState) {
        this.customerState = customerState;
    }

    public Double getCustomerTarget() {
        return customerTarget;
    }

    public void setCustomerTarget(Double customerTarget) {
        this.customerTarget = customerTarget;
    }

    public String getCustomerTehsil() {
        return customerTehsil;
    }

    public void setCustomerTehsil(String customerTehsil) {
        this.customerTehsil = customerTehsil;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public Float getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(Float discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
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

    public String getTms_CustID() {
        return tms_CustID;
    }

    public void setTms_CustID(String tms_CustID) {
        this.tms_CustID = tms_CustID;
    }

    public String getTransporterInUse() {
        return transporterInUse;
    }

    public void setTransporterInUse(String transporterInUse) {
        this.transporterInUse = transporterInUse;
    }

    public String getCustCategory() {
        return custCategory;
    }

    public void setCustCategory(String custCategory) {
        this.custCategory = custCategory;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getSearchCont() {
        return searchCont;
    }

    public void setSearchCont(String searchCont) {
        this.searchCont = searchCont;
    }

    public String getSearchLoc() {
        return searchLoc;
    }

    public void setSearchLoc(String searchLoc) {
        this.searchLoc = searchLoc;
    }

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public LinkedHashSet<LabelValueBean> getPaymentModeList() {
        return paymentModeList;
    }

    public void setPaymentModeList(LinkedHashSet<LabelValueBean> paymentModeList) {
        this.paymentModeList = paymentModeList;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Integer getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Integer paymentId) {
        this.paymentId = paymentId;
    }

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public Double getAvailableCreditLimit() {
        return availableCreditLimit;
    }

    public void setAvailableCreditLimit(Double availableCreditLimit) {
        this.availableCreditLimit = availableCreditLimit;
    }

    public Double getPaymentDue() {
        return paymentDue;
    }

    public void setPaymentDue(Double paymentDue) {
        this.paymentDue = paymentDue;
    }

    /**
     * @return the totalDiscountPercentage
     */
    public Float getTotalDiscountPercentage() {
        return totalDiscountPercentage;
    }

    /**
     * @param totalDiscountPercentage the totalDiscountPercentage to set
     */
    public void setTotalDiscountPercentage(Float totalDiscountPercentage) {
        this.totalDiscountPercentage = totalDiscountPercentage;
    }

    /**
     * @return the searchCode
     */
    public String getSearchCode() {
        return searchCode;
    }

    /**
     * @param searchCode the searchCode to set
     */
    public void setSearchCode(String searchCode) {
        this.searchCode = searchCode;
    }
public String getPanNo() {
        return panNo;
    }

    public void setPanNo(String panNo) {
        this.panNo = panNo;
    }

    public String getTinNo() {
        return tinNo;
    }

    public void setTinNo(String tinNo) {
        this.tinNo = tinNo;
    }

    public String getSonalikaId() {
        return sonalikaId;
    }

    public void setSonalikaId(String sonalikaId) {
        this.sonalikaId = sonalikaId;
    }

    public LinkedHashSet<LabelValueBean> getBlockList() {
        return blockList;
    }

    public void setBlockList(LinkedHashSet<LabelValueBean> blockList) {
        this.blockList = blockList;
    }

    public LinkedHashSet<LabelValueBean> getCountryList() {
        return countryList;
    }

    public void setCountryList(LinkedHashSet<LabelValueBean> countryList) {
        this.countryList = countryList;
    }

    public LinkedHashSet<LabelValueBean> getDistrictList() {
        return districtList;
    }

    public void setDistrictList(LinkedHashSet<LabelValueBean> districtList) {
        this.districtList = districtList;
    }

    public LinkedHashSet<LabelValueBean> getStateList() {
        return stateList;
    }

    public void setStateList(LinkedHashSet<LabelValueBean> stateList) {
        this.stateList = stateList;
    }

    public LinkedHashSet<LabelValueBean> getTehsilList() {
        return tehsilList;
    }

    public void setTehsilList(LinkedHashSet<LabelValueBean> tehsilList) {
        this.tehsilList = tehsilList;
    }

    public LinkedHashSet<LabelValueBean> getVillageList() {
        return villageList;
    }

    public void setVillageList(LinkedHashSet<LabelValueBean> villageList) {
        this.villageList = villageList;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public LinkedHashSet<LabelValueBean> getStateNameList() {
        return stateNameList;
    }

    public void setStateNameList(LinkedHashSet<LabelValueBean> stateNameList) {
        this.stateNameList = stateNameList;
    }

    public String getCommodityCodeTax() {
        return commodityCodeTax;
    }

    public void setCommodityCodeTax(String commodityCodeTax) {
        this.commodityCodeTax = commodityCodeTax;
    }   

    public String getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(String effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public String getTaxCategory() {
        return taxCategory;
    }

    public void setTaxCategory(String taxCategory) {
        this.taxCategory = taxCategory;
    }
    
}
