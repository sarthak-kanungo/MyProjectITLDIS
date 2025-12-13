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
@Table(name = "MSW_warranty_taxStatePercentage")
@NamedQueries({
    @NamedQuery(name = "MSWwarrantytaxStatePercentage.findAll", query = "SELECT m FROM MSWwarrantytaxStatePercentage m")})
public class MSWwarrantytaxStatePercentage implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected MSWwarrantytaxStatePercentagePK mSWwarrantytaxStatePercentagePK;
    @Basic(optional = false)
    @Column(name = "TypePercentage")
    private double typePercentage;
    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;
    @Column(name = "LastUpdatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedOn;

    public MSWwarrantytaxStatePercentage() {
    }

    public MSWwarrantytaxStatePercentage(MSWwarrantytaxStatePercentagePK mSWwarrantytaxStatePercentagePK) {
        this.mSWwarrantytaxStatePercentagePK = mSWwarrantytaxStatePercentagePK;
    }

    public MSWwarrantytaxStatePercentage(MSWwarrantytaxStatePercentagePK mSWwarrantytaxStatePercentagePK, double typePercentage) {
        this.mSWwarrantytaxStatePercentagePK = mSWwarrantytaxStatePercentagePK;
        this.typePercentage = typePercentage;
    }

    public MSWwarrantytaxStatePercentage(int typeID, int stateID) {
        this.mSWwarrantytaxStatePercentagePK = new MSWwarrantytaxStatePercentagePK(typeID, stateID);
    }

    public MSWwarrantytaxStatePercentagePK getMSWwarrantytaxStatePercentagePK() {
        return mSWwarrantytaxStatePercentagePK;
    }

    public void setMSWwarrantytaxStatePercentagePK(MSWwarrantytaxStatePercentagePK mSWwarrantytaxStatePercentagePK) {
        this.mSWwarrantytaxStatePercentagePK = mSWwarrantytaxStatePercentagePK;
    }

    public double getTypePercentage() {
        return typePercentage;
    }

    public void setTypePercentage(double typePercentage) {
        this.typePercentage = typePercentage;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mSWwarrantytaxStatePercentagePK != null ? mSWwarrantytaxStatePercentagePK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MSWwarrantytaxStatePercentage)) {
            return false;
        }
        MSWwarrantytaxStatePercentage other = (MSWwarrantytaxStatePercentage) object;
        if ((this.mSWwarrantytaxStatePercentagePK == null && other.mSWwarrantytaxStatePercentagePK != null) || (this.mSWwarrantytaxStatePercentagePK != null && !this.mSWwarrantytaxStatePercentagePK.equals(other.mSWwarrantytaxStatePercentagePK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.MSWwarrantytaxStatePercentage[mSWwarrantytaxStatePercentagePK=" + mSWwarrantytaxStatePercentagePK + "]";
    }

}
