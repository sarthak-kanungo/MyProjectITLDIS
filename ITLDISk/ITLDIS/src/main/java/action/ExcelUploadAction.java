/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package action;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.actions.DispatchAction;

/**
 *
 * @author anand.prakash
 */
public class ExcelUploadAction extends DispatchAction{
    public ActionForward upload(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException{

    List usersList = new ArrayList();
        String filename = request.getParameter("fileName");
       
        try {
            // Read excel file and store email ids in mailIdsArray
            InputStream input = new BufferedInputStream(new FileInputStream(filename));
            POIFSFileSystem fs = new POIFSFileSystem(input);
            HSSFWorkbook wb = new HSSFWorkbook(fs);
            HSSFSheet sheet = wb.getSheetAt(0);
 
            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                //RegistrationUser user= new RegistrationUser();
                HSSFRow row = (HSSFRow) rows.next();
                 
                 
                 
                if(row.getCell(0) != null && !row.getCell(0).equals("First Name")){
                    
                     
                }
                 
                 
                //usersList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    
    return mapping.findForward("uploadContent");
    }
}
