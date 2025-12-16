<%--
    Document   : getUserTypeDetails
    Created on : Nov 19, 2015, 4:14:39 PM
    Author     : mahendra.rawat
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="dao.masterDAO" %>
<%@ page import="org.apache.struts.util.LabelValueBean" %>
<%@ page import="java.util.LinkedHashSet" %>
<%
            String result = null;
            LinkedHashSet<LabelValueBean> listResult = null;
            String obj = request.getParameter("objjCode");
            String userType = request.getParameter("userType");
            String value = request.getParameter("spain2");
            if (value.equalsIgnoreCase("spain2")) {
                result = new masterDAO().getPrice(obj);
                listResult = new masterDAO().getPriceList(userType);
            }
%>
<c:set var="data" value='<%=result%>'/>
<c:set var="dataList" value='<%=listResult%>'/>
<select name='price' id='priceId' class="headingSpas" style='width:180px !important'>
    <option value='' >--Select Here--</option>
    <c:forEach items='${dataList}'  var='price'  varStatus='status'>
        <c:choose>
            <c:when test="${data eq price.label}">
                <option value='${price.value}' title="${price.label}" label="${price.label}" selected>${price.label}</option>
            </c:when>
            <c:otherwise>
                <option value='${price.value}' title="${price.label}" label="${price.label}">${price.label}</option>
            </c:otherwise>
        </c:choose>
    </c:forEach>
</select>

