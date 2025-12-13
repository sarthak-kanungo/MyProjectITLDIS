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
<script language="javascript" src="${pageContext.request.contextPath}/js/alert_message.js"></script>
<link rel="stylesheet" href="layout/css/login.css" type="text/css" />
<link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/css/calendar.css" media="screen" />
<link href="${pageContext.request.contextPath}/css/dynCalendar.css" rel="stylesheet" type="text/css"/>
<link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/Master_290414.css" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css"  type="text/css"/>




<script language="JavaScript" src='${pageContext.request.contextPath}/js/validation.js'></script>

<script language="JavaScript" src='${pageContext.request.contextPath}/js/alert_message.js'></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script type="text/javascript"src="${pageContext.request.contextPath}/js/Master_290414.js"></script>

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
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_4.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/commonValidations.js"></script>

<script type="text/javascript" language="javascript">

    var contextPath = '${pageContext.request.contextPath}';
    var manualEntry = "";

    <%--function validate()
    {


        document.getElementById("serviceForm").submit();
        //        var jobType=document.forms[0].jobType.value;

    }
    
     --%>
    
   /*   $(document).ready(function() {
    	 $("#HMR").prop("disabled", true).css("background-color", "#E6E4E4").css("border","solid 1px #CCCCCC");
    });
     */
    
   


        function chassisfromViewPDI(){
            if("${jobTypeStatus}"=="PDI"){
                var obj=document.getElementById("vinid");
                document.getElementById("vinid").value="${chassisno}";
                document.getElementById("VIN No.").value="${chassisno}";
                document.getElementById("Job Type").value="1";
                getvinNoData(document.getElementById("vinid"))

            }
        }
        //changeLang('hi_IN');


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
            var strURL="<%=cntxpath%>/serviceCreateJC.do?option=getVimNumberDetails&vinNo=" + vinno+"&tm=" + new Date().getTime();
            xmlHttp = GetXmlHttpObject();

            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    res = trim(xmlHttp.responseText);


                    if(res!='' && res.indexOf('@@') !=-1){
                        manualEntry = false;
                        result= res.split("@@");
                        document.getElementById("Engine Number").value=result[0];
                        id.value=id.value.trim();
                        document.getElementById("Sale Date").value=result[1];
                        //document.getElementById("Sale Date").setAttribute("class", "");

                        document.getElementById("saledatetd").innerHTML="<input name='deliveryDate'  type='text'  id='Sale Date' value='"+result[1]+"' readonly/>";

                        document.getElementById("Registration No.").value=result[2];
                        document.getElementById("Service Booklet No.").value=result[3];
                        document.getElementById("Owner Driven").value=result[5];
                        document.getElementById("Model Family").value=result[6];
                        document.getElementById("Model Code").value=result[7];
                        document.getElementById("Model Family Desc").value=result[8];
                        document.getElementById("Model Code Desc.").value=result[9];

                        if(result[11]==null || result[11]=="null" || result[11]=="")
                            document.getElementById("Nextservice From Here").value="NO";
                        else
                            document.getElementById("Nextservice From Here").value=result[11];
                        document.getElementById("vinid").value= result[12];
                        document.getElementById("vin_details_type_vehicle").value= result[13];
                        document.getElementById("vindatatype").value="DB"
                        document.getElementById("VIN No.").readOnly = true;

                        document.getElementById("Dealer Job Card No").focus();

                        document.getElementById('img').style.visibility="hidden";


                        document.getElementById("Engine Number").readOnly = true;
                        // document.getElementById("saledatetd").innerHTML="<input name='saleDate' type='text'  id='Sale Date' value='"+result[1]+"' readonly/>";
                        document.getElementById("Sale Date").readOnly = true;

                        document.getElementById("Registration No.").readOnly = true;
                        document.getElementById("Service Booklet No.").readOnly = true;
                        document.getElementById("Owner Driven").readOnly = true;
                        document.getElementById("Model Family").readOnly = true;
                        document.getElementById("Model Code").readOnly = true;
                        document.getElementById("Model Family Desc").readOnly = true;
                        document.getElementById("Model Code Desc.").readOnly = true;
                        document.getElementById("Nextservice From Here").readOnly = true;
                        document.getElementById("vindatatype").value = "DB";
                        document.getElementById("check").value="exist";
                        Set_color("#E6E4E4");


                        if(result[13]=!""){

                            var sdate=result[1];
                            var mon=result[12];
                            var hr=result[13];

                        }

                        else{

                            //      document.getElementById("Warranty App").innerHTML="<input name='warrantyapp' type='radio' value='Y' checked='checked'/><span class='formLabel'>Yes</span><input name='warrantyapp' type='radio' value='N'/><span class='formLabel'>No</span>";

                        }

                    }


                    else{
                        //EngineNo,SaleDate,RegNo,SerBookNo,KeyIdentification,OwnerDriven,ModelFamily,
                        //ModelCode,ModelFamilyDesc,ModelDesc,DealerCode,NextService

                        document.getElementById("Engine Number").value="";
                        document.getElementById("Sale Date").value="";
                        document.getElementById("Registration No.").value="";
                        document.getElementById("Service Booklet No.").value="";
                        document.getElementById("Owner Driven").value="";
                        document.getElementById("Model Family").value="";
                        document.getElementById("Model Code").value="";
                        document.getElementById("Model Family Desc").value="";
                        document.getElementById("Model Code Desc.").value="";
                        document.getElementById("Nextservice From Here").value="";




                    }

                }
            }
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);


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

                var today = new Date(ttdate);

                var tractorindate=document.getElementById("Tractor In Date").value;

                if(tractorindate!=""){
                    $( ".datepicker" ).datepicker({ dateFormat: 'dd/mm/yy'});
                }else{

                  $( ".datepicker" ).datepicker( "setDate", ttdate);
                }
            });
        });

        $(document).ready(function () {

            $(function() {
                $( ".datepickerSale" ).datepicker({ dateFormat: 'dd/mm/yy' });
            });
        });






        function validateForm()
        {

            var ck_code = /^[a-zA-Z\d\,\-\/\_\ ]+$/;
            var cks_code = /^[a-zA-Z\d\-\/]+$/;
            //compaignname,compaigndesc,compaigndate,compaignamount
            if(document.getElementById("msg_saveFAILED")!=null)
                document.getElementById("msg_saveFAILED").innerHTML="";
            //Engine Number,Sale Date,Registration No.  Service Booklet No. ,ownerDriven,Model Family
            //Model Code,Model Family Desc,Model Code Desc. nextService   //Sale Date  , Tractor In Date


            //
            var fromdb = new Array('VIN No.','Model Family','Model Code','Model Family Desc','Engine Number','Model Code Desc.','Registration No.','Service Booklet No.')

            var notfromdb = new Array('Dealer Job Card No','Coupon No');




            var elementArr = new Array('VIN No.','Service Type', 'Job Type','Model Family' ,'Model Code','HMR WORKING','HMR WORKINGN','Location','Event','Nextservice From Here','Owner Driven','Tractor In Date');

            var strObject = null;

            for (var i = 0; i < elementArr.length; i++)
            {
                strObject = document.getElementById(elementArr[i]);

                if (strObject.value=="" && i==0)
                {
                    alert(notblankchassisno_validation_msg);
                    strObject.focus();

                    //document.getElementById('msg_saveFAILED').innerHTML="Please enter Chassis No";
                    return false;
                }
                if(strObject.value=="" && i>0)
                {
                    alert(not_blank_validation_msg + elementArr[i] + "");
                    strObject.focus();
                    //document.getElementById('msg_saveFAILED').innerHTML = "Please enter value in " + elementArr[i] + " field.";
                    return false;
                }




                if(elementArr[i]=="VIN No." ){

                    if(cks_code.test(document.getElementById(elementArr[i]).value)==false){
                        alert(invalidchassisno_validation_msg);

                        document.getElementById(elementArr[i]).focus();
                        return false;
                    }

                }
                if(elementArr[i]=="HMR WORKING" || elementArr[i]=="HMR WORKINGN"){

                    if(document.getElementById("HMR WORKING").checked==false && document.getElementById("HMR WORKINGN").checked==false){

                        alert(hmrworking_validation_msg);

                        return false;

                    }
                }

            }


            if(document.getElementById("HMR").value !=""){
                if(validNumberdigit(document.getElementById("HMR").value)==false){
                    alert(numberhmr_validation_msg);

                    document.getElementById("HMR").focus();
                    return false;
                }
            }

            if(document.getElementById("Sale Date").value==""){

                if(document.getElementById("Sale Date").value=="" &&  document.getElementById("Job Type").value!="1" &&  document.getElementById("Job Type").value!="41"){
                    alert(saledate_validation_msg);
                    return false;
                }
            }



            if(document.getElementById("HMR WORKING").checked==true)
            {
                if(document.getElementById("HMR").value==""){
                    alert(notblankhmr_validation_msg);
                    document.getElementById("HMR").focus();
                    return false;
                }
            }

            if(document.getElementById("Coupon No").value!="")
            {
                if(ck_code.test(document.getElementById("Coupon No").value)==false){
                    alert(invalidcoupon_validation_msg);
                    document.getElementById("Coupon No").focus();
                    return false;
                }
            }
            if(document.getElementById("Dealer Job Card No").value!="")
            {
                if(ck_code.test(document.getElementById("Dealer Job Card No").value)==false){
                    alert(invaliddealerjobcardno_validation_msg);
                    document.getElementById("Dealer Job Card No").focus();
                    return false;

                }
            }
            
            if(document.getElementById("vorJobcard").value==""){
                alert("Please Select VOR Job card");
                document.getElementById("vorJobcard").focus();
                return false;
            }

            var saledate=document.getElementById("Sale Date").value;

            var tractorindate=document.getElementById("Tractor In Date").value;



            var d1=new Date();


            var sale=saledate.split('/');
            var sdate=sale[2]+'/'+sale[1]+'/'+sale[0];
            var d2=new Date(sdate);



            var tractor=tractorindate.split('/');
            var tdate=tractor[2]+'/'+tractor[1]+'/'+tractor[0];
            var d3=new Date(tdate);





            var ttdate="";
            var tmon="<%=month%>";
            var tdays="<%=day%>";

            if(eval(tmon)<10)
                tmon="0"+tmon;

            if(eval(tdays)<10)
                tdays="0"+tdays;

            ttdate=<%=year%>+'/'+tmon+'/'+tdays;

            var today = new Date(ttdate);




            if(today < d2)
            {
                document.getElementById("Sale Date").value==""

                alert(saledatecurrent_validation_msg);
                document.getElementById("Sale Date").focus();
                return false;

            }



            if(today < d3)
            {
                document.getElementById("Tractor In Date").value==""

                alert(tractorindate_validation_msg);

                return false;

            }

            //event dates
            var etsdate=document.getElementById("eventstartdate").value.split('/');
            var esdate=etsdate[2]+'/'+etsdate[1]+'/'+etsdate[0];
            var des=new Date(esdate);

            var etedate=document.getElementById("eventenddate").value.split('/');
            var eedate=etedate[2]+'/'+etedate[1]+'/'+etedate[0];
            var dee=new Date(eedate);



            var constantval=${serviceform.constantValue}

            var condate =new Date(ttdate);

            condate.setDate(condate.getDate()-constantval);
            var jobcardDateStr='${serviceform.jobCardDate}'
            if(jobcardDateStr==''||jobcardDateStr=='null'){
             if(condate>d3 || today<d3){
                document.getElementById("Tractor In Date").style.border="1px solid #ff0000";
                alert(tractorwith_validation_msg +" "+constantval+" "+dat_validation_msg2);
                document.getElementById("Tractor In Date").select();
                return false;

            }
            else{
                document.getElementById("Tractor In Date").style.border="1px solid #cccccc";
            }
            if(document.getElementById("Event").value!="0"){
                if(des>d3 || dee<d3){
                    document.getElementById("Tractor In Date").style.border="1px solid #ff0000";
                    alert(eventdate_validation_msg +" ("+document.getElementById("eventstartdate").value+" -"+document.getElementById("eventenddate").value+")");
                    document.getElementById("Tractor In Date").select();
                    return false;

                }
                else{
                    document.getElementById("Tractor In Date").style.border="1px solid #cccccc";
                }

            }
            }
            if(document.getElementById("HMR WORKING").checked==false && document.getElementById("HMR WORKINGN").checked==false){

                alert(hmrworking_validation_msg);

                return false;

            }

            if(document.getElementById("Job Type").value != "41" && document.getElementById("Job Type").value !="1"){
              getdatehrs();
            }
            
            actualHmr();
            var jobType = document.getElementById("Job Type").value;
            submitform();
           
           /*  var res = 	checkITLExtWtyPolicyStatus();
            if(res  == "N"){
            	document.getElementById("warrantyapplicable").value;
            	document.getElementById("itlExtWarrantyApplicable").value;
                itlExtWarrantyApplicable.value=res;
            	return false;
            }
            else{
            	document.getElementById("warrantyapplicable").value=res;
            	document.getElementById("itlExtWarrantyApplicable").value=res;
            }  */
        }

        function msgfunction()
        {

            var vinno=document.getElementById("VIN No.").value;

            document.getElementById("msg_saveFAILED").innerHTML="";

            var x="false";
            var r=confirm(sysncdata_confirmation_msg);
            if (r==true)
            {
                x="true";
            }
            else
            {
                x="false";
            }


            if(x=="true"){
                var strURL="<%=cntxpath%>/serviceCreateJC.do?option=getVimNumberDetailsFromServer&vinNo=" + vinno+"&pathNm=fillJC&tm=" + new Date().getTime();
                xmlHttp = GetXmlHttpObject();
                xmlHttp.onreadystatechange = function()
                {
                    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                    {
                        res = trim(xmlHttp.responseText);

                        //res = trim(xmlHttp.responseText);
                        if(res=="notexist" ){
                            alert(sysncdata_validation_msg+vinno);
                            //document.getElementById("msg_saveFAILED").innerHTML="This Chassis No does not exists on Sonalika ITL server. . Kindly contact Administrator \nNo data found for chassis no - "+vinno;
                            return false;
                        }
                        manualEntry = false;


                        if(res!='' && res!="notexist" ){

                            document.getElementById("msg_saveFAILED").innerHTML="";
                            //EngineNo,SaleDate,RegNo,SerBookNo,KeyIdentification,OwnerDriven,ModelFamily,
                            //ModelCode,ModelFamilyDesc,ModelDesc,DealerCode,NextService
                            result= res.split("@@");//vinno
                            document.getElementById("VIN No.").value=vinno;
                            document.getElementById("Engine Number").value=result[0];
                            document.getElementById("Sale Date").value=result[1];
                            document.getElementById("saledatetd").innerHTML="<input name='deliveryDate' type='text'  id='Sale Date' value='"+result[1]+"' readonly/>";
                            document.getElementById("Registration No.").value=result[2];
                            document.getElementById("Service Booklet No.").value=result[3];
                            document.getElementById("Owner Driven").value=result[5];
                            document.getElementById("Model Family").value=result[6];
                            document.getElementById("Model Code").value=result[7];
                            document.getElementById("Model Family Desc").value=result[8];
                            document.getElementById("Model Code Desc.").value=result[9];
                            document.getElementById("Nextservice From Here").value=result[11];
                            document.getElementById("vin_details_type_vehicle").value= result[13];
                            document.getElementById("vindatatype").value = "DB";

                            //document.getElementById("textboxid").readOnly = true;

                            document.getElementById("VIN No.").readOnly = true;

                            document.getElementById("Engine Number").readOnly = true;
                            document.getElementById("Sale Date").readOnly = true;

                            document.getElementById("saledatetd").innerHTML="<input name='deliveryDate' type='text'  id='Sale Date' value='"+result[1]+"' readonly/>";

                            document.getElementById("Registration No.").readOnly = true;
                            document.getElementById("Service Booklet No.").readOnly = true;
                            document.getElementById("Owner Driven").readOnly = true;
                            document.getElementById("Model Family").readOnly = true;
                            document.getElementById("Model Code").readOnly = true;
                            document.getElementById("Model Family Desc").readOnly = true;
                            document.getElementById("Model Code Desc.").readOnly = true;
                            document.getElementById("Nextservice From Here").readOnly = true;
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



            var strURL="<%=cntxpath%>/serviceCreateJC.do?option=getWarrantyModeldetail&modelcode=" + modelcode+"&saledate="+saledate+"&tm=" + new Date().getTime();

            xmlHttp = GetXmlHttpObject();
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);

            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    res = trim(xmlHttp.responseText);

                    if(res!='' && res!="notexist" ){
                        result= res.split("@@");
                        document.getElementById("months").value=result[0];
                        document.getElementById("hrs").value=result[1];
                        if(document.getElementById("Job Type").value!="1")
                            compareDates();
                    }
                    if(document.getElementById("Job Type").value=="35"){
                            checkExtWtyPolicyStatus();
                    }else{
                      submitform();
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




            if(saledate.value.length==10 && document.getElementById("months").value!=null && document.getElementById("months").value!=""){

                var tractorindate=document.getElementById("Tractor In Date");
                var tractindt=tractorindate.value.split('/');
                var tracdate=tractindt[2]+'/'+tractindt[1]+'/'+tractindt[0];
                var d1=new Date(tracdate);
                var hmr=document.getElementById("HMR");
                var sale=saledate.value.split('/');
                var sdate=sale[2]+'/'+sale[1]+'/'+sale[0];
                var hmrworking=document.getElementById("HMR WORKING");
                var hmrlabel=document.getElementById("HMRLABEL");
                var d2=new Date(sdate);
                var warrantyapp= document.getElementById("Warranty App");
                var warrantyapplicable=document.getElementById("warrantyApplicable");
                var hmrvalue=document.getElementById("HMRVALUE");
                var daydiff=1;
                var months;


                months = (d2.getFullYear() - d1.getFullYear()) * 12;
                months -= d1.getMonth() + 1;
                months += d2.getMonth();
                months=months+1;
                months=Math.abs(months);


               // if(months>0)
                 //   daydiff=eval(d1.getDate())-eval(sale[0]);

               // daydiff=Math.abs(daydiff)

               // if(daydiff>29)
               //     daydiff=0;



                if(hmrworking.checked==true && hmr.value=="0" )
                {
                    hmrlabel.innerHTML="<div align='right'> <span class='formLabel'><label>HMR</label>&nbsp;<span class='mandatory'>*</span></span></div></td>"
                    hmrvalue.innerHTML="<input name='hmr' type='text' id='HMR'  value='0' maxlength='' />"
                    hmr.value="";

                }




 //alert('i m  '+daydiff+' months '+months+' & mon '+mon+' hrs '+hmr.value+' hr '+hrs);

                if(hmr.value!="" && hmr.value!=undefined && hmrworking.checked==true ){

                    if(validNumberdigit(hmr.value)==true){


                        if((daydiff>0 && months<=eval(mon)) && hmr.value<=eval(hrs)){
                            //warrantyapp.innerHTML="<font color='red'>YES</font>";
                            warrantyapplicable.value="Y";
                        }
                        else{
                           // alert('hi')
                            //warrantyapp.innerHTML="<font color='red'>NO</font>";
                            warrantyapplicable.value="N";
                        }
                    }
                }

                else {

                    if((daydiff>0 && months<=eval(mon))){

                        //warrantyapp.innerHTML="<font color='red'>YES</font>";//<font color="red">Yes </font>
                        warrantyapplicable.value="Y";
                    }

                    else{
                      //  alert('hello')
                        //warrantyapp.innerHTML="<font color='red'>NO</font>";
                        warrantyapplicable.value="N";
                    }
                }
            }

//alert(warrantyapplicable.value);

        }





        function Set_color(color){

            document.getElementById("VIN No.").style.backgroundColor=color;
            document.getElementById("Engine Number").style.backgroundColor=color;
            document.getElementById("Sale Date").style.backgroundColor=color;
            document.getElementById("Registration No.").style.backgroundColor=color;
            document.getElementById("Service Booklet No.").style.backgroundColor=color;
            document.getElementById("Model Family").style.backgroundColor=color;
            document.getElementById("Model Code").style.backgroundColor=color;
            document.getElementById("Model Family Desc").style.backgroundColor=color;
            document.getElementById("Model Code Desc.").style.backgroundColor=color;
        }




        function validatesaledate(id){
            var warrantyapp= document.getElementById("Warranty App");
            var warrantyapplicable=document.getElementById("warrantyApplicable");
            
            if(id.value =="1" || id.value =="41"){
            	
            	var mandatorySpan = document.querySelector('#saledatetd1 .mandatory');

            	
            	if (mandatorySpan) {
            	    mandatorySpan.textContent = ''; 
            	}
            	
            	
            	$('#Sale Date').prop('readonly', true);
            	$('.datepickerSale[readonly]').datepicker('destroy');
            }
            

            if(id.value=="1"){

                document.getElementById("saledatetd1").innerHTML="<td align='right' ><span class='formLabel'><label><bean:message key='label.common.Saledate' /></label></span></td>";
                //warrantyapp.innerHTML="<font color='red'>YES</font>";
                warrantyapplicable.value="Y";
            }
            else if(id.value=="41"){

                document.getElementById("saledatetd1").innerHTML="<td align='right' ><span class='formLabel'><label><bean:message key='label.common.Saledate' /></label></span></td>";
                warrantyapplicable.value="N";
            }
             
            else{
                document.getElementById("saledatetd1").innerHTML="<td align='right' ><span class='formLabel'><label><bean:message key='label.common.Saledate' /></label>&nbsp;<span class='mandatory'>*</span></span></td>";
                //warrantyapp.innerHTML="<font color='red'>NA</font>";
                warrantyapplicable.value="N";
            }
        }



        function changeHmr(){
            var hmrworking=document.getElementById("HMR WORKING");
            var hmrlabel=document.getElementById("HMRLABEL");
            if(hmrworking.checked==true){
                hmrlabel.innerHTML="<div align='right'> <span class='formLabel'><label>HMR</label>&nbsp;<span class='mandatory'>*</span></span></div></td>"
    <%--if(hmr.value=="" || hmr.value==undefined ){
        alert(notblankhmr_validation_msg);
        //warrantyapp.innerHTML="<font color='red'>NO</font>";
        warrantyapplicable.value="N";
        hmr.focus();
        return false;
    }
    if(validNumberdigit(hmr.value)==false){
        alert(numberhmr_validation_msg);
        hmr.focus();
    }--%>
            }
            if(hmrworking.checked==false)
            {
                hmrlabel.innerHTML="<div align='right'> <span class='formLabel'><label>HMR</label></span></div></td>"
                // hmre.innerHTML="<input name='hmr' type='text' id='HMR'  value='"+hmr.value+"' maxlength=''/>"

            }
        }


        function submitform(){

                      if(!isManualEntry()){
              document.getElementById("vehicleform").submit();
             }else{
                 alert(manualEntryForPDIValidationMessage);
             }

          

         }






         function eventdate(id){

             var totalval=id.value;
             document.getElementById("eventstartdate").value=totalval.split("@@")[1];
             document.getElementById("eventenddate").value=totalval.split("@@")[2];


         }

         function Reset(){

             window.location.href="<%=cntxpath%>/serviceCreateJC.do?option=initVehicleInformation&jobType=PDI&pathNm=fillJC";

         }

         $(document).ready(function(){
             var jobCardNo = "${serviceform.jobCardNo}";
             if(jobCardNo == ""){
                 manualEntry = true;
             }else{
                 manualEntry = false;
             }

         });
         function isManualEntry(){
             var jobType = $('select[name="jobType"]').val();
             if(jobType == '1' && manualEntry){
                 return true;
             }else{
                return false;
             }
         }
          function checkExtWtyPolicyStatus(){

            document.getElementById("msg_saveFAILED").innerHTML="";
            var chassisNo=document.getElementById("VIN No.").value;
            var tractorindate=document.getElementById("Tractor In Date").value;
            var warrantyapplicable=document.getElementById("warrantyApplicable")

            var strURL="<%=cntxpath%>/serviceCreateJC.do?option=checkExtWtyPolicyStatus&chassisNo=" + chassisNo+"&tractorindate="+tractorindate+"&tm=" + new Date().getTime();

            xmlHttp = GetXmlHttpObject();
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);

            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    res = trim(xmlHttp.responseText);
                    if(res=='YES'){
                       warrantyapplicable.value='Y'
                       submitform();
                   }else{
                    document.getElementById("msg_saveFAILED").innerHTML=res;
                    return false;
                   }
                }
            }
        }
function actualHmr(){
       ///alert('hi hmr');
        document.getElementById("msg_saveFAILED").innerHTML="";
        var hmr=document.getElementById("HMR").value;
        var chassisNo=document.getElementById("VIN No.").value
        var jobType = document.getElementById("Job Type").value;
        var strURL="<%=cntxpath%>/serviceCreateJC.do?option=getJobEligibiltyForTSN&chassisNo="+chassisNo+"&hmr="+hmr+"&jobType="+jobType+"&tm=" + new Date().getTime();

        xmlHttp = GetXmlHttpObject();
        xmlHttp.open("POST", strURL, true);
        xmlHttp.send(null);

        xmlHttp.onreadystatechange = function()
        {
            if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
            {
                res = trim(xmlHttp.responseText);
                //alert(res);
                var tempValue = res.split("@@")[0];
                var tempdesc = res.split("@@")[1];
                if(tempValue == 'N')
                {
                    alert(tempdesc);
                    document.getElementById("HMR").value='';
                    return false;
                }else{
                	$('select[id="Job Type"]').prop('disabled', false);
			//		submitform();
				}
            }
        }
    }
    
 function checkITLExtWtyPolicyStatus(){
    
     document.getElementById("msg_saveFAILED").innerHTML="";
     var saleDate=document.getElementById("Sale Date").value;
     var chassisNo=document.getElementById("VIN No.").value;
     var hmr=document.getElementById("HMR").value;
     var jobType = document.getElementById("Job Type").value; 
     var strURL="<%=cntxpath%>/serviceCreateJC.do?option=checkITLExtWtyPolicyStatus&chassisNo="+chassisNo+"&saleDate="+saleDate+"&hmr="+hmr;

     xmlHttp = GetXmlHttpObject();
     xmlHttp.open("POST", strURL, true);
     xmlHttp.send(null);

     xmlHttp.onreadystatechange = function()
     {
         if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
         {
             res = trim(xmlHttp.responseText);
             if(jobType != '9'){
             
             if(res == 'N'){
            	 document.getElementById("warrantyApplicable").value=res;
            	 alert("ITL Extended Warranty Period is Ended");
            	 Reset();
            	 return false;
             }
            }
             
             else{
            	 document.getElementById("warrantyApplicable").value=res;
             }
             
         }
     }
 } 
 
/*
function toggleEditableField() {
     var jobLocation = document.getElementById("Location").value;
     var ownerDriven = document.getElementById("Owner Driven").value;
     var serviceType = document.getElementById("Service Type").value;
     var jobType = document.getElementById("Job Type").value;
     
     
     if(jobLocation != '' && ownerDriven != '' && serviceType != '' && jobType != ''){
    	 $( "#HMR" ).prop( "disabled", false ).css("background-color", "#FFF");
     }

     else{
        $("#HMR").prop("disabled", true).css("background-color", "#E6E4E4").css("border","solid 1px #CCCCCC");
     }
 } 
 */
    
</script>
<body onload="chassisfromViewPDI()">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link> </li>
            <c:if test="${empty pdiPage}">
                <c:if test="${openJC eq 'viewAll'}">
                <li class="breadcrumb_link"><a href="<%=cntxpath%>/serviceProcess.do?option=init_viewallJobcarddetails&jobType=ViewJC"><bean:message key="label.common.viewalljobcard" /></a></li>
                </c:if>
            </c:if>
            <c:if test="${pdiPage eq 'Y'}">
            <li class="breadcrumb_link">
                <a href ='<%=cntxpath%>/pdiServiceProcess.do?option=init_viewPDIDetails&jobType=PDI'>
                    <bean:message key="label.common.viewpdidetail" />
                </a></li>
            </c:if>
            <li class="breadcrumb_link"><bean:message key="label.common.createjobcard" /></li>

        </ul>
    </div>
    <div class="message" id="msg_saveFAILED"><html:errors/>${message}</div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="width: 100%">


                <h1 ><span class="formLabel"> <bean:message key="label.common.jobcard" /></span></h1>





                <c:if test="${serviceform.jobcardview eq 'true'}">
                    <%@ include file="topbandshow.jsp" %>
                </c:if>
                <%--<div class="message1" id="msg_saveFAILED">${message}</div>--%>
                <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >


                    <tr><td>
                            <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1">

                                <tr align="center" valign="middle" class="formHeader">

                                    <c:choose>

                                        <c:when test="${serviceform.jobcardview eq 'true' }">
                                            <td class="formSelectedHeader"><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initVehicleInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="linkGreyBg"><bean:message key="label.common.vehicleinfo" /></a></label></td>
                                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initCustomerInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}&itlExtWarrantyApplicable=${serviceform.itlExtWarrantyApplicable}" class="formLabel"><bean:message key="label.common.customerinfo" /></a></label></td>
                                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStandardChecks&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.standardcheck" /></a></label></td>
                                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initComplaint&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.complaint" /></a></label></td>
                                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initEstimate&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.estimate" /></a></label></td>
                                            
                                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initActual&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.actuals" /></a></label></td>
                                            
                                            <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initStatus&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&showHistory=${showHistory}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.status" /></a></label></td>
                                            <c:if test="${showHistory ne 'Y'}">
                                                <td><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=init_vehicle_History&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&pathNm=fillJC&jctype=${serviceform.jctype}&vinid=${serviceform.vinid}&jobId=${serviceform.jobId}&openJC=${openJC}" class="formLabel"><bean:message key="label.common.history" /></a></label></td>
                                            </c:if>
                                        </c:when>

                                      
                                        <c:otherwise>
                                            <td class="formSelectedHeader"><label><a href="<%=cntxpath%>/serviceCreateJC.do?option=initVehicleInformation&vinNo=${serviceform.vinNo}&jobTypeDesc=${serviceform.jobTypeDesc}&jobCardNo=${serviceform.jobCardNo}&productionCategory_Desc=${serviceform.productionCategory_Desc}&warrantyApplicable=${serviceform.warrantyApplicable}&engineNumber=${serviceform.engineNumber}&jobcardview=${serviceform.jobcardview}&jobType=${serviceform.jobType}&modelCode=${serviceform.modelCode}&status=${serviceform.status}&vin_details_type=${serviceform.vin_details_type}&vinid=${serviceform.vinid}&pathNm=fillJC&jctype=${serviceform.jctype}&jobId=${serviceform.jobId}&jobTypeStatus=${jobTypeStatus}&itlExtWarrantyApplicable=${serviceform.itlExtWarrantyApplicable}" class="linkGreyBg"><bean:message key="label.common.vehicleinfo" /></a></label></td>

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



                    <tr><td><form action="<%=cntxpath%>/serviceCreateJC.do?option=manageVehileInformation&pathNm=fillJC&jctype=fillJC&jctype=${serviceform.jctype}" method="post" id="vehicleform" >
                                <table width="100%" border="0" align="center" cellpadding="1" cellspacing="5"  >
                                    <tbody>
                                        <tr>
                                            <td width="157" align="right"><span class="formLabel">
                                                    <label><bean:message key="label.common.Productcategory" /></label></span></td>
                                            <td  align="left"  >
                                                <input name="jobId" type="hidden" value="${serviceform.jobId }">
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
                                                    <c:when test="${jobTypeStatus eq 'PDI' }">
                                                        <select name="jobType"  id="Job Type" >
                                                            <c:forEach items="${jobTypeList}" var="dataList">

                                                                <c:if test="${serviceform.jobType eq dataList.value}">

                                                                    <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>

                                                                </c:if>

                                                            </c:forEach>
                                                        </select>
                                                    </c:when>

                                                    <c:otherwise>
                                                        <select name="jobType"  id="Job Type" onchange="validatesaledate(this);">
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:if test="${jobTypeStatus ne 'PDI' }">
                                                        <option value="">--Select--</option>
                                                        <c:forEach items="${jobTypeList}" var="dataList">

                                                            <c:if test="${serviceform.jobType eq dataList.value}">

                                                                <option value='${dataList.value}' title='${dataList.label}' selected>${dataList.label}</option>

                                                            </c:if>

                                                            <c:if test="${serviceform.jobType ne dataList.value }">

                                                                <option value='${dataList.value}' title='${dataList.label}' >${dataList.label}</option>

                                                            </c:if>


                                                        </c:forEach>
                                                    </c:if>
                                                </select>

                                            </td>

                                            <!--                            <td width="197" align="right" ><span class="formLabel">
                                                                                <label>Job Card No</label>
                                                                                .</span></td>
                                                                        <td width="110" align="left" >
                                                                            <input name="jobCardNo" type="text" id="Job Card No" maxlength="" readonly value="${jobid}" />
                                                                        </td>-->
                                            <td width="173" align="right" id="modelfamilytd"><span class="formLabel">
                                                    <label><bean:message key="label.common.Modelfamily" /></label>&nbsp;<span class="mandatory">*</span>
                                                </span></td>

                                            <td width="118" align="left" >
                                                <c:choose>
                                                    <c:when test="${serviceform.vin_details_type eq 'DB'  }">
                                                        <input name="modelFamily" type="text" id="Model Family"  maxlength="40" value="${serviceform.modelFamily}" readonly="true" style="background-color: #E6E4E4"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input name="modelFamily" type="text" id="Model Family"  maxlength="40" value="${serviceform.modelFamily}" />
                                                    </c:otherwise>
                                                </c:choose>
                                                <input name="jobcardview" type="hidden" value="${serviceform.jobcardview }">
                                                <input name="jobcardno" type="hidden" value="${serviceform.jobCardNo }">
                                                <input name="showHistory" type="hidden" value="${showHistory}">
                                                <input name="vinid" id="vinid" type="hidden" value="${serviceform.vinid}">
                                                <input name="vin_details_type_vehicle" id="vin_details_type_vehicle" type="hidden" value="">
                                            </td>

                                        </tr>
                                        <tr>
                                            <td width="157" align="right"><span class="formLabel">
                                                    <label><bean:message key="label.common.Chassisno" /></label>&nbsp;<span class="mandatory">*</span></span></td>
                                            <td width="175" align="left"  >
                                                <c:choose>
                                                    <c:when test="${serviceform.jobcardview eq 'true' or serviceform.vin_details_type eq 'DB' }">
                                                        <input name="vinNo" type="text" id="VIN No." maxlength="40"   value="${serviceform.vinNo}" readonly style="background-color: #E6E4E4"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input name="vinNo" type="text" id="VIN No." maxlength="40" value="${serviceform.vinNo}" onblur="getvinNoData(this)"/>
                                                        <img src='<%=cntxpath%>/image/arrdown.gif' alt='Get Suggestions' name="img1" border='0'  onclick="getVinDetails('VIN No.',document.getElementById('VIN No.'),document.getElementById('img'),'');"/>
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


                                                <input name="dealerJobCardNo" type="text" class="" maxlength="50" id="Dealer Job Card No" value="${serviceform.dealerJobCardNo}" />

                                            </td>

                                            <td align="right" style=""><div align="right"> <span class="formLabel">
                                                        <label><bean:message key="label.common.Modelfamilydesc" /></label>
                                                    </span></div></td>
                                            <td align="left">
                                                <c:choose>
                                                    <c:when test="${serviceform.vin_details_type eq 'DB' }">
                                                        <input name="modelFamilyDesc" type="text" id="Model Family Desc"  maxlength="70" value="${serviceform.modelFamilyDesc}" readonly  style="background-color: #E6E4E4"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input name="modelFamilyDesc" type="text" id="Model Family Desc"  maxlength="70" value="${serviceform.modelFamilyDesc}"/>
                                                    </c:otherwise>
                                                </c:choose>

                                            </td>




                                        </tr>
                                        <tr>
                                            <td align="right" style="" id="saledatetd1"><span class="formLabel">
                                                    <label><bean:message key="label.common.Saledate" /> </label>&nbsp;<span class="mandatory">*</span>
                                                </span>
                                            </td>
                                            <td align="left" id="saledatetd">
                                                <c:choose>
                                                    <c:when test="${serviceform.vin_details_type eq 'DB' }">
                                                        <input name="saleDate1" type="text"  id="Sale Date1" value="${serviceform.deliveryDate}" readonly style="background-color: #E6E4E4"/>
                                                        <input name="deliveryDate" type="hidden" class="datepickerSale" maxlength="10" id="Sale Date" value="${serviceform.deliveryDate}"  />
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input name="deliveryDate" type="text" class="datepickerSale"  maxlength="10" id="Sale Date" value="${serviceform.deliveryDate}" readOnly/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>

                                            <td align="right" style="" ><div align="right"> <span class="formLabel">
                                                        <label><bean:message key="label.common.hrmworking" /></label>&nbsp;<span class="mandatory">*</span>
                                                    </span></div></td>
                                            <td align="left" style="padding-left: 5px">
                                                 <input type="hidden" name="hmeRadio" id="HMR WORKINGN"  value="Y"/>
                                                <c:if test="${serviceform.hmeRadio eq 'Y'}">
                                                    <input name="hmeRadio" id="HMR WORKING" type="radio" value="Y" checked="checked" onclick="changeHmr()"/>
                                                    <span class="formLabel">Yes</span>
                                                    <%--<input name="hmeRadio"  type="radio" id="HMR WORKINGN" value="N" onclick="changeHmr()"/>
                                                    <span class="formLabel">No</span>--%>
                                                </c:if>
                                                <c:if test="${serviceform.hmeRadio eq 'N'}">
                                                    <input name="hmeRadio" type="radio" id="HMR WORKING" value="Y" onclick="changeHmr()"/>
                                                    <span class="formLabel">Yes</span>
                                                    <%--<input name="hmeRadio" type="radio" value="N" id="HMR WORKINGN" checked="checked" />
                                                    <span class="formLabel">No</span> --%>
                                                </c:if>

                                                <c:if test="${serviceform.hmeRadio eq null or serviceform.hmeRadio eq '' }">
                                                    <input name="hmeRadio" type="radio" value="Y"  checked="checked" id="HMR WORKING" onclick="changeHmr()" />
                                                    <span class="formLabel">Yes</span>
                                                    <%--<input name="hmeRadio" type="radio" value="N" id="HMR WORKINGN"  onclick="changeHmr()"/>
                                                    <span class="formLabel">No</span>--%>

                                                </c:if>

                                            </td>




                                            <td align="right"><span class="formLabel" id="modelcodetd">
                                                    <label><bean:message key="label.common.Modelcode" /></label>&nbsp;<span class="mandatory">*</span>
                                                </span></td>
                                            <td align="left" >
                                                <c:choose>
                                                    <c:when test="${serviceform.vin_details_type eq 'DB' }">
                                                        <input name="modelCode" type="text" id="Model Code"  maxlength="50" value="${serviceform.modelCode}" readOnly="true" style="background-color: #E6E4E4"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input name="modelCode" type="text" id="Model Code"  maxlength="50" value="${serviceform.modelCode}" />
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
                                                        <input name="engineNumber" type="text" id="Engine Number" value="${serviceform.engineNumber}" readonly="true" maxlength="40" style="background-color: #E6E4E4"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input name="engineNumber" type="text" id="Engine Number" value="${serviceform.engineNumber}" maxlength="40"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>





                                            <td align="right" style="" id="HMRLABEL"><div align="right"> <span class="formLabel">
                                                        <label><bean:message key="label.common.hrm" /> <span class='mandatory'>*</span></label>
                                                    </span></div></td>
                                            <td align="left" id="HMRVALUE">
                                                <input name="hmr" type="text" id="HMR"   value="${serviceform.hmr}" onchange="actualHmr()" maxlength="7" />

                                            </td>

                                            <td align="right" style="" ><div align="right"> <span class="formLabel">
                                                        <label><bean:message key="label.common.Modelcodedesc" /></label>
                                                    </span></div></td>
                                            <td align="left">
                                                <c:choose>
                                                    <c:when test="${serviceform.vin_details_type eq 'DB' }">
                                                        <input name="modelCodeDesc" type="text" id="Model Code Desc." maxlength="75" value="${serviceform.modelCodeDesc}" readonly="true" style="background-color: #E6E4E4"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input name="modelCodeDesc" type="text" id="Model Code Desc." maxlength="75" value="${serviceform.modelCodeDesc}"/>
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
                                                        <input name="registrationNo" type="text" id="Registration No." maxlength="100" value="${serviceform.registrationNo}" readonly="true" style="background-color: #E6E4E4"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input name="registrationNo" type="text" id="Registration No." maxlength="100" value="${serviceform.registrationNo}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>

                                            <td align="right" style="" ><span class="formLabel">
                                                    <label><bean:message key="label.common.event" /></label>
                                                </span></td>
                                            <td align="left">
                                                <input type="hidden" id="eventstartdate" value="">
                                                <input type="hidden" id="eventenddate" value="">
                                                <select name="event"  id="Event" onchange="eventdate(this)">

                                                    <option value='0' title='NA' >NA</option>
                                                    <c:forEach items="${eventList}" var="dataList">


                                                        <c:set value="${fn:split(dataList.value,'@@')}" var="evt"/>
                                                        <c:if test="${serviceform.event eq  evt[0] }">
                                                            <option value='${dataList.value}' title='${dataList.label}(${evt[1]})' selected>${dataList.label}(${evt[1]})</option>

                                                        </c:if>

                                                        <c:if test="${serviceform.event ne  evt[0] }">

                                                            <option value='${dataList.value}' title='${dataList.label}(${evt[1]})' >${dataList.label}(${evt[1]})</option>

                                                        </c:if>

                                                    </c:forEach>
                                                </select>

                                            </td>


                                            <td align="right" style="" ><div align="right"> <span class="formLabel">
                                                        <label><bean:message key="label.common.location" /></label>&nbsp;<span class="mandatory">*</span>
                                                    </span></div></td>
                                            <td align="left">
                                                <select name="jobLocation"  id="Location">
                                                    <option value=""  >--select--</option>
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
                                                        <input name="serviceBookletNo" type="text" id="Service Booklet No."  maxlength="40" value="${serviceform.serviceBookletNo}" readonly="true" style="background-color: #E6E4E4"/>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input name="serviceBookletNo" type="text" id="Service Booklet No."  maxlength="40" value="${serviceform.serviceBookletNo}"/>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>


                                            <td align="right" style="" ><div align="right"> <span class="formLabel">
                                                        <label><bean:message key="label.common.servicetype" /></label>&nbsp;<span class="mandatory">*</span></span></div></td>
                                            <td align="left">
                                                <select name="serviceType"  id="Service Type" >
                                                    <option value="" title="" >--select--</option>
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
                                                    <label><bean:message key="label.common.tractorindate" />  </label>&nbsp;<span class="mandatory">*</span>
                                                </span></td>
                                            <td align="left" style="white-space: nowrap">
                                            <c:choose>
                                            <c:when test="${!empty serviceform.jobCardDate}">
                                                <input name="jobCardDate" type="text" readonly  id="Tractor In Date" size="15" value="${serviceform.jobCardDate}" />&nbsp;
                                                <select name="currentEstimateTime"  id="currentEstimateTime" style="width:45px !important " readonly>
                                                    <c:forEach var="i" begin="0" end="23">

                                                        <c:if test="${i lt 10 }">
                                                            <c:set var="temp" value="0${i}"></c:set>
                                                            <c:if test="${serviceform.currentEstimateTime eq temp}">
                                                                <option value="0${i}" selected>0${i}</option>
                                                            </c:if>
                                                            <c:if test="${serviceform.currentEstimateTime ne temp}">
                                                                <option value="0${i}">0${i}</option><c:out value="${('0')+i}"/>
                                                            </c:if>
                                                        </c:if>
                                                        <c:if test="${i gt 10 or i eq 10}">

                                                            <c:if test="${serviceform.currentEstimateTime eq i }">
                                                                <option value="${i}" selected>${i}</option>
                                                            </c:if>
                                                            <c:if test="${serviceform.currentEstimateTime ne i }">
                                                                <option value="${i}" >${i}</option>
                                                            </c:if>
                                                        </c:if>

                                                    </c:forEach>
                                                </select>
                                                :
                                                <select name="currentEstimateHrs" id="currentEstimateHrs"  style="width:45px !important " readonly>
                                                    <c:set var="i" value="0"></c:set>
                                                    <c:forEach begin="0" end="11">
                                                        <c:set var="temp1" value="0${i}"></c:set>
                                                        <c:if test="${i lt 10 }">
                                                            <c:if test="${serviceform.currentEstimateHrs eq temp1 }">
                                                                <option value="0${i}" selected>0${i}</option>
                                                            </c:if>
                                                            <c:if test="${serviceform.currentEstimateHrs ne temp1 }">
                                                                <option value="0${i}">0${i}</option>
                                                            </c:if>
                                                        </c:if>
                                                        <c:if test="${i gt 10 or i eq 10}">
                                                            <c:if test="${serviceform.currentEstimateHrs eq i }">
                                                                <option value="${i}" selected>${i}</option>
                                                            </c:if>
                                                            <c:if test="${serviceform.currentEstimateHrs ne i }">
                                                                <option value="${i}" >${i}</option>
                                                            </c:if>
                                                        </c:if>
                                                        <c:set var="i" value="${i + 5 }" />
                                                    </c:forEach>
                                                </select>
                                            </c:when>
                                            <c:otherwise>
                                                <input name="jobCardDate" type="text" readonly class="datepicker" id="Tractor In Date" size="15" value="${serviceform.jobCardDate}"  />&nbsp;
                                                <select name="currentEstimateTime"  id="currentEstimateTime" style="width:45px !important " >
                                                    <c:forEach var="i" begin="0" end="23">

                                                        <c:if test="${i lt 10 }">
                                                            <c:set var="temp" value="0${i}"></c:set>
                                                            <c:if test="${serviceform.currentEstimateTime eq temp}">
                                                                <option value="0${i}" selected>0${i}</option>
                                                            </c:if>
                                                            <c:if test="${serviceform.currentEstimateTime ne temp}">
                                                                <option value="0${i}">0${i}</option><c:out value="${('0')+i}"/>
                                                            </c:if>
                                                        </c:if>
                                                        <c:if test="${i gt 10 or i eq 10}">

                                                            <c:if test="${serviceform.currentEstimateTime eq i }">
                                                                <option value="${i}" selected>${i}</option>
                                                            </c:if>
                                                            <c:if test="${serviceform.currentEstimateTime ne i }">
                                                                <option value="${i}" >${i}</option>
                                                            </c:if>
                                                        </c:if>

                                                    </c:forEach>
                                                </select>
                                                :
                                                <select name="currentEstimateHrs" id="currentEstimateHrs"  style="width:45px !important " >
                                                    <c:set var="i" value="0"></c:set>
                                                    <c:forEach begin="0" end="11">
                                                        <c:set var="temp1" value="0${i}"></c:set>
                                                        <c:if test="${i lt 10 }">
                                                            <c:if test="${serviceform.currentEstimateHrs eq temp1 }">
                                                                <option value="0${i}" selected>0${i}</option>
                                                            </c:if>
                                                            <c:if test="${serviceform.currentEstimateHrs ne temp1 }">
                                                                <option value="0${i}">0${i}</option>
                                                            </c:if>
                                                        </c:if>
                                                        <c:if test="${i gt 10 or i eq 10}">
                                                            <c:if test="${serviceform.currentEstimateHrs eq i }">
                                                                <option value="${i}" selected>${i}</option>
                                                            </c:if>
                                                            <c:if test="${serviceform.currentEstimateHrs ne i }">
                                                                <option value="${i}" >${i}</option>
                                                            </c:if>
                                                        </c:if>
                                                        <c:set var="i" value="${i + 5 }" />
                                                    </c:forEach>
                                                </select>
                                            </c:otherwise>
                                        </c:choose>
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
                                                <input name="CouponNo" type="text" id="Coupon No"  maxlength="40" value="${serviceform.couponno}"/></td>





                                            <td align="right"><span class="formLabel">
                                                    <label><bean:message key="label.common.Ownerdriven" /></label>&nbsp;<span class="mandatory">*</span>
                                                </span></td>
                                            <td align="left" >
                                                <select name="ownerDriven"  id="Owner Driven">
                                                    
                                                    <c:if test="${serviceform.ownerDriven eq 'YES'}">
                                                        <option value="YES" title="YES" selected>YES</option>
                                                        <option value="NO" title="NO" >NO</option>
                                                    </c:if>
                                                    <c:if test="${serviceform.ownerDriven ne 'YES'}">
                                                        <option value="YES" title="YES" >YES</option>
                                                        <option value="NO" title="NO" selected>NO</option>
                                                    </c:if>
                                                </select>
                                            </td>



                                            <%--<td align="right" style=""><div align="right"> <span class="formLabel">
                                                        <label><bean:message key="label.common.Jobcardstatus" /></label></span></div></td>
                                            <td align="left" >

                                <select name="jobCardStatus"  id="jobCardStatus">
                                  <option value="OPEN" title="OPEN" selected>OPEN</option>
                                  <option value="CLOSE" title="CLOSE" >CLOSE</option>
                                </select>
                            </td>--%>

                                            <td align="right"><span class="formLabel">
                                                    <label><bean:message key="label.common.nextservicefromhere" /></label>&nbsp;<span class="mandatory">*</span>
                                                </span>
                                            </td>

                                            <td align="left">

                                                <select name="nextService"  id="Nextservice From Here">
                                                    <c:choose>
                                                        <c:when test="${serviceform.nextService eq 'YES'}">
                                                            <option value="YES" title="YES" selected>YES</option>
                                                            <option value="NO" title="NO" >NO</option>
                                                        </c:when>

                                                        <c:when test="${serviceform.nextService eq 'NO'}">
                                                            <option value="YES" title="YES" >YES</option>
                                                            <option value="NO" title="NO" selected>NO</option>
                                                        </c:when>

                                                        <c:otherwise>
                                                            <option value="YES" title="YES" selected>YES</option>
                                                            <option value="NO" title="NO" >NO</option>
                                                        </c:otherwise>

                                                    </c:choose>


                                                </select>
                                            </td>

                                        </tr>


                                        <tr>



                                            <td align="right"><span class="formLabel">
                                                    <label>
                                                        <bean:message key="label.common.Jobcardpriority" /></label>
                                                </span>
                                            </td>
                                            <td align="left">


                                                <select name="jobcardpriority"  id="Job card Priority">
                                                    <c:choose>

                                                        <c:when test="${serviceform.jobcardpriority eq 'Moderate' }">
                                                            <option value='Low' >Low</option>
                                                            <option value='Moderate' selected>Moderate</option>
                                                            <option value='Critical' >Critical</option>
                                                            <option value='Off Road Vehicle' >Off Road Vehicle </option>

                                                        </c:when>
                                                        <c:when test="${serviceform.jobcardpriority eq 'Critical' }">
                                                            <option value='Low' >Low</option>
                                                            <option value='Moderate' >Moderate</option>
                                                            <option value='Critical' selected>Critical</option>
                                                            <option value='Off Road Vehicle' >Off Road Vehicle </option>
                                                        </c:when>

                                                        <c:when test="${serviceform.jobcardpriority eq 'Off Road Vehicle' }">
                                                            <option value='Low' >Low</option>
                                                            <option value='Moderate' >Moderate</option>
                                                            <option value='Critical' >Critical</option>
                                                            <option value='Off Road Vehicle' selected>Off Road Vehicle </option>
                                                        </c:when>

                                                        <c:otherwise>
                                                            <option value='Low' >Low</option>
                                                            <option value='Moderate' >Moderate</option>
                                                            <option value='Critical' >Critical</option>
                                                            <option value='Off Road Vehicle' >Off Road Vehicle </option>
                                                        </c:otherwise>
                                                    </c:choose>


                                                </select>
                                                
                                                
                                                 <td align="right"><span class="formLabel">
                                                    <label>
                                                        <bean:message key="label.common.VorJobCard" /></label>
                                                        &nbsp;<span class="mandatory">*</span>
                                                </span>
                                               
                                            </td>
                                            <td align="left">

                                                <select name="vorJobcard"  id="vorJobcard">
                                                    <c:choose>

                                                        <c:when test="${serviceform.vorJobcard eq 'YES' }">
                                                            <option value='NO' >NO</option>
                                                            <option value='YES' selected>YES</option>
                                                           

                                                        </c:when>
                                                        <c:when test="${serviceform.vorJobcard eq 'NO' }">
                                                             <option value='YES'>YES</option>
                                                             <option value='NO' selected>NO</option>
                                                            
                                                        </c:when>

                                                        <c:otherwise>
                                                            <option value=''>--select--</option>
                                                            <option value='YES'>YES</option>
                                                             <option value='NO'>NO</option>
                                                        </c:otherwise>
                                                    </c:choose>


                                                </select>
                                                
                                                <input type="hidden" value="" name="months" id="months">
                                                <input type="hidden" value="" name="hrs" id="hrs">
                                                <input type="hidden" value="MANUAL" name="vindatatype" id="vindatatype">

                                                <c:if test="${empty serviceform.warrantyApplicable}">
                                                    <input type="hidden" value="" name="warrantyApplicable" id="warrantyApplicable">
                                                </c:if>

                                                <c:if test="${!empty serviceform.warrantyApplicable}">
                                                    <input type="hidden" value="${serviceform.warrantyApplicable}" name="warrantyApplicable" id="warrantyApplicable">
                                                </c:if>
                                                
                                                <c:if test="${empty serviceform.itlExtWarrantyApplicable}">
                                                    <input type="hidden" value="" name="itlExtWarrantyApplicable" id="itlExtWarrantyApplicable">
                                                </c:if>

                                                <c:if test="${!empty serviceform.itlExtWarrantyApplicable}">
                                                    <input type="hidden" value="${serviceform.itlExtWarrantyApplicable}" name="itlExtWarrantyApplicable" id="itlExtWarrantyApplicable">
                                                </c:if>
                                               


                                            </td>


                                            <%--<td align="right" style="" ><span class="formLabel">

                                    <label><bean:message key="label.common.warrantyapplicable" /></label>
                                </span></td >
                            <td align="left" id="Warranty App">  <c:choose>
                                    <c:when test="${serviceform.warrantyApplicable eq 'Y'}">
                                        : <font color="red">YES</font>
                                    </c:when>
                                    <c:when test="${serviceform.warrantyApplicable eq 'N'}">
                                        : <font color="red">NO</font>
                                    </c:when>
                                    <c:otherwise>
                                        <font color="red">NA</font>

                                    </c:otherwise>
                                        </c:choose>

                              </td>--%>

                                        </tr>
                                        <tr>
                                            <td align="right">&nbsp;</td>
                                            <td align="left" >&nbsp;</td>
                                            <td align="right" style="" >&nbsp;</td>
                                            <td align="left">&nbsp;</td>
                                            <td align="right" style="" >&nbsp;</td>
                                            <td align="left">&nbsp;</td>
                                        </tr>
                                        <tr align="right">
                                            <td colspan="6" >
                                                <%if (userFunctionalities.contains("608"))
                                                            {%>
                                                <c:if test="${serviceform.status eq 'OPEN'}">
                                                    <c:if test="${serviceform.jobcardview ne 'true'}">
                                                        <input name="input" type="button" value="Reset" style="float:none; " onclick="Reset()"/>
                                                    </c:if>
                                                    <input name="input" type="button" value="Save" style="float:none; " onclick="validateForm();"/>

                                                </c:if>
                                                <%}%>
                                            </td>




                                        </tr>
                                </table>
                                
      <!--                           <h3 style="color:red">Note: Please fill job type,location, service type and owner driven before filling HMR</h3>
       -->
                             </form>
                        </td>
                    </tr>
                </table>

            </div>
        </div>
    </center>