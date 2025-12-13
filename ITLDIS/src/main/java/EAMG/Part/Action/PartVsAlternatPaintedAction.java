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

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

import com.oreilly.servlet.MultipartRequest;

import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author vijay.mishra
 */
public class PartVsAlternatPaintedAction extends DispatchAction {

    private final static String SUCCESS = "success";
    private final static String FAILURE = "fail";

 
    public ActionForward validatedPartVsPainted(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        String ecatPath = session.getAttribute("ecatPATH").toString();
        String user_id = session.getAttribute("userCode").toString();
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        String context = request.getContextPath();
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
        EAMG_InsertPartParameters insertpart = null;
        File xlsfile = null;
        try {
            dest_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
            if (!dest_folder.exists()) {
                dest_folder.mkdir();
            }
            dest = ecatPath + "dealer/tempExcels/" + user_id;

            try {
                multi = new MultipartRequest(request, dest, 5 * 1024 * 1024);
            } catch (IOException e1) {
                isFilesAttached = false;
                e1.printStackTrace();
                result = "Posted Content exceeds the size limit. Please Attach the files with size less than 5 MB. ";
                request.setAttribute("result", result);
                return mapping.findForward(FAILURE);
            }
            if (isFilesAttached == true) {
                files = multi.getFileNames();
                while (files.hasMoreElements()) {
                    name = (String) files.nextElement();
                    filename = multi.getFilesystemName(name);
                }
            }
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
                           // System.out.println("***vbscript out=" + inputLine);
                        }
                    }
                    p.waitFor();
                    p.destroy();
                    xlsfile = new File(dest + "/" + filename.substring(0, filename.length() - 1));
                    cehckExcelData = new File(dest + filename);
                    cehckExcelData.delete();
                }
            }
            conn = holder.getConnection();
            xls_validate_result = new EAMG_ValidateXlsWithTemplate().isPartVsPaintedValidated(xlsfile, conn);
            if (xls_validate_result.equals("success")) {
                insertpart = new EAMG_InsertPartParameters();
                request.setAttribute("validate_type", "paintedPartList");
                Vector insertionresult = insertpart.partVsPaintedVsAlternate_Insertion(xlsfile, conn, user_id,"Painted");
                request.setAttribute("insertionresult", insertionresult);

                if (xlsfile.exists() && xlsfile.isFile()) {
                    xlsfile.delete();
                }
                File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
                if (src_folder.exists()) {
                    src_folder.delete();
                }
                return mapping.findForward(SUCCESS);
            } //if file is corrupted.
            else if (xls_validate_result.equals("readingerr")) {
                    result = "Error occured while reading Excel. It may be due to corrupted Excel file or invalid file path.<br> Hence Part vs Paint Code Creation Process aborted. Attach valid Excel to complete the Process successfully.";
                    result += "<br><font class='red-for-temmplate-link' ><a href=\"javascript:location.href='" + context + "'/authJSP/EAMG_Part/Upload_Part_vs_Painted.jsp';\">Try Again...</a></font>";
                request.setAttribute("result", result);
                request.setAttribute("comp_type", "paintedPartList");
                if (xlsfile.exists() && xlsfile.isFile()) {
                    xlsfile.delete();
                }
                File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
                if (src_folder.exists()) {
                    src_folder.delete();
                }
                return mapping.findForward("readingerr");
            } //if validation is failed.
            else {
                request.setAttribute("validate_type", "paintedPartList");
                request.setAttribute("xls_validate_result", xls_validate_result);
                if (xlsfile.exists() && xlsfile.isFile()) {
                    xlsfile.delete();
                }
                File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
                if (src_folder.exists()) {
                    src_folder.delete();
                }
                return mapping.findForward("failure");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("validate_type", "paintedPartList");
            request.setAttribute("xls_validate_result", "error");
            if (xlsfile.exists() && xlsfile.isFile()) {
                xlsfile.delete();
            }
            File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
            if (src_folder.exists()) {
                src_folder.delete();
            }
            return mapping.findForward("failure");
        } finally {
        }
    }
    public ActionForward validatedPartVsAlternate(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        HttpSession session = request.getSession();
        String ecatPath = session.getAttribute("ecatPATH").toString();
        String user_id = session.getAttribute("userCode").toString();
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        String context = request.getContextPath();
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
        EAMG_InsertPartParameters insertpart = null;
        File xlsfile = null;
        try {
            dest_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
            if (!dest_folder.exists()) {
                dest_folder.mkdir();
            }
            dest = ecatPath + "dealer/tempExcels/" + user_id;

            try {
                multi = new MultipartRequest(request, dest, 5 * 1024 * 1024);
            } catch (IOException e1) {
                isFilesAttached = false;
                e1.printStackTrace();
                result = "Posted Content exceeds the size limit. Please Attach the files with size less than 5 MB. ";
                request.setAttribute("result", result);
                return mapping.findForward(FAILURE);
            }
            if (isFilesAttached == true) {
                files = multi.getFileNames();
                while (files.hasMoreElements()) {
                    name = (String) files.nextElement();
                    filename = multi.getFilesystemName(name);
                }
            }
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
                            //System.out.println("***vbscript out=" + inputLine);
                        }
                    }
                    p.waitFor();
                    p.destroy();
                    xlsfile = new File(dest + "/" + filename.substring(0, filename.length() - 1));
                    cehckExcelData = new File(dest + filename);
                    cehckExcelData.delete();
                }
            }
            conn = holder.getConnection();
            xls_validate_result =new EAMG_ValidateXlsWithTemplate().isPartVsAlternateValidated(xlsfile, conn);
            if (xls_validate_result.equals("success")) {
                insertpart = new EAMG_InsertPartParameters();
                request.setAttribute("validate_type", "alternatePartList");
                Vector insertionresult = insertpart.partVsPaintedVsAlternate_Insertion(xlsfile, conn, user_id,"Alternate");
                request.setAttribute("insertionresult", insertionresult);

                if (xlsfile.exists() && xlsfile.isFile()) {
                    xlsfile.delete();
                }
                File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
                if (src_folder.exists()) {
                    src_folder.delete();
                }
                return mapping.findForward(SUCCESS);
            } //if file is corrupted.
            else if (xls_validate_result.equals("readingerr")) {
                    result = "Error occured while reading Excel. It may be due to corrupted Excel file or invalid file path.<br> Hence Part vs Alternate Creation Process aborted. Attach valid Excel to complete the Process successfully.";
                    result += "<br><font class='red-for-temmplate-link' ><a href=\"javascript:location.href='" + context + "'/authJSP/EAMG_Part/Upload_Part_vs_Alternate.jsp';\">Try Again...</a></font>";
                request.setAttribute("result", result);
                request.setAttribute("comp_type", "alternatePartList");
                if (xlsfile.exists() && xlsfile.isFile()) {
                    xlsfile.delete();
                }
                File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
                if (src_folder.exists()) {
                    src_folder.delete();
                }
                return mapping.findForward("readingerr");
            } //if validation is failed.
            else {
                request.setAttribute("validate_type", "alternatePartList");
                request.setAttribute("xls_validate_result", xls_validate_result);
                if (xlsfile.exists() && xlsfile.isFile()) {
                    xlsfile.delete();
                }
                File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
                if (src_folder.exists()) {
                    src_folder.delete();
                }
                return mapping.findForward("failure");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            request.setAttribute("validate_type", "alternatePartList");
            request.setAttribute("xls_validate_result", "error");
            if (xlsfile.exists() && xlsfile.isFile()) {
                xlsfile.delete();
            }
            File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
            if (src_folder.exists()) {
                src_folder.delete();
            }
            return mapping.findForward("failure");
        } finally {
        }
    }
}
