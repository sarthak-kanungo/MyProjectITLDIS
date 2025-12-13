/*
 * EAMG_ToolInsertionByWzAction.java
 *
 * Created on Nov 16, 2011, 4:51 PM
 */
/*
File Name: EAMG_ToolInsertionByWzAction.java
PURPOSE: 	:TO CREATE KITS BY WIZARD.
HISTORY:
DATE        	BUILD     	AUTHOR    	MODIFICATIONS
16/11/11       1.0            Avinash.Pandey      $$1 Created
 */
package EAMG.Tool.Action;

import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EAMG.common.EAMG_MethodsUtility;

import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author avinash.pandey
 * @version
 */
public class EAMG_ToolInsertionByWzAction extends Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";

    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //System.out.println("In Tool Insertion.");
        ////////////////////getting the values from session//////////////////////
        HttpSession session = request.getSession();
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        String ecatPath = "" + session.getAttribute("ecatPATH");
        String user_id = session.getAttribute("userCode").toString();
        String option1 = "" + session.getAttribute("option");
        String comp_type = "" + request.getParameter("comp_type");
        if (option1 == null) {
            option1 = "create";
        }
        String categoryType = "";
        String toolno = "" + session.getAttribute("toolno");
        toolno = toolno.trim();
        String tooldesc = "" + session.getAttribute("tooldesc");
        tooldesc = tooldesc.trim();
        String toolRemrks = "" + session.getAttribute("tool_rmk");
        toolRemrks = toolRemrks.trim();
        if (option1.equals("create")) {
            categoryType = session.getAttribute("categoryType").toString();
        }
//        String user_id = "" + session.getAttribute("userCode");
        Vector partVec = (Vector) session.getAttribute("partVec");
        Vector assemblyVec = (Vector) session.getAttribute("assemblyVec");
        Vector paramValueVec = (Vector) session.getAttribute("paramValueVec");
        Vector toolVec = (Vector) session.getAttribute("toolVec");
        Vector paramVec = (Vector) session.getAttribute("param");
//        String MOQ= ""+session.getAttribute("MOQ");
//        String QML= ""+session.getAttribute("QML");
//        String NDP= ""+session.getAttribute("NDP");
        /////////////////////////////////////////////////////////////////////////
        ////////////////////getting the values from request//////////////////////
        String[] qty = request.getParameterValues("qty");
        /////////////////////////////////////////////////////////////////////////

        //declaration of variables used.
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        String param = "";
        String result = "";
        String grpno = "";
        int cnt = 0, param_id = 0;
        String option = "";
        int cd_no = 0;
        int patch_no = 0;
        int res = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        String query = "";
        try {
            conn = holder.getConnection();
            conn.setAutoCommit(false);
            if (option1.equals("modify")) {
                query = "delete from CAT_S_KIT_BOM(NOLOCK) where KIT_NO='" + toolno + "'";
                ps = conn.prepareStatement(query);
                ps.execute();
                ps.close();
                //insertion in S_KIT_BOM in database.
                query = "insert into CAT_S_KIT_BOM values(?,?,?,?)";
                ps = conn.prepareStatement(query);
                for (int i = 0; i < partVec.size(); i++) {
                    ps.setString(1, toolno);
                    ps.setString(2, "" + partVec.elementAt(i));
                    ps.setString(3, qty[i]);
                    ps.setString(4, "PRT");
                    res = ps.executeUpdate();
                }
                cnt = partVec.size();
                for (int i = 0; i < assemblyVec.size(); i++) {
                    ps.setString(1, toolno);
                    ps.setString(2, "" + assemblyVec.elementAt(i));
                    ps.setString(3, qty[cnt + i]);
                    ps.setString(4, "ASM");
                    res = ps.executeUpdate();
                }
                cnt = cnt + assemblyVec.size();
                for (int i = 0; i < toolVec.size(); i++) {
                    ps.setString(1, toolno);
                    ps.setString(2, "" + toolVec.elementAt(i));
                    ps.setString(3, qty[cnt + i]);
                    ps.setString(4, "TOOL");
                    res = ps.executeUpdate();
                }
                ps.close();
                result = "Tool '" + toolno + "' is updated successfully.";

            } else {
                //getting CD Number.
                cd_no = methodutil.getCD_No(conn);
                //getting PATCH Number.
                patch_no = methodutil.getPatch_No(conn);
                //getting DATE.
                Date date = methodutil.getSQLTodaysDate();
                //insertion in COMP_DETAIL in database.
                query = "Insert into CAT_PART(part_no,part_type,p1,CD_NO,PATCH_NO,CREATION_DATE,p2,p3,np4,creator)  VALUES(?,?,?,?,?,?,?,?,?,?)";
                ps = conn.prepareStatement(query);
                ps.setString(1, toolno);
                ps.setString(2, "TOOL");
                ps.setString(3, tooldesc);
                ps.setInt(4, cd_no);
                ps.setInt(5, patch_no);
                ps.setDate(6, date);
                ps.setString(7, " ");
                if (!toolRemrks.equals("") || !toolRemrks.isEmpty()) {
                    ps.setString(8, toolRemrks);
                } else {
                    ps.setString(8, " ");
                }
                if (!categoryType.equals("") || !categoryType.isEmpty()) {
                ps.setString(9, categoryType);
                }else{
                   ps.setString(9, " ");
                }
                ps.setString(10, user_id);
                ps.executeUpdate();
                ps.close();

                //insertion in S_KIT_BOM in database.
                query = "insert into CAT_S_KIT_BOM values(?,?,?,?)";
                ps = conn.prepareStatement(query);
                for (int i = 0; i < partVec.size(); i++) {
                    ps.setString(1, toolno);
                    ps.setString(2, "" + partVec.elementAt(i));
                    ps.setString(3, qty[i]);
                    ps.setString(4, "PRT");
                    ps.executeUpdate();
                }
                cnt = partVec.size();
                for (int i = 0; i < assemblyVec.size(); i++) {
                    ps.setString(1, toolno);
                    ps.setString(2, "" + assemblyVec.elementAt(i));
                    ps.setString(3, qty[cnt + i]);
                    ps.setString(4, "ASM");
                    ps.executeUpdate();
                }
                cnt = cnt + assemblyVec.size();
                for (int i = 0; i < toolVec.size(); i++) {
                    ps.setString(1, toolno);
                    ps.setString(2, "" + toolVec.elementAt(i));
                    ps.setString(3, qty[cnt + i]);
                    ps.setString(4, "TOOL");
                    ps.executeUpdate();
                }
                ps.close();
                result = "Tool '" + toolno + "' is created successfully.";

            }

            conn.commit();


            //copy tool_image from temp_kit_image to group_image
            File destFolder = new File(ecatPath + "dealer/ecat_print/tool_image");

            File tool_image = new File(ecatPath + "dealer/ecat_print/temp_tool_image/" + toolno + ".jpg");
            if (tool_image.exists()) {
                ////System.out.println("grp_image_file exists");
                visad.install.Util.copyFile(tool_image, destFolder);
                tool_image.delete();
            }

            //delete temp img folder
            File src_folder = new File(ecatPath + "dealer/ecat_print/temp_tool_image");
            src_folder.delete();


        } catch (Exception e) {
            result = "Tool '" + toolno + "' can not be created due to some database problem.";
            conn.rollback();
            e.printStackTrace();
        } finally {
            option = "TOOL";
            request.setAttribute("result", result);
            request.setAttribute("option", option);
            //conn.close();                                // Closing Connection
        }
        return mapping.findForward(SUCCESS);
    }
}
