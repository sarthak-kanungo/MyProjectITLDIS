package viewEcat.comEcat;

/*
File Name: ModelPrintable.java
PURPOSE: It is used for printing the complete model BOM and Image with Page Setting as Letter and all margin 0.163
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModelPrintable extends HttpServlet {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {


        java.util.Date date_today = new java.util.Date();
        DateFormat df2 = new SimpleDateFormat("dd-MMM-yyyy");
        String todaysDate = df2.format(date_today);

        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        res.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
        res.setHeader("Expires", "0");
        res.setHeader("Pragma", "no-cache");

        try {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            String date_OR_serial = (String) session.getValue("date_OR_serial");
            java.sql.Date inputDate = (java.sql.Date) session.getValue("input_Date");
            java.sql.Date buckleDate = (java.sql.Date) session.getValue("buckleDate");
            String serialNo = (String) session.getValue("input_serialNo");

            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");

            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String imagesURL = object_pageTemplate.imagesURL();

            String groupJPG = object_pageTemplate.groupJPG();

            String groupDesc = null;
            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            int vc = 0;

            if (session_id.equals(getSession)) {
                Connection conn = holder.getConnection();

                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));



                /*########################################*/

                //Statement stmt;
                PreparedStatement stmt = null;
                Statement stmt1, stmt2;
                ResultSet rs;
                ResultSet rs1;

                //stmt = conn.createStatement();
                stmt1 = conn.createStatement();
                stmt2 = conn.createStatement();

                String modelNo = req.getParameter("model");

                Vector<String> totalGroups = new Vector();
                Vector dummytotalGroups = new Vector();

                // GET THE LAST CD INSTALLED

                //*********************************************************************************

                // GET THE LAST PATCH INSTALLED FOR LAST CD INSTALLED


                //*********************************************************************************

                String engSeries = "";
                //String bulletinNo = "";

                //rs = stmt.executeQuery("SELECT engine_series FROM CAT_MODEL_CLASSIFICATION where MODEL_NO = '" + modelNo + "'");
                String query = ("SELECT engine_series FROM CAT_MODEL_CLASSIFICATION(NOLOCK) where MODEL_NO = '" + modelNo + "'");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    engSeries = rs.getString(1);
                    //bulletinNo = rs.getString(5);
                }
                rs.close();

                java.sql.Date compDate = new java.sql.Date(0);
                String modelDesc = modelNo;
               // rs = stmt.executeQuery("SELECT COMPLETION_DATE,DESCRIPTION FROM CAT_MODELS where MODEL_NO = '" + modelNo + "'");
                String query1 = ("SELECT COMPLETION_DATE,DESCRIPTION FROM CAT_MODELS(NOLOCK) where MODEL_NO = '" + modelNo + "'");
                stmt = conn.prepareStatement(query1);
                rs = stmt.executeQuery();
                if (rs.next()) {
                    compDate = rs.getDate(1);
                    if (compDate == null) {
                        compDate = new java.sql.Date(0);
                    }
                    modelDesc = rs.getString(2);
                }
                rs.close();



                String tableName_MG = "CAT_MODEL_GROUP";
                String temp_1 = "";
                String temp_2 = "";
                String temp_3 = "";
                String temp_4 = "";


                Vector distinctGrpTypes = new Vector();				// For storing distinct group aggregate types

                Vector grpNoWithTypeVec = new Vector();

                HashMap groupDescMap = new HashMap();
                HashMap groupNameMap = new HashMap();
                // rs = stmt.executeQuery("SELECT MG.GROUP_NO,GKD.GK_TYPE,MG.GROUP_TYPE FROM " + tableName_MG + " MG, GROUP_KIT_DETAIL GKD WHERE MG.MODEL_NO = '" + modelNo + "' AND MG.GROUP_NO  = GKD.GRP_KIT_NO AND MG.REV_NO = " + revNo + " ORDER BY MG.TYPE_LEVEL, MG.GROUP_TYPE, GKD.P1");
               // rs = stmt.executeQuery("SELECT MG.GROUP_NO,GKD.GK_TYPE,MG.GROUP_TYPE,MG.MAP_NAME, GKD.p1 FROM " + tableName_MG + " MG, CAT_GROUP_KIT_DETAIL GKD WHERE MG.MODEL_NO = '" + modelNo + "' AND MG.GROUP_NO  = GKD.GRP_KIT_NO ORDER BY MG.GROUP_SEQUENCE");
               String query2 = ("SELECT MG.GROUP_NO,GKD.GK_TYPE,MG.GROUP_TYPE,MG.MAP_NAME, GKD.p1 FROM " + tableName_MG + " MG, CAT_GROUP_KIT_DETAIL GKD WHERE MG.MODEL_NO = '" + modelNo + "' AND MG.GROUP_NO  = GKD.GRP_KIT_NO ORDER BY MG.GROUP_SEQUENCE");
               stmt = conn.prepareStatement(query2);
               rs = stmt.executeQuery();
                if (rs.next()) {
                    do {
                        temp_1 = rs.getString(1);
                        temp_2 = rs.getString(2);
                        temp_3 = rs.getString(3);
                        temp_4 = rs.getString(4);
                        //System.out.println("Group_no temp_1"+temp_1 +"and Group Type temp_2"+temp_2+" and temp_3"+temp_3);
                        if (temp_2.equals("G")) {
                        //if (temp_2 != null && temp_2.equals("G")) {
                            totalGroups.addElement(temp_1);				// adding in total groups vector sorted on description
                            dummytotalGroups.addElement(temp_1);			// adding in dummy vector for further sorting on group no
                            grpNoWithTypeVec.addElement(temp_1);			// adding in vector for storing distinct group no & aggregate type
                            grpNoWithTypeVec.addElement(temp_3);
                        } else {
                            // totalKits.addElement(temp_1);
                        }
                        groupDescMap.put(temp_1, rs.getString(5));
                        groupNameMap.put(temp_1, temp_4);
                    } while (rs.next());
                }
                rs.close();

                // New Code for sorting by Group Map


                //Collections.sort(dummytotalGroups);
                //Sorting the map

                /*      String temp_group = "";
                String temp_group1 = "";
                int indexofA = 0;
                int indexofUnderscore = 0;


                for (int v = 0; v < dummytotalGroups.size(); v++)
                {
                if (v < dummytotalGroups.size() - 1)
                {
                temp_group = "" + dummytotalGroups.elementAt(v);
                temp_group1 = "" + dummytotalGroups.elementAt(v + 1);
                indexofA = temp_group.indexOf("A");
                indexofUnderscore = temp_group.indexOf("_");

                if (indexofA != -1)
                {
                if (indexofUnderscore > indexofA)
                {
                dummytotalGroups.set(v, temp_group1);
                dummytotalGroups.set(v + 1, temp_group);
                v++;
                }
                }
                }
                }
                 */
                String tempGroupType = "";

                ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                //rs = stmt.executeQuery("SELECT DISTINCT GROUP_TYPE,TYPE_LEVEL FROM " + tableName_MG + " WHERE MODEL_NO = '" + modelNo + "' AND REV_NO=" + revNo + "  ORDER BY TYPE_LEVEL, GROUP_TYPE");
                //rs = stmt.executeQuery("SELECT DISTINCT GROUP_TYPE,TYPE_LEVEL, GROUP_SEQUENCE FROM " + tableName_MG + " WHERE MODEL_NO = '" + modelNo + "' ORDER BY GROUP_SEQUENCE");
                String sqlQuery = ("SELECT DISTINCT GROUP_TYPE,TYPE_LEVEL, GROUP_SEQUENCE FROM " + tableName_MG + " WHERE MODEL_NO = '" + modelNo + "' ORDER BY GROUP_SEQUENCE");
               stmt = conn.prepareStatement(sqlQuery);
               rs = stmt.executeQuery();
                if (rs.next()) {
                    do {
                        tempGroupType = rs.getString(1);
                        if (!distinctGrpTypes.contains(tempGroupType)) {
                            distinctGrpTypes.addElement(tempGroupType);       // adding in vector for storing distinct group aggregate types
                        }
                    } while (rs.next());
                }
                rs.close();

                //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////



                ps.println("<html>");
                ps.println("<head>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
                ps.println(" <object classid='clsid:1663ed61-23eb-11d2-b92f-008048fdd814' id='factory' codebase=" + imagesURL + "/smsx.cab#Version=6,5,439,12 style='display:none' viewastext></object>");
                ps.println("<title>BOM of MODEL NUMBER. " + modelNo + "</title>");
                ps.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">");
                ps.println("<STYLE>");
                ps.println(".break { page-break-after: always; }");
                ps.println("</STYLE>");
                ps.println("<script language=javascript>");
                ps.println(" function PrintBasic()");
                ps.println("   {");
                ps.println("try");
                ps.println("{");
                ps.println("   document.factory.printing.header = '';");
                ps.println("   document.factory.printing.footer = '';");
                ps.println("   document.factory.printing.portrait = true;");
                ps.println("   document.factory.printing.leftMargin = 0.163;");
                ps.println("   document.factory.printing.topMargin = 0.163;");
                ps.println("   document.factory.printing.rightMargin = 0.163;");
                ps.println("   document.factory.printing.bottomMargin = 0.163;");

                ps.println("} catch (e) { ");
                ps.println("alert(e);");
                ps.println("alert('Please verify that your print settings have a Landscape orientation and minimum margins.');");
                ps.println("} ");
                ps.println("  }");
                ps.println("  </script>");

                ps.println("</head>");
                ps.println("<script>PrintBasic()</script>");
                ps.println("<body >");
                //......................................................PRINTING OF COVER PAGE STARTS


                ps.println("<h1 class=\"break\">");
                ps.println("<table width=100% height=100%  border=0>");
                ps.println("  <tr>");
                ps.println("    <td height=50>&nbsp;</td>");
                ps.println("  </tr>");
                ps.println("  <tr>");
                ps.println("		<td height=40 align=center bgcolor=#000000>");
                ps.println("			<table width=100% height=100%  border=0>");
                ps.println("				<tr align=center bgcolor=#000000>");
                ps.println("				    <td width=30% height=40 align=left valign = middle>&nbsp;");
                ps.println("					</td>");
                ps.println("				    <td width=60% align=left valign=middle >");
                ps.println("						<font color= #FFFFFF size=6 face=arial><b>");
                ps.println("							Parts Catalog");
                ps.println("						</font>");
                ps.println("					 </td>");
                ps.println("				</tr>");
                ps.println("			</table>");
                ps.println("		</td>");
                ps.println("  </tr>");
                ps.println("  <tr>");
                ps.println("    <td>&nbsp;</td>");
                ps.println("  </tr>");
                ps.println("  <tr>");
                ps.println("		<td height=40 align=center bgcolor=#000000>");

                ps.println("			<table width=100% height=100%  border=0>");
                ps.println("				<tr align=center bgcolor=#000000>");
                ps.println("				    <td width=30% height=40 valign=top>&nbsp;</td>");
                ps.println("				    <td width=60% align=left valign=middle >");
                ps.println("						<font color= #FFFFFF size=6 face=arial><b>");
                ps.println(engSeries);
                ps.println("						</font>");
                ps.println("					 </td>");
                ps.println("				</tr>");
                ps.println("			</table>");

                ps.println("		</td>");
                ps.println("  </tr>");

                ps.println("  <tr>");
                ps.println("    <td>&nbsp;</td>");
                ps.println("  </tr>");

                ps.println("  <tr>");
                ps.println("		<td height=40 align=center bgcolor=#000000>");

                ps.println("			<table width=100% height=100%  border=0>");
                ps.println("				<tr align=center bgcolor=#000000>");
                ps.println("				    <td width=30% height=40 valign=top>&nbsp;</td>");
                ps.println("				    <td width=60% align=left valign=middle >");
                ps.println("						<font color= #FFFFFF size=3 face=arial><b>");
                ps.println(modelDesc);
                ps.println("						</font>");
                ps.println("					 </td>");
                ps.println("				</tr>");
                ps.println("			</table>");

                ps.println("		</td>");
                ps.println("  </tr>");


                ps.println("  <tr>");
                ps.println("    <td height=480>&nbsp;</td>");
                ps.println("  </tr>");

                ps.println("  <tr>");
                ps.println("    <td>&nbsp;</td>");
                ps.println("  </tr>");

                ps.println("	 <tr>");
                ps.println("		<td height=100 align=center bgcolor=#000000><font color= #FFFFFF>");
                ps.println("			<font size = 5 face=arial><b>International Tractors Ltd.</b></font><br>");
                ps.println("			<font size = 2 face=arial></font>");
                ps.println("		</td>");
                ps.println("  </tr>");


                ps.println("  <tr>");
                ps.println("    <td height=50>&nbsp;</td>");
                ps.println("  </tr>");


                ps.println("</table>");
                ps.println("</h1>");

                //***********************************************************************//
                //......................................................PRINTING OF COVER PAGE ENDS

                vc = vc + 1024;
                int pageNo = 1;
                int pagePerPart = 0;

                ////*********************************************************************************************/////

                String groupMap = "";
                int indexCount = 30;
                int printIndexCount = 0;
                int grp_arr_count = 0;
                double grp_arr_count1 = 0;
                int partsPerPage = 44;
                LinkedHashMap<String, GroupBom> groupBomMap = new LinkedHashMap();

                //........................................................................PRINTING OF INDEX PAGE STARTS

                for (int i = 0; i < totalGroups.size(); i = printIndexCount) {
                    ps.println("<h1 class=\"break\">");

                    indexCount = 30;


                    /*          if (totalGroups.size() < i + indexCount)
                    {
                    printIndexCount = totalGroups.size();
                    }
                    else
                    {
                    printIndexCount = i + indexCount;
                    }
                     */
                    ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 width=695>");

                    ps.println("<tr>");
                    ps.println("<td valign=top align = right>");
                    ps.println("<font face=Helvetica size=1 color=#999999>");
                    ps.println("Print Date: " + todaysDate);
                    ps.println("</font>");
                    ps.println("</td>");
                    ps.println("</tr>");

                    /*ps.println("<tr>");
                    ps.println("<tr>");
                    ps.println("<td align=left>");
                    ps.println("<b>");
                    ps.println("Version : 3.4." + lastCD_no + "." + lastPATCH_no + " Applicable Date : " + appDate + " Model No. : " + modelNo);
                    ps.println(">");
                    ps.println("</td>");
                    ps.println("</tr>");*/

                    ps.println("<tr>");
                    ps.println("<td align=center class=\"print-TxtBig\">");
                    ps.println("<b>" + PageTemplate.GROUP.toUpperCase() + " INDEX</b>");
                    ps.println("</td>");
                    ps.println("</tr>");

                    /*ps.println("<tr >");
                    ps.println("<td>");
                    ps.println("<font face=Helvetica size=2 color=#000000><b>");
                    ps.println("GROUPS.");
                    ps.println("</b>>");
                    ps.println("</td>");
                    ps.println("</tr>");*/

                    ps.println("</table>");

                    ps.println("<center><table cellspacing=0 cellpadding=5 border=1 width=695 bordercolor=#CCCCCC>");
                    ps.println("<tr >");

                    ps.println("<td width=70 align = center  class=\"links_txt4\">");
                    ps.println("<b>");
                    ps.println("Sr No.");
                    ps.println("</b>");
                    ps.println("</td>");

                    ps.println("<td width=630 class=\"links_txt4\"> ");
                    ps.println("<b>");
                    ps.println(PageTemplate.GROUP + " Name");
                    ps.println("</b>");
                    ps.println("</td>");

//                    ps.println("<td width=150 align=center class=\"links_txt4\">");
//                    ps.println("<b>");
//                    ps.println(PageTemplate.GROUP + " No.");
//                    ps.println("</b>");
//                    ps.println("</td>");

                    ps.println("<td width=130 align = center class=\"links_txt4\">");
                    ps.println("<b>");
                    ps.println("Page No.");
                    ps.println("</b>");
                    ps.println("</td>");
                    ps.println("</tr>");

                    for (int j = i; j < totalGroups.size(); j++) {
                        if (indexCount == 0) {
                            break;
                        }
                        ps.println("<tr>");
                        ps.println("<td valign=top align = center class=\"links_txt\">");
                        ps.println("");
                        ps.println(j + 1);
                        ps.println("</b>");
                        ps.println("</td>");
                        ps.println("<td valign=top class=\"links_txt\">");
                        ps.println("");

                        //new chagges;

                        GroupBom ob = new GroupBom(conn, ps, date_OR_serial, inputDate, serialNo, totalGroups.elementAt(j), buckleDate);
                        GroupBom ob2 = ob.getGroupBom();
                        grp_arr_count1 = ob2.print_data_counter;
                        groupBomMap.put(totalGroups.elementAt(j), ob2);

                        //new changes

                        ps.println(groupDescMap.get("" + totalGroups.elementAt(j)));

                        ps.println("</b>");
                        ps.println("");
                        ps.println("</td>");

//                        ps.println("<td valign=top align=center class=\"links_txt\">");
//                        ps.println("");
//
//                        groupMap = "" + groupNameMap.get("" + totalGroups.elementAt(j));
//
//
////                        Integer s = Integer.parseInt(groupMap.substring(groupMap.indexOf("-") + 1).trim());
////                        NumberToRoman dec = new NumberToRoman();
////                        String roman = dec.convertIntegerToRoman(s);
//
//                        ps.println(groupMap.substring(groupMap.lastIndexOf("-") + 1).trim());
//                        ps.println("</b>");
//                        ps.println("</td>");

                        ps.println("<td valign=top align=center class=\"links_txt\">");

                        pagePerPart = Double.valueOf(Math.ceil(grp_arr_count1 / partsPerPage)).intValue();

                        if (pagePerPart > 0) {
                            ps.println((pageNo) + "-" + ((pageNo + (pagePerPart * 2)) - 1));
                            pageNo = ((pageNo + (pagePerPart * 2)) - 1);
                        } else {
                            ps.println((pageNo) + "-" + (pageNo + 1));
                        }

                        pageNo++;

                        ps.println("</td>");
                        ps.println("</tr>");

                        indexCount--;

                        printIndexCount = j + 1;

                        if (indexCount == 0) {
                            break;
                        }
                    }

                    ps.println("</table>");
                    ps.println("</h1>");
                    vc = vc + 1024;
                }



                int trHeight = 0;
                int counter = 0;
                pageNo = 1;
                LinkedHashMap<String, Integer> pageGroupMap = new LinkedHashMap();

                for (String temp_group2 : totalGroups) {

                    if (counter == 0 || trHeight >= 800) {
                        if (trHeight >= 800) {
                            trHeight = 0;
                        }


                        /*  ps.println("<br><h1 class=\"break\">");
                        ps.println("<center><table cellspacing=0 cellpadding=0 border=0  width=695>");
                        ps.println("<tr height=0>");
                        ps.println("<td valign=top align = right>");
                        ps.println("<font face=Helvetica size=1 color=#999999>");
                        ps.println("Print Date: " + todaysDate);
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("</tr>");



                        ps.println("<tr height=35>");
                        ps.println("<td align=center valign=middle class=\"print-TxtBig\">");
                        ps.println("<b>PICTORIAL TABLES INDEX</b>");
                        ps.println("</td>");
                        ps.println("</tr>");
                        ps.println("</table>");
                        ps.println("<table align=center valign=middle cellspacing=0 cellpadding=0 border=1  width=695 bordercolor=#000000>");
                        ps.println("<tr>");
                        ps.println("<td align=center valign=top>");
                        ps.println("<table width=685 border=0 cellspacing=0 cellpadding=0 align=center valign=top>");

                        trHeight += 35;
                         */                    }
                    groupMap = "" + groupNameMap.get(temp_group2);
                    groupDesc = "" + groupDescMap.get(temp_group2);


                    if ((counter % 2) == 0) {
                        //     ps.println("<tr>");
                    }
                    /*     ps.println("<td align=left valign=top>");
                    ps.println("<table border=0 valign=top width=340 align=left>");
                    ps.println("<tr>");

                    ps.println("<td>");
                    ps.println("<table valign=top width=337 align=left border=0 bordercolor = #000000 cellspacing=0 cellpadding=0>");
                    ps.println("<tr height=45>");

                    ps.println("<td width=30% align=left valign=top class=\"left_top\">");
                    ps.println("<b>" + groupMap + "<b>");
                    ps.println("</td>");
                    ps.println("<td width=70% align=left valign=top class=\"right_top\">");
                    ps.println("<b>" + groupDesc + "</b>");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("<tr >");
                    ps.println("<td align=left colspan=2 class=\"small_left_right34\">");
                     */
                    //  System.out.println(""+temp_group2);
                    GroupBom ob2 = groupBomMap.get(temp_group2);

                    if (ob2 == null) {
                        grp_arr_count1 = 0;
                        //     System.out.println("i m here");
                    } else {
                        grp_arr_count1 = ob2.print_data_counter;
                    }

                    pagePerPart = Double.valueOf(Math.ceil(grp_arr_count1 / partsPerPage)).intValue();
                    pageGroupMap.put(temp_group2, pageNo);
                    if (pagePerPart > 0) {
                        //      ps.println("<b>Page No. :" + (pageNo) + "-" + ((pageNo + (pagePerPart * 2)) - 1) + "</b>");
                        pageNo = ((pageNo + (pagePerPart * 2)) - 1);
                    } else {
                        //      ps.println("<b>Page No. :" + (pageNo) + "-" + (pageNo + 1) + "</b>");
                    }


                    pageNo++;

                    //ps.println("<b>Page No. : 5</b>");
                  /*  ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("<tr height=155>");//height=35
                    ps.println("<td colspan=2 align=center class=\"small_left_right_bottom_top\">");



                    File jpg_File = new File(ecatPATH + "/dealer/ecat_print/group_jpg/" + temp_group2 + ".jpg");
                    if (jpg_File.isFile())
                    {
                    ps.println("<IMG style=\"border-color:black;\" SRC=" + groupJPG + temp_group2 + ".jpg border = 0 bordercolor=#000000 width=105 height=105 >");
                    }
                    else
                    {
                    ps.println("<IMG style=\"border-color:black;\" SRC=" + groupJPG + "group_image.jpg border = 0 bordercolor=#000000 width=105 height=105 >");
                    }

                    //  ps.println("			<embed id=\"imghes\" border-width=\"2px;\" src=\"" + req.getContextPath() + "/svg/" + temp_group2 + ".svg\" class=\"electricalzoom\" type=\"image/svg+xml\" height=\"155\" width=\"190\">");
                    //   ps.println("			</embed>");

                    ps.println("</td>");
                    ps.println("</tr>");
                     */ trHeight += 100;

                    //  ps.println("</table>");
                    //  ps.println("</td></tr></table>");
                    //  ps.println("</td>");

                    counter++;

                    if ((counter % 2) == 0) {
                        //      ps.println("</tr>");
                    }
                    if (trHeight >= 800) {
                        //       ps.println("<tr height=" + (900 - trHeight) + "><td>&nbsp;</td></tr>");
                        //       ps.println("</table></td></tr></table></h1>");
                    }

                }
                if ((counter % 2) != 0) {
                    //     ps.println("</tr>");
                    //     ps.println("<tr height=" + (800 - trHeight) + "><td>&nbsp;</td></tr>");
                }
                if (trHeight < 800) {
                    //    ps.println("</table></td></tr></table></h1>");
                    //     ps.println("</table></td></tr></table></h1>");
                }

                int counter4 = 1;
                pageNo = 1;

                for (String group : totalGroups) //          for (int kk=20;kk< totalGroups.size();kk++)
                {
//String group = totalGroups.elementAt(kk);
//                    GroupBom ob = new GroupBom(conn, ps, date_OR_serial, inputDate, serialNo, group, buckleDate);
//                    GroupBom ob2 = ob.getGroupBom();
                    GroupBom ob2 = groupBomMap.get(group);
                    grp_arr_count = ob2.print_data_counter;
                    grp_arr_count1 = ob2.print_data_counter;
                    //pagePerPart = Double.valueOf(Math.ceil(grp_arr_count1 / partsPerPage)).intValue();
                    pageNo = pageGroupMap.get(group);

                    //        System.out.println("pageNo"+pageNo);
                    //     System.out.println("group"+group);

                    String[][] grp_arr = new String[ob2.print_data_counter][ob2.no_array_parameters];
                    grp_arr_count = ob2.print_data_counter;

                    // Get_groupInfo ob_get_grp_inf = new Get_groupInfo();
                    //int revNo = 0;
                    // Get_groupInfo ob_get_grp_inf = new Get_groupInfo(conn, ps, date_OR_serial, inputDate, serialNo, group);
                    // int revNo = ob_get_grp_inf.get_groupRevisionNo();
                    //                String var_REF_NO = "";
//                String var_DESCRIPTION = "";
//                String var_SERVICEABLE = "";
//                String var_REMARK = "";
//
//                String var_HEADER_DESCRIPTION = "";

                    for (int i = 0; i < grp_arr_count; i++) {

                        for (int aa = 0; aa < ob2.no_array_parameters; aa++) {
                            grp_arr[i][aa] = ob2.print_data[i][aa];
                            if (grp_arr[i][aa] == null) {
                                grp_arr[i][aa] = " &nbsp;";
                            }
                        }

                        /*var_REF_NO = "";
                        var_DESCRIPTION = "";
                        var_SERVICEABLE = "";
                        var_REMARK = "";
                        var_HEADER_DESCRIPTION = "";

                        rs1 = stmt1.executeQuery("SELECT distinct p1,p2,gkb.remarks,p4,p5,part_type FROM part p,group_kit_bom gkb WHERE gkb.component=p.part_no and p.part_no ='" + grp_arr[i][0].replaceAll("&nbsp;", "") + "' and gkb.grp_kit_no='" + group + "'");
                        if (rs1.next()) {
                        var_DESCRIPTION = rs1.getString(1);
                        var_REF_NO = rs1.getString(2);
                        var_REMARK = rs1.getString(3);
                        var_SERVICEABLE = rs1.getString(4);
                        var_HEADER_DESCRIPTION = rs1.getString(5);
                        }
                        rs1.close();


                        if (var_DESCRIPTION == null) {
                        var_DESCRIPTION = "";
                        }

                        grp_arr[i][9] = var_REF_NO;
                        grp_arr[i][10] = var_DESCRIPTION.toUpperCase();
                        grp_arr[i][11] = var_SERVICEABLE;
                        grp_arr[i][12] = var_REMARK;
                        grp_arr[i][13] = var_HEADER_DESCRIPTION;
                         */

                    }

                    /*  for (int i = 0; i < grp_arr_count; i++)
                    {
                    for (int aa = 0; aa < 16; aa++)
                    {
                    if (grp_arr[i][aa] == null)
                    {
                    grp_arr[i][aa] = " &nbsp;";
                    }
                    }
                    }
                     */


                    groupMap = "" + groupNameMap.get(group);
                    String groupDescription = "" + groupDescMap.get(group);

                    /////////////////////////////////////////////////////////////////////////////////
                    String part_array[] = new String[grp_arr.length];
                    int part_counter = 0;
                    String linkPart1 = "";
                    for (int i = 0; i < grp_arr.length; i++) {
                        if (grp_arr[i][1].equals("AP") || grp_arr[i][1].equals("AA")) {
                            //
                        } else {
                            linkPart1 = grp_arr[i][0].replaceAll("&nbsp;", "");
                            part_array[part_counter] = linkPart1;
                            part_counter++;
                        }
                    }


                    //	PAGE GENERATION STARTS

                    trHeight = 20;

                    //..............................PRINTING PER PAGE OF GROUP BOM STARTS


                    int print_count = 0;
                    int Checker = 1;


                    for (int i = 0; i < grp_arr_count; i += partsPerPage) {
                        if (grp_arr_count < i + partsPerPage) {
                            print_count = grp_arr_count;
                        } else {
                            print_count = i + partsPerPage;
                        }

                        ps.println("<h1 class=\"break\">");
                        ps.println("<center><table cellspacing=0 cellpadding=0 border=0 width=695px;><tr><td >");
                        ps.println("<table cellspacing=0 cellpadding=0 border=0 width=695px;><tr><td >");
                        ps.println("<table cellspacing=0 cellpadding=2 border=0 width=695px;>");
                        ps.println("<tr>");
                        ps.println("<td valign=top colspan=0 align = right height=0>");
                        ps.println("<font face=Helvetica size=1 color=#999999>");
                        ps.println("Print Date: " + todaysDate);
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("</tr>");
//                        ps.println("<tr>");
//                        ps.println("<td class=small_left_right_bottom_head_1>");
//                        //ps.println("<font size=2>");
//                        ps.println(groupMap.toUpperCase());
//                        //ps.println(">");
//                        ps.println("</td>");
//                        ps.println("</tr>");
//                        ps.println("<tr>");
                        ps.println("<td class=small_left_right_bottom_head_1>"); //small_left_right_bottom_head_2
                        //ps.println("<font size=2>");
                        ps.println(groupDescription);
                        //ps.println(">");
                        ps.println("</td>");
                        ps.println("</tr>");
                        ps.println("</table></td></tr><tr><td style=\"padding-top:8px;\">");
                        //ps.println("</h1>");
                        //ps.println("<h1 class=\"break\">");
                        //ps.println("\n");
                        ps.println("<table cellspacing=0 cellpadding=2 border=0 width=695px;>");
                        ps.println("<tr>");
                        ps.println("<td align=center colspan=7 width =681 height = 920 class=small_left_right_bottom_top>");
                        //ps.println("<IMG SRC=" + imagePath + group + ".jpg width = 681 height = 711>");


                        File svg_File = new File(PageTemplate.svgPATH + "" + group + ".svg");
                        if (svg_File.isFile()) {
                            ps.println("			<embed id=\"imghes\" border-width=\"2px;\" src=\"" + req.getContextPath() + "/svg/" + group + ".svg\" class=\"electricalzoom\" type=\"image/svg+xml\" height=\"920\" width=\"681\">");
                            ps.println("			</embed>");
                        } else {
                            ps.println("<IMG style=\"border-color:black;\" SRC=" + groupJPG + "group_image.jpg border = 0 bordercolor=#000000 width=681 height=920 >");
                        }



                        ps.println("</td>");
                        ps.println("</tr>");
                        //ps.println("</table></td></tr></table></td></tr></table>");
                        ps.println("</table></td></tr></table></td></tr>");
                        ps.println("<tr><td>");
                        ps.println("<table cellspacing='0'  cellpadding='2'   border='0' width=695px;>");
                        ps.println("<tr  valign=top>");
                        ps.println("<td   align=right  class=\"links_txt\">");
                        ps.println("<b>" + ((pageNo++)) + "</b>");
                        ps.println("</td></tr></table>");

//                        ps.println("<td   align=right  class=\"links_txt\">");
//                        ps.println("<b>" + (counter4) + "&nbsp; of " + (++groupCount) + "</b>");
//                        ps.println("</td></tr></table>");
                        ///
                        ps.println("</td></tr>");
                        ps.println("</table></td></tr></table>");
                        ps.println("</h1>");

                        //ps.println("</h1>");
                        ps.println("<h1 class=\"break\">");
                        ps.println("<center><table cellspacing=0 cellpadding=0 border=0 width=695px;><tr><td style=\"padding-top:8px;\">");//table no5 start
                        ps.println("<table cellspacing=0 cellpadding=2 border=0 width=695px;>");//table no6 start
                        ps.println("<tr>");
                        ps.println("<td valign=top colspan=0 align = right height=0>");
                        ps.println("<font face=Helvetica size=1 color=#999999>");
                        ps.println("Print Date: " + todaysDate);
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("</tr>");
                        ps.println("<tr>");
                        ps.println("<td class=small_left_right_bottom_head_1>");//small_left_right_bottom_head_2
                        ps.println("<font size=2>");
                        ps.println(groupDescription);
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("</tr>");
                        ps.println("</table></td></tr><tr><td style=\"padding-top:8px;\">");//table no6 end
                        ps.println("<table cellspacing='0' cellpadding='0' border='0' width=695px;>");//table no7 start
                        ps.println("<tr>");
                        ps.println("<td align='center' width='7%' class='heading1_print_1'>");
                        ps.println("Fig&nbsp;No.");
                        ps.println("</td>");

                        ps.println("<td width='13%' align='left' class='heading1_print_2'>");
                        ps.println("&nbsp;Part No.");
                        ps.println("</td>");

                        ps.println("<td width='31%' align='left' class='heading1_print_1'>");
                        ps.println("&nbsp;Description");
                        ps.println("</td>");

                        ps.println("<td align = 'center' width='4%' class='heading1_print_2'>");
                        ps.println("Qty");
                        ps.println("</td>");

                        ps.println("<td align = 'center' width='5%' title=\"Interchangeability\" class='heading1_print_1'>");
                        ps.println("Int.");
                        ps.println("</td>");

                        ps.println("<td align = 'left' style=\"padding-left:5 px\" width='20%' class='heading1_print_2'>");
                        ps.println("Cut Off Chassis");
                        ps.println("</td>");

                        ps.println("<td align = 'left' style=\"padding-left:5 px\" width='20%' class='heading1_print_1'>");
                        ps.println("Remark");
                        ps.println("</td></tr>");


                        counter = 0;
                        for (int j = i; j < print_count; j++) {


                            if (grp_arr[j][8].equals("YES")) {


                                ps.println("<tr >");
                                ps.println("<td align = center>");
                                ps.println("&nbsp;");
                                ps.println("</td>");

                                ps.println("<td align = center>");
                                ps.println("&nbsp;");
                                ps.println("</td>");

                                ps.println("<td align = center>");
                                ps.println("&nbsp;");
                                ps.println("</td>");

                                ps.println("<td align = center>");
                                ps.println(grp_arr[j][13]);
                                ps.println("</td>");
                                ps.println("</tr>");
                            }
                            counter++;
                            ps.println("<tr height=" + trHeight + ">");

                            ps.println("<td align='center'  width='7%'  class='small_left_right12'>");
                            if (grp_arr[j][1].equals("AP") || grp_arr[j][1].equals("AA")) {
                                ps.println(grp_arr[j][1]);
                            } else {
                                ps.println(grp_arr[j][5]);
                                Checker++;

                            }
                            ps.println("</td>");
                            ps.println("<td  align='left'  width='13%' style=\"padding-left:5 px\" class='small_left_rightt'>");
                            if (grp_arr[j][14].equals("YES")) {
                                ps.println("");
                            }
                            if (grp_arr[j][0].startsWith("TBA")) {
                                ps.println("&nbsp;");
                            } else {
                                ps.println(grp_arr[j][0].replaceAll("_", "&nbsp;"));
                            }
                            ps.println("</td>");

                            ps.println("<td  align='left'  width='31%' style=\"padding-left:5 px\"  class='small_left_right12'>");
                            ps.println(grp_arr[j][10].equals("") ? "&nbsp" : grp_arr[j][10]);
                            ps.println("</td>");

                            ps.println("<td align='center'  width='4%'  class='small_left_rightt'>");
                            if (grp_arr[j][7].equals("YES")) {
                                ps.println("AR");
                            } else {
                                ps.println(grp_arr[j][3].equals("-") || grp_arr[j][3].equals("") ? "&nbsp" : grp_arr[j][3]);
                            }
                            ps.println("</td>");

                            ps.println("<td align='center' width='5%'   class='small_left_right12'>");
                            ps.println(grp_arr[j][16].equals("-") || grp_arr[j][16].equals("") ? "&nbsp" : grp_arr[j][16]);
                            ps.println("</td>");

                            ps.println("<td align='left'  width='20%' style=\"padding-left:5 px\" class='small_left_rightt'>");
                            ps.println(grp_arr[j][17].equals("-") || grp_arr[j][17].equals("") ? "&nbsp" : grp_arr[j][17]);
                            ps.println("</td>");

                            ps.println("<td align='left' width='20%' style=\"padding-left:5 px\"  class='small_left_right34'>");
                            ps.println(grp_arr[j][12].equals("") ? "&nbsp" : grp_arr[j][12]);
                            ps.println("</td>");

                            ps.println("</tr>");

                        }

                        ps.println("<tr height=" + (900 - (counter * 20)) + "  valign=top>");
                        ps.println("<td  width='7%'   class=small_left_right_bottom>");
                        ps.println("&nbsp;");
                        ps.println("</td>");

                        ps.println("<td   width='13%'  class=small_bottom>");
                        ps.println("&nbsp;");
                        ps.println("</td>");

                        ps.println("<td  width='31%'  class=small_left_right_bottom>");
                        ps.println("&nbsp;");
                        ps.println("</td>");
                        ps.println("<td   width='4%'  class=small_bottom>");
                        ps.println("&nbsp;");
                        ps.println("</td>");

                        ps.println("<td  width='5%' class=small_left_right_bottom>");
                        ps.println("&nbsp;");
                        ps.println("</td>");

                        ps.println("<td width='20%' class=small_bottom>");
                        ps.println("&nbsp;");
                        ps.println("</td>");

                        ps.println("<td  width='20%' class=small_left_right_bottom >");
                        ps.println("&nbsp;");
                        ps.println("</td>");

                        ps.println("</tr>");

                        ps.println("</table></td></tr>");//table no7 end
                        //ps.println("</table></td></tr>");//table no6 end
                        ps.println("</table>");//table no5 end
                        ps.println("</h1>");

                    }
                    counter4++;
                }
                stmt.close();
                stmt1.close();
                stmt2.close();
            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
            ps.println("</body></html>");
        } catch (Exception e) {
            e.printStackTrace();
            ps.println(e);
        } finally {
            ps.close();
            wps.close();
        }
    }
}
