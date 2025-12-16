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

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "MSW_otherjobworkmaster")
@NamedQueries({
    @NamedQuery(name = "Otherjobworkmaster.findAll", query = "SELECT o FROM Otherjobworkmaster o"),
    @NamedQuery(name = "Otherjobworkmaster.findByWorkID", query = "SELECT o FROM Otherjobworkmaster o WHERE o.workID = :workID"),
    @NamedQuery(name = "Otherjobworkmaster.findByWorkDesc", query = "SELECT o FROM Otherjobworkmaster o WHERE o.workDesc = :workDesc"),
    @NamedQuery(name = "Otherjobworkmaster.findByIsActive", query = "SELECT o FROM Otherjobworkmaster o WHERE o.isActive = :isActive"),
    @NamedQuery(name = "Otherjobworkmaster.findByLastUpdatedDate", query = "SELECT o FROM Otherjobworkmaster o WHERE o.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "Otherjobworkmaster.findBySeqNo", query = "SELECT o FROM Otherjobworkmaster o WHERE o.seqNo = :seqNo")})
public class Otherjobworkmaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
     @Generated(GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "WorkID")
    private Long workID;
    @Column(name = "WorkDesc")
    private String workDesc;
    @Column(name = "isActive")
    private Character isActive;
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "seqNo")
    private Integer seqNo;

    public Otherjobworkmaster() {
    }

    public Otherjobworkmaster(Long workID) {
        this.workID = workID;
    }

    public Long getWorkID() {
        return workID;
    }

    public void setWorkID(Long workID) {
        this.workID = workID;
    }

    public String getWorkDesc() {
        return workDesc;
    }

    public void setWorkDesc(String workDesc) {
        this.workDesc = workDesc;
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

    public Integer getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(Integer seqNo) {
        this.seqNo = seqNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (workID != null ? workID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Otherjobworkmaster)) {
            return false;
        }
        Otherjobworkmaster other = (Otherjobworkmaster) object;
        if ((this.workID == null && other.workID != null) || (this.workID != null && !this.workID.equals(other.workID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Otherjobworkmaster[workID=" + workID + "]";
    }

}
