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
 * @author megha.pandya
 */
@Entity
@Table(name = "SP_INVENT_OTHER_DEALER")
@NamedQueries({
    @NamedQuery(name = "SpInventOtherDealer.findAll", query = "SELECT s FROM SpInventOtherDealer s"),
    @NamedQuery(name = "SpInventOtherDealer.findByInventNo", query = "SELECT s FROM SpInventOtherDealer s WHERE s.inventNo = :inventNo"),
    @NamedQuery(name = "SpInventOtherDealer.findByDealerCode", query = "SELECT s FROM SpInventOtherDealer s WHERE s.dealerCode = :dealerCode"),
    @NamedQuery(name = "SpInventOtherDealer.findByBillNo", query = "SELECT s FROM SpInventOtherDealer s WHERE s.billNo = :billNo"),
    @NamedQuery(name = "SpInventOtherDealer.findByBillDate", query = "SELECT s FROM SpInventOtherDealer s WHERE s.billDate = :billDate"),
    @NamedQuery(name = "SpInventOtherDealer.findByVendorName", query = "SELECT s FROM SpInventOtherDealer s WHERE s.vendorName = :vendorName"),
    @NamedQuery(name = "SpInventOtherDealer.findByTotalValue", query = "SELECT s FROM SpInventOtherDealer s WHERE s.totalValue = :totalValue"),
    @NamedQuery(name = "SpInventOtherDealer.findByCreatedOn", query = "SELECT s FROM SpInventOtherDealer s WHERE s.createdOn = :createdOn"),
    @NamedQuery(name = "SpInventOtherDealer.findByCreatedBy", query = "SELECT s FROM SpInventOtherDealer s WHERE s.createdBy = :createdBy")})
public class SpInventOtherDealer implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "INVENT_NO")
    private String inventNo;
    @Basic(optional = false)
    @Column(name = "DEALER_CODE")
    private String dealerCode;
    @Basic(optional = false)
    @Column(name = "BILL_NO")
    private String billNo;
    @Basic(optional = false)
    @Column(name = "BILL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date billDate;
    @Basic(optional = false)
    @Column(name = "VENDOR_NAME")
    private String vendorName;
    @Basic(optional = false)
    @Column(name = "SUB_TOTAL")
    private double subTotal;
    @Basic(optional = false)
    @Column(name = "TAX_VALUE")
    private double taxValue;
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

    public SpInventOtherDealer() {
    }

    public SpInventOtherDealer(String inventNo) {
        this.inventNo = inventNo;
    }

    public SpInventOtherDealer(String inventNo, String dealerCode, String billNo, Date billDate, String vendorName, double totalValue, Date createdOn, String createdBy) {
        this.inventNo = inventNo;
        this.dealerCode = dealerCode;
        this.billNo = billNo;
        this.billDate = billDate;
        this.vendorName = vendorName;
        this.totalValue = totalValue;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
    }

    public String getInventNo() {
        return inventNo;
    }

    public void setInventNo(String inventNo) {
        this.inventNo = inventNo;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getBillNo() {
        return billNo;
    }

    public void setBillNo(String billNo) {
        this.billNo = billNo;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public String getVendorName() {
        return vendorName;
    }

    public void setVendorName(String vendorName) {
        this.vendorName = vendorName;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(double taxValue) {
        this.taxValue = taxValue;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (inventNo != null ? inventNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpInventOtherDealer)) {
            return false;
        }
        SpInventOtherDealer other = (SpInventOtherDealer) object;
        if ((this.inventNo == null && other.inventNo != null) || (this.inventNo != null && !this.inventNo.equals(other.inventNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpInventOtherDealer[inventNo=" + inventNo + "]";
    }

}
