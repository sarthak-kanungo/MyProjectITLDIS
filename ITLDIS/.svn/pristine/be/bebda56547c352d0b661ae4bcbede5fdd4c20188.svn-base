/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package HibernateMapping;

import java.io.Serializable;
import java.math.BigInteger;
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
 * @author megha.pandya
 */
@Entity
@Table(name = "SW_VehicleServiceSchedule")
public class SWVehicleServiceSchedule implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "scheduleID")
    private BigInteger scheduleID;
    @Basic(optional = false)
    @Column(name = "DueDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dueDate;
    @Basic(optional = false)
    @Column(name = "Status")
    private String status;
    @Column(name = "JobCardNo")
    private String jobCardNo;
    @Column(name = "JobCardDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date jobCardDate;
    @Column(name = "ServiceDealer")
    private String serviceDealer;
    @Column(name = "FollowUpStatus")
    private String followUpStatus;
    @Column(name = "LastFollowupDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastFollowupDate;
    @Basic(optional = false)
    @Column(name = "vinNo")
    private String vinNo;
    @Basic(optional = false)
    @Column(name = "JobTypeId")
    private int jobTypeId;
    @Basic(optional = false)
    @Column(name = "DeliveryDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deliveryDate;

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(int jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    public String getVinNo() {
        return vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public BigInteger getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(BigInteger scheduleID) {
        this.scheduleID = scheduleID;
    }

    public String getFollowUpStatus() {
        return followUpStatus;
    }

    public void setFollowUpStatus(String followUpStatus) {
        this.followUpStatus = followUpStatus;
    }

    public Date getLastFollowupDate() {
        return lastFollowupDate;
    }

    public void setLastFollowupDate(Date lastFollowupDate) {
        this.lastFollowupDate = lastFollowupDate;
    }

    public SWVehicleServiceSchedule() {
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getJobCardNo() {
        return jobCardNo;
    }

    public void setJobCardNo(String jobCardNo) {
        this.jobCardNo = jobCardNo;
    }

    public Date getJobCardDate() {
        return jobCardDate;
    }

    public void setJobCardDate(Date jobCardDate) {
        this.jobCardDate = jobCardDate;
    }

    public String getServiceDealer() {
        return serviceDealer;
    }

    public void setServiceDealer(String serviceDealer) {
        this.serviceDealer = serviceDealer;
    }
}
