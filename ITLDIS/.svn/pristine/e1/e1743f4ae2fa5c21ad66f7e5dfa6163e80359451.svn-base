/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/*
File Name: 	amw_ModifyComponentByExcel.java
PURPOSE: 	TO validate Components from Excel.
HISTORY:
DATE        	BUILD     	AUTHOR              MODIFICATIONS
09/11/11	1.0		Avinash.pandey  $$1 Created
 */
package EAMG.Tool.Action;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EAMG.common.EAMG_MethodsUtility;
import com.oreilly.servlet.MultipartRequest;

import EAMG.Part.Action.EAMG_ValidateXlsWithTemplate;
import EAMG.PartDAO_CUD.EAMGPartDAO_R;
import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author pramod.vishwakarma
 */
public class EAMG_ModifyToolByExcel extends org.apache.struts.action.Action {

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
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
         ////////////////////getting the values from session//////////////////////
       HttpSession session= request.getSession();
       String ecatPath=""+session.getAttribute("ecatPATH");
       String user_id=""+session.getAttribute("userCode");
       String context = request.getContextPath();
       ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
       /////////////////////////////////////////////////////////////////////////

        //declaration of variables used.
       Connection conn=null;
       MultipartRequest multi=null;
       boolean isFilesAttached=true;
       String comp_type="";
       String comp_param_list="";
       String fileName="";
       String result="";
       String modify_option="";
		 Vector insertionResult=new Vector();
       try{
           //getting connection.
        conn = holder.getConnection();
        File dest_folder=new File(ecatPath+"/dealer/tempExcels/"+user_id);
        //////System.out.println("dest_folder is :"+dest_folder);

        if (!dest_folder.exists()) {
            dest_folder.mkdirs();
        }
        String dest=ecatPath+"dealer/tempExcels/"+user_id;
        //////System.out.println("dest:"+dest);

        //uploading file onto server.
        try {
            multi = new MultipartRequest(request,dest,5*1024*1024);
            //////System.out.println("multi:"+multi);
        } catch(IOException e1) {
            isFilesAttached=false;
            result="Posted Content exceeds the size limit. Please Attach the files with size less than 5 MB. ";
            request.setAttribute("result",result);
            //return mapping.findForward(FAILURE);
        }
        //getting component type from MultipartRequest.
        comp_type=multi.getParameter("comp_type");
        request.setAttribute("comp_type",comp_type);
		  

        String iscomppresent="";
        modify_option=multi.getParameter("modify_option");
        ////System.out.println("modify_option:"+modify_option);
        if(modify_option.equals("wizard"))
        {
            comp_param_list=multi.getParameter("comp_param_list").toUpperCase();
            iscomppresent=new EAMG_MethodsUtility().isCompPresent(comp_param_list, conn);
            session.setAttribute("iscomppresent",iscomppresent);
            session.setAttribute("comp_param_list",comp_param_list);
            session.setAttribute("option","modify");
            return mapping.findForward("wizard2");

        }

        //////System.out.println("comp_type:"+comp_type);
        //String fileName="";
        if (isFilesAttached==true) {
            Enumeration files = multi.getFileNames();

            while (files.hasMoreElements()) {
                String name = (String)files.nextElement();
                fileName = multi.getFilesystemName(name);
                ////System.out.println("filename:"+fileName);
            }
        }

        //////System.out.println(dest+"/"+fileName);
        File xlsfile=new File(dest+"/"+fileName);
        session.setAttribute("xlsfile", xlsfile);
        //excel validation.
       // if(comp_type.equalsIgnoreCase("Tool") || comp_type.equalsIgnoreCase("Lube"))

         result= new EAMG_ValidateXlsWithTemplate().isLubeToolExcelValidated(xlsfile, conn);

        //////System.out.println("result :"+result);
        //if file is currupted.
        if(result.equals("readingerr"))
            {
                result = "Error occured while reading Excel. It may be due to corrupted Excel file.";
                result+="<br><font class='red-for-temmplate-link' ><a href=\""+context+"'/authJSP/EAMG_Part/EAMG_browse_part_excel.jsp\">Try Again...</a></font>";
                request.setAttribute("result",result);
                return mapping.findForward("readingerr");
            }
        //if validation failed.
        if(!result.equals("Excel Validation Successful"))
        {
				 result = "Error in " + result;
				session.setAttribute("comp_no", "COMPONENT");
				session.setAttribute("filepath", dest+"/"+fileName);
				request.setAttribute("option", "tool_validated");
				request.setAttribute("comp_type", comp_type);
				session.setAttribute("result", result);
            request.setAttribute("xls_validate_result", result);
            request.setAttribute("validate_type", "modify");
            return mapping.findForward("failure");
        }
		  else
		  {
                 insertionResult=new EAMGPartDAO_R().updatePartExcel(conn, xlsfile,"Tool");
					  request.setAttribute("insertionresult",insertionResult);
					  request.setAttribute("modifyFlag","");
                 return mapping.findForward("insertionResult");
		  }

        }
       catch (Exception ex)
         {
           ex.printStackTrace();
            result = "Error occured while reading Excel. It may be due to corrupted Excel file.";
            result+="<br><font class='red-for-temmplate-link' ><a href=\"javascript:location.href=''"+context+"'/authJSP/EAMG_Part/EAMG_browse_part_excel.jsp';\">Try Again...</a></font>";
            request.setAttribute("result",result);
            return mapping.findForward("readingerr");
        }

//        request.setAttribute("xls_validate_result", result);
//        request.setAttribute("validate_type", "modify");
      //  return mapping.findForward(SUCCESS);

    }
}