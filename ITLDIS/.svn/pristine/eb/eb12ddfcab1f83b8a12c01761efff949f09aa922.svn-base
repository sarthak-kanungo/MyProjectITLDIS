package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Arrays;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
File Name: Part_where_used.java
PURPOSE: It is used to find the groups in which the particular part is used.
HISTORY:
DATE		BUILD	AUTHOR			MODIFICATIONS
NA			v3.4	Deepak Mangal	$$0 Created
 */
import authEcat.UtilityMapkeys1;

public class Part_where_used extends HttpServlet
{

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException
    {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        String productFamilySubQuery = null;
        String applicationTypSubQuery = null;
        String message = null;

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
            String date_OR_serial = (String) session.getValue("date_OR_serial");
            java.sql.Date inputDate = (java.sql.Date) session.getValue("input_Date");
            java.sql.Date buckleDate = (java.sql.Date) session.getValue("buckleDate");
            productFamilySubQuery = "" + session.getAttribute("productFamilySubQuery");
            applicationTypSubQuery = "" + session.getAttribute("applicationTypSubQuery");
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
            int width = 602, height = 90;//479;

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ps.println(header);
            if (session_id.equals(getSession))
            {

                Vector allASSY = new Vector();
                Vector allSKITS = new Vector();
                Vector allGROUPS = new Vector();

                Connection conn = holder.getConnection();
                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));



                /*########################################*/

                //Statement stmt;
                PreparedStatement stmt = null;
                ResultSet rs;

                //stmt = conn.createStatement();
                String part = req.getParameter("part");
                String linksReq = req.getParameter("linksReq");
                String compType = null;//req.getParameter("compType");

                java.sql.Date viewDate = new java.sql.Date((new java.util.Date()).getTime());

                if (date_OR_serial == null)
                {
                    date_OR_serial = "latest";
                }
                else if (date_OR_serial.equals("model_date"))
                {
                    viewDate = inputDate;
                }
                else if (date_OR_serial.equals("serialNo"))
                {
                    viewDate = buckleDate;
                }


//System.out.println("select part_type from part where part_no='"+part+"'");
                //rs = stmt.executeQuery("select part_type from CAT_PART where part_no='" + part + "'");
                String query1 = ("select part_type from CAT_PART(NOLOCK) where part_no='" + part + "'");
                stmt = conn.prepareStatement(query1);
                rs = stmt.executeQuery();

                if (rs.next())
                {
                    compType = rs.getString(1);
                }

                rs.close();

                CompWhereUsedReport ob = new CompWhereUsedReport(conn);
                CompWhereUsedReport ob2 = ob.getTotalModels(part, viewDate, productFamilySubQuery, applicationTypSubQuery);

                int modelsCOUNT = ob2.print_data_counter;

                for (int j = 0; j < modelsCOUNT; j++)
                {
                    int totLen = ob2.print_data[j].length();
                    String temp = ob2.print_data[j].substring(totLen - 1, totLen);
                    String temp_1 = ob2.print_data[j].substring(0, totLen - 4);

                    if (temp.equals("A"))
                    {
                        if (allASSY.indexOf(temp_1) == -1)
                        {
                            allASSY.addElement(temp_1);
                        }
                    }

                    if (temp.equals("K"))
                    {
                        if (allSKITS.indexOf(temp_1) == -1)
                        {
                            allSKITS.addElement(temp_1);
                        }
                    }

                    if (temp.equals("G"))
                    {
                        if (allGROUPS.indexOf(temp_1) == -1)
                        {
                            allGROUPS.addElement(temp_1);
                        }
                    }
                }

                ps.println("<html><head>");
                ps.println("      <title>");
                ps.println("         " + UtilityMapkeys1.tile1 + "");
                ps.println("      </title>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
                ps.println("			<script language=JavaScript type=text/JavaScript>");
                ps.println("			function onTop()");
                ps.println("			{");
                ps.println("				self.focus();");
                ps.println("				window.moveTo(0,0);");
                ps.println("			}");
                ps.println("			</script>");

                ps.println("</head>");
                ps.println("<body leftmargin=0 topmargin=0 marginwidth=0 marginheight=0   onLoad = onTop()>");

                //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

                //APPLICABLE MODELS FOR KIT,LUBE,TOOL

                int counter = 1;
                String engineModel = null;

                int l = 0;
                String cssClass = null;

                if (compType.equalsIgnoreCase("KIT") || compType.equalsIgnoreCase("LUBE") || compType.equalsIgnoreCase("TOOL"))
                {
                    //kitBomDetail=new ArrayList();
                    //rs = stmt.executeQuery("select distinct pmm.ENGINE_MODEL,engine_series from CAT_MODEL_CLASSIFICATION mc,CAT_PART_MODEL_MATRIX pmm where pmm.ENGINE_MODEL=MC.ENGINE_MODEL and part_no='" + part + "' order by pmm.ENGINE_MODEL");
                     String query2 = ("select distinct pmm.ENGINE_MODEL,engine_series from CAT_MODEL_CLASSIFICATION(NOLOCK) mc,CAT_PART_MODEL_MATRIX(NOLOCK) pmm where pmm.ENGINE_MODEL=MC.ENGINE_MODEL and part_no='" + part + "' order by pmm.ENGINE_MODEL");
                     stmt = conn.prepareStatement(query2);
                     rs = stmt.executeQuery();
                    while (rs.next())
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


                        if (counter == 1)
                        {
                            ps.println(object_pageTemplate.tableHeader("Applicable " + PageTemplate.ENGINE_MODEL + " for Kit/Lube/Tool No. \"" + part + "\"", width, height));

                            ps.println("<table width=97% border=1 cellspacing=0 cellpadding=3 bordercolor = #CCCCCC>");
                            ps.println("<tr>");

                            ps.println("<td  align=center width=10% class=heading1>");
                            ps.println("#");
                            ps.println("</td>");

                            ps.println("<td  width=90% align=left class=heading1>");
                            ps.println("MODELS");
                            ps.println("</td>");

                            ps.println("	</tr>");
                            ps.println("	</table>");
                            ps.println("<div style=\"float: left; width: 100%; height: 100px;  overflow: auto; \"> ");//380//background-color: pink;//border: 1px solid black;
                            //ps.println("<div class=\"maindiv\" style=\"overflow-X:hidden; overflow-Y: scroll; height:380;\">");//380

                            ps.println(" <table width=100%  align=\"center\" border=0 cellspacing=2 cellpadding=2 bordercolor = #CCCCCC >");
                            //ps.println("    <tr>                       ");


                        }


                        ps.println("<tr>");

                        ps.println("<td  width=70px align=center class=" + cssClass + ">");
                        ps.println(counter);
                        ps.println("</td>");

                        engineModel = rs.getString(1);

                        ps.println("<td   align=left class=" + cssClass + ">");
                        ps.println("<a  href=\"MainModels?engineModel=" + engineModel + "&engineSeries=" + rs.getString(2) + "\" target=right>");
                        ps.println(engineModel);
                        ps.println("</a");
                        ps.println("</td");
                        ps.println("</tr>");

                        counter++;
                    }
                    rs.close();
                    if (counter > 1)
                    {
                        ps.println("</table>");
                        ps.println("</div>");
                        ps.println(object_pageTemplate.tableFooter());
                    }
                }
                //APPLICABLE MODELS FOR KIT.LUBE,TOOL
                engineModel = null;



                if (allGROUPS.size() > 0)
                {


                    //ps.println("	</tr>");


                    String[] stringsArr = (String[]) allGROUPS.toArray(new String[allGROUPS.size()]);
                    Arrays.sort(stringsArr);

                    int sNo = 1;
                    String temp_group = "";
                    Vector allMODELS = new Vector();
                    String tempHeading = "";
                    String modelNo = "";
                    String modelDesc = "";
                    String subQuery = null;
                    boolean flag = false;
String tempGroupDesc="";
                    if (productFamilySubQuery.equals(""))
                    {
                        subQuery = " WHERE mc.MODEL_NO=M.MODEL_NO and M.MODEL_NO = MGM.MODEL_NO" + applicationTypSubQuery;
                    }
                    else
                    {
                        subQuery = productFamilySubQuery + " and  mc.MODEL_NO=M.MODEL_NO and  M.MODEL_NO = MGM.MODEL_NO" + applicationTypSubQuery;
                    }

                    for (int i = 0; i < allGROUPS.size(); i++)
                    {
                        temp_group = "" + stringsArr[i];
                        allMODELS = new Vector();

                        if (i % 2 == 0)
                        {
                            cssClass = "links_txt";
                        }
                        else
                        {
                            cssClass = "links_txt_Alt";
                        }

                        if (userFunctionalities.contains("36"))//setweryer
                        {
                            //rs = stmt.executeQuery("SELECT distinct MGM.MAP_NAME, MGM.MODEL_NO, M.MODEL_NO,mc.ENGINE_SERIES,mc.ENGINE_MODEL,gkd.P1 FROM CAT_MODEL_GROUP MGM, CAT_MODELS M,CAT_MODEL_CLASSIFICATION mc ,CAT_GROUP_KIT_DETAIL gkd " + subQuery + " and MGM.GROUP_NO=gkd.GRP_KIT_NO AND M.STATUS='COMPLETE' AND MGM.GROUP_NO = '" + temp_group + "' order by MGM.MODEL_NO");
                        String sqlQuery = ("SELECT distinct MGM.MAP_NAME, MGM.MODEL_NO, M.MODEL_NO,mc.ENGINE_SERIES,mc.ENGINE_MODEL,gkd.P1 FROM CAT_MODEL_GROUP MGM(NOLOCK), CAT_MODELS(NOLOCK) M,CAT_MODEL_CLASSIFICATION(NOLOCK) mc ,CAT_GROUP_KIT_DETAIL(NOLOCK) gkd " + subQuery + " and MGM.GROUP_NO=gkd.GRP_KIT_NO AND M.STATUS='COMPLETE' AND MGM.GROUP_NO = '" + temp_group + "' order by MGM.MODEL_NO");
                        stmt = conn.prepareStatement(sqlQuery);
                        rs = stmt.executeQuery();
                        }
                        else
                        {
                           // rs = stmt.executeQuery("SELECT distinct MGM.MAP_NAME, MGM.MODEL_NO, M.MODEL_NO,mc.ENGINE_SERIES,mc.ENGINE_MODEL,gkd.P1 FROM CAT_MODEL_GROUP MGM, CAT_MODELS M,CAT_MODEL_CLASSIFICATION mc ,CAT_GROUP_KIT_DETAIL gkd " + subQuery + " and MGM.GROUP_NO=gkd.GRP_KIT_NO AND MGM.GROUP_NO = '" + temp_group + "' order by MGM.MODEL_NO");
                        	String sqlQuery1 = ("SELECT distinct MGM.MAP_NAME, MGM.MODEL_NO, M.MODEL_NO,mc.ENGINE_SERIES,mc.ENGINE_MODEL,gkd.P1 FROM CAT_MODEL_GROUP MGM(NOLOCK), CAT_MODELS(NOLOCK) M,CAT_MODEL_CLASSIFICATION(NOLOCK) mc ,CAT_GROUP_KIT_DETAIL(NOLOCK) gkd " + subQuery + " and MGM.GROUP_NO=gkd.GRP_KIT_NO AND MGM.GROUP_NO = '" + temp_group + "' order by MGM.MODEL_NO");
                        	stmt = conn.prepareStatement(sqlQuery1);
                        	rs = stmt.executeQuery();
                        }

                        if (rs.next())
                        {

                            do
                            {
                                tempHeading = rs.getString(1);
                                allMODELS.addElement(rs.getString(2));
                                allMODELS.addElement(rs.getString(4));
                                allMODELS.addElement(rs.getString(5));
                                tempGroupDesc=rs.getString(6);
                            }
                            while (rs.next());
                        }
                        rs.close();

                        if (allMODELS.size() > 0)
                        {

                            if (sNo == 1)
                            {
                                counter++;
                                flag = true;

                                if (part.startsWith("TBA"))
                                {
                                    ps.println(object_pageTemplate.tableHeader("Applicable " + PageTemplate.MODEL_NO + "s/" + PageTemplate.GROUP + "s For Part: \"" + PageTemplate.tbaPart + "\"", width, height));
                                }
                                else
                                {
                                    ps.println(object_pageTemplate.tableHeader("Applicable " + PageTemplate.MODEL_NO + "s/" + PageTemplate.GROUP + "s For Part: " + part.replaceAll("_", "&nbsp;"), width, height));
                                }

                                ps.println("<table valign=top width=98% border=1 cellspacing=0 cellpadding=3 bordercolor = #CCCCCC>");

                                ps.println("<tr>");
                                ps.println("<td align=center class=heading1 width = 10%>");
                                ps.println("S. No.");
                                ps.println("</td> ");

                                ps.println("<td valign=top class=heading1 width = 50%>");
                                ps.println(PageTemplate.MODEL_NO);
                                ps.println("</td> ");

                                ps.println("<td valign=top class=heading1 width = 40%>");
                                ps.println(PageTemplate.GROUP);
                                ps.println("</td> ");

                                ps.println("	</tr>");
                                ps.println("	</table>");
                                ps.println("<div style=\"float: left; width: 100%; height: 240px;  overflow: auto; \"> ");//380//background-color: pink;//border: 1px solid black;
                                ///ps.println("<div class=\"maindiv\" style=\"overflow-X:hidden; overflow-Y: scroll; height:380;\">");//380

                                ps.println("<table align=\"center\" width=98% border=0 cellspacing=1 cellpadding=2 bordercolor = #CCCCCC>");
                                //ps.println("    <tr>                       ");
                            }
                            ps.println("<tr >");
                            ps.println("<td  width=51px align=\"center\" class=" + cssClass + " valign=\"top\">");
                            ps.println(sNo);
                            ps.println("</td> ");                            

                            ps.println("<td  align=left class=" + cssClass + ">");

                            for (int j = 0; j < allMODELS.size(); j += 3)
                            {
                                modelNo = "" + allMODELS.elementAt(j);
                                modelDesc = "";
                                //rs = stmt.executeQuery("SELECT DESCRIPTION FROM CAT_MODELS WHERE MODEL_NO = '" + modelNo + "'");
                                String sqlquery2 = ("SELECT DESCRIPTION FROM CAT_MODELS(NOLOCK) WHERE MODEL_NO = '" + modelNo + "'");
                                stmt = conn.prepareStatement(sqlquery2);
                                rs = stmt.executeQuery();
                                if (rs.next())
                                {
                                    modelDesc = rs.getString(1);
                                }
                                rs.close();

                                if (j != 0)
                                {
                                    ps.println("<br>");
                                }

                                if (linksReq == null)
                                {
                                    ps.println("<a href=\"ViewModel?engineModel=" + allMODELS.elementAt(j + 2) + "&engineSeries=" + allMODELS.elementAt(j + 1) + "&model=" + modelNo + "&highGroup=" + temp_group + "\" target = right>");
                                }

                                ps.println(modelDesc + " [ " + modelNo + " ]");
                                ps.println("</a>");
                            }
                            ps.println("</td> ");

                            ps.println("<td nowrap  width=200px valign=top class=" + cssClass + " align = left>");
                            if (linksReq == null)
                            {
                                ps.println("<a href=\"" + servletURL + "ViewGroupImage_BOM?engineModel=" + allMODELS.elementAt(2) + "&engineSeries=" + allMODELS.elementAt(1) + "&group=" + temp_group + "&model=" + allMODELS.elementAt(0) + "&partSearch=" + part + "\" target = right>");
                            }

                            ps.println(tempGroupDesc);
                            ps.println("</a>");
                            ps.println("</td> ");
                            ps.println("	</tr>");
                            sNo++;

                        }
//                        else
//                        {
//                            message = "Unused Part";
//                            ps.println(object_pageTemplate.tableHeader1(message, width, height));
//                            return;
//
//                        }
                    }
                    if (flag)
                    {
                        ps.println("</table>");
                        ps.println("</div>");

                        ps.println(object_pageTemplate.tableFooter());
                    }

                }


                if (allSKITS.size() > 0)
                {
                    counter++;

                    if (part.startsWith("TBA"))
                    {
                        ps.println(object_pageTemplate.tableHeader("APPLICABLE KITS FOR PART: \"" + PageTemplate.tbaPart + "\"", width, height));
                    }
                    else
                    {
                        ps.println(object_pageTemplate.tableHeader("APPLICABLE KITS FOR PART: " + part.replaceAll("_", "&nbsp;"), width, height));
                    }

                    ps.println("<table valign=top align=\"center\" width=98% border=1 cellspacing=0 cellpadding=3 bordercolor = #CCCCCC>");
                    ps.println("<tr>");
                    ps.println("<td align=center class=heading1 width = 10%>");
                    ps.println("S. No.");
                    ps.println("</td> ");

                    ps.println("<td valign=top align=left class=heading1 width = 40%>");
                    ps.println("KIT NO");
                    ps.println("</td> ");

                    ps.println("<td valign=top align=left class=heading1 width = 50%>");
                    ps.println("KIT DESCRIPTION");
                    ps.println("</td> ");
                    ps.println("	</tr>");
                    ps.println("	</table>");
                    ps.println("<div style=\"float: left; width: 100%; height: 90px;  overflow: auto; \"> ");//380//background-color: pink;//border: 1px solid black;
                    //ps.println("<div class=\"maindiv\" style=\"overflow-X:hidden; overflow-Y: scroll; height:380;\">");//380

                    ps.println(" <table width=100%  valign=top align=\"center\" border=0 cellspacing=1 cellpadding=2 bordercolor = #CCCCCC >");
                    //ps.println("    <tr>                       ");


                    for (int i = 0; i < allSKITS.size(); i++)
                    {
                        String kitNo = "" + allSKITS.elementAt(i);


                        if (i % 2 == 0)
                        {
                            cssClass = "links_txt_left";
                        }
                        else
                        {
                            cssClass = "links_txt_left_Alt";
                        }


                        ps.println("<tr>");
                        ps.println("<td align=center class=" + cssClass + " width = 10%>");
                        ps.println(i + 1);
                        ps.println("</td> ");

                        ps.println("<td valign=top align=left class=" + cssClass + " width = 40%>");

                        if (linksReq == null)
                        {
                            //ps.println("<a href='" + servletURL + "NewSKits?sKitNo=" + kitNo + "'>");
                            ps.println("<a href=\"" + servletURL + "PartDetails?partNo=" + kitNo + "&compType=KIT\">");
                        }
                        if (kitNo.startsWith("TBA"))
                        {
                            ps.println(PageTemplate.tbaPart);
                        }
                        else
                        {
                            ps.println(kitNo.replaceAll("_", " "));
                        }

                        ps.println("</td> ");

                        ps.println("<td class=" + cssClass + " align=left  width = 50%>");

                        if (linksReq == null)
                        {
                            ps.println("<a href=\"" + servletURL + "PartDetails?partNo=" + kitNo + "&compType=KIT\">");
                        }

                        String query = ("SELECT P1 FROM CAT_PART(NOLOCK)  WHERE part_no='" + kitNo + "'");
                       stmt = conn.prepareStatement(query);
                       rs = stmt.executeQuery();
                        // rs = stmt.executeQuery(query);
                        if (rs.next())
                        {
                            ps.println(rs.getString(1));
                        }
                        rs.close();

                        ps.println("</td> ");
                        ps.println("	</tr>");

                    }

                    ps.println("</table>");
                    ps.println("</div>");
                    ps.println(object_pageTemplate.tableFooter());
                }
                //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

                if ((counter == 1) || (allSKITS.size() == 0 && allGROUPS.size() == 0 && (compType.equalsIgnoreCase("PRT") || compType.equalsIgnoreCase("ASM"))))
                {
                    message = "Unused Part";
                    ps.println(object_pageTemplate.tableHeader1(message, width, height));
                    return;
                    //object_pageTemplate.ShowMsg(ps, "FAILURE", "UNUSED PART/ASSEMBLY/KIT.", "NO", "", "", "", imagesURL);
                }

                ps.println("</body>");
                ps.println("</html>");
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
            wps.close();
        }
    }
}
