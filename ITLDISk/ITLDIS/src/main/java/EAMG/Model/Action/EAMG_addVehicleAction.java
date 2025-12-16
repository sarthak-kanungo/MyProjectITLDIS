/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Model.Action;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import EAMG.Group.Action.EAMG_ValidateXlsWithTemplate;
import EAMG.Group.DAO.EAMGGroupDAO_R;
import EAMG.Model.DAO.EAMG_ModelDAO;
import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author manish.kishore
 */
public class EAMG_addVehicleAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
   
    private Logger logger = Logger.getLogger(this.getClass());

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        /*********************Getting the session values**************************/
        HttpSession session = request.getSession();
        String ecatPath = (String) session.getAttribute("ecatPath");
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        Connection conn = null;
        String result=null;
        String filename=null;
        String xls_validate=null;
        EAMG_ModelDAO dao=null;
        EAMG_Variant_AggregatesActionForm formBean = (EAMG_Variant_AggregatesActionForm) form;
        
        try {
            conn = holder.getConnection();
            dao=new EAMG_ModelDAO();
            conn.setAutoCommit(false);
            
            FormFile excelFile = null;
            boolean isFileUploaded=false,isSuccessfull=false;
            excelFile=formBean.getImportexcelfile();
            String user_id=""+session.getAttribute("user_id");
            File dest_folder=new File(ecatPath+"dealer/tempExcels/"+user_id);
            if (!dest_folder.exists())
                dest_folder.mkdirs();

            String dest=ecatPath+"dealer/tempExcels/"+user_id;
            int filesize=excelFile.getFileSize();
            int maxfilesize=5*1024*1024;
            if(filesize>maxfilesize)
            {
                result="Posted Content exceeds the size limit. Please Attach the files with size less than 5 MB. ";
                request.setAttribute("result",result);
            }
            filename=excelFile.getFileName();
            File xlsfile=new File(dest+"/"+filename);
            
            isFileUploaded=new EAMGGroupDAO_R(conn).upload(filename, dest, excelFile);
            if(!isFileUploaded)
            {
               request.setAttribute("result","Unable To Upload Excel Please Contact System Administrator."); 
               return mapping.findForward(SUCCESS);
                
            }
            ArrayList xls_validate_result=null;;
            try
            {
                xls_validate_result = new EAMG_ValidateXlsWithTemplate().isVehicleExcelValidated(xlsfile, conn);
            } catch (Exception ex)
            {
                ex.printStackTrace();
                result=ex.getMessage();
                request.setAttribute("result",result);
            }
            
            String []resultArr=null;
            xls_validate=(String)xls_validate_result.get(0);
            if(xls_validate.indexOf("@@")!=-1)
            {
                resultArr=xls_validate.split("@@");
                if(resultArr[0].equals("success"))//If Validated
                {
                    //result="Excel Template validated successfully.";
                    isSuccessfull=dao.insertVehicleData(xls_validate_result, conn);
                    if(isSuccessfull)
                        request.setAttribute("result","Vehicle Added Successfully.");
                    else
                      request.setAttribute("result","Unable To Add Vehicle Please Contact System Administrator.");
                }
                else
                {
                    int row=Integer.parseInt(resultArr[0])+1;
                    int col=Integer.parseInt(resultArr[1])+1;
                    result="Attached Excel does not match with Add Vehicle Template at line no: "+row+" and column no: "+col+". <br>Hence  Creation Process Aborted. Attach valid Excel to complete the Process successfully.";
                    xlsfile.delete();//delete the temporary Excel File.
                    request.setAttribute("result",result);

                }
            }// Not Validated.
            else
            {
                result=xls_validate_result+"<br> Hence  Creation Process aborted. Attach valid Excel to complete the Process successfully.";
                xlsfile.delete();//delete the temporary Excel File.
                request.setAttribute("result",result);
            }
            conn.commit();
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("result","Unable To Add Vehicle .Please Contact System Administrator.");
            conn.rollback();
            logger.error("Caught in Exception", e);

        } finally {
            try {
//                if (conn != null) {
//                    conn.close();
//                    conn = null;
//                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Caught in Final Exception", e);
            }
        }
        return mapping.findForward(SUCCESS);
    }
}
