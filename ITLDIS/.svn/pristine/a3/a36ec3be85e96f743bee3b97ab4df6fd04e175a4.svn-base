package viewEcat.comEcat;

/*
File Name: LeftMenu .java
PURPOSE: Left Menu.
HISTORY:
DATE			BUILD		AUTHOR					MODIFICATIONS
NA				v3.4		Deepak Mangal			$$0 Created
11-Jan-08		v3.4		Deepak Mangal			$$1	"View Consolidated Order Status" & "View Orders Report"  are blocked
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

public class LeftMenu extends HttpServlet
{

    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException
    {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        try
        {
            ///////////////////////////// CREATE SESSION ///////////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////
            HttpSession session = req.getSession(true);
            String session_id = session.getId();

            String getSession = (String) session.getValue("session_value");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String date_OR_serial = (String) session.getValue("date_OR_serial");
            String userCode = (String) session.getValue("userCode");

            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");

            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////


            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();

            String orderRefNoString = object_pageTemplate.orderRefNoString();

            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////
//System.out.println("session_id.equals(getSession).."+session_id.equals(getSession));
            if (session_id.equals(getSession))
            {

                Connection conn = holder.getConnection();

                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));
                //
                //

                /*########################################*/
                //Statement stmt = conn.createStatement();
                PreparedStatement stmt = null;
                ResultSet rs;

//                Vector invUserVec = new Vector();
//
//                rs = stmt.executeQuery("SELECT USER_ID FROM USERS_FOR_INVENTORY");
//                while (rs.next())
//                {
//                    invUserVec.add(rs.getString(1));
//                }
//                rs.close();


                ps.println("<html>");
                ps.println("<head>");
                ps.println("<style type=\"text/css\">");
                ps.println("<!--");

                ps.println(".link {");
                ps.println("	font-family:Arial, Helvetica, sans-serif;");
                ps.println("	font-family: Arial, Helvetica, sans-serif;");
                ps.println("	font-size: 11px;");
                ps.println("	font-weight: bold;");
                ps.println("	color: #FFFFFF;");
                ps.println("	padding: 0px 0 0 15px;");
                ps.println("	background-image:url(" + imagesURL + "leftMenuImages/left_link.jpg);");
                ps.println("	background-repeat:no-repeat;");
                ps.println("	height:21px;");
                ps.println("}");
                ps.println(".link a {");
                ps.println("	font-family: Arial, Helvetica, sans-serif;");
                ps.println("	font-size: 11px;");
                ps.println("	text-decoration:none;");
                ps.println("	color:#FFFFFF;");
                ps.println("}");
                ps.println(".link a:hover {");
                ps.println("	font-family: Arial, Helvetica, sans-serif;");
                ps.println("	font-size: 11px;");
                ps.println("	text-decoration: underline;");
                ps.println("	color:#FFFFFF;");
                ps.println("}");

                ps.println("img { border: 0px; }");
                ps.println("font, th, td, p { font-family: :Arial, Helvetica, sans-serif; }");
                ps.println("a:link { color: #003399; text-decoration: none; }");
                ps.println("a:active { color: #003399 }");
                ps.println("a:visited { color: #003399; text-decoration: none; }");
                ps.println("a:hover { color: #dd6900; text-decoration: underline; }");

                ps.println(".gen, .poll { font-size: 12px }");
                ps.println(".genmed, .name { font-size: 11px; }");
                ps.println(".gensmall, .postdetails { font-size: 10px; }");
                ps.println(".strong { font-weight: bold; }");

                ps.println("-->");
                ps.println("</style>");

                //       ps.println("<link href='" + imagesURL + "leftMenuImages/style.css' rel=\"stylesheet\" type=\"text/css\" />");
                ps.println("<script language=JavaScript type=text/JavaScript>");

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
                //ps.println("		alert(\"Your Pop-up Blocker is Enabled. Please Allow Pop ups to view this website.\");");
                ps.println("		alert(\"Your Pop-up Blocker is Enabled. Please Add our site http://" + server_name + " to your trusted sites.\");");
                //	ps.println("		location.href='Index';");
                ps.println("		parent.parent.location.href = 'Logout'");
                ps.println("		return false;");
                ps.println("	} ");
                ps.println("	else ");
                ps.println("	{");
                ps.println("		myTest.close();");
                ps.println("		return true;");
                ps.println("	}");
                ps.println("}");

                ps.println("function show()");
                ps.println("{");
                ps.println("	document.getElementById(\"id1\").style.display='block';");
                ps.println("");
                ps.println("}");
                ps.println("function showMe(objectId)");
                ps.println("{");
                ps.println("	changeObjectVisibility(objectId, 'block');");
                ps.println("}");
                ps.println("function getStyleObject(objectId)");
                ps.println("{");
                ps.println("    if(document.getElementById && document.getElementById(objectId))");
                ps.println("	{");
                ps.println("		return document.getElementById(objectId).style;");
                ps.println("    }");
                ps.println("	else if (document.all && document.all(objectId))");
                ps.println("	{");
                ps.println("		return document.all(objectId).style;");
                ps.println("    }");
                ps.println("	else if (document.layers && document.layers[objectId])");
                ps.println("	{");
                ps.println("		return document.layers[objectId];");
                ps.println("    }else");
                ps.println("	{");
                ps.println("		return false;");
                ps.println("    }");
                ps.println("} ");
                ps.println("function changeObjectVisibility(objectId, newVisibility)");
                ps.println("{");
                ps.println("    var styleObject = getStyleObject(objectId);");
                ps.println("    if(styleObject.display=='block')");
                ps.println("    {");
                ps.println("        styleObject.display = 'none';");
                ps.println("    }");
                ps.println("    else if(styleObject.display=='none')");
                ps.println("    {");
                ps.println("       styleObject.display = 'block';");
                ps.println("    }");
                ps.println("}");
                ps.println("</script>");
                ps.println("<script language=JavaScript> <!--");
                ps.println("function click(e) {");
                ps.println("if (document.all) {");
                ps.println("if (event.button == 2) {");
                ps.println(" alert('Right Click is Disabled');");
                ps.println("return false;");
                ps.println("}");
                ps.println("}");
                ps.println("if (document.layers) {");
                ps.println("if (e.which == 3) {");
                ps.println("alert('Right Click is Disabled');");
                ps.println("return false;");
                ps.println("}");
                ps.println("}");
                ps.println("}");
                ps.println("if (document.layers) {");
                ps.println("document.captureEvents(Event.MOUSEDOWN);");
                ps.println("}");
                ps.println("document.onmousedown=click;");
                ps.println("// --> </script>");
                ps.println("<script language=JavaScript><!--");
                ps.println("if (document.layers) document.captureEvents(Event.KEYPRESS); // needed if you wish to cancel the key");
                ps.println("document.onkeydown = keyhandler;");
                ps.println("function keyhandler(e) {");
                ps.println("    var Key = (window.event) ? event.keyCode : e.keyCode;");
                ps.println("    if (Key == 93)");
                ps.println("	{");
                ps.println("        alert('Right Click is Disabled');");
                ps.println("	    return false;");
                ps.println("	}");
                ps.println("}");
                ps.println("//--> ");
                ps.println("</script>");
                ps.println("</head>");
                ps.println("<body  text=#000000 vLink=#5493b4 link=#006699  topmargin=0 leftmargin=0 style=\"background-image:url(" + imagesURL + "background2.jpg);\"");
                ps.println("<form name=form1>");
                ps.println(" <TABLE   cellSpacing=0 cellPadding=0 width=\"100%\" border=0>");
                ps.println("  <TBODY>");
                if (userFunctionalities.contains("1"))
                {
                    ps.println("  <TR>");
                    ps.println("     <TD  class=link ><A href=\"Main\"  target=\"right\"><font color=#ffffff>Home</font></A></TD></TR>");
                }

                if (userFunctionalities.contains("2") || userFunctionalities.contains("48") || userFunctionalities.contains("13"))
                {
//                    ps.println("  <TR>");
//                    ps.println("     <TD class=link><A href=\"#\" onClick=\"showMe('Layer1','none');\"><font color=#ffffff>Catalogues</font></A></TD>");
//                    ps.println("  </TR>");
                    ps.println("  <TR>");
                    ps.println("     <TD class=link><A href=\"View_EngineSeries\" target=\"right\"><font color=#ffffff>Catalogues</font></A></TD>");
                    ps.println("  </TR>");
                    ps.println("  <TR >");
                    ps.println("     <TD>");
                    ps.println("        <div id=\"Layer1\" LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 style=\"display:none;position:static;width:auto\">");
                    ps.println("        <TABLE cellSpacing=0 cellPadding=0  width=\"100%\" border=0>");
                    ps.println("        <TBODY>");
                    if (userFunctionalities.contains("2"))
                    {
                        ps.println("            <TR >");
                        ps.println("                <TD   width=20>&nbsp;</TD>");
                        ps.println("                <TD  ><li><A class=genmed    href=\"#\" onClick=\"showMe('Layer7','none');\"><b>Parts Catalogue</A>");
                        ps.println("        <div id=\"Layer7\" LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 style=\"display:none;position:static;width:auto\">");
                        ps.println("        <TABLE cellSpacing=0 cellPadding=0  width=\"100%\" border=0>");
                        ps.println(" <tr><TD width=20% align=right><li type=square></TD><td align=left><a class=genmed href=MainModels target=\"right\">All Models</a></td></tr>");
                        if (date_OR_serial.equals("latest"))
                        {
                            String temp_1 = "";
                            String c1 = "";
                            if (userFunctionalities.contains("36"))
                            {
                               // rs = stmt.executeQuery("SELECT DISTINCT MC.ENGINE_SERIES FROM CAT_MODEL_CLASSIFICATION MC, CAT_MODELS M where M.MODEL_NO=MC.MODEL_NO and M.STATUS='COMPLETE'");
                                String query = ("SELECT DISTINCT MC.ENGINE_SERIES FROM CAT_MODEL_CLASSIFICATION(NOLOCK) MC, CAT_MODELS(NOLOCK) M where M.MODEL_NO=MC.MODEL_NO and M.STATUS='COMPLETE'");
                                stmt = conn.prepareStatement(query);
                                rs = stmt.executeQuery();
                            }
                            else
                            {
                                //rs = stmt.executeQuery("SELECT DISTINCT MC.ENGINE_SERIES FROM CAT_MODEL_CLASSIFICATION MC, CAT_MODELS M where M.MODEL_NO=MC.MODEL_NO");
                           String query = ("SELECT DISTINCT MC.ENGINE_SERIES FROM CAT_MODEL_CLASSIFICATION(NOLOCK) MC, CAT_MODELS(NOLOCK) M where M.MODEL_NO=MC.MODEL_NO"); 
                           stmt = conn.prepareStatement(query);
                           rs = stmt.executeQuery();
                            }
                            while (rs.next())
                            {
                                temp_1 = rs.getString(1);
                                c1 = temp_1;
                                temp_1 = temp_1.replaceAll("\'", "''");

                                if (temp_1 != null && !(temp_1.equals("NA")))
                                {
                                    ps.println(" <tr><TD width=20% align=right><li type=square></TD><td align=left><a class=genmed href=\"MainModels?engineSeries='" + temp_1 + "'\" target=\"right\">" + c1 + "</a></td></tr>");
                                }
                            }
                            rs.close();
                        }

                        ps.println("        </TABLE>");
                        ps.println("           </div> ");
                        ps.println("                </TD></TR>");
                    }
                    if (userFunctionalities.contains("48"))
                    {
                        if (date_OR_serial.equals("latest"))
                        {
                            ps.println("            <TR>");
                            ps.println("                <TD   width=20>&nbsp;</TD>");
                            ps.println("                <TD  ><li><A class=genmed  href=\"#\" onClick=\"showMe('Layer8','none');\"><b>Kits Catalogue</A>");
                            ps.println("        <div id=\"Layer8\" LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 style=\"display:none;position:static;width:auto\">");
                            ps.println("        <TABLE cellSpacing=0 cellPadding=0  width=\"100%\" border=0>");
                            ps.println(" <tr><TD width=20% align=right><li type=square></TD><td align=left><a class=genmed href=ViewKits target=\"right\">All Kits</a></td></tr>");
                            String kits = "";
                            //rs = stmt.executeQuery("SELECT DISTINCT SERIES FROM S_KIT_DETAIL");
                            String  query = ("SELECT DISTINCT SERIES FROM S_KIT_DETAIL(NOLOCK)");
                            stmt = conn.prepareStatement(query);
                            rs = stmt.executeQuery();
                            while (rs.next())
                            {
                                kits = rs.getString(1);
                                if (kits != null && !(kits.equals("NA")))
                                {
                                    ps.println(" <tr><TD width=20% align=right><li type=square></TD><td align=left><a class=genmed href=\"ViewKits?kitSeries='" + kits + "'\" target=\"right\">" + kits + "</a></td></tr>");
                                }
                            }
                            rs.close();

                            ps.println("        </TABLE>");
                            ps.println("           </div> ");
                            ps.println("   </TD></TR>");
                        }
                    }
                    //////New LINK FOR///////////////////////////////////////////////////////
                    ps.println("            <TR>");
                    ps.println("                <TD   width=20>&nbsp;</TD>");
                    ps.println("                <TD  ><li><A class=genmed  href=\"#\" onClick=\"showMe('Layer18','none');\"><b>Crop Solutions</A>");
                    ps.println("        <div id=\"Layer18\" LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 style=\"display:none;position:static;width:auto\">");
                    ps.println("        <TABLE cellSpacing=0 cellPadding=0  width=\"100%\" border=0>");
                    ps.println(" <tr><TD width=20% align=right><li type=square></TD><td align=left><a class=genmed href=\"http://" + server_name + "/ecatalogue/sol_dealer/dealer/ecat_pdf/TD90D_T3.pdf\" target=\"_blank\">Tractor TD90-T3</a></td></tr>");
                    //////New LINK ///////////////////////////////////////////////////////
                    //   ps.println(" <tr><TD width=20% align=right><li type=square></TD><td align=left><a class=genmed href=\"http://" + server_name + "/ecatalogue/sol_dealer/dealer/ecat_pdf/Balers.pdf\" target=\"_blank\">Balers</a></td></tr>");
                    //////New LINK  ENDS///////////////////////////////////////////////////////
                    ps.println("        </TABLE>");
                    ps.println("           </div> ");
                    ps.println("   </TD></TR>");
                    //////New LINK FOR ENDS///////////////////////////////////////////////////////


                    if (userFunctionalities.contains("13"))
                    {
                        if (orderRefNoString.equals("WEB"))
                        {
                            ps.println("		<TR>");
                            ps.println("                <TD   width=20>&nbsp;</TD>");
                            ps.println("                <TD  ><li><A class=genmed  href=\"http://" + server_name + "/ecatalogue/tools/index.html\" target=_blank ><b>Special Tools</A> </TD></TR>");
                        }
                        else
                        {
                            ps.println("		<TR>");
                            ps.println("                <TD   width=20>&nbsp;</TD>");
                            ps.println("                <TD  ><li><A class=genmed  href=\"OpenIE?url=" + mainURL + "dealer/tools/index.html\" target=_blank ><b>Special Tools</A> </TD></TR>");
                        }

                    }

                    ps.println("        </TBODY></TABLE>");
                    ps.println("        </div>");
                    ps.println("    </TD></TR>");
                }

                if (!(date_OR_serial.equals("serialNo") || date_OR_serial.equals("model_date")))
                {
                    if (userFunctionalities.contains("4"))
                    {
                        ps.println("               <TR>");
                        ps.println("                <TD  class=link><A  href=\"#\" onClick=\"showMe('Layer5','none');\"><font color=#ffffff>Order Option</font></A></TD></TR>");
                        ps.println("                <TR >");
                        ps.println("                <TD>");
                        ps.println("                <div id=\"Layer5\" style=\"display:none;position:static;width:auto\">");
                        ps.println("		  <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" >");
                        ps.println("              <TBODY>");
                        /*****************NEW ORDERING OPTION***************************************************************************************/
                        if (userFunctionalities.contains("327"))
                        {
                            ps.println("                <TD  width=20>&nbsp;</TD>");
                            ps.println("                <TD ><li><A class=genmed   href=\"DraftOrder_0\" target=\"right\" ><b>Unregistered Orders</A> </TD></TR>");
                            ps.println("               </TR>");
                        }

                        if (userFunctionalities.contains("20"))
                        {
                            ps.println("              <TR >");
                            ps.println("                <TD  width=20>&nbsp;</TD>");
                            ps.println("                <TD ><li><A class=genmed   href=\"CreateOrder_0\" target=\"right\" ><b>Create New Order</A> </TD></TR>");
                            ps.println("               </TR>");
                        }

                        if (userFunctionalities.contains("21"))
                        {
                            ps.println("              <TR >");
                            ps.println("                <TD  width=20>&nbsp;</TD>");
                            ps.println("                <TD ><li><A class=genmed   href=\"ViewOrderNew_0\" target=\"right\" ><b>Registered Orders</A> </TD></TR>");
                            ps.println("               </TR>");
                        }

                        if (userFunctionalities.contains("331"))
                        {
                            if (userFunctionalities.contains("311"))
                            {
                                ps.println("              <TR>");
                                ps.println("                <TD  width=20 align=right>&nbsp;</TD>");
                                ps.println("                <TD ><li><A class=genmed  onClick=\"showMe('Layer13','none');\" href=\"#\" ><b>Back Order Report </A> ");
                                ps.println("		  <div id=\"Layer13\" style=\"display:none;position:static;width:auto\">");
                                ps.println("		  <TABLE cellSpacing=0 cellPadding=0 width=\"100%\"  >");

                                ps.println("		  <TR>");
                                ps.println("                <TD  width=20% align=right><li type=square></TD><TD><A class=genmed  href=javascript:MM_openBrWindow('BackOrderReport_0?criteria=orderWise','BACKORDER','resizable=no,scrollbars=yes,width=1010,height=700')>Orderwise</A> </TD></TR>");

                                ps.println("              <TR>");
                                ps.println("                <TD  width=20% align=right><li type=square></TD><TD ><A class=genmed    href=javascript:MM_openBrWindow('BackOrderReport_0?criteria=consolidated','BACKORDER','resizable=no,scrollbars=yes,width=1010,height=700')>Consolidated</A> </TD></TR>");

                                ps.println("		</TABLE>");
                                ps.println("                </div></TD></TR>");
                            }
                            else
                            {
                                ps.println("              <TR >");
                                ps.println("                <TD  width=20>&nbsp;</TD>");
                                ps.println("                <TD ><li><A class=genmed   href=\"BackOrderRepInput\" target=\"right\" ><b>Back Order Report</A> </TD></TR>");
                                ps.println("               </TR>");
                            }

                        }

                        /*****************NEW DEALER LEVEL OPTION ENDS***************************************************************************************/
                        if (userFunctionalities.contains("250"))
                        {
                            ps.println("              <TR>");
                            ps.println("                <TD  width=20 align=right>&nbsp;</TD>");
                            ps.println("                <TD ><li><A class=genmed  onClick=\"showMe('Layer9','none');\" href=\"#\" ><b>Web Orders</A> ");
                            ps.println("		  <div id=\"Layer9\" style=\"display:none;position:static;width:auto\">");
                            ps.println("		  <TABLE cellSpacing=0 cellPadding=0 width=\"100%\"  >");
                            if (userFunctionalities.contains("21"))
                            {
                                ps.println("              <TR>");
                                ps.println("                <TD  width=20% align=right><li type=square></TD><TD ><A class=genmed    href=\"ViewOrder_0\" target=\"right\">View Order</A> </TD></TR>");
                            }
                            if (userFunctionalities.contains("20"))
                            {
                                ps.println("		  <TR>");
                                ps.println("                <TD  width=20% align=right><li type=square></TD><TD><A class=genmed    href=\"CreateOrder_0\" target=\"right\">Create New Order</A> </TD></TR>");
                            }
                            ps.println("		</TABLE>");
                            ps.println("                </div></TD></TR>");
                        }
                        if (orderRefNoString.equals("WEB"))
                        {
                            if (userFunctionalities.contains("251"))
                            {
                                ps.println("              <TR>");
                                ps.println("                <TD  width=20 align=right>&nbsp;</TD>");
                                ps.println("                <TD ><li><A class=genmed   target=\"right\" href=\"ViewManualOrder_0\" ><b>Manual Orders</A> </TD></TR>");
                            }
                        }

                        if (userFunctionalities.contains("284") || userFunctionalities.contains("249"))
                        {
                            ps.println("              <TR>");
                            ps.println("                <TD  width=20 align=right>&nbsp;</TD>");
                            ps.println("                <TD ><li><A class=genmed  onClick=\"showMe('Layer10','none');\" href=\"#\" ><b>Order Search / Enquiry</A> ");
                            ps.println("		  <div id=\"Layer10\" style=\"display:none;position:static;width:auto\">");
                            ps.println("		  <TABLE cellSpacing=0 cellPadding=0 width=\"100%\"  >");
                            if (userFunctionalities.contains("284"))
                            {
                                ps.println("		  <TR>");
                                ps.println("                <TD  width=20% align=right><li type=square></TD><TD><A class=genmed    href=\"SearchOption\" target=\"right\">Order Search</A> </TD></TR>");
                            }
                            if (orderRefNoString.equals("WEB") && userFunctionalities.contains("249"))
                            {
                                ps.println("              <TR>");
                                ps.println("                <TD  width=20% align=right><li type=square></TD><TD ><A class=genmed    href=\"OrderOptions?flag=Report\" target=\"right\">MIS Reports</A> </TD></TR>");
                            }
                            ps.println("		</TABLE>");
                            ps.println("                </div></TD></TR>");
                        }

                        if (orderRefNoString.equals("LOCAL"))
                        {
                            if (userFunctionalities.contains("165"))
                            {
                                ps.println("              <TR>");
                                ps.println("                <TD  width=20 align=right>&nbsp;</TD>");
                                ps.println("                <TD ><li><A class=genmed  target=\"right\" href=\"ExportCartToExcel\" ><b>Export Cart To Excel</A> </TD></TR>");
                            }
                        }

                        if (userFunctionalities.contains("5"))
                        {
                            ps.println("              <TR>");
                            ps.println("                <TD  width=20 align=right>&nbsp;</TD>");
                            ps.println("                <TD ><li><A class=genmed  onClick=\"showMe('Layer11','none');\" href=\"#\" ><b>MSO View Options</A> ");
                            ps.println("		  <div id=\"Layer11\" style=\"display:none;position:static;width:auto\">");
                            ps.println("		  <TABLE cellSpacing=0 cellPadding=0 width=\"100%\"  >");
                            if (userFunctionalities.contains("18"))
                            {
                                ps.println("		  <TR>");
                                ps.println("                <TD  width=20% align=right><li type=square></TD><TD><A class=genmed    href=\"ViewMSO?seriesType=OLD\" target=\"right\">Old Series</A> </TD></TR>");
                            }
                            if (userFunctionalities.contains("19"))
                            {
                                ps.println("              <TR>");
                                ps.println("                <TD  width=20% align=right><li type=square></TD><TD ><A class=genmed    href=\"ViewMSO?seriesType=NEW\" target=\"right\">New Series</A> </TD></TR>");
                            }
                            ps.println("		</TABLE>");
                            ps.println("                </div></TD></TR>");
                        }

                        ps.println("               </TBODY></TABLE>");
                        ps.println("                </div></td></tr>");
                    }
                }

                /* 
                 * Invoice report for the dealer
                 */
                if (userFunctionalities.contains("335"))
                {
                    ps.println("		  <TR>");
                    ps.println("                <TD  class=link>");//InvoiceReport_1//\"View_EngineSeries\"
                    ps.println("                    <A href=\"InvoiceReport_1\" target=\"right\"><font color=#ffffff>Invoice Details</font></A></TD></TR>");
                }
                /*End of invoice report*/


                if (orderRefNoString.equals("WEB"))
                {
                    if (userFunctionalities.contains("14") || userFunctionalities.contains("15") || userFunctionalities.contains("32") || userFunctionalities.contains("283") || userFunctionalities.contains("380"))
                    {
                        ps.println("       <TR>");
                        ps.println("          <TD  class=link><A  href=\"#\" onClick=\"showMe('Layer2','none');\"><font color=#ffffff>Bulletin / Circular</font></A></TD></TR>");
                        ps.println("        <TR>");
                        ps.println("          <TD>");
                        ps.println("		<div id=\"Layer2\" style=\"display:none;position:static;width:auto\">");
                        ps.println("		  <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" >");
                        ps.println("              <TBODY>");
//                        if (userFunctionalities.contains("14"))
//                        {
//                            ps.println("              <TR >");
//                            ps.println("                <TD  width=20>&nbsp;</TD>");
//                            ps.println("                <TD ><li><A class=genmed   href=\"SparesPriceList\" target=\"right\" ><b>Price List</A> </TD></TR>");
//                            ps.println("               </TR>");
//                        }
                        if (userFunctionalities.contains("15"))
                        {
                            ps.println("		  <TR>");
                            ps.println("                <TD  width=20>&nbsp;</TD>");
                            ps.println("                <TD ><li><A class=genmed   href=\"SparesCommunication\" target=\"right\"><b>Parts Circular</A> </TD></TR>");
                        }
                        if (userFunctionalities.contains("380"))
                        {
                            ps.println("		  <TR>");
                            ps.println("                <TD  width=20>&nbsp;</TD>");
                            ps.println("                <TD ><li><A class=genmed   href=\"PartECNBulletin\" target=\"right\"><b>Parts Bulletin</A> </TD></TR>");
                        }
//                        if (userFunctionalities.contains("32"))
//                        {
//                            ps.println("		  <TR>");
//                            ps.println("                <TD  width=20>&nbsp;</TD>");
//                            ps.println("                <TD ><li><A class=genmed   onClick=\"showMe('Layer14','none');\" href=\"#\" ><b>Service Bulletin</A> ");
//                            ps.println("		  <div id=\"Layer14\" style=\"display:none;position:static;width:auto\">");
//                            ps.println("		  <TABLE cellSpacing=0 cellPadding=0 width=\"100%\"  >");
//
//                            ps.println("		  <TR>");
//                            ps.println("                <TD  width=20% align=right><li type=square></TD><TD><A class=genmed  href='ServiceBulletin_1?category=tractor' target=\"right\">For Tractors</A> </TD></TR>");
//
//                            ps.println("              <TR>");
//                            ps.println("                <TD  width=20% align=right><li type=square></TD><TD ><A class=genmed    href='ServiceBulletin_1?category=rotavator' target=\"right\">For Rotavators</A> </TD></TR>");
//
//                            ps.println("		</TABLE>");
//                            ps.println("                    </div></TD></TR>");
//                        }
//                        if (userFunctionalities.contains("283"))
//                        {
//                            ps.println("		  <TR>");
//                            ps.println("                <TD  width=20>&nbsp;</TD>");
//                            ps.println("                <TD ><li><A class=genmed    href=javascript:MM_openBrWindow('AlternateOrReplacebyPdf','ALTREPLACE','resizable=no,scrollbars=yes,width=1010,height=700')><b>Alternate/Replaced Items</A> </TD></TR>");
//                        }


                        ps.println("			    </TBODY></TABLE></div>");
                        ps.println("		  </TD></TR>");
                    }

//                    if (userFunctionalities.contains("8"))
//                    {
//                        ps.println("              <TR>");
//                        ps.println("                <TD  class=link><A  href=\"#\" onClick=\"showMe('Layer3','none');\"><font color=#ffffff>After Sales Warranty</font></A></TD></TR>");
//                        ps.println("              <TR >");
//                        ps.println("                <TD>");
//                        ps.println("                <div id=\"Layer3\" style=\"display:none;position:static;width:auto\">");
//                        ps.println("                <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" >");
//                        if (userFunctionalities.contains("342"))
//                        {
//                            ps.println("                <TR>");
//                            ps.println("                 <TD  width=20>&nbsp;</TD>");
//                            ps.println("                 <TD ><li><A class=genmed    href=\"Credit_summary\" target=\"right\"><b>Credit Summary</A> </TD></TR>");
//                        }
//                        if (userFunctionalities.contains("87"))
//                        {
//                            ps.println("                <TR>");
//                            ps.println("                 <TD  width=20>&nbsp;</TD>");
//                            ps.println("                 <TD ><li><A class=genmed    href=\"WarrantyInput\" target=\"right\"><b>Credit Details</A> </TD></TR>");
//                        }
//
//                        if (userFunctionalities.contains("326"))
//                        {
//                            ps.println("                <TR>");
//                            ps.println("                 <TD  width=20>&nbsp;</TD>");
//                            ps.println("                 <TD ><li><A class=genmed    href=\"WarrantyMISReport\" target=\"right\"><b>Warranty MIS Report</A> </TD></TR>");
//                        }
//                        if (userFunctionalities.contains("387"))
//                        {
//                            ps.println("                <TR>");
//                            ps.println("                 <TD  width=20>&nbsp;</TD>");
//                            ps.println("                 <TD ><li><A class=genmed href=\"CbuExpendReport1\" target=\"right\"><b>CBU Wise Expenditure</A></TD></TR>");
//                        }
//                        if (userFunctionalities.contains("390"))
//                        {
//                            ps.println("                <TR>");
//                            ps.println("                 <TD  width=20>&nbsp;</TD>");
//                            ps.println("                 <TD ><li><A class=genmed href=\"NonUIOExpenditure1\" target=\"right\"><b>UIO other than Tractors</A></TD></TR>");
//                        }
//                        if (userFunctionalities.contains("388"))
//                        {
//                            ps.println("                <TR>");
//                            ps.println("                 <TD  width=20>&nbsp;</TD>");
//                            ps.println("                 <TD ><li><A class=genmed href=\"WarrantyDataDetails_0\" target=\"right\"><b>Warranty Data Details</A></TD></TR>");
//                        }
//                        if (userFunctionalities.contains("88") || userFunctionalities.contains("89") || userFunctionalities.contains("90"))
//                        {
//                            ps.println("                <TR>");
//                            ps.println("                <TD  width=20>&nbsp;</TD>");
//                            ps.println("                <TD ><li><A class=genmed href=\"#\" onClick=\"showMe('Layer4','none');\" ><b>Acknowledgement</A> ");
//                            ps.println("		  <div id=\"Layer4\" style=\"display:none;position:static;width:auto\">");
//                            ps.println("		  <TABLE cellSpacing=0 cellPadding=0 width=\"100%\"  >");
//                            if (userFunctionalities.contains("88"))
//                            {
//                                ps.println("              <TR>");
//                                ps.println("                <TD  width=20% align=right><li type=square></TD><TD ><A class=genmed    href=\"WarAckInput?warType=claims\" target=\"right\">Warranty Claims</A> </TD></TR>");
//                            }
//                            if (userFunctionalities.contains("89"))
//                            {
//                                ps.println("		  <TR>");
//                                ps.println("                <TD  width=20% align=right><li type=square></TD><TD><A class=genmed    href=\"WarAckInput?warType=coupons\" target=\"right\">Coupons</A> </TD></TR>");
//                            }
//                            if (userFunctionalities.contains("90"))
//                            {
//                                ps.println("             <TR>");
//                                ps.println("                <TD  width=20% align=right><li type=square></TD><TD ><A class=genmed    href=\"WarAckInput?warType=regCards\" target=\"right\" >Registration Cards</A> </TD></TR>");
//                            }
//                            ps.println("		</TABLE>");
//                            ps.println("                </div></TD></TR>");
//                        }
//
//                        ps.println("		</div>");
//                        ps.println("                </TD></TR></TABLE>");
//
//                    }
                }
                if (userFunctionalities.contains("3"))
                {
                    if (!(date_OR_serial.equals("serialNo") || date_OR_serial.equals("model_date")))
                    {
                        ps.println("		  <TR>");
                        ps.println("                <TD  class=link>");
                        ps.println("                    <A href=\"ReportOptions\" target=\"right\"><font color=#ffffff>Reports</font></A></TD></TR>");
                    }
                }

                if (userFunctionalities.contains("300"))
                {
                    ps.println("               <TR>");
                    ps.println("                 <TD  class=link>");
                    ps.println("                    <A href=\"InfoFrame\" target=\"right\"><font color=#ffffff>User Profile</font></A></TD></TR>");
                    ps.println("              <TR >");
                }

                if (userFunctionalities.contains("12"))
                {
                    if (orderRefNoString.equals("WEB"))
                    {
                        ps.println("		  <TR>");
                        ps.println("                <TD class=link>");
                        ps.println("                    <A  href=\"" + mainURL + "dealer/hlp/index.html\" target=_blank ><font color=#ffffff>Help</font></A></TD></TR>");
                    }
                    else
                    {
                        ps.println("		  <TR>");
                        ps.println("                <TD  class=link>");
                        ps.println("                    <A href=\"OpenIE?url=" + mainURL + "dealer/hlp/index.html\" target=_blank ><font color=#ffffff>Help</font></A></TD></TR>");
                    }

                }

//                if (orderRefNoString.equals("WEB"))
//                {
//                    if (userFunctionalities.contains("282"))
//                    {
//                        ps.println("		  <TR>");
//                        ps.println("                <TD  class=link>");
//                        ps.println("                    <A  href=\"SoftwareSupport\" target=\"right\"><font color=#ffffff>Software Support</font></A></TD></TR>");
//                    }
//                }
                if (userFunctionalities.contains("7"))
                {
                    if (orderRefNoString.equals("LOCAL"))
                    {
                        ps.println("		  <TR>");
                        ps.println("                <TD  class=link>");
                        ps.println("                    <A  href=\"Others\" target=\"right\"><font color=#ffffff>Update e-Catalogue</font></A></TD></TR>");
                    }
                }

                if (userFunctionalities.contains("341"))
                {
//                    if (userFunctionalities.contains("340"))
//                    {
//                        ps.println("		  <TR>");
//                        ps.println("                <TD  class=link>");
//                        ps.println("                    <A  href=\"Survey\" target=\"right\" ><font color=#ffffff>Satisfaction Survey</font></A></TD></TR>");
//                    }
//                    else if (userFunctionalities.contains("339"))
//                    {
//                        ps.println("		  <TR>");
//                        ps.println("                <TD  class=link>");
//                        ps.println("                    <A  href=\"SurveyAdmin\" target=\"right\" ><font color=#ffffff>Satisfaction Survey</font></A></TD></TR>");
//                    }
//                    if (userFunctionalities.contains("358"))
//                    {
//                        ps.println("		  <TR>");
//                        ps.println("                <TD  class=link>");
//                        ps.println("                    <A  href=\"SurveyReport\" target=\"right\" ><font color=#ffffff>Satisfaction Survey Report</font></A></TD></TR>");
//                    }

                    if (userFunctionalities.contains("347"))
                    {
                        ps.println("		  <TR>");
                        ps.println("                <TD  class=link>");
                        ps.println("                    <A  href=\"Dealer_DssDate\" target=\"right\" ><font color=#ffffff>Update DSS Target Date</font></A></TD></TR>");
                    }

                }

                if (orderRefNoString.equals("WEB"))
                {
                    if (userFunctionalities.contains("384"))
                    {
                        ps.println("		  <TR>");
                        ps.println("                <TD  class=link>");
                        ps.println("                    <A  href=\"ViewECOExport1\" target=\"right\" ><font color=#ffffff>View ECO(s)</font></A></TD></TR>");
                    }
                    if (userFunctionalities.contains("16"))
                    {
                        ps.println("		  <TR>");
                        ps.println("                <TD  class=link>");
                        ps.println("                    <A  href=\"View_dealers_1\" target=\"right\" ><font color=#ffffff>Admin Login</font></A></TD></TR>");
                    }
                    if (userFunctionalities.contains("291"))
                    {
                        ps.println("		  <TR>");
                        ps.println("                <TD  class=link>");
                        ps.println("                    <A  href=\"Feedback\" target=\"right\" ><font color=#ffffff>Feedback</font></A></TD></TR>");
                    }

                    if (userFunctionalities.contains("294"))
                    {
                        ps.println("       <TR>");
                        ps.println("          <TD  class=link><A  href=\"#\" onClick=\"showMe('Layer12','none');\"><font color=#ffffff>ECO Management</font></A></TD></TR>");
                        ps.println("        <TR>");
                        ps.println("          <TD>");
                        ps.println("		<div id=\"Layer12\" style=\"display:none;position:static;width:auto\">");
                        ps.println("		  <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" >");
                        ps.println("              <TBODY>");
                        /*  if (userFunctionalities.contains("295"))
                        {
                        ps.println("              <TR >");
                        ps.println("                <TD  width=20>&nbsp;</TD>");
                        // ps.println("                <TD ><li><A class=genmed   href=\"EcoAuthorization_0?stat=authorize\" target=\"right\" ><b>Authorize ECO</A> </TD></TR>");
                        ps.println("                <TD ><li><A class=genmed   href=\"EcnMgmt?stat=authorize\" target=\"right\" ><b>Authorize ECO</A> </TD></TR>");
                        ps.println("               </TR>");
                        }
                        if (userFunctionalities.contains("296"))
                        {
                        ps.println("		  <TR>");
                        ps.println("                <TD  width=20>&nbsp;</TD>");
                        ps.println("                <TD ><li><A class=genmed   href=\"EcoAuthorization_0?stat=implement\" target=\"right\"><b>Implement ECO</A> </TD></TR>");
                        }
                        if (userFunctionalities.contains("297"))
                        {
                        ps.println("		  <TR>");
                        ps.println("                <TD  width=20>&nbsp;</TD>");
                        ps.println("                <TD ><li><A class=genmed    href=\"EcoAuthorization_0?stat=approve\" target=\"right\"><b>Approve ECO</A> </TD></TR>");
                        }
                         */
                        if (userFunctionalities.contains("403"))
                        {
                            ps.println("		  <TR>");
                            ps.println("                <TD  width=20>&nbsp;</TD>");
                            ps.println("                <TD ><li><A class=genmed    href=\"ECNBulletin\" target=\"right\"><b>ECN Bulletin</A> </TD></TR>");
                        }
                        if (userFunctionalities.contains("298"))
                        {
                            ps.println("		  <TR>");
                            ps.println("                <TD  width=20>&nbsp;</TD>");
                            ps.println("                <TD ><li><A class=genmed    href=\"EcnMgmt?stat=view\" target=\"right\"><b>View ECO</A> </TD></TR>");
                        }

                        if (userFunctionalities.contains("296"))
                        {
                            ps.println("		  <TR>");
                            ps.println("                <TD  width=20>&nbsp;</TD>");
                            ps.println("                <TD ><li><A class=genmed    href=\"EcnToImplement\" target=\"right\"><b>Implement ECO</A> </TD></TR>");
                        }

                        if (userFunctionalities.contains("385"))
                        {
                            ps.println("		  <TR>");
                            ps.println("                <TD  width=20>&nbsp;</TD>");
                            ps.println("                <TD ><li><A class=genmed    href=\"EcoToExcel\" target=\"right\"><b>Export ECO(s) to Excel</A> </TD></TR>");
                        }

                        ps.println("			    </TBODY></TABLE></div>");
                        ps.println("		  </TD></TR>");

                    }

//                    if (userFunctionalities.contains("383"))
//                    {
//                        ps.println("		  <TR>");
//                        ps.println("                <TD  class=link>");
//                        ps.println("                    <A  href=\"AssignInventory1\" target=\"right\" ><font color=#ffffff>Assign Inventory Mgmt.</font></A></TD></TR>");
//                    }

                    //if (userFunctionalities.contains("348") && (userCode.equals("800001") || userCode.equals("230340") || userCode.equals("210507") || userCode.equals("210551") || userCode.equals("110604") || userCode.equalsIgnoreCase("nhi")))                    
                    //   if (userFunctionalities.contains("348"))
//                    if (userFunctionalities.contains("348") && invUserVec.contains(userCode))
//                    {
//                        ps.println("       <TR>");
//                        ps.println("          <TD  class=link><A  href=\"#\" onClick=\"showMe('Layer21','none');\"><font color=#ffffff>Manage Inventory</font></A></TD></TR>");
//                        ps.println("        <TR>");
//                        ps.println("          <TD>");
//                        ps.println("		<div id=\"Layer21\" style=\"display:none;position:static;width:auto\">");
//                        ps.println("		  <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" >");
//                        ps.println("              <TBODY>");
//
//                        Inventory_util inv_util = new Inventory_util(conn);
//                        if (userFunctionalities.contains("349") && !inv_util.checkInventoryLock(userCode))
//                        {
//                            if (userFunctionalities.contains("349"))
//                            {
//                                ps.println("              <TR >");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed   href=\"UploadInventory\" target=\"right\" ><b>Upload Inventory</A> </TD></TR>");
//                                ps.println("               </TR>");
//                            }
//                            if (userFunctionalities.contains("350"))
//                            {
//                                ps.println("		  <TR>");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed   href=\"LockInventory\" target=\"right\"><b>Lock Inventory</A> </TD></TR>");
//                            }
//                            if (userFunctionalities.contains("351"))
//                            {
//                                ps.println("		  <TR>");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed    href=\"ViewInventory\" target=\"right\"><b>View Inventory</A> </TD></TR>");
//                                ps.println("               </TR>");
//                            }
//                        }
//                        else
//                        {
//                            if (userFunctionalities.contains("351"))
//                            {
//                                ps.println("		  <TR>");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed  href=\"ViewInventory\" target=\"right\"><b>View Inventory1</A> </TD></TR>");
//                            }
//                            if (userFunctionalities.contains("352"))
//                            {
//                                ps.println("		  <TR>");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed    href=\"AddInventory\" target=\"right\"><b>Create GRN for Recd. Material</A> </TD></TR>");
//                            }
//                            if (userFunctionalities.contains("353"))
//                            {
//                                ps.println("		  <TR>");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed    href=\"AddNonInventory\" target=\"right\"><b>Add Inventory From Other Dealers</A> </TD></TR>");
//                            }
//                            if (userFunctionalities.contains("354"))
//                            {
//                                ps.println("		  <TR>");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed    href=\"JobCardInput\" target=\"right\"><b>Add Job Card </A> </TD></TR>");
//                            }
//                            if (userFunctionalities.contains("356"))
//                            {
//                                ps.println("		  <TR>");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed    href=\"JobInvoiceQuery\" target=\"right\"><b>JC / INV / CHL / SR Query </A> </TD></TR>");
//                            }
//                            if (userFunctionalities.contains("357"))
//                            {
//                                ps.println("		  <TR>");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed    href=\"CounterSale\" target=\"right\"><b>Counter Sale</A> </TD></TR>");
//                            }
//                            if (userFunctionalities.contains("359"))
//                            {
//                                ps.println("		  <TR>");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed    href=\"CreateChallan\" target=\"right\"><b>Create Challan</A> </TD></TR>");
//                            }
//                            if (userFunctionalities.contains("360"))
//                            {
//                                ps.println("		  <TR>");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed    href=\"CloseChallan\" target=\"right\"><b>Close Challan</A> </TD></TR>");
//                            }
//
//                            if (userFunctionalities.contains("381"))
//                            {
//                                ps.println("		  <TR>");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed    href=\"SalesReturn\" target=\"right\"><b>Create Sale Return</A> </TD></TR>");
//                            }
//
//                            boolean flag = false;
//
//                            rs = stmt.executeQuery("SELECT * FROM USERS_BRANCH where user_id='" + userCode + "' or BRANCH_USER_ID='" + userCode + "'");
//                            if (rs.next())
//                            {
//                                flag = true;
//                            }
//                            rs.close();
//
//                            if (userFunctionalities.contains("371") && flag)
//                            {
//                                ps.println("		  <TR>");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed    href=\"TransferStock\" target=\"right\"><b>Transfer Stock to Branch</A> </TD></TR>");
//                            }
//                            if (userFunctionalities.contains("372") && flag)
//                            {
//                                ps.println("		  <TR>");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed    href=\"ReceiveStock\" target=\"right\"><b>Receive Stock from Branch</A> </TD></TR>");
//                            }
//
//                            if (userFunctionalities.contains("355") && !(inv_util.getInactiveUsers(355).contains(userCode)))
//                            {
//                                ps.println("		  <TR>");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed    href=\"SkipInvoice\" target=\"right\"><b>Skip GRN for Invoice</A> </TD></TR>");
//                            }
//
//                            if (userFunctionalities.contains("382"))
//                            {
//                                ps.println("		  <TR>");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed  href=\"ManageLocation1\" target=\"right\"><b>Manage Locations</A> </TD></TR>");
//                            }
//
//                        }
//
//                        if (userFunctionalities.contains("361"))
//                        {
//                            ps.println("		  <TR>");
//                            ps.println("                <TD  width=20>&nbsp;</TD>");
//                            ps.println("                <TD ><li><A class=genmed  href=\"SkipInventGrn\" target=\"right\"><b>Lock Or Unlock Skip GRN</A> </TD></TR>");
//                        }
//                        ps.println("	</TBODY></TABLE></div>");
//                        ps.println("</TD></TR>");
//
//                    }
                    /////////////////////////////////////////////////////////////////////////////
                   //&& invUserVec.contains(userCode)
                    if (userFunctionalities.contains("397"))
                    {
                        ps.println("       <TR>");
                        ps.println("          <TD  class=link><A  href=\"#\" onClick=\"showMe('Layer51','none');\"><font color=#ffffff>Reorder Inventory Mgmt</font></A></TD></TR>");
                        ps.println("        <TR>");
                        ps.println("          <TD>");
                        ps.println("		<div id=\"Layer51\" style=\"display:none;position:static;width:auto\">");
                        ps.println("		  <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" >");
                        ps.println("              <TBODY>");

//                        Inventory_util inv_util = new Inventory_util(conn);
//                        if (inv_util.checkInventoryLock(userCode))
//                        {
//                            if (userFunctionalities.contains("398"))
//                            {
//                                ps.println("              <TR >");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed   href=\"UploadMinMaxLevel_1\" target=\"right\" ><b>Upload Min. & Max. Levels</A> </TD></TR>");
//                                ps.println("               </TR>");
//
//                            }
//                            if (userFunctionalities.contains("399"))
//                            {
//                                ps.println("              <TR >");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed   href=\"ManageMinMax1\" target=\"right\" ><b>Manage Reorder Level</A> </TD></TR>");
//                                ps.println("               </TR>");
//                            }
//                            if (userFunctionalities.contains("400"))
//                            {
//                                ps.println("              <TR >");
//                                ps.println("                <TD  width=20>&nbsp;</TD>");
//                                ps.println("                <TD ><li><A class=genmed   href=\"v2_nhi_orderPartsToReorderLevel\" target=\"right\" ><b>Add Parts to Order Cart</A> </TD></TR>");
//                                ps.println("               </TR>");
//                            }
//                        }

                        ps.println("	</TBODY></TABLE></div>");
                        ps.println("</TD></TR>");
                    }
                    ///////////////////////////////////////////////////////////////////////////





//                    if (userFunctionalities.contains("391"))
//                    {
//                        ps.println("       <TR>");
//                        ps.println("          <TD  class=link><A  href=\"uploadISL\" target=\"right\" ><font color=#ffffff>ISL Ties</font></A></TD></TR>");
//                        ps.println("       <TR>");
//                        ps.println("          <TD  class=link><A  href=\"#\" onClick=\"showMe('Layer22','none');\"><font color=#ffffff>Quotation Management</font></A></TD></TR>");
//                        ps.println("  <TR >");
//                        ps.println("     <TD>");
//                        ps.println("        <div id=\"Layer22\" LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 style=\"display:none;position:static;width:auto\">");
//                        ps.println("        <TABLE cellSpacing=0 cellPadding=0  width=\"100%\" border=0>");
//                        ps.println("        <TBODY>");
//                        if (userFunctionalities.contains("392"))
//                        {
//                            ps.println("              <TR >");
//                            ps.println("                <TD  width=20>&nbsp;</TD>");
//                            ps.println("                <TD ><li><A class=genmed   href=\"CreateQuotation\" target=\"right\" ><b>Create Quotation</A> </TD></TR>");
//                            ps.println("               </TR>");
//                        }
//                        if (userFunctionalities.contains("393"))
//                        {
//                            ps.println("              <TR >");
//                            ps.println("                <TD  width=20>&nbsp;</TD>");
//                            ps.println("                <TD ><li><A class=genmed   href=\"ViewQuotations1\" target=\"right\" ><b>View Quotation</A> </TD></TR>");
//                            ps.println("               </TR>");
//                        }
//                        ps.println("	</TBODY></TABLE></div>");
//                        ps.println("</TD></TR>");
//                    }

                    // Inventory reports
                    //if (userFunctionalities.contains("362") && (userCode.equals("800001") || userCode.equals("230340") || userCode.equals("210507") || userCode.equals("210551") || userCode.equals("110604") || userCode.equalsIgnoreCase("nhi") || userCode.equalsIgnoreCase("11HR01")))
                    // if (userFunctionalities.contains("362"))
//                    if (userFunctionalities.contains("362") && invUserVec.contains(userCode))
//                    {
//                        ps.println("		  <TR>");
//                        ps.println("                <TD  class=link>");
//                        ps.println("                    <A  href=\"InvReportOptions\" target=\"right\" ><font color=#ffffff>Inventory Reports</font></A></TD></TR>");
//                    }
//
//                    // Update or upload parts bulletin
//                    if (userFunctionalities.contains("78"))
//                    {
//                        ps.println("		  <TR>");
//                        ps.println("                <TD  class=link>");
//                        ps.println("                    <A  href=\"SparesCommunicationUpdate\" target=\"right\" ><font color=#ffffff>Update Parts Circular</font></A></TD></TR>");
//                    }
//                    //Update Service Bulletin
//                    if (userFunctionalities.contains("396"))
//                    {
//                        ps.println("		  <TR>");
//                        ps.println("                <TD  class=link>");
//                        ps.println("                    <A  href=\"ServiceBulletinUpdate1\" target=\"right\" ><font color=#ffffff>Update Service Bulletin</font></A></TD></TR>");
//                    }
                    // New link for animation

//                    ps.println("       <TR>");
//                    ps.println("          <TD  class=link><A  href=\"#\" onClick=\"showMe('Layer15','none');\"><font color=#ffffff>Online Service Training </font></A></TD></TR>");
//                    ps.println("        <TR>");
//                    ps.println("          <TD>");
//                    ps.println("		<div id=\"Layer15\" style=\"display:none;position:static;width:auto\">");
//                    ps.println("		  <TABLE cellSpacing=0 cellPadding=0 width=\"100%\" >");
//                    ps.println("              <TBODY>");
//                    ps.println("		  <TR>");
//                    ps.println("                <TD  width=20>&nbsp;</TD>");
//                    ps.println("                <TD ><li><A class=genmed  href=\"http://" + server_name + "/ecatalogue/animation/index.html\" target=_blank><b>Fuel Injection Pump Timing (Animation) </A> </TD></TR>");
//                    ps.println("			    </TBODY></TABLE></div>");
//                    ps.println("		  </TD></TR>");

                }


                ps.println("            </TD></TR>");
                ps.println("        </TBODY></TABLE>");
                ps.println("</form>");
                ps.println("</body>");
                ps.println("</html>");
                stmt.close();

            }
            else
            {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
            }
        }
        catch (Exception e)
        {
            ps.println(e);
        }
        finally
        {
            ps.close();
            wps.close();
        }

    }
}
