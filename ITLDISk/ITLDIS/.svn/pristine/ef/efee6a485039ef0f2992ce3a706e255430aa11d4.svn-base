/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.IOUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.json.JSONObject;

import com.common.MethodUtility;

import beans.WarrantyForm;
import dao.WarrantyDAO;
import dao.serviceDAO;
import dbConnection.dbConnection;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import viewEcat.comEcat.ComUtils;
//import viewEcat.comEcat.ConnectionHolder;
import viewEcat.comEcat.PageTemplate;


/**
 *
 * @author kundan.rastogi
 */
public class WarrantyAction extends DispatchAction{



     private static final String SUCCESS = "success";
     private static String dms_properties = "com/myapp/struts/ApplicationResource.properties";
     private static InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(dms_properties);
     private static Properties dms_prop = new Properties();

   

    public ActionForward initWarrantyList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "warrantyList";
        try {
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            
            if(userFunctionalities.contains("101"))
            	 warrantyForm.setDealer_code((String)session.getAttribute("dealerCode"));
            
            else
             warrantyForm.setDealer_code("ALL");
            
            request.setAttribute("jobCardList", warrantyDao.getJobCardList(warrantyForm,userFunctionalities,user_id));
            request.setAttribute("dealerList", warrantyDao.getDealerCodeWithName(user_id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }
    
    public ActionForward createWarranty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "createWarranty";
        HttpSession session = request.getSession();
        Connection conn = null;
        try {
            //ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            //Connection conn = holder.getConnection();
             conn = new dbConnection().getConnection();
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;

            String user_id = (String) session.getAttribute("user_id");
            String dealerCode = (String) session.getAttribute("dealerCode");
            warrantyForm.setDealer_code(dealerCode); //(String)session.getAttribute("dealerCode")
            String jobCardNo = request.getParameter("jobCrdNo");
            warrantyDao.getJobCardDetail(warrantyForm, jobCardNo);
            request.setAttribute("taxList", warrantyDao.getTaxDetail(dealerCode, "", conn,warrantyForm,jobCardNo));
            request.setAttribute("partList", warrantyDao.getJobCardPartDetail(warrantyForm, jobCardNo,"0", conn));
            request.setAttribute("otherPartList", warrantyDao.getJobCardPartDetail(warrantyForm, jobCardNo,"1", conn));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("Exception connection closed!!!");
                ex.printStackTrace();
            }
        }
        return mapping.findForward(forward);
    }
    public ActionForward saveWarranty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	
    	 Connection conn = null;
        try {
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
            String realPath = getServlet().getServletContext().getRealPath("/");
            conn = new dbConnection().getConnection();
            for (int i = 0; i < warrantyForm.getHsnNoArr().length; i++) {
                if (warrantyForm.getHsnNoArr()[i] == null || warrantyForm.getHsnNoArr()[i].equals("")) {
                    request.setAttribute("show_message", "Error Occured While Creating Warranty Claim. because HSN Code is blank for Part No: " + warrantyForm.getPartNoArr()[i]);
                    request.setAttribute("addMoreLink", "YES");
                    request.setAttribute("result", "FAILURE");
                    request.setAttribute("addMoreLinkURL", "warrantyAction.do?option=initWarrantyList");
                    return mapping.findForward("message");
                }
            }
            String taxType  = warrantyDao.getTaxType(warrantyForm.getDealer_code(),conn);
            WarrantyForm wf = warrantyDao.saveWarranty(warrantyForm, user_id, realPath,taxType);
            ActionMessages messages = new ActionMessages();
             
            if (wf.getResult().equals("success")) {
                messages.add("SUCCESS", new ActionMessage("label.common.wcCreateSuccess"));
                request.setAttribute("show_message",wf.getWarrantyClaimNo());
                request.setAttribute("result","SUCCESS");
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "warrantyAction.do?option=initWarrantyList");
            } else if (wf.getResult().equals("FAILURE")) {
                messages.add("FAILURE", new ActionMessage("label.common.wcTaxFailure"));
                request.setAttribute("taxNotAvailable", "YES");
                request.setAttribute("result","FAILURE");
                request.setAttribute("addMoreLinkURL", "warrantyAction.do?option=initWarrantyList");
            }else {
                messages.add("FAILURE", new ActionMessage("label.common.wcFailure"));
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("result","FAILURE");
                request.setAttribute("addMoreLinkURL", "warrantyAction.do?option=initWarrantyList");
            }
               saveErrors(request, messages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("message");
    }


    public ActionForward viewWarrantyClaim(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "viewWarranty";
        String todate = "";
        String fromdate = "";
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();

            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                warrantyForm.setRange("1");
            }

            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            warrantyForm.setDealer_code((String)session.getAttribute("dealerCode"));

            warrantyForm.setFromDate(warrantyForm.getFromDate() == null ? fromdate : warrantyForm.getFromDate());
            warrantyForm.setToDate(warrantyForm.getToDate() == null ? todate : warrantyForm.getToDate());

            request.setAttribute("dataList", warrantyDao.getWarrantyClaimList(warrantyForm,userFunctionalities,user_id));
            request.setAttribute("dealerList", warrantyDao.getDealerCodeWithName(user_id));
            request.setAttribute("dealerName", warrantyForm.getDealerName()!=null?warrantyForm.getDealerName():"");
            warrantyForm.setClaimStatus(warrantyForm.getClaimStatus());
            request.setAttribute("fromDate", warrantyForm.getFromDate());
            request.setAttribute("toDate", warrantyForm.getToDate());
            request.setAttribute("range", warrantyForm.getRange());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }
    public ActionForward viewPendingWarrantyClaim(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "viewPendingWarranty";
        String todate = "";
        String fromdate = "";
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();

            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -7);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                warrantyForm.setRange("1");
            }

            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            if (userFunctionalities.contains("101")) {
                warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
            } else {
                List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
                request.setAttribute("dealerList", dealerList);
            }
            
            if(warrantyForm.getFromDate() != null && !warrantyForm.getFromDate().isEmpty() && warrantyForm.getToDate() != null && !warrantyForm.getToDate().isEmpty()) {
            	warrantyForm.setRange("1");
            }
            warrantyForm.setFromDate(warrantyForm.getFromDate() == null ? fromdate : warrantyForm.getFromDate());
            warrantyForm.setToDate(warrantyForm.getToDate() == null ? todate : warrantyForm.getToDate());
            request.setAttribute("dataList", warrantyDao.getPendingWarrantyClaimList(warrantyForm,userFunctionalities,user_id));
            request.setAttribute("fromDate", warrantyForm.getFromDate());
            request.setAttribute("toDate", warrantyForm.getToDate());
            if(warrantyForm.getFromDate() != null && !warrantyForm.getFromDate().isEmpty() && warrantyForm.getToDate() != null && !warrantyForm.getToDate().isEmpty()) {
            	warrantyForm.setRange("1");
            }
            request.setAttribute("range", warrantyForm.getRange());
            request.setAttribute("vinNo", warrantyForm.getVinNo());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }
//    public ActionForward viewPendingWarrantyClaim(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
//        String forward = "viewPendingWarranty";
//        try {
//            WarrantyDAO warrantyDao = new WarrantyDAO();
//            WarrantyForm warrantyForm = (WarrantyForm) form;
//            HttpSession session = request.getSession();
//            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
//            String user_id = (String) session.getAttribute("user_id");
//            warrantyForm.setDealer_code((String)session.getAttribute("dealerCode"));
//            request.setAttribute("dataList", warrantyDao.getPendingWarrantyClaimList(warrantyForm,userFunctionalities,user_id));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return mapping.findForward(forward);
//    }

    public ActionForward viewWarrantyClaimDetail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "viewWarrantyDetail";
          Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();
            //ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            //Connection conn = holder.getConnection();
            String user_id = (String) session.getAttribute("user_id");
            String user_type= (String) session.getAttribute("user_type");
            warrantyForm.setDealer_code((String)session.getAttribute("dealerCode"));
            String warClaimNo=request.getParameter("warrntyClaimNo");

            String flag=request.getParameter("flag");
            request.setAttribute("flag",flag);
            warrantyDao.getWarrantyClaimDetail(warrantyForm,warClaimNo);
            if(user_type.equalsIgnoreCase("Dealer")){
            request.setAttribute("taxList", warrantyDao.getTaxDetail((String)session.getAttribute("dealerCode"),warClaimNo,conn,warrantyForm,""));
            }else{
            String dealerCode=warrantyDao.getDealerCodeByWcNo(warClaimNo);
            request.setAttribute("taxList", warrantyDao.getTaxDetail(dealerCode,warClaimNo,conn,warrantyForm,""));
            }
           request.setAttribute("partList", warrantyDao.getPartDetail(warrantyForm,warClaimNo));
           request.setAttribute("otherPartList", warrantyDao.getWarrantyViewHandlingDetail(warrantyForm, warClaimNo,"1", conn));

           String eType=request.getParameter("eType");
           if(eType !=null){
           if(eType.equalsIgnoreCase("export") ){
               forward="exportWarantyClaim";
           }else{
               forward="viewWarrantyDetail";
           }
           }

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                System.out.println("Exception connection closed!!!");
                ex.printStackTrace();
            }
        }
        return mapping.findForward(forward);
    }
    public ActionForward approveWarranty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "approveWarranty";
         Connection conn = null;
        try {
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();
            conn = new dbConnection().getConnection();
            //ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            //Connection conn = holder.getConnection();
            String user_id = (String) session.getAttribute("user_id");
           //  String dealerCode=(String)session.getAttribute("dealerCode");
           // warrantyForm.setDealer_code(dealerCode);    //(String)session.getAttribute("dealerCode")
            String warClaimNo=request.getParameter("warrntyClaimNo");
            String jobCardNo=request.getParameter("jobCardNo");
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");
            String claimDate = request.getParameter("claimDate");
            ActionMessages messages = new ActionMessages();
            if (df1.format(sdf.parse(claimDate)).compareTo(df1.format(df.parse(PageTemplate.packingGenDateCheck))) < 0) {

                messages.add("SUCCESS", new ActionMessage("label.warranty.claimNotforApp"));
                request.setAttribute("show_message", "Claim is before '"+sdf.format(df.parse(PageTemplate.packingGenDateCheck))+"' date <br> so , please contact to system administrator for update from backend");
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("approveMoreLink", "YES");
                request.setAttribute("approveMoreLinkURL", "warrantyAction.do?option=viewPendingWarrantyClaim");
                saveErrors(request, messages);
                return mapping.findForward("message");
            }
            String flag=request.getParameter("flag");
            request.setAttribute("flag",flag);
            String dealerCode=warrantyDao.getDealerCode(jobCardNo);
            warrantyForm.setDealer_code(dealerCode);    
            warrantyForm.setClaimDate(claimDate);
            warrantyDao.getWarrantyClaimDetail(warrantyForm,warClaimNo);
            request.setAttribute("rejectionList",MethodUtility.getCommonLabelValueHiber("SAPRejectionCodeMaster", "rejectionCode", "rejectionDesc", "rejectionDesc", "where e.isActive='Y' "));
            request.setAttribute("serviceMap",warrantyDao.getServiceHistoryList(warrantyForm));
            request.setAttribute("taxList", warrantyDao.getTaxDetail(dealerCode,warClaimNo,conn,warrantyForm,""));
            request.setAttribute("otherPartList", warrantyDao.getWarrantyViewHandlingDetail(warrantyForm, warClaimNo,"1", conn));
            warrantyDao.getClaimAndComplaintList(warrantyForm,jobCardNo);

        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return mapping.findForward(forward);
    }


     public ActionForward saveApproveWarranty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	 
    	 Connection conn = null;
        try {
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();
//            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
//            Connection conn = holder.getConnection();
            String user_id = (String) session.getAttribute("user_id");
         //   warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
            conn = new dbConnection().getConnection();
           // Session hsession = HibernateUtil.getSessionFactory().openSession();
           ActionMessages messages = new ActionMessages();
           String taxType  = warrantyDao.getTaxType(warrantyForm.getDealer_code(),conn);
           System.out.println("inside saveApproveWarranty api ");
           System.out.println("taxType "+taxType);
            String result = warrantyDao.saveApproveWarranty(warrantyForm, user_id,taxType);
            if (result.equals("success")) {
                messages.add("SUCCESS", new ActionMessage("label.common.wcApproveSuccess"));
                request.setAttribute("show_message","");
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("approveMoreLink", "YES");

              //  String wClaimPath= dbConnection.warrantyClaimPath;
               // String wClaimPath = properties.getProperty("Warranty_Claim_Path");
              //  String newClaimNo = warrantyForm.getWarrantyClaimNo().replace("/", "-").concat(".txt");
               // File file = new File(wClaimPath.concat("/") + newClaimNo);
              //  File file = new File(newClaimNo);
                //new MethodUtility().saveWarrantyClaimSync(warrantyForm.getWarrantyClaimNo(), hsession, conn);

                // COMMENTED AS NOW IT WILL BE SENT THROUGH SAP INTEGRATION JOB IN STAGING ----- 28DEC2018
              //  new MethodUtility().writingTxt(warrantyForm.getWarrantyClaimNo(), hsession, file);

               if(warrantyForm.getFlag().equals("Approve")){
                request.setAttribute("approveMoreLinkURL", "warrantyAction.do?option=viewPendingWarrantyClaim");
                }
                else{
                request.setAttribute("approveMoreLinkURL", "warrantyAction.do?option=viewWarrantyClaim");
                }
            }else if (warrantyForm.getResult().equalsIgnoreCase("FAILURE")) {
                messages.add("FAILURE", new ActionMessage("label.common.wcTaxFailure"));
                request.setAttribute("taxNotAvailable", "YES");
                request.setAttribute("result","FAILURE");
                request.setAttribute("approveMoreLinkURL", "warrantyAction.do?option=viewWarrantyClaim");
            } else {
                messages.add("FAILURE", new ActionMessage("label.common.wcApproveFailure"));
                request.setAttribute("show_message","");
                request.setAttribute("result", "FAILURE");
                request.setAttribute("approveMoreLink", "YES");
                if(warrantyForm.getFlag().equals("Approve")){
                request.setAttribute("approveMoreLinkURL", "warrantyAction.do?option=viewPendingWarrantyClaim");
                }
                else{
                request.setAttribute("approveMoreLinkURL", "warrantyAction.do?option=viewWarrantyClaim");
                }
            }
             saveErrors(request, messages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("message");
    }



    public ActionForward pendingForDispatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "pendingForDispatch";
        serviceDAO obj = new serviceDAO();
        try {
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            warrantyForm.setDealer_code((String)session.getAttribute("dealerCode"));
            request.setAttribute("jobCardList", warrantyDao.getPendingDispatchList(warrantyForm,userFunctionalities));
            Integer cnt = Integer.parseInt(obj.getHesConstantValue(13));
            request.setAttribute("cnt",cnt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }
    public ActionForward getPendingDispatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        try {
            String result="fail";
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
            request.setAttribute("jobCardList", warrantyDao.getPendingDispatch(warrantyForm, user_id));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("partForDispatch");
    }
     public ActionForward savePartDispatch(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        try {
            String result="fail";
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
            WarrantyForm wf= warrantyDao.savePartDispatch(warrantyForm, user_id);
               ActionMessages messages = new ActionMessages();
            if (wf.getResult().equals("success")) {
                if(wf.getPackingNo().equalsIgnoreCase("notPacked")){
                messages.add("SUCCESS", new ActionMessage("label.warranty.packingNotRequred"));
                request.setAttribute("show_message","");
                request.setAttribute("result","SUCCESS");
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "warrantyAction.do?option=pendingForDispatch");
                }else{
                messages.add("SUCCESS", new ActionMessage("label.warranty.packingSuccess"));
                request.setAttribute("show_message",wf.getPackingNo());
                request.setAttribute("result","SUCCESS");
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "warrantyAction.do?option=pendingForDispatch");
                }
            } else {
                messages.add("FAILURE", new ActionMessage("label.warranty.packingFailure"));
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("result","FAILURE");
                request.setAttribute("addMoreLinkURL", "warrantyAction.do?option=pendingForDispatch");
            }
               saveErrors(request, messages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("message");
    }

  /**
   * init Pending 4 Dishpatched 
   */
  public ActionForward initPackedWarrantyList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "initPackedWarrantyList";
        try {
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            String dealerCode=(String)session.getAttribute("dealerCode");
            warrantyForm.setDealer_code((String)session.getAttribute("dealerCode"));
            ArrayList<WarrantyForm> dispachedList = warrantyDao.getPackedWarrantyList(dealerCode,user_id);
            request.setAttribute("dispachedList", dispachedList);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

  /**
   * consignmentDetails
   */
  public ActionForward initconsignment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "initconsignment";
        try {
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            String packingNo = request.getParameter("packingNo");
//            warrantyForm = warrantyDao.getDishpatchedData(packingNo);
            request.setAttribute("warrantyForm", warrantyDao.getDishpatchedData(packingNo));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

  /**
   * manage consignmentDetails
   */
  public ActionForward manageconsignment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forwardto = "manageconsignment";
        HttpSession session = request.getSession();
        try {
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            String realPath = getServlet().getServletContext().getRealPath("/");
            warrantyForm.setUserId((String) session.getAttribute("user_id"));
            warrantyForm.setDealer_code((String)session.getAttribute("dealerCode"));
            String result = warrantyDao.setDishpatchedData(warrantyForm,realPath);
            ActionMessages messages = new ActionMessages();
            if(result.equalsIgnoreCase("SUCCESS")){
                messages.add("SUCCESS", new ActionMessage("label.common.dispatchedSuccess"));
                request.setAttribute("result","SUCCESS");
                request.setAttribute("show_message",warrantyForm.getPackingNo());
                request.setAttribute("modifyMoreLink", "YES");
                request.setAttribute("modifyMoreLinkURL", "warrantyAction.do?option=initPackedWarrantyList");
                forwardto = "message";
            }
            else if(result.equalsIgnoreCase("FAILURE")){
                messages.add("FAILURE", new ActionMessage("label.common.dispatchedFailure"));
                request.setAttribute("result","FAILURE");
                request.setAttribute("show_message","");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "warrantyAction.do?option=initconsignment");
                forwardto = "message";
            }
            saveErrors(request, messages);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forwardto);
    }

     public ActionForward pendingForAcknow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

         String forward = "pendingForAcknow";
        String fromdate = "";
        String todate = "";
        try {
            HttpSession session = request.getSession();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                warrantyForm.setRange("1");
            }
            warrantyForm.setUserId(user_id);
            
        if (userFunctionalities.contains("101")) {
            warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
        } else {
                List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            request.setAttribute("dealerList", dealerList);
        }            
/*            if (userFunctionalities.contains("101")) {
                warrantyForm.setDealer_code(session.getAttribute("dealerCode").toString());
            } else if (userFunctionalities.contains("102")) {
                ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('" + warrantyForm.getUserId() + "',dm.loginId)", 3);
                request.setAttribute("dealerList", dealerList);
            } else if (userFunctionalities.contains("103")) {
                ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "", 3);
                request.setAttribute("dealerList", dealerList);
            }
 */
            warrantyForm.setPackingNo(warrantyForm.getPackingNo() == null ? "" : warrantyForm.getPackingNo());
            warrantyForm.setFromDate(warrantyForm.getFromDate() == null ? fromdate : warrantyForm.getFromDate());
            warrantyForm.setToDate(warrantyForm.getToDate() == null ? todate : warrantyForm.getToDate());

            // warrantyForm.setDealer_code((String)session.getAttribute("dealerCode"));
            if (warrantyForm.getDealer_code() == null) {
                warrantyForm.setDealer_code("ALL");
            }
            // if(warrantyForm.getDealer_code()!=null){
            request.setAttribute("packingList", warrantyDao.getPendingForAcknowList(warrantyForm, userFunctionalities));  //userFunctionalities
            //  }
            request.setAttribute("warrantyForm", warrantyForm);
            request.setAttribute("range", warrantyForm.getRange());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

     public ActionForward acknowPacking(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "acknowPacking";
        try {
             WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
             warrantyForm.setDealer_code((String)session.getAttribute("dealerCode"));
             warrantyForm.setPackingNo(""+request.getParameter("packingNo"));
            request.setAttribute("dispatchList", warrantyDao.getPackingDataForAcknow(warrantyForm,"ack"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }



     public ActionForward createPackingAcknow(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        try {
            String result="fail";
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
            WarrantyForm wf= warrantyDao.savePackingAcknow(warrantyForm, user_id);
               ActionMessages messages = new ActionMessages();
            if (wf.getResult().equals("success")) {

                messages.add("SUCCESS", new ActionMessage("label.warranty.acknowSuccess"));
                request.setAttribute("show_message","");
                request.setAttribute("result","SUCCESS");
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "warrantyAction.do?option=pendingForAcknow");

            } else {
                messages.add("FAILURE", new ActionMessage("label.warranty.acknowFailure"));
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("result","FAILURE");
                request.setAttribute("addMoreLinkURL", "warrantyAction.do?option=pendingForAcknow");
            }
               saveErrors(request, messages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("message");
    }


  /**
   * ViewPackingList
   */
    public ActionForward viewPackingList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "viewPackingList";
        String fromdate = "";
        String todate = "";
        try {
             HttpSession session = request.getSession();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id= (String) session.getAttribute("user_id");
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                warrantyForm.setRange("1");
            }           
            warrantyForm.setUserId(user_id);

           
            if (userFunctionalities.contains("101")) {
                warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
            } else {
                 List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
                request.setAttribute("dealerList", dealerList);
            }

/*            if (userFunctionalities.contains("101")) {
                warrantyForm.setDealer_code(session.getAttribute("dealerCode").toString());
            } else if (userFunctionalities.contains("102")) {
                ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('" + warrantyForm.getUserId() + "',dm.loginId)", 3);
                request.setAttribute("dealerList", dealerList);
            } else if (userFunctionalities.contains("103")) {
                ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "", 3);
                request.setAttribute("dealerList", dealerList);
            }
 *
 */
            warrantyForm.setFromDate(warrantyForm.getFromDate() == null ? fromdate : warrantyForm.getFromDate());
            warrantyForm.setToDate(warrantyForm.getToDate() == null ? todate : warrantyForm.getToDate());
            ArrayList<WarrantyForm> packingList = new ArrayList<WarrantyForm>();
            if (warrantyForm.getDealer_code() == null) {
                warrantyForm.setDealer_code("ALL");
            }
            warrantyForm.setPackingStatus("".equals(warrantyForm.getPackingStatus()) == true ? null : warrantyForm.getPackingStatus());
            packingList = warrantyDao.getviewPackingList(warrantyForm, userFunctionalities);
            request.setAttribute("packingList", packingList);
            request.setAttribute("warrantyFpackingDetails4MassVieworm", warrantyForm);
            request.setAttribute("range", warrantyForm.getRange());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }
/**
   * packing Details for View with given packing no
   */
  public ActionForward packingDetails4View(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "packingDetails4View";
        try {
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();
            warrantyForm.setUserId((String) session.getAttribute("user_id"));
            warrantyForm.setDealer_code((String)session.getAttribute("dealerCode"));
            warrantyForm.setPackingNo(request.getParameter("packingNo"));
            ArrayList<WarrantyForm> packingList = warrantyDao.getPackingDataForAcknow(warrantyForm,"pack");
            request.setAttribute("packingList", packingList);
            if (request.getParameter("print") != null && request.getParameter("print").equals("true")) {
                request.setAttribute("dealerCode", warrantyForm.getDealer_code());
                request.setAttribute("heading", "Consignment Details");
//                request.setAttribute("headingFlag", "headingFlag");
                forward = "printConsignment";
            }
//            request.setAttribute("warrantyForm", warrantyForm);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }
    
  /*
   * pending for SAP credit Note List
   */
    public ActionForward pendingSAPList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "pendingSAPList";
        String todate = "";
        String fromdate = "";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();
            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                if(warrantyForm.getRange()==null) {
                    warrantyForm.setRange("1");
                }
            }
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            warrantyForm.setUserId(user_id);

          
            if (userFunctionalities.contains("101")) {
                warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
            } else {
            	List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            	request.setAttribute("dealerList", dealerList);
            }
            request.setAttribute("dealerList1", warrantyDao.getDealerCodeWithName(user_id));
            request.setAttribute("fromDate", warrantyForm.getFromDate()==null?fromdate:warrantyForm.getFromDate());
            request.setAttribute("toDate", warrantyForm.getToDate()==null?todate:warrantyForm.getToDate());
            request.setAttribute("range", warrantyForm.getRange());


            request.setAttribute("dealerCode", warrantyForm.getDealerCode()!=null?warrantyForm.getDealerCode():"");
            warrantyForm.setPackingNo(request.getParameter("packingNo"));
            ArrayList<WarrantyForm> panding4SAPList = new ArrayList<WarrantyForm>();
            panding4SAPList = warrantyDao.getpendingSAPList(warrantyForm, userFunctionalities);
            //  if(request.getParameter("getDataFlag").toString().equalsIgnoreCase("true"))
            //   {
            request.setAttribute("panding4SAPList", panding4SAPList);
            //   }
            
            
            String eType=request.getParameter("etype");
            if(eType != null && !eType.isEmpty() && eType.equalsIgnoreCase("export") ) {
            	
            	
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                Connection conn = null;
                try {
        			
                	
                	WarrantyForm warForm = (WarrantyForm) form;
        			JasperPrint jasperPrint = null;

        			String fromDate = warrantyForm.getFromDate();
        			String toDate = warrantyForm.getToDate();
        			String dealerCode = warrantyForm.getDealer_code();
        			String claimNo  = warrantyForm.getWarrantyClaimNo();
        			
        			if (userFunctionalities.contains("101")) {
        				warForm.setDealer_code((String) session.getAttribute("dealerCode"));
        			}
        			else if(dealerCode.isEmpty() || dealerCode == null || dealerCode.equals("ALL")) {
        				warForm.setDealer_code("ALL");
        			}
                	

                    String path = request.getSession().getServletContext().getRealPath("/");
                    String filename = path + "/jasperFile/Pending_Credit_Note_Report.jasper";

        			HashMap<String, Object> parameterMap = new HashMap<>();
        			parameterMap.put("dealerCode", warForm.getDealer_code());
        			parameterMap.put("fromDate", fromDate);
        			parameterMap.put("toDate", toDate);
        			parameterMap.put("claimNo", claimNo);

                    conn = new dbConnection().getConnection();
                    jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);
                    request.setAttribute("jasperPrint", jasperPrint);
                    request.setAttribute("reportType", "xls");
                    request.setAttribute("reportName", "PendingSapCreditNoteGenerationReport");
                    request.setAttribute("fromDate", warForm.getFromDate());
                    request.setAttribute("toDate", warForm.getToDate());
                    request.setAttribute("range", warForm.getRange());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return mapping.findForward("downloadReport");
            	
            }
                   
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

   public ActionForward getVendorDescbyCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        try {

            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            String vendorCode = request.getParameter("vendorCode");
            HttpSession session = request.getSession();
            String dealerCode=(String) session.getAttribute("dealerCode");
            String result = warrantyDao.getDescByVendorCode(vendorCode,dealerCode);
            try {
                PrintWriter pw = response.getWriter();
                pw.write(result);
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
        }
        return mapping.findForward(null);
    }

   // View SAP Credit Note list 

    public ActionForward viewCreditNote(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "viewCreditNoteList";
        String todate = "";
        String fromdate = "";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();
            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                warrantyForm.setRange("1");
            }

            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            warrantyForm.setUserId((String) session.getAttribute("user_id"));

           
            if (userFunctionalities.contains("101")) {
                warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
            } else {
            }
            List dealerList = MethodUtility.getDealersDetailsUnderUser((String) session.getAttribute("user_id"));
            request.setAttribute("dealerList", dealerList);
            request.setAttribute("dealerList1", warrantyDao.getDealerCodeWithName(warrantyForm.getUserId()));
            request.setAttribute("dealerCode", warrantyForm.getDealerCode()!=null?warrantyForm.getDealerCode():"");
  /*          if(userFunctionalities.contains("101"))
            {
                warrantyForm.setDealer_code(session.getAttribute("dealerCode").toString());
            }else if(userFunctionalities.contains("102")){
                    ArrayList<ArrayList<String>> dealerList=MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('"+warrantyForm.getUserId()+"',dm.loginId)", 3);
                    request.setAttribute("dealerList",dealerList);
            }
            else if(userFunctionalities.contains("103")){
                    ArrayList<ArrayList<String>> dealerList=MethodUtility.getListwithJoinQuery("Dealervslocationcode d", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "", 3);
                    request.setAttribute("dealerList",dealerList);
            }
   *
   */
             if (warrantyForm.getDealer_code() == null) {
                warrantyForm.setDealer_code("ALL");
            }
            warrantyForm.setFromDate(warrantyForm.getFromDate() == null ? fromdate : warrantyForm.getFromDate());
            warrantyForm.setToDate(warrantyForm.getToDate() == null ? todate : warrantyForm.getToDate());
            ArrayList<WarrantyForm> panding4SAPList = new ArrayList<WarrantyForm>();
            panding4SAPList = warrantyDao.getViewCreditNoteList(warrantyForm, userFunctionalities);
            request.setAttribute("panding4SAPList", panding4SAPList);  //viewCreditNoteList
            request.setAttribute("fromDate", warrantyForm.getFromDate());
            request.setAttribute("toDate", warrantyForm.getToDate());
            request.setAttribute("range", warrantyForm.getRange());
            String eType = request.getParameter("eType");
            if (eType != null) {
                if (eType.equalsIgnoreCase("export")) {
                    forward = "exportViewCreditNoteList";
                } else {
                    forward = "viewCreditNoteList";
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward WarrantyMaterialView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "WarrantyMaterialView";
        try {
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();
            warrantyForm.setUserId((String) session.getAttribute("user_id"));
            warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
            warrantyForm.setPackingNo(request.getParameter("packingNo"));
            ArrayList<String> packingList = warrantyDao.getWarrantyMaterialData(request.getParameter("packingNo"));
            request.setAttribute("packingList", packingList);
            request.setAttribute("dealerCode", warrantyForm.getDealer_code());
            request.setAttribute("heading", "Consignment Details");

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

     public ActionForward viewClaimReoprt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "claimReport";
        String todate = "";
        String fromdate = "";
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();

            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                warrantyForm.setRange("1");
            }

            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            //warrantyForm.setDealer_code((String)session.getAttribute("dealerCode"));

             if (userFunctionalities.contains("101")) {
                warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
            } else {
                 List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
                request.setAttribute("dealerList", dealerList);
            }


            request.setAttribute("fromDate", warrantyForm.getFromDate() == null ? fromdate : warrantyForm.getFromDate());
            request.setAttribute("toDate", warrantyForm.getToDate() == null ? todate : warrantyForm.getToDate());
            request.setAttribute("range", warrantyForm.getRange());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }
    public ActionForward claimReoprt(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String forward = "claimReoprtExcel";
        String todate = "";
        String fromdate = "";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        try {
            WarrantyDAO warDAO = new WarrantyDAO();
            WarrantyForm warForm = (WarrantyForm) form;
            HttpSession session = request.getSession();

            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                warForm.setRange("1");
            }

            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            //warForm.setDealer_code((String)session.getAttribute("dealerCode"));

            if (userFunctionalities.contains("101")) {
                warForm.setDealer_code((String) session.getAttribute("dealerCode"));
            } else {
                List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
                request.setAttribute("dealerList", dealerList);
            }

            warForm.setFromDate(warForm.getFromDate() == null ? fromdate : warForm.getFromDate());
            warForm.setToDate(warForm.getToDate() == null ? todate : warForm.getToDate());

            request.setAttribute("claimReportList", warDAO.getViewWarrantyClaimReport(warForm,userFunctionalities,user_id));
            warForm.setClaimStatus(warForm.getClaimStatus());
            request.setAttribute("fromDate", warForm.getFromDate());
            request.setAttribute("toDate", warForm.getToDate());
            request.setAttribute("range", warForm.getRange());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }
    public ActionForward printDeliveryChallan(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //HttpSession session = request.getSession();
        Connection conn = null;
       // ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        //Connection conn = holder.getConnection();
        try {
            conn = new dbConnection().getConnection();
            String path = request.getSession().getServletContext().getRealPath("/");
            String filename = path + "/jasperFile/itldeliveryChallan.jasper";

            HashMap<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("packingNo", request.getParameter("packingNo").toString().trim());

            JasperPrint jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);
            request.setAttribute("jasperPrint", jasperPrint);
            request.setAttribute("reportType", "pdf");
            request.setAttribute("reportName", "DeliveryChallanReport");
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return mapping.findForward("downloadReport");
    }
    public ActionForward printWarrantyInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        //HttpSession session = request.getSession();
        //ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        //Connection conn = holder.getConnection();
        try {
            conn = new dbConnection().getConnection();
            String path = request.getSession().getServletContext().getRealPath("/");
            String filename = path + "/jasperFile/itlWarrantyClaimInvoice.jasper";

            HashMap<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("warrantyClaimNo", request.getParameter("claimNo").toString().trim());

            JasperPrint jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);
            request.setAttribute("jasperPrint", jasperPrint);
            request.setAttribute("reportType", "pdf");
            request.setAttribute("reportName", "WarrantyClaimInvoiceReport");
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return mapping.findForward("downloadReport");
    }

    public ActionForward viewBajajExtendedWarrantyInv(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String fromdate = "", todate = "";
        String forward= "viewBajajExtendedWarrantyInv";
        try {
            HttpSession session = request.getSession();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
             WarrantyDAO warDAO = new WarrantyDAO();
            WarrantyForm sf = (WarrantyForm) form;
            String chassisNo = request.getParameter("chassisNo");
            sf.setVinNo(chassisNo);
            sf.setWarrantyClaimNo(request.getParameter("nameSrch"));
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String etype = request.getParameter("etype") == null ? "" : request.getParameter("etype");
            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                sf.setRange("1");
            }
            sf.setRange(sf.getRange() == null ? "" : sf.getRange());
            sf.setUserId(user_id);
            sf.setFromDate(sf.getFromDate() == null ? fromdate : sf.getFromDate());
            sf.setToDate(sf.getToDate() == null ? todate : sf.getToDate());
            sf.setVinNo(sf.getVinNo() == null ? "" : sf.getVinNo());
            sf.setWarrantyClaimNo(sf.getWarrantyClaimNo() == null ? "" : sf.getWarrantyClaimNo());

            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                sf.setDealer_code((String) session.getAttribute("dealerCode"));
            } else {
                request.setAttribute("dealerList", dealerList);
            }
            sf.setDealer_code(sf.getDealer_code()== null ? "ALL" : sf.getDealer_code());
            request.setAttribute("fromdate", sf.getFromDate());
            request.setAttribute("todate", sf.getToDate());
            request.setAttribute("range", sf.getRange());      
            request.setAttribute("warrantyClaimNo", sf.getWarrantyClaimNo());

            if (etype != null && etype.equalsIgnoreCase("Export")) {
                request.setAttribute("expWarrListExport", warDAO.getBajajExtendedWarrantyInvList(sf, userFunctionalities,user_id));
                forward = "exportBajajExtendedWarrantyInv";
            }  else{
                 request.setAttribute("dataList", warDAO.getBajajExtendedWarrantyInvList(sf,userFunctionalities,user_id));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(forward);
    }

     public ActionForward printBajajExtendedWarrantyInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            String path = request.getSession().getServletContext().getRealPath("/");
            String filename = path + "/jasperFile/bajajExtWarrantyClaimInvoiceReport.jasper";

            HashMap<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("extWarrantyClaimNo", request.getParameter("extWarrantyClaimNo").toString().trim());

            JasperPrint jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);
            request.setAttribute("jasperPrint", jasperPrint);
            request.setAttribute("reportType", "pdf");
            request.setAttribute("reportName", "ExtWarrantyClaimInvoiceReport");
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return mapping.findForward("downloadReport");
    }
     public ActionForward viewSAPDumpClaimReoprt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "sapDumpClaimReport";
        String todate = "";
        String fromdate = "";
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();

            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                warrantyForm.setRange("1");
            }

            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            //warrantyForm.setDealer_code((String)session.getAttribute("dealerCode"));

             if (userFunctionalities.contains("101")) {
                warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
            } else {
                 List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
                request.setAttribute("dealerList", dealerList);
            }


            request.setAttribute("fromDate", warrantyForm.getFromDate() == null ? fromdate : warrantyForm.getFromDate());
            request.setAttribute("toDate", warrantyForm.getToDate() == null ? todate : warrantyForm.getToDate());
            request.setAttribute("range", warrantyForm.getRange());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

	public ActionForward sapDumpClaimReoprt(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		String todate = "";
		String fromdate = "";
		SimpleDateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Connection conn = null;
		try {
			
			Date fromDate = null;
			Date toDate = null;
			WarrantyForm warrantyForm = (WarrantyForm) form;
			String formattedFromDate = "",formattedToDate="";
			if (!warrantyForm.getFromDate().isEmpty() && !warrantyForm.getToDate().isEmpty() && warrantyForm.getFromDate() != null && warrantyForm.getToDate() != null) {
				try {
					fromDate = inputFormat.parse(warrantyForm.getFromDate());
					toDate = inputFormat.parse(warrantyForm.getToDate());
				} catch (ParseException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			

	        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");

	         formattedFromDate = outputFormat.format(fromDate);
	         formattedToDate = outputFormat.format(toDate);
			}
			

			WarrantyForm warForm = (WarrantyForm) form;
			HttpSession session = request.getSession();
			JasperPrint jasperPrint = null;
			Vector userFunctionalities = (Vector) session.getAttribute("userFun");
			 String user_id = (String) session.getAttribute("user_id");

			if (userFunctionalities.contains("101")) {
				warForm.setDealer_code((String) session.getAttribute("dealerCode"));
			} else if (warForm.getDealerCode() == null || warForm.getDealerCode().isEmpty() || warForm.getDealerCode() == null || warForm.getDealerCode().equals("ALL")) {
				warForm.setDealerCode("ALL");
			}

			String path = request.getSession().getServletContext().getRealPath("/");
			String filename = path + "/jasperFile/itlSAPDumpClaimReport.jasper";

			HashMap<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("warrantyClaimNO", warForm.getWarrantyClaimNo());
			parameterMap.put("DELAERCODE", warForm.getDealer_code());
			parameterMap.put("USERID", user_id);
			parameterMap.put("FROMDATE", formattedFromDate);
			parameterMap.put("TODATE", formattedToDate);
			parameterMap.put("CLAIMSTATUS", warForm.getClaimStatus());

			
			conn = new dbConnection().getConnection();
			jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);
			request.setAttribute("jasperPrint", jasperPrint);
			request.setAttribute("reportType", "xls");
			request.setAttribute("reportName", "SAPDumpClaimReport");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("downloadReport");
	}

      public ActionForward printWarrantyClaimChallan(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
            conn = new dbConnection().getConnection();
            String path = request.getSession().getServletContext().getRealPath("/");
            String filename = path + "/jasperFile/warrantyClaimRevisedReport.jasper";

            HashMap<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("packingNo", request.getParameter("packingNo").toString().trim());

            JasperPrint jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);
            request.setAttribute("jasperPrint", jasperPrint);
            request.setAttribute("reportType", "pdf");
            request.setAttribute("reportName", "WarrantyClaimRevisedReport");
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return mapping.findForward("downloadReport");
    }

     public ActionForward getLabourCharge(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        try {

            WarrantyDAO warrantyDao = new WarrantyDAO();
            String taxable = request.getParameter("taxable");
            String claimDate = request.getParameter("claimDate");
            BigDecimal tax =new BigDecimal(taxable);
            String result = warrantyDao.getLabourCharge(tax,claimDate);
            try {
                PrintWriter pw = response.getWriter();
                pw.write(result);
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
        }
        return mapping.findForward(null);
    }
    public ActionForward warInvoiceUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
            String forward = "warInvoiceUpdate";
            try {
                WarrantyDAO warrantyDao = new WarrantyDAO();
                WarrantyForm warrantyForm = (WarrantyForm) form;
                HttpSession session = request.getSession();
                Vector userFunctionalities = (Vector) session.getAttribute("userFun");
                String user_id = (String) session.getAttribute("user_id");
                warrantyForm.setDealer_code((String)session.getAttribute("dealerCode"));
                warrantyForm.setWarrantyClaimNo((String)request.getParameter("claimNo").toString().trim());
                warrantyDao.getClaimInvoiceNo(warrantyForm,userFunctionalities,user_id);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mapping.findForward(forward);
      }

   
    public ActionForward saveWrrantyInvoice(ActionMapping mapping, ActionForm form, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        String forward = "successPath";
        String result = "failure", message = "";
        try {
            HttpSession session = request.getSession();
            String updatedBy = (String) session.getAttribute("user_id");
            String dealerCode = (String) session.getAttribute("dealerCode");
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            result = warrantyDao.saveWrrantyInvoice(dealerCode, warrantyForm, updatedBy);

            if (result.equals("Updated")) {
                message = "Invoice updated Successfully for Warranty Number :" + warrantyForm.getWarrantyClaimNo() + "";
                result="success";
            }
            else if (result.equals("Created")) {
                message = "Invoice created Successfully for Warranty Number:" + warrantyForm.getWarrantyClaimNo() + "";
                result="success";
            } else {
                message = "Invoice created Unsuccessful.";
                result="failure";
            }
            request.setAttribute("message", message);
            request.setAttribute("result", result);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(forward);
    }
    
	public ActionForward initItlExtWarranty(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		String forward = "initItlExtWarranty";
		try {
			HttpSession session = request.getSession();
			Vector userFunctionalities = (Vector) session.getAttribute("userFun");
			String user_id = (String) session.getAttribute("user_id");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return mapping.findForward(forward);
	}
	
	  public ActionForward viewItlExtendedWarrantyInv(ActionMapping mapping, ActionForm form,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception {

	        String fromdate = "", todate = "";
	        String forward= "viewItlExtendedWarrantyInv";
	        try {
	            HttpSession session = request.getSession();
	            serviceDAO sDao = new serviceDAO();
	            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
	            String user_id = (String) session.getAttribute("user_id");
	             WarrantyDAO warDAO = new WarrantyDAO();
	            WarrantyForm sf = (WarrantyForm) form;
	            String chassisNo = request.getParameter("chassisNo");
	            sf.setVinNo(chassisNo);
	            sf.setWarrantyClaimNo(request.getParameter("nameSrch"));
	            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            String etype = request.getParameter("etype") == null ? "" : request.getParameter("etype");
	            String flag = request.getParameter("flag");
	            if (flag == null) {
	                Calendar cal = Calendar.getInstance();
	                todate = dateFormat.format(cal.getTime());
	                cal.add(Calendar.DATE, -1);
	                fromdate = dateFormat.format(cal.getTime());
	                flag = "";
	                sf.setRange("1");
	            }
	            sf.setRange(sf.getRange() == null ? "" : sf.getRange());
	            sf.setUserId(user_id);
	            sf.setFromDate(sf.getFromDate() == null ? fromdate : sf.getFromDate());
	            sf.setToDate(sf.getToDate() == null ? todate : sf.getToDate());
	            sf.setVinNo(sf.getVinNo() == null ? "" : sf.getVinNo());
	            sf.setWarrantyClaimNo(sf.getWarrantyClaimNo() == null ? "" : sf.getWarrantyClaimNo());

	            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
	            if (userFunctionalities.contains("101")) {
	                sf.setDealer_code((String) session.getAttribute("dealerCode"));
	            } else {
	                request.setAttribute("dealerList", dealerList);
	            }
	            sf.setDealer_code(sf.getDealer_code()== null ? "ALL" : sf.getDealer_code());
	            request.setAttribute("fromdate", sf.getFromDate());
	            request.setAttribute("todate", sf.getToDate());
	            request.setAttribute("range", sf.getRange());      
	            request.setAttribute("warrantyClaimNo", sf.getWarrantyClaimNo());

	            if (etype != null && etype.equalsIgnoreCase("Export")) {
	                request.setAttribute("expWarrListExport", warDAO.getBajajExtendedWarrantyInvList(sf, userFunctionalities,user_id));
	                forward = "exportItlExtendedWarrantyInv";
	            }  else{
	                 request.setAttribute("dataList", warDAO.getBajajExtendedWarrantyInvList(sf,userFunctionalities,user_id));
	            }
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }
	        return mapping.findForward(forward);
	    }
	  
	  public ActionForward viewItlExtendedWarrantyClaimStatus(ActionMapping mapping, ActionForm form,
	            HttpServletRequest request, HttpServletResponse response)
	            throws Exception {

	        String fromdate = "", todate = "";
	        String forward= "viewItlExtendedWarrantyClaimStatus";
	        try {
	            HttpSession session = request.getSession();
	            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
	            String user_id = (String) session.getAttribute("user_id");
	             WarrantyDAO warDAO = new WarrantyDAO();
	             serviceDAO sDao = new serviceDAO();
	            WarrantyForm sf = (WarrantyForm) form;
	            String chassisNo = request.getParameter("chassisNo");
	            sf.setVinNo(chassisNo);
	            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	            String etype = request.getParameter("etype") == null ? "" : request.getParameter("etype");
	            String flag = request.getParameter("flag");
	            if (flag == null) {
	                Calendar cal = Calendar.getInstance();
	                todate = dateFormat.format(cal.getTime());
	                cal.add(Calendar.DATE, -1);
	                fromdate = dateFormat.format(cal.getTime());
	                flag = "";
	                sf.setRange("1");
	            }
	            sf.setRange(sf.getRange() == null ? "" : sf.getRange());
	            sf.setUserId(user_id);
	            sf.setFromDate(sf.getFromDate() == null ? fromdate : sf.getFromDate());
	            sf.setToDate(sf.getToDate() == null ? todate : sf.getToDate());
	            sf.setVinNo(sf.getVinNo() == null ? "" : sf.getVinNo());
	            request.setAttribute("extWarrStatus", sDao.getExtWarrStatus());

	            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
	            if (userFunctionalities.contains("101")) {
	                sf.setDealer_code((String) session.getAttribute("dealerCode"));
	            } else {
	                request.setAttribute("dealerList", dealerList);
	            }
	            sf.setDealer_code(sf.getDealer_code()== null ? "ALL" : sf.getDealer_code());
	            request.setAttribute("fromdate", sf.getFromDate());
	            request.setAttribute("todate", sf.getToDate());
	            request.setAttribute("range", sf.getRange());      
	           
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }
	        return mapping.findForward(forward);
	    }
	  
	public ActionForward exportWarrantyClaimStatusReport(ActionMapping mapping, ActionForm form,
		        HttpServletRequest request, HttpServletResponse response) throws Exception {

		    WarrantyForm sf = (WarrantyForm) form;
		    HttpSession session = request.getSession();
		    WarrantyDAO warDAO = new WarrantyDAO();
		    String chassisNo = request.getParameter("chassisNo");
		    sf.setVinNo(chassisNo);
		    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		    
		    Vector userFunctionalities = (Vector) session.getAttribute("userFun");
	        String user_id = (String) session.getAttribute("user_id");

	        List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
	        if (userFunctionalities.contains("101")) {
	        	sf.setDealer_code((String) session.getAttribute("dealerCode"));
	        } else {
	            request.setAttribute("dealerList", dealerList);
	            sf.setDealer_code("ALL");   
	        }
		   
		    String flag = request.getParameter("flag");
		    String fromdate = "", todate = "";
		    if (flag == null) {
		        Calendar cal = Calendar.getInstance();
		        todate = dateFormat.format(cal.getTime());
		        cal.add(Calendar.DATE, -1);
		        fromdate = dateFormat.format(cal.getTime());
		        flag = "";
		        sf.setRange("1");
		    }
		    sf.setRange(sf.getRange() == null ? "" : sf.getRange());
		    sf.setFromDate(sf.getFromDate() == null ? fromdate : sf.getFromDate());
		    sf.setToDate(sf.getToDate() == null ? todate : sf.getToDate());
		    sf.setVinNo(sf.getVinNo() == null ? "" : sf.getVinNo());

		    Connection conn = null;
		    OutputStream outputStream = null;

		        conn = new dbConnection().getConnection();
		        String path = request.getSession().getServletContext().getRealPath("/");
		        String filename = path + "/jasperFile/ITL_EXT_WTY_CLAIM_STATUS_REPORT.jasper";
		        
		        System.out.println();

		        HashMap<String, Object> parameterMap = new HashMap<>();
		        parameterMap.put("CHASSISNO", sf.getVinNo());
		        parameterMap.put("FROMDATE", sf.getFromDate());
		        parameterMap.put("TODATE", sf.getToDate());
		        parameterMap.put("DEALERCODE", sf.getDealer_code());
		        parameterMap.put("STATUS", sf.getStatus());
		        

		        JasperPrint jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);

		    
		    if(jasperPrint.getPages().isEmpty()) {
				System.out.println("empty pages");
			}
	//		if (jasperPrint != null && !jasperPrint.getPages().isEmpty()) {
				try {
					outputStream = response.getOutputStream();

					printReport("xls", jasperPrint, true, "Warranty Claim Status Report", outputStream,null,response);
				} catch (Exception e) {
					System.out.println(e.getMessage());
					
				} finally {
					try {
						if (outputStream != null) {
							outputStream.flush();
							outputStream.close();
						}
					} catch (Exception e) {
						System.out.println(e.getMessage());
					}
				}
//			} else {
				
//			}
		    return null;
		}
	
	public void printReport(String reportType, JasperPrint jasperPrint, Boolean printStatus, String reportName,
				OutputStream outputStream, List<JasperPrint> jpList, HttpServletResponse response) throws Exception {
			JRXlsExporter xlsExporter = new JRXlsExporter();
			xlsExporter.setExporterInput(new SimpleExporterInput(jasperPrint));
			xlsExporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
			String fileName = reportName + ".xls";
			response.setContentType("application/vnd.ms-excel");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
			xlsExporter.exportReport();
		}
	
	public ActionForward viewTaxInvoiceUpdate(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "viewTaxInvoiceUpdate";
        String todate = "";
        String fromdate = "";
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();
            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                warrantyForm.setRange("1");
            }

            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            warrantyForm.setDealer_code((String)session.getAttribute("dealerCode"));

            warrantyForm.setFromDate(warrantyForm.getFromDate() == null ? fromdate : warrantyForm.getFromDate());
            warrantyForm.setToDate(warrantyForm.getToDate() == null ? todate : warrantyForm.getToDate());

            request.setAttribute("dataList", warrantyDao.getWarrantyClaimList(warrantyForm,userFunctionalities,user_id));
            request.setAttribute("dealerList", warrantyDao.getDealerCodeWithName(user_id));
            request.setAttribute("dealerCode", warrantyForm.getDealerCode()!=null?warrantyForm.getDealerCode():"");
            request.setAttribute("dealerName", warrantyForm.getDealerName()!=null?warrantyForm.getDealerName():"");
            warrantyForm.setClaimStatus(warrantyForm.getClaimStatus());
            request.setAttribute("fromDate", warrantyForm.getFromDate());
            request.setAttribute("toDate", warrantyForm.getToDate());
            request.setAttribute("range", warrantyForm.getRange());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }
		
	public ActionForward packingDetails4MassView(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
//		String forward = "packingDetails4View";
		Connection conn = null;
		OutputStream ouputStream = null;
		List<JasperPrint> jasperPrintList = new ArrayList<>();

		try {
			WarrantyDAO warrantyDao = new WarrantyDAO();
			WarrantyForm warrantyForm = (WarrantyForm) form;
			HttpSession session = request.getSession();
			warrantyForm.setUserId((String) session.getAttribute("user_id"));
			warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));

			String packingNo = request.getParameter("packingNo");
			List<String> warrantyClaimNos = warrantyDao.getWarrantyClaimsOnPackingNo(packingNo);
			
			for (String warrantyClaimNo : warrantyClaimNos) {
				try {
					conn = new dbConnection().getConnection();
					String path = request.getSession().getServletContext().getRealPath("/");
					String filename = path + "/jasperFile/itlWarrantyClaimInvoice.jasper";

					HashMap<String, Object> parameterMap = new HashMap<>();
					parameterMap.put("warrantyClaimNo", warrantyClaimNo.trim());

					JasperPrint jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);
					jasperPrintList.add(jasperPrint);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (conn != null) {
							conn.close();
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}

			if (!jasperPrintList.isEmpty()) {
				JRPdfExporter exporter = new JRPdfExporter();
				ouputStream = response.getOutputStream();
				response.setContentType("application/pdf");
				String reportName="BulkWarrantyClaimInvoiceReport";
                response.setHeader("Content-Disposition", "filename=\"" + reportName + ".pdf\"");
                exporter = new JRPdfExporter();                 
                exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);                 
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                exporter.exportReport();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward(null);
	}
	
	public ActionForward warrantyDetails4MassView(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
//		String forward = "packingDetails4View";
		Connection conn = null;
		OutputStream ouputStream = null;
		List<JasperPrint> jasperPrintList = new ArrayList<>();

		try {
			WarrantyDAO warrantyDao = new WarrantyDAO();
			WarrantyForm warrantyForm = (WarrantyForm) form;
			HttpSession session = request.getSession();
	

			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String dealerCode = request.getParameter("dealerCode");
			
			List<String> warrantyClaimNos = warrantyDao.getWarrantyClaimNoList(fromDate, toDate, dealerCode);


			for (String warrantyClaimNo : warrantyClaimNos) {
				try {
					conn = new dbConnection().getConnection();
					String path = request.getSession().getServletContext().getRealPath("/");
					String filename = path + "/jasperFile/itlWarrantyClaimInvoice.jasper";

					HashMap<String, Object> parameterMap = new HashMap<>();
					parameterMap.put("warrantyClaimNo", warrantyClaimNo.trim());

					JasperPrint jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);
					jasperPrintList.add(jasperPrint);

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (conn != null) {
							conn.close();
						}
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}

			if (!jasperPrintList.isEmpty()) {
				JRPdfExporter exporter = new JRPdfExporter();
				ouputStream = response.getOutputStream();
				response.setContentType("application/pdf");
				String reportName="BulkWarrantyClaimInvoiceReport";
                response.setHeader("Content-Disposition", "filename=\"" + reportName + ".pdf\"");
                exporter = new JRPdfExporter();                 
                exporter.setParameter(JRExporterParameter.JASPER_PRINT_LIST, jasperPrintList);                 
                exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
                exporter.exportReport();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward(null);
	}

	public ActionForward initBajajExtWarranty(ActionMapping mapping, ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {

			
			String forward = "initBajajExtWarranty";
			try {
				HttpSession session = request.getSession();
				Vector userFunctionalities = (Vector) session.getAttribute("userFun");
				String user_id = (String) session.getAttribute("user_id");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			return mapping.findForward(forward);
		}
        
	
	public ActionForward viewTaxInvoiceUpload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "viewTaxInvoiceUpload";
        String fromdate = "";
        String todate = "";
        try {
             HttpSession session = request.getSession();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id= (String) session.getAttribute("user_id");
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            
         
            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                warrantyForm.setRange("1");
            }           
            warrantyForm.setUserId(user_id);

           
            if (userFunctionalities.contains("101")) {
                warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
            } else {
            }
            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            request.setAttribute("dealerList", dealerList);

            warrantyForm.setFromDate(warrantyForm.getFromDate() == null ? fromdate : warrantyForm.getFromDate());
            warrantyForm.setToDate(warrantyForm.getToDate() == null ? todate : warrantyForm.getToDate());
            ArrayList<WarrantyForm> packingList = new ArrayList<WarrantyForm>();
            if (warrantyForm.getDealer_code() == null) {
                warrantyForm.setDealer_code("ALL");
            }
            warrantyForm.setTaxInvoiceStatus("".equals(warrantyForm.getTaxInvoiceStatus()) == true ? null : warrantyForm.getTaxInvoiceStatus());
            packingList = warrantyDao.getviewTaxInvoiceUploadList(warrantyForm, userFunctionalities);
            request.setAttribute("packingList", packingList);
            request.setAttribute("warrantyForm", warrantyForm);
            request.setAttribute("range", warrantyForm.getRange());

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }
	
	 public ActionForward warrantyClaimStatusDashboard(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

         String forward = "warrantyClaimStatusDashboard";
        String fromdate = "";
        String todate = "";
        try {
            HttpSession session = request.getSession();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                warrantyForm.setRange("1");
            }
            warrantyForm.setUserId(user_id);
            
        if (userFunctionalities.contains("101")) {
            warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
        } else {
                List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            request.setAttribute("dealerList", dealerList);
        }            

            warrantyForm.setFromDate(warrantyForm.getFromDate() == null ? fromdate : warrantyForm.getFromDate());
            warrantyForm.setToDate(warrantyForm.getToDate() == null ? todate : warrantyForm.getToDate());

            // warrantyForm.setDealer_code((String)session.getAttribute("dealerCode"));
            if (warrantyForm.getDealer_code() == null) {
                warrantyForm.setDealer_code("ALL");
            }
            
            request.setAttribute("warrantyClaimStatusList", warrantyDao.getWarrantyClaimStatusList(warrantyForm, userFunctionalities,user_id));  //userFunctionalities
         
            request.setAttribute("warrantyForm", warrantyForm);
            request.setAttribute("range", warrantyForm.getRange());
            request.setAttribute("fromDate", (warrantyForm.getFromDate() == null || warrantyForm.getFromDate().isEmpty() ? fromdate : warrantyForm.getFromDate()));
            request.setAttribute("toDate", (warrantyForm.getToDate() == null || warrantyForm.getToDate().isEmpty() ? todate : warrantyForm.getToDate()));
			
            
            String eType=request.getParameter("etype");
            Connection conn = null;
    		OutputStream ouputStream = null;
			if (eType != null && eType.equalsIgnoreCase("export")) {
				try {

					WarrantyForm warForm = (WarrantyForm) form;
					JasperPrint jasperPrint = null;
	
					String fromDate = request.getParameter("fromDate");
        			String toDate = request.getParameter("toDate");
        			String dealerCode = request.getParameter("dealerCode");
        			if (userFunctionalities.contains("101")) {
        				warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
        			}

					String path = request.getSession().getServletContext().getRealPath("/");
					String filename = path + "/jasperFile/Warranty_Dashboard_Report.jasper";

					HashMap<String, Object> parameterMap = new HashMap<>();
					parameterMap.put("DealerCode", warrantyForm.getDealer_code());
    				parameterMap.put("fromDate", fromDate);
    				parameterMap.put("toDate", toDate);
    				parameterMap.put("userId", user_id);

					conn = new dbConnection().getConnection();
					jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);
					request.setAttribute("jasperPrint", jasperPrint);
					request.setAttribute("reportType", "xls");
					request.setAttribute("reportName", "WarrantyClaimDashboardReport");

				} catch (Exception e) {
					e.printStackTrace();
				}
				return mapping.findForward("downloadReport");
			}
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }
	 
	public ActionForward exportWarrantyDashboardClaimReport(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		
		Connection conn = null;
		OutputStream ouputStream = null;

		try {
			WarrantyDAO warrantyDao = new WarrantyDAO();
			WarrantyForm warrantyForm = (WarrantyForm) form;
			HttpSession session = request.getSession();
			JasperPrint jasperPrint = null;
			Vector userFunctionalities = (Vector) session.getAttribute("userFun");

			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String dealerCode = request.getParameter("dealerCode");
			if (userFunctionalities.contains("101")) {
				warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
			}
			else if(dealerCode.isEmpty() || dealerCode == null || dealerCode.equals("ALL")) {
				warrantyForm.setDealer_code("ALL");
			}

			try {
				conn = new dbConnection().getConnection();
				String path = request.getSession().getServletContext().getRealPath("/");
				String filename = path + "/jasperFile/Warranty_Claim_Status_Report.jasper";

				HashMap<String, Object> parameterMap = new HashMap<>();
				parameterMap.put("dealerCode", warrantyForm.getDealer_code());
				parameterMap.put("fromDate", fromDate);
				parameterMap.put("toDate", toDate);

				jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (conn != null) {
						conn.close();
					}
				} catch (Exception ex) {
					ex.printStackTrace();
				}
			}

			if (!jasperPrint.getPages().isEmpty()) {
				JRPdfExporter exporter = new JRPdfExporter();
				ouputStream = response.getOutputStream();
				response.setContentType("application/xls");
				String reportName = "WarrantyClaimDashboardReport";
				response.setHeader("Content-Disposition", "filename=\"" + reportName + ".xls\"");
				exporter = new JRPdfExporter();
				exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
				exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, ouputStream);
				exporter.exportReport();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return mapping.findForward(null);
	}
	

	public ActionForward exportPendingSapCreditNoteGeneration(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String todate = "";
        String fromdate = "";
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Connection conn = null;
        try {
			/*
			 * WarrantyForm warForm = (WarrantyForm) form; HttpSession session =
			 * request.getSession(); String user_id = (String)
			 * session.getAttribute("user_id"); warForm.setFromDate(warForm.getFromDate() ==
			 * null ? fromdate : df.format(sdf.parse(warForm.getFromDate()+ " 00:00")));
			 * warForm.setToDate(warForm.getToDate() == null ? todate :
			 * df.format(sdf.parse(warForm.getToDate()+ " 23:59")));
			 */
        	
        	WarrantyForm warForm = (WarrantyForm) form;
			HttpSession session = request.getSession();
			JasperPrint jasperPrint = null;
			Vector userFunctionalities = (Vector) session.getAttribute("userFun");

			String fromDate = request.getParameter("fromDate");
			String toDate = request.getParameter("toDate");
			String dealerCode = request.getParameter("dealerCode");
			String claimNo  = request.getParameter("claimNo");
			String export  = request.getParameter("export");
			if (userFunctionalities.contains("101")) {
				warForm.setDealer_code((String) session.getAttribute("dealerCode"));
			}
			else if(dealerCode.isEmpty() || dealerCode == null || dealerCode.equals("ALL")) {
				warForm.setDealer_code("ALL");
			}
        	

            String path = request.getSession().getServletContext().getRealPath("/");
            String filename = path + "/jasperFile/Pending_Credit_Note_Report.jasper";

			HashMap<String, Object> parameterMap = new HashMap<>();
			parameterMap.put("dealerCode", warForm.getDealer_code());
			parameterMap.put("fromDate", fromDate);
			parameterMap.put("toDate", toDate);
			parameterMap.put("claimNo", claimNo);

            conn = new dbConnection().getConnection();
            jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);
            request.setAttribute("jasperPrint", jasperPrint);
            request.setAttribute("reportType", "xls");
            request.setAttribute("reportName", "PendingSapCreditNoteGenerationReport");
            request.setAttribute("fromDate", warForm.getFromDate());
            request.setAttribute("toDate", warForm.getToDate());
            request.setAttribute("range", warForm.getRange());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("downloadReport");
    }
	
	public ActionForward taxAcknowledgement(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		
		String forward = "taxAcknowledgement";
		try {
			HttpSession session = request.getSession();
			Vector userFunctionalities = (Vector) session.getAttribute("userFun");
			String user_id = (String) session.getAttribute("user_id");
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return mapping.findForward(forward);
	}
	
	public ActionForward taxInvoiceAcknowledgeViewList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "taxInvoiceAcknowledgeViewList";
        String todate = "";
        String fromdate = "";
         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            WarrantyDAO warrantyDao = new WarrantyDAO();
            WarrantyForm warrantyForm = (WarrantyForm) form;
            HttpSession session = request.getSession();
            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                warrantyForm.setRange("1");
            }

            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
      
            warrantyForm.setFromDate(warrantyForm.getFromDate() == null ? fromdate : warrantyForm.getFromDate());
            warrantyForm.setToDate(warrantyForm.getToDate() == null ? todate : warrantyForm.getToDate());
            warrantyForm.setUserId(user_id);
            
            if (userFunctionalities.contains("101")) {
                warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
            } else {
                 List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
                request.setAttribute("dealerList", dealerList);
            }

            request.setAttribute("packingList", warrantyDao.getTaxInvoiceAcknowledgeList(warrantyForm,userFunctionalities,user_id));
            request.setAttribute("dealerCode", warrantyForm.getDealerCode()!=null?warrantyForm.getDealerCode():"");
            request.setAttribute("dealerName", warrantyForm.getDealerName()!=null?warrantyForm.getDealerName():"");
            warrantyForm.setClaimStatus(warrantyForm.getClaimStatus());
            request.setAttribute("fromDate", warrantyForm.getFromDate());
            request.setAttribute("toDate", warrantyForm.getToDate());
            request.setAttribute("range", warrantyForm.getRange());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }
	
	public ActionForward uploadTaxInvoice(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		PrintWriter pw = null;
		String forword = "";
		try {
			WarrantyForm warrantyForm = (WarrantyForm) form;
			WarrantyDAO obj = new WarrantyDAO();

			FormFile uploadedFile = warrantyForm.getUploadTaxInvoice();
			String packingNo = warrantyForm.getPackingNo();

			HttpSession session = request.getSession();
			String delcode = (String) session.getAttribute("dealerCode");
			String realPath = getServlet().getServletContext().getRealPath("/");
			String contextPath = (String) request.getContextPath();
			warrantyForm.setContext(contextPath);
			warrantyForm.setRealPath(realPath);
			warrantyForm.setPackingNo(packingNo);
			String result = "";
			if (uploadedFile != null) {
				result = obj.uploadTaxInvoice(warrantyForm, packingNo, realPath, uploadedFile);
			}

			if (result.equalsIgnoreCase("success")) {

				request.setAttribute("show_message", "Tax Invoice File has been Uploaded Successfully");
			} else {
				request.setAttribute("show_message", "Tax Invoice File has not been Uploaded");

			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return mapping.findForward("message");
	}
	
	 public ActionForward taxInvoiceAcknowledge(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	        String forward = "taxInvoiceAcknowledge";
	        try {
	             WarrantyDAO warrantyDao = new WarrantyDAO();
	            WarrantyForm warrantyForm = (WarrantyForm) form;
	            HttpSession session = request.getSession();
	            String user_id = (String) session.getAttribute("user_id");
	             warrantyForm.setDealer_code((String)session.getAttribute("dealerCode"));
	             warrantyForm.setPackingNo(""+request.getParameter("packingNo"));
	             String packingNo = warrantyForm.getPackingNo();
	            request.setAttribute("dispatchList", warrantyDao.getTaxInvoiceDataForAcknow(warrantyForm));
	            request.setAttribute("claimInvoiceList", warrantyDao.getClaimInvoiceListForPackingNo(packingNo));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return mapping.findForward(forward);
	    }
	 
	 public ActionForward taxInvoiceAcknowledgeSubmit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	        String forward = "taxInvoiceAcknowledge";
	        try {
	             WarrantyDAO warrantyDao = new WarrantyDAO();
	            WarrantyForm warrantyForm = (WarrantyForm) form;
	            HttpSession session = request.getSession();
	            String user_id = (String) session.getAttribute("user_id");
	             String warrantyClaims = request.getParameter("checkedClaims");
	             String taxInvoiceStatus = request.getParameter("taxInvoiceStatus");
	             String packingNo = request.getParameter("packingNo");
	             System.out.println(warrantyClaims);
	            String result =  warrantyDao.updateStatusOfTaxInvoices(warrantyClaims,taxInvoiceStatus,packingNo);
	            if(result.equalsIgnoreCase("SUCCESS"))
	            	request.setAttribute("show_message","Selected Tax Invoices has been received succesfully");
	            
	            else 
	            	request.setAttribute("show_message", "Error in Receiving Tax Invoices. Please try later");
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return mapping.findForward(forward);
	    }
	 
	 public ActionForward taxInvoiceStatus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	        String forward = "taxInvoiceStatus";
	        String todate = "";
	        String fromdate = "";
	         DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
	        try {
	            WarrantyDAO warrantyDao = new WarrantyDAO();
	            WarrantyForm warrantyForm = (WarrantyForm) form;
	            HttpSession session = request.getSession();
	            String flag = request.getParameter("flag");
	            if (flag == null) {
	                Calendar cal = Calendar.getInstance();
	                todate = dateFormat.format(cal.getTime());
	                cal.add(Calendar.DATE, -7);
	                fromdate = dateFormat.format(cal.getTime());
	                flag = "";
	                warrantyForm.setRange("1");
	            }

	            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
	            String user_id = (String) session.getAttribute("user_id");
	      
	            warrantyForm.setFromDate(warrantyForm.getFromDate() == null ? fromdate : warrantyForm.getFromDate());
	            warrantyForm.setToDate(warrantyForm.getToDate() == null ? todate : warrantyForm.getToDate());
	            warrantyForm.setUserId(user_id);
	            
	            if (userFunctionalities.contains("101")) {
	                warrantyForm.setDealer_code((String) session.getAttribute("dealerCode"));
	            } else {
	                 List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
	                request.setAttribute("dealerList", dealerList);
	            }
	            
	            warrantyForm.setTaxInvoiceStatus("".equals(warrantyForm.getTaxInvoiceStatus()) == true ? null : warrantyForm.getTaxInvoiceStatus());

	            request.setAttribute("packingList", warrantyDao.getTaxInvoiceStatusList(warrantyForm,userFunctionalities,user_id));
	            request.setAttribute("dealerCode", warrantyForm.getDealerCode()!=null?warrantyForm.getDealerCode():"");
	            request.setAttribute("dealerName", warrantyForm.getDealerName()!=null?warrantyForm.getDealerName():"");
	            warrantyForm.setClaimStatus(warrantyForm.getClaimStatus());
	            request.setAttribute("fromDate", warrantyForm.getFromDate());
	            request.setAttribute("toDate", warrantyForm.getToDate());
	            request.setAttribute("range", warrantyForm.getRange());
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return mapping.findForward(forward);
	    }
	 
	 public ActionForward downloadTaxInvoice(ActionMapping mapping, ActionForm form, HttpServletRequest request,
				HttpServletResponse response) {
			WarrantyForm warrantyForm = (WarrantyForm) form;
			String packingNo = warrantyForm.getPackingNo();
			FileInputStream inputStream = null;
			OutputStream outStream = null;

			try {
				WarrantyDAO warrantyDao = new WarrantyDAO();
				String realPath = getServlet().getServletContext().getRealPath("/");

				// Get the invoice file name from the DAO
				String fileName = warrantyDao.getTaxInvoiceFileName(packingNo);

				// Correct file path with escaped backslashes
				String filePath = realPath + "DOCUMENTS\\WARRANTY\\TAX INVOICE\\" + fileName;
				System.out.println("File Path: " + filePath);

				File file = new File(filePath);
				inputStream = new FileInputStream(file);
				response.setContentLength((int) file.length());
				response.setContentType("application/pdf");
				outStream = response.getOutputStream();
				IOUtils.copy(inputStream, outStream);

			} catch (Exception e) {
				e.printStackTrace();
			}
			return null; // No forward needed
		}
		
	public ActionForward pendingWarrantyClaimExport(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) {

			String todate = "";
			String fromdate = "";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Connection conn = null;
			try {

				WarrantyForm warForm = (WarrantyForm) form;
				HttpSession session = request.getSession();
				JasperPrint jasperPrint = null;
				Vector userFunctionalities = (Vector) session.getAttribute("userFun");
				 String user_id = (String) session.getAttribute("user_id");

				if (userFunctionalities.contains("101")) {
					warForm.setDealer_code((String) session.getAttribute("dealerCode"));
				} else if ( warForm.getDealerCode() == null  || warForm.getDealerCode().isEmpty() || warForm.getDealerCode().equals("ALL")) {
					warForm.setDealerCode("ALL");
				}

				String path = request.getSession().getServletContext().getRealPath("/");
				String filename = path + "/jasperFile/PendingWarrantyClaimsReport.jasper";

				HashMap<String, Object> parameterMap = new HashMap<>();
				parameterMap.put("dealerCode", warForm.getDealerCode());
				parameterMap.put("userId", user_id);
				
				conn = new dbConnection().getConnection();
				jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);
				request.setAttribute("jasperPrint", jasperPrint);
				request.setAttribute("reportType", "xls");
				request.setAttribute("reportName", "PendingWarrantyClaimsReport");

			} catch (Exception e) {
				e.printStackTrace();
			}
			return mapping.findForward("downloadReport");
		}
	
	 public ActionForward taxInvoiceAcknowledgeView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	        String forward = "taxInvoiceAcknowledgeView";
	        try {
	             WarrantyDAO warrantyDao = new WarrantyDAO();
	            WarrantyForm warrantyForm = (WarrantyForm) form;
	            HttpSession session = request.getSession();
	            String user_id = (String) session.getAttribute("user_id");
	             warrantyForm.setDealer_code((String)session.getAttribute("dealerCode"));
	             warrantyForm.setPackingNo(""+request.getParameter("packingNo"));
	             String packingNo = warrantyForm.getPackingNo();
	            request.setAttribute("dispatchList", warrantyDao.getTaxInvoiceDataForAcknow(warrantyForm));
	            request.setAttribute("claimInvoiceList", warrantyDao.getClaimInvoiceListForPackingNo(packingNo));
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return mapping.findForward(forward);
	    }
	 
		public ActionForward digitalSignedTaxInvoice(ActionMapping mapping, ActionForm form, HttpServletRequest request,
				HttpServletResponse response) throws Exception {
			PrintWriter out = null;
			Connection conn = null;
			try {
				conn = new dbConnection().getConnection();
				WarrantyForm warrantyForm = (WarrantyForm) form;
				WarrantyDAO obj = new WarrantyDAO();

				String claimNo = warrantyForm.getWarrantyClaimNo();
				String path = request.getSession().getServletContext().getRealPath("/");
				String filename = path + "/jasperFile/itlWarrantyClaimInvoice.jasper";

				HashMap<String, Object> parameterMap = new HashMap<>();
				parameterMap.put("warrantyClaimNo", claimNo);

				JasperPrint jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);

// Generate and save PDF to server
				
				String filePath = request.getSession().getServletContext().getRealPath("/");
				String outputDir = filePath + "DOCUMENTS" + File.separator + "WARRANTY_TAX_INVOICES";
				new File(outputDir).mkdirs();

				String sanitizedClaimNo = claimNo.replace('/', '_');
				String outputPath = outputDir + File.separator + sanitizedClaimNo + ".pdf";
				JasperExportManager.exportReportToPdfFile(jasperPrint, outputPath);
				File serverFile = new File(outputPath);

// Load properties
				Properties dms_prop = new Properties();
				dms_prop.load(is);

				String apiKey = ComUtils.getPropertyValue("API_KEY").trim();
				String emailInitiator = ComUtils.getPropertyValue("EMAIL_INITIATOR").trim();
				String fileUploadUrl = ComUtils.getPropertyValue("FILE_UPLOAD_URL").trim();
				String callbackURL = ComUtils.getPropertyValue("CALLBACK_URL").trim();
				
				System.out.println("apiKey "+apiKey);
				System.out.println("emailInitiator "+emailInitiator);
				System.out.println("fileUploadUrl "+fileUploadUrl);
				System.out.println("callbackURL "+callbackURL);

// Upload the PDF file for digital signing
				Map<String, String> responseMap = obj.UploadSignedFile(apiKey, emailInitiator, fileUploadUrl,
						outputPath,callbackURL);

				response.setContentType("application/json");
				out = response.getWriter();
				JSONObject jsonResponse = new JSONObject();

				String message = responseMap.get("message");
				String widgetUrl = responseMap.get("widget");
				String uuid = responseMap.get("uuid");
				String signLocation = responseMap.get("signLocation");
				
				System.out.println("message "+message);
				System.out.println("widgetUrl "+widgetUrl);
				System.out.println("uuid "+uuid);
				System.out.println("signLocation "+signLocation);
				

				if (signLocation == null || signLocation.isEmpty()) {
					jsonResponse.put("result", "error");
					jsonResponse.put("message", "Did not get valid sign location.");
				} else if ("File successfully uploaded".equalsIgnoreCase(message) && widgetUrl != null && uuid != null
						&& !widgetUrl.isEmpty() && !uuid.isEmpty()) {
                    
					System.out.println("inside else if");
					String result = obj.uploadSignedTaxInvoiceInDB(warrantyForm, claimNo, uuid, path, serverFile);
					System.out.println("result "+result);
					if ("SUCCESS".equalsIgnoreCase(result)) {
						jsonResponse.put("result", "success");
						jsonResponse.put("widgetUrl", widgetUrl);
						jsonResponse.put("uuid", uuid);
					} else {
						jsonResponse.put("result", "error");
						jsonResponse.put("message", "Failed to save file info in DB.");
					}
				} else {
					jsonResponse.put("result", "error");
					jsonResponse.put("message", "File upload to signing server failed.");
				}

				out.print(jsonResponse.toString());
				out.flush();
				return null;

			} catch (Exception e) {
				e.printStackTrace();
				if (out != null) {
					JSONObject errorJson = new JSONObject();
					errorJson.put("result", "error");
					errorJson.put("message", "Exception occurred: " + e.getMessage());
					out.print(errorJson.toString());
					out.flush();
				}
				return null;
			} finally {
				if (conn != null)
					conn.close();
			}
		}



	
}
