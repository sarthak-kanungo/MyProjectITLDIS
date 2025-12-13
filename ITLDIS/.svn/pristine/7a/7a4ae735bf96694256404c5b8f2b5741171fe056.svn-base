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
@Table(name = "SP_INVENT_SRETURN")
@NamedQueries({
    @NamedQuery(name = "SpInventSreturn.findAll", query = "SELECT s FROM SpInventSreturn s"),
    @NamedQuery(name = "SpInventSreturn.findByReturnNo", query = "SELECT s FROM SpInventSreturn s WHERE s.returnNo = :returnNo"),
    @NamedQuery(name = "SpInventSreturn.findByDealerCode", query = "SELECT s FROM SpInventSreturn s WHERE s.dealerCode = :dealerCode"),
    @NamedQuery(name = "SpInventSreturn.findByInvoiceNo", query = "SELECT s FROM SpInventSreturn s WHERE s.invoiceNo = :invoiceNo"),
    @NamedQuery(name = "SpInventSreturn.findByReturnDate", query = "SELECT s FROM SpInventSreturn s WHERE s.returnDate = :returnDate"),
    @NamedQuery(name = "SpInventSreturn.findByReturnBy", query = "SELECT s FROM SpInventSreturn s WHERE s.returnBy = :returnBy"),
    @NamedQuery(name = "SpInventSreturn.findByTotalValue", query = "SELECT s FROM SpInventSreturn s WHERE s.totalValue = :totalValue"),
    @NamedQuery(name = "SpInventSreturn.findByCreatedOn", query = "SELECT s FROM SpInventSreturn s WHERE s.createdOn = :createdOn"),
    @NamedQuery(name = "SpInventSreturn.findByCreatedBy", query = "SELECT s FROM SpInventSreturn s WHERE s.createdBy = :createdBy")})
public class SpInventSreturn implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "RETURN_NO")
    private String returnNo;
    @Basic(optional = false)
    @Column(name = "DEALER_CODE")
    private String dealerCode;
    @Basic(optional = false)
    @Column(name = "INVOICE_NO")
    private String invoiceNo;
    @Basic(optional = false)
    @Column(name = "RETURN_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;
    @Basic(optional = false)
    @Column(name = "RETURN_BY")
    private String returnBy;
    @Basic(optional = false)
    @Column(name = "TOTAL_VALUE")
    private double totalValue;
    @Basic(optional = false)
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Basic(optional = false)
    @Column(name = "CreatedBy")
    private String createdBy;
    @Column(name = "CustomerID")
    private BigInteger customerID;

    public SpInventSreturn() {
    }

    public SpInventSreturn(String returnNo) {
        this.returnNo = returnNo;
    }

    public SpInventSreturn(String returnNo, String dealerCode, String invoiceNo, Date returnDate, String returnBy, double totalValue, Date createdOn, String createdBy,BigInteger customerID) {
        this.returnNo = returnNo;
        this.dealerCode = dealerCode;
        this.invoiceNo = invoiceNo;
        this.returnDate = returnDate;
        this.returnBy = returnBy;
        this.totalValue = totalValue;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.customerID = customerID;
    }

    public String getReturnNo() {
        return returnNo;
    }

    public void setReturnNo(String returnNo) {
        this.returnNo = returnNo;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public String getReturnBy() {
        return returnBy;
    }

    public void setReturnBy(String returnBy) {
        this.returnBy = returnBy;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }
    public BigInteger getCustomerID() {
        return customerID;
    }

    public void setCustomerID(BigInteger customerID) {
        this.customerID = customerID;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (returnNo != null ? returnNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpInventSreturn)) {
            return false;
        }
        SpInventSreturn other = (SpInventSreturn) object;
        if ((this.returnNo == null && other.returnNo != null) || (this.returnNo != null && !this.returnNo.equals(other.returnNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpInventSreturn[returnNo=" + returnNo + "]";
    }

}
