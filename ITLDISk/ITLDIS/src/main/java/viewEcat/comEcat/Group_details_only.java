package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/*
File Name: Group_details_only.java
PURPOSE: To show the BOM of a group and highlighting the part in Group Image.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak Mangal		$$0 Created
 */
import authEcat.UtilityMapkeys1;

public class Group_details_only extends HttpServlet {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");

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

            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();




            if (session_id.equals(getSession)) {
                Connection conn = holder.getConnection();
                Statement stmt1 = conn.createStatement();
                //Statement stmt2 = conn.createStatement();
                PreparedStatement stmt = null;
                ResultSet rs1 = null;

                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));

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

                String group = req.getParameter("group");
                String model = req.getParameter("model");
                String partSearch = req.getParameter("partSearch");

                GroupBom ob = new GroupBom(conn, ps, date_OR_serial, inputDate, serialNo, group, buckleDate);
                GroupBom ob2 = ob.getGroupBom();
                //Get_groupInfo ob_get_grp_inf = new Get_groupInfo();
                //int revNo = 0;
                //Get_groupInfo ob_get_grp_inf = new Get_groupInfo(conn, ps, date_OR_serial, inputDate, serialNo, group);
                //int revNo = ob_get_grp_inf.get_groupRevisionNo();

               // System.out.println("no. of array " + ob.no_array_parameters);
                String[][] grp_arr = new String[ob2.print_data_counter][ob.no_array_parameters];
                int grp_arr_count = ob2.print_data_counter;

//                String var_REF_NO = "";
//                String var_DESCRIPTION = "";
//                String var_SERVICEABLE = "";
//                String var_REMARK = "";
//
//                String var_HEADER_DESCRIPTION = "";
                ArrayList<String> enabledList = new ArrayList();

                //rs1 = stmt2.executeQuery("select part_no from CAT_PART where p4='Y' or p4='Yes'");
                String query = ("select part_no from CAT_PART(NOLOCK) where p4='Y' or p4='Yes'");
                stmt = conn.prepareStatement(query);
                rs1 = stmt.executeQuery();
                while (rs1.next()) {
                    enabledList.add(rs1.getString(1).trim());
                }
                rs1.close();
                for (int i = 0; i < grp_arr_count; i++) {

                    for (int aa = 0; aa < ob.no_array_parameters; aa++) {
                        grp_arr[i][aa] = ob2.print_data[i][aa];
                        if (grp_arr[i][aa] == null) {
                            grp_arr[i][aa] = " &nbsp;";
                        }
                    }


                }

                ps.println("<html>");
                ps.println("   <head>");
                ps.println("      <title>");
                ps.println("         " + UtilityMapkeys1.tile1 + "");
                ps.println("      </title>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
                ps.println("<script language=JavaScript type=text/JavaScript src='" + imagesURL + "/js/overlib.js'></script>");
                ps.println("<script language=JavaScript type=text/JavaScript src='" + imagesURL + "/js/overlib_hideform.js'></script>");

                ps.println("<script language=JavaScript type=text/JavaScript>");

                //new changes_manish_06_02_2012
                ps.println("	function MM_openBrWindow(theURL,winName,features)");
                ps.println("		{ ");
                ps.println("			var isValidPopUpBlocker=detectPopupBlocker()");
                ps.println("			if (isValidPopUpBlocker== false)");
                ps.println("			{");
                ps.println("				return");
                ps.println("			}");
                ps.println("			var win=window.open(theURL,winName,features);");
                ps.println("                    win.focus();");
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
                ps.println("function LTrim(str)");
                ps.println("{");
                ps.println("	if(str==null)");
                ps.println("	{");
                ps.println("		return null;");
                ps.println("	}");
                ps.println("	for(var i=0;str.charAt(i)==\" \";i++);");
                ps.println("	return str.substring(i,str.length);");
                ps.println("}");
                ps.println("function RTrim(str)");
                ps.println("{");
                ps.println("	if(str==null)");
                ps.println("	{");
                ps.println("		return null;");
                ps.println("	}");
                ps.println("	for(var i=str.length-1;str.charAt(i)==\" \";i--);");
                ps.println("	return str.substring(0,i+1);");
                ps.println("}");

                ps.println("function Trim(str)");//Trim function

                ps.println("{");
                ps.println("	return LTrim(RTrim(str));");
                ps.println("}");
                ps.println("function LTrimAll(str)");
                ps.println("{");
                ps.println("	if(str==null)");
                ps.println("	{");
                ps.println("		return str;");
                ps.println("	}");
                ps.println("	for(var i=0;str.charAt(i)==\" \" || str.charAt(i)==\"\\n\" || str.charAt(i)==\"\\t\";i++);");
                ps.println("	return str.substring(i,str.length);");
                ps.println("}");
                ps.println("function RTrimAll(str)");
                ps.println("{");
                ps.println("	if(str==null)");
                ps.println("	{");
                ps.println("		return str;");
                ps.println("	}");
                ps.println("	for(var i=str.length-1;str.charAt(i)==\" \" || str.charAt(i)==\"\\n\" || str.charAt(i)==\"\\t\";i--);");
                ps.println("	return str.substring(0,i+1);");
                ps.println("}");
                ps.println("function TrimAll(str)");
                ps.println("{");
                ps.println("	return LTrimAll(RTrimAll(str));");
                ps.println("}");


                ps.println(" function getOffset( el ) {");
                ps.println("        var _x = 0;");
                ps.println("        var _y = 0;");
                ps.println("        while( el && !isNaN( el.offsetLeft ) && !isNaN( el.offsetTop ) ) {");
                ps.println("            _x += el.offsetLeft - el.scrollLeft;");
                ps.println("            _y += el.offsetTop - el.scrollTop;");
                ps.println("            el = el.parentNode;");
                ps.println("        }");
                ps.println("        return {");
                ps.println("            top: _y, ");
                ps.println("            left: _x");
                ps.println("        };    ");
                ps.println("}");



                ps.println("	function onMouseOverFn(rowCount)");
                ps.println("	{ ");
                ps.println("document.getElementById('myTable').rows[rowCount].style.backgroundColor='#CCCCCC';");
                ps.println("	}");


                ps.println("			function onMouseOutFn(rowCount)");
                ps.println("			{ ");
                ps.println("document.getElementById('myTable').rows[rowCount].style.backgroundColor='';");
                ps.println("			}");


               //ashutosh kumar 05 may 2015

                ps.println("function changeColor(seqNo,rowCount){ ");
                ps.println("var trObject=document.getElementById(seqNo);");
                ps.println("if(trObject){");

                ps.println("document.getElementById(seqNo).style.backgroundColor='#fbe5be';");//#333333
                ps.println("}");
                ps.println("if(parent.right_middle.imghes){");
                ps.println("onMouseOverFn(rowCount);");
                ps.println("var a= parent.right_middle.imghes.getSVGDocument();");
                ps.println("			var returnList = new Array();");
                ps.println("			var nodes = a.getElementsByTagName('text');");
                ps.println("			var max = nodes.length;");
                ps.println("			 for ( var i = 0; i < max; i++ ) {");
                ps.println("                        if(nodes.item(i).getAttribute('id')!=null && nodes.item(i).getAttribute('id')==seqNo)");
                ps.println("                        {");
                ps.println("                            changeColorImage(i,a,seqNo);");
                ps.println("                        }");
                ps.println("                     }");
                ps.println("       }         }  ");




                //ashutosh kumar 05 may 2015
                ps.println("function resetChangeColor(seqNo,rowCount){ ");
                ps.println("var trObject=document.getElementById(seqNo);");
                ps.println("if(trObject){");
                ps.println("document.getElementById(seqNo).style.backgroundColor='';");
                ps.println("}");
                ps.println("if(parent.right_middle.imghes){");
                ps.println("onMouseOutFn(rowCount);");
                ps.println("var a= parent.right_middle.imghes.getSVGDocument();");
                ps.println("			var returnList = new Array();");
                ps.println("			var nodes = a.getElementsByTagName('text');");
                ps.println("			var max = nodes.length;");
                ps.println("			 for ( var i = 0; i < max; i++ ) {");
                ps.println("                        if(nodes.item(i).getAttribute('id')!=null && nodes.item(i).getAttribute('id')==seqNo)");
                ps.println("                        {");
                ps.println("                            resetColorImage(i,a,seqNo);");
                ps.println("                        }");
                ps.println("                     }");
                ps.println("            }         }");


                //ashutosh kumar 05 may 2015
                ps.println("function changeColorImage(i,SVGRoot,seqNo){ ");
                ps.println("	var svgObject=	SVGRoot.getElementsByTagName('text').item(i); ");
                ps.println("	 SVGRoot.getElementById(seqNo).setAttribute('style', 'fill: red'); ");
                ps.println("		var fontSize=svgObject.getAttribute('font-size');fontSize=parseInt(fontSize)*2;");
                ps.println("		SVGRoot.getElementById(seqNo).setAttribute('font-size', fontSize);");
                ps.println("} ");



                //ashutosh kumar 05 may 2015
                ps.println("function resetColorImage(i,SVGRoot,seqNo){ ");
                ps.println("	var svgObject=	SVGRoot.getElementsByTagName('text').item(i); ");
                ps.println("	SVGRoot.getElementById(seqNo).setAttribute('style', 'fill: black');  ");
                ps.println("		var fontSize=svgObject.getAttribute('font-size');fontSize=parseInt(fontSize)/2;");
                ps.println("		SVGRoot.getElementById(seqNo).setAttribute('font-size', fontSize);");
                ps.println("} ");



                ps.println("	function getQTY(partNo,rowCount,seqNo,moq)");
                ps.println("	{ ");
                ps.println("     var flag=\"addOrderAjax\"");
                ps.println("		var qtyObject = document.getElementById(\"OrderQty\"+rowCount);");
                ps.println("		var qty = qtyObject.value;");
                ps.println("		if (qty==null)");
                ps.println("		{");
                ps.println("		alert('This Part Will Not Be Added To Order.');");
                ps.println("		}");
                ps.println("		else if ((qty=='') || (qty.indexOf(' ')!=-1))");
                ps.println("		{");
                ps.println("			alert('Please enter the Quantity');");
                ps.println("			qtyObject.focus();return false;");
                ps.println("		}");
                ps.println("		else if ((isNaN(qty)) || (qty.indexOf('.')!=-1))");
                ps.println("		{");
                ps.println("			alert('Quantity should be Numeric.');");
                ps.println("			qtyObject.focus();return false;");
                ps.println("		}");
                ps.println("			else if (Number(qty)==0)");
                ps.println("			{");
                ps.println("			alert('Quantity must be greater than Zero')");
                ps.println("			qtyObject.focus();return false;");
                ps.println("			}");
                ps.println("		else");
                ps.println("			{");
                ps.println("			addToCartOrder(flag,qty,partNo,seqNo);");
                ps.println("			}");
                ps.println("	}");


                ps.println("function addToCartOrder(flag,inputValue,partNo,seqNo)");
                ps.println("{");
                ps.println("    var strURL=null;");
                ps.println("    strURL=\"Add_2_cart?flag=\"+flag+\"&ordQty=\"+inputValue+\"&partNo=\"+partNo+\"&tm=\"+new Date().getTime();");
                ps.println("    var xmlHttp = GetXmlHttpObject();");
                ps.println("    xmlHttp.onreadystatechange = function()");
                ps.println("    {");
                ps.println("        stateChangedOrder(xmlHttp,seqNo);");
                ps.println("");
                ps.println("    };");
                ps.println("    xmlHttp.open(\"GET\", strURL, true);");
                ps.println("    xmlHttp.send(null);");
                ps.println("}");
                ps.println("");



                ps.println("function stateChangedOrder(xmlHttp,seqNo)");
                ps.println("{");
                ps.println("    var response=null;");
                ps.println("    if (xmlHttp.readyState == 4 || xmlHttp.readyState == \"complete\")");
                ps.println("    {");
                ps.println("        response=xmlHttp.responseText;");//alert(response.fontcolor('red'));
                ps.println("if(response.indexOf('ALREADY')!=-1){");//
                ps.println("   alert(response);   ");//
                ps.println("}");
                ps.println("else");
                ps.println("{");
                ps.println("    alert(response);     ");//
                ps.println("}");
                ps.println("    }");
                ps.println("}");

                ps.println("function GetXmlHttpObject()");
                ps.println("{");
                ps.println("    var objXmlHttp = null;");
                ps.println("    if (navigator.userAgent.indexOf('Opera') >= 0)");
                ps.println("    {");
                ps.println("        xmlHttp=new XMLHttpRequest();");
                ps.println("        return xmlHttp;");
                ps.println("    }");
                ps.println("    if (navigator.userAgent.indexOf('MSIE') >= 0)");
                ps.println("    {");
                ps.println("        var strName = 'Msxml2.XMLHTTP';");
                ps.println("        if (navigator.appVersion.indexOf('MSIE 5.5') >= 0)");
                ps.println("        {");
                ps.println("            strName = 'Microsoft.XMLHTTP';");
                ps.println("        }");
                ps.println("        try");
                ps.println("        {");
                ps.println("            objXmlHttp = new ActiveXObject(strName);");
                ps.println("            // objXmlHttp.onreadystatechange = handler;");
                ps.println("            return objXmlHttp;");
                ps.println("        }");
                ps.println("        catch(e)");
                ps.println("        {");
                ps.println("            alert('Your Browser Does Not Support Ajax');");
                ps.println("            return;");
                ps.println("        }");
                ps.println("    }");
                ps.println("    if (navigator.userAgent.indexOf('Mozilla') >= 0)");
                ps.println("    {");
                ps.println("        objXmlHttp = new XMLHttpRequest();");
                ps.println("        return objXmlHttp;");
                ps.println("    }");
                ps.println("}");
                ps.println("function validnumber(ev) {");
                ps.println("ev.returnValue = (ev.keyCode >= 48 && ev.keyCode <= 57);}");
                ps.println("	function MM_openBrWindow(theURL,winName,features)");
                ps.println("		{ ");
                ps.println("			var isValidPopUpBlocker=detectPopupBlocker()");
                ps.println("			if (isValidPopUpBlocker== false)");
                ps.println("			{");
                ps.println("				return");
                ps.println("			}");
                ps.println("			var win=window.open(theURL,winName,features);");
                ps.println("                    win.focus();");
                ps.println("		}");

                ps.println("</script>");


                ps.println("	 </HEAD>");
                ps.println("<body topmargin=0  marginheight=0 leftmargin=0  marginwidth=0>");

//                String groupMap = "";
//                rs1 = stmt1.executeQuery("SELECT MAP_NAME FROM MODEL_GROUP WHERE GROUP_NO = '" + group + "' and MODEL_NO='" + model + "' ");
//                if (rs1.next()) {
//                    groupMap = rs1.getString(1);
//                }
//                rs1.close();

                int widths1[] = new int[6];
                widths1[0] = 29;
                widths1[1] = 77;
                widths1[2] = 23;
                widths1[3] = 224;
                widths1[4] = 42;

                int widths[] = new int[6];
                widths[0] = 31;
                widths[1] = 78;
                widths[2] = 25;
                widths[3] = 228;
                widths[4] = 42;
                int rowCounter = 0;

                //********** PRINTING OF GROUP BOM STARTS **********//


                if (grp_arr_count > 0) {

                    ps.println("<table  cellspacing=0 cellpadding=0 border=0 width=512px;><tr><td align=left>");//bordercolor=#D7D7D7
                    ps.println("<table  cellspacing=0 cellpadding=1 border=0 width=512px; >");//bordercolor=#D7D7D7
                    ps.println("<tr>");
                    ps.println("<td align = center width=" + widths1[0] + "px;  class=heading_BOLD_1ST>");
                    ps.println("<b>");
                    ps.println("#");
                    ps.println("</b>");
                    ps.println("</td>");

                    ps.println("<td   width=" + widths1[1] + "px;  class=heading_BOLD>");
                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.partNo", session));
                    ps.println("</td>");

                    ps.println("<td align = center   width=" + widths1[2] + "px; class=heading_BOLD>");
                    ps.println("<IMG SRC=" + imagesURL + "perceedence.gif border = 0 ALT='Remarks, Interchangeability & Cut Off'>");
                    ps.println("</td>");

                    ps.println("<td    width=" + widths1[3] + "px;     class=heading_BOLD>");
                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.description", session));
                    ps.println("</td>");

//                    ps.println("<td align = center   width=" + widths1[5] + "px;  class=heading_BOLD>");
//                    ps.println("<IMG SRC=" + imagesURL + "red.gif border = 0 ALT='Interchangeability & Remarks'>");
//                    ps.println("</td>");

                    ps.println("<td align = center   width=" + widths1[4] + "px;  class=heading_BOLD>");
                    ps.println(ComUtils.getLanguageTranslation("label.catalogue.quantity", session));
                    ps.println("</td>");
                    if (userFunctionalities.contains("34")) {


                        ps.println("<td  align = center    class=\"heading_BOLD\">");
                        ps.println(ComUtils.getLanguageTranslation("label.catalogue.orderQuantity", session));
                        ps.println("</td>");

                    }

                    ps.println("</tr></table></td><tr><td style='padding-top:0px;' valign=top align=left>");
                    ps.println("<div id='groupImageDiv' style=\"  height: 365px;  overflow: auto; \"> ");
                    ps.println("<table id = 'myTable' cellspacing=0 cellpadding=0 style=\"border:solid 1px #D7D7D7\"; border=0 width=100%;>");//bordercolor=#D7D7D7
                    //rowCounter++;

                }

                int Checker = 1;

                Vector superVec = new Vector();
                String linkPart = "";
                String winName = "";
                String printText = "";
                String tempSeq = "";
                int cnt = 0;
                String cssClass = null;
                String cssClass1 = null;

                ps.println("<a href='#bottom' id='gotoIndex' style='display:none;'>##</a>");////////////auto scroll

                for (int i = 0; i < grp_arr_count; i++) {
                    linkPart = grp_arr[i][0].replaceAll("&nbsp;", "");
                    winName = linkPart.replaceAll("-", "");
                    printText = "";


                    if (i % 2 == 0) {
                        cssClass = "small_left_right";//small_left_right_Alt
                        cssClass1 = "small_right";//small_right_Alt
                    } else {
                        cssClass = "small_left_right";
                        cssClass1 = "small_right";
                    }


                    printText = printText + "<a href=javascript:MM_openBrWindow('" + servletURL + "PartDetails?partNo=" + linkPart + "','DETAILS','scrollbars=yes,width=700,height=605')>";
//                    
                    if (!(partSearch == null) && (winName.equals(partSearch))) {
                        printText = printText + "<font color=#3300FF>";
                    }


                    if (grp_arr[i][14].equals("YES")) {
                        printText = printText + "";
                    }

                    if (grp_arr[i][8].equals("YES")) {
                        ps.println("<tr>");
                        ps.println("<td align = center width=50px; height=27px>");
                        ps.println("&nbsp;");
                        ps.println("</td>");

                        ps.println("<td align = center width=70px; height=27px>");
                        ps.println("&nbsp;");
                        ps.println("</td>");

                        ps.println("<td width=20px; height=27px>");
                        ps.println("&nbsp;");
                        ps.println("</td>");

                        ps.println("<td align = center width=180px; height=27px>");
                        ps.println("&nbsp;");
                        ps.println("</td>");

                        ps.println("<td width=50px; height=27px><b>");
                        ps.println(grp_arr[i][13]);
                        ps.println("</td>");

                        if (!userFunctionalities.contains("16")) {
                            ps.println("<td width=21% height=27px>");
                            ps.println("&nbsp;");
                            ps.println("</td>");
                            ps.println("</tr>");
                        }
                        ps.println("</tr>");
                        rowCounter++;
                    }

                    if (grp_arr[i][1].equals("AP") || grp_arr[i][1].equals("AA")) {
                        ps.println("<tr   bordercolor = #CCCCCC>");
                    }//
                    else {
                        ps.println("<tr id='" + (grp_arr[i][5]) + "' onmouseover=\"changeColor('" + (grp_arr[i][5]) + "','" + rowCounter + "')\"  onmouseout=\"resetChangeColor('" + (grp_arr[i][5]) + "','" + rowCounter + "');\"  bordercolor = #CCCCCC>");
                    }//

                    ps.println("<td align = center height=27px  style=\"word-wrap: break-word\"  width=" + widths[0] + "px;  class=" + cssClass + ">");
                    if (grp_arr[i][1].equals("AP") || grp_arr[i][1].equals("AA")) {
                        ps.println(grp_arr[i][1]);
                    } else {
                        ps.println("<a href='#' style='text-decoration=none' name='" + grp_arr[i][5] + "'>");////////////auto scroll
                        /*if (tempSeq.equals(grp_arr[i][5])) {
                        cnt++;
                        ps.println(grp_arr[i][5] + "-" + cnt);
                        } else {
                        tempSeq = grp_arr[i][5];
                        cnt = 0;
                         */ ps.println(grp_arr[i][5]);
                        //}
                        //ps.println(Checker);
                        ps.println("</a>");////////////auto scroll

                        Checker++;
                    }
                    ps.println("</td>");

                    ps.println("<td  align=left height=27px width=" + widths[1] + "px; style=\"word-wrap:break-word\" class=" + cssClass1 + ">");
                    ps.println(printText);

                    if (grp_arr[i][0].startsWith("TBA")) {
                        ps.println(PageTemplate.tbaPart);
                    } else {
                        ps.println(grp_arr[i][0].replaceAll("_", "&nbsp;"));
                    }
                    ps.println("");
                    ps.println("</td>");

                    ps.println("<td width=" + widths[2] + "px; height=27px  align = center style=\"word-wrap:break-word\" class=" + cssClass1 + ">");
//                    if (grp_arr[i][2].equals("YES")) {
                    if (date_OR_serial.equals("latest")) {
                        if (grp_arr[i][16].equals("-") && grp_arr[i][17].equals("-") && grp_arr[i][18].equals("-") && (grp_arr[i][12] == null || grp_arr[i][12].equals(""))) {
                            ps.println("&nbsp;");
                        } else {
//                            ps.println("<a href=javascript:MM_openBrWindow('" + servletURL + "CompChangeDetails?component=" + linkPart + "&groupNo=" + grp_arr[i][6] + "','CHANGEMGMT','scrollbars=yes,width=650,height=500')>");
                            ps.println("<a href=javascript:MM_openBrWindow('" + servletURL + "GroupDetail?component=" + linkPart + "&groupNo=" + grp_arr[i][6] + "&index=" + grp_arr[i][19] + "','interchangeability','scrollbars=yes,width=670,height=220')>");
                            ps.println("<IMG SRC=" + imagesURL + "perceedence.gif border = 0 alt='Interchangeability & Remarks'>");
                            ps.println("</a>");
                        }
                    } else {
                        ps.println("&nbsp;");
                    }
//                    } else {
//                        ps.println("&nbsp;");
//                    }
                    ps.println("</td>");

                    ps.println("<td  width=" + widths[3] + "px; height=27px align=left style=\"word-wrap:break-word\" class=" + cssClass1 + " title='" + grp_arr[i][10] + "' >");

                    if (grp_arr[i][10].equals("")) {
                        ps.println("&nbsp;");
                    } else {
                        ps.println(grp_arr[i][10]);
                    }
                    ps.println("</td>");

                    /* ps.println("<td align=center  style=\"word-wrap:break-word\" class=links_txt >");
                    ps.println(grp_arr[i][11]);
                    ps.println("</td>");
                     */
//                    ps.println("<td align = center   width=" + widths[5] + "px; valign=middle  style=\"word-wrap: break-word\" class=" + cssClass1 + ">");
//                    if(i == 0){
//                    String popuupSb ="INTERCHANGEABILITY : Yes <br>REMARKS : ENGINE ASSY<br>CUT OFF : 30-Jun-2014<br>";
//                    ps.println("<IMG SRC=" + imagesURL + "red.gif border = 0 ALT='' style=\"z-index:0;\" border=0  onmouseover=\"return overlib('" + popuupSb + "',CAPTION, 'INTERCHANGEABILITY', WIDTH, 160);\" onmouseout=\"return nd();\"/>");
//                    }else{
//                    ps.println("&nbsp;");
//                    }
//                    ps.println("</td>");

                    ps.println("<td width=" + widths[4] + "px; height=27px align=center  style=\"word-wrap:break-word\" class=" + cssClass1 + ">");
                    if (grp_arr[i][7].equals("YES")) {
                        ps.println("AR");
                    } else {
                        ps.println("&nbsp;" + grp_arr[i][3]);
                    }
                    ps.println("</td>");

                    if (userFunctionalities.contains("34")) {

                        ps.println("<td   width=" + widths[5] + "px;  align=center class=\"small_right\"><table cellspacing=0 width=" + widths[5] + "px;  cellpadding=0  border=0><tr>");
                        {
                            ps.println("<td  class=\"links_txt\">");
                            ps.println("<input type=\"text\"  name=\"orderQty\" class=\"textBox\" id=\"OrderQty" + rowCounter + "\"   onkeypress=\"validnumber(event);\"  maxlength=\"4\" size=4>");//onblur=\"validnumber(event);\"
                            ps.println("</td>");

                            ps.println("<td   class=\"links_txt\">");
                            if (enabledList.indexOf(linkPart) != -1) {
                                ps.println("<img border=0   src=\"" + imagesURL + "add-to-cart.png\"  alt='Add to Cart' onclick=\"getQTY('" + linkPart + "','" + rowCounter + "','" + grp_arr[i][5] + "','');\"/>");//'" + (grp_arr[i][5]) + "'
                            } else {
                                ps.println("<img border=0   src=\"" + imagesURL + "add-to-cart-disable.png\"  alt='Part is not Servicable'  />");//'" + (grp_arr[i][5]) + "'
                            }
                            //ps.println("<img border=0   src=\"" + imagesURL + "add-to-cart.png\"  alt='Add to Cart' onclick=\"getQTY('" + linkPart + "','" + rowCounter + "','" + grp_arr[i][5] + "','');\"/>");//'" + (grp_arr[i][5]) + "'
                            ps.println("</td>");
                        }
                        ps.println("</tr></table></td>");


                    }

                    ps.println("</tr>");
                    rowCounter++;

                    /**
                     * ***********************************************
                     */
                }


                ps.println("</table></div></td></tr>");

                ps.println("	<tr>");
                ps.println("		<td class=links_txt align=left><br>");
                ps.println("	<font color=grey><i>Note:If Image is not visible, then <a href=" + imagesURL + "SVGView.exe>Click Here</a> to install SVG Viewer.</font></td>");
                ps.println("		</td></tr>");

                ps.println("</table>");

                ps.println("<form name=f1 id=f1>");
                ps.println("<input type=hidden name=noOfRows value=" + grp_arr.length + ">");
                //System.out.println("grp_arr.length..."+grp_arr.length);
                ps.println("</form>");
                ps.println("</body></html>");
                ps.println("<script>");
                ps.println(" document.getElementById('loading').style.display='none';");
                ps.println("</script>");
                stmt1.close();

            } else {
                object_pageTemplate.ShowMsg(ps, "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", "Index", "", imagesURL);
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
