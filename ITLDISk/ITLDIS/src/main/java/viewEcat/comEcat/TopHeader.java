package viewEcat.comEcat;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.JspFactory;
import javax.servlet.jsp.PageContext;

/*
File Name: TopHeader.java
PURPOSE: Shows the top Header.
HISTORY:
DATE		BUILD		AUTHOR				MODIFICATIONS
NA			v3.4		Deepak MAngal		$$0 Created
 */
import authEcat.UtilityMapkeys1;

public class TopHeader extends HttpServlet {

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res)  throws ServletException, IOException {
        PrintStream wps = new PrintStream(res.getOutputStream());
        PrintWriter ps = new PrintWriter(wps, true);
        res.setContentType("text/html");
        try {
            ///////////////////////////// CREATE SESSION /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            HttpSession session = req.getSession(true);
            String session_id = session.getId();
            res.setContentType("text/html;charset=UTF-8");

            javax.servlet.jsp.tagext.JspTag jsptag=null;
            JspFactory _jspxFactory = JspFactory.getDefaultFactory();
            PageContext pageContext = _jspxFactory.getPageContext(this, req, res,null, true, 8192, true);
            org.apache.jasper.runtime.TagHandlerPool taghandlerpool1 = org.apache.jasper.runtime.TagHandlerPool.getTagHandlerPool(getServletConfig());
            org.apache.struts.taglib.bean.MessageTag messagetag = (org.apache.struts.taglib.bean.MessageTag) taghandlerpool1.get(org.apache.struts.taglib.bean.MessageTag.class);
            messagetag.setPageContext(pageContext);
            messagetag.setKey("label.common.Service");
          //  System.out.println("messagetag"+messagetag);
            taghandlerpool1.reuse(messagetag);
         //   System.out.println("taghandlerpool1=="+taghandlerpool1);
          //  messagetag.setParent((javax.servlet.jsp.tagext.Tag) pageContext);

            // messagetag.setPageContext(_jspx_page_context);
           // messagetag.setParent((javax.servlet.jsp.tagext.Tag) _jspx_th_html_005flink_005f0);

            
         //   System.out.println("messagetag===="+messagetag.getProperty());
         //   System.out.println("messagetag===="+messagetag.getLocale());
         //   System.out.println("messagetag===="+messagetag.getName());
         //   System.out.println("messagetag===="+messagetag.getValue("label.common.Service"));
         //   System.out.println("messagetag===="+messagetag.getArg0());
         //   System.out.println("messagetag===="+messagetag.getBundle());
         //   System.out.println("messagetag===="+messagetag.getArg1());
            
           // System.out.println("messagetag===dostarttag="+messagetag.doStartTag());
            


            String getSession = (String) session.getValue("session_value");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String userCode = (String) session.getValue("userCode");
            String date_OR_serial = (String) session.getValue("date_OR_serial");
            String context = req.getContextPath();

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

            String loginAgain = "";
            loginAgain = object_pageTemplate.loginAgain();

            String login_Name = "--";


            ////////////////////////////////////////////////////////////////////////////////
            /////////////////////// TEMPLATE FUNCTIONS /////////////////////////////
            ////////////////////////////////////////////////////////////////////////////////

            if (session_id.equals(getSession)) {
                Connection conn = holder.getConnection();
                ////////////// RETERVING  USER FUNC. FROM SESSION //////////////

                Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));
                //
                //
                /*########################################*/
                String versionNo = "";
                ArrayList<String> msg = new ArrayList();

                //Statement stmt = null;
                PreparedStatement stmt = null;
                ResultSet rs = null;

                String qval = req.getParameter("q");


                if (qval == null) {
                    qval = "0";
                }
                try {
                    //stmt = conn.createStatement();
                   // rs = stmt.executeQuery("select USER_NAME from UM_user_check(NOLOCK) where user_id='" + userCode + "'");
                	String query = ("select USER_NAME from UM_user_check(NOLOCK) where user_id='" + userCode + "'");
                	stmt = conn.prepareStatement(query);
                	rs = stmt.executeQuery();
                    if (rs.next()) {
                        login_Name = rs.getString(1);
                    }
                    rs.close();

                    if ((login_Name == null) || login_Name.equals("")) {
                        login_Name = "--";
                    }
                    //rs = stmt.executeQuery("select cd_no,patch_no  from CAT_PATCH(NOLOCK) where cd_no in(select max(sno) from CAT_CD(NOLOCK))");
                    String query1 = ("select cd_no,patch_no  from CAT_PATCH(NOLOCK) where cd_no in(select max(sno) from CAT_CD(NOLOCK))");
                    stmt = conn.prepareStatement(query1);
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        versionNo = rs.getString(1) + "." + rs.getString(2);
                    }
                    rs.close();

                    msg = new ArrayList();

                    //rs = stmt.executeQuery("select msg_color,msg from GEN_MAIN_MESSAGE(NOLOCK) order by msg_no");
                    String query3 = ("select msg_color,msg from GEN_MAIN_MESSAGE(NOLOCK) order by msg_no");
                    stmt = conn.prepareStatement(query3);
                    rs = stmt.executeQuery();
                    while (rs.next()) {
                        String temp_color = rs.getString(1);
                        String temp_msg = rs.getString(2);

                        if ((temp_color != null) && (temp_msg != null)) {
                            msg.add(temp_color);
                            msg.add(temp_msg);
                        }
                    }
                    //rs.close();
                } catch (Exception ex) {
                    ex.printStackTrace();

                } finally {
                    try {
                        if (rs != null) {
                            rs.close();
                            ;
                            rs = null;
                        }
                        if (stmt != null) {
                            stmt.close();
                            ;
                            stmt = null;
                        }
                    } catch (Exception e) {
                        e.printStackTrace();

                    }
                }




                ps.println("<html>");
                ps.println("<head>");
                ps.println("<TITLE>" + UtilityMapkeys1.tile1 + "</TITLE>");
              //  ps.println("<%@taglib uri='http://struts.apache.org/tags-bean' prefix='bean'%><%@page contentType='text/html' pageEncoding='UTF-8"%>");
                ps.println("<TITLE>" + UtilityMapkeys1.tile1 + "</TITLE>");
                ps.println("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=iso-8859-1\">");
                ps.println("<link href=\"" + imagesURL + "style.css\" type=\"text/css\" rel=\"stylesheet\">");
                ps.println("<link href=\"" + imagesURL + "css/login.css\" type=\"text/css\" rel=\"stylesheet\">");
                ps.println("<style type=\"text/css\">");
                ps.println("<!--");
                ps.println("body {");
                ps.println("	margin-left: 0px;");
                ps.println("	margin-top: 0px;");
                ps.println("	margin-right: 0px;");
                ps.println("	margin-bottom: 0px;");
                ps.println("}");

                ps.println(".version{ font-family:Verdana, Arial, Helvetica, sans-serif;");
                ps.println("font-size:11px;");
                ps.println("font-weight:bold;");
                ps.println("color:#333333;}");
                ps.println("");
                ps.println(".version-txt{ font-family:Verdana, Arial, Helvetica, sans-serif;");
                ps.println("font-size:11px;");
                ps.println("color:#333333;}");

                ps.println("</style>");

                ps.println("<SCRIPT LANGUAGE=\"JavaScript\">");
                ps.println("function newImage(arg) {");
                ps.println("	if (document.images) {");
                ps.println("		rslt = new Image();");
                ps.println("		rslt.src = arg;");
                ps.println("		return rslt;");
                ps.println("	}");
                ps.println("}");


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
                ps.println("			window.open(theURL,winName,features);");
                ps.println("		}");
                ps.println("function detectPopupBlocker()");
                ps.println("{ ");
                ps.println("	var myTest = window.open(\"about:blank\",\"\",\"directories=no,height=100,width=100,menubar=no,resizable=no,scrollbars=no,status=no,titlebar=no,top=0,location=no\");");
                ps.println("	if (!myTest)");
                ps.println("	{");
                ps.println("		alert(\"Your Pop-up Blocker is Enabled. Please Add our site http://" + server_name + " to your trusted sites.\");");
                ps.println("		parent.parent.location.href = 'Logout'");
                ps.println("		return false;");
                ps.println("	} ");
                ps.println("	else ");
                ps.println("	{");
                ps.println("		myTest.close();");
                ps.println("		return true;");
                ps.println("	}");
                ps.println("}");




                ps.println("function changeImages() {");
                ps.println("	if (document.images && (preloadFlag == true)) {");
                ps.println("		for (var i=0; i<changeImages.arguments.length; i+=2) {");
                ps.println("			document[changeImages.arguments[i]].src = changeImages.arguments[i+1];");
                ps.println("		}");
                ps.println("	}");
                ps.println("}");

                ps.println("var preloadFlag = false;");
                ps.println("function preloadImages() {");
                ps.println("	if (document.images) {");
                ps.println("		header_06_ImageMap_01_over = newImage(\"" + imagesURL + "topHeader/header_06-ImageMap_01_over.jpg\");");
                ps.println("		preloadFlag = true;");
                ps.println("	}");
                ps.println("}");
                ps.println(" function Validate()");
                ps.println(" {   ");
                ps.println("   var iChars = '!@#$%^&()+=-[]\\\';,/{}|\":<>?'");
                ps.println("   searchText=document.search.search_text.value.replace('\\\\','/');");
                ps.println("     for (var i = 0; i < searchText.length; i++) ");
                ps.println("      { ");
                ps.println("        if (iChars.indexOf(searchText.charAt(i)) != -1) ");
                ps.println("          { ");
                ps.println("            alert (searchText.charAt(i)+' '+'Is Not Vaild.'); ");
                ps.println("            document.search.search_text.value='';");
                ps.println("            return false; ");
                ps.println("           } ");
                ps.println("       } ");
                ps.println("  }");
                ps.println(" function Validate1()");
                ps.println(" {   ");
                ps.println("   var iChars = '!@#$%^&()+=[]\\\';,/{}|\":<>?'");
                ps.println("   searchText=document.search.search_text.value.replace('\\\\','/');");
                ps.println("   for (var i = 0; i < searchText.length; i++) ");
                ps.println("     { ");
                ps.println("      if (iChars.indexOf(searchText.charAt(i)) != -1)");
                ps.println("        { ");
                ps.println("          alert (searchText.charAt(i)+' '+'Is Not Vaild.'); ");
                ps.println("           document.search.search_text.value='';");
                ps.println("           return false; ");
                ps.println("         } ");
                ps.println("       } ");
                ps.println("      document.search.submit()");
                ps.println(" }");

                ps.println(" function showValue(obj)");
                ps.println("{");
                ps.println("         var strURL =\"SignOut\"");
                ps.println("         xmlHttp = GetXmlHttpObject();");
                ps.println("         xmlHttp.onreadystatechange = function()");
                ps.println("         {");
                ps.println("             stateChanged(obj);");
                ps.println("         };");
                ps.println("       xmlHttp.open(\"GET\", strURL, false);");
                ps.println("       xmlHttp.send(null);");
                ps.println("       return;");
                ps.println(" }");

                ps.println("function stateChanged(obj)");
                ps.println(" {");
                ps.println("}");

                ps.println("function GetXmlHttpObject()");
                ps.println("{");
                ps.println("	var objXmlHttp = null;");
                ps.println("	if (navigator.userAgent.indexOf('Opera') >= 0)");
                ps.println("	{");
                ps.println("	xmlHttp=new XMLHttpRequest();");
                ps.println("	return xmlHttp;");
                ps.println("	}");
                ps.println("	if (navigator.userAgent.indexOf('MSIE') >= 0)");
                ps.println("	{");
                ps.println("            var strName = 'Msxml2.XMLHTTP';");
                ps.println("            if (navigator.appVersion.indexOf('MSIE 5.5') >= 0)");
                ps.println("            {");
                ps.println("		strName = 'Microsoft.XMLHTTP';");
                ps.println("            }");
                ps.println("	    try");
                ps.println("            {");
                ps.println("		objXmlHttp = new ActiveXObject(strName);");
                ps.println("		// objXmlHttp.onreadystatechange = handler;");
                ps.println("		return objXmlHttp;");
                ps.println("            }");
                ps.println("      	    catch(e)");
                ps.println("	    {");
                ps.println("		alert('Your Browser Does Not Support Ajax');");
                ps.println("		return;");
                ps.println("            }");
                ps.println("	}");
                ps.println("	if (navigator.userAgent.indexOf('Mozilla') >= 0)");
                ps.println("	{");
                ps.println("	    objXmlHttp = new XMLHttpRequest();");
                ps.println("            return objXmlHttp;");
                ps.println("	}");
                ps.println("}");

                ps.println("</SCRIPT>");

 ps.println("<style type=\"text/css\">");
               ps.println(" *html{margin:0 auto; padding:0}");
 ps.println("body{text-align:center; margin:0 auto; padding:0; font-family:Arial, Helvetica, sans-serif; font-size:12px; color:#000000; }");
 ps.println("a{text-decoration:none; color:#0085cf; outline:none; padding:0; margin:0}");
 ps.println("a:hover{text-decoration:underline;}");
 ps.println("img{border:none}");
 ps.println(".dealer{width:397px; height:92px; float:right;}");
 ps.println(".main{width:1002px; text-align:center; margin:0 auto; background:#ffffff; font-family:Arial, Helvetica, sans-serif; overflow: hidden;}");
 ps.println(".header{border-top:2px solid #078ed2;border-bottom:2px solid #ff8400; height:109px; padding:0px 15px 13px 15px; float:left; width:972px; margin:0}");
 ps.println(".header .logo{float:left; width:148px;}");
 ps.println(".header .logo img{margin-right:20px;}");
 ps.println(".header .logo .logoTag{margin-top:11px;}");
 ps.println(".header .topLinks{float:right; width:300px; text-align:right; color:#7f7f7f; border:0px solid red}");
 ps.println(".header .topLinks a{color:#7f7f7f}");
 ps.println(".header .topLinks .dealerTab{margin-top:10px; padding:30px 0 0; float:right; width:200px; text-align:left; font-size:10px; height:60px;}");
 ps.println(".header .topLinks .dealerTab a{border-top:1px solid #CCCCCC; text-align:right; padding:5px 0 0; display:block;}");
 ps.println(".header .topLinks .dealerTab strong{color:#000000; font-weight:normal;font-size:10px;}");
 ps.println(".header .topLinks .dealer span{display:none}");
 ps.println(".header .topLinks a.dealer{background:url(images/dealerLocator.gif) left top no-repeat; height:51px; padding:0; margin:0; width:210px; display:block;}");
 ps.println(".navbg{width:1002px; height:13px; background:url(images/topHeader/nav-bg.jpg) repeat-x; clear:both;}");
 ps.println(".content{margin-top:16px; float:left; width:972px; padding:0 15px 20px}");
 ps.println(".homeBanner{float:left; width:707px; height:305px;}");
 ps.println(".innerBanner{float:left; width:933px; height:155px;}");
 ps.println(".stockInfo{width:220px; height:300px; float:right; background-color:#0081cd; padding:5px 15px 0 15px;}");
 ps.println(".stockInfo h2{text-align:left; color:#fff; font-size:18px; margin:10px 0 0 0; padding:0px 0 10px 0; }");
 ps.println(".login{ max-width: 320px; clear:both; margin:0px 20px 50px 0; padding-bottom:10px; background: url(images/login_bg.jpg) repeat-x; overflow:hidden;  border-radius:10px; }");
 ps.println(".login h3{font-size:18px;  line-height:45px; width:100%; margin:0px; padding:0px 0 0 10px; color:#fff; border-bottom:solid 1px #ddd;}");
 ps.println(".left{width:210px; float:left;}");
 ps.println(".left p{font-size:13px; font-weight:bold; padding:0 0 0 5px; margin:10px 0; text-align:left; color:#fff; }");
 ps.println(".inputtext{width:200px; border-radius:5px; height:20px; background:#fff; border:solid 1px #e5e5e5; margin:0 0 0 5px; font-size:12px; padding:5px; font-weight:bold;  }");
 ps.println(".submit_new {width: 95px; height: 27px; background: url(loginImages/button_signin.gif) no-repeat 0 0; color: #FFF; font-size: 12px; cursor: pointer; border: none; text-align: center; margin: 15px 0 10px 18px;}");
 ps.println(".footerBottom{background-color:#ffffff; float:left; padding:15px; width:957px; font-size:11px}");
 ps.println(".footerBottom span.leftFooter{float:left; width:480px; text-align:left; line-height:22px; display:block; color:#999999}");
 ps.println(".footerBottom span.leftFooter a{color:#999999}");
 ps.println(".footerBottom span.leftFooter a:hover{color:#000000}");
 ps.println(".footerBottom span.leftFooter img{float:left; vertical-align:middle; margin:0 3px;}");
 ps.println(".footerBottom span.rightLink{float:right; text-align:right; width:200px; line-height:22px;}");
 ps.println(".header_main{width:1002px; height:76px; position:relative; }");
 ps.println(".navigation{width:1002px; height:33px; }");
 ps.println(".navleft{width:12px; height:33px; float:left; background:url("+ imagesURL + "topHeader/left_bar.jpg) no-repeat;}");
 ps.println(".navmid{width:978px; height:33px; background:url("+ imagesURL + "topHeader/navigation.jpg) repeat-x; float:left;}");
 ps.println(".navright{width:12px; height:33px; float:right; background:url("+ imagesURL + "topHeader/rightbar.jpg) no-repeat;}");
 ps.println("menu{width:500px; height:auto; float:left;}");
 ps.println(".menu ul{ list-style:none; margin:0px; padding:0px;}");
 ps.println(".menu li{float:left; line-height:33px; background: url(topHeader/divider.jpg) no-repeat right top }");
 ps.println(".menu a{padding:0 15px 0 15px; font-size:12px; text-decoration:none; color:#fff; display:block; font-weight:bold;}");
 ps.println(".menu a:hover{color:#f00; }");
 ps.println(".menu_right{ height:auto; float:right;}");
 ps.println(".menu_right ul{ list-style:none; margin:0px; padding:0px;}");
 ps.println(".menu_right li{ float:left; line-height:33px;  background: url(topHeader/divider.jpg) no-repeat right top; font-size:14px; font-weight:bold; text-decoration:none; color:#fff;}");
 ps.println(".menu_right a{ font-size:12px; padding:0 15px; text-decoration:none; color:#fff; display:block; font-weight:bold;}");
 ps.println(".menu_right a:hover{color:#f00; }");
 ps.println(".menu_right img{margin:3px 0 5px 0;}");
 ps.println(".icon1 {height: 40px; position:absolute; right:2px; top:5px;}");
 ps.println(".icon1 ul{margin:0px; padding:0px; list-style-type:none; }");
 ps.println(".icon1 li{float:left;  padding:0 2px; }");
 ps.println("img{border:none;}");
 ps.println(".marquee{width:900px; height:30px; position:absolute; right:0; top:60px; font-size:12px; font-weight:bold; text-align:left; color:#F00;}");
 ps.println("</style>");
                ps.println("</head>");
                ps.println("");
                ps.println("<body>");




                ps.println("<table width=\"1002\"  border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");




                ps.println("<tr>");
                ps.println("    <td colspan=\"3\" valign=\"top\" >");

                ps.println(" <div class=\"main\">");
                ps.println(" <div class=\"header_main\">");

                ps.println(" <div class=\"icon1\">");
                ps.println(" <ul>");

//                ps.println("  <li><a href=\"#\"><img src=\"" + imagesURL + "topHeader/contact.jpg\"  alt=\"contact\" title=\"Contact Us\" /></a></li>");
//                if (userFunctionalities.contains("300")) {
//                    ps.println(" <li><a href=\"InfoFrame\" target=\"right\"><img src=\"" + imagesURL + "topHeader/profile.jpg\"  alt=\"My Profile\" title=\"My Profile\" /></a></li>");
//                } else {
//                    ps.println("  <li><a href=\"#\" title=\"This functionality is not available.\"><img src=\"" + imagesURL + "topHeader/profile.jpg\"  alt=\"My Profile\" title=\"My Profile\" /></a></li>");
//                }
                //ps.println(" <li><a href=\"index.jsp\" target=\"_parent\"><img src=\"" + imagesURL + "topHeader/logout.jpg\"  alt=\"Sign Out\"  title=\"Sign Out\"/></a></li>");
                ps.println("<li><a href="+context+"/login.do?option=logout target=\"_parent\"><img src=\"" + imagesURL + "topHeader/logout.jpg\"  alt=\"Sign Out\"  title=\"Sign Out\"/></a></li>");
                ps.println(" </ul>");

                ps.println(" </div>");

                ps.println("<div class=\"marquee\">");
                if (msg.size() > 1)//ps.println("<font color=" + msg[j][0] + ">" + msg[j][1] + "</font><img src=" + imagesURL + "topHeaders/tractor.gif width=30 height=20>");
                {
                     ps.println("<marquee >");
                    for (int j = 0; j < msg.size(); j += 2) {
                        //if (!msg.get(j+1).trim().equals(""))
                        {
                            ps.print("<font color=" + msg.get(j) + "> " + msg.get(j + 1) + " </font> ");
                        }
                    }
                    ps.println("</marquee>");
                } else {
                    ps.print("<marquee >Welcome To ITL Dealer Information System.</marquee>");
                }
                ps.println("</div>");
                ps.println(" <img src=\"" + imagesURL + "topHeader/Header.jpg\" width=\"1002\" height=\"76\" alt=\"header\" /> </div>");

                ps.println(" </div>");
                ps.println(" </td>");
                ps.println("</tr>");
//                if (msg.size() > 1)//ps.println("<font color=" + msg[j][0] + ">" + msg[j][1] + "</font><img src=" + imagesURL + "topHeaders/tractor.gif width=30 height=20>");
//                {
//                    ps.println("<tr><td  style=\"background:#fff;\" class=\"version-txt\" colspan=3><marquee >");
//                    for (int j = 0; j < msg.size(); j += 2) {
//                        //if (!msg.get(j+1).trim().equals(""))
//                        {
//                            ps.print("<font color=" + msg.get(j) + "> " + msg.get(j + 1) + " </font> ");
//                        }
//                    }
//                    ps.println("</marquee></td>");
//                } else {
//                    ps.print("<td  class=\"version\" colspan=2><marquee >Welcome To ITL Dealer Information System.</marquee></td>");
//                }
               // ps.println("</tr>");
                ps.println("  <tr>");
                //  ps.println("    <td valign=\"top\"><img src=\"1.jpg\" width=\"3\" height=\"27\"></td>");
                ps.println("    <td width=\"1002\" align=\"center\" colspan=3 >");

                ps.println("<div class=\"navigation\">");
                ps.println("<div class=\"navleft\"></div>");
                ps.println("<div class=\"navmid\">");
                ps.println("<div class=\"nav\">");
                ps.println("<div class=\"menu\">");
                ps.println("<ul>");
                if (userFunctionalities.contains("2")) {
                    ps.println("<li><a href=\"View_EngineSeries\" target=\"right\">" + ComUtils.getLanguageTranslation("label.catalogue.products",session)+ "</a></li>");
                }
                if (userFunctionalities.contains("408")) {
                    ps.println("<li><a href=\"AllCompAvailable?compType=Tool\" target=\"right\">" + ComUtils.getLanguageTranslation("label.catalogue.tools",session)+ "</a></li>");
                   // ps.println("<li><a href=\"AllCompAvailable?compType=Tool\" target=\"right\">"+_jspx_th_bean_005fmessage_005f33.setKey("label.common.tools")+" </a></li>");
                }
                if (userFunctionalities.contains("48")) {
                    ps.println("<li><a href=\"AllCompAvailable?compType=Kit\" target=\"right\">" + ComUtils.getLanguageTranslation("label.catalogue.kits",session)+ "</a></li>");
                }
                if (userFunctionalities.contains("409")) {
                    ps.println("<li><a href=\"AllCompAvailable?compType=Lubes\" target=\"right\">"+ComUtils.getLanguageTranslation("label.catalogue.lubes", session)+"</a></li>");
                }
                if (userFunctionalities.contains("34")) {
                    ps.println("<li><a href=\"" + context + "/View_cart\"  target=\"right\">"+ComUtils.getLanguageTranslation("label.catalogue.cart", session)+"</a></li>");
                }
                if (userFunctionalities.contains("16")) {
                    ps.println("<li style=\"border:none;\"><a href=\"" + context + "/common/EAMG_admin.jsp\"  target=\"right\">"+ComUtils.getLanguageTranslation("label.catalogue.admin", session)+"</a></li>");
                }
                if (date_OR_serial.equals("latest") && userFunctionalities.contains("217")) {
                    ps.println("<li><a href=\"javascript:MM_openBrWindow('" + servletURL + "AdvancesearchOption_1','AdvanceSearch','resizable=no,scrollbars=no,width=430,height=250');\">"+ComUtils.getLanguageTranslation("label.catalogue.search", session)+"</a></li>");
                    ps.println("<li style=\"border:none;\"><a href=\"" + context + "/authJSP/webService/EAMG_Advance_Search.jsp?step=1\"  target=\"right\">"+ComUtils.getLanguageTranslation("label.catalogue.advanceSearch", session)+"</a></li>");
                }
                    if (userFunctionalities.contains("52")) {
                    ps.println("<li style=\"border:none;\"><a href=\"" + context + "/serviceBulletin.do?option=viewBulletin\"  target=\"right\">"+ComUtils.getLanguageTranslation("label.catalogue.bulletin", session)+"</a></li>");
                }


//                    //ps.println("        <td><img src=\"" + imagesURL + "topHeader/sep.jpg\" width=\"2\" height=\"25\"></td>");
//                    //ps.println("<td  align=\"left\" class=\"TXT_LINKS\"><a href=\"" + context + "/serviceBulletin.do?option=viewBulletin\"  target=\"right\">Bulletin</a></td>");
//                    //ps.println("        <td><img src=\"" + imagesURL + "topHeader/sep.jpg\" width=\"2\" height=\"25\"></td>");
//                    //ps.println("<td  align=\"left\" class=\"TXT_LINKS\"><a href=\"" + mainURL + "dealer/hlp/UserManual.pdf\"  target=\"_blank\">Help</a></td>");
//                }
                ps.println("</ul>");
                ps.println("</div>");

//                ps.println("<div class=\"menu_right\">");
////                ps.println("<ul>");
////                ps.println("<li><a href=\"#\">My Profile</a></li>");
////                ps.println("</ul>");
//                ps.println("</div>");
                ps.println("</div>");

                ps.println("</div>");
                ps.println("<div class=\"navright\"></div>");
                ps.println("</div>");

//                ps.println("   <table width=\"100%\"  border=\"1\" cellspacing=\"0\" cellpadding=\"0\">");
//
//                ps.println("      <tr>");
//                if (userFunctionalities.contains("2")) {
//                    ps.println("        <td><img src=\"" + imagesURL + "topHeader/sep.jpg\" width=\"2\" height=\"25\"></td>");
//                    ps.println("        <td width=\"60\" align=\"left\" class=\"TXT_LINKS\"><a href=\"View_EngineSeries\" target=\"right\">" + object_pageTemplate.ENGINE_SERIES + "s</a></td>");
//                }
//                if (userFunctionalities.contains("408")) {
//                    ps.println("        <td><img src=\"" + imagesURL + "topHeader/sep.jpg\" width=\"2\" height=\"25\"></td>");
//                    ps.println("        <td  width=\"40\" align=\"left\" class=\"TXT_LINKS\"><a href=\"AllCompAvailable?compType=Tool\" target=\"right\">Tools </a></td>");
//                }
//                if (userFunctionalities.contains("48")) {
//                    ps.println("        <td><img src=\"" + imagesURL + "topHeader/sep.jpg\" width=\"2\" height=\"25\"></td>");
//                    ps.println("        <td align=\"left\" class=\"TXT_LINKS\"><a href=\"AllCompAvailable?compType=Kit\" target=\"right\">Kits</a></td>");
//                }
//                if (userFunctionalities.contains("409")) {
//                    ps.println("        <td><img src=\"" + imagesURL + "topHeader/sep.jpg\" width=\"2\" height=\"25\"></td>");
//                    ps.println("        <td align=\"left\" class=\"TXT_LINKS\"><a href=\"AllCompAvailable?compType=Lube\" target=\"right\">Lubes </a></td>");
//                }
//                {
//                    //ps.println("        <td><img src=\"" + imagesURL + "topHeader/sep.jpg\" width=\"2\" height=\"25\"></td>");
//                    //ps.println("<td  align=\"left\" class=\"TXT_LINKS\"><a href=\"" + context + "/serviceBulletin.do?option=viewBulletin\"  target=\"right\">Bulletin</a></td>");
//                    //ps.println("        <td><img src=\"" + imagesURL + "topHeader/sep.jpg\" width=\"2\" height=\"25\"></td>");
//                    //ps.println("<td  align=\"left\" class=\"TXT_LINKS\"><a href=\"" + mainURL + "dealer/hlp/UserManual.pdf\"  target=\"_blank\">Help</a></td>");
//                }
//                if (userFunctionalities.contains("34")) {
//                    ps.println("        <td><img src=\"" + imagesURL + "topHeader/sep.jpg\" width=\"2\" height=\"25\"></td>");
//                    ps.println("<td  align=\"left\" class=\"TXT_LINKS\"><a href=\"" + context + "/View_cart\"  target=\"right\">Cart</a></td>");
//                }
//                if (userFunctionalities.contains("16")) {
//                    ps.println("        <td><img src=\"" + imagesURL + "topHeader/sep.jpg\" width=\"2\" height=\"25\"></td>");
//                    ps.println("<td align=\"left\" class=\"TXT_LINKS\"><a href=\"" + context + "/common/EAMG_admin.jsp\"  target=\"right\">Admin</a></td>");
//                }
//                if (date_OR_serial.equals("latest") && userFunctionalities.contains("217")) {
//                    ps.println("        <td><img src=\"" + imagesURL + "topHeader/sep.jpg\" width=\"2\" height=\"25\"></td>");
//                    ps.println("        <td align=\"right\"><img src=\"" + imagesURL + "topHeader/Search-icon_01.jpg\"  height=\"25\"></td>");
//                    ps.println("        <td  width=\"40\" align=\"right\" class=\"TXT_LINKS\"><a href=\"javascript:MM_openBrWindow('" + servletURL + "AdvancesearchOption_1','AdvanceSearch','resizable=no,scrollbars=no,width=430,height=250');\">Search</a></td>");
//                } else {
//                }
//                //ps.println("        <td width=\"350\">&nbsp;</td>");
//                //ps.println("<td> &nbsp;</td>");
//
////                if (userFunctionalities.contains("300")) {
////                    ps.println("        <td><img src=\"" + imagesURL + "topHeader/sep.jpg\"  height=\"25\"></td>");
////                    ps.println("        <td align=\"right\"><img src=\"" + imagesURL + "topHeader/profile-icon.jpg\" height=\"25\"></td>");
////                    ps.println("        <td  width=\"72\" align=\"right\" class=\"TXT_LINKS-RIGHT\"><a href=\"InfoFrame\" target=\"right\">My Profile </a></td>");
////                }
////                else {
////                    ps.println("        <td  width=\"72\" align=\"right\" class=\"TXT_LINKS-RIGHT\"><a href=\"#\" title=\"This functionality is not available.\">My Profile </a></td>");
////                }
//
//                //ps.println("        <td align=\"right\"><img src=\"" + imagesURL + "topHeader/signout.jpg\"  height=\"25\"></td>");
//                //ps.println("        <td  width=\"58\" align=\"right\" class=\"TXT_LINKS-RIGHT\"><a href=\"index.jsp\" target=\"_parent\">Sign Out </a></td>");
//                ps.println("      </tr>");
                ps.println("    </td>");
                //ps.println("    <td align=\"right\" valign=\"middle\"><img src=\"" + imagesURL + "topHeader/2.jpg\" width=\"3\" height=\"27\"></td>");
                ps.println("  </tr>");
                ps.println("  <tr>");
                ps.println("    <td colspan=\"3\" valign=\"top\" style=\"height:4PX; \"></td>");
                ps.println("  </tr>");
                ps.println("</table>");
                ps.println("</body>");
                ps.println("</html>");

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
