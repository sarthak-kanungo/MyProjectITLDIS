<%-- 
    Document   : v_viewjobcardDetail
    Created on : Sep 30, 2014, 10:41:59 AM
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
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
    function CommentsMaxLength(text,maxLength)
    {
        var diff = maxLength - text.value.length;

        if (diff < 0){
            alert(maxLength_validation_msg + maxLength);
            return false;
        }
        return true;
    }
    function validateReject()
    {
      
        alert(not_blank_validation_msg+"Remarks");
        document.getElementById("Remarks").focus();
        return false;
       
    }
    function callback(){

        window.location.href="<%=cntxpath%>/serviceProcess.do?option=initSearchHistory&vinNo=${jobcardDetails.vinNo}";
    }
    function approveJobCard(val)
    {
        if(val=='reject'){
            document.getElementById("Status").value="REJECTED";
            if( document.getElementById("Remarks").value==""){
                alert(not_blank_validation_msg+"Remarks");
                document.getElementById("Remarks").focus();
                return false;
            }
        }
        else{
            document.getElementById("Status").value="APPROVED";
        }
        document.getElementById("serviceForm").submit();
    }
    function OpenOrReject(val)
    {
        if( document.getElementById("Remarks").value==""){
            alert(not_blank_validation_msg+"Remarks");
            document.getElementById("Remarks").focus();
            return false;
        }
        document.getElementById("serviceForm").submit();
    }
    function printInvoice()
    {
        var vinNo='${jobcardDetails.vinNo}';
        var jobCardNo='${jobcardDetails.jobCardNo}';
        var dealerCode=$('#notOpenDealerCode').val();
        var strURL = "${pageContext.request.contextPath}/serviceProcess.do?option=viewJobcard&vinNo="+Base64.encode(vinNo.trim())+"&jobCardNo="+Base64.encode(jobCardNo.trim())+"&flag="+Base64.encode("print")+"&dealerCode="+Base64.encode(dealerCode);
        window.open(strURL,'iji','width=1000,height=900, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
    }
</script>
<div class="breadcrumb_container" style="width: 98%">
    <input type="hidden" id="notOpenDealerCode" value="${dealerCode}">
    <ul class="hText">
        <c:if test="${flag ne 'penPackGen' }">
            <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link></li>
        </c:if>
        <c:if test="${flag eq 'closeAll'}">
            <li class="breadcrumb_link"><a href="<%=cntxpath%>/serviceProcess.do?option=initCloseJobCards&pathNm=fillJC"><bean:message key="label.common.closejobcard" /></a></li>
        </c:if>
        <c:if test="${flag eq 'viewAll'}">
            <li class="breadcrumb_link"><a href="<%=cntxpath%>/serviceProcess.do?option=init_viewallJobcarddetails&jobType=ViewJC"><bean:message key="label.common.viewalljobcard" /></a></li>
        </c:if>

        <c:if test="${flag eq 'ReOpen'}">
            <li class="breadcrumb_link"><a href="<%=cntxpath%>/serviceProcess.do?option=initReOpenJobCards"><bean:message key="label.common.reOpenJobCard" /></a></li>
        </c:if>
        <c:if test="${flag eq 'deApproval'}">
            <li class="breadcrumb_link"><a href="<%=cntxpath%>/serviceProcess.do?option=initPendingDE4Approval"><bean:message key="label.common.pending4DEapproval" /></a></li>
        </c:if>
        <c:if test="${flag eq 'validateVinNo'}">
            <li class="breadcrumb_link"><a href="<%=cntxpath%>/serviceProcess.do?option=initPending4validateVinNo"><bean:message key="label.common.pending4chassisvalidate" /></a></li>
        </c:if>
        <c:if test="${flag eq 'Ginvoice'}">
            <li class="breadcrumb_link"><a href="<%=cntxpath%>/serviceProcess.do?option=initCreateInvoiceList"><bean:message key="label.common.generateinvoice" /></a></li>
        </c:if>
        <c:if test="${flag eq 'ReOpenUpdate'}">
            <li class="breadcrumb_link"><a href="<%=cntxpath%>/serviceProcess.do?option=initReOpenJobCards"><bean:message key="label.common.reOpenJobCard" /></a></li>
        </c:if>
        <c:if test="${flag eq 'searchhistory' }">
            <li class="breadcrumb_link"><a href="<%=cntxpath%>/serviceProcess.do?option=initSearchHistory"><bean:message key="label.common.tractorhistory" /></a></li>
        </c:if>

        <c:if test="${flag eq 'penPackGen' }">
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/initWarranty.do'><bean:message key="label.common.warranty" /></a></li>
            <li class="breadcrumb_link"> <a href='<%=cntxpath%>/warrantyAction.do?option=pendingForDispatch'>  <bean:message key="label.warranty.pendingforPackGen" /></a> </li>
        </c:if>

        <c:if test="${flag eq 'PDI'}">
            <li class="breadcrumb_link"><a href ='<%=cntxpath%>/pdiServiceProcess.do?option=init_viewPDIDetails&jobType=PDI'><bean:message key="label.common.viewpdidetail" /></a></li>
        </c:if>

        <li class="breadcrumb_link">${jobcardDetails.jobCardNo}</li>
    </ul>

    <c:if test="${flag eq 'viewAll' and pagename eq 'history' }">
        <div align="right" style="float: right">
            <input name="input" type="button" onclick="window.history.back()" value="back">
        </div>
    </c:if>

    <c:if test="${flag eq 'searchhistory'}">
        <div align="right" style="float: right">
            <input name="input" type="button" onclick="callback()" value="back">
        </div>
    </c:if>



</div>

<div class="message" id="msg_saveFAILED"></div>
<center>
    <div class="content_right1">
        <div class="con_slidediv2" style="position: relative;width: 100%">
            <h1 class="hText"><bean:message key="label.common.viewjobCard" /></h1>

            <input type="hidden" name="upperLink" value="<a href ='<%=cntxpath%>/pdiServiceProcess.do?option=init_viewallPendingChassisdetails'>PENDING FOR PDI</a>@@Message"/>
            <table width="100%" border="0" cellspacing="0" cellpadding="5">
                <tr bgcolor="#eeeeee"  >
                    <td align="left" colspan="7"><label><B><bean:message key="label.common.jobCardSummary" /></B></label></td>
                    <%--<td align="right" colspan="1" style="padding-right:7px "><a href="<%=cntxpath%>/serviceProcess.do?option=viewJobcard&vinNo=${jobcardDetails.vinNo}&jobCardNo=${jobcardDetails.jobCardNo}&flag=viewAll" ><label ><B><h4 style="color: blue"><bean:message key="label.common.viewcompletejobcard" /></h4></B></label></a></td>--%>
                    <td align="right" colspan="1" style="padding-right:7px ">
                        <label >

                            <c:if test="${flag eq 'PDI'}">
                                <a href="<%=cntxpath%>/serviceCreateJC.do?option=initVehicleInformation&vinNo=${jobcardDetails.vinNo}&jobCardNo=${jobcardDetails.jobCardNo}&openJC=viewAll&jobType=PDI" style="color: blue">
                                    <bean:message key="label.common.viewcompletejobcard" />
                                </a>
                            </c:if>

                            <c:if test="${flag ne 'PDI'}">
                                <a href="<%=cntxpath%>/serviceCreateJC.do?option=initVehicleInformation&vinNo=${jobcardDetails.vinNo}&jobCardNo=${jobcardDetails.jobCardNo}&openJC=viewAll" style="color: blue">
                                    <bean:message key="label.common.viewcompletejobcard" />
                                </a>
                            </c:if>
                        </label>
                        <%--<input type="button" id="print" value="print" onclick="printInvoice();"/>--%>
                        &nbsp;<a href="#"><img src='<%=cntxpath%>/image/print-icon.png' alt='Get Suggestions' name="img1" border='0' title="print"  onclick="printInvoice();"/></a>
                    </td>

                </tr>
                <tr>
                    <td align="right"><label ><B><bean:message key="label.common.dealerName" />: </B></label></td>
                    <td align="left"><span>${jobcardDetails.dealerName}[ ${jobcardDetails.dealercode} ]</span></td>
                    <td align="right"><label ><B><bean:message key="label.common.jobcardno" />: </B></label></td>
                    <td align="left"><span>${jobcardDetails.jobCardNo}</span></td>
                    <td align="right"><label ><B><bean:message key="label.common.date" />: </B></label></td>
                    <td align="left"><span>${jobcardDetails.createdOn}</span></td>
                </tr>
                <tr>
                    <td align="right"><label ><B><bean:message key="label.common.tractorInon" />: </B></label></td>
                    <td align="left"><span>${jobcardDetails.jobCardDate}</span></td>
                    <td align="right"><label ><B><bean:message key="label.common.workstartedon" />: </B></label></td>
                    <td align="left"><span>${jobcardDetails.workStarted}</span></td>
                    <td align="right"><label ><B><bean:message key="label.common.Jobtype" />: </B></label></td>
                    <td align="left"><span>${jobcardDetails.jobTypeDesc}</span></td>
                </tr>

                <tr>
                    <td align="right"><label ><B><bean:message key="label.common.tractorOuton" />: </B></label></td>
                    <td align="left"><span>${jobcardDetails.vehicleOut}</span></td>
                    <td align="right"><label ><B><bean:message key="label.common.workfinishedon" />: </B></label></td>
                    <td align="left"><span>${jobcardDetails.workFinished}</span></td>
                    <td align="right"><label ><B><bean:message key="label.common.servicetype" />: </B></label></td>
                    <td align="left"><span>${jobcardDetails.serviceType}</span></td>
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
                            
                     <td align="right"><label ><B><bean:message key="label.common.itlExtWarrantyapplicable" />: </B></label></td>
                    <td align="left"><span>${jobcardDetails.itlExtWarrantyApplicable}</span></td>       
                   

                </tr>
                <tr>
                    <td align="right"><label ><B><bean:message key="label.catalogue.remarks" />: </B></label></td>
                    <td align="left" colspan="3" style="white-space: nowrap"><span>${jobcardDetails.remarks}</span></td>
                    <td align="right"><label ><B><bean:message key="label.common.salesDate" />: </B></label></td>
                    <td align="left"><span>${jobcardDetails.deliveryDate}</span></td>

                </tr>
                <tr>
                    <td align="right"><label class="hText"><B><bean:message key="label.service.reasonForDealy" />: </B></label></td>
                    <td align="left"  style="white-space: nowrap"><span>${jobcardDetails.reasonForDealy}</span></td>
                    <td align="right"><label class="hText"><B><bean:message key="label.service.complaintDate" />: </B></label></td>
                    <td align="left"><span>${jobcardDetails.complaintDate}</span></td>
                    <td align="right"><label ><B><bean:message key="label.common.VorJobCard" />: </B></label></td>
                    <td align="left"><span>${jobcardDetails.vorJobcard}</span></td>
                     
                    
                </tr>

                <tr bgcolor="#eeeeee" height="20" >
                    <td align="left" colspan="10"><label><B><bean:message key="label.common.TractorDetails" /></B></label></td>
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
                <c:if test="${!empty jobcardDetails.cmpid}">
                    <tr bgcolor="#eeeeee" height="20" >
                        <td align="left" colspan="10"><label><B><bean:message key="label.common.ComplaintDetails" /></B></label></td>
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
                            <%--<td align="right"><label ><B><bean:message key="label.common.cause" />: </B></label></td>
                            <td align="left"><span>${jobcardDetails.causalCode[i]}</span></td>--%>
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
                </c:if>
                <c:if test="${!empty jobcardDetails.partNo ||!empty jobcardDetails.workDescription}">
                    <tr bgcolor="#eeeeee" height="20" >
                        <td align="left" colspan="10"><label><B><bean:message key="label.warranty.jobCardDetails" /></B></label></td>
                    </tr>
                    <c:if test="${!empty jobcardDetails.partNo}">
                        <tr>
                            <td align="left" colspan="10"><label><B><bean:message key="label.common.label4viewpart" /></B></label></td>
                        </tr>
                        <tr>
                            <td align="left" colspan="6"><table  border="0" cellspacing="3" cellpadding="3" width="100%">
                                    <tr class="grid ">
                                        <th  align="center" class="tdgridnew1"><label><bean:message key="label.common.sno" /></label></th>
                                        <th  align="left" class="tdgridnew1"><label><bean:message key="label.common.partno" /></label></th>
                                        <th  align="left" class="tdgridnew1"><label><bean:message key="label.common.partdesc" /></label></th>
                                        <th  align="left" class="tdgridnew1"><label><bean:message key="label.common.category" /></label></th>
                                        <th  align="left" class="tdgridnew1"><label><bean:message key="label.common.qty" /></label></th>
                                        <th  align="center" class="tdgridnew1" ><label><bean:message key="label.common.unitprice" /></label></th>
                                        <th  align="center" class="tdgridnew1"><label><bean:message key="label.common.discount" /> (%)</label></th>
                                        <th  align="left" class="tdgridnew1"><label><bean:message key="label.common.billto" /></label></th>
                                        <th  align="right" class="tdgridnew1" style="padding-right: 35px;"><label><bean:message key="label.common.amount" /></label></th>
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
                                        <td align="left" class="tdgridnew1"><label><B><bean:message key="label.common.totallubesvalue" /></B></label></td>
                                        <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${jobcardDetails.totalLubesValue}</span></td>
                                    </tr>
                                </table></td>
                        </tr>
                    </c:if>
                    <c:if test="${!empty jobcardDetails.workDescription}">
                        <tr bgcolor="#eeeeee" height="20" >
                            <td align="left" colspan="10"><label><B><bean:message key="label.common.othercharges" /></B></label></td>
                        </tr></c:if>
                        <tr>
                            <td align="left" colspan="6"><table  border="0" cellspacing="3" cellpadding="3" width="100%">
                                <c:if test="${!empty jobcardDetails.workDescription}">
                                    <tr class="grid ">
                                        <th width="7%"  align="center" class="tdgridnew1"><label><bean:message key="label.common.sno" /></label></th>
                                        <th width="31%"  align="left" class="tdgridnew1"><label><bean:message key="label.common.workType" /></label></th>
                                        <th width="48%"  align="left" class="tdgridnew1"><label><bean:message key="label.common.workDesc" /></label></th>
                                        <th width="14%"  align="right" class="tdgridnew1" style="padding-right: 35px;"><label><bean:message key="label.common.amount" /></label></th>
                                    </tr>

                                    <c:set var="k" value="0"></c:set>
                                    <c:forEach items="${jobcardDetails.workDescription}" var="dataList">
                                        <tr >
                                            <td align="center" class="tdgridnew1" ><span>${(k+1)}</span></td>
                                            <td align="left" ><span>${jobcardDetails.workCode[k]}</span></td>
                                            <td align="left" ><span>${jobcardDetails.workDescription[k]}</span></td>
                                            <td align="right" style="padding-right: 35px;"><span>${jobcardDetails.workAmount[k]}</span></td>
                                        </tr>
                                        <c:set var="k" value="${k + 1 }" />
                                    </c:forEach>
                                    <tr >
                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                        <td align="right" class="tdgridnew1"><label><B><bean:message key="label.common.totalSublets" /></B></label></td>
                                        <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${jobcardDetails.totalOtherCharges}</span></td>
                                    </tr>
                                    <tr >
                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                        <td align="right" class="tdgridnew1"><label><B><bean:message key="label.common.otherdiscount" /></B></label></td>
                                        <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${jobcardDetails.totaldiscount}</span></td>
                                    </tr>
                                    <tr >
                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                        <td align="right" class="tdgridnew1">&nbsp;</td>
                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                    </tr></c:if>
                                    <tr >
                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                        <td align="right" class="tdgridnew1">&nbsp;</td>
                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                    </tr>
                                    <tr >
                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                        <td align="left" class="tdgridnew1">&nbsp;</td>
                                        <td align="right" class="tdgridnew1"><label><B><bean:message key="label.common.GrandTotal" /> (<bean:message key="label.common.spares" /> + <bean:message key="label.common.lubes" /> + <bean:message key="label.common.Labour" /> +<bean:message key="label.common.Sublets" />)</B></label></td>
                                    <td align="right" class="tdgridnew1" style="padding-right: 35px;"><span>${jobcardDetails.totalEstimate}</span></td>
                                </tr>
                            </table></td>
                    </tr>
                </c:if>
                <c:if test="${flag eq 'ReOpenUpdate'}">
                    <form action="<%=cntxpath%>/serviceProcess.do" method="post" id="serviceForm" onsubmit="return false;">
                        <input type="hidden" name="option" value="setJobCardNoStatus" ><%--value="approveJobCard"--%>
                        <input type="hidden" name="jobCardNo" value="${jobcardDetails.jobCardNo}">
                        <input type="hidden" name="status" value="${jobcardDetails.status}" id="Status">
                        <input type="hidden" name="isWarReq"  value="${jobcardDetails.isWarReq}">
                        <tr>
                            <td align="center" colspan="10">
                                <table border="0" cellspacing="3" cellpadding="3" width="100%">

                                    <tr bgcolor="#FFFFFF" height="40">
                                        <td align="right" width="7%" >
                                            <span class="headingSpas">
                                                <label><B><bean:message key="label.common.remarks" /></B></label>
                                            </span></td>
                                        <td ><span class="headingSpas"></span>
                                            <textarea name="remarks" rows="4" id="Remarks" onblur="if(!CommentsMaxLength(this, 450)){this.value=this.value.substring(0, 450);}"></textarea>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td colspan="2" align="center" style="padding-top: 10px;">
                                            <input name="input" type="submit" value="${jobcardDetails.status}" class="headingSpas1" onclick="OpenOrReject('${jobcardDetails.status}')">
                                            <%--<input name="input" type="button" value="Approve" class="submit" onclick="approveJobCard('approve')">
                                            <input name="input" type="button" value="Reject" class="submit" onclick="approveJobCard('reject')" >--%>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </form>
                </c:if>

            </table>

        </div>
    </div>
</center>