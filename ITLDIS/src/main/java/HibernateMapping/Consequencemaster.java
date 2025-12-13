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
@Table(name = "MSW_consequencemaster")
@NamedQueries({
    @NamedQuery(name = "Consequencemaster.findAll", query = "SELECT c FROM Consequencemaster c"),
    @NamedQuery(name = "Consequencemaster.findByConsequenceCode", query = "SELECT c FROM Consequencemaster c WHERE c.consequenceCode = :consequenceCode"),
    @NamedQuery(name = "Consequencemaster.findByConsequenceDesc", query = "SELECT c FROM Consequencemaster c WHERE c.consequenceDesc = :consequenceDesc"),
    @NamedQuery(name = "Consequencemaster.findByIsActive", query = "SELECT c FROM Consequencemaster c WHERE c.isActive = :isActive"),
    @NamedQuery(name = "Consequencemaster.findByLastUpdatedDate", query = "SELECT c FROM Consequencemaster c WHERE c.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "Consequencemaster.findByLastUpdatedBy", query = "SELECT c FROM Consequencemaster c WHERE c.lastUpdatedBy = :lastUpdatedBy")})
public class Consequencemaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ConsequenceCode")
    private String consequenceCode;
    @Basic(optional = false)
    @Column(name = "ConsequenceDesc")
    private String consequenceDesc;
    @Basic(optional = false)
    @Column(name = "isActive")
    private char isActive;
    @Basic(optional = false)
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;

    public Consequencemaster() {
    }

    public Consequencemaster(String consequenceCode) {
        this.consequenceCode = consequenceCode;
    }

    public Consequencemaster(String consequenceCode, String consequenceDesc, char isActive, Date lastUpdatedDate) {
        this.consequenceCode = consequenceCode;
        this.consequenceDesc = consequenceDesc;
        this.isActive = isActive;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getConsequenceCode() {
        return consequenceCode;
    }

    public void setConsequenceCode(String consequenceCode) {
        this.consequenceCode = consequenceCode;
    }

    public String getConsequenceDesc() {
        return consequenceDesc;
    }

    public void setConsequenceDesc(String consequenceDesc) {
        this.consequenceDesc = consequenceDesc;
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
        hash += (consequenceCode != null ? consequenceCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Consequencemaster)) {
            return false;
        }
        Consequencemaster other = (Consequencemaster) object;
        if ((this.consequenceCode == null && other.consequenceCode != null) || (this.consequenceCode != null && !this.consequenceCode.equals(other.consequenceCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Consequencemaster[consequenceCode=" + consequenceCode + "]";
    }

}
