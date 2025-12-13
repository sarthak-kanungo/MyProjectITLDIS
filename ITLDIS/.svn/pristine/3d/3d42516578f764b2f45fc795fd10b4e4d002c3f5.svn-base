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
import javax.persistence.Id;
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
@Table(name = "MSW_applicationmaster")
@NamedQueries({
    @NamedQuery(name = "ApplicationMaster.findAll", query = "SELECT a FROM ApplicationMaster a"),
    @NamedQuery(name = "ApplicationMaster.findByAppCode", query = "SELECT a FROM ApplicationMaster a WHERE a.appCode = :appCode"),
    @NamedQuery(name = "ApplicationMaster.findByAppDesc", query = "SELECT a FROM ApplicationMaster a WHERE a.appDesc = :appDesc"),
    @NamedQuery(name = "ApplicationMaster.findByIsActive", query = "SELECT a FROM ApplicationMaster a WHERE a.isActive = :isActive"),
    @NamedQuery(name = "ApplicationMaster.findByLastUpdatedDate", query = "SELECT a FROM ApplicationMaster a WHERE a.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "ApplicationMaster.findByLastUpdatedBy", query = "SELECT a FROM ApplicationMaster a WHERE a.lastUpdatedBy = :lastUpdatedBy")})
public class ApplicationMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AppCode")
    private String appCode;
    @Column(name = "AppDesc")
    private String appDesc;
    @Column(name = "isActive")
    private Character isActive;
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;

    public ApplicationMaster() {
    }

    public ApplicationMaster(String appCode) {
        this.appCode = appCode;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public Character getIsActive() {
        return isActive;
    }

    public void setIsActive(Character isActive) {
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (appCode != null ? appCode.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ApplicationMaster)) {
            return false;
        }
        ApplicationMaster other = (ApplicationMaster) object;
        if ((this.appCode == null && other.appCode != null) || (this.appCode != null && !this.appCode.equals(other.appCode))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.ApplicationMaster[appCode=" + appCode + "]";
    }

}
