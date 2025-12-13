
<%@page import="dao.installDAO"%>
<%@page import="beans.ContentFormBean"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<%@ page import="java.sql.*,dao.inventoryEXPDAO,java.util.*,org.apache.struts.util.LabelValueBean" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
%>


    <%if (request.getAttribute("poListByodType") != null) {%>
    <table width="80%" border="0" >

        <tr>
            <td width="40%" align="right" class="headingSpas" nowrap style="padding-right:83px;padding-bottom:10px;" >
                <bean:message key="label.spare.selectCriteria" /> :
            </td>
            <td width="40%" align="left" class="headingSpas">
                &nbsp;
            </td>
        </tr>
        <tr>
            <td  width="40%" align="right" class="headingSpas" nowrap style="padding-left:100px;padding-right:73px" >
                <input type="radio" name="poNoRadio" value="newPO" checked id="NewPO">
                <bean:message key="label.spare.newOrder" /> :
            </td>
            <td width="40%" align="left" >
                <input type="hidden" name="newPoNo" value="">
                ( <bean:message key="label.spare.poNo" /> : ${refNo})
                <input type="hidden" name="refNo" value="${refNo}">
            </td>
            <%--<td colspan="2">&nbsp;</td>--%>
        </tr>
        <tr>
            <td  width="40%" align="right" class="headingSpas" nowrap style="padding-left:10px" >
                <input type="radio" name="poNoRadio" value="oldPO" id="PreviousPO">
                <bean:message key="label.spare.addPrevPoNo" /> :
            </td>
            <td width="40%" align="left" >
                <select id="Previous Po No" name="prevPoNo" class="headingSpas" style="width:200px !important; margin:0px!important" onClick="selectPreviousRadio();"  >   <%--onchange="getOrderType(this.value);"--%>
                    <option value="" >--Select Previous PO No--</option>
                    <c:forEach items="${prevPoList}" var="prevPoList">
                        <option value='${prevPoList.value}' title='${prevPoList.label}'>${prevPoList.label}</option>
                    </c:forEach>
                </select>
                    <input type="hidden" value="${prevPoList}" id="prevPoList">
            </td>
            <%--<td colspan="2">&nbsp;</td>--%>
        </tr>

    </table>

    <%}else {%>
<select id="Discharge Port" name="dischargePort" class="headingSpas" style="width:180px !important; margin:0px!important">
    <option value="">--Select Here--</option>
    <c:if test="${not empty result}">
        <c:set var="list" value='${result}'/>
            <c:forEach items="${list}" var="dataList">
                <c:if test="${inventoryForm.dischargePort eq dataList.value}">
                    <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>
                </c:if>
                <c:if test="${inventoryForm.dischargePort ne dataList.value}">
                    <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
                </c:if>
            </c:forEach>
        </c:if>
</select>
<%}%>
