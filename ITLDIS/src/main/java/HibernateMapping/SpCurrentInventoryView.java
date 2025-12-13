/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "SP_CURRENT_INVENTORY_VIEW")

public class SpCurrentInventoryView implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "DEALER_CODE")
    private String dealerCode;
    @Id
    @Basic(optional = false)
    @Column(name = "PART_NO")
    private String partNo;
     @Basic(optional = false)
    @Column(name = "QUANTITY")
    private Double quantity;
    @Basic(optional = false)
    @Column(name = "Current_Qty")
    private Double currentQty;
    @Basic(optional = false)
    @Column(name = "WIP_QTY")
    private Double wipQty;
    @Basic(optional = false)
    @Column(name = "p1")
    private String partDesc;
    @Column(name = "BIN_LOCATION")
    private String binLocation;
    @Column(name = "MRP")
    private double mrp;
    @Column(name = "Amount")
    private double amount;
    //Added by avinash 18-11-2015
    @Column(name = "PRICELIST_CODE")
    private String pricelistCode;
  
    public SpCurrentInventoryView() {
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public Double getQuantity() {
        return quantity;
    }

    public void setQuantity(Double quantity) {
        this.quantity = quantity;
    }

    public Double getCurrentQty() {
        return currentQty;
    }

    public void setCurrentQty(Double currentQty) {
        this.currentQty = currentQty;
    }

    public Double getWipQty() {
        return wipQty;
    }

    public void setWipQty(Double wipQty) {
        this.wipQty = wipQty;
    }

    public String getPartDesc() {
        return partDesc;
    }

    public void setPartDesc(String partDesc) {
        this.partDesc = partDesc;
    }

    public String getBinLocation() {
        return binLocation;
    }

    public void setBinLocation(String binLocation) {
        this.binLocation = binLocation;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getMrp() {
        return mrp;
    }

    public void setMrp(double mrp) {
        this.mrp = mrp;
    }

    public String getPricelistCode() {
        return pricelistCode;
    }

    public void setPricelistCode(String pricelistCode) {
        this.pricelistCode = pricelistCode;
    }
    
}
