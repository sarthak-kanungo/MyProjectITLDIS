<%-- 
    Document   : pending4SAPCreaditNote
    Created on : Dec 15, 2014, 3:10:30 PM
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

<%@ page import="java.util.*" %>
<%@ page import="beans.WarrantyForm" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String dealerFlag = "";
            if (!userFunctionalities.contains("101")) {dealerFlag = "true";}
%>
<style>
.suggestions {
    border: 1px solid #ccc;
    border-radius: 7px;
    border-top: none;
    max-height: 150px;
    overflow-y: auto;
    position: absolute;
    z-index: 1000;
    background-color: #fff;
    display: inline-block;
    min-width: max-content;
}

.suggestion-item {
    padding: 3px;
    cursor: pointer;
    white-space: nowrap;
    overflow: hidden;
    text-overflow: ellipsis;
    display: block;
}

.suggestion-item:hover {
    background-color: #458AE8;
}
</style>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_6.js"></script>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';

    function submitForm()
    {
    	
        var dealerFlag='<%=dealerFlag%>';
        if(dealerFlag=='true')
        {
            var dealer=document.getElementById('dealerName').value;
            if(dealer=='')
            {
                alert(not_blank_dropdown_validation_msg+'Dealer Name');
                return false;
            }
        }
        document.getElementById("pendingSAPList").submit();
    }
    function disableDate(dis)
    {
        if(dis.checked){
            document.getElementById("fromDate").disabled=false;
            document.getElementById("toDate").disabled=false;
            document.getElementById('Range').value = '1';
        }else{
            document.getElementById("fromDate").value="";
            document.getElementById("fromDate").disabled=true;
            document.getElementById("toDate").value="";
            document.getElementById("toDate").disabled=true;
            document.getElementById('Range').value = '0';
        }
    }

    function printWarInvoice(claimNo)
    {
        var strURL = "${pageContext.request.contextPath}/warrantyAction.do?option=printWarrantyInvoice&claimNo="+claimNo.trim();
        window.open(strURL,'taxInv','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
    }
    
    function printMassConsignment() {
        var fromDateId = $('#fromDate').val();
        var toDateId = $('#toDate').val();
        var dealerCode = $('#dealerName').val();

        // Validate From Date
        if (fromDateId === undefined || fromDateId === '') {
            alert('Please Select From Date.');
            return false;
        } else {
            if (!isFromDateValid(fromDateId)) {
                return false;
            }
        }

        // Validate To Date
        if (toDateId === undefined || toDateId === '') {
            alert('Please Select To Date.');
            return false;
        } else {
            if (!isToDateValid(toDateId)) {
                return false;
            }
        }
        
        if (dealerCode === undefined || dealerCode === '' || dealerCode === 'ALL') {
            alert('Please Select a Dealer.');
            return false;
        }

         var strURL = "${pageContext.request.contextPath}/warrantyAction.do?option=warrantyDetails4MassView&fromDate="+fromDateId +"&toDate="+toDateId +"&dealerCode="+dealerCode;
        window.open(strURL, 'iji', 'width=1000,height=950,resizable=yes,scrollbars=yes,status=0,titlebar=0,toolbar=0,addressbar=0');
    }

    function isFromDateValid(empFromVal) { // Updated function name
        if (!isDate(empFromVal)) {
            alert('Please Enter Valid From Date.!');
            return false;
        }
        var toDate = $('#toDate').val(); // Updated selector
        if (!compareSearchDates(empFromVal, toDate)) {
            return false;
        }
        return true;
    }

    function isToDateValid(empToVal) { // Updated function name
        if (!isDate(empToVal)) {
            alert('Please Enter Valid To Date.!');
            return false;
        }
        var fromDate = $('#fromDate').val(); // Updated selector
        if (!compareSearchDates(fromDate, empToVal)) {
            return false;
        }
        return true;
    }

    function isDate(txtDate) {
        var reg = /^(0[1-9]|[12][0-9]|3[01])([\/-])(0[1-9]|1[012])\2(\d{4})$/;
        return reg.test(txtDate);
    }

    function compareSearchDates(stDate, enDate) {
        if (stDate === '' || stDate === undefined || enDate === '' || enDate === undefined) {
            return false;
        }

        // Get the current date
        var today = new Date();
        var currentDate = today.getDate() + '/' + (today.getMonth() + 1) + '/' + today.getFullYear();

        // Convert input date strings to Date objects
        var regs = stDate.split('/');
        var startDate = new Date(regs[2], regs[1] - 1, regs[0]);

        regs = enDate.split('/');
        var endDate = new Date(regs[2], regs[1] - 1, regs[0]);

        // Convert current date string to Date object
        regs = currentDate.split('/');
        var cDate = new Date(regs[2], regs[1] - 1, regs[0]);

        // Validation logic
        if (startDate > endDate) {
            alert("[From Date] is greater than [To Date].");
            return false;
        } else if (((endDate - startDate) / (24 * 1000 * 60 * 60)) > 7) { 
            alert("[To Date] should not be more than 7 days from the [From Date].");
            return false;
        }

        return true;
    }
    
    function Export(){
    	
   	 if(document.getElementById("Range").checked==false){
   		 alert("Please Select Date Range");
   		 return false;
   	 }

           if(document.getElementById("Range").checked==true){
               if(document.getElementById("fromDate").value==""){
                   alert("Please Select From Date.");
                   document.getElementById("fromDate").focus();
                   return false;
               }
               if(document.getElementById("toDate").value==""){
                   alert("Please Select To Date.");
                   document.getElementById("toDate").focus();
                   return false;
               }
           }
           var  fDate=document.getElementById("fromDate").value;
           fDate = trim(fDate);
           var x =fDate;     //'1-1-2015';
           var a = x.split('/');
           var dateF = new Date (a[2], a[1] - 1,a[0]);

           var  tDate=document.getElementById("toDate").value;
           tDate = trim(tDate);
           var y =tDate;     //'17-1-2015';
           var b = y.split('/');
           var dateT = new Date (b[2], b[1] - 1,b[0]);

           if(dateF>dateT){
               alert(date_validation_msg)
               document.getElementById('fromDate').focus();
               return false;
           }
           
           var dealerCode  = document.getElementById("dealerName").value;
          
           var claimNo  = document.getElementById("WarrantyClaim No").value;
           document.getElementById('etype').value="export";
           document.getElementById("pendingSAPList").submit();
   //        var strURL = "${pageContext.request.contextPath}/warrantyAction.do?option=exportPendingSapCreditNoteGeneration&fromDate="+fDate +"&toDate="+tDate +"&dealerCode="+dealerCode+"&claimNo="+claimNo+"&export=true";
	//       window.open(strURL, 'iji', 'width=1000,height=950,resizable=yes,scrollbars=yes,status=0,titlebar=0,toolbar=0,addressbar=0');
       }
    
    $(document).ready(function() {
    	 var fromDateValue = "${fromDate}" || new Date().toLocaleDateString('en-GB'); // Format as dd/MM/yyyy
    	 var toDateValue = "${toDate}" || new Date().toLocaleDateString('en-GB');     // Format as dd/MM/yyyy


        // Initialize the datepicker for fromDate
        $("#fromDate").datepicker({
            dateFormat: "dd/mm/yy"
        }).datepicker('setDate', fromDateValue);

        // Initialize the datepicker for toDate
        $("#toDate").datepicker({
            dateFormat: "dd/mm/yy"
        }).datepicker('setDate', toDateValue);
    });





   
		
		
</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.warranty.warranty" /></a></li>
            <li class="breadcrumb_link"><bean:message key="label.common.pendingSAPgeneration" /></li>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED" align="center">${message}</div>

    <center>


        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.common.pendingSAPgeneration" /></h1>
                <table  width=100%  border=0 cellspacing=0 cellpadding=5 bordercolor=#cccccc bgcolor=#cccccc>
                    <tr height=50 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <form name="pendingSAPList" id="pendingSAPList" method="post" action="<%=cntxpath%>/warrantyAction.do?option=pendingSAPList" onsubmit="return false;">
                                <input type="hidden" value="view" name="flag"/>
                                <table width="100%" border="0" align="center" cellpadding="1" cellspacing="1">
                                    <tbody >
                                        <tr bgcolor="#FFFFFF">
                                            <td style="padding-left:5px;width:5% " class="headingSpas" nowrap align="right"><span class="formLabel"><bean:message key="label.common.ClaimNo" /></span></td>
                                            <td style="padding-left:5px;width:10% " align="left">
                                                <input name="warrantyClaimNo" type="text" id="WarrantyClaim No" value="${warrantyForm.warrantyClaimNo}" />
                                            </td>
                                            
                                            
                                            <%if (userFunctionalities.contains("101")) {%>
                                            <%} else {%> 
                                            <td align="right" > <bean:message key="label.common.dealerName" />    </td>
                                            <td width="15%">
                                                <select id="dealerName" name="dealer_code" style="width:200px !important">
                                                    <option value='ALL' >ALL</option>
                                                    <%--<option value='' >--Select Dealer--</option>--%>
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
                                            <td class="headingSpas" nowrap align="right">
                                            <c:if test="${range eq '1'}">
                                                <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                            </c:if>
                                            <c:if test="${range ne '1' }">
                                                <input type="checkbox" name="range" value="0"  id="Range" onclick="disableDate(this)">
                                            </c:if>
                                            <bean:message key="label.common.taxInvoiceAcknowledgeDate" />
	                                        </td>
	                                        <td align="left">
	                                            <input type="text" name="fromDate" class="datepicker" id="fromDate" style="width:80px !important " class="txtGeneralNW" value=""  readonly="readonly"/>
	                                        </td>
	                                        <td    class="headingSpas" nowrap align="right">
	                                            To
	                                        </td>
	                                        <td   align="left">
	                                            <input type="text" name="toDate" class="datepicker" id="toDate" style="width:80px !important " class="txtGeneralNW" value=""  readonly="readonly"/>
	                                        </td>
                                            <td style="padding-left:5px" align="left"  >
                                                <input name="go" type="submit" id="go" style="float:left" class="headingSpas1" value="search" onclick="submitForm()"/>
                                                <%if (userFunctionalities.contains("101")) {%>
                                                 <%} else {%> 
                                                <input name="go" type="submit" id="go" style="float:left;margin-left:30px" class="headingSpas1" value="EXPORT" onclick="Export()"/>
                                                <input type="hidden" name="etype" id="etype" />
                                                 <%}%>
                                                 
                                                <%if (userFunctionalities.contains("101")) {%>
                                            <%} else {%> 
                                                 <a href="#"><img src="${pageContext.request.contextPath}/images/taxInvoice.png" height="25" width="25" title="Print Mass Tax Invoice" style="cursor:pointer; margin-left:20px;" onclick="printMassConsignment();" /></a> 
                                                <%}%>
                                            

                                            </td>
                                          
                                        </tr>
                                    </tbody>
                                </table>
                            </form>
                        </td>
                    </tr>
                    <c:if test="${!empty panding4SAPList}">
                        <div style="overflow: auto">
                            <table align="center" cellspacing=1 cellpadding=5 border=0 width=100% >
                                <tr height=25 bgcolor="#FFFFFF">
                                    <td align= center class="headingSpas">
                                        <pg:pager url="warrantyAction.do" maxIndexPages="10" maxPageItems="15">
                                            <pg:param name="option" value="pendingSAPList"/>
                                            <pg:param name="warrantyClaimNo"  value='${warrantyForm.warrantyClaimNo}'/>
                                            <pg:param name="dealer_code"  value='${warrantyForm.dealer_code}'/>
                                            <pg:param name="fromDate"  value='<%=request.getParameter("fromDate")%>'/>
                                            <pg:param name="toDate"  value='<%=request.getParameter("toDate")%>'/>
                                            <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                                <tr bgcolor="#eeeeee" height="20">
                                                    <td   align="center"class="headingSpas" nowrap ><b><bean:message key="label.warranty.S.No" /></b></td>
                                                    <td   align="left"  class="headingSpas" nowrap ><b> <bean:message key="label.common.ClaimNo" /></b></td>
                                                    <td   align="left"  class="headingSpas" nowrap ><b><bean:message key="label.warranty.claimDate" /></b></td>
                                                    <td   align="left"  class="headingSpas" nowrap ><b><bean:message key="label.common.jobcardno" /> / <bean:message key="label.warranty.TMSRefNo" /></b></td>
                                                    
                                                    <td   align="left"  class="headingSpas" nowrap ><b><bean:message key="label.common.packinglistno" /></b></td>
                                                    <td   align="left"  class="headingSpas" nowrap ><b><bean:message key="label.common.sapclaimno" /></b></td>
                                                    
                                                    <td   align="left"  class="headingSpas" nowrap ><b><bean:message key="label.common.Chassisno" /></b></td>
                                                    <%if (userFunctionalities.contains("101")) {%>
                                                    <%} else {%>
                                                    <td   align="left"  class="headingSpas" nowrap ><b><bean:message key="label.common.dealerName" /></b></td>
                                                    <%}%> 
                                                    <td   align="left"  class="headingSpas" nowrap ><b><bean:message key="label.common.status" /></b></td>
                                                </tr>
                                                <c:set var="sno" value="1"></c:set>
                                                <logic:iterate id="dataList" name="panding4SAPList">
                                                    <pg:item>
                                                        <tr  bgcolor="#eeeeee" height="20">
                                                            <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                            <td align="left" bgcolor="#FFFFFF" title="  ${dataList.warrantyClaimNo}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                                <span>
                                                                    <a href="warrantyAction.do?option=viewWarrantyClaimDetail&warrntyClaimNo=${dataList.warrantyClaimNo}&flag=pendSAP">  ${dataList.warrantyClaimNo} </a>
                                                                    <c:if test="${dataList.claimStatus eq 'APPROVED' or dataList.claimStatus eq 'PARTIAL APPROVED'}">
                                                                        <a onclick="printWarInvoice('${dataList.warrantyClaimNo}');" href="javascript:void(0);" ><img src="${pageContext.request.contextPath}/images/taxInvoice.png" height="15px" width="15px"  title="Warranty Claim Invoice Report"/>
                                                                        </a>
                                                                    </c:if>
                                                                </span>
                                                            </td>
                                                            <td align="left" bgcolor="#FFFFFF" title="${dataList.claimDate}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                                <span>
                                                                    ${dataList.claimDate}
                                                                </span>
                                                            </td>
                                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.jobCardNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                                <span>
                                                                    ${dataList.jobCardNo}
                                                                </span>
                                                            </td>
                                                            
                                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.packingNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                                <span>
                                                                    ${dataList.packingNo}
                                                                </span>
                                                            </td>
                                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.sapWarrantyClaimNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                                <span>
                                                                    ${dataList.sapWarrantyClaimNo}
                                                                </span>
                                                            </td>
                                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.vinNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                                <span >
                                                                    ${dataList.vinNo}
                                                                </span>
                                                            </td>
                                                            <%if (userFunctionalities.contains("101")) {%>
                                                            <%} else {%> 
                                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.dealerName}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                                <span>
                                                                    ${dataList.dealer_code} [${dataList.dealerName}] [${dataList.location}]
                                                                </span>
                                                            </td>
                                                             <%}%> 
                                                            <td align="left" bgcolor="#FFFFFF" title=" ${dataList.claimStatus}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                                <span>
                                                                    ${dataList.claimStatus}
                                                                </span>
                                                            </td>
                                                        </tr>
                                                    </pg:item>
                                                    <c:set var="sno" value="${sno + 1 }" />
                                                </logic:iterate>
                                                <tr>
                                                    <%-- <%if (userFunctionalities.contains("101")) {%>
                                                    <td colspan="6" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                        <%} else {%> --%>
                                                    <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                       <%--  <%}%> --%>
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
                            <c:if test="${empty panding4SAPList}">
                                <tr  class="headingSpas" bgcolor="#FFFFFF" height="20">
                                    <td valign="bottom" align="center" style="padding-top:10px;color: red"><bean:message key="label.common.noRecordFound" /></td>
                                </tr>
                            </c:if>
                        </table>
                    </div>
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