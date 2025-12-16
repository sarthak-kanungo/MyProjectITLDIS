<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" pageEncoding="UTF-8"  />
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<link rel="stylesheet" href="${pageContext.request.contextPath}/layout/css/login.css" type="text/css" />
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            //  String imagesURL = (String) session.getAttribute("imagesURL");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String user_id = (String) session.getAttribute("user_id");
%>

<body>
    <div class="con_slidediv" style="position: relative; ">
        <h1><bean:message key="label.common.usermanagement" /></h1>
        <ul>
            <%--if (userFunctionalities.contains("25")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initAssignRoles'>ASSIGN ROLES TO USER TYPE</a></li>
            <%}%>
            <%if (userFunctionalities.contains("2")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/UserManagement.do?option=initUserType'> MANAGE USER TYPES</a></li>
            <%}%>
            <%if (userFunctionalities.contains("4")) {%>
            <li class="brouchers">  <a href = '<%=cntxpath%>/UserManagement.do?option=initUser'> MANAGE USERS</a></li>
            <%}%>--%>
            <%if (userFunctionalities.contains("901")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/UserManagement.do?option=initAssignRoles'><bean:message key="label.common.assignRoles" /></a></li>
            <%}%>

            <%--<%if (userFunctionalities.contains("902")) {%>
            <li class="brouchers">  <a href = '<%=cntxpath%>/UserManagement.do?option=initUserType'> MANAGE USER TYPES</a></li>
            <%}%>
            <%if (userFunctionalities.contains("903")) {%>
            <li class="brouchers">  <a href = '<%=cntxpath%>/UserManagement.do?option=initRegion'> MANAGE REGION</a></li>
            <%}%>--%>
            <%if (userFunctionalities.contains("908")) {%>
            <li class="brouchers">  <a href = '<%=cntxpath%>/UserManagement.do?option=initUser'> MANAGE USERS</a></li>
            <%}%>
            <%if (userFunctionalities.contains("911")) {%>
            <li class="brouchers">  <a href = '<%=cntxpath%>/UserManagement.do?option=initDealer'> MANAGE DEALER</a></li>
            <%}%>

            <%if (userFunctionalities.contains("906")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initManageUserVsPriceList'>MANAGE USER Vs PRICE LIST</a></li>
            <%}%>

            <%if (userFunctionalities.contains("909")) {%>
            <li class="brouchers">  <a href = '<%=cntxpath%>/UserManagement.do?option=getUserData&flag=edit&user_id=<%=user_id%>'>EDIT OWN PROFILE</a></li>
            <%--cntxpath%>/UserManagement.do?option=getUserData&user_id=${userList.id}--%>
            <%}%>

            <%if (userFunctionalities.contains("910")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/UserManagement.do?option=initChangePassword'>CHANGE OWN PASSWORD</a></li>
            <%}%>

            <% if (userFunctionalities.contains("912")) {%>
            <li class="brouchers"><a href = '<%=cntxpath%>/masterAction.do?option=initManageUserHierarchy'>MANAGE USER HIERARCHY</a></li>
            <%}%>
        </ul>
    </div>


</body>

