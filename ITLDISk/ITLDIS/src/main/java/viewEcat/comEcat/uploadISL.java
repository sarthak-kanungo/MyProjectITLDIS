package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import authEcat.UtilityMapkeys1;
/**
 *
 * @author dipti.kewlani
 */
public class uploadISL extends HttpServlet {
   
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


            //ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();
            //String imagesURL=object_pageTemplate.imagesURL();

            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////

            if (session_id.equals(getSession))
            {
                ps.println("<HTML>");
                ps.println("<HEAD>");
                ps.println("<TITLE>"+UtilityMapkeys1.tile1+"</TITLE>");
                ps.println("<META HTTP-EQUIV=Content-Type CONTENT=text/html; charset=iso-8859-1>");
                ps.println("<script language=\"JavaScript\" src='" + imagesURL + "datejs.js'></script>");
                ps.println("<script language=\"JavaScript\" src='" + imagesURL + "overlib_mini.js'></script>");
                ps.println("<script language=JavaScript type=text/JavaScript>");

                ps.println("function validate()");
                ps.println("{");
                ps.println("	var bulltnFile = document.f1.bulltnFile.value");
                ps.println("var index=bulltnFile.lastIndexOf('.');");
                ps.println("var doc_type=bulltnFile.substring(index+1);");
                ps.println("     if(bulltnFile == '')");
                ps.println("		{");
                ps.println("			alert('Please browse file to upload.')");
                ps.println("			return;");
                ps.println("		}");
                ps.println("      else if((bulltnFile.charAt(0) == '+\\+' && bulltnFile.charAt(1) == '+\\+') && (bulltnFile.charAt(0) == '+/+' && bulltnFile.charAt(1) == '+/+'))");
                ps.println("            {");
                ps.println("                    alert('Enter Valid location');");
                ps.println("                     return false;");
                ps.println("               }");
                ps.println("       else if(!bulltnFile.charAt(0).match(/^[a-zA-Z]/))  ");
                ps.println("                {");
                ps.println("                    alert('Enter Valid location');");
                ps.println("                     return false;");
                ps.println("               }");
                ps.println("       else if(!(doc_type=='xls'||doc_type=='xslx'))");
                ps.println("        {");
                ps.println("          alert('Please upload only Excel File.')");
                ps.println("          return false;");
                ps.println("         }");
                ps.println("	else");
                ps.println("	{");
                ps.println("		document.f1.action = '" + servletURL + "UploadISL1'");
                ps.println("		document.f1.submit();");
                ps.println("	}");
                ps.println("}");

                ps.println("</script>");

                ps.println("			<style type=text/css>");
                ps.println("			<!--");
                ps.println("			body");
                ps.println("			* { font-family: Helvetica; font-size: 10pt; }");
                ps.println("			a:link {				color: #000000;				text-decoration: none; }");
                ps.println("			a:visited { color: #000000;				text-decoration: none;		  }	");
                ps.println("			-->");
                ps.println("			</style>");
                ps.println("</HEAD>");

                ps.println("<BODY >");

                ps.println("<br>");
                ps.println("<br>");
                ps.println("<br>");

                ps.println("<form name=f1 method=post ENCTYPE=\"multipart/form-data\">");

                ps.println("<table id='myTable' width=400 border=1 align=center cellspacing=1 cellpadding=5 bordercolor=#CCCCCC>");
                ps.println("<tr> ");
                ps.println("<td colspan=2 bgcolor=#003399> <div align=center><font size=4 face=Helvetica color = #ffffff><strong>");
                ps.println("Upload ISL");
                ps.println("</strong></font></div></td>");
                ps.println("</tr>");
                ps.println("<tr>");
                ps.println("<td width=100% align = center>");
                ps.println("<input size=40 type=file name=bulltnFile value='Browse' >");
                ps.println("</td>");
                ps.println("</tr>");
                ps.println("<tr>");
                ps.println("<td align = center>");
                ps.println("<input type = button value='Submit' onClick='validate()'");

                ps.println("</td>");
                ps.println("</tr>");

                ps.println("<tr>");
                ps.println("<td colspan = 2 valign=center align = left>");
                ps.println("<b>** <a href=" + mainURL + "dealer/images/ISLFormat.zip><font color=#000099>Click Here</font></a>");
                ps.println(" to download excel format.");
                ps.println("</b></td>");
                ps.println("</tr>");

                ps.println("</table>");
                ps.println("</form>");

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
