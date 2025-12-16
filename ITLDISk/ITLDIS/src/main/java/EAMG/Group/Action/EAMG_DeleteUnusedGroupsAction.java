/*
 * EAMG_DeleteUnusedGroupsAction.java
 *
 * Created on JAN 16, 2012, 12:39 PM
 */
package EAMG.Group.Action;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import viewEcat.comEcat.ConnectionHolder;
import viewEcat.comEcat.PageTemplate;

/**
 *
 * @author anita.dhar
 * @version
 */
public class EAMG_DeleteUnusedGroupsAction extends Action {

	 Connection conn = null;
	 private final static String SUCCESS = "success";

	 public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws Exception {
		  String result = "";
		  int counter=0;
		  HttpSession session = request.getSession();
		  String[] tempVar = request.getParameterValues("attachedlist");
		  String ecatPath = "" + request.getSession().getAttribute("ecatPath");
		  ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
		  Statement stmt = null;
		  //PreparedStatement stmt = null;
		  try {
				conn = holder.getConnection();

				if (tempVar != null) {
					 conn.setAutoCommit(false);
					 stmt = conn.createStatement();
					 //System.out.println("tempVar.length:"+tempVar.length);
					 for (int i = 0; i < tempVar.length; i++) {

						  counter++;

						  stmt.addBatch("DELETE FROM CAT_GROUP_KIT_DETAIL WHERE GRP_KIT_NO='" + tempVar[i] + "'");
						  stmt.addBatch("DELETE FROM CAT_GROUP_KIT_BOM WHERE GRP_KIT_NO='" + tempVar[i] + "'");
//						  stmt.addBatch("DELETE FROM REF_COORD WHERE GROUP_NO='" + tempVar[i] + "'");
						 

						  File grp_image_file = new File(ecatPath + "dealer/ecat_print/group_image/" + tempVar[i] + ".jpg");
						  if (grp_image_file.exists()) {
								grp_image_file.delete();
						  }

						  if(counter==200)
						  {
								stmt.executeBatch();
								counter=0;
						  }
					 }
					 stmt.executeBatch();
					 conn.commit();
					 request.setAttribute("tempVar", tempVar);
				}

				result = PageTemplate.GROUP+"s Deleted Successfully.";
		  } catch (SQLException ex) {
				conn.rollback();
				ex.printStackTrace();
				result = "Unable to Delete "+PageTemplate.GROUP+"s, Please contact System Administrator.";
		  } catch (Exception ex) {
				conn.rollback();
				ex.printStackTrace();
				result = "Unable to Delete "+PageTemplate.GROUP+"s, Please contact System Administrator.";
		  } finally {
				//conn.close();
		  }
		  request.setAttribute("result", result);
		  return mapping.findForward("success");
	 }
}



