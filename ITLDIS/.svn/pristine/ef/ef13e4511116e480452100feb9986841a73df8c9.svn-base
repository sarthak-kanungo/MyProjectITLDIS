<%-- 
    Document   : v_manageChargeBranch
    Created on : Mar 9, 2016, 2:24:48 PM
    Author     : prashant.kumar
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ page import="java.util.*" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>
<script type="" language="JavaScript" src='${pageContext.request.contextPath}/js/validation.js'></script>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>

<script language="JavaScript" type="text/javascript">

    function submitForm() {
        document.getElementById('searchBy').submit();
    }
    
    function checkValue(strObject, strValue)
    {
        var regExp = /^[ A-Za-z0-9_/-@.%]*$/;   // prashant
        var regExpCode = /^[A-Za-z0-9-]*$/;
        var objRegExp = /^(\s*)$/g;    
        var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
        var objSpecExpDes = /['#\*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
        // var ck_code = /^[a-zA-Z0-9]+$/;

        strValue = strValue.replace(objRegExp, '');
        // var temp = objSpecExp.exec(strValue);
        // var tempD= objSpecExpDes.exec(strValue);
        var regExpTemp = regExp.exec(strValue);
        var text = strObject.id;
        if (strObject.id == 'chargeCode') {
            text = 'Charge Code';
        }
        if (strObject.id == 'chargeDesc') {
            text = 'Charge Description';
        }
        if (strObject.id == 'chargeRate') {
            text = 'Charge Rate';
        }

        if (document.getElementById("taxType").value==""){
            document.getElementById("taxType").focus();
            document.getElementById("msg_saveFAILED").innerHTML = "Please Select 'Tax Type' value";
            return false;
        }
        
        if (strValue.length == 0){
            strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Value in " + text + " Field";
            window.scrollTo(0, 0);//go to top of page
            return false;

        }else if (strObject.id == 'chargeCode') {
            text = 'Charge Code';
            if (!regExpCode.test(strValue)) {
                strObject.focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Only characters, numbers and spacial character (-) without 'Space' are allowed in " + text + " field";
                window.scrollTo(0, 0);//go to top of page
                return false;
            }       
        }else if (strObject.id == 'chargeDesc') {
            text = 'Charge Description';
            if (!regExp.test(strValue)) {
                strObject.focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Only special characters 'Space' and (-, ., /, _, %, @) are allowed in " + text + " field";
                window.scrollTo(0, 0);//go to top of page
                return false;
            }
        }else if (strObject.id == 'chargeRate') {
            text = 'Charge Rate';
            if (objSpecExpDes.test(strValue)) {
                strObject.focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "No special characters or symbol are allowed in " + text + " field";
                window.scrollTo(0, 0);//go to top of page
                return false;
            }
        }

        var chRate = document.getElementById("chargeRate");
        if(parseFloat(chRate.value)<= 0.0) {
            chRate.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter the value greater than Zero.";
            window.scrollTo(0, 0);//go to top of page
            return false;
        }
    }
    
    function cUDAction(str, row, strVal ,charge_id){

        var url = null;
        if (str == 'add') {
            var elementArr = new Array('chargeCode', 'chargeDesc', 'chargeRate');
            var strValue = null;
            var strObject = null;

            for (var i = 0; i < elementArr.length; i++){
                strObject = document.getElementById(elementArr[i]);
                strValue = document.getElementById(elementArr[i]).value;
                if (checkValue(strObject, strValue) == false)
                    return false;
            }
            var chargeCode = TrimAll(document.getElementById("chargeCode").value);
            var chargeDesc = TrimAll(document.getElementById("chargeDesc").value);
            var taxTypeSelect = TrimAll(document.getElementById("taxType").value);
            var chargeRate = TrimAll(document.getElementById("chargeRate").value);
            document.getElementById("chargeRateHi").value=chargeRate;
            document.getElementById("chargeDescHi").value=chargeDesc;
            
            var tempDes=replaceall(chargeDesc,'&','`');
            url = "<%=cntxpath%>/masterAction.do?option=manageChargeBranchMaster&type=" + str + "&taxType=" + taxTypeSelect +"&chargeCode=" + chargeCode + "&chargeDesc=" + tempDes + "&chargeRate=" + chargeRate + "&primary_id="+charge_id+"&t=" + new Date().getTime();

        }else {
            url = "<%=cntxpath%>/masterAction.do?option=manageChargeBranchMaster&status=" + str + "&type=" + str + "&chargeCode=" + strVal + "&primary_id="+charge_id+"&t=" + new Date().getTime();
        }

        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
            {
                if (xmlHttp.responseText.indexOf("Session has been expired") != -1)
                {
                    document.location.href = contextPath + "/login/SessionExpired.jsp";
                }
                else{
                    res = trim(xmlHttp.responseText);
                    document.getElementById("msg_saveFAILED").innerHTML = res.split("@@")[1];
                    if (str != 'add' && res.split("@@")[0] == 'Success') {
                        if (str == 'Y') {
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('N','" + row + "','" + strVal + "','"+charge_id+"')\"  class=\"headingSpas\" style=\"color: blue;\" >N</a>";
                        } else {
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','" + row + "','" + strVal + "','"+charge_id+"')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                        }
                    }
                    if (str == 'add' && res.split("@@")[0] == 'Success') {

                        document.getElementById("msg_saveFAILED").innerHTML = res.split("@@")[1];
                        document.getElementById("chargeCode").value = '';
                        document.getElementById("chargeDesc").value = '';
                        document.getElementById("chargeRate").value = '';                        
                        document.getElementById("taxType").value = '';
                        // setTimeout(function() { submitForm(); }, 3000);
                    }
                }
            }
        };
        xmlHttp.open("GET", encodeURI(url), true);
        xmlHttp.send(null);
        return false;
    }

    function editRow(chargeCode, row,chargeDesc,charge_id, chargeRate){
        var url = "<%=cntxpath%>/masterAction.do?option=manageChargeBranchMaster&type=check&chargeCode="+chargeCode+"&primary_id="+charge_id+"&t="+new Date().getTime();
        var flag = false;
        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
            {
                if (xmlHttp.responseText.indexOf("Session has been expired") != -1)
                {
                    document.location.href = "${contextPath}/login/SessionExpired.jsp";

                }else{
                    res = trim(xmlHttp.responseText);
                    if(res == 'notexist'){
                        //document.getElementById('taxCode'+row).innerHTML="<input type=\"hidden\" name=\"taxCode\"   value=\""+taxCode+"\"/>";
                        document.getElementById('chargeDesc'+row).innerHTML="<input type=\"text\" name=\"chargeDesc\" size='150' maxlength='90' class=\"headingSpas\" id=\"newChargeDesc"+row+"\" style=\"width:450px\" value=\""+chargeDesc+"\"/>";
                        document.getElementById('chargeRate'+row).innerHTML="<input type=\"text\" name=\"chargeRate\" size='150' maxlength='10' class=\"headingSpas\" id=\"newChargeRate"+row+"\" style=\"width:100px\" value=\""+chargeRate+"\"/>";
                        flag = true;
                    }else{
                        document.getElementById('chargeDesc'+row).innerHTML="<input type=\"text\" name=\"chargeDesc\" size='150' maxlength='90' class=\"headingSpas\" id=\"newChargeDesc"+row+"\" style=\"width:450px\" value=\""+chargeDesc+"\"/>";
                        document.getElementById('chargeRate'+row).innerHTML="<input type=\"text\" name=\"chargeRate\" size='150' maxlength='10' class=\"headingSpas\" id=\"newChargeRate"+row+"\" style=\"width:100px\" value=\""+chargeRate+"\"/>";
                    }
                }
            }
        };
        xmlHttp.open("GET", encodeURI(url), false);
        xmlHttp.send(null);
        document.getElementById('imageButton'+row).innerHTML="<input type=button class=\"headingSpas\" name=\"apply\" value=\"Apply\" onclick=\"updateAction('edit', "+row+",'"+chargeCode+"','"+flag+"','"+charge_id+"')\"/>";
    }

    function updateAction(str, row, oldChargeCode,flag,charge_id){
        
        var objRegExp = /^(\s*)$/g;
        var ck_code = /^[a-zA-Z0-9]+$/;
        var regExp = /^[ A-Za-z0-9_/-@.%]*$/;
        var objSpecExpDes = /['#\*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
        
        if (document.getElementById("newChargeDesc" + row).value == "") {
            document.getElementById("newChargeDesc" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Charge Description";
            return false;
        }
        if (document.getElementById("newChargeRate" + row).value == "") {
            document.getElementById("newChargeRate" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter the value in Charge Rate";
            return false;
        }

        var chckValue = document.getElementById("newChargeDesc" + row).value;
        chckValue = chckValue.replace(objRegExp, '');
        var temp = regExp.exec(chckValue);
        if (!temp)
        {
            document.getElementById("newChargeDesc" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Only special characters 'Space' and (-, ., /, _, %, @) are allowed in 'Charge Description' field";
            window.scrollTo(0, 0);//go to top of page
            return false;
        }

        var checkValueRate = document.getElementById("newChargeRate" + row).value;
        checkValueRate = checkValueRate.replace(objRegExp, '');        

        if(objSpecExpDes.test(checkValueRate)) {
            document.getElementById("newChargeRate" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "No special characters or symbol are allowed in 'Charge Rate' field";
            window.scrollTo(0, 0);//go to top of page
            return false;
        }
        
        if(parseFloat(checkValueRate)<= 0.0){

            document.getElementById("newChargeRate" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter the value greater than Zero in 'Charge Rate' field";
            window.scrollTo(0, 0);//go to top of page
            return false;
        }

        var chargeDesc = TrimAll(document.getElementById("newChargeDesc" + row).value);
        var chargeRate = TrimAll(document.getElementById("newChargeRate" + row).value);
        var tempDes=replaceall(chargeDesc,'&','`');
        var tempRate=replaceall(chargeRate,'&','`');

        var url = "<%=cntxpath%>/masterAction.do?option=manageChargeBranchMaster&id=''&chargeCode=" + oldChargeCode + "&chargeDesc=" + tempDes +"&chargeRate=" + tempRate + "&type=" + str + "&primary_id="+charge_id+"&t=" + new Date().getTime();
        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function(){
            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
            {
                if (xmlHttp.responseText.indexOf("Session has been expired") != -1)
                {
                    document.location.href = contextPath + "/login/SessionExpired.jsp";

                }else{
                    res = trim(xmlHttp.responseText);
                    document.getElementById("msg_saveFAILED").innerHTML = res.split("@@")[1];
                    if (str != 'add' && res.split("@@")[0] == 'Success') {
                        if(flag == 'true'){
                            document.getElementById('chargeCode'+row).innerHTML=""+oldChargeCode+"";
                        }else{
                            document.getElementById('chargeCode'+row).innerHTML=""+oldChargeCode+"";
                        }
                        document.getElementById('chargeDesc' + row).innerHTML = "" + chargeDesc + "";
                        document.getElementById('chargeRate' + row).innerHTML = "" + chargeRate + "";
                        document.getElementById('imageButton' + row).innerHTML = "<img src=\"images/edit.gif\" onclick=\"editRow('" + oldChargeCode + "'," + row + ",'" + chargeDesc + "','"+charge_id+"','"+chargeRate+"')\" title=\"Edit\"/>";
                    }
                }
            }
        };
        xmlHttp.open("GET", encodeURI(url), true);
        xmlHttp.send(null);
        return false;
    }

    function donotsubmit(){
        return false;
    }

</script>

<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href ='<%=cntxpath%>/masterAction.do?option=initOptions'><bean:message key="label.common.Masters" /></a></li>
            <li class="breadcrumb_link"><bean:message key="label.common.manageChargeBranch" /></li>
        </ul>
    </div>       
    <br/>
    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.common.manageChargeBranch" /></h1>
                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=initManageChargeBranch" method="post" styleId="searchBy" onsubmit="donotsubmit();">      <%--donotsubmit()--%>
                                <html:hidden property="chargeRate" styleId="chargeRateHi"/>
                                <html:hidden property="chargeDesc" styleId="chargeDescHi"/>
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">
                                        <td  style="padding-left:10px" width="100px" class="headingSpas" nowrap align="left"><bean:message key="label.common.chargeCode" /></td>
                                        <td style="padding-left:10px" width="120px">
                                            <html:text property="codeSrch" styleClass="headingSpas"  style="width:170px" onblur="this.value=TrimAll(this.value)" maxlength="15"/>
                                        </td>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <td  style="padding-left:10px" width="140px" class="headingSpas" nowrap align="left"><bean:message key="label.common.chargeDesc" /></td>
                                        <td style="padding-left:10px" width="140px">
                                            <html:text property="nameSrch" styleClass="headingSpas"  style="width:170px" onblur="this.value=TrimAll(this.value)"/>
                                        </td>
                                        <td style="padding-left:10px" align="left" >
                                            <input type=button name="Go" class="headingSpas" value="Search" onClick = "submitForm()"/>
                                        </td>
                                    </tr>
                                </table>
                            </html:form>
                        </td></tr>
                        <c:if test="${empty chargeBranchList}">
                        <tr bgcolor="#FFFFFF">
                            <td valign="top" align="center" class="headingSpas" style="padding-top:10px;color: red">
                                <bean:message key="label.common.chargeDescNotFound" />
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!empty chargeBranchList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="10">
                                    <pg:param name="option" value="initManageChargeBranch"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td  nowrap align="center" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >S.No</td>
                                            <td   align="left" class="headingSpas" width="30" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap><bean:message key="label.common.chargeCode" /></td>
                                            <td   align="left" class="headingSpas" width="150" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap><bean:message key="label.common.chargeDesc" /></td>
                                            <td   align="left" class="headingSpas" width="50" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap><bean:message key="label.common.chargeRate" /></td>
                                            <td   align="center" class="headingSpas" width="50" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap><bean:message key="label.common.edit" /></td>
                                            <%--<td   align="center" class="headingSpas" width="75" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap><bean:message key="label.common.isActive" /></td>--%>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="chargeBranchList" name="chargeBranchList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20">
                                                <input type="hidden" id="taxTypeHid" value="${chargeBranchList.taxType}" >
                                                <input type="hidden" id="chargeRateHid" value="${chargeBranchList.chargeRate}" >
                                                <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                <td align="left" bgcolor="#FFFFFF" title=" ${chargeBranchList.chargeCode}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span id ="chargeCode${sno}" >
                                                        ${chargeBranchList.chargeCode}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" title="${chargeBranchList.chargeDesc}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span id ="chargeDesc${sno}" >
                                                        ${chargeBranchList.chargeDesc}
                                                    </span>
                                                </td>
                                                <td align="left" bgcolor="#FFFFFF" title="${chargeBranchList.chargeRate}"  class="headingSpas" style="padding-left:10px; padding-right: 5px">
                                                    <span id ="chargeRate${sno}" >
                                                        ${chargeBranchList.chargeRate}
                                                    </span>
                                                </td>
                                                <td align="center" bgcolor="#FFFFFF" width="20" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                    <span id ="imageButton${sno}" >
                                                        <img alt="" src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${chargeBranchList.chargeCode}', ${sno}, '${chargeBranchList.chargeDesc}','${chargeBranchList.chargeBranchId}','${chargeBranchList.chargeRate}')" title="Edit"/>
                                                    </span>
                                                </td>                                            
                                        </tr>
                                </pg:item>
                                <c:set var="sno" value="${sno + 1 }" />
                            </logic:iterate>
                            <tr >
                                <td colspan="5" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                    <pg:index>
                                        <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle"/></a></pg:first>&nbsp;
                                        <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle"/></a></pg:prev>&nbsp;
                                        <pg:pages>&nbsp;<a style="color: blue;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                        <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle"/></a></pg:next>&nbsp;
                                        <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle"/></a></pg:last>
                                        </pg:index>
                                </td>
                            </tr>
                        </table>
                    </pg:pager>
                    </td>
                    </tr>
                </c:if>

                <tr bgcolor="#FFFFFF">
                    <td width=100% valign="top" >
                        <form action="#" >
                            <table width=100% border="0" cellpadding="0" cellspacing="0" >
                                <tr bgcolor="#eeeeee" height="20">
                                    <td  style="padding-left: 5px" align= left class="headingSpas">
                                        <b class="hText"><bean:message key="label.common.addNewChargeBranch" /></b>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="100%" valign="top">
                                        <table width="100%" border="0" cellpadding="0" cellspacing="0" >
                                            <tr height="30">
                                                <td  style="padding-left:10px;padding-top: 5px;padding-bottom: 5px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left"><bean:message key="label.common.selectTaxType" /></td>
                                                <td align="left" width="10%">
                                                    <select id="taxType" name="taxType" class="headingSpas" style="width:120px !important;">
                                                        <option value="" >-Select-</option>
                                                        <c:forEach items="${taxList}" var="taxList">
                                                            <option value='${taxList.value}' title='${taxList.label}'>${taxList.label}</option>
                                                        </c:forEach>
                                                    </select>
                                                </td>
                                                <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="right"><bean:message key="label.common.chargeCode" /></td>
                                                <td style="padding-left:10px;padding-top: 5px;padding-bottom: 5px" bgcolor="#FFFFFF" align="left">
                                                    <input type="text" name="chargeCode"  id="chargeCode" class="headingSpas" onkeypress="return CommentsMaxLength(this, 11);" maxlength="11" style="width:120px" onblur="this.value=TrimAll(this.value)"/>
                                                </td>

                                                <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="right"><bean:message key="label.common.chargeDesc"/></td>
                                                <td style="padding-left:10px;padding-top: 5px;padding-bottom: 5px" bgcolor="#FFFFFF" align="left">
                                                    <input type="text" name="chargeDesc1"  id="chargeDesc" class="headingSpas" size="90"  maxlength="90" style="width:180px" onkeypress="return CommentsMaxLength(this, 90);" onblur="this.value=TrimAll(this.value)"/>
                                                </td>
                                                <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="right"><bean:message key="label.common.chargeRate"/></td>
                                                <td style="padding-left:10px;padding-top: 5px;padding-bottom: 5px" bgcolor="#FFFFFF" align="left">
                                                    <input type="text" name="chargeRate1" id="chargeRate" class="headingSpas" size="10"  maxlength="10" style="width:80px" onblur="this.value=TrimAll(this.value)"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="8" style="padding-left:10px;padding-bottom: 5px;padding-bottom: 5px" align="center" bgcolor="#FFFFFF">
                                                    <input type=button name="add" value="Submit" class="headingSpas" onclick="cUDAction('add','','','')"/>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </td>
                </tr>
                </table>
            </div>
        </div>
    </center>
</div>
