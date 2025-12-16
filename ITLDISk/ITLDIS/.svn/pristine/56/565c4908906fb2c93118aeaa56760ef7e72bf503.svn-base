/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author kundan.rastogi
 */
@Entity
@Table(name = "UM_DealerMaster")
@NamedQueries({
    @NamedQuery(name = "DealerMaster.findAll", query = "SELECT d FROM DealerMaster d"),
    @NamedQuery(name = "DealerMaster.findById", query = "SELECT d FROM DealerMaster d WHERE d.id = :id"),
    @NamedQuery(name = "DealerMaster.findByDealerCode", query = "SELECT d FROM DealerMaster d WHERE d.dealerCode = :dealerCode"),
    @NamedQuery(name = "DealerMaster.findByDealerName", query = "SELECT d FROM DealerMaster d WHERE d.dealerName = :dealerName"),
    @NamedQuery(name = "DealerMaster.findByLocationCode", query = "SELECT d FROM DealerMaster d WHERE d.locationCode = :locationCode"),
    @NamedQuery(name = "DealerMaster.findByBranchCode", query = "SELECT d FROM DealerMaster d WHERE d.branchCode = :branchCode"),
    @NamedQuery(name = "DealerMaster.findByLocation", query = "SELECT d FROM DealerMaster d WHERE d.location = :location"),
    @NamedQuery(name = "DealerMaster.findByAddress", query = "SELECT d FROM DealerMaster d WHERE d.address = :address"),
    @NamedQuery(name = "DealerMaster.findByDisttID", query = "SELECT d FROM DealerMaster d WHERE d.disttID = :disttID"),
    @NamedQuery(name = "DealerMaster.findByDisttName", query = "SELECT d FROM DealerMaster d WHERE d.disttName = :disttName"),
    @NamedQuery(name = "DealerMaster.findByStateID", query = "SELECT d FROM DealerMaster d WHERE d.stateID = :stateID"),
    @NamedQuery(name = "DealerMaster.findByStateName", query = "SELECT d FROM DealerMaster d WHERE d.stateName = :stateName"),
    @NamedQuery(name = "DealerMaster.findByContactNo", query = "SELECT d FROM DealerMaster d WHERE d.contactNo = :contactNo")})
public class DealerMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DealerCode")
    private String dealerCode;
    @Column(name = "DealerName")
    private String dealerName;
    @Basic(optional = false)
    @Column(name = "LocationCode")
    private String locationCode;
    @Column(name = "BranchCode")
    private String branchCode;
    @Column(name = "Location")
    private String location;
    @Column(name = "Address")
    private String address;
    @Column(name = "DisttID")
    private BigInteger disttID;
    @Column(name = "DisttName")
    private String disttName;
    @Column(name = "StateID")
    private BigInteger stateID;
    @Column(name = "StateName")
    private String stateName;
    @Column(name = "ContactNo")
    private String contactNo;
    
    public DealerMaster() {
    }

    public DealerMaster(Integer id) {
        this.id = id;
    }

    public DealerMaster(Integer id, String dealerCode, String locationCode) {
        this.id = id;
        this.dealerCode = dealerCode;
        this.locationCode = locationCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getBranchCode() {
        return branchCode;
    }

    public void setBranchCode(String branchCode) {
        this.branchCode = branchCode;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BigInteger getDisttID() {
        return disttID;
    }

    public void setDisttID(BigInteger disttID) {
        this.disttID = disttID;
    }

    public String getDisttName() {
        return disttName;
    }

    public void setDisttName(String disttName) {
        this.disttName = disttName;
    }

    public BigInteger getStateID() {
        return stateID;
    }

    public void setStateID(BigInteger stateID) {
        this.stateID = stateID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof DealerMaster)) {
            return false;
        }
        DealerMaster other = (DealerMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.DealerMaster[id=" + id + "]";
    }

    }
