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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author yogasmita.patel
 */
@Entity
@Table(name="MSW_PaymentModes")
public class MSWPaymentModes implements Serializable {
    @Id
    @Basic(optional=false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="PaymentModeID")
    private Integer paymentModeID;
    @Basic(optional=false)
    @Column(name="PayementMode")
    private String payementMode;
    @Basic(optional=false)
    @Column(name="IsActive")
    private Character isActive;
    @Basic(optional=false)
    @Column(name="CreatedBy")
    private String createdBy;
    @Basic(optional=false)
    @Column(name="CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Character getIsActive() {
        return isActive;
    }

    public void setIsActive(Character isActive) {
        this.isActive = isActive;
    }

    public String getPayementMode() {
        return payementMode;
    }

    public void setPayementMode(String payementMode) {
        this.payementMode = payementMode;
    }

    public Integer getPaymentModeID() {
        return paymentModeID;
    }

    public void setPaymentModeID(Integer paymentModeID) {
        this.paymentModeID = paymentModeID;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MSWPaymentModes other = (MSWPaymentModes) obj;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 61 * hash + (this.paymentModeID != null ? this.paymentModeID.hashCode() : 0);
        return hash;
    }

}
