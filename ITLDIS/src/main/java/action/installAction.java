/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import beans.serviceForm;
import dao.installDAO;
import dao.pdiDAO;
import dao.serviceDAO;

/**
 *
 * @author megha.pandya
 */
public class installAction extends DispatchAction {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";

    /**
     * This is the Struts action method called on
     * http://.../actionPath?method=myAction1, where "method" is the value
     * specified in <action> element : ( <action parameter="method" .../> )
     */
    public ActionForward initInstallInfo(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {

            serviceForm sf = (serviceForm) form;
            installDAO obj1 = new installDAO();

            pdiDAO pDAO = new pdiDAO();
            serviceDAO sd = new serviceDAO();
            HttpSession session = request.getSession();
            String dealercode = (String) session.getAttribute("dealerCode");
            LinkedHashSet<LabelValueBean> accessoriesList = obj1.getCommonLabelValue("Insmasteraccessories", "id", "accessoriesName", "accessoriesName", "");
            LinkedHashSet<LabelValueBean> majorAppList = obj1.getCommonLabelValue("Insmastermajorapplication", "id", "majorApplicationNm", "majorApplicationNm", "");
            LinkedHashSet<LabelValueBean> mechanicList = obj1.getCommonLabelValue("MSWDmechanicmaster", "mechanicDealerKey", "concat(mechanicName,' (', mechanicCode,')'),mechanicName", "mechanicName", " where dealerCode='" + dealercode + "' and isActive='Y'");
            obj1.getContentList(sf, 0);
            obj1.GetVinNoDetails(sf);//

            HashMap<String, ArrayList<String>> travelcardPartList = pDAO.GetTravelDetailIns(sf.getVinNo());
            LinkedList<ContentFormBean> pditravelcardPartList = obj1.GetTravelDetailIns(sf.getVinNo());
            sf.setConstantValue(sd.getHesConstantValue(10));
            request.setAttribute("accessoriesList", accessoriesList);
            request.setAttribute("majorAppList", majorAppList);
            request.setAttribute("mechanicList", mechanicList);
            request.setAttribute("travelcardPartList", travelcardPartList);
            request.setAttribute("pditravelcardPartList", pditravelcardPartList);
            request.setAttribute("serviceform", sf);
            //request.setAttribute("saleDate", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            request.setAttribute("insDate", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));
            request.setAttribute("jobCardDate", new SimpleDateFormat("dd/MM/yyyy").format(new Date()));

            //
//             System.out.println(""+mechanicList.size()+"-"+majorAppList.size()+"--"+sf.getCustomerName()+"--");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
//        initInstallCustomerInformation(mapping, form, request, response);
        return mapping.findForward("initInstallInfo");
    }

    public ActionForward initInstallInfoPrint(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "ViewInstallPrint";
        try {

            serviceForm sf = (serviceForm) form;
            installDAO inDAO = new installDAO();
            HttpSession session = request.getSession();
            String dealercode = (String) session.getAttribute("dealerCode");
            //  System.out.println("1---" + dealercode);
            dealercode = commonUtilMethods.decodeBase64(request.getParameter("dealerCode").toString());
            //  System.out.println("2---" + dealercode);
            String realPath = getServlet().getServletContext().getRealPath("/");
            String contextPath = (String) request.getContextPath();
            sf.setContext(contextPath);
            sf.setRealpath(realPath);
            sf.setInsNo(commonUtilMethods.decodeBase64(sf.getInsNo()));
            inDAO.getSingleINSData(sf, sf.getInsNo());
            LinkedList<ContentFormBean> travelcardPartList = inDAO.getSingleINSDatatractorlist(sf, sf.getInsNo());
            inDAO.getContentListview(sf);
            LinkedList<serviceForm> enquiriesList = inDAO.GetEnquriesIns(sf.getInsNo());
            //  LinkedHashSet<LabelValueBean> mechanicList = inDAO.getCommonLabelValue("MSWDmechanicmaster", "mechanicDealerKey", "concat(mechanicName,' (', mechanicCode,')'),mechanicName", "mechanicName", " where  mechanicDealerKey='"+sf.getMechanicName()+"' and isActive='Y'");
            sf.setRealpath(realPath.replace("\\", "/"));
            //System.out.println("sf.setRealpath()" + sf.getRealpath());
            request.setAttribute("serviceform", sf);//custName,fatherName,mobilePh,villagename,relationwithcustomer
            request.setAttribute("travelcardPartList", travelcardPartList);
            request.setAttribute("enquiriesList", enquiriesList);
            //  request.setAttribute("mechanicList", mechanicList);
            if (commonUtilMethods.decodeBase64(request.getParameter("printINS")) != null && commonUtilMethods.decodeBase64(request.getParameter("printINS")).equals("true")) {
                request.setAttribute("heading", "Installation Certificate");
                request.setAttribute("headingFlag", "headingFlag");
                forward = "printINS";
            }
            //
//             System.out.println(""+mechanicList.size()+"-"+majorAppList.size()+"--"+sf.getCustomerName()+"--");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
//        initInstallCustomerInformation(mapping, form, request, response);
        return mapping.findForward(forward);
    }

    /**
     * getvinNumberAjax for passing vin no list to jsp created on 26/05/14 by Megha
     */
    public ActionForward getvinNumberAjax(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        PrintWriter pw = null;
        try {
           // installDAO obj = new installDAO();
           // String vinNo = request.getParameter("vinNo");
            //HttpSession session = request.getSession();
//            String result = obj.GetVinNoList(vinNo,session.getAttribute("user_id").toString());
            pw = response.getWriter();
            pw.write("result");

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(null);
    }

    /**
     * getting vinNumber details  for Installation created on 26/05/14 by Megha
     */
    public ActionForward getvinNumberdetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        PrintWriter pw = null;
        try {

            serviceForm sf = (serviceForm) form;
            installDAO obj = new installDAO();
            String vindetails = obj.GetVinNoDetails(sf);
//            System.out.println("detail:"+vindetails);
            pw = response.getWriter();
            pw.write(vindetails);

        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(null);
    }

    /**
     * init Customer Information for installation created on 27/05/14 by Megha
     */
    public ActionForward initInstallCustomerInformation(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {

        try {

            serviceForm sf = (serviceForm) form;
            installDAO obj = new installDAO();
            sf = obj.GetCustomerDetails(sf);
            request.setAttribute("serviceform", sf);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward("initInstallCustomerInfo");
    }

    /**
     * init Standard Checks for installation created on 27/05/14 by Megha
     */
    public ActionForward initStandardChecks4Install(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        try {

            installDAO obj1 = new installDAO();
            serviceForm sf = (serviceForm) form;
            obj1.getContentList(sf, 0);
            request.setAttribute("serviceform", sf);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward("standardChecks4Ins");
    }

    /**
     * manage installation created on 27/05/14 by Megha
     */
    public ActionForward manageInstall(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        HttpSession session = request.getSession();
        String forwardto = "ViewAllInstallList";
        serviceForm sf = null;
        try {
            installDAO obj = new installDAO();
            sf = (serviceForm) form;
            //   System.out.println("sf.getupload" + sf.getUploadphoto());
            sf.setVinid(request.getParameter("vinid"));
            sf.setRepName(request.getParameter("repname"));
            //  System.out.println("vinid" + sf.getVinid());
//             System.out.println("in manage install:"+sf.getJobCardDate()+"-"+sf.getVinNo()+"-"+sf.getCustomerName());
            String user_id = (String) session.getAttribute("user_id");
            String realPath = getServlet().getServletContext().getRealPath("/");
            String contextPath = (String) request.getContextPath();
            sf.setContext(contextPath);
            sf.setRealpath(realPath);
            LinkedList<ContentFormBean> TravelCardList = new LinkedList<ContentFormBean>();
            LinkedList<serviceForm> EnquiriesList = new LinkedList<serviceForm>();
            String[] actiontakens = sf.getActiontakens();
            String[] enquiry = sf.getEnquiry();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            SimpleDateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            ActionMessages messages = new ActionMessages();
            java.util.Date visitdate = sdf.parse(sf.getJobCardDate());
            java.util.Date deliverydate = sdf.parse(sf.getDeliveryDate());
            java.util.Date invoiceDate = sdf.parse(sf.getSaleDate());
            java.util.Date insDate = sdf.parse(sf.getInsDate());
            Date today = new Date();
            Date condate = new Date(df.format(today));
            condate.setDate(condate.getDate() - Integer.parseInt(new serviceDAO().getHesConstantValue(10)));

            if ((today.compareTo(visitdate) < 0)) {
                request.setAttribute("message", "Visit Date cannot be greater than Current Date.");
                initInstallInfo(mapping, form, request, response);
                forwardto = "initInstallInfo";
            }else if ((condate.compareTo(insDate) > 0)  || (today.compareTo(insDate) < 0)) {
                request.setAttribute("message", "Final Installation Date should be within " + new serviceDAO().getHesConstantValue(10) + " days to Current Date.");
                initInstallInfo(mapping, form, request, response);
                forwardto = "initInstallInfo";
            }else if (visitdate.compareTo(invoiceDate) < 0) {
                request.setAttribute("message", "Dealer Invoice Date should be less than Visit Date.");
                initInstallInfo(mapping, form, request, response);
                forwardto = "initInstallInfo";
            } else if ((today.compareTo(deliverydate) < 0)) {
                request.setAttribute("message", "Delivery Date cannot be greater than Current Date.");
                initInstallInfo(mapping, form, request, response);
                forwardto = "initInstallInfo";
            } else if (visitdate.compareTo(insDate) > 0) {
                request.setAttribute("message", "Final Installation Date cannot be less than visit Date.");
                initInstallInfo(mapping, form, request, response);
                forwardto = "initInstallInfo";
            } else if (deliverydate.compareTo(visitdate) > 0) {
                request.setAttribute("message", "Delivery Date cannot be greater than Visit Date.");
                initInstallInfo(mapping, form, request, response);
                forwardto = "initInstallInfo";
            } else {
                for (int a = 0; a < actiontakens.length; a++) {
                    if (request.getParameter("partNo" + actiontakens[a]) != null && !request.getParameter("partNo" + actiontakens[a]).equals("")) {
                        ContentFormBean cf = new ContentFormBean();
                        cf.setContentDesc(request.getParameter("partNo" + actiontakens[a]));
                        cf.setObservation(request.getParameter("partVendorName" + actiontakens[a]));
                        cf.setStatus(request.getParameter("partSerialNo" + actiontakens[a]));
//                 cf.setStatus(request.getParameter("contentId"+actiontakens[a]));
//                    System.out.println("--" + cf.getContentDesc() + "--" + cf.getObservation() + "--" + cf.getStatus());
                        TravelCardList.add(cf);
                    }
                }

                for (int a = 0; a < enquiry.length; a++) {
                    if (request.getParameter("custName" + enquiry[a]) != null && !request.getParameter("custName" + enquiry[a]).equals("")) {
                        serviceForm sf1 = new serviceForm();
                        sf1.setCustName(request.getParameter("custName" + enquiry[a]));
                        sf1.setFatherName(request.getParameter("fatherName" + enquiry[a]));
                        sf1.setMobilePh(request.getParameter("mobilePh" + enquiry[a]));
                        sf1.setVillagename(request.getParameter("villagename" + enquiry[a]));
                        sf1.setRelationwithcustomer(request.getParameter("relationwithcustomer" + enquiry[a]));

                        EnquiriesList.add(sf1);
                    }
                }


                StringBuilder majorApplications = new StringBuilder(), accessories = new StringBuilder();
                String[] accessories_size = request.getParameterValues("accessories");
                String[] majorApp_size = request.getParameterValues("majorApp");
//             System.out.println(""+accessories_size.length+"--"+majorApp_size.length);

                for (int i = 0; i < accessories_size.length; i++) {
                    if (request.getParameter("assrev" + accessories_size[i]) != null) {
                        accessories.append(request.getParameter("assrev" + accessories_size[i]));
                        accessories.append(",");
//                    System.out.println("value:"+request.getParameter("assrev"+accessories_size[i]));
                    }
                }
                for (int i = 0; i < majorApp_size.length; i++) {
                    if (request.getParameter("majapp" + majorApp_size[i]) != null) {
                        majorApplications.append(request.getParameter("majapp" + majorApp_size[i]));
                        majorApplications.append(",");
//                    System.out.println("value:"+request.getParameter("majapp"+majorApp_size[i]));
                    }
                }
                sf.setMajorApplications(majorApplications.toString().length() > 0 ? majorApplications.toString().substring(0, majorApplications.toString().length() - 1) : "");
                sf.setAccessories(accessories.toString().length() > 0 ? accessories.toString().substring(0, accessories.toString().length() - 1) : "");

                LinkedList<ContentFormBean> Content_CheckList = new LinkedList<ContentFormBean>();
                int contentI;
                String[] contentId = sf.getContentId();
                for (int c = 0; c < contentId.length; c++) {
                    ContentFormBean cf = new ContentFormBean();
                    contentI = Integer.valueOf(contentId[c]);
                    cf.setContentId(contentI);
                    cf.setStatus(request.getParameter(contentId[c] + "checkpoints") == null ? "N" : "Y");
                    //                System.out.println("id:"+cf.getContentId()+"-status:"+cf.getStatus());
                    Content_CheckList.add(cf);
                }
                sf.setDealercode((String) session.getAttribute("dealerCode"));
                String data_str = obj.addInstallationDetails(sf, user_id, Content_CheckList, TravelCardList, EnquiriesList);
                request.setAttribute("message", data_str.split("@@")[2]);
                request.setAttribute("show_message", data_str.split("@@")[2]);
                request.setAttribute("result", data_str.split("@@")[0].toUpperCase());
                if (data_str.split("@@")[1].equals("existfailure")) {

                    initViewAllInstallList(mapping, form, request, response);
                    messages.add("FAILURE", new ActionMessage("label.common.existfailure"));
                    request.setAttribute("message", sf.getVinNo());
                    forwardto = "ViewAllInstallList";
                } else if (data_str.split("@@")[1].equals("installationsuccess")) {
                    request.setAttribute("addMoreLink", "YES");
                    request.setAttribute("addMoreLinkURL", "installProcess.do?option=initViewAllInstallList");
                    messages.add("SUCCESS", new ActionMessage("label.common.installationsuccess"));
                    request.setAttribute("message", sf.getVinNo());
                    forwardto = "message";
                } else if (data_str.split("@@")[1].equals("installationfailure")) {

                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "installProcess.do?option=initViewAllInstallList");
                    messages.add("FAILURE", new ActionMessage("label.common.installationfailure"));
                    request.setAttribute("message", sf.getVinNo());
                    forwardto = "message";
                } else if (data_str.split("@@")[1].equals("FILESIZE")) {
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkURL", "installProcess.do?option=initViewAllInstallList");
                    messages.add("FAILURE", new ActionMessage("label.common.filesizefailure"));
                    request.setAttribute("message", sf.getVinNo());
                    forwardto = "message";
                }
            }
            saveErrors(request, messages);


        } catch (Exception e1) {
            request.setAttribute("message", "Installation for Vehicle No. " + sf.getVinNo() + " has not been  Added");
            request.setAttribute("optionLink", "YES");
            request.setAttribute("optionLinkURL", "installProcess.do?option=initViewAllInstallList");
            forwardto = "message";

            e1.printStackTrace();
        }
        return mapping.findForward(forwardto);
    }

    /**
     * manage Standard Checks for installation created on 27/05/14 by Megha
     */
    public ActionForward manageStandardChecks4Install(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String forwardto = "";
        try {
            HttpSession session = request.getSession();
            installDAO obj = new installDAO();
            serviceForm sf = (serviceForm) form;
            String user_id = (String) session.getAttribute("user_id");
            int contentI;
            LinkedList<ContentFormBean> UpdatedContentList = new LinkedList<ContentFormBean>();
            String[] contentId = sf.getContentId();
            for (int c = 0; c < contentId.length; c++) {
                ContentFormBean cf = new ContentFormBean();
                contentI = Integer.valueOf(contentId[c]);
                cf.setContentId(contentI);
                cf.setStatus(request.getParameter(contentId[c] + "checkpoints") == null ? "N" : "Y");
                UpdatedContentList.add(cf);
            }

            String data_str = "";
            obj.addStandardChecksData4Ins(null, UpdatedContentList, "");
            request.setAttribute("message", data_str.split("@@")[1]);
            request.setAttribute("serviceform", sf);
            if (data_str.split("@@")[0].equalsIgnoreCase("SUCCESS")) {

                forwardto = "standardChecks4Ins";
            } else {

                forwardto = "standardChecks4Ins";
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(forwardto);
    }

    /**
     * View All InstallList created on 3/6/14 n=by Megha
     */
    public ActionForward initViewAllInstallList(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        String forward = "";
        try {
            String user_id = (String) session.getAttribute("user_id");
            serviceForm sf = (serviceForm) form;
            //sf.setDealercode((String) session.getAttribute("dealerCode"));
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            installDAO obj = new installDAO();
            String search = request.getParameter("nameSrch") == null ? "" : request.getParameter("nameSrch");

            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                sf.setDealercode((String) session.getAttribute("dealerCode"));
            } else {
                request.setAttribute("dealerList", dealerList);
            }

            /*          if (userFunctionalities.contains("101")) {
            sf.setDealercode((String) session.getAttribute("dealerCode"));
            } else if (userFunctionalities.contains("102")) {
            ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('" + user_id + "',dm.loginId)", 3);
            request.setAttribute("dealerList", dealerList);
            } else {
            ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d ", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "", 3);
            request.setAttribute("dealerList", dealerList);
            }    
             */
            sf.setDealercode(sf.getDealercode() == null ? "ALL" : sf.getDealercode());
            LinkedList<serviceForm> chassisList = obj.GetVinNoList(search, (String) session.getAttribute("dealerCode"), userFunctionalities, user_id, sf);
            if (chassisList.size() == 0) {
                if (!search.equalsIgnoreCase("")) {
                    request.setAttribute("message", "No Chassis no. found for '" + search + "'");
                } else {
                    request.setAttribute("message", "No Installation pending for Dealer '" + sf.getDealercode() + "'");
                }
            }
            request.setAttribute("chassisList", chassisList);
            request.setAttribute("serviceform", sf);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        if (request.getParameter("eType") != null && request.getParameter("eType").equals("export")) {
            forward = "exportPendingInstallList";
        } else {
            forward = "ViewAllInstallList";
        }
        return mapping.findForward(forward);
    }

    public ActionForward init_viewDetails(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();

        String todate = "";
        String fromdate = "";
        String forward = "";

        try {
            String user_id = (String) session.getAttribute("user_id");
            serviceForm sf = (serviceForm) form;
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            // InstallationDetails id= new InstallationDetails();
            // sf.setDealercode((String)session.getAttribute("dealerCode"));
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            installDAO obj = new installDAO();
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
            String search = request.getParameter("nameSrch") == null ? "" : request.getParameter("nameSrch");
            sf.setFromdate(request.getParameter("fromDate") == null ? fromdate : request.getParameter("fromDate"));
            sf.setTodate(request.getParameter("toDate") == null ? todate : request.getParameter("toDate"));

            List dealerList = MethodUtility.getDealersDetailsUnderUser(user_id);
            if (userFunctionalities.contains("101")) {
                sf.setDealercode((String) session.getAttribute("dealerCode"));
            } else {
                request.setAttribute("dealerList", dealerList);
            }
            /*         if (userFunctionalities.contains("101")) {
            sf.setDealercode((String) session.getAttribute("dealerCode"));
            } else if (userFunctionalities.contains("102")) {
            ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d,UmUserDealerMatrix dm", "d.dealerCode,d.dealerName,d.location", "d.dealerName", " where dm.dealerCode=d.dealerCode and dm.loginId = isnull('" + user_id + "',dm.loginId)", 3);
            request.setAttribute("dealerList", dealerList);
            } else {
            ArrayList<ArrayList<String>> dealerList = MethodUtility.getListwithJoinQuery("Dealervslocationcode d ", "d.dealerCode,d.dealerName,d.location", "d.dealerName", "", 3);
            request.setAttribute("dealerList", dealerList);
            }
             */
            sf.setDealercode(sf.getDealercode() == null ? "ALL" : sf.getDealercode());
            LinkedList<serviceForm> chassisList = obj.GetInsNoList(search, (String) session.getAttribute("dealerCode"), userFunctionalities, user_id, sf);
            if (chassisList.size() == 0) {
                if (!search.equalsIgnoreCase("")) {
                    request.setAttribute("message", "No Installation found for '" + search + "'");
                } else {
                    // request.setAttribute("message", "No Installation pending for '"+(String)session.getAttribute("dealerCode")+"'");
                }
            }
            String dealerName = new pdiDAO().getDealerName(sf.getDealercode());
            request.setAttribute("chassisList", chassisList);
            request.setAttribute("fromDate", sf.getFromdate());
            request.setAttribute("toDate", sf.getTodate());
            request.setAttribute("InstallationDetails", sf);
            request.setAttribute("nameSrch", search);
            request.setAttribute("range", sf.getRange());
            request.setAttribute("dealerName", dealerName);
        } catch (Exception e1) {
            e1.printStackTrace();
        }

        if (request.getParameter("eType") != null && request.getParameter("eType").equals("export")) {
            forward = "exportViewInstallList";
        } else {
            forward = "ViewInstallList";
        }
        return mapping.findForward(forward);
    }

    public ActionForward getfilesize(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PrintWriter pw = null;
        try {

            String filename = request.getParameter("filename");
            String filesize = new MethodUtility().getfilesize(filename);
            pw = response.getWriter();
            pw.write(filesize);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward(null);
    }

    public ActionForward updateInsImage(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        PrintWriter pw = null;
        String forword = "";
        try {
            serviceForm sf = (serviceForm) form;
            installDAO obj = new installDAO();
            HttpSession session = request.getSession();
            String insNo = request.getParameter("insNo");
            String delcode = (String) session.getAttribute("dealerCode");
            String realPath = getServlet().getServletContext().getRealPath("/");
            String result = obj.updateInsImage(sf, insNo, realPath);
            if (result.equalsIgnoreCase("success")) {
                //messages.add("SUCCESS", new ActionMessage("label.common.wcCreateSuccess"));
                request.setAttribute("show_message", "Image has been Successfully Updated");
                // request.setAttribute("show_message","");
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("viewInstallLink", "YES");
                request.setAttribute("viewInstallLinkURL", "installProcess.do?option=initInstallInfoPrint&vinNo=&insNo=" + commonUtilMethods.encodeToBase64(insNo) + "&dealerCode=" + commonUtilMethods.encodeToBase64(delcode));
            } else {
                // messages.add("FAILURE", new ActionMessage("label.common.wcFailure"));
                request.setAttribute("show_message", "Image has not been Updated.(Size should be less than 500 KB.)");
                request.setAttribute("viewInstallLink", "YES");
                request.setAttribute("result", "FAILURE");
                request.setAttribute("viewInstallLinkURL", "installProcess.do?option=initInstallInfoPrint&vinNo=&insNo=" + commonUtilMethods.encodeToBase64(insNo) + "&dealerCode=" + commonUtilMethods.encodeToBase64(delcode));
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return mapping.findForward("message");
    }
}
