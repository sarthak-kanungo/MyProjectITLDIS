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
 * @author vijay.mishra
 */
@Entity
@Table(name = "partmaster")
@NamedQueries({
    @NamedQuery(name = "Partmaster.findAll", query = "SELECT p FROM Partmaster p"),
    @NamedQuery(name = "Partmaster.findByPartNumber", query = "SELECT p FROM Partmaster p WHERE p.partNumber = :partNumber"),
    @NamedQuery(name = "Partmaster.findByPartDesc", query = "SELECT p FROM Partmaster p WHERE p.partDesc = :partDesc"),
    @NamedQuery(name = "Partmaster.findByFiledBy", query = "SELECT p FROM Partmaster p WHERE p.filedBy = :filedBy"),
    @NamedQuery(name = "Partmaster.findByLastUpdatedDate", query = "SELECT p FROM Partmaster p WHERE p.lastUpdatedDate = :lastUpdatedDate"),
    @NamedQuery(name = "Partmaster.findByIsActive", query = "SELECT p FROM Partmaster p WHERE p.isActive = :isActive"),
    @NamedQuery(name = "Partmaster.findByPartType", query = "SELECT p FROM Partmaster p WHERE p.partType = :partType")})
public class Partmaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Part_Number")
    private String partNumber;
    @Column(name = "part_desc")
    private String partDesc;
    @Column(name = "filedBy")
    private String filedBy;
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "isActive")
    private Character isActive;
    @Column(name = "partType")
    private String partType;

    public Partmaster() {
    }

    public Partmaster(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartNumber() {
        return partNumber;
    }

    public void setPartNumber(String partNumber) {
        this.partNumber = partNumber;
    }

    public String getPartDesc() {
        return partDesc;
    }

    public void setPartDesc(String partDesc) {
        this.partDesc = partDesc;
    }

    public String getFiledBy() {
        return filedBy;
    }

    public void setFiledBy(String filedBy) {
        this.filedBy = filedBy;
    }

    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public Character getIsActive() {
        return isActive;
    }

    public void setIsActive(Character isActive) {
        this.isActive = isActive;
    }

    public String getPartType() {
        return partType;
    }

    public void setPartType(String partType) {
        this.partType = partType;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (partNumber != null ? partNumber.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partmaster)) {
            return false;
        }
        Partmaster other = (Partmaster) object;
        if ((this.partNumber == null && other.partNumber != null) || (this.partNumber != null && !this.partNumber.equals(other.partNumber))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Partmaster[partNumber=" + partNumber + "]";
    }

}
