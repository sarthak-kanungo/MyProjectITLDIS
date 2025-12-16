<%-- 
    Document   : amw_session_expiredLogin
    Created on : nov 5, 2011, 12:37:58 PM
    Author     : avinash.pandey
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="dbConnection.*,EAMG.com.filter.*"%>
<%@ page import="java.util.*,EAMG.utility.*,EAMG.com.LoginAction.*,authEcat.UtilityMapkeys1"%>
<html>
    <%
        session.invalidate();
        String contextPath = request.getContextPath();
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=UtilityMapkeys1.tile%></title>
        <style type="text/css">
            <!--
            body {
                margin-left: 0px;
                margin-top: 0px;
                margin-right: 0px;
                margin-bottom: 0px;
            }
            -->
        </style>
        <link href="<%=contextPath%>/css/style.css" type="text/css" rel="stylesheet">
        <link rel='stylesheet' type='text/css' href='<%=contextPath%>/css/config.css'rel="stylesheet" type="text/css" />
    </head>

    <body>
        <center>
        <table width="380" height="380"  border="0" cellpadding="0" cellspacing="0">
           <br><br><br><br>
            <tr>
                <td style="background-image:url(<%=contextPath%>/auth_images/RelogIn.jpg); background-repeat:no-repeat; ">
                    <table width="85%"  border="0" cellspacing="0" cellpadding="0">
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td width="34%">&nbsp;</td>
                            <td width="66%" align="left"><div align="left" class="big_txt">Your Session has been expired.</div></td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td colspan="2">&nbsp;</td>
                        </tr>
                        <tr>
                            <td>&nbsp;</td>
                            <td class="small_txt">
                                <a href="<%=contextPath%>/Welcome.do" target="_parent">
                                    Please Click here to Re-login
                                </a>
                            </td>
                        </tr>
                    </table></td>
            </tr>
        </table>
        </center>

    </body>
</html>

