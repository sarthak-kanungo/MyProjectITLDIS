<%-- 
    Document   : v_mechanicMaster
    Created on : Apr 17, 2014, 1:05:47 PM
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
        var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
        var ck_code = /^[a-zA-Z0-9]+$/;
        var ck_name = /^[a-z A-Z]+$/;

        strValue = strValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(strValue);
        var text = strObject.id;
        if (strObject.id == 'mechanic_Name') {
            text = 'Mechanic Name';
        }
        if (strObject.id == 'mechanic_Code') {
            text = 'Mechanic Code';
        }
        if (strValue.length == 0)
        {

            strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Value in " + text + " Field";
            window.scrollTo(0, 0);//go to top of page
            return false;

        }
        else if (strObject.id == 'mechanic_Code') {
            text = 'Mechanic Code';
            if (!ck_code.test(strValue)) {
                       strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
           document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in " + text + " Field";
            window.scrollTo(0, 0);//go to top of page
            return false;
                          }
        }
        else if (strObject.id == 'mechanic_Name') {
            text = 'Mechanic Name';
            if (!ck_name.test(strValue)) {
                       strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
           document.getElementById("msg_saveFAILED").innerHTML = "Special Characters & Numbers are not allowed in " + text + " Field";
            window.scrollTo(0, 0);//go to top of page
            return false;
                          }
        }
        else if (temp)
        {
            if (strObject.tagName == "INPUT")
            {
                //alert("Please Enter "+elementArr[i]);
                strObject.focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in " + text + " field";
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
            var elementArr = new Array('mechanic_Code', 'mechanic_Name');
            var strValue = null;
            var strObject = null;

            for (var i = 0; i < elementArr.length; i++)
            {
                strObject = document.getElementById(elementArr[i]);
                strValue = document.getElementById(elementArr[i]).value;
                if (checkValue(strObject, strValue) == false)
                    return false;
            }

            var mechanicCode = TrimAll(document.getElementById("mechanic_Code").value);
            var mechanicName = TrimAll(document.getElementById("mechanic_Name").value);
            url = "<%=cntxpath%>/masterAction.do?option=manageMechanic&type=" + str + "&mechanic_Code=" + mechanicCode + "&mechanic_Name=" + mechanicName + "&worker_type="+document.getElementById("worker_type").value+"&t=" + new Date().getTime();
           
        }
        else {
            url = "<%=cntxpath%>/masterAction.do?option=manageMechanic&status=" + str + "&type=" + str + "&mechanic_Code=" + strVal + "&t=" + new Date().getTime();
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
                        document.getElementById("mechanic_Code").value = '';
                        document.getElementById("mechanic_Name").value = '';
                        // setTimeout(function() { submitForm(); }, 3000);
                    }
                }
            }
        };
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);
        return false;
    }
    function editRow(mechanicCode, row, mechanicName) {
        //  document.getElementById('compType'+row).innerHTML="<input type=\"text\" name=\"item_no\" maxlength='15' class=\"headingSpas\" id=\"newItem_no"+row+"\" style=\"width:120px\" value=\""+itemNo+"\"/>";
        document.getElementById('mechanicName' + row).innerHTML = "<input type=\"text\" name=\"mechanic_Name\" size='200' maxlength='200' class=\"headingSpas\" id=\"newMechaninName" + row + "\" style=\"width:220px\" value=\"" + mechanicName + "\"/>";
        document.getElementById('imageButton' + row).innerHTML = "<input type=button name=\"apply\" class=\"headingSpas\" value=\"Apply\" onclick=\"updateAction('edit', " + row + ",'" + mechanicCode + "')\"/>";
    }

    function updateAction(str, row, mechanicCode)
    {
    <%--if(document.getElementById("newMechanin_Code"+row).value==""){
        document.getElementById("newMechanin_Code"+row).focus();
        document.getElementById("msg_saveFAILED").style.display = "";
        document.getElementById("msg_saveFAILED").innerHTML="Please enter value in Item No. field";
        return false;
    }--%>
            if (document.getElementById("newMechaninName" + row).value == "") {
                document.getElementById("newMechaninName" + row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Mechanic Name";
                return false;
            }
            if (!trim(document.getElementById("newMechaninName" + row).value).match(/^[a-z A-Z]+$/)) {
                document.getElementById("newMechaninName" + row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters & Numbers are not allowed in Mechanic Name Field";
                return false;
            }
             var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
            <%--var chckValue = document.getElementById("newMechanin_Code"+row).value;
            chckValue=chckValue.replace(objRegExp,'');
            var temp=objSpecExp.exec(chckValue);
            if(temp)
            {
                document.getElementById("newMechanin_Code"+row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML="Special Characters are not allowed in Mechanic Code. field";
                window.scrollTo(0,0);//go to top of page
                return false;
            }--%>
            var chckValue = document.getElementById("newMechaninName" + row).value;
            chckValue = chckValue.replace(objRegExp, '');
            var temp = objSpecExp.exec(chckValue);
            if (temp)
            {
                document.getElementById("newMechaninName" + row).focus();
                document.getElementById("msg_saveFAILED").style.display = "";
                document.getElementById("msg_saveFAILED").innerHTML = "Special Characters are not allowed in Mechanic Name field";
                window.scrollTo(0, 0);//go to top of page
                return false;
            }

            //  var item_No = TrimAll(document.getElementById("newMechanin_Code"+row).value);
            var mechanicName = TrimAll(document.getElementById("newMechaninName" + row).value);
            var url = "<%=cntxpath%>/masterAction.do?option=manageMechanic&id=" + mechanicCode + "&mechanic_Code=" + mechanicCode + "&mechanic_Name=" + mechanicName + "&type=" + str + "&t=" + new Date().getTime();
           
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
                            document.getElementById('mechanicName' + row).innerHTML = "" + mechanicName + "";
                            document.getElementById('imageButton' + row).innerHTML = "<img src=\"images/edit.gif\" onclick=\"editRow('" + mechanicCode + "'," + row + ",'" + mechanicName + "')\" title=\"Edit\"/>";
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
            <li class="breadcrumb_link">MANAGE MECHANIC</li>

        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED"></div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1>MANAGE MECHANIC </h1>


                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=initMechanicMaster" method="post" styleId="searchBy" onsubmit="donotsubmit();">
                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">

                                        <td  style="padding-left:10px" width="100px" class="headingSpas" nowrap align="left">Mechanic Name</td>
                                        <td style="padding-left:10px" width="140px">
                                            <html:text property="nameSrch" styleClass="headingSpas" maxlength="15" style="width:170px" onblur="this.value=TrimAll(this.value)"/>
                                        </td>
                                        <td style="padding-left:10px" align="left" >
                                            <input type=button name="Go" class="headingSpas" value="Search" onClick = "submitForm()"/>
                                        </td>
                                    </tr>
                                </table>
                            </html:form>
                        </td></tr>
                        <c:if test="${empty mechanicList}">
                        <tr bgcolor="#FFFFFF">
                            <td valign="top" align="center" class="headingSpas" style="padding-top:10px;color: red">
                                Mechanic Name Not Found. !
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${!empty mechanicList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="10">
                                    <pg:param name="option" value="initMechanicMaster"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td  nowrap align="center" width="35" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >S.No</td>
                                            <td   align="left" width="120" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Mechanic Code</td>
                                            <td   align="left"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Mechanic Name</td>
                                            <td   align="left"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Worker Type</td>
                                            <td   align="center" width="75" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>Edit</td>
                                            <td   align="center" width="35" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>isActive</td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="mechanicList" name="mechanicList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20">
                                                    <td align="center" bgcolor="#FFFFFF" width="35" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                    <td align="left" bgcolor="#FFFFFF" width="120" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="compType${sno}" >
                                                            ${mechanicList.mechanic_Code}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="mechanicName${sno}" >
                                                            ${mechanicList.mechanic_Name}
                                                        </span>
                                                    </td>
                                                        <td align="left" bgcolor="#FFFFFF"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="mechanicName${sno}" >
                                                            ${mechanicList.worker_type}
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="40" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="imageButton${sno}" >
                                                            <img src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${mechanicList.mechanic_Code}', ${sno}, '${mechanicList.mechanic_Name}', '${mechanicList.id}@@${mechanicList.name}')" title="Edit"/>
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="35" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}" >
                                                            <a href="javascript:void(0)" onclick="cUDAction('${mechanicList.status}', ${sno}, '${mechanicList.mechanic_Code}')"  class="headingSpas" style="color: blue;" >
                                                                ${mechanicList.status}&nbsp;
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
                                            <b>ADD NEW MECHANIC DETAILS</b>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0" >

                                                <tr height="30">
                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Mechanic Code</td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="mechanic_Code"  id="mechanic_Code" class="headingSpas"  maxlength="5" style="width:170px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>

                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Mechanic Name</td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="mechanic_Name"  id="mechanic_Name" class="headingSpas" size="200"  maxlength="200" style="width:170px" onblur="this.value=TrimAll(this.value)"/>
                                                    </td>
                                                    
                                                  
                                                    
                                                </tr>
                                                <br/>
                                                <tr height="30">
                                                      <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Worker Type</td>
                                                   
                                                     <td style="padding-left:11px" bgcolor="#FFFFFF" align="left">
                                                        <select name="worker_type" id="worker_type" class="headingSpas" style='width:170px'>
                                                            <option value="MECHANIC">
                                                                MECHANIC
                                                            </option>
                                                             <option value="FOREMAN">
                                                                FOREMAN
                                                            </option>
                                                        </select>
                                                            
                                                      
                                                    </td>
                                                </tr>
                                                <tr height="20">
                                                    <td colspan="4" style="padding-left:10px;padding-bottom: 5px" align="center" bgcolor="#FFFFFF">
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
