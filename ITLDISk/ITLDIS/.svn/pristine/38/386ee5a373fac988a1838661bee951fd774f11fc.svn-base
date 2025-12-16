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
 * @author megha.pandya
 */
@Entity
@Table(name = "SP_INVENT_SALE_MASTER")
@NamedQueries({
    @NamedQuery(name = "SpInventSaleMaster.findAll", query = "SELECT s FROM SpInventSaleMaster s"),
    @NamedQuery(name = "SpInventSaleMaster.findByInvoiceNo", query = "SELECT s FROM SpInventSaleMaster s WHERE s.invoiceNo = :invoiceNo"),
    @NamedQuery(name = "SpInventSaleMaster.findByDealerCode", query = "SELECT s FROM SpInventSaleMaster s WHERE s.dealerCode = :dealerCode"),
    @NamedQuery(name = "SpInventSaleMaster.findByInvoicetype", query = "SELECT s FROM SpInventSaleMaster s WHERE s.invoicetype = :invoicetype"),
    @NamedQuery(name = "SpInventSaleMaster.findByTypeRefno", query = "SELECT s FROM SpInventSaleMaster s WHERE s.typeRefno = :typeRefno"),
    @NamedQuery(name = "SpInventSaleMaster.findByInvoiceDate", query = "SELECT s FROM SpInventSaleMaster s WHERE s.invoiceDate = :invoiceDate"),
    @NamedQuery(name = "SpInventSaleMaster.findByCustomerName", query = "SELECT s FROM SpInventSaleMaster s WHERE s.customerName = :customerName"),
    @NamedQuery(name = "SpInventSaleMaster.findByContactno", query = "SELECT s FROM SpInventSaleMaster s WHERE s.contactno = :contactno"),
    @NamedQuery(name = "SpInventSaleMaster.findByInvoiceValue", query = "SELECT s FROM SpInventSaleMaster s WHERE s.invoiceValue = :invoiceValue"),
    @NamedQuery(name = "SpInventSaleMaster.findByPartsValue", query = "SELECT s FROM SpInventSaleMaster s WHERE s.partsValue = :partsValue"),
    @NamedQuery(name = "SpInventSaleMaster.findByLubesValue", query = "SELECT s FROM SpInventSaleMaster s WHERE s.lubesValue = :lubesValue"),
    @NamedQuery(name = "SpInventSaleMaster.findByOtherValue", query = "SELECT s FROM SpInventSaleMaster s WHERE s.otherValue = :otherValue"),
    @NamedQuery(name = "SpInventSaleMaster.findByDiscountParts", query = "SELECT s FROM SpInventSaleMaster s WHERE s.discountParts = :discountParts"),
    @NamedQuery(name = "SpInventSaleMaster.findByDiscountLubes", query = "SELECT s FROM SpInventSaleMaster s WHERE s.discountLubes = :discountLubes"),
    @NamedQuery(name = "SpInventSaleMaster.findByDiscountOther", query = "SELECT s FROM SpInventSaleMaster s WHERE s.discountOther = :discountOther"),
    @NamedQuery(name = "SpInventSaleMaster.findByCreatedOn", query = "SELECT s FROM SpInventSaleMaster s WHERE s.createdOn = :createdOn"),
    @NamedQuery(name = "SpInventSaleMaster.findByCreatedBy", query = "SELECT s FROM SpInventSaleMaster s WHERE s.createdBy = :createdBy")})
public class SpInventSaleMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "invoice_no")
    private String invoiceNo;
    @Basic(optional = false)
    @Column(name = "dealer_code")
    private String dealerCode;
    @Basic(optional = false)
    @Column(name = "Invoice_type")
    private String invoicetype;
    @Basic(optional = false)
    @Column(name = "Type_Ref_no")
    private String typeRefno;
    @Basic(optional = false)
    @Column(name = "Invoice_Date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;
    @Basic(optional = false)
    @Column(name = "Customer_Name")
    private String customerName;
    @Basic(optional = false)
    @Column(name = "Contact_no")
    private String contactno;
    @Basic(optional = false)
    @Column(name = "Invoice_Value")
    private double invoiceValue;
    @Basic(optional = false)
    @Column(name = "Parts_Value")
    private double partsValue;
    @Basic(optional = false)
    @Column(name = "Lubes_Value")
    private double lubesValue;
    @Basic(optional = false)
    @Column(name = "Other_Value")
    private double otherValue;
    @Basic(optional = false)
    @Column(name = "Discount_Parts")
    private double discountParts;
    @Basic(optional = false)
    @Column(name = "Discount_Lubes")
    private double discountLubes;
    @Basic(optional = false)
    @Column(name = "Discount_Other")
    private double discountOther;
    @Column(name = "Credit_Value")
    private double creditValue;
    @Basic(optional = false)
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Basic(optional = false)
    @Column(name = "CreatedBy")
    private String createdBy;
    @Column(name = "CustomerID")
    private BigInteger customerID;

    @Column(name = "SaleTypeCode")
    private String saleTypeCode;
    @Column(name = "GST_NO")
    private String gstNo;
    
    @Column(name="invoice_no_12Digits")
    private String invoiceNo12Digits;

    public SpInventSaleMaster() {
    }

    public SpInventSaleMaster(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public SpInventSaleMaster(String invoiceNo, String dealerCode, String invoicetype, String typeRefno, Date invoiceDate, String customerName, String contactno, double invoiceValue, double partsValue, double lubesValue, double otherValue, double discountParts, double discountLubes, double discountOther,double creditValue, Date createdOn, String createdBy,BigInteger customerID,String saleTypeCode,String invoiceNo12Digits) {
        this.invoiceNo = invoiceNo;
        this.dealerCode = dealerCode;
        this.invoicetype = invoicetype;
        this.typeRefno = typeRefno;
        this.invoiceDate = invoiceDate;
        this.customerName = customerName;
        this.contactno = contactno;
        this.invoiceValue = invoiceValue;
        this.partsValue = partsValue;
        this.lubesValue = lubesValue;
        this.otherValue = otherValue;
        this.discountParts = discountParts;
        this.discountLubes = discountLubes;
        this.discountOther = discountOther;
        this.creditValue = creditValue;
        this.createdOn = createdOn;
        this.createdBy = createdBy;
        this.customerID = customerID;
        this.saleTypeCode = saleTypeCode;
        this.invoiceNo12Digits= invoiceNo12Digits;
    }

    public String getGstNo() {
        return gstNo;
    }

    public void setGstNo(String gstNo) {
        this.gstNo = gstNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getInvoicetype() {
        return invoicetype;
    }

    public void setInvoicetype(String invoicetype) {
        this.invoicetype = invoicetype;
    }

    public String getTypeRefno() {
        return typeRefno;
    }

    public void setTypeRefno(String typeRefno) {
        this.typeRefno = typeRefno;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getContactno() {
        return contactno;
    }

    public void setContactno(String contactno) {
        this.contactno = contactno;
    }

    public double getInvoiceValue() {
        return invoiceValue;
    }

    public void setInvoiceValue(double invoiceValue) {
        this.invoiceValue = invoiceValue;
    }

    public double getPartsValue() {
        return partsValue;
    }

    public void setPartsValue(double partsValue) {
        this.partsValue = partsValue;
    }

    public double getLubesValue() {
        return lubesValue;
    }

    public void setLubesValue(double lubesValue) {
        this.lubesValue = lubesValue;
    }

    public double getOtherValue() {
        return otherValue;
    }

    public void setOtherValue(double otherValue) {
        this.otherValue = otherValue;
    }

    public double getDiscountParts() {
        return discountParts;
    }

    public void setDiscountParts(double discountParts) {
        this.discountParts = discountParts;
    }

    public double getDiscountLubes() {
        return discountLubes;
    }

    public void setDiscountLubes(double discountLubes) {
        this.discountLubes = discountLubes;
    }

    public double getDiscountOther() {
        return discountOther;
    }

    public void setDiscountOther(double discountOther) {
        this.discountOther = discountOther;
    }

    public double getCreditValue() {
        return creditValue;
    }

    public void setCreditValue(double creditValue) {
        this.creditValue = creditValue;
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

    public String getSaleTypeCode() {
        return saleTypeCode;
    }

    public void setSaleTypeCode(String saleTypeCode) {
        this.saleTypeCode = saleTypeCode;
    }
    
    public String getInvoiceNo12Digits() {
		return invoiceNo12Digits;
	}

	public void setInvoiceNo12Digits(String invoiceNo12Digits) {
		this.invoiceNo12Digits = invoiceNo12Digits;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceNo != null ? invoiceNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpInventSaleMaster)) {
            return false;
        }
        SpInventSaleMaster other = (SpInventSaleMaster) object;
        if ((this.invoiceNo == null && other.invoiceNo != null) || (this.invoiceNo != null && !this.invoiceNo.equals(other.invoiceNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpInventSaleMaster[invoiceNo=" + invoiceNo + "]";
    }

}
