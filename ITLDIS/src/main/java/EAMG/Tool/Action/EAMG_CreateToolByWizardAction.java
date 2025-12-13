/*
 * EAMG_CreateToolByWizardAction.java
 *
 * Created on January 2, 2009, 2:05 PM
 */
package EAMG.Tool.Action;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EAMG.common.EAMG_MethodsUtility;
import com.oreilly.servlet.MultipartRequest;

import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author divya.rani
 * @version
 */
public class EAMG_CreateToolByWizardAction extends Action {

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
        /////////getting session//////////////////////
        HttpSession session = request.getSession();
        String ecatPath = "" + session.getAttribute("ecatPATH");
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        String userId = session.getAttribute("userCode").toString();
        /////////////////////////////////////////////////////////


        //declaration of variables used.
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        Connection conn = null;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        String result = "";
        String dest = "";
        String name = "";
        String filename = "";
        String param = "";
        String toolno = "";
        String tooldesc = "";
        String tool_rmk = "";
        String categoryType = "";
        String imgchanged = "";
        String QML = "";
        String MOQ = "";
        String NDP = "";
        String[] param_values = null;
        Vector paramValueVec = new Vector();
        MultipartRequest multi = null;
        String comp_type = null;
        try {



            conn = holder.getConnection();
            File dest_folder = new File(ecatPath + "dealer/ecat_print/temp_tool_image");
            if (!dest_folder.exists()) {
                dest_folder.mkdirs();
            }

            dest = ecatPath + "dealer/ecat_print/temp_tool_image/";

            boolean isFilesAttached = true;
            try {
                multi = new MultipartRequest(request, dest, 5 * 1024 * 1024);
                /////////getting values from multipart//////////////////////
                toolno = multi.getParameter("toolno").trim().toUpperCase();
                tooldesc = multi.getParameter("desc").trim();
                tool_rmk = multi.getParameter("tool_rmk").trim();
//					 if (multi.getParameter("categoryType").trim().equalsIgnoreCase("1Oth")) {
//						  categoryType = multi.getParameter("categoryTypeOth").trim();
//
//					 } else {
//						  categoryType = multi.getParameter("categoryType").trim();
//
//					 }
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
                session.setAttribute("comp_type", comp_type);
                request.setAttribute("comp_type", comp_type);
//                QML=multi.getParameter("QML").trim();
//	        MOQ=multi.getParameter("MOQ").trim();
//                NDP=multi.getParameter("NDP").trim();
                param_values = multi.getParameterValues("param");
                imgchanged = multi.getParameter("change_img");
                /////////////////////////////////////////////////////////

            } catch (IOException e1) {
                isFilesAttached = false;
                result = "Posted Content exceeds the size limit. Please Attach the files with size less than 5 MB. ";
            }
            if (isFilesAttached == true) {
                Enumeration files = multi.getFileNames();

                while (files.hasMoreElements()) {
                    name = (String) files.nextElement();
                    filename = multi.getFilesystemName(name);
                    //System.out.println("filename:" + filename);
                }
            }
            File tool_image = new File(dest + "/" + filename);
            if (imgchanged.equals("yes")) {
                if (tool_image.exists() && tool_image.length() > 0) {
                    tool_image.renameTo(new File(ecatPath + "dealer/ecat_print/temp_tool_image/" + toolno + ".jpg"));
                } else {
                    //delete tool image
                    if (tool_image.exists()) {
                        tool_image.delete();
                    }
                    session.setAttribute("result", "Tool Image File '" + filename + "' is not valid. Hence Tool Creation Process Aborted.");
                    session.setAttribute("option", "create");
                    //conn.close();
                    return mapping.findForward("invalidimg");
                }
            }

            //checking the tool present or not in database.
            result = methodutil.isCompPresent(toolno, conn);
            //if present then return exists.
            if (!result.equals("notpresent")) {
                session.setAttribute("status", result);
                return mapping.findForward("exist");
            }
            //if not present then storing its parameter values into a Vector.
            if (param_values != null) {
                for (int i = 0; i < param_values.length; i++) {
                    param = param_values[i].trim();
                    paramValueVec.add(param);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            conn.close();
//        }


        try {
            //stmt = conn.createStatement();

            if (categoryType.equals("")) {
                categoryType = null;
            } else {
                categoryType = "'" + categoryType + "'";
            }

            if (tool_rmk.equals("")) {
                tool_rmk = null;
            } else {
                tool_rmk = "'" + tool_rmk + "'";
            }
           // rs = stmt.executeQuery("select part_no from cat_part(NOLOCK) where part_no='" + toolno + "'");
            String query = ("select part_no from cat_part(NOLOCK) where part_no='" + toolno + "'");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                //stmt.executeUpdate("Insert into cat_part (part_no, part_type,CREATION_DATE,CREATOR,CD_NO,PATCH_NO,p1,p3,np4) VALUES('" + toolno + "','" + comp_type + "','" + new java.sql.Date(new java.util.Date().getTime()) + "','" + userId + "','0','0','" + tooldesc + "'," + tool_rmk + "," + categoryType + ")");
            	query = ("Insert into cat_part (part_no, part_type,CREATION_DATE,CREATOR,CD_NO,PATCH_NO,p1,p3,np4) VALUES('" + toolno + "','" + comp_type + "','" + new java.sql.Date(new java.util.Date().getTime()) + "','" + userId + "','0','0','" + tooldesc + "'," + tool_rmk + "," + categoryType + ")");
            	stmt = conn.prepareStatement(query);
            	int result1 = stmt.executeUpdate();
                request.setAttribute("result", "Success");
            } else {
                request.setAttribute("result", "exist");
            }
            conn.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }

        session.setAttribute("toolno", toolno);
        session.setAttribute("option", "create");
        session.setAttribute("tooldesc", tooldesc);
        session.setAttribute("tool_rmk", tool_rmk);
        session.setAttribute("categoryType", categoryType);
        session.setAttribute("paramValueVec", paramValueVec);
        return mapping.findForward(SUCCESS);

    }
}
