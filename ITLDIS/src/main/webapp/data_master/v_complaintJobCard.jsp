<%--
    Document   : v_jobCard
    Created on : Apr 19, 2014, 04:25:09 PM
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
    function validate()
    {
        
        var compVerbatim=new Array();
        var aggCode=new Array();
        var compCode=new Array();
        var causalCode=new Array();
        var consequenceCode=new Array();
        var sparex=new Array();
        compVerbatim=document.getElementsByName("compVerbatim");
        aggCode=document.getElementsByName("aggCode");
        compCode=document.getElementsByName("compCode");
        causalCode=document.getElementsByName("causalCode");
        consequenceCode=document.getElementsByName("consequenceCode");
        for(var i=0;i<compVerbatim.length;i++ ){

            if(compVerbatim[i].value=='')
            {
                document.getElementById("msg_saveFAILED").innerHTML="Please Enter Complaint Verbatim"
                compVerbatim[i].focus();
                return false;
            }

            var res1=true;
            for(var k=0;k<sparex.length;k++)
            {
                if(sparex[k]==(compVerbatim[i].value))
                {
                    res1=false;
                }
            }
            if(res1==false)
            {
                document.getElementById("msg_saveFAILED").innerHTML="Same Complaint Verbatim can not be enter twice";
                compVerbatim[k].value='';
                compVerbatim[k].focus();
                return false;
            }
            if (!trim( compVerbatim[i].value).match(/^[a-z A-Z 0-9]+$/) &&  compVerbatim[i].value !="")
            {
                compVerbatim[i].value="";
                compVerbatim[i].focus();
                document.getElementById("msg_saveFAILED").innerHTML="Please Enter only alphabets in Complaint Verbatim";
                return false;
            }
            sparex.push(compVerbatim[i].value);
            if(aggCode[i].value=='')
            {
                document.getElementById("msg_saveFAILED").innerHTML="Please Select Aggregate"
                aggCode[i].focus();
                return false;
            }
            if(compCode[i].value=='')
            {
                document.getElementById("msg_saveFAILED").innerHTML="Please Select Defect Code"
                compCode[i].focus();
                return false;
            }
            if(causalCode[i].value=='')
            {
                document.getElementById("msg_saveFAILED").innerHTML="Please Select Cause Code"
                causalCode[i].focus();
                return false;
            }
            if(consequenceCode[i].value=='')
            {
                document.getElementById("msg_saveFAILED").innerHTML="Please Select Consequences"
                consequenceCode[i].focus();
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
        //alert(row);
        var strURL="<%=cntxpath%>/data_master/getCompCodeListAjax.jsp?objjCode=" + objjCode+"&nameObj="+nameObj+"&rowCount="+row;
        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")

            {
                res = trim(xmlHttp.responseText);
                    
                document.getElementById(nameObj+row).innerHTML=res;
                    
            }
        }
        xmlHttp.open("POST", strURL, true);
        xmlHttp.send(null);
    }

    var count = "2";
    function addRowC(in_tbl_name)
    {
        var tbody = document.getElementById(in_tbl_name).getElementsByTagName("TBODY")[0];
        // create row
        var row = document.createElement("TR");
        // create table cell 1
       // var td1 = document.createElement("TD")
        //var strHtml1 = "<FONT align=\"center\" width=\"25\" style=\"padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;height:24;border: 1 solid;margin:0;;\">"+count+"</FONT>";
       // td1.innerHTML = strHtml1.replace(/!count!/g,count);
        // create table cell 2
        var td2 = document.createElement("TD")
        var strHtml2 = "<FONT style=\"padding-left:6px;height:24;border: 1 solid;margin:0;width=\"50\"\" bgcolor=\"#FFFFFF\" align=\"left\" ><input type=\"text\" name=\"compVerbatim\" id=\"compVerbatim"+count+"\" class=\"text\"  style=\"width:120px\" maxlength=\"200\"/></FONT>";
        td2.innerHTML = strHtml2.replace(/!count!/g,count);
        // create table cell 3
        var td3 = document.createElement("TD")
        var strHtml3 = "<FONT style=\"padding-left:8px;height:24;border: 1 solid;margin:0;width=\"120\"\" bgcolor=\"#FFFFFF\" class=\"headingSpas\" nowrap align=\"left\"><select name=\"aggCode\"  class=\"headingSpas\" style=\"width:170px\" id=\"aggCode"+count+"\" onchange=\"getCommonCode(this,'Agg_Code',"+parseInt(count)+")\"><option value=''>--Select Here--</option><c:forEach items="${aggregateList}" var='aggregateList'><option value='${aggregateList.value}' title='${aggregateList.value}[ ${aggregateList.label}]'>${aggregateList.value}</option></c:forEach></select></FONT>";
        td3.innerHTML = strHtml3.replace(/!count!/g,count);
        // create table cell 4
        var td4 = document.createElement("TD")
        var strHtml4 = "<FONT  style=\"padding-left:12px;height:24;border: 1 solid;margin:0;width=\"120\"\" bgcolor=\"#FFFFFF\" class=\"headingSpas\" align=\"left\"><div id=\"Agg_Code"+count+"\">&nbsp;<select name=\"compCode\" class=\"headingSpas\" style='width:170px' id=\"compCode"+count+"\" onchange=\"getCommonCode(this,'Comp_Code',"+parseInt(count)+")\"><option value=\"\">--Select Here--</option></select></div></FONT>";
        td4.innerHTML = strHtml4.replace(/!count!/g,count);
        // create table cell 5
        var td5 = document.createElement("TD")
        var strHtml5 = "<FONT  style=\"padding-left:12px;height:24;border: 1 solid;margin:0;width=\"20\"\" bgcolor=\"#FFFFFF\" class=\"headingSpas\"  align=\"left\"><div id=\"Comp_Code"+count+"\"><select name=\"causalCode\" class=\"headingSpas\" style='width:170px' id=\"causalCode"+count+"\" onchange=\"getCommonCode(this,'Causal_Code',"+parseInt(count)+")\"><option value=\"\">--Select Here--</option></select></div></FONT>";
        td5.innerHTML = strHtml5.replace(/!count!/g,count);
        // create table cell 6
        var td6 = document.createElement("TD")
        var strHtml6 = "<FONT  style=\"padding-left:12px;height:24;border: 1 solid;margin:0;width=\"25\"\" bgcolor=\"#FFFFFF\" class=\"headingSpas\"  align=\"left\"><div id=\"Causal_Code"+count+"\"><select name=\"consequenceCode\" class=\"headingSpas\" style='width:170px' id=\"consequenceCode"+count+"\" ><option value=\"\">--Select Here--</option></select></div></FONT>";
        td6.innerHTML = strHtml6.replace(/!count!/g,count);
        // create table cell 7
        var td7 = document.createElement("TD")
        var strHtml7 = "<FONT><img src='"+contextPath+"/image/minus.gif' onclick=\"deleteRowC()\" class='statusicon' title=\"Delete Row\"/></FONT>";
        td7.innerHTML = strHtml7.replace(/!count!/g,count);
        // append data to row
       // row.appendChild(td1);
        row.appendChild(td2);
        row.appendChild(td3);
        row.appendChild(td4);
        row.appendChild(td5);
        row.appendChild(td6);
        row.appendChild(td7);
        // add to count variable
        count = parseInt(count) + 1;
        // append row to table
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

    <%--function deleteRowC()
    {
        var current = window.event.srcElement;
        //here we will delete the line
        while ( (current = current.parentElement)  && current.tagName !="TR");
        current.parentElement.removeChild(current);
            
    }--%>
</script>

<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href ='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
            <li class="breadcrumb_link">ADD COMPLAINT</li>

        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED"></div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1>ADD COMPLAINT</h1>
                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=manageCompJobCard"   method="Post" styleId="masterForm" onsubmit="return false" >
                            <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/masterAction.do?option=initManageJobCard'>MANAGE JOB CARD</a>@@ADD COMPLAINT"/>
                                <table width="95%" border="0" cellspacing="1" cellpadding="1">
                                    <tr>
                                        <td colspan="2">
                                            <table id="myTable" border="0" width="100%" >
                                                <tr bgcolor="#eeeeee" height="20">
                                                    <%--<td  nowrap align="center" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >S.No</td>--%>
                                                    <td   align="left" width="50" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Complaint Verbatim</td>
                                                    <td   align="left" width="120" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Select Aggregate</td>
                                                    <td   align="left" width="120" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"  nowrap>Select Defect Code</td>
                                                    <td   align="left" width="20" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>Select Cause Code</td>
                                                    <td   align="left" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap>Select Consequences</td>
                                                    <td   align="left" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" nowrap><img src='${contextPath}/image/plus.gif' onclick="addRowC('myTable')" class='statusicon'/></td>
                                                </tr>
                                                <tr>
                                                    <%--<td  nowrap align="center" width="25" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >1</td>--%>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                                        <input type="text" name="compVerbatim" id="compVerbatim1" class="text" maxlength="200"  style="width:120px"/>
                                                    </td>
                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                        <select name="aggCode"  class="headingSpas" style='width:170px' id="aggCode1" onchange="getCommonCode(this,'Agg_Code',1)">
                                                            <option value="">--Select Here--</option>
                                                            <c:forEach items="${aggregateList}" var="aggregateList">
                                                                <option value='${aggregateList.value}' title='${aggregateList.value}[ ${aggregateList.label}]'>${aggregateList.value}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <td  style="padding-left:6px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                        <div id="Agg_Code1">
                                                            <select name="compCode" class="headingSpas" style='width:170px' id="compCode1" onchange="getCommonCode(this,'Comp_Code',1)">
                                                                <option value="">--Select Here--</option>

                                                            </select>
                                                        </div>
                                                    </td>
                                                    <td  style="padding-left:6px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                        <div id="Comp_Code1">
                                                            <select name="causalCode" class="headingSpas" style='width:170px' id="causalCode1" onchange="getCommonCode(this,'Causal_Code',1)">
                                                                <option value="">--Select Here--</option>

                                                            </select>
                                                        </div>
                                                    </td>
                                                    <td  style="padding-left:6px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                        <div id="Causal_Code1">
                                                            <select name="consequenceCode" class="headingSpas" style='width:170px' id="consequenceCode1" >
                                                                <option value="">--Select Here--</option>

                                                            </select>
                                                        </div>
                                                    </td>
                                                    <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">&nbsp;
                                                        <%--<img src='${contextPath}/image/minus.gif' onclick="deleteRowC()" class='statusicon'/>--%>
                                                    </td>
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
