<%-- 
    Document   : dmis_spareLubes_Report1
    Created on : Jul 27, 2015, 2:43:28 PM
    Author     : Ashutosh.Kumar1
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h3 class="hText"><bean:message key="label.report.misfordealer" />&nbsp;for &nbsp;<bean:write name="reportForm" property="dealerNameWithCode" /> &nbsp;Report From <bean:write name="reportForm" property="fromDate" />&nbsp;to&nbsp;<bean:write name="reportForm" property="toDate" /> </h3>
               
        <table width="100%" border=1 cellspacing=0  cellpadding=4 bordercolor=#cccccc  >

            <tr bgcolor="#ddddee" height="20">
                <td class="headingSpas"  rowspan="3" align="center" ><b>Spares & Lubes</b></td>
                <td class="headingSpas"  align="center"  colspan="6"><b> Parts & Lubs Inventory (Lac  Rs.)</b></td>
                <td class="headingSpas"  align="center"  colspan="12"><b>Order Execution</b></td>
            </tr>
            <tr bgcolor="#ddddee" height="20">
                <td class="headingSpas"  rowspan="2" align="center" ><b>Opening Stock</b></td>
                <td class="headingSpas" align="center"  colspan="2"><b>Parts & Lubes Purchased</b></td>
                <td class="headingSpas" align="center"  colspan="2"><b>Parts & Lubes Sale</b></td>
                <td class="headingSpas" rowspan="2" align="center" ><b>Closing Stock</b></td>
                <td class="headingSpas"  colspan="3" align="center" ><b>Order Placed</b></td>
                <td class="headingSpas"  colspan="3" align="center" ><b>Order Executed</b></td>
                <td class="headingSpas"  colspan="3" align="center" ><b>% of Execution</b></td>
                <td class="headingSpas"  colspan="3" align="center" ><b>Pending Orders</b></td>
            </tr>
            <tr bgcolor="#ddddee" height="20">
                <td class="headingSpas"  align="center" ><b>Target</b></td>
                <td class="headingSpas"  align="center" ><b>Actual</b></td>
                <td class="headingSpas" wrap align="center" ><b>Counter (To Direct Customer Only)</b></td>
                <td class="headingSpas"  align="center" ><b>Workshop (thru JOB Card)</b></td>
                <td class="headingSpas"  align="center" ><b>Value</b></td>
                <td class="headingSpas"  align="center" ><b>No. of Part Lines</b></td>
                <td class="headingSpas"  align="center" ><b>Total No. of Parts</b></td>
                <td class="headingSpas"  align="center" ><b>Value</b></td>
                <td class="headingSpas"  align="center" ><b>No. of Part Lines</b></td>
                <td class="headingSpas"  align="center" ><b>Total No. of Parts</b></td>
                <td class="headingSpas"  align="center" ><b>Value</b></td>
                <td class="headingSpas"  align="center" ><b>No. of Part Lines</b></td>
                <td class="headingSpas"  align="center" ><b>Total No. of Parts</b></td>
                <td class="headingSpas"  align="center" ><b>Value</b></td>
                <td class="headingSpas"  align="center" ><b>No. of Part Lines</b></td>
                <td class="headingSpas"  align="center" ><b>Total No. of Parts</b></td>
            </tr>

            <logic:iterate id="allLubesList" name="reportForm" property="listOfLubes">
                <tr>
                    <logic:iterate id="allValues" name="allLubesList">
                        <td align="center">
                            <bean:write name="allValues" />
                        </td>
                    </logic:iterate>
                </tr>
            </logic:iterate>
            <tr bgcolor="#ffffff" height="20">
            </tr>
            <tr bgcolor="#ddddee" height="20">
                <td class="headingSpas" nowrap rowspan="2" align="center" ><b>Workshop</b></td>
                <td class="headingSpas" nowrap align="center"  colspan="<bean:write name="reportForm" property="noOfColumnOfJobCard" />"><b>No. OF JOB Cards (Tractor Attended)</b></td>
                <td class="headingSpas" nowrap align="center"  colspan="<bean:write name="reportForm" property="noOfColumnOfJobCard" />"><b>Labour Earned</b></td>
            </tr>
            <tr bgcolor="#ddddee" height="20">
                <logic:iterate id="jobCardcolumnHeaderName" name="reportForm" property="jobCardColumnName">
                    <td align="center">
                        <b><bean:write name="jobCardcolumnHeaderName" /></b>
                    </td>
                </logic:iterate>
                <td class="headingSpas" nowrap align="center">
                    <b>Total</b>
                </td>
                <logic:iterate id="jobCardcolumnHeaderName" name="reportForm" property="jobCardColumnName">
                    <td align="center">
                        <b><bean:write name="jobCardcolumnHeaderName" /></b>
                    </td>
                </logic:iterate>
                <td class="headingSpas" nowrap align="center">
                    <b>Total</b>
                </td>
            </tr>

            <tr>
                <td class="headingSpas" bgcolor="#ddddee" nowrap align="center">MTD (CUMM.)</td>
                <logic:iterate id="jobCardcountOfMonth" name="reportForm" property="jobCardMonthCount">
                    <td align="center">
                        <bean:write name="jobCardcountOfMonth" />
                    </td>
                </logic:iterate>
                <td align="center">
                    <bean:write name="reportForm" property="totalmonthCount_jobCard"/>
                </td>

                <logic:iterate id="jobCardcountOfMonthLabour" name="reportForm" property="jobCardMonthLabour">
                    <td align="center">
                        <bean:write name="jobCardcountOfMonthLabour" />
                    </td>
                </logic:iterate>
                <td align="center">
                    <bean:write name="reportForm" property="totalmonthCount_jobCard_labour"/>
                </td>
            </tr>

            <tr>
                <td class="headingSpas" bgcolor="#ddddee" nowrap align="center">YTM  (CUMM.)</td>
                <logic:iterate id="jobCardcountOfYear" name="reportForm" property="jobCadYearCount">
                    <td align="center">
                        <bean:write name="jobCardcountOfYear" />
                    </td>
                </logic:iterate>
                <td align="center">
                    <bean:write name="reportForm" property="totalyearCount_jobCard"/>
                </td>
                <logic:iterate id="jobCardcountOfYearLabour" name="reportForm" property="jobCardYearLabour">
                    <td align="center">
                        <bean:write name="jobCardcountOfYearLabour" />
                    </td>
                </logic:iterate>
                <td align="center">
                    <bean:write name="reportForm" property="totalyearCount_jobCard_labour"/>
                </td>
            </tr>

            <tr bgcolor="#ffffff" height="20">
            </tr>

            <tr bgcolor="#ddddee" height="20">
                <td class="headingSpas" nowrap rowspan="2" align="center" ><b>Workshop</b></td>
                <td class="headingSpas" nowrap align="center" colspan="<bean:write name="reportForm" property="dmisJobTypeTablelColength" />"><b>Type Of Job</b></td>
                <td class="headingSpas" nowrap align="center" colspan="6"><b>Installation</b></td>
            </tr>
            <tr bgcolor="#ddddee" height="20">
                <logic:iterate id="columnHeaderName" name="reportForm" property="jobTypeDesc">
                    <td align="center" >
                        <b><bean:write name="columnHeaderName" /></b>
                    </td>
                </logic:iterate>
                <td class="headingSpas" nowrap align="center" >
                    <b>Total</b>
                </td>
                <td align="center" >
                    <b>Total Delivery</b>
                </td>
                <td align="center">
                    <b>Done</b>
                </td>
                <td align="center">
                    <b>Invalid</b>
                </td>
                <td align="center" >
                    <b>Invalid(%)</b>
                </td>
                <td align="center">
                    <b>Pending</b>
                </td>
                <td align="center" >
                    <b>Pending(%)</b>
                </td>

            </tr>
            <tr >
                <td class="headingSpas" bgcolor="#ddddee" nowrap align="center" >MTD (CUMM.)</td>
                <logic:iterate id="countOfMonth" name="reportForm" property="monthCount">
                    <td align="center" >
                        <bean:write name="countOfMonth" />
                    </td>
                </logic:iterate>
                <td align="center" >
                    <bean:write name="reportForm" property="jobCardTypeMTDTotal"/>
                </td>
                <td align="center" >
                    <bean:write name="reportForm" property="monthDeliveryCnt"/>
                </td>
                <td align="center" >
                    <bean:write name="reportForm" property="monthInsCnt"/>
                </td>
                <td align="center" >
                    <bean:write name="reportForm" property="monthInvalidCnt"/>
                </td>
                <td align="center" >
                    <bean:write name="reportForm" property="monthInvalidPer"/>
                </td>
                <td align="center" >
                    <bean:write name="reportForm" property="monthPendingIns"/>
                </td>
                <td align="center" >
                    <bean:write name="reportForm" property="monthPendingPer"/>
                </td>
            </tr>
            <tr >
                <td class="headingSpas" bgcolor="#ddddee" nowrap align="center" >YTM (CUMM.)</td>
                <logic:iterate id="countOfYear" name="reportForm" property="yearCount">
                    <td align="center" >
                        <bean:write name="countOfYear" />
                    </td>
                </logic:iterate>
                <td align="center" >
                    <bean:write name="reportForm" property="jobCardTypeYTMTotal"/>
                </td>
                <td align="center" >
                    <bean:write name="reportForm" property="yearDeliveryCnt"/>
                </td>
                <td align="center" >
                    <bean:write name="reportForm" property="yearInsCnt"/>
                </td>
                <td align="center" >
                    <bean:write name="reportForm" property="yearInvalidCnt"/>
                </td>
                <td align="center" >
                    <bean:write name="reportForm" property="yearInvalidPer"/>
                </td>
                <td align="center" >
                    <bean:write name="reportForm" property="yearPendingIns"/>
                </td>
                <td align="center" >
                    <bean:write name="reportForm" property="yearPendingPer"/>
                </td>
            </tr>

            <tr bgcolor="#ffffff" height="20">
            </tr>
            <tr bgcolor="#ddddee" height="20">
                <td class="headingSpas"  rowspan="2" align="center" ><b>Warranty Opn.</b></td>
                <td class="headingSpas"  align="center"  colspan="2"><b>Claimed</b></td>
                <td class="headingSpas"  align="center"  colspan="2"><b>Settled</b></td>
            </tr>
            <tr bgcolor="#ddddee" height="20">
                <td class="headingSpas" align="center"><b>No. of Claims</b></td>
                <td class="headingSpas" align="center"><b>Claimed Amt. Rs.</b></td>
                <td class="headingSpas" align="center"><b>No. of Claims</b></td>
                <td class="headingSpas" align="center"><b>Amount</b></td>
            </tr>
            <tr height="20">
                <td  bgcolor="#ddddee" class="headingSpas" align="center">MTD (CUMM.)</td>
                <logic:iterate id="monthValueWarranty" name="reportForm" property="listOfWarrantyClaimMonth">
                    <td  align="center"> <bean:write name="monthValueWarranty" /></td>
                </logic:iterate>


            </tr>
            <tr  height="20">
                <td bgcolor="#ddddee" class="headingSpas" align="center">YTM  (CUMM.)</td>
                <logic:iterate id="yearValueWarranty" name="reportForm" property="listOfWarrantyClaimYear">
                    <td  align="center"> <bean:write name="yearValueWarranty" /></td>
                </logic:iterate>
            </tr>

            <tr bgcolor="#ffffff" height="20">
            </tr>


        </table>
    </body>
</html>
