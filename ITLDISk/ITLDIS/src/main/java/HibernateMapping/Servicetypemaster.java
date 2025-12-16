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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "MSW_servicetypemaster")
public class Servicetypemaster implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ServiceTypeID")
    private int serviceTypeID;
    @Basic(optional = false)
    @Column(name = "ServiceTypeDesc")
    private String serviceTypeDesc;
    @Basic(optional = false)
    @Column(name = "isActive")
    private char isActive;
    @Basic(optional = false)
    @Column(name = "LastUpdatedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedDate;
    @Column(name = "SeqNo")
    private int seqNo;
    @Column(name = "LastUpdatedBy")
    private String lastUpdatedBy;

    public Servicetypemaster() {
    }

    public Servicetypemaster(int serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
    }

    public Servicetypemaster(int serviceTypeID, String serviceTypeDesc, char isActive, Date lastUpdatedDate) {
        this.serviceTypeID = serviceTypeID;
        this.serviceTypeDesc = serviceTypeDesc;
        this.isActive = isActive;
        this.lastUpdatedDate = lastUpdatedDate;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public int getServiceTypeID() {
        return serviceTypeID;
    }

    public void setServiceTypeID(int serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
    }

    public String getServiceTypeDesc() {
        return serviceTypeDesc;
    }

    public void setServiceTypeDesc(String serviceTypeDesc) {
        this.serviceTypeDesc = serviceTypeDesc;
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

    public int getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(int seqNo) {
        this.seqNo = seqNo;
    }

   
    

    @Override
    public String toString() {
        return "HibernateMapping.Servicetypemaster[serviceTypeID=" + serviceTypeID + "]";
    }
}
