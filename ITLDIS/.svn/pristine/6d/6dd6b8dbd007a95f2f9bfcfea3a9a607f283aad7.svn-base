package action;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;

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

import com.EAMG.common.commonUtilMethods;
import com.common.MethodUtility;

import beans.ContentFormBean;
import beans.pdiForm;
import dao.pdiDAO;
import dao.serviceDAO;
import dbConnection.dbConnection;

public class pdiAction extends DispatchAction {

    private static final String SUCCESS = "success";
    String dbPATH = new dbConnection().dbPathAuth;

    ///////////////////////////////////////***VEHICLE INFORMATION***////////////////////////////////////////////////
    public ActionForward initpdiVehicleInformation(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        LinkedHashSet<LabelValueBean> jobTypeList = null;
        try {

            pdiDAO obj = new pdiDAO();
            pdiForm pf = (pdiForm) form;
            //masterDAO mstObj = new masterDAO();
            //String jobType = request.getParameter("jobType");

            HttpSession session = request.getSession();
            //String user_id = (String) session.getAttribute("user_id");//

            pf = obj.getJobCard_vehicale_DetailFor_singleJobcard(pf, pf.getVinNo());


            request.setAttribute("pdiform", pf);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward("pdiVehicleInformation");
    }

    // Init view all Job card details  created on 15/05/14 by Megha
    public ActionForward init_viewallPendingChassisdetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = null;
        try {
            pdiDAO obj = new pdiDAO();
            pdiForm pf = (pdiForm) form;
            String nameSrch = request.getParameter("Srch") == null ? "" : request.getParameter("Srch");
            String ColumnName = "vinNo";
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");//
            String dealercode = (String) session.getAttribute("dealerCode");
            boolean checkPDI = obj.checkPDI(nameSrch, dealercode);
            if (checkPDI) {
                //request.setAttribute("message", "alert(\"PDI Already Filled for Chassis No. '" + nameSrch + "' \")");
                request.setAttribute("message", "PDI Already Filled for Chassis No. '" + nameSrch + "'");
            } else {
                Vector userFunctionalities = (Vector) session.getAttribute("userFun");
                List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
                if (userFunctionalities.contains("101")) {
                    pf.setDealerCode((String) session.getAttribute("dealerCode"));
                } else {
                    request.setAttribute("dealerList", dealerList);
                }

                /*          if (userFunctionalities.contains("101")) {
                pf.setDealerCode((String) session.getAttribute("dealerCode"));
                } else if (userFunctionalities.contains("102")) {
                ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('" + user_id + "',dm.loginId)", 3);
                request.setAttribute("dealerList", dealerList);
                } else {
                ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d ", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "", 3);
                request.setAttribute("dealerList", dealerList);
                }
                 */
                pf.setDealerCode(pf.getDealerCode() == null ? "ALL" : pf.getDealerCode());
                ArrayList<pdiForm> chassisList = obj.getChassisList(pf, nameSrch, user_id, (String) session.getAttribute("dealerCode"), userFunctionalities);
                request.setAttribute("chassisList", chassisList);
                request.setAttribute("nameSrch", nameSrch);
                request.setAttribute("columnName", ColumnName);
                request.setAttribute("pdiForm", pf);
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        if (request.getParameter("eType") != null && request.getParameter("eType").equals("export")) {
            forward = "allPendingPDIExport";
        } else {
            forward = "view_allpendingjobcardDetails";
        }
        return mapping.findForward(forward);
    }

    public ActionForward show_SinglePendingChassisdetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            HttpSession session = request.getSession();
            pdiForm pf = (pdiForm) form;
            pdiDAO obj = new pdiDAO();
            String dealercode = (String) session.getAttribute("dealerCode");
            pf.setDealerCode((String) session.getAttribute("dealerCode"));
            String user_id = (String) session.getAttribute("user_id");
            // String vinid = ;
            if(request.getAttribute("vinNo")!=null){
            pf.setVinNo(commonUtilMethods.decodeBase64(request.getAttribute("vinNo").toString()));
            pf.setModelCode(commonUtilMethods.decodeBase64(request.getAttribute("modelCode").toString()));
            }else{
            pf.setVinNo(commonUtilMethods.decodeBase64(request.getParameter("vinNo")));
            pf.setModelCode(commonUtilMethods.decodeBase64(request.getParameter("modelCode")));
            }

            LinkedHashSet<LabelValueBean> productcategoryList = obj.getCommonLabelValue("Productcategorymaster", "categoryId", "productCategory", "productCategory", "");
            LinkedHashSet<LabelValueBean> mechanicList = obj.getCommonLabelValue("MSWDmechanicmaster", "mechanicDealerKey", "concat(mechanicName,' (', mechanicCode,')'),mechanicName", "mechanicName", " where dealerCode='" + dealercode + "' and isActive='Y'");
            request.setAttribute("productcategoryList", productcategoryList);
            HashMap<String, ArrayList<String>> travelcardPartList = obj.GetTravelDetailIns(pf.getVinNo());
            pf = obj.getJobCard_vehicale_DetailFor_singleJobcard(pf, pf.getVinNo());

            obj.getPDIFormContent(pf);
            request.setAttribute("pdiform", pf);
            request.setAttribute("travelcardPartList", travelcardPartList);
            request.setAttribute("mechanicList", mechanicList);
            request.setAttribute("jobCardDate", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            request.setAttribute("pdiDate", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));


        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }

        return mapping.findForward("pdiVehicleInformation");
    }

    public ActionForward initPDIStandardChecks(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {

            pdiDAO obj1 = new pdiDAO();
            pdiForm pf = (pdiForm) form;
            //String formId = "";
            //System.out.println("model code" + pf.getModelCode());
            if (request.getParameter("jobType") != null) {
                pf.setJobType(request.getParameter("jobType"));
            }
            obj1.getPDIFormContent(pf);//obj1.getView_FormContent(sf); 
            request.setAttribute("pdiform", pf);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward("pdiStandardChecks");
    }

    public ActionForward managePDIStandardChecksdetail(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String forwardto = "";
        String data_str = "";

        try {
            HttpSession session = request.getSession();
            pdiDAO obj = new pdiDAO();
            pdiForm pf = (pdiForm) form;
            String user_id = (String) session.getAttribute("user_id");
            int contentI;
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            String vinid = request.getParameter("vinid");
            pf.setDealerCode((String) session.getAttribute("dealerCode"));
            ArrayList<String> dataList = null;
            TreeMap<Integer, ArrayList<String>> tMap = new TreeMap();
            String[] contentId = pf.getContentId();
            String subContentId[] = null;
            String checkpoints[] = null;
            String status[] = null;
            String observations[] = null;
            String actionTakens[] = null;
            pf.setModelCode(request.getParameter("modelCode"));
            pf.setJobType(request.getParameter("jobType"));
            if (request.getParameter("pdiId") != null) {
                pf.setPdiId(Integer.parseInt(request.getParameter("pdiId")));
            }
            ActionMessages messages = new ActionMessages();
            java.util.Date tractorInDate = sdf.parse(pf.getJobCardDate());
            java.util.Date PDIDate = sdf.parse(pf.getPdiDate());
            java.util.Date invoiceDate = sdf.parse(pf.getInvoiceDate());
            Date today = new Date();
            Date condate = new Date(df.format(today));
            condate.setDate(condate.getDate() - Integer.parseInt(new serviceDAO().getHesConstantValue(10)));

            if ((condate.compareTo(PDIDate) > 0) || (today.compareTo(PDIDate) < 0)) {
                //messages.add("FAILURE", new ActionMessage("label.common.pdiDate_currentDate_validation"));
                request.setAttribute("message", "PDI Date should be within " + new serviceDAO().getHesConstantValue(10) + " days to Current Date.");
                request.setAttribute("vinNo" ,commonUtilMethods.encodeToBase64(request.getParameter("vinNo")));
                request.setAttribute("modelCode" ,commonUtilMethods.encodeToBase64(request.getParameter("modelCode")));
                show_SinglePendingChassisdetails(mapping, form, request, response);
                forwardto = "pdiVehicleInformation";
            } else if (condate.compareTo(tractorInDate) > 0) {
                request.setAttribute("message", "Tractor Received Date should be within " + new serviceDAO().getHesConstantValue(10) + " days to Current Date.");
                request.setAttribute("vinNo" ,commonUtilMethods.encodeToBase64(request.getParameter("vinNo")));
                request.setAttribute("modelCode" ,commonUtilMethods.encodeToBase64(request.getParameter("modelCode")));
                show_SinglePendingChassisdetails(mapping, form, request, response);
                forwardto = "pdiVehicleInformation";
            } else if (tractorInDate.compareTo(PDIDate) > 0) {
                request.setAttribute("message", "Tractor Received Date cannot be greater than PDI Date.");
                request.setAttribute("vinNo" ,commonUtilMethods.encodeToBase64(request.getParameter("vinNo")));
                request.setAttribute("modelCode" ,commonUtilMethods.encodeToBase64(request.getParameter("modelCode")));
                show_SinglePendingChassisdetails(mapping, form, request, response);
                forwardto = "pdiVehicleInformation";
            } else if (invoiceDate.compareTo(tractorInDate) > 0) {
                request.setAttribute("message", "Invoice Date cannot be greater than Tractor Received and PDI Date.");
                request.setAttribute("vinNo" ,commonUtilMethods.encodeToBase64(request.getParameter("vinNo")));
                request.setAttribute("modelCode" ,commonUtilMethods.encodeToBase64(request.getParameter("modelCode")));
                show_SinglePendingChassisdetails(mapping, form, request, response);
                forwardto = "pdiVehicleInformation";
            } else {
                if (contentId != null) {
                    for (int c = 0; c < contentId.length; c++) {
                        dataList = new ArrayList();

                        contentI = Integer.valueOf(contentId[c]);

                        tMap.put(contentI, dataList);

                        subContentId = request.getParameterValues(contentId[c] + "SubContent");
                        checkpoints = request.getParameterValues(contentId[c] + "checkpoints");
                        status = request.getParameterValues(contentId[c] + "status");
                        observations = request.getParameterValues(contentId[c] + "observations");
                        actionTakens = request.getParameterValues(contentId[c] + "actionTaken");
                        for (int s = 0; s < checkpoints.length; s++) {
                            dataList.add(subContentId == null ? "0" : subContentId[s]);
                            dataList.add(checkpoints[s]);
                            dataList.add(observations[s]);
                            dataList.add(actionTakens[s]);
                            // dataList.add(status[s]);
                        }
                    }
                }
                pf.setVinId(vinid);
                LinkedList<ContentFormBean> TravelCardList = new LinkedList<ContentFormBean>();

                String[] actiontakens = pf.getActiontakens();
                //System.out.println("actiontakens.length==" + actiontakens.length);
                for (int a = 0; a < actiontakens.length; a++) {
                    if (request.getParameter("partNo" + actiontakens[a]) != null && !request.getParameter("partNo" + actiontakens[a]).equals("")) {
                        ContentFormBean cf = new ContentFormBean();
                        cf.setContentDesc(request.getParameter("partNo" + actiontakens[a]));
                        cf.setObservation(request.getParameter("partVendorName" + actiontakens[a]));
                        cf.setStatus(request.getParameter("partSerialNo" + actiontakens[a]));
//                 cf.setStatus(request.getParameter("contentId"+actiontakens[a]));
                        //System.out.println("--" + cf.getContentDesc() + "--" + cf.getObservation() + "--" + cf.getStatus());
                        TravelCardList.add(cf);
                    }
                }


                data_str = obj.addPDIStandardChecksData(pf, tMap, user_id, (String) session.getAttribute("dealerCode"), TravelCardList);

                if (pf.getCreateJC().equalsIgnoreCase("Yes")) {
                    String jccreated = obj.createJobCard(pf, user_id, (String) session.getAttribute("dealerCode"));
                    //System.out.println(" jccreated  "+jccreated);
                }

                if (data_str.split("@@")[0].equalsIgnoreCase("SUCCESS")) {
                    messages.add("SUCCESS", new ActionMessage("label.common.pdiSuccess"));
                } else if (data_str.split("@@")[0].equalsIgnoreCase("FAILURE")) {
                    messages.add("FAILURE", new ActionMessage("label.common.pdiFailure"));
                } else if (data_str.split("@@")[0].equalsIgnoreCase("EXIST")) {
                    messages.add("EXIST", new ActionMessage("label.common.pdiexist"));
                }
                saveErrors(request, messages);
                request.setAttribute("show_message", pf.getVinNo());//data_str.split("@@")[1]);
                request.setAttribute("result", data_str.split("@@")[0].toUpperCase());
                if (data_str.split("@@")[0].toUpperCase().equals("EXIST")) {
                    request.setAttribute("message", data_str.split("@@")[1]);
                    request.setAttribute("pdiform", pf);
                    init_viewallPendingChassisdetails(mapping, form, request, response);
                    forwardto = "view_allpendingjobcardDetails";
                } else if (data_str.split("@@")[0].toUpperCase().equals("SUCCESS")) {
                    request.setAttribute("addMoreLink", "YES");
                    request.setAttribute("addMoreLinkURL", "pdiServiceProcess.do?option=init_viewallPendingChassisdetails&jobType=ViewPJC");
                    forwardto = "message";
                } else {
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "pdiServiceProcess.do?option=init_viewallPendingChassisdetails&jobType=ViewPJC");
                    forwardto = "message";
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(forwardto);
    }

    public ActionForward init_viewPDIDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        //Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        String forward = "";
        String todate = "";
        String fromdate = "";
        pdiDAO pdDao = new pdiDAO();
        pdiForm pf = (pdiForm) form;
        String flag = request.getParameter("flag");
        if (flag == null) {
            Calendar cal = Calendar.getInstance();
            todate = dateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, -1);
            fromdate = dateFormat.format(cal.getTime());
            flag = "";
            pf.setRange("1");
        }
        pf.setRange(pf.getRange() == null ? "" : pf.getRange());
        String nameSrch = request.getParameter("Srch") == null ? "" : request.getParameter("Srch");
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");
        Vector userFunctionalities = (Vector) session.getAttribute("userFun");
        pf.setStatus(pf.getStatus() == null ? "all" : pf.getStatus());

        pf.setFromdate(request.getParameter("fromDate") == null ? fromdate : request.getParameter("fromDate"));
        pf.setToDate(request.getParameter("toDate") == null ? todate : request.getParameter("toDate"));

        //List dealerList = hrbsession.createSQLQuery(" Select * from FN_GetDealersDetailsUnderUser(?)").setString(0, user_id).list();
        List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
        if (userFunctionalities.contains("101")) {
            pf.setDealerCode((String) session.getAttribute("dealerCode"));
        } else {
            request.setAttribute("dealerList", dealerList);
        }
        /*   else if (userFunctionalities.contains("102")) {
        ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('" + user_id + "',dm.loginId)", 3);
        request.setAttribute("dealerList", dealerList);
        } else {
        ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d ", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "", 3);
        request.setAttribute("dealerList", dealerList);
        }  */

        pf.setDealerCode(pf.getDealerCode() == null ? "ALL" : pf.getDealerCode());
        ArrayList<pdiForm> chassisList = pdDao.getPDIList4View(nameSrch, pf, user_id, userFunctionalities, flag); //(String) session.getAttribute("dealerCode"),

        String dealerName = pdDao.getDealerName(pf.getDealerCode());
        request.setAttribute("chassisList", chassisList);
        request.setAttribute("fromDate", pf.getFromdate());
        request.setAttribute("toDate", pf.getToDate());
        request.setAttribute("nameSrch", nameSrch);
        request.setAttribute("range", pf.getRange());
        request.setAttribute("flag", flag);
        request.setAttribute("pdiForm", pf);
        request.setAttribute("status", pf.getStatus());
        request.setAttribute("dealerName", dealerName);

        if (request.getParameter("eType") != null && request.getParameter("eType").equals("export")) {
            forward = "exportInitViewPDI";
        } else {
            forward = "initViewPDI";
        }
        return mapping.findForward(forward);
    }

    public ActionForward getvinNumberAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        // Connection conn = null;

        PrintWriter pw = null;
        try {
            //  conn = new dbConnection().getConnection();
            pdiDAO obj = new pdiDAO();
            String vinNo = request.getParameter("vinNo");
            HttpSession session = request.getSession();
            String result = obj.GetVinNoList(vinNo, session.getAttribute("dealerCode").toString());
            pw = response.getWriter();
            pw.write(result);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {

                pw.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(null);
    }

    public ActionForward viewPDIData(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "viewPDIData";
        try {
            boolean flag = true;

            HttpSession session = request.getSession();
            pdiForm pf = (pdiForm) form;
            pf.setPdiNo(commonUtilMethods.decodeBase64(pf.getPdiNo()));
            pf.setVinNo(commonUtilMethods.decodeBase64(pf.getVinNo()));
            pdiDAO obj = new pdiDAO();
           // String user_id = (String) session.getAttribute("user_id");
//            pf.setDealerCode((String)session.getAttribute("dealerCode"));
//            flag=obj.checkPDIView(pf);
//            MethodUtility.check_in_Db_HQL(pf.getVinNo(), "", "columnname", "", session);
            //System.out.println(pf.getPdiNo().length());
            if (flag == true) {
                LinkedList<ContentFormBean> travelcardPartList = obj.getSingleINSDatatractorlist(pf, pf.getPdiNo());
                pf = obj.getSinglePDIData(pf, pf.getPdiNo());
                obj.getView_FormContent(pf);
                request.setAttribute("travelcardPartList", travelcardPartList);
                request.setAttribute("pdiform", pf);
                if (request.getParameter("printPDI") != null && request.getParameter("printPDI").equals("true")) {
                    request.setAttribute("heading", "PDI Certificate");
                    request.setAttribute("headingFlag", "headingFlag");
                    forward = "printPDI";
                }
            } else {
                forward = "initViewPDI";
                request.setAttribute("message", pf.getVinNo() + " Chassis No. does not exist");
            }

            //obj.getPDIFormContent(pf);




        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return mapping.findForward(forward);
    }
}
