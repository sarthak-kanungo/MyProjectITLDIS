
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page contentType="application/vnd.ms-excel" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
            response.setHeader("Content-Disposition", "attachment; filename=View_Claim_Report.xls");
%>

<html lang="en">
    <head>  <meta charset="utf-8">
        <title>DIS</title>
        <script src="JS/Master_290414.js"></script>
        
    </head>
    <body>
        <div class="contentmain1">
            <center>
                <div class="content_right1">
                    <div class="con_slidediv1" style="position: relative;width: 100%">
                        <table width=100%  border=1 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                            <c:if test="${empty claimReportList}">
                                <tr  bgcolor="#FFFFFF" height="20">
                                    <td colspan="5" valign="bottom" align="center" class="headingSpas" style="padding-top:10px;color: red">
                                        <h3><bean:message key="label.common.noRecordFound" /></h3>
                                    </td>
                                </tr>
                            </c:if>

                            <c:if test="${!empty claimReportList}">
                                <tr>
                                    <td>
                                        <table width="100%" border=1 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor="#cccccc" >
                                           <%-- <tr bgcolor="#FFFFFF" >
                                                <td colspan="12" align="center" class="headingSpas" style="padding-bottom: 18px"><h3> View Report</h3> </td>
                                            </tr>  --%>
                                            <tr bgcolor="#eeeeee" height="20">
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px;font-size: 15" >
                                                    <bean:message key="label.warranty.S.No" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15" >
                                                    <bean:message key="label.common.ClaimNo" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center" nowrap style="padding-left:5px;padding-right:5px; font-size: 15" >
                                                    <bean:message key="label.warranty.claimDate" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15" >
                                                    <bean:message key="label.common.jobcardno" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15" >
                                                    <bean:message key="label.common.jobcardCloseDate" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15" >
                                                    <bean:message key="label.warranty.TMSRefNo" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15" >
                                                    <bean:message key="label.common.Chassisno" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px">
                                                    <bean:message key="label.common.hrm" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px">
                                                    <bean:message key="label.common.state" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px" >
                                                    <bean:message key="label.common.dealerCode" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px" >
                                                    <bean:message key="label.common.dealerName" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px" >
                                                    <bean:message key="label.common.location" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px"  >
                                                    <bean:message key="label.common.status" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px" >
                                                    <bean:message key="label.common.packingNo" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px"  >
                                                    <bean:message key="label.common.packingDate" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px"  >
                                                    <bean:message key="label.common.dispatchedFor" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px"  >
                                                    <bean:message key="label.common.dispatchedThrough" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.common.dispatchedDate" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px" >
                                                    <bean:message key="label.common.consignmentNo" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px" >
                                                    <bean:message key="label.common.acknowledgeDate" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.common.approvedDate" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.common.sapclaimno" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="lable.common.gstInvoiceNo" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="lable.common.gstInvoiceDate" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.common.taxInvoiceUploadDate" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.common.taxInvoiceReceivedDate" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.common.creditNoteNo" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="lable.common.creditNoteDate" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="lable.common.creditNoteAmount" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center"  style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.common.ageingJobCardClosureDateToClaimGenrationDate" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center"  style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.common.ageingClaimGenerationDateToClaimDispatchedDate" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center"  style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.common.ageingClaimReceivedDateToClaimApprovedDate" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center"  style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.common.ageingClaimApprovedDateToTaxInvoiceSubmittedDate" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center"  style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.common.ageingClaimInvoiceSubmittedDateToCreditNoteDate" />
                                                </td>
                                                
                                                <td bgcolor="#cccccc" align="center"  style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.common.taxInvoiceStatus" />
                                                </td>
                                              
                                                
                                            </tr>

                                            <c:set var="sno" value="1"></c:set>
                                            <logic:iterate id="claimReportList" name="claimReportList">
                                                <tr  height="20" bgcolor="#FFFFFF">                                                    
                                                    <td nowrap align="left">
                                                        <span>${sno}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${claimReportList.warrantyClaimNo}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${claimReportList.claimDate}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${claimReportList.jobCardNo}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${claimReportList.jobcardCloseDate}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${claimReportList.tmsRefNo}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${claimReportList.vinNo}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${claimReportList.hmr}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${claimReportList.stateName}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${claimReportList.dealer_code}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.dealerName}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.location}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.claimStatus}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.packingNo}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.packingDate}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.dispatchFor}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.dispatchBy}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.dispatchDate}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.courierNo}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.acknowlegdeDate}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.approvedDate}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.sapWarrantyClaimNo}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.invNo}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.invDate}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.taxInvoiceUploadDate}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.taxInvoiceReceivedDate}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.sapCreditNo}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.sapCreditDate}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.sapCreditAmount}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.jobCardClosureDateToClaimGenrationDate}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.claimGenrationDateToClaimDispatchedDate}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.claimReceivedDateToClaimApprovedDate}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.claimApprovedDateToTaxInvoiceSubmittedDate}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.claimInvoiceSubmittedDateToCreditNoteDate}</span>
                                                    </td>
                                                    
                                                    <td nowrap align="center">
                                                        <span>${claimReportList.taxInvoiceStatus}</span>
                                                    </td>
                                                                                                                                              
                                                </tr>
                                                <c:set var="sno" value="${sno + 1 }" />
                                            </logic:iterate>
                                        </table>
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