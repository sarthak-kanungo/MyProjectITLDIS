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
 * @author megha.pandya
 */
@Embeddable
public class SpInventOtherDealerDetailsPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "INVENT_NO")
    private String inventNo;
    @Basic(optional = false)
    @Column(name = "Part_no")
    private String partno;

    public SpInventOtherDealerDetailsPK() {
    }

    public SpInventOtherDealerDetailsPK(String inventNo, String partno) {
        this.inventNo = inventNo;
        this.partno = partno;
    }

    public String getInventNo() {
        return inventNo;
    }

    public void setInventNo(String inventNo) {
        this.inventNo = inventNo;
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
        hash += (inventNo != null ? inventNo.hashCode() : 0);
        hash += (partno != null ? partno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpInventOtherDealerDetailsPK)) {
            return false;
        }
        SpInventOtherDealerDetailsPK other = (SpInventOtherDealerDetailsPK) object;
        if ((this.inventNo == null && other.inventNo != null) || (this.inventNo != null && !this.inventNo.equals(other.inventNo))) {
            return false;
        }
        if ((this.partno == null && other.partno != null) || (this.partno != null && !this.partno.equals(other.partno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpInventOtherDealerDetailsPK[inventNo=" + inventNo + ", partno=" + partno + "]";
    }

}
