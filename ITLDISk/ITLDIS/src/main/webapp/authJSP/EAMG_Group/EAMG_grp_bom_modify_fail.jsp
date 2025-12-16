<%-- 
    Document   : EAMG_grp_bom_modify_fail

--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.ConnectionHolder,viewEcat.comEcat.PageTemplate" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String result = "" + request.getAttribute("res");
            String context = request.getContextPath();
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String imagesURL = "";
				String contextPath = request.getContextPath();
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
    <%
                heading = "";
                out.println(object_pageTemplate.tableHeader(heading, width, height));
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=context%>css/config.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
         <%
                    String tdData = "MANAGE  TABLE >> MODIFY  " + PageTemplate.GROUP.toUpperCase();
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "MODIFY " + PageTemplate.GROUP.toUpperCase();
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
          <%--  <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Group>> Modify Group</b></td>
                            </tr>
                        </table></td>
                </tr>
            </table>--%>
            <br>
            <center>
                <font class="red"><%=result%></font>
                <br>
                <font class="red-for-temmplate-link"><a href="javascript:location.href='<%=context%>/authJSP/EAMG_Group/EAMG_modify_group.jsp';">Try Again</a></font>
            </center> </div>
    </body>
    <%
                out.println(object_pageTemplate.tableFooter());
    %>
</html>
