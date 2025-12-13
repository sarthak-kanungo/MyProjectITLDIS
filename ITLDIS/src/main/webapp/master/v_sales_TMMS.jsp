<%-- 
    Document   : v_sales_TMMS
    Created on : May 7, 2015, 2:49:34 PM
    Author     : Avinash.Pandey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="html" uri="http://struts.apache.org/tags-html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" pageEncoding="UTF-8"  />
<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>

<c:set var="contextPath" value='<%=request.getContextPath()%>'/>
<link rel="stylesheet" href="${contextPath}/css/login.css" type="text/css" />

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String userId= (String)session.getAttribute("user_id");
            String password=(String) session.getAttribute("password");

%>
<body>
<div class="con_slidediv" style="position: relative;">
    <ul>
        <%if (userFunctionalities.contains("300")) {%>
        <iframe  style="border:none; height:480px;" noresize="noresize"  frameborder="0"   allowautotransparency=true id="myiframe" name="myiframe"   width="100%"   src="https://crm.sonalika.com/tms/admin/DMS/Sales.aspx?uid=<%=userId%>&PWD=<%=password%>"></iframe>
        <%}%>

    </ul>
</div>
</body>