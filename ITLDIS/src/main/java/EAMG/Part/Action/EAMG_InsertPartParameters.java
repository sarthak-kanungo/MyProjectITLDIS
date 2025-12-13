
/*
File Name: 	EAMG_InsertPartParameters.java
PURPOSE: 	TO Insert PARTS in the DATABASE.
HISTORY:
DATE        	BUILD     	AUTHOR              MODIFICATIONS
6/11/08		1.0		Avinash.Pandey  $$1 Created
 */
package EAMG.Part.Action;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import com.EAMG.common.EAMG_MethodsUtility;

import jxl.Sheet;
import jxl.Workbook;

/**
 *
 * @author pramod.vishwakarma
 */
public class EAMG_InsertPartParameters {

    /** Creates a new instance of AT4_InsertPartParameters */
    public EAMG_InsertPartParameters() {
    }

    public Vector partInsertion(File xlsfile, Connection conn, String userid) throws Exception {

        //decalration of variables used.
        int row = 3, column = 0;
        int errind = 0;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        Vector result = new Vector();
        Vector message = new Vector();
        Vector exists = new Vector();
        Workbook workbook1 = null;
        Sheet sheet = null;
        String partno = "";
        int noOfParts = 0;
        String partdesc = "";
        String partrmk = "";
        int cdno = 0, patchno = 0;
        int partCounter = 0;
        String insertpartquery = "";
        String category = "", serviciability = "";
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();

        try {
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            insertpartquery = "Insert into cat_part (part_no, part_type,CREATION_DATE,CREATOR,CD_NO,PATCH_NO,p1,p3, p4, np4, np1, np2) VALUES(?,?,?,?,?,?,?,?,?,?,'1','1')";
            ps = conn.prepareStatement(insertpartquery);
            ps1 = conn.prepareStatement("Select PART_NO from CAT_PART(NOLOCK) where PART_NO=?");
            //loop for reading part parameters row wise.
            while (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("End"))) {
                partno = sheet.getCell(column, row).getContents().trim();
                partno = partno.toUpperCase();
                column++;
                partdesc = sheet.getCell(column, row).getContents().trim();
                column++;
                partrmk = sheet.getCell(column, row).getContents().trim();
                column++;
                serviciability = sheet.getCell(column, row).getContents().trim();
                column++;
                category = "";
                column++;
//                partStatus = sheet.getCell(column, row).getContents().trim();
//                column++;


                //checking the part already exists or not.

                ps1.setString(1, partno.trim());
                rs = ps1.executeQuery();

                if (!rs.next()) {

                    //System.out.println("part_no"+partno);
                    java.sql.Date todayDate = methodutil.getSQLTodaysDate();
                    //getting CD number
                    cdno = methodutil.getCD_No(conn);
                    //getting PATCH number
                    patchno = methodutil.getPatch_No(conn);
                    //insertion into COMP_DETAIL.

                    ps.setString(1, partno.trim());
                    ps.setString(2, "PRT");
                    ps.setDate(3, todayDate);
                    ps.setString(4, userid);
                    ps.setInt(5, cdno);
                    ps.setInt(6, patchno);
                    ps.setString(7, partdesc);
                    if (!partrmk.equals("") || !partrmk.isEmpty()) {
                        ps.setString(8, partrmk);
                    } else {
                        ps.setString(8, " ");
                    }
                    ps.setString(9, serviciability);
                    if (!category.equals("") || !category.isEmpty()) {
                        ps.setString(10, category);
                    } else {
                        ps.setString(10, null);
                    }

                    partCounter++;

                    ps.addBatch();

                    if (partCounter == 200) {
                        ps.executeBatch();
                        partCounter = 0;
                    }
                    noOfParts++;


                } else {
                    if (!exists.contains(partno)) {
                        exists.add(partno);
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

            if (noOfParts == 1) {
                message.add(0, "Part has been Added Successfully.");
                message.add(1, "Add More");

            } else if (noOfParts > 1) {
                message.add(0, "Parts have been Added Successfully.");
                message.add(1, "Add More");
            } else {
                message.add(0, "No Part has been Added.");
                message.add(1, "Try Again");
            }
            message.add(2, "" + noOfParts);

            result.add(message);
            result.add(exists);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Vector sapPartInsertion(File xlsfile, Connection conn, String userid) throws Exception {

        //decalration of variables used.
        int row = 1, column = 0;
        int errind = 0;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        Vector result = new Vector();
        Vector message = new Vector();
        Vector exists = new Vector();
        Workbook workbook1 = null;
        Sheet sheet = null;
        String partno = "";
        int noOfParts = 0;
        String partdesc = "";
        String partgrp = "";
        int partCounter = 0;
        int cdno = 0, patchno = 0;
        String insertpartquery = "", updatepartquery = "";
        String category = "", serviciability = "", moq = "", stdpack = "";
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();

        try {
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
//            insertpartquery = "Insert into cat_part (part_no, part_type,CREATION_DATE,CREATOR,CD_NO,PATCH_NO,p1, p4, np1,np2) VALUES(?,?,?,?,?,?,?,?,?,?)";
//            updatepartquery = "update cat_part set part_type=?,CREATION_DATE=?,CREATOR=?,CD_NO=?,PATCH_NO=?,p1=?, p4=?, np1=?,np2=? where part_no=?";
            insertpartquery = "Insert into cat_part (part_no, part_type,CREATION_DATE,CREATOR,CD_NO,PATCH_NO,p1, p4, np2,np1) VALUES(?,?,?,?,?,?,?,?,?,?)";
            updatepartquery = "update cat_part set part_type=?,CREATION_DATE=?,CREATOR=?,CD_NO=?,PATCH_NO=?,p1=?, p4=?, np2=? where part_no=?";

            ps1 = conn.prepareStatement("Select PART_NO from CAT_PART(NOLOCK) where PART_NO=?");
            //loop for reading part parameters row wise.
            while (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("End"))) {
                partno = sheet.getCell(column, row).getContents().replace("\\s", "").trim();
                partno = partno.toUpperCase();
                column++;
                partdesc = sheet.getCell(column, row).getContents().trim().toUpperCase();
                column++;
                partgrp = sheet.getCell(column, row).getContents().replace("\\s", "").trim().toUpperCase();
                column++;
                stdpack = sheet.getCell(column, row).getContents().trim();
                column++;
                moq = sheet.getCell(column, row).getContents().trim();
                column++;
                serviciability = sheet.getCell(column, row).getContents().trim().toUpperCase();
                column++;
                category = "";
                column++;
//                partStatus = sheet.getCell(column, row).getContents().trim();
//                column++;


                //checking the part already exists or not.

                ps1.setString(1, partno);
                rs = ps1.executeQuery();

                if (!rs.next()) {
                    ps = conn.prepareStatement(insertpartquery);
                    //System.out.println("part_no"+partno);
                    java.sql.Date todayDate = methodutil.getSQLTodaysDate();
                    //getting CD number
                    cdno = methodutil.getCD_No(conn);
                    //getting PATCH number
                    patchno = methodutil.getPatch_No(conn);
                    //insertion into COMP_DETAIL.

                    ps.setString(1, partno);
                    ps.setString(2, partgrp);
                    ps.setDate(3, todayDate);
                    ps.setString(4, userid);
                    ps.setInt(5, cdno);
                    ps.setInt(6, patchno);
                    ps.setString(7, partdesc);
                    if (serviciability.equalsIgnoreCase("YES")) {
                        ps.setString(8, "Y");
                    } else {
                        ps.setString(8, "N");
                    }
                    ps.setString(9, stdpack);
                    ps.setString(10, "1");
                    partCounter++;
                    ps.executeUpdate();

                    if (partCounter == 200) {
                        ps.executeBatch();
                        partCounter = 0;
                    }
                    noOfParts++;


                } else {
//                    if (!exists.contains(partno)) {
//                        exists.add(partno);
//                    }
                    ps = conn.prepareStatement(updatepartquery);
                    java.sql.Date todayDate = methodutil.getSQLTodaysDate();
                    //getting CD number
                    cdno = methodutil.getCD_No(conn);
                    //getting PATCH number
                    patchno = methodutil.getPatch_No(conn);
                    //insertion into COMP_DETAIL.

                    //ps.setString(1, partno);
                    ps.setString(1, partgrp);
                    ps.setDate(2, todayDate);
                    ps.setString(3, userid);
                    ps.setInt(4, cdno);
                    ps.setInt(5, patchno);
                    ps.setString(6, partdesc);
                    if (serviciability.equalsIgnoreCase("YES")) {
                        ps.setString(7, "Y");
                    } else {
                        ps.setString(7, "N");
                    }
                    ps.setString(8, stdpack);
//                    ps.setString(9, moq);
                    ps.setString(9, partno);
                    partCounter++;
                    ps.executeUpdate();

                    if (partCounter == 200) {
                        ps.executeBatch();
                        partCounter = 0;
                    }
                    noOfParts++;

                }
                errind++;
                column = 0;
                row++;
            }
            // ps.executeBatch();

            conn.commit();
            ps.close();
            ps1.close();

            if (noOfParts == 1) {
                message.add(0, "Part has been Added Successfully.");
                message.add(1, "Add More");

            } else if (noOfParts > 1) {
                message.add(0, "Parts have been Added Successfully.");
                message.add(1, "Add More");
            } else {
                message.add(0, "No Part has been Added.");
                message.add(1, "Try Again");
            }
            message.add(2, "" + noOfParts);

            result.add(message);
            result.add(exists);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public Vector partVsPaintedVsAlternate_Insertion(File xlsfile, Connection conn, String userid, String flag) throws Exception {
        //decalration of variables used.
        int row = 3, column = 0;
        int errind = 0;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        Vector result = new Vector();
        Vector message = new Vector();
        Vector exists = new Vector();
        Workbook workbook1 = null;
        Sheet sheet = null;
        String partno = "";
        int noOfParts = 0;
        String painted_alternate_partno = "";
        String partgrp = "";
        int partCounter = 0;
        String insertpartquery = "", updatepartquery = "", deletepartquery = "";
        String categoryType = "";
        int set=0;

        try {

            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            if (flag.equals("Painted")) {
                insertpartquery = "Insert into CAT_PAINT_CODE_MASTER (PART_NO,PAINT_CODE) VALUES(?,?)";
                deletepartquery = "delete From CAT_PAINT_CODE_MASTER  where PART_NO=? and PAINT_CODE=?";
                ps1 = conn.prepareStatement("Select * from CAT_PAINT_CODE_MASTER(NOLOCK) where PART_NO=? and PAINT_CODE=?");
            } else {
                insertpartquery = "Insert into CAT_ALTERNATE_PART_MASTER (PART_NO,ALTERNATE_PART_NO,SET_NO) VALUES(?,?,?)";
                deletepartquery = "delete From CAT_ALTERNATE_PART_MASTER  where PART_NO=? and ALTERNATE_PART_NO=?";
                ps1 = conn.prepareStatement("Select * from CAT_ALTERNATE_PART_MASTER(NOLOCK) where PART_NO=? and ALTERNATE_PART_NO=?");
            }
            while (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("End"))) {
                partno = sheet.getCell(column, row).getContents().trim();
                partno = partno.toUpperCase();
                column++;
                painted_alternate_partno = sheet.getCell(column, row).getContents().trim().toUpperCase();
                column++;
                if (!flag.equals("Painted")) {
                set = Integer.parseInt(sheet.getCell(column, row).getContents().trim().toUpperCase());
                column++;
                }
                categoryType = sheet.getCell(column, row).getContents().trim().toUpperCase();
                column++;
                ps1.setString(1, partno);
                ps1.setString(2, painted_alternate_partno);
               // ps1.setInt(3, set);
                rs = ps1.executeQuery();
                ps = conn.prepareStatement(insertpartquery);
                if (!rs.next() && categoryType.equalsIgnoreCase("ADD")) {
                    ps.setString(1, partno);
                    ps.setString(2, painted_alternate_partno);
                    if (!flag.equals("Painted")) {
                    ps.setInt(3, set);
                    }
                    partCounter++;
                    ps.executeUpdate();

                    if (partCounter == 200) {
                        ps.executeBatch();
                        partCounter = 0;
                    }
                    noOfParts++;
                } else if (categoryType.equalsIgnoreCase("DELETE")) {
                    ps = conn.prepareStatement(deletepartquery);
                    ps.setString(1, partno);
                    ps.setString(2, painted_alternate_partno);
                    partCounter++;
                    ps.executeUpdate();
                    if (partCounter == 200) {
                        ps.executeBatch();
                        partCounter = 0;
                    }
                    noOfParts++;
                }


                errind++;
                column = 0;
                row++;
            }
            conn.commit();

            if (noOfParts == 1) {
                message.add(0, "" + flag + " Parts has been Added/Deleted Successfully.");
                message.add(1, "Add More");

            } else if (noOfParts > 1) {
                message.add(0, "" + flag + " Parts have been Added/Deleted Successfully.");
                message.add(1, "Add More");
            } else {
                message.add(0, "" + flag + " Parts Already Exist.");
                message.add(1, "Try Again");
            }
            message.add(2, "" + noOfParts);

            result.add(message);
            result.add(exists);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                    ps = null;
                }
                if (ps1 != null) {
                    ps1.close();
                    ps1 = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result;
    }
}
