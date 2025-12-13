package viewEcat.comEcat;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import authEcat.UtilityMapkeys1;

public class ViewGroupImages extends HttpServlet {
    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");

        res.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
        res.setHeader("Expires", "0");
        res.setHeader("Pragma", "no-cache");
        HashMap<String, String> part_SequenceMap = null;

        try {
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
            String userCode = (String) session.getValue("userCode");
            String contextPath = req.getContextPath();
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");

            ///////////////////////////// CREATE SESSION /////////////////////////////

            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();


            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            //ps.println(header);
            if (session_id.equals(getSession)) {

                Connection conn = holder.getConnection();
                String group = req.getParameter("group");
                String aggregate = req.getParameter("aggregate");
                String engineModel = req.getParameter("engineModel");
                String engineSeries = req.getParameter("engineSeries");
                //String model = req.getParameter("model");
                 String svgName = "/" + group + ".svg";
                 String zoomimageName = contextPath + "/svg/" + svgName;
                GroupBom ob = new GroupBom(conn, ps, date_OR_serial, inputDate, serialNo, group, buckleDate);
                GroupBom ob2 = ob.getGroupBom();
                String linkPart1 = "";

                String[][] grp_arr = new String[ob2.print_data_counter][ob.no_array_parameters];
                int grp_arr_count = ob2.print_data_counter;

                for (int i = 0; i < grp_arr_count; i++) {
                    for (int aa = 0; aa < ob.no_array_parameters; aa++) {
                        grp_arr[i][aa] = ob2.print_data[i][aa];
                    }
                }

                String part_array[] = new String[grp_arr.length];
                int partCounter = 0;
                part_SequenceMap = new HashMap();
                for (int i = 0; i < grp_arr.length; i++) {
                    if (!grp_arr[i][5].equals("")) {
                        part_SequenceMap.put(grp_arr[i][5], grp_arr[i][0]);
                    }

                    if (grp_arr[i][1].equals("AP") || grp_arr[i][1].equals("AA")) {
                    } else {
                        linkPart1 = grp_arr[i][0].replaceAll("&nbsp;", "");
                        part_array[partCounter] = linkPart1;
                        partCounter++;
                    }
                }


//                String flag = req.getParameter("flag");
//                if (flag == null) {
//                    flag = "1";
//                }
                String group_imagesURL = object_pageTemplate.groupJPG();
//                String ecatURL = getServletContext().getRealPath("/");
//                File svgFile = new File(getServletContext().getRealPath("/") + "/svg/" + group + ".svg");//svgFile.exists()
//
//
//                String tempzoomsvg = ecatURL + "/tempzoomsvg/" + userCode;
//                String zoomsvgcode = ecatPATH + "/dealer/zoomfile_code/zoomfunction.svg";
//                File zoomsvgcodeFile = new File(zoomsvgcode);
//
//
//
//
//                File zoom_dest = new File(tempzoomsvg);
//
//                if (zoom_dest.exists()) {
//                    EAMGGroupDAO_R.deleteDir(zoom_dest);
//                }
//                zoom_dest.mkdir();
//
//
//                File output_zoom = new File(tempzoomsvg + svgName);
//                if (output_zoom.exists()) {
//                    output_zoom.delete();
//                }
//
//                FileChannel inputChannel = null;
//                FileChannel outputChannel = null;
//                try {
//                    inputChannel = new FileInputStream(zoomsvgcodeFile).getChannel();
//                    outputChannel = new FileOutputStream(output_zoom).getChannel();
//                    outputChannel.transferFrom(inputChannel, 0, inputChannel.size());
//                } catch (Exception e) {
//                    e.printStackTrace();
//                } finally {
//                    inputChannel.close();
//                    outputChannel.close();
//                }
//
//
//                if (output_zoom.exists()) {
//
//                    FileOutputStream out;
//                    PrintStream p;
//                    File svgFile1 = new File(tempzoomsvg, svgName);
//                    try {
//                        int vc = 1, svg_line_W = 1, svg_line_H = 1;
//                        // Vector lineVec = new Vector();
//                        FileReader fileReader = new FileReader(svgFile1);
//                        BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//                        String line = "";
//
//                        out = new FileOutputStream(new File(tempzoomsvg, svgName.replace(".svg", "out.svg")));
//                        p = new PrintStream(out);
//                        while ((line = bufferedReader.readLine()) != null) {
//                            if (line.indexOf("<image") != -1) {
//                                if (vc == 8) {
//                                    FileReader fileReader1 = new FileReader(svgFile);
//                                    BufferedReader bufferedReader1 = new BufferedReader(fileReader1);
//                                    String line1 = "";
//                                    while ((line1 = bufferedReader1.readLine()) != null) {
//                                        if (line1.indexOf("<?xml") != -1 || line1.indexOf("<!-- Generator") != -1 || line1.indexOf("<!DOCTYPE") != -1) {
//                                        } else if (line1.indexOf("<svg") != -1 || svg_line_W > 0 || svg_line_H > 0) {
//                                            int widthfi = line1.indexOf("width=\"");
//                                            int widthli = line1.indexOf("\"", widthfi + 8);
//
//                                            int heightfi = line1.indexOf("height=\"");
//                                            int heightli = line1.indexOf("\"", heightfi + 8);
//                                            if (widthfi != -1 && heightfi != -1) {
//                                                String widths = line1.substring(0, (widthfi + 7)) + "460px" + line1.substring(widthli, widthli + 2);
//                                                String heights = line1.substring(heightfi, (heightfi + 8)) + "400px" + line1.substring(heightli, line1.length());
//                                                p.println(widths + heights);
//                                                svg_line_W = 0;
//                                                svg_line_H = 0;
//                                            } else if (widthfi != -1) {
//                                                String widths = line1.substring(0, (widthfi + 7)) + "460px" + line1.substring(widthli, widthli + 1);
//                                                p.println(widths);
//                                                svg_line_W = 0;
//                                            } else if (heightfi != -1) {
//                                                String heights = line1.substring(heightfi, (heightfi + 8)) + "400px" + line1.substring(heightli, line1.length());
//                                                p.println(heights);
//                                                svg_line_H = 0;
//                                            } else {
//                                                p.println(line1);
//                                            }
//                                            if (svg_line_W > 0) {
//                                                svg_line_W++;
//                                            }
//                                            if (svg_line_H > 0) {
//                                                svg_line_H++;
//                                            }
//                                        } else {
//                                            p.println(line1);
//                                        }
//
//                                    }
//
//                                } else {
//                                    p.println(line);
//                                }
//                                vc++;
//                            } else {
//                                p.println(line);
//                            }
//                        }
//                        bufferedReader.close();
//                        fileReader.close();
//                        out.close();
//                        p.close();
//                        svgFile1.delete();
//                    } catch (Exception e) {
//                        //e.printStackTrace();
//                    }
//                }
//



                /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
                ps.println("<html>");
                ps.println("<head>");

                ps.println("<title>");
                ps.println("" + UtilityMapkeys1.tile1 + " - E-CATALOG");
                ps.println("</title>");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");



//                ps.println("<style type=text/css>");
//                ps.println("<!--");
//
//                ps.println("			* { font-family: Helvetica; font-size: 8pt;}");
//                ps.println("a:link {				color: #000000;				text-decoration: none; }");
//                ps.println("a:visited { color: #000000;				text-decoration: none;		  }	");
//                ps.println("		-->");
//                ps.println("</style>");

                 ps.println("<script language=\"javascript\" src=\"" + contextPath + "/js/mysvg.js\">");
                ps.println("<script language=\"javascript\" src=\"" + imagesURL + "alttxt.js\">");

                ps.println("<script></script>");
                ps.println("<script language=JavaScript type=text/JavaScript>");


                ps.println("			function onTop()");
                ps.println("			{");
                ps.println("				self.focus();");
                ps.println("			}");


                ps.println("		function MM_openBrWindow(theURL,winName,features)");
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
                //ps.println("		alert(\"Your Pop-up Blocker is Enabled. Please Allow Pop ups to view this website.\");");
                ps.println("		alert(\"Your Pop-up Blocker is Enabled. Please Add our site http://" + server_name + " to your trusted sites.\");");
                //	ps.println("		location.href='v4_ltk_index';");
                ps.println("		parent.location.href = 'Logout'");
                ps.println("		return false;");
                ps.println("	} ");
                ps.println("	else ");
                ps.println("	{");
                ps.println("		myTest.close();");
                ps.println("		return true;");
                ps.println("	}");
                ps.println("}");



                //ashutosh kumar 05 may 2015
                ps.println("			function onMouseOutchangeColorFn(srno)");
                ps.println("			{ ");
                ps.println("if(parent.right_main.document.getElementById(srno)){");
                ps.println("var rowCount=parent.right_main.document.getElementById(srno).rowIndex;");
                ps.println("parent.right_main.document.getElementById('myTable').rows[rowCount].style.backgroundColor='';");
                ps.println("		}	}");


                //ashutosh kumar 05 may 2015
                ps.println("	function changeColorBOM(srno)");
                ps.println("		{ ");
                ps.println("if(parent.right_main.document.f1 && parent.right_main.document.f1.noOfRows){");
                ps.println("if(parent.right_main.document.getElementById(srno)){ ");
                ps.println("			parent.right_main.document.getElementById(srno).style.backgroundColor=\"#CCCCCC\";");
                ps.println("			parent.right_main.document.getElementById(srno).focus();");
                ps.println("var objWin=parent.right_main.document.getElementById(srno);");
                ps.println("var xPos = getOffset(objWin).left;  ");
                ps.println("var yPos = getOffset(objWin).top; ");
                //ps.println("parent.right_main.scrollTo(xPos+15,yPos+15);");
                ps.println("parent.right_main.document.getElementById('groupImageDiv').scrollTop=yPos;");
                ps.println("}		}}");

                //manish changes_08_06_2012



                ps.println(" function getOffset( el ) {");
                ps.println("        var _x = 0;");
                ps.println("        var _y = 0;");
                ps.println("        if( el && !isNaN( el.offsetLeft ) && !isNaN( el.offsetTop ) ) {");
                ps.println("            _x += el.offsetLeft - el.scrollLeft;");
                ps.println("            _y += el.offsetTop - el.scrollTop;");
                ps.println("            el = el.parentNode;");
                ps.println("        }");
                ps.println("        return {");
                ps.println("            top: _y, ");
                ps.println("            left: _x");
                ps.println("        };    ");
                ps.println("}");

               //ashutosh kumar 05 may 2015

                ps.println("function svg1(img){ ");
                ps.println("var a= img.getSVGDocument();");
                ps.println("			var returnList = new Array();");
                ps.println("			var nodes = a.getElementsByTagName('text');");
                ps.println("			var max = nodes.length;");
                ps.println("			 for ( var i = 0; i < max; i++ ) {");
                ps.println("                        if(nodes.item(i).getAttribute('id')!=null && nodes.item(i).getAttribute('id')!='')");
                ps.println("                        {");

                ps.println("                            onMouseOverfunc(i,a,nodes.item(i).getAttribute('id'));");
                ps.println("                            onMouseOutfunc(i,a,nodes.item(i).getAttribute('id'));");
                ps.println("                            onMouseClickfunc(i,a);");
                ps.println("                        }");
                ps.println("                     }");

                ps.println("			var nodes1 = a.getElementsByTagName('a');");
                ps.println("			var max1 = nodes1.length;");
                ps.println("			 for ( var k = 0; k < max1; k++ ) {");
                ps.println("                            nodes1.item(k).setAttribute('target','_blank');");
                ps.println("                     }");
                ps.println("                     }");

                 //ashutosh kumar 05 may 2015

                ps.println("function onMouseOverfunc(i,SVGRoot,id){ ");
                ps.println("	SVGRoot.getElementsByTagName('text').item(i).addEventListener('mouseover',");
                ps.println("					function()");
                ps.println("					{");
                ps.println("var SVGElementsArr=SVGRoot.getElementsByTagName('text');");
                ps.println("			 for ( var k = 0; k < SVGElementsArr.length; k++ ) {");
                ps.println("                        if(SVGElementsArr.item(k).getAttribute('id')==id)");
                ps.println("                        {");
                ps.println("	        var objectSVG=SVGElementsArr.item(k);");
                ps.println("		SVGRoot.getElementById(id).setAttribute('style', 'fill: red'); ");
                ps.println("		var fontSize=objectSVG.getAttribute('font-size');fontSize=parseInt(fontSize)*2;");
                ps.println("		SVGRoot.getElementById(id).setAttribute('font-size', fontSize);");
                ps.println("changeColorBOM(objectSVG.getAttribute('id'));");//changeColorBOMMouseOut(srno)
                ps.println("				}}	},");
                ps.println("					false); ");
                ps.println("} ");

                //ashutosh kumar 05 may 2015
                ps.println("function onMouseOutfunc(i,SVGRoot,id){ ");
                ps.println("	var objectSVG=SVGRoot.getElementsByTagName('text').item(i).addEventListener('mouseout',");
                ps.println("					function()");
                ps.println("					{");
                ps.println("var SVGElementsArr=SVGRoot.getElementsByTagName('text');");
                ps.println("			 for ( var k = 0; k < SVGElementsArr.length; k++ ) {");
                ps.println("                        if(SVGElementsArr.item(k).getAttribute('id')==id)");
                ps.println("                        {");
                ps.println("	var objectSVG=SVGElementsArr.item(k);");
                ps.println("		SVGRoot.getElementById(id).setAttribute('style', 'fill: black');  ");
                ps.println("		var fontSize=objectSVG.getAttribute('font-size');fontSize=parseInt(fontSize);");
                ps.println("		SVGRoot.getElementById(id).setAttribute('font-size', fontSize/2);");
                ps.println("onMouseOutchangeColorFn(objectSVG.getAttribute('id'));");//(srno)
                ps.println("				}}	},");
                ps.println("					false); ");
                ps.println("} ");


                ps.println("function onMouseClickfunc(i,SVGRoot){ ");
                ps.println("			SVGRoot.getElementsByTagName('text').item(i).addEventListener('click',");
                ps.println("					function()");
                ps.println("					{");
                ps.println("						MM_openBrWindow('RefNoDetails?partNo='+SVGRoot.getElementsByTagName('text').item(i).getAttribute('id')+'&XPCounter=1&group=" + group + "','REFDETAILS','width=700,height=605') ; ");
                ps.println("					},");
                ps.println("					false); ");
                ps.println("} ");






/*
                ps.println("function replaceSrc() {");
                ps.println("");
                ps.println("parent.document.all(\"right_middle\").src=\"ViewGroupImages?aggregate=" + aggregate + "&engineModel=" + engineModel + "&engineSeries=" + engineSeries + "&group=" + group + "\";");
                ps.println("}");
                ps.println("function help() {");
                ps.println("alert(' ZOOM-IN : Left Click + Select Area  \\n\\n DRAG : PRESS \\'D\\' + Left Click  \\n\\n ZOOM-OUT : PRESS \\'Z\\' + Left Click + Select Area ');");
                ps.println("}");
*/
                ps.println("</script>");
                ps.println("<style type=\"text/css\">");

                ps.println(".tooltip{");
                ps.println("	display: inline;");
                ps.println("position: relative;");
                ps.println("}");

                ps.println(".tooltip:hover:after{");
                ps.println("background: #333;");
                ps.println("background: rgba(0,0,0,.8);");
                ps.println("border-radius: 5px;");
                ps.println("bottom: 26px;");
                ps.println("color: #fff;");
                ps.println("content: attr(title);");
                ps.println("left: 20%;");
                ps.println("padding: 5px 15px;");
                ps.println("position: absolute;");
                ps.println("z-index: 98;");
                ps.println("width: 220px;");
                ps.println("}");

                ps.println(".tooltip:hover:before{");
                ps.println("border: solid;");
                ps.println("border-color: #333 transparent;");
                ps.println("border-width: 6px 6px 0 6px;");
                ps.println("bottom: 20px;");
                ps.println("content: \"\";");
                ps.println("	left: 50%;");
                ps.println("	position: absolute;");
                ps.println("z-index: 99;");
                ps.println("}");

                ps.println("</style>");
                ps.println("</head>");
                File chkFile = new File(getServletContext().getRealPath("/") + "/svg/" + group + ".svg");//svgFile.exists()
                if (chkFile.exists()) {
                    ps.println("<body topmargin=0  marginheight=0 leftmargin=0  marginwidth=0 onload=\"svg1(imghes)\">");
                } else {
                    ps.println("<body topmargin=0  marginheight=0 leftmargin=0  marginwidth=0 >");
                }
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

                ps.println("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=100%>");

                ps.println("<tr>");
                ps.println("	<td width=462  align=center>");
                if (chkFile.exists()) {
                    ps.println("<embed id=\"imghes\" src=\"" + zoomimageName + "\" class=\"electricalzoom\" type=\"image/svg+xml\" height=\"400\" width=\"460\">");
                    ps.println("</embed>");
                } else {
                    chkFile = new File(ecatPATH + "/dealer/ecat_print/group_jpg/" + group + ".jpg");
                    if (chkFile.isFile()) {
                        ps.println("<p align=center><IMG SRC=" + group_imagesURL + group + ".jpg width = 400 height = 460></p>");
                    } else {
                        ps.println("<p align=center><IMG SRC=" + group_imagesURL + "group_image.jpg width = 400 height = 460></p>");
                    }
                }
                ps.println("   </td>");
                ps.println("</tr>");
                 ps.println("	<tr>");
                ps.println("		<td align=center>");
                ps.println("			<table id=\"Table_02\" width=\"44\" border=\"0\" cellpadding=\"2\" cellspacing=\"2\">");
                ps.println("				<tr>");
                ps.println("					<td>");
                ps.println("						<a class=\"svg\" href=\"javascript:svgMove(imghes,-10,0)\">");
                ps.println("							<img src=\"" + imagesURL + "svgBar_01.png\" width=\"20\" height=\"20\" alt=\"MOVE IMAGE LEFT\" border=\"0\">");
                ps.println("						</a>");
                ps.println("					</td>");

                ps.println("					<td>");
                ps.println("						<a class=\"svg\" href=\"javascript:svgMove(imghes,0,10)\">");
                ps.println("							<img src=\"" + imagesURL + "svgBar_02.png\" width=\"20\" height=\"20\" alt=\"MOVE IMAGE DOWN\" border=\"0\">");
                ps.println("						</a>");
                ps.println("					</td>");

                ps.println("					<td>");
                ps.println("					<a class=\"svg\" href=\"javascript:svgZoom(imghes,0.7)\">");
                ps.println("							<img src=\"" + imagesURL + "svgBar_03.png\" width=\"20\" height=\"20\" alt=\"ZOOM IN IMAGE\" border=\"0\">");
                ps.println("						</a>");
                ps.println("					</td>");

                ps.println("					<td>");
                ps.println("						<a class=\"svg\" href=\"javascript:svgBase(imghes)\">");
                ps.println("							<img src=\"" + imagesURL + "svgBar_04.png\" width=\"20\" height=\"20\" alt=\"RESTORE IMAGE\" border=\"0\">");
                ps.println("						</a>");
                ps.println("					</td>");

                ps.println("					<td>");
                ps.println("						<a class=\"svg\" href=\"javascript:svgZoom(imghes,1.3)\">");
                ps.println("							<img src=\"" + imagesURL + "svgBar_05.png\" width=\"20\" height=\"20\" alt=\"ZOOM OUT IMAGE\" border=\"0\">");
                ps.println("						</a>");
                ps.println("					</td>");

                ps.println("					<td>");
                ps.println("						<a class=\"svg\" href=\"javascript:svgMove(imghes,0,-10)\">");
                ps.println("							<img src=\"" + imagesURL + "svgBar_06.png\" width=\"20\" height=\"20\" alt=\"MOVE IMAGE UP\" border=\"0\">");
                ps.println("						</a>");
                ps.println("					</td>");

                ps.println("					<td>");
                ps.println("						<a class=\"svg\" href=\"javascript:svgMove(imghes,10,0)\">");
                ps.println("							<img src=\"" + imagesURL + "svgBar_07.png\" width=\"20\" height=\"20\" alt=\"MOVE IMAGE RIGHT\" border=\"0\">");
                ps.println("						</a>");
                ps.println("					</td>");
                ps.println("				</tr>");
 /*
                ps.println("<tr>");
                ps.println("<td align=center>");
                ps.println("<table id=\"Table_02\" width=\"44\" border=\"0\" cellpadding=\"2\" cellspacing=\"2\">");
                ps.println("<tr>");
                ps.println("<td align='center'>");
                ps.println("<a class=\"svg\" href=\"javascript:replaceSrc()\">");
                ps.println("<img src=\"" + imagesURL + "refreshicon.png\" width=\"25\" height=\"22\" alt=\"Restore Image\" border=\"0\">");
                ps.println("</a>");
                ps.println("</td>");
                ps.println("<td align='center' class=\"textNormal\">");
                ps.println(" <a class=\"svg\" href=\"javascript:help()\">");
                ps.println(" <font class=\"links_txt\"><b>               			HELP</b></font>");
                ps.println("</a>");
                ps.println("</td>");
                ps.println("</tr>");
                ps.println("</table>");
                ps.println("</td>");
                ps.println("</tr>");
 */

                ps.println("</table>");

                ps.println("<form name=f1>");
                ps.println("<input type=hidden name=noOfParts value=0>");
                ps.println("</form>");
                ps.println("<script>");
                ps.println(" document.getElementById('loading').style.display='none';");
                ps.println("</script>");

                ps.println("</body></html>");


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
