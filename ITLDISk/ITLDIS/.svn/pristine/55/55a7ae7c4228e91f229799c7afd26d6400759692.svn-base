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
public class InsenquiriesPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "INS_No")
    private String iNSNo;
    @Basic(optional = false)
    @Column(name = "CustomerName")
    private String customerName;

    public InsenquiriesPK() {
    }

    public InsenquiriesPK(String iNSNo, String customerName) {
        this.iNSNo = iNSNo;
        this.customerName = customerName;
    }

    public String getINSNo() {
        return iNSNo;
    }

    public void setINSNo(String iNSNo) {
        this.iNSNo = iNSNo;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iNSNo != null ? iNSNo.hashCode() : 0);
        hash += (customerName != null ? customerName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsenquiriesPK)) {
            return false;
        }
        InsenquiriesPK other = (InsenquiriesPK) object;
        if ((this.iNSNo == null && other.iNSNo != null) || (this.iNSNo != null && !this.iNSNo.equals(other.iNSNo))) {
            return false;
        }
        if ((this.customerName == null && other.customerName != null) || (this.customerName != null && !this.customerName.equals(other.customerName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.InsenquiriesPK[iNSNo=" + iNSNo + ", customerName=" + customerName + "]";
    }

}
