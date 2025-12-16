package HibernateMapping;

import java.io.Serializable;
import java.util.Date;

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
 * @author prashant.kumar
 */
@Entity
@Table(name = "MSP_CostHead_EXP")
public class MspCostHeadExp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="CostHeadID")
    private String costHeadID;
    @Column(name="CostHeadName")
    private String costHeadName;
    @Column(name="isActive")
    private String isActive;
    @Column(name="LastUpdatedBy")
    private String lastUpdatedBy;
    @Column(name = "LastUpdatedOn")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdatedOn;

    public String getCostHeadID() {
        return costHeadID;
    }

    public void setCostHeadID(String costHeadID) {
        this.costHeadID = costHeadID;
    }

    public String getCostHeadName() {
        return costHeadName;
    }

    public void setCostHeadName(String costHeadName) {
        this.costHeadName = costHeadName;
    }

    public String getIsActive() {
        return isActive;
    }

    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }

    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    public Date getLastUpdatedOn() {
        return lastUpdatedOn;
    }

    public void setLastUpdatedOn(Date lastUpdatedOn) {
        this.lastUpdatedOn = lastUpdatedOn;
    }

     
//    @Override
//    public boolean equals(Object object) {
//        // TODO: Warning - this method won't work in the case the id fields are not set
//        if (!(object instanceof MspCostHeadExp)) {
//            return false;
//        }
//        MspCostHeadExp other = (MspCostHeadExp) object;
//        if ((this.costHeadID == null && other.costHeadID != null) || (this.costHeadID != null && !this.costHeadID.equals(other.costHeadID))) {
//            return false;
//        }
//        return true;
//    }
//
//    @Override
//    public String toString() {
//        return "HibernateMapping.MspCostHeadExp[id=" + costHeadID + "]";
//    }
}