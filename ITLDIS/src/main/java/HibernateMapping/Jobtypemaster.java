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
@Table(name = "MSW_jobtypemaster")

public class Jobtypemaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "JobTypeID")
    private Integer jobTypeID;
    @Basic(optional = false)
    @Column(name = "JobTypeDesc")
    private String jobTypeDesc;
    @Basic(optional = false)
    @Column(name = "isActive")
    private char isActive;
    @Basic(optional = false)
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "SeqNo")
    private Integer seqNo;
  @Column(name = "FreeService")
    private char freeService;

    public Jobtypemaster() {
    }

    public Jobtypemaster(Integer jobTypeID) {
        this.jobTypeID = jobTypeID;
    }

    public Jobtypemaster(Integer jobTypeID, String jobTypeDesc, char isActive, Date lastUpdatedDate) {
        this.jobTypeID = jobTypeID;
        this.jobTypeDesc = jobTypeDesc;
        this.isActive = isActive;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Integer getJobTypeID() {
        return jobTypeID;
    }

    public void setJobTypeID(Integer jobTypeID) {
        this.jobTypeID = jobTypeID;
    }

    public String getJobTypeDesc() {
        return jobTypeDesc;
    }

    public void setJobTypeDesc(String jobTypeDesc) {
        this.jobTypeDesc = jobTypeDesc;
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

    public char getFreeService() {
        return freeService;
    }

    public void setFreeService(char freeService) {
        this.freeService = freeService;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobTypeID != null ? jobTypeID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jobtypemaster)) {
            return false;
        }
        Jobtypemaster other = (Jobtypemaster) object;
        if ((this.jobTypeID == null && other.jobTypeID != null) || (this.jobTypeID != null && !this.jobTypeID.equals(other.jobTypeID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Jobtypemaster[jobTypeID=" + jobTypeID + "]";
    }

}
