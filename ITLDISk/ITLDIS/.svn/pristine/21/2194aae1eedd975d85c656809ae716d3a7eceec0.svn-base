/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Part.Action;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oreilly.servlet.MultipartRequest;

import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author vijay.mishra
 */
public class EAMG_PriceListAction extends Action {

    private final static String SUCCESS = "success";
    private final static String FAILURE = "fail";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ////////////////////getting the values from session//////////////////
        HttpSession session = request.getSession();
        String ecatPath = session.getAttribute("ecatPATH").toString();
        String user_id = session.getAttribute("userCode").toString();
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        String context = request.getContextPath();
        ////////////////////////////////////////////////////////////////////

        //Declaration of variables used
        String result = "";
        Connection conn = null;
        File dest_folder = null;
        String dest = "";
        String filename = "";
        String name = "";
        Enumeration files = null;
        boolean isFilesAttached = true;
        MultipartRequest multi = null;
        String xls_validate_result = "";
        EAMG_InsertPriceListParameters insertpart = null;
        File xlsfile = null;
        try {
            //for getting excel file and uploading it onto server.
            dest_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
            if (!dest_folder.exists()) {
                dest_folder.mkdir();
            }
            dest = ecatPath + "dealer/tempExcels/" + user_id;
            ////System.out.println("dest:"+dest);

            try {
                multi = new MultipartRequest(request, dest, 5 * 1024 * 1024);
                ////System.out.println("multi:"+multi);
            } catch (IOException e1) {
                isFilesAttached = false;
                result = "Posted Content exceeds the size limit. Please Attach the files with size less than 5 MB. ";
                request.setAttribute("result", result);
                return mapping.findForward(FAILURE);
            }

            if (isFilesAttached == true) {
                files = multi.getFileNames();
                while (files.hasMoreElements()) {
                    name = (String) files.nextElement();
                    filename = multi.getFilesystemName(name);
                    ////System.out.println("filename:"+filename);
                }
            }
            //////////

            //  xlsfile = new File(dest + "/" + filename);//vbcode for xls
            File cehckExcelData = null;

            String ext = "";
            if (filename != null) {
                xlsfile = new File(dest + "/" + filename);//vbcode for xls
                ext = filename.substring(filename.indexOf("."));
                if (!xlsfile.exists()) {
                    xlsfile.createNewFile();
                }
                if (ext.equals(".xlsx")) {
                    Process p = Runtime.getRuntime().exec("cscript \"" + ecatPath + "dealer/VBScript/oxl.vbs\" \"" + dest + "\\" + filename);
                    if (p != null) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
                        String inputLine;
                        while ((inputLine = in.readLine()) != null) {
                          //  System.out.println("***vbscript out=" + inputLine);
                        }
                    }
                    p.waitFor();
                    p.destroy();
                    xlsfile = new File(dest + "/" + filename.substring(0, filename.length() - 1));
                    cehckExcelData = new File(dest + filename);
                    cehckExcelData.delete();
                }
                //xlsfile = new File(dest + "/" + fileName);
                //  session.setAttribute("xlsfile", xlsfile);
                //excel validation.

            }


            //getting a connection.
            conn = holder.getConnection();
            //validation of uploaded excel file.
            xls_validate_result = new EAMG_ValidateXlsWithTemplate().isPricelistExcelValidated(xlsfile, conn);
            ////System.out.println("xls_validate_result:"+xls_validate_result);
            String effectiveDate = multi.getParameter("effectiveDate");
           // System.out.println("effectiveDate==" + effectiveDate);
            //if validation successful.
            if (xls_validate_result.equals("success")) {
                insertpart = new EAMG_InsertPriceListParameters();
                //insertion of PARTS into database.
                Vector insertionresult = insertpart.PriceListInsertion(xlsfile, conn, user_id, effectiveDate);
                request.setAttribute("insertionresult", insertionresult);

                //delete the temporary excel file
                if (xlsfile.exists() && xlsfile.isFile()) {
                    xlsfile.delete();
                }
                File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
                if (src_folder.exists()) {
                    src_folder.delete();
                }
                ////////////////////////////////////////////////////////////////////
                return mapping.findForward(SUCCESS);
            } //if file is corrupted.
            else if (xls_validate_result.equals("readingerr")) {
                if (multi.getParameter("flag").equalsIgnoreCase("cataFlag") && multi.getParameter("flag")!=null) {
                    result = "Error occured while reading Excel. It may be due to corrupted Excel file or invalid file path.<br> Hence PriceList Creation Process aborted. Attach valid Excel to complete the Process successfully.";
                    result += "<br><font class='red-for-temmplate-link' ><a href=\"javascript:location.href='" + context + "'/authJSP/EAMG_Part/EAMG_upload_pricelist_excel.jsp';\">Try Again...</a></font>";
                } else {
                    result = "Error occured while reading Excel. It may be due to corrupted Excel file or invalid file path.<br> Hence PriceList Creation Process aborted. Attach valid Excel to complete the Process successfully.";
                    result += "<br><font class='red-for-temmplate-link' ><a href=\"javascript:location.href='" + context + "'/masterAction.do?option=initPricelistExcelMaster';\">Try Again...</a></font>";

                }
                request.setAttribute("result", result);
                request.setAttribute("comp_type", "pricelist");
                //delete the temporary excel file
                if (xlsfile.exists() && xlsfile.isFile()) {
                    xlsfile.delete();
                }
                File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
                if (src_folder.exists()) {
                    src_folder.delete();
                }
                ////////////////////////////////////////////////////////////////////
                return mapping.findForward("readingerr");
            } //if validation is failed.
            else {
                request.setAttribute("validate_type", "pricelist");
                request.setAttribute("xls_validate_result", xls_validate_result);
                //delete the temporary excel file
                if (xlsfile.exists() && xlsfile.isFile()) {
                    xlsfile.delete();
                }
                File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
                if (src_folder.exists()) {
                    src_folder.delete();
                }
                ////////////////////////////////////////////////////////////////////
                return mapping.findForward("failure");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("validate_type", "part");
            request.setAttribute("xls_validate_result", "error");
            //delete the temporary excel file
            if (xlsfile.exists() && xlsfile.isFile()) {
                xlsfile.delete();
            }
            File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
            if (src_folder.exists()) {
                src_folder.delete();
            }
            ////////////////////////////////////////////////////////////////////
            return mapping.findForward("failure");
        } finally {
            //conn.close();
        }
    }
}
