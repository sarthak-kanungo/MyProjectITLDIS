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
 * @author kundan.rastogi
 */
@Entity
@Table(name = "SP_ORDER_INV_GRN_DETAILS")
@NamedQueries({
    @NamedQuery(name = "SpOrderInvGrnDetails.findAll", query = "SELECT s FROM SpOrderInvGrnDetails s"),
    @NamedQuery(name = "SpOrderInvGrnDetails.findByGrNo", query = "SELECT s FROM SpOrderInvGrnDetails s WHERE s.spOrderInvGrnDetailsPK.grNo = :grNo"),
    @NamedQuery(name = "SpOrderInvGrnDetails.findByPartno", query = "SELECT s FROM SpOrderInvGrnDetails s WHERE s.spOrderInvGrnDetailsPK.partno = :partno"),
    @NamedQuery(name = "SpOrderInvGrnDetails.findByPartdesc", query = "SELECT s FROM SpOrderInvGrnDetails s WHERE s.partdesc = :partdesc"),
    @NamedQuery(name = "SpOrderInvGrnDetails.findByInvoiceQty", query = "SELECT s FROM SpOrderInvGrnDetails s WHERE s.invoiceQty = :invoiceQty"),
    @NamedQuery(name = "SpOrderInvGrnDetails.findByReceivedQty", query = "SELECT s FROM SpOrderInvGrnDetails s WHERE s.receivedQty = :receivedQty"),
    @NamedQuery(name = "SpOrderInvGrnDetails.findByUnitvalue", query = "SELECT s FROM SpOrderInvGrnDetails s WHERE s.unitvalue = :unitvalue")})
public class SpOrderInvGrnDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SpOrderInvGrnDetailsPK spOrderInvGrnDetailsPK;
    @Basic(optional = false)
    @Column(name = "Part_desc")
    private String partdesc;
    @Basic(optional = false)
    @Column(name = "Invoice_Qty")
    private double invoiceQty;
    @Basic(optional = false)
    @Column(name = "Received_Qty")
    private double receivedQty;
    @Basic(optional = false)
    @Column(name = "Unit_value")
    private double unitvalue;

    public SpOrderInvGrnDetails() {
    }

    public SpOrderInvGrnDetails(SpOrderInvGrnDetailsPK spOrderInvGrnDetailsPK) {
        this.spOrderInvGrnDetailsPK = spOrderInvGrnDetailsPK;
    }

    public SpOrderInvGrnDetails(SpOrderInvGrnDetailsPK spOrderInvGrnDetailsPK, String partdesc, double invoiceQty, double receivedQty, double unitvalue) {
        this.spOrderInvGrnDetailsPK = spOrderInvGrnDetailsPK;
        this.partdesc = partdesc;
        this.invoiceQty = invoiceQty;
        this.receivedQty = receivedQty;
        this.unitvalue = unitvalue;
    }

    public SpOrderInvGrnDetails(String grNo, String partno) {
        this.spOrderInvGrnDetailsPK = new SpOrderInvGrnDetailsPK(grNo, partno);
    }

    public SpOrderInvGrnDetailsPK getSpOrderInvGrnDetailsPK() {
        return spOrderInvGrnDetailsPK;
    }

    public void setSpOrderInvGrnDetailsPK(SpOrderInvGrnDetailsPK spOrderInvGrnDetailsPK) {
        this.spOrderInvGrnDetailsPK = spOrderInvGrnDetailsPK;
    }

    public String getPartdesc() {
        return partdesc;
    }

    public void setPartdesc(String partdesc) {
        this.partdesc = partdesc;
    }

    public double getInvoiceQty() {
        return invoiceQty;
    }

    public void setInvoiceQty(double invoiceQty) {
        this.invoiceQty = invoiceQty;
    }

    public double getReceivedQty() {
        return receivedQty;
    }

    public void setReceivedQty(double receivedQty) {
        this.receivedQty = receivedQty;
    }

    public double getUnitvalue() {
        return unitvalue;
    }

    public void setUnitvalue(double unitvalue) {
        this.unitvalue = unitvalue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spOrderInvGrnDetailsPK != null ? spOrderInvGrnDetailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpOrderInvGrnDetails)) {
            return false;
        }
        SpOrderInvGrnDetails other = (SpOrderInvGrnDetails) object;
        if ((this.spOrderInvGrnDetailsPK == null && other.spOrderInvGrnDetailsPK != null) || (this.spOrderInvGrnDetailsPK != null && !this.spOrderInvGrnDetailsPK.equals(other.spOrderInvGrnDetailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpOrderInvGrnDetails[spOrderInvGrnDetailsPK=" + spOrderInvGrnDetailsPK + "]";
    }

}
