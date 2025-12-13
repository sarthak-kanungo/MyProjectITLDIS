<%--
    Document   : dmis_jobTypes_Report
    Created on : Jul 2, 2015, 7:25:59 PM
    Author     : satendra.aditya
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
        <table width="100%" border=1 cellspacing=0  cellpadding=4 bordercolor=#cccccc  >
            <tr >
                <td align="right" colspan="19">
                    <input type="button" value="Export" onclick="Export()"/>
                </td>
            </tr>
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


        </table>
    </body>
</html>
