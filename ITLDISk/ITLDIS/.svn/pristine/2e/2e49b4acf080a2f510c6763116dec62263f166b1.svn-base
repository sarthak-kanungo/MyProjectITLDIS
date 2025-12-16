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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "MSW_billmaster")
@NamedQueries({
    @NamedQuery(name = "Billmaster.findAll", query = "SELECT b FROM Billmaster b"),
    @NamedQuery(name = "Billmaster.findByBillID", query = "SELECT b FROM Billmaster b WHERE b.billID = :billID"),
    @NamedQuery(name = "Billmaster.findByBillDesc", query = "SELECT b FROM Billmaster b WHERE b.billDesc = :billDesc"),
    @NamedQuery(name = "Billmaster.findByIsActive", query = "SELECT b FROM Billmaster b WHERE b.isActive = :isActive"),
    @NamedQuery(name = "Billmaster.findByLastUpdatedDate", query = "SELECT b FROM Billmaster b WHERE b.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "Billmaster.findByDiscount", query = "SELECT b FROM Billmaster b WHERE b.discount = :discount"),
    @NamedQuery(name = "Billmaster.findByWarrantyType", query = "SELECT b FROM Billmaster b WHERE b.warrantyType = :warrantyType")})
public class Billmaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "BillID")
    private Long billID;
    @Column(name = "BillDesc")
    private String billDesc;
    @Column(name = "isActive")
    private Character isActive;
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "Discount")
    private Float discount;
    @Column(name = "warrantyType")
    private String warrantyType;

    public Billmaster() {
    }

    public Billmaster(Long billID) {
        this.billID = billID;
    }

    public Long getBillID() {
        return billID;
    }

    public void setBillID(Long billID) {
        this.billID = billID;
    }

    public String getBillDesc() {
        return billDesc;
    }

    public void setBillDesc(String billDesc) {
        this.billDesc = billDesc;
    }

    public Character getIsActive() {
        return isActive;
    }

    public void setIsActive(Character isActive) {
        this.isActive = isActive;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public String getWarrantyType() {
        return warrantyType;
    }

    public void setWarrantyType(String warrantyType) {
        this.warrantyType = warrantyType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (billID != null ? billID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Billmaster)) {
            return false;
        }
        Billmaster other = (Billmaster) object;
        if ((this.billID == null && other.billID != null) || (this.billID != null && !this.billID.equals(other.billID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Billmaster[billID=" + billID + "]";
    }

}
