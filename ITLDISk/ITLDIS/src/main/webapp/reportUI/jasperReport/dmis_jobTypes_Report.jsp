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
        <br>
        <table width="100%" border=1 cellspacing=0  cellpadding=4 bordercolor=#cccccc  >
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

        </table>
    </body>
</html>
