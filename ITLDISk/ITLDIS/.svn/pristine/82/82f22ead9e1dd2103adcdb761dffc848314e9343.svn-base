package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
File Name: ChangeMainMessage1.java
PURPOSE: Change Main Message Into Database.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */

import authEcat.UtilityMapkeys1;


public class ChangeMainMessage1 extends HttpServlet
{

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        try
        {
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

            if (session_id.equals(getSession))
            {
                Connection conn = holder.getConnection();
                //Statement stmt;
                PreparedStatement stmt = null;
                //stmt = conn.createStatement();

                for (int i = 1; i <= 5; i++)
                {
                    String text = req.getParameter("text_" + i);
                    if (text != null)
                    {
                        text = text.replaceAll("\'", "\\\\''");
                        text = text.trim();
                        String clr = req.getParameter("clr_" + i);
                        //stmt.executeUpdate("update GEN_MAIN_MESSAGE set msg_color='" + clr + "', msg ='" + text + "' where msg_no=" + i + "");
                        String updateQuery = ("update GEN_MAIN_MESSAGE set msg_color='" + clr + "', msg ='" + text + "' where msg_no=" + i + "");
                        stmt = conn.prepareStatement(updateQuery);
                        stmt.executeUpdate();
                    }
                }

                conn.commit();
                stmt.close();

                ps.println("<HTML>");
                ps.println("<HEAD>");
                ps.println("<TITLE>"+UtilityMapkeys1.tile1+"</TITLE>");
                ps.println("</HEAD>");
                ps.println("<BODY >");

                ps.println("<br>");
                ps.println("<br>");
                ps.println("<br>");
                ps.println("<br>");

                ps.println("<center><table width=70% border=1 cellspacing=1 cellpadding=5 bordercolor=#CCCCCC align=center>");
                ps.println("<tr bgcolor=#003399>");
                ps.println("<td align=center>");
                ps.println("<font face=Helvetica size=1 color=#ffffff>");
                ps.println("<b>SUCCESSFULLY CHANGED THE MAIN MESSAGE.");
                ps.println("</b></font>");
                ps.println("</td>");
                ps.println("</tr>");
                ps.println("<tr>");
                ps.println("<td valign = middle align = right colspan=1>");

                ps.println("<a href ='View_dealers_1a'>");
                ps.println("<font color=#000099><b>");
                ps.println("<< BACK");
                ps.println("</font></a>");

                ps.println("</td>");
                ps.println("</tr>");
                ps.println("</table>");

                ps.println("</body>");
                ps.println("</html>");
            }
            else
            {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ps.close();
        }
    }
}
