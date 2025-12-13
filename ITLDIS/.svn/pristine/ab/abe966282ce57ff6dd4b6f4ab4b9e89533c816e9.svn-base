package viewEcat.comEcat;

/*
File Name: Logout.java
PURPOSE: LOGOUT FILE.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Logout extends HttpServlet
{
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");

        res.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
        res.setHeader("Expires", "0");
        res.setHeader("Pragma", "no-cache");

        try
        {
            HttpSession session = req.getSession(true);

            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();

            String orderRefNoString = object_pageTemplate.orderRefNoString();

            if (holder != null)
            {
                Connection conn = holder.getConnection();
               
                conn.rollback();
                conn.close();
            }

            session.removeValue("user_type");
            session.removeValue("userFun");
            session.invalidate();

            ps.println("<html>");
            ps.println("   <head>");
            ps.println("   <title>LOGGED OUT</title>");
            ps.println("			<style type=text/css>");
            ps.println("			<!--");
            ps.println("			* { font-family: Helvetica; }");
            ps.println("			a:link {				color: #0000FF;				text-decoration: none; }");
            ps.println("			a:visited { color: #0000FF;				text-decoration: none;		  }	");
            ps.println("			-->");
            ps.println("			</style>");
            ps.println("</HEAD>");

            ps.println("<body  topmargin = 0 margin height = 0>");

            ps.println("<center><table cellspacing=0 cellpadding=0 border=0 height = 100% width=90%");
            ps.println("<tr><td>&nbsp;</td></tr>");
            ps.println("<tr bgcolor = #AAC8E2>");
            ps.println("<td colspan=2 align = center>");
            ps.println("<b><FONT COLOR=#000000>");
            ps.println("YOU HAVE LOGGED OUT. PLEASE ALLOW POP-UPS TO VIEW THIS WEBSITE.");
            ps.println("</FONT></td>");
            ps.println("</tr>");
            ps.println("<tr><td><a name=\"top\">&nbsp;</td></tr>");
            ps.println("<tr><td>&nbsp;</td></tr>");
            ps.println("<tr>");
            ps.println("<td valign = middle>");
            ps.println("It is recommended that you do the following settings in your system to get the best view of this website.<br>");
            ps.println("1. <a href=\"#ccc\">Add our site into your trusted sites.</a><br>");
            ps.println("2. <a href=\"#bbb\">Disable Pop-up Blockers.</a><br>");
            ps.println("<br><br>");
            ps.println("<table cellspacing=0 cellpadding=10 border=1 width=100% bordercolor=#D7D7D7>");
            ps.println("<tr bgcolor = #AAC8E2>");
            ps.println("<td colspan=2 align = center><a name=\"ccc\"><font size=5>How to add our site http://" + server_name + " in your trusted sites?</font></td></tr>");
            ps.println("<tr>");
            if (orderRefNoString.equals("WEB"))
            {
                ps.println("<td align = center><img src='" + mainURL + "dealer/images/trusted_site1.jpg'></td>");
            }
            else
            {
                ps.println("<td align = center><img src='" + mainURL + "dealer/images/trusted_site2.jpg'></td>");
            }
            ps.println("<td><font size=5>How to include a site in trusted sites?</font><br><br><div align=left>To include a site in Trusted sites in Internet Explorer,<br>>> Go to Tools<br>>> Internet Options<br>>> Security Tab<br>>> Select Trusted Sites<br>>> Click Sites Button<br>>> Write <b>http://" + server_name + "</b> in text box<br>>> Click add Button<br>>> Remove check from \"Require server verification\"<br>>> Click OK.</div></td></tr>");
            ps.println("<tr><td colspan=2><a href=\"#top\">Back To top</a></td></tr>");

            ps.println("</table>");

            ps.println("<br>");

            ps.println("<table cellspacing=0 cellpadding=10 border=1 width=100% bordercolor=#D7D7D7>");
            ps.println("<tr bgcolor = #AAC8E2>");
            ps.println("<td colspan=2 align = center><a name=\"bbb\"><font size=5>Disabling Internet Explorer's Pop-up Blocker</font></td></tr>");
            ps.println("<tr>");
            ps.println("<td align = center><img src='" + mainURL + "dealer/images/popupblocker1.jpg'></td>");
            ps.println("<td><font size=5>How to Enable Pop-ups?</font><br><br><div align=left>To enable pop-ups in Internet Explorer, <br>>> Go to Tools<br>>> Pop-up Blocker<br>>> Turn Off Pop-up Blocker.</div></td></tr>");
            ps.println("<tr><td colspan=2><a href=\"#top\">Back To top</a></td></tr>");

            ps.println("<tr align=center><td colspan=2><font size=3>Now, you are ready for login. <a href='" + servletURL + "Index'>Click here to Login Again</a></font></td></tr>");
            ps.println("</table>");

            ps.println("</td>");
            ps.println("</tr>");
            ps.println("</table>");

            ps.println("</body>");
            ps.println("</html>");

        }
        catch (Exception e)
        {
            ps.println(e);
        }
        finally
        {
            ps.close();
            wps.close();
        }
    }
}
