/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author megha.pandya
 */
@Embeddable
public class SWVehicleServiceSchedulePK implements Serializable {
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

    public SWVehicleServiceSchedulePK() {
    }

    public SWVehicleServiceSchedulePK(String vinNo, int jobTypeId, Date deliveryDate) {
        this.vinNo = vinNo;
        this.jobTypeId = jobTypeId;
        this.deliveryDate = deliveryDate;
    }

    public String getVinNo() {
        return vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public int getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(int jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (vinNo != null ? vinNo.hashCode() : 0);
        hash += (int) jobTypeId;
        hash += (deliveryDate != null ? deliveryDate.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SWVehicleServiceSchedulePK)) {
            return false;
        }
        SWVehicleServiceSchedulePK other = (SWVehicleServiceSchedulePK) object;
        if ((this.vinNo == null && other.vinNo != null) || (this.vinNo != null && !this.vinNo.equals(other.vinNo))) {
            return false;
        }
        if (this.jobTypeId != other.jobTypeId) {
            return false;
        }
        if ((this.deliveryDate == null && other.deliveryDate != null) || (this.deliveryDate != null && !this.deliveryDate.equals(other.deliveryDate))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SWVehicleServiceSchedulePK[vinNo=" + vinNo + ", jobTypeId=" + jobTypeId + ", deliveryDate=" + deliveryDate + "]";
    }

}
