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
 * @author vijay.mishra
 */
@Entity
@Table(name = "MSW_ins_checklist_master")
@NamedQueries({
    @NamedQuery(name = "InsChecklistMaster.findAll", query = "SELECT i FROM InsChecklistMaster i"),
    @NamedQuery(name = "InsChecklistMaster.findByContentid", query = "SELECT i FROM InsChecklistMaster i WHERE i.contentid = :contentid"),
    @NamedQuery(name = "InsChecklistMaster.findByContentdesc", query = "SELECT i FROM InsChecklistMaster i WHERE i.contentdesc = :contentdesc")})
public class InsChecklistMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Content_id")
    private Integer contentid;
    @Column(name = "Content_desc")
    private String contentdesc;

    public InsChecklistMaster() {
    }

    public InsChecklistMaster(Integer contentid) {
        this.contentid = contentid;
    }

    public Integer getContentid() {
        return contentid;
    }

    public void setContentid(Integer contentid) {
        this.contentid = contentid;
    }

    public String getContentdesc() {
        return contentdesc;
    }

    public void setContentdesc(String contentdesc) {
        this.contentdesc = contentdesc;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (contentid != null ? contentid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof InsChecklistMaster)) {
            return false;
        }
        InsChecklistMaster other = (InsChecklistMaster) object;
        if ((this.contentid == null && other.contentid != null) || (this.contentid != null && !this.contentid.equals(other.contentid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.InsChecklistMaster[contentid=" + contentid + "]";
    }

}
