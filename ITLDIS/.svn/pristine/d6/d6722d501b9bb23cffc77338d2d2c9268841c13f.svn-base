package viewEcat.comEcat;

/*
 File Name: ViewModel_PictureView.java
 PURPOSE: It is used to show the JPG picture of groups in a particular model.
 HISTORY:
 DATE		BUILD	AUTHOR			MODIFICATIONS
 NA			v3.4	Deepak Mangal	$$0 Created
 */
import java.io.File;
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

public class ViewModel_PictureView extends HttpServlet {

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        String message = null;
        try {
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
            String groupJPG = "";
            groupJPG = object_pageTemplate.groupJPG();

            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();

            String modelImage = "";
            modelImage = object_pageTemplate.modelImage();



            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////


            if (session_id.equals(getSession)) {
                Connection conn = holder.getConnection();

                //Statement stmt;
                PreparedStatement stmt = null;
                ResultSet rs;

                int tableWidth1 = 800;
                int tableWidth = 615;
                int tableHeight_1 = 390;

                if (screenWidth.equals("800")) {
                    tableWidth = 560;
                    tableHeight_1 = 240;
                }

                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));

                /*########################################*/

                //stmt = conn.createStatement();

                Vector groupTypes = new Vector();

                String model = req.getParameter("model");
                String engineSeries = req.getParameter("engineSeries");
                String engineModel = req.getParameter("engineModel");
                String aggregate = req.getParameter("aggregate");
                String partSearch = req.getParameter("partSearch");

                if (!((aggregate == null) || aggregate.equals("") || aggregate.equalsIgnoreCase("null"))) {
                    aggregate = aggregate.replaceAll("_", " ");
                    aggregate = aggregate.replaceAll("@@", "&");

                } else {
                    aggregate = "";

                }

                // String modelDescription = "";
                String modelStatus = "";

                String tableName_MG = "CAT_MODEL_GROUP";

               // rs = stmt.executeQuery("SELECT DESCRIPTION,STATUS FROM CAT_MODELS(NOLOCK) WHERE MODEL_NO = '" + model + "'");
                String query = ("SELECT DESCRIPTION,STATUS FROM CAT_MODELS(NOLOCK) WHERE MODEL_NO = '" + model + "'");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    //  modelDescription = rs.getString(1);
                    modelStatus = rs.getString(2);
                }
                rs.close();

                if (modelStatus.equals("INCOMPLETE")) {
                    ps.println("<script language=JavaScript type=text/JavaScript> alert('All the Groups of this Model have not been attached.\\n You will be viewing an Incomplete Model.');</script>");
                }


                String sqlQuery = "";

                if ((aggregate == null) || aggregate.equals("") || (aggregate.equalsIgnoreCase("null"))) {
                    sqlQuery = "SELECT DISTINCT GROUP_TYPE, TYPE_LEVEL FROM " + tableName_MG + " WHERE MODEL_NO = '" + model + "'  ORDER BY TYPE_LEVEL, GROUP_TYPE";
                
                } else {
                    sqlQuery = "SELECT DISTINCT GROUP_TYPE, TYPE_LEVEL FROM " + tableName_MG + " WHERE MODEL_NO = '" + model + "' and GROUP_TYPE='" + aggregate + "'  ORDER BY TYPE_LEVEL, GROUP_TYPE";
                }
                  stmt = conn.prepareStatement(sqlQuery);
                  rs = stmt.executeQuery();
                //rs = stmt.executeQuery(sqlQuery);
                if (rs.next()) {
                    String temp_1 = "";
                    do {
                        temp_1 = rs.getString(1);
                        if (!groupTypes.contains(temp_1)) {
                            groupTypes.addElement(temp_1);
                        }
                    } while (rs.next());
                }
                rs.close();


                String highGroup = req.getParameter("highGroup");



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

                String aggregateLink = "";
                String tdData = "<a href='View_EngineSeries'>" + PageTemplate.ENGINE_SERIES.toUpperCase() + "</a> >> ";
                if ((engineSeries != null) && !engineSeries.equalsIgnoreCase("null")) {
                    tdData += "<a href='View_EngineModel?engineSeries=" + engineSeries + "'>" + engineSeries.toUpperCase() + "</a> >> ";
                }
                if ((engineModel != null) && !engineModel.equalsIgnoreCase("null")) {
                    tdData += "<a href=\"MainModels?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "\">" + engineModel.toUpperCase() + "</a> >> ";
                }
                if ((model != null) && !model.equalsIgnoreCase("null")) {
                    tdData += "<a href=\"ViewAggregates?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "\">" + model.toUpperCase() + "</a> >> ";
                }

//                String tdData = "<a href='View_EngineSeries'>"+PageTemplate.ENGINE_SERIES+"</a> >> <a href='View_EngineModel?engineSeries=" + engineSeries + "'>" + engineSeries + "</a> >> "
//                        + "<a href=\"MainModels?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "\">" + engineModel + "</a> >> "
//                        + "<a href=\"ViewAggregates?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "\">" + model + "</a> >> ";

                if (aggregate != null && !aggregate.equals("") && !aggregate.equalsIgnoreCase("null")) {
                    aggregateLink = "" + aggregate.toUpperCase() + " >>";
                }

                int width = 970, height = 450;

                tdData += aggregateLink;

                object_pageTemplate.pageLink(ps, width, height, tdData);


                if (groupTypes.size() == 0) {
                    message = "No " + PageTemplate.GROUP + " Exist";
                    ps.println(object_pageTemplate.tableHeader1(message, width, height));
                    //object_pageTemplate.ShowMsg(ps, "FAILURE", "No Table Exists.", "NO", "", "", "", imagesURL);
                    return;
                }





                ps.println("<td align=right width=190 valign = top>");
                ps.println("<table cellspacing=1 cellpadding=3 align=right>");
                ps.println("<tr>");
                if (userFunctionalities.contains("37")) {
                    ps.println("<td align  = left class=links_txt valign = top>");
                    ps.println("<a href='" + servletURL + "ModelPrintable?model=" + model + "' target=vc name = vc>");
                    ps.println("<IMG SRC=" + imagesURL + "" + PageTemplate.printIconimage + " border = 0 alt='Print Catalogue' style=\"z-index:0;\">");
                    ps.println("</a>");
                    ps.println("</td>");
                }
//                if (userFunctionalities.contains("41")) {
//                    if (!(date_OR_serial.equals("serialNo"))) {
//                        ps.println("<td align  = left class=links_txt>");
//                        ps.println("<a href='" + servletURL + "ModelChmt?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "'>");
//                        ps.println("<IMG SRC=" + imagesURL + "MODIFICATION-ICON.jpg border = 0 alt='" + PageTemplate.ECN + "'>");
//                        ps.println("</a>");
//                        ps.println("</td>");
//                    }
//                }
                if (userFunctionalities.contains("42")) {
                    ps.println("<td align  = left class=links_txt valign = top>");
                    ps.println("<a href='" + servletURL + "ModelExport_bom?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "' target=_blank>");
                    ps.println("<IMG SRC=" + imagesURL + "excel_icon.jpg border = 0 width=18 height=18 alt='Export " + PageTemplate.MODEL_NO + " BOM' style=\"z-index:0;\">");
                    ps.println("</a>");
                    ps.println("</td>");
                }

                if (userFunctionalities.contains("230")) {
                    ps.println("<td align  = left class=links_txt valign = top>");
                    ps.println("<a href=\"" + servletURL + "ViewModel?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "&highGroup=" + highGroup + "&aggregate=" + aggregate.replaceAll("&", "@@") + "\">");
                    ps.println("<IMG SRC=" + imagesURL + "list.gif border = 0 width=18 height=20 alt='" + PageTemplate.GROUP + "s List View' style=\"z-index:0;\">");
                    ps.println("</a>");
                    ps.println("</td>");
                }
//
//                ps.println("<td align  = left class=links_txt>");
//                ps.println("<a href='" + servletURL + "ViewAggregates?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "'>");
//                ps.println("<IMG SRC=" + imagesURL + "thumb.gif border = 0 alt='View " + PageTemplate.AGGREGATE + "s'>");
//                ps.println("</a>");
//                ps.println("</td>");

                if (userFunctionalities.contains("48")) {
                    ps.println("<td valign  = left class=links_txt valign = top>");
                    ps.println("<a href=\"" + servletURL + "compAvailable?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "&compType=Kit\" >");
                    ps.println("<IMG SRC=" + imagesURL + "" + PageTemplate.kitIconimage + " border = 0 alt='Kits Available' style=\"z-index:0;\">");
                    ps.println("</a>");
                    ps.println("</td>");
                }
//                if (userFunctionalities.contains("409")) {
//                    ps.println("<td valign  = left class=links_txt>");
//                    ps.println("<a href=\"" + servletURL + "compAvailable?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "&compType=Lube\" >");
//                    ps.println("<IMG SRC=" + imagesURL + "" + PageTemplate.lubeIconimage + " border = 0 alt='Lubes Available'>");
//                    ps.println("</a>");
//                    ps.println("</td>");
//                }
//                if (userFunctionalities.contains("408")) {
//                    ps.println("<td valign  = left class=links_txt>");
//                    ps.println("<a href=\"" + servletURL + "compAvailable?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "&compType=Tool\" >");
//                    ps.println("<IMG SRC=" + imagesURL + "" + PageTemplate.toolIconimage + " border = 0 alt='Tools Available'>");
//                    ps.println("</a>");
//                    ps.println("</td>");
//
//                }
                ps.println("</tr>");
                ps.println("</table>");

                ps.println("</td>");

                ps.println("<td>");
                ps.println("<br>");
                ps.println(object_pageTemplate.tableHeader(PageTemplate.GROUP + "s Available", width, height));
                ps.println("<div class=\"maindiv\" STYLE=\" overflow-X:hidden; overflow-y: scroll; height:" + tableHeight_1 + ";\">");
                ps.println("<table width=100% border=0 valign=top>");

                ps.println("<tr> ");

                ps.println("<td valign = top align = left>");
                ps.println("<table width=100% border=0 cellspacing=1 cellpadding=2 bordercolor = #CCCCCC>");

                /*
                 ps.println("<tr bgcolor = #003399 class=heading1> ");

                 ps.println("<td width = 55% colspan = 2 class=heading1>");
                 ps.println("Description");
                 ps.println("</td>");

                 ps.println("<td width = 50% colspan = 2 class=heading1>");
                 ps.println("Description");
                 ps.println("</td>");

                 ps.println("</tr>");
                 */
                //ps.println("</table>");


                //ps.println("<table  width=" + tableWidth + " border=0 cellspacing=1 cellpadding=2 bordercolor = #CCCCCC>");


                int counter = 1;
//                Vector groupArray = new Vector();
//                int groupArrayCounter = 0;

                String temp_3 = "";
                String temp_p1 = "";
                String groupDescription = "";
                Vector groupArray = new Vector();
                int groupArrayCounter = 0;
                int cntr = 0;

                String jpg_path_2 = ".jpg";
                String jpg_full_path = "";
                int jpgExists = 0;

                aggregate = aggregate.replaceAll("&", "@@");

                for (int i = 0; i < groupTypes.size(); i++) {
                    groupArray = new Vector();
                    groupArrayCounter = 0;
                    //rs = stmt.executeQuery("SELECT MG.GROUP_NO,GM.MAP_NAME FROM " + tableName_MG + " MG," + tableName_MG_MAP + " GM, GROUP_KIT_DETAIL GKD WHERE MG.MODEL_NO = '" + model + "' AND MG.GROUP_TYPE = '" + groupTypes.elementAt(i) + "' AND GM.MODEL_NO = '" + model + "' AND GM.GRP_KIT_NO = MG.GROUP_NO AND GM.GRP_KIT_NO = GKD.GRP_KIT_NO AND MG.REV_NO = " + revNo + " AND MG.REV_NO = GM.REV_NO ORDER BY GKD.GK_TYPE,GKD.P1");
                    //rs = stmt.executeQuery("SELECT MG.GROUP_NO,MG.MAP_NAME, GKD.P1 FROM " + tableName_MG + " MG, CAT_GROUP_KIT_DETAIL GKD WHERE MG.MODEL_NO = '" + model + "' AND MG.GROUP_TYPE = '" + groupTypes.elementAt(i) + "'  AND MG.GROUP_NO = GKD.GRP_KIT_NO ORDER BY GKD.GK_TYPE,GKD.P1");
                    String query1 = ("SELECT MG.GROUP_NO,MG.MAP_NAME, GKD.P1 FROM " + tableName_MG + " MG, CAT_GROUP_KIT_DETAIL(NOLOCK) GKD WHERE MG.MODEL_NO = '" + model + "' AND MG.GROUP_TYPE = '" + groupTypes.elementAt(i) + "'  AND MG.GROUP_NO = GKD.GRP_KIT_NO ORDER BY GKD.GK_TYPE,GKD.P1");
                    stmt = conn.prepareStatement(query1);
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        //  boolean insertVal = true;
                        do {
                            temp_3 = rs.getString(1);
                            temp_p1 = rs.getString(2);
                            groupDescription = rs.getString(3);

                            if (!groupArray.contains(temp_3)) {
                                groupArray.add(temp_p1);
                                groupArray.add(temp_3);
                                groupArray.add(groupDescription);
                                groupArrayCounter++;
                            }
                        } while (rs.next());
                        session.setAttribute("groupDescription", groupArray);
                    }
                    rs.close();

                    //////////////////////////////////////////////////////////

                    if (groupArrayCounter != 0) {
                        ps.println("<tr><td colspan=16 align=left class=links_txt valign=top width=100% ><b>" + ("" + groupTypes.elementAt(i)).toUpperCase() + "</td></tr>");
                    }

                    jpg_full_path = "";
                    jpgExists = 0;

                    ps.println("<tr>");

                    int count = 0;
                    for (int k = 0; k < groupArray.size(); k = k + 3) {

                        jpgExists = 0;
                        String jpg_path_1 = ecatPATH + "/dealer/ecat_print/group_jpg/";

                        jpg_full_path = jpg_path_1 + groupArray.elementAt(k + 1) + jpg_path_2;
                        File jpg_File = new File(jpg_full_path);
                        if (jpg_File.isFile()) {
                            jpgExists = 1;
                        }

                        if (!(highGroup == null) && (groupArray.elementAt(k + 1).equals(highGroup))) {
                            ps.println("<td bgcolor='#FFFF99' align=center width=25% colspan=4 valign=top>");
                        } else {
                            ps.println("<td align=center width=25% colspan=4 valign=top>");
                        }

                        ps.println("<table border=0 cellspacing=1 cellpadding=1 width = 100% valign=top align=center>");

                        /*
                         if (counter > 9) {
                         ps.println("<tr>"); //<td valign=top width=5% class=links_txt>" + counter + ".</td>
                         } else {
                         ps.println("<tr>");//<td valign=top width=5% class=links_txt>&nbsp;&nbsp;" + counter + ".
                         }
                         */
                        ps.println("<tr>");
                        ps.println("<td width=100% class=links_txt4 align=center valign=top><a   href=\"" + servletURL + "ViewGroupImage_BOM?aggregate=" + aggregate + "&engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&group=" + groupArray.elementAt(k + 1) + "&model=" + model + "&type=NP&partSearch=" + partSearch + "\">");

                        //   ps.println("			<embed id=\"imghes\" src=\"/ITL-DIS/svg/" + groupArray.elementAt(k + 1) + ".svg\" class=\"electricalzoom\" type=\"image/svg+xml\" height=\"105\" width=\"105\">");
                        //   ps.println("			</embed>");
                        if (jpgExists == 1) {
                            ps.println("<IMG style=\"border-color:black;\" SRC='" + groupJPG + groupArray.elementAt(k + 1) + ".JPG' border = 1 bordercolor=#000000 width=105 height=105 >");
                        } else {
                            ps.println("<IMG style=\"border-color:black;\" SRC=" + groupJPG + "group_image.jpg border = 1 bordercolor=#000000 width=105 height=105 >");
                        }

                        ps.println(("</br><span class=links_txt4 style=\"word-wrap:normal\">" + groupArray.elementAt(k + 2)) + "</span>");
                        ps.println("</a>");
                        ps.println("</span></td>");
                        // ps.println("<td width=95% valign=top><table width=100% border=1 cellspacing=1 cellpadding=1><tr><td valign=top width=100% class=links_txt>");
//                        ps.println("<a href=\"" + servletURL + "ViewGroupImage_BOM?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&group=" + groupArray.elementAt(k + 1) + "&model=" + model + "&type=NP&partSearch=" + partSearch + "\">");
//                        if (!(highGroup == null) && (groupArray.elementAt(k + 1).equals(highGroup))) {
//                            ps.println("<b>");
//                        } else {
//                            ps.println("");
//                        }
//                        ps.println(groupArray.elementAt(k));
                        //ps.println("</a>");
                        //ps.println("</td></tr>"); //<tr><td valign=top  width=100% class=links_txt>
//                        ps.println("<a href=\"" + servletURL + "ViewGroupImage_BOM?aggregate=" + aggregate + "&engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&group=" + groupArray.elementAt(k + 1) + "&model=" + model + "&type=NP&partSearch=" + partSearch + "\">");
//                        if (!(highGroup == null) && (groupArray.elementAt(k + 1).equals(highGroup))) {
//                            ps.println("<b>");
//                        } else {
//                            ps.println("");
//                        }
//
//                        ps.println(groupArray.elementAt(k + 2));

                        //ps.println("</a></td></tr></table></td></tr>");
                        // ps.println("</table></td>");
                        ps.println("</tr></table>");

                        counter++;
                        cntr++;
                        count++;

                        if (count == 4) {
                            count = 0;
                            ps.println("</td>");
                            ps.println("</tr>");
                            ps.println("<tr>");
                        }
                    }


                    if ((cntr % 2) == 0) {
                        ps.println("</tr>");
                    }

                    ps.println("<tr>");
                    ps.println("<td colspan = 16>");
                    ps.println("	 <hr color = #cccccc>");
                    ps.println("</td>");
                    ps.println("</tr>");
                }

                ps.println("</table> ");
                ps.println("</div>");

                ps.println("</td>");
                ps.println("</tr>");



                ps.println("</table>");

                ps.println(object_pageTemplate.tableFooter());

                stmt.close();
            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
        } catch (Exception e) {
            ps.println(e);
            e.printStackTrace();
        } finally {
            ps.close();
            wps.close();
        }
    }
}
