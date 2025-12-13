/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sapIntegration;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.sap.conn.jco.JCoDestination;
import com.sap.conn.jco.JCoDestinationManager;
import com.sap.conn.jco.JCoException;
import com.sap.conn.jco.JCoFunction;
import com.sap.conn.jco.JCoTable;
import com.sap.conn.jco.ext.DestinationDataProvider;  
/**
 *
 * @author Avinash.Pandey
 */
public class RemoteFunctionCallAPN {

    static private final String DESTINATION_NAME = "ABAP_AS_WITHOUT_POOL";//ABAP_AS_WITH_POOL
    //static private final String DESTINATION_NAME1 = "ABAP_AS_WITH_POOL";//ABAP_AS_WITHOUT_POOL

    private static String dms_properties = "com/myapp/struts/ApplicationResource.properties";
    private static InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(dms_properties);
    private static Properties dms_prop = new Properties();

    public void createDestinationDataFile(String destinationName,
            Properties connectProperties) {
        File destCfg = new File(destinationName + ".jcoDestination");
        try {
            FileOutputStream fos = new FileOutputStream(destCfg, false);
            connectProperties.store(fos, "for tests only !");
            fos.close();
        } catch (Exception e) {
            throw new RuntimeException("Unable to create the destination files", e);
        }
    }

    public void initDestinationProperty() {

        Properties connectProperties = new Properties();
        connectProperties.setProperty(DestinationDataProvider.JCO_ASHOST, dms_prop.getProperty("RFC_GWHOST").trim());
        connectProperties.setProperty(DestinationDataProvider.JCO_SYSNR, dms_prop.getProperty("RFC_SYSNR").trim());
        connectProperties.setProperty(DestinationDataProvider.JCO_CLIENT, dms_prop.getProperty("RFC_CLIENT").trim());
        connectProperties.setProperty(DestinationDataProvider.JCO_USER, dms_prop.getProperty("RFC_USER").trim());
        connectProperties.setProperty(DestinationDataProvider.JCO_PASSWD, dms_prop.getProperty("RFC_PASSWD").trim());
        connectProperties.setProperty(DestinationDataProvider.JCO_LANG, "EN");

        createDestinationDataFile(DESTINATION_NAME, connectProperties);
        connectProperties.setProperty(DestinationDataProvider.JCO_POOL_CAPACITY, "3");
        connectProperties.setProperty(DestinationDataProvider.JCO_PEAK_LIMIT, "10");
        //createDestinationDataFile(DESTINATION_NAME, connectProperties);
        //System.out.println("initDestinationProperty APN VS MAIN PART Connection successfull....");
    }

    public long consumeABAPFMSAP(String date) throws JCoException, SQLException {

        String dsnPATH = dms_prop.getProperty("db_Pathnew");
        String dbUserName = dms_prop.getProperty("db_usernmnew");
        String dbPassword = dms_prop.getProperty("db_passwordnew");
        String className = dms_prop.getProperty("sqlsDrivernew");

        String mainPart = "";
        String apnPart = "";
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date todayDate = new Date();  
        long count = 0;
        Connection conn = null;
        PreparedStatement pstmt = null;
        PreparedStatement pstmtDel = null;

        String sqlQuery = "INSERT INTO CAT_ALTERNATE_PART_MASTER (PART_NO,ALTERNATE_PART_NO,SET_NO) VALUES (?,?,?)";
        long totalNoOfRows = 0;
        int[] inserted = null;
        JCoDestination destination;

        try {
            this.initDestinationProperty();
            //System.out.println("RemoteFunctionCall_APN RFC connection successful");
            //Logger.getLogger(RemoteFunctionCallAPN.class.getName()).log(Level.SEVERE, "Exception occured while creating RFC connection");

            Class.forName(className);
            conn = DriverManager.getConnection(dsnPATH, dbUserName, dbPassword);
            //System.out.println("SQL connection RemoteFunctionCall_APN RFC");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
            System.out.println("Exception occured while creating RemoteFunctionCall_APN RFC connection");
            //Logger.getLogger(RemoteFunctionCallAPN.class.getName()).log(Level.SEVERE, "Exception occured while creating RemoteFunctionCall_APN RFC connection");
        }

        destination = JCoDestinationManager.getDestination(DESTINATION_NAME);

        //JCoDestinationManager.getDestination(DESTINATION_NAME);
        System.out.println("Call function Attributes.." + destination);

        JCoFunction function = destination.getRepository().getFunction("ZAPN_RFC_MAT_MAP");
        //System.out.println("Before function :: " + function);
        if (function == null) {
            throw new RuntimeException("RemoteFunctionCall_APN RFC-->Data not found in APN RFC.");
        }
        //function.getImportParameterList().setValue("IR_DATE", "01.01.2020");
        function.execute(destination);
        if (function == null) {
            throw new RuntimeException("ZAPN_RFC_MAT_MAP structure not found in SAP.");
        }

        JCoTable table = function.getChangingParameterList().getTable("CT_MATNR");
        //System.out.println("After executing statement by passing CT_MATNR :: " + function);
        //Logger.getLogger(RemoteFunctionCallAPN.class.getName()).log(Level.SEVERE, "After executing statement by passing CT_MATNR :: " + function);
        //System.out.println("After Table :: " + table);
        totalNoOfRows = table.getNumRows();
        System.out.println("JOB Started Date " + formatter.format(todayDate));
        
        System.out.println("Table Row Count " + totalNoOfRows);
        
        //Logger.getLogger(RemoteFunctionCallAPN.class.getName()).log(Level.SEVERE, "Table Row Count=" + totalNoOfRows);
        try {
            pstmtDel = conn.prepareStatement("DELETE FROM CAT_ALTERNATE_PART_MASTER");
            pstmtDel.executeUpdate();
            pstmtDel.close();
            if (totalNoOfRows > 0) {
                pstmt = conn.prepareStatement(sqlQuery);
                do {
                    mainPart = (table.getValue("MATNR").toString()) != null ? (table.getValue("MATNR").toString().trim()) : "NA";
                    apnPart = (table.getValue("APN").toString()) != null ? (table.getValue("APN").toString().trim()) : "NA";
                    ++count;
                    pstmt.setString(1, mainPart.trim());
                    pstmt.setString(2, apnPart.trim());
                    pstmt.setLong(3, 1);

                    pstmt.addBatch();

                } while (table.nextRow());

                inserted = pstmt.executeBatch();
                if (inserted.length > 0) {
                    conn.commit();
                }
                System.out.println(inserted.length + " rows inserted from APN RFC and count is " + count + " on Date--" + formatter.format(todayDate));
            } else {
                System.out.println("There are no APN RFC data available...on Date--" + date);
            }
        } catch (SQLException e) {

            e.printStackTrace();
            Logger.getLogger(RemoteFunctionCallAPN.class.getName()).log(Level.SEVERE, null, e);
        } finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (pstmtDel != null) {
                    pstmtDel.close();
                }
                if (conn != null) {
                    conn.close();
                }
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return inserted.length;
    }

    public static void main(String[] args) {

        RemoteFunctionCallAPN obj = new RemoteFunctionCallAPN();
        try {
            dms_prop.load(is);
            Date currentDate = new Date(System.currentTimeMillis());
            long totalNoOfRows = obj.consumeABAPFMSAP(currentDate.toString());
            System.out.println("Total " + totalNoOfRows + " rows inserted in alternate vs part");
        } catch (Exception ex) {
            Logger.getLogger(RemoteFunctionCallAPN.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
