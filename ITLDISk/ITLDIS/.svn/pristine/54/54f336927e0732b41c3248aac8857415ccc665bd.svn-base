/*
 * EAMG_DeleteModelAction.java
 *
 * Created on November 09, 2011, 3:35 PM
 */

/*
File Name: 	amw_DeleteModelcAction.java
PURPOSE: 	:TO Delete the Model from the  DATABASE.
HISTORY:
DATE        	BUILD     	AUTHOR              MODIFICATIONS
08/11/11	1.0		avinash.pandey  $$1 Created
 */
package EAMG.Model.Action;

import java.io.File;
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
import viewEcat.comEcat.PageTemplate;

/**
 *
 * @author Avinash.Pandey
 * @version
 */
public class EAMG_DeleteModelAction extends Action {

	 Connection conn = null;
	 /* forward name="success" path="" */
	 private final static String SUCCESS = "success";

	 /**
	  * This is the action called from the Struts framework.
	  * @param mapping The ActionMapping used to select this instance.
	  * @param form The optional ActionForm bean for this request.
	  * @param request The HTTP Request we are processing.
	  * @param response The HTTP Response we are processing.
	  * @throws java.lang.Exception
	  * @return
	  */
	 public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response) throws Exception {
		  ///////////////////////////// CREATING REQUEST SESSION VARIABLES /////////////////////////////
		  ////////////////////////////////////////////////////////////////////////////////
		  HttpSession hs = request.getSession();
		  String tempVarArr[] = request.getParameterValues("model_param2");
		  String server_name = (String) hs.getValue("server_name");
		  String ecatPath = (String) hs.getValue("ecatPATH");
		  String mainURL = (String) hs.getValue("mainURL");
		  PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
		  String modelNo = object_pageTemplate.MODEL_NO;
		  ConnectionHolder holder = (ConnectionHolder) hs.getValue("servletapp.connection");

		  // Declaring the variables
		  String result = "";
		  try {
				// To get the connection.
				conn = holder.getConnection();
				PreparedStatement ps = null, ps1 = null, ps2 = null;
				//check the parameters till the last values and delete if it is found
				int counter = 0;
				if (tempVarArr != null) {
					 ps = conn.prepareStatement("DELETE FROM CAT_MODELS WHERE MODEL_NO=?");
					 ps1 = conn.prepareStatement("DELETE FROM CAT_MODEL_CLASSIFICATION WHERE MODEL_NO=?");
					 ps2 = conn.prepareStatement("DELETE FROM CAT_MODEL_GROUP WHERE MODEL_NO=?");

					 for (String tempVar : tempVarArr) {

						  ps.setString(1,tempVar);
						  ps.addBatch();

						  ps1.setString(1,tempVar);
						  ps1.addBatch();

						  ps2.setString(1,tempVar);
						  ps2.addBatch();

						  File model_image_file = new File(ecatPath + "dealer/ecat_print/model_images/" + tempVar + ".jpg");
						  if (model_image_file.exists()) {
								model_image_file.delete();
						  }
						  counter++;
						  if (counter % 200 == 0) {
								ps.executeBatch();
								ps1.executeBatch();
								ps2.executeBatch();

						  }
					 }

				}
				ps.executeBatch();
				ps1.executeBatch();
				ps2.executeBatch();
				ps.close();
				ps1.close();
				ps2.close();


				conn.commit();
				result = "" + modelNo + "s Deleted Successfully.";
				request.setAttribute("result", result);
		  } //throwing the exceptions if found
		  catch (SQLException ex) {
				ex.printStackTrace();
				result = "SQL Exception occured";
		  } catch (Exception ex) {
				ex.printStackTrace();
				result = "Exception occured";
		  } finally {
				//closing the connection
				//conn.close();
				request.setAttribute("option", "delete");
				request.setAttribute("result", result);
				// forwarding the result to the sucess page
				return mapping.findForward("success");
		  }

	 }
}
