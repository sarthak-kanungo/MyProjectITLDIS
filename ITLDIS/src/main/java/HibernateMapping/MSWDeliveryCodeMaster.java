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
@Table(name = "MSW_DeliveryCodeMaster")
@NamedQueries({
    @NamedQuery(name = "MSWDeliveryCodeMaster.findAll", query = "SELECT m FROM MSWDeliveryCodeMaster m"),
    @NamedQuery(name = "MSWDeliveryCodeMaster.findByDeliveryCode", query = "SELECT m FROM MSWDeliveryCodeMaster m WHERE m.deliveryCode = :deliveryCode"),
    @NamedQuery(name = "MSWDeliveryCodeMaster.findByDeliveryDesc", query = "SELECT m FROM MSWDeliveryCodeMaster m WHERE m.deliveryDesc = :deliveryDesc"),
    @NamedQuery(name = "MSWDeliveryCodeMaster.findByIsActive", query = "SELECT m FROM MSWDeliveryCodeMaster m WHERE m.isActive = :isActive"),
    @NamedQuery(name = "MSWDeliveryCodeMaster.findByLastUpdatedDate", query = "SELECT m FROM MSWDeliveryCodeMaster m WHERE m.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "MSWDeliveryCodeMaster.findByLastUpdatedBy", query = "SELECT m FROM MSWDeliveryCodeMaster m WHERE m.lastUpdatedBy = :lastUpdatedBy")})
public class MSWDeliveryCodeMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DeliveryCode")
    private String deliveryCode;
    @Basic(optional = false)
    @Column(name = "DeliveryDesc")
    private String deliveryDesc;
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

    public MSWDeliveryCodeMaster() {
    }

    public MSWDeliveryCodeMaster(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public MSWDeliveryCodeMaster(String deliveryCode, String deliveryDesc, char isActive, Date lastUpdatedDate, String lastUpdatedBy) {
        this.deliveryCode = deliveryCode;
        this.deliveryDesc = deliveryDesc;
        this.isActive = isActive;
        this.lastUpdatedDate = lastUpdatedDate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public String getDeliveryDesc() {
        return deliveryDesc;
    }

    public void setDeliveryDesc(String deliveryDesc) {
        this.deliveryDesc = deliveryDesc;
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
        hash += (deliveryCode != null ? deliveryCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MSWDeliveryCodeMaster)) {
            return false;
        }
        MSWDeliveryCodeMaster other = (MSWDeliveryCodeMaster) object;
        if ((this.deliveryCode == null && other.deliveryCode != null) || (this.deliveryCode != null && !this.deliveryCode.equals(other.deliveryCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.MSWDeliveryCodeMaster[deliveryCode=" + deliveryCode + "]";
    }

}
