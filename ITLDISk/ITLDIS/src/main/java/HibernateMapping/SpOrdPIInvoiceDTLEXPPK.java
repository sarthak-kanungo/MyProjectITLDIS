/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author prashant.kumar
 */
@Embeddable
public class SpOrdPIInvoiceDTLEXPPK implements Serializable {

    @Basic(optional = false)
    @Column(name = "INVOICE_ID")
    private int invoiceId;
    @Basic(optional = false)
    @Column(name = "DMS_PI_NO")
    private String dmsPINo;
    @Basic(optional = false)
    @Column(name = "ERP_ORDER_NO")
    private String erpOrderNo;
    @Basic(optional = false)
    @Column(name = "ERP_PART_ORDER_NO")
    private Integer erpPartOrderNo;
    @Basic(optional = false)   
    @Column(name = "SHIPPED_PART_NO")
    private String shippedPartNo;

    public SpOrdPIInvoiceDTLEXPPK() {
    }

    public String getDmsPINo() {
        return dmsPINo;
    }

    public void setDmsPINo(String dmsPINo) {
        this.dmsPINo = dmsPINo;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getErpOrderNo() {
        return erpOrderNo;
    }

    public void setErpOrderNo(String erpOrderNo) {
        this.erpOrderNo = erpOrderNo;
    }

    public Integer getErpPartOrderNo() {
        return erpPartOrderNo;
    }

    public void setErpPartOrderNo(Integer erpPartOrderNo) {
        this.erpPartOrderNo = erpPartOrderNo;
    }

    public String getShippedPartNo() {
        return shippedPartNo;
    }

    public void setShippedPartNo(String shippedPartNo) {
        this.shippedPartNo = shippedPartNo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SpOrdPIInvoiceDTLEXPPK other = (SpOrdPIInvoiceDTLEXPPK) obj;
        if (this.invoiceId != other.invoiceId) {
            return false;
        }
        if ((this.dmsPINo == null) ? (other.dmsPINo != null) : !this.dmsPINo.equals(other.dmsPINo)) {
            return false;
        }
        if ((this.erpOrderNo == null) ? (other.erpOrderNo != null) : !this.erpOrderNo.equals(other.erpOrderNo)) {
            return false;
        }
        if ((this.erpPartOrderNo == null) ? (other.erpPartOrderNo != null) : !this.erpPartOrderNo.equals(other.erpPartOrderNo)) {
            return false;
        }
        if ((this.shippedPartNo == null) ? (other.shippedPartNo != null) : !this.shippedPartNo.equals(other.shippedPartNo)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + this.invoiceId;
        hash = 53 * hash + (this.dmsPINo != null ? this.dmsPINo.hashCode() : 0);
        hash = 53 * hash + (this.erpOrderNo != null ? this.erpOrderNo.hashCode() : 0);
        hash = 53 * hash + (this.erpPartOrderNo != null ? this.erpPartOrderNo.hashCode() : 0);
        hash = 53 * hash + (this.shippedPartNo != null ? this.shippedPartNo.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpOrdPIInvoiceDTLEXPPK[dmsPINo="+ dmsPINo + ", erpOrderNo=" + erpOrderNo + ", erpPartOrderNo=" + erpPartOrderNo + ", invoiceId=" + invoiceId+  ", shippedPartNo=" + shippedPartNo+ "]";
    }

}
