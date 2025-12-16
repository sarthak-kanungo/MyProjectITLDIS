package viewEcat.comEcat;

/*
File Name: ModelPrintable_images.java
PURPOSE: It is used for printing all teh group images in a particular with Page Setting as Letter and all margin 0.163
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModelPrintable_images extends HttpServlet
{
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        //	Date date =  new Date();
        //	DateFormat df =DateFormat.getDateInstance(DateFormat.SHORT);
        //	String todaysDate=df.format(date);	

        java.util.Date date_today = new java.util.Date();
        DateFormat df2 = new SimpleDateFormat("dd-MMM-yyyy");
        String todaysDate = df2.format(date_today);

        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");

        res.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
        res.setHeader("Expires", "0");
        res.setHeader("Pragma", "no-cache");

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

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();

            String ecat_sp_imagesURL = "";
            ecat_sp_imagesURL = object_pageTemplate.ecat_sp_imagesURL();

            String group_imagesURL = "";
            group_imagesURL = object_pageTemplate.group_imagesURL();

            String backupimageURL = object_pageTemplate.backupimageURL();

            String groupJPG = "";
            groupJPG = object_pageTemplate.groupJPG();

            String imagesURL = object_pageTemplate.imagesURL();

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            if (session_id.equals(getSession))
            {
                Connection conn = holder.getConnection();
                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));
                
                

                /*########################################*/

                //Statement stmt;
                PreparedStatement stmt = null;
                //Statement stmt1, stmt2;
                PreparedStatement stmt1,stmt2;
                ResultSet rs;
                ResultSet rs1, rs2, rs3;

                //stmt = conn.createStatement();
                //stmt1 = conn.createStatement();
                //stmt2 = conn.createStatement();

                String modelNo = req.getParameter("model");

                Vector totalGroups = new Vector();
                Vector dummytotalGroups = new Vector();

                Vector totalKits = new Vector();

                // GET THE LAST CD INSTALLED

                String lastCD_no = "";

               // rs = stmt.executeQuery("SELECT MAX(sno) FROM CAT_CD");
                String query = ("SELECT MAX(sno) FROM CAT_CD(NOLOCK)");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
                if (rs.next())
                {
                    lastCD_no = rs.getString(1);
                }
                rs.close();

                //*********************************************************************************

                // GET THE LAST PATCH INSTALLED FOR LAST CD INSTALLED

                //              java.sql.Date lastPATCH_applicable_on = new java.sql.Date(0);
                int lastPATCH_no = 0;

                //rs = stmt.executeQuery("SELECT PATCH_NO,APP_FROM FROM CAT_PATCH WHERE CD_NO=" + lastCD_no + " ORDER BY PATCH_NO DESC");
                String query1 = ("SELECT PATCH_NO,APP_FROM FROM CAT_PATCH(NOLOCK) WHERE CD_NO=" + lastCD_no + " ORDER BY PATCH_NO DESC");
                stmt = conn.prepareStatement(query1);
                rs = stmt.executeQuery();
                if (rs.next())
                {
                    lastPATCH_no = Integer.parseInt(rs.getString(1));
                //                  lastPATCH_applicable_on = rs.getDate(2);
                }
                rs.close();

                //*********************************************************************************

                /*             String appType = "";
                String engModel = "";
                String bulletinNo = "";
                rs = stmt.executeQuery("SELECT * FROM MODEL_CLASSIFICATION where MODEL_NO = '" + modelNo + "'");
                if (rs.next())
                {
                engModel = rs.getString(3);
                appType = rs.getString(4);
                bulletinNo = rs.getString(5);
                }
                rs.close();
                 */
                java.sql.Date compDate = new java.sql.Date(0);
               // rs = stmt.executeQuery("SELECT COMPLETION_DATE FROM CAT_MODELS where MODEL_NO = '" + modelNo + "'");
                String query3 = ("SELECT COMPLETION_DATE FROM CAT_MODELS(NOLOCK) where MODEL_NO = '" + modelNo + "'");
                stmt = conn.prepareStatement(query3);
                rs = stmt.executeQuery();
                if (rs.next())
                {
                    compDate = rs.getDate(1);
                    if (compDate == null)
                    {
                        compDate = new java.sql.Date(0);
                    }
                }
                rs.close();

                //*********************************************************************************


                Vector distinctGrpTypes = new Vector();				// For storing distinct group aggregate types

                Vector grpNoWithTypeVec = new Vector();				// For storing group nos with aggregate types


                SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdfInput.parse(compDate.toString());

                SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
                String appDate = sdf.format(date);

                String tableName_MG = "CAT_MODEL_GROUP";
                //              String tableName_MG_MAP = "MODEL_GROUP_MAP";

                /*    int revNo = 0;
                String new_old = "";
                
                Get_modelInfo ob_get_rev_no = new Get_modelInfo(conn, ps, date_OR_serial, inputDate, serialNo, modelNo);
                revNo = ob_get_rev_no.get_modelRevisionNo();
                new_old = ob_get_rev_no.get_modelTable(revNo);
                
                if (new_old.equals("old"))
                {
                tableName_MG = "MODEL_GROUP_OLD";
                //                   tableName_MG_MAP = "MODEL_GROUP_MAP_OLD";
                }
                else
                {
                tableName_MG = "CAT_MODEL_GROUP";
                //                   tableName_MG_MAP = "MODEL_GROUP_MAP";
                }
                 */
                String temp_1 = "";
                String temp_2 = "";
                String temp_3 = "";

                // rs = stmt.executeQuery("SELECT MG.GROUP_NO,GKD.GK_TYPE,MG.GROUP_TYPE FROM " + tableName_MG + " MG, GROUP_KIT_DETAIL GKD WHERE MG.MODEL_NO = '" + modelNo + "' AND MG.GROUP_NO  = GKD.GRP_KIT_NO AND MG.REV_NO = " + revNo + " ORDER BY MG.TYPE_LEVEL, MG.GROUP_TYPE, GKD.P1");
                //rs = stmt.executeQuery("SELECT MG.GROUP_NO,GKD.GK_TYPE,MG.GROUP_TYPE FROM " + tableName_MG + " MG, CAT_GROUP_KIT_DETAIL GKD WHERE MG.MODEL_NO = '" + modelNo + "' AND MG.GROUP_NO  = GKD.GRP_KIT_NO ORDER BY MG.TYPE_LEVEL, MG.GROUP_TYPE, GKD.P1");
                String sqlQuery = ("SELECT MG.GROUP_NO,GKD.GK_TYPE,MG.GROUP_TYPE FROM " + tableName_MG + " MG, CAT_GROUP_KIT_DETAIL(NOLOCK) GKD WHERE MG.MODEL_NO = '" + modelNo + "' AND MG.GROUP_NO  = GKD.GRP_KIT_NO ORDER BY MG.TYPE_LEVEL, MG.GROUP_TYPE, GKD.P1");
                stmt = conn.prepareStatement(sqlQuery);
                rs = stmt.executeQuery();
                if (rs.next())
                {
                    do
                    {
                        temp_1 = rs.getString(1);
                        temp_2 = rs.getString(2);
                        temp_3 = rs.getString(3);

                        if (temp_2.equals("G"))
                        {
                            totalGroups.addElement(temp_1);					// adding in total groups vector sorted on type_level, group_type, description

                            dummytotalGroups.addElement(temp_1);			// adding in dummy vector for further sorting on group no

                            grpNoWithTypeVec.addElement(temp_1);			// adding in vector for storing distinct group no & aggregate type

                            grpNoWithTypeVec.addElement(temp_3);
                        }
                        else
                        {
                            totalKits.addElement(temp_1);
                        }
                    }
                    while (rs.next());
                }
                rs.close();

                // New Code for sorting by Group Map

                String temp_group = "";
                String temp_group1 = "";

                Collections.sort(dummytotalGroups);

                for (int v = 0; v < dummytotalGroups.size(); v++)
                {
                    if (v < dummytotalGroups.size() - 1)
                    {
                        temp_group = "" + dummytotalGroups.elementAt(v);
                        temp_group1 = "" + dummytotalGroups.elementAt(v + 1);
                        int indexofA = temp_group.indexOf("A");
                        int indexofUnderscore = temp_group.indexOf("_");

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

                //////////////////////////////////////////////////////////////////////////////////

                // rs = stmt.executeQuery("SELECT DISTINCT GROUP_TYPE, TYPE_LEVEL FROM " + tableName_MG + " WHERE MODEL_NO = '" + modelNo + "' AND REV_NO=" + revNo + "  ORDER BY TYPE_LEVEL, GROUP_TYPE");
                //rs = stmt.executeQuery("SELECT DISTINCT GROUP_TYPE, TYPE_LEVEL FROM " + tableName_MG + " WHERE MODEL_NO = '" + modelNo + "'  ORDER BY TYPE_LEVEL, GROUP_TYPE");
                String sqlQuery1 = ("SELECT DISTINCT GROUP_TYPE, TYPE_LEVEL FROM " + tableName_MG + " WHERE MODEL_NO = '" + modelNo + "'  ORDER BY TYPE_LEVEL, GROUP_TYPE");
                stmt = conn.prepareStatement(sqlQuery1);
                rs = stmt.executeQuery();
                if (rs.next())
                {
                    String tempGroupType = "";
                    do
                    {
                        tempGroupType = rs.getString(1);
                        if (!distinctGrpTypes.contains(tempGroupType))
                        {
                            distinctGrpTypes.addElement(tempGroupType);	// adding in vector for storing distinct group aggregate types

                        }
                    }
                    while (rs.next());
                }
                rs.close();


                String temp_group_type = "";
                String groupMap = "";

                //////////////////////////////////////////////////////////////////////////////////


                /*           String modelDesc = modelNo;
                rs = stmt.executeQuery("SELECT DESCRIPTION FROM MODELS WHERE MODEL_NO = '" + modelNo + "'");
                if (rs.next())
                {
                modelDesc = rs.getString(1);
                }
                rs.close();
                 */
                //......................................................PRINTING OF COVER PAGE STARTS

                ps.println("<html>");
                ps.println("<head>");
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
                ps.println("<body>");
                /*
                ps.println("<h1 class=\"break\">");	
                ps.println("<table width=100% height=100%  border=0>");
                ps.println("  <tr>");
                ps.println("    <td height=50>&nbsp;</td>");
                ps.println("  </tr>");
                ps.println("  <tr>");
                ps.println("		<td height=40 align=center bgcolor=#000000>");
                ps.println("			<table width=100% height=100%  border=0>");
                ps.println("				<tr align=center bgcolor=#000000>");
                ps.println("				    <td width=47% height=40 align=left valign = middle>");
                ps.println("						<img src="+imagesURL+"logo-print.gif width=59 height=58>");
                ps.println("					</td>");
                ps.println("				    <td width=38% align=left valign=middle >");
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
                ps.println("				    <td width=47% height=40 valign=top>&nbsp;</td>");
                ps.println("				    <td width=38% align=left valign=middle >");
                ps.println("						<font color= #FFFFFF size=6 face=arial><b>");
                ps.println(							engModel);
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
                ps.println("				    <td width=47% height=40 valign=top>&nbsp;</td>");
                ps.println("				    <td width=38% align=left valign=middle >");
                ps.println("						<font color= #FFFFFF size=3 face=arial><b>");
                ps.println(							appType);
                ps.println("								<br>");
                ps.println(							modelDesc);
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
                ps.println("		<td height=40 align=center bgcolor=#000000>");
                ps.println("			<table width=100% height=100%  border=0>");
                ps.println("				<tr align=center bgcolor=#000000>");
                ps.println("				    <td width=47% height=40 valign=top>&nbsp;</td>");
                ps.println("				    <td width=38% align=left valign=middle >");
                ps.println("						<font color= #FFFFFF size=3 face=arial><b>");
                ps.println("							Bulletin No. "+bulletinNo+" ("+appDate+")");
                ps.println("						</font>");
                ps.println("					 </td>");
                ps.println("				</tr>");
                ps.println("			</table>");
                ps.println("		</td>");
                ps.println("  </tr>");
                ps.println("  <tr>");
                ps.println("    <td>&nbsp;</td>");
                ps.println("  </tr>");
                ps.println("	 <tr>");
                ps.println("		<td height=100 align=center bgcolor=#000000><font color= #FFFFFF>");
                ps.println("			<font size = 5 face=arial><b>"+UtilityMapkeys1.tile1+"</b></font><br>");
                ps.println("			<font size = 2 face=arial>Registered Office: Kothrud, Pune 411 038 (India)");
                ps.println("		</td>");
                ps.println("  </tr>");
                ps.println("  <tr>");
                ps.println("    <td height=50>&nbsp;</td>");
                ps.println("  </tr>");
                ps.println("</table>");
                ps.println("</h1>");	
                ps.println("<h1 class=\"break\">");	
                ps.println("<center><br>");	
                ps.println("<IMG SRC="+imagesURL+"page-1.jpg width = 681 height = 95% border=1>");
                ps.println("</h1>");	
                ps.println("<h1 class=\"break\">");	
                ps.println("<center><br>");	
                ps.println("<IMG SRC="+imagesURL+"page-2.jpg width = 681 height = 95% border=1>");
                ps.println("</h1>");	
                ps.println("<h1 class=\"break\">");	
                ps.println("<center><br>");	
                ps.println("<IMG SRC="+imagesURL+"page-3.jpg width = 681 height = 95% border=1>");
                ps.println("</h1>");	
                 */
                //......................................................PRINTING OF COVER PAGE ENDS






                int indexCount = 30;
                int printIndexCount = 0;
                int vc = 0;
                int indxGrpImageCnt = 0;
                int startCount = 0;

                //........................................................................PRINTING OF INDEX PAGE STARTS

                String tempGroupType = "";

                for (int i = 0; i < totalGroups.size(); i = printIndexCount)
                {

                    ps.println("<h1 class=\"break\">");
                    indexCount = 30;

                    /*  if (totalGroups.size() < i + indexCount)
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

                    ps.println("<tr>");
                    ps.println("<tr>");
                    ps.println("<td align=left>");
                    ps.println("<font face=Helvetica size=1 color=#000000><b>");
                    ps.println("Version : 3.4." + lastCD_no + "." + lastPATCH_no + " Applicable Date : " + appDate + " Model No. : " + modelNo);
                    ps.println("</font>");
                    ps.println("</td>");
                    ps.println("</tr>");

                    ps.println("<tr>");
                    ps.println("<td>");
                    //			ps.println("<H1>");
                    ps.println("<font size=5><b>GROUP / SUB-GROUP INDEX</b></font>");
                    //			ps.println("</H1>");
                    ps.println("</td>");
                    ps.println("</tr>");

                    ps.println("<tr >");
                    ps.println("<td>");
                    ps.println("<font face=Helvetica size=2 color=#000000><b>");
                    ps.println("GROUPS.");
                    ps.println("</b></font>");
                    ps.println("</td>");
                    ps.println("</tr>");

                    ps.println("</table>");

                    ps.println("<br><center><table cellspacing=0 cellpadding=5 border=1 width=695 bordercolor=#CCCCCC>");
                    ps.println("<tr >");

                    ps.println("<td width=50 align = center>");
                    ps.println("<font face=Helvetica size=2 color=#000000><b>");
                    ps.println("S NO.");
                    ps.println("</b><br></font>");
                    ps.println("</td>");

                    ps.println("<td width=500>");
                    ps.println("<font face=Helvetica size=2 color=#000000><b>");
                    ps.println("DESCRIPTION");
                    ps.println("</b><br></font>");
                    ps.println("</td>");

                    ps.println("<td width=145 align=center>");
                    ps.println("<font face=Helvetica size=2 color=#000000><b>");
                    ps.println("GROUP NO.");
                    ps.println("</b><br></font>");
                    ps.println("</td>");

                    ps.println("<td width=50 align = center>");
                    ps.println("<font face=Helvetica size=2 color=#000000><b>");
                    ps.println("PAGE NO.");
                    ps.println("</b><br></font>");
                    ps.println("</td>");
                    ps.println("</tr>");


                    for (int j = i; j < totalGroups.size(); j++)
                    {
                        if (!(grpNoWithTypeVec.elementAt(grpNoWithTypeVec.indexOf(totalGroups.elementAt(j)) + 1)).equals(tempGroupType))
                        {
                            tempGroupType = "" + grpNoWithTypeVec.elementAt(grpNoWithTypeVec.indexOf(totalGroups.elementAt(j)) + 1);
                            ps.println("<tr >");

                            ps.println("<td colspan=4 align = left>");
                            ps.println("<font face=Helvetica size=2 color=#000000><b>");
                            ps.println(tempGroupType.toUpperCase());
                            ps.println("</b><br></font>");
                            ps.println("</td>");
                            ps.println("</tr>");
                            // printIndexCount--;
                            indexCount--;
                        }

                        if (indexCount == 0)
                        {
                            break;
                        }
                        ps.println("<tr>");
                        ps.println("<td valign=top align = center>");
                        ps.println("<font face=Helvetica size=2 color=#000000>");
                        ps.println(j + 1);
                        ps.println("</b></font>");
                        ps.println("</td>");
                        ps.println("<td valign=top >");
                        ps.println("<font face=Helvetica size=2 color=#000000><b>");

                        rs = stmt.executeQuery("SELECT P1 FROM CAT_GROUP_KIT_DETAIL WHERE GRP_KIT_NO = '" + totalGroups.elementAt(j) + "'");
                        if (rs.next())
                        {
                            ps.println(rs.getString(1));
                        }
                        rs.close();

                        ps.println("</b>");
                        ps.println("</font>");
                        ps.println("</td>");

                        ps.println("<td valign=top align=center>");
                        ps.println("<font face=Helvetica size=2 color=#000000>");

                        groupMap = "" + totalGroups.elementAt(j);

                       // rs = stmt.executeQuery("SELECT MAP_NAME FROM CAT_MODEL_GROUP WHERE GROUP_NO = '" + totalGroups.elementAt(j) + "' and MODEL_NO = '" + modelNo + "'");
                        String sqlQuery2 = ("SELECT MAP_NAME FROM CAT_MODEL_GROUP(NOLOCK) WHERE GROUP_NO = '" + totalGroups.elementAt(j) + "' and MODEL_NO = '" + modelNo + "'");
                        stmt = conn.prepareStatement(sqlQuery2);
                        rs = stmt.executeQuery();
                        if (rs.next())
                        {
                            groupMap = rs.getString(1);
                        }
                        rs.close();

                        ps.println(groupMap);
                        ps.println("</b></font>");
                        ps.println("</td>");

                        ps.println("<td valign=top align=center>");
                        ps.println("<font face=Helvetica size=2 color=#000000>");

                        int index = dummytotalGroups.indexOf(totalGroups.elementAt(j));
                        index = index + 1;
                        ps.println("" + index);


                        ps.println("</font>");
                        indexCount--;

                        printIndexCount = j + 1;

                        if (indexCount == 0)
                        {
                            break;
                        }
                    }
                    ps.println("</td>");

                    ps.println("</tr>");
                    ps.println("</table>");
                    ps.println("</h1>");
                    vc = vc + 1024;
                }

                int kitIndexCount = 28;
                int kitPrintIndexCount = 0;

                for (int i = 0; i < totalKits.size(); i += kitIndexCount)
                {
                    ps.println("<h1 class=\"break\">");

                    if (totalKits.size() < i + kitIndexCount)
                    {
                        kitPrintIndexCount = totalKits.size();
                    }
                    else
                    {
                        kitPrintIndexCount = i + kitIndexCount;
                    }

                    ps.println("<br><center><table cellspacing=0 cellpadding=0 border=0 width=695>");

                    ps.println("<tr>");
                    ps.println("<td valign=top align = right>");
                    ps.println("<font face=Helvetica size=1 color=#999999>");
                    ps.println("Print Date: " + todaysDate);
                    ps.println("</font>");
                    ps.println("</td>");
                    ps.println("</tr>");

                    ps.println("<tr>");
                    ps.println("<td align=left>");
                    ps.println("<font face=Helvetica size=1 color=#000000><b>");
                    ps.println("Version : 3.4." + lastCD_no + "." + lastPATCH_no + " Applicable Date : " + appDate + " Model No. : " + modelNo);
                    ps.println("</font>");
                    ps.println("</td>");
                    ps.println("</tr>");

                    ps.println("<tr>");
                    ps.println("<td>");
                    //		ps.println("<H1>");
                    ps.println("<font size=5><b>TABLE OF CONTENTS</b></font>");
                    //		ps.println("</H1>");
                    ps.println("</td>");
                    ps.println("</tr>");

                    ps.println("<tr>");
                    ps.println("<td>");
                    ps.println("<font face=Helvetica size=2 color=#000000><b>");
                    ps.println("SERVICEABLE KITS.");
                    ps.println("</b></font>");
                    ps.println("</td>");
                    ps.println("</tr>");

                    ps.println("</table>");

                    ps.println("<br><center><table cellspacing=0 cellpadding=5 border=1 width=695 bordercolor=#CCCCCC>");
                    ps.println("<tr >");

                    ps.println("<td width=50 align = center>");
                    ps.println("<font face=Helvetica size=2 color=#000000><b>");
                    ps.println("S NO.");
                    ps.println("</b><br></font>");
                    ps.println("</td>");

                    ps.println("<td width=500>");
                    ps.println("<font face=Helvetica size=2 color=#000000><b>");
                    ps.println("DESCRIPTION");
                    ps.println("</b><br></font>");
                    ps.println("</td>");

                    ps.println("<td width=145 align=center>");
                    ps.println("<font face=Helvetica size=2 color=#000000><b>");
                    ps.println("KIT NO.");
                    ps.println("</b><br></font>");
                    ps.println("</td>");
                    ps.println("</tr>");

                    for (int j = i; j < kitPrintIndexCount; j++)
                    {
                        ps.println("<tr>");
                        ps.println("<td valign=top align = center>");
                        ps.println("<font face=Helvetica size=2 color=#000000>");
                        ps.println(j + 1);
                        ps.println("</b></font>");
                        ps.println("</td>");
                        ps.println("<td valign=top >");
                        ps.println("<font face=Helvetica size=2 color=#000000><b>");

                        //rs = stmt.executeQuery("SELECT P1 FROM CAT_GROUP_KIT_DETAIL WHERE GRP_KIT_NO = '" + totalKits.elementAt(j) + "'");
                        String sqlQuery3 = ("SELECT P1 FROM CAT_GROUP_KIT_DETAIL(NOLOCK) WHERE GRP_KIT_NO = '" + totalKits.elementAt(j) + "'");
                        stmt  = conn.prepareStatement(sqlQuery3);
                        rs = stmt.executeQuery();
                        if (rs.next())
                        {
                            ps.println(rs.getString(1));
                        }
                        rs.close();

                        ps.println("</b>");
                        ps.println("</font>");
                        ps.println("</td>");


                        ps.println("<td valign=top align=center>");
                        ps.println("<font face=Helvetica size=2 color=#000000>");
                        ps.println(totalKits.elementAt(j));
                        ps.println("</b></font>");
                        ps.println("</td>");
                        ps.println("</tr>");
                    }
                    ps.println("</table>");

                    ps.println("</h1>");

                    vc = vc + 1024;
                }
                //......................................................PRINTING OF INDEX PAGE ENDS


                //.......................................PRINTING OF IMAGES AS PER GROUP TYPES START

                int pageIndex = 0;
                int sNo = 1;

                for (int m = 0; m < distinctGrpTypes.size(); m++)
                {
                    startCount = 0;
                    indxGrpImageCnt = 0;

                    temp_group_type = "" + distinctGrpTypes.elementAt(m);

                    do
                    {
                        int counter = 0;

                        for (int k = startCount; k < totalGroups.size(); k++)
                        {
                            temp_group = "" + totalGroups.elementAt(k);

                            startCount = k + 1;

                            if ((grpNoWithTypeVec.elementAt(grpNoWithTypeVec.indexOf(temp_group) + 1)).equals(temp_group_type))
                            {
                                // printing of header once for every page
                                if (counter == 0)
                                {
                                    ps.println("<h1 class=\"break\">");

                                    ps.println("<center><table cellspacing=0 cellpadding=0 border=0  width=695>");

                                    ps.println("<tr>");
                                    ps.println("<td valign=top align = right>");
                                    ps.println("<font face=Helvetica size=1 color=#999999>");
                                    ps.println("Print Date: " + todaysDate);
                                    ps.println("</font>");
                                    ps.println("</td>");
                                    ps.println("</tr>");

                                    ps.println("<tr>");
                                    ps.println("<td align=left>");
                                    ps.println("<font face=Helvetica size=1 color=#000000><b>");
                                    ps.println("Version : 3.4." + lastCD_no + "." + lastPATCH_no + " Applicable Date : " + appDate + " Model No. : " + modelNo);
                                    ps.println("</font>");
                                    ps.println("</td>");
                                    ps.println("</tr>");

                                    ps.println("<tr><td>&nbsp;</td></tr>");

                                    ps.println("<tr>");
                                    ps.println("<td align=center valign=middle>");
                                    ps.println("<font size=4 face=arial><b>" + temp_group_type.toUpperCase() + "</b></font>");
                                    ps.println("</td>");
                                    ps.println("</tr>");

                                    ps.println("<tr><td>&nbsp;</td></tr>");

                                    ps.println("</table>");

                                    ps.println("<center><table align=center valign=middle cellspacing=0 cellpadding=0 border=1 height=880 width=695 bordercolor=#000000>");

                                    ps.println("<tr>");

                                    ps.println("<td align=center valign=top>");

                                    ps.println("<table width=685 border=0 cellspacing=1 cellpadding=2 align=center valign=top>");
                                    ps.println("<tr><td colspan=9>&nbsp;</td></tr>");
                                }
                                rs = stmt.executeQuery("SELECT MAP_NAME FROM CAT_MODEL_GROUP WHERE GROUP_NO = '" + temp_group + "' AND  MODEL_NO = '" + modelNo + "'");
                                if (rs.next())
                                {
                                    groupMap = rs.getString(1);
                                }
                                rs.close();

                                if ((counter % 4) == 0)
                                {
                                    ps.println("<tr>");
                                }

                                ps.println("<td width=3%>&nbsp;");
                                ps.println("</td>");

                                ps.println("<td align=left valign=top>");
                                ps.println("<table border=0 valign=top width=107 align=left>");
                                ps.println("<tr><td align=center valign=top>");
                                ps.println("<font face=Arial size=2><b>");
                                ps.println(sNo + ".");
                                ps.println("</b></font>");
                                ps.println("</td><td>");
                                ps.println("<table valign=top width=105 align=left border=1 bordercolor = #000000 cellspacing=0 cellpadding=0>");
                                ps.println("<tr >");
                                ps.println("<td align=center>");
                                ps.println("<font face=Arial>" + groupMap + "</font>");
                                ps.println("</td>");
                                ps.println("</tr>");
                                ps.println("<tr >");
                                ps.println("<td align=center>");

                                int jpgExists = 0;

                                String jpg_path_1 = ecatPATH + "/dealer/ecat_print/group_jpg/";
                                String jpg_path_2 = ".jpg";
                                String jpg_full_path = jpg_path_1 + temp_group + jpg_path_2;
                                File jpg_File = new File(jpg_full_path);
                                if (jpg_File.isFile())
                                {
                                    jpgExists = 1;
                                }

                                if (jpgExists == 1)
                                {
                                    ps.println("<IMG SRC=" + groupJPG + temp_group + ".jpg border = 0 width=105 height=105 align=center>");
                                }
                                else
                                {
                                    ps.println("<IMG SRC=" + groupJPG + "group_image.jpg border = 0 width=105 height=105 align=center>");
                                }

                                ps.println("</td>");
                                ps.println("</tr>");
                                ps.println("<tr >");
                                ps.println("<td align=center>");

                                pageIndex = (dummytotalGroups.indexOf(temp_group)) + 1;
                                ps.println("<font face=Arial>Page No. " + pageIndex + "</font>");
                                ps.println("</td>");
                                ps.println("</tr>");

                                ps.println("</table>");
                                ps.println("</td></tr></table>");
                                ps.println("</td>");

                                sNo++;
                                counter++;

                                if ((counter % 4) == 0)
                                {
                                    ps.println("<td>&nbsp;");
                                    ps.println("</td>");
                                    ps.println("</tr>");
                                    ps.println("<tr><td colspan=9>&nbsp;</td></tr>");
                                }


                                indxGrpImageCnt++;

                                if ((indxGrpImageCnt % 20) == 0)
                                {
                                    k = totalGroups.size();
                                }
                            }

                        }

                        if (counter != 0)
                        {
                            if ((counter % 4) != 0)
                            {
                                int remaining = 4 - (counter % 4);
                                for (int cc = 0; cc < remaining; cc++)
                                {

                                    ps.println("<td width=3%>&nbsp;");
                                    ps.println("</td>");

                                    ps.println("<td align=left valign=top>");
                                    ps.println("<table border=0 valign=top width=107 align=left>");
                                    ps.println("<tr><td align=center valign=top>");
                                    ps.println("<font face=Arial size=2><b>");
                                    ps.println("&nbsp;");
                                    ps.println("</b></font>");
                                    ps.println("</td><td>");
                                    ps.println("<table valign=top width=105 align=left border=0 bordercolor = #000000 cellspacing=0 cellpadding=0>");
                                    ps.println("<tr >");
                                    ps.println("<td align=center>");
                                    ps.println("&nbsp;");
                                    ps.println("</td>");
                                    ps.println("</tr>");
                                    ps.println("<tr >");
                                    ps.println("<td align=center>&nbsp;");
                                    ps.println("</td>");
                                    ps.println("</tr>");
                                    ps.println("<tr >");
                                    ps.println("<td align=center>");
                                    ps.println("&nbsp;");
                                    ps.println("</td>");
                                    ps.println("</tr>");

                                    ps.println("</table>");
                                    ps.println("</td></tr></table>");
                                    ps.println("</td>");

                                }
                                ps.println("<td>&nbsp;");
                                ps.println("</td>");
                                ps.println("</tr>");
                                ps.println("<tr><td colspan=9>&nbsp;</td></tr>");
                            }

                            ps.println("</table>");
                            ps.println("</td>");

                            ps.println("</tr>");
                            ps.println("</table>");

                            ps.println("</h1>");

                            vc = vc + 1025;
                        }

                    }
                    while (startCount < totalGroups.size());

                }

                //.......................................PRINTING OF IMAGES AS PER GROUP TYPES ENDS

                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                for (int i = 0; i < totalKits.size(); i++)
                {
                    dummytotalGroups.addElement(totalKits.elementAt(i));
                }

                int serialNUMBER = 1;

                //Collections.sort(totalGroups);
                String var_REF_NO = "";
                String var_DESCRIPTION = "";
                String var_SERVICEABLE = "";
                String var_REMARK = "";

                for (int v = 0; v < dummytotalGroups.size(); v++)
                {
                    String group = "" + dummytotalGroups.elementAt(v);
                    GroupBom ob = new GroupBom(conn, ps, date_OR_serial, inputDate, serialNo, group, buckleDate);
                    GroupBom ob2 = ob.getGroupBom();

                    String[][] grp_arr = new String[ob2.print_data_counter][ob.no_array_parameters];
                    int grp_arr_count = ob2.print_data_counter;

                    var_REF_NO = "";
                    var_DESCRIPTION = "";
                    var_SERVICEABLE = "";
                    var_REMARK = "";

                    String var_HEADER_DESCRIPTION = "";

                    for (int i = 0; i < grp_arr_count; i++)
                    {
                        var_REF_NO = "";
                        var_DESCRIPTION = "";
                        var_SERVICEABLE = "";
                        var_REMARK = "";
                        var_HEADER_DESCRIPTION = "";

                        for (int aa = 0; aa < ob.no_array_parameters; aa++)
                        {
                            grp_arr[i][aa] = ob2.print_data[i][aa];
                        }

                        if (grp_arr[i][4].equals("ASM"))
                        {
                            //                var_BOLD = "YES";

                           // rs1 = stmt1.executeQuery("SELECT P1,P2,p5,P3 FROM ASSY_DETAIL WHERE ASSY_NO ='" + grp_arr[i][0].replaceAll("&nbsp;", "") + "'");
                            String sql = ("SELECT P1,P2,p5,P3 FROM ASSY_DETAIL(NOLOCK) WHERE ASSY_NO ='" + grp_arr[i][0].replaceAll("&nbsp;", "") + "'");
                            stmt1 = conn.prepareStatement(sql);
                            rs1 = stmt1.executeQuery();
                        	if (rs1.next())
                            {
                                var_DESCRIPTION = rs1.getString(1);
                                var_REF_NO = rs1.getString(2);
                                var_REMARK = rs1.getString(3);
                                var_SERVICEABLE = rs1.getString(4);
                                var_HEADER_DESCRIPTION = var_DESCRIPTION;
                            }
                            rs1.close();
                        }
                        else
                        {
                            //rs1 = stmt1.executeQuery("SELECT p1,p2,P3,p4,p5 FROM CAT_PART WHERE part_no ='" + grp_arr[i][0].replaceAll("&nbsp;", "") + "'");
                           String sql1 = ("SELECT p1,p2,P3,p4,p5 FROM CAT_PART(NOLOCK) WHERE part_no ='" + grp_arr[i][0].replaceAll("&nbsp;", "") + "'");
                            stmt1 = conn.prepareStatement(sql1);
                            rs1 = stmt1.executeQuery();
                           if (rs1.next())
                            {
                                var_DESCRIPTION = rs1.getString(1);
                                var_REF_NO = rs1.getString(2);
                                var_REMARK = rs1.getString(3);
                                var_SERVICEABLE = rs1.getString(4);
                                var_HEADER_DESCRIPTION = rs1.getString(5);
                            }
                            rs1.close();
                        }

                        grp_arr[i][9] = var_REF_NO;
                        grp_arr[i][10] = var_DESCRIPTION;
                        grp_arr[i][11] = var_SERVICEABLE;
                        grp_arr[i][12] = var_REMARK;
                        grp_arr[i][13] = var_HEADER_DESCRIPTION;
                    }

                    for (int i = 0; i < grp_arr_count; i++)
                    {
                        for (int aa = 0; aa < ob.no_array_parameters; aa++)
                        {
                            if (grp_arr[i][aa] == null)
                            {
                                grp_arr[i][aa] = " &nbsp;";
                            }
                        }
                    }

                    groupMap = "";
                   // rs = stmt.executeQuery("SELECT MAP_NAME FROM CAT_MODEL_GROUP WHERE GROUP_NO = '" + group + "' AND  MODEL_NO = '" + modelNo + "'");
                    String Query1 = ("SELECT MAP_NAME FROM CAT_MODEL_GROUP(NOLOCK) WHERE GROUP_NO = '" + group + "' AND  MODEL_NO = '" + modelNo + "'");
                    stmt = conn.prepareStatement(Query1);
                    rs = stmt.executeQuery();
                    if (rs.next())
                    {
                        groupMap = rs.getString(1);
                    }
                    rs.close();

                    String groupDescription = "";
                    String groupRemark = "";

                   // rs = stmt.executeQuery("SELECT p1,REMARKS FROM CAT_GROUP_KIT_DETAIL WHERE GRP_KIT_NO ='" + group + "'");
                    String executeQuery = ("SELECT p1,REMARKS FROM CAT_GROUP_KIT_DETAIL(NOLOCK) WHERE GRP_KIT_NO ='" + group + "'");
                    stmt = conn.prepareStatement(executeQuery);
                    rs = stmt.executeQuery();
                    if (rs.next())
                    {
                        groupDescription = rs.getString(1);
                        groupRemark = rs.getString(2);
                    }
                    rs.close();

                    /////////////////////////////////////////////////////////////////////////////////	
                    String part_array[] = new String[grp_arr.length];
                    int part_counter = 0;
                    for (int i = 0; i < grp_arr.length; i++)
                    {
                        if (grp_arr[i][1].equals("AP") || grp_arr[i][1].equals("AA"))
                        {
                        }
                        else
                        {
                            String linkPart1 = grp_arr[i][0].replaceAll("&nbsp;", "");
                            part_array[part_counter] = linkPart1;
                            part_counter++;
                        }
                    }

                    ///////////////////////////////////////////////// IMAGE LOCATION AS PER REVISION NO //////////////////////////////////////////

                    // String groupRevNo = "";
                    //Get_groupInfo ob_gi = new Get_groupInfo(conn, ps, date_OR_serial, inputDate, serialNo, group);
                    // groupRevNo = ob_gi.get_groupImagePath();

                    String imagePath = "";
                    String Query = "";
                    // if (groupRevNo.equals("new"))
                    // {
                    imagePath = group_imagesURL;
                    Query = "SELECT distinct REF_NO FROM REF_COORD where GROUP_NO='" + group + "' ORDER BY REF_NO";
                    /*  }
                    else
                    {
                    int revisionNo = 0;
                    try
                    {
                    revisionNo = Integer.parseInt(groupRevNo);
                    Query = "SELECT distinct REF_NO FROM REF_COORD_OLD where GROUP_NO='" + group + "' AND REV_NO = " + revisionNo + " ORDER BY REF_NO";
                    }
                    catch (Exception cc)
                    {
                    revisionNo = 0;
                    }
                    imagePath = backupimageURL + revisionNo + "/group_image/";
                    
                    
                    }
                    
                     */
                    ///////////////////////////////////////////////// IMAGE LOCATION AS PER REVISION NO //////////////////////////////////////////

                    //..............................PRINTING PER PAGE OF GROUP BOM STARTS

                    int partsPerPage = 40;
                    //    int print_count = 0;
                    int dm = 1;
                    for (int i = 0; i < grp_arr_count; i += partsPerPage)
                    {
                        /*              if (grp_arr_count < i + partsPerPage)
                        {
                        print_count = grp_arr_count;
                        }
                        else
                        {
                        print_count = i + partsPerPage;
                        }
                         */
                        ps.println("<h1 class=\"break\">");

                        ps.println("<center><table cellspacing=0 cellpadding=5 border=0 height=800 width=695>");
                        ps.println("<tr>");
                        ps.println("<td valign=top colspan=7 align = right height=20>");
                        ps.println("<font face=Helvetica size=1 color=#999999>");
                        ps.println("Print Date: " + todaysDate);
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("</tr>");

                        ps.println("<tr>");
                        ps.println("<td align=left colspan=2 height=20>");
                        ps.println("<font face=Helvetica size=1 color=#000000><b>");
                        ps.println("Version : 3.4." + lastCD_no + "." + lastPATCH_no + " Applicable Date : " + appDate + " Model No. : " + modelNo);
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("<td align=right colspan=5 height=20>");
                        ps.println("<H1>");
                        ps.println(serialNUMBER);
                        ps.println("</H1>");
                        ps.println("</td>");
                        ps.println("</tr>");

                        ps.println("<tr>");
                        ps.println("<td valign=top colspan=7 height=20>");
                        ps.println("<font face=Helvetica size=3 color=#000000><b>");
                        ps.println("Group Number: ");
                        ps.println(groupMap);
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("</tr>");

                        ps.println("<tr>");
                        ps.println("<td align=left valign=top colspan=7 height=20>");
                        ps.println("<font face=Helvetica size=3 color=#000000><b>");
                        ps.println("Description: ");
                        ps.println(groupDescription);
                        ps.println("<br></b></font>");
                        ps.println("</td>");
                        ps.println("</tr>");

                        ps.println("<tr>");
                        ps.println("<td align=center colspan=7 height=711>");
                        ps.println("<font face=Helvetica size=2 color=#000000><b>");
                        //	ps.println("<p align=center>");

                        ps.println("<IMG SRC=" + imagePath + group + ".plt width = 681 height = 711>");

                        String refQuery = "";
                       // rs2 = stmt2.executeQuery(Query);
                        stmt2 = conn.prepareStatement(query);
                        rs2 = stmt2.executeQuery();
                        
                        while (rs2.next())
                        {
                            String partNo = rs2.getString("REF_NO");

                            // if (groupRevNo.equals("new"))
                            //  {
                            refQuery = "SELECT REF_NO, SIDE, X, Y FROM REF_COORD where GROUP_NO='" + group + "' and REF_NO='" + partNo + "' ORDER BY REF_NO";
                            /*  }
                            else
                            {
                            int revisionNo = 0;
                            try
                            {
                            revisionNo = Integer.parseInt(groupRevNo);
                            }
                            catch (Exception cc)
                            {
                            revisionNo = 0;
                            }
                            
                            refQuery = "SELECT REF_NO, SIDE, X, Y FROM REF_COORD_OLD where GROUP_NO='" + group + "' and REF_NO='" + partNo + "' AND REV_NO = " + revisionNo + " ORDER BY REF_NO";
                            }
                             */
                            rs3 = stmt.executeQuery(refQuery);
                            while (rs3.next())
                            {
                                String part_no = rs3.getString("REF_NO");
                                String side = rs3.getString("SIDE");

                                String x = rs3.getString("X");
                                String y = rs3.getString("Y");

                                int posX = 0;
                                int posY = 0;

                                if (side.equals("LEFT"))
                                {
                                    posX = Integer.parseInt(x) + 11;
                                }
                                else
                                {
                                    posX = Integer.parseInt(x) + 37;
                                }


                                posY = Integer.parseInt(y) + 132;
                                posY = posY + vc;

                                int prt = 0;

                                ps.println("<div style=\"position:absolute; width:60px; height:20px; z-index:1; left: " + posX + "px; top: " + posY + "px;\">");


                                for (int k = 0; k < part_counter; k++)
                                {
                                    if (part_array[k].equals(part_no))
                                    {
                                        prt++;
                                    }
                                }

                                int flag = 1;

                                for (int j = 0; j < part_counter; j++)
                                {
                                    if (part_array[j].equals(part_no))
                                    {
                                        ps.println(j + 1);
                                        if (prt > 1)
                                        {
                                            if (flag < prt)
                                            {
                                                ps.println("/");
                                                flag++;
                                            }
                                        }
                                    }
                                }
                                ps.println("</div>");
                            }
                            rs3.close();
                        }
                        rs2.close();


                        //	ps.println("</p>");
                        ps.println("</b></font>");
                        ps.println("</td>");
                        ps.println("</tr>");
                        ps.println("</table>");

                        if (!(groupRemark == null || groupRemark.equals("")))
                        {
                            ps.println("<center><table cellspacing=5 cellpadding=4 border=0 width=695>");

                            ps.println("<tr>");
                            ps.println("<td>");
                            ps.println("<b>");
                            ps.println("<font face=Helvetica size=2 color=#000000>");
                            ps.println("Remarks");
                            ps.println("</font>");
                            ps.println("</b>");
                            ps.println("</td>");
                            ps.println("</tr>");

                            ps.println("<tr>");
                            ps.println("<td >");
                            ps.println("<font face=Helvetica size=2 color=#000000>");
                            ps.println(groupRemark);
                            ps.println("</font>");
                            ps.println("</td>");
                            ps.println("</tr>");

                            ps.println("</table>");
                        }
                        ps.println("</h1>");
                        vc = vc + 1024 + dm;
                        dm++;
                    }

                    //********** PRINTING OF GROUP SERVICE PARTS STARTS **********//

                    
                    int servicePartprintArrayCount = 0;
                    String serviceBomImage = "NO";

                   /*
                    String[][] servicePartprintArray = new String[150][5];
                    rs = stmt.executeQuery("SELECT OSP.PART_NO, OSP.QTY, P.P3, P.P2, P.P1, OSP.IMAGE FROM OPTION_SERVICE_PART OSP, CAT_PART P WHERE OSP.OPTION_NO='" + group + "' AND OSP.PART_NO = P.part_no");
                    if (rs.next())
                    {
                        do
                        {
                            String Prt_no = rs.getString(1);
                            String Prt_x = rs.getString(2);
                            String Prt_remark = rs.getString(3);
                            String Prt_ref_no = rs.getString(4);
                            String Prt_name = rs.getString(5);
                            serviceBomImage = rs.getString(6);

                            if (Prt_ref_no == null || Prt_ref_no.equals("null"))
                            {
                                Prt_ref_no = "-";
                            }

                            if (Prt_remark == null || Prt_remark.equals("null"))
                            {
                                Prt_remark = "-";
                            }

                            if (Prt_name == null || Prt_name.equals("null"))
                            {
                                Prt_name = "-";
                            }

                            servicePartprintArray[servicePartprintArrayCount][0] = Prt_no;
                            servicePartprintArray[servicePartprintArrayCount][1] = Prt_x;
                            servicePartprintArray[servicePartprintArrayCount][2] = Prt_remark;
                            servicePartprintArray[servicePartprintArrayCount][3] = Prt_ref_no;
                            servicePartprintArray[servicePartprintArrayCount][4] = Prt_name;
                            servicePartprintArrayCount++;
                        }
                        while (rs.next());
                    }
                    rs.close();
*/
                    int servicePartsPerPage = 35;

                    int service_print_count = 0;
                    for (int i = 0; i < servicePartprintArrayCount; i += servicePartsPerPage)
                    {
                        if (servicePartprintArrayCount < i + servicePartsPerPage)
                        {
                            service_print_count = servicePartprintArrayCount;
                        }
                        else
                        {
                            service_print_count = i + servicePartsPerPage;
                        }

                        ps.println("<h1 class=\"break\">");
                        ps.println("<center><table cellspacing=0 cellpadding=5 border=0 width=695 bordercolor=#000000>");

                        ps.println("<tr>");
                        ps.println("<td valign=top colspan=7 align = right>");
                        ps.println("<font face=Helvetica size=1 color=#999999>");
                        ps.println("Print Date: " + todaysDate);
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("</tr>");

                        ps.println("<tr>");
                        ps.println("<td align=left colspan=2>");
                        ps.println("<font face=Helvetica size=1 color=#000000><b>");
                        ps.println("Version : 3.4." + lastCD_no + "." + lastPATCH_no + " Applicable Date : " + appDate + " Model No. : " + modelNo);
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("<td align=right colspan=5>");
                        ps.println("<H1>");
                        ps.println(serialNUMBER);
                        ps.println("</H1>");
                        ps.println("</td>");
                        ps.println("</tr>");

                        ps.println("<tr>");
                        ps.println("<td valign=top colspan=7>");
                        ps.println("<font face=Helvetica size=2 color=#000000><b>");
                        ps.println("Group Number: ");
                        ps.println("<font color=#000000>");
                        ps.println(groupMap);
                        ps.println("</font>");
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("</tr>");

                        ps.println("<tr>");
                        ps.println("<td align=center colspan=7>");
                        ps.println("<br><font face=Helvetica size=2 color=#000000><b>");
                        ps.println("SERVICE BOM IMAGE");
                        ps.println("</b><br></font>");
                        ps.println("</td>");
                        ps.println("</tr>");

                        ps.println("<tr>");
                        ps.println("<td align=center colspan=7>");
                        ps.println("<font face=Helvetica size=2 color=#000000><b>");

                        if (serviceBomImage.equals("YES"))
                        {
                            ps.println("<p align=center><IMG SRC=" + ecat_sp_imagesURL + group + "_op.jpg></p>");
                        }
                        else
                        {
                            ps.println("<IMG SRC=" + imagePath + group + ".plt width = 681 height = 711>");
                        }
                        ps.println("</b></font>");
                        ps.println("</td>");
                        ps.println("</tr>");
                        ps.println("</table>");
                        ps.println("</h1>");

                    }

                    //********** PRINTING OF GROUP SERVICE PARTS ENDS **********//
                    serialNUMBER++;
                }
                stmt.close();
                //stmt1.close();
            }
            else
            {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
            ps.println("</body></html>");
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
