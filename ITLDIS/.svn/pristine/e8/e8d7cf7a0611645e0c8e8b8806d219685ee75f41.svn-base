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
import javax.persistence.Transient;

/**
 *
 * @author kundan.rastogi
 */
@Entity
@Table(name = "SP_ORDER_INV_GRN")
@NamedQueries({
    @NamedQuery(name = "SpOrderInvGrn.findAll", query = "SELECT s FROM SpOrderInvGrn s"),
    @NamedQuery(name = "SpOrderInvGrn.findByGrNo", query = "SELECT s FROM SpOrderInvGrn s WHERE s.grNo = :grNo"),
    @NamedQuery(name = "SpOrderInvGrn.findByGrDate", query = "SELECT s FROM SpOrderInvGrn s WHERE s.grDate = :grDate"),
    @NamedQuery(name = "SpOrderInvGrn.findByInvoiceNo", query = "SELECT s FROM SpOrderInvGrn s WHERE s.invoiceNo = :invoiceNo"),
    @NamedQuery(name = "SpOrderInvGrn.findByInvoiceDate", query = "SELECT s FROM SpOrderInvGrn s WHERE s.invoiceDate = :invoiceDate"),
    @NamedQuery(name = "SpOrderInvGrn.findByDealerCode", query = "SELECT s FROM SpOrderInvGrn s WHERE s.dealerCode = :dealerCode"),
    @NamedQuery(name = "SpOrderInvGrn.findByReceivedBy", query = "SELECT s FROM SpOrderInvGrn s WHERE s.receivedBy = :receivedBy"),
    @NamedQuery(name = "SpOrderInvGrn.findByReceivedOn", query = "SELECT s FROM SpOrderInvGrn s WHERE s.receivedOn = :receivedOn"),
    @NamedQuery(name = "SpOrderInvGrn.findByCreatedBy", query = "SELECT s FROM SpOrderInvGrn s WHERE s.createdBy = :createdBy"),
    @NamedQuery(name = "SpOrderInvGrn.findByCreatedOn", query = "SELECT s FROM SpOrderInvGrn s WHERE s.createdOn = :createdOn")})
public class SpOrderInvGrn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "GR_NO")
    private String grNo;
    @Basic(optional = false)
    @Column(name = "GR_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date grDate;
    @Basic(optional = false)
    @Column(name = "INVOICE_NO")
    private String invoiceNo;
    @Basic(optional = false)
    @Column(name = "INVOICE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;
    @Basic(optional = false)
    @Column(name = "DEALER_CODE")
    private String dealerCode;
    @Basic(optional = false)
    @Column(name = "RECEIVED_BY")
    private String receivedBy;
    @Basic(optional = false)
    @Column(name = "RECEIVED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date receivedOn;
    @Basic(optional = false)
    @Column(name = "CreatedBy")
    private String createdBy;
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    @Transient
    private String grStrDate;

    @Transient
    private String receivedOnStr;

    @Transient
    private String createdOnStr;

    @Transient
    private String invDateStr;


    public SpOrderInvGrn() {
    }

    public SpOrderInvGrn(String grNo) {
        this.grNo = grNo;
    }

    public SpOrderInvGrn(String grNo, Date grDate, String invoiceNo, Date invoiceDate, String dealerCode, String receivedBy, Date receivedOn, String createdBy) {
        this.grNo = grNo;
        this.grDate = grDate;
        this.invoiceNo = invoiceNo;
        this.invoiceDate = invoiceDate;
        this.dealerCode = dealerCode;
        this.receivedBy = receivedBy;
        this.receivedOn = receivedOn;
        this.createdBy = createdBy;
    }

    public String getGrNo() {
        return grNo;
    }

    public void setGrNo(String grNo) {
        this.grNo = grNo;
    }

    public Date getGrDate() {
        return grDate;
    }

    public void setGrDate(Date grDate) {
        this.grDate = grDate;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public Date getReceivedOn() {
        return receivedOn;
    }

    public void setReceivedOn(Date receivedOn) {
        this.receivedOn = receivedOn;
    }

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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grNo != null ? grNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpOrderInvGrn)) {
            return false;
        }
        SpOrderInvGrn other = (SpOrderInvGrn) object;
        if ((this.grNo == null && other.grNo != null) || (this.grNo != null && !this.grNo.equals(other.grNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpOrderInvGrn[grNo=" + grNo + "]";
    }

    /**
     * @return the grStrDate
     */
    public String getGrStrDate() {
        return grStrDate;
    }

    /**
     * @param grStrDate the grStrDate to set
     */
    public void setGrStrDate(String grStrDate) {
        this.grStrDate = grStrDate;
    }

    /**
     * @return the receivedOnStr
     */
    public String getReceivedOnStr() {
        return receivedOnStr;
    }

    /**
     * @param receivedOnStr the receivedOnStr to set
     */
    public void setReceivedOnStr(String receivedOnStr) {
        this.receivedOnStr = receivedOnStr;
    }

    /**
     * @return the createdOnStr
     */
    public String getCreatedOnStr() {
        return createdOnStr;
    }

    /**
     * @param createdOnStr the createdOnStr to set
     */
    public void setCreatedOnStr(String createdOnStr) {
        this.createdOnStr = createdOnStr;
    }

    /**
     * @return the invDateStr
     */
    public String getInvDateStr() {
        return invDateStr;
    }

    /**
     * @param invDateStr the invDateStr to set
     */
    public void setInvDateStr(String invDateStr) {
        this.invDateStr = invDateStr;
    }

}
