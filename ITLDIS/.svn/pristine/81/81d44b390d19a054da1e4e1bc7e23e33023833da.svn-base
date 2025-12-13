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
 * @author kundan.rastogi
 */
@Entity
@Table(name = "SP_INVENT_REORDER_LEVEL")
@NamedQueries({
    @NamedQuery(name = "SpInventReorderLevel.findAll", query = "SELECT s FROM SpInventReorderLevel s"),
    @NamedQuery(name = "SpInventReorderLevel.findByDealerCode", query = "SELECT s FROM SpInventReorderLevel s WHERE s.spInventReorderLevelPK.dealerCode = :dealerCode"),
    @NamedQuery(name = "SpInventReorderLevel.findByPartNo", query = "SELECT s FROM SpInventReorderLevel s WHERE s.spInventReorderLevelPK.partNo = :partNo"),
    @NamedQuery(name = "SpInventReorderLevel.findByMinLevel", query = "SELECT s FROM SpInventReorderLevel s WHERE s.minLevel = :minLevel"),
    @NamedQuery(name = "SpInventReorderLevel.findByMaxLevel", query = "SELECT s FROM SpInventReorderLevel s WHERE s.maxLevel = :maxLevel"),
    @NamedQuery(name = "SpInventReorderLevel.findByReorderLevel", query = "SELECT s FROM SpInventReorderLevel s WHERE s.reorderLevel = :reorderLevel"),
    @NamedQuery(name = "SpInventReorderLevel.findByCreatedBy", query = "SELECT s FROM SpInventReorderLevel s WHERE s.createdBy = :createdBy"),
    @NamedQuery(name = "SpInventReorderLevel.findByCreatedOn", query = "SELECT s FROM SpInventReorderLevel s WHERE s.createdOn = :createdOn"),
    @NamedQuery(name = "SpInventReorderLevel.findByLastModifiedBy", query = "SELECT s FROM SpInventReorderLevel s WHERE s.lastModifiedBy = :lastModifiedBy"),
    @NamedQuery(name = "SpInventReorderLevel.findByLastModifiedOn", query = "SELECT s FROM SpInventReorderLevel s WHERE s.lastModifiedOn = :lastModifiedOn")})
public class SpInventReorderLevel implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SpInventReorderLevelPK spInventReorderLevelPK;
    @Basic(optional = false)
    @Column(name = "MIN_LEVEL")
    private int minLevel;
    @Basic(optional = false)
    @Column(name = "MAX_LEVEL")
    private int maxLevel;
    @Basic(optional = false)
    @Column(name = "REORDER_LEVEL")
    private int reorderLevel;
    @Basic(optional = false)
    @Column(name = "CREATED_BY")
    private String createdBy;
    @Basic(optional = false)
    @Column(name = "CREATED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name = "LAST_MODIFIED_BY")
    private String lastModifiedBy;
    @Column(name = "LAST_MODIFIED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedOn;

    public SpInventReorderLevel() {
    }

    public SpInventReorderLevel(SpInventReorderLevelPK spInventReorderLevelPK) {
        this.spInventReorderLevelPK = spInventReorderLevelPK;
    }

    public SpInventReorderLevel(SpInventReorderLevelPK spInventReorderLevelPK, int minLevel, int maxLevel, int reorderLevel, String createdBy, Date createdOn) {
        this.spInventReorderLevelPK = spInventReorderLevelPK;
        this.minLevel = minLevel;
        this.maxLevel = maxLevel;
        this.reorderLevel = reorderLevel;
        this.createdBy = createdBy;
        this.createdOn = createdOn;
    }

    public SpInventReorderLevel(String dealerCode, String partNo) {
        this.spInventReorderLevelPK = new SpInventReorderLevelPK(dealerCode, partNo);
    }

    public SpInventReorderLevelPK getSpInventReorderLevelPK() {
        return spInventReorderLevelPK;
    }

    public void setSpInventReorderLevelPK(SpInventReorderLevelPK spInventReorderLevelPK) {
        this.spInventReorderLevelPK = spInventReorderLevelPK;
    }

    public int getMinLevel() {
        return minLevel;
    }

    public void setMinLevel(int minLevel) {
        this.minLevel = minLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getReorderLevel() {
        return reorderLevel;
    }

    public void setReorderLevel(int reorderLevel) {
        this.reorderLevel = reorderLevel;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

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
        hash += (spInventReorderLevelPK != null ? spInventReorderLevelPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpInventReorderLevel)) {
            return false;
        }
        SpInventReorderLevel other = (SpInventReorderLevel) object;
        if ((this.spInventReorderLevelPK == null && other.spInventReorderLevelPK != null) || (this.spInventReorderLevelPK != null && !this.spInventReorderLevelPK.equals(other.spInventReorderLevelPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpInventReorderLevel[spInventReorderLevelPK=" + spInventReorderLevelPK + "]";
    }

}
