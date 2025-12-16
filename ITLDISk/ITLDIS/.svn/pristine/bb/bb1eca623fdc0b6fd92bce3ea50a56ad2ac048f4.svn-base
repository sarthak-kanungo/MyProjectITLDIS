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
@Table(name = "MSW_subaggregatemaster")
@NamedQueries({
    @NamedQuery(name = "Subaggregatemaster.findAll", query = "SELECT s FROM Subaggregatemaster s"),
    @NamedQuery(name = "Subaggregatemaster.findBySubAggCode", query = "SELECT s FROM Subaggregatemaster s WHERE s.subAggCode = :subAggCode"),
    @NamedQuery(name = "Subaggregatemaster.findBySubAggDesc", query = "SELECT s FROM Subaggregatemaster s WHERE s.subAggDesc = :subAggDesc"),
    @NamedQuery(name = "Subaggregatemaster.findByIsActive", query = "SELECT s FROM Subaggregatemaster s WHERE s.isActive = :isActive"),
    @NamedQuery(name = "Subaggregatemaster.findByLastUpdatedBy", query = "SELECT s FROM Subaggregatemaster s WHERE s.lastUpdatedBy = :lastUpdatedBy"),
    @NamedQuery(name = "Subaggregatemaster.findByLastUpdateDate", query = "SELECT s FROM Subaggregatemaster s WHERE s.lastUpdateDate = :lastUpdateDate")})
public class Subaggregatemaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SubAggCode")
    private String subAggCode;
    @Basic(optional = false)
    @Column(name = "SubAggDesc")
    private String subAggDesc;
    @Basic(optional = false)
    @Column(name = "is_Active")
    private char isActive;
    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;
    @Basic(optional = false)
    @Column(name = "LastUpdateDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdateDate;
    @JoinColumn(name = "AggCode", referencedColumnName = "AggCode")
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Aggregatemaster aggCode;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "subAggCode", fetch = FetchType.LAZY)
    private List<Subassemblymaster> subassemblymasterList;

    public Subaggregatemaster() {
    }

    public Subaggregatemaster(String subAggCode) {
        this.subAggCode = subAggCode;
    }

    public Subaggregatemaster(String subAggCode, String subAggDesc, char isActive, Date lastUpdateDate) {
        this.subAggCode = subAggCode;
        this.subAggDesc = subAggDesc;
        this.isActive = isActive;
        this.lastUpdateDate = lastUpdateDate;
    }

    public String getSubAggCode() {
        return subAggCode;
    }

    public void setSubAggCode(String subAggCode) {
        this.subAggCode = subAggCode;
    }

    public String getSubAggDesc() {
        return subAggDesc;
    }

    public void setSubAggDesc(String subAggDesc) {
        this.subAggDesc = subAggDesc;
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

    public Date getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(Date lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }

    public Aggregatemaster getAggCode() {
        return aggCode;
    }

    public void setAggCode(Aggregatemaster aggCode) {
        this.aggCode = aggCode;
    }

    public List<Subassemblymaster> getSubassemblymasterList() {
        return subassemblymasterList;
    }

    public void setSubassemblymasterList(List<Subassemblymaster> subassemblymasterList) {
        this.subassemblymasterList = subassemblymasterList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (subAggCode != null ? subAggCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subaggregatemaster)) {
            return false;
        }
        Subaggregatemaster other = (Subaggregatemaster) object;
        if ((this.subAggCode == null && other.subAggCode != null) || (this.subAggCode != null && !this.subAggCode.equals(other.subAggCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Subaggregatemaster[subAggCode=" + subAggCode + "]";
    }

}
