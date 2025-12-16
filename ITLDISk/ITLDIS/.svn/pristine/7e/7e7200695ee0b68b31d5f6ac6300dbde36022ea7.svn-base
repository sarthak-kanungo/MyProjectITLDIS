<%-- 
    Document   : packingDetails4View
    Created on : Dec 10, 2014, 2:20:27 PM
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
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<script type="text/javascript" language="javascript">
    function printConsignment()
    {
        var packingNo='${warrantyForm.packingNo}';
        var strURL="${pageContext.request.contextPath}/warrantyAction.do?option=packingDetails4View&packingNo="+packingNo+"&print=true";
        window.open(strURL,'iji','width=1000,height=950, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
    }
    function viewCourierCopyFile()
    {
        var courierCopyFileName='${warrantyForm.courierCopyFileName}';
        var strURL="${pageContext.request.contextPath}/DOCUMENTS/WARRANTY/"+courierCopyFileName+"";
        window.open(strURL,'courierCopyFileName','width=750,height=600, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
        window.moveTo(50, 0);
    }
    
</script>
<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.warranty.warranty" /></a></li>
            <li class="breadcrumb_link"><a href="<%=cntxpath%>/warrantyAction.do?option=viewPackingList"><bean:message key="label.common.viewPackingList" /></a></li>
            <li class="breadcrumb_link"><bean:message key="label.common.consignmentDetails" /></li>
        </ul>
    </div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1><bean:message key="label.common.consignmentDetails" /> ${warrantyForm.packingNo}</h1>
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" >
                    <tr bgcolor="#eeeeee" height="20" >
                        <td  class="headingSpas" align="left" width="90%">
                            <label>
                                <B><bean:message key="label.common.consignmentDetails"/></B>
                            </label>
                        </td>
                        <td align="right"><a href="#"><img src='<%=cntxpath%>/image/print-icon.png' alt='Get Suggestions' name="img1" border='0' title="print"  onclick="printConsignment();"/></a></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" >
                                <%--<tbody>--%>
                                <tr>
                                    <td width="15%"  align="right"><span class="headingSpas">
                                            <label><B><bean:message key="label.common.packingNo" />: </B></label></span>
                                    </td>
                                    <td align="left" width="15%" ><span class="headingSpas">${warrantyForm.packingNo}</span>
                                    </td>
                                    <td align="right" width="20%"><span class="headingSpas">
                                            <label><B><bean:message key="label.common.packingDate" />: </B></label>
                                        </span>
                                    </td>
                                    <td align="left" colspan="3"><span class="headingSpas">${warrantyForm.packingDate}</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td  align="right"><span class="headingSpas">
                                            <label><B><bean:message key="label.common.dispatchedFor" />: </B></label></span>
                                    </td>
                                    <td  align="left"  ><span class="headingSpas">${warrantyForm.dispatchFor}</span>
                                    </td>
                                    <td align="right" style=""><span class="headingSpas">
                                            <label><B><bean:message key="label.common.dispatchedThrough" />: </B></label>
                                        </span>
                                    </td>
                                    <td align="left"><span class="headingSpas">${warrantyForm.dispatchBy}</span>
                                    </td>
                                    <td align="right" style="" ><span class="headingSpas">
                                            <label><B><bean:message key="label.common.dispatchedDate" />: </B></label>
                                        </span></td>
                                    <td align="left"><span class="headingSpas">${warrantyForm.dispatchDate}</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td align="right"><span class="headingSpas">
                                            <label><B><bean:message key="label.common.courierNo" />: </B></label>
                                        </span>
                                    </td>
                                    <td align="left" style="white-space: nowrap"><span class="headingSpas">${warrantyForm.courierNo}</span>
                                    </td>
                                    <td align="right"><span class="headingSpas">
                                            <label><B><bean:message key="label.common.courierName" />: </B></label>
                                        </span></td>
                                    <td align="left" style="white-space: nowrap"><span class="headingSpas">${warrantyForm.courierName}</span>
                                    </td>
                                    <td align="right" style="" ><span class="headingSpas">
                                            <label><B> <bean:message key="label.common.courierCopyFile" />: </B></label>
                                        </span></td>
                                       <td align="left" id="saledatetd"><span class="headingSpas">
                                           <c:if test="${fn:length(warrantyForm.courierCopyFileName) gt 1}">
                                            <a href="javascript:void(0)" ><img src="${pageContext.request.contextPath}/images/history.jpg" height="18" width="18"  onclick="viewCourierCopyFile();" title="${warrantyForm.courierCopyFileName}"/></a>
                                        </c:if>
                                            <c:if test="${fn:length(warrantyForm.courierCopyFileName) eq 1}">
                                                -
                                             </c:if>
                                           </span>
                                    </td>
                                </tr>
                                <tr>
                                    <td  align="right"><span class="headingSpas">
                                            <label><B><bean:message key="label.warranty.receivedBy" />: </B></label></span>
                                    </td>
                                    <td align="left"  ><span class="headingSpas">${warrantyForm.receivedBy}</span>
                                    </td>
                                    <td align="right" ><span class="headingSpas">
                                            <label><B><bean:message key="label.warranty.receivedDate" />: </B></label>
                                        </span>
                                    </td>
                                    <td align="left" ><span class="headingSpas">${warrantyForm.receivedDate}</span>
                                    </td>
                                     <td  align="right"><span class="headingSpas">
                                            <label><B><bean:message key="label.common.storeNo" />: </B></label></span>
                                    </td>
                                    <td align="left"  ><span class="headingSpas">${warrantyForm.storeNoOfPackages}</span>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <%--</table>--%>
                    <c:if test="${!empty packingList}">
                        <tr><td colspan="2">
                                <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0" class="LiteBorder" >
                                    <%--<tr  height="20">
                                        <td align="left" class="headingSpas">
                                            <B><bean:message key="label.common.packingList" /></B>
                                        </td>
                                    </tr>
                                    <tr  bgcolor="#eeeeee" height="7">
                                    </tr>--%>

                                    <tr height=25 bgcolor="#FFFFFF">
                                        <td align= center class="headingSpas">
                                            <table width=100%  border=0 cellspacing=1 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc >
                                                <tr  bgcolor="#eeeeee" height="20%">
                                                    <td   align="center"  class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.warranty.S.No" /></b></td>
                                                    <td   align="left"   class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.warranty.warrantyClaimNo" /></b></td>
                                                    <td align="left" class="headingSpas" nowrap style="padding-left:5px;" ><B><bean:message key="label.common.partno_small" /></B></td>
                                                    <td align="left" class="headingSpas" nowrap style="padding-left:5px;" ><B><bean:message key="label.common.partdesc_small" /></B></td>
                                                    <td align="center" class="headingSpas" nowrap style="padding-left:5px;"><B><bean:message key="label.warranty.claimQty" />  </B></td>
                                                    <td align="center" class="headingSpas" nowrap style="padding-left:5px;"><B><bean:message key="label.warranty.dispatchQty" />   </B></td>
                                                    <td align="center" class="headingSpas" nowrap style="padding-left:5px;"><B><bean:message key="label.saleReturn.ReceivedQty" />   </B></td>
                                                    <td align="left" class="headingSpas" nowrap style="padding-left:5px;"><B><bean:message key="label.warranty.bixNo" />  </B></td>
                                                </tr>
                                                <c:set var="srNo" value="1"/>
                                                <c:forEach items="${packingList}" var="packingList">
                                                    <tr height="20%" bgcolor="#ffffff">
                                                        <td   align="center"  class="headingSpas" nowrap style="padding-left:5px;"><span>${srNo}</span></td>
                                                        <td   align="left"   class="headingSpas" nowrap style="padding-left:5px;"><span>${packingList.warrantyClaimNo}</span></td>
                                                        <td align="left" class="headingSpas" nowrap style="padding-left:5px;" ><span>${packingList.partNo}</span></td>
                                                        <td align="left" class="headingSpas" nowrap style="padding-left:5px;" ><span>${packingList.partDesc}</span></td>
                                                        <td align="center" class="headingSpas" nowrap style="padding-left:5px;"><span>${packingList.qty}</span></td>
                                                        <td align="center" class="headingSpas" nowrap style="padding-left:5px;"><span>${packingList.dispatchQty}</span></td>
                                                        <td align="center" class="headingSpas" nowrap style="padding-left:5px;"><span>${packingList.receivedQty}</span></td>
                                                        <td align="left" class="headingSpas" nowrap style="padding-left:5px;"><span>${packingList.boxNo}</span></td>
                                                    </tr>
                                                    <c:set var="srNo" value="${srNo+1}"/>
                                                </c:forEach>
                                                <%--<tr bgcolor="#FFFFFF" height="40">
                                                    <td align="left" >
                                                        <span class="headingSpas">
                                                            <label><B><bean:message key="label.common.anyobservation" /></B></label>
                                                        </span></td>
                                                    <td colspan="4" align="left"  width="80%"><span class="headingSpas">${pdiform.remark}</span>
                                                    </td>
                                                </tr>--%>
                                            </table>
                                        </td>
                                    </tr>
                                </table>

                            </td>
                        </tr></c:if>
                </table>
            </div>
        </div>
    </center>
</div>