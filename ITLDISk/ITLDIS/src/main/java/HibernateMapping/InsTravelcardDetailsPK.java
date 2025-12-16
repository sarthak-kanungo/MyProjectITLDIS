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
public class InsTravelcardDetailsPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "INS_No")
    private String iNSNo;
    @Basic(optional = false)
    @Column(name = "PartName")
    private String partNo;

    public InsTravelcardDetailsPK() {
    }

    public InsTravelcardDetailsPK(String iNSNo, String partNo) {
        this.iNSNo = iNSNo;
        this.partNo = partNo;
    }

    public String getINSNo() {
        return iNSNo;
    }

    public void setINSNo(String iNSNo) {
        this.iNSNo = iNSNo;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iNSNo != null ? iNSNo.hashCode() : 0);
        hash += (partNo != null ? partNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsTravelcardDetailsPK)) {
            return false;
        }
        InsTravelcardDetailsPK other = (InsTravelcardDetailsPK) object;
        if ((this.iNSNo == null && other.iNSNo != null) || (this.iNSNo != null && !this.iNSNo.equals(other.iNSNo))) {
            return false;
        }
        if ((this.partNo == null && other.partNo != null) || (this.partNo != null && !this.partNo.equals(other.partNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.InsTravelcardDetailsPK[iNSNo=" + iNSNo + ", partNo=" + partNo + "]";
    }

}
