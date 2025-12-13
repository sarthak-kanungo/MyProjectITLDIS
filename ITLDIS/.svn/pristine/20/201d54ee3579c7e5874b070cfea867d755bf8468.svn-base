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
public class SpInventSreturnDetailsPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "RETURN_NO")
    private String returnNo;
    @Basic(optional = false)
    @Column(name = "Part_no")
    private String partno;

    public SpInventSreturnDetailsPK() {
    }

    public SpInventSreturnDetailsPK(String returnNo, String partno) {
        this.returnNo = returnNo;
        this.partno = partno;
    }

    public String getReturnNo() {
        return returnNo;
    }

    public void setReturnNo(String returnNo) {
        this.returnNo = returnNo;
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
        hash += (returnNo != null ? returnNo.hashCode() : 0);
        hash += (partno != null ? partno.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpInventSreturnDetailsPK)) {
            return false;
        }
        SpInventSreturnDetailsPK other = (SpInventSreturnDetailsPK) object;
        if ((this.returnNo == null && other.returnNo != null) || (this.returnNo != null && !this.returnNo.equals(other.returnNo))) {
            return false;
        }
        if ((this.partno == null && other.partno != null) || (this.partno != null && !this.partno.equals(other.partno))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpInventSreturnDetailsPK[returnNo=" + returnNo + ", partno=" + partno + "]";
    }

}
