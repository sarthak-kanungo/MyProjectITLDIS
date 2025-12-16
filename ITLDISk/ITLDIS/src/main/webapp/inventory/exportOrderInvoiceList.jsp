<%-- 
    Document   : exportOrderInvoiceList
    Created on : 02-Mar-2015, 16:12:02
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
            response.setHeader("Content-Disposition", "attachment; filename=OrderInvoiceList.xls");
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            LoginDAO dao = new LoginDAO();
            String dealerCode = (String) session.getAttribute("dealerCode");
         //   serviceForm sf = dao.getDealerAddress(dealerCode);
         //   String dealerName = sf.getDealerName() + " [" + sf.getLocationName() + "] [" + dealerCode + "]";
%>




<c:if test="${!empty viewOrderList}">
        <div style="width:99%; overflow:auto">
            <h3 class="hText" align="center"> <bean:message key="label.spare.orderInvoiceList" /></h3>
            <table width=100%  border=1 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                <tr bgcolor="#eeeeee" height="20">
                    <td   align="center"class="headingSpas" width="4%" nowrap style="padding-left:5px; "><b><bean:message key="label.warranty.S.No" /></b></td>
                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.common.InvoiceNo" /></b></td>
                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.InvoiceDate" /> </b></td>
                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.order.totalInvoiceAmount" /> </b></td>
                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.order.shippmentDate" /> </b></td>
                    <%--<td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.spare.noOfLines" /> </b></td>--%>
                    <%if (userFunctionalities.contains("101")) {%>
                    <%} else {%>
                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.common.dealerName" /></b></td>
                    <%}%>
                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b><bean:message key="label.order.lrNo" /></b></td>
                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.order.transporterName" /></b></td>
                    <td   align="left"  class="headingSpas" width="10%" nowrap style="padding-left:5px; "><b> <bean:message key="label.order.permitNo" /></b></td>
                </tr>
                <c:set var="sno" value="1"></c:set>
                <logic:iterate id="dataList" name="viewOrderList">

                        <tr  bgcolor="#eeeeee" height="20">
                            <td align="center" bgcolor="#FFFFFF" width="4%" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                            <td align="left" bgcolor="#FFFFFF" width="10%" title="${dataList.invNo}" width="150" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                <span>
                                     ${dataList.invNo} 
                                </span>
                            </td>
                            <td align="left" bgcolor="#FFFFFF" title="${dataList.invDate}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                <span>
                                    ${dataList.invDate}
                                </span>
                            </td>
                            <td align="left" bgcolor="#FFFFFF" title="${dataList.finalamount}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                <span>
                                    ${dataList.finalamount}
                                </span>
                            </td>

                            <td align="left" bgcolor="#FFFFFF" width="10%" title=" "  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                <span >
                                    ${dataList.shipmentDate}
                                </span>
                            </td>
                            <%if (userFunctionalities.contains("101")) {%>
                            <%} else {%>
                            <td align="left" bgcolor="#FFFFFF" width="10%" title=" ${dataList.dealerName}"  class="headingSpas" style="padding-left:5px; padding-right: 5px; white-space: nowrap;">
                                <span>
                                    ${dataList.dealerCode} [${dataList.dealerName}] [${dataList.location}]
                                </span>
                            </td>
                            <%}%>
                            <td align="left" bgcolor="#FFFFFF" width="10%" title=" ${dataList.lrNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                <span>
                                    ${dataList.lrNo}
                                </span>
                            </td>
                            <td align="left" bgcolor="#FFFFFF" width="10%" title=" ${dataList.transporterName}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                                ${dataList.transporterName}
                            </td>
                            <td align="left" bgcolor="#FFFFFF" width="10%" title=" ${dataList.permitNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">  <%--;white-space: nowrap;--%>
                                ${dataList.permitNo}
                            </td>
               </tr>
            <c:set var="sno" value="${sno + 1 }" />
                </logic:iterate>
          </table>
        </div>
</c:if>
