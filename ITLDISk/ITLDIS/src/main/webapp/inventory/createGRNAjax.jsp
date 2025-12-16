<%--
    Document   : createGRNAjax
    Created on : 25-Feb-2015, 10:19:33
    Author     : kundan.rastogi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            String userType = "" + request.getAttribute("subDealer");
            String flag = "" + request.getAttribute("flag");
            //System.out.println(" flag is   "+flag);

              Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DATE);
%>



<div class="content_right1">
    <div  style="overflow:auto; position: relative;width: 100%; height:450px">
        <table border=0 cellspacing=1 cellpadding=1  width=100%  bordercolor=#cccccc bgcolor=#cccccc id="myTb" name="myTb">
            <tr  bgcolor="#eeeeee" height="20" >
                <td align="center" width="2%" class="headingSpas" style="border-top:solid 1px #ccc" ><b><bean:message key='label.common.sno' /></b></td>
                <td align="left" width="10%" class="headingSpas" style="border-top:solid 1px #ccc" ><b><bean:message key='label.order.orderNo' /></b></td>
                <td width="12%" align="left" class="headingSpas" style="border-top:solid 1px #ccc" ><b><bean:message key='label.common.partno_small' /> </b></td>
                <td width="12%" align="left" class="headingSpas"style="border-top:solid 1px #ccc"  ><b><bean:message key='label.common.partdesc_small' /></b></td>
                <td width="10%" align="left" class="headingSpas" style="border-top:solid 1px #ccc" ><b><bean:message key='label.Grn.invQty' /> </b></td>
                <td width="10%" align="left" class="headingSpas" style="border-top:solid 1px #ccc" ><b><bean:message key='label.saleReturn.ReceivedQty' /> </b></td>
           </tr>
            <c:set var="sno" value="1"/>
            <c:forEach items="${invoiceDataList}" var="invoiceDataList">
                <tr bgcolor="#FFFFFF">
                    <td align="center" bgcolor="#FFFFFF" width="5%" class="headingSpas" style="padding-left:5px; padding-right: 5px" >
                        ${sno}<%--  <input type="hidden" name="partInventry" value="GRN">--%>
                    </td>
                    <td align="left" bgcolor="#FFFFFF" width="7%" class="headingSpas" style="padding-left:5px; padding-right: 5px" >
                        ${invoiceDataList.invOrderNo} <input type="hidden" name="invOrderNo" value="">
                    </td>
                    <td width="12.1%" align="left" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                        ${invoiceDataList.partno} <input type="hidden" name="partno" value="">
                    </td>
                    <td width="12%" align="left" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                        ${invoiceDataList.part_desc}
                    </td>
                    <td width="10%" align="left" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                        ${invoiceDataList.qty} <input type="hidden" name="qty" value="">
                    </td>
                    <td width="10%" align="left" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                        <input type="text" name="qty" id="ReceivedQty${sno}" value="${invoiceDataList.qty}">
                    </td>
                </tr>
                <c:set var="sno" value="${sno + 1 }" />
            </c:forEach>
            <tr bgcolor="#eeeeee" height="20">
                <td colspan="6"  align="center" bgcolor="#FFFFFF" width="100%" style="padding-top:25px;">
                    <input type="button" value="Submit" class="headingSpas1" style="float:none;" onclick="validate('part')"/>
                    <input type="hidden" name="invNo" id="InvNo" value="${inventoryForm.invNo}@@${inventoryForm.invDate}">
                </td>
            </tr>
        </table>
    </div>
    <c:if test="${empty invoiceDataList}">
        <tr bgcolor="#eeeeee" height="20">
            <td colspan="8"  align="center" bgcolor="#FFFFFF" width="100%">
                <font color="red">No Record Found! </font>
            </td>
        </tr>
    </c:if>
</div>




                <%-- <div id="heading" style="display: none">
                             <table border=0 cellspacing=1 cellpadding=1  width=100%  bordercolor=#cccccc >
                                 <tr>
                                     <td width="20%" align="right" ><label><bean:message key="label.common.InvoiceNo" /> :</label></td>
                                     <td width="20%" align="left"><span id="InvSpan"> </span></td>
                                     <td width="20%" align="right"><label><bean:message key="label.common.InvoiceDate" /> :</label></td>
                                     <td  width="20%" align="left"><span id="InvDateSpan">20/02/2015 </span></td>
                                 </tr>
                                 <tr>
                                     <td width="20%" align="right" ><label><bean:message key="label.warranty.receivedBy" /> :</label></td>
                                     <td width="20%" align="left"><input type="text" name="receivedBy" id="ReceivedBy"></td>
                                     <td width="20%" align="right"><label><bean:message key="label.common.tractorRecdate" /> :</label></td>
                                     <td  width="20%" align="left">
                                         <input name="invDate" type="text" id="Invoice Date" name="receivedOn" value="" class="datepicker" maxlength="10"/>
                                      </td>
                                 </tr>

                            </table>
                        </div>

                        <div cellspacing=1 cellpadding=5 border=0 width=100% id="InvoiceData">
                        </div>--%>
