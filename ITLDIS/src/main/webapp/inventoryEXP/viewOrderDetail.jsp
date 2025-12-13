<%-- 
    Document   : viewOrderDetail
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
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
         
    function Export(pono)
    {            
        window.location.href='<%=cntxpath%>/inventoryEXPAction.do?option=viewOrderDetail&etype=export&poNo='+pono+'&flag=check&etype=export';
    }
    
    function goEdit(poNo)
    {
        var poNo= document.getElementById("poNo").value;
        var ordType= document.getElementById("ordType").value;                      
        window.location.href="<%=cntxpath%>/inventoryEXPAction.do?option=createNewEXPOrder&poNo="+poNo+"&editPo=YES&odType="+Base64.encode(ordType);
    }
</script>

<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryEXPAction.do?option=viewEXPOrder'><bean:message key="label.spare.viewOrder" /></a></li>
            <li class="breadcrumb_link"> ${inventoryForm.newPoNo}</li>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.spare.viewOrderDetail" /> </h1>
                <table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" >
                    <input id="poNo" type="hidden" name="" value="${inventoryForm.newPoNo}">
                    <input id="ordType" type="hidden" name="" value="${inventoryForm.orderType}">
                    <tr>
                        <td>
                            <table width="100%" border="0" cellspacing="0" cellpadding="4" align="center">                               
                                <tr bgcolor="#eeeeee" height="20" >
                                    <td colspan="7" class="headingSpas" align="left" >
                                        <label>
                                            <B><bean:message key="label.spare.orderInfo" /> </B>
                                        </label>
                                    </td>                                  
                                    <td align="center" colspan="2" >
                                        <%if (userFunctionalities.contains("858")) {%>
                                        <c:if test="${inventoryForm.status eq 'OPEN'}">
                                            <input type="button" id="edit" value="Edit" onclick="goEdit(poNo)"/>
                                        </c:if>
                                        <%}%> 
                                    </td>                                    
                                </tr>
                                <tr>
                                    <td width="12%" align="right" nowrap ><label><bean:message key="label.spare.poNo" /> :</label></td>
                                    <td width="12%" align="left">${inventoryForm.newPoNo}</td>
                                    <td width="12%" align="right" nowrap><label><bean:message key="label.spare.PODate" /> :</label></td>
                                    <td  width="14%" align="left">${inventoryForm.poDate}</td>
                                    <td width="12%" align="right" nowrap><label><bean:message key="label.spare.orderType" /> :</label></td>
                                    <td width="12%" align="left"> ${inventoryForm.orderType}</td>
                                    <td width="12%" align="right" nowrap><label><bean:message key="label.spare.orderStatus" /> :</label></td>
                                    <td width="12%" align="left">${inventoryForm.status}</td>
                                    <td width="12%" align="right">&nbsp;</td>
                                    <td  width="14%" align="left">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td width="12%" align="right" nowrap><label><bean:message key="label.spare.dealRefNo" /> :</label></td>
                                    <td width="14%" align="left">${inventoryForm.dealerRefNo}</td>
                                    <td width="12%" align="right" nowrap><label><bean:message key="label.spare.consigneeName" /> :</label></td>
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
                                    <td width="12%" align="right" nowrap><label><bean:message key="label.spare.consigneeCountry" /> :</label></td>
                                    <td width="14%" align="left">${inventoryForm.consigneeCountry}</td>

                                    <td width="12%" align="right" nowrap><label><bean:message key="label.spare.destinationPlace" /> :</label></td>
                                    <td width="14%" align="left">${inventoryForm.destinationPlace}</td>
                                </tr>
                                <c:if test="${inventoryForm.orderType eq 'VOR'}">
                                    <tr>
                                        <td width="12%" align="right" nowrap><label><bean:message key="label.common.capital.jobcardno" /> :</label></td>
                                        <td width="12%" align="left"> ${inventoryForm.firNo}</td>
                                        <td width="12%" align="right" nowrap><label><bean:message key="label.catalogue.chassisNo" /> :</label></td>
                                        <td width="12%" align="left">${inventoryForm.chassisNo}</td>
                                        <td width="12%" align="right" nowrap><label><bean:message key="label.common.engineno" /> :</label></td>
                                        <td  width="14%" align="left">${inventoryForm.engineNo}</td>
                                        <td width="12%" align="right" nowrap><label><bean:message key="label.warranty.modelNo" /> :</label></td>
                                        <td width="12%" align="left"> ${inventoryForm.modelNo}</td>
                                    </tr>
                                </c:if>
                                <tr>
                                    <td width="12%" align="right" nowrap><label><bean:message key="label.common.modeOfDispatch" /> :</label></td>
                                    <td width="14%" align="left"> ${inventoryForm.deliveryDesc}</td>
                                    <td width="12%" align="right" nowrap><label><bean:message key="label.spare.paymentTerms" /> :</label></td>
                                    <td width="14%" align="left">${inventoryForm.paymentTerms}</td>
                                    <td width="12%" align="right" nowrap><label><bean:message key="label.spare.incoTerms" /> :</label></td>
                                    <td width="14%" align="left">${inventoryForm.incoTerms}</td>
                                    <td width="12%" align="right" nowrap><label><bean:message key="label.spare.dischargePortName" /> :</label></td>
                                    <td width="14%" align="left">${inventoryForm.dischargePort}</td>
                                </tr>
                                <tr>
                                    <td width="12%" align="right" nowrap><label><bean:message key="label.spare.PreCarriageBy" /> :</label></td>
                                    <td width="14%" align="left">${inventoryForm.precarriageBy}</td>
                                    <td width="12%" align="right" nowrap><label><bean:message key="label.spare.PlacePreCarrier" /> :</label></td>
                                    <td width="14%" align="left">${inventoryForm.placePreCarrier}</td>
                                    <td width="12%" align="right" nowrap><label><bean:message key="label.spare.comments" /> :</label></td>
                                    <td width="14%" align="left">${inventoryForm.comments}</td>
                                    <td width="12%" align="right" nowrap><label><bean:message key="label.common.order.remark" /> :</label></td>
                                    <td width="14%" align="left">${inventoryForm.erpRemarks}</td>
                                </tr>
                                <tr  height="10" >
                                    <td colspan="6" class="headingSpas" align="left" >
                                    </td>
                                </tr>
                                <tr bgcolor="#eeeeee" height="20" >
                                    <td colspan="9" class="headingSpas" align="left" >
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
                                                <th align="right" width="10%" style="padding-right:5px; "><bean:message key="label.common.unitprice_small" />&nbsp;(${inventoryForm.currency})</th>
                                                <th align="right" width="10%" style="padding-right:5px; "><bean:message key="label.common.amt_small" />&nbsp;(${inventoryForm.currency})</th>
                                            </tr>
                                            <c:set var="i" value="1"/>
                                            <c:set var="qty" value="0"/>
                                            <c:set var="pendingQty" value="0"/>
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
                                                    <td align="center" bgcolor="#FFFFFF" title="${partList.erpPendingQty}" class="headingSpas" style="padding-left:5px; " >
                                                        ${partList.erpPendingQty}
                                                    </td>
                                                    <td align="right" bgcolor="#FFFFFF" title="${partList.finalamount}" class="headingSpas" style="padding-right:5px; " >
                                                        ${partList.finalamount}
                                                    </td>
                                                    <td align="right" bgcolor="#FFFFFF" title="${partList.amountPerPrice}" class="headingSpas" style="padding-right:5px; " >
                                                        <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${partList.amountPerPrice}" />
                                                    </td>
                                                </tr>
                                                <c:set var="i" value="${i+1}"/>
                                                <c:set var="qty" value="${qty+partList.qty}"/>
                                                <c:set var="pendingQty" value="${pendingQty+partList.erpPendingQty}"/>

                                            </c:forEach>
                                                <tr bgcolor="#eeeeee">
                                                    <td colspan="3" bgcolor="#FFFFFF" align="right" style="padding-right:5px;padding-top: 5px"><strong><bean:message key="label.common.total" /></strong></td>
                                                    <td align="center" bgcolor="#FFFFFF" style="padding-left: 9px;padding-right:5px;padding-top: 5px"><strong>${qty}</strong></td>
                                                    <td align="center" bgcolor="#FFFFFF" style="padding-left: 9px;padding-right:5px;padding-top: 5px"><strong>${pendingQty}</strong></td>
                                                    <td align="right" bgcolor="#FFFFFF" style="padding-right:5px;"></td>
                                                    <td align="right" bgcolor="#FFFFFF" title="${inventoryForm.totalAmont}" style="padding-right:5px;padding-top: 5px "><strong>
                                                            <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${inventoryForm.totalAmont}" /></strong></td>
                                                </tr>
                                        </table>
                                    </td>
                                </tr>
                                <c:if test="${!empty piDetail}">
                                    <tr height="10" >
                                        <td colspan="6"  class="headingSpas" align="left" ></td>
                                    </tr>
                                    <tr bgcolor="#eeeeee" height="20" >
                                        <td colspan="9" class="headingSpas" align="left" width="100%">
                                            <label><B><bean:message key="label.spare.piDetails" /></B></label>
                                        </td>
                                    </tr>
                                    <tr height="20" >
                                        <td colspan="9" class="headingSpas" align="left" >
                                            <table id="data1" width="100%" border=0 cellspacing=1 cellpadding=2 bordercolor=#cccccc bgcolor=#cccccc >
                                                <tr bgcolor="#eeeeee">
                                                    <th  align="center" width="5%" style="padding-left:5px; "><bean:message key="label.warranty.S.No" /> </th>
                                                    <th align="left"  width="10%" style="padding-left:5px; "><bean:message key="label.spare.piNo" /></th>
                                                    <th align="left" width="10%" style="padding-left:5px; " ><bean:message key="label.common.invoice.ordered.part" /></th>
                                                    <th align="left" width="20%" style="padding-left:5px; "><bean:message key="label.common.partDescPI" />.</th>                                                    
                                                    <th align="center"  width="8%" style="padding-left:5px; "><bean:message key="label.common.orderedQtyPI" /></th>
                                                    <th align="center"  width="10%" style="padding-left:5px; "><bean:message key="label.common.status" /></th>
                                                    <th align="center" width="8%" style="padding-right:5px; "><bean:message key="label.common.leadTime" /></th>
                                                    <th align="left" width="10%" style="padding-right:5px; "><bean:message key="label.common.availablePartNo" /></th>
                                                    <th align="left" width="20%" style="padding-right:5px; "><bean:message key="label.common.avlPartDescPI" />.</th>
                                                    <th align="center" width="8%" style="padding-right:5px; "><bean:message key="label.common.piQty" /></th>
                                                    <th align="center" width="8%" style="padding-right:5px; "><bean:message key="label.common.pricePI" /><br>(${inventoryForm.currency})</th>
                                                    <th align="center" width="8%" style="padding-right:5px; "><bean:message key="label.common.amountPI" /><br>(${inventoryForm.currency})</th>
                                                    <th align="center" width="8%" style="padding-right:5px; "><bean:message key="label.spare.BookedQty" /></th>
                                                    <th align="center" width="8%" style="padding-right:5px; "><bean:message key="label.spare.PendingQty" /></th>
                                                </tr>
                                                <c:set var="i" value="1"/>
                                                <c:forEach items="${piDetail}" var="partList">
                                                    <tr >
                                                        <td align="center" bgcolor="#FFFFFF"  class="headingSpas"  >${i}.</td>                                                        
                                                        <td align="left" nowrap width="15%"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                            <span>
                                                                <a href="createPIAction.do?option=getPIDetailForView&piNo=${partList.piNo}&orderType=${inventoryForm.orderType}&buyerEditAllowed=OnlyView" >${partList.piNo} </a>
                                                            </span>
                                                        </td>
                                                        <td align="left" bgcolor="#FFFFFF" title="${partList.partNoPI}" class="headingSpas" style="padding-left:5px; " >
                                                            ${partList.partNoPI}
                                                        </td>
                                                        <td align="left" bgcolor="#FFFFFF" title="${partList.partDescPI}" class="headingSpas" style="padding-left:5px; " >
                                                            ${partList.partDescPI}
                                                        </td>                                                        
                                                        <td align="center" bgcolor="#FFFFFF" title="${partList.orderedQtyPI}" class="headingSpas" style="padding-left:5px; " >
                                                            ${partList.orderedQtyPI}
                                                        </td>
                                                        <td align="center" bgcolor="#FFFFFF" title="${partList.statusPI}" class="headingSpas" style="padding-right:5px; " >
                                                            ${partList.statusPI}
                                                        </td>
                                                        <td align="center" bgcolor="#FFFFFF" title="${partList.leadTimePI}" class="headingSpas" style="padding-right:5px; " >
                                                            ${partList.leadTimePI}
                                                        </td>
                                                        <td align="left" bgcolor="#FFFFFF" title="${partList.availablePartNoPI}" class="headingSpas" style="padding-right:5px; " >
                                                            ${partList.availablePartNoPI}
                                                        </td>
                                                        <td align="left" bgcolor="#FFFFFF" title="${partList.avlPartDescPI }" class="headingSpas" style="padding-right:5px; " >
                                                            ${partList.avlPartDescPI}
                                                        </td>
                                                        <td align="center" bgcolor="#FFFFFF" title="${partList.piQtyPI}" class="headingSpas" style="padding-right:5px; " >
                                                            ${partList.piQtyPI}
                                                        </td>
                                                        <td align="right" bgcolor="#FFFFFF" title="${partList.pricePI}" class="headingSpas" style="padding-right:5px; " >
                                                            ${partList.pricePI}
                                                        </td>
                                                        <td align="right" bgcolor="#FFFFFF" title="${partList.piQtyPI * partList.pricePI}" class="headingSpas" style="padding-right:5px; " >
                                                            <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${partList.piQtyPI * partList.pricePI}" />
                                                        </td>
                                                        <td align="right" bgcolor="#FFFFFF" title="${partList.bookedQtyPI}" class="headingSpas" style="padding-right:5px; " >
                                                            ${partList.bookedQtyPI}
                                                        </td>
                                                        <td align="right" bgcolor="#FFFFFF" title="${partList.pendingQtyPI}" class="headingSpas" style="padding-right:5px; " >
                                                            ${partList.pendingQtyPI}
                                                        </td>
                                                    </tr>
                                                    <c:set var="i" value="${i+1}"/>
                                                    <c:set var="total" value="${total + partList.piQtyPI * partList.pricePI }" />
                                                </c:forEach>
                                                <tr bgcolor="#eeeeee">
                                                    <td colspan="11" bgcolor="#FFFFFF" align="right" style="padding-right:5px;"><strong><bean:message key="label.newOrder.torderamount" />&nbsp;(${inventoryForm.currency})</strong></td>
                                                    <td align="right" bgcolor="#FFFFFF"  style="padding-right:5px; "><strong><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${total}" /></strong></td>
                                                    <td bgcolor="#FFFFFF">&nbsp;</td>
                                                    <td bgcolor="#FFFFFF">&nbsp;</td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </table>
                            </c:if>

                            <c:if test="${!empty inventoryForm.spOrderInvoices}">
                                <table id="invoicePartData" width="100%" border=0 cellspacing=1 cellpadding=2 >
                                    <tr bgcolor="#eeeeee" height="20" >
                                        <td colspan="14" class="headingSpas" align="left" style="padding-top:10px;"  >
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
                                <th align="left" width="8%" style="padding-left:5px; "><bean:message key="label.common.dealerAcceptanceStatus"/></th>
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
                                    <c:if test="${invoiceList[11] ne 'CANCELLED'}">
                                        <td align="left" width="8%" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; ">${invoiceList[11]}</td>
                                    </c:if>
                                    <c:if test="${invoiceList[11] eq 'CANCELLED'}">
                                        <td align="left" width="8%" bgcolor="#FFFFFF"  class="headingSpas" title="${invoiceList[14]}" style="padding-left:5px; ">${invoiceList[11]}</td>
                                    </c:if>
                                    <td align="left" width="8%" bgcolor="#FFFFFF"  class="headingSpas"  style="padding-left:5px; ">${invoiceList[13]}</td>
                                </tr>
                                <c:set var="g" value="${g+1}"/>
                            </c:forEach>
                            <tr>
                                <td colspan="15" align="left" bgcolor="#FFFFFF"  class="headingSpas" style="padding-top:10px;color: red"><bean:message key="label.common.remarkNote" /></td>
                            </tr>
                        </table>
                    </c:if>
                </div>
            </div>
        </div>
    </center>
</div>