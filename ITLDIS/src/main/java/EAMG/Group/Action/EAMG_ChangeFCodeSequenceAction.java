/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Group.Action;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import EAMG.Group.ActionFormBean.EAMG_GroupCreationByWzActionForm;
import EAMG.Group.DAO.EAMGGroupDAO_R;
import dbConnection.dbConnection;
import viewEcat.comEcat.PageTemplate;

/**
 *
 * @author satyaprakash.verma
 */
public class EAMG_ChangeFCodeSequenceAction extends org.apache.struts.action.Action {

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        String forward = "";
        Connection conn = null;
        try {
            HttpSession session = request.getSession();
            EAMGGroupDAO_R dao = null;
            conn = new dbConnection().getDbConnection();
            dao = new EAMGGroupDAO_R(conn);
            EAMG_GroupCreationByWzActionForm GroupForm = (EAMG_GroupCreationByWzActionForm) form;
            String option = GroupForm.getOption();
            if (option.equals("initFCodeSequence")) {
                forward = "initFCodeSequenceDisplay";
            } else if (option.equals("updateGroupSequence")) {
                String[] groups = request.getParameterValues("assign_Code");
                String model_no = request.getParameter("model_no");
                int result = dao.updateModelGroupSequence(groups, model_no, conn);
                if (result == 1) {
                    request.setAttribute("tdData", "CHANGE "+ PageTemplate.MODEL_NO.toUpperCase()+" " + PageTemplate.GROUP.toUpperCase() + " SEQUENCE");
                    request.setAttribute("heading", "CHANGE " + PageTemplate.MODEL_NO .toUpperCase()+ " " + PageTemplate.GROUP.toUpperCase() + " SEQUENCE");
                    request.setAttribute("show_message", PageTemplate.MODEL_NO + " " + PageTemplate.GROUP + " Sequence has been updated Successfully.");
                } else {
                    request.setAttribute("tdData", "CHANGE "+ PageTemplate.MODEL_NO.toUpperCase()+" " + PageTemplate.GROUP.toUpperCase() + " SEQUENCE");
                    request.setAttribute("heading", "CHANGE " + PageTemplate.MODEL_NO .toUpperCase()+ " " + PageTemplate.GROUP.toUpperCase() + " SEQUENCE");
                    request.setAttribute("show_message", PageTemplate.MODEL_NO + " " + PageTemplate.GROUP + " Sequence has not been updated, please contact System Admin.");
                }
                forward = "success";
            }


        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }


        return mapping.findForward(forward);
    }
}
