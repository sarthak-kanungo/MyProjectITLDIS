/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG_Service_Bulletin;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import viewEcat.comEcat.ConnectionHolder;
import viewEcat.comEcat.PageTemplate;

/**
 *
 * @author satyaprakash.verma
 */
public class EAMG_ServiceBulletinAction extends org.apache.struts.action.Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String forward = "";
        HttpSession session = request.getSession();
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        Connection con = holder.getConnection();
        String ecatPath = (String) session.getValue("ecatPATH");
        EAMG_ServiceBulletinBean sBForm = (EAMG_ServiceBulletinBean) form;
        int result = 3;
        String option = sBForm.getOption();
        if (option.equals("initSBUpdate")) {
            EAMG_ServiceBulletinDAO.getYears(sBForm);
            request.setAttribute("yearList", sBForm.getYearList());
            request.setAttribute("issueDate", sBForm.getIssueDate());
            forward = "initSBUpdate";
        } else if (option.equals("insertUpdateSB")) {
            EAMG_ServiceBulletinDAO.generateSBNo(sBForm, con);
            boolean isDocsUploaded = EAMG_ServiceBulletinDAO.uploadSBDocs(sBForm, ecatPath);
            if (isDocsUploaded) {
                result = EAMG_ServiceBulletinDAO.updateServiceBulletin(sBForm, con);
                if (result == 1) {
                    request.setAttribute("result", "SUCCESS");
                    request.setAttribute("show_message", "Successfully Uploaded "+PageTemplate.bulletin+".");
                    request.setAttribute("heading", "UPLOAD "+PageTemplate.bulletin.toUpperCase()+"");
                } else {
                    request.setAttribute("result", "FAILURE");
                    request.setAttribute("show_message", "Unable to upload "+PageTemplate.bulletin+", please contact System Admin.");
                    request.setAttribute("heading", "UPLOAD "+PageTemplate.bulletin.toUpperCase()+"");
                }
            }
            forward = "success";
        } else if (option.equals("viewBulletin")) {
            EAMG_ServiceBulletinDAO.viewOFBulletinDetails(sBForm, con);
            request.setAttribute("bulletinBean", sBForm);
            forward = "viewBulletin";
        } else if (option.equals("viewBulletinDetails")) {
            EAMG_ServiceBulletinDAO.viewOFBulletinSubjectDetails(sBForm, con);
            request.setAttribute("dataList", sBForm.getDataList());
            request.setAttribute("bulletinType", sBForm.getType());
            request.setAttribute("issueOfYear", sBForm.getYearOfIssue());
            forward = "viewBulletinDetails";
        } else if (option.equals("initSBDelete")) {
            EAMG_ServiceBulletinDAO.getYears(sBForm);
            request.setAttribute("yearList", sBForm.getYearList());
            request.setAttribute("issueDate", sBForm.getIssueDate());
            request.setAttribute("flag", "initView");
            forward = "initSBDelete";
        } else if (option.equals("deleteViewBulletin")) {
            EAMG_ServiceBulletinDAO.getAllBulletin(sBForm, con);
            request.setAttribute("yearOfIssue", sBForm.getYearOfIssue());
            request.setAttribute("type", sBForm.getType());
            request.setAttribute("flag", "dataView");
            request.setAttribute("dataList", sBForm.getDataList());
            forward = "initSBDelete";
        } else if (option.equals("deleteViewBulletinDetails")) {
            result = EAMG_ServiceBulletinDAO.deleteBulletin(sBForm, con, ecatPath);
            if (result == 1) {
                request.setAttribute("result", "SUCCESS");
                request.setAttribute("show_message", "Successfully Deleted "+PageTemplate.bulletin+".");
                request.setAttribute("heading", "DELETE "+PageTemplate.bulletin.toUpperCase()+"");
            } else {
                request.setAttribute("result", "FAILURE");
                request.setAttribute("show_message", "Unable to delete "+PageTemplate.bulletin+", please contact System Admin.");
                request.setAttribute("heading", "DELETE "+PageTemplate.bulletin.toUpperCase()+"");
            }
            forward = "success";
        }
        return mapping.findForward(forward);

    }
}
