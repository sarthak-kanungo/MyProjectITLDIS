

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
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%> 
<%@ page import="java.util.*,org.apache.struts.util.TokenProcessor" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DATE);
            String token = TokenProcessor.getInstance().generateToken(request);
            session.setAttribute("org.apache.struts.action.TOKEN", token);
%>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_6.js"></script>
<script src="js/intermediate.js"></script>
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

        var td1 = document.createElement("TD")
        td1.align="left";
        var strHtml1 = "<input type=\"text\" name=\"partNo\" id=\"Part No."+count+"\" value=\"\" maxlength=\"30\"  onblur=\"getpartDesc(this,"+count+");this.value=TrimAll(this.value);\" tabindex="+count+"/>"+
            "<img alt=\"\" src=\"${pageContext.request.contextPath}/image/next_1.gif\" onclick=\"javascript:getpartDesc(document.getElementById(\"Part No."+count+"\"), "+count+");\">"+
            "<img alt=\"\"  style=\"visibility:hidden;\" align=\"top\" border=\"0\" src=\"${pageContext.request.contextPath}/image/load.gif\"/>  \n\ "+
            "<input type=\"hidden\" name=\"partType\" id=\"PartType"+count+"\" value=\"\" maxlength=\"20\"  />    ";
        td1.innerHTML = strHtml1.replace(/!count!/g, count);

        var td2 = document.createElement("TD")
        td2.align="left";
        var strHtml2 = "<span id=\"Part Desc."+count+"\"></span><input name=\"partDesc\" id=\"desc"+count+"\" type=\"hidden\" value=\"\"/>";
        td2.innerHTML = strHtml2.replace(/!count!/g, count);

        var td4 = document.createElement("TD")
        var strHtml4 = "<input type=\"text\" name=\"unitprice\" id=\"Unit Price"+count+"\" value=\"\" maxlength=\"4\" style=\"text-align: right\" readonly/>";
        td4.innerHTML = strHtml4.replace(/!count!/g, count);

        var td3 = document.createElement("TD")
        var strHtml3 = "<input type=\"text\" name=\"quantity\" id=\"Qty"+count+"\" value=\"\" maxlength=\"7\"  style=\"width: 50px;\"  onblur=\"checkfloatvalue("+count+");CalculateAmount("+count+");\" tabindex="+count+"/> \n\ "+
            "<input type=\"hidden\" name=\"availQuantity\" id=\"AvailQty"+count+"\" value=\"\" maxlength=\"7\" style=\"width: 50px;\" />  ";
        td3.innerHTML = strHtml3.replace(/!count!/g, count);

        var td6 = document.createElement("TD")
        var strHtml6 = "<input type=\"text\" name=\"percentDis\" id=\"Discount"+count+"\" value=\"\" maxlength=\"7\"  style=\"width: 70px;\" onblur=\"getCalAmmountAfterDiscInput(this.value, "+count+");\" tabindex="+count+"/> \n\ "+
            "<input type=\"hidden\" name=\"disAmmperPart\" id=\"DiscountAmmount"+count+"\" value=\"\" maxlength=\"7\" style=\"width: 70px;\" />  ";
        td6.innerHTML = strHtml6.replace(/!count!/g, count);


        var td7 = document.createElement("TD")
        var strHtml7 = "<input type=\"text\" name=\"amount\" id=\"Amount"+count+"\" value=\"\" readonly style=\"text-align: right\"/>";
        td7.innerHTML = strHtml7.replace(/!count!/g, count);

        var td5 = document.createElement("TD")
        var strHtml5 = "<select name='billIdArr'  id=\"BillTo"+count+"\" class=\"headingSpas\" onchange=\"javascript:getCalAmmountAfterDisc(this.value, "+count+");\"> <option value=\"\">- Select -</option> <c:forEach items="${billedList}" var="billedList">  <option value='${billedList.billId}@@${billedList.discount}@@${billedList.billdesc}' title='${billedList.billdesc}'>${billedList.billdesc} </option></c:forEach>   </select>";
        td5.innerHTML = strHtml5.replace(/!count!/g, count);

        row.appendChild(td0);
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        row.appendChild(td6);
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

    function getpartDesc(obj,row)
    {
        debugger;
        document.getElementById("msg_saveFAILED").innerHTML ="";
        var part_No1 = obj.value;
        var part_No=part_No1.toUpperCase();
        //alert(row);
        //document.getElementsByName("partNo").innerHTML = part_No;
        document.getElementById("Part No."+row).value = part_No;
        if(part_No==""){
            document.getElementById("Part Desc." + row).innerHTML = "";
            document.getElementById("desc" + row).value = '';
            document.getElementById("Part No." + row).value = '';
            document.getElementById("Part No." + row).title="";
            document.getElementById("Unit Price" + row).value = '';
            document.getElementById("Qty" + row).value = '';
            document.getElementById("Amount"+row).value ='';
            document.getElementById("BillTo"+row).value="";
            document.getElementById("Discount" + row).value = "";
            CalculateAmount(row);
            return false;
        }

        if(ck_code.test(part_No)){
            obj.focus();
            alert(specialChar_validation_msg+"Part No.");
            obj.select();
            return false;
        }
        var todate = new Date().getTime();
        var sellingFlag="sellingFlag";
        var strURL = '<%=cntxpath%>/inventoryAction.do?option=getPartPriceBypartNo&partno=' + part_No + '&sellingFlag=' + sellingFlag + '&tm=' + todate;
        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            stateChangedPrice(xmlHttp, row, part_No);
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
                var valArr = res.split('@@');

                if (valArr.length > 0 && res != '')
                {
                    if(valArr[4]==""){
                        document.getElementById("msg_saveFAILED").innerHTML = " HSN Code is not present for part no: '"+partNos+"'";
                        document.getElementById("Part No." + row).value = '';
                        document.getElementById("Part Desc." + row).innerHTML = '';
                        document.getElementById("desc" + row).value = '';
                        document.getElementById("Unit Price" + row).value = '';
                       
                        document.getElementById("Part No." + row).focus();
                        window.scrollTo(0, 0);
                        
                        return false;
                    }else{
                        sparex = new Array();
                        part_No = document.getElementsByName("partNo")
                        partDesc = document.getElementsByName("partDesc")
                        unitPrice = document.getElementsByName("unitPrice")
                        for (var k = 0; k < part_No.length; k++) {
                            if (part_No[k].value != '')
                            {
                                var res1 = true;
                                for (var m = 0; m < sparex.length; m++)
                                {
                                    if (sparex[m] == trimS(part_No[k].value))
                                    {
                                        res1 = false;
                                    }
                                }
                                if (res1 == false)
                                {
                                    document.getElementById("msg_saveFAILED").innerHTML = partunique_validation_msg;
                                    document.getElementById("Part No." + row).value = '';
                                    document.getElementById("Part Desc." + row).innerHTML = '';
                                    document.getElementById("desc" + row).value = '';
                                    document.getElementById("Unit Price" + row).value = '';
                                    document.getElementById("comptype"+row).value=''
                                    document.getElementById("Part No." + row).focus();
                                    window.scrollTo(0, 0);
                                    img_obj.style.visibility = "hidden";
                                    return false;
                                }
                                sparex.push(part_No[k].value);
                            }
                        }
                        document.getElementById("Part Desc." + row).innerHTML = valArr[0];
                        document.getElementById("desc" + row).value = valArr[0];
                        document.getElementById("PartType" + row).value = valArr[1];
                        document.getElementById("Unit Price" + row).value = valArr[2];
                        document.getElementById("AvailQty" + row).value = valArr[3];
                    }
                    //alert(valArr[3]);
                    // document.getElementById("img" + row).style.dispaly = "none";


                } else
                {
                    document.getElementById("Part Desc." + row).innerHTML = "";
                    document.getElementById("desc" + row).value = '';
                    document.getElementById("Part No." + row).value = '';
                    document.getElementById("Part No." + row).title="";
                    document.getElementById("Unit Price" + row).value = '';
                    document.getElementById("Qty" + row).value = '';
                    document.getElementById("Amount"+row).value ='';
                    document.getElementById("Discount" + row).value = "";
                    document.getElementById("BillTo"+row).value="";
                    CalculateAmount(row);
                    //   document.getElementById("img" + row).style.dispaly = "none";
                }
            }
        }
    }

    function getCalAmmountAfterDisc(val,row)
    {
        //alert(val);
        var oRows = document.getElementById("inventory_Table").getElementsByTagName('tr');
        if(document.getElementById("Unit Price"+row).value!="" && document.getElementById("Qty"+row).value!=""){
            
            if(!document.getElementById("BillTo"+row).value==""){
                if(val.split("@@")[1]=="100.0"){
                    document.getElementById("Discount" + row).value = val.split("@@")[1];
                    document.getElementById("DiscountAmmount" + row).value =document.getElementById("Amount"+row).value;
                    document.getElementById("Discount" + row).focus();
                    document.getElementById("Discount" + row).readOnly = true;
                    document.getElementById("Discount" + row).style.backgroundColor= "#E6E4E4";
                }
                else{
                    if(val.split("@@")[1]=="0.0"){
                        document.getElementById("Discount" + row).value = document.getElementById("totalDiscountPercentage").value;
                    }else{
                        document.getElementById("Discount" + row).value = val.split("@@")[1];
                    }
                    document.getElementById("DiscountAmmount" + row).value ="";
                    document.getElementById("Discount" + row).focus();
                    document.getElementById("Discount" + row).readOnly = false;
                    document.getElementById("Discount" + row).style.backgroundColor="#FFFFFF";
                }
                
            }

            if(document.getElementById("BillTo"+row).value==""){
                document.getElementById("Discount" + row).value = "";
                document.getElementById("Amount" + row).value ="";
                document.getElementById("Discount" + row).readOnly = false;
                document.getElementById("Discount" + row).style.backgroundColor="#FFFFFF";

            }
            CalculateAmount(row);
 
        }

    }
    function getCalAmmountAfterDiscInput(discountAmm,row)
    {

        if(!discountAmm.match(/^[0-9.]+$/)){
            alert("Discount "+numeric_msg);
            document.getElementById("Amount"+row).value="";
            document.getElementById("Discount"+row).value="0.0";
            getCalAmmountAfterDisc('1@@0.0',row);
            return false;
        }

        if( eval(discountAmm)>100 ){
            alert(discount_validation_msg);
            document.getElementById("Amount"+row).value="";
            document.getElementById("Discount"+row).value="";
            document.getElementById("DiscountAmmount"+row).value="";
            document.getElementById("Discount"+row).focus();
            return false;
        }
            
        CalculateAmount(row);

    <%--      if(document.getElementById("Unit Price"+row).value!="" && document.getElementById("Qty"+row).value!=""){
            // CalculateAmount(row);
             // var discount=val.split("@@")[1];
            var amm=document.getElementById("Amount"+row).value;
             var disAmm=(eval(amm)* eval(discountAmm))/100;
             var lessAmm=eval(amm)- eval(disAmm);
             document.getElementById("DiscountAmmount"+row).value=disAmm.toFixed(2);
             document.getElementById("Amount"+row).value = lessAmm.toFixed(2);

         //  var lubesDis=document.getElementById("Discount on Lubes").value;
         //   var partDis=document.getElementById("Discount on Part").value;
         //   getDisAmountOnPart(partDis);
         //  getDisAmountOnLubes(lubesDis);
    }
    --%>
        }
        
        function calculateTotal()
        {
            var oRows = document.getElementById("inventory_Table").getElementsByTagName('tr');
            var totalpartAmm=0;
            var totalLubesAmm=0;
            var disOnPartAmm=0;
            var disOnLubesAmm=0;
            for(var i=1;i<=oRows.length-1;i++){
                if(document.getElementById("PartType"+i).value.toString().toUpperCase()=='LUBES'){
                    if(document.getElementById("Amount"+i).value!=""){
                        totalLubesAmm=totalLubesAmm + eval(document.getElementById("Amount"+i).value);
                        disOnLubesAmm=disOnLubesAmm +eval(document.getElementById("DiscountAmmount"+i).value);
                    }
                }
                else{
                    if(document.getElementById("Amount"+i).value!=""){
                        totalpartAmm=totalpartAmm + eval(document.getElementById("Amount"+i).value);
                        disOnPartAmm=disOnPartAmm +eval(document.getElementById("DiscountAmmount"+i).value);
                    }
                }
            }
            document.getElementById("Total Part Value").value = totalpartAmm.toFixed(2);
            document.getElementById("Total Lubes Value").value = totalLubesAmm.toFixed(2);

            var otherDis=document.getElementById("Other Discount").value;
            if(otherDis==""){
                otherDis=0;
                // document.getElementById("Other Discount").value="0";
            }
            var afterOtherDisTotalValue= eval(totalpartAmm.toFixed(2))+eval(totalLubesAmm.toFixed(2))-eval(otherDis);

            document.getElementById("Total").value=eval(afterOtherDisTotalValue).toFixed(2);
            document.getElementById("Discount on Lubes").value = disOnLubesAmm.toFixed(2);
            document.getElementById("Discount on Part").value = disOnPartAmm.toFixed(2);
            
        }

        function getTotalAmmAfterOtherDis(otherDis){

            var totalpartvalue=document.getElementById("Total Part Value").value;
            var totallubesvalue=document.getElementById("Total Lubes Value").value;
            var otherDis=document.getElementById("Other Discount").value;
            if(otherDis==""){
                otherDis=0;
                document.getElementById("Other Discount").value="0";
            }
            if(otherDis!="" &&  !otherDis.match(/^[0-9.]+$/)){
                otherDis=0;
                document.getElementById("Other Discount").value="0";
                alert("Other Discount "+numeric_msg);
            }
            if( eval(otherDis) > eval(document.getElementById("Total").value))
            {
                otherDis=0;
                document.getElementById("Other Discount").value="0";
                alert("Other Discount "+gt_than_msg+" Total Amount");
            }

            var afterOtherDisTotalValue= eval(totalpartvalue)+eval(totallubesvalue)-eval(otherDis);
            document.getElementById("Total").value = afterOtherDisTotalValue.toFixed(2);
            
        }

        function getCreditAmm(creditVal){

            if(creditVal==""){
                creditVal=0;
                // document.getElementById("CreditValue").value="0";
            }
            if(creditVal!="" && !creditVal.match(/^[0-9]+$/)){
                alert("Credit Value "+numeric_msg);
                document.getElementById("CreditValue").value="0";
                return false;
            }
            if( eval(creditVal) > eval(document.getElementById("Total").value))
            {
                document.getElementById("CreditValue").value="0";
                // document.getElementById("Total").value=eval(totalpartvalue)+eval(totallubesvalue);
                alert("Credit Amount "+gt_than_msg+" Total Amount");
                return false;
            }
        }



        function checkfloatvalue(row){
            var fexp =/^\d{0,7}(\.\d{0,2}){0,1}$/;
            var noexp = /^[0-9]+$/;
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

                // if(noexp.test(document.getElementById("Qty"+row).value))
                if(!(document.getElementById("Qty"+row).value).match(/^[0-9]+$/)) {
                    document.getElementById("Amount"+row).value='';
                    document.getElementById("BillTo" + row).value="";
                    document.getElementById("Qty"+row).value="";
                    //document.getElementById("Qty" + row).focus();
                    //alert("Qty "+numeric_msg);
                    return false;
                }
            }
        }
        function CalculateAmount(row)
        {
            var quantity = document.getElementById("Qty" + row).value;
            var availQty =  document.getElementById("AvailQty" + row).value;
            var partNo =  document.getElementById("Part No." + row).value;

    <%--if(partNo!=""){
    if(!quantity.match(/^[0-9]+$/)){
        document.getElementById("Amount"+row).value='';
        document.getElementById("Qty" + row).focus();
        alert("Qty "+numeric_msg);
        return false;
    }
    }--%>
            if( eval (quantity) > eval(availQty) ){
                document.getElementById("Amount"+row).value='';
                // document.getElementById("Qty" + row).focus();
                document.getElementById("Qty"+row).value="";
                alert("Qty "+qtyCheck_msg+""+availQty);
                return false;
            }
            if (quantity == null || quantity == "") {
                document.getElementById("Qty" + row).value = '';
                quantity = 0;
            }
            var unitPrice = document.getElementById("Unit Price"+row).value;
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
            unitPrice = parseFloat(unitPrice);
            unitPrice = unitPrice.toFixed(2);
            


            var discount=document.getElementById("Discount"+row).value;
            if (discount == null || discount == "") {
                discount = 0;
            }
            discount = parseFloat(discount);
    <%-- var total=0;
     total=document.getElementById("Total").value;
     if (total == null || total == "") {
         total = 0;
     }
     total = parseFloat(total);--%>
             var partPriceAmount = eval(quantity) * eval(unitPrice);
             discount=(eval(discount)/100)* parseFloat(partPriceAmount);

             partPriceAmount = parseFloat(partPriceAmount)-parseFloat(discount);
       
             document.getElementById("Amount"+row).value = partPriceAmount.toFixed(2);
             //  document.getElementById("Total").value =total.toFixed(2);
             // var amm=document.getElementById("Amount"+row).value;

             calculateTotal();
         }

         function settodayDate(){
             document.getElementById("Invoice Date").value=tdays+'/'+tmon+'/'+<%=year%>;
         }
         function validateInventory(val){

          
             if(val=='pro'){
                 document.getElementById('eType').value="export";
             }
             else{
                 document.getElementById('eType').value="";
             }

             if(document.getElementById("Customer Name").value==""){
                 document.getElementById("Customer Name").focus();
                 alert(not_blank_validation_msg+'Customer Name');
                 return false;
             }
             if(!document.getElementById("Customer Name").value.match(/^[a-z A-Z 0-9]+$/)){
                 document.getElementById("Customer Name").focus();
                 alert(specialChar_validation_msg+'Customer Name');
                 return false;
             }

             if(document.getElementById("Contact No").value==""){
                 document.getElementById("Contact No").focus();
                 alert(not_blank_validation_msg+'Contact No');
                 return false;
             }
             if(!document.getElementById("Contact No").value.match(/^[0-9]+$/)){
                 document.getElementById("Contact No").focus();
                 // alert(not_blank_validation_msg+'Contact No');
                 alert("Contact No "+numeric_msg);
                 return false;
             }
             if(document.getElementById("Contact No").value.length<10){
                 document.getElementById("Contact No").focus();
                 alert(contactLength_msg);
                 return false;
             }

             if(document.getElementById("SaleTaxTypeID").value==''){
                 document.getElementById("SaleTaxTypeID").focus();
                 alert("Please select Sale Tax Type");
                 return false;
             }
             if (document.getElementById("GST No").value !='' && !isProperSerialCust(document.getElementById("GST No").value)){
                 alert("Only Special characters (-, /, _) are allowed in GST No field");
                 document.getElementById("GST No").value = '';
                 document.getElementById("GST No").focus();
                 return false;
             }
             var count = (document.getElementById("inventory_Table").rows.length);var total=0.0;
             for(var i=1;i<count;i++)
             {
                 if(document.getElementById("Part No." + i).value.length>0)
                 {
                     var qty=document.getElementById("Qty" + i).value;
                     var unitprice=document.getElementById("Unit Price"+i).value;
                     if(unitprice==0)
                     {
                         alert(not_blank_validation_msg+'Unit Price')
                         document.getElementById("Unit Price"+i).focus();
                         return false;
                     }
                     if(qty==0)
                     {
                         alert(not_blank_validation_msg+'Qty')
                         document.getElementById("Qty"+i).focus();
                         return false;
                     }
                     total=total+ (parseFloat(unitprice)*parseFloat(qty));
                 }
                 if(document.getElementById("BillTo" + i).value=="" && document.getElementById("Qty" + i).value!=""){
                     document.getElementById("BillTo" + i).focus();
                     alert(not_blank_dropdown_validation_msg+"BillTo");
                     return false;

                 }
             }
             if(total<=0.0)
             {
                 alert(partNomadat_validation_msg);
                 return false;
             }
             if(document.getElementById("Other Discount").value==""){
                 document.getElementById("Other Discount").value="0";
             }
             if(document.getElementById("CreditValue").value==""){
                 document.getElementById("CreditValue").value="0";
             }
             //  document.getElementById('btn_submit').disabled = 1
             document.getElementById("CounterSaleForm").submit();
         }

         function cancelInventory(){
             window.location.href='<%=cntxpath%>/inventoryAction.do?option=initCounterSale';
         }

</script>

<body onload="settodayDate()"></body>
<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><html:link action="/inventoryAction.do?option=initInvOptions"><bean:message key="label.common.spares" /></html:link></li>
            <li class="breadcrumb_link"><bean:message key="label.common.counterSale" /></li>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED" align="center">${message}</div>

    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.common.counterSale" />  </h1>
                <form action="<%=cntxpath%>/inventoryAction.do?option=addCounterSale" method="post" id="CounterSaleForm"  onsubmit="return false;">
                    <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a>@@<bean:message key="label.common.counterSale" />"/>
                    <input type="hidden" name="<%= Constants.TOKEN_KEY%>" value="<%= token%>" />
                    <input  type="hidden" name="customerId" id="customerId"  value="${inventoryForm.customerId}"/>
                    <input  type="hidden" name="TotalDiscountPercentage" id="totalDiscountPercentage"  value="${inventoryForm.totalDiscountPercentage}"/>
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="4" class="LiteBorder">
                        <tr>
                            <td>
                                <table width="90%" border="0" align="center" cellpadding="3" cellspacing="3" bordercolor=#cccccc >
                                    <tr height=20 bgcolor="#FFFFFF">
                                        <td align="right" ><label ><B><bean:message key="label.common.CustomerName" /> : </B></label></td>
                                        <td align="left" >
                                            <input name="cusName" type="text" id="Customer Name" value="${inventoryForm.customerName}" style="width:200px" maxlength="30"/>
                                        </td>
                                        <td align="right" ><label ><B><bean:message key="label.spare.customerLocationCode"/> : </B></label></td>
                                        <td align="left" >
                                            ${inventoryForm.customerCode} [ ${inventoryForm.customerLocation} ]
                                        </td>

                                        <td align="right" style="color:red"><label ><B><bean:message key="label.spare.amountDue" /> : </B></label></td>
                                        <td align="left" style="color:red">
                                            <fmt:parseNumber type="number" integerOnly="true"   value="${inventoryForm.paymentDue}"/>
                                        </td>

                                    </tr>
                                    <tr height=20 bgcolor="#FFFFFF">
                                        <td align="right" ><label ><B><bean:message key="label.common.contactno" /> : </B></label></td>
                                        <td align="left" >
                                            <input name="contactNo" type="text"  id="Contact No"  value="${inventoryForm.contactNo}"  maxlength="10"/>
                                        </td>
                                        <td align="right" ><label ><B><bean:message key="label.common.InvoiceDate" /> : </B></label></td>
                                        <td align="left" ><input name="invDate" type="text" id="Invoice Date" class="datepicker" value="" maxlength="10" onkeydown="return false;"/>
                                        </td>
                                        <td align="right" ><label ><B><bean:message key="label.spare.existingCreditLimit" /> : </B></label></td>
                                        <td align="left" >
                                            <fmt:parseNumber type="number" integerOnly="true"   value="${inventoryForm.availableCreditLimit}"/>
                                        </td>
                                    </tr>
                                    <tr height=20 bgcolor="#FFFFFF">
                                        <td align="right" ><label ><B><bean:message key="label.common.saleTaxType" /> : </B></label></td>
                                        <td align="left" bgcolor="#FFFFFF">
                                            <select name="saleTaxTypeID" id="SaleTaxTypeID" class="headingSpas" style="width:150px !important" >
                                                <option value="">--Select--</option>
                                                <c:forEach items="${saleTaxTypeList}" var="dataList">
                                                    <option value ="${dataList.value}">${dataList.label}</option>
                                                </c:forEach>

                                            </select>
                                        </td>
                                        <td align="right" ><label ><B><bean:message key="label.common.gstNo" /> : </B></label></td>
                                        <%--<td align="right" ><label ><B>GST No : </B></label></td>--%>
                                        <td align="left" style="white-space: nowrap">
                                            <html:text property="gstNo" styleId="GST No" maxlength="49" value="${inventoryForm.gstNo}"  onblur="this.value=TrimAll(this.value)" />
                                        </td>
                                    </tr>
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
                                            <td align="center" width="5%"><B><bean:message key="label.common.qty_small" /></B></td>
                                            <td align="center" width="5%"><B><bean:message key="label.common.unitprice_small" /></B></td>

                                            <td align="center" width="8%"><B><bean:message key="label.common.billto_small" /></B></td>
                                            <td align="center" width="5%"><B><bean:message key="label.common.discount_small" /> (%)</B></td>
                                            <td align="center" width="8%"><B><bean:message key="label.common.amt_small" /></B></td>

                                        </tr>
                                        <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                            <td align="center" >1</td>
                                            <td align="left" ><input type="text" name="partNo" id="Part No.1" value="" maxlength="30" onblur="getpartDesc(this, '1');this.value=TrimAll(this.value);" tabindex="1"/>
                                                <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' onclick="javascript:getpartDesc(document.getElementById('Part No.1'), '1');"><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                                <input type="hidden" name="partType" id="PartType1" value="" maxlength="20"  />
                                            </td>
                                            <td align="left" ><span id="Part Desc.1"></span><input name="partDesc" id="desc1" type="hidden" value=""/></td>
                                            <td align="left" >
                                                <input type="text" name="quantity" id="Qty1" value="" maxlength="7"  onblur="checkfloatvalue('1');CalculateAmount('1');" style="width:50px !important" tabindex="2"/>
                                                <input type="hidden" name="availQuantity" id="AvailQty1" value="" maxlength="7"  />
                                            </td>
                                            <td align="left" ><input type="text" name="unitprice" id="Unit Price1" value="" maxlength="4" style="text-align: right" readonly/></td>


                                            <td align="left" >
                                                <select name="billIdArr"  id="BillTo1"  class="headingSpas" onchange="getCalAmmountAfterDisc(this.value,'1')" >
                                                    <option value="">- Select -</option>
                                                    <c:forEach items="${billedList}" var="billedList">
                                                        <option value='${billedList.billId}@@${billedList.discount}@@${billedList.billdesc}' title='${billedList.billdesc}'>${billedList.billdesc} </option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td align="left" ><input type="text" name="percentDis" id="Discount1" value="" maxlength="4" onblur="getCalAmmountAfterDiscInput(this.value,'1')" style="width:70px !important"/>
                                                <input type="hidden" name="disAmmperPart" id="DiscountAmmount1" value="" maxlength="4"  style="width:70px !important" tabindex="3"/>
                                            </td>
                                            <td align="left" ><input type="text" name="amount" id="Amount1" value="" readonly style="text-align: right"/></td>
                                        </tr>

                                        <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                            <td align="center" >2</td>
                                            <td align="left" ><input type="text" name="partNo" id="Part No.2" value="" maxlength="30"  onblur="getpartDesc(this, '2');this.value=TrimAll(this.value);" tabindex="4" />
                                                <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' onclick="javascript:getpartDesc(document.getElementById('Part No.2'), '2');"><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                                <input type="hidden" name="partType" id="PartType2" value="" maxlength="20"  />
                                            </td>
                                            <td align="left" ><span id="Part Desc.2"></span><input name="partDesc" id="desc2" type="hidden" value=""/></td>
                                            <td align="left" ><input type="text" name="quantity" id="Qty2" value="" maxlength="7"  onblur="checkfloatvalue('2');CalculateAmount('2');" style="width:50px !important " tabindex="5"/>
                                                <input type="hidden" name="availQuantity" id="AvailQty2" value="" maxlength="7"  />
                                            </td>
                                            <td align="left" ><input type="text" name="unitprice" id="Unit Price2" value="" maxlength="4" style="text-align: right" readonly/></td>


                                            <td align="left" >
                                                <select name="billIdArr"  id="BillTo2"  class="headingSpas" onchange="getCalAmmountAfterDisc(this.value,'2')" >
                                                    <option value="">- Select -</option>
                                                    <c:forEach items="${billedList}" var="billedList">
                                                        <option value='${billedList.billId}@@${billedList.discount}@@${billedList.billdesc}' title='${billedList.billdesc}'>${billedList.billdesc} </option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td align="left" ><input type="text" name="percentDis" id="Discount2" value="" maxlength="4" onblur="getCalAmmountAfterDiscInput(this.value,'2')" style="width:70px !important"/>
                                                <input type="hidden" name="disAmmperPart" id="DiscountAmmount2" value="" maxlength="4"  style="width:70px !important" tabindex="6"/>
                                            </td>
                                            <td align="left" ><input type="text" name="amount" id="Amount2" value="" readonly style="text-align: right"/></td>
                                        </tr>


                                        <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                            <td align="center" >3</td>
                                            <td align="left" ><input type="text" name="partNo" id="Part No.3" value="" maxlength="30"  onblur="getpartDesc(this, '3');this.value=TrimAll(this.value);" tabindex="7" />
                                                <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' onclick="javascript:getpartDesc(document.getElementById('Part No.3'), '3');"><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                                <input type="hidden" name="partType" id="PartType3" value="" maxlength="20"  />
                                            </td>
                                            <td align="left" ><span id="Part Desc.3"></span><input name="partDesc" id="desc3" type="hidden" value=""/></td>

                                            <td align="left" ><input type="text" name="quantity" id="Qty3" value="" maxlength="7"  onblur="checkfloatvalue('3');CalculateAmount('3');" style="width:50px !important " tabindex="8"/>
                                                <input type="hidden" name="availQuantity" id="AvailQty3" value="" maxlength="7"  />
                                            </td>
                                            <td align="left" ><input type="text" name="unitprice" id="Unit Price3" value="" maxlength="4" style="text-align: right" readonly/></td>

                                            <td align="left" >
                                                <select name="billIdArr"  id="BillTo3"  class="headingSpas" onchange="getCalAmmountAfterDisc(this.value,'3')" >
                                                    <option value="">- Select -</option>
                                                    <c:forEach items="${billedList}" var="billedList">
                                                        <option value='${billedList.billId}@@${billedList.discount}@@${billedList.billdesc}' title='${billedList.billdesc}'>${billedList.billdesc} </option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td align="left" ><input type="text" name="percentDis" id="Discount3" value="" maxlength="4" onblur="getCalAmmountAfterDiscInput(this.value,'3')" style="width:70px !important" tabindex="9"/>
                                                <input type="hidden" name="disAmmperPart" id="DiscountAmmount3" value="" maxlength="4"  style="width:70px !important"/>
                                            </td>
                                            <td align="left" ><input type="text" name="amount" id="Amount3" value="" readonly style="text-align: right"/></td>
                                        </tr>


                                        <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                            <td align="center" >4</td>
                                            <td align="left" ><input type="text" name="partNo" id="Part No.4" value="" maxlength="30"  onblur="getpartDesc(this, '4');this.value=TrimAll(this.value);" tabindex="10"/>
                                                <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' onclick="javascript:getpartDesc(document.getElementById('Part No.4'), '4');"><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                                <input type="hidden" name="partType" id="PartType4" value="" maxlength="20"  />
                                            </td>
                                            <td align="left" ><span id="Part Desc.4"></span><input name="partDesc" id="desc4" type="hidden" value=""/></td>

                                            <td align="left" ><input type="text" name="quantity" id="Qty4" value="" maxlength="7"  onblur="checkfloatvalue('4');CalculateAmount('4');" style="width:50px !important " tabindex="11"/>
                                                <input type="hidden" name="availQuantity" id="AvailQty4" value="" maxlength="7"  />
                                            </td>
                                            <td align="left" ><input type="text" name="unitprice" id="Unit Price4" value="" maxlength="4" style="text-align: right" readonly/></td>

                                            <td align="left" >
                                                <select name="billIdArr"  id="BillTo4"  class="headingSpas" onchange="getCalAmmountAfterDisc(this.value,'4')" >
                                                    <option value="">- Select -</option>
                                                    <c:forEach items="${billedList}" var="billedList">
                                                        <option value='${billedList.billId}@@${billedList.discount}@@${billedList.billdesc}' title='${billedList.billdesc}'>${billedList.billdesc} </option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td align="left" ><input type="text" name="percentDis" id="Discount4" value="" maxlength="4" onblur="getCalAmmountAfterDiscInput(this.value,'4')" style="width:70px !important" tabindex="12"/>
                                                <input type="hidden" name="disAmmperPart" id="DiscountAmmount4" value="" maxlength="4"  style="width:70px !important"/>
                                            </td>
                                            <td align="left" ><input type="text" name="amount" id="Amount4" value="" readonly style="text-align: right"/></td>
                                        </tr>

                                        <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                            <td align="center" >5</td>
                                            <td align="left" ><input type="text" name="partNo" id="Part No.5" value="" maxlength="30"  onblur="getpartDesc(this, '5');this.value=TrimAll(this.value);" tabindex="13"/>
                                                <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' onclick="javascript:getpartDesc(document.getElementById('Part No.5'), '5');"><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                                <input type="hidden" name="partType" id="PartType5" value="" maxlength="20"  />
                                            </td>
                                            <td align="left" ><span id="Part Desc.5"></span><input name="partDesc" id="desc5" type="hidden" value=""/></td>

                                            <td align="left" ><input type="text" name="quantity" id="Qty5" value="" maxlength="7"  onblur="checkfloatvalue('5');CalculateAmount('5');" style="width:50px !important " tabindex="14"/>
                                                <input type="hidden" name="availQuantity" id="AvailQty5" value="" maxlength="7"  />
                                            </td>
                                            <td align="left" ><input type="text" name="unitprice" id="Unit Price5" value="" maxlength="4" style="text-align: right" readonly/></td>

                                            <td align="left" >
                                                <select name="billIdArr"  id="BillTo5"  class="headingSpas" onchange="getCalAmmountAfterDisc(this.value,'5')" >
                                                    <option value="">- Select -</option>
                                                    <c:forEach items="${billedList}" var="billedList">
                                                        <option value='${billedList.billId}@@${billedList.discount}@@${billedList.billdesc}' title='${billedList.billdesc}'>${billedList.billdesc} </option>
                                                    </c:forEach>
                                                </select>
                                            </td>
                                            <td align="left" ><input type="text" name="percentDis" id="Discount5" value="" maxlength="4" onblur="getCalAmmountAfterDiscInput(this.value,'5')" style="width:70px !important" tabindex="15"/>
                                                <input type="hidden" name="disAmmperPart" id="DiscountAmmount5" value="" maxlength="4"  style="width:70px !important"/>
                                            </td>
                                            <td align="left" ><input type="text" name="amount" id="Amount5" value="" readonly style="text-align: right"/></td>
                                        </tr>

                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bordercolor=#cccccc>
                                    <tr height=20 bgcolor="#FFFFFF">
                                        <td align="right" width="15%"><B><bean:message key="label.common.totalPartValue" /> </B></td>
                                        <td align="center" width="15%"><input type="text"  readonly class="headingSpas" value="" name="partValue" id="Total Part Value" style="text-align: right" /></td>
                                        <td  align="right" width="15%"><B><bean:message key="label.common.totalLubesValue" /> </B></td>
                                        <td align="center" width="15%"><input type="text"  readonly class="headingSpas" value="" name="lubesValue" id="Total Lubes Value" style="text-align: right"/></td>


                                    <input type="hidden"   class="headingSpas" value="0" name="partDiscount" id="Discount on Part" >  
                                    <td  align="right" width="15%"><B><bean:message key="label.common.otherdiscount" />  </B></td>
                                    <td align="center">
                                        <input type="hidden"   class="headingSpas" value="0" name="lubesDiscount" id="Discount on Lubes"  >
                                        <input type="text"   class="headingSpas" value="0" name="otherDiscount" maxlength="7" id="Other Discount" onblur="getTotalAmmAfterOtherDis(this.value);" >

                                    </td>   
                                    <td  align="right" width="15%"><B><bean:message key="label.warranty.total" />  <bean:message key="label.common.amt_small" /></B></td>
                                    <td align="right"><input type="text"  readonly class="headingSpas" value="" name="finalamount" id="Total" style="text-align: right"/></td>

                        </tr>

                        <tr height=20 bgcolor="#FFFFFF">
                            <td colspan="7" align="right"><B><%--<bean:message key="label.counterSale.creditAmount" />--%> </B></td>
                            <td align="left" style="padding-left: 15px"><input type="hidden"  class="headingSpas" value="0" name="creditValue" id="CreditValue" maxlength="7" onkeyup="getCreditAmm(this.value);" /></td>
                        </tr>

                        <tr height=20 bgcolor="#FFFFFF">
                            <td align="left" colspan="2"><input type="button" class="headingSpas1" value="Add More" name="addmore" id="Add More" onclick="addRowSpares('inventory_Table')"/></td>
                            <td width="10%" align="right">
                                <input name="input" type="button" value="Submit" class="submit" id="btn_submit" onclick="validateInventory('save')" >
                            </td>
                            <td width="10%" align="left"><input type="button" class="headingSpas1" value="Cancel" name="cancel" id="Cancel" onclick="cancelInventory();"/></td>
                            <td width="20%" align="right">
                                <input name="input" type="button" value="Proforma Invoice" class="submit" onclick="validateInventory('pro')" >

                                <input type="hidden" name="eType" id="eType" value=""/></td>
                        </tr>
                    </table>

                </form>
            </div>
        </div>
    </center>
</div>