/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author kundan.rastogi
 */
@Entity
@Table(name = "SP_ORDER_INVOICES")
@NamedQueries({
    @NamedQuery(name = "SpOrderInvoices.findAll", query = "SELECT s FROM SpOrderInvoices s"),
    @NamedQuery(name = "SpOrderInvoices.findByErpOrderNo", query = "SELECT s FROM SpOrderInvoices s WHERE s.spOrderInvoicesPK.erpOrderNo = :erpOrderNo"),
    @NamedQuery(name = "SpOrderInvoices.findByErpPartOrderNo", query = "SELECT s FROM SpOrderInvoices s WHERE s.spOrderInvoicesPK.erpPartOrderNo = :erpPartOrderNo"),
    @NamedQuery(name = "SpOrderInvoices.findByInvoiceNo", query = "SELECT s FROM SpOrderInvoices s WHERE s.spOrderInvoicesPK.invoiceNo = :invoiceNo"),
    @NamedQuery(name = "SpOrderInvoices.findByInvoiceDate", query = "SELECT s FROM SpOrderInvoices s WHERE s.invoiceDate = :invoiceDate"),
    @NamedQuery(name = "SpOrderInvoices.findByTotalInvoiceAmount", query = "SELECT s FROM SpOrderInvoices s WHERE s.totalInvoiceAmount = :totalInvoiceAmount"),
    @NamedQuery(name = "SpOrderInvoices.findByShippedPartNo", query = "SELECT s FROM SpOrderInvoices s WHERE s.shippedPartNo = :shippedPartNo"),
    @NamedQuery(name = "SpOrderInvoices.findByQtyShipped", query = "SELECT s FROM SpOrderInvoices s WHERE s.qtyShipped = :qtyShipped"),
    //@NamedQuery(name = "SpOrderInvoices.findByPrice", query = "SELECT s FROM SpOrderInvoices s WHERE s.price = :price"),
    @NamedQuery(name = "SpOrderInvoices.findByStatus", query = "SELECT s FROM SpOrderInvoices s WHERE s.status = :status"),
    @NamedQuery(name = "SpOrderInvoices.findByLrNo", query = "SELECT s FROM SpOrderInvoices s WHERE s.lrNo = :lrNo"),
    @NamedQuery(name = "SpOrderInvoices.findByShipmentDate", query = "SELECT s FROM SpOrderInvoices s WHERE s.shipmentDate = :shipmentDate"),
    @NamedQuery(name = "SpOrderInvoices.findByTransporterName", query = "SELECT s FROM SpOrderInvoices s WHERE s.transporterName = :transporterName"),
    @NamedQuery(name = "SpOrderInvoices.findByPermitNo", query = "SELECT s FROM SpOrderInvoices s WHERE s.permitNo = :permitNo"),
    @NamedQuery(name = "SpOrderInvoices.findByIsServerSync", query = "SELECT s FROM SpOrderInvoices s WHERE s.isServerSync = :isServerSync"),
    @NamedQuery(name = "SpOrderInvoices.findByLastSyncDate", query = "SELECT s FROM SpOrderInvoices s WHERE s.lastSyncDate = :lastSyncDate")})
public class SpOrderInvoices implements Serializable {

    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected SpOrderInvoicesPK spOrderInvoicesPK;
    @Column(name = "DEALER_CODE")
    private String dealerCode;
    @Column(name = "INVOICE_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date invoiceDate;
    @Column(name = "TOTAL_INVOICE_AMOUNT")
    private BigDecimal totalInvoiceAmount;
    @Basic(optional = false)
    @Column(name = "SHIPPED_PART_NO")
    private String shippedPartNo;
    @Basic(optional = false)
    @Column(name = "QTY_SHIPPED")
    private int qtyShipped;
    //@Basic(optional = false)
    //@Column(name = "PRICE")
    //private double price;
    @Basic(optional = false)
    @Column(name = "STATUS")
    private String status;
    @Basic(optional = false)
    @Column(name = "LR_NO")
    private String lrNo;
    @Basic(optional = false)
    @Column(name = "SHIPMENT_DATE")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shipmentDate;
    @Basic(optional = false)
    @Column(name = "TRANSPORTER_NAME")
    private String transporterName;
    @Basic(optional = false)
    @Column(name = "PERMIT_NO")
    private String permitNo;
    @Basic(optional = false)
    @Column(name = "Is_ServerSync")
    private char isServerSync;
    @Column(name = "LastSyncDate")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastSyncDate;
    @Column(name = "INVOICED_RATE")
    private float invoicedRate;
    @Column(name = "DEALER_ACCEPTANCE")
    private String dealerAcceptance;
    @Column(name = "ACCEPTED_BY")
    private String acceptedBy;
    @Column(name = "ACCEPTED_ON")
    @Temporal(TemporalType.TIMESTAMP)
    private Date acceptedOn;
    @Column(name = "DEALER_REMARKS")
    private String dealerRemarks;
    @Column(name = "REMARKS")
    private String remarks;

    public SpOrderInvoices() {
    }

    public SpOrderInvoices(SpOrderInvoicesPK spOrderInvoicesPK) {
        this.spOrderInvoicesPK = spOrderInvoicesPK;
    }

    public SpOrderInvoices(SpOrderInvoicesPK spOrderInvoicesPK, String dealerCode, String shippedPartNo, int qtyShipped, double price, String status, String lrNo, Date shipmentDate, String transporterName, String permitNo, char isServerSync) {
        this.spOrderInvoicesPK = spOrderInvoicesPK;
        this.dealerCode = dealerCode;
        this.shippedPartNo = shippedPartNo;
        this.qtyShipped = qtyShipped;
        //this.price = price;
        this.status = status;
        this.lrNo = lrNo;
        this.shipmentDate = shipmentDate;
        this.transporterName = transporterName;
        this.permitNo = permitNo;
        this.isServerSync = isServerSync;
    }

    public SpOrderInvoices(String erpOrderNo, String erpPartOrderNo, String invoiceNo, String orderedPart) {
        this.spOrderInvoicesPK = new SpOrderInvoicesPK(erpOrderNo, erpPartOrderNo, invoiceNo, orderedPart);
    }

    public SpOrderInvoicesPK getSpOrderInvoicesPK() {
        return spOrderInvoicesPK;
    }

    public void setSpOrderInvoicesPK(SpOrderInvoicesPK spOrderInvoicesPK) {
        this.spOrderInvoicesPK = spOrderInvoicesPK;
    }

    public String getCustCode() {
        return dealerCode;
    }

    public void setCustCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public BigDecimal getTotalInvoiceAmount() {
        return totalInvoiceAmount;
    }

    public void setTotalInvoiceAmount(BigDecimal totalInvoiceAmount) {
        this.totalInvoiceAmount = totalInvoiceAmount;
    }

    public String getShippedPartNo() {
        return shippedPartNo;
    }

    public void setShippedPartNo(String shippedPartNo) {
        this.shippedPartNo = shippedPartNo;
    }

    public int getQtyShipped() {
        return qtyShipped;
    }

    public void setQtyShipped(int qtyShipped) {
        this.qtyShipped = qtyShipped;
    }

//    public double getPrice() {
//        return price;
//    }
//
//    public void setPrice(double price) {
//        this.price = price;
//    }
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLrNo() {
        return lrNo;
    }

    public void setLrNo(String lrNo) {
        this.lrNo = lrNo;
    }

    public Date getShipmentDate() {
        return shipmentDate;
    }

    public void setShipmentDate(Date shipmentDate) {
        this.shipmentDate = shipmentDate;
    }

    public String getTransporterName() {
        return transporterName;
    }

    public void setTransporterName(String transporterName) {
        this.transporterName = transporterName;
    }

    public String getPermitNo() {
        return permitNo;
    }

    public void setPermitNo(String permitNo) {
        this.permitNo = permitNo;
    }

    public char getIsServerSync() {
        return isServerSync;
    }

    public void setIsServerSync(char isServerSync) {
        this.isServerSync = isServerSync;
    }

    public Date getLastSyncDate() {
        return lastSyncDate;
    }

    public void setLastSyncDate(Date lastSyncDate) {
        this.lastSyncDate = lastSyncDate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (spOrderInvoicesPK != null ? spOrderInvoicesPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SpOrderInvoices)) {
            return false;
        }
        SpOrderInvoices other = (SpOrderInvoices) object;
        if ((this.spOrderInvoicesPK == null && other.spOrderInvoicesPK != null) || (this.spOrderInvoicesPK != null && !this.spOrderInvoicesPK.equals(other.spOrderInvoicesPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HibernateMapping.SpOrderInvoices[spOrderInvoicesPK=" + spOrderInvoicesPK + "]";
    }

    /**
     * @return the invoicedRade
     */
    public float getInvoicedRate() {
        return invoicedRate;
    }

    /**
     * @param invoicedRade the invoicedRade to set
     */
    public void setInvoicedRate(float invoicedRate) {
        this.invoicedRate = invoicedRate;
    }

    /**
     * @return the dealerAcceptance
     */
    public String getDealerAcceptance() {
        return dealerAcceptance;
    }

    /**
     * @param dealerAcceptance the dealerAcceptance to set
     */
    public void setDealerAcceptance(String dealerAcceptance) {
        this.dealerAcceptance = dealerAcceptance;
    }

    /**
     * @return the acceptedBy
     */
    public String getAcceptedBy() {
        return acceptedBy;
    }

    /**
     * @param acceptedBy the acceptedBy to set
     */
    public void setAcceptedBy(String acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    /**
     * @return the acceptedOn
     */
    public Date getAcceptedOn() {
        return acceptedOn;
    }

    /**
     * @param acceptedOn the acceptedOn to set
     */
    public void setAcceptedOn(Date acceptedOn) {
        this.acceptedOn = acceptedOn;
    }

    /**
     * @return the dealerRemarks
     */
    public String getDealerRemarks() {
        return dealerRemarks;
    }

    /**
     * @param dealerRemarks the dealerRemarks to set
     */
    public void setDealerRemarks(String dealerRemarks) {
        this.dealerRemarks = dealerRemarks;
    }

    /**
     * @return the remarks
     */
    public String getRemarks() {
        return remarks;
    }

    /**
     * @param remarks the remarks to set
     */
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
