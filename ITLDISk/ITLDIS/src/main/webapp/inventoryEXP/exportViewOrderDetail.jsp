<%--
    Document   : esportViewOrderDetail
    Created on : 05-Jan-2016, 11:10:56
    Author     : ashutosh.kumar
--%>


<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
            response.setHeader("Content-Disposition", "attachment; filename=viewOrderDetail.xls");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>



<center>
    <div class="content_right1">
        <div class="con_slidediv1" style="position: relative;width: 100%">
            <h1 class="hText"><bean:message key="label.spare.viewOrderDetail" /> </h1>
            <table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" >
                <input type="hidden" name="" value="${inventoryForm.newPoNo}">
                <tr>
                    <td>
                        <table width="100%" border="0" cellspacing="0" cellpadding="4" align="center">
                            <td align="right" colspan="8">
                                <input type="button" value="Export" onclick="Export('${inventoryForm.newPoNo}')"/>
                            </td>
                            <tr bgcolor="#eeeeee" height="20" >
                                <td colspan="8" class="headingSpas" align="left" >
                                    <label>
                                        <B><bean:message key="label.spare.orderInfo" /> </B>
                                    </label>
                                </td>
                            </tr>
                            <tr>
                                <td width="12%" align="right" ><label><bean:message key="label.spare.poNo" /> :</label></td>
                                <td width="12%" align="left">${inventoryForm.newPoNo}</td>
                                <td width="12%" align="right"><label><bean:message key="label.spare.PODate" /> :</label></td>
                                <td  width="14%" align="left">${inventoryForm.poDate}</td>
                                <td width="12%" align="right"><label><bean:message key="label.spare.consigneeName" /> :</label></td>
                                <td  width="14%" align="left">
                                    <%if (userFunctionalities.contains("101")) {%>
                                    ${inventoryForm.consigneeName}
                                    <%} else {%>
                                    <c:if test="${inventoryForm.consigneeCode eq '-' && inventoryForm.consigneeName eq '-'}">
                                        - -
                                    </c:if>
                                    <c:if test="${inventoryForm.consigneeCode ne '-' && inventoryForm.consigneeName ne '-'}">
                                        ${inventoryForm.consigneeCode} [ ${inventoryForm.consigneeName} ]
                                    </c:if>
                                    <%}%>
                                </td>
                                <%--added by satendra--%>
                                <td width="12%" align="right">&nbsp;</td>
                                <td  width="14%" align="left">&nbsp;</td>

                            </tr>
                            <c:if test="${inventoryForm.orderType eq 'VOR'}">
                                <tr>
                                    <td width="12%" align="right"><label><bean:message key="label.common.capital.jobcardno" /> :</label></td>
                                    <td width="12%" align="left"> ${inventoryForm.firNo}</td>
                                    <td width="12%" align="right" ><label><bean:message key="label.catalogue.chassisNo" /> :</label></td>
                                    <td width="12%" align="left">${inventoryForm.chassisNo}</td>
                                    <td width="12%" align="right"><label><bean:message key="label.common.engineno" /> :</label></td>
                                    <td  width="14%" align="left">${inventoryForm.engineNo}</td>
                                    <td width="12%" align="right"><label><bean:message key="label.warranty.modelNo" /> :</label></td>
                                    <td width="12%" align="left"> ${inventoryForm.modelNo}</td>
                                </tr>
                            </c:if>
                            <tr>
                                <td width="12%" align="right"><label><bean:message key="label.spare.orderType" /> :</label></td>
                                <td width="12%" align="left"> ${inventoryForm.orderType}</td>
                                <td width="12%" align="right" ><label><bean:message key="label.spare.orderStatus" /> :</label></td>
                                <td width="12%" align="left">${inventoryForm.status}</td>
                                <td width="12%" align="right"><label><bean:message key="label.common.modeOfDispatch" /> :</label></td>
                                <td width="14%" align="left"> ${inventoryForm.deliveryDesc}</td>
                            </tr>
                            <tr>

                                <td width="12%" align="right" ><label><bean:message key="label.spare.consigneeCountry" /> :</label></td>
                                <td width="14%" align="left">${inventoryForm.consigneeCountry}</td>

                                <td width="12%" align="right" ><label><bean:message key="label.spare.destinationPlace" /> :</label></td>
                                <td width="14%" align="left">${inventoryForm.destinationPlace}</td>

                                <td width="12%" align="right" ><label><bean:message key="label.common.order.remark" /> :</label></td>
                                <td width="14%" align="left">${inventoryForm.erpRemarks}</td>

                            </tr>
                            <tr  height="10" >
                                <td colspan="6" class="headingSpas" align="left" >
                                </td>
                            </tr>
                            <tr bgcolor="#eeeeee" height="20" >
                                <td colspan="8" class="headingSpas" align="left" >
                                    <label >
                                        <B><bean:message key="label.catalogue.partDetails" /></B>
                                    </label>
                                </td>
                            </tr>
                            <tr  height="20" >
                                <td colspan="9" class="headingSpas" align="left" >
                                    <table  id="data" width="100%" border=0 cellspacing=1 cellpadding=2 bordercolor=#cccccc bgcolor=#cccccc >
                                        <tr bgcolor="#eeeeee">
                                            <th  align="center" width="7%" style="padding-left:5px; "><bean:message key="label.warranty.S.No" /> </th>
                                            <th align="left" width="20%" style="padding-left:5px; " ><bean:message key="label.catalogue.partCode" /></th>
                                            <th align="left" width="37%" style="padding-left:5px; "><bean:message key="label.common.partDesc" /></th>
                                            <%--<th align="left"  width="8%" style="padding-left:5px; "><bean:message key="label.catalogue.status" /></th>--%>
                                            <th align="center"  width="8%" style="padding-left:5px; "><bean:message key="label.catalogue.ordered.quantity" /></th>
                                            <th align="center"  width="8%" style="padding-left:5px; "><bean:message key="label.catalogue.pending.quantity" /></th>
                                            <th align="right" width="10%" style="padding-right:5px; "><bean:message key="label.common.unitprice_small" /></th>
                                            <th align="right" width="10%" style="padding-right:5px; "><bean:message key="label.common.amt_small" /></th>
                                            <th align="right" width="10%" style="padding-right:5px; ">&nbsp;</th>
                                        </tr>
                                        <c:set var="i" value="1"/>
                                        <c:forEach items="${partList}" var="partList">
                                            <tr >
                                                <td align="center" bgcolor="#FFFFFF"  class="headingSpas"  >${i}.</td>
                                                <td align="left" bgcolor="#FFFFFF" title="${partList.partno}" class="headingSpas" style="padding-left:5px; " >
                                                    ${partList.partno}
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" title="${partList.part_desc}" class="headingSpas" style="padding-left:5px; " >
                                                    ${partList.part_desc}
                                                </td>
                                                <td align="center" bgcolor="#FFFFFF" title="${partList.qty}" class="headingSpas" style="padding-left:5px; " >
                                                    ${partList.qty}
                                                </td>
                                                <td align="center" bgcolor="#FFFFFF" title="${partList.qty}" class="headingSpas" style="padding-left:5px; " >
                                                    ${partList.erpPendingQty}
                                                </td>
                                                <td align="right" bgcolor="#FFFFFF" title="${partList.finalamount}" class="headingSpas" style="padding-right:5px; " >
                                                    ${partList.finalamount}
                                                </td>
                                                <td align="right" bgcolor="#FFFFFF" title="${partList.amountPerPrice}" class="headingSpas" style="padding-right:5px; " >
                                                    ${partList.amountPerPrice}
                                                </td>
                                                <td align="right" bgcolor="#FFFFFF" title="${partList.amountPerPrice}" class="headingSpas" style="padding-right:5px; " >
                                                    &nbsp;
                                                </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}"/>
                                        </c:forEach>
                                        <tr bgcolor="#eeeeee">
                                            <td colspan="6" bgcolor="#FFFFFF" align="right" style="padding-right:5px;"><strong><bean:message key="label.newOrder.torderamount" /></strong></td>
                                            <td   align="right" bgcolor="#FFFFFF" title="${inventoryForm.totalAmont}" style="padding-right:5px; ">${inventoryForm.totalAmont}</td>
                                            <td   align="right" bgcolor="#FFFFFF" title="${inventoryForm.totalAmont}" style="padding-right:5px; ">  &nbsp;</td>
                                        </tr>
                                    </table>
                                </td>
                            </tr>
                        </table>
                        <c:if test="${!empty inventoryForm.spOrderInvoices}">
                            <table id="invoicePartData" width="100%" border=0 cellspacing=1 cellpadding=2 >
                                <tr bgcolor="#eeeeee" height="20" >
                                    <td colspan="16" class="headingSpas" align="left" style="padding-top:10px;"  >
                                        <label >
                                            <B><bean:message key="label.common.invoice.partdetails" /></B>
                                        </label>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                </table>
                <div style="width:100%; height:auto; overflow: auto; padding-bottom: 10px;">
                    <table width="98%" align="center" cellspacing="1" cellpadding="1"  class="headingSpas" border="0" bordercolor="#cccccc" bgcolor="#cccccc">
                        <tr bgcolor="#eeeeee">
                            <th  align="center" width="5%" style="padding-left:5px; white-space:  "><bean:message key="label.warranty.S.No" /> </th>
                            <th align="left" width="8%" style="padding-left:5px; " ><bean:message key="label.common.InvoiceNo" /></th>
                            <th align="left" width="8%" style="padding-left:5px; "><bean:message key="label.common.InvoiceDate" /></th>
                            <th align="left"  width="8%" style="padding-left:5px; "><bean:message key="label.common.invoice.ordered.part" /></th>
                            <th align="left"  width="8%" style="padding-left:5px; "><bean:message key="label.common.invoice.shiped.part" /></th>
                            <th align="left" width="20%" style="padding-left:5px; "><bean:message key="label.common.invoice.shiped.part.desc" /></th>
                            <th align="center" width="8%" style="padding-left:5px; "><bean:message key="label.common.invoice.shiped.qty" /></th>
                            <th align="right" width="8%" style="padding-left:5px; " ><bean:message key="label.common.invoice.rate" /> &nbsp;</th>
                            <th align="right" width="8%" style="padding-left:5px; "><bean:message key="label.common.invoice.amount" /> &nbsp;</th>
                            <th align="center"  width="8%" style="padding-left:5px; "><bean:message key="label.common.invoice.lrno" /></th>
                            <th align="center"  width="8%" style="padding-left:5px; "><bean:message key="label.order.shippmentDate" /></th>
                            <th align="left" width="30%" style="padding-left:5px; white-space: nowrap; "><bean:message key="label.order.transporterName" /></th>
                            <th align="left" width="8%" style="padding-left:5px; white-space: nowrap;"><bean:message key="label.order.permitNo"/></th>
                            <th align="left" width="8%" style="padding-left:5px; "><bean:message key="label.common.status"/></th>
                            <th align="left" width="8%" style="padding-left:5px; "><bean:message key="label.common.remarks"/></th>
                            <th align="center" width="8%" style="padding-left:5px; "><bean:message key="label.common.dealerAcceptanceStatus"/></th>
                        </tr>
                        <c:set var="g" value="1"/>
                        <c:forEach items="${inventoryForm.spOrderInvoices}" var="invoiceList">
                            <tr>
                                <td  align="center" width="5%" height="30" bgcolor="#FFFFFF"  class="headingSpas"  style="padding-left:5px; "> ${g} </td>
                                <td align="left" width="8%" bgcolor="#FFFFFF"  class="headingSpas"  style="padding-left:5px; " >${invoiceList[0]}</td>
                                <td align="left" width="8%" bgcolor="#FFFFFF"  class="headingSpas"  style="padding-left:5px; "> ${invoiceList[1]}</td>
                                <td align="left"  width="8%" bgcolor="#FFFFFF"  class="headingSpas"  style="padding-left:5px; ">${invoiceList[10]}</td>
                                <td align="left"  width="8%" bgcolor="#FFFFFF"  class="headingSpas"  style="padding-left:5px; ">${invoiceList[2]}</td>
                                <td align="left" width="20%" bgcolor="#FFFFFF"  class="headingSpas"  style="padding-left:5px;  ">${invoiceList[9]}</td>
                                <td align="center" width="8%" bgcolor="#FFFFFF"  class="headingSpas"  style="padding-left:5px; ">${invoiceList[3]}</td>
                                <td align="right" width="8%" bgcolor="#FFFFFF"  class="headingSpas"  style="padding-left:5px; " >
                                    <fmt:formatNumber pattern="0.00" value="${invoiceList[12]}"/>
                                </td>
                                <td align="right" width="8%" bgcolor="#FFFFFF"  class="headingSpas"  style="padding-left:5px; ">
                                    <fmt:formatNumber pattern="0.00" value="${invoiceList[3] *invoiceList[12] }"/>
                                </td>
                                <td align="center"  width="8%" bgcolor="#FFFFFF"  class="headingSpas"  style="padding-left:5px; ">${invoiceList[5]}</td>
                                <td align="center"  width="8%" bgcolor="#FFFFFF"  class="headingSpas"  style="padding-left:5px;">${invoiceList[6]}</td>
                                <td align="left" width="30%" bgcolor="#FFFFFF"  class="headingSpas"  style="padding-left:5px; ">${invoiceList[7]}</td>
                                <td align="left" width="8%" bgcolor="#FFFFFF"  class="headingSpas"  style="padding-left:5px; ">${invoiceList[8]}</td>
                                <td align="left" width="8%" bgcolor="#FFFFFF"  class="headingSpas"  style="padding-left:5px; ">${invoiceList[11]}</td>
                                <td align="left" width="8%" bgcolor="#FFFFFF"  class="headingSpas"  style="padding-left:5px; ">${invoiceList[14]}</td>
                                <td align="center" width="8%" bgcolor="#FFFFFF"  class="headingSpas"  style="padding-left:5px; ">${invoiceList[13]}</td>
                            </tr>

                            <c:set var="g" value="${g+1}"/>
                        </c:forEach>

                    </table>
                </c:if>
            </div>
        </div>
    </div>
</center>





