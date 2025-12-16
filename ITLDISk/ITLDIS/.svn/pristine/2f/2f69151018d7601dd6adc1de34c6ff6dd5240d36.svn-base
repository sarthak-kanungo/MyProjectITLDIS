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
@Table(name = "MSP_IncoTerms_EXP")
@NamedQueries({
    @NamedQuery(name = "MSPIncoTermsEXPMaster.findAll", query = "SELECT m FROM MSPIncoTermsEXPMaster m"),
    @NamedQuery(name = "MSPIncoTermsEXPMaster.findByIncoTermCode", query = "SELECT m FROM MSPIncoTermsEXPMaster m WHERE m.incoTermCode = :incoTermCode"),
    @NamedQuery(name = "MSPIncoTermsEXPMaster.findByIncoTermDesc", query = "SELECT m FROM MSPIncoTermsEXPMaster m WHERE m.incoTermDesc = :incoTermDesc"),
    @NamedQuery(name = "MSPIncoTermsEXPMaster.findByIsActive", query = "SELECT m FROM MSPIncoTermsEXPMaster m WHERE m.isActive = :isActive"),
    @NamedQuery(name = "MSPIncoTermsEXPMaster.findByLastUpdatedDate", query = "SELECT m FROM MSPIncoTermsEXPMaster m WHERE m.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "MSPIncoTermsEXPMaster.findByLastUpdatedBy", query = "SELECT m FROM MSPIncoTermsEXPMaster m WHERE m.lastUpdatedBy = :lastUpdatedBy")})
public class MSPIncoTermsEXPMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IncoTermCode")
    private String incoTermCode;
    @Basic(optional = false)
    @Column(name = "IncoTermDesc")
    private String incoTermDesc;
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

    public MSPIncoTermsEXPMaster() {
    }

    public MSPIncoTermsEXPMaster(String incoTermCode) {
        this.incoTermCode = incoTermCode;
    }

    public MSPIncoTermsEXPMaster(String incoTermCode, String incoTermDesc, char isActive, Date lastUpdatedDate, String lastUpdatedBy) {
        this.incoTermCode = incoTermCode;
        this.incoTermDesc = incoTermDesc;
        this.isActive = isActive;
        this.lastUpdatedDate = lastUpdatedDate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getIncoTermCode() {
        return incoTermCode;
    }

    public void setIncoTermCode(String incoTermCode) {
        this.incoTermCode = incoTermCode;
    }

    public String getIncoTermDesc() {
        return incoTermDesc;
    }

    public void setIncoTermDesc(String incoTermDesc) {
        this.incoTermDesc = incoTermDesc;
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
        hash += (incoTermCode != null ? incoTermCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MSPIncoTermsEXPMaster)) {
            return false;
        }
        MSPIncoTermsEXPMaster other = (MSPIncoTermsEXPMaster) object;
        if ((this.incoTermCode == null && other.incoTermCode != null) || (this.incoTermCode != null && !this.incoTermCode.equals(other.incoTermCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.MSPIncoTermsEXPMaster[incoTermCode=" + incoTermCode + "]";
    }

}
