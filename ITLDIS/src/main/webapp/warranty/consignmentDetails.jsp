<%-- 
    Document   : consignmentDetails
    Created on : Dec 8, 2014, 4:26:04 PM
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

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<script>
function makeEnable(val){
        if(val=='By Hand'){
            document.getElementById("Courier No").disabled=true;
            document.getElementById("Courier Name").disabled=true;
            document.getElementById("Courier Copy File").disabled=true;
            document.getElementById("lblCourier No").disabled=true;
            document.getElementById("lblCourier Name").disabled=true;
            document.getElementById("lblCourier Copy File").disabled=true;

        }
        else{
            document.getElementById("Courier No").disabled=false;
            document.getElementById("Courier Name").disabled=false;
            document.getElementById("Courier Copy File").disabled=false;
            document.getElementById("lblCourier No").disabled=false;
            document.getElementById("lblCourier Name").disabled=false;
            document.getElementById("lblCourier Copy File").disabled=false;
        }
    }
    function ValidateForm()
    {
        if (document.getElementById('Dispatched For').value=="" ){
            document.getElementById("Dispatched For").focus();
            alert(not_blank_dropdown_validation_msg+'Dispatched For');
            return false;
        }
        if (document.getElementById('Dispatched By').value=="" ){
            document.getElementById("Dispatched By").focus();
            alert(not_blank_dropdown_validation_msg+'Dispatched By');
            return false;
        }
        if (document.getElementById('Dispatch Date').value=="" ){
            document.getElementById("Dispatch Date").focus();
            alert(dispatch_date_validation_msg);
            return false;
        }
        dateValue=document.getElementById('Dispatch Date').value;
        var packdt='${warrantyForm.packingDate}';
        dateValue = trim(dateValue);
        var x =dateValue;
        var a = x.split('/');
        var packngdt=packdt.split('/');
        packdt=packngdt[2]+'/'+packngdt[1]+'/'+packngdt[0];
        var packengDate=new Date(packdt);
        var date = new Date (a[2], a[1] - 1,a[0]);//using a[1]-1 since Date object has month from 0-11
        var Today = new Date();
        Today.setDate(Today.getDate());

        if ( date < packengDate )
        {
            document.getElementById('Dispatch Date').focus();
            alert("Dishpatch Date "+greater_than_val_msg+"Packing Date");

            return false;
        }

        if ( date > Today )
        {
            document.getElementById('Dispatch Date').focus();
            alert(dispatch_date_check_validation_msg);
            return false;
        }

        // alert(document.getElementById('Dispatched By').value)
        if(document.getElementById('Dispatched By').value !='By Hand'){
            if (document.getElementById('Courier No').value=="" ){
                document.getElementById("Courier No").focus();
                alert(not_blank_validation_msg +'Courier No');
                return false;
            }
            if (!trim(document.getElementById('Courier No').value).match(/^[a-z A-Z0-9/._-]+$/)){
                document.getElementById("Courier No").focus();
                document.getElementById('Courier No').value="";
                alert(specialChar_validation_msg +'Courier No Field');
                return false;
            }
            if (document.getElementById('Courier Name').value=="" ){
                document.getElementById("Courier Name").focus();
                alert(not_blank_validation_msg +'Courier Name');
                return false;
            }
            if (!trim(document.getElementById('Courier Name').value).match(/^[a-z A-Z0-9]+$/)){
                document.getElementById("Courier Name").focus();
                document.getElementById('Courier Name').value="";
                document.getElementById("msg_saveFAILED").style.display = "";
                // document.getElementById("msg_saveFAILED").innerHTML="Special Characters Not allowed in Transporter / Courier Name Field";
                alert(specialChar_validation_msg + 'Courier Name Field');
                return false;
            }
            if (document.getElementById('Courier Copy File').value=="" ){
                document.getElementById("Courier Copy File").focus();
                alert(courier_copy_validation_msg);
                return false;
            }
            if (document.getElementById('Store No').value=="" ){
                document.getElementById("Store No").focus();
                alert(not_blank_validation_msg +'Store No');
                return false;
            }
            if (!trim(document.getElementById('Store No').value).match(/^[0-9]+$/)){
                document.getElementById("Store No").focus();
                document.getElementById('Store No').value="";
                alert(numeric_val_validation_msg +'Store No Field');
                return false;
            }
        }
        document.getElementById("consignmentForm").submit();
    }
</script>
<html lang="en">
    <head>  <meta charset="utf-8">
        <title>DIS</title>
        <script src="JS/Master_290414.js"></script>
        <link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >
    </head>
    <body>
  <div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.warranty.warranty" /></a></li>
            <li class="breadcrumb_link"><a href ='<%=cntxpath%>/warrantyAction.do?option=initPackedWarrantyList'><bean:message key="label.common.pending4Dispatch" /></a></li>
            <li class="breadcrumb_link"><label><bean:message key="label.common.consignmentDetails" /></label></li>
      </ul>
    </div>
    <div class="message" id="msg_saveFAILED"></div>
    <center>
         <div class="content_right1">
             <div class="con_slidediv1" style="position: relative;width: 100%">
                 <h1 class="hText"> <bean:message key="label.common.consignmentDetails" /></h1>
                 <form action="<%=cntxpath%>/warrantyAction.do?option=manageconsignment" method="post" id="consignmentForm" name="consignmentForm" enctype="multipart/form-data" onsubmit="return false;">
                  <%--<input type="hidden" name="option" value="manageconsignment"/>--%>
                  <input type="hidden" name="upperLink" value="<a href = '<%=cntxpath%>/vinitWarranty.do'><bean:message key="label.warranty.warranty" /></a>@@<a href ='<%=cntxpath%>/warrantyAction.do?option=initPackedWarrantyList'><bean:message key="label.common.pending4Dispatch" /></a>@@<bean:message key="label.common.consignmentDetails" />"/>
                 <table width="100%" border=0 cellspacing=1  cellpadding=3 bordercolor=#cccccc bgcolor="#cccccc" >
                     <tr bgcolor="#FFFFFF" height="30" >
                         <td align="right" ><label><B><bean:message key="label.common.packingNo" /></B></label></td>
                         <td align="left" ><span>${warrantyForm.packingNo}</span><input type="hidden" name="packingNo" value="${warrantyForm.packingNo}"/></td>
                         <td align="right" ><label><B><bean:message key="label.common.packingDate" /></B></label></td>
                         <td align="left" colspan="3" ><span>${warrantyForm.packingDate}</span><input type="hidden" name="packingDate" value="${warrantyForm.packingDate}"/></td>
                      </tr>
                      <tr bgcolor="#FFFFFF" height="30" >
                          <td align="right" ><label><B><bean:message key="label.common.listofClaimNo"/></B></label></td>
                          <td align="left" colspan="5">${warrantyForm.warrantyClaimNo}<input type="hidden" name="warrantyClaimNo" value="${warrantyForm.warrantyClaimNo}"/></td>
                      </tr>
                     <tr bgcolor="#FFFFFF" >
                         <td align="right" ><label><B><bean:message key="label.common.dispatchedFor" /></B></label></td>
                         <td align="left">
                             <select name="dispatchFor" id="Dispatched For" class="selectnewbox" class="txtGeneralNW">
                                 <option value="">--Select--</option>
                                 <option value="CFT">CFT </option>
                                 <option value="Warranty">Warranty</option>
                             </select>
                         </td>
                         <td align="right" ><label><B><bean:message key="label.common.dispatchedThrough" /></B></label></td>
                         <td align="left"><select name="dispatchBy" id="Dispatched By" class="selectnewbox" onchange="makeEnable(this.value)">
                                 <option value="">--Select--</option>
                                 <option value="By Hand"> By Hand </option>
                                 <option value="Courier">Courier</option>
                                 <option value="GR">GR</option>
                                 <option value="RR">RR</option>
                             </select></td>
                         <td align="right" ><label><B><bean:message key="label.common.dispatchedDate" /></B></label></td>
                         <td align="left" ><span>
                          <input type="text" name="dispatchDate" id="Dispatch Date" readOnly="readonly" class="datepicker" value="" onkeydown="return false;"/></span></td>
                      </tr>
                      <tr bgcolor="#FFFFFF">
                         <td align="right" ><label id="lblCourier No"><B><bean:message key="label.common.courierNo" /></B></label></td>
                         <td align="left" ><span><input type="text" name="courierNo" id="Courier No" maxlength="45"/></span></td>
                         <td align="right" ><label id="lblCourier Name"><B><bean:message key="label.common.courierName" /></B></label></td>
                         <td align="left" ><span><input type="text" name="courierName" id="Courier Name" maxlength="85"/></span></td>
                         <td align="right" ><label id="lblCourier Copy File"><B><bean:message key="label.common.courierCopyFile" /></B></label></td>
                         <td align="left" ><span><input type="file" name="courierCopy" id="Courier Copy File"/></span></td>
                     </tr>
                      <tr bgcolor="#FFFFFF">
                         <td align="right" ><label ><B><bean:message key="label.common.storeNo" /></B></label></td>
                         <td align="left" ><span><input type="text" name="storeNoOfPackages" id="Store No" maxlength="45"/></span></td>
                         <td align="left" colspan="4" >&nbsp;</td>
                     </tr>
                     <tr bgcolor="#FFFFFF">
                         <td align="center" colspan="6" ><span><input type="button" class="headingSpas1" name="consignmentSubmit"  value="Submit" onclick="ValidateForm();"/></span></td>
                     </tr>
                 </table>
                 </form>
             </div>
         </div>
    </center>
  </div>
</body>
</html>