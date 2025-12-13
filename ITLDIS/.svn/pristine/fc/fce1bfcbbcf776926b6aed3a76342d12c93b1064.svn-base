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
@Table(name = "GEN_hes_constants")
@NamedQueries({
    @NamedQuery(name = "HesConstants.findAll", query = "SELECT h FROM HesConstants h"),
    @NamedQuery(name = "HesConstants.findByElementId", query = "SELECT h FROM HesConstants h WHERE h.elementId = :elementId"),
    @NamedQuery(name = "HesConstants.findByElementDesc", query = "SELECT h FROM HesConstants h WHERE h.elementDesc = :elementDesc"),
    @NamedQuery(name = "HesConstants.findByElementValue", query = "SELECT h FROM HesConstants h WHERE h.elementValue = :elementValue")})
public class HesConstants implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ELEMENT_ID")
    private Integer elementId;
    @Basic(optional = false)
    @Column(name = "ELEMENT_DESC")
    private String elementDesc;
    @Basic(optional = false)
    @Column(name = "ELEMENT_VALUE")
    private String elementValue;

    public HesConstants() {
    }

    public HesConstants(Integer elementId) {
        this.elementId = elementId;
    }

    public HesConstants(Integer elementId, String elementDesc, String elementValue) {
        this.elementId = elementId;
        this.elementDesc = elementDesc;
        this.elementValue = elementValue;
    }

    public Integer getElementId() {
        return elementId;
    }

    public void setElementId(Integer elementId) {
        this.elementId = elementId;
    }

    public String getElementDesc() {
        return elementDesc;
    }

    public void setElementDesc(String elementDesc) {
        this.elementDesc = elementDesc;
    }

    public String getElementValue() {
        return elementValue;
    }

    public void setElementValue(String elementValue) {
        this.elementValue = elementValue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (elementId != null ? elementId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof HesConstants)) {
            return false;
        }
        HesConstants other = (HesConstants) object;
        if ((this.elementId == null && other.elementId != null) || (this.elementId != null && !this.elementId.equals(other.elementId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.HesConstants[elementId=" + elementId + "]";
    }

}
