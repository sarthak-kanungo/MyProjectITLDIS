<%--
    Document   : misReportDealer
    Created on : Jun 29, 2015, 5:08:38 PM
    Author     : satendra.aditya
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
<script src="${pageContext.request.contextPath}/js/base64.js"></script>
<script>
    $(document).ready(function(){
        var dealerFlag = "${dealerFlag}";
       $('form').submit(function(){

          if($('input[name="fromDate"]').val().trim()== ''){
               alert("Please select From Date.");
               return false;
           }

           if($('input[name="toDate"]').val().trim()== ''){
               alert("Please select To Date.");
               return false;
           }else {
                var fromDate = $('input[name="fromDate"]').val().trim()
                var toDate = $('input[name="toDate"]').val().trim()
                fromDate=fromDate.split("/")[2]+fromDate.split("/")[1]+fromDate.split("/")[0];
                toDate=toDate.split("/")[2]+toDate.split("/")[1]+toDate.split("/")[0];

                if(parseInt(toDate-fromDate)>100){
                     alert("Date Range can't be more than one month.");
                     return false;
                }
            }

             if($('#dealerCode')){
             if($('#dealerCode option:selected').val() == 0){
               alert("Please select dealer.");
               return false;
           }
             }


       });

       $('#dealerCode').change(function(){
           $("input[name='dealerCode']").val($(this).val())
       });
    });

   <%-- $(document).ready(function(){
        $( "#fromDate" ).datepicker("setDate",-30);
        //$( "#fromDate" ).datepicker( "setDate", "10/12/2012" );
    });--%>

</script>
 <div class="contentmain1">
     <div class="breadcrumb_container">
         <ul class="hText">
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/reportAction.do?option=options'><bean:message key="label.report.report" /></a></li>
            <li class="breadcrumb_link"><bean:message key="label.report.gstr2" /></li>
         </ul>
     </div>


 <center>


     <div class="content_right1">
         <div class="con_slidediv2" style="position: relative;width: 100%; padding-bottom: 10px;">
             <h1 class="hText"><bean:message key="label.report.gstr2" /></h1>
              <html:form action="reportAction.do?option=generateGstr2Report" method="post" styleId="searchBy">
                  <html:hidden property="dealerCode"/>
                  <table width=100%  border=0 cellspacing=0 cellpadding=4 align="center">
                                   <tr bgcolor="#FFFFFF" >
                                       <td align="right" width="20%">
                                           Select Period From:
                                       </td>
                                       <td align="left" style="width:100px;">
                                       <input type="text" name="fromDate" class="datepicker txtGeneralNW" id="fromDate" style="width:100px !important " value=""  readonly="readonly"/>
                                       </td>
                                       <td align="left" width="2%" style="text-align:center;">
                                           to
                                       </td>
                                       <td align="left" width="10%">
                                       <input type="text" name="toDate" class="datepicker txtGeneralNW" id="toDate" style="width:100px !important " value=""  readonly="readonly"/>
                                       </td>

                                 <%if (userFunctionalities.contains("101")) {%>
                                 <%} else {%>
                                 <td align="right" width="5%" style="white-space: nowrap;"> <bean:message key="label.common.dealerName" />    </td>
                                     <td align="left" width="20%">
                                         <select name="dealerCode" id="dealerCode" style="width:238px !important">
                                            <%-- <option value='' >--Select Dealer--</option>--%>
                                            <%-- <option value="ALL">--ALL--</option>--%>
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
                                     <%}%>
                                       <td>
                                         <input name="go" type="submit" value="Export" style="float:none;"/>
                                       </td>
                                   </tr>
                             </table>



                                   </html:form>
          </div>
   </div>
 </center>
 </div>