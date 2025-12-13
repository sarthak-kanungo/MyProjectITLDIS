<%-- 
    Document   : v_partmaster
    Created on : Jun 4, 2014, 4:49:51 PM
    Author     : megha.pandya
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_1.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_2.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_3.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_7.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_8.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggesion_master.js"></script>


<script  type="text/javascript" language="javascript">
    var totalPartsValueS = 0.0;
    var totalLabourChargesS = 0.0;
    var totalOtherChargesS = 0.0;
    var totalLubesValueS = 0.0;
     window.onload=function(){
        var data = '${masterForm.name}';
        if(data!=null && data!=""){
        var showdata = data.split('@@');
        document.getElementById("msg_formchecklist").innerHTML="Please add spars for Job Type \""+showdata[0]+"\" and Model Code \""+showdata[1]+"\"";
        }
    }
    function GetXmlHttpObject()
    {
        var objXmlHttp = null;
        if (navigator.userAgent.indexOf('Opera') >= 0)
        {
            var xmlHttp = new XMLHttpRequest();
            return xmlHttp;
        }
        if (navigator.userAgent.indexOf('MSIE') >= 0)
        {
            var strName = 'Msxml2.XMLHTTP';
            if (navigator.appVersion.indexOf('MSIE 5.5') >= 0)
            {
                strName = 'Microsoft.XMLHTTP';
            }
            try
            {
                objXmlHttp = new ActiveXObject(strName);
                // objXmlHttp.onreadystatechange = handler;
                return objXmlHttp;
            }
            catch (e)
            {
                alert('Your Browser Does Not Support Ajax');
                return;
            }
        }
        if (navigator.userAgent.indexOf('Mozilla') >= 0)
        {
            objXmlHttp = new XMLHttpRequest();
            return objXmlHttp;
        }
    }

    var count = "3";
    function addRowSpares(in_tbl_name)
    {
        count = (document.getElementById(in_tbl_name).rows.length);//alert(count)
        var tbody = document.getElementById(in_tbl_name).getElementsByTagName("TBODY")[0];
        // create row
        var row = document.createElement("TR");
        row.height="20px";
        row.bgColor="#FFFFFF";
        var td0 = document.createElement("TD");
        td0.style.paddingLeft="10px";
        td0.align ="center";
        var strHtml0 =(count-1);
        td0.innerHTML = strHtml0;

        var td1 = document.createElement("TD")
        td1.style.paddingTop="5px";
        td1.style.paddingBottom="5px";
        td1.style.paddingLeft="10px";
        var strHtml1 = "<input name=\"part_No\" type=\"text\" id=\"part_No" + count + "\" onblur=\"this.value=TrimAll(this.value)\"/>&nbsp;<a href='#'><img src='" + contextPath + "/image/arrdown.gif' alt='Get Suggestions' border='0' onclick=\"getSuggestionsPart4master('part_No" + count + "',document.getElementById('part_No" + count + "'),document.getElementById('comp_type'),document.getElementById('img" + count + "')," + count + ");\"/><img  id='img" + count + "' align=\"top\" style='visibility:hidden;' border='0' src='" + contextPath + "/image/load.gif'/></a><input type=\"hidden\" name=\"partNumber\" id=\"partNumber" + count + "\" value=\"\">";
        td1.innerHTML = strHtml1.replace(/!count!/g, count);

        var td2 = document.createElement("TD")
        td2.style.paddingLeft="10px";
        var strHtml2 = "<input name=\"partDesc\" type=\"text\" readonly id=\"partDesc" + count + "\" style=\"width:400px\" onblur=\"this.value=TrimAll(this.value)\"/>";
        td2.innerHTML = strHtml2.replace(/!count!/g, count);

        <%--var td3 = document.createElement("TD")
        var strHtml3 = "<input name=\"unitPrice\" type=\"text\" id=\"unitPrice" + count + "\" value='' class=\"AmountTxtBox\" readonly id=\"text8\"/>";
        td3.innerHTML = strHtml3.replace(/!count!/g, count);--%>

        var td4 = document.createElement("TD")
        td4.style.paddingLeft="10px";
        var strHtml4 = "<input name=\"quantityS\" type=\"text\" id=\"quantityS" + count + "\" maxlength=\"10\"  onkeypress=\"return isNumberKey(event);\"  style=\"width:50px !important\"/>";<%--onblur=\"partAmountTotal('quantityS" + count + "','unitPrice" + count + "','partPriceAmount" + count + "'," + count + ");\"--%>
        td4.innerHTML = strHtml4.replace(/!count!/g, count);

        var td5 = document.createElement("TD")
        td5.style.paddingLeft="10px";
        var strHtml5 = "<input type=\"text\" name=\"comptype\" readonly id=\"comptype" + count + "\" value=\"\">";<%--<input name=\"partPriceAmount\" type=\"hidden\" class=\"AmountTxtBox\" value='' readonly id=\"partPriceAmount" + count + "\"/>--%>
        td5.innerHTML = strHtml5.replace(/!count!/g, count);

//        var td6 = document.createElement("TD")
//        var strHtml6 = "<select name=\"billCode\" id=\"billCode" + count + "\" onchange=\"onChangeBillToFunc(this," + count + ");\"><c:forEach items='${billList}' var='dataList'><option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option></c:forEach></select>";
//        td6.innerHTML = strHtml6.replace(/!count!/g, count);
//
//        var td7 = document.createElement("TD")
//        var strHtml7 = "<input name=\"warranty\" type=\"text\" id=\"warranty" + count + "\" readonly style=\"width:50px !important\" maxlength=\"3\" !important\" onfocus=\"checkBlank(this," + count + ")\" onkeypress=\"return numeralsOnly(event,getElementById('warranty" + count + "'))\"  onblur=\"billToTotal('partPriceAmount" + count + "'," + count + ");\"/>";
//        td7.innerHTML = strHtml7.replace(/!count!/g, count);
//
//        var td8 = document.createElement("TD")
//        var strHtml8 = "<input name=\"finalAmount\" type=\"text\" class=\"AmountTxtBox\" id=\"finalAmount" + count + "\" readonly/>";
//        td8.innerHTML = strHtml8.replace(/!count!/g, count);
        row.appendChild(td0);
        row.appendChild(td1);
        row.appendChild(td2);
        <%--row.appendChild(td3);--%>
        row.appendChild(td5);
        row.appendChild(td4);
//        row.appendChild(td6);
//        row.appendChild(td7);
//        row.appendChild(td8);

        count = parseInt(count) + 1;
        tbody.appendChild(row);

    }
    function deleteSparesRow(in_tbl_name)
    {
        var oRows = document.getElementById(in_tbl_name).getElementsByTagName('tr');
        var rowLength = oRows.length - 1;
        if (rowLength > 2) {
            document.getElementById(in_tbl_name).deleteRow(rowLength - 1);
        }
    }

    
        function notblank(obj)
        {
            var objSpecExp = /\S+/g;
            if (objSpecExp.test(obj))
            {
                return true;
            }
            return false
        }

        function getServiceHrsAjax(obj, nameObj, row)
        {
          

            var val= document.getElementById("actionCode"+row).value;

            if(val.indexOf("@@") > -1){
                val= val.split('@@');
                document.getElementById("complainhrs").value= val[1];
            }

            else{
                document.getElementById("complainhrs").value= val;
            }
            //complainhrs

            //actionCode1

            var labourChargesAmount = 0;
            var partPriceAmoun = 0;
            var totalPrice = 0.0;
            var objjCode = obj.value;
            //alert(objjCode);
            var strURL = "<%=cntxpath%>/serviceProcess.do?option=getServiceHrsAjax&objjCode=" + objjCode + "&nameObj=" + nameObj + "&rowCount=" + row + "&tm=" + new Date().getTime();

            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")

                {
                    res = trim(xmlHttp.responseText);
                    // alert(res);
                    document.getElementById(nameObj + row).innerHTML = '';
                    document.getElementById(nameObj + row).innerHTML = "<input type=\"text\" name=\"labourChargesAmount\" id=\"labourChargesAmount" + row + "\" class=\"AmountTxtBox\" value=" + res + "   readonly/>";

                    document.getElementById("labourChargesAmount"+row).value=eval(val[1])*eval(res);


                    var tableName = document.getElementsByName('labourChargesAmount');
                    for (var i = 0; i < tableName.length; i++) {

                        labourChargesAmount = tableName[i].value == '' ? 0 : tableName[i].value;
                        totalPrice += parseFloat(labourChargesAmount);
                    }
                    document.getElementById("totalLabourCharges").value = totalPrice.toFixed(2);
                    //labourChargesAmount
                    totalPartsValueS = document.getElementById("totalPartsValue").value == '' ? 0 : document.getElementById("totalPartsValue").value;
                    totalLabourChargesS = document.getElementById("totalLabourCharges").value == '' ? 0 : document.getElementById("totalLabourCharges").value;
                    totalOtherChargesS = document.getElementById("totalOtherCharges").value == '' ? 0 : document.getElementById("totalOtherCharges").value;
                    ;
                    //document.forms[0].totalEstimate.value=((parseFloat(totalPartsValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);
                    totalLubesValueS = document.getElementById("totalLubesValue").value == '' ? 0 : document.getElementById("totalLubesValue").value;
                    document.forms[0].totalEstimate.value = ((parseFloat(totalPartsValueS)) + (parseFloat(totalLubesValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);

                }
            }
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);


        }
        var sparex = new Array();
        var part_No = new Array();
        var partDesc = new Array();
        var unitPrice = new Array();

        function getPartPrice(part_No, row) {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var part_No = part_No.value;
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
                        part_No = document.getElementsByName("part_No")
                        partDesc = document.getElementsByName("partDesc")
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
                                    document.getElementById("msg_saveFAILED").innerHTML = "Same part number can not be enter twice";
                                    document.getElementById("part_No" + row).value = '';
                                    document.getElementById("partDesc" + row).value = '';
                                    <%--document.getElementById("unitPrice" + row).value = '';--%>
                                    document.getElementById("comptype"+row).value=''
                                    document.getElementById("part_No" + row).focus();
                                    window.scrollTo(0, 0);
                                    img_obj.style.visibility = "hidden";
                                    return false;
                                }

                                sparex.push(part_No[k].value);
                            }
                        }
                        document.getElementById("partDesc" + row).value = valArr[0];
                        document.getElementById("partDesc" + row).title = valArr[0];
                        <%--document.getElementById("unitPrice" + row).value = valArr[0];--%>
                        if(valArr[1]=='PRT')
                        {document.getElementById("comptype"+row).value='SPARES'}
                        else if(valArr[1]=='LUBE')
                        { document.getElementById("comptype"+row).value='LUBES'}
//                        alert(row+'--'+valArr[2]+'--'+document.getElementById("comptype"+row).value);
                        document.getElementById("img" + row).style.dispaly = "none";
//                        onChangeBillToFunc(document.getElementById("billCode" + row), row);
                    } else
                    {
                        document.getElementById("partDesc" + row).value = "";
                        <%--document.getElementById("unitPrice" + row).value = "";--%>
                        document.getElementById("part_No" + row).value = '';
                        document.getElementById("part_No" + row).title="";
                        document.getElementById("partDesc" + row).title="";
                        document.getElementById("comptype"+row).value=''
                        document.getElementById("img" + row).style.dispaly = "none";
                    }
                }
            }
        }

        function getPartPriceByPart(partDesc, row) {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var partDesc = partDesc.value;
            var todate = new Date().getTime();
            var strURL = '<%=cntxpath%>/masterAction.do?option=getPartPriceBypartDesc&partDesc=' + partDesc + '&tm=' + todate;
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                stateChangedP(xmlHttp, row, partDesc);
            };
            xmlHttp.open("GET", strURL, true);
            xmlHttp.send(null);
        }

        function stateChangedP(xmlHttp, row, partDescS)
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
                        part_No = document.getElementsByName("part_No")
                        partDesc = document.getElementsByName("partDesc")
                        unitPrice = document.getElementsByName("unitPrice")
                        for (var k = 0; k < partDesc.length; k++) {
                            if (partDesc[k].value != '')
                            {
                                var res1 = true;
                                for (var m = 0; m < sparex.length; m++)
                                {
                                    if (sparex[m] == trimS(partDesc[k].value))
                                    {
                                        res1 = false;
                                    }
                                }
                                if (res1 == false)
                                {
                                    document.getElementById("msg_saveFAILED").innerHTML = "Same part description can not be enter twice";
                                    document.getElementById("part_No"+row).value = '';
                                    document.getElementById("partDesc"+row).value = '';
                                    document.getElementById("unitPrice"+row).value = '';
                                    document.getElementById("comptype"+row).value=''
                                    document.getElementById("partDesc"+row).focus();
                                    window.scrollTo(0, 0);
                                    img_obj.style.visibility = "hidden";
                                    return false;
                                }

                                sparex.push(partDesc[k].value);
                            }
                        }
                        document.getElementById("part_No"+row).value = valArr[1];
                        document.getElementById("part_No"+row).title = valArr[1];
                        document.getElementById("unitPrice"+row).value = valArr[0];
                        if(valArr[2]=='PRT')
                        {document.getElementById("comptype"+row).value='SPARES'}
                        else if(valArr[2]=='LUBE')
                        {document.getElementById("comptype"+row).value='LUBES'}
//                        alert(valArr[2]+'--'+document.getElementById("comptype"+row).value);
                        document.getElementById("image"+row).style.dispaly = "none";
//                        onChangeBillToFunc(document.getElementById("billCode" + row), row);
                    } else
                    {
                        document.getElementById("part_No" + row).value = "";
                        document.getElementById("unitPrice" + row).value = "";
                        document.getElementById("part_No" + row).title="";
                        document.getElementById("partDesc" + row).title="";
                        document.getElementById("comptype"+row).value=''
                        document.getElementById("image" + row).style.dispaly = "none";
                    }
                }
            }
        }

        var sparex = new Array();
        var actionCode = new Array();
        var labourChargesAmount = new Array();
        var complaintCode = new Array();

        function partAmountTotal(obj1, obj2, obj3, rowId) {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var totalPrice = 0.0;
            var unitPrice = 0.0;
            var subTotal = 0.0;
            //var excise=0;
            //var finalAmount=0.0;
            //var warranty=0;
            var partPriceAmount = 0;
            //var partPriceAmoun=0;
            //var finalAmountT=0;

            var quantity = document.getElementById(obj1).value;
            if (quantity == null || quantity == "") {
                document.getElementById(obj1).value = '0';
                quantity = 0;
            }
            unitPrice = document.getElementById(obj2).value;
            if (unitPrice == null || unitPrice == "") {
                document.getElementById(obj2).value = '0';
                unitPrice = 0;
            }
            unitPrice = parseFloat(unitPrice);
            unitPrice = unitPrice.toFixed(2);
            var partPriceAmount = quantity * unitPrice;
            document.getElementById(obj3).value = partPriceAmount.toFixed(2);
            subTotal = subTotal + parseFloat(partPriceAmount);
        }

        function totalCharges(obj, rowId) {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var chargesAmount = 0;
            var workAmount = 0;
            var subTotal = 0.0;
            var workAmountTotal = 0.0;
            var workAmountArr = document.getElementsByName('workAmount');
            for (var i = 0; i < workAmountArr.length; i++) {

                workAmountTotal = workAmountArr[i].value == '' ? 0 : workAmountArr[i].value;
                subTotal += parseFloat(workAmountTotal);
            }
            document.getElementById("totalOtherCharges").value = subTotal.toFixed(2);
            totalPartsValueS = document.getElementById("totalPartsValue").value == '' ? 0 : document.getElementById("totalPartsValue").value;
            totalLabourChargesS = document.getElementById("totalLabourCharges").value == '' ? 0 : document.getElementById("totalLabourCharges").value;
            totalOtherChargesS = document.getElementById("totalOtherCharges").value == '' ? 0 : document.getElementById("totalOtherCharges").value;
            totalLubesValueS = document.getElementById("totalLubesValue").value == '' ? 0 : document.getElementById("totalLubesValue").value;
            document.forms[0].totalEstimate.value = ((parseFloat(totalPartsValueS)) + (parseFloat(totalLubesValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);

        }
        var sparex = new Array();
        var workCode = new Array();
        var workDescription = new Array();
        var workAmount = new Array();

        function checkQuantity(qty)
        {
            if (isNaN(qty.replace('-', '')))
                return false;
            else if (qty == 0)
                return false;
            else if (qty.indexOf(".") != -1)
                return false;
            else
                return true;
        }
        /// LUBES///
        function lubesAmountTotal(obj1, obj2, obj3, rowId) {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var totalPrice = 0.0;
            var unitPrice = 0.0;
            var subTotal = 0.0;
            var partPriceAmount = 0;
            var quantity = document.getElementById(obj1).value;
            if (quantity == null || quantity == "") {
                document.getElementById(obj1).value = '0';
                quantity = 0;
            }
            unitPrice = document.getElementById(obj2).value;
            if (unitPrice == null || unitPrice == "") {
                document.getElementById(obj2).value = '0';
                unitPrice = 0;
            }
            unitPrice = parseFloat(unitPrice);
            unitPrice = unitPrice.toFixed(2);
            var partPriceAmount = quantity * unitPrice;
            document.getElementById(obj3).value = partPriceAmount.toFixed(2);
            subTotal = subTotal + parseFloat(partPriceAmount);
            document.getElementById("lubesFinalAmount" + rowId).value = document.getElementById('lubesPriceAmount' + rowId).value;
            var tableName = document.getElementsByName('lubesFinalAmount');
            for (var i = 0; i < tableName.length; i++) {

                partPriceAmount = tableName[i].value == '' ? 0 : tableName[i].value;
                totalPrice += parseFloat(partPriceAmount);
            }
            document.getElementById("totalLubesValue").value = totalPrice.toFixed(2);

            if (document.getElementById("lubesBillCode" + rowId).value != '')
            {

                lubesBillToTotal('lubesPriceAmount' + rowId, rowId);

            }
            var valArr = document.getElementById("lubesBillCode" + rowId).value.split('@@');
            //            if(valArr[1]=='Yes' && document.getElementById("lubesWarranty"+rowId).value==valArr[2]){
            //                document.getElementById("msg_saveFAILED").innerHTML="Discount on goodwill should be numeric and greater than zero.";
            //                document.getElementById("lubesWarranty"+rowId).focus();
            //            }
            totalPartsValueS = document.getElementById("totalPartsValue").value == '' ? 0 : document.getElementById("totalPartsValue").value;
            totalLabourChargesS = document.getElementById("totalLabourCharges").value == '' ? 0 : document.getElementById("totalLabourCharges").value;
            totalOtherChargesS = document.getElementById("totalOtherCharges").value == '' ? 0 : document.getElementById("totalOtherCharges").value;
            ;
            totalLubesValueS = document.getElementById("totalLubesValue").value == '' ? 0 : document.getElementById("totalLubesValue").value;
            document.forms[0].totalEstimate.value = ((parseFloat(totalPartsValueS)) + (parseFloat(totalLubesValueS)) + (parseFloat(totalLabourChargesS)) + (parseFloat(totalOtherChargesS))).toFixed(2);
        }
        var sparex = new Array();
        var lubesNo = new Array();
        var lubesDesc = new Array();
        var lubesUnitPrice = new Array();

        function getLubesPartPrice(lubesNo, row) {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var lubesNo = lubesNo.value;
            var todate = new Date().getTime();
            var strURL = '<%=cntxpath%>/serviceProcess.do?option=getPartPriceBypartNo&partno=' + lubesNo + '&tm=' + todate;
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                stateChangedLubesPrice(xmlHttp, row, lubesNo);
            };
            xmlHttp.open("GET", strURL, true);
            xmlHttp.send(null);
        }

        function stateChangedLubesPrice(xmlHttp, row, lubesNoS)
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
                        lubesNo = document.getElementsByName("lubesNo")
                        lubesDesc = document.getElementsByName("lubesDesc")
                        lubesUnitPrice = document.getElementsByName("lubesUnitPrice")
                        for (var k = 0; k < lubesNo.length; k++) {
                            if (lubesNo[k].value != '')
                            {
                                var res1 = true;
                                for (var m = 0; m < sparex.length; m++)
                                {
                                    if (sparex[m] == trimS(lubesNo[k].value))
                                    {
                                        res1 = false;
                                    }
                                }
                                if (res1 == false)
                                {
                                    document.getElementById("msg_saveFAILED").innerHTML = "Same part number can not be enter twice in lubes";
                                    document.getElementById("lubesNo" + row).value = '';
                                    document.getElementById("lubesUnitPrice" + row).value = '';
                                    document.getElementById("lubesDesc" + row).value = '';
                                    document.getElementById("lubesNo" + row).focus();
                                    window.scrollTo(0, 0);
                                    img_obj.style.visibility = "hidden";
                                    return false;
                                }

                                sparex.push(lubesNo[k].value);
                            }
                        }
                        document.getElementById("lubesDesc" + row).value = valArr[1];
                        document.getElementById("lubesDesc" + row).title = valArr[1];
                        document.getElementById("lubesUnitPrice" + row).value = valArr[0];
                        document.getElementById("imgLubes" + row).style.dispaly = "none";
                        onChangeLubesBillToFunc(document.getElementById("lubesBillCode" + row), row);
                    } else
                    {
                        document.getElementById("lubesNo" + row).value = "";
                        document.getElementById("lubesDesc" + row).value = "";
                        document.getElementById("lubesUnitPrice" + row).value = "";
                        document.getElementById("lubesNo" + row).title="";
                        document.getElementById("lubesDesc" + row).title="";
                        document.getElementById("imgLubes" + row).style.dispaly = "none";
                    }
                }
            }
        }

        function getLubesPartPriceByPart(lubesDesc, row) {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var lubesDesc = lubesDesc.value;
            var todate = new Date().getTime();
            var strURL = '<%=cntxpath%>/serviceProcess.do?option=getPartPriceBypartDesc&partDesc=' + lubesDesc + '&tm=' + todate;
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                stateChangedLubesP(xmlHttp, row, lubesDesc);
            };
            xmlHttp.open("GET", strURL, true);
            xmlHttp.send(null);
        }

        function stateChangedLubesP(xmlHttp, row, lubesDescS)
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
                        lubesNo = document.getElementsByName("lubesNo")
                        lubesDesc = document.getElementsByName("lubesDesc")
                        lubesUnitPrice = document.getElementsByName("lubesUnitPrice")
                        for (var k = 0; k < lubesDesc.length; k++) {
                            if (lubesDesc[k].value != '')
                            {
                                var res1 = true;
                                for (var m = 0; m < sparex.length; m++)
                                {
                                    if (sparex[m] == trimS(lubesDesc[k].value))
                                    {
                                        res1 = false;
                                    }
                                }
                                if (res1 == false)
                                {
                                    document.getElementById("msg_saveFAILED").innerHTML = "Same part description can not be enter twice in lubes";
                                    document.getElementById("lubesNo" + row).value = '';
                                    document.getElementById("lubesUnitPrice" + row).value = '';
                                    document.getElementById("lubesDesc" + row).value = '';
                                    document.getElementById("lubesDesc" + row).focus();
                                    window.scrollTo(0, 0);
                                    img_obj.style.visibility = "hidden";
                                    return false;
                                }

                                sparex.push(lubesDesc[k].value);
                            }
                        }
                        document.getElementById("lubesNo" + row).value = valArr[1];
                        document.getElementById("lubesNo" + row).title = valArr[1];
                        document.getElementById("lubesUnitPrice" + row).value = valArr[0];
                        document.getElementById("imageLubes" + row).style.dispaly = "none";
                        onChangeLubesBillToFunc(document.getElementById("lubesBillCode" + row), row);
                    } else
                    {
                        document.getElementById("lubesNo" + row).value = "";
                        document.getElementById("lubesUnitPrice" + row).value = "";
                        document.getElementById("lubesDesc" + row).value = "";
                        document.getElementById("lubesNo" + row).title="";
                        document.getElementById("lubesDesc" + row).title="";
                        document.getElementById("imageLubes" + row).style.dispaly = "none";
                    }
                }
            }
        }
        function getLubesPartCheck(lubesNo, row) {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var part_No = lubesNo.value;
            var todate = new Date().getTime();
            var strURL = '<%=cntxpath%>/serviceProcess.do?option=getPartCheck&partno=' + part_No + '&tm=' + todate;
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                stateLubesPartCheck(xmlHttp, row);
            };
            xmlHttp.open("GET", strURL, true);
            xmlHttp.send(null);
        }

        function stateLubesPartCheck(xmlHttp, row)
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
                    //var valArr = res.split('@@');
                    if (res == 'notexists')
                    {
                        document.getElementById("lubesPartNumber" + row).value = res;
                        document.getElementById("msg_saveFAILED").innerHTML = "Part Number '" + res + "' does not exist in database.";
                        document.getElementById("lubesNo" + row).focus();
                        document.getElementById("lubesNo" + row).value = '';
                        return false;
                    }


                }
            }
        }
        //END///
        function validate(obj)
        {
            document.getElementById("msg_saveFAILED").innerHTML ="";
            var quantityS = new Array();
            var sparex = new Array();
            var sparexComp = new Array();
            var sparexAct = new Array();
            var warranty = new Array();
            var chCount = 0;
            var part_No = new Array();

            part_No = document.getElementsByName("part_No");
            quantityS = document.getElementsByName("quantityS");
            
            if(document.getElementById("Job Type").value== '')
            {
                alert('Please select Job Type');
                return false;
            }
            if(document.getElementById("Model No").value== '')
            {
                alert('Please Enter Moddel Code');
                return false;
            }
             var el = document.getElementById('Job Type');
            var text = el.options[el.selectedIndex].innerHTML;
            document.getElementById("jobtype").value=  document.getElementById("Job Type").value+"@@"+text;
            document.getElementById("modelNo").value=  document.getElementById("Model No").value;
            for (var k = 0; k < part_No.length; k++) {
                if (part_No[0].value == '')
                {
                    document.getElementById("msg_saveFAILED").innerHTML = "Please enter atleast one character of part number to get suggestions."
                    part_No[0].focus();
                    window.scrollTo(0, 0);//go to top of page
                    return false;
                }

                if (part_No[k].value != '')
                {
//                    alert((k + 1)+'---type:'+document.getElementById("comptype"+(k + 1)).value);
                    var res1 = true;
                    for (var m = 0; m < sparex.length; m++)
                    {
                        if (sparex[m] == (part_No[k].value))
                        {
                            res1 = false;
                        }
                    }
                    if (res1 == false)
                    {
                        document.getElementById("msg_saveFAILED").innerHTML = "Same part number can not be enter twice";
//                        estimateTotalValue();
                        part_No[k].value = '';
                        part_No[k].focus();
                        window.scrollTo(0, 0);
                        return false;
                    }
                    if (!(checkWhitStringCommon(part_No[k])) && part_No[k].value != "")
                    {
                        document.getElementById("msg_saveFAILED").innerHTML = "Spacial characters semicolon and AND(') are not permitted part number";
                        part_No[k].value = "";
                        part_No[k].focus();
                        window.scrollTo(0, 0);
                        return false;
                    }
                    getPartCheck(part_No[k], k + 1)
                    sparex.push(part_No[k].value);

                    if (quantityS[k].value.trim() == "")
                    {
                        document.getElementById("msg_saveFAILED").innerHTML = "Quantity can not be blank";
                        quantityS[k].focus();
                        window.scrollTo(0, 0);
                        return false;
                    }
                    if (!checkQuantity(quantityS[k].value))
                    {
                        document.getElementById("msg_saveFAILED").innerHTML = "Quantity should be numeric and greater than zero.";
                        quantityS[k].value = "";
                        quantityS[k].focus();
                        window.scrollTo(0, 0);
                        return false;

                    }

                }
            }
            document.getElementById("form1").submit();

        }
        function getPartCheck(partNo, row) {
            var part_No = partNo.value;
            var todate = new Date().getTime();
            var strURL = '<%=cntxpath%>/masterAction.do?option=getPartCheck&partno=' + part_No + '&tm=' + todate;
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                statePartCheck(xmlHttp, row);
            };
            xmlHttp.open("GET", strURL, true);
            xmlHttp.send(null);
        }

        function statePartCheck(xmlHttp, row)
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
                    //var valArr = res.split('@@');
                    if (res == 'notexists')
                    {
                        document.getElementById("partNumber" + row).value = res;
                        document.getElementById("msg_saveFAILED").innerHTML = "Part Number '" + res + "' does not exist in database.";
                        document.getElementById("part_No" + row).focus();
                        document.getElementById("part_No" + row).value = '';
                        return false;
                    }


                }
            }
        }
        
   function gofordata()
   {
       if(document.getElementById("Job Type").value== '')
        {
           alert('Please select Job Type');
           return false;
        }
        if(document.getElementById("Model No").value== '')
        {
           alert('Please Enter Moddel Code');
           return false;
        }
         var el = document.getElementById('Job Type');
         var text = el.options[el.selectedIndex].innerHTML;
        document.getElementById("name").value=text;
       document.getElementById("formpart").submit();
   }
        
</script>
<div class="breadcrumb_container">
    <ul>
       
                <li class="breadcrumb_link"><a href ='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTER</a></li>
                <li class="breadcrumb_link">MANAGE DEFAULT SPARES /LUBES MASTER</li>
    </ul>
</div>

 <center>
            <div class="content_right1">


                <div class="con_slidediv1" style="position: relative;width: 100%">
                    <h1>MANAGE DEFAULT SPARES /LUBES MASTER</h1>

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="2" class="LiteBorder" >
    <form name="formpart" id="formpart" method="post" action="<%=cntxpath%>/masterAction.do?option=initPartMaster" onsubmit="return false;">
        <tr>
            <td><input type="hidden" id="name" name="name" value=""/>
                <table width="100%"  border="0">
                    <tr >
                        <td align="right" width="10%">Job Type</td>
                        <td align="left" width="15%">
                            <select name="jobtype"   id="Job Type" style="width:150px !important">
                                <option value="">--Select--</option>
                                <c:forEach items="${jobTypeList}" var="dataList">
                                    <c:if test="${masterform.jobtype eq dataList.value}">
                                        <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>
                                    </c:if>
                                    <c:if test="${masterform.jobtype ne dataList.value}">
                                        <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
                                    </c:if>
                                </c:forEach>
                            </select>
                        </td>
                        <td align="right" width="10%">Model No.</td>
                        <td  align="left" width="15%">
                            <input name="modelNo" id="Model No" value="${masterform.modelNo}"/>
                        </td>
                        <td  align="left" width="50%">
                            <input type="button" name="go" id="go" value="Go" onclick="gofordata()"/>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
        <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/masterAction.do?option=initPartMaster'>MASTERS</a>@@MANAGE DEFAULT SPARES /LUBES MASTER"/>
        </form>
    <div class="message1" id="msg_saveFAILED">${message}</div>
    <tr>
        <td>  <div class="message" id="msg_formchecklist"></div>
                <table width="100%"  border="0" cellspacing="1">
                    <tr>
                        <td colspan="2" valign="top">
                        <form name="form1" id="form1" method="post" action="<%=cntxpath%>/masterAction.do?option=managePartLubeMaster" onsubmit="return false;">
                            <input type="hidden" name="jobtype" id="jobtype" value="${masterform.jobtype}"/>
                            <input type="hidden" name="modelNo" id="modelNo" value="${masterform.modelNo}"/>
                            <table  id="myTable" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                            <tbody>
                            <tr gcolor="#eeeeee" height="20">
                                <td width="5%" style="padding-left: 10px;" align="center">Sr No.</td>
                                <td width="15%" style="padding-left: 10px;">Part No</td>
                                <td width="30%" style="padding-left: 10px;">Part Desc</td>
                                <%--<td> <label>Unit Price</label></td>--%>
                                <td width="15%" style="padding-left: 10px;">Category</td>
                                <td width="15%" style="padding-left: 10px;">QTY<input type="hidden" name="comp_type" id="comp_type" value=""></td>
                            </tr>
                                <c:choose>
                                        <c:when test="${fn:length(partList) gt 0}">
                                            <c:set var="i" value="0" scope="page" />
                                                <c:set var="sno" value="1"></c:set>
                                                <c:forEach items="${partList}" var="partList">
                                                    <tr id ="${sno}"  height="20" bgcolor="#FFFFFF">
                                                        <td style="padding-left: 10px;" align="center">${sno}</td>
                                                        <td style="padding-left: 10px;padding-bottom: 5px;"><input name="part_No" type="text" id="part_No${sno}" value="${partList.partNo}" onblur="this.value=TrimAll(this.value)"/>&nbsp;<a href='#'><img src='<%=cntxpath%>/image/arrdown.gif' alt='Get Suggestions' border='0'  onclick="javascript:getSuggestionsPart4master('part_No${sno}', document.getElementById('part_No${sno}'), document.getElementById('comp_type'), document.getElementById('img${sno}'), '${sno}');"/><img alt=""  id='img${sno}' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif' /></a><input type="hidden" name="partNumber" id="partNumber${sno}" value=""></td>
                                                        <td style="padding-left: 10px;"><input name="partDesc" type="text" id="partDesc${sno}" style="width:400px;" value="${partList.part_Desc}"maxlength="250"  onblur="this.value=TrimAll(this.value)" readonly/><%--<a href='#' ><img src='<%=cntxpath%>/image/arrdown.gif' alt='Get Suggestions'  border='0'  onclick="getSuggestionsPartDesc4master('partDesc${sno}', document.getElementById('partDesc${sno}'), document.getElementById('comp_type'), document.getElementById('image${sno}'), '${sno}');"/><img alt=""  id='image${sno}' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/></a>--%></td>
                                                        <%--<td><input name="unitPrice" type="text" class="AmountTxtBox" value="${partList.unit_price}" readonly id="unitPrice${sno}"/></td>--%>
                                                        <td style="padding-left: 10px;"><input name="comptype" id="comptype${sno}" type="text" value="${partList.partCategory}" readonly/>
                                                            <%--<input name="partPriceAmount" type="hidden" class="AmountTxtBox" id="partPriceAmount${sno}" value="${partList.part_Amount}"readonly />--%>
                                                        </td>
                                                        <td style="padding-left: 10px;"><input name="quantityS" type="text" id="quantityS${sno}" value="${partList.qty}"maxlength="10" onkeypress="return isNumberKey(event);"  maxlength="15"  style="width:50px !important "/></td><%--onblur="partAmountTotal('quantityS${sno}', 'unitPrice${sno}', 'partPriceAmount${sno}', ${sno});"--%>
                                                        </tr>
                                                 <c:set var="sno" value="${sno + 1 }" />
                                                </c:forEach>
                                            <%--</c:if>--%>  
                                        </c:when>
                                        <c:otherwise>
                                        <tr id ="1"  height="20" bgcolor="#FFFFFF">
                                        <td  style="padding-left: 10px;" align="center">1</td>
                                        <td style="padding-left: 10px;padding-bottom: 5px;"><input name="part_No" type="text" id="part_No1" onblur="this.value=TrimAll(this.value)"/><a href='#' ><img src='<%=cntxpath%>/image/arrdown.gif' alt='Get Suggestions'  border='0'  onclick="getSuggestionsPart4master('part_No1', document.getElementById('part_No1'), document.getElementById('comp_type'), document.getElementById('img1'), '1');"/><img alt=""  id='img1' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/></a><input type="hidden" name="partNumber" id="partNumber1" value=""></td>
                                        <td style="padding-left: 10px;"><input name="partDesc" type="text" id="partDesc1" style="width:400px;" readonly onblur="this.value=TrimAll(this.value)"/><%--<a href='#' ><img src='<%=cntxpath%>/image/arrdown.gif' alt='Get Suggestions'  border='0'  onclick="getSuggestionsPartDesc4master('partDesc1', document.getElementById('partDesc1'), document.getElementById('comp_type'), document.getElementById('image1'), '1');"/><img alt=""  id='image1' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/></a>--%></td>
                                        <%--<td><input name="unitPrice" type="text" class="AmountTxtBox" readonly id="unitPrice1"  /></td>--%>
                                        <td style="padding-left: 10px;"><input name="comptype" id="comptype1" type="text" value="" readonly/>
                                            <input name="partPriceAmount" type="hidden" class="AmountTxtBox" id="partPriceAmount1" value="" readonly/>
                                        </td>
                                        <td style="padding-left: 10px;"><input name="quantityS" type="text" id="quantityS1" maxlength="10" onkeypress="return isNumberKey(event);"  onblur="partAmountTotal('quantityS1', 'unitPrice1', 'partPriceAmount1', 1);" style="width:50px !important "/></td>
                                        </tr>
                                        <tr id ="2"  height="20" bgcolor="#FFFFFF">
                                            <td style="padding-left: 10px;" align="center">2</td>
                                            <td style="padding-left: 10px;padding-bottom: 5px;"><input name="part_No" type="text" id="part_No2"  onblur="this.value=TrimAll(this.value)"/>&nbsp;<a href='#'><img src='<%=cntxpath%>/image/arrdown.gif' alt='Get Suggestions' border='0'  onclick="javascript:getSuggestionsPart4master('part_No2', document.getElementById('part_No2'), document.getElementById('comp_type'), document.getElementById('img2'), '2');"/><img alt=""  id='img2' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif' /></a><input type="hidden" name="partNumber" id="partNumber2" value=""></td>
                                            <td style="padding-left: 10px;"><input name="partDesc" type="text" id="partDesc2" style="width:400px;" maxlength="250" readonly onblur="this.value=TrimAll(this.value)"/><%--<a href='#' ><img src='<%=cntxpath%>/image/arrdown.gif' alt='Get Suggestions'  border='0'  onclick="getSuggestionsPartDesc4master('partDesc2', document.getElementById('partDesc2'), document.getElementById('comp_type'), document.getElementById('image2'), '2');"/><img alt=""  id='image2' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/></a><input type="hidden" name="comptype" id="comptype2" value="PRT">--%></td>
                                            <%--<td><input name="unitPrice" type="text" class="AmountTxtBox" readonly id="unitPrice2"/></td>--%>
                                            <td style="padding-left: 10px;"><input name="comptype" id="comptype2" type="text" value="" readonly/>
                                                <%--<input name="partPriceAmount" type="hidden" class="AmountTxtBox" id="partPriceAmount2" readonly />--%>
                                            </td>
                                            <td style="padding-left: 10px;"><input name="quantityS" type="text" id="quantityS2" maxlength="10" onkeypress="return isNumberKey(event);"  maxlength="15"  style="width:50px !important "/></td>
                                        </tr>  
                                        </c:otherwise>        
                                </c:choose>
                    </tbody>
                    <tr bgcolor="#FFFFFF">
                        <td style="padding-left: 25px;" align="left" colspan="5"><label><input name="Button" type="button" value="Add" onclick="addRowSpares('myTable')"/>
                                <input name="Button" type="button" value="Delete" onclick="deleteSparesRow('myTable')"/></label>
                        </td>
                    </tr>
                </table>
              </form>
        </td>

    </tr>

<tr align="center">
        <td colspan="2" style="padding-left: 25px;"><input name="input" type="button" value="Submit" style="float:none; " onclick="validate()"/></td>
</tr>
</table>

</td>
</tr>
</table>
</div>
</div>
 </center>
