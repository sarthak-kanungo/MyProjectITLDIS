<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
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
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String modelNo = object_pageTemplate.MODEL_NO;
            String engineModel = object_pageTemplate.ENGINE_MODEL;
            String engineSeries = object_pageTemplate.ENGINE_SERIES;
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }
            String heading = null;
            int width = 659;
            int height = 84;
            String tdData = "";
%>
<%
            String option = "" + request.getAttribute("option");
            String msg = "Unable to Create Variant. Please Contact to System Administrator.";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%
                    if (option.equals("modify")) {

                        tdData = "MANAGE " + modelNo.toUpperCase() + " >> MODIFY " + modelNo.toUpperCase() + " >> VARIENT DETAILS";
                    } else {
                        tdData = "MANAGE " + modelNo.toUpperCase() + " >> ADD NEW " + modelNo.toUpperCase();

                    }


                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>

        <%out.println(object_pageTemplate.tableHeader(heading, width, height));%>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%--<tr>
                    <td>&nbsp;</td>
                </tr>
                <%if (option.equals("modify")) {%>
                 <%
                    heading = "MODIFY " + modelNo.toUpperCase();

                %>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=modelNo%> >> Modify <%=modelNo%> >> Variant Details</b></td>
                            </tr>
                        </table></td>
                </tr>
                <%} else {%>
                <%
                    heading = "ADD NEW " + modelNo.toUpperCase();

                %>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=modelNo%> >> Add New <%=modelNo%></b></td>
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
                <%if (option.equals("modify")) {%>
                <tr>  <td class="red-for-temmplate-link" align="center" ><a href="javascript:location.href='<%=contextPath%>/authJSP/EAMG_Variant/EAMG_modify_model_bypass.jsp';">Try Again</a></td></tr>
                <%} else {%>
                <tr>  <td class="red-for-temmplate-link" align="center" ><a href="javascript:location.href='<%=contextPath%>/EAMG_create_model.do';">Try Again</a></td></tr>
                <%}%>

            </table>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
