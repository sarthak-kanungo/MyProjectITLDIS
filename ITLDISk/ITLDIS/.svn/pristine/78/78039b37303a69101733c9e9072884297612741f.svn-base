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

/**
 *
 * @author prashant.kumar
 */
@Entity
@Table (name="UM_EmailMaster")
public class UmEmailMaster implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="MailID")
    private BigInteger id;
    @Basic(optional=false)
    @Column(name="Event_ID")
    private BigInteger eventId;
    @Basic(optional=false)
    @Column(name="FROM_MAIL_ID")
    private String fromMailId;
    @Column(name="TO_MAIL_ID")
    private String toMailId;
    @Column(name="CC_MAIL_ID")
    private String ccMailId;
    @Column(name="BCC_MAIL_ID")
    private String bccMailId;
    @Column(name="MAIL_SUBJECT")
    private String mailSubject;
    @Column(name="MAIL_TEXT")
    private String mailText;
    @Column(name="FILE_VECTOR")
    private String fileVector;
    @Column(name="STATUS")
    private String status;
    @Column(name="CreatedOn")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createdOn;
    @Column(name="CreatedBy")
    private String createdBy;
    @Column(name="SentOn")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date sentOn;
    @Column(name="Remarks")
    private String remarks;

    public BigInteger getId() {
        return id;
    }

    public void setId(BigInteger id) {
        this.id = id;
    }

    public String getBccMailId() {
        return bccMailId;
    }

    public void setBccMailId(String bccMailId) {
        this.bccMailId = bccMailId;
    }

    public String getCcMailId() {
        return ccMailId;
    }

    public void setCcMailId(String ccMailId) {
        this.ccMailId = ccMailId;
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

  

    public BigInteger getEventId() {
        return eventId;
    }

    public void setEventId(BigInteger eventId) {
        this.eventId = eventId;
    }

    public String getFileVector() {
        return fileVector;
    }

    public void setFileVector(String fileVector) {
        this.fileVector = fileVector;
    }

    public String getFromMailId() {
        return fromMailId;
    }

    public void setFromMailId(String fromMailId) {
        this.fromMailId = fromMailId;
    }

    public String getMailSubject() {
        return mailSubject;
    }

    public void setMailSubject(String mailSubject) {
        this.mailSubject = mailSubject;
    }

    public String getMailText() {
        return mailText;
    }

    public void setMailText(String mailText) {
        this.mailText = mailText;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public Date getSentOn() {
        return sentOn;
    }

    public void setSentOn(Date sentOn) {
        this.sentOn = sentOn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getToMailId() {
        return toMailId;
    }

    public void setToMailId(String toMailId) {
        this.toMailId = toMailId;
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
        if (!(object instanceof UmEmailMaster)) {
            return false;
        }
        UmEmailMaster other = (UmEmailMaster) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.UmEmailMaster[id=" + id + "]";
    }

}
