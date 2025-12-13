<%-- 
    Document   : viewInventory
    Created on : Aug 25, 2014, 12:51:25 PM
    Author     : sreeja.vijayakumaran
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<script>
   function search()
   {
       <%--document.getElementById("searchform").action="<%=cntxpath%>/inventoryAction.do?option=viewInventory";--%>
       document.getElementById('etype').value="view";
       document.getElementById("searchform").submit();
   }
    function editRow(partNo, row,binLocation){

        document.getElementById('Bin Location'+row).innerHTML="<input type=\"text\" name=\"binLocation\"  maxlength=\"75\" class=\"headingSpas\" id=\"newBinLocation"+row+"\" style=\"width:220px\" value=\""+binLocation+"\"/>";
        document.getElementById('imageButton'+row).innerHTML="<input type=button name=\"apply\" class=\"headingSpas\" value=\"Apply\" onclick=\"updateAction('edit', "+row+",'"+partNo+"','"+binLocation+"')\"/>";
    }

    function updateAction(str, row,partNo,binLocation)
    {

        if(document.getElementById("newBinLocation"+row).value==""){
            document.getElementById("newBinLocation"+row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML=not_blank_validation_msg+"Bin Location";
            return false;
        }

        var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['\*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
        var chckValue = document.getElementById("newBinLocation"+row).value;
        chckValue=chckValue.replace(objRegExp,'');
        var temp=objSpecExp.exec(chckValue);
        if(temp)
        {
            document.getElementById("newBinLocation"+row).focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            document.getElementById("msg_saveFAILED").innerHTML=specialChar_validation_msg+"Bin Location";
            window.scrollTo(0,0);
            return false;
        }

        var bin_Location = TrimAll(document.getElementById("newBinLocation"+row).value);
        var url="<%=cntxpath%>/inventoryAction.do?option=updateBinLocation&partno="+partNo+"&binLocation="+bin_Location+"&type="+str+"&t="+new Date().getTime();
        <%--alert(url)--%>
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
                { res = trim(xmlHttp.responseText);
                    if(res =='Success'){
                        document.getElementById("msg_saveFAILED").innerHTML=binlocsuccess_validation_msg+partNo;
                        document.getElementById('Bin Location'+row).innerHTML=""+bin_Location+"";
                        document.getElementById('imageButton'+row).innerHTML="<img src=\"images/edit.gif\" onclick=\"editRow('"+partNo+"',"+row+",'"+bin_Location+"')\" title=\"Edit\"/>";
                    }
                    else{
                        document.getElementById("msg_saveFAILED").innerHTML=binlocfailure_validation_msg;
                    }
                }
            }
        };
        xmlHttp.open("GET",url, true);
        xmlHttp.send(null);
        return false;
    }
    function Export()
    {
         document.getElementById('etype').value="export";
         document.getElementById('searchform').submit();
    }
</script>
<html lang="en">
    <head>  <meta charset="utf-8">
        <title>DIS</title>
        <script src="JS/Master_290414.js"></script>
        <link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >
    </head>
    <body>
  <div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares" /></a></li>

            <li class="breadcrumb_link"><label><bean:message key="label.common.viewInventory" /></label></li>
      </ul>
    </div>
    <div class="message" id="msg_saveFAILED"></div>
    <center>
         <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1> <bean:message key="label.common.viewInventory" /></h1>


       
                <table id="data" width=100%  border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc   >
            <form action="inventoryAction.do?option=viewInventory" onsubmit="" method="post" id="searchform">

                <tr bgcolor="#FFFFFF">
                    <td align="right" style="padding-left: 5px" width="80px"> <bean:message key="label.catalogue.partNo" />    </td>
                    <td width="270px" align="left"><input type="text" name="partnum" style="width: 250px" value="${inventoryForm.partnum}"/></td>
                <td align="left"><input type="button" value="GO" onclick="search()"/>
                 <input type="button" value="Export" onclick="Export()"/> <input type="hidden" name="etype" id="etype" />
                </td>
            </tr>

            </form>


            <c:if test="${empty inventoryList}">
                <tr bgcolor="#FFFFFF">
                    <td valign="top" align="center" colspan="10" class="headingSpas" style="padding-top:10px;color: red">
                        <bean:message key="label.common.noRecordFound" />
                    </td>
                </tr>
            </c:if>


            <c:if test="${!empty inventoryList}">
                <tr>
                    <td colspan="3">
                        <pg:pager url="inventoryAction.do" maxIndexPages="10" maxPageItems="15">
                            <pg:param name="option" value="viewInventory"/>
                            <pg:param name="partnum" value="${inventoryForm.partnum}"/>
                            <table width="100%" border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor="#cccccc" >
                                <tr bgcolor="#eeeeee" height="20">
                                    <td  align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.catalogue.sNo" />   </B> </td>
                                    <td align="left" nowrap style="padding-left:5px;padding-right:5px;"><B> <bean:message key="label.common.partno_small" /></B></td>
                                    <td align="left" nowrap style="padding-left:5px;padding-right:5px;"><B> <bean:message key="label.common.partdesc_small" /></B></td>
                                    <td align="left" nowrap style="padding-left:5px;padding-right:5px;"><B> <bean:message key="label.common.binLocation" /></B></td>
                                    <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.common.qty_small" /> </B></td>
                                    <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.common.wipQty" /> </B></td>
                                    <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.common.currentQty" /> </B></td>
                                    <td align="right" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.catalogue.mrp" /> </B></td>
                                    <td align="right" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.common.amt_small" /> </B></td>
                                    <%if (userFunctionalities.contains("807")){%>
                                    <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.common.action" /> </B></td>
                                    <%}%>
                                </tr>
                                <c:set var="sno" value="1"></c:set>
                                <logic:iterate id="invlist" name="inventoryList">
                                    <pg:item>
                                        <tr bgcolor="#FFFFFF">
                                            <td align="center" >${sno}</td>
                                            <td align="left" ><span>${invlist.partno}</span></td>
                                            <td align="left" ><span>${invlist.part_desc}</span></td>
                                            <td align="left" ><span id="Bin Location${sno}">${invlist.binLocation}</span></td>
                                            <td align="center" ><span>${invlist.qty}</span></td>
                                            <td align="center" ><span>${invlist.wip_qty}</span></td>
                                            <td align="center" ><span>${invlist.total_qty}</span></td>
                                            <td align="right" ><span>${invlist.mrp}</span></td>
                                            <fmt:parseNumber var="finalAmount" type="number" value="${invlist.finalamount}" />
                                            <td align="right" ><span><c:out value="${finalAmount}"/></span></td>
                                            <%if (userFunctionalities.contains("807")){%>
                                            <td align="center">
                                                <span id ="imageButton${sno}" >
                                                <img alt="" src="${pageContext.request.contextPath}/images/edit.gif" onclick="editRow('${invlist.partno}', ${sno}, '${invlist.binLocation}')" title="Edit"/>
                                                </span>
                                            </td> <%}%>
                                        </tr>
                                    </pg:item>
                                    <c:set var="sno" value="${sno + 1 }" />
                                </logic:iterate>
                                <tr>
                                    <%if (userFunctionalities.contains("807")){%>
                                    <td colspan="10" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                    <% } else { %>
                                    <td colspan="9" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                     <%}%>
                                        <table  align="center" cellspacing="0" cellpadding="0">
                                            <tr>
                                                <td>
                                                    <pg:index>
                                                        <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle" style="float:left;"/></a></pg:first>&nbsp;
                                                        <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle" style="float:left;"/></a></pg:prev>&nbsp;
                                                        <pg:pages>&nbsp;<a style="color: blue;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                                        <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle" style="float:right"/></a></pg:next>&nbsp;
                                                        <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle" style="float:right"/></a></pg:last>
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
        </table>
    
            </div>
         </div>
    </center>
  </div>
</body>
</html>