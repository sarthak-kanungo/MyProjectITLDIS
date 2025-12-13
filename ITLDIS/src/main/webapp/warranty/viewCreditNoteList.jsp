<%-- 
    Document   : viewCreditNoteList
    Created on : 04-Feb-2015, 14:12:03
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
<%@ page import="beans.WarrantyForm" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String dealerFlag="";
            if (!userFunctionalities.contains("101")) {dealerFlag="true";}
            List<WarrantyForm> dealerList = (List<WarrantyForm>) request.getAttribute("dealerList1");
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
     function Export()
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

        document.getElementById('eType').value="export";
        document.getElementById('searchBy').submit();
    }
     
     function printMassConsignment() {
    	    var fromDateId = document.getElementById('From Date').value;
    	    var toDateId = document.getElementById('To Date').value;
    	    var dealerName = document.getElementById('Dealer Name').value;

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

    	    // Validate Dealer Name
    	    if (dealerName === undefined || dealerName === '' || dealerName === 'ALL') {
    	        alert('Please Select a Dealer.');
    	        return false;
    	    }

    	    // Rest of your function logic...
    	    var strURL = "${pageContext.request.contextPath}/warrantyAction.do?option=warrantyDetails4MassView&fromDate="+fromDateId +"&toDate="+toDateId +"&dealerCode="+dealerName;
            window.open(strURL, 'iji', 'width=1000,height=950,resizable=yes,scrollbars=yes,status=0,titlebar=0,toolbar=0,addressbar=0');
    	}


     
     
     function isFromDateValid(empFromVal) { // Updated function name
         if (!isDate(empFromVal)) {
             alert('Please Enter Valid From Date.!');
             return false;
         }
         var toDate = document.getElementById('To Date').value; // Updated selector
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
         var fromDate = document.getElementById('From Date').value; // Updated selector
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
         } else if (((endDate - startDate) / (24 * 1000 * 60 * 60)) > 7) { // Limit date range to 15 days
             alert("[To Date] should not be more than 7 days from the [From Date].");
             return false;
         }

         return true;
     }
     
     $(document).ready(function() {
         var fromDateValue = "${fromDate}" || new Date().toLocaleDateString('en-GB'); // Ensure this is in the correct format dd/MM/yyyy
         var toDateValue = "${toDate}" || new Date().toLocaleDateString('en-GB');     // Ensure this is in the correct format dd/MM/yyyy
         
         
         document.getElementById('From Date').value = fromDateValue;
         document.getElementById('To Date').value = toDateValue;
     
     });



</script>


 <div class="contentmain1">
     <div class="breadcrumb_container">
         <ul class="hText">
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.warranty.warranty" /></a></li>
            <li class="breadcrumb_link"><bean:message key="label.common.viewCreditNote" /></li>
         </ul>
     </div>


 <center>


     <div class="content_right1">
         <div class="con_slidediv2" style="position: relative;width: 100%">
             <h1 class="hText"><bean:message key="label.common.viewCreditNote" /></h1>
             <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>
                 <tr height=50 bgcolor="#FFFFFF">
                     <td align= center class="headingSpas">
                           <html:form action="warrantyAction.do?option=viewCreditNote&flag=check" method="post" styleId="searchBy" onsubmit="donotsubmit();">

                               <table width=100%  border=0 cellspacing=0 cellpadding=4 bgcolor=#cccccc>
                                 <tr bgcolor="#FFFFFF" >
                                     <td class="headingSpas" nowrap align="right" width="15%"><bean:message key="label.creditNote.creditNo" /></td>
                                     <td align="left" width="10%"><input name="sapCreditNo" type="text" id="Credit No" value="${warrantyForm.sapCreditNo}" style="width:80px !important " onblur="this.value=TrimAll(this.value)"/></td>
                                     <td class="headingSpas" nowrap align="right" width="10%"><bean:message key="label.common.ClaimNo" /> </td>
                                     <td align="left" width="10%"><input name="warrantyClaimNo" type="text" id="nameSrch" value="${warrantyForm.warrantyClaimNo}" style="width:80px !important " onblur="this.value=TrimAll(this.value)"/></td>
                                     <td class="headingSpas" nowrap align="right" width="10%">
                                         <c:if test="${range eq '1'}">
                                             <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                         </c:if>
                                         <c:if test="${range ne '1' }">
                                             <input type="checkbox" name="range" value="1"  id="Range" >
                                         </c:if>
                                         <bean:message key="label.creditNote.creditDate" />
                                     </td>
                                     <td   align="left" width="5%">
                                         <input type="text" name="fromDate" class="datepicker" id="From Date" style="width:70px !important " class="txtGeneralNW" value=""  readonly="readonly"/></td>
                                     </td>
                                     <td class="headingSpas" nowrap align="left" width="1%"  >
                                         To
                                     </td>
                                     <td   align="left" width="10%">
                                         <input type="text" name="toDate" class="datepicker" id="To Date" style="width:70px !important " class="txtGeneralNW" value=""  readonly="readonly"/>
                                     </td>
                                      <%if (userFunctionalities.contains("101")) {%>
                                     <td  align="left" width="10%">
                                         <input name="go" type="button" value="search" style="float:none; " onClick = "submitForm()"/>
                                     </td>
                                         <td  align="left" width="10%">
                                         <input type="button" value="Export" onclick="Export()"/> <input type="hidden" name="eType" id="eType" />
                                     </td>
                                     <%}%>
                                 </tr>
                                 <%if (userFunctionalities.contains("101")) {%>
                                 <%} else {%> 
                                 <tr bgcolor="#FFFFFF">
                                      
                                     <td align="right" > <bean:message key="label.common.dealerName" />    </td>
                                     <td colspan="4" align="left">
                                         <select id="Dealer Name" name="dealer_code" style="width:300px !important" onchange="updateInput()">
                                             <option value="ALL">ALL</option>
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

                                     <td  align="left" colspan="3" style="padding-top:20px">
                                         <input name="Srch" type="text" id="Srch" value="" style="display: none"/>
                                         <input name="go" type="button" value="search" style="float: left;margin-right: 10px;" onClick = "submitForm()"/>
                                         <input type="button" value="Export" style="float: left;margin-right: 10px;" onclick="Export()"/> <input type="hidden" name="eType" id="eType" />
                                         <%if (userFunctionalities.contains("101")) {%>
                                            <%} else {%> 
                                                 <a href="#"><img src="${pageContext.request.contextPath}/images/taxInvoice.png" height="25" width="25"  style="margin: 0  0 0 0;  title="Print Mass Tax Invoice" style="cursor:pointer; margin-left:10px;" onclick="printMassConsignment();" /></a> 
                                         <%}%>
                                     </td>
                                     <%}%> 
                                 </tr>

                             </table>
                         </html:form>

                 </td>
               </tr>
             </table>
               <c:if test="${!empty panding4SAPList}">
                        
                                <pg:pager url="warrantyAction.do" maxIndexPages="15" maxPageItems="15">
                                    <pg:param name="option" value="viewCreditNote"/>
                                    <pg:param name="warrantyClaimNo"  value='${warrantyForm.warrantyClaimNo}'/>
                                    <pg:param name="dealer_code"  value='${warrantyForm.dealer_code}'/>
                                    <pg:param name="fromDate"  value='${warrantyForm.fromDate}'/>
                                    <pg:param name="toDate"  value='${warrantyForm.toDate}'/>
                                    <pg:param name="flag"  value='check'/>
                                    <c:if test="${range eq '1'}"><pg:param name="range"  value='1'/> </c:if>

                                    <div style="width:99%; overflow:auto">
                                    <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td   align="center"class="headingSpas" width="4%" nowrap style="padding-left:5px; "><b><bean:message key="label.warranty.S.No" /></b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.ClaimNo" /></b></td>
                                           <%-- <td   align="left"  class="headingSpas" nowrap style="padding-left:5px; padding-right: 5px"><b><bean:message key="label.warranty.claimDate" /></b></td>--%>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.Chassisno" /></b></td>
                                            <td   align="left"  class="headingSpas" nowrap style="padding-left:5px; "><b><bean:message key="label.common.packinglistno" /></b></td>
                                            <td   align="left"  class="headingSpas" nowrap style="padding-left:5px; "><b><bean:message key="label.common.sapclaimno" /></b></td>
                                            <%if (userFunctionalities.contains("101")){%>
                                            <%} else {%> 
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.dealerName" /></b></td>
                                             <%}%> 
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.creditNote.claimAmount" /></b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b>Claim <bean:message key="label.common.status" /></b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.warranty.approvedAmmount" /></b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.creditNote.creditStatus" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.creditNote.creditNo" /> </b></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.creditNote.creditDate" /></td>
                                            <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.creditNote.creditAmount" /> </b></td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="dataList" name="panding4SAPList">
                                            <pg:item>
                                                <tr  bgcolor="#eeeeee" height="20">
                                                    <td align="center" bgcolor="#FFFFFF" width="4%" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                   <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.warrantyClaimNo}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span>
                                                            <a href="warrantyAction.do?option=viewWarrantyClaimDetail&warrntyClaimNo=${dataList.warrantyClaimNo}&flag=creditNote">  ${dataList.warrantyClaimNo} </a>
                                                        </span>
                                                    </td>
                                                  <%--  <td align="left" bgcolor="#FFFFFF" title="${dataList.claimDate}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span>
                                                            ${dataList.claimDate}
                                                        </span>
                                                    </td>--%>
                                                   
                                                    <td align="left" bgcolor="#FFFFFF" width="10%" title=" ${dataList.vinNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span >
                                                            ${dataList.vinNo}
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
                                                     <%if (userFunctionalities.contains("101")){%>
                                                    <%} else {%> 
                                                    <td align="left" bgcolor="#FFFFFF" width="10%" title=" ${dataList.dealerName}"  class="headingSpas" style="padding-left:5px; padding-right: 5px; white-space: nowrap;">
                                                        <span>
                                                           ${dataList.dealer_code} [${dataList.dealerName}] [${dataList.location}]
                                                        </span>
                                                    </td>
                                                     <%}%>
                                                    <td align="right" bgcolor="#FFFFFF" width="10%" title=" ${dataList.totalClaimValue}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span>
                                                            ${dataList.totalClaimValue}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" width="10%" title=" ${dataList.claimStatus}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                                                        
                                                            ${dataList.claimStatus}
                                                        
                                                    </td>

                                                    <td align="right" bgcolor="#FFFFFF" width="10%" title=" ${dataList.totalApproveAmmount}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span>
                                                            ${dataList.totalApproveAmmount}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" width="10%" title=" ${dataList.sapSyncStatus}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span>
                                                            ${dataList.sapSyncStatus}
                                                        </span>
                                                    </td>
                                                     <td align="left" bgcolor="#FFFFFF" width="10%" title=" ${dataList.sapCreditNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span>
                                                            ${dataList.sapCreditNo}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" width="10%" title=" ${dataList.sapCreditDate}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span>
                                                            ${dataList.sapCreditDate}
                                                        </span>
                                                    </td>
                                                    <td align="right" bgcolor="#FFFFFF" width="10%" title=" ${dataList.sapCreditAmount}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span>
                                                            ${dataList.sapCreditAmount}
                                                        </span>
                                                    </td>
                                                   
                                                </tr>
                                                
                                            </pg:item>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                                <tr  bgcolor="#eeeeee" height="10">
                                                    <%-- <%if (userFunctionalities.contains("101")){%>
                                                    <td colspan="10"  bgcolor="#FFFFFF"> </td>
                                                     <%} else {%> --%>
                                                      <td colspan="13"  bgcolor="#FFFFFF"> </td>
                                                      <%-- <%}%> --%>
                                                </tr>
                                        <tr>
                                            <%-- <%if (userFunctionalities.contains("101")){%>
                                            <td colspan="10" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                            <%} else {%> --%>
                                            <td colspan="13" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                            <%-- <%}%> --%>
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
                        <c:if test="${empty panding4SAPList}">
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
var dealerList = [];
    <% for (WarrantyForm dealer : dealerList) { %>
    dealerList.push({
        dealerCode : "<%= dealer.getDealer_code() %>",
        dealerName : "<%= dealer.getDealerName() %>",
        location : "<%= dealer.getLocation() %>",
    });
    <% } %>

    function showSuggestions(value) {
        const suggestionsContainer = document.getElementById('suggestions');
        suggestionsContainer.innerHTML = '';

        if (value.length === 0) {
            suggestionsContainer.style.display = 'none';
            return;
        }

        const filteredDealers = dealerList.filter(dealer => dealer.dealerCode.toLowerCase().includes(value.toLowerCase()));

        if (filteredDealers.length === 0) {
            suggestionsContainer.style.display = 'none';
            return;
        }

        // Create suggestion items
        filteredDealers.forEach(dealer => {
            const suggestionItem = document.createElement('div');
            suggestionItem.classList.add('suggestion-item');
            suggestionItem.textContent = dealer.dealerCode + " [" + dealer.dealerName + "] [" + dealer.location + "]";
            suggestionItem.onclick = () => {
                document.getElementById('dealerCode').value = dealer.dealerCode;
                document.getElementById('Dealer Name').value = dealer.dealerCode;
                suggestionsContainer.style.display = 'none';
            };
            suggestionsContainer.appendChild(suggestionItem);
        });

        suggestionsContainer.style.display = 'block';
    }

    document.addEventListener('click', function(event) {
        const suggestionsContainer = document.getElementById('suggestions');
        const inputField = document.getElementById('dealerCode');
        if (!suggestionsContainer.contains(event.target) && event.target !== inputField) {
            suggestionsContainer.style.display = 'none';
        }
    });
    function updateInput() {
        var dealerCode = document.getElementById("Dealer Name").value;
        console.log(dealerCode);
        document.getElementById("dealerCode").value = dealerCode==='ALL'?'':dealerCode;
    }
</script>