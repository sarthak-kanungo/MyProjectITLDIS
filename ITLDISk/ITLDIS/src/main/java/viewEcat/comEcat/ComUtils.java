/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viewEcat.comEcat;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.Properties;
import java.util.Vector;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpSession;

/**
 *
 * @author deepak.mangal
 */
public class ComUtils {

    /******************************************************************
     * @param outfilename
     * @param group
     * @param ps
     * @param saveLocation
     * @param ecatPATH
     * @return boolean , It Compresses the File 
     ***************************************************************/
    public static boolean compress_file(Vector filename, String outfilename, String group, PrintWriter ps, String saveLocation, String ecatPATH) {
        try {
            byte[] buf = new byte[102400];
            int retval;

            FileInputStream is;
            FileOutputStream os = new FileOutputStream(outfilename);
            ZipOutputStream zos = new ZipOutputStream(os);

            for (int i = 0; i < filename.size(); i++) {
                is = new FileInputStream("" + filename.elementAt(i));

                String entryPath = "" + filename.elementAt(i);
                entryPath = entryPath.replaceAll(saveLocation, "");

                zos.putNextEntry(new ZipEntry(entryPath));
                do {
                    retval = is.read(buf, 0, 102400);
                    if (retval != -1) {
                        zos.write(buf, 0, retval);
                    }
                } while (retval != -1);
                zos.closeEntry();
                is.close();
            }
            zos.close();
            return true;
        } catch (Exception e) {
            ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 height = 80% width=50%>");
            ps.println("<tr>");
            ps.println("<td valign = middle>");
            ps.println("<table cellspacing=0 cellpadding=10 border=1 width=100% bordercolor=#D7D7D7>");
            ps.println("<tr bgcolor = #003399>");
            ps.println("<td align = center>");
            ps.println("<font color = #FFFFFF face=Verdana size = 2><b>");
            ps.println("ERROR OCCURED. CODE - ZIP.<br>");
            ps.println("FILES TO ZIP DOES NOT EXISTS.<br>");
            ps.println("CONTACT ADMINISTRATOR");
            ps.println("</td>");
            ps.println("</tr>");
            ps.println("</table>");

            ps.println("</td>");
            ps.println("</tr>");
            ps.println("</table>");
            return false;
        }
    }

//    public static int loginTrakerByUserId(String userCode, int usageType, String remoteIPAddr, Connection conn) throws Exception, SQLException {
//        int isCreated = 0;
//        PreparedStatement pstmt = null, pstmt1 = null;
//        ResultSet rs = null;
//        Calendar currenttime = Calendar.getInstance();
//        Date sqldate = new Date((currenttime.getTime()).getTime());
//        int count = 1;
//
//        try {
//            pstmt1 = conn.prepareStatement("select COUNT from UM_LOGIN_TRACK where USER_ID=? and LOGIN_DATE=? and USAGE_TYPE_ID=?");
//            pstmt1.setString(1, userCode);
//            pstmt1.setDate(2, sqldate);
//            pstmt1.setInt(3, usageType);
//            rs = pstmt1.executeQuery();
//            if (rs.next()) {
//                count = rs.getInt("COUNT");
//                count++;
//                pstmt = conn.prepareStatement("update UM_LOGIN_TRACK set COUNT=?,IP=? where USER_ID=? and LOGIN_DATE=? and USAGE_TYPE_ID=?");
//                pstmt.setInt(1, count);
//                pstmt.setString(2, remoteIPAddr);
//                pstmt.setString(3, userCode);
//                pstmt.setDate(4, sqldate);
//                pstmt.setInt(5, usageType);
//                pstmt.executeUpdate();
//            } else {
//                pstmt = conn.prepareStatement("insert into UM_LOGIN_TRACK values (?,?,?,?,?)");
//                pstmt.setString(1, userCode);
//                pstmt.setString(2, remoteIPAddr);
//                pstmt.setDate(3, sqldate);
//                pstmt.setInt(4, count);
//                pstmt.setInt(5, usageType);
//                pstmt.executeUpdate();
//            }
//            rs.close();
//            pstmt.close();
//            pstmt = null;
//            isCreated = 1;
//            pstmt1.close();
//            conn.commit();
//        } catch (Exception ex) {
//            isCreated = 2;
//            conn.rollback();
//            ex.printStackTrace();
//            //logger.error ( WebConstants.logException , ex );
//        } finally {
//            try {
//                if (pstmt != null) {
//                    pstmt.close();
//                }
//                if (pstmt1 != null) {
//                    pstmt1.close();
//                }
//            } catch (Exception e) {
//               // System.out.println("i m here in finally");
//                e.printStackTrace();
//                //logger.error ( WebConstants.fLogException , e );
//            }
//        }
//        return isCreated;
//    }

    public static String getLanguageTranslation(String str, HttpSession session) throws Exception {
    	 
        Properties prop = (Properties) session.getAttribute("prop");
 
        if (prop.getProperty(str) == null) {
            InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream("com/myapp/struts/ApplicationResource_en_US.properties");
            Properties prop1 = new Properties();
 
            try {
                prop1.load(is);
            } catch (Exception e) {
            //    System.out.println("Resource loader cannot be loaded");
                e.printStackTrace();
            }
            return prop1.getProperty(str);
        } else {
            return prop.getProperty(str);
        }
    }
    
    
    public static String getPropertyValue(String key) throws Exception {
        // Define a default properties file path
        String defaultPropertiesFile = "com/myapp/struts/ApplicationResource.properties";
        
        // Load properties from default properties file
        Properties defaultProps = new Properties();
        try (InputStream is = Thread.currentThread().getContextClassLoader().getResourceAsStream(defaultPropertiesFile)) {
            if (is != null) {
                defaultProps.load(is);
            }
        } catch (Exception e) {
            e.printStackTrace();
            // Handle the exception as appropriate
        }

        // Return the property value, or null if not found
        return Optional.ofNullable(defaultProps.getProperty(key)).orElse("Property not found");
    }
}
