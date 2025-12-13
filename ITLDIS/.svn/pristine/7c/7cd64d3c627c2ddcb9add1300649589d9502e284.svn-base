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
@Table(name = "GEN_states")
@NamedQueries({
    @NamedQuery(name = "GENstates.findAll", query = "SELECT g FROM GENstates g"),
    @NamedQuery(name = "GENstates.findByStateID", query = "SELECT g FROM GENstates g WHERE g.stateID = :stateID"),
    @NamedQuery(name = "GENstates.findByStateName", query = "SELECT g FROM GENstates g WHERE g.stateName = :stateName")})
public class GENstates implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "StateID")
    private Integer stateID;
    @Basic(optional = false)
    @Column(name = "STATE_NAME")
    private String stateName;

    public GENstates() {
    }

    public GENstates(Integer stateID) {
        this.stateID = stateID;
    }

    public GENstates(Integer stateID, String stateName) {
        this.stateID = stateID;
        this.stateName = stateName;
    }

    public Integer getStateID() {
        return stateID;
    }

    public void setStateID(Integer stateID) {
        this.stateID = stateID;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (stateID != null ? stateID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof GENstates)) {
            return false;
        }
        GENstates other = (GENstates) object;
        if ((this.stateID == null && other.stateID != null) || (this.stateID != null && !this.stateID.equals(other.stateID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.GENstates[stateID=" + stateID + "]";
    }

}
