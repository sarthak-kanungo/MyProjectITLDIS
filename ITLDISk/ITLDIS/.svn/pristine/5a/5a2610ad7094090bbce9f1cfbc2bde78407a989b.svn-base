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
 * @author prashant.kumar
 */
@Entity
@Table(name = "SP_ORD_PI_INVOICE_DTL_EXP")
public class SpOrdPIInvoiceDTLEXP implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    private SpOrdPIInvoiceDTLEXPPK spOrdPIInvoiceDTLEXPPK;    
    @Column(name = "ORDERED_PART")
    private String orderedPart;
    @Basic(optional = false)
    @Column(name = "QTY_SHIPPED")
    private Integer qtyShipped;
    @Column(name = "INVOICED_RATE")
    private Float invoicedRate;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "REMARKS")
    private String remarks;    
    @Basic(optional = false)
    @Column(name = "Is_ServerSync")
    private Character isServerSync;
    @Column(name = "LastSyncDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSyncDate;

    public SpOrdPIInvoiceDTLEXPPK getSpOrdPIInvoiceDTLEXPPK() {
        return spOrdPIInvoiceDTLEXPPK;
    }

    public void setSpOrdPIInvoiceDTLEXPPK(SpOrdPIInvoiceDTLEXPPK spOrdPIInvoiceDTLEXPPK) {
        this.spOrdPIInvoiceDTLEXPPK = spOrdPIInvoiceDTLEXPPK;
    }
    
    public Float getInvoicedRate() {
        return invoicedRate;
    }

    public void setInvoicedRate(Float invoicedRate) {
        this.invoicedRate = invoicedRate;
    }

    public Character getIsServerSync() {
        return isServerSync;
    }

    public void setIsServerSync(Character isServerSync) {
        this.isServerSync = isServerSync;
    }

    public Date getLastSyncDate() {
        return lastSyncDate;
    }

    public void setLastSyncDate(Date lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }

    public String getOrderedPart() {
        return orderedPart;
    }

    public void setOrderedPart(String orderedPart) {
        this.orderedPart = orderedPart;
    }

    public Integer getQtyShipped() {
        return qtyShipped;
    }

    public void setQtyShipped(Integer qtyShipped) {
        this.qtyShipped = qtyShipped;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SpOrdPIInvoiceDTLEXP other = (SpOrdPIInvoiceDTLEXP) obj;
        if (this.spOrdPIInvoiceDTLEXPPK != other.spOrdPIInvoiceDTLEXPPK && (this.spOrdPIInvoiceDTLEXPPK == null || !this.spOrdPIInvoiceDTLEXPPK.equals(other.spOrdPIInvoiceDTLEXPPK))) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + (this.spOrdPIInvoiceDTLEXPPK != null ? this.spOrdPIInvoiceDTLEXPPK.hashCode() : 0);
        return hash;
    }
   
    @Override
    public String toString() {
        return "HibernateMapping.SpOrdPIInvoiceDTLEXP[spOrdPIInvoiceDTLEXPPK=" + spOrdPIInvoiceDTLEXPPK + "]";
    }
}
