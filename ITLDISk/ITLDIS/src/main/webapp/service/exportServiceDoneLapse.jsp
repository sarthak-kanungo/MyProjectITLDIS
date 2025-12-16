<%-- 
    Document   : exportServiceDoneLapse
    Created on : 17-Dec-2014, 10:55:25
    Author     : kundan.rastogi
--%>

<!DOCTYPE html>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*,dao.LoginDAO,beans.serviceForm" %>
<%@page contentType="application/vnd.ms-excel" pageEncoding="UTF-8"%>


     
<%
     response.setHeader("Content-Disposition", "attachment; filename=ServiceDoneLapseReport.xls");
     Vector userFunctionalities = (Vector) session.getAttribute("userFun");
     LoginDAO dao = new LoginDAO();
     String dealerCode=(String) session.getAttribute("dealerCode");
     serviceForm sf = dao.getDealerAddress(dealerCode);
     String dealerName=sf.getDealerName()+" ["+sf.getLocationName()+"] ["+dealerCode+"]";
%>
 <center>
        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h3><bean:message key="label.common.serDoneLapseReprt" />
                 <%if (userFunctionalities.contains("101")) {%>
                    for <%=dealerName%>
                    <%} else {%>
                    <c:forEach items="${dealerList}"  var="labelValue">
                        <c:if test="${serviceForm.dealercode eq labelValue[0]}">
                            for ${labelValue[1]} [${labelValue[2]}] [${labelValue[0]}]
                        </c:if>
                    </c:forEach>
                    <%}%>
                    From ${serviceForm.fromdate} To ${serviceForm.todate}
                     </h3>
                       <table width=100%  border=1 cellspacing=1  cellpadding=4 bordercolor=#cccccc   >
                        <c:if test="${!empty sercviceReminderList}">
                        <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                               
                                    <table width=100%  border=1 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                        <tr bgcolor="#eeeeee" height="20">
                                            <td nowrap align="center" width="5%"  class="headingSpas" style="padding-left:3px;padding-right:3px;" >
                                               <b> <bean:message key="label.common.sno" /></b>
                                            </td>
                                            <td nowrap align="left" width="10%" class="headingSpas" style="padding-left:3px;padding-right:3px;" >
                                               <b> <bean:message key="label.common.Chassisno" /></b>
                                            </td>
                                            <td nowrap align="left" width="10%" class="headingSpas" style="padding-left:3px;padding-right:3px;" >
                                              <b>  <bean:message key="label.common.Modelcode" /></b>
                                            </td>
                                            <td nowrap align="left" width="10%" class="headingSpas" style="padding-left:3px;padding-right:3px;" >
                                               <b> <bean:message key="label.common.Modelcodedesc" /></b>
                                            </td>
                                            <td nowrap align="left" width="10%" class="headingSpas" style="padding-left:3px;padding-right:3px;" >
                                               <b> <bean:message key="label.common.Jobtype" /></b>
                                            </td>
                                            <td nowrap align="left" width="20%" class="headingSpas" style="padding-left:3px;padding-right:3px;" >
                                              <b>  <bean:message key="label.common.jobcardno" /></b>
                                            </td>
                                            <td nowrap align="left" width="10%" class="headingSpas" style="padding-left:3px;padding-right:3px;" >
                                                <b> <bean:message key="label.common.hrm" /></b>
                                            </td>
                                            <%if (userFunctionalities.contains("101")){%>
                                            <%} else {%>
                                            <td   align="left" width="10%" class="headingSpas" nowrap ><b><bean:message key="label.common.dealerName" /></b></td>
                                            <%}%>
                                        </tr>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="sercviceReminderList" name="sercviceReminderList">
                                           
                                                <tr  bgcolor="#eeeeee" height="20">
                                                    <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                    <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">
                                                        <%--<a href="<%=cntxpath%>/warrantyAction.do?option=packingDetails4View&packingNo=${packingList.packingNo}" >--%>
                                                        <span >${sercviceReminderList.vinNo}</span><%--</a>--%></td>
                                                    <td align="left" nowrap   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${sercviceReminderList.modelCode}</td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${sercviceReminderList.modelCodeDesc}</span></td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${sercviceReminderList.jobTypeDesc}</span></td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${sercviceReminderList.jobCardNo} [${sercviceReminderList.jobCardDate}]</span></td>
                                                    <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${sercviceReminderList.hmr}</span></td>
                                                   <%if (userFunctionalities.contains("101")){%>
                                                    <%} else {%>
                                                    <td align="left" bgcolor="#FFFFFF" title=" ${sercviceReminderList.dealerName}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span>
                                                           ${sercviceReminderList.dealercode} [${sercviceReminderList.dealerName}] [${sercviceReminderList.locationName}]
                                                        </span>
                                                    </td>
                                                    <%}%>
                                                </tr>
                                           
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        

                                    </table>
                               
                            </td>
                        </tr>
                    </c:if>
                        <c:if test="${empty sercviceReminderList}">
                            <tr  class="headingSpas" bgcolor="#FFFFFF" height="20">
                                <td valign="bottom" align="center" style="padding-top:10px;color: red"><bean:message key="label.common.noRecordFound" /></td>
                            </tr>
                        </c:if>
                       </table>
 </div>
        </div>
    </center>