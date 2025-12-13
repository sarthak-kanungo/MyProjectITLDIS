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
@Table(name = "SW_jobother_estimatecharges")
@NamedQueries({
    @NamedQuery(name = "Estimateothercharges.findAll", query = "SELECT e FROM Estimateothercharges e"),
    @NamedQuery(name = "Estimateothercharges.findByJobWorkID", query = "SELECT e FROM Estimateothercharges e WHERE e.jobWorkID = :jobWorkID"),
    @NamedQuery(name = "Estimateothercharges.findByJobCardNo", query = "SELECT e FROM Estimateothercharges e WHERE e.jobCardNo = :jobCardNo"),
    @NamedQuery(name = "Estimateothercharges.findByWorkCode", query = "SELECT e FROM Estimateothercharges e WHERE e.workCode = :workCode"),
    @NamedQuery(name = "Estimateothercharges.findByWorkDescription", query = "SELECT e FROM Estimateothercharges e WHERE e.workDescription = :workDescription"),
    @NamedQuery(name = "Estimateothercharges.findByWorkAmount", query = "SELECT e FROM Estimateothercharges e WHERE e.workAmount = :workAmount"),
    @NamedQuery(name = "Estimateothercharges.findByIsDocAttach", query = "SELECT e FROM Estimateothercharges e WHERE e.isDocAttach = :isDocAttach"),
    @NamedQuery(name = "Estimateothercharges.findByDocName", query = "SELECT e FROM Estimateothercharges e WHERE e.docName = :docName")})
public class Estimateothercharges implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "JobWorkID")
    private String jobWorkID;
    @Basic(optional = false)
    @Column(name = "JobCardNo")
    private String jobCardNo;
    @Basic(optional = false)
    @Column(name = "workCode")
    private String workCode;
    @Basic(optional = false)
    @Column(name = "workDescription")
    private String workDescription;
    @Basic(optional = false)
    @Column(name = "workAmount")
    private double workAmount;
    @Column(name = "IS_DOC_ATTACH")
    private Character isDocAttach;
    @Column(name = "Doc_Name")
    private String docName;

    public Estimateothercharges() {
    }

    public Estimateothercharges(String jobWorkID) {
        this.jobWorkID = jobWorkID;
    }

    public Estimateothercharges(String jobWorkID, String jobCardNo, String workCode, String workDescription, double workAmount) {
        this.jobWorkID = jobWorkID;
        this.jobCardNo = jobCardNo;
        this.workCode = workCode;
        this.workDescription = workDescription;
        this.workAmount = workAmount;
    }

    public String getJobWorkID() {
        return jobWorkID;
    }

    public void setJobWorkID(String jobWorkID) {
        this.jobWorkID = jobWorkID;
    }

    public String getJobCardNo() {
        return jobCardNo;
    }

    public void setJobCardNo(String jobCardNo) {
        this.jobCardNo = jobCardNo;
    }

    public String getWorkCode() {
        return workCode;
    }

    public void setWorkCode(String workCode) {
        this.workCode = workCode;
    }

    public String getWorkDescription() {
        return workDescription;
    }

    public void setWorkDescription(String workDescription) {
        this.workDescription = workDescription;
    }

    public double getWorkAmount() {
        return workAmount;
    }

    public void setWorkAmount(double workAmount) {
        this.workAmount = workAmount;
    }

    public Character getIsDocAttach() {
        return isDocAttach;
    }

    public void setIsDocAttach(Character isDocAttach) {
        this.isDocAttach = isDocAttach;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (jobWorkID != null ? jobWorkID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estimateothercharges)) {
            return false;
        }
        Estimateothercharges other = (Estimateothercharges) object;
        if ((this.jobWorkID == null && other.jobWorkID != null) || (this.jobWorkID != null && !this.jobWorkID.equals(other.jobWorkID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.Estimateothercharges[jobWorkID=" + jobWorkID + "]";
    }

}
