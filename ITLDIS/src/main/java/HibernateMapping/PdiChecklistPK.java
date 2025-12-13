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
public class PdiChecklistPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "PDI_No")
    private String pDINo;
    @Basic(optional = false)
    @Column(name = "content_id")
    private long contentId;
    @Basic(optional = false)
    @Column(name = "subContentId")
    private long subContentId;

    public PdiChecklistPK() {
    }

    public PdiChecklistPK(String pDINo, long contentId, long subContentId) {
        this.pDINo = pDINo;
        this.contentId = contentId;
        this.subContentId = subContentId;
    }

    public String getPDINo() {
        return pDINo;
    }

    public void setPDINo(String pDINo) {
        this.pDINo = pDINo;
    }

    public long getContentId() {
        return contentId;
    }

    public void setContentId(long contentId) {
        this.contentId = contentId;
    }

    public long getSubContentId() {
        return subContentId;
    }

    public void setSubContentId(long subContentId) {
        this.subContentId = subContentId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pDINo != null ? pDINo.hashCode() : 0);
        hash += (int) contentId;
        hash += (int) subContentId;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PdiChecklistPK)) {
            return false;
        }
        PdiChecklistPK other = (PdiChecklistPK) object;
        if ((this.pDINo == null && other.pDINo != null) || (this.pDINo != null && !this.pDINo.equals(other.pDINo))) {
            return false;
        }
        if (this.contentId != other.contentId) {
            return false;
        }
        if (this.subContentId != other.subContentId) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.PdiChecklistPK[pDINo=" + pDINo + ", contentId=" + contentId + ", subContentId=" + subContentId + "]";
    }

}
