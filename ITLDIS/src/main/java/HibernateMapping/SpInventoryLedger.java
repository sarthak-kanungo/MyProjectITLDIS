/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "SP_INVENTORY_LEDGER")
@NamedQueries({
    @NamedQuery(name = "SpInventoryLedger.findAll", query = "SELECT s FROM SpInventoryLedger s"),
//    @NamedQuery(name = "SpInventoryLedger.findByIlId", query = "SELECT s FROM SpInventoryLedger s WHERE s.ilId = :ilId"),
    @NamedQuery(name = "SpInventoryLedger.findByDealerCode", query = "SELECT s FROM SpInventoryLedger s WHERE s.spInventoryLedgerPK.dealerCode = :dealerCode"),
    @NamedQuery(name = "SpInventoryLedger.findByPartNo", query = "SELECT s FROM SpInventoryLedger s WHERE s.spInventoryLedgerPK.partNo = :partNo"),
    @NamedQuery(name = "SpInventoryLedger.findByTransactionDate", query = "SELECT s FROM SpInventoryLedger s WHERE s.spInventoryLedgerPK.transactionDate = :transactionDate"),
    @NamedQuery(name = "SpInventoryLedger.findByTransactionDescription", query = "SELECT s FROM SpInventoryLedger s WHERE s.transactionDescription = :transactionDescription"),
//    @NamedQuery(name = "SpInventoryLedger.findByOpeningStk", query = "SELECT s FROM SpInventoryLedger s WHERE s.openingStk = :openingStk"),
    @NamedQuery(name = "SpInventoryLedger.findByInward", query = "SELECT s FROM SpInventoryLedger s WHERE s.inward = :inward"),
    @NamedQuery(name = "SpInventoryLedger.findByOutward", query = "SELECT s FROM SpInventoryLedger s WHERE s.outward = :outward"),
//    @NamedQuery(name = "SpInventoryLedger.findByClosingStk", query = "SELECT s FROM SpInventoryLedger s WHERE s.closingStk = :closingStk"),
    @NamedQuery(name = "SpInventoryLedger.findByLastModifiedBy", query = "SELECT s FROM SpInventoryLedger s WHERE s.lastModifiedBy = :lastModifiedBy"),
    @NamedQuery(name = "SpInventoryLedger.findByLastModifiedOn", query = "SELECT s FROM SpInventoryLedger s WHERE s.lastModifiedOn = :lastModifiedOn")})
public class SpInventoryLedger implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SpInventoryLedgerPK spInventoryLedgerPK;
//    @Basic(optional = false)
//     @Generated(GenerationTime.INSERT)
//    @GeneratedValue(strategy=GenerationType.IDENTITY)
//    @Column(name = "IL_ID")
//    private int ilId;
    @Column(name = "TRANSACTION_DESCRIPTION")
    private String transactionDescription;
//    @Basic(optional = false)
//    @Column(name = "OPENING_STK")
//    private int openingStk;
    @Basic(optional = false)
    @Column(name = "INWARD")
    private double inward;
    @Basic(optional = false)
    @Column(name = "OUTWARD")
    private double outward;
//    @Basic(optional = false)
//    @Column(name = "CLOSING_STK")
//    private int closingStk;
    @Basic(optional = false)
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;
    @Basic(optional = false)
    @Column(name = "LAST_MODIFIED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedOn;

    public SpInventoryLedger() {
    }

    public SpInventoryLedger(SpInventoryLedgerPK spInventoryLedgerPK) {
        this.spInventoryLedgerPK = spInventoryLedgerPK;
    }

    public SpInventoryLedger(SpInventoryLedgerPK spInventoryLedgerPK, int ilId, int openingStk, int inward, int outward, int closingStk, String lastModifiedBy, Date lastModifiedOn) {
        this.spInventoryLedgerPK = spInventoryLedgerPK;
//        this.ilId = ilId;
//        this.openingStk = openingStk;
        this.inward = inward;
        this.outward = outward;
//        this.closingStk = closingStk;
        this.lastModifiedBy = lastModifiedBy;
        this.lastModifiedOn = lastModifiedOn;
    }

    public SpInventoryLedger(String dealerCode, String partNo, Date transactionDate) {
        this.spInventoryLedgerPK = new SpInventoryLedgerPK(dealerCode, partNo, transactionDate);
    }

    public SpInventoryLedgerPK getSpInventoryLedgerPK() {
        return spInventoryLedgerPK;
    }

    public void setSpInventoryLedgerPK(SpInventoryLedgerPK spInventoryLedgerPK) {
        this.spInventoryLedgerPK = spInventoryLedgerPK;
    }

//    public int getIlId() {
//        return ilId;
//    }
//
//    public void setIlId(int ilId) {
//        this.ilId = ilId;
//    }

    public String getTransactionDescription() {
        return transactionDescription;
    }

    public void setTransactionDescription(String transactionDescription) {
        this.transactionDescription = transactionDescription;
    }

//    public int getOpeningStk() {
//        return openingStk;
//    }
//
//    public void setOpeningStk(int openingStk) {
//        this.openingStk = openingStk;
//    }

    public double getInward() {
        return inward;
    }

    public void setInward(double inward) {
        this.inward = inward;
    }

    public double getOutward() {
        return outward;
    }

    public void setOutward(double outward) {
        this.outward = outward;
    }

//    public int getClosingStk() {
//        return closingStk;
//    }
//
//    public void setClosingStk(int closingStk) {
//        this.closingStk = closingStk;
//    }

    public String getLastModifiedBy() {
        return lastModifiedBy;
    }

    public void setLastModifiedBy(String lastModifiedBy) {
        this.lastModifiedBy = lastModifiedBy;
    }

    public Date getLastModifiedOn() {
        return lastModifiedOn;
    }

    public void setLastModifiedOn(Date lastModifiedOn) {
        this.lastModifiedOn = lastModifiedOn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spInventoryLedgerPK != null ? spInventoryLedgerPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpInventoryLedger)) {
            return false;
        }
        SpInventoryLedger other = (SpInventoryLedger) object;
        if ((this.spInventoryLedgerPK == null && other.spInventoryLedgerPK != null) || (this.spInventoryLedgerPK != null && !this.spInventoryLedgerPK.equals(other.spInventoryLedgerPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpInventoryLedger[spInventoryLedgerPK=" + spInventoryLedgerPK + "]";
    }

}
