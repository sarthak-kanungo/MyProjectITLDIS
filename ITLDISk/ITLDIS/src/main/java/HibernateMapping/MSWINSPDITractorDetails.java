/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

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
@Table(name = "MSW_INS_PDI_TractorDetails")
@NamedQueries({
    @NamedQuery(name = "MSWINSPDITractorDetails.findAll", query = "SELECT m FROM MSWINSPDITractorDetails m"),
    @NamedQuery(name = "MSWINSPDITractorDetails.findById", query = "SELECT m FROM MSWINSPDITractorDetails m WHERE m.id = :id"),
    @NamedQuery(name = "MSWINSPDITractorDetails.findByPartName", query = "SELECT m FROM MSWINSPDITractorDetails m WHERE m.partName = :partName"),
    @NamedQuery(name = "MSWINSPDITractorDetails.findByMakeName", query = "SELECT m FROM MSWINSPDITractorDetails m WHERE m.makeName = :makeName"),
    @NamedQuery(name = "MSWINSPDITractorDetails.findByIsActive", query = "SELECT m FROM MSWINSPDITractorDetails m WHERE m.isActive = :isActive"),
    @NamedQuery(name = "MSWINSPDITractorDetails.findByLastUpdatedBy", query = "SELECT m FROM MSWINSPDITractorDetails m WHERE m.lastUpdatedBy = :lastUpdatedBy"),
    @NamedQuery(name = "MSWINSPDITractorDetails.findByLastUpdatedOn", query = "SELECT m FROM MSWINSPDITractorDetails m WHERE m.lastUpdatedOn = :lastUpdatedOn")})
public class MSWINSPDITractorDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
//    @Basic(optional = false)
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "PartName")
    private String partName;
    @Column(name = "MakeName")
    private String makeName;
    @Column(name = "IsActive")
    private Character isActive;
    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;
    @Column(name = "LastUpdatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedOn;

    public MSWINSPDITractorDetails() {
    }

    public MSWINSPDITractorDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPartName() {
        return partName;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public Character getIsActive() {
        return isActive;
    }

    public void setIsActive(Character isActive) {
        this.isActive = isActive;
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
        if (!(object instanceof MSWINSPDITractorDetails)) {
            return false;
        }
        MSWINSPDITractorDetails other = (MSWINSPDITractorDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.MSWINSPDITractorDetails[id=" + id + "]";
    }

}
