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
File Name: KitChmt.java
PURPOSE: To find any modification done in kits in Change Mgmt.
HISTORY:
DATE			BUILD		AUTHOR					MODIFICATIONS
NA				v3.4		Deepak Mangal			$$0 Created
 */
import authEcat.UtilityMapkeys1;

public class KitChmt extends HttpServlet
{

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintStream wps = new PrintStream(res.getOutputStream());         PrintWriter ps = new PrintWriter(wps, true);
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

            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////


            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String footer = "";
            footer = object_pageTemplate.footer();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();


            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            if (session_id.equals(getSession))
            {
                Connection conn = holder.getConnection();
                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));
                
                

                /*########################################*/

                String kitNo = req.getParameter("kitNo");

                //Statement stmt = conn.createStatement();
                PreparedStatement stmt = null;
                ResultSet rs;
                String component = "";

                Vector revisionVec = new Vector();
                
                ps.println("<html>");
                ps.println("   <head>");
                ps.println("      <title>");
                ps.println("         "+UtilityMapkeys1.tile1+"");
                ps.println("      </title>");
                ps.println("			<style type=text/css>");
                ps.println("			<!--");
                ps.println("			* { font-family: Helvetica; font-size: 8pt;;}");
                ps.println("			a:link {				color: #FFFFFF;				text-decoration: none; }");
                ps.println("			a:visited { color: #FFFFFF;				text-decoration: none;		  }	");
                ps.println("			-->");
                ps.println("			</style>");

                ps.println("</head>");
                ps.println("<body >");

               // rs = stmt.executeQuery("SELECT DISTINCT REV_NO FROM CAT_KIT_CHANGE_MGMT where KIT_NO='" + kitNo + "' ORDER BY REV_NO");
               String query = ("SELECT DISTINCT REV_NO FROM CAT_KIT_CHANGE_MGMT(NOLOCK) where KIT_NO='" + kitNo + "' ORDER BY REV_NO");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
               if (rs.next())
                {
                    do
                    {
                        revisionVec.add(rs.getString(1));
                    }
                    while (rs.next());
                }
                rs.close();

                if (revisionVec.size() == 0)
                {
                    ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 height = 95% width=50%>");
                    ps.println("<tr>");
                    ps.println("<td valign = middle>");
                    ps.println("<table cellspacing=0 cellpadding=10 border=1 width=100% bordercolor=#D7D7D7>");
                    ps.println("<tr bgcolor = #003399>");
                    ps.println("<td align = center>");
                    ps.println("<font color = #FFFFFF><b>");
                    ps.println("NO MODIFICATION DONE.");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table>");

                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table>");
                    
                    stmt.close();
                    return;
                }

                for (int i = 0; i < revisionVec.size(); i++)
                {

                   // rs = stmt.executeQuery("SELECT * FROM CAT_KIT_CHANGE_MGMT WHERE REV_NO = " + revisionVec.elementAt(i));
                    String query1 = ("SELECT * FROM CAT_KIT_CHANGE_MGMT(NOLOCK) WHERE REV_NO = " + revisionVec.elementAt(i));
                    stmt = conn.prepareStatement(query1);
                    rs = stmt.executeQuery();
                    if (rs.next())
                    {
                        ps.println("<center><table cellspacing=1 cellpadding=3 border=1 width=99% bordercolor = #C0C0C0>");
                        ps.println("<tr bgcolor = #003399>");
                        ps.println("<td colspan = 2>");
                        ps.println("<font color=#FFFFFF>");
                        ps.println("<b>REVISION NO:</b>");
                        ps.println("</font>");
                        ps.println("</td>");

                        ps.println("<td colspan = 3>");
                        ps.println("<font color=#FFFFFF><b>");
                        ps.println(revisionVec.elementAt(i));
                        ps.println("</b></font>");
                        ps.println("</font>");
                        ps.println("</td>");

                        ps.println("</tr>");

                        ps.println("<tr>");

                        ps.println("<td width = 25%>");
                        ps.println("<b>Part No.");
                        ps.println("</td>");

                        ps.println("<td width = 30%>");
                        ps.println("<b>Change Type");
                        ps.println("</td>");

                        ps.println("<td width = 25%>");
                        ps.println("<b>Replaced Part No.");
                        ps.println("</td>");

                        ps.println("<td width = 10% align = center>");
                        ps.println("<b>Old Qty");
                        ps.println("</td>");

                        ps.println("<td width = 10% align = center>");
                        ps.println("<b>New Qty");
                        ps.println("</td>");

                        ps.println("</tr>");

                        String change_type = "";
                        String replaced_comp = "";
                        String oldQty = "";
                        String newQty = "";

                        do
                        {
                            component = rs.getString(3);
                            if (component == null)
                            {
                                component = "";
                            }

                            change_type = rs.getString(4);
                            if (change_type == null)
                            {
                                change_type = "";
                            }

                            replaced_comp = rs.getString(5);
                            if (replaced_comp == null)
                            {
                                replaced_comp = "";
                            }

                            oldQty = rs.getString(6);
                            if (oldQty == null)
                            {
                                oldQty = "";
                            }

                            newQty = rs.getString(7);
                            if (newQty == null)
                            {
                                newQty = "";
                            }

                            ps.println("<tr>");

                            ps.println("<td>");
                            ps.println(component);
                            ps.println("</td>");

                            ps.println("<td>");
                            if (change_type != null)
                            {
                                ps.println(change_type);
                            }
                            else
                            {
                                ps.println("&nbsp;");
                            }
                            ps.println("</td>");

                            ps.println("<td>");

                            ps.println(replaced_comp);

                            if (replaced_comp == null || replaced_comp.equals("") || replaced_comp.equals(" "))
                            {
                                ps.println("&nbsp;");
                            }

                            ps.println("</td>");

                            ps.println("<td align = center>");
                            ps.println("" + oldQty + "&nbsp;");
                            ps.println("</td>");

                            ps.println("<td align = center>");
                            ps.println("" + newQty + "&nbsp;");
                            ps.println("</td>");

                            ps.println("</tr>");
                        }
                        while (rs.next());

                        ps.println("</table>");
                        ps.println("<br>");
                    }
                    rs.close();
                }
                stmt.close();
            }
            else
            {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
            ps.println(footer);
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
