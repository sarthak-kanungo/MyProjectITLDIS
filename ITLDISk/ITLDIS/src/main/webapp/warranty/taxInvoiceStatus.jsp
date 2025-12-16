<%-- 
    Document   : pendingForAcknow
    Created on : Dec 10, 2014, 11:27:35 AM
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
        
        if(document.getElementById("Range").checked==false){
        	document.getElementById("To Date").value="";
        	document.getElementById("From Date").value="";
        	document.getElementById('myForm').submit();
        	return true;
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
       document.getElementById("myForm").submit();
      
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
   
   $(document).ready(function() {
       var fromDateValue = "${fromDate}" || new Date().toLocaleDateString('en-GB');  // Ensure this is in the correct format dd/MM/yyyy
       var toDateValue = "${toDate}" || new Date().toLocaleDateString('en-GB');     // Ensure this is in the correct format dd/MM/yyyy
       
       
       document.getElementById('From Date').value = fromDateValue;
       document.getElementById('To Date').value = toDateValue;
       

   
   });
   
</script>


 <div class="contentmain1">
     <div class="breadcrumb_container">
         <ul class="hText">
             <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.warranty.warranty" /></a></li>
            <li class="breadcrumb_link"><bean:message key="label.common.taxInvoiceStatus" /></li>
         </ul>
     </div>
<div class="message" id="msg_saveFAILED" align="center">${message}</div>

 <center>


     <div class="content_right1">
         <div class="con_slidediv2" style="position: relative;width: 100%">
             <h1 class="hText"><bean:message key="label.common.taxInvoiceStatus" /></h1>
             <table  width=100%  border=0 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#cccccc>

                  <tr height=50 bgcolor="#FFFFFF">
                     <td align= center class="headingSpas">
                     <form name="pendingForAcknow" id="myForm" method="post" action="<%=cntxpath%>/warrantyAction.do?option=taxInvoiceStatus&flag=check" onsubmit="return false;">
                    <table width="100%" border="0" align="center" cellpadding="1" cellspacing="1">
                        <tbody >
                            <tr bgcolor="#FFFFFF">
                                <td style="padding-left:5px;width:5% " class="headingSpas" nowrap align="right"><span class="formLabel"><bean:message key="label.common.packingNo" /></span></td>
                                <td style="padding-left:5px;width:10% " align="left">
                                    <input name="packingNo" type="text" id="Packing No" value="${warrantyForm.packingNo}" />
                                </td>
                                <td  style="padding-left:5px;width:5% "  class="headingSpas" nowrap align="right">
                                     <c:if test="${range eq '1'}">
                                        <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                                    </c:if>
                                    <c:if test="${range ne '1' }">
                                        <input type="checkbox" name="range" value="1"  id="Range" >
                                    </c:if>
                                    <bean:message key="label.common.taxInvoiceAcknowledgeDate"/></td>
                                <td style="padding-left:5px;width:5%" align="left">
                                    <input name="fromDate" type="text" class="datepicker" id="From Date" style="width:60px"  value="" onkeydown="return false;"/>
                                </td>
                                <td  style="padding-left:5px;width:2% "  class="headingSpas" nowrap align="center"><bean:message key="label.common.to"/></td>
                                <td style="padding-left:5px;width:5%" align="left">
                                   <input name="toDate" type="text" class="datepicker" id="To Date" value="" style="width:60px" onkeydown="return false;"/>
                                </td>
           
                               <%if (userFunctionalities.contains("101")) {%>
                                <%} else{%>
                                <td width="15%">
                                    <select id="Dealer Name" name="dealer_code" style="width:300px !important">
                                        <option value="ALL" >ALL</option>
                                       <option value='' >--Select Dealer--</option>
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
                                
                                <td  style="padding-left:5px;width:5% "  class="headingSpas" nowrap align="center"><bean:message key="label.common.status"/></td>
                                            <td align="left" >
                                                <select name="taxInvoiceStatus" id="taxInvoiceStatus" class="selectnewbox txtGeneralNW">
                                                <option value="" <c:if test="${empty warrantyForm.taxInvoiceStatus}">selected</c:if>>All</option>
                                                <option value="SUBMITTED" <c:if test="${warrantyForm.taxInvoiceStatus == 'SUBMITTED'}">selected</c:if>>SUBMITTED</option>
                                                <option value="RECEIVED" <c:if test="${warrantyForm.taxInvoiceStatus == 'RECEIVED'}">selected</c:if>>RECEIVED</option>
                                                <option value="NOT APPROVED" <c:if test="${warrantyForm.taxInvoiceStatus == 'NOT APPROVED'}">selected</c:if>>NOT APPROVED</option>
                                                <option value="PARTIALLY RECEIVED" <c:if test="${warrantyForm.taxInvoiceStatus == 'PARTIALLY RECEIVED'}">selected</c:if>>PARTIALLY RECEIVED</option>
                                                </select>
        
                                 </td> 
                                
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
                                    <pg:param name="option" value="taxInvoiceStatus"/>
                                    <pg:param name="Srch"  value='<%=request.getParameter("Srch")%>'/>
                                    <pg:param name="fromDate"  value='<%=request.getParameter("fromDate")%>'/>
                                    <pg:param name="toDate"  value='<%=request.getParameter("toDate")%>'/>
                                    <pg:param name="dealer_code"  value='${warrantyForm.dealer_code}'/>
                                    <pg:param name="taxInvoiceStatus"  value='${warrantyForm.taxInvoiceStatus}'/>
                                    <pg:param name="flag"  value='check'/>
                                    <c:if test="${range eq '1'}"><pg:param name="range"  value='1'/> </c:if>
                                    <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td nowrap align="center" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.sno" />
                                            </td>
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.packingNo" />
                                            </td>
                                            
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                <bean:message key="label.common.consignmentNo" />
                                            </td>
                                            
                                            <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                               <bean:message key="label.common.taxInvoiceStatus" />
                                            </td>
                                          

                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="packingList" name="packingList">
                                            <pg:item>
                                                <tr  bgcolor="#eeeeee" height="20">
                                                    <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                    <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                       
                                                        <span >${packingList.packingNo}</span>
                                                    </td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${packingList.courierNo} [${packingList.courierName}]</span></td>

                                                     
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                        <span >${packingList.taxInvoiceStatus}</span>
                                                    </td>
                                                    
                                                 

                                                </tr>
                                            </pg:item>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        <tr>
                                            <%if (userFunctionalities.contains("710")) {%>
                                            <td colspan="8" align="center" bgcolor="#FFFFFF" class="textPaging" >
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

