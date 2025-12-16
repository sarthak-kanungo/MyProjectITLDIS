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
 * @author kundan.rastogi
 */
@Embeddable
public class SpOrderInvoicesPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "ERP_ORDER_NO")
    private String erpOrderNo;
    @Basic(optional = false)
    @Column(name = "ERP_PART_ORDER_NO")
    private String erpPartOrderNo;
    @Basic(optional = false)
    @Column(name = "INVOICE_NO")
    private String invoiceNo;
    @Column(name = "ORDERED_PART")
    private String orderedPart;

    public SpOrderInvoicesPK() {
    }

    public SpOrderInvoicesPK(String erpOrderNo, String erpPartOrderNo, String invoiceNo, String orderedPart) {
        this.erpOrderNo = erpOrderNo;
        this.erpPartOrderNo = erpPartOrderNo;
        this.invoiceNo = invoiceNo;
        this.orderedPart = orderedPart;
    }

    public String getErpOrderNo() {
        return erpOrderNo;
    }

    public void setErpOrderNo(String erpOrderNo) {
        this.erpOrderNo = erpOrderNo;
    }

    public String getErpPartOrderNo() {
        return erpPartOrderNo;
    }

    public void setErpPartOrderNo(String erpPartOrderNo) {
        this.erpPartOrderNo = erpPartOrderNo;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

     /**
     * @return the orderedPart
     */
    public String getOrderedPart() {
        return orderedPart;
    }

    /**
     * @param orderedPart the orderedPart to set
     */
    public void setOrderedPart(String orderedPart) {
        this.orderedPart = orderedPart;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (erpOrderNo != null ? erpOrderNo.hashCode() : 0);
        hash += (erpPartOrderNo != null ? erpPartOrderNo.hashCode() : 0);
        hash += (invoiceNo != null ? invoiceNo.hashCode() : 0);
         hash += (orderedPart != null ? orderedPart.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpOrderInvoicesPK)) {
            return false;
        }
        SpOrderInvoicesPK other = (SpOrderInvoicesPK) object;
        if ((this.erpOrderNo == null && other.erpOrderNo != null) || (this.erpOrderNo != null && !this.erpOrderNo.equals(other.erpOrderNo))) {
            return false;
        }
        if ((this.erpPartOrderNo == null && other.erpPartOrderNo != null) || (this.erpPartOrderNo != null && !this.erpPartOrderNo.equals(other.erpPartOrderNo))) {
            return false;
        }
        if ((this.invoiceNo == null && other.invoiceNo != null) || (this.invoiceNo != null && !this.invoiceNo.equals(other.invoiceNo))) {
            return false;
        }
         if ((this.orderedPart == null && other.orderedPart != null) || (this.orderedPart != null && !this.orderedPart.equals(other.orderedPart))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpOrderInvoicesPK[erpOrderNo=" + erpOrderNo + ", erpPartOrderNo=" + erpPartOrderNo + ", invoiceNo=" + invoiceNo+", orderedPart=" + orderedPart + "]";
    }

}
