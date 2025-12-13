<%-- 
    Document   : v_printStandardChecklist
    Created on : Oct 22, 2014, 12:49:09 PM
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
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>

<%   if (request.getParameter("type").equals("print")) {
%>

<script>
    window.print();
    window.onfocus=function(){ window.close();}
</script>
<%                                                 }
%>
<body >
<%--<div class="breadcrumb_container">
    <ul>
        <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link></li>
        <li class="breadcrumb_link"><a href ='<%=cntxpath%>/pdiServiceProcess.do?option=init_viewPDIDetails&jobType=PDI'><bean:message key="label.common.viewpdidetail" /></a></li>
        <li class="breadcrumb_link">${pdiform.pdiNo}</li>
    </ul>
</div>

<div class="message" id="msg_saveFAILED">${message}</div>--%>
<center>
    <div class="content_right1">
        <div class="con_slidediv2" style="position: relative;width: 100%">
            <%--<h1><bean:message key="label.common.PDIForChassisno" />  ${pdiform.vinNo}</h1>--%>
             <%--<form action="<%=cntxpath%>/pdiServiceProcess.do?option=managePDIStandardChecksdetail" method="post" id="StandardChecksForm">
                            <input type="hidden" name="upperLink" value="<a href ='<%=cntxpath%>/pdiServiceProcess.do?option=init_viewallPendingChassisdetails'>PENDING FOR PDI</a>@@Message"/>--%>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="4" class="LiteBorder" >
                <tr bgcolor="#eeeeee" height="20" >
                    <td  class="headingSpas" align="left" style="padding:3px;FONT-SIZE: 11px;">
                        <label>
                            <B><bean:message key="label.common.vehicleinfo"/></B>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>
                            <table width="100%" border="1" align="center" cellpadding="0" cellspacing="0" bordercolor=#cccccc  bgcolor="#eeeeee" >
                                    <tr height=20 bgcolor="#FFFFFF">
                                        <td align="right" width="20%" style="padding:3px;FONT-SIZE: 11px;"><span class="headingSpas">
                                                <label><B><bean:message key="label.common.dealerName" />: </B></label>
                                            </span></td>
                                        <td align="left" width="20%" style="padding:3px;FONT-SIZE: 11px;"><span class="headingSpas">${serForm.dealerName} [${serForm.dealercode}]</span>
                                        </td>
                                        <td width="15%" align="right" style="padding:3px;FONT-SIZE: 11px;"><span class="headingSpas">
                                                <label><B><bean:message key="label.common.Modelfamily" />: </B></label>
                                            </span>
                                        </td>
                                        <td width="15%" align="left" style="padding:3px;FONT-SIZE: 11px;"><span class="headingSpas">${serForm.modelFamilyDesc}</span>
                                        </td>
                                        <td width="15%" align="right" style="padding:3px;FONT-SIZE: 11px;"><span class="headingSpas">
                                            <label><B><bean:message key="label.common.Modelcode" />: </B></label>
                                        </span></td>
                                        <td width="15%" align="left" style="padding:3px;FONT-SIZE: 11px;"><span class="headingSpas">${serForm.modelCode}</span>
                                        </td>
                                </tr>
                                <tr height=20 bgcolor="#FFFFFF">
                                    <td  align="right" style="padding:3px;FONT-SIZE: 11px;"><span class="headingSpas">
                                            <label><B><bean:message key="label.common.Jobtype" />: </B></label></span>
                                    </td>
                                    <td  align="left" style="padding:3px;FONT-SIZE: 11px;"><span class="headingSpas">${serForm.jobTypeDesc}</span>
                                    </td>
                                    <td  align="right" style="padding:3px;FONT-SIZE: 11px;"><span class="headingSpas">
                                            <label><B><bean:message key="label.common.Chassisno" />: </B></label></span>
                                    </td>
                                    <td  align="left" style="padding:3px;FONT-SIZE: 11px;"><span class="headingSpas">${serForm.vinNo}</span>
                                    </td>
                                    <td align="right" style="padding:3px;FONT-SIZE: 11px;"><span class="headingSpas">
                                            <label><B><bean:message key="label.common.engineno" />: </B></label>
                                        </span></td>
                                    <td align="left"  style="padding:3px;FONT-SIZE: 11px;"><span class="headingSpas">${serForm.engineNumber}</span>
                                    </td>
                                </tr>
                            </table>
                    </td>
                </tr>
            
            <tr><td>
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >
                <tr  height="20" bgcolor="#eeeeee">
                    <td align="left" class="headingSpas" style="padding:3px;FONT-SIZE: 11px;">
                        <B><bean:message key="label.common.standardcheck" /></B>
                    </td>
                </tr>
                <tr  bgcolor="#eeeeee" height="7">
                </tr>
               
                <tr height=25 bgcolor="#FFFFFF">
                    <td align= center class="headingSpas">
                        <table width=100%  border=1 cellspacing=0 cellpadding=0 bordercolor=#cccccc bgcolor=#cccccc>
                            <tr  bgcolor="#eeeeee" height="20">
                                <td nowrap align="center" width="7%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.sno" /></label></td>
                                <td nowrap align="left" width="33%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.activity" /></label></td>
                                <td nowrap align="center" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.ok" /> </label></td>
                                <td nowrap align="left" width="25%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.actiontaken" /></label></td>
                                <td nowrap align="left" width="25%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><label><bean:message key="label.common.remarks" /></label></td>
                                <!--<td width="13%"><label>Status</label></td>-->
                            </tr>

                            <c:set var="count" value="1"/>
                            <c:forEach items="${serForm.dataMap}" var="contentList">
                               <c:choose>
                                    <c:when test="${empty contentList.value}">
                                        <tr bgcolor="#FFFFFF">
                                            <td width="3%" align="center">&nbsp;</td>
                                            <td ><label><strong>${contentList.key.contentDesc}</strong></label><input type="hidden" name="contentId" value="${contentList.key.contentId}"/></td>
                                            <td align="center">${contentList.key.contentId}
                                                <select name="${contentList.key.contentId}checkpoints" style="width:75px !important ">
                                                    <option value="OK" selected>OK</option>
                                                    <option value="NOT OK">NOT OK</option>
                                                </select>
                                            </td>
                                            <td align="left" style="padding-bottom:3px;FONT-SIZE: 11px;padding-top:2px;padding-left:3px;padding-right:3px;"><input type="text" name="${contentList.key.contentId}actionTaken" id="Action Taken" maxlength="100" style="width:240px !important "/></td>
                                            <td align="left" style="padding-bottom:3px;FONT-SIZE: 11px;vpadding-top:2px;padding-left:3px;padding-right:3px;"><input type="text" name="${contentList.key.contentId}observations" id="Observation" maxlength="100" style="width:240px !important "/></td>
                                        </tr>
                                    </c:when>
                                    <c:otherwise>
                                        <tr bgcolor="#FFFFFF" >
                                            <td height="25" colspan="5" style="padding-bottom:3px;FONT-SIZE: 11px;padding-top:2px;padding-left:3px;padding-right:3px;">
                                                <div align="left">
                                                    <label><strong> &nbsp;${contentList.key.contentDesc}</strong></label>
                                                </div><input type="hidden" name="contentId" value="${contentList.key.contentId}"/></td>
                                        </tr>
                                        <%-- <c:set var="count" value="1"/>--%>
                                        <c:set var="i" value="1"/>
                                        <c:forEach items="${contentList.value}" var="subCList">
                                            <tr bgcolor="#FFFFFF">
                                                <td width="3%" align="center" style="padding:3px;FONT-SIZE: 11px;">${i}</td>
                                                <td align="left" style="padding:3px;FONT-SIZE: 11px;">${subCList.subContentDesc}<input type="hidden" name="${contentList.key.contentId}SubContent" value="${subCList.subContentId}"/></td>
                                                <td align="center" style="padding:3px;FONT-SIZE: 11px;"><span class="headingSpas">${subCList.textBoxOption}</span>
                                                </td>
                                                <td align="left" style="padding:3px;FONT-SIZE: 11px;">
                                                    <span class="headingSpas">${subCList.actiontaken}</span>
                                                    <%--<input type="text" name="${contentList.key.contentId}actionTaken" value="${subCList.actiontaken}" id="Action Taken${count}" maxlength="100"  style="width:240px !important " readonly="true" onkeydown="return false;"/>--%>
                                                </td>
                                                <td align="left" style="padding:3px;FONT-SIZE: 11px;">
                                                    <span class="headingSpas">${subCList.observations}</span>
                                                    <%--<input type="text" name="${contentList.key.contentId}observations" value="${subCList.observations}" id="Observation${count}" maxlength="100"  style="width:240px !important " readonly="true" onkeydown="return false;"/>--%>
                                                </td>
                                            </tr>
                                            <c:set var="i" value="${i+1}"/>
                                            <c:set var="count" value="${count+1}"/>
                                        </c:forEach>

                                    </c:otherwise>

                                </c:choose>

                            </c:forEach>
                            <tr bgcolor="#FFFFFF" height="40">
                                                 <td align="left" style="padding:3px;FONT-SIZE: 11px;">
                                                  <span class="headingSpas">
                                                   <label><B><bean:message key="label.common.anyobservation" /></B></label>
                                                 </span></td>
                                               <td colspan="4" align="left"  width="80%" style="padding:3px;FONT-SIZE: 11px;"><span class="headingSpas">${pdiform.remark}</span>
                                                   <%--<textarea name="remark" rows="4" id="Any Observation" onblur="if(!CommentsMaxLength(this, 450)){this.value=this.value.substring(0, 450);}">${serviceform.compVerbatim[count]}${pdiform.remark}</textarea>--%>
                                               </td>
                           </tr>
                        </table>
                    </td>
                </tr>
                </table>

                    </td>
                </tr>
            </table><%--</form>--%>
        </div>
    </div>
</center>
</body>