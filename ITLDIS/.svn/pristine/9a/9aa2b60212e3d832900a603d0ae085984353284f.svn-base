<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String result = "" + request.getAttribute("result");
            String contextPath = request.getContextPath();
%>
<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href ='<%=contextPath%>/masterAction.do?option=initOptions'>MASTERS</a></li>

        </ul>
    </div>
    <br/>
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <div align="center">
                                <center>
                                    <font class="red" style="align:'center'"><%=result%></font>
                                </center>

                            </div>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </center>
</div>
