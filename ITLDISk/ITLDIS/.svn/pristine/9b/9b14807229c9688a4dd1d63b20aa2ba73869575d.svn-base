/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "SP_INVENTORY")
@NamedQueries({
    @NamedQuery(name = "SpInventory.findAll", query = "SELECT s FROM SpInventory s"),
    @NamedQuery(name = "SpInventory.findByDealerCode", query = "SELECT s FROM SpInventory s WHERE s.spInventoryPK.dealerCode = :dealerCode"),
    @NamedQuery(name = "SpInventory.findByPartNo", query = "SELECT s FROM SpInventory s WHERE s.spInventoryPK.partNo = :partNo"),
    @NamedQuery(name = "SpInventory.findByQuantity", query = "SELECT s FROM SpInventory s WHERE s.quantity = :quantity"),
    @NamedQuery(name = "SpInventory.findByCreatedOn", query = "SELECT s FROM SpInventory s WHERE s.createdOn = :createdOn"),
    @NamedQuery(name = "SpInventory.findByCreatedBy", query = "SELECT s FROM SpInventory s WHERE s.createdBy = :createdBy"),
    @NamedQuery(name = "SpInventory.findByModifiedOn", query = "SELECT s FROM SpInventory s WHERE s.modifiedOn = :modifiedOn"),
    @NamedQuery(name = "SpInventory.findByModifiedBy", query = "SELECT s FROM SpInventory s WHERE s.modifiedBy = :modifiedBy")})
public class SpInventory implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SpInventoryPK spInventoryPK;
    @Column(name = "QUANTITY")
    private double quantity;
    @Column(name = "CREATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Column(name = "MODIFIED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date modifiedOn;
    @Column(name = "MODIFIED_BY")
    private String modifiedBy;
    @Column(name = "BIN_LOCATION")
    private String binLocation;

    public SpInventory() {
    }

    public SpInventory(SpInventoryPK spInventoryPK) {
        this.spInventoryPK = spInventoryPK;
    }

    public SpInventory(String dealerCode, String partNo) {
        this.spInventoryPK = new SpInventoryPK(dealerCode, partNo);
    }

    public SpInventoryPK getSpInventoryPK() {
        return spInventoryPK;
    }

    public void setSpInventoryPK(SpInventoryPK spInventoryPK) {
        this.spInventoryPK = spInventoryPK;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getModifiedOn() {
        return modifiedOn;
    }

    public void setModifiedOn(Date modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    public String getModifiedBy() {
        return modifiedBy;
    }

    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
    public String getBinLocation() {
        return binLocation;
    }

    public void setBinLocation(String binLocation) {
        this.binLocation = binLocation;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spInventoryPK != null ? spInventoryPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpInventory)) {
            return false;
        }
        SpInventory other = (SpInventory) object;
        if ((this.spInventoryPK == null && other.spInventoryPK != null) || (this.spInventoryPK != null && !this.spInventoryPK.equals(other.spInventoryPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpInventory[spInventoryPK=" + spInventoryPK + "]";
    }

}
