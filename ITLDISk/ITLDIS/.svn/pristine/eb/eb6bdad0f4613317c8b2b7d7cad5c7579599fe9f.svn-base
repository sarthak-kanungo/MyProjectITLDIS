<%--
    Document   : viewOrderList
    Created on : 11-Feb-2015, 11:26:21
    Author     : kundan.rastogi
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
<script>
    $(document).ready(function(){
        $('form').submit(function(){
            if($('#dealCode option:selected').val() == 0){
                alert("Please select dealer.");
                return false;
            }
        });
    });



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

</script>
<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>
            <li class="breadcrumb_link"><bean:message key="label.common.back.order.report" /></li>
        </ul>
    </div>


    <center>


        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.common.back.order.report" /></h1>
                <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr height=50 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="inventoryAction.do?option=backOrderReport&flag=check" method="post" styleId="searchBy">
                                <table width=100%  border=0 cellspacing=0 cellpadding=4 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF" >

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
                                            </select>
                                        </td>


                                        <td class="headingSpas" nowrap align="right" width="10%">
                                            <c:if test="${range eq '1'}">
                                                <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                            </c:if>
                                            <c:if test="${range ne '1' }">
                                                <input type="checkbox" name="range" value="1"  id="Range" >
                                            </c:if>
                                            <bean:message key="label.spare.PODate" />

                                        </td>
                                        <td   align="left" width="5%">
                                            <input type="text" name="fromdate" class="datepicker" id="From Date" style="width:60px !important " class="txtGeneralNW" value="${inventoryForm.fromdate}"  readonly="readonly"/>
                                        </td>
                                        <td class="headingSpas" nowrap align="left" width="1%"  >
                                            To
                                        </td>
                                        <td   align="left" width="10%">
                                            <input type="text" name="todate" class="datepicker" id="To Date" style="width:60px !important " class="txtGeneralNW" value="${inventoryForm.todate}"  readonly="readonly"/>
                                        </td>
                                        <%if (userFunctionalities.contains("101")) {%>
                                        <td  align="left" width="10%">
                                            <input name="go" type="submit" value="Submit" style="float:none;"/>
                                            <%--<input name="go" type="button" value="search" style="float:none; " onClick = "submitForm()"/>--%>
                                        </td>
                                        <%}%>
                                    </tr>

                                    <%if (userFunctionalities.contains("101")) {%>
                                    <%} else {%>
                                    <tr bgcolor="#FFFFFF">
                                        <td align="right" style="white-space: nowrap;"> <bean:message key="label.common.dealerName" />    </td>
                                        <td colspan="4" align="left">
                                            <select id="dealCode" name="dealerCode" style="width:300px !important">
                                                <%-- <option value='' >--Select Dealer--</option>--%>
                                                <option value="ALL">--ALL--</option>
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
                                            <input name="go" type="submit" value="Submit" style="float:none;"/>
                                            <%-- <input type="button" value="Export" onclick="Export()"/> <input type="hidden" name="eType" id="eType" />--%>
                                        </td>
                                        <%}%>
                                    </tr>

                                </table>
                                <input type="hidden" name="hoPage" value="hoPage"/>
                            </html:form>

                        </td>
                    </tr>
                </table>
                </table>
            </div>
        </div>
    </center>
</div>