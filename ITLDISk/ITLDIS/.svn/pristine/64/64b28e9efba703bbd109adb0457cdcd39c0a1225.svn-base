/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action.piAction;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;
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

import com.common.MethodUtility;

import beans.piBean.PIFormBean;
import dao.piDao.CreatePIDao;
import dbConnection.dbConnection;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import viewEcat.comEcat.ConnectionHolder;
import viewEcat.orderEcat.PriceDetails;

/**
 *
 * @author yogasmita.patel
 */
public class CreatePIAction extends DispatchAction {

    private CreatePIDao piDao = null;
    private HttpSession session = null;
    private PIFormBean piForm = null;

    public ActionForward getorderListForCreatePI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            piDao = new CreatePIDao();
            piForm = (PIFormBean) form;
            session = request.getSession();
           // ArrayList<ArrayList<String>> dealerList = new ArrayList<ArrayList<String>>();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            Boolean searchFlag = request.getParameter("searchFlag").equals("true") ? true : false;

             List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
             request.setAttribute("dealerList", dealerList);

             if(userFunctionalities.contains("101"))
             {
                 piForm.setDealerCode((String) session.getAttribute("dealerCode"));
             }
             
            
            if (piForm.getDealerCode() == null) {
                piForm.setDealerCode("ALL");
            }

            piForm.setDealerCode(request.getParameter("dealerCode"));
            piForm.setOrderType(request.getParameter("orderType"));

            if (searchFlag) {
                List<PIFormBean> orderList = piDao.getPOListExpoDealer(piForm);
                request.setAttribute("orderList", orderList);
                request.setAttribute("buyer", piForm.getBuyer());
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
        }
        return mapping.findForward("initcreatePIforExpoOrder");
    }

    public ActionForward getorderDetailList(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "";
        try {
            piDao = new CreatePIDao();
            piForm = (PIFormBean) form;
            ConnectionHolder holder = (ConnectionHolder) request.getSession().getAttribute("servletapp.connection");
            Connection conn = holder.getConnection();
            PriceDetails pObj = new PriceDetails(conn);
            List<PIFormBean> orderDtlList = piDao.getPOListDetails(piForm, pObj);
            ArrayList<String> poList = new ArrayList<String>(Arrays.asList(piForm.getCheckedPOList()));
            session = request.getSession();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            List dealerList = new ArrayList();
            String fromdate = "";
            String todate = "";
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String user_id = (String) session.getAttribute("user_id");
            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                piForm.setRange("1");
            }
            piForm.setFromDate(piForm.getFromDate() == null ? fromdate : piForm.getFromDate());
            piForm.setToDate(piForm.getToDate() == null ? todate : piForm.getToDate());
            if (!poList.isEmpty() && poList != null) {
                String cus_po_no = poList.get(0).toString();

                if (orderDtlList != null && !orderDtlList.isEmpty()) {
                    request.setAttribute("orderList", orderDtlList);
                    piDao.getOrderDetail(piForm, cus_po_no);
                    request.setAttribute("paymentTermsList", MethodUtility.getCommonLabelValueHiber("MSPPaymentTermsEXPMaster", "paymentTermCode", "paymentTermDesc", "paymentTermDesc", "where e.isActive='Y' "));
                    request.setAttribute("otherChargeList", piDao.getOtherChargeList());
                    forward = "createPIExpoOrder";
                    saveToken(request);
                } else {
                    if (userFunctionalities.contains("101")) {                        
                        piForm.setDealerCode((String) session.getAttribute("dealerCode"));
                    } 

                    dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
                    request.setAttribute("dealerList", dealerList);
                    
                    if (piForm.getDealerCode() == null) {
                        piForm.setDealerCode("ALL");
                    }
                    request.setAttribute("range", piForm.getRange());
                    List<PIFormBean> orderList = piDao.getPOListExpoDealer(piForm);
                    request.setAttribute("orderList", orderList);
                    request.setAttribute("buyer", piForm.getBuyer());
                    request.setAttribute("message", "PIFail");
                        forward = "initcreatePIforExpoOrder";
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
        }
        return mapping.findForward(forward);
    }

    public ActionForward savePIDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String result = "";
        ActionMessages messages = new ActionMessages();
        try {
            piDao = new CreatePIDao();
            piForm = (PIFormBean) form;
            session = request.getSession();
            String userId = (String) session.getAttribute("user_id");

            if (isTokenValid(request)) {
                result = piDao.savePIDetails(piForm, userId);
                resetToken(request);
            }
            Properties prop = (Properties) session.getAttribute("prop");
            String msg = prop.getProperty("label.spare.PISuccessMessage");
            if (result.equals("success")) {
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("show_message", msg + piForm.getPiNo());
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "createPIAction.do?option=getListForInitPI");
            } else {
                messages.add("FAILURE", new ActionMessage("label.spare.PICreationFailure"));
                request.setAttribute("result", "FAILURE");
                request.setAttribute("show_message", "");
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "createPIAction.do?option=getListForInitPI");
            }
            saveErrors(request, messages);
        } catch (Exception e1) {
            e1.printStackTrace();
            messages.add("FAILURE", new ActionMessage("label.spare.PICreationFailure"));
            request.setAttribute("result", "FAILURE");
            request.setAttribute("show_message", "");
            request.setAttribute("addMoreLink", "YES");
            request.setAttribute("addMoreLinkURL", "createPIAction.do?option=getListForInitPI");
        }
        return mapping.findForward("message");
    }

    public ActionForward getPIListForView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            piDao = new CreatePIDao();
            piForm = (PIFormBean) form;
            String fromdate = "";
            String todate = "";
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            session = request.getSession();
            ArrayList<ArrayList<String>> dealerList = new ArrayList<ArrayList<String>>();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                piForm.setRange("1");
            }
            piForm.setFromDate(piForm.getFromDate() == null ? fromdate : piForm.getFromDate());
            piForm.setToDate(piForm.getToDate() == null ? todate : piForm.getToDate());
            if (piForm.getOrderType().equals("ALL")) {
                piForm.setOrderType("%");
            }

           /* if (userFunctionalities.contains("103")) {
                dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d ", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "where d.dealerCategory='EXPORT'", 3);
                request.setAttribute("dealerList", dealerList);
            } else if (userFunctionalities.contains("102")) {
                dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('" + user_id + "',dm.loginId) and  d.dealerCategory='EXPORT'", 3);
                request.setAttribute("dealerList", dealerList);
            } else {
                piForm.setDealerCode((String) session.getAttribute("dealerCode"));
            }
            request.setAttribute("dealerList", dealerList);
           */

            dealerList = (ArrayList) MethodUtility.getDealersDetailsUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                piForm.setDealerCode((String) session.getAttribute("dealerCode"));
            }
                request.setAttribute("dealerList", dealerList);
             
            if (piForm.getDealerCode() == null) {
                piForm.setDealerCode("");
            }
            request.setAttribute("range", piForm.getRange());
            List<PIFormBean> piList = piDao.getPIDetailForView(piForm);
            request.setAttribute("piList", piList);
            request.setAttribute("buyer", piForm.getDealerCode() + " [" + piForm.getDealerName() + "]");
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
        }
        return mapping.findForward("initViewPI");
    }

    public ActionForward getPIDetailForView(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            piDao = new CreatePIDao();
            ConnectionHolder holder = (ConnectionHolder) request.getSession().getAttribute("servletapp.connection");
           // PriceDetails pd = new PriceDetails(conn);
            piForm = (PIFormBean) form;
            piForm.setPiNo(request.getParameter("piNo"));
            piForm.setOrderType(request.getParameter("orderType"));
            List<PIFormBean> piList = piDao.getPIDetailForView(piForm);
            List<PIFormBean> invList = piDao.getInvoiceDetailView(piForm);
            List<PIFormBean> partDetailList = piDao.getPIPartDetail(piForm);
           // request.setAttribute("priceListCode", pd.getCurrency(pd.getPriceListCode(piForm.getUserId())));
            piDao.getOtherChargeDetail(piForm);
            request.setAttribute("piList", piList);
            request.setAttribute("invList", invList);
            request.setAttribute("buyerEditAllowed", request.getParameter("buyerEditAllowed"));
            request.setAttribute("acceptFlag", request.getParameter("acceptFlag"));
            request.setAttribute("partDetailList", partDetailList);
            request.setAttribute("invCancelledList", piDao.getInvoiceCancelledDetailView(piForm));
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
        }
        return mapping.findForward("viewPIDetail");
    }

    public ActionForward getAlternateParts(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            piDao = new CreatePIDao();
            String partNo = request.getParameter("partNo");
            String dealerCode = request.getParameter("dealerCode");
            String priceCode = request.getParameter("priceCode");
            try {
//                ConnectionHolder holder = (ConnectionHolder) request.getSession().getAttribute("servletapp.connection");
//                Connection conn = holder.getConnection();
//                PriceDetails pObj = new PriceDetails(conn);
//                String priceListCode = pObj.getPriceListCode(dealerCode);
                String alternatePart = piDao.getAlternatePartNos(partNo, priceCode);
                PrintWriter pw = response.getWriter();
                pw.write(alternatePart);
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
        }
        return null;
    }

    public ActionForward updatePIDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        ActionMessages messages = new ActionMessages();
        piDao = new CreatePIDao();
        Vector userFunctionalities = (Vector) request.getSession().getAttribute("userFun");
        String result = "";
        try {
            piForm = (PIFormBean) form;
            result = piDao.updatePIDetails(piForm, userFunctionalities);
            Properties prop = (Properties) session.getAttribute("prop");
            String msg = prop.getProperty("label.spare.PIAcceptSuccess");
            if (result.equals("success")) {
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("show_message", piForm.getPiNo() + " " + msg);
                request.setAttribute("modifyMoreLink", "YES");
                request.setAttribute("modifyMoreLinkURL", "createPIAction.do?option=getPIListForView&orderType=ALL");
            } else {
                messages.add("FAILURE", new ActionMessage("label.spare.PIAcceptFailure"));
                request.setAttribute("result", "FAILURE");
                request.setAttribute("show_message", "");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "createPIAction.do?option=getPIListForView&searchFlag=false");
            }
            saveErrors(request, messages);
        } catch (Exception e1) {
            e1.printStackTrace();
            messages.add("FAILURE", new ActionMessage("label.spare.PIAcceptFailure"));
            request.setAttribute("result", "FAILURE");
            request.setAttribute("show_message", "");
            request.setAttribute("optionLink", "YES");
            request.setAttribute("optionLinkURL", "createPIAction.do?option=getPIListForView&searchFlag=false");
        }
        return mapping.findForward("message");
    }

    public ActionForward viewPrintPI_Tibco(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {

        Connection conn = null;
        OutputStream ouputStream = null;
        try {
            String path = request.getSession().getServletContext().getRealPath("/");
            String filename = path + "/jasperFile/proformaInvoice.jasper";
            String piNo = request.getParameter("piNo");

            HashMap<String, Object> parameterMap = new HashMap<String, Object>();
            parameterMap.put("piNo", piNo);

            Locale locale = new Locale("en", "US");
            ResourceBundle labels = ResourceBundle.getBundle("com.myapp.struts.ApplicationResource", locale);
            //ResourceBundle labels = ResourceBundle.getBundle("com.myapp.struts.ApplicationResource_en_US");
            //ResourceBundle labels = ResourceBundle.getBundle("ApplicationResource_en_US");
            parameterMap.put("REPORT_RESOURCE_BUNDLE", labels);
            
            conn = new dbConnection().getConnection();
            JasperPrint jasperPrint = JasperFillManager.fillReport(filename, parameterMap, conn);
            request.setAttribute("jasperPrint", jasperPrint);
            request.setAttribute("reportType", "pdf");
            request.setAttribute("reportName", "PROFORMA INVOICE");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            conn.close();
            try {
                if (ouputStream != null) {
                    ouputStream.close();
                }
            } catch (IOException ex) {
            }
        }
        return mapping.findForward("downloadReport");
    }

    public ActionForward getListForInitPI(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            piDao = new CreatePIDao();
            piForm = (PIFormBean) form;
            List<PIFormBean> orderList = piDao.getListForPI(piForm);
            request.setAttribute("orderList", orderList);
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
        }
        return mapping.findForward("initListForInitPI");
    }
}
