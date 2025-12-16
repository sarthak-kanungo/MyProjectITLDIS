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
public class SpInventSaleDetailsChrgPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "Invoice_No")
    private String invoiceNo;
    @Basic(optional = false)
    @Column(name = "charge_branch_id")
    private Integer charge_branch_id;
    @Basic(optional = false)
    @Column(name = "Part_no")
    private String partno;

    public SpInventSaleDetailsChrgPK() {
    }

    public SpInventSaleDetailsChrgPK(String invoiceNo, String partno) {
        this.invoiceNo = invoiceNo;
        this.partno = partno;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getPartno() {
        return partno;
    }

    public void setPartno(String partno) {
        this.partno = partno;
    }

    public Integer getCharge_branch_id() {
        return charge_branch_id;
    }

    public void setCharge_branch_id(Integer charge_branch_id) {
        this.charge_branch_id = charge_branch_id;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (invoiceNo != null ? invoiceNo.hashCode() : 0);
        hash += (partno != null ? partno.hashCode() : 0);
        hash += (charge_branch_id != null ? charge_branch_id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpInventSaleDetailsChrgPK)) {
            return false;
        }
        SpInventSaleDetailsChrgPK other = (SpInventSaleDetailsChrgPK) object;
        if ((this.invoiceNo == null && other.invoiceNo != null) || (this.invoiceNo != null && !this.invoiceNo.equals(other.invoiceNo))) {
            return false;
        }
        if ((this.partno == null && other.partno != null) || (this.partno != null && !this.partno.equals(other.partno))) {
            return false;
        }
        if ((this.charge_branch_id == null && other.charge_branch_id != null) || (this.charge_branch_id != null && !this.charge_branch_id.equals(other.charge_branch_id))) {
            return false;
        }

        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpInventSaleDetailsChrgPK[invoiceNo=" + invoiceNo + ", partno=" + partno + ", charge_branch_id=" + charge_branch_id + "]";
    }

}
