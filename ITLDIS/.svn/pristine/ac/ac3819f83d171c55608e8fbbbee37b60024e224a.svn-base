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
 * @author avinash.pandey
 */
@Entity
@Table(name = "MSP_Countries_EXP")
@NamedQueries({
    @NamedQuery(name = "MSPCountriesEXPMaster.findAll", query = "SELECT m FROM MSPCountriesEXPMaster m"),
    @NamedQuery(name = "MSPCountriesEXPMaster.findByCountryCode", query = "SELECT m FROM MSPCountriesEXPMaster m WHERE m.countryCode = :countryCode"),
    @NamedQuery(name = "MSPCountriesEXPMaster.findByCountryName", query = "SELECT m FROM MSPCountriesEXPMaster m WHERE m.countryName = :countryName"),
    @NamedQuery(name = "MSPCountriesEXPMaster.findByIsActive", query = "SELECT m FROM MSPCountriesEXPMaster m WHERE m.isActive = :isActive"),
    @NamedQuery(name = "MSPCountriesEXPMaster.findByLastUpdatedDate", query = "SELECT m FROM MSPCountriesEXPMaster m WHERE m.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "MSPCountriesEXPMaster.findByLastUpdatedBy", query = "SELECT m FROM MSPCountriesEXPMaster m WHERE m.lastUpdatedBy = :lastUpdatedBy")})

public class MSPCountriesEXPMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CountryCode")
    private String countryCode;
    @Basic(optional = false)
    @Column(name = "CountryName")
    private String countryName;
    @Basic(optional = false)
    @Column(name = "isActive")
    private char isActive;
    @Basic(optional = false)
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Basic(optional = false)
    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;

    public MSPCountriesEXPMaster() {
    }

    public MSPCountriesEXPMaster(String countryCode) {
        this.countryCode = countryCode;
    }

    public MSPCountriesEXPMaster(String countryCode, String countryName, char isActive, Date lastUpdatedDate, String lastUpdatedBy) {
        this.countryCode = countryCode;
        this.countryName = countryName;
        this.isActive = isActive;
        this.lastUpdatedDate = lastUpdatedDate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
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
        hash += (countryCode != null ? countryCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MSPCountriesEXPMaster)) {
            return false;
        }
        MSPCountriesEXPMaster other = (MSPCountriesEXPMaster) object;
        if ((this.countryCode == null && other.countryCode != null) || (this.countryCode != null && !this.countryCode.equals(other.countryCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.MSPCountriesEXPMaster[countryCode=" + countryCode + "]";
    }

}
