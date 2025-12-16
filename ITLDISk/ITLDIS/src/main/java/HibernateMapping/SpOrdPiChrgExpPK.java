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
 * @author yogasmita.patel
 */
@Embeddable
public class SpOrdPiChrgExpPK implements Serializable{
    @Basic(optional = false)
    @Column(name = "PI_NO")
    private String piNo;
    @Basic(optional = false)
    @Column(name = "CostHeadID")
    private String costHeadId;

    public String getCostHeadId() {
        return costHeadId;
    }

    public void setCostHeadId(String costHeadId) {
        this.costHeadId = costHeadId;
    }

    public String getPiNo() {
        return piNo;
    }

    public void setPiNo(String piNo) {
        this.piNo = piNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (piNo != null ? piNo.hashCode() : 0);
        hash += (costHeadId != null ? costHeadId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsTravelcardDetailsPK)) {
            return false;
        }
        SpOrdPiChrgExpPK other = (SpOrdPiChrgExpPK) object;
        if ((this.piNo == null && other.piNo != null) || (this.piNo != null && !this.piNo.equals(other.piNo))) {
            return false;
        }
        if ((this.costHeadId == null && other.costHeadId != null) || (this.costHeadId != null && !this.costHeadId.equals(other.costHeadId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.InsTravelcardDetailsPK[iNSNo=" + piNo + ", partNo=" + costHeadId + "]";
    }
}
