<%--
    Document   : approveWarranty
    Created on : Sep 25, 2014, 6:35:37 PM
    Author     : kundan.rastogi
--%>


<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,java.io.File" %>
<c:set var="uplodFailedPartFilePath" value="${warrantyForm.failedPartFileName1}" scope="request" />
<style>
      .partDescInput{width:150px!important;}
    </style>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            String value = (String) pageContext.getRequest().getAttribute("uplodFailedPartFilePath");
%>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>
<script>

//When the document is loaded, set up event listeners
/*document.addEventListener("DOMContentLoaded", function() {
    var claimedCount = parseInt(document.getElementById("ClaimedCount")?.value, 10);
    if (isNaN(claimedCount)) {
        console.error("ClaimedCount element not found or invalid");
        return;
    }

    // Loop through the complaints
    for (let j = 1; j <= claimedCount; j++) {
        var partCountElement = document.getElementById("PartCount" + j);
        var qtyApprovedElement = document.getElementById("QtyApproved" + j);

        if (partCountElement && qtyApprovedElement) {
            var count = parseInt(partCountElement.value, 10);
            if (!isNaN(count)) {
                console.log("PartCount for complaint " + j + ": " + count);
                // Proceed with your logic
                qtyApprovedElement.addEventListener("blur", function() {
                    validD(this, j, j);  // Pass element and row number
                });
            } else {
                console.error("Invalid PartCount value for complaint " + j);
            }
        } else {
            console.error("Element not found for complaint " + j + ": PartCount or QtyApproved");
        }
    }
});*/


       function checkQty(val, j, i){
    	console.log("Qty Approved Value:", val,j,i);
        if(val!=''){
        	var totalQty = parseFloat(document.getElementById("Qty" + j + "-" + i).value);
            
            if (parseFloat(val) > totalQty) {
                alert("Approved Qty must be less than or equal to Total Qty.");
                document.getElementById("QtyApproved" + j + "-" + i).value = "";
                document.getElementById("QtyRejected" + j + "-" + i).value = "0";
                return false;
            }

            // Calculate and update Rejected Qty
            var qtyRejected = (totalQty - parseFloat(val)).toFixed(2);
            document.getElementById("QtyRejected" + j + "-" + i).value = qtyRejected;
           /* if(parseFloat(val)>parseFloat(document.getElementById("Qty"+j+"-"+i).value)){
                document.getElementById("QtyApproved"+j+"-"+i).focus();
                // document.getElementById("msg_saveFAILED").style.display = "";
                // document.getElementById("msg_saveFAILED").innerHTML="Aprroved Qty is must be less or Equal to Total Qty";
                alert(qty_chech_validation_msg);
                document.getElementById("QtyApproved"+j+"-"+i).value="";
                document.getElementById("QtyRejected"+j+"-"+i).value="0";
                return false;
            }*/
            var amount=(parseFloat(document.getElementById("Unit Price"+j+"-"+i).value) * parseFloat(val)).toFixed(2);
            var dealerDiscPercent =(parseFloat(document.getElementById("dealerDiscount"+j+"-"+i).value)).toFixed(2);
            
            var dealerDiscval =(parseFloat(amount*parseFloat(dealerDiscPercent)/100)).toFixed(2);
            var taxableValue=(parseFloat((amount-dealerDiscval))).toFixed(2);
            //document.getElementById("QtyRejected"+j+"-"+i).value=(parseFloat(document.getElementById("Qty"+j+"-"+i).value) - parseFloat(val)).toFixed(2);
            document.getElementById("ApproveAmmount"+j+"-"+i).value=(parseFloat(document.getElementById("Unit Price"+j+"-"+i).value) * parseFloat(val)).toFixed(2);
            document.getElementById("taxableValue"+j+"-"+i).value=taxableValue;


    <%--if(val==0 || val=="" ){
        document.getElementById("cgstRate"+j+"-"+i).value=0.00;
        document.getElementById("sgstRate"+j+"-"+i).value=0.00;
        document.getElementById("ugstRate"+j+"-"+i).value=0.00;
        document.getElementById("igstRate"+j+"-"+i).value=0.00;
    }--%>
                        var cgstPercent =(parseFloat(document.getElementById("cgstRate"+j+"-"+i).value)).toFixed(2);
                        var cgstval =(parseFloat(taxableValue*parseFloat(cgstPercent)/100)).toFixed(2);
                        var sgstPercent =(parseFloat(document.getElementById("sgstRate"+j+"-"+i).value)).toFixed(2);
                        var sgstval =(parseFloat(taxableValue*parseFloat(sgstPercent)/100)).toFixed(2);
                        var ugstPercent =(parseFloat(document.getElementById("ugstRate"+j+"-"+i).value)).toFixed(2);
                        var ugstval =(parseFloat(taxableValue*parseFloat(ugstPercent)/100)).toFixed(2);
                        var igstPercent =(parseFloat(document.getElementById("igstRate"+j+"-"+i).value)).toFixed(2);
                        var igstval =(parseFloat(taxableValue*parseFloat(igstPercent)/100)).toFixed(2);
                        document.getElementById("cgstAmt"+j+"-"+i).value=cgstval;
                        document.getElementById("sgstAmt"+j+"-"+i).value=sgstval;
                        document.getElementById("ugstAmt"+j+"-"+i).value=ugstval;
                        document.getElementById("igstAmt"+j+"-"+i).value=igstval;
                        
                        if (igstval === '0.00') {
                            document.getElementById("igstRate" + j + "-" + i).value = "0.00";
                        }
                        
                        if (cgstval === '0.00') {
                            document.getElementById("cgstRate" + j + "-" + i).value = "0.00";
                        }
                        
                        if (sgstval === '0.00') {
                            document.getElementById("sgstRate" + j + "-" + i).value = "0.00";
                        }
                        
                        if (ugstval === '0.00') {
                            document.getElementById("ugstRate" + j + "-" + i).value = "0.00";
                        }
                        
                        
                        var total=(parseFloat(taxableValue)+parseFloat(cgstval)+parseFloat(sgstval)+parseFloat(ugstval)+parseFloat(igstval)).toFixed(2);
                        document.getElementById("Claim Value"+j+"-"+i).value=parseFloat(taxableValue);//total;


                        var taxableValueArr = document.getElementsByName('taxableValueArr');
                        var cgstRateArr = document.getElementsByName('cgstRateArr');
                        var sgstRateArr = document.getElementsByName('sgstRateArr');
                        var ugstRateArr = document.getElementsByName('ugstRateArr');
                        var igstRateArr = document.getElementsByName('igstRateArr');

                        var cgstAmtArr = document.getElementsByName('cgstAmtArr');
                        var sgstAmtArr = document.getElementsByName('sgstAmtArr');
                        var ugstAmtArr = document.getElementsByName('ugstAmtArr');
                        var igstAmtArr = document.getElementsByName('igstAmtArr');

                        var taxable='0';
                        var cgstRate='0';
                        var sgstRate='0';
                        var ugstRate='0';
                        var igstRate='0';
                        var cgstAmt='0';
                        var sgstAmt='0';
                        var ugstAmt='0';
                        var igstAmt='0';
                        var appQtyArr=document.getElementsByName("qtyApprovedArr");
            
                        for(var i=0;i<taxableValueArr.length;i++){
                
                            //if(parseFloat(taxable) < parseFloat(taxableValueArr[i].value)){
                                taxable=parseFloat(taxable)+parseFloat(taxableValueArr[i].value);
                            //}
                            if(parseFloat(cgstRate) < parseFloat(cgstRateArr[i].value) && appQtyArr[i].value!=0){
                                cgstRate=cgstRateArr[i].value;
                            }
                            if(parseFloat(sgstRate) < parseFloat(sgstRateArr[i].value) && appQtyArr[i].value!=0){
                                sgstRate=sgstRateArr[i].value;
                            }
                            if(parseFloat(ugstRate) < parseFloat(ugstRateArr[i].value) && appQtyArr[i].value!=0){
                                ugstRate=ugstRateArr[i].value;
                            }
                            if(parseFloat(igstRate) < parseFloat(igstRateArr[i].value) && appQtyArr[i].value!=0){
                                igstRate=igstRateArr[i].value;
                            }
                            cgstAmt=parseFloat(cgstAmt)+parseFloat(cgstAmtArr[i].value);
                            sgstAmt=parseFloat(sgstAmt)+parseFloat(sgstAmtArr[i].value);
                            ugstAmt=parseFloat(ugstAmt)+parseFloat(ugstAmtArr[i].value);
                            igstAmt=parseFloat(igstAmt)+parseFloat(igstAmtArr[i].value);
                        }
                        var maxRate=cgstRate;
                        if(maxRate<sgstRate){
                            maxRate=sgstRate;
                        }else if(maxRate<ugstRate){
                            maxRate=ugstRate;
            
                        }else if(maxRate<igstRate){
                            maxRate=igstRate;
                        }

                        var totMaxRate=0;
                        if(cgstAmt!=0.00){
                            totMaxRate+=parseFloat(maxRate);
                        }
                        if(sgstAmt!=0.00){
                            totMaxRate+=parseFloat(maxRate);
                        }
                        if(ugstAmt!=0.00){
                            totMaxRate+=parseFloat(maxRate);
                        }
                        if(igstAmt!=0.00){
                            totMaxRate+=parseFloat(maxRate);
                        }
                        document.getElementById("Discount2").value=totMaxRate;
                        document.getElementById("Discount4").value=totMaxRate;

                        var totGst=(parseFloat(cgstAmt)+parseFloat(sgstAmt)+parseFloat(ugstAmt)+parseFloat(igstAmt)).toFixed(2);
                        getLabourCharge(taxable,(parseFloat(cgstRate)+parseFloat(sgstRate)+parseFloat(ugstRate)+parseFloat(igstRate)));

                        var subTotal = 0.0;
                        var approveAmmountArrTotal = 0.0;
                        var grandApproveAmmountArrTotal = 0.0;
                        var approveAmmountArr = document.getElementsByName('approveAmmountArr');

                        var count=0;
                        for(var p=1;p<=approveAmmountArr.length;p++){
                            if(document.getElementById("Claim Value"+j+"-"+p)!=null){
                                count++;
                            }
                        }

                        for (var k = 1; k <= count; k++) {
                            approveAmmountArrTotal = document.getElementById("Claim Value"+j+"-"+k).value == '' ? 0 : document.getElementById("Claim Value"+j+"-"+k).value;
                            subTotal += parseFloat(approveAmmountArrTotal);
                        }
                        document.getElementById("TotalApproveAmmount"+j).value =subTotal.toFixed(2);

                        var claimCount=document.getElementById("ClaimedCount").value;

                        subTotal=0.0;
                        for(var t=1;t<=claimCount;t++){
                            grandApproveAmmountArrTotal = document.getElementById("TotalApproveAmmount"+t).value == '' ? 0 : document.getElementById("TotalApproveAmmount"+t).value;
                            subTotal += parseFloat(grandApproveAmmountArrTotal);
                        }
                        document.getElementById("GrandTotalApproveAmmount").value =subTotal.toFixed(2);
            
                        if(document.getElementById("GrandTotalApproveAmmount").value !=""){
                            var lessAmmount =(( document.getElementById("GrandTotalApproveAmmount").value * document.getElementById("Discount1").value )/100);
                            document.getElementById("lessAmmount1").value =lessAmmount.toFixed(2);
                            var lessCST= ( lessAmmount * totMaxRate )/100;
                            document.getElementById("lessAmmount2").value =lessCST.toFixed(2);
                            var insCharge=document.getElementById("lessAmmount3").value;
                            var gstOnInsCharge=document.getElementById("lessAmmount4").value;
                            //var totalAfterLess=parseFloat(document.getElementById("GrandTotalApproveAmmount").value) + parseFloat(lessAmmount) +parseFloat(lessCST)+parseFloat(insCharge)+parseFloat(gstOnInsCharge);
                            //document.getElementById("TotalValue").value =totalAfterLess.toFixed(2);
                            var totalTaxableVal=(subTotal+parseFloat(lessAmmount)+parseFloat(insCharge)).toFixed(2);
                            var totalTaxVal=(parseFloat(totGst)+parseFloat(lessCST)+parseFloat(gstOnInsCharge)).toFixed(2);
                            document.getElementById("GTotalApproveAmmount").value =totalTaxableVal;
                            document.getElementById("totalTaxAmmount").value =totalTaxVal;
                            document.getElementById("TotalValue").value =(parseFloat(totalTaxableVal)+parseFloat(totalTaxVal)).toFixed(2);
               
                        }

                    }else{
                        document.getElementById("QtyRejected"+j+"-"+i).value=0;
                    	//document.getElementById("QtyRejected" + j + "-" + i).value = document.getElementById("Qty" + j + "-" + i).value;
                    }
                }
       
function validD(dis, row, claimRow) {
    var digitsReg = /^[\d\.]*$/;  
    var claimCount = parseInt(document.getElementById("ClaimedCount").value, 10); // Parse claim count as integer

    console.log("Validating row:", row, "for claimRow:", claimRow);  
    for (var j = 1; j <= claimCount; j++) {
        if (claimRow == j) { 
            var partCountElement = document.getElementById("PartCount" + j);
            var count = partCountElement ? parseInt(partCountElement.value, 10) : 0;

            console.log("PartCount for complaint", j, ":", count); 
            for (var i = 1; i <= count; i++) {
                if (row == i) { 
                    var val = dis.value.trim();  
                    console.log('Validating value:', val);  

                    if (val === '' || !digitsReg.test(val)) {
                        console.log('Invalid value, trimming last character:', dis.value);  
                        dis.value = val.substring(0, val.length - 1);  
                        return false;  
                    } else {
                        console.log('Valid value, calling checkQty');  // Log valid value
                        checkQty(val, j, i);
                    }
                }
            }
        }
    }

    return true;  
}





              /* function validD(dis,row,claimrow){
                	console.log(dis,'Dis',row,'Roe',claimrow,'claimRow')
                    var digitsreg=/^[\d\.]*$/;
                    var claimCount=document.getElementById("ClaimedCount").value;
                    console.log(claimCount,'ClaimedCount')
                    for(var j=1;j<=claimCount;j++){
                          
                        if(eval(claimrow)==j){

                            var count=document.getElementById("PartCount").value;
                             console.log()
                            console.log(count,'part count')
                            for( var i=1;i<=count;i++){
                                if(eval(row)==i ){
                                    var val=dis.value;

                                    if(!digitsreg.test(val)){
                                        return dis.value=val.substring(0, (val.length-1))
                                    }else{
                                        checkQty(val,i,j);
                                    }
                                }
                            }
                        }
                    }
                    return true;
                }*/
                

              


                <%--function scrapChange(val,j,i){
                    if(val=="SCRAP" || val=="INSPECTION"){
                        document.getElementById("VendorCodeAdmin"+j+"-"+i).value="";
                        document.getElementById("VendorCodeAdmin"+j+"-"+i).readOnly=true;
                        document.getElementById("VendorCodeDesc"+j+"-"+i).value="";
                        document.getElementById("ValidVendorCode"+j+"-"+i).value="";
                    }
                    else{
                        document.getElementById("VendorCodeAdmin"+j+"-"+i).readOnly=false;
                    }
                }--%>

                function checkVendorCode(val,j,i){
                    if(document.getElementById("ScrapValue"+j+"-"+i).value=="" ){
                        if(val==""){
                            // document.getElementById("VendorCodeAdmin"+j+"-"+i).focus();
                            //alert(not_blank_validation_msg+' Vendor');
                            return false;
                        }
                        var todate = new Date().getTime();
                        var strURL = '<%=cntxpath%>/warrantyAction.do?option=getVendorDescbyCode&vendorCode=' + val + '&tm=' + todate;
                        xmlHttp = GetXmlHttpObject();
                        xmlHttp.onreadystatechange = function()
                        {
                            stateChangedPrice(xmlHttp,j,i);
                        };
                        xmlHttp.open("GET", strURL, false);
                        xmlHttp.send(null);
                    }
                }
                function stateChangedPrice(xmlHttp,j,i)
                {
                    var context = '<%=cntxpath%>';
                    var res = null;
                    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                    {
                        if (xmlHttp.responseText.indexOf("session_expired") != -1)
                        {
                            window.location.href = context + "/session_expired.do";
                        }
                        else
                        {
                            res = trimS(xmlHttp.responseText);
                            //  alert(res);
                            if(res==""){
                                //alert("Vendor Code does not exist.");
                                alert(valid_vendorCode_val_msg);
                                document.getElementById("VendorCodeDesc"+j+"-"+i).value="";
                                document.getElementById("VendorCodeAdmin"+j+"-"+i).value="";
                                document.getElementById("VendorCodeAdmin"+j+"-"+i).focus();
                                document.getElementById("ValidVendorCode"+j+"-"+i).value="Invalid";
                                return false;
                            }
                            else{
                                document.getElementById("VendorCodeDesc"+j+"-"+i).value=res.split("@@")[1];
                                document.getElementById("ValidVendorCode"+j+"-"+i).value="valid";
                                return true;
                            }
                        }
                    }
                }

                function validateWarranty(){

                   // var claimCount=document.getElementById("ClaimedCount").value;
                   // var count=document.getElementById("PartCount").value;
                     var claimCount = parseInt(document.getElementById("ClaimedCount").value, 10);
                   
                    for(var j=1;j<=claimCount;j++){
                         
                    	 var partCountElement = document.getElementById("PartCount" + j);
                         var count = partCountElement ? parseInt(partCountElement.value, 10) : 0;
                         console.log("PartCount", j, ":", count);
                        for( var i=1;i<=count;i++){
                        	
                        		 
                        	/*if(document.getElementById("partCodeFailed"+j+"-"+i).value == undefined || document.getElementById("partCodeFailed"+j+"-"+i).value=== ''){
                        		//document.getElementById("partCodeFailed"+j+"-"+i).focus();
                        		//alert("Failed Part Code is Mandatory");
                            	//return false;
                        	}*/
                        	 

                            //  alert(parseInt(document.getElementById("Qty"+j+"-"+i).value));
                            // alert(parseInt(document.getElementById("QtyApproved"+j+"-"+i).value));
                            //  alert(parseInt(document.getElementById("QtyRejected"+j+"-"+i).value));
                            if(document.getElementById("QtyApproved"+j+"-"+i)!=undefined){
                                if(document.getElementById("QtyApproved"+j+"-"+i).value=="" )
                                {
                                    document.getElementById("QtyApproved"+j+"-"+i).focus();
                                    document.getElementById("msg_saveFAILED").style.display = "";
                                    // document.getElementById("msg_saveFAILED").innerHTML="Please Enter Value in Qty Approved Field.";
                                    alert(not_blank_validation_msg+'Qty Approved');
                                    return false;
                                }

                                if(parseFloat(document.getElementById("Qty"+j+"-"+i).value)!=((parseFloat(document.getElementById("QtyApproved"+j+"-"+i).value))+ (parseFloat(document.getElementById("QtyRejected"+j+"-"+i).value))) )
                                {
                                    return false;
                                }
                                if(  (parseFloat(document.getElementById("QtyApproved"+j+"-"+i).value)<parseFloat(document.getElementById("Qty"+j+"-"+i).value)) &&  (parseFloat(document.getElementById("QtyApproved"+j+"-"+i).value)!=parseFloat(document.getElementById("Qty"+j+"-"+i).value))   ){
                                    if((document.getElementById("Reason For Rejection"+j+"-"+i).value=="")){
                                        document.getElementById("Reason For Rejection"+j+"-"+i).focus();
                                        document.getElementById("msg_saveFAILED").style.display = "";
                                        //document.getElementById("msg_saveFAILED").innerHTML="Please Select Value in Reason For Rejection Field.";
                                        alert(not_blank_dropdown_validation_msg+'Reason For Rejection');
                                        return false;
                                    }
                                }

                                if(document.getElementById("ScrapValue"+j+"-"+i).value==""){
                                     /*if((document.getElementById("VendorCodeAdmin"+j+"-"+i).value=="")){
                                        //document.getElementById("VendorCodeAdmin"+j+"-"+i).focus();
                                        //alert(not_blank_validation_msg+' Vendor');
                                        //return false;
                                    } */

                                    var vCode=document.getElementById("VendorCodeAdmin"+j+"-"+i).value;
                                    checkVendorCode(vCode,j,i)
                                    if(document.getElementById("ValidVendorCode"+j+"-"+i).value=="Invalid"){
                                        return false;
                                    }
                                }
                            }
                        }
                    }
                      
                    var answer=confirm("Do you really want to Approve?");
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
                
                function downloadfile(filename)
                {
                    var url=contextPath+"/DOCUMENTS/WARRANTY/"+filename;
                    var win=window.open(url, '_blank',"directories=no,height=600,width=600,menubar=no,resizable=yes,scrollbars=yes,status=no,titlebar=no,top=0,location=no");
                    win.focus();

                }
    <%--function calcGstOnIns(val){


        if(val==''){
            document.getElementById("lessAmmount3").value ="0";
        }
        var totalAmmount = document.getElementById("GrandTotalApproveAmmount").value ;
        var lessAmmount= document.getElementById("lessAmmount1").value;
        var lessAmmount1= document.getElementById("lessAmmount2").value;
        var insurCharge= document.getElementById("lessAmmount3").value ;
        var GstInsurpercent=document.getElementById("Discount4").value ;
        var GstOnInsurCharge=((insurCharge * GstInsurpercent )/100);
        document.getElementById("lessAmmount4").value =GstOnInsurCharge.toFixed(2);
        var totalAfterLess=parseFloat(document.getElementById("GrandTotalApproveAmmount").value)+parseFloat(lessAmmount)+parseFloat(lessAmmount1)+parseFloat(insurCharge)+parseFloat(GstOnInsurCharge);
        document.getElementById("TotalValue").value =totalAfterLess.toFixed(2);
    }--%>

        function getLabourCharge(taxable,totalGst){

            var todate = new Date().getTime();
            var claimDate=document.getElementById("claimDate").value;
            var strURL = '<%=cntxpath%>/warrantyAction.do?option=getLabourCharge&taxable=' + taxable + '&claimDate=' + claimDate + '&tm=' + todate;
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                stateChangedLabourCharge(xmlHttp,totalGst);
            };
            xmlHttp.open("GET", strURL, false);
            xmlHttp.send(null);

        }
        function stateChangedLabourCharge(xmlHttp,totalGst)
        {
            var context = '<%=cntxpath%>';
            var res = null;
            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
            {
                if (xmlHttp.responseText.indexOf("session_expired") != -1)
                {
                    window.location.href = context + "/session_expired.do";
                }
                else
                {
                    res = trimS(xmlHttp.responseText);
                    document.getElementById("lessAmmount3").value=res;
                    var gstPercent =(parseFloat(totalGst)).toFixed(2);
                    var gstval =(parseFloat(res*parseFloat(gstPercent)/100)).toFixed(2);
                    document.getElementById("lessAmmount4").value=gstval;
                    
                    return true;
                
                }
            }
        }
</script>

<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.warranty.warranty" /></a></li>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/warrantyAction.do?option=viewPendingWarrantyClaim'><bean:message key="label.warranty.pendingWarrantyClaim" /></a></li>
            <li class="breadcrumb_link"><bean:message key="label.warranty.approveWarrantyClaim" /></li>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1><bean:message key="label.warranty.approveWarrantyClaim" /></h1>
                <form action="<%=cntxpath%>/warrantyAction.do" method="post" id="warrantyForm" enctype="multipart/form-data" onsubmit="return false;">
                    <input type="hidden" name="option" value="saveApproveWarranty" >
                    <input type="hidden" name="upperLink" value="<a href ='<%=cntxpath%>/initWarranty.do'><bean:message key="label.warranty.warranty" /></a>@@<a href = '<%=cntxpath%>/warrantyAction.do?option=viewPendingWarrantyClaim'><bean:message key="label.warranty.pendingWarrantyClaim" /></a>@@<bean:message key="label.warranty.approveWarrantyClaim" />"/>
                    <table width="100%" border="0" align="left" cellpadding="1" cellspacing="1" >
                        <input type="hidden" name="vinid" value="${warrantyForm.vinid}">
                        <input type="hidden" name="flag" value="${flag}">
                        <input type="hidden" name="claimDate" id="claimDate" value="${warrantyForm.claimDate}">\
                        <input type="hidden" name="dealer_code" value="${warrantyForm.dealer_code}">
                        <tr>
                            <td colspan="6">
                                <table width="100%" border="0" cellspacing="3" cellpadding="3" align="left">
                                    <tr bgcolor="#eeeeee" height="20" >
                                        <td colspan="6" class="headingSpas" align="left" >
                                            <label>
                                                <B><bean:message key="label.common.customerinfo" /></B>
                                            </label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="12%" align="right" ><label><bean:message key="label.common.dealerName" /> :</label></td>
                                        <td width="12%" align="left">${warrantyForm.dealerName} [${warrantyForm.dealer_code}]
                                        </td>
                                        <td width="12%" align="right"><label><bean:message key="label.common.CustomerNameCaps" />:</label></td>
                                        <td  width="14%" align="left">${warrantyForm.customerName}</td>
                                        <td width="12%"></td>
                                        <td width="12%"></td>
                                    </tr>
                                    <tr>
                                        <td align="right"><label><bean:message key="label.common.location" /> :</label></td>
                                        <td align="left">${warrantyForm.dealerLocation}
                                        </td>
                                        <td align="right"><label><bean:message key="label.delivery.Address" /> :</label></td>
                                        <td align="left">
                                            ${warrantyForm.customerAddress}  ${warrantyForm.cusTehsil} ${warrantyForm.cusDistrict} ${warrantyForm.cusState}</td>
                                        <td></td>
                                        <td></td>
                                    </tr>
                                    <tr>
                                        <td align="right"></td>
                                        <td align="left">
                                        </td>
                                        <td align="right"><label><bean:message key="label.common.PinCode" /> :</label></td>
                                        <td align="left">${warrantyForm.cusPincode} </td>
                                        <td></td>
                                        <td></td>
                                    </tr>

                                    <tr>
                                        <td align="right"><label><bean:message key="label.warranty.warrantyClaimNo" /> :</label></td>
                                        <td align="left">${warrantyForm.warrantyClaimNo}</td><input type="hidden" name="warrantyClaimNo" value="${warrantyForm.warrantyClaimNo}">

                                    <td align="right"><label ><bean:message key="label.warranty.claimDate" /> :</label></td>
                                    <td align="left">${warrantyForm.claimDate}</td>

                                    <td align="right"><label><bean:message key="label.warranty.viewFailedPartFile" /> :</label></td>
                                    <td   align="left" class="headingSpas" style="white-space: nowrap" >&nbsp;
                                        <%
                                                    String s1 = getServletContext().getRealPath("/") + "/DOCUMENTS/WARRANTY/" + value;
                                                    File f1 = new File(s1);
                                                    if (f1.exists()) {
                                        %>

                                        <img src='${pageContext.request.contextPath}/image/download.jpg' border='0' alt='Download File'  title="Click here view failed part" onclick="downloadfile('${warrantyForm.failedPartFileName1}')"/>
                                        <% } else {%>
                                        &nbsp;
                                        <% }%>

                                    </td>
                        </tr>
                        <tr>
                            <td align="right"><label><bean:message key="label.warranty.TMSRefNo" /> :</label></td>
                            <td align="left">${warrantyForm.tmsRefNo}</td>
                            <td align="right"><label class="red"><bean:message key="label.common.Modelfamily" /> :</label></td>
                            <td align="left">${warrantyForm.modelFamily}</td>
                            <td align="right"><label><bean:message key="label.common.Modelfamilydesc" /> :</label></td>
                            <td align="left">${warrantyForm.modelFamilyDesc}</td>
                        </tr>

                        <tr>
                            <td align="right"><label><bean:message key="label.catalogue.chassisNo" /></label> :</td>
                            <td align="left">${warrantyForm.vinNo}
                            </td>
                            <td align="right"><label><bean:message key="label.warranty.modelNo" /> :</label></td>
                            <td align="left">${warrantyForm.modelNo}
                            </td>
                            <td align="right"><label ><bean:message key="label.catalogue.modelDesc" /> :</label></td>
                            <td align="left">${warrantyForm.modelDesc}</td>
                        </tr>
                        <tr>

                            <td align="right"><label><bean:message key="label.common.engineno" /></label> :</td>
                            <td align="left">${warrantyForm.engineNo}</td>
                            <td align="right"><label ><bean:message key="label.warranty.dateofFailure" /> :</label></td>
                            <td align="left">${warrantyForm.failureDate}</td>

                            <td align="right"><label class="red"><bean:message key="label.warranty.dateofDelivery" /> :</label></td>
                            <td align="left">${warrantyForm.deliveryDate}</td>
                        </tr>
                        <tr>
                            <td align="right"><label><bean:message key="label.common.jobcardno" /> :</label></td>
                            <td align="left">${warrantyForm.jobCardNo}
                            </td>
                            <td align="right"><label><bean:message key="label.common.jobcardDate" /> :</label></td>
                            <td align="left">${warrantyForm.jobCardDate}</td>
                            <td align="right"><label><bean:message key="label.warranty.dateofReplacement" /> :</label></td>
                            <td align="left">${warrantyForm.replacementDate}
                            </td>
                        </tr>
                        <tr>
                            <td align="right"><label><bean:message key="label.common.hrm" /> :</label></td>
                            <td align="left" >${warrantyForm.hours} </td>
                            <td align="right">&nbsp;</td>
                            <td   align="left" class="headingSpas"  >&nbsp;
                            </td>
                            <td align="right">&nbsp;</td>
                            <td   align="left" class="headingSpas"  >&nbsp;
                            </td>
                        </tr>

                    </table>
                    <table width="100%" border="0" align="left" cellpadding="1" cellspacing="1" >

                        <tr>

                            <td colspan="6">
                                <div style="width:100%;  overflow: auto;">
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc >
                                        <tr bgcolor="#eeeeee" height="15">
                                            <th width="10%" align="center" style="padding-left:5px; padding-right: 5px"><bean:message key="label.warranty.freeService" /></th>
                                            <%--  <th width="10%" align="center" style="padding-left:5px; padding-right: 5px"><bean:message key="label.common.pdi" /> </th>
                                              <th width="10%" align="center" style="padding-left:5px; padding-right: 5px"><bean:message key="label.common.installation" /> </th>
                                            --%>
                                            <c:forEach var="serviceMap" items="${serviceMap}" begin="0" end="${fn:length(serviceMap)}">
                                                <th> ${serviceMap.jobTypeDesc} </th>
                                            </c:forEach>
                                        </tr>
                                        <tr bgcolor="#FFFFFF" height="15">
                                            <td>HRS</td>
                                            <%--<td>- -</td>--%>
                                            <%--<td>${warrantyForm.hmr}</td>--%>
                                            <c:forEach var="serviceMap" items="${serviceMap}" begin="0" end="${fn:length(serviceMap)}">
                                                <td>${serviceMap.hmr}</td>
                                            </c:forEach>

                                        </tr>

                                        <tr bgcolor="#FFFFFF" height="15">
                                            <td>Date</td>
                                            <%--<td>${warrantyForm.pdiDate}</td>
                                            <td>${warrantyForm.insDate}</td>--%>
                                            <c:forEach var="serviceMap" items="${serviceMap}" begin="0" end="${fn:length(serviceMap)}">
                                                <td>${serviceMap.jobCardDate}</td>
                                            </c:forEach>
                                        </tr>
                                    </table>
                                </div>

                            </td>

                        </tr>
                    </table>

                    <tr>
                        <td colspan="6">
                            <table width="100%" border="0" cellspacing="3" cellpadding="3" align="left">
                                <tr bgcolor="#eeeeee" height="20" >
                                    <td colspan="6" style="padding-left: 5px" class="headingSpas" align="left" >
                                        <label >
                                            <B><bean:message key="label.warranty.claimComplaintDetails" /> </B>
                                        </label>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>


                    <table width="100%" border="0" align="left" cellpadding="1" cellspacing="1" >

                        <c:if test="${!empty warrantyForm.dataMap}">
                            <c:set var="j" value="1"/>
                            <c:set var="sumAllApprovedAmt" value="0"/>
                            <c:set var="appHanCharge" value="0"/>

                            <c:set var="gstOnHandling" value="0"/>
                            <c:set var="insuranceCharges" value="0"/>
                            <c:set var="gstOnInsurance" value="0"/>
                            <c:set var="approveAmm" value="0"/>
                            <c:forEach var="claimComplaintList" items="${warrantyForm.dataMap}">
                                <tr>
                                    <td colspan="6">
                                        <table width="100%" border="0" cellspacing="3" cellpadding="3" align="left">
                                            <tr>
                                                <td align="left">
                                                    <label >
                                                        <B><bean:message key="label.common.complaint" /> ${j}</B>
                                                        <input type="hidden" name="cmpNoArr" value="${claimComplaintList.key.cmpNo}">
                                                    </label>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td align="right"><label><bean:message key="label.common.customerverbatim" /> :</label></td>
                                                <td align="left"><textarea readonly="readonly">${claimComplaintList.key.cusVerbatim}</textarea>
                                                </td>
                                                <td align="right"><label><bean:message key="label.warranty.formanObservation" /> :</label></td>
                                                <td align="left"><textarea readonly="readonly">${claimComplaintList.key.formanObser}</textarea></td>
                                                <td align="right"></td>
                                                <td align="left"></td>

                                            </tr>

                                            <tr>
                                                <td align="right"><label class="red"><bean:message key="label.common.Application" /> :</label></td>
                                                <td align="left">${claimComplaintList.key.appDesc}</td>
                                                <td align="right"><label><bean:message key="label.common.aggregate" /> :</label></td>
                                                <td align="left">${claimComplaintList.key.aggDisc}
                                                </td>
                                                <td align="right"><label><bean:message key="label.warranty.subAggregate" /> :</label></td>
                                                <td align="left">${claimComplaintList.key.subAggDesc}</td>
                                            </tr>

                                            <tr>
                                                <td align="right"><label class="red"><bean:message key="label.warranty.subAssembly" /> :</label></td>
                                                <td align="left">${claimComplaintList.key.subAssemDesc}</td>
                                                <td align="right"><label><bean:message key="label.common.defect" /> :</label></td>  <%--label.common.complaint--%>
                                                <td align="left">${claimComplaintList.key.complaintDesc}
                                                </td>
                                                <td align="right"></label></td>
                                                <td align="left"></td>

                                            </tr>
                                        </table>
                                    </td>
                                </tr>


                                <tr>
                                    <td colspan="6">
                                        <table width="100%" border="0" cellspacing="3" cellpadding="3" align="left">
                                            <tr bgcolor="#eeeeee" height="20" >
                                                <td colspan="6" style="padding-left: 5px" class="headingSpas" align="left" >
                                                    <label >
                                                        <B><bean:message key="label.warranty.claimedPartAganistComplaint" /></B>
                                                    </label>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>


                            <div style="width:100%;  overflow: auto;">

                                <table  border="0" cellspacing="3" cellpadding="3" width="100%" align="left">
                                    <tr >
                                        <th  align="center"><bean:message key="label.warranty.S.No" /></th>
                                        <%--<th align="left" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.warranty.defectDescription" /></th>--%>
                                        <th  align="center"><bean:message key="label.warranty.causalConsequential" /></th>
                                        <th  align="center"><bean:message key="label.catalogue.partCode1" /></th>
                                        <th  align="center"><bean:message key="label.common.partDesc" /></th>
                                        <th  align="center"><bean:message key="label.catalogue.partCode2" /></th>
                                        <th  align="center"><bean:message key="label.warranty.venderCode" /></th>
                                        <th  align="center"><bean:message key="label.warranty.vendorDesc" /></th>
                                        <th  align="center"><bean:message key="label.common.Qty" /></th>
                                        <th  align="center"><bean:message key="label.common.packingNo" /></th>
                                        <%--<th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.warranty.bixNo" /></th>--%>
                                        <th  align="center"><bean:message key="label.warranty.dispatch" /></th>
                                        <th  align="center"><bean:message key="label.warranty.dispatchQty" /></th>
                                        <th  align="center"><bean:message key="label.saleReturn.ReceivedQty" /></th>
                                        <th  align="center"><bean:message key="label.common.unitPrice" /></th>
                                        <th  align="center"><bean:message key="label.warranty.qtyApproved" /> </th>
                                        <th  align="center"><bean:message key="label.warranty.qtyRejected" /></th>


                                        <th  align="center"><bean:message key="label.warranty.approvedAmmount" /></th>
                                        <th  align="center"><bean:message key="label.warranty.reasonforRejection" /></th>
                                        <th  align="center"><bean:message key="label.common.remarks" /></th>
                                        <th  align="center"><bean:message key="label.warranty.scrap" /></th>



                                        <c:if test="${warrantyForm.flag ne 'true'}">
                                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap ><bean:message key="label.common.hsnCode" /></th>
                                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.dealerDiscount" /></th>
                                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap ><bean:message key="label.common.taxableValue" /></th>
                                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.cgstRate" /></th>
                                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.cgstAmt" /></th>
                                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.sgstRate" /></th>
                                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.sgstAmt" /></th>
                                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.ugstRate" /></th>
                                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.ugstAmt" /></th>
                                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.igstRate" /></th>
                                            <th align="center" style="padding-left:5px;padding-right: 5px" nowrap><bean:message key="label.common.igstAmt" /></th>
                                        </c:if>


                                        <%--<th  align="center"><bean:message key="label.warranty.claimValue" /></th>--%>
                                        <%--<th  align="center"><bean:message key="label.common.totalApprovedVal" /></th>--%>



                                    </tr>

                                    <c:set var="i" value="1"/>
                                    <c:set var="sumApprovedAmt" value="0"/>
                                    <c:set var="TTaxableValue" value="0"/>
                                    <c:set var="disc" value="0"/>
                                    <c:forEach items="${claimComplaintList.value}" var="compPartList">
                                        <tr>
                                            <td width="2%" align="center" class="tdgridnew1 " >${i}.</td>
                                        <input type="hidden" style="width:90px " name="jobSpareIDArr" id="jobSpareID" class="txtGeneralNW"  value="${compPartList.jobSpareID}" readonly="readonly" onkeydown="return false;"/>

                                        <%--<td   align="center"><input type="text"  name="complaintNoArr" id="Complaint No." class="txtGeneralNW" value="${compPartList.compDesc}" title="${compPartList.compDesc}" readonly="readonly" onkeydown="return false;"/></td>--%>
                                        <td   align="center">
                                            <select name="causalOrConseqArr" id="textbox" class="txtGeneralNW" >
                                                <%--     <input type="text"  name="causalOrConseqArr" id="textbox" class="txtGeneralNW" value="${compPartList.causalOrConseq}" title="${compPartList.causalOrConseq}" readonly="readonly" onkeydown="return false;"/></td>--%>
                                                <c:if test="${compPartList.causalOrConseq eq 'Causal'}">
                                                    <option value="Causal" selected >Causal</option>
                                                    <option value="Consequential" >Consequential</option>
                                                </c:if>

                                                <c:if test="${compPartList.causalOrConseq eq 'Consequential'}">
                                                    <option value="Causal" >Causal</option>
                                                    <option value="Consequential" selected >Consequential</option>
                                                </c:if>


                                                <c:if test="${compPartList.causalOrConseq ne 'Consequential' && compPartList.causalOrConseq ne 'Causal'}">
                                                    <option value="Causal" >Causal</option>
                                                    <option value="Consequential" >Consequential</option>
                                                </c:if>
                                            </select>
                                        </td>

                                        <td   align="center" ><input type="text"  name="partNoArr" id="Part No" class="textbox" value="${compPartList.partNo}" title="${compPartList.partNo}" readonly="readonly" onkeydown="return false;"/></td>
                                        <td  align="center" ><input type="text"  name="partDescArr" id="Part Description"  class="partDescInput" value="${compPartList.partDesc}" title="${compPartList.partDesc}" readonly="readonly" onkeydown="return false;"/></td>
                                        
                                        <td  width="6%" align="center" ><input type="text" style="width:90px " name="partCodeFailed" size="30" id="partCodeFailed${j}-${i}" class="txtGeneralNW" value="" maxlength="15" />
                                            <input type="hidden" name="" value="" id="partCodeFailed${j}-${i}">
                                        </td>
                                        
                                        <td  width="6%" align="center" ><input type="text" style="width:90px " name="vendorCodeAdmin" size="30" id="VendorCodeAdmin${j}-${i}" class="txtGeneralNW" value="" maxlength="15" onblur="checkVendorCode(this.value,${j},${i})"/>
                                            <input type="hidden" name="" value="" id="ValidVendorCode${j}-${i}">
                                        </td>
                                        <td  width="6%" align="center" ><input type="text" style="width:90px " name="vendorCodeDesc" size="30" id="VendorCodeDesc${j}-${i}" class="txtGeneralNW" value="" readonly/></td>

                                        <td   align="center" ><input type="text"  name="qtyArr" id="Qty${j}-${i}" class="textbox"  value="${compPartList.qty}" title="${compPartList.qty}" readonly="readonly" onkeydown="return false;"/></td>
                                        <td  align="center" ><input type="text"  name="packingNo" id="PackingNo${j}-${i}" class="textbox" value="${compPartList.packingNo}" title="${compPartList.packingNo}" readonly="readonly" onkeydown="return false;"/></td>
                                            <%--<td  align="center" ><input type="text"   name="boxNoArr" id="boxNo${i}" class="textbox" value="${compPartList.boxNo}" title="${compPartList.boxNo}" readonly="readonly" onkeydown="return false;"/></td>--%>
                                        <td  align="center" ><input type="text"  name="dispatchStatus" id="DispatchStatus${j}-${i}" class="textbox" value="${compPartList.dispatchStatus}" title="${compPartList.dispatchStatus}" readonly="readonly" onkeydown="return false;"/></td>
                                        <td  align="center" ><input type="text"  name="dispatchQty" id="DispatchQty${j}-${i}" class="textbox" value="${compPartList.dispatchQty}" title="${compPartList.dispatchQty}" readonly="readonly" onkeydown="return false;"/></td>
                                        <td  align="center" ><input type="text"  name="receivedQty" id="ReceivedQty${j}-${i}" class="textbox" value="${compPartList.receivedQty}" title="${compPartList.receivedQty}" readonly="readonly" onkeydown="return false;"/></td>
                                        <td  align="center" ><input type="text" style="text-align: right; " name="unitPriceArr" id="Unit Price${j}-${i}" class="textbox" value="${compPartList.unitPrice}" title="${compPartList.unitPrice}" readonly="readonly" onkeydown="return false;"/></td>
                                        <td  align="center" ><input type="text"  name="qtyApprovedArr" id="QtyApproved${j}-${i}" class="textbox" title=""  value="${compPartList.qty}" onkeyup ="return validD(this,'${i}',${j})"/></td>
                                        <td  align="center" ><input type="text"  name="qtyRejectedArr" id="QtyRejected${j}-${i}" class="textbox" title="" value="0" readonly="readonly" onkeydown="return false;"   /></td>

                                        <td  align="center" ><input type="text" style="text-align: right; " name="ammountArr" id="ApproveAmmount${j}-${i}" class="textbox" value="${compPartList.amount}" title="${compPartList.amount}" readonly="readonly" onkeydown="return false;"/></td>
                                        <td  align="center" >
                                            <select name="rejectionCode" id="Reason For Rejection${j}-${i}" class="selectnewbox" >
                                                <option value="">--Select--</option>
                                                <c:forEach items="${rejectionList}" var="rejectionList">
                                                    <option value='${rejectionList.value}' title='${rejectionList.label}'>${rejectionList.label}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td  width="6%" align="center" ><input type="text" style="width:90px " name="remarks" size="30" id="Remarks${i}" class="txtGeneralNW" value="" /></td>

                                        <td  width="6%" align="center" >
                                            <select name="scrapValue" id="ScrapValue${j}-${i}" class="selectnewbox" ><%--onchange="scrapChange(this.value,${j},${i});"--%>
                                                <option value="">--Select--</option>
                                                <option value="SCRAP">SCRAP</option>
                                                <option value="INSPECTION">INSPECTION</option>

                                            </select>
                                        </td>
                                        <c:if test="${warrantyForm.flag ne 'true'}">
                                            <td align="left" ><input type="text" style="width:90px;text-align: right; " name="hsnNoArr" id="hsnNo${j}-${i}" class="txtGeneralNW" value="${compPartList.hsnCode}"  readonly="readonly"   onkeydown="return false;"/></td>
                                            <td align="left" ><input type="text" style="width:90px;text-align: right; " name="dealerDiscountArr" id="dealerDiscount${j}-${i}" class="txtGeneralNW" value="${compPartList.dealerDiscount}"  readonly="readonly"   onkeydown="return false;"/></td>
                                            <td align="left" ><input type="text" style="width:90px;text-align: right; " name="taxableValueArr" id="taxableValue${j}-${i}" class="txtGeneralNW" value="${compPartList.taxableValue}"  readonly="readonly"   onkeydown="return false;"/></td>

                                            <td align="left" ><input type="text" style="width:90px;text-align: right; " name="cgstRateArr" id="cgstRate${j}-${i}" class="txtGeneralNW" value="${compPartList.cgstRate}"  readonly="readonly"   onkeydown="return false;"/></td>
                                            <td align="left" ><input type="text" style="width:90px;text-align: right; " name="cgstAmtArr" id="cgstAmt${j}-${i}" class="txtGeneralNW" value="${compPartList.cgstAmt}"  readonly="readonly"   onkeydown="return false;"/></td>
                                            <td align="left" ><input type="text" style="width:90px;text-align: right; " name="sgstRateArr" id="sgstRate${j}-${i}" class="txtGeneralNW" value="${compPartList.sgstRate}"  readonly="readonly"   onkeydown="return false;"/></td>
                                            <td align="left" ><input type="text" style="width:90px;text-align: right; " name="sgstAmtArr" id="sgstAmt${j}-${i}" class="txtGeneralNW" value="${compPartList.sgstAmt}"  readonly="readonly"   onkeydown="return false;"/></td>
                                            <td align="left" ><input type="text" style="width:90px;text-align: right; " name="ugstRateArr" id="ugstRate${j}-${i}" class="txtGeneralNW" value="${compPartList.ugstRate}"  readonly="readonly"   onkeydown="return false;"/></td>
                                            <td align="left" ><input type="text" style="width:90px;text-align: right; " name="ugstAmtArr" id="ugstAmt${j}-${i}" class="txtGeneralNW" value="${compPartList.ugstAmt}"  readonly="readonly"   onkeydown="return false;"/></td>
                                            <td align="left" ><input type="text" style="width:90px;text-align: right; " name="igstRateArr" id="igstRate${j}-${i}" class="txtGeneralNW" value="${compPartList.igstRate}"  readonly="readonly"   onkeydown="return false;"/></td>
                                            <td align="left" ><input type="text" style="width:90px;text-align: right; " name="igstAmtArr" id="igstAmt${j}-${i}" class="txtGeneralNW" value="${compPartList.igstAmt}"  readonly="readonly"   onkeydown="return false;"/></td>
                                            </c:if>



                                        <td  align="center" ><input type="hidden" style="text-align: right; " name="approveAmmountArr" id="Claim Value${j}-${i}" class="textbox" value="${compPartList.taxableValue}" title="${compPartList.taxableValue}" readonly="readonly" onkeydown="return false;"/></td>
                                            <%--<td  align="center" ><input type="text" style="text-align: right; " name="totalApprovedValueArr" id="Total Approved Value${j}-${i}" class="textbox" value="${compPartList.claimValue}" title="${compPartList.claimValue}" readonly="readonly" onkeydown="return false;"/></td>--%>


                                        </tr>

                                        <c:set var="i" value="${i+1}"/>
                                        <c:set var="sumApprovedAmt" value="${sumApprovedAmt+compPartList.claimValue}"/>
                                        <c:set var="TTaxableValue" value="${compPartList.taxableValue}"/>
                                    </c:forEach>
                                    <c:set var="k" value="0"/>
                                    <c:forEach items="${otherPartList}" var="otherPartList">
                                        <c:set property="totalTaxableValueStr" var="totalTaxableValueStr" value="${otherPartList.totalTaxableValue}"/>
                                        <c:set property="totalTaxValueStr" var="totalTaxValueStr" value="${otherPartList.totalTaxValue}"/>
                                        <c:set property="totalInvoiceAmountStr" var="totalInvoiceAmountStr" value="${otherPartList.totalInvoiceAmount}"/>
                                        
                                        <c:if test="${k eq 0}">
                                            <c:set var="appHanCharge" value="${otherPartList.appHanCharge}"/>
                                            <c:set var="gstOnHandling" value="${otherPartList.gstOnHandling}"/>
                                            <c:set property="disc" var="disc" value="${otherPartList.cgstRate+otherPartList.sgstRate+otherPartList.ugstRate+otherPartList.igstRate}"/>                                      
                                        </c:if>
                                        <c:if test="${k eq 1}">
                                            <c:set var="insuranceCharges" value="${otherPartList.insuranceCharges}"/>
                                            <c:set var="gstOnInsurance" value="${otherPartList.gstOnInsurance}"/>
                                        </c:if>
                                        <c:set var="k" value="${k+1}"/>
                                    </c:forEach>
                                </table>

                            </div>

                            <%-- <input type="hidden" name="partCount" id="PartCount" value="${i-1}"> --%>
                            
                           <input type="hidden" name="partCount[${j}]" id="PartCount${j}" value="${i-1}">
                            
                            <table width="100%" border="0" align="left" cellpadding="1" cellspacing="1" >
                                <tr>
                                    <td colspan="6" >
                                        <table width="100%" border="0" cellspacing="3" cellpadding="3" align="left">
                                            <tr >
                                                <%--<fmt:formatNumber var="sumApprovedAmt1" type = "number" groupingUsed = "false" value = "${totalTaxableValueStr}" />--%>
                                                <%--<td width="89%" align="right" class="tdgridnew1 "><B><bean:message key="label.warranty.total" /></B></td>--%>
                                                <td style="padding-left:27px" align="left" class="tdgridnew1 "><input type="hidden" name="totalApproveAmmount" id="TotalApproveAmmount${j}" class="txtGeneralNW" style="width:90px;text-align: right;" value="${TTaxableValue}" readonly="readonly" /></td>
                                            </tr>
                                        </table>
                                </tr>
                                <c:set var="j" value="${j+1}"/>
                                <c:set var="sumAllApprovedAmt" value="${sumAllApprovedAmt+sumApprovedAmt}"/>

                                <c:set var="approveAmm" value="${claimComplaintList.key.approveAmm}"/>
                            </c:forEach>

                            <input type="hidden" name="claimedCount" id="ClaimedCount" value="${j-1}">
                            <input type="hidden" name="sumTotalApproveAmmount" id="GrandTotalApproveAmmount" class="txtGeneralNW" style="width:90px;text-align: right;" value="${TTaxableValue}" readonly="readonly" />



                            <tr>
                                <td colspan="9">
                                    <table border="0" cellspacing="3" cellpadding="3" width="100%">
                                        <c:set var="t" value="1"/>
                                        <c:forEach items="${taxList}" var="taxList">
                                            <c:if test="${taxList.typeDescription eq 'Handling Charges'}">
                                                <tr>
                                                    <td width="89%" align="right" class="tdgridnew1 "> ${taxList.typeDescription} (${taxList.typePercentage} %) <input type="hidden" name="discount" id="Discount${t}" value="${taxList.typePercentage}">        </td>
                                                    <td align="center" class="tdgridnew1 "><input type="text" name="lessAmmountArr" id="lessAmmount${t}" class="txtGeneralNW" value="${appHanCharge}" readonly="readonly" style="width:90px;text-align: right; " onkeydown="return false;"/></td>
                                                </tr>
                                            </c:if>
                                            <c:if test="${taxList.typeDescription eq 'GST on Handling Charges'}">
                                                <tr>
                                                    <td width="89%" align="right" class="tdgridnew1 "> ${taxList.typeDescription}  <input type="hidden" name="discount" id="Discount${t}" value="${disc}">        </td><%--(${taxList.typePercentage} %)--%>
                                                    <td align="center" class="tdgridnew1 "><input type="text" name="lessAmmountArr" id="lessAmmount${t}" class="txtGeneralNW" value="${gstOnHandling}" readonly="readonly" style="width:90px;text-align: right; " onkeydown="return false;"/></td>
                                                </tr>
                                            </c:if>
                                            <c:if test="${taxList.typeDescription eq 'GST on Insurance Charges'}">
                                                <tr>
                                                    <td width="89%" align="right" class="tdgridnew1 "> Labour Charges <input type="hidden" name="discount" id="Discount${t}" value="${taxList.typePercentage}"></td>
                                                    <td align="center" class="tdgridnew1 "><input type="text" name="lessAmmountArr" id="lessAmmount${t}" class="txtGeneralNW" value="${insuranceCharges}"  readonly="readonly" style="width:90px;text-align: right;"  onkeydown="return false;"/></td> <%--onblur="calcGstOnIns(this.value);" onkeyup="calcGstOnIns(this.value);"--%>
                                                </tr>
                                                <c:set var="t" value="${t+1}"/>
                                                <tr>
                                                    <td width="89%" align="right" class="tdgridnew1 ">  <input type="hidden" name="discount" id="Discount${t}" value="${disc}">GST on Labour Charges    <%--${taxList.typeDescription} (${taxList.typePercentage} %)--%></td>
                                                    <td align="center" class="tdgridnew1 "><input type="text" name="lessAmmountArr" id="lessAmmount${t}" class="txtGeneralNW" value="${gstOnInsurance}" readonly="readonly" style="width:90px;text-align: right; " onkeydown="return false;"/></td>
                                                </tr>

                                            </c:if>

                                            <c:set var="t" value="${t+1}"/>
                                        </c:forEach>
                                        <input type="hidden" name="taxCount" id="TaxCount" value="${t-1}">
                                        <tr>


                                            <td width="89%" align="right" class="tdgridnew1 "><B><bean:message key="label.common.totaltaxablevalue" /></B></td><%--<bean:message key="label.warranty.total" />  <bean:message key="label.warranty.Sum" /> --%>
                                            <td align="center" class="tdgridnew1 ">
                                                <input type="text" name="sumTotalApproveAmmount" id="GTotalApproveAmmount" class="txtGeneralNW" style="width:90px;text-align: right;" value="${totalTaxableValueStr}" readonly="readonly" />
                                            </td>
                                        </tr>


                                        <tr>


                                            <td width="89%" align="right" class="tdgridnew1 "><B><bean:message key="label.common.totaltaxvalue" /></B></td><%--<bean:message key="label.warranty.total" />  <bean:message key="label.warranty.Sum" /> --%>
                                            <td align="center" class="tdgridnew1 ">
                                                <input type="text" name="totalTaxAmmount" id="totalTaxAmmount" class="txtGeneralNW" style="width:90px;text-align: right;" value="${totalTaxValueStr}" readonly="readonly" />
                                            </td>

                                        </tr>
                                        <tr>
                                            <td  align="right" class="tdgridnew1 " width="60%"><b><bean:message key="label.common.GrandTotal" /></b></td>
                                            <td align="center" class="tdgridnew1 "><input type="text" name="approveAmmountAfterDiscount" id="TotalValue" class="txtGeneralNW" value="${totalInvoiceAmountStr}" readonly="readonly" style="width:90px ;text-align: right;" onkeydown="return false;"/></td>

                                        </tr>
                                    </table>
                                </td>
                            </tr>

                            <%--------------------------%>

                            <c:if test="${empty warrantyForm.dataMap}">
                                <tr bgcolor="#FFFFFF">
                                    <td valign="top" align="center" colspan="6"  style="padding-top:10px;color: red">
                                        Claim & Complaint Details Not Found.!
                                    </td>
                                </tr>
                            </c:if>
                        </table>

                    </c:if>

                    <c:if test="${!empty warrantyForm.dataMap}">


                        <div style="clear:both; margin:0 auto; text-align:center" >
                            <input name="input" type="button" value="Submit" id="btn" class="submit" onclick="validateWarranty()" >
                        </div>
                    </c:if>
                </form>
