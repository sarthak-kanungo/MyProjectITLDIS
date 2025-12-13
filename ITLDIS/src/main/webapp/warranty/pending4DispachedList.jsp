<%-- 
    Document   : pending4DispachedList
    Created on : Dec 6, 2014, 6:11:53 PM
    Author     : megha.pandya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*" %>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<script>
</script>
<html lang="en">
    <head>  <meta charset="utf-8">
        <title>DIS</title>
        <script src="JS/Master_290414.js"></script>
        <link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >
    </head>
    <body>
  <div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.warranty.warranty" /></a></li>

            <li class="breadcrumb_link"><label><bean:message key="label.common.pending4Dispatch" /></label></li>
      </ul>
    </div>
    <div class="message" id="msg_saveFAILED"></div>
    <center>
         <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1 class="hText"> <bean:message key="label.common.pending4Dispatch" /></h1>



                <table id="data" width=100%  border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc   >
           
            <c:if test="${empty dispachedList}">
                <tr bgcolor="#FFFFFF">
                    <td valign="top" align="center" colspan="10" class="headingSpas" style="padding-top:10px;color: red">
                        <bean:message key="label.common.noRecordFound" />
                    </td>
                </tr>
            </c:if>


            <c:if test="${!empty dispachedList}">
                <tr>
                    <td colspan="3">
                        <pg:pager url="warrantyAction.do" maxIndexPages="10" maxPageItems="15">
                             <pg:param name="option" value="initPackedWarrantyList"/>
                            <%--<pg:param name="option" value="initPending4Dishpatched"/>--%>
                            <table width="100%" border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor="#cccccc" >
                                <tr bgcolor="#eeeeee" height="20">
                                    <td  align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.catalogue.sNo" />   </B> </td>
                                    <td align="left" nowrap style="padding-left:5px;padding-right:5px;"><B> <bean:message key="label.common.packingNo" /></B></td>
                                    <td align="left" nowrap style="padding-left:5px;padding-right:5px;"><B> <bean:message key="label.common.packingDate" /></B></td>
                                    <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B> <bean:message key="label.common.noOfClaims" /></B></td>
                                    <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.common.action" /> </B></td>
                                </tr>
                                <c:set var="sno" value="1"></c:set>
                                <logic:iterate id="dispachedList" name="dispachedList">
                                    <pg:item>
                                        <tr bgcolor="#FFFFFF">
                                            <td align="center" >${sno}</td>
                                            <td align="left" ><span>${dispachedList.packingNo}</span></td>
                                            <td align="left" ><span>${dispachedList.packingDate}</span></td>
                                            <td align="center" ><span>${dispachedList.noOfClaims}</span></td>
                                            <td align="center" >
                                                <span>
                                                 <a href="<%=cntxpath%>/warrantyAction.do?option=initconsignment&packingNo=${dispachedList.packingNo}">
                                                <img src="${pageContext.request.contextPath}/images/edit.gif" title="Update Consignment Details"/></a>
                                                </span></td>
                                        </tr>
                                    </pg:item>
                                    <c:set var="sno" value="${sno + 1 }" />
                                </logic:iterate>
                                <tr>
                                    <td colspan="5" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                        <table  align="center" cellspacing="0" cellpadding="0">
                                            <tr>
                                                <td>
                                                    <pg:index>
                                                        <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle" style="float:left;"/></a></pg:first>&nbsp;
                                                        <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle" style="float:left;"/></a></pg:prev>&nbsp;
                                                        <pg:pages>&nbsp;<a style="color: blue;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                                        <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle" style="float:right"/></a></pg:next>&nbsp;
                                                        <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle" style="float:right"/></a></pg:last>
                                                        </pg:index>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </pg:pager>
                    </td>
                </tr>
            </c:if>
        </table>

            </div>
         </div>
    </center>
  </div>
</body>
</html>