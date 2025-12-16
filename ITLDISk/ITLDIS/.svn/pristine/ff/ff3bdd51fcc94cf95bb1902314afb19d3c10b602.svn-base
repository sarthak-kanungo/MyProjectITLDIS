package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
File Name: ReportOptions.java
PURPOSE: It is used to various Search Report options available.
HISTORY:
DATE		BUILD	AUTHOR			MODIFICATIONS
NA			v3.4	Deepak Mangal	$$0 Created
 */
import authEcat.UtilityMapkeys1;

public class ReportOptions extends HttpServlet
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

            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();


            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////
            if (session_id.equals(getSession))
            {
                Connection conn = holder.getConnection();
                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));
                
                

                /*########################################*/

                ps.println("<html><head>");
                ps.println("      <title>");
                ps.println("         "+UtilityMapkeys1.tile1+"");
                ps.println("      </title>");
                ps.println("			<style type=text/css>");
                ps.println("			<!--");
                ps.println("			* { font-family: Helvetica; font-size: 10pt;}");
                ps.println("			a:link {				color: #000000;				text-decoration: none; }");
                ps.println("			a:visited { color: #000000;				text-decoration: none;		  }	");
                ps.println("			-->");
                ps.println("			</style>");

                ps.println("<body topmargin = 0 >");
                ps.println("<center>");
                ps.println("<br>");
                ps.println("<center><table border=0 cellpadding=5 cellspacing=5 bordercolor=#CCCCCC width = 95%>");

                if (userFunctionalities.contains("200"))
                {
                    ps.println("<tr bgcolor=#003399>");
                    ps.println("<td align=left colspan = 2>");
                    ps.println("<strong>");
                    ps.println("<img src = " + imagesURL + "red.gif width=11 height=11>");
                    ps.println("<font color=#FFFFFF> ");
                    ps.println("SEARCH REPORTS AVAILABLE");
                    ps.println("</font>");
                    ps.println("</strong>");
                    ps.println("</div>");
                    ps.println("</td>");
                    ps.println("</tr>");

                    ps.println("  <tr> ");
                    ps.println("		<td width = 5%> ");
                    ps.println("			<img src = " + imagesURL + "green.gif width=11 height=11>");
                    ps.println("		</td>");
                    ps.println("		<td align = left>");
                    ps.println("			<a href=" + servletURL + "SonoOrSerial>");
                    ps.println("				<font size=2 face=Helvetica color=#000000><b>");
                    ps.println("					MODEL vs TRACTOR NO.");
                    ps.println("				</font>");
                    ps.println("</a>");
                    ps.println("					&nbsp;&nbsp;&nbsp;<img src = " + imagesURL + "hlp.gif border = 0 ALT = 'This lists the Model for a Particular Engine NO and Vice Versa.'>");
                    ps.println("		</td>");
                    ps.println("  </tr>");
                }

                ps.println("<tr bgcolor=#003399>");
                ps.println("<td align=left colspan = 2>");
                ps.println("<strong>");
                ps.println("<img src = " + imagesURL + "red.gif width=11 height=11>");
                ps.println("<font color=#FFFFFF> ");
                ps.println("SEARCH REPORTS AVAILABLE (CHANGE MANAGEMENT)");
                ps.println("</font>");
                ps.println("</strong>");
                ps.println("</div>");
                ps.println("</td>");
                ps.println("</tr>");

                /* if (userFunctionalities.contains("201"))
                {
                ps.println("  <tr> ");
                ps.println("		<td width = 5%> ");
                ps.println("			<img src = " + imagesURL + "green.gif width=11 height=11>");
                ps.println("		</td>");
                ps.println("		<td align = left>");
                ps.println("			<a href=" + servletURL + "Search_between_1?type=revision>");
                ps.println("				<font size=2 face=Helvetica color=#000000><b>");
                ps.println("					VIEW CHANGES DONE BETWEEN REVISIONS");
                ps.println("				</font>");
                ps.println("</a>");
                ps.println("					&nbsp;&nbsp;&nbsp;<img src = " + imagesURL + "hlp.gif border = 0 ALT = 'This lists the changes that have been done in a Sub-Assembly or a Group between two given revisions e.g. 5 TO 10.'>");
                ps.println("		</td>");
                ps.println("  </tr>");

                }
                 */
                if (userFunctionalities.contains("202"))
                {
                    ps.println("  <tr> ");
                    ps.println("		<td width = 5%> ");
                    ps.println("			<img src = " + imagesURL + "green.gif width=11 height=11>");
                    ps.println("		</td>");
                    ps.println("		<td align = left>");
                    //ps.println("			<a href=" + servletURL + "Search_between_1?type=date>");
                    ps.println("			<a href=" + servletURL + "EcnChangesBtnDates_1>");
                    ps.println("				<font size=2 face=Helvetica color=#000000><b>");
                    ps.println("					VIEW CHANGES DONE BETWEEN DATES");
                    ps.println("				</font>");
                    ps.println("</a>");
                    ps.println("					&nbsp;&nbsp;&nbsp;<img src = " + imagesURL + "hlp.gif border = 0 ALT = 'This lists the changes that have been done in a Sub-Assembly or a Group between two given dates e.g. 01/01/04 TO 01/06/04.'>");
                    ps.println("		</td>");
                    ps.println("  </tr>");
                }
                if (userFunctionalities.contains("203"))
                {
                    ps.println("  <tr> ");
                    ps.println("		<td width = 5%> ");
                    ps.println("			<img src = " + imagesURL + "green.gif width=11 height=11>");
                    ps.println("		</td>");
                    ps.println("		<td align = left>");
                    //ps.println("			<a href=" + servletURL + "A_D_R?criteria=ADDED>");
                    ps.println("			<a href=" + servletURL + "EcnReport?criteria=ADDED>");
                    ps.println("				<font size=2 face=Helvetica color=#000000><b>");
                    ps.println("					NEW PARTS ATTACHED TO A GROUP/ASSEMBLY REPORT");
                    ps.println("				</font>");
                    ps.println("</a>");
                    ps.println("					&nbsp;&nbsp;&nbsp;<img src = " + imagesURL + "hlp.gif border = 0 ALT = 'This lists the components that have been added in the Sub-Assembly or a Group through some modification.'>");
                    ps.println("		</td>");
                    ps.println("  </tr>");
                }
                if (userFunctionalities.contains("204"))
                {
                    ps.println("  <tr> ");
                    ps.println("		<td width = 5%> ");
                    ps.println("			<img src = " + imagesURL + "green.gif width=11 height=11>");
                    ps.println("		</td>");
                    ps.println("		<td align = left>");
                    ps.println("			<a href=" + servletURL + "EcnReport?criteria=DELETED>");
                    ps.println("				<font size=2 face=Helvetica color=#000000><b>");
                    ps.println("					PARTS DE - ATTACHED FROM A GROUP/ASSEMBLY REPORT");
                    ps.println("				</font>");
                    ps.println("</a>");
                    ps.println("					&nbsp;&nbsp;&nbsp;<img src = " + imagesURL + "hlp.gif border = 0 ALT = 'This lists the components that have been deleted from the Sub-Assembly or a Group through some modification.'>");
                    ps.println("		</td>");
                    ps.println("  </tr>");
                }
                if (userFunctionalities.contains("205"))
                {
                    ps.println("  <tr> ");
                    ps.println("		<td width = 5%> ");
                    ps.println("			<img src = " + imagesURL + "green.gif width=11 height=11>");
                    ps.println("		</td>");
                    ps.println("		<td align = left>");
                    ps.println("			<a href=" + servletURL + "EcnReport?criteria=REPLACED>");
                    ps.println("				<font size=2 face=Helvetica color=#000000><b>");
                    ps.println("					OLD TO NEW PART RELATION REPORT");
                    ps.println("				</font>");
                    ps.println("</a>");
                    ps.println("					&nbsp;&nbsp;&nbsp;<img src = " + imagesURL + "hlp.gif border = 0 ALT = 'This lists the components that have been replaced by new components in a Sub-Assembly or a Group.'>");
                    ps.println("		</td>");
                    ps.println("  </tr>");
                }
                ps.println("  <tr> ");
                ps.println("		<td align = center colspan = 2>");
                ps.println("				<font size=1 face=Helvetica color=#000000>");
                ps.println("			<br>");
                ps.println("			<br>");
                ps.println("			<br>");
                ps.println("					** Move your mouse over <img src = " + imagesURL + "hlp.gif> to get help on particular option.");
                ps.println("				</font>");
                ps.println("		</td>");
                ps.println("  </tr>");

                ps.println("  </table>");

            }
            else
            {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
            ps.println("</body></html>");
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
