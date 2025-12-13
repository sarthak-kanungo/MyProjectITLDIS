/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.PrintWriter;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;

import com.common.MethodUtility;

import beans.serviceForm;
import dao.serviceDAO;
import dbConnection.dbConnection;

/**
 *
 * @author jasmeen.kaur
 */
public class createJobCardAction extends DispatchAction {

    //  private static final String SUCCESS = "success";
    String dbPATH = dbConnection.dbPathAuth;

    ///////////////////////////////////////***VEHICLE INFORMATION***////////////////////////////////////////////////
    public ActionForward initVehicleInformation(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        LinkedHashSet<LabelValueBean> jobTypeList = null;
        String showHistory = request.getParameter("showHistory");

        String pathNm = request.getParameter("pathNm");
        String jobTypeStatus = request.getParameter("jobTypeStatus");
        String chassisno = request.getParameter("chassisno");


        try {


            serviceDAO obj = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            // masterDAO mstObj = new masterDAO();
            String jobtype = request.getParameter("jobType");
            String openJC = request.getParameter("openJC");
            // System.out.println("jobtype"+jobtype);
            //  String jobTypeDesc;
            sf.setConstantValue(obj.getHesConstantValue(10));
            if (jobTypeStatus != null && jobTypeStatus.equals("PDI")) {
                sf.setJobType("1");

            }
            String vinid = request.getParameter("vinid");
            sf.setVinid(vinid);
            {
                jobTypeList = obj.getCommonLabelValues("Jobtypemaster", "jobTypeID", "jobTypeDesc,seqNo", "seqNo", "");
            }

            HttpSession session = request.getSession();
            //  String user_id = (String) session.getAttribute("dealerCode");
            LinkedHashSet<LabelValueBean> jobLocationList = obj.getCommonLabelValues("Joblocationmaster", "locationID", "locationDesc,seqNo", "seqNo", "");
            LinkedHashSet<LabelValueBean> serviceTypeList = obj.getCommonLabelValues("Servicetypemaster", "serviceTypeID", "serviceTypeDesc,seqNo", "seqNo", "");
            LinkedHashSet<LabelValueBean> productcategoryList = obj.getCommonLabelValues("Productcategorymaster", "categoryId", "productCategory", "categoryId", "");
            LinkedHashSet<LabelValueBean> eventList = obj.getCommonLabelValues("Eventmaster", "id", "eventName,startDate,endDate", "eventName", (String) session.getAttribute("dealerCode"));
            request.setAttribute("jobTypeList", jobTypeList);
            request.setAttribute("jobLocationList", jobLocationList);
            // request.setAttribute("nextServiceList", nextServiceList);
            // request.setAttribute("jobCardStatusList", jobCardStatusList);
            //  request.setAttribute("ownerDrivenList", ownerDrivenList);
            request.setAttribute("serviceTypeList", serviceTypeList);
            request.setAttribute("productcategoryList", productcategoryList);
            request.setAttribute("eventList", eventList);
            request.setAttribute("jobTypeStatus", jobTypeStatus);
            request.setAttribute("openJC", openJC);
            request.setAttribute("chassisno", chassisno);

            if (sf.getJobCardNo() != null && !sf.getJobCardNo().equals("")) {
                sf = obj.getJobCard_vehicale_DetailFor_singleJobcard(sf, sf.getJobCardNo(), sf.getVinNo());
                sf.setJobcardview("true");
                sf = obj.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
                if (jobtype != null && jobtype.equals("PDI")) {
                    sf.setWarrantyApplicable("Y");
                    sf.setJobType("1");
                    request.setAttribute("pdiPage", "Y");

                }
                request.setAttribute("status", sf.getStatus());
            } else {
                sf.setStatus("OPEN");
            }
            request.setAttribute("serviceform", sf);
            request.setAttribute("jobType", sf.getJobType());
            request.setAttribute("pathNm", pathNm);
            request.setAttribute("showHistory", showHistory);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        if (showHistory != null && showHistory.equalsIgnoreCase("Y")) {
            return mapping.findForward("vehicleInformationHis");
        } else if (pathNm != null && pathNm.equalsIgnoreCase("hisJC")) {
            return mapping.findForward("vehicleInformationHis");
        } else if (pathNm != null && pathNm.equalsIgnoreCase("fillJC")) {
            return mapping.findForward("vehicleInformationFill");
        }
        request.setAttribute("openJC", request.getParameter("openJC"));
        return mapping.findForward("vehicleInformationFill");

    }

    ///////////////////////////////////////***CUSTOMER INFORMATION***////////////////////////////////////////////////
    public ActionForward initCustomerInformation(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String pathNm = request.getParameter("pathNm");
        // System.out.println("pathNm"+);
        try {

            serviceForm sf = (serviceForm) form;
            String status = request.getParameter("status");
            String jobCardView = request.getParameter("jobcardview");
            String vin_details_type = request.getParameter("vin_details_type");
            sf.setVin_details_type(vin_details_type);
            sf.setJctype("fillJC");
            String showHistory = request.getParameter("showHistory");

            String vinid = request.getParameter("vinid");
            //System.out.println("vsdfsdfs=="+vinid);
            String jobidvalue = request.getParameter("jobidvalue");
            String openJC = request.getParameter("openJC");
            sf.setVinid(vinid);
            serviceDAO obj = new serviceDAO();
            if (jobCardView.equals("true")) {
                sf = obj.getCustomerDetail4JobcardNo(sf, sf.getJobCardNo(), sf.getVinid());
                sf = obj.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
            } else {
                sf = obj.getCustomerDetailsVinNo(sf, sf.getVinid());
                sf = obj.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
            }
            sf.setStatus(status);
            request.setAttribute("serviceform", sf);
            request.setAttribute("pathNm", pathNm);
            request.setAttribute("vinid", vinid);
            request.setAttribute("jobidvalue", jobidvalue);
            request.setAttribute("showHistory", showHistory);
            request.setAttribute("openJC", openJC);//String openJC = request.getParameter("openJC");
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        String showHistory = request.getParameter("showHistory");
        if (showHistory != null && showHistory.equalsIgnoreCase("Y")) {
            return mapping.findForward("customerInformationHis");
        } else {
            return mapping.findForward("customerInformationFill");
        }

    }
    ///////////////////////////////////////***Complaint***////////////////////////////////////////////////

    public ActionForward initComplaint(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        //  String pathNm = request.getParameter("pathNm");
        String vinid = request.getParameter("vinid");
        try {

            String showHistory = request.getParameter("showHistory");
            String openJC = request.getParameter("openJC");
            serviceForm sf = (serviceForm) form;
            serviceDAO obj1 = new serviceDAO();
            LinkedHashSet<LabelValueBean> aggregateList = obj1.getCommonLabelValues("Aggregatemaster", "aggCode", "aggDesc", "aggDesc", "");
            LinkedHashSet<LabelValueBean> applicationList = obj1.getCommonLabelValues("ApplicationMaster", "appCode", "appDesc", "appDesc", "");
            LinkedHashSet<LabelValueBean> complaintCodeList = obj1.getCommonLabelValues("Complaintcodemaster", "compCode", "compDesc", "compDesc", "");
            // LinkedHashSet<LabelValueBean> causalCodeList = obj1.getCommonLabelValues("Causalcodemaster", "causalCode", "causalDesc", "causalDesc", "");
            //    LinkedHashSet<LabelValueBean> consequenceCodeList = obj1.getCommonLabelValues("Consequencemaster", "consequenceCode", "consequenceDesc", "consequenceDesc", "");
            
            
            LinkedHashSet<LabelValueBean> biPartList = obj1.getLabelValues();
            LinkedHashSet<LabelValueBean> atmCaseList = obj1.getLabelValues();

            sf.setVinid(vinid);
            request.setAttribute("aggregateList", aggregateList);
            request.setAttribute("applicationList", applicationList);
            request.setAttribute("complaintCodeList", complaintCodeList);
            
            
            request.setAttribute("atmCaseList", atmCaseList);
            request.setAttribute("biPartList", biPartList);
            
            
            sf.setConstantValue(obj1.getHesConstantValue(10));
            // request.setAttribute("causalCodeList", causalCodeList);
            // request.setAttribute("consequenceCodeList", consequenceCodeList);
            request.setAttribute("showHistory", showHistory);
            request.setAttribute("openJC", openJC);
            if (sf.getJobCardNo() != null) {
                sf = obj1.getComplaintDetail4JobcardNo(sf, sf.getJobCardNo());
                sf = obj1.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
                String jobCardStatus = sf.getStatus();
                sf.setStatus(jobCardStatus);
            }

            request.setAttribute("pathNm", "fillJC");
            request.setAttribute("serviceform", sf);

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        String showHistory = request.getParameter("showHistory");
        if (showHistory != null && showHistory.equalsIgnoreCase("Y")) {
            return mapping.findForward("initComplaintHis");
        } else {
            return mapping.findForward("initComplaintFill");
        }

    }

    public ActionForward manageComplaintdetail(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String forwardto = "";
        String data_str = "";

        try {
            HttpSession session = request.getSession();
            serviceDAO obj = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            String showHistory = request.getParameter("showHistory");
            request.setAttribute("showHistory", showHistory);
            String op = request.getParameter("operate");
            String user_id = (String) session.getAttribute("user_id");
            String vinid = request.getParameter("vinid");
            sf.setVinid(vinid);
            sf.setDealercode((String) (session.getAttribute("dealerCode")));
            ActionMessages messages = new ActionMessages();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date complaintDate = sdf.parse(sf.getComplaintDate());
            sf.setConstantValue(obj.getHesConstantValue(10));
            Date today = new Date();
            Date condate = new Date(df.format(today));
            condate.setDate(condate.getDate() - Integer.parseInt(sf.getConstantValue()));
            if (condate.compareTo(complaintDate) > 0) {
                messages.add("FAILURE", new ActionMessage("label.common.dateofComplaintwith_validation"));
                request.setAttribute("message", " " + sf.getConstantValue() + " days to Current Date.");
                data_str = "FAILURE@@Invalid Date of Complaint";
                initComplaint(mapping, form, request, response);
                forwardto = "initComplaintFill";  
            } else if (today.compareTo(complaintDate) < 0) {
                messages.add("FAILURE", new ActionMessage("label.common.complaintDate_validation"));
                data_str = "FAILURE@@Invalid Date of Complaint ";
                initComplaint(mapping, form, request, response);
                forwardto = "initComplaintFill";
            } else {
                data_str = obj.addComplaintDetailJobCard(sf, user_id, op);
                sf.setJctype(request.getParameter("jctype"));
                if (data_str.split("@@")[0].equalsIgnoreCase("SUCCESS")) {
                    obj.updateComplaintDateInJC(sf, user_id);
                    sf = obj.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
                    initEstimate(mapping, form, request, response);
                    sf.setJobcardview("true");
                    messages.add("SUCCESS", new ActionMessage("label.common.compSuccess"));
                    request.setAttribute("serviceform", sf);
                    request.setAttribute("vinid", sf.getVinid());
                    request.setAttribute("jobidvalue", sf.getJobId());

                    forwardto = "initEstimateFill";

                } else {
                    messages.add("FAILURE", new ActionMessage("label.common.compFailure"));
                    initComplaint(mapping, form, request, response);
                    forwardto = "initComplaintFill";
                }
            }
            saveErrors(request, messages);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(forwardto);
    }
    ///////////////////////////////////////***Complaint***////////////////////////////////////////////////

    public ActionForward initEstimate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String pathNm = "fillJC";
        try {
            HttpSession session = request.getSession();
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
            serviceDAO obj1 = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            // System.out.println("jobcardno in init estimate" + sf.getJobCardNo());
            LinkedHashSet<LabelValueBean> billList = obj1.getBillCode(request.getParameter("jobType"));
            LinkedHashSet<LabelValueBean> billListLubes = obj1.getBillCodeForLubes(request.getParameter("jobType"));
            LinkedHashSet<LabelValueBean> jobWorkList = obj1.getCommonLabelValues("Otherjobworkmaster", "workID", "workDesc,seqNo", "seqNo", "");
            LinkedHashSet<LabelValueBean> complaintCodeList = obj1.getComplaintCodeOnCompDetails(sf);
            LinkedHashSet<LabelValueBean> actionTakenList = new LinkedHashSet<LabelValueBean>();
            obj1.getParameterActionTaken(sf);
            String vinid = request.getParameter("vinid");
            String openJC = request.getParameter("openJC");
            sf.setVinid(vinid);
            sf.setDealercode((String) (session.getAttribute("dealerCode")));
            obj1.checkStandardPart(sf);
            MethodUtility methodUtility = new MethodUtility();
            sf = obj1.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
      
           
            String priceListCode = (String) session.getAttribute("priceListCode");

            String result = obj1.getEstimateDetail4jobCardNo(sf, sf.getJobCardNo(), priceListCode);

            //System.out.println(billList.size() + "--" + sf.getPartList().size());
            if (sf.getPromisedstr() != null && !sf.getPromisedstr().equals("")) {
                sf.setPromisedHrs(methodUtility.GetDateandTimevalue(sf.getPromisedstr(), "hr"));
                sf.setPromisedTime(methodUtility.GetDateandTimevalue(sf.getPromisedstr(), "min"));
                sf.setPromised(sdf1.format(sdf2.parse(methodUtility.GetDateandTimevalue(sf.getPromisedstr(), "date"))));

            } else {
                sf.setPromisedHrs("");
                sf.setPromisedTime("");
            }
            if (sf.getRequiredBYCustomerstr() != null && !sf.getRequiredBYCustomerstr().equals("")) {
                sf.setRequiredBYCustomerHrs(methodUtility.GetDateandTimevalue(sf.getRequiredBYCustomerstr(), "hr"));
                sf.setRequiredBYCustomerTime(methodUtility.GetDateandTimevalue(sf.getRequiredBYCustomerstr(), "min"));
                sf.setRequiredBYCustomer(sdf1.format(sdf2.parse(methodUtility.GetDateandTimevalue(sf.getRequiredBYCustomerstr(), "date"))));
            } else {
                sf.setRequiredBYCustomerHrs("");
                sf.setRequiredBYCustomerTime("");
            }
            if (sf.getCurrentEstimatestr() != null && !sf.getCurrentEstimatestr().equals("")) {
                sf.setCurrentEstimateHrs(methodUtility.GetDateandTimevalue(sf.getCurrentEstimatestr(), "hr"));
                sf.setCurrentEstimateTime(methodUtility.GetDateandTimevalue(sf.getCurrentEstimatestr(), "min"));
                sf.setCurrentEstimate(sdf1.format(sdf2.parse(methodUtility.GetDateandTimevalue(sf.getCurrentEstimatestr(), "date"))));
            } else {
                sf.setCurrentEstimateHrs("");
                sf.setCurrentEstimateTime("");
            }
            // sf.setStatus(pathNm);
            LabelValueBean lb = null;
            ArrayList<serviceForm> labourchargeList = new ArrayList<serviceForm>();
//if data exist and remaining will add in list
            int t = 0;
            if (!sf.getLabourchargeList().isEmpty()) {
                labourchargeList = sf.getLabourchargeList();
                Iterator<LabelValueBean> itr1 = complaintCodeList.iterator();
                Iterator<serviceForm> itr2;
                serviceForm srv;
                boolean flag = false;
                while (itr1.hasNext()) {
                    lb = (LabelValueBean) itr1.next();

                    flag = false;

                    itr2 = sf.getLabourchargeList().iterator();
                    while (itr2.hasNext()) {
                        srv = itr2.next();

                        if (srv.getComplaintCode_str().equalsIgnoreCase(lb.getLabel() + "@@" + lb.getValue())) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        serviceForm servForm = new serviceForm();
                        servForm.setComplaintCode_str(lb.getLabel() + "@@" + lb.getValue());
                        servForm.setActionTaken_str("");
                        servForm.setLabourChargesAmount_str("0");
                        labourchargeList.add(servForm);
                    }
                }
                sf.setLabourchargeList(labourchargeList);
            } // if not data exist then all data will add
            else {

                if (complaintCodeList.size() > 0) {
                    Iterator<LabelValueBean> itr1 = complaintCodeList.iterator();
                    while (itr1.hasNext()) {
                        lb = (LabelValueBean) itr1.next();
                        serviceForm servForm = new serviceForm();
                        servForm.setComplaintCode_str(lb.getLabel() + "@@" + lb.getValue());
                        servForm.setActionTaken_str("");
                        servForm.setLabourChargesAmount_str("0");
                        labourchargeList.add(servForm);
                    }
                    sf.setLabourchargeList(labourchargeList);
                }
            }




            if (sf.getLabourchargeList() != null && sf.getLabourchargeList().size() > 0) {
                serviceForm sf1 = sf.getLabourchargeList().get(0);
                //  System.out.println("complaint code" + sf1.getActionTaken_str());
                actionTakenList = obj1.getActionTakenOnCompCode(sf1);
            }
            
            String res = obj1.poCreatedForVOR(sf.getJobCardNo());
            sf.setPoCreated(res); 
            request.setAttribute("partList", sf.getPartList());
            request.setAttribute("lubesList", sf.getLubesList());
            request.setAttribute("labourchargeList", sf.getLabourchargeList());
            request.setAttribute("otherchargeList", sf.getOtherchargeList());
            // System.out.println("jobcard view" + sf.getJobcardview());
            request.setAttribute("jobWorkList", jobWorkList);
            request.setAttribute("billList", billList);
            request.setAttribute("billListLubes", billListLubes);
            request.setAttribute("complaintCodeList", complaintCodeList);
            request.setAttribute("actionTakenList", actionTakenList);
            request.setAttribute("serviceform", sf);
            request.setAttribute("openJC", openJC);
            request.setAttribute("VOR", sf.getVorJobcard() == null ? "" : sf.getVorJobcard().trim());
            obj1.getCustomerDetailById(sf);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        
        String showHistory = request.getParameter("showHistory");
        request.setAttribute("showHistory", showHistory);
        if (showHistory != null && showHistory.equalsIgnoreCase("Y")) {
            return mapping.findForward("initEstimateHis");
        } else {
            request.setAttribute("pathNm", pathNm);
            return mapping.findForward("initEstimateFill");
        }


    }

    public ActionForward getPartNumberAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        PrintWriter pw = null;
        try {

            //conn1 = new dbConnection().getDbConnection();
            serviceDAO obj1 = new serviceDAO();
            //serviceForm mf = (serviceForm) form;
            String tableName = "partmaster";
            String partno = request.getParameter("partno");
            String comptype = request.getParameter("comptype");
            String result = obj1.getComponentList(partno, comptype, "Part_Number", tableName, "partType");
            pw = response.getWriter();
            pw.write(result);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(null);
    }

    public ActionForward getPartPriceBypartNo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        try {
            serviceDAO obj1 = new serviceDAO();
            //serviceForm mf = (serviceForm) form;
            HttpSession session = request.getSession();
            String priceListCode = (String) session.getAttribute("priceListCode");
            String invqty = "0.0";
            String TablePartprice = "SpPriceMaster";
            String TablePartmaster = "CatPart";
            String partno = request.getParameter("partno");
            String cat = request.getParameter("cat");
            partno = obj1.getPartno_in_db(partno, cat);
            //adding by annpurna
         // Check if partno exists in Lubes_Part table
            boolean partExistsInLubes = obj1.isPartNoInLubesPart(partno); 
            //String partExistsInLubesString = partExistsInLubes ? "True" : "False";
            System.out.println("partExistsInLubes:"+partExistsInLubes);
            String moq="";
            String result = obj1.getPriceByPartNo(partno, "p1", TablePartprice, TablePartmaster, "price", "partNo", priceListCode);
            if (partno.equals("")) {
                result = "notexist";
            }else {
            	moq=result.split("@@")[2];
            	result=result.substring(0, result.lastIndexOf("@@"));
            }
            if (request.getParameter("jobType").equalsIgnoreCase("35")) {
                partno = obj1.getPartNoCheckBEWJobType(partno, cat);
                if (partno.equals("")) {
                    result = "notexistBEW";
                }
            }
            
            if (!result.equals("") && !(result.equals("notexist") || (result.equals("notexistBEW")))) {
            	
                invqty = obj1.getInventoryQty(partno, session.getAttribute("dealerCode").toString());

                if (!cat.equals("LUBES")) {
                    invqty = invqty.substring(0, invqty.indexOf("."));
                }

                result = result + "@@" + invqty+"@@"+moq+ "@@" + (partExistsInLubes?"Y":"N");
                
                
            }
            
            

            try {
                PrintWriter pw = response.getWriter();
                pw.write(result);
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(null);
    }

    public ActionForward getPartCheck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        try {

            serviceDAO obj1 = new serviceDAO();
            //serviceForm mf = (serviceForm) form;
            String partno = request.getParameter("partno");
            String result = obj1.getPartNoCheck(partno);
            try {
                PrintWriter pw = response.getWriter();
                pw.write(result);
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(null);
    }

    public ActionForward getPartDescAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter pw = null;
        try {
            serviceDAO obj1 = new serviceDAO();
            //serviceForm mf = (serviceForm) form;
            String partno = request.getParameter("partDesc").trim();
            String comptype = request.getParameter("comptype");
            String tableName = "CatPart";
            String result = obj1.getComponentList(partno, comptype, "p1", tableName, "partType");
            pw = response.getWriter();
            pw.write(result);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(null);
    }

    public ActionForward getPartPriceBypartDesc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        try {
            serviceDAO obj1 = new serviceDAO();
            HttpSession session = request.getSession();
            String invqty = "0.0";
            String priceListCode = (String) session.getAttribute("priceListCode");
            String partDesc = request.getParameter("partDesc");
            String TablePartprice = "SpPriceMaster";
            String TablePartmaster = "CatPart";
            String cat = request.getParameter("cat");
            String partno = obj1.getPart_in_db(partDesc);
            String result = obj1.getPriceByPartNo(partno, "partNo", TablePartprice, TablePartmaster, "price", "partNo", priceListCode);
            if (partno.equals("")) {
                result = "notexist";
            }
             if (request.getParameter("jobType").equalsIgnoreCase("35")) {
                partno = obj1.getPartNoCheckBEWJobType(partno, cat);
                 if (partno.equals("")) {
                     result = "notexistBEW";
                 }
            }
            
            if (!result.equals("") &&  !(result.equals("notexist") || (result.equals("notexistBEW")))) {

                invqty = obj1.getInventoryQty(result.split("@@")[0], session.getAttribute("dealerCode").toString());
                result = result + "@@" + invqty;
            }
            
           
           
            try {
                PrintWriter pw = response.getWriter();
                pw.write(result);
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(null);
    }

    ///////////////////////////////////////***Standard Checks***////////////////////////////////////////////////
    public ActionForward initStandardChecks(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        //    String pathNm = request.getParameter("pathNm");
        try {

            serviceDAO obj1 = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            //   String formId = "";
            HttpSession session = request.getSession();
            String dealercode = (String) session.getAttribute("dealerCode");
            String vinid = request.getParameter("vinid");
            String showHistory = request.getParameter("showHistory");
            String openJC = request.getParameter("openJC");

            sf.setVinid(vinid);
            if (request.getParameter("jobType") != null) {
                sf.setJobType(request.getParameter("jobType"));
            }
            sf.setDealercode(dealercode);
            obj1.getFormContent(sf);
            request.setAttribute("serviceform", sf);
            request.setAttribute("showHistory", showHistory);
            request.setAttribute("openJC", openJC);
            sf = obj1.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
            request.setAttribute("pathNm", "fillJC");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        String showHistory = request.getParameter("showHistory");
        if (showHistory != null && showHistory.equalsIgnoreCase("Y")) {
            return mapping.findForward("standardChecksHis");
        } else {
            return mapping.findForward("standardChecksFill");
        }

    }

    public ActionForward manageVehileInformation(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String forwardto = "";
        String data_str = "";
        boolean joCardFill = true;
        String pathNm = request.getParameter("pathNm");
        try {
            // System.out.println("entered");
            HttpSession session = request.getSession();
            serviceDAO obj = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            String user_id = (String) session.getAttribute("user_id");
            sf.setVinid(request.getParameter("vinid"));
            sf.setProductionCategory(request.getParameter("productCategory"));
            sf.setVinNo(request.getParameter("vinNo"));
            sf.setModelFamily(request.getParameter("modelFamily"));
            sf.setEngineNumber(request.getParameter("engineNumber"));
            sf.setDealerJobCardNo(request.getParameter("dealerJobCardNo"));
            sf.setModelCode(request.getParameter("modelCode"));
            sf.setSaleDatestr(request.getParameter("saleDate"));
            String jobType = request.getParameter("jobType");
            sf.setJobType(jobType);
            sf.setModelFamilyDesc(request.getParameter("modelFamilyDesc"));
            sf.setRegistrationNo(request.getParameter("registrationNo"));
            sf.setHmr(request.getParameter("hmr"));
            //ProductionCategory,vinno,modelFamily,engineNumber,dealerJobCardNo,modelCode,saleDate,jobType,modelFamilyDesc,registrationNo,hmr,modelCodeDesc,serviceBookletNo,jobLocation,jobCardDate,currentEstimateTime,currentEstimateHrs,keyIdentificationNo,nextService,jobCardStatus,ownerDriven,setWarrantyApplicable,hmeRadio
            sf.setModelCodeDesc(request.getParameter("modelCodeDesc"));
            sf.setServiceBookletNo(request.getParameter("serviceBookletNo"));
            String event = "0";
            if (request.getParameter("event") != null && !request.getParameter("event").equals("0")) {
                event = request.getParameter("event").split("@@")[0];
            }


            sf.setEvent(event);
            sf.setJobLocation(request.getParameter("jobLocation"));
            sf.setJobCardDateStr(request.getParameter("jobCardDate"));
            sf.setJobCardDate(request.getParameter("jobCardDate"));
            sf.setCurrentEstimateTime(request.getParameter("currentEstimateTime"));
            sf.setCurrentEstimateHrs(request.getParameter("currentEstimateHrs"));
            sf.setKeyIdentificationNo(request.getParameter("keyIdentificationNo"));
            sf.setNextService(request.getParameter("nextService"));

            sf.setJobCardStatus(request.getParameter("jobCardStatus"));
            sf.setOwnerDriven(request.getParameter("ownerDriven"));
            sf.setVorJobcard(request.getParameter("vorJobcard"));
            
			if (sf.getJobType() != null) {

				if (sf.getJobType().equals("41") || sf.getJobType().equals("1")) {
					sf.setWarrantyApplicable("Y");
				}
			}
       
            System.out.println("saledate :-" + sf.getDeliveryDate() + "  modelcode :-" + sf.getModelCode());
            String WarApp = "";
            
			if (jobType != null) {

				if (!jobType.equals("1") && !jobType.equals("41")) {
					String result = obj.getWarrantyModeldetail(sf.getModelCode(), sf.getDeliveryDate());
//              String mon = result.split("@@")[0];
//              String hrs = result.split("@@")[1];
					String mon = result.equals("notexist") ? "0" : result.split("@@")[0];
					String hrs = result.equals("notexist") ? "0" : result.split("@@")[1];

					// -------------------------

					String tractorindate = sf.getJobCardDateStr();
					String sale = sf.getDeliveryDate();
					String hmr = sf.getHmr();

					Integer months = (Integer.parseInt(sale.split("/")[2])
							- Integer.parseInt(tractorindate.split("/")[2])) * 12;
					months -= Integer.parseInt(tractorindate.split("/")[1]) + 1;
					months += Integer.parseInt(sale.split("/")[1]);
					months = months + 1;
					months = Math.abs(months);
					// ------------------------

					if (hmr == null || hmr.equals("")) {
						WarApp = months <= Integer.parseInt(mon) ? "Y" : "N";

					} else {
						WarApp = (months <= Integer.parseInt(mon) && Integer.parseInt(hmr) <= Integer.parseInt(hrs))
								? "Y"
								: "N";
					}

				}
			}
            
            
			if (jobType != null) {

				if (jobType.equals("1") || jobType.equals("41")) {
					WarApp = "Y";
				}
			}
            
            
            
            //sf.setWarrantyApplicable(request.getParameter("warrantyApplicable"));
            sf.setHmeRadio(request.getParameter("hmeRadio"));
            sf.setServiceType(request.getParameter("serviceType"));
            sf.setJobcardview(request.getParameter("jobcardview"));
            sf.setCouponno(request.getParameter("CouponNo"));
            sf.setJobcardpriority(request.getParameter("jobcardpriority"));
            String check = request.getParameter("check");
            String vehicletypedetail = request.getParameter("vindatatype");
            sf.setVin_details_type(vehicletypedetail);
            sf.setJctype(request.getParameter("jctype"));
            sf.setJobCardNo(request.getParameter("jobcardno"));
            sf.setDealercode((String) session.getAttribute("dealerCode"));
            //  data_str = obj.addVehileInformation(sf, user_id, check);
            ActionMessages messages = new ActionMessages();

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            java.util.Date jobCardDate = sf.getJobCardDateStr() == null ? null : sdf.parse(sf.getJobCardDateStr());
            //Calendar jobCal = Calendar.getInstance();
            // jobCal.setTime(jobCardDate);

            sf.setConstantValue(obj.getHesConstantValue(10));

            Date today = new Date();
            Date condate = new Date(df.format(today));

            condate.setDate(condate.getDate() - Integer.parseInt(sf.getConstantValue()));

            if ((sf.getJobcardview()==null || !sf.getJobcardview().equals("true")) && joCardFill) {
                if (condate.compareTo(jobCardDate) > 0) {
                    messages.add("FAILURE", new ActionMessage("label.common.tractorwithValidation"));
                    request.setAttribute("message", " " + sf.getConstantValue() + " days to Current Date.");
                    data_str = "FAILURE@@Invalid Tractor In Date";
                } else if (today.compareTo(jobCardDate) < 0) {
                    messages.add("FAILURE", new ActionMessage("label.common.tractorindate_validation"));
                    data_str = "FAILURE@@Invalid Tractor In Date";
                } else {
                	System.out.println("inside else of addVehileInformation "+sf.getVinNo());
                    data_str = obj.addVehileInformation(sf, user_id, check);//add
                    request.setAttribute("pathNm", "fillJC");
                    if (data_str.split("@@")[0].equalsIgnoreCase("SUCCESS")) {
                        messages.add("SUCCESS", new ActionMessage("label.common.vehicleInfoSuccess"));
                        request.setAttribute("message", data_str.split("@@")[2]);
                    } else {
                        if (data_str.split("@@")[1].equalsIgnoreCase("vehicleInfoFailure4exist")) {
                            messages.add("FAILURE", new ActionMessage("label.common.vehicleInfoFailure4exist"));
                            request.setAttribute("message", sf.getVinNo());
                        } else if (data_str.split("@@")[1].equalsIgnoreCase("vehicleInfoFailure4jobType")) {
                            messages.add("FAILURE", new ActionMessage("label.common.vehicleInfoFailure4jobType"));
                            request.setAttribute("message", sf.getVinNo());
                        } else if (data_str.split("@@")[1].equalsIgnoreCase("freeServiceCheckManual")) {
                            messages.add("FAILURE", new ActionMessage("label.common.freeServiceCheckManual"));
                            request.setAttribute("message", sf.getVinNo());
                        } else if (data_str.split("@@")[1].equalsIgnoreCase("freeServiceCheck")) {
                            messages.add("FAILURE", new ActionMessage("label.common.freeServiceCheck"));
                            request.setAttribute("message", sf.getVinNo());
                        } else if (data_str.split("@@")[1].equalsIgnoreCase("vehicleInfoFailure4locCode")) {
                            messages.add("FAILURE", new ActionMessage("label.common.vehicleInfoFailure4locCode"));
                        } else if (data_str.split("@@")[1].equalsIgnoreCase("vehicleInfoFailure")) {
                            messages.add("FAILURE", new ActionMessage("label.common.vehicleInfoFailure"));
                        } else if (data_str.split("@@")[1].equalsIgnoreCase("vehicleInfoFailure4pdicreated")) {
                            messages.add("FAILURE", new ActionMessage("label.common.vehicleInfoFailureforpdicreated"));
                        } else if (data_str.split("@@")[1].equalsIgnoreCase("jobcardFailure4withpdiins")) {
                            messages.add("FAILURE", new ActionMessage("label.common.jobcardFailure4withpdiins"));
                        }
                    }
//                    data_str = obj.getJobEligibiltyForTSN(sf.getVinNo(), sf.getHmr());//add
//                    System.out.println("data_strdata_str"+data_str);
//                    if (data_str.split("@@")[0].equalsIgnoreCase("N")) {
//                        //messages.add("FAILURE", new ActionMessage("label.common.freeServiceCheck"));
//                        request.setAttribute("message", "<br>"+data_str.split("@@")[1]);
//                    }
                }
                

            } else {

                data_str = obj.UpdateVehileInformation(sf, user_id);//update
                //System.out.println("data_str" + data_str);
                if (data_str.split("@@")[0].equalsIgnoreCase("SUCCESS")) {
                    messages.add("SUCCESS", new ActionMessage("label.common.vehicleInfoUpdateSuccess"));
                    request.setAttribute("message", data_str.split("@@")[2]);
                } else {
                    messages.add("FAILURE", new ActionMessage("label.common.vehicleInfoUpdateFailure"));
                }
                //ViewJC
                request.setAttribute("pathNm", "fillJC");
            }
            saveErrors(request, messages);
            request.setAttribute("pathNm", pathNm);
            if (data_str.split("@@")[0].equalsIgnoreCase("SUCCESS")) {
                sf.setJobCardNo(data_str.split("@@")[2]);
                if (sf.getJctype() != null && sf.getJctype().equals("PDI")) {
                    initcustomerinformationbydealer(mapping, sf, request, response);
                } else {
                    sf = obj.getCustomerDetail4JobcardNo(sf, sf.getJobCardNo(), (sf.getDealercode() + "-" + sf.getVinNo()));
                }
                sf = obj.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
                request.setAttribute("vinid", sf.getVinid());
                request.setAttribute("status", sf.getJobCardStatus());
                sf.setJobcardview("true");
                request.setAttribute("serviceform", sf);
                request.setAttribute("vehicletypedetail", vehicletypedetail);

                forwardto = "customerInformationFill";

            } else {

                initVehicleInformation(mapping, form, request, response);

                sf.setJobType(jobType);
                sf.setJobcardview("");
                sf.setVin_details_type("");
                request.setAttribute("serviceform", sf);

                if (sf.getJctype() != null && sf.getJctype().equals("JPDI")) {
                    forwardto = "viewPDI";
                } else {
                    forwardto = "vehicleInformationFill";
                }
            }



        } catch (Exception e1) {
            e1.printStackTrace();

        }
        return mapping.findForward(forwardto);
    }

    public ActionForward manageCustomerPayeeInformation(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String data_str = "";
        String forwardto = "";
        // String pathNm = request.getParameter("pathNm");
        String vinid = request.getParameter("vinid");
        try {
            // System.out.println("vinid====="+vinid);
            HttpSession session = request.getSession();

            serviceDAO obj = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            String user_id = (String) session.getAttribute("user_id");
            sf.setVinid(vinid);

            sf.setCustomerName(request.getParameter("customerName"));
            sf.setVillage(request.getParameter("village"));
            sf.setTaluka(request.getParameter("taluka"));
            sf.setTehsil(request.getParameter("tehsil"));
            sf.setDistrict(request.getParameter("district"));
            sf.setPinCode(request.getParameter("pinCode"));
            sf.setState(request.getParameter("state"));
            sf.setCountry(request.getParameter("country"));
            sf.setMobilePhone(request.getParameter("mobilePhone"));
            sf.setLandline(request.getParameter("landline"));
            sf.setEmailId(request.getParameter("emailId"));

            sf.setPayeeName(request.getParameter("payeeName"));
            sf.setPayeeVillage(request.getParameter("payeeVillage"));
            sf.setPayeeTaluka(request.getParameter("payeeTaluka"));
            sf.setPayeeTehsil(request.getParameter("payeeTehsil"));
            sf.setPayeeDistrict(request.getParameter("payeeDistrict"));
            sf.setPayeePinCode(request.getParameter("payeePinCode"));
            sf.setPayeeState(request.getParameter("payeeState"));
            sf.setPayeeCountry(request.getParameter("payeeCountry"));
            sf.setPayeeMobilePhone(request.getParameter("payeeMobilePhone"));
            sf.setPayeeLandline(request.getParameter("payeeLandline"));
            sf.setPayeeEmailId(request.getParameter("payeeEmailId"));

            String jobno = request.getParameter("jobno");
            sf.setJobCardNo(jobno);
            String vinno = request.getParameter("vinno");
            sf.setVinNo(vinno);

            sf.setSizeoflandhold(request.getParameter("sizeoflandholding"));
            sf.setMaincrops(request.getParameter("maincrops"));
            sf.setOccupation(request.getParameter("occupation"));
            // sizeLandHolding     mainCrops     occupation
            //vindatatype
            String vindatatype = request.getParameter("vindatatype");
            sf.setVin_details_type(vindatatype);
            sf.setVinid(request.getParameter("vinid"));//600ID3163LDBG
            sf.setJobType(request.getParameter("jobType"));
            sf.setJobId(request.getParameter("jobidvalue"));
            sf.setModelCode(request.getParameter("modelCode"));

            data_str = obj.addCustomerPayeeInformation(sf, user_id, jobno, vinno);
            sf.setJctype(request.getParameter("jctype"));

            sf.setJobcardview("true");
            ActionMessages messages = new ActionMessages();
            // request.setAttribute("show_message", data_str.split("@@")[1]);
            // request.setAttribute("result", data_str.split("@@")[0].toUpperCase());
            if (data_str.split("@@")[0].equalsIgnoreCase("SUCCESS")) {
                initStandardChecks(mapping, sf, request, response);
                sf = obj.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
                messages.add("SUCCESS", new ActionMessage("label.common.custInfoSuccess"));
                request.setAttribute("message", sf.getJobCardNo());
                request.setAttribute("serviceform", sf);
//                request.setAttribute("message", data_str.split("@@")[1]);
                request.setAttribute("pathNm", "fillJC");
                forwardto = "standardChecksFill";

            } else {
                sf = obj.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
                messages.add("FAILURE", new ActionMessage("label.common.custInfoFailure"));
                request.setAttribute("serviceform", sf);
//                request.setAttribute("message", data_str.split("@@")[1]);
                request.setAttribute("pathNm", "fillJC");
                forwardto = "standardChecksFill";
            }
            saveErrors(request, messages);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(forwardto);
    }
    // Init History details for vehicle based on vinNO created on 15/05/14 by Megha

    public ActionForward init_vehicle_History(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {

            serviceDAO obj = new serviceDAO();
            serviceForm sf = (serviceForm) form;
//            sf = obj.getHeaderDetail4JobcardNo(sf,request.getParameter("jobCardNo"));

            String openJC = request.getParameter("openJC");

            ArrayList<serviceForm> vehicle_HistoryList = obj.getvehicle_History(sf, sf.getVinNo());
            String vinid = request.getParameter("vinid");
            sf.setVinid(vinid);
            // System.out.println("vehicle_HistoryList"+vehicle_HistoryList);
            request.setAttribute("vehicle_HistoryList", vehicle_HistoryList);
            request.setAttribute("serviceform", sf);
            request.setAttribute("pathNm", "fillJC");
            request.setAttribute("openJC", openJC);

//            System.out.println(""+sf.getProductionCategory_Desc()+"-"+sf.getJobTypeDesc());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward("vehicle_History_detailFill");
    }

    // Init status created on 15/05/14 by Anand
    public ActionForward initStatus(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String pathNm = request.getParameter("pathNm");
        try {
            String flagvalue = "notexist";
            String checkactiontaken = "notexist";
            String checkComplaintStatus = "notexist";
            serviceDAO obj1 = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            HttpSession session = request.getSession();
            String dealercode = (String) session.getAttribute("dealerCode");
            LinkedHashSet<LabelValueBean> bayList = obj1.getBayCode();
            LinkedHashSet<LabelValueBean> mechanicList = obj1.getMechanicCode(dealercode);
            LinkedHashSet<LabelValueBean> inspectionByList = obj1.getInspectionBy(dealercode);
            //boolean iswarranty=obj1.checkWarrantyApplicable(sf, sf.getJobCardNo());
            String vinid = request.getParameter("vinid");
            String openJC = request.getParameter("openJC");
            sf.setVinid(vinid);
            obj1.getParameterActionTaken(sf);
            sf = obj1.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
            MethodUtility methodUtility = new MethodUtility();
            sf = obj1.getStatusDetail4jobCardNo(sf, sf.getJobCardNo());
            flagvalue = obj1.checkWarrantyApplicable(sf.getJobCardNo());
            checkactiontaken = obj1.checkActualActiontaken(sf.getJobCardNo());
            checkComplaintStatus = obj1.checkComplaintStatus(sf.getJobCardNo());
            if (sf.getWorkStarted()!=null && !sf.getWorkStarted().equalsIgnoreCase("")) {
                sf.setWorkStartedHrs(methodUtility.GetDateandTimevalue(sf.getWorkStarted(), "hr"));
                sf.setWorkStartedMins(methodUtility.GetDateandTimevalue(sf.getWorkStarted(), "min"));
                sf.setWorkStarted(methodUtility.GetDateandTimevalue(sf.getWorkStarted(), "date"));
            } else {
                sf.setWorkStartedHrs("");
                sf.setWorkStartedMins("");
            }
            if (sf.getWorkFinished()!=null && !sf.getWorkFinished().equalsIgnoreCase("")) {
                sf.setWorkFinishedHrs(methodUtility.GetDateandTimevalue(sf.getWorkFinished(), "hr"));
                sf.setWorkFinishedMins(methodUtility.GetDateandTimevalue(sf.getWorkFinished(), "min"));
                sf.setWorkFinished(methodUtility.GetDateandTimevalue(sf.getWorkFinished(), "date"));
            } else {
                sf.setWorkFinishedHrs("");
                sf.setWorkFinishedMins("");
            }
            if (sf.getVehicleOut()!=null && !sf.getVehicleOut().equalsIgnoreCase("")) {
                sf.setVehicleOutHrs(methodUtility.GetDateandTimevalue(sf.getVehicleOut(), "hr"));
                sf.setVehicleOutMins(methodUtility.GetDateandTimevalue(sf.getVehicleOut(), "min"));
                sf.setVehicleOut(methodUtility.GetDateandTimevalue(sf.getVehicleOut(), "date"));
            } else {
                sf.setVehicleOutHrs("");
                sf.setVehicleOutMins("");
            }
//                System.out.println(""+sf.getWorkStarted()+"-"+sf.getWorkStartedHrs()+"-"+sf.getWorkStartedMins());
            request.setAttribute("delayReasonList", obj1.getDealyReasonList());
            request.setAttribute("mechanicList", mechanicList);
            request.setAttribute("bayList", bayList);
            request.setAttribute("inspectionByList", inspectionByList);
            request.setAttribute("serviceform", sf);
            request.setAttribute("pathNm", "fillJC");
            request.setAttribute("openJC", openJC);
            request.setAttribute("wtyflag", flagvalue);
            request.setAttribute("checkactiontaken", checkactiontaken);
            request.setAttribute("checkComplaintStatus", checkComplaintStatus);
            
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        request.setAttribute("pathNm", pathNm);
        String showHistory = request.getParameter("showHistory");
        request.setAttribute("showHistory", showHistory);


        if (showHistory != null && showHistory.equalsIgnoreCase("Y")) {
            return mapping.findForward("initStatusHis");
        } else {
            return mapping.findForward("initStatusFill");
        }
    }

    public ActionForward manageStatus(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forwardto = "";

        String data_str = "";
        try {
            HttpSession session = request.getSession();
            serviceDAO obj = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            //System.out.println("sf first time"+sf);
            String user_id = (String) session.getAttribute("user_id");
            //  String jobstatusid=request.getParameter("jobstatusid");
            String actiontype = request.getParameter("actiontype");
            data_str = obj.addStatus(sf, user_id, actiontype);
            sf.setJctype(request.getParameter("jctype"));
//            request.setAttribute("message", data_str.split("@@")[1]);
            request.setAttribute("result", data_str.split("@@")[0].toUpperCase());
            sf = obj.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
            ActionMessages messages = new ActionMessages();
            if (data_str.split("@@")[0].equalsIgnoreCase("SUCCESS")) {


                if (actiontype.equals("saveandclose")) {
                    messages.add("SUCCESS", new ActionMessage("label.common.statuscloseSuccess"));
                } else {
                    messages.add("SUCCESS", new ActionMessage("label.common.statusSuccess"));
                }
                initStatus(mapping, sf, request, response);
                request.setAttribute("serviceform", sf);
                forwardto = "initStatusFill";
            } else {
                messages.add("FAILURE", new ActionMessage("label.common.statusFailure"));
                initStatus(mapping, form, request, response);
                initStatus(mapping, sf, request, response);
                forwardto = "initStatusFill";
            }
            saveErrors(request, messages);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return mapping.findForward(forwardto);
    }

    // show single job card detail of given vehicle id   created on 16/05/14 by Megha
    public ActionForward show_SingleJobcarddetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        boolean show_History = false;
        try {

            HttpSession session = request.getSession();
            serviceForm sf = (serviceForm) form;
            serviceDAO obj = new serviceDAO();

            String user_id = (String) session.getAttribute("user_id");

            if (request.getParameter("showHistory") != null && request.getParameter("showHistory").equals("Y")) {
                show_History = true;
            }

            String vinid = request.getParameter("vinid");
            sf.setVinid(vinid);
//            System.out.println("" + sf.getJobCardNo());

//            session.setAttribute("jobid", sf.getJobCardNo());
            LinkedHashSet<LabelValueBean> jobTypeList = obj.getCommonLabelValues("Jobtypemaster", "jobTypeID", "jobTypeDesc,seqNo", "seqNo", "");
            LinkedHashSet<LabelValueBean> jobLocationList = obj.getCommonLabelValues("Joblocationmaster", "locationID", "locationDesc,seqNo", "seqNo", "");
            LinkedHashSet<LabelValueBean> serviceTypeList = obj.getCommonLabelValues("Servicetypemaster", "serviceTypeID", "serviceTypeDesc,seqNo", "seqNo", "");
            LinkedHashSet<LabelValueBean> productcategoryList = obj.getCommonLabelValues("Productcategorymaster", "categoryId", "productCategory", "productCategory", "");
            LinkedHashSet<LabelValueBean> eventList = obj.getCommonLabelValues("Eventmaster", "id", "eventName", "eventName", (String) session.getAttribute("dealerCode"));
            //LinkedHashSet<LabelValueBean> nextServiceList = obj.getCommonLabelValue("NextServiceMaster", "NextServiceID", "NextServiceDesc", "SeqNo", "");
            //LinkedHashSet<LabelValueBean> jobCardStatusList = obj.getCommonLabelValue("JobCardStatusMaster", "StatusID", "StatusDesc", "SeqNo", "");
            //LinkedHashSet<LabelValueBean> ownerDrivenList = obj.getCommonLabelValue("OwnerDrivenMaster", "OwnerDrivenID", "OwnerDrivenDesc", "SeqNo", "");




            request.setAttribute("jobTypeList", jobTypeList);
            request.setAttribute("jobLocationList", jobLocationList);
            //  request.setAttribute("nextServiceList", nextServiceList);
            // request.setAttribute("jobCardStatusList", jobCardStatusList);
            // request.setAttribute("ownerDrivenList", ownerDrivenList);
            request.setAttribute("serviceTypeList", serviceTypeList);
            request.setAttribute("productcategoryList", productcategoryList);
            request.setAttribute("eventList", eventList);
            sf = obj.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
            sf.setJobcardview("true");
            sf = obj.getJobCard_vehicale_DetailFor_singleJobcard(sf, sf.getJobCardNo(), sf.getVinNo());
            request.setAttribute("jobType", "ViewJC");
            request.setAttribute("CloseForm", "CLOSE");
            request.setAttribute("serviceform", sf);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (show_History) {
            return mapping.findForward("vehicleInformationHis");
        } else {
            return mapping.findForward("vehicleInformationFill");
        }

    }

    public ActionForward getVimNumberDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        try {

            HttpSession session = request.getSession();

            serviceDAO obj = new serviceDAO();
            serviceForm sf = (serviceForm) form;

            String vinno = request.getParameter("vinNo").trim();

            String vindetails = obj.getVinDetails(vinno, (String) session.getAttribute("dealerCode"));

            // System.out.println("vindetails==" + vindetails);
            PrintWriter pw = response.getWriter();

            pw.write(vindetails);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(null);
    }

    // By Anand 19 May
    public ActionForward manageEstimate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String forwardto = "";
        String data_str = "";
        try {
            HttpSession session = request.getSession();

            serviceDAO obj = new serviceDAO();

            serviceForm sf = (serviceForm) form;
            //System.out.println("estimate part no" + sf.getPartNo());
            
          
            
            sf.setJobId(request.getParameter("jobidvalue"));
            sf.setVinid(request.getParameter("vinid"));
            String vinid = request.getParameter("vinid");
            sf.setVinid(vinid);
            String user_id = (String) session.getAttribute("user_id");
            //data_str = obj.addComplaintDetailJobCard(compVerbatim, aggCode, user_id, compCode, causalCode, consequenceCode, applicationCode,subaggCode,vendorCode,jobID, foremanObservation);
            data_str = obj.addEstimate(sf, user_id);
//            request.setAttribute("message", data_str.split("@@")[1]);
            request.setAttribute("result", data_str.split("@@")[0].toUpperCase());
            sf.setJctype(request.getParameter("jctype"));
            ActionMessages messages = new ActionMessages();
            
           if (data_str.split("@@")[0].equalsIgnoreCase("SUCCESS")) {
                sf = obj.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
                String res = obj.poCreatedForVOR(sf.getJobCardNo());
                sf.setPoCreated(res);
                initActual(mapping, form, request, response);
                messages.add("SUCCESS", new ActionMessage("label.common.estimateSuccess"));
                sf.setJobcardview("true");
                request.setAttribute("serviceform", sf);
                forwardto = "initActualFill";
            }
           
           else {
        	    messages.add("FAILURE", new ActionMessage("label.common.estimateFailure"));
                sf = obj.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
                String res = obj.poCreatedForVOR(sf.getJobCardNo());
                sf.setPoCreated(res);
                sf.setJobcardview("true");
                request.setAttribute("serviceform", sf);
                initEstimate(mapping, form, request, response);
                forwardto = "initEstimeteFill";
            }
            saveErrors(request, messages);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(forwardto);
    }

    //
    public ActionForward getvinNumberAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter pw = null;
        try {
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            serviceDAO obj1 = new serviceDAO();
            String vinNo = request.getParameter("vinNo");
            String jctype = request.getParameter("jctype");
            String result = obj1.getVinNoList(vinNo, jctype, user_id, (String) session.getAttribute("dealerCode"));
            pw = response.getWriter();
            pw.write(result);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(null);
    }

    //getVimNumberDetailsFromServer
    public ActionForward getVimNumberDetailsFromServer(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        Connection conn1 = null;
        try {
            conn = new dbConnection().getConnection();
            conn1 = new dbConnection().getConnectionNew();
            serviceDAO obj = new serviceDAO(conn);
            // serviceForm sf = (serviceForm) form;
            // System.out.println("connconn" + conn);
            String vinno = request.getParameter("vinNo").trim();

            HttpSession session = request.getSession();
            String dealerCode = (String) session.getAttribute("dealerCode");

            String vindetails = obj.getVimDetailsFromServer(vinno, conn1, dealerCode);

            // System.out.println("vindetails==" + vindetails);
            PrintWriter pw = response.getWriter();

            pw.write(vindetails);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(null);
    }

//getWarrantyModeldetail(String vinno,String modelcode,String saledate,Connection conn)
    public ActionForward getWarrantyModeldetail(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            String modelno, saledate, vindetails;
            serviceDAO obj = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            PrintWriter pw = null;

            modelno = request.getParameter("modelcode").trim();
            saledate = request.getParameter("saledate").trim();
            vindetails = obj.getWarrantyModeldetail(modelno, saledate);
            pw = response.getWriter();
            pw.write(vindetails);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(null);
    }

// manage Standard Checks created on 23/05/14 by Megha
    public ActionForward manageStandardChecksdetail(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String forwardto = "";
        String data_str = "";

        try {
            HttpSession session = request.getSession();
            serviceDAO obj = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            //    String user_id = (String) session.getAttribute("user_id");
            int contentI;
            String vinid = request.getParameter("vinid");
            sf.setVinid(vinid);
            ArrayList<String> dataList = null;
            TreeMap<Integer, ArrayList<String>> tMap = new TreeMap();
            String[] contentId = sf.getContentId();
            String subContentId[] = null;
            String checkpoints[] = null;
            //    String status[] = null;
            String observations[] = null;
            String actionTakens[] = null;
            sf.setModelCode(sf.getJobType().substring(sf.getJobType().indexOf("@") + 1, sf.getJobType().length()));
            sf.setJobType(sf.getJobType().substring(0, sf.getJobType().indexOf("@")));
            sf.setJobcardview("true");
            for (int c = 0; c < contentId.length; c++) {
                dataList = new ArrayList();

                contentI = Integer.valueOf(contentId[c]);

                tMap.put(contentI, dataList);

                subContentId = request.getParameterValues(contentId[c] + "SubContent");
                checkpoints = request.getParameterValues(contentId[c] + "checkpoints");
                //status = request.getParameterValues(contentId[c] + "status");
                observations = request.getParameterValues(contentId[c] + "observations");
                actionTakens = request.getParameterValues(contentId[c] + "actionTaken");
                for (int s = 0; s < checkpoints.length; s++) {
                    dataList.add(subContentId == null ? "0" : subContentId[s]);
                    dataList.add(checkpoints[s]);
                    dataList.add(observations[s]);
                    dataList.add(actionTakens[s]);
                    //dataList.add(status[s]);
                }
            }
            sf.setJctype(request.getParameter("jctype"));
            data_str = obj.addStandardChecksData(sf, tMap);
            sf = obj.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
//            request.setAttribute("message", data_str.split("@@")[1]);
            request.setAttribute("serviceform", sf);
            ActionMessages messages = new ActionMessages();
            if (data_str.split("@@")[0].equalsIgnoreCase("SUCCESS")) {
                messages.add("SUCCESS", new ActionMessage("label.common.stndChkSuccess"));
                initComplaint(mapping, sf, request, response);
                forwardto = "initComplaintFill";
            } else {
                messages.add("FAILURE", new ActionMessage("label.common.stndChkFailure"));
                forwardto = "initComplaintFill";
            }
            saveErrors(request, messages);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(forwardto);
    }

    public ActionForward getServiceHrsAjax(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        try {

            serviceDAO obj = new serviceDAO();
            //    serviceForm sf = (serviceForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            PrintWriter pw = response.getWriter();
            String nameObj = request.getParameter("nameObj");
            //   String objjCode = request.getParameter("objjCode");
            String result = obj.getServiceHrsById(user_id, nameObj, (String) session.getAttribute("dealerCode"));
            pw.print(result);

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return mapping.findForward(null);
    }

    public ActionForward initActual(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String pathNm = "fillJC";
        String result = "";
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            SimpleDateFormat sdf2 = new SimpleDateFormat("dd/MM/yyyy");

            serviceDAO obj1 = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            HttpSession session = request.getSession();
            String openJC = request.getParameter("openJC");
            LinkedHashSet<LabelValueBean> billList = obj1.getBillCode(request.getParameter("jobType"));
            LinkedHashSet<LabelValueBean> billListLubes = obj1.getBillCodeForLubes(request.getParameter("jobType"));
            
            LinkedHashSet<LabelValueBean> jobWorkList = obj1.getCommonLabelValues("Otherjobworkmaster", "workID", "workDesc,seqNo", "seqNo", "");
            LinkedHashSet<LabelValueBean> complaintCodeList = obj1.getComplaintCodeOnCompDetails(sf);
            LinkedHashSet<LabelValueBean> actionTakenList = new LinkedHashSet<LabelValueBean>();
            LinkedHashSet<LabelValueBean> modifiedPartsUsedList = obj1.getLabelValues();
            
            String vinid = request.getParameter("vinid");
            sf.setVinid(vinid);
            sf.setDealercode((String) (session.getAttribute("dealerCode")));
            obj1.getParameterActionTaken(sf);
            LabelValueBean lb = null;
            obj1.checkStandardPart(sf);
            MethodUtility methodUtility = new MethodUtility();
            String priceListCode = (String) session.getAttribute("priceListCode");

            System.out.println("inside init actual :" + sf.getJobCardNo());
            String existactualsentry = obj1.isExistActual(sf);
            
            System.out.println("existactualsentry "+ existactualsentry);

             sf = obj1.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
            sf.setDealercode((String) session.getAttribute("dealerCode"));
            if (existactualsentry.equals("exist")) {                
                result = obj1.getActualDetail4jobCardNo(sf, sf.getJobCardNo());

            } else if (sf.getStatus()!=null && sf.getStatus().equalsIgnoreCase("OPEN")) {
                result = obj1.getEstimateDetail4jobCardNo(sf, sf.getJobCardNo(), priceListCode);
            }

          //  if (result.equalsIgnoreCase("Have data")) {
                // System.out.println(billList.size() + "--" + sf.getPartList().size());
                // System.out.println("sf.getPromisedstr()"+sf.getPromisedstr());
                if (sf.getPromisedstr() != null && !sf.getPromisedstr().equals("")) {
                    sf.setPromisedHrs(methodUtility.GetDateandTimevalue(sf.getPromisedstr(), "hr"));
                    sf.setPromisedTime(methodUtility.GetDateandTimevalue(sf.getPromisedstr(), "min"));
                    sf.setPromised(sdf2.format(sdf.parse(methodUtility.GetDateandTimevalue(sf.getPromisedstr(), "date"))));

                } else {
                    sf.setPromisedHrs("");
                    sf.setPromisedTime("");
                }
                if (sf.getRequiredBYCustomerstr() != null && !sf.getRequiredBYCustomerstr().equals("")) {
                    sf.setRequiredBYCustomerHrs(methodUtility.GetDateandTimevalue(sf.getRequiredBYCustomerstr(), "hr"));
                    sf.setRequiredBYCustomerTime(methodUtility.GetDateandTimevalue(sf.getRequiredBYCustomerstr(), "min"));
                    sf.setRequiredBYCustomer(sdf2.format(sdf.parse(methodUtility.GetDateandTimevalue(sf.getRequiredBYCustomerstr(), "date"))));
                } else {
                    sf.setRequiredBYCustomerHrs("");
                    sf.setRequiredBYCustomerTime("");
                }
                if (sf.getCurrentEstimatestr() != null && !sf.getCurrentEstimatestr().equals("")) {
                    sf.setCurrentEstimateHrs(methodUtility.GetDateandTimevalue(sf.getCurrentEstimatestr(), "hr"));
                    sf.setCurrentEstimateTime(methodUtility.GetDateandTimevalue(sf.getCurrentEstimatestr(), "min"));
                    sf.setCurrentEstimate(sdf2.format(sdf.parse(methodUtility.GetDateandTimevalue(sf.getCurrentEstimatestr(), "date"))));
                } else {
                    sf.setCurrentEstimateHrs("");
                    sf.setCurrentEstimateTime("");
                }
                //  sf.setStatus(pathNm);
           // }

            if (sf.getLabourchargeList()!=null && sf.getLabourchargeList().size() > 0) {
                serviceForm sf1 = sf.getLabourchargeList().get(0);
                //System.out.println("complaint code" + sf1.getActionTaken_str());
                actionTakenList = obj1.getActionTakenOnCompCode(sf1);
            }

            if (sf.getPromisedstr() != null) {
                sf.setPromisedstr(sf.getPromisedstr() == "" ? "" : sdf1.format(sdf.parse(sf.getPromisedstr())));
            }
            if (sf.getRequiredBYCustomerstr() != null) {
                sf.setRequiredBYCustomerstr(sf.getRequiredBYCustomerstr() == "" ? "" : sdf1.format(sdf.parse(sf.getRequiredBYCustomerstr())));
            }
            if (sf.getCurrentEstimatestr() != null) {
                sf.setCurrentEstimatestr(sf.getCurrentEstimatestr() == "" ? "" : sdf1.format(sdf.parse(sf.getCurrentEstimatestr())));
            }
            ArrayList<serviceForm> labourchargeList = new ArrayList<serviceForm>();


//if data exist and remaining will add in list

            if (sf.getLabourchargeList()!=null && !sf.getLabourchargeList().isEmpty()) {
                labourchargeList = sf.getLabourchargeList();
                Iterator<LabelValueBean> itr1 = complaintCodeList.iterator();

                Iterator<serviceForm> itr2;
                serviceForm srv;
                boolean flag = false;
                while (itr1.hasNext()) {
                    lb = (LabelValueBean) itr1.next();

                    flag = false;

                    itr2 = sf.getLabourchargeList().iterator();
                    while (itr2.hasNext()) {
                        srv = itr2.next();

                        if (srv.getComplaintCode_str().equalsIgnoreCase(lb.getLabel() + "@@" + lb.getValue())) {
                            flag = true;
                        }
                    }
                    if (!flag) {
                        serviceForm servForm = new serviceForm();
                        servForm.setComplaintCode_str(lb.getLabel() + "@@" + lb.getValue());
                        servForm.setActionTaken_str("");
                        servForm.setLabourChargesAmount_str("0");
                        labourchargeList.add(servForm);
                    }
                }
                sf.setLabourchargeList(labourchargeList);
            } // if not data exist then all data will add
            else {

                if (complaintCodeList!=null && complaintCodeList.size() > 0) {
                    Iterator<LabelValueBean> itr1 = complaintCodeList.iterator();
                    while (itr1.hasNext()) {
                        lb = (LabelValueBean) itr1.next();
                        serviceForm servForm = new serviceForm();
                        servForm.setComplaintCode_str(lb.getLabel() + "@@" + lb.getValue());
                        servForm.setActionTaken_str("");
                        servForm.setLabourChargesAmount_str("0");
                        labourchargeList.add(servForm);
                    }
                    sf.setLabourchargeList(labourchargeList);
                }
            }
            
            String isEstimateMandatory = obj1.checkEstimateMandatory(sf.getJobCardNo());

            
            
            request.setAttribute("partList", sf.getPartList());
            request.setAttribute("lubesList", sf.getLubesList());
            request.setAttribute("labourchargeList", sf.getLabourchargeList());
            request.setAttribute("otherchargeList", sf.getOtherchargeList());
            // System.out.println("jobcard view" + sf.getJobcardview());
            request.setAttribute("jobWorkList", jobWorkList);
            request.setAttribute("billList", billList);
            request.setAttribute("billListLubes", billListLubes);
            request.setAttribute("modifiedPartsUsedList", modifiedPartsUsedList);
            request.setAttribute("complaintCodeList", complaintCodeList);
            request.setAttribute("actionTakenList", actionTakenList);
            request.setAttribute("openJC", openJC);
            request.setAttribute("serviceform", sf);
            request.setAttribute("VOR", sf.getVorJobcard());
            request.setAttribute("isEstimateMandatory", isEstimateMandatory == null ? "N" : isEstimateMandatory);

            obj1.getCustomerDetailById(sf);

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        request.setAttribute("pathNm", pathNm);
        String showHistory = request.getParameter("showHistory");
        request.setAttribute("showHistory", showHistory);
        if (showHistory != null && showHistory.equalsIgnoreCase("Y")) {
            return mapping.findForward("initActualHis");
        }//
        else {
            return mapping.findForward("initActualFill");
        }



    }

    public ActionForward manageActual(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String forwardto = "";
        String data_str = "";
        try {
            HttpSession session = request.getSession();

            serviceDAO obj = new serviceDAO();

            serviceForm sf = (serviceForm) form;
            String vinid = request.getParameter("vinid");
            sf.setVinid(vinid);
            String showHistory = request.getParameter("showHistory");
            request.setAttribute("showHistory", showHistory);
            String user_id = (String) session.getAttribute("user_id");
            //data_str = obj.addComplaintDetailJobCard(compVerbatim, aggCode, user_id, compCode, causalCode, consequenceCode, applicationCode,subaggCode,vendorCode,jobID, foremanObservation);
            data_str = obj.addActual(sf, user_id);
//            request.setAttribute("message", data_str.split("@@")[1]);
            request.setAttribute("result", data_str.split("@@")[0].toUpperCase());
            sf.setJctype(request.getParameter("jctype"));
            ActionMessages messages = new ActionMessages();
            if (data_str.split("@@")[0].equalsIgnoreCase("SUCCESS")) {
                initStatus(mapping, form, request, response);
                sf = obj.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
                messages.add("SUCCESS", new ActionMessage("label.common.actualsSuccess"));
                sf.setJobcardview("true");
                request.setAttribute("serviceform", sf);
                forwardto = "initStatusFill";
            } else {
                messages.add("FAILURE", new ActionMessage("label.common.actualsFailure"));
                initActual(mapping, form, request, response);
                forwardto = "initActualFill";
            }
            saveErrors(request, messages);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(forwardto);
    }

    public ActionForward initcustomerinformationbydealer(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String pathNm = request.getParameter("pathNm");
        // System.out.println("pathNm"+);
        try {


            serviceForm sf = (serviceForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");//
            String dealercode = (String) session.getAttribute("dealerCode");//

            String status = request.getParameter("status");
            String jobCardView = request.getParameter("jobcardview");
            String vinid = request.getParameter("vinid");
            String jobidvalue = request.getParameter("jobidvalue");
            sf.setVinid(vinid);
            sf.setVin_details_type("MANUAL");

            serviceDAO obj = new serviceDAO();

            sf = obj.getCustomerDetail4JobcardNoByDealer(sf, user_id, dealercode);

            sf.setStatus(status);
            request.setAttribute("serviceform", sf);
            request.setAttribute("pathNm", pathNm);


        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        if (pathNm != null && pathNm.equalsIgnoreCase("hisJC")) {
            return mapping.findForward("customerInformationHis");
        } else {
            return mapping.findForward("customerInformationFill");
        }

    }

    public ActionForward InventoryQty(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
//SpCurrentInventoryView

        PrintWriter pw = null;
        try {

            //conn1 = new dbConnection().getDbConnection();
            serviceDAO obj1 = new serviceDAO();
            //serviceForm mf = (serviceForm) form;

            String partno = request.getParameter("partno");
            String qty = request.getParameter("qty");
            HttpSession session = request.getSession();
            String dealercode = (String) session.getAttribute("dealerCode");
            String result = obj1.getInventoryQty(partno, dealercode);
            pw = response.getWriter();
            pw.write(result);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(null);
    }

    /**
     * Print standard cecklist data created on 22/10/14 by Megha
     */
    public ActionForward printStandardChecklist(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            serviceForm sf = (serviceForm) form;
            serviceDAO obj = new serviceDAO();
            sf.setDealercode(session.getAttribute("dealerCode").toString());//System.out.println("***"+sf.getJobCardNo());
            sf = obj.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
            obj.getFormContent(sf);//obj.getView_FormContent(sf);
            sf.setJctype("print");
            request.setAttribute("dealerCode", sf.getDealercode());
            request.setAttribute("heading", "Standard Checklist");
            request.setAttribute("type", request.getParameter("type"));
            request.setAttribute("serForm", sf);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("printStandardChecklist");
    }

    /**
     * initSearchComplain
     */
    public ActionForward initSearchComplain(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            serviceForm sf = (serviceForm) form;
            serviceDAO obj = new serviceDAO();
            sf.setDealercode(session.getAttribute("dealerCode").toString());
            if (sf.getConstantValue() != null) {
                obj.getsearchComplainData(sf);
//                request.setAttribute("defectList", sf);
            }
            request.setAttribute("serForm", sf);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("initSearchComplain");
    }

    /**
     * initSearchComplain
     */
    public ActionForward searchComplain(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            serviceForm sf = (serviceForm) form;
            serviceDAO obj = new serviceDAO();
            sf.setDealercode(session.getAttribute("dealerCode").toString());//System.out.println("***"+sf.getJobCardNo());
            sf = obj.getHeaderDetail4JobcardNo(sf, sf.getJobCardNo());
            obj.getFormContent(sf);//obj.getView_FormContent(sf);
            sf.setJctype("print");
            request.setAttribute("type", request.getParameter("type"));
            request.setAttribute("serForm", sf);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("initSearchComplain");
    }

    /**
     * Print standard cecklist data created on 22/10/14 by Megha
     */
    public ActionForward printEstimateData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            serviceForm sf = (serviceForm) form;
            serviceDAO obj = new serviceDAO();
            sf.setVinNo(request.getParameter("vinNo").toString());
            sf.setJobCardNo(request.getParameter("jobCardNo").toString());
            sf.setDealercode(session.getAttribute("dealerCode").toString());//System.out.println("***"+sf.getJobCardNo());
            sf = obj.printEstimate(sf);
            sf.setJctype("print");
            request.setAttribute("dealerCode", sf.getDealercode());
            request.setAttribute("heading", "Estimate");
            request.setAttribute("type", request.getParameter("type"));
            request.setAttribute("jobcardDetails", sf);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("printEstimate");
    }
    public ActionForward checkExtWtyPolicyStatus(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            String chassisNo, tractorindate, checkExtWtyStatus;
            serviceDAO obj = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            PrintWriter pw = null;

            chassisNo = request.getParameter("chassisNo").trim();
            tractorindate = request.getParameter("tractorindate").trim();
            checkExtWtyStatus = obj.checkExtWtyPolicyStatus(chassisNo, tractorindate);
            pw = response.getWriter();
            pw.write(checkExtWtyStatus);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(null);
    }
    public ActionForward getJobEligibiltyForTSN(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            String hmr, chassisNo, vindetails,jobType;
            serviceDAO obj = new serviceDAO();
            PrintWriter pw = null;

            hmr = request.getParameter("hmr").trim();
            chassisNo = request.getParameter("chassisNo").trim();
            jobType = request.getParameter("jobType").trim();
            vindetails = obj.getJobEligibiltyForTSN(chassisNo, hmr,jobType);
            pw = response.getWriter();
            pw.write(vindetails);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(null);
    }
    
    public ActionForward checkITLExtWtyPolicyStatus(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            String chassisNo, deliveryDate, checkExtWtyStatus,hmr;
            serviceDAO obj = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            PrintWriter pw = null;

            chassisNo = request.getParameter("chassisNo").trim();
            deliveryDate = request.getParameter("saleDate").trim();
            hmr  = request.getParameter("hmr").trim();
            checkExtWtyStatus = obj.checkITLExtWtyPolicyStatus(chassisNo, deliveryDate,hmr);
            pw = response.getWriter();
            pw.write(checkExtWtyStatus);
            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(null);
    }
    
	public ActionForward checkPartInLubes(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {

		PrintWriter pw = null;
		try {
			HttpSession session = request.getSession();
			String user_id = (String) session.getAttribute("user_id");
			serviceDAO obj1 = new serviceDAO();
			String partNo = request.getParameter("partNo");

			Boolean result = obj1.isPartNoInLubesPart(partNo);
			String writeResult = "false";
			if (result)
				writeResult = "true";

			pw = response.getWriter();
			pw.write(writeResult);

		} catch (Exception e1) {
			e1.printStackTrace();
		}

		return mapping.findForward(null); // null because no page forward, just a response
	}

   


}
