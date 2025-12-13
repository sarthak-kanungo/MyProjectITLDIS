package viewEcat.comEcat;

/*
File Name: GroupPrintable.java
PURPOSE: File for printing the BOM and image of the group.
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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class GroupPrintable extends HttpServlet {

    java.util.Date date_today = new java.util.Date();
    DateFormat df2 = new SimpleDateFormat("dd-MMM-yyyy");
    String todaysDate = df2.format(date_today);

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
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

            if (session_id.equals(getSession)) {
                Connection conn = holder.getConnection();



                /*########################################*/
                //Statement stmt1 = conn.createStatement();
                PreparedStatement stmt1 = null;
                ResultSet rs1;

                String group = req.getParameter("group");
                String model = req.getParameter("model");

                GroupBom ob = new GroupBom(conn, ps, date_OR_serial, inputDate, serialNo, group, buckleDate);
                GroupBom ob2 = ob.getGroupBom();

                String[][] grp_arr = new String[ob2.print_data_counter][ob.no_array_parameters];
                int grp_arr_count = ob2.print_data_counter;


                for (int i = 0; i < grp_arr_count; i++) {

                    for (int aa = 0; aa < ob.no_array_parameters; aa++) {
                        grp_arr[i][aa] = ob2.print_data[i][aa];
                        if (grp_arr[i][aa] == null) {
                            grp_arr[i][aa] = " &nbsp;";
                        }
                    }


                }


                int tableWidth = 695;

                String groupMap = "";
                //rs1 = stmt1.executeQuery("SELECT MAP_NAME FROM CAT_MODEL_GROUP WHERE GROUP_NO = '" + group + "' and MODEL_NO='" + model + "'");
                String query = ("SELECT MAP_NAME FROM CAT_MODEL_GROUP(NOLOCK) WHERE GROUP_NO = '" + group + "' and MODEL_NO='" + model + "'");
                stmt1 = conn.prepareStatement(query);
                rs1 = stmt1.executeQuery();
                if (rs1.next()) {
                    groupMap = rs1.getString(1);
                }
                rs1.close();

                String groupDescription = "";
                String groupRemark = "";

                //rs1 = stmt1.executeQuery("SELECT p1,REMARKS FROM CAT_GROUP_KIT_DETAIL WHERE GRP_KIT_NO ='" + group + "'");
                String query1 = ("SELECT p1,REMARKS FROM CAT_GROUP_KIT_DETAIL(NOLOCK) WHERE GRP_KIT_NO ='" + group + "'");
               stmt1 = conn.prepareStatement(query1);
               rs1 = stmt1.executeQuery();
                if (rs1.next()) {
                    groupDescription = rs1.getString(1);
                    groupRemark = rs1.getString(2);
                }
                rs1.close();

                /////////////////////////////////////////////////////////////////////////////////



                //	PAGE GENERATION STARTS

                int trHeight = 20;

                ps.println("<html>");
                ps.println("<head>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
                ps.println("<title>BOM of " + PageTemplate.GROUP + " No. " + groupMap + "</title>");
                ps.println(" <object classid='clsid:1663ed61-23eb-11d2-b92f-008048fdd814' id='factory' codebase=" + imagesURL + "/smsx.cab#Version=6,5,439,12 style='display:none' viewastext></object>");
                ps.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">");
                ps.println("<STYLE>");
                ps.println(".break { page-break-after: always; }");
                ps.println("			* { font-family: Helvetica; }");
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
                ps.println("<body><center>");

                //..............................PRINTING PER PAGE OF GROUP BOM STARTS

                int partsPerPage = 45;
                int print_count = 0;
                int Checker = 1;

                for (int i = 0; i < grp_arr_count; i += partsPerPage) {
                    if (grp_arr_count < i + partsPerPage) {
                        print_count = grp_arr_count;
                    } else {
                        print_count = i + partsPerPage;
                    }

                    ps.println("<h1 class=\"break\">");
                    ps.println("<table cellspacing=0 cellpadding=0 border=0 width=695px;><tr><td >");//table no1
                    ps.println("<table cellspacing=0 cellpadding=0 border=0 width=695px;><tr><td >");//table no2


                    ps.println("<table cellspacing=0 cellpadding=2 border=0 width=695px;>");//table no3
                    ps.println("<tr>");
                    ps.println("<td valign=top colspan=0 align = right height=0>");
                    ps.println("<font face=Helvetica size=1 color=#999999>");
                    ps.println("Print Date: " + todaysDate);
                    ps.println("</font>");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("<tr>");
                    ps.println("<td class=small_left_right_bottom_head_1>"); ////small_left_right_bottom_head_2
                    ps.println("<font size=2>");
                    ps.println(groupDescription);
                    ps.println("</font>");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table>");//table no3


                    ps.println("</td></tr><tr><td style=\"padding-top:8px;\">");
                    ps.println("<table cellspacing=0 cellpadding=2 border=0 width=695px;>");//table no4 start
                    ps.println("<tr>");
                    ps.println("<td align=center colspan=7 width =681 height = 920 class=small_left_right_bottom_top>");
                    ps.println("			<embed id=\"imghes\" border-width=\"2px;\" src=\"" + req.getContextPath() + "/svg/" + group + ".svg\" class=\"electricalzoom\" type=\"image/svg+xml\" height=\"920\" width=\"681\">");
                    ps.println("			</embed>");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table>");//table no4 end

                    ps.println("</td></tr></table>");//table no2 end
                    ps.println("</td></tr></table>");//table no1 end


                    ps.println("</h1>");
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


                    int counter = 0;

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
                        ps.println(grp_arr[j][10]);
                        ps.println("</td>");

                        ps.println("<td align='center'  width='4%'  class='small_left_rightt'>");
                        if (grp_arr[j][7].equals("YES")) {
                            ps.println("AR");
                        } else {
                            ps.println(grp_arr[j][3]);
                        }
                        ps.println("</td>");

                        ps.println("<td align='center' width='5%'   class='small_left_right12'>");
                        ps.println(grp_arr[j][16].equals("-") ? "&nbsp" : grp_arr[j][16]);
                        ps.println("</td>");

                        ps.println("<td align='left'  width='20%' style=\"padding-left:5 px\" class='small_left_rightt'>");
                        ps.println(grp_arr[j][17].equals("-") ? "&nbsp" : grp_arr[j][17]);
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


                stmt1.close();
            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
            ps.println("</body></html>");
        } catch (Exception e) {
            ps.println(e);
        } finally {
            ps.close();
            wps.close();
        }
    }
}
