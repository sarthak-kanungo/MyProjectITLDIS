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
public class SpInventReorderLevelPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "DEALER_CODE")
    private String dealerCode;
    @Basic(optional = false)
    @Column(name = "PART_NO")
    private String partNo;

    public SpInventReorderLevelPK() {
    }

    public SpInventReorderLevelPK(String dealerCode, String partNo) {
        this.dealerCode = dealerCode;
        this.partNo = partNo;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
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
        hash += (dealerCode != null ? dealerCode.hashCode() : 0);
        hash += (partNo != null ? partNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpInventReorderLevelPK)) {
            return false;
        }
        SpInventReorderLevelPK other = (SpInventReorderLevelPK) object;
        if ((this.dealerCode == null && other.dealerCode != null) || (this.dealerCode != null && !this.dealerCode.equals(other.dealerCode))) {
            return false;
        }
        if ((this.partNo == null && other.partNo != null) || (this.partNo != null && !this.partNo.equals(other.partNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpInventReorderLevelPK[dealerCode=" + dealerCode + ", partNo=" + partNo + "]";
    }

}
