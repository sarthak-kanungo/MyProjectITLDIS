<%--
    Document   : addInventory
    Created on : Nov 3, 2014, 2:48:19 PM
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
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%> 
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            //    Vector userFunctionalities = (Vector) session.getAttribute("userFun");

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DATE);
%>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_6.js"></script>
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
        if(count<101){
            var tbody = document.getElementById(in_tbl_name).getElementsByTagName("TBODY")[0];
            // create row
            var row = document.createElement("TR");
            row.height="20px";
            row.bgColor="#FFFFFF";
            var td0 = document.createElement("TD");
    <%--td0.style.paddingLeft="10px";--%>
                td0.align ="center";
                var strHtml0 =(count);
                td0.innerHTML = strHtml0;

                var td1 = document.createElement("TD")
                td1.align="left";
                var strHtml1 = "<input type=\"text\" name=\"partNo\" id=\"Part No."+count+"\" value=\"\" maxlength=\"30\"  onblur=\"getCheckRestrictedPart(this, "+count+");this.value=TrimAll(this.value);\"/>"+  //getpartDesc(this,"+count+");
                    "<img alt=\"\" src=\"${pageContext.request.contextPath}/image/next_1.gif\">"+// onclick=\"javascript:getpartDesc(document.getElementById(\"Part No."+count+"\"), "+count+");\"
                "<img alt=\"\"  style=\"visibility:hidden;\" align=\"top\" border=\"0\" src=\"${pageContext.request.contextPath}/image/load.gif\"/>";
                td1.innerHTML = strHtml1.replace(/!count!/g, count);
       
                var td2 = document.createElement("TD")
    <%--td2.style.paddingLeft="10px";--%>td2.align="left";
                var strHtml2 = "<span id=\"Part Desc."+count+"\"></span><input name=\"partDesc\" id=\"desc"+count+"\" type=\"hidden\" value=\"\"/>";
                td2.innerHTML = strHtml2.replace(/!count!/g, count);

                var td3 = document.createElement("TD")
                var strHtml3 = "<input type=\"text\" name=\"unitprice\" id=\"Unit Price"+count+"\" value=\"\" maxlength=\"7\" style=\"text-align: right;\"/>"
                    +"<input  id=\"cat"+count+"\" type=\"hidden\" value=\"\"/>";
                td3.innerHTML = strHtml3.replace(/!count!/g, count);

                var td4 = document.createElement("TD")
    <%--td4.style.paddingLeft="10px";--%>
                var strHtml4 = "<input type=\"text\" name=\"quantity\" id=\"Qty"+count+"\" value=\"\" maxlength=\"7\" onblur=\"checkfloatvalue(this,"+count+");CalculateAmount("+count+");\"/>";
                td4.innerHTML = strHtml4.replace(/!count!/g, count);

                var td5 = document.createElement("TD")
    <%--td5.style.paddingLeft="10px";--%>
                var strHtml5 = "<input type=\"text\" name=\"amount\" id=\"Amount"+count+"\" value=\"\" readonly style=\"text-align: right;\"/>";
                td5.innerHTML = strHtml5.replace(/!count!/g, count);

                row.appendChild(td0);
                row.appendChild(td1);
                row.appendChild(td2);
                row.appendChild(td3);
                row.appendChild(td4);
                row.appendChild(td5);
                tbody.appendChild(row);
                count = parseInt(count) + 1;
            }
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
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var part_No1 = obj.value;
            var part_No=part_No1.toUpperCase();
            document.getElementById("Part No."+row).value = part_No;
            if(part_No==""){
                document.getElementById("Part Desc." + row).innerHTML = "";
                document.getElementById("desc" + row).value = '';
                document.getElementById("cat" + row).value = '';
                document.getElementById("Part No." + row).value = '';
                document.getElementById("Part No." + row).title='';
                document.getElementById("Unit Price" + row).value = '';
                document.getElementById("Qty" + row).value = '';
                document.getElementById("Amount"+row).value ='';
                CalculateAmount(row);
                return false;
            }

            if(ck_code.test(part_No)){
                obj.focus();
                alert(specialChar_validation_msg+"Part No.");
                return false;
            }
            var todate = new Date().getTime();
            var strURL = '<%=cntxpath%>/masterAction.do?option=getPartPriceBypartNo&partno=' + part_No + '&tm=' + todate;
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
                        sparex = new Array();
                        part_No = document.getElementsByName("partNo")
    <%--partDesc = document.getElementsByName("partDesc")--%>
    <%--unitPrice = document.getElementsByName("unitPrice")--%>
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
    <%--document.getElementById("comptype"+row).value=''--%>
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
                        document.getElementById("cat" + row).value = valArr[1];
                        document.getElementById("Unit Price" + row).value = valArr[2];
    <%--document.getElementById("img" + row).style.dispaly = "none";--%>
                           

                    } else
                    {
                        document.getElementById("Part Desc." + row).innerHTML = "";
                        document.getElementById("desc" + row).value = '';
                        document.getElementById("cat" + row).value = '';
                        document.getElementById("Part No." + row).value = '';
                        document.getElementById("Part No." + row).title='';
                        document.getElementById("Unit Price" + row).value = '';
                        document.getElementById("Qty" + row).value = '';
                        document.getElementById("Amount"+row).value ='';
                        CalculateAmount(row);
    <%--document.getElementById("img" + row).style.dispaly = "none";--%>
                    }
                }
            }
        }
            
        function CalculateAmount(row)
        {
            var quantity = document.getElementById("Qty" + row).value
            if (quantity == null || quantity == "") {
                document.getElementById("Qty" + row).value = '0';
                quantity = 0;
            }
            var unitPrice = document.getElementById("Unit Price"+row).value;
            if (unitPrice == null || unitPrice == "") {
                document.getElementById("Unit Price"+row).value = '0';
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
            var total=0;
            var partPriceAmount = quantity * unitPrice;
            var taxAmt= document.getElementById("taxAmtId").value;
            document.getElementById("Amount"+row).value = partPriceAmount.toFixed(2);

            var count = (document.getElementById('inventory_Table').rows.length);
            for(var i=1;i<count;i++)
            {
                if(document.getElementById("Part No." + i).value == ''){}
                else{
                    var amt= document.getElementById("Amount"+i).value;
                    total=total+ parseFloat(amt);
                }
            }
            document.getElementById("Total").value =total.toFixed(2);
            document.getElementById("totalAmountId").value = (parseFloat(total) + parseFloat(taxAmt)).toFixed(2);
        }

        function finalCalAmount(){
            var subTotal = document.getElementById("Total").value;
            var tax = document.getElementById("taxAmtId").value;
                                    
            if(parseFloat(tax) < 0){
                alert("Tax value should be greater than or equal to 0.");
                tax = 0;
                document.getElementById("totalAmountId").value = subTotal;
            }
            if(parseFloat(tax) > parseFloat(subTotal)){
                alert("Tax value should be less than Sub Total value.");
                tax = 0;
                document.getElementById("totalAmountId").value = subTotal;
            }
            if (tax == "") {
                document.getElementById("taxAmtId").value = '0';
            }else{
                document.getElementById("totalAmountId").value = (parseFloat(tax)+parseFloat(subTotal)).toFixed(2);
            }
        }
                             
        function settodayDate(){
            document.getElementById("Transaction Date").value=tdays+'/'+tmon+'/'+<%=year%>;
        }

        function validateInventory(){
            if(document.getElementById("Bill No.").value==""){
                document.getElementById("Bill No.").focus();
                alert(not_blank_validation_msg+'Bill No.');
                document.getElementById("Bill No.").select();
                return false;
            }

            if(ck_code.test(document.getElementById("Bill No.").value)){
                document.getElementById("Bill No.").focus();
                alert(specialChar_validation_msg+"Bill No.");
                document.getElementById("Bill No.").select();
                return false;
            }

            if(document.getElementById("Bill Date").value==""){
                document.getElementById("Bill Date").focus();
                alert(not_blank_validation_msg+'Bill Date');
                document.getElementById("Bill Date").select();
                return false;
            }
        
            var billDate=document.getElementById("Bill Date").value.split('/');
            var sdate=billDate[2]+'/'+billDate[1]+'/'+billDate[0];
            var billdt=new Date(sdate).getTime();
            if(today < billdt || backDate > billdt){
                document.getElementById("Bill Date").focus();
                alert(invoice_bill_currentDate_validation_msg+noOfDays+dat_validation_msg2);
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
            }
            if(total<=0.0)
            {
                alert(partNomadat_validation_msg);
                return false;
            }
                                    
                                   

            document.getElementById("addInventory").submit();
            document.getElementById("inventoryBtn").disabled=true;
        }
        function cancelInventory(){
            window.location.href='<%=cntxpath%>/inventoryAction.do?option=initaddInventory';
        }
    
        function checkfloatvalue(field,row){
            var fexp =/^\d{0,7}(\.\d{0,2}){0,1}$/;
            var noexp = /^[0-9\b]+$/;<%--/^\d{0,200}$/g;--%>
            var category = document.getElementById("cat"+row).value;
            if(category.toUpperCase()=='LUBES' && trim(field.value)!='')
            {
    <%--var temp = fexp.test(field.value);alert('lub'+temp)--%>
                if(!fexp.test(field.value))
                {
                    //field.value.replace(/.$/,'');
                    alert(proper_validation_msg);
                    field.value='';
                    return false;
                }
            }
            else{
                if(!noexp.test(field.value))
                {
                    alert('Qty '+numeric_msg);
                    field.value='';
                    return false;
                }
            }
        }

        function getCheckRestrictedPart(val,row){
            var part_No = val.value;
            var todate = new Date().getTime();
            var strURL = '<%=cntxpath%>/inventoryAction.do?option=getCheckRestrictedPart&partNo=' + part_No + '&tm=' + todate;
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readystate == 'complete')
                {
                    var result= trimS(xmlHttp.responseText);
                    //alert(row);
                    if(result!=''){
               /*          alert(partNotAllowedForAddInv_validation_msg+'" '+result+' "'+ partNotAllowedForAddInv_validation_msg1);
                        document.getElementById("desc" + row).value = '';
                        document.getElementById("cat" + row).value = '';
                        document.getElementById("Part No." + row).value = '';
                        document.getElementById("Part No." + row).title='';
                        document.getElementById("Unit Price" + row).value = '';
                        document.getElementById("Qty" + row).value = '';
                        document.getElementById("Amount"+row).value ='';
                        return false; */

                    }else{
                        getpartDesc(val, row);
                    }
                }
            };
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);
                    
        }
</script>

<body onload="settodayDate()"></body>
<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><html:link action="/inventoryAction.do?option=initInvOptions"><bean:message key="label.common.spares_small" /></html:link></li>
            <li class="breadcrumb_link"><bean:message key="label.common.addInventory" /></li>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED" align="center">${message}</div>

    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.common.addInventory" /></h1>
                <form action="<%=cntxpath%>/inventoryAction.do?option=addInventoryToDB" method="post" id="addInventory" name="addInventory1" onsubmit="return false;">
                    <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.inventory" /></a>@@<bean:message key="label.common.addInventory" />"/>
                    <input type="hidden" name="<%= Constants.TOKEN_KEY%>" value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY)%>" />
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="4" class="LiteBorder">
                        <%--<tr bgcolor="#eeeeee" height="20">
                            <td  class="headingSpas hText" align="center" style="padding:3px;">
                                <label>
                                    <B><bean:message key="label.common.addInventory"/></B>
                                </label>
                            </td>
                        </tr>--%>
                        <tr>
                            <td>
                                <table width="100%" border="0" align="center" cellpadding="1" cellspacing="1" bordercolor=#cccccc >
                                    <tr height=20 bgcolor="#FFFFFF">
                                        <td align="right" width="10%"><label ><B><bean:message key="label.common.billNo" />: </B></label></td>
                                        <td align="left" width="10%"><input name="billNo" type="text" id="Bill No." value="" style="width:200px"/>
                                        </td>
                                        <td align="right" width="10%"><label ><B><bean:message key="label.common.billdate" />: </B></label></td>
                                        <td align="left" width="10%"><input name="billdate" type="text" class="datepicker" id="Bill Date"  value="" onkeydown="return false;"/>
                                        </td>
                                        <td align="right" width="10%"><label ><B><bean:message key="label.common.vandorName" />: </B></label></td>
                                        <td align="left" width="10%"><input name="dealerName" type="text" id="Dealer Name" value="" style="width:200px"/>
                                        </td>
                                        <td align="right" width="15%"><label ><B><bean:message key="label.common.transDate" />: </B></label></td>
                                        <td align="left" width="10%"><input name="transdate" type="text"  id="Transaction Date"  value="" readonly/>
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
                                            <td align="left" width="45%"><B><bean:message key="label.common.partdesc_small" /></B></td>
                                            <td align="center" width="8%"><B><bean:message key="label.common.unitprice_small" /></B></td>
                                            <td align="center" width="5%"><B><bean:message key="label.common.qty_small" /></B></td>
                                            <td align="center" width="8%"><B><bean:message key="label.common.amt_small" /></B></td>
                                        </tr>

                                        <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                            <td align="center" >1</td>
                                            <td align="left" ><input type="text" name="partNo" id="Part No.1" value=""  maxlength="30"  onblur="getCheckRestrictedPart(this, '1');this.value=TrimAll(this.value);"/>
                                                <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' <%--onclick="javascript:getpartDesc(document.getElementById('Part No.1'), '1');"--%>><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                            </td>
                                            <td align="left" ><span id="Part Desc.1"></span><input name="partDesc" id="desc1" type="hidden" value=""/></td>
                                            <td align="left" ><input type="text" name="unitprice" id="Unit Price1" value="" maxlength="7" style="text-align: right;" onblur="CalculateAmount('1')"/>
                                                <input  id="cat1" type="hidden" value=""/>
                                            </td>
                                            <td align="left" ><input type="text" name="quantity" id="Qty1" value="" maxlength="7" onblur="checkfloatvalue(this,'1');CalculateAmount('1');"/></td>
                                            <td align="left" ><input type="text" name="amount" id="Amount1" value="" style="text-align: right;" readonly/></td>
                                        </tr>
                                        <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                            <td align="center" >2</td>
                                            <td align="left" ><input type="text" name="partNo" id="Part No.2" value="" maxlength="30"  onblur="getCheckRestrictedPart(this, '2');this.value=TrimAll(this.value);"/>
                                                <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' <%--onclick="javascript:getpartDesc(document.getElementById('Part No.2'), '2');"--%>><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                            </td>
                                            <td align="left" ><span id="Part Desc.2"></span><input name="partDesc" id="desc2" type="hidden" value=""/></td>
                                            <td align="left" ><input type="text" name="unitprice" id="Unit Price2" value="" maxlength="7" style="text-align: right;" onblur="CalculateAmount('2')"/>
                                                <input  id="cat2" type="hidden" value=""/>
                                            </td>
                                            <td align="left" ><input type="text" name="quantity" id="Qty2" value="" maxlength="7"  onblur="checkfloatvalue(this,'2');CalculateAmount('2');"/></td>
                                            <td align="left" ><input type="text" name="amount" id="Amount2" value="" readonly style="text-align: right;"/></td>
                                        </tr>
                                        <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                            <td align="center" >3</td>
                                            <td align="left" ><input type="text" name="partNo" id="Part No.3" value="" maxlength="30"  onblur="getCheckRestrictedPart(this, '3');this.value=TrimAll(this.value);"/>
                                                <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' <%--onclick="javascript:getpartDesc(document.getElementById('Part No.3'), '3');"--%>><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                            </td>
                                            <td align="left" ><span id="Part Desc.3"></span><input name="partDesc" id="desc3" type="hidden" value=""/></td>
                                            <td align="left" ><input type="text" name="unitprice" id="Unit Price3" value="" maxlength="7" style="text-align: right;" onblur="CalculateAmount('3')"/>
                                                <input  id="cat3" type="hidden" value=""/>
                                            </td>
                                            <td align="left" ><input type="text" name="quantity" id="Qty3" value="" maxlength="7" onblur="checkfloatvalue(this,'3');CalculateAmount('3');"/></td>
                                            <td align="left" ><input type="text" name="amount" id="Amount3" value="" readonly style="text-align: right;"/></td>
                                        </tr>
                                        <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                            <td align="center" >4</td>
                                            <td align="left" ><input type="text" name="partNo" id="Part No.4" value="" maxlength="30"  onblur="getCheckRestrictedPart(this, '4');this.value=TrimAll(this.value);"/>
                                                <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' <%--onclick="javascript:getpartDesc(document.getElementById('Part No.4'), '4');"--%>><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                            </td>
                                            <td align="left" ><span id="Part Desc.4"></span><input name="partDesc" id="desc4" type="hidden" value=""/></td>
                                            <td align="left" ><input type="text" name="unitprice" id="Unit Price4" value="" maxlength="7" style="text-align: right;" onblur="CalculateAmount('4')"/>
                                                <input  id="cat4" type="hidden" value=""/>
                                            </td>
                                            <td align="left" ><input type="text" name="quantity" id="Qty4" value="" maxlength="7" onblur="checkfloatvalue(this,'4');CalculateAmount('4');"/></td>
                                            <td align="left" ><input type="text" name="amount" id="Amount4" value="" readonly style="text-align: right;"/></td>
                                        </tr>
                                        <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                            <td align="center" >5</td>
                                            <td align="left" ><input type="text" name="partNo" id="Part No.5" value="" maxlength="30"  onblur="getCheckRestrictedPart(this, '5');this.value=TrimAll(this.value);"/>
                                                <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' <%--onclick="javascript:getpartDesc(document.getElementById('Part No.5'), '5');"--%>><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                            </td>
                                            <td align="left" ><span id="Part Desc.5"></span><input name="partDesc" id="desc5" type="hidden" value=""/></td>
                                            <td align="left" ><input type="text" name="unitprice" id="Unit Price5" value="" maxlength="7" style="text-align: right;" onblur="CalculateAmount('5')"/>
                                                <input  id="cat5" type="hidden" value=""/>
                                            </td>
                                            <td align="left" ><input type="text" name="quantity" id="Qty5" value="" maxlength="7" onblur="checkfloatvalue(this,'5');CalculateAmount('5');"/></td>
                                            <td align="left" ><input type="text" name="amount" id="Amount5" value="" readonly style="text-align: right;"/></td>
                                        </tr>
                                    </tbody>
                                </table>
                            </td>
                        </tr>
                        <tr>
                            <td>
                                <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bordercolor=#cccccc>
                                    <tr height=20 bgcolor="#FFFFFF">
                                        <td colspan="2" align="right"><B><bean:message key="label.spare.subTotal" /></B></td>
                                        <td align="right"><input type="text"  readonly class="headingSpas" value="" name="finalamount" id="Total"/></td>
                                        <td colspan="2" align="right"><B><bean:message key="label.spare.tax" /></B></td>
                                        <td align="right"><input type="text" class="headingSpas" value="0" name="taxamount" id="taxAmtId" onblur="finalCalAmount();"/></td>
                                        <td colspan="2" align="right"><B><bean:message key="label.common.amt_small" /></B></td>
                                        <td align="right"><input type="text" readonly class="headingSpas" value="" name="totalFinalamount" id="totalAmountId"/></td>
                                    </tr>
                                    <tr height=20 bgcolor="#FFFFFF">
                                        <td align="left" width="80%"><input type="button" class="headingSpas1" value="Add More" name="addmore" id="Add More" onclick="addRowSpares('inventory_Table')"/></td>
                                        <td width="10%" align="right"><input type="button" class="headingSpas1" value="Submit" id="inventoryBtn" name="invAdd"  onclick="validateInventory();"/></td>
                                        <td width="10%" align="left"><input type="button" class="headingSpas1" value="Cancel" name="cancel" id="Cancel" onclick="cancelInventory();"/></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </center>
</div>