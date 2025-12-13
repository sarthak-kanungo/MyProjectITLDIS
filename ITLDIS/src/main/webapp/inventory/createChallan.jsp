
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

            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DATE);
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
   function addRowSpares(in_tbl_name)
    {
        var count = (document.getElementById(in_tbl_name).rows.length);//alert(count)
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
        var strHtml1 = "<input type=\"text\" name=\"partNo\" id=\"Part No."+count+"\" value=\"\" maxlength=\"30\" style=\"width: 200px;\" onblur=\"getpartDesc(this,"+count+");\"/>"+
            "<img alt=\"\" src=\"${pageContext.request.contextPath}/image/next_1.gif\" onclick=\"javascript:getpartDesc(document.getElementById(\"Part No."+count+"\"), "+count+");\">"+
            "<img alt=\"\"  style=\"visibility:hidden;\" align=\"top\" border=\"0\" src=\"${pageContext.request.contextPath}/image/load.gif\"/>";
        td1.innerHTML = strHtml1.replace(/!count!/g, count);

        var td2 = document.createElement("TD")
        <%--td2.style.paddingLeft="10px";--%>
        var strHtml2 = "<span id=\"Part Desc."+count+"\"></span><input name=\"partDesc\" id=\"desc"+count+"\" type=\"hidden\" value=\"\"/>";
        td2.innerHTML = strHtml2.replace(/!count!/g, count);

        var td3 = document.createElement("TD")
        var strHtml3 = "<input type=\"text\" name=\"unitprice\" id=\"Unit Price"+count+"\" value=\"\" maxlength=\"7\"/>";
        td3.innerHTML = strHtml3.replace(/!count!/g, count);

        var td4 = document.createElement("TD")
        <%--td4.style.paddingLeft="10px";--%>
        var strHtml4 = "<input type=\"text\" name=\"quantity\" id=\"Qty"+count+"\" value=\"\" maxlength=\"7\" onblur=\"CalculateAmount("+count+");\"/>";
        td4.innerHTML = strHtml4.replace(/!count!/g, count);

        var td5 = document.createElement("TD")
        <%--td5.style.paddingLeft="10px";--%>
        var strHtml5 = "<input type=\"text\" name=\"amount\" id=\"Amount"+count+"\" value=\"\" readonly/>";
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
        var part_No = obj.value; //alert(part_No);
        if(part_No==""){
           obj.focus();
           alert(not_blank_validation_msg+'Part No.');
           obj.select();
           return false;
        }

        if(ck_code.test(part_No)){
           obj.focus();
           alert(specialChar_validation_msg+"Part No.");
           obj.select();
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
                            document.getElementById("Unit Price" + row).value = valArr[2];
                            document.getElementById("img" + row).style.dispaly = "none";


                        } else
                        {
                            document.getElementById("Part Desc." + row).innerHTML = "";
                            document.getElementById("desc" + row).value = '';
                            document.getElementById("Part No." + row).value = '';
                            document.getElementById("Part No." + row).title="";
                            document.getElementById("Unit Price" + row).value = '';
                            document.getElementById("img" + row).style.dispaly = "none";
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
        total=document.getElementById("Total").value;
        if (total == null || total == "") {
            total = 0;
        }
        total = parseFloat(total);
        var partPriceAmount = quantity * unitPrice;
        total=total+ parseFloat(partPriceAmount);<%--alert(partPriceAmount+'---'+total);--%>
        document.getElementById("Amount"+row).value = partPriceAmount.toFixed(2);
        document.getElementById("Total").value =total.toFixed(2);;
    }

   <%-- function settodayDate(){
       document.getElementById("Transaction Date").value=tdays+'/'+tmon+'/'+<%=year%>;
    }--%>
    function validateInventory(){
        if(document.getElementById("Challan No").value==""){
           document.getElementById("Challan No").focus();
            alert(not_blank_validation_msg+'Challan No');
            document.getElementById("Challan No").select();
            return false;
        }

        if(ck_code.test(document.getElementById("Challan No").value)){
           document.getElementById("Challan No").focus();
            alert(specialChar_validation_msg+"Challan No");
            document.getElementById("Challan No").select();
            return false;
        }

        if(document.getElementById("Challan Date").value==""){
           document.getElementById("Challan Date").focus();
            alert(not_blank_validation_msg+'Challan Date');
            document.getElementById("Challan Date").select();
            return false;
        }
        if(document.getElementById("Type").value==""){
           document.getElementById("Type").focus();
            alert(not_blank_dropdown_validation_msg+'Type');
            document.getElementById("Type").select();
            return false;
        }
        if(document.getElementById("Name").value==""){
           document.getElementById("Name").focus();
            alert(not_blank_validation_msg+'Name');
            document.getElementById("Name").select();
            return false;
        }

       <%-- var billDate=document.getElementById("Challan Date").value.split('/');
        var sdate=billDate[2]+'/'+billDate[1]+'/'+billDate[0];
        var billdt=new Date(sdate).getTime();
        if(today < billdt || backDate > billdt){
            document.getElementById("Challan Date").focus();
            alert(invoice_currentDate_validation_msg+noOfDays+dat_validation_msg2);
            return false;
        }--%>

         var count = (document.getElementById("inventory_Table").rows.length);
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
             }
         }
        document.getElementById("addInventory").submit();
    }
    function cancelInventory(){
    window.location.href='<%=cntxpath%>/inventoryAction.do?option=createChallan';
    }
</script>

<%--<body onload="settodayDate()"></body>--%>
 <div class="contentmain1">
     <div class="breadcrumb_container">
         <ul class="hText">
             <li class="breadcrumb_link"><html:link action="/inventoryAction.do?option=initInvOptions"><bean:message key="label.common.inventory" /></html:link></li>
             <li class="breadcrumb_link"><bean:message key="label.common.createChallan" /></li>
         </ul>
     </div>
<div class="message" id="msg_saveFAILED" align="center">${message}</div>

 <center>
     <div class="content_right1">
         <div class="con_slidediv2" style="position: relative;width: 100%">
             <h1 class="hText"><bean:message key="label.common.createChallan" /></h1>
             <form action="<%=cntxpath%>/inventoryAction.do?option=saveChallan" method="post" id="addInventory" name="addInventory1" onsubmit="return false;">
             <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.inventory" /></a>@@<bean:message key="label.common.createChallan" />"/>
             <table width="100%" border="0" align="center" cellpadding="0" cellspacing="4" class="LiteBorder">
                 <tr bgcolor="#eeeeee" height="20">
                     <td  class="headingSpas hText" align="center" style="padding:3px;">
                         <label>
                             <B><bean:message key="label.common.createChallan"/></B>
                         </label>
                     </td>
                 </tr>
                 <tr>
                     <td>
                         <table width="90%" border="0" align="center" cellpadding="3" cellspacing="3" bordercolor=#cccccc >
                             <tr height=20 bgcolor="#FFFFFF">
                                 <td align="right" ><label ><B><bean:message key="label.common.challanNo" />. : </B></label></td>
                                 <td align="left" ><input name="challanNo" type="text" id="Challan No" value="" maxlength="30" style="width:200px"/>
                                 </td>
                                 <td align="right" ><label ><B><bean:message key="label.common.challanDate" /> : </B></label></td>
                                 <td align="left" ><input name="challanDate" type="text" class="datepicker" id="Challan Date" maxlength="10" value="" onkeydown="return false;"/>
                                 </td>
                             </tr>
                             <tr height=20 bgcolor="#FFFFFF">
                                 <td align="right" ><label ><B><bean:message key="label.common.type" /> : </B></label></td>
                                 <td align="left" >
                                  
                                <select name="type"  id="Type" style="width:200px !important " class="headingSpas"  >
                                        <option value="">--Select Here--</option>
                                        <option value="Returnable">Returnable</option>
                                        <option value="Non Returnable">Non Returnable</option>
                                       <%-- <c:forEach items="${PendigInvoiceList}" var="PendigInvoiceList">
                                            <option value="${PendigInvoiceList.invNo}" >${PendigInvoiceList.invNo}</option>
                                        </c:forEach>   onchange="getInvoiceData(this.value,'${flag}')" --%>
                                    </select>

                                 </td>
                                 <td align="right" ><label ><B><bean:message key="label.common.name" /> : </B></label></td>
                                 <td align="left" ><input name="name" type="text"  id="Name" value="" maxlength="30"/>
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
                                 <td align="center" width="5%"><B><bean:message key="label.common.sno" /></B></td>
                                 <td align="left" width="25%"><B><bean:message key="label.common.partno_small" /></B></td>
                                 <td align="left" width="40%"><B><bean:message key="label.common.partdesc_small" /></B></td>
                                 <td align="center" width="10%"><B><bean:message key="label.common.unitprice_small" /></B></td>
                                 <td align="center" width="5%"><B><bean:message key="label.common.qty_small" /></B></td>
                                 <td align="center" width="10%"><B><bean:message key="label.common.amt_small" /></B></td>
                             </tr>

                             <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                 <td align="center" >1</td>
                                 <td align="left" ><input type="text" name="partNo" id="Part No.1" value="" maxlength="30" style="width: 200px" onblur="getpartDesc(this, '1');"/>
                                    <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' onclick="javascript:getpartDesc(document.getElementById('Part No.1'), '1');"><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                 </td>
                                 <td align="left" ><span id="Part Desc.1"></span><input name="partDesc" id="desc1" type="hidden" value=""/></td>
                                 <td align="left" ><input type="text" name="unitprice" id="Unit Price1" value="" maxlength="7"/></td>
                                 <td align="left" ><input type="text" name="quantity" id="Qty1" value="" maxlength="7" onblur="CalculateAmount('1');"/></td>
                                 <td align="left" ><input type="text" name="amount" id="Amount1" value="" readonly/></td>
                             </tr>
                              <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                 <td align="center" >2</td>
                                 <td align="left" ><input type="text" name="partNo" id="Part No.2" value="" maxlength="30" style="width: 200px" onblur="getpartDesc(this, '2');"/>
                                 <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' onclick="javascript:getpartDesc(document.getElementById('Part No.2'), '2');"><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                 </td>
                                 <td align="left" ><span id="Part Desc.2"></span><input name="partDesc" id="desc2" type="hidden" value=""/></td>
                                 <td align="left" ><input type="text" name="unitprice" id="Unit Price2" value="" maxlength="7"/></td>
                                 <td align="left" ><input type="text" name="quantity" id="Qty2" value="" maxlength="7" onblur="CalculateAmount('2');"/></td>
                                 <td align="left" ><input type="text" name="amount" id="Amount2" value="" readonly/></td>
                             </tr>
                              <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                 <td align="center" >3</td>
                                 <td align="left" ><input type="text" name="partNo" id="Part No.3" value="" maxlength="30" style="width: 200px" onblur="getpartDesc(this, '3');"/>
                                 <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' onclick="javascript:getpartDesc(document.getElementById('Part No.3'), '3');"><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                 </td>
                                 <td align="left" ><span id="Part Desc.3"></span><input name="partDesc" id="desc3" type="hidden" value=""/></td>
                                 <td align="left" ><input type="text" name="unitprice" id="Unit Price3" value="" maxlength="7"/></td>
                                 <td align="left" ><input type="text" name="quantity" id="Qty3" value="" maxlength="7" onblur="CalculateAmount('3');"/></td>
                                 <td align="left" ><input type="text" name="amount" id="Amount3" value="" readonly/></td>
                             </tr>
                             <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                 <td align="center" >4</td>
                                 <td align="left" ><input type="text" name="partNo" id="Part No.4" value="" maxlength="30" style="width: 200px" onblur="getpartDesc(this, '4');"/>
                                 <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' onclick="javascript:getpartDesc(document.getElementById('Part No.4'), '4');"><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                 </td>
                                 <td align="left" ><span id="Part Desc.4"></span><input name="partDesc" id="desc4" type="hidden" value=""/></td>
                                 <td align="left" ><input type="text" name="unitprice" id="Unit Price4" value="" maxlength="7"/></td>
                                 <td align="left" ><input type="text" name="quantity" id="Qty4" value="" maxlength="7" onblur="CalculateAmount('4');"/></td>
                                 <td align="left" ><input type="text" name="amount" id="Amount4" value="" readonly/></td>
                             </tr>
                             <tr height=20 bgcolor="#FFFFFF" class="headingSpas">
                                 <td align="center" >5</td>
                                 <td align="left" ><input type="text" name="partNo" id="Part No.5" value="" maxlength="30" style="width: 200px" onblur="getpartDesc(this, '5');"/>
                                 <img alt="" src='${pageContext.request.contextPath}/image/next_1.gif' onclick="javascript:getpartDesc(document.getElementById('Part No.5'), '5');"><img alt=''  style='visibility:hidden;' align="top" border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                 </td>
                                 <td align="left" ><span id="Part Desc.5"></span><input name="partDesc" id="desc5" type="hidden" value=""/></td>
                                 <td align="left" ><input type="text" name="unitprice" id="Unit Price5" value="" maxlength="7"/></td>
                                 <td align="left" ><input type="text" name="quantity" id="Qty5" value="" maxlength="7" onblur="CalculateAmount('5');"/></td>
                                 <td align="left" ><input type="text" name="amount" id="Amount5" value="" readonly/></td>
                             </tr>
                             </tbody>
                         </table>
                     </td>
                 </tr>
                 <tr>
                     <td>
                         <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1" bordercolor=#cccccc>
                             <tr height=20 bgcolor="#FFFFFF">
                                 <td colspan="2" align="right"><B><bean:message key="label.common.amt_small" /></B></td>
                                 <td align="center"><input type="text"  readonly class="headingSpas" value="" name="finalamount" id="Total"/></td>
                             </tr>
                             <tr height=20 bgcolor="#FFFFFF">
                                 <td align="left" width="80%"><input type="button" class="headingSpas1" value="Add More" name="addmore" id="Add More" onclick="addRowSpares('inventory_Table')"/></td>
                                 <td width="10%" align="right"><input type="submit" class="headingSpas1" value="Submit" name="submit" id="Submit" onclick="validateInventory();"/></td>
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