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
@Table(name = "SW_ins_travelcard_details")
@NamedQueries({
    @NamedQuery(name = "InsTravelcardDetails.findAll", query = "SELECT i FROM InsTravelcardDetails i"),
    @NamedQuery(name = "InsTravelcardDetails.findByINSNo", query = "SELECT i FROM InsTravelcardDetails i WHERE i.insTravelcardDetailsPK.iNSNo = :iNSNo"),
    @NamedQuery(name = "InsTravelcardDetails.findByPartNo", query = "SELECT i FROM InsTravelcardDetails i WHERE i.insTravelcardDetailsPK.partNo = :partNo"),
    @NamedQuery(name = "InsTravelcardDetails.findByPartVendorName", query = "SELECT i FROM InsTravelcardDetails i WHERE i.partVendorName = :partVendorName"),
    @NamedQuery(name = "InsTravelcardDetails.findByPartSerialNo", query = "SELECT i FROM InsTravelcardDetails i WHERE i.partSerialNo = :partSerialNo")})
public class InsTravelcardDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InsTravelcardDetailsPK insTravelcardDetailsPK;
    @Column(name = "PartVendorName")
    private String partVendorName;
    @Column(name = "PartSerialNo")
    private String partSerialNo;

    public InsTravelcardDetails() {
    }

    public InsTravelcardDetails(InsTravelcardDetailsPK insTravelcardDetailsPK) {
        this.insTravelcardDetailsPK = insTravelcardDetailsPK;
    }

    public InsTravelcardDetails(String iNSNo, String partNo) {
        this.insTravelcardDetailsPK = new InsTravelcardDetailsPK(iNSNo, partNo);
    }

    public InsTravelcardDetailsPK getInsTravelcardDetailsPK() {
        return insTravelcardDetailsPK;
    }

    public void setInsTravelcardDetailsPK(InsTravelcardDetailsPK insTravelcardDetailsPK) {
        this.insTravelcardDetailsPK = insTravelcardDetailsPK;
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
        hash += (insTravelcardDetailsPK != null ? insTravelcardDetailsPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsTravelcardDetails)) {
            return false;
        }
        InsTravelcardDetails other = (InsTravelcardDetails) object;
        if ((this.insTravelcardDetailsPK == null && other.insTravelcardDetailsPK != null) || (this.insTravelcardDetailsPK != null && !this.insTravelcardDetailsPK.equals(other.insTravelcardDetailsPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.InsTravelcardDetails[insTravelcardDetailsPK=" + insTravelcardDetailsPK + "]";
    }

}
