package viewEcat.comEcat;

/*
File Name: ModelPrintable_bom.java
PURPOSE: It is used for printing the complete model BOM with Page Setting as Letter and all margin 0.163
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

public class ModelPrintable_bom extends HttpServlet
{
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintStream wps = new PrintStream(res.getOutputStream());
        //	Date date =  new Date();
        //	DateFormat df =DateFormat.getDateInstance(DateFormat.SHORT);
        //	String todaysDate=df.format(date);	

        java.util.Date date_today = new java.util.Date();
        DateFormat df2 = new SimpleDateFormat("dd-MMM-yyyy");
        String todaysDate = df2.format(date_today);

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
            String getType = (String) session.getValue("user_type");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            String date_OR_serial = (String) session.getValue("date_OR_serial");
            java.sql.Date inputDate = (java.sql.Date) session.getValue("input_Date");
            java.sql.Date buckleDate = (java.sql.Date) session.getValue("buckleDate");
            String serialNo = (String) session.getValue("input_serialNo");

            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String userCode = (String) session.getValue("userCode");

            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);


            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();
            String imagesURL=object_pageTemplate.imagesURL();

            String group_imagesURL = "";
            group_imagesURL = object_pageTemplate.group_imagesURL();

            String backupimageURL = object_pageTemplate.backupimageURL();

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            if (session_id.equals(getSession))
            {
                Connection conn = holder.getConnection();
                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));
               
                /*########################################*/

               // Statement stmt;
                //Statement stmt1;
                PreparedStatement stmt = null;
                PreparedStatement stmt1 = null;
                ResultSet rs;
                ResultSet rs1;

                //stmt = conn.createStatement();
                //stmt1 = conn.createStatement();

                String modelNo = req.getParameter("model");

                Vector totalGroups = new Vector();
                Vector totalKits = new Vector();

                // GET THE LAST CD INSTALLED

                String lastCD_no = "";

                //rs = stmt.executeQuery("SELECT MAX(sno) FROM CAT_CD");
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

                java.sql.Date lastPATCH_applicable_on = new java.sql.Date(0);
                int lastPATCH_no = 0;

                //rs = stmt.executeQuery("SELECT PATCH_NO,APP_FROM FROM CAT_PATCH WHERE CD_NO=" + lastCD_no + " ORDER BY PATCH_NO DESC");
                String query1 = ("SELECT PATCH_NO,APP_FROM FROM CAT_PATCH(NOLOCK) WHERE CD_NO=" + lastCD_no + " ORDER BY PATCH_NO DESC");
                stmt = conn.prepareStatement(query1);
                rs = stmt.executeQuery();
                if (rs.next())
                {
                    lastPATCH_no = Integer.parseInt(rs.getString(1));
                    lastPATCH_applicable_on = rs.getDate(2);
                }
                rs.close();

                //*********************************************************************************

                String appType = "";
                String engModel = "";
                String bulletinNo = "";

                //rs = stmt.executeQuery("SELECT * FROM CAT_MODEL_CLASSIFICATION where MODEL_NO = '" + modelNo + "'");
                String query2 = ("SELECT * FROM CAT_MODEL_CLASSIFICATION(NOLOCK) where MODEL_NO = '" + modelNo + "'");
                stmt = conn.prepareStatement(query2);
                rs = stmt.executeQuery();
                if (rs.next())
                {
                    engModel = rs.getString(3);
                    appType = rs.getString(4);
                    bulletinNo = rs.getString(5);
                }
                rs.close();

                java.sql.Date compDate = new java.sql.Date(0);
                rs = stmt.executeQuery("SELECT COMPLETION_DATE FROM CAT_MODELS where MODEL_NO = '" + modelNo + "'");
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

                SimpleDateFormat sdfInput = new SimpleDateFormat("yyyy-MM-dd");
                Date date = sdfInput.parse(compDate.toString());

                SimpleDateFormat sdf = new SimpleDateFormat("MMM-yyyy");
                String appDate = sdf.format(date);

                String tableName_MG = "CAT_MODEL_GROUP";
                /*    String tableName_MG_MAP = "MODEL_GROUP_MAP";
                
                int revNo = 0;
                String new_old = "";
                
                Get_modelInfo ob_get_rev_no = new Get_modelInfo(conn, ps, date_OR_serial, inputDate, serialNo, modelNo);
                revNo = ob_get_rev_no.get_modelRevisionNo();
                new_old = ob_get_rev_no.get_modelTable(revNo);
                
                if (new_old.equals("old"))
                {
                tableName_MG = "MODEL_GROUP_OLD";
                tableName_MG_MAP = "MODEL_GROUP_MAP_OLD";
                }
                else
                {
                tableName_MG = "CAT_MODEL_GROUP";
                tableName_MG_MAP = "MODEL_GROUP_MAP";
                }
                
                rs = stmt.executeQuery("SELECT MG.GROUP_NO,GKD.GK_TYPE FROM " + tableName_MG + " MG, CAT_GROUP_KIT_DETAIL GKD WHERE MG.MODEL_NO = '" + modelNo + "' AND MG.GROUP_NO  = GKD.GRP_KIT_NO AND MG.REV_NO = " + revNo + " ORDER BY GKD.P1");
                 */
               // rs = stmt.executeQuery("SELECT MG.GROUP_NO,GKD.GK_TYPE FROM " + tableName_MG + " MG, CAT_GROUP_KIT_DETAIL GKD WHERE MG.MODEL_NO = '" + modelNo + "' AND MG.GROUP_NO  = GKD.GRP_KIT_NO ORDER BY GKD.P1");
               String sqlquery = ("SELECT MG.GROUP_NO,GKD.GK_TYPE FROM " + tableName_MG + " MG, CAT_GROUP_KIT_DETAIL(NOLOCK) GKD WHERE MG.MODEL_NO = '" + modelNo + "' AND MG.GROUP_NO  = GKD.GRP_KIT_NO ORDER BY GKD.P1");
               stmt = conn.prepareStatement(sqlquery);
                rs = stmt.executeQuery();
               if (rs.next())
                {
                    do
                    {
                        String temp_1 = rs.getString(1);
                        String temp_2 = rs.getString(2);

                        if (temp_2.equals("G"))
                        {
                            totalGroups.addElement(temp_1);
                        }
                        else
                        {
                            totalKits.addElement(temp_1);
                        }
                    }
                    while (rs.next());
                }
                rs.close();

                String modelDesc = modelNo;

                //rs = stmt.executeQuery("SELECT DESCRIPTION FROM CAT_MODELS WHERE MODEL_NO = '" + modelNo + "'");
                String sqlQuery = ("SELECT DESCRIPTION FROM CAT_MODELS(NOLOCK) WHERE MODEL_NO = '" + modelNo + "'");
                stmt = conn.prepareStatement(sqlQuery);
                rs = stmt.executeQuery();
                if (rs.next())
                {
                    modelDesc = rs.getString(1);
                }
                rs.close();

                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                for (int i = 0; i < totalKits.size(); i++)
                {
                    totalGroups.addElement(totalKits.elementAt(i));
                }

                int serialNUMBER = 1;

                Collections.sort(totalGroups);
                for (int v = 0; v < totalGroups.size(); v++)
                {
                    if (v < totalGroups.size() - 1)
                    {
                        String temp_group = "" + totalGroups.elementAt(v);
                        String temp_group1 = "" + totalGroups.elementAt(v + 1);
                        int indexofA = temp_group.indexOf("A");
                        int indexofUnderscore = temp_group.indexOf("_");

                        if (indexofA != -1)
                        {
                            if (indexofUnderscore > indexofA)
                            {
                                totalGroups.set(v, temp_group1);
                                totalGroups.set(v + 1, temp_group);
                                v++;
                            }
                        }
                    }
                }

                for (int v = 0; v < totalGroups.size(); v++)
                {
                    String group = "" + totalGroups.elementAt(v);
                    GroupBom ob = new GroupBom(conn, ps, date_OR_serial, inputDate, serialNo, group, buckleDate);
                    GroupBom ob2 = ob.getGroupBom();

                    String[][] grp_arr = new String[ob2.print_data_counter][ob.no_array_parameters];
                    int grp_arr_count = ob2.print_data_counter;

                    String var_REF_NO = "";
                    String var_DESCRIPTION = "";
                    String var_SERVICEABLE = "";
                    String var_REMARK = "";

                    String var_HEADER_DESCRIPTION = "";
                 //   String var_BOLD = "";

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
                        //    var_BOLD = "YES";

                           // rs1 = stmt1.executeQuery("SELECT P1,P2,p4,P3 FROM ASSY_DETAIL WHERE ASSY_NO ='" + grp_arr[i][0].replaceAll("&nbsp;", "") + "'");
                            String sqlQuery1 = ("SELECT P1,P2,p4,P3 FROM ASSY_DETAIL(NOLOCK) WHERE ASSY_NO ='" + grp_arr[i][0].replaceAll("&nbsp;", "") + "'");
                            stmt1 = conn.prepareStatement(sqlQuery1);
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
                            String sqlQuery2 = ("SELECT p1,p2,P3,p4,p5 FROM CAT_PART(NOLOCK) WHERE part_no ='" + grp_arr[i][0].replaceAll("&nbsp;", "") + "'");
                            stmt1 = conn.prepareStatement(sqlQuery2);
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

                        if (var_DESCRIPTION == null)
                        {
                            var_DESCRIPTION = "";
                        }

                        grp_arr[i][9] = var_REF_NO;
                        grp_arr[i][10] = var_DESCRIPTION.toUpperCase();
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

                    int tableWidth = 695;

                    String groupMap = "";
                    rs = stmt.executeQuery("SELECT MAP_NAME FROM CAT_MODEL_GROUP WHERE GROUP_NO = '" + group + "' AND MODEL_NO = '" + modelNo + "'");
                    String sqlQuery1 = ("SELECT MAP_NAME FROM CAT_MODEL_GROUP(NOLOCK) WHERE GROUP_NO = '" + group + "' AND MODEL_NO = '" + modelNo + "'");
                    stmt = conn.prepareStatement(sqlQuery1);
                    rs = stmt.executeQuery();
                    if (rs.next())
                    {
                        groupMap = rs.getString(1);
                    }
                    rs.close();
                    //stmt.close();

                    String groupDescription = "";
                    String groupRemark = "";

                    //rs = stmt.executeQuery("SELECT p1,REMARKS FROM CAT_GROUP_KIT_DETAIL WHERE GRP_KIT_NO ='" + group + "'");
                    String sqlQuery2 = ("SELECT p1,REMARKS FROM CAT_GROUP_KIT_DETAIL(NOLOCK) WHERE GRP_KIT_NO ='" + group + "'");
                    stmt = conn.prepareStatement(sqlQuery2);
                    rs = stmt.executeQuery();
                    if (rs.next())
                    {
                        groupDescription = rs.getString(1);
                        groupRemark = rs.getString(2);
                    }
                    rs.close();

                    ///////////////////////////////////////////////// IMAGE LOCATION AS PER REVISION NO //////////////////////////////////////////

                    String groupRevNo = "";
                    //Get_groupInfo ob_gi = new Get_groupInfo(conn, ps, date_OR_serial, inputDate, serialNo, group);
                    //groupRevNo = ob_gi.get_groupImagePath();

                    String imagePath = "";

                    if (groupRevNo.equals("new"))
                    {
                        imagePath = group_imagesURL;
                    }
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
                        imagePath = backupimageURL + revisionNo + "/group_image/";

                    }

                    ///////////////////////////////////////////////// IMAGE LOCATION AS PER REVISION NO //////////////////////////////////////////

                    ps.println("<html>");
                    ps.println("<head>");
                    ps.println("<title>BOM of MODEL NUMBER. " + modelNo + "</title>");
                    ps.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">");
                    ps.println("<STYLE>");
                    ps.println(".break { page-break-before: always; }");
                    ps.println("</STYLE>");
                    ps.println("</head>");
                    ps.println("<body>");

                    //..............................PRINTING PER PAGE OF GROUP BOM STARTS

                    int partsPerPage = 40;
                    int print_count = 0;
                    int Checker = 1;

                    for (int i = 0; i < grp_arr_count; i += partsPerPage)
                    {
                        if (grp_arr_count < i + partsPerPage)
                        {
                            print_count = grp_arr_count;
                        }
                        else
                        {
                            print_count = i + partsPerPage;
                        }

                        ps.println("<h1 class=\"break\">");

                        ps.println("<center><table cellspacing=0 cellpadding=5 border=0 width=695 >");

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
                        ps.println("<font face=Helvetica size=3 color=#000000><b>");
                        //	ps.println("Details of ");
                        ps.println("Group Number: ");
                        ps.println(groupMap);
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("</tr>");
                        ps.println("<tr>");
                        ps.println("<td align=left colspan=7>");
                        //	ps.println("<br>");
                        ps.println("<font face=Helvetica size=3 color=#000000><b>");
                        ps.println("Description: ");
                        ps.println(groupDescription);
                        ps.println("</b></font>");
                        ps.println("<br>");
                        ps.println("<br>");
                        ps.println("</td>");
                        ps.println("</tr>");
                        ps.println("</table>");

                        ps.println("<table cellspacing=0 cellpadding=2 border=1 width=" + tableWidth + " bordercolor=#CCCCCC>");
                        ps.println("<tr>");
                        ps.println("<td align = center width=7%>");
                        ps.println("<font face=Helvetica size=1 color=#000000><b>");
                        ps.println("S No");
                        ps.println("</td>");

                        /*						ps.println("<td align = center width=10%>");
                        ps.println("<font face=Helvetica size=1 color=#000000><b>");	
                        ps.println("Ref No");
                        ps.println("</td>");	
                         */
                        ps.println("<td width=17%>");
                        ps.println("<font face=Helvetica size=1 color=#000000><b>");
                        ps.println("Part No");
                        ps.println("</td>");

                        /*
                        ps.println("<td align = center width=5%>");
                        ps.println("<font face=Helvetica size=1 color=#000000><b>");	
                        ps.println("&nbsp;");
                        ps.println("</td>");
                         */

                        ps.println("<td width=30%>");
                        ps.println("<font face=Helvetica size=1 color=#000000><b>");
                        ps.println("Description");
                        ps.println("</td>");

                        ps.println("<td align = center width=5%>");
                        ps.println("<font face=Helvetica size=1 color=#000000><b>");
                        ps.println("Qty");
                        ps.println("</td>");

                        /*
                        ps.println("<td align = center width=5%>");
                        ps.println("<font face=Helvetica size=1 color=#000000><b>");	
                        ps.println("Ord.");
                        ps.println("</td>");	
                         */

                        ps.println("<td align = left width=21%>");
                        ps.println("<font face=Helvetica size=1 color=#000000><b>");
                        ps.println("Alternate");
                        ps.println("</td>");

                        ps.println("<td align = left width=20%>");
                        ps.println("<font face=Helvetica size=1 color=#000000><b>");
                        ps.println("Remarks");
                        ps.println("</td>");
                        ps.println("</tr>");

                        Get_groupInfo ob_get_grp_inf = new Get_groupInfo();

                        //Get_groupInfo ob_get_grp_inf = new Get_groupInfo(conn, ps, date_OR_serial, inputDate, serialNo, group);
                        String altReplBy = "";
                        Vector superVec = new Vector();
                        String linkPart = "";
                        for (int j = i; j < print_count; j++)
                        {
                            linkPart = grp_arr[j][0].replaceAll("&nbsp;", "");

                            if (grp_arr[j][8].equals("YES"))
                            {
                                ps.println("<tr>");
                                ps.println("<td align = center>");
                                ps.println("<font face=Helvetica size=1 color=#000000>");
                                ps.println("&nbsp;");
                                ps.println("</td>");

                                ps.println("<td align = center>");
                                ps.println("<font face=Helvetica size=1 color=#000000>");
                                ps.println("&nbsp;");
                                ps.println("</td>");

                                ps.println("<td>");
                                ps.println("<font face=Helvetica size=1 color=#000000>");
                                ps.println("&nbsp;");
                                ps.println("</td>");

                                /*
                                ps.println("<td align = center>");
                                ps.println("<font face=Helvetica size=1 color=#000000>>");	
                                ps.println("&nbsp;");
                                ps.println("</td>");	
                                 */

                                ps.println("<td><b>");
                                ps.println("<font face=verdana size=1 color=#000000><b>");
                                ps.println(grp_arr[j][13]);
                                ps.println("</td>");

                                ps.println("<td align=center>");
                                ps.println("<font face=Helvetica size=1 color=#000000>");
                                ps.println("&nbsp;");
                                ps.println("</td>");

                                /*
                                ps.println("<td align=center>");
                                ps.println("<font face=Helvetica size=1 color=#000000>");	
                                ps.println("&nbsp;");
                                ps.println("</td>");	
                                 */

                                ps.println("<td>");
                                ps.println("<font face=Helvetica size=1 color=#000000><b>");
                                ps.println("&nbsp;");
                                ps.println("</td>");
                                ps.println("</tr>");
                            }

                            ps.println("<tr>");

                            ps.println("<td align = center width=7% >");
                            ps.println("<font face=Helvetica size=1 color=#000000>");
                            if (grp_arr[j][1].equals("AP") || grp_arr[j][1].equals("AA"))
                            {
                                ps.println(grp_arr[j][1]);
                            }
                            else
                            {
                                ps.println(Checker);
                                Checker++;
                            }
                            ps.println("</td>");

                            /*ps.println("<td align = center width=10% >");
                            ps.println("<font face=Helvetica size=1 color=#000000>");	
                            ps.println(grp_arr[j][9]);
                            ps.println("</td>");
                             */
                            ps.println("<td width=17%>");
                            ps.println("<font face=Helvetica size=1 color=#000000>");
                            if (grp_arr[j][14].equals("YES"))
                            {
                                ps.println("<b>");
                            }
                            ps.println(grp_arr[j][0].replaceAll("_", "&nbsp;"));
                            ps.println("</td>");

                            /*
                            ps.println("<td align = center width=5%>");
                            if (grp_arr[j][1].equals("NO"))
                            {
                            ps.println("&nbsp;");
                            }
                            else
                            {
                            ps.println(grp_arr[j][1]);
                            }
                            ps.println("</td>");
                             */

                            ps.println("<td width=30%>");
                            ps.println("<font face=Helvetica size=1 color=#000000>");
                            ps.println(grp_arr[j][10]);
                            ps.println("</td>");

                            ps.println("<td align=center width=5%>");
                            ps.println("<font face=Helvetica size=1 color=#000000>");
                            if (grp_arr[j][7].equals("YES"))
                            {
                                ps.println("AR");
                            }
                            else
                            {
                                ps.println(grp_arr[j][3]);
                            }
                            ps.println("</td>");

                            /*
                            ps.println("<td align=center width=5%>");
                            ps.println(grp_arr[j][11]);
                            ps.println("</td>");
                             */
                            altReplBy = ob_get_grp_inf.getAltOrReplBy(conn, "" + grp_arr[j][0].replaceAll("_", "&nbsp;"));
                            ps.println("<td  width=20%>");
                            ps.println("<font face=Helvetica size=1 color=#000000>");
                            ps.println(altReplBy + "&nbsp;");
                            ps.println("</td>");

                            ps.println("<td  width=20%>");
                            ps.println("<font face=Helvetica size=1 color=#000000>");
                            ps.println(grp_arr[j][12] + "&nbsp;");
                            ps.println("</td>");

                            ps.println("</tr>");

                            // Print Part Deletion history in the asm
                            if (grp_arr[j][4].equals("ASM") && date_OR_serial.equals("latest"))
                            {
                                superVec = new Vector();
                                superVec = ob_get_grp_inf.getPartDeleteHistory(true, holder.getConnection(), "", grp_arr[j][0].replaceAll("_", "&nbsp;"), superVec, "", new java.sql.Date(0));

                                if (superVec != null)
                                {
                                    String spaceStr = grp_arr[j][0].substring(0, grp_arr[j][0].lastIndexOf(";") + 1) + "&nbsp;&nbsp;&nbsp;";
                                    for (int m = 0; m < superVec.size(); m = m + 6)
                                    {
                                        //  partAssy = "" + superVec.elementAt(m);
                                        linkPart = "" + superVec.elementAt(m + 1);

                                        ps.println("<tr>");

                                        ps.println("<td align = center >");
                                        ps.println("&nbsp;");
                                        ps.println("</td>");

                                        ps.println("<td >");
                                        ps.println("<font face=Helvetica size=1 color=#000000>");
                                        ps.println("" + spaceStr + linkPart);
                                        ps.println("</font >");
                                        ps.println("</td>");

                                        ps.println("<td  >");
                                        ps.println("<font face=Helvetica size=1 color=#000000>");
                                        ps.println(("" + superVec.elementAt(m + 2)).toUpperCase());
                                        ps.println("</font >");
                                        ps.println("</td>");

                                        ps.println("<td>");

                                        ps.println("&nbsp;");
                                        ps.println("</td>");

                                        ps.println("<td >");
                                        ps.println("&nbsp;");
                                        ps.println("</td>");

                                        ps.println("<td >");
                                        //  ps.println("Rev. No.:" +superVec.elementAt(m + 3) + "&nbsp;{ TSN: "+superVec.elementAt(m + 4) +"&nbsp;}");

                                        /* ps.println("<font face=Helvetica size=1 color=#000000>");
                                        ps.println("Rev. No.:" + superVec.elementAt(m + 3));
                                        ps.println("</font>");
                                        ps.println(", ");
                                         */
                                        ps.println("<font face=Helvetica size=1 color=#000000>");
                                        // ps.println("Till TSN:" + superVec.elementAt(m + 4));
                                        // ps.println("Till Date: " + superVec.elementAt(m + 3));
                                        ps.println("Till TSN: X" + superVec.elementAt(m + 5));

                                        ps.println("</font>");
                                        ps.println("</td>");

                                        ps.println("</tr>");
                                    }
                                }
                            }

                            //revNo = ob_get_grp_inf.get_groupRevisionNo();
                            if (grp_arr[j][2].equals("YES"))
                            {
                                if (date_OR_serial.equals("latest"))
                                {
                                    superVec = new Vector();

                                    superVec = ob_get_grp_inf.getPartHistory(true, holder.getConnection(), grp_arr[j][0].replaceAll("_", "&nbsp;"), grp_arr[j][6], superVec, "", new java.sql.Date(0));
                                    // System.out.println("superVec" + superVec);
                                    // String partAssy = "";
                                    if (superVec != null)
                                    {
                                        String spaceStr = grp_arr[j][0].substring(0, grp_arr[j][0].lastIndexOf(";") + 1);

                                        for (int m = 0; m < superVec.size(); m = m + 6)
                                        {
                                            //  partAssy = "" + superVec.elementAt(m);
                                            linkPart = "" + superVec.elementAt(m + 1);

                                            ps.println("<tr>");

                                            ps.println("<td align = center >");
                                            ps.println("&nbsp;");
                                            ps.println("</td>");

                                            ps.println("<td >");
                                            ps.println("<font face=Helvetica size=1 color=#000000>");
                                            ps.println("" + spaceStr + linkPart);
                                            ps.println("</font >");
                                            ps.println("</td>");

                                            ps.println("<td  >");
                                            ps.println("<font face=Helvetica size=1 color=#000000>");
                                            ps.println(("" + superVec.elementAt(m + 2)).toUpperCase());
                                            ps.println("</font >");
                                            ps.println("</td>");

                                            ps.println("<td>");

                                            ps.println("&nbsp;");
                                            ps.println("</td>");

                                            ps.println("<td >");
                                            ps.println("&nbsp;");
                                            ps.println("</td>");

                                            ps.println("<td >");
                                            //  ps.println("Rev. No.:" +superVec.elementAt(m + 3) + "&nbsp;{ TSN: "+superVec.elementAt(m + 4) +"&nbsp;}");

                                            /* ps.println("<font face=Helvetica size=1 color=#000000>");
                                            ps.println("Rev. No.:" + superVec.elementAt(m + 3));
                                            ps.println("</font>");
                                            ps.println(", ");
                                             */
                                            ps.println("<font face=Helvetica size=1 color=#000000>");
                                            // ps.println("Till TSN:" + superVec.elementAt(m + 4));
                                            // ps.println("Till Date: " + superVec.elementAt(m + 3));
                                            ps.println("Till TSN: X" + superVec.elementAt(m + 5));

                                            ps.println("</font>");
                                            ps.println("</td>");

                                            ps.println("</tr>");
                                        }
                                    }
                                }
                            }
                        /****************************/
                        }

                        // Print Part Deletion history in the group
                        if (date_OR_serial.equals("latest"))
                        {
                            superVec = new Vector();
                            superVec = ob_get_grp_inf.getPartDeleteHistory(true, holder.getConnection(), "", group, superVec, "", new java.sql.Date(0));
                            //    System.out.println("superVec" + superVec);
                            if (superVec != null)
                            {
                                String spaceStr = "";
                                for (int m = 0; m < superVec.size(); m = m + 6)
                                {
                                    //  partAssy = "" + superVec.elementAt(m);
                                    linkPart = "" + superVec.elementAt(m + 1);

                                    ps.println("<tr>");

                                    ps.println("<td align = center >");
                                    ps.println("&nbsp;");
                                    ps.println("</td>");

                                    ps.println("<td >");
                                    ps.println("<font face=Helvetica size=1 color=#000000>");
                                    ps.println("" + spaceStr + linkPart);
                                    ps.println("</font >");
                                    ps.println("</td>");

                                    ps.println("<td  >");
                                    ps.println("<font face=Helvetica size=1 color=#000000>");
                                    ps.println(("" + superVec.elementAt(m + 2)).toUpperCase());
                                    ps.println("</font >");
                                    ps.println("</td>");

                                    ps.println("<td>");

                                    ps.println("&nbsp;");
                                    ps.println("</td>");

                                    ps.println("<td >");
                                    ps.println("&nbsp;");
                                    ps.println("</td>");

                                    ps.println("<td >");
                                    //  ps.println("Rev. No.:" +superVec.elementAt(m + 3) + "&nbsp;{ TSN: "+superVec.elementAt(m + 4) +"&nbsp;}");

                                    /* ps.println("<font face=Helvetica size=1 color=#000000>");
                                    ps.println("Rev. No.:" + superVec.elementAt(m + 3));
                                    ps.println("</font>");
                                    ps.println(", ");
                                     */
                                    ps.println("<font face=Helvetica size=1 color=#000000>");
                                    // ps.println("Till TSN:" + superVec.elementAt(m + 4));
                                    // ps.println("Till Date: " + superVec.elementAt(m + 3));
                                    ps.println("Till TSN: X" + superVec.elementAt(m + 5));

                                    ps.println("</font>");
                                    ps.println("</td>");

                                    ps.println("</tr>");
                                }
                            }
                        }
                        ps.println("</table>");
                        ps.println("</h1>");
                    }

                    //********** PRINTING OF GROUP SERVICE PARTS STARTS **********//

                    String[][] servicePartprintArray = new String[150][5];
                    int servicePartprintArrayCount = 0;
                    //    String serviceBomImage = "NO";

                 /*   rs = stmt.executeQuery("SELECT OSP.PART_NO, OSP.QTY, P.P3, P.P2, P.P1, OSP.IMAGE FROM OPTION_SERVICE_PART OSP, CAT_PART P WHERE OSP.OPTION_NO='" + group + "' AND OSP.PART_NO = P.part_no");
                    if (rs.next())
                    {
                        do
                        {
                            String Prt_no = rs.getString(1);
                            String Prt_x = rs.getString(2);
                            String Prt_remark = rs.getString(3);
                            String Prt_ref_no = rs.getString(4);
                            String Prt_name = rs.getString(5);
                            //   serviceBomImage = rs.getString(6);

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
                        ps.println("<center><table cellspacing=0 cellpadding=5 border=0 width=695 >");
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
                        ps.println("Service BOM of ");
                        ps.println("Group Number: ");
                        ps.println("<font color=#000000>");
                        ps.println(groupMap);
                        ps.println("</font>");
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("</tr>");
                        ps.println("<tr>");
                        ps.println("<td align=center colspan=7>");
                        ps.println("<br>");
                        ps.println("<font face=Helvetica size=2 color=#000000><b>");
                        ps.println(groupDescription);
                        ps.println("</b></font>");
                        ps.println("<br>");
                        ps.println("<br>");
                        ps.println("</td>");
                        ps.println("</tr>");
                        ps.println("</table>");

                        ps.println("<center><table cellspacing=0 cellpadding=1 border=1 width=695 bordercolor=#BCBCBC>");

                        ps.println("<tr>");
                        ps.println("<td width=8% align = center>");
                        ps.println("<font face=Helvetica size=1 color=#000000><b>");
                        ps.println("Ref No.");
                        ps.println("</td>");

                        ps.println("<td width=20% align = center>");
                        ps.println("<font face=Helvetica size=1 color=#000000><b>");
                        ps.println("Part Number");
                        ps.println("</td>");

                        ps.println("<td width=35% align = center>");
                        ps.println("<font face=Helvetica size=1 color=#000000><b>");
                        ps.println("Part Name");
                        ps.println("</td>");

                        ps.println("<td width=7% align = center>");
                        ps.println("<font face=Helvetica size=1 color=#000000><b>");
                        ps.println("Qty");
                        ps.println("</td>");

                        ps.println("<td width=30% align = center>");
                        ps.println("<font face=Helvetica size=1 color=#000000><b>");
                        ps.println("Part Remark");
                        ps.println("</td>");

                        ps.println("</tr>");

                        for (int j = i; j < service_print_count; j++)
                        {
                            ps.println("<tr>");
                            ps.println("<td width=8% align = center>");
                            ps.println("<font face=Helvetica size=1 color=#000000>");
                            ps.println(servicePartprintArray[j][3].replaceAll("_", "&nbsp;"));
                            ps.println("</td>");

                            ps.println("<td width=20% align = LEFT>");
                            ps.println("<font face=Helvetica size=1 color=#000000>");
                            ps.println(servicePartprintArray[j][0]);
                            ps.println("</td>");

                            ps.println("<td width=35% >");
                            ps.println("<font face=Helvetica size=1 color=#000000>");
                            ps.println(servicePartprintArray[j][4]);
                            ps.println("</td>");

                            ps.println("<td width=7% align = center>");
                            ps.println("<font face=Helvetica size=1 color=#000000>");
                            ps.println(servicePartprintArray[j][1]);
                            ps.println("</td>");

                            ps.println("<td width=30% >");
                            ps.println("<font face=Helvetica size=1 color=#000000>");
                            ps.println(servicePartprintArray[j][2]);
                            ps.println("</td>");

                            ps.println("</tr>");
                        }
                        ps.println("</table>");

                        rs = stmt.executeQuery("SELECT * FROM OP_REMARK WHERE OPTION_NO = '" + group + "' ");
                        String Query = ("SELECT * FROM OP_REMARK WHERE OPTION_NO = '" + group + "' ");
                        stmt = conn.prepareStatement(Query);
                        rs = stmt.executeQuery();
                        if (rs.next())
                        {
                            ps.println("<table cellspacing=0 cellpadding=0 border=0 " + tableWidth + " bordercolor=#000000>");
                            ps.println("<tr>");
                            ps.println("<td align = left>");
                            ps.println("<font face=Helvetica size=1 color=#000000>");
                            ps.println("Note : " + rs.getString("OP_REMARK"));
                            ps.println("</td>");
                            ps.println("</tr>");
                            ps.println("</table>");
                        }
                        rs.close();

                        ps.println("</h1>");
                    }
                    //********** PRINTING OF GROUP SERVICE PARTS ENDS **********//
                    serialNUMBER++;
                }

                stmt.close();
                stmt1.close();
            }
            else
            {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
            ps.println("</body></html>");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ps.println(e);
        }
        finally
        {
            ps.close();
            wps.close();
        }
    }
}
