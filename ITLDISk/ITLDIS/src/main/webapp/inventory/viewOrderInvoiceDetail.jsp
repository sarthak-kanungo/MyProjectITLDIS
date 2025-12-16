<%-- 
    Document   : viewOrderInvoiceDetail
    Created on : 21-Feb-2015, 10:19:46
    Author     : kundan.rastogi
--%>


<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

    function Export(wClaimNo,flag)
    {
       // alert(wClaimNo);
       window.location.href='<%=cntxpath%>/warrantyAction.do?option=viewWarrantyClaimDetail&eType=export&warrntyClaimNo='+wClaimNo+'&flag='+flag;
    }
</script>
<div class="contentmain1">
    <div class="breadcrumb_container">
         <ul class="hText">
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=viewOrderInvoice'><bean:message key="label.spare.viewOrderInvoice" /></a></li>
            <li class="breadcrumb_link"> ${inventoryForm.invNo}</li>
         </ul>
     </div>
    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.spare.viewOrderInvDetail" /> </h1>
                <table width="100%" border="0" align="center" cellpadding="4" cellspacing="4" >
                    <input type="hidden" name="" value="${inventoryForm.invNo}">
                    <tr>
                        <td>
                            <table width="100%" border="0" cellspacing="0" cellpadding="4" align="center">
                                <tr bgcolor="#eeeeee" height="20" >
                                    <td colspan="6" class="headingSpas" align="left" >
                                        <label>
                                            <B><bean:message key="label.spare.invoiceInfo" /></B>
                                        </label>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="15%" align="right" ><label><bean:message key="label.common.InvoiceNo" /> :</label></td>
                                    <td width="20%" align="left">${inventoryForm.invNo}</td>
                                    <td width="15%" align="right"><label><bean:message key="label.common.InvoiceDate" /> :</label></td>
                                    <td  width="15%" align="left">${inventoryForm.invDate}</td>
                                    <td width="15%" align="right"><label><bean:message key="label.order.lrNo" /> :</label></td>
                                    <td  width="20%" align="left">${inventoryForm.lrNo}
                                    </td>
                                </tr>
                                <tr>
                                    <td width="15%" align="right"><label><bean:message key="label.order.transporterName" /> :</label></td>
                                    <td width="20%" align="left"> ${inventoryForm.transporterName}</td>
                                    <td width="15%" align="right" ><label><bean:message key="label.order.shippmentDate" /> :</label></td>
                                    <td width="15%" align="left">${inventoryForm.shipmentDate}</td>
                                    <td width="15%" align="right"><label><bean:message key="label.order.permitNo" /> :</label></td>
                                    <td width="20%" align="left"> ${inventoryForm.permitNo}</td>
                                </tr>
                                <tr  height="10" >
                                    <td colspan="6" class="headingSpas" align="left" >
                                    </td>
                                </tr>
                                <tr bgcolor="#eeeeee" height="20" >
                                    <td colspan="6" class="headingSpas" align="left" >
                                        <label >
                                            <B><bean:message key="label.catalogue.partDetails" /></B>
                                        </label>
                                    </td>
                                </tr>
                                <tr  height="20" >
                                    <td colspan="6" class="headingSpas" align="left" >
                                        <table  id="data" width="100%" border=0 cellspacing=1 cellpadding=2 bordercolor=#cccccc bgcolor=#cccccc >
                                            <tr bgcolor="#eeeeee">
                                                <th  align="center" width="5%" style="padding-left:5px; "><bean:message key="label.warranty.S.No" /> </th>
                                                <th align="left" width="10%" style="padding-left:5px; white-space: nowrap" ><bean:message key="label.order.suppOrderNo" /></th>
                                                <th align="left" width="10%" style="padding-left:5px; " ><bean:message key="label.common.invoice.ordered.part" /></th>
                                                <th align="left" width="10%" style="padding-left:5px; " ><bean:message key="label.common.invoice.shiped.part" /></th>
                                                <th align="left" width="30%" style="padding-left:5px; "><bean:message key="label.common.partDesc" /></th>
                                                <%--<th align="left"  width="8%" style="padding-left:5px; "><bean:message key="label.catalogue.status" /></th>--%>
                                                <th align="center"  width="8%" style="padding-left:5px; "><bean:message key="label.catalogue.quantity" /></th>
                                                <th align="right" width="10%" style="padding-right:5px; "><bean:message key="label.common.unitprice_order" /></th>
                                                <th align="right" width="10%" style="padding-right:5px; "><bean:message key="label.common.amt_small" /></th>
                                            </tr>
                                            <c:set var="i" value="1"/>
                                            <c:forEach items="${partList}" var="partList">
                                                <tr >
                                                    <td align="center" bgcolor="#FFFFFF"  class="headingSpas"  >${i}.</td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${partList.erpOrderNo}" class="headingSpas" style="padding-left:5px; " >
                                                        ${partList.erpOrderNo}
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${partList.partno}" class="headingSpas" style="padding-left:5px; " >
                                                        ${partList.partno}
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${partList.orderedPartNo}" class="headingSpas" style="padding-left:5px; " >
                                                        ${partList.orderedPartNo}
                                                    </td>

                                                    <td align="left" bgcolor="#FFFFFF" title="${partList.part_desc}" class="headingSpas" style="padding-left:5px; " >
                                                        ${partList.part_desc}
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" title="${partList.qty}" class="headingSpas" style="padding-left:5px; " >
                                                        ${partList.qty}
                                                    </td>
                                                    <td align="right" bgcolor="#FFFFFF" title="${partList.unitValue}" class="headingSpas" style="padding-right:5px; " >
                                                        ${partList.unitValue}
                                                    </td>
                                                    <td align="right" bgcolor="#FFFFFF" title="${partList.amountPerPrice}" class="headingSpas" style="padding-right:5px; " >
                                                        ${partList.amountPerPrice}
                                                    </td>
                                                </tr>
                                                <c:set var="i" value="${i+1}"/>
                                            </c:forEach>
                                                <tr bgcolor="#eeeeee">
                                                    <td colspan="7" bgcolor="#FFFFFF" align="right" style="padding-right:5px;"><strong><bean:message key="label.common.totalAmmount" /></strong>&nbsp;&nbsp</td>
                                                    <td   align="right" bgcolor="#FFFFFF" title="${inventoryForm.totalAmont}" style="padding-right:5px; ">${inventoryForm.totalAmont}</td>
                                                </tr>
                                                <tr bgcolor="#eeeeee">
                                                    <td colspan="7" bgcolor="#FFFFFF" align="right" style="padding-right:5px;"><strong><bean:message key="label.order.totalInvoiceAmount" /></strong>&nbsp;&nbsp;<I><bean:message key="label.orderInvice.inclusiveAllCharges" /></I></td>
                                                    <td   align="right" bgcolor="#FFFFFF" title="${inventoryForm.finalamount}" style="padding-right:5px; ">${inventoryForm.finalamount}</td>
                                                </tr>

                                        </table>
                                    </td>
                                </tr>
                            </table>
                </table>
           </div>
        </div>
    </center>
</div>




