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
LinkedHashSet<LabelValueBean> result=null;
String obj=request.getParameter("objjCode");
String nameObj=request.getParameter("nameObj");
if (nameObj.equalsIgnoreCase("spain1")){
 result=new masterDAO().getUserList(obj);
}
if (nameObj.equalsIgnoreCase("spain2")){
  result=new masterDAO().getPriceList(obj);
}
%>
<c:set var="dataList" value='<%=result%>'/>


<%if (nameObj.equalsIgnoreCase("spain1")) {%>
<select name='user' id='userId' class="headingSpas" style='width:180px !important' onchange="selectUser1(this,'spain2',document.getElementById('userTypeId').value);">
    <option value='' >--Select Here--</option>
<c:forEach items='${dataList}'  var='dataList'  varStatus='status'>
    <option value='${dataList.value}' title="${dataList.label}" label="${dataList.label} [ ${dataList.value} ]"> ${dataList.label} [ ${dataList.value} ]</option>
</c:forEach>
</select>

<%}%>
<%if (nameObj.equalsIgnoreCase("spain2")) {%>
<select name='price' id='priceId' class="headingSpas" style='width:180px !important'>
<option value='' >--Select Here--</option>
<c:forEach items='${dataList}'  var='price'  varStatus='status'>
    <option value='${price.value}' title="${price.label}" label="${price.label}">${price.label}</option>
</c:forEach></select>

<%}%>
