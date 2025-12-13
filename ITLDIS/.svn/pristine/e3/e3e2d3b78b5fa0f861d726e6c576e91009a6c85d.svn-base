

<!DOCTYPE html>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*" %>
<%@page contentType="application/vnd.ms-excel" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,dao.LoginDAO,beans.serviceForm" %>
<%
     response.setHeader("Content-Disposition", "attachment; filename=TransactionList.xls");
                 Vector userFunctionalities = (Vector) session.getAttribute("userFun");
                 LoginDAO dao = new LoginDAO();
                 String dealerCode = "";
                 if (userFunctionalities.contains("101")) {
                      dealerCode = (String) session.getAttribute("dealerCode");
                 } else {
                     dealerCode = "" + request.getAttribute("deCode");
                }
                 serviceForm sf = dao.getDealerAddress(dealerCode);
                 String locName = sf.getLocationName();
                 String dealerName = sf.getDealerName();
%>
<c:set var="dCode" value='<%=dealerCode%>'/>
<c:set var="dName" value='<%=dealerName%>'/>
<c:set var="locName" value='<%=locName%>'/>

<html lang="en">
    <head>  <meta charset="utf-8">
        <title>DIS</title>
        <script src="JS/Master_290414.js"></script>
        <link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >
    </head>
    <body>
  <div class="contentmain1">

       <center>
         <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h3>
                    <c:if test="${action eq 'Invoice'}">
                        <bean:message key="label.spares.exportInvoice" /> <bean:message key="label.spares.exportfrom" /> ${servForm.fromdate} to ${servForm.todate}  <%--[${dCode}] [${dName}] [${locName}]--%>
                    </c:if>
                    <c:if test="${action eq 'CounterSale'}">
                        <bean:message key="label.spares.exportCounterSale" /> <bean:message key="label.spares.exportfrom" />  ${servForm.fromdate} to ${servForm.todate}
                    </c:if>
                    <c:if test="${action eq 'InvOtherDealer'}">
                        <bean:message key="label.spares.exportInvOtherDealer" /> <bean:message key="label.spares.exportfrom" />  ${servForm.fromdate} to ${servForm.todate}
                    </c:if>
                </h3>
            <table id="data" width=100%  border=1 cellspacing=1  cellpadding=4 bordercolor=#cccccc   >

                <c:if test="${empty invoiceList4print}">
                    <tr  class="headingSpas" bgcolor="#FFFFFF" height="20">
                        <td valign="bottom" align="center" style="padding-top:10px;color: red"><bean:message key="label.common.noRecordFound" /></td>
                    </tr>
                </c:if>

   <c:if test="${action eq 'Invoice'}">
               <c:if test="${!empty invoiceList4print}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                               
                                    <table width=100%  border=1 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.sno" />
                                            </td>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.InvoiceNo" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.InvoiceDate" />
                                            </td>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.CustomerName" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.contactno" />
                                            </td>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.referenceNo" />
                                            </td>
                                            <td nowrap align="right" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.invoice" />  <bean:message key="label.common.amt_small" />
                                            </td>
                                           <%if (userFunctionalities.contains("101")) {%>
                                           <%} else{%>
                                            <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.dealerName" />
                                            </td>
                                           <%-- <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.location" />
                                            </td>--%>
                                            <%}%>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="invoiceList4print" name="invoiceList4print">
                                        
                                                <tr  bgcolor="#eeeeee" height="20">
                                                    <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                    <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                    ${invoiceList4print.invoiceno}
                                                    </td>
                                                    <td align="left" nowrap   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${invoiceList4print.invoiceDate}</td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.customerName}</span></td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.mobilePhone}</span></td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.refNo}</span></td>
                                                    <td align="right"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.totalEstimate}</span></td>

                                                    <%if (userFunctionalities.contains("101")) {%>
                                                    <%} else{%>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>${invoiceList4print.dealercode} [${invoiceList4print.dealerName}] [${invoiceList4print.locationName}]</span></td>
                                                    <%--<td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>${invoiceList4print.locationName}</span></td>--%>
                                                    <%}%>
                                                </tr>
                                         
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                       

                                    </table>
                              
                            </td>
                        </tr>
                    </c:if>
               </c:if>


         <c:if test="${action eq 'CounterSale'}">
               <c:if test="${!empty invoiceList4print}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                               
                                    <table width=100%  border=1 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.sno" />
                                            </td>
                                             <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spares.returnNo" />
                                            </td>
                                             <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.saleReturn.ReturnDate" />
                                            </td>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.InvoiceNo" />
                                            </td>

                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                               <bean:message key="label.saleReturn.ReturnBy" />
                                            </td>
                                           <%-- <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.contactno" />
                                            </td>--%>

                                            <td nowrap align="right" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.invoice" />  <bean:message key="label.common.amt_small" />
                                            </td>
                                           <%if (userFunctionalities.contains("101")) {%>
                                           <%} else{%>
                                            <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.dealerName" />
                                            </td>
                                           <%-- <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.location" />
                                            </td>--%>
                                            <%}%>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="invoiceList4print" name="invoiceList4print">
                                          
                                                <tr  bgcolor="#eeeeee" height="20">
                                                    <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.refNo}</span></td>
                                                    <td align="left" nowrap   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${invoiceList4print.invoiceDate}</td>
                                                    <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                     ${invoiceList4print.invoiceno}
                                                    </td>

                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.customerName}</span></td>
                                                   <%-- <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.mobilePhone}</span></td>--%>

                                                    <td align="right"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.totalEstimate}</span></td>

                                                    <%if (userFunctionalities.contains("101")) {%>
                                                    <%} else{%>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>${invoiceList4print.dealercode} [${invoiceList4print.dealerName}] [${invoiceList4print.locationName}]</span></td>
                                                    <%--<td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>${invoiceList4print.locationName}</span></td>--%>
                                                    <%}%>
                                                </tr>
                                          
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                          
                                    </table>
                             
                            </td>
                        </tr>
                    </c:if>
               </c:if>



             <c:if test="${action eq 'InvOtherDealer'}">
               <c:if test="${!empty invoiceList4print}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                               
                                    <table width=100%  border=1 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.sno" />
                                            </td>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                            <bean:message key="label.spares.refNo" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                            <bean:message key="label.common.billdate" />
                                            </td>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.vandorName" />
                                            </td>
                                           <%-- <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.contactno" />
                                            </td>--%>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.billNo" />  
                                            </td>
                                            <td nowrap align="right" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.invoice" />  <bean:message key="label.common.amt_small" />
                                            </td>
                                           <%if (userFunctionalities.contains("101")) {%>
                                           <%} else{%>
                                            <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.dealerName" />
                                            </td>
                                           <%-- <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.location" />
                                            </td>--%>
                                            <%}%>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="invoiceList4print" name="invoiceList4print">
                                         
                                                <tr  bgcolor="#eeeeee" height="20">
                                                    <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                    <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                    ${invoiceList4print.invoiceno}
                                                    </td>
                                                    <td align="left" nowrap   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${invoiceList4print.invoiceDate}</td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.customerName}</span></td>
                                                    <%--<td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.mobilePhone}</span></td>--%>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.refNo}</span></td>
                                                    <td align="right"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.totalEstimate}</span></td>

                                                    <%if (userFunctionalities.contains("101")) {%>
                                                    <%} else{%>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>${invoiceList4print.dealercode} [${invoiceList4print.dealerName}] [${invoiceList4print.locationName}]</span></td>
                                                    <%--<td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>${invoiceList4print.locationName}</span></td>--%>
                                                    <%}%>
                                                </tr>
                                          
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                       

                                    </table>
                               
                            </td>
                        </tr>
                    </c:if>
               </c:if>

            <c:if test="${action eq 'grndetail'}">
               <c:if test="${!empty invoiceList4print}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                    <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.sno" />
                                            </td>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spares.grNo" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spares.grdate" />
                                            </td>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spares.invoiceno" />
                                            </td>

                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.invoice" />&nbsp; <bean:message key="label.common.date" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.warranty.receivedBy" />
                                            </td>

                                            <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.spares.receivedon" />
                                            </td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="invoiceList4print" name="invoiceList4print">
                                                <tr  bgcolor="#eeeeee" height="20">
                                                    <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                    <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                        <a href="#" class="grnlink" >
                                                        <span >${invoiceList4print.grNo}</span></a>
                                                   <%-- ${invoiceList4print.invoiceno}--%>
                                                    </td>
                                                    <td align="left" nowrap   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                        ${invoiceList4print.grStrDate}
                                                    </td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.invoiceNo}</span></td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >
                                                          <fmt:formatDate pattern="dd/MM/yyyy" value="${invoiceList4print.invDateStr}" />
                                                        </span></td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.receivedBy}</span></td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>
                                                       ${invoiceList4print.receivedOnStr}
                                                     <%--       <fmt:formatDate pattern="dd/MM/yyyy" value="${invoiceList4print.receivedOn}" />--%>
                                                        </span></td>
                                                </tr>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        <tr>
                                            <%if (userFunctionalities.contains("101")) {%>
                                            <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                            <%} else {%>
                                            <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                            <%}%>
                                            </td>
                                        </tr>

                                    </table>
                            </td>
                        </tr>
                    </c:if>
               </c:if>







       </table>
          </div>
   </div>
 </center>
 </div>