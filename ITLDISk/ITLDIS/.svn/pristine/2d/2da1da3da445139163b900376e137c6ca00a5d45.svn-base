/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.Globals;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.apache.struts.action.ActionMessages;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.upload.FormFile;
import org.apache.struts.util.LabelValueBean;
import org.hibernate.Session;

import com.EAMG.common.commonUtilMethods;
import com.common.MailSender;
import com.common.MethodUtility;

import HibernateUtil.HibernateUtil;
import beans.inventoryForm;
import dao.LoginDAO;
import dao.inventoryDAO;

/**
 *
 * @author sreeja.vijayakumaran
 */
public class InvtoryAction extends DispatchAction {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";

    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward initInvOptions(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "inventoryOption";
        return mapping.findForward(forward);
    }

    public ActionForward uploadInventory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "uploadInventoryfwd";
        return mapping.findForward(forward);
    }

    public ActionForward uploadInvtryExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String show_message = "";
        File dest_folder = null;
        String dest = "";
        String filename = "";
        FormFile xls = null;
        String xls_validate_result = "";
        File xlsfile = null;
        String ext = "";
        File cehckExcelData = null;
        String contextPath = (String) request.getContextPath();
        String realPath = getServlet().getServletContext().getRealPath("/");
        String dealerCode = (String) session.getAttribute("dealerCode");

        try {
            //for getting excel file and uploading it onto server.
            boolean isFileUploaded = false;
            inventoryDAO invDAO = new inventoryDAO();
            inventoryForm invForm = (inventoryForm) form;

            dest_folder = new File(realPath + "/dealer/tempExcels/" + dealerCode);
            if (!dest_folder.exists()) {
                dest_folder.mkdirs();
            }

            // request.setAttribute("modelNo", modelNo);
            dest = realPath + "/dealer/tempExcels/" + dealerCode;
            xls = invForm.getExcelFile();
            filename = xls.getFileName();
            xlsfile = new File(dest + "/" + filename);

            isFileUploaded = invDAO.uploadXls(filename, dest, xls, realPath);
            ext = filename.substring(filename.indexOf("."));
            if (ext.equals(".xlsx")) {
                xlsfile = invDAO.uploadXlsxImage(filename, dest, realPath);
                isFileUploaded = invDAO.uploadXls(filename.substring(0, filename.length() - 1), dest, xls, realPath);
            }
            if (isFileUploaded) {
                xls_validate_result = invDAO.isInventoryExcelValidated(xlsfile);

                if (xls_validate_result.equals("success1")) {

                    ArrayList<ArrayList<String>> insertionresult = invDAO.inventoryExcelInsertion(xlsfile, dealerCode, contextPath, invForm);

                    ArrayList<String> message = (ArrayList<String>) insertionresult.get(0);
                    //ArrayList<String> exists = (ArrayList<String>) insertionresult.get(1);

                    request.setAttribute("show_message", message.get(0));
                    if (message.get(0).equals("Part No. has not been Added.")) {
                        request.setAttribute("optionLink", "YES");
                        request.setAttribute("optionLinkURL", "inventoryAction.do?option=uploadInventory");
                    }
                    request.setAttribute("result", "SUCCESS");
                    request.setAttribute("addMoreLink", "YES");
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=uploadInventory");
                    //request.setAttribute("exists", exists);

                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "/dealer/tempExcels/" + dealerCode);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    session.setAttribute("failPartList", invForm.getFailPartList());
                    return mapping.findForward("message");

                } else if (xls_validate_result.equals("readingerr")) {

                    show_message = "Error occured while reading Excel. It may be due to corrupted Excel file or invalid file path. Hence Chassis Vs Model Process aborted. Attach valid Excel to complete the Process successfully.";

                    request.setAttribute("result", "FAILURE");
                    request.setAttribute("show_message", show_message);
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "inventoryAction.do?option=uploadInventory");
                    //delete the temporary excel file
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "dealer/tempExcels/" + dealerCode);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }

                    return mapping.findForward("message");
                } else {

                    request.setAttribute("result", "FAILURE");
                    request.setAttribute("show_message", xls_validate_result);
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "inventoryAction.do?option=uploadInventory");
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "dealer/tempExcels/" + dealerCode);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    return mapping.findForward("message");
                }
            } else {

                request.setAttribute("result", "FAILURE");
                request.setAttribute("show_message", "File could not be uploaded.");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "inventoryAction.do?option=uploadInventory");
                return mapping.findForward("message");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("result", "FAILURE");
            request.setAttribute("show_message", "Error Occured. Please contact administrator.");
            request.setAttribute("optionLink", "YES");
            request.setAttribute("optionLinkURL", "inventoryAction.do?option=uploadInventory");//delete the temporary excel file
            if (xlsfile.exists() && xlsfile.isFile()) {
                xlsfile.delete();
            }
            File src_folder = new File(realPath + "dealer/tempExcels/" + dealerCode);
            if (src_folder.exists()) {
                src_folder.delete();
            }
            return mapping.findForward("message");
        }
    }

    public ActionForward viewInventory(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "viewinventory";
        try {
            HttpSession session = request.getSession();
            inventoryDAO invDAO = new inventoryDAO();
            inventoryForm invForm = (inventoryForm) form;
            String priceListCode = (String) session.getAttribute("priceListCode");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");

            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                invForm.setDealerCode((String) session.getAttribute("dealerCode"));

                ArrayList<inventoryForm> inventoryList = invDAO.getInventoryList(invForm, priceListCode);
                request.setAttribute("inventoryList", inventoryList);
                if (request.getParameter("etype") != null && request.getParameter("etype").equals("export")) {
                    forward = "exportinventory";
                } else {
                    forward = "viewinventory";
                }
            } else {
                request.setAttribute("dealerList", dealerList);
                if (request.getParameter("getDataFlag").toString().equalsIgnoreCase("true")) {
                    invForm.setDealerCode(invForm.getDealerCode() == null ? null : invForm.getDealerCode().equals("") == true ? null : invForm.getDealerCode());
                    ArrayList<inventoryForm> inventoryList = invDAO.getInventoryList(invForm, priceListCode);
                    request.setAttribute("inventoryList", inventoryList);
                }
                if (request.getParameter("etype") != null && request.getParameter("etype").equals("export")) {
                    forward = "exportinventory";
                } else {
                    forward = "viewinventory4HO";
                }
            }

            /*           if (userFunctionalities.contains("101")) {
            String dealerCode = (String) session.getAttribute("dealerCode");
            invForm.setDealerCode(dealerCode);
            ArrayList<inventoryForm> inventoryList = invDAO.getInventoryList(invForm, priceListCode);
            request.setAttribute("inventoryList", inventoryList);
            if (request.getParameter("etype") != null && request.getParameter("etype").equals("export")) {
            forward = "exportinventory";
            } else {
            forward = "viewinventory";
            }

            } else if (userFunctionalities.contains("102") || userFunctionalities.contains("103")) {
            String user_id = (String) session.getAttribute("user_id");
            if (userFunctionalities.contains("102")) {
            ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('" + user_id + "',dm.loginId)", 3);
            request.setAttribute("dealerList", dealerList);
            } else {
            ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d ", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "", 3);
            request.setAttribute("dealerList", dealerList);
            }
            if (request.getParameter("getDataFlag").toString().equalsIgnoreCase("true")) {
            invForm.setDealerCode(invForm.getDealerCode() == null ? null : invForm.getDealerCode().equals("") == true ? null : invForm.getDealerCode());
            ArrayList<inventoryForm> inventoryList = invDAO.getInventoryList(invForm, priceListCode);
            request.setAttribute("inventoryList", inventoryList);
            }
            if (request.getParameter("etype") != null && request.getParameter("etype").equals("export")) {
            forward = "exportinventory";
            } else {
            forward = "viewinventory4HO";
            }
            }
             *
             */
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward lockinventory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "lockinventory";
        return mapping.findForward(forward);
    }

    public ActionForward completeLockInventory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("result", "SUCCESS");
        HttpSession session = request.getSession();
        String dealerCode = (String) session.getAttribute("dealerCode");
        inventoryDAO invDAO = new inventoryDAO();
        inventoryForm invForm = (inventoryForm) form;
        try {
            invDAO.insertInventoryLeger(dealerCode);
            invDAO.insertInventoryLock(dealerCode);
            session.setAttribute("lockedDealerlist", invDAO.getDealerCode());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        request.setAttribute("show_message", "Inventory Locked Successfully.");
        return mapping.findForward("message");
    }

    public ActionForward initCounterSale(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "initCounterSale";
        inventoryDAO invDAO = new inventoryDAO();

        try {
            LinkedHashSet<LabelValueBean> categoryList = invDAO.getCategoryList();
            request.setAttribute("categoryList", categoryList);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward getcustNameByCustcategoryID(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            inventoryDAO invDAO = new inventoryDAO();
            inventoryForm invForm = (inventoryForm) form;
            invForm.setCustcategoryID(String.valueOf(request.getParameter("custcategoryID")));
            invForm.setDealerCode((String) session.getAttribute("dealerCode"));
            String result = invDAO.getcustNameByCustcategoryID(invForm);
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

    public ActionForward counterSale(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "counterSale";
        inventoryDAO invDAO = new inventoryDAO();

        HttpSession session = request.getSession();
        try {
            inventoryForm invForm = (inventoryForm) form;

            invForm.setCustcategoryID(String.valueOf(request.getParameter("custcategoryID")));
            invForm.setCustomerId(String.valueOf(request.getParameter("customerId")));

            invForm.setDealerCode((String) session.getAttribute("dealerCode"));
            request.setAttribute("billedList", invDAO.getBilledList());
            invDAO.getcustNameById(invForm);
            LinkedHashSet<LabelValueBean> saleTaxTypeList = invDAO.getTaxSaleTypeList();
            //invForm.setSaleTaxTypeList(saleTaxTypeList);
            request.setAttribute("saleTaxTypeList", saleTaxTypeList);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward addCounterSale(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "";
        double finalDis = 0.0, discountAmt = 0.0;
        ActionMessages messages = new ActionMessages();
        try {
            inventoryForm invForm = (inventoryForm) form;
            inventoryForm invenForm = null;
            inventoryDAO invDAO = new inventoryDAO();
            HttpSession session = request.getSession();

            String user_id = (String) session.getAttribute("user_id");
            invForm.setDealerCode((String) session.getAttribute("dealerCode"));
            //Set SellingPercentage
            invForm.setSellingPercentage((Double) session.getAttribute("sellingPercentage"));
            if (request.getParameter("eType") != null && request.getParameter("eType").equals("export")) {
                request.setAttribute("profarmaList", invDAO.profarmaInvoiceCounterSale(invForm, user_id));
                //Avinash Discount Logic 21-03-2016
                finalDis = Math.floor(Double.parseDouble(invForm.getFinalamount()));
                discountAmt = Double.parseDouble(invForm.getFinalamount()) - finalDis;
                invForm.setFinalamount("" + Math.floor(Double.parseDouble(invForm.getFinalamount())));
                invForm.setOtherDiscount("" + (Double.parseDouble(invForm.getOtherDiscount()) + discountAmt));
                //invForm.setOtherDiscount(""+Double.parseDouble(invForm.getOtherDiscount()+dfAmt.format(discountAmt)));
                //
                request.setAttribute("heading", "Proforma Invoice");
                request.setAttribute("headingFlag", "");
                request.setAttribute("dealerCode", (String) session.getAttribute("dealerCode"));
                forward = "exportProfarmaInvoice";
            } else if (!request.getParameter("eType").equals("export") && request.getParameter("eType").equals("")) {
                if (isTokenValid(request)) {
                    if (invForm.getCustomerId() != null && invForm.getCustomerId().length() > 0) {
                        invenForm = invDAO.saveCounterSale(invForm, user_id);
                        if (invenForm.getResult().equals("success")) {
                            resetToken(request);
                            request.setAttribute("result", "SUCCESS");
                            messages.add("SUCCESS", new ActionMessage("label.common.counterSaleSuccess"));
                            request.setAttribute("show_message", invenForm.getInvNo());
                            request.setAttribute("printInvoiceLink", "YES");
                            request.setAttribute("printInvoiceLinkURL", "serviceProcess.do?option=printInvoice&invoiceNo=" + commonUtilMethods.encodeToBase64(invenForm.getInvNo()) + "&type=" + commonUtilMethods.encodeToBase64("csprint"));
                            request.setAttribute("addMoreLink", "YES");
                            request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=initCounterSale");//option=counterSale
                        }else if(invenForm.getResult().equals("HSNCODE")) {
                            messages.add("FAILURE", new ActionMessage("label.common.counterSaleFailure"));
                            request.setAttribute("show_message", "HSN CODE not present in Datadase for given part");
                            request.setAttribute("addMoreLink", "YES");
                            request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=initCounterSale");
                        }else {
                            messages.add("FAILURE", new ActionMessage("label.common.counterSaleFailure"));
                            request.setAttribute("show_message", "");
                            request.setAttribute("addMoreLink", "YES");
                            request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=initCounterSale");
                        }
                    } else {
                        messages.add("FAILURE", new ActionMessage("label.common.counterSaleFailure"));
                        request.setAttribute("show_message", "");
                        request.setAttribute("addMoreLink", "YES");
                        request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=initCounterSale");
                    }
                } else {
                    messages.add("FAILURE", new ActionMessage("label.common.counterSaleFailure"));
                    request.setAttribute("show_message", "");
                    request.setAttribute("addMoreLink", "YES");
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=initCounterSale");
                }
                saveErrors(request, messages);
                forward = "message";
            }

        } catch (Exception e) {
            e.printStackTrace();
            messages.add("FAILURE", new ActionMessage("label.common.duplicate"));
            request.setAttribute("show_message", "");
            request.setAttribute("addMoreLink", "YES");
            request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=initCounterSale");
        }
        return mapping.findForward(forward);
    }

    public ActionForward getPartPriceBypartNo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String result = "";
        try {

            inventoryDAO invDAO = new inventoryDAO();
            String partno = request.getParameter("partno");
            HttpSession session = request.getSession();
            String dealerCode = (String) session.getAttribute("dealerCode");
            String priceListCode = (String) session.getAttribute("priceListCode");
            if (request.getParameter("sellingFlag") != null) {
                //Set SellingPercentage Avinash 02-12-2015
                Double sellingPercentage = (Double) session.getAttribute("sellingPercentage");
                result = invDAO.getSellingPercentageByPartNo(partno, dealerCode, priceListCode, sellingPercentage);
            } else {
                result = invDAO.getPriceByPartNo(partno, dealerCode, priceListCode);

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
        } finally {
        }
        return mapping.findForward(null);
    }

    public ActionForward createSalesReturn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "createSalesReturn";
        inventoryDAO invDAO = new inventoryDAO();
        try {
            HttpSession session = request.getSession();
            String dealerCode = (String) session.getAttribute("dealerCode");
            request.setAttribute("csInvoiceList", invDAO.getCounSaleInvoiceList(dealerCode));

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return mapping.findForward(forward);
    }

    public ActionForward getInvoiceDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        // String forward = "salesReturnDetail";
        String forward = "createSalesReturn";
        Session hrbsession = HibernateUtil.getSessionFactory().openSession();
        inventoryDAO invDAO = new inventoryDAO();
        inventoryForm invForm = (inventoryForm) form;
        try {
            HttpSession session = request.getSession();
            String dealerCode = (String) session.getAttribute("dealerCode");
            String invNo = request.getParameter("invNo");
            String checkInvoice = invDAO.checkInvoice(invNo);
            String result = MethodUtility.check_in_Db_HQL(invNo, "SpInventSaleMaster", "invoiceNo", "and m.invoicetype='CounterSale'", hrbsession);
            if (result.equals("exist") && checkInvoice.equals("notexist")) {
                request.setAttribute("dataList", invDAO.getCounSaleDetails(invForm, dealerCode, invNo));
                request.setAttribute("invNo", invNo);
                return mapping.findForward(forward);
            } else if (result.equals("exist") && checkInvoice.equals("exist")) {
//                PrintWriter pw = response.getWriter();
//                pw.write("SalesReturnCreated");
//                pw.close();
//                return mapping.findForward(null);
                request.setAttribute("invNo", invNo);
                request.setAttribute("message", "Sales Return already created for this Invoice No.");
                return mapping.findForward(forward);
            } else {
                //request.setAttribute("invNo", "Invalid Invoice No.");
//                PrintWriter pw = response.getWriter();
//                pw.write("InvalidInvoiceNo");
//                pw.close();
//                return mapping.findForward(null);
                request.setAttribute("invNo", invNo);
                request.setAttribute("message", "Invalid Invoice No.! Please Enter Valid Invoice No.");
                return mapping.findForward(forward);
            }

        } catch (Exception e1) {
            e1.printStackTrace();
        }

        return null;
    }

    public ActionForward addSalesReturn(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        try {
            inventoryForm invenForm = null;
            String result = "";
            inventoryForm invForm = (inventoryForm) form;
            inventoryDAO invDAO = new inventoryDAO();
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            invForm.setDealerCode((String) session.getAttribute("dealerCode"));
            ActionMessages messages = new ActionMessages();
            if (isTokenValid(request)) {
                if (invForm.getCustomerId() != null && !invForm.getCustomerId().equals("0")) {
                    invenForm = invDAO.saveSalesReturn(invForm, user_id);

                    if (invenForm.getResult().equals("success")) {
                        resetToken(request);
                        request.setAttribute("result", "SUCCESS");
                        messages.add("SUCCESS", new ActionMessage("label.saleReturn.Success"));
                        request.setAttribute("show_message", invenForm.getSaleReturnNo());
                        request.setAttribute("addMoreLink", "YES");
                        request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createSalesReturn");
                    } else {
                        messages.add("FAILURE", new ActionMessage("label.saleReturn.Failure"));
                        request.setAttribute("show_message", "");
                        request.setAttribute("addMoreLink", "YES");
                        request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createSalesReturn");
                    }
                } else {
                    messages.add("FAILURE", new ActionMessage("label.saleReturn.FailureCustId"));
                    request.setAttribute("show_message", "");
                    request.setAttribute("addMoreLink", "YES");
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createSalesReturn");
                }
            } else {
                messages.add("FAILURE", new ActionMessage("label.common.duplicate"));
                request.setAttribute("show_message", "");
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createSalesReturn");
            }



            saveErrors(request, messages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("message");
    }

    public ActionForward createChallan(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "createChallan";
        return mapping.findForward(forward);
    }

    /**
     * Add Inventory link
     */
    public ActionForward initaddInventory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "initaddInventory";
        try {
            HttpSession session = request.getSession();
            request.setAttribute("noOfDays", MethodUtility.getHesConstantValue(10));
            String token = generateToken(request);
            if (token != null) {
                session.setAttribute(Globals.TRANSACTION_TOKEN_KEY, token);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    /**
     * Add Inventory to database
     */
    public ActionForward addInventoryToDB(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forwardto = "initaddInventory";
        try {
            // System.out.println("in action");
            HttpSession session = request.getSession();
            inventoryForm invForm = (inventoryForm) form;
            inventoryDAO invDAO = new inventoryDAO();
            invForm.setDealerCode(session.getAttribute("dealerCode").toString());
            invForm.setUserid(session.getAttribute("user_id").toString());
            String data_str = null;
            ActionMessages messages = new ActionMessages();
            if (isTokenValid(request)) {
                data_str = invDAO.addInventoryToDB(invForm);

                if (data_str.split("@@")[0].equalsIgnoreCase("SUCCESS")) {
                    messages.add("SUCCESS", new ActionMessage("label.common.inventorySuccess"));
                    request.setAttribute("show_message", "");
                } else if (data_str.split("@@")[0].equalsIgnoreCase("FAILURE")) {
                    messages.add("FAILURE", new ActionMessage("label.common.inventoryFailure"));
                } else if (data_str.split("@@")[0].equalsIgnoreCase("FAILUREREST")) {
                    messages.add("FAILURE", new ActionMessage("label.common.restrictedPartFailure"));
                } else if (data_str.split("@@")[0].equalsIgnoreCase("EXIST")) {
                    messages.add("EXIST", new ActionMessage("label.common.inventoryExist"));
                }
                saveErrors(request, messages);
                request.setAttribute("result", data_str.split("@@")[0].toUpperCase());
                if (data_str.split("@@")[0].toUpperCase().equals("EXIST")) {
                    initaddInventory(mapping, form, request, response);
                    forwardto = "initaddInventory";
                } else if (data_str.split("@@")[0].toUpperCase().equals("SUCCESS")) {
                    request.setAttribute("addMoreLink", "YES");
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=initaddInventory");
                    forwardto = "message";
                } else if (data_str.split("@@")[0].toUpperCase().equals("FAILUREREST")) {
                	request.setAttribute("result", "FAILURE");
                	request.setAttribute("show_message", " The restricted part is :-"+data_str.split("@@")[1] +"");
                    request.setAttribute("addMoreLink", "YES");
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=initaddInventory");
                    forwardto = "message";
                } else {
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "inventoryAction.do?option=initaddInventory");
                    forwardto = "message";
                }
                resetToken(request);
            } else {
                request.setAttribute("result", "FAILURE");
                messages.add("FAILURE", new ActionMessage("label.common.duplicate"));
                request.setAttribute("show_message", "");
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=initaddInventory");
                forwardto = "message";
            }
            saveErrors(request, messages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forwardto);
    }

    /**
     * update BinLocation
     */
    public ActionForward updateBinLocation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            inventoryForm invForm = (inventoryForm) form;
            inventoryDAO invdAO = new inventoryDAO();
            String result = invdAO.updateBinLocation(invForm.getPartno(), invForm.getBinLocation(), session.getAttribute("dealerCode").toString());
            PrintWriter pw = response.getWriter();
            pw.write(result);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(null);
    }

    /**
     * inventory Ledger Report
     */
    public ActionForward inventoryLedgerReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "inventoryLedgerReport";
        HttpSession session = request.getSession();
        String fromdate = "";
        String todate = "";
        try {
            inventoryForm invForm = (inventoryForm) form;
            String user_id = session.getAttribute("user_id").toString();
            inventoryDAO invDAO = new inventoryDAO();
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                invForm.setRange("1");
            }
            invForm.setRange(invForm.getRange() == null ? "" : invForm.getRange());
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            invForm.setPartnum(invForm.getPartnum() == null ? "" : invForm.getPartnum());
            invForm.setFromdate(invForm.getFromdate() == null ? fromdate : invForm.getFromdate());
            invForm.setTodate(invForm.getTodate() == null ? todate : invForm.getTodate());

            List dealerList = MethodUtility.getDealersDetailsUnderUser(session.getAttribute("user_id").toString());
            if (userFunctionalities.contains("101")) {
                invForm.setDealerCode((String) session.getAttribute("dealerCode"));
            } else {
                request.setAttribute("dealerList", dealerList);
            }
            if (invForm.getDealerCode() == null) {
                invForm.setDealerCode("ALL");
            }

            /*
            if (userFunctionalities.contains("101")) {
            invForm.setDealerCode(session.getAttribute("dealerCode").toString());
            //                ArrayList<inventoryForm> inventoryList = invDAO.inventoryLedgerReport(invForm);
            //                request.setAttribute("inventoryList", inventoryList);
            } else if (userFunctionalities.contains("102") || userFunctionalities.contains("103")) {
            invForm.setUserid(session.getAttribute("user_id").toString());
            if (userFunctionalities.contains("102")) {
            ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('" + invForm.getUserid() + "',dm.loginId)", 3);
            request.setAttribute("dealerList", dealerList);
            } else {
            ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "", 3);
            request.setAttribute("dealerList", dealerList);
            }
            }
             *
             */
            if ("true".equalsIgnoreCase(request.getParameter("getDataFlag"))) {
                invForm.setDealerCode(invForm.getDealerCode() == null ? null : invForm.getDealerCode().equals("") == true ? null : invForm.getDealerCode());
                ArrayList<inventoryForm> inventoryList = invDAO.inventoryLedgerReport(invForm, userFunctionalities, user_id);
                request.setAttribute("inventoryList", inventoryList);
            }
            if (request.getParameter("etype") != null && request.getParameter("etype").equals("export")) {
                forward = "exportLedgerReport";
            } else {
                forward = "inventoryLedgerReport";
            }
            request.setAttribute("range", invForm.getRange());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    /*
     * init upload bin Location
     */
    public ActionForward uploadBinLoc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "uploadBinLoc";
        return mapping.findForward(forward);
    }
    /*
     * Upload Bin Location in DB
     */

    public ActionForward uploadBinLocationExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String show_message = "";
        File dest_folder = null;
        String dest = "";
        String filename = "";
        FormFile xls = null;
        String xls_validate_result = "";
        File xlsfile = null;
        String ext = "";
        File cehckExcelData = null;
        String contextPath = (String) request.getContextPath();
        String realPath = getServlet().getServletContext().getRealPath("/");
        String dealerCode = (String) session.getAttribute("dealerCode");


        try {
            //for getting excel file and uploading it onto server.
            boolean isFileUploaded = false;
            inventoryDAO invDAO = new inventoryDAO();
            inventoryForm invForm = (inventoryForm) form;

            dest_folder = new File(realPath + "/dealer/tempExcels/" + dealerCode);
            if (!dest_folder.exists()) {
                dest_folder.mkdirs();
            }

            // request.setAttribute("modelNo", modelNo);
            dest = realPath + "/dealer/tempExcels/" + dealerCode;
            xls = invForm.getExcelFile();
            filename = xls.getFileName();
            xlsfile = new File(dest + "/" + filename);

            isFileUploaded = invDAO.uploadXls(filename, dest, xls, realPath);
            ext = filename.substring(filename.indexOf("."));
            if (ext.equals(".xlsx")) {
                xlsfile = invDAO.uploadXlsxImage(filename, dest, realPath);
                isFileUploaded = invDAO.uploadXls(filename.substring(0, filename.length() - 1), dest, xls, realPath);
            }
            if (isFileUploaded) {
                xls_validate_result = invDAO.isBinLocationExcelValidated(xlsfile);

                if (xls_validate_result.equals("success1")) {

                    String message = invDAO.binLocationExcelUpdate(xlsfile, dealerCode, contextPath);
                    //System.out.println("" + message);
                    //ArrayList<String> message = (ArrayList<String>) insertionresult.get(0);
                    //ArrayList<String> exists = (ArrayList<String>) insertionresult.get(1);

                    request.setAttribute("show_message", message);
                    if (message.equals("Part No. has not been Updated.")) {
                        request.setAttribute("optionLink", "YES");
                        request.setAttribute("optionLinkURL", "inventoryAction.do?option=uploadBinLoc");
                    }
                    request.setAttribute("result", "SUCCESS");
                    request.setAttribute("addMoreLink", "YES");
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=uploadBinLoc");
                    //request.setAttribute("exists", exists);

                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "/dealer/tempExcels/" + dealerCode);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }

                    return mapping.findForward("message");

                } else if (xls_validate_result.equals("readingerr")) {

                    show_message = "Error occured while reading Excel. It may be due to corrupted Excel file or invalid file path. Hence Update BinLocation Process aborted. Attach valid Excel to complete the Process successfully.";

                    request.setAttribute("result", "FAILURE");
                    request.setAttribute("show_message", show_message);
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "inventoryAction.do?option=uploadBinLoc");
                    //delete the temporary excel file
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "dealer/tempExcels/" + dealerCode);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }

                    return mapping.findForward("message");
                } else {

                    request.setAttribute("result", "FAILURE");
                    request.setAttribute("show_message", xls_validate_result);
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "inventoryAction.do?option=uploadBinLoc");
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "dealer/tempExcels/" + dealerCode);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    return mapping.findForward("message");
                }
            } else {

                request.setAttribute("result", "FAILURE");
                request.setAttribute("show_message", "File could not be uploaded.");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "inventoryAction.do?option=uploadBinLoc");
                return mapping.findForward("message");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("result", "FAILURE");
            request.setAttribute("show_message", "Error Occured. Please contact administrator.");
            request.setAttribute("optionLink", "YES");
            request.setAttribute("optionLinkURL", "inventoryAction.do?option=uploadBinLoc");
            if (xlsfile.exists() && xlsfile.isFile()) {
                xlsfile.delete();
            }
            File src_folder = new File(realPath + "dealer/tempExcels/" + dealerCode);
            if (src_folder.exists()) {
                src_folder.delete();
            }
            return mapping.findForward("message");
        }
    }

    public ActionForward manageReorderLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "manageReorderLevel";
        HttpSession session = request.getSession();
        Vector userFunctionalities = (Vector) session.getAttribute("userFun");
        String user_id = (String) session.getAttribute("user_id");
        if (userFunctionalities.contains("102")) {
            ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('" + user_id + "',dm.loginId)", 3);
            request.setAttribute("dealerList", dealerList);
        } else {
            ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d ", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "", 3);
            request.setAttribute("dealerList", dealerList);
        }
        request.setAttribute("userId", user_id);

        return mapping.findForward(forward);
    }

    public ActionForward uploadReOrderLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        String show_message = "";
        File dest_folder = null;
        String dest = "";
        String filename = "";
        FormFile xls = null;
        String xls_validate_result = "";
        File xlsfile = null;
        String ext = "";
        File cehckExcelData = null;
        String dealerCode = "";
        String contextPath = (String) request.getContextPath();
        String realPath = getServlet().getServletContext().getRealPath("/");
        Vector userFunctionalities = (Vector) session.getAttribute("userFun");

        try {
            //for getting excel file and uploading it onto server.
            boolean isFileUploaded = false;
            inventoryDAO invDAO = new inventoryDAO();
            inventoryForm invForm = (inventoryForm) form;
            if (userFunctionalities.contains("101")) {
                dealerCode = (String) session.getAttribute("dealerCode");
                invForm.setDealerCode(dealerCode);
            } else {
                dealerCode = invForm.getDealerCode();
            }

            dest_folder = new File(realPath + "/dealer/tempExcels/" + dealerCode);
            if (!dest_folder.exists()) {
                dest_folder.mkdirs();
            }

            // request.setAttribute("modelNo", modelNo);
            dest = realPath + "/dealer/tempExcels/" + dealerCode;
            xls = invForm.getExcelFile();
            filename = xls.getFileName();
            xlsfile = new File(dest + "/" + filename);

            isFileUploaded = invDAO.uploadXls(filename, dest, xls, realPath);
            ext = filename.substring(filename.indexOf("."));

            if (isFileUploaded) {
                xls_validate_result = invDAO.reOrderLevelExcelValidated(xlsfile);  //isInventoryExcelValidated

                if (xls_validate_result.equals("success1")) {

                    ArrayList<ArrayList<String>> insertionresult = invDAO.reOrderLevelInsertion(xlsfile, dealerCode, contextPath);

                    ArrayList<String> message = (ArrayList<String>) insertionresult.get(0);
                    //ArrayList<String> exists = (ArrayList<String>) insertionresult.get(1);

                    request.setAttribute("show_message", message.get(0));
                    if (message.get(0).equals("Part No. has not been Added.")) {
                        request.setAttribute("optionLink", "YES");
                        request.setAttribute("optionLinkURL", "inventoryAction.do?option=manageReorderLevel");
                    }
                    request.setAttribute("result", "SUCCESS");
                    request.setAttribute("addMoreLink", "YES");
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=manageReorderLevel");
                    //request.setAttribute("exists", exists);

                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "/dealer/tempExcels/" + dealerCode);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }

                    return mapping.findForward("message");

                } else if (xls_validate_result.equals("readingerr")) {

                    show_message = "Error occured while reading Excel. It may be due to corrupted Excel file or invalid file path. Hence Chassis Vs Model Process aborted. Attach valid Excel to complete the Process successfully.";

                    request.setAttribute("result", "FAILURE");
                    request.setAttribute("show_message", show_message);
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "inventoryAction.do?option=manageReorderLevel");
                    //delete the temporary excel file
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "dealer/tempExcels/" + dealerCode);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }

                    return mapping.findForward("message");
                } else {

                    request.setAttribute("result", "FAILURE");
                    request.setAttribute("show_message", xls_validate_result);
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "inventoryAction.do?option=manageReorderLevel");
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "dealer/tempExcels/" + dealerCode);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    return mapping.findForward("message");
                }
            } else {

                request.setAttribute("result", "FAILURE");
                request.setAttribute("show_message", "File could not be uploaded.");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "inventoryAction.do?option=manageReorderLevel");
                return mapping.findForward("message");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("result", "FAILURE");
            request.setAttribute("show_message", "Error Occured. Please contact administrator.");
            request.setAttribute("optionLink", "YES");
            request.setAttribute("optionLinkURL", "inventoryAction.do?option=uploadReOrderLevel");//delete the temporary excel file
            if (xlsfile.exists() && xlsfile.isFile()) {
                xlsfile.delete();
            }
            File src_folder = new File(realPath + "dealer/tempExcels/" + dealerCode);
            if (src_folder.exists()) {
                src_folder.delete();
            }
            return mapping.findForward("message");
        }
    }

    public ActionForward viewReorderLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "viewReorderLevel";
        String dealerCode = "";
        inventoryDAO invDAO = new inventoryDAO();
        inventoryForm invForm = (inventoryForm) form;

        HttpSession session = request.getSession();
        Vector userFunctionalities = (Vector) session.getAttribute("userFun");
        // String dealerCode = (String) session.getAttribute("dealerCode");
        String user_id = (String) session.getAttribute("user_id");
        invForm.setPartnum(invForm.getPartnum() == null ? "" : invForm.getPartnum());
        try {
            if (userFunctionalities.contains("101")) {
                invForm.setDealerCode((String) session.getAttribute("dealerCode"));
            } else if (userFunctionalities.contains("102")) {
                ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('" + user_id + "',dm.loginId)", 3);
                request.setAttribute("dealerList", dealerList);

            } else {
                ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d ", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "", 3);
                request.setAttribute("dealerList", dealerList);
            }
            request.setAttribute("dataList", invDAO.getReorderLevelData(invForm, userFunctionalities));

            if (request.getParameter("etype") != null && request.getParameter("etype").equals("export")) {
                forward = "exportReorderLevel";
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward updateReorderLevel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            HttpSession session = request.getSession();
            inventoryForm invForm = (inventoryForm) form;
            inventoryDAO invdAO = new inventoryDAO();
            String result = invdAO.updateReorderLevel(invForm.getPartno(), invForm, session.getAttribute("dealerCode").toString());
            PrintWriter pw = response.getWriter();
            pw.write(result);
            pw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(null);
    }

    public ActionForward addReorderPartToCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "addReorderPartToCart";
        String dealerCode = "";
        inventoryDAO invDAO = new inventoryDAO();
        inventoryForm invForm = (inventoryForm) form;
        HttpSession session = request.getSession();
        Vector userFunctionalities = (Vector) session.getAttribute("userFun");
        String user_id = (String) session.getAttribute("user_id");
        invForm.setPartnum(invForm.getPartnum() == null ? "" : invForm.getPartnum());
        String priceListCode = (String) session.getAttribute("priceListCode");
        try {
            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                invForm.setDealerCode((String) session.getAttribute("dealerCode"));
            } else {
                request.setAttribute("dealerList", dealerList);
            }
            /*           if (userFunctionalities.contains("101")) {
            invForm.setDealerCode((String) session.getAttribute("dealerCode"));
            } else if (userFunctionalities.contains("102")) {
            ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('" + user_id + "',dm.loginId)", 3);
            request.setAttribute("dealerList", dealerList);
            } else {
            ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d ", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "", 3);
            request.setAttribute("dealerList", dealerList);
            }
             *
             */
            request.setAttribute("dataList", invDAO.getReorderPartToCart(invForm, userFunctionalities, priceListCode));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward setReorderPartToCart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {

            HttpSession session = request.getSession();
            inventoryForm invForm = (inventoryForm) form;
            inventoryDAO invdAO = new inventoryDAO();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (session_id.equals(getSession)) {
                Vector totalParts = null;
                totalParts = (Vector) session.getValue("cartItems");
                if (totalParts == null) {
                    totalParts = new Vector();
                }
                for (int i = 0; i < invForm.getCheckedArr().length; i++) {
                    int j = Integer.parseInt(invForm.getCheckedArr()[i]);
                    if (!totalParts.contains(invForm.getPartNo()[j - 1])) {
                        totalParts.add(invForm.getPartNo()[j - 1]);
                        totalParts.add(invForm.getQuantity()[j - 1]);
                    }
                }
                session.putValue("cartItems", totalParts);
                if (totalParts.size() > 0) {
                    request.setAttribute("result", "SUCCESS");
                    request.setAttribute("show_message", "Parts Successfully Added to Cart.");
                    request.setAttribute("addMoreLink", "YES");
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=addReorderPartToCart");
                } else {
                    request.setAttribute("result", "FAILURE");
                    request.setAttribute("show_message", "Parts Not Added to Cart.");
                    request.setAttribute("addMoreLink", "YES");
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=addReorderPartToCart");

                }
                //result="Parts Added to Cart";
                // PrintWriter pw = response.getWriter();
                // pw.write(result);
                // pw.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("message");
    }

    //  Create New Order(STD/VOR)
    public ActionForward createNewOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "createNewOrder";
        HttpSession httpsession = request.getSession();
        Vector userFunctionalities = (Vector) httpsession.getAttribute("userFun");
        String user_id = (String) httpsession.getAttribute("user_id");
        String dealerCode = (String) httpsession.getAttribute("dealerCode");
        String stocistId = new LoginDAO().getDealerAddress(dealerCode).getStockistId();
        String odType = commonUtilMethods.decodeBase64(request.getParameter("odType"));
        String delvry = request.getParameter("delvry");
        Session session = HibernateUtil.getSessionFactory().openSession();
        inventoryDAO invDAO = new inventoryDAO();
        ActionMessages messages = new ActionMessages();
        try {
            request.setAttribute("deliveryList", MethodUtility.getCommonLabelValueHiber("MSWDeliveryCodeMaster", "deliveryCode", "deliveryDesc", "deliveryDesc", "where e.isActive='Y' "));
            // request.setAttribute("orderTypeList",MethodUtility.getCommonLabelValueHiber("SpOrderTypeMaster", "codeRefNo", "orderType", "orderType", " "));
            if (odType.equalsIgnoreCase("ALL")) {
                request.setAttribute("prevPoList", MethodUtility.getCommonLabelValueHiber("SpOrderMaster", "custPoNo", "custPoNo", "custPoNo", " where e.ordType<>'VOR' and e.status='OPEN' and e.dealerCode='" + dealerCode + "'  "));
                request.setAttribute("orderTypeList", MethodUtility.getOrderTypes(1));
                
            } else {
                request.setAttribute("prevPoList", MethodUtility.getCommonLabelValueHiber("SpOrderMaster", "custPoNo", "custPoNo", "custPoNo", " where e.ordType='VOR' and e.status='OPEN' and e.dealerCode='" + dealerCode + "'  "));
                request.setAttribute("jobcardList",invDAO.getJobCardList(dealerCode));    
            }
            String refNo = new MethodUtility().getNumber(session, "SpOrderMaster", dealerCode, "PO");
            String deliveryaddress = new inventoryDAO().getNewPo(dealerCode);
            request.setAttribute("refNo", refNo);
            request.setAttribute("odType", odType);
            request.setAttribute("delvry", delvry);
            request.setAttribute("deliveryAddress", deliveryaddress);
            if ((dealerCode == null || dealerCode.equals("null") || dealerCode.equals("-") || dealerCode.equals("")) || (stocistId == null || stocistId.equals("null") || stocistId.equals(""))) {
                messages.add("FAILURE", new ActionMessage("label.newOrder.stockistNotAttached"));
                request.setAttribute("result", "FAILURE");
                request.setAttribute("show_message", "");
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=initInvOptions");
                saveErrors(request, messages);
                return mapping.findForward("message");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward addNewOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "failure";
        File dest_folder = null;
        String dest = "";
        String filename = "";
        FormFile xls = null;
        String xls_validate_result = "";
        File xlsfile = null;
        String ext = "";
        String dealerCode = "", deliveryCode = "", deliveryTerms = "";
        String contextPath = (String) request.getContextPath();
        String realPath = getServlet().getServletContext().getRealPath("/");
        boolean isFileUploaded = false;
        ActionMessages messages = new ActionMessages();
        Session hrbSession = HibernateUtil.getSessionFactory().openSession();
        try {
            String partNoInCatPart = "", alternateparts="";
            inventoryForm invForm = (inventoryForm) form;
            inventoryDAO invDAO = new inventoryDAO();
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            dealerCode = (String) session.getAttribute("dealerCode");
            String priceListCode = (String) session.getAttribute("priceListCode");
            deliveryTerms = invDAO.getDeliveryTerms(invForm.getDeliveryCode(), hrbSession);
            invForm.setDealerCode(dealerCode);
            ArrayList<inventoryForm> dataList = new ArrayList();
            Double totalAmm = 0.0;
            
            
            if(invForm.getOrderType().equalsIgnoreCase("vor")) {
            	
            	dataList.addAll(invDAO.partListbyJobCardEstimate(invForm, priceListCode));
            	request.setAttribute("dataList", dataList);
                request.setAttribute("deliveryTerms", deliveryTerms);
            	return mapping.findForward("addPartInNewOrder");
            }
            
            if (invForm.getExcelUploadRadio().equalsIgnoreCase("ByCart")) {
                Vector totalParts = (Vector) session.getValue("cartItems");
                if (totalParts != null) {

                    for (int k = 0; k < totalParts.size(); k++) {
                        String partNo = (String) totalParts.get(k);
                        String qty = (String) totalParts.get(k + 1);
                        k++;
                        alternateparts = invDAO.getAlternateByPartNo(partNo, hrbSession, "");
                        partNoInCatPart = invDAO.getPartNoInCatPart(alternateparts, hrbSession, priceListCode);
                        if (!partNoInCatPart.equals("")) {
                            if (partNoInCatPart.split("@@")[1].equals("N") || partNoInCatPart.split("@@")[2].equals("-")) {
                               
//                                partNo=alternateparts;
                                 if (alternateparts.equals("")) {
                                    alternateparts = "notservicable";
                                 }
                            } 
                        }
                        partNo=alternateparts.equals("")? partNo :alternateparts;
                        String partData = invDAO.getPriceForOrderByPartNo(partNo, hrbSession, priceListCode);
                        inventoryForm inf = new inventoryForm();
                        inf.setPartno(partNo);
                        inf.setQty("" + qty);
                        inf.setPart_desc(partData.split("@@")[0]);
                        inf.setPartTypeStr(partData.split("@@")[1]);
                        inf.setUnitValue(partData.split("@@")[2]);
                        inf.setMoq(partData.split("@@")[3]);
                        inf.setService(partData.split("@@")[4]);
                        inf.setAmountPerPrice("" + (Double.parseDouble(qty) * Double.parseDouble(partData.split("@@")[2])));
                        totalAmm = totalAmm + (Double.parseDouble(qty) * Double.parseDouble(partData.split("@@")[2]));
                        inf.setTotalAmont(totalAmm.toString());
                        invForm.setTotalAmont(totalAmm.toString());

                        dataList.add(inf);
                    }
                }
                forward = "addPartInNewOrder";
            } else {
                dest_folder = new File(realPath + "/dealer/tempExcels/" + dealerCode);
                if (!dest_folder.exists()) {
                    dest_folder.mkdirs();
                }
                dest = realPath + "/dealer/tempExcels/" + dealerCode;
                xls = invForm.getExcelFile();
                filename = xls.getFileName();
                xlsfile = new File(dest + "/" + filename);
                isFileUploaded = invDAO.uploadXls(filename, dest, xls, realPath);
                ext = filename.substring(filename.indexOf("."));
                if (isFileUploaded) {
                    xls_validate_result = invDAO.newOrderExcelValidated(xlsfile);  //reOrderLevelExcelValidated(xlsfile)   isInventoryExcelValidated  saveNewOrder
                    if (xls_validate_result.equals("success1")) {
                        // ArrayList<ArrayList<String>> insertionresult = invDAO.saveNewOrder(xlsfile, dealerCode, contextPath,invForm,user_id);
                        dataList.addAll(invDAO.addIntoList(xlsfile, dealerCode, contextPath, invForm, user_id, priceListCode));
                        forward = "addPartInNewOrder";
                    } else {
                        request.setAttribute("result", "FAILURE");
                        if (xls_validate_result == null || xls_validate_result.equalsIgnoreCase("readingerr")) {
                            messages.add("FAILURE", new ActionMessage("label.newOrderExcel.Failure"));
                        } else {

                            request.setAttribute("show_message", xls_validate_result);
                            messages.add("FAILURE", new ActionMessage(xls_validate_result));
                        }

                        //request.setAttribute("show_message", "");
                        request.setAttribute("addMoreLink", "YES");
                        if (invForm.getOrderType().equalsIgnoreCase("STD")) {
                            request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createNewOrder&odType=" + commonUtilMethods.encodeToBase64("STD"));
                        } else {
                            request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createNewOrder&odType=" + commonUtilMethods.encodeToBase64("VOR"));
                        }
                        saveErrors(request, messages);
                        forward = "message";
                    }
                }
            }
            if (invForm.getPoNoRadio().equals("oldPO")) {
                dataList.addAll(invDAO.partListbyOldPO(invForm, priceListCode));

            }
            request.setAttribute("dataList", dataList);
            request.setAttribute("deliveryTerms", deliveryTerms);
            session.removeValue("cartItems");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (hrbSession != null) {
                    hrbSession.close();
                    hrbSession = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mapping.findForward(forward);
    }

    public ActionForward addNewOrderByCartorExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        try {
            inventoryForm invForm = (inventoryForm) form;
            inventoryDAO invDAO = new inventoryDAO();
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            ActionMessages messages = new ActionMessages();
            String dealerCode = (String) session.getAttribute("dealerCode");
            if (dealerCode == null || dealerCode.equalsIgnoreCase("null")) {
                messages.add("FAILURE", new ActionMessage("label.newOrder.Failure"));
                request.setAttribute("show_message", "");
                request.setAttribute("addMoreLink", "YES");
                if (invForm.getOrderType().equalsIgnoreCase("STD")) {
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createNewOrder&odType=" + commonUtilMethods.encodeToBase64("STD"));
                } else {
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createNewOrder&odType=" + commonUtilMethods.encodeToBase64("VOR"));
                }
            } else {
                invForm.setDealerCode(dealerCode);


                inventoryForm invenForm = invDAO.saveNewOrderByCart(invForm, user_id, dealerCode);
                
                //Mail Triggger - for vor orders
                
				if ("success".equals(invenForm.getResult())
						&& !"saveAsDraft".equalsIgnoreCase(invenForm.getSaveOrDraft())) {
					
					//will do when itl provide mail body

					//MailSender.sendPoReleaseMail();
				}
                
                
                

                if (invenForm.getResult().equals("success")) {
                    request.setAttribute("result", "SUCCESS");
                    if (invenForm.getSaveOrDraft().equalsIgnoreCase("saveAsDraft")) {
                        messages.add("SUCCESS", new ActionMessage("label.newOrder.SuccessDraft"));   //label.newOrder.Success
                    } else {
                        messages.add("SUCCESS", new ActionMessage("label.newOrder.SuccessSave"));
                    }
                    request.setAttribute("show_message", invForm.getRefNo());
                    request.setAttribute("addMoreLink", "YES");
                    if (invForm.getOrderType().equalsIgnoreCase("STD")) {
                        request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createNewOrder&odType=" + commonUtilMethods.encodeToBase64("STD"));
                    } else {
                        request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createNewOrder&odType=" + commonUtilMethods.encodeToBase64("VOR"));
                    }
                } else if (invenForm.getResult().equalsIgnoreCase("poExist")) {
                    messages.add("FAILURE", new ActionMessage("label.newOrder.poExist"));
                    request.setAttribute("show_message", "");
                    request.setAttribute("addMoreLink", "YES");
                    if (invForm.getOrderType().equalsIgnoreCase("STD")) {
                        request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createNewOrder&odType=" + commonUtilMethods.encodeToBase64("STD"));
                    } else {
                        request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createNewOrder&odType=" + commonUtilMethods.encodeToBase64("VOR"));
                    }
                } else if (invenForm.getResult().equalsIgnoreCase("SessionMismatch")) {
                    messages.add("FAILURE", new ActionMessage("label.newOrder.SessionMismatch"));
                    request.setAttribute("show_message", "");
                    request.setAttribute("addMoreLink", "YES");
                    if (invForm.getOrderType().equalsIgnoreCase("STD")) {
                        request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createNewOrder&odType=" + commonUtilMethods.encodeToBase64("STD"));
                    } else {
                        request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createNewOrder&odType=" + commonUtilMethods.encodeToBase64("VOR"));
                    }
                } else {
                    messages.add("FAILURE", new ActionMessage("label.newOrder.Failure"));
                    request.setAttribute("show_message", "");
                    request.setAttribute("addMoreLink", "YES");
                    if (invForm.getOrderType().equalsIgnoreCase("STD")) {
                        request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createNewOrder&odType=" + commonUtilMethods.encodeToBase64("STD"));
                    } else {
                        request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createNewOrder&odType=" + commonUtilMethods.encodeToBase64("VOR"));
                    }
                }
            }
            saveErrors(request, messages);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward("message");
    }

    public ActionForward getChassisDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            inventoryDAO invDAO = new inventoryDAO();
            String vinNo = request.getParameter("vinNo");
            HttpSession session = request.getSession();
            String dealerCode = (String) session.getAttribute("dealerCode");
            String result = invDAO.getChassisDetails(vinNo, dealerCode);
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

    public ActionForward getJobCardNo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            inventoryDAO invDAO = new inventoryDAO();
            String jobCardNo = request.getParameter("jobCardNo");
            HttpSession session = request.getSession();
            String dealerCode = (String) session.getAttribute("dealerCode");
            String result = invDAO.getJobCardDetails(jobCardNo, dealerCode);
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

    public ActionForward getCustPoNo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            inventoryDAO invDAO = new inventoryDAO();
            String custPoNo = request.getParameter("custPoNo");
            HttpSession session = request.getSession();
            //String dealerCode=(String) session.getAttribute("dealerCode");
            String result = invDAO.getCustPoNo(custPoNo);
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

    public ActionForward getNewPo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            inventoryDAO invDAO = new inventoryDAO();
            // String add = request.getParameter("dealerCode");
            HttpSession session = request.getSession();
            String dealerCode = (String) session.getAttribute("dealerCode");
            String result = invDAO.getNewPo(dealerCode);
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

    public ActionForward getPriceByPartNo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Session hrbSession = HibernateUtil.getSessionFactory().openSession();
        try {
            inventoryDAO invDAO = new inventoryDAO();
            String partno = request.getParameter("partno");
            HttpSession session = request.getSession();
            String dealerCode = (String) session.getAttribute("dealerCode");
            String priceListCode = (String) session.getAttribute("priceListCode");
            String result = invDAO.getPriceForOrderByPartNo(partno, hrbSession, priceListCode);
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
            try {
                if (hrbSession != null) {
                    hrbSession.close();
                    hrbSession = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mapping.findForward(null);
    }

// ******** view order starts from here  *********
    public ActionForward viewOrder(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "viewOrder";
        String fromdate = "";
        String todate = "";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            HttpSession session = request.getSession();
            inventoryDAO invDAO = new inventoryDAO();
            inventoryForm invForm = (inventoryForm) form;
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                invForm.setRange("1");
            }
            String user_id = (String) session.getAttribute("user_id");
            invForm.setUserid((String) session.getAttribute("user_id"));
            invForm.setPartnum(invForm.getPartnum() == null ? "" : invForm.getPartnum());
            invForm.setFromdate(invForm.getFromdate() == null ? fromdate : invForm.getFromdate());
            invForm.setTodate(invForm.getTodate() == null ? todate : invForm.getTodate());

//            if (userFunctionalities.contains("103")) {
//                ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d ", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "", 3);
//                request.setAttribute("dealerList", dealerList);
//            } else if (userFunctionalities.contains("102")) {
//                ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('" + user_id + "',dm.loginId)", 3);
//                request.setAttribute("dealerList", dealerList);
//            } else {
//                invForm.setDealerCode((String) session.getAttribute("dealerCode"));
//            }

            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                invForm.setDealerCode((String) session.getAttribute("dealerCode"));
            }

            request.setAttribute("dealerList", dealerList);

            if (invForm.getDealerCode() == null) {
                invForm.setDealerCode("ALL");
            }
            request.setAttribute("viewOrderList", invDAO.getViewOrderList(invForm, userFunctionalities));
            request.setAttribute("orderTypeList", MethodUtility.getCommonLabelValueHiber("SpOrderTypeMaster", "codeRefNo", "orderType", "orderType", " "));
            // request.setAttribute("fromDate", invForm.getFromdate());
            //request.setAttribute("toDate", invForm.getToDate());
            request.setAttribute("range", invForm.getRange());
            if (request.getParameter("eType") != null && request.getParameter("eType").equals("export")) {
                forward = "exportViewOrder";
            } else {
                forward = "viewOrder";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward viewOrderDetail(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "viewOrderDetail";
        String cus_po_no = "";
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            inventoryDAO invDAO = new inventoryDAO();
            inventoryForm invForm = (inventoryForm) form;
            HttpSession session = request.getSession();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            if (request.getParameter("etype") != null && request.getParameter("etype").equals("export")) {
                cus_po_no = request.getParameter("poNo");
            } else {
                cus_po_no = commonUtilMethods.decodeBase64(request.getParameter("poNo"));

            }
            invForm.setDealerCode((String) session.getAttribute("dealerCode"));
            session.setAttribute("userFun", userFunctionalities);
            request.setAttribute("partList", invDAO.getViewOrderDetail(invForm, cus_po_no));
            if (request.getParameter("etype") != null && request.getParameter("etype").equals("export")) {
                forward = "exportViewOrderDetail";
            } else {
                forward = "viewOrderDetail";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward getOrderDesc(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            inventoryDAO invDAO = new inventoryDAO();
            String poNo = request.getParameter("poNo");
            HttpSession session = request.getSession();
            String dealerCode = (String) session.getAttribute("dealerCode");
            String result = invDAO.getOrderDesc(poNo, dealerCode);
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
// ******** view order ends here  *********

    public ActionForward viewOrderInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "viewOrderInvoice";
        String fromdate = "";
        String todate = "";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            HttpSession session = request.getSession();
            inventoryDAO invDAO = new inventoryDAO();
            inventoryForm invForm = (inventoryForm) form;
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String flag = request.getParameter("flag");
            if (flag == null) {
                Calendar cal = Calendar.getInstance();
                todate = dateFormat.format(cal.getTime());
                cal.add(Calendar.DATE, -1);
                fromdate = dateFormat.format(cal.getTime());
                flag = "";
                invForm.setRange("1");
            }
            String user_id = (String) session.getAttribute("user_id");
            invForm.setUserid((String) session.getAttribute("user_id"));
            invForm.setPartnum(invForm.getPartnum() == null ? "" : invForm.getPartnum());
            invForm.setFromdate(invForm.getFromdate() == null ? fromdate : invForm.getFromdate());
            invForm.setTodate(invForm.getTodate() == null ? todate : invForm.getTodate());

            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                invForm.setDealerCode((String) session.getAttribute("dealerCode"));
            } else {
                request.setAttribute("dealerList", dealerList);
            }
            /*            if (userFunctionalities.contains("101")) {
            invForm.setDealerCode((String) session.getAttribute("dealerCode"));
            } else if (userFunctionalities.contains("102")) {
            ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('" + user_id + "',dm.loginId)", 3);
            request.setAttribute("dealerList", dealerList);
            } else {
            ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d ", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "", 3);
            request.setAttribute("dealerList", dealerList);
            }
             *
             */
            if (invForm.getDealerCode() == null) {
                invForm.setDealerCode("ALL");
            }
            request.setAttribute("viewOrderList", invDAO.getViewOrderInvoiceList(invForm, userFunctionalities));
            request.setAttribute("orderTypeList", MethodUtility.getCommonLabelValueHiber("SpOrderTypeMaster", "codeRefNo", "orderType", "orderType", " "));
            request.setAttribute("range", invForm.getRange());

            if (request.getParameter("eType") != null && request.getParameter("eType").equals("Export")) {
                forward = "exportOrderInvoiceList";
            } else if (request.getParameter("eType") != null && request.getParameter("eType").equals("Billing Details")) {
                request.setAttribute("viewOrderList", invDAO.getBillingDetailsExport(invForm, userFunctionalities));
                forward = "exportBillingDetailsList";
            } else {
                forward = "viewOrderInvoice";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward viewOrderInvoiceDetail(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "viewOrderInvoiceDetail";

        try {
            inventoryDAO invDAO = new inventoryDAO();
            inventoryForm invForm = (inventoryForm) form;
            HttpSession session = request.getSession();
            //Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
            String invNo = commonUtilMethods.decodeBase64(request.getParameter("invNo"));
            invForm.setDealerCode(commonUtilMethods.decodeBase64(request.getParameter("dealerCode")));
            request.setAttribute("partList", invDAO.getViewOrderInvoiceDetail(invForm, invNo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    //**** GRN Creation starts here
    public ActionForward createGRN(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "createGRN";
        try {
            inventoryDAO invDAO = new inventoryDAO();
            inventoryForm inForm = (inventoryForm) form;
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("user_id");
            inForm.setDealerCode((String) session.getAttribute("dealerCode"));
            inForm.setUserid(userId);
            invDAO.getPendigInvoiceList(inForm);
            invDAO.getInvoiceData(inForm);
            String token = generateToken(request);
            if (token != null) {
                session.setAttribute(Globals.TRANSACTION_TOKEN_KEY, token);
            }
        } catch (Exception e) {
            System.out.println("exception occurs" + e.toString());
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward addGRN(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "";
        try {
            inventoryDAO invDAO = new inventoryDAO();
            inventoryForm inForm = (inventoryForm) form;
            HttpSession session = request.getSession();
            inventoryForm invForm = null;
            inForm.setUserid((String) session.getAttribute("user_id"));
            inForm.setDealerCode((String) session.getAttribute("dealerCode"));
            ActionMessages messages = new ActionMessages();
            if (isTokenValid(request)) {
                if (inForm.getReceivedQty() != null) {
                    for (int i = 0; i < inForm.getReceivedQty().length; i++) {
                        if (inForm.getReceivedQty()[i].equals("") || Integer.parseInt(inForm.getReceivedQty()[i]) > Integer.parseInt(inForm.getQuantity()[i])) {
                            request.setAttribute("result", "FAILURE");
                            messages.add("FAILURE", new ActionMessage("label.common.recvQtyValidation"));
                            request.setAttribute("show_message", "");
                            request.setAttribute("addMoreLink", "YES");
                            request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createGRN");
                            saveErrors(request, messages);
                            return mapping.findForward("message");
                        }
                    }
                }
                invForm = invDAO.saveGRN(inForm);
                if (invForm.getResult().equals("success")) {
                    request.setAttribute("result", "SUCCESS");
                    messages.add("SUCCESS", new ActionMessage("label.Grn.success"));
                    request.setAttribute("show_message", invForm.getGrnNo());
                    request.setAttribute("addMoreLink", "YES");
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createGRN");
                    resetToken(request);
                } else if (invForm.getResult().equals("PartPriceMismatch")) {
                    request.setAttribute("result", "FAILURE");
                    messages.add("FAILURE", new ActionMessage("label.Grn.partPriceMismatch"));
                    request.setAttribute("show_message", invForm.getInvNo());
                    request.setAttribute("addMoreLink", "YES");
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createGRN");
                    resetToken(request);
                } else {
                    request.setAttribute("result", "FAILURE");
                    messages.add("FAILURE", new ActionMessage("label.Grn.failure"));
                    request.setAttribute("show_message", "");
                    request.setAttribute("addMoreLink", "YES");
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createGRN");
                }
                session.setAttribute("invno", invForm.getInvNo());
            } else {
                request.setAttribute("result", "FAILURE");
                messages.add("FAILURE", new ActionMessage("label.common.duplicate"));
                request.setAttribute("show_message", session.getAttribute("invno"));
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createGRN");
            }
            saveErrors(request, messages);
            forward = "message";
        } catch (Exception e) {
            System.out.println("exception occurs" + e.toString());
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward failPartExcelCreation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "failPartExcelCreation";

        return mapping.findForward(forward);
    }

    public ActionForward getDealAddress(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            inventoryDAO invDAO = new inventoryDAO();
            //String vinNo = request.getParameter("vinNo");
            HttpSession session = request.getSession();
            String dealerCode = (String) session.getAttribute("dealerCode");
            String result = invDAO.getDealAddress(dealerCode);
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

    public ActionForward validatePartNumbers(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            inventoryDAO invDAO = new inventoryDAO();
            String partNos = request.getParameter("partNos");
//            JSONArray responsData = invDAO.validatePartNumber(partNos);
//            responsData.writeJSONString(response.getWriter());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public ActionForward backOrderReport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "backOrderReportListExport";
        inventoryForm invForm = (inventoryForm) form;
        inventoryDAO invDao = new inventoryDAO();
        HttpSession session = request.getSession();
        String dealerCode = null;
        Vector userFunctionalities = (Vector) session.getAttribute("userFun");
        String user_id = (String) session.getAttribute("user_id");
        String hoPage = (String) request.getParameter("hoPage");
        String flag = request.getParameter("flag");
        String fromdate = "";
        String todate = "";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        if (flag == null) {
            Calendar cal = Calendar.getInstance();
            todate = dateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, -1);
            fromdate = dateFormat.format(cal.getTime());
            flag = "";
            invForm.setRange("1");
        }
        invForm.setFromdate(invForm.getFromdate() == null ? fromdate : invForm.getFromdate());
        invForm.setTodate(invForm.getTodate() == null ? todate : invForm.getTodate());
        if (hoPage == null) {
            dealerCode = (String) session.getAttribute("dealerCode");
        } else {
            dealerCode = invForm.getDealerCode() == null ? (String) session.getAttribute("dealerCode") : invForm.getDealerCode();
        }

        if (flag.equals("check")) {
            request.setAttribute("pendingList", invDao.getBackOrderList(dealerCode, invForm,user_id));
        }
        List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
        if (!flag.equals("check")) {
            forward = "backOrderQtyDealerList";
        }
        request.setAttribute("dealerList", dealerList);

        request.setAttribute("range", invForm.getRange());
        request.setAttribute("orderTypeList", MethodUtility.getCommonLabelValueHiber("SpOrderTypeMaster", "codeRefNo", "orderType", "orderType", " "));
        return mapping.findForward(forward);
    }

    public ActionForward initUploadStockAdjustment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "uploadStockAdjustment";
        return mapping.findForward(forward);
    }

    public ActionForward stockAdjustmentExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String show_message = "";
        File dest_folder = null;
        String dest = "";
        String filename = "";
        FormFile xls = null;
        String xls_validate_result = "";
        Connection conn = null;
        File xlsfile = null;
        String ext = "";
        String realPath = getServlet().getServletContext().getRealPath("/");
        String dealerCode = session.getAttribute("dealerCode").toString();
        // String user_id = (String) session.getAttribute("user_id");

        try {
            //for getting excel file and uploading it onto server.
            boolean isFileUploaded = false;
            inventoryDAO invDAO = new inventoryDAO();
            inventoryForm invForm = (inventoryForm) form;
            dest_folder = new File(realPath + "/dealer/tempExcels/" + dealerCode);
            if (!dest_folder.exists()) {
                dest_folder.mkdirs();
            }
            dest = realPath + "dealer/tempExcels/" + dealerCode;
            xls = invForm.getExcelFile();
            filename = xls.getFileName();
            xlsfile = new File(dest + "/" + filename);
            isFileUploaded = invDAO.uploadXls(filename, dest, xls, realPath);
            ext = filename.substring(filename.indexOf("."));
            if (ext.equals(".xlsx")) {
                xlsfile = invDAO.uploadXlsxImage(filename, dest, realPath);
                isFileUploaded = invDAO.uploadXls(filename.substring(0, filename.length() - 1), dest, xls, realPath);
            }
            if (isFileUploaded) {
                xls_validate_result = invDAO.isStockExcelValidated(xlsfile, conn);
                if (xls_validate_result.equals("success")) {
                    Vector insertionresult = invDAO.insertStockAjustmentFromExcel(xlsfile, conn, dealerCode, "Parts");

                    request.setAttribute("insertionresult", insertionresult);
                    Vector message = (Vector) insertionresult.get(0);
                    if (message.get(1).equals("Add More.")) {
                        request.setAttribute("show_message", message.get(0));
                        request.setAttribute("result", "SUCCESS");
                        request.setAttribute("addMoreLink", "YES");
                        request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=initUploadStockAdjustment");
                    } else {
                        request.setAttribute("show_message", message.get(0));
                        request.setAttribute("optionLink", "YES");
                        request.setAttribute("result", "FAILURE");
                        request.setAttribute("optionLinkURL", "inventoryAction.do?option=initUploadStockAdjustment");
                    }
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "/dealer/tempExcels/" + dealerCode);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    session.setAttribute("failPartList", invForm.getFailPartList());
                    return mapping.findForward("message");

                } else if (xls_validate_result.equals("readingerr")) {
                    show_message = "Error occured while reading Excel. It may be due to corrupted Excel file or invalid file path. Attach valid Excel to complete the Process successfully.";
                    request.setAttribute("result", "FAILURE");
                    request.setAttribute("show_message", show_message);
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "inventoryAction.do?option=initUploadStockAdjustment");
                    //delete the temporary excel file
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "dealer/tempExcels/" + dealerCode);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    return mapping.findForward("message");
                } else {
                    request.setAttribute("result", "FAILURE");
                    request.setAttribute("show_message", xls_validate_result);
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "inventoryAction.do?option=initUploadStockAdjustment");
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "dealer/tempExcels/" + dealerCode);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    return mapping.findForward("message");
                }
            } else {
                request.setAttribute("result", "FAILURE");
                request.setAttribute("show_message", "File could not be uploaded.");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "inventoryAction.do?option=initUploadStockAdjustment");
                return mapping.findForward("message");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("result", "FAILURE");
            request.setAttribute("show_message", "Error Occured. Please contact administrator.");
            request.setAttribute("optionLink", "YES");
            request.setAttribute("optionLinkURL", "inventoryAction.do?option=initUploadStockAdjustment");//delete the temporary excel file
            if (xlsfile.exists() && xlsfile.isFile()) {
                xlsfile.delete();
            }
            File src_folder = new File(realPath + "dealer/tempExcels/" + dealerCode);
            if (src_folder.exists()) {
                src_folder.delete();
            }
            return mapping.findForward("message");
        }
    }

    // ******************** Initaialize cancelled pending lines for acceptance *********************** (BY APURV)
    public ActionForward initPendingCancelLinesForAcceptance(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "pendingCancelLinesForAcceptance";
        try {
            HttpSession session = request.getSession();
            inventoryDAO invDAO = new inventoryDAO();
            inventoryForm invForm = (inventoryForm) form;
            request.setAttribute("dataList", invDAO.getPendingCancelLines(invForm, session.getAttribute("dealerCode").toString()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    // ******************** To accept pending lines for acceptance *********************** (BY APURV)
    public ActionForward pendingCancelLinesForAcceptance(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "pendingCancelLinesForAcceptance";
        try {
            HttpSession session = request.getSession();
            inventoryDAO invDAO = new inventoryDAO();

            String user_id = (String) session.getAttribute("user_id");
            int listCount = Integer.parseInt(request.getParameter("listCount").toString());

            LinkedList<String> acceptanceList = new LinkedList<String>();
            for (int i = 1; i < listCount; i++) {
                acceptanceList.add(request.getParameter("checkedArr" + i).toString());
            }

            request.setAttribute("message", invDAO.acceptPendingLines(acceptanceList, user_id));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward getPriceByPartNoNew(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Session hrbSession = HibernateUtil.getSessionFactory().openSession();
        try {
            inventoryDAO invDAO = new inventoryDAO();
            String partno = request.getParameter("partno");
            HttpSession session = request.getSession();
            String partNoInCatPart = "", alternatePart = "", finalpart = "", result = "",tempDesc="";
            String dealerCode = (String) session.getAttribute("dealerCode");
            String priceListCode = (String) session.getAttribute("priceListCode");

            if (request.getParameter("sellingFlag") != null) {
                //Set SellingPercentage Avinash 02-12-2015
                Double sellingPercentage = (Double) session.getAttribute("sellingPercentage");
                tempDesc = invDAO.getSellingPercentageByPartNo(partno, dealerCode, priceListCode, sellingPercentage);
            } else {
                alternatePart = invDAO.getAlternateByPartNo(partno, hrbSession, "");
                partNoInCatPart = invDAO.getPartNoInCatPart(partno, hrbSession, priceListCode);
                partno=alternatePart.equals("")? partno :alternatePart;
                if (!partNoInCatPart.equals("")) {
                    if (partNoInCatPart.split("@@")[1].equals("N") || partNoInCatPart.split("@@")[2].equals("-")) {
                        //alternatePart = invDAO.getAlternatePartNo(partno, hrbSession, "");
                        if (alternatePart.equals("")) {
                            alternatePart = "notservicable";
                        }
                    } else {
                        finalpart = invDAO.getPriceForOrderByPartNo(partno, hrbSession, priceListCode);
                    }
                }
            }
            String sarathiPart =  invDAO.getCheckSarathiPart(partno, hrbSession);
            result = tempDesc+"##"+partNoInCatPart + "##" + alternatePart + "##" + finalpart + "##" + sarathiPart;
             System.out.println(result);
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
            try {
                if (hrbSession != null) {
                    hrbSession.close();
                    hrbSession = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mapping.findForward(null);
    }

    public ActionForward getCheckRestrictedPart(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            Session hrbSession = HibernateUtil.getSessionFactory().openSession();
            inventoryDAO invDAO = new inventoryDAO();
            String partNo = request.getParameter("partNo");
            String result =  invDAO.getCheckRestrictedPart(partNo, hrbSession);
            try {
                PrintWriter pw = response.getWriter();
                pw.write(result);
                pw.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
