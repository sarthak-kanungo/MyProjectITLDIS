/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;

import com.common.MethodUtility;

import beans.ManageCustomerForm;
import dao.ManageCustomerDao;

/**
 *
 * @author yogasmita.patel
 */
public class ManageCustomerAction extends DispatchAction {

    public ActionForward addCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "masterCustomerOption";
        LinkedHashSet<LabelValueBean> custCatList=new LinkedHashSet<LabelValueBean>();
        try {
            ManageCustomerForm custForm = (ManageCustomerForm) form;
            custCatList=MethodUtility.getCommonLabelValueHiber("CustomerCategoryMaster", "custcategoryID", "custCategory", "custCategory", "where e.isActive='Y'");
            LabelValueBean custcCatlable=new LabelValueBean();
            custcCatlable.setValue("4");
            custCatList.remove(custcCatlable);
            custForm.setCustomerCatgryList(custCatList);
            custForm.setCountryList(MethodUtility.getCustomerCommonList("countryId","country"));
            custForm.setStateList(new LinkedHashSet<LabelValueBean>());
            custForm.setDistrictList(new LinkedHashSet<LabelValueBean>());
            custForm.setTehsilList(new LinkedHashSet<LabelValueBean>());
            custForm.setBlockList(new LinkedHashSet<LabelValueBean>());
            custForm.setVillageList(new LinkedHashSet<LabelValueBean>());
            custForm.setCreditLimit(0.0);
            custForm.setCustomerTarget(0.0);
            custForm.setDiscountPercentage(0.0f);
            saveToken(request);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward initCustomerMasters(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "searchCustomerOption";
        try {
            String dealerCode = request.getSession().getAttribute("dealerCode").toString();
            ManageCustomerForm custForm = (ManageCustomerForm) form;
            custForm.setCustomerCatgryList(MethodUtility.getCommonLabelValueHiber("CustomerCategoryMaster", "custcategoryID", "custCategory", "custCategory", "where e.isActive='Y'"));
            request.setAttribute("customerList", new ManageCustomerDao().getCustomerDetailsList(custForm, dealerCode));            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward saveCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "";
        String result = "fail", message = "";
        //LinkedHashSet<LabelValueBean> custCatgryList = null;

        try {
            ManageCustomerForm custForm = (ManageCustomerForm) form;
            custForm.setCustomerCatgryList(MethodUtility.getCommonLabelValueHiber("CustomerCategoryMaster", "custcategoryID", "custCategory", "custCategory", "where e.isActive='Y'"));
            String dealerCode = request.getSession().getAttribute("dealerCode").toString();
            String customerId = request.getParameter("customerId");
            ManageCustomerDao custDao = new ManageCustomerDao();
            if (customerId != null && customerId.length() > 0) {
                result = custDao.updateCustomerDetails(custForm, dealerCode);
                if (result.equals("success")) {
                    message = "Customer " + "'" + custForm.getCustomerName() + "'" + " updated successfully.";
                } else {
                    message = "Customer Code " + "'" + custForm.getCustomerCode() + "'" + " already Exist, Please Enter other Code.";
                    request.setAttribute("message", message);
                }
            } else {
                if (isTokenValid(request)) {
                    result = custDao.saveCustomerDetails(custForm, dealerCode);
                    if (result.equals("success")) {
                        message = "Customer " + "'" + custForm.getCustomerName() + "'" + " added successfully.";
                        resetToken(request);

                    } else {
                        message = "Customer Code " + "'" + custForm.getCustomerCode() + "'" + " already Exist, Please Enter other Code.";
                        request.setAttribute("message", message);
                    }
                }
            }
            if (result.equals("success")) {
                forward = "searchCustomerOption";
            } else {
                forward = "masterCustomerOption";
            }
            request.setAttribute("customerList", new ManageCustomerDao().getCustomerDetailsList(custForm, dealerCode));
            
            request.setAttribute("message", message);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward editCustomer(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "masterCustomerOption";
        LinkedHashSet<LabelValueBean> custCatList=new LinkedHashSet<LabelValueBean>();
        try {
            String userId = request.getSession().getAttribute("user_id").toString();
            String customerId = request.getParameter("customerId").toString();
            String custcategoryID = request.getParameter("custcategoryID").toString();
            String custCategory = request.getParameter("custCategory").toString();
            ManageCustomerForm custForm = (ManageCustomerForm) form;
            custCatList=MethodUtility.getCommonLabelValueHiber("CustomerCategoryMaster", "custcategoryID", "custCategory", "custCategory", "where e.isActive='Y'");
            if(custcategoryID.equals("4")){
            LabelValueBean custcCatlable=new LabelValueBean();
                custCatList=new LinkedHashSet<LabelValueBean>();
                custcCatlable.setLabel(custCategory);
                custcCatlable.setValue(custcategoryID);
                custCatList.add(custcCatlable);
                custForm.setCustomerCatgryList(custCatList);
            }else{
              custForm.setCustomerCatgryList(custCatList);
            }
            
             new ManageCustomerDao().getCustomerDetailsForEdit(custForm, userId, customerId);

            custForm.setCountryList(MethodUtility.getCustomerCommonList("countryId","country"));

            if(custForm.getCountryID()!=null && custForm.getCountryID()!=0)
            {
             custForm.setStateList(MethodUtility.getCustomerGeographyList("stateId,state","countryId",""+custForm.getCountryID(),"state"));
            }else{
                custForm.setStateList(new LinkedHashSet<LabelValueBean>());
            }
            if(custForm.getStateID()!=null && custForm.getStateID()!=0)
            {
             custForm.setDistrictList(MethodUtility.getCustomerGeographyList("districtId,district","stateId",""+custForm.getStateID(),"district"));
            }else{
               custForm.setDistrictList(new LinkedHashSet<LabelValueBean>());
            }
            if(custForm.getDistrictID()!=null && custForm.getDistrictID()!=0)
            {
             custForm.setTehsilList(MethodUtility.getCustomerGeographyList("tehsilId,tehsil","districtId",""+custForm.getDistrictID(),"tehsil"));
            }else{
               custForm.setTehsilList(new LinkedHashSet<LabelValueBean>());
            }
            if(custForm.getTehsilID()!=null && custForm.getTehsilID()!=0)
            {
             custForm.setBlockList(MethodUtility.getCustomerGeographyList("blockId,block","tehsilId",""+custForm.getTehsilID(),"block"));
            }else{
               custForm.setBlockList(new LinkedHashSet<LabelValueBean>());
            }
            if(custForm.getBlockID()!=null) // && custForm.getBlockID()!=0
            {
             custForm.setVillageList(MethodUtility.getCustomerGeographyList("villageId,villageName","blockId",""+custForm.getBlockID(),"villageName"));
            }else{
              custForm.setVillageList(new LinkedHashSet<LabelValueBean>());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward initReceivePayment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "initReceivePayment";
        try {
            ManageCustomerForm custForm = (ManageCustomerForm) form;
            custForm.setCustomerId(request.getParameter("customerId").toString());
            custForm.setCustomerName(request.getParameter("customerName").toString());
            custForm.setCustomerCode(request.getParameter("customerCode").toString());
            custForm.setCustomerLocation(request.getParameter("customerLocation").toString());
            custForm.setPaymentDue(Double.parseDouble(request.getParameter("paymentDue").toString()));
            custForm.setAvailableCreditLimit(Double.parseDouble(request.getParameter("availableCreditLimit").toString()));
            custForm.setPaymentModeList(MethodUtility.getCommonLabelValueHiber("MSWPaymentModes", "paymentModeID", "payementMode", "payementMode", "where e.isActive='Y'"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward saveReceivedPayment(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "success";
        String result = "failure", message = "";
        try {
            String userId = request.getSession().getAttribute("user_id").toString();
            ManageCustomerForm custForm = (ManageCustomerForm) form;
            result = new ManageCustomerDao().saveReceivedPayment(custForm, userId);
            if (result.equals("success")) {
                message = "Payment added Successfully.";
            } else {
                message = "Payment added Unsuccessful.";
            }
            request.setAttribute("message", message);
            request.setAttribute("result", result);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }

    public ActionForward showPaymentHistory(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        String forward = "showPaymentHistory";
        String todate = "";
        String fromdate = "";
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
            ManageCustomerForm custForm = (ManageCustomerForm) form;
            if (request.getParameter("customerId") != null) {
                custForm.setCustomerId(request.getParameter("customerId").toString());
                new ManageCustomerDao().getCustomerDetailsForEdit(custForm, request.getSession().getAttribute("dealerCode").toString(), custForm.getCustomerId());
            }

            Calendar cal = Calendar.getInstance();
            todate = dateFormat.format(cal.getTime());
            cal.add(Calendar.DATE, -365);
            fromdate = dateFormat.format(cal.getTime());
            ManageCustomerDao dao = new ManageCustomerDao();

            request.setAttribute("paymentList", dao.getReceivedPaymentList(custForm));
            custForm.setFromdate(request.getParameter("fromdate") == null ? fromdate : request.getParameter("fromdate"));
            custForm.setTodate(request.getParameter("todate") == null ? todate : request.getParameter("todate"));
            custForm.setCustomerId(request.getParameter("customerId") == null ? custForm.getCustomerId() : request.getParameter("customerId"));

        } catch (Exception e) {
            e.printStackTrace();
        }
        return mapping.findForward(forward);
    }
    // new chages in Customer master

    public ActionForward getStandardDiscount(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String discountPercentage = "0";
        try {
            if(request.getParameter("custCategoryID")!=null && !"".equals((String)request.getParameter("custCategoryID")))
            {
                discountPercentage = new ManageCustomerDao().getStandardDiscount(Integer.parseInt(request.getParameter("custCategoryID")), request.getSession().getAttribute("dealerCode").toString());
                PrintWriter pw = response.getWriter();
                pw.write(discountPercentage);
                pw.close();
            }
            
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return null;
    }

     public ActionForward getSubFieldDetails(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
        try {
            
            String colValue = request.getParameter("colValue");
            String getColName = request.getParameter("getColName");
            String whereColName = request.getParameter("whereColName");
            String orderBy= request.getParameter("orderBy");
            String subField = "";
            try {               
                subField= ManageCustomerDao.getListOnSelect(getColName,whereColName, colValue, orderBy);
                PrintWriter pw = response.getWriter();
                pw.write(subField);
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
}
