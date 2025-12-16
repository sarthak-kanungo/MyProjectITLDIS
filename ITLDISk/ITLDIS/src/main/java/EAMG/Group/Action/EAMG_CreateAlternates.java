/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
File Name: 	EAMG_CreateAlternates.java
PURPOSE: 	TO alternates of Components Of Group in the DATABASE.
HISTORY:
DATE        	BUILD     	AUTHOR              MODIFICATIONS
24/02/09	1.0		PRAMOD.VISHWAKARMA  $$1 Created
 */
package EAMG.Group.Action;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EAMG.common.EAMG_MethodsUtility;

import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author pramod.vishwakarma
 */
public class EAMG_CreateAlternates extends org.apache.struts.action.Action {

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
        HttpSession session = request.getSession(true);
        String context = request.getContextPath();
        String[] components = request.getParameterValues("attachedlist");
        String compno = request.getParameter("compno_select");
        String grpno = request.getParameter("group_number");
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
//        System.out.println("compno :" + compno);
//        System.out.println("grpno :" + grpno);
        String result = "";
        Connection conn = null;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        PreparedStatement ps = null;
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        Date date = methodutil.getSQLTodaysDate();
        try {
            String[] comp = null;
            Vector partVec = new Vector();

            if (components != null) {
                for (int i = 0; i < components.length; i++) {
                    comp = components[i].split(" ");
                    if (comp[1].equals("(Part)")) {
                        partVec.add(comp[0]);
                    }
                }
            }
//            System.out.println("partVec :" + partVec);
            conn = holder.getConnection();
            ResultSet rs;
           // stmt = conn.createStatement();
            //rs = stmt.executeQuery("SELECT * FROM CAT_GROUP_KIT_BOM where COMPONENT='" + compno + "' and GRP_KIT_NO='" + grpno + "'");
            String sqlQuery = ("SELECT * FROM CAT_GROUP_KIT_BOM(NOLOCK) where COMPONENT='" + compno + "' and GRP_KIT_NO='" + grpno + "'");
            stmt = conn.prepareStatement(sqlQuery);
            rs = stmt.executeQuery();
            if (!rs.next()) {
                result = "Component '" + compno + "' does not exist in Group BOM of Group '" + grpno + "'.";
                result += "<br><font class='red-for-temmplate-link' ><a href=\"javascript:location.href='"+context+"'/authJSP/EAMG_attach_alternate.jsp';\">Try Again</a></font>";
                request.setAttribute("result", result);
                return mapping.findForward(SUCCESS);
            }
            rs.close();
            //insertion in S_KIT_BOM in database.
            String query = "insert into CAT_ALTERNATE values(?,?,?,?,?,?)";
            ps = conn.prepareStatement(query);
            for (int i = 0; i < partVec.size(); i++) {
                ps.setString(1, grpno);
                ps.setString(2, compno);
                ps.setString(3, "" + partVec.elementAt(i));
                ps.setInt(4, 0);
                ps.setDate(5, date);
                ps.setString(6, "PRT");
                //ps.setString(7,"0");
                ps.executeUpdate();
            }
            conn.commit();// commit connection
            result = "Alternate of Component '" + compno + "' created successfully.";
        } catch (Exception exception) {
            exception.printStackTrace();
            //System.out.println("exception :"+exception);
            result = "Alternate of Component '" + compno + "' can not be created.";
            result += "<br><font class='red-for-temmplate-link' ><a href=\"javascript:location.href='"+context+"'/authJSP/EAMG_Group/EAMG_attach_alternate.jsp';\">Try Again</a></font>";
            request.setAttribute("result", result);
            return mapping.findForward(SUCCESS);
        } finally {
            try {
//            if(conn!=null)
//              conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        result += "<br><font class='red-for-temmplate-link' ><a href=\"javascript:location.href='"+context+"'/authJSP/EAMG_Group/EAMG_attach_alternate.jsp';\">Create More</a></font>";
        request.setAttribute("result", result);
        return mapping.findForward(SUCCESS);

    }
}
