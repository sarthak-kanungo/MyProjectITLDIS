/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "UM_DealerMasterAllUser")

public class DealervslocationcodeAll implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
//    @Column(name = "Id")
//    private Integer id;
    @Basic(optional = false)
    @Column(name = "DealerCode")
    private String dealerCode;
    @Basic(optional = false)
    @Column(name = "LocationCode")
    private String locationCode;
     @Column(name = "DealerName")
    private String dealerName;
    @Column(name = "Location")
    private String location;
    @Column(name = "Address")
    private String address;
    @Column(name = "DisttName")
    private String disttName;
    @Column(name = "StateID")
    private String stateId;
    @Column(name = "StateName")
    private String stateName;
    @Column(name = "ContactNo")
    private String contactNo;
    @Column(name = "TinNo")
    private String tinNo;
    @Column(name = "GST_NO")
    private String gstNo;
    @Column(name = "NetworkCategory")
    private String networkCategory;
    @Column(name = "STOCKIST_ID")
    private String stockistId;
    @Column(name = "Stockist_Name")
    private String stockistName;
    @Column(name = "DealerCategory")
    private String dealerCategory;
    @Column(name = "CountryCode")
    private String countryCode;

    public DealervslocationcodeAll() {
    }

    public DealervslocationcodeAll(Integer id) {
        this.dealerCode = dealerCode;
    }

    public DealervslocationcodeAll(Integer id, String dealerCode, String locationCode) {
//        this.id = id;
        this.dealerCode = dealerCode;
        this.locationCode = locationCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDisttName() {
        return disttName;
    }

    public void setDisttName(String disttName) {
        this.disttName = disttName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStateId() {
        return stateId;
    }

    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getTinNo() {
        return tinNo;
    }

    public void setTinNo(String tinNo) {
        this.tinNo = tinNo;
    }

     public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

//    public Integer getId() {
//        return id;
//    }
//
//    public void setId(Integer id) {
//        this.id = id;
//    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getNetworkCategory() {
        return networkCategory;
    }

    public void setNetworkCategory(String networkCategory) {
        this.networkCategory = networkCategory;
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

    
//    public String getBranchCode() {
//        return branchCode;
//    }
//
//    public void setBranchCode(String branchCode) {
//        this.branchCode = branchCode;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dealerCode != null ? dealerCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DealervslocationcodeAll)) {
            return false;
        }
        DealervslocationcodeAll other = (DealervslocationcodeAll) object;
        if ((this.dealerCode == null && other.dealerCode != null) || (this.dealerCode != null && !this.dealerCode.equals(other.dealerCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "beans.DealervslocationcodeAll[dealerCode=" + dealerCode + "]";
    }

    /**
     * @return the dealerCategory
     */
    public String getDealerCategory() {
        return dealerCategory;
    }

    /**
     * @param dealerCategory the dealerCategory to set
     */
    public void setDealerCategory(String dealerCategory) {
        this.dealerCategory = dealerCategory;
    }

    /**
     * @return the countryCode
     */
    public String getCountryCode() {
        return countryCode;
    }

    /**
     * @param countryCode the countryCode to set
     */
    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

}
