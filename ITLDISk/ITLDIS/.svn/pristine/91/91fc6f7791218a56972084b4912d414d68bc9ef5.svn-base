<%-- 
    Document   : EAMG_tool_modify_success

    Author     : avinash.pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*" %>

<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>
<html>
    <%
                response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                response.setHeader("Expires", "0");
                response.setHeader("Pragma", "no-cache");
                Connection conn = null;
                ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
                conn = holder.getConnection();
                String contextPath = request.getContextPath();
                String server_name = (String) session.getValue("server_name");
                String ecatPath = (String) session.getValue("ecatPATH");
                String mainURL = (String) session.getValue("mainURL");
					 
                PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
                String servletURL = "";
                servletURL = object_pageTemplate.servletURL();
                String imagesURL = "";
					 String comp_type=(String)request.getAttribute("comp_type");
                imagesURL = object_pageTemplate.imagesURL();
                String session_id = session.getId();
                String getSession = (String) session.getValue("session_value");
                if (!session_id.equals(getSession)) {
                    object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath+"/Index", "", imagesURL);
                    return;
                }

                String heading = null;
                int width = 659;
                int height = 84;%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />

        <%


                    String comp_param_list = (String) session.getAttribute("comp_param_list");
                    String result = "";
                    result = comp_type + " '"+comp_param_list+"' modified successfully.";

        %>
    </head>
    <body>
        <%
                    String tdData = "MANAGE " +"" + comp_type.toUpperCase() + " >> MODIFY " + "" + comp_type.toUpperCase() + " >> " + "" + comp_type.toUpperCase() + " DETAILS";
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
                    heading = comp_type +" Details";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <%--<tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=comp_type%> >> Modify <%=comp_type%> >> <%=comp_type%> Details</b></td>
                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td valign="top">&nbsp;</td>
                </tr>--%>


                <tr><td class="red" align="center">
                        <%=result%><br>
                        <font class="red-for-temmplate-link" ><a href="javascript:location.href='<%=contextPath%>/authJSP/EAMG_Tool/EAMG_tool_modify_comptype.jsp';">Modify More</a></font>
                    </td>
                </tr>
            </table>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
