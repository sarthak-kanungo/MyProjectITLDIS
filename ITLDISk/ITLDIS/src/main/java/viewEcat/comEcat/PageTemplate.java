package viewEcat.comEcat;

import java.io.InputStream;
import java.io.PrintWriter;
/*
 File Name: PageTemplate.java
 PURPOSE: It is the main template file used for running the e-catalogue.
 Server Name, database drivers, username and passwords of the database, images url, etc are stored in this file.
 HISTORY:
 DATE		BUILD	AUTHOR			MODIFICATIONS
 NA			v3.4	Deepak Mangal	$$0 Created
 */
import java.util.Properties;

import authEcat.UtilityMapkeys1;

public class PageTemplate extends Exception {

    static String ecat_properties = "com/myapp/struts/ApplicationResource.properties";
    static InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(ecat_properties);
    static Properties ecat_prop = new Properties();

    static {
        try {
            ecat_prop.load(is);
        } catch (Exception e) {
          //  System.out.println("Resource loader cannot be loaded");
            e.printStackTrace();
        }
    }
    public static String ORAClassName = ecat_prop.getProperty("ORAClassName");
    public static String ORADSN = ecat_prop.getProperty("ORADSN");
    public static String ORAUser = ecat_prop.getProperty("ORAUser");
    public static String ORAPwd = ecat_prop.getProperty("ORAPwd");
    public static String web_or_local = ecat_prop.getProperty("web_or_local");
    public static String server_name = ecat_prop.getProperty("servername");
    public static String mainURL = ecat_prop.getProperty("mainURL");
    public static String ecatPATH = ecat_prop.getProperty("ecatPATH");
    public static String userCode = ecat_prop.getProperty("userCode");
    String servletURL = ecat_prop.getProperty("servletURL");
    public static String ENGINE_SERIES = "Product";//"Product Family";
    public static String ENGINE_MODEL = "Model";//Model";
    public static String MODEL_NO = "Variant";//F-Code";
    public static String AGGREGATE = "Aggregate";//Group";
    public static String ECN = "Change History";//Variant";
    public static String GROUP = "Group";//Table";
    public static String MODELTYPE = "Type";//Variant";
    public static int groupImageWidth = 681;
    public static int groupImageHeight = 711;
    public static int modelImageWidth = 150;
    public static int modelImageHeight = 150;
    public static int engineSeriesWidth = 220;
    public static int engineSeriesHeight = 193;
    public static int tableImageWidth = 465;
    public static int tableImageHeight = 620;
    public static String engineSeriesImageExt = ".jpg";
    public static String engineModelImageExt = ".jpg";
    public static int engineModelImageWidth = 220;
    public static int engineModelImageHeight = 193;
    public static int groupImageThumbWidth = 105;
    public static int groupImageThumbHeight = 105;
    public static String toolIconimage = "TOOLS-ICON.jpg";
    public static String printIconimage = "PRINT-ICON.jpg";
    public static String kitIconimage = "KITS-ICON.jpg";
    public static String lubeIconimage = "LUBES-ICON.jpg";
    public static String sessionExpiredMessage = "SESSION EXPIRED";
    public static String tbaPart = "-";
    public static String modelImages = "Image Size: 150 x 150 (Jpg)";
    public static String toolImages = "";
    public static final String effectiveDate = ecat_prop.getProperty("variant.effectiveDate");
    public static final String packingGenDateCheck = ecat_prop.getProperty("common.packingGenDateCheck");
    public static int varientImageWidth = 150;
    public static int varientImageHeight = 150;
    public static final String maxSizeStr = "60";
    public static final long maxSize = Integer.parseInt(maxSizeStr) * (1024 * 1024);
    public static String svgPATH = ecat_prop.getProperty("mainSvgPath");
    public static String bulletin = "Circular";
    public static String service = "Service";
    public static String startYearOfIssue = "01/01/2010";
    public static String warrBookNo = ecat_prop.getProperty("warrBookNoDefault");
    public static String typeName = ecat_prop.getProperty("typeNameDefault");
    public static String amtToBajaj = ecat_prop.getProperty("amtToBajajDefault");
    public static String bajajPolicyNo = ecat_prop.getProperty("bajajPolicyNoDefault");
    public static String sumInsured = ecat_prop.getProperty("sumInsuredDefault");
    public static String imdCode = ecat_prop.getProperty("imdCodeDefault");
    public static String ppId = ecat_prop.getProperty("ppIdDefault");
    public static String floatType = ecat_prop.getProperty("floatTypeDefault");
    public static String deliveryDaysDiff = ecat_prop.getProperty("deliveryDaysDiff");
    public static final String warrantyClaimDateDefault = ecat_prop.getProperty("common.warrantyClaimDateDefault");
    public static String imgPath = ecat_prop.getProperty("imgPath");

    public PageTemplate() {
    }

    public PageTemplate(String temp_1, String temp_2, String temp_3) {
        /* server_name = temp_1;
         ecatPATH = temp_2;
         mainURL = temp_3;
         */
    }
    // JDBC DRIVER
//    public String tableHeader(String heading, String width)
//    {
//        String imagesURL = imagesURL();
//
//        // String tableHeader = " " + "      <div align=\"center\">" + "        <table width=\"" + width + "\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" id=\"Table_01\">" + "        <tr>" + "            <td width=\"10\" align=\"left\"><img src=\"" + imagesURL + "/tableLayout/ReportPage_01.jpg\" width=\"10\" height=\"26\"></td>" + "            <td width=\"53\" align=\"left\"><img src=\"" + imagesURL + "/tableLayout/ReportPage_02.jpg\" width=\"53\" height=\"26\"></td>" + "            <td width=\"690\" background=\"" + imagesURL + "/tableLayout/ReportPage_03.jpg\">" + "                <table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">" + "                    <tr>" + "                        <td align=\"right\" class=\"heading-main\">" + heading + "</td>" + "                    </tr>" + "                </table>" + "            </td>" + "            <td width=\"6\" align=\"right\"><img src=\"" + imagesURL + "/tableLayout/ReportPage_04.jpg\" width=\"6\" height=\"26\"></td>" + "        </tr>" + "        <tr>" + "            <td width=\"10\" align=\"left\" background=\"" + imagesURL + "/tableLayout/ReportPage_05.jpg\" style=\"background-repeat:repeat-y\"></td>" + "            <td colspan=\"2\" align=\"center\" valign=\"top\">" + "                <table width=\"100%\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" bordercolor=\"#333333\">" + "                    <tr>" + "                        <td  align=\"center\" valign=\"top\" bgcolor=\"#FFFFFF\">" + "                            <table width=\"100%\" border=\"0\" cellspacing=\"2\" cellpadding=\"2\">" + "                                <tr>" + "                                    <td  valign=\"top\"  style=\"padding-top:10px\">" + "                                       <div  align=\"center\">";
//        String tableHeader = "<table width=\"" + width + "\" height=\"99\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" id=\"Table_01\">" + "	<tr>" + "		<td width=\"10\" height=\"32\">" + "			<img src=\"" + imagesURL + "tableLayout/profile_01.jpg\" width=\"10\" height=\"32\" alt=\"\"></td>" + "	  <td width=\"100%\" align=\"right\" class=\"heading-main\" style=\"background-image:url(" + imagesURL + "tableLayout/profile_10.jpg); background-repeat:repeat-x;\">" + heading + "</td>" + "		<td width=\"35\">" + "			<img src=\"" + imagesURL + "tableLayout/profile_03.jpg\" width=\"10\" height=\"32\" alt=\"\"></td>" + "	</tr>" + "	<tr>" + "		<td style=\"background-image:url(" + imagesURL + "tableLayout/profile_04.jpg); background-repeat:repeat-y;\">&nbsp;</td>" + "		<td valign=\"top\" style=\"padding-top:10px\">" + "		<div align=center>";
//        return tableHeader;
//    }

    public void pageLink(PrintWriter ps, int width, int height1, String tdData) {
        ps.println("<table width=100% border=0 bgcolor=\"#ffffff\" align=\"center\" cellspacing=0 cellpadding=4>");
        ps.println("                        <tr>");
        ps.println("                            <td align=\"left\" class=\"links_txt\">");
        ps.println("&nbsp;" + tdData + "");
        ps.println("                        </tr>");
        ps.println("                    </table>");

    }

    public String mainHeading(String heading) {
        String headingMain = ""
                + "<tr>"
                + "                                                                    <td colspan=2 width=\"100%\"><table width=\"100%\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" bordercolor=\"#333333\">"
                + "                                                                            <tr>"
                + "                                                                                <td height=\"30\" align=\"left\" class=\"blue\"><b class=\"heading\"  style=\"padding-left:10px\">" + heading + "</b></td>"
                + "                                                                            </tr>"
                + "                                                                        </table></td>";

        return headingMain;
    }

    public String tableHeaderMessage(String heading, int width1, int height1) {

        String imagesURL = imagesURL();
        String result = null;

        int width2 = width1 - 72;

        result = "<table align=\"center\" width=\"" + width1 + "\"  height=\"" + height1 + "\" class=\"headertable-main\" border=0 cellspacing=0 cellpadding=0>            <tr>                <td valign = top align=center style=\"padding-top:10px;padding-left:5px\">                                    <table width=100% border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" id=\"Table_01\">                    "
                + "          <tr>                        <td width=\"10\" align=\"right\"><img src=\"" + imagesURL + "tableLayout/ReportPage_01.jpg\" width=\"10\" height=\"26\"></td>                                               "
                + "        <td width=\"100%\" align=\"left\" colspan=\"2\" background=\"" + imagesURL + "tableLayout/ReportPage_03.jpg\"><table width=\"95%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"
                + "          <tr>"
                + "            <td align=\"right\" class=\"heading-main\"  style=\"padding-left:10px\">" + heading + "</td>"
                + "          </tr>"
                + "        </table></td>                        <td width=\"10\" align=\"left\"><img src=\"" + imagesURL + "tableLayout/ReportPage_04.jpg\" width=\"6\" height=\"26\"></td>                    "
                + "        </tr>  <tr>      <td width=\"10\" align=\"left\" background=\"" + imagesURL + "tableLayout/ReportPage_05.jpg\" style=\"background-repeat:repeat-y\"></td>                        <td colspan=\"2\" align=\"center\" valign=\"top\" bgcolor=\"#FFFFFF\">"
                + " ";

        return result;

    }

    public String tableHeader2(String heading, int width1, int height1, String align, String tdRight) {
        String imagesURL = imagesURL();
        String result = null;
        //String tdData="";

        result = "<table width=\"" + width1 + "\"  height=\"" + height1 + "\"  border=0 cellspacing=0 cellpadding=0>            <tr>                <td valign = top align=center style=\"padding-top:10px;padding-left:16px\">                                    <table width=100% border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" id=\"Table_01\">                    "
                + "          <tr>                        <td width=\"10\" align=\"right\"><img src=\"" + imagesURL + "tableLayout/ReportPage_01.jpg\" width=\"10\" height=\"26\"></td>                                               "
                + "        <td width=\"100%\" align=\"left\" colspan=\"2\" background=\"" + imagesURL + "tableLayout/ReportPage_03.jpg\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"
                + "          <tr>"
                + "            <td width=80% align=\"" + align + "\"  class=\"heading-main\"  style=\"padding-left:10px\">" + heading + "</td>" + tdRight.replaceAll("tdstyleBold", "links_txtBold")
                + "          </tr>"
                + "        </table></td>                        <td width=\"10\" align=\"left\"><img src=\"" + imagesURL + "tableLayout/ReportPage_04.jpg\" width=\"6\" height=\"26\"></td>                    "
                + "        </tr>  <tr>      <td width=\"10\" align=\"left\" background=\"" + imagesURL + "tableLayout/ReportPage_05.jpg\" style=\"background-repeat:repeat-y\"></td>                        <td colspan=\"2\" align=\"center\" valign=\"top\" bgcolor=\"#FFFFFF\">"
                + " ";

        return result;
    }
//	  public String tableHeader2(String heading, int width1, int height1, String align, String tdRight) {
//        String imagesURL = imagesURL();
//        String result = null;
//        //String tdData="";
//
//        result = "<table width=\"" + width1 + "\"  height=\"" + height1 + "\"  border=0 cellspacing=0 cellpadding=0>            <tr>                <td valign = top align=center style=\"padding-top:10px;padding-left:16px\">                                    <table width=100% border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" id=\"Table_01\">                    "
//                + "          <tr>                        <td width=\"10\" align=\"right\"><img src=\"" + imagesURL + "tableLayout/ReportPage_01.jpg\" width=\"10\" height=\"26\"></td>                        <td width=\"6\" align=\"left\" background=\"" + imagesURL + "tableLayout/ReportPage_02.jpg\">&nbsp;</td>                        "
//                + "        <td width=\"100%\" align=\"left\" background=\"" + imagesURL + "tableLayout/ReportPage_03.jpg\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"
//                + "          <tr>"
//                + "            <td width=80% align=\"" + align + "\"  class=\"heading-main\">" + heading + "</td>" + tdRight.replaceAll("tdstyleBold","links_txtBold")
//                + "          </tr>"
//                + "        </table></td>                        <td width=\"10\" align=\"left\"><img src=\"" + imagesURL + "tableLayout/ReportPage_04.jpg\" width=\"6\" height=\"26\"></td>                    "
//                + "        </tr>  <tr>      <td width=\"10\" align=\"left\" background=\"" + imagesURL + "tableLayout/ReportPage_05.jpg\" style=\"background-repeat:repeat-y\"></td>                        <td colspan=\"2\" align=\"center\" valign=\"top\" bgcolor=\"#FFFFFF\">"
//                + " ";
//
//        return result;
//    }

    public String tableHeader(String heading, int width1, int height1) {
        String imagesURL = imagesURL();
        String result = null;

        result = "<table width=\"" + width1 + "\"  height=\"" + height1 + "\"  border=0 cellspacing=0 cellpadding=0>            <tr>                <td valign = top align=center style=\"padding-top:10px;padding-left:12px\">                                    <table width=100% border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" id=\"Table_01\">                    "
                + "          <tr>                        <td width=\"10\" align=\"right\"><img src=\"" + imagesURL + "tableLayout/ReportPage_01.jpg\" width=\"10\" height=\"26\"></td>                                            "
                + "        <td width=\"100%\" colspan=\"2\"  align=\"left\" background=\"" + imagesURL + "tableLayout/ReportPage_03.jpg\"><table width=\"95%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"
                + "          <tr>"
                + "            <td align=\"left\" class=\"heading-main\" style=\"padding-left:10px\">" + heading + "</td>"
                + "          </tr>"
                + "        </table></td>                        <td width=\"10\" align=\"left\"><img src=\"" + imagesURL + "tableLayout/ReportPage_04.jpg\" width=\"6\" height=\"26\"></td>                    "
                + "        </tr>  <tr>      <td width=\"10\" align=\"left\" background=\"" + imagesURL + "tableLayout/ReportPage_05.jpg\" style=\"background-repeat:repeat-y\"></td>                        <td colspan=\"2\" align=\"center\" valign=\"top\" bgcolor=\"#FFFFFF\">"
                + " ";

        return result;
    }

    public String tableHeader1(String heading, int width1, int height1) {
        String imagesURL = imagesURL();
        String result = null;

        result = "<table width=\"" + width1 + "\"  height=\"" + height1 + "\"  border=0 cellspacing=0 cellpadding=0>            <tr>                <td valign = top align=center style=\"padding-top:10px;padding-left:12px\">                                    <table width=100% border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" id=\"Table_01\">                    "
                + "          <tr>                        <td width=\"10\" align=\"right\"><img src=\"" + imagesURL + "tableLayout/ReportPage_01.jpg\" width=\"10\" height=\"26\"></td>                                          "
                + "        <td width=\"100%\" colspan=\"2\" align=\"left\" background=\"" + imagesURL + "tableLayout/ReportPage_03.jpg\"><table width=\"95%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"
                + "          <tr>"
                + "            <td align=\"center\" class=\"heading-main\" style=\"padding-left:10px\">" + heading + "</td>"
                + "          </tr>"
                + "        </table></td>                        <td width=\"10\" align=\"left\"><img src=\"" + imagesURL + "tableLayout/ReportPage_04.jpg\" width=\"6\" height=\"26\"></td>                    "
                + "        </tr>  <tr>      <td width=\"10\" align=\"left\" background=\"" + imagesURL + "tableLayout/ReportPage_05.jpg\" style=\"background-repeat:repeat-y\"></td>                        <td colspan=\"2\" align=\"center\" valign=\"top\" bgcolor=\"#FFFFFF\">"
                + " ";

        return result;
    }

    public String tableHeader3(String heading, int width1, int height1) {
        String imagesURL = imagesURL();
        String result = null;

        result = "<table align=\"center\" width=\"" + width1 + "\"  height=\"" + height1 + "\"  border=0 cellspacing=0 cellpadding=0>            <tr>                <td valign = top align=center style=\"padding-top:10px;padding-left:12px\">                                    <table width=100% border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" id=\"Table_01\">                    "
                + "          <tr>                        <td width=\"10\" align=\"right\"><img src=\"" + imagesURL + "tableLayout/ReportPage_01.jpg\" width=\"10\" height=\"26\"></td>                                             "
                + "        <td width=\"100%\" colspan=\"2\" align=\"left\" background=\"" + imagesURL + "tableLayout/ReportPage_03.jpg\"><table width=\"95%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"
                + "          <tr>"
                + "            <td align=\"left\" class=\"heading-main\" style=\"padding-left:10px\">" + heading + "</td>"
                + "          </tr>"
                + "        </table></td>                        <td width=\"10\" align=\"left\"><img src=\"" + imagesURL + "tableLayout/ReportPage_04.jpg\" width=\"6\" height=\"26\"></td>                    "
                + "        </tr>  <tr>      <td width=\"10\" align=\"left\" background=\"" + imagesURL + "tableLayout/ReportPage_05.jpg\" style=\"background-repeat:repeat-y\"></td>                        <td colspan=\"2\" align=\"center\" valign=\"top\" bgcolor=\"#FFFFFF\">"
                + " ";

        return result;
    }

    public String tableHeader4(String heading, int width1, int height1) {
        String imagesURL = imagesURL();
        String result = null;

        result = "<table align=\"center\" width=\"" + width1 + "\"  height=\"" + height1 + "\"  border=0 cellspacing=0 cellpadding=0>            <tr>                <td valign = top align=center style=\"padding-top:10px;padding-left:12px\">                                    <table width=100% border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" id=\"Table_01\">                    "
                + "          <tr>                        <td width=\"10\" align=\"right\"><img src=\"" + imagesURL + "tableLayout/ReportPage_01.jpg\" width=\"10\" height=\"26\"></td>                                              "
                + "        <td width=\"100%\" colspan=\"2\" align=\"left\" background=\"" + imagesURL + "tableLayout/ReportPage_03.jpg\"><table width=\"95%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"
                + "          <tr>"
                + "            <td align=\"center\" class=\"heading-main\" style=\"padding-left:10px\">" + heading + "</td>"
                + "          </tr>"
                + "        </table></td>                        <td width=\"10\" align=\"left\"><img src=\"" + imagesURL + "tableLayout/ReportPage_04.jpg\" width=\"6\" height=\"26\"></td>                    "
                + "        </tr>  <tr>      <td width=\"10\" align=\"left\" background=\"" + imagesURL + "tableLayout/ReportPage_05.jpg\" style=\"background-repeat:repeat-y\"></td>                        <td colspan=\"2\" align=\"center\" valign=\"top\" bgcolor=\"#FFFFFF\">"
                + " ";

        return result;
    }

    public String tableHeader2(String heading, int width1, int height1) {
        String imagesURL = imagesURL();
        String result = null;

        result = "<table valign=\"top\" style=\"padding-top:0px;\" width=\"" + width1 + "\"  height=\"" + height1 + "\"  border=0 cellspacing=0 cellpadding=0>            <tr>                <td valign = top align=center >                                    <table width=100% border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" id=\"Table_01\">                    "
                + "          <tr>                        <td width=\"10\" align=\"right\"><img src=\"" + imagesURL + "tableLayout/ReportPage_01.jpg\" width=\"10\" height=\"26\"></td>                                             "
                + "        <td width=\"100%\" colspan=\"2\" align=\"left\" background=\"" + imagesURL + "tableLayout/ReportPage_03.jpg\"><table width=\"95%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"
                + "          <tr>"
                + "            <td align=\"center\" class=\"heading-main\" style=\"padding-left:10px\">" + heading + "</td>"
                + "          </tr>"
                + "        </table></td>                        <td width=\"10\" align=\"left\"><img src=\"" + imagesURL + "tableLayout/ReportPage_04.jpg\" width=\"6\" height=\"26\"></td>                    "
                + "        </tr> </table> ";

        return result;
    }
    //String heading, String width

    public String tableFooter() {
        String imagesURL = imagesURL();
        String result = null;
        //String result = null;
        result = "                        </td>"
                + "                        <td width=\"6\" align=\"right\" background=\"" + imagesURL + "tableLayout/ReportPage_08.jpg\" style=\"background-repeat:repeat-y\"></td>"
                + "                    </tr>"
                + "                    <tr>"
                + "                        <td width=\"10\" valign=\"top\"><img src=\"" + imagesURL + "tableLayout/ReportPage_09.jpg\" width=\"10\" height=\"6\"></td>"
                + "                        <td height=\"19\" colspan=\"2\" background=\"" + imagesURL + "tableLayout/ReportPage_11.jpg\" style=\"background-repeat:repeat-x\">&nbsp;</td>"
                + "                        <td width=\"6\" align=\"right\" valign=\"top\"><img src=\"" + imagesURL + "tableLayout/ReportPage_12.jpg\" width=\"6\" height=\"6\"></td>"
                + "                    </tr>"
                + "                </table>"
                + ""
                + "                </td>"
                + "            </tr>"
                + "        </table>   ";

        return result;
    }

    public String jdbcDriverMAIN() {
        String jdbcDriver = ecat_prop.getProperty("JDBCClassName");				// FOR TESTING ON ACCESS

        return jdbcDriver;
    }

    public String dsnPATH() {
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
            //dsnPATH = "jdbc:sqlserver://" + db_ip + ":" + db_port + ";SelectMethod=cursor";
            dsnPATH = "jdbc:sqlserver://" + db_ip + ":" + db_port + ";databaseName=" + db_name + ";useUnicode=true;characterEncoding=utf8;";
        } else {
            String mainMDBPATH = ecatPATH + "/dealer/database/" + UtilityMapkeys1.databasename + "";
            mainMDBPATH = mainMDBPATH.replaceAll("\\\\", "/");

            dsnPATH = "jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb); "
                    + "DBQ=" + mainMDBPATH + "; "
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

//	MAIN DATABASE USERNAME
    public String dbUserName() {
        String dbUserName = ecat_prop.getProperty("dbUserName");

        return dbUserName;
    }

//	MAIN DATABASE PASSWORD
    public String dbPasswd() {
        String dbPasswd = ecat_prop.getProperty("dbPwd");
        return dbPasswd;
    }

    public String dsnPATH_ORDER() {
        String dsnPATH_ORDER = "";
        String temp = orderRefNoString();
        if (temp.equals("WEB")) {
            dsnPATH_ORDER = dsnPATH();
        } else {
            dsnPATH_ORDER = "jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb); "
                    + "DBQ=" + ecatPATH + "/order/database/order_dealer.mdb; "
                    + "UserCommitSync=Yes; "
                    + "Threads=3; "
                    + "SafeTransactions=0; "
                    + "PageTimeout=5; "
                    + "MaxScanRows=8; "
                    + "MaxBufferSize=2048; "
                    + "DriverId=281; "
                    + "DefaultDir=C:/ProgramFiles/CommonFiles/ODBC/DataSources";
        }
        return dsnPATH_ORDER;
    }

// JDBC DRIVER FOR CBU SUPPORT SYSTEM
    public String jdbcDriverNHT() {
        String jdbcDriverNHT = ecat_prop.getProperty("JDBCClassNameNHT");				// FOR TESTING ON ACCESS

        return jdbcDriverNHT;
    }

// DSN PATH FOR CBU SUPPORT SYSTEM DATABASE
    public String dsnPATH_NHT() {
        String dsnPATH_NHT = "";
        String db = ecat_prop.getProperty("dbNHT");
        String db_ip = ecat_prop.getProperty("db_ipNHT");
        String db_port = ecat_prop.getProperty("db_portNHT");
        String db_name = ecat_prop.getProperty("db_nameNHT");
        if (db.equals("MYSQL")) {
            dsnPATH_NHT = "jdbc:mysql://" + db_ip + ":" + db_port + "/" + db_name + "";
        } else if (db.equals("ORACLE")) {
            dsnPATH_NHT = "jdbc:oracle:thin:@" + db_ip + ":" + db_port + ":" + db_name + "";
        } else if (db.equals("MSSQL2000")) {
            dsnPATH_NHT = "jdbc:microsoft:sqlserver://" + db_ip + ":" + db_port + ";SelectMethod=cursor";
        } else if (db.equals("MSSQL2005")) {
            dsnPATH_NHT = "jdbc:sqlserver://" + db_ip + ":" + db_port + ";SelectMethod=cursor";
        } else {
            String mainMDBPATH = ecatPATH + "/dealer/database/nhtdb.mdb";
            mainMDBPATH = mainMDBPATH.replaceAll("\\\\", "/");

            dsnPATH_NHT = "jdbc:odbc:DRIVER=Microsoft Access Driver (*.mdb); "
                    + "DBQ=" + mainMDBPATH + "; "
                    + "UserCommitSync=Yes; "
                    + "Threads=3; "
                    + "SafeTransactions=0; "
                    + "PageTimeout=5; "
                    + "MaxScanRows=8; "
                    + "MaxBufferSize=2048; "
                    + "DriverId=281; "
                    + "DefaultDir=C:/ProgramFiles/CommonFiles/ODBC/DataSources";
        }
        return dsnPATH_NHT;
    }

// USERNAME FOR CBU SUPPORT SYSTEM DATABASE
    public String nhtUserName() {
        String nhtUserName = ecat_prop.getProperty("dbUserNameNHT");
        return nhtUserName;
    }

// PASSWORD FOR CBU SUPPORT SYSTEM DATABASE
    public String nhtPassword() {
        String nhtPassword = ecat_prop.getProperty("dbPwdNHT");
        return nhtPassword;
    }

    public String servletURL() {
        return servletURL;
    }

    public String databaseNAME() {
        String databaseNAME = "";  //***************************** FOR ACCESS
        //String databaseNAME = "upper";  //***************************** FOR ORACLE

        return databaseNAME;
    }

    public String orderRefNoString() {
        return web_or_local;
    }

    public String serverIP() {
        String syncservername = ecat_prop.getProperty("syncservername");
        return syncservername;
    }

    public int serverPort() {
        int serverPort = 0;
        try {
            serverPort = Integer.parseInt(ecat_prop.getProperty("syncPort"));
        } catch (Exception ee) {
            serverPort = 0;
        }
        return serverPort;
    }

    public String ecat_imagesURL() {
        String ecat_imagesURL = mainURL + "dealer/ecat_image/";
        return ecat_imagesURL;
    }

    public String ecat_EngineSeries_imagesURL() {
        String ecat_imagesURL = mainURL + "dealer/ecat_print/engine_series_image/";
        return ecat_imagesURL;
    }

    public String ecat_EngineModel_imagesURL() {
        String ecat_imagesURL = mainURL + "dealer/ecat_print/engine_model_image/";
        return ecat_imagesURL;
    }

    public String ecat_Model_imagesURL() {
        String ecat_imagesURL = mainURL + "dealer/ecat_print/model_images/";
        return ecat_imagesURL;
    }

    public String ecat_sp_imagesURL() {
        String ecat_sp_imagesURL = mainURL + "dealer/ecat_print/op_sp_image/";
        return ecat_sp_imagesURL;
    }

    public String imagesURL() {
        String imagesURL = mainURL + "dealer/images/";
        return imagesURL;
    }

    public String animURL() {
        String animURL = mainURL + "dealer/anim/";
        return animURL;
    }

    public String mhtURL() {
        String mhtURL = mainURL + "dealer/htm/";
        return mhtURL;
    }

    public String printURL() {
        String printURL = mainURL + "dealer/ecat_print/";
        return printURL;
    }

    public String mainURL_dealer() {
        String mainURL_dealer = mainURL + "dealer/";
        return mainURL_dealer;
    }

    public String org_imagesURL() {
        String org_imagesURL = "http://" + server_name + "/ecatalogue/sol_org/org_print/";
        return org_imagesURL;
    }

    public String backupimageURL() {
        String backupimageURL = mainURL + "dealer/backup_image/";
        return backupimageURL;
    }

    public String modelURL() {
        String modelURL = mainURL + "dealer/ecat_print/model_images/";
        return modelURL;
    }

    public String modelIndexURL() {
        String modelIndexURL = mainURL + "dealer/ecat_print/block_index/";
        return modelIndexURL;
    }

    public String modelImage() {
        String modelImage = mainURL + "dealer/ecat_print/model_images/";
        return modelImage;
    }

    public String groupJPG() {
        String groupJPG = mainURL + "dealer/ecat_print/group_jpg/";
        return groupJPG;
    }

    //     ********** by Apurv *************
    public String aggregateJPG() {
        String groupJPG = mainURL + "dealer/ecat_print/imagesAGG/";
        return groupJPG;
    }
    //     *********************************

    public String part_imagesURL() {
        String part_imagesURL = mainURL + "dealer/ecat_print/part_image/";
        return part_imagesURL;
    }

    public String group_imagesURL() {
        String group_imagesURL = mainURL + "dealer/ecat_print/group_image/";
        return group_imagesURL;
    }

    public String patchesURL() {
        String patchesURL = mainURL + "dealer/patches/";
        return patchesURL;
    }

    public String recordsURL() {
        String recordsURL = mainURL + "dealer/ecat_print/change_records/";
        return recordsURL;
    }

    public String kitImageURL() {
        String kitIMAGE = mainURL + "dealer/ecat_print/kit_image/";
        return kitIMAGE;
    }

    public String toolImageURL() {
        String toolIMAGE = mainURL + "dealer/ecat_print/tool_image/";
        return toolIMAGE;
    }

    public String header(String type) {
        String img_url = imagesURL();

        String header = "<html>" + "<head>" + "<title>" + UtilityMapkeys1.tile + "</title><meta http-equiv=Content-Type content=text/html; charset=iso-8859-1>" + "	<style type=text/css>" + "<!--" + "			body" + "		{" //		+	"				scrollbar-face-color: #cccccc; scrollbar-arrow-color: #000000; scrollbar-track-color: #E2EEFA;"
                + "		}" + "* { font-family: Helvetica; font-size: 9pt; }" + " a:link {				color: #000000;				text-decoration: none; }" + " a:visited { color: #000000;				text-decoration: none;		  }	" + "		-->" + "</style>" + "</head>" + "<body  leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 >" + "<table width=100% border=0 align=center cellpadding=0 cellspacing=0>" + "<tr>" + "<td height=350 valign=top>" + "<div align=left>";
        return header;
    }
//    public String tableHeader2(String heading, int width1, int height1, String align, String tdRight) {
//        String imagesURL = imagesURL();
//        String result = null;
//        //String tdData="";
//
//        result = "<table width=\"" + width1 + "\"  height=\"" + height1 + "\"  border=0 cellspacing=0 cellpadding=0>            <tr>                <td valign = top align=center style=\"padding-top:10px;padding-left:12px\">                                    <table width=100% border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" id=\"Table_01\">                    "
//                + "          <tr>                        <td width=\"10\" align=\"right\"><img src=\"" + imagesURL + "tableLayout/ReportPage_01.jpg\" width=\"10\" height=\"26\"></td>                        <td width=\"6\" align=\"left\" background=\"" + imagesURL + "tableLayout/ReportPage_02.jpg\">&nbsp;</td>                        "
//                + "        <td width=\"100%\" align=\"left\" background=\"" + imagesURL + "tableLayout/ReportPage_03.jpg\"><table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">"
//                + "          <tr>"
//                + "            <td width=80% align=\"" + align + "\"  class=\"heading-main\">" + heading + "</td>" + tdRight
//                + "          </tr>"
//                + "        </table></td>                        <td width=\"10\" align=\"left\"><img src=\"" + imagesURL + "tableLayout/ReportPage_04.jpg\" width=\"6\" height=\"26\"></td>                    "
//                + "        </tr>  <tr>      <td width=\"10\" align=\"left\" background=\"" + imagesURL + "tableLayout/ReportPage_05.jpg\" style=\"background-repeat:repeat-y\"></td>                        <td colspan=\"2\" align=\"center\" valign=\"top\" bgcolor=\"#FFFFFF\">"
//                + " ";
//
//        return result;
//    }

//    public  String[] advanceSearchVar()
//    {
//       return new String[]{"Model No","Model Desc","Part Desc","Part No.","Group No","Group Desc"};
//    }
//    public  String[] advanceSearchVarAlert()
//    {
//       return new String[]{"Model No","Model Desc","Part Desc","Part No.","Group No","Group Desc"};
//    }
    public String footer() {
        /*String footer = "</div>"
         +	"</td>"
         +	"</tr>"
         +	"</table>"
         +	"<table width=100% border=0 align=center cellpadding=0 cellspacing=0>"
         +	"<tr>"
         +	"<td>"
         +	"<div align=center>"
         +	"<font color=#000000 size=1 face=Helvetica>"
         +	"<br>"
         +	"Optimized for IE 5.5 + browsers in 16 bit colour at 800 x 600 resolution."
         +	"<br>"
         +	"Maintained &amp; Developed By"
         +	"</font>"
         +	"<br>"
         +	"<a href=http://www.hitechesoft.com target=_blank>"
         +	"<font color=#000000 size=1 face=Helvetica>"
         +	"Hi - Tech e Soft (A Division of Hi - Tech Gears Ltd.) "
         +	"</font>"
         +	"</a>"
         +	"</div>"
         +	"</td>"
         +	"</tr>"
         +	"<tr>"
         +	"</tr>"
         +	"</table>"
         +	"</body>"
         +	"</html>	";
         return footer;*/

        String footer = "</div>" + "</td>" + "</tr>" + "</table>" + "</body>" + "</html>	";
        return footer;
    }

    public String footer2() {
        String footer2 = "</div>" + "</td>" + "</tr>" + "</table>" + "</body>" + "</html>	";
        return footer2;
    }

    public String loginAgain() {
        //String img_url = imagesURL();
        //String loginAgain="<a href='Index' target =\"_top\"><font class=\"text\">Please Login Again</font></a>";
        //String loginAgain = "<html><body  leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 ><br><br><br><br><br><br><center> " + "<table width=300 border=1 cellpadding=4 cellspacing=1 bordercolor=#000000>" + "<tr> " + "<td bgcolor=#003399 align = center>" + "	<font size=2 face=Helvetica color=#FFFFFF>" + "      <strong>" + "			SESSION  EXPIRED" + "			<br>" + "			<a href='Index' target = _top><font color=#FFFFFF size=1 face=helvetica>CLICK HERE TO BROWSE AGAIN.</a>" + "		</strong>" + "	</font>" + "</td>" + "</tr>" + "</table></body></html>";
        String loginAgain = "<center>"
                + "            <table align=\"center\"  valign=\"top\" cellspacing=0 cellpadding=0 width=\"80%\" border=0>"
                + "                <tr>"
                + "                    <td class=\"links_text\" valign=top align=\"center\">"
                + "                        <br>"
                + "                        <a href=\"Index\" target=\"_top\">"
                + "                            <font class=\"text\">Please Login Again</font></a>"
                + "                    </td>"
                + "                </tr>"
                + "            </table>"
                + "        </center>";
        return loginAgain;
    }

    public String scriptPRINT() {
        String scriptPRINT = "<script language=javascript>" + "var check;" + "var k=0;" + "function start_action(vv)" + "{" //		+  "	window.open(vv,\"sa\");"
                + "	document.location.href = vv;" //		+  "	window.open(\"v_alert\",\"Alert\",\"height=171,width=250,left=350,top=200,alwaysRaised=yes \");"
                + "}" + "function start_action_new(vv)" + "{" //		+  "	window.open(vv,\"sa\");"
                + "	document.location.href = vv;" //		+  "	window.open(\"v_alert\",\"Alert\",\"height=171,width=250,left=350,top=200,alwaysRaised=yes \");"
                + "}" + "</script>";
        return scriptPRINT;
    }

    public void ShowMsg(PrintWriter ps, String result, String show_message, String optionLink, String optionLinkName, String optionLinkURL, String backURL, String imagesURL) {
        ps.println("<html>" + "    <head>" + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" + "        <link rel='stylesheet' type='text/css' href='" + imagesURL + "style.css' />" + "        <title>" + UtilityMapkeys1.tile + "</title>" + "<style type=text/css>" + "            <!--" + "            body {" + "                margin-left: 0px;" + "                margin-top: 10px;" + "                margin-right: 0px;" + "                margin-bottom: 0px;" + "            }" + "            -->" + "        </style>" + "    ");
        ps.println("</head>");
        ps.println("<body cellpadding=\"0\" cellspacing=\"0\" border=0 >");
        ps.println("<table width=70% cellpadding=\"0\" cellspacing=\"0\" border=0  align=\"center\">");
        ps.println("    <tr >");
        ps.println("        <td>");
        ps.println("            &nbsp;");
        ps.println("        </td>");
        ps.println("    </tr>");
        ps.println("    <tr>");
        ps.println("        <td>");
        ps.println("            &nbsp;");
        ps.println("        </td>");
        ps.println("    </tr>");

        if ((show_message != null) && !(show_message.equals("null"))) {
            ps.println("<tr>");
            ps.println("<td>");
            ps.println("<center class=\"links_txt\">");
            ps.println("<br><br>");
            if ((result != null) && !result.equals("null")) {
                if (result.equalsIgnoreCase("SUCCESS")) {
                    ps.println("<img src=\"" + imagesURL + "success.gif\">");
                }
                if (result.equalsIgnoreCase("FAILURE")) {
                    ps.println("<img src=\"" + imagesURL + "failure.gif\">");
                }
            }

            ps.println("<b>" + show_message + "</b>");

            ps.println("</center>");
            ps.println("</td>");
            ps.println("</tr>");
        }
        if ((optionLink != null) && !optionLink.equalsIgnoreCase("null") && optionLink.equalsIgnoreCase("YES")) {
            ps.println("<tr>");
            ps.println("<td class=links_txt align=center>");
            ps.println("<br>");
            ps.println(" <a href=" + optionLinkURL + " target=\"_top\">" + optionLinkName + "</a>");
            ps.println(" </td>");
            ps.println("</tr> ");
        }

        if ((backURL != null) && !backURL.equalsIgnoreCase("null") && !backURL.equalsIgnoreCase("")) {
            ps.println("<tr bgcolor=#ffffff height=20>");
            ps.println("<td valign = middle align = right class=links_txt>");
            ps.println("<br>");
            ps.println("<br>");
            ps.println(" <a href=" + backURL + " class=red-for-temmplate-link > << BACK </a>");
            ps.println(" </td>");
            ps.println("</tr> ");
        }

        ps.println("    </table>");
        ps.println("    </body></html>");
    }

    public void ShowMsg1(PrintWriter ps, String result, String show_message, String optionLink, String optionLinkName, String optionLinkURL, String backURL, String imagesURL) {
        ps.println("<html>" + "    <head>" + "        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">" + "        <link rel='stylesheet' type='text/css' href='" + imagesURL + "style.css' />" + "        <title>" + UtilityMapkeys1.tile + "</title>" + "<style type=text/css>" + "            <!--" + "            body {" + "                margin-left: 0px;" + "                margin-top: 10px;" + "                margin-right: 0px;" + "                margin-bottom: 0px;" + "            }" + "            -->" + "        </style>" + "    ");
        ps.println("</head>");
        ps.println("<body cellpadding=\"0\" cellspacing=\"0\" border=0 >");
        ps.println("<table width=70% cellpadding=\"0\" cellspacing=\"0\" border=0  align=\"center\">");
        ps.println("    <tr >");
        ps.println("        <td>");
        ps.println("            &nbsp;");
        ps.println("        </td>");
        ps.println("    </tr>");
        ps.println("    <tr>");
        ps.println("        <td>");
        ps.println("            &nbsp;");
        ps.println("        </td>");
        ps.println("    </tr>");

        if ((show_message != null) && !(show_message.equals("null"))) {
            ps.println("<tr>");
            ps.println("<td>");
            ps.println("<center class=\"links_txt\">");
            ps.println("<br><br>");
            if ((result != null) && !result.equals("null")) {
                if (result.equalsIgnoreCase("SUCCESS")) {
                    ps.println("<img src=\"" + imagesURL + "success.gif\">");
                }
                if (result.equalsIgnoreCase("FAILURE")) {
                    ps.println("<img src=\"" + imagesURL + "failure.gif\">");
                }
            }

            ps.println("<b>" + show_message + "</b>");

            ps.println("</center>");
            ps.println("</td>");
            ps.println("</tr>");
        }
        if ((optionLink != null) && !optionLink.equalsIgnoreCase("null") && optionLink.equalsIgnoreCase("YES")) {
            ps.println("<tr>");
            ps.println("<td class=links_txt align=center>");
            ps.println("<br>");
            ps.println(" <a href=" + optionLinkURL + " target=\"_parent\">" + optionLinkName + "</a>");
            ps.println(" </td>");
            ps.println("</tr> ");
        }

        if ((backURL != null) && !backURL.equalsIgnoreCase("null") && !backURL.equalsIgnoreCase("")) {
            ps.println("<tr bgcolor=#ffffff height=20>");
            ps.println("<td valign = middle align = right class=links_txt>");
            ps.println("<br>");
            ps.println("<br>");
            ps.println(" <a href=" + backURL + " class=red-for-temmplate-link > << BACK </a>");
            ps.println(" </td>");
            ps.println("</tr> ");
        }

        ps.println("    </table>");
        ps.println("    </body></html>");
    }
}
