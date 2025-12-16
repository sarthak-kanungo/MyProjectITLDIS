<%-- 
    Document   : EAMG_TSN_validation_err
    Created on : Nov 23, 2012, 5:47:10 PM
    Author     : satyaprakash.verma
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
                     String tdData = "MANAGE TSN >> UPLOAD TSN MATRIX";
                     object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
       %>
        <%
                    heading = "UPLOAD TSN MATRIX";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <%
                        String err = (String) request.getAttribute("xls_validate_result");
                        //System.out.println("ia m in validation err:"+err);
                        String validate_type = (String) request.getAttribute("validate_type");
                        //System.out.println("validation:"+validate_type);
                        int pos1 = err.lastIndexOf("@");
                        int pos2 = err.lastIndexOf("#");
                        int row = Integer.parseInt(err.substring(0, pos1 - 1)) + 1;
                        int column = Integer.parseInt(err.substring(pos1 + 1, pos2 - 1)) + 1;
                        String error = err.substring(pos2 + 1);
                        String error2 = "";
            %>
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                
                                <%
                                            if (validate_type.equals("modify")) {
                                                error2 = "Hence TSN Modification Process aborted. Attach valid Excel to complete the Process successfully.";

                                %>
                                
                                <%} else {
                                                                                error2 = "Hence TSN Creation Process aborted. Attach valid Excel to complete the Process successfully.";
                                %>
                                
                                <%}%>
                      
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td class="red" align="center">
                        There is an error at Column <%=column%>, Row <%=row%>. <%=error%><br><%=error2%></td>
                </tr>

                <%
                            if (validate_type.equals("modify")) {
                %>
                <tr>  <td align="center"> <font class="red-for-temmplate-link"><a href="javascript:location.href='<%=contextPath%>/authJSP/other/EAMG_Upload_TSN_matrix.jsp';">Upload More</a></font></td></tr>
                <%} else {
                %>
                <tr>  <td align="center"> <font class="red-for-temmplate-link"><a href="javascript:location.href='<%=contextPath%>/authJSP/other/EAMG_Upload_TSN_matrix.jsp';">Upload More</a></font></td></tr>
                <%}%>


            </table>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
