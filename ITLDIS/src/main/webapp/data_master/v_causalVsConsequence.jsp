<%-- 
    Document   : v_causalVsConsequence
    Created on : May 2, 2014, 1:05:51 PM
    Author     : Avinash.Pandey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="java.util.*" %>
<%
    response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
    response.setHeader("Expires", "0");
    response.setHeader("Pragma", "no-cache");
%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:set var="contextPath" value="<%=request.getContextPath()%>" />

<script type="text/JavaScript" language="javascript" src="${contextPath}/js/validation.js"></script>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>
<script language="JavaScript" type="text/JavaScript">

    function showItems(causalCode)
    {

        if (causalCode != 'select')
        {

            var strURL = "${contextPath}/masterAction.do?option=getAssignedConsequenceCode&causalCode=" + causalCode + ""
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
                        var res = xmlHttp.responseText;
                        var available_consequence = window.document.form1.available_consequence;
                        var assigned_consequence = window.document.form1.assigned_consequence;
                        available_consequence.options.length = 0;
                        assigned_consequence.options.length = 0;
                        var assignConsArr = (res.split("##")[0]).split("@@");
                        var unAssignConsArr = (res.split("##")[1]).split("@@");
                        var count = 0;
                        for (var i = 0; i < assignConsArr.length - 1; i++)
                        {

                            if (assignConsArr[i] != '' || assignConsArr[i] != null)
                            {

                                assigned_consequence.options[count] = new Option(assignConsArr[i].split("|")[0]);
                                assigned_consequence.options[count].text = assignConsArr[i].split("|")[1];
                                assigned_consequence.options[count].value = assignConsArr[i].split("|")[0];
                                assigned_consequence.options[count].title = assignConsArr[i].split("|")[1];
                                count++;
                            }
                        }
                        count = 0;
                        for (var i = 0; i < unAssignConsArr.length - 1; i++)
                        {
                            if (unAssignConsArr[i] != '' || unAssignConsArr[i] != null)
                            {

                                available_consequence.options[count] = new Option(unAssignConsArr[i].split("|")[0]);
                                available_consequence.options[count].text = unAssignConsArr[i].split("|")[1];
                                available_consequence.options[count].value = unAssignConsArr[i].split("|")[0];
                                available_consequence.options[count].title = unAssignConsArr[i].split("|")[1];
                                count++;
                            }
                        }
                    }

                }
            };
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);
        }
        else
        {
            window.document.form1.available_consequence.options.length = 0;
            window.document.form1.assigned_consequence.options.length = 0;
        }

    }

    function validate()
    {
        if (document.form1.causalCode.value == 'select')
        {
            document.getElementById("msg_saveFAILED").innerHTML = "Please Select Cause Code.";
            document.form1.causalCode.focus();
            return false;
        }

        else if (document.form1.assigned_consequence.options.length == 0)
        {
            document.getElementById("msg_saveFAILED").innerHTML = 'No Consequence Code Selected to Assign.';
            return false;
        }

        else
        {

            for (var i = 0; i < document.form1.assigned_consequence.options.length; i++)
            {
                document.form1.assigned_consequence.options[i].selected = true;
            }
            document.form1.submit();
        }

        return true;

    }
</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href = '${contextPath}/masterAction.do?option=initOptions' >MASTERS</a></li>
            <li class="breadcrumb_link">CAUSE CODE VS CONSEQUENCE CODE</li>

        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED"></div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1>CAUSE CODE VS CONSEQUENCE CODE</h1>

                <form action="${contextPath}/masterAction.do?option=assignConsequenceCode" method=post name=form1 >
                     <input type="hidden" name="upperLink" value="<a href = '${contextPath}/masterAction.do?option=initOptions'>MASTERS</a>@@MANAGE CAUSE CODE VS CONSEQUENCE CODE"/>

                    <table width=100% border=0 cellspacing=1 cellpadding=5 >

                        <tr bgcolor=#ffffff><td>
                                <table width=100% border=0 cellspacing=0 cellpadding=0 class="tableStyle">

                                    <tr height=20 bgcolor=#ffffff>
                                        <td  align=left width="45%" height="24" class="headingSpas"> <div align=right>
                                                Select Cause Code
                                            </div></td>
                                        <td align=left width="55%"  height="24" style="padding-left: 5px">
                                            <select name ="causalCode" class="headingSpas" onchange="showItems(this.value)" style="width: 160px !important">
                                                <option value="select">-Select-</option>
                                                <c:forEach items="${causalCodeList}" var="labelvalue" varStatus="status">
                                                    <option value="${labelvalue.value}" title="${labelvalue.value} [ ${labelvalue.label} ]">${labelvalue.value} [ ${labelvalue.label} ]</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                
                                    <tr height=20 bgcolor=#ffffff>
                                        <td  height="24" colspan=2>
                                            <table border=0 width=100% cellpadding="1" cellspacing="1">
                                                <tr height=20 bgcolor=#ffffff>
                                                    <td height="24"  class="headingSpas"> <div align=center>
                                                            AVAILABLE CONSEQUENCE CODE
                                                        </div></td>
                                                    <td align=center >&nbsp;</td>
                                                    <td align=center class="headingSpas" height="24">
                                                        ASSIGNED CONSEQUENCE CODE
                                                    </td>
                                                </tr>
                                                <tr height=20 bgcolor=#ffffff>
                                                    <td rowspan=6 align=center ><select type="multi" name="available_consequence" class="heading" multiple size="10" style="width:240px!important;height:200px "> </select></td>
                                                    <td align=center >&nbsp;</td>
                                                    <td rowspan=6 align=center  ><select type="multi" name="assigned_consequence" class="heading" multiple size="10" style="width:240px!important;height:200px "> </select>
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td align="center" bgcolor="#FFFFFF"><label>
                                                            <img src="${pageContext.request.contextPath}/images///next.gif" name="selectone" alt="Add" style="width:24px " onclick="moveDualList(document.form1.available_consequence, document.form1.assigned_consequence, false);" />
                                                        </label></td>
                                                </tr>
                                                <tr>
                                                    <td align="center" bgcolor="#FFFFFF"><label>
                                                            <img src="${pageContext.request.contextPath}/images///next1.gif" name="selectall" alt="Add All" style="width:24px " onclick="moveDualList(document.form1.available_consequence, document.form1.assigned_consequence, true);"/>
                                                        </label></td>
                                                </tr>
                                                <tr>
                                                    <td align="center" bgcolor="#FFFFFF"><label>
                                                            <img src="${pageContext.request.contextPath}/images///previous.gif" name="removeone" alt="Remove" style="width:24px " onclick="moveDualList(document.form1.assigned_consequence, document.form1.available_consequence, false);"/>
                                                        </label></td>
                                                </tr>
                                                <tr>
                                                    <td align="center" bgcolor="#FFFFFF"><label>
                                                            <img src="${pageContext.request.contextPath}/images///previous1.gif" name="removeall" alt="Remove All" style="width:24px " onclick="moveDualList(document.form1.assigned_consequence, document.form1.available_consequence, true);"/>
                                                        </label></td>
                                                </tr>
                                                <tr>
                                                    <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                </tr>


                                                <tr height=20 bgcolor=#ffffff>
                                                    <td align=left colspan = 3><div align=center>
                                                            <input type=button name=Submit class="headingSpas" value=Submit onclick="validate()">
                                                        </div></td>
                                                </tr>
                                               
                                            </table>
                                        </td>
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




