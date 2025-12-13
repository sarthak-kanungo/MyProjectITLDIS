<%--
    Document   : exportReorderLevel
    Created on : 23-Dec-2014, 15:46:47
    Author     : kundan.rastogi
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
     response.setHeader("Content-Disposition", "attachment; filename=ReOrderLevel.xls");
     Vector userFunctionalities = (Vector) session.getAttribute("userFun");
     LoginDAO dao = new LoginDAO();

     String dealerCode=(String) session.getAttribute("dealerCode");

     serviceForm sf = dao.getDealerAddress(dealerCode);
     String dealerName=sf.getDealerName()+" ["+sf.getLocationName()+"] ["+dealerCode+"]";

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
                <h3 class="hText"> <bean:message key="label.spare.viewReorderLevel" />
            <%--   <%if (userFunctionalities.contains("101")) {%>
                    for <%=dealerName%>
                    <% }%>--%></h3>
                <table id="data" width=100%  border=0 cellspacing=1  cellpadding=4 bordercolor=#cccccc   >
           

            <c:if test="${empty dataList}">
                <tr bgcolor="#FFFFFF">
                    <td valign="top" align="center" colspan="10" class="headingSpas" style="padding-top:10px;color: red">
                        <bean:message key="label.common.noRecordFound" />
                    </td>
                </tr>
            </c:if>


            <c:if test="${!empty dataList}">
                <tr>
                    <td >
                        <table width="100%" border=1 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor="#cccccc" >
                            <tr bgcolor="#eeeeee" height="20">
                                <td  align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.catalogue.sNo" />   </B> </td>
                                <td align="left" nowrap style="padding-left:5px;padding-right:5px;"><B> <bean:message key="label.common.partno_small" /></B></td>
                                <td align="left" nowrap style="padding-left:5px;padding-right:5px;"><B> <bean:message key="label.common.partdesc_small" /></B></td>
                                <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.spare.minQty" /> </B></td>
                                <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.spare.maxQty" /> </B></td>
                                <td align="center" nowrap style="padding-left:5px;padding-right:5px;"><B><bean:message key="label.spare.reorderQty" /></B></td>


                            </tr>
                            <c:set var="sno" value="1"></c:set>
                            <logic:iterate id="dataList" name="dataList">
                            
                                    <tr bgcolor="#FFFFFF">
                                        <td align="center" >${sno}</td>
                                        <td align="left" ><span>${dataList.partno}</span></td>
                                        <td align="left" ><span>${dataList.part_desc}</span></td>
                                        <td align="center" ><span id="MinLevel${sno}">${dataList.minLevel}</span></td>
                                        <td align="center" ><span id="MaxLevel${sno}">${dataList.maxLevel}</span></td>
                                        <td align="center" ><span id="ReorderLevel${sno}">${dataList.reorderLevel}</span></td>
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

