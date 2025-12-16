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
public class SpOrderDetailsPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "CUST_PO_NO")
    private String custPoNo;
    @Basic(optional = false)
    @Column(name = "POSITION_NO")
    private int positionNo;
    @Basic(optional = false)
    @Column(name = "PART_NO")
    private String partNo;

    public SpOrderDetailsPK() {
    }

    public SpOrderDetailsPK(String custPoNo, int positionNo, String partNo) {
        this.custPoNo = custPoNo;
        this.positionNo = positionNo;
        this.partNo = partNo;
    }

    public String getCustPoNo() {
        return custPoNo;
    }

    public void setCustPoNo(String custPoNo) {
        this.custPoNo = custPoNo;
    }

    public int getPositionNo() {
        return positionNo;
    }

    public void setPositionNo(int positionNo) {
        this.positionNo = positionNo;
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
        hash += (custPoNo != null ? custPoNo.hashCode() : 0);
        hash += (int) positionNo;
        hash += (partNo != null ? partNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpOrderDetailsPK)) {
            return false;
        }
        SpOrderDetailsPK other = (SpOrderDetailsPK) object;
        if ((this.custPoNo == null && other.custPoNo != null) || (this.custPoNo != null && !this.custPoNo.equals(other.custPoNo))) {
            return false;
        }
        if (this.positionNo != other.positionNo) {
            return false;
        }
        if ((this.partNo == null && other.partNo != null) || (this.partNo != null && !this.partNo.equals(other.partNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpOrderDetailsPK[custPoNo=" + custPoNo + ", positionNo=" + positionNo + ", partNo=" + partNo + "]";
    }

}
