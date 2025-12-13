<%-- 
    Document   : EAMG_TSN_reading_err
    Created on : Nov 23, 2012, 5:47:28 PM
    Author     : satyaprakash.verma
--%>

<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String contextPath = request.getContextPath();
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }
%>
<%
            String result = "" + request.getAttribute("result");
            String comp_type = "" + request.getAttribute("comp_type");
            String heading = null;
            int width = 659;
            int height = 84;
            String tdData = "";
            //String grp_cr_status=""+request.getAttribute("grp_cr_status");
            //System.out.println("i am in output page");
            //System.out.println("result is :"+result);
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%
                    tdData = "MANAGE TSN >> UPLOAD TSN MATRIX";
                     object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
      %>
        <%

                    heading = "UPLOAD TSN MATRIX";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">

            <br>


            <center>
                <font class="red" style="align:'center'"><%=result%></font>
            </center>

        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
