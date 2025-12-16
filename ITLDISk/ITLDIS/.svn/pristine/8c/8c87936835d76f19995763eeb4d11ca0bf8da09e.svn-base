<%-- 
    Document   : v_wageMaster
    Created on : Apr 18, 2014, 10:24:43 AM
    Author     : Avinash.Pandey
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

<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>

<script language="JavaScript" type="text/javascript">
    function submitForm() {
        document.getElementById('searchBy').submit();
    }
    function checkValue(strObject, strValue)
    {
        var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['&\*\{\}\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
        var ck_code = /^[a-zA-Z0-9]+$/;

        strValue = strValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(strValue);
        var text = strObject.id;
        /* if (strObject.id == 'wageCode') {
            text = 'Wage Code';
        }
         */ if (strObject.id == 'wageName') {
            text = 'Wage Name';
        }
        if (strObject.id == 'wageValue') {
            text = 'Wage Value';
        }
        if (strValue.length == 0)
        {
            if (strObject.tagName == "SELECT")
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Please Select Value in " + strObject.id + " Field.";
                //alert("Please select value in " + objectId + " field.");
                return false;
            }
            else
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Please Enter Value in " + text + " Field.";
                //alert("Please enter value in " + objectId + " field.");
                return false;
            }
            window.scrollTo(0, 0);//go to top of page
            return false;

        }
        /*  else if (strObject.id == 'wageCode') {
            text = 'Wage Code';
            if (!ck_code.test(strValue)) {
                       strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
           document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in " + text + " Field";
            window.scrollTo(0, 0);//go to top of page
            return false;
                          }
        }*/
        else if (temp)
        {
            if (strObject.tagName == "INPUT")
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in " + text + " Field";
                window.scrollTo(0, 0);//go to top of page
                return false;
            }

            return false;

        }
        else if (text == "Wage Value")
        {
            if (validfloatnumber(strObject,text) == false)
            {
                strObject.focus();
                return false;
            }
        }
    }
    function cUDAction(str, row, strVal)
    {

        var url = null;

        if (str == 'add') {
            var elementArr = new Array('wageName','wageValue','Dealer Code');
            var strValue = null;
            var strObject = null;

            for (var i = 0; i < elementArr.length; i++)
            {
                strObject = document.getElementById(elementArr[i]);
                strValue = document.getElementById(elementArr[i]).value;
                if (checkValue(strObject, strValue) == false)
                    return false;
            }

            //   var wageCode = TrimAll(document.getElementById("wageCode").value);
            var wageName = TrimAll(document.getElementById("wageName").value);
            var wageValue = TrimAll(document.getElementById("wageValue").value);
            var dealerCode = TrimAll(document.getElementById("Dealer Code").value);
            url = "<%=cntxpath%>/masterAction.do?option=manageWage&type=" + str + "&wageName=" + wageName + "&wageValue=" + wageValue +"&t=" + new Date().getTime()+"&dealer_code="+dealerCode;
        }
        else {
          
            url = "<%=cntxpath%>/masterAction.do?option=manageWage&status=" + str + "&type=" + str + "&wageName=" + wageName + "&t=" + new Date().getTime()+"&dealer_code="+strVal;
        }
        //alert(url);
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
                        if (str == 'Y') {
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('N','" + row + "','" + strVal + "')\"  class=\"headingSpas\" style=\"color: blue;\" >N</a>";
                        } else {
                            document.getElementById('st' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','" + row + "','" + strVal + "')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                        }
                    }
                    if (str == 'add' && res.split("@@")[0] == 'Success') {
                        document.getElementById("msg_saveFAILED").innerHTML = res.split("@@")[1];
                        //   document.getElementById("wageCode").value = '';
                        document.getElementById("wageName").value = '';
                        document.getElementById("wageValue").value = '';
                        document.getElementById("Dealer Code").value='';
                        // setTimeout(function() { submitForm(); }, 3000);
                    }
                }
            }
        };
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        return false;
    }
    function editRow(row, wageName,wageValue,dealerCode) {
        //  document.getElementById('compType'+row).innerHTML="<input type=\"text\" name=\"item_no\" maxlength='15' class=\"headingSpas\" id=\"newItem_no"+row+"\" style=\"width:120px\" value=\""+itemNo+"\"/>";
        document.getElementById('wageName' + row).innerHTML = "<input type=\"text\" name=\"wageName\" size='200' maxlength='200' class=\"headingSpas\" id=\"newWageName" + row + "\" style=\"width:180px\" value=\"" +wageName + "\"/>";
        document.getElementById('wageValue' + row).innerHTML = "<input type=\"text\" name=\"wageValue\" maxlength='9' class=\"headingSpas\" id=\"newWageValue" + row + "\" style=\"width:180px\" value=\"" + wageValue + "\"/>";
        document.getElementById('imageButton' + row).innerHTML = "<input type=button name=\"apply\" class=\"headingSpas\" value=\"Apply\" onclick=\"updateAction('edit', " + row + ",'" + dealerCode +"')\"/>";
    }

    function updateAction(str, row, dealerCode)
    {
   
        if (document.getElementById("newWageName" + row).value == "") {
            document.getElementById("newWageName" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Wage Name";
            return false;
        }
        if (document.getElementById("newWageValue" + row).value == "") {
            document.getElementById("newWageValue" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Value in Wage Value Field";
            return false;
        }
        var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['&\*\{\}\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
    
        var chckValue = document.getElementById("newWageName" + row).value;
        chckValue = chckValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(chckValue);
        if (temp)
        {
            document.getElementById("newWageName" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in Wage Name Field";
            window.scrollTo(0, 0);//go to top of page
            return false;
        }
        var newWageValue = document.getElementById("newWageValue" + row);
        var wageValue = newWageValue.value;
        if (validfloat(newWageValue) == false)
        {
            alert("Please enter only decimal value in Wage Value field.");
            newPrice.value = "";
            newPrice.focus();
            return false;
        }

        //  var item_No = TrimAll(document.getElementById("newMechanin_Code"+row).value);
        var wageName = TrimAll(document.getElementById("newWageName" + row).value);
        var url = "<%=cntxpath%>/masterAction.do?option=manageWage&wageName=" + wageName + "&wageValue=" + wageValue + "&type=" + str + "&t=" + new Date().getTime()+"&dealer_code="+dealerCode;

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
                        //   document.getElementById('compType'+row).innerHTML=""+item_No+"";
                        document.getElementById('wageName' + row).innerHTML = "" + wageName + "";
                        document.getElementById('wageValue' + row).innerHTML = "" + parseFloat(wageValue) + "";
                        document.getElementById('imageButton' + row).innerHTML = "<img src=\"images/edit.gif\" onclick=\"editRow(" + row + ",'" + wageName + "',"+parseFloat(wageValue)+")\" title=\"Edit\"/>";
                    }
                }
            }
        };
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        return false;
    }
    function donotsubmit()
    {
        return false;
    }
    function validfloatnumber(obj,text) {
        var regExp = /^[0-9]*(\.[0-9]+)?$/g
        if (regExp.test(obj.value)) {
            return true;
        } else {
            document.getElementById("msg_saveFAILED").innerHTML ="Please Enter Only Numeric Value in " + text + " Field.";
            obj.value = "";
            obj.focus();
            return false;
        }
    }
    function validfloat(obj) {
        var regExp = /^[0-9]*(\.[0-9]+)?$/g
        if (regExp.test(obj.value)) {
            return true;
        } else {
            return false;
        }
    }
    
    function CheckDealerCode(dealerCode)
    {
        //        alert('in check');
        var url = "<%=cntxpath%>/masterAction.do?option=checkdealerCodeAjax&dealer_code="+dealerCode;

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
                    //                    document.getElementById("msg_saveFAILED").innerHTML = res.split("@@")[1];
                    if (res == 'exist') {
                        //                        document.getElementById('wageName').innerHTML = "" + wageName + "";
                        alert('Wage already added for '+dealerCode+'. You can update it.')
                    }
                }
            }
        };
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        return false;
    }
    function validateWage()
    {
        var elementArr = new Array('Dealer Code', 'wageName','wageValue');
        var strValue = null;
        var strObject = null;

        for (var i = 0; i < elementArr.length; i++)
        {
            strObject = document.getElementById(elementArr[i]);
            strValue = document.getElementById(elementArr[i]).value;
            if (checkValue(strObject, strValue) == false)
                return false;
        }
        document.getElementById('addWage').submit();
    }
</script>



<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href ='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
            <li class="breadcrumb_link">MANAGE WAGE</li>

        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED">${successmsg}</div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1>MANAGE WAGE </h1>


                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=initWageMaster" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">

                                        <td  style="padding-left:10px" width="100px" class="headingSpas" nowrap align="left">Wage Name</td>
                                        <td style="padding-left:10px" width="140px">
                                            <html:text property="nameSrch" styleClass="headingSpas" style="width:170px" onblur="this.value=TrimAll(this.value)"/>
                                        </td>
                                        <td style="padding-left:10px" align="left" >
                                            <input type=button name="Go" class="headingSpas" value="Search" onClick = "submitForm()"/>
                                        </td>
                                    </tr>
                                </table>
                            </html:form>
                        </td></tr>
                        <c:if test="${empty wageList}">
                        <tr bgcolor="#FFFFFF">
                            <td valign="top" class="headingSpas" style="padding-top:10px;color: red" align="center">
                                Wages not Found
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!empty wageList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="15">
                                    <pg:param name="option" value="initWageMaster"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td  nowrap align="center" width="35" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >S.No</td>
                                            <td   align="left" width="85" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>Dealer Code</td>
                                            <td   align="left" width="250" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Wage Name</td>
                                            <td   align="left" width="80" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Wage Value</td>
                                            <td   align="center" width="40" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>Edit</td>
                                            <td   align="center" width="35" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>isActive</td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="wageList" name="wageList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20">
                                                    <td align="center" bgcolor="#FFFFFF" width="85" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${wageList.dealer_code}" width="40" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="dealer_code{sno}" >
                                                            ${wageList.dealer_code}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${wageList.wageName}" width="250" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="wageName${sno}" >
                                                            ${wageList.wageName}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${wageList.wageValue}" width="80" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="wageValue${sno}" >
                                                            ${wageList.wageValue}
                                                        </span>
                                                    </td>

                                                    <td align="center" bgcolor="#FFFFFF" width="40" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="imageButton${sno}" >
                                                            <img src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow( ${sno}, '${wageList.wageName}','${wageList.wageValue}', '${wageList.dealer_code}')" title="Edit"/>
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="35" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >
                                                            <a href="javascript:void(0)" onclick="cUDAction('${wageList.status}', ${sno}, '${wageList.dealer_code}')"  class="headingSpas" style="color: blue;" >
                                                                ${wageList.status}&nbsp;
                                                            </a>
                                                        </span>
                                                    </td>
                                                </tr>
                                            </pg:item>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        <tr >
                                            <td colspan="6" align="center" bgcolor="#FFFFFF" class="textPaging" >
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
                            <form action="masterAction.do?option=manageWage&type=add" method="post" id="addWage" >
                                <table width=100% border="0" cellpadding="0" cellspacing="0" >
                                    <tr bgcolor="#eeeeee" height="20">
                                        <td  style="padding-left: 5px" align= left class="headingSpas">
                                            <b>ADD NEW WAGE</b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0" >
                                                <tr height="30">
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Select Dealer Code</td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                        <select name='dealer_code' id='Dealer Code' class="headingSpas" style='width:120px !important' onchange="CheckDealerCode(this.value);">
                                                            <option value='' >--Select Here--</option>
                                                            <c:forEach items='${dealerCodeList}'  var='labelValue'  varStatus='status'>
                                                                <option value='${labelValue.value}' title='${labelValue.label}'>${labelValue.value}</option>
                                                            </c:forEach></select>
                                                    </td>
                                                </tr>

                                                <tr height="30">
                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Wage Name</td>
                                                    <td style="padding-left:10px;padding-top:5px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="wageName"  id="wageName" class="headingSpas" size="200"  maxlength="200" style="width:220px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                </tr>
                                                <tr height="30">
                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Wage Value</td>
                                                    <td style="padding-left:10px;padding-top:5px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="wageValue"  id="wageValue" class="headingSpas"  maxlength="9" style="width:80px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                </tr>

                                                <tr height="20">
                                                    <td colspan="5" style="padding-left:10px;padding-bottom: 5px" align="center" bgcolor="#FFFFFF">
                                                        <input type=button name="add" value="Submit" class="headingSpas" onclick="validateWage()"/>
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
