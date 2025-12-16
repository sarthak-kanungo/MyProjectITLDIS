/*
 * AddModelAction.java
 *
 * Created on Nov 09, 2011, 3:40 PM
 */
/*
 File Name: 	AddModelAction.java
 PURPOSE: 	TO Create MODEL.
 HISTORY:
 DATE        	BUILD     	AUTHOR              MODIFICATIONS
 09/11/11	1.0		AVINASH.PANDEY  $$1 Created
 */
package EAMG.Model.Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EAMG.common.EAMG_MethodsUtility;
import com.oreilly.servlet.MultipartRequest;

import EAMG.Model.DAO.EAMG_ModelDAO;
import viewEcat.comEcat.ConnectionHolder;
import viewEcat.comEcat.PageTemplate;

/**
 *
 * @author pramod.vishwakarma
 * @version
 */
public class EAMG_AddModelAction extends Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";
    private Logger logger = Logger.getLogger(this.getClass());

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
    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        /////////getting values from session//////////////////////
        HttpSession hs = request.getSession();
        EAMG_ModelDAO dao = new EAMG_ModelDAO();
        String server_name = (String) hs.getValue("server_name");
        String ecatPath = (String) hs.getValue("ecatPATH");
        String mainURL = (String) hs.getValue("mainURL");
        PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
        Vector ids = (Vector) hs.getAttribute("levelid");
        ConnectionHolder holder = (ConnectionHolder) hs.getValue("servletapp.connection");
        SimpleDateFormat sdfFormat = new SimpleDateFormat("dd/mm/yyyy");
        int modelImageWidth = object_pageTemplate.modelImageWidth;
        int modelImageHeight = object_pageTemplate.modelImageHeight;
        /////////////////////////////////////////////////////////
        //declaration of variables used.

        //String dest = ecatPath + "dealer/ecat_print/temp_model_images/";
        String filePath = ecatPath + "dealer/ecat_print/temp_model_images/";
        int result1 = 1, result2 = 0;
        int cd_no = 0;
        int patch_no = 0;
        String mno = "";
        String result = "";
        String filename = "";
        //String name = "";
        String mdesc = "";
        String effectiveDate = "";
        java.sql.Date effectiveDateM = null;
        java.sql.Date doneOn = null;
        String result_isCompPresent = "";
        String flag = null;
        PreparedStatement ps = null;
        Connection conn = null;
        Enumeration files = null;
        MultipartRequest multi = null;
        boolean isFilesAttached = true;
        int seqOrder = 0;
        String message = "";
        Vector msgVec = new Vector();

        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        try {
            //getting a connection
            conn = holder.getConnection();
            conn.setAutoCommit(false);
            String modelNo = object_pageTemplate.MODEL_NO;
            long maxSize = PageTemplate.maxSize;
            if (request.getParameter("variantName").equalsIgnoreCase("1Oth") || request.getParameter("engineModel").equalsIgnoreCase("1Oth")) {
                File dest_folder = new File(filePath);
                String name = "";

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
            mno = request.getParameter("modelno");
            mno = mno.trim();
            mno = mno.toUpperCase();
            if (mno != null) {

                //model_image.renameTo(new File(dest + mno + ".jpg"));
                hs.setAttribute("modelno", mno);
                mdesc = request.getParameter("modeldesc");
                effectiveDate = request.getParameter("effectiveDate");
                // System.out.println("effectiveDate===="+effectiveDate);
                String variantN = "";
                String engineModel = "";
                String applicationType = "";
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


                //checking whether the component exists in database or not.
                result_isCompPresent = methodutil.isCompPresent(mno, conn);
                Vector levelidVec = new Vector();
                levelidVec = dao.getModelEngineSeries(conn);
                if (!result_isCompPresent.equals("notpresent")) {
                    flag = "exist";
                } else {
                    flag = "notexist";
                }
                //if not exist.
                if (flag.equals("notexist")) {
                    String query3 = "select max(SEQ_ORDER) from CAT_MODEL_CLASSIFICATION(NOLOCK)";
                    ps = conn.prepareStatement(query3);
                    ResultSet rs = ps.executeQuery();
                    if (rs.next()) {
                        seqOrder = rs.getInt(1);
                    }
                    rs.close();
                    //getting CD Number.
                    cd_no = methodutil.getCD_No(conn);
                    //getting PATCH Number.
                    patch_no = methodutil.getPatch_No(conn);
                    //getting DATE.
                    //Date date = methodutil.getSQLTodaysDate();
                    //insertion of model into MODELS in database.
                    String query2 = "insert into CAT_MODELS values(?,?,?,?,?,?,?)";
                    ps = conn.prepareStatement(query2);
                    ps.setString(1, mno);
                    ps.setDate(2, methodutil.getSQLTodaysDate());
                    ps.setString(3, mdesc);
                    ps.setInt(4, cd_no);
                    ps.setInt(5, patch_no);
                    ps.setString(6, "INCOMPLETE");
//                    try {
//                        doneOn = new Date(sdfFormat.parse(effectiveDate).getTime());
//                    } catch (ParseException ex) {
//                        logger.error(WebConstants.logException, ex);
//                    }
//                    //System.out.println("doneOn=="+doneOn);
                    ps.setDate(7, methodutil.getSQLTodaysDate());
                    ps.executeUpdate();
                    ps.close();

                    //insertion into MODEL_CLASSIFICATION in database.
                    String query1 = "insert into CAT_MODEL_CLASSIFICATION values(?,?,?,?,?,?,?)";
                    ps = conn.prepareStatement(query1);
                    ps.setString(1, mno);
                    ps.setString(2, variantN);
                    ps.setString(3, engineModel);
                    ps.setString(4, applicationType);
                    ps.setString(5, "NA");
                    ps.setString(6, "NA");
                    ps.setInt(7, seqOrder + 1);
                    result1 = ps.executeUpdate();
                    ps.close();

//                    if (request.getParameter("modelno") != null) {
//                        String modelImage = request.getParameter("modelno");
//                        String inputName = ecatPath + "dealer/ecat_print/temp_model_images/" + modelImage + ".jpg";
//                        modelImage = modelImage.trim();
//                        modelImage = modelImage.toUpperCase();
//                        String outputName = ecatPath + "dealer/ecat_print/model_images/" + modelImage + ".jpg";
//                        FileChannel fileSource = new FileInputStream(inputName).getChannel();
//                        FileChannel destination = new FileOutputStream(outputName).getChannel();
//                        destination.transferFrom(fileSource, 0, fileSource.size());
//                    }

                    if (request.getParameter("variantName").equalsIgnoreCase("1Oth")) {
                        String productImages = request.getParameter("variantNameOth");
                        String inputName = ecatPath + "dealer/ecat_print/temp_model_images/" + productImages + ".jpg";
                        productImages = productImages.trim();
                        productImages = productImages.toUpperCase();  //engine_series_image
                        String outputName = ecatPath + "dealer/ecat_print/engine_series_image/" + productImages + ".jpg";
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
                    hs.setAttribute("flag", "add_model");
                    conn.commit();
                    return mapping.findForward(SUCCESS);
                } else {

                    hs.setAttribute("modelstatus", "exist");
                    hs.setAttribute("flag", "add_model");
                    hs.setAttribute("exist_value", result_isCompPresent);
                    hs.setAttribute("levelidVec", levelidVec);
                    conn.commit();
                    return mapping.findForward("exist");
                }
            } else {

                //boolean m=deleteFolder(new File(filePath));
                request.setAttribute("option", "create");
                request.setAttribute("result", "" + modelNo + " Image File " + filename + " of " + modelNo + " '" + mno + "' is not a valid File.<br>Hence " + modelNo + " Creation Process Aborted.");
                return mapping.findForward("fail");
            }


        } catch (Exception exception) {
            exception.printStackTrace();
            conn.rollback();
            return mapping.findForward("fail");
        } finally {
        }

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
