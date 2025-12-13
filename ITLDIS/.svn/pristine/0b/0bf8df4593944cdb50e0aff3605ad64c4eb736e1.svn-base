/*
 * EAMG_KitInsertionByWzAction.java
 *
 * Created on Nov 16, 2011, 4:51 PM
 */
/*
File Name: EAMG_KitInsertionByWzAction.java
PURPOSE: 	:TO CREATE KITS BY WIZARD.
HISTORY:
DATE        	BUILD     	AUTHOR    	MODIFICATIONS
16/11/11       1.0            Avinash.Pandey      $$1 Created
 */
package EAMG.Kit.Action;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.LinkedHashMap;

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
 * @author avinash.pandey
 * @version
 */
public class EAMG_KitInsertionByWzAction extends Action {

    /* forward name="success" path="" */
    private final static String SUCCESS = "success";

    @Override
    public ActionForward execute(ActionMapping mapping, ActionForm form,
            HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        //System.out.println("In Kit Insertion.");
        ////////////////////getting the values from session//////////////////////
        HttpSession session = request.getSession();
        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
        String user_id = session.getAttribute("userCode").toString();
        String option1 = "" + session.getAttribute("option");
        if (option1 == null) {
            option1 = "create";
        }
        String categoryType = "";
        String kitno = "" + session.getAttribute("kitno");
        String kitdesc = (String)session.getAttribute("kitdesc");
        String kitRemrks = (String) session.getAttribute("kit_rmk");

		  if(kitRemrks!=null && kitRemrks.equals(""))
				kitRemrks=null;

		  if (option1.equals("create")) {
            categoryType = session.getAttribute("categoryType").toString();
        }
        String[] qty = request.getParameterValues("qty");
        String[] components = request.getParameterValues("components");
		  LinkedHashMap<String, String> partMap = (LinkedHashMap) session.getAttribute("partMap");
        /////////////////////////////////////////////////////////////////////////

        //declaration of variables used.
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
        String result = "";
        String option = "";
        int cd_no = 0;
        int patch_no = 0;
        Connection conn = null;
        PreparedStatement ps = null,ps1=null;
        String query = "";
        try {
            conn = holder.getConnection();
            conn.setAutoCommit(false);
            if (option1.equals("modify")) {
                query = "delete from CAT_S_KIT_BOM where KIT_NO='" + kitno + "'";
                ps = conn.prepareStatement(query);
                ps.execute();
                ps.close();
                //insertion in S_KIT_BOM in database.
                query = "insert into CAT_S_KIT_BOM values(?,?,?,?)";
                ps1 = conn.prepareStatement(query);
                for (int i = 0; i < components.length; i++)
					 {
                    ps1.setString(1, kitno);
                    ps1.setString(2, components[i]);
                    ps1.setString(3, qty[i]);
                    ps1.setString(4, partMap.get(components[i]));
                    ps1.addBatch();

						  if(i%200==0)
								 ps1.executeBatch();
                }
                ps1.executeBatch();
                ps1.close();
                result = "Kit '" + kitno + "' is updated successfully.";

            } else {
                //getting CD Number.
                cd_no = 0;
                //getting PATCH Number.
                patch_no = 0;
                //getting DATE.
                Date date = methodutil.getSQLTodaysDate();

                //insertion in COMP_DETAIL in database.
                query = "Insert into CAT_PART(part_no,part_type,p1,CD_NO,PATCH_NO,CREATION_DATE,p2,p3,np4,creator)  VALUES(?,?,?,?,?,?,?,?,?,?)";
                ps = conn.prepareStatement(query);
                ps.setString(1, kitno);
                ps.setString(2, "KIT");
                ps.setString(3, kitdesc);
                ps.setInt(4, cd_no);
                ps.setInt(5, patch_no);
                ps.setDate(6, date);
                ps.setString(7, null);
                ps.setString(8, kitRemrks);
               
                if (!categoryType.equals("") || !categoryType.isEmpty()) {
                    ps.setString(9, categoryType);
                } else {
                    ps.setString(9, null);
                }
                ps.setString(10, user_id);
                ps.executeUpdate();
					 ps.close();
                

                //insertion in S_KIT_BOM in database.
                query = "insert into CAT_S_KIT_BOM values(?,?,?,?)";
                ps1 = conn.prepareStatement(query);
                for (int i = 0; i < components.length; i++)
					 {
                    ps1.setString(1, kitno);
                    ps1.setString(2, components[i]);
                    ps1.setString(3, qty[i]);
                    ps1.setString(4, partMap.get(components[i]));
                    ps1.addBatch();

						  if(i%200==0)
								 ps1.executeBatch();
                }
                ps1.executeBatch();
                ps1.close();
                result = "Kit '" + kitno + "' is created successfully.";

            }

            conn.commit();


            //copy kit_image from temp_kit_image to group_image
//            File destFolder = new File(ecatPath + "dealer/ecat_print/kit_image");
//
//            File kit_image = new File(ecatPath + "dealer/ecat_print/temp_kit_image/" + kitno + ".jpg");
//            if (kit_image.exists()) {
//                ////System.out.println("grp_image_file exists");
//                visad.install.Util.copyFile(kit_image, destFolder);
//                kit_image.delete();
//            }
//
//            //delete temp img folder
//            File src_folder = new File(ecatPath + "dealer/ecat_print/temp_kit_image");
//            src_folder.delete();


        } catch (Exception e) {
            result = "Kit '" + kitno + "' can not be created due to some database problem.";
            conn.rollback();
            e.printStackTrace();
        } finally {
            option = "KIT";
            request.setAttribute("result", result);
            request.setAttribute("option", option);
            //conn.close();                                // Closing Connection
        }
        return mapping.findForward(SUCCESS);
    }
}
