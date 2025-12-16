<%@ page import="java.util.*" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" pageEncoding="UTF-8"  />
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<c:set var="now" value="<%=new java.util.Date()%>" />
<%
            String cntxpath = request.getContextPath();
            String username = (String) session.getAttribute("username");
            String dealerCode = (String) session.getAttribute("dealerCode");
            String dealerName = (String) session.getAttribute("dealerName");
            String userId = (String) session.getAttribute("user_id");
            String binaryString = (String) session.getAttribute("binaryString");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<c:set var="dCode" value='<%=dealerCode%>'/>
<c:set var="dName" value='<%=dealerName%>'/>

<script language="javascript">
    function openPages(str, param)
    {
        if (str == 'masters')
        {
            document.location.href = "<%=cntxpath%>/masterAction.do?option=initOptions";
        }
        if (str == 'spares')
        {
            document.location.href = "<%=cntxpath%>/inventoryAction.do?option=initInvOptions";
    <%--window.location = "/inventory/manage_inventoryOptions.jsp";--%>
            }
        }
    
    
    /*


    Block right-click

    Block F12

    Block Ctrl+Shift+I (Inspect)

    Block Ctrl+U (View Source)

    */

    document.addEventListener('contextmenu', e => e.preventDefault());
    document.onkeydown = function(e) {
        if (e.keyCode == 123) { // F12
            return false;
        }
        if (e.ctrlKey && e.shiftKey && e.keyCode == 73) { // Ctrl+Shift+I
            return false;
        }
        if (e.ctrlKey && e.keyCode == 85) { // Ctrl+U
            return false;
        }
    };
    
    
    
    
    
    
    
</script>
<div class="mainwrap">
    <div class="header_main">
        <div class="headright">
            <div class="toper"> 
                <ul>
                    <%-- <li><a href='<%=cntxpath%>/UserManagement.do?option=getUserProfile&user_id=<%=userId%>'><bean:message key="label.common.Welcome" /> <%=username%> </a></li>--%>
                    <c:if test="${dCode ne 'null' && dName ne 'null'}" >
                        <li><a href="#"><%=dealerCode%> [ <%=dealerName%> ]</a></li>
                    </c:if>
                    <li><a href="#"><bean:message key="label.common.Welcome" /> <%=username%></a></li>
                    <li style="border:none;"><a href="${pageContext.request.contextPath}/login.do?option=logout"><bean:message key="label.common.Signout" /></a></li>
                </ul>
            </div>
            <div class="topbar">
                <ul>
                    <li><a href="${pageContext.request.contextPath}/login.do?option=homePage" usemap="#Map"><img src="layout/images/home.jpg" alt="home" /></a></li>
                    <!--   <li><a href="#" usemap="#Map9"><img src="layout/images/help.jpg"  alt="help" /></a></li>-->
                    <li><a href="${pageContext.request.contextPath}/login.do?option=contactUS" usemap="#Map9"><img src="layout/images/contact.jpg"  alt="contact" /></a></li>
                </ul>
            </div>
        </div>
        <div class="logo">
            <a href="#"><img src="layout/images/logo.png" alt="" class="fl"></a>
        </div>
    </div>
    <div class="navigation">
        <!--<div class="navleft"><a href="${pageContext.request.contextPath}/FillForm.do">Fill Form</a></div>-->
        <div class="navright"><fmt:formatDate type="date" dateStyle="long" value="${now}" /></div>
        <div class="navmid">
            <div class="nav">
                <div class="menu">
                    <ul class="hText">
                        <%if (userFunctionalities.contains("600")) {%>
                        <li><html:link action="/initService"> <bean:message key="label.common.Service" /> </html:link></li>
                        <%}%>
                        <%if (userFunctionalities.contains("700")) {%>
                        <li><html:link action="/initWarranty"> <bean:message key="label.common.warranty" /></html:link></li>
                        <%}%>
                        <%if (userFunctionalities.contains("1000")) {%>
                        <li><html:link action="/initExtWarranty"> <bean:message key="label.common.extWarrantyReg" /> </html:link></li>
                        <%}%>
                        <%if (userFunctionalities.contains("800")) {%>
                        <li><a href="#" onclick="javascript:openPages('spares')"> <bean:message key="label.common.spares" /> </a></li>
                        <%}%>
                        <%if (userFunctionalities.contains("2")) {%>
                        <li><a href="changelogin" target="_blank"><bean:message key="label.common.catalogue" /></a></li>
                        <%} else if (userFunctionalities.contains("99")) {%>
                        <li><a href="<%=binaryString%>" target="_blank"><bean:message key="label.common.catalogue" /></a></li>
                        <%}%>
                        <%if (userFunctionalities.contains("500")) {%>
                        <li><a href="#" onclick="javascript:openPages('masters')"><bean:message key="label.common.Masters" /></a></li>
                        <%}%>
                        <%if (userFunctionalities.contains("900")) {%>
                        <li><a href="${pageContext.request.contextPath}/UserManagement.do?option=initUserInformation"><bean:message key="label.common.usermanagement" /></a></li>
                        <%}%>
                        <%if (userFunctionalities.contains("300")) {%>
                        <li><a href="${pageContext.request.contextPath}/masterAction.do?option=initSales"><bean:message key="label.common.sales" /></a></li>
                        <%}%>
                        <%if (userFunctionalities.contains("902")) {%>
                        <li><a href="${pageContext.request.contextPath}/reportAction.do?option=options"><bean:message key="label.report.report" /></a></li>
                        <%}%>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>
<map name="Map" id="Map">
    <area shape="rect" coords="25,12,115,31" href=""/>
    <area shape="rect" coords="151,11,197,32" href=""/>
</map>
<map name="Map9" id="Map9">
    <area shape="rect" coords="20,8,51,21" href=""/>
    <area shape="rect" coords="76,6,101,21" href="#" />
    <area shape="rect" coords="129,6,184,21" href="${pageContext.request.contextPath}/login/LocateUs.jsp" target="right" />
</map>