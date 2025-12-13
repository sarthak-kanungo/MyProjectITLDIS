package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
File Name: ChangeMainMessage.java
PURPOSE: Change Main Message Interface.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */

import authEcat.UtilityMapkeys1;

public class ChangeMainMessage extends HttpServlet
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
            String header = "";
            header = object_pageTemplate.header(getType);

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();


            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////


            ps.println(header);

            if (session_id.equals(getSession))
            {

                Connection conn = holder.getConnection();
                //Statement stmt;
                PreparedStatement stmt = null;
                ResultSet rs;
                //stmt = conn.createStatement();

                Vector msg = new Vector();
                String msg_Value = "";
                String msg_Color = "";
                int j = 1;
                // = stmt.executeQuery("select msg, msg_color from GEN_MAIN_MESSAGE order by msg_no");
                String query = ("select msg, msg_color from GEN_MAIN_MESSAGE(NOLOCK) order by msg_no");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                while (rs.next())
                {
                    msg_Value = rs.getString(1);
                    msg_Color = rs.getString(2);
                    if (msg_Value == null || msg_Value.equals("") || msg_Value.equals(" "))
                    {
                        msg_Value = "";
                    }
                    if (msg_Color == null || msg_Color.equals("") || msg_Color.equals(" "))
                    {
                        msg_Color = "BLACK";
                    }
                    msg.add(msg_Value);
                    msg.add(msg_Color);
                }
                rs.close();
                stmt.close();

                ps.println("<HTML>");
                ps.println("<HEAD>");
                ps.println("<TITLE>"+UtilityMapkeys1.tile1+"</TITLE>");

                ps.println("<script language=JavaScript type=text/JavaScript>");

                ps.println("	function validate()");
                ps.println("	 { ");
                ps.println("			javascript:document.form1.submit()");
                ps.println("	 }");

                ps.println("</script>");

                ps.println("</HEAD>");
                ps.println("<BODY >");

                ps.println("<br>");
                ps.println("<br>");
                ps.println("<br>");
                ps.println("<br>");

                ps.println("<table width=100% height=60% border=0 align=center cellpadding=0 cellspacing=0 bordercolor=#B2A277 valign = middle>");
                ps.println("<tr> ");
                ps.println("<td align = center>");

                ps.println("<form action=ChangeMainMessage1 method=post name=form1>");

                ps.println("<table id='myTable' width=500 border=1 cellspacing=1 cellpadding=5 bordercolor=#CCCCCC>");
                ps.println("<tr> ");
                ps.println("<td colspan=3 bgcolor=#003399> <div align=center><font size=4 face=Helvetica color = #ffffff><strong>");
                ps.println("CHANGE MAIN MESSAGE CONSOLE");
                ps.println("</strong></font></div></td>");
                ps.println("</tr>");

                ps.println("<tr> ");
                ps.println("<td width=10%> <div align=left> ");
                ps.println("<b>");
                ps.println("S.No.");
                ps.println("</td>");
                ps.println("<td width=40%> <div align=center> ");
                ps.println("<b>");
                ps.println("COLOR");
                ps.println("</td>");
                ps.println("<td width=50%> <div align=center> ");
                ps.println("<b>");
                ps.println("MESSAGE");
                ps.println("</td>");
                ps.println("</tr>");

                for (int i = 0; i < msg.size(); i = i + 2)
                {
                    ps.println("<tr> ");
                    ps.println("<td width=10%> <div align=center>");
                    ps.println("<b>");
                    ps.println(j + ".");
                    ps.println("</td>");
                    ps.println("<td width=40%>");
                    ps.println("<select name=clr_" + j + ">");
                    if (msg.elementAt(i + 1).equals("BLACK"))
                    {
                        ps.println("<option value='BLACK' selected>BLACK</option>");
                        ps.println("<option value='#000099'>BLUE</option>");
                        ps.println("<option value='GREEN'>GREEN</option>");
                        ps.println("<option value='RED'>RED</option>");
                        ps.println("<option value='BROWN' >BROWN</option>");
                    }
                    if (msg.elementAt(i + 1).equals("#000099"))
                    {
                        ps.println("<option value='BLACK' >BLACK</option>");
                        ps.println("<option value='#000099' selected>BLUE</option>");
                        ps.println("<option value='GREEN'>GREEN</option>");
                        ps.println("<option value='RED'>RED</option>");
                        ps.println("<option value='BROWN' >BROWN</option>");
                    }
                    if (msg.elementAt(i + 1).equals("GREEN"))
                    {
                        ps.println("<option value='BLACK'>BLACK</option>");
                        ps.println("<option value='#000099'>BLUE</option>");
                        ps.println("<option value='GREEN' selected>GREEN</option>");
                        ps.println("<option value='RED'>RED</option>");
                        ps.println("<option value='BROWN' >BROWN</option>");
                    }
                    if (msg.elementAt(i + 1).equals("RED"))
                    {
                        ps.println("<option value='BLACK'>BLACK</option>");
                        ps.println("<option value='#000099'>BLUE</option>");
                        ps.println("<option value='GREEN'>GREEN</option>");
                        ps.println("<option value='RED' selected>RED</option>");
                        ps.println("<option value='BROWN' >BROWN</option>");
                    }
                    if (msg.elementAt(i + 1).equals("BROWN"))
                    {
                        ps.println("<option value='BLACK'>BLACK</option>");
                        ps.println("<option value='#000099'>BLUE</option>");
                        ps.println("<option value='GREEN'>GREEN</option>");
                        ps.println("<option value='RED'>RED</option>");
                        ps.println("<option value='BROWN' selected>BROWN</option>");
                    }

                    ps.println("</select>");
                    ps.println("</td>");
                    ps.println("<td width=50%>");
                    ps.println("<input type=text name=text_" + j + " value='" + msg.elementAt(i) + "' size=70 maxlength=200>");
                    ps.println("</td>");
                    ps.println("</tr>");
                    j++;
                }

                ps.println("<tr> ");
                ps.println("<td colspan = 3><div align=center> ");
                ps.println("<input type=button name=Submit value=Submit onClick = validate()>");
                ps.println("</div></td>");
                ps.println("</tr>");

                ps.println("<tr>");
                ps.println("<td valign = middle align = right colspan=3>");

                ps.println("<a href ='View_dealers_1a'>");
                ps.println("<font color=#000099><b>");
                ps.println("<< BACK");
                ps.println("</font></a>");

                ps.println("</td>");
                ps.println("</tr>");

                ps.println("</table>");
                ps.println("</form>");
                ps.println("</td>");
                ps.println("</tr>");
                ps.println("</table>");
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
