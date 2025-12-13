<%-- 
    Document   : EAMG_variant_aggregates_success
    Author     : avinash.pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*" %>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String success=""+request.getAttribute("success");
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String modelNo = object_pageTemplate.MODEL_NO;
            String table = object_pageTemplate.GROUP;
            String aggregate = object_pageTemplate.AGGREGATE;

            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            String contextPath = (String) request.getContextPath();
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
    </head>
    <body>
        <%
                    String tdData ="MANAGE "+modelNo.toUpperCase()+" ADD "+modelNo.toUpperCase();
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
                    heading = ""+modelNo+" Vs "+aggregate+"";
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
                            <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=modelNo%> >>  Add <%=modelNo%></b></td>
                        </tr>
                </table></td>
            </tr>--%>
        </table>
        <br>
        <%if(success==null){%>
            <font class="red"><%=aggregate%> is not Added</font>
            <br>
            <font class="red-for-temmplate-link" ><a href="javascript:location.href='/EAMG_Variant_Aggregates.do?option=initVariant';">Try Again</a></font>
        <%}
       else{%>
        
            <font class="red"><%=aggregate%> is Added Successfully for <%=modelNo%> '<%=success%>'</font>
            <br>
            <font class="red-for-temmplate-link" ><a href="javascript:location.href='/EAMG_Variant_Aggregates.do?option=initVariant';">Add More</a></font>
        <%}%>
        </div>
         <%
               out.println(object_pageTemplate.tableFooter());
         %>
    </body>
</html>

