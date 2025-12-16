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
File Name: Adv_search_ind_model.java
PURPOSE: To search for MODEL NO or MODEL DESC.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import authEcat.UtilityMapkeys1;

public class Adv_search_ind_model extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        String productFamilySubQuery = null;
        String applicationTypSubQuery = null;
        String subQuery = "";
        String message = null;
        //make changes by annpurna
        PreparedStatement stmt = null;
       // Statement stmt = null;
        PreparedStatement stmt1 = null;
       // Statement stmt1 = null;
        ResultSet rs = null;

        try {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            productFamilySubQuery = "" + session.getAttribute("productFamilySubQuery");
            applicationTypSubQuery = "" + session.getAttribute("applicationTypSubQuery");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();


            String databaseNAME = object_pageTemplate.databaseNAME();

            if (session_id.equals(getSession)) {
                Connection conn = holder.getConnection();
                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));

               // stmt = conn.createStatement();
                //stmt1 = conn.createStatement();
           // stmt = conn.prepareStatement();
            //stmt1 =conn.prepareStatement();

                String productText = "", group_t = "", modelDesc = "", partdesc_t = "", partno_t = "", aggregateDesc = "";
                String productText1 = "", group_t1 = "", modelDesc1 = "", partdesc_t1 = "", partno_t1 = "", aggregateDesc1 = "";
                if (req.getParameter("modelNoTxt") != null) {
                    //modelNo_CHECK = req.getParameter("modelNo");
                    productText = req.getParameter("modelNoTxt");
                    productText = productText.toUpperCase();
                    productText1 = productText;
                    productText = productText.replaceAll("\'", "''");
                } else {
                    productText = "%";
                }

                if (productText.equalsIgnoreCase("All")) {
                    productText = "%";
                }



                if (req.getParameter("modelTxt") != null) {
                    ///model_CHECK = req.getParameter("model");
                    modelDesc = req.getParameter("modelTxt");
                    modelDesc = modelDesc.toUpperCase();
                    modelDesc1 = modelDesc;
                    modelDesc = modelDesc.replaceAll("\'", "''");
                } else {
                    modelDesc = "%";
                }
                if (modelDesc.equalsIgnoreCase("All")) {
                    modelDesc = "%";
                }

                if (req.getParameter("aggTxt") != null) {
                    ///model_CHECK = req.getParameter("model");
                    aggregateDesc = req.getParameter("aggTxt");
                    aggregateDesc = aggregateDesc.toUpperCase();
                    aggregateDesc1 = aggregateDesc;
                    aggregateDesc = aggregateDesc.replaceAll("\'", "''");
                } else {
                    aggregateDesc = "%";
                }
                if (aggregateDesc.equalsIgnoreCase("All")) {
                    aggregateDesc = "%";
                }

                if (req.getParameter("groupTxt") != null) {
                    //group_CHECK = req.getParameter("group");
                    group_t = req.getParameter("groupTxt");
                    group_t = group_t.toUpperCase();
                    group_t1 = group_t;
                    group_t = group_t.replaceAll("\'", "''");

                    if (group_t.equalsIgnoreCase("All")) {
                        group_t = "%";
                    }
                } else {
                    group_t = "%";
                }

                if (req.getParameter("partDescTxt") != null) {
                    //partdesc_CHECK = req.getParameter("partDesc");
                    partdesc_t = req.getParameter("partDescTxt");
                    partdesc_t = partdesc_t.toUpperCase();
                    partdesc_t1 = partdesc_t;
                    partdesc_t = partdesc_t.replaceAll("\'", "''");
                    if (partdesc_t.equals("")) {
                        partdesc_t = "%";
                    }
                } else {
                    partdesc_t = "%";
                }


                if (req.getParameter("partnoTxt") != null) {
                    //partno_CHECK = req.getParameter("partno");
                    partno_t = req.getParameter("partnoTxt");
                    partno_t = partno_t.toUpperCase();
                    partno_t1 = partno_t;
                    partno_t = partno_t.replaceAll("\'", "''");
                    partno_t = partno_t.replaceAll(" ", "_");
                    if (partno_t.equals("")) {
                        partno_t = "%";
                    }
                } else {
                    partno_t = "%";
                }


                if (group_t.indexOf("*") != -1) {
                    group_t = group_t.replace("*", "%");
                } else if (!group_t.equalsIgnoreCase("")) {
                    group_t = "%" + group_t + "%";
                }
                if (partdesc_t.indexOf("*") != -1) {
                    partdesc_t = partdesc_t.replace("*", "%");
                } else if (!partdesc_t.equalsIgnoreCase("")) {
                    partdesc_t = "%" + partdesc_t + "%";
                }

                if (partno_t.indexOf("*") != -1) {
                    partno_t = partno_t.replace("*", "%");
                } else if (!partno_t.equalsIgnoreCase("")) {
                    partno_t = "%" + partno_t + "%";
                }



                //    int temp_ModelsCount = 0;

                /* if (!productFamilySubQuery.equals("")) {
                if (!applicationTypSubQuery.equals("")) {
                subQuery = productFamilySubQuery + applicationTypSubQuery;
                } else {
                subQuery = productFamilySubQuery;
                }
                } else if (!applicationTypSubQuery.equals("")) {
                subQuery = applicationTypSubQuery.replaceAll("and", "where");
                }
                 */
                //System.out.println("SELECT COUNT(*) FROM MODEL_CLASSIFICATION "+subQuery);

                /*  rs = stmt.executeQuery("SELECT COUNT(*) FROM MODEL_CLASSIFICATION " + subQuery);
                if (rs.next()) {
                temp_ModelsCount = rs.getInt(1);
                }
                rs.close();
                 */
                String query = "";

                if (productFamilySubQuery.equals("")) {
                    subQuery = "";
                } else {
                    subQuery = productFamilySubQuery + " ";
                }


                //if (userFunctionalities.contains("36")) {
                //query = "SELECT DISTINCT MC.ENGINE_SERIES, MC.ENGINE_MODEL, MC.APPLICATION_TYPE, MC.MODEL_NO, MC.LEVEL_4 FROM MODEL_CLASSIFICATION MC, MODELS M " + subQuery + "(M.DESCRIPTION) LIKE " + databaseNAME + "('" + modelDescription + "')  AND M.STATUS='COMPLETE' AND M.MODEL_NO = MC.MODEL_NO" + applicationTypSubQuery;
                //} else {
                //query = "SELECT DISTINCT MC.ENGINE_SERIES, MC.ENGINE_MODEL, MC.APPLICATION_TYPE, MC.MODEL_NO, MC.LEVEL_4 FROM MODEL_CLASSIFICATION MC, MODELS M,MODEL_GROUP mg " + subQuery + "(M.DESCRIPTION) LIKE " + databaseNAME + "('" + modelDescription + "') AND M.MODEL_NO = MC.MODEL_NO AND ND MG.MODEL_NO = M.MODEL_NO" + applicationTypSubQuery;

                //}

                String filterQ = "Order by part_no";
                query = " Select DISTINCT p.part_no,p.p1,p.part_type "
                        + " FROM CAT_MODEL_CLASSIFICATION(NOLOCK) MC,"
                        + " CAT_MODELS(NOLOCK) M,CAT_MODEL_GROUP(NOLOCK) MG,"
                        + " CAT_PART(NOLOCK) P,"
                        + " CAT_GROUP_KIT_BOM(NOLOCK) GKB,"
                        + " CAT_GROUP_KIT_DETAIL GKD(NOLOCK) where "
                        + " (M.MODEL_NO = MC.MODEL_NO) "
                        + " AND (MG.MODEL_NO = M.MODEL_NO)"
                        + " AND (MG.GROUP_NO=GKB.GRP_KIT_NO) "
                        + " AND (GKB.COMPONENT=P.part_no )"
                        + " AND (GKB.GRP_KIT_NO= GKD.GRP_KIT_NO) " + productFamilySubQuery.replaceAll("where", "") + applicationTypSubQuery;

                if (userFunctionalities.contains("36")) {
                    query += " and M.STATUS='COMPLETE' ";
                }


                query += " AND MC.ENGINE_SERIES like " + databaseNAME + "('" + productText + "') and  MC.ENGINE_MODEL  like " + databaseNAME + "('" + modelDesc + "')"
                        + " and  MG.GROUP_TYPE like " + databaseNAME + "('" + aggregateDesc + "') "
                        + " and  GKD.P1 like " + databaseNAME + "('" + group_t + "') "
                        + " and P.part_no like " + databaseNAME + "('" + partno_t + "')"
                        + " and P.P1 like " + databaseNAME + "('" + partdesc_t + "') " + filterQ;
//changes here
                stmt = conn.prepareStatement(query);
                Vector totalSearchR = new Vector();
               // rs = stmt.executeQuery(query);
                rs = stmt.executeQuery();
                int totalCount = 0;
                if (rs.next()) {
                    do {
                        totalSearchR.addElement("" + rs.getString(1));
                        totalSearchR.addElement("" + rs.getString(2));
                        totalSearchR.addElement("" + rs.getString(3));
                        totalCount++;
                    } while (rs.next());
                }


                ps.println("<center><table id=loading cellspacing=0 cellpadding=0 border=0 height=80% width=90%>");
                ps.println("<tr>");
                ps.println("<td valign = middle>");
                ps.println("<table cellspacing=0 cellpadding=10 border=0 width=100% bordercolor=#D7D7D7>");
                ps.println("<tr>");
                ps.println("<td align = center>");
                ps.println("<font color = #000099><b>");
                ps.println("<img src='" + imagesURL + "loading.gif'><br>Loading....");
                ps.println("</td>");
                ps.println("</tr>");
                ps.println("</table>");
                ps.println("</td>");
                ps.println("</tr>");
                ps.println("</table>");

                ps.println("<html>");
                ps.println("   <head>");
                ps.println("      <title>");
                ps.println("         " + UtilityMapkeys1.tile1 + "");
                ps.println("      </title>");
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

                ps.println("</HEAD><BODY  topmargin = 0 marginheight = 0>");

                int width = 970, height = 440;

                if (totalCount == 0) {
                    message = "No Data Found";
                    ps.println(object_pageTemplate.tableHeader1(message, width, height));
                    ps.println("<br>");

//                    ps.println("<tr>");
//                    ps.println("<td  align = left class=links_txt>");
ps.println("<table border=0 width=100%>");
                ps.println("<br><tr align=left class=links_txt >");

                ps.println("<td valign=top >");
                ps.println("<strong>" + ComUtils.getLanguageTranslation("label.catalogue.product", session).toUpperCase() + ":</strong>");
                ps.println("</td><td valign=top style='width=40%'>" + productText1 + "</td>");

                ps.println("<td valign=top >");
                ps.println("<strong>" + ComUtils.getLanguageTranslation("label.catalogue.model", session).toUpperCase() + ":</strong>");
                ps.println("</td><td valign=top style='width=20%'>" + modelDesc1 + "</td>");

                ps.println("<td valign=top >");
                ps.println("<strong>" + ComUtils.getLanguageTranslation("label.catalogue.aggregate", session).toUpperCase() + ":</strong>");
                ps.println("</td><td valign=top width=40%>" + aggregateDesc1 + "</td>");

                ps.println("</tr><tr align=left class=links_txt>");

                ps.println("<td valign=top >");
                ps.println("<strong>" + ComUtils.getLanguageTranslation("label.catalogue.group", session).toUpperCase() + ":</strong>");
                ps.println("</td><td valign=top style=''>" + group_t1 + "</td>");

                if (!(partno_t1 == null || partno_t1.equals(""))) {
                    ps.println("<td valign=top >");
                    ps.println("<strong>" + ComUtils.getLanguageTranslation("label.catalogue.partNo", session).toUpperCase() + ":</strong>");
                    ps.println("</td><td valign=top style='' class=links_txt >" + partno_t1 + "</td>");
                }

                if (!(partdesc_t1 == null || partdesc_t1.equals(""))) {
                    ps.println("<td valign=top >");
                    ps.println("<strong>" + ComUtils.getLanguageTranslation("label.catalogue.partDesc", session).toUpperCase() + ":</strong>");
                    ps.println("</td><td valign=top class=links_txt >" + partdesc_t1 + "</td>");
                }

                ps.println("</tr>");
                ps.println("</table>");
                    ps.println("<script>");
                    ps.println(" document.getElementById('loading').style.display='none';");
                    ps.println("</script>");
                    ps.println(object_pageTemplate.tableFooter());
                    return;
                }


                ps.println(object_pageTemplate.tableHeader(ComUtils.getLanguageTranslation("label.catalogue.searchResult", session) + " (" + totalCount + ")", width, height));
                ps.println(" <table width=100% border=0 cellspacing=0 cellpadding=0 bordercolor = #CCCCCC>");


                ps.println("<tr>");
                ps.println("<td  align = left class=links_txt>");
                ps.println("<table border=0 width=100%>");
                ps.println("<br><tr align=left class=links_txt >");

                ps.println("<td valign=top width=7% align=right>");
                ps.println("<strong>" + ComUtils.getLanguageTranslation("label.catalogue.product", session).toUpperCase() + ":</strong>");
                ps.println("</td><td valign=top width=25%>" + productText1 + "</td>");

                ps.println("<td valign=top width=10% align=right>");
                ps.println("<strong>" + ComUtils.getLanguageTranslation("label.catalogue.model", session).toUpperCase() + ":</strong>");
                ps.println("</td><td valign=top width=25%>" + modelDesc1 + "</td>");

                ps.println("<td valign=top width=8% align=right>");
                ps.println("<strong>" + ComUtils.getLanguageTranslation("label.catalogue.aggregate", session).toUpperCase() + ":</strong>");
                ps.println("</td><td valign=top width=25%>" + aggregateDesc1 + "</td>");

                ps.println("</tr><tr align=left class=links_txt>");

                ps.println("<td valign=top width=7% align=right>");
                ps.println("<strong>" + ComUtils.getLanguageTranslation("label.catalogue.group", session).toUpperCase() + ":</strong>");
                ps.println("</td><td valign=top  width=25%>" + group_t1 + "</td>");

                if (!(partno_t1 == null || partno_t1.equals(""))) {
                    ps.println("<td valign=top  width=10% align=right>");
                    ps.println("<strong>" + ComUtils.getLanguageTranslation("label.catalogue.partNo", session).toUpperCase() + ":</strong>");
                    ps.println("</td><td valign=top width=25% class=links_txt >" + partno_t1 + "</td>");
                }

                if (!(partdesc_t1 == null || partdesc_t1.equals(""))) {
                    ps.println("<td valign=top  width=10% align=right>");
                    ps.println("<strong>" + ComUtils.getLanguageTranslation("label.catalogue.partDesc", session).toUpperCase() + ":</strong>");
                    ps.println("</td><td valign=top  width=25% class=links_txt >" + partdesc_t1 + "</td>");
                }

                ps.println("</tr>");
                ps.println("</table>");

                /*   ps.println("<br><br>");
                ps.println("<strong>NO. OF PARTS FOUND: </strong>" + totalCount);
                ps.println("<br><br></td>");
                 */
                ps.println("<br>");
                ps.println("</td>");
                ps.println("</tr>");
//                ps.println("<tr><td>&nbsp;</td></tr>");




                ps.println(" <tr><td align=left  width=100%><table width=99% border=0 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC>");

                ps.println("<tr>");
                ps.println("<td width = 7% align = center class=heading1>");
                ps.println(ComUtils.getLanguageTranslation("label.catalogue.sNo", session).toUpperCase());
                ps.println("</td>");

                ps.println("<td width = 13% align=left class=heading1>");
                ps.println(ComUtils.getLanguageTranslation("label.catalogue.partNo", session).toUpperCase());
                ps.println("</td>");

                ps.println("<td width = 62% align=left class=heading1>");
                ps.println(ComUtils.getLanguageTranslation("label.catalogue.description", session).toUpperCase());
                ps.println("</td>");

                ps.println("<td width = 18% align=left class=heading1>");
                ps.println(ComUtils.getLanguageTranslation("label.catalogue.whereUsed", session).toUpperCase() + "?");
                ps.println("</td>");
                ps.println("</tr>");
                ps.println("</table>");

                ps.println(" </td></tr><tr><td align=left width=100%><div valign=top STYLE=\" overflow-X:hidden; overflow-y: scroll; height:360;\">");

                ps.println("                 <table width=100% border=0 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC>");
                ps.println("              ");



                //  String winName = "";
                String tempPartNo = "";
                String cssClass = null;

                for (int i = 0; i < totalSearchR.size(); i = i + 3) {

                    cssClass = "links_txt";

                    ps.println("<tr>");
                    ps.println("<td width=7% align = center class=" + cssClass + ">");
                    ps.println(((i / 3) + 1));
                    ps.println("</td>");


                    //winName = totalSearchR.get(i).toString().replaceAll("-", "");

                    ps.println("<td width=13% align=left class=" + cssClass + ">");
                    ps.println("<a href=javascript:MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + totalSearchR.elementAt(i) + "&compType=" + totalSearchR.elementAt(i + 2) + "','DETAILS','scrollbars=yes,width=700,height=605')>");
                    tempPartNo = totalSearchR.elementAt(i).toString().replaceAll("_", "&nbsp;");

                    if (tempPartNo.startsWith("TBA")) {
                        ps.println(PageTemplate.tbaPart);
                    } else {
                        ps.println(tempPartNo);
                    }

                    ps.println("</a>");
                    ps.println("</td>");

                    ps.println("<td width=63% align=left class=" + cssClass + ">");
                    ps.println("<a href=javascript:MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + totalSearchR.elementAt(i) + "&compType=" + totalSearchR.elementAt(i + 2) + "','DETAILS','scrollbars=yes,width=700,height=605')>");
                    ps.println(totalSearchR.elementAt(i + 1) + "");
                    ps.println("</a></td>");


                    ps.println("<td width=18%  align=left class=" + cssClass + ">");
                    ps.println("<a href=javascript:MM_openBrWindow('" + servletURL + "Part_where_used?part=" + totalSearchR.elementAt(i) + "','WHEREUSED','scrollbars=yes,width=700,height=505')>");
                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.whereUsed", session) + "?</a>");
                    ps.println("</td>");

                    ps.println("</tr>");
                }
                ps.println("                        </table></div>");
                ps.println("</td></tr></table>");
//Footer

                ps.println(object_pageTemplate.tableFooter());

            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
            ps.println("</body></html>");
            ps.println("<script>");
            ps.println(" document.getElementById('loading').style.display='none';");
            ps.println("</script>");

            session.setAttribute("groupDescription", new Vector());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                    rs = null;
                }
                if (stmt != null) {
                    stmt.close();
                    stmt = null;
                }
                if (stmt1 != null) {
                    stmt1.close();
                    stmt1 = null;
                }

            } catch (Exception e) {
            }
            ps.close();
            wps.close();
        }
    }
}
