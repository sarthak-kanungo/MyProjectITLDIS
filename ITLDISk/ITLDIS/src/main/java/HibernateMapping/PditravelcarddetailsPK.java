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
 * @author vijay.mishra
 */
@Embeddable
public class PditravelcarddetailsPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "PDI_No")
    private String pDINo;
    @Basic(optional = false)
    @Column(name = "PartName")
    private String partName;

    public PditravelcarddetailsPK() {
    }

    public PditravelcarddetailsPK(String pDINo, String partName) {
        this.pDINo = pDINo;
        this.partName = partName;
    }

    public String getPDINo() {
        return pDINo;
    }

    public void setPDINo(String pDINo) {
        this.pDINo = pDINo;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pDINo != null ? pDINo.hashCode() : 0);
        hash += (partName != null ? partName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PditravelcarddetailsPK)) {
            return false;
        }
        PditravelcarddetailsPK other = (PditravelcarddetailsPK) object;
        if ((this.pDINo == null && other.pDINo != null) || (this.pDINo != null && !this.pDINo.equals(other.pDINo))) {
            return false;
        }
        if ((this.partName == null && other.partName != null) || (this.partName != null && !this.partName.equals(other.partName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.PditravelcarddetailsPK[pDINo=" + pDINo + ", partName=" + partName + "]";
    }

}
