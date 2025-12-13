/*
 * EAMG_CreateToolDataAction.java
 *
 * Created on November 14, 2008, 4:08 PM
 */

/*
File Name: amw_CreateAssemblyDataAction.java
PURPOSE: To create KIT BOM.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
14/11/08	1.0		Shivani Chauhan                 $$1 Created
 */
package EAMG.Tool.Action;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oreilly.servlet.MultipartRequest;

import jxl.Sheet;
import jxl.Workbook;
import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author shivani.chauhan
 * @version
 */
public class EAMG_CreateToolDataAction extends Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //Get session
        HttpSession session = request.getSession();
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        Connection conn = null;
        String result = "";
        String dest = "";
        String name = "";
        String filename = "";
        String imgchanged = "";
        String comp_type = "";
        String toolno = null;
        String query = null;
        PreparedStatement pstmt = null, pstmt1 = null;
        String tooldesc = null;
        String toolRemarks = null;
        String groupType = null;
        String MOQ = null;
        String QML = null;
        String NDP = null;
        String comp = null;
        String qty = null;
        try {
            conn = holder.getConnection();
            conn.setAutoCommit(false);

            //Session attributes
            String ecatPath = "" + session.getAttribute("ecatPATH");
             String user_id = session.getAttribute("userCode").toString();
            String filepath = "" + session.getAttribute("filepath");
            String comp_no = "" + session.getAttribute("comp_no");

            int cdNo = 0;//highest running CD No
            int patchNo = 0;//highest Patch No against Max CD NO
           
            //Date formatting as per database format requirement.
            java.text.DateFormat df = new java.text.SimpleDateFormat("MM-DD-yyyy");
            java.util.Date todayDate = new java.util.Date();
            java.sql.Date date = new java.sql.Date(todayDate.getTime());
            MultipartRequest multi = null;
            try {
                File dest_folder = new File(ecatPath + "dealer/ecat_print/temp_tool_image");
                if (!dest_folder.exists()) {
                    dest_folder.mkdirs();
                }
                dest = ecatPath + "dealer/ecat_print/temp_tool_image/";

                boolean isFilesAttached = true;
                try {
                    multi = new MultipartRequest(request, dest, 5 * 1024 * 1024);
                    imgchanged = multi.getParameter("change_img");
                    comp_type=multi.getParameter("comp_type");
                    //System.out.println("comp_type=="+comp_type);
                    //System.out.println("imgchanged :" + imgchanged);
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
                File kit_image = new File(dest + "/" + filename);
                if (imgchanged.equals("yes")) {
                    if (kit_image.exists() && kit_image.length() > 0) {
                        kit_image.renameTo(new File(ecatPath + "dealer/ecat_print/temp_tool_image/" + comp_no + ".jpg"));
                        //copy kit_image from temp_kit_image to kit_image
                        File destFolder = new File(ecatPath + "dealer/ecat_print/tool_image");

                        kit_image = new File(ecatPath + "dealer/ecat_print/temp_tool_image/" + comp_no + ".jpg");
                        if (kit_image.exists()) {
                            ////System.out.println("grp_image_file exists");
                            visad.install.Util.copyFile(kit_image, destFolder);
                            kit_image.delete();
                        }

                        //delete temp img folder
                        File src_folder = new File(ecatPath + "dealer/ecat_print/temp_tool_image");
                        src_folder.delete();
                    } else {
                        //delete kit image
                        if (kit_image.exists()) {
                            kit_image.delete();
                        }
                        request.setAttribute("option", "TOOL");
                        request.setAttribute("result", "Tool Image File '" + filename + "' is not valid. Hence Tool Creation Process Aborted.");
                        request.setAttribute("comp_type",comp_type);
                        return mapping.findForward(SUCCESS);

                    }
                }
            } catch (Exception exception) {
            }
            Workbook wb = Workbook.getWorkbook(new File(filepath));//Excel sheet to be read.
            int index = 0;
            
            Sheet sheet = wb.getSheet(index);
            toolno = sheet.getCell(0, 3).getContents().trim();
            tooldesc = sheet.getCell(2, 3).getContents().trim();
            toolRemarks = sheet.getCell(3, 3).getContents().trim();
            groupType = sheet.getCell(4, 3).getContents().trim();
//            MOQ = sheet.getCell(1, 4).getContents().trim();
//            QML = sheet.getCell(1, 5).getContents().trim();
//            NDP = sheet.getCell(1, 6).getContents().trim();

            query = "Insert into CAT_PART(part_no,part_type,p1,CD_NO,PATCH_NO,CREATION_DATE,p2,p3,np4,creator)  VALUES(?,?,?,?,?,?,?,?,?,?)";
            pstmt = conn.prepareStatement(query);
            pstmt.setString(1, toolno);
            pstmt.setString(2, comp_type.toUpperCase());
            pstmt.setString(3, tooldesc);
            pstmt.setInt(4, cdNo);
            pstmt.setInt(5, patchNo);
            pstmt.setDate(6, date);
            pstmt.setString(7, " ");
            pstmt.setString(8, toolRemarks);
            pstmt.setString(9, groupType);
            pstmt.setString(10, user_id);
//            pstmt.setString(7, MOQ);
//            pstmt.setString(8, QML);
//            pstmt.setString(9, NDP);
//            pstmt.setString(10, "India");
//            pstmt.setString(11, "Y");
            pstmt.addBatch();
            pstmt.executeBatch();
            
            
            query="insert into CAT_S_KIT_BOM(KIT_NO,COMPONENT,QTY,COMP_TYPE) values(?,?,?,?)";
            pstmt1=conn.prepareStatement(query);

            //insertion in S_KIT_BOM in database.
            int row = 8;
            int r = 0;

            while (!(sheet.getCell(0, row).getContents().trim()).equals("end")) {
                for (int m = 0; m < 2; m++) {
                    if (m == 0) {
                        comp = sheet.getCell(m, row).getContents().trim();
                    } else if (m == 1) {
                        qty = sheet.getCell(m, row).getContents().trim();
                    }

                }
                pstmt1.setString(1, toolno);
                pstmt1.setString(2, comp);
                pstmt1.setString(3, qty);
                pstmt1.setString(4, "PRT");
                pstmt1.addBatch();
                row++;
                r++;
                if (r == 300) {
                    pstmt1.executeBatch();
                }
            }
            pstmt1.executeBatch();
           
            conn.commit();
            request.setAttribute("option", "TOOL");
            result = "Tool '" + toolno + "' is created successfully.";
            request.setAttribute("result", result);
            request.setAttribute("comp_type",comp_type);

        } catch (Exception e) {
            e.printStackTrace();
            result = "Unable To Create Tool Please Contact System Administrator.";
            conn.rollback();
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (pstmt1 != null) {
                    pstmt1.close();
                }
                

            } catch (Exception e) {
                e.printStackTrace();
            }
            result = null;
            dest = null;
            name = null;
            filename = null;
            imgchanged = null;
            toolno = null;
            query = null;
            pstmt = null;
            pstmt1 = null;
            tooldesc = null;
            toolRemarks = null;
            groupType = null;
            MOQ = null;
            QML = null;
            NDP = null;
            comp = null;
            qty = null;
        }
        return mapping.findForward(SUCCESS);

    }
}
