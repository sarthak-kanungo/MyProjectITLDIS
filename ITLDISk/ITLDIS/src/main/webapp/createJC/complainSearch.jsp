<%-- 
    Document   : complainSearch
    Created on : Nov 27, 2014, 11:15:39 AM
    Author     : megha.pandya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="layout/css/login.css" type="text/css" />
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <%--<h1><bean:message key="label.common.pendingforpdi" /></h1>--%>
                <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <form action="serviceCreateJC.do?option=initSearchComplain" method="post" name="searchBy" id="searchBy">
                                <table width=100%  border=0 cellspacing=0 cellpadding=3 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">
                                        <td  style="padding-left:10px"  class="headingSpas" nowrap align="right">
                                           <bean:message key="label.common.customercomplaint" />
                                        </td>
                                        <td style="padding-left:10px"  align="left">
                                            <input name="constantValue" type="text" id="constantValue" value="${serForm.constantValue}" style="width:200px !important " onblur="this.value=TrimAll(this.value)"/>
                                        </td>
                                        <td style="padding-left:10px" align="left" >
                                            <input name="go" type="submit" value="search" class="headingSpas1"  />
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </td>
                    </tr>
                    <c:if test="${!empty serForm.aggCode}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">

                                <%--<pg:pager url="serviceCreateJC.do" maxIndexPages="10" maxPageItems="15">
                                    <pg:param name="option" value="initSearchComplain"/>
                                    <pg:param name="constantValue"  value='${serForm.constantValue}'/>--%>
                                    <table width=100%  border=0 cellspacing=1 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td nowrap align="center"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.sno" />
                                            </td>
                                            <td nowrap align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.aggregate" />
                                            </td>
                                            <td nowrap align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.subaggregate" />
                                            </td>
                                            <td nowrap align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.subassembly" />
                                            </td>

                                            <td nowrap align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.defect" />
                                            </td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <c:set var="j" value="0"></c:set>
                                        <c:forEach items="${serForm.aggCode}" var="dataList">
                                        <%--<logic:iterate id="${serForm.aggCode}" name="defectList">--%>
                                           <%-- <pg:item>--%>
                                                <tr  bgcolor="#eeeeee">
                                                    <td nowrap align="center"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                    <td align="left" nowrap  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                        <span>${serForm.aggCode[j]}</span>
                                                    </td>
                                                    <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${serForm.subaggCode[j]}</span></td>
                                                    <td nowrap align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" ><span>${serForm.subassembly[j]}</span></td>
                                                    <td nowrap align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" ><span>${serForm.compVerbatim[j]}</span></td>
                                                </tr>
                                          <%--  </pg:item>--%>
                                            <c:set var="sno" value="${sno + 1 }" />
                                            <c:set var="j" value="${j + 1 }"></c:set>
                                        </c:forEach><%--</logic:iterate>--%>
                                        <%--<tr>
                                            <td colspan="5" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                <pg:index>
                                                    <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle"/></a></pg:first>&nbsp;
                                                    <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle"/></a></pg:prev>&nbsp;
                                                    <pg:pages>&nbsp;<a style="color: blue;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                                    <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle"/></a></pg:next>&nbsp;
                                                    <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle"/></a></pg:last>
                                                    </pg:index>
                                            </td>
                                        </tr>--%>

                                    </table>
                                <%--</pg:pager>--%>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${ fn:length(serForm.aggCode) eq 0}">
                            <tr  class="headingSpas" bgcolor="#FFFFFF" height="20">
                                <td valign="bottom" align="center" style="padding-top:10px;color: red"> <bean:message key="label.common.noRecordFound" /></td>
                            </tr>
                        </c:if>
                </table>
            </div>
        </div>
    </center>
