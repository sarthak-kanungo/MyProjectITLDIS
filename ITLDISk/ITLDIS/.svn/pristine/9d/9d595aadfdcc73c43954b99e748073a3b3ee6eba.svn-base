/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EAMG.Group.Action;


import java.sql.Connection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import EAMG.Group.ActionFormBean.EAMG_GroupCreationByWzActionForm;
import EAMG.Group.DAO.EAMGGroupDAO_R;
import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author vandana.singla
 */
public class ChangeGroupSequenceAction extends org.apache.struts.action.Action {
    
    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    
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
        String forward = "initGroupSequence";
        try {
            HttpSession session = request.getSession();
            EAMGGroupDAO_R dao = null;


            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            Connection con = holder.getConnection();
            EAMG_GroupCreationByWzActionForm GroupForm = (EAMG_GroupCreationByWzActionForm) form;
            String option = GroupForm.getOption();
            dao = new EAMGGroupDAO_R(con);
            if (option == null) {
                Vector<String> groupSeriesList = dao.getGroupSeries(con);
                request.setAttribute("groupSeriesList", groupSeriesList);
            } else if (option.equals("updateGroupSequence")) {
                String sequenceOrderList[] = request.getParameterValues("assign_Code");

                if (sequenceOrderList == null) {
                    Vector<String> groupSeriesList = dao.getGroupSeries(con);
                    request.setAttribute("groupSeriesList", groupSeriesList);
                    forward = "initGroupSequence";
                } else {
                    int result = dao.updateGroupSequence(con, sequenceOrderList);
                    if (result == 1) {
                        request.setAttribute("result", "SUCCESS");
                        request.setAttribute("show_message", "Group Sequence has been updated successfully.");
                        request.setAttribute("heading", "CHANGE TABLE BOM SEQUENCE");
                        
                    } else {
                        request.setAttribute("result", "FAILURE");
                        request.setAttribute("show_message", "Unable to update Group Sequence, please contact System Admin.");
                        request.setAttribute("heading", "CHANGE TABLE BOM SEQUENCE");
                        
                    }
                    forward = "success";
                }

            }
        } catch (Exception e) {
        }


        return mapping.findForward(forward);
    }
}