/*
 * AT4_kit_creation.java
 *
 * Created on November 14, 2008, 2:54 PM
 */

package EAMG.Kit.Action;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oreilly.servlet.MultipartRequest;

import EAMG.Part.Action.EAMG_ValidateXlsWithTemplate;
import jxl.Sheet;
import jxl.Workbook;
import viewEcat.comEcat.ConnectionHolder;
/**
 *
 * @author Avinash.Pandey
 * @version
 */

public class EAMG_KitCreationAction extends Action {
    
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
        //System.out.println("r we here");
         HttpSession session= request.getSession();
         ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        Connection conn=conn = holder.getConnection();
        try{
        String result="";
        String ecatPath =session.getAttribute("ecatPATH").toString();
        String user_id = session.getAttribute("userCode").toString();
        String fileName=request.getParameter("excelFile");
        
        //System.out.println("ecatPath is ;"+ecatPath);
        
        File dest_folder=new File(ecatPath+"/dealer/tempExcels/"+user_id);
        //System.out.println("dest_folder is :"+dest_folder);
        
        if (!dest_folder.exists()) {
            //System.out.println("not exists");
            dest_folder.mkdirs();
        }
        
        String dest=ecatPath+"/dealer/tempExcels/"+user_id;
        //System.out.println("dest:"+dest);
        
        MultipartRequest multi=null;
        boolean isFilesAttached=true;
        try {
            multi = new MultipartRequest(request,dest,5*1024*1024);
            //System.out.println("multi:"+multi);
        } catch(IOException e1) {
            isFilesAttached=false;
            result="Posted Content exceeds the size limit. Please Attach the files with size less than 5 MB. ";
            request.setAttribute("result",result);
            //return mapping.findForward(FAILURE);
        }
        
        //String fileName="";
        if (isFilesAttached==true) {
            Enumeration files = multi.getFileNames();
            
            int cnt=0;
            while (files.hasMoreElements()) {
                String name = (String)files.nextElement();
                fileName = multi.getFilesystemName(name);
                //System.out.println("filename:"+fileName);
            }
        }
        //System.out.println(dest+"/"+fileName);
        File xlsfile=new File(dest+"/"+fileName);
        String path=dest+"/"+fileName;
        Workbook wb=null;
        try
        {
            wb = Workbook.getWorkbook(xlsfile);
        }
        //if excel is currupted or wrong path specified.
        catch (Exception e)
        {
            e.printStackTrace();
            result = "Error occured while reading Excel. It may be due to corrupted Excel file or invalid file path.<br> Hence Kit Creation Process aborted. Attach valid Excel to complete the Process successfully.";
            result+="<br><font class='red-for-temmplate-link' ><a href=\"javascript:location.href='/authJSP/EAMG_KIT/EAMG_create_kit.jsp';\">Try Again...</a></font>";
            request.setAttribute("result", result);
            request.setAttribute("comp_type", "kit");
            return mapping.findForward("readingerr");
            
        }

        int no_of_Sheet= wb.getNumberOfSheets();//No. of WorkSheet in WorkBook
        int index=0;
        Sheet sheet = wb.getSheet(index);
        
        result= new EAMG_ValidateXlsWithTemplate().isKitExcel(fileName,index,sheet,conn);
        String kitNo= sheet.getCell(1,2).getContents();
        session.setAttribute("comp_no",kitNo.toUpperCase());   
        session.setAttribute("filepath",path);    
        request.setAttribute("option","kit_validated");
        session.setAttribute("result",result);
        } 
        catch(Exception e)
        {
          e.printStackTrace();
      
        }
        return mapping.findForward(SUCCESS);
        
    }
}
