/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author megha.pandya
 */
@Entity
@Table(name = "MSW_ServiceSchedule")
@NamedQueries({
    @NamedQuery(name = "MSWServiceSchedule.findAll", query = "SELECT m FROM MSWServiceSchedule m"),
    @NamedQuery(name = "MSWServiceSchedule.findByJobTypeId", query = "SELECT m FROM MSWServiceSchedule m WHERE m.jobTypeId = :jobTypeId"),
    @NamedQuery(name = "MSWServiceSchedule.findByDueDays", query = "SELECT m FROM MSWServiceSchedule m WHERE m.dueDays = :dueDays")})
public class MSWServiceSchedule implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "JobTypeId")
    private Integer jobTypeId;
    @Basic(optional = false)
    @Column(name = "DueDays")
    private int dueDays;

    public MSWServiceSchedule() {
    }

    public MSWServiceSchedule(Integer jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    public MSWServiceSchedule(Integer jobTypeId, int dueDays) {
        this.jobTypeId = jobTypeId;
        this.dueDays = dueDays;
    }

    public Integer getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(Integer jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    public int getDueDays() {
        return dueDays;
    }

    public void setDueDays(int dueDays) {
        this.dueDays = dueDays;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobTypeId != null ? jobTypeId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MSWServiceSchedule)) {
            return false;
        }
        MSWServiceSchedule other = (MSWServiceSchedule) object;
        if ((this.jobTypeId == null && other.jobTypeId != null) || (this.jobTypeId != null && !this.jobTypeId.equals(other.jobTypeId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.MSWServiceSchedule[jobTypeId=" + jobTypeId + "]";
    }

}
