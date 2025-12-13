/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Model.Action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import EAMG.Model.DAO.EAMG_ModelDAO;
import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author avinash.pandey
 */
public class EAMG_DisplayGroupsAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
private Logger logger = Logger.getLogger(this.getClass());
    Connection sqlConnection = null;
    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
         ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        sqlConnection = holder.getConnection();
        EAMG_ModelDAO dao=new EAMG_ModelDAO();
        String[] selectedgroups = request.getParameterValues("attachedlist");
        int selectedgroupslength = selectedgroups.length;
        int str=dao.getGroupMap(SUCCESS, null);
        session.setAttribute("selectedgroupslength", selectedgroupslength);
        session.setAttribute("selectedgroups", selectedgroups);
        return mapping.findForward(SUCCESS);
    }
}
