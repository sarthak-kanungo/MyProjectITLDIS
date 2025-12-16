<%-- 
    Document   : exportFailPartCreation
    Created on : 11-Mar-2015, 17:08:39
    Author     : kundan.rastogi
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<%@page contentType="application/vnd.ms-excel" pageEncoding="UTF-8"%>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Content-Disposition", "attachment; filename=Fail_Part_List.xls");
         
%>


<center style="background-color:#FFFFFF">
    <c:if test="${not empty failPartList}">
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
               <h3> <bean:message key="label.invenUpload.message" /></h3>
                <table border="1" cellspacing="0" cellpadding="3" width="100%">
                    <tr class="grid" bgcolor="#eeeeee">
                        <th  align="center" class="tdgridnew1"><label><bean:message key="label.common.sno" /></label></th>
                        <th  align="left" class="tdgridnew1"><label><bean:message key="label.common.partno_small" /></label></th>
                        <th  align="center" class="tdgridnew1"><label><bean:message key="label.common.qty_small" /></label></th>
                        <th  align="center" class="tdgridnew1"><label><bean:message key="label.common.binLocation" /></label></th>
                    </tr>
                    <c:set var="j" value="0"></c:set>
                    <c:forEach items="${failPartList}" var="failPartList">
                        <tr>
                            <td align="center" class="tdgridnew1" >${(j+1)}</td>
                            <td align="left" ><span>${failPartList.partno}</span></td>
                            <td align="center" ><span>${failPartList.qty}</span></td>
                            <td align="center" ><span>${failPartList.binLocation}</span> </td>
                        </tr>
                        <c:set var="j" value="${j + 1 }" />
                    </c:forEach>
                </table>
            </div>
        </div>
    </c:if>
</center>