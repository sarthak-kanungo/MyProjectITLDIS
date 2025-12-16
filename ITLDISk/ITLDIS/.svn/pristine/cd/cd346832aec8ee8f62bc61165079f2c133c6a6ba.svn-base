/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
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
@Table(name = "SP_INVENTORY_LOCK")
@NamedQueries({
    @NamedQuery(name = "SpInventoryLock.findAll", query = "SELECT s FROM SpInventoryLock s"),
    @NamedQuery(name = "SpInventoryLock.findByDealerCode", query = "SELECT s FROM SpInventoryLock s WHERE s.dealerCode = :dealerCode"),
    @NamedQuery(name = "SpInventoryLock.findByLockDate", query = "SELECT s FROM SpInventoryLock s WHERE s.lockDate = :lockDate"),
    @NamedQuery(name = "SpInventoryLock.findByLockedBy", query = "SELECT s FROM SpInventoryLock s WHERE s.lockedBy = :lockedBy")})
public class SpInventoryLock implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "DEALER_CODE")
    private String dealerCode;
    @Basic(optional = false)
    @Column(name = "LOCK_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lockDate;
    @Basic(optional = false)
    @Column(name = "LOCKED_BY")
    private String lockedBy;

    public SpInventoryLock() {
    }

    public SpInventoryLock(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public SpInventoryLock(String dealerCode, Date lockDate, String lockedBy) {
        this.dealerCode = dealerCode;
        this.lockDate = lockDate;
        this.lockedBy = lockedBy;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public Date getLockDate() {
        return lockDate;
    }

    public void setLockDate(Date lockDate) {
        this.lockDate = lockDate;
    }

    public String getLockedBy() {
        return lockedBy;
    }

    public void setLockedBy(String lockedBy) {
        this.lockedBy = lockedBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dealerCode != null ? dealerCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpInventoryLock)) {
            return false;
        }
        SpInventoryLock other = (SpInventoryLock) object;
        if ((this.dealerCode == null && other.dealerCode != null) || (this.dealerCode != null && !this.dealerCode.equals(other.dealerCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpInventoryLock[dealerCode=" + dealerCode + "]";
    }

}
