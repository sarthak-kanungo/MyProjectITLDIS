<%-- 
    Document   : inventoryLedgerReport
    Created on : Dec 1, 2014, 12:58:19 PM
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
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*" %>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            String dealerFlag="false";
            if (userFunctionalities.contains("101")) {dealerFlag = "true";}
%>
<script>
   function search()
   {
       var dealerFlag='<%=dealerFlag%>';
       if(dealerFlag=="true"){
       }else{
           if(document.getElementById("Dealer Name").value=='')
           {
               alert(not_blank_dropdown_validation_msg+"Dealer Name")
               return false;
           }
       }
        if(document.getElementById("Range").checked==true){
                if(document.getElementById("From Date").value==""){
                alert("Please Select From Date .");
                document.getElementById("From Date").focus();
                return false;
                }
                if(document.getElementById("To Date").value==""){
                alert("Please Select To Date .");
                document.getElementById("To Date").focus();
                return false;
                }
            }

        var  fDate=document.getElementById("From Date").value;
        fDate = trim(fDate);
        var x =fDate;     //'1-1-2015';
        var a = x.split('/');
        var dateF = new Date (a[2], a[1] - 1,a[0]);

        var  tDate=document.getElementById("To Date").value;
        tDate = trim(tDate);
        var y =tDate;     //'17-1-2015';
        var b = y.split('/');
        var dateT = new Date (b[2], b[1] - 1,b[0]);

        //alert(dateF)
        //alert(dateT)
        if(dateF>dateT){
            alert(date_validation_msg)
            document.getElementById('From Date').focus();
            return false;
        }

       document.getElementById('etype').value="view";
       document.getElementById("searchform").submit();
   }
 
    function Export()
    {
      var dealerFlag='<%=dealerFlag%>';
       if(dealerFlag=="true"){
       }else{
           if(document.getElementById("Dealer Name").value=='')
           {
               alert(not_blank_dropdown_validation_msg+"Dealer Name")
               return false;
           }
       }
      document.getElementById('etype').value="export";
      document.getElementById('searchform').submit();
    }
    function disableDate(dis)
    {
        if(dis.checked){
            document.getElementById("From Date").disabled=false;
            document.getElementById("To Date").disabled=false;
        }else{
            document.getElementById("From Date").value="";
            document.getElementById("From Date").disabled=true;
            document.getElementById("To Date").value="";
            document.getElementById("To Date").disabled=true;
        }
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
        <ul class="hText">
            <li class="breadcrumb_link"><a href='${pageContext.request.contextPath}/inventoryAction.do?option=initInvOptions'><bean:message key="label.common.spares_small" /></a></li>

            <li class="breadcrumb_link"><label><bean:message key="label.common.ledgerReport" /></label></li>
      </ul>
    </div>
    <div class="message" id="msg_saveFAILED"></div>
    <center>
         <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1 class="hText"> <bean:message key="label.common.stockReport" /></h1>
                <form action="inventoryAction.do?option=inventoryLedgerReport&flag=check" onsubmit="" method="post" id="searchform">
                <input type="hidden" value="true" name="getDataFlag"/>
                <table id="data" width=100%  border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc   >
                <%if (userFunctionalities.contains("101")) {%>
                <tr bgcolor="#FFFFFF">
                    <td   class="headingSpas" nowrap align="right">
                        <c:if test="${range eq '1'}">
                            <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                        </c:if>
                        <c:if test="${range ne '1' }">
                            <input type="checkbox" name="range" value="1"  id="Range" >
                        </c:if>
                        <bean:message key="label.common.systransDate"/></td>
                    <td  align="left" style="width: 2%">
                        <input name="fromdate" type="text" class="datepicker" id="From Date"  value="${inventoryForm.fromdate}" onkeydown="return false;"/>
                    </td>
                    <td  class="headingSpas" nowrap align="center"><bean:message key="label.common.to"/></td>
                    <td  align="left" >
                        <input name="todate" type="text" class="datepicker" id="To Date" value="${inventoryForm.todate}"  onkeydown="return false;"/>
                    </td>
                    <td align="right" > <bean:message key="label.catalogue.partNo" />    </td>
                    <td align="left"><input type="text" id="Part No." name="partnum" style="width: 130px" value="${inventoryForm.partnum}"/></td>
                    <td align="left">
                        <input type="button" value="GO" onclick="search()"/>
                        <input type="button" value="Export" onclick="Export()"/> <input type="hidden" name="etype" id="etype" />
                    </td>
                </tr>
                <%} else{%>
                <tr bgcolor="#FFFFFF">
                    <td   class="headingSpas" nowrap align="right">
                        <c:if test="${range eq '1'}">
                            <input type="checkbox" name="range" value="1"  id="Range" checked onclick="disableDate(this)">
                        </c:if>
                        <c:if test="${range ne '1' }">
                            <input type="checkbox" name="range" value="1"  id="Range" >
                        </c:if>
                        <bean:message key="label.common.transDate"/></td>
                    <td  align="left" style="width: 2%">
                        <input name="fromdate" type="text" class="datepicker" id="From Date"  value="${inventoryForm.fromdate}" onkeydown="return false;"/>
                    </td>
                    <td  class="headingSpas" nowrap align="center"><bean:message key="label.common.to"/></td>
                    <td  align="left" >
                        <input name="todate" type="text" class="datepicker" id="To Date" value="${inventoryForm.todate}"  onkeydown="return false;"/>
                    </td>
                    <td align="right" > <bean:message key="label.catalogue.partNo" />    </td>
                    <td ><input type="text" id="Part No." name="partnum" style="width: 130px" value="${inventoryForm.partnum}"/></td>
                    <td align="right" > <bean:message key="label.common.dealerName" />    </td>
                    <td >
                        <select id="Dealer Name" name="dealerCode" style="width:300px !important">
                            <%--<option value='' >--Select Here--</option>--%>
                            <option value="ALL">ALL</option>
                            <c:forEach items="${dealerList}"  var="labelValue">
                                <c:if test="${inventoryForm.dealerCode eq labelValue[0]}">
                                    <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]" selected>${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                </c:if>
                                <c:if test="${inventoryForm.dealerCode ne labelValue[0]}">
                                    <option value="${labelValue[0]}" title="${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]">${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]</option>
                                </c:if>

                            </c:forEach>
                        </select>
                    </td>
                </tr>
                <%}%>
                </table>
                <%if (userFunctionalities.contains("101")) {%>
                 <%} else{%>
                <div style="float:right; margin-right: 10px;">
                 <input type="button" value="GO" onclick="search()"/>
                 <input type="button" value="Export" onclick="Export()"/> <input type="hidden" name="etype" id="etype" />
               </div>
                 <%}%>
            </form>

            <table width=100%  border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc   >
            <c:if test="${empty inventoryList}">
                <tr bgcolor="#FFFFFF">
                    <td valign="top" align="center" class="headingSpas" style="padding-top:10px;color: red">
                        <bean:message key="label.common.noRecordFound" />
                    </td>
                </tr>
            </c:if>


            <c:if test="${!empty inventoryList}">
                <tr>
                    <td>
                        <pg:pager url="inventoryAction.do" maxIndexPages="10" maxPageItems="15">
                            <pg:param name="option" value="inventoryLedgerReport"/>
                            <pg:param name="getDataFlag" value="true"/>
                            <pg:param name="partnum" value="${inventoryForm.partnum}"/>
                            <pg:param name="dealerCode" value="${inventoryForm.dealerCode}"/>
                            <pg:param name="fromdate" value="${inventoryForm.fromdate}"/>
                            <pg:param name="todate" value="${inventoryForm.todate}"/>
                             <pg:param name="flag"  value='check'/>
                             <c:if test="${range eq '1'}"><pg:param name="range"  value='1'/> </c:if>
                             <table width="100%" border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor="#cccccc" >
                                <tr bgcolor="#eeeeee" height="20">
                                    <td  align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.catalogue.sNo" /></B> </td>
                                    <td align="left" nowrap style="padding-left:5px;padding-right:5px;"><B> <bean:message key="label.common.partno_small" /></B></td>
                                    <td  align="left" nowrap style="padding-left:5px;padding-right:5px;"><B> <bean:message key="label.common.partdesc_small" /></B></td>
                                    <td  align="left" nowrap style="padding-left:5px;padding-right:5px;"><B> <bean:message key="label.common.transDesc" /></B></td>
                                    <td  align="left" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.common.systransDate" /> </B></td>
                                    <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.common.opening" /></B></td>
                                    <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.common.inWard" /></B></td>
                                    <td  align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.common.outWard" /></B></td>
                                    <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.common.closing" /></B></td>
                                </tr>
                                <c:set var="sno" value="1"></c:set>
                                <logic:iterate id="invlist" name="inventoryList">
                                    <pg:item>
                                        <tr bgcolor="#FFFFFF">
                                            <td align="center" >${sno}</td>
                                            <td align="left" ><span>${invlist.partno}</span></td>
                                            <td align="left" ><span>${invlist.part_desc}</span></td>
                                            <td align="left" ><span>${invlist.transdate_desc}</span></td>
                                            <td align="left" ><span>${invlist.transdate}</span></td>
                                            <td align="center" ><span>${invlist.openingStock}</span></td>
                                            <td align="center" ><span>${invlist.inWard_qty}</span></td>
                                            <td align="center" ><span>${invlist.outWard_qty}</span></td>
                                            <td align="center" ><span>${invlist.closingStock}</span></td>
                                        </tr>
                                    </pg:item>
                                    <c:set var="sno" value="${sno + 1 }" />
                                </logic:iterate>
                                <tr>
                                    <td colspan="9" align="center" bgcolor="#FFFFFF" class="textPaging" >
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
