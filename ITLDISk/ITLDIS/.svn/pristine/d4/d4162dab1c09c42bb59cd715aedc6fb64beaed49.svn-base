<%-- 
    Document   : printInvoice
    Created on : Nov 3, 2014, 2:48:19 PM
    Author     : megha.pandya
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
                      <%--  <label>
                            <B><bean:message key="label.common.invoice"/></B>
                        </label>--%>
                    </td>
                    <td align="right"><a href="#"><img src='<%=cntxpath%>/image/print-icon.png' alt='Get Suggestions' name="img1" border='0' title="print"  onclick="printview(this);"/></a></td>
                </tr>
                <tr>
                    <td colspan="2">
                            <table width="100%" border="0" align="center" cellpadding="3" cellspacing="0" bordercolor=#cccccc >
                                <tr height=20 bgcolor="#FFFFFF">
                                <td align="right" ><label ><B><bean:message key="label.common.dealerName" />: </B></label></td>
                                <td align="left" ><span>${jobcardDetails.dealerName} [${jobcardDetails.dealercode}]</span>
                                </td>
                                <td align="right" ><label ><B><bean:message key="label.common.referenceNo" />: </B></label></td>
                                <td align="left" ><span>${jobcardDetails.refNo}</span>
                                </td>
                                <td align="right" ><label ><B><bean:message key="label.common.InvoiceNo" />: </B></label></td>
                                <td align="left" ><span>${jobcardDetails.invoiceno}</span>
                                </td>
                              </tr>
                              <tr height=20 bgcolor="#FFFFFF">
                               <td align="right" ><label ><B><bean:message key="label.common.contactname" />: </B></label></td>
                               <td align="left" ><span>${jobcardDetails.payeeName}</span>
                               </td>
                               <td align="right" ><label ><B><bean:message key="label.common.contactno" />: </B></label></td>
                               <td align="left" ><span>${jobcardDetails.payeeMobilePhone}</span>
                               </td>
                               <td align="right" ><span><label ><B><bean:message key="label.common.InvoiceDate" />: </B></label></span></td>
                               <td align="left" ><span>${jobcardDetails.invoiceDate}</span>
                               </td>
                              </tr>
                              <tr height=20 bgcolor="#FFFFFF"></tr>
                              <%--<tr bgcolor="#eeeeee" height="20" >
                                  <td align="left" colspan="6"><label><B><bean:message key="label.warranty.jobCardDetails" /></B></label></td>
                              </tr>--%>
                              <c:if test="${!empty jobcardDetails.partNo}">
                              <tr bgcolor="#eeeeee" height="20" class="hText">
                                  <td align="left" colspan="6" ><B><bean:message key="label.common.label4viewpart" /></B></td>
                              </tr>
                              <tr height=20 bgcolor="#FFFFFF">
                                  <td align="left" colspan="6">
                                      <table  border="0" cellspacing="3" cellpadding="3" width="100%" style="FONT-SIZE: 11px;">
                                          <tr class="grid">
                                              <th  align="center" class="tdgridnew1" width="7%"><label><bean:message key="label.common.sno" /></label></th>
                                              <th  align="left" class="tdgridnew1" width="15%"><label><bean:message key="label.common.partno_small" /></label></th>
                                              <th  align="left" class="tdgridnew1" width="25%"><label><bean:message key="label.common.partdesc_small" /></label></th>
                                              <th  align="left" class="tdgridnew1" width="8%"><label><bean:message key="label.common.category" /></label></th>
                                              <th  align="center" class="tdgridnew1" width="5%"><label><bean:message key="label.common.qty_small" /></label></th>
                                              <th  align="center" class="tdgridnew1" width="12%" ><label><bean:message key="label.common.unitprice_small" /></label></th>
                                              <th  align="center" class="tdgridnew1" width="9%" ><label><bean:message key="label.common.discount_small" /></label></th>
                                              <th  align="left" class="tdgridnew1" width="9%"><label><bean:message key="label.common.billto_small" /></label></th>
                                              <th  align="right" class="tdgridnew1" width="10%" style="padding-right: 35px;"><label><bean:message key="label.common.amt_small" /></label></th>
                                          </tr>
                                          <c:set var="j" value="0"></c:set>
                                          <c:set var="totalSparesValue" value="0"></c:set>
                                          <c:set var="totalLubesValue" value="0"></c:set>
                                          <c:forEach items="${jobcardDetails.partNo}" var="dataList">
                                              <tr >
                                                  <td align="center" class="tdgridnew1" style="FONT-SIZE: 11px;">${(j+1)}</td>
                                                  <td align="left"><span>${jobcardDetails.partNo[j]}</span>
                                                      <input type="hidden"  name="partNo" value="${jobcardDetails.partNo[j]}" />
                                                  </td>
                                                  <td align="left"><span>${jobcardDetails.partDesc[j]}</span>
                                                      <input type="hidden"  name="partDesc" value="${jobcardDetails.partDesc[j]}" />
                                                  </td>
                                                  <td align="left"><span>${jobcardDetails.comptype[j]}</span>
                                                      <input type="hidden"  name="comptype" value="${jobcardDetails.comptype[j]}" />
                                                  </td>
                                                  <td align="center"><span>${jobcardDetails.quantityS[j]}</span>
                                                      <input type="hidden"  name="quantityS" value="${jobcardDetails.quantityS[j]}" />
                                                  </td>
                                                  <td align="right" style="padding-right: 30px;"><span>${jobcardDetails.unitPrice[j]}</span>
                                                      <input type="hidden"  name="unitPrice" value="${jobcardDetails.unitPrice[j]}" />
                                                  </td>
                                                  <td align="right" style="padding-right: 25px;" ><span><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"  value="${jobcardDetails.discount_amt[j]}"/></span>
                                                      <input type="hidden"  name="discount_amt" value="${jobcardDetails.discount_amt[j]}" />
                                                  </td>
                                                  <td align="left" ><span>${jobcardDetails.billTo[j]}</span>
                                                      <input type="hidden"  name="billCode" value="${jobcardDetails.billCode[j]}" />
                                                  </td>
                                                  <td align="right" style="padding-right: 35px;"><span>${jobcardDetails.partPriceAmount[j]}</span>
                                                      <input type="hidden"  name="partPriceAmount" value="${jobcardDetails.partPriceAmount[j]}" />
                                                  </td>
                                                  <c:choose>
                                                      <c:when test="${jobcardDetails.comptype[j] eq 'SPARES'}">
                                                          <c:set var="totalSparesValue" value="${totalSparesValue + jobcardDetails.partPriceAmount[j]}"></c:set>
                                                      </c:when>
                                                      <c:when test="${jobcardDetails.comptype[j] eq 'LUBES'}">
                                                          <c:set var="totalLubesValue" value="${totalLubesValue + jobcardDetails.partPriceAmount[j]}"></c:set>
                                                      </c:when>
                                                  </c:choose>
                                              </tr>
                                              <c:set var="commodityCode" value="${spTaxDetails.comptype[j]}"></c:set>
                                              <c:set var="j" value="${j + 1 }" />
                                          </c:forEach>
                                          <tr >
                                              <c:set var="totalPartsValue" value="0"></c:set>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td colspan="3" align="right" class="tdgridnew1" ><label><B><bean:message key="label.common.totalSparesValue" /></B></label></td>
                                              <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"   value="${totalSparesValue}"/></span>
                                                        <input type="hidden"  name="totalPartsValue" value="${totalSparesValue}" />
                                              </td>
                                          </tr>
                                          <tr >
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td colspan="3" align="right" class="tdgridnew1" ><label><B><bean:message key="label.common.ttllubesvalue_small" /></B></label></td>
                                              <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"   value="${totalLubesValue}"/></span>
                                                  <input type="hidden"  name="totalLubesValue" value="${totalLubesValue}" />
                                              </td>
                                          </tr>
                                          <c:if test="${jobcardDetails.jctype eq 'CounterSale'}">
                                           <tr >
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td colspan="3" align="right" class="tdgridnew1" ><label><B><bean:message key="label.common.otherdiscount" /></B></label></td>
                                              <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"   value="${jobcardDetails.totaldiscount}"/></span>
                                                  <input type="hidden"  name="totalLubesValue" value="${jobcardDetails.totaldiscount}" />
                                              </td>
                                          </tr>
                                          <tr >
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td colspan="3" align="right" class="tdgridnew1" ><label><B><bean:message key="label.common.GrandTotal" /></B></label></td>
                                              <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"   value="${jobcardDetails.totalEstimate}"/></span>
                                                  <input type="hidden"  name="totalLubesValue" value="${jobcardDetails.totalEstimate}" />
                                              </td>
                                          </tr>
                                          <c:if test="${jobcardDetails.creditValue ne '0.0'}">
                                          <tr >
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td align="left" class="tdgridnew1">&nbsp;</td>
                                              <td colspan="3" align="right" class="tdgridnew1"><label><B><bean:message key="label.counterSale.creditAmount" /></B></label></td>
                                              <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${jobcardDetails.creditValue}</span></td>
                                          </tr></c:if>
                                          </c:if>
                                      </table></td>
                              </tr>
                              </c:if>
                              <c:if test="${empty jobcardDetails.partNo  && (!empty jobcardDetails.workDescription || !empty jobcardDetails.cmpid)}">
                                  <tr bgcolor="#eeeeee" height="20" >
                                      <td align="left" colspan="6" ><label><B><bean:message key="label.common.othercharges" /></B></label></td>
                                  </tr></c:if>
                                  <tr height=20 bgcolor="#FFFFFF">
                                      <td align="left" colspan="6">
                                          <table  border="0" cellspacing="3" cellpadding="3" width="100%" style="FONT-SIZE: 11px;">
                                          <c:if test="${empty jobcardDetails.partNo  && (!empty jobcardDetails.workDescription || !empty jobcardDetails.cmpid)}">
                                              <tr class="grid">
                                                  <th width="7%"  align="center" class="tdgridnew1" ><label><bean:message key="label.common.sno" /></label></th>
                                                  <th width="30%"  align="left" class="tdgridnew1" ><label><bean:message key="label.common.workType" /></label></th>
                                                  <th width="10%" align="left" class="tdgridnew1" ><label><bean:message key="label.common.category" /></label></th>
                                                  <th width="40%"  align="left" class="tdgridnew1" ><label><bean:message key="label.common.workDesc" /></label></th>
                                                  <th width="13%"  align="right" class="tdgridnew1" style="padding-right: 35px;"><label><bean:message key="label.common.amt_small" /></label></th>
                                              </tr>

                                              <c:set var="k" value="0"></c:set>
                                              <c:forEach items="${jobcardDetails.workDescription}" var="dataList">
                                                  <tr >
                                                      <td align="center" class="tdgridnew1" ><span>${(k+1)}</span></td>
                                                      <td align="left" ><span>${jobcardDetails.workCode[k]}</span></td>
                                                      <td align="left" ><span><bean:message key="label.common.Sublets" /></span></td>
                                                      <td align="left" ><span>${jobcardDetails.workDescription[k]}</span></td>
                                                      <td align="right" style="padding-right: 35px;"><span>${jobcardDetails.workAmount[k]}</span></td>
                                                  </tr>
                                                  <c:set var="k" value="${k + 1 }" />
                                              </c:forEach>
                                              <c:set var="i" value="0"></c:set>
                                              <c:forEach items="${jobcardDetails.cmpid}" var="dataList">
                                                  <c:set var="i1" value="0"></c:set>
                                                  <c:forEach items="${jobcardDetails.action_taken[i]}" var="actionList">
                                                      <tr >
                                                          <td align="center" class="tdgridnew1" ><span>${(k+1)}</span></td>
                                                          <td align="left" ><span>${jobcardDetails.compCode[i]}</span></td>
                                                          <td align="left" ><span><bean:message key="label.common.Labour" /></span></td>
                                                          <td align="left" ><span>${jobcardDetails.action_taken[i][i1]}</span></td>
                                                          <td align="right" style="padding-right: 35px;"><span>${jobcardDetails.labourCharges[i][i1]}</span></td>
                                                      </tr>
                                                      <c:set var="k" value="${k + 1 }" /><c:set var="i1" value="${i1 + 1 }" />
                                                  </c:forEach>
                                                  <c:set var="i" value="${i + 1 }" />
                                              </c:forEach>
                                              <tr >
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                                  <td align="right" class="tdgridnew1" ><label><B><bean:message key="label.common.totalSublets" /></B></label></td>
                                                  <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"   value="${jobcardDetails.totalOtherCharges}"/></span>
                                                      <input type="hidden"  name="totalOtherCharges" value="${jobcardDetails.totalOtherCharges}" />
                                                  </td>
                                              </tr>
                                              <tr >
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                                  <td align="right" class="tdgridnew1" ><label><B><bean:message key="label.common.ttllabourcharge_small" /></B></label></td>
                                                  <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"   value="${jobcardDetails.totalLabourCharges}"/></span>
                                                      <input type="hidden"  name="totalLabourCharges" value="${jobcardDetails.totalLabourCharges}" />
                                                  </td>
                                              </tr>
                                              <tr >
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                                  <td align="right" class="tdgridnew1"><label><B><bean:message key="label.common.otherdiscount" /></B></label></td>
                                                  <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>
                                                     <fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"  value="${jobcardDetails.totaldiscount}"/></span>
                                                      <input type="hidden"  name="totaldiscount" value="${jobcardDetails.totaldiscount}" />
                                                  </td>
                                              </tr>
                                              <%--<tr >
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                                  <td align="right" class="tdgridnew1">&nbsp;</td>
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                              </tr>--%></c:if>
                                              <c:if test="${jobcardDetails.jctype ne 'CounterSale'}">
                                              <tr >
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                                  <td align="right" class="tdgridnew1"><label><B><bean:message key="label.common.GrandTotal" /></B></label></td>
                                              <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span><fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2"   value="${jobcardDetails.totalEstimate}"/></span>
                                                  <input type="hidden" name="totalEstimate" value="${jobcardDetails.totalEstimate}" />
                                              </td>
                                          </tr>
                                          <c:if test="${jobcardDetails.creditValue ne '0.0'}">
                                              <tr >
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                                  <td align="left" class="tdgridnew1">&nbsp;</td>
                                                  <td align="right" class="tdgridnew1"><label><B><bean:message key="label.counterSale.creditAmount" /></B></label></td>
                                                  <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${jobcardDetails.creditValue}</span></td>
                                              </tr></c:if>
                                          </c:if>
                                      </table></td>
                              </tr>
                            </table>
                    </td>
                </tr>

            
            </table><%--</form>--%>
        </div>
    </div>
    <div align="left" class="footer_print" style="background-color: #ffffff">
    <p style="padding-left: 15px;">* All prices are inclusive of all taxes.</p>
    <p class="textftl"><b>Customer Name & Signature</b></p>
    <p class="textftr"><b>Dealer representative Name & <br>Signature with Stamp</b></p>
    <p class="textftd"><b>Date</b></p>
    </div>
</center>
</body>
