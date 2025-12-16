
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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

                                <td align="right" ><label ><B><bean:message key="label.spares.grNo" />: </B></label></td>
                                <td align="left" ><span>${serviceForm.spOrderInvGrn.grNo}</span></td>

                               <td align="right" ><span><label ><B><bean:message key="label.spares.invoiceno" />: </B></label></span></td>
                               <td align="left" ><span>${serviceForm.spOrderInvGrn.invoiceNo}</span></td>

                               <td align="right" ><span><label ><B><bean:message key="label.warranty.receivedBy" />: </B></label></span></td>
                               <td align="left" ><span>${serviceForm.spOrderInvGrn.receivedBy}</span></td>
                              </tr>

                              <tr height=20 bgcolor="#FFFFFF">

                               <td align="right" ><label ><B><bean:message key="label.spares.grdate" />: </B></label></td>
                               <td align="left" ><span><fmt:formatDate pattern="dd/MM/yyyy" value="${serviceForm.spOrderInvGrn.grDate}"/></span>
                               </td>
                               <td align="right" ><label ><B><bean:message key="label.common.invoice" />&nbsp;<bean:message key="label.common.date"/>: </B></label></td>
                                <td align="left" ><span><fmt:formatDate pattern="dd/MM/yyyy" value="${serviceForm.spOrderInvGrn.invoiceDate}" /></span></td>
                                <td align="right" ><label ><B><bean:message key="label.spares.receivedon" />: </B></label></td>
                                <td align="left" ><span><fmt:formatDate pattern="dd/MM/yyyy" value="${serviceForm.spOrderInvGrn.receivedOn}" /></span></td>
                              </tr>
                              <tr height=20 bgcolor="#FFFFFF"></tr>

                              <c:if test="${!empty spOrderInvGrnDetail}">
                              <tr bgcolor="#eeeeee" height="20" class="hText">
                                  <td align="left" colspan="6" ><B><bean:message key="label.common.label4viewpart" /></B></td>
                              </tr>
                              <tr height=20 bgcolor="#FFFFFF">
                                  <td align="left" colspan="6">
                                      <table  border="0" cellspacing="3" cellpadding="3" width="100%" style="FONT-SIZE: 11px;">
                                          <tr class="grid">
                                              <td align="center" width="6%"><B><bean:message key="label.common.sno" /></B></td>
                                              <td align="left" width="10%"><B><bean:message key="label.common.partno_small" /></B></td>
                                              <td align="left" width="20%"><B><bean:message key="label.common.partdesc_small" /></B></td>
                                              <td align="center" width="10%"><B><bean:message key="label.common.invoice" />&nbsp;<bean:message key="label.common.qty_small" /></B></td>
                                              <td align="center" width="10%"><B>  <bean:message key="label.spares.received"/>&nbsp;<bean:message key="label.common.qty_small" /></B></td>
                                              <td align="right" width="10%"><B><bean:message key="label.common.unitprice_small" /></B></td>
                                              <td align="right" width="10%"><B><bean:message key="label.common.invoice.amount" /> </B></td>
                                          </tr>
                                          <c:set var="j" value="1"></c:set>
                                          <c:set var="totalAmount" value="0"></c:set>
                                          <c:forEach items="${spOrderInvGrnDetail}" var="datalist">
                                              <tr >
                                                  <td align="center" class="tdgridnew1" style="FONT-SIZE: 11px;">${j}</td>
                                                  <td align="left"><span>${datalist.partNo_str}</span></td>
                                                  <td align="left"><span>${datalist.partDesc_str}</span></td>
                                                  <fmt:parseNumber var="invQty" type="number" value="${datalist.invoiceQty}" />
                                                  <td align="center"><span>${invQty}</span></td>
                                                   <fmt:parseNumber var="rcvdQty" type="number" value="${datalist.recdQty}" />
                                                  <td align="center"  ><span>${rcvdQty}</span></td>
                                                  <td align="right"><span>${datalist.unitprice_str}</span></td>
                                                  <fmt:parseNumber var="amount" type="number" value="${datalist.amount_str}" />
                                                  <td align="right"><span><c:out value="${amount}"/></span></td>
                                              </tr>
                                               <c:set var="totalAmount" value="${datalist.amount_str + totalAmount}"></c:set>
                                              <c:set var="j" value="${j + 1 }" />
                                          </c:forEach>

                                            <tr class="grid">
                                                <td colspan="6" align="right" width="6%" ><B><bean:message key="label.warranty.total" />&nbsp;<bean:message key="label.common.invoice.amount" /> </B></td>
                                                <fmt:parseNumber var="totalAmountNumber" type="number" value="${totalAmount}" />
                                                <td align="right" width="6%"><c:out value="${totalAmountNumber}"/></td>


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
</center>