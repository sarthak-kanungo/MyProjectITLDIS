<%-- 
    Document   : v_pending4DEApproval
    Created on : Sep 24, 2014, 10:54:09 AM
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

<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';

</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link></li>
            <li class="breadcrumb_link"><bean:message key="label.common.pending4DEapproval" /></li>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED" align="center"></div>

    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.common.pending4DEapproval" /></h1>
                <form action="serviceProcess.do?option=initPendingDE4Approval" method="post" id="DE4Approval" name="DE4Approval">
                <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr bgcolor="#eeeeee" height="20">
                        <td nowrap align="center" width="5%" style="padding-left:3px;padding-right:3px;FONT-SIZE: 11px;font-weight:bold;" >
                            <bean:message key="label.common.sno" />
                        </td>
                        <td nowrap align="left" width="16%" style="padding-left:3px;padding-right:3px;FONT-SIZE: 11px;font-weight:bold;" >
                            <bean:message key="label.common.jobcardno" />
                        </td>
                        <td nowrap align="left" width="10%" style="padding-left:3px;padding-right:3px;FONT-SIZE: 11px;font-weight:bold;" >
                            <bean:message key="label.common.jobcardDate" />
                        </td>
                        <td nowrap align="left" width="16%" style="padding-left:3px;padding-right:3px;FONT-SIZE: 11px;font-weight:bold;" >
                            <bean:message key="label.common.Chassisno" />
                        </td>
                        <td nowrap align="left" width="15%" style="padding-left:3px;padding-right:3px;FONT-SIZE: 11px;font-weight:bold;" >
                            <bean:message key="label.common.JobcardPayeeName" />
                        </td>
                        <%if (userFunctionalities.contains("101")) {%>
                        <%} else {%>
                        <td nowrap align="left" width="15%" style="padding-left:3px;padding-right:3px;FONT-SIZE: 11px;font-weight:bold;" >
                            <bean:message key="label.common.dealerName" />
                        </td>
                        <td nowrap align="left" width="10%" style="padding-left:3px;padding-right:3px;FONT-SIZE: 11px;font-weight:bold;" >
                            <bean:message key="label.common.location" />
                        </td>
                       <%-- <td nowrap align="left" width="18%" style="padding-left:3px;padding-right:3px;" >
                            <bean:message key="label.common.deTMSApprove" />
                        </td>--%>

                        <%}%>
                    </tr>
                    <c:if test="${!empty de4ApprovalList}">

                        <pg:pager url="serviceProcess.do" maxIndexPages="10" maxPageItems="15">
                            <pg:param name="option" value="initPendingDE4Approval"/>
                            <c:set var="sno" value="1"></c:set>
                            <logic:iterate id="de4ApprovalList" name="de4ApprovalList">
                                <pg:item>
                                    <tr  class="headingSpas" bgcolor="#FFFFFF" height="20">
                                        <td nowrap align="center"  bgcolor="#FFFFFF" style="padding-left:3px;" >${sno}</td>
                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;">
                                            <a href="<%=cntxpath%>/serviceProcess.do?option=viewJobcard&vinNo=${de4ApprovalList.vinNo}&jobCardNo=${de4ApprovalList.jobCardNo}&flag=deApproval" >
                                            <span id ="jobCardNo${sno}" >${de4ApprovalList.jobCardNo}</span></a>
                                        <%-- <a href="${pageContext.request.contextPath}/pdiServiceProcess.do?option=viewPDIData&vinNo=${de4ApprovalList.vinNo}" >
                                                ${de4ApprovalList.jobCardNo}</a>--%>
                                        </td>
                                        <td align="left" nowrap   bgcolor="#FFFFFF" style="padding-left:3px"><span >${de4ApprovalList.jobCardDate}</span>
                                        </td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;"><span >${de4ApprovalList.vinNo}</span></td>
                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;"><span >${de4ApprovalList.payeeName}</span></td>
                                        <%if (userFunctionalities.contains("101")) {%>
                                        <%} else {%>
                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;"><span>${de4ApprovalList.dealerName}[ ${de4ApprovalList.dealercode} ]</span></td>
                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;"><span>${de4ApprovalList.locationName}</span></td>
                                        <%--<td align="center"  bgcolor="#FFFFFF" style="padding-left:3px;">
                                            <select id="status${sno}" name="status">
                                                <option value="Approve">Approve</option>
                                                <option value="Reject">Reject</option>
                                            </select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input style="padding-left:10px;" type="checkBox" id="confirmChk${sno}" name="confirmChk"/>
                                        </td>--%>
                                        <%}%>
                                    </tr>
                                </pg:item>
                                <c:set var="sno" value="${sno + 1 }" />
                            </logic:iterate>
                            <tr>
                                <%if (userFunctionalities.contains("101")) {%>
                                <td colspan="5" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                    <%} else {%>
                                <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                    <%}%>

                                    <pg:index>
                                        <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle"/></a></pg:first>&nbsp;
                                        <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle"/></a></pg:prev>&nbsp;
                                        <pg:pages>&nbsp;<a style="color: blue;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                        <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle"/></a></pg:next>&nbsp;
                                        <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle"/></a></pg:last>
                                        </pg:index>
                                </td>
                            </tr>
                        </pg:pager></c:if>
                    <c:if test="${empty de4ApprovalList}">
                        <tr  class="headingSpas" bgcolor="#FFFFFF" height="20">
                            <%if (userFunctionalities.contains("101")) {%>
                            <td valign="bottom" colspan="5" align="center" style="padding-top:10px;color: red"> <bean:message key="label.common.noRecordFound" /></td>
                                    <%} else {%>
                               <td valign="bottom" colspan="7" align="center" style="padding-top:10px;color: red"> <bean:message key="label.common.noRecordFound" /></td>
                                    <%}%>
                            
                        </tr>
                    </c:if>
                </table>
              </form>
            </div>
        </div>
    </center>
</div>


