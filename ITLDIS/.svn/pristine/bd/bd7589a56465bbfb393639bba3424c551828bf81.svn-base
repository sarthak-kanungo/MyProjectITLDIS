<%-- 
    Document   : ViewPackingList
    Created on : Dec 9, 2014, 4:19:31 PM
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
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ page import="java.util.*,java.text.*" %>
<%@ page import="java.util.Date,viewEcat.comEcat.PageTemplate" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
            SimpleDateFormat df1 = new SimpleDateFormat("yyyyMMdd");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String dealerFlag="";
            if (!userFunctionalities.contains("101")) {dealerFlag="true";}
%>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_6.js"></script>
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

        document.getElementById("packingList4view").submit();
    }
    function printConsignment(packingNo)
    {
        var strURL="${pageContext.request.contextPath}/warrantyAction.do?option=packingDetails4View&packingNo="+packingNo+"&print=true";
        window.open(strURL,'iji','width=1000,height=950, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
    }
    function printWarrantyMaterial(packingNo)
    {
        var strURL="${pageContext.request.contextPath}/warrantyAction.do?option=WarrantyMaterialView&packingNo="+packingNo+"&print=true";
        window.open(strURL,'iji','width=800,height=950, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
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
    function viewCourierCopyFile(courierCopyFileName)
    {
        
        var strURL="${pageContext.request.contextPath}/DOCUMENTS/WARRANTY/"+courierCopyFileName+"";
        window.open(strURL,'courierCopyFileName','width=750,height=600, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
        window.moveTo(50, 0);
    }
    function printTaxInvoice(packingNo)
    {
        var strURL = "${pageContext.request.contextPath}/warrantyAction.do?option=printDeliveryChallan&packingNo="+packingNo;
        window.open(strURL,'taxInv','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
    }
    function printWarrantyClaimChallan(packingNo)
    {
        var strURL = "${pageContext.request.contextPath}/warrantyAction.do?option=printWarrantyClaimChallan&packingNo="+packingNo;
        window.open(strURL,'taxClaimInv','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
    }
    
    function toggleCheckboxes(source) {
        const checkboxes = document.querySelectorAll('.check_list');
        checkboxes.forEach(checkbox => {
            checkbox.checked = source.checked;
        });
    }
    
    function collectAndPrintMassConsignment(packingNo) {
      
        printMassConsignment(packingNo);
    }

    function printMassConsignment(packingNo) {
    	
        var strURL = "${pageContext.request.contextPath}/warrantyAction.do?option=packingDetails4MassView&packingNo=" + packingNo + "&print=true";
        window.open(strURL, 'iji', 'width=1000,height=950,resizable=yes,scrollbars=yes,status=0,titlebar=0,toolbar=0,addressbar=0');
    }

    
      
</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.warranty.warranty" /></a></li>
            <li class="breadcrumb_link"><bean:message key="label.common.viewPackingList" /></li>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED" align="center">${message}</div>

    <center>


        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.common.viewPackingList" /></h1>
                <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr height=50 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <form name="packingList4view" id="packingList4view" method="post" action="<%=cntxpath%>/warrantyAction.do?option=viewPackingList&flag=check" onsubmit="return false;">
                                <table width="100%" border="0" align="center" cellpadding="1" cellspacing="1">
                                    <tbody >
                                        <tr bgcolor="#FFFFFF">
                                            <td style="padding-left:5px;width:5% " class="headingSpas" nowrap align="right"><span class="formLabel"><bean:message key="label.common.packingNo" /></span></td>
                                            <td align="left">
                                                <input name="packingNo" type="text" id="Packing No" value="${warrantyForm.packingNo}" />
                                            </td>
                                            <td  style="padding-left:5px;width:5% "  class="headingSpas" nowrap align="right">
                                                <c:if test="${range eq '1'}">
                                                    <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                                </c:if>
                                                <c:if test="${range ne '1' }">
                                                    <input type="checkbox" name="range" value="1"  id="Range" >
                                                </c:if>
                                                <bean:message key="label.common.packingDate"/></td>
                                            <td  align="left">
                                                <input name="fromDate" type="text" class="datepicker" id="From Date" style="width:60px"  value="${warrantyForm.fromDate}" onkeydown="return false;"/>
                                            </td>
                                            <td  class="headingSpas" nowrap align="center"><bean:message key="label.common.to"/></td>
                                            <td  align="left">
                                                <input name="toDate" type="text" class="datepicker" id="To Date" value="${warrantyForm.toDate}" style="width:60px" onkeydown="return false;"/>
                                            </td>
                                            <td  style="padding-left:5px;width:5% "  class="headingSpas" nowrap align="center"><bean:message key="label.common.status"/></td>
                                            <td align="left" >
                                                <select name="packingStatus" id="Packing Status" class="selectnewbox" class="txtGeneralNW">
                                                    <option value="">All</option>
                                                    <option value="PACKED">PACKED </option>
                                                    <option value="DISPATCHED">DISPATCHED</option>
                                                    <option value="RECEIVED">RECEIVED</option>
                                                </select>
                                            </td>
                                            <%if (userFunctionalities.contains("101")) {%>
                                            <%} else{%>
                                            <td width="15%">
                                                <select id="Dealer Name" name="dealer_code" style="width:300px !important">
                                                    <option value="ALL" >ALL</option>
                                                    <%-- <option value='' >--Select Dealer--</option>--%>
                                                    <c:forEach items="${dealerList}"  var="labelValue">
                                                        <c:if test="${warrantyForm.dealer_code eq labelValue[0]}">
                                                            <option value="${labelValue[0]}" title="${labelValue[0]} [${labelValue[1]}] [${labelValue[2]}]" selected>${labelValue[0]} [${labelValue[1]}] [${labelValue[2]}]</option>
                                                        </c:if>
                                                        <c:if test="${warrantyForm.dealer_code ne labelValue[0]}">
                                                            <option value="${labelValue[0]}" title="${labelValue[0]} [${labelValue[1]}] [${labelValue[2]}]">${labelValue[0]} [${labelValue[1]}] [${labelValue[2]}]</option>
                                                        </c:if>

                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <%}%>
                                            <td style="padding-left:5px" align="left"  >
                                                <input name="go" type="submit" id="go" class="headingSpas1" value="search" onclick="submitForm()"/>
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </td>
                    </tr>
                    <c:if test="${!empty packingList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="warrantyAction.do" maxIndexPages="10" maxPageItems="15">
                                    <pg:param name="option" value="viewPackingList"/>
                                    <pg:param name="packingStatus"  value='${warrantyForm.packingStatus}'/>
                                    <pg:param name="packingNo"  value='${warrantyForm.packingNo}'/>
                                    <pg:param name="fromDate"  value='${warrantyForm.fromDate}'/>
                                    <pg:param name="toDate"  value='${warrantyForm.toDate}'/>
                                    <pg:param name="dealer_code"  value='${warrantyForm.dealer_code}'/>
                                    <pg:param name="flag"  value='check'/>
                                    <c:if test="${range eq '1'}"><pg:param name="range"  value='1'/> </c:if>
                                    <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            
                                            <%-- <%if (userFunctionalities.contains("1014")) {%>
                                            <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <input type='checkbox' checked="checked" id='check_header' onclick="toggleCheckboxes(this)"/>
                                            </td>
                                            <%} else {%>
                                             <%}%> --%>
                                            
                                            <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.sno" />
                                            </td>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.packingNo" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.packingDate" />
                                            </td>
                                            <td nowrap align="left" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.dispatchedFor" />
                                            </td>
                                            <td nowrap align="left" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.dispatchedThrough" />
                                            </td>
                                            <td nowrap align="left" width="20%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.consignmentNo" />
                                            </td>
                                            <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.status" />
                                            </td>
                                            <td nowrap align="center" width="3%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.courierFile" />
                                            </td>
                                            <td nowrap align="center" width="18%" colspan="2" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.action" />
                                            </td>
                                            <%-- <td nowrap align="center" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                 <bean:message key="label.common.warrantymaterial" />
                                             </td>--%>

                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <c:set var="packingGenDateCheck" value="<%=df1.format(df.parse(PageTemplate.packingGenDateCheck))%>"></c:set>
                                        <logic:iterate id="packingList" name="packingList">
                                            <pg:item>
                                                <tr  bgcolor="#eeeeee" height="20">
                                                    <fmt:parseDate value="${packingList.packingDate}" pattern="dd/MM/yyyy" var="myDate"/>
                                                    <fmt:formatDate value="${myDate}" var="startFormat" pattern="yyyyMMdd"/>
                                                    
                                                    <%-- <%if (userFunctionalities.contains("1014")) {%>
                                                    <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" ><input type='checkbox' checked="checked" class='check_list' name='picklist' value='${packingList.packingNo}'/></td>
                                                     <%} else {%>
                                                     <%}%> --%>
                                                     
                                                    <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                    <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                        <a href="<%=cntxpath%>/warrantyAction.do?option=packingDetails4View&packingNo=${packingList.packingNo}" >
                                                            <span >${packingList.packingNo}</span></a></td>
                                                    <td align="left" nowrap   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${packingList.packingDate}</td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${packingList.dispatchFor}</span></td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${packingList.dispatchBy}</span></td>
                                                    <c:if test="${fn:length(packingList.courierNo) gt 1}">
                                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${packingList.courierNo} [${packingList.courierName}]</span></td>
                                                    </c:if>
                                                    <c:if test="${fn:length(packingList.courierNo) eq 1}">
                                                        <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >-</span></td>
                                                    </c:if>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${packingList.packingStatus}</span></td>
                                                    <c:if test="${fn:length(packingList.courierCopyFileName) gt 1}">
                                                        <%--<td align="center"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${packingList.courierCopyFileName}</span></td>--%>
                                                        <td align="center"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><a href="javascript:void(0)"><img src="${pageContext.request.contextPath}/images/history.jpg" height="18" width="18"  onclick="viewCourierCopyFile('${packingList.courierCopyFileName}');" title="${packingList.courierCopyFileName}"/></a></td>
                                                            </c:if>
                                                            <c:if test="${fn:length(packingList.courierCopyFileName) eq 1}">
                                                        <td align="center"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >-</span></td>
                                                    </c:if>
                                                    <td align="center"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><a href="#"><img src="${pageContext.request.contextPath}/image/print-icon.png" height="18" width="18" title="Print Packing List" onclick="printConsignment('${packingList.packingNo}');"/></a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"><img src="${pageContext.request.contextPath}/image/printicon.png" height="25" width="25" title="Print Warranty Tag" onclick="printWarrantyMaterial('${packingList.packingNo}');"/></a>
                                                            <c:if test="${startFormat ge packingGenDateCheck}">
                                                            &nbsp;&nbsp;&nbsp;&nbsp;<a onclick="printTaxInvoice('${packingList.packingNo}');" href="javascript:void(0);" ><img src="${pageContext.request.contextPath}/image/printChallan.jpg" height="25" width="25"  title="Print Delivery Challan"/></a>
                                                            &nbsp;&nbsp;&nbsp;&nbsp;<a onclick="printWarrantyClaimChallan('${packingList.packingNo}');" href="javascript:void(0);" ><img src="${pageContext.request.contextPath}/image/approve2.jpg"   title="Print Warranty Claim"/></a>
                                                      </td>
                                                      <td align="center"   bgcolor="#FFFFFF" style="FONT-SIZE: 11px; padding: 0 10px;">         
                                                           <c:if test="${packingList.isApproved eq 'Y'}"> 
                                                            <%-- <%if (userFunctionalities.contains("1014")) {%> --%>
                                                            
                                                            <a onclick="collectAndPrintMassConsignment('${packingList.packingNo}');" href="javascript:void(0);" ><img src="${pageContext.request.contextPath}/images/taxInvoice.png" height="18" width="18"    title="Print Tax Invoice"/></a>
                                                            <%-- <%} else {%>
                                                            <%}%>  --%>
                                                         </c:if> 
                                                           </c:if>
                                                   </td>
                                                </tr>
                                            </pg:item>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        <tr>
                                            <td colspan="10" align="center" bgcolor="#FFFFFF" class="textPaging" >
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
                    <c:if test="${empty packingList}">
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
        var status = '${warrantyForm.packingStatus}';
        if(status != ""){
            document.getElementById("Packing Status").value=status;
        }
    }
</script>