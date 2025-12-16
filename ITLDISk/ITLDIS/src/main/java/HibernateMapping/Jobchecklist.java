/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Column;
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
@Table(name = "SW_jobchecklist")
@NamedQueries({
    @NamedQuery(name = "Jobchecklist.findAll", query = "SELECT j FROM Jobchecklist j"),
    @NamedQuery(name = "Jobchecklist.findByJobCardNo", query = "SELECT j FROM Jobchecklist j WHERE j.jobchecklistPK.jobCardNo = :jobCardNo"),
    @NamedQuery(name = "Jobchecklist.findByContentId", query = "SELECT j FROM Jobchecklist j WHERE j.jobchecklistPK.contentId = :contentId"),
    @NamedQuery(name = "Jobchecklist.findBySubcontentId", query = "SELECT j FROM Jobchecklist j WHERE j.jobchecklistPK.subcontentId = :subcontentId"),
    @NamedQuery(name = "Jobchecklist.findByOkStatus", query = "SELECT j FROM Jobchecklist j WHERE j.okStatus = :okStatus"),
    @NamedQuery(name = "Jobchecklist.findByActionTaken", query = "SELECT j FROM Jobchecklist j WHERE j.actionTaken = :actionTaken"),
    @NamedQuery(name = "Jobchecklist.findByRemarks", query = "SELECT j FROM Jobchecklist j WHERE j.remarks = :remarks"),
    @NamedQuery(name = "Jobchecklist.findByFinalStatus", query = "SELECT j FROM Jobchecklist j WHERE j.finalStatus = :finalStatus")})
public class Jobchecklist implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected JobchecklistPK jobchecklistPK;
    @Column(name = "okStatus")
    private String okStatus;
    @Column(name = "actionTaken")
    private String actionTaken;
    @Column(name = "remarks")
    private String remarks;
    @Column(name = "finalStatus")
    private String finalStatus;

    public Jobchecklist() {
    }

    public Jobchecklist(JobchecklistPK jobchecklistPK) {
        this.jobchecklistPK = jobchecklistPK;
    }

    public Jobchecklist(String jobCardNo, int contentId, int subcontentId) {
        this.jobchecklistPK = new JobchecklistPK(jobCardNo, contentId, subcontentId);
    }

    public JobchecklistPK getJobchecklistPK() {
        return jobchecklistPK;
    }

    public void setJobchecklistPK(JobchecklistPK jobchecklistPK) {
        this.jobchecklistPK = jobchecklistPK;
    }

    public String getOkStatus() {
        return okStatus;
    }

    public void setOkStatus(String okStatus) {
        this.okStatus = okStatus;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getFinalStatus() {
        return finalStatus;
    }

    public void setFinalStatus(String finalStatus) {
        this.finalStatus = finalStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobchecklistPK != null ? jobchecklistPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Jobchecklist)) {
            return false;
        }
        Jobchecklist other = (Jobchecklist) object;
        if ((this.jobchecklistPK == null && other.jobchecklistPK != null) || (this.jobchecklistPK != null && !this.jobchecklistPK.equals(other.jobchecklistPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Jobchecklist[jobchecklistPK=" + jobchecklistPK + "]";
    }

}
