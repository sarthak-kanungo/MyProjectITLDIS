/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author prashant.kumar
 */
@Entity
@Table(name = "SP_STK_ADJ_DETAILS")
public class SpStockAdjDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "STK_ADJ_DTL_ID")
    private Integer stkAdjId;
    @Column(name = "STK_ADJ_NO")
    private String stkAdjNo;
    @Column(name = "PART_NO")
    private String partNo;
    @Column(name = "SALE_PURCHASE")
    private String salePurchase;
    @Column(name = "QTY")
    private float qty;
    @Column(name = "UNIT_PRICE")
    private float unitPrice;
    @Column(name = "REMARKS")
    private String remarks;

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public float getQty() {
        return qty;
    }

    public void setQty(float qty) {
        this.qty = qty;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getSalePurchase() {
        return salePurchase;
    }

    public void setSalePurchase(String salePurchase) {
        this.salePurchase = salePurchase;
    }

    public Integer getStkAdjId() {
        return stkAdjId;
    }

    public void setStkAdjId(Integer stkAdjId) {
        this.stkAdjId = stkAdjId;
    }

    public String getStkAdjNo() {
        return stkAdjNo;
    }

    public void setStkAdjNo(String stkAdjNo) {
        this.stkAdjNo = stkAdjNo;
    }

    public float getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(float unitPrice) {
        this.unitPrice = unitPrice;
    }

    public SpStockAdjDetails(Integer stkAdjId, String stkAdjNo, String partNo, String salePurchase, float qty, float unitPrice, String remarks) {
        this.stkAdjId = stkAdjId;
        this.stkAdjNo = stkAdjNo;
        this.partNo = partNo;
        this.salePurchase = salePurchase;
        this.qty = qty;
        this.unitPrice = unitPrice;
        this.remarks = remarks;
    }

      public SpStockAdjDetails() {
    }
  
    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpStockAdjDetails)) {
            return false;
        }
        SpStockAdjDetails other = (SpStockAdjDetails) object;
        if ((this.stkAdjId == null && other.stkAdjId != null) || (this.stkAdjId != null && !this.stkAdjId.equals(other.stkAdjId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpStockAdjDetails[id=" + stkAdjId + "]";
    }

}
