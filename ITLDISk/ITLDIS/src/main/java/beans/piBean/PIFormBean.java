/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans.piBean;

import java.math.BigInteger;
import java.util.LinkedHashSet;
import java.util.TreeMap;

import org.apache.struts.action.ActionForm;
import org.apache.struts.util.LabelValueBean;

/**
 *
 * @author yogasmita.patel
 */
public class PIFormBean extends ActionForm {

    private String dealerCode;
    private Long poId;
    private Long piId;
    private Long[] poIdList;
    private BigInteger[] piIdList;
    private String dealerName;
    private String orderType;
    private String[] checkedPOList;
    private String poNo;
    private String partNo;
    private String partDesc;
    private Integer orderedQty;
    private int pendingQty;
    private LinkedHashSet<LabelValueBean> dealerCodeList;
    private String custPoDate;
    private String consigneeCode;
    private String consigneeName;
    private String consigneeCountry;
    private String dischargePort;
    private String destinationPlace;
    private double price;
    private double totalPrice;
    private int piQty;
    private Integer lineItem;
    private String status;
    private String leadTime;
    private String engineNo;
    private String chassisNo;
    private String modelNo;
    private String firNo;
    private String deliveryDesc;
    private String totalAmount;
    private String specInstr;
    private String erpRemarks;
    private String dealerRefNo;
    private String comments;
    private String paymentTerms;
    private String incoTerms;
    private String precarriageBy;
    private String placePreCarrier;
    private String deliveryCode;
    private Float totalValue;
    private String[] orderPartNoList;
    private String[] leadTimeList;
    private Integer[] avlQtyList;
    private String[] avlPartList;
    private Integer[] piQtyList;
    private Float[] priceList;
    private String[] statusList;
    private Integer bookesQty;
    private Integer[] pendingQtyList;
    private Integer[] finalQtyList;
    private String[] poDetailIdList;
    private String[] otherchargeType;
    private String[] otherChargeList;
    private String piNo;
    private String piDate;
    private String fromDate;
    private String toDate;
    private int acceptPiQty;
    private int finalPiQty;
    private String buyerEditAllowed;
    private String buyer;
    private String range;
    private TreeMap<LabelValueBean,Float> otherCharges;
    private String availablePartNoPI;
    private String avlPartDescPI;
    private String location;
    private int noOfOrder;

    private String invoiceNo;
    private String invoiceDate;
    private String dispatchMode;
    private String awbBolNo;
    private String awbBolDate;
    private String erpOrderNo;
    private String dmsPINo;
    private String orderedPart;
    private String shippedPartNo;
    private String partDescInvoice;
    private String statusInvoice;
    private String remarkInvoice;
    private int qtyShipped;
    private double partPriceInvoice;
    private Float inspChargeInv;
    private Float localTPTInv;
    private Float insurnceChargeInv;
    private Float freightChargeInv;
    private Float otherChargeInv;
    private String finalAmount;
    private String userId;
    private String priceListCode;
    private String currency;
    private String erpOrderDate;

    public int getAcceptPiQty() {
        return acceptPiQty;
    }


    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }

    public void setAcceptPiQty(int acceptPiQty) {
        this.acceptPiQty = acceptPiQty;
    }

    public int getFinalPiQty() {
        return finalPiQty;
    }

    public void setFinalPiQty(int finalPiQty) {
        this.finalPiQty = finalPiQty;
    }

    public String getBuyer() {
        return buyer;
    }

    public void setBuyer(String buyer) {
        this.buyer = buyer;
    }

    public String getDealerCode() {
        return dealerCode;
    }

    public void setDealerCode(String dealerCode) {
        this.dealerCode = dealerCode;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String[] getCheckedPOList() {
        return checkedPOList;
    }

    public void setCheckedPOList(String[] checkedPOList) {
        this.checkedPOList = checkedPOList;
    }

    public Integer getOrderedQty() {
        return orderedQty;
    }

    public void setOrderedQty(Integer orderedQty) {
        this.orderedQty = orderedQty;
    }

    public int getPendingQty() {
        return pendingQty;
    }

    public void setPendingQty(int pendingQty) {
        this.pendingQty = pendingQty;
    }

    public String getPartDesc() {
        return partDesc;
    }

    public void setPartDesc(String partDesc) {
        this.partDesc = partDesc;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getPoNo() {
        return poNo;
    }

    public void setPoNo(String poNo) {
        this.poNo = poNo;
    }

    public LinkedHashSet<LabelValueBean> getDealerCodeList() {
        return dealerCodeList;
    }

    public void setDealerCodeList(LinkedHashSet<LabelValueBean> dealerCodeList) {
        this.dealerCodeList = dealerCodeList;
    }

    public String getConsigneeCode() {
        return consigneeCode;
    }

    public void setConsigneeCode(String consigneeCode) {
        this.consigneeCode = consigneeCode;
    }

    public String getConsigneeCountry() {
        return consigneeCountry;
    }

    public void setConsigneeCountry(String consigneeCountry) {
        this.consigneeCountry = consigneeCountry;
    }

    public String getConsigneeName() {
        return consigneeName;
    }

    public void setConsigneeName(String consigneeName) {
        this.consigneeName = consigneeName;
    }

    public String getCustPoDate() {
        return custPoDate;
    }

    public void setCustPoDate(String custPoDate) {
        this.custPoDate = custPoDate;
    }

    public String getDestinationPlace() {
        return destinationPlace;
    }

    public void setDestinationPlace(String destinationPlace) {
        this.destinationPlace = destinationPlace;
    }

    public String getDischargePort() {
        return dischargePort;
    }

    public void setDischargePort(String dischargePort) {
        this.dischargePort = dischargePort;
    }

    public int getPiQty() {
        return piQty;
    }

    public void setPiQty(int piQty) {
        this.piQty = piQty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getLineItem() {
        return lineItem;
    }

    public void setLineItem(Integer lineItem) {
        this.lineItem = lineItem;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getLeadTime() {
        return leadTime;
    }

    public void setLeadTime(String leadTime) {
        this.leadTime = leadTime;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getDealerRefNo() {
        return dealerRefNo;
    }

    public void setDealerRefNo(String dealerRefNo) {
        this.dealerRefNo = dealerRefNo;
    }

    public String getDeliveryDesc() {
        return deliveryDesc;
    }

    public void setDeliveryDesc(String deliveryDesc) {
        this.deliveryDesc = deliveryDesc;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getErpRemarks() {
        return erpRemarks;
    }

    public void setErpRemarks(String erpRemarks) {
        this.erpRemarks = erpRemarks;
    }

    public String getFirNo() {
        return firNo;
    }

    public void setFirNo(String firNo) {
        this.firNo = firNo;
    }

    public String getIncoTerms() {
        return incoTerms;
    }

    public void setIncoTerms(String incoTerms) {
        this.incoTerms = incoTerms;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getPlacePreCarrier() {
        return placePreCarrier;
    }

    public void setPlacePreCarrier(String placePreCarrier) {
        this.placePreCarrier = placePreCarrier;
    }

    public String getPrecarriageBy() {
        return precarriageBy;
    }

    public void setPrecarriageBy(String precarriageBy) {
        this.precarriageBy = precarriageBy;
    }

    public String getSpecInstr() {
        return specInstr;
    }

    public void setSpecInstr(String specInstr) {
        this.specInstr = specInstr;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getDeliveryCode() {
        return deliveryCode;
    }

    public void setDeliveryCode(String deliveryCode) {
        this.deliveryCode = deliveryCode;
    }

    public Float getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(Float totalValue) {
        this.totalValue = totalValue;
    }

    public Integer[] getAvlQtyList() {
        return avlQtyList;
    }

    public void setAvlQtyList(Integer[] avlQtyList) {
        this.avlQtyList = avlQtyList;
    }

    public String[] getLeadTimeList() {
        return leadTimeList;
    }

    public void setLeadTimeList(String[] leadTimeList) {
        this.leadTimeList = leadTimeList;
    }

    public String[] getOrderPartNoList() {
        return orderPartNoList;
    }

    public void setOrderPartNoList(String[] orderPartNoList) {
        this.orderPartNoList = orderPartNoList;
    }

    public String[] getPoDetailIdList() {
        return poDetailIdList;
    }

    public void setPoDetailIdList(String[] poDetailIdList) {
        this.poDetailIdList = poDetailIdList;
    }

    public Float[] getPriceList() {
        return priceList;
    }

    public void setPriceList(Float[] priceList) {
        this.priceList = priceList;
    }

    public String[] getStatusList() {
        return statusList;
    }

    public void setStatusList(String[] statusList) {
        this.statusList = statusList;
    }

    public Long getPoId() {
        return poId;
    }

    public void setPoId(Long poId) {
        this.poId = poId;
    }

    public String[] getOtherChargeList() {
        return otherChargeList;
    }

    public void setOtherChargeList(String[] otherChargeList) {
        this.otherChargeList = otherChargeList;
    }

    public String[] getOtherchargeType() {
        return otherchargeType;
    }

    public void setOtherchargeType(String[] otherchargeType) {
        this.otherchargeType = otherchargeType;
    }

    public String getPiDate() {
        return piDate;
    }

    public void setPiDate(String piDate) {
        this.piDate = piDate;
    }

    public String getPiNo() {
        return piNo;
    }

    public void setPiNo(String piNo) {
        this.piNo = piNo;
    }

    public String getFromDate() {
        return fromDate;
    }

    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }

    public String getToDate() {
        return toDate;
    }

    public void setToDate(String toDate) {
        this.toDate = toDate;
    }

    public Integer[] getPiQtyList() {
        return piQtyList;
    }

    public void setPiQtyList(Integer[] piQtyList) {
        this.piQtyList = piQtyList;
    }

    public Long[] getPoIdList() {
        return poIdList;
    }

    public void setPoIdList(Long[] poIdList) {
        this.poIdList = poIdList;
    }

    public TreeMap<LabelValueBean, Float> getOtherCharges() {
        return otherCharges;
    }

    public void setOtherCharges(TreeMap<LabelValueBean, Float> otherCharges) {
        this.otherCharges = otherCharges;
    }

    public String getAvailablePartNoPI() {
        return availablePartNoPI;
    }

    public void setAvailablePartNoPI(String availablePartNoPI) {
        this.availablePartNoPI = availablePartNoPI;
    }

    public String getAvlPartDescPI() {
        return avlPartDescPI;
    }

    public void setAvlPartDescPI(String avlPartDescPI) {
        this.avlPartDescPI = avlPartDescPI;
    }

    public String[] getAvlPartList() {
        return avlPartList;
    }

    public void setAvlPartList(String[] avlPartList) {
        this.avlPartList = avlPartList;
    }

    public String getBuyerEditAllowed() {
        return buyerEditAllowed;
    }

    public void setBuyerEditAllowed(String buyerEditAllowed) {
        this.buyerEditAllowed = buyerEditAllowed;
    }

    public Long getPiId() {
        return piId;
    }

    public void setPiId(Long piId) {
        this.piId = piId;
    }

    public BigInteger[] getPiIdList() {
        return piIdList;
    }

    public void setPiIdList(BigInteger[] piIdList) {
        this.piIdList = piIdList;
    }

    public Integer[] getFinalQtyList() {
        return finalQtyList;
    }

    public void setFinalQtyList(Integer[] finalQtyList) {
        this.finalQtyList = finalQtyList;
    }

    public Integer[] getPendingQtyList() {
        return pendingQtyList;
    }

    public void setPendingQtyList(Integer[] pendingQtyList) {
        this.pendingQtyList = pendingQtyList;
    }

    public Integer getBookesQty() {
        return bookesQty;
    }

    public void setBookesQty(Integer bookesQty) {
        this.bookesQty = bookesQty;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getNoOfOrder() {
        return noOfOrder;
    }

    public void setNoOfOrder(int noOfOrder) {
        this.noOfOrder = noOfOrder;
    }

    public String getAwbBolDate() {
        return awbBolDate;
    }

    public void setAwbBolDate(String awbBolDate) {
        this.awbBolDate = awbBolDate;
    }

    public String getAwbBolNo() {
        return awbBolNo;
    }

    public void setAwbBolNo(String awbBolNo) {
        this.awbBolNo = awbBolNo;
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

    public String getErpOrderNo() {
        return erpOrderNo;
    }

    public void setErpOrderNo(String erpOrderNo) {
        this.erpOrderNo = erpOrderNo;
    }

    public Float getFreightChargeInv() {
        return freightChargeInv;
    }

    public void setFreightChargeInv(Float freightChargeInv) {
        this.freightChargeInv = freightChargeInv;
    }

    public Float getInspChargeInv() {
        return inspChargeInv;
    }

    public void setInspChargeInv(Float inspChargeInv) {
        this.inspChargeInv = inspChargeInv;
    }

    public Float getInsurnceChargeInv() {
        return insurnceChargeInv;
    }

    public void setInsurnceChargeInv(Float insurnceChargeInv) {
        this.insurnceChargeInv = insurnceChargeInv;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public Float getLocalTPTInv() {
        return localTPTInv;
    }

    public void setLocalTPTInv(Float localTPTInv) {
        this.localTPTInv = localTPTInv;
    }

    public String getOrderedPart() {
        return orderedPart;
    }

    public void setOrderedPart(String orderedPart) {
        this.orderedPart = orderedPart;
    }

    public Float getOtherChargeInv() {
        return otherChargeInv;
    }

    public void setOtherChargeInv(Float otherChargeInv) {
        this.otherChargeInv = otherChargeInv;
    }

    public String getPartDescInvoice() {
        return partDescInvoice;
    }

    public void setPartDescInvoice(String partDescInvoice) {
        this.partDescInvoice = partDescInvoice;
    }

    public double getPartPriceInvoice() {
        return partPriceInvoice;
    }

    public void setPartPriceInvoice(double partPriceInvoice) {
        this.partPriceInvoice = partPriceInvoice;
    }

    public int getQtyShipped() {
        return qtyShipped;
    }

    public void setQtyShipped(int qtyShipped) {
        this.qtyShipped = qtyShipped;
    }

    public String getRemarkInvoice() {
        return remarkInvoice;
    }

    public void setRemarkInvoice(String remarkInvoice) {
        this.remarkInvoice = remarkInvoice;
    }

    public String getShippedPartNo() {
        return shippedPartNo;
    }

    public void setShippedPartNo(String shippedPartNo) {
        this.shippedPartNo = shippedPartNo;
    }

    public String getStatusInvoice() {
        return statusInvoice;
    }

    public void setStatusInvoice(String statusInvoice) {
        this.statusInvoice = statusInvoice;
    }

    public String getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(String finalAmount) {
        this.finalAmount = finalAmount;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getPriceListCode() {
        return priceListCode;
    }

    public void setPriceListCode(String priceListCode) {
        this.priceListCode = priceListCode;
    }

    public String getErpOrderDate() {
        return erpOrderDate;
    }

    public void setErpOrderDate(String erpOrderDate) {
        this.erpOrderDate = erpOrderDate;
    }

    
}    
