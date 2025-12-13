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
@Table(name = "MSW_standardjobpartmaster")
@NamedQueries({
    @NamedQuery(name = "Standardjobpartmaster.findAll", query = "SELECT s FROM Standardjobpartmaster s"),
    @NamedQuery(name = "Standardjobpartmaster.findById", query = "SELECT s FROM Standardjobpartmaster s WHERE s.id = :id"),
    @NamedQuery(name = "Standardjobpartmaster.findByJobTypeId", query = "SELECT s FROM Standardjobpartmaster s WHERE s.jobTypeId = :jobTypeId"),
    @NamedQuery(name = "Standardjobpartmaster.findByModelcode", query = "SELECT s FROM Standardjobpartmaster s WHERE s.modelcode = :modelcode"),
    @NamedQuery(name = "Standardjobpartmaster.findByPartNo", query = "SELECT s FROM Standardjobpartmaster s WHERE s.partNo = :partNo"),
    @NamedQuery(name = "Standardjobpartmaster.findByQty", query = "SELECT s FROM Standardjobpartmaster s WHERE s.qty = :qty")})
public class Standardjobpartmaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "Id")
    private Long id;
    @Basic(optional = false)
    @Column(name = "JobTypeId")
    private int jobTypeId;
    @Basic(optional = false)
    @Column(name = "modelcode")
    private String modelcode;
    @Basic(optional = false)
    @Column(name = "PartNo")
    private String partNo;
    @Basic(optional = false)
    @Column(name = "qty")
    private Integer qty;
    @Column(name="LastUpdatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedOn;
    @Column(name="LastUpdatedBy")
    private String lastUpdatedBy;

    public Standardjobpartmaster() {
    }

    public Standardjobpartmaster(Long id) {
        this.id = id;
    }

    public Standardjobpartmaster(Long id, int jobTypeId, String modelcode, String partNo, String lastUpdatedBy, Date lastUpdatedOn) {
        this.id = id;
        this.jobTypeId = jobTypeId;
        this.modelcode = modelcode;
        this.partNo = partNo;
        this.lastUpdatedBy = lastUpdatedBy;
        this.lastUpdatedOn = lastUpdatedOn;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(int jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    public String getModelcode() {
        return modelcode;
    }

    public void setModelcode(String modelcode) {
        this.modelcode = modelcode;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public Integer getQty() {
        return qty;
    }

    public void setQty(Integer qty) {
        this.qty = qty;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
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
        if (!(object instanceof Standardjobpartmaster)) {
            return false;
        }
        Standardjobpartmaster other = (Standardjobpartmaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Standardjobpartmaster[id=" + id + "]";
    }

}
