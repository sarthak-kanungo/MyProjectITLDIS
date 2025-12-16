<%--
    Document   : v_v_actionJobCard
    Created on : APR 21, 2014, 02:57:08 PM
    Author     : Avinash.Pandey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<link rel="stylesheet" href="${contextPath}/css/style.css" type="text/css" />
<link rel="stylesheet" href="${contextPath}/layout/css" type="text/css" />
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>

<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>
<script  type="text/javascript" language="javascript">

    function GetXmlHttpObject()
    {
        var objXmlHttp = null;
        if (navigator.userAgent.indexOf('Opera') >= 0)
        {
            var xmlHttp=new XMLHttpRequest();
            return xmlHttp;
        }
        if (navigator.userAgent.indexOf('MSIE') >= 0)
        {
            var strName = 'Msxml2.XMLHTTP';
            if (navigator.appVersion.indexOf('MSIE 5.5') >=0)
            {
                strName = 'Microsoft.XMLHTTP';
            }
            try
            {
                objXmlHttp = new ActiveXObject(strName);
                // objXmlHttp.onreadystatechange = handler;
                return objXmlHttp;
            }
            catch(e)
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

    function getCommonCode(obj,nameObj,row)
    {
        var objjCode = obj.value;
        if(objjCode!='NA'){
            var strURL="<%=cntxpath%>/data_master/getCompCodeListAjax.jsp?objjCode=" + objjCode+"&nameObj="+nameObj+"&rowCount="+row;
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    res = trim(xmlHttp.responseText);
                    document.getElementById(nameObj+row).innerHTML='';
                    document.getElementById(nameObj+row).innerHTML=res;

                }
            }
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);
        }else{
            document.getElementById(nameObj+row).innerHTML="<input type=\"text\" name=\"actionCode\" id=\"actionCode\" class=\"text\" maxlength=\"5\" style=\"width:170px\"/>";
            document.getElementById("serviceHrs"+row).value="0";
        }
    }

    var count = "2";
    function addRowC(in_tbl_name)
    {
        var tbody = document.getElementById(in_tbl_name).getElementsByTagName("TBODY")[0];
        // create row
        var row = document.createElement("TR");
     
        var td1 = document.createElement("TD")
        var strHtml1 = "<FONT style=\"padding-left:8px;height:24;float:left;border: 1 solid;margin:0;width=\"100\"\" bgcolor=\"#FFFFFF\" class=\"headingSpas\" nowrap align=\"left\"><select name=\"complaintCode\"  class=\"headingSpas\" style=\"width:170px\" id=\"complaintCode"+count+"\" onchange=\"getCommonCode(this,'Complaint',"+parseInt(count)+")\"><option value=''>--Select Here--</option><c:forEach items="${compJCList}" var='compJCList'><option value='${compJCList.value}' title='${compJCList.value}[ ${compJCList.label}]'>${compJCList.value}[ ${compJCList.label}]</option></c:forEach> <option value=\"NA\">NA</option></select></FONT>";
        td1.innerHTML = strHtml1.replace(/!count!/g,count);

        var td2 = document.createElement("TD")
        var strHtml2 = "<FONT  style=\"height:24;float:left;border: 1 solid;margin:0;width=\"100\"\" bgcolor=\"#FFFFFF\" class=\"headingSpas\" align=\"left\"><div id=\"Complaint"+count+"\">&nbsp;<select name=\"actionCode\" class=\"headingSpas\" style='width:170px' id=\"actionCode"+count+"\" onchange=\"getServiceHrsAjax(this,'Action_Taken',"+parseInt(count)+")\"><option value=\"\">--Select Here--</option></select></div></FONT>";
        td2.innerHTML = strHtml2.replace(/!count!/g,count);
        
        var td3 = document.createElement("TD")
        var strHtml3 = "<FONT style=\"padding-left:9px;height:24;float:left;border: 1 solid;margin:0;width=\"80\"\" bgcolor=\"#FFFFFF\" align=\"left\" ><div id=\"Action_Taken"+count+"\"><input type=\"text\" name=\"serviceHrs\" id=\"serviceHrs"+count+"\" class=\"text\" value=\"0\"  style=\"width:120px\" maxlength=\"12\"/></div></FONT>";
        td3.innerHTML = strHtml3.replace(/!count!/g,count);
      
        var td4 = document.createElement("TD")
        var strHtml4 = "<FONT style=\"padding-left:6px;height:24;float:left;border: 1 solid;margin:0;width=\"25\"\"><img src='"+contextPath+"/image/minus.gif' onclick=\"deleteRowC()\" class='statusicon' title=\"Delete Row\"/></FONT>";
        td4.innerHTML = strHtml4.replace(/!count!/g,count);
      
        row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);

        count = parseInt(count) + 1;
        tbody.appendChild(row);

    }
    function deleteRowC()
    {
        var oRows = document.getElementById('myTable').getElementsByTagName('tr') ;
        var rowLength=oRows.length;
        if(rowLength>1){
            document.getElementById('myTable').deleteRow(rowLength-1);
        }
    }
    function validate()
    {

        var complaintCode=new Array();
        var actionCode=new Array();
        var serviceHrs=new Array();

        complaintCode=document.getElementsByName("complaintCode");
        actionCode=document.getElementsByName("actionCode");
        serviceHrs=document.getElementsByName("serviceHrs");
        for(var i=0;i<complaintCode.length;i++ ){

            if(complaintCode[i].value=='')
            {
                document.getElementById("msg_saveFAILED").innerHTML="Please Select Defect Code"
                complaintCode[i].focus();
                return false;
            }
            if(actionCode[i].value=='NA')
            {
                document.getElementById("msg_saveFAILED").innerHTML="Please Enter Action Taken"
                actionCode[i].focus();
                return false;
            }else if(actionCode[i].value=='')
            {
                document.getElementById("msg_saveFAILED").innerHTML="Please Select Action Taken"
                actionCode[i].focus();
                return false;
            }
            if(serviceHrs[i].value=='')
            {
                document.getElementById("msg_saveFAILED").innerHTML="Please enter Service Hrs"
                serviceHrs[i].focus();
                return false;
            }
        }
        var answer = confirm("Do you really want to save ? ")
        if (answer){
            document.getElementById("masterForm").submit();
            //return true;
        }
        else{
            return false;
        }

    }

    function notblank(obj)
    {
        var objSpecExp=/\S+/g;
        if(objSpecExp.test(obj))
        {
            return true;
        }
        return false
    }

    function getServiceHrsAjax(obj,nameObj,row)
    {
        var objjCode = obj.value;
        var strURL = "<%=cntxpath%>/masterAction.do?option=getServiceHrsAjax&objjCode=" + objjCode+"&nameObj="+nameObj+"&rowCount="+row+"&tm=" + new Date().getTime();

        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")

            {
                res = trim(xmlHttp.responseText);
                document.getElementById(nameObj+row).innerHTML='';
                document.getElementById(nameObj+row).innerHTML="<input type=\"text\" name=\"serviceHrs\" id=\"serviceHrs"+row+"\" class=\"text\" value="+res+"  style=\"width:120px\" maxlength=\"12\"/>";

            }
        }
        xmlHttp.open("POST", strURL, true);
        xmlHttp.send(null);

    }

   

</script>

<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href ='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
            <li class="breadcrumb_link">ADD ACTION</li>

        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED"></div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 80%">
                <h1>ADD ACTION</h1>
                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=manageActionJobCard"   method="Post" styleId="masterForm" onsubmit="return false" >
                                <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/masterAction.do?option=initManageJobCard'>MANAGE JOB CARD</a>@@ADD ACTION"/>
                                <table width="95%" border="0" cellspacing="1" cellpadding="1">
                                    <tr>
                                        <td colspan="2">
                                            <table id="myTable" border="0" width="100%" >
                                                <tr bgcolor="#eeeeee" height="20">
                                                    <%-- <td  nowrap align="center" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >S.No</td>--%>
                                                    <td   align="left" width="100" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Select Complaint</td>
                                                    <td   align="left" width="100" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Select / Enter Action Taken</td>
                                                    <td   align="left" width="80" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Labour Charges</td>
                                                    <td   align="left" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap><img alt="" src='${contextPath}/image/plus.gif' onclick="addRowC('myTable')" class='statusicon'/></td>
                                                </tr>
                                                <tr>
                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                        <select name="complaintCode"  class="headingSpas" style='width:170px' id="complaintCode1" onchange="getCommonCode(this,'Complaint',1)">
                                                            <option value="">--Select Here--</option>
                                                            <c:forEach items="${compJCList}" var="compJCList">
                                                                <option value='${compJCList.value}' title='${compJCList.value}[ ${compJCList.label}]'>${compJCList.value}[ ${compJCList.label}]</option>
                                                            </c:forEach>
                                                            <option value="NA">NA</option>
                                                        </select>
                                                    </td>
                                                    <td  style="padding-left:6px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                        <div id="Complaint1">
                                                            <select name="actionCode" class="headingSpas" style='width:170px' id="actionCode1" onchange="getServiceHrsAjax(this,'Action_Taken',1)">
                                                                <option value="">--Select Here--</option>

                                                            </select>
                                                        </div>
                                                    </td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                                        <div id="Action_Taken1"><input type="text" name="serviceHrs" id="serviceHrs1" class="text"  style="width:120px" maxlength="12"/></div>
                                                    </td>
                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">&nbsp;</td>
                                                </tr>
                                            </table>

                                        </td>
                                    </tr>
                                </table>
                                <table width="100%" border="0" cellspacing="0" cellpadding="0">
                                    <tr >

                                        <td  width="10%" height="25" align="center" style="padding-right:5px; padding-top:5px">
                                            <input type="submit" name="Submit" class="text" value="Submit"  onclick="return validate()"/>

                                        </td>
                                        <%--<td width="50%" height="25" align="left" style="padding-left:5px; padding-top:5px">
                                            <input type="button"  class="text" name="Next2" id="Next2" value="Cancel" onClick='goBack()'/></td>--%>
                                    </tr>
                                </table>
                            </html:form>
                        </td>
                    </tr>
                </table>
            </div>
            <table border="0" ><tr align="center" ><td id="msg_saveFAILEDAjax" style="color: Red;height:25px" align="center" width="850px" height="5px"></td></tr></table>
        </div>
    </center>
</div>
