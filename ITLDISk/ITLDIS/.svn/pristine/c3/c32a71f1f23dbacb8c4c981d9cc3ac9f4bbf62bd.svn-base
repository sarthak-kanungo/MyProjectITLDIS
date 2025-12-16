/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author megha.pandya
 */
@Entity
@Table(name = "SP_INVENT_SALE_DETAILS")
@NamedQueries({
    @NamedQuery(name = "SpInventSaleDetails.findAll", query = "SELECT s FROM SpInventSaleDetails s"),
    @NamedQuery(name = "SpInventSaleDetails.findByInvoiceNo", query = "SELECT s FROM SpInventSaleDetails s WHERE s.spInventSaleDetailsPK.invoiceNo = :invoiceNo"),
    @NamedQuery(name = "SpInventSaleDetails.findByPartno", query = "SELECT s FROM SpInventSaleDetails s WHERE s.spInventSaleDetailsPK.partno = :partno"),
    @NamedQuery(name = "SpInventSaleDetails.findByPartdesc", query = "SELECT s FROM SpInventSaleDetails s WHERE s.partdesc = :partdesc"),
    @NamedQuery(name = "SpInventSaleDetails.findByQty", query = "SELECT s FROM SpInventSaleDetails s WHERE s.qty = :qty"),
    @NamedQuery(name = "SpInventSaleDetails.findByUnitvalue", query = "SELECT s FROM SpInventSaleDetails s WHERE s.unitvalue = :unitvalue"),
    @NamedQuery(name = "SpInventSaleDetails.findByBillID", query = "SELECT s FROM SpInventSaleDetails s WHERE s.billID = :billID"),
    @NamedQuery(name = "SpInventSaleDetails.findByDiscount", query = "SELECT s FROM SpInventSaleDetails s WHERE s.discount = :discount"),
    @NamedQuery(name = "SpInventSaleDetails.findByFinalAmount", query = "SELECT s FROM SpInventSaleDetails s WHERE s.finalAmount = :finalAmount")})
public class SpInventSaleDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SpInventSaleDetailsPK spInventSaleDetailsPK;
    @Basic(optional = false)
    @Column(name = "Part_desc")
    private String partdesc;
    @Basic(optional = false)
    @Column(name = "Qty")
    private double qty;
    @Basic(optional = false)
    @Column(name = "Unit_value")
    private double unitvalue;
    @Basic(optional = false)
    @Column(name = "BillID")
    private int billID;
    @Basic(optional = false)
    @Column(name = "Discount")
    private double discount;
    @Basic(optional = false)
    @Column(name = "final_amount")
    private double finalAmount;
    @Column(name = "part_category")
    private String partcategory;


    public SpInventSaleDetails() {
    }

    public SpInventSaleDetails(SpInventSaleDetailsPK spInventSaleDetailsPK) {
        this.spInventSaleDetailsPK = spInventSaleDetailsPK;
    }

    public SpInventSaleDetails(SpInventSaleDetailsPK spInventSaleDetailsPK, String partdesc, double qty, double unitvalue, int billID, double discount, double finalAmount) {
        this.spInventSaleDetailsPK = spInventSaleDetailsPK;
        this.partdesc = partdesc;
        this.qty = qty;
        this.unitvalue = unitvalue;
        this.billID = billID;
        this.discount = discount;
        this.finalAmount = finalAmount;
    }

    public SpInventSaleDetails(String invoiceNo, String partno) {
        this.spInventSaleDetailsPK = new SpInventSaleDetailsPK(invoiceNo, partno);
    }

    public SpInventSaleDetailsPK getSpInventSaleDetailsPK() {
        return spInventSaleDetailsPK;
    }

    public void setSpInventSaleDetailsPK(SpInventSaleDetailsPK spInventSaleDetailsPK) {
        this.spInventSaleDetailsPK = spInventSaleDetailsPK;
    }

    public String getPartdesc() {
        return partdesc;
    }

    public void setPartdesc(String partdesc) {
        this.partdesc = partdesc;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getUnitvalue() {
        return unitvalue;
    }

    public void setUnitvalue(double unitvalue) {
        this.unitvalue = unitvalue;
    }

    public int getBillID() {
        return billID;
    }

    public void setBillID(int billID) {
        this.billID = billID;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(double finalAmount) {
        this.finalAmount = finalAmount;
    }

    public String getPartcategory() {
        return partcategory;
    }

    public void setPartcategory(String partcategory) {
        this.partcategory = partcategory;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spInventSaleDetailsPK != null ? spInventSaleDetailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpInventSaleDetails)) {
            return false;
        }
        SpInventSaleDetails other = (SpInventSaleDetails) object;
        if ((this.spInventSaleDetailsPK == null && other.spInventSaleDetailsPK != null) || (this.spInventSaleDetailsPK != null && !this.spInventSaleDetailsPK.equals(other.spInventSaleDetailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpInventSaleDetails[spInventSaleDetailsPK=" + spInventSaleDetailsPK + "]";
    }

}
