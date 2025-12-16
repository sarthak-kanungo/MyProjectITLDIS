/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "MSWD_mechanicmaster")
@NamedQueries({
    @NamedQuery(name = "MSWDmechanicmaster.findAll", query = "SELECT m FROM MSWDmechanicmaster m"),
    @NamedQuery(name = "MSWDmechanicmaster.findByMechanicDealerKey", query = "SELECT m FROM MSWDmechanicmaster m WHERE m.mechanicDealerKey = :mechanicDealerKey"),
    @NamedQuery(name = "MSWDmechanicmaster.findByDealerCode", query = "SELECT m FROM MSWDmechanicmaster m WHERE m.dealerCode = :dealerCode"),
    @NamedQuery(name = "MSWDmechanicmaster.findByMechanicCode", query = "SELECT m FROM MSWDmechanicmaster m WHERE m.mechanicCode = :mechanicCode"),
    @NamedQuery(name = "MSWDmechanicmaster.findByMechanicName", query = "SELECT m FROM MSWDmechanicmaster m WHERE m.mechanicName = :mechanicName"),
    @NamedQuery(name = "MSWDmechanicmaster.findByWorkertype", query = "SELECT m FROM MSWDmechanicmaster m WHERE m.workertype = :workertype"),
    @NamedQuery(name = "MSWDmechanicmaster.findByIsActive", query = "SELECT m FROM MSWDmechanicmaster m WHERE m.isActive = :isActive"),
    @NamedQuery(name = "MSWDmechanicmaster.findByLastUpdatedDate", query = "SELECT m FROM MSWDmechanicmaster m WHERE m.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "MSWDmechanicmaster.findByLastUpdatedBy", query = "SELECT m FROM MSWDmechanicmaster m WHERE m.lastUpdatedBy = :lastUpdatedBy")})
public class MSWDmechanicmaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "MechanicDealerKey")
    private String mechanicDealerKey;
    @Basic(optional = false)
    @Column(name = "Dealer_Code")
    private String dealerCode;
    @Basic(optional = false)
    @Column(name = "MechanicCode")
    private String mechanicCode;
    @Basic(optional = false)
    @Column(name = "MechanicName")
    private String mechanicName;
    @Column(name = "Workertype")
    private String workertype;
    @Basic(optional = false)
    @Column(name = "isActive")
    private char isActive;
    @Basic(optional = false)
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;
    @OneToMany(mappedBy = "mechanicDealerKey")
    private Collection<InstallationDetails> installationDetailsCollection;

    public MSWDmechanicmaster() {
    }

    public MSWDmechanicmaster(String mechanicDealerKey) {
        this.mechanicDealerKey = mechanicDealerKey;
    }

    public MSWDmechanicmaster(String mechanicDealerKey, String dealerCode, String mechanicCode, String mechanicName, char isActive, Date lastUpdatedDate) {
        this.mechanicDealerKey = mechanicDealerKey;
        this.dealerCode = dealerCode;
        this.mechanicCode = mechanicCode;
        this.mechanicName = mechanicName;
        this.isActive = isActive;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getMechanicDealerKey() {
        return mechanicDealerKey;
    }

    public void setMechanicDealerKey(String mechanicDealerKey) {
        this.mechanicDealerKey = mechanicDealerKey;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getMechanicCode() {
        return mechanicCode;
    }

    public void setMechanicCode(String mechanicCode) {
        this.mechanicCode = mechanicCode;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }

    public String getWorkertype() {
        return workertype;
    }

    public void setWorkertype(String workertype) {
        this.workertype = workertype;
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

    public Collection<InstallationDetails> getInstallationDetailsCollection() {
        return installationDetailsCollection;
    }

    public void setInstallationDetailsCollection(Collection<InstallationDetails> installationDetailsCollection) {
        this.installationDetailsCollection = installationDetailsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mechanicDealerKey != null ? mechanicDealerKey.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MSWDmechanicmaster)) {
            return false;
        }
        MSWDmechanicmaster other = (MSWDmechanicmaster) object;
        if ((this.mechanicDealerKey == null && other.mechanicDealerKey != null) || (this.mechanicDealerKey != null && !this.mechanicDealerKey.equals(other.mechanicDealerKey))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.MSWDmechanicmaster[mechanicDealerKey=" + mechanicDealerKey + "]";
    }

}
