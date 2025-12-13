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
@Table(name = "MSW_aggregatemaster")
@NamedQueries({
    @NamedQuery(name = "Aggregatemaster.findAll", query = "SELECT a FROM Aggregatemaster a"),
    @NamedQuery(name = "Aggregatemaster.findByAggCode", query = "SELECT a FROM Aggregatemaster a WHERE a.aggCode = :aggCode"),
    @NamedQuery(name = "Aggregatemaster.findByAggDesc", query = "SELECT a FROM Aggregatemaster a WHERE a.aggDesc = :aggDesc"),
    @NamedQuery(name = "Aggregatemaster.findByIsActive", query = "SELECT a FROM Aggregatemaster a WHERE a.isActive = :isActive"),
    @NamedQuery(name = "Aggregatemaster.findByLastUpdatedBy", query = "SELECT a FROM Aggregatemaster a WHERE a.lastUpdatedBy = :lastUpdatedBy"),
    @NamedQuery(name = "Aggregatemaster.findByLastUpdatedDate", query = "SELECT a FROM Aggregatemaster a WHERE a.lastUpdatedDate = :lastUpdatedDate")})
public class Aggregatemaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AggCode")
    private String aggCode;
    @Basic(optional = false)
    @Column(name = "AggDesc")
    private String aggDesc;
    @Basic(optional = false)
    @Column(name = "isActive")
    private char isActive;
    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;
    @Basic(optional = false)
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "aggCode", fetch = FetchType.LAZY)
    private List<Subaggregatemaster> subaggregatemasterList;

    public Aggregatemaster() {
    }

    public Aggregatemaster(String aggCode) {
        this.aggCode = aggCode;
    }

    public Aggregatemaster(String aggCode, String aggDesc, char isActive, Date lastUpdatedDate) {
        this.aggCode = aggCode;
        this.aggDesc = aggDesc;
        this.isActive = isActive;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getAggCode() {
        return aggCode;
    }

    public void setAggCode(String aggCode) {
        this.aggCode = aggCode;
    }

    public String getAggDesc() {
        return aggDesc;
    }

    public void setAggDesc(String aggDesc) {
        this.aggDesc = aggDesc;
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

    public List<Subaggregatemaster> getSubaggregatemasterList() {
        return subaggregatemasterList;
    }

    public void setSubaggregatemasterList(List<Subaggregatemaster> subaggregatemasterList) {
        this.subaggregatemasterList = subaggregatemasterList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aggCode != null ? aggCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Aggregatemaster)) {
            return false;
        }
        Aggregatemaster other = (Aggregatemaster) object;
        if ((this.aggCode == null && other.aggCode != null) || (this.aggCode != null && !this.aggCode.equals(other.aggCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Aggregatemaster[aggCode=" + aggCode + "]";
    }

}
