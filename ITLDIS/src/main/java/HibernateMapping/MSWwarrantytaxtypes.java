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
 * @author kundan.rastogi
 */
@Entity
@Table(name = "MSW_warranty_taxtypes")
@NamedQueries({
    @NamedQuery(name = "MSWwarrantytaxtypes.findAll", query = "SELECT m FROM MSWwarrantytaxtypes m")})
public class MSWwarrantytaxtypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "TypeID")
    private Integer typeID;
    @Basic(optional = false)
    @Column(name = "TypeDescription")
    private String typeDescription;
    @Column(name = "SeqNo")
    private Integer seqNo;
    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;
    @Column(name = "LastUpdatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedOn;

    public MSWwarrantytaxtypes() {
    }

    public MSWwarrantytaxtypes(Integer typeID) {
        this.typeID = typeID;
    }

    public MSWwarrantytaxtypes(Integer typeID, String typeDescription) {
        this.typeID = typeID;
        this.typeDescription = typeDescription;
    }

    public Integer getTypeID() {
        return typeID;
    }

    public void setTypeID(Integer typeID) {
        this.typeID = typeID;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
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
        hash += (typeID != null ? typeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MSWwarrantytaxtypes)) {
            return false;
        }
        MSWwarrantytaxtypes other = (MSWwarrantytaxtypes) object;
        if ((this.typeID == null && other.typeID != null) || (this.typeID != null && !this.typeID.equals(other.typeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.MSWwarrantytaxtypes[typeID=" + typeID + "]";
    }

}
