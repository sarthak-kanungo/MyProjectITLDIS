<%-- 
    Document   : getCompCodeListAjax
    Created on : APR 19, 2014, 5:06:15 PM
    Author     : Avinash.Pandey
--%>

<%@page import="dao.installDAO"%>
<%@page import="beans.ContentFormBean"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<%@ page import="java.sql.*,dao.masterDAO,java.util.*,org.apache.struts.util.LabelValueBean" %>
<%
            String nameObj = request.getParameter("nameObj");
            String objjCode = request.getParameter("objjCode");
            String rowCount = request.getParameter("rowCount");
            LinkedHashSet<LabelValueBean> result = null;
            LinkedList<ContentFormBean> travelcardPartList = null;
            travelcardPartList = new LinkedList<ContentFormBean>();
            result = new LinkedHashSet<LabelValueBean>();
            if (objjCode != null) {
                try {
                    if (nameObj.equalsIgnoreCase("travelcardData")) {
                        travelcardPartList = new installDAO().GetTravelDetailIns(objjCode);
                    }
                    else{
                    result = new masterDAO().getCommonCodeById(objjCode, nameObj);}
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

%>
<c:set var="list" value='<%=result%>'/>
<c:set var="travelcardPartList" value='<%=travelcardPartList%>'/>
<%if (nameObj.equalsIgnoreCase("Agg_Code")) {%>
<select name="subaggCode" id="subaggCode<%=rowCount%>" onchange="getCommonCode(this,'subAgg_Code','<%=rowCount%>')">
    <option value="">--Select--</option>

    <c:forEach items="${list}" var="dataList">
        <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
    </c:forEach>
</select>
<%}%>
<%if (nameObj.equalsIgnoreCase("Comp_Code")) {
    // onchange="getCommonCode(this,'Causal_Code','<%=rowCount')"
    %>

<select name="causalCode"  id="causalCode<%=rowCount%>">
    <option value="">--Select--</option>

    <c:forEach items="${list}" var="dataList">
        <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
    </c:forEach>
</select>

<%}%>
<%if (nameObj.equalsIgnoreCase("Causal_Code")) {%>
<select name="consequenceCode<%=rowCount%>" type="list" multiple id="consequenceCode<%=rowCount%>"  style="height:60px !important">
    <c:forEach items="${list}" var="dataList">
        <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
    </c:forEach>
</select>

<%}%>
<%if (nameObj.equalsIgnoreCase("Complaint")) {%>
<select name="actionCode" id="actionCode<%=rowCount%>"    onchange="getServiceHrsAjax(this,'Action_Taken','<%=rowCount%>')">
    <option value="">--Select--</option>
    <c:forEach items="${list}" var="dataList">
        <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
    </c:forEach>
</select>

<%}%>
<%if (nameObj.equalsIgnoreCase("subAgg_Code")) {%>
<select name="subassemblyCode" id="subassemblyCode<%=rowCount%>" onchange="getCommonCode(this,'subassembly_Code',<%=rowCount%>);">
    <option value="">--Select--</option>
<%--    <c:forEach items="${list}" var="dataList">
        <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
    </c:forEach>--%>
        <c:forEach items="${list}" var="dataList">
            <c:if test="${serviceform.subaggCode[count] eq dataList.value }">

                <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>

            </c:if>

            <c:if test="${serviceform.subaggCode[count] ne dataList.value }">

                <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>

            </c:if>

        </c:forEach>
</select>

<%}%>
<%if (nameObj.equalsIgnoreCase("subassembly_Code")) {%>
<select name="compCode" id="compCode<%=rowCount%>" >
    <option value="">--Select--</option>
    <c:forEach items="${list}" var="dataList">
        <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
    </c:forEach>
</select>

<%}%>
<%if (nameObj.equalsIgnoreCase("travelcardData")) {%>
<table width="90%" border="0" align="center" cellpadding="3" cellspacing="5">
<tbody>
                <tr>
			<td width="6%" align="center">S.NO</td>
                        <td width="33%">PART NO</td>
			<td width="16%">PART VENDOR NAME</td>
			<td width="50%">PART SERIAL NO</td>
		</tr>
		<c:set var="srNo" value="1"/>
		<c:forEach items="${travelcardPartList}" var="travelcardPartList">
                    <c:if test="${!empty travelcardPartList}">
			<tr>
                        <input type="hidden" name="actiontakens" value="${srNo}"/>
                        <td align="center">${srNo}</td>
                	<td align="left"  >
                            <input name="partNo${srNo}" type="text" id="Part No" style="width:230px !important" title="${travelcardPartList.contentDesc}" value="${travelcardPartList.contentDesc}"/>
               		 </td>
               		 
                   	 <td align="left"  >
                        <input name="partVendorName${srNo}" type="text" id="PartVendor Name" style="width:100px !important" title="${travelcardPartList.observation}" value="${travelcardPartList.observation}" />

                  	  </td>
                	
                	<td align="left"  >
                   	 <input name="partSerialNo${srNo}" type="text" id="PartSerial No" style="width:280px !important" title="${travelcardPartList.status}" value="${travelcardPartList.status}" />
                	</td>
               		</tr>
		    </c:if>
                  <c:set var="srNo" value="${srNo+1}"/>
                </c:forEach>
             <tr align="right">
                    <td  colspan="4" >
                        <input name="ListSize" id="ListSize" type="hidden" value="${srNo}"/>
                        <input name="input" type="button" value="Next" onclick="validateForm();"/></td>
                </tr>  
            </tbody>
          </table>

<%}%>
