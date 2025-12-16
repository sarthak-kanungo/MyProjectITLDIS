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
public class SpOrderInvGrnDetailsPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "GR_NO")
    private String grNo;
    @Basic(optional = false)
    @Column(name = "Part_no")
    private String partno;

    public SpOrderInvGrnDetailsPK() {
    }

    public SpOrderInvGrnDetailsPK(String grNo, String partno) {
        this.grNo = grNo;
        this.partno = partno;
    }

    public String getGrNo() {
        return grNo;
    }

    public void setGrNo(String grNo) {
        this.grNo = grNo;
    }

    public String getPartno() {
        return partno;
    }

    public void setPartno(String partno) {
        this.partno = partno;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grNo != null ? grNo.hashCode() : 0);
        hash += (partno != null ? partno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpOrderInvGrnDetailsPK)) {
            return false;
        }
        SpOrderInvGrnDetailsPK other = (SpOrderInvGrnDetailsPK) object;
        if ((this.grNo == null && other.grNo != null) || (this.grNo != null && !this.grNo.equals(other.grNo))) {
            return false;
        }
        if ((this.partno == null && other.partno != null) || (this.partno != null && !this.partno.equals(other.partno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpOrderInvGrnDetailsPK[grNo=" + grNo + ", partno=" + partno + "]";
    }

}
