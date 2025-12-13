<%-- 
    Document   : exportInventory
    Created on : Nov 19, 2014, 12:39:04 PM
    Author     : megha.pandya
--%>

<%--<%@page contentType="text/html" pageEncoding="UTF-8"%>--%>
<!DOCTYPE html>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*,java.text.*" %>
<%@page contentType="application/vnd.ms-excel" pageEncoding="UTF-8"%>

<%
     response.setHeader("Content-Disposition", "attachment; filename=Inventory.xls");
     Vector userFunctionalities = (Vector) session.getAttribute("userFun");
     java.util.Date dt = new java.util.Date();

     SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
%>


<html lang="en">
    <head>  <meta charset="utf-8">
        <title>DIS</title>
        <script src="JS/Master_290414.js"></script>
        <link rel="stylesheet" href="CSS/Master_290414.css" type="text/css" >
    </head>
    <body>
  <div class="contentmain1">
    
   
    <center>
         <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1> <bean:message key="label.common.viewInventory" /> as on <%=sdf.format(dt)%></h1>
            <table id="data" width=100%  border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc   >

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
                            <table width="100%" border=1 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor="#cccccc" >
                                <tr bgcolor="#eeeeee" height="20">
                                    <td  align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.catalogue.sNo" />   </B> </td>
                                    <%if (userFunctionalities.contains("101")) {%>
                                    <%} else {%>
                                    <td nowrap align="left" nowrap style="padding-left:5px;padding-right:5px;"><B>Dealer Code</B></td>
                                    <%}%>
                                    <td  align="left" nowrap style="padding-left:5px;padding-right:5px;"><B> <bean:message key="label.common.partno_small" /></B></td>
                                    <td  align="left" nowrap style="padding-left:5px;padding-right:5px;"><B> <bean:message key="label.common.partdesc_small" /></B></td>
                                    <td  align="left" nowrap style="padding-left:5px;padding-right:5px;"><B> <bean:message key="label.common.binLocation" /></B></td>
                                    <td  align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.common.qty_small" /> </B></td>
                                    <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.common.wipQty" /> </B></td>
                                    <td  align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.common.currentQty" /> </B></td>
                                    <td  align="right" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.catalogue.mrp" /> </B></td>
                                    <td  align="right" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.common.amt_small" /> </B></td>
                                </tr>
                                <c:set var="sno" value="1"></c:set>
                                <logic:iterate id="invlist" name="inventoryList">
                                   
                                        <tr bgcolor="#FFFFFF">
                                            <td align="center" >${sno}</td>
                                            <%if (userFunctionalities.contains("101")) {%>
                                            <%} else {%>
                                            <td align="left" ><span>${invlist.dealerCode}</span></td>
                                            <%}%>
                                            <td align="left" ><span>${invlist.partno}</span></td>
                                            <td align="left" ><span>${invlist.part_desc}</span></td>
                                            <td align="left" ><span id="Bin Location${sno}">${invlist.binLocation}</span></td>
                                            <td align="center" ><span>${invlist.qty}</span></td>
                                            <td align="center" ><span>${invlist.wip_qty}</span></td>
                                            <td align="center" ><span>${invlist.total_qty}</span></td>
                                            <td align="right" ><span>${invlist.mrp}</span></td>
                                            <td align="right" ><span>${invlist.finalamount}</span></td>
                                        </tr>
                                   
                                    <c:set var="sno" value="${sno + 1 }" />
                                </logic:iterate>
                                
                            </table>
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