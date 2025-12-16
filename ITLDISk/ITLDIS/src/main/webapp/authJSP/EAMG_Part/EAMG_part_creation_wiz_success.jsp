<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
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
            String COMP_NO = request.getParameter("partno").trim();
            String msg = (String) request.getAttribute("msg");
            String heading = null;
            int width = 659;
            int height = 84;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <title><%=UtilityMapkeys1.tile%></title>
    </head>
    <body>
        <%
                    String tdData = "MANAGE PART >> ADD NEW PART";
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "ADD NEW PART";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
               <tr>
                    <td>&nbsp;</td>
                </tr>

                 <%--<tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Part >> Manage Part >> Add New Part </b></td>
                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>--%>
                <%if (msg.equals("SUCCESS")) {%>
                <tr>
                    <td class="red" align="center" height="30">Part '<%=COMP_NO%>' added successfully.</td>
                </tr>
                <%
                    String ECNImplementation = (String) session.getAttribute("ECNImplementation");
                    if (ECNImplementation == null) {
                        ECNImplementation = "";
                    }
                    if (!ECNImplementation.equals("ECNImplementation")) {%>
                <tr>
                    <td class="red-for-temmplate-link" align="center"><a href="javascript:location.href='<%=contextPath%>/authJSP/EAMG_Part/EAMG_browse_part_excel.jsp'">Add More</a></td>
                </tr>
                <%}%>
                <%} else {%>

                <tr>
                    <td class="red" height="30" align="center"><%=msg%></td>
                </tr>
                <tr>
                    <td class="red-for-temmplate-link" align="center"><a href="javascript:location.href='<%=contextPath%>/authJSP/EAMG_Part/EAMG_browse_part_excel.jsp'">Add More</a></td>
                </tr>
                <%}%>
            </table>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
