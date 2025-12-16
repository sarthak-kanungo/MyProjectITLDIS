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
public class InsChecklistPK implements Serializable {
    @Basic(optional = false)
    @Column(name = "INS_No")
    private String iNSNo;
    @Basic(optional = false)
    @Column(name = "Content_id")
    private int contentid;

    public InsChecklistPK() {
    }

    public InsChecklistPK(String iNSNo, int contentid) {
        this.iNSNo = iNSNo;
        this.contentid = contentid;
    }

    public String getINSNo() {
        return iNSNo;
    }

    public void setINSNo(String iNSNo) {
        this.iNSNo = iNSNo;
    }

    public int getContentid() {
        return contentid;
    }

    public void setContentid(int contentid) {
        this.contentid = contentid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (iNSNo != null ? iNSNo.hashCode() : 0);
        hash += (int) contentid;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsChecklistPK)) {
            return false;
        }
        InsChecklistPK other = (InsChecklistPK) object;
        if ((this.iNSNo == null && other.iNSNo != null) || (this.iNSNo != null && !this.iNSNo.equals(other.iNSNo))) {
            return false;
        }
        if (this.contentid != other.contentid) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.InsChecklistPK[iNSNo=" + iNSNo + ", contentid=" + contentid + "]";
    }

}
