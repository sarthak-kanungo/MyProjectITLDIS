<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : v_InvoiceTransList
    Created on : Nov 29, 2014, 3:29:32 PM
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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        var strURL = "${pageContext.request.contextPath}/serviceProcess.do?option=printInvoice&invoiceNo="+                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        Base64.encode(invoiceNo.trim())+"&refNo="+Base64.encode(refNo.trim())+"&type="+Base64.encode("print");
        window.open(strURL,'iji','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
    }

    function printTrasDetails(refNo,action,dealerCode)
    {
      
        var strURL = "${pageContext.request.contextPath}/serviceProcess.do?option=printTrasDetails&refNo="+refNo+"&action="+action+"&type=print&dealerCode="+dealerCode;
     
        window.open(strURL,'iji','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
    }

    function setRefNo(){
        var ref="${servForm.refNo}";
        document.getElementById("Ref No").value=ref;
    }
    function search()
    {
        if(document.getElementById("Ref No").value=="")
        {
            alert(not_blank_dropdown_validation_msg +"Transaction")
            return false;
        }
        document.getElementById('etype').value="view";
        document.getElementById("myForm").submit();
    }
    function Export()
    {
        if(document.getElementById("Ref No").value=='')
        {
            alert(filter_validation_msg)
            return false;
        }
        document.getElementById('etype').value="export";
        document.getElementById('myForm').submit();
    }

    $(document).ready(function(){
        $('.viewJobcards').click(function(){
            var url = "serviceProcess.do?option=viewJobcard&jobCardNo="+Base64.encode($(this).text())+"&flag="+Base64.encode("viewAll");
            $(this).attr('href',url);
        });
    <%--  $('.grnlink').click(function(event){
          event.preventDefault();
          alert($('#dealercode').val())
          alert(Base64.encode($(this).text().trim()))
          var url = "serviceProcess.do?option=printGrn&grnNo="+Base64.encode($(this).text().trim())+"&dealerCode="+dealerCode;
          window.open(url,'iji','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
      });--%>

    <%--    $('.pageNavigate').click(function(){
            var urlString = $(this).attr('href');
            var leftString;
            var rightString;
            var urlArray = urlString.split('&');
            var url = "serviceProcess.do?option=invoiceTransctionList";
            $.each(urlArray,function(idx,val){
                if(idx!=0 && idx != 5){
                    rightString = urlArray[idx].substr(urlArray[idx].indexOf('=')+1);
                    leftString = urlArray[idx].substring(0, urlArray[idx].indexOf('='));
                    if(rightString.trim().length > 0){
                    url +="&"+leftString+"="+Base64.encode(rightString.trim());
                    }else{
                     url+="&"+leftString+"=";
                    }
                }else if(idx == 5){
                    url+=urlArray[idx];
                }
            });

        });--%>

            });


            function printGRN(grNo,dealerCode)
            {
    <%--var url = "serviceProcess.do?option=printGrn&grnNo="+Base64.encode($(this).text().trim())+"&dealerCode="+dealerCode;--%>
            var url = "serviceProcess.do?option=printGrn&grnNo="+Base64.encode(grNo.trim())+"&dealerCode="+dealerCode;
            window.open(url,'iji','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
        }
        function printTaxInvoice(invoiceNo,refNo)
        {
            var strURL = "${pageContext.request.contextPath}/serviceProcess.do?option=printTaxInvoice&invoiceNo="+Base64.encode(invoiceNo.trim())+"&refNo="+Base64.encode(refNo.trim())+"&type="+Base64.encode("print");
            window.open(strURL,'taxInv','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
        }
</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href="<%=cntxpath%>/inventoryAction.do?option=initInvOptions"><bean:message key="label.common.spares" /></a></li>
            <li class="breadcrumb_link"><bean:message key="label.spares.transDetail" /></li>
        </ul>
    </div>
    <center>


        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.spares.transDetail" /></h1>
                <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr height=50 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <form  id="myForm" method="post" action="<%=cntxpath%>/serviceProcess.do?option=invoiceTransctionList" onsubmit="">
                                <table width="100%" border="0" align="center" cellpadding="1" cellspacing="1">
                                    <tbody >

                                        <%if (!userFunctionalities.contains("101")) {%>
                                        <tr bgcolor="#FFFFFF">
                                            <td align="left" class="headingSpas" nowrap style="padding-left: 5px" > <bean:message key="label.common.dealerName" />    </td>
                                            <td>
                                                <select id="Dealer Name" name="dealercode" id="dealercode" style="width:300px !important">
                                                    <option value="" >--Select Here--</option>
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
                                            <td    class="headingSpas" nowrap align="right"><bean:message key="label.common.transDate"/></td>
                                            <td  align="left"  style="width: 2%">
                                                <input name="fromdate" type="text" class="datepicker" id="From Date"  value="${servForm.fromdate}" onkeydown="return false;" />
                                            </td>
                                            <td  class="headingSpas" nowrap align="center"><bean:message key="label.common.to"/></td>
                                            <td align="left" >
                                                <input name="todate" type="text" class="datepicker" id="To Date" value="${servForm.todate}"  onkeydown="return false;" />
                                            </td>
                                            <td   class="headingSpas" nowrap align="right"><bean:message key="label.spares.SelTansac"/></td>
                                            <td align="left">
                                                <select id="Ref No" name="refNo" style="width:160px !important">
                                                    <option value="" >--Select Here--</option>
                                                    <option value="1">Invoice </option>
                                                    <option value="2">Counter Sale Return</option>
                                                    <option value="3">Inventry From Other Dealer</option>
                                                    <option value="4">GRN Details</option>

                                                </select>
                                            </td>
                                        </tr>
                                        <%} else {%>
                                        <tr>
                                            <td    class="headingSpas" nowrap align="right"><bean:message key="label.common.transDate"/></td>
                                            <td  align="left"  style="width: 2%">
                                                <input name="fromdate" type="text" class="datepicker" id="From Date"  value="${servForm.fromdate}" onkeydown="return false;" />
                                            </td>
                                            <td  class="headingSpas" nowrap align="center"><bean:message key="label.common.to"/></td>
                                            <td align="left" >
                                                <input name="todate" type="text" class="datepicker" id="To Date" value="${servForm.todate}"  onkeydown="return false;" />
                                            </td>

                                            <td   class="headingSpas" nowrap align="right"><bean:message key="label.spares.SelTansac"/></td>
                                            <td align="left">
                                                <select id="Ref No" name="refNo" style="width:160px !important">
                                                    <option value="" >--Select Here--</option>
                                                  
                                                    <option value="1">Invoice </option>
                                                    <option value="2">Counter Sale Return</option>
                                                    <option value="3">Inventory From Other Dealer</option>
                                                    <option value="4">GRN Details</option>
                                                      <%if (userFunctionalities.contains("856")) {%>
                                                    <option value="5">Stock Adjustment</option>
                                                    <%}%>
                                                </select>
                                            </td>
                                            <td>
                                                <input name="Srch" type="text" id="Srch" value="" style="display: none"/>
                                                <input type="button" id="srbutton" name="go" value="search" onclick="search()"/>
                                                <input type="button" value="Export" onclick="Export()"/> <input type="hidden" name="etype" id="etype" />
                                            </td>

                                        </tr>
                                        <%}%>
                                    </tbody>

                                </table>
                                <%if (!userFunctionalities.contains("101")) {%>
                                <div style=" padding-right: 10px;float: right;">
                                    <input name="Srch" type="text" id="Srch" value="" style="display: none"/>
                                    <input type="button" id="srbutton" name="go" value="search" onclick="search()"/>
                                    <input type="button" value="Export" onclick="Export()"/> <input type="hidden" name="etype" id="etype" />
                                </div>
                                <%}%>


                            </form>
                        </td>
                    </tr>

                    <c:if test="${action eq 'Invoice'}">
                        <c:if test="${!empty invoiceList4print}">
                            <tr height=25 bgcolor="#FFFFFF">
                                <td align= center class="headingSpas">
                                    <pg:pager url="serviceProcess.do" maxIndexPages="10" maxPageItems="15">
                                        <pg:param name="option" value="invoiceTransctionList"/>
                                        <pg:param name="invoiceno"  value='${servForm.invoiceno}'/>
                                        <pg:param name="fromdate"  value='${servForm.fromdate}'/>
                                        <pg:param name="todate"  value='${servForm.todate}'/>
                                        <pg:param name="refNo"  value='${servForm.refNo}'/>
                                         <pg:param name="dealercode"  value='${servForm.dealercode}'/>
                                        <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                            <tr bgcolor="#eeeeee" height="20">
                                                <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.sno" />
                                                </td>
                                                <td nowrap align="left" width="18%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.InvoiceNo" />
                                                </td>
                                                <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.InvoiceDate" />
                                                </td>
                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.CustomerName" />
                                                </td>
                                                <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.contactno" />
                                                </td>
                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.referenceNo" />
                                                </td>
                                                <td nowrap align="right" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.invoice" />  <bean:message key="label.common.amt_small" />
                                                </td>
                                                <%if (userFunctionalities.contains("101")) {%>
                                                <%} else {%>
                                                <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
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
                                                                <%--<a href="<%=cntxpath%>/serviceProcess.do?option=viewJobcard&jobCardNo=${invoiceList4print.refNo}&flag=viewAll" class="viewJobcards" >${invoiceList4print.refNo}</a>--%>
                                                                <a href="#" class="viewJobcards" >${invoiceList4print.refNo}</a>
                                                            </span>
                                                        </td>
                                                        <td align="right"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.totalEstimate}</span></td>

                                                        <%if (userFunctionalities.contains("101")) {%>
                                                        <%} else {%>
                                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>${invoiceList4print.dealercode} [${invoiceList4print.dealerName}] [${invoiceList4print.locationName}]</span></td>
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
                                                <td colspan="9" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                    <%}%>

                                                    <table  align="center" cellspacing="0" cellpadding="0">
                                                        <tr>
                                                            <td>
                                                                <pg:index>
                                                                    <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle" style="float:left;"/></a></pg:first>&nbsp;
                                                                    <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle" style="float:left;"/></a></pg:prev>&nbsp;
                                                                    <pg:pages>&nbsp;<a style="color: blue!important;font: bold small Palatino, serif;"  href="${pageUrl}" class="pageNavigate">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
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
                    </c:if>


                    <c:if test="${action eq 'CounterSale'}">
                        <c:if test="${!empty invoiceList4print}">
                            <tr height=25 bgcolor="#FFFFFF">
                                <td align= center class="headingSpas">
                                    <pg:pager url="serviceProcess.do" maxIndexPages="10" maxPageItems="15">
                                        <pg:param name="option" value="invoiceTransctionList"/>
                                        <pg:param name="invoiceno"  value='${servForm.invoiceno}'/>
                                        <pg:param name="fromdate"  value='${servForm.fromdate}'/>
                                        <pg:param name="todate"  value='${servForm.todate}'/>
                                        <pg:param name="refNo"  value='${servForm.refNo}'/>
                                        <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                            <tr bgcolor="#eeeeee" height="20">
                                                <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.sno" />
                                                </td>
                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.spares.returnNo" />
                                                </td>
                                                <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.saleReturn.ReturnDate" />
                                                </td>
                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.InvoiceNo" />
                                                </td>

                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.saleReturn.ReturnBy" />
                                                </td>

                                                <td nowrap align="right" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.invoice" />  <bean:message key="label.common.amt_small" />
                                                </td>
                                                <%if (userFunctionalities.contains("101")) {%>
                                                <%} else {%>
                                                <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.dealerName" />
                                                </td>

                                                <%}%>
                                            </tr>
                                            <c:set var="sno" value="1"></c:set>
                                            <logic:iterate id="invoiceList4print" name="invoiceList4print">
                                                <pg:item>
                                                    <tr  bgcolor="#eeeeee" height="20">
                                                        <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">

                                                            <a onclick="printTrasDetails('${invoiceList4print.refNo}','couSaleReturn','${invoiceList4print.dealercode}');" href="javascript:void(0);" >
                                                                <span >${invoiceList4print.refNo}</span></a>

                                                        </td>
                                                        <td align="left" nowrap   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${invoiceList4print.invoiceDate}</td>
                                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">

                                                            ${invoiceList4print.invoiceno}
                                                        </td>

                                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.customerName}</span></td>
                                                        <td align="right"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.totalEstimate}</span></td>

                                                        <%if (userFunctionalities.contains("101")) {%>
                                                        <%} else {%>
                                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>${invoiceList4print.dealercode} [${invoiceList4print.dealerName}] [${invoiceList4print.locationName}]</span></td>
                                                        <%--<td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>${invoiceList4print.locationName}</span></td>--%>
                                                        <%}%>
                                                    </tr>
                                                </pg:item>
                                                <c:set var="sno" value="${sno + 1 }" />
                                            </logic:iterate>
                                            <tr>
                                                <%if (userFunctionalities.contains("101")) {%>
                                                <td colspan="6" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                    <%} else {%>
                                                <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                    <%}%>

                                                    <table  align="center" cellspacing="0" cellpadding="0">
                                                        <tr>
                                                            <td>
                                                                <pg:index>
                                                                    <pg:first><a href="${pageUrl}" class="pageNavigation"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle" style="float:left;"/></a></pg:first>&nbsp;
                                                                    <pg:prev><a href="${pageUrl}" class="pageNavigation"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle" style="float:left;"/></a></pg:prev>&nbsp;
                                                                    <pg:pages>&nbsp;<a style="color: blue!important;font: bold small Palatino, serif;"  href="${pageUrl}" class="pageNavigation">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                                                    <pg:last><a href="${pageUrl}" class="pageNavigation"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle" style="float:right"/></a></pg:last>
                                                                    <pg:next><a href="${pageUrl}" class="pageNavigation"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle" style="float:right;"/></a></pg:next>&nbsp;
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
                    </c:if>



                    <c:if test="${action eq 'InvOtherDealer'}">
                        <c:if test="${!empty invoiceList4print}">
                            <tr height=25 bgcolor="#FFFFFF">
                                <td align= center class="headingSpas">
                                    <pg:pager url="serviceProcess.do" maxIndexPages="10" maxPageItems="15">
                                        <pg:param name="option" value="invoiceTransctionList"/>
                                        <pg:param name="invoiceno"  value='${servForm.invoiceno}'/>
                                        <pg:param name="fromdate"  value='${servForm.fromdate}'/>
                                        <pg:param name="todate"  value='${servForm.todate}'/>
                                        <pg:param name="refNo"  value='${servForm.refNo}'/>
                                        <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                            <tr bgcolor="#eeeeee" height="20">
                                                <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.sno" />
                                                </td>
                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.spares.refNo" />
                                                </td>
                                                <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.billdate" />
                                                </td>
                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.vandorName" />
                                                </td>

                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.billNo" />
                                                </td>
                                                <td nowrap align="right" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.invoice" />  <bean:message key="label.common.amt_small" />
                                                </td>
                                                <%if (userFunctionalities.contains("101")) {%>
                                                <%} else {%>
                                                <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
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
                                                            <a onclick="printTrasDetails('${invoiceList4print.invoiceno}','InvOtherDealer','${invoiceList4print.dealercode}');" href="javascript:void(0);" >
                                                                <span >${invoiceList4print.invoiceno}</span></a>
                                                                <%-- ${invoiceList4print.invoiceno}--%>
                                                        </td>
                                                        <td align="left" nowrap   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${invoiceList4print.invoiceDate}</td>
                                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.customerName}</span></td>
                                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.refNo}</span></td>
                                                        <td align="right"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.totalEstimate}</span></td>
                                                        <%if (userFunctionalities.contains("101")) {%>
                                                        <%} else {%>
                                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>${invoiceList4print.dealercode} [${invoiceList4print.dealerName}] [${invoiceList4print.locationName}]</span></td>
                                                        <%}%>
                                                    </tr>
                                                </pg:item>
                                                <c:set var="sno" value="${sno + 1 }" />
                                            </logic:iterate>
                                            <tr>
                                                <%if (userFunctionalities.contains("101")) {%>
                                                <td colspan="6" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                    <%} else {%>
                                                <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
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
                    </c:if>

                    <c:if test="${action eq 'exportdetail'}">
                        <c:if test="${!empty invoiceList4print}">
                            <tr height=25 bgcolor="#FFFFFF">
                                <td align= center class="headingSpas">
                                    <pg:pager url="serviceProcess.do" maxIndexPages="10" maxPageItems="15">
                                        <pg:param name="option" value="invoiceTransctionList"/>
                                        <pg:param name="invoiceno"  value='${servForm.invoiceno}'/>
                                        <pg:param name="fromdate"  value='${servForm.fromdate}'/>
                                        <pg:param name="todate"  value='${servForm.todate}'/>
                                        <pg:param name="refNo"  value='${servForm.refNo}'/>
                                        <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                            <tr bgcolor="#eeeeee" height="20">
                                                <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.sno" />
                                                </td>
                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.stockAdjno" />
                                                </td>
                                                <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.stockAdjdate" />
                                                </td>
                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.totalpurchageval" />
                                                </td>

                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.totalsalesval" />
                                                </td>
                                                <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.nooflines" /> 
                                                </td>
                                               
                                            </tr>
                                            <c:set var="sno" value="1"></c:set>
                                            <logic:iterate id="invoiceList4print" name="invoiceList4print">
                                                <pg:item>
                                                    <tr  bgcolor="#eeeeee" height="20">
                                                        <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                            <a onclick="printTrasDetails('${invoiceList4print.stkAdjNo}','exportdetail','${invoiceList4print.dealercode}');" href="javascript:void(0);" >
                                                                <span >${invoiceList4print.stkAdjNo}</span></a>
                                                                <%-- ${invoiceList4print.invoiceno}--%>
                                                        </td>
                                                        <td align="left" nowrap   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${invoiceList4print.stkAdjDate}</td>
                                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.totalPuschaseValue}</span></td>
                                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.totalSaleValue}</span></td>
                                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.noOfLines}</span></td>
                                                        
                                                    </tr>
                                                </pg:item>
                                                <c:set var="sno" value="${sno + 1 }" />
                                            </logic:iterate>
                                            <tr>
                                                <%if (userFunctionalities.contains("101")) {%>
                                                <td colspan="6" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                    <%} else {%>
                                                <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
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
                    </c:if>


                    <c:if test="${action eq 'grndetail'}">
                        <c:if test="${!empty invoiceList4print}">
                            <tr height=25 bgcolor="#FFFFFF">
                                <td align= center class="headingSpas">
                                    <pg:pager url="serviceProcess.do" maxIndexPages="10" maxPageItems="15">
                                        <pg:param name="option" value="invoiceTransctionList"/>
                                        <pg:param name="invoiceno"  value='${servForm.invoiceno}'/>
                                        <pg:param name="fromdate"  value='${servForm.fromdate}'/>
                                        <pg:param name="todate"  value='${servForm.todate}'/>
                                        <pg:param name="refNo"  value='${servForm.refNo}'/>
                                        <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                            <tr bgcolor="#eeeeee" height="20">
                                                <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.sno" />
                                                </td>
                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.spares.grNo" />
                                                </td>
                                                <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.spares.grdate" />
                                                </td>
                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.spares.invoiceno" />
                                                </td>

                                                <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.common.invoice" />&nbsp; <bean:message key="label.common.date" />
                                                </td>
                                                <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.warranty.receivedBy" />
                                                </td>

                                                <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                    <bean:message key="label.spares.receivedon" />
                                                </td>
                                            </tr>
                                            <c:set var="sno" value="1"></c:set>
                                            <logic:iterate id="invoiceList4print" name="invoiceList4print">
                                                <pg:item>
                                                    <tr  bgcolor="#eeeeee" height="20">
                                                        <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                        <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                            <a href="#" class="grnlink" onclick="printGRN('${invoiceList4print.grNo}',${invoiceList4print.dealerCode})">
                                                                <span >${invoiceList4print.grNo}</span></a>
                                                                <%-- ${invoiceList4print.invoiceno}--%>
                                                        </td>
                                                        <td align="left" nowrap   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                            <fmt:formatDate pattern="dd/MM/yyyy" value="${invoiceList4print.grDate}" />
                                                        </td>
                                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.invoiceNo}</span></td>
                                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >
                                                                <fmt:formatDate pattern="dd/MM/yyyy" value="${invoiceList4print.invoiceDate}" />
                                                            </span></td>
                                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${invoiceList4print.receivedBy}</span></td>
                                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span>
                                                                <fmt:formatDate pattern="dd/MM/yyyy" value="${invoiceList4print.receivedOn}" />
                                                            </span></td>
                                                    </tr>
                                                </pg:item>
                                                <c:set var="sno" value="${sno + 1 }" />
                                            </logic:iterate>
                                            <tr>
                                                <%if (userFunctionalities.contains("101")) {%>
                                                <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                    <%} else {%>
                                                <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
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
<body onload="setRefNo()"></body>

