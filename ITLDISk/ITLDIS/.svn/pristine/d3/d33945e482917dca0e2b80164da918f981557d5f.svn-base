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
@Table(name = "MSW_RejectionCodeMaster")
@NamedQueries({
    @NamedQuery(name = "SAPRejectionCodeMaster.findAll", query = "SELECT s FROM SAPRejectionCodeMaster s"),
    @NamedQuery(name = "SAPRejectionCodeMaster.findByRejectionCode", query = "SELECT s FROM SAPRejectionCodeMaster s WHERE s.rejectionCode = :rejectionCode"),
    @NamedQuery(name = "SAPRejectionCodeMaster.findByRejectionDesc", query = "SELECT s FROM SAPRejectionCodeMaster s WHERE s.rejectionDesc = :rejectionDesc"),
    @NamedQuery(name = "SAPRejectionCodeMaster.findByIsActive", query = "SELECT s FROM SAPRejectionCodeMaster s WHERE s.isActive = :isActive"),
    @NamedQuery(name = "SAPRejectionCodeMaster.findByLastUpdatedDate", query = "SELECT s FROM SAPRejectionCodeMaster s WHERE s.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "SAPRejectionCodeMaster.findByLastUpdatedBy", query = "SELECT s FROM SAPRejectionCodeMaster s WHERE s.lastUpdatedBy = :lastUpdatedBy")})
public class SAPRejectionCodeMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RejectionCode")
    private String rejectionCode;
    @Basic(optional = false)
    @Column(name = "RejectionDesc")
    private String rejectionDesc;
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

    public SAPRejectionCodeMaster() {
    }

    public SAPRejectionCodeMaster(String rejectionCode) {
        this.rejectionCode = rejectionCode;
    }

    public SAPRejectionCodeMaster(String rejectionCode, String rejectionDesc, char isActive, Date lastUpdatedDate, String lastUpdatedBy) {
        this.rejectionCode = rejectionCode;
        this.rejectionDesc = rejectionDesc;
        this.isActive = isActive;
        this.lastUpdatedDate = lastUpdatedDate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getRejectionCode() {
        return rejectionCode;
    }

    public void setRejectionCode(String rejectionCode) {
        this.rejectionCode = rejectionCode;
    }

    public String getRejectionDesc() {
        return rejectionDesc;
    }

    public void setRejectionDesc(String rejectionDesc) {
        this.rejectionDesc = rejectionDesc;
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
        hash += (rejectionCode != null ? rejectionCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SAPRejectionCodeMaster)) {
            return false;
        }
        SAPRejectionCodeMaster other = (SAPRejectionCodeMaster) object;
        if ((this.rejectionCode == null && other.rejectionCode != null) || (this.rejectionCode != null && !this.rejectionCode.equals(other.rejectionCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SAPRejectionCodeMaster[rejectionCode=" + rejectionCode + "]";
    }

}
