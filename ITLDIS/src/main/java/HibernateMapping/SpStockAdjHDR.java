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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author prashant.kumar
 */
@Entity
@Table(name = "SP_STK_ADJ_HDR")
public class SpStockAdjHDR implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "STK_ADJ_NO")
    private String stkAdjNo;
    @Column(name = "DEALER_CODE")
    private String dealerCode;
    @Column(name = "STK_ADJ_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date stkAdjDate;
    @Column(name = "TOTAL_PURCHASE_VALUE")
    private float totalPuschaseValue;
    @Column(name = "TOTAL_SALE_VALUE")
    private float totalSaleValue;
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "CreatedBy")
    private String createdBy;

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

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public Date getStkAdjDate() {
        return stkAdjDate;
    }

    public void setStkAdjDate(Date stkAdjDate) {
        this.stkAdjDate = stkAdjDate;
    }

    public String getStkAdjNo() {
        return stkAdjNo;
    }

    public void setStkAdjNo(String stkAdjNo) {
        this.stkAdjNo = stkAdjNo;
    }

    public float getTotalPuschaseValue() {
        return totalPuschaseValue;
    }

    public void setTotalPuschaseValue(float totalPuschaseValue) {
        this.totalPuschaseValue = totalPuschaseValue;
    }

    public float getTotalSaleValue() {
        return totalSaleValue;
    }

    public void setTotalSaleValue(float totalSaleValue) {
        this.totalSaleValue = totalSaleValue;
    }

    public SpStockAdjHDR() {
    }



    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stkAdjNo != null ? stkAdjNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpStockAdjHDR)) {
            return false;
        }
        SpStockAdjHDR other = (SpStockAdjHDR) object;
        if ((this.stkAdjNo == null && other.stkAdjNo != null) || (this.stkAdjNo != null && !this.stkAdjNo.equals(other.stkAdjNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpStockAdjHDR[id=" + stkAdjNo + "]";
    }

}
