/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author vijay.mishra
 */
@Embeddable
public class JobchecklistPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "JobCardNo")
    private String jobCardNo;
    @Basic(optional = false)
    @Column(name = "contentId")
    private int contentId;
    @Basic(optional = false)
    @Column(name = "subcontentId")
    private int subcontentId;

    public JobchecklistPK() {
    }

    public JobchecklistPK(String jobCardNo, int contentId, int subcontentId) {
        this.jobCardNo = jobCardNo;
        this.contentId = contentId;
        this.subcontentId = subcontentId;
    }

    public String getJobCardNo() {
        return jobCardNo;
    }

    public void setJobCardNo(String jobCardNo) {
        this.jobCardNo = jobCardNo;
    }

    public int getContentId() {
        return contentId;
    }

    public void setContentId(int contentId) {
        this.contentId = contentId;
    }

    public int getSubcontentId() {
        return subcontentId;
    }

    public void setSubcontentId(int subcontentId) {
        this.subcontentId = subcontentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobCardNo != null ? jobCardNo.hashCode() : 0);
        hash += (int) contentId;
        hash += (int) subcontentId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof JobchecklistPK)) {
            return false;
        }
        JobchecklistPK other = (JobchecklistPK) object;
        if ((this.jobCardNo == null && other.jobCardNo != null) || (this.jobCardNo != null && !this.jobCardNo.equals(other.jobCardNo))) {
            return false;
        }
        if (this.contentId != other.contentId) {
            return false;
        }
        if (this.subcontentId != other.subcontentId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.JobchecklistPK[jobCardNo=" + jobCardNo + ", contentId=" + contentId + ", subcontentId=" + subcontentId + "]";
    }

}
