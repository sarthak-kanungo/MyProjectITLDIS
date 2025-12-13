<%--
    Document   : warrantyClaimed
    Created on : Jul 3, 2015, 5:34:19 PM
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

        </table>
    </body>
</html>
