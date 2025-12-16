/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author manish.kishore
 */
public class Group_Image_BOM_Center extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        try {


            HttpSession session = req.getSession(true);
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            //ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            //String userCode = (String) session.getValue("userCode");
            ////////////////////////////////////////////////////////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String imagesURL = object_pageTemplate.imagesURL();

            if (session_id.equals(getSession)) {


                ps.println("<html>");
                ps.println("    <head>");
                ps.println("        <script>");
                ps.println("            var isFrameResized='no';");
                ps.println("");
                ps.println("            cols=\"490 px,25 px,*\";");
                ps.println("            function resizeFrame(){               ");
                ps.println("                    //parent.document.all(\"middle\").rows=\"31,0,25,*\";");
                ps.println("                    parent.document.all(\"middle\").cols=\"0,25 px,*\";");
                ps.println("            }");
                ps.println("");
                ps.println("			 function resizeFrame1(){");
                ps.println("                ");
                ps.println("                    //parent.document.all(\"middle\").rows=\"31,237,25,*\";");
                ps.println("                    parent.document.all(\"middle\").cols=\"490 px,25 px,*\";");
                ps.println("                ");
                ps.println("            }");
                ps.println("			function resizeFrame2(){");
                ps.println("");
                ps.println("                    parent.document.all(\"middle\").cols=\"*,25 px,0\";");
                ps.println("                    isFrameResized='yes';                   ");
                ps.println("             ");
                ps.println("            }");
                ps.println("        </script>");
                ps.println("       ");
                ps.println("");
                ps.println("        ");
                ps.println("    </head>");
                ps.println("    <body  >");
                ps.println("<center>");
                ps.println("     ");
                ps.println("		<table style=\"width:20px;\"  border=0 valign=middle cellspacing=0 cellpadding=0>");
                ps.println("		<tr>");
                ps.println("            <td align=left  valign=middle>");
                ps.println("			<a href=\"#\" onclick=\"resizeFrame2();\">");
                ps.println("                    <img border=\"0\" alt=\"Resize\" id=\"resizeImg\" src=\""+imagesURL+"View_image.jpg\" />");
                ps.println("            </a>");
                ps.println("			</td>");
                ps.println("");
                ps.println("                        </tr>");
                ps.println("                        <tr>");
                ps.println("			<td align=left  valign=middle>");
                ps.println("			<a href=\"#\" onclick=\"resizeFrame1();\">");
                ps.println("                    <img border=\"0\" alt=\"Resize\" id=\"resizeImg\" src=\""+imagesURL+"View-Image-BOM.jpg\" />");
                ps.println("            </a>");
                ps.println("			</td>");
                ps.println("                        </tr>");
                ps.println("                        <tr>");
                ps.println("");
                ps.println("                        <td align=left valign=middle>");
                ps.println("");
                ps.println("			<a href=\"#\" onclick=\"resizeFrame();\">");
                ps.println("                    <img border=\"0\" alt=\"Resize\" id=\"resizeImg\" src=\""+imagesURL+"ViewBom.jpg\"/>");
                ps.println("            </a>");
                ps.println("                </td>");
                ps.println("				</tr></table>");
                ps.println("		");
                ps.println("    </body>");
                ps.println("</html>");

            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
        } catch (Exception e) {
            ps.println(e);
        } finally {
            ps.close();
            wps.close();
        }
    }
}
