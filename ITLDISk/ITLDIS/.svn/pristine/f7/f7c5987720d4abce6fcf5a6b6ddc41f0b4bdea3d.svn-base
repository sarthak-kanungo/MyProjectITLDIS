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
 * @author kundan.rastogi
 */
@Embeddable
public class MSWwarrantytaxStatePercentagePK implements Serializable {
    @Basic(optional = false)
    @Column(name = "TypeID")
    private int typeID;
    @Basic(optional = false)
    @Column(name = "StateID")
    private int stateID;

    public MSWwarrantytaxStatePercentagePK() {
    }

    public MSWwarrantytaxStatePercentagePK(int typeID, int stateID) {
        this.typeID = typeID;
        this.stateID = stateID;
    }

    public int getTypeID() {
        return typeID;
    }

    public void setTypeID(int typeID) {
        this.typeID = typeID;
    }

    public int getStateID() {
        return stateID;
    }

    public void setStateID(int stateID) {
        this.stateID = stateID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) typeID;
        hash += (int) stateID;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof MSWwarrantytaxStatePercentagePK)) {
            return false;
        }
        MSWwarrantytaxStatePercentagePK other = (MSWwarrantytaxStatePercentagePK) object;
        if (this.typeID != other.typeID) {
            return false;
        }
        if (this.stateID != other.stateID) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.MSWwarrantytaxStatePercentagePK[typeID=" + typeID + ", stateID=" + stateID + "]";
    }

}
