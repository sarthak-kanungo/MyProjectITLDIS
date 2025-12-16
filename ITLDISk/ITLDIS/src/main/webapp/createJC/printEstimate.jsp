<%-- 
    Document   : printEstimate
    Created on : Dec 18, 2014, 4:08:25 PM
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

<script>
 function printview(obj){
        obj.style.display='none';
        window.print();
        window.onfocus=function(){  obj.style.display='block';}
    }
</script>
<center style="background-color:#FFFFFF">
    <div class="content_right1">
        <div class="con_slidediv2" style="position: relative;width: 100%">
          <%--  <h1 class="hText" style="FONT-SIZE: 11px;"><bean:message key="label.common.viewjobCard" /></h1>--%>

            <table width="100%" border="0" cellspacing="0" cellpadding="4" style="FONT-SIZE: 11px;">
                      <tr bgcolor="#eeeeee"  >
                          <td align="left" colspan="5"><label><B><bean:message key="label.common.jobCardSummary" /></B></label></td>
                          <td align="right"><a href="#"><img src='<%=cntxpath%>/image/print-icon.png' alt='Get Suggestions' name="img1" border='0' title="print"  onclick="printview(this);"/></a></td>
                      </tr>
                      <tr>
                        <td align="right"><label ><B><bean:message key="label.common.dealerName" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.dealerName} [${jobcardDetails.dealercode}]</span></td>
                        <td align="right"><label ><B><bean:message key="label.common.jobcardno" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.jobCardNo}</span></td>
                        <td align="right"><label ><B><bean:message key="label.common.date" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.createdOn}</span></td>
                      </tr>
                      <tr>
                        <td align="right"><label ><B><bean:message key="label.common.Jobtype" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.jobTypeDesc}</span></td>
                        <td align="right"><label ><B><bean:message key="label.common.servicetype" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.serviceType}</span></td>
                        <td align="right"><label ><B><bean:message key="label.common.warrantyapplicable" />: </B></label></td>
                        <td align="left"><span>
                         <c:choose>
                        <c:when test="${jobcardDetails.warrantyApplicable eq 'Y'}">
                            Yes
                        </c:when>
                         <c:when test="${jobcardDetails.warrantyApplicable eq 'N'}">
                            No
                        </c:when>
                            <c:otherwise>

                            </c:otherwise>
                        </c:choose></span></td>
                      </tr>

                       <%--<tr>
                        <td align="right"><label ><B><bean:message key="label.common.tractorOuton" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.vehicleOut}</span></td>
                        <td align="right"><label ><B><bean:message key="label.common.workstartedon" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.workStarted}</span></td>
                        <td align="right"><label ><B><bean:message key="label.common.workfinishedon" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.workFinished}</span></td>
                      </tr>
                      <tr>
                        <td align="right"><label ><B><bean:message key="label.common.location" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.locationName}</span></td>
                        <td align="right"><label ><B><bean:message key="label.common.bay" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.bayCode}</span></td>
                        <td align="right"><label ><B><bean:message key="label.common.Couponno" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.couponno}</span></td>
                      </tr>
                      <tr>
                        <td align="right"><label ><B><bean:message key="label.common.mechanicName" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.mechanicName}</span></td>
                        <td align="right"><label ><B><bean:message key="label.common.InspectedBy" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.inspectionBy}</span></td>
                        <td align="right"><label ><B><bean:message key="label.common.tractorInon" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.jobCardDate}</span></td>
                      </tr>--%>

                      <tr bgcolor="#eeeeee" height="20" >
                       <td align="left" colspan="6"><label><B><bean:message key="label.common.TractorDetails" /></B></label></td>
                      </tr>
                      <tr>
                        <td align="right"><label ><B><bean:message key="label.common.Chassisno" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.vinNo}</span></td>
                        <td align="right"><label ><B><bean:message key="label.common.CustomerName" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.customerName}</span></td>
                        <td align="right"><label ><B><bean:message key="label.common.PayeeName" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.payeeName}</span></td>
                      </tr>
                      <tr>
                        <td align="right"><label ><B><bean:message key="label.common.engineno" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.engineNumber}</span></td>
                        <td align="right"><label ><B><bean:message key="label.warranty.customerAddress" />: </B></label></td>
                        <td align="left" rowspan="2" align="left" valign="top">
                            <span>${jobcardDetails.village},${jobcardDetails.tehsil}<br>${jobcardDetails.district}(${jobcardDetails.state})<br>Pin. ${jobcardDetails.pinCode}</span>
                        </td>
                        <td align="right"><label ><B><bean:message key="label.common.PayeeAddress" />: </B></label></td>
                        <td align="left" rowspan="2" align="left" valign="top">
                            <span>${jobcardDetails.payeeVillage},${jobcardDetails.payeeTehsil}<br>${jobcardDetails.payeeDistrict}(${jobcardDetails.payeeState})<br>Pin. ${jobcardDetails.payeePinCode}</span>
                        </td>
                      </tr>
                      <tr>
                        <td align="right"><label ><B><bean:message key="label.common.Modelfamily" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.modelFamily}</span></td>
                        <td>&nbsp;</td>
                        <td>&nbsp;</td>
                      </tr>
                      <tr>
                        <td align="right"><label ><B><bean:message key="label.common.hrmworking" />: </B></label></td>
                        <td align="left"><span>
                                <c:if test="${jobcardDetails.hmeRadio eq 'N'}" >
                                    No
                                </c:if>

                                <c:if test="${jobcardDetails.hmeRadio eq 'Y'}" >
                                    Yes
                                </c:if>

                                <c:if test="${!empty jobcardDetails.hmr}">&nbsp;(${jobcardDetails.hmr})</c:if></span></td>
                        <td align="right"><label ><B><bean:message key="label.common.contactno" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.mobilePhone}</span></td>
                        <td align="right"><label ><B><bean:message key="label.common.contactno" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.payeeMobilePhone}</span></td>
                      </tr>
                      <%--<c:if test="${!empty jobcardDetails.cmpid}">
                      <tr bgcolor="#eeeeee" height="20" >
                       <td align="left" colspan="6"><label><B><bean:message key="label.common.ComplaintDetails" /></B></label></td>
                      </tr>
                        <c:set var="i" value="0"></c:set>
                       <c:forEach items="${jobcardDetails.cmpid}" var="dataList">
                      <tr>
                        <td colspan="6" align="left"><label style="float:left; font-weight:bold; font-size:11px; " ><bean:message key="label.common.complaint" /> - ${(i+1)}</label></td>
                      </tr>
                      <tr>
                        <td align="right"><label ><B><bean:message key="label.common.customerverbatim" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.compVerbatim[i]}</span></td>
                        <td>&nbsp;</td>
                        <td align="left" valign="top">&nbsp;</td>
                        <td align="right"><label><B><bean:message key="label.common.foremanobservation" />: </B></label></td>
                        <td align="left" valign="top"><span>${jobcardDetails.foremanObservation[i]}</span></td>
                      </tr>
                      <tr>
                        <td align="right"><label ><B><bean:message key="label.common.application" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.applicationCode[i]}</span></td>
                        <td align="right"><label><B><bean:message key="label.common.aggregate" />: </B></label></td>
                        <td align="left" valign="top"><span>${jobcardDetails.aggCode[i]}</span></td>
                        <td align="right"><label><B><bean:message key="label.common.subaggregate" />: </B></label></td>
                        <td align="left" valign="top"><span>${jobcardDetails.subaggCode[i]}</span></td>
                      </tr>
                      <tr>
                        <td align="right"><label ><B><bean:message key="label.warranty.subAssembly" />: </B>	</label></td>
                        <td align="left"><span>${jobcardDetails.subassembly[i]}</span></td>
                        <td align="right"><label ><B><bean:message key="label.common.defect" />: </B> </label></td>
                        <td align="left"><span>${jobcardDetails.compCode[i]}</span></td>
                        <td align="right"><label ><B><bean:message key="label.common.cause" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.causalCode[i]}</span></td>
                      </tr>
                       <c:set var="i1" value="0"></c:set>
                      <c:forEach items="${jobcardDetails.action_taken[i]}" var="actionList">
                        <tr>
                        <td align="right"><label ><B><bean:message key="label.common.actiontaken" />: </B></label></td>
                        <td align="left"><span>${jobcardDetails.action_taken[i][i1]}</span></td>
                        <td>&nbsp;</td>
                        <td align="left" valign="top">&nbsp;</td>
                        <td align="right"><label><B><bean:message key="label.common.labourcharge" />: </B></label></td>
                        <td align="left" valign="top"><span>${jobcardDetails.labourCharges[i][i1]}</span></td>
                      </tr>
                       <c:set var="i1" value="${i1 + 1 }" />
                      </c:forEach>
                      <c:set var="i" value="${i + 1 }" />
                      </c:forEach>
                      </c:if>--%>
                    <c:if test="${!empty jobcardDetails.partNo ||!empty jobcardDetails.workDescription}">
                   <tr bgcolor="#eeeeee" height="20" >
                       <td align="left" colspan="6"><label><B><bean:message key="label.warranty.jobCardDetails" /></B></label></td>
                   </tr>
                  <c:if test="${!empty jobcardDetails.partNo}">
                  <tr>
                    <td align="left" colspan="6"><B><bean:message key="label.common.label4viewpart" /></B></td>
                  </tr>
                  <tr>
                    <td align="left" colspan="6">
                      <table  border="0" cellspacing="3" cellpadding="3" width="100%">
                      <tr class="grid">
                        <th  align="center" class="tdgridnew1"><label><bean:message key="label.common.sno" /></label></th>
                        <th  align="left" class="tdgridnew1"><label><bean:message key="label.common.partno_small" /></label></th>
                        <th  align="left" class="tdgridnew1"><label><bean:message key="label.common.partdesc_small" /></label></th>
                        <th  align="left" class="tdgridnew1"><label><bean:message key="label.common.category" /></label></th>
                        <th  align="left" class="tdgridnew1"><label><bean:message key="label.common.qty_small" /></label></th>
                        <th  align="center" class="tdgridnew1" ><label><bean:message key="label.common.unitprice_small" /></label></th>
                        <th  align="center" class="tdgridnew1"><label><bean:message key="label.common.discount_small" /> (%)</label></th>
                        <th  align="left" class="tdgridnew1"><label><bean:message key="label.common.billto_small" /></label></th>
                        <th  align="right" class="tdgridnew1" style="padding-right: 35px;"><label><bean:message key="label.common.amt_small" /></label></th>
                      </tr>
                       <c:set var="j" value="0"></c:set>
                       <c:forEach items="${jobcardDetails.partNo}" var="dataList">
                      <tr >
                        <td align="center" class="tdgridnew1" >${(j+1)}</td>
                        <td align="left" ><span>${jobcardDetails.partNo[j]}</span></td>
                        <td align="left" ><span>${jobcardDetails.partDesc[j]}</span></td>
                        <td align="left" ><span>${jobcardDetails.comptype[j]}</span></td>
                        <td align="left" ><span>${jobcardDetails.quantityS[j]}</span></td>
                        <td align="right" style="padding-right: 50px;"><span>${jobcardDetails.unitPrice[j]}</span></td>
                        <td align="right" style="padding-right: 50px;" ><span>${jobcardDetails.discount_perc[j]}</span></td>
                        <td align="left" ><span>${jobcardDetails.billTo[j]}</span></td>
                        <td align="right" style="padding-right: 35px;"><span>${jobcardDetails.partPriceAmount[j]}</span></td>
                      </tr>
                      <c:set var="j" value="${j + 1 }" />
                      </c:forEach>
                     <tr >
                         <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1"><label><B><bean:message key="label.common.totalSparesValue" /></B></label></td>
                        <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${jobcardDetails.totalPartsValue}</span></td>
                      </tr>
                      <tr >
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1"><label><B><bean:message key="label.common.totalLubesValue" /></B></label></td>
                        <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${jobcardDetails.totalLubesValue}</span></td>
                      </tr>
                    </table></td>
                  </tr>
                  </c:if>
                  <c:if test="${!empty jobcardDetails.workDescription || !empty jobcardDetails.cmpid}">
                  <tr bgcolor="#eeeeee" height="20" >
                       <td align="left" colspan="6"><label><B><bean:message key="label.common.charges_small" /></B></label></td>
                   </tr></c:if>
                  <tr>
                    <td align="left" colspan="6"><table  border="0" cellspacing="3" cellpadding="3" width="100%">
                      <c:if test="${!empty jobcardDetails.workDescription}">
                          <tr class="grid">
                              <th width="7%"  align="center" class="tdgridnew1" ><label><bean:message key="label.common.sno" /></label></th>
                              <th width="30%"  align="left" class="tdgridnew1" ><label><bean:message key="label.common.workType" /></label></th>
                              <th width="10%" align="left" class="tdgridnew1" ><label><bean:message key="label.common.category" /></label></th>
                              <th width="40%"  align="left" class="tdgridnew1" ><label><bean:message key="label.common.workDesc" /></label></th>
                              <th width="13%"  align="right" class="tdgridnew1" style="padding-right: 35px;"><label><bean:message key="label.common.amt_small" /></label></th>
                          </tr>

                       <c:set var="k" value="0"></c:set>
                       <c:forEach items="${jobcardDetails.workDescription}" var="dataList">
                      <tr >
                        <td align="center" class="tdgridnew1" ><span>${(k+1)}</span></td>
                        <td align="left" ><span>${jobcardDetails.workCode[k]}</span></td>
                        <td align="left" ><span><bean:message key="label.common.Sublets" /></span></td>
                        <td align="left" ><span>${jobcardDetails.workDescription[k]}</span></td>
                        <td align="right" style="padding-right: 35px;"><span>${jobcardDetails.workAmount[k]}</span></td>
                      </tr>
                      <c:set var="k" value="${k + 1 }" />
                      </c:forEach>
                      <c:set var="i" value="0"></c:set>
                      <c:forEach items="${jobcardDetails.cmpid}" var="dataList">
                          <c:set var="i1" value="0"></c:set>
                          <c:forEach items="${jobcardDetails.action_taken[i]}" var="actionList">
                              <tr >
                                  <td align="center" class="tdgridnew1" ><span>${(k+1)}</span></td>
                                  <td align="left" ><span>${jobcardDetails.compCode[i]}</span></td>
                                  <td align="left" ><span><bean:message key="label.common.Labour" /></span></td>
                                  <td align="left" ><span>${jobcardDetails.action_taken[i][i1]}</span></td>
                                  <td align="right" style="padding-right: 35px;"><span>${jobcardDetails.labourCharges[i][i1]}</span></td>
                              </tr>
                              <c:set var="k" value="${k + 1 }" /><c:set var="i1" value="${i1 + 1 }" />
                          </c:forEach>
                          <c:set var="i" value="${i + 1 }" />
                      </c:forEach>
                      <tr >
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="right" class="tdgridnew1"><label><B><bean:message key="label.common.totalSublets" /></B></label></td>
                        <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${jobcardDetails.totalOtherCharges}</span></td>
                      </tr>
                      <tr >
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="right" class="tdgridnew1"><label><B><bean:message key="label.common.ttllabourcharge_small" /></B></label></td>
                        <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${jobcardDetails.totalLabourCharges}</span></td>
                      </tr>
                      <tr >
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="right" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                      </tr></c:if>
                      
                      <tr >
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="left" class="tdgridnew1">&nbsp;</td>
                        <td align="right" class="tdgridnew1"><label><B><bean:message key="label.common.GrandTotal" /><%-- (<bean:message key="label.common.spares" /> + <bean:message key="label.common.lubes" /> + <bean:message key="label.common.Labour" /> +<bean:message key="label.common.Sublets" />)--%></B></label></td>
                        <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${jobcardDetails.totalEstimate}</span></td>
                      </tr>
                    </table></td>
                  </tr>
                  </c:if>


                </table>

        </div>
    </div>
<%--<div align="left" class="footer_print" style="background-color: #ffffff">
    <p style="padding-left: 15px;"><B>Customer Undertaking</B><br>I/We have received above tractor duly repaired in perfect working condition. I/We do not have any complaint and are fully satisfied by your services. Received all old parts.</p>
    <p class="textftl"><b>Customer Name & Signature</b></p>
    <p class="textftr"><b>Dealer representative Name & <br>Signature with Stamp</b></p>
    <p class="textftd"><b>Date</b></p>
</div>--%>
</center>
