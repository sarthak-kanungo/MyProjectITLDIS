/*
File Name: UserInfo_1.java
PURPOSE: To show the links to view and edit Personal info, view message history and change password.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA		v3.4		Shivani Chauhan			$$0 Created
 */
package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Vector;

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
public class UserInfo_1 extends HttpServlet
{

    protected void service(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException
    {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter ps = response.getWriter();
        try
        {

            HttpSession session = request.getSession(true);
            String session_id = session.getId();


            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String getSession = (String) session.getValue("session_value");
            String mainURL = (String) session.getValue("mainURL");

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");

            Connection conn = holder.getConnection();

            String imagesURL = object_pageTemplate.imagesURL();
            String loginAgain = object_pageTemplate.loginAgain();
            String orderRefNoString = object_pageTemplate.orderRefNoString();

            Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));

            int width = 759;
            int height = 84;
            //width="759" height="84"

            ps.println("<html>");
            ps.println("<head>");
            ps.println("<title>" + UtilityMapkeys1.tile1 + " - E-CATALOGUE</title>");
            ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
            ps.println("</head>");

            ps.println("<body  leftmargin=0 topmargin=0 marginwidth=0 marginheight=0>");

            if (session_id.equals(getSession))
            {

                ps.println(object_pageTemplate.tableHeader3("User Profile", width, height));
                ps.println("                 <table width=90% border=0 height=40 align=\"center\" cellspacing=1 cellpadding=2 bordercolor = #CCCCCC>");
                ps.println("                            <tbody>");
                ps.println("                                <tr valign=top>                       ");
                ps.println("              ");

                //ps.println("<tr>");
                ps.println("<td valign=middle><div align=center><img src = " + imagesURL + "personal-info.gif></div></td>");
                ps.println("<td valign=middle class=\"links_txt\">");
                ps.println("<a href = 'UserProfile' target = right_main >");
                ps.println("Personal Information");
                ps.println("</a>");
                ps.println("</td>");


                ps.println("<td valign=middle><div align=center><img src = " + imagesURL + "edit-personal-info.gif></div></td>");
                ps.println("<td valign=middle class=\"links_txt\">");

                if (orderRefNoString.equals("WEB") && userFunctionalities.contains("10"))
                {
                    ps.println("<a href = 'ChangeInfo' target = right_main >");
                    ps.println("Edit Personal Info");
                    ps.println("</a>");
                } else
                {
                    ps.println("Edit Personal Info");
                }
                ps.println("</td>");


                if (userFunctionalities.contains("9"))
                {
                    ps.println("<td valign=middle><div align=center><img src = " + imagesURL + "message-history.gif></div></td>");
                    ps.println("<td valign=middle class=\"links_txt\">");
                    ps.println("<a href = 'Message_history' target = right_main >");
                    ps.println("Message History");
                    ps.println("</a>");
                    ps.println("</td>");
                }

                ps.println("<td valign=middle><div align=center><img src = " + imagesURL + "change-pass.gif></div></td>");

                if (orderRefNoString.equals("WEB") && userFunctionalities.contains("11"))
                {
                    ps.println("<td valign=middle class=\"links_txt\">");
                    ps.println("<a href = 'ChangePassword_1?id=\"+userCode+\"' target = right_main >");
                    ps.println("Change Password");
                    ps.println("</a>");
                    ps.println("</td>");
                } else
                {
                    ps.println("<td valign=middle class=\"links_txt\">");
                    ps.println("Change Password");
                    ps.println("</td>");
                }

                ps.println("</tr>");

                ps.println("                            </tbody>");
                ps.println("                        </table>");
//Footer

                ps.println(object_pageTemplate.tableFooter());

                ps.println("</body>");
                ps.println("</html>");
            } else
            {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
        } finally
        {
            ps.close();
        }
    }
}
