<%-- 
    Document   : WarrantyMaterialView
    Created on : Oct 30, 2015, 2:44:05 PM
    Author     : Ashutosh.Kumar1
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*" %>
<html>
    <head>


        <%
                    response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                    response.setHeader("Expires", "0");
                    response.setHeader("Pragma", "no-cache");
                    String cntxpath = request.getContextPath();
                    ArrayList<String> packingList = (ArrayList<String>) request.getAttribute("packingList");
        %>
        <script type="text/javascript" language="javascript">
            function printview(obj){
                obj.style.display='none';
                window.print();
                window.onfocus=function(){  obj.style.display='block';}
            }

        </script>

        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
    </head>

    <body >

        <center style="background-color: #ffffff;FONT-SIZE: 11px;">
            <div class="content_right1">
                <div class="con_slidediv2" style="position: relative;width: 100%">
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="8" style="FONT-SIZE: 11px;">
                        <tr id="abcd" bgcolor="#eeeeee"  >
                            <td align="right" colspan="2"><a href="#"><img src='<%=cntxpath%>/image/print-icon.png' alt='Get Suggestions' name="img1" border='0' title="print"  onclick="printview(document.getElementById('abcd'));"/></a></td>
                        </tr>
                        <%--<tr ><td colspan="2" height="20px"></td></tr>--%>
                        <% if (!packingList.isEmpty()) {%>
                        <%
                             for (int i = 0; i < packingList.size(); i += 11) {
                                 if (i % 66 == 0 && i != 0) {%>
                        <tr><td colspan="2" height="15px"><p style="page-break-after:always"></p></td></tr>
                        <tr >
                            <%} else if (i % 22 == 0) {%>
                        <tr ><td colspan="2" height="15px"></td></tr>
                        <tr >
                            <% }%>
                            <td align= left  class="headingSpas">
                                <table  width=50%  border=1 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#ffffff style="FONT-SIZE: 11px;">
                                    <tr >
                                        <td align="left" colspan="3"  ><img   src='<%=cntxpath%>/image/sonalika.jpg' alt='Get Suggestions' name="img1" border='0' title="Warranty Tag"/></td>
                                    </tr>
                                    <tr  bgcolor="#eeeeee" height="20">
                                        <td   align="left" width="10%" class="headingSpas"  nowrap style="padding-left:5px;"><b><bean:message key="label.common.dealerCode" /></b>:<br><span><%= packingList.get(i)%></span></td>
                                        <td   align="left" width="20%" class="headingSpas"  style="padding-left:5px;"><b><bean:message key="label.common.dealerName" /></b>:<br><span><%= packingList.get(i + 1)%></span></td>
                                        <td   align="left" width="10%"  class="headingSpas"  style="padding-left:5px;"><b><bean:message key="label.common.dealerLocation" /></b>:<br><span><%= packingList.get(i + 2)%></span></td>
                                    </tr>

                                    <tr height="20">
                                        <td   align="right"  class="headingSpas"  style="padding-left:5px;"><b><bean:message key="label.common.ClaimNo" /></b></td>
                                        <td   colspan="2" align="left"  class="headingSpas"  style="padding-left:5px;"><span><%= packingList.get(i + 3)%></span></td>
                                    </tr>

                                    <tr height="20">
                                        <td   align="right" class="headingSpas"  style="padding-left:5px;"><b><bean:message key="label.catalogue.partNo" /></b></td>
                                        <td   colspan="2" align="left"  class="headingSpas"  style="padding-left:5px;"><span><%= packingList.get(i + 4)%></span></td>
                                    </tr>

                                    <tr height="20">
                                        <td   align="right"  class="headingSpas"  style="padding-left:5px;"><b><bean:message key="label.common.partDesc" /></b></td>
                                        <td   colspan="2"  align="left"  class="headingSpas"  style="padding-left:5px;"><span><%= packingList.get(i + 5)%></span></td>
                                    </tr>

                                    <tr height="20">
                                        <td   align="right"  class="headingSpas"  style="padding-left:5px;"><b><bean:message key="label.catalogue.engineNo" /></b></td>
                                        <td   colspan="2"align="left"  class="headingSpas"  style="padding-left:5px;"><span><%= packingList.get(i + 6)%>&nbsp;</span></td>
                                    </tr>

                                    <tr height="20">
                                        <td   align="right" class="headingSpas"  style="padding-left:5px;"><b><bean:message key="label.common.tractorChassisNo" /></b></td>
                                        <td   colspan="2" align="left"  class="headingSpas"  style="padding-left:5px;"><span><%= packingList.get(i + 7)%></span></td>
                                    </tr>

                                    <tr height="20">
                                        <td   align="right" class="headingSpas"  style="padding-left:5px;"><b><bean:message key="label.common.failureDate" /></b></td>
                                        <td   colspan="2" align="left"  class="headingSpas"  style="padding-left:5px;"><span><%= packingList.get(i + 8)%></span></td>
                                    </tr>

                                    <tr height="20">
                                        <td   align="right"  class="headingSpas"  style="padding-left:5px;"><b><bean:message key="label.common.failureHrs" /></b></td>
                                        <td   colspan="2" align="left"  class="headingSpas"  style="padding-left:5px;"><span><%= packingList.get(i + 9)%></span></td>
                                    </tr>

                                    <tr height="20">
                                        <td   align="right"  class="headingSpas"  style="padding-left:5px;"><b><bean:message key="label.common.failureDesc" /></b></td>
                                        <td   colspan="2" align="left"  class="headingSpas"  style="padding-left:5px;"><span><%= packingList.get(i + 10)%></span></td>
                                    </tr>

                                </table>
                            </td>


                            <% if (i % 22 != 0) {%>
                        </tr>
                        <% }%>

                        <% }
                                      
                        %>



                        <% }%>
                    </table>
                </div>
            </div>

        </center>
    </body>
</html>

