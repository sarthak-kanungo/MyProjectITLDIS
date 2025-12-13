/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dbConnection;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class dbConnection extends Exception
{

    private Connection conn;
    private static String itldis_properties = "com/myapp/struts/ApplicationResource.properties";
    private static InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(itldis_properties);
    private static Properties itldis_prop = new Properties();

    static
    {
        try
        {
            itldis_prop.load(is);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    final private String sqlsDriver = itldis_prop.getProperty("sqlsDriver");
    final private String db_Path = itldis_prop.getProperty("db_Path");
    final private String db_usernm = itldis_prop.getProperty("db_usernm");
    final private String db_password = itldis_prop.getProperty("db_password");
    final static public String db = itldis_prop.getProperty("db");
    final static public String dbPathAuth = itldis_prop.getProperty("dbPathAuth");
    final private String sqlsDrivernew = itldis_prop.getProperty("sqlsDrivernew");
    final private String db_Pathnew = itldis_prop.getProperty("db_Pathnew");
    final private String db_usernmnew = itldis_prop.getProperty("db_usernmnew");
    final private String db_passwordnew = itldis_prop.getProperty("db_passwordnew");
    final static public String dbnew = itldis_prop.getProperty("dbnew");
    final static public String SAPWebService = itldis_prop.getProperty("SAPWebService");
    final static public String warrantyClaimPath = itldis_prop.getProperty("Warranty_Claim_Path");
    final static public String ftp_server_ip = itldis_prop.getProperty("ftp_server");
    final static public String ftp_server_port = itldis_prop.getProperty("ftp_port");
    final static public String ftp_server_username = itldis_prop.getProperty("ftp_username");
    final static public String ftp_server_password = itldis_prop.getProperty("ftp_password");
    final static public String ftp_file_path = itldis_prop.getProperty("ftp_file_path");
    final static public String credit_new_path = itldis_prop.getProperty("credit_new_path");
    final static public String credit_success_path = itldis_prop.getProperty("credit_success_path");
    final static public String credit_failure_path = itldis_prop.getProperty("credit_failure_path");
    final static public String ftp_credit_new_username = itldis_prop.getProperty("ftp_credit_new_username");
    final static public String ftp_credit_new_password = itldis_prop.getProperty("ftp_credit_new_password");
    final static public String ftp_credit_success_username = itldis_prop.getProperty("ftp_credit_success_username");
    final static public String ftp_credit_success_password = itldis_prop.getProperty("ftp_credit_success_password");
    final static public String ftp_credit_failure_username = itldis_prop.getProperty("ftp_credit_failure_username");
    final static public String ftp_credit_failure_password = itldis_prop.getProperty("ftp_credit_failure_password");

    final static public String sap_SO_Path = itldis_prop.getProperty("sap_SO_Path");
    /****
    Method for accessing Sql server
     */
    public String dsnPATH()
    {
        String dsnPATH = "";

        String db = itldis_prop.getProperty("db");
        String db_ip = itldis_prop.getProperty("db_ip");
        String db_port = itldis_prop.getProperty("db_port");
        String db_name = itldis_prop.getProperty("db_name");
        if (db.equals("MYSQL"))
        {
            dsnPATH = "jdbc:mysql://" + db_ip + ":" + db_port + "/" + db_name + "";
        }
        else if (db.equals("ORACLE"))
        {
            dsnPATH = "jdbc:oracle:thin:@" + db_ip + ":" + db_port + ":" + db_name + "";
        }
        else if (db.equals("MSSQL2000"))
        {
            dsnPATH = "jdbc:microsoft:sqlserver://" + db_ip + ":" + db_port + ";SelectMethod=cursor";
        }
        else if (db.equals("MSSQL2005"))
        {
            dsnPATH = "jdbc:sqlserver://" + db_ip + ":" + db_port + ";SelectMethod=cursor";
        }
        return dsnPATH;
    }

    public Connection getConnection_DM()
    {

        try
        {
            conn = null;

            String dbUserName = itldis_prop.getProperty("dbUserName");
            String dbPasswd = itldis_prop.getProperty("dbPwd");
//            String dsnPATH = dsnPATH();
            conn = DriverManager.getConnection(db_Path, dbUserName, dbPasswd);
            conn.setAutoCommit(false);

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return conn;
    }

    public Connection getConnection() throws Exception
    {

        try
        {
            if (conn == null)
            {
                Class.forName(sqlsDriver);
                conn = DriverManager.getConnection(db_Path, db_usernm, db_password);
                conn.setAutoCommit(false);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return conn;

    }

    public Connection getConnectionNew() throws Exception
    {

        try
        {
            if (conn == null)
            {
                Class.forName(sqlsDrivernew);
                conn = DriverManager.getConnection(db_Pathnew, db_usernmnew, db_passwordnew);
                conn.setAutoCommit(false);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return conn;

    }

    public Connection getDbConnection()
    {
        conn = getConnection_DM();
        return conn;
    }
}


