/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
File Name: 	EAMG_ModifyToolDetailsAction.java
PURPOSE: 	TO modify Tool Details in database.
HISTORY:

 */
package EAMG.Tool.Action;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EAMG.common.EAMG_MethodsUtility;
import com.oreilly.servlet.MultipartRequest;

import viewEcat.comEcat.ConnectionHolder;

/**
 * @author pramod.vishwakarma
 */
public class EAMG_ModifyToolDetailsAction extends org.apache.struts.action.Action {

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
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        /************************Getting the session values**********************/
        HttpSession session = request.getSession();
        String ecatPath = "" + session.getAttribute("ecatPATH");
        String comp_param_list = (String) session.getAttribute("comp_param_list");
        String comp_type = null;
        session.setAttribute("assno", comp_param_list);
        session.setAttribute("option", "modify");


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
        String toolno = "";
        String tooldesc = "";
        String tool_rmk = "";
        String categoryType = "";
        String imgchanged = "";
        String name = "";
        MultipartRequest multi = null;
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
            File dest_folder = new File(ecatPath + "dealer/ecat_print/temp_tool_image");
            if (!dest_folder.exists()) {
                dest_folder.mkdirs();
            }
            dest = ecatPath + "dealer/ecat_print/temp_tool_image/";
            // System.out.println("dest:" + dest);
            boolean isFilesAttached = true;
            try {
                multi = new MultipartRequest(request, dest, 5 * 1024 * 1024);
                /////////getting values from multipart//////////////////////
//                desc = multi.getParameter("desc");
//                MOQ = multi.getParameter("MOQ");
//                QML = multi.getParameter("QML");
//                NDP = multi.getParameter("NDP");
                //toolno = multi.getParameter("toolno").trim().toUpperCase();
                tooldesc = multi.getParameter("desc").trim();
                tool_rmk = multi.getParameter("tool_rmk").trim();
//                if (multi.getParameter("categoryType").trim().equalsIgnoreCase("1Oth")) {
//                    categoryType = multi.getParameter("categoryTypeOth").trim();
//
//                } else {
//                    categoryType = multi.getParameter("categoryType").trim();
//
//                }
                categoryType = multi.getParameter("categoryType");
                if (categoryType != null) {
                    if (categoryType.trim().equalsIgnoreCase("1Oth")) {
                        categoryType = multi.getParameter("categoryTypeOth").trim();

                    } else {
                        categoryType = multi.getParameter("categoryType").trim();

                    }
                } else {
                    categoryType = "";
                }

                comp_type = multi.getParameter("comp_type");
                param1 = (String[]) multi.getParameterValues("param1");
                imgchanged = multi.getParameter("change_img");
                /////////////////////////////////////////////////////////

            } catch (IOException e1) {
                isFilesAttached = false;
                result = "Posted Content exceeds the size limit. Please Attach the files with size less than 5 MB. ";

            }

            request.setAttribute("comp_type", comp_type);

            if (isFilesAttached == true) {
                Enumeration files = multi.getFileNames();
                while (files.hasMoreElements()) {
                    name = (String) files.nextElement();
                    filename = multi.getFilesystemName(name);
                }
            }
            if (imgchanged.equals("yes")) {
                File tool_image = new File(dest + "/" + filename);
                if (tool_image.exists() && tool_image.length() > 0) {
                    tool_image.renameTo(new File(ecatPath + "dealer/ecat_print/temp_tool_image/" + comp_param_list + ".jpg"));
                    //tool_image.delete();
                    //copy tool_image from temp_tool_image to group_image
                    File destFolder = new File(ecatPath + "dealer/ecat_print/tool_image");

                    tool_image = new File(ecatPath + "dealer/ecat_print/temp_tool_image/" + comp_param_list + ".jpg");
                    if (tool_image.exists()) {
                        ////System.out.println("grp_image_file exists");
                        visad.install.Util.copyFile(tool_image, destFolder);
                        tool_image.delete();
                    }
                    //delete temp img folder
                    File src_folder = new File(ecatPath + "dealer/ecat_print/temp_tool_image");
                    src_folder.delete();
                } else {
                    //delete group image
                    if (tool_image.exists()) {
                        tool_image.delete();
                    }
                    session.setAttribute("result", "Tool Image File '" + filename + "' is not valid. Hence Component Modification Process Aborted.");
                    session.setAttribute("option", "modify");

                    return mapping.findForward("invalidimg");
                }
            }

            ResultSet rs;
           // Statement stmt1 = conn.createStatement();
            PreparedStatement stmt1 = null;
            Date date = methodutil.getSQLTodaysDate();
            tooldesc = tooldesc.trim();

            if (tool_rmk.equals("")) {
                tool_rmk = null;
            } else {
                tool_rmk = "'" + tool_rmk + "'";
            }

            if (categoryType.equals("")) {
                categoryType = null;
            } else {
                categoryType = "'" + categoryType + "'";
            }

            /**************************************************
             * Get User inputs and  Update in S_KIT_DETAIL.
             **************************************************/
            if (comp_param_list != null) //stmt1.executeUpdate("UPDATE  PART SET p1='" + kitdesc + "',MOQ=" + MOQ + " ,NDP=" + NDP + ",QML="+ QML +" ,IMAGE='NP', CREATION_DATE='" + date + "' WHERE KIT_NO='" + comp_param_list + "'");
            {
                //stmt1.executeUpdate("UPDATE CAT_PART SET CREATION_DATE='" + date + "', p1='" + tooldesc + "',p3=" + tool_rmk + " ,np4=" + categoryType + "  WHERE part_no='" + comp_param_list + "'");
            	String updateQuery = ("UPDATE CAT_PART SET CREATION_DATE='" + date + "', p1='" + tooldesc + "',p3=" + tool_rmk + " ,np4=" + categoryType + "  WHERE part_no='" + comp_param_list + "'");
            	stmt1 = conn.prepareStatement(updateQuery);
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
        session.setAttribute("tooldesc", tooldesc);
        session.setAttribute("tool_rmk", tool_rmk);
        session.setAttribute("categoryType", categoryType);
        return mapping.findForward(SUCCESS);
    }
}
