<%-- 
    Document   : exportLedgerReport
    Created on : Dec 2, 2014, 10:37:42 AM
    Author     : megha.pandya
--%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*,dao.LoginDAO,beans.serviceForm" %>
<%@page contentType="application/vnd.ms-excel" pageEncoding="UTF-8"%>

<%
     response.setHeader("Content-Disposition", "attachment; filename=LedgerReport.xls");
     Vector userFunctionalities = (Vector) session.getAttribute("userFun");
     LoginDAO dao = new LoginDAO();
     String dealerCode=(String) session.getAttribute("dealerCode");
     serviceForm sf = dao.getDealerAddress(dealerCode);
     String dealerName=sf.getDealerName()+" ["+sf.getLocationName()+"] ["+dealerCode+"]";
%>
<c:set var="dealer" value="<%=dealerName%>" />
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
                <h3> <bean:message key="label.common.stockReport" /> for
                    <%if (userFunctionalities.contains("101")) {%>
                    <%=dealerName%>
                    <%} else {%>
                    <c:forEach items="${dealerList}"  var="labelValue">
                        <c:if test="${inventoryForm.dealerCode eq labelValue[0]}">
                            ${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]
                        </c:if>
                    </c:forEach>
                    <%}%>
                    From ${inventoryForm.fromdate} To ${inventoryForm.todate}
                </h3>
              
            <table width=100%  border=1 cellspacing=1  cellpadding=4 bordercolor=#cccccc   >
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
                            <table width="100%" border=1 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor="#cccccc" >
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