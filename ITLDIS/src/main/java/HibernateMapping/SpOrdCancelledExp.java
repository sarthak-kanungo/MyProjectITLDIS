/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "SP_ORD_CANCELLED_EXP")
public class SpOrdCancelledExp implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CANCELLED_ID")
    private BigInteger cancelledId;
    @Column(name = "ERP_ORDER_NO")
    private String erpOrderNo;
    @Column(name = "ERP_PART_ORDER_NO")
    private Integer erpPartOrderNo;
    @Column(name = "DMS_PI_NO")
    private String dmsPiNo;
    @Column(name = "CANCELLED_PART")
    private String cancelledPart;
    @Column(name = "QTY_CANCELLED")
    private int qtyCancelled;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "REMARKS")    
    private String remarks;
    @Column(name = "Is_ServerSync")
    private Character isServerSync;
    @Column(name = "LastSyncDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSyncDate;

    public BigInteger getCancelledId() {
        return cancelledId;
    }

    public void setCancelledId(BigInteger cancelledId) {
        this.cancelledId = cancelledId;
    }

    public String getCancelledPart() {
        return cancelledPart;
    }

    public void setCancelledPart(String cancelledPart) {
        this.cancelledPart = cancelledPart;
    }

    public String getDmsPiNo() {
        return dmsPiNo;
    }

    public void setDmsPiNo(String dmsPiNo) {
        this.dmsPiNo = dmsPiNo;
    }

    public Integer getErpPartOrderNo() {
        return erpPartOrderNo;
    }

    public void setErpPartOrderNo(Integer erpPartOrderNo) {
        this.erpPartOrderNo = erpPartOrderNo;
    }

    public String getErpOrderNo() {
        return erpOrderNo;
    }

    public void setErpOrderNo(String erpOrderNo) {
        this.erpOrderNo = erpOrderNo;
    }

    public Character getIsServerSync() {
        return isServerSync;
    }

    public void setIsServerSync(Character isServerSync) {
        this.isServerSync = isServerSync;
    }

    public Date getLastSyncDate() {
        return lastSyncDate;
    }

    public void setLastSyncDate(Date lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }

    public int getQtyCancelled() {
        return qtyCancelled;
    }

    public void setQtyCancelled(int qtyCancelled) {
        this.qtyCancelled = qtyCancelled;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpOrdCancelledExp[cancelledId=" + cancelledId + "]";
    }
}
