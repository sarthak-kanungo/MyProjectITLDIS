<%-- 
    Document   : pdi_ins_paramVsmake
    Created on : Oct 31, 2014, 3:00:26 PM
    Author     : megha.pandya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
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
<c:set var="param" value='<%=request.getParameter("param")%>'/>
<script language="JavaScript" type="text/javascript">

    function submitForm(){
        document.getElementById('searchBy').submit();
    }


    function validNumber(obj) {
        var regExp = /^[0-9.]+$/g;
        if (regExp.test(obj)) {
            return true;
        } else {
            return false;
        }
    }

    function validSpclChar(obj) {
        var regExp =/^[a-zA-Z0-9-_.\"\"\&\(\)\/ ]*$/g;
        if (regExp.test(obj)) {
            return true;
        } else {
            return false;
        }
    }

    function cUDAction(str, row,partName,make)
    {
        if(str == 'add'){
            var elementArr=new Array('Part Name','Make');
            var strValue=null;
            var strObject=null;
            for(var i=0;i<elementArr.length;i++)
            {
                strObject=document.getElementById(elementArr[i]);
                strValue =document.getElementById(elementArr[i]).value;
                if(strValue=="")
                {
                    document.getElementById(elementArr[i]).focus();
                    alert(not_blank_validation_msg+elementArr[i])
                    return false;
                }
                if(validSpclChar(strValue))
                {}
                else{
                    document.getElementById(elementArr[i]).focus();
                    alert(specialChar_validation_msg+elementArr[i])
                    return false;
                }
            }
            
            document.getElementById("pdipartVsMake").submit();

        }else{

            var url="<%=cntxpath%>/masterAction.do?option=managepdi_ins_parameterVsmake&type="+str+"&part_Desc="+partName+"&id="+make+"&status="+str+"&t="+new Date().getTime();
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
                        document.getElementById("msg_saveFAILED").innerHTML=res.split("@@")[1];
                        if (str != 'add' && res.split("@@")[0] == 'Success') {
                            if (str == 'Y') {
                                document.getElementById('Status' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('N','" + row + "','" + partName + "','"+make+"')\"  class=\"headingSpas\" style=\"color: blue;\" >N</a>";
                            } else {
                                document.getElementById('Status' + row).innerHTML = "<a href=\"javascript:void(0)\" onclick=\"cUDAction('Y','" + row + "','" + partName + "','"+make+"')\"  class=\"headingSpas\" style=\"color: blue;\" >Y</a>";
                            }
                        }

                    }
                }
            };
            xmlHttp.open("GET",url, true);
            xmlHttp.send(null);
            return false;
        }
    }

    function editRow(row,partName,make){

        document.getElementById('Part Name'+row).innerHTML="<input type=\"text\" name=\"newpartName\" maxlength='45' class=\"headingSpas\"  id=\"newpartName"+row+"\" style=\"width:65px\" value=\""+partName+"\"/>";

        document.getElementById('Make'+row).innerHTML="<input type=\"text\" name=\"newmake\" maxlength='50' class=\"headingSpas\"  id=\"newmake"+row+"\" style=\"width:65px\" value=\""+make+"\"/>";

        document.getElementById('imageButton'+row).innerHTML="<input type=button name=\"apply\" class=\"headingSpas\" value=\"Apply\" onclick=\"updateAction('edit', "+row+",'"+partName+"','"+make+"')\"/>";
    }

    function updateAction(str, row,partName,make)
    {

        var elementArr=new Array('newpartName'+row,'newmake'+row);
        var strValue=null;
        var strObject=null;
        for(var i=0;i<elementArr.length;i++)
        {
            strObject=document.getElementById(elementArr[i]);
            strValue =document.getElementById(elementArr[i]).value;
            if(strValue=="")
            {
                document.getElementById(elementArr[i]).focus();
                if(elementArr[i].contains("newpartName"))
                {
                    alert(not_blank_dropdown_validation_msg+'Part Name')
                }
                else{
                    alert(not_blank_dropdown_validation_msg+'Make')
                }

                return false;
            }
            if(validSpclChar(strValue))
            {}
            else{
                document.getElementById(elementArr[i]).focus();
                if(elementArr[i].contains("newpartName"))
                {
                    alert(specialChar_validation_msg+'Part Name')
                }
                else{
                    alert(specialChar_validation_msg+'Make')
                }
                return false;
            }
        }
        
        var partName=document.getElementById("newpartName"+row).value;
        var make=document.getElementById("newmake"+row).value;
        var id=document.getElementById("id"+row).value;
        var url="<%=cntxpath%>/masterAction.do?option=managepdi_ins_parameterVsmake&type="+str+"&status="+str+"&part_Desc="+partName+"&make="+make+"&id="+id+"&t="+new Date().getTime();

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
                    document.getElementById("msg_saveFAILED").innerHTML=res.split("@@")[1];
                    if(res.split("@@")[0] =='Success'){
                            document.getElementById('Part Name'+row).innerHTML=""+partName;
                            document.getElementById('Make'+row).innerHTML=""+make;
                            document.getElementById('imageButton'+row).innerHTML="<img src=\"images/edit.gif\" onclick=\"editRow("+row+",'"+partName+"','"+make+"')\" title=\"Edit\"/>";

                        }
                    }
                }
            };
            xmlHttp.open("GET",url, true);
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
        <ul class="hText">
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a>Manage PDI/INS Parameter Vs Make</li>
        </ul>
    </div>

    <div class="message" id="msg_saveFAILED"><html:errors/></div>
    <center>

        <div class="content_right1">

            <div class="con_slidediv1" style="position: relative;width:100%">

                <h1 class="hText">Manage PDI/INS Parameter Vs Make</h1>

                <table cellspacing=1 cellpadding=5 border=0 width=100%>

                    <tr height=30 bgcolor="#FFFFFF">
                        <td align= center class="headingSpas">
                            <html:form action="masterAction.do?option=initpdi_ins_parameterVsmake" method="post" styleId="searchBy">

                                <table width=100%  border=0 cellspacing=0 cellpadding=0 bgcolor=#cccccc>
                                    <tr bgcolor="#FFFFFF">
                                        <td  style="padding-left:10px" width="10%" class="headingSpas" nowrap align="right">Part Name</td>
                                        <td style="padding-left:10px" width="10%" class="headingSpas" align="left">
                                            <input name="partNo" id="Part Name Search" class="headingSpas" value="${masterForm.partNo}"/>
                                        </td>
                                        <td  style="padding-left:10px" width="10%" class="headingSpas" nowrap align="right">Make</td>
                                        <td style="padding-left:10px" width="10%" class="headingSpas" align="left">
                                            <input name="makeShow" id="make" class="headingSpas" value="${masterForm.makeShow}"/>
                                        </td>
                                        <td style="padding-left:10px" align="left" class="headingSpas" width="60%" >
                                            <input type="submit" name="Go" value="Search" class="headingSpas1" />
                                        </td>
                                    </tr>
                                </table>
                            </html:form>
                        </td></tr>
                        <c:if test="${!empty pdi_insparamVsmakeList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <pg:pager url="masterAction.do" maxIndexPages="10" maxPageItems="10">
                                    <pg:param name="option" value="initpdi_ins_parameterVsmake"/>
                                    <pg:param name="partNo"  value='${masterForm.partNo}'/>
                                    <pg:param name="makeShow"  value='${masterForm.makeShow}'/>
                                    <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr  bgcolor="#eeeeee" height="20">
                                            <td   align="center" width="5%" class="headingSpas" nowrap ><b>S.No</b></td>
                                            <td   align="left" width="40%"  class="headingSpas" nowrap><b>Part Name</b></td>
                                            <td   align="left" width="30%" class="headingSpas" nowrap><b>Make</b></td>
                                            <td   align="center" width="10%" class="headingSpas" nowrap><b>Is Active</b></td>
                                            <td   align="center" width="10%" class="headingSpas" nowrap><b>Edit</b></td>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="pdi_insparamVsmakeList" name="pdi_insparamVsmakeList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20">
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>

                                                    <td align="left" bgcolor="#FFFFFF" width="200" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <input type="hidden" name="id" id="id${sno}" value="${pdi_insparamVsmakeList.id}"/>
                                                        <span id ="Part Name${sno}" >
                                                            ${pdi_insparamVsmakeList.part_Desc}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" width="40" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="Make${sno}" >
                                                            ${pdi_insparamVsmakeList.make}
                                                        </span>
                                                    </td>
                                                    <td align="center" bgcolor="#FFFFFF" width="35" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="Status${sno}" >
                                                             <a href="javascript:void(0)" onclick="cUDAction('${pdi_insparamVsmakeList.status}', ${sno}, '${pdi_insparamVsmakeList.part_Desc}','${pdi_insparamVsmakeList.id}');"  class="headingSpas" style="color: blue;" >
                                                                ${pdi_insparamVsmakeList.status}&nbsp;
                                                             </a>
                                                        </span>
                                                    </td>

                                                    <td align="center" bgcolor="#FFFFFF" width="20" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="imageButton${sno}" >
                                                            <img src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow(${sno},'${pdi_insparamVsmakeList.part_Desc}','${pdi_insparamVsmakeList.make}')" title="Edit"/>
                                                        </span>
                                                    </td>
                                                </tr>
                                            </pg:item>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        <tr>
                                            <td colspan="5" align="center" bgcolor="#FFFFFF" class="textPaging" >

                                                <table  align="center" cellspacing="0" cellpadding="0">
                                                    <tr>
                                                        <td>
                                                            <pg:index>
                                                                <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle" style="float:left;"/></a></pg:first>&nbsp;
                                                                <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle" style="float:left;"/></a></pg:prev>&nbsp;
                                                                <pg:pages>&nbsp;<a style="color: blue!important;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                                                <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle" style="float:right"/></a></pg:last>
                                                                <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle" style="float:right;"/></a></pg:next>&nbsp;
                                                                </pg:index>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                </pg:pager>
                            </td>
                        </tr>
                    </c:if>
                    <c:if test="${empty pdi_insparamVsmakeList}">
                        <tr bgcolor="#FFFFFF">
                            <td align="center" valign="top" class="headingSpas" style="padding-top:10px;color: red">
                                PDI/INS Parameter Vs Make Not Found. !
                            </td>
                        </tr>
                    </c:if>
                    <tr bgcolor="#FFFFFF">
                        <td width=100% valign="top">
                            <form action="masterAction.do?option=managepdi_ins_parameterVsmake" id="pdipartVsMake" name="pdipartVsMake" method="post">
                                <input type="hidden" name="type" value="add" />
                                <table width=100% bgcolor=#000000 border="0" cellpadding="0" cellspacing="0" >
                                    <tr   bgcolor="#eeeeee" height="20">
                                        <td align= left class="headingSpas" style="padding-left: 5px;">
                                            <b>ADD NEW PDI/INS Parameter Vs Make</b>
                                        </td>

                                    <tr>
                                        <td width="100%" valign="top">
                                            <table id="myTable" width="100%" border="0" cellpadding="0" cellspacing="0" >
                                                <tr height="30" bgcolor="#FFFFFF">
                                                    <td style="padding-left:10px" width="20%" align="right">Part Name</td>
                                                    <td style="padding-left:10px" width="80%" align="left">
                                                        <input name="part_Desc" id="Part Name" class="headingSpas" value="" maxlength="45"/>
                                                    </td>
                                                </tr>
                                                <tr height="30" bgcolor="#FFFFFF">

                                                    <td style="padding-left:10px" width="20%" align="right">Make</td>
                                                    <td style="padding-left:10px" width="80%" align="left">
                                                        <input name="make" id="Make" class="headingSpas" value="" maxlength="50"/>
                                                    </td>
                                                </tr>
                                                <tr height="30" bgcolor="#FFFFFF">
                                                    <td style="padding-left:10px" width="20%" align="right">
                                                    </td>
                                                    <td  width="80%" style="padding-left:10px;padding-bottom: 5px" align="left" >
                                                        <input type=button name="add" class="headingSpas" value="Submit" onclick="cUDAction('add','','','')"/>
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
