/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author apurv.singh
 */
import authEcat.UtilityMapkeys1;

public class GroupDetail extends HttpServlet
{

    @Override
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
            //   String getType = (String) session.getValue("user_type");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            if (session_id.equals(getSession))
            {
                ///////////////////////////// CREATE SESSION /////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////

                ////////////////////////////////////////////////////////////////////////////////
                /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
                ////////////////////////////////////////////////////////////////////////////////

                PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);


                String imagesURL = "";
                imagesURL = object_pageTemplate.imagesURL();


                Connection conn = holder.getConnection();

                //Statement stmt = null;
                PreparedStatement stmt = null;
               // stmt = conn.createStatement();

                ResultSet rs = null;
                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
                String component = req.getParameter("component");
                String index = req.getParameter("index");
                String interchangeability = "", interchangeRemarks = "", cut_off = "", cut_off_chassis = "";
                String group = req.getParameter("groupNo");
                String cssClass = null;
                group = group.trim().toUpperCase();

                java.sql.Date cDate = null;
                //rs = stmt.executeQuery("SELECT INTERCHANGEABILITY, REMARKS, CUT_OFF,CUT_OFF_CHASSIS FROM  CAT_GROUP_KIT_BOM "
                       // + " WHERE GRP_KIT_NO = '" + group + "' and COMPONENT= '" + component + "' and INDEX_NO="+index+"");
               String query = ("SELECT INTERCHANGEABILITY, REMARKS, CUT_OFF,CUT_OFF_CHASSIS FROM  CAT_GROUP_KIT_BOM(NOLOCK) "
                       + " WHERE GRP_KIT_NO = '" + group + "' and COMPONENT= '" + component + "' and INDEX_NO="+index+"");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
               if (rs.next())
                {
                    interchangeability = rs.getString(1);
                    if (interchangeability == null)
                    {
                        interchangeability = "-";
                    }

                    interchangeRemarks = rs.getString(2);
                    if (interchangeRemarks == null)
                    {
                        interchangeRemarks = "-";
                    }

                    cDate = rs.getDate(3);
                    if ( cDate!= null)
                    {
                        try
                        {
                            cut_off = sdf.format(rs.getDate(3));

                        }
                        catch (Exception ex)
                        {
                             cut_off = "-";
                            ex.printStackTrace();
                        }
                    }
                    else
                    {
                        cut_off = "-";
                    }

                    cut_off_chassis = rs.getString(4);
                    if (cut_off_chassis== null)
                    {
                        cut_off_chassis = "-";
                    }

                }
                rs.close();

                ps.println("<html>");
                ps.println("<head>");
                ps.println("<title>");
                ps.println("" + UtilityMapkeys1.tile1 + "");
                ps.println("</title>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");

                ps.println("	<script language=javascript>");

                ps.println("			function onTop()");
                ps.println("			{");
                ps.println("				self.focus();");
                ps.println("				window.moveTo(0,0);");
                ps.println("			}");

                ps.println("			var top_level = window");
                ps.println("			function WebViewCheck()");
                ps.println("			{");
                ps.println("				top_level.IPAControl = parent.IPAControl ");
                ps.println("				top_level.IPAControl.SetBGColor('#336699') ");
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
                ps.println("		parent.location.href = 'Logout'");
                ps.println("		return false;");
                ps.println("	} ");
                ps.println("	else ");
                ps.println("	{");
                ps.println("		myTest.close();");
                ps.println("		return true;");
                ps.println("	}");
                ps.println("}");

                ps.println("  function windClose()");
                ps.println(" {");
                ps.println(" self.close()");
                ps.println(" }");

                ps.println("</script>");

                ps.println("</head>");
                ps.println("<body topmargin = 0 margin height = 0 onLoad=onTop()>");
                int width = 590, height = 0;
                int l = 0;
                cssClass = "links_txt";


                if (component.startsWith("TBA"))
                {
                    component = PageTemplate.tbaPart;
                }
                else
                {
                    component = component.replaceAll("_", "&nbsp;");
                }

                ps.println(object_pageTemplate.tableHeader("Details for Part No. \"" + component + "\"", width, height));

                ps.println("<table width=590px; align=\"left\" border=0 cellspacing=0 cellpadding=0 bordercolor = #CCCCCC><tr><td style=\"padding-top:5px;\">");
                ps.println("<table width=590px;  border=0  cellspacing=2 cellpadding=3 bordercolor = #CCCCCC>");
//                rs = stmt.executeQuery("SELECT DISTINCT P1 FROM GROUP_KIT_DETAIL where GRP_KIT_NO='" + group + "' ");
//                
//                if (rs.next()) {
//                    ps.println("<tr>");
//                    ps.println("<td  align=left   class=" + cssClass + "><b>");
//                    ps.println("Group No.");
//                    ps.println("</b></td>");
//                    ps.println("<td width=10% align=center valign=top class=" + cssClass + "> : </td>");
//                    ps.println("<td  align=left   class=" + cssClass + ">");
//                    ps.println(" " + group + "  [ " + rs.getString(1) + " ]");
//                    ps.println("</td>");
//                    ps.println("</tr>");
//                }
//
//                ps.println("<tr>");

                ps.println("<tr>");
                ps.println("<td align=left width=15% class=" + cssClass + "><b>");
                ps.println("Remarks");
                ps.println("</b></td>");
                ps.println("<td width=10% align=center valign=top class=" + cssClass + "> : </td>");
                ps.println("<td  width=75% align=left class=" + cssClass + ">");
                ps.println(interchangeRemarks);
                ps.println("</a>");
                ps.println("</td>");
                ps.println("</tr>");

                ps.println("<tr>");
                ps.println("<td  align=left width=15% class=" + cssClass + "><b>");
                ps.println("Interchangeability");
                ps.println("</b></td>");
                ps.println("<td width=10% align=center valign=top class=" + cssClass + "> : </td>");
                ps.println("<td  width=75% align=left class=" + cssClass + ">");
                ps.println(interchangeability);
                ps.println("</a>");
                ps.println("</td>");
                ps.println("</tr>");



                ps.println("<tr>");
                ps.println("<td  width=15% align = left class=" + cssClass + "><b>");
                ps.println("Cut Off Chassis");
                ps.println("</b></td>");
                ps.println("<td width=10% align=center valign=top class=" + cssClass + "> : </td>");
                ps.println("<td  width=75% align=left class=" + cssClass + ">");
                ps.println(cut_off_chassis);
                ps.println("</td>");
                ps.println("</tr>");

                ps.println("<tr>");
                ps.println("<td  width=15% align = left class=" + cssClass + "><b>");
                ps.println("Cut Off Date");
                ps.println("</b></td>");
                ps.println("<td width=10% align=center valign=top class=" + cssClass + "> : </td>");
                ps.println("<td  width=75% align=left class=" + cssClass + ">");
                ps.println(cut_off);
                ps.println("</td>");
                ps.println("	</tr>");

                ps.println("</table>");
                ps.println("</td>");
                ps.println("</tr>");
                ps.println("</table>");


                ps.println(object_pageTemplate.tableFooter());
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            ps.println(e);
        }
        finally
        {
            ps.close();
            wps.close();
        }
    }
}
