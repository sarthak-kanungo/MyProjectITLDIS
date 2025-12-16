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
 * @author avinash.pandey
 */

@Embeddable
public class SpOrderDetailsEXPPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "PO_DTL_ID")
    private Integer poDetailId;
   
    

    public SpOrderDetailsEXPPK() {
    }

    public SpOrderDetailsEXPPK(Integer poDetailId) {
        this.poDetailId = poDetailId;
        
    }

    public Integer getPODetailId() {
        return poDetailId;
    }

    public void setPODetailId(Integer poDetailId) {
        this.poDetailId = poDetailId;
    }

    

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (poDetailId != null ? poDetailId.hashCode() : 0);
       
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpOrderDetailsEXPPK)) {
            return false;
        }
        SpOrderDetailsEXPPK other = (SpOrderDetailsEXPPK) object;
        if ((this.poDetailId == null && other.poDetailId != null) || (this.poDetailId != null && !this.poDetailId.equals(other.poDetailId))) {
            return false;
        }
        
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpOrderDetailsEXPPK[poDetailId=" + poDetailId + "]";
    }

}
