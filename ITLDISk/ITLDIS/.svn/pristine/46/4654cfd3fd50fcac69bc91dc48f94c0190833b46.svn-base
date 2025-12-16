/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
File Name: 	AT4_ModifyComponentByExcel.java
PURPOSE: 	TO validate Components from Excel.
HISTORY:
DATE        	BUILD     	AUTHOR              MODIFICATIONS
02/01/09	1.0		PRAMOD.VISHWAKARMA  $$1 Created
 */
package EAMG.Kit.Action;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EAMG.common.EAMG_MethodsUtility;
import com.oreilly.servlet.MultipartRequest;

import EAMG.Part.Action.EAMG_ValidateXlsWithTemplate;
import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author pramod.vishwakarma
 */
public class EAMG_ModifyKitByExcel extends org.apache.struts.action.Action {

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
        String ecatPath = session.getAttribute("ecatPATH").toString();
        String user_id = session.getAttribute("userCode").toString();
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        /////////////////////////////////////////////////////////////////////////

        //declaration of variables used.
        Connection conn = null;
        MultipartRequest multi = null;
        boolean isFilesAttached = true;
        String comp_type = "";
        String comp_param_list = "";
        String fileName = "";
        String result = "";
        String modify_option = "";
        try {
            //getting connection.
            conn = holder.getConnection();
            File dest_folder = new File(ecatPath + "/dealer/tempExcels/" + user_id);
            //////System.out.println("dest_folder is :"+dest_folder);

            if (!dest_folder.exists()) {
                dest_folder.mkdirs();
            }
            String dest = ecatPath + "dealer/tempExcels/" + user_id;
            //////System.out.println("dest:"+dest);

            //uploading file onto server.
            try {
                multi = new MultipartRequest(request, dest, 5 * 1024 * 1024);
                //////System.out.println("multi:"+multi);
            } catch (IOException e1) {
                isFilesAttached = false;
                result = "Posted Content exceeds the size limit. Please Attach the files with size less than 5 MB. ";
                request.setAttribute("result", result);
                //return mapping.findForward(FAILURE);
            }
            //getting component type from MultipartRequest.
            comp_type = multi.getParameter("comp_type");
            session.setAttribute("comp_type", comp_type);

            String iscomppresent = "";
            modify_option = multi.getParameter("modify_option");
            ////System.out.println("modify_option:"+modify_option);
            if (modify_option.equals("wizard")) {
                comp_param_list = multi.getParameter("comp_param_list").toUpperCase();
                iscomppresent = new EAMG_MethodsUtility().isCompPresent(comp_param_list, conn);
                session.setAttribute("iscomppresent", iscomppresent);
                session.setAttribute("comp_param_list", comp_param_list);
                session.setAttribute("option", "modify");
//            if(comp_type.equals("ASM"))
//            {
//                session.setAttribute("assno",comp_param_list);
//                return mapping.findForward("wizard1");
//            }
                if (comp_type.equals("KIT")) {
                    session.setAttribute("kitno", comp_param_list);
                    return mapping.findForward("wizard1");
                }
//            else{
//                return mapping.findForward("wizard2");
//            }

            }

            //////System.out.println("comp_type:"+comp_type);
            //String fileName="";
            if (isFilesAttached == true) {
                Enumeration files = multi.getFileNames();

                while (files.hasMoreElements()) {
                    String name = (String) files.nextElement();
                    fileName = multi.getFilesystemName(name);
                    ////System.out.println("filename:"+fileName);
                }
            }

            //////System.out.println(dest+"/"+fileName);
            File xlsfile = new File(dest + "/" + fileName);
            session.setAttribute("xlsfile", xlsfile);
            //excel validation.

            //if(comp_type.equals("ASM"))
            //result= new AT4_ValidateXlsWithTemplate().isAssemblyExcelValidated(xlsfile, conn);
            if (comp_type.equals("KIT")) {
                result = new EAMG_ValidateXlsWithTemplate().isKitExcelValidated(xlsfile, conn);
            }


            //////System.out.println("result :"+result);
            //if file is currupted.
            if (result.equals("readingerr")) {
                result = "Error occured while reading Excel. It may be due to corrupted Excel file.";
                result += "<br><font class='red-for-temmplate-link' ><a href=\"javascript:location.href='/authJSP/EAMG_KIT/EAMG_kit_modify_comptype.jsp';\">Try Again...</a></font>";
                request.setAttribute("result", result);
                return mapping.findForward("readingerr");
            }
            //if validation failed.
            if (!result.equals("success")) {
                request.setAttribute("xls_validate_result", result);
                request.setAttribute("validate_type", "modify");
                return mapping.findForward("failure");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            result = "Error occured while reading Excel. It may be due to corrupted Excel file.";
            result += "<br><font class='red-for-temmplate-link' ><a href=\"javascript:location.href='/authJSP/EAMG_KIT/EAMG_kit_modify_comptype.jsp';\">Try Again...</a></font>";
            request.setAttribute("result", result);
            return mapping.findForward("readingerr");
        } 

        request.setAttribute("xls_validate_result", result);
        request.setAttribute("validate_type", "modify");
        return mapping.findForward(SUCCESS);

    }
}
