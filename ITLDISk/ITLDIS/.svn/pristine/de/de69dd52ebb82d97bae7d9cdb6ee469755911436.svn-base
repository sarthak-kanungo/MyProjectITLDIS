<%--
    Document   : estimate.jsp
    Created on : Apr 30, 2014, 03:25:09 PM
    Author     : Avinash.Pandey
--%>
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
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DATE);
            Vector userFunctionalities = (Vector) session.getAttribute("userFun");
%>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_1.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions_1.js"></script>
<link rel="stylesheet" href="layout/css/login.css" type="text/css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css" media="screen" />
<link href="${pageContext.request.contextPath}/css/dynCalendar.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Master_290414.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css"  type="text/css"/>
<script  type="text/javascript" language="javascript">

    function Statusremarks()
    {
        $('.statusremarks').each(function(idx,val){
            val.value = val.value.replace(/\s+/g, ' ');
        });

    }
    
      
    function validate(val)
    {

        var jobcardno=document.getElementById("jobCardNo").value;
        var bayCode=new Array();
        var mechanicName=new Array();
        var inspectionBy=new Array();
        var workStarted=new Array();
        var workFinished=new Array();
        var vehicleOut=new Array();

        bayCode=document.getElementsByName("bayCode");
        mechanicName=document.getElementsByName("mechanicName");
        inspectionBy=document.getElementsByName("inspectionBy");
        workStarted=document.getElementsByName("workStarted");
        workFinished=document.getElementsByName("workFinished");
        vehicleOut=document.getElementsByName("vehicleOut");
        var tractorindate=document.getElementById("tractorindate").value;
        if(jobcardno=='')
        {
            alert(vehInfo_validation_msg);
               
            return false;
        }
        if(val)
        {
            if(workStarted[0].value.trim()==="")
            {
                alert(not_blank_validation_msg+"Work Started On");
                workStarted[0].focus();
           
                return false;
            }
            if(workFinished[0].value.trim()==="")
            {
                alert(not_blank_validation_msg+"Work Finished On");
                workFinished[0].focus();
           
                return false;
            }
            if(vehicleOut[0].value.trim()==="")
            {
                alert(not_blank_validation_msg+"Vehical Out On");
                vehicleOut[0].focus();
          
                return false;
            }
        
            var ttdate="";
            var tmon="<%=month%>";
            var tdays="<%=day%>";

            if(eval(tmon)<10)
                tmon="0"+tmon;

            if(eval(tdays)<10)
                tdays="0"+tdays;

            ttdate=<%=year%>+'/'+tmon+'/'+tdays;
            var today = new Date(ttdate);
            var wsdate = workStarted[0].value;
            var wfdate = workFinished[0].value;
            var vodate = vehicleOut[0].value;
            var vodate = vehicleOut[0].value;
            
            var wsd=wsdate.split('/');
            wsdate=wsd[2]+'/'+wsd[1]+'/'+wsd[0];
            wsdate=new Date(wsdate);

            var wfd=wfdate.split('/');
            wfdate=wfd[2]+'/'+wfd[1]+'/'+wfd[0];
            wfdate=new Date(wfdate);

            var vod=vodate.split('/');
            vodate=vod[2]+'/'+vod[1]+'/'+vod[0];
            vodate=new Date(vodate);


            var tid=tractorindate.split('/');
            var ttid=tid[2]+'/'+tid[1]+'/'+tid[0];
            tid=new Date(ttid);

            

            //current date comparision
            if(today < wsdate){
             
                //document.getElementById("Visit Date").value="";
                alert(workstart_validation_msg);
                workStarted[0].focus();
                return false;
            }
            if(today < wfdate){
                //document.getElementById("Visit Date").value="";
                alert(workfinish_validation_msg);
                workFinished[0].focus();
                return false;
            }
            if(today < vodate){
             
                //document.getElementById("Visit Date").value="";
                alert(vehicaleDt_validation_msg);
                vehicleOut[0].focus();
                return false;
            }


            //other date comparision

            if( wsdate < tid){
                alert(workstartGT_validation_msg);
                workStarted[0].focus();
                return false;
            }
            if(wfdate < wsdate){
                alert(workfinishGT_validation_msg);
                workFinished[0].focus();
                return false;
            }
            if( vodate < wfdate){
                alert(vehicalDTGT_validation_msg);
                vehicleOut[0].focus();
                return false;
            }
            

            //other min and hrs comparision


            if( wsdate.getTime() == tid.getTime()){
                if(minhrs("jobCardDateHrs","jobCardDateMins","workStartedHrs","workStartedMins","WORK STARTED ON","TRACTOR IN DATE")==false){
                    return false;
                }
            }
            if(wfdate.getTime() == wsdate.getTime()){
                if(minhrs("workStartedHrs","workStartedMins","workFinishedHrs","workFinishedMins","WORK FINISHED ON","WORK STARTED ON")==false){
                    return false;
                }
            }
            if( vodate.getTime() == wfdate.getTime()){
                if(minhrs("workFinishedHrs","workFinishedMins","vehicleOutHrs","vehicleOutMins","VEHICLE OUT ON","WORK FINISHED ON")==false){
                    return false;
                }
            }


            for(var k=0;k<bayCode.length;k++){
                if(bayCode[0].value.trim()==="")
                {
                    alert(not_blank_dropdown_validation_msg+"Bay");
                    bayCode[0].focus();
              
                    return false;
                }
                if(mechanicName[0].value.trim()==="")
                {
                    alert(not_blank_dropdown_validation_msg+"Mechanic Name");
                    mechanicName[0].focus();
             
                    return false;
                }
                if(inspectionBy[0].value.trim()==="")
                {
                    alert(not_blank_dropdown_validation_msg+"Final Inspection By");
                    inspectionBy[0].focus();
              
                    return false;
                }
                
            }
            document.getElementById("actiontype").value="saveandclose";
            var r=confirm(closeJC_validation_msg+document.getElementById("jobCardNo").value+"?");
            if (r==true)
            {

                document.getElementById("form1").submit();

            }
            else{
                return false;
            }

        }
        else
        {
            document.getElementById("actiontype").value="save";
        }


        Statusremarks();
        document.getElementById("form1").submit();

    }

    function validateForClose(val){
      
        if(document.getElementById("payeename").value==""){
            alert(payee_validation_msg);
            return false;
        }

        var flag='${wtyflag}';

        var wtyapp='${serviceform.warrantyApplicable}'
       
        if(flag=="exist" && wtyapp=="N"){
            alert(warrantyapp_validation_msg);
            return false;
        }

        var checkactiontaken='${checkactiontaken}'

        if(checkactiontaken=="exist" ){
            alert(checkactiontaken_validation_msg);
            return false;
        }
        
        var checkComplaintStatus='${checkComplaintStatus}'

            if(checkComplaintStatus=="notexist" ){
                alert(checkComplaintStatus_validation_msg);
                return false;
            }



        validate(val);

             
    }


    function minhrs(hrs1,min1,hrs2,min2,name1,name2){

        min1= document.getElementById(min1)
        min2= document.getElementById(min2)
        hrs1= document.getElementById(hrs1)
        hrs2= document.getElementById(hrs2)

        if(eval(hrs2.value)<=eval(hrs1.value)){
            if(eval(hrs2.value)==eval(hrs1.value)){
                if(eval(min2.value)<=eval(min1.value)){
                    if(eval(min2.value)==eval(min1.value)){
                        alert(name1+min_validation_msg+name2);
                        min2.focus();
                    }
                    else{
                        alert(name1+minGT_validation_msg+name2);
                        min2.focus();
                    }
                    return false;
                }
            }
            else{
                alert(name1+hrGT_validation_msg+name2);
                hrs2.focus();
                return false;
            }
        }
        return true;
    }

    function caMaxLength(ca, MaxLen)
    {
        return (ca.value.length <= MaxLen);
    }

        $(document).ready(function () {

            $(function() {
                var ttdate="";
                var tmon="<%=month%>";
                var tdays="<%=day%>";
                if(eval(tmon)<10)
                    tmon="0"+tmon;

                if(eval(tdays)<10)
                tdays="0"+tdays;

                ttdate=+tdays+'/'+tmon+ '/'+<%=year%>;

                //var today = new Date(ttdate);

                var tractorindate=document.getElementById("tractorindate").value;
                var workStarted=document.getElementById("workStarted").value;
                var workFinished=document.getElementById("workFinished").value;
                var vehicleOut=document.getElementById("vehicleOut").value;

                if(tractorindate!=""){
                    $( ".datepicker" ).datepicker();
                }
                if(workStarted!=""){
                    $( ".datepicker" ).datepicker();
                }
                if(workFinished!=""){
                    $( ".datepicker" ).datepicker();
                }
                if(vehicleOut!=""){
                    $( ".datepicker" ).datepicker();
                }else{
                  $( ".datepicker" ).datepicker( "setDate", ttdate);
                }
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


            <h1 ><span class="formLabel"> <bean:message key="label.common.status" /></span></h1>



            <c:if test="${serviceform.jobcardview eq 'true' }">
                <%@ include file="topbandshow.jsp" %>
            </c:if>


            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >
                <tr><td>
                        <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1">
                            <tr align="center" valign="middle" class="formHeader">





                                <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initVehicleInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.vehicleinfo" /></a></label></td>
                                <td ><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initCustomerInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.customerinfo" /></a></label></td>
                                <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStandardChecks&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.standardcheck" /></a></label></td>
                                <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initComplaint&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.complaint" /></a></label></td>
                                <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initEstimate&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.estimate" /></a></label></td>
                                <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initActual&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.actuals" /></a></label></td>
                                <td class="formSelectedHeader"><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStatus&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="linkGreyBg"><bean:message key="label.common.status" /></a></label></td>
                                <c:if test="${showHistory ne 'Y'}">
                                    <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=init_vehicle_History&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.history" /></a></label></td>
                                </c:if>       


                            </tr>
                        </table>
                    </td></tr>
                <tr><td>
                        <form name="form1" id="form1" method="post" action="<%=cntxpath%>/serviceCreateJC.do?option=manageStatus&jctype=${serviceform.jctype}" onsubmit="return false;">
                            <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/serviceAction.do?option=initStatus'>MANAGE JOB CARD</a>@@ADD ACTION"/>
                            <input type="hidden" name="jobCardNo" id="jobCardNo" value="${serviceform.jobCardNo}"/>
                            <input type="hidden" name="vinid"  value="${serviceform.vinid}"/>
                            <input type="hidden" name="jobId" value="${serviceform.jobId}">
                            <table width="100%"  border="0" cellspacing="2">



                                <tr>

                                    <td valign="top" width ="50%" height="170" class="LiteBorder">
                                        <table width="100%" border="0" align="center" cellpadding="2" cellspacing="4"   >
                                            <tbody>
                                                <tr class="formCatHeading">
                                                    <td colspan="4" align="center"><label><bean:message key="label.common.payeeinfo" /></label></td>
                                                </tr>
                                                <tr>


                                                    <td  align="right" width="12%">
                                                        <label><bean:message key="label.common.CustomerName" /></label></td>
                                                    <td align="left" width="12%" style="white-space: nowrap">: ${serviceform.customerName} <input type="hidden" id="customername" >
                                                    </td>

                                                    <td  align="right" width="12%">
                                                        <label><bean:message key="label.common.JobcardPayeeName" /></label></td>
                                                    <td align="left" width="12%" style="white-space: nowrap">: ${serviceform.payeeName} <input type="hidden" id="payeename" value="${serviceform.payeeName}"></td>



                                                </tr>
                                                <tr>
                                                    <td align="right" width="12%"> <label><bean:message key="label.common.mobilephone" /> </label></td>
                                                    <td align="left" width="12%">: ${serviceform.mobilePhone}<input type="hidden" id="mobileno" ></td>

                                                    <td align="right" width="12%"> <label><bean:message key="label.common.mobilephone" /> </label></td>
                                                    <td align="left" width="12%">: ${serviceform.payeeMobilePhone}<input type="hidden" id="mobileno" ></td>
                                                </tr>
                                                <%--   <tr style="height: 8px"></tr>--%>
                                                <tr>
                                                    <td valign="top" width ="50%" colspan="4">
                                                        <table width="100%" border="0" align="center" cellpadding="2" cellspacing="4"  >
                                                            <td align="right" width="20%"> <label>Remarks  :<%--<bean:message key="label.common.mobilephone" />--%> </label></td>
                                                            <td align="left" colspan="3" width="80%" style="text-align: left;">
                                                                <textarea class="statusremarks" rows="3" name="remarks" onkeypress="return caMaxLength(this, 250);">${serviceform.remarks}</textarea>
                                                            </td>
                                                        </table>
                                                    </td>
                                                </tr>

                                            </tbody>
                                        </table>



                                    </td>
                                    <td valign="top" width ="50%" height="170" class="LiteBorder">
                                        <table width="100%" border="0" align="center" cellpadding="2" cellspacing="4"  >
                                            <tbody>
                                                <tr class="formCatHeading">
                                                    <td colspan="4" align="center"><label> <bean:message key="label.common.actuals" /></label></td>
                                                </tr>
                                                <tr>
                                                    <%-- #label.common.totalpartvalue,label.common.totallubesvalue,label.common.othercharges,
                                                    label.common.totallabourcharge,label.common.totalactual --%>

                                                    <td width="41%"  align="right" >
                                                        <label><bean:message key="label.common.totalpartvalue" /></label></td>
                                                    <td width="18%" align="left" >
                                                        : ${serviceform.totalPartsValue}
                                                    </td>
                                                    <td width="41%" align="center" ></td></tr>
                                                <tr>
                                                    <td align="right"><label><bean:message key="label.common.totallubesvalue" /></label></td>
                                                    <td width="18%" align="left" >
                                                        : ${serviceform.totalLubesValue}
                                                    </td>
                                                    <td rowspan="2" align="center" valign="top"></td>
                                                </tr>
                                                <tr>
                                                    <td  align="right" ><label><bean:message key="label.common.othercharges" /></label></td>
                                                    <td width="18%" align="left" >
                                                        : ${serviceform.totalOtherCharges}
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td  align="right" ><label><bean:message key="label.common.totallabourcharge" /></label></td>
                                                    <td width="18%" align="left" >
                                                        : ${serviceform.totalLabourCharges}
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td  align="right" ><label><bean:message key="label.common.totaldiscount" /></label></td>
                                                    <td width="18%" align="left" >
                                                        : ${serviceform.totaldiscount}
                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td  align="right" ><label><bean:message key="label.common.totalactual" /></label></td>
                                                    <td width="18%" align="left" >
                                                        : ${serviceform.totalactuals}
                                                    </td>
                                                </tr>
                                            </tbody>
                                        </table></td>
                                </tr>



















                                <tr >

                                    <td valign="top" width ="50%"><table width="100%" border="0" align="center" cellpadding="2" cellspacing="4" class="LiteBorder"  >
                                            <tbody>
                                                <tr class="formCatHeading">
                                                    <td colspan="3" align="center"><bean:message key="label.common.timeinfo" /></td>
                                                </tr>


                                                <tr>
                                                    <td  align="right" ><label><bean:message key="label.common.tractorindate" /></label></td>
                                                    <td align="left" ><input name="tractorindate" id="tractorindate" type="text"  value="${serviceform.jobCardDate}" readonly/>

                                                        <select name="jobCardDateHrs"  id="jobCardDateHrs" style="width:45px !important " disabled>
                                                            <c:set var="i" value="0"></c:set>
                                                            <c:forEach var="i" begin="0" end="23">
                                                                <c:set var="zero" value="0${i}"></c:set>
                                                                <c:if test="${i lt 10 }">
                                                                    <c:if test="${serviceform.hrs eq (zero) }">
                                                                        <option value="0${i}" selected>0${i}</option>
                                                                    </c:if>
                                                                    <c:if test="${serviceform.hrs ne (zero+i) }">
                                                                        <option value="0${i}">0${i}</option>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:if test="${i gt 10 or i eq 10}">
                                                                    <c:if test="${serviceform.hrs eq i }">
                                                                        <option value="${i}" selected>${i}</option>
                                                                    </c:if>
                                                                    <c:if test="${serviceform.hrs ne i }">
                                                                        <option value="${i}" >${i}</option>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:set var="i" value="${i + 1 }" />
                                                            </c:forEach>
                                                        </select>
                                                        :
                                                        <select name="jobCardDateMins" id="jobCardDateMins"  style="width:45px !important " disabled>
                                                            <c:set var="i" value="0"></c:set>
                                                            <c:forEach  begin="0" end="11">
                                                                <c:if test="${i lt 10 }">
                                                                    <c:set var="zero" value="0${i}"></c:set>
                                                                    <c:if test="${serviceform.min eq zero }">
                                                                        <option value="0${i}" selected>0${i}</option>
                                                                    </c:if>
                                                                    <c:if test="${serviceform.min ne ('0'+i) }">
                                                                        <option value="0${i}">0${i}</option>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:if test="${i gt 10 or i eq 10}">
                                                                    <c:if test="${serviceform.min eq i }">
                                                                        <option value="${i}" selected>${i}</option>
                                                                    </c:if>
                                                                    <c:if test="${serviceform.workStartedMins ne i }">
                                                                        <option value="${i}" >${i}</option>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:set var="i" value="${i + 5 }" />
                                                            </c:forEach>
                                                        </select></td>
                                                </tr>






                                                <tr>
                                                    <td  align="right" >
                                                        <label><bean:message key="label.common.workstartedon" /></label>&nbsp;<span class="mandatory">*</span></td>
                                                    <td align="left" > <input name="workStarted" type="text" id="workStarted" class="datepicker" value="${serviceform.workStarted}">
                                                        <select name="workStartedHrs"  id="workStartedHrs" style="width:45px !important ">
                                                            <c:set var="i" value="0"></c:set>
                                                            <c:forEach var="i" begin="0" end="23">
                                                                <c:if test="${i lt 10 }">
                                                                    <c:set var="zero" value="0${i}"></c:set>
                                                                    <c:if test="${serviceform.workStartedHrs eq (zero) }">
                                                                        <option value="0${i}" selected>0${i}</option>
                                                                    </c:if>
                                                                    <c:if test="${serviceform.workStartedHrs ne ('0'+i) }">
                                                                        <option value="0${i}">0${i}</option>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:if test="${i gt 10 or i eq 10}">
                                                                    <c:if test="${serviceform.workStartedHrs eq i }">
                                                                        <option value="${i}" selected>${i}</option>
                                                                    </c:if>
                                                                    <c:if test="${serviceform.workStartedHrs ne i }">
                                                                        <option value="${i}" >${i}</option>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:set var="i" value="${i + 1 }" />
                                                            </c:forEach>
                                                        </select>
                                                        :
                                                        <select name="workStartedMins"  id="workStartedMins" style="width:45px !important ">
                                                            <c:set var="i" value="0"></c:set>
                                                            <c:forEach  begin="0" end="11">
                                                                <c:if test="${i lt 10 }">
                                                                    <c:set var="zero" value="0${i}"></c:set>
                                                                    <c:if test="${serviceform.workStartedMins eq (zero) }">
                                                                        <option value="0${i}" selected>0${i}</option>
                                                                    </c:if>
                                                                    <c:if test="${serviceform.workStartedMins ne ('0'+i) }">
                                                                        <option value="0${i}">0${i}</option>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:if test="${i gt 10 or i eq 10}">
                                                                    <c:if test="${serviceform.workStartedMins eq i }">
                                                                        <option value="${i}" selected>${i}</option>
                                                                    </c:if>
                                                                    <c:if test="${serviceform.workStartedMins ne i }">
                                                                        <option value="${i}" >${i}</option>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:set var="i" value="${i + 5 }" />
                                                            </c:forEach>
                                                        </select>
                                                </tr>
                                                <tr>
                                                    <td align="right"><label><bean:message key="label.common.workfinishedon" /> </label>&nbsp;<span class="mandatory">*</span></td>
                                                    <td align="left">
                                                        <input name="workFinished" id="workFinished" type="text" class="datepicker" value="${serviceform.workFinished}"/>
                                                        <select name="workFinishedHrs" id="workFinishedHrs"  style="width:45px !important ">
                                                            <c:set var="i" value="0"></c:set>
                                                            <c:forEach var="i" begin="0" end="23">
                                                                <c:if test="${i lt 10 }">
                                                                    <c:set var="zero" value="0${i}"></c:set>
                                                                    <c:if test="${serviceform.workFinishedHrs eq (zero) }">
                                                                        <option value="0${i}" selected>0${i}</option>
                                                                    </c:if>
                                                                    <c:if test="${serviceform.workFinishedHrs ne ('0'+i) }">
                                                                        <option value="0${i}">0${i}</option>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:if test="${i gt 10 or i eq 10}">
                                                                    <c:if test="${serviceform.workFinishedHrs eq i }">
                                                                        <option value="${i}" selected>${i}</option>
                                                                    </c:if>
                                                                    <c:if test="${serviceform.workFinishedHrs ne i }">
                                                                        <option value="${i}" >${i}</option>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:set var="i" value="${i + 1 }" />
                                                            </c:forEach>
                                                        </select>
                                                        :
                                                        <select name="workFinishedMins" id="workFinishedMins"  style="width:45px !important ">
                                                            <c:set var="i" value="0"></c:set>
                                                            <c:forEach  begin="0" end="11">
                                                                <c:if test="${i lt 10 }">
                                                                    <c:set var="zero" value="0${i}"></c:set>
                                                                    <c:if test="${serviceform.workFinishedMins eq (zero) }">
                                                                        <option value="0${i}" selected>0${i}</option>
                                                                    </c:if>
                                                                    <c:if test="${serviceform.workFinishedMins ne ('0'+i) }">
                                                                        <option value="0${i}">0${i}</option>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:if test="${i gt 10 or i eq 10}">
                                                                    <c:if test="${serviceform.workFinishedMins eq i }">
                                                                        <option value="${i}" selected>${i}</option>
                                                                    </c:if>
                                                                    <c:if test="${serviceform.workFinishedMins ne i }">
                                                                        <option value="${i}" >${i}</option>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:set var="i" value="${i + 5 }" />
                                                            </c:forEach>
                                                        </select></td>
                                                </tr>
                                                <tr>
                                                    <td  align="right" ><label><bean:message key="label.common.vehicleouton" /></label>&nbsp;<span class="mandatory">*</span></td>
                                                    <td align="left" ><input name="vehicleOut" id="vehicleOut" type="text" class="datepicker" value="${serviceform.vehicleOut}"/>
                                                        <select name="vehicleOutHrs"  id="vehicleOutHrs" style="width:45px !important ">
                                                            <c:set var="i" value="0"></c:set>
                                                            <c:forEach var="i" begin="0" end="23">
                                                                <c:if test="${i lt 10 }">
                                                                    <c:set var="zero" value="0${i}"></c:set>
                                                                    <c:if test="${serviceform.vehicleOutHrs eq (zero) }">
                                                                        <option value="0${i}" selected>0${i}</option>
                                                                    </c:if>
                                                                    <c:if test="${serviceform.vehicleOutHrs ne ('0'+i) }">
                                                                        <option value="0${i}">0${i}</option>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:if test="${i gt 10 or i eq 10}">
                                                                    <c:if test="${serviceform.vehicleOutHrs eq i }">
                                                                        <option value="${i}" selected>${i}</option>
                                                                    </c:if>
                                                                    <c:if test="${serviceform.vehicleOutHrs ne i }">
                                                                        <option value="${i}" >${i}</option>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:set var="i" value="${i + 1 }" />
                                                            </c:forEach>
                                                        </select>
                                                        :
                                                        <select name="vehicleOutMins" id="vehicleOutMins"  style="width:45px !important ">
                                                            <c:set var="i" value="0"></c:set>
                                                            <c:forEach  begin="0" end="11">
                                                                <c:if test="${i lt 10 }">
                                                                    <c:set var="zero" value="0${i}"></c:set>
                                                                    <c:if test="${serviceform.vehicleOutMins eq (zero) }">
                                                                        <option value="0${i}" selected>0${i}</option>
                                                                    </c:if>
                                                                    <c:if test="${serviceform.vehicleOutMins ne ('0'+i) }">
                                                                        <option value="0${i}">0${i}</option>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:if test="${i gt 10 or i eq 10}">
                                                                    <c:if test="${serviceform.vehicleOutMins eq i }">
                                                                        <option value="${i}" selected>${i}</option>
                                                                    </c:if>
                                                                    <c:if test="${serviceform.vehicleOutMins ne i }">
                                                                        <option value="${i}" >${i}</option>
                                                                    </c:if>
                                                                </c:if>
                                                                <c:set var="i" value="${i + 5 }" />
                                                            </c:forEach>
                                                        </select></td>
                                                </tr>
                                            </tbody>
                                        </table></td>
                                    <td valign="top" width ="50%"><table width="100%" border="0" align="center" cellpadding="2" cellspacing="4" class="LiteBorder"  >
                                            <tbody>
                                                <tr class="formCatHeading">
                                                    <td colspan="2" align="center"> <bean:message key="label.common.machanicinfo" /></td>
                                                </tr>
                                                <tr>
                                                    <td width="30%"  align="right" >
                                                        <label><bean:message key="label.common.bay" /></label>&nbsp;<span class="mandatory">*</span></td>
                                                    <td width="60%" align="left" >
                                                        <select name="bayCode"   id=bayCode1" style="width:150px !important" onchange="">
                                                            <option value="">--Select--</option>
                                                            <c:forEach items="${bayList}" var="dataList">
                                                                <c:choose>
                                                                    <c:when test="${serviceform.bayCode eq dataList.value }">
                                                                        <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                    </td>

                                                </tr>
                                                <tr>
                                                    <td align="right"><label><bean:message key="label.common.machanicname" /></label>&nbsp;<span class="mandatory">*</span></td>
                                                    <td align="left">
                                                        <select name="mechanicName"   id=mechanicName1" style="width:150px !important" onchange="">
                                                            <option value="">--Select--</option>
                                                            <c:forEach items="${mechanicList}" var="dataList">
                                                                <c:choose>
                                                                    <c:when test="${serviceform.mechanicName eq dataList.value }">
                                                                        <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                    </td>

                                                </tr>
                                                <tr>
                                                    <td  align="right" ><label><bean:message key="label.common.finalinspectionby" /></label>&nbsp;<span class="mandatory">*</span></td>
                                                    <td align="left" >
                                                        <select name="inspectionBy"   id=inspectionBy1" style="width:150px !important" onchange="">
                                                            <option value="">--Select--</option>
                                                            <c:forEach items="${inspectionByList}" var="dataList">
                                                                <c:choose>
                                                                    <c:when test="${serviceform.inspectionBy eq dataList.value }">
                                                                        <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                    </td>

                                                </tr>
                                                <tr>
                                                    <td  align="right" ><label><bean:message key="label.service.reasonForDealy" /></label></td>
                                                    <td align="left"  >
                                                        <%--  <textarea rows="2"  name="reasonForDealy" onkeypress="return caMaxLength(this, 250);">${serviceform.reasonForDealy}</textarea>--%>
                                                        <select name="reasonForDealy"   id=reasonForDealy" style="width:150px !important" onchange="">
                                                            <option value="">--Select--</option>
                                                            <c:forEach items="${delayReasonList}" var="dataList">
                                                                <c:choose>
                                                                    <c:when test="${serviceform.reasonForDealy eq dataList.value }">
                                                                        <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option value='${dataList.value}' title='${dataList.label}'>${dataList.label}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                    </td>

                                                </tr>

                                            </tbody>
                                        </table></td>
                                </tr>

                                <tr align="right"><%if (userFunctionalities.contains("608")) {%> <c:if test="${serviceform.status eq 'OPEN'}">
                                    <input name="actiontype" id="actiontype" type="hidden" value="save">
                                    <td colspan="2"> <input name="input" type="button" value="Save" style="float:none; " onclick="validate(false)"/>
                                        <input name="input" type="button" value="Save and close" style="float:none; " onclick="validateForClose(true)"/>
                                    </td></c:if><%}%>
                                </tr>
                            </table>
                        </form></td>
                </tr>
            </table>
        </div></div></center>