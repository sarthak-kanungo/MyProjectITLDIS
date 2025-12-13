<%--
    Document   : v_update_enquiry
    Created on : Jun 6, 2014, 9:13:22 AM
    Author     : umesh.batra
--%>

<%@page import="beans.serviceForm"%>
<%--<%@page import="org.apache.jasper.compiler.Node.UseBean"%>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page import="java.util.*,beans.masterForm" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>


<jsp:useBean id="serviceForm" scope="request" class="beans.serviceForm"></jsp:useBean>
<script language="javascript" src="${pageContext.request.contextPath}/js/date.js"></script>

<link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_test.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/format_test.css" type="text/css" />
<script src="${pageContext.request.contextPath}/js/javascript.js" type="text/javascript"> </script>

<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/Master_290414.css" type="text/css" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css"/>
        <script src="${pageContext.request.contextPath}/js/dhtmlgoodies_calendar.js"></script>

        <script src="${pageContext.request.contextPath}/js/jquery-1.10.2.js"></script>
        <script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
        <script>

            $(function() {
                $( ".datepicker" ).datepicker();
            });

            function showMeDetails(id)
            {
                document.getElementById('all').display='block';
            }

            function showMeDetail1(objectId)
            {
                var styleObject=getStyleObject(objectId)

                if(styleObject.display=="block")
                {
                    changeObjectVisibility(objectId, 'none');
                    document.getElementById('buttonexpand').src=contextPath+"plus.png";
                }
                else
                {
                    changeObjectVisibility(objectId, 'block');
                    document.getElementById('buttonexpand').src=contextPath+"minus.png";
                }
            }
            function changeObjectVisibility(objectId, newVisibility)
            {
                var styleObject = getStyleObject(objectId);
                if(styleObject)
                {
                    styleObject.display = newVisibility;
                }
            }

            function caMaxLength(ca, MaxLen)
            {
                return (ca.value.length <= MaxLen);
            }

          
            <%-- function closedEnquiry(eProdId,enquiryNo){
                 if(document.getElementById('ramarks').value==''){
                     document.getElementById("ramarks").focus();
                     document.getElementById("msg_saveFAILED").style.display = "";
                     document.getElementById('msg_saveFAILED').innerHTML='Please enter value in remarks field';
                     return false;
                 }
                 window.location.href = "enquiryManagement.do?option=closeEnquiry&enqProdId="+eProdId+"&enquiryNo="+enquiryNo+"";
             }--%>

            
                 function validate(){

                     if(document.getElementById("servicetype Id").value=='') {
                         document.getElementById('msg_saveFAILED').innerHTML='Please Select value in Service Type field';
                         document.getElementById("servicetype Id").focus();
                         return false;
                     }

            
                     if (document.getElementById('call Date').value==""){
                         document.getElementById("call Date").focus();
                         document.getElementById("msg_saveFAILED").style.display = "";
                         document.getElementById("msg_saveFAILED").innerHTML="Please Select value in call Date field";
                         return false;
                     }
                     if (document.getElementById('doorstepServiceRequired ID').value==""){
                         document.getElementById("doorstepServiceRequired ID").focus();
                         document.getElementById("msg_saveFAILED").style.display = "";
                         document.getElementById("msg_saveFAILED").innerHTML="Please Select value in Door step Service Required field";
                         return false;
                     }
                    
                     if (document.getElementById('followupStatus ID').value==""){
                         document.getElementById("followupStatus ID").focus();
                         document.getElementById("msg_saveFAILED").style.display = "";
                         document.getElementById("msg_saveFAILED").innerHTML="Please Enter value in Follow Up Status field.";
                         return false;
                     }


                     var today1 = new Date();

 
                     var currdate=today1.setDate(today1.getDate());
                     var promdate= document.getElementById('Promised Date').value;
                     var promiseddate= new Date(promdate.split("/")[2],promdate.split("/")[1]-1,promdate.split("/")[0]);
                     var today=today1.setDate(today1.getDate()-1);
                     var followdate= document.getElementById('nextFollowUpDate Id').value;
                     var followupdate = new Date(followdate.split("/")[2],followdate.split("/")[1]-1,followdate.split("/")[0]);
                     if (document.getElementById("followupStatus ID").value=="PROMISED"  ){
                         if(promdate==""){
                             document.getElementById("Promised Date").focus();
                             document.getElementById("msg_saveFAILED").style.display = "";
                             document.getElementById("msg_saveFAILED").innerHTML="Please Enter value in Promised Date field.";
                             return false;
                         }
                         else if((promiseddate < today))
                         {
                             
                             document.getElementById('Promised Date').value="";
                             document.getElementById('Promised Date').focus();
                             document.getElementById("msg_saveFAILED").innerHTML = "Promised Date must be greater than or equal to Current Date.";
                             return false;
                         }else if(followdate==""){
                             document.getElementById('nextFollowUpDate Id').value="";
                             document.getElementById("nextFollowUpDate Id").focus();
                             document.getElementById("msg_saveFAILED").style.display = "";
                             document.getElementById("msg_saveFAILED").innerHTML="Please Enter value in Next Follow Up Date field.";
                             return false;
                         }
                         else if((followupdate < today) )
                         {

                             document.getElementById('nextFollowUpDate Id').value="";
                             document.getElementById('nextFollowUpDate Id').focus();
                             document.getElementById("msg_saveFAILED").innerHTML = "Next Follow Up Date must be greater than or equal to Current Date.";
                             return false;
                         }
                         else if((followupdate > promiseddate) )
                         {

                             document.getElementById('nextFollowUpDate Id').value="";
                             document.getElementById('nextFollowUpDate Id').focus();
                             document.getElementById("msg_saveFAILED").innerHTML = " Follow Up Date can not be greater than  Promised Date.";
                             return false;
                         }
                         
                        
                     }
                     else if (document.getElementById("followupStatus ID").value=="DECLINED"  ){


                         if(followdate!=""){
                             if((followupdate < today) )
                             {

                                 document.getElementById('nextFollowUpDate Id').value="";
                                 document.getElementById('nextFollowUpDate Id').focus();
                                 document.getElementById("msg_saveFAILED").innerHTML = "Next Follow Up Date must be greater than or equal to Current Date.";
                                 return false;
                             }
                         }

                         else if(promdate!=""){
                             if((promiseddate < today))
                             {

                                 document.getElementById('Promised Date').value="";
                                 document.getElementById('Promised Date').focus();
                                 document.getElementById("msg_saveFAILED").innerHTML = "Promised Date must be greater than or equal to Current Date.";
                                 return false;
                             }else if((followupdate > promiseddate) )
                             {

                                 document.getElementById('nextFollowUpDate Id').value="";
                                 document.getElementById('nextFollowUpDate Id').focus();
                                 document.getElementById("msg_saveFAILED").innerHTML = " Follow Up Date can not be greater than  Promised Date.";
                                 return false;
                             }
                         }
                     }
                     else if (document.getElementById("followupStatus ID").value=="RECALL"  ){


                         if(promdate!=""){
                             if((promiseddate < today))
                             {

                                 document.getElementById('Promised Date').value="";
                                 document.getElementById('Promised Date').focus();
                                 document.getElementById("msg_saveFAILED").innerHTML = "Promised Date must be greater than or equal to Current Date.";
                                 return false;
                             }else if((followupdate > promiseddate) )
                             {

                                 document.getElementById('nextFollowUpDate Id').value="";
                                 document.getElementById('nextFollowUpDate Id').focus();
                                 document.getElementById("msg_saveFAILED").innerHTML = " Follow Up Date can not be greater than  Promised Date.";
                                 return false;
                             }
                         }
                         else if(followdate==""){
                             document.getElementById('nextFollowUpDate Id').value="";
                             document.getElementById("nextFollowUpDate Id").focus();
                             document.getElementById("msg_saveFAILED").style.display = "";
                             document.getElementById("msg_saveFAILED").innerHTML="Please Enter value in Next Follow Up Date field.";
                             return false;
                         }
                         else if((followupdate < today) )
                         {

                             document.getElementById('nextFollowUpDate Id').value="";
                             document.getElementById('nextFollowUpDate Id').focus();
                             document.getElementById("msg_saveFAILED").innerHTML = "Next Follow Up Date must be greater than or equal to Current Date.";
                             return false;
                         }
                        
                         
                     }

                     if(document.getElementById('calldesc Id').value==''){
                         document.getElementById("calldesc Id").focus();
                         document.getElementById("msg_saveFAILED").style.display = "";
                         document.getElementById('msg_saveFAILED').innerHTML='Please enter value in call Description field';
                         return false;
                     }
                     
           

                     document.getElementById("Button2").disabled=true;
                     document.getElementById("Button3").disabled=true;
                     document.getElementById("form1").submit();
                 }
                 //After phase1 completion by anand
                 function checkExpectedDate(expDate){
   
                     dateValue = trim(expDate.value);
                     var x =dateValue;
                     var a = x.split('/');
                     var date = new Date (a[2], a[1] - 1,a[0]);//using a[1]-1 since Date object has month from 0-11
                     var hotDay = new Date();
                     hotDay.setDate(hotDay.getDate()+29);
                     var warmDay = new Date();
                     warmDay.setDate(hotDay.getDate()+59);
                     var nextDay = new Date();
                     var followDate = new Date();
                     followDate.setDate(followDate.getDate()+7);

                     if(date<hotDay){
                         if(date < followDate){
                             document.getElementById("enquirytypeid").value='1';
                             document.getElementById("followup_date").value=dateValue;
                         }else{
                             nextDay.setDate(nextDay.getDate()+7);
                             var dd = nextDay.getDate();
                             var mm = nextDay.getMonth()+1; //January is 0!
                             var yyyy = nextDay.getFullYear();
                             if(dd < 10)
                             {
                                 dd = '0'+ dd;
                             }
                             if(mm < 10)
                             {
                                 mm = '0' + mm;
                             }
                             var fromdate1 = dd+'/'+mm+'/'+yyyy

                             document.getElementById("enquirytypeid").value='1';
                             document.getElementById("followup_date").value=fromdate1;

                         }
                     }
                     else if(date<warmDay){
                         nextDay.setDate(nextDay.getDate()+14);
                         var dd = nextDay.getDate();
                         var mm = nextDay.getMonth()+1; //January is 0!
                         var yyyy = nextDay.getFullYear();
                         if(dd < 10)
                         {
                             dd = '0'+ dd;
                         }
                         if(mm < 10)
                         {
                             mm = '0' + mm;
                         }
                         var fromdate1 = dd+'/'+mm+'/'+yyyy

                         document.getElementById("enquirytypeid").value='2';
                         document.getElementById("followup_date").value=fromdate1;
                     }
                     else {
                         nextDay.setDate(nextDay.getDate()+29);
                         var dd = nextDay.getDate();
                         var mm = nextDay.getMonth()+1; //January is 0!
                         var yyyy = nextDay.getFullYear();
                         if(dd < 10)
                         {
                             dd = '0'+ dd;
                         }
                         if(mm < 10)
                         {
                             mm = '0' + mm;
                         }
                         var fromdate1 = dd+'/'+mm+'/'+yyyy

                         document.getElementById("enquirytypeid").value='3';
                         document.getElementById("followup_date").value=fromdate1;

                     }

                 }
                 function backpage(){
                     window.location.href="<%=cntxpath%>/serviceProcess.do?option=initsercviceReminder";
                 }
                 function mandatoryCheck(val){
                     if(val=='PROMISED'){
                         document.getElementById("proID").style.display="block";
                         document.getElementById("proID1").style.display="none";
                         document.getElementById("nextFollowID").style.display="block";
                         document.getElementById("nextFollowID1").style.display="none";
                     }else if(val=='DECLINED'){
                         document.getElementById("proID").style.display="none";
                         document.getElementById("proID1").style.display="block";
                         document.getElementById("nextFollowID").style.display="none";
                         document.getElementById("nextFollowID1").style.display="block";
                     }else if(val=='RECALL'){
                         document.getElementById("proID").style.display="none";
                         document.getElementById("proID1").style.display="block";
                         document.getElementById("nextFollowID").style.display="block";
                         document.getElementById("nextFollowID1").style.display="none";
                     }
                     
                 }
        </script>
    </head>
    <div class="contentmain1">
        <div class="breadcrumb_container">
            <ul>
                <c:choose>
                    <c:when test="${DashdueFollowUpEnq eq 'Y'}">
                        <li class="breadcrumb_link"><a href='<%=cntxpath%>/login.do?option=homePage'><bean:message key="label.common.dashboard" /></a></li>
                        <li class="breadcrumb_link"><a href='<%=cntxpath%>/serviceProcess.do?option=initsercviceReminder'><font class="hText"><bean:message key="label.common.DashBoardDueEnqFollowUp"/></font></a></li>
                        <li class="breadcrumb_link"><span class="hText"><bean:message key="label.common.Followup" /></span></li>
                    </c:when>
                    <c:otherwise>
                        <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link></li>
                        <li class="breadcrumb_link"><a href='<%=cntxpath%>/serviceProcess.do?option=initsercviceReminder'><font class="hText"><bean:message key="label.common.serviceReminder" /></font></a></li>
                        <li class="breadcrumb_link"><span class="hText"><bean:message key="label.common.Followup" /></span></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
        <div class="message" id="msg_saveFAILED"></div>
        <center>
            <div class="content_right1">
                <div class="con_slidediv1" style="position: relative;width: 100%">
                    <h1><bean:message key="label.common.Followup" /></h1>
                    <form action="<%=cntxpath%>/serviceProcess.do?option=updateFollowUp" method="post" id="form1" onsubmit="return false">
                        <table  width="100%"  cellpadding="5" cellspacing="5"  border="0" align="center" class="LiteBorder">
                            <tbody>
                                <tr>
                                    <td colspan="4">
                                        <table width="100%" border="0" align="center" cellpadding="2" cellspacing="4" class="LiteBorder"  >
                                            <input type="hidden" name="upperLink" class="hText" value="<a href = '<%=cntxpath%>/initService'><bean:message key="label.common.Service" /></a>@@<a href = '<%=cntxpath%>/serviceProcess.do?option=initsercviceReminder' ><bean:message key="label.common.serviceReminder"/></a>@@<bean:message key="label.common.Followup" />"/>
                                            <input name="scheduleID" type="hidden" value="${serviceForm.scheduleID}"/>
                                            <tbody>
                                                <tr class="formCatHeading">
                                                    <td colspan="6"><label><strong><bean:message key="label.common.followupdetails" /></strong></label></td>
                                                </tr>
                                                <tr >


                                                    <td style="padding-right: 10px" align="right"  width="30%" class="headingSpas" >
                                                        <bean:message key="label.common.servicetype" /><span class="mandatory" >*</span></td>
                                                    <td  align="left" width="20%" class="headingSpas">
                                                        <select id="servicetype Id" name="serviceType" class="headingSpas" style="width: 200px!important">
                                                            <option value=""><bean:message key="label.common.selectoption" /></option>
                                                            <c:forEach items="${serviceTypeIdList}" var="serviceTypeId">
                                                                <c:choose>
                                                                    <c:when test="${serviceTypeId.value eq serviceForm.serviceType}">
                                                                        <option selected value="${serviceTypeId.value}" title="${serviceTypeId.label}">${serviceTypeId.label}</option>
                                                                    </c:when>
                                                                    <c:otherwise>
                                                                        <option  value="${serviceTypeId.value}" title="${serviceTypeId.label}">${serviceTypeId.label}</option>
                                                                    </c:otherwise>
                                                                </c:choose>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                    <%--<td  align="left" width="50%" class="headingSpas" >
                                                        &nbsp;</td>--%>
                                                    <td style="padding-right: 10px" align="right"  width="20%"  class="headingSpas" >
                                                        <bean:message key="label.common.calldate" /></td>
                                                    <td  align="left" width="30%" class="headingSpas" style="padding-left: 10px">
                                                        <input id="call Date" style="width: 145px" name="callDate"  type="hidden" maxlength="50" class="headingSpas"  value="${serviceForm.callDate}" />
                                                        ${serviceForm.callDate}
                                                    </td>
                                                </tr>
                                                <tr >


                                                    <td style="padding-right: 10px" align="right" class="headingSpas" >
                                                        <bean:message key="label.common.doorstepServiceRequired" /><span class="mandatory" >*</span></td>
                                                    <td  align="left" class="headingSpas">
                                                        <select id="doorstepServiceRequired ID" name="doorstepServiceRequired" class="headingSpas" style="width: 200px!important">
                                                            <option value=""><bean:message key="label.common.selectoption" /></option>
                                                            <option value="Y">YES</option>
                                                            <option value="N">NO</option>

                                                        </select>
                                                    </td>

                                                    <td style="padding-right: 10px" align="right" class="headingSpas" >
                                                        <bean:message key="label.common.followUpStatus" /><span class="mandatory" >*</span></td>
                                                    <td  align="left" class="headingSpas">
                                                        <select id="followupStatus ID" name="followUpStatus" class="headingSpas" style="width: 200px!important" onchange="mandatoryCheck(this.value);">
                                                            <option value=""><bean:message key="label.common.selectoption" /></option>
                                                            <option value="PROMISED">PROMISED</option>
                                                            <option value="DECLINED">DECLINED</option>
                                                            <option value="RECALL">RECALL</option>

                                                        </select>
                                                    </td>
                                                </tr>

                                                <tr>

                                                    <td  align="right" style="padding-right: 10px;"  class="headingSpas" >
                                                        <div id="proID1" style="display: none"> <bean:message key="label.common.promisedDate" /> </div>
                                                        <div id="proID" style="display: block"> <bean:message key="label.common.promisedDate" /> <span  class="mandatory" >*</span></div>
                                                    </td>

                                                    <td align="left">
                                                        <input name="customerPromisedDate" type="text" class="datepicker" id="Promised Date" style="width:80px"  value="${serviceForm.fromdate}" onkeydown="return false;"/>
                                                    </td>
                                                    <td  align="right" style="padding-right: 10px;" align="left" class="headingSpas" >
                                                        <div id="nextFollowID1" style="display: none"> <bean:message key="label.common.nextFollowUpDate" /></div>
                                                        <div id="nextFollowID" style="display: block"> <bean:message key="label.common.nextFollowUpDate" /> <span  class="mandatory" >*</span></div>
                                                        </td>
                                                 
                                                    <td  align="left">
                                                        <input name="nextFollowUpDate" type="text" class="datepicker" id="nextFollowUpDate Id" style="width:80px"  value="${serviceForm.fromdate}" onkeydown="return false;"/>
                                                    </td>
                                                </tr>

                                                <tr >


                                                    <td style="padding-right: 10px" align="right" class="headingSpas" >
                                                        <bean:message key="label.common.calldesc" /><span class="mandatory" >*</span></td>
                                                    <td colspan="3" align="left" class="headingSpas" style="padding-left:7px">
                                                        <textarea id="calldesc Id" name="callDescription" style="width: 500px;height: 30px!important" maxlength="200" class="headingSpas"  ></textarea>
                                                    </td>
                                                </tr>

                                                <tr>
                                                    <td align="right" colspan="2">
                                                        <input id="Button2" type="submit" class="headingSpas" value="SUBMIT" onclick="validate()" /></td>

                                                    <td align="left" colspan="3">
                                                        <input id="Button3" type="submit" class="headingSpas" value="BACK" onclick="backpage()" /></td>
                                                        <%--<td align="left" colspan="">
                                                            <input id="Button3" type="submit" class="headingSpas" value="CLOSE ENQUIRY" onclick="closedEnquiry('${enquiryForm.enqProdId}',' ${enquiryForm.enquiryNo}')" /></td>--%>
                                                </tr>

                                            </tbody>
                                        </table>
                                    </td>
                                </tr>

                            </tbody>
                        </table>
                    </form>
                </div>
            </div>
            <div class="content_right1" style="margin-top:10px;">
                <div class="con_slidediv1" style="position: relative;width: 100%">
                    <h1><bean:message key="label.common.FOLLOWUPHISTORY" /></h1>
                    <div id="inner">
                        <div id="carousel">


                            <c:if test="${!empty followUpHistoryList}">
                                <tr height=25 bgcolor="#FFFFFF">
                                    <td align= center class="headingSpas">
                                        <pg:pager url="serviceProcess.do" maxIndexPages="10" maxPageItems="10">
                                            <pg:param name="option" value="getFollowup"/>
                                            <pg:param name="scheduleID"  value='${serviceForm.scheduleID}'/>
                                            <c:if test="${range eq '1'}"><pg:param name="range"  value='1'/> </c:if>

                                            <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                                <tr bgcolor="#eeeeee" height="20">
                                                    <td nowrap align="center" width="2%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.sno" />
                                                    </td>
                                                    <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.servicetype" />
                                                    </td>

                                                    <td nowrap align="left" width="13%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.calldate" />
                                                    </td>
                                                    <td nowrap align="left" width="15%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.doorstepServiceRequired" />
                                                    </td>
                                                    <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.calldesc" />
                                                    </td>
                                                    <td nowrap align="left" width="10%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.followUpStatus" />
                                                    </td>
                                                    <td nowrap align="left" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.promisedDate" />
                                                    </td>

                                                    <td nowrap align="left" width="5%" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;" >
                                                        <bean:message key="label.common.nextFollowUpDate" />
                                                    </td>
                                                </tr>
                                                <c:set var="sno" value="1"></c:set>
                                                <logic:iterate id="followUpHistoryList" name="followUpHistoryList">
                                                    <pg:item>
                                                        <tr  bgcolor="#eeeeee" height="20">
                                                            <td nowrap align="center" bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;" >${sno}</td>
                                                            <td align="left"  bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"> ${followUpHistoryList.serviceType}</td>
                                                            <td align="left" nowrap   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;">${followUpHistoryList.callDate}</td>
                                                            <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${followUpHistoryList.doorstepServiceRequired}</span></td>
                                                            <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;white-space: nowrap;"><span >${followUpHistoryList.callDescription}</span></td>
                                                            <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;white-space: nowrap;"><span >${followUpHistoryList.status}</span></td>
                                                            <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${followUpHistoryList.customerPromisedDate}</span></td>
                                                            <td align="left"   bgcolor="#FFFFFF" style="padding-left:3px;FONT-SIZE: 11px;"><span >${followUpHistoryList.nextFollowUpDate}</span></td>

                                                        </tr>
                                                    </pg:item>
                                                    <c:set var="sno" value="${sno + 1 }" />
                                                </logic:iterate>
                                                <tr>

                                                    <td colspan="8" align="center" bgcolor="#FFFFFF" class="textPaging" >

                                                        <table  align="center" cellspacing="0" cellpadding="0">
                                                            <tr>
                                                                <td>
                                                                    <pg:index>
                                                                        <pg:first><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/first.gif" border="0" align="middle" style="float:left;"/></a></pg:first>&nbsp;
                                                                        <pg:prev><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/prev.gif" border="0" align="middle" style="float:left;"/></a></pg:prev>&nbsp;
                                                                        <pg:pages>&nbsp;<a style="color: blue!important;font: bold small Palatino, serif;"  href="${pageUrl}">${pageNumber}</a>&nbsp;</pg:pages>&nbsp;
                                                                        <pg:last><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/last.gif" border="0" align="middle" style="float:right"/></a></pg:last>
                                                                        <pg:next><a href="${pageUrl}"><img alt="" src="${pageContext.request.contextPath}/images/next_1.gif" border="0" align="middle" style="float:right;"/></a></pg:next>&nbsp;
                                                                        </pg:index>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>

                                            </table>
                                        </pg:pager>
                                    </td>
                                </tr>
                            </c:if>
                        </div>
                    </div>
                </div>
            </div>
        </center>
    </div>
</html>
