/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package action;

import java.util.LinkedHashSet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;
import org.apache.struts.util.LabelValueBean;

import beans.serviceForm;
import dao.masterDAO;
import dao.serviceDAO;
import dbConnection.dbConnection;
/**
 *
 * @author jasmeen.kaur
 */
public class serviceOptionsAction extends DispatchAction {

    private static final String SUCCESS = "success";
    String dbPATH = new dbConnection().dbPathAuth;


    public ActionForward initJobCardForPDI(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {


        try {
            HttpSession session = request.getSession();
            String user_id = (String) session.getAttribute("user_id");
            serviceForm sf = (serviceForm) form;
            String vinid=request.getParameter("vinid");
            sf.setVinid(vinid);
            serviceDAO obj = new serviceDAO();
            LinkedHashSet<LabelValueBean> jobTypeList = null;
            String pathNm = request.getParameter("pathNm");
            masterDAO mstObj = new masterDAO();
            String jobType = request.getParameter("jobType");
            String jobTypeDesc;

            if (jobType != null && jobType.equals("PDI")) {
                jobTypeDesc = "PDI";
                jobTypeList = obj.getCommonLabelValues("Jobtypemaster", "jobTypeID", "jobTypeDesc,seqNo", "seqNo", "where isActive='Y' and  jobTypeDesc='" + jobTypeDesc + "'");
            }
            LinkedHashSet<LabelValueBean> jobLocationList = obj.getCommonLabelValues("Joblocationmaster", "locationID", "locationDesc,seqNo", "seqNo", "");
            LinkedHashSet<LabelValueBean> nextServiceList = obj.getCommonLabelValues("Nextservicemaster", "nextServiceID", "nextServiceDesc,seqNo", "seqNo", "");//nextServiceID,nextServiceDesc,seqNo
            LinkedHashSet<LabelValueBean> jobCardStatusList = obj.getCommonLabelValues("Jobcardstatusmaster", "statusID", "statusDesc,seqNo", "seqNo", "");
            LinkedHashSet<LabelValueBean> ownerDrivenList = obj.getCommonLabelValues("Ownerdrivenmaster", "ownerDrivenID", "ownerDrivenDesc,seqNo", "seqNo", "");
            LinkedHashSet<LabelValueBean> serviceTypeList = obj.getCommonLabelValues("Servicetypemaster", "serviceTypeID", "serviceTypeDesc,seqNo", "seqNo", "");
            LinkedHashSet<LabelValueBean> productcategoryList = obj.getCommonLabelValues("Productcategorymaster", "categoryId", "productCategory", "productCategory", "");
            LinkedHashSet<LabelValueBean> eventList = obj.getCommonLabelValues("Eventmaster", "id", "eventName", "eventName", (String)session.getAttribute("dealerCode"));

           
            request.setAttribute("jobTypeList", jobTypeList);//JobLocationMaster,NextServiceMaster,JobCardStatusMaster,OwnerDrivenMaster,ServiceTypeMaster,eventmaster
            request.setAttribute("jobLocationList", jobLocationList);//
            request.setAttribute("nextServiceList", nextServiceList);
            request.setAttribute("jobCardStatusList", jobCardStatusList);
            request.setAttribute("ownerDrivenList", ownerDrivenList);
            request.setAttribute("serviceTypeList", serviceTypeList);
            request.setAttribute("productcategoryList", productcategoryList);
            request.setAttribute("eventList", eventList);

            if (sf.getJobCardNo() != null) {
                sf = obj.getJobCard_vehicale_DetailFor_singleJobcard(sf, sf.getJobCardNo(), sf.getVinNo());
                request.setAttribute("status", sf.getStatus());
            }

            request.setAttribute("serviceform", sf);
            request.setAttribute("jobType", sf.getJobType());
            request.setAttribute("pathNm", pathNm);


        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            try {
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
        return mapping.findForward("viewPDI");
    }
    ///////////////////////////////////////**JOB CARD FOR pdi***////////////////////////////////////////////////
}
