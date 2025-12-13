<%-- 
    Document   : printTaxInvoice
    Created on : March 17, 2016, 2:48:19 PM
    Author     : avinash.pandey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>

<script>
    function printview(obj){
        obj.style.display='none';
        window.print();
        window.onfocus=function(){  obj.style.display='block';}
    }
    
</script>

<body >
    <center style="background-color:#FFFFFF">
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <table width="100%" border="0" align="center" cellpadding="4" cellspacing="0" class="LiteBorder" style="FONT-SIZE: 11px;">
                    <tr >
                        <td  class="headingSpas hText" align="center" style="padding:3px;">
                        </td>
                        <td align="right"><a href="#"><img src='<%=cntxpath%>/image/print-icon.png' alt='Get Suggestions' name="img1" border='0' title="print"  onclick="printview(this);"/></a></td>
                    </tr>
                    <tr>
                        <td colspan="2">
                            <table width="100%" border="0" align="center" cellpadding="3" cellspacing="0" bordercolor=#cccccc >
                                <tr height=20 bgcolor="#FFFFFF">
                                    <td align="right" ><label ><B><bean:message key="label.spare.name" />: </B></label></td>
                                    <td align="left" ><span>${spTaxDetails.dealerName} </span> <%-- [${spTaxDetails.dealercode}]--%>
                                    </td>
                                    <td align="right" ><label ><B><bean:message key="label.common.referenceNo" />: </B></label></td>
                                    <td align="left" ><span>${spTaxDetails.refNo}</span>
                                    </td>
                                    <td align="right" ><label ><B><bean:message key="label.common.InvoiceNo" />: </B></label></td>
                                    <td align="left" ><span>${spTaxDetails.invoiceno}</span>
                                    </td>
                                </tr>
                                <tr height=20 bgcolor="#FFFFFF">
                                    <td align="right" ><label ><B><bean:message key="label.common.contactname" />: </B></label></td>
                                    <td align="left" ><span>${spTaxDetails.payeeName}</span>
                                    </td>
                                    <td align="right" ><label ><B><bean:message key="label.common.contactno" />: </B></label></td>
                                    <td align="left" ><span>${spTaxDetails.payeeMobilePhone}</span>
                                    </td>
                                    <td align="right" ><span><label ><B><bean:message key="label.common.InvoiceDate" />: </B></label></span></td>
                                    <td align="left" ><span>${spTaxDetails.invoiceDate}</span>
                                    </td>
                                </tr>
                                <tr height=20 bgcolor="#FFFFFF">
                                    <td align="right" ><label ><B><bean:message key="label.common.CustomerName" />: </B></label></td>
                                    <td align="left" ><span>${spTaxDetails.customerName}</span>
                                    </td>
                                    <td align="right" ><label ><B><bean:message key="label.common.location" />: </B></label></td>
                                    <td align="left"  ><span>${spTaxDetails.customerLocation}</span>
                                    </td>
                                    <td align="right" ><label ><B><bean:message key="label.spare.tinNo" />: </B></label></td>
                                    <td align="left"  ><span>${spTaxDetails.tinNo}</span>
                                    </td>
                                </tr>
                                <tr height=10 bgcolor="#FFFFFF"></tr>
                                <c:if test="${!empty spTaxDetails.partNo}">
                                    <tr height=20 bgcolor="#FFFFFF">
                                        <td align="left" colspan="6">
                                            <table  border="0" cellspacing="3" cellpadding="3" width="100%" style="FONT-SIZE: 11px;">
                                                <c:set var="j" value="0"></c:set>
                                                <c:set var="k" value="0"></c:set>
                                                <c:set var="unitTotal" value="0"></c:set>
                                                <c:set var="grandTotal" value="0"></c:set>
                                                <c:set var="totalChargeAmt" value="0"></c:set>
                                                <c:set var="totalTaxAmt" value="0"></c:set>
                                                <c:set var="totalFinalAmount" value="0"></c:set>
                                                <c:set var="commodityCode" value=""></c:set>
                                                <c:set var="commodityCode1" value="${spTaxDetails.commodityCode[0]}"></c:set>
                                                <c:forEach items="${spTaxDetails.partNo}" var="dataList">
                                                    <c:if test="${spTaxDetails.commodityCode[j] ne commodityCode}">
                                                        <c:if test="${spTaxDetails.commodityCode[j] ne commodityCode1}">
                                                            <tr >
                                                                <td align="left" class="tdgridnew1">&nbsp;</td>
                                                                <td align="left" class="tdgridnew1">&nbsp;</td>
                                                                <td align="left" class="tdgridnew1">&nbsp;</td>
                                                                <td align="left" class="tdgridnew1">&nbsp;</td>
                                                                <td align="left" class="tdgridnew1">&nbsp;</td>

                                                                <td colspan="3" align="right" class="tdgridnew1" ><label><B>TOTAL ${commodityCode1} VALUE</B></label></td>
                                                                <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span><fmt:formatNumber  type="number" minFractionDigits="2" maxFractionDigits="2"  value="${unitTotal}"/></span>
                                                                </td>

                                                                <c:set var="totalFinalAmount" value="${unitTotal}"></c:set>
                                                            </tr>
                                                            <c:forEach items="${spTaxDetails.chMap[commodityCode1]}" var="chDataList">
                                                                <tr >
                                                                    <td align="left" class="tdgridnew1">&nbsp;</td>
                                                                    <td align="left" class="tdgridnew1">&nbsp;</td>
                                                                    <td align="left" class="tdgridnew1">&nbsp;</td>
                                                                    <td align="left" class="tdgridnew1">&nbsp;</td>
                                                                    <td align="left" class="tdgridnew1">&nbsp;</td>
                                                                    <td colspan="3" align="right" class="tdgridnew1" >
                                                                        <label><B>Inclusive of  ${chDataList.label}</B></label>
                                                                    </td>
                                                                    <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${chDataList.value}</span>
                                                                        <c:set var="totalTaxAmt" value="${chDataList.value}" />
                                                                     <%--   <c:set var="totalFinalAmount" value="${totalFinalAmount + totalTaxAmt}"></c:set> --%>
                                                                    </td>
                                                                </tr>
                                                            </c:forEach>
                                                            <c:set var="grandTotal" value="${grandTotal+totalFinalAmount}"></c:set>
                                                           <%-- <tr >
                                                                <td align="left" class="tdgridnew1">&nbsp;</td>
                                                                <td align="left" class="tdgridnew1">&nbsp;</td>
                                                                <td align="left" class="tdgridnew1">&nbsp;</td>
                                                                <td align="left" class="tdgridnew1">&nbsp;</td>
                                                                <td align="left" class="tdgridnew1">&nbsp;</td>
                                                                <td colspan="3" align="right" class="tdgridnew1" >
                                                                    <label><B>TOTAL ${commodityCode1} AMOUNT</B></label>
                                                                </td>
                                                                <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"  value="${totalFinalAmount}"/></span>

                                                                </td>
                                                            </tr>--%>
                                                            <c:set var="commodityCode1" value="${spTaxDetails.commodityCode[j]}"></c:set>
                                                        </c:if>
                                                        <tr bgcolor="#eeeeee" height="20" class="hText">
                                                            <td align="left" colspan="9" ><B>${spTaxDetails.commodityCode[j]} DETAILS</B></td>
                                                        </tr>
                                                        <tr class="grid">
                                                            <td  align="center" class="tdgridnew1" width="7%"><label><b><bean:message key="label.common.sno" /></b></label></td>
                                                            <td  align="left" class="tdgridnew1" width="15%"><label><b><bean:message key="label.common.itmno_small" /></b></label></td>
                                                            <td  align="left" class="tdgridnew1" width="25%"><label><b><bean:message key="label.common.itemdesc_small" /></b></label></td>
                                                            <td  align="left" class="tdgridnew1" width="8%"><label><b><bean:message key="label.common.category" /></b></label></td>
                                                            <td  align="center" class="tdgridnew1" width="5%"><label><b><bean:message key="label.common.qty_small" /></b></label></td>
                                                            <td  align="center" class="tdgridnew1" width="12%" ><label><b><bean:message key="label.catalogue.mrp" /></b></label></td>
                                                            <td  align="center" class="tdgridnew1" width="9%" ><label><b><bean:message key="label.common.discount_small" /></b></label></td>
                                                            <td  align="left" class="tdgridnew1" width="9%"><label><b><bean:message key="label.common.billtype_small" /></b></label></td>
                                                            <td  align="right" class="tdgridnew1" width="10%" style="padding-right: 35px;"><label><b><bean:message key="label.common.amt_small" /></b></label></td>
                                                        </tr>
                                                        <c:set var="k" value="0"></c:set>
                                                        <c:set var="commodityCode" value="${spTaxDetails.commodityCode[j]}"></c:set>
                                                        <c:set var="unitTotal" value="0"></c:set>
                                                    </c:if>
                                                    <tr >
                                                        <td align="center" class="tdgridnew1" style="FONT-SIZE: 11px;">${k+1}</td>
                                                        <td align="left"><span>${spTaxDetails.partNo[j]}</span>
                                                            <input type="hidden"  name="partNo" value="${spTaxDetails.partNo[j]}" />
                                                        </td>
                                                        <td align="left"><span>${spTaxDetails.partDesc[j]}</span>
                                                            <input type="hidden"  name="partDesc" value="${spTaxDetails.partDesc[j]}" />
                                                        </td>
                                                        <td align="left"><span>${spTaxDetails.comptype[j]}</span>
                                                            <input type="hidden"  name="comptype" value="${spTaxDetails.comptype[j]}" />
                                                        </td>
                                                        <td align="center"><span>${spTaxDetails.quantityS[j]}</span>
                                                            <input type="hidden"  name="quantityS" value="${spTaxDetails.quantityS[j]}" />
                                                        </td>
                                                        <td align="right" style="padding-right: 30px;"><span>${spTaxDetails.unitPrice[j]}</span>
                                                            <input type="hidden"  name="unitPrice" value="${spTaxDetails.unitPrice[j]}" />
                                                        </td>
                                                        <td align="right" style="padding-right: 25px;" ><span><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"  value="${spTaxDetails.discount_amt[j]}"/></span>
                                                            <input type="hidden"  name="discount_amt" value="${spTaxDetails.discount_amt[j]}" />
                                                        </td>
                                                        <td align="left" ><span>${spTaxDetails.billTo[j]}</span>
                                                            <input type="hidden"  name="billCode" value="${spTaxDetails.billCode[j]}" />
                                                        </td>
                                                        <td align="right" style="padding-right: 35px;"><span>${spTaxDetails.partPriceAmount[j]}</span>
                                                            <input type="hidden"  name="partPriceAmount" value="${spTaxDetails.partPriceAmount[j]}" />
                                                        </td>
                                                    </tr>
                                                    <c:set var="unitTotal" value="${unitTotal + spTaxDetails.partPriceAmount[j]}"></c:set>
                                                    <c:set var="k" value="${k + 1 }" /><c:set var="j" value="${j + 1 }" />
                                                </c:forEach>
                                                <c:if test="${fn:length(spTaxDetails.commodityCode) eq j}">
                                                    <tr >
                                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                                        <td align="left" class="tdgridnew1">&nbsp;</td>

                                                        <td colspan="3" align="right" class="tdgridnew1" ><label><B>TOTAL ${commodityCode1} VALUE</B></label></td>
                                                        <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span><fmt:formatNumber  type="number" minFractionDigits="2" maxFractionDigits="2"  value="${unitTotal}"/></span>
                                                        </td>

                                                        <c:set var="totalFinalAmount" value="${unitTotal}"></c:set>
                                                    </tr>
                                                    <c:forEach items="${spTaxDetails.chMap[commodityCode1]}" var="chDataList">
                                                        <tr >
                                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                                            <td align="left" class="tdgridnew1">&nbsp;</td>
                                                            <td colspan="3" align="right" class="tdgridnew1" >
                                                                <label><B>Inclusive of ${chDataList.label}</B></label>
                                                            </td>
                                                            <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${chDataList.value}</span>
                                                                <c:set var="totalTaxAmt" value="${chDataList.value}" />
                                                             <%--   <c:set var="totalFinalAmount" value="${totalFinalAmount + totalTaxAmt}"></c:set> --%>
                                                            </td>
                                                        </tr>
                                                    </c:forEach>
                                                    <c:set var="grandTotal" value="${grandTotal+totalFinalAmount}"></c:set>
                                                  <%--  <tr >
                                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                                        <td colspan="3" align="right" class="tdgridnew1" >
                                                            <label><B>TOTAL ${commodityCode1} AMOUNT</B></label>
                                                        </td>
                                                        <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"  value="${totalFinalAmount}"/></span>

                                                        </td>
                                                    </tr>--%>
                                                </c:if>
                                                <tr bgcolor="#eeeeee" height="5" class="hText">
                                                     <td align="left" colspan="9" >&nbsp;</td>
                                                 </tr>
                                                <tr >
                                                    <fmt:parseNumber type="number" integerOnly="true" var="gTotal" value="${grandTotal}"/>
                                                    <c:set var="finalTemp" value="${grandTotal-gTotal}"></c:set>
                                                    <td align="left" class="tdgridnew1">&nbsp;</td>
                                                    <td align="left" class="tdgridnew1">&nbsp;</td>
                                                    <td align="left" class="tdgridnew1">&nbsp;</td>
                                                    <td align="left" class="tdgridnew1">&nbsp;</td>
                                                    <td align="left" class="tdgridnew1">&nbsp;</td>
                                                    <td colspan="3" align="right" class="tdgridnew1" >
                                                        <label class="hText"><B><bean:message key="label.common.otherdiscount" /></B></label>
                                                    </td>
                                                    <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"  value="${spTaxDetails.totaldiscount}"/></span>
                                                    </td>
                                                    <c:set var="grandTotal" value="${grandTotal-spTaxDetails.totaldiscount}"></c:set>
                                                </tr>
                                                <tr >
                                                    <td align="left" class="tdgridnew1">&nbsp;</td>
                                                    <td align="left" class="tdgridnew1">&nbsp;</td>
                                                    <td align="left" class="tdgridnew1">&nbsp;</td>
                                                    <td align="left" class="tdgridnew1">&nbsp;</td>
                                                    <td align="left" class="tdgridnew1">&nbsp;</td>
                                                    <td colspan="3" align="right" class="tdgridnew1" >
                                                        <label class="hText"><B><bean:message key="label.common.GrandTotal" /></B></label>
                                                    </td>
                                                    <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>
                                                           <%-- <fmt:parseNumber type="number" integerOnly="true" value="${grandTotal}"/></span>--%>
                                                            <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"   value="${grandTotal}"/></span>

                                                    </td>
                                                </tr>
                                            </table>
                                        </td>
                                    </tr>
                                </c:if>
                            </table>
                        </td>
                    </tr>
                </table>
            </div>
        </div>
        <div align="left" class="footer_print" style="background-color: #ffffff">
            <br><br><br>
            <p class="textftl"><b>Customer Name & Signature</b></p>
            <p class="textftr"><b>Name & Signature with Stamp</b></p>
            <p class="textftd"><b>Date</b></p><br>
        </div>
    </center>
</body>
