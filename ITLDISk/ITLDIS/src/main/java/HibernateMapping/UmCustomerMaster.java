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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author yogasmita.patel
 */
@Entity
@Table(name = "UM_CustomerMaster")
public class UmCustomerMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CustomerID")
    private BigInteger customerID;
    @Basic(optional = false)
    @Column(name = "DealerCode")
    private String dealerCode;
    @Column(name = "CustCategoryID")
    @Basic(optional = false)
    private Integer custCategoryID;
    @Column(name = "CustomerCode")
    @Basic(optional = false)
    private String customerCode;
    @Column(name = "CustomerName")
    @Basic(optional = false)
    private String customerName;
    @Column(name = "CustomerLocation")
    private String customerLocation;
    @Column(name = "LocationVillageID")
    private Integer locationVillageID;
    @Column(name = "CustomerTehsil")
    private String customerTehsil;
    @Column(name = "TehsilID")
    private Integer tehsilID;
    @Column(name = "CustomerBlock")
    private String customerBlock;
    @Column(name = "BlockID")
    private Integer blockID;
    @Column(name = "CustomerDistrict")
    private String customerDistrict;
    @Column(name = "DistrictID")
    private Integer districtID;
    @Column(name = "CustomerState")
    private String customerState;
    @Column(name = "StateID")
    private Integer stateID;
    @Column(name = "CustomerCountry")
    private String customerCountry;
    @Column(name = "CountryID")
    private Integer countryID;
    @Column(name = "ContactPerson")
    private String contactPerson;
    @Basic(optional = false)
    @Column(name = "ContactMobile")
    private String contactMobile;
    @Column(name = "ContactEmail")
    private String contactEmail;
    @Column(name = "ContactDOB")
    @Temporal(TemporalType.TIMESTAMP)
    private Date contactDOB;
    @Basic(optional = false)
    @Column(name = "DiscountPercentage")
    private Float discountPercentage;
    @Basic(optional = false)
    @Column(name = "CustomerTarget")
    private Double customerTarget;
    @Basic(optional = false)
    @Column(name = "CreditLimit")
    private Double creditLimit;
    @Column(name = "TransporterInUse")
    private String transporterInUse;
    @Basic(optional = false)
    @Column(name = "IsActive")
    private Character isActive;
    @Basic(optional = false)
    @Column(name = "CreatedBy")
    private String createdBy;
    @Column(name = "CreatedOn")
    @Basic(optional = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "ModifiedBy")
    private String modifiedBy;
    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    @Column(name = "TMS_CustID")
    private String tms_CustID;
    @Column(name = "Pincode")
    private String pincode;
    @Column(name = "PAN_NO")
    private String panNo;
    @Column(name = "TIN_NO")
    private String tinNo;
    @Column(name = "GST_NO")
    private String gstNo;

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }


    public Date getContactDOB() {
        return contactDOB;
    }

    public void setContactDOB(Date contactDOB) {
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

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
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

    public BigInteger getCustomerID() {
        return customerID;
    }

    public void setCustomerID(BigInteger customerID) {
        this.customerID = customerID;
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

    public Character getIsActive() {
        return isActive;
    }

    public void setIsActive(Character isActive) {
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
    

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UmCustomerMaster other = (UmCustomerMaster) obj;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + (this.customerID != null ? this.customerID.hashCode() : 0);
        return hash;
    }
}
