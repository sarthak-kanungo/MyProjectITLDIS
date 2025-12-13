<%-- 
    Document   : addPartInNewOrder
    Created on : 05-Jan-2015, 12:32:03
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

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DATE);
            String con_country_name = (String) request.getParameter("con_country_name");
%>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_6.js"></script>
<script src="${pageContext.request.contextPath}/js/intermediate.js"></script>
<script type="text/javascript" language="javascript">


    var contextPath = '${pageContext.request.contextPath}';
    var noOfDays = '${noOfDays}';
    var ttdate="";
    var tmon="<%=month%>";
    var tdays="<%=day%>";

    if(eval(tmon)<10)
        tmon="0"+tmon;

    if(eval(tdays)<10)
        tdays="0"+tdays;

    ttdate=<%=year%>+'/'+tmon+'/'+tdays;

    var today = new Date(ttdate);
    var backDate= new Date(ttdate);
    backDate.setDate(backDate.getDate()-noOfDays);
    var ck_code = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
    var ck_qtyOrUnitPrice = /[' &\@\\\-\_\/\(\)\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;


    function partNotBlank(in_tbl_name){

        var count = (document.getElementById(in_tbl_name).rows.length);
        //alert(count)
        for(var k=1;k<=count;k++){
            if(document.getElementById("Part No." + k).value ==""){
                alert(please_Enter_msg+" Part No.");
                document.getElementById("Part No." + k).focus();
                return false;
            }
            else{
                //  alert("pb e")
                addRowSpares(in_tbl_name);
            }

        }

    }


    function addRowSpares(in_tbl_name)
    {
        var count = (document.getElementById(in_tbl_name).rows.length);//alert(count)
        var tbody = document.getElementById(in_tbl_name).getElementsByTagName("TBODY")[0];
        // create row
        var row = document.createElement("TR");
        row.height="20px";
        row.bgColor="#FFFFFF";
        var td0 = document.createElement("TD");

        td0.align ="center";
        var strHtml0 =(count);
        td0.innerHTML = strHtml0;

        var td1 = document.createElement("TD");
        td1.align="left";
        var strHtml1 = "<input type=\"text\" class=\"partNoTrim\" name=\"partNo\" id=\"Part No."+count+"\" value=\"\" maxlength=\"30\" onblur=\"checkDuplicatePartNo(this, '"+count+"');\" tabindex="+count+"/>"+
           "&nbsp;<img alt=\"\" src='${pageContext.request.contextPath}/image/next_1.gif' onclick=\"javascript:checkDuplicatePartNo(document.getElementById('Part No."+count+"'),'"+count+"');\" align=\"top\"/>"+
           "<img alt=''  style='visibility:hidden;' align=\"top\" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>"+
           "<input type=\"hidden\" name=\"partType\" id=\"PartType"+count+"\" value=\"\" maxlength=\"20\"/>";
        td1.innerHTML = strHtml1.replace(/!count!/g, count);

        var td2 = document.createElement("TD");
        td2.align="left";
        var strHtml2 = "<span id=\"Part Desc."+count+"\"></span><input name=\"partDesc\" id=\"desc"+count+"\" type=\"hidden\" value=\"\"/>";
        td2.innerHTML = strHtml2.replace(/!count!/g, count);

        var td4 = document.createElement("TD");
        td4.align="left";
        var strHtml4 = "<input type=\"text\" name=\"unitprice\" readonly  id=\"Unit Price"+count+"\" value=\"\" maxlength=\"4\"  style=\"width: 70px;text-align:right;\"/>";  
        td4.innerHTML = strHtml4.replace(/!count!/g, count);

        var td3 = document.createElement("TD");
        td3.align="left";
        var strHtml3 = "<input type=\"text\" name=\"moq\" id=\"MOQ"+count+"\" value=\"\" readonly maxlength=\"4\"  style=\"width: 50px;text-align:center;\"/>";  
        td3.innerHTML = strHtml3.replace(/!count!/g, count);

        var td5 = document.createElement("TD");
        td5.align="left";
        var strHtml5 = "<input type=\"text\" name=\"quantity\" id=\"Qty"+count+"\" value=\"\"  maxlength=\"7\"  style=\"width: 50px;\"  onblur=\"checkfloatvalue("+count+");CalculateAmount("+count+");\" tabindex="+count+"/>"+
            "<input type=\"hidden\" name=\"availQuantity\" id=\"AvailQty"+count+"\" value=\"\" maxlength=\"7\" style=\"width: 50px;\" />  ";
        td5.innerHTML = strHtml5.replace(/!count!/g, count);
 
        var td7 = document.createElement("TD");
        td7.align="left";
        var strHtml7 = "<input type=\"text\" name=\"amount\" id=\"Amount"+count+"\" value=\"\" readonly style=\"text-align: right;\"/>"+
                        "<input type=\"hidden\" id=\"service"+count+"\" name=\"serviceCheck\"/>";
        td7.innerHTML = strHtml7.replace(/!count!/g, count);

        row.appendChild(td0);
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        // row.appendChild(td6);
        row.appendChild(td7);
        tbody.appendChild(row);
        count = parseInt(count) + 1;
    }
    function deleteSparesRow(in_tbl_name)
    {
        var oRows = document.getElementById(in_tbl_name).getElementsByTagName('tr');
        var rowLength = oRows.length - 1;
        if (rowLength > 2) {
            document.getElementById(in_tbl_name).deleteRow(rowLength - 1);
        }
    }

    var sparex = new Array();
    var part_No = new Array();
    var partDesc = new Array();
    var unitPrice = new Array();
    
    function checkPartNo(obj,row){
        var part_No=obj.value;
        if(part_No != ""){
        var todate = new Date().getTime();
        var strURL = '<%=cntxpath%>/inventoryEXPAction.do?option=checkPartNo&partno=' + part_No.toUpperCase() + '&tm=' + todate;
        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete"){
            var result=xmlHttp.responseText;
            if(result == ""){
                alert(custinvalid_validation_msg+" Part no.");
                document.getElementById("Part No."+row).style.backgroundColor = "#FFC7C7";
                document.getElementById("Part Desc." + row).innerHTML = "";
                document.getElementById("desc" + row).value = "";
                document.getElementById("Unit Price" + row).value = "";
                document.getElementById("Qty" + row).value = "";
                document.getElementById("Amount"+row).value ="";
                document.getElementById("MOQ" + row).value ="";
                return false;
            }else{
                var result1=result.split("##");
                if(result1[1]==""){
                    alert(notServicable_OR_pricenotAvailable_msg);
                    document.getElementById("Part No."+row).style.backgroundColor = "#FFC7C7";
                document.getElementById("Part Desc." + row).innerHTML = "";
                document.getElementById("desc" + row).value = "";
                document.getElementById("Unit Price" + row).value = "";
                document.getElementById("Qty" + row).value = "";
                document.getElementById("Amount"+row).value ="";
                document.getElementById("MOQ" + row).value ="";
                }
            }
        }
        };
        xmlHttp.open("GET", strURL, true);
        xmlHttp.send(null);
        }
    }

    function getpartDesc(obj,row)
    {
        document.getElementById("msg_saveFAILED").innerHTML ="";

        obj.value = obj.value.toUpperCase();
        var part_No = obj.value; 
        if(part_No==""){
            return false;
        }

        if(ck_code.test(part_No)){
            obj.focus();
            alert(specialChar_validation_msg+" Part No.");
            obj.select();
            return false;
        }
        var todate = new Date().getTime();
        var strURL = '<%=cntxpath%>/inventoryEXPAction.do?option=getPriceByPartNo&partno=' + part_No.toUpperCase() + '&tm=' + todate;
        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete"){
            var result=xmlHttp.responseText;
            if(result != ''){
                var parts = result.split("##");
                var paintedParts = parts[0].split("@@");
                if(parts[0] != ""){
                    alert(paintedPart_msg+paintedParts);
                    document.getElementById("Part Desc." + row).innerHTML = "";
                    document.getElementById("desc" + row).value = "";
                    document.getElementById("Part No." + row).value = "";
                    document.getElementById("Part No." + row).title="";
                    document.getElementById("Unit Price" + row).value = "";
                    document.getElementById("Qty" + row).value = "";
                    document.getElementById("Amount"+row).value ="";
                    document.getElementById("MOQ" + row).value ="";
                    document.getElementById("Part No."+row).focus();
                    return false;
                }else if(parts[1] == ""){
                    alert(custinvalid_validation_msg+"Part No.");
                    document.getElementById("Part No."+row).value='';
                    document.getElementById("Part No."+row).focus();
                    return false;
                }else{
                    if(parts[2]!=""){
                        if(parts[2] == "notservicable"){
                            alert(notServicable_OR_pricenotAvailable_msg);
                            document.getElementById("Part Desc." + row).innerHTML = "";
                            document.getElementById("desc" + row).value = "";
                            document.getElementById("Part No." + row).value = "";
                            document.getElementById("Part No." + row).title="";
                            document.getElementById("Unit Price" + row).value = "";
                            document.getElementById("Qty" + row).value = "";
                            document.getElementById("Amount"+row).value ="";
                            document.getElementById("MOQ" + row).value ="";
                            document.getElementById("Part No."+row).focus();
                            return false;
                        }else{
                            var alternateparts=parts[2];
                            alert(alternatePart_msg+" ("+part_No.toUpperCase()+") : "+alternateparts);
                            document.getElementById("Part Desc." + row).innerHTML = "";
                            document.getElementById("desc" + row).value = "";
                            document.getElementById("Part No." + row).value = "";
                            document.getElementById("Part No." + row).title="";
                            document.getElementById("Unit Price" + row).value = "";
                            document.getElementById("Qty" + row).value = "";
                            document.getElementById("Amount"+row).value ="";
                            document.getElementById("MOQ" + row).value ="";
                            document.getElementById("Part No."+row).focus();
                            return false;
                        }
                    }else if(parts[3]==""){
                        alert(priceNotAvailable_msg);
                        document.getElementById("Part Desc." + row).innerHTML = "";
                        document.getElementById("desc" + row).value = "";
                        document.getElementById("Part No." + row).value = "";
                        document.getElementById("Part No." + row).title="";
                        document.getElementById("Unit Price" + row).value = "";
                        document.getElementById("Qty" + row).value = "";
                        document.getElementById("Amount"+row).value ="";
                        document.getElementById("MOQ" + row).value ="";
                        document.getElementById("Part No."+row).focus();
                        return false;
                    }else{
                        stateChangedPrice(xmlHttp, row, part_No);
                    }
                }
            }
            }
        };
        xmlHttp.open("GET", strURL, true);
        xmlHttp.send(null);
        }

    function stateChangedPrice(xmlHttp, row, partNos)
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
                var valArr = res.split('##')[3].split("@@");

                if (valArr.length > 0 && res != '')
                {
                    sparex = new Array();
                    part_No = document.getElementsByName("partNo")
                    partDesc = document.getElementsByName("partDesc")
                    unitPrice = document.getElementsByName("unitPrice")
                        
                    if(valArr[4]=="N"){
                        document.getElementById("service" + row).value = 'N';
                        document.getElementById("Part No." + row).style.backgroundColor = "#FFC7C7"
                        alert(notServicable_msg);
                        return false;
                    }else{
                        document.getElementById("Part No." + row).style.backgroundColor = "white"
                        document.getElementById("service" + row).value = 'Y';
                    }

                    if(valArr[2]==""){
                        alert(priceNotAvailable_msg);
                        document.getElementById("Part No." + row).value = "";
                        return false;
                    }
                    else{
                        document.getElementById("Part Desc." + row).innerHTML = valArr[0];
                        document.getElementById("desc" + row).value = valArr[0];
                        document.getElementById("PartType" + row).value = valArr[1];
                        document.getElementById("Unit Price" + row).value = valArr[2];
                        document.getElementById("MOQ" + row).value = valArr[3];
                    }
                } else
                {
                    document.getElementById("Part Desc." + row).innerHTML = "";
                    document.getElementById("desc" + row).value = "";
                    document.getElementById("Part No." + row).value = "";
                    document.getElementById("Part No." + row).title="";
                    document.getElementById("Unit Price" + row).value = "";
                    document.getElementById("Qty" + row).value = "";
                    document.getElementById("Amount"+row).value ="";
                    document.getElementById("MOQ" + row).value ="";
                      
                }
            }
        }
    }



    function checkfloatvalue(row){
        var fexp =/^\d{0,7}(\.\d{0,2}){0,1}$/;
        var noexp = /^[0-9]+$/;
        if(!(document.getElementById("Qty"+row).value).match(/^[0-9]+$/)) {
            document.getElementById("Qty"+row).value="";
            document.getElementById("Total").value="";
            alert("Qty "+numeric_msg);
            return false;
        }
        var partNo =  document.getElementById("Part No." + row).value;
        var partType =  document.getElementById("PartType" + row).value;
        if(document.getElementById("Qty"+row).value!="" && partType.toUpperCase()=='LUBES')
        {
            var temp = fexp.test(document.getElementById("Qty"+row).value);
            if(!fexp.test(document.getElementById("Qty"+row).value))
            {
                document.getElementById("Qty"+row).value="";
                //alert("Qty "+numeric_msg);
                alert(proper_validation_msg);
                return false;
            }
        }
        else{
            if(!(document.getElementById("Qty"+row).value).match(/^[0-9]+$/)) {
                document.getElementById("Qty"+row).value="";
                document.getElementById("Total").value="";
                alert("Qty "+numeric_msg);
                return false;
            }
        }
    }
    function CalculateAmount(row)
    {
      
        var quantity = document.getElementById("Qty" + row).value;
        var availQty =  document.getElementById("AvailQty" + row).value;
        var moq =  document.getElementById("MOQ" + row).value;
        var unitPrice= document.getElementById("Unit Price"+row).value;
        var partNo =  document.getElementById("Part No." + row).value;

        var oRows = document.getElementById("inventory_Table").getElementsByTagName('tr');
      
        if(quantity!="" && moq!=""){
            if(eval(quantity) % eval(moq) !=0){
                document.getElementById("Qty"+row).value="";
                alert(mOQ_msg+moq);
                document.getElementById("Amount"+row).value ="";
                document.getElementById("Total").value="";
                return false;
            }}

        if( eval (quantity) > eval(availQty) ){
            document.getElementById("Amount"+row).value='';
            document.getElementById("Qty"+row).value="";
            alert("Qty "+qtyCheck_msg+""+availQty);
            return false;
        }
        if (quantity == null || quantity == "") {
            document.getElementById("Qty" + row).value = '';
            quantity = 0;
        }
        
        if (unitPrice == null || unitPrice == "") {
            document.getElementById("Unit Price"+row).value = '';
            unitPrice = 0;
        }
        if(ck_qtyOrUnitPrice.test(document.getElementById("Unit Price"+row).value)){
            document.getElementById("Unit Price"+row).focus();
            alert(specialChar_validation_msg+"Unit Price");
            document.getElementById("Unit Price"+row).select();
            return false;
        }
        if(ck_qtyOrUnitPrice.test(document.getElementById("Qty"+row).value)){
            document.getElementById("Qty"+row).focus();
            alert(specialChar_validation_msg+"Qty");
            document.getElementById("Qty"+row).select();
            return false;
        }

    
        unitPrice = parseFloat(document.getElementById("Unit Price"+row).value);
        unitPrice = unitPrice.toFixed(2);
        var total=0;
        total=document.getElementById("Total").value;
        if (total == null || total == "") {
            total = 0;
        }

        if(document.getElementById("Unit Price"+row).value!="" && document.getElementById("Qty"+row).value!=""){
            var partPriceAmount =0;
            partPriceAmount=eval(quantity) * eval(unitPrice);
            document.getElementById("Amount"+row).value = partPriceAmount.toFixed(2);
        }
        else{
            document.getElementById("Amount"+row).value = "";
            // document.getElementById("Total").value ="";
        }
          
        // alert(oRows.length);
        if(document.getElementById("Unit Price"+row).value!="" && document.getElementById("Qty"+row).value!=""){
            var totalAmm=0.0;
            for(var i=1;i<=oRows.length-1;i++){
                if(document.getElementById("Amount"+i).value!=""){
                    //  alert(document.getElementById("Amount"+i).value);
                    totalAmm=totalAmm + eval(document.getElementById("Amount"+i).value);
                }
            }
            document.getElementById("Total").value =totalAmm.toFixed(2);
        }
    }
      
    function validateInventory(flag){

        var count = (document.getElementById("inventory_Table").rows.length);
        var total=0.0;
        for(var i=1;i<count;i++)
        {
            if(document.getElementById("Part No." + i).value.length>0)
            {
                var qty=document.getElementById("Qty" + i).value;
                var unitprice=document.getElementById("Unit Price"+i).value;
                if(unitprice==0)
                {
                    alert(notServicable_OR_pricenotAvailable_msg);
                    document.getElementById("Part No."+i).style.backgroundColor = "#FFC7C7";
                    document.getElementById("Part Desc." + i).innerHTML = "";
                    document.getElementById("desc" + i).value = "";
                    document.getElementById("Unit Price" + i).value = "";
                    document.getElementById("Qty" + i).value = "";
                    document.getElementById("Amount"+i).value ="";
                    document.getElementById("MOQ" + i).value ="";
                    document.getElementById("Part No."+i).focus();
                    return false;
                }
                if(qty==0)
                {
                    alert(not_blank_validation_msg+'Qty')
                    document.getElementById("Qty"+i).focus();
                    return false;
                }
                if(document.getElementById("Amount"+i).value!=""){
                    total=total + eval(document.getElementById("Amount"+i).value);
                }
            }
            if(document.getElementById("Qty"+i).value!=""){
                if( (eval(document.getElementById("Qty"+i).value) % eval(document.getElementById("MOQ" + i).value) )!=0){
                    document.getElementById("Qty"+i).value="";
                    alert(mOQ_msg+document.getElementById("MOQ" + i).value);
                    document.getElementById("Amount"+i).value ="";
                    document.getElementById("Total").value="";
                    return false;
                }}
            
        }
        document.getElementById("Total").value =total.toFixed(2);
        if(total<=0.0)
        {
            alert(partNomadat_validation_msg);
            return false;
        }
        PartNoTrim();
        if(flag =='saveAsDraft'){
            document.getElementById("SaveAsDraft").value="saveAsDraft";
            document.getElementById("myForm").submit();
        }else{
            if (confirm('Do you want to submit order ?') == true) {   
                document.getElementById("SaveAsDraft").value="finalSave";
                document.getElementById("myForm").submit();
            }
        }
    }

    function cancelInventory(){
  
        if (confirm(order_confirm_msg) == true) {
     
            var otype=document.getElementById("OrderType").value;
            var delvry=document.getElementById("Delivery").value;
            if(otype=='STD'){
                window.location.href='<%=cntxpath%>/inventoryEXPAction.do?option=createNewEXPOrder&odType=STD&delvry='+delvry.split("@@")[0];
            }
            else{
                window.location.href='<%=cntxpath%>/inventoryEXPAction.do?option=createNewEXPOrder&odType=VOR&delvry='+delvry.split("@@")[0];
            }
        }
    }


    function checkDuplicatePartNo(obj,row){
        var currentPartNo = obj.value.toUpperCase();
        var duplicateFound = true;
        var iteration;
        $.each($("input[name='partNo']"),function(key,value){
            iteration = key+1;
            if(row != iteration){
                if(currentPartNo != "" && currentPartNo == value.value.toUpperCase()){
                    duplicateFound = false;
                    document.getElementById("msg_saveFAILED").innerHTML = partunique_validation_msg;
                    document.getElementById("Part No." + row).style.backgroundColor = "white"
                    document.getElementById("Part No." + row).value = '';
                    document.getElementById("Part Desc." + row).innerHTML = '';
                    document.getElementById("desc" + row).value = '';
                    document.getElementById("Unit Price" + row).value = '';
                    document.getElementById("Part No." + row).focus();
                    window.scrollTo(0, 0);
                    return duplicateFound;
                }
            }
              
        })

        if(duplicateFound){
            getpartDesc(obj,row)
        }

    }

    $(document).ready(function(){
        $('.submit').click(function(){
            var partNo = new Array;
            var tempPartNo = new Array;
            $.each(elementArray = $("input[name='partNo']"),function(key,value){
                partNo.push(value.value);
                tempPartNo.push(value.value);
            });


            var iteration;
            var duplicationStatus = false;
            var serviceable = false;

            $.each(partNo, function(index,val){
                var iteration = 0;
                $.each($("input[name='partNo']"),function(key,value){
                    if(val != ''&& value.value != '' && val == value.value){
                        iteration = iteration+1;
                        if(iteration>1){
                            var id = 'Part No.'+(key+1);
                            document.getElementById(id).style.backgroundColor = "#FFC7C7"
                            document.getElementById("msg_saveFAILED").innerHTML = partunique_validation_msg;
                            duplicationStatus = true;
                        }
                    }
                });
            });

            // Checking serviceability on part no.
          
  
            if(!duplicationStatus){
                $.each($("input[name='serviceCheck']"),function(key,value){
                    if(value.value != '' && value.value =='N' ){
                        var id = 'Part No.'+(key+1);
                        var partValue = document.getElementById(id).value;
                        if(partValue.trim().length > 0){
                            document.getElementById(id).style.backgroundColor = "#FFC7C7";
                            alert(part_number_not_serviceable_msg);
                            serviceable = true;
                        }
                    }
                });
            }
            if(!duplicationStatus && !serviceable){
                serviceable = false;
                duplicationStatus = false;
                var buttonClicked = $(this).val();

                if(buttonClicked == "Submit"){
                    validateInventory('save');
                }else{
                    validateInventory('saveAsDraft');
                }
            }
                
        });
    });

    function PartNoTrim()
    {
        $('.partNoTrim').each(function(idx,val){
            val.value = val.value.replace(/\s+/g, ' ');
        });

    }
</script>

<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a><a href='${pageContext.request.contextPath}/inventoryEXPAction.do?option=initCreateEXPOrder'><bean:message key="label.spare.initCreateOrderEXP" /></a></li>
            <c:if test="${inventoryForm.orderType eq 'STD'}">
                <li class="breadcrumb_link"><bean:message key="label.spare.createNewOrderEXP" />  </li>
            </c:if>
            <c:if test="${inventoryForm.orderType eq 'VOR'}">
                <li class="breadcrumb_link"><bean:message key="label.spare.createNewOrderVOREXP" />  </li>
            </c:if>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED" align="center">${message}</div>

    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText">
                    <c:if test="${inventoryForm.orderType eq 'STD'}">
                        <bean:message key="label.spare.createNewOrderEXP" />
                    </c:if>
                    <c:if test="${inventoryForm.orderType eq 'VOR'}">
                        <bean:message key="label.spare.createNewOrderVOREXP" />
                    </c:if>
                </h1>
                <form action="<%=cntxpath%>/inventoryEXPAction.do?option=addNewOrderByCartorExcel" method="post" id="myForm"  onsubmit="return false;" ENCTYPE="multipart/form-data">
                    <input type="hidden" name="currency" value="${inventoryForm.currency}"/>
                    <input type="hidden" name="priceListCode" value="${inventoryForm.priceListCode}"/>
                    <c:if test="${inventoryForm.orderType eq 'STD'}">
                        <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a>@@<bean:message key="label.spare.createNewOrder" />"/>
                    </c:if>
                    <c:if test="${inventoryForm.orderType eq 'VOR'}">
                        <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a>@@<bean:message key="label.spare.createNewOrderVOR" />"/>
                    </c:if>

                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="4" class="LiteBorder">
                        <tr>
                            <td>
                                <table width="100%" border="0" align="center" cellpadding="3" cellspacing="3" bordercolor=#cccccc >
                                    <tr height=20 bgcolor="#FFFFFF">
                                        <td width="10%" align="right" ><label ><B><bean:message key="label.spare.poNo" /> : </B></label></td>
                                        <td align="left" width="10%" >
                                            <c:if test="${inventoryForm.poNoRadio eq 'newPO'}" >
                                                ${inventoryForm.refNo}
                                                <input type="hidden" name="refNo" value="${inventoryForm.refNo}">
                                            </c:if>
                                            <c:if test="${inventoryForm.poNoRadio eq 'oldPO'}" >
                                                ${inventoryForm.prevPoNo}
                                                <input type="hidden" name="refNo" value="${inventoryForm.prevPoNo}">
                                            </c:if>
                                            <input type="hidden" name="poNoRadio" value="${inventoryForm.poNoRadio}">
                                        </td>
                                        <td align="right" width="10%"><label ><B> <bean:message key="label.spare.orderType" /> : </B></label></td>
                                        <td align="left" width="15%">
                                            ${inventoryForm.orderType}
                                            <input type="hidden" name="orderType" id="OrderType" value="${inventoryForm.orderType}">
                                        </td>
                                        <td  width="10%" align="right" class="headingSpas" nowrap  ><label ><B><bean:message key="label.spare.buyerName" /> :</B></label>
                                        </td>
                                        <td width="15%" align="left">
                                            ${inventoryForm.dealerName} 

                                        </td>
                                        <td align="right" width="10%" nowrap><label ><B><bean:message key="label.spare.consigneeName" /> : </B></label></td>
                                        <td align="left" width="20%">
                                            ${inventoryForm.consigneeCode} 
                                            <input type="hidden" name="consigneeName" value="${inventoryForm.consigneeCode}" >
                                            <input type="hidden" name="consigneeCode" value="${inventoryForm.consigneeName}" >
                                            <input type="hidden" name="deliveryCode" id="Delivery" value="${inventoryForm.deliveryCode}">
                                            <input type="hidden" name="dealerRefNo"  value="${inventoryForm.dealerRefNo}">
                                            <input type="hidden" name="paymentTerms"  value="${inventoryForm.paymentTerms}">
                                            <input type="hidden" name="incoTerms"  value="${inventoryForm.incoTerms}">
                                            <input type="hidden" name="consigneeName"  value="${inventoryForm.consigneeName}">
                                            <input type="hidden" name="dischargePort"  value="${inventoryForm.dischargePort}">
                                            <input type="hidden" name="consigneeAddress"  value="${inventoryForm.consigneeAddress}">
                                            <input type="hidden" name="consigneeCountry"  value="${inventoryForm.consigneeCountry}">
                                            <input type="hidden" name="destinationPlace"  value="${inventoryForm.destinationPlace}">
                                            <input type="hidden" name="precarriageBy"  value="${inventoryForm.precarriageBy}">
                                            <input type="hidden" name="placePreCarrier"  value="${inventoryForm.placePreCarrier}">
                                            <input type="hidden" name="refNo"  value="${inventoryForm.refNo}">
                                            <input type="hidden" name="documentName"  value="${inventoryForm.documentName}">
                                            <input type="hidden" name="specInstr"  value="${inventoryForm.specInstr}">


                                        </td>
                                    </tr>
                                    <tr height=20 bgcolor="#FFFFFF">
                                        <td align="right" colspan="3" width="10%" nowrap><label ><B> <bean:message key="label.spare.destinationPlace" /> : </B></label></td>
                                        <td align="left" >
                                            ${inventoryForm.destinationPlace}
                                        </td>
                                        <td width="10%" align="right" nowrap><label ><B><bean:message key="label.spare.buyerCountry" /> : </B></label></td>
                                        <td align="left"  >
                                            ${inventoryForm.buyerCountry}
                                        </td>
                                        <td align="right" width="10%" nowrap><label ><B> <bean:message key="label.spare.consigneeCountry" /> : </B></label></td>
                                        <td align="left" >
                                            <%=con_country_name%>
                                        </td>
                                    </tr>
                                   <%-- <tr height=20 bgcolor="#FFFFFF">
                                        <td align="right" width="10%" nowrap style="padding-right: 3px;">
                                            <b><bean:message key="label.spare.documentUpload" /> :</b>
                                        </td>
                                        <td  align="left" style="padding-right:10px" colspan="3">
                                            <input name="excelDocumentFile" type="file" id="excelDocumentFile" style="width: 200px"/>&nbsp;<font color="red"><b>${inventoryForm.documentName}</b></font>
                                            <input type="hidden" name="documentName"  value="${inventoryForm.documentName}">
                                        </td>
                                    </tr>--%>
                                    <c:if test="${inventoryForm.orderType eq 'VOR'}">
                                        <tr height=20 bgcolor="#FFFFFF">
                                            <td width="10%" align="right" ><label ><B><bean:message key="label.catalogue.chassisNo" /> : </B></label></td>
                                            <td align="left" width="10%" >
                                                ${inventoryForm.chassisNo}
                                                <input type="hidden" name="chassisNo" value="${inventoryForm.chassisNo}">
                                            </td>
                                            <td align="right" width="10%"><label ><B> <bean:message key="label.catalogue.model" /> : </B></label></td>
                                            <td align="left" width="15%">
                                                ${inventoryForm.modelNo}
                                                <input type="hidden" name="modelNo" id="OrderType" value="${inventoryForm.modelNo}">
                                            </td>
                                            <td align="right" width="10%"><label ><B> <bean:message key="label.catalogue.engineNo" /> : </B></label></td>
                                            <td align="left" width="15%">
                                                ${inventoryForm.engineNo}
                                                <input type="hidden" name="engineNo" id="Delivery" value="${inventoryForm.engineNo}">
                                            </td>
                                            <td align="right" width="10%"><label ><B> <bean:message key="label.newOrder.firNo" /> : </B></label></td>
                                            <td align="left" width="20%">
                                                ${inventoryForm.firNo}
                                                <input type="hidden" name="firNo" id="Fir No" value="${inventoryForm.firNo}">
                                            </td>
                                        </tr>
                                    </c:if>


                                </table>
                            </td>
                        </tr>

                        <tr>
                            <td>
                                <table id="inventory_Table" width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bordercolor=#cccccc bgcolor="#cccccc" >
                                    <tbody>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td align="center" width="8%"><B><bean:message key="label.common.sno" /></B></td>
                                            <td align="left" width="18%"><B><bean:message key="label.common.partno_small" /></B></td>
                                            <td align="left" width="30%"><B><bean:message key="label.common.partdesc_small" /></B></td>
                                            <td align="center" width="5%"><B><bean:message key="label.newOrder.moq" /></B></td>
                                            <td align="center" width="5%"><B><bean:message key="label.catalogue.mrp" />&nbsp;(${inventoryForm.currency})</B></td>
                                            <td align="center" width="5%"><B><bean:message key="label.common.qty_small" /></B></td>
                                            <td align="center" width="8%"><B><bean:message key="label.common.amt_small" />&nbsp;(${inventoryForm.currency})</B></td>
                                        </tr>
                                        <c:if test="${empty dataList}">
                                            <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                                <td align="center" >1</td>
                                                <td align="left" ><input type="text" class="partNoTrim" name="partNo" id="Part No.1" value="" maxlength="30" onblur="checkDuplicatePartNo(this, '1');" tabindex="1"/><%--onblur="checkDuplicatePartNo(this, '1');"--%>
                                                    <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' onclick="javascript:checkDuplicatePartNo(document.getElementById('Part No.1'), '1');"><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                                    <input type="hidden" name="partType" id="PartType1" value="" maxlength="20"  />
                                                </td>
                                                <td align="left" ><span id="Part Desc.1"></span><input name="partDesc" id="desc1" type="hidden" value=""/></td>
                                                <td align="left" ><input type="text" name="moq" id="MOQ1" value="" maxlength="4" readonly onkeydown="return false;" style="text-align: center;width:50px !important"/></td>
                                                <td align="left" ><input type="text" name="unitprice" id="Unit Price1" value="" maxlength="4" readonly style="text-align: right;width:70px !important"/>    <%--onblur="getCalAmmountAfterDiscInput(this.value,'1')" --%>
                                                    <input type="hidden" name="disAmmperPart" id="DiscountAmmount1" value="" maxlength="4"  style="width:70px !important"/>
                                                </td>
                                                <td align="left" >
                                                    <input type="text" name="quantity" id="Qty1" value="" maxlength="7"  onblur="checkfloatvalue('1');CalculateAmount('1');" style="width:50px !important" tabindex="2"/>
                                                    <input type="hidden" name="availQuantity" id="AvailQty1" value="" maxlength="7"  />
                                                </td>
                                                <td align="left" >
                                                    <input type="text" name="amount" id="Amount1" value="" readonly onkeydown="return false;" style="text-align: right"/>
                                                    <input type="hidden" id="service1" name="serviceCheck"/>
                                                </td>
                                            </tr>
                                            <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                                <td align="center" >2</td>
                                                <td align="left" ><input type="text" class="partNoTrim" name="partNo" id="Part No.2" value="" maxlength="30" onblur="checkDuplicatePartNo(this, '2');" tabindex="3"/><%--onblur="checkDuplicatePartNo(this, '2');"--%>
                                                    <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' onclick="javascript:checkDuplicatePartNo(document.getElementById('Part No.2'), '2');"><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                                    <input type="hidden" name="partType" id="PartType2" value="" maxlength="20"  />
                                                </td>
                                                <td align="left" ><span id="Part Desc.2"></span><input name="partDesc" id="desc2" type="hidden" value=""/></td>
                                                <td align="left" ><input type="text" name="moq" id="MOQ2" value="" maxlength="4" readonly onkeydown="return false;" style="text-align: center;width:50px !important"/></td>
                                                <td align="left" ><input type="text" name="unitprice" readonly id="Unit Price2" value="" maxlength="4" style="text-align: right;width:70px !important"/>   <%--onblur="getCalAmmountAfterDiscInput(this.value,'2')" --%>
                                                    <input type="hidden" name="disAmmperPart" id="DiscountAmmount2" value="" maxlength="4"  style="width:70px !important"/>
                                                </td>
                                                <td align="left" ><input type="text" name="quantity" id="Qty2" value="" maxlength="7"  onblur="checkfloatvalue('2');CalculateAmount('2');" style="width:50px !important " tabindex="4"/>
                                                    <input type="hidden" name="availQuantity" id="AvailQty2" value="" maxlength="7"  />
                                                </td>
                                                <td align="left" >
                                                    <input type="text" name="amount" id="Amount2" value="" onkeydown="return false;" readonly style="text-align: right"/>
                                                    <input type="hidden" id="service2" name="serviceCheck"/>
                                                </td>
                                            </tr>
                                            <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                                <td align="center" >3</td>
                                                <td align="left" ><input type="text" class="partNoTrim" name="partNo" id="Part No.3" value="" maxlength="30" onblur="checkDuplicatePartNo(this, '3');" tabindex="5"/><%--onblur="checkDuplicatePartNo(this, '3');"--%>
                                                    <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' onclick="javascript:checkDuplicatePartNo(document.getElementById('Part No.3'), '3');"><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                                    <input type="hidden" name="partType" id="PartType3" value="" maxlength="20"  />
                                                </td>
                                                <td align="left" ><span id="Part Desc.3"></span><input name="partDesc" id="desc3" type="hidden" value=""/></td>
                                                <td align="left" ><input type="text" name="moq" id="MOQ3" value="" maxlength="4" readonly onkeydown="return false;" style="text-align: center;width:50px !important"/></td>
                                                <td align="left" ><input type="text" name="unitprice" id="Unit Price3" readonly value="" maxlength="4"  style="text-align: right;width:70px !important"/>   <%--onblur="getCalAmmountAfterDiscInput(this.value,'3')"--%>
                                                    <input type="hidden" name="disAmmperPart" id="DiscountAmmount3" value="" maxlength="4"  style="width:70px !important"/>
                                                </td>
                                                <td align="left" ><input type="text" name="quantity" id="Qty3" value="" maxlength="7"  onblur="checkfloatvalue('3');CalculateAmount('3');" style="width:50px !important " tabindex="6"/>
                                                    <input type="hidden" name="availQuantity" id="AvailQty3" value="" maxlength="7"  />
                                                </td>
                                                <td align="left" >
                                                    <input type="text" name="amount" id="Amount3" value="" onkeydown="return false;" readonly style="text-align: right"/>
                                                    <input type="hidden" id="service3" name="serviceCheck"/>
                                                </td>
                                            </tr>
                                            <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                                <td align="center" >4</td>
                                                <td align="left" ><input type="text" class="partNoTrim" name="partNo" id="Part No.4" value="" maxlength="30" onblur="checkDuplicatePartNo(this, '4');" tabindex="7"/><%--onblur="checkDuplicatePartNo(this, '4');"--%>
                                                    <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' onclick="javascript:checkDuplicatePartNo(document.getElementById('Part No.4'), '4');"><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                                    <input type="hidden" name="partType" id="PartType4" value="" maxlength="20"  />
                                                </td>
                                                <td align="left" ><span id="Part Desc.4"></span><input name="partDesc" id="desc4" type="hidden" value=""/></td>
                                                <td align="left" ><input type="text" name="moq" id="MOQ4" value="" maxlength="4" readonly onkeydown="return false;" style="text-align: center;width:50px !important"/></td>
                                                <td align="left" ><input type="text" name="unitprice" readonly id="Unit Price4" value="" maxlength="4" style="text-align: right;width:70px !important"/>  <%-- onblur="getCalAmmountAfterDiscInput(this.value,'4')" --%>
                                                    <input type="hidden" name="disAmmperPart" id="DiscountAmmount4" value="" maxlength="4"  style="width:70px !important"/>
                                                </td>
                                                <td align="left" ><input type="text" name="quantity" id="Qty4" value="" maxlength="7"  onblur="checkfloatvalue('4');CalculateAmount('4');" style="width:50px !important " tabindex="8"/>
                                                    <input type="hidden" name="availQuantity" id="AvailQty4" value="" maxlength="7"  />
                                                </td>
                                                <td align="left" >
                                                    <input type="text" name="amount" id="Amount4" value="" readonly onkeydown="return false;"  style="text-align: right"/>
                                                    <input type="hidden" id="service4" name="serviceCheck"/>
                                                </td>
                                            </tr>
                                            <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                                <td align="center" >5</td>
                                                <td align="left" ><input type="text" class="partNoTrim" name="partNo" id="Part No.5" value="" maxlength="30" onblur="checkDuplicatePartNo(this, '5');" tabindex="9" /><%--onblur="checkDuplicatePartNo(this, '5');"--%>
                                                    <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' onclick="javascript:checkDuplicatePartNo(document.getElementById('Part No.5'), '5');"><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                                    <input type="hidden" name="partType" id="PartType5" value="" maxlength="20"  />
                                                </td>
                                                <td align="left" ><span id="Part Desc.5"></span><input name="partDesc" id="desc5" type="hidden" value=""/></td>
                                                <td align="left" ><input type="text" name="moq" id="MOQ5" value="" maxlength="4" readonly onkeydown="return false;" style="text-align: center;width:50px !important"/></td>
                                                <td align="left" ><input type="text" name="unitprice" readonly id="Unit Price5" value="" maxlength="4"  style="text-align: right;width:70px !important"/>    <%--onblur="getCalAmmountAfterDiscInput(this.value,'5')"--%>
                                                    <input type="hidden" name="disAmmperPart" id="DiscountAmmount5" value="" maxlength="4"  style="width:70px !important"/>
                                                </td>
                                                <td align="left" ><input type="text" name="quantity" id="Qty5" value="" maxlength="7"  onblur="checkfloatvalue('5');CalculateAmount('5');" style="width:50px !important " tabindex="10"/>
                                                    <input type="hidden" name="availQuantity" id="AvailQty5" value="" maxlength="7"  />
                                                </td>
                                                <td align="left" >
                                                    <input type="text" name="amount" id="Amount5" value="" readonly  onkeydown="return false;" style="text-align: right"/>
                                                    <input type="hidden" id="service5" name="serviceCheck"/>
                                                </td>
                                            </tr>
                                        </c:if>


                                        <c:if test="${!empty dataList}">
                                            <c:set var="jj" value="1"/>
                                            <c:forEach items="${dataList}" var="dataList">
                                                <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                                    <td align="center" >${jj}</td>
                                                    <td align="left" ><input type="text" name="partNo" id="Part No.${jj}" value="${dataList.partno}" maxlength="30" onblur="checkPartNo(this, ${jj});"  tabindex="1"/><%--onblur="checkDuplicatePartNo(this, ${jj});"--%>
                                                        <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' onclick="javascript:checkDuplicatePartNo(document.getElementById('Part No.${jj}'),${jj});"><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                                        <input type="hidden" name="partType" id="PartType${jj}" value="${dataList.partTypeStr}" maxlength="20"  />
                                                    </td>
                                                    <td align="left" ><span id="Part Desc.${jj}">${dataList.part_desc}</span><input name="partDesc" id="desc${jj}" type="hidden" value="${dataList.part_desc}"/></td>
                                                    <td align="left" ><input type="text" name="moq" id="MOQ${jj}" value="${dataList.moq}" maxlength="4" readonly onkeydown="return false;" style="text-align: center;width:50px !important"/></td>
                                                    <td align="left" >
                                                        <fmt:parseNumber type="number" var="unitValue" value="${dataList.unitValue}"/>
                                                        <input type="text" readonly name="unitprice" id="Unit Price${jj}" value="${unitValue}" maxlength="4"  style="text-align: right;width:70px !important" tabindex="2"/>    <%--onblur="getCalAmmountAfterDiscInput(this.value,'5')"--%>
                                                        <input type="hidden" name="disAmmperPart" id="DiscountAmmount5" value="" maxlength="4"  style="width:70px !important"/>
                                                    </td>
                                                    <td align="left" ><input type="text" name="quantity" id="Qty${jj}" value="${dataList.qty}" maxlength="7"  onblur="checkfloatvalue(${jj});CalculateAmount(${jj});" style="width:50px !important "/>
                                                        <input type="hidden" name="availQuantity" id="AvailQty${jj}" value="${dataList.currQty}" maxlength="7"  />
                                                    </td>
                                                    <td align="left" >
                                                        <fmt:parseNumber type="number" var="amountPerPrice" value="${dataList.amountPerPrice}"/>
                                                        <input type="text" name="amount" id="Amount${jj}" value="${amountPerPrice}" readonly  onkeydown="return false;" style="text-align: right"/>
                                                        <input type="hidden" id="service${jj}" value="${dataList.service}" name="serviceCheck"/>
                                                    </td> <%--  ${dataList.amountPerPrice}--%>
                                                </tr>
                                                <c:set var="jj" value="${jj+1}"/>
                                            </c:forEach>
                                        </c:if>
                                    </tbody>
                                </table>
                            </td>
                        </tr>


                        <tr height=20 bgcolor="#FFFFFF">
                            <td align="right"><input type="hidden" name="saveOrDraft" id="SaveAsDraft">
                                <B> <bean:message key="label.newOrder.torderamount" /> </B>
                                <fmt:parseNumber type="number" var="totalAmont" value="${inventoryForm.totalAmont}"/>
                                <input type="text" name="totalAmont" value="${totalAmont}" id="Total" readonly style="text-align: right"/>
                            </td>
                        </tr>
                        <tr height=20 bgcolor="#FFFFFF">
                            <td align="center" >
                                <input type="button" class="headingSpas1" value="Add More Part" name="addmore" id="Add More" onclick="addRowSpares('inventory_Table')"/> &nbsp; &nbsp;&nbsp;<%--  partNotBlank  addRowSpares--%>
                                <input name="input" type="button" value="Save Draft Order" class="submit" >&nbsp; &nbsp;&nbsp;
                                <input name="input" type="button" value="Submit" class="submit">
                            </td>
                        </tr>
                        <tr height=20 bgcolor="#FFFFFF">
                            <td align="left" >
                                <font  color="green"> <B> Note : Qty must be Multiple of MOQ ! </B> </font>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </center>
</div>
