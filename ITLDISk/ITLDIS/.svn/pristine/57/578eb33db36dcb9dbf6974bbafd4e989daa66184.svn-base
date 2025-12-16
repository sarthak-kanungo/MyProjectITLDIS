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
@Table(name = "MSW_wagemaster")
@NamedQueries({
    @NamedQuery(name = "Wagemaster.findAll", query = "SELECT w FROM Wagemaster w"),
    @NamedQuery(name = "Wagemaster.findByDealerCode", query = "SELECT w FROM Wagemaster w WHERE w.dealerCode = :dealerCode"),
    @NamedQuery(name = "Wagemaster.findByWageName", query = "SELECT w FROM Wagemaster w WHERE w.wageName = :wageName"),
    @NamedQuery(name = "Wagemaster.findByWageValue", query = "SELECT w FROM Wagemaster w WHERE w.wageValue = :wageValue"),
    @NamedQuery(name = "Wagemaster.findByIsActive", query = "SELECT w FROM Wagemaster w WHERE w.isActive = :isActive"),
    @NamedQuery(name = "Wagemaster.findByLastUpdatedDate", query = "SELECT w FROM Wagemaster w WHERE w.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "Wagemaster.findByLastUpdatedBy", query = "SELECT w FROM Wagemaster w WHERE w.lastUpdatedBy = :lastUpdatedBy")})
public class Wagemaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "dealerCode")
    private String dealerCode;
    @Basic(optional = false)
    @Column(name = "WageName")
    private String wageName;
    @Basic(optional = false)
    @Column(name = "WageValue")
    private double wageValue;
    @Basic(optional = false)
    @Column(name = "isActive")
    private char isActive;
    @Basic(optional = false)
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;

    public Wagemaster() {
    }

    public Wagemaster(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public Wagemaster(String dealerCode, String wageName, double wageValue, char isActive, Date lastUpdatedDate) {
        this.dealerCode = dealerCode;
        this.wageName = wageName;
        this.wageValue = wageValue;
        this.isActive = isActive;
        this.lastUpdatedDate = lastUpdatedDate;
    }


    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getWageName() {
        return wageName;
    }

    public void setWageName(String wageName) {
        this.wageName = wageName;
    }

    public double getWageValue() {
        return wageValue;
    }

    public void setWageValue(double wageValue) {
        this.wageValue = wageValue;
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
        hash += (dealerCode != null ? dealerCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Wagemaster)) {
            return false;
        }
        Wagemaster other = (Wagemaster) object;
        if ((this.dealerCode == null && other.dealerCode != null) || (this.dealerCode != null && !this.dealerCode.equals(other.dealerCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Wagemaster[dealerCode=" + dealerCode + "]";
    }

}
