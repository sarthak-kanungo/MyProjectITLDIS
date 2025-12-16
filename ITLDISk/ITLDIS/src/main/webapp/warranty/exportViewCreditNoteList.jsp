<%-- 
    Document   : exportViewCreditNoteList
    Created on : 04-Feb-2015, 16:12:17
    Author     : kundan.rastogi
--%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*,dao.LoginDAO,beans.serviceForm" %>
<%@page contentType="application/vnd.ms-excel" pageEncoding="UTF-8"%>


<%
            response.setHeader("Content-Disposition", "attachment; filename=CreditNoteList.xls");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<c:if test="${!empty panding4SAPList}">
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 ><bean:message key="label.common.viewCreditNote" /></h1>

                <table width=100%  border=1 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>

                    <tr height=25 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <table width=100%  border=1 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                <tr bgcolor="#eeeeee" height="20">
                                    <td   align="center"class="headingSpas" width="4%" nowrap style="padding-left:5px; "><b><bean:message key="label.warranty.S.No" /></b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.ClaimNo" /></b></td>
                                    <%-- <td   align="left"  class="headingSpas" nowrap style="padding-left:5px; padding-right: 5px"><b><bean:message key="label.warranty.claimDate" /></b></td>--%>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.Chassisno" /></b></td>
                                    <%if (userFunctionalities.contains("101")) {%>
                                    <%} else {%>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.dealerName" /></b></td>
                                    <%}%>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.creditNote.claimAmount" /></b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b>Claim <bean:message key="label.common.status" /></b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.warranty.approvedAmmount" /></b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.creditNote.creditStatus" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.creditNote.creditNo" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.creditNote.creditDate" /></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.creditNote.creditAmount" /> </b></td>
                                </tr>
                                <c:set var="sno" value="1"></c:set>
                                <logic:iterate id="dataList" name="panding4SAPList">
                                    <tr  bgcolor="#eeeeee" height="20">
                                        <td align="center" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                        <td align="left" bgcolor="#FFFFFF" title="${dataList.warrantyClaimNo}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span>
                                                <%--<a href="warrantyAction.do?option=viewWarrantyClaimDetail&warrntyClaimNo=${dataList.warrantyClaimNo}&flag=pendSAP"> --%>
                                                    ${dataList.warrantyClaimNo}
                                               <%-- </a>--%>
                                            </span>
                                        </td>
                                       <%-- <td align="left" bgcolor="#FFFFFF" title="${dataList.claimDate}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span>
                                                ${dataList.claimDate}
                                            </span>
                                        </td>--%>

                                        <td align="left" bgcolor="#FFFFFF" title="${dataList.vinNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span >
                                                ${dataList.vinNo}
                                            </span>
                                        </td>
                                        <%if (userFunctionalities.contains("101")) {%>
                                        <%} else {%>
                                        <td align="left" bgcolor="#FFFFFF" title="${dataList.dealerName}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span>
                                                ${dataList.dealer_code} [${dataList.dealerName}] [${dataList.location}]
                                            </span>
                                        </td>
                                        <%}%>
                                        <td align="right" bgcolor="#FFFFFF" title="${dataList.totalClaimValue}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span>
                                                ${dataList.totalClaimValue}
                                            </span>
                                        </td>
                                        <td align="left" bgcolor="#FFFFFF" title="${dataList.claimStatus}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span>
                                                ${dataList.claimStatus}
                                            </span>
                                        </td>

                                        <td align="right" bgcolor="#FFFFFF" title="${dataList.totalApproveAmmount}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span>
                                                ${dataList.totalApproveAmmount}
                                            </span>
                                        </td>
                                        <td align="left" bgcolor="#FFFFFF" title="${dataList.sapSyncStatus}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span>
                                                ${dataList.sapSyncStatus}
                                            </span>
                                        </td>
                                        <td align="left" bgcolor="#FFFFFF" title="${dataList.sapCreditNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span>
                                                ${dataList.sapCreditNo}
                                            </span>
                                        </td>
                                        <td align="left" bgcolor="#FFFFFF" title="${dataList.sapCreditDate}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span>
                                                ${dataList.sapCreditDate}
                                            </span>
                                        </td>
                                        <td align="right" bgcolor="#FFFFFF" title="${dataList.sapCreditAmount}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                            <span>
                                                ${dataList.sapCreditAmount}
                                            </span>
                                        </td>

                                    </tr>
                                    <c:set var="sno" value="${sno + 1 }" />
                                </logic:iterate>


                            </table>

                        </td>
                    </tr>
                </table>
            </div>
        </div>
    </center>
</c:if>