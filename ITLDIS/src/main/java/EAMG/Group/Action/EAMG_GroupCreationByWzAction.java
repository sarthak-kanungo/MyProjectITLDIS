/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Group.Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.sql.Connection;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.EAMG.common.EAMG_MethodsUtility;

import EAMG.Group.ActionFormBean.EAMG_GroupCreationByWzActionForm;
import EAMG.Group.DAO.EAMGGroupDAO_R;
import viewEcat.comEcat.ConnectionHolder;
import viewEcat.comEcat.PageTemplate;

/**
 *
 * @author manish.kishore
 */
public class EAMG_GroupCreationByWzAction extends org.apache.struts.action.Action {

	 /* forward name="success" path="" */
	 private static final String SUCCESS = "success";
	 private static final String FAILURE = "failure";
	 private static final String MESSAGE = "message";
	 private Logger logger = Logger.getLogger(this.getClass());

	 @Override
	 public ActionForward execute(ActionMapping mapping, ActionForm form,
				HttpServletRequest request, HttpServletResponse response)
				throws Exception {

		  /*********************Getting the session values**************************/
		  HttpSession session = request.getSession();
		  String server_name = (String) session.getValue("server_name");
		  String ecatPath = (String) session.getValue("ecatPATH");
		  String mainURL = (String) session.getValue("mainURL");
		  PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
		  ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
		  String svgPath = object_pageTemplate.svgPATH;
		  EAMGGroupDAO_R dao = null;
		  Connection conn = null;
		  boolean isExist = false, isImageUploaded = false, isSvgUploaded = false;
		  EAMG_GroupCreationByWzActionForm groupform = (EAMG_GroupCreationByWzActionForm) form;
		  Vector<String> sqlVec = new Vector<String>();
		  String forward = SUCCESS;
		  try {
				conn = holder.getConnection();
				conn.setAutoCommit(false);
				String groupno = null, comp = null, desc = null, option = null;
				String sql = null;
				String userid = "" + session.getAttribute("userCode");
				FormFile ImageFile = null;
				FormFile SvgFile = null;
				Date today = new Date();
				ArrayList<String> result = null;
				SimpleDateFormat df1 = new SimpleDateFormat("dd-MMM-yy");
				groupno = groupform.getGroupname().toUpperCase();
				session.setAttribute("group_number", groupno);
				desc = groupform.getGroupdesc();
				comp = groupform.getComp();
				option = groupform.getOption();
				dao = new EAMGGroupDAO_R(conn);

				if (option != null && option.equals("AttachParts")) {
					 result = dao.isCompExist(groupform);
					 if (!result.isEmpty()) {
						  request.setAttribute("result", "Group '" + groupno + "' can not be created as Component '" + result.get(1) + "' does not exist in database.");
						  forward = MESSAGE;
					 } else {
						  sqlVec = (Vector) session.getAttribute("sqlVec");
						  //System.out.println("sqlVec..."+sqlVec);
						  //

//                    File destFolder = new File(ecatPath + "dealer/ecat_print/group_image");
//                    if (!destFolder.exists()) {
//                        destFolder.mkdirs();
//                    }
//                    String filePath = ecatPath + "dealer/ecat_print/temp_group_image";
//                    ImageFile = groupform.getImportimagefile();
//                    String fileName = ImageFile.getFileName();
//
//                    SvgFile = groupform.getImportSvgFile();
//                    String svgFileName = SvgFile.getFileName();
//                    isImageUploaded = dao.upload(fileName, filePath, ImageFile);
//                    isSvgUploaded=dao.uploadSvg(svgFileName, filePath, SvgFile);
//
//                    File grp_image_file = new File(ecatPath + "dealer/ecat_print/temp_group_image/" + groupno + ".jpg");
//                    if (grp_image_file.exists()) {
//                        // grp_image_file.renameTo(new File(ecatPath + "dealer/ecat_print/temp_group_image/" + groupno + ".svg"));
//                        String inputName = ecatPath + "dealer/ecat_print/temp_group_image/" + groupno + ".jpg";
//                        String groupImage=groupno.toUpperCase();
//                        String outputName = ecatPath + "dealer/ecat_print/group_image/" + groupImage + ".jpg";
//                        FileChannel fileSource = new FileInputStream(inputName).getChannel();
//                        FileChannel destination = new FileOutputStream(outputName).getChannel();
//                        destination.transferFrom(fileSource, 0, fileSource.size());
//
//                         File grp_svg_file = new File(ecatPath + "dealer/ecat_print/temp_group_image/" + groupno + ".svg");
//                         if (grp_svg_file.exists()) {
//
//                            inputName = ecatPath + "dealer/ecat_print/temp_group_image/" + groupno + ".svg";
//                            String svgImage=groupno.toUpperCase();
//                            outputName = svgPath + svgImage + ".svg";
//                            fileSource = new FileInputStream(inputName).getChannel();
//                            destination = new FileOutputStream(outputName).getChannel();
//                            destination.transferFrom(fileSource, 0, fileSource.size());
//
//                              File directory = new File(filePath);
//                               if (directory.exists())
//                               {
//                                 delete(directory);
//                               }
//
//                         }
//                    }
						  //
						  result = dao.insertIntoGroupComponent(groupform, sqlVec);

						  if (!result.isEmpty()) {
								request.setAttribute("result", result.get(0));
								request.setAttribute("flag", result.get(1));
								forward = MESSAGE;
						  }
					 }

				} else {
					 isExist = dao.checkGroup(groupno);
					 if (!isExist) {
						  int cd_no = 0;//EAMG_commonUtilMethods.getCD_No(conn);
						  int patch_no = 0;// EAMG_commonUtilMethods.getPatch_No(conn);
						  sql = "insert into cat_group_kit_detail(GRP_KIT_NO,p1,CREATION_DATE,CREATOR,CD_NO,PATCH_NO) values('" + groupno + "','" + desc + "','" + new  java.sql.Date(new java.util.Date().getTime()) + "','" + userid + "','" + cd_no + "','" + patch_no + "')";
						  sqlVec.add(sql);
						  session.setAttribute("sqlVec", sqlVec);
						  ImageFile = groupform.getImportimagefile();
						  String fileName = ImageFile.getFileName();

						  SvgFile = groupform.getImportSvgFile();
						  String svgFileName = SvgFile.getFileName();

						  String filePath = ecatPath + "dealer/ecat_print/temp_group_image";

						  File dest_folder = new File(filePath);
						  if (!dest_folder.exists()) {
								dest_folder.mkdirs();
						  }

						  isImageUploaded = dao.upload(fileName, filePath, ImageFile);
						  isSvgUploaded = dao.uploadSvg(svgFileName, filePath, SvgFile);

						  File grp_image_file = new File(ecatPath + "dealer/ecat_print/temp_group_image/" + groupno + ".jpg");
						  if (grp_image_file.exists()) {
								// grp_image_file.renameTo(new File(ecatPath + "dealer/ecat_print/temp_group_image/" + groupno + ".svg"));
								String inputName = ecatPath + "dealer/ecat_print/temp_group_image/" + groupno + ".jpg";
								String groupImage = groupno.toUpperCase();
								String outputName = ecatPath + "dealer/ecat_print/group_jpg/" + groupImage + ".jpg";
								FileChannel fileSource = new FileInputStream(inputName).getChannel();
								FileChannel destination = new FileOutputStream(outputName).getChannel();
								destination.transferFrom(fileSource, 0, fileSource.size());

								File grp_svg_file = new File(ecatPath + "dealer/ecat_print/temp_group_image/" + groupno + ".svg");
								if (grp_svg_file.exists()) {
									 inputName = ecatPath + "dealer/ecat_print/temp_group_image/" + groupno + ".svg";
									 String svgImage = groupno.toUpperCase();
									 outputName = svgPath + svgImage + ".svg";
									 fileSource = new FileInputStream(inputName).getChannel();
									 destination = new FileOutputStream(outputName).getChannel();
									 // destination.transferFrom(fileSource, 0, fileSource.size());

									 EAMG_MethodsUtility utility = new EAMG_MethodsUtility();
									 utility.writeSVGId(new File(ecatPath + "dealer/ecat_print/temp_group_image/" + groupno + ".svg"), new File(outputName));

									 File directory = new File(ecatPath + "dealer/ecat_print/temp_group_image");
									 if (directory.exists()) {
										  delete(directory);
									 }
								}
						  }
						  if (isImageUploaded && isSvgUploaded) {
								request.setAttribute("noofcomp", comp);
								request.setAttribute("groupno", groupno);
								request.setAttribute("flag", "Success");
								forward = SUCCESS;
						  } else {
								request.setAttribute("result", "Image Not Uploaded Please Try Again.");
								request.setAttribute("flag", "Failure");
								forward = MESSAGE;
						  }
					 } else {
						  request.setAttribute("result", "Group " + groupno + " Already Exist in Database.");
						  request.setAttribute("flag", "Failure");
						  forward = MESSAGE;
					 }
				}



		  } catch (Exception e) {
				e.printStackTrace();
				conn.rollback();
				logger.error("Caught in Exception", e);

		  } finally {
				try {
				} catch (Exception e) {
					 e.printStackTrace();
					 logger.error("Caught in Final Exception", e);
				}
		  }
		  return mapping.findForward(forward);
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
