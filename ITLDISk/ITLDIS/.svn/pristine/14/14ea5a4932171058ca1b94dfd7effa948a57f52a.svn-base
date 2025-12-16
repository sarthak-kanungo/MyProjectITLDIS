package EAMG.Model.Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Vector;

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
import viewEcat.comEcat.PageTemplate;

/**
 *
 * @author avinash.pandey
 * @version
 */
public class EAMG_ModifyModelDetailsAction extends Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";

    /**
     * This is the action called from the Struts framework.
     *
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
        String effectiveDate = "";
        HttpSession session = request.getSession();
        String server_name = (String) session.getValue("server_name");
        String ecatPath = (String) session.getValue("ecatPATH");
        String mainURL = (String) session.getValue("mainURL");
        PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
        int modelImageWidth = object_pageTemplate.modelImageWidth;
        int modelImageHeight = object_pageTemplate.modelImageHeight;
        String modelNo = object_pageTemplate.MODEL_NO;
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        String result = "";
        Enumeration files = null;
        MultipartRequest multi = null;
        boolean isFilesAttached = true;
        int seqOrder = 0;
        String filePath = ecatPath + "dealer/ecat_print/temp_model_images/";
        String message = "";
        Vector msgVec = new Vector();
        long maxSize = PageTemplate.maxSize;
        PreparedStatement ps = null;
        ResultSet rs = null;

        Connection conn = holder.getConnection();
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        try {

            String model_no = request.getParameter("model_no");
            if (request.getParameter("variantName").equalsIgnoreCase("1Oth") || request.getParameter("engineModel").equalsIgnoreCase("1Oth")) {
                File dest_folder = new File(filePath);
                String name = "";
                String filename = "";

                if (!dest_folder.exists()) {
                    dest_folder.mkdirs();
                }



                try {
                    multi = new MultipartRequest(request, filePath, 5 * 1024 * 1024);
                    ////System.out.println("multi:"+multi);
                } catch (IOException e1) {
                    isFilesAttached = false;
                    e1.printStackTrace();
                    result = "Posted Content exceeds the size limit. Please Attach the files with size less than 5 MB. ";
                    request.setAttribute("result", result);
                    return mapping.findForward("fail");
                }

                if (isFilesAttached == true) {
                    files = multi.getFileNames();
                    while (files.hasMoreElements()) {
                        name = (String) files.nextElement();
                        filename = multi.getFilesystemName(name);
                        ////System.out.println("filename:"+filename);
                    }
                }

            }



            String engineModel = "";
            String applicationType = "";

            String mdesc = request.getParameter("modeldesc").trim();
            String variantN = "";
            if (request.getParameter("variantName").equalsIgnoreCase("1Oth")) {
                variantN = request.getParameter("variantNameOth");
            } else {
                variantN = request.getParameter("variantName");
            }
            if (request.getParameter("engineModel").equalsIgnoreCase("1Oth")) {
                engineModel = request.getParameter("engineModelOth");
            } else {
                engineModel = request.getParameter("engineModel");
            }
            if (request.getParameter("applicationType").equalsIgnoreCase("1Oth")) {
                applicationType = request.getParameter("applicationTypeOth");
            } else {
                applicationType = request.getParameter("applicationType");
            }
            effectiveDate = request.getParameter("effectiveDate");

            Date date = methodutil.getSQLTodaysDate();
            String query1 = "update CAT_MODELS set DESCRIPTION=?,CREATION_DATE=? where MODEL_NO=?";
            ps = conn.prepareStatement(query1);
            ps.setString(1, mdesc);
            ps.setDate(2, date);
            ps.setString(3, model_no);
            ps.executeUpdate();
            ps.close();
            String query = "select * from CAT_MODEL_CLASSIFICATION(NOLOCK) where MODEL_NO='" + model_no + "'";
            ResultSet rs1 = null;
            PreparedStatement stmt = conn.prepareStatement(query);
            rs1 = stmt.executeQuery();
            if (rs1.next()) {
                query1 = "delete from CAT_MODEL_CLASSIFICATION where MODEL_NO='" + model_no + "'";
                ps = conn.prepareStatement(query1);
                ps.executeUpdate();
                //System.out.println(result1);
            }

            rs1.close();

            String query3 = "select max(SEQ_ORDER) from CAT_MODEL_CLASSIFICATION(NOLOCK)";
            ps = conn.prepareStatement(query3);
            rs = ps.executeQuery();
            if (rs.next()) {
                seqOrder = rs.getInt(1);
            }
            rs.close();
            query1 = "insert into CAT_MODEL_CLASSIFICATION values(?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(query1);
            ps.setString(1, model_no);
            ps.setString(2, variantN);
            ps.setString(3, engineModel);
            ps.setString(4, applicationType);
            ps.setString(5, "NA");
            ps.setString(6, "NA");
            ps.setInt(7, seqOrder + 1);
            ps.executeUpdate();

            ps.close();
            stmt.close();
            result = "" + modelNo + " '" + model_no + "' Modified Successfully.";

            if (request.getParameter("variantName").equalsIgnoreCase("1Oth")) {
                String productImages = request.getParameter("variantNameOth");
                String inputName = ecatPath + "dealer/ecat_print/temp_model_images/" + productImages + ".jpg";
                productImages = productImages.trim();
                productImages = productImages.toUpperCase();  //engine_series_image
                String outputName = ecatPath + "dealer/ecat_print/engine_series_image/" + productImages + ".jpg";

                File oldFile = new File(outputName);
                if (oldFile.exists()) {
                    oldFile.delete();
                }
                FileChannel fileSource = new FileInputStream(inputName).getChannel();
                FileChannel destination = new FileOutputStream(outputName).getChannel();
                destination.transferFrom(fileSource, 0, fileSource.size());

            }

            if (request.getParameter("engineModel").equalsIgnoreCase("1Oth")) {
                String engineImages = request.getParameter("engineModelOth");
                String inputName = ecatPath + "dealer/ecat_print/temp_model_images/" + engineImages + ".jpg";
                engineImages = engineImages.trim();
                engineImages = engineImages.toUpperCase();
                String outputName = ecatPath + "dealer/ecat_print/engine_model_image/" + engineImages + ".jpg";
                
                File oldFile = new File(outputName);
                if (oldFile.exists()) {
                    oldFile.delete();
                }
                
                FileChannel fileSource = new FileInputStream(inputName).getChannel();
                FileChannel destination = new FileOutputStream(outputName).getChannel();
                destination.transferFrom(fileSource, 0, fileSource.size());
            }
            if (request.getParameter("variantName").equalsIgnoreCase("1Oth") || request.getParameter("engineModel").equalsIgnoreCase("1Oth")) {
                File directory = new File(ecatPath + "dealer/ecat_print/temp_model_images");
                if (directory.exists()) {

                    delete(directory);
                }
            }
            conn.commit();
        } catch (SQLException ex) {
            ex.printStackTrace();
            result = "Exception";
            conn.rollback();

        }

        request.setAttribute("result", result);
        return mapping.findForward("success");

    }

    public void delete(File file) {
        if (file.isDirectory()) {
            if (file.list().length == 0) {
                file.delete();
            } else {
                String files[] = file.list();
                for (String temp : files) {
                    File fileDelete = new File(file, temp);
                    delete(fileDelete);
                }
                if (file.list().length == 0) {
                    file.delete();
                }
            }
        } else {
            file.delete();
        }
    }
}
