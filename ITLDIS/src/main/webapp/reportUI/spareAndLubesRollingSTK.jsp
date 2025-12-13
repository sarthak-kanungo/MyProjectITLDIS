<%-- 
    Document   : ${name}
    Created on : ${date}, ${time}
    Author     : ${user}
--%>


<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            Calendar cal = Calendar.getInstance();
            int baseYear = 2015;
            int yearNext = cal.get(Calendar.YEAR) + 1;
            List yrList = new ArrayList();
            for (int i = baseYear; i < yearNext; i++) {
                yrList.add((i) + "-" + (i + 1));
            }
            Collections.reverse(yrList);

%>

<c:set var="yrList" value='<%=yrList%>'/>

<script src="JS/Master_290414.js"></script>
<script src="js/intermediate.js"></script>
<link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >

<script type="text/javascript">
    
    function submitSearchByForm(){
        var finYear=document.getElementById("finYearId").value;
        var month=document.getElementById("monthId").value;        
       var url="reportAction.do?option=getRollingSpareLubeReportSTK&finYear=" + finYear + "&month="+month;
        window.open(url);       
    }

  
</script>

<html>
    <head><title>ITLDIS</title></head>
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/reportAction.do?option=options'><bean:message key="label.report.report" /></a></li>
            <li><bean:message key="label.report.spareAndLubeRollingSTK" /></li>
        </ul>
    </div>

    <center>
        <div class="content_right1">
            <div class="con_slidediv2" >
                <h1 class="hText"><bean:message key="label.report.spareAndLubeRollingSTK" /></h1>
                <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr height=50 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            
                            <html:form action="reportAction.do?option=getRollingSpareLubeReportSTK&flag=STK" method="post" styleId="searchBy" onsubmit="return false;">         <%--onsubmit="return submitSearchByForm();"--%>
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#FFFFFF>                                   
                                    <tr bgcolor="#FFFFFF">
                                        <td colspan="2" style="padding-left:10px; padding-right: 10px; padding-top: 10px" class="headingSpas" nowrap align="right" width="80px">
                                            <span class="formLabel"><bean:message key="label.common.finYear" /></span>
                                        </td>
                                        <td align="left" style="padding-top: 10px">
                                            <html:select property="finYear" styleId="finYearId" styleClass="headingSpas" style="width:160px !important">
                                                <c:forEach items="${yrList}"  var="yr">
                                                    <html:option value ="${yr}">${yr}</html:option>
                                                </c:forEach>
                                            </html:select>
                                        </td>
                                        <td colspan="1" style="padding-left:10px; padding-right: 10px; padding-top: 10px" class="headingSpas" nowrap align="right" width="40px">
                                            <span class="formLabel"><bean:message key="label.common.month" /></span>
                                        </td>
                                        <td align="left" style="padding-top: 10px">
                                            <html:select property="month" styleId="monthId" styleClass="headingSpas" style="width:160px !important">                                                
                                                <html:option value="1">January</html:option>
                                                <html:option value="2">February</html:option>
                                                <html:option value="3">March</html:option>
                                                <html:option value="4">April</html:option>
                                                <html:option value="5">May</html:option>
                                                <html:option value="6">June</html:option>
                                                <html:option value="7">July</html:option>
                                                <html:option value="8">August</html:option>
                                                <html:option value="9">September</html:option>
                                                <html:option value="10">October</html:option>
                                                <html:option value="11">November</html:option>
                                                <html:option value="12">December</html:option>
                                            </html:select>
                                        </td>                                       
                                        <td colspan="2" style="padding-left:5px; padding-top: 10px" align="left" >
                                            <input type="button" name="print" value="Submit" class="headingSpas" onclick="submitSearchByForm()"/>
                                        </td>
                                    </tr>
                                    <tr><td>&nbsp;</td></tr>
                                </table>
                            </html:form>
                        </td>
                    </tr>
                </table>
            </div>
          </div>
    </center>
</body>
</html>;
