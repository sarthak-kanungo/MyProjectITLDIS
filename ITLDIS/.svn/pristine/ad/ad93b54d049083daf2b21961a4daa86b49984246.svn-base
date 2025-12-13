/*
 * AT4_kit_creation.java
 *
 * Created on November 14, 2008, 2:54 PM
 */
package EAMG.Tool.Action;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.oreilly.servlet.MultipartRequest;

import EAMG.Part.Action.EAMG_ValidateXlsWithTemplate;
import jxl.Sheet;
import jxl.Workbook;
import viewEcat.comEcat.ConnectionHolder;

/**
 *
 * @author Avinash.Pandey
 * @version
 */
public class EAMG_ToolCreationAction extends Action {

	 /* forward name="success" path="" */
	 private final static String SUCCESS = "success";
	 private final static String INSERTSUCCESS = "insertsuccess";

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
		  //System.out.println("r we here");
		  HttpSession session = request.getSession();
		  ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
		  Connection conn = conn = holder.getConnection();
		  PreparedStatement ps = null, ps1 = null;
		  ResultSet rs = null;
		  String compno = null, comptype = null, categoryType = null, compdesc = null, compremark = null;
		  String user_id = session.getAttribute("userCode").toString();
		  try {
				String result = "";
				String ecatPath = session.getAttribute("ecatPATH").toString();

				String fileName = request.getParameter("excelFile");
				String comp_type = request.getParameter("comp_type");

				String contextPath = request.getContextPath();

				//System.out.println("ecatPath is ;"+ecatPath);

				File dest_folder = new File(ecatPath + "/dealer/tempExcels/" + user_id);
				//System.out.println("dest_folder is :"+dest_folder);

				if (!dest_folder.exists()) {
					 //System.out.println("not exists");
					 dest_folder.mkdirs();
				}

				String dest = ecatPath + "/dealer/tempExcels/" + user_id;
				//System.out.println("dest:"+dest);

				MultipartRequest multi = null;
				boolean isFilesAttached = true;
				try {
					 multi = new MultipartRequest(request, dest, 5 * 1024 * 1024);
					 //System.out.println("multi:"+multi);
				} catch (IOException e1) {
					 isFilesAttached = false;
					 result = "Posted Content exceeds the size limit. Please Attach the files with size less than 5 MB. ";
					 request.setAttribute("result", result);
					 //return mapping.findForward(FAILURE);
				}

				//String fileName="";
				if (isFilesAttached == true) {
					 Enumeration files = multi.getFileNames();

					 int cnt = 0;
					 while (files.hasMoreElements()) {
						  String name = (String) files.nextElement();
						  fileName = multi.getFilesystemName(name);
						  //System.out.println("filename:"+fileName);
					 }
				}

				File xlsfile = new File(dest + "/" + fileName);
				String path = dest + "/" + fileName;
				Workbook wb = null;
				try {
					 wb = Workbook.getWorkbook(xlsfile);
				} //if excel is currupted or wrong path specified.
				catch (Exception e) {
					 e.printStackTrace();
					 result = "Error occured while reading Excel. It may be due to corrupted Excel file or invalid file path.<br> Hence Tool Creation Process aborted. Attach valid Excel to complete the Process successfully.";
					 result += "<br><font class='red-for-temmplate-link' ><a href=\"" + contextPath + "/authJSP/EAMG_Tool/EAMG_create_tool.jsp\">Try Again...</a></font>";
					 request.setAttribute("result", result);
					 request.setAttribute("comp_type", comp_type);
					 return mapping.findForward("readingerr");

				}

				int no_of_Sheet = wb.getNumberOfSheets();//No. of WorkSheet in WorkBook
				int index = 0;
				Sheet sheet = wb.getSheet(index);

				result = new EAMG_ValidateXlsWithTemplate().isLubeToolExcelValidated(xlsfile, conn);

				if (!result.equals("Excel Validation Successful")) {
					 result = "Error in " + result;
				} else {

					 Vector result1 = new Vector();
					 Vector message = new Vector();
					 Vector exists = new Vector();

					 wb = Workbook.getWorkbook(xlsfile);
					 sheet = wb.getSheet(0);
					 String insertpartquery = "Insert into cat_part (part_no, part_type,CREATION_DATE,CREATOR,CD_NO,PATCH_NO,p1,p3, p4, np4) VALUES(?,?,?,?,?,?,?,?,?,?)";
					 ps = conn.prepareStatement(insertpartquery);
					 ps1 = conn.prepareStatement("Select PART_NO from CAT_PART(NOLOCK) where PART_NO=?");
					 int row = 3, column = 0;
					 int partCounter = 0;
					 int noofparts = 0;
					 int errind = 0;
					 //loop for reading part parameters row wise.
					 while (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("End"))) {
						  compno = sheet.getCell(column, row).getContents().trim();
						  compno = compno.toUpperCase();
						  column++;
						  comptype = sheet.getCell(column, row).getContents().trim();
						  column++;
						  compdesc = sheet.getCell(column, row).getContents().trim();
						  column++;
						  compremark = sheet.getCell(column, row).getContents().trim();
						  column++;
						  categoryType = sheet.getCell(column, row).getContents().trim();
						  column++;
//                serviciability = sheet.getCell(column, row).getContents().trim();
//                column++;
//                partStatus = sheet.getCell(column, row).getContents().trim();
//                column++;


						  //checking the part already exists or not.

						  ps1.setString(1, compno);
						  rs = ps1.executeQuery();

						  if (!rs.next()) {

								//System.out.println("part_no"+compno);
								java.sql.Date todayDate = new java.sql.Date(new java.util.Date().getTime());
								//getting CD number
								int cdno = 0;
								//getting PATCH number
								int patchno = 0;
								//insertion into COMP_DETAIL.

								ps.setString(1, compno);
								ps.setString(2, comptype.toUpperCase());
								ps.setDate(3, todayDate);
								ps.setString(4, user_id);
								ps.setInt(5, cdno);
								ps.setInt(6, patchno);
								ps.setString(7, compdesc);
								if (!compremark.equals("") || !compremark.isEmpty()) {
									 ps.setString(8, compremark);
								} else {
									 ps.setString(8,null);
								}
								ps.setString(9,null);
								if (!categoryType.equals("") || !categoryType.isEmpty()) {
									 ps.setString(10, categoryType);
								} else {
									 ps.setString(10, null);
								}

								partCounter++;

								ps.addBatch();

								if (partCounter == 200) {
									 ps.executeBatch();
									 partCounter = 0;
								}
								noofparts++;


						  } else {
								if (!exists.contains(compno)) {
									 exists.add(compno);
								}

						  }
						  errind++;
						  column = 0;
						  row++;
					 }
					 ps.executeBatch();

					 conn.commit();
					 ps.close();
					 ps1.close();

					 if (noofparts == 1) {
						  message.add(0, "Component has been Added Successfully.");
						  message.add(1, "Add More");

					 } else if (noofparts > 1) {
						  message.add(0, "Components have been Added Successfully.");
						  message.add(1, "Add More");
					 } else {
						  message.add(0, "No Component has been Added.");
						  message.add(1, "Try Again");
					 }
					 message.add(2, "" + noofparts);

					 result1.add(message);
					 result1.add(exists);

					 request.setAttribute("insertionresult",result1);
					 return mapping.findForward(INSERTSUCCESS);
				}
				String toolNo = sheet.getCell(0, 3).getContents();
				session.setAttribute("comp_no", toolNo.toUpperCase());
				session.setAttribute("filepath", path);
				request.setAttribute("option", "tool_validated");
				request.setAttribute("comp_type", comp_type);
				session.setAttribute("result", result);
		  } catch (Exception e) {
				e.printStackTrace();

		  }
		  return mapping.findForward(SUCCESS);

	 }
}
