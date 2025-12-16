<%-- 
    Document   : amw_invalid_kit_image
    Created on : Nov 15, 2011, 11:14:28 PM
    Author     : avinash.pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*,EAMG.Kit.Action.*" %>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");

            String contextPath = request.getContextPath();
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
				String comp_type=(String)request.getAttribute("comp_type");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath+"/Index", "", imagesURL);
                return;
            }
            String heading = null;
            int width = 659;
            int height = 84;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />

        <%
                    String result = (String) session.getAttribute("result");
                    String option = (String) session.getAttribute("option");
        %>
    </head>
    <body>
        <%          String tdData="";
                    if (option.equals("modify")) {
                        heading = "TOOL DETAILS";
                         tdData = "MANAGE " +"" + comp_type + " >> MODIFY "+"" + comp_type+" >> "+comp_type+" DETAILS";
                        }else{
                        heading = "ADD NEW "+comp_type;
                         tdData = "MANAGE " +"" + comp_type + " >> ADD NEW "+"" + comp_type;
                        }
                    
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
               <%-- <%if (option.equals("modify")) {%>
                <%heading = "TOOL DETAILS";%>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=comp_type%> >> Modify <%=comp_type%> >> <%=comp_type%> Details</b></td>
                            </tr>
                        </table></td>
                </tr>
                <%} else {%>
                <%heading = "ADD NEW TOOL";%>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=comp_type%> >> Add New <%=comp_type%></b></td>
                            </tr>
                        </table></td>
                </tr>
                <%}%>
                <tr>
                    <td valign="top">&nbsp;</td>
                </tr>
--%>

                <tr><td class="red" align="center">
                        <%=result%><br>
                        <font class="red-for-temmplate-link" ><a href="javascript:location.href='<%=contextPath%>/authJSP/EAMG_Tool/EAMG_tool_modify_comptype.jsp';">Modify More</a></font>
                    </td></tr>

                <%--
        This example uses JSTL, uncomment the taglib directive above.
        To test, display the page like this: index.jsp?sayHello=true&name=Murphy
                --%>
                <%--
        <c:if test="${param.sayHello}">
            <!-- Let's welcome the user ${param.name} -->
            Hello ${param.name}!
        </c:if>
                --%></table>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
