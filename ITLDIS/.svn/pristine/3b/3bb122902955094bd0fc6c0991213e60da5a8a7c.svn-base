<%-- 
    Document   : v_history_info
    Created on : May 14, 2014, 6:36:42 PM
    Author     : megha.pandya
--%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
%>
<script type="text/javascript" language="javascript">
    function openHistory(jobcard){
    
        window.open("${pageContext.request.contextPath}/serviceCreateJC.do?option=initVehicleInformation&jobCardNo="+jobcard+"&vinNo=${serviceform.vinNo}&showHistory=Y","history","width=1000,height=600,scrollbars=yes");
    }

    $(document).ready(function(){
        $('.viewJobcard').click(function(){
            var url = "serviceProcess.do?option=viewJobcard&productionCategory_Desc="
                +$(this).attr('data-categoryDesc').trim()+"&vinNo="+Base64.encode($(this).attr('data-vinNo').trim())
                +"&jobTypeDesc="+$(this).attr('data-jobTypeDesc').trim()
                +"&jobCardNo="+Base64.encode($(this).attr('data-jobCardNo').trim())
                +"&warrantyApplicable="+$(this).attr('data-warrantyApplicable').trim()
                +"&engineNumber="+$(this).attr('data-engineNumber').trim()
                +"&jobcardview="+$(this).attr('data-jobcardView').trim()
                +"&jobType="+$(this).attr('data-jobType').trim()
                +"&modelCode="+$(this).attr('data-modelCode').trim()
                +"&vin_details_type="+$(this).attr('data-vinDetailsType').trim()
                +"&pathNm=fillJC&jctype="+$(this).attr('data-jctype').trim()
                +"&vinid="+$(this).attr('data-vinId').trim()
                +"&pagename=history&flag="+Base64.encode("viewAll");
            $(this).attr('href',url);
        });
    });
</script>
<div class="breadcrumb_container">
    <ul>
       
              <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link> </li>
              <c:if test="${openJC eq 'viewAll'}">
                <li class="breadcrumb_link"><a href="<%=cntxpath%>/serviceProcess.do?option=init_viewallJobcarddetails&jobType=ViewJC"><bean:message key="label.common.viewalljobcard" /></a></li>
                </c:if>
               <li class="breadcrumb_link"><bean:message key="label.common.createjobcard" /></li>
       
    </ul>
</div>
 <div class="message" id="msg_saveFAILED"><html:errors/>${message}</div>

  
 <center>
  <div class="content_right1">
        <div class="con_slidediv2" style="width: 100%">
            <h1 ><span class="formLabel"> <bean:message key="label.common.history" /></span></h1>
<c:if test="${serviceform.jobcardview eq 'true' }">
    <%@ include file="topbandshow.jsp" %>
</c:if>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >
    <tr><td>
            <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1">
                <tr align="center" valign="middle" class="formHeader">
                    <input type="hidden" name="jobId" value="${serviceform.jobId}">
                    <td ><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initVehicleInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.vehicleinfo" /></a></label></td>
                        <td ><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initCustomerInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.customerinfo" /></a></label></td>
                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStandardChecks&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.standardcheck" /></a></label></td>
                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initComplaint&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.complaint" /></a></label></td>
                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initEstimate&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.estimate" /></a></label></td>
                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initActual&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.actuals" /></a></label></td>
                        <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStatus&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.status" /></a></label></td>
                        <td class="formSelectedHeader"><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=init_vehicle_History&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.history" /></a></label></td>
                </tr>
            </table>
        </td></tr>
    <c:if test="${empty vehicle_HistoryList}">
        <tr class="formCatHeading">
            <td valign="top"  style="padding-top:10px;color: red" align="center">
                <bean:message key="label.common.historynotfound"/>
            </td>
        </tr>
    </c:if>
    <c:if test="${!empty vehicle_HistoryList}">
    <tr><td><%--<form name="form1" id="form1" method="post" action="">--%>
             <pg:pager url="serviceCreateJC.do" maxIndexPages="10" maxPageItems="10">
             <pg:param name="option" value="init_vehicle_History"/>
             <pg:param name="vinNo"  value='${serviceform.vinNo}'/>
             <pg:param name="jobTypeDesc"  value='${serviceform.jobTypeDesc}'/>
             <pg:param name="jobCardNo"  value='${serviceform.jobCardNo}'/>
             <pg:param name="productionCategory_Desc"  value='${serviceform.productionCategory_Desc}'/>
             <pg:param name="warrantyApplicable"  value='${serviceform.warrantyApplicable}'/>
             <pg:param name="engineNumber"  value='${serviceform.engineNumber}'/>
            <table width="100%" cellpadding="2" cellspacing="4" class="LiteBorder">
                <tr class="formCatHeading">

                    <td align="center"><label><bean:message key="label.common.sno" /></label></td>
                    <td align="left"><label><bean:message key="label.common.jobcardno" /></label></td>
                    <td align="left"><label><bean:message key="label.common.Jobtype" /></label></td>
                    <td align="left"><label><bean:message key="label.common.jobcardDate" /></label></td>
                    <td align="left"><label><bean:message key="label.common.contactname" /></label></td>
                    <td align="left"><label><bean:message key="label.common.contactno" /></label></td>
                    <td align="left"><label><bean:message key="label.common.hrmworking" /></label></td>
                    <td align="left"><label><bean:message key="label.common.status" /></label></td>
                    <%--<td width="10%" align="center"><label>View Details</label></td>--%>
                   <!-- <td style="background-color:#f9f9f9 "></td>-->
                </tr>
                <c:set var="sno" value="1"></c:set>
                <logic:iterate id="vehicle_HistoryList" name="vehicle_HistoryList">
                    <pg:item>
                        <tr>
                            <td align="center">${sno}</td>
                            <td align="left">
                                <a href="#" class="viewJobcard" data-categoryDesc="${serviceform.productionCategory_Desc}"
                                   data-vinNo="${serviceform.vinNo}" data-jobTypeDesc="${serviceform.jobTypeDesc}" data-jobCardNo="${vehicle_HistoryList.jobCardNo}"
                                   data-warrantyApplicable="${serviceform.warrantyApplicable}" data-engineNumber="${serviceform.engineNumber}" data-jobcardView="${serviceform.jobcardview}"
                                   data-jobType="${serviceform.jobType}" data-modelCode="${serviceform.modelCode}" data-vinDetailsType="${serviceform.vin_details_type}"
                                   data-jctype="${serviceform.jctype}" data-vinId="${serviceform.vinid}">
                                <span id ="jobCardNo${sno}" >${vehicle_HistoryList.jobCardNo}</span></a></td>
                            <%--<td align="left"><span id ="jobCardNo${sno}" onclick="openHistory('${vehicle_HistoryList.jobCardNo}');">${vehicle_HistoryList.jobCardNo}</span></td>--%>
                             <td align="left"><span >${vehicle_HistoryList.jobTypeDesc}</span></td>
                            <td align="left"><span id ="jobCardDate${sno}" >${vehicle_HistoryList.jobCardDate}</span></td>
                            <td align="left"><span id="serviceDoneBy${sno}">${vehicle_HistoryList.customerName}</span></td>
                            <td align="left"><span id="jobTypeDesc${sno}">${vehicle_HistoryList.mobilePhone}</span></td>
                            <td align="left">
                            <c:if test="${vehicle_HistoryList.hmeRadio eq 'Y'}">
                            <span >YES</span>
                            </c:if>
                            <c:if test="${vehicle_HistoryList.hmeRadio ne 'Y'}">
                            <span >NO</span>
                            </c:if>
                            <c:if test="${!empty vehicle_HistoryList.hmr}">
                            <span >(${vehicle_HistoryList.hmr})</span>
                            </c:if>

                            </td>
                            <td align="left"><span >${vehicle_HistoryList.status}</span></td>
                           <%-- <td align="center"><span id="View${sno}"><img alt="View History Details" src="${pageContext.request.contextPath}/images/notebook.png"   title="View History Details"/></span></td>--%>
                        </tr>
                   </pg:item>
                    <c:set var="sno" value="${sno + 1 }" />
                </logic:iterate>
                        
                <tr >
                    <td colspan="6" align="center" bgcolor="#FFFFFF" class="textPaging" >
                        <pg:index>
                            <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle"/></a></pg:first>&nbsp;
                            <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle"/></a></pg:prev>&nbsp;
                            <pg:pages>&nbsp;<a style="color: blue;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                            <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle"/></a></pg:next>&nbsp;
                            <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle"/></a></pg:last>
                        </pg:index>
                    </td>
                </tr>
            </table>
            </pg:pager>
            <%--  </form>--%>
        </td>
    </tr>
    </c:if>
</table>
        </div></div>
 </center>