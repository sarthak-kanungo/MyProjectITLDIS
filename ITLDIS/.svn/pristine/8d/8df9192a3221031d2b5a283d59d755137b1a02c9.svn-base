package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
File Name: LocateUs.java
PURPOSE: Locate Us.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import authEcat.UtilityMapkeys1;

public class LocateUs extends HttpServlet
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
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String footer = "";
            footer = object_pageTemplate.footer();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ///////////////////////// CONNECTION CODE //////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            if (session_id.equals(getSession))
            {
                ps.println("<HTML>");
                ps.println("<HEAD>");
                ps.println("<TITLE>"+UtilityMapkeys1.tile1+"</TITLE>");
                ps.println("			<style type=text/css>");
                ps.println("			<!--");
                ps.println("			body");
                ps.println("			* { font-family: Helvetica; font-size: 10pt; color:#003399;}");
                ps.println("			a:link {				color: #990000;				text-decoration: none; }");
                ps.println("			a:visited { color: #990000;				text-decoration: none;		  }	");
                ps.println("			-->");
                ps.println("			</style>");
                ps.println("</HEAD>");
                ps.println("<BODY >");
                ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 height = 95% width=50%>");
                ps.println("<tr>");
                ps.println("<td valign = middle>");
                ps.println("<table cellspacing=0 cellpadding=10 border=1 width=100% bordercolor=#D7D7D7>");
                ps.println("<tr >");
                ps.println("<td align = center>");
                ps.println("<font color = #003399 size = 8><b>");
                ps.println("    "+UtilityMapkeys1.custTitle+" Pvt. Ltd.</b> <br>");
                ps.println(UtilityMapkeys1.companyAddress());
                
            //    ps.println("    E-Mail: <a href='mailto:ecatadminit@newhollandindia.co.in'>ecatadminit@newhollandindia.co.in</a>");
                ps.println("</td>");
                ps.println("</tr>");
                ps.println("</table>");
                ps.println("</td>");
                ps.println("</tr>");
                ps.println("</table>");
            }
            else
            {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
            ps.println(footer);
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
