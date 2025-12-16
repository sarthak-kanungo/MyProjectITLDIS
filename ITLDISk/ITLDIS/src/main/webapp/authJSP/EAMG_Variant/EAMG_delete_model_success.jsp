<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*" %>
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
            String contextPath = request.getContextPath();
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
            String engineModel = object_pageTemplate.ENGINE_MODEL;

            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }
%>
<%

            String result = (String) request.getAttribute("result");
            String option = "" + request.getAttribute("option");
            String heading = null;
            int width = 659;
            int height = 84;
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
                    String tdData = "MANAGE " + modelNo.toUpperCase() + " >> DELETE UNUSED " + modelNo.toUpperCase();
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "DELETE UNUSED " + modelNo.toUpperCase() + "";
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
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=modelNo%>  >> Delete Unused <%=modelNo%></b></td>
                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>--%>
                <tr>
                    <td align="center" class="red"><%=result%></td>
                </tr>

                <tr>
                    <td align="center" class="red-for-temmplate-link"> <% if (option.equals("delete")) {
                        %>
                        <a href="javascript:location.href='<%=contextPath%>/authJSP/EAMG_Variant/EAMG_delete_model.jsp'">Delete  More</a>
                        <%
                                    }
                        %></td>
                </tr>


            </table>




            <%--
        This example uses JSTL, uncomment the taglib directive above.
        To test, display the page like this: index.jsp?sayHello=true&name=Murphy
            --%>
            <%--
        <c:if test="${param.sayHello}">
            <!-- Let's welcome the user ${param.name} -->
            Hello ${param.name}!
        </c:if>
            --%>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>