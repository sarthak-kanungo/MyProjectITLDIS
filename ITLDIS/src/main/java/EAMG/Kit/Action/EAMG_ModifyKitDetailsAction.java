/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
File Name: 	AT4_ModifyKitDetailsAction.java
PURPOSE: 	TO modify Kit Details in database.
HISTORY:
DATE        	BUILD     	AUTHOR              MODIFICATIONS
17/03/09	1.0		PRAMOD.VISHWAKARMA  $$1 Created
 */
package EAMG.Kit.Action;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EAMG.common.EAMG_MethodsUtility;

import viewEcat.comEcat.ConnectionHolder;

/**
 * @author pramod.vishwakarma
 */
public class EAMG_ModifyKitDetailsAction extends org.apache.struts.action.Action {

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
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //System.out.println("AT4_ModifyKitDetailsAction");
        /************************Getting the session values**********************/
        HttpSession session = request.getSession();
        String ecatPath = "" + session.getAttribute("ecatPATH");
        String comp_param_list = (String) session.getAttribute("comp_param_list");
        String comp_type = (String) session.getAttribute("comp_type");
        session.setAttribute("assno", comp_param_list);
        session.setAttribute("option", "modify");
        session.setAttribute("comp_type", comp_type);

        /************************************************************************/
        /***********************Initializing the variables***********************/
        Connection conn = null;
        String desc = "";
        String query1 = "";
        String query2 = "";
        String result = "", filename = "";
        String dest = "";
        String MOQ = "";
        String QML = "";
        String NDP = "";
        String kitno = "";
        String kitdesc = "";
        String kit_rmk = "";
        String categoryType = "";
       // requestpartRequest request = null;
        /************************************************************************/
        /*************** Getting  the values through request parameter.**************/
        desc = request.getParameter("desc");
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        String[] param1 = null;
        /*****************************************************************************/
        try {
            /*****************Getting Connection*****************************/
            conn = holder.getConnection();
            EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
            /*****************************************************************/
            //File dest_folder = new File(ecatPath + "dealer/ecat_print/temp_kit_image");
//            if (!dest_folder.exists()) {
//                dest_folder.mkdirs();
//            }
            //dest = ecatPath + "dealer/ecat_print/temp_kit_image/";
            // System.out.println("dest:" + dest);
           // boolean isFilesAttached = true;
            try {
               // request = new requestpartRequest(request, dest, 5 * 1024 * 1024);
                /////////getting values from requestpart//////////////////////
//                desc = request.getParameter("desc");
//                MOQ = request.getParameter("MOQ");
//                QML = request.getParameter("QML");
//                NDP = request.getParameter("NDP");
                //kitno = request.getParameter("kitno").trim().toUpperCase();
                kitdesc = request.getParameter("desc").trim();
                kit_rmk = request.getParameter("kit_rmk").trim();
                if (request.getParameter("categoryType").trim().equalsIgnoreCase("1Oth")) {
                    categoryType = request.getParameter("categoryTypeOth").trim();

                } else {
                    categoryType = request.getParameter("categoryType").trim();

                }
                param1 = (String[]) request.getParameterValues("param1");
                //imgchanged = request.getParameter("change_img");
                /////////////////////////////////////////////////////////

            } catch (Exception e1) {
                //isFilesAttached = false;
                result = "Posted Content exceeds the size limit. Please Attach the files with size less than 5 MB. ";

            }
//            if (isFilesAttached == true) {
//                Enumeration files = request.getFileNames();
//                while (files.hasMoreElements()) {
//                    name = (String) files.nextElement();
//                    filename = request.getFilesystemName(name);
//                }
//            }
//            if (imgchanged.equals("yes")) {
//                File kit_image = new File(dest + "/" + filename);
//                if (kit_image.exists() && kit_image.length() > 0) {
//                    kit_image.renameTo(new File(ecatPath + "dealer/ecat_print/temp_kit_image/" + comp_param_list + ".jpg"));
//                    //kit_image.delete();
//                    //copy kit_image from temp_kit_image to group_image
//                    File destFolder = new File(ecatPath + "dealer/ecat_print/kit_image");
//
//                    kit_image = new File(ecatPath + "dealer/ecat_print/temp_kit_image/" + comp_param_list + ".jpg");
//                    if (kit_image.exists()) {
//                        ////System.out.println("grp_image_file exists");
//                        visad.install.Util.copyFile(kit_image, destFolder);
//                        kit_image.delete();
//                    }
//                    //delete temp img folder
//                    File src_folder = new File(ecatPath + "dealer/ecat_print/temp_kit_image");
//                    src_folder.delete();
//                } else {
//                    //delete group image
//                    if (kit_image.exists()) {
//                        kit_image.delete();
//                    }
//                    session.setAttribute("result", "Kit Image File '" + filename + "' is not valid. Hence Component Modification Process Aborted.");
//                    session.setAttribute("option", "modify");
//
//                    return mapping.findForward("invalidimg");
//                }
//            }

            ResultSet rs;
           // Statement stmt1 = conn.createStatement();
            PreparedStatement stmt1 = null;
            Date date = methodutil.getSQLTodaysDate();
            kitdesc = kitdesc.trim();

				if(kit_rmk.equals(""))
					kit_rmk=null;
				else
					kit_rmk="'"+kit_rmk+"'";

				if(categoryType.equals(""))
					categoryType=null;
				else
					categoryType="'"+categoryType+"'";
            /**************************************************
             * Get User inputs and  Update in S_KIT_DETAIL.
             **************************************************/
            if (comp_param_list != null) //stmt1.executeUpdate("UPDATE  PART SET p1='" + kitdesc + "',MOQ=" + MOQ + " ,NDP=" + NDP + ",QML="+ QML +" ,IMAGE='NP', CREATION_DATE='" + date + "' WHERE KIT_NO='" + comp_param_list + "'");
            {

					 //stmt1.executeUpdate("UPDATE CAT_PART SET CREATION_DATE='" + date + "', p1='" + kitdesc + "',p3=" + kit_rmk + " ,np4=" + categoryType + "  WHERE part_no='" + comp_param_list + "'");
            	String updateSQL = "UPDATE CAT_PART SET CREATION_DATE = ?, p1 = ?, p3 = ?, np4 = ? WHERE part_no = ?";
            	stmt1 = conn.prepareStatement(updateSQL);
            	stmt1.executeUpdate();
            }
            stmt1.close();
            conn.commit();

        } catch (SQLException ex) {
            ex.printStackTrace();
            //throw new amw_SQLExceptionCatcher("SQLException", ex);
        } catch (Exception e) {
            e.printStackTrace();
            //throw new amw_ExceptionCatcher("Exception", e);
        }
        session.setAttribute("kitdesc", kitdesc);
        session.setAttribute("kit_rmk", kit_rmk);
        session.setAttribute("categoryType", categoryType);
        return mapping.findForward(SUCCESS);
    }
}
