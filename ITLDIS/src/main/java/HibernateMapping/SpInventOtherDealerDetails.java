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
@Table(name = "SP_INVENT_OTHER_DEALER_DETAILS")
@NamedQueries({
    @NamedQuery(name = "SpInventOtherDealerDetails.findAll", query = "SELECT s FROM SpInventOtherDealerDetails s"),
    @NamedQuery(name = "SpInventOtherDealerDetails.findByInventNo", query = "SELECT s FROM SpInventOtherDealerDetails s WHERE s.spInventOtherDealerDetailsPK.inventNo = :inventNo"),
    @NamedQuery(name = "SpInventOtherDealerDetails.findByPartno", query = "SELECT s FROM SpInventOtherDealerDetails s WHERE s.spInventOtherDealerDetailsPK.partno = :partno"),
    @NamedQuery(name = "SpInventOtherDealerDetails.findByPartdesc", query = "SELECT s FROM SpInventOtherDealerDetails s WHERE s.partdesc = :partdesc"),
    @NamedQuery(name = "SpInventOtherDealerDetails.findByQty", query = "SELECT s FROM SpInventOtherDealerDetails s WHERE s.qty = :qty"),
    @NamedQuery(name = "SpInventOtherDealerDetails.findByUnitvalue", query = "SELECT s FROM SpInventOtherDealerDetails s WHERE s.unitvalue = :unitvalue")})
public class SpInventOtherDealerDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SpInventOtherDealerDetailsPK spInventOtherDealerDetailsPK;
    @Basic(optional = false)
    @Column(name = "Part_desc")
    private String partdesc;
    @Basic(optional = false)
    @Column(name = "Qty")
    private double qty;
    @Basic(optional = false)
    @Column(name = "Unit_value")
    private double unitvalue;

    public SpInventOtherDealerDetails() {
    }

    public SpInventOtherDealerDetails(SpInventOtherDealerDetailsPK spInventOtherDealerDetailsPK) {
        this.spInventOtherDealerDetailsPK = spInventOtherDealerDetailsPK;
    }

    public SpInventOtherDealerDetails(SpInventOtherDealerDetailsPK spInventOtherDealerDetailsPK, String partdesc, double qty, double unitvalue) {
        this.spInventOtherDealerDetailsPK = spInventOtherDealerDetailsPK;
        this.partdesc = partdesc;
        this.qty = qty;
        this.unitvalue = unitvalue;
    }

    public SpInventOtherDealerDetails(String inventNo, String partno) {
        this.spInventOtherDealerDetailsPK = new SpInventOtherDealerDetailsPK(inventNo, partno);
    }

    public SpInventOtherDealerDetailsPK getSpInventOtherDealerDetailsPK() {
        return spInventOtherDealerDetailsPK;
    }

    public void setSpInventOtherDealerDetailsPK(SpInventOtherDealerDetailsPK spInventOtherDealerDetailsPK) {
        this.spInventOtherDealerDetailsPK = spInventOtherDealerDetailsPK;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spInventOtherDealerDetailsPK != null ? spInventOtherDealerDetailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpInventOtherDealerDetails)) {
            return false;
        }
        SpInventOtherDealerDetails other = (SpInventOtherDealerDetails) object;
        if ((this.spInventOtherDealerDetailsPK == null && other.spInventOtherDealerDetailsPK != null) || (this.spInventOtherDealerDetailsPK != null && !this.spInventOtherDealerDetailsPK.equals(other.spInventOtherDealerDetailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpInventOtherDealerDetails[spInventOtherDealerDetailsPK=" + spInventOtherDealerDetailsPK + "]";
    }

}
