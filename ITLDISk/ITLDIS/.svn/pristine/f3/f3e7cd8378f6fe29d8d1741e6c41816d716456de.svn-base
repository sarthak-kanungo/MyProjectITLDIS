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
@Table(name = "SP_INVENT_SRETURN_DETAILS")
@NamedQueries({
    @NamedQuery(name = "SpInventSreturnDetails.findAll", query = "SELECT s FROM SpInventSreturnDetails s"),
    @NamedQuery(name = "SpInventSreturnDetails.findByReturnNo", query = "SELECT s FROM SpInventSreturnDetails s WHERE s.spInventSreturnDetailsPK.returnNo = :returnNo"),
    @NamedQuery(name = "SpInventSreturnDetails.findByPartno", query = "SELECT s FROM SpInventSreturnDetails s WHERE s.spInventSreturnDetailsPK.partno = :partno"),
    @NamedQuery(name = "SpInventSreturnDetails.findByPartdesc", query = "SELECT s FROM SpInventSreturnDetails s WHERE s.partdesc = :partdesc"),
    @NamedQuery(name = "SpInventSreturnDetails.findByInvoiceQty", query = "SELECT s FROM SpInventSreturnDetails s WHERE s.invoiceQty = :invoiceQty"),
    @NamedQuery(name = "SpInventSreturnDetails.findByUnitvalue", query = "SELECT s FROM SpInventSreturnDetails s WHERE s.unitvalue = :unitvalue"),
    @NamedQuery(name = "SpInventSreturnDetails.findByRecdQty", query = "SELECT s FROM SpInventSreturnDetails s WHERE s.recdQty = :recdQty")})
public class SpInventSreturnDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SpInventSreturnDetailsPK spInventSreturnDetailsPK;
    @Basic(optional = false)
    @Column(name = "Part_desc")
    private String partdesc;
    @Basic(optional = false)
    @Column(name = "Invoice_Qty")
    private double invoiceQty;
    @Basic(optional = false)
    @Column(name = "Unit_value")
    private double unitvalue;
    @Basic(optional = false)
    @Column(name = "RECD_QTY")
    private double recdQty;

    public SpInventSreturnDetails() {
    }

    public SpInventSreturnDetails(SpInventSreturnDetailsPK spInventSreturnDetailsPK) {
        this.spInventSreturnDetailsPK = spInventSreturnDetailsPK;
    }

    public SpInventSreturnDetails(SpInventSreturnDetailsPK spInventSreturnDetailsPK, String partdesc, double invoiceQty, double unitvalue, double recdQty) {
        this.spInventSreturnDetailsPK = spInventSreturnDetailsPK;
        this.partdesc = partdesc;
        this.invoiceQty = invoiceQty;
        this.unitvalue = unitvalue;
        this.recdQty = recdQty;
    }

    public SpInventSreturnDetails(String returnNo, String partno) {
        this.spInventSreturnDetailsPK = new SpInventSreturnDetailsPK(returnNo, partno);
    }

    public SpInventSreturnDetailsPK getSpInventSreturnDetailsPK() {
        return spInventSreturnDetailsPK;
    }

    public void setSpInventSreturnDetailsPK(SpInventSreturnDetailsPK spInventSreturnDetailsPK) {
        this.spInventSreturnDetailsPK = spInventSreturnDetailsPK;
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

    public double getUnitvalue() {
        return unitvalue;
    }

    public void setUnitvalue(double unitvalue) {
        this.unitvalue = unitvalue;
    }

    public double getRecdQty() {
        return recdQty;
    }

    public void setRecdQty(double recdQty) {
        this.recdQty = recdQty;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spInventSreturnDetailsPK != null ? spInventSreturnDetailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpInventSreturnDetails)) {
            return false;
        }
        SpInventSreturnDetails other = (SpInventSreturnDetails) object;
        if ((this.spInventSreturnDetailsPK == null && other.spInventSreturnDetailsPK != null) || (this.spInventSreturnDetailsPK != null && !this.spInventSreturnDetailsPK.equals(other.spInventSreturnDetailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpInventSreturnDetails[spInventSreturnDetailsPK=" + spInventSreturnDetailsPK + "]";
    }

}
