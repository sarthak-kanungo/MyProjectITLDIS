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
@Table(name = "MSW_ins_master_majorapplication")
@NamedQueries({
    @NamedQuery(name = "Insmastermajorapplication.findAll", query = "SELECT i FROM Insmastermajorapplication i"),
    @NamedQuery(name = "Insmastermajorapplication.findById", query = "SELECT i FROM Insmastermajorapplication i WHERE i.id = :id"),
    @NamedQuery(name = "Insmastermajorapplication.findByMajorApplicationNm", query = "SELECT i FROM Insmastermajorapplication i WHERE i.majorApplicationNm = :majorApplicationNm"),
    @NamedQuery(name = "Insmastermajorapplication.findByIsActive", query = "SELECT i FROM Insmastermajorapplication i WHERE i.isActive = :isActive")})
public class Insmastermajorapplication implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Id")
    private Integer id;
    @Column(name = "majorApplicationNm")
    private String majorApplicationNm;
    @Column(name = "isActive")
    private String isActive;

    public Insmastermajorapplication() {
    }

    public Insmastermajorapplication(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajorApplicationNm() {
        return majorApplicationNm;
    }

    public void setMajorApplicationNm(String majorApplicationNm) {
        this.majorApplicationNm = majorApplicationNm;
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
        if (!(object instanceof Insmastermajorapplication)) {
            return false;
        }
        Insmastermajorapplication other = (Insmastermajorapplication) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Insmastermajorapplication[id=" + id + "]";
    }

}
