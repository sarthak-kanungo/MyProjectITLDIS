<%-- 
    Document   : viewOrderInvoiceList
    Created on : 21-Feb-2015, 10:19:33
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
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';

    function submitForm()
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
    <%if (!userFunctionalities.contains("101")) {%>
                OnDealerChange(document.getElementById('Dealer Name'));
    <%}%>
              document.getElementById('eType').value="";
              document.getElementById("searchBy").submit();
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
          function Export(val)
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

              document.getElementById('eType').value=val;
              document.getElementById('searchBy').submit();
          }
          $(document).ready(function(){
              $(document).ready(function(){
                  $('.invoiceClass').click(function(){
                      //event.preventDefault();
                      var url = "inventoryAction.do?option=viewOrderInvoiceDetail&invNo="+Base64.encode($(this).text().trim())+"&dealerCode="+Base64.encode($(this).attr("data-dealerCode").trim());
                      $(this).attr("href",url);
                  });
              });
          });
          function OnDealerChange(obj)
          {
              if( obj!="ALL"){
                  document.getElementById('Billing_Details').style.visibility = 'visible';
              }
              else{
                  document.getElementById('Billing_Details').style.visibility = 'hidden';
              }
          }

</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>
            <li class="breadcrumb_link"><bean:message key="label.spare.viewOrderInvoice" /></li>
        </ul>
    </div>


    <center>


        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.spare.viewOrderInvoice" /></h1>
                <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr height=50 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="inventoryAction.do?option=viewOrderInvoice&flag=check" method="post" styleId="searchBy" onsubmit="donotsubmit();">

                                <table width=100%  border=0 cellspacing=0 cellpadding=4 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF" >
                                        <td class="headingSpas" nowrap align="right" width="15%"> <bean:message key="label.common.InvoiceNo" /></td>
                                        <td align="left" width="10%"><input name="invNo" type="text" id="InvNo" value="${warrantyForm.invNo}" style="width:80px !important " onblur="this.value=TrimAll(this.value)"/></td>
                                        <td class="headingSpas" nowrap align="right" width="15%"> <bean:message key="label.order.suppOrderNo" /></td>
                                        <td align="left" width="10%"><input name="invOrderNo" type="text" id="InvOrderNo" value="${warrantyForm.invOrderNo}" style="width:80px !important " onblur="this.value=TrimAll(this.value)"/></td>
                                        <td class="headingSpas" nowrap align="right" width="10%">
                                            <c:if test="${range eq '1'}">
                                                <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                            </c:if>
                                            <c:if test="${range ne '1' }">
                                                <input type="checkbox" name="range" value="1"  id="Range" >
                                            </c:if>
                                            <bean:message key="label.common.InvoiceDate" />
                                        </td>
                                        <td   align="left" width="5%">
                                            <input type="text" name="fromdate" class="datepicker" id="From Date" style="width:70px !important " class="txtGeneralNW" value="${inventoryForm.fromdate}"  readonly="readonly"/>
                                        </td>
                                        <td class="headingSpas" nowrap align="left" width="1%"  >
                                            To
                                        </td>
                                        <td   align="left" width="30%">
                                            <input type="text" name="todate" class="datepicker" id="To Date" style="width:70px !important " class="txtGeneralNW" value="${inventoryForm.todate}"  readonly="readonly"/>
                                        </td>
                                        <%if (userFunctionalities.contains("101")) {%>
                                        <td  align="left" width="40%" style="white-space: nowrap;padding-right: 20px;">
                                            <input name="go" type="button" value="search" style="float:none; " onClick = "submitForm()"/>&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="button" value="Export" onclick="Export(this.value)"/> <input type="hidden" name="eType" id="eType" />
                                        </td>
                                        <%}%>
                                    </tr>
                                    <%if (userFunctionalities.contains("101")) {%>
                                    <%} else {%>
                                    <tr bgcolor="#FFFFFF">
                                        <td align="right" style="white-space: nowrap;"> <bean:message key="label.common.dealerName" />    </td>
                                        <td colspan="4" align="left">
                                            <select id="Dealer Name" name="dealerCode" style="width:300px !important" onchange="OnDealerChange(this.value);">
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
                                        <td  align="left" colspan="4" style="padding-left: 10px;">
                                            <input name="Srch" type="text" id="Srch" value="" style="display: none"/>
                                            <input name="go" type="button" value="search" style="float:none; " onClick = "submitForm()"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="button" value="Export" onclick="Export(this.value)"/> <input type="hidden" name="eType" id="eType" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                            <input type="button" id="Billing_Details" value="Billing Details" onclick="Export(this.value)"/>
                                        </td>
                                        <%}%>
                                    </tr>

                                </table>
                            </html:form>

                        </td>
                    </tr>
                </table>
                <c:if test="${!empty viewOrderList}">

                    <pg:pager url="inventoryAction.do" maxIndexPages="10" maxPageItems="10">
                        <pg:param name="option" value="viewOrderInvoice"/>
                        <pg:param name="invNo"  value='${inventoryForm.invNo}'/>
                        <pg:param name="invOrderNo"  value='${inventoryForm.invOrderNo}'/>
                        <pg:param name="dealerCode"  value='${inventoryForm.dealerCode}'/>
                        <pg:param name="fromdate"  value='${inventoryForm.fromdate}'/>
                        <pg:param name="todate"  value='${inventoryForm.todate}'/>
                        <pg:param name="flag"  value='check'/>
                        <c:if test="${range eq '1'}"><pg:param name="range"  value='1'/> </c:if>
                        <div style="width:99%; overflow:auto">
                            <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                <tr bgcolor="#eeeeee" height="20">
                                    <td   align="center"class="headingSpas" width="4%" nowrap style="padding-left:5px; "><b><bean:message key="label.warranty.S.No" /></b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.InvoiceNo" /></b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.InvoiceDate" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.order.totalInvoiceAmount" /> </b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.order.shippmentDate" /> </b></td>
                                    <%--<td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.spare.noOfLines" /> </b></td>--%>
                                    <%if (userFunctionalities.contains("101")) {%>
                                    <%} else {%>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.dealerName" /></b></td>
                                    <%}%>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.order.lrNo" /></b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.order.transporterName" /></b></td>
                                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.order.permitNo" /></b></td>
                                </tr>
                                <c:set var="sno" value="1"></c:set>
                                <logic:iterate id="dataList" name="viewOrderList">
                                    <pg:item>
                                        <tr  bgcolor="#eeeeee" height="20">
                                            <td align="center" bgcolor="#FFFFFF" width="4%" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                            <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.invNo}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    <%--<a data-dealerCode="${dataList.dealerCode}" href="inventoryAction.do?option=viewOrderInvoiceDetail&invNo=${dataList.invNo}&dealerCode=${dataList.dealerCode}">${dataList.invNo}</a>--%>
                                                    <a data-dealerCode="${dataList.dealerCode}" href="#" class="invoiceClass">${dataList.invNo}</a>
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.invDate}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.invDate}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.finalamount}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.finalamount}
                                                </span>
                                            </td>

                                            <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.shipmentDate}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span >
                                                    ${dataList.shipmentDate}
                                                </span>
                                            </td>
                                            <%if (userFunctionalities.contains("101")) {%>
                                            <%} else {%>
                                            <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.dealerCode} [${dataList.dealerName}] [${dataList.location}]"  class="headingSpas" style="padding-left:5px; padding-right: 5px; white-space: nowrap;">
                                                <span>
                                                    ${dataList.dealerCode} [${dataList.dealerName}] [${dataList.location}]
                                                </span>
                                            </td>
                                            <%}%>
                                            <td align="left" bgcolor="#FFFFFF" width="10%" title=" ${dataList.lrNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                <span>
                                                    ${dataList.lrNo}
                                                </span>
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" width="10%" title=" ${dataList.transporterName}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                                                ${dataList.transporterName}
                                            </td>
                                            <td align="left" bgcolor="#FFFFFF" width="10%" title=" ${dataList.permitNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                                                ${dataList.permitNo}
                                            </td>





                                        </tr>
                                    </pg:item>
                                    <c:set var="sno" value="${sno + 1 }" />
                                </logic:iterate>
                                <tr>
                                    <%if (userFunctionalities.contains("101")) {%>
                                    <td colspan="8" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                        <%} else {%>
                                    <td colspan="9" align="center" bgcolor="#FFFFFF" class="textPaging" >
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

                </c:if>
                <c:if test="${empty viewOrderList}">
                    <tr  class="headingSpas" bgcolor="#FFFFFF" height="20">
                        <td valign="bottom" align="center" style="padding-top:10px;color: red"><bean:message key="label.common.noRecordFound" /></td>
                    </tr>
                </c:if>
                </table>
            </div>
        </div>
    </center>
</div>
<script>
    window.onload =function(){
        var invNo = '${inventoryForm.invNo}';
        var invOrderNo = '${inventoryForm.invOrderNo}';
        var dCode = '${inventoryForm.dealerCode}';
        if( document.getElementById("Dealer Name").value=="ALL") {
            document.getElementById('Billing_Details').style.visibility = 'hidden';
        }
        if(invNo != ""){
            document.getElementById("InvNo").value=invNo;
        }

        if(invOrderNo != ""){
            document.getElementById("InvOrderNo").value=invOrderNo;
        }
        var dealerFlag='<%=dealerFlag%>';
        if(dealerFlag=='true')
        {
            document.getElementById('Dealer Name').value=dCode;
        }
        if( document.getElementById('Dealer Name').value!="ALL"){
            document.getElementById('Billing_Details').style.visibility = 'visible';
        }
    }
</script>