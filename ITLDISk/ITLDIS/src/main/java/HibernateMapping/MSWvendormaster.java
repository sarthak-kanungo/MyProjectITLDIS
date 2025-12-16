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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author megha.pandya
 */
@Entity
@Table(name = "MSW_vendormaster")
@NamedQueries({
    @NamedQuery(name = "MSWvendormaster.findAll", query = "SELECT m FROM MSWvendormaster m"),
    @NamedQuery(name = "MSWvendormaster.findByVendorCode", query = "SELECT m FROM MSWvendormaster m WHERE m.vendorCode = :vendorCode"),
    @NamedQuery(name = "MSWvendormaster.findByVendorDesc", query = "SELECT m FROM MSWvendormaster m WHERE m.vendorDesc = :vendorDesc"),
    @NamedQuery(name = "MSWvendormaster.findByIsActive", query = "SELECT m FROM MSWvendormaster m WHERE m.isActive = :isActive"),
    @NamedQuery(name = "MSWvendormaster.findByLastUpdatedDate", query = "SELECT m FROM MSWvendormaster m WHERE m.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "MSWvendormaster.findByLastUpdatedBy", query = "SELECT m FROM MSWvendormaster m WHERE m.lastUpdatedBy = :lastUpdatedBy")})
public class MSWvendormaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "VendorCode")
    private String vendorCode;
    @Basic(optional = false)
    @Column(name = "VendorDesc")
    private String vendorDesc;
    @Basic(optional = false)
    @Column(name = "IsActive")
    private char isActive;
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;

    public MSWvendormaster() {
    }

    public MSWvendormaster(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public MSWvendormaster(String vendorCode, String vendorDesc, char isActive) {
        this.vendorCode = vendorCode;
        this.vendorDesc = vendorDesc;
        this.isActive = isActive;
    }

    public String getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String getVendorDesc() {
        return vendorDesc;
    }

    public void setVendorDesc(String vendorDesc) {
        this.vendorDesc = vendorDesc;
    }

    public char getIsActive() {
        return isActive;
    }

    public void setIsActive(char isActive) {
        this.isActive = isActive;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vendorCode != null ? vendorCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MSWvendormaster)) {
            return false;
        }
        MSWvendormaster other = (MSWvendormaster) object;
        if ((this.vendorCode == null && other.vendorCode != null) || (this.vendorCode != null && !this.vendorCode.equals(other.vendorCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.MSWvendormaster[vendorCode=" + vendorCode + "]";
    }

}
