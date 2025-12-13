<%-- 
    Document   : v_install_information
    Created on : May 26, 2014, 12:28:42 PM
    Author     : megha.pandya
--%>


<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%> 
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

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


%>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_5.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/commonValidations.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/alert_message.js"></script>
<script type="text/javascript" language="javascript">

    var contextPath = '${pageContext.request.contextPath}';
    //    document.getElementById("step2").style.display = 'none'
    <%--  function validate()
      {
          document.getElementById("installationform").submit();
          //        var jobType=document.forms[0].jobType.value;
      }--%>
        function goBack()
        {
            document.getElementById("step2").style.display = 'none'
            document.getElementById("step1").style.display='';
        }
        
        function submitStandarCheckForm(c,c1)
        {
         
            var flag=0;
            //alert(document.getElementById("count").value);
        for(var i=1;i<c;i++)
        {
            if (document.getElementById("checkpoints"+i).checked) {
                flag=1;
            }
        }
        if(c>1){
        if(flag==0)
        {
            alert(notblank_standardcheck_validation_msg)
            return false;
        }
             
        }
    flag=0;
  for(var i=1;i<c1;i++)
        {
            if (document.getElementById("majapp"+i).checked) {
                flag=1;
            }
        }
        if(c1>1){
        if(flag==0)
        {
            alert(notblank_majorapplication_validation_msg)
            return false;
        }

        }



            var x="false";
            var r=confirm(submitconfirmation_validation_msg);
            if (r==true)
            {
                document.getElementById("StandardChecksForm").submit();
            }
        


        
        }
        var count=0;
        var rows=0;
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



        function getvinNoData4Install(id)
        {
            var vinno= id.value;
            document.getElementById("msg_saveFAILED").innerHTML="";
            var strURL="<%=cntxpath%>/installProcess.do?option=getvinNumberdetails&vinNo=" + vinno+"&tm=" + new Date().getTime();
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
                        //                    document.getElementById("Registration No.").value=result[2];
                        //                    document.getElementById("Service Booklet No.").value=result[3];

                        document.getElementById("Model Code").value=result[3];
                        document.getElementById("Model Family").value=result[4];
                        document.getElementById("Model Code Desc.").value=result[5];
                        document.getElementById("Model Family Desc").value=result[6];
                        document.getElementById("Customer Name").value=result[7];
                        document.getElementById("Village").value=result[8];
                        document.getElementById("Taluka").value=result[9];
                        document.getElementById("tehsil").value=result[10];
                        document.getElementById("District").value=result[11];
                        document.getElementById("Pin Code").value=result[12];
                        document.getElementById("State").value=result[13];
                        document.getElementById("Country").value=result[14];
                        document.getElementById("Mobile Phone").value=result[15];
                        document.getElementById("Landline").value=result[16];
                        document.getElementById("E-mail ID").value=result[17];
                        document.getElementById("Size of Land Holding").value=result[18];
                        document.getElementById("Main Crops").value=result[19];
                        document.getElementById("Occupation").value=result[20];
 
                        document.getElementById("VIN No.").readOnly = true;
                        document.getElementById("Engine Number").readOnly = true;

                        document.getElementById("saledatetd").innerHTML="<input name='saleDate' type='text'  id='Sale Date' value='"+result[1]+"' readonly/>";
                        document.getElementById("Sale Date").readOnly = true;
                        //                    document.getElementById("Registration No.").readOnly = true;
                        //                    document.getElementById("Service Booklet No.").readOnly = true;
                        //                    document.getElementById("ownerDriven").readOnly = true;
                        document.getElementById("Model Family").readOnly = true;
                        document.getElementById("Model Code").readOnly = true;
                        document.getElementById("Model Family Desc").readOnly = true;
                        document.getElementById("Model Code Desc.").readOnly = true;
                        //                    document.getElementById("nextService").readOnly = true;
                        //   document.getElementById("HMR").readOnly = true;
                        document.getElementById("Customer Name").readOnly = true;
                        document.getElementById("Village").readOnly = true;
                        document.getElementById("Taluka").readOnly = true;
                        document.getElementById("tehsil").readOnly = true;
                        document.getElementById("District").readOnly = true;
                        document.getElementById("Pin Code").readOnly = true;
                        document.getElementById("State").readOnly = true;
                        document.getElementById("Country").readOnly = true;
                        document.getElementById("Mobile Phone").readOnly = true;
                        document.getElementById("Landline").readOnly = true;
                        document.getElementById("E-mail ID").readOnly = true;
                        document.getElementById("Size of Land Holding").readOnly = true;
                        document.getElementById("Main Crops").readOnly = true;
                        document.getElementById("Occupation").readOnly = true;
                        document.getElementById("check").value="exist";
                        getCommonCode(id,"travelcardData");
                    }


                    else{
                        //EngineNo,SaleDate,RegNo,SerBookNo,KeyIdentification,OwnerDriven,ModelFamily,
                        //ModelCode,ModelFamilyDesc,ModelDesc,DealerCode,NextService

                        document.getElementById("Engine Number").value="";
                        document.getElementById("Sale Date").value="";
                        document.getElementById("Registration No.").value="";
                        document.getElementById("Service Booklet No.").value="";
                        document.getElementById("Model Family").value="";
                        document.getElementById("Model Code").value="";
                        document.getElementById("Model Family Desc").value="";
                        document.getElementById("Model Code Desc.").value="";
                    }

                }
            }
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);
        }




 function getfilesize()
        {
            var filename= document.getElementById("Upload Photo").value;
           
            var myFSO = new ActiveXObject("Scripting.FileSystemObject");
            var filepath = document.getElementById("Upload Photo").value;
            var thefile = myFSO.getFile(filepath);
            var size = thefile.size;
            alert(size);
            
                    
            

            var strURL="<%=cntxpath%>/installProcess.do?option=getfilesize&filename=" + filename+"&tm=" + new Date().getTime();
            xmlHttp = GetXmlHttpObject();
            xmlHttp.open("POST", strURL, false);
           
            xmlHttp.send(null);
           
                     res = trim(xmlHttp.responseText);
                     
                    if(res!='notexist'){
                        document.getElementById("filesize").value=res;
                       
                        if(300<eval(res)){
                            alert(filesize_validation_msg);
                            return false;
                        }
            
            }
            else{
                
            
            var file = document.getElementById("Upload Photo").files[0];
            var filesize=file.size/1024;
            if(300<eval(filesize)){
            alert(filesize_validation_msg);
                   return false;
            }
            return true;
           }
}
        function validateForm(tractorCount)
        {
            var ck_code = /^[a-zA-Z\.\-\/\ ]+$/;
            var cks_code = /^[a-zA-Z\d\-\/\.\,\\\ ]+$/;
            var cks_codes = /^[\d\,\\ ]+$/;
            var namevalidation = /^[a-zA-Z\.\ ]+$/;
            var occupation=/^[a-zA-Z\.\-\[\]\(\)\ ]+$/;
            var elementArr = new Array('Dealer Invoice No','Sale Date','Delivery Date','Representative Name','Visit Date','HMR','Customer Name','Upload Photo','Final Installation Date','Contact Name','Contact No.','Relation with Customer');

            var strObject = null;

            if(document.getElementById("Service Booklet No.").value!=""){
                if(cks_code.test(document.getElementById("Service Booklet No.").value)==false){

                    alert(servicebookletno_validation_msg);
                    document.getElementById("Service Booklet No.").focus();
                    return false;
                }
            }

            for (var i = 0; i < elementArr.length; i++)
            {
           
                strObject = document.getElementById(elementArr[i]);

          
                if(strObject.value=="" && i>=0 )
                {
                     if(elementArr[i]=="Sale Date"){
                        alert(notblankdidate_validation_msg);
                        strObject.focus();
                        //strObject.select();
                        return false;
                    }
                    if(elementArr[i]=="Representative Name"){
                        alert(notblankinstallername_validation_msg);
                        strObject.focus();
                        //strObject.select();
                        return false;
                    }
                    if(elementArr[i]=="Upload Photo"){
                        alert(notblankphotograph_validation_msg);
                        strObject.focus();
                        //strObject.select();
                        return false;
                    }
                    if(elementArr[i]=="Final Installation Date"){
                        alert(notblankfinalinsdate_validation_msg);
                        strObject.focus();
                        //strObject.select();
                        return false;
                    }
                
                    alert("Please enter value in " + elementArr[i] + " field.");
                    strObject.select();
                    strObject.focus();
                    return false;
                }
 
                if(elementArr[i]=="HMR"){
                    if(validNumberdigit(document.getElementById(elementArr[i]).value)==false){
                        alert(numvalue_validation_msg+" "+elementArr[i]);
                        document.getElementById(elementArr[i]).focus();
                        return false;
                    }
                }
             
                    //elementArr[i]=="Mobile Phone" ||  elementArr[i]=="Pincode" ||
                                
               
                //
                  if( elementArr[i]=="Contact Name"){
                 if(namevalidation.test(document.getElementById(elementArr[i]).value)==false){

                        alert("Invalid "+elementArr[i]);
                        document.getElementById(elementArr[i]).focus();
                        return false;
                    }
                }
        
                  if( elementArr[i]=="Contact No."){
                  if(cks_codes.test(document.getElementById(elementArr[i]).value)==false){

                        alert("Invalid "+elementArr[i]);
                        document.getElementById(elementArr[i]).focus();
                        return false;
                    }
                  if(document.getElementById(elementArr[i]).value.length<6){

                        alert(mincontact_validation_msg+" "+elementArr[i]);
                        document.getElementById(elementArr[i]).focus();
                        return false;
                    }
                }

  //'Customer Name','Mobile Phone','Village','Tehsil','District','Pincode','State','Main Crops','Occupation','Family Members',

            }


               
                    if(document.getElementById("Mobile Phone").value!=""){
                    if(validNumberdigit(document.getElementById("Mobile Phone").value)==false){
                        alert(numvalue_validation_msg+" Mobile Phone");
                        document.getElementById("Mobile Phone").focus();
                        return false;
                    }
                    }

                    if(document.getElementById("Family Members").value!=""){
                    if(validNumberdigit(document.getElementById("Family Members").value)==false){
                        alert(numvalue_validation_msg+" Family Members");
                        document.getElementById("Family Members").focus();
                        return false;
                    }
                    }
                 if(document.getElementById("Pincode").value!=""){
                    if(validNumberdigit(document.getElementById("Pincode").value)==false){
                        alert(numvalue_validation_msg+" Pincode");
                        document.getElementById("Pincode").focus();
                        return false;
                    }
                    }

    
            if(document.getElementById("Other Tractor Detail").value!=""){
                if(cks_code.test(document.getElementById("Other Tractor Detail").value)==false){

                    alert(invalidothertractor_validation_msg);
                    document.getElementById("Other Tractor Detail").focus();
                    return false;
                }
            }
            if(document.getElementById("Payment Mode").value!=""){
                if(cks_code.test(document.getElementById("Payment Mode").value)==false){

                    alert(invalidpaymentmode_validation_msg);
                    document.getElementById("Payment Mode").focus();
                    return false;
                }
            }
            if(document.getElementById("Bank Name").value!=""){
                if(cks_code.test(document.getElementById("Bank Name").value)==false){

                    alert(invalidbankname_validation_msg);
                    document.getElementById("Bank Name").focus();
                    return false;
                }
            }


            var size= parseInt(document.getElementById("ListSize").value);
            if(size < 2)
            {
                alert(notfoundtractorinfo_validation_msg);
                return false;
            }
            var vdate = document.getElementById("Visit Date").value;
            var Ddate = document.getElementById("Delivery Date").value;
            var Fdate = document.getElementById("Final Installation Date").value;
            var Sdate = document.getElementById("Sale Date").value;
            var sale=vdate.split('/');
            vdate=sale[2]+'/'+sale[1]+'/'+sale[0];
            vdate=new Date(vdate);
        
            var sale1=Ddate.split('/');
            Ddate=sale1[2]+'/'+sale1[1]+'/'+sale1[0];
            Ddate=new Date(Ddate);

            var sale2=Fdate.split('/');
            Fdate=sale2[2]+'/'+sale2[1]+'/'+sale2[0];
            Fdate=new Date(Fdate);

            var sale4=Sdate.split('/');
            Sdate=sale4[2]+'/'+sale4[1]+'/'+sale4[0];
            Sdate=new Date(Sdate);



            var ttdate="";
            var tmon="<%=month%>";
            var tdays="<%=day%>";

            if(eval(tmon)<10)
                tmon="0"+tmon;

            if(eval(tdays)<10)
                tdays="0"+tdays;

            ttdate=<%=year%>+'/'+tmon+'/'+tdays;
      
            var today = new Date(ttdate);

            if(vdate<Sdate){
            alert(vistdtvsdinvdt_validation_msg);
            document.getElementById("Sale Date").focus();
            
            return false;
            }

            //             alert(today+'---'+Ddate)
            if(today < vdate){
                document.getElementById("Visit Date").style.border="1px solid #ff0000";
                //document.getElementById("Visit Date").value="";
                alert(visitdate_validation_msg);
                document.getElementById("Visit Date").focus();
                return false;
            }
            if(today >= vdate){
                document.getElementById("Visit Date").style.border="#cccccc 1px solid";
                //document.getElementById("Visit Date").value="";
          
            }

           var constantval=${serviceform.constantValue}
         
           var condate =new Date(ttdate);
            
           condate.setDate(condate.getDate()-constantval);
           document.getElementById("Delivery Date").style.border="#cccccc 1px solid";
           document.getElementById("Final Installation Date").style.border="#cccccc 1px solid";
//
            if(today<Ddate)
              {
                //            document.getElementById("Delivery Date").style.border="1px solid #ff0000";
                document.getElementById("Delivery Date").style.border="1px solid #ff0000";
                alert(deliverydate_validation_msg);
                document.getElementById("Delivery Date").select();
                return false;
            }

            

            if(condate>Fdate || today<Fdate){

                document.getElementById("Final Installation Date").style.border="1px solid #ff0000";
                alert(constantfinaldate_validation_msg +" "+constantval+" "+dat_validation_msg2);
                document.getElementById("Final Installation Date").select();
                return false;

            }
            if(vdate>Fdate){

                document.getElementById("Final Installation Date").style.border="1px solid #ff0000";
                alert(constantfinalvisitdate_validation_msg);
                document.getElementById("Final Installation Date").select();
                return false;

            }


            var vistdate=document.getElementById("Visit Date").value.split('/');
            var sdate=vistdate[2]+'/'+vistdate[1]+'/'+vistdate[0];
            var vdate=new Date(sdate).getTime();

       

            var deldate=document.getElementById("Delivery Date").value.split('/');
            var trdate=deldate[2]+'/'+deldate[1]+'/'+deldate[0];
            var ddate=new Date(trdate).getTime();

        


            if(vdate<ddate){
                document.getElementById("Delivery Date").focus();
                document.getElementById("Delivery Date").style.border="1px solid #ff0000";
                alert(deliveryvisitdate_validation_msg);
                //bool="true";
                return false;

            }


            if(vdate>=ddate){

                document.getElementById("Delivery Date").style.border="#cccccc 1px solid";
            
            }

            //upload validation
                       
            var importfile= document.getElementById("Upload Photo").value;
                           
   
            if(importfile != ""){
                 importfile = importfile.toLowerCase();
                 if(importfile.indexOf(".jpeg")!=-1){
                    importfile = (importfile.substr(0, importfile.indexOf(".jpeg"))+".jpg");
                 }
                <%--if((importfile.indexOf(':\\') ==-1 || importfile.indexOf(':\\')==0))
                {
                    alert(invalidbrowsefile_validation_msg);
                    // acitvityArr[i].focus();
                    return false;
                }
                else--%> if(importfile.indexOf(".jpg") != (importfile.length-4))
                {
                    alert(onlyjpgbrowsefile_validation_msg);
                    //  acitvityArr[i].focus();
                    return false;
                }
            }

         <%--if(getfilesize()==false){
             return false;
         }--%>
/*
            var c=0;
            if(count==0)
                count =document.getElementById("ListSize").value;

            for(var i=1;i<count;i++){
                if(document.getElementById("Part No"+i)!=null)
                {
                    if(document.getElementById("Part No"+i).value!=""){ c=1; }
                    else if(document.getElementById("PartVendor Name"+i).value!=""){ c=1; }
                    else if(document.getElementById("PartSerial No"+i).value!=""){ c=1; }
                }
            }

            if(c==0){
                alert("please fill atleast one Tractor Information ");
                document.getElementById("Part No1").focus();
                return false;
            }



            partlist=new Array();
            var j=0;
               for(var i=1;i<count;i++){

                if(document.getElementById("Part No"+i)!=null)
                {

                    if(document.getElementById("Part No"+i).value!=""){
                        partlist[j]=document.getElementById("Part No"+i).value;
                        j++;
                        if(cks_code.test(document.getElementById("Part No"+i).value)==false){
                        alert("Invalid Part Name");
                        document.getElementById("Part No"+i).focus();
                        return false;
                       }
                        if(document.getElementById("PartVendor Name"+i).value==""){alert("Make should not be empty"); document.getElementById("PartVendor Name"+i).focus();    return false; }
                        if(document.getElementById("PartSerial No"+i).value==""){alert("Serial No. should not be empty"); document.getElementById("PartSerial No"+i).focus();return false;}

                    }
                    if(document.getElementById("PartVendor Name"+i).value!=""){

                        if(cks_code.test(document.getElementById("PartVendor Name"+i).value)==false){

                        alert("Invalid Make");
                        document.getElementById("PartVendor Name"+i).focus();
                        return false;
                       }
                        if(document.getElementById("Part No"+i).value==""){alert("Part Name should not be empty"); document.getElementById("Part No"+i).focus();c=1;return false;}
                        if(document.getElementById("PartSerial No"+i).value==""){alert("Serial No should not be empty"); document.getElementById("PartSerial No"+i).focus();return false;}
                    }
                     if(document.getElementById("PartSerial No"+i).value!=""){

                         if(cks_code.test(document.getElementById("PartSerial No"+i).value)==false){

                         alert("Invalid Serial No");
                         document.getElementById("PartSerial No"+i).focus();
                         return false;
                         }
                        if(document.getElementById("Part No"+i).value==""){alert("Part Name should not be empty"); document.getElementById("Part No"+i).focus();return false;}
                        if(document.getElementById("PartVendor Name"+i).value==""){alert("Make should not be empty"); document.getElementById("PartVendor Name"+i).focus();return false;}
                    }

                }
            }

            //for unique partno

            for (var k = 2; k < count; k++) {
                if (document.getElementById("Part No"+k).value != "")
                {
                    var res1 = true;
                    for (var m = 0; m < k-1; m++)
                    {
                               
                        if (partlist[m] == trimS(document.getElementById("Part No"+k).value))
                        {
                            res1 = false;
                        }
                    }
                    if (res1 == false)
                    {
                        alert("Same Part Name can not be enter twice");
                        document.getElementById("Part No" + k).focus();
                                                                     
                        return false;
                    }
                }
            }*/

// for tractor part name and Make validation
var ck_code_tr = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
    for(j=1;j<tractorCount;j++){
        if(document.getElementById("Make"+j).value==""){
            document.getElementById("Make"+j).focus();
            alert(not_blank_dropdown_validation_msg+'Make')
            return false;
        }
        if(document.getElementById("PartSerial No"+j).value==""){
            document.getElementById("PartSerial No"+j).focus();
            alert(not_blank_validation_msg+'Serial No')
            return false;
        }
        if(ck_code_tr.test(document.getElementById("PartSerial No"+j).value)){
           document.getElementById("PartSerial No"+j).focus();
            alert(specialChar_validation_msg+"Serial No");
            return false;
        }

    }


// for customer vaidation
        customerlist=new Array();
            var j=0;
               for(var i=1;i<=5;i++){

                

                    if(document.getElementById("customerName"+i).value!=""){
                        customerlist[j]=document.getElementById("customerName"+i).value;
                        j++;
                        if(namevalidation.test(document.getElementById("customerName"+i).value)==false){
                        alert(invalid_custname_validation_msg);
                        document.getElementById("customerName"+i).focus();
                        return false;
                       }
                        if(document.getElementById("fatherName"+i).value==""){alert(notblank_fathername_validation_msg); document.getElementById("fatherName"+i).focus();    return false; }
                        if(document.getElementById("mobilePhone"+i).value==""){alert(notblank_mobno_validation_msg); document.getElementById("mobilePhone"+i).focus();return false;}
                        if(document.getElementById("village"+i).value==""){alert(notblank_village_validation_msg); document.getElementById("village"+i).focus();return false;}
                        if(document.getElementById("relationwithcustomer"+i).value==""){alert(notblank_relwithcust_validation_msg); document.getElementById("relationwithcustomer"+i).focus();return false;}

                    }

                    if(document.getElementById("fatherName"+i).value!=""){

                        if(namevalidation.test(document.getElementById("fatherName"+i).value)==false){

                        alert(invalid_fathername_validation_msg);
                        document.getElementById("fatherName"+i).focus();
                        return false;
                       }
                        if(document.getElementById("customerName"+i).value==""){alert(notblank_custname_validation_msg); document.getElementById("customerName"+i).focus();return false;}
                        if(document.getElementById("mobilePhone"+i).value==""){alert(notblank_mobno_validation_msg); document.getElementById("mobilePhone"+i).focus();return false;}
                        if(document.getElementById("village"+i).value==""){alert(notblank_village_validation_msg); document.getElementById("village"+i).focus();return false;}
                        if(document.getElementById("relationwithcustomer"+i).value==""){alert(notblank_relwithcust_validation_msg); document.getElementById("relationwithcustomer"+i).focus();return false;}
                    }

                     if(document.getElementById("mobilePhone"+i).value!=""){

                         if(cks_codes.test(document.getElementById("mobilePhone"+i).value)==false){

                         alert(invalid_mobno_validation_msg);
                         document.getElementById("mobilePhone"+i).focus();
                         return false;
                         }
                        if(document.getElementById("customerName"+i).value==""){alert(notblank_custname_validation_msg); document.getElementById("customerName"+i).focus();return false;}
                        if(document.getElementById("fatherName"+i).value==""){alert(notblank_fathername_validation_msg); document.getElementById("fatherName"+i).focus();return false;}
                        if(document.getElementById("village"+i).value==""){alert(notblank_village_validation_msg); document.getElementById("village"+i).focus();return false;}
                        if(document.getElementById("relationwithcustomer"+i).value==""){alert(notblank_relwithcust_validation_msg); document.getElementById("relationwithcustomer"+i).focus();return false;}
                    }

                    if(document.getElementById("village"+i).value!=""){

                         if(cks_code.test(document.getElementById("village"+i).value)==false){

                         alert(invalid_village_validation_msg);
                         document.getElementById("village"+i).focus();
                         return false;
                         }
                        if(document.getElementById("customerName"+i).value==""){alert(notblank_custname_validation_msg); document.getElementById("customerName"+i).focus();return false;}
                        if(document.getElementById("fatherName"+i).value==""){alert(notblank_fathername_validation_msg); document.getElementById("fatherName"+i).focus();return false;}
                        if(document.getElementById("mobilePhone"+i).value==""){alert(notblank_mobno_validation_msg); document.getElementById("mobilePhone"+i).focus();return false;}
                        if(document.getElementById("relationwithcustomer"+i).value==""){alert(notblank_relwithcust_validation_msg); document.getElementById("relationwithcustomer"+i).focus();return false;}
                    }
                        if(document.getElementById("relationwithcustomer"+i).value!=""){

                         if(cks_code.test(document.getElementById("relationwithcustomer"+i).value)==false){

                         alert(invalid_relwithcust_validation_msg);
                         document.getElementById("relationwithcustomer"+i).focus();
                         return false;
                         }
                        if(document.getElementById("customerName"+i).value==""){alert(notblank_custname_validation_msg); document.getElementById("customerName"+i).focus();return false;}
                        if(document.getElementById("fatherName"+i).value==""){alert(notblank_fathername_validation_msg); document.getElementById("fatherName"+i).focus();return false;}
                        if(document.getElementById("village"+i).value==""){alert(notblank_village_validation_msg); document.getElementById("village"+i).focus();return false;}
                        if(document.getElementById("mobilePhone"+i).value==""){alert(notblank_mobno_validation_msg); document.getElementById("mobilePhone"+i).focus();return false;}
                    }


                }
            















            //for unique partno

            for (var k = 2; k < 5; k++) {
                if (document.getElementById("customerName"+k).value != "")
                {
                    var res1 = true;
                    for (var m = 0; m < k-1; m++)
                    {

                        if (customerlist[m] == trimS(document.getElementById("customerName"+k).value))
                        {
                            res1 = false;
                        }
                    }
                    if (res1 == false)
                    {
                        alert(repeat_relwithcust_validation_msg);
                        document.getElementById("customerName" + k).focus();

                        return false;
                    }
                }
            }










                
            document.getElementById("step1").style.display = 'none'
            document.getElementById("step2").style.display='';
                

         
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
                var strURL="<%=cntxpath%>/serviceProcess.do?option=getVimNumberDetailsFromServer&vinNo=" + vinno+"&tm=" + new Date().getTime();
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
    
        function getCommonCode(obj,nameObj)
        {
            var objjCode = obj.value;
            var strURL="<%=cntxpath%>/data_master/getCompCodeListAjax.jsp?objjCode=" + objjCode+"&nameObj="+nameObj+"&rowCount=0";
            xmlHttp = GetXmlHttpObject();
            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")

                {
                    res = trim(xmlHttp.responseText);

                    document.getElementById(nameObj).innerHTML=res;
                }
            }
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);
        }

                          


        function addRows(rowno){
                                
            if(count==0)
                count=parseInt(rowno)+1;

            var tbody = document.getElementById("tractorinfo");
        
            var row = document.createElement("TR");
               
            row.style.backgroundColor="#FFFFFF";
     
            var td0 = document.createElement("TD");
            var strHtml1 = count;
            td0.setAttribute("align", "center");
            td0.setAttribute("height", "20");
            td0.innerHTML = strHtml1;

            //Part No, PartVendor Name,PartSerial No
            var td1 = document.createElement("TD");
            var strHtml1 = "<input id=\"Part No"+ count +"\" type=\"text\"   maxlength=\"20\" name=\"partNo" + count + "\" style=\"width:300px\" /><input type=\"hidden\" name=\"actiontakens\"   value=\""+count+"\"/>";
            td1.setAttribute("align", "left");
            td1.style.paddingLeft = "3px";
            // "padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;padding-top: 2px;padding-bottom: 2px";
            td1.style.paddingTop="2px";
            td1.style.paddingRight="3px";
            td1.style.paddingBottom="2px";
        
            td1.innerHTML = strHtml1;



            var td2 = document.createElement("TD");
            var strHtml2 = "<input name=\"partVendorName"+ count +"\" type=\"text\" id=\"PartVendor Name"+ count +"\" maxlength=\"40\" style=\"width:300px\" />";
            td2.setAttribute("align", "left");
            td2.innerHTML = strHtml2;
            td2.style.paddingLeft = "3px";
            // "padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;padding-top: 2px;padding-bottom: 2px";
            td2.style.paddingTop="2px";
            td2.style.paddingRight="3px";
            td2.style.paddingBottom="2px";

            var td3 = document.createElement("TD");
            var strHtml3 = "<input name=\"partSerialNo"+ count +"\" type=\"text\" id=\"PartSerial No"+ count +"\"  maxlength=\"40\"  style=\"width:250px\" />";
            td3.setAttribute("align", "left");
            td3.style.paddingLeft = "3px";
            td3.style.paddingTop="2px";
            td3.style.paddingRight="3px";
            td3.style.paddingBottom="2px";
            td3.innerHTML = strHtml3;
            //partVendorName,partSerialNo
            row.appendChild(td0);
            row.appendChild(td1);
            row.appendChild(td2);
            row.appendChild(td3);
        
            count = parseInt(count) + 1;
        
            tbody.appendChild(row);

       
        }


        function CommentsMaxLength(text,maxLength)
        {
            var diff = maxLength - text.value.length;
    
            if (diff < 0){
                alert("Cannot be greater than " + maxLength);
                return false;
            }
            //document.getElementById("msg_saveFAILED").style.display = "";
    
    
            return true;
    
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
                var saleDate=document.getElementById("Sale Date").value;
                var jobCardDate=document.getElementById("Visit Date").value;
                var finalInstallationDate=document.getElementById("Final Installation Date").value;
                if(jobCardDate!=""){
                    $( ".datepicker" ).datepicker();
                }
                if(saleDate!=""){
                    $( ".datepicker" ).datepicker();
                }
                if(finalInstallationDate!=""){
                    $( ".datepicker" ).datepicker();
                }else{
                  $( ".datepicker" ).datepicker( "setDate", ttdate);
                }
            });
        });

</script>


<form action="<%=cntxpath%>/installProcess.do?option=manageInstall" method="post" id="StandardChecksForm" enctype="multipart/form-data">
    <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/initService.do'><bean:message key="label.common.Service" /></a>@@<a href='<%=cntxpath%>/installProcess.do?option=initViewAllInstallList'><bean:message key="label.common.pendinginstallation" /></a>"/>
    <div id="step1">
        <div class="breadcrumb_container">
            <ul>

                <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link> </li>
                <li class="breadcrumb_link"><a href='<%=cntxpath%>/installProcess.do?option=initViewAllInstallList'><bean:message key="label.common.pendinginstallation" /></a></li>
                <li class="breadcrumb_link">${serviceform.vinNo}</li>
            </ul>
        </div>
        <div class="message" id="msg_saveFAILED">${message}</div>
        <center>
            <div class="content_right1">
                <div class="con_slidediv2" style="position: relative;width: 100%">
                    <h1><label><bean:message key="label.common.installationforChassisno" /> ${serviceform.vinNo}</label></h1>


                    <%--<div class="message1" id="msg_saveFAILED">${message}</div>--%>

                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >


                        <tr><td>
                                <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1" >

                                    <tr bgcolor="#eeeeee" height="20">
                                        <td  class="headingSpas" align="left" ><label><B><bean:message key="label.common.vehicleinfo" /></B></label></td>
                                    </tr>
                                </table>
                            </td></tr>

                        <tr><td><%--<form action="<%=cntxpath%>/installProcess.do?option=manageInstall" method="post" id="StandardChecksForm" enctype="multipart/form-data">--%>
                                <!--<form action="<%=cntxpath%>/installProcess.do?option=initStandardChecks4Install" method="post" id="installationform" >-->
                                <table width="100%" border="0" align="center" cellpadding="1" cellspacing="5"  >

                                    <tr>
                                        <td width="15%" align="right"><span class="headingSpas">
                                                <label><bean:message key="label.common.Chassisno" /></label></span></td>
                                        <td width="15%" align="left"  >
                                            <input name="vinNo" type="text" id="VIN No." maxlength="50" value="${serviceform.vinNo}" readonly  />
                                            <input name="vinid" type="hidden"  maxlength="50" value="${serviceform.vinid}" readonly />
                                        </td>
                                        <td align="right"><span class="headingSpas">
                                                <label><bean:message key="label.common.engineno" /></label>
                                            </span></td>
                                        <td align="left" >
                                            <input name="engineNumber" type="text" id="Engine Number" value="${serviceform.engineNumber}" readonly="true" />
                                        </td>

                                        <td align="right"><span class="headingSpas">
                                                <label><bean:message key="label.common.Modelfamily" /></label>
                                            </span></td>
                                        <td align="left" >
                                            <input name="modelfamily" type="text" id="Model Family" value="${serviceform.modelFamily}" readonly="true" />
                                        </td>

                                    </tr>
                                    <tr>

                                        <td align="right"><span class="headingSpas">
                                                <label><bean:message key="label.common.dealerinvoiceno" />&nbsp;<span class="mandatory">*</span></label>
                                            </span></td>
                                        <td align="left" >
                                            <input name="dealerinvoiceno" type="text" id="Dealer Invoice No" value="${serviceform.dealerinvoiceno}"  maxlength="40" />
                                        </td>

                                        <td align="right" style="" ><span class="headingSpas">
                                                <label><bean:message key="label.common.dealerinvoicedate" />&nbsp;<span class="mandatory">*</span></label>
                                            </span></td>
                                        <td align="left" id="saledatetd">
                                            <input name="saleDate" type="text" id="Sale Date" value="${serviceform.saleDate}" class="datepicker" />
                                        </td>
                                        <td align="right"><span class="headingSpas">
                                                <label><bean:message key="label.common.delivery.date" /> &nbsp;<span class="mandatory">*</span></label>
                                            </span>
                                        </td>
                                        <td align="left" style="white-space: nowrap">
                                            <input name="deliveryDate" type="text"  id="Delivery Date" readonly size="15" value="${serviceform.deliveryDate}" />
                                        </td>
                                    </tr>


                                    <tr>

                                        <td width="14%" align="right" style="" ><div align="right"> <span class="headingSpas">
                                                    <label><bean:message key="label.common.installer" />&nbsp;<bean:message key="label.common.name" /> &nbsp;<span class="mandatory">*</span></label>
                                                </span></div></td>
                                        <td width="12%" align="left">
                                            <select name="repname" id="Representative Name" >
                                                <option value="">--select--</option>
                                                <c:forEach items='${mechanicList}'  var='labelValue'  varStatus='status'>
                                                    <option value='${labelValue.value}' title='${labelValue.label}'>${labelValue.label}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                        <td align="right"><span class="headingSpas">
                                                <label><bean:message key="label.common.visit" />&nbsp;<bean:message key="label.common.date" /> </label>&nbsp;<span class="mandatory">*</span>
                                            </span></td>
                                        <td align="left" style="white-space: nowrap">
                                            <input name="jobCardDate" type="text" class="datepicker" id="Visit Date" size="15" style="background-color: #fff" value="${serviceform.jobCardDate}" readOnly="true"/>

                                        </td>
                                        <td align="right"><span class="headingSpas">
                                                <label><bean:message key="label.common.finalinstallationdate" /></label>&nbsp;<span class="mandatory">*</span>
                                                </span>
                                        </td>
                                        <td align="left" style="white-space: nowrap">
                                            <input name="insDate" type="text"   class="datepicker" id="Final Installation Date" size="15" readOnly="true"  style="background-color:#fff" value="${serviceform.insDate}" />

                                        </td>



                                        


                                    </tr>
                                    <tr>
                                    <td align="right" style="" id="HMRLABEL"><div align="right"> <span class="headingSpas">
                                                    <label><bean:message key="label.common.hrm" />&nbsp;<span class="mandatory">*</span></label>
                                                </span></div></td>
                                        <td align="left" id="HMRVALUE">
                                            <input name="hmr" type="text" id="HMR"  value="${serviceform.hmr}" maxlength="7" />
                                        </td>


                                        <td align="right"><span class="headingSpas">
                                                <label><bean:message key="label.common.Registrationno" />  </label>
                                            </span></td>
                                        <td align="left" >
                                            <input name="registrationNo" type="text" id="Registration No."  value="${serviceform.registrationNo}" readonly="true" />
                                        </td>
                                        <td align="right" style="white-space: nowrap"><span class="headingSpas">
                                                <label><bean:message key="label.common.Servicebookletno" /> </label>
                                            </span>
                                        </td>
                                        <td align="left" >
                                            <input name="serviceBookletNo" type="text" id="Service Booklet No."  maxlength="40" value="${serviceform.serviceBookletNo}" />
                                        </td>
                                    </tr>

                                </table>
                            </td>
                        </tr>
                    </table>
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >
                        <tr><td>
                                <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1">
                                    <tr bgcolor="#eeeeee" height="20">

                                        <td class="headingSpas" align="left"><B><label><bean:message key="label.common.customerinfo" /></label></B></td>


                                    </tr>
                                </table>
                            </td></tr>


                        <tr>

                            <td>
                                <table  width="100%" border="0" align="center" cellpadding="3" cellspacing="3"  >
                                    <tbody>

                                        <tr>
                                            <td width="16%" align="right" class="headingSpas" style="white-space: nowrap"><bean:message key="label.common.CustomerNameCaps" /></td>
                                            <td width="16%" align="left" >
                                                <input name="customerName" type="text" id="Customer Name" value="${serviceform.customerName}" maxlength="70" readOnly="true" />
                                            </td>


                                            <td  width="16%" align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label><bean:message key="label.common.mobilephone" /></label></td>

                                            <td  width="16%" align="left" >
                                                <input name="mobilePhone" type="text" id="Mobile Phone" value="${serviceform.mobilePhone}" maxlength="10"  readonly="true" />
                                            </td>


                                            <td  width="16%" align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label><bean:message key="label.common.village" /></label></td>

                                            <td  width="16%" align="left" >
                                                <input name="village" type="text" id="Village" value="${serviceform.village}" maxlength="70"  readonly="true" />
                                            </td>

                                        </tr>

                                        <tr>

                                            <td  width="16%" align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label><bean:message key="label.common.talukatahsil" /></label></td>

                                            <td  width="16%" align="left" >
                                                <input name="tehsil" type="text" id="Tehsil" value="${serviceform.tehsil}" maxlength="40"  readonly="true" />
                                            </td>




                                            <td  width="16%" align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label><bean:message key="label.common.district" /></label></td>

                                            <td  width="16%" align="left" >
                                                <input name="district" type="text" id="District" value="${serviceform.district}" maxlength="40"  readonly="true" />
                                            </td>

                                            <td  width="16%" align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label><bean:message key="label.common.pincode" /></label></td>

                                            <td  width="16%" align="left" >
                                                <c:if test="${serviceform.pinCode eq '0'}">
                                                    <input name="pinCode" type="text" id="Pincode" value="" maxlength="6" readonly="true"/>
                                                </c:if>
                                                <c:if test="${serviceform.pinCode ne '0'}">
                                                    <input name="pinCode" type="text" id="Pincode" value="${serviceform.pinCode}" maxlength="6"  readonly="true" />
                                                </c:if>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td  width="16%" align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label><bean:message key="label.common.state" /></label></td>

                                            <td  width="16%" align="left" >
                                                <input name="state" type="text" id="State" value="${serviceform.state}" maxlength="40"  readonly="true" />
                                            </td>

                                            <td  width="16%" align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label><bean:message key="label.common.sizeoflandhold" /></label></td>

                                            <td  width="16%" align="left" >
                                                <input name="sizeoflandhold" type="text" id="Size of Land Holding" value="${serviceform.sizeoflandhold}" maxlength="40"  readonly="true" />
                                            </td>
                                            <td width="16%" align="right"  class="headingSpas" style="white-space: nowrap" >
                                                <label><bean:message key="label.common.maincrop" /></label></td>

                                            <td  width="20%" align="left" >
                                                <input name="maincrops" type="text" id="Main Crops" value="${serviceform.maincrops}" maxlength="100"/>
                                            </td>

                                        </tr>

                                        <tr>
                                            <td align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label><bean:message key="label.common.occupation" /></label></td>

                                            <td align="left" >
                                                <input name="occupation" type="text" id="Occupation" value="${serviceform.occupation}" maxlength="50"/>
                                            </td>




                                            <td align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label><bean:message key="label.common.nooffamilymembers" /></label></td>

                                            <td align="left" >
                                                <input name="familyMembers" type="text" id="Family Members"  maxlength="2"/>
                                            </td>

                                            <td align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label><bean:message key="label.common.otherTractorDetail" /></label></td>

                                            <td align="left" >
                                                <input name="othertractorDetail" type="text" id="Other Tractor Detail" maxlength="35"/>
                                            </td>

                                        </tr>
<tr>
                                            <td align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label><bean:message key="label.common.contactname" />&nbsp;<span class="mandatory">*</span></label></td>

                                            <td align="left" >
                                                <input name="contactname" type="text" id="Contact Name" value="" maxlength="75"/>
                                            </td>




                                            <td align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label><bean:message key="label.common.contactno" />&nbsp;<span class="mandatory">*</span></label></td>

                                            <td align="left" >
                                                <input name="contactno" type="text" id="Contact No."  maxlength="20"/>
                                            </td>

                                            <td align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label><bean:message key="label.common.relationwithcustomer" />&nbsp;<span class="mandatory">*</span></label></td>

                                            <td align="left" >
                                                <input name="relationshipwithcust" type="text" id="Relation with Customer" maxlength="35"/>
                                            </td>

                                        </tr>

                                        <tr>
                                            <td align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label><bean:message key="label.common.modeofpayment" /></label>
                                            </td>

                                            <td align="left" >
                                                <input name="paymentMode" type="text" id="Payment Mode" maxlength="70"/>
                                            </td>




                                            <td align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label><bean:message key="label.common.nameofbank" /></label>
                                            </td>

                                            <td align="left" >
                                                <input name="bankName" type="text" id="Bank Name" maxlength="80"/>
                                            </td>

                                            <td align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label><bean:message key="label.common.uploadphoto" />&nbsp;<span class="mandatory">*</span></label>
                                            </td>

                                            <td align="left" style="padding-left:8px;">
                                                <input type="file" name="uploadphoto" id="Upload Photo" style="width:178px;height: 22px;background-color:#fff;line-height:22px;border:solid 1px #CCCCCC;" />
                                                <input name="filesize" type="hidden" id="filesize" value=""/>
                                            </td>
                                        </tr>
                                        <tr height="4">
                                            <td align="right" colspan="10" height="4" style="padding-right: 20px"><label style="color: red;font-size:10px " ><B>( <bean:message key="label.common.maxfilesize" /> )</B></label>
                                            </td>
                                        </tr>

                                         
                                    </tbody>
                                </table></td>
                        </tr>
                    </table>
                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >
                        <tr><td>
                                <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1">
                                    <tr bgcolor="#eeeeee" height="20">
                                        <td class="headingSpas" align="left" ><B><label><bean:message key="label.common.TractorInfomation" /></label></B></td>
                                    </tr>
                                </table>
                            </td></tr>


                        <tr>
                            <td id="travelcardData">
                                <table  width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc >
                                    <tbody id="tractorinfo">
                                        <tr bgcolor="#FFFFFF" height="20"  class="headingSpas">
                                            <td width="10%" align="center" ><B><bean:message key="label.common.sno" /></B></td>
                                            <td width="40%" align="left" ><B><bean:message key="label.common.partname" /></B></td>
                                            <td width="20%" align="left" ><B><bean:message key="label.common.partvendorname" />&nbsp;<span class="mandatory">*</span></B></td>
                                            <td width="20%" align="left" ><B><bean:message key="label.common.partSerialNo" />&nbsp;<span class="mandatory">*</span></B></td>
                                        </tr>
                                        <c:set var="srNo" value="1"/>
                                        
                                       <c:if test="${!empty travelcardPartList}">
                                        <c:forEach items="${travelcardPartList}" var="travelcardPartList">
                                         <c:if test="${!empty pditravelcardPartList}">
                                          <c:set var="counterFlag" value="1"/>
                                         <c:forEach items="${pditravelcardPartList}" var="pditravelcardPartList">
                                             <c:if test="${pditravelcardPartList.contentDesc eq travelcardPartList.key}">
                                            <tr bgcolor="#FFFFFF" height="20">
                                            <input type="hidden" name="actiontakens"  value="${srNo}"/>
                                            <td align="center">${srNo}</td>
                                            <td align="left" nowrap  style="padding-left:3px;padding-right:3px;padding-top: 2px;padding-bottom: 2px" >
                                                <input name="partNo${srNo}" type="text" id="Part No${srNo}" style=" background-color:#E6E4E4;display: none;"  value="${travelcardPartList.key}" maxlength="20"/>
                                                <span>${travelcardPartList.key}</span>
                                            </td>                                                         

                                            <td align="left" nowrap  style="padding-left:3px;padding-right:3px;padding-top: 2px;padding-bottom: 2px" >
                                                <%--<input name="partVendorName${srNo}" type="text" id="PartVendor Name${srNo}" style="width:300px !important" title="" value="" maxlength="40"/>--%>
                                                <select name="partVendorName${srNo}" type="text" id="Make${srNo}" >
                                                <option value="">--Select--</option>
                                                <c:forEach items="${travelcardPartList.value}" var="makeList">
                                                    <c:if test="${makeList eq pditravelcardPartList.observation}">
                                                        <option value='${makeList}' title='${makeList}' selected>${makeList}</option>
                                                    </c:if>
                                                    <c:if test="${makeList ne pditravelcardPartList.observation}">
                                                        <option value='${makeList}' title='${makeList}'>${makeList}</option>
                                                    </c:if>
                                                </c:forEach>
                                                </select>
                                            </td>

                                            <td align="left"  nowrap  style="padding-left:3px;padding-right:3px;padding-top: 2px;padding-bottom: 2px">
                                                <input name="partSerialNo${srNo}" type="text" id="PartSerial No${srNo}" title="" value="${pditravelcardPartList.serialno}" maxlength="40"/>
                                            </td>
                                        </tr>
                                        <c:set var="counterFlag" value="${counterFlag+1}"/>
                                        </c:if>
                                </c:forEach>
                                 <c:if test="${counterFlag eq 1}">
                                   <tr bgcolor="#FFFFFF" height="20">
                                            <input type="hidden" name="actiontakens"  value="${srNo}"/>
                                            <td align="center">${srNo}</td>
                                            <td align="left" nowrap  style="padding-left:3px;padding-right:3px;padding-top: 2px;padding-bottom: 2px" >
                                                <input name="partNo${srNo}" type="text" id="Part No${srNo}" style=" background-color:#E6E4E4;display: none;"  value="${travelcardPartList.key}" maxlength="20"/>
                                                <span>${travelcardPartList.key}</span>
                                            </td>

                                            <td align="left" nowrap  style="padding-left:3px;padding-right:3px;padding-top: 2px;padding-bottom: 2px" >
                                                <%--<input name="partVendorName${srNo}" type="text" id="PartVendor Name${srNo}" style="width:300px !important" title="" value="" maxlength="40"/>--%>
                                                <select name="partVendorName${srNo}" type="text" id="Make${srNo}" >
                                                <option value="">--Select--</option>
                                                <c:forEach items="${travelcardPartList.value}" var="makeList">
                                                  <option value='${makeList}' title='${makeList}'>${makeList}</option>
                                                </c:forEach>
                                                </select>
                                            </td>

                                            <td align="left"  nowrap  style="padding-left:3px;padding-right:3px;padding-top: 2px;padding-bottom: 2px">
                                                <input name="partSerialNo${srNo}" type="text" id="PartSerial No${srNo}"  title="" value="" maxlength="40"/>
                                            </td>
                                    </tr>         
                                 </c:if>
                                </c:if>
                                <c:if test="${empty pditravelcardPartList}">
                                     <tr bgcolor="#FFFFFF" height="20">
                                            <input type="hidden" name="actiontakens"  value="${srNo}"/>
                                            <td align="center">${srNo}</td>
                                            <td align="left" nowrap  style="padding-left:3px;padding-right:3px;padding-top: 2px;padding-bottom: 2px" >
                                                <input name="partNo${srNo}" type="text" id="Part No${srNo}" style="background-color:#E6E4E4;display: none;"  value="${travelcardPartList.key}" maxlength="20"/>
                                                <span>${travelcardPartList.key}</span>
                                            </td>

                                            <td align="left" nowrap  style="padding-left:3px;padding-right:3px;padding-top: 2px;padding-bottom: 2px" >
                                                <%--<input name="partVendorName${srNo}" type="text" id="PartVendor Name${srNo}" style="width:300px !important" title="" value="" maxlength="40"/>--%>
                                                <select name="partVendorName${srNo}" type="text" id="Make${srNo}" >
                                                <option value="">--Select--</option>
                                                <c:forEach items="${travelcardPartList.value}" var="makeList">
                                                  <option value='${makeList}' title='${makeList}'>${makeList}</option>
                                                </c:forEach>
                                                </select>
                                            </td>

                                            <td align="left"  nowrap  style="padding-left:3px;padding-right:3px;padding-top: 2px;padding-bottom: 2px">
                                                <input name="partSerialNo${srNo}" type="text" id="PartSerial No${srNo}" title="" value="" maxlength="40"/>
                                            </td>
                                    </tr>
                                </c:if>
                                <c:set var="srNo" value="${srNo+1}"/>
                                
                        </c:forEach></c:if>

                        </tbody>

                        <%--<tr bgcolor="#FFFFFF">
                            <td align="right" colspan="4" style="padding-right:12px;">
                                <input name="Button" type="button" value="+" onclick="addRows(${totallist})"/>
                            </td>
                        </tr>--%>

                    </table></td>
                    </tr>



                <tr><td>
                                <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1">
                                    <tr bgcolor="#eeeeee" height="20">
                                        <td class="headingSpas" align="left" ><B><label><bean:message key="label.common.insenquiry" /></label></B></td>
                                    </tr>
                                </table>
                            </td></tr>


                        <tr>
                            <td id="enquiriesdata">
                                <table  width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc >
                                    <tbody id="tractorinfo">
                                        <tr bgcolor="#FFFFFF" height="20"  class="headingSpas">
                                            <td align="left"><B><bean:message key="label.common.sno" /></B></td>
                                            <td align="left"><B><label><bean:message key="label.common.CustomerName" /></label></B></td>
                                            <td align="left"><B><label><bean:message key="label.common.fathername" /></label></B></td>
                                            <td align="left"><B><label><bean:message key="label.common.contactno" /> </label></B></td>
                                            <td align="left"><B><label><bean:message key="label.common.village" /> </label></B></td>
                                            <td align="left"><B><label><bean:message key="label.common.relationwithcustomer" /></label> </B></td>
                                        </tr>
                                        <c:set var="srNo_enq" value="1"/>
                                        <c:set var="tsrNo" value="5"/>
                                        <c:set var="totallist" value="0"/>
                                        

                        
                            <c:forEach begin="1" end="${tsrNo}" var="tsrNo">
                                <tr bgcolor="#FFFFFF" height="20">
                                <input type="hidden" name="enquiry" value="${srNo_enq}"/>
                                <td align="center">${tsrNo}</td>
                                <td align="left" nowrap  >
                                    <input name="custName${tsrNo}" type="text" id="customerName${tsrNo}" class="partnumber" title="" value="" maxlength="60"/>
                                </td>

                                <td align="left" nowrap  >
                                    <input name="fatherName${tsrNo}" type="text" id="fatherName${tsrNo}" class="partnumber" title="" value="" maxlength="60"/>
                                </td>

                                <td align="left"  nowrap >
                                    <input name="mobilePh${tsrNo}" type="text" id="mobilePhone${tsrNo}" class="partnumber" title="" value="" maxlength="60"/>
                                </td>
                                <td align="left"  nowrap >
                                    <input name="villagename${tsrNo}" type="text" id="village${tsrNo}" class="partnumber" title="" value="" maxlength="60"/>
                                </td>
                                <td align="left"  nowrap >
                                    <input name="relationwithcustomer${tsrNo}" type="text" id="relationwithcustomer${tsrNo}" class="partnumber" title="" value="" maxlength="45"/>
                                </td>
                                </tr>
                                <c:set var="srNo_enq" value="${srNo_enq+1}"/>
                                <c:set var="totallist" value="${totallist+1}"/>
                            </c:forEach>
                        



                        </tbody>

                        <tr bgcolor="#FFFFFF" align="center" height="40">
                            <td  colspan="6" >
                                <input name="ListSize" id="ListSize" type="hidden" value="${srNo}"/>
                                <input name="input" type="button" value="Next" onclick="return validateForm(${srNo});"/>
                            </td>
                        </tr>

                    </table></td>
                    </tr>
                    </table>
                </div>
            </div>
        </center>
    </div>

    <div id="step2" style="display: none">
        <div class="breadcrumb_container">
            <ul>
                <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link> </li>
                <li class="breadcrumb_link"><bean:message key="label.common.installation" /></li>
            </ul>
        </div>

        <div class="message" id="msg_saveFAILED">${message}</div>
        <center>
            <div class="content_right1">
                <div class="con_slidediv2" style="position: relative;width: 100%">
                    <h1><bean:message key="label.common.installationforChassisno" /> ${serviceform.vinNo}</h1>


                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >
                        <tr><td>
                                <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1">
                                    <tr bgcolor="#eeeeee" height="20">
                                        <td class="headingSpas" align="left"><B><label><bean:message key="label.common.insCheckList" /></label></B></td>

                                    </tr>
                                </table>
                            </td></tr>

                        <tr><td>
                                <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>
                                    <tr bgcolor="#eeeeee" height="20"  class="headingSpas">
                                        <td width="10%" align="center"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><bean:message key="label.common.sno" /></label></B></td>
                                        <td align="left" width="80%"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><bean:message key="label.common.instructions" /></label></B></td>
                                        <td width="10%" align="center" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><bean:message key="label.common.tickhere" /></label></B></td>

                                    </tr>
                                    <c:set var="count" value="1"/>
                                    <c:forEach items="${serviceForm.contentList}" var="contentList">

                                        <c:choose>
                                            <c:when test="${empty contentList}">

                                                <tr bgcolor="#FFFFFF" height="20">
                                                    <td width="3%" align="center">${count}</td>
                                                    <td ><label>${contentList.contentDesc}</label><input type="hidden" name="contentId" value="${contentList.contentId}"/></td>
                                                    <td align="center">
                                                        <input type="checkbox" name="${contentList.contentId}checkpoints"/>
                                                    </td>
                                                    <td ><input type="text" name="${contentList.contentId}observations" id="Observation" maxlength="100" style="width:200px !important "/></td>
                                                </tr>
                                            </c:when>
                                            <c:otherwise>

                                                <tr bgcolor="#FFFFFF" height="20" >
                                                    <td width="3%" align="center">${count}</td>
                                                    <td align="left" style="padding-left:3px;padding-right:3px;">
                                                        ${contentList.contentDesc}<input type="hidden" name="contentId" value="${contentList.contentId}"/>
                                                    </td>
                                                    <td align="center">
                                                        <input type="checkbox" name="${contentList.contentId}checkpoints" id="checkpoints${count}"/>
                                                    </td>
                    <!--                                <td ><input type="text" name="${contentList.contentId}observations" value="${subCList.observations}" id="Observation" maxlength="100"  style="width:200px !important "/></td>-->
                                                </tr>
                                            </c:otherwise>

                                        </c:choose>
                                        <c:set var="count" value="${count+1}"/>
                                    </c:forEach>

                                </table>
                                     </td></tr>
                                <!--</form>-->
                                <tr><td>
                                <table width=100%  border=0 cellspacing=1 cellpadding=4  bgcolor=#FFFFFF>

                                    <tr bgcolor="#FFFFFF" height="20">

                                        <td valign="top" width="100%" align="center">




                                <table width=100%  border=0 cellspacing=0 cellpadding=1  bgcolor=#cccccc>

                                    <tr bgcolor="#FFFFFF" height="20">

                                        <td valign="top" width="33%">
                                            <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc >
                                                <tbody>
                                                    <tr bgcolor="#FFFFFF" height="25">
                                                        <td class="headingSpas" align="left" colspan="2" style="padding-left:5px"><B><bean:message key="label.common.accessoriesrecieved" /></B></td>
                                                    </tr>
                                                    <c:set  var="sNo" value="1"></c:set>
                                                    <c:forEach items="${accessoriesList}" var="labelValue">
                                                        <c:if test="${!empty labelValue}">
                                                        <input type="hidden" name="accessories" value="${sNo}"/>
                                                        <tr bgcolor="#FFFFFF" height="20" >
                                                            <td align="left" class="headingSpas" style="padding-left:5px;white-space: nowrap" width="75%"><label> ${labelValue.label} </label></td>
                                                            <td align="center" width="25%">
                                                                <input type="checkbox" name="assrev${sNo}" id="assrev${sNo}" value="${labelValue.label}"/>
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                    <c:set var="sNo" value="${sNo+1}"/>
                                                </c:forEach>
                                                </tbody>
                                            </table>
                                        </td>
                                        <td valign="top" width="1%"></td>
                                        <td valign="top" width="33%">
                                            <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc  >
                                                <tbody>
                                                    <tr bgcolor="#FFFFFF" height="25">
                                                        <td colspan="2" class="headingSpas" align="left" style="padding-left:5px"><B> <bean:message key="label.common.major" /> &nbsp;<bean:message key="label.common.application" /></B></td>
                                                    </tr>
                                                    <c:set  var="sNo" value="1"></c:set>
                                                    <c:forEach items="${majorAppList}" var="labelValue">
                                                        <c:if test="${!empty labelValue}">
                                                        <input type="hidden" name="majorApp" value="${sNo}"/>
                                                        <tr bgcolor="#FFFFFF" height="20" >
                                                            <td align="left" class="headingSpas" style="padding-left:5px;white-space: nowrap"><label> ${labelValue.label} </label></td>
                                                            <td align="center">
                                                                <input type="checkbox" name="majapp${sNo}" id="majapp${sNo}" value="${labelValue.label}"/>
                                                            </td>
                                                        </tr>
                                                    </c:if>
                                                    <c:set var="sNo" value="${sNo+1}"/>
                                                </c:forEach>
                                                </tbody>
                                            </table></td>
                                            <td valign="top" width="1%"></td>
                                            <td valign="top" width="32%">
                                            <table width="100%">
                                            <tr>
                                            <td align="left" width="10%">
                                                <span class="headingSpas">
                                                <label><b><bean:message key="label.common.anyobservation" /></b></label>
                                                </span></td>
                                            </tr><tr>
                                            <td align="right"  width="100%">
                                                <textarea name="remarks" rows="4"  id="Any Observation"  onblur="if(!CommentsMaxLength(this, 450)){this.value=this.value.substring(0, 450);}">${serviceform.compVerbatim[count]}</textarea>
                                            </td>
                                            </tr>
                                             </table>
                                            </td>

                                            
                                    </tr>

                                  </table>
                                 </td>
                                </tr>
                                 </table>
                
                            
                          
                        

                    </td>
                    </tr>



                    <tr bgcolor="#FFFFFF" height="50">
                        <td align="center">
                            
                                <input name="input1" type="button" value="<< BACK" onclick="goBack();"/> &nbsp;&nbsp;&nbsp;
                                <input name="input2" type="button" value="Submit" onclick="submitStandarCheckForm(${count},${sNo});"/>
                        </td>
                    </tr>



                    </table>

                </div>
            </div>

        </center>
    </div>

</form> 