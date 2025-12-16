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
@Table(name = "SW_ins_checklist")
@NamedQueries({
    @NamedQuery(name = "InsChecklist.findAll", query = "SELECT i FROM InsChecklist i"),
    @NamedQuery(name = "InsChecklist.findByINSNo", query = "SELECT i FROM InsChecklist i WHERE i.insChecklistPK.iNSNo = :iNSNo"),
    @NamedQuery(name = "InsChecklist.findByContentid", query = "SELECT i FROM InsChecklist i WHERE i.insChecklistPK.contentid = :contentid"),
    @NamedQuery(name = "InsChecklist.findByCheckedStatus", query = "SELECT i FROM InsChecklist i WHERE i.checkedStatus = :checkedStatus")})
public class InsChecklist implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected InsChecklistPK insChecklistPK;
    @Column(name = "CheckedStatus")
    private String checkedStatus;

    public InsChecklist() {
    }

    public InsChecklist(InsChecklistPK insChecklistPK) {
        this.insChecklistPK = insChecklistPK;
    }

    public InsChecklist(String iNSNo, int contentid) {
        this.insChecklistPK = new InsChecklistPK(iNSNo, contentid);
    }

    public InsChecklistPK getInsChecklistPK() {
        return insChecklistPK;
    }

    public void setInsChecklistPK(InsChecklistPK insChecklistPK) {
        this.insChecklistPK = insChecklistPK;
    }

    public String getCheckedStatus() {
        return checkedStatus;
    }

    public void setCheckedStatus(String checkedStatus) {
        this.checkedStatus = checkedStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (insChecklistPK != null ? insChecklistPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsChecklist)) {
            return false;
        }
        InsChecklist other = (InsChecklist) object;
        if ((this.insChecklistPK == null && other.insChecklistPK != null) || (this.insChecklistPK != null && !this.insChecklistPK.equals(other.insChecklistPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.InsChecklist[insChecklistPK=" + insChecklistPK + "]";
    }

}
