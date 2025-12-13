/*
File Name: UserProfile.java
PURPOSE: To show Personal info of user.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA		v3.4		Shivani Chauhan			$$0 Created
 */
package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import authEcat.UtilityMapkeys1;

/**
 *
 * @author shivani.chauhan
 */
public class UserProfile extends HttpServlet {

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter ps = response.getWriter();
        Connection conn_cat = null;
        try {

            HttpSession session = request.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String userCode = (String) session.getValue("userCode");

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();


            PreparedStatement pstmt;
            ResultSet rs;
            ps.println("<html>");
            ps.println("<head>");
            ps.println("<title>" + UtilityMapkeys1.tile1 + "</title>");
            ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
            ps.println("<link href=\"" + imagesURL + "css/config.css\" type=\"text/css\" rel=\"stylesheet\">");
            ps.println("</head>");


            ps.println("<body  leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 >");


            ps.println(" <table width=\"100%\" align=\"center\">");
            ps.println(" <tr><td>");

            if (session_id.equals(getSession)) {

                conn_cat = new dbConnection.dbConnection().getDbConnection();
                String dealerName = "";
                String dealerAddress1 = "";
                String dealerAddress2 = "";
                String dealerCity = "";
                String dealerState = "";
                String dealerContact = "";
                String dealerMail1 = "";
                String dealerMail2 = "";
                String dealerMobile = "";
                String dealerCountry = "";
                String dealerPin = "";
                String dealerPhone = "";

                pstmt = conn_cat.prepareStatement("Select * from UM_user_check(NOLOCK) where user_id=?");
                pstmt.setString(1, userCode);
                rs = pstmt.executeQuery();

                if (rs.next()) {
                    dealerName = rs.getString(6);
                    dealerAddress1 = rs.getString(7);
                    dealerAddress2 = rs.getString(8);
                    dealerCity = rs.getString(9);
                    dealerState = rs.getString(10);
                    dealerContact = rs.getString(11);
                    dealerMail1 = rs.getString(12);
                    dealerMail2 = rs.getString(13);
                    dealerMobile = rs.getString(14);
                    dealerCountry = rs.getString(18);
                    dealerPin = rs.getString(19);
                    dealerPhone = rs.getString(21);

                }
                rs.close();
                pstmt.close();
                //conn_cat.close();


                if (dealerPhone == null) {
                    dealerPhone = "--";
                }

                if (dealerName == null) {
                    dealerName = "--";
                }
                if ((dealerAddress1 == null) || (dealerAddress1.equals("null")) || (dealerAddress1.equals(" ")) || (dealerAddress1.equals(""))) {
                    dealerAddress1 = "--";
                }
                if ((dealerAddress2 == null) || (dealerAddress2.equals("null")) || (dealerAddress2.equals(" ")) || (dealerAddress2.equals(""))) {
                    dealerAddress2 = "--";
                }
                if ((dealerCity == null) || (dealerCity.equals("null")) || (dealerCity.equals(" ")) || (dealerCity.equals(""))) {
                    dealerCity = "--";
                }
                if ((dealerState == null) || (dealerState.equals("null")) || (dealerState.equals(" ")) || (dealerState.equals(""))) {
                    dealerState = "--";
                }
                if ((dealerContact == null) || (dealerContact.equals("null")) || (dealerContact.equals(" ")) || (dealerContact.equals(""))) {
                    dealerContact = "--";
                }
                if ((dealerMail1 == null) || (dealerMail1.equals("null")) || (dealerMail1.equals(" ")) || (dealerMail1.equals(""))) {
                    dealerMail1 = "--";
                }
                if ((dealerMail2 == null) || (dealerMail2.equals("null")) || (dealerMail2.equals(" ")) || (dealerMail2.equals(""))) {
                    dealerMail2 = "--";
                }
                if ((dealerMobile == null) || (dealerMobile.equals("null")) || (dealerMobile.equals(" ")) || (dealerMobile.equals(""))) {
                    dealerMobile = "--";
                }
                if ((dealerCountry == null) || (dealerCountry.equals("null")) || (dealerCountry.equals(" ")) || (dealerCountry.equals(""))) {
                    dealerCountry = "--";
                }
                if ((dealerPin == null) || (dealerPin.equals("null")) || (dealerPin.equals(" ")) || (dealerPin.equals(""))) {
                    dealerPin = "--";
                }
                int width = 759;
                int height = 300;
                ps.println(object_pageTemplate.tableHeader3("User Personal Information", width, height));


                ps.println("                 <table width=90% border=0 valign=top height=300 align=\"center\" cellspacing=1 cellpadding=2 bordercolor = #CCCCCC>");
                ps.println("                            <tbody>");
                ps.println("                                <tr valign=top>                       ");
                ps.println("              ");

                //ps.println("<form name=form1>");
                //ps.println("    <tr >");
                ps.println("      <td  style=\"padding-top:15px;\" class=\"links_txt\">");
                ps.println("Dealer Code </td>");
                ps.println("      <td style=\"padding-top:15px;\" class=\"links_txt\">");
                ps.println(userCode);
                ps.println("</td>");
                ps.println("    </tr>");
                ps.println("    <tr><td colspan=\"2\" >");
                //ps.println(object_pageTemplate.mainHeading("Personal Details"));
                ps.println("<table width=\"100%\" border=\"0\" cellpadding=\"2\" cellspacing=\"2\"  bgcolor=\"#CCCCCC\">");
                ps.println(" <tr height=\"20\">");
                ps.println("  <td align=\"left\" class=\"blue\"><strong class=\"heading\">Personal Details</strong></td>");
                ps.println(" </tr>");
                ps.println(" </table>");
                ps.println(" </td></tr>");
                ps.println("    <tr >");
                ps.println("      <td class=\"links_txt\">");
                ps.println("User Name");
                ps.println("</td>");
                ps.println("      <td align = left class=\"links_txt\"> " + dealerName);
                ps.println("    </td>");
                ps.println("    </tr>");
                ps.println("    <tr >");
                ps.println("      <td class=\"links_txt\">Contact Person</td>");
                ps.println("      <td align = left class=\"links_txt\">" + dealerContact);
                ps.println("    </td>");
                ps.println("    </tr>");
                ps.println("    <tr >");
                ps.println("      <td  align = left class=\"links_txt\">Address1</td>");
                ps.println("      <td align=left class=\"links_txt\">" + dealerAddress1);
                ps.println("    </td>");
                ps.println("    </tr>");
                ps.println("    <tr >");
                ps.println("      <td  align = left class=\"links_txt\">Address2</td>");
                ps.println("      <td align=left class=\"links_txt\">" + dealerAddress2);
                ps.println("    </td>");
                ps.println("    </tr>");
                ps.println("    <tr >");
                ps.println("      <td  align = left class=\"links_txt\">City </td>");
                ps.println("      <td align=left class=\"links_txt\">" + dealerCity);
                ps.println("    </td>");
                ps.println("    </tr>");
                ps.println("    <tr >");
                ps.println("      <td  align = left class=\"links_txt\">State </td>");
                ps.println("      <td align=left class=\"links_txt\"t>" + dealerState);
                ps.println("    </td>");
                ps.println("    </tr>");
                ps.println("    <tr >");
                ps.println("      <td  align = left class=text>Country </td>");
                ps.println("      <td align=left class=\"links_txt\">" + dealerCountry);
                ps.println("    </td>");
                ps.println("    </tr>");
                ps.println("    <tr >");
                ps.println("      <td  align = left class=\"links_txt\">Pin Code </td>");
                ps.println("      <td align=left class=\"links_txt\">" + dealerPin);
                ps.println("    </td>");
                ps.println("    </tr>");

                ps.println("	<tr >");
                ps.println("		<td class=\"links_txt\">");
                ps.println("			");
                ps.println("				E-Mail 1");
                ps.println("		</td>");
                ps.println("		<td class=\"links_txt\">");
                ps.println(dealerMail1);
                ps.println("		</td>");
                ps.println("	</tr>");
                ps.println("	<tr >");
                ps.println("		<td class=\"links_txt\">");
                ps.println("			");
                ps.println("				E-Mail 2");
                ps.println("		</td>");
                ps.println("		<td class=\"links_txt\">");
                ps.println(dealerMail2);
                ps.println("		</td>");
                ps.println("	</tr>");
                ps.println("	<tr >");
                ps.println("		<td class=\"links_txt\">");
                ps.println("			");
                ps.println("				Mobile");
                ps.println("		</td>");
                ps.println("		<td class=\"links_txt\">");
                ps.println(dealerMobile);
                ps.println("		</td>");
                ps.println("	</tr>");

                ps.println("	<tr >");
                ps.println("		<td class=\"links_txt\">");
                ps.println("			");
                ps.println("				Phone");
                ps.println("		</td>");
                ps.println("		<td class=\"links_txt\">");
                ps.println(dealerPhone);
                ps.println("		</td>");
                ps.println("	</tr>");
                ps.println("                            </tbody>");
                ps.println("                        </table>");
//Footer

                ps.println(object_pageTemplate.tableFooter());


                ps.println("</body>");
                ps.println("</html>");
            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
        } catch (Exception se) {
            se.printStackTrace();
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }

                if (conn_cat != null) {
                    conn_cat.close();
                }

            } catch (Exception e) {
            }

        }

    }
}
