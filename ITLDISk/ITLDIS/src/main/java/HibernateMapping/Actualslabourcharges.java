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
@Table(name = "SW_joblabour_actualcharges")
@NamedQueries({
    @NamedQuery(name = "Actualslabourcharges.findAll", query = "SELECT a FROM Actualslabourcharges a"),
    @NamedQuery(name = "Actualslabourcharges.findByJobCardLabourID", query = "SELECT a FROM Actualslabourcharges a WHERE a.jobCardLabourID = :jobCardLabourID"),
    @NamedQuery(name = "Actualslabourcharges.findByJobCardNo", query = "SELECT a FROM Actualslabourcharges a WHERE a.jobCardNo = :jobCardNo"),
    @NamedQuery(name = "Actualslabourcharges.findByCmpNo", query = "SELECT a FROM Actualslabourcharges a WHERE a.cmpNo = :cmpNo"),
    @NamedQuery(name = "Actualslabourcharges.findByActionTaken", query = "SELECT a FROM Actualslabourcharges a WHERE a.actionTaken = :actionTaken"),
    @NamedQuery(name = "Actualslabourcharges.findByLabourChargesAmount", query = "SELECT a FROM Actualslabourcharges a WHERE a.labourChargesAmount = :labourChargesAmount")})
public class Actualslabourcharges implements Serializable {
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

    public Actualslabourcharges() {
    }

    public Actualslabourcharges(String jobCardLabourID) {
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
        if (!(object instanceof Actualslabourcharges)) {
            return false;
        }
        Actualslabourcharges other = (Actualslabourcharges) object;
        if ((this.jobCardLabourID == null && other.jobCardLabourID != null) || (this.jobCardLabourID != null && !this.jobCardLabourID.equals(other.jobCardLabourID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Actualslabourcharges[jobCardLabourID=" + jobCardLabourID + "]";
    }

}
