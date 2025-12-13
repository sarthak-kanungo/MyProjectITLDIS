<%--
    Document   : vehicle_information.jsp
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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" pageEncoding="UTF-8"  />
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_4.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/commonValidations.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/jquery.i18n.properties-min-1.0.9.js"></script>
<script type="text/javascript" language="javascript">

    var contextPath = '${pageContext.request.contextPath}';
    function validate()
    {


        document.getElementById("serviceForm").submit();
        //        var jobType=document.forms[0].jobType.value;
      
    }


    function GetXmlHttpObject()
    {
        var objXmlHttp = null;
        if (navigator.userAgent.indexOf('Opera') >= 0)
        {
            var xmlHttp=new XMLHttpRequest();
            return xmlHttp;
        }
        if (navigator.userAgent.indexOf('MSIE') >= 0)
        {
            var strName = 'Msxml2.XMLHTTP';
            if (navigator.appVersion.indexOf('MSIE 5.5') >=0)
            {
                strName = 'Microsoft.XMLHTTP';
            }
            try
            {
                objXmlHttp = new ActiveXObject(strName);
                // objXmlHttp.onreadystatechange = handler;
                return objXmlHttp;
            }
            catch(e)
            {
                alert('Your Browser Does Not Support Ajax');
                return;
            }
        }
        if (navigator.userAgent.indexOf('Mozilla') >= 0)
        {
            objXmlHttp = new XMLHttpRequest();
            return objXmlHttp;
        }
    }



    function getvinNoData(id)
    {
        var vinno= id.value;
        document.getElementById("msg_saveFAILED").innerHTML="";
        var strURL="<%=cntxpath%>/serviceProcess.do?option=getVimNumberDetails&vinNo=" + vinno+"&tm=" + new Date().getTime();
        xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
            {
                res = trim(xmlHttp.responseText);

                if(res!=''){
                    //EngineNo,SaleDate,RegNo,SerBookNo,KeyIdentification,OwnerDriven,ModelFamily,
                    //ModelCode,ModelFamilyDesc,ModelDesc,DealerCode,NextService
                    result= res.split("@@");
                    
                    document.getElementById("Engine Number").value=result[0];
                    document.getElementById("Sale Date").value=result[1];
                    document.getElementById("Registration No.").value=result[2];
                    document.getElementById("Service Booklet No.").value=result[3];

                    document.getElementById("ownerDriven").value=result[5];
                    document.getElementById("Model Family").value=result[6];
                    document.getElementById("Model Code").value=result[7];
                    document.getElementById("Model Family Desc").value=result[8];
                    document.getElementById("Model Code Desc.").value=result[9];
                    document.getElementById("nextService").value=result[11];
                    document.getElementById("vinid").value=result[12];
                    
                    // document.getElementById("months").value=result[12];
                    // document.getElementById("hrs").value=result[13];

                    document.getElementById("vindatatype").value="DB"

 
                    //document.getElementById("VIN No.").readOnly = true;
                    document.getElementById("Engine Number").readOnly = true;

                    document.getElementById("saledatetd").innerHTML="<input name='saleDate' type='text'  id='Sale Date' value='"+result[1]+"' readonly/>";
                    document.getElementById("Sale Date").readOnly = true;
                    document.getElementById("Registration No.").readOnly = true;
                    document.getElementById("Service Booklet No.").readOnly = true;
                    document.getElementById("ownerDriven").readOnly = true;
                    document.getElementById("Model Family").readOnly = true;
                    document.getElementById("Model Code").readOnly = true;
                    document.getElementById("Model Family Desc").readOnly = true;
                    document.getElementById("Model Code Desc.").readOnly = true;
                    document.getElementById("nextService").readOnly = true;
                    //   document.getElementById("HMR").readOnly = true;
                    document.getElementById("check").value="exist";

                    if(result[13]=!""){

                        var sdate=result[1];
                        var mon=result[12];
                        var hr=result[13];

                    }

                    else{

                        document.getElementById("Warranty App").innerHTML="<input name='warrantyapp' type='radio' value='Y' checked='checked'/><span class='formLabel'>Yes</span><input name='warrantyapp' type='radio' value='N'/><span class='formLabel'>No</span>";

                    }

                }


                else{
                    //EngineNo,SaleDate,RegNo,SerBookNo,KeyIdentification,OwnerDriven,ModelFamily,
                    //ModelCode,ModelFamilyDesc,ModelDesc,DealerCode,NextService

                    document.getElementById("Engine Number").value="";
                    document.getElementById("Sale Date").value="";
                    document.getElementById("Registration No.").value="";
                    document.getElementById("Service Booklet No.").value="";
                    document.getElementById("ownerDriven").value="";
                    document.getElementById("Model Family").value="";
                    document.getElementById("Model Code").value="";
                    document.getElementById("Model Family Desc").value="";
                    document.getElementById("Model Code Desc.").value="";
                    document.getElementById("nextService").value="";
                    document.getElementById("vinid").value="";



                }

            }
        }
        xmlHttp.open("POST", strURL, true);
        xmlHttp.send(null);


    }








    function validateForm()
    {
        //compaignname,compaigndesc,compaigndate,compaignamount
        if(document.getElementById("msg_saveFAILED")!=null)
            document.getElementById("msg_saveFAILED").innerHTML="";
        //Engine Number,Sale Date,Registration No.  Service Booklet No. ,ownerDriven,Model Family
        //Model Code,Model Family Desc,Model Code Desc. nextService

        var elementArr = new Array('VIN No.', 'Job Type', 'Sale Date', 'HMR WORKING','jobLocation','event','nextService','Tractor In Date','jobCardStatus');

        var strObject = null;

        for (var i = 0; i < elementArr.length; i++)
        {

            strObject = document.getElementById(elementArr[i]);



            if (strObject.value=="" && i==0)
            {
                alert("Please Enter Value in Chassis No");
                strObject.focus();
                //document.getElementById('msg_saveFAILED').innerHTML="Please enter Chassis No";
                return false;
            }
            if(strObject.value=="" && i>0)
            {
                alert("Please Select Value in " + elementArr[i] + " Field.");
                strObject.focus();
                //document.getElementById('msg_saveFAILED').innerHTML = "Please enter value in " + elementArr[i] + " field.";
                return false;
            }




        }


        //// //checkInteger() ,validNumberdigit,emailIdValid,onlyAlphabets

        //hmeRadio


       

        if(validNumberdigit(document.getElementById("HMR").value)==false){

            //document.getElementById('msg_saveFAILED').innerHTML = "Enter numeric value in HRM";
            alert("Enter Numeric Value in HRM");
            document.getElementById("HMR").focus();
            return false;
        }

        if(document.getElementById("HMR WORKING").value==true)
        {
            if(document.getElementById("HMR").value==""){
                alert("Enter Value in HRM");
                document.getElementById("HMR WORKING").focus();
                //document.getElementById('msg_saveFAILED').innerHTML = "Enter  value in HRM";

            }
            if(validNumberdigit(document.getElementById("HMR").value)==false){

                alert("Enter Numeric Value in HRM");
                document.getElementById("HMR").focus();
                //document.getElementById('msg_saveFAILED').innerHTML = "Enter numeric value in HRM";
                return false;
            }
         
        }

        
    


        //alert("after")



        document.getElementById("vehicleform").submit();
    }

    function msgfunction()
    {

        var vinno=document.getElementById("VIN No.").value;

        document.getElementById("msg_saveFAILED").innerHTML="";

        var x="false";
        var r=confirm("No data found for CHASSIS NO. \nClick OK if you want to download from Sonalika ITL server. \nOthewise click Cancel to continue manually.");
        if (r==true)
        {
            x="true";
        }
        else
        {
            x="false";
        }


        if(x=="true"){
            var strURL="<%=cntxpath%>/serviceProcess.do?option=getVimNumberDetailsFromServer&vinNo=" + vinno+"&pathNm=fillJC&tm=" + new Date().getTime();
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    res = trim(xmlHttp.responseText);
                   
                    //res = trim(xmlHttp.responseText);
                    if(res=="notexist" ){
                        alert("This Chassis No does not exists on Sonalika ITL server. . Kindly contact Administrator \nNo data found for chassis no - "+vinno);
                        //document.getElementById("msg_saveFAILED").innerHTML="This Chassis No does not exists on Sonalika ITL server. . Kindly contact Administrator \nNo data found for chassis no - "+vinno;
                        return false;
                    }

                    if(res!='' && res!="notexist" ){

                        document.getElementById("msg_saveFAILED").innerHTML="";
                        //EngineNo,SaleDate,RegNo,SerBookNo,KeyIdentification,OwnerDriven,ModelFamily,
                        //ModelCode,ModelFamilyDesc,ModelDesc,DealerCode,NextService
                        result= res.split("@@");//vinno
                        document.getElementById("VIN No.").value=vinno;
                        document.getElementById("Engine Number").value=result[0];
                        document.getElementById("Sale Date").value=result[1];
                        document.getElementById("Registration No.").value=result[2];
                        document.getElementById("Service Booklet No.").value=result[3];
                        document.getElementById("ownerDriven").value=result[5];
                        document.getElementById("Model Family").value=result[6];
                        document.getElementById("Model Code").value=result[7];
                        document.getElementById("Model Family Desc").value=result[8];
                        document.getElementById("Model Code Desc.").value=result[9];
                        document.getElementById("nextService").value=result[11];
                        document.getElementById("vinid").value=result[12];


                        //document.getElementById("textboxid").readOnly = true;

                        document.getElementById("VIN No.").readOnly = true;
                        document.getElementById("Engine Number").readOnly = true;
                        document.getElementById("Sale Date").readOnly = true;
                        document.getElementById("saledatetd").innerHTML="<input name='saleDate' type='text'  id='Sale Date' value='"+result[1]+"' readonly/>";
                        document.getElementById("Registration No.").readOnly = true;
                        document.getElementById("Service Booklet No.").readOnly = true;
                        document.getElementById("ownerDriven").readOnly = true;
                        document.getElementById("Model Family").readOnly = true;
                        document.getElementById("Model Code").readOnly = true;
                        document.getElementById("Model Family Desc").readOnly = true;
                        document.getElementById("Model Code Desc.").readOnly = true;
                        document.getElementById("nextService").readOnly = true;
                        document.getElementById("check").value="exist";




                    }
                }
            }
        }
        xmlHttp.open("POST", strURL, true);
        xmlHttp.send(null);




    }


    function getdatehrs(){

        document.getElementById("msg_saveFAILED").innerHTML="";
        var modelcode=document.getElementById("Model Code").value;
        var saledate=document.getElementById("Sale Date").value;

        if(saledate==undefined || saledate==""){
            alert("Please Enter Sale Date to check Warranty Applicable");
            //document.getElementById("msg_saveFAILED").innerHTML="Please Enter Sale Date to check Warranty Applicable";
            return false;


        }


        if(modelcode==undefined || modelcode==""){
            alert("Please Enter Model Code to check Warranty Applicable");
            //document.getElementById("msg_saveFAILED").innerHTML="Please Enter ModelCode to check Warranty Applicable";
            return false;


        }


        var strURL="<%=cntxpath%>/serviceProcess.do?option=getWarrantyModeldetail&modelcode=" + modelcode+"&saledate="+saledate+"&tm=" + new Date().getTime();

        xmlHttp = GetXmlHttpObject();
        xmlHttp.open("POST", strURL, true);
        xmlHttp.send(null);

        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
            {
                res = trim(xmlHttp.responseText);
                if(res=="notexist" ){
                    alert("Model code entered by you does not exists. contact Administrator");
                    //document.getElementById("msg_saveFAILED").innerHTML="Model code entered by you does not exists. contact Administrator";
                    return false;
                }

                if(res!='' && res!="notexist" ){
                    result= res.split("@@");
                    document.getElementById("months").value=result[0];
                    document.getElementById("hrs").value=result[1];
                    compareDates();
                }
                else{


                }
            }
        }
    }

    function compareDates()
    {



        document.getElementById("msg_saveFAILED").innerHTML="";
        var mon=eval(document.getElementById("months").value);
        var hrs=eval(document.getElementById("hrs").value);
        var saledate=document.getElementById("Sale Date");




        if(document.getElementById("HMR WORKINGN").checked==false
            && document.getElementById("HMR WORKING").checked==false ){
    
            alert("Please Check HMR Working.");
            //document.getElementById("msg_saveFAILED").innerHTML="please check HMR Working";
            return false;

        }
        
        if(saledate.value.length==10 && document.getElementById("months").value!=null && document.getElementById("months").value!=""){
            var d1=new Date();

               
            var sale=saledate.value.split('/');
            var sdate=sale[2]+'/'+sale[1]+'/'+sale[0];
            var d2=new Date(sdate);

            var months;
            months = (d2.getFullYear() - d1.getFullYear()) * 12;
            months -= d1.getMonth() + 1;
            months += d2.getMonth();
            months=months+1;
            months=Math.abs(months);
            
            var daydiff=1;
            
            if(months>0)
                daydiff=eval(d1.getDate())-eval(sale[0]);

            

            if(document.getElementById("HMR WORKING").checked==true){

                hmr=document.getElementById("HMR").value;
                
                if(hmr=="" || hmr==undefined ){
                    alert("Please insert value in HRM");
                    //document.getElementById("msg_saveFAILED").innerHTML="Please insert value in HRM";

                    document.getElementById("Warranty App").innerHTML="<font color='red'></font>";
                    return false;
                }

                if(validNumberdigit(document.getElementById("HMR").value)==false){

                    alert("Enter numeric value in HRM");
                    //document.getElementById('msg_saveFAILED').innerHTML = "Enter numeric value in HRM";
                    return false;
                }

            }

            var hmr=eval(document.getElementById("HMR").value);
        
            if(document.getElementById("HMR WORKING").checked==true && document.getElementById("HMR").value=="0" )
            {
              
                document.getElementById("HMRLABEL").innerHTML="<div align='right'> <span class='formLabel'><label>HMR</label>&nbsp;<span class='mandatory'>*</span></span></div></td>"
                document.getElementById("HMRVALUE").innerHTML="<input name='hmr' type='text' id='HMR'  value='0' maxlength='' onblur='getdatehrs()'/>"
                document.getElementById("HMR").value="";

            }

            if(document.getElementById("HMR WORKING").checked==false)
            {
                document.getElementById("HMRLABEL").innerHTML="<div align='right'> <span class='formLabel'><label>HMR</label></span></div></td>"
                document.getElementById("HMRVALUE").innerHTML="<input name='hmr' type='text' id='HMR'  value='0' maxlength='' onblur='getdatehrs()'/>"
                document.getElementById("HMR").value="0";
            }


            if(hmr!="" && hmr!=undefined && document.getElementById("HMR WORKING").checked==true ){

                if((daydiff>0 && months<=eval(mon)) && hmr<=eval(hrs)){

                    document.getElementById("Warranty App").innerHTML="<font color='red'>Yes</font>&nbsp;&nbsp;<input type='button' value='Check Warranty' name='checkwarranty' id='Check Warranty' onclick='getdatehrs()'>";//<font color="red">Yes </font>
                    document.getElementById("warrantyApplicable").value="Y";
                    //warrantyApplicable
                }
                else{
                    document.getElementById("Warranty App").innerHTML="<font color='red'>No</font>&nbsp;&nbsp;<input type='button' value='Check Warranty' name='checkwarranty' id='Check Warranty' onclick='getdatehrs()'>";
                    document.getElementById("warrantyApplicable").value="N";
                }
            }

            else {

                if((daydiff>0 && months<=eval(mon))){
    
                    document.getElementById("Warranty App").innerHTML="<font color='red'>Yes</font>&nbsp;&nbsp;<input type='button' value='Check Warranty' name='checkwarranty' id='Check Warranty' onclick='getdatehrs()'>";//<font color="red">Yes </font>
                    document.getElementById("warrantyApplicable").value="Y";
                }

                else{
                    document.getElementById("Warranty App").innerHTML="<font color='red'>No</font>&nbsp;&nbsp;<input type='button' value='Check Warranty' name='checkwarranty' id='Check Warranty' onclick='getdatehrs()'>";
                    document.getElementById("warrantyApplicable").value="N";
                }
            }

        }
    }





</script>

<div class="breadcrumb_container">
    <ul>
       
              <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link> </li>
              <li class="breadcrumb_link"><bean:message key="label.common.pdi" />&nbsp;<bean:message key="label.common.jobcard" /></li>
              
          
    </ul>
</div>

<div class="message" id="msg_saveFAILED">${message}</div>
  <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="width: 100%">
                <h1 ><span class="formLabel"><bean:message key="label.common.pdi" />&nbsp;<bean:message key="label.common.jobcard" /></span></h1>


<c:if test="${serviceform.jobCardNo}" >
      
    <%@ include file="topbandshow.jsp" %>
</c:if>
<%--<div class="message1" id="msg_saveFAILED">${message}</div>--%>
<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >


    <tr><td>
            <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1">

                <tr align="center" valign="middle" class="formHeader">
                    <c:choose>
                        <c:when test="${serviceform.jobcardview eq 'true'}">
                            <td class="formSelectedHeader"><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initVehicleInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&jcStatus=${serviceform.status}&pathNm=fillJC" class="linkGreyBg"><bean:message key="label.common.vehicleinfo" /></a></label></td>
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initCustomerInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&jcStatus=${serviceform.status}&pathNm=fillJC" class="formLabel"><bean:message key="label.common.customerinfo" /></a></label></td>
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStandardChecks&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&jcStatus=${serviceform.status}&pathNm=fillJC" class="formLabel"><bean:message key="label.common.standardcheck" /></a></label></td>
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initComplaint&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&jcStatus=${serviceform.status}&pathNm=fillJC" class="formLabel"><bean:message key="label.common.complaint" /></a></label></td>
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initEstimate&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&jcStatus=${serviceform.status}&pathNm=fillJC" class="formLabel"><bean:message key="label.common.estimate" /></a></label></td>
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initActual&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=ViewJC" class="formLabel"><bean:message key="label.common.actuals" /></a></label></td>
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStatus&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&jcStatus=${serviceform.status}&pathNm=fillJC" class="formLabel"><bean:message key="label.common.status" /></a></label></td>
                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=init_vehicle_History&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&jcStatus=${serviceform.status}&pathNm=fillJC" class="formLabel"><bean:message key="label.common.history" /></a></label></td>
                            </c:when>
                         <c:otherwise>
                            <td class="formSelectedHeader"><label><a href="<%=cntxpath%>/serviceOptProcess.do?option=initJobCardForPDI&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&jcStatus=${serviceform.status}&pathNm=fillJC" class="linkGreyBg"><bean:message key="label.common.vehicleinfo" /></a></label></td>
                            <td><label><a href="#" class="formLabel"><bean:message key="label.common.customerinfo" /></a></label></td>
                            <td><label><a href="#" class="formLabel"><bean:message key="label.common.standardcheck" /></a></label></td>
                            <td><label><a href="#" class="formLabel"><bean:message key="label.common.complaint" /></a></label></td>
                            <td><label><a href="#" class="formLabel"><bean:message key="label.common.estimate" /></a></label></td>
                            <td><label><a href="#" class="formLabel"><bean:message key="label.common.actuals" /></a></label></td>
                            <td><label><a href="#" class="formLabel"><bean:message key="label.common.status" /></a></label></td>
                            <td><label><a href="#" class="formLabel"><bean:message key="label.common.history" /></a></label></td>
                           </c:otherwise>
                      </c:choose>
                </tr>
            </table>
        </td></tr>



    <tr><td><form action="<%=cntxpath%>/serviceCreateJC.do?option=manageVehileInformation&jctype=JPDI&pathNm=fillJC" method="post" id="vehicleform" >
                <table width="100%" border="0" align="center" cellpadding="1" cellspacing="5"  >
                    <tbody>
                        <tr>
                            <td  align="right"><span class="formLabel">
                                    <label><bean:message key="label.common.Productcategory" /></label></span></td>
                            <td align="left"  >
                                <c:choose>
                                    <c:when test="${serviceform.jobcardview eq 'true' }">
                                        <select name="productCategory"  id="productCategory" disabled/>
                                    </c:when>
                                    <c:otherwise>
                                        <select name="productCategory"  id="productCategory">
                                        </c:otherwise>
                                    </c:choose>

                                    <c:forEach items="${productcategoryList}" var="dataList">

                                        <c:if test="${serviceform.productionCategory eq dataList.value }">

                                            <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>

                                        </c:if>

                                        <c:if test="${serviceform.productionCategory ne dataList.value }">

                                            <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>

                                        </c:if>

                                    </c:forEach>
                                </select>
                            </td>

                            <td align="right" style="" ><span class="formLabel">
                                    <label><bean:message key="label.common.Jobtype" /></label>&nbsp;<span class="mandatory">*</span>
                                </span></td>
                            <td align="left">
                                <c:choose>
                                    <c:when test="${serviceform.jobcardview eq 'true' }">
                                        <select name="jobType"  id="Job Type" disabled/>
                                    </c:when>
                                    <c:otherwise>
                                        <select name="jobType"  id="Job Type">
                                        </c:otherwise>
                                    </c:choose>
                                    <option value="">--Select--</option>
                                    <c:forEach items="${jobTypeList}" var="dataList">

                                        <c:if test="${serviceform.jobType eq dataList.value}">

                                            <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>

                                        </c:if>

                                        <c:if test="${serviceform.jobType ne dataList.value }">

                                            <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>

                                        </c:if>


                                    </c:forEach>
                                </select>

                            </td>   

                            <!--                            <td width="197" align="right" ><span class="formLabel">
                                                                <label>Job Card No</label>
                                                                .</span></td>
                                                        <td width="110" align="left" >
                                                            <input name="jobCardNo" type="text" id="Job Card No" maxlength="" readonly value="${jobid}" />
                                                        </td>-->
                            <td  align="right" ><span class="formLabel">
                                    <label><bean:message key="label.common.Modelfamily" /></label>
                                </span></td>

                            <td  align="left" >
                                <c:choose>
                                    <c:when test="${serviceform.vin_details_type eq 'DB'  }">
                                        <input name="modelFamily" type="text" id="Model Family"  maxlength="" value="${serviceform.modelFamily}" readonly="true"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input name="modelFamily" type="text" id="Model Family"  maxlength="" value="${serviceform.modelFamily}" />
                                    </c:otherwise>
                                </c:choose>
                                <input name="jobcardview" type="hidden" value="${serviceform.jobcardview }">
                                <input name="jobcardno" type="hidden" value="${serviceform.jobCardNo }">
                            </td>

                        </tr>
                        <tr>
                            <td align="right"><span class="formLabel">
                                    <label><bean:message key="label.common.Chassisno" /></label>&nbsp;<span class="mandatory">*</span></span></td>
                            <td  align="left"  >
                                <c:choose>
                                    <c:when test="${serviceform.jobcardview eq 'true' or serviceform.vin_details_type eq 'DB' }">
                                        <input name="vinNo" type="text" id="VIN No." maxlength="50" value="${serviceform.vinNo}" readonly />
                                    </c:when>
                                    <c:otherwise>
                                        <input name="vinNo" type="text" id="VIN No." maxlength="50" value="${serviceform.vinNo}" />
                                        <img src='<%=cntxpath%>/image/arrdown.gif' alt='Get Suggestions' name="img1" border='0'  onclick="getVinDetails('VIN No.',document.getElementById('VIN No.'),document.getElementById('img'),'JPDI');"/>
                                        <img alt=""  id='img' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                        <input type="hidden" name="check" value="notexist" id ="check">
                                    </c:otherwise>
                                </c:choose>
                            </td>


                            <td align="right" style="" ><span class="formLabel">
                                    <label><bean:message key="label.common.Dealerjobcardno" /></label>
                                </span>
                                <input name="jobcardno" type="hidden" value="${serviceform.jobCardNo}">
                            </td>

                            <td align="left">
                             <input name="vinid" type="hidden" id="vinid" >
                            <input name="dealerJobCardNo" type="text" class="" id="Dealer Job Card No" value="${serviceform.dealerJobCardNo}"/>
                                  
                            </td>




                            <td align="right"><span class="formLabel">
                                    <label><bean:message key="label.common.Modelcode" /></label>
                                </span></td>
                            <td align="left" >
                                <c:choose>
                                    <c:when test="${serviceform.vin_details_type eq 'DB' }">
                                        <input name="modelCode" type="text" id="Model Code"  maxlength="" value="${serviceform.modelCode}" readOnly="true"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input name="modelCode" type="text" id="Model Code"  maxlength="" value="${serviceform.modelCode}" />
                                    </c:otherwise>
                                </c:choose>
                            </td>

                        </tr>
                        <tr>
                            <td align="right" style="" ><span class="formLabel">
                                    <label><bean:message key="label.common.Saledate" /></label>&nbsp;<span class="mandatory">*</span>
                                </span></td>
                            <td align="left" id="saledatetd">
                                <c:choose>
                                    <c:when test="${serviceform.vin_details_type eq 'DB' }">
                                        <input name="saleDate1" type="text"  id="Sale Date1" value="${serviceform.saleDate}" readonly/>
                                        <input name="saleDate" type="hidden" class="datepicker" id="Sale Date" value="${serviceform.saleDate}" />
                                    </c:when>
                                    <c:otherwise>
                                        <input name="saleDate" type="text" class="datepicker" id="Sale Date" value="${serviceform.saleDate}" onblur="comparedt(this)"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <td align="right" style="" ><div align="right"> <span class="formLabel">
                                        <label><bean:message key="label.common.hrmworking" /></label>&nbsp;<span class="mandatory">*</span>
                                    </span></div></td>
                            <td align="left" >
                                <input type="hidden" name="hmeRadio" id="HMR WORKINGN"  value="Y"/>
                                <c:if test="${serviceform.hmeRadio eq 'Y'}">
                                    <input name="hmeRadio" id="HMR WORKING" type="radio" value="Y" checked="checked" onclick="getdatehrs()"/>
                                    <span class="formLabel">Yes</span>
                                    <%--<input name="hmeRadio"  type="radio" id="HMR WORKINGN" value="N" onclick="getdatehrs()"/>
                                    <span class="formLabel">No</span>--%>
                                </c:if>
                                <c:if test="${serviceform.hmeRadio eq 'N'}">
                                    <input name="hmeRadio" type="radio" id="HMR WORKING" value="Y" onclick="getdatehrs()"/>
                                    <span class="formLabel">Yes</span>
                                    <%--<input name="hmeRadio" type="radio" value="N" id="HMR WORKINGN" checked="checked" onclick="getdatehrs()"/>
                                    <span class="formLabel">No</span>--%>
                                </c:if>

                                <c:if test="${serviceform.hmeRadio eq null or serviceform.hmeRadio eq '' }">
                                    <input name="hmeRadio" type="radio" value="Y"  id="HMR WORKING" checked="checked"  onclick="getdatehrs()"/>
                                    <span class="formLabel">Yes</span>
                                    <%--<input name="hmeRadio" type="radio" value="N" id="HMR WORKINGN" onclick="getdatehrs()"/>
                                    <span class="formLabel">No</span>--%>
                                   
                                </c:if>

                            </td>




                            <td align="right" style=""><div align="right"> <span class="formLabel">
                                        <label><bean:message key="label.common.Modelfamilydesc" /></label>.
                                    </span></div></td>
                            <td align="left">
                                <c:choose>
                                    <c:when test="${serviceform.vin_details_type eq 'DB' }">
                                        <input name="modelFamilyDesc" type="text" id="Model Family Desc"  maxlength="" value="${serviceform.modelFamilyDesc}" readonly/>
                                    </c:when>
                                    <c:otherwise>
                                        <input name="modelFamilyDesc" type="text" id="Model Family Desc"  maxlength="" value="${serviceform.modelFamilyDesc}"/>
                                    </c:otherwise>
                                </c:choose>

                            </td>

                        </tr>

                        <tr>

                            <td align="right"><span class="formLabel">
                                    <label><bean:message key="label.common.engineno" /></label>
                                </span></td>
                            <td align="left" >
                                <c:choose>
                                    <c:when test="${serviceform.vin_details_type eq 'DB' }">
                                        <input name="engineNumber" type="text" id="Engine Number" value="${serviceform.engineNumber}" readonly="true"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input name="engineNumber" type="text" id="Engine Number" value="${serviceform.engineNumber}"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>





                            <td align="right" style="" id="HMRLABEL"><div align="right"> <span class="formLabel">
                                        <label><bean:message key="label.common.hrm" /><span class="mandatory">*</span></label>
                                    </span></div></td>
                            <td align="left" id="HMRVALUE">
                                <input name="hmr" type="text" id="HMR"  value="${serviceform.hmr}" maxlength="" onblur="getdatehrs()"/>
                            </td>

                            <td align="right" style="" ><div align="right"> <span class="formLabel">
                                        <label><bean:message key="label.common.Modelcodedesc" /></label>
                                    </span></div></td>
                            <td align="left">
                                <c:choose>
                                    <c:when test="${serviceform.vin_details_type eq 'DB' }">
                                        <input name="modelCodeDesc" type="text" id="Model Code Desc." maxlength="" value="${serviceform.modelCodeDesc}" readonly="true"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input name="modelCodeDesc" type="text" id="Model Code Desc." maxlength="" value="${serviceform.modelCodeDesc}"/>
                                    </c:otherwise>
                                </c:choose>

                            </td>
                        </tr>
                        <tr>


                            <td align="right"><span class="formLabel">
                                    <label><bean:message key="label.common.Registrationno" /></label>
                                </span></td>
                            <td align="left" >
                                <c:choose>
                                    <c:when test="${serviceform.vin_details_type eq 'DB' }">
                                        <input name="registrationNo" type="text" id="Registration No." maxlength="" value="${serviceform.registrationNo}" readonly="true"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input name="registrationNo" type="text" id="Registration No." maxlength="" value="${serviceform.registrationNo}"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>

                            <td align="right" style="" ><span class="formLabel">
                                    <label><bean:message key="label.common.event" /></label>
                                </span></td>
                            <td align="left">

                                <select name="event"  id="event">
                                    <option value='0' title='NA' >NA</option>
                                    <c:forEach items="${eventList}" var="dataList">
                                        <c:if test="${serviceform.event eq dataList.value }">

                                            <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>

                                        </c:if>

                                        <c:if test="${serviceform.event ne dataList.value }">

                                            <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>

                                        </c:if>

                                    </c:forEach>
                                </select>

                            </td>


                            <td align="right" style="" ><div align="right"> <span class="formLabel">
                                        <label><bean:message key="label.common.location" /></label>&nbsp;<span class="mandatory">*</span>
                                    </span></div></td>
                            <td align="left">
                                <select name="jobLocation"  id="jobLocation">
                                    <c:forEach items="${jobLocationList}" var="dataList">
                                        <c:if test="${serviceform.jobLocation eq dataList.value }">

                                            <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>

                                        </c:if>

                                        <c:if test="${serviceform.jobLocation ne dataList.value }">

                                            <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>

                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>





                        </tr>
                        <tr>


                            <td align="right" style="white-space: nowrap"><span class="formLabel">
                                    <label><bean:message key="label.common.Servicebookletno" /></label>
                                </span>
                            </td>
                            <td align="left" >
                                <c:choose>
                                    <c:when test="${serviceform.vin_details_type eq 'DB' }">
                                        <input name="serviceBookletNo" type="text" id="Service Booklet No."  maxlength="" value="${serviceform.serviceBookletNo}" readonly="true"/>
                                    </c:when>
                                    <c:otherwise>
                                        <input name="serviceBookletNo" type="text" id="Service Booklet No."  maxlength="" value="${serviceform.serviceBookletNo}"/>
                                    </c:otherwise>
                                </c:choose>
                            </td>


                            <td align="right" style="" ><div align="right"> <span class="formLabel">
                                        <label><bean:message key="label.common.servicetype" /></label></span></div></td>
                            <td align="left">
                                <select name="serviceType"  id="serviceType">
                                    <c:forEach items="${serviceTypeList}" var="dataList">
                                        <c:if test="${serviceform.serviceType eq dataList.value }">

                                            <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>

                                        </c:if>

                                        <c:if test="${serviceform.serviceType ne dataList.value }">

                                            <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>

                                        </c:if>
                                    </c:forEach>
                                </select>
                            </td>

                            <td align="right"><span class="formLabel">
                                    <label><bean:message key="label.common.tractorindate" /> </label>&nbsp;<span class="mandatory">*</span>
                                </span></td>
                            <td align="left" style="white-space: nowrap">
                                <input name="jobCardDate" type="text" class="datepicker" id="Tractor In Date" size="15" value="${serviceform.jobCardDate}" onblur="comparedt(this)"/>&nbsp;<select name="currentEstimateTime"  id="currentEstimateTime" style="width:40px !important ">
                                    <option value="00">00</option>
                                    <option value="01">01</option>
                                    <option value="02">02</option>
                                    <option value="03">03</option>
                                    <option value="04">04</option>
                                    <option value="05">05</option>
                                    <option value="06">06</option>
                                    <option value="07">07</option>
                                    <option value="08">08</option>
                                    <option value="09" selected>09</option>
                                    <option value="10">10</option>
                                    <option value="11">11</option>
                                    <option value="12">12</option>
                                    <option value="13">13</option>
                                    <option value="14">14</option>
                                    <option value="15">15</option>
                                    <option value="16">16</option>
                                    <option value="17">17</option>
                                    <option value="18">18</option>
                                    <option value="19">19</option>
                                    <option value="20">20</option>
                                    <option value="21">21</option>
                                    <option value="22">22</option>
                                    <option value="23">23</option>
                                </select>
                                :
                                <select name="currentEstimateHrs" id="currentEstimateHrs"  style="width:40px !important ">
                                    <option value="00">00</option>
                                    <option value="05">05</option>
                                    <option value="10">10</option>
                                    <option value="15">15</option>
                                    <option value="20">20</option>
                                    <option value="25">25</option>
                                    <option value="30" selected>30</option>
                                    <option value="35">35</option>
                                    <option value="40">40</option>
                                    <option value="45">45</option>
                                    <option value="50">50</option>
                                    <option value="55">55</option>
                                </select>
                            </td>


                        </tr>
                        <tr>



                        </tr>
                        <tr>

                            <td align="right" style="white-space: nowrap"><span class="formLabel">
                                    <label><bean:message key="label.common.Couponno" /></label>
                                </span>
                            </td>
                            <td align="left" >
                                <input name="CouponNo" type="text" id="Coupon No"  maxlength="" value="${serviceform.couponno}"/></td>





                            <td align="right"><span class="formLabel">
                                    <label><bean:message key="label.common.Ownerdriven" /></label>
                                </span></td>
                            <td align="left" >
                                <select name="ownerDriven"  id="ownerDriven">
                                    <c:forEach items="${ownerDrivenList}" var="dataList">
                                        <c:if test="${serviceform.ownerDriven eq dataList.value }">

                                            <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>

                                        </c:if>

                                        <c:if test="${serviceform.ownerDriven ne dataList.value }">

                                            <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>

                                        </c:if>

                                    </c:forEach>
                                </select>
                            </td>



                            <td align="right" style=""><div align="right"> <span class="formLabel">
                                        <label><bean:message key="label.common.Jobcardstatus" /></label></span></div></td>
                            <td align="left" >

                                <select name="jobCardStatus"  id="jobCardStatus">
                                    <c:choose>
                                        <c:when test="${serviceform.jobcardview eq 'true' }">
                                            <c:forEach items="${jobCardStatusList}" var="dataList">
                                                <c:if test="${serviceform.status eq dataList.label}">

                                                    <option value='${dataList.label}'>${dataList.label}</option>
                                                </c:if>
                                            </c:forEach></c:when>
                                        <c:otherwise>
                                            <c:forEach items="${jobCardStatusList}" var="dataList">
                                                <option value='${dataList.label}' title='${dataList.label}' >${dataList.label}</option>

                                            </c:forEach>

                                        </c:otherwise>
                                    </c:choose>

                                </select>
                            </td>

                        </tr>


                        <tr>


                            <td align="right"><span class="formLabel">
                                    <label><bean:message key="label.common.nextservicefromhere" /></label>&nbsp;<span class="mandatory">*</span>
                                </span>
                            </td>
                            <td align="left">
                                <select name="nextService"  id="nextService">
                                    <c:forEach items="${nextServiceList}" var="dataList">
                                        <c:if test="${serviceform.nextService eq dataList.value }">

                                            <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>

                                        </c:if>

                                        <c:if test="${serviceform.nextService ne dataList.value }">

                                            <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>

                                        </c:if>

                                    </c:forEach>
                                </select>
                            </td>
                            <td align="right"><span class="formLabel">
                                    <label>
                                        <bean:message key="label.common.Jobcardpriority" /></label>
                                </span>
                            </td>
                            <td align="left">
                                
                                <select name="jobcardpriority"  id="Job card Priority">
                                    <c:choose>
                                        
                                        <c:when test="${serviceform.jobcardpriority eq 'moderate' }">
                                             <option value='Critical' >Critical</option>
                                             <option value='moderate' selected>Moderate</option>
                                             <option value='Low' >Low</option>
                                        </c:when>
                                        <c:when test="${serviceform.jobcardpriority eq 'Low' }">
                                            <option value='Critical' >Critical</option>
                                            <option value='moderate' >Moderate</option>
                                            <option value='Low' selected>Low</option>
                                        </c:when>

                                        <c:otherwise>
                                            <option value='Critical' >Critical</option>
                                            <option value='moderate' >Moderate</option>
                                            <option value='Low' >Low</option>
                                        </c:otherwise>
                                    </c:choose>


                                </select>
                                <input type="hidden" value="" name="months" id="months">
                                <input type="hidden" value="" name="hrs" id="hrs">
                                <input type="hidden" value="MANUAL" name="vindatatype" id="vindatatype">
                                <input type="hidden" value="${serviceform.warrantyApplicable}" name="warrantyApplicable" id="warrantyApplicable">

                            </td>


                            <td align="right" style="" ><span class="formLabel">

                                    <label><bean:message key="label.common.warrantyapplicable" /></label>
                                </span></td >
                            <td align="left" id="Warranty App"> <font color="red"> </font> <c:choose>
                                    <c:when test="${serviceform.warrantyApplicable eq 'Y'}">
                                        : Yes
                                    </c:when>
                                    <c:when test="${serviceform.warrantyApplicable eq 'N'}">
                                        : No
                                    </c:when>
                                    <c:otherwise>


                                    <font color="red">YES</font>
                          



                                    </c:otherwise>
                                </c:choose>
                                <c:if test="${serviceform.status ne 'CLOSE' and jobType ne 'PDI'}">
                                    <input type="button" value="Check Warranty" name="checkwarranty" id="Check Warranty" onclick="getdatehrs()">
                                </c:if>


                            </td>

                        </tr>
                        <tr>
                            
                        </tr>
                        <tr align="center">
                            <td colspan="6" >
                                <c:if test="${serviceform.status ne 'CLOSE'}">
                                    <input name="input" type="button" value="Save" style="float:none; " onclick="validateForm();"/>
                                </c:if></td>
                        </tr>
                </table>
            </form>
        </td>
    </tr>
</table>

            </div>
        </div>
  </center>