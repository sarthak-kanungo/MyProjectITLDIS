
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ashutosh.Kumar1
 */
@Entity
@Table(name = "UM_DMS_DealerMaster")
public class UmDmsDealerMaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DealerCode")
    private String dealerCode;
    @Basic(optional = false)
    @Column(name = "DealerName")
    private String dealerName;
    @Basic(optional = false)
    @Column(name = "LocationCode")
    private String locationCode;
    @Basic(optional = false)
    @Column(name = "Location")
    private String location;
    @Column(name = "Address")
    private String address;
    @Column(name = "DisttID")
    private long disttID;
    @Column(name = "DisttName")
    private String disttName;
    @Column(name = "StateID")
    private long stateID;
    @Column(name = "StateName")
    private String stateName;
    @Column(name = "ContactNo")
    private String contactNo;
    @Column(name = "Status")
    private String status;
    @Column(name = "TinNo")
    private String tinNo;
    @Column(name = "NetworkCategory")
    private String networkCategory;
    @Column(name = "CountryCode")
    private String countryCode;
    @Column(name = "DealerCategory")
    private String dealerCategory;
    @Column(name = "LastUpdatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedOn;
    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;

    public UmDmsDealerMaster(String dealerCode, String dealerName, String locationCode, String location, String address, Long disttID, String disttName, Long stateID, String stateName, String contactNo, String status, String tinNo, String countryCode, String dealerCategory, String networkCategory, String lastUpdatedBy, Date lastUpdatedOn) {
        this.dealerCode = dealerCode;
        this.dealerName = dealerName;
        this.locationCode = locationCode;
        this.location = location;
        this.address = address;
        this.disttID = disttID;
        this.disttName = disttName;
        this.stateID = stateID;
        this.stateName = stateName;
        this.contactNo = contactNo;
        this.status = status;
        this.tinNo = tinNo;
        this.countryCode = countryCode;
        this.dealerCategory = dealerCategory;
        this.networkCategory = networkCategory;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedOn = lastUpdatedOn;

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

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getDealerCategory() {
        return dealerCategory;
    }

    public void setDealerCategory(String dealerCategory) {
        this.dealerCategory = dealerCategory;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public long getDisttID() {
        return disttID;
    }

    public void setDisttID(long disttID) {
        this.disttID = disttID;
    }

    public String getDisttName() {
        return disttName;
    }

    public void setDisttName(String disttName) {
        this.disttName = disttName;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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

    public long getStateID() {
        return stateID;
    }

    public void setStateID(long stateID) {
        this.stateID = stateID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTinNo() {
        return tinNo;
    }

    public void setTinNo(String tinNo) {
        this.tinNo = tinNo;
    }

    public UmDmsDealerMaster() {
    }
}
