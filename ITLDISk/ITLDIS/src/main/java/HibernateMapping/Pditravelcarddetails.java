/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "SW_pdi_travelcard_details")
@NamedQueries({
    @NamedQuery(name = "Pditravelcarddetails.findAll", query = "SELECT p FROM Pditravelcarddetails p"),
    @NamedQuery(name = "Pditravelcarddetails.findByPDINo", query = "SELECT p FROM Pditravelcarddetails p WHERE p.pditravelcarddetailsPK.pDINo = :pDINo"),
    @NamedQuery(name = "Pditravelcarddetails.findByPartName", query = "SELECT p FROM Pditravelcarddetails p WHERE p.pditravelcarddetailsPK.partName = :partName"),
    @NamedQuery(name = "Pditravelcarddetails.findByPartVendorName", query = "SELECT p FROM Pditravelcarddetails p WHERE p.partVendorName = :partVendorName"),
    @NamedQuery(name = "Pditravelcarddetails.findByPartSerialNo", query = "SELECT p FROM Pditravelcarddetails p WHERE p.partSerialNo = :partSerialNo")})
public class Pditravelcarddetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PditravelcarddetailsPK pditravelcarddetailsPK;
    @Column(name = "PartVendorName")
    private String partVendorName;
    @Column(name = "PartSerialNo")
    private String partSerialNo;

    public Pditravelcarddetails() {
    }

    public Pditravelcarddetails(PditravelcarddetailsPK pditravelcarddetailsPK) {
        this.pditravelcarddetailsPK = pditravelcarddetailsPK;
    }

    public Pditravelcarddetails(String pDINo, String partName) {
        this.pditravelcarddetailsPK = new PditravelcarddetailsPK(pDINo, partName);
    }

    public PditravelcarddetailsPK getPditravelcarddetailsPK() {
        return pditravelcarddetailsPK;
    }

    public void setPditravelcarddetailsPK(PditravelcarddetailsPK pditravelcarddetailsPK) {
        this.pditravelcarddetailsPK = pditravelcarddetailsPK;
    }

    public String getPartVendorName() {
        return partVendorName;
    }

    public void setPartVendorName(String partVendorName) {
        this.partVendorName = partVendorName;
    }

    public String getPartSerialNo() {
        return partSerialNo;
    }

    public void setPartSerialNo(String partSerialNo) {
        this.partSerialNo = partSerialNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pditravelcarddetailsPK != null ? pditravelcarddetailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pditravelcarddetails)) {
            return false;
        }
        Pditravelcarddetails other = (Pditravelcarddetails) object;
        if ((this.pditravelcarddetailsPK == null && other.pditravelcarddetailsPK != null) || (this.pditravelcarddetailsPK != null && !this.pditravelcarddetailsPK.equals(other.pditravelcarddetailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Pditravelcarddetails[pditravelcarddetailsPK=" + pditravelcarddetailsPK + "]";
    }

}
