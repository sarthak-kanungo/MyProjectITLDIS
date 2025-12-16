package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
File Name: CompChangeDetails.java
PURPOSE: To find the change revision details of particular component in a group.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import authEcat.UtilityMapkeys1;

public class CompChangeDetails extends HttpServlet
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

            String footer = "";
            footer = object_pageTemplate.footer();

            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////


            if (session_id.equals(getSession))
            {

                int tableHeight_1 = 345;

                String groupNo = req.getParameter("groupNo");
                String component = req.getParameter("component");


                /*String partOrAssy = "PRT";
                Connection conn = holder.getConnection();
                Statement stmt1 = conn.createStatement();
                Statement stmt2 = conn.createStatement();
                ResultSet rs1, rs2;
                rs1 = stmt1.executeQuery("select * from CAT_PART where part_no ='" + component + "'");
                if (rs1.next())
                {
                partOrAssy = "PRT";
                }
                else
                {
                rs2 = stmt2.executeQuery("select * from assy_detail where assy_no ='" + component + "'");
                if (rs2.next())
                {
                partOrAssy = "ASM";
                }
                rs2.close();
                }
                rs1.close();
                stmt2.close();
                stmt1.close();
                 */

                String printString = "";
                printString = printString + "<a href=javascript:MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + component + "','DETAILS','scrollbars=yes,width=700,height=605')>";

//                if (partOrAssy.equals("PRT"))
//                {
//                    printString = printString + "<a href=javascript:MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + component + "','DETAILS','scrollbars=yes,width=700,height=605')>";
//                }
//                else
//                {
//                    printString = printString + "<a href=javascript:MM_openBrWindow('" + servletURL + "AssyDetails?assyNo=" + component + "','ASSYDETAILS','scrollbars=yes,width=700,height=605')>";
//                }
                printString = printString + "";
                printString = printString + component;
                printString = printString + "";
                printString = printString + "</a>";

                Vector superVec = new Vector();

                // superVec.add("<b>Current Component:</b>" + printString);

                superVec = getSupercedenceVec(holder.getConnection(), component, groupNo, superVec, "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;", servletURL, imagesURL);

                ps.println("<html>");
                ps.println("<head>");
                ps.println("<title>");
                ps.println("" + UtilityMapkeys1.tile1 + "");
                ps.println("</title>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");

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


                ps.println("</script>");
                ps.println("<body topmargin = 0 margin height = 0>");
                int width = 600, height = 0;
                ps.println(object_pageTemplate.tableHeader("Component Change History", width, height));

                ps.println("<div class=\"maindiv\" STYLE=\" overflow-X:hidden; overflow-y: scroll; height:" + tableHeight_1 + ";\">");
                ps.println("<table width=95% border=0 cellspacing=0 cellpadding=5 bordercolor=#CCCCCC>");
                int cnt = 1;
                ps.println("<tr>");
                ps.println("<td align = left colspan=4 class=links_txt>");
                ps.println("<b>Current Component: </b>" + printString);
                ps.println("</td>");
                ps.println("</tr>");

                for (int i = 0; i < superVec.size(); i = i + 2)
                {
                    ps.println("<tr>");
                    ps.println("<td align = left colspan=4 class=links_txt>");
                    ps.println(superVec.elementAt(i + 1));
                    ps.println("</td>");
                    ps.println("</tr>");
                    cnt++;
                }

                ps.println("</table>");
                ps.println("</div>");
                ps.println(object_pageTemplate.tableFooter());
            }
            else
            {
                ps.println(header);
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
            wps.close();
        }
    }

    public Vector getSupercedenceVec(Connection conn, String component, String groupNo, Vector superVec, String spaceString, String servletURL, String imagesURL)
    {
        String replaced_comp = "";
        String status = "";
        String effDate = "";
        String EFFECTIVE_TSN = "";
        // String revision = "";
        //  String serialNo = "";
        String printString = "";
        String partOrAssy = "PRT";
        try
        {
            //Statement stmt = conn.createStatement();
        	PreparedStatement stmt = null;
            Statement stmt1 = conn.createStatement();
            Statement stmt2 = conn.createStatement();
            ResultSet rs;

            DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
            //SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");

            // rs = stmt.executeQuery("SELECT replaced_comp,revision,serialNo_ID FROM change_mgmt WHERE component ='" + component + "' AND change_type ='Replaced' AND changed_group_engine = '" + groupNo + "' order by revision desc");
            //rs = stmt.executeQuery("SELECT OLD_PART,STATUS,EFFECTIVE_DATE,EFFECTIVE_TSN FROM CAT_ECN_IMPL_HISTORY WHERE NEW_PART ='" + component + "' AND change_type ='Replaced' AND GROUP_ASSY_NO = '" + groupNo + "' order by EFFECTIVE_DATE desc");
            String query = ("SELECT OLD_PART,STATUS,EFFECTIVE_DATE,EFFECTIVE_TSN FROM CAT_ECN_IMPL_HISTORY(NOLOCK) WHERE NEW_PART ='" + component + "' AND change_type ='Replaced' AND GROUP_ASSY_NO = '" + groupNo + "' order by EFFECTIVE_DATE desc");
            stmt = conn.prepareStatement(query);
            rs = stmt.executeQuery();
            if (rs.next())
            {
                do
                {
                    printString = "";
                    partOrAssy = "PRT";
                    replaced_comp = rs.getString(1);
                    //revision = rs.getString(2);
                    //serialNo = rs.getString(3);                    
                    status = rs.getString(2);
                    // effDate = df.format(df.parse(sdf1.format(rs.getDate(3))));
                    effDate = df.format(rs.getDate(3));
                    EFFECTIVE_TSN = rs.getString(4);
                    /* rs1 = stmt1.executeQuery("select * from part where part_no ='" + replaced_comp + "'");
                    if (rs1.next())
                    {
                    partOrAssy = "PRT";
                    }
                    else
                    {
                    rs2 = stmt2.executeQuery("select * from assy_detail where assy_no ='" + replaced_comp + "'");
                    if (rs2.next())
                    {
                    partOrAssy = "ASM";
                    }
                    rs2.close();
                    }
                    rs1.close();
                     */
                    if ((replaced_comp != null) && (!replaced_comp.equals("")))
                    {
                        if (!superVec.contains(replaced_comp))
                        {
                            printString = printString + spaceString + "<img src=" + imagesURL + "bullet.jpg" + ">&nbsp;&nbsp;";
                          //  if (partOrAssy.equals("PRT"))
                          //  {
                                printString = printString + "<a href=javascript:MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + replaced_comp + "','DETAILS','scrollbars=yes,width=700,height=605')>";
                          //  }
                          //  else
                          //  {
                          //      printString = printString + "<a href=javascript:MM_openBrWindow('" + servletURL + "AssyDetails?assyNo=" + replaced_comp + "','ASSYDETAILS','scrollbars=yes,width=700,height=605')>";
                          //  }
                            printString = printString + "";
                            printString = printString + replaced_comp;
                            printString = printString + "";
                            printString = printString + "</a>";
                            printString = printString + "&nbsp;&nbsp;(";
                            if ((EFFECTIVE_TSN != null) && !EFFECTIVE_TSN.equals("0"))
                            {
                                printString = printString + "TSN: " + EFFECTIVE_TSN + ", UPTO: " + effDate + ", STATUS: " + status;
                            }
                            else
                            {
                                printString = printString + "UPTO: " + effDate + ", STATUS: " + status;
                            }

                            printString = printString + "";
                            printString = printString + ")";

                            superVec.add(replaced_comp);
                            superVec.add(printString);

                            superVec = getSupercedenceVec(conn, replaced_comp, groupNo, superVec, spaceString + "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;", servletURL, imagesURL);
                        }


                    }
                }
                while (rs.next());
            }
            rs.close();
            stmt2.close();
            stmt1.close();
            stmt.close();

            return superVec;
        }
        catch (Exception e)
        {
            return superVec;
        }

    }
}
