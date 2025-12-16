/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

/**
 *
 * @author Ashutosh.Kumar
 */
import HibernateUtil.HibernateUtil;
import beans.inventoryForm;
import com.EAMG.common.commonUtilMethods;
import dao.inventoryEXPDAO;
import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
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
import org.apache.struts.upload.FormFile;
import org.hibernate.Session;
import com.common.MethodUtility;
import dbConnection.dbConnection;
import java.sql.Connection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
//import viewEcat.comEcat.ConnectionHolder;
import viewEcat.orderEcat.PriceDetails;

public class inventoryEXPAction extends DispatchAction {

    private static final String SUCCESS = "success";
    private static final String FAILURE = "failure";
    Connection conn = null;
//  Create New Order(STD/VOR)

    public ActionForward initCreateEXPOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "initCreateEXPOrder";
        return mapping.findForward(forward);
    }

    public ActionForward getOrderType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "inventoryEXPOption";
        return mapping.findForward(forward);
    }

    public ActionForward getdischargePortBydeliveryCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "getPoListByodType";
        request.setAttribute("result", inventoryEXPDAO.getPortOfDischarge(request.getParameter("deliveryCode"), request.getParameter("consigneeCountry")));
        return mapping.findForward(forward);
    }

    public ActionForward getPoListByodType(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "getPoListByodType";
        HttpSession httpsession = request.getSession();
        inventoryForm invForm = (inventoryForm) form;
        Session session = HibernateUtil.getSessionFactory().openSession();
        String dealerCode = (String) httpsession.getAttribute("dealerCode");
        String odType = request.getParameter("odType");
        if (odType.equalsIgnoreCase("STD")) {
            request.setAttribute("prevPoList", MethodUtility.getCommonLabelValueHiber("SPOrderHeaderEXP", "custPoNo", "custPoNo", "custPoNo", " where e.ordType='STD' and e.status='OPEN' and e.dealerCode='" + dealerCode + "'  "));
        } else {
            request.setAttribute("prevPoList", MethodUtility.getCommonLabelValueHiber("SPOrderHeaderEXP", "custPoNo", "custPoNo", "custPoNo", " where e.ordType='VOR' and e.status='OPEN' and e.dealerCode='" + dealerCode + "'  "));
        }
        String refNo = new MethodUtility().getNumber(session, "SPOrderHeaderEXP", dealerCode, "PO");
        request.setAttribute("refNo", refNo);
        request.setAttribute("poListByodType", "poListByodType");
        request.setAttribute("dischargePort", invForm.getDischargePort());
        return mapping.findForward(forward);
    }

    public ActionForward getConsigneeDetailByConsigneeName(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter pw = null;
        try {
            String consigneeDetail = new inventoryEXPDAO().getConsigneeDetailByConsigneeName(request.getParameter("dealerCode"));
            pw = response.getWriter();
            pw.write(consigneeDetail);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(null);
    }

    public ActionForward createNewEXPOrder(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "createNewEXPOrder";
        HttpSession httpsession = request.getSession();
        inventoryEXPDAO invDAO = new inventoryEXPDAO();
        Vector userFunctionalities = (Vector) httpsession.getAttribute("userFun");
        String user_id = (String) httpsession.getAttribute("user_id");
        String dealerName = (String) httpsession.getAttribute("dealerName");
        String dealerCode = (String) httpsession.getAttribute("dealerCode");
        String flagPoNo = (String) request.getParameter("editPo");
        String poNo = null;

        String odType = commonUtilMethods.decodeBase64(request.getParameter("odType"));
        String delvry = request.getParameter("delvry");
        Session session = HibernateUtil.getSessionFactory().openSession();
        ActionMessages messages = new ActionMessages();
        inventoryForm invForm = (inventoryForm) form;
        if (flagPoNo != null && flagPoNo.equals("YES")) {
            poNo = (String) request.getParameter("poNo");
            invForm.setPrevPoNo(poNo);
            invForm.setPoNoRadio("oldPO");
        }
        try {
            if ((userFunctionalities.contains("857") && userFunctionalities.contains("858")) && !(dealerCode == null || dealerCode.equals(""))) {

                String refNo = new MethodUtility().getNumber(session, "SPOrderHeaderEXP", dealerCode, "PO");
                if (odType.equalsIgnoreCase("STD")) {
                    request.setAttribute("prevPoList", MethodUtility.getCommonLabelValueHiber("SPOrderHeaderEXP", "custPoNo", "custPoNo", "custPoNo", " where e.ordType='STD' and e.status='OPEN' and e.dealerCode='" + dealerCode + "'  "));
                } else {
                    request.setAttribute("prevPoList", MethodUtility.getCommonLabelValueHiber("SPOrderHeaderEXP", "custPoNo", "custPoNo", "custPoNo", " where e.ordType='VOR' and e.status='OPEN' and e.dealerCode='" + dealerCode + "'  "));
                }
                if (invForm.getPrevPoNo() != null && !invForm.getPrevPoNo().equals("")) {
                    invDAO.getCustPoNo(invForm);
                    request.setAttribute("refNo", invForm.getPrevPoNo());
                    request.setAttribute("result", inventoryEXPDAO.getPortOfDischarge(invForm.getDeliveryTerms(), invForm.getConsigneeCountry()));
                } else {
                    request.setAttribute("refNo", refNo);
                }

                request.setAttribute("deliveryTermsList", MethodUtility.getCommonLabelValueHiber("MSPDeliveryTermsEXPMaster", "deliveryTermCode", "deliveryTermDesc", "deliveryTermDesc", "where e.isActive='Y' "));
                request.setAttribute("paymentTermsList", MethodUtility.getCommonLabelValueHiber("MSPPaymentTermsEXPMaster", "paymentTermCode", "paymentTermDesc", "paymentTermDesc", "where e.isActive='Y' "));
                request.setAttribute("incoTermsList", MethodUtility.getCommonLabelValueHiber("MSPIncoTermsEXPMaster", "incoTermCode", "incoTermDesc", "incoTermDesc", "where e.isActive='Y' "));

                request.setAttribute("buyerDetail", invDAO.getBuyerDetails(dealerCode));
               // request.setAttribute("consigneeList", invDAO.getConsigneeDetails());
                request.setAttribute("odType", odType);
                request.setAttribute("delvry", delvry);
//                request.setAttribute("buyerName", dealerName);
//                request.setAttribute("buyerCountry", user_id);
                request.setAttribute("poNoRadio", request.getParameter("poNoRadio"));

            } else {
                messages.add("FAILURE", new ActionMessage("label.newOrder.dealerNotAttached"));
                request.setAttribute("result", "FAILURE");
                request.setAttribute("show_message", "");
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "inventoryEXPAction.do?option=initCreateEXPOrder");
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
     //   File dest_folderDoc = null;
        String dest = "";
        String filename = "";// docFileName = null;
        FormFile xls = null;// docFile = null;
        String xls_validate_result = "";
        File xlsfile = null;
      //  String ext = "";
        String dealerCode = "";
        String contextPath = (String) request.getContextPath();
        String realPath = getServlet().getServletContext().getRealPath("/");
        boolean isFileUploaded = false;
        ActionMessages messages = new ActionMessages();
        Session hrbSession = HibernateUtil.getSessionFactory().openSession();
        try {
            conn = new dbConnection().getConnection();
            inventoryForm invForm = (inventoryForm) form;
            inventoryEXPDAO invDAO = new inventoryEXPDAO();
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            dealerCode = (String) session.getAttribute("dealerCode");
            String priceListCode = (String) session.getAttribute("priceListCode");
            //ConnectionHolder holder = (ConnectionHolder) request.getSession().getAttribute("servletapp.connection");
            //Connection conn = holder.getConnection();
            PriceDetails pd = new PriceDetails(conn);
            invForm.setPriceListCode(priceListCode);
            invForm.setCurrency(pd.getCurrency(priceListCode));
            invForm.setDealerCode(dealerCode);
            ArrayList<inventoryForm> dataList = new ArrayList();
            Double totalAmm = 0.0;
            if (invForm.getExcelUploadRadio().equalsIgnoreCase("ByCart")) {
                Vector totalParts = (Vector) session.getValue("cartItems");
                if (totalParts != null) {

                    for (int k = 0; k < totalParts.size(); k++) {
                        String partNo = (String) totalParts.get(k);
                        String qty = (String) totalParts.get(k + 1);
                        k++;
                        String partData = invDAO.getPriceForOrderByPartNo(partNo, hrbSession, priceListCode);
                        inventoryForm inf = new inventoryForm();
                        inf.setPartno(partNo);
                        inf.setQty("" + qty);
                        inf.setPart_desc(partData.split("@@")[0]);
                        inf.setPartTypeStr(partData.split("@@")[1]);
                        inf.setUnitValue(partData.split("@@")[2]);
                        inf.setMoq("1");//partData.split("@@")[3]
                        inf.setService(partData.split("@@")[4]);
                        inf.setAmountPerPrice("" + (Double.parseDouble(qty) * Double.parseDouble(partData.split("@@")[2])));
                        totalAmm = totalAmm + (Double.parseDouble(qty) * Double.parseDouble(partData.split("@@")[2]));
                        inf.setTotalAmont(totalAmm.toString());
                        invForm.setTotalAmont(totalAmm.toString());

                        dataList.add(inf);
                    }
                }
                forward = "addPartInNewEXPOrder";
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
                if (isFileUploaded) {
                    xls_validate_result = invDAO.newOrderExcelValidated(xlsfile);  //reOrderLevelExcelValidated(xlsfile)   isInventoryExcelValidated  saveNewOrder
                    if (xls_validate_result.equals("success1")) {
                        dataList.addAll(invDAO.addIntoList(xlsfile, dealerCode, contextPath, invForm, user_id, priceListCode));
                        forward = "addPartInNewEXPOrder";
                    } else {
                        request.setAttribute("result", "FAILURE");
                        if (xls_validate_result == null || xls_validate_result.equalsIgnoreCase("readingerr")) {
                            messages.add("FAILURE", new ActionMessage("label.newOrderExcel.Failure"));
                        } else {

                            request.setAttribute("show_message", xls_validate_result);
                        }

                        request.setAttribute("addMoreLink", "YES");
                        if (invForm.getOrderType().equalsIgnoreCase("STD")) {
                            request.setAttribute("addMoreLinkURL", "inventoryEXPAction.do?option=createNewEXPOrder&odType=" + commonUtilMethods.encodeToBase64("STD"));
                        } else {
                            request.setAttribute("addMoreLinkURL", "inventoryEXPAction.do?option=createNewEXPOrder&odType=" + commonUtilMethods.encodeToBase64("VOR"));
                        }
                        saveErrors(request, messages);
                        forward = "message";
                    }
                }
            }
            if (invForm.getPoNoRadio().equals("oldPO")) {
                dataList.addAll(invDAO.partListbyOldPO(invForm, priceListCode));
                invDAO.documentDeatils(invForm.getPrevPoNo(), hrbSession);

            }
//            dest_folderDoc = new File(realPath + "/dealer/tempExcels/" + dealerCode);
//            if (!dest_folderDoc.exists()) {
//                dest_folderDoc.mkdirs();
//            }
//            String destDoc = realPath + "/dealer/tempExcels/" + dealerCode;
//            docFile = invForm.getExcelDocumentFile();
//            docFileName = docFile.getFileName();
//            if (!docFileName.equalsIgnoreCase("")) {
//
//                xlsfile = new File(dest + "/" + docFileName);
//                isFileUploaded = invDAO.uploadXls(docFileName, destDoc, docFile, realPath);
//                ext = docFileName.substring(docFileName.indexOf("."));
//                if (isFileUploaded) {
//                    request.setAttribute("documentName", docFileName);
//                    request.setAttribute("isDocumentUpload", 1);
//                    invForm.setDocumentName(docFileName);
//                }else{
//                   request.setAttribute("isDocumentUpload", 0);
//                }
//            }
            
            request.setAttribute("dataList", dataList);
            request.setAttribute("deliveryTerms", invDAO.deliveryTermsList(invForm.getDeliveryCode(), hrbSession));
            request.setAttribute("paymentTerms", invDAO.paymentTermsList(invForm.getPaymentTerms(), hrbSession));
            request.setAttribute("incoTerms", invDAO.incoTermsList(invForm.getIncoTerms(), hrbSession));
            request.setAttribute("buyerDetail", invDAO.getBuyerDetails(dealerCode));
            request.setAttribute("consigneeList", invDAO.getConsigneeDetails());
            //request.setAttribute("priceCode", pd.getCurrency(priceCode));
            session.removeValue("cartItems");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (hrbSession != null) {
                    hrbSession.close();
                    hrbSession = null;
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return mapping.findForward(forward);
    }

    public ActionForward addNewOrderByCartorExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {


        inventoryForm invForm = (inventoryForm) form;
        inventoryEXPDAO invDAO = new inventoryEXPDAO();
        HttpSession session = request.getSession();
        String user_id = (String) session.getAttribute("user_id");

        String dealerCode = (String) session.getAttribute("dealerCode");
        invForm.setDealerCode(dealerCode);

        String realPath = getServlet().getServletContext().getRealPath("/");

        String dest = "", destDoc = "", docFileName = "";
        boolean isFileUploaded = false;
        File dest_folderDoc = null, xlsfile = null;
        FormFile docFile = null;

        if (invForm.getExcelDocumentFile() != null) {
            dest_folderDoc = new File(realPath + "/DOCUMENTS/ORDER/" + invForm.getRefNo().toString().replace("/", "_"));
            if (!dest_folderDoc.exists()) {
                dest_folderDoc.mkdirs();
            }
            destDoc = realPath + "/DOCUMENTS/ORDER/" + invForm.getRefNo().toString().replace("/", "_");
            docFile = invForm.getExcelDocumentFile();
            docFileName = docFile.getFileName();

            if (!docFileName.equalsIgnoreCase("")) {
                isFileUploaded = invDAO.uploadXls(docFileName, destDoc, docFile, realPath);
                if (isFileUploaded) {
                    invForm.setDocumentName(docFileName);
                    invForm.setIsDocumentUpload("1");
                } else {
                    invForm.setDocumentName(null);
                    invForm.setIsDocumentUpload("0");
                }
            } else if (invForm.getDocumentName() != null) {
                invForm.setDocumentName(invForm.getDocumentName());
                invForm.setIsDocumentUpload("1");
            } else {
                invForm.setDocumentName(null);
                invForm.setIsDocumentUpload("0");
            }

        }
        invDAO.saveNewOrderByCart(invForm, user_id);
        ActionMessages messages = new ActionMessages();


        try {
            if (invForm.getResult().equals("success")) {
                request.setAttribute("result", "SUCCESS");
                if (invForm.getSaveOrDraft().equalsIgnoreCase("saveAsDraft")) {
                    messages.add("SUCCESS", new ActionMessage("label.newOrder.SuccessDraft"));   //label.newOrder.Success
                } else {
                    messages.add("SUCCESS", new ActionMessage("label.newOrder.SuccessSaveEXP"));
                }
                request.setAttribute("show_message", invForm.getRefNo());
                request.setAttribute("addMoreLink", "YES");
                if (invForm.getOrderType().equalsIgnoreCase("STD")) {
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=initInvOptions");
                } else {
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=initInvOptions");
                }
                
            } else if (invForm.getResult().equalsIgnoreCase("poExist")) {
                request.setAttribute("result", "FAILURE");
                messages.add("FAILURE", new ActionMessage("label.newOrder.poExist"));
//                request.setAttribute("show_message", new ActionMessage("label.newOrder.poExist"));
                request.setAttribute("show_message", "PO Number already exist.");
                request.setAttribute("addMoreLink", "YES");
                if (invForm.getOrderType().equalsIgnoreCase("STD")) {
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=initInvOptions");
                } else {
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=initInvOptions");
                }
                
            } else {
                messages.add("FAILURE", new ActionMessage("label.newOrder.Failure"));
                request.setAttribute("show_message", "Error Occured While Creating New Order");
                request.setAttribute("result", "FAILURE");
                request.setAttribute("addMoreLink", "YES");
                if (invForm.getOrderType().equalsIgnoreCase("STD")) {
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=initInvOptions");
                } else {
                    request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=initInvOptions");
                }
            }


            saveErrors(request, messages);
        } catch (Exception e) {
            e.printStackTrace();
            messages.add("FAILURE", new ActionMessage("label.spare.failure"));
//           request.setAttribute("show_message", new ActionMessage("label.newOrder.Failure"));
           request.setAttribute("show_message", "Error Occured While Creating New Order");
            request.setAttribute("addMoreLink", "YES");
            request.setAttribute("result", "FAILURE");
            if (invForm.getOrderType().equalsIgnoreCase("STD")) {
                request.setAttribute("addMoreLinkURL", "inventoryEXPAction.do?option=createNewEXPOrder&odType=" + commonUtilMethods.encodeToBase64("STD"));
            } else {
                request.setAttribute("addMoreLinkURL", "inventoryEXPAction.do?option=createNewEXPOrder&odType=" + commonUtilMethods.encodeToBase64("VOR"));
            }
        }
        return mapping.findForward("message");
    }

    public ActionForward getPriceByPartNo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Session hrbSession = HibernateUtil.getSessionFactory().openSession();
        try {
            inventoryEXPDAO invDAO = new inventoryEXPDAO();
            String partno = request.getParameter("partno");
            HttpSession session = request.getSession();
            String partNoInCatPart="",paintedparts="",alternatePart="",finalpart="",result="";
            String dealerCode = (String) session.getAttribute("dealerCode");
            String priceListCode = (String) session.getAttribute("priceListCode");
            paintedparts=invDAO.getPaintedPartNos(partno, hrbSession);
            if(paintedparts.equals("")){
                partNoInCatPart =invDAO.getPartNoInCatPart(partno, hrbSession,priceListCode);
                if(!partNoInCatPart.equals("")){
                if(partNoInCatPart.split("%%")[1].equals("N")  || partNoInCatPart.split("%%")[2].equals("-")){
                alternatePart =invDAO.getAlternatePartNo(partno, hrbSession,"");
                if(alternatePart.equals("")){
                    alternatePart="notservicable";
                        }
                }else{
                        finalpart = invDAO.getPriceForOrderByPartNo(partno, hrbSession, priceListCode);
                    }
                }
            }
            result=paintedparts+"##"+partNoInCatPart+"##"+alternatePart+"##"+finalpart;
          //  System.out.println(result);
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

    public ActionForward checkPartNo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        Session hrbSession = HibernateUtil.getSessionFactory().openSession();
        try {
            inventoryEXPDAO invDAO = new inventoryEXPDAO();
            String partno = request.getParameter("partno");
            HttpSession session = request.getSession();
            String partNoInCatPart = "", paintedparts = "", alternatePart = "", finalpart = "", result = "";
            String dealerCode = (String) session.getAttribute("dealerCode");
            String priceListCode = (String) session.getAttribute("priceListCode");
            finalpart = invDAO.getPartNo(partno, hrbSession);
            if(finalpart.length()>0){
            finalpart = finalpart+"##" + invDAO.getPriceForOrderByPartNo(partno, hrbSession, priceListCode);
            }
            try {
                PrintWriter pw = response.getWriter();
                pw.write(finalpart);
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

    public ActionForward getCustPoNo(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            inventoryEXPDAO invDAO = new inventoryEXPDAO();
            String custPoNo = request.getParameter("custPoNo");
            HttpSession session = request.getSession();
            //String result = invDAO.getCustPoNo(custPoNo);
            try {
                PrintWriter pw = response.getWriter();
                //pw.write(result);
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
            inventoryEXPDAO invDAO = new inventoryEXPDAO();
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

    public ActionForward viewEXPOrder(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "viewOrderEXP";
        String fromdate = "";
        String todate = "";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            HttpSession session = request.getSession();
            inventoryEXPDAO invDAO = new inventoryEXPDAO();
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
            invForm.setUserid(user_id);
            invForm.setPartnum(invForm.getPartnum() == null ? "" : invForm.getPartnum());
            invForm.setFromdate(invForm.getFromdate() == null ? fromdate : invForm.getFromdate());
            invForm.setTodate(invForm.getTodate() == null ? todate : invForm.getTodate());

            /*   if (userFunctionalities.contains("103")) {
            ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d ", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "where d.dealerCategory='EXPORT'", 3);
            request.setAttribute("dealerList", dealerList);
            } else if (userFunctionalities.contains("102")) {
            ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('" + user_id + "',dm.loginId) and d.dealerCategory='EXPORT'", 3);
            request.setAttribute("dealerList", dealerList);
            } else {
            invForm.setDealerCode((String) session.getAttribute("dealerCode"));
            }     */

            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                invForm.setDealerCode((String) session.getAttribute("dealerCode"));
            } else {
                request.setAttribute("dealerList", dealerList);
            }

            if (invForm.getDealerCode() == null) {
                invForm.setDealerCode("ALL");
            }
            request.setAttribute("viewOrderList", invDAO.getViewOrderList(invForm, userFunctionalities));
            request.setAttribute("orderTypeList", MethodUtility.getCommonLabelValueHiber("SpOrderTypeMaster", "codeRefNo", "orderType", "orderType", " "));
            // request.setAttribute("fromDate", invForm.getFromdate());
            //request.setAttribute("toDate", invForm.getToDate());
            request.setAttribute("range", invForm.getRange());
            if (request.getParameter("eType") != null && request.getParameter("eType").equals("export")) {
                forward = "exportViewOrderEXP";
            } else {
                forward = "viewOrderEXP";
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward viewOrderDetail(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "";
        String cus_po_no = "";
        //SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        //ConnectionHolder holder = (ConnectionHolder) request.getSession().getAttribute("servletapp.connection");
        //Connection conn = holder.getConnection();
        try {
            conn = new dbConnection().getConnection();
            inventoryEXPDAO invDAO = new inventoryEXPDAO();
            PriceDetails pd = new PriceDetails(conn);
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
           // request.setAttribute("priceListCode", pd.getCurrency(pd.getPriceListCode(user_id)));
            request.setAttribute("partList", invDAO.getViewOrderDetail(invForm, cus_po_no));
            request.setAttribute("piDetail", invDAO.getViewPiDetail(invForm, cus_po_no));
            if (request.getParameter("etype") != null && request.getParameter("etype").equals("export")) {
                forward = "exportViewOrderEXPDetail";
            } else {
                forward = "viewOrderDetailEXP";
            }
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

    public ActionForward viewEXPOrderInvoice(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "viewEXPOrderInvoice";
        String fromdate = "";
        String todate = "";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            HttpSession session = request.getSession();
            inventoryEXPDAO invDAO = new inventoryEXPDAO();
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

            /*  if (userFunctionalities.contains("101")) {
            invForm.setDealerCode((String) session.getAttribute("dealerCode"));
            } else if (userFunctionalities.contains("102")) {
            ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and d.dealerCategory='EXPORT' and dm.loginId = isnull('" + user_id + "',dm.loginId)", 3);
            request.setAttribute("dealerList", dealerList);
            } else {
            ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d ", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "where d.dealerCategory='EXPORT'", 3);
            request.setAttribute("dealerList", dealerList);
            } */

            if (invForm.getDealerCode() == null) {
                invForm.setDealerCode("ALL");
            }
            request.setAttribute("viewOrderList", invDAO.getViewOrderInvoiceList(invForm, userFunctionalities));
            request.setAttribute("orderTypeList", MethodUtility.getCommonLabelValueHiber("SpOrderTypeMaster", "codeRefNo", "orderType", "orderType", " "));
            request.setAttribute("range", invForm.getRange());

            if (request.getParameter("eType") != null && request.getParameter("eType").equals("Export")) {
                forward = "exportEXPOrderInvoiceList";
            }
            /*else if (request.getParameter("eType") != null && request.getParameter("eType").equals("Billing Details")) {
                request.setAttribute("viewOrderList", invDAO.getBillingDetailsExport(invForm, userFunctionalities));
                forward = "exportEXPBillingDetailsList";
            }*/ else {
                forward = "viewEXPOrderInvoice";
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward viewOrderInvoiceDetail(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "viewEXPOrderInvoiceDetail";

        try {
            inventoryEXPDAO invDAO = new inventoryEXPDAO();
            inventoryForm invForm = (inventoryForm) form;
            String invNo = commonUtilMethods.decodeBase64(request.getParameter("invNo"));
            invForm.setDealerCode(commonUtilMethods.decodeBase64(request.getParameter("dealerCode")));
            request.setAttribute("partList", invDAO.getViewOrderInvoiceDetail(invForm, invNo));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward downloadPIOrderSAP(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response) throws Exception {

        String forward = "downloadPIOrderSAP";
        inventoryForm invForm = (inventoryForm) form;
        inventoryEXPDAO invDAO = new inventoryEXPDAO();
        request.setAttribute("PiOrderForSAPList", invDAO.getPiOrderForSAP());
        return mapping.findForward(forward);
    }

     public ActionForward initUploadSAPAck(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "initUploadSAPAck";
        return mapping.findForward(forward);
    }

    public ActionForward uploadSAPAckExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String show_message = "";
        File dest_folder = null;
        String dest = "";
        String filename = "";
        FormFile xls = null;
        ArrayList xlsResultList = null;
        Connection conn = null;
        File xlsfile = null;
        String ext = "";
        String realPath = getServlet().getServletContext().getRealPath("/");
        String user_id = session.getAttribute("user_id").toString();

        try {
            //for getting excel file and uploading it onto server.
            boolean isFileUploaded = false;
            inventoryEXPDAO invDAO = new inventoryEXPDAO();
            inventoryForm invForm = (inventoryForm) form;
            dest_folder = new File(realPath + "/dealer/tempExcels/" + user_id);
            if (!dest_folder.exists()) {
                dest_folder.mkdirs();
            }
            dest = realPath + "dealer/tempExcels/" + user_id;
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
                xlsResultList = invDAO.isSAPExcelValidated(xlsfile, conn);

                if (xlsResultList != null && "success".equals((String) xlsResultList.get(0))) {
                    ArrayList insertionresult = new ArrayList();
                    if (xlsResultList.get(1) != null) {
                        insertionresult = invDAO.updateSAPFromExcel((ArrayList) xlsResultList.get(1), conn, user_id);
                    }
                    request.setAttribute("insertionresult", insertionresult);

                    if ("Add More.".equals(insertionresult.get(1))) {
                        request.setAttribute("show_message", insertionresult.get(0));
                        request.setAttribute("result", "SUCCESS");
                        request.setAttribute("addMoreLink", "YES");
                        request.setAttribute("addMoreLinkURL", "inventoryEXPAction.do?option=initUploadSAPAck");
                    } else {
                        if (insertionresult.get(0) != null) {
                            request.setAttribute("show_message", insertionresult.get(0));
                        } else {
                            request.setAttribute("show_message", "Error Occured.");
                        }
                        request.setAttribute("optionLink", "YES");
                        request.setAttribute("result", "FAILURE");
                        request.setAttribute("optionLinkURL", "inventoryEXPAction.do?option=initUploadSAPAck");
                    }
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "/dealer/tempExcels/" + user_id);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    session.setAttribute("failPartList", invForm.getFailPartList());
                    return mapping.findForward("message");

                } else if (xlsResultList != null && "readingerr".equals((String) xlsResultList.get(0))) {

                    show_message = "Error occured while reading Excel. It may be due to corrupted Excel file or invalid file path. Attach valid Excel to complete the Process successfully.";
                    request.setAttribute("result", "FAILURE");
                    request.setAttribute("show_message", show_message);
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "inventoryEXPAction.do?option=initUploadSAPAck");
                    //delete the temporary excel file
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "dealer/tempExcels/" + user_id);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    return mapping.findForward("message");
                } else {
                    request.setAttribute("result", "FAILURE");

                    if (xlsResultList != null) {
                        request.setAttribute("show_message", (String) xlsResultList.get(0));
                    } else {
                        request.setAttribute("show_message", "Error Occured");
                    }
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "inventoryEXPAction.do?option=initUploadSAPAck");
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "dealer/tempExcels/" + user_id);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    return mapping.findForward("message");
                }
            } else {
                request.setAttribute("result", "FAILURE");
                request.setAttribute("show_message", "File could not be updated.");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "inventoryEXPAction.do?option=initUploadSAPAck");
                return mapping.findForward("message");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("result", "FAILURE");
            request.setAttribute("show_message", "Error Occured. Please contact administrator.");
            request.setAttribute("optionLink", "YES");
            request.setAttribute("optionLinkURL", "inventoryEXPAction.do?option=initUploadSAPAck");//delete the temporary excel file
            if (xlsfile.exists() && xlsfile.isFile()) {
                xlsfile.delete();
            }
            File src_folder = new File(realPath + "dealer/tempExcels/" + user_id);
            if (src_folder.exists()) {
                src_folder.delete();
            }
            return mapping.findForward("message");
        }
    }

    public ActionForward initUploadSAPCommercial(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "initUploadSAPCommercial";
        return mapping.findForward(forward);
    }

    public ActionForward uploadSAPCommercialExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String show_message = "";
        File dest_folder = null;
        String dest = "";
        String filename = "";
        FormFile xls = null;
        ArrayList xlsResultList = null;
        Connection conn = null;
        File xlsfile = null;
        String ext = "";
        String realPath = getServlet().getServletContext().getRealPath("/");
        String user_id = session.getAttribute("user_id").toString();

        try {
            //for getting excel file and uploading it onto server.
            boolean isFileUploaded = false;
            inventoryEXPDAO invDAO = new inventoryEXPDAO();
            inventoryForm invForm = (inventoryForm) form;
            dest_folder = new File(realPath + "/dealer/tempExcels/" + user_id);
            if (!dest_folder.exists()) {
                dest_folder.mkdirs();
            }
            dest = realPath + "dealer/tempExcels/" + user_id;
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
                xlsResultList = invDAO.isSAPCommExcelValidated(xlsfile, conn);

                if (xlsResultList!=null && "success".equals((String)xlsResultList.get(0))) {
                    ArrayList insertionresult = new ArrayList();
                    if(xlsResultList.get(1)!=null){
                        insertionresult = invDAO.updateSAPCommFromExcel((ArrayList) xlsResultList.get(1), conn, user_id);
                    }
                    request.setAttribute("insertionresult", insertionresult);

                    if ("Add More.".equals(insertionresult.get(1))) {
                        request.setAttribute("show_message", insertionresult.get(0));
                        request.setAttribute("result", "SUCCESS");
                        request.setAttribute("addMoreLink", "YES");
                        request.setAttribute("addMoreLinkURL", "inventoryEXPAction.do?option=initUploadSAPCommercial");
                    } else {
                        if(insertionresult.get(0)!=null){
                            request.setAttribute("show_message", insertionresult.get(0));
                        }else{
                            request.setAttribute("show_message", "Error Occured.");
                        }
                        request.setAttribute("optionLink", "YES");
                        request.setAttribute("result", "FAILURE");
                        request.setAttribute("optionLinkURL", "inventoryEXPAction.do?option=initUploadSAPCommercial");
                    }
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "/dealer/tempExcels/" + user_id);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    session.setAttribute("failPartList", invForm.getFailPartList());
                    return mapping.findForward("message");

                } else if (xlsResultList!=null && "readingerr".equals((String)xlsResultList.get(0))) {

                    show_message = "Error occured while reading Excel. It may be due to corrupted Excel file or invalid file path. Attach valid Excel to complete the Process successfully.";
                    request.setAttribute("result", "FAILURE");
                    request.setAttribute("show_message", show_message);
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "inventoryEXPAction.do?option=initUploadSAPCommercial");
                    //delete the temporary excel file
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "dealer/tempExcels/" + user_id);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    return mapping.findForward("message");
                } else {
                    request.setAttribute("result", "FAILURE");

                    if(xlsResultList!=null){
                        request.setAttribute("show_message", (String)xlsResultList.get(0));
                    }
                    else{
                         request.setAttribute("show_message","Error Occured");
                    }
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "inventoryEXPAction.do?option=initUploadSAPCommercial");
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "dealer/tempExcels/" + user_id);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    return mapping.findForward("message");
                }
            } else {
                request.setAttribute("result", "FAILURE");
                request.setAttribute("show_message", "File could not be updated.");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "inventoryEXPAction.do?option=initUploadSAPCommercial");
                return mapping.findForward("message");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("result", "FAILURE");
            request.setAttribute("show_message", "Error Occured. Please contact administrator.");
            request.setAttribute("optionLink", "YES");
            request.setAttribute("optionLinkURL", "inventoryEXPAction.do?option=initUploadSAPCommercial");//delete the temporary excel file
            if (xlsfile.exists() && xlsfile.isFile()) {
                xlsfile.delete();
            }
            File src_folder = new File(realPath + "dealer/tempExcels/" + user_id);
            if (src_folder.exists()) {
                src_folder.delete();
            }
            return mapping.findForward("message");
        }
    }

    public ActionForward initUploadCancelledExp(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "initUploadCancelledExp";
        return mapping.findForward(forward);
    }

    public ActionForward uploadCancelledExpExcel(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        HttpSession session = request.getSession();
        String show_message = "";
        File dest_folder = null;
        String dest = "";
        String filename = "";
        FormFile xls = null;
        ArrayList xlsResultList = null;
        Connection conn = null;
        File xlsfile = null;
        String ext = "";
        String realPath = getServlet().getServletContext().getRealPath("/");
        String user_id = session.getAttribute("user_id").toString();

        try {
            //for getting excel file and uploading it onto server.
            boolean isFileUploaded = false;
            inventoryEXPDAO invDAO = new inventoryEXPDAO();
            inventoryForm invForm = (inventoryForm) form;
            dest_folder = new File(realPath + "/dealer/tempExcels/" + user_id);
            if (!dest_folder.exists()) {
                dest_folder.mkdirs();
            }
            dest = realPath + "dealer/tempExcels/" + user_id;
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
                xlsResultList = invDAO.isCancelledOrderExcelValidated(xlsfile, conn);

                if (xlsResultList!=null && "success".equals((String)xlsResultList.get(0))) {
                    ArrayList insertionresult = new ArrayList();
                    if(xlsResultList.get(1)!=null){
                        insertionresult = invDAO.updateCancelledOrderFromExcel((ArrayList) xlsResultList.get(1), conn, user_id);
                    }
                    request.setAttribute("insertionresult", insertionresult);

                    if ("Add More.".equals(insertionresult.get(1))) {
                        request.setAttribute("show_message", insertionresult.get(0));
                        request.setAttribute("result", "SUCCESS");
                        request.setAttribute("addMoreLink", "YES");
                        request.setAttribute("addMoreLinkURL", "inventoryEXPAction.do?option=initUploadCancelledExp");
                    } else {
                        if(insertionresult.get(0)!=null){
                            request.setAttribute("show_message", insertionresult.get(0));
                        }else{
                            request.setAttribute("show_message", "Error Occured.");
                        }
                        request.setAttribute("optionLink", "YES");
                        request.setAttribute("result", "FAILURE");
                        request.setAttribute("optionLinkURL", "inventoryEXPAction.do?option=initUploadCancelledExp");
                    }
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "/dealer/tempExcels/" + user_id);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    session.setAttribute("failPartList", invForm.getFailPartList());
                    return mapping.findForward("message");

                } else if (xlsResultList!=null && "readingerr".equals((String)xlsResultList.get(0))) {

                    show_message = "Error occured while reading Excel. It may be due to corrupted Excel file or invalid file path. Attach valid Excel to complete the Process successfully.";
                    request.setAttribute("result", "FAILURE");
                    request.setAttribute("show_message", show_message);
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "inventoryEXPAction.do?option=initUploadCancelledExp");
                    //delete the temporary excel file
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "dealer/tempExcels/" + user_id);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    return mapping.findForward("message");
                } else {
                    request.setAttribute("result", "FAILURE");

                    if(xlsResultList!=null){
                        request.setAttribute("show_message", (String)xlsResultList.get(0));
                    }
                    else{
                         request.setAttribute("show_message","Error Occured");
                    }
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "inventoryEXPAction.do?option=initUploadCancelledExp");
                    if (xlsfile.exists() && xlsfile.isFile()) {
                        xlsfile.delete();
                    }
                    File src_folder = new File(realPath + "dealer/tempExcels/" + user_id);
                    if (src_folder.exists()) {
                        src_folder.delete();
                    }
                    return mapping.findForward("message");
                }
            } else {
                request.setAttribute("result", "FAILURE");
                request.setAttribute("show_message", "File could not be updated.");
                request.setAttribute("optionLink", "YES");
                request.setAttribute("optionLinkURL", "inventoryEXPAction.do?option=initUploadCancelledExp");
                return mapping.findForward("message");
            }

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("result", "FAILURE");
            request.setAttribute("show_message", "Error Occured. Please contact administrator.");
            request.setAttribute("optionLink", "YES");
            request.setAttribute("optionLinkURL", "inventoryEXPAction.do?option=initUploadCancelledExp");//delete the temporary excel file
            if (xlsfile.exists() && xlsfile.isFile()) {
                xlsfile.delete();
            }
            File src_folder = new File(realPath + "dealer/tempExcels/" + user_id);
            if (src_folder.exists()) {
                src_folder.delete();
            }
            return mapping.findForward("message");
        }
    }
 //**** GRN Creation starts here
    public ActionForward createGRNEXP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "createGRNEXP";
        try {
            inventoryEXPDAO invDAO = new inventoryEXPDAO();
            inventoryForm inForm = (inventoryForm) form;
            HttpSession session = request.getSession();
            String userId = (String) session.getAttribute("user_id");
            inForm.setDealerCode((String) session.getAttribute("dealerCode"));
            inForm.setUserid(userId);
            invDAO.getPendigInvoiceList(inForm);
            invDAO.getInvoiceData(inForm);
        } catch (Exception e) {
            System.out.println("exception occurs" + e.toString());
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward addGRNEXP(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "";
        try {
            inventoryEXPDAO invDAO = new inventoryEXPDAO();
            inventoryForm inForm = (inventoryForm) form;
            HttpSession session = request.getSession();
            inForm.setUserid((String) session.getAttribute("user_id"));
            inForm.setDealerCode((String) session.getAttribute("dealerCode"));
            inventoryForm invForm = invDAO.saveGRNEXP(inForm);
            ActionMessages messages = new ActionMessages();
            if (invForm.getResult().equals("success")) {
                request.setAttribute("result", "SUCCESS");
                messages.add("SUCCESS", new ActionMessage("label.Grn.success"));
                request.setAttribute("show_message", invForm.getGrnNo());
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createGRN");
            } else if (invForm.getResult().equals("PartPriceMismatch")) {
                request.setAttribute("result", "FAILURE");
                messages.add("FAILURE", new ActionMessage("label.Grn.partPriceMismatch"));
                request.setAttribute("show_message", invForm.getInvNo());
                request.setAttribute("addMoreLink", "YES");
                request.setAttribute("addMoreLinkURL", "inventoryAction.do?option=createGRN");
            } else {
                request.setAttribute("result", "FAILURE");
                messages.add("FAILURE", new ActionMessage("label.Grn.failure"));
                request.setAttribute("show_message", "");
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

}

