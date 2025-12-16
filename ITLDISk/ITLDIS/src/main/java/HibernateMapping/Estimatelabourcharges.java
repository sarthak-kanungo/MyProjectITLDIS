/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author vijay.mishra
 */
@Entity
@Table(name = "SW_joblabour_estimatecharges")
@NamedQueries({
    @NamedQuery(name = "Estimatelabourcharges.findAll", query = "SELECT e FROM Estimatelabourcharges e"),
    @NamedQuery(name = "Estimatelabourcharges.findByJobCardLabourID", query = "SELECT e FROM Estimatelabourcharges e WHERE e.jobCardLabourID = :jobCardLabourID"),
    @NamedQuery(name = "Estimatelabourcharges.findByJobCardNo", query = "SELECT e FROM Estimatelabourcharges e WHERE e.jobCardNo = :jobCardNo"),
    @NamedQuery(name = "Estimatelabourcharges.findByCmpNo", query = "SELECT e FROM Estimatelabourcharges e WHERE e.cmpNo = :cmpNo"),
    @NamedQuery(name = "Estimatelabourcharges.findByActionTaken", query = "SELECT e FROM Estimatelabourcharges e WHERE e.actionTaken = :actionTaken"),
    @NamedQuery(name = "Estimatelabourcharges.findByLabourChargesAmount", query = "SELECT e FROM Estimatelabourcharges e WHERE e.labourChargesAmount = :labourChargesAmount")})
public class Estimatelabourcharges implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "JobCardLabourID")
    private String jobCardLabourID;
    @Column(name = "JobCardNo")
    private String jobCardNo;
    @Column(name = "CMP_NO")
    private String cmpNo;
    @Column(name = "ActionTaken")
    private String actionTaken;
    @Column(name = "labourChargesAmount")
    private Double labourChargesAmount;

    public Estimatelabourcharges() {
    }

    public Estimatelabourcharges(String jobCardLabourID) {
        this.jobCardLabourID = jobCardLabourID;
    }

    public String getJobCardLabourID() {
        return jobCardLabourID;
    }

    public void setJobCardLabourID(String jobCardLabourID) {
        this.jobCardLabourID = jobCardLabourID;
    }

    public String getJobCardNo() {
        return jobCardNo;
    }

    public void setJobCardNo(String jobCardNo) {
        this.jobCardNo = jobCardNo;
    }

    public String getCmpNo() {
        return cmpNo;
    }

    public void setCmpNo(String cmpNo) {
        this.cmpNo = cmpNo;
    }

    public String getActionTaken() {
        return actionTaken;
    }

    public void setActionTaken(String actionTaken) {
        this.actionTaken = actionTaken;
    }

    public Double getLabourChargesAmount() {
        return labourChargesAmount;
    }

    public void setLabourChargesAmount(Double labourChargesAmount) {
        this.labourChargesAmount = labourChargesAmount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobCardLabourID != null ? jobCardLabourID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estimatelabourcharges)) {
            return false;
        }
        Estimatelabourcharges other = (Estimatelabourcharges) object;
        if ((this.jobCardLabourID == null && other.jobCardLabourID != null) || (this.jobCardLabourID != null && !this.jobCardLabourID.equals(other.jobCardLabourID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Estimatelabourcharges[jobCardLabourID=" + jobCardLabourID + "]";
    }

}
