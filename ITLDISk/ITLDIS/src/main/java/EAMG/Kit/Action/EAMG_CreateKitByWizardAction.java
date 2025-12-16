/*
 * EAMG_CreateKitByWizardAction.java
 *
 * Created on January 2, 2009, 2:05 PM
 */
package EAMG.Kit.Action;

import java.sql.Connection;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.EAMG.common.EAMG_MethodsUtility;

import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author divya.rani
 * @version
 */
public class EAMG_CreateKitByWizardAction extends Action {

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
        /////////getting session//////////////////////
        HttpSession session = request.getSession();
        String ecatPath = "" + session.getAttribute("ecatPATH");
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        /////////////////////////////////////////////////////////


        //declaration of variables used.
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        Connection conn = null;
        String result = "";
        String dest = "";
        String name = "";
        String filename = "";
        String param = "";
        String kitno = "";
        String kitdesc = "";
        String kit_rmk = "";
        String categoryType = "";
        String imgchanged = "";
        String QML = "";
        String MOQ = "";
        String NDP = "";
        String[] param_values = null;
        Vector paramValueVec = new Vector();
        //requestpartRequest request = null;
        try {
            conn = holder.getConnection();
//            File dest_folder = new File(ecatPath + "dealer/ecat_print/temp_kit_image");
//            if (!dest_folder.exists()) {
//                dest_folder.mkdirs();
//            }
//
//            dest = ecatPath + "dealer/ecat_print/temp_kit_image/";

            boolean isFilesAttached = true;
            try {
                //request = new requestpartRequest(request, dest, 5 * 1024 * 1024);
                /////////getting values from requestpart//////////////////////
                kitno = request.getParameter("kitno").trim().toUpperCase();
                kitdesc = request.getParameter("desc").trim();
                kit_rmk = request.getParameter("kit_rmk").trim();
                if (request.getParameter("categoryType").trim().equalsIgnoreCase("1Oth")) {
                    categoryType = request.getParameter("categoryTypeOth").trim();

                } else {
                    categoryType = request.getParameter("categoryType").trim();

                }
//                QML=request.getParameter("QML").trim();
//	        MOQ=request.getParameter("MOQ").trim();
//                NDP=request.getParameter("NDP").trim();
                param_values = request.getParameterValues("param");
                imgchanged = request.getParameter("change_img");
                /////////////////////////////////////////////////////////

            }catch(Exception e1) {
                isFilesAttached = false;
                result = "Posted Content exceeds the size limit. Please Attach the files with size less than 5 MB. ";
            }
//            if (isFilesAttached == true) {
//                Enumeration files = request.getFileNames();
//
//                while (files.hasMoreElements()) {
//                    name = (String) files.nextElement();
//                    filename = request.getFilesystemName(name);
//                    //System.out.println("filename:" + filename);
//                }
//            }
//            File kit_image = new File(dest + "/" + filename);
//            if (imgchanged.equals("yes")) {
//                if (kit_image.exists() && kit_image.length() > 0) {
//                    kit_image.renameTo(new File(ecatPath + "dealer/ecat_print/temp_kit_image/" + kitno + ".jpg"));
//                } else {
//                    //delete kit image
//                    if (kit_image.exists()) {
//                        kit_image.delete();
//                    }
//                    session.setAttribute("result", "Kit Image File '" + filename + "' is not valid. Hence Kit Creation Process Aborted.");
//                    session.setAttribute("option", "create");
//                    //conn.close();
//                    return mapping.findForward("invalidimg");
//                }
//            }

            //checking the kit present or not in database.
            result = methodutil.isCompPresent(kitno, conn);
            //if present then return exists.
            if (!result.equals("notpresent")) {
                session.setAttribute("status", result);
                return mapping.findForward("exist");
            }
            //if not present then storing its parameter values into a Vector.
            if (param_values != null) {
                for (int i = 0; i < param_values.length; i++) {
                    param = param_values[i].trim();
                    paramValueVec.add(param);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
//        finally {
//            conn.close();
//        }
        session.setAttribute("kitno", kitno);
        session.setAttribute("option", "create");
        session.setAttribute("kitdesc", kitdesc);
        session.setAttribute("kit_rmk", kit_rmk);
        session.setAttribute("categoryType", categoryType);
//        session.setAttribute("MOQ", MOQ);
//        session.setAttribute("QML", QML);
//        session.setAttribute("NDP", NDP);
        session.setAttribute("paramValueVec", paramValueVec);
        return mapping.findForward(SUCCESS);

    }
}
