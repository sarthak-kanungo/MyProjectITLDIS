package viewEcat.comEcat;

/*
File Name: FrameOpener.java
PURPOSE: It is used to show group details and group options in two frames.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */

//import viewEcat.comEcat.ConnectionHolder;
//import viewEcat.comEcat.PageTemplate;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrameOpener extends HttpServlet
{

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        try
        {
    
           ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            String group = req.getParameter("group");
            String model = req.getParameter("model");
            String part = req.getParameter("partSearch");
            String engineSeries = req.getParameter("engineSeries");
                String engineModel = req.getParameter("engineModel");
                String aggregate = req.getParameter("aggregate");
                String applicationType = req.getParameter("applicationType");

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

            ps.println("		<frameset rows=\"64,*\" frameborder = 0 framespacing = 0>");
            ps.println("			<frame name= right_middle src=\"GroupOptions?aggregate="+aggregate+"&applicationType="+applicationType+"&engineModel=" + engineModel+ "&engineSeries=" + engineSeries + "&group=" + group + "&model=" + model + "\" noresize scrolling=no>");
            ps.println("			<frame name= right_main src=\"Group_details?aggregate="+aggregate+"&applicationType="+applicationType+"&engineModel=" + engineModel+ "&engineSeries=" + engineSeries + "&group=" + group + "&model=" + model + "&partSearch=" + part + "\" noresize>");
            ps.println("		</frameset>");

            ps.println("<body onLoad = onTop()>");
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
