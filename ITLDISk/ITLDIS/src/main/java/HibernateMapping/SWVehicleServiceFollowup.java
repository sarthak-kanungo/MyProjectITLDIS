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
 * @author ashutosh.kumar
 */
@Entity
@Table(name = "SW_VehicleServiceFollowup")
public class SWVehicleServiceFollowup implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "FollowUpId")
    private BigInteger followUpId;
    @Column(name = "scheduleID")
    private Double scheduleID;
    @Column(name = "ServiceTypeID")
    private int serviceTypeID;
    @Column(name = "CallDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date callDate;
    @Column(name = "CallDescription")
    private String callDescription;
    @Column(name = "DoorstepServiceRequired")
    private String doorstepServiceRequired;
    @Column(name = "CustomerPromisedDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date customerPromisedDate;
    @Column(name = "NextFollowUpDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date nextFollowUpDate;
    @Column(name = "Status")
    private String status;
    @Column(name = "CreatedBy")
    private String createdBy;
    @Column(name = "CreatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdOn;

    public SWVehicleServiceFollowup() {
    }

    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    public String getCallDescription() {
        return callDescription;
    }

    public void setCallDescription(String callDescription) {
        this.callDescription = callDescription;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(Date createdOn) {
        this.createdOn = createdOn;
    }

    public Date getCustomerPromisedDate() {
        return customerPromisedDate;
    }

    public void setCustomerPromisedDate(Date customerPromisedDate) {
        this.customerPromisedDate = customerPromisedDate;
    }

    public String getDoorstepServiceRequired() {
        return doorstepServiceRequired;
    }

    public void setDoorstepServiceRequired(String doorstepServiceRequired) {
        this.doorstepServiceRequired = doorstepServiceRequired;
    }

    public Date getNextFollowUpDate() {
        return nextFollowUpDate;
    }

    public void setNextFollowUpDate(Date nextFollowUpDate) {
        this.nextFollowUpDate = nextFollowUpDate;
    }

    public int getServiceTypeID() {
        return serviceTypeID;
    }

    public void setServiceTypeID(int serviceTypeID) {
        this.serviceTypeID = serviceTypeID;
    }

   

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigInteger getFollowUpId() {
        return followUpId;
    }

    public void setFollowUpId(BigInteger followUpId) {
        this.followUpId = followUpId;
    }

   

    public Double getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(Double scheduleID) {
        this.scheduleID = scheduleID;
    }
}

