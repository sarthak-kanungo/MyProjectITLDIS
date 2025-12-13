<%--
    Document   : successExtWarr
    Created on : 25 Apr, 2017, 12:05:56 PM
    Author     : prashant.kumar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
        <title>ITLDIS</title>
        <link href="${pageContext.request.contextPath}/css/config.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <br><br><br><br><br><br>

        <p align="center" ><span class="heading">
                <c:if test="${result eq 'success' || result eq 'SUCCESS'}">
                    <img alt="" src="${pageContext.request.contextPath}/images/success.gif"/>
                </c:if>
                <c:if test="${result eq 'failure' || result eq 'FAILURE'}">
                    <img alt="" src="${pageContext.request.contextPath}/images/failure.gif"/>
                </c:if><b>
                    ${message} : ${refNo}</b><br/><br/>
                    
                <br/>
                   <a href="" onclick="window.opener.location.reload();
                    window.close();">
                    <b>Close...</b>
                </a>
            </span>
        </p>
    </body>
</html>
