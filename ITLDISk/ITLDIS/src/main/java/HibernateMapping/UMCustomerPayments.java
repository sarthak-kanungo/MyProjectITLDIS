/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HibernateMapping;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "UM_CustomerPayments")
public class UMCustomerPayments implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PaymentID")
    private BigInteger paymentID;
    @Basic(optional = false)
    @Column(name = "CustomerID")
    private BigInteger customerID;
    @Basic(optional = false)
    @Column(name = "PaymentDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date paymentDate;
    @Basic(optional = false)
    @Column(name = "PaymentReceived")
    private Double paymentReceived;
    @Basic(optional = false)
    @Column(name = "PaymentModeID")
    private Integer paymentModeID;
    @Column(name = "Remarks")
    private String remarks;
    @Basic(optional = false)
    @Column(name = "CreatedBy")
    private String createdBy;
    @Basic(optional = false)
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "ModifiedBy")
    private String modifiedBy;
    @Column(name = "ModifiedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;

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

    public BigInteger getCustomerID() {
        return customerID;
    }

    public void setCustomerID(BigInteger customerID) {
        this.customerID = customerID;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public Date getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(Date paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigInteger getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(BigInteger paymentID) {
        this.paymentID = paymentID;
    }

    public Integer getPaymentModeID() {
        return paymentModeID;
    }

    public void setPaymentModeID(Integer paymentModeID) {
        this.paymentModeID = paymentModeID;
    }

    public Double getPaymentReceived() {
        return paymentReceived;
    }

    public void setPaymentReceived(Double paymentReceived) {
        this.paymentReceived = paymentReceived;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UMCustomerPayments other = (UMCustomerPayments) obj;
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.paymentID != null ? this.paymentID.hashCode() : 0);
        return hash;
    }
}
