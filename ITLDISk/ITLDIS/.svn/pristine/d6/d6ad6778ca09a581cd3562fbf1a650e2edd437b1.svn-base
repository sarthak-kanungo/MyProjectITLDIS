

<!DOCTYPE html>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*" %>
<%@page contentType="application/vnd.ms-excel" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,dao.LoginDAO,beans.serviceForm" %>
<%
            response.setHeader("Content-Disposition", "attachment; filename=BackReport.xls");
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
                        <table width=100%  border=1 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                            <c:if test="${empty pendingList}">
                                <tr  bgcolor="#FFFFFF" height="20">
                                    <td colspan="5" valign="bottom" align="center" class="headingSpas" style="padding-top:10px;color: red">
                                        <h3><bean:message key="label.common.noRecordFound" /></h3>
                                    </td>
                                </tr>
                            </c:if>

                            <c:if test="${!empty pendingList}">
                                <tr>
                                    <td>
                                        <table width="100%" border=1 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor="#cccccc" >
                                            <tr bgcolor="#FFFFFF" >
                                                <td colspan="15" align="center" class="headingSpas" style="padding-bottom: 18px"><h3> View Back Order Report</h3> </td>
                                            </tr>
                                            <tr>
                                                <td colspan="15"> &nbsp; </td>
                                            </tr>
                                            <tr bgcolor="#eeeeee" height="20">
                                                <td  align="center" width="5%" nowrap style="padding-left:5px;padding-right:5px;" >
                                                    <bean:message key="label.common.sno" />
                                                </td>
                                                <td  align="left" nowrap style="padding-left:5px;padding-right:5px;" >
                                                    <bean:message key="label.common.dealerName" />
                                                </td>
                                                <td  align="left" nowrap style="padding-left:5px;padding-right:5px;">
                                                    <bean:message key="label.common.jobcardno" />
                                                </td>
                                                <td  align="left" nowrap style="padding-left:5px;padding-right:5px;">
                                                    <bean:message key="label.common.jobcardDate" />
                                                </td>
                                                <td  align="left" nowrap style="padding-left:5px;padding-right:5px;" >
                                                    <bean:message key="label.common.back.order.report.po.number" />
                                                </td>
                                                <td  align="left" nowrap style="padding-left:5px;padding-right:5px;" >
                                                    <bean:message key="label.common.back.order.report.date" />
                                                </td>
                                                <td  align="left" nowrap style="padding-left:5px;padding-right:5px;" >
                                                    <bean:message key="label.spare.orderType" />
                                                </td>
                                                <td  align="left" nowrap style="padding-left:5px;padding-right:5px;" >
                                                    <bean:message key="label.spare.orderStatus" />
                                                </td>
                                                <td  align="left" nowrap style="padding-left:5px;padding-right:5px;" >
                                                    <bean:message key="label.common.back.order.report.stockist.orderNumber" />
                                                </td>
                                                <td  align="left" nowrap style="padding-left:5px;padding-right:5px;" >
                                                    <bean:message key="label.common.partno_small" />
                                                </td>
                                                <td  align="left" nowrap style="padding-left:5px;padding-right:5px;" >
                                                    <bean:message key="label.common.partdesc_small" />
                                                </td>
                                                <td  align="center" nowrap style="padding-left:5px;padding-right:5px;" >
                                                    <bean:message key="label.catalogue.ordered.quantity" />
                                                </td>
                                                <td  align="center" nowrap style="padding-left:5px;padding-right:5px;" >
                                                    <bean:message key="label.catalogue.pending.quantity" />
                                                </td>
                                                <td  align="right" nowrap style="padding-left:5px;padding-right:5px;" >
                                                    <bean:message key="label.common.unitprice_small" />
                                                </td>
                                                <td  align="right" nowrap style="padding-left:5px;padding-right:5px;"  >
                                                    <bean:message key="label.common.amt_small" />
                                                </td>
                                            </tr>

                                            <c:set var="sno" value="1"></c:set>
                                            <logic:iterate id="pendingList" name="pendingList">
                                                <tr  height="20" bgcolor="#FFFFFF">
                                                    <td nowrap align="center" >
                                                        <span> ${sno}</span>
                                                    </td>
                                                    <td nowrap align="left" >
                                                        <span>${pendingList.dealerCode} [ ${pendingList.dealerName} ]</span>
                                                    </td>
                                                    <td nowrap align="left" >
                                                        <span>${pendingList.jobCardNo}</span>
                                                    </td>
                                                    <td nowrap align="left" >
                                                        <span>${pendingList.jobCardDate}</span>
                                                    </td>
                                                    <td nowrap align="left"  >
                                                        <span>${pendingList.refNo}</span>
                                                    </td>
                                                    <td nowrap align="left"  >
                                                        <span>${pendingList.customPoDate}</span>
                                                    </td>
                                                    <td nowrap align="left" >
                                                        <span>${pendingList.orderType}</span>
                                                    </td>
                                                    <td nowrap align="left" >
                                                        <span>${pendingList.status}</span>
                                                    </td>
                                                    <td nowrap align="left"  >
                                                        <span>${pendingList.erpOrderNo}</span>
                                                    </td>
                                                    <td nowrap align="left"  >
                                                        <span>${pendingList.partno}</span>
                                                    </td>
                                                    <td nowrap align="left" >
                                                        <span>${pendingList.part_desc}</span>
                                                    </td>
                                                    <td nowrap align="center"  >
                                                        <span>${pendingList.qty}</span>
                                                    </td>
                                                    <td nowrap align="center"  >
                                                        <span>${pendingList.erpPendingQty}</span>
                                                    </td>
                                                    <td nowrap align="right"  >
                                                        <span>${pendingList.price}</span>
                                                    </td>
                                                    <td nowrap align="right"  >
                                                        <span>${pendingList.finalamount}</span>
                                                    </td>
                                                    <c:set var="sno" value="${sno + 1 }" />
                                                </tr>
                                                <c:if test="${!empty pendingList.totalAmont}">
                                                    <tr  height="20" bgcolor="#eeeeee">
                                                        <td nowrap colspan="13" align="left"  >
                                                            &nbsp;
                                                        </td>
                                                        <td nowrap align="left"  >
                                                            <b><bean:message key="label.spares.pending.qty.total.amount"/></b>
                                                        </td>
                                                        <td nowrap align="right"  >
                                                            <b> <span>${pendingList.totalAmont}</span></b>
                                                        </td>

                                                    </tr>
                                                </c:if>
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