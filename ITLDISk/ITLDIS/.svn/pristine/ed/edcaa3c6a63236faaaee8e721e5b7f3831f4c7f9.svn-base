package viewEcat.comEcat;

/*
File Name: Main.java
PURPOSE: Main Interface.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Main extends HttpServlet
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
            String getType = (String) session.getValue("user_type");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String userCode = (String) session.getValue("userCode");

            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");

            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);
            String header = "";
            header = object_pageTemplate.header(getType);

            String footer = "";
            footer = object_pageTemplate.footer();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();

            String orderRefNoString = object_pageTemplate.orderRefNoString();

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            if (session_id.equals(getSession))
            {
//                Connection conn = holder.getConnection();
                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));

//                Statement stmt = conn.createStatement();
//                ResultSet rs;

//                String msg_id = "";
//                rs = stmt.executeQuery("select message from user_check where user_id='" + userCode + "'");
//                if (rs.next())
//                {
//                    msg_id = rs.getString(1);
//                }
//                rs.close();
//                stmt.close();

                ps.println("<html>");
                ps.println("<head><meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">");
                ps.println("<style type=\"text/css\">");
                ps.println("<!--");
                ps.println("body {");
                ps.println("margin-left: 0px;");
                ps.println("margin-top: 0px;");
                ps.println("margin-right: 0px;");
                ps.println("margin-bottom: 0px;");
                ps.println("}");
                ps.println("-->");
                ps.println("</style>");

                ps.println("<script language=JavaScript type=text/JavaScript>");
                ps.println("	function MM_openBrWindow(theURL,winName,features)");
                ps.println("		{ ");
                ps.println("			var isValidPopUpBlocker=detectPopupBlocker()");
                ps.println("			if (isValidPopUpBlocker== false)");
                ps.println("			{");
                ps.println("				return");
                ps.println("			}");
                ps.println("			window.open(theURL,winName,features);");
                ps.println("		}");

//                ps.println("	function open_Msg()");
//                ps.println("	{ ");
//                ps.println("    var MyArgs = new Array();");
//                ps.println("    var WinSettings = 'center:yes;resizable:no;dialogHeight:425px;dialogWidth:480px' ");
//
//                ps.println("    var MyArgs = window.showModalDialog('Message', MyArgs, WinSettings);");
//                ps.println("    if (MyArgs != null)");
//                ps.println("    {");
//                ps.println("        location.href = 'Main'");
//                ps.println("	}");
//                ps.println("	}");


                ps.println("function detectPopupBlocker()");
                ps.println("{ ");
                ps.println("	var myTest = window.open(\"about:blank\",\"\",\"directories=no,height=100,width=100,menubar=no,resizable=no,scrollbars=no,status=no,titlebar=no,top=0,location=no\");");
                ps.println("	if (!myTest)");
                ps.println("	{");
                ps.println("		alert(\"Your Pop-up Blocker is Enabled. Please Add our site http://" + server_name + " to your trusted sites.\");");
                ps.println("		parent.location.href = 'Logout'");
                ps.println("		return false;");
                ps.println("	} ");
                ps.println("	else ");
                ps.println("	{");
                ps.println("		myTest.close();");
                ps.println("		return true;");
                ps.println("	}");
                ps.println("}");

//                ps.println("function fillSurvey()");
//                ps.println("{ ");
//                ps.println("	var confrm = confirm('Would you like to fill the Satisfaction Survey?\\n Press \\'OK\\' to fill now, and \\'CANCEL\\' to fill later.')");
//                ps.println("	if (confrm)");
//                ps.println("	{");
//                ps.println("		document.location.href = 'Survey'");
//                ps.println("	} ");
//                ps.println("}");

                ps.println("</script>");
                ps.println("</head>");

                boolean flag = false;
// Check whether the survey has been filled or not
//                if (userFunctionalities.contains("340") && orderRefNoString.equals("WEB"))
//                {
//                    OrgStructure obj = new OrgStructure();
//                    flag = obj.FillSurvey(userCode, conn);
//                }

                if (userFunctionalities.contains("400") && orderRefNoString.equals("WEB"))
                {
//                    Inventory_util inventUtils = new Inventory_util(conn);
//                    if (inventUtils.checkReorderParts(userCode))
//                    {
//                        ps.println("<body bgColor=#E0EBF2 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 onload=reorderParts('" + flag + "') >");
//                    }
//                    else if (flag)
//                    {
//                        ps.println("<body bgColor=#E0EBF2 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0 onload=fillSurvey() >");
//                    }
//                    else
//                    {
//                        ps.println("<body bgColor=#E0EBF2 leftmargin=0 topmargin=0 marginwidth=0 marginheight=0  >");
//                    }
                }
                else if (flag)
                {
                    ps.println("<body onload=fillSurvey() >");
                }
                else
                {
                    ps.println("<body   >");
                }

                // ps.println("<form name=form1>");
                ps.println("<table width=100% height=100%  border=0 cellpadding=0 cellspacing=0>");
                /* ps.println("<tr>");
                if (msg_id.equals("0"))
                {
                ps.println("<td COLSPAN=2>");
                }
                else
                {
                ps.println("<td colspan=2>");
                ps.println("<table border=0 cellpadding=0 cellspacing=0>");
                ps.println("<tr>");
                ps.println("<td width=25%>");
                ps.println("<a href=javascript:open_Msg() style='text-decoration:none'>");
                ps.println("<img src='" + imagesURL + "cooltext.gif'></a>");
                ps.println("&nbsp;");
                ps.println("</td>");
                ps.println("<td width=75%>&nbsp;</td>");
                ps.println("</tr>");
                ps.println("</table>");
                }
                ps.println("</td>");
                ps.println("</tr>");
                 */ ps.println("<tr>");
                ps.println("<td align=center valign=bottom background='" + imagesURL + "homeImages/HOME.jpg' style=\"background-repeat:no-repeat;background-position:bottom\">");
                //ps.println("<img src= width=\"1002\" height=\"456\">");
                ps.println("</td>");
                ps.println("</tr>");
                ps.println("</table>");
                //  ps.println("</form>");
                ps.println("</body>");

                ps.println("</html>");
            }
            else
            {
                ps.println(header);
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
                ps.println(footer);
            }

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
