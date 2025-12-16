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
@Table(name = "MSP_DischargePort_EXP")
@NamedQueries({
    @NamedQuery(name = "MSPDischargePortEXPMaster.findAll", query = "SELECT m FROM MSPDischargePortEXPMaster m"),
    @NamedQuery(name = "MSPDischargePortEXPMaster.findByDischargePortCode", query = "SELECT m FROM MSPDischargePortEXPMaster m WHERE m.dischargePortCode = :dischargePortCode"),
    @NamedQuery(name = "MSPDischargePortEXPMaster.findByDischargePortName", query = "SELECT m FROM MSPDischargePortEXPMaster m WHERE m.dischargePortName = :dischargePortName"),
    @NamedQuery(name = "MSPDischargePortEXPMaster.findByDischargeCountryCode", query = "SELECT m FROM MSPDischargePortEXPMaster m WHERE m.dischargeCountryCode = :dischargeCountryCode"),
    @NamedQuery(name = "MSPDischargePortEXPMaster.findByDeliveryTermCode", query = "SELECT m FROM MSPDischargePortEXPMaster m WHERE m.deliveryCode = :deliveryCode"),
    @NamedQuery(name = "MSPDischargePortEXPMaster.findByIsActive", query = "SELECT m FROM MSPDischargePortEXPMaster m WHERE m.isActive = :isActive"),
    @NamedQuery(name = "MSPDischargePortEXPMaster.findByLastUpdatedDate", query = "SELECT m FROM MSPDischargePortEXPMaster m WHERE m.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "MSPDischargePortEXPMaster.findByLastUpdatedBy", query = "SELECT m FROM MSPDischargePortEXPMaster m WHERE m.lastUpdatedBy = :lastUpdatedBy")})
public class MSPDischargePortEXPMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DischargePortCode")
    private String dischargePortCode;
    @Basic(optional = false)
    @Column(name = "DischargePortName")
    private String dischargePortName;
    @Basic(optional = false)
    @Column(name = "DischargeCountryCode")
    private String dischargeCountryCode;
    @Column(name = "DeliveryTermCode")
    private String deliveryCode;
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

    public MSPDischargePortEXPMaster() {
    }

    public MSPDischargePortEXPMaster(String dischargePortCode) {
        this.dischargePortCode = dischargePortCode;
    }

    public MSPDischargePortEXPMaster(String dischargePortCode, String dischargePortName, String dischargeCountryCode,char isActive, Date lastUpdatedDate, String lastUpdatedBy) {
        this.dischargePortCode = dischargePortCode;
        this.dischargePortName = dischargePortName;
        this.dischargeCountryCode = dischargeCountryCode;
        this.isActive = isActive;
        this.lastUpdatedDate = lastUpdatedDate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public String getDischargePortCode() {
        return dischargePortCode;
    }

    public void setDischargePortCode(String dischargePortCode) {
        this.dischargePortCode = dischargePortCode;
    }

    public String getDischargePortName() {
        return dischargePortName;
    }

    public void setDischargePortName(String dischargePortName) {
        this.dischargePortName = dischargePortName;
    }
    public String getDischargeCountryCode() {
        return dischargeCountryCode;
    }

    public void setDischargeCountryCode(String dischargeCountryCode) {
        this.dischargeCountryCode = dischargeCountryCode;
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
        hash += (dischargePortCode != null ? dischargePortCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MSPDischargePortEXPMaster)) {
            return false;
        }
        MSPDischargePortEXPMaster other = (MSPDischargePortEXPMaster) object;
        if ((this.dischargePortCode == null && other.dischargePortCode != null) || (this.dischargePortCode != null && !this.dischargePortCode.equals(other.dischargePortCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.MSPDischargePortEXPMaster[dischargePortCode=" + dischargePortCode + "]";
    }

    /**
     * @return the deliveryCode
     */
    public String getDeliveryCode() {
        return deliveryCode;
    }

    /**
     * @param deliveryCode the deliveryCode to set
     */
    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

}
