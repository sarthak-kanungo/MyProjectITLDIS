package HibernateMapping;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ashutosh.Kumar1
 */
@Entity
@Table(name = "SP_ORD_PI_INVOICE_EXP")
@NamedQueries({
    @NamedQuery(name = "SpPiInvoiceEXP.findAll", query = "SELECT s FROM SpPiInvoiceEXP s"),
    @NamedQuery(name = "SpPiInvoiceEXP.findByErpOrderNo", query = "SELECT s FROM SpPiInvoiceEXP s WHERE s.spPiInvoiceEXPPK.erpOrderNo = :erpOrderNo"),
    @NamedQuery(name = "SpPiInvoiceEXP.findByErpPartOrderNo", query = "SELECT s FROM SpPiInvoiceEXP s WHERE s.spPiInvoiceEXPPK.erpPartOrderNo = :erpPartOrderNo"),
    @NamedQuery(name = "SpPiInvoiceEXP.findByInvoiceNo", query = "SELECT s FROM SpPiInvoiceEXP s WHERE s.spPiInvoiceEXPPK.invoiceNo = :invoiceNo"),
    @NamedQuery(name = "SpPiInvoiceEXP.findByInvoiceDate", query = "SELECT s FROM SpPiInvoiceEXP s WHERE s.invoiceDate = :invoiceDate"),
    @NamedQuery(name = "SpPiInvoiceEXP.findByTotalInvoiceAmount", query = "SELECT s FROM SpPiInvoiceEXP s WHERE s.totalInvoiceAmount = :totalInvoiceAmount"),
    @NamedQuery(name = "SpPiInvoiceEXP.findByShippedPartNo", query = "SELECT s FROM SpPiInvoiceEXP s WHERE s.shippedPartNo = :shippedPartNo"),
    @NamedQuery(name = "SpPiInvoiceEXP.findByQtyShipped", query = "SELECT s FROM SpPiInvoiceEXP s WHERE s.qtyShipped = :qtyShipped"),
    //@NamedQuery(name = "SpPiInvoiceEXP.findByPrice", query = "SELECT s FROM SpPiInvoiceEXP s WHERE s.price = :price"),
    @NamedQuery(name = "SpPiInvoiceEXP.findByStatus", query = "SELECT s FROM SpPiInvoiceEXP s WHERE s.status = :status"),
    //@NamedQuery(name = "SpPiInvoiceEXP.findByLrNo", query = "SELECT s FROM SpPiInvoiceEXP s WHERE s.lrNo = :lrNo"),
    //@NamedQuery(name = "SpPiInvoiceEXP.findByShipmentDate", query = "SELECT s FROM SpPiInvoiceEXP s WHERE s.shipmentDate = :shipmentDate"),
    //@NamedQuery(name = "SpPiInvoiceEXP.findByTransporterName", query = "SELECT s FROM SpPiInvoiceEXP s WHERE s.transporterName = :transporterName"),
    //@NamedQuery(name = "SpPiInvoiceEXP.findByPermitNo", query = "SELECT s FROM SpPiInvoiceEXP s WHERE s.permitNo = :permitNo"),
    @NamedQuery(name = "SpPiInvoiceEXP.findByIsServerSync", query = "SELECT s FROM SpPiInvoiceEXP s WHERE s.isServerSync = :isServerSync"),
    @NamedQuery(name = "SpPiInvoiceEXP.findByLastSyncDate", query = "SELECT s FROM SpPiInvoiceEXP s WHERE s.lastSyncDate = :lastSyncDate")})
public class SpPiInvoiceEXP implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SpPiInvoiceEXPPK spPiInvoiceEXPPK;
    @Column(name = "INVOICE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;
    @Column(name = "TOTAL_INVOICE_AMOUNT")
    private BigDecimal totalInvoiceAmount;
    @Column(name = "DMS_PI_NO")
    private String dmsPINo;
    @Column(name = "DEALER_CODE")
    private String dealerCode;
    @Column(name = "DISPATCH_MODE")
    private String dispatchMode;
    @Column(name = "AWB_BOL_NO")
    private String awbBolNo;
    @Column(name = "AWB_BOL_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date awbBolDate;
    @Column(name = "ORDERED_PART")
    private String orderedPart;
    @Column(name = "SHIPPED_PART_NO")
    private String shippedPartNo;
    @Basic(optional = false)
    @Column(name = "QTY_SHIPPED")
    private Integer qtyShipped;
    @Column(name = "INVOICED_RATE")
    private Float invoicedRate;
    @Column(name = "INV_CURRENCY")
    private String invCurrency;
    @Column(name = "STATUS")
    private String status;
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
    @Column(name = "REMARKS")
    private String remarks;
    @Column(name = "DEALER_ACCEPTANCE")
    private String dealerAcceptance;
    @Column(name = "ACCEPTED_BY")
    private String acceptedBy;
    @Column(name = "ACCEPTED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acceptedOn;
    @Column(name = "DEALER_REMARKS")
    private String dealerRemarks;
    @Basic(optional = false)
    @Column(name = "Is_ServerSync")
    private Character isServerSync;
    @Column(name = "LastSyncDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSyncDate;
    
    public SpPiInvoiceEXP() {
    }

    public SpPiInvoiceEXP(SpPiInvoiceEXPPK spPiInvoiceEXPPK) {
        this.spPiInvoiceEXPPK = spPiInvoiceEXPPK;
    }

    public SpPiInvoiceEXP(SpPiInvoiceEXPPK spPiInvoiceEXPPK, Date invoiceDate, BigDecimal totalInvoiceAmount, String dmsPINo, String dealerCode, String dispatchMode, String awbBolNo, Date awbBolDate, String orderedPart, String shippedPartNo, Integer qtyShipped, Float invoicedRate, String invCurrency, String status, Float inspCharge, Float localTPT, Float insurnceCharge, Float freightCharge, Float otherCharge, String remarks, String dealerAcceptance, String acceptedBy, Date acceptedOn, String dealerRemarks, Character isServerSync, Date lastSyncDate) {
        this.spPiInvoiceEXPPK = spPiInvoiceEXPPK;
        this.invoiceDate = invoiceDate;
        this.totalInvoiceAmount = totalInvoiceAmount;
        this.dmsPINo = dmsPINo;
        this.dealerCode = dealerCode;
        this.dispatchMode = dispatchMode;
        this.awbBolNo = awbBolNo;
        this.awbBolDate = awbBolDate;
        this.orderedPart = orderedPart;
        this.shippedPartNo = shippedPartNo;
        this.qtyShipped = qtyShipped;
        this.invoicedRate = invoicedRate;
        this.invCurrency = invCurrency;
        this.status = status;
        this.inspCharge = inspCharge;
        this.localTPT = localTPT;
        this.insurnceCharge = insurnceCharge;
        this.freightCharge = freightCharge;
        this.otherCharge = otherCharge;
        this.remarks = remarks;
        this.dealerAcceptance = dealerAcceptance;
        this.acceptedBy = acceptedBy;
        this.acceptedOn = acceptedOn;
        this.dealerRemarks = dealerRemarks;
        this.isServerSync = isServerSync;
        this.lastSyncDate = lastSyncDate;
    }

   
    public SpPiInvoiceEXP(String erpOrderNo, String erpPartOrderNo, String invoiceNo, String orderedPart) {
        this.spPiInvoiceEXPPK = new SpPiInvoiceEXPPK(erpOrderNo, erpPartOrderNo, invoiceNo);
    }

    public SpPiInvoiceEXPPK getSpPiInvoiceEXPPK() {
        return spPiInvoiceEXPPK;
    }

    public void setSpPiInvoiceEXPPK(SpPiInvoiceEXPPK spPiInvoiceEXPPK) {
        this.spPiInvoiceEXPPK = spPiInvoiceEXPPK;
    }

    public String getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(String acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public Date getAcceptedOn() {
        return acceptedOn;
    }

    public void setAcceptedOn(Date acceptedOn) {
        this.acceptedOn = acceptedOn;
    }

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

    public String getDealerAcceptance() {
        return dealerAcceptance;
    }

    public void setDealerAcceptance(String dealerAcceptance) {
        this.dealerAcceptance = dealerAcceptance;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDealerRemarks() {
        return dealerRemarks;
    }

    public void setDealerRemarks(String dealerRemarks) {
        this.dealerRemarks = dealerRemarks;
    }

    public String getDispatchMode() {
        return dispatchMode;
    }

    public void setDispatchMode(String dispatchMode) {
        this.dispatchMode = dispatchMode;
    }

    public String getDmsPINo() {
        return dmsPINo;
    }

    public void setDmsPINo(String dmsPINo) {
        this.dmsPINo = dmsPINo;
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

    public Float getInvoicedRate() {
        return invoicedRate;
    }

    public void setInvoicedRate(Float invoicedRate) {
        this.invoicedRate = invoicedRate;
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

    public Float getLocalTPT() {
        return localTPT;
    }

    public void setLocalTPT(Float localTPT) {
        this.localTPT = localTPT;
    }

    public String getOrderedPart() {
        return orderedPart;
    }

    public void setOrderedPart(String orderedPart) {
        this.orderedPart = orderedPart;
    }

    public Float getOtherCharge() {
        return otherCharge;
    }

    public void setOtherCharge(Float otherCharge) {
        this.otherCharge = otherCharge;
    }

    public Integer getQtyShipped() {
        return qtyShipped;
    }

    public void setQtyShipped(Integer qtyShipped) {
        this.qtyShipped = qtyShipped;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getShippedPartNo() {
        return shippedPartNo;
    }

    public void setShippedPartNo(String shippedPartNo) {
        this.shippedPartNo = shippedPartNo;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getTotalInvoiceAmount() {
        return totalInvoiceAmount;
    }

    public void setTotalInvoiceAmount(BigDecimal totalInvoiceAmount) {
        this.totalInvoiceAmount = totalInvoiceAmount;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spPiInvoiceEXPPK != null ? spPiInvoiceEXPPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpPiInvoiceEXP)) {
            return false;
        }
        SpPiInvoiceEXP other = (SpPiInvoiceEXP) object;
        if ((this.spPiInvoiceEXPPK == null && other.spPiInvoiceEXPPK != null) || (this.spPiInvoiceEXPPK != null && !this.spPiInvoiceEXPPK.equals(other.spPiInvoiceEXPPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpPiInvoiceEXP[spPiInvoiceEXPPK=" + spPiInvoiceEXPPK + "]";
    }
}
