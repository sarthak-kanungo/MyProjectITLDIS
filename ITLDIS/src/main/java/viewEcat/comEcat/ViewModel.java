package viewEcat.comEcat;

/*
File Name: ViewModel.java
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
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ViewModel extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        String message = null;
        //String engineSeries=null;
        //String engineModel=null;
        try {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            String getType = (String) session.getValue("user_type");
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


            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////


            if (session_id.equals(getSession)) {
                Connection conn = holder.getConnection();

                //Statement stmt;
                PreparedStatement stmt = null;
                ResultSet rs;

                int tableHeight_1 = 375;


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
                // System.out.println("sqlQuery..."+sqlQuery);
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
                ps.println("var win=null;");
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
                ps.println("try{if(win!=null && !win.closed){win.close();}}catch(e){}");
                ps.println("			win=window.open(theURL,winName,features);");
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
                String tdData = "<a href='View_EngineSeries'>" + ComUtils.getLanguageTranslation("label.catalogue.product", session).toUpperCase() + "</a> >> ";
                if ((engineSeries != null) && !engineSeries.equalsIgnoreCase("null")) {
                    tdData += "<a href='View_EngineModel?engineSeries=" + engineSeries + "'>" + engineSeries.toUpperCase() + "</a> >> ";
                }
                if ((engineModel != null) && !engineModel.equalsIgnoreCase("null")) {
                    tdData += "<a href=\"MainModels?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "\">" + engineModel.toUpperCase() + "</a> >> ";
                }
                if ((model != null) && !model.equalsIgnoreCase("null")) {
                    tdData += "<a href=\"ViewAggregates?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "\">" + model.toUpperCase() + "</a> >> ";
                }

                if (aggregate != null && !aggregate.equals("") && !aggregate.equalsIgnoreCase("null")) {
                    aggregateLink = "" + aggregate.toUpperCase() + " >>";
                }

                int width = 970, height = 450;

                tdData += aggregateLink;

                object_pageTemplate.pageLink(ps, width, height, tdData);

                if (groupTypes.size() == 0) {
                    ps.println(object_pageTemplate.tableHeader1(ComUtils.getLanguageTranslation("label.catalogue.noGroupExist", session), width, height));
                    //object_pageTemplate.ShowMsg(ps, "FAILURE", "No Table Exists.", "NO", "", "", "", imagesURL);
                    return;
                }

                ps.println("<td align=right width=190 valign = top>");
                ps.println("<table cellspacing=1 cellpadding=3 align=right>");

                /* ps.println("<tr>");
                ps.println("<td align  = left>");
                ps.println("<IMG SRC=" + modelImage + model + ".jpg width=150 height=120 border = 1>");
                ps.println("</td>");
                ps.println("</tr>");
                 */
                ps.println("<tr>");
                if (userFunctionalities.contains("37")) {
                    ps.println("<td align  = left class=links_txt valign = top>");
                    ps.println("<a href='" + servletURL + "ModelPrintable?model=" + model + "' target=vc name = vc>");
                    ps.println("<IMG SRC=" + imagesURL + "" + PageTemplate.printIconimage + " border = 0 alt=\""+ComUtils.getLanguageTranslation("label.catalogue.printCatalogue", session)+"\" style=\"z-index:0;\">");
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
//
//                    }
//                }

                if (userFunctionalities.contains("42")) {
                    ps.println("<td align  = left class=links_txt valign = top>");
                    ps.println("<a href='" + servletURL + "ModelExport_bom?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "' target=_blank>");
                    ps.println("<IMG SRC=" + imagesURL + "excel_icon.jpg border = 0 width=18 height=18 alt=\""+ComUtils.getLanguageTranslation("label.catalogue.exportVariantBOM", session)+"\" style=\"z-index:0;\">");
                    ps.println("</a>");
                    ps.println("</td>");
                }


                if (userFunctionalities.contains("231")) {
                    
                        ps.println("<td align  = left class=links_txt valign = top>");

                        if ((aggregate == null) || aggregate.equals("") || (aggregate.equalsIgnoreCase("null"))) {
                            ps.println("<a href=\"" + servletURL + "ViewModel_PictureView?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "&highGroup=" + highGroup + "&aggregate=" + aggregate.replaceAll("&", "@@") + "\">");
                        } else {
                            ps.println("<a href=\"" + servletURL + "ViewModel_pictureView1?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "&highGroup=" + highGroup + "&aggregate=" + aggregate.replaceAll("&", "@@") + "\">");
                        }
                        ps.println("<IMG SRC=" + imagesURL + "thumb.gif border = 0 width=18 height=18 alt=\"" + ComUtils.getLanguageTranslation("label.catalogue.groupPictureView", session) + "\" style=\"z-index:0;\">");
                        ps.println("</a>");
                        ps.println("</td>");
                    
                }
////
//                ps.println("<td align  = left class=links_txt>");
//                ps.println("<a href='" + servletURL + "ViewAggregates?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "'>");
//                ps.println("<IMG SRC=" + imagesURL + "thumb.gif border = 0 alt='View " + PageTemplate.AGGREGATE + "s'>");
//                ps.println("</a>");
//                ps.println("</td>");


                if (userFunctionalities.contains("48")) {
                    ps.println("<td valign  = left class=links_txt valign = top>");
                    ps.println("<a href=\"" + servletURL + "compAvailable?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "&compType=Kit\" >");
                    ps.println("<IMG SRC=" + imagesURL + "" + PageTemplate.kitIconimage + " border = 0 alt=\""+ComUtils.getLanguageTranslation("label.catalogue.kitsAvialable", session)+"\" style=\"z-index:0;\">");
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
//                     ps.println("<IMG SRC=" + imagesURL + "" + PageTemplate.toolIconimage + " border = 0 alt='Tools Available'>");
//                    ps.println("</a>");
//                    ps.println("</td>");
//                }
                ps.println("</tr>");
                ps.println("</table>");

                ps.println("</td>");
                ps.println("<br>");

                ps.println(object_pageTemplate.tableHeader(ComUtils.getLanguageTranslation("label.catalogue.groupsAvailable", session), width, height));

                ps.println("<table width=100% border=0 valign=top>");

                ps.println("<tr> ");

                ps.println("<td valign = top align = left>");
                ps.println("<table width=97% border=0 cellspacing=1 cellpadding=2 bordercolor = #CCCCCC>");

                ps.println("<tr bgcolor = #003399 class=heading1> ");
                ps.println("<td width = 10% align = center>");
                ps.println(ComUtils.getLanguageTranslation("label.catalogue.sNo", session).toUpperCase());
                ps.println("</td>");

                ps.println("<td width = 50% class=heading1>");
                ps.println(ComUtils.getLanguageTranslation("label.catalogue.description", session).toUpperCase());
                ps.println("</td>");

//                ps.println("<td width = 40% class=heading1>");
//                ps.println("" + PageTemplate.GROUP.toUpperCase() + " NO.");
//                ps.println("</td>");

                ps.println("</tr>");

                ps.println("</table>");

                ps.println("<div  STYLE=\" overflow-X:hidden; overflow-y: scroll; height:" + tableHeight_1 + ";\">");
                ps.println("<table width=99% border=0 cellspacing=1 cellpadding=2 bordercolor = #CCCCCC>");


                int counter = 1;


                String temp_3 = "";
                String temp_p1 = "";
                String groupDescription = "";
                Vector groupArray = new Vector();
                int groupArrayCounter = 0;

                String cssClass = null;
                int l = 0;


                for (int i = 0; i < groupTypes.size(); i++) {



                    groupArray = new Vector();
                    groupArrayCounter = 0;
                    //rs = stmt.executeQuery("SELECT MG.GROUP_NO,MG.MAP_NAME, GKD.P1 FROM " + tableName_MG + " MG, CAT_GROUP_KIT_DETAIL(NOLOCK) GKD WHERE MG.MODEL_NO = '" + model + "' AND MG.GROUP_TYPE = '" + groupTypes.elementAt(i) + "'  AND MG.GROUP_NO = GKD.GRP_KIT_NO ORDER BY GKD.GK_TYPE,GKD.P1");
                    String query1 = ("SELECT MG.GROUP_NO,MG.MAP_NAME, GKD.P1 FROM " + tableName_MG + " MG, CAT_GROUP_KIT_DETAIL(NOLOCK) GKD WHERE MG.MODEL_NO = '" + model + "' AND MG.GROUP_TYPE = '" + groupTypes.elementAt(i) + "'  AND MG.GROUP_NO = GKD.GRP_KIT_NO ORDER BY GKD.GK_TYPE,GKD.P1");
                    stmt = conn.prepareStatement(query1);
                    rs = stmt.executeQuery();
                    //System.out.println("SELECT MG.GROUP_NO,MG.MAP_NAME, GKD.P1 FROM " + tableName_MG + " MG, GROUP_KIT_DETAIL GKD WHERE MG.MODEL_NO = '" + model + "' AND MG.GROUP_TYPE = '" + groupTypes.elementAt(i) + "'  AND MG.GROUP_NO = GKD.GRP_KIT_NO ORDER BY GKD.GK_TYPE,GKD.P1");
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
                        ps.println("<tr><td colspan=3 align=left  class=links_txt><b>" + ("" + groupTypes.elementAt(i)).toUpperCase() + "</td></tr>");
                    }



                    for (int k = 0; k < groupArray.size(); k = k + 3) {

                        if (!(highGroup == null) && (groupArray.elementAt(k + 1).equals(highGroup))) {
                            ps.println("<tr bgcolor='#FFFF99'>");
                        } else {
                            ps.println("<tr >");
                        }

                        if (l % 2 == 0) {
                            cssClass = "links_txt";//links_txt_Alt
                        } else {
                            cssClass = "links_txt";
                        }
                        l++;


                        ps.println("<td  align = center width = 10%  class=" + cssClass + ">");
                        ps.println(counter);
                        ps.println("</td>");

                        ps.println("<td width = 50% class=" + cssClass + ">");
                        //ps.println("<a href='" + servletURL + "ViewGroupImage_BOM?group=" + groupArray.elementAt(k + 1) + "&model=" + model + "&partSearch=" + partSearch + "'>");
                        ps.println("<a href=\"" + servletURL + "ViewGroupImage_BOM?aggregate=" + aggregate.replaceAll("&", "@@") + "&engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&group=" + groupArray.elementAt(k + 1) + "&model=" + model + "&partSearch=" + partSearch + "\" >");

                        //ps.println("<a href=\"" + servletURL + "FrameOpener?aggregate="+aggregate+"&applicationType="+applicationType+"&engineModel=" + engineModel+ "&engineSeries=" + engineSeries + "&group=" + groupArray.elementAt(k + 1) + "&model=" + model + "\">");
                        if (!(highGroup == null) && (groupArray.elementAt(k + 1).equals(highGroup))) {
                            ps.println("<b>");
                        } else {
                        }

                        ps.println(groupArray.elementAt(k + 2));
                        ps.println("</a>");
                        ps.println("</td>");

//                        ps.println("<td width = 50% class=" + cssClass + ">");
//                        //ps.println("<a href='" + servletURL + "ViewGroupImage_BOM?group=" + groupArray.elementAt(k + 1) + "&model=" + model + "&partSearch=" + partSearch + "'>");
//                        ps.println("<a href=\"" + servletURL + "ViewGroupImage_BOM?aggregate=" + aggregate + "&engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&group=" + groupArray.elementAt(k + 1) + "&model=" + model + "&partSearch=" + partSearch + "\">");
//                        if (!(highGroup == null) && (groupArray.elementAt(k + 1).equals(highGroup))) {
//                            ps.println("<b>");
//                        } else {
//                        }
//
//                        ps.println(groupArray.elementAt(k));
//
//                        ps.println("</a>");
//                        ps.println("</td>");

                        ps.println("</tr> ");

                        counter++;
                    }

                    ps.println("<tr>");
                    ps.println("<td colspan = 3>");
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
                object_pageTemplate.ShowMsg(ps, "FAILURE", ComUtils.getLanguageTranslation("label.catalogue.sessionExpired", session), "YES", ComUtils.getLanguageTranslation("label.catalogue.loginAgain", session), "Index", "", imagesURL);
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
