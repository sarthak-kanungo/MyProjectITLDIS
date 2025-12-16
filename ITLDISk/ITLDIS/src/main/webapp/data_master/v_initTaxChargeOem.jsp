<%--
    Document   : v_aggregateMaster
    Created on : Apr 18, 2014, 10:25:09 AM
    Author     : Avinash.Pandey
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
        var regExp = /^[ A-Za-z0-9_/-@.%]*$/;
        var objRegExp = /^(\s*)$/g;
        var regExpCode = /^[A-Za-z0-9-]*$/;
       // var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
       // var objSpecExpDes = /['#\*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
       // var ck_code = /^[a-zA-Z0-9]+$/;

        strValue = strValue.replace(objRegExp, '');
       // var temp = objSpecExp.exec(strValue);
       // var tempD= objSpecExpDes.exec(strValue);
        var regExpTemp = regExp.exec(strValue);       
        var text = strObject.id;
        if (strObject.id == 'taxCode') {
            text = 'Tax Code';

        }
        if (strObject.id == 'taxDesc') {
            text = 'Tax Description';
        }
        if (strValue.length == 0){
            strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Value in " + text + " Field";
            window.scrollTo(0, 0);//go to top of page
            return false;
        
        }else if (strObject.id == 'taxCode') {
            text = 'Tax Code';
            if (!regExpCode.test(strValue)) {
                strObject.focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Only characters, numbers and spacial character (-) without 'Space' are allowed in " + text + " field";
                window.scrollTo(0, 0);//go to top of page
                return false;
            }
        }else if (strObject.id == 'taxDesc') {
            text = 'Tax Description';
            if (!regExp.test(strValue)) {
                strObject.focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Only special characters 'Space' and (-, ., /, _, %, @) are allowed in " + text + " field";
                window.scrollTo(0, 0);//go to top of page
                return false;
            }

        }else if (!regExpTemp){
            if (strObject.tagName == "INPUT"){
                strObject.focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Only special characters 'Space' and (-, /, _, %, @) are allowed in " + text + " field";
                window.scrollTo(0, 0);//go to top of page
                return false;
            }
            return false;
        }
    }
    
    function cUDAction(str, row, strVal ,tax_id){

        var url = null;
        if (str == 'add') {
            var elementArr = new Array('taxCode', 'taxDesc');
            var strValue = null;
            var strObject = null;

            for (var i = 0; i < elementArr.length; i++){
                strObject = document.getElementById(elementArr[i]);
                strValue = document.getElementById(elementArr[i]).value;
                if (checkValue(strObject, strValue) == false)
                    return false;
            }

            var taxCode = TrimAll(document.getElementById("taxCode").value);
            var taxDesc = TrimAll(document.getElementById("taxDesc").value);
            document.getElementById("taxDescHid").value=taxDesc;           
            var tempDes=replaceall(taxDesc,'&','`');
            url = "<%=cntxpath%>/masterAction.do?option=manageTaxChargeOEMMaster&type=" + str + "&taxCode=" + taxCode + "&taxDesc=" + tempDes + "&primary_id="+tax_id+"&t=" + new Date().getTime();

        }else {
            url = "<%=cntxpath%>/masterAction.do?option=manageTaxChargeOEMMaster&status=" + str + "&type=" + str + "&taxCode=" + strVal + "&primary_id="+tax_id+"&t=" + new Date().getTime();
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
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('N','" + row + "','" + strVal + "','"+tax_id+"')\"  class=\"headingSpas\" style=\"color: blue;\" >N</a>";
                        } else {
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','" + row + "','" + strVal + "','"+tax_id+"')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                        }
                    }
                    if (str == 'add' && res.split("@@")[0] == 'Success') {
                        document.getElementById("msg_saveFAILED").innerHTML = res.split("@@")[1];
                        document.getElementById("taxCode").value = '';
                        document.getElementById("taxDesc").value = '';                        
                        // setTimeout(function() { submitForm(); }, 3000);
                    }
                }
            }
        };
        xmlHttp.open("GET", encodeURI(url), true);
        xmlHttp.send(null);
        return false;
    }

    function editRow(taxCode, row,taxDesc,tax_id){        
        var url = "<%=cntxpath%>/masterAction.do?option=manageTaxChargeOEMMaster&type=check&taxCode="+taxCode+"&primary_id="+tax_id+"&t="+new Date().getTime();
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
                        document.getElementById('taxDesc'+row).innerHTML="<input type=\"text\" name=\"taxDesc\" size='150' maxlength='90' class=\"headingSpas\" id=\"newTaxDesc"+row+"\" style=\"width:450px\" value=\""+taxDesc+"\"/>";
                        flag = true;
                    }else{                                               
                        document.getElementById('taxDesc'+row).innerHTML="<input type=\"text\" name=\"taxDesc\" size='150' maxlength='90' class=\"headingSpas\" id=\"newTaxDesc"+row+"\" style=\"width:450px\" value=\""+taxDesc+"\"/>";
                    }
                }
            }
        };        
        xmlHttp.open("GET", encodeURI(url), false);
        xmlHttp.send(null);
        document.getElementById('imageButton'+row).innerHTML="<input type=button class=\"headingSpas\" name=\"apply\" value=\"Apply\" onclick=\"updateAction('edit', "+row+",'"+taxCode+"','"+flag+"','"+tax_id+"')\"/>";
    }

        function updateAction(str, row, oldTaxCode,flag,tax_id){
        
            var newTaxCode='';
            var objRegExp = /^(\s*)$/g;
            var ck_code = /^[a-zA-Z0-9]+$/;
            var regExp = /^[ A-Za-z0-9_/-@.%]*$/;

            if (document.getElementById("newTaxDesc" + row).value == "") {
                document.getElementById("newTaxDesc" + row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Tax Description";
                return false;
            }

            var chckValue = document.getElementById("newTaxDesc" + row).value;
            chckValue = chckValue.replace(objRegExp, '');
            var temp = regExp.exec(chckValue);
            if (!temp){
                document.getElementById("newTaxDesc" + row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Only special characters 'Space' and (-, ., /, _, %, @) are allowed in Tax Description field";
                window.scrollTo(0, 0);//go to top of page
                return false;
            }
            
            var taxDesc = TrimAll(document.getElementById("newTaxDesc" + row).value);
            var tempDes=replaceall(taxDesc,'&','`');

            var url = "<%=cntxpath%>/masterAction.do?option=manageTaxChargeOEMMaster&id=''&taxCode=" + oldTaxCode + "&taxDesc=" + tempDes + "&type=" + str + "&primary_id="+tax_id+"&t=" + new Date().getTime();            
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    if (xmlHttp.responseText.indexOf("Session has been expired") != -1)
                    {
                        document.location.href = contextPath + "/login/SessionExpired.jsp";
                    }
                    else
                    {
                        res = trim(xmlHttp.responseText);
                        document.getElementById("msg_saveFAILED").innerHTML = res.split("@@")[1];
                        if (str != 'add' && res.split("@@")[0] == 'Success') {
                            //var tableObject=document.getElementById('data');
                            if(flag == 'true'){
                               // document.getElementById('aggCode'+row).innerHTML=""+newAggCode+"";
                                document.getElementById('taxCode'+row).innerHTML=""+oldTaxCode+"";
                            }else{
                                document.getElementById('taxCode'+row).innerHTML=""+oldTaxCode+"";
                            }
                            document.getElementById('taxDesc' + row).innerHTML = "" + taxDesc + "";
                            document.getElementById('imageButton' + row).innerHTML = "<img src=\"images/edit.gif\" onclick=\"editRow('" + oldTaxCode + "'," + row + ",'" + taxDesc + "','"+tax_id+"')\" title=\"Edit\"/>";
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
            <li class="breadcrumb_link"><bean:message key="label.common.manageTaxType" /></li>
        </ul>
    </div>
    <br/>
    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><bean:message key="label.common.manageTaxType" /></h1>
                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=iniTaxChargeOEM" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <html:hidden property="taxDesc" styleId="taxDescHid"/>
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">
                                         <td  style="padding-left:10px" width="100px" class="headingSpas" nowrap align="left"><bean:message key="label.common.chargeCode" /></td>
                                        <td style="padding-left:10px" width="120px">
                                            <html:text property="codeSrch" styleClass="headingSpas"  style="width:170px" onblur="this.value=TrimAll(this.value)" maxlength="15"/>
                                        </td>&nbsp;&nbsp;&nbsp;&nbsp;
                                        <td  style="padding-left:10px" width="100px" class="headingSpas" nowrap align="left"><bean:message key="label.common.taxDesc" /></td>
                                        <td style="padding-left:10px" width="140px">
                                            <html:text property="nameSrch" styleClass="headingSpas"  style="width:170px" onblur="this.value=TrimAll(this.value)" />
                                        </td>
                                        <td style="padding-left:10px" align="left" >
                                            <input type=button name="Go" class="headingSpas" value="Search" onClick = "submitForm()"/>
                                        </td>
                                    </tr>
                                </table>
                            </html:form>
                        </td></tr>
                        <c:if test="${empty taxOemList}">
                        <tr bgcolor="#FFFFFF">
                            <td valign="top" align="center" class="headingSpas" style="padding-top:10px;color: red">                               
                                <bean:message key="label.common.taxDescNotFound" />
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!empty taxOemList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="10">
                                    <pg:param name="option" value="iniTaxChargeOEM"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td  nowrap align="center" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >S.No</td>
                                            <td   align="left" class="headingSpas" width="150"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap><bean:message key="label.common.taxTypeOem" /></td>
                                            <td   align="left" class="headingSpas"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap><bean:message key="label.common.taxDesc" /></td>
                                            <td   align="center" class="headingSpas" width="75" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap><bean:message key="label.common.edit" /></td>
                                            <td   align="center" class="headingSpas" width="75" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap><bean:message key="label.common.isActive" /></td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="taxOemList" name="taxOemList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20">
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                    <td align="left" bgcolor="#FFFFFF" title=" ${taxOemList.taxCode}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="taxCode${sno}" >
                                                            ${taxOemList.taxCode}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${taxOemList.taxDesc}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="taxDesc${sno}" >
                                                            ${taxOemList.taxDesc}
                                                        </span>
                                                    </td>

                                                    <td align="center" bgcolor="#FFFFFF" width="20" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="imageButton${sno}" >
                                                            <img alt="" src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${taxOemList.taxCode}', ${sno}, '${taxOemList.taxDesc}','${taxOemList.taxChgOemId}')" title="Edit"/>   <%-- ,'${taxOemList.id}@@${taxOemList.name}'--%>
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="50" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >                                                            
                                                            <a href="javascript:void(0)" onclick="cUDAction('${taxOemList.status}', ${sno}, '${taxOemList.taxCode}','${taxOemList.taxChgOemId}')"  class="headingSpas" style="color: blue;" >
                                                                ${taxOemList.status}&nbsp;
                                                            </a>
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
                                            <b class="hText"><bean:message key="label.common.addNewTaxChargeOem" /></b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0" >

                                                <tr height="30">
                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left"><bean:message key="label.common.taxTypeOem" /></td>
                                                    <td style="padding-left:10px;padding-top: 5px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="taxCode"  id="taxCode" class="headingSpas" onkeypress="return CommentsMaxLength(this, 11);" maxlength="11" style="width:120px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>

                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left"><bean:message key="label.common.taxDesc"/></td>
                                                    <td style="padding-left:10px;padding-top: 5px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="taxDesc1"  id="taxDesc" class="headingSpas" size="90"  maxlength="90" style="width:240px" onkeypress="return CommentsMaxLength(this, 90);" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                </tr>
                                                <tr height="20">
                                                    <td colspan="4" style="padding-left:10px;padding-bottom: 5px" align="center" bgcolor="#FFFFFF">
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
