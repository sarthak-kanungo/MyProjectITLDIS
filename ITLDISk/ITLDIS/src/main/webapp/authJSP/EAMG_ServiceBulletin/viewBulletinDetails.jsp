<%--
    Document   : uploadServiceBulletin
    Created on : Jul 3, 2013, 5:10:35 PM
    Author     : satyaprakash.verma
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*,EAMG_Service_Bulletin.EAMG_ServiceBulletinBean"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib  prefix="bean" uri="http://struts.apache.org/tags-bean"%>
<%

            String contextPath = request.getContextPath();
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
         //   ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
          //  String servletURL = "";
         //   servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }
        //    String heading = null;
            int width = 970, height = 450;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=UtilityMapkeys1.tile%></title>
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <link href="<%=imagesURL%>style.css" type="text/css" rel="stylesheet">
    </head>
    <body>
        <bean:define id="bulletinType"  name="sBFormBean" property="type" type="java.lang.String"/>
        <bean:define id="issueOfYear"  name="sBFormBean" property="yearOfIssue" type="java.lang.Integer"/>
    <body topmargin="0"  marginheight="0" leftmargin="0"  marginwidth="0">
        <% out.println(object_pageTemplate.tableHeader("(" + bulletinType + ") " + PageTemplate.bulletin + "s Available for " + issueOfYear, width, height));%>
        <table width="920px;" border=0 cellspacing=0 cellpadding=0 bordercolor = #CCCCCC>
            <c:if test="${not empty dataList}">
                <tr><td><table width="920px" border=0 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC>
                            <tr bgcolor = #003399 class=heading1>
                                <td width = 10% align = center>
                                    S. NO.
                                </td>

                                <td width = 70% align = left>
                                    HEADING
                                </td>

                                <td width = 20% align = center>
                                    DATE
                                </td>
                            </tr>
                        </table>
                    </td></tr><tr><td>
                        <div valign=top STYLE=" overflow-X:hidden;overflow-y: scroll; height:430;">
                            <table width="920px;"  border=0 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC>
                                <c:set var="sno" value="1"/>
                                <%--<logic:iterate name="dataList"  id="listBean">--%>
                                <c:forEach items="${dataList}" var="listBean" varStatus="counter">
                                    <%--<c:out value="${counter.count}"/>--%>
                                    <c:choose>
                                        <c:when test="${(counter.count) % 2 == 0}">
                                            <tr>
                                                <td  width=10% align = center class="links_txt">${sno}
                                                </td>
                                                <td  width=70% align = left class="links_txt"><a target="_blank" href="<%=contextPath%>/downloadFileServlet?folderName=<%=ecatPath%>dealer/bulletin&fileName=${listBean.formFileName}">${listBean.subject}</a>
                                                </td>
                                                 <td  width=20% align = center class="links_txt">${listBean.issueDate}
                                                </td>
                                            </tr>
                                        </c:when>
                                        <c:otherwise>
                                            <tr>
                                                <td  width=10% align = center class="links_txt_Alt">${sno}
                                                </td>
                                                <td  width=70% align = left class="links_txt_Alt"><a target="_blank" href="<%=contextPath%>/downloadFileServlet?folderName=<%=ecatPath%>dealer/bulletin&fileName=${listBean.formFileName}">${listBean.subject}</a>
                                                </td>
                                                 <td  width=20% align = center class="links_txt_Alt">${listBean.issueDate}
                                                </td>
                                            </tr>
                                        </c:otherwise>
                                    </c:choose>
                                    <c:set var="sno" value="${sno+1}"/>
                                </c:forEach>
                                <%--</logic:iterate>--%>
                            </table>
                        </div>
                    </td></tr>
                </c:if>
                <c:if test="${empty dataList}">
                <tr ><td align="center" class="links_txt"><b><font color="red">No Record Exists</font></b>

                    </td>
                </tr>
            </c:if>
        </table>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>