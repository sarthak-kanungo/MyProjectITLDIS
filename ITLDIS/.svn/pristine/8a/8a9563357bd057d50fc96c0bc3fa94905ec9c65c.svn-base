/*
 * EAMG_CreateGroupDataAction.java
 *
 * Created on Jan 18, 2012, 2:11 PM
 */
/*
File Name: 	EAMG_CreateGroupDataAction.java
PURPOSE: 	:TO Create Group By Excel,
1.Uploads the Group Image in temp_group_image folder.
2.validates the Data in Excel Uploaded.
3.if validated, enter the excel data in database and delete the Excel file from temp_excel_folder.
4.if not validated, delete the Excel file from temp_excel_folder.
4.if validated, copy the Group Image from temp_group_image folder to group_image folder.
5.if validated, Setting GRP_STATUS table for GROUP_BOM =1.
 *
HISTORY:
DATE        	BUILD     	AUTHOR    	MODIFICATIONS
18/02/12	1.0		AIVINASH	$$1 	Created
19/02/2012       1.0             AVINASH PANDEY      $$2 Modification: If Group Image filesize is zero then return
 */
package EAMG.Group.Action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.sql.Connection;
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
 * @author mina.yadav
 * @version
 */
public class EAMG_CreateGroupDataAction extends Action {

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
		  ///////////////////Declaring Variables/////////////////////
		  MultipartRequest multi = null;
		  String result = "";
		  boolean isFilesAttached = true;

		  Connection conn = null;
		  ///////////////////////////////////////////////////////////

		  //////////////////getting Session Attributes///////////////////
		  HttpSession session = request.getSession();
		  String server_name = (String) session.getValue("server_name");
		  String ecatPath = (String) session.getValue("ecatPATH");
		  String mainURL = (String) session.getValue("mainURL");
		  PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
		  int tableImageWidth = object_pageTemplate.tableImageWidth;
		  int tableImageHeight = object_pageTemplate.tableImageHeight;
		  String svgPath = object_pageTemplate.svgPATH;
		  String user_id = "" + session.getAttribute("userCode");
		  String group_no = "" + session.getAttribute("group_number");
		  String group_xls_file = "" + session.getAttribute("group_xls_file");
		  ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
		  String groupno = "" + session.getAttribute("group_number");
		  ///////////////////////////////////////////////////////////////


		  //////////////// GROUP IMAGE UPLOADING VIA MULTIPART in TEMP//////////////////
		  File dest_folder = new File(ecatPath + "dealer/ecat_print/temp_group_image");
		  if (!dest_folder.exists()) {
				dest_folder.mkdirs();
		  }
//        File dest_folder1 = new File(ecatPath + "dealer/ecat_print/group_thumb_view");
//        if (!dest_folder1.exists())
//        {
//            dest_folder1.mkdirs();
//        }

		  String dest = ecatPath + "dealer/ecat_print/temp_group_image/";
		  //System.out.println("dest:" + dest);
		  try {
				multi = new MultipartRequest(request, dest, 5 * 1024 * 1024);

				//System.out.println("multi:" + multi);
		  } catch (IOException e1) {
				isFilesAttached = false;
				result = "Posted Content exceeds the size limit. Please Attach the files with size less than 5 MB. ";
				//request.setAttribute("result",result);
				//return mapping.findForward("fail");
		  }
		  String filename = "";
		  if (isFilesAttached == true) {
				Enumeration files = multi.getFileNames();
				int cnt = 0;
				String name = "";
				while (files.hasMoreElements()) {
					 name = (String) files.nextElement();
					 filename = multi.getFilesystemName(name);
					 //System.out.println("filename:" + filename);
				}
		  }


		  ////////////////////////////////////////////////////////////////////////
		  File file = null;
		  String option = multi.getParameter("option");
		  //System.out.println("option with multi:" + option);
		  if (option.equals("cancel"))// if Cancel option
		  {
				//delete the temporary excel file
				//System.out.println("group_xls_file:"+group_xls_file);
				file = new File(group_xls_file);
				//System.out.println("file.exists():"+file.exists());
				if (file.exists() && file.isFile()) {
					 file.delete();
				}
				File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
				if (src_folder.exists()) {
					 src_folder.delete();
				}
				/////////////////////////////////////////////////////////////////////

				//setting result in request and return
				result = "Group Creation Process Aborted Successfully.";
				request.setAttribute("result", result);
				return mapping.findForward(SUCCESS);
		  } else //if Continue Option
		  {
				///$$2check file existence. if file with 0 bytes exists return
				File grp_image = new File(dest + "/" + filename);

				if (grp_image.exists() && grp_image.length() > 0) {
					 //////////Initialize Connection object/////////////////////////
					 conn = holder.getConnection();
					 conn.setAutoCommit(false);
					 ///////////////////////////////////////////////////////////////


					 File grp_xls_file = new File(group_xls_file);


					 Vector compVec = new Vector();//for storing components of group bom in session

					 String file_result = "";
					 try {
						  // validate the group and insert in database
						  file_result = new EAMG_ValidateGroupInsertion().validateGroup(group_no, compVec, grp_xls_file, user_id, conn);
						  ////System.out.println("compVec in group creation:"+compVec);

						  ////System.out.println("file_result in create group:"+file_result);
						  if (!file_result.equalsIgnoreCase("success"))// If not success.
						  {
								conn.rollback();
								result = file_result + "<br> Hence Group Creation is aborted. Please validate the Excel and try again.";

								//delete the temporary excel file

								if (grp_xls_file.exists() && grp_xls_file.isFile()) {
									 grp_xls_file.delete();
								}
								File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
								if (src_folder.exists()) {
									 src_folder.delete();
								}
								/////////////////////////////////////////////////////////////////////

								//delete the Group Image from temp

								if (grp_image.exists()) {
									 grp_image.delete();
								}
								///////////////////////////////////////////////////////////////////////
								// session.setAttribute("groupInsertVec", null);

						  } else {
								//copy group_image from temp_group_image to group_image
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

										  EAMG_MethodsUtility utility = new EAMG_MethodsUtility();
										  utility.writeSVGId(new File(ecatPath + "dealer/ecat_print/temp_group_image/" + groupno + ".svg"), new File(outputName));
										  //destination.transferFrom(fileSource, 0, fileSource.size());
									 }


									 File directory = new File(ecatPath + "dealer/ecat_print/temp_group_image");
									 if (directory.exists()) {

										  delete(directory);
									 }


								}

								//delete temp excel folder
//                        File src_folder = new File(ecatPath + "dealer/tempExcels/" + user_id);
//                        String listFiles[] = src_folder.list();
//                        if (listFiles != null) {
//                            File ff = null;
//                            for (int i = 0; i < listFiles.length; i++) {
//                                ////System.out.println("listFiles:"+listFiles[i]);
//                                ff = new File(ecatPath + "dealer/tempExcels/" + user_id + "/" + listFiles[i]);
//                                if (ff.exists() && ff.isFile()) {
//                                    ff.delete();
//                                }
//                            }
//                        }
//                        src_folder.delete();

								//File destFolder = new File(ecatPath + "dealer/ecat_print/group_image");

//                        File destFolder = new File(ecatPath + "dealer/ecat_print/temp_group_image");
//                        String destListFiles[] = destFolder.list();
//                        if (destListFiles != null)
//                        {
//                            File ff = null;
//                            for (int i = 0; i < destListFiles.length; i++)
//                            {
//                                ////System.out.println("listFiles:"+listFiles[i]);
//                                ff = new File(ecatPath + "dealer/ecat_print/temp_group_image/" + listFiles[i]);
//                                if (ff.exists() && ff.isFile())
//                                {
//                                    ff.delete();
//                                }
//                            }
//                        }
//                        destFolder.delete();

								conn.commit();// commit connection

								result = "Group \"" + group_no + "\" Created Successfully.";
								request.setAttribute("flag", "Success");

								//setting session

								session.setAttribute("compVec", compVec);
						  }
					 } catch (Exception ex) {
						  ex.printStackTrace();
						  result = ex.getMessage();
						  conn.rollback();// rollback Connection

					 } finally {
						  //conn.close();// Close Connection
					 }
				} else {
					 //delete group image
					 if (grp_image.exists()) {
						  grp_image.delete();
					 }

					 //delete the temporary excel file
					 file = new File(group_xls_file);
					 if (file.exists() && file.isFile()) {
						  file.delete();
					 }
					 request.setAttribute("result", "Group Image File '" + filename + "' is not a valid File.<br>Hence Group Creation Process Aborted.");
					 return mapping.findForward(SUCCESS);
				}

				//set result
				request.setAttribute("result", result);
				return mapping.findForward(SUCCESS);
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
