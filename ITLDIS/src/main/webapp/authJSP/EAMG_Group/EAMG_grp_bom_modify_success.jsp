<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="java.sql.*,viewEcat.comEcat.ConnectionHolder,viewEcat.comEcat.PageTemplate" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
%>
<%
            String result = "" + request.getAttribute("res");
            String group_no = "" + request.getAttribute("group_no");
            String flag = (String) request.getAttribute("flag");
            String context = request.getContextPath();
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String imagesURL = "";
            String contextPath = request.getContextPath();
            imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
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
                    String tdData = "MANAGE  TABLE >> MODIFY  " + PageTemplate.GROUP.toUpperCase();
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "MODIFY " + PageTemplate.GROUP.toUpperCase();
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">

            <%--<table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=PageTemplate.GROUP%>>> Modify <%=PageTemplate.GROUP%></b></td>
                            </tr>
                        </table></td>
                </tr>
            </table>
            <br>--%>
            <br>

            <font class="red"><%=result%></font><br>

            <%if (flag != null && flag.equals("Success")) {%>
            <font class="red-for-temmplate-link"><a href="javascript:location.href='<%=context%>/authJSP/EAMG_Group/EAMG_modify_group.jsp';">Modify More</a></font>
            <%} else {%>
            <%--<font class="red-for-temmplate-link"><a href="<%=context%>/EAMG_Create_Group.do" >Try Again</a></font>--%>
            <%}%>


            <br>
            <br>
            <br>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
