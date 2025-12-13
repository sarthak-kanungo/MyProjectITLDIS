/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;


import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;

import HibernateMapping.SpOrderInvGrn;

/**
 *
 * @author vandana.singla
 */
public class serviceForm extends ManageCustomerForm {

    //Vehicle Information
    private FormFile file;
    private String pathName;
    private String jobType;
    private String jobTypeDesc;
    private String jobLocation;
    private String nextService;
    private String jobCardStatus;
    private String ownerDriven;
    private String serviceType;
    private String vinNo;
    private String jobCardNo;
    private String modelFamily;
    private String engineNumber;
    private String dealerJobCardNo;
    private String modelCode;
    private String saleDate;
    private String saleDatestr;
    private String modelFamilyDesc;
    private String registrationNo;
    private String hmr;
    private String modelCodeDesc;
    private String serviceBookletNo;
    private String jobCardDate;
    private String jobCardDateStr;
    private String keyIdentificationNo;
    private String warrantyApplicable;
    // end
    //customer Info
    private String customerName;
    private String payeeName; 
    private String village;
    private String payeeVillage;
    private String mobilePhone;
    private String payeeMobilePhone;
    private String taluka; 
    private String payeeTaluka;
    private String landline;
    private String payeeLandline;
    private String tehsil;
    private String payeeTehsil;
    private String emailId;
    private String payeeEmailId;
    private String district;//customerName,payeeName,village,payeeVillage,mobilePhone,payeeMobilePhone,taluka,payeeTaluka,landline,payeeLandline,tehsil,payeeTehsil,emailId,payeeEmailId,district,payeeDistrict,pinCode,payeePinCode,state,payeeState,country,payeeCountry
    private String payeeDistrict;
    private String pinCode;
    private String payeePinCode;
    private String gstCode;
    private String state;
    private String payeeState;
    private String country;
    private String payeeCountry;
    //Complaint
    private String stndprt;
       
    private String constantValue;
    private ArrayList prtParam;
    private String formName;
    private String formId;
    private ArrayList<LabelValueBean> labelList;
    private Map<ContentFormBean, ArrayList<SubContentFormBean>> dataMap;
    private String compVerbatim[];
    private String applicationCode[];
    private String compCode[];
    private String aggCode[];
    private String causalCode[];
    private String subaggCode[];
    private String consequenceCode1[];
    private String consequenceCode2[];
    private String consequenceCode3[];
    private String consequenceCode4[];
    private String consequenceCode5[];  
    private String consequenceCode6[];
    private String consequenceCode7[];
    private String consequenceCode8[];
    private String consequenceCode9[];
    private String consequenceCode10[];
    private String vendorCode[]; 
    private String foremanObservation[];
    private String workStarted;
    private String workStartedHrs;
    private String workStartedMins;
    private String workFinished;
    private String workFinishedHrs;
    private String workFinishedMins; 
    private String bayCode;
    private String mechanicName;
    private String inspectionBy;
    private String vehicleOut;
    private String vehicleOutHrs;
    private String vehicleOutMins;
    private String compid;
    private String subassemblyCode[];
    
    private String[] discount_perc;
    private String[] discount_amt;
    private String[] partNo;
    private String[] partDesc;
    private String[] unitPrice;
    private String[] quantityS;
    private String[] partPriceAmount;
    private String[] billTo;
    private String[] billCode;
    private String[] warranty;
    private String[] finalAmount;
    private String[] lubesNo;
    private String[] lubesDesc;
    private String[] lubesUnitPrice;
    private String[] lubesQuantityS;
    private String[] lubesPriceAmount;
    private String[] lubesBillCode;
    private String[] lubesWarranty;
    private String[] lubesFinalAmount;
    private String[] complaintCode;
    private String[] actionCode;
    private String[] actionTaken;
    private String[] labourChargesAmount;
    private String[] workCode;
    private String[] workDescription;
    private String[] workAmount;
    private String[] complaint_Code;
    private String[] lubescomplaint_Code;
    private String[] causal_Code;
    private String[] lubescausal_Code;

    private String totalPartsValue;
    private String totalLubesValue;
    private String totalLabourCharges;
    private String totalOtherCharges;
    private String totalEstimate;
    private String promised;
    private String promisedstr;
    private String promisedTime;
    private String promisedHrs;
    private String requiredBYCustomer;
    private String requiredBYCustomerstr;
    private String requiredBYCustomerTime;
    private String requiredBYCustomerHrs;
    private String currentEstimate;
    private String currentEstimatestr;
    private String currentEstimateTime;
    private String currentEstimateHrs;
    private String[] comptype;
    private String[] lubesComptype;
    private String productionCategory_Desc;
    private String couponno;
    private String jobcardpriority;
    private String jobId;
    private String jobcardview;
    private String cmpid[];
    private String subassembly[];

    private LinkedHashSet<LabelValueBean>[] subagg;
    private LinkedHashSet<LabelValueBean>[] defectcode;
    private LinkedHashSet<LabelValueBean>[] subassmblylabel;
    private LinkedHashSet<LabelValueBean>[] cause;
    private LinkedHashSet<LabelValueBean>[] conseq;  

    private String vin_details_type;
    private String part_category;
    private String occupation;
    private String sizeoflandhold;
    private String maincrops;
    private String jctype;
    private String vinid;
    private String casualconseq;
    private String dealercode;
    private String destinationpath;
    private String context;
    private String realpath;
    private String itlinvoiceno;
    private String dealerinvoiceno;
    private String invoicedate;
    private String repname;
    private String locationName;
    private String fromdate;
    private String todate;
    private String uploadins;
    private String totalactuals;
    private String hrs;
    private String min;
    private String[] Enquiry;
    private String custName;//
    private String fatherName;
    private String mobilePh;
    private String villagename;
    private String relationwithcustomer;
    private String createdOn;
    private String inventoryqty;
    private String totaldiscount;
    private String isWarReq;
    ArrayList<ArrayList<String>> action_taken;
    ArrayList<ArrayList<String>> labourCharges;
    private String refNo;
    private String dbqty;
    String contactname;
    String contactno;
    String relationshipwithcust;
    String vin_details_type_vehicle;
    private String creditValue;
    private String invoiceQty;
    private String recdQty;
    private String userId;
    private String dueDate;
    private String location;
    private String range;
    private String creditAmount;
    private String reasonForDealy;
    private String receivedBy;
    private Date grDate;
    private Date receivedOn;
    private SpOrderInvGrn spOrderInvGrn;
    private double totalAmount;
    private String stkAdjNo;
    private String stkAdjDate;
    private String totalPuschaseValue;
    private String totalSaleValue;
    private String noOfLines;
    private String salePurchage;
    private String followUpStatus;
    private String lastFollowUpDate;
    private String scheduleID;
    private String followUpId;
    private String callDate;
    private String callDescription;
    private String doorstepServiceRequired;
    private String customerPromisedDate;
    private String nextFollowUpDate;
    private String createdBy;
    private HashMap<String,List<LabelValueBean>> chMap;
    private String subTotal;
    private String taxValue;

    private String title;
    private String mobileNo;    
    private String regNo;
    private String salesDate;
    private String imdCode;
    private long hmrNo;
    private String extWarrantyBookletNo;
    private String dateOfSaleOfCertificate;
    private String typeName;
    private String fuelType;
    private String plan;        
    private String sumInsured;
    private String itlEwRef;
    private String floatType;
    private long ppId;
    private String makeName;
    private int policyTypeId;
    private int policyTermId;
    private String policyType;
    private String policyTerm;
    private String ewLoaderId;
    private long amtToBajaj;
    private String bajajPolicyNo;
    private String bajajPolicyDate;
    private String saleTypeCode;
    private LinkedHashSet<LabelValueBean> policyTypeList;
    private String[] hsnCode;
    private String mechanicDealerKey;
    private String jobTypeId;

    public String getSaleTypeCode() {
        return saleTypeCode;
    }

    public void setSaleTypeCode(String saleTypeCode) {
        this.saleTypeCode = saleTypeCode;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getNextFollowUpDate() {
        return nextFollowUpDate;
    }

    public void setNextFollowUpDate(String nextFollowUpDate) {
        this.nextFollowUpDate = nextFollowUpDate;
    }

    
    public String getCustomerPromisedDate() {
        return customerPromisedDate;
    }

    public void setCustomerPromisedDate(String customerPromisedDate) {
        this.customerPromisedDate = customerPromisedDate;
    }

    
    public String getDoorstepServiceRequired() {
        return doorstepServiceRequired;
    }

    public void setDoorstepServiceRequired(String doorstepServiceRequired) {
        this.doorstepServiceRequired = doorstepServiceRequired;
    }

    
    public String getCallDescription() {
        return callDescription;
    }

    public void setCallDescription(String callDescription) {
        this.callDescription = callDescription;
    }

    public String getCallDate() {
        return callDate;
    }

    public void setCallDate(String callDate) {
        this.callDate = callDate;
    }

    public String getFollowUpId() {
        return followUpId;
    }

    public void setFollowUpId(String followUpId) {
        this.followUpId = followUpId;
    }

    public String getScheduleID() {
        return scheduleID;
    }

    public void setScheduleID(String scheduleID) {
        this.scheduleID = scheduleID;
    }

    public String getFollowUpStatus() {
        return followUpStatus;
    }

    public void setFollowUpStatus(String followUpStatus) {
        this.followUpStatus = followUpStatus;
    }

    public String getLastFollowUpDate() {
        return lastFollowUpDate;
    }

    public void setLastFollowUpDate(String lastFollowUpDate) {
        this.lastFollowUpDate = lastFollowUpDate;
    }

   
    public String getSalePurchage() {
        return salePurchage;
    }

    public void setSalePurchage(String salePurchage) {
        this.salePurchage = salePurchage;
    }

    public String getNoOfLines() {
        return noOfLines;
    }

    public void setNoOfLines(String noOfLines) {
        this.noOfLines = noOfLines;
    }

    public String getStkAdjDate() {
        return stkAdjDate;
    }

    public void setStkAdjDate(String stkAdjDate) {
        this.stkAdjDate = stkAdjDate;
    }

    public String getStkAdjNo() {
        return stkAdjNo;
    }

    public void setStkAdjNo(String stkAdjNo) {
        this.stkAdjNo = stkAdjNo;
    }

    public String getTotalPuschaseValue() {
        return totalPuschaseValue;
    }

    public void setTotalPuschaseValue(String totalPuschaseValue) {
        this.totalPuschaseValue = totalPuschaseValue;
    }

    public String getTotalSaleValue() {
        return totalSaleValue;
    }

    public void setTotalSaleValue(String totalSaleValue) {
        this.totalSaleValue = totalSaleValue;
    }

    public String getCreditAmount() {
        return creditAmount;
    }

    public void setCreditAmount(String creditAmount) {
        this.creditAmount = creditAmount;
    }

    public String getReasonForDealy() {
        return reasonForDealy;
    }

    public void setReasonForDealy(String reasonForDealy) {
        this.reasonForDealy = reasonForDealy;
    }
    

    public String getRange() {
        return range;
    }
    public void setRange(String range) {
        this.range = range;
    }
     public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRepname() {
        return repname;
    }

    public void setRepname(String repname) {
        this.repname = repname;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getInvoiceQty() {
        return invoiceQty;
    }

    public void setInvoiceQty(String invoiceQty) {
        this.invoiceQty = invoiceQty;
    }

    public String getRecdQty() {
        return recdQty;
    }

    public void setRecdQty(String recdQty) {
        this.recdQty = recdQty;
    }

  public String getCreditValue() {
        return creditValue;
    }

    public void setCreditValue(String creditValue) {
        this.creditValue = creditValue;
    }

    
    public String getVin_details_type_vehicle()
    {
        return vin_details_type_vehicle;
    }

    public void setVin_details_type_vehicle(String vin_details_type_vehicle)
    {
        this.vin_details_type_vehicle = vin_details_type_vehicle;
    }

    public String getRelationshipwithcust()
    {
        return relationshipwithcust;
    }

    public void setRelationshipwithcust(String relationshipwithcust)
    {
        this.relationshipwithcust = relationshipwithcust;
    }

    public String getContactname()
    {
        return contactname;
    }

    public void setContactname(String contactname)
    {
        this.contactname = contactname;
    }

    public String getContactno()
    {
        return contactno;
    }

    public void setContactno(String contactno)
    {
        this.contactno = contactno;
    }
    

    public String getDbqty()
    {
        return dbqty;
    }

    public void setDbqty(String dbqty)
    {
        this.dbqty = dbqty;
    }

    public String getRefNo() {
        return refNo;
    }

    public void setRefNo(String refNo) {
        this.refNo = refNo;
    }

    public ArrayList<ArrayList<String>> getAction_taken() {
        return action_taken;
    }

    public void setAction_taken(ArrayList<ArrayList<String>> action_taken) {
        this.action_taken = action_taken;
    }

    public ArrayList<ArrayList<String>> getLabourCharges() {
        return labourCharges;
    }

    public void setLabourCharges(ArrayList<ArrayList<String>> labourCharges) {
        this.labourCharges = labourCharges;
    }

    public String[] getLubescomplaint_Code() {
        return lubescomplaint_Code;
    }

    public void setLubescomplaint_Code(String[] lubescomplaint_Code) {
        this.lubescomplaint_Code = lubescomplaint_Code;
    }

    public String[] getLubescausal_Code() {
        return lubescausal_Code;
    }

    public void setLubescausal_Code(String[] lubescausal_Code) {
        this.lubescausal_Code = lubescausal_Code;
    }

    public String[] getDiscount_perc() {
        return discount_perc;
    }

    public void setDiscount_perc(String[] discount_perc) {
        this.discount_perc = discount_perc;
    }

    public String getInventoryqty() {
        return inventoryqty;
    }

    public void setInventoryqty(String inventoryqty) {
        this.inventoryqty = inventoryqty;
    }

    public String[] getDiscount_amt() {
        return discount_amt;
    }

    public void setDiscount_amt(String[] discount_amt) {
        this.discount_amt = discount_amt;
    }

    public String getTotaldiscount() {
        return totaldiscount;
    }

    public void setTotaldiscount(String totaldiscount) {
        this.totaldiscount = totaldiscount;
    }

    

    public String getIsWarReq() {
        return isWarReq;
    }
    public void setIsWarReq(String isWarReq) {
        this.isWarReq = isWarReq;
    }

    public String getCreatedOn() {
        return createdOn;
    }

    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getFatherName() {
        return fatherName;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getMobilePh() {
        return mobilePh;
    }

    public void setMobilePh(String mobilePh) {
        this.mobilePh = mobilePh;
    }

    public String getRelationwithcustomer() {
        return relationwithcustomer;
    }

    public void setRelationwithcustomer(String relationwithcustomer) {
        this.relationwithcustomer = relationwithcustomer;
    }

    public String getVillagename() {
        return villagename;
    }

    public void setVillagename(String villagename) {
        this.villagename = villagename;
    }

    

    public String[] getEnquiry() {
        return Enquiry;
    }

    public void setEnquiry(String[] Enquiry) {
        this.Enquiry = Enquiry;
    }

   
    
    public String getHrs() {
        return hrs;
    }

    public void setHrs(String hrs) {
        this.hrs = hrs;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    


    public String getTotalactuals() {
        return totalactuals;
    }

    public void setTotalactuals(String totalactuals) {
        this.totalactuals = totalactuals;
    }

    

    public String getUploadins() {
        return uploadins;
    }

    public void setUploadins(String uploadins) {
        this.uploadins = uploadins;
    }
    

    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }


    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }


    public String getRepName() {
        return repname;
    }

    public void setRepName(String repName) {
        this.repname = repName;
    }

    public String getDealerinvoiceno() {
        return dealerinvoiceno;
    }

    public void setDealerinvoiceno(String dealerinvoiceno) {
        this.dealerinvoiceno = dealerinvoiceno;
    }

    

    public String getInvoicedate() {
        return invoicedate;
    }

    public void setInvoicedate(String invoicedate) {
        this.invoicedate = invoicedate;
    }

    public String getItlinvoiceno() {
        return itlinvoiceno;
    }

    public void setItlinvoiceno(String itlinvoiceno) {
        this.itlinvoiceno = itlinvoiceno;
    }



    public String getRealpath() {
        return realpath;
    }

    public void setRealpath(String realpath) {
        this.realpath = realpath;
    }
 
    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getDestinationpath() {
        return destinationpath;
    }

    public void setDestinationpath(String destinationpath) {
        this.destinationpath = destinationpath;
    }
    

    public String getDealercode() {
        return dealercode;
    }

    public void setDealercode(String dealercode) {
        this.dealercode = dealercode;
    }


    
    public String getCasualconseq() {
        return casualconseq;
    }

    public void setCasualconseq(String casualconseq) {
        this.casualconseq = casualconseq;
    }

    

    public String[] getComplaint_Code() {
        return complaint_Code;
    }

    public void setComplaint_Code(String[] complaint_Code) {
        this.complaint_Code = complaint_Code;
    }

    public String[] getCausal_Code() {
        return causal_Code;
    }

    public void setCausal_Code(String[] causal_Code) {
        this.causal_Code = causal_Code;
    }

    
   


    public String[] getSubassembly() {
        return subassembly;
    }

    public void setSubassembly(String[] subassembly) {
        this.subassembly = subassembly;
    }

    

    public String getCompid() {
        return compid;
    }

    public String[] getSubassemblyCode() {
        return subassemblyCode;
    }

    public void setSubassemblyCode(String[] subassemblyCode) {
        this.subassemblyCode = subassemblyCode;
    }

   

    public void setCompid(String compid) {
        this.compid = compid;
    }

    

    public String getVinid() {
        return vinid;
    }

    public void setVinid(String vinid) {
        this.vinid = vinid;
    }

    
    public String getJctype() {
        return jctype;
    }

    public void setJctype(String jctype) {
        this.jctype = jctype;
    }
    public FormFile getFile() {
        return file;
    }

    public void setFile(FormFile file) {
        this.file = file;
    }


    
    public String getStndprt() {
        return stndprt;
    }

    public void setStndprt(String stndprt) {
        this.stndprt = stndprt;
    }

    
    
    public String getMaincrops() {
        return maincrops;
    }

    public void setMaincrops(String maincrops) {
        this.maincrops = maincrops;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getSizeoflandhold() {
        return sizeoflandhold;
    }

    public void setSizeoflandhold(String sizeoflandhold) {
        this.sizeoflandhold = sizeoflandhold;
    }

    

    
    public String[] getCmpid() {
        return cmpid;
    }

    public void setCmpid(String[] cmpid) {
        this.cmpid = cmpid;
    }

   public LinkedHashSet<LabelValueBean>[] getConseq() {
        return conseq;
    }

    public void setConseq(LinkedHashSet<LabelValueBean>[] conseq) {
        this.conseq = conseq;
    }

    public LinkedHashSet<LabelValueBean>[] getCause() {
        return cause;
    }

    public void setCause(LinkedHashSet<LabelValueBean>[] cause) {
        this.cause = cause;
    }
    

    public LinkedHashSet<LabelValueBean>[] getSubassmblylabel() {
        return subassmblylabel;
    }

    public void setSubassmblylabel(LinkedHashSet<LabelValueBean>[] subassmblylabel) {
        this.subassmblylabel = subassmblylabel;
    }

    public LinkedHashSet<LabelValueBean>[] getDefectcode() {
        return defectcode;
    }

    public void setDefectcode(LinkedHashSet<LabelValueBean>[] defectcode) {
        this.defectcode = defectcode;
    }

    public LinkedHashSet<LabelValueBean>[] getSubagg() {
        return subagg;
    }

    public void setSubagg(LinkedHashSet<LabelValueBean>[] subagg) {
        this.subagg = subagg;
    }


    private String maxWtyMonths;
    private String maxWtyHrs;
    private String partNo_str;
    private String partDesc_str;
    private String unitprice_str;
    private String qty_str;
    private String amount_str;
    private String billto_str;
    private String discount_str;
    private String finalamt_str;
    private String complaintCode_str;
    private String actionTaken_str;
    private String labourChargesAmount_str;
    private String workCode_str;
    private String workDescription_str;
    private String workAmount_str;
    private ArrayList<serviceForm> partList,lubesList;
    private ArrayList<serviceForm> labourchargeList;
    private ArrayList<serviceForm> otherchargeList;
    private String[] contentId;
    private String[] subContentId;
    private String[] checkpoints;
    private String[] observations;
    private String[] actiontakens;
    private String[] stc_status;
    LinkedList<ContentFormBean> contentList;
    private String deliveryDate;
    private String visitDate;
    private String dealerName;
    private String buyerName;
    private String buyerPrice;
    private String invoiceno;
    private String majorApplications;
    private String accessories;
    private String invoiceDate;
    LinkedList<ContentFormBean> travelcardPartList;
    private String familyMembers;
    private String othertractorDetail;
    private String paymentMode;
    private String bankName;
    private FormFile uploadphoto;
    private String remarks;
    private String insNo;
    private String insDate;
    private String chassisNo;
    private String stockistId;
    private String stockistName;
    private String partNoStr;
    private String complaintDate;
    private String itlExtWarrantyApplicable;
    private Boolean DecimalQty;

    public String getStockistId() {
        return stockistId;
    }

    public void setStockistId(String stockistId) {
        this.stockistId = stockistId;
    }

    public String getStockistName() {
        return stockistName;
    }

    public void setStockistName(String stockistName) {
        this.stockistName = stockistName;
    }

    public String getChassisNo() {
        return chassisNo;
    }

    public void setChassisNo(String chassisNo) {
        this.chassisNo = chassisNo;
    }

    public String getInsDate() {
        return insDate;
    }

    public void setInsDate(String insDate) {
        this.insDate = insDate;
    }

    public String getInsNo() {
        return insNo;
    }

    public void setInsNo(String insNo) {
        this.insNo = insNo;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }


    
    public FormFile getUploadphoto() {
        return uploadphoto;
    }

    public void setUploadphoto(FormFile uploadphoto) {
        this.uploadphoto = uploadphoto;
    }
    

    public String getFamilyMembers() {
        return familyMembers;
    }

    public void setFamilyMembers(String familyMembers) {
        this.familyMembers = familyMembers;
    }

    public String getOthertractorDetail() {
        return othertractorDetail;
    }

    public void setOthertractorDetail(String othertractorDetail) {
        this.othertractorDetail = othertractorDetail;
    }

    public String getPaymentMode() {
        return paymentMode;
    }

    public void setPaymentMode(String paymentMode) {
        this.paymentMode = paymentMode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public LinkedList<ContentFormBean> getTravelcardPartList() {
        return travelcardPartList;
    }

    public void setTravelcardPartList(LinkedList<ContentFormBean> travelcardPartList) {
        this.travelcardPartList = travelcardPartList;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getMajorApplications() {
        return majorApplications;
    }

    public void setMajorApplications(String majorApplications) {
        this.majorApplications = majorApplications;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public String getInvoiceno() {
        return invoiceno;
    }

    public void setInvoiceno(String invoiceno) {
        this.invoiceno = invoiceno;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }


    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getBuyerName() {
        return buyerName;
    }

    public void setBuyerName(String buyerName) {
        this.buyerName = buyerName;
    }

    public String getBuyerPrice() {
        return buyerPrice;
    }

    public void setBuyerPrice(String buyerPrice) {
        this.buyerPrice = buyerPrice;
    }

    public LinkedList<ContentFormBean> getContentList() {
        return contentList;
    }

    public void setContentList(LinkedList<ContentFormBean> ContentList) {
        this.contentList = ContentList;
    }

    public String[] getContentId() {
        return contentId;
    }

    public void setContentId(String[] contentId) {
        this.contentId = contentId;
    }

    public String[] getSubContentId() {
        return subContentId;
    }

    public void setSubContentId(String[] subContentId) {
        this.subContentId = subContentId;
    }

    public String[] getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(String[] checkpoints) {
        this.checkpoints = checkpoints;
    }

    public String[] getObservations() {
        return observations;
    }

    public void setObservations(String[] remarks) {
        this.observations = remarks;
    }

    public String[] getActiontakens() {
        return actiontakens;
    }

    public void setActiontakens(String[] actiontakens) {
        this.actiontakens = actiontakens;
    }

    public String[] getStc_status() {
        return stc_status;
    }

    public void setStc_status(String[] stc_status) {
        this.stc_status = stc_status;
    }

    public ArrayList<serviceForm> getLubesList() {
        return lubesList;
    }

    public void setLubesList(ArrayList<serviceForm> lubesList) {
        this.lubesList = lubesList;
    }

    public ArrayList<serviceForm> getPartList() {
        return partList;
    }

    public void setPartList(ArrayList<serviceForm> partList) {
        this.partList = partList;
    }

    public ArrayList<serviceForm> getLabourchargeList() {
        return labourchargeList;
    }

    public void setLabourchargeList(ArrayList<serviceForm> labourchargeList) {
        this.labourchargeList = labourchargeList;
    }

    public ArrayList<serviceForm> getOtherchargeList() {
        return otherchargeList;
    }

    public void setOtherchargeList(ArrayList<serviceForm> otherchargeList) {
        this.otherchargeList = otherchargeList;
    }

    public String getWorkCode_str() {
        return workCode_str;
    }

    public void setWorkCode_str(String workCode_str) {
        this.workCode_str = workCode_str;
    }

    public String getWorkDescription_str() {
        return workDescription_str;
    }

    public void setWorkDescription_str(String workDescription_str) {
        this.workDescription_str = workDescription_str;
    }

    public String getWorkAmount_str() {
        return workAmount_str;
    }

    public void setWorkAmount_str(String workAmount_str) {
        this.workAmount_str = workAmount_str;
    }

    public String getComplaintCode_str() {
        return complaintCode_str;
    }

    public void setComplaintCode_str(String complaintCode_str) {
        this.complaintCode_str = complaintCode_str;
    }

    public String getActionTaken_str() {
        return actionTaken_str;
    }

    public void setActionTaken_str(String actionTaken_str) {
        this.actionTaken_str = actionTaken_str;
    }

    public String getLabourChargesAmount_str() {
        return labourChargesAmount_str;
    }

    public void setLabourChargesAmount_str(String labourChargesAmount_str) {
        this.labourChargesAmount_str = labourChargesAmount_str;
    }

    public String getPartNo_str() {
        return partNo_str;
    }

    public void setPartNo_str(String partNo_str) {
        this.partNo_str = partNo_str;
    }

    public String getPartDesc_str() {
        return partDesc_str;
    }

    public void setPartDesc_str(String partDesc_str) {
        this.partDesc_str = partDesc_str;
    }

    public String getUnitprice_str() {
        return unitprice_str;
    }

    public void setUnitprice_str(String unitprice_str) {
        this.unitprice_str = unitprice_str;
    }

    public String getQty_str() {
        return qty_str;
    }

    public void setQty_str(String qty_str) {
        this.qty_str = qty_str;
    }

    public String getAmount_str() {
        return amount_str;
    }

    public void setAmount_str(String amount_str) {
        this.amount_str = amount_str;
    }

    public String getBillto_str() {
        return billto_str;
    }

    public void setBillto_str(String billto_str) {
        this.billto_str = billto_str;
    }

    public String getDiscount_str() {
        return discount_str;
    }

    public void setDiscount_str(String discount_str) {
        this.discount_str = discount_str;
    }

    public String getFinalamt_str() {
        return finalamt_str;
    }

    public void setFinalamt_str(String finalamt_str) {
        this.finalamt_str = finalamt_str;
    }

    public String getPart_category() {
        return part_category;
    }

    public void setPart_category(String part_category) {
        this.part_category = part_category;
    }

    public String getVin_details_type() {
        return vin_details_type;
    }

    public void setVin_details_type(String vin_details_type) {
        this.vin_details_type = vin_details_type;
    }

    public String getMaxWtyHrs() {
        return maxWtyHrs;
    }

    public void setMaxWtyHrs(String maxWtyHrs) {
        this.maxWtyHrs = maxWtyHrs;
    }

    public String getMaxWtyMonths() {
        return maxWtyMonths;
    }

    public void setMaxWtyMonths(String maxWtyMonths) {
        this.maxWtyMonths = maxWtyMonths;
    }


    public String getJobcardview() {
        return jobcardview;
    }

    public void setJobcardview(String jobcardview) {
        this.jobcardview = jobcardview;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }


    public String getCouponno() {
        return couponno;
    }

    public void setCouponno(String couponno) {
        this.couponno = couponno;
    }

    public String getJobcardpriority() {
        return jobcardpriority;
    }

    public void setJobcardpriority(String jobcardpriority) {
        this.jobcardpriority = jobcardpriority;
    }


    public String[] getLubesFinalAmount() {
        return lubesFinalAmount;
    }

    public void setLubesFinalAmount(String[] lubesFinalAmount) {
        this.lubesFinalAmount = lubesFinalAmount;
    }

    
    public String[] getComptype() {
        return comptype;
    }

    public void setComptype(String[] comptype) {
        this.comptype = comptype;
    }

    public String[] getLubesComptype() {
        return lubesComptype;
    }

    public void setLubesComptype(String[] lubesComptype) {
        this.lubesComptype = lubesComptype;
    }

    
    public String[] getBillCode() {
        return billCode;
    }

    public void setBillCode(String[] billCode) {
        this.billCode = billCode;
    }

    public String[] getLubesNo() {
        return lubesNo;
    }

   
    public String getProductionCategory_Desc() {
        return productionCategory_Desc;
    }

    public void setProductionCategory_Desc(String productionCategory_Desc) {
        this.productionCategory_Desc = productionCategory_Desc;
    }


    public void setLubesNo(String[] lubesNo) {
        this.lubesNo = lubesNo;
    }

    public String[] getLubesDesc() {
        return lubesDesc;
    }

    public void setLubesDesc(String[] lubesDesc) {
        this.lubesDesc = lubesDesc;
    }

    public String[] getLubesUnitPrice() {
        return lubesUnitPrice;
    }

    public void setLubesUnitPrice(String[] lubesUnitPrice) {
        this.lubesUnitPrice = lubesUnitPrice;
    }

    public String[] getLubesQuantityS() {
        return lubesQuantityS;
    }

    public void setLubesQuantityS(String[] lubesQuantityS) {
        this.lubesQuantityS = lubesQuantityS;
    }

    public String[] getLubesPriceAmount() {
        return lubesPriceAmount;
    }

    public void setLubesPriceAmount(String[] lubesPriceAmount) {
        this.lubesPriceAmount = lubesPriceAmount;
    }

    public String[] getLubesBillCode() {
        return lubesBillCode;
    }

    public void setLubesBillCode(String[] lubesBillCode) {
        this.lubesBillCode = lubesBillCode;
    }

    public String[] getLubesWarranty() {
        return lubesWarranty;
    }

    public void setLubesWarranty(String[] lubesWarranty) {
        this.lubesWarranty = lubesWarranty;
    }

    public String[] getActionCode() {
        return actionCode;
    }

    public void setActionCode(String[] actionCode) {
        this.actionCode = actionCode;
    }

    public String getTotalLubesValue() {
        return totalLubesValue;
    }

    public void setTotalLubesValue(String totalLubesValue) {
        this.totalLubesValue = totalLubesValue;
    }
    
    
    public String getVehicleOut() {
        return vehicleOut;
    }

    public void setVehicleOut(String vehicleOut) {
        this.vehicleOut = vehicleOut;
    }

    public String getVehicleOutHrs() {
        return vehicleOutHrs;
    }

    public void setVehicleOutHrs(String vehicleOutHrs) {
        this.vehicleOutHrs = vehicleOutHrs;
    }

    public String getVehicleOutMins() {
        return vehicleOutMins;
    }

    public void setVehicleOutMins(String vehicleOutMins) {
        this.vehicleOutMins = vehicleOutMins;
    }
        
    public String getWorkStarted() {
        return workStarted;
    }

    public void setWorkStarted(String workStarted) {
        this.workStarted = workStarted;
    }

    public String getWorkStartedHrs() {
        return workStartedHrs;
    }

    public void setWorkStartedHrs(String workStartedHrs) {
        this.workStartedHrs = workStartedHrs;
    }

    public String getWorkStartedMins() {
        return workStartedMins;
    }

    public void setWorkStartedMins(String workStartedMins) {
        this.workStartedMins = workStartedMins;
    }

    public String getWorkFinished() {
        return workFinished;
    }

    public void setWorkFinished(String workFinished) {
        this.workFinished = workFinished;
    }

    public String getWorkFinishedHrs() {
        return workFinishedHrs;
    }

    public void setWorkFinishedHrs(String workFinishedHrs) {
        this.workFinishedHrs = workFinishedHrs;
    }

    public String getWorkFinishedMins() {
        return workFinishedMins;
    }

    public void setWorkFinishedMins(String workFinishedMins) {
        this.workFinishedMins = workFinishedMins;
    }

    public String getBayCode() {
        return bayCode;
    }

    public void setBayCode(String bayCode) {
        this.bayCode = bayCode;
    }

    public String getMechanicName() {
        return mechanicName;
    }

    public void setMechanicName(String mechanicName) {
        this.mechanicName = mechanicName;
    }

    public String getInspectionBy() {
        return inspectionBy;
    }

    public void setInspectionBy(String inspectionBy) {
        this.inspectionBy = inspectionBy;
    }
    
            
    
    public String[] getConsequenceCode2() {
        return consequenceCode2;
    }

    public void setConsequenceCode2(String[] consequenceCode2) {
        this.consequenceCode2 = consequenceCode2;
    }

    public String[] getConsequenceCode3() {
        return consequenceCode3;
    }

    public void setConsequenceCode3(String[] consequenceCode3) {
        this.consequenceCode3 = consequenceCode3;
    }

    public String[] getConsequenceCode4() {
        return consequenceCode4;
    }

    public void setConsequenceCode4(String[] consequenceCode4) {
        this.consequenceCode4 = consequenceCode4;
    }

    public String[] getConsequenceCode5() {
        return consequenceCode5;
    }

    public void setConsequenceCode5(String[] consequenceCode5) {
        this.consequenceCode5 = consequenceCode5;
    }

    public String[] getConsequenceCode6() {
        return consequenceCode6;
    }

    public void setConsequenceCode6(String[] consequenceCode6) {
        this.consequenceCode6 = consequenceCode6;
    }

    public String[] getConsequenceCode7() {
        return consequenceCode7;
    }

    public void setConsequenceCode7(String[] consequenceCode7) {
        this.consequenceCode7 = consequenceCode7;
    }

    public String[] getConsequenceCode8() {
        return consequenceCode8;
    }

    public void setConsequenceCode8(String[] consequenceCode8) {
        this.consequenceCode8 = consequenceCode8;
    }

    public String[] getConsequenceCode9() {
        return consequenceCode9;
    }

    public void setConsequenceCode9(String[] consequenceCode9) {
        this.consequenceCode9 = consequenceCode9;
    }

    public String[] getConsequenceCode10() {
        return consequenceCode10;
    }

    public void setConsequenceCode10(String[] consequenceCode10) {
        this.consequenceCode10 = consequenceCode10;
    }

    
    
    
    
    
    public String[] getCompVerbatim() {
        return compVerbatim;
    }

    public void setCompVerbatim(String[] compVerbatim) {
        this.compVerbatim = compVerbatim;
    }

    public String[] getApplicationCode() {
        return applicationCode;
    }

    public void setApplicationCode(String[] applicationCode) {
        this.applicationCode = applicationCode;
    }

    public String[] getCompCode() {
        return compCode;
    }

    public void setCompCode(String[] compCode) {
        this.compCode = compCode;
    }

    public String[] getAggCode() {
        return aggCode;
    }

    public void setAggCode(String[] aggCode) {
        this.aggCode = aggCode;
    }

    public String[] getCausalCode() {
        return causalCode;
    }

    public void setCausalCode(String[] causalCode) {
        this.causalCode = causalCode;
    }

    public String[] getSubaggCode() {
        return subaggCode;
    }

    public void setSubaggCode(String[] subaggCode) {
        this.subaggCode = subaggCode;
    }

    public String[] getConsequenceCode1() {
        return consequenceCode1;
    }

    public void setConsequenceCode1(String[] consequenceCode1) {
        this.consequenceCode1 = consequenceCode1;
    }

    public String[] getVendorCode() {
        return vendorCode;
    }

    public void setVendorCode(String[] vendorCode) {
        this.vendorCode = vendorCode;
    }

    public String[] getForemanObservation() {
        return foremanObservation;
    }

    public void setForemanObservation(String[] foremanObservation) {
        this.foremanObservation = foremanObservation;
    }
    
    
    


    private String productionCategory;
    private String event;
    private String hmeRadio;
    private String serviceDoneBy;
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    

    public String getServiceDoneBy() {
        return serviceDoneBy;
    }

    public void setServiceDoneBy(String serviceDoneBy) {
        this.serviceDoneBy = serviceDoneBy;
    }

    public String getCurrentEstimatestr() {
        return currentEstimatestr;
    }

    public void setCurrentEstimatestr(String currentEstimatestr) {
        this.currentEstimatestr = currentEstimatestr;
    }

    public String getJobCardDateStr() {
        return jobCardDateStr;
    }

    public void setJobCardDateStr(String jobCardDateStr) {
        this.jobCardDateStr = jobCardDateStr;
    }

    public String getPromisedstr() {
        return promisedstr;
    }

    public void setPromisedstr(String promisedstr) {
        this.promisedstr = promisedstr;
    }

    public String getRequiredBYCustomerstr() {
        return requiredBYCustomerstr;
    }

    public void setRequiredBYCustomerstr(String requiredBYCustomerstr) {
        this.requiredBYCustomerstr = requiredBYCustomerstr;
    }

    public String getSaleDatestr() {
        return saleDatestr;
    }

    public void setSaleDatestr(String saleDatestr) {
        this.saleDatestr = saleDatestr;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getProductionCategory() {
        return productionCategory;
    }

    public void setProductionCategory(String productionCategory) {
        this.productionCategory = productionCategory;
    }

    

    public String getHmeRadio() {
        return hmeRadio;
    }

    public void setHmeRadio(String hmeRadio) {
        this.hmeRadio = hmeRadio;
    }
    
    /**
     * @return the jobType
     */
    public String getJobType() {
        return jobType;
    }

    /**
     * @param jobType the jobType to set
     */
    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    /**
     * @return the labelList
     */
    public ArrayList<LabelValueBean> getLabelList() {
        return labelList;
    }

    /**
     * @param labelList the labelList to set
     */
    public void setLabelList(ArrayList<LabelValueBean> labelList) {
        this.labelList = labelList;
    }

    /**
     * @return the jobLocation
     */
    public String getJobLocation() {
        return jobLocation;
    }

    /**
     * @param jobLocation the jobLocation to set
     */
    public void setJobLocation(String jobLocation) {
        this.jobLocation = jobLocation;
    }

    /**
     * @return the nextService
     */
    public String getNextService() {
        return nextService;
    }

    /**
     * @param nextService the nextService to set
     */
    public void setNextService(String nextService) {
        this.nextService = nextService;
    }

    /**
     * @return the jobCardStatus
     */
    public String getJobCardStatus() {
        return jobCardStatus;
    }

    /**
     * @param jobCardStatus the jobCardStatus to set
     */
    public void setJobCardStatus(String jobCardStatus) {
        this.jobCardStatus = jobCardStatus;
    }

    /**
     * @return the ownerDriven
     */
    public String getOwnerDriven() {
        return ownerDriven;
    }

    /**
     * @param ownerDriven the ownerDriven to set
     */
    public void setOwnerDriven(String ownerDriven) {
        this.ownerDriven = ownerDriven;
    }

    /**
     * @return the serviceType
     */
    public String getServiceType() {
        return serviceType;
    }

    /**
     * @param serviceType the serviceType to set
     */
    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    /**
     * @return the vinNo
     */
    public String getVinNo() {
        return vinNo;
    }

    /**
     * @param vinNo the vinNo to set
     */
    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    /**
     * @return the jobCardNo
     */
    public String getJobCardNo() {
        return jobCardNo;
    }

    /**
     * @param jobCardNo the jobCardNo to set
     */
    public void setJobCardNo(String jobCardNo) {
        this.jobCardNo = jobCardNo;
    }

    /**
     * @return the modelFamily
     */
    public String getModelFamily() {
        return modelFamily;
    }

    /**
     * @param modelFamily the modelFamily to set
     */
    public void setModelFamily(String modelFamily) {
        this.modelFamily = modelFamily;
    }

    /**
     * @return the engineNumber
     */
    public String getEngineNumber() {
        return engineNumber;
    }

    /**
     * @param engineNumber the engineNumber to set
     */
    public void setEngineNumber(String engineNumber) {
        this.engineNumber = engineNumber;
    }

    /**
     * @return the dealerJobCardNo
     */
    public String getDealerJobCardNo() {
        return dealerJobCardNo;
    }

    /**
     * @param dealerJobCardNo the dealerJobCardNo to set
     */
    public void setDealerJobCardNo(String dealerJobCardNo) {
        this.dealerJobCardNo = dealerJobCardNo;
    }

    /**
     * @return the modelCode
     */
    public String getModelCode() {
        return modelCode;
    }

    /**
     * @param modelCode the modelCode to set
     */
    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    /**
     * @return the saleDate
     */
    public String getSaleDate() {
        return saleDate;
    }

    /**
     * @param saleDate the saleDate to set
     */
    public void setSaleDate(String saleDate) {
        this.saleDate = saleDate;
    }

    /**
     * @return the modelFamilyDesc
     */
    public String getModelFamilyDesc() {
        return modelFamilyDesc;
    }

    /**
     * @param modelFamilyDesc the modelFamilyDesc to set
     */
    public void setModelFamilyDesc(String modelFamilyDesc) {
        this.modelFamilyDesc = modelFamilyDesc;
    }

    /**
     * @return the registrationNo
     */
    public String getRegistrationNo() {
        return registrationNo;
    }

    /**
     * @param registrationNo the registrationNo to set
     */
    public void setRegistrationNo(String registrationNo) {
        this.registrationNo = registrationNo;
    }

    /**
     * @return the hmr
     */
    public String getHmr() {
        return hmr;
    }

    /**
     * @param hmr the hmr to set
     */
    public void setHmr(String hmr) {
        this.hmr = hmr;
    }

    /**
     * @return the modelCodeDesc
     */
    public String getModelCodeDesc() {
        return modelCodeDesc;
    }

    /**
     * @param modelCodeDesc the modelCodeDesc to set
     */
    public void setModelCodeDesc(String modelCodeDesc) {
        this.modelCodeDesc = modelCodeDesc;
    }

    /**
     * @return the serviceBookletNo
     */
    public String getServiceBookletNo() {
        return serviceBookletNo;
    }

    /**
     * @param serviceBookletNo the serviceBookletNo to set
     */
    public void setServiceBookletNo(String serviceBookletNo) {
        this.serviceBookletNo = serviceBookletNo;
    }

    /**
     * @return the jobCardDate
     */
    public String getJobCardDate() {
        return jobCardDate;
    }

    /**
     * @param jobCardDate the jobCardDate to set
     */
    public void setJobCardDate(String jobCardDate) {
        this.jobCardDate = jobCardDate;
    }

    /**
     * @return the keyIdentificationNo
     */
    public String getKeyIdentificationNo() {
        return keyIdentificationNo;
    }

    /**
     * @param keyIdentificationNo the keyIdentificationNo to set
     */
    public void setKeyIdentificationNo(String keyIdentificationNo) {
        this.keyIdentificationNo = keyIdentificationNo;
    }

    /**
     * @return the warrantyApplicable
     */
    public String getWarrantyApplicable() {
        return warrantyApplicable;
    }

    /**
     * @param warrantyApplicable the warrantyApplicable to set
     */
    public void setWarrantyApplicable(String warrantyApplicable) {
        this.warrantyApplicable = warrantyApplicable;
    }

    /**
     * @return the customerName
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the customerName to set
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the payeeName
     */
    public String getPayeeName() {
        return payeeName;
    }

    /**
     * @param payeeName the payeeName to set
     */
    public void setPayeeName(String payeeName) {
        this.payeeName = payeeName;
    }

    /**
     * @return the village
     */
    public String getVillage() {
        return village;
    }

    /**
     * @param village the village to set
     */
    public void setVillage(String village) {
        this.village = village;
    }

    /**
     * @return the payeeVillage
     */
    public String getPayeeVillage() {
        return payeeVillage;
    }

    /**
     * @param payeeVillage the payeeVillage to set
     */
    public void setPayeeVillage(String payeeVillage) {
        this.payeeVillage = payeeVillage;
    }

    /**
     * @return the mobilePhone
     */
    public String getMobilePhone() {
        return mobilePhone;
    }

    /**
     * @param mobilePhone the mobilePhone to set
     */
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    /**
     * @return the payeeMobilePhone
     */
    public String getPayeeMobilePhone() {
        return payeeMobilePhone;
    }

    /**
     * @param payeeMobilePhone the payeeMobilePhone to set
     */
    public void setPayeeMobilePhone(String payeeMobilePhone) {
        this.payeeMobilePhone = payeeMobilePhone;
    }

    /**
     * @return the taluka
     */
    public String getTaluka() {
        return taluka;
    }

    /**
     * @param taluka the taluka to set
     */
    public void setTaluka(String taluka) {
        this.taluka = taluka;
    }

    /**
     * @return the payeeTaluka
     */
    public String getPayeeTaluka() {
        return payeeTaluka;
    }

    /**
     * @param payeeTaluka the payeeTaluka to set
     */
    public void setPayeeTaluka(String payeeTaluka) {
        this.payeeTaluka = payeeTaluka;
    }

    /**
     * @return the landline
     */
    public String getLandline() {
        return landline;
    }

    /**
     * @param landline the landline to set
     */
    public void setLandline(String landline) {
        this.landline = landline;
    }

    /**
     * @return the payeeLandline
     */
    public String getPayeeLandline() {
        return payeeLandline;
    }

    /**
     * @param payeeLandline the payeeLandline to set
     */
    public void setPayeeLandline(String payeeLandline) {
        this.payeeLandline = payeeLandline;
    }

    /**
     * @return the tehsil
     */
    public String getTehsil() {
        return tehsil;
    }

    /**
     * @param tehsil the tehsil to set
     */
    public void setTehsil(String tehsil) {
        this.tehsil = tehsil;
    }

    /**
     * @return the payeeTehsil
     */
    public String getPayeeTehsil() {
        return payeeTehsil;
    }

    /**
     * @param payeeTehsil the payeeTehsil to set
     */
    public void setPayeeTehsil(String payeeTehsil) {
        this.payeeTehsil = payeeTehsil;
    }

    /**
     * @return the emailId
     */
    public String getEmailId() {
        return emailId;
    }

    /**
     * @param emailId the emailId to set
     */
    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    /**
     * @return the payeeEmailId
     */
    public String getPayeeEmailId() {
        return payeeEmailId;
    }

    /**
     * @param payeeEmailId the payeeEmailId to set
     */
    public void setPayeeEmailId(String payeeEmailId) {
        this.payeeEmailId = payeeEmailId;
    }

    /**
     * @return the district
     */
    public String getDistrict() {
        return district;
    }

    /**
     * @param district the district to set
     */
    public void setDistrict(String district) {
        this.district = district;
    }

    /**
     * @return the payeeDistrict
     */
    public String getPayeeDistrict() {
        return payeeDistrict;
    }

    /**
     * @param payeeDistrict the payeeDistrict to set
     */
    public void setPayeeDistrict(String payeeDistrict) {
        this.payeeDistrict = payeeDistrict;
    }

    /**
     * @return the pinCode
     */
    public String getPinCode() {
        return pinCode;
    }

    /**
     * @param pinCode the pinCode to set
     */
    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    /**
     * @return the payeePinCode
     */
    public String getPayeePinCode() {
        return payeePinCode;
    }

    /**
     * @param payeePinCode the payeePinCode to set
     */
    public void setPayeePinCode(String payeePinCode) {
        this.payeePinCode = payeePinCode;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the payeeState
     */
    public String getPayeeState() {
        return payeeState;
    }

    /**
     * @param payeeState the payeeState to set
     */
    public void setPayeeState(String payeeState) {
        this.payeeState = payeeState;
    }

    /**
     * @return the country
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country to set
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the payeeCountry
     */
    public String getPayeeCountry() {
        return payeeCountry;
    }

    /**
     * @param payeeCountry the payeeCountry to set
     */
    public void setPayeeCountry(String payeeCountry) {
        this.payeeCountry = payeeCountry;
    }

    /**
     * @return the foremanObservation
     */
//    public String getForemanObservation() {
//        return foremanObservation;
//    }
//
//    /**
//     * @param foremanObservation the foremanObservation to set
//     */
//    public void setForemanObservation(String foremanObservation) {
//        this.foremanObservation = foremanObservation;
//    }

    /**
     * @return the partNo
     */
    public String[] getPartNo() {
        return partNo;
    }

    /**
     * @param partNo the partNo to set
     */
    public void setPartNo(String[] partNo) {
        this.partNo = partNo;
    }

    /**
     * @return the partDesc
     */
    public String[] getPartDesc() {
        return partDesc;
    }

    /**
     * @param partDesc the partDesc to set
     */
    public void setPartDesc(String[] partDesc) {
        this.partDesc = partDesc;
    }

    /**
     * @return the unitPrice
     */
    public String[] getUnitPrice() {
        return unitPrice;
    }

    /**
     * @param unitPrice the unitPrice to set
     */
    public void setUnitPrice(String[] unitPrice) {
        this.unitPrice = unitPrice;
    }

    /**
     * @return the quantityS
     */
    public String[] getQuantityS() {
        return quantityS;
    }

    /**
     * @param quantityS the quantityS to set
     */
    public void setQuantityS(String[] quantityS) {
        this.quantityS = quantityS;
    }

    /**
     * @return the partPriceAmount
     */
    public String[] getPartPriceAmount() {
        return partPriceAmount;
    }

    /**
     * @param partPriceAmount the partPriceAmount to set
     */
    public void setPartPriceAmount(String[] partPriceAmount) {
        this.partPriceAmount = partPriceAmount;
    }

    /**
     * @return the billTo
     */
    public String[] getBillTo() {
        return billTo;
    }

    /**
     * @param billTo the billTo to set
     */
    public void setBillTo(String[] billTo) {
        this.billTo = billTo;
    }

    /**
     * @return the warranty
     */
    public String[] getWarranty() {
        return warranty;
    }

    /**
     * @param warranty the warranty to set
     */
    public void setWarranty(String[] warranty) {
        this.warranty = warranty;
    }

    /**
     * @return the finalAmount
     */
    public String[] getFinalAmount() {
        return finalAmount;
    }

    /**
     * @param finalAmount the finalAmount to set
     */
    public void setFinalAmount(String[] finalAmount) {
        this.finalAmount = finalAmount;
    }

    /**
     * @return the complaintCode
     */
    public String[] getComplaintCode() {
        return complaintCode;
    }

    /**
     * @param complaintCode the complaintCode to set
     */
    public void setComplaintCode(String[] complaintCode) {
        this.complaintCode = complaintCode;
    }

    /**
     * @return the actionTaken
     */
    public String[] getActionTaken() {
        return actionTaken;
    }

    /**
     * @param actionTaken the actionTaken to set
     */
    public void setActionTaken(String[] actionTaken) {
        this.actionTaken = actionTaken;
    }

    /**
     * @return the labourChargesAmount
     */
    public String[] getLabourChargesAmount() {
        return labourChargesAmount;
    }

    /**
     * @param labourChargesAmount the labourChargesAmount to set
     */
    public void setLabourChargesAmount(String[] labourChargesAmount) {
        this.labourChargesAmount = labourChargesAmount;
    }

    /**
     * @return the workCode
     */
    public String[] getWorkCode() {
        return workCode;
    }

    /**
     * @param workCode the workCode to set
     */
    public void setWorkCode(String[] workCode) {
        this.workCode = workCode;
    }

    /**
     * @return the workDescription
     */
    public String[] getWorkDescription() {
        return workDescription;
    }

    /**
     * @param workDescription the workDescription to set
     */
    public void setWorkDescription(String[] workDescription) {
        this.workDescription = workDescription;
    }

    /**
     * @return the workAmount
     */
    public String[] getWorkAmount() {
        return workAmount;
    }

    /**
     * @param workAmount the workAmount to set
     */
    public void setWorkAmount(String[] workAmount) {
        this.workAmount = workAmount;
    }

    /**
     * @return the totalPartsValue
     */
    public String getTotalPartsValue() {
        return totalPartsValue;
    }

    /**
     * @param totalPartsValue the totalPartsValue to set
     */
    public void setTotalPartsValue(String totalPartsValue) {
        this.totalPartsValue = totalPartsValue;
    }

    /**
     * @return the totalLabourCharges
     */
    public String getTotalLabourCharges() {
        return totalLabourCharges;
    }

    /**
     * @param totalLabourCharges the totalLabourCharges to set
     */
    public void setTotalLabourCharges(String totalLabourCharges) {
        this.totalLabourCharges = totalLabourCharges;
    }

    /**
     * @return the totalOtherCharges
     */
    public String getTotalOtherCharges() {
        return totalOtherCharges;
    }

    /**
     * @param totalOtherCharges the totalOtherCharges to set
     */
    public void setTotalOtherCharges(String totalOtherCharges) {
        this.totalOtherCharges = totalOtherCharges;
    }

    /**
     * @return the totalEstimate
     */
    public String getTotalEstimate() {
        return totalEstimate;
    }

    /**
     * @param totalEstimate the totalEstimate to set
     */
    public void setTotalEstimate(String totalEstimate) {
        this.totalEstimate = totalEstimate;
    }

    /**
     * @return the promised
     */
    public String getPromised() {
        return promised;
    }

    /**
     * @param promised the promised to set
     */
    public void setPromised(String promised) {
        this.promised = promised;
    }

    /**
     * @return the promisedTime
     */
    public String getPromisedTime() {
        return promisedTime;
    }

    /**
     * @param promisedTime the promisedTime to set
     */
    public void setPromisedTime(String promisedTime) {
        this.promisedTime = promisedTime;
    }

    /**
     * @return the requiredBYCustomer
     */
    public String getRequiredBYCustomer() {
        return requiredBYCustomer;
    }

    /**
     * @param requiredBYCustomer the requiredBYCustomer to set
     */
    public void setRequiredBYCustomer(String requiredBYCustomer) {
        this.requiredBYCustomer = requiredBYCustomer;
    }

    /**
     * @return the requiredBYCustomerTime
     */
    public String getRequiredBYCustomerTime() {
        return requiredBYCustomerTime;
    }

    /**
     * @param requiredBYCustomerTime the requiredBYCustomerTime to set
     */
    public void setRequiredBYCustomerTime(String requiredBYCustomerTime) {
        this.requiredBYCustomerTime = requiredBYCustomerTime;
    }

    /**
     * @return the currentEstimate
     */
    public String getCurrentEstimate() {
        return currentEstimate;
    }

    /**
     * @param currentEstimate the currentEstimate to set
     */
    public void setCurrentEstimate(String currentEstimate) {
        this.currentEstimate = currentEstimate;
    }

    /**
     * @return the currentEstimateTime
     */
    public String getCurrentEstimateTime() {
        return currentEstimateTime;
    }

    /**
     * @param currentEstimateTime the currentEstimateTime to set
     */
    public void setCurrentEstimateTime(String currentEstimateTime) {
        this.currentEstimateTime = currentEstimateTime;
    }

    /**
     * @return the promisedHrs
     */
    public String getPromisedHrs() {
        return promisedHrs;
    }

    /**
     * @param promisedHrs the promisedHrs to set
     */
    public void setPromisedHrs(String promisedHrs) {
        this.promisedHrs = promisedHrs;
    }

    /**
     * @return the requiredBYCustomerHrs
     */
    public String getRequiredBYCustomerHrs() {
        return requiredBYCustomerHrs;
    }

    /**
     * @param requiredBYCustomerHrs the requiredBYCustomerHrs to set
     */
    public void setRequiredBYCustomerHrs(String requiredBYCustomerHrs) {
        this.requiredBYCustomerHrs = requiredBYCustomerHrs;
    }

    /**
     * @return the currentEstimateHrs
     */
    public String getCurrentEstimateHrs() {
        return currentEstimateHrs;
    }

    /**
     * @param currentEstimateHrs the currentEstimateHrs to set
     */
    public void setCurrentEstimateHrs(String currentEstimateHrs) {
        this.currentEstimateHrs = currentEstimateHrs;
    }

    /**
     * @return the constantValue
     */
    public String getConstantValue() {
        return constantValue;
    }

    /**
     * @param constantValue the constantValue to set
     */
    public void setConstantValue(String constantValue) {
        this.constantValue = constantValue;
    }

    /**
     * @return the prtParam
     */
    public ArrayList getPrtParam() {
        return prtParam;
    }

    /**
     * @param prtParam the prtParam to set
     */
    public void setPrtParam(ArrayList prtParam) {
        this.prtParam = prtParam;
    }

    /**
     * @return the formName
     */
    public String getFormName() {
        return formName;
    }

    /**
     * @param formName the formName to set
     */
    public void setFormName(String formName) {
        this.formName = formName;
    }

    /**
     * @return the formId
     */
    public String getFormId() {
        return formId;
    }

    /**
     * @param formId the formId to set
     */
    public void setFormId(String formId) {
        this.formId = formId;
    }

    /**
     * @return the dataMap
     */
    public Map<ContentFormBean, ArrayList<SubContentFormBean>> getDataMap() {
        return dataMap;
    }

    /**
     * @param dataMap the dataMap to set
     */
    public void setDataMap(Map<ContentFormBean, ArrayList<SubContentFormBean>> dataMap) {
        this.dataMap = dataMap;
    }

    /**
     * @return the jobTypeDesc
     */
    public String getJobTypeDesc() {
        return jobTypeDesc;
    }

    /**
     * @param jobTypeDesc the jobTypeDesc to set
     */
    public void setJobTypeDesc(String jobTypeDesc) {
        this.jobTypeDesc = jobTypeDesc;
    }

    /**
     * @return the pathName
     */
    public String getPathName() {
        return pathName;
    }

    /**
     * @param pathName the pathName to set
     */
    public void setPathName(String pathName) {
        this.pathName = pathName;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    /**
     * @return the receivedBy
     */
    public String getReceivedBy() {
        return receivedBy;
    }

    /**
     * @param receivedBy the receivedBy to set
     */
    public void setReceivedBy(String receivedBy) {
        this.receivedBy = receivedBy;
    }

    /**
     * @return the grDate
     */
    public Date getGrDate() {
        return grDate;
    }

    /**
     * @param grDate the grDate to set
     */
    public void setGrDate(Date grDate) {
        this.grDate = grDate;
    }

    /**
     * @return the receivedOn
     */
    public Date getReceivedOn() {
        return receivedOn;
    }

    /**
     * @param receivedOn the receivedOn to set
     */
    public void setReceivedOn(Date receivedOn) {
        this.receivedOn = receivedOn;
    }

    /**
     * @return the spOrderInvGrn
     */
    public SpOrderInvGrn getSpOrderInvGrn() {
        return spOrderInvGrn;
    }

    /**
     * @param spOrderInvGrn the spOrderInvGrn to set
     */
    public void setSpOrderInvGrn(SpOrderInvGrn spOrderInvGrn) {
        this.spOrderInvGrn = spOrderInvGrn;
    }
    /**
     * @return the partNoStr
     */
    public String getPartNoStr() {
        return partNoStr;
    }

    /**
     * @param partNoStr the partNoStr to set
     */
    public void setPartNoStr(String partNoStr) {
        this.partNoStr = partNoStr;
    }

    /**
     * @return the totalAmount
     */
    public double getTotalAmount() {
        return totalAmount;
    }

    /**
     * @param totalAmount the totalAmount to set
     */
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
    private String[] part_order_seq;
    private String[] charge_branch_id;
    private String[] chargeDesc;
    private String[] charge_rate;
    private String[] chargeAmount;
    private String[] seqNo;
    private String[] charge_order;
    private String[] commodityCode;

    public String[] getCommodityCode() {
        return commodityCode;
    }

    public void setCommodityCode(String[] commodityCode) {
        this.commodityCode = commodityCode;
    }

    public String[] getChargeAmount() {
        return chargeAmount;
    }

    public void setChargeAmount(String[] chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public String[] getChargeDesc() {
        return chargeDesc;
    }

    public void setChargeDesc(String[] chargeDesc) {
        this.chargeDesc = chargeDesc;
    }

    public String[] getCharge_branch_id() {
        return charge_branch_id;
    }

    public void setCharge_branch_id(String[] charge_branch_id) {
        this.charge_branch_id = charge_branch_id;
    }

    public String[] getCharge_order() {
        return charge_order;
    }

    public void setCharge_order(String[] charge_order) {
        this.charge_order = charge_order;
    }

    public String[] getCharge_rate() {
        return charge_rate;
    }

    public void setCharge_rate(String[] charge_rate) {
        this.charge_rate = charge_rate;
    }

    public String[] getPart_order_seq() {
        return part_order_seq;
    }

    public void setPart_order_seq(String[] part_order_seq) {
        this.part_order_seq = part_order_seq;
    }

    public String[] getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String[] seqNo) {
        this.seqNo = seqNo;
    }

    public HashMap<String, List<LabelValueBean>> getChMap() {
        return chMap;
    }

    public void setChMap(HashMap<String, List<LabelValueBean>> chMap) {
        this.chMap = chMap;
    }

    public String getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(String taxValue) {
        this.taxValue = taxValue;
    }

    public String getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }

    public String getComplaintDate() {
        return complaintDate;
    }

    public void setComplaintDate(String complaintDate) {
        this.complaintDate = complaintDate;
    }

    public String getExtWarrantyBookletNo() {
        return extWarrantyBookletNo;
    }

    public void setExtWarrantyBookletNo(String extWarrantyBookletNo) {
        this.extWarrantyBookletNo = extWarrantyBookletNo;
    }

    public String getFloatType() {
        return floatType;
    }

    public void setFloatType(String floatType) {
        this.floatType = floatType;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getImdCode() {
        return imdCode;
    }

    public void setImdCode(String imdCode) {
        this.imdCode = imdCode;
    }

    public String getItlEwRef() {
        return itlEwRef;
    }

    public void setItlEwRef(String itlEwRef) {
        this.itlEwRef = itlEwRef;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getRegNo() {
        return regNo;
    }

    public void setRegNo(String regNo) {
        this.regNo = regNo;
    }

    public String getSalesDate() {
        return salesDate;
    }

    public void setSalesDate(String salesDate) {
        this.salesDate = salesDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getMakeName() {
        return makeName;
    }

    public void setMakeName(String makeName) {
        this.makeName = makeName;
    }

    public String getDateOfSaleOfCertificate() {
        return dateOfSaleOfCertificate;
    }

    public void setDateOfSaleOfCertificate(String dateOfSaleOfCertificate) {
        this.dateOfSaleOfCertificate = dateOfSaleOfCertificate;
    }

    public String getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(String sumInsured) {
        this.sumInsured = sumInsured;
    }

    public long getHmrNo() {
        return hmrNo;
    }

    public void setHmrNo(long hmrNo) {
        this.hmrNo = hmrNo;
    }

    public long getPpId() {
        return ppId;
    }

    public void setPpId(long ppId) {
        this.ppId = ppId;
    }

    public int getPolicyTermId() {
        return policyTermId;
    }

    public void setPolicyTermId(int policyTermId) {
        this.policyTermId = policyTermId;
    }

    public int getPolicyTypeId() {
        return policyTypeId;
    }

    public void setPolicyTypeId(int policyTypeId) {
        this.policyTypeId = policyTypeId;
    }

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public String getEwLoaderId() {
        return ewLoaderId;
    }

    public void setEwLoaderId(String ewLoaderId) {
        this.ewLoaderId = ewLoaderId;
    }

    public long getAmtToBajaj() {
        return amtToBajaj;
    }

    public void setAmtToBajaj(long amtToBajaj) {
        this.amtToBajaj = amtToBajaj;
    }

    public String getBajajPolicyNo() {
        return bajajPolicyNo;
    }

    public void setBajajPolicyNo(String bajajPolicyNo) {
        this.bajajPolicyNo = bajajPolicyNo;
    }

    public String getBajajPolicyDate() {
        return bajajPolicyDate;
    }

    public void setBajajPolicyDate(String bajajPolicyDate) {
        this.bajajPolicyDate = bajajPolicyDate;
    }

    public String getPolicyTerm() {
        return policyTerm;
    }

    public void setPolicyTerm(String policyTerm) {
        this.policyTerm = policyTerm;
    }

    public LinkedHashSet<LabelValueBean> getPolicyTypeList() {
        return policyTypeList;
    }

    public void setPolicyTypeList(LinkedHashSet<LabelValueBean> policyTypeList) {
        this.policyTypeList = policyTypeList;
    }

    /**
     * @return the gstCode
     */
    public String getGstCode()
    {
        return gstCode;
    }

    /**
     * @param gstCode the gstCode to set
     */
    public void setGstCode(String gstCode)
    {
        this.gstCode = gstCode;
    }

    public String[] getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String[] hsnCode) {
        this.hsnCode = hsnCode;
    }

    /**
     * @return the mechanicDealerKey
     */
    public String getMechanicDealerKey() {
        return mechanicDealerKey;
    }

    /**
     * @param mechanicDealerKey the mechanicDealerKey to set
     */
    public void setMechanicDealerKey(String mechanicDealerKey) {
        this.mechanicDealerKey = mechanicDealerKey;
    }

    /**
     * @return the jobTypeId
     */
    public String getJobTypeId() {
        return jobTypeId;
    }

    /**
     * @param jobTypeId the jobTypeId to set
     */
    public void setJobTypeId(String jobTypeId) {
        this.jobTypeId = jobTypeId;
    }
    
    //add columns for ITL EXT WTY 
    private LinkedHashSet<LabelValueBean> states;
    private LinkedHashSet<LabelValueBean> districts;
    private LinkedHashSet<LabelValueBean> cities;
    private LinkedHashSet<LabelValueBean> tehsils;
    private String itlExtRegDate;
    private String ewType;
    private String ewRegistrationAmount;
    private String payeeCity;
    private String customerAddress;
    private String payeeAddress;
    private String gstInvoiceDocName;
    private String itlEwCertificateName;
    private String itlEwDebitInvoice;
    private String itlPolicyNo;
    private String itlPolicyDate;
    
    private String serviceRecordEngine;
    private String gearOilChangeTransmission;
    private String engine;
    private String clutch;
    private String transmission;
    private String brakes;
    private String hydraulic;
    private String tpl;
    private String checkExternalCrackDamage;
    private String Itlstatus;
    private String itlEmployeeId;
    private String firstName;
    
	public String getItlPolicyNo() {
		return itlPolicyNo;
	}

	public void setItlPolicyNo(String itlPolicyNo) {
		this.itlPolicyNo = itlPolicyNo;
	}

	public String getItlPolicyDate() {
		return itlPolicyDate;
	}

	public void setItlPolicyDate(String itlPolicyDate) {
		this.itlPolicyDate = itlPolicyDate;
	}

	public String getGstInvoiceDocName() {
		return gstInvoiceDocName;
	}

	public void setGstInvoiceDocName(String gstInvoiceDocName) {
		this.gstInvoiceDocName = gstInvoiceDocName;
	}

	public String getItlEwCertificateName() {
		return itlEwCertificateName;
	}

	public void setItlEwCertificateName(String itlEwCertificateName) {
		this.itlEwCertificateName = itlEwCertificateName;
	}

	public String getItlEwDebitInvoice() {
		return itlEwDebitInvoice;
	}

	public void setItlEwDebitInvoice(String itlEwDebitInvoice) {
		this.itlEwDebitInvoice = itlEwDebitInvoice;
	}

	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}

	public String getPayeeAddress() {
		return payeeAddress;
	}

	public void setPayeeAddress(String payeeAddress) {
		this.payeeAddress = payeeAddress;
	}

	public String getPayeeCity() {
		return payeeCity;
	}

	public void setPayeeCity(String payeeCity) {
		this.payeeCity = payeeCity;
	}

	public String getEwRegistrationAmount() {
		return ewRegistrationAmount;
	}

	public void setEwRegistrationAmount(String ewRegistrationAmount) {
		this.ewRegistrationAmount = ewRegistrationAmount;
	}

	public String getEwType() {
		return ewType;
	}

	public void setEwType(String ewType) {
		this.ewType = ewType;
	}

	public String getItlExtRegDate() {
		return itlExtRegDate;
	}

	public void setItlExtRegDate(String itlExtRegDate) {
		this.itlExtRegDate = itlExtRegDate;
	}

	public LinkedHashSet<LabelValueBean> getStates() {
		return states;
	}

	public void setStates(LinkedHashSet<LabelValueBean> states) {
		this.states = states;
	}

	public LinkedHashSet<LabelValueBean> getDistricts() {
		return districts;
	}

	public void setDistricts(LinkedHashSet<LabelValueBean> districts) {
		this.districts = districts;
	}

	public LinkedHashSet<LabelValueBean> getCities() {
		return cities;
	}

	public void setCities(LinkedHashSet<LabelValueBean> cities) {
		this.cities = cities;
	}

	public LinkedHashSet<LabelValueBean> getTehsils() {
		return tehsils;
	}

	public void setTehsils(LinkedHashSet<LabelValueBean> tehsils) {
		this.tehsils = tehsils;
	}

	public String getItlExtWarrantyApplicable() {
		return itlExtWarrantyApplicable;
	}

	public void setItlExtWarrantyApplicable(String itlExtWarrantyApplicable) {
		this.itlExtWarrantyApplicable = itlExtWarrantyApplicable;
	}

	public String getServiceRecordEngine() {
		return serviceRecordEngine;
	}

	public void setServiceRecordEngine(String serviceRecordEngine) {
		this.serviceRecordEngine = serviceRecordEngine;
	}

	public String getGearOilChangeTransmission() {
		return gearOilChangeTransmission;
	}

	public void setGearOilChangeTransmission(String gearOilChangeTransmission) {
		this.gearOilChangeTransmission = gearOilChangeTransmission;
	}

	public String getEngine() {
		return engine;
	}

	public void setEngine(String engine) {
		this.engine = engine;
	}

	public String getClutch() {
		return clutch;
	}

	public void setClutch(String clutch) {
		this.clutch = clutch;
	}

	public String getTransmission() {
		return transmission;
	}

	public void setTransmission(String transmission) {
		this.transmission = transmission;
	}

	public String getBrakes() {
		return brakes;
	}

	public void setBrakes(String brakes) {
		this.brakes = brakes;
	}

	public String getHydraulic() {
		return hydraulic;
	}

	public void setHydraulic(String hydraulic) {
		this.hydraulic = hydraulic;
	}

	public String getTpl() {
		return tpl;
	}

	public void setTpl(String tpl) {
		this.tpl = tpl;
	}

	public String getCheckExternalCrackDamage() {
		return checkExternalCrackDamage;
	}

	public void setCheckExternalCrackDamage(String checkExternalCrackDamage) {
		this.checkExternalCrackDamage = checkExternalCrackDamage;
	}

	public String getItlstatus() {
		return Itlstatus;
	}

	public void setItlstatus(String itlstatus) {
		Itlstatus = itlstatus;
	}

	public String getItlEmployeeId() {
		return itlEmployeeId;
	}

	public void setItlEmployeeId(String itlEmployeeId) {
		this.itlEmployeeId = itlEmployeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	private String moq;
	private String isLubePartInLitre;
	
	public String getMoq() {
		return moq;
	}

	public void setMoq(String moq) {
		this.moq = moq;
	}

	public String getIsLubePartInLitre() {
		return isLubePartInLitre;
	}

	public void setIsLubePartInLitre(String isLubePartInLitre) {
		this.isLubePartInLitre = isLubePartInLitre;
	}

	public Boolean getDecimalQty() {
		return DecimalQty;
	}

	public void setDecimalQty(Boolean decimalQty) {
		DecimalQty = decimalQty;
	}
	
	private String[] modifiedPartsUsed;
	private String[] vendorName;
	private String modifiedPartsUsed_str;
	private String vendorName_str;
	private String biPartCode[];
	private String atmCaseCode[];
	private String biPart;
	private String atmCase;
	
	public String[] getModifiedPartsUsed() {
		return modifiedPartsUsed;
	}

	public void setModifiedPartsUsed(String[] modifiedPartsUsed) {
		this.modifiedPartsUsed = modifiedPartsUsed;
	}

	public String[] getVendorName() {
		return vendorName;
	}

	public void setVendorName(String[] vendorName) {
		this.vendorName = vendorName;
	}

	public String getVendorName_str() {
		return vendorName_str;
	}

	public void setVendorName_str(String vendorName_str) {
		this.vendorName_str = vendorName_str;
	}

	public String getModifiedPartsUsed_str() {
		return modifiedPartsUsed_str;
	}

	public void setModifiedPartsUsed_str(String modifiedPartsUsed_str) {
		this.modifiedPartsUsed_str = modifiedPartsUsed_str;
	}

	public String[] getBiPartCode() {
		return biPartCode;
	}

	public void setBiPartCode(String[] biPartCode) {
		this.biPartCode = biPartCode;
	}

	public String[] getAtmCaseCode() {
		return atmCaseCode;
	}

	public void setAtmCaseCode(String[] atmCaseCode) {
		this.atmCaseCode = atmCaseCode;
	}

	public String getBiPart() {
		return biPart;
	}

	public void setBiPart(String biPart) {
		this.biPart = biPart;
	}

	public String getAtmCase() {
		return atmCase;
	}

	public void setAtmCase(String atmCase) {
		this.atmCase = atmCase;
	}
	
	private String vorJobcard;

	public String getVorJobcard() {
		return vorJobcard;
	}

	public void setVorJobcard(String vorJobcard) {
		this.vorJobcard = vorJobcard;
	}
	
	private String poCreated;

	public String getPoCreated() {
	    return poCreated;
	}

	public void setPoCreated(String poCreated) {
	    this.poCreated = poCreated;
	}

	

	
}
