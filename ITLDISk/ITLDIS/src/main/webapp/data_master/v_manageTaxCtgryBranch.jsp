<%-- 
    Document   : v_manageTaxCtgryBranch
    Created on : 15 March, 2016, 2:31:51 PM
    Author     : avinash.pandey
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
<%@ page import="org.apache.struts.Globals"%>
<%@ page import="org.apache.struts.taglib.html.Constants"%> 
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="<%=cntxpath%>/js/Master_290414.js"></script>
        <script src="<%=cntxpath%>/js/intermediate.js"></script>
        <link rel="stylesheet" href="css/Master_290414.css" type="text/css" >
        <title><bean:message key="label.master.addRegUpdateTaxCategories" /></title>

        <script type="text/javascript" language="javascript">
            function isProperSerialCategory(string)
            {
                if (!string) return false;
                var iChars = "=|!^,\\:<>[]{}`';&$#~?><"+'"';

                for (var i = 0; i < string.length; i++) {
                    if (iChars.indexOf(string.charAt(i)) != -1)
                        return false;
                }
                return true;
            }
            
            function validateForm(){

                var regExpCode = /^[A-Za-z0-9-_]*$/;
                var elementArr = new Array('Category Code','Category Description');
                var strObject = null;
                for (var i = 0; i < elementArr.length; i++)
                {
                    strObject = document.getElementById(elementArr[i]);
                    if (strObject){
                        if(elementArr[i] == 'Category Code'){//|| elementArr[i] == 'Dealer Code'
                            if (!isNotEmpty(strObject, elementArr[i])){
                                strObject.focus();
                                return false;
                            }
                        }
                        if(elementArr[i] == 'Category Code'){
                           <%-- if (strObject.value !='' && !isProperSpaceNotA(strObject.value)){--%>
                            if (strObject.value !='' && !regExpCode.test(strObject.value)){                                
                                document.getElementById("msg_saveFAILED").style.display = "";
                                document.getElementById("msg_saveFAILED").innerHTML="Only Special characters 'Space' and (-, _) are allowed in "+elementArr[i]+" field";
                                strObject.focus();
                                return false;
                            }
                        }                 
                        if(elementArr[i] == 'Category Description'){//|| elementArr[i] == 'Dealer Code'
                            if (!isNotEmpty(strObject, elementArr[i])){
                                strObject.focus();
                                return false;
                            }
                        }if(elementArr[i] == 'Category Description'){
                            if(!isProperSerialCategory(strObject.value)){
                                document.getElementById("msg_saveFAILED").style.display = "";
                                document.getElementById("msg_saveFAILED").innerHTML="Only Special characters (), / (.),%,@_ and (-) are allowed in "+elementArr[i]+" field";
                                strObject.focus();
                                return false;
                            }
                        }
                    }
                }
                var taxType=new Array();
                var taxCharge=new Array();
                var isPrimaryTax=new Array();
                taxType=document.getElementsByName("tax_Type");
                taxCharge=document.getElementsByName("charge_branch_id");
                isPrimaryTax=document.getElementsByName("isPrimaryTax");
                for(var i=0;i<taxType.length;i++ ){
                    if(taxType[i].value== "")
                    {
                        document.getElementById("msg_saveFAILED").style.display = "";
                        document.getElementById("msg_saveFAILED").innerHTML="Please Select Tax Type"
                        taxType[i].focus();
                        return false;
                    }
                    if(taxCharge[i].value== "")
                    {
                        document.getElementById("msg_saveFAILED").style.display = "";
                        document.getElementById("msg_saveFAILED").innerHTML="Please Select Tax Charge"
                        taxCharge[i].focus();
                        return false;
                    }
                }
                var count=0;
                for(var i=0;i<isPrimaryTax.length;i++ ){
                    if(isPrimaryTax[i].value=="Y"){
                        count++;
                    }
                }
                if(count==0){
                    document.getElementById("msg_saveFAILED").style.display = "";
                    document.getElementById("msg_saveFAILED").innerHTML="Please Select atleast one primary tax 'Yes'"
                    return false;
                }
            }
            function addRow()
            {
                var oRows = document.getElementById('sdTable').getElementsByTagName('tr');
                var  rowLength=oRows.length
                var x=document.getElementById('sdTable').insertRow(rowLength);
                x.bgColor="white";
                x.className = 'tableactive';
                x.id=rowLength;
                if(rowLength<10){
                    var ch=x.insertCell(0);

                    x.className = 'tableactive';
                    var ch=x.insertCell(0);
                    ch.width="25%";
                    ch.align="left";
                    // width=20%" align="center"
                    ch.className="headingSpas";
                    ch.innerHTML="<input type=\"hidden\" name=\"display_Order\" id=\"display_Order"+rowLength+"\" value="+rowLength+"><select name=\"tax_Type\" id=\"Tax Type"+rowLength+"\" class=\"headingSpas\" style=\"width:150px!important\" onchange=\"getTaxChargeByTaxTypeId(this.value,"+rowLength+");\"> <option value=\"\">--Select Here--</option><c:forEach items="${taxList}" var="taxList"><option value='${taxList.value}' title='${taxList.label}'>${taxList.label}</option></c:forEach></select>";

                    var b=x.insertCell(1);
                    b.className="headingSpas";
                    b.width="25%";
                    b.align="left";
                    b.innerHTML="<select name=\"charge_branch_id\" id=\"Tax Charge"+rowLength+"\" class=\"headingSpas\" style=\"width:150px!important\" onchange=\"checkDuplicateTaxEntry(this.value,"+rowLength+");\"><option value=\"\">--Select Here--</option></select>";

                    var ch=x.insertCell(2);
                    ch.width="25%";
                    ch.align="left";
                    ch.className="headingSpas";
                    ch.innerHTML="<select name=\"isPrimaryTax\" id=\"isPrimaryTax"+rowLength+"\" class=\"headingSpas\" style=\"width:80px!important\"><option value=\"\">--Select Here--</option><option value=\"Y\">Yes</option><option value=\"N\" selected>No</option></select>";

                }else{
                    alert("Only 9 row. can be added at a time");
                }

            }
            function deleterow()
            {
                var oRows = document.getElementById('sdTable').getElementsByTagName('tr') ;
                var rowLength=oRows.length;
                if(rowLength>2){
                    document.getElementById('sdTable').deleteRow(rowLength-1);
                }
            }
            function getTaxChargeByTaxTypeId(val,row)
            {
                
                    var todate=new Date().getTime();
                    if(val!=undefined)
                        var taxType=val;
                    var strURL = '<%=cntxpath%>/masterAction.do?option=getTaxChargeByTaxTypeId&taxchargeOemId=' + taxType + '&todate=' + todate;
                    xmlHttp = GetXmlHttpObject();
                    ajaxindicatorstart('loading data.. please wait..');
                    xmlHttp.onreadystatechange = function()
                    {
                        stateChanged12(row);
                    }
                    xmlHttp.open("POST", strURL, true);
                    xmlHttp.send(null);
                    ajaxindicatorstop();
                

            }
            function stateChanged12(row)
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")

                {
                    res = trim(xmlHttp.responseText);
                    var select_ops=res.split("||");
                    var selectObj=document.getElementById("Tax Charge" + row);
                    selectObj.options.length=1;
                    for (var i=0; i<select_ops.length-1; i++){
                        var warshow=select_ops[i].substring(0, select_ops[i].indexOf("$$"));
                        var warval=select_ops[i].substring(select_ops[i].indexOf("$$")+2);
                        var objopt=new Option(warval,warshow);
                        objopt.id=warval;
                        objopt.title=warval;
                        selectObj.options[selectObj.options.length]=objopt;
                    }
                    document.getElementById("Tax Charge" + row).value="";
                }
            }

            function checkDuplicateTaxEntry(value,index)
            {
                var oRows = document.getElementById('sdTable').getElementsByTagName('tr');
                var  rowLength=oRows.length;
                for(var i=1;i<rowLength;i++){
                    if(index != i){
                        if(document.getElementById("Tax Charge"+i).value==value){
                            document.getElementById("msg_saveFAILED").style.display = "";
                            document.getElementById("msg_saveFAILED").innerHTML="Please select different tax charge";
                            document.getElementById("Tax Charge"+index).value="";
                            document.getElementById("isPrimaryTax"+index).value="N";
                            document.getElementById("Tax Charge"+index).focus();
                            return false;
                        }
                    }
                }
            }

        </script>
    </head>
    <body>
        <div class="contentmain1">
            <div class="breadcrumb_container">
                <ul>
                    <li class="breadcrumb_link"><a href = '<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
                    <li class="breadcrumb_link"><a href = '<%=cntxpath%>/masterAction.do?option=initManageTaxCtgryBranch'><font class="hText"><bean:message key="label.common.managetaxCategories" /></font></a></li>
                    <li class="breadcrumb_link"><bean:message key="label.master.addRegUpdateTaxCategories" /></li>

                </ul>
            </div>
            <br/>

            <div class="message" id="msg_saveFAILED">${message}</div>
            <div class="content_right1">
                <div class="con_slidediv1" style="position: relative;width: 100%">
                    <h1 class="hText"><bean:message key="label.master.addRegUpdateTaxCategories" /></h1>
                    <table align="center" cellspacing=1 cellpadding=5 border=0 width=100% >
                        <tr bgcolor="#FFFFFF">
                            <td align="center" class="headingSpas">
                                <html:form action="masterAction.do?option=saveTaxCategories" styleId="custForm">
                                    <html:hidden  property="taxctgry_branch_id" styleId="taxctgry_branch_id"/>
                                    <input type="hidden" name="<%= Constants.TOKEN_KEY%>" value="<%= session.getAttribute(Globals.TRANSACTION_TOKEN_KEY)%>" />
                                    <table width=100%  border=0 cellspacing=1 cellpadding=1 >
                                        <tr bgcolor="#FFFFFF"  >
                                            <c:if test="${not empty cmTaxDetails}">
                                                <td align="right" class="headingSpas" height="30px"><bean:message key="label.master.categoryCode" /></td> <%--<span class="mandatory">*</span>--%>
                                                <td align="left" class="headingSpas" height="30px" style="padding-left: 5px">
                                                    <html:text property="taxCtgryCode" styleId="Category Code" maxlength="49"   style="width:150px" onblur="this.value=TrimAll(this.value)" readonly="true"/>
                                                </td>
                                            </c:if>
                                            <c:if test="${empty cmTaxDetails}">
                                                <td align="right" class="headingSpas" height="30px"><bean:message key="label.master.categoryCode" /></td> <%--<span class="mandatory">*</span>--%>
                                                <td align="left" class="headingSpas" height="30px" style="padding-left: 5px">
                                                    <html:text property="taxCtgryCode" styleId="Category Code" maxlength="49"   style="width:150px" onblur="this.value=TrimAll(this.value)" />
                                                </td>

                                            </c:if>
                                            <td align="right" class="headingSpas" height="30px" ><bean:message key="label.master.categoryDesc" /></td>
                                            <td align="left" class="headingSpas" height="30px" style="padding-left: 5px">
                                                <html:text property="taxCtgryDesc" styleId="Category Description" maxlength="99"  style="width:150px" onblur="this.value=TrimAll(this.value)"/>
                                            </td>
                                        </tr>
                                    </table>


                                    <table id="data" width=100%  border=0 cellspacing=1 cellpadding=1 >
                                        <tr><td height="5px"><td></tr>
                                        <tr bgcolor="#CCCCCC"><td align="left" class="headingSpas" colspan="2" height="25"><b>Select Taxes</b></td></tr>

                                    </table>

                                    <table  id="sdTable" width=100%  border=0 cellspacing=2 cellpadding=5  >

                                        <tr bgcolor="#eeeeee">
                                            <td width="25%" align="left" class="headingSpas" style="padding-left: 10px"><b><bean:message key="label.master.taxType" /></b></td>
                                            <td width="25%" align="left" class="headingSpas" style="padding-left: 10px"><b><bean:message key="label.master.taxcharge" /></b></td>
                                            <td width="25%" align="left" class="headingSpas" style="padding-left: 10px"><b><bean:message key="label.master.isPrimaryTax" /></b></td>
                                            <td width="10%" align="left" class="headingSpas" style="padding-left: 2px"><img src='<%=cntxpath%>/layout/images/plus.gif' onclick="addRow()" class='statusicon'/>&nbsp;<img src='<%=cntxpath%>/layout/images/minus.gif' onclick="deleterow()" class='statusicon' /></td>
                                        </tr>
                                        <c:if test="${not empty cmTaxDetails}">
                                            <c:set var="index" value="1"/>
                                            <c:forEach items="${cmTaxDetails}" var="data">
                                                <tr bgcolor="#FFFFFF">
                                                    <td width="25%" align="left" class="headingSpas"><input type="hidden" name="display_Order" id="display_Order1" value="1">
                                                        <select  name="tax_Type" id="Tax Type${index}" class="headingSpas" style="width:150px!important" onchange="getTaxChargeByTaxTypeId(this.value,'${index}');">
                                                            <option value="">--Select Here--</option>
                                                            <c:forEach items="${taxList}" var="taxList" >
                                                                <c:if test="${data.taxCode eq taxList.value}">
                                                                    <option value='${taxList.value}' title="${taxList.label}" selected>${taxList.label}</option>
                                                                </c:if>
                                                                <c:if test="${data.taxCode ne taxList.value}">
                                                                    <option value='${taxList.value}' title="${taxList.label}">${taxList.label}</option>
                                                                </c:if>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td width="25%" align="left" class="headingSpas">
                                                        <select name="charge_branch_id" id="Tax Charge${index}" class="headingSpas" style="width:150px!important" onchange="checkDuplicateTaxEntry(this.value,'${index}');">
                                                            <option value="">--Select Here--</option>
                                                            <c:if test="${not empty data.chargeBranchId}">
                                                                <option value='${data.chargeBranchId}' title="${data.chargeDesc}" selected>${data.chargeDesc}</option>
                                                            </c:if>
                                                        </select>
                                                    </td>
                                                    <td  width="25%" align="left" class="headingSpas" colspan="2" >
                                                        <select name="isPrimaryTax" id="isPrimaryTax${index}" class="headingSpas" style="width:80px!important">
                                                            <c:if test="${data.isPrimary_Tax eq 'Y'}">
                                                                <option value="Y" selected>Yes</option>
                                                                <option value="N" >No</option>
                                                            </c:if>
                                                            <c:if test="${data.isPrimary_Tax eq 'N'}">
                                                                <option value="Y" >Yes</option>
                                                                <option value="N" selected>No</option>
                                                            </c:if>
                                                        </select>
                                                    </td>
                                                </tr>
                                                <c:set var="index" value="${index +1}"/>
                                            </c:forEach>
                                        </c:if >
                                        <c:if test="${empty cmTaxDetails}">
                                            <tr bgcolor="#FFFFFF">
                                                <td width="25%" align="left" class="headingSpas"><input type="hidden" name="display_Order" id="display_Order1" value="1">
                                                    <select  name="tax_Type" id="Tax Type1" class="headingSpas" style="width:150px!important" onchange="getTaxChargeByTaxTypeId(this.value,1);">
                                                        <option value="">--Select Here--</option>
                                                        <c:forEach items="${taxList}" var="taxList" >
                                                            <option value='${taxList.value}' title="${taxList.label}">${taxList.label}</option>

                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td width="25%" align="left" class="headingSpas">
                                                    <select name="charge_branch_id" id="Tax Charge1" class="headingSpas" style="width:150px!important" onchange="checkDuplicateTaxEntry(this.value,1);">
                                                        <option value="">--Select Here--</option>
                                                    </select>
                                                </td>
                                                <td  width="25%" align="left" class="headingSpas" colspan="2" >
                                                    <select name="isPrimaryTax" id="isPrimaryTax1" class="headingSpas" style="width:80px!important">
                                                        <option value="Y">Yes</option>
                                                        <option value="N" selected>No</option>
                                                    </select>
                                                </td>
                                            </tr>
                                        </c:if>
                                    </table>
                                    <table   width=100%  border=0 cellspacing=1 cellpadding=1 >
                                        <tr bgcolor="#FFFFFF">
                                            <td align="center" class="headingSpas" colspan="4">
                                                <html:submit property="submitBtn" styleId="submitBtn" styleClass="headingSpas" value="Submit" onclick=" return validateForm();"/>
                                            </td>
                                        </tr>
                                    </table>
                                </html:form>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
