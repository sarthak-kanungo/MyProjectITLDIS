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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kundan.rastogi
 */
@Entity
@Table(name = "MSW_causalcodemaster")
@NamedQueries({
    @NamedQuery(name = "Causalcodemaster.findAll", query = "SELECT c FROM Causalcodemaster c"),
    @NamedQuery(name = "Causalcodemaster.findByCausalCode", query = "SELECT c FROM Causalcodemaster c WHERE c.causalCode = :causalCode"),
    @NamedQuery(name = "Causalcodemaster.findByCausalDesc", query = "SELECT c FROM Causalcodemaster c WHERE c.causalDesc = :causalDesc"),
    @NamedQuery(name = "Causalcodemaster.findByIsActive", query = "SELECT c FROM Causalcodemaster c WHERE c.isActive = :isActive"),
    @NamedQuery(name = "Causalcodemaster.findByLastUpdatedDate", query = "SELECT c FROM Causalcodemaster c WHERE c.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "Causalcodemaster.findByLastUpdatedBy", query = "SELECT c FROM Causalcodemaster c WHERE c.lastUpdatedBy = :lastUpdatedBy")})
public class Causalcodemaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CausalCode")
    private String causalCode;
    @Basic(optional = false)
    @Column(name = "CausalDesc")
    private String causalDesc;
    @Basic(optional = false)
    @Column(name = "isActive")
    private char isActive;
    @Basic(optional = false)
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;
    @JoinColumn(name = "CompCode", referencedColumnName = "CompCode")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Complaintcodemaster compCode;

    public Causalcodemaster() {
    }

    public Causalcodemaster(String causalCode) {
        this.causalCode = causalCode;
    }

    public Causalcodemaster(String causalCode, String causalDesc, char isActive, Date lastUpdatedDate) {
        this.causalCode = causalCode;
        this.causalDesc = causalDesc;
        this.isActive = isActive;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCausalCode() {
        return causalCode;
    }

    public void setCausalCode(String causalCode) {
        this.causalCode = causalCode;
    }

    public String getCausalDesc() {
        return causalDesc;
    }

    public void setCausalDesc(String causalDesc) {
        this.causalDesc = causalDesc;
    }

    public char getIsActive() {
        return isActive;
    }

    public void setIsActive(char isActive) {
        this.isActive = isActive;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Complaintcodemaster getCompCode() {
        return compCode;
    }

    public void setCompCode(Complaintcodemaster compCode) {
        this.compCode = compCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (causalCode != null ? causalCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Causalcodemaster)) {
            return false;
        }
        Causalcodemaster other = (Causalcodemaster) object;
        if ((this.causalCode == null && other.causalCode != null) || (this.causalCode != null && !this.causalCode.equals(other.causalCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Causalcodemaster[causalCode=" + causalCode + "]";
    }

}
