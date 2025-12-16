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
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class compAvailable extends HttpServlet
{

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        ArrayList<String> compAvailable = null;
        String compTypeHeading = "";
        String message = null;
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
            String screenWidth = (String) session.getValue("screenWidth");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");

            String date_OR_serial = (String) session.getValue("date_OR_serial");


            ///////////////////////////// CREATE SESSION /////////////////////////////

            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////


            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();

            String modelImage = "";
            modelImage = object_pageTemplate.modelImage();

            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////


            if (session_id.equals(getSession))
            {
                Connection conn = holder.getConnection();

                //Statement stmt;
                PreparedStatement stmt = null;
                ResultSet rs;

                int tableWidth1 = 800;
                int tableWidth = 615;
                int tableHeight_1 = 415;

                if (screenWidth.equals("800"))
                {
                    tableWidth = 560;
                    tableHeight_1 = 200;
                }
                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));

                /*########################################*/

                //stmt = conn.createStatement();

                //Vector groupTypes = new Vector();

                String model = req.getParameter("model");
                String compType = req.getParameter("compType");
                String engineSeries = req.getParameter("engineSeries");
                String engineModel = req.getParameter("engineModel");
//                String aggregate = req.getParameter("aggregate");
//                String partSearch = req.getParameter("partSearch");
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

                compAvailable = new ArrayList<String>();//

                String heading = null;
                String toolNumber = "";

                String engModel = "-";
                //rs = stmt.executeQuery("SELECT ENGINE_MODEL from CAT_MODEL_CLASSIFICATION   where model_no='" + model + "'");//
                String query = ("SELECT ENGINE_MODEL from CAT_MODEL_CLASSIFICATION(NOLOCK)   where model_no='" + model + "'");
               stmt = conn.prepareStatement(query);
               rs = stmt.executeQuery();
                if (rs.next())
                {
                    engModel = rs.getString(1);
                }
                rs.close();
                //rs = stmt.executeQuery("SELECT pmm.part_no,p.p1,p.np4,p.p3 from CAT_PART p, CAT_PART_MODEL_MATRIX pmm  where p.part_no=pmm.part_no and pmm.ENGINE_MODEL='" + engModel + "' and p.part_type='" + compType + "' order by p.np4,pmm.part_no");//
                String query1 = ("SELECT pmm.part_no,p.p1,p.np4,p.p3 from CAT_PART(NOLOCK) p, CAT_PART_MODEL_MATRIX(NOLOCK) pmm  where p.part_no=pmm.part_no and pmm.ENGINE_MODEL='" + engModel + "' and p.part_type='" + compType + "' order by p.np4,pmm.part_no");
                stmt = conn.prepareStatement(query1);
                rs = stmt.executeQuery();
                while (rs.next())
                {
                    compAvailable.add(rs.getString(1));
                    compAvailable.add(rs.getString(2));
                    heading = rs.getString(3);
                    if (heading == null)
                    {
                        heading = "";
                    }
                    compAvailable.add(heading);
                    toolNumber = rs.getString(4);
                    if (toolNumber == null || toolNumber.equals(""))
                    {
                        toolNumber = "--";
                    }
                    compAvailable.add(toolNumber);
                }
                rs.close();
                stmt.close();


                //String aggregateLink = "";

                String tdData = "<a href='View_EngineSeries'>" + PageTemplate.ENGINE_SERIES.toUpperCase() + "</a> >> ";
                if ((engineSeries != null) && !engineSeries.equalsIgnoreCase("null"))
                {
                    tdData += "<a href='View_EngineModel?engineSeries=" + engineSeries + "'>" + engineSeries.toUpperCase() + "</a> >> ";
                }
                if ((engineModel != null) && !engineModel.equalsIgnoreCase("null"))
                {
                    tdData += "<a href=\"MainModels?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "\">" + engineModel.toUpperCase() + "</a> >> ";
                }
                if ((model != null) && !model.equalsIgnoreCase("null"))
                {
                    tdData += "<a href=\"ViewAggregates?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "\">" + model.toUpperCase() + "</a> >> ";
                }
                else
                {
                    tdData += model.toUpperCase() + " >>";
                }
//                String tdData = "<a href='View_EngineSeries'>"+PageTemplate.ENGINE_SERIES+"</a> >> <a href='View_EngineModel?engineSeries=" + engineSeries + "'>" + engineSeries + "</a> >> "
//                        + "<a href=\"MainModels?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "\">" + engineModel + "</a> >> "
//                        + "<a href=\"ViewAggregates?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "\">" + model + "</a> >> ";


                int width = 970, height = 490;


                //tdData += aggregateLink;

                object_pageTemplate.pageLink(ps, width, height, tdData);

                if (compAvailable.size() == 0)
                {
                    message = "No  " + compType + " Exist For " + PageTemplate.MODEL_NO + " \"" + model + "\"";
                    ps.println(object_pageTemplate.tableHeader1(message, width, height));
                    //object_pageTemplate.ShowMsg(ps, "FAILURE", "No  " + compType + " Exists.", "NO", "", "", "", imagesURL);
                    return;
                }
                ps.println(object_pageTemplate.tableHeader(compType + "s Available for " + PageTemplate.MODEL_NO + " \"" + model + "\"", width, height));
//Groups Available for F-Code "F10003900"
                ps.println("<table width=100% border=0 valign=top>");

                ps.println("<tr> ");

                ps.println("<td align=left width=190 valign = top>");
                ps.println("<table cellspacing=1 cellpadding=3>");

                if (userFunctionalities.contains("37"))
                {
                    ps.println("<tr>");
                    ps.println("<td align  = left class=links_txt>");
                    ps.println("<IMG SRC=" + imagesURL + "" + PageTemplate.printIconimage + " border = 0></td><td class=links_txt>");
                    //ps.println("<a href='" + servletURL + "ModelPrintable?model=" + model + "' target=vc name = vc>");
                    ps.println("<a href='" + servletURL + "ModelPrintable?model=" + model + "' target=vc name = vc>");
                    ps.println("Print Catalogue");// BOM &amp; Image
                    ps.println("</a>");
                    ps.println("</td>");
                    ps.println("</tr>");
                   
                  /*  ps.println("<tr>");
                    ps.println("<td align  = left class=links_txt>");
                    ps.println("<IMG SRC=" + imagesURL + "" + PageTemplate.printIconimage + " border = 0></td><td class=links_txt>");
                    //ps.println("<a href='" + servletURL + "ModelPrintable?model=" + model + "' target=vc name = vc>");
                    ps.println("<a href='" + servletURL + "ModelPrintablePDF?model=" + model + "' target=vc name = vc>");
                    ps.println("Print Catalogue PDF");// BOM &amp; Image
                    ps.println("</a>");
                    ps.println("</td>");
                    ps.println("</tr>");
              
                   */

                }


//                if (userFunctionalities.contains("38"))
//                {
//                    ps.println("<tr>");
//                    ps.println("<td align  = left class=links_txt>");
//                    ps.println("<IMG SRC=" + imagesURL + "group_printer.gif border = 0>");
//                    ps.println("<a href='" + servletURL + "ModelPrintable_bom?model=" + model + "' target=vc name = vc>");
//                    ps.println("Print Catalogue BOM ");
//                    ps.println("</a>");
//                    ps.println("</td>");
//                    ps.println("</tr>");
//                }
//
//
//                if (userFunctionalities.contains("39"))
//                {
//                    ps.println("<tr>");
//                    ps.println("<td align  = left class=links_txt>");
//                    ps.println("<IMG SRC=" + imagesURL + "group_printer.gif border = 0>");
//                    ps.println("<a href='" + servletURL + "ModelPrintable_images?model=" + model + "' target=vc name = vc>");
//                    ps.println("Print Catalogue Image");
//                    ps.println("</a>");
//                    ps.println("</td>");
//                    ps.println("</tr>");
//                }


//                if (userFunctionalities.contains("41"))
//                {
//                    if (!(date_OR_serial.equals("serialNo")))
//                    {
//                        ps.println("<tr>");
//                        ps.println("<td align  = left class=links_txt>");
//                        ps.println("<IMG SRC=" + imagesURL + "MODIFICATION-ICON.jpg border = 0></td><td class=links_txt>");
//                        ps.println("<a href='" + servletURL + "ModelChmt?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "'>");
//                        ps.println(PageTemplate.ECN);
//                        ps.println("</a>");
//                        ps.println("</td>");
//                        ps.println("</tr>");
//                    }
//                }

                if (userFunctionalities.contains("42"))
                {
                    ps.println("<tr>");
                    ps.println("<td align  = left class=links_txt>");
                    ps.println("<IMG SRC=" + imagesURL + "excel_icon.jpg border = 0></td><td class=links_txt>");
                    ps.println("<a href='" + servletURL + "ModelExport_bom?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "' target=_blank>");
                    ps.println("Export " + PageTemplate.MODEL_NO + " BOM");
                    ps.println("</a>");
                    ps.println("</td>");
                    ps.println("</tr>");
                }


                ps.println("<tr>");
                ps.println("<td align  = left class=links_txt>");
                ps.println("<IMG SRC=" + imagesURL + "list.gif border = 0></td><td class=links_txt>");
                ps.println("<a href=\"" + servletURL + "ViewAggregates?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "\">");
                ps.println("View " + PageTemplate.AGGREGATE + "s");
                ps.println("</a>");
                ps.println("</td>");
                ps.println("</tr>");

                if (userFunctionalities.contains("48"))
                {
                    ps.println("<tr>");
                    ps.println("<td valign  = left class=links_txt>");
                    ps.println("<IMG SRC=" + imagesURL + "" + PageTemplate.kitIconimage + " border = 0></td><td class=links_txt>");
                    ps.println("<a href=\"" + servletURL + "compAvailable?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "&compType=Kit\" >");
                    ps.println("Kits Available");
                    ps.println("</a>");
                    ps.println("</td>");
                    ps.println("</tr>");
                }
                if (userFunctionalities.contains("409"))
                {
                    ps.println("<tr>");
                    ps.println("<td valign  = left class=links_txt>");
                    ps.println("<IMG SRC=" + imagesURL + "" + PageTemplate.lubeIconimage + " border = 0></td><td class=links_txt>");
                    ps.println("<a href=\"" + servletURL + "compAvailable?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "&compType=Lube\" >");
                    ps.println("Lubes Available");
                    ps.println("</a>");
                    ps.println("</td>");
                    ps.println("</tr>");
                }
                if (userFunctionalities.contains("408"))
                {
                    ps.println("<tr>");
                    ps.println("<td valign  = left class=links_txt>");
                    ps.println("<IMG SRC=" + imagesURL + "" + PageTemplate.toolIconimage + " border = 0></td><td class=links_txt>");
                    ps.println("<a href=\"" + servletURL + "compAvailable?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "&compType=Tool\" >");
                    ps.println("Tools Available");
                    ps.println("</a>");
                    ps.println("</td>");
                    ps.println("</tr>");
                }

                ps.println("</table>");

                ps.println("</td>");

                ps.println("<td valign = top align = left>");
                ps.println("<table width=99% border=0 cellspacing=1 cellpadding=2 bordercolor = #CCCCCC>");

                ps.println("<tr bgcolor = #003399 class=heading1> ");
                ps.println("<td width = 10% align = center>");
                ps.println("S. NO.");
                ps.println("</td>");

                ps.println("<td width = 25% class=heading1>");
                ps.println(compType.toUpperCase());
                ps.println("</td>");

                if (compType.equalsIgnoreCase("Tool"))
                {
                    ps.println("<td width = 45% class=heading1>");
                    ps.println("DESCRIPTION");
                    ps.println("</td>");
                    ps.println("<td width = 20% class=heading1>");
                    ps.println("TOOL NO.");
                    ps.println("</td>");
                }
                else
                {
                    ps.println("<td width = 65% class=heading1>");
                    ps.println("DESCRIPTION");
                    ps.println("</td>");
                }

                ps.println("</tr>");

                ps.println("</table>");

                ps.println("<div class=\"maindiv\" STYLE=\" overflow-X:hidden; overflow-y: scroll; height:" + tableHeight_1 + ";\">");
                ps.println("<table width=100% border=0 cellspacing=1 cellpadding=2 bordercolor = #CCCCCC>");

                int counter = 1;
                int l = 0;

                String cssClass = null;


                for (int c = 0; c < compAvailable.size(); c += 4)
                {



                    if (l % 2 == 0)
                    {
                        cssClass = "links_txt";//links_txt_Alt
                    }
                    else
                    {
                        cssClass = "links_txt";
                    }
                    l++;

                    if ((!"".equals(compAvailable.get(c + 2))) && (counter == 1 || !compAvailable.get(c + 2).equals(compTypeHeading)))
                    {
                        compTypeHeading = compAvailable.get(c + 2);
                        ps.println("<tr><td colspan=3 align=left  class=links_txt><b>" + compTypeHeading.toUpperCase() + "</td></tr>");
                    }

                    ps.println("<tr >");
                    ps.println("<td  align = center width = 10%  class=" + cssClass + ">");
                    ps.println(counter);
                    ps.println("</td>");

                    ps.println("<td width = 25% class=" + cssClass + ">");
                    ps.println("<a href=javascript:MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + compAvailable.get(c) + "','DETAILS','scrollbars=yes,width=700,height=605')>");
                    ps.println(compAvailable.get(c));
                    ps.println("</a>");
                    ps.println("</td>");

                    if (compType.equalsIgnoreCase("Tool"))
                    {
                        ps.println("<td  width=45% class=" + cssClass + ">");
                        ps.println("<a href=javascript:MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + compAvailable.get(c) + "','DETAILS','scrollbars=yes,width=700,height=605')>");
                        ps.println(compAvailable.get(c + 1));
                        ps.println("</a>");
                        ps.println("</td>");
                        ps.println("<td  width=20% class=" + cssClass + " align=left>");
                        ps.println("<a href=javascript:MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + compAvailable.get(c) + "','DETAILS','scrollbars=yes,width=700,height=605')>");
                        ps.println(compAvailable.get(c + 3));
                        ps.println("</a>");
                        ps.println("</td>");
                    }
                    else
                    {
                        ps.println("<td  width=65% class=" + cssClass + ">");
                        ps.println("<a href=javascript:MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + compAvailable.get(c) + "','DETAILS','scrollbars=yes,width=700,height=605')>");
                        ps.println(compAvailable.get(c + 1));
                        ps.println("</a>");
                        ps.println("</td>");
                    }
                    ps.println("</tr> ");

                    counter++;
                }

                ps.println("</table> ");
                ps.println("</div>");
                ps.println("</td>");
                ps.println("</tr>");
                ps.println("</table>");
                ps.println(object_pageTemplate.tableFooter());
            }
            else
            {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);

            }
        }
        catch (Exception e)
        {
            ps.println(e);
            e.printStackTrace();
        }
        finally
        {
            ps.close();
            wps.close();
        }
    }
}
