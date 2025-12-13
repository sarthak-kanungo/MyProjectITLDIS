/*
File Name   : 	AT4_DeleteUnusedKitsAction.java
PURPOSE     : 	TO DELETE UNUSED KITS FROM DATABASE.
HISTORY     :
DATE                    BUILD     AUTHOR    		MODIFICATIONS
December 22, 2008	1.0       SURENDER KUMAR        $$1 Creation
 */
package EAMG.Kit.Action;

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

import viewEcat.comEcat.ConnectionHolder;

public class EAMG_DeleteUnusedKitsAction extends Action {

	 private final static String SUCCESS = "success";

	 @Override
	 public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {

		  /*****************Getting  the values through request parameter.***************/
		  HttpSession session = request.getSession();
		  String[] deletedarr = (String[]) request.getParameterValues("unusedkitlist");
		  ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
		  /***************************************************************************/
		  /*************************Initializing the variables*************************/
		  Connection conn = null;
		  String query = "";
		  PreparedStatement ptmt = null,ptmt1;
		  /**************************************************************************/
		  try {
				/*****************Getting Connection**********************/
				conn = holder.getConnection();
				/*********************************************************/
				/*********************************
				 * Deletion of UnusedKits Starts.
				 *********************************/
				ptmt = conn.prepareStatement("DELETE FROM CAT_part(NOLOCK) WHERE part_NO=?");
				ptmt1 = conn.prepareStatement("DELETE FROM CAT_s_kit_bom(NOLOCK) WHERE kit_no=?");
				for (int i = 0; i < deletedarr.length; i++) {
					 
					 /*****************************************
					  * Deleteing the values from S_KIT_DETAIL.
					  *****************************************/

					 ptmt.setString(1,deletedarr[i]);
					 ptmt1.setString(1,deletedarr[i]);
					 //query = "DELETE FROM part WHERE part_NO='" + deletedarr[i] + "'";
					//System.out.println(query);
					 ptmt.addBatch();
					 ptmt1.addBatch();

					 if (i % 200 == 0) {
						  ptmt.executeBatch();
						  ptmt1.executeBatch();
					 }

				}
				ptmt.executeBatch();
				ptmt1.executeBatch();
				conn.commit();
				ptmt.close();
				ptmt1.close();
		  } catch (SQLException e) {
				e.printStackTrace();

		  } catch (Exception e) {
				e.printStackTrace();

		  }

		  /**************Setting variables in session***********************/
		  request.setAttribute("deletedarr", deletedarr);
		  /*****************************************************************/
		  return mapping.findForward(SUCCESS);
	 }
}
