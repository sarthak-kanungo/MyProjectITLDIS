/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.common;

/**
 *
 * @author megha.pandya 
 */
public interface DBQueryConstant {
   public static final String Get_FROM_REGION_MASTER = "select REGION_ID,REGION_NAME from UM_region_master where STATUS='Active' order by REGION_NAME";
   public static final String Get_USER_TYPE_FROM_SPAS101 = "SELECT USER_TYPE_ID,USER_TYPE  FROM UM_spas101 where RoleCategory='EXPORT' and RoleSource='DMS' order by USER_TYPE";
   public static final String Get_country_FROM_MSP_Countries_EXP = "SELECT CountryCode,CountryName  FROM MSP_Countries_EXP";
   public static final String Get_STATE_FROM_STATES = "SELECT STATE_NAME FROM GEN_states order by STATE_NAME";
   public static final String Get_CITY_FROM_CITIES = "SELECT CITY_NAME FROM GEN_cities order by CITY_NAME";
   public static final String Get_COUNTRY_FROM_COUNTRIES = "SELECT COUNTRY_NAME FROM GEN_countries order by COUNTRY_NAME";
   public static final String Get_DealerName_FROM_UM_DealerMaster = "SELECT DealerCode,DealerName FROM UM_DMS_DealerMaster order by DealerName";
   public static final String Get_ComplaintCode = " FROM Complaintcodemaster where isActive='Y' order by compDesc";
   public static final String Get_LABOUR_CODE = "SELECT cm.CompCode, cm.CompDesc,lm.LabourCodeId,lm.LabourCodeDesc, lm.Is_Active,lm.Id,lm.Hrs " +
                                                " FROM labour_hrs_master lm, ComplaintCodeMaster cm " +
                                                " WHERE cm.CompCode=lm.DefectCode ";
   public static final String Insert_LaborCode = "INSERT INTO labour_hrs_master(LabourCodeId, LabourCodeDesc, DefectCode, Hrs, Is_Active) VALUES(?,?,?,?,?)";
   public static final String Update_LaborCode = "UPDATE labour_hrs_master SET is_Active=?,Hrs=? WHERE LabourCodeId=? ";
   public static final String Get_SubAggregate_Code = "SELECT am.aggCode,am.aggDesc,sm.subAggCode, sm.subAggDesc, sm.isActive"+//,sm.id,am.aggid" +
                                        " FROM Subaggregatemaster sm, Aggregatemaster am " +
                                        " WHERE sm.aggCode=am.aggCode";
   public static final String Insert_subAggregateCode ="INSERT INTO subaggregatemaster(SubAggCode, SubAggDesc, is_Active, LastUpdateDate, AggId)  VALUES(?,?,?,?,?)";
   public static final String Update_subAggregateCode = "UPDATE subaggregatemaster SET is_Active=?,LastUpdateDate=?  WHERE SubAggCode=? ";
   public static final String Get_vehicle_History_mysql = "SELECT jd.jobCardDate,jd.dealerCode,jd.hmr,jd.jobCardNo,vd.customerName,vd.mobilePh,jd.status,jd.hMRWorking,jp.jobTypeDesc " +
                                                    " FROM Jobcarddetails as jd,Vehicledetails as vd,Jobtypemaster as jp " +
                                                    " where  vd.vinid=jd.vinid  and  jd.status='BILLED' and  jd.jobTypeId = jp.jobTypeID and jd.vinno ='";////jobCardNo,jobCardDate,CreatedOn,

   public static final String Get_allJobcardData = "select jd.JobCardNo,jd.vinno,jd.PayeeName,jd.MobilePhone,jd.status from jobcarddetails jd  where (jd.DealerCode=? or jd.CreatedBy=?)";

//   public static final String Get_vehicle_JobcardDetail_for_givenJobcardNo_Mysql = "SELECT jd.ProductCatId,vd.vinNo,vd.EngineNo, DATE_FORMAT(vd.SaleDate,'%d/%m/%Y') ,vd.RegNo,vd.SerBookNo,vd.KeyIdentification, vd.OwnerDriven," +
//                                    " jd.JobCardNo,jd.DealerJCNo,jd.JobTypeId,jd.HMRWorking,jd.HMR,jd.LocationId,jd.NextService,jd.Is_warranty_req," +
//                                    " vd.ModelFamily,vd.ModelCode,vd.ModelFamilyDesc,vd.modelCodeDesc,jd.ServiceTypeId,jd.EventId,DATE_FORMAT(jd.JobCardDate,'%d/%m/%Y'),jd.Status,jd.DealerJCNo,jd.couponNo,jd.jobCardPriority," +
//                                    " jd.vin_details_type,jd.JobCardDate FROM jobcarddetails as jd" +
//                                    "  JOIN vehicledetails as vd ON jd.vinNo = vd.vinNo" +
//                                    "  where jd.JobCardNo ='";


        public static final String Get_vehicle_JobcardDetail_for_givenJobcardNo_Mysql = "SELECT jd.productCatId,vd.vinNo,vd.engineNo, vd.deliveryDate ,vd.regNo,vd.serBookNo,vd.keyIdentification, vd.ownerDriven," +
        " jd.jobCardNo,jd.dealerJCNo,jd.jobTypeId,jd.hMRWorking,jd.hmr,jd.locationId,jd.nextService,jd.warrantyStatus," +
        " vd.modelFamily,vd.modelCode,vd.modelFamilyDesc,vd.modelCodeDesc,jd.serviceTypeId,jd.eventId,jd.jobCardDate,jd.status,jd.dealerJCNo,jd.couponNo,jd.jobCardPriority," +
        " jd.vinDetailsType,jd.jobCardDate,vd.vinid FROM  Jobcarddetails as jd , Vehicledetails as vd  where  jd.vinid = vd.vinid and " +
        " jd.jobCardNo ='";
   



   public static final String Get_vehicle_JobcardDetail_for_givenJobcardNo_Sql="SELECT jd.productCatId,vd.vinNo,vd.engineNo,vd.deliveryDate,vd.regNo,vd.serBookNo,vd.keyIdentification, vd.ownerDriven," +
                                    " jd.jobCardNo,jd.dealerJCNo,jd.jobTypeId,jd.hMRWorking,jd.hmr,jd.locationId,jd.nextService,jd.warrantyStatus," +
                                    " vd.modelFamily,vd.modelCode,vd.modelFamilyDesc,vd.modelCodeDesc,jd.serviceTypeId,jd.eventId,jd.jobCardDate,jd.status,jd.dealerJCNo,jd.couponNo,jd.jobCardPriority," +
                                    " jd.vinDetailsType,jd.jobCardDate,vd.vinid,jd.complaintDate,jd.itlEwStatus,jd.vorJobCard  FROM Jobcarddetails as jd" +
                                    "  , Vehicledetails as vd where jd.vinid = vd.vinid" +
                                    "  and jd.jobCardNo ='";
   public static final String Get_vehicle_History_sql = "SELECT jd.jobCardDate,jd.dealerCode,jt.jobTypeDesc,jd.hrm " +
                                                    " FROM Jobcarddetails as jd" +
                                                    " , Jobtypemaster as jt  "+
                                                    " where jd.status='CLOSE' and jt.jobTypeID = jd.jobTypeId and jd.vinNo like '";
   public static final String Get_vendormasterdetail = " select sm.subAggCode,sm.subAggDesc, sam.subAssemblyCode,sam.subAssemblyDesc,sam.isActive from Subassemblymaster as sam, Subaggregatemaster sm" +
                                                       " where sam.subAggCode = sm.subAggCode";
 
   public static final String Insert_VendorMaster ="insert into VendorMaster (VendorCode, VendorDesc,SubAggCode,isActive,LastUpdatedDate) values(?,?,?,?,?)";
   public static final String Update_vendorMaster1 = "UPDATE VendorMaster SET isActive=?,LastUpdatedDate=? WHERE vendorCode=? ";
   public static final String Update_vendorMaster2 = "UPDATE VendorMaster SET vendorCode=?,VendorDesc=?,SubAggCode=?,LastUpdatedDate=? WHERE vendorCode=? and Id=?";
   public static final String Get_CustomerDetail4JobcardNo ="select jd.PayeeName,jd.village,jd.Taluka,jd.Tehsil,jd.District,jd.Pincode,jd.State," +
                                    "jd.Country,jd.MobilePhone,jd.Landline,jd.emailid,vd.CustomerName,vd.VillageName," +
                                    "vd.Taluka,vd.Tehsil,vd.District,vd.Pincode,vd.State,vd.Country,vd.MobilePh,vd.LandlineNo,vd.emailId,vd.sizeLandHolding,vd.mainCrops,vd.occupation" +
                                    " from jobcarddetails jd " +
                                    " JOIN vehicledetails as vd ON jd.vinNo = vd.vinNo" +
                                    " where jd.JobCardNo='";

   public static final String Get_CustomerDetail4JobcardNoByDealer ="FROM UserCheck where  userId='";

   public static final String get_custDetailsVinNo="from Vehicledetails where vinid=?";
   public static final String Get_headerDetail4jobcardno= "select jd.jobCardNo,vd.vinNo,jp.jobTypeDesc,pm.productCategory,jd.warrantyStatus,jd.engineno,jd.jobTypeId,vd.modelCode " +
                                ",vd.vinid,jd.status,jd.payeeName,jd.mobilePhone ,jd.ttlActualPartsAmt,jd.ttlActualLubesAmt,jd.ttlActualOtherChargesAmt,jd.ttlActualLabourChargesAmt,jd.ttlActualAmount,jd.jobCardDate " +
                                " ,vd.customerName,vd.mobilePh,jd.ttlDiscount,u.dealerName,u.dealerCode,vd.modelFamilyDesc,jd.complaintDate,jd.itlEwStatus,jd.vorJobCard from Jobcarddetails jd ,Vehicledetails as vd,Jobtypemaster as jp,Productcategorymaster as pm , Dealervslocationcode as u "+
                                " where  jd.vinid = vd.vinid and jd.jobTypeId = jp.jobTypeID and  jd.productCatId = pm.categoryId  and jd.dealerCode=u.dealerCode and jd.jobCardNo='";
/*
 ,[ttl_actual_parts_amt]
      ,[ttl_actual_lubes_amt]
      ,[ttl_actual_other_charges_amt]
      ,[ttl_actual_labour_charges_amt]
 */
   
   public static final String Get_statusDetail4jobCardNo_mysql = "select js.jobCardId , js.workStartDate , " +
                                " js.workFinishDate ," +
                                " js.vehicleOutDate  ," +
                                " js.bayDesc,js.mechanicName,js.inspectedBy " +
                                " from Jobcardstatus as js" +
                                " , Jobcarddetails as jd  where jd.jobId = js.jobCardId " +
                                " and jd.jobCardNo='";

    public static final String Get_ComplaintDetail4JobcardNo=" FROM Jobcomplaintdetails where jobCardNo='";
    public static final String Get_ComplaintDetailConsequences4JobcardNo=" FROM Jobcmpconsequences as jc where jc.id.cmpNo='";
   
    public static final String Get_statusDetail4jobCardNo_sql = "select js.jobCardId , js.workStartDate , " +
                                " js.workFinishDate ," +
                                " js.vehicleOutDate ," +
                                " js.bayDesc,js.mechanicName,js.inspectedBy " +
                                " from Jobcardstatus as js" +
                                " , Jobcarddetails as jd  where jd.jobId = js.jobCardId" +
                                " and jd.jobCardNo='";
   
//    public static final String Get_Estimate_partDetail4jobcardNo_mysql = "select jd.JobId, DATE_FORMAT(jd.PromiseDate ,'%d/%m/%Y %H:%i')," +
//                                " DATE_FORMAT(jd.ReqCustDate ,'%d/%m/%Y %H:%i')," +
//                                " DATE_FORMAT(jd.EstDate  ,'%d/%m/%Y %H:%i')," +
//                                " js.part_category,js.part_no ,js.part_desc,js.unit_price,js.qty,js.amount,js.unit,js.disc_in_perc,js.final_amt" +
//                                " from jobcarddetails jd" +
//                                " join jobcard_spares_charges as js  ON jd.JobId = js.job_id" +
//                                " where jd.JobCardNo='";
//

    public static final String Get_Estimate_partDetail4jobcardNo_mysql =" select jd.jobId, jd.promiseDate ," +
                " jd.reqCustDate ," +
                " jd.estDate  ," +
                " js.partCategory,js.partNo ,js.partDesc,js.unitPrice,js.qty,js.amount,js.billID,js.discInPerc,js.finalAmt,js.compcode,js.conseqcode" +
                " from Jobcarddetails jd , JobcardSparesCharges as js  " +
                " where   jd.jobCardNo='";
    
    public static final String Get_Estimate_partDetail4jobcardNo_sql = "select js.partCategory,js.partNo ,js.partDesc,js.unitPrice,js.qty,js.amount,js.billID,js.discInPerc,js.finalAmount,js.cmpNo,js.causalOrConseq,jd.ttlEstimatePartsAmt,jd.ttlEstimateLubesAmt,jd.ttlEstimateLabourChargesAmt,jd.ttlEstimateOtherChargesAmt" +
                " from Jobcarddetails jd , JobcardSparesCharges as js  " +
                " where  jd.jobCardNo = js.jobCardNo and  jd.jobCardNo='";

    public static final String Get_Actuals_partDetail4jobcardNo_mysql = " select jd.jobCardNo,jd.promiseDate," +
                                " jd.reqCustDate ," +
                                " jd.actualDate ," +
                                " js.partCategory,js.partNo ,js.partDesc,js.unitPrice,js.qty,js.amount,js.billID,js.discInPerc,js.finalAmount,js.cmpNo,js.causalOrConseq,js.modifiedPartsUsed,js.vendorName" +
                                " from Jobcarddetails as jd ,"+
                                " JobcardSparesActualcharges as js  " +
                                " where jd.jobCardNo = js.jobCardNo and jd.jobCardNo='";

    public static final String Get_Actuals_partDetail4jobcardNo_sql = "select jd.JobId, FORMAT(jd.PromiseDate ,'%d/%m/%Y %H:%i')," +
                                " FORMAT(jd.ReqCustDate ,'%d/%m/%Y %H:%i')," +
                                " FORMAT(jd.ActualDate  ,'%d/%m/%Y %H:%i')," +
                                " js.part_category,js.part_no ,js.part_desc,js.unit_price,js.qty,js.amount,js.unit,js.disc_in_perc,js.final_amt" +
                                " from jobcarddetails jd" +
                                " join jobcard_spares_actualcharges as js  ON jd.JobId = js.job_id" +
                                " where jd.JobCardNo=''";


//    public static final String Get_EstimateLabourCharges4jobCardNo="select el.ComplaintCode,el.ActionTaken,el.labourChargesAmount" +
//                            " from jobcarddetails jd" +
//                            " join estimatelabourcharges as el  ON jd.JobId = el.JobId" +
//                            " where jd.JobCardNo='";
    public static final String Get_EstimateLabourCharges4jobCardNo="select el.cmpNo,el.actionTaken,el.labourChargesAmount,jcd.custVerbatim" +
                            " from Jobcarddetails as jd" +
                            " , Estimatelabourcharges as el,  " +
                            " Jobcomplaintdetails as jcd  " +
                            " where   jd.jobCardNo = el.jobCardNo and jcd.cmpNo=el.cmpNo and jd.jobCardNo='";
    public static final String Get_ActualLabourCharges4jobCardNo="select el.cmpNo,el.actionTaken,el.labourChargesAmount,jcd.custVerbatim" +
                            " from Jobcarddetails as jd ," +
                            " Actualslabourcharges as el,  " +
                            " Jobcomplaintdetails as jcd  " +
                            " where  jd.jobCardNo = el.jobCardNo and jcd.cmpNo=el.cmpNo and jd.jobCardNo='";//////custVerbatim,Jobcomplaintdetails

    public static final String Get_EstimateOtherCharges4jobCardNo="select eo.workCode,eo.workDescription,eo.workAmount" +
                            " from Jobcarddetails as jd" +
                            " , Estimateothercharges as eo " +
                            " where  jd.jobCardNo = eo.jobCardNo and jd.jobCardNo='";

    public static final String Get_ActualOtherCharges4jobCardNo="select eo.workCode,eo.workDescription,eo.workAmount" +
                            " from Jobcarddetails jd" +
                            " ,Actualsothercharges as eo   " +
                            " where  jd.jobCardNo = eo.jobCardNo and jd.jobCardNo='";

    public static final String insert_jobchecklist ="insert into SW_jobchecklist (jobCardId,contentId,subcontentId ,okStatus,remarks,actionTaken,finalStatus) values(?,?,?,?,?,?,?)";
    public static final String getJobId = "select jobId from Jobcarddetails where jobCardNo='";
    public static final String GetFormContent4View = " select jc.id.contentId,cm.contentDesc,jc.id.subcontentId,scm.subContentDesc,jc.okStatus,jc.actionTaken,jc.remarks,jc.finalStatus " +
                                " from Jobchecklist as  jc,ItlContentMaster  as cm,ItlSubContentMaster  as scm ,Jobcarddetails as jd " +
                                " where cm.contentId = jc.id.contentId " +
                                " and scm.subContentId = jc.id.subcontentId " +
                                " and jd.jobCardNo = jc.id.jobCardNo " +
                                " and jd.jobCardNo='";
             

    public static final String Get_allPendingChassisData = "select vd.vinNo from vehicledetails vd where vd.PDI_STATUS='N' and INS_Status='N' and vd.dealerCode='";
    public static final String Get_vehicle_PDIDetail_Mysql = "SELECT vd.DealerCode,vd.ModelCode,vd.Modelfamily,vd.modelCodeDesc,vd.modelfamilyDesc, vd.EngineNo" +
                                    " FROM vehicledetails vd where vd.vinNo='";
                                   

    public static final String CheckRowsInjobchecklist = " from Jobchecklist as jc ,Jobcarddetails jd" +
                //" join jobcarddetails  on jobcarddetails.JobId = jobchecklist.jobCardId " +
                " where jd.jobCardNo = jc.id.jobCardNo  and jd.jobCardNo='";

    public static final String insert_pdichecklist ="insert into pdi_checklist (pdi_id,content_id,subContentId ,ok_status,remarks,actionTaken) values(?,?,?,?,?,?)";

    
    public static final String GetVinNoList =  "select  ti.vinNo,ti.modelFamily,ti.modelCode,ti.customerName,ti.mobilePh from Vehicledetails ti where " +
            "ti.pdiStatus='Y' and ti.insStatus='N' and ti.dealerCode='";


    public static final String GetVinNoDetails = "select ti.EngineNo,ti.DealerCode,ti.ModelCode,ti.ModelFamily,ti.ModelFamilyDesc,ti.modelCodeDesc,ti.deliveryDate,"+
                  "ti.CustomerName,ti.villageName,ti.Taluka,ti.Tehsil,ti.District,ti.State,ti.Country,ti.Pincode,ti.MobilePh,ti.LandlineNo,ti.emailId, "+
                  "ti.sizeLandHolding,ti.mainCrops,ti.occupation from vehicledetails ti" +
                  " where  ti.PDI_STATUS='Y' and ti.INS_Status='N' and ti.vinNo = '";
    
    public static final String GetInsCustInfo = "select ti.customerName,ti.villageName,ti.taluka,ti.tehsil,ti.district," +
                  "ti.state,ti.country,ti.pincode,ti.mobilePh,ti.landlineNo,ti.emailId " +
                  "from TmsChassisvsinsmaster ti  where vinno='";
    public static final String GetContentList4Ins = "from InsChecklistMaster order by contentid";

    public static final String CheckRowsInInsChecklist = "from InsChecklist tc where tc.id.iNSNo='";
    public static final String Insert_Inschecklist = "INSERT INTO SW_ins_checklist(INS_ID, Conetent_id, CheckedStatus) VALUES(?,?,?)";
    public static final String Insert_InstallationDetails ="INSERT INTO installation_details(INS_No, VinNo, INS_DATE, VisitLocation," +
                    "nameVisitor,BuyerName, EngineNo, DealerName, InvoiceNo, VisitDate, delieveryDate,  HMR, majorApplications," +
                    " accessories,FamilyMembers,OthertractorDetail,PaymentMode, BankName,BuyerPrice) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
    //public static final String GetMaxInsId = "select max(insId) from InstallationDetails where MONTH(CURRENT_DATE)= MONTH(insDate)";
    public static final String GetMaxInsId = "select count(*) from InstallationDetails where YEAR(getDate())= YEAR(createdOn) and dealerCode='";

    public static final String GetLocationCode =" from Dealervslocationcode where dealerCode=? ";
    public static final String CheckVinNo_in_InsDetail ="from InstallationDetails where vinNo='";
    public static final String Update_InsStatus = "UPDATE vehicledetails SET  INS_Status='Y',SerBookNo=?,sizeLandHolding=?,mainCrops=?,occupation=? WHERE vinNo=?";
    public static final String Insert_travelCardData = "INSERT INTO SW_ins_travelcard_details(ins_id, vinNo, PartNo, PartVendorName, PartSerialNo) VALUES(?,?,?,?,?)";
    public static final String Get_DealerCodeList = "select u.USER_ID  from UM_user_check as u " +
                    " join UM_spas101 as s on u.USER_TYPE_ID=s.USER_TYPE_ID  where s.USER_TYPE='Dealer'";
    public static final String Insert_wageMaster ="INSERT INTO MSW_wagemaster(WageName, WageValue, isActive, LastUpdatedDate, StartDate ,dealerCode) VALUES(?,?,?,?,?,?)";

    public static final String getServicehrs ="SELECT am.serviceHrs  from Actualslabourcharges al,Actioncodemaster am,Jobcarddetails jc where al.actionTaken=? "
                                              +" and al.cmpNo=? and am.actionCode=al.actionTaken and al.cmpNo=am.compCode and jc.jobCardNo=? and jc.jobCardNo=al.jobCardNo";

    
    public static final String getestimateServicehrs ="SELECT am.serviceHrs  FROM Estimatelabourcharges as al,Actioncodemaster as am,Jobcarddetails as jc where al.actionTaken=?"
                                              +" and al.cmpNo=? and am.actionCode=al.actionTaken and al.cmpNo=am.compCode and jc.jobCardNo=? and jc.jobCardNo=al.jobCardNo";
    public static final String GetVinNoList_PDI =  "select distinct ti.vinNo from vehicledetails ti where " +
            "ti.PDI_STATUS='Y' and ti.dealerCode='";
    public static final String Get_SinglePDIData_Mysql = "SELECT pd.EngineNo,pd.PDI_Date,pd.TractorReceieveDate,vd.modelCodeDesc,vd.modelfamilyDesc, vd.ModelCode,vd.ModelFamily" +
                                     " FROM pdi_details pd join vehicledetails vd on pd.vinNo=vd.vinNo where pd.vinNo='";

    //
   // public static final String GetFormContent4ViewPDI = "select pc.content_Id,cm.CONTENT_DESC,pc.subcontentId,scm.SUB_CONTENT_DESC,pc.ok_status,pc.actionTaken,pc.remarks  from pdi_checklist pc  join itl_content_master  as cm on cm.CONTENT_ID = pc.content_Id Inner join itl_sub_content_master  as scm on scm.SUB_CONTENT_ID = pc.subcontentId  join pdi_details as pd on pd.Id = pc.pdi_id where pd.vinNo='";

    public static final String GetFormContent4ViewPDI = "select pc.content_Id,cm.CONTENT_DESC,pc.subcontentId,scm.SUB_CONTENT_DESC,pc.ok_status,pc.actionTaken,pc.remarks  from pdi_checklist pc  join MSW_itl_content_master  as cm on cm.CONTENT_ID = pc.content_Id Inner join MSW_form_sub_content_master  as scm on scm.SUB_CONTENT_ID = pc.subcontentId  join pdi_details as pd on pd.Id = pc.pdi_id where pd.vinNo='";

    public static final String GetVinNoDetail4INS = " from Vehicledetails ti where  ti.pdiStatus='Y' and ti.insStatus='N' and ti.vinNo = '";
    public static final String insert_formmaster ="insert into MSW_form_master (Modelcode,jobtypeid) values(?,?)";
    public static final String insert_formchecklist ="insert into MSW_form_content (FORM_ID,CONTENT_ID,CONTENT_ORDER ,SUB_CONTENT_ID,SUB_CONTENT_ORDER) values(?,?,?,?,?)";
     public static final String GetFormContent_View = " select fc.content_Id,cm.CONTENT_DESC,fc.SUB_CONTENT_ID,scm.SUB_CONTENT_DESC,fc.CONTENT_ORDER,fc.SUB_CONTENT_ORDER " +
                                                      "     from itl_form_content fc " +
                                                      "     join MSW_itl_content_master  as cm on cm.CONTENT_ID = fc.content_Id " +
                                                      "     join MSW_form_sub_content_master  as scm on scm.SUB_CONTENT_ID = fc.SUB_CONTENT_ID " +
                                                      "     where fc.FORM_ID='";
   public static final String Insertstandardjobpartmaster = "INSERT INTO MSW_standardjobpartmaster(JobTypeId, modelcode, PartNo, PartDesc, PartCategory, UnitPrice, qty)  VALUES(?,?,?,?,?,?,?)";
   
   public static final String GetPartLubesData4jobtype = "select distinct partNo,partDesc,unitPrice,qty,partCategory from Standardjobpartmaster where jobTypeId=? and modelcode=?";

}

