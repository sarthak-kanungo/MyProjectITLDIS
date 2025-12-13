<%-- 
    Document   : complaintCodeMaster
    Created on : Apr 18, 2014, 10:25:21 AM
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
//        var objRegExp = /^(\s*)$/g;
//        var objSpecExp = /['&!#\$%\^*(){},\/\[\]~`";:>\|<.?\\@]/g;
        var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
        var objSpecExpDes = /['*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
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
                document.getElementById("msg_saveFAILED").innerHTML = "Please Select Value in Sub " + strObject.id + " Field";
            }
            window.scrollTo(0, 0);//go to top of page
            return false;

        }
       else if (strObject.id == 'Defect Code') {
            text = 'Defect Code';
            if (!ck_code.test(strValue)) {
                       strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
           document.getElementById("msg_saveFAILED").innerHTML = "Special Characters or Space are not allowed in " + text + " Field";
            window.scrollTo(0, 0);//go to top of page
            return false;
                          }
        }
        else if (tempD)
        {
            if (strObject.tagName == "INPUT")
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in " + strObject.id + " Field";
                window.scrollTo(0, 0);//go to top of page
                return false;
            }
            return false;
        }
    }
    
    function cUDAction(str, row, strVal,compid)
    {

        var url = null;

        if (str == 'add') {
            var elementArr = new Array('Defect Code','Defect Description','Assembly Code');
            var strValue = null;
            var strObject = null;

            for (var i = 0; i < elementArr.length; i++)
            {
                strObject = document.getElementById(elementArr[i]);
                strValue = document.getElementById(elementArr[i]).value;
                if (checkValue(strObject, strValue) == false)
                    return false;
            }

            var compCode = TrimAll(document.getElementById("Defect Code").value);
            var compDesc = TrimAll(document.getElementById("Defect Description").value);
            var tempDes=replaceall(compDesc,'&','`');
            var assmId = TrimAll(document.getElementById("Assembly Code").value);


            url = "<%=cntxpath%>/masterAction.do?option=manageComplaintCode&type=" + str + "&compCode=" + compCode + "&compDesc=" + tempDes + "&assmId=" + assmId +"&compid="+compid+"&t=" + new Date().getTime();
        }
        else {
            url = "<%=cntxpath%>/masterAction.do?option=manageComplaintCode&status=" + str + "&type=" + str + "&compCode=" + strVal + "&compid="+compid+"&t=" + new Date().getTime();
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
                        document.getElementById("Defect Code").value='';
                        document.getElementById("Defect Description").value='';
                        document.getElementById("Assembly Code").value='';
                        // setTimeout(function() { submitForm(); }, 3000);
                    }
                }
            }
        };
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        return false;
    }

    function editRow(compCode, row,compDesc,assmCodeid,compId,assdesc){
        //alert(compId);
        //alert(assdesc.split("[")[0])
        var url = "<%=cntxpath%>/masterAction.do?option=manageComplaintCode&type=check&compCode="+compCode+"&t="+new Date().getTime();
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
                       // document.getElementById('compCode'+row).innerHTML="<input type=\"text\" name=\"compCode\" maxlength='5' class=\"headingSpas\" id=\"newCompCode"+row+"\" style=\"width:80px\" value=\""+compCode+"\"/>";
                        document.getElementById('compDesc'+row).innerHTML="<input type=\"text\" name=\"compDesc\" size='200' maxlength='150' class=\"headingSpas\" id=\"newCompDesc"+row+"\" style=\"width:300px\" value=\""+compDesc+"\"/>";
                        flag = true;
                    }else{
                        document.getElementById('compDesc'+row).innerHTML="<input type=\"text\" name=\"compDesc\" size='200' maxlength='150' class=\"headingSpas\" id=\"newCompDesc"+row+"\" style=\"width:300px\" value=\""+compDesc+"\"/>";
                    }
                }
            }
            
        };
        xmlHttp.open("GET", url, false);
        xmlHttp.send(null);
        
        document.getElementById('iassmCode' + row).innerHTML = "<select name='name' class=\"headingSpas\" id='newName" + row + "' style='width:220px'><option value='' >--Select Here--</option><c:forEach items='${assmCodeList}'  var='labelValue'  varStatus='status'><option value='${labelValue.value}' title='${labelValue.label}'>${labelValue.label}</option></c:forEach></select>";
        document.getElementById('newName'+row).value = assdesc.split("[")[0];//assmCodeid;
        
        //document.getElementById('iassmDesc'+row).value = assdesc;
        document.getElementById('imageButton'+row).innerHTML="<input type=button class=\"headingSpas\" name=\"apply\" value=\"Apply\" onclick=\"updateAction('edit', "+row+",'"+compCode+"','"+flag+"','"+compDesc+"','"+compId+"' )\"/>";

    }
    function updateAction(str, row, oldCompCode,flag,compDesc,compId)
    {
     //   alert(compId);

        var e = document.getElementById('newName'+row);
        var assdesc= e.options[e.selectedIndex].innerHTML;
        

        var objRegExp = /^(\s*)$/g;
       // var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
          var objSpecExp = /['*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
         var ck_code = /^[a-zA-Z0-9]+$/;
        var newCompCode='';
        <%--if(flag == 'true'){
            if(document.getElementById("newCompCode"+row).value==""){
                document.getElementById("newCompCode"+row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML="Please enter value in Defect Code field";
                return false;
            }
            var chckValue = document.getElementById("newCompCode"+row).value;
//            chckValue=chckValue.replace(objRegExp,'');
//            var temp=objSpecExp.exec(chckValue);
//            if(temp)
//            {
//                document.getElementById("newCompCode"+row).focus();
//                document.getElementById("msg_saveFAILED").style.display = "";
//                document.getElementById("msg_saveFAILED").innerHTML="Special Characters are not allowed in Defect Code field";
//                window.scrollTo(0,0);//go to top of page
//                return false;
//            }
             text = 'Defect Code';
               if (!ck_code.test(chckValue)) {
               
                document.getElementById("newCompCode"+row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in " + text + " field";
                window.scrollTo(0, 0);//go to top of page
               return false;
                        
              }
            newCompCode = TrimAll(document.getElementById("newCompCode" + row).value);
        }--%>
        if (document.getElementById("newCompDesc" + row).value == "") {
            document.getElementById("newCompDesc" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Defect Description";
            return false;
        }
            
        var chckValue = document.getElementById("newCompDesc" + row).value;
        chckValue = chckValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(chckValue);
        if (temp)
        {
            document.getElementById("newCompDesc" + row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in Defect Description Field";
            window.scrollTo(0, 0);//go to top of page
            return false;
        }
        var compDesc = TrimAll(document.getElementById("newCompDesc" + row).value);
        var tempDes=replaceall(compDesc,'&','`');
        
        if(document.getElementById("newName"+row).value==""){
            document.getElementById("newName"+row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML="Please Select Sub Assembly Code";
            return false;
        }
        var assmCode = TrimAll(document.getElementById("newName"+row).value);
        
        var url = "<%=cntxpath%>/masterAction.do?option=manageComplaintCode&id=" + oldCompCode + "&compCode=" + newCompCode + "&compDesc=" + tempDes + "&assmId=" + assmCode + "&compid=" + compId + "&type=" + str +"&t=" + new Date().getTime();

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
                            document.getElementById('compCode'+row).innerHTML=""+newCompCode+"";
                        }else{
                            document.getElementById('compCode'+row).innerHTML=""+oldCompCode+"";
                        }
                        document.getElementById('compDesc' + row).innerHTML = "" + compDesc + "";

                        document.getElementById('iassmCode' + row).innerHTML = "" + assdesc + "";
                        document.getElementById('imageButton' + row).innerHTML = "<img src=\"images/edit.gif\" onclick=\"editRow('" + oldCompCode + "'," + row + ",'" + compDesc + "','"+assmCode+"','"+compId+"')\" title=\"Edit\"/>";
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
            <li class="breadcrumb_link">MANAGE DEFECT CODE</li>

        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED"></div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1>MANAGE DEFECT CODE </h1>


                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=initComplaintCode" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">

                                        <td  style="padding-left:10px" width="100px" class="headingSpas" nowrap align="left">Defect Description</td>
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
                        <c:if test="${empty complaintCodeList}">
                        <tr bgcolor="#FFFFFF">
                            <td valign="top" align="center" class="headingSpas" style="padding-top:10px;color: red">
                                Defect Description not Found. !
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!empty complaintCodeList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="10">
                                    <pg:param name="option" value="initComplaintCode"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td  nowrap align="center" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >S.No</td>
                                            <td   align="left" width="80" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Defect&nbsp;Code</td>
                                            <td   align="left" width="120" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Defect&nbsp;Description</td>
                                            <td   align="left" width="220" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Sub Assembly Code</td>
                                            <td   align="center" width="75" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>Edit</td>
                                            <td   align="center" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>is Active</td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="complaintCodeList" name="complaintCodeList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20" >

                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                    <td align="left" bgcolor="#FFFFFF" title=" ${complaintCodeList.compCode}" width="80" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="compCode${sno}" >
                                                            ${complaintCodeList.compCode}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${complaintCodeList.compDesc}" width="300" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="compDesc${sno}" >
                                                            ${complaintCodeList.compDesc}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" width="120" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <input type="hidden" name="assmCode" title="${complaintCodeList.assmCode}[${complaintCodeList.assmDesc}]" id="iassmCode" value="${complaintCodeList.assmCode}@@${complaintCodeList.assmDesc}"/>
                                                        <input type="hidden" name="assmDesc" id="iassmDesc" value="${complaintCodeList.assmDesc}"/>
                                                        <span id ="iassmCode${sno}" >
                                                            ${complaintCodeList.assmCode} [${complaintCodeList.assmDesc}]
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="20" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="imageButton${sno}" >

                                                            <img alt="" src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${complaintCodeList.compCode}', ${sno}, '${complaintCodeList.compDesc}','${complaintCodeList.assmId}','${complaintCodeList.primary_id}','${complaintCodeList.assmCode}[${complaintCodeList.assmDesc}]')" title="Edit"/>
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >
                                                            <a href="javascript:void(0)" onclick="cUDAction('${complaintCodeList.status}', ${sno}, '${complaintCodeList.compCode}','${complaintCodeList.compid}')"  class="headingSpas" style="color: blue;" >
                                                                ${complaintCodeList.status}&nbsp;
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
                                            <b>ADD NEW DEFECT CODE</b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0" >

                                                <tr height="30">
                                                    <td  style="padding-left:10px" width="25%" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Defect Code</td>
                                                    <td style="padding-left:10px;padding-top: 5px" width="75%" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="compCode"  id="Defect Code" class="headingSpas"  maxlength="5" style="width:120px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                </tr>
                                                <tr height="30">

                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Defect Description</td>
                                                    <td style="padding-left:10px;padding-top: 5px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="compDesc"  id="Defect Description" class="headingSpas"  maxlength="150" style="width:240px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                </tr>
                                                <tr height="30">
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Select Sub-Assembly Code</td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                        <select name='name' id='Assembly Code' class="headingSpas" style='width:245px !important'>
                                                            <option value='' >--Select Here--</option>
                                                            <c:forEach items='${assmCodeList}'  var='labelValue'  varStatus='status'>
                                                                <option value='${labelValue.value}' title='${labelValue.label}'>${labelValue.label}</option>
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
