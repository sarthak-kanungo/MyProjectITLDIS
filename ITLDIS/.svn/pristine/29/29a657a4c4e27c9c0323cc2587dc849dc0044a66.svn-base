package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
File Name: Message.java
PURPOSE: It is used to show the current alert message to the user.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import authEcat.UtilityMapkeys1;


public class Message extends HttpServlet
{
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");

        res.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
        res.setHeader("Expires", "0");
        res.setHeader("Pragma", "no-cache");

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
            String userCode = (String) session.getValue("userCode");

            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");

            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();


            if (session_id.equals(getSession))
            {

                Connection conn = holder.getConnection();

                //Statement stmt = conn.createStatement();
                PreparedStatement stmt = null;
                ResultSet rs;

                String msg_id = "";
                String msg_txt = "";
                String msg_date = "";

               // rs = stmt.executeQuery("select message from UM_user_check where user_id='" + userCode + "'");
                String query = ("select message from UM_user_check(NOLOCK) where user_id='" + userCode + "'");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                if (rs.next())
                {
                    msg_id = rs.getString(1);
                }
                rs.close();

                //rs = stmt.executeQuery("select message,msg_date from user_message where cust_code='" + userCode + "' and msg_id=" + Integer.parseInt(msg_id) + "");
              String query1 = ("select message,msg_date from user_message(NOLOCK) where cust_code='" + userCode + "' and msg_id=" + Integer.parseInt(msg_id) + "");
                stmt = conn.prepareStatement(query1);
                rs = stmt.executeQuery();
              if (rs.next())
                {
                    msg_txt = rs.getString(1);
                    msg_date = rs.getString(2);
                }
                rs.close();

                //stmt.executeUpdate("update UM_user_check set message='0' where user_id='" + userCode + "'");
               String sqlUpdate = ("update UM_user_check set message='0' where user_id='" + userCode + "'");
               stmt = conn.prepareStatement(sqlUpdate);
               stmt.executeUpdate();
                stmt.close();
                
                conn.commit();

                ps.println("<HTML>");
                ps.println("<HEAD>");
                ps.println("<TITLE>"+UtilityMapkeys1.tile1+"</TITLE>");

                ps.println("<META HTTP-EQUIV=Content-Type CONTENT=text/html; charset=iso-8859-1>");

                ps.println("			<style type=text/css>");
                ps.println("			<!--");
                ps.println("			body");
                ps.println("			{");
                ps.println("				scrollbar-face-color: #cccccc; scrollbar-arrow-color: #000000; scrollbar-track-color: #ffffff;");
                ps.println("			}");
                ps.println("			* { font-family: Helvetica; font-size: 8pt;}");
                ps.println("			-->");
                ps.println("			</style>");
                ps.println("<script language=JavaScript type=text/JavaScript>");
                ps.println("	 	function winClose()");
                ps.println("			{ ");
                ps.println("					document.form1.action='Main'");
                ps.println("					document.form1.target='right'");
                 ps.println("					document.form1.submit();");
                ps.println("			}");
                ps.println("</script>");
                ps.println("</head>");
                ps.println("<body  leftmargin=0 topmargin=0 marginwidth=0 marginheight=0  onUnload='winClose();'>");
                ps.println("<form name=form1>");
                ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 height = 95% width=80%>");
                ps.println("<tr>");
                ps.println("<td valign = middle>");
                ps.println("<table cellspacing=0 cellpadding=10 border=1 width=100% bordercolor=#D7D7D7>");
                ps.println("<tr bgcolor = #003399>");
                ps.println("<td align = center width=25%>");
                ps.println("<font color = #FFFFFF><b>");
                ps.println(msg_date);
                ps.println("</td>");
                ps.println("<td align = center width=75%>");
                ps.println("<font color = #FFFFFF><b>");
                ps.println(msg_txt);
                ps.println("</td>");
                ps.println("</tr>");
                ps.println("</table>");

                ps.println("</td>");
                ps.println("</tr>");
                ps.println("</table>");
                ps.println("</form></body></html>");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            ps.close();
            wps.close();
        }
    }
}
