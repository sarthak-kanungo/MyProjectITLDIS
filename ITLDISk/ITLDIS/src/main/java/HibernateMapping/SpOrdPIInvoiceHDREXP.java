/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package HibernateMapping;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "SP_ORD_PI_INVOICE_HDR_EXP")
public class SpOrdPIInvoiceHDREXP implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "INVOICE_ID")
    private int invoiceId;
    @Column(name = "INVOICE_NO")
    private String invoiceNo;
    @Column(name = "INVOICE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;
    @Column(name = "TOTAL_INVOICE_AMOUNT")
    private BigDecimal totalInvoiceAmount;    
    @Column(name = "DEALER_CODE")
    private String dealerCode;
    @Column(name = "DISPATCH_MODE")
    private String dispatchMode;
    @Column(name = "AWB_BOL_NO")
    private String awbBolNo;
    @Column(name = "AWB_BOL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date awbBolDate;
    @Column(name = "INV_CURRENCY")
    private String invCurrency;
    @Column(name = "INSPECTION_CHRG")
    private Float inspCharge;
    @Column(name = "LOCAL_TPT")
    private Float localTPT;
    @Column(name = "INSURANCE_CHRG")
    private Float insurnceCharge;
    @Column(name = "FREIGHT_CHRG")
    private Float freightCharge;
    @Column(name = "OTHER_CHRG")
    private Float otherCharge;
    @Column(name = "Is_ServerSync")
    private Character isServerSync;
    @Column(name = "LastSyncDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSyncDate;

    public Date getAwbBolDate() {
        return awbBolDate;
    }

    public void setAwbBolDate(Date awbBolDate) {
        this.awbBolDate = awbBolDate;
    }

    public String getAwbBolNo() {
        return awbBolNo;
    }

    public void setAwbBolNo(String awbBolNo) {
        this.awbBolNo = awbBolNo;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDispatchMode() {
        return dispatchMode;
    }

    public void setDispatchMode(String dispatchMode) {
        this.dispatchMode = dispatchMode;
    }

    public Float getFreightCharge() {
        return freightCharge;
    }

    public void setFreightCharge(Float freightCharge) {
        this.freightCharge = freightCharge;
    }

    public Float getInspCharge() {
        return inspCharge;
    }

    public void setInspCharge(Float inspCharge) {
        this.inspCharge = inspCharge;
    }

    public Float getInsurnceCharge() {
        return insurnceCharge;
    }

    public void setInsurnceCharge(Float insurnceCharge) {
        this.insurnceCharge = insurnceCharge;
    }

    public String getInvCurrency() {
        return invCurrency;
    }

    public void setInvCurrency(String invCurrency) {
        this.invCurrency = invCurrency;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Date getLastSyncDate() {
        return lastSyncDate;
    }

    public void setLastSyncDate(Date lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }

    public Float getLocalTPT() {
        return localTPT;
    }

    public void setLocalTPT(Float localTPT) {
        this.localTPT = localTPT;
    }

    public Float getOtherCharge() {
        return otherCharge;
    }

    public void setOtherCharge(Float otherCharge) {
        this.otherCharge = otherCharge;
    }

    public BigDecimal getTotalInvoiceAmount() {
        return totalInvoiceAmount;
    }

    public void setTotalInvoiceAmount(BigDecimal totalInvoiceAmount) {
        this.totalInvoiceAmount = totalInvoiceAmount;
    }

    public Character getIsServerSync() {
        return isServerSync;
    }

    public void setIsServerSync(Character isServerSync) {
        this.isServerSync = isServerSync;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final SpOrdPIInvoiceHDREXP other = (SpOrdPIInvoiceHDREXP) obj;
        if (this.invoiceId != other.invoiceId) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + this.invoiceId;
        return hash;
    }

     @Override
    public String toString() {
        return "HibernateMapping.SpOrdPIInvoiceHDREXP[id=" + invoiceId + "]";
    }

}
