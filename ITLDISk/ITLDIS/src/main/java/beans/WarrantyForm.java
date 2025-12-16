/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.apache.struts.upload.FormFile;
/**
 *
 * @author kundan.rastogi
 */
public class WarrantyForm extends org.apache.struts.action.ActionForm implements Comparable{


    private String vinid;
    private String vinNo;
    private String jobCardNo;
    private String jobCardDate;
    private String payeeName;
    private String tmsRefNo;
    private String dealer_code;
    private String customerName;
    private String customerAddress;
    private String contactNo;
    private String dealerCode;
    private String dealerName;
    private String warrantyClaimNo;
    private String claimDate;
    private String claimStatus;
    private String engineNo;
    private String modelFamily;
    private String modelFamilyDesc;
    private String modelNo;
    private String modelDesc;
    private String deliveryDate;
    private String replacementDate;
    private String failureDate;
    private String hours;
    private String dispatchFor;
    private String dispatchBy;
    private String dispatchDate;
    private String courierNo;
    private String courierName;
    private FormFile courierCopy;
    private FormFile failedPart1;
    private String failedPartFileName1;
    private FormFile failedPart2;
    private String failedPartFileName2;
    private FormFile failedPart3;
    private String failedPartFileName3;
    private FormFile failedPart4;
    private String failedPartFileName4;
    private FormFile failedPart5;
    private String failedPartFileName5;
    private String courierCopyFileName;
    private String complaintNo;
    private String partCode;
    private String partDesc;
    private String qty;
    private String ndp;
    private String claimValue;
    private String totalClaimValue;
    private String claimValueAfterDiscount;
    private String currentDate;
    private String saleDate;
    private String cmpNo;
    private String compDesc;
    private String defectCode;
    private String billID;
    private String causalOrConseq;
    private String discInPerc;
    private String jobSpareID;
    private String finalAmount;
    private String partNo;
    private String unit;
    private String unitPrice;
    private String partCategory;
    private String venderCode;
    private String[] jobSpareIDArr;
    private String[] partCategoryArr;
    private String[] cmpNoArr;
    private String[] complaintNoArr;
    private String[] causalOrConseqArr;
    private String[] partNoArr;
    private String[] partDescArr;
    private String[] qtyArr;
    private String[] unitPriceArr;
    private String[] venderCodeArr;
    private String[] claimValueArr;


    private String[] qtyApprovedArr;
    private String[] qtyRejectedArr;
    private String[] approveAmmountArr;
    private String[] remarks;
    private String[] rejectionCode;

    private String approveQty;
    private String approveAmm;
    private String rejectionDesc;
    private String remark;

    private String applessAmm;
    private String appCstVat;
    private String appHanCharge;
    private String gstOnHandling;
    private String insuranceCharges;
    private String gstOnInsurance;

    private String totalApproveAmmount;
    private String sumTotalApproveAmmount;
    private String approveAmmountAfterDiscount;
    private String cusTehsil;
    private String cusDistrict;
    private String cusState;
    private String cusPincode;

    private String dealerLocation;
    private String dealerAddress;
    private String dealerDistName;
    private String dealerStateName;
    private String dealerContactNo;

    private String fromDate;
    private String toDate;
    private String location;
    private String jobTypeId;
    private String jobTypeDesc;
    private String insDate;
    private String pdiDate;
    private String hmr;

    private String cusVerbatim;
    private String formanObser;
    private String appCode;
    private String appDesc;
    private String aggCode;
    private String aggDisc;
    private String subAggCode;
    private String subAggDesc;
    private String subAssemCode;
    private String subAssemDesc;
    private String result;
    private String complaintCode;  //same as defect code
    private String complaintDesc;   //defectDesc
    private String casulCode;
    private String casulDesc;
    private String typrId;
    private String stateID;
    private String typePercentage;
    private String typeDescription;
    private String[] lessAmmountArr;
    private String[] warClaimNoArr;
    private String[] packArr;
    private String flag;
    private String[] dispatchReqArr;
    private String[] dispatchQtyArr;
    private String[] boxNoArr;
    private String packingNo;
    private String packingDate;
    private String packingStatus;
    private int noOfClaims;
    private String userId;
    private String dispatchQty;
    private String receivedQty;
    private String[] receivedQtyArr;
    private String boxNo;
    private String receivedDate;
    private String receivedBy;

    private String dispatchStatus;
    private String[] scrapValue;
    private String[] vendorCodeAdmin;
    private String vendorCodeDesc;
    private String range;

    private String sapSyncStatus;
    private String sapCreditNo;
    private String sapCreditDate;
    private String sapCreditAmount;
    private String flagTemp;
    private String approvedDate;
    private String acknowlegdeDate;
    private Integer storeNoOfPackages;
    private String[] amountArr;
    private String[] hsnNoArr;
    private String[] dealerDiscountArr;
    private String[] taxableValueArr;
    private String[] cgstRateArr;
    private String[] cgstAmtArr;
    private String[] sgstRateArr;
    private String[] sgstAmtArr;
    private String[] ugstRateArr;
    private String[] ugstAmtArr;
    private String[] igstRateArr;
    private String[] igstAmtArr;
    private String hsnCode;
    private String amount;
    private String dealerDiscount;
    private String taxableValue;
    private String cgstRate;
    private String cgstAmt;
    private String sgstRate;
    private String sgstAmt;
    private String ugstRate;
    private String ugstAmt;
    private String igstRate;
    private String igstAmt;
    private String[] discount;
    private String qtyRejected;
    private String RejectCode;
    private String scrapVal;
    private String vendorCodeAdm;
    private String bajajPolicyNo;
    private String bajajPolicyDate;
    private String extWarrantyBookletNo;
    private String invNo;
    private String invDate;
    private String claimInvoicedAmount;
    private String stateName;
    private String totalTaxableValue;
    private String totalTaxValue;
    private String totalInvoiceAmount;
    private String[] otherPartDescArr;
    private String sapWarrantyClaimNo;
    private String status;
    private String taxInvoiceDate;
    private String taxInvoiceNo;
    private String listOfPackingNos;
    private String[] partCodeFailed;
    private String noOfClaimsPendingForCreation;
    private String noOfClaimsCreated;
    private String noOfClaimsPacked;
    private String noOfClaimsDispatched;
    private String noOfClaimsReceivedToWtyTeam;
    private String noOfClaimsApproved;
    private String noOfClaimsTaxInvoiceReceivedToWtyTeam;
    private String noOfClaimsCreditNoteIssued; 
    private String jobcardCloseDate;
    private String taxInvoiceUploadDate;
    private String taxInvoiceReceivedDate;
    private String jobCardClosureDateToClaimGenrationDate;
    private String claimGenrationDateToClaimDispatchedDate;
    private String claimReceivedDateToClaimApprovedDate;
    private String claimApprovedDateToTaxInvoiceSubmittedDate;
    private String claimInvoiceSubmittedDateToCreditNoteDate;
    private String isApproved;
    private FormFile uploadTaxInvoice;
    private String destinationpath;
    private String realPath;
    private String context;
    private String taxInvoiceStatus;
    private List<WarrantyForm> claimInvoiceList;
    private String validationMessage;
    private FormFile uploadSignedTaxInvoice;
    private String digitalSignStatus;
    private String uuid;
    
    public String getTaxInvoiceNo() {
		return taxInvoiceNo;
	}

	public void setTaxInvoiceNo(String taxInvoiceNo) {
		this.taxInvoiceNo = taxInvoiceNo;
	}

	public String getTaxInvoiceDate() {
		return taxInvoiceDate;
	}

	public void setTaxInvoiceDate(String taxInvoiceDate) {
		this.taxInvoiceDate = taxInvoiceDate;
	}

	public String[] getDiscount() {
        return discount;
    }

    public void setDiscount(String[] discount) {
        this.discount = discount;
    }
    
    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public String getGstOnHandling() {
        return gstOnHandling;
    }

    public void setGstOnHandling(String gstOnHandling) {
        this.gstOnHandling = gstOnHandling;
    }

    public String getGstOnInsurance() {
        return gstOnInsurance;
    }

    public void setGstOnInsurance(String gstOnInsurance) {
        this.gstOnInsurance = gstOnInsurance;
    }

    public String getInsuranceCharges() {
        return insuranceCharges;
    }

    public void setInsuranceCharges(String insuranceCharges) {
        this.insuranceCharges = insuranceCharges;
    }

    public String getCgstAmt() {
        return cgstAmt;
    }

    public void setCgstAmt(String cgstAmt) {
        this.cgstAmt = cgstAmt;
    }

    public String getCgstRate() {
        return cgstRate;
    }

    public void setCgstRate(String cgstRate) {
        this.cgstRate = cgstRate;
    }

    public String getIgstAmt() {
        return igstAmt;
    }

    public void setIgstAmt(String igstAmt) {
        this.igstAmt = igstAmt;
    }

    public String[] getIgstAmtArr() {
        return igstAmtArr;
    }

    public void setIgstAmtArr(String[] igstAmtArr) {
        this.igstAmtArr = igstAmtArr;
    }

    public String getIgstRate() {
        return igstRate;
    }

    public void setIgstRate(String igstRate) {
        this.igstRate = igstRate;
    }

    public String[] getIgstRateArr() {
        return igstRateArr;
    }

    public void setIgstRateArr(String[] igstRateArr) {
        this.igstRateArr = igstRateArr;
    }

    public String getSgstAmt() {
        return sgstAmt;
    }

    public void setSgstAmt(String sgstAmt) {
        this.sgstAmt = sgstAmt;
    }

    public String getSgstRate() {
        return sgstRate;
    }

    public void setSgstRate(String sgstRate) {
        this.sgstRate = sgstRate;
    }

    public String getUgstAmt() {
        return ugstAmt;
    }

    public void setUgstAmt(String ugstAmt) {
        this.ugstAmt = ugstAmt;
    }

    public String getUgstRate() {
        return ugstRate;
    }

    public void setUgstRate(String ugstRate) {
        this.ugstRate = ugstRate;
    }

    
    public String getTaxableValue() {
        return taxableValue;
    }

    public void setTaxableValue(String taxableValue) {
        this.taxableValue = taxableValue;
    }

    
    public String getDealerDiscount() {
        return dealerDiscount;
    }

    public void setDealerDiscount(String dealerDiscount) {
        this.dealerDiscount = dealerDiscount;
    }

    

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }


    public String[] getHsnNoArr() {
        return hsnNoArr;
    }

    public void setHsnNoArr(String[] hsnNoArr) {
        this.hsnNoArr = hsnNoArr;
    }


    public String[] getSgstAmtArr() {
        return sgstAmtArr;
    }

    public void setSgstAmtArr(String[] sgstAmtArr) {
        this.sgstAmtArr = sgstAmtArr;
    }

    public String[] getSgstRateArr() {
        return sgstRateArr;
    }

    public void setSgstRateArr(String[] sgstRateArr) {
        this.sgstRateArr = sgstRateArr;
    }

    public String[] getUgstAmtArr() {
        return ugstAmtArr;
    }

    public void setUgstAmtArr(String[] ugstAmtArr) {
        this.ugstAmtArr = ugstAmtArr;
    }

    public String[] getUgstRateArr() {
        return ugstRateArr;
    }

    public void setUgstRateArr(String[] ugstRateArr) {
        this.ugstRateArr = ugstRateArr;
    }

    public String[] getCgstAmtArr() {
        return cgstAmtArr;
    }

    public void setCgstAmtArr(String[] cgstAmtArr) {
        this.cgstAmtArr = cgstAmtArr;
    }

    public String[] getCgstRateArr() {
        return cgstRateArr;
    }

    public void setCgstRateArr(String[] cgstRateArr) {
        this.cgstRateArr = cgstRateArr;
    }

    public String[] getTaxableValueArr() {
        return taxableValueArr;
    }

    public void setTaxableValueArr(String[] taxableValueArr) {
        this.taxableValueArr = taxableValueArr;
    }

    public String[] getDealerDiscountArr() {
        return dealerDiscountArr;
    }

    public void setDealerDiscountArr(String[] dealerDiscountArr) {
        this.dealerDiscountArr = dealerDiscountArr;
    }

    
    public String[] getAmountArr() {
        return amountArr;
    }

    public void setAmountArr(String[] amountArr) {
        this.amountArr = amountArr;
    }

    public Integer getStoreNoOfPackages() {
        return storeNoOfPackages;
    }

    public void setStoreNoOfPackages(Integer storeNoOfPackages) {
        this.storeNoOfPackages = storeNoOfPackages;
    }

    public String getSapCreditAmount() {
        return sapCreditAmount;
    }

    public void setSapCreditAmount(String sapCreditAmount) {
        this.sapCreditAmount = sapCreditAmount;
    }

    public String getSapCreditDate() {
        return sapCreditDate;
    }

    public void setSapCreditDate(String sapCreditDate) {
        this.sapCreditDate = sapCreditDate;
    }

    public String getSapCreditNo() {
        return sapCreditNo;
    }

    public void setSapCreditNo(String sapCreditNo) {
        this.sapCreditNo = sapCreditNo;
    }

    public String getSapSyncStatus() {
        return sapSyncStatus;
    }

    public void setSapSyncStatus(String sapSyncStatus) {
        this.sapSyncStatus = sapSyncStatus;
    }

    public String getRange() {
        return range;
    }

    public void setRange(String range) {
        this.range = range;
    }
    
    public String[] getScrapValue() {
        return scrapValue;
    }

    public void setScrapValue(String[] scrapValue) {
        this.scrapValue = scrapValue;
    }

    public String[] getVendorCodeAdmin() {
        return vendorCodeAdmin;
    }

    public void setVendorCodeAdmin(String[] vendorCodeAdmin) {
        this.vendorCodeAdmin = vendorCodeAdmin;
    }
    public String getVendorCodeDesc() {
        return vendorCodeDesc;
    }
    public void setVendorCodeDesc(String vendorCodeDesc) {
        this.vendorCodeDesc = vendorCodeDesc;
    }
    public String getDispatchStatus() {
        return dispatchStatus;
    }

    public void setDispatchStatus(String dispatchStatus) {
        this.dispatchStatus = dispatchStatus;
    }


    public String getReceivedBy() {
        return receivedBy;
    }

    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    public String getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(String receivedDate) {
        this.receivedDate = receivedDate;
    }
    
    public String getDispatchQty() {
        return dispatchQty;
    }

    public void setDispatchQty(String dispatchQty) {
        this.dispatchQty = dispatchQty;
    }

    public String getBoxNo() {
        return boxNo;
    }

    public void setBoxNo(String boxNo) {
        this.boxNo = boxNo;
    }

    public String getReceivedQty() {
        return receivedQty;
    }

    public void setReceivedQty(String receivedQty) {
        this.receivedQty = receivedQty;
    }

    public String[] getReceivedQtyArr() {
        return receivedQtyArr;
    }

    public void setReceivedQtyArr(String[] receivedQtyArr) {
        this.receivedQtyArr = receivedQtyArr;
    }



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public int getNoOfClaims() {
        return noOfClaims;
    }

    public void setNoOfClaims(int noOfClaims) {
        this.noOfClaims = noOfClaims;
    }

    public String getPackingDate() {
        return packingDate;
    }

    public void setPackingDate(String packingDate) {
        this.packingDate = packingDate;
    }

    public String getPackingNo() {
        return packingNo;
    }

    public void setPackingNo(String packingNo) {
        this.packingNo = packingNo;
    }

    public String getPackingStatus() {
        return packingStatus;
    }

    public void setPackingStatus(String packingStatus) {
        this.packingStatus = packingStatus;
    }

    public String[] getBoxNoArr() {
        return boxNoArr;
    }

    public void setBoxNoArr(String[] boxNoArr) {
        this.boxNoArr = boxNoArr;
    }

    public String[] getDispatchQtyArr() {
        return dispatchQtyArr;
    }

    public void setDispatchQtyArr(String[] dispatchQtyArr) {
        this.dispatchQtyArr = dispatchQtyArr;
    }

    public String[] getDispatchReqArr() {
        return dispatchReqArr;
    }

    public void setDispatchReqArr(String[] dispatchReqArr) {
        this.dispatchReqArr = dispatchReqArr;
    }

  
    public String[] getWarClaimNoArr() {
        return warClaimNoArr;
    }

    public void setWarClaimNoArr(String[] warClaimNoArr) {
        this.warClaimNoArr = warClaimNoArr;
    }

    public String[] getPackArr() {
        return packArr;
    }

    public void setPackArr(String[] packArr) {
        this.packArr = packArr;
    }



    public String getAppCstVat() {
        return appCstVat;
    }

    public void setAppCstVat(String appCstVat) {
        this.appCstVat = appCstVat;
    }

    public String getAppHanCharge() {
        return appHanCharge;
    }

    public void setAppHanCharge(String appHanCharge) {
        this.appHanCharge = appHanCharge;
    }

    public String getApplessAmm() {
        return applessAmm;
    }

    public void setApplessAmm(String applessAmm) {
        this.applessAmm = applessAmm;
    }



    public String getApproveAmm() {
        return approveAmm;
    }

    public void setApproveAmm(String approveAmm) {
        this.approveAmm = approveAmm;
    }

    public String getApproveQty() {
        return approveQty;
    }

    public void setApproveQty(String approveQty) {
        this.approveQty = approveQty;
    }

    public String getRejectionDesc() {
        return rejectionDesc;
    }

    public void setRejectionDesc(String rejectionDesc) {
        this.rejectionDesc = rejectionDesc;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
 

    public String getStateID() {
        return stateID;
    }

    public void setStateID(String stateID) {
        this.stateID = stateID;
    }

    public String getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(String typeDescription) {
        this.typeDescription = typeDescription;
    }

    public String getTypePercentage() {
        return typePercentage;
    }

    public void setTypePercentage(String typePercentage) {
        this.typePercentage = typePercentage;
    }

   

    public String getTyprId() {
        return typrId;
    }

    public void setTyprId(String typrId) {
        this.typrId = typrId;
    }

    public String[] getLessAmmountArr() {
        return lessAmmountArr;
    }

    public void setLessAmmountArr(String[] lessAmmountArr) {
        this.lessAmmountArr = lessAmmountArr;
    }

    public String getApproveAmmountAfterDiscount() {
        return approveAmmountAfterDiscount;
    }

    public void setApproveAmmountAfterDiscount(String approveAmmountAfterDiscount) {
        this.approveAmmountAfterDiscount = approveAmmountAfterDiscount;
    }

    public String getClaimValueAfterDiscount() {
        return claimValueAfterDiscount;
    }

    public void setClaimValueAfterDiscount(String claimValueAfterDiscount) {
        this.claimValueAfterDiscount = claimValueAfterDiscount;
    }

  
    private Map<WarrantyForm, ArrayList<WarrantyForm>> dataMap;

   


    public String getTotalApproveAmmount() {
        return totalApproveAmmount;
    }

    public void setTotalApproveAmmount(String totalApproveAmmount) {
        this.totalApproveAmmount = totalApproveAmmount;
    }

    public String getSumTotalApproveAmmount() {
        return sumTotalApproveAmmount;
    }

    public void setSumTotalApproveAmmount(String sumTotalApproveAmmount) {
        this.sumTotalApproveAmmount = sumTotalApproveAmmount;
    }


    public String[] getApproveAmmountArr() {
        return approveAmmountArr;
    }

    public void setApproveAmmountArr(String[] approveAmmountArr) {
        this.approveAmmountArr = approveAmmountArr;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String[] getQtyApprovedArr() {
        return qtyApprovedArr;
    }

    public void setQtyApprovedArr(String[] qtyApprovedArr) {
        this.qtyApprovedArr = qtyApprovedArr;
    }

    public String[] getQtyRejectedArr() {
        return qtyRejectedArr;
    }

    public void setQtyRejectedArr(String[] qtyRejectedArr) {
        this.qtyRejectedArr = qtyRejectedArr;
    }



   

    public Map<WarrantyForm, ArrayList<WarrantyForm>> getDataMap() {
        return dataMap;
    }

    public void setDataMap(Map<WarrantyForm, ArrayList<WarrantyForm>> dataMap) {
        this.dataMap = dataMap;
    }

    public String[] getRejectionCode() {
        return rejectionCode;
    }

    public void setRejectionCode(String[] rejectionCode) {
        this.rejectionCode = rejectionCode;
    }

    public String[] getRemarks() {
        return remarks;
    }

    public void setRemarks(String[] remarks) {
        this.remarks = remarks;
    }

    

    

    public String getAggCode() {
        return aggCode;
    }

    public void setAggCode(String aggCode) {
        this.aggCode = aggCode;
    }

    public String getAggDisc() {
        return aggDisc;
    }

    public void setAggDisc(String aggDisc) {
        this.aggDisc = aggDisc;
    }

    public String getAppCode() {
        return appCode;
    }

    public void setAppCode(String appCode) {
        this.appCode = appCode;
    }

    public String getAppDesc() {
        return appDesc;
    }

    public void setAppDesc(String appDesc) {
        this.appDesc = appDesc;
    }

    public String getSubAssemCode() {
        return subAssemCode;
    }

    public void setSubAssemCode(String subAssemCode) {
        this.subAssemCode = subAssemCode;
    }

    public String getSubAssemDesc() {
        return subAssemDesc;
    }

    public void setSubAssemDesc(String subAssemDesc) {
        this.subAssemDesc = subAssemDesc;
    }

    public String getCasulCode() {
        return casulCode;
    }

    public void setCasulCode(String casulCode) {
        this.casulCode = casulCode;
    }

    public String getCasulDesc() {
        return casulDesc;
    }

    public void setCasulDesc(String casulDesc) {
        this.casulDesc = casulDesc;
    }

    public String getComplaintCode() {
        return complaintCode;
    }

    public void setComplaintCode(String complaintCode) {
        this.complaintCode = complaintCode;
    }

    public String getComplaintDesc() {
        return complaintDesc;
    }

    public void setComplaintDesc(String complaintDesc) {
        this.complaintDesc = complaintDesc;
    }

    public String getCusVerbatim() {
        return cusVerbatim;
    }

    public void setCusVerbatim(String cusVerbatim) {
        this.cusVerbatim = cusVerbatim;
    }

    public String getFormanObser() {
        return formanObser;
    }

    public void setFormanObser(String formanObser) {
        this.formanObser = formanObser;
    }

    public String getSubAggCode() {
        return subAggCode;
    }

    public void setSubAggCode(String subAggCode) {
        this.subAggCode = subAggCode;
    }

    public String getSubAggDesc() {
        return subAggDesc;
    }

    public void setSubAggDesc(String subAggDesc) {
        this.subAggDesc = subAggDesc;
    }

    public String getHmr() {
        return hmr;
    }

    public void setHmr(String hmr) {
        this.hmr = hmr;
    }

    public String getInsDate() {
        return insDate;
    }

    public void setInsDate(String insDate) {
        this.insDate = insDate;
    }

    public String getPdiDate() {
        return pdiDate;
    }

    public void setPdiDate(String pdiDate) {
        this.pdiDate = pdiDate;
    }

    public String getCusDistrict() {
        return cusDistrict;
    }

    public void setCusDistrict(String cusDistrict) {
        this.cusDistrict = cusDistrict;
    }

    public String getCusPincode() {
        return cusPincode;
    }

    public void setCusPincode(String cusPincode) {
        this.cusPincode = cusPincode;
    }

    public String getCusState() {
        return cusState;
    }

    public void setCusState(String cusState) {
        this.cusState = cusState;
    }

    public String getCusTehsil() {
        return cusTehsil;
    }

    public void setCusTehsil(String cusTehsil) {
        this.cusTehsil = cusTehsil;
    }

    public String getDealerAddress() {
        return dealerAddress;
    }

    public void setDealerAddress(String dealerAddress) {
        this.dealerAddress = dealerAddress;
    }

    public String getDealerContactNo() {
        return dealerContactNo;
    }

    public void setDealerContactNo(String dealerContactNo) {
        this.dealerContactNo = dealerContactNo;
    }

    public String getDealerDistName() {
        return dealerDistName;
    }

    public void setDealerDistName(String dealerDistName) {
        this.dealerDistName = dealerDistName;
    }

    public String getDealerLocation() {
        return dealerLocation;
    }

    public void setDealerLocation(String dealerLocation) {
        this.dealerLocation = dealerLocation;
    }

    public String getDealerStateName() {
        return dealerStateName;
    }

    public void setDealerStateName(String dealerStateName) {
        this.dealerStateName = dealerStateName;
    }



    public String getJobTypeDesc() {
        return jobTypeDesc;
    }

    public void setJobTypeDesc(String jobTypeDesc) {
        this.jobTypeDesc = jobTypeDesc;
    }

    public String getJobTypeId() {
        return jobTypeId;
    }

    public void setJobTypeId(String jobTypeId) {
        this.jobTypeId = jobTypeId;
    }

    
     public FormFile getFailedPart1() {
		return failedPart1;
	}

	public void setFailedPart1(FormFile failedPart1) {
		this.failedPart1 = failedPart1;
	}

	public String getFailedPartFileName1() {
		return failedPartFileName1;
	}

	public void setFailedPartFileName1(String failedPartFileName1) {
		this.failedPartFileName1 = failedPartFileName1;
	}

	public FormFile getFailedPart2() {
		return failedPart2;
	}

	public void setFailedPart2(FormFile failedPart2) {
		this.failedPart2 = failedPart2;
	}

	public String getFailedPartFileName2() {
		return failedPartFileName2;
	}

	public void setFailedPartFileName2(String failedPartFileName2) {
		this.failedPartFileName2 = failedPartFileName2;
	}

	public FormFile getFailedPart3() {
		return failedPart3;
	}

	public void setFailedPart3(FormFile failedPart3) {
		this.failedPart3 = failedPart3;
	}

	public String getFailedPartFileName3() {
		return failedPartFileName3;
	}

	public void setFailedPartFileName3(String failedPartFileName3) {
		this.failedPartFileName3 = failedPartFileName3;
	}

	public FormFile getFailedPart4() {
		return failedPart4;
	}

	public void setFailedPart4(FormFile failedPart4) {
		this.failedPart4 = failedPart4;
	}

	public String getFailedPartFileName4() {
		return failedPartFileName4;
	}

	public void setFailedPartFileName4(String failedPartFileName4) {
		this.failedPartFileName4 = failedPartFileName4;
	}

	public FormFile getFailedPart5() {
		return failedPart5;
	}

	public void setFailedPart5(FormFile failedPart5) {
		this.failedPart5 = failedPart5;
	}

	public String getFailedPartFileName5() {
		return failedPartFileName5;
	}

	public void setFailedPartFileName5(String failedPartFileName5) {
		this.failedPartFileName5 = failedPartFileName5;
	}

	public String getCourierCopyFileName() {
        return courierCopyFileName;
    }

    public void setCourierCopyFileName(String courierCopyFileName) {
        this.courierCopyFileName = courierCopyFileName;
    }


    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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


    public String getClaimStatus() {
        return claimStatus;
    }

    public void setClaimStatus(String claimStatus) {
        this.claimStatus = claimStatus;
    }

      public String getTotalClaimValue() {
        return totalClaimValue;
    }

    public void setTotalClaimValue(String totalClaimValue) {
        this.totalClaimValue = totalClaimValue;
    }

  
    public String[] getCausalOrConseqArr() {
        return causalOrConseqArr;
    }

    public void setCausalOrConseqArr(String[] causalOrConseqArr) {
        this.causalOrConseqArr = causalOrConseqArr;
    }

    public String[] getClaimValueArr() {
        return claimValueArr;
    }

    public void setClaimValueArr(String[] claimValueArr) {
        this.claimValueArr = claimValueArr;
    }

    public String[] getCmpNoArr() {
        return cmpNoArr;
    }

    public void setCmpNoArr(String[] cmpNoArr) {
        this.cmpNoArr = cmpNoArr;
    }

    public String[] getComplaintNoArr() {
        return complaintNoArr;
    }

    public void setComplaintNoArr(String[] complaintNoArr) {
        this.complaintNoArr = complaintNoArr;
    }

    public String[] getJobSpareIDArr() {
        return jobSpareIDArr;
    }

    public void setJobSpareIDArr(String[] jobSpareIDArr) {
        this.jobSpareIDArr = jobSpareIDArr;
    }

    public String[] getPartCategoryArr() {
        return partCategoryArr;
    }

    public void setPartCategoryArr(String[] partCategoryArr) {
        this.partCategoryArr = partCategoryArr;
    }

    public String[] getPartDescArr() {
        return partDescArr;
    }

    public void setPartDescArr(String[] partDescArr) {
        this.partDescArr = partDescArr;
    }

    public String[] getPartNoArr() {
        return partNoArr;
    }

    public void setPartNoArr(String[] partNoArr) {
        this.partNoArr = partNoArr;
    }

    public String[] getQtyArr() {
        return qtyArr;
    }

    public void setQtyArr(String[] qtyArr) {
        this.qtyArr = qtyArr;
    }

    public String[] getUnitPriceArr() {
        return unitPriceArr;
    }

    public void setUnitPriceArr(String[] unitPriceArr) {
        this.unitPriceArr = unitPriceArr;
    }

    public String[] getVenderCodeArr() {
        return venderCodeArr;
    }

    public void setVenderCodeArr(String[] venderCodeArr) {
        this.venderCodeArr = venderCodeArr;
    }


    public FormFile getCourierCopy() {
        return courierCopy;
    }

    public void setCourierCopy(FormFile courierCopy) {
        this.courierCopy = courierCopy;
    }

    public String getVenderCode() {
        return venderCode;
    }

    public void setVenderCode(String venderCode) {
        this.venderCode = venderCode;
    }
 

    public String getDispatchDate() {
        return dispatchDate;
    }

    public void setDispatchDate(String dispatchDate) {
        this.dispatchDate = dispatchDate;
    }


    public String getBillID() {
        return billID;
    }

    public void setBillID(String billID) {
        this.billID = billID;
    }

    public String getCausalOrConseq() {
        return causalOrConseq;
    }

    public void setCausalOrConseq(String causalOrConseq) {
        this.causalOrConseq = causalOrConseq;
    }

    public String getCmpNo() {
        return cmpNo;
    }

    public void setCmpNo(String cmpNo) {
        this.cmpNo = cmpNo;
    }

    public String getCompDesc() {
        return compDesc;
    }

    public void setCompDesc(String compDesc) {
        this.compDesc = compDesc;
    }

    public String getDefectCode() {
        return defectCode;
    }

    public void setDefectCode(String defectCode) {
        this.defectCode = defectCode;
    }

    public String getDiscInPerc() {
        return discInPerc;
    }

    public void setDiscInPerc(String discInPerc) {
        this.discInPerc = discInPerc;
    }

    public String getFinalAmount() {
        return finalAmount;
    }

    public void setFinalAmount(String finalAmount) {
        this.finalAmount = finalAmount;
    }

    public String getJobSpareID() {
        return jobSpareID;
    }

    public void setJobSpareID(String jobSpareID) {
        this.jobSpareID = jobSpareID;
    }

    public String getPartCategory() {
        return partCategory;
    }

    public void setPartCategory(String partCategory) {
        this.partCategory = partCategory;
    }

    public String getPartNo() {
        return partNo;
    }

    public void setPartNo(String partNo) {
        this.partNo = partNo;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(String unitPrice) {
        this.unitPrice = unitPrice;
    }



    public String getSaleDate() {
        return saleDate;
    }

    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }


    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }
  
     public String getVinid() {
        return vinid;
    }

    public void setVinid(String vinid) {
        this.vinid = vinid;
    }

    public String getJobCardDate() {
        return jobCardDate;
    }

    public void setJobCardDate(String jobCardDate) {
        this.jobCardDate = jobCardDate;
    }

    public String getJobCardNo() {
        return jobCardNo;
    }

    public void setJobCardNo(String jobCardNo) {
        this.jobCardNo = jobCardNo;
    }

    public String getPayeeName() {
        return payeeName;
    }

    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    public String getTmsRefNo() {
        return tmsRefNo;
    }

    public void setTmsRefNo(String tmsRefNo) {
        this.tmsRefNo = tmsRefNo;
    }

    public String getVinNo() {
        return vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public String getDealer_code() {
        return dealer_code;
    }

    public void setDealer_code(String dealer_code) {
        this.dealer_code = dealer_code;
    }

    public String getClaimDate() {
        return claimDate;
    }

    public void setClaimDate(String claimDate) {
        this.claimDate = claimDate;
    }

    public String getClaimValue() {
        return claimValue;
    }

    public void setClaimValue(String claimValue) {
        this.claimValue = claimValue;
    }

    public String getComplaintNo() {
        return complaintNo;
    }

    public void setComplaintNo(String complaintNo) {
        this.complaintNo = complaintNo;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getCourierName() {
        return courierName;
    }

    public void setCourierName(String courierName) {
        this.courierName = courierName;
    }

    public String getCourierNo() {
        return courierNo;
    }

    public void setCourierNo(String courierNo) {
        this.courierNo = courierNo;
    }

    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
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

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public String getDispatchBy() {
        return dispatchBy;
    }

    public void setDispatchBy(String dispatchBy) {
        this.dispatchBy = dispatchBy;
    }

    public String getDispatchFor() {
        return dispatchFor;
    }

    public void setDispatchFor(String dispatchFor) {
        this.dispatchFor = dispatchFor;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getFailureDate() {
        return failureDate;
    }

    public void setFailureDate(String failureDate) {
        this.failureDate = failureDate;
    }

    public String getHours() {
        return hours;
    }

    public void setHours(String hours) {
        this.hours = hours;
    }

    public String getModelDesc() {
        return modelDesc;
    }

    public void setModelDesc(String modelDesc) {
        this.modelDesc = modelDesc;
    }

    public String getModelFamily() {
        return modelFamily;
    }

    public void setModelFamily(String modelFamily) {
        this.modelFamily = modelFamily;
    }

    public String getModelFamilyDesc() {
        return modelFamilyDesc;
    }

    public void setModelFamilyDesc(String modelFamilyDesc) {
        this.modelFamilyDesc = modelFamilyDesc;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getNdp() {
        return ndp;
    }

    public void setNdp(String ndp) {
        this.ndp = ndp;
    }

    public String getPartCode() {
        return partCode;
    }

    public void setPartCode(String partCode) {
        this.partCode = partCode;
    }

    public String getPartDesc() {
        return partDesc;
    }

    public void setPartDesc(String partDesc) {
        this.partDesc = partDesc;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getReplacementDate() {
        return replacementDate;
    }

    public void setReplacementDate(String replacementDate) {
        this.replacementDate = replacementDate;
    }

    public String getWarrantyClaimNo() {
        return warrantyClaimNo;
    }

    public void setWarrantyClaimNo(String warrantyClaimNo) {
        this.warrantyClaimNo = warrantyClaimNo;
    }


   public int compareTo(Object o) {
        WarrantyForm i=(WarrantyForm)o;
        return getCmpNo().compareTo(i.getCmpNo());
    }

    /**
     * @return the flagTemp
     */
    public String getFlagTemp() {
        return flagTemp;
    }

    /**
     * @param flagTemp the flagTemp to set
     */
    public void setFlagTemp(String flagTemp) {
        this.flagTemp = flagTemp;
    }

    /**
     * @return the approvedDate
     */
    public String getApprovedDate() {
        return approvedDate;
    }

    /**
     * @param approvedDate the approvedDate to set
     */
    public void setApprovedDate(String approvedDate) {
        this.approvedDate = approvedDate;
    }

    /**
     * @return the acknowlegdeDate
     */
    public String getAcknowlegdeDate() {
        return acknowlegdeDate;
    }

    /**
     * @param acknowlegdeDate the acknowlegdeDate to set
     */
    public void setAcknowlegdeDate(String acknowlegdeDate) {
        this.acknowlegdeDate = acknowlegdeDate;
    }

    public String getQtyRejected() {
        return qtyRejected;
    }

    public void setQtyRejected(String qtyRejected) {
        this.qtyRejected = qtyRejected;
    }

    public String getRejectCode() {
        return RejectCode;
    }

    public void setRejectCode(String RejectCode) {
        this.RejectCode = RejectCode;
    }

    public String getScrapVal() {
        return scrapVal;
    }

    public void setScrapVal(String scrapVal) {
        this.scrapVal = scrapVal;
    }

    public String getVendorCodeAdm() {
        return vendorCodeAdm;
    }

    public void setVendorCodeAdm(String vendorCodeAdm) {
        this.vendorCodeAdm = vendorCodeAdm;
    }

    /**
     * @return the bajajPolicyNo
     */
    public String getBajajPolicyNo() {
        return bajajPolicyNo;
    }

    /**
     * @param bajajPolicyNo the bajajPolicyNo to set
     */
    public void setBajajPolicyNo(String bajajPolicyNo) {
        this.bajajPolicyNo = bajajPolicyNo;
    }

    /**
     * @return the bajajPolicyDate
     */
     public void settBajajPolicyDate(String bajajPolicyDate) {
        this.bajajPolicyDate = bajajPolicyDate;
    }
    public String getBajajPolicyDate() {
        return bajajPolicyDate;
    }


    /**
     * @return the extWarrantyBookletNo
     */
    public String getExtWarrantyBookletNo() {
        return extWarrantyBookletNo;
    }

    /**
     * @param extWarrantyBookletNo the extWarrantyBookletNo to set
     */
    public void setExtWarrantyBookletNo(String extWarrantyBookletNo) {
        this.extWarrantyBookletNo = extWarrantyBookletNo;
    }

    /**
     * @return the invNo
     */
    public String getInvNo() {
        return invNo;
    }

    /**
     * @param invNo the invNo to set
     */
    public void setInvNo(String invNo) {
        this.invNo = invNo;
    }

    /**
     * @return the invDate
     */
    public String getInvDate() {
        return invDate;
    }

    /**
     * @param invDate the invDate to set
     */
    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    /**
     * @return the claimInvoicedAmount
     */
    public String getClaimInvoicedAmount() {
        return claimInvoicedAmount;
    }

    /**
     * @param claimInvoicedAmount the claimInvoicedAmount to set
     */
    public void setClaimInvoicedAmount(String claimInvoicedAmount) {
        this.claimInvoicedAmount = claimInvoicedAmount;
    }

    /**
     * @return the stateName
     */
    public String getStateName() {
        return stateName;
    }

    /**
     * @param stateName the stateName to set
     */
    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    /**
     * @return the totalTaxableValue
     */
    public String getTotalTaxableValue() {
        return totalTaxableValue;
    }

    /**
     * @param totalTaxableValue the totalTaxableValue to set
     */
    public void setTotalTaxableValue(String totalTaxableValue) {
        this.totalTaxableValue = totalTaxableValue;
    }

    /**
     * @return the totalTaxValue
     */
    public String getTotalTaxValue() {
        return totalTaxValue;
    }

    /**
     * @param totalTaxValue the totalTaxValue to set
     */
    public void setTotalTaxValue(String totalTaxValue) {
        this.totalTaxValue = totalTaxValue;
    }

    /**
     * @return the totalInvoiceAmount
     */
    public String getTotalInvoiceAmount() {
        return totalInvoiceAmount;
    }

    /**
     * @param totalInvoiceAmount the totalInvoiceAmount to set
     */
    public void setTotalInvoiceAmount(String totalInvoiceAmount) {
        this.totalInvoiceAmount = totalInvoiceAmount;
    }

    /**
     * @return the otherPartDescArr
     */
    public String[] getOtherPartDescArr() {
        return otherPartDescArr;
    }

    /**
     * @param otherPartDescArr the otherPartDescArr to set
     */
    public void setOtherPartDescArr(String[] otherPartDescArr) {
        this.otherPartDescArr = otherPartDescArr;
    }

    /**
     * @return the sapWarrantyClaimNo
     */
    public String getSapWarrantyClaimNo() {
        return sapWarrantyClaimNo;
    }

    /**
     * @param sapWarrantyClaimNo the sapWarrantyClaimNo to set
     */
    public void setSapWarrantyClaimNo(String sapWarrantyClaimNo) {
        this.sapWarrantyClaimNo = sapWarrantyClaimNo;
    }

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getListOfPackingNos() {
		return listOfPackingNos;
	}

	public void setListOfPackingNos(String listOfPackingNos) {
		this.listOfPackingNos = listOfPackingNos;
	}

	public String[] getPartCodeFailed() {
		return partCodeFailed;
	}

	public void setPartCodeFailed(String[] partCodeFailed) {
		this.partCodeFailed = partCodeFailed;
	}

	public String getNoOfClaimsPendingForCreation() {
		return noOfClaimsPendingForCreation;
	}

	public void setNoOfClaimsPendingForCreation(String noOfClaimsPendingForCreation) {
		this.noOfClaimsPendingForCreation = noOfClaimsPendingForCreation;
	}

	public String getNoOfClaimsCreated() {
		return noOfClaimsCreated;
	}

	public void setNoOfClaimsCreated(String noOfClaimsCreated) {
		this.noOfClaimsCreated = noOfClaimsCreated;
	}

	public String getNoOfClaimsPacked() {
		return noOfClaimsPacked;
	}

	public void setNoOfClaimsPacked(String noOfClaimsPacked) {
		this.noOfClaimsPacked = noOfClaimsPacked;
	}

	public String getNoOfClaimsDispatched() {
		return noOfClaimsDispatched;
	}

	public void setNoOfClaimsDispatched(String noOfClaimsDispatched) {
		this.noOfClaimsDispatched = noOfClaimsDispatched;
	}

	public String getNoOfClaimsReceivedToWtyTeam() {
		return noOfClaimsReceivedToWtyTeam;
	}

	public void setNoOfClaimsReceivedToWtyTeam(String noOfClaimsReceivedToWtyTeam) {
		this.noOfClaimsReceivedToWtyTeam = noOfClaimsReceivedToWtyTeam;
	}

	public String getNoOfClaimsApproved() {
		return noOfClaimsApproved;
	}

	public void setNoOfClaimsApproved(String noOfClaimsApproved) {
		this.noOfClaimsApproved = noOfClaimsApproved;
	}

	public String getNoOfClaimsTaxInvoiceReceivedToWtyTeam() {
		return noOfClaimsTaxInvoiceReceivedToWtyTeam;
	}

	public void setNoOfClaimsTaxInvoiceReceivedToWtyTeam(String noOfClaimsTaxInvoiceReceivedToWtyTeam) {
		this.noOfClaimsTaxInvoiceReceivedToWtyTeam = noOfClaimsTaxInvoiceReceivedToWtyTeam;
	}

	public String getNoOfClaimsCreditNoteIssued() {
		return noOfClaimsCreditNoteIssued;
	}

	public void setNoOfClaimsCreditNoteIssued(String noOfClaimsCreditNoteIssued) {
		this.noOfClaimsCreditNoteIssued = noOfClaimsCreditNoteIssued;
	}

	public String getJobcardCloseDate() {
		return jobcardCloseDate;
	}

	public void setJobcardCloseDate(String jobcardCloseDate) {
		this.jobcardCloseDate = jobcardCloseDate;
	}

	public String getTaxInvoiceUploadDate() {
		return taxInvoiceUploadDate;
	}

	public void setTaxInvoiceUploadDate(String taxInvoiceUploadDate) {
		this.taxInvoiceUploadDate = taxInvoiceUploadDate;
	}

	public String getTaxInvoiceReceivedDate() {
		return taxInvoiceReceivedDate;
	}

	public void setTaxInvoiceReceivedDate(String taxInvoiceReceivedDate) {
		this.taxInvoiceReceivedDate = taxInvoiceReceivedDate;
	}

	public String getJobCardClosureDateToClaimGenrationDate() {
		return jobCardClosureDateToClaimGenrationDate;
	}

	public void setJobCardClosureDateToClaimGenrationDate(String jobCardClosureDateToClaimGenrationDate) {
		this.jobCardClosureDateToClaimGenrationDate = jobCardClosureDateToClaimGenrationDate;
	}

	public String getClaimGenrationDateToClaimDispatchedDate() {
		return claimGenrationDateToClaimDispatchedDate;
	}

	public void setClaimGenrationDateToClaimDispatchedDate(String claimGenrationDateToClaimDispatchedDate) {
		this.claimGenrationDateToClaimDispatchedDate = claimGenrationDateToClaimDispatchedDate;
	}

	public String getClaimReceivedDateToClaimApprovedDate() {
		return claimReceivedDateToClaimApprovedDate;
	}

	public void setClaimReceivedDateToClaimApprovedDate(String claimReceivedDateToClaimApprovedDate) {
		this.claimReceivedDateToClaimApprovedDate = claimReceivedDateToClaimApprovedDate;
	}

	public String getClaimApprovedDateToTaxInvoiceSubmittedDate() {
		return claimApprovedDateToTaxInvoiceSubmittedDate;
	}

	public void setClaimApprovedDateToTaxInvoiceSubmittedDate(String claimApprovedDateToTaxInvoiceSubmittedDate) {
		this.claimApprovedDateToTaxInvoiceSubmittedDate = claimApprovedDateToTaxInvoiceSubmittedDate;
	}

	public String getClaimInvoiceSubmittedDateToCreditNoteDate() {
		return claimInvoiceSubmittedDateToCreditNoteDate;
	}

	public void setClaimInvoiceSubmittedDateToCreditNoteDate(String claimInvoiceSubmittedDateToCreditNoteDate) {
		this.claimInvoiceSubmittedDateToCreditNoteDate = claimInvoiceSubmittedDateToCreditNoteDate;
	}

	public String getIsApproved() {
		return isApproved;
	}

	public void setIsApproved(String isApproved) {
		this.isApproved = isApproved;
	}

	public FormFile getUploadTaxInvoice() {
		return uploadTaxInvoice;
	}

	public void setUploadTaxInvoice(FormFile uploadTaxInvoice) {
		this.uploadTaxInvoice = uploadTaxInvoice;
	}

	public String getDestinationpath() {
		return destinationpath;
	}


	public String getRealPath() {
		return realPath;
	}

	public void setRealPath(String realPath) {
		this.realPath = realPath;
	}

	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public void setDestinationpath(String destinationpath) {
		this.destinationpath = destinationpath;
	}

	public String getTaxInvoiceStatus() {
		return taxInvoiceStatus;
	}

	public void setTaxInvoiceStatus(String taxInvoiceStatus) {
		this.taxInvoiceStatus = taxInvoiceStatus;
	}

	public List<WarrantyForm> getClaimInvoiceList() {
		return claimInvoiceList;
	}

	public void setClaimInvoiceList(List<WarrantyForm> claimInvoiceList) {
		this.claimInvoiceList = claimInvoiceList;
	}

	public String getValidationMessage() {
		return validationMessage;
	}

	public void setValidationMessage(String validationMessage) {
		this.validationMessage = validationMessage;
	}

	public FormFile getUploadSignedTaxInvoice() {
		return uploadSignedTaxInvoice;
	}

	public void setUploadSignedTaxInvoice(FormFile uploadSignedTaxInvoice) {
		this.uploadSignedTaxInvoice = uploadSignedTaxInvoice;
	}

	public String getDigitalSignStatus() {
		return digitalSignStatus;
	}

	public void setDigitalSignStatus(String digitalSignStatus) {
		this.digitalSignStatus = digitalSignStatus;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	@Override
	public String toString() {
		return "WarrantyForm [vinid=" + vinid + ", vinNo=" + vinNo + ", jobCardNo=" + jobCardNo + ", jobCardDate="
				+ jobCardDate + ", payeeName=" + payeeName + ", tmsRefNo=" + tmsRefNo + ", dealer_code=" + dealer_code
				+ ", customerName=" + customerName + ", customerAddress=" + customerAddress + ", contactNo=" + contactNo
				+ ", dealerCode=" + dealerCode + ", dealerName=" + dealerName + ", warrantyClaimNo=" + warrantyClaimNo
				+ ", claimDate=" + claimDate + ", claimStatus=" + claimStatus + ", engineNo=" + engineNo
				+ ", modelFamily=" + modelFamily + ", modelFamilyDesc=" + modelFamilyDesc + ", modelNo=" + modelNo
				+ ", modelDesc=" + modelDesc + ", deliveryDate=" + deliveryDate + ", replacementDate=" + replacementDate
				+ ", failureDate=" + failureDate + ", hours=" + hours + ", dispatchFor=" + dispatchFor + ", dispatchBy="
				+ dispatchBy + ", dispatchDate=" + dispatchDate + ", courierNo=" + courierNo + ", courierName="
				+ courierName + ", courierCopy=" + courierCopy + ", failedPart1=" + failedPart1
				+ ", failedPartFileName1=" + failedPartFileName1 + ", failedPart2=" + failedPart2
				+ ", failedPartFileName2=" + failedPartFileName2 + ", failedPart3=" + failedPart3
				+ ", failedPartFileName3=" + failedPartFileName3 + ", failedPart4=" + failedPart4
				+ ", failedPartFileName4=" + failedPartFileName4 + ", failedPart5=" + failedPart5
				+ ", failedPartFileName5=" + failedPartFileName5 + ", courierCopyFileName=" + courierCopyFileName
				+ ", complaintNo=" + complaintNo + ", partCode=" + partCode + ", partDesc=" + partDesc + ", qty=" + qty
				+ ", ndp=" + ndp + ", claimValue=" + claimValue + ", totalClaimValue=" + totalClaimValue
				+ ", claimValueAfterDiscount=" + claimValueAfterDiscount + ", currentDate=" + currentDate
				+ ", saleDate=" + saleDate + ", cmpNo=" + cmpNo + ", compDesc=" + compDesc + ", defectCode="
				+ defectCode + ", billID=" + billID + ", causalOrConseq=" + causalOrConseq + ", discInPerc="
				+ discInPerc + ", jobSpareID=" + jobSpareID + ", finalAmount=" + finalAmount + ", partNo=" + partNo
				+ ", unit=" + unit + ", unitPrice=" + unitPrice + ", partCategory=" + partCategory + ", venderCode="
				+ venderCode + ", jobSpareIDArr=" + Arrays.toString(jobSpareIDArr) + ", partCategoryArr="
				+ Arrays.toString(partCategoryArr) + ", cmpNoArr=" + Arrays.toString(cmpNoArr) + ", complaintNoArr="
				+ Arrays.toString(complaintNoArr) + ", causalOrConseqArr=" + Arrays.toString(causalOrConseqArr)
				+ ", partNoArr=" + Arrays.toString(partNoArr) + ", partDescArr=" + Arrays.toString(partDescArr)
				+ ", qtyArr=" + Arrays.toString(qtyArr) + ", unitPriceArr=" + Arrays.toString(unitPriceArr)
				+ ", venderCodeArr=" + Arrays.toString(venderCodeArr) + ", claimValueArr="
				+ Arrays.toString(claimValueArr) + ", qtyApprovedArr=" + Arrays.toString(qtyApprovedArr)
				+ ", qtyRejectedArr=" + Arrays.toString(qtyRejectedArr) + ", approveAmmountArr="
				+ Arrays.toString(approveAmmountArr) + ", remarks=" + Arrays.toString(remarks) + ", rejectionCode="
				+ Arrays.toString(rejectionCode) + ", approveQty=" + approveQty + ", approveAmm=" + approveAmm
				+ ", rejectionDesc=" + rejectionDesc + ", remark=" + remark + ", applessAmm=" + applessAmm
				+ ", appCstVat=" + appCstVat + ", appHanCharge=" + appHanCharge + ", gstOnHandling=" + gstOnHandling
				+ ", insuranceCharges=" + insuranceCharges + ", gstOnInsurance=" + gstOnInsurance
				+ ", totalApproveAmmount=" + totalApproveAmmount + ", sumTotalApproveAmmount=" + sumTotalApproveAmmount
				+ ", approveAmmountAfterDiscount=" + approveAmmountAfterDiscount + ", cusTehsil=" + cusTehsil
				+ ", cusDistrict=" + cusDistrict + ", cusState=" + cusState + ", cusPincode=" + cusPincode
				+ ", dealerLocation=" + dealerLocation + ", dealerAddress=" + dealerAddress + ", dealerDistName="
				+ dealerDistName + ", dealerStateName=" + dealerStateName + ", dealerContactNo=" + dealerContactNo
				+ ", fromDate=" + fromDate + ", toDate=" + toDate + ", location=" + location + ", jobTypeId="
				+ jobTypeId + ", jobTypeDesc=" + jobTypeDesc + ", insDate=" + insDate + ", pdiDate=" + pdiDate
				+ ", hmr=" + hmr + ", cusVerbatim=" + cusVerbatim + ", formanObser=" + formanObser + ", appCode="
				+ appCode + ", appDesc=" + appDesc + ", aggCode=" + aggCode + ", aggDisc=" + aggDisc + ", subAggCode="
				+ subAggCode + ", subAggDesc=" + subAggDesc + ", subAssemCode=" + subAssemCode + ", subAssemDesc="
				+ subAssemDesc + ", result=" + result + ", complaintCode=" + complaintCode + ", complaintDesc="
				+ complaintDesc + ", casulCode=" + casulCode + ", casulDesc=" + casulDesc + ", typrId=" + typrId
				+ ", stateID=" + stateID + ", typePercentage=" + typePercentage + ", typeDescription=" + typeDescription
				+ ", lessAmmountArr=" + Arrays.toString(lessAmmountArr) + ", warClaimNoArr="
				+ Arrays.toString(warClaimNoArr) + ", packArr=" + Arrays.toString(packArr) + ", flag=" + flag
				+ ", dispatchReqArr=" + Arrays.toString(dispatchReqArr) + ", dispatchQtyArr="
				+ Arrays.toString(dispatchQtyArr) + ", boxNoArr=" + Arrays.toString(boxNoArr) + ", packingNo="
				+ packingNo + ", packingDate=" + packingDate + ", packingStatus=" + packingStatus + ", noOfClaims="
				+ noOfClaims + ", userId=" + userId + ", dispatchQty=" + dispatchQty + ", receivedQty=" + receivedQty
				+ ", receivedQtyArr=" + Arrays.toString(receivedQtyArr) + ", boxNo=" + boxNo + ", receivedDate="
				+ receivedDate + ", receivedBy=" + receivedBy + ", dispatchStatus=" + dispatchStatus + ", scrapValue="
				+ Arrays.toString(scrapValue) + ", vendorCodeAdmin=" + Arrays.toString(vendorCodeAdmin)
				+ ", vendorCodeDesc=" + vendorCodeDesc + ", range=" + range + ", sapSyncStatus=" + sapSyncStatus
				+ ", sapCreditNo=" + sapCreditNo + ", sapCreditDate=" + sapCreditDate + ", sapCreditAmount="
				+ sapCreditAmount + ", flagTemp=" + flagTemp + ", approvedDate=" + approvedDate + ", acknowlegdeDate="
				+ acknowlegdeDate + ", storeNoOfPackages=" + storeNoOfPackages + ", amountArr="
				+ Arrays.toString(amountArr) + ", hsnNoArr=" + Arrays.toString(hsnNoArr) + ", dealerDiscountArr="
				+ Arrays.toString(dealerDiscountArr) + ", taxableValueArr=" + Arrays.toString(taxableValueArr)
				+ ", cgstRateArr=" + Arrays.toString(cgstRateArr) + ", cgstAmtArr=" + Arrays.toString(cgstAmtArr)
				+ ", sgstRateArr=" + Arrays.toString(sgstRateArr) + ", sgstAmtArr=" + Arrays.toString(sgstAmtArr)
				+ ", ugstRateArr=" + Arrays.toString(ugstRateArr) + ", ugstAmtArr=" + Arrays.toString(ugstAmtArr)
				+ ", igstRateArr=" + Arrays.toString(igstRateArr) + ", igstAmtArr=" + Arrays.toString(igstAmtArr)
				+ ", hsnCode=" + hsnCode + ", amount=" + amount + ", dealerDiscount=" + dealerDiscount
				+ ", taxableValue=" + taxableValue + ", cgstRate=" + cgstRate + ", cgstAmt=" + cgstAmt + ", sgstRate="
				+ sgstRate + ", sgstAmt=" + sgstAmt + ", ugstRate=" + ugstRate + ", ugstAmt=" + ugstAmt + ", igstRate="
				+ igstRate + ", igstAmt=" + igstAmt + ", discount=" + Arrays.toString(discount) + ", qtyRejected="
				+ qtyRejected + ", RejectCode=" + RejectCode + ", scrapVal=" + scrapVal + ", vendorCodeAdm="
				+ vendorCodeAdm + ", bajajPolicyNo=" + bajajPolicyNo + ", bajajPolicyDate=" + bajajPolicyDate
				+ ", extWarrantyBookletNo=" + extWarrantyBookletNo + ", invNo=" + invNo + ", invDate=" + invDate
				+ ", claimInvoicedAmount=" + claimInvoicedAmount + ", stateName=" + stateName + ", totalTaxableValue="
				+ totalTaxableValue + ", totalTaxValue=" + totalTaxValue + ", totalInvoiceAmount=" + totalInvoiceAmount
				+ ", otherPartDescArr=" + Arrays.toString(otherPartDescArr) + ", sapWarrantyClaimNo="
				+ sapWarrantyClaimNo + ", status=" + status + ", taxInvoiceDate=" + taxInvoiceDate + ", taxInvoiceNo="
				+ taxInvoiceNo + ", listOfPackingNos=" + listOfPackingNos + ", partCodeFailed="
				+ Arrays.toString(partCodeFailed) + ", noOfClaimsPendingForCreation=" + noOfClaimsPendingForCreation
				+ ", noOfClaimsCreated=" + noOfClaimsCreated + ", noOfClaimsPacked=" + noOfClaimsPacked
				+ ", noOfClaimsDispatched=" + noOfClaimsDispatched + ", noOfClaimsReceivedToWtyTeam="
				+ noOfClaimsReceivedToWtyTeam + ", noOfClaimsApproved=" + noOfClaimsApproved
				+ ", noOfClaimsTaxInvoiceReceivedToWtyTeam=" + noOfClaimsTaxInvoiceReceivedToWtyTeam
				+ ", noOfClaimsCreditNoteIssued=" + noOfClaimsCreditNoteIssued + ", jobcardCloseDate="
				+ jobcardCloseDate + ", taxInvoiceUploadDate=" + taxInvoiceUploadDate + ", taxInvoiceReceivedDate="
				+ taxInvoiceReceivedDate + ", jobCardClosureDateToClaimGenrationDate="
				+ jobCardClosureDateToClaimGenrationDate + ", claimGenrationDateToClaimDispatchedDate="
				+ claimGenrationDateToClaimDispatchedDate + ", claimReceivedDateToClaimApprovedDate="
				+ claimReceivedDateToClaimApprovedDate + ", claimApprovedDateToTaxInvoiceSubmittedDate="
				+ claimApprovedDateToTaxInvoiceSubmittedDate + ", claimInvoiceSubmittedDateToCreditNoteDate="
				+ claimInvoiceSubmittedDateToCreditNoteDate + ", isApproved=" + isApproved + ", uploadTaxInvoice="
				+ uploadTaxInvoice + ", destinationpath=" + destinationpath + ", realPath=" + realPath + ", context="
				+ context + ", taxInvoiceStatus=" + taxInvoiceStatus + ", claimInvoiceList=" + claimInvoiceList
				+ ", validationMessage=" + validationMessage + ", uploadSignedTaxInvoice=" + uploadSignedTaxInvoice
				+ ", digitalSignStatus=" + digitalSignStatus + ", uuid=" + uuid + ", dataMap=" + dataMap + "]";
	}
	
	
	
	
	
	
	
	
	
}
