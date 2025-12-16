/*
File Name: InfoFrame.java
PURPOSE: To divide the page for user profile in two frames
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA		v3.4		Shivani Chauhan			$$0 Created
 */
package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author shivani.chauhan
 */
public class InfoFrame extends HttpServlet
{

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     */
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        PrintStream wps = new PrintStream(response.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        response.setContentType("text/html");
        try
        {
            ps.println("<html>");
            ps.println("<head>");

            ps.println("			<script language=JavaScript type=text/JavaScript>");
            ps.println("			function onTop()");
            ps.println("			{");
            ps.println("				self.focus();");
            //ps.println("				window.moveTo(0,0);");
            ps.println("			}");
            ps.println("			</script>");
            ps.println("</head>");
            //ps.println("<body  leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 background = \" + imagesURL + \"/backBlue.gif>");

           // ps.println("		<frameset rows=\"112,*\" frameborder = 0 framespacing = 0>");//rows="25%,*,25%">
            ps.println("		<frameset rows=\"102,*\" frameborder = 0 framespacing = 0>");//rows="25%,*,25%">

            ps.println("			<frame  name= right_middle src=UserInfo_1  noresize=\"noresize\">");
            ps.println("			<frame  name= right_main src=UserProfile  noresize=\"noresize\">");
            ps.println("		</frameset>");

            ps.println("<body onLoad = onTop()>");
            ps.println("</body>");
            ps.println("</html>");
        }
        finally
        {
            ps.close();
        }
    }
}
