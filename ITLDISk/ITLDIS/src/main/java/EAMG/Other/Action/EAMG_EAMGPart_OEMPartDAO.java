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

import org.apache.log4j.Logger;

import com.EAMG.common.WebConstants;

import jxl.Sheet;
import jxl.Workbook;

/**
 *
 * @author avinash.pandey
 */
public class EAMG_EAMGPart_OEMPartDAO {
 private Logger logger = Logger.getLogger ( this.getClass () );
    /** Creates a new instance of AT4_InsertPartParameters */
    public EAMG_EAMGPart_OEMPartDAO() {
    }

    public Vector AMWPartOEMpartInsertion(File xlsfile, Connection conn, String userid) throws Exception {

        //decalration of variables used.
        int row = 3, column = 0;
        int errind = 0;
        PreparedStatement ps = null;
        Vector result = new Vector();
        Workbook workbook1 = null;
        Sheet sheet = null;
        String AMW_PART_NO = "";
        String OEM_PART_NO = "";
        String OEM_PART_DESC = "";
        String insertpartquery = "";
        try {
            workbook1 = Workbook.getWorkbook(xlsfile);
            //getting the sheet.
            sheet = workbook1.getSheet(0);
            //loop for reading part parameters row wise.
            while (!(sheet.getCell(column, row).getContents().trim().equalsIgnoreCase("End"))) {
                AMW_PART_NO = sheet.getCell(column, row).getContents().trim();
                AMW_PART_NO = AMW_PART_NO.toUpperCase();
                column++;
                OEM_PART_NO = sheet.getCell(column, row).getContents().trim();
                OEM_PART_NO = OEM_PART_NO.toUpperCase();
                column++;
                OEM_PART_DESC = sheet.getCell(column, row).getContents().trim();
                column++;
                //checking the part already exists or not.
                String amwPartinDB = new EAMG_EAMGPart_OEMPartDAO().checkAMWPartExistsinDB(AMW_PART_NO, conn);
                //String oemPartinDB = new EAMG_EAMGPart_OEMPartDAO().checkOEMPartExistsinDB(OEM_PART_NO, conn);
                ////System.out.println("Comp Type in DB:"+comptypeinDB);
                //if not exists.
                if (amwPartinDB.equals("false")) {
                    //if (oemPartinDB.equals("false")) 
                    //{
                    insertpartquery = "Insert into AMW_VS_OEM_MATRIX (AMW_PART_NO, OEM_PART_NO, OEM_PART_DESC) VALUES(?,?,?)";
                    ps = conn.prepareStatement(insertpartquery);
                    ps.setString(1, AMW_PART_NO);
                    ps.setString(2, OEM_PART_NO);
                    if (!OEM_PART_DESC.equals("") || !OEM_PART_DESC.isEmpty()) {
                        ps.setString(3, OEM_PART_DESC);
                    } else {
                        ps.setString(3, "--");
                    }
                    ps.executeUpdate();
                    ps.close();

                    //}
                } else {
                    if (!result.contains(AMW_PART_NO)) {
                        result.add(AMW_PART_NO);
                        //result.add(amwPartinDB);
                    }
                    if (!result.contains(OEM_PART_NO)) {
                        result.add(OEM_PART_NO);
                        //result.add(oemPartinDB);
                    }
                }
                errind++;
                column = 0;
                row++;

                conn.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public String checkAMWPartExistsinDB(String AMW_PART_NO, Connection conn) throws Exception {
        //Statement st = conn.createStatement();
    	PreparedStatement st = null;
        ResultSet rs = null;
        String result = "false";
        //if component exists as Assembly, Part, Tool or Kit.
        //rs = st.executeQuery("Select AMW_PART_NO from AMW_VS_OEM_MATRIX where AMW_PART_NO='" + AMW_PART_NO + "'");
        String query = ("Select AMW_PART_NO from AMW_VS_OEM_MATRIX(NOLOCK) where AMW_PART_NO='" + AMW_PART_NO + "'");
        st = conn.prepareStatement(query);
        rs = st.executeQuery();
        if (rs.next()) {
            result = rs.getString(1);
        }
        rs.close();
        st.close();
        return result;
    }

    public Vector getAllOMEPARTByPart(String AMW_PART_NO, Connection conn) {

        String query = "";
        String OEM_PART_NO = "";
        ResultSet rs = null;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        Vector tempVec = new Vector();
        try {
            //stmt = conn.createStatement();
            query = "Select OEM_PART_NO from AMW_VS_OEM_MATRIX(NOLOCK) where AMW_PART_NO='" + AMW_PART_NO + "'ORDER BY OEM_PART_NO ";
           stmt = conn.prepareStatement(query);
           rs = stmt.executeQuery();
           // rs = stmt.executeQuery(query);
            while (rs.next()) {
                OEM_PART_NO = rs.getString(1);
                tempVec.add(OEM_PART_NO);
            }
            rs.close();
             stmt.close();
        } catch (Exception ae) {
            ae.printStackTrace();

        }
        return tempVec;

    }
    public Vector getAllOMEPART(Connection conn) {
        String query = "";
        String OEM_PART_NO = "";
        ResultSet rs = null;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        Vector tempVec = new Vector();
        try {
            //stmt = conn.createStatement();
            query = "SELECT distinct(OEM_PART_NO) FROM AMW_VS_OEM_MATRIX(NOLOCK) order by OEM_PART_NO";
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                OEM_PART_NO = rs.getString(1);
                if(OEM_PART_NO!=null || !OEM_PART_NO.equals("")){
                tempVec.add(OEM_PART_NO);
                }
            }
            rs.close();
            stmt.close();
        } catch (Exception ae) {
            ae.printStackTrace();
        }
        return tempVec;
    }
    /**
      *
      * @param searchAMWPart
      * @param tempVector
      * @param conn
      * @return
      * <b>Author</b> - Avinash
      */
     public int assignAMWOMEMatrix ( String searchAMWPart , Vector tempVector ,  Connection conn ) {
          int isCreated = 0;
          PreparedStatement pstmt = null;
          try {
               pstmt = conn.prepareStatement ( "delete from AMW_VS_OEM_MATRIX(NOLOCK) where AMW_PART_NO=?" );
               pstmt.setString ( 1 , searchAMWPart );
               pstmt.executeUpdate ();
               pstmt.close ();
               pstmt = null;

               pstmt = conn.prepareStatement ( "INSERT into AMW_VS_OEM_MATRIX(AMW_PART_NO,OEM_PART_NO,OEM_PART_DESC) values(?,?,?)" );
               for ( int i = 0 ; i < tempVector.size () ; i++ ) {
                    pstmt.setString ( 1 , searchAMWPart );
                    pstmt.setString ( 2 , tempVector.elementAt ( i ).toString () );
                    pstmt.setString(3, "--");
                    pstmt.executeUpdate ();
               }
               pstmt.close ();
               pstmt = null;
               isCreated = 1;

          }
          catch ( Exception e ) {
               isCreated = 2;
               logger.error ( WebConstants.logException , e );
          }
          finally {
               try {
                    if ( pstmt != null ) {
                         pstmt.close ();
                         pstmt = null;
                    }
               }
               catch ( Exception e ) {
                    logger.error ( WebConstants.fLogException , e );
               }
          }
          return isCreated;

     }
}
