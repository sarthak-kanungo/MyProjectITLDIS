package viewEcat.comEcat;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
File Name: PartDetails.java
PURPOSE: It is used to show the part details like MOQ, QML, RP with 3D view of part
HISTORY:
DATE		BUILD	AUTHOR			MODIFICATIONS
NA			v3.4	Deepak Mangal	$$0 Created
 */
import authEcat.UtilityMapkeys1;
import viewEcat.orderEcat.PriceDetails;

public class PartDetails extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        String subQuery = "";
        String productFamilySubQuery = null;
        String applicationTypSubQuery = null;
        String message = null;
        String engineSeries = null;
        String engineModel = null;
        //String part_type=null;
        try {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            //   String getType = (String) session.getValue("user_type");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String userCode = (String) session.getValue("userCode");
            String date_OR_serial = (String) session.getValue("date_OR_serial");
            java.sql.Date inputDate = (java.sql.Date) session.getValue("input_Date");
            java.sql.Date buckleDate = (java.sql.Date) session.getValue("buckleDate");
            String serialNo = (String) session.getValue("input_serialNo");
            productFamilySubQuery = "" + session.getAttribute("productFamilySubQuery");
            applicationTypSubQuery = "" + session.getAttribute("applicationTypSubQuery");

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


            String part_imagesURL = "";
            part_imagesURL = object_pageTemplate.part_imagesURL();

            String animURL = "";
            animURL = object_pageTemplate.animURL();

            String orderRefNoString = object_pageTemplate.orderRefNoString();

            /*         String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();
            String header = "";
            header = object_pageTemplate.header(getType);
            String footer = "";
            footer = object_pageTemplate.footer();
             */          ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            if (session_id.equals(getSession)) {
                Vector allASSY = new Vector();
                Vector allSKITS = new Vector();
                Vector allGROUPS = new Vector();

                Connection conn;
                //     conn = holder.getConnection();

                // String XPCounter = req.getParameter("XPCounter");
                // if (!((XPCounter == null) || (XPCounter.equals("null"))))
                //{
                conn = holder.getConnection();
                /* }
                else
                {
                Session dsn1 = new Session();
                date_OR_serial = dsn1.getDate_OR_serial();
                animURL = dsn1.getAnimURL();
                imagesURL = dsn1.getImagesURL();
                ecatPATH = dsn1.getEcatPATH();
                conn = dsn1.getConnection();
                }
                 */
                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));



                /*########################################*/

                ///////////////////////////////////////////////////////////////

                PriceDetails priceDetails = new PriceDetails(holder.getConnection());
                String priceListCode = priceDetails.getPriceListCode(userCode);
                String currencyType = priceDetails.getCurrency(priceListCode);
               // boolean isOrderable = priceDetails.isPriceListOrderable(priceListCode);

                ////////////////////////////////////////////////////////////////////

                //Statement stmt = conn.createStatement();
                PreparedStatement stmt = null;
                ResultSet rs;

                String partNo = req.getParameter("partNo");
                String linksReq = req.getParameter("linksReq");

                partNo = partNo.trim();
                partNo = partNo.toUpperCase();

                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////


                ArrayList<String> enabledList = new ArrayList();
                ResultSet rs1 = null;
               // Statement stmt1 = conn.createStatement();
                PreparedStatement stmt1 = null;

               // rs1 = stmt1.executeQuery("select part_no from CAT_PART(NOLOCK) where p4='Y' or p4='Yes'");
               String Query = ("select part_no from CAT_PART(NOLOCK) where p4='Y' or p4='Yes'");
               stmt1 = conn.prepareStatement(Query);
               rs1 = stmt1.executeQuery();
                while (rs1.next()) {
                    enabledList.add(rs1.getString(1).trim());
                }
                rs1.close();
                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                ps.println("<html>");
                ps.println("<head>");
                ps.println("<title>");
                ps.println("" + UtilityMapkeys1.tile1 + "");
                ps.println("</title>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");

                ps.println("	<script language=javascript>");

                ps.println("			function onTop()");
                ps.println("			{");
                ps.println("				self.focus();");
                ps.println("				window.moveTo(0,0);");
                ps.println("			}");

                ps.println("			var top_level = window");
                ps.println("			function WebViewCheck()");
                ps.println("			{");
                ps.println("				top_level.IPAControl = parent.IPAControl ");
                ps.println("				top_level.IPAControl.SetBGColor('#336699') ");
                ps.println("			}");

                ps.println("		function getQTY(qml)");
                ps.println("		{ ");
                ps.println("                    var qty = prompt('Enter Quantity To Order',qml);");
                ps.println("                    if ((qty!=null))");
                ps.println("                    {");
                ps.println("                         if ((qty=='') || (isNaN(qty)) || (qty.indexOf(' ')!=-1) || (Number(qty)==0) || (qty.indexOf('.')!=-1)||(qty.indexOf('+')!=-1)||(qty.indexOf('-')!=-1))");
                ps.println("                        {");
                ps.println("				alert('Please Enter Numeric Value.');");
                ps.println("				getQTY(qml)");
                ps.println("                        }");
                ps.println("                        else");
                ps.println("                        {");
                ps.println("                                while(qty.indexOf('0')==0)");
                ps.println("                                {");
                ps.println("                                 qty=    qty.replace('0','');");
                ps.println("                                }");
                ps.println("				if (qty.length>4)");
                ps.println("				{");
                ps.println("					alert('Quantity Cannot Be Greater Than 9999.')");
                ps.println("					getQTY(qml)");
                ps.println("				}");
                ps.println("				else if(qty<qml)");
                ps.println("				{");
                ps.println("					alert('Quantity Cannot Be Less Than '+qml+'.')");
                ps.println("					getQTY(qml)");
                ps.println("				}");
                ps.println("				else if((qty%qml)!=0)");
                ps.println("				{");
                ps.println("					alert('Quantity Must Be In Multiples of '+qml+'.')");
                ps.println("					getQTY(qml)");
                ps.println("				}");
                ps.println("				else");
                ps.println("				{");
                ps.println("					document.order.ordQty.value = qty;	");
                ps.println("					document.order.submit();");
                ps.println("				}");
                ps.println("                        }");
                ps.println("                    }");
                ps.println("		}");

                ps.println("		function MM_openBrWindow(theURL,winName,features)");
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

                ps.println("  function windClose()");
                ps.println(" {");
                ps.println(" self.close()");
                ps.println(" }");

                ps.println("</script>");

                ps.println("</head>");
                ps.println("<body topmargin = 0 margin height = 0 onLoad=onTop()>");
                int checkPromoItem = 0;
                String temp = "";
                String partPrice = "-";
                int width = 660, height = 0;
                String partType = "";
                String cssClass = null;

                if (linksReq == null) {
                    ps.println("<table width=" + width + "  border=0 cellspacing=0 cellpadding=0  bordercolor = #000000>");
                    ps.println(" <tr><td align=left>");

                    ps.println("<table cellspacing=1 cellpadding=1 border=0  bordercolor = #000000>");
                    ps.println("<tr>");
                    ps.println("<td valign=top align = left>");
                    ps.println("<a href ='javascript:history.go(-1)'>");
                    ps.println("<img src = " + imagesURL + "back.gif border = 0 alt = BACK>");
                    ps.println("</a>");
                    ps.println("</td>");

                    ps.println("<td valign=top align = left>");
                    ps.println("<a href ='javascript:history.go(1)'>");
                    ps.println("<img src = " + imagesURL + "forward.gif border = 0 alt = FORWARD>");
                    ps.println("</a>");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table>");

                    ps.println("<td valign=middle align = right>");
                    ps.println("<a href ='javascript:windClose()'>");
                    ps.println("<img src = " + imagesURL + "close.jpg border = 0 alt = CLOSE>");
                    ps.println("</a>");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table>");
                } else {
                    ps.println("<br>");
                }



                //rs = stmt.executeQuery("SELECT part_type,p1,p3,p4,np1,np2 FROM CAT_PART WHERE PART_NO ='" + partNo + "'");
                String query1 = ("SELECT part_type,p1,p3,p4,np1,np2 FROM CAT_PART(NOLOCK) WHERE PART_NO ='" + partNo + "'");
                stmt = conn.prepareStatement(query1);
                rs = stmt.executeQuery();
                if (rs.next()) {

                    //part_type=rs.getString("part_type");
                    if (partNo.startsWith("TBA")) {
                        ps.println(object_pageTemplate.tableHeader(ComUtils.getLanguageTranslation("label.catalogue.detailsOf", session) + " \"-\"", width, height));
                    } else {
                        ps.println(object_pageTemplate.tableHeader(ComUtils.getLanguageTranslation("label.catalogue.detailsOf", session) + " \"" + partNo + "\"", width, height));
                    }
                    ps.println("<table cellspacing=1 cellpadding=2 border=0 width=98% bordercolor = #000000>");


                    partType = rs.getString(1);


                    if (userFunctionalities.contains("281")) {
                        partPrice = "" + priceDetails.getPartPrice(partNo, priceListCode, new java.util.Date());
                        if ((partPrice == null) || (partPrice.equals("null")) || (partPrice.equals(""))) {
                            partPrice = "--";
                        }
                    }


                    String partDesc = rs.getString(2);

                    if (partDesc == null) {
                        partDesc = "-";
                    }
                    String remarks = rs.getString(3);
                    if (remarks == null || remarks.equals("null") || remarks.equals("")) {
                        remarks = "-";
                    }
                    String part_serviceable = rs.getString(4);
                    if (part_serviceable == null || part_serviceable.equals("null") || part_serviceable.equals("")) {
                        part_serviceable = "-";
                    }
                    String packinglot = rs.getString(5);
                    String qml = rs.getString(6);
                    if (qml == null || qml.equals("null") || qml.equals("")) {
                        qml = "-";
                    }

                    ps.println("<tr>");
                    ps.println("<td valign=top align = center>");


                    ps.println("<table width=100% border=0 cellspacing=0 cellpadding=3 bordercolor = #CCCCCC>");

                    ps.println("<tr>");
                    ps.println("<td width=25% valign=top class=links_txt><b>");
                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.description", session));
                    ps.println("</b></td><td width=10% align=center valign=top class=links_txt> : </td>");
                    ps.println("<td width=65% valign=top class=links_txt>");
                    ps.println(partDesc.toUpperCase());
                    ps.println("</td> ");
                    ps.println("</tr>");

                    ps.println("<tr>");
                    ps.println("<td width=25% valign=top class=links_txt><b>");
                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.serviceability", session));
                    ps.println("</b></td><td width=10% align=center valign=top class=links_txt> : </td>");
                    ps.println("<td width=65% valign=top class=links_txt>");
                    ps.println(part_serviceable);
                    ps.println("</td> ");
                    ps.println("</tr>");

                    ps.println("<tr>");
                    ps.println("<td width=25% valign=top class=links_txt><b>");
                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.remarks", session));
                    ps.println("</b></td><td width=10% align=center valign=top class=links_txt> : </td>");
                    ps.println("<td width=65% valign=top class=links_txt>");
                    ps.println(remarks);
                    ps.println("</td> ");
                    ps.println("</tr>");


                    ps.println("<tr>");
                    ps.println("	<td width=25% valign=top class=links_txt><b>");
                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.mrp", session) + "(" + currencyType + ")");
                    ps.println("</b></td><td width=10% align=center valign=top class=links_txt> : </td>");
                    ps.println("<td width=65% valign=top class=links_txt>");
                    if (partPrice == null || partPrice.equals("null") || partPrice.equals("")) {
                        partPrice = "-";
                    }
                    ps.println(partPrice);
                    ps.println("</td> ");

                    ps.println("</tr>");



                    ps.println("</table>");

                    if (userFunctionalities.contains("34")) {
                        if ((checkPromoItem == 0) && (linksReq == null)) {
                            //   if (!partPrice.equals("--")) {
                            // if (isOrderable) {
                            //if (part_serviceable.equalsIgnoreCase("Y") || part_serviceable.equalsIgnoreCase("Yes"))
                            // {
                            qml = "1";


                            if (enabledList.indexOf(partNo) != -1) {
                                ps.println("<form name=order action=Add_2_cart>");
                                ps.println("<input type=hidden name=ordQty>");
                                ps.println("<input type=hidden name=partNo value=" + partNo + ">");
                                ps.println("<input type=hidden name=qml value=" + qml + ">");
                                ps.println("<br>");
                                ps.println("<a href = # onclick=getQTY(1)>");
                                ps.println("<IMG SRC=" + imagesURL + "add2cart.gif border = 0> ");
                                ps.println("</a>");
                                ps.println("</form>");
                            } else {
                                ps.println("<IMG SRC=" + imagesURL + "add2cartDisabled.gif border = 0> ");
                            }

//                            }
//                            else
//                            {
//                                ps.println("<br>");
//                                ps.println("<IMG SRC=" + imagesURL + "add2cartDisabled.gif border = 0 alt='It is a non-serviceable part. So, it cannot be added to cart'> ");
//                            }
                            // } else {
                            //      ps.println("<br>");
                            //      ps.println("<IMG SRC=" + imagesURL + "add2cartDisabled.gif border = 0 alt='Price List Expired. So, part cannot be added to cart'> ");
                            //  }
                            //} else {
                            //     ps.println("<br>");
                            //     ps.println("<IMG SRC=" + imagesURL + "add2cartDisabled.gif border = 0 alt='Price Not Available. So, part cannot be added to cart'> ");
                            // }
                        }
                    }

                    ps.println("</td>");


                    int idbExists = 0;
                    int jpgExists = 0;
                    String idb_path_1 = ecatPATH + "/dealer/anim/";
                    String idb_path_2 = ".idb";

                    File idb_File = new File(idb_path_1 + partNo + idb_path_2);
                    if (idb_File.isFile()) {
                        idbExists = 1;
                    }

                    String jpg_full_path = ecatPATH + "/dealer/ecat_print/part_image/" + partNo + ".jpg";
                    File jpg_File = new File(jpg_full_path);
                    if (jpg_File.isFile()) {
                        jpgExists = 1;
                    }

                    if (orderRefNoString.equals("LOCAL")) {
                        String pathChecker = animURL.substring(0, 5);

                        if (pathChecker.equals("file:")) {
                            int len = animURL.length();
                            animURL = animURL.substring(8, len);
                            animURL = animURL.replaceAll("/", "\\\\");
                        }
                    }

                    if (idbExists == 1) {
                        ps.println("<td align=center width = 40% valign = bottom>");
                        ps.println("<OBJECT ID=IPAControl CLASSID=CLSID:22CF0C35-80CE-11D3-9354-00105AA793BF CODEBASE=http://www.immdesign.com/webview/IPAWebView.cab#Version=1,0,0,84 width=250 height=250 >");
                        ps.println("	<PARAM Name=Openfile Value=" + animURL + partNo + ".idb>");
                        ps.println("	<PARAM Name=Autoplay Value=0>");
                        ps.println("	<PARAM Name=Fixed Value=1>");
                        ps.println("	</OBJECT>");

                        if (!(date_OR_serial.equals("latest"))) {
                            ps.println("<br>");
                            ps.println("* Latest 3D View of Part.");
                        }

                        ps.println("<br>");
                        ps.println("<IMG SRC=" + imagesURL + "ipaHelp.gif border=0>");
                        ps.println("</td>");
                    } else if (jpgExists == 1) {
                        ps.println("<td align=center width = 40% valign = bottom>");
                        ps.println("<IMG SRC=" + part_imagesURL + partNo + ".jpg border = 1 bordercolor = #000000 width = 250 height = 250>");
                        ps.println("</td>");
                    } else {
                    }


                    ps.println("</tr>");
                    ps.println("</table>");
                    ps.println(object_pageTemplate.tableFooter());
                }
                rs.close();
                //////////////////////////////////////////////////////////////
                // BOM GENERATION OF ASSY STARTS
                int counter = 1;
                String tempPartNo = null;
                String partNo_temp = null;
                int l = 0;
                //BOM OF KIT START
                if (partType.equalsIgnoreCase("KIT")) {
                    //kitBomDetail=new ArrayList();
                    //rs = stmt.executeQuery("select distinct skb.component,p.p1,skb.qty from CAT_S_KIT_BOM skb,CAT_PART p  where p.part_no=skb.component and kit_no='" + partNo + "'");
                	String sqlQuery3 = ("select distinct skb.component,p.p1,skb.qty from CAT_S_KIT_BOM(NOLOCK) skb,CAT_PART(NOLOCK) p  where p.part_no=skb.component and kit_no='" + partNo + "'");
                	stmt = conn.prepareStatement(sqlQuery3);
                	rs = stmt.executeQuery();

                    // System.out.println("select distinct skb.component,p.p1,skb.qty from S_KIT_BOM skb,part p  where p.part_no=skb.component and kit_no='" + partNo + "'");

                    while (rs.next()) {
                        if (counter == 1) {
                            if (partNo.startsWith("TBA")) {
                                ps.println(object_pageTemplate.tableHeader(ComUtils.getLanguageTranslation("label.catalogue.bomOfPartNo", session) + " \"" + PageTemplate.tbaPart + "\"", width, height));
                            } else {
                                ps.println(object_pageTemplate.tableHeader(ComUtils.getLanguageTranslation("label.catalogue.bomOfPartNo", session) + " \"" + partNo + "\"", width, height));
                            }

                            //ps.println(" <table width=100% border=0 cellspacing=0 cellpadding=0 bordercolor = #CCCCCC> <tr><td align=left  width=100%>");



                            ps.println("<table width=610px; align=\"center\" border=0 cellspacing=0 cellpadding=0 bordercolor = #CCCCCC><tr><td style=\"padding-top:5px;\">");
                            ps.println("<table width=100% border=0  cellspacing=2 cellpadding=3 bordercolor = #CCCCCC>");
                            ps.println("<tr>");

                            ps.println("<td align=center width=10% class=heading1>");
                            ps.println("#");
                            ps.println("</td>");

                            ps.println("<td  align=left width=15% class=heading1>");
                            ps.println(ComUtils.getLanguageTranslation("label.catalogue.partNo", session).toUpperCase());
                            ps.println("</td>");

                            ps.println("<td align=left width=65% class=heading1>");
                            ps.println(ComUtils.getLanguageTranslation("label.catalogue.description", session).toUpperCase());
                            ps.println("</td>");

                            ps.println("<td  align = center  width=10% class=heading1>");
                            ps.println(ComUtils.getLanguageTranslation("label.catalogue.quantity", session).toUpperCase());
                            ps.println("</td>");
                            ps.println("	</tr>");
                            //  ps.println("	</table>");
                            //  ps.println("</td>");
                            //   ps.println("	</tr>");
                            //  ps.println("	<tr>");
                            //  ps.println("	<td>");
                            //  ps.println("<div style=\"height: 90px;  overflow: auto; \"> ");//380//background-color: pink;//border: 1px solid black;
                            //ps.println("<div class=\"maindiv\" style=\"overflow-X:hidden; overflow-Y: scroll; height:380;\">");//380

                            //  ps.println(" <table width=100%;   border=0  cellspacing=2 cellpadding=3 bordercolor = #CCCCCC >");

                        }

                        //println("    <tr> ");
                        //ps.println("    <td colspan=\"4\"> ");


                        ps.println("    <tr>                       ");
                        ps.println("              ");


                        //ps.println("<tr>");

                        if (l % 2 == 0) {
                            cssClass = "links_txt";//links_txt_Alt
                        } else {
                            cssClass = "links_txt";
                        }
                        l++;

                        ps.println("<td align=center width=10% class=" + cssClass + ">");
                        ps.println(counter);
                        ps.println("</td>");

                        partNo_temp = rs.getString(1);

                        if (partNo_temp.startsWith("TBA")) {
                            tempPartNo = PageTemplate.tbaPart;
                        } else {
                            tempPartNo = partNo_temp.replaceAll("_", " ");
                        }


                        ps.println("<td  width=15% align=left class=" + cssClass + ">");
                        // ps.println("<a href='" + servletURL + "PartDetails?partNo=" + partNo_temp + "'>");
                        ps.println(tempPartNo);
                        //  ps.println("</a>");
                        ps.println("</td>");

                        ps.println("<td  width=65% align=left class=" + cssClass + ">");
                        // ps.println("<a href='" + servletURL + "PartDetails?partNo=" + partNo_temp + "'>");
                        ps.println(rs.getString(2));
                        //  ps.println("</a>");
                        ps.println("</td>");

                        ps.println("<td  width=10% align=center class=" + cssClass + ">");
                        ps.println(rs.getString(3));
                        ps.println("</td>");
                        ps.println("	</tr>");

                        counter++;
                    }
                    ps.println("</table>");
                    //   ps.println("</div>");
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table>");

                    ps.println(object_pageTemplate.tableFooter());
                    rs.close();
                }
                //BOM OF KIT END


                if (partType.equalsIgnoreCase("ASM")) {
                    GroupBom ob = new GroupBom(conn, ps, date_OR_serial, inputDate, serialNo, partNo, buckleDate);
                    GroupBom ob2 = ob.get_assyBOM(ps, partNo, 0);

                    String[][] grp_arr = new String[ob2.print_data_counter][14];
                    int grp_arr_count = ob2.print_data_counter;

                    String var_REF_NO = "";
                    String var_DESCRIPTION = "";
                    String var_SERVICEABLE = "";
                    String var_REMARK = "";

                    String var_HEADER_DESCRIPTION = "";

                    for (int i = 0; i < grp_arr_count; i++) {
                        var_REF_NO = "";
                        var_DESCRIPTION = "";
                        var_SERVICEABLE = "";
                        var_REMARK = "";
                        var_HEADER_DESCRIPTION = "";

                        for (int aa = 0; aa < 9; aa++) {
                            grp_arr[i][aa] = ob2.print_data[i][aa];
                        }

                        if (grp_arr[i][4].equals("ASM")) {
                           // rs = stmt.executeQuery("SELECT P1,P2,p4,P3 FROM ASSY_DETAIL WHERE ASSY_NO ='" + grp_arr[i][0].replaceAll("&nbsp;", "") + "'");
                            String query11 = ("SELECT P1,P2,p4,P3 FROM ASSY_DETAIL(NOLOCK) WHERE ASSY_NO ='" + grp_arr[i][0].replaceAll("&nbsp;", "") + "'");
                            stmt = conn.prepareStatement(query11);
                            rs = stmt.executeQuery();
                            if (rs.next()) {
                                var_DESCRIPTION = rs.getString(1);
                                var_REF_NO = rs.getString(2);
                                var_REMARK = rs.getString(3);
                                var_SERVICEABLE = rs.getString(4);
                                var_HEADER_DESCRIPTION = var_DESCRIPTION;
                            }
                            rs.close();
                        } else {
                            //rs = stmt.executeQuery("SELECT p1,p2,P3,p4,p5 FROM CAT_PART WHERE part_no ='" + grp_arr[i][0].replaceAll("&nbsp;", "") + "'");
                            String query2 = ("SELECT P1,P2,p4,P3 FROM ASSY_DETAIL(NOLOCK) WHERE ASSY_NO ='" + grp_arr[i][0].replaceAll("&nbsp;", "") + "'");
                            stmt = conn.prepareStatement(query2);
                            rs = stmt.executeQuery();
                        	if (rs.next()) {
                                var_DESCRIPTION = rs.getString(1);
                                var_REF_NO = rs.getString(2);
                                var_REMARK = rs.getString(3);
                                var_SERVICEABLE = rs.getString(4);
                                var_HEADER_DESCRIPTION = rs.getString(5);
                            }
                            rs.close();
                        }

                        if (var_DESCRIPTION == null) {
                            var_DESCRIPTION = "";
                        }

                        grp_arr[i][9] = var_REF_NO;
                        grp_arr[i][10] = var_DESCRIPTION.toUpperCase();

                        grp_arr[i][11] = var_SERVICEABLE;
                        grp_arr[i][12] = var_REMARK;
                        grp_arr[i][13] = var_HEADER_DESCRIPTION;
                    }

                    for (int i = 0; i < grp_arr_count; i++) {
                        for (int aa = 0; aa < 14; aa++) {
                            if (grp_arr[i][aa] == null) {
                                grp_arr[i][aa] = " &nbsp;";
                            }
                        }
                    }

                    // BOM GENERATION OF ASSY ENDS

                    if (grp_arr_count > 0) {
                        if (partNo.startsWith("TBA")) {
                            ps.println(object_pageTemplate.tableHeader(ComUtils.getLanguageTranslation("label.catalogue.bomOfPartNo", session) + " \"-\"", width, height));
                        } else {
                            ps.println(object_pageTemplate.tableHeader(ComUtils.getLanguageTranslation("label.catalogue.bomOfPartNo", session) + " \"" + partNo + "\"", width, height));
                        }

                        //ps.println(" <table width=100% border=0 cellspacing=0 cellpadding=0 bordercolor = #CCCCCC> <tr><td align=left  width=100%>");
                        ps.println("<table width=610px; border=0 cellspacing=0 cellpadding=0 bordercolor = #CCCCCC><tr><td style=\"padding-top:5px;\">");
                        ps.println("<table width=610px;  border=0 cellspacing=2 cellpadding=3 bordercolor = #CCCCCC>");

                        ps.println("<tr>");
                        ps.println("<td width=25% align=left class=heading1>");
                        ps.println(ComUtils.getLanguageTranslation("label.catalogue.partNo", session));
                        ps.println("</td>");

                        ps.println("<td width=40% align=left class=heading1>");
                        ps.println(ComUtils.getLanguageTranslation("label.catalogue.partName", session));
                        ps.println("</td>");

                        ps.println("<td width=15% align =center class=heading1>");
                        ps.println(ComUtils.getLanguageTranslation("label.catalogue.qty", session));
                        ps.println("</td>");

                        ps.println("<td width=20% align=left class=heading1>");
                        ps.println(ComUtils.getLanguageTranslation("label.catalogue.remarks", session));
                        ps.println("</td>");

                        ps.println("	</tr>");
                        ps.println("	</table> ");
                        ps.println("</td>");
                        ps.println("	</tr>");
                        ps.println("	<tr>");
                        ps.println("	<td>");
                        ps.println("<div style=\"  height: 90px;  overflow: auto; \"> ");//380//background-color: pink;//border: 1px solid black;
                        //ps.println("<div class=\"maindiv\" style=\"overflow-X:hidden; overflow-Y: scroll; height:380;\">");//380

                        ps.println(" <table width=100%;   border=0 cellspacing=2 cellpadding=3 bordercolor = #CCCCCC >");
                        // ps.println("    <tr>                       ");

                        //<table cellspacing=1 cellpadding=2 border=0 width=98% bordercolor = #000000>
//border=1 cellspacing=0 cellpadding=3 bordercolor = #CCCCCC
                        //    ps.println("</td></tr><tr><td align=left width=100%><div valign=top STYLE=\" overflow-X:hidden; overflow-y: scroll; height:150;\">");

                        // ps.println("                 <table width=99% border=1 cellspacing=0 cellpadding=3 bordercolor = #CCCCCC>");
                        ps.println("              ");


                        String tempParam = "";
                        Vector superVec = new Vector();
                        Get_groupInfo ob_get_grp_inf = new Get_groupInfo();

                        l = 0;

                        for (int i = 0; i < grp_arr_count; i++) {
                            tempParam = grp_arr[i][0].replaceAll("&nbsp;", "");


                            if (l % 2 == 0) {
                                cssClass = "links_txt_left"; //links_txt_left_Alt
                            } else {
                                cssClass = "links_txt_left";
                            }
                            l++;

                            ps.println("<tr >");

                            ps.println("<td align=left width = 25% class=" + cssClass + ">");

                            if (linksReq == null) {
                                ps.println("<a href='" + servletURL + "PartDetails?partNo=" + tempParam + "'>");
                            }

                            if (tempParam.startsWith("TBA")) {
                                ps.println(PageTemplate.tbaPart);
                            } else {
                                ps.println(grp_arr[i][0].replaceAll("_", "&nbsp;"));
                            }

                            ps.println("</a></td>");

                            ps.println("<td width = 40% align=left class=" + cssClass + ">");
                            ps.println(grp_arr[i][10]);
                            ps.println("</td>");

                            ps.println("<td align=left width = 15% class=" + cssClass + ">");
                            ps.println(grp_arr[i][3]);
                            ps.println("</td>");

                            ps.println("<td align=left width = 20% class=" + cssClass + ">");
                            ps.println("&nbsp;");
                            ps.println("</td>");
                            ps.println("</tr>");

                            // Print Part Deletion history in the asm
                            if (grp_arr[i][4].equals("ASM") && date_OR_serial.equals("latest")) {
                                superVec = new Vector();
                                superVec = ob_get_grp_inf.getPartDeleteHistory(true, holder.getConnection(), "", grp_arr[i][0].replaceAll("_", "&nbsp;"), superVec, "", new java.sql.Date(0));
                                //    System.out.println("superVec" + superVec);
                                String partAssy = "";
                                if (superVec != null) {
                                    String printText = "";
                                    String linkPart = "";
                                    String spaceStr = grp_arr[i][0].substring(0, grp_arr[i][0].lastIndexOf(";") + 1) + "&nbsp;&nbsp;&nbsp;";
                                    for (int m = 0; m < superVec.size(); m = m + 6) {
                                        printText = "";
                                        partAssy = "" + superVec.elementAt(m);
                                        linkPart = "" + superVec.elementAt(m + 1);

                                        if (linksReq == null) {
                                            printText = printText + "<a href='" + servletURL + "PartDetails?partNo=" + linkPart + "'>";
                                        }

                                        if (l % 2 == 0) {
                                            cssClass = "links_txt_left";//links_txt_left_Alt
                                        } else {
                                            cssClass = "links_txt_left";
                                        }
                                        l++;
                                        ps.println("<tr >");

                                        ps.println("<td width=25%  style=\"word-wrap: break-word\"  class=" + cssClass + " >");
                                        ps.println("" + printText);

                                        if (linkPart.startsWith("TBA")) {
                                            ps.println("" + PageTemplate.tbaPart);
                                        } else {
                                            ps.println("" + spaceStr + linkPart);
                                        }
                                        if (linksReq == null) {
                                            ps.println("</a>");
                                        }
                                        ps.println("</td>");
                                        ps.println("<td width=40%  style=\"word-wrap:break-word\"  class=" + cssClass + ">");
                                        ps.println(("" + superVec.elementAt(m + 2)).toUpperCase());
                                        ps.println("</td>");

                                        ps.println("<td width=15%  style=\"word-wrap:break-word\"  class=" + cssClass + " >");
                                        ps.println("&nbsp;");
                                        ps.println("</td>");

                                        ps.println("<td  width=20%  style=\"word-wrap:break-word\"  class=" + cssClass + ">");
                                        // ps.println("Till Date: " + superVec.elementAt(m + 3));
                                        ps.println("Till TSN: X" + superVec.elementAt(m + 5));
                                        ps.println("</td>");

                                        ps.println("</tr>");
                                    }
                                }
                            }

                            if (date_OR_serial.equals("latest")) {
                                superVec = new Vector();
                                superVec = ob_get_grp_inf.getPartHistory(true, holder.getConnection(), grp_arr[i][0].replaceAll("_", "&nbsp;"), grp_arr[i][6], superVec, "", new java.sql.Date(0));
                                if (superVec != null) {
                                    String spaceStr = grp_arr[i][0].substring(0, grp_arr[i][0].lastIndexOf(";") + 1);
                                    String printText = "";
                                    String partAssy = "";
                                    String linkPart = "";

                                    for (int m = 0; m < superVec.size(); m = m + 6) {
                                        printText = "";
                                        partAssy = "" + superVec.elementAt(m);
                                        linkPart = "" + superVec.elementAt(m + 1);

                                        if (linksReq == null) {
                                            printText = printText + "<a href='" + servletURL + "PartDetails?partNo=" + linkPart + "'>";
                                        }

                                        if (l % 2 == 0) {
                                            cssClass = "links_txt_left";//links_txt_left_Alt
                                        } else {
                                            cssClass = "links_txt_left";
                                        }
                                        l++;

                                        ps.println("<tr >");

                                        ps.println("<td width=25%  style=\"word-wrap: break-word\"    class=" + cssClass + ">");
                                        ps.println("" + printText);


                                        if (linkPart.startsWith("TBA")) {
                                            ps.println("" + PageTemplate.tbaPart);
                                        } else {
                                            ps.println("" + spaceStr + linkPart);
                                        }

                                        //ps.println("" + spaceStr + linkPart);
                                        if (linksReq == null) {
                                            ps.println("</a>");
                                        }
                                        ps.println("</td>");
                                        ps.println("<td width=40%  style=\"word-wrap:break-word\"   class=" + cssClass + ">");
                                        ps.println(("" + superVec.elementAt(m + 2)).toUpperCase());
                                        ps.println("</td>");

                                        ps.println("<td width=15%  style=\"word-wrap:break-word\"    class=" + cssClass + ">");
                                        ps.println("&nbsp;");
                                        ps.println("</td>");

                                        ps.println("<td  width=20%  style=\"word-wrap:break-word\"   class=" + cssClass + ">");
                                        // ps.println("Till Date: " + superVec.elementAt(m + 3));
                                        ps.println(ComUtils.getLanguageTranslation("label.catalogue.tillTSN", session) + superVec.elementAt(m + 5));
                                        ps.println("</td>");

                                        ps.println("</tr>");
                                    }
                                }
                            }
                        }
                        ps.println("</table>");
                        ps.println("</div>");
                        ps.println("</td>");
                        ps.println("</tr>");
                        ps.println("</table>");
//Footer

                        ps.println(object_pageTemplate.tableFooter());
                    }


                }


                //////////////////////////////////////////////////////////////
                String temp_1 = "";

                if (checkPromoItem == 0) {
                    if (date_OR_serial.equals("latest")) {
                        int tableWidth = 0;

                        tableWidth = 660;
                        java.sql.Date viewDate = new java.sql.Date((new java.util.Date()).getTime());

                        if (date_OR_serial == null) {
                            date_OR_serial = "latest";
                        } else if (date_OR_serial.equals("model_date")) {
                            viewDate = inputDate;
                        } else if (date_OR_serial.equals("serialNo")) {
                            viewDate = buckleDate;
                        }


                        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

                        CompWhereUsedReport ob1 = new CompWhereUsedReport(conn);
                        CompWhereUsedReport ob3 = ob1.getTotalModels(partNo, viewDate, productFamilySubQuery, applicationTypSubQuery);

                        int modelsCOUNT = ob3.print_data_counter;

                        for (int j = 0; j < modelsCOUNT; j++) {
                            int totLen = ob3.print_data[j].length();
                            temp = ob3.print_data[j].substring(totLen - 1, totLen);
                            temp_1 = ob3.print_data[j].substring(0, totLen - 4);

                            if (temp.equals("A")) {
                                if (allASSY.indexOf(temp_1) == -1) {
                                    allASSY.addElement(temp_1);
                                }
                            }

                            if (temp.equals("K")) {
                                if (allSKITS.indexOf(temp_1) == -1) {
                                    allSKITS.addElement(temp_1);
                                }
                            }

                            if (temp.equals("G")) {
                                if (allGROUPS.indexOf(temp_1) == -1) {
                                    allGROUPS.addElement(temp_1);
                                }
                            }
                        }

                        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

                        if (allGROUPS.size() > 0) {


                            //ps.println("	</tr>");

                            String[] stringsArr = (String[]) allGROUPS.toArray(new String[allGROUPS.size()]);
                            Arrays.sort(stringsArr);

                            int sNo = 1;
                            String temp_group = "";
                            Vector allMODELS = new Vector();
                            String tempHeading = "";
                            String modelNo = "";
                            String modelDesc = "";


                            if (productFamilySubQuery.equals("")) {
                                subQuery = " WHERE MC.MODEL_NO=M.MODEL_NO " + applicationTypSubQuery;
                            } else {
                                subQuery = productFamilySubQuery + " AND MC.MODEL_NO=M.MODEL_NO " + applicationTypSubQuery;
                            }

                            l = 0;
                            for (int i = 0; i < allGROUPS.size(); i++) {


                                temp_group = "" + stringsArr[i];
                                allMODELS = new Vector();

                                if (userFunctionalities.contains("36")) {
                                    //rs = stmt.executeQuery("SELECT MGM.MAP_NAME, MGM.MODEL_NO, M.MODEL_NO,mc.ENGINE_SERIES,mc.ENGINE_MODEL FROM CAT_MODEL_GROUP MGM(NOLOCK), CAT_MODELS(NOLOCK) M,CAT_MODEL_CLASSIFICATION(NOLOCK) MC " + subQuery + "  AND M.MODEL_NO= MGM.MODEL_NO AND M.STATUS='COMPLETE' AND MGM.GROUP_NO = '" + temp_group + "' order by MGM.MODEL_NO");
                               String query = ("SELECT MGM.MAP_NAME, MGM.MODEL_NO, M.MODEL_NO,mc.ENGINE_SERIES,mc.ENGINE_MODEL FROM CAT_MODEL_GROUP MGM(NOLOCK), CAT_MODELS(NOLOCK) M,CAT_MODEL_CLASSIFICATION(NOLOCK) MC " + subQuery + "  AND M.MODEL_NO= MGM.MODEL_NO AND M.STATUS='COMPLETE' AND MGM.GROUP_NO = '" + temp_group + "' order by MGM.MODEL_NO");
                                stmt = conn.prepareStatement(query);
                                rs = stmt.executeQuery();
                                } else {
                                    //rs = stmt.executeQuery("SELECT MGM.MAP_NAME, MGM.MODEL_NO, M.MODEL_NO,mc.ENGINE_SERIES,mc.ENGINE_MODEL FROM CAT_MODEL_GROUP MGM(NOLOCK), CAT_MODELS(NOLOCK) M,CAT_MODEL_CLASSIFICATION(NOLOCK) MC " + subQuery + "  AND M.MODEL_NO= MGM.MODEL_NO AND MGM.GROUP_NO = '" + temp_group + "' order by MGM.MODEL_NO");
                                String query = ("SELECT MGM.MAP_NAME, MGM.MODEL_NO, M.MODEL_NO,mc.ENGINE_SERIES,mc.ENGINE_MODEL FROM CAT_MODEL_GROUP MGM(NOLOCK), CAT_MODELS(NOLOCK) M,CAT_MODEL_CLASSIFICATION(NOLOCK) MC " + subQuery + "  AND M.MODEL_NO= MGM.MODEL_NO AND MGM.GROUP_NO = '" + temp_group + "' order by MGM.MODEL_NO");
                                stmt = conn.prepareStatement(query);
                                rs = stmt.executeQuery();
                                }

                                if (rs.next()) {

                                    do {
                                        tempHeading = rs.getString(1);
                                        allMODELS.addElement(rs.getString(2));
                                        allMODELS.addElement(rs.getString(4));
                                        allMODELS.addElement(rs.getString(5));
                                    } while (rs.next());
                                }
                                rs.close();

                                if (l % 2 == 0) {
                                    cssClass = "links_txt_left";//links_txt_left_Alt
                                } else {
                                    cssClass = "links_txt_left";
                                }


                                if (allMODELS.size() > 0) {
                                    l++;
                                    if (sNo == 1) {
                                        if (partNo.startsWith("TBA")) {
                                            ps.println(object_pageTemplate.tableHeader(ComUtils.getLanguageTranslation("label.catalogue.applicableVariantGroup", session) + " \"-\"", width, height));
                                        } else {
                                            ps.println(object_pageTemplate.tableHeader(ComUtils.getLanguageTranslation("label.catalogue.applicableVariantGroup", session) + " \"" + partNo.replaceAll("_", "&nbsp;") + "\"", width, height));
                                        }

                                        ps.println("<table width=610px;   border=0 cellspacing=0 cellpadding=0 bordercolor = #CCCCCC><tr><td style=\"padding-top:5px;\">");
                                        ps.println("<table width=610px;  border=0 cellspacing=2 cellpadding=3 bordercolor = #CCCCCC>");
                                        ps.println("<tr>");
                                        ps.println("<td  align=center width = 10% class=heading1>");
                                        ps.println(ComUtils.getLanguageTranslation("label.catalogue.sNo", session).toUpperCase());

                                        ps.println("</td> ");

                                        ps.println("<td align=left  width = 60% class=heading1>");
                                        ps.println(ComUtils.getLanguageTranslation("label.catalogue.variant", session).toUpperCase() + " NO");
                                        ps.println("</td> ");

                                        ps.println("<td align=left width = 30% class=heading1>");
                                        ps.println(ComUtils.getLanguageTranslation("label.catalogue.group", session));
                                        ps.println("</td> ");


                                        ps.println("	</tr>");
                                        ps.println("	</table></td></tr><tr><td>");
                                        ps.println("<div style=\"  height: 90px;  overflow: auto; \"> ");//380//background-color: pink;//border: 1px solid black;
                                        // ps.println("<div class=\"maindiv\" style=\"overflow-X:hidden; overflow-Y: scroll; height:380;\">");//380

                                        ps.println(" <table width=100%   border=0 cellspacing=2 cellpadding=3 bordercolor = #CCCCCC>");
                                        //ps.println("    <tr>                       ");


                                    }

                                    ps.println("<tr>");
                                    ps.println("<td align=center width = 10% valign = top class=" + cssClass + ">");
                                    ps.println(sNo);
                                    ps.println("</td> ");

                                    ps.println("<td align=left width = 60% valign = top class=" + cssClass + ">");

                                    for (int j = 0; j < allMODELS.size(); j += 3) {
                                        modelNo = "" + allMODELS.elementAt(j);

                                       // rs = stmt.executeQuery("SELECT DESCRIPTION FROM CAT_MODELS(NOLOCK) WHERE MODEL_NO = '" + modelNo + "'");
                                       String sql =  ("SELECT DESCRIPTION FROM CAT_MODELS(NOLOCK) WHERE MODEL_NO = '" + modelNo + "'");
                                       stmt = conn.prepareStatement(sql);
                                       rs = stmt.executeQuery();
                                        if (rs.next()) {
                                            modelDesc = rs.getString(1);
                                        }
                                        rs.close();

                                        if (j != 0) {
                                            ps.println("<br>");
                                        }

                                        if (linksReq == null) {
                                            ps.println("<a href=\"ViewModel?engineModel=" + allMODELS.elementAt(j + 2) + "&engineSeries=" + allMODELS.elementAt(j + 1) + "&model=" + modelNo + "&highGroup=" + temp_group + "\" target = right>");
                                        }
                                        ps.println(modelDesc + " [ " + modelNo + " ] ");
                                        if (linksReq == null) {
                                            ps.println("</a>");
                                        }
                                    }
                                    ps.println("</td> ");

                                    ps.println("<td valign=top width = 30% align = left class=" + cssClass + ">");
                                    if (linksReq == null) {
                                        ps.println("<a target=\"right\" href=\"" + servletURL + "ViewGroupImage_BOM?engineModel=" + allMODELS.elementAt(2) + "&engineSeries=" + allMODELS.elementAt(1) + "&group=" + temp_group + "&model=" + allMODELS.elementAt(0) + "&partSearch=" + partNo + "\">");
//                                        ps.println("<a href=javascript:MM_openBrWindow('" + servletURL + "ViewGroupImage_BOM?group=" + temp_group + "&model=" + allMODELS.elementAt(0) + "&partSearch=" + partNo + "','IMAGEBOM','resizable=no,scrollbars=yes,width=1010,height=700')>");
                                    }
                                   // rs = stmt.executeQuery("SELECT p1 FROM CAT_GROUP_KIT_DETAIL(NOLOCK) WHERE grp_kit_no = '" + temp_group + "'");
                                    String sqlQuery = ("SELECT p1 FROM CAT_GROUP_KIT_DETAIL(NOLOCK) WHERE grp_kit_no = '" + temp_group + "'");
                                    stmt = conn.prepareStatement(sqlQuery);
                                    rs = stmt.executeQuery();
                                    if (rs.next()) {

                                        //*********** modified by apurv**************

//                                        tempHeading = tempHeading + " [ " + rs.getString(1) + " ]";
                                        tempHeading = rs.getString(1);

                                        //*******************************************
                                    }
                                    rs.close();
                                    ps.println("&nbsp;" + tempHeading);
                                    if (linksReq == null) {
                                        ps.println("</a>");
                                    }
                                    ps.println("</td> ");
                                    ps.println("	</tr>");
                                    sNo++;
                                }
                            }
                            if (sNo > 1) {
                                ps.println("</table>");
                                ps.println("</div></td></tr></table>");
                                ps.println(object_pageTemplate.tableFooter());
                            }
                        }
                        if (allSKITS.size() > 0)////kit start
                        {
                            if (partNo.startsWith("TBA")) {
                                ps.println(object_pageTemplate.tableHeader(ComUtils.getLanguageTranslation("label.catalogue.applicableKits", session) + "  \"-\"", width, height));
                            } else {
                                ps.println(object_pageTemplate.tableHeader(ComUtils.getLanguageTranslation("label.catalogue.applicableKits", session) + " \"" + partNo.replaceAll("_", "&nbsp;") + "\"", width, height));
                            }

                            ps.println("<table width=610px; border=0 cellspacing=0 cellpadding=0 bordercolor = #CCCCCC><tr><td style=\"padding-top:5px;\">");
                            ps.println("<table width=610px; border=0 cellspacing=2 cellpadding=3 bordercolor = #CCCCCC>");
                            ps.println("<tr>");
                            ps.println("<td  align=center width = 10% class=heading1>");
                            ps.println(ComUtils.getLanguageTranslation("label.catalogue.sNo", session));
                            ps.println("</td> ");

                            ps.println("<td   align=left width = 15% class=heading1>");
                            ps.println(ComUtils.getLanguageTranslation("label.catalogue.kitNo", session).toUpperCase());
                            ps.println("</td> ");

                            ps.println("<td  align=left width = 75% class=heading1>");
                            ps.println(ComUtils.getLanguageTranslation("label.catalogue.kitDescription", session).toUpperCase());
                            ps.println("</td> ");
                            ps.println("	</tr>");
                            ps.println("	</table>");
                            ps.println("</td>");
                            ps.println("	</tr>");
                            ps.println("	<tr>");
                            ps.println("	<td>");
                            String kitNo = "";
                            String query = "";
                            ps.println("<div style=\"  height: 90px;  overflow: auto; \"> ");//380//background-color: pink;//border: 1px solid black;
                            //ps.println("<div class=\"maindiv\" style=\"overflow-X:hidden; overflow-Y: scroll; height:380;\">");//380

                            ps.println(" <table width=100%;   border=0 cellspacing=2 cellpadding=3 bordercolor = #CCCCCC >");
                            //ps.println("    <tr>                       ");

                            for (int i = 0; i < allSKITS.size(); i++) {
                                kitNo = "" + allSKITS.elementAt(i);

                                if (i % 2 == 0) {
                                    cssClass = "links_txt_left";//links_txt_left_Alt
                                } else {
                                    cssClass = "links_txt_left";
                                }


                                ps.println("<tr>");
                                ps.println("<td align=center width = 10% class=" + cssClass + ">");
                                ps.println(i + 1);
                                ps.println("</td> ");

                                ps.println("<td valign=top align=left width = 15% class=" + cssClass + ">");
                                if (linksReq == null) {
                                    ps.println("<a href='" + servletURL + "PartDetails?partNo=" + kitNo + "'>");
                                }

                                if (kitNo.startsWith("TBA")) {
                                    ps.println(PageTemplate.tbaPart);
                                } else {
                                    ps.println(kitNo.replaceAll("_", " "));
                                }

                                if (linksReq == null) {
                                    ps.println("</a>");
                                }
                                ps.println("</td> ");
                                ps.println("<td valign=top width = 75% align=left class=" + cssClass + ">");
                                if (linksReq == null) {
                                    ps.println("<a href='" + servletURL + "PartDetails?partNo=" + kitNo + "'>");
                                }

                               // rs = stmt.executeQuery("SELECT p1 FROM CAT_PART(NOLOCK) WHERE part_no='" + kitNo + "'");
                                String sqlQuery = ("SELECT p1 FROM CAT_PART(NOLOCK) WHERE part_no='" + kitNo + "'");
                                stmt = conn.prepareStatement(sqlQuery);
                                rs = stmt.executeQuery();
                                if (rs.next()) {
                                    ps.println(rs.getString(1));
                                } else {
                                    ps.println("-");
                                }
                                rs.close();
                                if (linksReq == null) {
                                    ps.println("</a>");
                                }
                                ps.println("</td> ");
                                ps.println("	</tr>");

                            }

                            ps.println("</table>");
                            ps.println("</div>");
                            ps.println("</td>");
                            ps.println("</tr>");
                            ps.println("</table>");
                            ps.println(object_pageTemplate.tableFooter());
                        } //kit end


                        //APPLICABLE MODELS FOR KIT,LUBE,TOOL

                        counter = 1;
                        l = 0;

                        if (partType.equalsIgnoreCase("KIT") || partType.equalsIgnoreCase("LUBE") || partType.equalsIgnoreCase("TOOL")) {
                            //kitBomDetail=new ArrayList();
                           // rs = stmt.executeQuery("select distinct pmm.ENGINE_MODEL,engine_series from CAT_MODEL_CLASSIFICATION(NOLOCK) mc,CAT_PART_MODEL_MATRIX(NOLOCK) pmm where pmm.ENGINE_MODEL=MC.ENGINE_MODEL and part_no='" + partNo + "' order by pmm.ENGINE_MODEL");
                        	String sqlQuery1 = ("select distinct pmm.ENGINE_MODEL,engine_series from CAT_MODEL_CLASSIFICATION(NOLOCK) mc,CAT_PART_MODEL_MATRIX(NOLOCK) pmm where pmm.ENGINE_MODEL=MC.ENGINE_MODEL and part_no='" + partNo + "' order by pmm.ENGINE_MODEL");
                        	stmt = conn.prepareStatement(sqlQuery1);
                        	rs = stmt.executeQuery();

                            while (rs.next()) {
                                if (counter == 1) {
                                    ps.println(object_pageTemplate.tableHeader(ComUtils.getLanguageTranslation("label.catalogue.applicableVariant", session) + " \"" + partNo + "\"", width, height));

                                    ps.println("<table width=610px; border=0 cellspacing=0 cellpadding=0 bordercolor = #CCCCCC><tr><td style=\"padding-top:5px;\">");
                                    ps.println("<table width=610px; border=0 cellspacing=2 cellpadding=3 bordercolor = #CCCCCC>");
                                    ps.println("<tr>");

                                    ps.println("<td  align=center width=10% class=heading1>");
                                    ps.println("#");
                                    ps.println("</td>");

                                    ps.println("<td  width=90% align=left class=heading1>");
                                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.model", session).toUpperCase() + "S");
                                    ps.println("</td>");

                                    ps.println("	</tr>");
                                    ps.println("	</table>");
                                    ps.println("</td>");
                                    ps.println("	</tr>");
                                    ps.println("<tr>");
                                    ps.println("	<td>");
                                    ps.println("<div style=\"  height: 100px;  overflow: auto; \"> ");//380//background-color: pink;//border: 1px solid black;
                                    //ps.println("<div class=\"maindiv\" style=\"overflow-X:hidden; overflow-Y: scroll; height:380;\">");//380

                                    ps.println(" <table width=100%;   border=0 cellspacing=2 cellpadding=3 bordercolor = #CCCCCC >");
                                    //ps.println("    <tr>                       ");




                                }

                                if (l % 2 == 0) {
                                    cssClass = "links_txt";//links_txt_Alt
                                } else {
                                    cssClass = "links_txt";
                                }

                                l++;


                                ps.println("<tr>");

                                ps.println("<td  width=10% align=center class=" + cssClass + ">");
                                ps.println(counter);
                                ps.println("</td>");

                                engineModel = rs.getString(1);

                                ps.println("<td   width=90% align=left class=" + cssClass + ">");
                                ps.println("<a  href=\"MainModels?engineModel=" + engineModel + "&engineSeries=" + rs.getString(2) + "\" target=right>");
                                ps.println(engineModel);
                                ps.println("</a");
                                ps.println("</td");
                                ps.println("</tr>");

                                counter++;
                            }
                            if (counter > 1) {
                                ps.println("</table>");
                                ps.println("</div>");
                                ps.println("</td></tr>");
                                ps.println("</table>");
                                ps.println(object_pageTemplate.tableFooter());
                            }
                        }
                        //APPLICABLE MODELS FOR KIT.LUBE,TOOL
                        engineModel = null;


                        if (allSKITS.size() == 0 && allGROUPS.size() == 0 && (partType.equalsIgnoreCase("PRT") || partType.equalsIgnoreCase("ASM"))) {
                            message = ComUtils.getLanguageTranslation("label.catalogue.unusedPart", session);
                            ps.println(object_pageTemplate.tableHeader1(message, width, height));
                        }

                        //@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@

                        //^^^^^^^^^^^^^^^^^^^^^^^^^^^ CODE TO GET PRECEDED & SUPERCEDED PARTS STARTS ^^^^^^^^^^^^^^^^^^^^^^^^^^
                        ps.println("	<br>");

//                        ps.println("<table width=" + tableWidth + " border=0 cellspacing=0 cellpadding=3 bordercolor = #CCCCCC>");
//
//                        ps.println("	<tr>");
                        //^^^^^^^^^^^^^^^^^^^^^^^^^^^ CODE TO GET REPLACED PARTS STARTS ^^^^^^^^^^^^^^^^^^^^^^^^^^
                        tempPartNo = "";
                        // String temp_revisionNo = "";
                        // DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
                        DateFormat df = new SimpleDateFormat("dd-MMM-yyyy");


                        //rs = stmt.executeQuery("SELECT replaced_comp,revision,changed_group_engine FROM change_mgmt WHERE component = '" + partNo + "' AND change_type='Replaced' ORDER BY revision");
                        //rs = stmt.executeQuery("SELECT distinct NEW_PART, OLD_PART, EFFECTIVE_DATE, EFFECTIVE_TSN, STATUS FROM CAT_ECN_IMPL_HISTORY(NOLOCK) WHERE NEW_PART = '" + partNo + "' or OLD_PART='" + partNo + "' ORDER BY EFFECTIVE_DATE desc");
                        String sqlQuery = ("SELECT distinct NEW_PART, OLD_PART, EFFECTIVE_DATE, EFFECTIVE_TSN, STATUS FROM CAT_ECN_IMPL_HISTORY(NOLOCK) WHERE NEW_PART = '" + partNo + "' or OLD_PART='" + partNo + "' ORDER BY EFFECTIVE_DATE desc");
                        stmt = conn.prepareStatement(sqlQuery);
                        rs = stmt.executeQuery();
                        if (rs.next()) {
                            ps.println(object_pageTemplate.tableHeader("\"" + partNo.replaceAll("_", "&nbsp;") + "\"" + ComUtils.getLanguageTranslation("label.catalogue.changeHistory", session), width, height));

                            ps.println("<table width=610px; border=0 cellspacing=0 cellpadding=0 bordercolor = #CCCCCC><tr><td style=\"padding-top:5px;\">");
                            ps.println("<table width=610px; border=0 cellspacing=2 cellpadding=3 bordercolor = #CCCCCC>");
                            ps.println("<tr>");
                            ps.println("<td  align=center width = 20% class=heading1>");
                            ps.println(ComUtils.getLanguageTranslation("label.catalogue.newComponent", session));
                            ps.println("</td> ");

                            ps.println("<td  align=center width = 20% class=heading1>");
                            ps.println(ComUtils.getLanguageTranslation("label.catalogue.oldComponent", session));
                            ps.println("</td> ");

                            ps.println("<td  align=center width = 20% class=heading1>");
                            ps.println(ComUtils.getLanguageTranslation("label.catalogue.interchange", session));
                            ps.println("</td> ");


                            ps.println("<td   align=left width = 20% class=heading1>");
                            ps.println(ComUtils.getLanguageTranslation("label.catalogue.fromTSN", session));
                            ps.println("</td> ");

                            ps.println("<td  align=left width = 20% class=heading1>");
                            ps.println(ComUtils.getLanguageTranslation("label.catalogue.effectiveFrom", session));
                            ps.println("</td> ");
                            ps.println("	</tr>");
                            ps.println("	</table>");
                            ps.println("</td>");
                            ps.println("	</tr>");
                            ps.println("<tr>");
                            ps.println("	<td>");

                            ps.println("<div style=\"0   height: 90px;  overflow: auto; \"> ");//380//background-color: pink;//border: 1px solid black;
                            //ps.println("<div class=\"maindiv\" style=\"overflow-X:hidden; overflow-Y: scroll; height:380;\">");//380

                            ps.println(" <table width=100%;   border=0 cellspacing=2 cellpadding=3 bordercolor = #CCCCCC >");

                            l = 0;
//
                            do {
                                tempPartNo = rs.getString(1);

                                if (l % 2 == 0) {
                                    cssClass = "links_txt";//links_txt_Alt
                                } else {
                                    cssClass = "links_txt";
                                }

                                l++;
                                ps.println("<tr>");
                                ps.println("<td class=" + cssClass + " width = 20%>");
                                if (linksReq == null) {
                                    ps.println("<a href='" + servletURL + "PartDetails?partNo=" + tempPartNo + "'>");
                                }

                                if (tempPartNo.startsWith("TBA")) {
                                    ps.println(PageTemplate.tbaPart);
                                } else {
                                    ps.println("" + tempPartNo);
                                }
                                if (linksReq == null) {
                                    ps.println("</a>");
                                }
                                ps.println("</td>");
                                tempPartNo = rs.getString(2);
                                ps.println("<td class=" + cssClass + " width = 20%>");
                                if (linksReq == null) {
                                    ps.println("<a href='" + servletURL + "PartDetails?partNo=" + tempPartNo + "'>");
                                }

                                if (tempPartNo.startsWith("TBA")) {
                                    ps.println(PageTemplate.tbaPart);
                                } else {
                                    ps.println("" + tempPartNo);
                                }
                                if (linksReq == null) {
                                    ps.println("</a>");
                                }
                                ps.println("</td>");
                                ps.println("<td class=" + cssClass + " width = 20%>");
                                ps.println("" + rs.getString(5));
                                ps.println("</td>");
                                ps.println("<td class=" + cssClass + " width = 20%>");
                                ps.println("" + rs.getString(4));
                                ps.println("</td>");
                                ps.println("<td class=" + cssClass + " width = 20%>");
                                ps.println("" + df.format(rs.getDate(3)));
                                ps.println("</td>");


                                ps.println("</tr>");

                            } while (rs.next());
                            ps.println("                        </table>");
                            ps.println("</div>");
                            ps.println("</td>");
                            ps.println("</tr>");
                            ps.println("</table>");
                            ps.println(object_pageTemplate.tableFooter());
                            //ps.println("</td> ");
                        }
                        rs.close();
                        //^^^^^^^^^^^^^^^^^^^^^^^^^^^ CODE TO GET REPLACED PARTS ENDS ^^^^^^^^^^^^^^^^^^^^^^^^^^


                        //^^^^^^^^^^^^^^^^^^^^^^^^^^^ CODE TO GET REPLACED BY PARTS STARTS ^^^^^^^^^^^^^^^^^^^^^^^^^^

                        // rs = stmt.executeQuery("SELECT component,revision,changed_group_engine FROM change_mgmt WHERE replaced_comp = '" + partNo + "' AND change_type='Replaced' ORDER BY revision");
                      /*  rs = stmt.executeQuery("SELECT distinct NEW_PART,EFFECTIVE_DATE, EFFECTIVE_TSN FROM CAT_ECN_IMPL_HISTORY WHERE OLD_PART = '" + partNo + "' AND change_type='Replaced' ORDER BY EFFECTIVE_DATE desc");
                        if (rs.next())
                        {
                        ps.println("<td width = 50% valign=top>");

                        // int firstCounter = 1;
                        ps.println(object_pageTemplate.tableHeader("\"" + partNo.replaceAll("_", "&nbsp;") + "\" Replaced By following", width / 2, height));
                        ps.println("<table valign=top width = 98% cellspacing=0 cellpadding=3 border = 0 bordercolor = #CCCCCC>");

                        ps.println("<tr>");

                        ps.println("<td width = 50% class=heading1>");
                        //ps.println("<b> REVISION NO");
                        ps.println("<b> FROM DATE");
                        ps.println("</td>");

                        ps.println("<td width = 50% class=heading1>");
                        ps.println("<b> PART NO");
                        ps.println("</td>");

                        ps.println("</tr>");

                        do
                        {
                        tempPartNo = rs.getString(1);
                        //temp_revisionNo = rs.getString(2);

                        ps.println("<tr>");

                        ps.println("<td class=links_txt>");

                        ps.println("" + df.format(rs.getDate(2)));
                        //ps.println("X" + rs.getInt(3));
                        ps.println("</td>");

                        ps.println("<td class=links_txt>");
                        if (linksReq == null)
                        {
                        ps.println("<a href='" + servletURL + "PartDetails?partNo=" + tempPartNo + "'>");
                        }

                        if (tempPartNo.startsWith("TBA"))
                        {
                        ps.println(PageTemplate.tbaPart);
                        }
                        else
                        {
                        ps.println("" + tempPartNo);
                        }

                        if (linksReq == null)
                        {
                        ps.println("</a>");
                        }
                        ps.println("</td>");

                        ps.println("</tr>");

                        // firstCounter++;
                        }
                        while (rs.next());
                        ps.println("</table>");
                        ps.println(object_pageTemplate.tableFooter());
                        ps.println("</td> ");
                        }
                        rs.close();
                         */ //^^^^^^^^^^^^^^^^^^^^^^^^^^^ CODE TO GET REPLACED BY PARTS ENDS ^^^^^^^^^^^^^^^^^^^^^^^^^^

//                        ps.println("	</tr>");
//
//                        ps.println("	</table>");

                        //^^^^^^^^^^^^^^^^^^^^^^^^^^^ CODE TO GET PRECEDED & SUPERCEDED PARTS ENDS ^^^^^^^^^^^^^^^^^^^^^^^^^^
                    }

                } else {
                    ps.println("<br><br><br><br><center><table cellspacing=0 cellpadding=0 border=0 width=50%>");
                    ps.println("<tr>");
                    ps.println("<td valign = middle>");
                    ps.println("<table cellspacing=0 cellpadding=10 border=1 width=100% bordercolor=#D7D7D7>");
                    ps.println("<tr >");
                    ps.println("<td align = center class=heading1>");
                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.promotionalItem", session).toUpperCase());
                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table>");

                    ps.println("</td>");
                    ps.println("</tr>");
                    ps.println("</table>");
                }

                ps.println("</body>");
                ps.println("</html>");
                stmt.close();
            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE", ComUtils.getLanguageTranslation("label.catalogue.sessionExpired", session), "YES", ComUtils.getLanguageTranslation("label.catalogue.loginAgain", session), "Index target=_parent", "", imagesURL);
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
