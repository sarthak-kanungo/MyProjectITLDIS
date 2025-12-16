package viewEcat.comEcat;

/*
File Name: compAvailable.java
PURPOSE: It shows the list view of groups in a particular model.
HISTORY:
DATE		BUILD	 AUTHOR				MODIFICATIONS
NA			v3.4	 Deepak Mangal		$$0 Created
 */
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AllCompAvailable extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        ArrayList<String> compAvailable = null;
        String compTypeHeading = "";
        String message = null;
        try {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");


            ///////////////////////////// CREATE SESSION /////////////////////////////

            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();


            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////


            if (session_id.equals(getSession)) {
                Connection conn = holder.getConnection();

                //Statement stmt;
                PreparedStatement pstmt = null;
                ResultSet rs;
                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                /*########################################*/

                //stmt = conn.createStatement();

                String compType = req.getParameter("compType");

                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
                ps.println("<script language=JavaScript type=text/JavaScript>");
                ps.println("			function onTop()");
                ps.println("			{");
                ps.println("				self.focus();");
                ps.println("			}");
                ps.println("		function MM_openBrWindow(theURL,winName,features)");
                ps.println("		{ ");
                ps.println("			var isValidPopUpBlocker=detectPopupBlocker()");
                ps.println("			if (isValidPopUpBlocker== false)");
                ps.println("			{");
                ps.println("				return");
                ps.println("			}");
                ps.println("			window.open(theURL,winName,features);");
                ps.println("		}");
                ps.println("function detectPopupBlocker()");
                ps.println("{ ");
                ps.println("	var myTest = window.open(\"about:blank\",\"\",\"directories=no,height=100,width=100,menubar=no,resizable=no,scrollbars=no,status=no,titlebar=no,top=0,location=no\");");
                ps.println("	if (!myTest)");
                ps.println("	{");
                ps.println("		alert(\"Your Pop-up Blocker is Enabled. Please Add our site http://" + server_name + " to your trusted sites.\");");
                ps.println("		parent.parent.location.href = 'Logout'");
                ps.println("		return false;");
                ps.println("	} ");
                ps.println("	else ");
                ps.println("	{");
                ps.println("		myTest.close();");
                ps.println("		return true;");
                ps.println("	}");
                ps.println("}");
                ps.println("</script>");


                ps.println("<body topmargin=0  marginheight=0 leftmargin=0  marginwidth=0>");

                compAvailable = new ArrayList<String>();
                String heading = "";
                String toolNumber = "";

                //rs = stmt.executeQuery("SELECT part_no,p1,np4,p3 from CAT_PART where part_type='" + compType + "' order by np4,part_no");
                String sql = ("SELECT part_no,p1,np4,p3 from CAT_PART(NOLOCK) where part_type='" + compType + "' order by np4,part_no");
                pstmt = conn.prepareStatement(sql);
                rs = pstmt.executeQuery();

                while (rs.next()) {
                    compAvailable.add(rs.getString(1));
                    compAvailable.add(rs.getString(2));
                    heading = rs.getString(3);
                    if (heading == null || heading.equals("")) {
                        heading = "";
                    }
                    compAvailable.add(heading);
                    toolNumber = rs.getString(4);
                    if (toolNumber == null || toolNumber.equals("")) {
                        toolNumber = "--";
                    }
                    compAvailable.add(toolNumber);
                }
                rs.close();
                //stmt.close();
                pstmt.close();

                int width = 970, height = 450;

                if (compAvailable.size() == 0) {
                    message = "No  " + compType + " Exist";
                    ps.println(object_pageTemplate.tableHeader1(message, width, height));
                    //object_pageTemplate.ShowMsg(ps, "FAILURE", "No  " + compType + " Exists.", "NO", "", "", "", imagesURL);
                    return;
                }


                ps.println(object_pageTemplate.tableHeader(compType + "s Available", width, height));



                //ps.println(" <table width=100% border=0 cellspacing=0 cellpadding=0 bordercolor = #CCCCCC><tr><td>");
                ps.println(" <table width=\"880px;\" border=0 cellspacing=0 cellpadding=0 bordercolor = #CCCCCC>");
                ps.println(" <tr><td><table width=\"880px;\" border=0 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC>");

                ps.println("<tr bgcolor = #003399 class=heading1> ");
                ps.println("<td width = 10% align = center>");
                ps.println(ComUtils.getLanguageTranslation("label.catalogue.sNo", session).toUpperCase());
                ps.println("</td>");

                ps.println("<td width = 13% class=heading1>");
                ps.println(compType.toUpperCase());
                ps.println("</td>");


                if (compType.equalsIgnoreCase("Tool")) {
                    ps.println("<td width = 50% class=heading1>");
                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.description", session).toUpperCase());
                    ps.println("</td>");
                    ps.println("<td width = 27% class=heading1>");
                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.toolNo", session).toUpperCase());
                    ps.println("</td>");
                } else {
                    ps.println("<td width = 77% class=heading1>");
                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.description", session).toUpperCase());
                    ps.println("</td>");
                }
                ps.println("</tr>");

                ps.println("</table>");
                //ps.println("</td></tr><tr><td>");

                ps.println(" </td></tr><tr><td ><div valign=top STYLE=\" overflow-X:hidden; overflow-y: scroll; height:440;\">");

                ps.println("                 <table width=\"880px;\"  border=0 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC>");
                ps.println("              ");

                int counter = 1;

                int l = 0;
                String cssClass = null;

                for (int c = 0; c < compAvailable.size(); c += 4) {



                    if (l % 2 == 0) {
                        cssClass = "links_txt";//links_txt_Alt
                    } else {
                        cssClass = "links_txt";
                    }
                    l++;


                    if ((!"".equals(compAvailable.get(c + 2))) && (counter == 1 || !compAvailable.get(c + 2).equals(compTypeHeading))) {
                        compTypeHeading = compAvailable.get(c + 2);
                        ps.println("<tr><td  colspan=3 align=left  class=links_txt><b>" + compTypeHeading.toUpperCase() + "</td></tr>");
                    }

                    ps.println("<tr>");
                    ps.println("<td  width=10% align = center   class=" + cssClass + ">");
                    ps.println(counter);
                    ps.println("</td>");

                    ps.println("<td  width=13% class=" + cssClass + ">");
                    ps.println("<a href=javascript:MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + compAvailable.get(c) + "','DETAILS','scrollbars=yes,width=700,height=605')>");
                    ps.println(compAvailable.get(c));
                    ps.println("</a>");
                    ps.println("</td>");


                    if (compType.equalsIgnoreCase("Tool")) {
                        ps.println("<td  width=50% class=" + cssClass + ">");
                        ps.println("<a href=javascript:MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + compAvailable.get(c) + "','DETAILS','scrollbars=yes,width=700,height=605')>");
                        ps.println(compAvailable.get(c + 1));
                        ps.println("</a>");
                        ps.println("</td>");
                        ps.println("<td  width=27% class=" + cssClass + " align=left>");
                        ps.println("<a href=javascript:MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + compAvailable.get(c) + "','DETAILS','scrollbars=yes,width=700,height=605')>");
                        ps.println(compAvailable.get(c + 3));
                        ps.println("</a>");
                        ps.println("</td>");
                    } else {
                        ps.println("<td  width=77% class=" + cssClass + ">");
                        ps.println("<a href=javascript:MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + compAvailable.get(c) + "','DETAILS','scrollbars=yes,width=700,height=605')>");
                        ps.println(compAvailable.get(c + 1));
                        ps.println("</a>");
                        ps.println("</td>");
                    }
                    ps.println("</tr> ");

                    counter++;
                }


                ps.println("                        </table></div>");
                ps.println("</td></tr></table>");

                ps.println(object_pageTemplate.tableFooter());
            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", ComUtils.getLanguageTranslation("label.catalogue.loginAgain", session), "Index", "", imagesURL);

            }
        } catch (Exception e) {
            ps.println(e);
            e.printStackTrace();
        } finally {
            ps.close();
            wps.close();
        }
    }
}
