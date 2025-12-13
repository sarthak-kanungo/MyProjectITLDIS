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
            String group = object_pageTemplate.GROUP;
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }


            String heading = null;
            int width = 659;
            int height = 84;
            String flag = "" + session.getAttribute("flag");
            String tdData = "";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%
                    if (flag.equals("add_model")) {
                        tdData = "MANAGE " + modelNo.toUpperCase() + " >> ADD " + modelNo.toUpperCase() + " >> ASSIGN " + group.toUpperCase() + " MAP";
                        heading = "ADD NEW " + modelNo.toUpperCase();
                    } else {
                        tdData = "MANAGE " + modelNo.toUpperCase() + " >> MODIFY " + modelNo.toUpperCase();
                        heading = "MODIFY " + modelNo.toUpperCase();

                    }
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%

                            if (flag.equals("add_model")) {%>
                <%--<tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=modelNo%> >> Add  <%=modelNo%> >> Assign <%=group%> Map</b></td>
                            </tr>
                        </table></td>
                </tr>--%>
                <tr>
                    <td>&nbsp;</td>
                </tr>

                <tr>
                    <td><div align="center"><font class="red" ><%=modelNo%> '<%=session.getAttribute("modelno")%>' Created and <%=group%> Attached Successfully.</font>
                        </div>
                    </td>
                </tr>
                <tr>
                    <td align="center"><font class="red-for-temmplate-link" ><a href="javascript:location.href='<%=contextPath%>/EAMG_create_model.do';">Create More</a></font>
                        <br>
                        <br>
                        <font class="text">Click <font class="red-for-temmplate-link"><a href="<%=contextPath%>/common/EAMG_home_page.jsp">Complete</a></font> to Complete <%=modelNo%> '<%=session.getAttribute("modelno")%>'.</font>
                    </td>
                </tr>


                <%} else {%>
                <%--<tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=modelNo%> >> Modify <%=modelNo%></b></td>
                            </tr>
                        </table></td>
                </tr>--%>
                <tr>
                    <td>&nbsp;</td>
                </tr>

                <tr>
                    <td><div align="center"><font class="red"  ><%=group%> Attached /Removed from <%=modelNo%> '<%=session.getAttribute("modelno")%>' Successfully.</font>
                        </div></td>
                </tr>
                <tr>
                    <td align="center"><font class="red-for-temmplate-link" ><a href="javascript:location.href='<%=contextPath%>/authJSP/EAMG_Variant/EAMG_modify_model_bypass.jsp';">Modify More</a></font>
                        <br>
                        <br>
                        <font class="text">Click <font class="red-for-temmplate-link"><a href="<%=contextPath%>/common/EAMG_home_page.jsp">Complete</a></font> to Complete <%=modelNo%> '<%=session.getAttribute("modelno")%>'.</font>
                    </td>
                </tr>

                <%}%>
            </table>

        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
