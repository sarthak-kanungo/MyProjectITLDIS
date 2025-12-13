/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EAMG.Model.Action;

import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

/**
 *
 * @author manish.kishore
 */


import EAMG.Model.DAO.EAMG_ModelDAO;
import dbConnection.dbConnection;

/**
 *
 * @author manish.kishore
 * public class
 */
public class AssignComponentToModelAction extends org.apache.struts.action.Action {

	 /* forward name="success" path="" */
	 private static final String SUCCESS = "success";
	 private static final String SHOWMODEL = "showmodel";
	 private static final String SHOWMODELAJAX = "showmodelajax";
	 //private static final String  FAIL="fail";
	 private Logger logger = Logger.getLogger(this.getClass());

	 @Override
	 public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {
		  String forward = null;
		  String option = null;
		  Connection conn = null;
		  ArrayList dataList = null;
		  String model_no=null;
		  int inserStatus = 0;

		  try {

				conn = new dbConnection().getDbConnection();
				if (conn == null) {
					 logger.error("Failed to Get Connection");
					 ///forward = false;
				} else {
					 EAMG_Variant_AggregatesActionForm modelBean = (EAMG_Variant_AggregatesActionForm) form;//dataList

					 option = modelBean.getOption();
					 EAMG_ModelDAO modelDao = new EAMG_ModelDAO();
					 String attachedArr[]=request.getParameterValues("attachedlist");
					 //int forward_main = 0;

					 if (option.equals("initModel")) {
						  dataList = modelDao.getModel(conn);
						  request.setAttribute("modelList", dataList);//applicationTypeList
						  dataList = modelDao.getModelComp(conn,"all");
						  request.setAttribute("compList", dataList);
						  forward = SHOWMODEL;

					 }
					 else if (option.equals("ajax")) {//option=ajax

						  model_no=modelBean.getModel_no();
						  //System.out.println()
						  dataList = modelDao.getModelComp(conn,model_no);

						  request.setAttribute("dataList", dataList);//applicationTypeList
						  forward = SHOWMODELAJAX;

					 }
					 else if (option.equals("insertValues")) {
						  inserStatus = modelDao.insertmodelVsComp(conn, modelBean);
                    request.setAttribute("heading","Assign Component To Model");
						  if (inserStatus == 1) {
								request.setAttribute("result", "SUCCESS");
								request.setAttribute("show_message", "Component Assigned Successfully.");
								request.setAttribute("optionLinkName", "Assign More");
								request.setAttribute("optionLink", "YES");
								request.setAttribute("optionLinkURL", "/Assign_ComponentToModelAction.do?option=initModel");
								forward = SUCCESS;
						  } else {
								request.setAttribute("result", "FAILURE");
								request.setAttribute("show_message", "Unable To Assign Component,Please contact System Administrator.");
								forward = SUCCESS;
						  }

					 } 


				}
		  } catch (Exception e) {
				e.printStackTrace();
				logger.error("Caught in Exception", e);
		  } finally {
				try {
					 if (conn != null) {
						  conn.close();
						  conn = null;
					 }
				} catch (Exception e) {
					 e.printStackTrace();
					 logger.error("Caught in Final Exception", e);
				}
		  }
		  return mapping.findForward(forward);
	 }
}

