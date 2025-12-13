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
@Table(name = "MSW_subassemblymaster")
@NamedQueries({
    @NamedQuery(name = "Subassemblymaster.findAll", query = "SELECT s FROM Subassemblymaster s"),
    @NamedQuery(name = "Subassemblymaster.findBySubAssemblyCode", query = "SELECT s FROM Subassemblymaster s WHERE s.subAssemblyCode = :subAssemblyCode"),
    @NamedQuery(name = "Subassemblymaster.findBySubAssemblyDesc", query = "SELECT s FROM Subassemblymaster s WHERE s.subAssemblyDesc = :subAssemblyDesc"),
    @NamedQuery(name = "Subassemblymaster.findByIsActive", query = "SELECT s FROM Subassemblymaster s WHERE s.isActive = :isActive"),
    @NamedQuery(name = "Subassemblymaster.findByLastUpdatedDate", query = "SELECT s FROM Subassemblymaster s WHERE s.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "Subassemblymaster.findByLastUpdatedBy", query = "SELECT s FROM Subassemblymaster s WHERE s.lastUpdatedBy = :lastUpdatedBy")})
public class Subassemblymaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SubAssemblyCode")
    private String subAssemblyCode;
    @Basic(optional = false)
    @Column(name = "SubAssemblyDesc")
    private String subAssemblyDesc;
    @Basic(optional = false)
    @Column(name = "IsActive")
    private char isActive;
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;
    @JoinColumn(name = "SubAggCode", referencedColumnName = "SubAggCode")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Subaggregatemaster subAggCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subAssemblyCode", fetch = FetchType.LAZY)
    private List<Complaintcodemaster> complaintcodemasterList;

    public Subassemblymaster() {
    }

    public Subassemblymaster(String subAssemblyCode) {
        this.subAssemblyCode = subAssemblyCode;
    }

    public Subassemblymaster(String subAssemblyCode, String subAssemblyDesc, char isActive) {
        this.subAssemblyCode = subAssemblyCode;
        this.subAssemblyDesc = subAssemblyDesc;
        this.isActive = isActive;
    }

    public String getSubAssemblyCode() {
        return subAssemblyCode;
    }

    public void setSubAssemblyCode(String subAssemblyCode) {
        this.subAssemblyCode = subAssemblyCode;
    }

    public String getSubAssemblyDesc() {
        return subAssemblyDesc;
    }

    public void setSubAssemblyDesc(String subAssemblyDesc) {
        this.subAssemblyDesc = subAssemblyDesc;
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

    public Subaggregatemaster getSubAggCode() {
        return subAggCode;
    }

    public void setSubAggCode(Subaggregatemaster subAggCode) {
        this.subAggCode = subAggCode;
    }

    public List<Complaintcodemaster> getComplaintcodemasterList() {
        return complaintcodemasterList;
    }

    public void setComplaintcodemasterList(List<Complaintcodemaster> complaintcodemasterList) {
        this.complaintcodemasterList = complaintcodemasterList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subAssemblyCode != null ? subAssemblyCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subassemblymaster)) {
            return false;
        }
        Subassemblymaster other = (Subassemblymaster) object;
        if ((this.subAssemblyCode == null && other.subAssemblyCode != null) || (this.subAssemblyCode != null && !this.subAssemblyCode.equals(other.subAssemblyCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Subassemblymaster[subAssemblyCode=" + subAssemblyCode + "]";
    }

}
