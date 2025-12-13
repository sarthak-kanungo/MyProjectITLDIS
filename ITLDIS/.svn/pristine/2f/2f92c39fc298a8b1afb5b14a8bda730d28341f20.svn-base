<%-- 
    Document   : warrantyList
    Created on : Sep 18, 2014, 3:10:52 PM
    Author     : kundan.rastogi
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';

	function submitForm() {

		document.getElementById('export').submit();
	}
</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.common.warranty" /></a></li>
            <li class="breadcrumb_link"><label><bean:message key="label.warranty.pendingforwarranty" /></label></li>                            <%--<bean:message key="label.warranty.jobCardPendingForWarranty" />--%>
      </ul>
      
      </div>
      
      <html:form action="warrantyAction.do?option=pendingWarrantyClaimExport&flag=check" method="post"  styleId="export">
      
      <div style="width:30%; float:right">
      <table style="width: 100%; text-align: right">
    <tr>
        <% if (!userFunctionalities.contains("101")) { %>
            <td align="right" style="white-space: nowrap; text-align: right;">
                <span class="headingSpas" style="">
                    <bean:message key="label.warranty.dealerName" />
                </span>
                <select name="dealerCode" id="dealerCode"
                    class="selectnewbox txtGeneralNW"
                    style="width: 200px !important; display: inline-block;" onchange="updateInput()">
                    <option value="">ALL</option>
                    <c:forEach var="dList" items="${dealerList}">
                        <c:set var="isSelected" value="${dealerName == dList.dealerName}" />
                        <option value="${dList.dealer_code}"
                            data-code="${dList.dealer_code}" ${isSelected ? 'selected' : ''}>
                            ${dList.dealer_code} [${dList.dealerName}] [${dList.location}]
                        </option>
                    </c:forEach>
                </select>
                <input type="hidden" name="dealerCode" id="dealerCode" value="" />
            </td>
            <td align="right">
                <input name="Srch" type="text" id="Srch" value="" style="display: none"/>
                <input name="go" type="button" value="EXPORT" onClick="submitForm()"/>
            </td>
        <% } %>
    </tr>
</table>
      
    </div>
           
      </html:form>
      
    

    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1><bean:message key="label.warranty.jobCardPendingForWarranty" /></h1>

                <table id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>

                    <tr  bgcolor="#eeeeee" height="20">
                        <td   align="center" width="6%" class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.warranty.S.No" /></b></td>
                        <td   align="left" width="15%"  class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.common.jobcardno" /></b></td>
                        <td   align="left" width="12%"  class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.common.jobcardDate" /></b></td>
                        <td   align="left" width="15%" class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.warranty.vehicleNo" /></b></td>
                         <td   align="left" width="15%" class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.common.JobcardPayeeName" /></b></td>
                        <td   align="left" width="15%" class="headingSpas" nowrap style="padding-left:5px;"><b><bean:message key="label.warranty.TMSRefNo" /></b></td>
                       <%if (userFunctionalities.contains("101")) {%>
                        <td   align="center" width="10%" class="headingSpas" nowrap><b><bean:message key="label.warranty.generateClaim" /></b></td>
                       <%} %>

                      
                    </tr>

                 
                        <c:if test="${!empty jobCardList}">
                      <%--  <tr height=25 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">--%>
                                <pg:pager url="warrantyAction.do" maxIndexPages="10" maxPageItems="10">
                                    <pg:param name="option" value="initWarrantyList"/>
                                    <pg:param name="nameSrch"  value='<%=request.getParameter("nameSrch")%>'/>
                                 <%--   <table  id="data" width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>--%>
                                       <%-- <tr  bgcolor="#eeeeee" height="20">
                                            <td   align="center" width="25" class="headingSpas" nowrap ><b>S.No</b></td>
                                            <td   align="left" width="150"  class="headingSpas" nowrap><b>Bill Description</b></td>
                                            <td   align="left" width="150"  class="headingSpas" nowrap><b>Discount%</b></td>
                                            <td   align="center" width="40" class="headingSpas" nowrap><b>Edit</b></td>
                                            <td   align="center" width="35" class="headingSpas" nowrap><b>isActive</b></td>
                                        </tr>--%>
                                        <c:set var="sno" value="1"></c:set>
                                        <logic:iterate id="jobCardList" name="jobCardList">
                                            <pg:item>
                                                <tr id ="${sno}" height="20">
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">${sno}</td>
                                                    <td align="left" bgcolor="#FFFFFF" title="${jobCardList.jobCardNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="compType${sno}" >
                                                            ${jobCardList.jobCardNo}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" title=" ${jobCardList.jobCardDate}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="compType${sno}" >
                                                            ${jobCardList.jobCardDate}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" title="  ${jobCardList.vinNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="compType${sno}" >
                                                            ${jobCardList.vinNo}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" title=" ${jobCardList.payeeName}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="compType${sno}" >

                                                            ${jobCardList.payeeName}
                                                        </span>
                                                    </td>
                                                    <td align="left" bgcolor="#FFFFFF" title=" ${jobCardList.tmsRefNo}"  class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="compType${sno}" >
                                                         <c:set var="now" value="<%=new java.util.Date()%>"/>
                                                            <fmt:formatDate value="${now}" type="both" dateStyle="long" timeStyle="long" var="currentDate"  pattern="yyyyMMdd" />
                                                             <c:if test="${jobCardList.tmsRefNo ne 'NA'}" >
                                                            ${jobCardList.tmsRefNo}
                                                             </c:if>
                                                             <c:if test="${jobCardList.tmsRefNo eq 'NA'}" >
                                                                 - -
                                                             </c:if>
                                                        </span>
                                                    </td>
                                                   
                                                    <%if (userFunctionalities.contains("101")) {%>
                                                    <td align="center" bgcolor="#FFFFFF" width="25" class="headingSpas" style="padding-left:5px; padding-right: 5px">
                                                        <span id ="st${sno}"> ${jobCardList.replacementDate} ${jobCardList.tmsRefNo}
                                                            <c:if test="${jobCardList.tmsRefNo ne 'NA' and jobCardList.replacementDate gt currentDate}" >
                                                            <%--<a href="javascript:void(0)" onclick="createWarrenty('${jobCardList.jobCardNo}', '${jobCardList.vinNo}')"  class="headingSpas" style="color: blue;" >--%>
                                                            <a href="warrantyAction.do?option=createWarranty&jobCrdNo=${jobCardList.jobCardNo}&vinNo=${jobCardList.vinNo}"  class="headingSpas" style="color: blue;" >
                                                                
                                                                 <img src="${pageContext.request.contextPath}/image/approve2.jpg"  title="Generate Warranty Claim"/>
                                                            </a>
                                                            </c:if>

                                                             <c:if test="${jobCardList.tmsRefNo eq 'NA'}" >
                                                                 - -
                                                             </c:if>
                                                        </span>
                                                    </td>
                                                     <%}%>
                                                </tr>
                                            </pg:item>
                                            <c:set var="sno" value="${sno + 1 }" />
                                        </logic:iterate>
                                        <tr >
                                             <%if (userFunctionalities.contains("702")) {%>
                                            <td colspan="7" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                 <%} else {%>
                                             
                                            <td colspan="6" align="center" bgcolor="#FFFFFF" class="textPaging" >
                                                 <%}%>
                                                <pg:index>
                                                    <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle"/></a></pg:first>&nbsp;
                                                    <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle"/></a></pg:prev>&nbsp;
                                                    <pg:pages>&nbsp;<a style="color: blue;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                                    <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle"/></a></pg:next>&nbsp;
                                                    <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle"/></a></pg:last>
                                                    </pg:index>
                                            </td>
                                        </tr>
                                <%--    </table>--%>
                                </pg:pager>
                           <%-- </td>
                        </tr>--%>
                    </c:if>
               
                        <c:if test="${empty jobCardList}">
                            <tr bgcolor="#FFFFFF">
                                <%if (userFunctionalities.contains("702")) {%>
                                            <td valign="top" align="center" colspan="7" class="headingSpas" style="padding-top:10px;color: red">
                                                 <%} else {%>

                                             <td valign="top" align="center" colspan="6" class="headingSpas" style="padding-top:10px;color: red">
                                                 <%}%>
                              
                                    No Job Card is pending for Warranty Claim Creation
                                 
                                </td>
                            </tr>
                        </c:if>
                
                 
                </table>
               </div>
        </div>
    </center>
</div>

