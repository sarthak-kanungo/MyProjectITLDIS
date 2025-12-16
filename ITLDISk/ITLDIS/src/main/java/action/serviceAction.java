package action;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
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
import org.hibernate.Session;

import com.EAMG.common.commonUtilMethods;
import com.common.MethodUtility;

import HibernateMapping.SWVehicleServiceFollowup;
import HibernateMapping.SpOrderInvGrn;
import HibernateUtil.HibernateUtil;
import beans.serviceForm;
import dao.serviceDAO;
import dbConnection.dbConnection;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import viewEcat.comEcat.PageTemplate;

public class serviceAction extends DispatchAction {

    private static final String SUCCESS = "success";
    String dbPATH = new dbConnection().dbPathAuth;


    public ActionForward getPartNumberAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        PrintWriter pw = null;
        try {
            //conn1 = new dbConnection().getDbConnection();
            serviceDAO obj1 = new serviceDAO();
            //serviceForm mf = (serviceForm) form;
            String tableName = "Partmaster";
            String partno = request.getParameter("partno");
            String comptype = request.getParameter("comptype");
            
            

            String result = obj1.getComponentList(partno, comptype, "partNo", tableName, "partType");
            pw = response.getWriter();
            pw.write(result);
//14805
        } catch (Exception e1) {
            e1.printStackTrace();
        } 
        return mapping.findForward(null);
    }

    public ActionForward getPartPriceBypartNo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        
        try {
            serviceDAO obj1 = new serviceDAO();
            HttpSession session = request.getSession();
            String priceListCode = (String) session.getAttribute("priceListCode");
            String TablePartprice = "SpPriceMaster";
            String TablePartmaster = "CatPart";
            String partno = request.getParameter("partno");
            String result = obj1.getPriceByPartNo(partno, "p1", TablePartprice, TablePartmaster, "price", "partNo",priceListCode);
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
            //serviceForm addComplaintDetailJobCardmf = (serviceForm) form;
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
            String priceListCode = (String) session.getAttribute("priceListCode");
            String partDesc = request.getParameter("partDesc");
            String TablePartprice = "SpPriceMaster";
            String TablePartmaster = "CatPart";
            String partno = obj1.getPart_in_db(partDesc);
            String result = obj1.getPriceByPartNo(partno, "partNo", TablePartprice, TablePartmaster, "price", "partNo",priceListCode);
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

    // Init view all Job card details  created on 15/05/14 by Megha
    public ActionForward init_viewallJobcarddetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String todate = "";
        String fromdate = "";
        try {
            HttpSession session = request.getSession();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            serviceDAO obj = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            String vinid = request.getParameter("vinid");
            sf.setVinid(vinid);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
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
            sf.setJobCardStatus(sf.getJobCardStatus() == null ? "ALL" : sf.getJobCardStatus());
            sf.setFromdate(sf.getFromdate() == null ? fromdate : sf.getFromdate());
            sf.setTodate(sf.getTodate() == null ? todate : sf.getTodate());
            String nameSrch = request.getParameter("Srch") == null ? "" : request.getParameter("Srch");
            String ColumnName = "jobCardNo";
            if (!nameSrch.equals("")) {
                ColumnName = nameSrch.substring(0, nameSrch.indexOf(":"));
                nameSrch = nameSrch.substring(nameSrch.indexOf(":") + 1, nameSrch.length());
            }

            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                sf.setDealercode((String) session.getAttribute("dealerCode"));
            } else {
                request.setAttribute("dealerList", dealerList);
            }
            
   /*         if(userFunctionalities.contains("101"))
            {
                sf.setDealercode(session.getAttribute("dealerCode").toString());
            }else if(userFunctionalities.contains("102")){
                    ArrayList<ArrayList<String>> dealerList=MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('"+sf.getUserId()+"',dm.loginId)", 3);
                    request.setAttribute("dealerList",dealerList);
            }
            else if(userFunctionalities.contains("103")){
                    ArrayList<ArrayList<String>> dealerList=MethodUtility.getListwithJoinQuery("Dealervslocationcode d", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "", 3);
                    request.setAttribute("dealerList",dealerList);
            }
    * 
    */
            sf.setDealercode(sf.getDealercode()== null ? "ALL" : sf.getDealercode());
            ArrayList<serviceForm> jobcardDetails = obj.getJobCardDetails(sf, nameSrch, ColumnName, user_id,userFunctionalities);
            request.setAttribute("jobcardDetails", jobcardDetails);
            request.setAttribute("nameSrch", nameSrch);
            request.setAttribute("columnName", ColumnName);
            request.setAttribute("fromdate", sf.getFromdate());
            request.setAttribute("todate", sf.getTodate());
            request.setAttribute("status", sf.getJobCardStatus());
            request.setAttribute("range", sf.getRange());
        } catch (Exception e1) {
            e1.printStackTrace();
        } 

        return mapping.findForward("view_alljobcardDetails");
    }

    
    public ActionForward getVimNumberDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            serviceDAO obj = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            HttpSession session = request.getSession();
            String vinno = request.getParameter("vinNo").trim();

            String vindetails = obj.getVinDetails(vinno,(String)session.getAttribute("dealerCode"));

            PrintWriter pw = response.getWriter();

            pw.write(vindetails);

            pw.close();

        } catch (Exception e1) {
            e1.printStackTrace();
        } 
        return mapping.findForward(null);
    }

    
    //
    public ActionForward getvinNumberAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Connection conn = null;
        PrintWriter pw = null;
        try {
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            conn = new dbConnection().getConnection();
            serviceDAO obj1 = new serviceDAO(conn);
            String vinNo = request.getParameter("vinNo");
            String jctype = request.getParameter("jctype");
            String result = obj1.getVinNoList(vinNo, jctype,user_id,(String)session.getAttribute("dealerCode"));
            pw = response.getWriter();
            pw.write(result);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
                pw.close();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(null);
    }


    public ActionForward getServiceHrsAjax(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            serviceDAO obj = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            PrintWriter pw = response.getWriter();
            String nameObj = request.getParameter("nameObj");
            String objjCode = request.getParameter("objjCode");
            String result = obj.getServiceHrsById(user_id, nameObj,(String)session.getAttribute("dealerCode"));
            pw.print(result);

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return mapping.findForward(null);
    }

   
    /**
     *  pending 4 DE approval screen page created on 24/09/14 by Megha
     */
   /*
    Commented by DM on 15-Feb-2016
    public ActionForward initPendingDE4Approval(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        try {
            serviceDAO seDAO = new serviceDAO();
            String user_id = (String) session.getAttribute("user_id");
            String dealerCode =(String)session.getAttribute("dealerCode");
            Vector userFunctionalities=(Vector) session.getAttribute("userFun");
            request.setAttribute("de4ApprovalList", seDAO.getDE4ApprovalList(user_id,dealerCode,userFunctionalities));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return mapping.findForward("initPendingDE4Approval");
    }
*/
    /**
     *  pending 4 validate chassis no  screen page created on 24/09/14 by Megha
     */
    public ActionForward initPending4validateVinNo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        try {
            serviceDAO seDAO = new serviceDAO();
            String user_id = (String) session.getAttribute("user_id");
            String dealerCode =(String)session.getAttribute("dealerCode");
            Vector userFunctionalities=(Vector) session.getAttribute("userFun");
            request.setAttribute("vinList4Validate", seDAO.getVinNo4validate(user_id,dealerCode,userFunctionalities));
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return mapping.findForward("initPending4validateVinNo");
    }

    /**
     * validate chassis no and update in db  created on 24/09/14 by Megha
     */
    public ActionForward validateVinNoNupdate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        try {
             Session hsession = HibernateUtil.getSessionFactory().openSession();
            serviceDAO seDAO = new serviceDAO();
            String user_id = (String) session.getAttribute("user_id");
            String dealerCode =(String)session.getAttribute("dealerCode");//request.getParameter("dealerCode");
            String vinNo = request.getParameter("vinNo");
            String jobCardNo = request.getParameter("jobCardNo");
            String oldvinNo = request.getParameter("oldvinNo");
            //System.out.println(oldvinNo+"*"+dealerCode+"*"+user_id+"*"+vinNo+"*"+jobCardNo);
            PrintWriter pw = response.getWriter();
            pw.write(seDAO.validateVinNoNupdate(dealerCode,vinNo,jobCardNo,oldvinNo));
            pw.close();

        }
        catch(Exception e){
            e.printStackTrace();
        }
        return mapping.findForward(null);
    }

     ///////////////////////////////////////***CLOSE JOB CARD***////////////////////////////////////////////////
    public ActionForward initCloseJobCards(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            HttpSession session = request.getSession();
            serviceForm sf = (serviceForm) form;
            serviceDAO obj = new serviceDAO();
            String user_id = (String) session.getAttribute("user_id");
            Vector userFunctionalities=(Vector) session.getAttribute("userFun");
            sf.setDealercode(session.getAttribute("dealerCode").toString());
            sf.setJobCardStatus("open");
            sf.setFromdate("");
            sf.setTodate("");
           ArrayList<serviceForm> jobcardDetails = obj.getJobCardDetails(sf, "", "", user_id,userFunctionalities);
           request.setAttribute("jobcardDetails", jobcardDetails);
           request.setAttribute("closeflag", "true");

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("view_alljobcardDetails");
    }

    /**
     *  Re open job card created on 26/09/14 by Megha
     */
    public ActionForward initReOpenJobCards(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            serviceForm sf = (serviceForm) form;
            serviceDAO obj = new serviceDAO();
          //  LinkedHashSet<LabelValueBean> jobCardStatusList = obj.getCommonLabelValues("Jobcardstatusmaster", "statusID", "statusDesc,seqNo", "seqNo", "");
            String vinid=request.getParameter("vinid");
            sf.setVinid(vinid);
            ArrayList<serviceForm> jobcardDetails = obj.getJobCardOpenList(sf, user_id,(String)session.getAttribute("dealerCode"));
           // request.setAttribute("jobCardStatusList", jobCardStatusList);
            request.setAttribute("jobcardDetails", jobcardDetails);


        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("closeJC");
    }



    public ActionForward initSearchHistory(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            serviceForm sf = (serviceForm) form;
            serviceDAO obj = new serviceDAO();
            Vector userFunctionalities=(Vector) session.getAttribute("userFun");
            ArrayList<serviceForm> vehicle_HistoryList = obj.getJobCardHistory(sf,(String)session.getAttribute("dealerCode"),user_id,userFunctionalities);
            request.setAttribute("vehicle_HistoryList", vehicle_HistoryList);
            request.setAttribute("nameSrch", sf.getVinNo());
            //${nameSrch}
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("searchhistory");
    }
    /**
     * view Jobcard Details for given job card no. created on 30/09/14 by Megha
     */

    public ActionForward viewJobcard(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            String forwarding="viewJobcard";
        try {

            HttpSession session = request.getSession();
             Session hrbsession = HibernateUtil.getSessionFactory().openSession();
            boolean decodeStatus = false;
            String user_id = (String) session.getAttribute("user_id");
            serviceForm sf = (serviceForm) form;

            serviceDAO serDAO = new serviceDAO();
            String vinNo = commonUtilMethods.decodeBase64(request.getParameter("vinNo"));
            String status = commonUtilMethods.decodeBase64(request.getParameter("status"));
            if(status!=null && status.equalsIgnoreCase("open")){
                String check = MethodUtility.check_in_Db_HQL(commonUtilMethods.decodeBase64(sf.getJobCardNo()), "SpInventSaleMaster", "typeRefno", "", hrbsession);
                if (check.equalsIgnoreCase("exist")) {
                  
                        forwarding = "closeJC";
                        ArrayList<serviceForm> jobcardDetails = new serviceDAO().getJobCardOpenList(sf, user_id, (String)session.getAttribute("dealerCode"));
                        request.setAttribute("jobcardDetails", jobcardDetails);
                        request.setAttribute("message", "Job Card cannot be Re-Open because Invoice already exist for given Reference No."+commonUtilMethods.decodeBase64(sf.getJobCardNo()));
                        return mapping.findForward(forwarding);
                    }
            }
            if(status!=null && status.trim().length()>0 && !status.equalsIgnoreCase("open")){
                request.setAttribute("dealerCode", commonUtilMethods.decodeBase64(request.getParameter("dealerCode")));
                decodeStatus = true;
            //sf.setDealercode(commonUtilMethods.decodeBase64(request.getParameter("dealerCode")));
            }
            if(!decodeStatus){
                if(request.getParameter("dealerCode")!=null){
                    sf.setDealercode(commonUtilMethods.decodeBase64(request.getParameter("dealerCode")));
                }
            }

            sf.setVinNo(commonUtilMethods.decodeBase64(sf.getVinNo()));
            sf.setJobCardNo(commonUtilMethods.decodeBase64(sf.getJobCardNo()));

            String jobCardNo=commonUtilMethods.decodeBase64(request.getParameter("jobCardNo"));
            sf.setStatus(commonUtilMethods.decodeBase64(request.getParameter("status")));
            sf=serDAO.viewJobcard(user_id, vinNo, jobCardNo,sf);

          
            request.setAttribute("jobcardDetails",sf);
            request.setAttribute("flag", commonUtilMethods.decodeBase64(request.getParameter("flag")));
            request.setAttribute("status", commonUtilMethods.decodeBase64(request.getParameter("status")));
            request.setAttribute("pagename", request.getParameter("pagename"));
            serDAO.getCustomerDetailById(sf);
            if(commonUtilMethods.decodeBase64(request.getParameter("flag")).toString().equalsIgnoreCase("print")){
                
            }

            if(commonUtilMethods.decodeBase64(request.getParameter("flag")).toString().equalsIgnoreCase("Ginvoice"))
            {
                if(commonUtilMethods.decodeBase64(request.getParameter("createInvoice")).toString().equals("true"))
                {
                    request.setAttribute("noOfDays", serDAO.getHesConstantValue(10));
                    forwarding="generateInvoice";
                }
            }
            else if(commonUtilMethods.decodeBase64(request.getParameter("flag")).toString().equalsIgnoreCase("print"))
            {
                forwarding="printJobCard";
                request.setAttribute("dealerCode", sf.getDealercode());
                request.setAttribute("heading", "Job Card");
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(forwarding);
    }
  
    /**
     *  changing status of job card created on 6/10/14 by Megha
     */
    public ActionForward setJobCardNoStatus(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        try {
            HttpSession session = request.getSession();
            serviceDAO obj = new serviceDAO();
            serviceForm sf = (serviceForm) form;
//            PrintWriter pw = response.getWriter();
            String user_id = (String) session.getAttribute("user_id");
//            String jobcard = request.getParameter("jobcard");
//            System.out.println("jobcard" + jobcard);
//            String jobStatus = request.getParameter("jobStatus");
//            System.out.println("jobStatus" + jobStatus);
            String result = obj.setJobCardNoStatus(sf.getJobCardNo(),sf.getStatus(),user_id,sf.getRemarks());
            ActionMessages messages = new ActionMessages();
            if(result.equalsIgnoreCase("success"))
            {
                messages.add("SUCCESS", new ActionMessage("label.common.jobCardUpdateSuccess"));
            }
            else if(result.equalsIgnoreCase("FAILURE")){
                messages.add("FAILURE", new ActionMessage("label.common.jobCardUpdateFailure"));
            }
            else{
                messages.add("EXIST", new ActionMessage("label.common.jobCardUpdateFailure"));
            }
            saveErrors(request, messages);
            initReOpenJobCards(mapping,form,request,response);
//            pw.print(result);

        } catch (Exception e1) {
            } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("closeJC");
    }

    /**
     *   job card List 4 create Invoice created on 6/10/14 by Megha
     */
    public ActionForward initCreateInvoiceList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            serviceForm sf = (serviceForm) form;
            serviceDAO obj = new serviceDAO();
            ArrayList<serviceForm> jobcardDetails = obj.getJobCardOpenList(sf, user_id,(String)session.getAttribute("dealerCode"));
            request.setAttribute("jobcardDetails", jobcardDetails);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("initCreateInvoiceList");
    }


      public ActionForward approveJobCard(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            serviceForm sf = (serviceForm) form;
            serviceDAO serDAO = new serviceDAO();
             ActionMessages messages = new ActionMessages();
            String result = serDAO.approveJobCard(sf,user_id);
            if (result.equals("success")) {
                if(sf.getStatus().equalsIgnoreCase("Approved")){
                messages.add("SUCCESS", new ActionMessage("label.common.jcApproveSuccess"));
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "serviceProcess.do?option=initPendingDE4Approval");
                }
                else{
                 messages.add("SUCCESS", new ActionMessage("label.common.jcRejectSuccess"));
                 request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "serviceProcess.do?option=initPendingDE4Approval");
               }
            } else {
                  messages.add("FAILURE", new ActionMessage("label.common.jcFailure"));
               //request.setAttribute("show_message", "Error Occured While Approving Job Card for Warranty Claim.");
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "serviceProcess.do?option=initPendingDE4Approval");
            }
            saveErrors(request, messages);

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("message");
    }

    /**
     *   create Invoice created on 13/10/14 by Megha
     */
    public ActionForward generateInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            String forwardto="message";
        try {
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            serviceForm sf = (serviceForm) form;
            serviceDAO obj = new serviceDAO();
            sf.setDealercode(session.getAttribute("dealerCode").toString());
            sf.setJobType(request.getParameter("jobType").toString());
            sf.setVinNo(sf.getVinNo());
            //obj.getCustomerDetailById(sf);
            //System.out.println("***"+sf.getJobCardNo());
            String data_str = obj.generateInvoice(sf,user_id);
             ActionMessages messages = new ActionMessages();
            if(data_str.split("@@")[0].equalsIgnoreCase("SUCCESS")){
                 messages.add("SUCCESS", new ActionMessage("label.common.invoiceSuccess"));
             request.setAttribute("show_message",data_str.split("@@")[2]);}
            else if(data_str.split("@@")[0].equalsIgnoreCase("FAILURE")){
                 messages.add("FAILURE", new ActionMessage("label.common.invoiceFailure"));}
            else if(data_str.split("@@")[0].equalsIgnoreCase("EXIST")){
                 messages.add("EXIST", new ActionMessage("label.common.invoiceexist"));
            }else if(data_str.split("@@")[0].equalsIgnoreCase("FAILURECUST")){
                messages.add("FAILURE", new ActionMessage("label.generateInvoice.FailureCusId"));
            }else if(data_str.split("@@")[0].equalsIgnoreCase("HSNCODE")){
                messages.add("FAILURE", new ActionMessage("label.generateInvoice.FailureHsn"));
                request.setAttribute("show_message",data_str.split("@@")[1]);
            }
            
            //System.out.println(""+data_str);
            request.setAttribute("result", data_str.split("@@")[0].toUpperCase());
            if (data_str.split("@@")[0].toUpperCase().equals("EXIST")) {
//                request.setAttribute("message", data_str.split("@@")[1]);
                initCreateInvoiceList(mapping, form, request, response);
                forwardto = "initCreateInvoiceList";
            }
            else if (data_str.split("@@")[0].toUpperCase().equals("SUCCESS")) {
                request.setAttribute("generateInvoiceLink", "YES");
                request.setAttribute("generateInvoiceLinkURL", "serviceProcess.do?option=initCreateInvoiceList");
                request.setAttribute("printInvoiceLinkURL", "serviceProcess.do?option=printInvoice&invoiceNo="+commonUtilMethods.encodeToBase64(data_str.split("@@")[2])+"&refNo="+commonUtilMethods.encodeToBase64(sf.getJobCardNo())+"&type=print");
                forwardto = "message";
            } else if(data_str.split("@@")[0].equalsIgnoreCase("HSNCODE")){
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "serviceProcess.do?option=initCreateInvoiceList");
                forwardto = "message";
            }else {
                request.setAttribute("show_message", "");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "serviceProcess.do?option=initCreateInvoiceList");
                forwardto = "message";
            }
            saveErrors(request, messages);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(forwardto);
    }

    /**
     *  Invoice no List 4 print invoice created on 03/11/14 by Megha
     */
    public ActionForward initviewInvoiceList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "viewInvoiceList";
        String todate="";String fromdate="";
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
           HttpSession session = request.getSession();
            Vector userFunctionalities=(Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            serviceForm sf = (serviceForm) form;
            serviceDAO obj = new serviceDAO();
            //sf.setDealercode(session.getAttribute("dealerCode").toString());
             String flag=request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                sf.setRange("1");
                //sf.setCreditAmount("1"); //Avinash 30-07-2015
            }
            sf.setRange(sf.getRange() == null ? "" : sf.getRange());
            sf.setCreditAmount(sf.getCreditAmount() == null ? "" : sf.getCreditAmount());
            sf.setInvoiceno(sf.getInvoiceno()==null?"":sf.getInvoiceno());
            sf.setFromdate(sf.getFromdate()==null?fromdate:sf.getFromdate());
            sf.setTodate(sf.getTodate()==null?todate:sf.getTodate());
            sf.setUserId(user_id);

/*          if(userFunctionalities.contains("101")){
                sf.setDealercode(session.getAttribute("dealerCode").toString());
  
            }else if(userFunctionalities.contains("102")){
                    ArrayList<ArrayList<String>> dealerList=MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('"+sf.getUserId()+"',dm.loginId)", 3);
                    request.setAttribute("dealerList",dealerList);
 
            }else if(userFunctionalities.contains("103")){
                    ArrayList<ArrayList<String>> dealerList=MethodUtility.getListwithJoinQuery("Dealervslocationcode d", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "", 3);
                    request.setAttribute("dealerList",dealerList);
            }
 */
            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                sf.setDealercode((String) session.getAttribute("dealerCode").toString());
            } else {
                request.setAttribute("dealerList", dealerList);
            }

            if(sf.getDealercode()==null){sf.setDealercode("ALL");}
            ArrayList<serviceForm> invoiceList4print = obj.getInvNoList4print(user_id,sf,userFunctionalities);
           // request.setAttribute("jobCardStatusList", jobCardStatusList);
           request.setAttribute("invoiceList4print", invoiceList4print);
           request.setAttribute("servForm", sf);
           request.setAttribute("range", sf.getRange());
           request.setAttribute("creditAmount", sf.getCreditAmount());

           if (request.getParameter("eType") != null && request.getParameter("eType").equals("export")) {
                forward = "exportViewInvoiceList";
            } else {
                forward = "viewInvoiceList";
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(forward);
    }

    /**
     * Print invoice data created on 03/11/14 by Megha
     */
    public ActionForward printInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            serviceForm sf = (serviceForm) form;
            serviceDAO obj = new serviceDAO();
            String type= commonUtilMethods.decodeBase64(request.getParameter("type"));
            sf.setInvoiceno(commonUtilMethods.decodeBase64(request.getParameter("invoiceNo").toString()));
            if(!type.equals("csprint")){
            sf.setRefNo(commonUtilMethods.decodeBase64(request.getParameter("refNo").toString()));}
            sf.setJobCardNo(sf.getRefNo());
            sf.setDealercode(session.getAttribute("dealerCode").toString());
            sf = obj.printInvoiceData(sf);
            request.setAttribute("jobcardDetails", sf);
            request.setAttribute("dealerCode", sf.getDealercode());
            request.setAttribute("type", request.getParameter("type"));
            request.setAttribute("heading", "Invoice");
//            request.setAttribute("serForm", sf);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("printInvoice");
    }
 public ActionForward printTrasDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
            String forword="";
        try {
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            serviceForm sf = (serviceForm) form;
            serviceDAO obj = new serviceDAO();
            String type=request.getParameter("type");
            String action=request.getParameter("action");
            String dealerCode=request.getParameter("dealerCode");
            sf.setRefNo(request.getParameter("refNo").toString());
            request.setAttribute("jobcardDetails", sf);
            request.setAttribute("dealerCode", dealerCode);
            if (action.equalsIgnoreCase("couSaleReturn")) {
                request.setAttribute("datalist", obj.printConSaleReturn(sf));
                request.setAttribute("type", request.getParameter("type"));
                request.setAttribute("heading", "Counter Sale Return");
                forword = "printConSaleReturn";
            } else if (action.equalsIgnoreCase("exportdetail")) {
                request.setAttribute("datalist", obj.printExportDetails(sf));
                request.setAttribute("type", request.getParameter("type"));
                request.setAttribute("heading", "Export Dealer Deatails");
                forword = "printExportDetail";
            } else {
                request.setAttribute("datalist", obj.printInvOtherDealer(sf));
                request.setAttribute("type", request.getParameter("type"));
                request.setAttribute("heading", "Inventory From Other Dealer");
                forword = "printInvOtherDealer";
            }

            

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(forword);
    }


    public ActionForward invoiceTransctionList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
              String forward = "viewTransacList";
        try {
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Calendar cal = Calendar.getInstance();
            String todate = dateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, -1);
            String fromdate = dateFormat.format(cal.getTime());
            HttpSession session = request.getSession();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            serviceForm sf = (serviceForm) form;

            Calendar toDate = Calendar.getInstance();
            if (sf.getTodate() != null) {
                toDate.setTime(dateFormat.parse(sf.getTodate()));
                toDate.add(Calendar.DATE, 1);
                sf.setTodate(dateFormat.format(toDate.getTime()));
            }
            serviceDAO obj = new serviceDAO();
            if (userFunctionalities.contains("101")) {
                sf.setDealercode(session.getAttribute("dealerCode").toString());
            } else {
                sf.setDealercode(sf.getDealercode());
            }
            sf.setInvoiceno(sf.getInvoiceno() == null ? "" : sf.getInvoiceno());
            sf.setFromdate(sf.getFromdate() == null ? fromdate : sf.getFromdate());
            sf.setTodate(sf.getTodate() == null ? todate : sf.getTodate());
            sf.setRefNo(sf.getRefNo() == null ? "" : sf.getRefNo());

//            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
//            if (userFunctionalities.contains("101")) {
//                sf.setDealercode((String) session.getAttribute("dealerCode"));
//            } else {
//                request.setAttribute("dealerList", dealerList);
//            }

            if (sf.getRefNo().equalsIgnoreCase("1")) {
                ArrayList<serviceForm> invoiceList4print = obj.invTransListByRefNo(user_id, sf, userFunctionalities);
                request.setAttribute("invoiceList4print", invoiceList4print);
                request.setAttribute("action", "Invoice");
                if (request.getParameter("etype") != null && request.getParameter("etype").equals("export")) {
                    request.setAttribute("deCode", sf.getDealercode());
                    forward = "exportTransacList";
                } else {
                    forward = "viewTransacList";
                }
            } else if (sf.getRefNo().equalsIgnoreCase("2")) {
                ArrayList<serviceForm> invoiceList4print = obj.counterSaleReturnList(user_id, sf, userFunctionalities);
                request.setAttribute("invoiceList4print", invoiceList4print);
                request.setAttribute("action", "CounterSale");
                if (request.getParameter("etype") != null && request.getParameter("etype").equals("export")) {
                    request.setAttribute("deCode", sf.getDealercode());
                    forward = "exportTransacList";
                } else {
                    forward = "viewTransacList";
                }
            } else if (sf.getRefNo().equalsIgnoreCase("3")) {
                ArrayList<serviceForm> invoiceList4print = obj.invOtherDealerList(user_id, sf, userFunctionalities);
                request.setAttribute("invoiceList4print", invoiceList4print);
                request.setAttribute("action", "InvOtherDealer");
                if (request.getParameter("etype") != null && request.getParameter("etype").equals("export")) {
                    request.setAttribute("deCode", sf.getDealercode());
                    forward = "exportTransacList";
                } else {
                    forward = "viewTransacList";
                }
            } else if (sf.getRefNo().equalsIgnoreCase("4")) {
                ArrayList<SpOrderInvGrn> invoiceList4print = obj.invoiceGrnList(user_id, sf, userFunctionalities);
                request.setAttribute("invoiceList4print", invoiceList4print);
                request.setAttribute("action", "grndetail");
                if (request.getParameter("etype") != null && request.getParameter("etype").equals("export")) {
                    request.setAttribute("deCode", sf.getDealercode());
                    forward = "exportTransacList";
                } else {
                    forward = "viewTransacList";
                }
            } else if (sf.getRefNo().equalsIgnoreCase("5")) {
                ArrayList<serviceForm> invoiceList4print = obj.invExportDealerList(user_id, sf, userFunctionalities);
                request.setAttribute("invoiceList4print", invoiceList4print);
                request.setAttribute("action", "exportdetail");
                if (request.getParameter("etype") != null && request.getParameter("etype").equals("export")) {
                    request.setAttribute("deCode", sf.getDealercode());
                    forward = "exportTransacList";
                } else {
                    forward = "viewTransacList";
                }
            }
        
            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            request.setAttribute("dealerList", dealerList);
            
            request.setAttribute("servForm", sf);
          
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(forward);
    }

/*
 * sercviceReminder
 */
    public ActionForward initsercviceReminder(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forwardTo = "initsercviceReminder";
        String fromdate = "";
        String todate = "";
        try {
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            serviceForm sf = (serviceForm) form;
            serviceDAO obj = new serviceDAO();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                cal.add(Calendar.MONTH, -1);
                cal.set(Calendar.DATE, 1);

                fromdate = dateFormat.format(cal.getTime());
                Calendar cal1 = Calendar.getInstance();
                cal1.add(Calendar.DAY_OF_MONTH, 15);
                //cal.add(Calendar.DATE, 7);
                // cal.add(Calendar.MONTH, 1);
                // cal.add(Calendar.DAY_OF_MONTH, cal.getActualMaximum(Calendar.DAY_OF_MONTH));
                todate = dateFormat.format(cal1.getTime());
                flag = "";
                sf.setRange("1");
            }
            sf.setRange(sf.getRange() == null ? "" : sf.getRange());

            sf.setUserId(user_id);
            sf.setFromdate(sf.getFromdate() == null ? fromdate : sf.getFromdate());
            sf.setTodate(sf.getTodate() == null ? todate : sf.getTodate());
            
            LinkedHashSet<LabelValueBean> jobTypeList = obj.getCommonLabelValues("Jobtypemaster", "jobTypeID", "jobTypeDesc,seqNo", "seqNo", " where isActive='Y' and freeService='Y'");
            LabelValueBean lv = new LabelValueBean("Paid Service / Repair", "9");
            jobTypeList.add(lv);

            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                sf.setDealercode((String) session.getAttribute("dealerCode"));
            } else {
                request.setAttribute("dealerList", dealerList);
            }

/*            if (userFunctionalities.contains("101")) {
                sf.setDealercode(session.getAttribute("dealerCode").toString());
            } else if (userFunctionalities.contains("102")) {
                ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('" + sf.getUserId() + "',dm.loginId)", 3);
                request.setAttribute("dealerList", dealerList);
            } else if (userFunctionalities.contains("103")) {
                ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "", 3);
                request.setAttribute("dealerList", dealerList);
            }
 * 
 */
            sf.setDealercode(sf.getDealercode() == null ? "ALL" : sf.getDealercode());
            if (request.getParameter("etype") != null && request.getParameter("etype").toString().equals("export")) {
                forwardTo = "exportServiceReminder";
            }
            ArrayList<serviceForm> sercviceReminderList = obj.getsercviceReminder(sf, userFunctionalities);
            request.setAttribute("jobTypeList", jobTypeList);
            request.setAttribute("sercviceReminderList", sercviceReminderList);
            request.setAttribute("range", sf.getRange());
            
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(forwardTo);
    }


    public ActionForward initServiceDoneLapse(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "serviceDoneLapse";
        String fromdate = "";
        String todate = "";
        try {
            HttpSession session = request.getSession();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id= session.getAttribute("user_id").toString();
            serviceForm sf = (serviceForm) form;
            serviceDAO obj = new serviceDAO();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                fromdate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, 1);
                todate = dateFormat.format(cal.getTime());
                flag = "";
                sf.setRange("1");
            }
            sf.setRange(sf.getRange() == null ? "" : sf.getRange());
           
            sf.setUserId(user_id);
            sf.setFromdate(sf.getFromdate() == null ? fromdate : sf.getFromdate());
            sf.setTodate(sf.getTodate() == null ? todate : sf.getTodate());
            LinkedHashSet<LabelValueBean> jobTypeList = obj.getCommonLabelValues("Jobtypemaster", "jobTypeID", "jobTypeDesc,seqNo", "seqNo", "");

            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                sf.setDealercode((String) session.getAttribute("dealerCode"));
            } else {
                request.setAttribute("dealerList", dealerList);
            }

    /*        if (userFunctionalities.contains("101")) {
                sf.setDealercode(session.getAttribute("dealerCode").toString());
            } else if (userFunctionalities.contains("102")) {
                ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('" + sf.getUserId() + "',dm.loginId)", 3);
                request.setAttribute("dealerList", dealerList);
            } else if (userFunctionalities.contains("103")) {
                ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "", 3);
                request.setAttribute("dealerList", dealerList);
            }
     * 
     */
            sf.setDealercode(sf.getDealercode() == null ? "ALL" : sf.getDealercode());
            ArrayList<serviceForm> sercviceReminderList = obj.getServiceDoneLapse(sf, userFunctionalities);
            request.setAttribute("jobTypeList", jobTypeList);
            request.setAttribute("sercviceReminderList", sercviceReminderList);
            if (request.getParameter("etype") != null && request.getParameter("etype").equals("export")) {
                forward = "exportServiceDoneLapse";
            }
            request.setAttribute("range", sf.getRange());

        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward(forward);
    }

    public ActionForward printGrn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         
        serviceForm sf = (serviceForm) form;
        request.setAttribute("dealerCode", request.getParameter("dealerCode"));
        request.setAttribute("heading", "GRN DETAILS");
        String grnNo = commonUtilMethods.decodeBase64(request.getParameter("grnNo"));
        serviceDAO obj = new serviceDAO();
        sf.setSpOrderInvGrn(obj.getInvoiceGrn(grnNo));
        request.setAttribute("spOrderInvGrnDetail", obj.getInvoiceGrnDetailsList(grnNo));
        return mapping.findForward("printGrn");
    }

    public ActionForward getFollowup(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        String type = "";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            serviceForm enquiryForm = (serviceForm) form;
            conn = new dbConnection().getConnection();
            serviceDAO obj = new serviceDAO(conn);
            String scheduleID = request.getParameter("scheduleID");
            String dueFollowUpEnq = request.getParameter("dfleq");
            Calendar cal = Calendar.getInstance();


            enquiryForm.setCallDate(dateFormat.format(cal.getTime()));
            //obj.getFollowUpDetails(scheduleID, enquiryForm);
            LinkedHashSet<LabelValueBean> serviceTypeIdList = obj.getCommonLabelValueHiber("Servicetypemaster", "serviceTypeID", "serviceTypeDesc", "serviceTypeDesc", "");
            request.setAttribute("serviceTypeIdList", serviceTypeIdList);//enquiryTypeList
            request.setAttribute("followUpHistoryList", obj.followUpHistoryList(scheduleID, enquiryForm));
            if (dueFollowUpEnq != null && !dueFollowUpEnq.isEmpty() && dueFollowUpEnq.equalsIgnoreCase("Y")) {
                request.setAttribute("DashdueFollowUpEnq", dueFollowUpEnq);
            }
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
        return mapping.findForward("getFollowup");
    }
     public ActionForward updateFollowUp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        try {

            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            serviceDAO enquiryDaoObj = new serviceDAO();
            serviceForm sForm = (serviceForm) form;
            SWVehicleServiceFollowup sf = new SWVehicleServiceFollowup();
            sf.setCreatedBy(user_id);
            String result = enquiryDaoObj.addFollowUp(sf, sForm);
            if (result.equals("success")) {
                request.setAttribute("show_message", "FollowUp has been Updated Successfully.");
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("modifyMoreLink", "YES");
                request.setAttribute("modifyMoreLinkURL", "serviceProcess.do?option=initsercviceReminder");
            }
        } catch (Exception e) {
            System.out.println("Exception:-"+e.getMessage());
        }
        return mapping.findForward("message");

    }

      /**
     * Print TAX Invoice data created on 17/05/16 by Avinash Pandey
     */
    public ActionForward printTaxInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;
        try {
//            HttpSession session = request.getSession();
//            serviceForm sf = (serviceForm) form;
//            serviceDAO obj = new serviceDAO();
//            String type= commonUtilMethods.decodeBase64(request.getParameter("type"));
//            sf.setInvoiceno(commonUtilMethods.decodeBase64(request.getParameter("invoiceNo").toString()));
//            if(!type.equals("csprint")){
//                sf.setRefNo(commonUtilMethods.decodeBase64(request.getParameter("refNo").toString()));
//            }
//            sf.setJobCardNo(sf.getRefNo());
//            sf.setDealercode(session.getAttribute("dealerCode").toString());
//            sf = obj.printTaxInvoiceData(sf);
//            request.setAttribute("spTaxDetails", sf);
//            request.setAttribute("dealerCode", sf.getDealercode());
//            request.setAttribute("type", request.getParameter("type"));
//            request.setAttribute("heading", "Tax Invoice");

            String path = request.getSession().getServletContext().getRealPath("/");
            //String filename = path + "/jasperFile/itlInvoice.jasper";
            String filename = path + "/jasperFile/itlTaxInvoiceHeader.jasper";

            HashMap<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("invoiceNo", commonUtilMethods.decodeBase64(request.getParameter("invoiceNo").toString()));
            parameterMap.put("imgPath", path+"layout/images/logo.png");

            conn = new dbConnection().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);
            request.setAttribute("jasperPrint", jasperPrint);
            request.setAttribute("reportType", "pdf");
            request.setAttribute("reportName", "TAX_INVOICE");
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
        return mapping.findForward("downloadReport");
        // return mapping.findForward("printTaxInvoice");
    }


/// ---------------Work for Extented Warranty ---------------------/////

    public ActionForward initAddExtWarranty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String forwardTo = "initAddExtWarranty";
        HttpSession session = request.getSession();
        String seqNo = "",stateId="";
        Session hsession = HibernateUtil.getSessionFactory().openSession();
        try {
            serviceForm sf = (serviceForm) form;           
            serviceDAO sDao = new serviceDAO();            
            String dealerCode = (String) session.getAttribute("dealerCode");
            sf.setDealercode(dealerCode);
            String srch = request.getParameter("srch");
            String vinNo = request.getParameter("vinNo");
            vinNo = vinNo == null ? (String) request.getAttribute("vinNo") : vinNo;
            if (srch != null) {
                if (srch.equalsIgnoreCase("viewDiv")) {
                    seqNo = new MethodUtility().getNumberEW(hsession, "EWMLoaderDetail", dealerCode, "EW");
                    //stateId = MethodUtility.getStateIdByDealer(dealerCode,"Dealervslocationcode","stateId","dealerCode","",hsession);
                    request.setAttribute("flagCheck", "viewDiv");                    
                    sf.setEwLoaderId(seqNo);
                    //sf.setStateID(Integer.valueOf(stateId));
                    sDao.getPolicyType(sf);
                    request.setAttribute("policyTypeList",sf.getPolicyTypeList());
                    request.setAttribute("mechanicList",new serviceDAO().getMechanicCode(dealerCode));
                    sDao.getVehicleDetails(vinNo, sf);                                     
                    forwardTo = "initAddExtWarranty";
                }
            }
            request.setAttribute("vinNo", vinNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forwardTo);
    }

    public ActionForward addExtWarranty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String result = "FAILURE";
        String forwardTo = "";
        HttpSession session = request.getSession();
        String server_name = (String) session.getValue("server_name");
        String ecatPath = (String) session.getValue("ecatPATH");
        String mainURL = (String) session.getValue("mainURL");
        PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            serviceForm sf = (serviceForm) form;
            sf.setExtWarrantyBookletNo(object_pageTemplate.warrBookNo);            
            sf.setImdCode(object_pageTemplate.imdCode);
            sf.setFloatType(object_pageTemplate.floatType);
            sf.setPpId(Long.parseLong(object_pageTemplate.ppId));
            sf.setTypeName(object_pageTemplate.typeName);
            int delDaysCount = Integer.parseInt(object_pageTemplate.deliveryDaysDiff);

            serviceDAO sDao = new serviceDAO();
            String userId = (String) session.getAttribute("user_id");
            String dealerCode = (String) session.getAttribute("dealerCode");
            sf.setDealercode(dealerCode);
            Date today = new Date(df.format(new Date()));
            Date delDate = sdf1.parse(sf.getDeliveryDate());
            int countDays = serviceDAO.daysBetween( delDate,today);
            if(sf.getCreditAmount()==null|| sf.getCreditAmount().equals("0.00")|| sf.getCreditAmount().equals("0") || sf.getCreditAmount().equals("0.0")){
      
                request.setAttribute("vinNo",sf.getChassisNo());
                request.setAttribute("message", "Extended Warranty can't be added because Premium Amount is Zero for Chassis No: ' "+sf.getChassisNo()+" '");
                return mapping.findForward("premiumAmount");
                //serviceProcess.do?option=initAddExtWarranty&srch=viewDiv
            }
            if (countDays < delDaysCount){
                result = sDao.saveExtWarranty(sf, userId);
            }
            ActionMessages messages = new ActionMessages();

            if (result.equalsIgnoreCase("SUCCESS")) {
                messages.add("SUCCESS", new ActionMessage("label.common.ewLoaderSuccess"));
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("show_message", sf.getEwLoaderId());
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "serviceProcess.do?option=initAddExtWarranty");
                forwardTo = "message";
            } else {
                messages.add("FAILURE", new ActionMessage("label.common.ewLoaderFailure"));
                request.setAttribute("result", "FAILURE");
                request.setAttribute("show_message", "");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "serviceProcess.do?option=initAddExtWarranty");
                forwardTo = "message";
            }

            saveErrors(request, messages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forwardTo);
    }

   public ActionForward getPremiumAmt(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String result = "";
        try {
            serviceDAO sDao = new serviceDAO();
            String dealerCode="";
            String policyType = request.getParameter("policyType");
            String delDate = request.getParameter("delDate");
            if(request.getSession().getAttribute("dealerCode")!=null && !request.getSession().getAttribute("dealerCode").equals("null")){
                dealerCode = ""+request.getSession().getAttribute("dealerCode");
            }else{
                dealerCode = ""+request.getParameter("dealerCode");
            }
            result = sDao.getPremiumAmt(policyType, delDate, dealerCode);
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

    public ActionForward isVehicleExist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        
        HttpSession session = request.getSession();
        String server_name = (String) session.getValue("server_name");
        String ecatPath = (String) session.getValue("ecatPATH");
        String mainURL = (String) session.getValue("mainURL");
        PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
        String result = "";
        try {
            int delDaysCount = Integer.parseInt(object_pageTemplate.deliveryDaysDiff);
            serviceDAO sDao = new serviceDAO();
            String vinNo = request.getParameter("vinNo");
            result = sDao.isVehicleExist(vinNo,delDaysCount);
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

    public ActionForward isChassisExist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        
        String resultChassis = "";
        try {
            serviceDAO sDao = new serviceDAO();
            String vinNo = request.getParameter("vinNo");
            resultChassis = sDao.isChassisAlreadyExist(vinNo);
            try {
                PrintWriter pw = response.getWriter();
                pw.write(resultChassis);
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

    public ActionForward viewExtendedWarranty(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String fromdate = "", todate = "";
        String forward= "viewExtWarranty";
        try {
            HttpSession session = request.getSession();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            serviceDAO sDao = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            String chassisNo = request.getParameter("chassisNo");
            sf.setChassisNo(chassisNo);
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
            sf.setFromdate(sf.getFromdate() == null ? fromdate : sf.getFromdate());
            sf.setTodate(sf.getTodate() == null ? todate : sf.getTodate());
            sf.setStatus(sf.getStatus() == null ? "All" : sf.getStatus());
            sf.setChassisNo(sf.getChassisNo() == null ? "" : sf.getChassisNo());
            request.setAttribute("extWarrStatus", sDao.getExtWarrStatus());

            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                sf.setDealercode((String) session.getAttribute("dealerCode"));
            } else {
                request.setAttribute("dealerList", dealerList);
            }
            sf.setDealercode(sf.getDealercode()== null ? "ALL" : sf.getDealercode());
            request.setAttribute("fromdate", sf.getFromdate());
            request.setAttribute("todate", sf.getTodate());
            request.setAttribute("range", sf.getRange());            
            
            if (etype != null && etype.equalsIgnoreCase("Export")) {
                request.setAttribute("expWarrListExport", sDao.getExpWarrDetailsExport(sf, user_id, userFunctionalities));
                forward = "exportExtWarranty";
            }  else{
                request.setAttribute("viewExpWarrList", sDao.getViewExpWarrDetails(sf, user_id, userFunctionalities));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(forward);
    }

     public ActionForward getExtWarrPopupView(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {        
        try {
            HttpSession session = request.getSession();            
            String user_id = (String) session.getAttribute("user_id");
            serviceDAO sDao = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            String ewRefNo = request.getParameter("ewRefNo");
            String chassisNo = request.getParameter("chassisNo");            
            sDao.getExtWarrPopupView(sf, ewRefNo, chassisNo, user_id);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward("extWarrPopup");
    }

    public ActionForward editExtWarranty(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forwardTo ="";
        try {
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            serviceDAO sDao = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            String ewRefNo = request.getParameter("ewRefNo");
            String dealerCode = request.getParameter("dealerCode");
            String chassisNo = request.getParameter("chassisNo");
            sDao.getPolicyType(sf);
            request.setAttribute("policyTypeList",sf.getPolicyTypeList());
            request.setAttribute("mechanicList",new serviceDAO().getMechanicCode(request.getParameter("userCode")));
            sDao.getExtWarrPopupView(sf, ewRefNo, chassisNo, user_id);           
            forwardTo = "initEditExtWarranty";           

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(forwardTo);
    }

    public ActionForward updateExtWarranty(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String forward = "successExtWarr";
        String result = "failure", message = "";
        try {
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            serviceDAO sDao = new serviceDAO();
            serviceForm sf = (serviceForm) form;                      
            result=sDao.updateExtWarranty(sf, user_id);
            if (result.equals("success")) {                
                message = "Extended Warranty Detail has been Successfully updated for Ref No. ";
            } else {
                message = "Unable to updated Extended Warranty Details, Please Contact System Administrator.";
            }
            request.setAttribute("message", message);
            request.setAttribute("result", result);
            request.setAttribute("refNo", sf.getEwLoaderId());
                         
        } catch (Exception e) {            
           e.printStackTrace();
        }
         return mapping.findForward(forward);                  
    }
    
    public ActionForward initAddItlExtWarranty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
    	
        String forwardTo = "initAddItlExtWarranty";
        HttpSession session = request.getSession();
        String seqNo = "",stateId="";
        Session hsession = HibernateUtil.getSessionFactory().openSession();
        try {
            serviceForm sf = (serviceForm) form;           
            serviceDAO sDao = new serviceDAO();            
            String dealerCode = (String) session.getAttribute("dealerCode");
            sf.setDealercode(dealerCode);
            String srch = request.getParameter("srch");
            String vinNo = request.getParameter("vinNo");
            vinNo = vinNo == null ? (String) request.getAttribute("vinNo") : vinNo;
       //     request.setAttribute(srch, sDao.getStates());
            
            if (srch != null) {
                if (srch.equalsIgnoreCase("viewDiv")) {
                    seqNo = new MethodUtility().getNumberEW(hsession, "ITLEWMLoaderDetail", dealerCode, "ITLEW");
                    //stateId = MethodUtility.getStateIdByDealer(dealerCode,"Dealervslocationcode","stateId","dealerCode","",hsession);
                    request.setAttribute("flagCheck", "viewDiv");                    
                    sf.setEwLoaderId(seqNo);
                    //sf.setStateID(Integer.valueOf(stateId));
                    sDao.getPolicyType(sf);
                    request.setAttribute("policyTypeList",sf.getPolicyTypeList());
                    request.setAttribute("mechanicList",new serviceDAO().getMechanicCode(dealerCode));
              
                    sDao.getExtendedWarrantyDetails(vinNo, sf);  
                    sDao.getStates(sf);
                    forwardTo = "initAddItlExtWarranty";
                }
            }
            request.setAttribute("vinNo", vinNo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forwardTo);
    }
    
    public ActionForward addItlExtWarranty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        String result = "FAILURE";
        String forwardTo = "";
        HttpSession session = request.getSession();
  
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MMM-yyyy");
        try {
            serviceForm sf = (serviceForm) form;
            serviceDAO sDao = new serviceDAO();
            String userId = (String) session.getAttribute("user_id");
            String dealerCode = (String) session.getAttribute("dealerCode");
            sf.setDealercode(dealerCode);
            
            result = sDao.saveItlExtWarranty(sf, userId);
            
            ActionMessages messages = new ActionMessages();

            if (result.equalsIgnoreCase("SUCCESS")) {
                messages.add("SUCCESS", new ActionMessage("label.common.ewLoaderSuccess"));
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("show_message", sf.getEwLoaderId());
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "serviceProcess.do?option=initAddExtWarranty");
                forwardTo = "message";
            } else {
                messages.add("FAILURE", new ActionMessage("label.common.ewLoaderFailure"));
                request.setAttribute("result", "FAILURE");
                request.setAttribute("show_message", "");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "serviceProcess.do?option=initAddExtWarranty");
                forwardTo = "message";
            }

            saveErrors(request, messages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forwardTo);
    }
    
    public ActionForward viewItlExtendedWarranty(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String fromdate = "", todate = "";
        String forward= "viewItlExtendedWarranty";
        try {
            HttpSession session = request.getSession();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            serviceDAO sDao = new serviceDAO();
            serviceForm sf = (serviceForm) form;
            String chassisNo = request.getParameter("chassisNo");
            sf.setChassisNo(chassisNo);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String etype = request.getParameter("etype") == null ? "" : request.getParameter("etype");
            String flag = request.getParameter("flag");
            if (flag == null) {
//                Calendar cal = Calendar.getInstance();
//                todate = dateFormat.format(cal.getTime());
//                cal.add(Calendar.DATE, -1);
//                fromdate = dateFormat.format(cal.getTime());
                  flag = "";
                sf.setRange("1");
            }
            sf.setRange(sf.getRange() == null ? "" : sf.getRange());
            sf.setUserId(user_id);            
            sf.setFromdate(sf.getFromdate() == null ? fromdate : sf.getFromdate());
            sf.setTodate(sf.getTodate() == null ? todate : sf.getTodate());
            sf.setStatus(sf.getStatus() == null ? "All" : sf.getStatus());
            sf.setChassisNo(sf.getChassisNo() == null ? "" : sf.getChassisNo());
            request.setAttribute("extWarrStatus", sDao.getExtWarrStatus());

            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                sf.setDealercode((String) session.getAttribute("dealerCode"));
            } else {
                request.setAttribute("dealerList", dealerList);
            }
            sf.setDealercode(sf.getDealercode()== null ? "ALL" : sf.getDealercode());
            request.setAttribute("fromdate", sf.getFromdate());
            request.setAttribute("todate", sf.getTodate());
            request.setAttribute("range", sf.getRange());            
            
            if (etype != null && etype.equalsIgnoreCase("Export")) {
            	
    		    Connection conn = null;
    		    OutputStream outputStream = null;

    		        conn = new dbConnection().getConnection();
    		        String path = request.getSession().getServletContext().getRealPath("/");
    		        String filename = path + "/jasperFile/Warranty_Claim_Status_Report.jasper";

    		        HashMap<String, Object> parameterMap = new HashMap<>();
    		        parameterMap.put("CHASSISNO", sf.getVinNo());
    		        parameterMap.put("FROMDATE", sf.getFromdate());
    		        parameterMap.put("TODATE", sf.getTodate());
    		        parameterMap.put("DEALERCODE", sf.getDealercode());
    		        parameterMap.put("STATUS", sf.getStatus());
    		        parameterMap.put("USER_ID", user_id);

    		        JasperPrint jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);

    		    
    		    if(jasperPrint.getPages().isEmpty()) {
    				System.out.println("empty pages");
    			}
//    			if (jasperPrint != null && !jasperPrint.getPages().isEmpty()) {
    				try {
    					outputStream = response.getOutputStream();

    					printReport("xls", jasperPrint, true, "View Extended Registration Report", outputStream,null,response);
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
//    			} else {
    				
//    			}
            	
                forward = "null";
            }  else if(flag.equals("check")){
                request.setAttribute("viewExpWarrList", sDao.getViewExpItlExtWarrDetails(sf, user_id, userFunctionalities));
            }
            String contextPathProject = request.getContextPath();
            String path = request.getServletContext().getRealPath("/");
            request.setAttribute("contextPathProject", contextPathProject);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(forward);
    }
    
	public ActionForward fetchDistricts(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PrintWriter pw = null;
		serviceDAO sDao = new serviceDAO();
		try {
			String stateIdStr = request.getParameter("stateId");
			int stateId = Integer.parseInt(stateIdStr);
			LinkedHashSet<LabelValueBean> districtList = sDao.getDistricts(stateId);
			pw = response.getWriter();
			response.setContentType("text/plain"); // Set the response type

			StringBuilder result = new StringBuilder();
			for (LabelValueBean lvb : districtList) {
				result.append(lvb.getLabel()).append(":").append(lvb.getValue()).append(",");
			}
			if (result.length() > 0) {
				result.setLength(result.length() - 1); // Remove the last comma
			}
			pw.write(result.toString());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return mapping.findForward(null);
	}
	
	public ActionForward fetchCities(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PrintWriter pw = null;
		serviceDAO sDao = new serviceDAO();
		try {
			String districtIdStr = request.getParameter("districtId");
			int districtId = Integer.parseInt(districtIdStr);
			LinkedHashSet<LabelValueBean> cityList = sDao.getCities(districtId);
			pw = response.getWriter();
			response.setContentType("text/plain"); // Set the response type

			StringBuilder result = new StringBuilder();
			for (LabelValueBean lvb : cityList) {
				result.append(lvb.getLabel()).append(":").append(lvb.getValue()).append(",");
			}
			if (result.length() > 0) {
				result.setLength(result.length() - 1); // Remove the last comma
			}
			pw.write(result.toString());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return mapping.findForward(null);

	}
	
	public ActionForward fetchTehsils(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		PrintWriter pw = null;
		serviceDAO sDao = new serviceDAO();
		try {
			String cityIdStr = request.getParameter("cityId");
			int cityId = Integer.parseInt(cityIdStr);
			LinkedHashSet<LabelValueBean> tehsilList = sDao.getTehsil(cityId);
			pw = response.getWriter();
			response.setContentType("text/plain"); // Set the response type

			StringBuilder result = new StringBuilder();
			for (LabelValueBean lvb : tehsilList) {
				result.append(lvb.getLabel()).append(":").append(lvb.getValue()).append(",");
			}
			if (result.length() > 0) {
				result.setLength(result.length() - 1); // Remove the last comma
			}
			pw.write(result.toString());
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		return mapping.findForward(null);

	}
	
   public ActionForward isChassisExistInItlExtWty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        
        String resultChassis = "";
        try {
            serviceDAO sDao = new serviceDAO();
            String vinNo = request.getParameter("vinNo");
            resultChassis = sDao.isChassisExistInItlExtWty(vinNo);
            try {
                PrintWriter pw = response.getWriter();
                pw.write(resultChassis);
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
   
   public ActionForward getItlExtWarrPopupView(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response) throws Exception {        
       try {
           HttpSession session = request.getSession();            
           String user_id = (String) session.getAttribute("user_id");
           serviceDAO sDao = new serviceDAO();
           serviceForm sf = (serviceForm) form;
           String ewRefNo = request.getParameter("ewRefNo");
           String chassisNo = request.getParameter("chassisNo");            
           sDao.getItlExtWarrPopupView(sf, ewRefNo, chassisNo, user_id);
       } catch (Exception e1) {
           e1.printStackTrace();
       }
       return mapping.findForward("extItlWarrPopup");
   }
   
   public ActionForward editItlExtWarranty(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response) throws Exception {
       String forwardTo ="";
       try {
           HttpSession session = request.getSession();
           String user_id = (String) session.getAttribute("user_id");
           serviceDAO sDao = new serviceDAO();
           serviceForm sf = (serviceForm) form;
           String ewRefNo = request.getParameter("ewRefNo");
           String dealerCode = request.getParameter("dealerCode");
           String chassisNo = request.getParameter("chassisNo");
           sDao.getItlExtWarrPopupView(sf, ewRefNo, chassisNo, user_id);           
           forwardTo = "initEditItlExtWarranty";           

       } catch (Exception e1) {
           e1.printStackTrace();
       }
       return mapping.findForward(forwardTo);
   }
   
   public ActionForward updateItlExtWarranty(ActionMapping mapping, ActionForm form,
           HttpServletRequest request, HttpServletResponse response) throws Exception {

       String forward = "successExtWarr";
       String result = "failure", message = "";
       try {
           HttpSession session = request.getSession();
           String user_id = (String) session.getAttribute("user_id");
           serviceDAO sDao = new serviceDAO();
           
           serviceForm sf = (serviceForm) form;                      
           
           if(sf.getEwLoaderId() != null && !sf.getEwLoaderId().equals(""))
           result=sDao.updateItlExtWarranty(sf, user_id);
           if (result.equals("success")) {                
               message = "ITL Extended Warranty Detail has been Successfully updated for Ref No. ";
           } else {
               message = "Unable to updated ITL Extended Warranty Details, Please Contact System Administrator.";
           }
           request.setAttribute("message", message);
           request.setAttribute("result", result);
           request.setAttribute("refNo", sf.getEwLoaderId());
                        
       } catch (Exception e) {            
          e.printStackTrace();
       }
        return mapping.findForward(forward);                  
   }
   
	public ActionForward printGstTaxInvoice(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Connection conn = null;
		try {

			String path = request.getSession().getServletContext().getRealPath("/");

			String filename = path + "/jasperFile/GST_TAX_INVOICE_DEALER_TO_CUSTOMER_Header.jasper";

			HashMap<String, Object> parameterMap = new HashMap<String, Object>();
			parameterMap.put("ITLEW_REF_NO",
					commonUtilMethods.decodeBase64(request.getParameter("EwRefNo").toString()));

			conn = new dbConnection().getConnection();
			JasperPrint jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);
			request.setAttribute("jasperPrint", jasperPrint);
			request.setAttribute("reportType", "pdf");
			request.setAttribute("reportName", "GST_TAX_INVOICE");
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
		return mapping.findForward("downloadReport");
	}
   
	 public ActionForward isVehicleExistForItlExtWty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
	        
	        HttpSession session = request.getSession();
	        String server_name = (String) session.getValue("server_name");
	        String ecatPath = (String) session.getValue("ecatPATH");
	        String mainURL = (String) session.getValue("mainURL");
	        PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
	        String result = "";
	        try {
	            int delDaysCount = Integer.parseInt(object_pageTemplate.deliveryDaysDiff);
	            serviceDAO sDao = new serviceDAO();
	            String vinNo = request.getParameter("vinNo");
	            result = sDao.isVehicleExistForItlExtWty(vinNo,delDaysCount);
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


  
    
}
