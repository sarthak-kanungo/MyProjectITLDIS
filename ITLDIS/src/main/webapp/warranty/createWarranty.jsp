<%--
    Document   : createWarranty
    Created on : Sep 19, 2014, 10:27:34 AM
    Author     : kundan.rastogi
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>
<script>

    function makeEnable(val){

      
        if(val=='By Hand'){
            document.getElementById("GR Courier No.").disabled=true;
            document.getElementById("GR Courier No.Label").disabled=true;
            document.getElementById("Transporter/Courier Name").disabled=true;
            document.getElementById("Browse GR/Courier/RR copy").disabled=true;
            document.getElementById("Transporter/Courier NameLabel").disabled=true;
            document.getElementById("Browse GR/Courier/RR copyLabel").disabled=true;
          
        }
        else{
            document.getElementById("GR Courier No.").disabled=false;
            document.getElementById("Transporter/Courier Name").disabled=false;
            document.getElementById("Browse GR/Courier/RR copy").disabled=false;
            document.getElementById("GR Courier No.Label").disabled=false;
            document.getElementById("Transporter/Courier NameLabel").disabled=false;
            document.getElementById("Browse GR/Courier/RR copyLabel").disabled=false;
        }
    }
    <%--function calcGstOnIns(val){


        if(val==''){
            document.getElementById("lessAmmount3").value ="0";
        }
        var totalAmmount = document.getElementById("TotalClaimValue").value ;
        var lessAmmount= document.getElementById("lessAmmount1").value;
        var lessAmmount1= document.getElementById("lessAmmount2").value;
        var insurCharge= document.getElementById("lessAmmount3").value ;
        var GstInsurpercent=document.getElementById("Discount4").value ;
        var GstOnInsurCharge=((insurCharge * GstInsurpercent )/100);
        document.getElementById("lessAmmount4").value =GstOnInsurCharge.toFixed(2);
        //var totalAfterLess=parseFloat(document.getElementById("TotalClaimValue").value)+parseFloat(lessAmmount)+parseFloat(lessAmmount1)+parseFloat(insurCharge)+parseFloat(GstOnInsurCharge);
        document.getElementById("TotalClaimValue").value =totalAmmount.toFixed(2);
    }--%>


    function validateWarranty(){

    	var failedParts = ['FailedPart1', 'FailedPart2', 'FailedPart3', 'FailedPart4', 'FailedPart5'];
    	var isAnyFieldFilled = false;
    	var valuesSet = new Set();

    	for (var i = 0; i < failedParts.length; i++) {
    	    var field = document.getElementById(failedParts[i]);
    	    var fieldValue = field.value.trim();
    	    
    	    if (fieldValue !== "") {
    	        if (valuesSet.has(fieldValue)) {
    	            alert('Duplicate value found: ' + fieldValue);
    	            field.value = '';
    	            field.focus();
    	            return false;
    	        }
    	        
    	        isAnyFieldFilled = true;
    	        valuesSet.add(fieldValue);
    	    }
    	}

    	if (!isAnyFieldFilled) {
    	    document.getElementById("msg_saveFAILED").style.display = "";
    	    alert(failed_part_validation_msg);
    	    document.getElementById(failedParts[0]).focus(); // Focus on the first field as an example
    	    return false;
    	}

    <%-- if (document.getElementById('Dispatched For').value=="" ){
         document.getElementById("Dispatched For").focus();
         document.getElementById("msg_saveFAILED").style.display = "";
         //document.getElementById("msg_saveFAILED").innerHTML="Please Select Value in Dispatched For Field";
         alert(not_blank_dropdown_validation_msg+'Dispatched For');
         return false;
     }
     if (document.getElementById('Dispatched By').value=="" ){
         document.getElementById("Dispatched By").focus();
         document.getElementById("msg_saveFAILED").style.display = "";
         //document.getElementById("msg_saveFAILED").innerHTML="Please Select Value in Dispatched By Field";
         alert(not_blank_dropdown_validation_msg+'Dispatched By');
         return false;
     }
     if (document.getElementById('Date').value=="" ){
         document.getElementById("Date").focus();
         document.getElementById("msg_saveFAILED").style.display = "";
         //document.getElementById("msg_saveFAILED").innerHTML="Please Enter Date in Date Field";
         alert(dispatch_date_validation_msg);
         return false;
     }
     dateValue=document.getElementById('Date').value;
        
     dateValue = trim(dateValue);
     var x =dateValue;
     var a = x.split('/');
     var date = new Date (a[2], a[1] - 1,a[0]);//using a[1]-1 since Date object has month from 0-11
     var Today = new Date();
     Today.setDate(Today.getDate());
       
        if ( date > Today )
         {
             document.getElementById('Date').focus();
            alert(dispatch_date_check_validation_msg);
             return false;
         }

       // alert(document.getElementById('Dispatched By').value)
        if(document.getElementById('Dispatched By').value !='By Hand'){
            if (document.getElementById('GR Courier No.').value=="" ){
                document.getElementById("GR Courier No.").focus();
                document.getElementById("msg_saveFAILED").style.display = "";
               // document.getElementById("msg_saveFAILED").innerHTML="Please Enter Value in GR / Courier No. Field";
               alert(not_blank_validation_msg +'GR / Courier No.');
                return false;
            }
             if (!trim(document.getElementById('GR Courier No.').value).match(/^[a-z A-Z0-9/._-]+$/)){
                document.getElementById("GR Courier No.").focus();
                document.getElementById('GR Courier No.').value="";
                document.getElementById("msg_saveFAILED").style.display = "";
               // document.getElementById("msg_saveFAILED").innerHTML="Special Characters Not allowed in GR / Courier No. Field";
                alert(specialChar_validation_msg +'GR / Courier No. Field');
                return false;
            }
            if (document.getElementById('Transporter/Courier Name').value=="" ){
                document.getElementById("Transporter/Courier Name").focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                //document.getElementById("msg_saveFAILED").innerHTML="Please Enter Value in Transporter / Courier Name Field";
                alert(not_blank_validation_msg +' Transporter / Courier Name');
                return false;
            }
            if (!trim(document.getElementById('Transporter/Courier Name').value).match(/^[a-z A-Z0-9]+$/)){
                document.getElementById("Transporter/Courier Name").focus();
                document.getElementById('Transporter/Courier Name').value="";
                document.getElementById("msg_saveFAILED").style.display = "";
               // document.getElementById("msg_saveFAILED").innerHTML="Special Characters Not allowed in Transporter / Courier Name Field";
                alert(specialChar_validation_msg + 'Transporter / Courier Name Field');
                return false;
            }
            if (document.getElementById('Browse GR/Courier/RR copy').value=="" ){
                document.getElementById("Browse GR/Courier/RR copy").focus();
                document.getElementById("msg_saveFAILED").style.display = "";
               // document.getElementById("msg_saveFAILED").innerHTML="Please Attach Copy of GR / Courier / RR Copy Field";
                alert(courier_copy_validation_msg);
                return false;
            }
            }--%>

                    var Count=document.getElementById("Count").value;
                    for( var i=1;i<=Count;i++){
                        if(document.getElementById("Vender Code"+i).value==""){
                            document.getElementById("Vender Code"+i).focus();
                            alert(not_blank_validation_msg +" Vendor Code");
                            return false;
                        }
                        if(!trim(document.getElementById("Vender Code"+i).value).match(/^[a-z A-Z0-9/\\._-]+$/)){
                            document.getElementById("Vender Code"+i).focus();
                            document.getElementById("Vender Code"+i).value="";
                            alert(specialChar_validation_msg +"Vendor Code");
                            return false;
                        }
                        if(document.getElementById("dealerDiscount"+i).value=="0.00"){
                            document.getElementById("dealerDiscount"+i).focus();
                            alert("Dealer Discount can't be zero");
                            return false;
                        }
                        if(document.getElementById("cgstAmt"+i).value=="0.0" && document.getElementById("sgstAmt"+i).value=="0.0" && document.getElementById("ugstAmt"+i).value=="0.0" && document.getElementById("igstAmt"+i).value=="0.0" ){
                            document.getElementById("cgstAmt"+i).focus();
                            alert("Claim cannot be created if GST % is not available for given parts");
                            return false;
                        }
                    }
                    var answer=confirm("Do you really want to submit the form?");
                    if (answer==true)
                    {
                        document.getElementById("btn").disabled = true;
                        document.getElementById("warrantyForm").submit();
                    }
                    else
                    {
                        return false;
                    }
                    
                }
    
    function toggleNextFileInput(current) {
        var currentFileInput = document.getElementById("FailedPart" + current);
        var nextFileInput = document.getElementById("FailedPart" + (current + 1));

        if (currentFileInput.files.length > 0 && nextFileInput) {
            nextFileInput.disabled = false;
        } else if (nextFileInput) {
            nextFileInput.disabled = true;
        }
    }
	
	function showHoursValue() {
        const hoursInput = document.getElementById('Hours');
        alert("The value of Hours is: " + hoursInput.value);
    }
</script>

<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.warranty.warranty" /></a></li>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/warrantyAction.do?option=initWarrantyList'><bean:message key="label.warranty.pendingforwarranty" /></a></li>
            <li class="breadcrumb_link"><bean:message key="label.warranty.genrateWarrantyClaim" /></li>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1><bean:message key="label.warranty.genrateWarrantyClaim" /> </h1>
                <form action="<%=cntxpath%>/warrantyAction.do" method="post" id="warrantyForm" enctype="multipart/form-data" onsubmit="return false;">
                    <input type="hidden" name="option" value="saveWarranty" >
                    <input type="hidden" name="upperLink" value="<a href ='<%=cntxpath%>/initWarranty.do'><bean:message key="label.warranty.warranty" /></a>@@<a href = '<%=cntxpath%>/warrantyAction.do?option=initWarrantyList'><bean:message key="label.warranty.pendingforwarranty" /></a>@@<bean:message key="label.warranty.genrateWarrantyClaim" />"/>
                    <table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" >
                        <input type="hidden" name="vinid" value="${warrantyForm.vinid}">
                        <tr>
                            <td>
                                <table width="100%" border="0" cellspacing="3" cellpadding="3" align="center">
                                    <tr bgcolor="#eeeeee" height="20" >
                                        <td colspan="6" class="headingSpas" align="left" >
                                            <label>
                                                <B><bean:message key="label.common.customerinfo" /></B>
                                            </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right"><label><bean:message key="label.common.CustomerNameCaps" /></label></td>
                                        <td align="left"><input type="text" name="customerName" id="Customer Name" class="txtGeneralNW" value="${warrantyForm.customerName}" title="${warrantyForm.customerName}" readonly="readonly" onkeydown="return false;"/></td>   <%--readonly="readonly"--%>
                                        <td align="right"><label><bean:message key="label.warranty.customerAddress" /></label></td>
                                        <td align="left"><input type="text" name="customerAddress" id="Customer Address" class="txtGeneralNW" value="${warrantyForm.customerAddress}" title="${warrantyForm.customerAddress}" readonly="readonly" onkeydown="return false;"/></td>
                                        <td align="right"><label><bean:message key="label.common.contactno" /></label></td>
                                        <td align="left"><input type="text" name="contactNo" id="Contact No." class="txtGeneralNW" value="${warrantyForm.contactNo}" title="${warrantyForm.contactNo}" readonly="readonly" onkeydown="return false;"/></td>
                                    </tr>
                                    <tr>
                                        <td align="right"><label><bean:message key="label.common.dealerName" /></label></td>
                                        <td align="left"><input type="text" name="dealerName" id="Dealer Name" class="txtGeneralNW" value="${warrantyForm.dealerName}" title="${warrantyForm.dealerName}"  readonly="readonly" onkeydown="return false;"/>
                                            <div class="search"><a href="#"></a></div></td>
                                        <td align="right"><label><bean:message key="label.warranty.TMSRefNo" /></label></td>
                                        <td align="left"><input type="text" name="tmsRefNo" id="TMS Ref. No." class="txtGeneralNW" value="${warrantyForm.tmsRefNo}" title="${warrantyForm.tmsRefNo}" readonly="readonly" onkeydown="return false;"/></td>
                                            <%-- <td align="right"><label class="red"><bean:message key="label.warranty.warrantyClaimNo" /></label></td>
                                             <td align="left"><input type="text" name="warrantyClaimNo" id="Warranty Claim No." class="txtGeneralNW" value=""  /></td>--%>

                                        <td align="right"><label ><bean:message key="label.warranty.claimDate" /></label></td>
                                        <td align="left"><input type="text"  name="claimDate" id="Claim Date" class="txtGeneralNW" value="${warrantyForm.currentDate}" title="${warrantyForm.currentDate}" readonly="readonly" onkeydown="return false;"/></td>  <%--class="datepicker"--%>

                                    </tr>
                                    <%--<tr>
                                        <td align="right"><label ><bean:message key="label.warranty.claimDate" /></label></td>
                                        <td align="left"><input type="text" class="datepicker" name="claimDate" id="Claim Date" class="txtGeneralNW" value=""  readonly="readonly"/></td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                        <td>&nbsp;</td>
                                    </tr>--%>

                                    <tr bgcolor="#eeeeee" height="20" >
                                        <td colspan="6" class="headingSpas" align="left" >
                                            <label >
                                                <B><bean:message key="label.common.TractorInfomation" /></B>
                                            </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right"><label><bean:message key="label.catalogue.chassisNo" /></label></td>
                                        <td align="left"><input type="text" name="vinNo" id="Chassis No." class="txtGeneralNW" value="${warrantyForm.vinNo}" title="${warrantyForm.vinNo}" readonly="readonly" onkeydown="return false;"/>
                                            <div class="search"><a href="#"></a></div></td>
                                        <td align="right"><label><bean:message key="label.common.engineno" /></label></td>
                                        <td align="left"><input type="text" name="engineNo" id="Engine No." class="txtGeneralNW" value="${warrantyForm.engineNo}" title="${warrantyForm.engineNo}"  readonly="readonly" onkeydown="return false;"/></td>
                                        <td align="right"><label class="red"><bean:message key="label.common.Modelfamily" /></label></td>
                                        <td align="left"><input type="text" name="modelFamily" id="Model Family" class="txtGeneralNW" value="${warrantyForm.modelFamily}" title="${warrantyForm.modelFamily}" readonly="readonly" onkeydown="return false;"/></td>
                                    </tr>
                                    <tr>
                                        <td align="right"><label><bean:message key="label.warranty.modelNo" /></label></td>
                                        <td align="left"><input type="text" name="modelNo" id="Model No." class="txtGeneralNW" value="${warrantyForm.modelNo}" title="${warrantyForm.modelNo}" readonly="readonly" onkeydown="return false;" />
                                            <div class="search"><a href="#"></a></div></td>
                                        <td align="right"><label><bean:message key="label.common.Modelfamilydesc" /></label></td>
                                        <td align="left"><input type="text" name="modelFamilyDesc" id="Model Family Description" class="txtGeneralNW" value="${warrantyForm.modelFamilyDesc}" title="${warrantyForm.modelFamilyDesc}" readonly="readonly"  onkeydown="return false;"/></td>
                                        <td align="right"><label class="red"><bean:message key="label.catalogue.modelDesc" /></label></td>
                                        <td align="left"><input type="text" name="modelDesc" id="Model Description" class="txtGeneralNW" value="${warrantyForm.modelDesc}" title="${warrantyForm.modelDesc}" readonly="readonly" onkeydown="return false;"/></td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                    </tr>
                                    <tr bgcolor="#eeeeee" height="20" >
                                        <td colspan="6" class="headingSpas" align="left" >
                                            <label >
                                                <B><bean:message key="label.warranty.jobCardDetails" /></B>
                                            </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td align="right"><label><bean:message key="label.common.jobcardno" /></label></td>
                                        <td align="left"><input type="text" name="jobCardNo" id="Job Card No." class="txtGeneralNW" value="${warrantyForm.jobCardNo}" title="${warrantyForm.jobCardNo}" readonly="readonly" onkeydown="return false;"/>
                                            <div class="search"><a href="#"></a></div></td>
                                        <td align="right"><label><bean:message key="label.common.jobcardDate" /></label></td>
                                        <td align="left"><input type="text" name="jobCardDate"  id="Job Card Date" class="txtGeneralNW" value="${warrantyForm.jobCardDate}" title="${warrantyForm.jobCardDate}"  readonly="readonly" onkeydown="return false;"/></td>
                                        <td align="right"><label class="red"><bean:message key="label.warranty.dateofDelivery" /></label></td>
                                        <td align="left"><input type="text" name="deliveryDate"  id="Delivery Date" class="txtGeneralNW" value="${warrantyForm.deliveryDate}" title="${warrantyForm.deliveryDate}" readonly="readonly" onkeydown="return false;"/></td>
                                    </tr>
                                    <tr>
                                        <td align="right"><label><bean:message key="label.warranty.dateofReplacement" /></label></td>
                                        <td align="left"><input type="text" name="replacementDate"   id="Replacement Date" class="txtGeneralNW" value="${warrantyForm.replacementDate}" title="${warrantyForm.replacementDate}"  readonly="readonly" onkeydown="return false;"/>
                                        </td>
                                        <td align="right"><label><bean:message key="label.warranty.dateofFailure" /></label></td>
                                        <td align="left"><input type="text" name="failureDate"  id="Failure Date" class="txtGeneralNW" value="${warrantyForm.jobCardDate}" title="${warrantyForm.jobCardDate}" readonly="readonly" onkeydown="return false;"/></td>  <%--class="datepicker"--%>
                                        <td align="right"><label class="red"><bean:message key="label.warranty.hours" /></label></td>
                                        <td align="left"><input type="text" name="hours" id="Hours" class="txtGeneralNW" value="${warrantyForm.hours}" title="${warrantyForm.hours}" readonly="readonly" onkeydown="return false;"/></td>
                                    </tr>
									
								
								    
                                    <tr>
                                    <td align="right" colspan="5"><label id="Failed Part Label1"><bean:message key="label.warranty.failedPartsFileAttached" />-1<span class='mandatory'>*</span></label></td>
                                    <td align="left"><input type="file" name="failedPart1" id="FailedPart1" class="txtGeneralNW" style="width: 195px" onchange="toggleNextFileInput(1)"/></td>
                                    </tr>
                                    
                                    <tr>
                                    <td align="right" colspan="5"><label id="Failed Part Label2"><bean:message key="label.warranty.failedPartsFileAttached" />-2</label></td>
                                    <td align="left"><input type="file" name="failedPart2" id="FailedPart2" class="txtGeneralNW" style="width: 195px" disabled onchange="toggleNextFileInput(2)"/></td>
                                    </tr>
                                    
                                    <tr>
                                    <td align="right" colspan="5"><label id="Failed Part Label3"><bean:message key="label.warranty.failedPartsFileAttached" />-3</label></td>
                                    <td align="left"><input type="file" name="failedPart3" id="FailedPart3" class="txtGeneralNW" style="width: 195px" disabled onchange="toggleNextFileInput(3)"/></td>
                                    </tr>
                                    
                                    <tr>
                                    <td align="right" colspan="5"><label id="Failed Part Label4"><bean:message key="label.warranty.failedPartsFileAttached" />-4</label></td>
                                    <td align="left"><input type="file" name="failedPart4" id="FailedPart4" class="txtGeneralNW" style="width: 195px" disabled onchange="toggleNextFileInput(4)"/></td>
                                    </tr>
                                    
                                    <tr>
                                    <td align="right" colspan="5"><label id="Failed Part Label5"><bean:message key="label.warranty.failedPartsFileAttached" />-5</label></td>
                                    <td align="left"><input type="file" name="failedPart5" id="FailedPart5" class="txtGeneralNW" style="width: 195px" disabled onchange="toggleNextFileInput(5)"/></td>
                                    </tr>




                                    <%-- <tr bgcolor="#eeeeee" height="20" >
                                         <td colspan="6" class="headingSpas" align="left" >
                                             <label >
                                                 <B><bean:message key="label.warranty.consignmentdetails" /></B>
                                             </label>
                                         </td>
                                     </tr>
                                     <tr>
                                         <td align="right"><label><bean:message key="label.warranty.partsDisptachedFor" /></label><span class='mandatory'>*</span></td>
                                         <td align="left">
                                             <select name="dispatchFor" id="Dispatched For" class="selectnewbox" class="txtGeneralNW">
                                                 <option value="">--Select--</option>
                                                 <option value="CFT">CFT </option>
                                                 <option value="Warranty">Warranty</option>
                                             </select>
                                         </td>
                                         <td align="right"><label><bean:message key="label.warranty.selectDispatchedBy" />Select Dispatched By</label><span class='mandatory'>*</span></td>
                                         <td align="left"><select name="dispatchBy" id="Dispatched By" class="selectnewbox" onchange="makeEnable(this.value)">
                                                 <option value="">--Select--</option>
                                                 <option value="By Hand"> By Hand </option>
                                                 <option value="Courier">Courier</option>
                                                 <option value="GR">GR</option>
                                                 <option value="RR">RR</option>

                                            </select></td>
                                        <td align="right"><label class="red"><bean:message key="label.warranty.dispatchDate" /><span class='mandatory'>*</span></label></td>
                                        <td align="left"><input type="text" name="dispatchDate" class="datepicker" id="Date" class="txtGeneralNW" value=""  readonly="readonly" onkeydown="return false;"/></td>
                                    </tr>
                                    <tr>
                                        <td colspan="6" align="left" class="tdgridnew1"><font color="blue">If not By Hand, then enter following information.</font></td>
                                    </tr>
                                    <tr style="display:">
                                        <td align="right"><label id="GR Courier No.Label"><bean:message key="label.warranty.grCourierRRNo" /><span class='mandatory'>*</span></label></td>
                                        <td align="left">
                                            <input type="text" name="courierNo" id="GR Courier No." class="txtGeneralNW" value="" maxlength="30"  />
                                        </td>
                                        <td align="right"><label id="Transporter/Courier NameLabel"><bean:message key="label.warranty.transporterCourierName" /><span class='mandatory'>*</span></label></td>
                                        <td align="left">
                                            <input type="text" name="courierName" id="Transporter/Courier Name" class="txtGeneralNW" value="" maxlength="30" />
                                       </td>
                                        <td align="right"><label id="Browse GR/Courier/RR copyLabel"><bean:message key="label.warranty.browseGRCourierRRcopy" /><span class='mandatory'>*</span></label></td>
                                        <td align="left"><input type="file" name="courierCopy" id="Browse GR/Courier/RR copy" class="txtGeneralNW" value="" style="width: 195px" /></td>
                                    </tr>
                                    <tr>
                                        <td>&nbsp;</td>
                                    </tr>
                                    --%>
                                    <tr><td colspan="6"><div style="overflow: scroll; width: 1235px">
                                                <table  border="0" cellspacing="3" cellpadding="3" width="100%" >

                                                    <tr bgcolor="#eeeeee" height="20" >
                                                        <td colspan="6" class="headingSpas" align="left" >
                                                            <label >
                                                                <B><bean:message key="label.catalogue.partDetails" /></B>
                                                            </label>
                                                        </td>
                                                    </tr>



                                                    <tr>
                                                        <c:if test="${!empty partList}">
                                                            <td colspan="6">
                                                                <table  border="0" cellspacing="3" cellpadding="3" width="100%" >
                                                                    <tr class="grid ">
                                                                        <th width="3%" align="left" ><bean:message key="label.warranty.S.No" /></th>
                                                                        <th width="5%" align="left" ><bean:message key="label.warranty.defectDescription" /></th>
                                                                        <th width="5%" align="left" ><bean:message key="label.warranty.causalConsequential" /></th>
                                                                        <th width="4%" align="left" ><bean:message key="label.catalogue.partCode" /></th>
                                                                        <th  width="4%" align="left" ><bean:message key="label.common.partDesc" /></th>
                                                                        <th width="4%" align="left" ><bean:message key="label.warranty.venderCode" /><span class='mandatory'>*</span></th>

                                                                        <th width="4%" align="center" ><bean:message key="label.common.hsnCode" /></th>
                                                                        <th width="4%" align="center" ><bean:message key="label.common.unitPrice" /></th>
                                                                        <th width="4%" align="center" ><bean:message key="label.common.Qty" /></th>
                                                                        <%--<th width="4%" align="center" ><bean:message key="label.common.packingNo" /></th>
                                                                        <th width="4%" align="center" ><bean:message key="label.warranty.bixNo" /></th>
                                                                        <th width="4%" align="center" ><bean:message key="label.warranty.dispatchQty" /></th>
                                                                        <th width="4%" align="center" ><bean:message key="label.saleReturn.ReceivedQty" /></th>--%>

                                                                        <%--<th width="4%" align="center" ><bean:message key="label.common.amount" /></th>--%>
                                                                        <th width="4%" align="center" ><bean:message key="label.common.dealerDiscount" /></th>
                                                                        <th width="4%" align="center" ><bean:message key="label.common.taxableValue" /></th>
                                                                        <th width="4%" align="center" ><bean:message key="label.common.cgstRate" /></th>
                                                                        <th width="4%" align="center" ><bean:message key="label.common.cgstAmt" /></th>
                                                                        <th width="4%" align="center" ><bean:message key="label.common.sgstRate" /></th>
                                                                        <th width="4%" align="center" ><bean:message key="label.common.sgstAmt" /></th>
                                                                        <th width="4%" align="center" ><bean:message key="label.common.ugstRate" /></th>
                                                                        <th width="4%" align="center" ><bean:message key="label.common.ugstAmt" /></th>
                                                                        <th width="4%" align="center" ><bean:message key="label.common.igstRate" /></th>
                                                                        <th width="4%" align="center" ><bean:message key="label.common.igstAmt" /></th>
                                                                        <%--<th width="4%" align="center"  ><bean:message key="label.warranty.claimValue" /></th>--%>
                                                                    </tr>

                                                                    <c:set var="i" value="1"/>
                                                                    <c:forEach items="${partList}" var="partList">
                                                                        <tr>
                                                                        <td align="center" class="tdgridnew1 " >${i}.</td>
                                                                        <input type="hidden" style="width:90px " name="jobSpareIDArr" id="jobSpareID" class="txtGeneralNW"  value="${partList.jobSpareID}" readonly="readonly" onkeydown="return false;"/>
                                                                        <input type="hidden" style="width:90px " name="partCategoryArr" id="Part Cat" class="txtGeneralNW"  value="${partList.partCategory}" readonly="readonly" onkeydown="return false;"/>
                                                                        <input type="hidden" style="width:90px " name="cmpNoArr" id="Part Cat" class="txtGeneralNW"  value="${partList.cmpNo}" readonly="readonly" onkeydown="return false;"/>
                                                                        <td align="left" ><input type="text" style="width:90px " name="complaintNoArr" id="Complaint No." class="txtGeneralNW"  value="${partList.compDesc}" title="${partList.compDesc}" readonly="readonly" onkeydown="return false;"/></td>
                                                                        <td align="left"><input type="text" style="width:90px " name="causalOrConseqArr" id="textfield6" class="txtGeneralNW" value="${partList.causalOrConseq}" title="${partList.causalOrConseq}" readonly="readonly" onkeydown="return false;"/></td>
                                                                        <td align="left" ><input type="text" style="width:90px " name="partNoArr" id="Part No" class="txtGeneralNW" value="${partList.partNo}" title="${partList.partNo}" readonly="readonly" onkeydown="return false;"/></td>
                                                                        <td align="left" ><input type="text" style="width:90px " name="partDescArr" id="Part Description" class="txtGeneralNW" value="${partList.partDesc}" title="${partList.partDesc}" readonly="readonly" onkeydown="return false;"/></td>
                                                                        <td align="left" ><input type="text" style="width:90px " name="venderCodeArr" id="Vender Code${i}" class="txtGeneralNW" value="${partList.venderCode}" maxlength="15" /></td>
                                                                        <td align="left" ><input type="text" style="width:90px;text-align: center; " name="hsnNoArr" id="hsnNo${i}" class="txtGeneralNW" value="${partList.hsnCode}"  readonly="readonly"   onkeydown="return false;"/></td>
                                                                        <td align="left" ><input type="text" style="width:90px;text-align: right; " name="unitPriceArr" id="Unit Price${i}" class="txtGeneralNW" value="${partList.unitPrice}"  readonly="readonly"   onkeydown="return false;"/></td>
                                                                        <td align="left" ><input type="text" style="width:90px;text-align: center; " name="qtyArr" id="Qty${i}" class="txtGeneralNW"  value="${partList.qty}" readonly="readonly" onkeydown="return false;" align="right"/></td>
                                                                            <%--                                                        <td align="left" ><input type="text" style="width:90px;text-align: center; " name="packingNoArr" id="packingNo${i}" class="txtGeneralNW"  value="${partList.packingNo}" readonly="readonly" onkeydown="return false;" align="right"/></td>
                                                                                                                                        <td align="left" ><input type="text" style="width:90px;text-align: center; " name="boxNoArr" id="boxNo${i}" class="txtGeneralNW"  value="${partList.boxNo}" readonly="readonly" onkeydown="return false;" align="right"/></td>
                                                                                                                                        <td align="left" ><input type="text" style="width:90px;text-align: center; " name="dispatchQtyArr" id="dispatchQty${i}" class="txtGeneralNW"  value="${partList.dispatchQty}" readonly="readonly" onkeydown="return false;" align="right"/></td>
                                                                                                                                        <td align="left" ><input type="text" style="width:90px;text-align: center; " name="receivedQtyArr" id="receivedQty${i}" class="txtGeneralNW"  value="${partList.receivedQty}" readonly="readonly" onkeydown="return false;" align="right"/></td>--%>
                                                                            <%--<td align="left" ><input type="text" style="width:90px;text-align: right; " name="amountArr" id="amount${i}" class="txtGeneralNW" value="${partList.amount}"  readonly="readonly"   onkeydown="return false;"/></td>--%>
                                                                        <td align="left" ><input type="text" style="width:90px;text-align: right; " name="dealerDiscountArr" id="dealerDiscount${i}" class="txtGeneralNW" value="${partList.dealerDiscount}"  readonly="readonly"   onkeydown="return false;"/></td>
                                                                        <td align="left" ><input type="text" style="width:90px;text-align: right; " name="taxableValueArr" id="taxableValue${i}" class="txtGeneralNW" value="${partList.taxableValue}"  readonly="readonly"   onkeydown="return false;"/></td>

                                                                        <td align="left" ><input type="text" style="width:90px;text-align: right; " name="cgstRateArr" id="cgstRate${i}" class="txtGeneralNW" value="${partList.cgstRate}"  readonly="readonly"   onkeydown="return false;"/></td>
                                                                        <td align="left" ><input type="text" style="width:90px;text-align: right; " name="cgstAmtArr" id="cgstAmt${i}" class="txtGeneralNW" value="${partList.cgstAmt}"  readonly="readonly"   onkeydown="return false;"/></td>
                                                                        <td align="left" ><input type="text" style="width:90px;text-align: right; " name="sgstRateArr" id="sgstRate${i}" class="txtGeneralNW" value="${partList.sgstRate}"  readonly="readonly"   onkeydown="return false;"/></td>
                                                                        <td align="left" ><input type="text" style="width:90px;text-align: right; " name="sgstAmtArr" id="sgstAmt${i}" class="txtGeneralNW" value="${partList.sgstAmt}"  readonly="readonly"   onkeydown="return false;"/></td>
                                                                        <td align="left" ><input type="text" style="width:90px;text-align: right; " name="ugstRateArr" id="ugstRate${i}" class="txtGeneralNW" value="${partList.ugstRate}"  readonly="readonly"   onkeydown="return false;"/></td>
                                                                        <td align="left" ><input type="text" style="width:90px;text-align: right; " name="ugstAmtArr" id="ugstAmt${i}" class="txtGeneralNW" value="${partList.ugstAmt}"  readonly="readonly"   onkeydown="return false;"/></td>
                                                                        <td align="left" ><input type="text" style="width:90px;text-align: right; " name="igstRateArr" id="igstRate${i}" class="txtGeneralNW" value="${partList.igstRate}"  readonly="readonly"   onkeydown="return false;"/></td>
                                                                        <td align="left" ><input type="text" style="width:90px;text-align: right; " name="igstAmtArr" id="igstAmt${i}" class="txtGeneralNW" value="${partList.igstAmt}"  readonly="readonly"   onkeydown="return false;"/></td>
                                                                        <td align="center" ><input type="hidden" style="width:90px;text-align: right; " name="claimValueArr" id="Claim Value${i}" class="txtGeneralNW" value="${partList.claimValue}" readonly="readonly" onkeydown="return false;"/></td>
                                                            </tr>
                                                            <c:set var="i" value="${i+1}"/>


                                                        </c:forEach>
                                                            <c:set var="j" value="1"/>
                                                             <c:forEach items="${otherPartList}" var="otherPartList">
                                                                <tr>
                                                                       <td align="center" class="tdgridnew1 " ></td>
                                                                        <td align="left" style="width:90px;text-align: right; ">&nbsp;</td>
                                                                        <td align="left"  style="width:90px;text-align: right; ">&nbsp;</td>
                                                                        <td align="left" style="width:90px;text-align: right; ">&nbsp;</td>
                                                                        <td align="left" colspan="2" style="width:90px;text-align: right; "><b>${otherPartList.partDesc}&nbsp;</b></td>
                                                                        <td align="left" style="width:90px;text-align: center;" >${otherPartList.hsnCode}&nbsp;</td>
                                                                        <td align="left" style="width:90px;text-align: right; ">&nbsp;</td>
                                                                        <td align="left" style="width:90px;text-align: center; ">&nbsp;</td>
                                                                        <td align="left" style="width:90px;text-align: right; ">&nbsp;</td>
                                                                        <td align="left" style="width:90px;text-align: right; ">${otherPartList.taxableValue}</td>
                                                                        <td align="left" style="width:90px;text-align: right; ">${otherPartList.cgstRate}</td>
                                                                        <td align="left" style="width:90px;text-align: right; ">${otherPartList.cgstAmt}</td>
                                                                        <td align="left" style="width:90px;text-align: right; ">${otherPartList.sgstRate}</td>
                                                                        <td align="left" style="width:90px;text-align: right; ">${otherPartList.sgstAmt}</td>
                                                                        <td align="left" style="width:90px;text-align: right; ">${otherPartList.ugstRate}</td>
                                                                        <td align="left" style="width:90px;text-align: right; ">${otherPartList.ugstAmt}</td>
                                                                        <td align="left" style="width:90px;text-align: right; ">${otherPartList.igstRate}</td>
                                                                        <td align="left" style="width:90px;text-align: right; ">${otherPartList.igstAmt}</td>
                                                                        <%--<td align="center" >&nbsp;</td>--%>
                                                                         <c:set property="totalPartsValueStr" var="totalPartsValueStr" value="${otherPartList.totalClaimValue}"/>
                                                                        <c:set property="totalTaxableValueStr" var="totalTaxableValueStr" value="${otherPartList.totalTaxableValue}"/>
                                                                        <c:set property="totalTaxValueStr" var="totalTaxValueStr" value="${otherPartList.totalTaxValue}"/>
                                                                        <c:set property="totalInvoiceAmountStr" var="totalInvoiceAmountStr" value="${otherPartList.totalInvoiceAmount}"/>
                                                                <input type="hidden" name="lessAmmountArr" id="lessAmmount${t}" value="${otherPartList.taxableValue}">
                                                                <input type="hidden" name="lessAmmountArr" id="lessAmmount${t}" value="${otherPartList.cgstAmt+otherPartList.sgstAmt+otherPartList.ugstAmt+otherPartList.igstAmt}">
                                                   
                                                            </tr>
                                                            <c:set var="j" value="${j+1}"/>


                                                        </c:forEach>
                                                        <input type="hidden" name="count" id="Count" value="${i-1}">
                                                        <tr>
                                                            <td colspan="17" align="left" class="tdgridnew1"  >&nbsp;</td>
                                                            <%--<td align="right" class="tdgridnew1 "><bean:message key="label.common.totalAmmount" /></td>--%>
                                                            <td align="center" class="tdgridnew1 "><input type="hidden" name="totalClaimValue" id="TotalClaimValue"  class="txtGeneralNW"  value="${totalPartsValueStr}" readonly="readonly" style="width:90px;text-align: right; " onkeydown="return false;"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="17" align="left" class="tdgridnew1 " >&nbsp;</td>
                                                            <td align="right" class="tdgridnew1 "><bean:message key="label.common.totaltaxablevalue" /></td>
                                                            <td align="center" class="tdgridnew1 "><input type="text" name="totalTaxableValueStr" id="totalTaxableValueStr" class="txtGeneralNW"  value="${totalTaxableValueStr}" readonly="readonly" style="width:90px;text-align: right; " onkeydown="return false;"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="17" align="left" class="tdgridnew1 " >&nbsp;</td>
                                                            <td align="right" class="tdgridnew1 "><bean:message key="label.common.totaltaxvalue" /></td>
                                                            <td align="center" class="tdgridnew1 "><input type="text" name="totalTaxValueStr" id="totalTaxValueStr" class="txtGeneralNW"  value="${totalTaxValueStr}" readonly="readonly" style="width:90px;text-align: right; " onkeydown="return false;"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="19">
                                                                <table border="0" cellspacing="1" cellpadding="1" width="100%">
                                                                   <%-- <c:set var="t" value="1"/>
                                                                    <c:forEach items="${taxList}" var="taxList">

                                                                        <c:if test="${taxList.typeDescription eq 'GST on Insurance Charges'}">
                                                                            <tr>
                                                                                <td width="100%" align="right" class="tdgridnew1 "> Insurance charges <input type="hidden" name="discount" id="Discount${t}" value="0"></td>
                                                                                <td align="center" class="tdgridnew1 "><input type="text" name="lessAmmountArr" id="lessAmmount${t}" class="txtGeneralNW" value="0.0"  style="width:90px;text-align: right;" onblur="calcGstOnIns(this.value);" onkeyup="calcGstOnIns(this.value);" maxlength="20" /></td>
                                                                            </tr>
                                                                            <c:set var="t" value="${t+1}"/>
                                                                            <tr>
                                                                                <td width="100%" align="right" class="tdgridnew1 "> ${taxList.typeDescription} (${taxList.typePercentage} %) <input type="hidden" name="discount" id="Discount${t}" value="${taxList.typePercentage}">        <bean:message key="label.warranty.total" /></td>
                                                                                <td align="center" class="tdgridnew1 "><input type="text" name="lessAmmountArr" id="lessAmmount${t}" class="txtGeneralNW" value="" readonly="readonly" style="width:90px;text-align: right; " onkeydown="return false;"/></td>
                                                                            </tr>

                                                                        </c:if>
                                                                        <c:if test="${taxList.typeDescription ne 'GST on Insurance Charges'}">
                                                                            <tr>
                                                                                <td width="100%" align="right" class="tdgridnew1 "> ${taxList.typeDescription} (${taxList.typePercentage} %) <input type="hidden" name="discount" id="Discount${t}" value="${taxList.typePercentage}">        <bean:message key="label.warranty.total" /></td>
                                                                                <td align="center" class="tdgridnew1 "><input type="text" name="lessAmmountArr" id="lessAmmount${t}" class="txtGeneralNW" value="" readonly="readonly" style="width:90px;text-align: right; " onkeydown="return false;"/></td>
                                                                            </tr>
                                                                        </c:if>
                                                                        <c:set var="t" value="${t+1}"/>
                                                                    </c:forEach>
                                                                    <input type="hidden" name="taxCount" id="TaxCount" value="${t-1}">--%>
                                                                    <tr>
                                                                        <td  width="100%" align="right" align="right" class="tdgridnew1 " width="60%"><bean:message key="label.common.totalinvoiceamount" /></td>
                                                                        <td align="center" class="tdgridnew1 "><input type="text" name="claimValueAfterDiscount" id="TotalValue" class="txtGeneralNW" value="${totalInvoiceAmountStr}" readonly="readonly" style="width:90px;text-align: right; " onkeydown="return false;"/></td>

                                                                    </tr>
                                                                </table>
                                                            </td>
                                                        </tr>

                                                    </c:if>
                                                    <c:if test="${empty partList}">
                                                        <tr bgcolor="#FFFFFF">
                                                            <td valign="top" align="center" colspan="6" class="headingSpas" style="padding-top:10px;color: red">
                                                                Part Details Not Found.!
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                    <tr>
                                                        <td colspan="22" align="left" class="tdgridnew1 " >&nbsp;</td>

                                                    </tr>

                                                </table></div></td> </tr>
                                </table>



                            </td></tr>
                    </table>

                    </td>

                    </tr>
                    </table>
                    <c:if test="${!empty partList}">
                        <div style="clear:both; margin:0 auto; text-align:center" >
                            <input name="input" type="button" value="Submit" class="submit" id="btn" onclick="validateWarranty()" >
                        </div><br>
                    </c:if>
                </form>
            </div>
        </div>
    </center>
</div>
<c:if test="${!empty partList}">
    <script>

        <%--var Count=document.getElementById("Count").value;
        for( var i=1;i<=Count;i++){
            var qty=document.getElementById("Qty"+i).value;
            var unitPrice=document.getElementById("Unit Price"+i).value;
            document.getElementById("Claim Value"+i).value=(qty*unitPrice).toFixed(2);
        }--%>
            var subTotal = 0.0;
            var claimValueArrTotal = 0.0;
            var claimValueArr = document.getElementsByName('claimValueArr');
            for (var i = 0; i < claimValueArr.length; i++) {
                claimValueArrTotal = claimValueArr[i].value == '' ? 0 : claimValueArr[i].value;
                subTotal += parseFloat(claimValueArrTotal);
            }
            document.getElementById("TotalClaimValue").value =subTotal.toFixed(2);

                   
            var lessAmmount =(( document.getElementById("TotalClaimValue").value * document.getElementById("Discount1").value )/100);
            document.getElementById("lessAmmount1").value =lessAmmount.toFixed(2);
            var lessCST= ( lessAmmount * document.getElementById("Discount2").value )/100;
            document.getElementById("lessAmmount2").value =lessCST.toFixed(2);
        <%-- var lessHandlingCharge= ( (document.getElementById("TotalClaimValue").value - lessAmmount) * document.getElementById("Discount3").value )/100;
         document.getElementById("lessAmmount3").value =lessHandlingCharge.toFixed(2);--%>
             var totalAfterLess=parseFloat(document.getElementById("TotalClaimValue").value) + parseFloat(lessAmmount) +parseFloat(lessCST);
             //document.getElementById("TotalValue").value =totalAfterLess.toFixed(2);
                                             
    </script>
</c:if>