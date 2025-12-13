/*
 * AT4_CreateGroupAction.java
 *
 * Created on October 31, 2008, 12:57 PM
 */
/*
File Name: 	AT4_CreateGroupAction.java
PURPOSE: 	:TO Create Group By Excel,validates the Excel Template Uploaded and setting the session variables.
HISTORY:
DATE        	BUILD     	AUTHOR    	MODIFICATIONS
31/10/08	.0		MINA YADAV	$$1 	Created
*/
package EAMG.Group.Action;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;

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
 * @author mina.yadav
 * @version
 */

public class EAMG_CreateGroupAction extends Action
{

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    private final static String FAILURE = "failure";


    /**
     * This is the action called from the Struts framework.
     * @param mapping The ActionMapping used to select this instance.
     * @param form The optional ActionForm bean for this request.
     * @param request The HTTP Request we are processing.
     * @param response The HTTP Response we are processing.
     * @throws java.lang.Exception
     * @return
     */
    public ActionForward execute(ActionMapping mapping, ActionForm  form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception
    {
        ///////////////////Declaring Variables/////////////////////
        Connection conn=null;
        String result="";
        String group_no="";
        try
        {
            //////////////////getting Session Attributes///////////////////
            HttpSession session=request.getSession();
            String ecatPath = (String) session.getValue("ecatPATH");
            String user_id=""+(String) session.getValue("userCode");

            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            //////////////////////////////////////////////////////////////


            //////////Initialize Connection object/////////////////////////
            conn=holder.getConnection();
            ///////////////////////////////////////////////////////////////


            //////////////// EXCEL UPLOADING VIA MULTIPART //////////////////

            //System.out.println("ecatPath:"+ecatPath);
            //Creating Temporary User_id folder in tempExcels into which file is uploaded
            File dest_folder=new File(ecatPath+"dealer/tempExcels/"+user_id);
            if (!dest_folder.exists())
            {
                //System.out.println("not exists");
                dest_folder.mkdirs();
            }
            String dest=ecatPath+"dealer/tempExcels/"+user_id;
            //System.out.println("dest:"+dest);

            MultipartRequest multi=null;
            boolean isFilesAttached=true;
            try
            {
                multi = new MultipartRequest(request,dest,5*1024*1024);
                //System.out.println("multi:"+multi);
            }
            catch(IOException e1)
            {
                isFilesAttached=false;
                result="Posted Content exceeds the size limit. Please Attach the files with size less than 5 MB. ";
                request.setAttribute("result",result);
                //return mapping.findForward(FAILURE);
            }
            String filename="";
            if (isFilesAttached==true)
            {
                Enumeration files = multi.getFileNames();
                int cnt=0;
                String name ="";
                while (files.hasMoreElements())
                {
                    name = (String)files.nextElement();
                    filename = multi.getFilesystemName(name);
                    //System.out.println("filename:"+filename);
                }
            }
            //////////////////////////////////////////////////////////////////////////////


            //////////////////// VALIDATING GROUP EXCEL STARTS ///////////////////////////
            File xlsfile=new File(dest+"/"+filename);
            String xls_validate_result="";
            try
            {
                xls_validate_result = new EAMG_ValidateXlsWithTemplate().isGroupExcelValidated(xlsfile, conn);
            } catch (Exception ex)
            {
                ex.printStackTrace();
                result=ex.getMessage();
                request.setAttribute("result",result);
            }

            String []resultArr=null;
            if(xls_validate_result.indexOf("@@")!=-1)
            {
                resultArr=xls_validate_result.split("@@");
                if(resultArr[0].equals("success"))//If Validated
                {
                    group_no=resultArr[1];
                    group_no=group_no.toUpperCase();
                    ////System.out.println("result of template:"+result);

                    //Check if Group Already Exists in Database.
                    String comp_check=new EAMG_MethodsUtility().isCompPresent(group_no,conn);
                    ////System.out.println("comp_check of template:"+comp_check);
                    if(!comp_check.equals("notpresent"))
                    {
                        result=comp_check+"<br>Hence Group Creation Process Aborted.";

                        //setting result in request and return.
                        request.setAttribute("result",result);
                      //  return mapping.findForward(SUCCESS);

                    }
                    result="Excel Template validated successfully.";

                    ////////////////////////Setting Session Variables///////////////////////////
                    session.setAttribute("group_number",group_no);
                    session.setAttribute("group_xls_file",dest+"/"+filename);
                    //System.out.println("group_no in session1:"+session.getAttribute("group_number"));
                    //////////////////////////////////////////////////////////////////////////////

                    //setting result in request and return.
                    request.setAttribute("result",result);
                   // return mapping.findForward(SUCCESS);

                }
                else
                {
                    int row=Integer.parseInt(resultArr[0])+1;
                    int col=Integer.parseInt(resultArr[1])+1;
                    result="Attached Excel does not match with Group Creation Template at line no: "+row+" and column no: "+col+". <br>Hence Group Creation Process Aborted. Attach valid Excel to complete the Process successfully.";

                    xlsfile.delete();//delete the temporary Excel File.

                    //setting result in request and return.
                    request.setAttribute("result",result);
                   // return mapping.findForward(SUCCESS);

                }
            }// Not Validated.
            else
            {
                result=xls_validate_result+"<br> Hence Group Creation Process aborted. Attach valid Excel to complete the Process successfully.";
                xlsfile.delete();//delete the temporary Excel File.

                //setting result in request and return.
                request.setAttribute("result",result);
              //  return mapping.findForward(SUCCESS);
            }
            //////////////////////////////////////////////////////////////////////////
           return mapping.findForward(SUCCESS);

        }
        catch(SQLException e)
        {
          e.printStackTrace();
         // throw new AT4_SQLExceptionCatcher("SQLException",e);
        }
        catch(Exception e)
        {
          e.printStackTrace();
          //throw new AT4_ExceptionCatcher("Exception",e);
        }
        
        return mapping.findForward(SUCCESS);
    }
}
