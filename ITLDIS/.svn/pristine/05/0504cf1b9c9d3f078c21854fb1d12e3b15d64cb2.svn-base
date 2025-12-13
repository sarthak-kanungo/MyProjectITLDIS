package viewEcat.comEcat;

/*
File Name: ModelExport_bom.java
PURPOSE: It is used to export the complete Model BOM to excel.
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
import java.util.Collections;
import java.util.HashMap;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ModelExport_bom extends HttpServlet {

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("application/vnd.ms-excel");
//
//        res.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
//        res.setHeader("Expires", "0");
//        res.setHeader("Pragma", "no-cache");

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
            String userCode = (String) session.getValue("userCode");

            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);


            String imagesURL = object_pageTemplate.imagesURL();


            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            if (session_id.equals(getSession)) {
                Connection conn = holder.getConnection();
                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////



                /*########################################*/

                ///////////////////////////////////////////////////////////////

                //     PriceDetails priceDetails = new PriceDetails(holder.getConnection());
                //     String priceListCode = priceDetails.getPriceListCode(userCode);
                //   String currencyType = priceDetails.getCurrency(priceListCode);
                //     Hashtable partPriceTable = priceDetails.getPartPrice(priceListCode, date_today);
                ////////////////////////////////////////////////////////////////////

                //Statement stmt;
                PreparedStatement stmt = null;
                ResultSet rs;

               // stmt = conn.createStatement();

                String modelNo = req.getParameter("model");



                int rowCounter = 2;
                //Excel related definitions end

                Vector totalGroups = new Vector();
                Vector totalKits = new Vector();

                //*********************************************************************************

                // GET THE LAST PATCH INSTALLED FOR LAST CD INSTALLED

                //*********************************************************************************

                String tableName_MG = "CAT_MODEL_GROUP";

                String temp_1 = "";
                String temp_2 = "";

                HashMap groupDescMap = new HashMap();

                HashMap groupNameMap = new HashMap();
                //rs = stmt.executeQuery("SELECT MG.GROUP_NO,GKD.GK_TYPE FROM " + tableName_MG + " MG, GROUP_KIT_DETAIL GKD WHERE MG.MODEL_NO = '" + modelNo + "' AND MG.GROUP_NO  = GKD.GRP_KIT_NO AND MG.REV_NO = " + revNo + " ORDER BY GKD.P1");
               // rs = stmt.executeQuery("SELECT MG.GROUP_NO,GKD.GK_TYPE,MG.GROUP_TYPE,MG.MAP_NAME, GKD.p1 FROM " + tableName_MG + " MG, CAT_GROUP_KIT_DETAIL GKD WHERE MG.MODEL_NO = '" + modelNo + "' AND MG.GROUP_NO  = GKD.GRP_KIT_NO ORDER BY GKD.P1");
               String query = ("SELECT MG.GROUP_NO,GKD.GK_TYPE,MG.GROUP_TYPE,MG.MAP_NAME, GKD.p1 FROM " + tableName_MG + " MG, CAT_GROUP_KIT_DETAIL(NOLOCK) GKD WHERE MG.MODEL_NO = '" + modelNo + "' AND MG.GROUP_NO  = GKD.GRP_KIT_NO ORDER BY GKD.P1");
                stmt = conn.prepareStatement(query);
                rs = stmt.executeQuery();
               if (rs.next()) {
                    String temp_4 = "";
                    do {
                        temp_1 = rs.getString(1);
                        temp_2 = rs.getString(2);
                        temp_4 = rs.getString(4);

                        if (temp_2.equals("G")) {
                            totalGroups.addElement(temp_1);
                        } else {
                            totalKits.addElement(temp_1);
                        }
                        groupDescMap.put(temp_1, rs.getString(5));
                        groupNameMap.put(temp_1, temp_4);
                    } while (rs.next());
                }
                rs.close();
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                for (int i = 0; i < totalKits.size(); i++) {
                    totalGroups.addElement(totalKits.elementAt(i));
                }

                //    int serialNUMBER = 1;

                Collections.sort(totalGroups);
                //  int partsPerPage = 40;
                //  int print_count = 0;
                //int Checker = 1;

                ps.println("<html>");
                ps.println("<head>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
                ps.println("<title>BOM of MODEL NUMBER. " + modelNo + "</title>");
                ps.println("</head><body>");


                ps.println(" <table width=100% border=1 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC>");

                ps.println("<tr>");
//                ps.println("<td  align = center class=links_txt>");
//                ps.println("<b>" + PageTemplate.GROUP + " Name" + "</b>");
//                ps.println("</td>");

                ps.println("<td  align=left class=links_txt>");
                ps.println("<b>&nbsp;" + PageTemplate.GROUP + "" + "</b>");
                ps.println("</td>");

                ps.println("<td  align=center class=links_txt>");
                ps.println("<b>Fig. No.</b>");
                ps.println("</td>");

                ps.println("<td  align=left class=links_txt>");
                ps.println("<b>&nbsp;Part No.</b>");
                ps.println("</td>");

                ps.println("<td  align=left class=links_txt>");
                ps.println("<b>&nbsp;Description</b>");
                ps.println("</td>");

                ps.println("<td  align=center class=links_txt>");
                ps.println("<b>Qty</b>");
                ps.println("</td>");
               

                ps.println("<td  align=left class=links_txt>");
                ps.println("<b>&nbsp;Remarks</b>");
                ps.println("</td>");

                 ps.println("<td  align=left class=links_txt>");
                ps.println("<b>&nbsp;Interchangeability</b>");
                ps.println("</td>");

                ps.println("<td  align=left class=links_txt>");
                ps.println("<b>&nbsp;Cut off Chassis</b>");
                ps.println("</td>");

                ps.println("<td  align=left class=links_txt>");
                ps.println("<b>&nbsp;Cut off Date</b>");
                ps.println("</td>");

//                ps.println("<td  align=left class=links_txt>");
//                ps.println("<b>&nbsp;Effective TSN</b>");
//                ps.println("</td>");
                ps.println("</tr>");




                String groupDescription = "";
                String groupMap = "";
                String group = "";
                GroupBom ob = null;
                GroupBom ob2 = null;
                int grp_arr_count = 0;
                String partNo = null;
                jxl.write.Label temp;
                String tempSeq = "";
                int cnt = 0;
             //   System.out.println("Inside Export BOM");
                String tsn = "";

                HashMap groupPartTSN = new HashMap();
                PreparedStatement pstmt = conn.prepareStatement("SELECT GROUP_ASSY_NO,NEW_PART,EFFECTIVE_TSN FROM CAT_ECN_IMPL_HISTORY WHERE GROUP_ASSY_NO in (Select GROUP_NO from CAT_MODEL_GROUP where model_no='" + modelNo + "')");
                rs = pstmt.executeQuery();
                while (rs.next()) {
                    groupPartTSN.put(rs.getString(1) + "-" + rs.getString(2), rs.getString(3));
                }
                rs.close();

                for (int v = 0; v < totalGroups.size(); v++) {
                    group = "" + totalGroups.elementAt(v);
                    // System.out.println("" + group);
                    ob = new GroupBom(conn, ps, date_OR_serial, inputDate, serialNo, group, buckleDate);
                    ob2 = ob.getGroupBom();

                    String[][] grp_arr = new String[ob2.print_data_counter][ob.no_array_parameters];
                    grp_arr_count = ob2.print_data_counter;
                    for (int i = 0; i < grp_arr_count; i++) {
                        for (int aa = 0; aa < ob.no_array_parameters; aa++) {
                            grp_arr[i][aa] = ob2.print_data[i][aa];
                            if (grp_arr[i][aa] == null) {
                                grp_arr[i][aa] = " &nbsp;";
                            }
                        }
                    }


                    groupMap = "" + groupNameMap.get(group);
                    groupDescription = "" + groupDescMap.get(group);

                    ///////////////////////////////////////////////// IMAGE LOCATION AS PER REVISION NO //////////////////////////////////////////


                    //..............................PRINTING PER PAGE OF GROUP BOM STARTS


                    //  print_count = 0;

                    tempSeq = "";
                    cnt = 0;

                    for (int j = 0; j < grp_arr_count; j++) {
                        ps.println("<tr >");

//                        ps.println("<td   align=center class=links_txt>");
//                        ps.println(groupMap);
//                        ps.println("</td>");

                        ps.println("<td   align=left class=links_txt>");
                        ps.println("&nbsp;" + groupDescription);
                        ps.println("</td>");


                        if (grp_arr[j][1].equals("AP") || grp_arr[j][1].equals("AA")) {

                            ps.println("<td   align=center class=links_txt>");
                            ps.println(grp_arr[j][1]);
                            ps.println("</td>");
                        } else {
                            //if (tempSeq.equals(grp_arr[j][5])) {
                            //    cnt++;
                                ps.println("<td   align=center class=links_txt>");
                                ps.println("" + grp_arr[j][5]);
                                ps.println("</td>");

                           /* } else {
                                tempSeq = grp_arr[j][5];
                                cnt = 0;

                                ps.println("<td   align=center class=links_txt>");
                                ps.println("" + grp_arr[j][5]);
                                ps.println("</td>");
                                //temp = new jxl.write.Label(2, rowCounter, "" + grp_arr[j][5], textFormat_index);
                            }*/
                        }

                        //  linkPart = grp_arr[j][0].replaceAll("&nbsp;", "");
                        // jxl.write.Label RefNo_2 = new jxl.write.Label(1, rowCounter, "" + ((grp_arr[j][9].replaceAll("_", " ")).replaceAll("&nbsp;", " ")), textFormat);
                        // bomReport.addCell(RefNo_2);
                        partNo = "" + ((grp_arr[j][0].replaceAll("_", " ")).replaceAll("&nbsp;", " "));

                        ps.println("<td   align=left class=links_txt>");
                        ps.println("&nbsp;" + partNo);
                        ps.println("</td>");


                        ps.println("<td   align=left class=links_txt>");
                        ps.println("&nbsp;" + grp_arr[j][10]);
                        ps.println("</td>");



                        if (grp_arr[j][7].equals("YES")) {

                            ps.println("<td  align=center class=links_txt>");
                            ps.println("AR");
                            ps.println("</td>");
                        } else {

                            ps.println("<td   align=center class=links_txt>");
                            ps.println("" + grp_arr[j][3]);
                            ps.println("</td>");
                        }

//                        ps.println("<td   align=left class=links_txt>");
//                        ps.println("&nbsp;" + (grp_arr[j][12].replaceAll("&nbsp;", " ")));
//                        ps.println("</td>");
                       

                        ps.println("<td  align=left class=links_txt>");
                        ps.println("&nbsp;" + grp_arr[j][12]);
                        ps.println("</td>");

                         ps.println("<td  align=left class=links_txt>");
                        ps.println("&nbsp;" + grp_arr[j][16]);
                        ps.println("</td>");

                        ps.println("<td  align=left class=links_txt>");
                        ps.println("&nbsp;" + grp_arr[j][17]);
                        ps.println("</td>");

                        ps.println("<td  align=left class=links_txt>");
                        ps.println("&nbsp;" + grp_arr[j][18]);
                        ps.println("</td>");

//                        tsn = "";
//
//                        tsn = (String) groupPartTSN.get(group + "-" + grp_arr[j][0].replaceAll("&nbsp;", ""));
//
//                        if (tsn == null) {
//                            tsn = "";
//                        }
//
//                        ps.println("<td  align=left class=links_txt>");
//                        ps.println("&nbsp;" + tsn);
//                        ps.println("</td>");


                        ps.println("</tr>");

                        rowCounter++;

                    }
                }

                ps.println("</table>");

                stmt.close();
                pstmt.close();


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
