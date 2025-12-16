<%-- 
    Document   : amw_success_Aggareagte
    Created on : Nov 11, 2011, 10:57:01 AM
    Author     : avinash.pandey
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
page import="java.sql.*, java.util.*" %>

<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>
<%
            String contextPath = request.getContextPath();
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String heading = null;
            int width = 659;
            int height = 84;
%>
<%
            String option = "" + request.getAttribute("option");
//System.out.println("option :"+option);
            String msg = "Unable to Create Aggregate. Please Contact to System Administrator.";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AMW-Ecatalogue</title>
        <link href="<%=contextPath%>/css/config.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%
                    String tdData = "";
                    if (option.equals("create")) {
                        tdData = "MANAGE AGGREGATE >> ADD NEW AGGREGATE";
                    } else {
                        tdData = "MANAGE AGGREGATE >> DELETE AGGREGATE";
                    }

                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "AGGREGATE DETAILS";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%-- <tr>
                     <td>&nbsp;</td>
                 </tr>
                 <%if(option.equals("create")){%>
                 <tr>
                     <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                             <tr>
                                 <td height="25" align="left" class="Lightblue"><b class="path">&nbsp;Manage Aggregate >> Add New Aggregate</b></td>
                             </tr>
                     </table></td>
                 </tr>
                 <%}else{%>
                 <tr>
                     <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                             <tr>
                                 <td height="25" align="left" class="Lightblue"><b class="path">&nbsp;Manage Aggregate >> Delete Aggregate</b></td>
                             </tr>
                     </table></td>
                 </tr>
                 <%}%>--%>
                <tr>
                    <td align="right" class="small">&nbsp;</td>
                </tr>
                <tr>
                    <%if (request.getAttribute("result") != null) {%>
                    <td align="center" class="red"><%=request.getAttribute("result")%></td> 
                    <%} else {%>
                    <td align="center" class="red">&nbsp;<%=msg%></td> 
                    <%}%>
                </tr>
                <%if (option.equals("create")) {%>
                <tr>  <td class="red-for-temmplate-link" align="center" ><a href="javascript:location.href='<%=contextPath%>/authJSP/amw_Variant/amw_add_aggregate.jsp';">Try Again</a></td></tr>
                <%} else {%>
                <tr>  <td class="red-for-temmplate-link" align="center" ><a href="javascript:location.href='<%=contextPath%>/amw_delete_aggregate.do';">Try Again</a></td></tr>
                <%}%>

            </table>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>