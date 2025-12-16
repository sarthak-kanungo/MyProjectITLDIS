<%-- 
    Document   : printInstallation
    Created on : Nov 20, 2014, 3:57:26 PM
    Author     : megha.pandya
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat,java.io.File" %>



<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();

%>

<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_5.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/commonValidations.js"></script>
<script type="text/javascript" language="javascript">

    function printview(obj){
        obj.style.display='none';
        window.print();
        window.onfocus=function(){  obj.style.display='block';}
    }
</script>
<c:set var="uplodINSPHotoPAth" value="${serviceform.uploadins}" scope="request" />
<%
            String value = (String) pageContext.getRequest().getAttribute("uplodINSPHotoPAth");
%>

<%--<div id="step1">--%>

<center style="background-color: #ffffff;FONT-SIZE: 11px;">
    <div class="content_right1" >
        <div class="con_slidediv2" style="position: relative;width: 100%">
            <%--<h1><label><b><bean:message key="label.common.installationforChassisno" /></b> ${serviceform.vinNo}</label></h1>--%>

            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="FONT-SIZE: 11px;" >
                <tr><td>
                        <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="0" style="FONT-SIZE: 11px;" >

                            <tr bgcolor="#eeeeee" height="20">
                                <td  class="headingSpas" align="left" ><label><B><bean:message key="label.common.vehicleinfo" /></B></label></td>
                                <td align="right"><a href="#"><img src='<%=cntxpath%>/image/print-icon.png' alt='Get Suggestions' name="img1" border='0' title="print"  onclick="printview(this);"/></a></td>
                            </tr>
                        </table>
                    </td></tr>

                <tr><td><%--<form action="<%=cntxpath%>/installProcess.do?option=manageInstall" method="post" id="StandardChecksForm" enctype="multipart/form-data">--%>
                        <!--<form action="<%=cntxpath%>/installProcess.do?option=initStandardChecks4Install" method="post" id="installationform" >-->
                        <table width="100%" border="0" align="center" cellpadding="3" cellspacing="1"  style="FONT-SIZE: 11px;">

                            <tr>
                                <td  align="right"><span class="headingSpas"><B>
                                            <bean:message key="label.common.Chassisno" />:</B></span></td>
                                <td width="15%" align="left"  ><span>${serviceform.vinNo}</span>
                                    <input name="vinid" type="hidden"  maxlength="50" value="${serviceform.vinid}" readonly />
                                </td>
                                <td align="right"><span class="headingSpas">
                                        <B><bean:message key="label.common.engineno" />:</B>
                                    </span></td>
                                <td align="left" ><span>${serviceform.engineNumber}</span>
                                </td>

                                <td align="right"><span class="headingSpas">
                                        <B><bean:message key="label.common.Modelfamily" />:</B>
                                    </span></td>
                                <td align="left" ><span>${serviceform.modelFamily}</span>
                                </td>

                            </tr>
                            <tr>

                                <td align="right"><span class="headingSpas">
                                        <B><bean:message key="label.common.InvoiceNo" />:</B>
                                    </span></td>
                                <td align="left" > <span>${serviceform.dealerinvoiceno}</span>
                                </td>

                                <td align="right" style="" ><span class="headingSpas">
                                        <B><bean:message key="label.common.InvoiceDate" />:</B>
                                    </span></td>
                                <td align="left" id="saledatetd"><span>${serviceform.saleDate}</span>
                                </td>
                                <td align="right"><span class="headingSpas">
                                        <B><bean:message key="label.common.INSno" />:</B>
                                    </span>
                                </td>
                                <td align="left" style="white-space: nowrap"><span>${serviceform.insNo}</span>
                                </td>
                            </tr>


                            <tr>

                                <td width="14%" align="right" style="" ><div align="right"> <span class="headingSpas">
                                            <B><bean:message key="label.common.installer" />&nbsp;<bean:message key="label.common.name" />:</B>
                                        </span></div></td>
                                <td width="12%" align="left"><span>${serviceform.repname}</span>
                                    <%--<c:forEach items='${mechanicList}'  var='labelValue'  varStatus='status'>
                                        ${labelValue.label}
                                    </c:forEach>--%>
                                </td>
                                <td align="right"><span class="headingSpas">
                                        <B><bean:message key="label.common.visit" />&nbsp;<bean:message key="label.common.date" />:</B>
                                    </span></td>
                                <td align="left" style="white-space: nowrap"><span>${serviceform.visitDate}</span>
                                </td>
                                <td align="right"><span class="headingSpas">
                                        <B><bean:message key="label.common.INSDate" />:</B>
                                    </span>
                                </td>
                                <td align="left" style="white-space: nowrap"><span>${serviceform.insDate}</span>
                                </td>
                            </tr>
                            <tr>
                                <td align="right"><span class="headingSpas">
                                        <B><bean:message key="label.common.Regno" />:</B>
                                    </span></td>
                                <td align="left" ><span>${serviceform.registrationNo}</span>
                                </td>
                                <td align="right" style="white-space: nowrap"><span class="headingSpas">
                                        <B><bean:message key="label.common.Servicebookletno" />:</B>
                                    </span>
                                </td>
                                <td align="left" ><span>${serviceform.serviceBookletNo}</span>
                                </td>
                                <td align="left" style="" id="HMRLABEL"><div align="right"> <span class="headingSpas">
                                            <B><bean:message key="label.common.hrm" />:</B>
                                        </span></div></td>
                                <td align="left" id="HMRVALUE"><span>${serviceform.hmr}</span>
                                </td>
                            </tr>
                            <tr>
                                <td align="right"><span class="headingSpas">
                                        <B><bean:message key="label.common.dealerName" />:</B>
                                    </span></td>
                                <td align="left" ><span class="headingSpas">${serviceform.dealerName}</span>
                                </td>
                                <td align="right"><span class="headingSpas">
                                        <B><bean:message key="label.common.dealerCode" />:</B>
                                    </span></td>
                                <td align="left" ><span class="headingSpas">${serviceform.dealercode}</span>
                                </td>
                                <td align="right" style="" ><span class="headingSpas">
                                        <B><bean:message key="label.common.location" />:</B>
                                    </span>
                                </td>
                                <td align="left"><span class="headingSpas">${serviceform.locationName}</span>
                                </td>
                            </tr>

                        </table>
                    </td>
                </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="FONT-SIZE: 11px;" >
                <tr><td>
                        <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1" style="FONT-SIZE: 11px;">
                            <tr bgcolor="#eeeeee" height="20">
                                <td class="headingSpas" align="left"><B><label><bean:message key="label.common.customerinfo" /></label></B></td>
                            </tr>
                        </table>
                    </td></tr>

                <tr>
                    <td>
                        <table  width="100%" border="0" align="center" cellpadding="3" cellspacing="1"  style="FONT-SIZE: 11px;">
                            <tbody>

                                <tr>
                                    <td   align="right" class="headingSpas" style="white-space: nowrap"><B><bean:message key="label.common.CustomerName" />:</B></td>
                                    <td  align="left" ><span>${serviceform.customerName}</span>
                                        <%--<input name="customerName" type="text" id="Customer Name" value="${serviceform.customerName}" maxlength="70"/>--%>
                                    </td>


                                    <td  align="right" class="headingSpas" style="white-space: nowrap" >
                                        <label><B><bean:message key="label.common.mobilephone" />:</B></label></td>

                                    <td   align="left" ><span>${serviceform.mobilePhone}</span>
                                        <%--<input name="mobilePhone" type="text" id="Mobile Phone" value="${serviceform.mobilePhone}" maxlength="10"/>--%>
                                    </td>


                                    <td   align="right" rowspan="9" class="headingSpas" style="white-space: nowrap" colspan="2" >

                                        <%
                                                    // String path= request.getAttribute("insPhotoPath")==null?"":request.getAttribute("insPhotoPath").toString();
                                                    String s1 = getServletContext().getRealPath("/") + "/DOCUMENTS/INSTALLATION/" + value;
                                                    File f1 = new File(s1);
                                                    if (f1.exists()) {
                                        %>

                                        <img src="<%=cntxpath%>/DOCUMENTS/INSTALLATION/${serviceform.uploadins}" alt="admin" height="250" width="250"/>
                                        <% }%>

                                    </td>
                                </tr>

                                <tr>
                                    <td   align="right" class="headingSpas" style="white-space: nowrap" >
                                        <B><bean:message key="label.common.talukatahsil" />:</B></td>

                                    <td  align="left" ><span>${serviceform.tehsil}</span>
                                    </td>
                                    <td  align="right" class="headingSpas" style="white-space: nowrap" >
                                        <B><bean:message key="label.common.district" />:</B></td>

                                    <td   align="left" ><span>${serviceform.district}</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td   align="right" class="headingSpas" style="white-space: nowrap" >
                                        <B><bean:message key="label.common.village" />:</B></td>

                                    <td   align="left" ><span>${serviceform.village}</span>
                                    </td>

                                    <td   align="right" class="headingSpas" style="white-space: nowrap" >
                                        <B><bean:message key="label.common.pincode" />:</B></td>

                                    <td   align="left" ><span>${serviceform.pinCode}</span></td>
                                </tr>
                                <tr>
                                    <td   align="right" class="headingSpas" style="white-space: nowrap" >
                                        <B><bean:message key="label.common.state" />:</B></td>

                                    <td  align="left" ><span>${serviceform.state}</span>
                                        <%-- <input name="state" type="text" id="State" value="${serviceform.state}" maxlength="40"/>--%>
                                    </td>

                                    <td   align="right" class="headingSpas" style="white-space: nowrap" >
                                        <B><bean:message key="label.common.sizeoflandhold" />:</B></td>

                                    <td   align="left" ><span>${serviceform.sizeoflandhold}</span>
                                    </td>

                                </tr>

                                <tr>
                                    <td align="right" class="headingSpas" style="white-space: nowrap" >
                                        <B><bean:message key="label.common.occupation" />:</B></td>

                                    <td align="left" ><span>${serviceform.occupation}</span>
                                    </td>
                                    <td align="right" class="headingSpas" style="white-space: nowrap" >
                                        <B><bean:message key="label.common.nooffamilymembers" />:</B></td>

                                    <td align="left" ><span>${serviceform.familyMembers}</span>
                                    </td>
                                </tr>

                                <tr>
                                    <td align="right" class="headingSpas" style="white-space: nowrap" >
                                        <B><bean:message key="label.common.nameofbank" />:</B>
                                    </td>
                                    <td align="left" ><span>${serviceform.bankName}</span>
                                    </td>
                                    <td align="right" class="headingSpas" style="white-space: nowrap" >
                                        <B><bean:message key="label.common.modeofpayment" />:</B>
                                    </td>

                                    <td align="left" ><span>${serviceform.paymentMode}</span>
                                    </td>
                                </tr>
                                <tr>
                                    <td  align="right"  class="headingSpas" style="white-space: nowrap" >
                                        <B><bean:message key="label.common.maincrop" />:</B></td>

                                    <td  align="left" ><span>${serviceform.maincrops}</span>
                                    </td>
                                    <td align="right" class="headingSpas" style="white-space: nowrap" >
                                        <B><bean:message key="label.common.relationwithcustomer" />:</B></td>
                                    <td align="left" >
                                        <span>${serviceform.relationshipwithcust}</span>
                                    </td>

                                </tr>
                                <tr>
                                    <td align="right" class="headingSpas" style="white-space: nowrap" >
                                        <B><bean:message key="label.common.contactname" />:</B></td>

                                    <td align="left" >
                                        <span>${serviceform.contactname}</span>
                                    </td>

                                    <td align="right" class="headingSpas" style="white-space: nowrap" >
                                        <B><bean:message key="label.common.contactno" />:</B></td>

                                    <td align="left" >
                                        <span>${serviceform.contactno}</span>
                                    </td>
                                </tr>

                                <tr>
                                    <td align="right" class="headingSpas" style="white-space: nowrap" colspan="1">
                                        <B><bean:message key="label.common.otherTractorDetail" />:</B></td>

                                    <td align="left" colspan="3" ><span>${serviceform.othertractorDetail}</span>
                                    </td>

                                </tr>
                        </table></td>
                </tr>
            </table>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="FONT-SIZE: 11px;">
                <tr><td>
                        <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1" style="FONT-SIZE: 11px;">
                            <tr bgcolor="#eeeeee" height="20">
                                <td class="headingSpas" align="left" ><B><label><bean:message key="label.common.TractorInfomation" /></label></B></td>
                            </tr>
                        </table>
                    </td></tr>


                <tr>
                    <td id="travelcardData">
                        <table  width=100%   border=1 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#ffffff style="FONT-SIZE: 11px;" >
                            <tbody id="tractorinfo">
                                <tr bgcolor="#FFFFFF" height="20"  class="headingSpas">
                                    <td width="10%" align="center" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><bean:message key="label.common.sno" /></B></td>
                                    <td width="30%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><bean:message key="label.common.partname" /></B></td>
                                    <td width="30%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><bean:message key="label.common.partvendorname" /></B></td>
                                    <td width="20%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><bean:message key="label.common.partSerialNo" /> </B></td>
                                </tr>
                                <c:set var="srNo" value="1"/>
                                <c:set var="tsrNo" value="5"/>
                                <c:set var="totallist" value="0"/>
                                <c:forEach items="${travelcardPartList}" var="travelcardPartList">
                                    <c:if test="${!empty travelcardPartList}">
                                        <tr bgcolor="#FFFFFF" height="20">
                                    <input type="hidden" name="actiontakens"  value="${srNo}"/>
                                    <td align="center">${srNo}</td>
                                    <td align="left" nowrap  style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;padding-top: 2px;padding-bottom: 2px" ><span>${travelcardPartList.contentDesc}</span>
                                        <%--  <input name="partNo${srNo}" type="text" id="Part No${srNo}" style="width:300px ; background-color:#E6E4E4;" title="${travelcardPartList.contentDesc}" value="${travelcardPartList.contentDesc}" readonly="true" maxlength="20"/>--%>
                                    </td>

                                    <td align="left" nowrap  style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;padding-top: 2px;padding-bottom: 2px" ><span>${travelcardPartList.observation}</span>
                                        <%-- <input name="partVendorName${srNo}" type="text" id="PartVendor Name${srNo}" style="width:300px !important" title="" value="${travelcardPartList.observation}" maxlength="40"/>--%>

                                    </td>

                                    <td align="left"  nowrap  style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;padding-top: 2px;padding-bottom: 2px"><span>${travelcardPartList.serialno}</span>
                                        <%-- <input name="partSerialNo${srNo}" type="text" id="PartSerial No${srNo}" style="width:250px !important" title="" value="${travelcardPartList.serialno}" maxlength="40"/>--%>
                                    </td>
                        </tr>
                        <c:set var="srNo" value="${srNo+1}"/>
                        <c:set var="totallist" value="${totallist+1}"/>
                    </c:if>

                </c:forEach>
                </tbody>
            </table></td></tr></table>

            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" style="FONT-SIZE: 11px;">
                <tr><td>
                        <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1" style="FONT-SIZE: 11px;">
                            <tr bgcolor="#eeeeee" height="20">
                                <td class="headingSpas" align="left"><B><label><bean:message key="label.common.insCheckList" /></label></B></td>

                            </tr>
                        </table>
                    </td></tr>

                <tr><td>
                        <table width=100%   border=1 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#ffffff style="FONT-SIZE: 11px;">
                            <tr bgcolor="#FFFFFF" height="20"  class="headingSpas">
                                <td width="10%" align="center"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><%--<bean:message key="label.common.sno" />--%>#</label></B></td>
                                <td align="left" width="80%"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><bean:message key="label.common.instructions" /></label></B></td>
                                <%--  <td width="10%"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><bean:message key="label.common.tickhere" /></label></B></td>--%>

                            </tr>
                            <c:set var="count" value="1"/>
                            <c:forEach items="${serviceForm.contentList}" var="contentList">

                                <c:choose>
                                    <c:when test="${empty contentList}">

                                        <tr bgcolor="#FFFFFF" height="20">
                                            <td width="3%" align="center">${rcount}</td>
                                            <td ><label>${contentList.contenvicetDesc}</label><input type="hidden" name="contentId" value="${contentList.contentId}"/></td>
                                            <td align="center">
                                                <input type="checkbox" name="${contentList.contentId}checkpoints"/>
                                            </td>
                                            <td ><input type="text" name="${contentList.contentId}observations" id="Observation" maxlength="100" style="width:200px !important "/></td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>

                                        <tr bgcolor="#FFFFFF" height="20" >
                                            <td width="3%" align="center"><%--${count}--%>
                                                <c:if test="${contentList.status eq 'Y'}">
                                                    <img alt="" src="${pageContext.request.contextPath}/images/success.gif" style="width: 15px;height:15px"/>
                                                </c:if>
                                                <c:if test="${contentList.status eq 'N'}">
                                                    <img alt="" src="${pageContext.request.contextPath}/images/failure.gif" style="width: 15px;height: 15px"/>
                                                </c:if>
                                            </td>
                                            <td align="left" style="padding-left:3px;padding-right:3px;">
                                                ${contentList.contentDesc}<input type="hidden" name="contentId" value="${contentList.contentId}"/>
                                            </td>
                                            <%-- <td align="center">
                                                 <input type="checkbox" name="${contentList.contentId}checkpoints"/>
                                             </td>--%>
             <!--                                <td ><input type="text" name="${contentList.contentId}observations" value="${subCList.observations}" id="Observation" maxlength="100"  style="width:200px !important "/></td>-->
                                        </tr>
                                    </c:otherwise>

                                </c:choose>
                                <c:set var="count" value="${count+1}"/>
                            </c:forEach>

                        </table>
                        <!--</form>-->
                        <table width=100%   border=1 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#ffffff style="FONT-SIZE: 11px;">

                            <tr height="20">

                                <td valign="top" width="50%">
                                    <table width=100%  border=1 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#ffffff style="FONT-SIZE: 11px;" >
                                        <tbody>
                                            <tr bgcolor="#eeeeee" height="25">
                                                <td class="headingSpas" align="left" colspan="2" style="padding-left:5px"><B><bean:message key="label.common.accessoriesrecieved" /></B></td>
                                            </tr>
                                            <c:set  var="sNo" value="1"></c:set>


                                            <c:set var="temp" value="${fn:split(serviceform.accessories, ',')}"/>
                                            <c:forEach var="i" begin="0" end="${fn:length(temp)-1}">

                                            <input type="hidden" name="accessories" value="${i}"/>
                                            <tr bgcolor="#FFFFFF" height="20" >
                                                <td align="left" class="headingSpas" style="padding-left:5px;white-space: nowrap" ><label> ${temp[i]} </label></td>
                                                <%-- <td align="center" >
                                                     <input type="checkbox" name="assrev${i}" id="assrev${i}" value="${temp[i]}"/>
                                                 </td>--%>
                                            </tr>

                                            <c:set var="sNo" value="${sNo+1}"/>
                                        </c:forEach>

                                        </tbody>
                                    </table>
                                </td>
                                <td valign="top" width="50%">
                                    <table width=100%   border=1 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#ffffff style="FONT-SIZE: 11px;">
                                        <tbody>
                                            <tr bgcolor="#eeeeee" height="25">
                                                <td colspan="2" class="headingSpas" align="left" style="padding-left:5px"><B> <bean:message key="label.common.major" /> &nbsp;<bean:message key="label.common.application" /></B></td>
                                            </tr>
                                            <c:set  var="sNo" value="1"></c:set>
                                            <c:set var="temp" value="${fn:split(serviceform.majorApplications, ',')}"/>
                                            <c:forEach var="i" begin="0" end="${fn:length(temp)-1}">
                                            <input type="hidden" name="majorApp" value="${i}"/>
                                            <tr bgcolor="#FFFFFF" height="20" >
                                                <td align="left" class="headingSpas" style="padding-left:5px;white-space: nowrap"><label> ${temp[i]} </label></td>
                                                <%-- <td align="center">
                                                     <input type="checkbox" name="majapp${i}" id="majapp${i}" value="${temp[i]}"/>
                                                 </td>--%>
                                            </tr>

                                            <c:set var="sNo" value="${sNo+1}"/>
                                        </c:forEach>
                                        </tbody>
                                    </table></td>
                            </tr>

                            <tr>
                            <table width="100%"><tr>
                                    <td align="left" width="10%"><span class="headingSpas">
                                            <label><B><bean:message key="label.common.anyobservation" /></B></label>
                                        </span></td>
                                    <td align="left"  width="80%"><span>${serviceform.remarks}</span>
                                        <%-- <textarea name="remarks" rows="4" id="Any Observation"  onblur="if(!CommentsMaxLength(this, 450)){this.value=this.value.substring(0, 450);}">${serviceform.compVerbatim[count]}</textarea>--%>
                                    </td>
                                </tr>

                            </table>
                </tr>


                <c:if test="${!empty enquiriesList}">

                    <tr><td>
                            <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1" bordercolor=#cccccc bgcolor=#cccccc style="FONT-SIZE: 11px;">
                                <tr bgcolor="#eeeeee" height="20">
                                    <td class="headingSpas" align="left" ><B><label><bean:message key="label.common.insenquiry" /></label></B></td>
                                </tr>
                            </table>
                        </td></tr>


                    <tr>
                        <td id="enquiriesdata">
                            <table  width=100%  border=1 cellspacing=0 cellpadding=4 bordercolor=#cccccc bgcolor=#ffffff style="FONT-SIZE: 11px;">
                                <tbody id="tractorinfo">
                                    <tr bgcolor="#FFFFFF" height="20"  class="headingSpas">
                                        <td width="10%" align="center" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><bean:message key="label.common.sno" /></B></td>
                                        <td width="20%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><bean:message key="label.common.CustomerName" /></label></B></td>
                                        <td width="20%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><bean:message key="label.common.fathername" /></label></B></td>
                                        <td width="20%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><bean:message key="label.common.village" /> </label></B></td>
                                        <td width="15%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><bean:message key="label.common.contactno" /> </label></B></td>
                                        <td width="15%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><bean:message key="label.common.relationwithcustomer" /></label> </B></td>
                                    </tr>
                                    <c:set var="srNo" value="1"/>
                                    <c:set var="tsrNo" value="5"/>
                                    <c:set var="totallist" value="0"/>





                                    <c:forEach items="${enquiriesList}" var="enquiriesList">

                                        <tr bgcolor="#FFFFFF" height="20">
                                            <td align="center" width="10%">${srNo}</td>
                                            <td align="left" nowrap width="20%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;padding-top: 2px;padding-bottom: 2px" >
                                                <span>${enquiriesList.custName}</span>
                                            </td>

                                            <td align="left" nowrap width="20%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;padding-top: 2px;padding-bottom: 2px" >
                                                <span>${enquiriesList.fatherName}</span>
                                            </td>

                                            <td align="left"  nowrap width="20%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;padding-top: 2px;padding-bottom: 2px">
                                                <span>${enquiriesList.villagename}</span>
                                            </td>
                                            <td align="left"  nowrap width="15%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;padding-top: 2px;padding-bottom: 2px">
                                                <span>${enquiriesList.mobilePh}</span>
                                            </td>
                                            <td align="left"  nowrap width="15%"  style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;padding-top: 2px;padding-bottom: 2px">
                                                <span>${enquiriesList.relationwithcustomer}</span>
                                            </td>
                                        </tr>
                                        <c:set var="srNo" value="${srNo+1}"/>
                                        <c:set var="totallist" value="${totallist+1}"/>

                                    </c:forEach>




                                </tbody>



                            </table></td>
                    </tr>
                </c:if>
            </table>
            </td>
            </tr>
            </table>
        </div>
    </div>
<div align="left" class="footer_print" style="background-color: #ffffff">
    <p style="padding-left: 15px;">I here by certify that I have carefully read the instructions necessary for maintenance and proper use of Tractor.I will follow all the instructions falling which my warranty stands cancelled.</p>
    <p class="textftl"><b>Customer Name & Signature<br>
             
       </b>
    </p>
    <p class="textftr"><b>Dealer representative Name & <br>Signature with Stamp</b><br>
        <span class="headingSpas">${serviceform.repname} </span>
    </p>
    <p class="textftd"><b>Date</b></p>
</div>
</center>


