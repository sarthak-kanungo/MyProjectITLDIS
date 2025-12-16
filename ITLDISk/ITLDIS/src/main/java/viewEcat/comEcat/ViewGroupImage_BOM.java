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
File Name: ViewGroupImage_BOM.java
PURPOSE: It divides the window to show the group image and BOM into two frames.
HISTORY:
DATE		BUILD	 AUTHOR				MODIFICATIONS
NA			v3.4	 Deepak Mangal		$$0 Created
 */
import authEcat.UtilityMapkeys1;

public class ViewGroupImage_BOM extends HttpServlet {

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
            //String getType = (String) session.getValue("user_type");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            //ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            //String userCode = (String) session.getValue("userCode");
            ////////////////////////////////////////////////////////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();
            String imagesURL = object_pageTemplate.imagesURL();

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            String group = req.getParameter("group");
            String model = req.getParameter("model");

            String engineSeries = req.getParameter("engineSeries");
            String engineModel = req.getParameter("engineModel");
            String aggregate = req.getParameter("aggregate");

            String partSearch = req.getParameter("partSearch");
            ps.println("<html>");
            ps.println("<head>");
            ps.println("      <title>");
            ps.println("         " + UtilityMapkeys1.tile1 + "");
            ps.println("      </title>");

            if (session_id.equals(getSession)) {

                ps.println("		<frameset  rows=\"50,*\"  frameborder = 0 framespacing=\"0\">");//20 px,

                ps.println("			<frame  name=\"top\" src=\"viewGroupImageTopHeading?aggregate=" + aggregate + "&engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&group=" + group + "&model=" + model + "&partSearch=" + partSearch + "\"  noresize=\"noresize\" srolling=\"no\">");
                ps.println("		<frameset name=\"middle\" cols=\"462,*\" frameborder = 0>");//20 px,

                ps.println("			<frame name=right_middle src=\"ViewGroupImages?aggregate=" + aggregate + "&engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&group=" + group + "\"  noresize=\"noresize\" scrolling=no>");
                ps.println("			<frame name=right_main src=\"Group_details_only?aggregate=" + aggregate + "&engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&group=" + group + "&model=" + model + "&partSearch=" + partSearch + "\"   noresize=\"noresize\">");
                ps.println("		</frameset>");
                ps.println("		</frameset>");

                ps.println("<script language=javascript>");
                ps.println("			function onTop()");
                ps.println("			{");
                ps.println("				self.focus();");
                ps.println("				window.moveTo(0,0);");
                ps.println("			}");
                ps.println("</script>");
                ps.println("</head>");

                ps.println("<body onload=onTop()>");

                ps.println("</body>");
                ps.println("</html>");

            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE", ComUtils.getLanguageTranslation("label.catalogue.sessionExpired", session), "YES", ComUtils.getLanguageTranslation("label.catalogue.loginAgain", session), "Index", "", imagesURL);
            }
        } catch (Exception e) {
            ps.println(e);
        } finally {
            ps.close();
            wps.close();
        }
    }
}
