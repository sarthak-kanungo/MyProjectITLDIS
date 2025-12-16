package viewEcat.comEcat;

/*
File Name: ViewAggregates.java
PURPOSE: File To view all aggregates in a model.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
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

public class ViewAggregates1 extends HttpServlet {

    @Override
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
            String getType = (String) session.getValue("user_type");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String screenWidth = (String) session.getValue("screenWidth");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");

            String date_OR_serial = (String) session.getValue("date_OR_serial");

            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////


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



            if (session_id.equals(getSession)) {
                Connection conn = holder.getConnection();
                //Statement stmt;
                PreparedStatement stmt = null;
                ResultSet rs;

                int tableHeight_1 = 375; //380;

                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));

                /*########################################*/

               // stmt = conn.createStatement();

                Vector groupTypes = new Vector();

                String model = req.getParameter("model");
                String engineSeries = req.getParameter("engineSeries");
                String engineModel = req.getParameter("engineModel");


                // String modelDescription = "";
                String modelStatus = "";

                String tableName_MG = "CAT_MODEL_GROUP";


                //rs = stmt.executeQuery("SELECT DESCRIPTION,STATUS FROM CAT_MODELS(NOLOCK) WHERE MODEL_NO = '" + model + "'");
                String query = ("SELECT DESCRIPTION,STATUS FROM CAT_MODELS(NOLOCK) WHERE MODEL_NO = '" + model + "'");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    //  modelDescription = rs.getString(1);
                    modelStatus = rs.getString(2);
                }
                rs.close();

                if (modelStatus.equals("INCOMPLETE")) {
                    ps.println("<script language=JavaScript type=text/JavaScript> alert('All the " + PageTemplate.AGGREGATE + "s of this Model have not been attached.\\n You will be viewing an Incomplete Model.');</script>");
                }

                // rs = stmt.executeQuery("SELECT DISTINCT GROUP_TYPE, TYPE_LEVEL FROM " + tableName_MG + " WHERE MODEL_NO = '" + model + "' AND REV_NO=" + revNo + "  ORDER BY TYPE_LEVEL, GROUP_TYPE");
                //rs = stmt.executeQuery("SELECT DISTINCT GROUP_TYPE, TYPE_LEVEL FROM " + tableName_MG + " WHERE MODEL_NO = '" + model + "' ORDER BY TYPE_LEVEL, GROUP_TYPE");
                String query1 = ("SELECT DISTINCT GROUP_TYPE, TYPE_LEVEL FROM " + tableName_MG + " WHERE MODEL_NO = '" + model + "' ORDER BY TYPE_LEVEL, GROUP_TYPE");
                stmt = conn.prepareStatement(query1);
                rs = stmt.executeQuery();
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
                stmt.close();



                ps.println("<html><head>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");

                ps.println("</head>");
                ps.println("<body topmargin=0  marginheight=0 leftmargin=0  marginwidth=0>");

                int width = 970, height = 450;

//                String tdData = "<a href='View_EngineSeries'>"+PageTemplate.ENGINE_SERIES+"</a> >> <a href='View_EngineModel?engineSeries=" + engineSeries + "'>" + engineSeries + "</a> >> "
//                        + "<a href=\"MainModels?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "\">" + engineModel + "</a> >> "
//                        + model + " >>";
                String tdData = "<a href='View_EngineSeries'>" + ComUtils.getLanguageTranslation("label.catalogue.product", session).toUpperCase() + "</a> >> ";
                if ((engineSeries != null) && !engineSeries.equalsIgnoreCase("null")) {
                    tdData += "<a href='View_EngineModel?engineSeries=" + engineSeries + "'>" + engineSeries.toUpperCase() + "</a> >> ";
                }
                if ((engineModel != null) && !engineModel.equalsIgnoreCase("null")) {
                    tdData += "<a href=\"MainModels?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "\">" + engineModel.toUpperCase() + "</a> >> ";
                }
                tdData += model.toUpperCase() + " >>";
                object_pageTemplate.pageLink(ps, width, height, tdData);

                if (groupTypes.size() == 0) {
                    message = ComUtils.getLanguageTranslation("label.catalogue.noAggregateVariant", session) + " \"" + model + "\"";
                    ps.println(object_pageTemplate.tableHeader1(message, width, height));
                    //object_pageTemplate.ShowMsg(ps, "FAILURE", "No "+PageTemplate.AGGREGATE+" Exists.", "NO", "", "", "", imagesURL);
                    return;
                }



                ps.println("<td align=right width=190 valign = top>");
                ps.println("<table cellspacing=1 cellpadding=3 align=right>");
                ps.println("<tr>");
                if (userFunctionalities.contains("37")) {
                    // ps.println("<tr>");
                    ps.println("<td align  = left class=links_txt valign = top>");
                    //ps.println("</td><td class=links_txt>");
                    //ps.println("<a href='" + servletURL + "ModelPrintable?model=" + model + "' target=vc name = vc>");
                    ps.println("<a href='" + servletURL + "ModelPrintable?model=" + model + "' target=vc name = vc>");
                    ps.println("<IMG SRC=" + imagesURL + "" + PageTemplate.printIconimage + " border = 0 alt=\""+ComUtils.getLanguageTranslation("label.catalogue.printCatalogue", session)+"\"  style=\"z-index:0;\">");// BOM &amp; Image
                    ps.println("</a>");
                    ps.println("</td>");
                    //ps.println("</tr>");


//                    ps.println("<tr>");
//                    ps.println("<td align  = left class=links_txt>");
//                    ps.println("<IMG SRC=" + imagesURL + ""+PageTemplate.printIconimage+" border = 0></td><td class=links_txt>");
//                    //ps.println("<a href='" + servletURL + "ModelPrintable?model=" + model + "' target=vc name = vc>");
//                    ps.println("<a href='" + servletURL + "ModelPrintablePDF?model=" + model + "' target=vc name = vc>");
//                    ps.println("Print Catalogue PDF");// BOM &amp; Image
//                    ps.println("</a>");
//                    ps.println("</td>");
//                    ps.println("</tr>");

                }
                ps.println("<td align  = left class=links_txt valign = top>");
                ps.println("<a href='" + servletURL + "ViewAggregates?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "'>");
                ps.println("<IMG SRC=" + imagesURL + "thumb.gif border = 0 width=18 height=18 alt=\"" + ComUtils.getLanguageTranslation("label.catalogue.viewAggregate", session) + "s\" height=25 style=\"z-index:0;\">");
                ps.println("</a>");
                ps.println("</td>");
//                ps.println("<td align  = left class=links_txt>");
//                ps.println("<a href='" + servletURL + "ViewAggregates1?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "'>");
//                ps.println("<IMG SRC=" + imagesURL + "list.gif border = 0 alt='View " + PageTemplate.AGGREGATE + "s' style=\"z-index:0;\">");
//                ps.println("</a>");
//                ps.println("</td>");

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


//                if (userFunctionalities.contains("41")) {
//                    if (!(date_OR_serial.equals("serialNo"))) {
//                        //ps.println("<tr>");
//                        ps.println("<td align  = left class=links_txt>");
//                        //ps.println("</td><td class=links_txt>");
//                        ps.println("<a href='" + servletURL + "ModelChmt?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "'>");
//                        ps.println("<IMG SRC=" + imagesURL + "MODIFICATION-ICON.jpg border = 0 alt='" + PageTemplate.ECN + "' >");
//                        ps.println("</a>");
//                        ps.println("</td>");
//                        //ps.println("</tr>");
//                    }
//                }

//                if (userFunctionalities.contains("40"))
//                {
//                    ps.println("<tr>");
//                    ps.println("<td align  = left class=links_txt>");
//                    ps.println("<IMG SRC=" + imagesURL + "group_printer.gif border = 0>");
//                    ps.println("<a href='" + servletURL + "PartIndex?sono=" + model + "' target=_blank>");
//                    ps.println("Print Part Index");
//                    ps.println("</a>");
//                    ps.println("</td>");
//                    ps.println("</tr>");
//                }
                if (userFunctionalities.contains("42")) {
                    //ps.println("<tr>");
                    ps.println("<td valign  = left class=links_txt valign = top>");
                    //ps.println("</td><td class=links_txt>");
                    ps.println("<a href='" + servletURL + "ModelExport_bom?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "' target=_blank>");
                    ps.println("<IMG SRC=" + imagesURL + "excel_icon.jpg border = 0 width=18 height=18 alt=\"" + ComUtils.getLanguageTranslation("label.catalogue.exportVariantBOM", session) + "\" height=25 style=\"z-index:0;\"");
                    ps.println("</a>");
                    ps.println("</td>");
                    //ps.println("</tr>");
                }


                if (userFunctionalities.contains("48")) {
                    //ps.println("<tr>");
                    ps.println("<td valign  = left class=links_txt valign = top>");
                    //ps.println("</td><td class=links_txt>");
                    ps.println("<a href=\"" + servletURL + "compAvailable?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "&compType=Kit\" >");
                    ps.println("<IMG SRC=" + imagesURL + "" + PageTemplate.kitIconimage + " border = 0 alt=\"" + ComUtils.getLanguageTranslation("label.catalogue.kitsAvialable", session) + "\"  style=\"z-index:0;\">");
                    ps.println("</a>");
                    ps.println("</td>");
                    //ps.println("</tr>");
                }
//                if (userFunctionalities.contains("409")) {
//                    //ps.println("<tr>");
//                    ps.println("<td valign  = left class=links_txt>");
//                    ps.println("<a href=\"" + servletURL + "compAvailable?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "&compType=Lube\" >");
//                    ps.println("<IMG SRC=" + imagesURL + "" + PageTemplate.lubeIconimage + " border = 0 alt='Lubes Available'>");
//                    ps.println("</a>");
//                    ps.println("</td>");
//                    //ps.println("</tr>");
//                }
//                if (userFunctionalities.contains("408")) {
//                    //ps.println("<tr>");
//                    ps.println("<td valign  = left class=links_txt>");
//                    ps.println("<a href=\"" + servletURL + "compAvailable?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "&compType=Tool\" >");
//                    //ps.println("Tools Available");
//                    ps.println("<IMG SRC=" + imagesURL + "" + PageTemplate.toolIconimage + " border = 0 alt='Tools Available'>");
//                    ps.println("</a>");
//                    ps.println("</td>");
//
//                }
                ps.println("</tr>");
                ps.println("</table>");

                ps.println("</td>");
                ps.println("<br>");

                ps.println(object_pageTemplate.tableHeader(ComUtils.getLanguageTranslation("label.catalogue.aggregatesAvailable", session) + " " + ComUtils.getLanguageTranslation("label.catalogue.variant", session) + " \"" + model + "\"", width, height));


                ps.println("<table width=100% border=0 valign=top>");

                ps.println("<tr> ");


                ps.println("<td valign = top align = left>");
                ps.println("<table width=99% border=0 valign=top cellspacing=1 cellpadding=2 bordercolor = #CCCCCC>");

                ps.println("<tr bgcolor = #003399> ");
                ps.println("<td width = 10% align = center class=heading1>");
                ps.println("");
                ps.println(ComUtils.getLanguageTranslation("label.catalogue.sNo", session).toUpperCase());
                ps.println("</td>");

                ps.println("<td width = 90% align=left class=heading1>");
                ps.println("");
                ps.println(ComUtils.getLanguageTranslation("label.catalogue.aggregate", session).toUpperCase());
                ps.println("</td>");

                ps.println("</tr>");

                ps.println("<tr >");
                ps.println("<td colspan=2 align=left class=links_txt>");
                ps.println("<a href=\"" + servletURL + "ViewModel?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "\">");
                ps.println("<b>");
                ps.println(ComUtils.getLanguageTranslation("label.catalogue.viewAllAggregates", session));
                ps.println("</b>");
                ps.println("</a>");
                ps.println("</td>");
                ps.println("</tr>");

                ps.println("</table>");

                // if (groupTypes.size() > 20)
                {
                    ps.println("<div class=\"maindiv\" STYLE=\" overflow-X:hidden; overflow-y: scroll; height:" + tableHeight_1 + ";\">");
                }
                ps.println("<table width=100% border=0 cellspacing=3 cellpadding=0 bordercolor = #CCCCCC>");
//" + height + "
                String tempGroupType = "";
                String cssClass = null;

                for (int i = 0; i < groupTypes.size(); i++) {

                    if (i % 2 == 0) {
                        cssClass = "links_txt";//links_txt_Alt
                    } else {
                        cssClass = "links_txt";
                    }

                    tempGroupType = "" + groupTypes.elementAt(i);
                    tempGroupType = tempGroupType.replaceAll(" ", "_");
                    ps.println("<tr >");
                    ps.println("<td  valign=top align = center width = 10%  class=" + cssClass + ">");
                    ps.println(i + 1);
                    ps.println("</td>");
                    ps.println("<td valign=top width = 90% class=" + cssClass + ">");
                    ps.println("<a href=\"" + servletURL + "ViewModel?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + model + "&aggregate=" + tempGroupType.replaceAll("&", "@@") + "\">");
                    ps.println(("" + groupTypes.elementAt(i)).toUpperCase());
                    ps.println("</a>");
                    ps.println("</font>");
                    ps.println("</td>");
                    ps.println("</tr> ");
                }
                ps.println("</table> ");
                // if (groupTypes.size() > 20)
                {
                    ps.println("</div>");
                }

                ps.println("</td>");

                ps.println("</tr>");

                /////mansih


                ///////


                //ps.println("</tr>");
                ps.println("</table>");

                ps.println(object_pageTemplate.tableFooter());
                ps.println("</body></html>");
            } else {


                object_pageTemplate.ShowMsg(ps, "FAILURE", ComUtils.getLanguageTranslation("label.catalogue.sessionExpired", session), "YES", ComUtils.getLanguageTranslation("label.catalogue.loginAgain", session), "Index", "", imagesURL);
            }
        } catch (Exception e) {
            ps.println(e);
        } finally {
            ps.close();
            wps.close();
        }
    }
}
