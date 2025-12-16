package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
File Name: ChangePassword_2.java
PURPOSE: To store the new password in database.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import authEcat.UtilityMapkeys1;

public class ChangePassword_2 extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        try {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            String getType = (String) session.getValue("user_type");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String message = null;


            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);
            String header = "";
            header = object_pageTemplate.header(getType);

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();


            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////


            ps.println(header);

            if (session_id.equals(getSession)) {
                Connection conn = holder.getConnection();
                //Statement stmt;
                PreparedStatement stmt = null;
                ResultSet rs;
                //PreparedStatement pstmt;
                PreparedStatement pstmt = null;

                //stmt = conn.createStatement();

                String login = req.getParameter("login");
                String oldPassword = req.getParameter("oldPassword");
                String newPassword_1 = req.getParameter("newPassword_1");
                String newPassword_2 = req.getParameter("newPassword_2");

                ps.println("<HTML>");
                ps.println("<HEAD>");
                ps.println("<TITLE>" + UtilityMapkeys1.tile1 + "</TITLE>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
                ps.println("</head>");
                ps.println("<BODY >");

                String password = "";
                //rs = stmt.executeQuery("SELECT * FROM UM_user_check where user_id ='" + login + "'");
                String query = ("SELECT * FROM UM_user_check(NOLOCK) where user_id ='" + login + "'");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    password = rs.getString(2);
                }
                rs.close();
                stmt.close();

                int width = 759;
                int height = 300;


                if (!(oldPassword.equals(password))) {

                    message = "Invalid Old Password";
                    ps.println(object_pageTemplate.tableHeader4(message, width, height));
                    // object_pageTemplate.ShowMsg(ps, "FAILURE", "NO MESSAGE AVAILABLE", "NO", "", "", "", imagesURL);
//                    return;
//                    ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 height = 95% width=90%>");
//                    ps.println("<tr>");
//                    ps.println("<td valign = middle>");
//                    ps.println("<table cellspacing=0 cellpadding=10 border=0 width=100% bordercolor=#D7D7D7>");
//                    ps.println("<tr bgcolor = #003399>");
//                    ps.println("<td align = center>");
//                    ps.println("<font color = #FFFFFF><b>");
//                    ps.println("INVALID OLD PASSWORD.");
//                    ps.println("</td>");
//                    ps.println("</tr>");
//                    ps.println("<tr>");
//                    ps.println("<td valign = middle align = right>");
//
//                    ps.println("<a href ='ChangePassword_1?id=" + login + "'>");
//                    ps.println("<b>");
//                    ps.println("<font color=#000099><< BACK</font>");
//                    ps.println("</a>");
//
//                    ps.println("</td>");
//                    ps.println("</tr>");
//                    ps.println("</table>");
//                    ps.println("</td>");
//                    ps.println("</tr>");
//                    ps.println("</table>");
                    return;
                }

                if (oldPassword.equals(newPassword_1)) {

                    message = "New Password Cannot Be Same As Old Password";
                    ps.println(object_pageTemplate.tableHeader4(message, width, height));

//                    ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 height = 95% width=90%>");
//                    ps.println("<tr>");
//                    ps.println("<td valign = middle>");
//                    ps.println("<table cellspacing=0 cellpadding=10 border=0 width=100% bordercolor=#D7D7D7>");
//                    ps.println("<tr bgcolor = #003399>");
//                    ps.println("<td align = center>");
//                    ps.println("<font color = #FFFFFF><b>");
//                    ps.println("NEW PASSWORD CANNOT BE SAME AS OLD PASSWORD.");
//                    ps.println("</td>");
//                    ps.println("</tr>");
//                    ps.println("<tr>");
//                    ps.println("<td valign = middle align = right>");
//
//                    ps.println("<a href ='ChangePassword_1?id=" + login + "'>");
//                    ps.println("<b>");
//                    ps.println("<font color=#000099><< BACK</font>");
//                    ps.println("</a>");
//
//                    ps.println("</td>");
//                    ps.println("</tr>");
//                    ps.println("</table>");
//                    ps.println("</td>");
//                    ps.println("</tr>");
//                    ps.println("</table>");
                    return;
                }

                if (!(newPassword_1.equals(newPassword_2))) {

                    message = "Invalid New Password";
                    ps.println(object_pageTemplate.tableHeader4(message, width, height));

//                    ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 height = 95% width=90%>");
//                    ps.println("<tr>");
//                    ps.println("<td valign = middle>");
//                    ps.println("<table cellspacing=0 cellpadding=10 border=0 width=100% bordercolor=#D7D7D7>");
//                    ps.println("<tr bgcolor = #003399>");
//                    ps.println("<td align = center>");
//                    ps.println("<font color = #FFFFFF><b>");
//                    ps.println("INVALID NEW PASSWORD.");
//                    ps.println("</td>");
//                    ps.println("</tr>");
//                    ps.println("<tr>");
//                    ps.println("<td valign = middle align = right>");
//
//                    ps.println("<a href ='ChangePassword_1?id=" + login + "'>");
//                    ps.println("<b>");
//                    ps.println("<font color=#000099><< BACK</font>");
//                    ps.println("</a>");
//
//                    ps.println("</td>");
//                    ps.println("</tr>");
//                    ps.println("</table>");
//                    ps.println("</td>");
//                    ps.println("</tr>");
//                    ps.println("</table>");
                    return;
                }

                java.util.Date currentDate = new java.util.Date();
                java.sql.Date todaysDate = new java.sql.Date(currentDate.getTime());

                pstmt = conn.prepareStatement("UPDATE UM_user_check SET password = ?, LAST_CHANGED = ? where user_id =?");
                pstmt.setString(1, newPassword_1);
                pstmt.setDate(2, todaysDate);
                pstmt.setString(3, login);
                pstmt.executeUpdate();
                conn.commit();
                pstmt.close();


                message = "Password has been Changed Successfully";
                ps.println(object_pageTemplate.tableHeader4(message, width, height));

//                ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 height = 95% width=90%>");
//                ps.println("<tr>");
//                ps.println("<td valign = middle>");
//                ps.println("<table cellspacing=0 cellpadding=10 border=1 width=100% bordercolor=#D7D7D7>");
//                ps.println("<tr bgcolor = #003399>");
//                ps.println("<td align = left>");
//                ps.println("<font color = #FFFFFF><b>");
//                ps.println("PASSWORD HAS BEEN CHANGED SUCCESSFULLY.<br>");
//                ps.println("YOUR PASSWORD WILL EXPIRE IN 45 DAYS.<br>");
//                ps.println("PLEASE CHANGE YOUR PASSWORD IN 45 DAYS.<br>");
//                ps.println("<a href=Index target=\"_top\"><font color = #FFFFFF><b>CLICK HERE TO LOGIN AGAIN</b></font>");
//                ps.println("</td>");
//                ps.println("</tr>");
//                ps.println("</table>");
//                ps.println("</td>");
//                ps.println("</tr>");
//                ps.println("</table>");

                session.removeValue("user_type");
                session.invalidate();

                ps.println("</body>");
                ps.println("</html>");

            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ps.close();
        }
    }
}
