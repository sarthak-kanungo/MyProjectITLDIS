<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.ConnectionHolder,viewEcat.comEcat.PageTemplate" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
--%><%--<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>--%><%--
--%>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
%>
<%
            String result = (String) request.getAttribute("result");
            String group_no = (String) session.getAttribute("group_number");
            String flag = (String) request.getAttribute("flag");
            String context = request.getContextPath();
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String contextPath = request.getContextPath();
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);
            String imagesURL = "";
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

            //String user_id=""+(String) session.getValue("userCode");

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />

    </head>
    <body>
        <%
                    String tdData = "MANAGE  TABLE >> ADD  NEW "+PageTemplate.GROUP.toUpperCase();
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "ADD NEW " + PageTemplate.GROUP.toUpperCase() + "";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">

          <%--  <br>

            <table width="700" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                <tr>
                    <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Group>> Add  New Group</b></td>
                </tr>
            </table>--%>
            <br>



            <font class="red"><%=result%>&nbsp;</font><br>
            <%if (flag != null && flag.equals("Success")) {%>
            <font class="red-for-temmplate-link"><a href="<%=context%>/EAMG_Create_Group.do" >Add More Table</a></font>
            <%} else {%>
            <font class="red-for-temmplate-link"><a href="<%=context%>/EAMG_Create_Group.do" >Try Again</a></font>
            <%}%>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
