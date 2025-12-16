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
@Table(name = "MSW_ins_master_accessories")
@NamedQueries({
    @NamedQuery(name = "Insmasteraccessories.findAll", query = "SELECT i FROM Insmasteraccessories i"),
    @NamedQuery(name = "Insmasteraccessories.findById", query = "SELECT i FROM Insmasteraccessories i WHERE i.id = :id"),
    @NamedQuery(name = "Insmasteraccessories.findByAccessoriesName", query = "SELECT i FROM Insmasteraccessories i WHERE i.accessoriesName = :accessoriesName"),
    @NamedQuery(name = "Insmasteraccessories.findByIsActive", query = "SELECT i FROM Insmasteraccessories i WHERE i.isActive = :isActive")})
public class Insmasteraccessories implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "AccessoriesName")
    private String accessoriesName;
    @Column(name = "isActive")
    private String isActive;

    public Insmasteraccessories() {
    }

    public Insmasteraccessories(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccessoriesName() {
        return accessoriesName;
    }

    public void setAccessoriesName(String accessoriesName) {
        this.accessoriesName = accessoriesName;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
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
        if (!(object instanceof Insmasteraccessories)) {
            return false;
        }
        Insmasteraccessories other = (Insmasteraccessories) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Insmasteraccessories[id=" + id + "]";
    }

}
