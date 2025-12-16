/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package EAMG.Other.Action;
import java.io.File;
import java.io.IOException;
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

import EAMG.Part.Action.EAMG_ValidateXlsWithTemplate;
import dbConnection.dbConnection;
import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author satyaprakash.verma
 */
public class EAMG_AddTSNAction extends Action {

    private final static String SUCCESS = "success";
    private final static String FAILURE = "fail";



 @Override
   
 public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        ////////////////////getting the values from session//////////////////
        HttpSession session = request.getSession();
        String ecatPath =session.getAttribute("ecatPATH").toString();
        String user_id = session.getAttribute("userCode").toString();
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        String context = request.getContextPath();
        ////////////////////////////////////////////////////////////////////

        //Declaration of variables used
        String result="";
        Connection conn=null;
        File dest_folder=null;
        String dest="";
        String filename="";
        String name="";
        Enumeration files = null;
        boolean isFilesAttached=true;
        MultipartRequest multi=null;
        String xls_validate_result="";
        EAMG_InsertTSNParameters insertTSN=null;
        File xlsfile=null;
        try
        {
            //for getting excel file and uploading it onto server.
            dest_folder=new File(ecatPath+"dealer/tempExcels/"+user_id);
            if (!dest_folder.exists())
            {
                    dest_folder.mkdir();
            }
            dest=ecatPath+"dealer/tempExcels/"+user_id;
            ////System.out.println("dest:"+dest);

            try
            {
                multi = new MultipartRequest(request,dest,5*1024*1024*1024);
                ////System.out.println("multi:"+multi);
            }
            catch(IOException e1)
            {
                isFilesAttached=false;
                result="Posted Content exceeds the size limit. Please Attach the files with size less than 5 MB. ";
                request.setAttribute("result",result);
                return mapping.findForward(FAILURE);
            }

            if (isFilesAttached==true)
            {
                files = multi.getFileNames();
                while (files.hasMoreElements())
                {
                    name = (String)files.nextElement();
                    filename = multi.getFilesystemName(name);
                    ////System.out.println("filename:"+filename);
                }
            }

            xlsfile=new File(dest+"/"+filename);
            //getting a connection.
            //conn=holder.getConnection();
            conn=new dbConnection().getConnection_DM();
            //validation of uploaded excel file.
            xls_validate_result=new EAMG_ValidateXlsWithTemplate().isTSNExcelValidated(xlsfile,conn);
            ////System.out.println("xls_validate_result:"+xls_validate_result);

            //if validation successful.
            if(xls_validate_result.equals("success"))
            {
                insertTSN=new EAMG_InsertTSNParameters();
                //insertion of TSN into database.
                Vector insertionresult=insertTSN.tsnInsertion(xlsfile,conn,user_id);
                request.setAttribute("insertionresult",insertionresult);

                //delete the temporary excel file
                if (xlsfile.exists() && xlsfile.isFile())
                {
                    xlsfile.delete();
                }
                File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
                if (src_folder.exists()) {
                    src_folder.delete();
                }
                ////////////////////////////////////////////////////////////////////
                return mapping.findForward(SUCCESS);
            }
            //if file is corrupted.
            else if(xls_validate_result.equals("readingerr"))
            {
                result = "Error occured while reading Excel. It may be due to corrupted Excel file or invalid file path.<br> Hence TSN Creation Process aborted. Attach valid Excel to complete the Process successfully.";
                result+="<br><font class='red-for-temmplate-link' ><a href=\"javascript:location.href='"+context+"'/authJSP/other/EAMG_Upload_TSN_matrix.jsp';\">Try Again...</a></font>";
                request.setAttribute("result",result);
                request.setAttribute("comp_type", "TSN");
                //delete the temporary excel file
                if (xlsfile.exists() && xlsfile.isFile())
                {
                    xlsfile.delete();
                }
                File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
                if (src_folder.exists()) {
                    src_folder.delete();
                }
                ////////////////////////////////////////////////////////////////////
                return mapping.findForward("readingerr");
            }
            //if validation is failed.
            else
            {
                request.setAttribute("validate_type", "TSN");
                request.setAttribute("xls_validate_result", xls_validate_result);
                //delete the temporary excel file
                if (xlsfile.exists() && xlsfile.isFile())
                {
                    xlsfile.delete();
                }
                File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
                if (src_folder.exists()) {
                    src_folder.delete();
                }
                ////////////////////////////////////////////////////////////////////
                return mapping.findForward("failure");
            }
         }
        catch (Exception ex)
         {
            ex.printStackTrace();
            request.setAttribute("validate_type", "TSN");
            request.setAttribute("xls_validate_result","error");
            //delete the temporary excel file
            if (xlsfile.exists() && xlsfile.isFile())
            {
                xlsfile.delete();
            }
            File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
            if (src_folder.exists()) {
                src_folder.delete();
            }
            ////////////////////////////////////////////////////////////////////
            return mapping.findForward("failure");
        }
        finally
        {

            //conn.close();
        }
    }
}
