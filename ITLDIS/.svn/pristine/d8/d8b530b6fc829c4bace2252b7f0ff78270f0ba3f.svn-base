/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.PartDAO_CUD;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import jxl.Sheet;
import jxl.Workbook;

/**
 *
 * @author avinash.pandey
 */
public class EAMGPartDAO_R {

	 public Vector getPartStatus(Connection conn) throws Exception {
		  ResultSet rs;
		  //Statement stmt = null;
		  PreparedStatement stmt = null;
		  //stmt = conn.createStatement();
		  String query = "";
		  Vector partStatusValVec = new Vector();
		  //getting Level Description of Model from the database.
		  query = "SELECT distinct  np4  from cat_part(NOLOCK) where  np4<>'' order by np4";
		  stmt = conn.prepareStatement(query);
		  rs = stmt.executeQuery();
		 // stmt = conn.createStatement();
		  //rs = stmt.executeQuery(query);

		  while (rs.next()) {
				partStatusValVec.add(rs.getString(1));
		  }
		  //////System.out.println("levelValVec:" + levelValVec);
		  rs.close();
		  stmt.close();
		  return partStatusValVec;
	 }

	 public Vector updatePartExcel(Connection conn, File xlsfile, String flag) throws Exception {
		  Workbook workbook1 = null;
		  Sheet sheet = null;
		  ResultSet rs = null;
		  PreparedStatement pstmt = null, pstmt2 = null;
		  Vector result = new Vector();
		  Vector message = new Vector();
		  Vector exists = new Vector();
		  // Statement st2 = null;
		  int row = 3, column = 0;
		  String compno = "";
		  String comptype = "";
		  String compdesc = "";
		  String partrmk = "",serviciability="";
		  String categoryType = "";


		  Vector unmodifyListVec = new Vector();
		  int noOfParts = 0;
		  try {
				workbook1 = Workbook.getWorkbook(xlsfile);
				//getting the sheet.
				sheet = workbook1.getSheet(0);

				pstmt = conn.prepareStatement("Select part_no from cat_PART(NOLOCK) where part_no=?");
				pstmt2 = conn.prepareStatement("update CAT_PART set p1=?,p3=?,p4=?,np4=? where part_no=?");
				//loop till 'end' is encountered.
				while (!(sheet.getCell(0, row).getContents().trim().equalsIgnoreCase("End"))) {
					 column = 0;
					 //getting component number.
					 compno = sheet.getCell(column, row).getContents().trim();
					 compno = compno.toUpperCase();
					 column++;
					 if (!flag.equals("PRT")) {
						  comptype = sheet.getCell(column, row).getContents().trim();
						  column++;
					 }
					 //getting component description.
					 compdesc = sheet.getCell(column, row).getContents().trim();
					 column++;
					 partrmk = sheet.getCell(column, row).getContents().trim();
					 column++;
					 serviciability = sheet.getCell(column, row).getContents().trim();
					 column++;


					 if (!flag.equals("PRT")) {
						  categoryType = sheet.getCell(column, row).getContents().trim();
						  column++;
					 } else {
						  categoryType = "";
					 }

					 // column++;

					 pstmt.setString(1, compno.trim());
					 rs = pstmt.executeQuery();
					 if (rs.next()) {

						  noOfParts++;

						  if (compdesc.equals("")) {
								compdesc = null;
						  }
						  if (partrmk.equals("")) {
								partrmk = null;
						  }
						  if (serviciability.equals("")) {
								serviciability = "Y";
						  }
						  if (categoryType.equals("")) {
								categoryType = null;
						  }

						  pstmt2.setString(1, compdesc);
						  pstmt2.setString(2, partrmk);
						  pstmt2.setString(3, serviciability);
						  pstmt2.setString(4, categoryType);
						  pstmt2.setString(5, compno.trim());
						  pstmt2.addBatch();


						  if (noOfParts % 200 == 0) {
								//System.out.println("noOfParts" + noOfParts);
								pstmt2.executeBatch();
						  }
					 } //if component not exist in database.
					 else {
						  if (!unmodifyListVec.contains(compno)) {
								unmodifyListVec.add(compno);
						  }
					 }
					 row++;
				}
				pstmt2.executeBatch();

				conn.commit();
				pstmt.close();
				pstmt2.close();

				if (noOfParts == 1) {
					 if (!flag.equals("PRT")) {
						  message.add(0, "Component has been Modified Successfully.");
					 } else {
						  message.add(0, "Part has been Modified Successfully.");
					 }
					 message.add(1, "Modify More");

				} else if (noOfParts > 1) {
					 if (!flag.equals("PRT")) {
						  message.add(0, "Components have been Modified Successfully.");
					 } else {
						  message.add(0, "Parts have been Modified Successfully.");
					 }
					 message.add(1, "Modify More");
				} else {
					 if (!flag.equals("PRT")) {
						  message.add(0, "No Component has been Modified.");
					 } else {
						  message.add(0, "No Part has been Modified.");
					 }
					 message.add(1, "Try Again");
				}
				message.add(2, "" + noOfParts);

				result.add(message);
				result.add(exists);

		  } catch (Exception exception) {
				conn.rollback();
				exception.printStackTrace();
		  } finally {
				//conn.close();
		  }
		  return result;
	 }
}
