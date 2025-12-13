/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vijay.mishra
 */
@Embeddable
public class SpInventoryLedgerPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "DEALER_CODE")
    private String dealerCode;
    @Basic(optional = false)
    @Column(name = "PART_NO")
    private String partNo;
    @Basic(optional = false)
    @Column(name = "TRANSACTION_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;

    public SpInventoryLedgerPK() {
    }

    public SpInventoryLedgerPK(String dealerCode, String partNo, Date transactionDate) {
        this.dealerCode = dealerCode;
        this.partNo = partNo;
        this.transactionDate = transactionDate;
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

    public Date getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dealerCode != null ? dealerCode.hashCode() : 0);
        hash += (partNo != null ? partNo.hashCode() : 0);
        hash += (transactionDate != null ? transactionDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpInventoryLedgerPK)) {
            return false;
        }
        SpInventoryLedgerPK other = (SpInventoryLedgerPK) object;
        if ((this.dealerCode == null && other.dealerCode != null) || (this.dealerCode != null && !this.dealerCode.equals(other.dealerCode))) {
            return false;
        }
        if ((this.partNo == null && other.partNo != null) || (this.partNo != null && !this.partNo.equals(other.partNo))) {
            return false;
        }
        if ((this.transactionDate == null && other.transactionDate != null) || (this.transactionDate != null && !this.transactionDate.equals(other.transactionDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpInventoryLedgerPK[dealerCode=" + dealerCode + ", partNo=" + partNo + ", transactionDate=" + transactionDate + "]";
    }

}
