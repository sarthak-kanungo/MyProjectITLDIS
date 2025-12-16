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
File Name: Home_page.java
PURPOSE: To show various criterias for viewing e-catalogue.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import authEcat.UtilityMapkeys1;

public class Home_page extends HttpServlet
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
            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            if (session_id.equals(getSession))
            {
                String form_id = req.getParameter("id");

                String qval = req.getParameter("q");

                ps.println("<html>");
ps.println("<head>");
ps.println("<title>"+UtilityMapkeys1.tile+"</title>");
ps.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">");
ps.println("<style type=\"text/css\">");
ps.println("<!--");
ps.println("body {");
ps.println("	background-color: #000000;;");
ps.println("	margin-left: 0px;");
ps.println("	margin-top: 0px;");
ps.println("	margin-right: 0px;");
ps.println("	margin-bottom: 0px;");
ps.println("}");
ps.println(".textfield {border:1px solid #969696;");
ps.println("	width:125px;");
ps.println("	margin:0px 0px 0px 0;");
ps.println("	height:22px;");
ps.println("	font-size:11px;");
ps.println("	padding: 5px 0 0 5px;");
ps.println("");
ps.println("");
ps.println("}");
ps.println("-->");
ps.println("</style>");
 ps.println("		<script language=JavaScript type=text/JavaScript>");
                ps.println("	document.attachEvent('onkeydown', my_onkeydown_handler);");

                ps.println("	function my_onkeydown_handler()");
                ps.println("	{");
                ps.println("	  switch (event.keyCode)");
                ps.println("	  {");
                ps.println("		  case 13 : // 'Enter'");
                ps.println("			document.f3.submit();");
                ps.println("			break;");
                ps.println("	}");
                ps.println("	}");
                ps.println("		function submitForm()");
                ps.println("		{ ");
                ps.println("				document.f3.submit();");
                ps.println("		}");

                ps.println("		function checkRadio_serial()");
                ps.println("		{ ");
                ps.println("				document.f3.criteria[0].checked = true");
                ps.println("		}");

                ps.println("		function checkRadio_date()");
                ps.println("		{ ");
                ps.println("				document.f3.criteria[1].checked = true");
                ps.println("		}");

                ps.println("</script>");

        ps.println("</head>");
ps.println("<body>");
 ps.println("			<form name=f3 method=post action=Authenticate_criteria method=post>");
ps.println("");
ps.println("<table width=\"1002\" height=\"595\" border=\"0\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" id=\"Table_01\">");
ps.println("	<tr>");
ps.println("		<td colspan=\"2\">");
ps.println("			<img src=\"" + imagesURL + "loginImages/Login_01.jpg\" width=\"1002\" height=\"158\" alt=\"\"></td>");
ps.println("	</tr>");
ps.println("	<tr>");
ps.println("		<td align=\"right\">			<object classid=\"clsid:D27CDB6E-AE6D-11cf-96B8-444553540000\" codebase=\"http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0\" width=\"776\" height=\"280\">");
ps.println("          <param name=\"movie\" value=\"" + imagesURL + "loginImages/SWF.swf\">");
ps.println("          <param name=\"quality\" value=\"high\">");
ps.println("          <embed src=\"" + imagesURL + "loginImages/SWF.swf\" quality=\"high\" pluginspage=\"http://www.macromedia.com/go/getflashplayer\" type=\"application/x-shockwave-flash\" width=\"776\" height=\"280\"></embed>");
ps.println("	    </object></td>");
ps.println("		<td width=\"226\" style=\" background-image:url(" + imagesURL + "loginImages/login_05.jpg); background-repeat:no-repeat; \"><table width=\"100%\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
ps.println("          <tr>");
ps.println("            <td style=\"padding: 0 0 0px 45px \">&nbsp;</td>");
ps.println("          </tr>");
ps.println("          <tr>");
ps.println("            <td style=\"padding: 12px 0 0 20px  \"><input name=criteria type=radio value=serialNo></td>");//
ps.println("          </tr>");
ps.println("          <tr>");
ps.println("            <td style=\"padding: 4px 0 0 45px \"><input name=serialNo type=text size=17 class=\'textfield\' onChange=checkRadio_serial()>");
ps.println("<input name=q type=hidden value=" + qval + ">");
ps.println("</td>");
ps.println("          </tr>");
ps.println("          <tr>");
ps.println("            <td style=\"padding: 20px 0 0 20px  \"><input name=criteria type=radio value='latest' checked></td>");
ps.println("          </tr>");
ps.println("          <tr>");
ps.println("            <td style=\"padding: 0px 0 0 0px \">&nbsp;</td>");
ps.println("          </tr>");
ps.println("          <tr>");
ps.println("            <td style=\"padding: 10px 0 0px 45px \"></td>");//<input nname=\"password\" type=\"text\" class=\"textfield\">
ps.println("          </tr>");
ps.println("          <tr>");
ps.println("            <td style=\"padding: 0px 0 0 104px \">&nbsp;</td>");
ps.println("          </tr>");
ps.println("          <tr>");
ps.println("            <td style=\"padding: 0px 0 0 104px \"><img name=formSubmit name=\'Submit\' src=\"" + imagesURL + "loginImages/submit.jpg\" onClick = \"submitForm();\" width=\"75\" height=\"28\" border=\"0\" usemap=\"#Map\"></td>");//
ps.println("          </tr>");//<input type=\'submit\' name=\'Submit\' value=Submit class=\"submit-button\" onClick = submitForm()>
ps.println("        </table></td>");
ps.println("	</tr>");
ps.println("	<tr>");
ps.println("		<td colspan=\"2\">");
ps.println("			<img src=\"" + imagesURL + "loginImages/Login_04.jpg\" width=\"1002\" height=\"157\" alt=\"\"></td>");
ps.println("<map name=\"Map\">");
ps.println("  <area shape=\"rect\" coords=\"-5,2,80,26\" href=\"#\">");
ps.println("</map>");ps.println("	</tr>");
ps.println("</table>");
ps.println("");

ps.println("</form>");
ps.println("</body>");
ps.println("</html>");

            }
        } catch (Exception e)
        {
            ps.println(e);
            e.printStackTrace();
        } finally
        {
            ps.close();
            wps.close();
        }
    }
}
