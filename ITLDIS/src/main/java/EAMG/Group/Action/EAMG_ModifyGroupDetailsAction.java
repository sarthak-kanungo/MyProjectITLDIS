/*
 * EAMG_ModifyGroupDetailsAction.java
 *
 * Created on November 19, 2008, 4:53 PM
 */
/*
 File Name: 	EAMG_ModifyGroupDetailsAction.java
 PURPOSE: 	:To modify Group Details:,
 1.Group description
 2.Group Image
 3.Group Parameters



 */
package EAMG.Group.Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
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
import viewEcat.comEcat.PageTemplate;

/**
 *
 * @author divya.rani
 * @version
 */
public class EAMG_ModifyGroupDetailsAction extends Action {

    private final static String SUCCESS = "success";
    private final static String FAILURE = "fail";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Connection conn = null;

        HttpSession session = request.getSession();
        String group_no = "" + session.getAttribute("group_no");
        String ecatPath = "" + session.getAttribute("ecatPATH");
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        String server_name = (String) session.getValue("server_name");
        String mainURL = (String) session.getValue("mainURL");
        PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
        int tableImageWidth = object_pageTemplate.tableImageWidth;
        int tableImageHeight = object_pageTemplate.tableImageHeight;
        String svgPath = object_pageTemplate.svgPATH;

        String result = "";
        String filePath = ecatPath + "dealer/ecat_print/temp_model_images/";
        File dest_folder = new File(ecatPath + "dealer/ecat_print/temp_group_image");
        if (!dest_folder.exists()) {
            dest_folder.mkdirs();
        }

        String dest = ecatPath + "dealer/ecat_print/temp_group_image/";
        //System.out.println("dest:" + dest);

        MultipartRequest multi = null;
        boolean isFilesAttached = true;
        try {
            multi = new MultipartRequest(request, dest, 5 * 1024 * 1024);

            //System.out.println("multi:" + multi);
        } catch (IOException e1) {
            isFilesAttached = false;
            result = "Posted Content exceeds the size limit. Please Attach the files with size less than 5 MB. ";
            request.setAttribute("result", result);
            return mapping.findForward("fail");
        }
        String filename = "";
        if (isFilesAttached == true) {
            Enumeration files = multi.getFileNames();
            int cnt = 0;
            while (files.hasMoreElements()) {
                String name = (String) files.nextElement();
                filename = multi.getFilesystemName(name);
                //System.out.println("filename:" + filename);

            }
        }

        /*--Getting Param-desc from COMP_PARAM_MASTER --*/

        try {
            conn = holder.getConnection();

            String grp_desc = multi.getParameter("desc").trim();
            String change_img = multi.getParameter("change_img");
            String change_svg_img = multi.getParameter("change_svg_img");
            //System.out.println("change_img=="+change_img+"==and==change_svg_img=="+change_svg_img);
            if (change_img.equals("yes")) {

                File grp_image_file = new File(ecatPath + "dealer/ecat_print/temp_group_image/" + group_no + ".jpg");
                if (grp_image_file.exists()) {

                    String groupImage = group_no;
                    // grp_image_file.renameTo(new File(ecatPath + "dealer/ecat_print/temp_group_image/" + groupno + ".svg"));
                    String inputName = ecatPath + "dealer/ecat_print/temp_group_image/" + group_no + ".jpg";
                    String outputName = ecatPath + "dealer/ecat_print/group_jpg/" + groupImage + ".jpg";

                    File oldFile = new File(outputName);
                    if (oldFile.exists()) {
                        oldFile.delete();
                    }
                    FileChannel fileSource = new FileInputStream(inputName).getChannel();
                    FileChannel destination = new FileOutputStream(outputName).getChannel();
                    destination.transferFrom(fileSource, 0, fileSource.size());
                }


            }
            if (change_svg_img.equals("svgYes")) {

                File grp_svg_file = new File(ecatPath + "dealer/ecat_print/temp_group_image/" + group_no + ".svg");
                if (grp_svg_file.exists()) {
                    String svgImage = group_no;
                    String inputName = ecatPath + "dealer/ecat_print/temp_group_image/" + group_no + ".svg";
                    String outputName = svgPath + svgImage + ".svg";

                    File oldFile = new File(outputName);
                    if (oldFile.exists()) {
                        oldFile.delete();
                    }

                    FileChannel fileSource = new FileInputStream(inputName).getChannel();
                    FileChannel destination = new FileOutputStream(outputName).getChannel();
                    EAMG_MethodsUtility utility = new EAMG_MethodsUtility();
                    utility.writeSVGId(new File(ecatPath + "dealer/ecat_print/temp_group_image/" + group_no + ".svg"), new File(outputName));
                    //destination.transferFrom(fileSource, 0, fileSource.size());
                }

            }
            if (change_svg_img.equals("svgYes") || change_img.equals("yes")) {

                File directory = new File(ecatPath + "dealer/ecat_print/temp_group_image");
                if (directory.exists()) {

                    delete(directory);
                }
            }
            //Statement stmt;
            PreparedStatement stmt = null;
            conn.setAutoCommit(false);
           // stmt = conn.createStatement();
            String sql = "update CAT_GROUP_KIT_DETAIL set p1='" + grp_desc + "' where GRP_KIT_NO='" + group_no + "'";
            //stmt.executeUpdate(sql);
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            stmt.close();
            conn.commit();

        } catch (SQLException e) {
            conn.rollback();
            e.printStackTrace();
            //throw new amw_SQLExceptionCatcher("SQLException", e);
        } catch (Exception e) {
            conn.rollback();
            e.printStackTrace();
            //throw new amw_ExceptionCatcher("Exception", e);
        } finally {
            //conn.close();
        }
        return mapping.findForward(SUCCESS);
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
