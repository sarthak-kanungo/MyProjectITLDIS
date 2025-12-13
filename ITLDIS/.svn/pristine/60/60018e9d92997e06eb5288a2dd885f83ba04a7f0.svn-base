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
@Table(name = "MSP_DeliveryTerms_EXP")
@NamedQueries({
    @NamedQuery(name = "MSPDeliveryTermsEXPMaster.findAll", query = "SELECT m FROM MSPDeliveryTermsEXPMaster m"),
    @NamedQuery(name = "MSPDeliveryTermsEXPMaster.findByDeliveryTermCode", query = "SELECT m FROM MSPDeliveryTermsEXPMaster m WHERE m.deliveryTermCode = :deliveryTermCode"),
    @NamedQuery(name = "MSPDeliveryTermsEXPMaster.findByDeliveryTermDesc", query = "SELECT m FROM MSPDeliveryTermsEXPMaster m WHERE m.deliveryTermDesc = :deliveryTermDesc"),
    @NamedQuery(name = "MSPDeliveryTermsEXPMaster.findByIsActive", query = "SELECT m FROM MSPDeliveryTermsEXPMaster m WHERE m.isActive = :isActive"),
    @NamedQuery(name = "MSPDeliveryTermsEXPMaster.findByLastUpdatedDate", query = "SELECT m FROM MSPDeliveryTermsEXPMaster m WHERE m.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "MSPDeliveryTermsEXPMaster.findByLastUpdatedBy", query = "SELECT m FROM MSPDeliveryTermsEXPMaster m WHERE m.lastUpdatedBy = :lastUpdatedBy")})
public class MSPDeliveryTermsEXPMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DeliveryTermCode")
    private String deliveryTermCode;
    @Basic(optional = false)
    @Column(name = "DeliveryTermDesc")
    private String deliveryTermDesc;
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

    public MSPDeliveryTermsEXPMaster() {
    }

    public MSPDeliveryTermsEXPMaster(String deliveryTermCode) {
        this.deliveryTermCode = deliveryTermCode;
    }

    public MSPDeliveryTermsEXPMaster(String deliveryTermCode, String deliveryTermDesc, char isActive, Date lastUpdatedDate, String lastUpdatedBy) {
        this.deliveryTermCode = deliveryTermCode;
        this.deliveryTermDesc = deliveryTermDesc;
        this.isActive = isActive;
        this.lastUpdatedDate = lastUpdatedDate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getDeliveryTermCode() {
        return deliveryTermCode;
    }

    public void setDeliveryTermCode(String deliveryTermCode) {
        this.deliveryTermCode = deliveryTermCode;
    }

    public String getDeliveryTermDesc() {
        return deliveryTermDesc;
    }

    public void setDeliveryTermDesc(String deliveryTermDesc) {
        this.deliveryTermDesc = deliveryTermDesc;
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
        hash += (deliveryTermCode != null ? deliveryTermCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MSPDeliveryTermsEXPMaster)) {
            return false;
        }
        MSPDeliveryTermsEXPMaster other = (MSPDeliveryTermsEXPMaster) object;
        if ((this.deliveryTermCode == null && other.deliveryTermCode != null) || (this.deliveryTermCode != null && !this.deliveryTermCode.equals(other.deliveryTermCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.MSPDeliveryTermsEXPMaster[deliveryTermCode=" + deliveryTermCode + "]";
    }

}
