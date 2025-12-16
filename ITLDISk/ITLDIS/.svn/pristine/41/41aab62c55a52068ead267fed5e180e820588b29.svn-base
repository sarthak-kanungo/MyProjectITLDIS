/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "SW_jobcomplaintconsequences")
@NamedQueries({
    @NamedQuery(name = "Jobcmpconsequences.findAll", query = "SELECT j FROM Jobcmpconsequences j"),
    @NamedQuery(name = "Jobcmpconsequences.findByCmpNo", query = "SELECT j FROM Jobcmpconsequences j WHERE j.jobcmpconsequencesPK.cmpNo = :cmpNo"),
    @NamedQuery(name = "Jobcmpconsequences.findByConsequenceCode", query = "SELECT j FROM Jobcmpconsequences j WHERE j.jobcmpconsequencesPK.consequenceCode = :consequenceCode")})
public class Jobcmpconsequences implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JobcmpconsequencesPK jobcmpconsequencesPK;

    public Jobcmpconsequences() {
    }

    public Jobcmpconsequences(JobcmpconsequencesPK jobcmpconsequencesPK) {
        this.jobcmpconsequencesPK = jobcmpconsequencesPK;
    }

    public Jobcmpconsequences(String cmpNo, String consequenceCode) {
        this.jobcmpconsequencesPK = new JobcmpconsequencesPK(cmpNo, consequenceCode);
    }

    public JobcmpconsequencesPK getJobcmpconsequencesPK() {
        return jobcmpconsequencesPK;
    }

    public void setJobcmpconsequencesPK(JobcmpconsequencesPK jobcmpconsequencesPK) {
        this.jobcmpconsequencesPK = jobcmpconsequencesPK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobcmpconsequencesPK != null ? jobcmpconsequencesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jobcmpconsequences)) {
            return false;
        }
        Jobcmpconsequences other = (Jobcmpconsequences) object;
        if ((this.jobcmpconsequencesPK == null && other.jobcmpconsequencesPK != null) || (this.jobcmpconsequencesPK != null && !this.jobcmpconsequencesPK.equals(other.jobcmpconsequencesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Jobcmpconsequences[jobcmpconsequencesPK=" + jobcmpconsequencesPK + "]";
    }

}
