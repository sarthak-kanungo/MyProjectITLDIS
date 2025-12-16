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
 * @author kundan.rastogi
 */
@Entity
@Table(name = "MSW_actioncodemaster")
@NamedQueries({
    @NamedQuery(name = "Actioncodemaster.findAll", query = "SELECT a FROM Actioncodemaster a"),
    @NamedQuery(name = "Actioncodemaster.findByActionCode", query = "SELECT a FROM Actioncodemaster a WHERE a.actionCode = :actionCode"),
    @NamedQuery(name = "Actioncodemaster.findByActionDesc", query = "SELECT a FROM Actioncodemaster a WHERE a.actionDesc = :actionDesc"),
    @NamedQuery(name = "Actioncodemaster.findByCompCode", query = "SELECT a FROM Actioncodemaster a WHERE a.compCode = :compCode"),
    @NamedQuery(name = "Actioncodemaster.findByServiceHrs", query = "SELECT a FROM Actioncodemaster a WHERE a.serviceHrs = :serviceHrs"),
    @NamedQuery(name = "Actioncodemaster.findByIsActive", query = "SELECT a FROM Actioncodemaster a WHERE a.isActive = :isActive"),
    @NamedQuery(name = "Actioncodemaster.findByLastUpdatedDate", query = "SELECT a FROM Actioncodemaster a WHERE a.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "Actioncodemaster.findByLastUpdatedBy", query = "SELECT a FROM Actioncodemaster a WHERE a.lastUpdatedBy = :lastUpdatedBy")})
public class Actioncodemaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ActionCode")
    private String actionCode;
    @Basic(optional = false)
    @Column(name = "ActionDesc")
    private String actionDesc;
    @Basic(optional = false)
    @Column(name = "CompCode")
    private String compCode;
    @Basic(optional = false)
    @Column(name = "ServiceHrs")
    private double serviceHrs;
    @Basic(optional = false)
    @Column(name = "isActive")
    private char isActive;
    @Basic(optional = false)
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;


    public Actioncodemaster() {
    }

    public Actioncodemaster(String actionCode) {
        this.actionCode = actionCode;
    }

    public Actioncodemaster(String actionCode, String actionDesc, String compCode, double serviceHrs, char isActive, Date lastUpdatedDate) {
        this.actionCode = actionCode;
        this.actionDesc = actionDesc;
        this.compCode = compCode;
        this.serviceHrs = serviceHrs;
        this.isActive = isActive;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getActionCode() {
        return actionCode;
    }

    public void setActionCode(String actionCode) {
        this.actionCode = actionCode;
    }

    public String getActionDesc() {
        return actionDesc;
    }

    public void setActionDesc(String actionDesc) {
        this.actionDesc = actionDesc;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public double getServiceHrs() {
        return serviceHrs;
    }

    public void setServiceHrs(double serviceHrs) {
        this.serviceHrs = serviceHrs;
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
        hash += (actionCode != null ? actionCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Actioncodemaster)) {
            return false;
        }
        Actioncodemaster other = (Actioncodemaster) object;
        if ((this.actionCode == null && other.actionCode != null) || (this.actionCode != null && !this.actionCode.equals(other.actionCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Actioncodemaster[actionCode=" + actionCode + "]";
    }

}
