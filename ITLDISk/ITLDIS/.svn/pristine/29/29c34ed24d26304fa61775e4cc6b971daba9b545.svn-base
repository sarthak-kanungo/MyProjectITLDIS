/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author kundan.rastogi
 */
@Entity
@Table(name = "MSW_complaintcode")
@NamedQueries({
    @NamedQuery(name = "Complaintcodemaster.findAll", query = "SELECT c FROM Complaintcodemaster c"),
    @NamedQuery(name = "Complaintcodemaster.findByCompCode", query = "SELECT c FROM Complaintcodemaster c WHERE c.compCode = :compCode"),
    @NamedQuery(name = "Complaintcodemaster.findByCompDesc", query = "SELECT c FROM Complaintcodemaster c WHERE c.compDesc = :compDesc"),
    @NamedQuery(name = "Complaintcodemaster.findByIsActive", query = "SELECT c FROM Complaintcodemaster c WHERE c.isActive = :isActive"),
    @NamedQuery(name = "Complaintcodemaster.findByLastUpdatedBy", query = "SELECT c FROM Complaintcodemaster c WHERE c.lastUpdatedBy = :lastUpdatedBy"),
    @NamedQuery(name = "Complaintcodemaster.findByLastUpdatedDate", query = "SELECT c FROM Complaintcodemaster c WHERE c.lastUpdatedDate = :lastUpdatedDate")})
public class Complaintcodemaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "CompCode")
    private String compCode;
    @Basic(optional = false)
    @Column(name = "CompDesc")
    private String compDesc;
    @Basic(optional = false)
    @Column(name = "isActive")
    private char isActive;
    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;
    @Basic(optional = false)
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compCode", fetch = FetchType.LAZY)
    private List<Causalcodemaster> causalcodemasterList;
    @JoinColumn(name = "SubAssemblyCode", referencedColumnName = "SubAssemblyCode")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Subassemblymaster subAssemblyCode;

    public Complaintcodemaster() {
    }

    public Complaintcodemaster(String compCode) {
        this.compCode = compCode;
    }

    public Complaintcodemaster(String compCode, String compDesc, char isActive, Date lastUpdatedDate) {
        this.compCode = compCode;
        this.compDesc = compDesc;
        this.isActive = isActive;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getCompCode() {
        return compCode;
    }

    public void setCompCode(String compCode) {
        this.compCode = compCode;
    }

    public String getCompDesc() {
        return compDesc;
    }

    public void setCompDesc(String compDesc) {
        this.compDesc = compDesc;
    }

    public char getIsActive() {
        return isActive;
    }

    public void setIsActive(char isActive) {
        this.isActive = isActive;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public List<Causalcodemaster> getCausalcodemasterList() {
        return causalcodemasterList;
    }

    public void setCausalcodemasterList(List<Causalcodemaster> causalcodemasterList) {
        this.causalcodemasterList = causalcodemasterList;
    }

    public Subassemblymaster getSubAssemblyCode() {
        return subAssemblyCode;
    }

    public void setSubAssemblyCode(Subassemblymaster subAssemblyCode) {
        this.subAssemblyCode = subAssemblyCode;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (compCode != null ? compCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Complaintcodemaster)) {
            return false;
        }
        Complaintcodemaster other = (Complaintcodemaster) object;
        if ((this.compCode == null && other.compCode != null) || (this.compCode != null && !this.compCode.equals(other.compCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Complaintcodemaster[compCode=" + compCode + "]";
    }

}
