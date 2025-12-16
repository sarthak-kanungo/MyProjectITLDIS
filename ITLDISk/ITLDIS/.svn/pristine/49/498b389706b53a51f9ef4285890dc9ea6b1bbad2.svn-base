/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Other.Action;

import java.sql.Connection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import EAMG.Model.DAO.EAMG_ModelDAO;
import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author manish.kishore
 */
public class EAMG_EAMG_VS_OMEAction extends org.apache.struts.action.Action {

    private static final String SUCCESS = "success";
    private static final String FAIL = "failure";
    private static final String INIT = "init";
    private static final String FORWARD_OME_PART = "getOMEAPart";
    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        String option = request.getParameter("option");
        ActionErrors errors = new ActionErrors();
        Connection conn = null;
        Vector tempVector = new Vector();
        String forwardsS = FAIL;
        EAMG_ModelDAO dao = null;
        boolean isError = false;
        int forward_main = 0;
        try {
            conn = holder.getConnection();
            conn.setAutoCommit(false);
            dao = new EAMG_ModelDAO();
            if (option.equals("getSelectedOMEPartByAMWPart")) {
                String searchAMWPart = request.getParameter("searchAMWPart").trim();
                Vector searchAMWPartresult = new EAMG_EAMGPart_OEMPartDAO().getAllOMEPARTByPart(searchAMWPart, conn);
                request.setAttribute("searchAMWPartresult", searchAMWPartresult);
                Vector searchOEMPartVec = new EAMG_EAMGPart_OEMPartDAO().getAllOMEPART(conn);
                request.setAttribute("searchOEMPartVec", searchOEMPartVec);
                forwardsS = FORWARD_OME_PART;
                //forward = INIT;
            } else if (option.equals("assign_AMW_VS_OME")) {

                String searchAMWPart = request.getParameter("searchAMWPart").trim();
                String[] attachedlist = request.getParameterValues("attachedlist");
                if (searchAMWPart.equals("")) {
                    errors.add("validate", new ActionMessage("error.AMW Part.failed"));
                    saveErrors(request, errors);
                    isError = true;
                    forwardsS = FAIL;
                    //return mapping.findForward(FAIL);
                }
                if (!isError) {
                    if (attachedlist.length > 0) {
                        for (int j = 0; j < attachedlist.length; j++) {
                            tempVector.add(attachedlist[j]);
                        }
                        forward_main = new EAMG_EAMGPart_OEMPartDAO().assignAMWOMEMatrix(searchAMWPart, tempVector, conn);
                        conn.commit();
                    }
                    if (forward_main == 1) {
                        request.setAttribute("result", "SUCCESS");
                        request.setAttribute("show_message", "Successfully Assign AMW vs OME Matrix.");
                    } else {
                        request.setAttribute("result", "FAILURE");
                        request.setAttribute("show_message", "Unable to Assign AMW vs OME Matrix. Please Contact Administrator.");
                    }
                    request.setAttribute("optionLink", "YES");
                    request.setAttribute("optionLinkName", "Assign More");
                    request.setAttribute("optionLinkURL", "/authJSP/other/amw_Assign_AMW_vs_OME_Matrix.jsp");
                    forwardsS = SUCCESS;
                }
            }
            conn.close();
            conn = null;

        } catch (Exception e) {
            logger.error("Caught in Exception", e);

        } finally {
            try {
                if (conn != null) {
                    conn.close();
                    conn = null;
                }
            } catch (Exception e) {
                logger.error("Caught in Final Exception", e);
            }
        }
        return mapping.findForward(forwardsS);
    }
}
