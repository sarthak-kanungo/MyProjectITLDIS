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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "MSW_ownerdrivenmaster")
public class Ownerdrivenmaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "OwnerDrivenID")
    private Long ownerDrivenID;
    @Basic(optional = false)
    @Column(name = "OwnerDrivenDesc")
    private String ownerDrivenDesc;
    @Basic(optional = false)
    @Column(name = "isActive")
    private char isActive;
    @Basic(optional = false)
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "SeqNo")
    private Integer seqNo;

    public Ownerdrivenmaster() {
    }

    public Ownerdrivenmaster(Long ownerDrivenID) {
        this.ownerDrivenID = ownerDrivenID;
    }

    public Ownerdrivenmaster(Long ownerDrivenID, String ownerDrivenDesc, char isActive, Date lastUpdatedDate) {
        this.ownerDrivenID = ownerDrivenID;
        this.ownerDrivenDesc = ownerDrivenDesc;
        this.isActive = isActive;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Long getOwnerDrivenID() {
        return ownerDrivenID;
    }

    public void setOwnerDrivenID(Long ownerDrivenID) {
        this.ownerDrivenID = ownerDrivenID;
    }

    public String getOwnerDrivenDesc() {
        return ownerDrivenDesc;
    }

    public void setOwnerDrivenDesc(String ownerDrivenDesc) {
        this.ownerDrivenDesc = ownerDrivenDesc;
    }

    public char getIsActive() {
        return isActive;
    }

    public void setIsActive(char isActive) {
        this.isActive = isActive;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ownerDrivenID != null ? ownerDrivenID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ownerdrivenmaster)) {
            return false;
        }
        Ownerdrivenmaster other = (Ownerdrivenmaster) object;
        if ((this.ownerDrivenID == null && other.ownerDrivenID != null) || (this.ownerDrivenID != null && !this.ownerDrivenID.equals(other.ownerDrivenID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Ownerdrivenmaster[ownerDrivenID=" + ownerDrivenID + "]";
    }
}
