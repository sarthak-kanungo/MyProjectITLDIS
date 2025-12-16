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
 * @author vijay.mishra
 */
@Entity
@Table(name = "MSW_baymaster")
@NamedQueries({
    @NamedQuery(name = "Baymaster.findAll", query = "SELECT b FROM Baymaster b"),
    @NamedQuery(name = "Baymaster.findByBayCode", query = "SELECT b FROM Baymaster b WHERE b.bayCode = :bayCode"),
    @NamedQuery(name = "Baymaster.findByBayName", query = "SELECT b FROM Baymaster b WHERE b.bayName = :bayName"),
    @NamedQuery(name = "Baymaster.findByIsActive", query = "SELECT b FROM Baymaster b WHERE b.isActive = :isActive"),
    @NamedQuery(name = "Baymaster.findByLastUpdatedDate", query = "SELECT b FROM Baymaster b WHERE b.lastUpdatedDate = :lastUpdatedDate")})
public class Baymaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "BayCode")
    private String bayCode;
    @Basic(optional = false)
    @Column(name = "BayName")
    private String bayName;
    @Basic(optional = false)
    @Column(name = "isActive")
    private char isActive;
    @Basic(optional = false)
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;

    public Baymaster() {
    }

    public Baymaster(String bayCode) {
        this.bayCode = bayCode;
    }

    public Baymaster(String bayCode, String bayName, char isActive, Date lastUpdatedDate) {
        this.bayCode = bayCode;
        this.bayName = bayName;
        this.isActive = isActive;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getBayCode() {
        return bayCode;
    }

    public void setBayCode(String bayCode) {
        this.bayCode = bayCode;
    }

    public String getBayName() {
        return bayName;
    }

    public void setBayName(String bayName) {
        this.bayName = bayName;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (bayCode != null ? bayCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Baymaster)) {
            return false;
        }
        Baymaster other = (Baymaster) object;
        if ((this.bayCode == null && other.bayCode != null) || (this.bayCode != null && !this.bayCode.equals(other.bayCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Baymaster[bayCode=" + bayCode + "]";
    }

}
