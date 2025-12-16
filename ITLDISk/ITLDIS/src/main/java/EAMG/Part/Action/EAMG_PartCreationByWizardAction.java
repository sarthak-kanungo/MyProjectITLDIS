/*
 * EAMG_PartCreationByWizardAction.java
 *
 * Created on December 07, 2011, 10:17 AM
 */
/*
File Name: 	amw_PartCreationByWizard.java
PURPOSE: 	:TO CREATION OF PARTS BY WIZARD.
HISTORY:
DATE        	BUILD     	AUTHOR    	MODIFICATIONS
07/11/11        1.0            Avinash.Pandey      $$1 Created
 */
package EAMG.Part.Action;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EAMG.common.EAMG_MethodsUtility;
import com.EAMG.common.WebConstants;

import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author divya.rani
 * @version
 */
public class EAMG_PartCreationByWizardAction extends Action {

    Logger logger = Logger.getLogger(this.getClass());
    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";

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


        Connection conn = null;
        //Statement  = null;
        PreparedStatement stmt = null;
        
		  ResultSet rs = null;
        String query = "";
        boolean result = false;
        String msg = "";

        try {
            /**************  Setting HTTP Session  ********************/
            HttpSession session = request.getSession();
            /**********************************************************/
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            conn = holder.getConnection();
            conn.setAutoCommit(false);
            EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
            Date now = new Date();
            DateFormat df1 = new SimpleDateFormat("dd-MMM-yyyy");
            String part_Type = "PRT";
            //String ServiceableP4 = "Y";
            String Seriesnp4 = " ";

            /**************  Getting Parameters from Session ***********/
            String COMP_NO = request.getParameter("partno").trim().toUpperCase();
            String COMP_DESC = request.getParameter("part_desc").trim();
            String COMP_RMK = request.getParameter("part_rmk");
            String serviciability = request.getParameter("Serviceable");
            String categoryType = null;
            if (request.getParameter("categoryType").equalsIgnoreCase("1Oth")) {
                categoryType = request.getParameter("categoryTypeOth").trim();
            } else {
                categoryType = request.getParameter("categoryType").trim();
            }


            if (COMP_RMK.equals("") || COMP_RMK.isEmpty()) {
                COMP_RMK = "--";
            } else {
                COMP_RMK = COMP_RMK;
            }
            ////System.out.println("parameter values:"+par_val[0]);
            /***********************************************************/
            /***********  Getting Attributes from Session  *************/
            String userid = (String) session.getAttribute("userCode");
            String prt_param = (String) session.getAttribute("prt_param");
            /***********************************************************/
            /***********  checking component exist or not in database  *************/

             //stmt = conn.createStatement();

            query = "select part_no from cat_part(NOLOCK) where part_no='"+COMP_NO + "'";
				//System.out.println();
            stmt = conn.prepareStatement(query);
				rs=stmt.executeQuery();

				if(rs.next())
					 result=true;

           // result = methodutil.isCompPresent(COMP_NO, conn);
            //if present then return exists.
            if (result) {
                request.setAttribute("status", "Part \""+COMP_NO+"\" already exists in database.");
                conn.rollback();
                //conn.close();
                return mapping.findForward("exist");
            }
            /*************************************************************
             *  Inserting the COMP_DETAIL values in the DataBase Starts.
             *************************************************************/
           
            int x = 0;


            if (categoryType.equals("")) {
                query = "Insert into cat_part (part_no,part_type,CREATION_DATE,CREATOR,CD_NO,PATCH_NO, p1, p3, p4, np1, np2) values('" + COMP_NO + "','" + part_Type + "','" + methodutil.getSQLTodaysDate() + "','" + userid + "','1','0','" + COMP_DESC + "','" + COMP_RMK + "','" + serviciability + "','1','1')";
                stmt = conn.prepareStatement(query);
            } else {
                query = "Insert into cat_part (part_no,part_type,CREATION_DATE,CREATOR,CD_NO,PATCH_NO, p1, p3, p4, np4, np1, np2) values('" + COMP_NO + "','" + part_Type + "','" + methodutil.getSQLTodaysDate() + "','" + userid + "','1','0','" + COMP_DESC + "','" + COMP_RMK + "','" + serviciability + "','" + categoryType + "','1','1')";
                stmt = conn.prepareStatement(query);
            }
            //System.out.println("query PART::" + query);
            x = stmt.executeUpdate();
            if (x == 1) {
                msg = "SUCCESS";
            } else {
                msg = "Unable to Create Part, Please Contact to System Administrator.";
            }
            ////System.out.println("paramhead :"+paramhead);
            stmt.close();
            conn.commit();

            /*************************************************************
             *  Inserting the COMP_DETAIL values in the DataBase Ends.
             *************************************************************/
        } catch (Exception ae) {
            msg = "Unable to Create Part, Please Contact to System Administrator.";
            conn.rollback();
            ae.printStackTrace();
            return mapping.findForward(FAILURE);
        } finally {
            try {

                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }

            } catch (Exception e) {
                logger.error(WebConstants.fLogException, e);
            }
        }
        request.setAttribute("msg", msg);
       // System.out.println("msg" + msg);
        return mapping.findForward(SUCCESS);

    }
}
