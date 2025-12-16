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
@Table(name = "MSP_PaymentTerms_EXP")
@NamedQueries({
    @NamedQuery(name = "MSPPaymentTermsEXPMaster.findAll", query = "SELECT m FROM MSPPaymentTermsEXPMaster m"),
    @NamedQuery(name = "MSPPaymentTermsEXPMaster.findByPaymentTermCode", query = "SELECT m FROM MSPPaymentTermsEXPMaster m WHERE m.paymentTermCode = :paymentTermCode"),
    @NamedQuery(name = "MSPPaymentTermsEXPMaster.findByPaymentTermDesc", query = "SELECT m FROM MSPPaymentTermsEXPMaster m WHERE m.paymentTermDesc = :paymentTermDesc"),
    @NamedQuery(name = "MSPPaymentTermsEXPMaster.findByIsActive", query = "SELECT m FROM MSPPaymentTermsEXPMaster m WHERE m.isActive = :isActive"),
    @NamedQuery(name = "MSPPaymentTermsEXPMaster.findByLastUpdatedDate", query = "SELECT m FROM MSPPaymentTermsEXPMaster m WHERE m.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "MSPPaymentTermsEXPMaster.findByLastUpdatedBy", query = "SELECT m FROM MSPPaymentTermsEXPMaster m WHERE m.lastUpdatedBy = :lastUpdatedBy")})
public class MSPPaymentTermsEXPMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PaymentTermCode")
    private String paymentTermCode;
    @Basic(optional = false)
    @Column(name = "PaymentTermDesc")
    private String paymentTermDesc;
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

    public MSPPaymentTermsEXPMaster() {
    }

    public MSPPaymentTermsEXPMaster(String paymentTermCode) {
        this.paymentTermCode = paymentTermCode;
    }

    public MSPPaymentTermsEXPMaster(String paymentTermCode, String paymentTermDesc, char isActive, Date lastUpdatedDate, String lastUpdatedBy) {
        this.paymentTermCode = paymentTermCode;
        this.paymentTermDesc = paymentTermDesc;
        this.isActive = isActive;
        this.lastUpdatedDate = lastUpdatedDate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getPaymentTermCode() {
        return paymentTermCode;
    }

    public void setPaymentTermCode(String paymentTermCode) {
        this.paymentTermCode = paymentTermCode;
    }

    public String getPaymentTermDesc() {
        return paymentTermDesc;
    }

    public void setPaymentTermDesc(String paymentTermDesc) {
        this.paymentTermDesc = paymentTermDesc;
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
        hash += (paymentTermCode != null ? paymentTermCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MSPPaymentTermsEXPMaster)) {
            return false;
        }
        MSPPaymentTermsEXPMaster other = (MSPPaymentTermsEXPMaster) object;
        if ((this.paymentTermCode == null && other.paymentTermCode != null) || (this.paymentTermCode != null && !this.paymentTermCode.equals(other.paymentTermCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.MSPPaymentTermsEXPMaster[paymentTermCode=" + paymentTermCode + "]";
    }

}
