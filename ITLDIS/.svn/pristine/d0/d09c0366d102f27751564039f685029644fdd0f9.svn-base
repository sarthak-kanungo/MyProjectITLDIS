<%-- 
    Document   : v_ListofInv4print
    Created on : Nov 3, 2014, 10:10:15 AM
    Author     : megha.pandya
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
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_6.js"></script>
<script src="${pageContext.request.contextPath}/js/base64.js"></script>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
   function printInvoice(invoiceNo,refNo)
   {
      var jobCardNo='${servForm.jobCardNo}';
      var strURL = "${pageContext.request.contextPath}/serviceProcess.do?option=printInvoice&invoiceNo="+Base64.encode(invoiceNo.trim())+"&refNo="+Base64.encode(refNo.trim())+"&type="+Base64.encode("print");
      window.open(strURL,'iji','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
   }
   function printTaxInvoice(invoiceNo,refNo)
        {
            var strURL = "${pageContext.request.contextPath}/serviceProcess.do?option=printTaxInvoice&invoiceNo="+Base64.encode(invoiceNo.trim())+"&refNo="+Base64.encode(refNo.trim())+"&type="+Base64.encode("print");
            window.open(strURL,'taxInv','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
        }
  

function submitpdiviewform()
    {
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
        document.getElementById("invoiceforPrint").submit();
    }
   function Export()
    {
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
        document.getElementById("invoiceforPrint").submit();
    }


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

            $(document).ready(function(){
                $('.viewJobcard').click(function(){
                    var url = "serviceProcess.do?option=viewJobcard&jobCardNo=" +Base64.encode($(this).text().trim())+"&flag="+Base64.encode("viewAll");
                    $(this).attr('href',url);
                });
            });
</script>


 <div class="contentmain1">
     <div class="breadcrumb_container">
         <ul class="hText">
             <li class="breadcrumb_link"><a href="<%=cntxpath%>/inventoryAction.do?option=initInvOptions"><bean:message key="label.common.spares" /></a></li>
             <li class="breadcrumb_link"><bean:message key="label.common.viewInvoice" /></li>
         </ul>
     </div>
<%--<div class="message" id="msg_saveFAILED" align="center">${message}</div>--%>

 <center>


     <div class="content_right1">
         <div class="con_slidediv2" style="position: relative;width: 100%">
             <h1 class="hText"><bean:message key="label.common.viewInvoice" /></h1>
             <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                 <tr height=50 bgcolor="#FFFFFF">
                     <td align= center class="headingSpas">
                     <form name="invoiceforPrint" id="invoiceforPrint" method="post" action="<%=cntxpath%>/serviceProcess.do?option=initviewInvoiceList&flag=check" onsubmit="return false;">
                    <table width="100%" border="0" align="center" cellpadding="1" cellspacing="1">
                        <tbody >
                            <tr bgcolor="#FFFFFF">
                                <td  style="width:10%"  class="headingSpas" nowrap align="right">
                                    <bean:message key="label.common.InvoiceNo" />
                                </td>
                                <td style="width:10%" align="left">
                                    <input name="invoiceno" type="text" id="Invoiceno" value="${servForm.invoiceno}" style="width: 100px"  onblur="this.value=TrimAll(this.value)"/>
                                </td>
                                 <%if (userFunctionalities.contains("101")) {%>
                                 <td  style="width:3%" class="headingSpas" nowrap >
                                    <c:if test="${creditAmount eq '1'}">
                                    <input type="hidden" name="creditAmount" value="0"  id="CreditInvoice"  >
                                     </c:if>
                                    <c:if test="${creditAmount ne '1' }">
                                         <input type="hidden" name="creditAmount" value="0"  id="CreditInvoice" >
                                     </c:if>
                                     <%-- <bean:message key="label.invoice.credit"/> --%><%-- onclick="disableDate(this)"--%>
                                </td>
                                  <%}%>
                                <td  style="width:8%"  class="headingSpas" nowrap align="right">
                                     <c:if test="${range eq '1'}">
                                        <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                    </c:if>
                                    <c:if test="${range ne '1' }">
                                        <input type="checkbox" name="range" value="1"  id="Range" >
                                    </c:if>
                                    <bean:message key="label.common.InvoiceDate"/></td>
                                <td style="width:8%" align="left">
                                    <input name="fromdate" type="text" class="datepicker" id="From Date" style="width: 60px" value="${servForm.fromdate}" onkeydown="return false;"/>
                                </td>
                                <td  style="width:8% "  class="headingSpas" nowrap align="center"><bean:message key="label.common.to"/></td>
                                <td style="width:8%" align="left">
                                   <input name="todate" type="text" class="datepicker" id="To Date" style="width: 60px" value="${servForm.todate}"  onkeydown="return false;"/>
                                </td>
                                
                                  <%if (userFunctionalities.contains("101")) {%>
                                                    <%} else{%>
                                                    <td align="right" style="white-space: nowrap;"> <bean:message key="label.common.dealerName" />    </td>
                                                    <td align="left">
                                         <select id="Dealer Name" name="dealercode" style="width:250px !important">
                                            <%-- <option value='' >--Select Dealer--</option>--%>
                                             <option value="ALL">ALL</option>
                                             <c:forEach items="${dealerList}"  var="labelValue">
                                                 <c:if test="${servForm.dealercode eq labelValue[0]}">
                                                     <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]" selected>${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                 </c:if>
                                                 <c:if test="${servForm.dealercode ne labelValue[0]}">
                                                     <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]">${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                                 </c:if>
                                             </c:forEach>
                                         </select>
                                     </td>
                                  <%}%>
                                  <td style="padding-left:10px;white-space: nowrap;" align="left"  >
                                    <input name="Srch" type="text" id="Srch" value="" style="display: none"/>
                                    <input name="go" type="submit" id="go" class="headingSpas1" value="search" onclick="submitpdiviewform()"/>
                                   <input type="button" value="Export" onclick="Export()"/> <input type="hidden" name="eType" id="eType" />
                                </td>
                            </tr>
                     </tbody>
                    </table>
                    </form>
                 </td>
               </tr>
               <c:if test="${!empty invoiceList4print}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="serviceProcess.do" maxIndexPages="10" maxPageItems="15">
                                    <pg:param name="option" value="initviewInvoiceList"/>
                                    <pg:param name="invoiceno"  value='${servForm.invoiceno}'/>
                                    <pg:param name="fromdate"  value='${servForm.fromdate}'/>
                                    <pg:param name="todate"  value='${servForm.todate}'/>
                                    <pg:param name="dealercode"  value='${servForm.dealercode}'/>
                                    <pg:param name="flag"  value='check'/>
                                    <c:if test="${range eq '1'}"><pg:param name="range"  value='1'/> </c:if>
                                    <c:if test="${creditAmount eq '1'}"><pg:param name="creditAmount"  value='0'/> </c:if>
                                    <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td nowrap align="center" width="2%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.sno" />
                                            </td>
                                            <td nowrap align="left" width="14%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.InvoiceNo" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.InvoiceDate" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.CustomerName" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.contactno" />
                                            </td>
                                            <td nowrap align="left" width="14%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.referenceNo" />
                                            </td>
                                            <td nowrap align="right" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.invoice" />  <bean:message key="label.common.amt_small" />
                                            </td>
                                            
                                           
                                           <%--<td nowrap align="right" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.creditNote.creditAmount" />
                                            </td>--%>
                                           <%if (!userFunctionalities.contains("101")) {%>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;white-space: nowrap" >
                                                <bean:message key="label.common.dealerName" /> 
                                            </td>
                                            <%}%>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="invoiceList4print" name="invoiceList4print">
                                            <pg:item>
                                                <tr  bgcolor="#eeeeee" height="20">
                                                    <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                    <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                        <a onclick="printInvoice('${invoiceList4print.invoiceno}','${invoiceList4print.refNo}');" href="javascript:void(0);" >
                                                        <span >${invoiceList4print.invoiceno}</span></a>
                                                       <%if (userFunctionalities.contains("623")) {%>
                                                                <a onclick="printTaxInvoice('${invoiceList4print.invoiceno}','${invoiceList4print.refNo}');" href="javascript:void(0);" ><img src="${pageContext.request.contextPath}/images/taxInvoice.png" height="15px" width="15px"  title="Tax Invoice"/>
                                                        </a>
                                                       <%}%>
                                                    </td>
                                                    <td align="left" nowrap   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${invoiceList4print.invoiceDate}</td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.customerName}</span></td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.mobilePhone}</span></td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                        <span>
                                                            <c:if test="${invoiceList4print.refNo ne 'NA'}">
                                                              <%--<a href="<%=cntxpath%>/serviceProcess.do?option=viewJobcard&jobCardNo=${invoiceList4print.refNo}&flag=viewAll">${invoiceList4print.refNo}</a>--%>
                                                              <a href="#" class="viewJobcard">${invoiceList4print.refNo}</a>
                                                            </c:if>
                                                            <c:if test="${invoiceList4print.refNo eq 'NA'}">
                                                              ${invoiceList4print.refNo}
                                                            </c:if>
                                                       </span>
                                                    </td>
                                                    <td align="right"   bgcolor="#FFFFFF" style="padding-right:3px;FONT-SIZE: 11px;"><span ><fmt:formatNumber  type="number" minFractionDigits="2" maxFractionDigits="2"  value="${invoiceList4print.totalEstimate}"/></span></td>
                                                 
                                                    <%--<%if (userFunctionalities.contains("101")) {%>
                                                  <td align="right"   bgcolor="#FFFFFF" style="padding-right:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.creditValue}</span></td>--%>
                                                    <%if (!userFunctionalities.contains("101")) {%>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;white-space: nowrap"><span>${invoiceList4print.dealercode} [${invoiceList4print.dealerName}] [${invoiceList4print.locationName}]</span></td>
                                                    <%--<td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>${invoiceList4print.locationName}</span></td>--%>
                                                    <%}%>
                                                </tr>
                                            </pg:item>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        <tr>
                                          <%if (userFunctionalities.contains("101")) {%>
                                            <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                            <%} else {%>
                                            <td colspan="8" align="center" bgcolor="#FFFFFF" class="textPaging" >
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
                                </pg:pager>
                            </td>
                        </tr>
                    </c:if>
                        <c:if test="${empty invoiceList4print}">
                            <tr  class="headingSpas" bgcolor="#FFFFFF" height="20">
                                <td valign="bottom" align="center" style="padding-top:10px;color: red"><bean:message key="label.common.noRecordFound" /></td>
                            </tr>
                        </c:if>
                </table>
          </div>
   </div>
 </center>
 </div>
