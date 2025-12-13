<%--
    Document   : viewOrderInvDetReportList
    Created on : 07-Sep-2017, 15:26:21
    Author     : ashutosh.kumar
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
            String dealerFlag = "";
            if (!userFunctionalities.contains("101")) {
                dealerFlag = "true";
            }
%>
<script src="${pageContext.request.contextPath}/js/base64.js"></script>
<script src="${pageContext.request.contextPath}/js/intermediate.js"></script>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';

    <%-- function submitForm()
     {
         var dealerFlag='<%=dealerFlag%>';
         if(dealerFlag=='true')
         {
             var dealer=document.getElementById('Dealer Name').value;
             if(dealer=='')
             {
                 alert(not_blank_dropdown_validation_msg+'Dealer Name');
                 return false;
             }
         }
         if(document.getElementById("Range").checked==true){
             if(document.getElementById("From Date").value==""){
                 alert("Please Select From Date .");
                 document.getElementById("From Date").focus();
                 return false;
             }
             if(document.getElementById("To Date").value==""){
                 alert("Please Select To Date .");
                 document.getElementById("To Date").focus();
                 return false;
             }
         }

        var  fDate=document.getElementById("From Date").value;
        fDate = trim(fDate);
        var x =fDate;     //'1-1-2015';
        var a = x.split('/');
        var dateF = new Date (a[2], a[1] - 1,a[0]);

        var  tDate=document.getElementById("To Date").value;
        tDate = trim(tDate);
        var y =tDate;     //'17-1-2015';
        var b = y.split('/');
        var dateT = new Date (b[2], b[1] - 1,b[0]);

        //alert(dateF)
        //alert(dateT)
        if(dateF>dateT){
            alert(date_validation_msg)
            document.getElementById('From Date').focus();
            return false;
        }

        document.getElementById('eType').value="";
        document.getElementById("searchBy").submit();
    }--%>
        function disableDate(dis)
        {
            if(dis.checked){
                document.getElementById("From Date").disabled=false;
                document.getElementById("To Date").disabled=false;
            }else{
                document.getElementById("From Date").value="";
                document.getElementById("From Date").disabled=true;
                document.getElementById("To Date").value="";
                document.getElementById("To Date").disabled=true;
            }
        }
        function Export()
        {

            var dealerFlag='<%=dealerFlag%>';
            if(dealerFlag=='true')
            {
                var dealer=document.getElementById('Dealer Name').value;
                if(dealer=='')
                {
                    alert(not_blank_dropdown_validation_msg+'Dealer Name');
                    return false;
                }
            }
            if(document.getElementById("Range").checked==true){
                if(document.getElementById("From Date").value==""){
                    alert("Please Select From Date .");
                    document.getElementById("From Date").focus();
                    return false;
                }
                if(document.getElementById("To Date").value==""){
                    alert("Please Select To Date .");
                    document.getElementById("To Date").focus();
                    return false;
                }
            }

            var  fDate=document.getElementById("From Date").value;
            fDate = trim(fDate);
            var x =fDate;     //'1-1-2015';
            var a = x.split('/');
            var dateF = new Date (a[2], a[1] - 1,a[0]);

            var  tDate=document.getElementById("To Date").value;
            tDate = trim(tDate);
            var y =tDate;     //'17-1-2015';
            var b = y.split('/');
            var dateT = new Date (b[2], b[1] - 1,b[0]);

            //alert(dateF)
            //alert(dateT)
            if(dateF>dateT){
                alert(date_validation_msg)
                document.getElementById('From Date').focus();
                return false;
            }

            document.getElementById('eType').value="export";
            document.getElementById('searchBy').submit();
        }

    <%-- $(document).ready(function(){
         $('.viewOrderDetails').click(function(){
             //event.preventDefault();
             var url = "reportAction.do?option=viewOrderDetail&poNo="+Base64.encode($(this).text().trim());
             $(this).attr("href",url);
         });
     });--%>
</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>
            <li class="breadcrumb_link">
                <c:if test="${reportForm.cce eq 'ordDet'}"><bean:message key="label.report.OrderDet" /></c:if>
                <c:if test="${reportForm.cce eq 'invDet'}"><bean:message key="label.report.InvDet" /></c:if>
                <%--<bean:message key="label.report.OrderInvDet" /></li>--%>
        </ul>
    </div>


    <center>


        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText">
                    <c:if test="${reportForm.cce eq 'ordDet'}"><bean:message key="label.report.OrderDet" /></c:if>
                    <c:if test="${reportForm.cce eq 'invDet'}"><bean:message key="label.report.InvDet" /></c:if>
                </h1>
                <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr height=50 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="reportAction.do?option=viewOrderInvDetReport&flag=check&cce=${reportForm.cce}" method="post" styleId="searchBy" >

                                <table width=100%  border=0 cellspacing=0 cellpadding=4 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF" >
                                        <%--<td class="headingSpas" nowrap align="right" width="5%">DMS <bean:message key="label.spare.poNo" /></td>
                                        <td align="left" width="10%"><input name="newPoNo" type="text" id="Po No" value="${warrantyForm.sapCreditNo}" style="width:80px !important " onblur="this.value=TrimAll(this.value)"/></td>--%>
                                        <td class="headingSpas" nowrap align="right" width="10%"><bean:message key="label.spare.orderType" /> </td>
                                        <td align="left" width="10%">
                                            <select id="OrderType" name="orderType" class="headingSpas" style="width:80px !important;" onchange="makeEnable(this.value)">
                                                <option value="" >-Select-</option>
                                                <option value="ALL" >ALL</option>
                                                <%--<option value="SO" >SO</option>
                                                <option value="VOR" >VOR</option>--%>
                                                <c:forEach items="${orderTypeList}" var="orderTypeList">
                                                    <option value='${orderTypeList.value}' title='${orderTypeList.label}'>${orderTypeList.label}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td class="headingSpas" nowrap align="right" width="10%"><bean:message key="label.spare.orderStatus" /> </td>
                                        <td align="left" width="10%">
                                            <select id="OrderStatus" name="status" class="headingSpas" style="width:110px !important;" onchange="makeEnable(this.value)">
                                                <option value="" >-Select-</option>
                                                <option value="ALL" >ALL</option>
                                                <option value="OPEN" >OPEN</option>
                                                <option value="PENDING" >PENDING</option>
                                                <option value="CANCELLED" >CANCELLED</option>
                                                <option value="REGISTERED" >REGISTERED</option>
                                                <%-- <c:forEach items="${orderTypeList}" var="orderTypeList">
                                                    <option value='${orderTypeList.value}@@${orderTypeList.label}' title='${orderTypeList.label}'>${orderTypeList.label}</option>
                                                </c:forEach>--%>
                                            </select>
                                        </td>


                                        <td class="headingSpas" nowrap align="right" width="10%">
                                            <c:if test="${range eq '1'}">
                                                <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                            </c:if>
                                            <c:if test="${range ne '1' }">
                                                <input type="checkbox" name="range" value="1"  id="Range" >
                                            </c:if>
                                            <c:if test="${reportForm.cce eq 'ordDet'}"><bean:message key="label.spare.PODate" /></c:if>
                                            <c:if test="${reportForm.cce eq 'invDet'}"><bean:message key="label.common.InvoiceDate" /></c:if>

                                        </td>
                                        <td   align="left" width="5%">
                                            <input type="text" name="fromdate" class="datepicker" id="From Date" style="width:60px !important " class="txtGeneralNW" value="${reportForm.fromdate}"  readonly="readonly"/>
                                        </td>
                                        <td class="headingSpas" nowrap align="left" width="1%"  >
                                            To
                                        </td>
                                        <td   align="left" width="10%">
                                            <input type="text" name="todate" class="datepicker" id="To Date" style="width:60px !important " class="txtGeneralNW" value="${reportForm.todate}"  readonly="readonly"/>
                                        </td>
                                        <%if (userFunctionalities.contains("101")) {%>
                                        <%--<td  align="left" width="10%">
                                            <input name="go" type="button" value="search" style="float:none; " onClick = "submitForm()"/>
                                        </td>--%>
                                        <td  align="left" width="10%">
                                            <input type="button" value="Export" onclick="Export()"/> <input type="hidden" name="eType" id="eType" />
                                        </td>
                                        <%}%>
                                    </tr>
                                    <%if (userFunctionalities.contains("101")) {%>
                                    <%} else {%>
                                    <tr bgcolor="#FFFFFF">
                                        <td align="right" style="white-space: nowrap;"> <bean:message key="label.common.dealerName" />    </td>
                                        <td colspan="4" align="left">
                                            <select id="Dealer Name" name="dealerCode" style="width:300px !important">
                                                <%-- <option value='' >--Select Dealer--</option>--%>
                                                <option value="ALL">ALL</option>
                                                <c:forEach items="${dealerList}"  var="labelValue">
                                                    <c:if test="${warrantyForm.dealer_code eq labelValue[0]}">
                                                        <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]" selected>${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                    </c:if>
                                                    <c:if test="${warrantyForm.dealer_code ne labelValue[0]}">
                                                        <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]">${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                    </c:if>
                                                </c:forEach>
                                            </select>
                                        </td>

                                        <td  align="left" colspan="5">
                                            <input name="Srch" type="text" id="Srch" value="" style="display: none"/>
                                            <%--<input name="go" type="button" value="search" style="float:none; " onClick = "submitForm()"/>--%>
                                            <input type="button" value="Export" onclick="Export()"/> <input type="hidden" name="eType" id="eType" />
                                        </td>
                                        <%}%>
                                    </tr>

                                </table>
                            </html:form>

                        </td>
                    </tr>
                </table>
                <%--<c:if test="${!empty viewOrderList}">

                    <pg:pager url="reportAction.do" maxIndexPages="10" maxPageItems="10">
                        <pg:param name="option" value="viewOrderInvDetReport"/>
                        <pg:param name="orderType"  value='${reportForm.orderType}'/>
                        <pg:param name="status"  value='${reportForm.status}'/>
                        <pg:param name="dealerCode"  value='${reportForm.dealerCode}'/>
                        <pg:param name="fromdate"  value='${reportForm.fromdate}'/>
                        <pg:param name="todate"  value='${reportForm.todate}'/>
                        <pg:param name="flag"  value='check'/>
                        <pg:param name="cce"  value='${reportForm.cce}'/>
                        <c:if test="${range eq '1'}"><pg:param name="range"  value='1'/> </c:if>
                        <div style="width:99%; overflow:auto">
                            <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                <tr bgcolor="#eeeeee" height="20">
                                    <td   align="center" class="headingSpas" width="4%" nowrap style="padding-left:5px; "><b><bean:message key="label.warranty.S.No" /></b></td>
                                    <%if (userFunctionalities.contains("101")) {%>
                                    <%} else {%>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.dealerName" /></b></td>
                                    <%}%>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.stockistName" /></b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.spare.poNo" /></b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.spare.PODate" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.spare.orderType" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.adOrderNo" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.adOrderDate" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b>ERP <bean:message key="label.common.remarks" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.orderedPart" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b>ORDERED <bean:message key="label.common.partdesc_small" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.catalogue.ordered.quantity" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.backOrdQty" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.InvoiceNo" /></b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.InvoiceDate" /></b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.order.totalInvoiceAmount" /></b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.shipStatus" /></b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.ShippedPart" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b>SHIPPED <bean:message key="label.common.partdesc_small" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b>SHIPPED <bean:message key="label.common.qty_small" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.invRate" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.partInValue" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.report.ShipmentDate" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.order.lrNo" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.order.transporterName" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.order.permitNo" /> </b></td>

                                </tr>
                                <c:set var="sno" value="1"></c:set>
                                <logic:iterate id="dataList" name="viewOrderList">
                                    <pg:item>
                                        <tr  bgcolor="#eeeeee" height="20">
                                            <td align="center" bgcolor="#FFFFFF" width="4%" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                            <%if (userFunctionalities.contains("101")) {%>
                                            <%} else {%>
                                            <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.dealerCode} [${dataList.dealerName}] [${dataList.location}]"  class="headingSpas" style="padding-left:5px; padding-right: 5px; white-space: nowrap;">
                                                <span>
                                                    ${dataList.dealerCode} [${dataList.dealerName}] [${dataList.location}]
                                                </span>
                                            </td>
                                            <%}%>

                                            <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.stockistName}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    <a href="#" class="viewOrderDetails">  ${dataList.stockistName} </a>
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.newPoNo}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    <a href="#" class="viewOrderDetails">  ${dataList.newPoNo} </a>
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.poDate}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.poDate}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.orderType}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.orderType}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.erpOrderNo}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.erpOrderNo}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.erpOrderDate}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                ${dataList.erpOrderDate}
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.erpRemarks}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                ${dataList.erpRemarks}
                                            </td>

                                            <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.partno}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                ${dataList.partno}
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.part_desc}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                ${dataList.part_desc}
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.qty}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                ${dataList.qty}
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.backOrdQty}"  class="headingSpas" style="padding-left:5px; padding-right: 5px;white-space: nowrap">
                                                ${dataList.backOrdQty}
                                            </td>


                                            <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.invNo}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.invNo}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.invoiceDate}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.invoiceDate}
                                                </span>
                                            </td>

                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.totalAmont}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.totalAmont}
                                                </span>
                                            </td>

                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.status}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.status}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.shippedpartno}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.shippedpartno}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.shippedpartDesc}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.shippedpartDesc}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.shippedQty}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.shippedQty}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.invoiceRate}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.invoiceRate}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.partInvoiceValue}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.partInvoiceValue}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.shipmentDate}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.shipmentDate}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.lrNo}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.lrNo}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.transporterName}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.transporterName}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.permitNo}" width="10%" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.permitNo}
                                                </span>
                                            </td>


                                        </tr>
                                    </pg:item>
                                    <c:set var="sno" value="${sno + 1 }" />
                                </logic:iterate>
                                <tr>
                                    <%if (userFunctionalities.contains("101")) {%>
                                    <td colspan="25" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                        <%} else {%>
                                    <td colspan="18" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                        <%}%>
                                        <table  align="center" cellspacing="0" cellpadding="0">
                                            <tr>
                                                <td>
                                                    <pg:index>
                                                        <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle" style="float:left;"/></a></pg:first>&nbsp;
                                                        <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle" style="float:left;"/></a></pg:prev>&nbsp;
                                                        <pg:pages>&nbsp;<a style="color: blue!important;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                                        <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle" style="float:right"/></a></pg:last>
                                                        <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle" style="float:right;"/></a></pg:next>&nbsp;
                                                        </pg:index>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>

                            </table>
                        </div>                 </pg:pager>

                </c:if>--%>
                <%--<c:if test="${empty viewOrderList}">
                    <tr  class="headingSpas" bgcolor="#FFFFFF" height="20">
                        <td valign="bottom" align="center" style="padding-top:10px;color: red"><bean:message key="label.common.noRecordFound" /></td>
                    </tr>
                </c:if>--%>

            </div>
        </div>
    </center>
</div>
<script>
    window.onload =function(){
        var odType = '${reportForm.orderType}';
        var status = '${reportForm.status}';
        var poNo = '${reportForm.newPoNo}';
        var dCode = '${reportForm.dealerCode}';
        if(odType != ""){
            document.getElementById("OrderType").value=odType;
        }

        if(status != ""){
            document.getElementById("OrderStatus").value=status;
        }
        if(poNo != ""){
            document.getElementById("Po No").value=poNo;
        }
        var dealerFlag='<%=dealerFlag%>';
        if(dealerFlag=='true')
        {
            document.getElementById('Dealer Name').value=dCode;
        }
    }
</script>