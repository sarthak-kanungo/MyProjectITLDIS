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

        </table>
    </body>
</html>
