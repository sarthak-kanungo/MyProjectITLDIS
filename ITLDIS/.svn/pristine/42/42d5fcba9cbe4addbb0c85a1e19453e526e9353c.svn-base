
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page contentType="application/vnd.ms-excel" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
            response.setHeader("Content-Disposition", "attachment; filename=PI_Order_for_Sap.xls");
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
                            <c:if test="${empty PiOrderForSAPList}">
                                <tr  bgcolor="#FFFFFF" height="20">
                                    <td colspan="5" valign="bottom" align="center" class="headingSpas" style="padding-top:10px;color: red">
                                        <h3><bean:message key="label.common.noRecordFound" /></h3>
                                    </td>
                                </tr>
                            </c:if>

                            <c:if test="${!empty PiOrderForSAPList}">
                                <tr>
                                    <td>
                                        <table width="100%" border=1 cellspacing=1  cellpadding=4 bordercolor=#cccccc bgcolor="#cccccc" >
                                           <%-- <tr bgcolor="#FFFFFF" >
                                                <td colspan="12" align="center" class="headingSpas" style="padding-bottom: 18px"><h3> View Report</h3> </td>
                                            </tr>  --%>
                                            <tr bgcolor="#eeeeee" height="20">
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px;font-size: 15" >
                                                    <bean:message key="label.spare.orderSNo" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15" >
                                                    <bean:message key="label.spare.quotType" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15" >
                                                    <bean:message key="label.spare.saleOrg" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15" >
                                                    <bean:message key="label.spare.disChannel" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15" >
                                                    <bean:message key="label.spare.divison" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15" >
                                                    <bean:message key="label.spare.soldToParty" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px">
                                                    <bean:message key="label.spare.shipToParty" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px" >
                                                    <bean:message key="label.spare.poPiNo" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px" >
                                                    <bean:message key="label.spare.PODate" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px" >
                                                    <bean:message key="label.spare.validTo" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px"  >
                                                    <bean:message key="label.spare.incoTerms1" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px" >
                                                    <bean:message key="label.spare.incoTerms2" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px"  >
                                                    <bean:message key="label.spare.paymentTerms" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px"  >
                                                    <bean:message key="label.common.modeOfDispatch" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px"  >
                                                    <bean:message key="label.spare.dischargePort" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.spare.portOfLoading" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px; font-size: 15px" >
                                                    <bean:message key="label.spare.destinationPlace" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px" >
                                                    <bean:message key="label.catalogue.partCode" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.catalogue.orderQuantity" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.spare.plant" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.spare.storageLoc" />
                                                </td>
                                                <td bgcolor="#cccccc" align="right" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.common.inspCharge" />
                                                </td>
                                                <td bgcolor="#cccccc" align="right" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.spare.localTransport" />
                                                </td>
                                                <td bgcolor="#cccccc" align="right" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.spare.insurance" />
                                                </td>
                                                <td bgcolor="#cccccc" align="right" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.spare.seaAirFreight" />
                                                </td>
                                                <td bgcolor="#cccccc" align="right" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.spare.othersCharges" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px" >
                                                    <bean:message key="label.spare.oRefNo" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px" >
                                                    <bean:message key="label.spare.tRefNo" />
                                                </td>
                                                <td bgcolor="#cccccc" align="right" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.spare.sapORefNo" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.spare.sapTRefNo" />
                                                </td>
                                                <td bgcolor="#cccccc" align="left" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px"  >
                                                    <bean:message key="label.spare.sapPartCode" />
                                                </td>
                                                <td bgcolor="#cccccc" align="center" nowrap style="padding-left:5px;padding-right:5px;font-size: 15px" >
                                                    <bean:message key="label.spare.sapQty" />
                                                </td>
                                            </tr>

                                            <c:set var="sno" value="1"></c:set>
                                            <logic:iterate id="PiOrderList" name="PiOrderForSAPList">
                                                <tr  height="20" bgcolor="#FFFFFF">                                                    
                                                    <td nowrap align="left">
                                                        <span>${PiOrderList.srNo}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${PiOrderList.quotType}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${PiOrderList.saleOrg}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${PiOrderList.disChannel}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${PiOrderList.divison}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${PiOrderList.soldToParty}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${PiOrderList.shipToParty}</span>
                                                    </td>
                                                    <td nowrap align="left">
                                                        <span>${PiOrderList.newPoNo}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.poDate}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.validTo}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.incoTerms1}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.incoTerms2}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.paymentTerms}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.dispatchMode}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.dischargePort}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.portOfLoading}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.finalDestCountry}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.partCode}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.orderQty}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.plant}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.storageLoc}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.inspCharge}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.localTPT}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.insurnceCharge}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.freightCharge}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.otherCharge}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.oRefNo}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.tRefNo}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.sapORefNo}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.sapTRefNo}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.sapPartCode}</span>
                                                    </td>
                                                    <td nowrap align="center">
                                                        <span>${PiOrderList.sapQty}</span>
                                                    </td>                                                                                                        
                                                </tr>                                              
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