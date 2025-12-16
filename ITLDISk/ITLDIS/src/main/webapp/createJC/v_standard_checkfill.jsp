<%--
    Document   : v_standard_check.jsp
    Created on : May 08, 2014, 02:25:09 PM
    Author     : Avinash.Pandey
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
 <link rel="stylesheet" href="layout/css/login.css" type="text/css" />
        <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css" media="screen" />
        <link href="${pageContext.request.contextPath}/css/dynCalendar.css" rel="stylesheet" type="text/css"/>
        <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Master_290414.css" type="text/css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css"  type="text/css"/>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<script>
    <%--function submitForm()
    {
        document.getElementById("StandardChecksForm").submit();
    }--%>

function checkpoint(len){

 
        for(i=1;i<len;i++){
            //alert(document.getElementById("checkpoints"+i).value);
            if(document.getElementById("checkpoints"+i).value==="NOT OK" && document.getElementById("Action Taken"+i).value===""){
                <%--document.getElementById("Action Taken"+i).style.border="1px solid #ff0000";--%>
                document.getElementById("Action Taken"+i).focus();
                alert(actiontaken_validation_msg);
                document.getElementById("Action Taken"+i).select();
                // bool="true";
                //break;
                return false;
            }
            else if(document.getElementById("checkpoints"+i).value==="NOT OK" && document.getElementById("Observation"+i).value===""){
                <%--document.getElementById("Observation"+i).style.border="1px solid #ff0000";--%>
                document.getElementById("Observation"+i).focus();
                alert(remark_validation_msg);
                document.getElementById("Observation"+i).select();
                //bool="true";
                //break;
                return false;
            }

      }

     document.getElementById("StandardChecksForm").submit();

      }

   function printList()
   {
      var jobCardNo='${serviceform.jobCardNo}';
      var modelCode='${serviceform.modelCode}';
      var jobType='${serviceform.jobType}';
      var strURL = "${pageContext.request.contextPath}/serviceCreateJC.do?option=printStandardChecklist&jobCardNo="+jobCardNo+"&modelCode="+modelCode+"&jobType="+jobType+"&type=print";
      window.open(strURL,'iji','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
   }

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


                <h1 ><span class="formLabel"> <bean:message key="label.common.standardcheck" /></span></h1>





<c:if test="${serviceform.jobcardview eq 'true'}">
    <%@ include file="topbandshow.jsp" %>
</c:if>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >
    <tr><td>
            <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1">
                <tr align="center" valign="middle" class="formHeader">
                    <c:choose>
                     <c:when test="${pathNm eq 'fillJC'}">
                    <td ><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initVehicleInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.vehicleinfo" /></a></label></td>
                    
                    <td ><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initCustomerInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.customerinfo" /></a></label></td>
                         
                         
                    <td class="formSelectedHeader"><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStandardChecks&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.standardcheck" /></a></label></td>
                    <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initComplaint&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.complaint" /></a></label></td>
                    <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initEstimate&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.estimate" /></a></label></td>
                    <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initActual&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.actuals" /></a></label></td>
                    <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStatus&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.status" /></a></label></td>
                    <c:if test="${showHistory ne 'Y'}">
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=init_vehicle_History&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}" class="formLabel"><bean:message key="label.common.history" /></a></label></td>
                            </c:if>
                     </c:when>
                     <c:otherwise>
                          <td ><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initVehicleInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}" class="linkGreyBg"><bean:message key="label.common.vehicleinfo" /></a></label></td>
                    <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initCustomerInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}" class="formLabel"><bean:message key="label.common.customerinfo" /></a></label></td>
                    <td class="formSelectedHeader"><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStandardChecks&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}" class="formLabel"><bean:message key="label.common.standardcheck" /></a></label></td>
                    <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initComplaint&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}" class="formLabel"><bean:message key="label.common.complaint" /></a></label></td>
                    <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initEstimate&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}" class="formLabel"><bean:message key="label.common.estimate" /></a></label></td>
                    <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initActual&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}" class="formLabel"><bean:message key="label.common.actuals" /></a></label></td>
                    <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStatus&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}" class="formLabel"><bean:message key="label.common.status" /></a></label></td>
                    <c:if test="${showHistory ne 'Y'}">
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=init_vehicle_History&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}" class="formLabel"><bean:message key="label.common.history" /></a></label></td>
                            </c:if>       
                   
                         
                     </c:otherwise>
                    </c:choose>
                     </tr>
            </table>
        </td></tr>
   
    <tr><td>
            <form action="<%=cntxpath%>/serviceCreateJC.do?option=manageStandardChecksdetail&jctype=${serviceform.jctype}" method="post" id="StandardChecksForm">
                <input type="hidden" name="jobCardNo" value="${serviceform.jobCardNo}"/>
                <input type="hidden" name="jobType" value="${serviceform.jobType}@${serviceform.modelCode}"/>
                <input type="hidden" name="vinid" value="${serviceform.vinid}"/>
                <input type="hidden" name="jobId" value="${serviceform.jobId}">
                <input name="showHistory" type="hidden" value="${showHistory}">
            <table width="100%" cellpadding="2" cellspacing="4" class="LiteBorder">
                <tr class="formCatHeading">
                    <td width="7%" align="center"><label><bean:message key="label.common.sno" /></label></td>
                    <td width="28%" align="left"><label><bean:message key="label.common.activity" /></label></td>
                    <td width="9%" align="left"><label><bean:message key="label.common.ok" /> </label></td>
                    <td width="28%" align="left"><label><bean:message key="label.common.actiontaken" /></label></td>
                    <td width="28%" align="left"><label><bean:message key="label.common.remarks" /></label></td>
                    <%--<td width="13%"><label><bean:message key="label.common.status" /></label></td>--%>
                </tr>
                 <c:set var="count" value="1"/>
                <c:forEach items="${serviceForm.dataMap}" var="contentList">

                    <c:choose>
                        <c:when test="${empty contentList.value }">

                            <tr>
                                <td width="7%" align="center">&nbsp;</td>
                                <td width="28%" align="left"><label><strong>${contentList.key.contentDesc}</strong></label><input type="hidden" name="contentId" value="${contentList.key.contentId}"/></td>
                                <td width="9%" align="left">${contentList.key.contentId}
                                    <select name="${contentList.key.contentId}checkpoints" style="width:55px !important " id="checkpoints${count}">
                                        <option value="OK" selected>OK</option>
                                        <option value="NOT OK">NOT OK</option>
                                    </select>
                                </td>
                                <td width="28%" align="left"><input type="text" name="${contentList.key.contentId}actionTaken" id="Action Taken${count}" maxlength="100" style="width:200px !important "/></td>
                                <td width="28%" align="left"><input type="text" name="${contentList.key.contentId}observations" id="Observation${count}" maxlength="100" style="width:200px !important "/></td>
                               <%-- <td >
                                    <select name="${contentList.key.contentId}status" >
                                        <option value="COMPLETE" selected>COMPLETE</option>
                                        <option value="PENDING">PENDING</option>
                                    </select>
                                </td>--%>
                            </tr>
                        </c:when>
                        <c:otherwise>

                            <tr bgcolor="#CCCCCC" >
                                <td height="25" colspan="5">
                                    <div align="left">
                                        <label><strong> &nbsp;${contentList.key.contentDesc}</strong></label>
                                    </div><input type="hidden" name="contentId" value="${contentList.key.contentId}"/></td>
                            </tr>
                           <%-- <c:set var="count" value="1"/>--%>
                            <c:forEach items="${contentList.value}" var="subCList">
                                <tr>
                                    <td width="7%" align="center">${count}</td>
                                    <td width="28%" align="left">${subCList.subContentDesc}<input type="hidden" name="${contentList.key.contentId}SubContent" value="${subCList.subContentId}"/></td>
                                    <td width="9%" align="left">
                                        <select name="${contentList.key.contentId}checkpoints" style="width:83px !important " id="checkpoints${count}" >
                                            <c:if test="${subCList.textBoxOption eq 'NOT OK'}">
                                                <option value="OK" >OK</option>
                                                <option  value="NOT OK" selected>NOT OK</option>
                                            </c:if>
                                            <c:if test="${subCList.textBoxOption ne 'NOT OK'}">
                                                <option value="OK" selected>OK</option>
                                                <option  value="NOT OK" >NOT OK</option>
                                            </c:if>
                                            
                                        </select>
                                    </td>
                                    <td width="28%" align="left"><input type="text" name="${contentList.key.contentId}actionTaken" value="${subCList.actiontaken}" id="Action Taken${count}" maxlength="100"  style="width:200px !important "/></td>
                                    <td width="28%" align="left"><input type="text" name="${contentList.key.contentId}observations" value="${subCList.observations}" id="Observation${count}" maxlength="100"  style="width:200px !important "/></td>
                                    <%--<td >
                                        <select name="${contentList.key.contentId}status">
                                            <c:if test="${subCList.status eq 'COMPLETE'}">
                                                <option value="COMPLETE" selected>COMPLETE</option>
                                                <option value="PENDING">PENDING</option>
                                            </c:if>
                                            <c:if test="${subCList.status ne 'COMPLETE'}">
                                                <option value="COMPLETE" >COMPLETE</option>
                                                <option value="PENDING" selected>PENDING</option>
                                            </c:if>
                                        </select>
                                    </td>--%>
                                </tr>
                                <c:set var="count" value="${count+1}"/>
                            </c:forEach>
                        </c:otherwise>

                    </c:choose>

                </c:forEach>

                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                   
                    <td colspan="2" align="right">&nbsp; </td>
                    <td align="right" style="padding-right:20px;">
                      <%if (userFunctionalities.contains("608")) {%>
                    <c:if test="${serviceform.status eq 'OPEN' and count ne 1}">
                    
                    <input name="input" type="button" value="Save" style="float:none; " onclick="return checkpoint(${count});"/>
                    
                    
                   </c:if>
                    
                    <%}%>
                    &nbsp; <a href="#" ><img src='<%=cntxpath%>/image/print-icon.png'  name="img1" border='0' title="print"  onclick="printList()"/></a>
                    <%--<input name="input" type="button" value="Print" style="float:none; " onclick="printList();"/>--%>
                    
                  </td>
                </tr>
            </table>
              </form>
        </td>
    </tr>
</table>
            </div></div></center>