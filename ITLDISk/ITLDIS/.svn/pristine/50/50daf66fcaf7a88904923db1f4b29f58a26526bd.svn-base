package viewEcat.comEcat;
/*
File Name: Authenticate_user.java
PURPOSE: File Used For Checking The Authenticity Of User And For Synchronizing Web & Local Database
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.common.EncryptionDecryption;

import authEcat.UtilityMapkeys1;
public class Authenticate_user extends HttpServlet {

    public static final void copyInputStream(InputStream in, OutputStream out) throws IOException {
        byte[] buffer = new byte[1024];
        int len;

        while ((len = in.read(buffer)) >= 0) {
            out.write(buffer, 0, len);
        }

        in.close();
        out.close();
    }

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");

        res.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
        res.setHeader("Expires", "0");
        res.setHeader("Pragma", "no-cache");
        StringBuilder productFamilySubQuery = null;
        StringBuilder applicationTypSubQuery = null;
        PageTemplate object_pageTemplate = new PageTemplate();
        String imagesURL = object_pageTemplate.imagesURL();
        PreparedStatement pst = null;
       // Statement st = null, st1 = null;
        PreparedStatement stmt1 = null;
        ResultSet rs = null;
        //Statement stmt = null;
        PreparedStatement stmt = null;
        Vector userFunTemp=null;
        try {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            /////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();
            //System.out.println("session_id inside ITL-DIS=="+session_id);
            String getType = (String) session.getValue("user_type");


            String WEB_or_LOCAL = PageTemplate.web_or_local;

            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            int[] versionInstalled = new int[4];
//          String[] versionUploaded = new String[4];

            String ecatPATH = "";
            String password = "";

            String screenWidth = "";
            String screenHeight = "";
         
            int q = 0;

            String form_id = (String)session.getAttribute("user_id");//req.getParameter("id");
            //String form_id = "e";
           // String userCode = form_id;
          //  String lanId = "";
            Connection conn = null;

            //String imagesURL = "";

            String form_password_Web = "";
            String userStatus = "";
            //PageTemplate object_pageTemplate = new PageTemplate();

            ////////////////////////////////////////////////////////////////////////////////
            
            
            //if (getType == null) { //comment vj
                String form_password = (String)session.getAttribute("password");//req.getParameter("password");
              //  PasswordEncryption passEncry = null;//new
              //  passEncry = new PasswordEncryption();//new

                form_password_Web = EncryptionDecryption.encrypt(form_password);//new
                
               // lanId = req.getParameter("lanId");

                screenWidth = req.getParameter("width");
                screenHeight = req.getParameter("height");

                String server_name = PageTemplate.server_name;
                String mainURL = PageTemplate.mainURL;
                ecatPATH = PageTemplate.ecatPATH;

                /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////



                imagesURL = object_pageTemplate.imagesURL();
                String dsnPATH = object_pageTemplate.dsnPATH();
                String animURL = object_pageTemplate.animURL();
                String group_imagesURL = object_pageTemplate.group_imagesURL();
   
                String servletURL = object_pageTemplate.servletURL();
                String variantEffectiveDate = object_pageTemplate.effectiveDate;

                /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////


                ConnectionHolder holder = null;
                String dbUserName = object_pageTemplate.dbUserName();
                String dbPasswd = object_pageTemplate.dbPasswd();

                Class.forName(object_pageTemplate.jdbcDriverMAIN());
                holder = new ConnectionHolder(DriverManager.getConnection(dsnPATH, dbUserName, dbPasswd));

                session.putValue("servletapp.connection", holder);

                conn = holder.getConnection();
                //st = conn.createStatement();
                //st1 = conn.createStatement();

                String type = "";
                //java.sql.Date lastDate = new java.sql.Date(0);

                Session dsn1 = new Session();
                dsn1.putDsnPath(dsnPATH);
                dsn1.putUserName(dbUserName);
                dsn1.putPassword(dbPasswd);
                dsn1.putServletURL(servletURL);
                dsn1.putConnection(conn);

                //java.sql.Date lastDate = new java.sql.Date(0);
                //java.sql.Date systemDate = new java.sql.Date(new java.util.Date().getTime());
                if (WEB_or_LOCAL.equals("WEB")) {

                    // CHECK USER-STATUS IN DATABASE
                    pst = conn.prepareStatement("SELECT * FROM UM_user_check(NOLOCK) where user_id =?");
                    pst.setString(1, form_id);
                    rs = pst.executeQuery();
                    if (rs.next()) {

                        if (rs.getString("PASSWORD").equals(form_password_Web)) {
                            type = rs.getString("USER_TYPE_ID");
                            userStatus = rs.getString("STATUS");
                            // IF USER STATUS IS CLOSED, THEN HE IS NOT ALLOWED TO VIEW E-CATALOGUE
                            if (userStatus.equals("CLOSED")) {
                                rs.close();
                                pst.close();
                                object_pageTemplate.ShowMsg(ps, "FAILURE", "Unauthorized User !!Contact " + UtilityMapkeys1.tile1 + " Administrator.", "NO", "", "", "", imagesURL);
                                return;
                            }
                        } else {
                            rs.close();
                            pst.close();
                            object_pageTemplate.ShowMsg(ps, "FAILURE", "Login Failure: Enter Correct Password.", "YES", "Please Login Again", "Index", "", imagesURL);
                            return;
                        }

                    } else {
                        rs.close();
                        pst.close();
                        object_pageTemplate.ShowMsg(ps, "FAILURE", "Login Failure: Enter Correct User ID.", "YES", "Please Login Again", "Index", "", imagesURL);
                        return;
                    }
                    rs.close();
                    //pst.close();


                } else {
                    // CHECK IF THE USER-ID EXISTS IN DATABASE
                    pst = conn.prepareStatement("SELECT * FROM UM_user_check(NOLOCK) where user_id =?");
                    pst.setString(1, form_id);
                    rs = pst.executeQuery();
                    if (rs.next()) {
                        form_id = rs.getString("USER_ID");
                        password = rs.getString(2);
                        type = rs.getString(3);
                        userStatus = rs.getString("STATUS");

                        // IF PASSWORD DOESN'T MATCH
                        if (!form_password.equals(password)) {
                            rs.close();
                            pst.close();
                            object_pageTemplate.ShowMsg(ps, "FAILURE", "Login Failure: Enter Correct Password.", "YES", "Please Login Again", "Index", "", imagesURL);
//                         
                            return;
                        }
                    } // IF USER-ID NOT FOUND
                    else {
                      //  checkOnWeb = true;
                    }
                    rs.close();

                    //pst.close();
                }
                Vector userSessionVec = null;
                // If successully logged in
                userSessionVec = new Vector();
                userSessionVec.add(form_id);
                userSessionVec.add("" + type);
                userSessionVec.add(session);
                LoginVector.loginVec.add(userSessionVec);


                session.putValue("session_value", session_id);
                session.putValue("user_type", type);
                session.putValue("screenWidth", screenWidth);
                session.putValue("screenHeight", screenHeight);

                session.putValue("server_name", server_name);
                session.putValue("mainURL", mainURL);
                session.putValue("ecatPATH", ecatPATH);
                session.putValue("userCode", form_id);
                session.setAttribute("effectiveDate", variantEffectiveDate);

                getType = type;

                dsn1.putGetType(getType);
                dsn1.putEcatPATH(ecatPATH);
                dsn1.putDate_OR_serial("latest");
                dsn1.putAnimURL(animURL);
                dsn1.putImagesURL(imagesURL);
                dsn1.putGroup_imagesURL(group_imagesURL);

                java.sql.Date inputDate = new java.sql.Date(new java.util.Date().getTime());
                java.sql.Date buckleDate = new java.sql.Date(new java.util.Date().getTime());

                session.putValue("date_OR_serial", "latest");
                session.putValue("input_Date", inputDate);
                session.putValue("modelNo", "");
                session.putValue("input_serialNo", "");
                session.putValue("buckleDate", buckleDate);
                //    session.putValue("cmt_Rev_No", "" + cmt_Rev_No);


                int lastRow = 0;
                int patchNo = 0;

                //rs = st.executeQuery("SELECT MAX(sno) FROM CAT_CD");
                String query = ("SELECT MAX(sno) FROM CAT_CD(NOLOCK)");
                stmt1 = conn.prepareStatement(query);
                rs = stmt1.executeQuery();
                if (rs.next()) {
                    lastRow = rs.getInt(1);
                }
                rs.close();

                //rs = st.executeQuery("SELECT MAX(PATCH_NO) FROM CAT_PATCH WHERE CD_NO= " + lastRow);
                String query1 = ("SELECT MAX(PATCH_NO) FROM CAT_PATCH(NOLOCK) WHERE CD_NO= " + lastRow);
                stmt1 = conn.prepareStatement(query1);
                rs = stmt1.executeQuery();
                if (rs.next()) {
                    String temp = rs.getString(1);
                    if (temp != null) {
                        patchNo = Integer.parseInt(temp);
                    }
                }
                rs.close();

                versionInstalled[0] = 3;
                versionInstalled[1] = 4;
                versionInstalled[2] = lastRow;
                versionInstalled[3] = patchNo;
               // cdNo = lastRow;
              //  patchNum = patchNo;
                //st.close();
                //st1.close();
          //  } // comment vj

            ps.println("<script>");
            ps.println("'window.open(\"Status?status=Verifying_Machine\",\"STATUSWIN\",\"width = 150,height = 100\")';");
            ps.println("</script>");


            if (true) {
                userFunTemp = new EcatAuthorisation().getUserFunctionalties(conn, getType);
                Vector userFun = new Vector(userFunTemp);
///

                //stmt = conn.createStatement();
                //rs = stmt.executeQuery("select distinct ENGINE_SERIES from CAT_USER_PRODUCT_FAMILY where USER_ID='" + form_id + "'");
                String query2 = ("select distinct ENGINE_SERIES from CAT_USER_PRODUCT_FAMILY(NOLOCK) where USER_ID='" + form_id + "'");
                stmt = conn.prepareStatement(query2);
                rs = stmt.executeQuery();

                productFamilySubQuery = new StringBuilder("");
                while (rs.next()) {
                    productFamilySubQuery.append("'" + rs.getString(1) + "',");
                }
                if (productFamilySubQuery.length() > 1) {
                    productFamilySubQuery.deleteCharAt(productFamilySubQuery.length() - 1);
                    productFamilySubQuery.insert(0, " where ENGINE_SERIES in(");
                    productFamilySubQuery.append(")");


                }

                rs.close();

                applicationTypSubQuery = new StringBuilder("");
                //rs = stmt.executeQuery("select distinct APPLICATION_TYPE from CAT_USER_PRODUCT_TYPE where USER_ID='" + form_id + "'");
                String query3 = ("select distinct APPLICATION_TYPE from CAT_USER_PRODUCT_TYPE(NOLOCK) where USER_ID='" + form_id + "'");
                stmt = conn.prepareStatement(query3);
                rs = stmt.executeQuery();
                while (rs.next()) {
                    applicationTypSubQuery.append("'" + rs.getString(1) + "',");
                }
                if (applicationTypSubQuery.length() > 1) {
                    applicationTypSubQuery.deleteCharAt(applicationTypSubQuery.length() - 1);
                    applicationTypSubQuery.insert(0, " and APPLICATION_TYPE in(");
                    applicationTypSubQuery.append(")");
                }

                rs.close();
                //stmt.close();

                session.setAttribute("productFamilySubQuery", productFamilySubQuery.toString());
                session.setAttribute("applicationTypSubQuery", applicationTypSubQuery.toString());

                applicationTypSubQuery.setLength(0);
                applicationTypSubQuery = null;
                productFamilySubQuery.setLength(0);
                productFamilySubQuery = null;

     
                session.putValue("userFun", userFun);


             /*   if (WEB_or_LOCAL.equals("WEB")) {
                    ComUtils.loginTrakerByUserId(form_id, 1, req.getRemoteAddr(), conn);
                }
*/
//                ps.println("<HTML>");
//                ps.println("<HEAD>");
//                ps.println("<TITLE>" + UtilityMapkeys1.tile1 + " - E-CATALOGUE</TITLE>");
//                ps.println("<META HTTP-EQUIV=Content-Type CONTENT=text/html; charset=iso-8859-1>");
//                ps.println("</HEAD>");
//                ps.println("<body onLoad='javascript:document.location.href=\"Home_page?id=" + form_id + "&q=" + q + "\"'>");
//                ps.println("</BODY>");
//                ps.println("</HTML>");

                ps.println("<HEAD>");
                ps.println("<TITLE>" + UtilityMapkeys1.tile1 + "</TITLE>");
                ps.println("<META HTTP-EQUIV=Content-Type CONTENT=text/html; charset=iso-8859-1>");
                ps.println("</HEAD>");
                ps.println("<body onLoad=\"javascript:document.homeForm.submit();\">");
                ps.println("<form action='Authenticate_criteria' method='POST' name='homeForm'>");
                ps.println("<input type='hidden' name='criteria' value='latest'/>");
                ps.println("<input type='hidden' name='q' value='" + q + "'/>");
                ps.println("<input type='hidden' name='id' value='" + form_id + "'/>");
                ps.println("</form>");
                ps.println("</BODY>");
                ps.println("</HTML>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            object_pageTemplate.ShowMsg(ps, "FAILURE", "Login Failure: Enter Correct User ID.", "YES", "Please Login Again", "Index", "", imagesURL);
            return;
        } finally {
            try {
                //st.close();
            	stmt1.close();
                //st1.close();
                pst.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            ps.close();
            wps.close();
        }
    }
}
