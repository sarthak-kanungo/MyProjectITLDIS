/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author kundan.rastogi
 */
@Entity
@Table(name = "SP_ORDER_TYPE_MASTER")
@NamedQueries({
    @NamedQuery(name = "SpOrderTypeMaster.findAll", query = "SELECT s FROM SpOrderTypeMaster s"),
    @NamedQuery(name = "SpOrderTypeMaster.findByOrderType", query = "SELECT s FROM SpOrderTypeMaster s WHERE s.orderType = :orderType"),
    @NamedQuery(name = "SpOrderTypeMaster.findByCodeRefNo", query = "SELECT s FROM SpOrderTypeMaster s WHERE s.codeRefNo = :codeRefNo")})
public class SpOrderTypeMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "ORDER_TYPE")
    private String orderType;
    @Id
    @Basic(optional = false)
    @Column(name = "CODE_REF_NO")
    private String codeRefNo;

    public SpOrderTypeMaster() {
    }

    public SpOrderTypeMaster(String codeRefNo) {
        this.codeRefNo = codeRefNo;
    }

    public SpOrderTypeMaster(String codeRefNo, String orderType) {
        this.codeRefNo = codeRefNo;
        this.orderType = orderType;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getCodeRefNo() {
        return codeRefNo;
    }

    public void setCodeRefNo(String codeRefNo) {
        this.codeRefNo = codeRefNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codeRefNo != null ? codeRefNo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpOrderTypeMaster)) {
            return false;
        }
        SpOrderTypeMaster other = (SpOrderTypeMaster) object;
        if ((this.codeRefNo == null && other.codeRefNo != null) || (this.codeRefNo != null && !this.codeRefNo.equals(other.codeRefNo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpOrderTypeMaster[codeRefNo=" + codeRefNo + "]";
    }

}
