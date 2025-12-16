package EAMG.utility;

/*
File Name: v3_amw_pageTemplate.java
PURPOSE: It is the main template file used for running the e-catalogue.
Server Name, database drivers, username and passwords of the database, images url, etc are stored in this file.
HISTORY:
DATE		BUILD	AUTHOR			MODIFICATIONS
NA			v3.4	Deepak Mangal	$$0 Created
 */
import java.io.InputStream;
import java.util.Properties;

public class EAMG_pageTemplate extends Exception {

    static String ecat_properties = "com/myapp/struts/ApplicationResource.properties";
    static InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(ecat_properties);
    static Properties ecat_prop = new Properties();
    private static EAMG_pageTemplate ourInstance = new EAMG_pageTemplate ();

    static {
        try {
            ecat_prop.load(is);
        } catch (Exception e) {
            //System.out.println("Resource loader cannot be loaded");
            e.printStackTrace();
        }
    }

    public static String dsnPATH() {
        String dsnPATH = "";

        String db = ecat_prop.getProperty("db");
        String db_ip = ecat_prop.getProperty("db_ip");
        String db_port = ecat_prop.getProperty("db_port");
        String db_name = ecat_prop.getProperty("db_name");
        if (db.equals("MYSQL")) {
            dsnPATH = "jdbc:mysql://" + db_ip + ":" + db_port + "/" + db_name + "";
        } else if (db.equals("ORACLE")) {
            dsnPATH = "jdbc:oracle:thin:@" + db_ip + ":" + db_port + ":" + db_name + "";
        } else if (db.equals("MSSQL2000")) {
            dsnPATH = "jdbc:microsoft:sqlserver://" + db_ip + ":" + db_port + ";SelectMethod=cursor";
        } else if (db.equals("MSSQL2005")) {
            dsnPATH = "jdbc:sqlserver://" + db_ip + ":" + db_port + ";SelectMethod=cursor";
        } else {
            dsnPATH = "jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb); "
                    + "DBQ=" + ecatPath() + "dealer/database/v3ecatalogue.mdb; "
                    + "UserCommitSync=Yes; "
                    + "Threads=3; "
                    + "SafeTransactions=0; "
                    + "PageTimeout=5; "
                    + "MaxScanRows=8; "
                    + "MaxBufferSize=2048; "
                    + "DriverId=281; "
                    + "DefaultDir=C:/ProgramFiles/CommonFiles/ODBC/DataSources";
        }
        return dsnPATH;
    }
     final public static String server_name = ecat_prop.getProperty ( "server_name" );
     final public static String servletURL = ecat_prop.getProperty ( "amw.servletURL" );
     public static final String appPath = ecat_prop.getProperty ( "amw.port" );
     public static final String mainURL = ecat_prop.getProperty ( "mainURL" );
     public static final String effectiveDate = ecat_prop.getProperty ( "variant.effectiveDate" );
    //public static final String attachmentFolderPath = appPath + "//" + mnal_prop.getProperty ( "attach.folder" );
   

    public String getApplicationProperty(String propertyName) {
        return ecat_prop.getProperty(propertyName);
    }

    public static String getProperty(String propertyName) {
        return ourInstance.getApplicationProperty(propertyName);
    }

    public static void setProperty(String propertyName, String propertyValue) {
    }
    public static String getTrustedSite() {
        return ecat_prop.getProperty("trusted.site.address");
    }
  
    public static String dbDriver() {
        String driver = ecat_prop.getProperty("JDBCClassName");
        return driver;
    }

    public static String dbUserName() {
        String dbUserName = ecat_prop.getProperty("dbUserName");

        return dbUserName;
    }

    public static String dbPwd() {
        String dbPwd = ecat_prop.getProperty("dbPwd");

        return dbPwd;
    }

    public static String ecatPath() {
        String ecatPath = ecat_prop.getProperty("ecatPath");
        return ecatPath;
    }

    public static String serverName() {
        String dbUserName = ecat_prop.getProperty("server_name");

        return dbUserName;
    }

    public static String mainURL() {
        String mainURL = ecat_prop.getProperty("mainURL");
        return mainURL;
    }
    
    public static String imagesURL() {
        String imagesURL = mainURL + "dealer/images/";
        return imagesURL;
    }
    public static String servletURL() {

        String servletURL = ecat_prop.getProperty("amw.servletURL");
        return servletURL;
    }
}
