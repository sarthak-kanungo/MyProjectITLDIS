/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
File Name: 	AT4_UpdateCompByExcelAction.java
PURPOSE: 	TO update Components into the database by Excel.
HISTORY:
DATE        	BUILD     	AUTHOR              MODIFICATIONS
02/01/09	1.0		PRAMOD.VISHWAKARMA  $$1 Created
 */
package EAMG.Part.Action;


import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import jxl.Sheet;
import jxl.Workbook;
import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author pramod.vishwakarma
 */
public class EAMG_UpdateCompByExcelAction extends org.apache.struts.action.Action {

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
        ////////////////////getting the values from session//////////////////////
        HttpSession session = request.getSession();
        File xlsfile = (File) session.getAttribute("xlsfile");
        /////////////////////////////////////////////////////////////////////////
        ////////////////////getting the values from request//////////////////////
        String update_desc = request.getParameter("descheckbox");
        String update_partRemarks = request.getParameter("remarkcheckbox");
        String categorycheckbox = request.getParameter("categorycheckbox");
        //String update_serviciablity = request.getParameter("serviciablitycheckbox");
        //String update_status = request.getParameter("statuscheckbox");
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        /////////////////////////////////////////////////////////////////////////
        //declaration of variables used.
        Workbook workbook1 = null;
        Sheet sheet = null;
        Connection conn = null;
        ResultSet rs = null;
        PreparedStatement pstmt = null;
        //Statement st1 = null, st2 = null;
        PreparedStatement st1 = null, st2 = null;
        int row = 3, column = 0;
        String compno = "";
        String compdesc = "";
        String partrmk = "";
        String categoryType = "";
        String updatequery = "";
        Vector unmodifyListVec = new Vector();
        try {
            conn = holder.getConnection();
            conn.setAutoCommit(false);
            //getting the workbook.
            workbook1 = Workbook.getWorkbook(xlsfile);
            //getting the sheet.
            sheet = workbook1.getSheet(0);
            //st1 = conn.createStatement();
            //st2 = conn.createStatement();

				pstmt=conn.prepareStatement("Select * from CAT_PART(NOLOCK) where part_no=?");
            //loop till 'end' is encountered.
            while (!(sheet.getCell(0, row).getContents().trim().equalsIgnoreCase("End"))) {
                column = 0;
                //getting component number.
                compno = sheet.getCell(column, row).getContents().trim();
                compno = compno.toUpperCase();
                column++;
                //getting component description.
                compdesc = sheet.getCell(column, row).getContents().trim();
                column++;
                partrmk = sheet.getCell(column, row).getContents().trim();
                column++;
                categoryType = sheet.getCell(column, row).getContents().trim();
                column++;
                //serviciability = sheet.getCell(column, row).getContents().trim();
                //column++;
                //partStatus = sheet.getCell(column, row).getContents().trim();
                //column++;
                //if component exists.
					 pstmt.setString(1,compno);
                rs = pstmt.executeQuery();
                if (rs.next()) {

                    //update component description.

						  if(categorycheckbox!=null && update_desc != null && update_partRemarks != null)
								st2.executeUpdate("update CAT_PART set p1='" + compdesc + "',p3='" + partrmk + "',np4='"+categoryType+"' where part_no='" + compno + "'");
                } //if component not exist in database.
                else {
                    if (!unmodifyListVec.contains(compno)) {
                        unmodifyListVec.add(compno);
                    }
                }
                row++;

            }
            session.setAttribute("unmodifyListVec", unmodifyListVec);
            conn.commit();
        } catch (Exception exception) {
            conn.rollback();
            exception.printStackTrace();
        } finally {
            //conn.close();
        }
        return mapping.findForward(SUCCESS);
    }
}
