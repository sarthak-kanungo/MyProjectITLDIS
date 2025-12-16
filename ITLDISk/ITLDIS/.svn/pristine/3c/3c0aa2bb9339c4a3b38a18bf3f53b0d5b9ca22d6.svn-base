/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "MSW_causalcodevsconsequencecode")
@NamedQueries({
    @NamedQuery(name = "Causalcodevsconsequencecode.findAll", query = "SELECT c FROM Causalcodevsconsequencecode c"),
    @NamedQuery(name = "Causalcodevsconsequencecode.findByCausalCode", query = "SELECT c FROM Causalcodevsconsequencecode c WHERE c.causalCode = :causalCode"),
    @NamedQuery(name = "Causalcodevsconsequencecode.findByConsequenceCode", query = "SELECT c FROM Causalcodevsconsequencecode c WHERE c.consequenceCode = :consequenceCode"),
    @NamedQuery(name = "Causalcodevsconsequencecode.findByLastUpdatedDate", query = "SELECT c FROM Causalcodevsconsequencecode c WHERE c.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "Causalcodevsconsequencecode.findByCreatedBy", query = "SELECT c FROM Causalcodevsconsequencecode c WHERE c.createdBy = :createdBy"),
    @NamedQuery(name = "Causalcodevsconsequencecode.findById", query = "SELECT c FROM Causalcodevsconsequencecode c WHERE c.id = :id")})
public class Causalcodevsconsequencecode implements Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "CausalCode")
    private String causalCode;
    @Column(name = "ConsequenceCode")
    private String consequenceCode;
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "CreatedBy")
    private String createdBy;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;

    public Causalcodevsconsequencecode() {
    }

    public Causalcodevsconsequencecode(Integer id) {
        this.id = id;
    }

    public String getCausalCode() {
        return causalCode;
    }

    public void setCausalCode(String causalCode) {
        this.causalCode = causalCode;
    }

    public String getConsequenceCode() {
        return consequenceCode;
    }

    public void setConsequenceCode(String consequenceCode) {
        this.consequenceCode = consequenceCode;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof Causalcodevsconsequencecode)) {
            return false;
        }
        Causalcodevsconsequencecode other = (Causalcodevsconsequencecode) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Causalcodevsconsequencecode[id=" + id + "]";
    }

}
