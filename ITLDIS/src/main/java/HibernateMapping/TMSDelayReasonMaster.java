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
 * @author kundan.rastogi
 */
@Entity
@Table(name = "TMS_DelayReasonMaster")
@NamedQueries({
    @NamedQuery(name = "TMSDelayReasonMaster.findAll", query = "SELECT t FROM TMSDelayReasonMaster t"),
    @NamedQuery(name = "TMSDelayReasonMaster.findById", query = "SELECT t FROM TMSDelayReasonMaster t WHERE t.id = :id"),
    @NamedQuery(name = "TMSDelayReasonMaster.findByDelayReason", query = "SELECT t FROM TMSDelayReasonMaster t WHERE t.delayReason = :delayReason"),
    @NamedQuery(name = "TMSDelayReasonMaster.findByStatus", query = "SELECT t FROM TMSDelayReasonMaster t WHERE t.status = :status")})
public class TMSDelayReasonMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ID")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "DelayReason")
    private String delayReason;
    @Basic(optional = false)
    @Column(name = "Status")
    private String status;

    public TMSDelayReasonMaster() {
    }

    public TMSDelayReasonMaster(Integer id) {
        this.id = id;
    }

    public TMSDelayReasonMaster(Integer id, String delayReason, String status) {
        this.id = id;
        this.delayReason = delayReason;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDelayReason() {
        return delayReason;
    }

    public void setDelayReason(String delayReason) {
        this.delayReason = delayReason;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TMSDelayReasonMaster)) {
            return false;
        }
        TMSDelayReasonMaster other = (TMSDelayReasonMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.TMSDelayReasonMaster[id=" + id + "]";
    }

}
