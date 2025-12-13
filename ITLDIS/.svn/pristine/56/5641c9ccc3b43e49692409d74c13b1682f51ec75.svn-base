package viewEcat.comEcat;

/*
File Name: MainModels.java
PURPOSE: To show the list of main models.
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
import java.sql.Statement;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import viewEcat.orderEcat.PriceDetails;

public class MainModels extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        String applicationTypSubQuery = null;
        String productFamilySubQuery = null;
        StringBuilder query = null;
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
            String date_OR_serial = (String) session.getValue("date_OR_serial");
            String modelNo = (String) session.getValue("modelNo");
            applicationTypSubQuery = "" + session.getAttribute("applicationTypSubQuery");
            productFamilySubQuery = "" + session.getAttribute("productFamilySubQuery");


            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");

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

            String orderRefNoString = object_pageTemplate.orderRefNoString();
//            String imagePath = object_pageTemplate.ecat_Model_imagesURL();
//            String imageWidth = null;
//            String imageHeight = null;
//            String imageExt = null;//model_images
            String tdData = null;
            //String imagePath=object_pageTemplate.ecat_Pint_imagesURL()+"imageDir/";


            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////
            //ps.println(header);

            if (session_id.equals(getSession)) {
                Connection conn = holder.getConnection();

                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));
                //
                //

                /*########################################*/

                //Statement stmt; 
                Statement stmt1;
                PreparedStatement stmt = null;
                
                ResultSet rs;


                //stmt = conn.createStatement();
                stmt1 = conn.createStatement();

          //      int tableWidth = 785;
           //     int tableHeight_1 = 400;

                /*java.util.Date todaysDate = null;
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                cal.set(year - 2, Calendar.JANUARY, 1);
                todaysDate = cal.getTime();

                java.sql.Date sqlDate = new java.sql.Date(todaysDate.getTime());
             */
                String flag = req.getParameter("flag") == null ? "" : req.getParameter("flag");
                String dateSubQuery = "";

              /*  if (!flag.equals("showAllFcode")) {
                    dateSubQuery = "AND M.CREATION_DATE>={d '" + sqlDate + "'}";//{d '" + sqlFromDate + "'}
                }
*/
                //c.getTime()


                /*################## Login Track ######################*/
                if (orderRefNoString.equals("WEB")) {
                    new PriceDetails(conn).loginTrakerByUserId((String) session.getValue("userCode"), 2, req.getRemoteAddr(), conn);
                }

                /*########################################*/

              /*  if (screenWidth.equals("800")) {
                    tableWidth = 560;
                    tableHeight_1 = 200;
                }
*/
                int temp_ModelsCount = 0;
               // rs = stmt.executeQuery("SELECT COUNT(*) FROM CAT_MODELS(NOLOCK)");
                String query1 = ("SELECT COUNT(*) FROM CAT_MODELS(NOLOCK)");
                stmt = conn.prepareStatement(query1);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    temp_ModelsCount = rs.getInt(1);
                }
                rs.close();

                String engineSeries = req.getParameter("engineSeries");
                String engineModel = req.getParameter("engineModel");
                String applicationType = req.getParameter("applicationType");
                String level4 = req.getParameter("level4");
                String level5 = req.getParameter("level5");

                if (productFamilySubQuery.equals("")) {
                    query = new StringBuilder("SELECT DISTINCT MC.ENGINE_SERIES, MC.ENGINE_MODEL, MC.APPLICATION_TYPE, MC.MODEL_NO, MC.LEVEL_4, MC.LEVEL_5, M.MODEL_NO, M.DESCRIPTION,MC.SEQ_ORDER FROM CAT_MODEL_CLASSIFICATION MC, CAT_MODELS M  where M.MODEL_NO = MC.MODEL_NO " + applicationTypSubQuery);
                } else {
                    query = new StringBuilder("SELECT DISTINCT MC.ENGINE_SERIES, MC.ENGINE_MODEL, MC.APPLICATION_TYPE, MC.MODEL_NO, MC.LEVEL_4, MC.LEVEL_5, M.MODEL_NO, M.DESCRIPTION,MC.SEQ_ORDER FROM CAT_MODEL_CLASSIFICATION MC, CAT_MODELS M   " + productFamilySubQuery + " and M.MODEL_NO = MC.MODEL_NO " + applicationTypSubQuery);
                }


                if (userFunctionalities.contains("36")) {
                    query.append(" AND M.STATUS='COMPLETE'");
                }
                if (date_OR_serial.equals("serialNo")) {
                    query.append(" AND M.MODEL_NO='" + modelNo + "'");
                } else if (engineSeries != null && engineModel == null && applicationType == null && level4 == null && level5 == null) {
                    query.append(" and MC.ENGINE_SERIES ='" + engineSeries + "' ");

                } else if (engineSeries != null && engineModel != null && applicationType == null && level4 == null && level5 == null) {
                    query.append(" and MC.ENGINE_SERIES = '" + engineSeries + "' AND MC.ENGINE_MODEL = '" + engineModel + "' ");
                } else if (engineSeries != null && engineModel != null && applicationType != null && level4 == null && level5 == null) {
                    query.append(" and MC.ENGINE_SERIES = '" + engineSeries + "' AND MC.ENGINE_MODEL = '" + engineModel + "' AND MC.APPLICATION_TYPE = '" + applicationType + "'");
                } else if (engineSeries != null && engineModel != null && applicationType != null && level4 != null && level5 == null) {
                    query.append(" and MC.ENGINE_SERIES = '" + engineSeries + "' AND MC.ENGINE_MODEL = '" + engineModel + "' AND MC.APPLICATION_TYPE = '" + applicationType + "' and MC.LEVEL_4 = '" + level4 + "'");
                } else if (engineSeries != null && engineModel != null && applicationType != null && level4 != null && level5 != null) {
                    query.append(" and MC.ENGINE_SERIES = '" + engineSeries + "' AND MC.ENGINE_MODEL = '" + engineModel + "' AND MC.APPLICATION_TYPE = '" + applicationType + "' and MC.LEVEL_4 = '" + level4 + "' AND MC.LEVEL_5 = '" + level5 + "'");
                }
                query.append(" " + dateSubQuery);
                query.append(" order by MC.SEQ_ORDER");

                String[][] totalModels = new String[temp_ModelsCount][7];
                int totalModelsCounter = 0;
                String temp_1 = "";
                String temp_2 = "";
                String temp_3 = "";
                String temp_4 = "";
                String temp_5 = "";
                String temp_6 = "";
                String modelDesc = "";
                //System.out.println("query.toString()...." + query.toString() + "query.toString()");

               // rs = stmt.executeQuery(query.toString());
                stmt = conn.prepareStatement(query.toString());
                rs = stmt.executeQuery();


                if (rs.next()) {
                    query.setLength(0);
                    query = null;
                    do {

                        temp_2 = rs.getString(1);
                        temp_3 = rs.getString(2);
                        temp_4 = rs.getString(3);
                        temp_1 = rs.getString(4);
                        temp_5 = rs.getString(5);
                        temp_6 = rs.getString(6);
                        modelDesc = rs.getString(8);

                        if ((modelDesc == null) || modelDesc.equals("")) {
                            modelDesc = "-";
                        }

                        if (temp_2.equals("NA")) {
                            temp_2 = "&nbsp;";
                        }

                        if (temp_3.equals("NA")) {
                            temp_3 = "&nbsp;";
                        }

                        if (temp_4.equals("NA")) {
                            temp_4 = "&nbsp;";
                        }

                        if (temp_5.equals("NA")) {
                            temp_5 = "&nbsp;";
                        }

                        if (temp_6.equals("NA")) {
                            temp_6 = "&nbsp;";
                        }

                        totalModels[totalModelsCounter][0] = temp_1;
                        totalModels[totalModelsCounter][1] = temp_2;
                        totalModels[totalModelsCounter][2] = temp_3;
                        totalModels[totalModelsCounter][3] = temp_4;
                        totalModels[totalModelsCounter][4] = modelDesc;
                        totalModels[totalModelsCounter][5] = temp_5;
                        totalModels[totalModelsCounter][6] = temp_6;


                        totalModelsCounter++;

                    } while (rs.next());
                } else if (totalModelsCounter == 0 && !flag.equals("showAllFcode")) {
                    String queryString = query.toString();
                    int dateSubIndex = queryString.lastIndexOf("AND");
                    int orderByIndex = queryString.lastIndexOf("order by");
                    queryString = queryString.substring(0, dateSubIndex) + " " + queryString.substring(orderByIndex);
                   // rs = stmt.executeQuery(queryString);
                    stmt = conn.prepareStatement(queryString);

                    //System.out.println("query.toString()...." + query.toString() + "query.toString()");
                    query.setLength(0);
                    //System.out.println("query.toString()...."+query.toString()+"query.toString()");
                    query = null;
                    queryString = null;

                    if (rs.next()) {
                        do {
                            temp_2 = rs.getString(1);
                            temp_3 = rs.getString(2);
                            temp_4 = rs.getString(3);
                            temp_1 = rs.getString(4);
                            temp_5 = rs.getString(5);
                            temp_6 = rs.getString(6);
                            modelDesc = rs.getString(8);

                            if ((modelDesc == null) || modelDesc.equals("")) {
                                modelDesc = "-";
                            }

                            if (temp_2.equals("NA")) {
                                temp_2 = "&nbsp;";
                            }

                            if (temp_3.equals("NA")) {
                                temp_3 = "&nbsp;";
                            }

                            if (temp_4.equals("NA")) {
                                temp_4 = "&nbsp;";
                            }

                            if (temp_5.equals("NA")) {
                                temp_5 = "&nbsp;";
                            }

                            if (temp_6.equals("NA")) {
                                temp_6 = "&nbsp;";
                            }

                            totalModels[totalModelsCounter][0] = temp_1;
                            totalModels[totalModelsCounter][1] = temp_2;
                            totalModels[totalModelsCounter][2] = temp_3;
                            totalModels[totalModelsCounter][3] = temp_4;
                            totalModels[totalModelsCounter][4] = modelDesc;
                            totalModels[totalModelsCounter][5] = temp_5;
                            totalModels[totalModelsCounter][6] = temp_6;


                            totalModelsCounter++;

                        } while (rs.next());
                    }
                }
                rs.close();
                stmt1.close();




                ps.println("<html>");
                ps.println("   <head>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
                ps.println("</head>");

                ps.println("<body topmargin=0  marginheight=0 leftmargin=0  marginwidth=0 >");


//                query = "select Width,Height,ImageExt  from IMAGE_REFERENCE where ImageType='MODELS'";
//                rs = stmt.executeQuery(query);
//
//                if (rs.next()) {
//                    imageWidth = rs.getString(1);
//                    imageHeight = rs.getString(2);
//                    imageExt = rs.getString(3);
//                }
                rs.close();
                stmt.close();

                //changes_01_02_2012
                //header
                //linking
                int width = 960, height = 400;

//                tdData = "<a href='View_EngineSeries'>"+PageTemplate.ENGINE_SERIES+"</a> >> <a href='View_EngineModel?engineSeries=" + engineSeries + "'>" + engineSeries + "</a> >> "
//                        + engineModel + " >>";
                tdData = "<a href='View_EngineSeries'>" + ComUtils.getLanguageTranslation("label.catalogue.product", session).toUpperCase() + "</a> >> ";
                if ((engineSeries != null) && !engineSeries.equalsIgnoreCase("null")) {
                    tdData += "<a href='View_EngineModel?engineSeries=" + engineSeries + "'>" + engineSeries.toUpperCase() + "</a> >> ";
                }
                if ((engineModel != null) && !engineModel.equalsIgnoreCase("null")) {
                    tdData += engineModel.toUpperCase() + " >>";
                }

                object_pageTemplate.pageLink(ps, width, height, tdData);

                if (totalModelsCounter == 0) {
                    message = ComUtils.getLanguageTranslation("label.catalogue.noVariantExist", session) + " For \"" + engineModel + "\"";
                    ps.println(object_pageTemplate.tableHeader1(message, width, height));
                    //object_pageTemplate.ShowMsg(ps, "FAILURE", "No Model Exists.", "NO", "", "", "", imagesURL);
                    return;
                }

               // String tdRight = "<td align=\"right\"  class=\"links_txt\"><a href='" + servletURL + "MainModels?flag=showAllFcode&engineModel=" + engineModel + "&engineSeries=" + engineSeries + "'><b>View All " + PageTemplate.MODEL_NO + "</b></a></td>";

                //String tdRight="<td class=\"links_txt\"><a href='" + servletURL + "MainModels?flag=showAllFcode'>View all "+PageTemplate.MODEL_NO+"</a></td>";

                ps.println(object_pageTemplate.tableHeader2(ComUtils.getLanguageTranslation("label.catalogue.variantsAvailable", session) + " \"" + engineModel + "\"", width, height, "left", ""));

                ps.println(" <table width=100% border=0 cellspacing=0 cellpadding=0 bordercolor = #CCCCCC style=padding-top:3px>");


                if (totalModelsCounter > 0) {
                    ps.println(" <tr><td align=left  width=100%><table width=99% border=0 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC>");

                    ps.println("<tr>");
                    ps.println("<td width = 8% align = center class=heading1>");
                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.sNo", session).toUpperCase());
                    ps.println("</td>");

                    ps.println("<td width = 15% align=left class=heading1>");
                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.variant", session).toUpperCase());
                    ps.println("</td>");

                    ps.println("<td width = 77% align=left class=heading1>");
                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.description", session).toUpperCase());
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table>");
                }

                ps.println(" </td></tr><tr><td align=left width=100%>");
                //ps.println("<div valign=top STYLE=\" overflow-X:hidden; overflow-y: scroll; height:430;\"> ");//380//background-color: pink;//border: 1px solid black;
                ps.println("<div style=\"float: left; width: 950px; height: 390px;  overflow: auto; \"> ");//380//background-color: pink;//border: 1px solid black;

                ps.println("                 <table width=99% border=0 cellspacing=1 cellpadding=4 bordercolor = #CCCCCC>");
                ps.println("              ");
                String cssClass = null;

                for (int i = 0; i < totalModelsCounter; i++) {
                    //New_Changes_08_02_2012
                    if (i % 2 == 0) {
                        cssClass = "links_txt";//links_txt_Alt
                    } else {
                        cssClass = "links_txt";
                    }

                    ps.println("<tr >");
                    ps.println("<td width=8%  align=center class=" + cssClass + ">");
                    ps.println("<b>" + (i + 1) + "</b>");
                    ps.println("</td>");

                    ps.println("<td width=15% align=left class=" + cssClass + ">");
                    ps.println("<a href='" + servletURL + "ViewAggregates?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + totalModels[i][0] + "'>");
                    ps.println(("<b>" + totalModels[i][0]).toUpperCase() + "</b>");
                    ps.println("</a>");
                    ps.println("</td>");

                    ps.println("<td width=77% align=left class=" + cssClass + ">");
                    ps.println("<a href='" + servletURL + "ViewAggregates?engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&model=" + totalModels[i][0] + "'>");
                    ps.println(("<b>" + totalModels[i][4]).toUpperCase() + "</b>");
                    ps.println("</a>");
                    ps.println("</td>");
                    ps.println("</tr> ");


                }

                ps.println("                        </table></div>");
                ps.println("</td></tr></table>");
//Footer

                ps.println(object_pageTemplate.tableFooter());


            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE", ComUtils.getLanguageTranslation("label.catalogue.sessionExpired", session), "YES", ComUtils.getLanguageTranslation("label.catalogue.loginAgain", session), "Index", "", imagesURL);
            }
        } catch (Exception e) {
            e.printStackTrace();
            ps.println(e);
        } finally {
        	
            ps.close();
            wps.close();
        }
    }
}
