/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author avinash.pandey
 */
@Entity
@Table(name = "SP_INVENT_SALE_DETAILS_CHRG")

    public class SpInventSaleDetailsChrg implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SpInventSaleDetailsChrgPK spInventSaleDetailsChrgPK;
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
    @Column(name = "charge_rate")
    private double charge_rate;
    @Basic(optional = false)
    @Column(name = "charge_amount")
    private double charge_amount;
    @Basic(optional = false)
    @Column(name = "charge_order")
    private Integer charge_order;
    @Basic(optional = false)
    @Column(name = "IsPrimaryTax")
    private String isPrimaryTax;
    @Basic(optional = false)
    @Column(name = "part_order_seq")
    private Integer part_order_seq;


    @Basic(optional = false)
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Basic(optional = false)
    @Column(name = "CreatedBy")
    private String createdBy;

    public SpInventSaleDetailsChrg() {
    }

    public SpInventSaleDetailsChrg(SpInventSaleDetailsChrgPK spInventSaleDetailsChrgPK) {
        this.spInventSaleDetailsChrgPK = spInventSaleDetailsChrgPK;
    }

    public SpInventSaleDetailsChrg(SpInventSaleDetailsChrgPK spInventSaleDetailsChrgPK, String partdesc, double qty, double unitvalue, int billID, double discount, double finalAmount,String partcategory,double charge_rate,double charge_amount,Integer charge_order,String isPrimaryTax) {
        this.spInventSaleDetailsChrgPK = spInventSaleDetailsChrgPK;
        this.partdesc = partdesc;
        this.qty = qty;
        this.unitvalue = unitvalue;
        this.billID = billID;
        this.discount = discount;
        this.partcategory = partcategory;
        this.charge_rate = charge_rate;
        this.charge_amount = charge_amount;
        this.charge_order = charge_order;
        this.isPrimaryTax = isPrimaryTax;
    }

    public SpInventSaleDetailsChrg(String invoiceNo, String partno) {
        this.spInventSaleDetailsChrgPK = new SpInventSaleDetailsChrgPK(invoiceNo, partno);
    }

    public SpInventSaleDetailsChrgPK getSpInventSaleDetailsChrgPK() {
        return spInventSaleDetailsChrgPK;
    }

    public void setSpInventSaleDetailsPK(SpInventSaleDetailsChrgPK spInventSaleDetailsChrgPK) {
        this.spInventSaleDetailsChrgPK = spInventSaleDetailsChrgPK;
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

    public double getCharge_amount() {
        return charge_amount;
    }

    public void setCharge_amount(double charge_amount) {
        this.charge_amount = charge_amount;
    }

    public Integer getCharge_order() {
        return charge_order;
    }

    public void setCharge_order(Integer charge_order) {
        this.charge_order = charge_order;
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

    public String getIsPrimaryTax() {
        return isPrimaryTax;
    }

    public void setIsPrimaryTax(String isPrimaryTax) {
        this.isPrimaryTax = isPrimaryTax;
    }
    public Integer getPart_order_seq() {
        return part_order_seq;
    }

    public void setPart_order_seq(Integer part_order_seq) {
        this.part_order_seq = part_order_seq;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spInventSaleDetailsChrgPK != null ? spInventSaleDetailsChrgPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpInventSaleDetailsChrg)) {
            return false;
        }
        SpInventSaleDetailsChrg other = (SpInventSaleDetailsChrg) object;
        if ((this.spInventSaleDetailsChrgPK == null && other.spInventSaleDetailsChrgPK != null) || (this.spInventSaleDetailsChrgPK != null && !this.spInventSaleDetailsChrgPK.equals(other.spInventSaleDetailsChrgPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpInventSaleDetailsChrg[spInventSaleDetailsChrgPK=" + spInventSaleDetailsChrgPK + "]";
    }

    /**
     * @return the charge_rate
     */
    public double getCharge_rate() {
        return charge_rate;
    }

    /**
     * @param charge_rate the charge_rate to set
     */
    public void setCharge_rate(double charge_rate) {
        this.charge_rate = charge_rate;
    }

}
