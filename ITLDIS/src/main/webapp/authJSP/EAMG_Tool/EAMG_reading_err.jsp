<%-- 
    Document   : EAMG_reading_err
     Author     : Avinash.Pandey
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*" %>

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
            String tdData="";
            String result = "" + request.getAttribute("result");
            String comp_type = "" + request.getAttribute("comp_type");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%
                    if(comp_type.equalsIgnoreCase("Tool"))
                     {
                       tdData = "MANAGE "+""+comp_type.toUpperCase()+" >> ADD NEW "+""+comp_type.toUpperCase();
                     }else{
                      tdData = "MANAGE "+""+comp_type.toUpperCase()+" >> ADD NEW "+""+comp_type.toUpperCase();
                     }

                    
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
                    heading = "Add New "+comp_type.toUpperCase();
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                
            </table>


            <center>
                <font class="red" style="align:'center'"><%=result%></font></center>

        </div>\
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
