/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package EAMG.Other.Action;


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
 * @author satyaprakash.verma
 */
public class EAMG_InsertTSNParameters {

    public EAMG_InsertTSNParameters() {
    }

   
   
    public Vector tsnInsertion(File xlsfile, Connection conn, String userid) throws Exception {

        //decalration of variables used.
        int row = 3, column = 0;
        int errind = 0;
        ResultSet rs = null;
        PreparedStatement ps = null;
        PreparedStatement ps1 = null;
        PreparedStatement ps2 = null;
        Vector result = new Vector();
        Vector message = new Vector();
        Workbook workbook1 = null;
        Sheet sheet = null;
        String tsnNo = "";
        int noOfTSN = 0;
        String fCode = "";
        String mfgDate = "";
        String dateMfg = "";
        java.sql.Date  dateMfgSql= null;
        int cdno = 0;
        int partCounter = 0;
        int partCounter1 = 0;
        String insertpartquery = "";
        EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();

        try {
            workbook1 = Workbook.getWorkbook(xlsfile);
            sheet = workbook1.getSheet(0);
            insertpartquery = "Insert into cat_VEHICLE_SONO (VEHICLE_NO, SO_NO,Mfg_Date,BUCKLE_UP_DATE) VALUES(?,?,?,?)";
            ps = conn.prepareStatement(insertpartquery);
            ps1 = conn.prepareStatement("Select VEHICLE_NO from cat_VEHICLE_SONO(NOLOCK) where VEHICLE_NO=?");
            ps2 = conn.prepareStatement("UPDATE cat_VEHICLE_SONO SET SO_NO=?, Mfg_Date=?,BUCKLE_UP_DATE=? WHERE VEHICLE_NO=?");
            //loop for reading TSN parameters row wise.
            while (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("End"))) {
               
                //System.out.println("row"+row);
                tsnNo = sheet.getCell(column, row).getContents().trim();
                tsnNo = tsnNo.toUpperCase();
                column++;
                fCode = sheet.getCell(column, row).getContents().trim();
                column++;
                mfgDate = sheet.getCell(column, row).getContents().trim();
                column++;
                column++;
                //checking the TSN already exists or not.
                ps1.setString(1, tsnNo);
                rs = ps1.executeQuery();
                dateMfg = methodutil.getConvertDate(mfgDate);
                dateMfgSql=methodutil.getConvertSQLDate(mfgDate);
                
                if (!rs.next()) {
                    ps.setString(1, tsnNo);
                    ps.setString(2, fCode);
                    ps.setString(3, dateMfg);
                    ps.setDate(4, dateMfgSql);
                    partCounter++;
                    ps.addBatch();
                    if (partCounter == 200) {
                        ps.executeBatch();
                        partCounter = 0;
                    }
                } else {
                    ps2.setString(1, fCode);
                    ps2.setString(2, dateMfg);
                    ps2.setDate(3, dateMfgSql);
                    ps2.setString(4, tsnNo);
                    partCounter1++;
                    ps2.addBatch();
                    if (partCounter1 == 200) {
                        ps2.executeBatch();
                        partCounter1 = 0;
                    }
                }
                noOfTSN++;
                errind++;
                column = 0;
                row++;
                //System.out.println("row"+row);
            }
            ps.executeBatch();
            ps2.executeBatch();

            conn.commit();
            //System.out.println("row"+row);
            ps.close();
            ps1.close();
            ps2.close();

            if (noOfTSN == 1) {
                message.add(0, "TSN has been Uploaded Successfully.");
                message.add(1, "Add More");

            } else if (noOfTSN > 1) {
                message.add(0, "TSN have been Uploaded Successfully.");
                message.add(1, "Add More");
            } else {
                message.add(0, "No TSN has been Uploaded.");
                message.add(1, "Try Again");
            }
            message.add(2, "" + noOfTSN);

            result.add(message);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}

