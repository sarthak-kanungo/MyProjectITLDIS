<%-- 
    Document   : v_SubAggregateMaster
    Created on : May 14, 2014, 1:55:19 PM
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
       var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
        var objSpecExpDes = /['#\*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
        var ck_code = /^[a-zA-Z0-9]+$/;
        strValue = strValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(strValue);
        var tempD= objSpecExpDes.exec(strValue);
       
        if (strValue.length == 0)
        {
            strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            if (strObject.tagName == "INPUT")
            {
                document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Value in " + strObject.id + " Field";
            } else {
                document.getElementById("msg_saveFAILED").innerHTML = "Please Select Value in " + strObject.id + " Field";
            }
            window.scrollTo(0, 0);//go to top of page
            return false;

        }
         else if (strObject.id == 'SubAgg Code') {
            text = 'Sub-Aggregate Code';
            if (!ck_code.test(strValue)) {
                       strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
           document.getElementById("msg_saveFAILED").innerHTML = "Special Characters or Space are not allowed in " + text + " Field";
            window.scrollTo(0, 0);//go to top of page
            return false;
                          }
        }

        //ck_code


        else if (tempD)
        {
            if (strObject.tagName == "INPUT")
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in " + strObject.id + " field";
                window.scrollTo(0, 0);//go to top of page
                return false;
            }
            return false;
        }
    }
    
    function cUDAction(str, row, strVal)
    {
        var url = null;
        if (str == 'add') {
            var elementArr = new Array('SubAgg Code','SubAgg Description','Aggregate Code');
            var strValue = null;
            var strObject = null;
            for (var i = 0; i < elementArr.length; i++)
            {
                strObject = document.getElementById(elementArr[i]);
                strValue = document.getElementById(elementArr[i]).value;
                if (checkValue(strObject, strValue) == false)
                    return false;
            }

            var subaggCode = TrimAll(document.getElementById("SubAgg Code").value);
            var subaggDesc = TrimAll(document.getElementById("SubAgg Description").value);
            var aggCode = TrimAll(document.getElementById("Aggregate Code").value);
            var tempDes=replaceall(subaggDesc,'&','`');

            url = "<%=cntxpath%>/masterAction.do?option=manageSubAggregate&type=" + str + "&subaggCode=" + subaggCode + "&subaggDesc=" + tempDes + "&aggCode=" + aggCode+ "&t=" + new Date().getTime();
        }
        else {
            url = "<%=cntxpath%>/masterAction.do?option=manageSubAggregate&status=" + str + "&type=" + str + "&subaggCode=" + strVal + "&t=" + new Date().getTime();
        }
//        alert(url);
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
                        document.getElementById("SubAgg Code").value='';
                        document.getElementById("SubAgg Description").value='';
                        document.getElementById("Aggregate Code").value='';
                        // setTimeout(function() { submitForm(); }, 3000);
                    }
                }
            }
        };
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        return false;
    }

    function editRow(subaggCode, row,subaggDesc,aggCodeid,subaggId){
       // alert(aggCodeid);
        var url = "<%=cntxpath%>/masterAction.do?option=manageSubAggregate&type=check&subaggCode="+subaggCode+"&t="+new Date().getTime();
        var flag = false;
        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
            {
                if (xmlHttp.responseText.indexOf("Session has been expired") != -1)
                {
                    document.location.href = "${contextPath}/login/SessionExpired.jsp";
                }
                else
                {
                    res = trim(xmlHttp.responseText);
                    if(res == 'notexist'){
                      //  document.getElementById('subaggCode'+row).innerHTML="<input type=\"text\" name=\"subaggCode\" maxlength='5' class=\"headingSpas\" id=\"newsubaggCode"+row+"\" style=\"width:80px\" value=\""+subaggCode+"\"/>";
                        document.getElementById('subaggDesc'+row).innerHTML="<input type=\"text\" name=\"subaggDesc\" size='200' maxlength='150' class=\"headingSpas\" id=\"newsubaggDesc"+row+"\" style=\"width:200px\" value=\""+subaggDesc+"\"/>";
                        flag = true;
                    }else{
                        document.getElementById('subaggDesc'+row).innerHTML="<input type=\"text\" name=\"subaggDesc\" size='200' maxlength='150' class=\"headingSpas\" id=\"newsubaggDesc"+row+"\" style=\"width:200px\" value=\""+subaggDesc+"\"/>";
                    }
                }
            }
            
        };
        xmlHttp.open("GET", url, false);
        xmlHttp.send(null);
        document.getElementById('iaggCode' + row).innerHTML = "<select name='name' class=\"headingSpas\" id='newName" + row + "' style='width:400px'><option value='' >--Select Here--</option><c:forEach items='${AggregateCodeList}'  var='labelValue'  varStatus='status'><option value='${labelValue.value}@@${labelValue.label}' title='${labelValue.label}'>${labelValue.value} [${labelValue.label}]</option></c:forEach></select>";    <%-- @@${labelValue.label}--%>
        document.getElementById('newName'+row).value = aggCodeid;  //.split("@@")[0]
        document.getElementById('imageButton'+row).innerHTML="<input type=button class=\"headingSpas\" name=\"apply\" value=\"Apply\" onclick=\"updateAction('edit', "+row+",'"+subaggCode+"','"+flag+"','"+subaggDesc+"','"+subaggId+"')\"/>";

    }
    function updateAction(str, row, oldsubaggCode,flag,subaggDesc,subaggId)
    {
        var objRegExp = /^(\s*)$/g;
       // var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
         var objSpecExp = /['#\*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
        var ck_code = /^[a-zA-Z0-9]+$/;
        var newsubaggCode='';
      <%--  if(flag == 'true'){
            if(document.getElementById("newsubaggCode"+row).value==""){
                document.getElementById("newsubaggCode"+row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML="Please enter value in Sub-Aggregate Code field";
                return false;
            }
            var chckValue = document.getElementById("newsubaggCode"+row).value;
//            chckValue=chckValue.replace(objRegExp,'');
//            var temp=objSpecExp.exec(chckValue);
//            if(temp)
//            {
//                document.getElementById("newsubaggCode"+row).focus();
//                document.getElementById("msg_saveFAILED").style.display = "";
//                document.getElementById("msg_saveFAILED").innerHTML="Special Characters are not allowed in Sub-Aggregate Code field";
//                window.scrollTo(0,0);//go to top of page
//                return false;
//            }
              text = 'Sub-Aggregate Code';
               if (!ck_code.test(chckValue)) {
               
                document.getElementById("newCausalCode"+row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in " + text + " field";
                window.scrollTo(0, 0);//go to top of page
               return false;
                        
              }
            newsubaggCode = TrimAll(document.getElementById("newsubaggCode" + row).value);
        }--%>
        if (document.getElementById("newsubaggDesc" + row).value == "") {
            document.getElementById("newsubaggDesc" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Sub-Aggregate Description";
            return false;
        }
            
        var chckValue = document.getElementById("newsubaggDesc" + row).value;
        chckValue = chckValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(chckValue);
        if (temp)
        {
            document.getElementById("newsubaggDesc" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in Sub-Aggregate Description field";
            window.scrollTo(0, 0);//go to top of page
            return false;
        }
        subaggDesc = TrimAll(document.getElementById("newsubaggDesc" + row).value);
         var tempDes=replaceall(subaggDesc,'&','`');
        
        if(document.getElementById("newName"+row).value==""){
            document.getElementById("newName"+row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML="Please Select Aggregate Code";
            return false;
        }
        var aggcode = TrimAll(document.getElementById("newName"+row).value);
       // alert(aggcode)
        var url = "<%=cntxpath%>/masterAction.do?option=manageSubAggregate&id=" + oldsubaggCode + "&subaggCode=" + newsubaggCode + "&subaggDesc=" + tempDes + "&aggCode=" + aggcode + "&primary_id=" + subaggId + "&type=" + str +"&t=" + new Date().getTime();
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
                        if(flag == 'true'){
                           // document.getElementById('subaggCode'+row).innerHTML=""+newsubaggCode+"";
                             document.getElementById('subaggCode'+row).innerHTML=""+oldsubaggCode+"";
                        }else{
                            document.getElementById('subaggCode'+row).innerHTML=""+oldsubaggCode+"";
                        }
                        document.getElementById('subaggDesc' + row).innerHTML = "" + subaggDesc + "";
                        document.getElementById('iaggCode' + row).innerHTML ="" + aggcode.split("@@")[0] + " ["+aggcode.split("@@")[1]+"]";// "" +aggcode;   //+aggcode.split("@@")[1]
                        document.getElementById('imageButton' + row).innerHTML = "<img src=\"images/edit.gif\" onclick=\"editRow('" + oldsubaggCode + "'," + row + ",'" + subaggDesc + "','"+aggcode+"','"+subaggId+"')\" title=\"Edit\"/>";
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
</script>



<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href ='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
            <li class="breadcrumb_link">MANAGE SUB-AGGREGATE MASTER</li>

        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED"></div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1>MANAGE SUB-AGGREGATE MASTER </h1>


                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=initSubAggregateMaster" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">

                                        <td  style="padding-left:10px" width="100px" class="headingSpas" nowrap align="left">Sub-Aggregate Description</td>
                                        <td style="padding-left:10px" width="140px">
                                            <html:text property="nameSrch" styleClass="headingSpas"  style="width:170px" onblur="this.value=TrimAll(this.value)"/>
                                        </td>
                                        <%--<td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Aggregate Code</td>
                                        <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                            <select name='aggcodefilter' id='Agg Code' class="headingSpas" style='width:260px'>
                                                <option value='' >--Select Here--</option>
                                                <c:forEach items='${AggregateCodeList}'  var='labelValue'  varStatus='status'>
                                                    <option value='${labelValue.value}' title='${labelValue.label}'>${labelValue.value} [${labelValue.label}]</option>
                                                </c:forEach></select>
                                        </td>--%>
                                        <td style="padding-left:10px" align="left" >
                                            <input type=button name="Go" class="headingSpas" value="Search" onClick = "submitForm()"/>
                                        </td>
                                    </tr>
                                </table>
                            </html:form>
                        </td></tr>
                        <c:if test="${empty SubAggregateCodeList}">
                        <tr bgcolor="#FFFFFF">
                            <td valign="top" align="center" class="headingSpas" style="padding-top:10px;color: red">
                                Sub-Aggregate Description not Found.!
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!empty SubAggregateCodeList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="10">
                                    <pg:param name="option" value="initSubAggregateMaster"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td  nowrap align="center" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >S.No</td>
                                            <td   align="left" width="80" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Sub-Aggregate&nbsp;Code</td>
                                            <td   align="left" width="120" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Sub-Aggregate&nbsp;Description</td>
                                            <td   align="left" width="220" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Aggregate Code</td>
                                            <td   align="center" width="75" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>Edit</td>
                                            <td   align="center" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>isActive</td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="SubAggregateCodeList" name="SubAggregateCodeList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20" >
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                    <td align="left" bgcolor="#FFFFFF" title=" ${SubAggregateCodeList.subaggCode}" width="80" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="subaggCode${sno}" >
                                                            ${SubAggregateCodeList.subaggCode}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${SubAggregateCodeList.subaggDesc}" width="120" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="subaggDesc${sno}" >
                                                            ${SubAggregateCodeList.subaggDesc}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" width="220" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <input type="hidden" name="aggCode" title="${SubAggregateCodeList.aggCode}[${SubAggregateCodeList.aggDesc}]" id="iaggCode" value="${SubAggregateCodeList.aggCode}@@${SubAggregateCodeList.aggDesc}"/>
                                                        <span id ="iaggCode${sno}" >
                                                            ${SubAggregateCodeList.aggCode} [${SubAggregateCodeList.aggDesc}]
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="20" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="imageButton${sno}" >
                                                            <img alt="" src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${SubAggregateCodeList.subaggCode}', ${sno}, '${SubAggregateCodeList.subaggDesc}','${SubAggregateCodeList.aggCode}@@${SubAggregateCodeList.aggDesc}','${SubAggregateCodeList.primary_id}')" title="Edit"/>   <%-- '${SubAggregateCodeList.id}@@${SubAggregateCodeList.aggCode}[${SubAggregateCodeList.aggDesc}]'--%>
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >
                                                            <a href="javascript:void(0)" onclick="cUDAction('${SubAggregateCodeList.status}', ${sno}, '${SubAggregateCodeList.subaggCode}')"  class="headingSpas" style="color: blue;" >
                                                                ${SubAggregateCodeList.status}&nbsp;
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
                            <form action="#" >
                                <table width=100% border="0" cellpadding="0" cellspacing="0" >
                                    <tr bgcolor="#eeeeee" height="20">
                                        <td  style="padding-left: 5px" align= left class="headingSpas">
                                            <b> ADD NEW SUB-AGGREGATE CODE</b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0" >

                                                <tr height="30">
                                                    <td  style="padding-left:10px" width="25%" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Sub-Aggregate Code</td>
                                                    <td style="padding-left:10px;padding-top: 5px" width="75%" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="subaggCode"  id="SubAgg Code" class="headingSpas"  maxlength="5" style="width:120px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                </tr>
                                                <tr height="30">

                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Sub-Aggregate Description</td>
                                                    <td style="padding-left:10px;padding-top: 5px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="subaggDesc"  id="SubAgg Description" class="headingSpas" size="200"  maxlength="150" style="width:240px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                </tr>
                                                <tr height="30">
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Select Aggregate Code</td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                        <select name='name' id='Aggregate Code' class="headingSpas" style='width:160px !important'>
                                                            <option value='' >--Select Here--</option>
                                                            <c:forEach items='${AggregateCodeList}'  var='labelValue'  varStatus='status'>
                                                                <option value='${labelValue.value}' title='${labelValue.label}'>${labelValue.value}[${labelValue.label}]</option>
                                                            </c:forEach></select>
                                                    </td>
                                                </tr>
                                                <tr height="20">
                                                    <td colspan="6" style="padding-left:10px;padding-bottom: 5px" align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                </tr>
                                                <tr height="20">
                                                    <td colspan="6" style="padding-left:10px;padding-bottom: 5px" align="center" bgcolor="#FFFFFF">
                                                        <input type=button name="add" value="Submit" class="headingSpas" onclick="cUDAction('add')"/>
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
