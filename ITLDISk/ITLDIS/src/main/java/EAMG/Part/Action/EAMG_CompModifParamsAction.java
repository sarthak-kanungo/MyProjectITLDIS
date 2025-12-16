/*
 *
File Name   : 	AT4_CompModifParamsAction.java
PURPOSE     :
1. To Update the values in COMP_DETAIL.
2. To Insert PARAM VALUES in COMP_PARAM_VALUES.
HISTORY     :
DATE        	BUILD     	AUTHOR    	MODIFICATIONS
01/12/08	1.0		SURENDER KUMAR	$$1 	Created
 *
 */
package EAMG.Part.Action;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EAMG.common.EAMG_MethodsUtility;

import viewEcat.comEcat.ConnectionHolder;

public class EAMG_CompModifParamsAction extends Action {

    private final static String SUCCESS = "success";

	 @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        /************************Getting the session values**********************/
        HttpSession session = request.getSession();
        String comp_param_list = (String) session.getAttribute("comp_param_list");
        session.setAttribute("option", "modify");
        String userId = session.getAttribute("userCode").toString();
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        /************************************************************************/
        /***********************Initializing the variables***********************/
        Connection conn = null;
        String desc = "";
        String query1 = "";
        PreparedStatement pstmt = null;
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        /************************************************************************/
        /*************** Getting  the values through request parameter.**************/
        desc = request.getParameter("part_desc").trim();
        String part_rmk = request.getParameter("part_rmk").trim();
        String serviceable = request.getParameter("Serviceable").trim();
        //String partStatus = request.getParameter("partStatus").trim();
        String categoryType =null;
        if (request.getParameter("categoryType").equalsIgnoreCase("1Oth")) {
            categoryType = request.getParameter("categoryTypeOth");
        } else {
            categoryType = request.getParameter("categoryType");
        }
        /*****************************************************************************/
        try {
            /*****************Getting Connection*****************************/
            conn = holder.getConnection();
            java.sql.Date todayDate = methodutil.getSQLTodaysDate();
            /*****************************************************************/
            /**************************************************
             * Get User inputs and  Update in COMP_DETAIL.
             **************************************************/
            if(categoryType.equals(""))
            {
                query1 = "UPDATE CAT_PART SET P1=?,P3=?,CREATION_DATE=?,CREATOR=?,p4=? WHERE PART_NO=?";
            }else
            {
                query1 = "UPDATE CAT_PART SET P1=?,P3=?,CREATION_DATE=?,CREATOR=?,NP4=?,p4=? WHERE PART_NO=?";
            }
            
            pstmt = conn.prepareStatement(query1);
            pstmt.setString(1, desc);
            if (!part_rmk.equals("") || !part_rmk.isEmpty()) {
                pstmt.setString(2, part_rmk);
            } else {
                pstmt.setString(2, "--");
            }
            pstmt.setDate(3, todayDate);
            pstmt.setString(4, userId);
            pstmt.setString(5, serviceable);
            if(categoryType.equals(""))
            {               
                pstmt.setString(6, comp_param_list);
                
            }
	    else
            {
                pstmt.setString(6, categoryType);
                pstmt.setString(7, comp_param_list);
            }
            
            pstmt.executeUpdate();
            conn.commit();

        } catch (SQLException ex) {
            ex.printStackTrace();
            ///throw new amw_SQLExceptionCatcher("SQLException", ex);
        } catch (Exception e) {
            e.printStackTrace();
            //throw new amw_ExceptionCatcher("Exception", e);
        } finally {
            if (pstmt != null) {
                pstmt.close();
                pstmt = null;
            }
            //conn.close();
            /*************Connection Closed***************/
        }

        return mapping.findForward(SUCCESS);
    }
}
