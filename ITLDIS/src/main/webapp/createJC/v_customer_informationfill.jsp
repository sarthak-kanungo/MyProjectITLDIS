<%--
    Document   : v_customer_information.jsp
    Created on : Apr 30, 2014, 03:25:09 PM
    Author     : Vijay Mishra
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<script language="javascript" src="${pageContext.request.contextPath}/js/alert_message.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/commonValidations.js"></script>
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
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';

    function validate(){

        var ck_code = /^[a-zA-Z\.\ ]+$/;
        var cks_code = /^[a-zA-Z\d\-\/\.\,\\\ ]+$/;
        var ckk_code=/^[a-zA-Z\d\-\/\ ]+$/;
        var manual = "${serviceform.vin_details_type}";
        var elementArr;

    if(manual == "MANUAL"){
    elementArr = new Array('Customer Name','Village','Taluka/Tehsil','District','Pin Code','State','Country','Mobile Phone','Payee Name', 'Payee Village', 'Payee Mobile Phone','Payee Taluka/Tehsil','Payee District','Payee State','Payee Country');   //'Payee PinCode',

//Customer Name
if(document.getElementById("Customer Name").value!=""){
                 if(ck_code.test(document.getElementById("Customer Name").value)==false){
                        alert(custname_validation_msg);//customername
                        document.getElementById("Customer Name").focus();
                        return false;
                    }
                        }
if(document.getElementById("Village").value!=""){
            if(cks_code.test(document.getElementById("Village").value)==false){

                document.getElementById("Village").focus();
                document.getElementById("Village").value="";
               alert(vill_validation_msg)
                return false;
            }
  }
if(document.getElementById("District").value!=""){
                 if(ck_code.test(document.getElementById("District").value)==false){
                        alert(district_validation_msg);//customername
                        document.getElementById("District").focus();
                        return false;
                    }
                        }
if(document.getElementById("Taluka/Tehsil").value!=""){
                 if(ckk_code.test(document.getElementById("Taluka/Tehsil").value)==false){
                        alert(taluka_validation_msg);//customername
                        document.getElementById("Taluka/Tehsil").focus();
                        return false;
                    }
                        }
if(document.getElementById("State").value!=""){
                 if(ck_code.test(document.getElementById("State").value)==false){
                        alert(state_validation_msg);//customername
                        document.getElementById("State").focus();
                        return false;
                    }
                        }
if(document.getElementById("Country").value!=""){
                 if(ck_code.test(document.getElementById("Country").value)==false){
                        alert(country_validation_msg);//customername
                        document.getElementById("Country").focus();
                        return false;
                    }
                        }

  

  

        if(document.getElementById("Landline").value!=""){
            if(validNumberdigit(document.getElementById("Landline").value)==false){


                document.getElementById("Landline").focus();
                document.getElementById("Landline").value="";
                alert(landline_validation_msg);
                return false;
            }
        }
        if(document.getElementById("Pin Code").value!=""){
            if(validNumberdigit(document.getElementById("Pin Code").value)==false){

                document.getElementById("Pin Code").focus();
                document.getElementById("Pin Code").value="";

                alert(custpincode_validation_msg);
                return false;
            }
        }

if(document.getElementById("Mobile Phone").value!=""){
        if(validNumberdigit(document.getElementById("Mobile Phone").value)==false){

            document.getElementById("Mobile Phone").focus();
            document.getElementById("Mobile Phone").value="";
            alert(mobileph_validation_msg);
            return false;
        }
}
          if(document.getElementById("Landline").value!=""){
        if(validNumberdigit(document.getElementById("Landline").value)==false){

            document.getElementById("Landline").focus();
            document.getElementById("Landline").value="";
            alert(landline_validation_msg);
            return false;
        }
           }
           if(document.getElementById("Pin Code").value!=""){
        if(validNumberdigit(document.getElementById("Pin Code").value)==false){

            document.getElementById("Pin Code").focus();
            document.getElementById("Pin Code").value="";
            alert(pincode_validation_msg);
            return false;
        }
           }

        //
        if(document.getElementById('E-mail ID').value!=""){
        if(emailIdValid(document.getElementById('E-mail ID').value)==false)
        {

            document.getElementById("E-mail ID").focus();
            document.getElementById("E-mail ID").value="";
            alert(payeeemail_validation_msg);

            return false;
        }
       }

if(document.getElementById("Mobile Phone").value!=""){
                if(document.getElementById("Mobile Phone").value.length !=document.getElementById("Mobile Phone").maxLength){
                    alert("Mobile Phone should be "+document.getElementById("Mobile Phone").maxLength+" Character");
                    document.getElementById("Mobile Phone").focus();
                    return false;
                }
}

<%--if(document.getElementById("Pin Code").value!=""){
  if(document.getElementById("Pin Code").value!="" && document.getElementById("Pin Code").value!="0"){
                if(document.getElementById("Pin Code").value.length !=document.getElementById("Pin Code").maxLength){
                    alert("Pin Code should be "+document.getElementById("Pin Code").maxLength+" Character");
                    document.getElementById("Pin Code").focus();
                    return false;
                }
            }

               if(document.getElementById("Pin Code").value.length !=document.getElementById("Pin Code").maxLength){
                    alert("PinCode should be "+document.getElementById("PinCode").maxLength+" Character");
                    document.getElementById("PinCode").focus();
                    return false;
                }

}--%>

if(document.getElementById("Size of Land Holding").value!=""){
    
    if(cks_code.test(document.getElementById("Size of Land Holding").value)==false){

                document.getElementById("Size of Land Holding").focus();
                document.getElementById("Size of Land Holding").value="";
                alert(sizeoflandhold_validation_msg)
                return false;
            }
    
}
if(document.getElementById("Main Crops").value!=""){

if(cks_code.test(document.getElementById("Main Crops").value)==false){

                document.getElementById("Main Crops").focus();
                document.getElementById("Main Crops").value="";
               alert(maincrop_validation_msg)
                return false;
            }

}
if(document.getElementById("Occupation").value!=""){
if(cks_code.test(document.getElementById("Occupation").value)==false){

                document.getElementById("Occupation").focus();
                document.getElementById("Occupation").value="";
               alert(occupation_validation_msg)
                return false;
            }


}




}else{
    elementArr = new Array('Payee Name', 'Payee Village', 'Payee Mobile Phone','Payee Taluka/Tehsil','Payee District','Payee State','Payee Country');   //'Payee PinCode',
}





        
        
        var strObject = null;

        for (var i = 0; i < elementArr.length; i++)
        {

            strObject = document.getElementById(elementArr[i]);

            if (strObject.value=="") 
            {
                strObject.focus();
                alert(not_blank_validation_msg  + elementArr[i] + "");
                return false;
            }
            if(elementArr[i]=="Payee Name" || elementArr[i]=="Payee State" || elementArr[i]=="Payee Country" || elementArr[i]=="Payee District")
               {
                    if(elementArr[i]!=""){
                    if(ck_code.test(document.getElementById(elementArr[i]).value)==false){
                        alert(invalid_validation_msg+" "+elementArr[i]);
                        document.getElementById(elementArr[i]).focus();
                        return false;
                    }
                        }
                   
               }
            if(elementArr[i]=="Payee Taluka/Tehsil")
               {
                    if(elementArr[i]!=""){
                    if(ckk_code.test(document.getElementById(elementArr[i]).value)==false){
                        alert(invalid_validation_msg+" "+elementArr[i]);
                        document.getElementById(elementArr[i]).focus();
                        return false;
                    }
                        }

               }

        }



        if(document.getElementById("Mobile Phone").value!="")
            if( validNumberdigit(document.getElementById("Mobile Phone").value)==false){

                document.getElementById("Mobile Phone").focus();
                document.getElementById("Mobile Phone").value="";
                alert(mobileph_validation_msg);
                return false;
            }
        if(document.getElementById("Landline").value!="")
            if(validNumberdigit(document.getElementById("Landline").value)==false){


                document.getElementById("Landline").focus();
                document.getElementById("Landline").value="";
                alert(landline_validation_msg);
                return false;
            }
        if(document.getElementById("Pin Code").value!="")
            if(validNumberdigit(document.getElementById("Pin Code").value)==false){

                document.getElementById("Pin Code").focus();
                document.getElementById("Pin Code").value="";

                alert(pincode_validation_msg);
                return false;
            }


        if(validNumberdigit(document.getElementById("Payee Mobile Phone").value)==false){

            document.getElementById("Payee Mobile Phone").focus();
            document.getElementById("Payee Mobile Phone").value="";
            alert(payeemobileph_validation_msg);
            return false;
        }
        if(document.getElementById("Payee Mobile Phone").value.length !=document.getElementById("Payee Mobile Phone").maxLength){
                    alert("Payee Mobile Phone should be "+document.getElementById("Payee Mobile Phone").maxLength+" Character");
                    document.getElementById("Payee Mobile Phone").focus();
                    return false;
         }
        if(document.getElementById("Payee Landline").value!=""){
        if(validNumberdigit(document.getElementById("Payee Landline").value)==false){

            document.getElementById("Payee Landline").focus();
            document.getElementById("Payee Landline").value="";
            alert(payeelandline_validation_msg);
            return false;
        }
           }
        if(validNumberdigit(document.getElementById("Payee PinCode").value)==false && document.getElementById("Payee PinCode").value!=""){

            document.getElementById("Payee PinCode").focus();
            document.getElementById("Payee PinCode").value="";
            alert(payeepincode_validation_msg);
            return false;
        }


        //
        if(document.getElementById('Payee E-mail ID').value!=""){
        if(emailIdValid(document.getElementById('Payee E-mail ID').value)==false)
        {

            document.getElementById("Payee E-mail ID").focus();
            document.getElementById("Payee E-mail ID").value="";
            alert(payeeemail_validation_msg);

            return false;
        }
       }

            
                if(document.getElementById("Payee Mobile Phone").value.length !=document.getElementById("Payee Mobile Phone").maxLength){
                    alert("Payee Mobile Phone should be "+document.getElementById("Payee Mobile Phone").maxLength+" Character");
                    document.getElementById("Payee Mobile Phone").focus();
                    return false;
                }
                
            

            
                    if(document.getElementById("Payee PinCode").value!=""){
                if(document.getElementById("Payee PinCode").value.length !=document.getElementById("Payee PinCode").maxLength){
                    alert("Payee PinCode should be "+document.getElementById("Payee PinCode").maxLength+" Character");
                    document.getElementById("Payee PinCode").focus();
                    return false;
                }
                    }
            
            if(document.getElementById("Pin Code").value!="" && document.getElementById("Pin Code").value!="0"){
                if(document.getElementById("Pin Code").value.length !=document.getElementById("Pin Code").maxLength){
                    alert("Pin Code should be "+document.getElementById("Pin Code").maxLength+" Character");
                    document.getElementById("Pin Code").focus();
                    return false;
                }
           }


      
      document.getElementById("CustomerForm").submit();

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
                
                <h1 ><span class="formLabel"> <bean:message key="label.common.customerinfo" /></span></h1>






<c:if test="${serviceform.jobCardNo ne null }">
    <%@ include file="topbandshow.jsp" %>
</c:if>

<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >
    <tr><td>
            <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1">
                <tr align="center" valign="middle" class="formHeader">
                    <c:choose> <c:when test="${pathNm eq 'fillJC'}">
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initVehicleInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.vehicleinfo" /></a></label></td>
                            <td class="formSelectedHeader"><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initCustomerInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.customerinfo" /></a></label></td>
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStandardChecks&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.standardcheck" /></a></label></td>
                            <td ><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initComplaint&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.complaint" /></a></label></td>
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initEstimate&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.estimate" /></a></label></td>
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initActual&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.actuals" /></a></label></td>
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStatus&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.status" /></a></label></td>
                            <c:if test="${showHistory ne 'Y'}">
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=init_vehicle_History&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.history" /></a></label></td>
                            </c:if>       
                        </c:when>
                        <c:otherwise>


                            <td ><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initVehicleInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}" class="formLabel"><bean:message key="label.common.vehicleinfo" /></a></label></td>
                            <td class="formSelectedHeader"><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initCustomerInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}" class="linkGreyBg"><bean:message key="label.common.customerinfo" /></a></label></td>
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStandardChecks&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}" class="formLabel"><bean:message key="label.common.standardcheck" /></a></label></td>
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initComplaint&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}" class="formLabel"><bean:message key="label.common.complaint" /></a></label></td>
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initEstimate&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}" class="formLabel"><bean:message key="label.common.estimate" /></a></label></td>
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initActual&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}" class="formLabel"><bean:message key="label.common.actuals" /></a></label></td>
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStatus&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&showHistory=${showHistory}" class="formLabel"><bean:message key="label.common.status" /></a></label></td>
                            <c:if test="${showHistory ne 'Y'}">
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=init_vehicle_History&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobidvalue=${jobidvalue}&jobId=${serviceform.jobId}&showHistory=${showHistory}" class="formLabel"><bean:message key="label.common.history" /></a></label></td>
                            </c:if>       
                        </c:otherwise> </c:choose>   </tr>
                </table>
            </td></tr>


        <tr>

       <%-- <div class="message1" id="msg_saveFAILED">${message}</div>--%>
    <td>
        <table  width="90%" border="0" align="center" cellpadding="3" cellspacing="3"  >
            <tbody><form action="<%=cntxpath%>/serviceCreateJC.do?option=manageCustomerPayeeInformation&jctype=${serviceform.jctype}&jobType=${serviceform.jobType}" method="post" id="CustomerForm" >
                <tr>
                    <td align="right" class="formLabel" style="white-space: nowrap"><strong> <label> <bean:message key="label.common.CustomerName" />&nbsp;<span class="mandatory">*</span></label></strong></td>
                    <td colspan="3" align="left"  >
                        <c:choose>
                            <c:when test="${serviceform.vin_details_type eq 'DB' }">
                                <input name="customerName" type="text" id="Customer Name" style="width:350px !important" value="${serviceform.customerName}" readonly/>
                            </c:when>
                            <c:otherwise>
                                <input name="customerName" type="text" id="Customer Name" style="width:350px !important" value="${serviceform.customerName}" maxlength="70"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td align="right" class="formLabel" style="white-space: nowrap"><strong><label><bean:message key="label.common.JobcardPayeeName" /></label></strong>&nbsp;<span class="mandatory">*</span></td>
                    <td colspan="3" align="left"  >

                        <input name="payeeName" type="text" id="Payee Name"  style="width:350px !important" value="${serviceform.payeeName}"/>
                        <input type="hidden" name="jobid" value="${jobidvalue}">
                        <input type="hidden" name="jobId" value="${serviceform.jobId}">
                         <input type="hidden" name="jobType" value="${serviceform.jobType}">
                         <input type="hidden" name="modelCode" value="${serviceform.modelCode}">
                         <input name="showHistory" type="hidden" value="${showHistory}">

                    </td>


               
                </tr>
                <tr>
                <input type="hidden" name="jobno" value="${serviceform.jobCardNo}">
                <input type="hidden" name="vinno" value="${serviceform.vinNo}" id="vinno">
                <td align="right"  style="white-space: nowrap"> <label><bean:message key="label.common.village" />&nbsp;<span class="mandatory">*</span></label></td>
                <td align="left"  >

                    <c:choose>
                        <c:when test="${serviceform.vin_details_type eq 'DB' }">
                            <input name="village" type="text" id="Village" size="15" value="${serviceform.village}" readonly/>
                        </c:when>
                        <c:otherwise>
                            <input name="village" type="text" id="Village" size="15" value="${serviceform.village}" maxlength="70"/>
                        </c:otherwise>
                    </c:choose>





                </td>
                <td align="right" style="white-space: nowrap" >
                    <label><bean:message key="label.common.mobilephone" />&nbsp;<span class="mandatory">*</span></label></td>
                <td align="left"  >


                    <c:choose>
                        <c:when test="${serviceform.vin_details_type eq 'DB'  }">
                            <input name="mobilePhone" type="text" id="Mobile Phone" value="${serviceform.mobilePhone}" readonly/>
                        </c:when>
                        <c:otherwise>
                            <input name="mobilePhone" type="text" id="Mobile Phone" value="${serviceform.mobilePhone}" maxlength="10"/>
                        </c:otherwise>
                    </c:choose>


                </td>
                <td align="right"  style="white-space: nowrap">
                    <label><bean:message key="label.common.village" /></label>&nbsp;<span class="mandatory">*</span></td>
                <td align="left"  >

                    <input name="payeeVillage" type="text" id="Payee Village" size="15" value="${serviceform.payeeVillage}" maxlength="70"/>



                </td>
                <td align="right" style="white-space: nowrap" >
                    <label><bean:message key="label.common.mobilephone" /></label>&nbsp;<span class="mandatory">*</span></td>
                <td align="left"  >

                    <input name="payeeMobilePhone" type="text" id="Payee Mobile Phone" value="${serviceform.payeeMobilePhone}" maxlength="10"/>



                </td>
                </tr>
                <tr>
                    <td align="right" class="formLabel" style="white-space: nowrap"><label><bean:message key="label.common.talukatahsil" />&nbsp;<span class="mandatory">*</span></label></td>
                    <td align="left" >
                        <c:choose>
                            <c:when test="${serviceform.vin_details_type eq 'DB'  }">
                                <input name="tehsil" type="text" id="Taluka/Tehsil" value="${serviceform.tehsil}" readonly/>
                            </c:when>
                            <c:otherwise>
                                <input name="tehsil" type="text" id="Taluka/Tehsil" value="${serviceform.tehsil}" maxlength="40"/>
                            </c:otherwise>
                        </c:choose>


                    </td>
                    <%--<td align="right"  style="white-space: nowrap">
                        <label>Taluka</label></td>
                    <td align="left"  >


                        <c:choose>
                            <c:when test="${serviceform.jobcardview eq 'true'  and serviceform.vin_details_type eq 'DB'}">
                                <input name="taluka" type="text" id="Taluka" value="${serviceform.taluka}" readonly/>
                            </c:when>
                            <c:otherwise>
                                <input name="taluka" type="text" id="Taluka" value="${serviceform.taluka}"/>
                            </c:otherwise>
                        </c:choose>

                    </td>--%>
                    
                    <td align="right"  style="white-space: nowrap">
                        <label><bean:message key="label.common.landline" /></label>
                    </td>
                    <td align="left"  >



                        <c:choose>
                            <c:when test="${serviceform.vin_details_type eq 'DB'  }">
                                <input name="landline" type="text" id="Landline" value="${serviceform.landline}" readonly/>
                            </c:when>
                            <c:otherwise>
                                <input name="landline" type="text" id="Landline" value="${serviceform.landline}" maxlength="70"/>
                            </c:otherwise>
                        </c:choose>

                    </td>
                    <%--<td align="right"  style="white-space: nowrap">
                        <label>Taluka</label>&nbsp;<span class="mandatory">*</span></td>
                    <td align="left"  style="white-space: nowrap">


                        <input name="payeeTaluka" type="text" id="Payee Taluka" value="${serviceform.payeeTaluka}"/>



                    </td>--%>
                    <td align="right" class="formLabel" style="white-space: nowrap"><label><bean:message key="label.common.talukatahsil" /></label>&nbsp;<span class="mandatory">*</span></td>
                    <td align="left" >

                        <input name="payeeTehsil" type="text" id="Payee Taluka/Tehsil" value="${serviceform.payeeTehsil}"/>
                    </td>
                    <td align="right"  style="white-space: nowrap">
                        <label><bean:message key="label.common.landline" /></label>
                    </td>
                    <td align="left"  style="white-space: nowrap">


                        <input name="payeeLandline" type="text" id="Payee Landline" value="${serviceform.payeeLandline}" maxlength="20"/>
                    </td>
                </tr>
                <tr>
                    <%--<td align="right" class="formLabel" style="white-space: nowrap"><label>Taluka/Tehsil</label></td>
                    <td align="left" >
                        <c:choose>
                            <c:when test="${serviceform.jobcardview eq 'true'  and serviceform.vin_details_type eq 'DB'  }">
                                <input name="tehsil" type="text" id="tehsil" value="${serviceform.tehsil}" readonly/>
                            </c:when>
                            <c:otherwise>
                                <input name="tehsil" type="text" id="tehsil" value="${serviceform.tehsil}"/>
                            </c:otherwise>
                        </c:choose>


                    </td>--%>
                    <td align="right" style="white-space: nowrap" >
                        <label><bean:message key="label.common.district" />&nbsp;<span class="mandatory">*</span></label></td>
                    <td align="left"  >
                        <c:choose>
                            <c:when test="${ serviceform.vin_details_type eq 'DB'}">
                                <input name="district" type="text" id="District" value="${serviceform.district}" readonly/>
                            </c:when>
                            <c:otherwise>
                                <input name="district" type="text" id="District" value="${serviceform.district}" maxlength="40"/>
                            </c:otherwise>
                        </c:choose>

                    </td>
                    <td align="right"  style="white-space: nowrap"><label><bean:message key="label.common.emailid" /></label></td>
                    <td align="left"  >


                        <c:choose>
                            <c:when test="${serviceform.vin_details_type eq 'DB'  }">
                                <input name="emailId" type="text" id="E-mail ID" value="${serviceform.emailId}" readonly/>
                            </c:when>
                            <c:otherwise>
                                <input name="emailId" type="text" id="E-mail ID" value="${serviceform.emailId}" maxlength="100"/>
                            </c:otherwise>
                        </c:choose>


                    </td>
                    <td align="right"  style="white-space: nowrap">
                        <label><bean:message key="label.common.district" /></label>&nbsp;<span class="mandatory">*</span></td>
                    <td align="left"  >
                        <input name="payeeDistrict" type="text" id="Payee District" value="${serviceform.payeeDistrict}"/>

                    </td>

                    <td align="right"  style="white-space: nowrap"><label><bean:message key="label.common.emailid" /></label></td>
                    <td align="left"  >
                        
                        <input type="hidden" name="vinid" value="${vinid}">
                        <input type="hidden" name="jobidvalue" value="${jobidvalue}">
                        <input name="payeeEmailId" type="text" id="Payee E-mail ID" value="${serviceform.payeeEmailId}" maxlength="100"/>

                    </td>
                </tr>
                <tr>
                    <%--<td align="right" style="white-space: nowrap" >
                        <label>District</label></td>
                    <td align="left"  >
                        <c:choose>
                            <c:when test="${serviceform.jobcardview eq 'true'  and serviceform.vin_details_type eq 'DB'}">
                                <input name="district" type="text" id="District" value="${serviceform.district}" readonly/>
                            </c:when>
                            <c:otherwise>
                                <input name="district" type="text" id="District" value="${serviceform.district}"/>
                            </c:otherwise>
                        </c:choose>

                    </td>--%>
                     <td align="right" style="white-space: nowrap" >
                        <label><bean:message key="label.common.pincode" />&nbsp;<span class="mandatory">*</span></label></td>
                    <td align="left"  >


                        <c:choose>
                            <c:when test="${serviceform.vin_details_type eq 'DB'  }">
                                <input name="pinCode" type="text" id="Pin Code" value="${serviceform.pinCode}" maxlength="6" readonly/>
                            </c:when>
                            <c:otherwise>
                                <input name="pinCode" type="text" id="Pin Code" value="${serviceform.pinCode}" maxlength="6"/>
                            </c:otherwise>
                        </c:choose>


                    </td>
                    <td align="right" style="white-space: nowrap" >
                        <label><bean:message key="label.common.sizeoflandhold" /></label></td>

                    <td align="left" >
                        <c:choose>
                            <c:when test="${serviceform.vin_details_type eq 'DB'  }">
                                <input name="sizeoflandholding" type="text" id="Size of Land Holding" value="${serviceform.sizeoflandhold}" readonly/>
                            </c:when>
                            <c:otherwise>
                                <input name="sizeoflandholding" type="text" id="Size of Land Holding" value="${serviceform.sizeoflandhold}" maxlength="40"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                   <%-- <td align="right"  style="white-space: nowrap">
                        <label>District</label>&nbsp;<span class="mandatory">*</span></td>
                    <td align="left"  >
                        <input name="payeeDistrict" type="text" id="Payee District" value="${serviceform.payeeDistrict}"/>

                    </td>--%>
                   <td align="right"  style="white-space: nowrap ;padding-right: 10px;" >
                        <label><bean:message key="label.common.pincode" /></label><%--&nbsp;<span class="mandatory">*</span>--%></td>
                    <td align="left"  >
                       
                        <c:if test="${serviceform.payeePinCode eq 0}">
                        <input name="payeePinCode" type="text" id="Payee PinCode" value="" maxlength="6"/></c:if>
                        <c:if test="${serviceform.payeePinCode ne 0}">
                        <input name="payeePinCode" type="text" id="Payee PinCode" value="${serviceform.payeePinCode}" maxlength="6"/>
                        </c:if>

                    </td>
                    <td align="right"  >&nbsp;</td>
                    <td align="left"  >&nbsp;</td>
                </tr>
                <tr>
                   <%-- <td align="right" style="white-space: nowrap" >
                        <label>Pin Code</label></td>
                    <td align="left"  >


                        <c:choose>
                            <c:when test="${serviceform.jobcardview eq 'true'  and serviceform.vin_details_type eq 'DB'  }">
                                <input name="pinCode" type="text" id="Pin Code" value="${serviceform.pinCode}" readonly/>
                            </c:when>
                            <c:otherwise>
                                <input name="pinCode" type="text" id="Pin Code" value="${serviceform.pinCode}"/>
                            </c:otherwise>
                        </c:choose>


                    </td>--%>
                    <td align="right"  style="white-space: nowrap">
                        <label><bean:message key="label.common.state" />&nbsp;<span class="mandatory">*</span></label></td>
                    <td align="left"  >
                        
                        <c:choose>
                            <c:when test="${serviceform.vin_details_type eq 'DB'  }">
                                <input name="state" type="text" id="State" value="${serviceform.state}" readonly/>
                            </c:when>
                            <c:otherwise>
                                <input name="state" type="text" id="State" value="${serviceform.state}" maxlength="40"/>
                            </c:otherwise>
                        </c:choose>


                    </td>
                    <td align="right" style="white-space: nowrap" >
                        <label><bean:message key="label.common.maincrop" /></label></td>

                    <td align="left" >
                        <c:choose>
                            <c:when test="${serviceform.vin_details_type eq 'DB'  }">
                                <input name="maincrops" type="text" id="Main Crops" value="${serviceform.maincrops}" readonly/>
                            </c:when>
                            <c:otherwise>
                                <input name="maincrops" type="text" id="Main Crops" value="${serviceform.maincrops}" maxlength="100"/>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <%--<td align="right"  style="white-space: nowrap">
                        <label>Pin Code</label>&nbsp;<span class="mandatory">*</span></td>
                    <td align="left"  >
                        <input name="payeePinCode" type="text" id="Payee Pin Code" value="${serviceform.payeePinCode}"/>

                    </td>--%>
                     <td align="right" style="white-space: nowrap" >
                        <label><bean:message key="label.common.state" /></label>&nbsp;<span class="mandatory">*</span></td>
                    <td align="left"  >
                        <input name="payeeState" type="text" id="Payee State" value="${serviceform.payeeState}" maxlength="40"/>

                    </td>
                    <td align="right"  >&nbsp;</td>
                    <td align="left"  >&nbsp;</td>
                </tr>
                <tr>
                   <%-- <td align="right"  style="white-space: nowrap">
                        <label>State</label></td>
                    <td align="left"  >
                        <c:choose>
                            <c:when test="${serviceform.jobcardview eq 'true'  and serviceform.vin_details_type eq 'DB'  }">
                                <input name="state" type="text" id="State" value="${serviceform.state}" readonly/>
                            </c:when>
                            <c:otherwise>
                                <input name="state" type="text" id="State" value="${serviceform.state}"/>
                            </c:otherwise>
                        </c:choose>


                    </td>--%>
                   <td align="right"  style="white-space: nowrap">
                        <label><bean:message key="label.common.Country" />&nbsp;<span class="mandatory">*</span></label></td>
                    <td align="left"  >
                        <c:choose>
                            <c:when test="${serviceform.vin_details_type eq 'DB'  }">
                                <input name="country" type="text" id="Country" value="${serviceform.country}" readonly/>
                            </c:when>
                            <c:otherwise>
                                <input name="country" type="text" id="Country" value="${serviceform.country}" maxlength="40"/>
                            </c:otherwise>
                        </c:choose>

                    </td>
                    <td align="right" style="white-space: nowrap" >
                        <label><bean:message key="label.common.occupation" /></label></td>

                    <td align="left" >
                        <c:choose>
                            <c:when test="${serviceform.vin_details_type eq 'DB'  }">
                                <input name="occupation" type="text" id="Occupation" value="${serviceform.occupation}" readonly/>
                            </c:when>
                            <c:otherwise>
                                <input name="occupation" type="text" id="Occupation" value="${serviceform.occupation}" maxlength="40"/>
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <%--<td align="right" style="white-space: nowrap" >
                        <label>State</label>&nbsp;<span class="mandatory">*</span></td>
                    <td align="left"  >
                        <input name="payeeState" type="text" id="Payee State" value="${serviceform.payeeState}"/>

                    </td>--%>
                    <td align="right"  style="white-space: nowrap">
                        <label><bean:message key="label.common.Country" /></label>&nbsp;<span class="mandatory">*</span></td>
                    <td align="left"  >
                        <input name="payeeCountry" type="text" id="Payee Country" value="${serviceform.payeeCountry}" maxlength="40"/>

                    </td>
                    <td align="right"  >&nbsp;</td>
                    <td align="left"  >&nbsp;</td>
                </tr>
               <%-- <tr>
                    <td align="right"  style="white-space: nowrap">
                        <label>Country</label></td>
                    <td align="left"  >
                        <c:choose>
                            <c:when test="${serviceform.jobcardview eq 'true'  and serviceform.vin_details_type eq 'DB'  }">
                                <input name="country" type="text" id="Country" value="${serviceform.country}" readonly/>
                            </c:when>
                            <c:otherwise>
                                <input name="country" type="text" id="Country" value="${serviceform.country}"/>
                            </c:otherwise>
                        </c:choose>

                    </td>
                    <td align="right"  >&nbsp;</td>
                    <td align="left"  >&nbsp;</td>
                    <td align="right"  style="white-space: nowrap">
                        <label>Country</label>&nbsp;<span class="mandatory">*</span></td>
                    <td align="left"  >
                        <input name="payeeCountry" type="text" id="Payee Country" value="${serviceform.payeeCountry}"/>

                    </td>
                    <td align="right"  >&nbsp;</td>
                    <td align="left"  >&nbsp;</td>
                </tr>
               --%> <input type="hidden" value="${serviceform.vin_details_type}" name="vindatatype" id="vindatatype">
                <tr align="right">
                    <td  colspan="8" >
           <%if (userFunctionalities.contains("608")) {%>
                     <c:if test="${serviceform.status eq 'OPEN'}">
                    <input name="input2" type="button" value="SAVE" onclick="validate();"/></td>
                    </c:if><%}%>
                </tr>
            </form>
            </tbody>
        </table></td>
</tr>
</table>

            </div>
        </div>
  </center>