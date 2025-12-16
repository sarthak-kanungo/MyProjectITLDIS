package viewEcat.comEcat;

/*
File Name: PageSearch.java
PURPOSE: It is used to search the text entered by the user in part, assy, groups, kits and  model tables.
HISTORY:
DATE		BUILD	AUTHOR			MODIFICATIONS
NA			v3.4	Deepak Mangal	$$0 Created
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

public class PageSearch extends HttpServlet
{

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        try
        {

            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);


            String footer = "";
            footer = object_pageTemplate.footer();

            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();


            String databaseNAME = object_pageTemplate.databaseNAME();

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////
            //ps.println(header);

            if (session_id.equals(getSession))
            {

                Connection conn = holder.getConnection();

                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));
                
                

                /*########################################*/


               // Statement stmt;
                PreparedStatement stmt;
                ResultSet rs;

                //stmt = conn.createStatement();

                String TEXT_TO_SEARCH = req.getParameter("search_text");
                TEXT_TO_SEARCH = TEXT_TO_SEARCH.replaceAll("\'", "''");


                String TABLE_NAME_1 = "part";
                String COL_NAME_11 = "part_no";
                String COL_NAME_12 = "p1";

                String TABLE_NAME_2 = "ASSY_DETAIL";
                String COL_NAME_21 = "ASSY_NO";
                String COL_NAME_22 = "P1";

                String TABLE_NAME_3 = "CAT_GROUP_KIT_DETAIL";
                String COL_NAME_32 = "P1";

                String TABLE_NAME_4 = "CAT_MODELS";
                String COL_NAME_41 = "MODEL_NO";
                String COL_NAME_42 = "DESCRIPTION";

                String S_NO = "S No";
                String PART = "Part No.";
                String ASSY = "Assembly No.";
                String GROUP = "Group No.";
                String MODEL = "Model";
                String DESC = "Description";
                String WHERE_USED = "Where used?";

                ps.println("<html>");
                ps.println("<head>");

                ps.println("			<style type=text/css>");
                ps.println("			<!--");
                ps.println("			* { font-family: Helvetica; font-size: 8pt;}");
                ps.println("			a:link {				color: #990000;				text-decoration: none; }");
                ps.println("			a:visited { color: #990000;				text-decoration: none;		  }	");
                ps.println("			-->");
                ps.println("			</style>");

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
                ps.println("</head>");

                Vector part_vector = new Vector();
                Vector part_desc_vector = new Vector();
                Vector assy_vector = new Vector();
                Vector assy_desc_vector = new Vector();
                Vector dummy_group_vector = new Vector();
                Vector model_vector = new Vector();
                Vector model_desc_vector = new Vector();

                String temp = "";
                String temp1 = "";

//START SEARCHING IN THE PARTS TABLE

                int match1 = 0;

                //rs = stmt.executeQuery("Select " + COL_NAME_11 + "," + COL_NAME_12 + " from " + TABLE_NAME_1 + " where " + databaseNAME + "(" + COL_NAME_11 + ") LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') order by " + COL_NAME_11);
                String sql = ("Select " + COL_NAME_11 + "," + COL_NAME_12 + " from " + TABLE_NAME_1 + " where " + databaseNAME + "(" + COL_NAME_11 + ") LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') order by " + COL_NAME_11);
                stmt = conn.prepareStatement(sql);
                rs = stmt.executeQuery();
                while (rs.next())
                {
                    temp = rs.getString(1);
                    temp1 = rs.getString(2);

                    if (temp1 == null)
                    {
                        temp1 = "";
                    }
                    temp1 = temp1.toUpperCase();
                    part_vector.addElement(temp);
                    part_desc_vector.addElement(temp1);
                }
                rs.close();

               // rs = stmt.executeQuery("Select " + COL_NAME_11 + "," + COL_NAME_12 + " from " + TABLE_NAME_1 + " where " + databaseNAME + "(" + COL_NAME_12 + ") LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') order by " + COL_NAME_11);
                String sql1 = ("Select " + COL_NAME_11 + "," + COL_NAME_12 + " from " + TABLE_NAME_1 + " where " + databaseNAME + "(" + COL_NAME_12 + ") LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') order by " + COL_NAME_11);
                stmt = conn.prepareStatement(sql1);
                rs = stmt.executeQuery();
                while (rs.next())
                {
                    temp = rs.getString(1);
                    temp1 = rs.getString(2);

                    if (temp1 == null)
                    {
                        temp1 = "";
                    }
                    temp1 = temp1.toUpperCase();

                    if (part_vector.indexOf(temp) == -1)
                    {
                        part_vector.addElement(temp);
                        part_desc_vector.addElement(temp1);
                    }
                    else
                    {
                        continue;
                    }
                }
                rs.close();
                match1 = part_vector.size();

//SEARCHING IN THE PARTS TABLE ENDS

//START SEARCHING IN THE ASSY_DETAIL TABLE

                int match2 = 0;

                //rs = stmt.executeQuery("Select " + COL_NAME_21 + "," + COL_NAME_22 + " from " + TABLE_NAME_2 + " where " + databaseNAME + "(" + COL_NAME_21 + ") LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') order by " + COL_NAME_21);
                String sql2 = ("Select " + COL_NAME_21 + "," + COL_NAME_22 + " from " + TABLE_NAME_2 + " where " + databaseNAME + "(" + COL_NAME_21 + ") LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') order by " + COL_NAME_21);
                stmt = conn.prepareStatement(sql2);
                rs = stmt.executeQuery();
                while (rs.next())
                {
                    temp = rs.getString(1);
                    temp1 = rs.getString(2);

                    if (temp1 == null)
                    {
                        temp1 = "";
                    }
                    temp1 = temp1.toUpperCase();
                    assy_vector.addElement(temp);
                    assy_desc_vector.addElement(temp1);
                }
                rs.close();

                //rs = stmt.executeQuery("Select " + COL_NAME_21 + "," + COL_NAME_22 + " from " + TABLE_NAME_2 + " where " + databaseNAME + "(" + COL_NAME_22 + ") LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') order by " + COL_NAME_21);
               String sql3 = ("Select " + COL_NAME_21 + "," + COL_NAME_22 + " from " + TABLE_NAME_2 + " where " + databaseNAME + "(" + COL_NAME_22 + ") LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') order by " + COL_NAME_21);
               stmt = conn.prepareStatement(sql3);
               rs = stmt.executeQuery();
                while (rs.next())
                {
                    temp = rs.getString(1);
                    temp1 = rs.getString(2);

                    if (temp1 == null)
                    {
                        temp1 = "";
                    }
                    temp1 = temp1.toUpperCase();

                    if (assy_vector.indexOf(temp) == -1)
                    {
                        assy_vector.addElement(temp);
                        assy_desc_vector.addElement(temp1);
                    }
                    else
                    {
                        continue;
                    }
                }
                rs.close();

                match2 = assy_vector.size();

//SEARCHING IN THE ASSY_DETAIL TABLE ENDS

//START SEARCHING IN THE GROUPS TABLE

                String searchCriteria = "MODEL_NO";

                String query = "";
                //FOR DEALER
                if (userFunctionalities.contains("36"))
                {
                    //query = "SELECT MODEL_NO FROM MODELS WHERE "+databaseNAME+"("+searchCriteria+") LIKE "+databaseNAME+"('%')";
                    query = "SELECT MODEL_NO FROM CAT_MODELS WHERE " + databaseNAME + "(" + searchCriteria + ") LIKE " + databaseNAME + "('%') AND STATUS='COMPLETE'";
                }
                else
                {
                    //query = "SELECT MODEL_NO FROM MODELS WHERE "+databaseNAME+"("+searchCriteria+") LIKE "+databaseNAME+"('%') AND STATUS='COMPLETE'";
                    query = "SELECT MODEL_NO FROM CAT_MODELS WHERE " + databaseNAME + "(" + searchCriteria + ") LIKE " + databaseNAME + "('%')";
                }

                Vector totalModels = new Vector();
                rs = stmt.executeQuery(query);
                
                if (rs.next())
                {
                    do
                    {
                        totalModels.addElement("" + rs.getString(1));
                    }
                    while (rs.next());
                }
                rs.close();

                String vc_temp_1 = "";
                for (int i = 0; i < totalModels.size(); i++)
                {
                   // rs = stmt.executeQuery("Select GM.GROUP_NO,GKD.GK_TYPE from CAT_MODEL_GROUP GM, CAT_GROUP_KIT_DETAIL GKD where MODEL_NO = '" + totalModels.elementAt(i) + "' AND " + databaseNAME + "(GM.MAP_NAME) LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') AND GM.GROUP_NO = GKD.GRP_KIT_NO ORDER BY  GKD.GK_TYPE, GM.GROUP_NO");
                    String sqlQuery = ("Select GM.GROUP_NO,GKD.GK_TYPE from CAT_MODEL_GROUP(NOLOCK) GM, CAT_GROUP_KIT_DETAIL(NOLOCK) GKD where MODEL_NO = '" + totalModels.elementAt(i) + "' AND " + databaseNAME + "(GM.MAP_NAME) LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') AND GM.GROUP_NO = GKD.GRP_KIT_NO ORDER BY  GKD.GK_TYPE, GM.GROUP_NO");
                    stmt = conn.prepareStatement(sqlQuery);
                    rs = stmt.executeQuery();
                    if (rs.next())
                    {
                        do
                        {
                            vc_temp_1 = rs.getString(1);
                            if (dummy_group_vector.indexOf(vc_temp_1) == -1)
                            {
                                dummy_group_vector.addElement(vc_temp_1);
                            }
                        }
                        while (rs.next());
                    }
                    rs.close();


                   // rs = stmt.executeQuery("Select GKD.GRP_KIT_NO,MG.GROUP_NO from CAT_MODEL_GROUP MG, CAT_GROUP_KIT_DETAIL GKD where MG.MODEL_NO = '" + totalModels.elementAt(i) + "' AND " + databaseNAME + "(GKD.P1) LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') AND MG.GROUP_NO = GKD.GRP_KIT_NO ORDER BY  MG.GROUP_NO");
                    String sqlQuery1 = ("Select GKD.GRP_KIT_NO,MG.GROUP_NO from CAT_MODEL_GROUP(NOLOCK) MG, CAT_GROUP_KIT_DETAIL(NOLOCK) GKD where MG.MODEL_NO = '" + totalModels.elementAt(i) + "' AND " + databaseNAME + "(GKD.P1) LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') AND MG.GROUP_NO = GKD.GRP_KIT_NO ORDER BY  MG.GROUP_NO");
                    stmt = conn.prepareStatement(sqlQuery1);
                    rs = stmt.executeQuery();
                    if (rs.next())
                    {
                        do
                        {
                            vc_temp_1 = rs.getString(1);
                            if (dummy_group_vector.indexOf(vc_temp_1) == -1)
                            {
                                dummy_group_vector.addElement(vc_temp_1);
                            }
                        }
                        while (rs.next());
                    }
                    rs.close();

                }

//SEARCHING IN THE GROUPS TABLE ENDS

//START SEARCHING IN THE MODELS TABLE

                int match4 = 0;
                if (userFunctionalities.contains("36"))
                {
                    //rs = stmt.executeQuery("Select " + COL_NAME_41 + "," + COL_NAME_42 + " from " + TABLE_NAME_4 + " where " + databaseNAME + "(" + COL_NAME_41 + ") LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') AND STATUS='COMPLETE' order by " + COL_NAME_41);
                	String sqlQuery2 = ("Select " + COL_NAME_41 + "," + COL_NAME_42 + " from " + TABLE_NAME_4 + " where " + databaseNAME + "(" + COL_NAME_41 + ") LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') AND STATUS='COMPLETE' order by " + COL_NAME_41);
                	stmt = conn.prepareStatement(sqlQuery2);
                	rs = stmt.executeQuery();
                }
                else
                {
                   // rs = stmt.executeQuery("Select " + COL_NAME_41 + "," + COL_NAME_42 + " from " + TABLE_NAME_4 + " where " + databaseNAME + "(" + COL_NAME_41 + ") LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') order by " + COL_NAME_41);
                    String sqlQuery = ("Select " + COL_NAME_41 + "," + COL_NAME_42 + " from " + TABLE_NAME_4 + " where " + databaseNAME + "(" + COL_NAME_41 + ") LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') order by " + COL_NAME_41);
                    stmt = conn.prepareStatement(sqlQuery);
                    rs = stmt.executeQuery();
                }
                while (rs.next())
                {
                    temp = rs.getString(1);
                    temp1 = rs.getString(2);
                    model_vector.addElement(temp);
                    model_desc_vector.addElement(temp1);
                }
                rs.close();

                //FOR DEALER
                if (userFunctionalities.contains("36"))
                {
                   // rs = stmt.executeQuery("Select " + COL_NAME_41 + "," + COL_NAME_42 + " from " + TABLE_NAME_4 + " where " + databaseNAME + "(" + COL_NAME_42 + ") LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') AND STATUS='COMPLETE' order by " + COL_NAME_41);
                String sqlQuery = ("Select " + COL_NAME_41 + "," + COL_NAME_42 + " from " + TABLE_NAME_4 + " where " + databaseNAME + "(" + COL_NAME_42 + ") LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') AND STATUS='COMPLETE' order by " + COL_NAME_41);
                stmt = conn.prepareStatement(sqlQuery);
                rs = stmt.executeQuery();
                }
                else
                {
                    //rs = stmt.executeQuery("Select " + COL_NAME_41 + "," + COL_NAME_42 + " from " + TABLE_NAME_4 + " where " + databaseNAME + "(" + COL_NAME_42 + ") LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') order by " + COL_NAME_41);
                String sqlQuery = ("Select " + COL_NAME_41 + "," + COL_NAME_42 + " from " + TABLE_NAME_4 + " where " + databaseNAME + "(" + COL_NAME_42 + ") LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') order by " + COL_NAME_41);
                stmt = conn.prepareStatement(sqlQuery);
                rs = stmt.executeQuery();
                }

                while (rs.next())
                {
                    temp = rs.getString(1);
                    temp1 = rs.getString(2);
                    if (model_vector.indexOf(temp) == -1)
                    {
                        model_vector.addElement(temp);
                        model_desc_vector.addElement(temp1);
                    }
                    else
                    {
                        continue;
                    }
                }
                rs.close();
                match4 = model_vector.size();

//SEARCHING IN THE MODELS TABLE ENDS

//START SEARCHING IN THE KITS TABLE

                Vector kit_vector = new Vector();
                Vector kit_desc_vector = new Vector();

                int match5 = 0;

                //rs = stmt.executeQuery("Select KIT_NO,DESCRIPTION from S_KIT_DETAIL(NOLOCK) where " + databaseNAME + "(KIT_NO) LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') order by KIT_NO");
                String sql11 = ("Select KIT_NO,DESCRIPTION from S_KIT_DETAIL(NOLOCK) where " + databaseNAME + "(KIT_NO) LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') order by KIT_NO");
                stmt = conn.prepareStatement(sql11);
                rs = stmt.executeQuery();
                while (rs.next())
                {
                    temp = rs.getString(1);
                    temp1 = rs.getString(2);
                    kit_vector.addElement(temp);
                    kit_desc_vector.addElement(temp1);
                }
                rs.close();

               // rs = stmt.executeQuery("Select KIT_NO,DESCRIPTION from S_KIT_DETAIL where " + databaseNAME + "(DESCRIPTION) LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') order by KIT_NO");
                String sql12 = ("Select KIT_NO,DESCRIPTION from S_KIT_DETAIL(NOLOCK) where " + databaseNAME + "(DESCRIPTION) LIKE " + databaseNAME + "('%" + TEXT_TO_SEARCH + "%') order by KIT_NO");
               stmt = conn.prepareStatement(sql12);
               rs = stmt.executeQuery();
                while (rs.next())
                {
                    temp = rs.getString(1);
                    temp1 = rs.getString(2);

                    if (kit_vector.indexOf(temp) == -1)
                    {
                        kit_vector.addElement(temp);
                        kit_desc_vector.addElement(temp1);
                    }
                    else
                    {
                        continue;
                    }
                }
                rs.close();
                match5 = kit_vector.size();

//SEARCHING IN THE KITS TABLE ENDS
                ps.println("<body  topmargin = 0 margin height = 0>");

                if ((match1 == 0) && (match2 == 0) && (dummy_group_vector.size() == 0) && (match4 == 0) && (match5 == 0))
                {
                    ps.println("<table width=90% border=1 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC align=center>");
                    ps.println("<tr bgcolor=#CCCCCC>");
                    ps.println("<td align=center>NO RESULTS FOUND FOR THE SEARCH <strong>\"" + TEXT_TO_SEARCH + "\" </strong></td>");
                    ps.println("</tr>");
                    ps.println("</table>");
                    ps.println("</body>");
                    return;
                }


                ps.println("<br><a name='top'>");

                ps.println("<table width=90% border=1 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC align=center>");

                ps.println("<tr>");
                ps.println("<td bgcolor=#003399>");
                ps.println("<font color=#FFFFFF> ");
                ps.println("SEARCHED FOR: <b> \"" + TEXT_TO_SEARCH + "\" </b>");
                ps.println("</td>");
                ps.println("</tr>");

                if (match1 > 0)
                {
                    ps.println("<tr>");
                    ps.println("<td>");
                    ps.println("<a href='#prt'>");
                    ps.println(match1 + " - PART(s) FOUND.");
                    ps.println("</a>");
                    ps.println("</td>");
                    ps.println("</tr>");
                }

                if (match2 > 0)
                {
                    ps.println("<tr>");
                    ps.println("<td>");
                    ps.println("<a href='#assy'>");
                    ps.println(match2 + " - ASSEMBLY(s) FOUND.");
                    ps.println("</a>");
                    ps.println("</td>");
                    ps.println("</tr>");
                }

                if (match5 > 0)
                {
                    ps.println("<tr>");
                    ps.println("<td>");
                    ps.println("<a href='#kit'>");
                    ps.println(match5 + " - KIT(s) FOUND.");
                    ps.println("</a>");
                    ps.println("</td>");
                    ps.println("</tr>");
                }

                if (dummy_group_vector.size() > 0)
                {
                    ps.println("<tr>");
                    ps.println("<td>");
                    ps.println("<a href='#grp'>");
                    ps.println(dummy_group_vector.size() + " - GROUP(s) FOUND.");
                    ps.println("</a>");
                    ps.println("</td>");
                    ps.println("</tr>");
                }

                if (match4 > 0)
                {
                    ps.println("<tr>");
                    ps.println("<td>");
                    ps.println("<a href='#mdl'>");
                    ps.println(match4 + " - MODEL(s) FOUND.");
                    ps.println("</a>");
                    ps.println("</td>");
                    ps.println("</tr>");
                }

                ps.println("</table><br>");

//PRINTING OF PARTS STARTS          
                String winName = "";
                String tempName = "";

                if (match1 > 0)
                {
                    ps.println("<a name='prt'>");
                    ps.println("<table width=90% border=1 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC align=center>");
                    ps.println("<tr>");
                    ps.println("<td colspan=4><b>LIST OF PART(s) FOUND</b></td>");
                    ps.println("</tr>");

                    ps.println("<tr bgcolor=#003399>");

                    ps.println("<td align=center width=7%><strong><font color=#FFFFFF>" + S_NO + " </strong></td>");
                    ps.println("<td align=center width=23%><strong><font color=#FFFFFF>" + PART + "</strong></td>");
                    ps.println("<td align=center width=55%><strong><font color=#FFFFFF>" + DESC + "</strong></td>");
                    ps.println("<td align=center width=15%><strong><font color=#FFFFFF>" + WHERE_USED + " </strong></td>");
                    ps.println("</tr>");



                    for (int i = 0; i < match1; i++)
                    {
                        ps.println("<tr>");
                        ps.println("<td align=center>");
                        ps.println("<font size=1 face=Helvetica color=#000000>");
                        ps.println((i + 1));
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("<td align=left>");
                        ps.println("<font size=1 face=Helvetica color=#000000>");

                        winName = "" + part_vector.elementAt(i);
                        winName = winName.replaceAll("-", "");

                        ps.println("<a href= javascript:MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + part_vector.get(i) + "','DETAILS','scrollbars=yes,width=700,height=605')>");

                        tempName = "" + part_vector.get(i);
                        ps.println(tempName.replaceAll("_", "&nbsp;") + "</a>");
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("<td align=left>");
                        ps.println("<font size=1 face=Helvetica color=#000000>");

                        ps.println("<a href= javascript:MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + part_vector.get(i) + "','DETAILS','scrollbars=yes,width=700,height=605')>");

                        ps.println(part_desc_vector.get(i) + "</a>");
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("<td align=center>");
                        ps.println("<font size=1 face=Helvetica color=#000000>");

                        ps.println("<a href= javascript:MM_openBrWindow('" + servletURL + "Part_where_used?part=" + part_vector.get(i) + "','WHEREUSED','scrollbars=yes,width=700,height=605')>");

                        ps.println(WHERE_USED + "</a>");

                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("</tr>");
                    }
                    ps.println("<tr>");
                    ps.println("<td colspan=4 align = right>");
                    ps.println("<a href='#top'>TOP</a>");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table><br>");
                }
//PRINTING OF PARTS ENDS

//PRINTING OF ASS_DETAIL STARTS

                if (match2 > 0)
                {
                    ps.println("<a name='assy'><br>");
                    ps.println("<table width=90% border=1 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC align=center>");
                    ps.println("<tr>");
                    ps.println("<td colspan=4><b>LIST OF ASSEMBLY(s) FOUND</b></td>");
                    ps.println("</tr>");

                    ps.println("<tr bgcolor=#003399>");
                    ps.println("<td align=center width=7%><strong><font color=#FFFFFF>" + S_NO + " </strong></td>");
                    ps.println("<td align=center width=23%><strong><font color=#FFFFFF>" + ASSY + "</strong></td>");
                    ps.println("<td align=center width=55%><strong><font color=#FFFFFF>" + DESC + "</strong></td>");
                    ps.println("<td align=center width=15%><strong><font color=#FFFFFF>" + WHERE_USED + " </strong></td>");
                    ps.println("</tr>");

                    for (int i = 0; i < match2; i++)
                    {
                        ps.println("<tr>");
                        ps.println("<td align=center>");
                        ps.println("<font size=1 face=Helvetica color=#000000>");
                        ps.println((i + 1));
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("<td align=left>");
                        ps.println("<font size=1 face=Helvetica color=#000000>");

                        winName = "" + assy_vector.get(i);
                        winName = winName.replaceAll("-", "");

                        ps.println("<a href= javascript:MM_openBrWindow('" + servletURL + "AssyDetails?assyNo=" + assy_vector.get(i) + "','" + winName + "','scrollbars=yes,width=700,height=605')>");

                        tempName = "" + assy_vector.get(i);
                        ps.println(tempName.replaceAll("_", "&nbsp;") + "</a>");

                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("<td align=left>");
                        ps.println("<font size=1 face=Helvetica color=#000000>");

                        ps.println("<a href= javascript:MM_openBrWindow('" + servletURL + "AssyDetails?assyNo=" + assy_vector.get(i) + "','" + winName + "','scrollbars=yes,width=700,height=605')>");

                        ps.println(assy_desc_vector.get(i) + "</a>");
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("<td align=center>");
                        ps.println("<font size=1 face=Helvetica color=#000000>");

                        ps.println("<a href= javascript:MM_openBrWindow('" + servletURL + "Part_where_used?part=" + assy_vector.get(i) + "','WHEREUSED','scrollbars=yes,width=700,height=605')>");

                        ps.println(WHERE_USED + "</a>");
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("</tr>");
                    }
                    ps.println("<tr>");
                    ps.println("<td colspan=4 align = right>");
                    ps.println("<a href='#top'>TOP</a>");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table><br>");
                }
//PRINTING OF ASS_DETAIL ENDS

//PRINTING OF KITS STARTS

                if (match5 > 0)
                {
                    ps.println("<a name='kit'>");
                    ps.println("<table width=90% border=1 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC align=center>");
                    ps.println("<tr>");
                    ps.println("<td colspan=4><b>LIST OF KIT(s) FOUND</b></td>");
                    ps.println("</tr>");

                    ps.println("<tr bgcolor=#003399>");
                    ps.println("<td align=center width=7%><strong><font color=#FFFFFF>" + S_NO + " </strong></td>");
                    ps.println("<td align=center width=23%><strong><font color=#FFFFFF>KIT NO</strong></td>");
                    ps.println("<td align=center width=55%><strong><font color=#FFFFFF>" + DESC + "</strong></td>");
                    ps.println("<td align=center width=15%><strong><font color=#FFFFFF>" + WHERE_USED + " </strong></td>");
                    ps.println("</tr>");

                    for (int i = 0; i < match5; i++)
                    {
                        ps.println("<tr>");
                        ps.println("<td align=center>");
                        ps.println("<font size=1 face=Helvetica color=#000000>");
                        ps.println((i + 1));
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("<td align=left>");
                        ps.println("<font size=1 face=Helvetica color=#000000>");

                        winName = "" + kit_vector.elementAt(i);
                        winName = winName.replaceAll("-", "");

                        ps.println("<a href= javascript:MM_openBrWindow('" + servletURL + "NewSKits?sKitNo=" + kit_vector.get(i) + "','DETAILS','scrollbars=yes,width=700,height=605')>");

                        tempName = "" + kit_vector.get(i);
                        ps.println(tempName.replaceAll("_", "&nbsp;") + "</a>");
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("<td align=left>");
                        ps.println("<font size=1 face=Helvetica color=#000000>");

                        ps.println("<a href= javascript:MM_openBrWindow('" + servletURL + "NewSKits?sKitNo=" + kit_vector.get(i) + "','DETAILS','scrollbars=yes,width=700,height=605')>");

                        ps.println(kit_desc_vector.get(i) + "</a>");
                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("<td align=center>");
                        ps.println("<font size=1 face=Helvetica color=#000000>");

                        ps.println("<a href= javascript:MM_openBrWindow('" + servletURL + "Part_where_used?part=" + kit_vector.get(i) + "','WHEREUSED','scrollbars=yes,width=700,height=605')>");

                        ps.println(WHERE_USED + "</a>");

                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("</tr>");
                    }
                    ps.println("<tr>");
                    ps.println("<td colspan=4 align = right>");
                    ps.println("<a href='#top'>TOP</a>");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table><br>");
                }
//PRINTING OF KITS ENDS

//PRINTING OF GROUPS STARTS

                if (dummy_group_vector.size() > 0)
                {
                    ps.println("<a name='grp'>");
                    ps.println("<table width=90% border=1 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC align=center>");
                    ps.println("<tr>");
                    ps.println("<td colspan=4><b>LIST OF GROUP(s) FOUND</b></td>");
                    ps.println("</tr>");

                    ps.println("<tr bgcolor=#003399>");
                    ps.println("<td align=center width=7%><strong><font color=#FFFFFF>" + S_NO + " </strong></td>");
                    ps.println("<td align=center width=14%><strong><font color=#FFFFFF>" + GROUP + "</strong></td>");
                    ps.println("<td align=center width=35%><strong><font color=#FFFFFF>" + DESC + "</strong></td>");
                    ps.println("<td align=center width=44%><strong><font color=#FFFFFF>" + WHERE_USED + " </strong></td>");
                    ps.println("</tr>");
                    String tempHeading = "";
                    String tempDescription = "";
                    Vector allMODELS = new Vector();
                    for (int vv = 0; vv < dummy_group_vector.size(); vv++)
                    {
                        allMODELS = new Vector();
                        //FOR DEALER
                        if (userFunctionalities.contains("36"))
                        {
                            //rs = stmt.executeQuery("SELECT MGM.MAP_NAME, MGM.MODEL_NO, M.MODEL_NO,mc.ENGINE_SERIES,mc.ENGINE_MODEL FROM CAT_MODEL_GROUP(NOLOCK) MGM, CAT_MODELS(NOLOCK) M WHERE M.MODEL_NO = MGM.MODEL_NO AND M.STATUS='COMPLETE' AND MGM.GROUP_NO = '" + dummy_group_vector.elementAt(vv) + "'");
                        	String sql13 = ("SELECT MGM.MAP_NAME, MGM.MODEL_NO, M.MODEL_NO,mc.ENGINE_SERIES,mc.ENGINE_MODEL FROM CAT_MODEL_GROUP(NOLOCK) MGM, CAT_MODELS(NOLOCK) M WHERE M.MODEL_NO = MGM.MODEL_NO AND M.STATUS='COMPLETE' AND MGM.GROUP_NO = '" + dummy_group_vector.elementAt(vv) + "'");
                        	stmt = conn.prepareStatement(sql13);
                        	rs = stmt.executeQuery();
                       
                        }
                        else
                        {
                            //rs = stmt.executeQuery("SELECT MGM.MAP_NAME, MGM.MODEL_NO, M.MODEL_NO,mc.ENGINE_SERIES,mc.ENGINE_MODEL FROM CAT_MODEL_GROUP(NOLOCK) MGM, CAT_MODELS(NOLOCK) M WHERE M.MODEL_NO = MGM.MODEL_NO AND MGM.GROUP_NO = '" + dummy_group_vector.elementAt(vv) + "'");
                            String query22 = ("SELECT MGM.MAP_NAME, MGM.MODEL_NO, M.MODEL_NO,mc.ENGINE_SERIES,mc.ENGINE_MODEL FROM CAT_MODEL_GROUP(NOLOCK) MGM, CAT_MODELS(NOLOCK) M WHERE M.MODEL_NO = MGM.MODEL_NO AND MGM.GROUP_NO = '" + dummy_group_vector.elementAt(vv) + "'");
                            stmt = conn.prepareStatement(sql12);
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
                            }
                            while (rs.next());
                        }
                        rs.close();


                        //rs = stmt.executeQuery("SELECT " + COL_NAME_32 + " from " + TABLE_NAME_3 + " WHERE GRP_KIT_NO = '" + dummy_group_vector.elementAt(vv) + "'");
                        String Quary =("SELECT " + COL_NAME_32 + " from " + TABLE_NAME_3 + " WHERE GRP_KIT_NO = '" + dummy_group_vector.elementAt(vv) + "'");
                        stmt = conn.prepareStatement(Quary);
                        rs = stmt.executeQuery();
                        if (rs.next())
                        {
                            tempDescription = rs.getString(1);
                        }
                        rs.close();

                        ps.println("<tr>");
                        ps.println("<td align=center valign = top>");
                        ps.println("<font size=1 face=Helvetica >");
                        ps.println(vv + 1);
                        ps.println("</font>");
                        ps.println("</td> ");

                        if (allMODELS.size() == 0)
                        {
                            ps.println("<td valign=top align = left>");
                            ps.println("<font size=1 face=Helvetica>");
                            ps.println(dummy_group_vector.elementAt(vv));
                            ps.println("</font>");
                            ps.println("</td> ");

                            ps.println("<td align=left valign = top>");
                            ps.println("<font size=1 face=Helvetica >");
                            ps.println(tempDescription);
                            ps.println("</font>");
                            ps.println("</a>");
                            ps.println("</td> ");
                        }
                        else
                        {
                            ps.println("<td valign=top align = left>");
                            ps.println("<a href=\"" + servletURL + "ViewGroupImage_BOM?group=" + dummy_group_vector.elementAt(vv) + "&model=" + allMODELS.elementAt(0) + "\" target = right>");
                            ps.println("<font size=1 face=Helvetica>");
                            ps.println(tempHeading);
                            ps.println("</font>");
                            ps.println("</a>");
                            ps.println("</td> ");

                            ps.println("<td align=left valign = top>");
                            ps.println("<a href=\"" + servletURL + "ViewGroupImage_BOM?group=" + dummy_group_vector.elementAt(vv) + "&model=" + allMODELS.elementAt(0) + "\" target = right>");
                            ps.println("<font size=1 face=Helvetica >");
                            ps.println(tempDescription);
                            ps.println("</font>");
                            ps.println("</a>");
                            ps.println("</td> ");
                        }

                        ps.println("<td align=left valign = top>");

                        for (int i = 0; i < allMODELS.size(); i++)
                        {

                            String modelDesc = "";
                            //rs = stmt.executeQuery("SELECT DESCRIPTION FROM CAT_MODELS WHERE MODEL_NO = '" + allMODELS.elementAt(i) + "'");
                            String sqlQuary12 = ("SELECT DESCRIPTION FROM CAT_MODELS(NOLOCK) WHERE MODEL_NO = '" + allMODELS.elementAt(i) + "'");
                            stmt = conn.prepareStatement(sqlQuary12);
                            rs = stmt.executeQuery();
                            if (rs.next())
                            {
                                modelDesc = rs.getString(1);
                            }
                            rs.close();

                            ps.println("<a href='ViewModel?model=" + allMODELS.elementAt(i) + "&highGroup=" + dummy_group_vector.elementAt(vv) + "' target = right>");
                            ps.println("<font size=1 face=Helvetica >");

                            ps.println(modelDesc + " [ " + allMODELS.elementAt(i) + " ]");

                            if (i != allMODELS.size() - 1)
                            {
                                ps.println("<br>");
                            }

                            ps.println("</font>");
                            ps.println("</a>");
                        }
                        ps.println("&nbsp;</td> ");
                        ps.println("	</tr>");
                    }
                    ps.println("<tr>");
                    ps.println("<td colspan=4 align = right>");
                    ps.println("<a href='#top'>TOP</a>");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table><br>");
                }

//PRINTING OF GROUPS ENDS

//PRINTING OF MODELS STARTS
                if (match4 > 0)
                {
                    ps.println("<a name='mdl'><br>");
                    ps.println("<table width=90% border=1 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC align=center>");
                    ps.println("<tr>");
                    ps.println("<td colspan=3><b>LIST OF MODEL(s) FOUND</b></td>");
                    ps.println("</tr>");

                    ps.println("<tr bgcolor=#003399>");
                    ps.println("<td align=center width=8%><strong><font color=#FFFFFF>" + S_NO + " </strong></td>");
                    ps.println("<td width=92%><strong><font color=#FFFFFF>" + MODEL + " Description/Number/Catalogue No</strong></td>");
                    ps.println("</tr>");

                    for (int i = 0; i < match4; i++)
                    {
                        ps.println("<tr>");
                        ps.println("<td align=center>");
                        ps.println("<font size=1 face=Helvetica color=#000000>");
                        ps.println("<p>" + (i + 1) + "</p>");
                        ps.println("</font>");
                        ps.println("</td>");

                        ps.println("<td align=left>");
                        ps.println("<font size=1 face=Helvetica color=#000000>");
                        ps.println("<p><a href='ViewAggregates?model=" + model_vector.get(i) + "'>" + model_desc_vector.get(i) + " [ " + model_vector.get(i) + " ]");

                        ps.println("</font>");
                        ps.println("</td>");
                        ps.println("</tr>");
                    }
                    ps.println("<tr>");
                    ps.println("<td colspan=3 align = right>");
                    ps.println("<a href='#top'>TOP</a>");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table><br>");
                    ps.println("</body>");
                }
//PRINTING OF MODELS ENDS
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
            ps.println(e);
        }
        finally
        {
            ps.close();
            wps.close();
        }
    }
}
