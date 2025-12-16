/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Other.Action;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import EAMG.Other.ActionFormBean.CreatePDFActionForm;
import dbConnection.dbConnection;
import viewEcat.comEcat.PageTemplate;


/**
 *
 * @author manish.kishore
 */
public class CreatePDFAction extends org.apache.struts.action.Action {

    /* forward name="success" path="" */
    private static final String SUCCESS = "success";
    private static final String FAIL = "success";
    //private static final String  FAIL="fail";
    private Logger logger = Logger.getLogger(this.getClass());

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
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {

        String forward = null;
        String option = null;
        Connection conn = null;
        int inserStatus = 0;
        boolean isCreated = false;
        String group = null;
        String model = null;
        String contextPath = null;
        PrintWriter ps = null;
        String pdfFileNameS = null;
        HttpSession session = request.getSession(true);
        String session_id = session.getId();
        String getSession = (String) session.getValue("session_value");
        String server_name = (String) session.getValue("server_name");
        String ecatPATH = (String) session.getValue("ecatPATH");
        String mainURL = (String) session.getValue("mainURL");
        String date_OR_serial = (String) session.getValue("date_OR_serial");
        java.sql.Date inputDate = (java.sql.Date) session.getValue("input_Date");
        java.sql.Date buckleDate = (java.sql.Date) session.getValue("buckleDate");
        String serialNo = (String) session.getValue("input_serialNo");



        ///////////////////////////// CREATE SESSION /////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////

        ////////////////////////////////////////////////////////////////////////////////
        /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
        ////////////////////////////////////////////////////////////////////////////////

        PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);


        String group_imagesURL = "";
        String engineSeries = null;
        String engineModel = null;
        group_imagesURL = object_pageTemplate.group_imagesURL();
        String servletURL = "";
        servletURL = object_pageTemplate.servletURL();

        String imagesURL = object_pageTemplate.imagesURL();


        try {

            conn = new dbConnection().getDbConnection();
            if (conn == null) {
                logger.error("Failed to Get Connection");
                ///forward = false;
            } else {
                contextPath = request.getContextPath();
                CreatePDFActionForm pdfBean = (CreatePDFActionForm) form;//dataList

                option = pdfBean.getOption();
                CreatePDFDAO pdfDao = new CreatePDFDAO();
                if (option.equals("groupPDF")) {
                    group = pdfBean.getGroup();

                    File PdfFolder = null;
                    PdfFolder = new File(ecatPATH + "/dealer/ecat_print/group_pdf/");
                    if (!PdfFolder.exists()) {
                        PdfFolder.mkdir();
                    }
                    String fileName = ecatPATH + "/dealer/ecat_print/group_pdf/" + group + ".pdf";
                    imagesURL = object_pageTemplate.imagesURL();
                    String headerEscort = ecatPATH + "/dealer/images/" + "piaggio_mini.jpg";
                    //Convert SVG to JPEG file//

                    ServletContext context = session.getServletContext();
                    String realContextPath = context.getRealPath(request.getContextPath());
                    //System.out.println("real context path= " + realContextPath);
                    String context_path = realContextPath.substring(0, realContextPath.lastIndexOf("\\"));
                    String SVGfileName = context_path + "\\svg\\" + group + ".svg";

                    //System.out.println("SVGfileName " + SVGfileName);
                   /* try {
                        JPEGTranscoder transcoder = new JPEGTranscoder();
                        transcoder.addTranscodingHint(JPEGTranscoder.KEY_XML_PARSER_CLASSNAME, "org.apache.crimson.parser.XMLReaderImpl");
                        transcoder.addTranscodingHint(JPEGTranscoder.KEY_QUALITY, new Float(1.0));
                        transcoder.addTranscodingHint(JPEGTranscoder.KEY_WIDTH, new Float(681));
                        transcoder.addTranscodingHint(JPEGTranscoder.KEY_HEIGHT, new Float(711));
                        TranscoderInput input = new TranscoderInput(new FileInputStream(SVGfileName));
                        String imageFolder = ecatPATH + "dealer/ecat_print/group_image/";
                        File imageFolderF = new File(imageFolder);
                        if (!imageFolderF.exists()) {
                            imageFolderF.mkdirs();
                        }
                        File oldfileCreate = new File(imageFolder, group.concat(".jpg"));
                        if (oldfileCreate.exists()) {
                            oldfileCreate.delete();
                        }
                        String newFileName = imageFolder + "/" + group + ".jpg";
                        File imgFileF = new File(newFileName);

                        OutputStream ostream = new FileOutputStream(imgFileF);
                        TranscoderOutput output = new TranscoderOutput(ostream);
                        transcoder.transcode(input, output);
                        ostream.close();
                        isCreated = true;

                    } catch (Exception e) {
                        e.printStackTrace();
                        isCreated = false;
                    }*/

                    ArrayList<String> engineSeriesdata = pdfDao.getEngineSeriesDetails(conn, model,group);

                    if (engineSeriesdata.size() > 0) {
                        engineSeries = engineSeriesdata.get(0);
                        engineModel = engineSeriesdata.get(1);
                    }

                    isCreated=true;
                    //END
                    if (isCreated) {
                        inserStatus = pdfDao.createGroupPDF(conn, ps, date_OR_serial, inputDate, serialNo, group, buckleDate, group_imagesURL, imagesURL, fileName, headerEscort,engineSeries,engineModel,ecatPATH);
                    }

                    if (inserStatus == 1) {
                        request.setAttribute("result", "SUCCESS");
                        request.setAttribute("show_message", "\"" + group + "\" PDF Created Successfully.");
                        request.setAttribute("optionLink", "YES");
                        request.setAttribute("optionLinkName", "Create Another");
                        request.setAttribute("optionLinkURL", "/authJSP/other/Create_Group_PDF.jsp");
                        forward = SUCCESS;
                    } else {
                        request.setAttribute("result", "FAILURE");
                        request.setAttribute("show_message", "Unable To Create Group PDF,Try Again.");
                        request.setAttribute("optionLink", "YES");
                        request.setAttribute("optionLinkName", "Try Again");
                        request.setAttribute("optionLinkURL", "/authJSP/other/Create_Group_PDF.jsp");
                        forward = SUCCESS;
                    }
                    if (!isCreated) {
                        request.setAttribute("result", "FAILURE");
                        request.setAttribute("show_message", "Unable To Create Group PDF.SVG file does not Exist!");
                        request.setAttribute("optionLink", "YES");
                        request.setAttribute("optionLinkName", "Try Again");
                        request.setAttribute("optionLinkURL", "/authJSP/other/Create_Group_PDF.jsp");
                        forward = SUCCESS;
                    }
                } else if (option.equals("modelPDF")) {
                    model = pdfBean.getModel();

                    imagesURL = object_pageTemplate.imagesURL();
                    String headerEscort = ecatPATH + "/dealer/images/" + "header1.jpg";
                    ecatPATH = PageTemplate.ecatPATH;

                    ArrayList<String> engineSeriesdata = pdfDao.getEngineSeriesDetails(conn, model,null);

                    if (engineSeriesdata.size() > 0) {
                        engineSeries = engineSeriesdata.get(0);
                        engineModel = engineSeriesdata.get(1);
                    }

                    inserStatus = pdfDao.createModelPDF(model, conn, imagesURL, headerEscort, ecatPATH,engineSeries,engineModel);


                    //inserStatus = pdfDao.createModelPDF(pdfFileNameS, server_name, ecatPATH, mainURL, inputDate, model, 0, conn, imagesURL);

                    if (inserStatus == 1) {
                        request.setAttribute("result", "SUCCESS");
                        request.setAttribute("show_message", "\"" + model + "\" PDF Created Successfully.");
                        request.setAttribute("optionLink", "YES");
                        request.setAttribute("optionLinkName", "Create Another");
                        request.setAttribute("optionLinkURL", "/authJSP/other/Create_Model_PDF.jsp");
                        forward = SUCCESS;
                    } else {
                        request.setAttribute("result", "FAILURE");
                        request.setAttribute("show_message", "Unable To Create Model PDF.");
                        request.setAttribute("optionLink", "YES");
                        request.setAttribute("optionLinkName", "Try Again");
                        request.setAttribute("optionLinkURL", "/authJSP/other/Create_Model_PDF.jsp");
                        forward = SUCCESS;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("Caught in Exception", e);
        } finally {
            try {
                conn.commit();
                if (conn != null) {
                    conn.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
                logger.error("Caught in Final Exception", e);
            }
        }
        return mapping.findForward(forward);
    }
}
