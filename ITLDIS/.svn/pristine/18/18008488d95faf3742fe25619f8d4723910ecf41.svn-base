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
<%@ page import="java.util.*" %>
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

    var contextPath = '${pageContext.request.contextPath}';
//    document.getElementById("step2").style.display = 'none'
    function validate()
    {
        document.getElementById("installationform").submit();
        //        var jobType=document.forms[0].jobType.value;
    }
    function goBack()
    {
        document.getElementById("step2").style.display = 'none'
        document.getElementById("step1").style.display='';
    }
    function submitStandarCheckForm()
    {
        var flag=0;
        for(var i=1;i<6;i++)
        {
            if (document.getElementById('majapp5').checked) {
                flag=1;
            }
        }
        if(flag==0)
        {
            alert('MAJOR APPLICATION not selected')
            return false;
        }
        
        document.getElementById("StandardChecksForm").submit();
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

    function validateForm()
    {
        var elementArr = new Array('VIN No.', 'Sale Date', 'HMR WORKING','Location','Visit Date');

        var strObject = null;

        for (var i = 0; i < elementArr.length; i++)
        {

            strObject = document.getElementById(elementArr[i]);
            if (strObject.value=="" && i==0)
            {
                alert("Please enter Chassis No");
                //document.getElementById('msg_saveFAILED').innerHTML="Please enter Chassis No";
                return false;
            }
            if(strObject.value=="" && i>0)
            {
                alert("Please enter value in " + elementArr[i] + " field.");
                //document.getElementById('msg_saveFAILED').innerHTML = "Please enter value in " + elementArr[i] + " field.";
                return false;
            }
        }

        if(validNumberdigit(document.getElementById("HMR").value)==false){

//            document.getElementById('msg_saveFAILED').innerHTML = "Enter numeric value in HRM";
                alert("Enter numeric value in HRM");
            return false;
        }

        if(document.getElementById("HMR WORKING").value==true)
        {
            if(document.getElementById("HMR").value==""){
                alert("Enter  value in HRM");
                //document.getElementById('msg_saveFAILED').innerHTML = "Enter  value in HRM";

            }
            if(validNumberdigit(document.getElementById("HMR").value)==false){

                alert("Enter numeric value in HRM");
                //document.getElementById('msg_saveFAILED').innerHTML = "Enter numeric value in HRM";
                return false;
            }
         
        }
        var size= parseInt(document.getElementById("ListSize").value);
        if(size < 2)
        {
            alert("Tractor information not found");
            return false;
        }
        document.getElementById("step1").style.display = 'none'
        document.getElementById("step2").style.display='';
//        document.getElementById("installationform").submit();
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
    
</script>
<div id="step1">
<div class="breadcrumb_container">
    <ul>
        <li class="breadcrumb_link"><html:link action="/initService">SERVICE</html:link> </li>
        <li class="breadcrumb_link">Installation</li>
    </ul>
</div>



<div class="message1" id="msg_saveFAILED">${message}</div>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >


    <tr><td>
            <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1">

                <tr align="center" valign="middle" class="formHeader">
                        <td class="formSelectedHeader"><label>Installation Information</a></label></td>
                </tr>
            </table>
        </td></tr>

    <tr><td><form action="<%=cntxpath%>/installProcess.do?option=manageInstall" method="post" id="StandardChecksForm">
            <!--<form action="<%=cntxpath%>/installProcess.do?option=initStandardChecks4Install" method="post" id="installationform" >-->
                <table width="100%" border="0" align="center" cellpadding="1" cellspacing="5"  >
                    <tbody>
                        <tr>
                            <td align="right" style=""><div align="right"> <span class="formLabel">
                                        <label>Buyer Name</label></span></div></td>
                            <td align="left" >
                                <input name="buyerName" type="text" id="Buyer Name" value="${serviceform.buyerName}"/>
                            </td>
                            <td align="right"><span class="formLabel">
                                    <label>Buyer Price</label>
                                </span>
                            </td>
                            <td align="left" style="white-space: nowrap">
                                <input name="buyerPrice" type="text" id="BuyerPrice"  value="${serviceform.buyerPrice}" />
                            </td>
                            <td width="173" align="right" ><span class="formLabel">
                                    <label>Model Family</label>
                                </span></td>
                            <td width="118" align="left" >
                                <input name="modelFamily" type="text" id="Model Family"  maxlength="" value="${serviceform.modelFamily}" readonly="true"/>
                            </td>
                        </tr>
                        <tr>
                            <td width="157" align="right"><span class="formLabel">
                                    <label>Chassis No</label>&nbsp;<span class="mandatory">*</span></span></td>
                            <td width="125" align="left"  >
                                <c:choose>
                                    <c:when test="${serviceform.jobcardview eq 'true' }">
                                        <input name="vinNo" type="text" id="VIN No." maxlength="50" value="${serviceform.vinNo}" readonly />
                                    </c:when>
                                    <c:otherwise>
                                        <input name="vinNo" type="text" id="VIN No." maxlength="50"  />
                                        <img src='<%=cntxpath%>/image/arrdown.gif' alt='Get Suggestions' name="img1" border='0'  onclick="getVinDetails_install('VIN No.',document.getElementById('VIN No.'),document.getElementById('img'));"/>
                                        <img alt=""  id='img' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/image/load.gif'/>
                                        <input type="hidden" name="check" value="notexist" id ="check">
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td align="right" style="" ><div align="right"> <span class="formLabel">
                                        <label>Representative Name</label>
                                    </span></div></td>
                            <td align="left">
                                <input name="repName" type="text" id="Representative Name" maxlength="" value="" />
                            </td>
                            <td align="right"><span class="formLabel">
                                    <label>Model Code</label>
                                </span></td>
                            <td align="left" >
                                <input name="modelCode" type="text" id="Model Code"  maxlength="" value="${serviceform.modelCode}" readOnly="true"/>
                            </td>

                        </tr>
                        <tr>
                            <td align="right" style="" ><span class="formLabel">
                                    <label>Sale Date</label>&nbsp;<span class="mandatory">*</span>
                                </span></td>
                            <td align="left" id="saledatetd">
                                <input name="saleDate" type="text" class="datepicker" id="Sale Date" value="${serviceform.saleDate}" disabled="disabled"/>
                            </td>
                            <td align="right" style="" ><div align="right"> <span class="formLabel">
                                        <label>Invoice No.</label>
                                    </span></div></td>
                            <td align="left">
                                <input name="invoiceno" type="text" id="invoiceno" maxlength="" value="" />
                            </td>
                            <td align="right" style=""><div align="right"> <span class="formLabel">
                                        <label>Model Family Desc</label>.
                                    </span></div></td>
                            <td align="left">
                                <input name="modelFamilyDesc" type="text" id="Model Family Desc"  maxlength="" value="${serviceform.modelFamilyDesc}" readonly="true"/>
                            </td>

                        </tr>

                        <tr>

                            <td align="right"><span class="formLabel">
                                    <label>Engine Number</label>
                                </span></td>
                            <td align="left" >
                                <input name="engineNumber" type="text" id="Engine Number" value="${serviceform.engineNumber}" readonly="true"/>
                            </td>
                            <td align="right"><span class="formLabel">
                                    <label>Delivery Date</label>
                                </span>
                            </td>
                            <td align="left" style="white-space: nowrap">
                                <input name="delieveryDate" type="text" class="datepicker" id="Delivery Date" size="15" value="${serviceform.delieveryDate}" onblur="comparedt(this)"/>
                            </td>
<!--                            <td align="right"><span class="formLabel">
                                    <label>Invoice Date </label>&nbsp;<span class="mandatory">*</span>
                                </span></td>
                            <td align="left" style="white-space: nowrap">
                                <input name="invoiceDate" type="text" class="datepicker" id="Invoice Date" size="15" value="${serviceform.invoiceDate}" onblur="comparedt(this)"/>

                            </td>-->
                            
                            <td align="right" style="" ><div align="right"> <span class="formLabel">
                                        <label>Model Code Desc.</label>
                                    </span></div></td>
                            <td align="left">
                                <input name="modelCodeDesc" type="text" id="Model Code Desc." maxlength="" value="${serviceform.modelCodeDesc}" readonly="true"/>
                            </td>
                        </tr>
                        <tr>


                            <td align="right"><span class="formLabel">
                                    <label>Registration No.</label>
                                </span></td>
                            <td align="left" >
                                <input name="registrationNo" type="text" id="Registration No." maxlength="" value="${serviceform.registrationNo}"/>
                            </td>
                            <td align="right" style="" ><div align="right"> <span class="formLabel">
                                        <label>HMR WORKING</label>&nbsp;<span class="mandatory">*</span>
                                    </span></div></td>
                            <td align="left" >

                                
                                    <input name="hmeRadio" id="HMR WORKING" type="radio" value="Y" checked="checked" onclick="getdatehrs()"/>
                                    <span class="formLabel">Yes</span>
                                    <input name="hmeRadio"  type="radio" id="HMR WORKINGN" value="N" onclick="getdatehrs()"/>
                                    <span class="formLabel">No</span>
                               
                            </td>
                            <td align="right" style="" ><div align="right"> <span class="formLabel">
                                        <label>Location</label>
                                    </span></div></td>
                            <td align="left">
                                <input name="jobLocation" type="text" id="Location" maxlength="" value="" />
                            </td>
                        </tr>
                        <tr>
                            <td align="right" style="white-space: nowrap"><span class="formLabel">
                                    <label>Service Booklet No.</label>
                                </span>
                            </td>
                            <td align="left" >
                                <input name="serviceBookletNo" type="text" id="Service Booklet No."  maxlength="" value="${serviceform.serviceBookletNo}" />
                            </td>
                            <td align="right" style="" id="HMRLABEL"><div align="right"> <span class="formLabel">
                                        <label>HMR</label>
                                    </span></div></td>
                            <td align="left" id="HMRVALUE">
                                <input name="hmr" type="text" id="HMR"  value="${serviceform.hmr}" maxlength="" />
                            </td>
                            <td align="right"><span class="formLabel">
                                    <label>Visit Date </label>&nbsp;<span class="mandatory">*</span>
                                </span></td>
                            <td align="left" style="white-space: nowrap">
                                <input name="jobCardDate" type="text" class="datepicker" id="Visit Date" size="15" value="${serviceform.jobCardDate}" onblur="comparedt(this)"/>

                            </td>
                        </tr>
                        <tr>

                        </tr>
                        <tr>
                            <td align="right" style="" ><span class="formLabel">

                                    <label>Warranty Applicable</label>
                                </span></td >
                            <td align="left" id="Warranty App"> <font color="red">Yes</font></td>
                        </tr>
                        <tr>
                            <td align="right">&nbsp;</td>
                            <td align="left" >&nbsp;</td>
                            <td align="right" style="" >&nbsp;</td>
                            <td align="left">&nbsp;</td>
                            <td align="right" style="" >&nbsp;</td>
                            <td align="left">&nbsp;</td>
                        </tr>
<!--                        <tr align="right">
                            <td colspan="6" >
                                <input name="input" type="button" value="Save" style="float:none; " onclick="validateForm();"/>
                            </td>
                        </tr>-->
                </table>
        </td>
    </tr>
</table>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >
    <tr><td>
            <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1">
                <tr align="center" valign="middle" class="formHeader">
                    <!--<td ><label><a href="<%=cntxpath%>/installProcess.do?option=initInstallInfo&vinNo=3" class="formLabel">Installation Information</a></label></td>-->
                    <td class="formSelectedHeader"><label>Customer Information</label></td>
                    <!--<td><label><a href="<%=cntxpath%>/installProcess.do?option=initStandardChecks4Install&vinNo=3" class="formLabel">Standard Checks</a></label></td>-->

                </tr>
            </table>
        </td></tr>


    <tr>
    <!--<div class="message1" id="msg_saveFAILED">${message}</div>-->
    <td>
        <table  width="90%" border="0" align="center" cellpadding="3" cellspacing="3"  >
            <tbody>
                <tr>
                    <td align="right" class="formLabel" style="white-space: nowrap">Customer Name</td>
                    <td colspan="3" align="left"  >
                       <input name="customerName" type="text" id="Customer Name" style="width:350px !important"/>
                    </td>
                </tr>
                <tr>
                <td align="right"  style="white-space: nowrap"> <label>Village</label></td>
                <td align="left"  >
                    <input name="village" type="text" id="Village" size="15"/>
                </td>
                <td align="right" style="white-space: nowrap" >
                        <label>District</label></td>
                    <td align="left"  >
                        <input name="district" type="text" id="District" />

                    </td>
                <td align="right" style="white-space: nowrap" >
                    <label>Mobile Phone</label></td>
                <td align="left"  >
                    <input name="mobilePhone" type="text" id="Mobile Phone" />
                </td>
                </tr>
                <tr>
                    <td align="right"  style="white-space: nowrap">
                        <label>Taluka</label></td>
                    <td align="left"  >
                        <input name="taluka" type="text" id="Taluka"/>
                    </td>
                    <td align="right" style="white-space: nowrap" >
                        <label>Pin Code</label></td>
                    <td align="left"  >
                        <input name="pinCode" type="text" id="Pin Code" />
                    </td>
                    <td align="right"  style="white-space: nowrap">
                        <label>Landline</label>
                    </td>
                    <td align="left"  >
                       <input name="landline" type="text" id="Landline" />
                    </td>
                </tr>
                <tr>
                    <td align="right" class="formLabel" style="white-space: nowrap"><label>Tehsil</label></td>
                    <td align="left" >
                        <input name="tehsil" type="text" id="tehsil" />
                    </td>
                    <td align="right"  style="white-space: nowrap">
                        <label>State</label></td>
                    <td align="left"  >
                        <input name="state" type="text" id="State" />
                    </td>
                    <td align="right"  style="white-space: nowrap"><label>E-mail ID</label></td>
                    <td align="left"  >
                        <input name="emailId" type="text" id="E-mail ID" />
                    </td>
                </tr>
                <tr>
                    <td align="right"  style="white-space: nowrap">
                        <label>Country</label></td>
                    <td align="left"  >
                        <input name="country" type="text" id="Country" />
                    </td>
                    <td align="right" style="white-space: nowrap" >
                        <label>Size of Land Holding</label></td>
                   
                   <td align="left" >
                       <input name="sizeoflandholding" type="text" id="Size of Land Holding" />
                    </td>
                    <td align="right" style="white-space: nowrap" >
                        <label>Main Crops</label></td>

                   <td align="left" >
                       <input name="maincrops" type="text" id="Main Crops" />
                    </td>
                </tr>
                <tr>
                    <td align="right" style="white-space: nowrap" >
                        <label>Occupation</label></td>

                   <td align="left" >
                       <input name="occupation" type="text" id="Occupation" />
                    </td>
                </tr>
                <!--</form>-->
            </tbody>
        </table></td>
</tr>
</table>
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >
    <tr><td>
            <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1">
                <tr align="center" valign="middle" class="formHeader">
                    <!--<td ><label><a href="<%=cntxpath%>/installProcess.do?option=initInstallInfo&vinNo=3" class="formLabel">Installation Information</a></label></td>-->
                    <td class="formSelectedHeader"><label>Tractor Information</label></td>
                    <!--<td><label><a href="<%=cntxpath%>/installProcess.do?option=initStandardChecks4Install&vinNo=3" class="formLabel">Standard Checks</a></label></td>-->

                </tr>
            </table>
        </td></tr>


    <tr>
    <td id="travelcardData">
        <table  width="90%" border="0" align="center" cellpadding="3" cellspacing="5"  >
            <tbody>
                <tr>
			<td width="6%" align="center">S.NO</td>
                        <td width="33%">PART NO</td>
			<td width="16%">PART VENDOR NAME</td>
			<td width="50%">PART SERIAL NO</td>
		</tr>
		<c:set var="srNo" value="1"/>
		<c:forEach items="${travelcardPartList}" var="travelcardPartList">
                    <c:if test="${!empty travelcardPartList}">
			<tr>
                        <input type="hidden" name="actiontakens" value="${srNo}"/>
                        <td align="center">${srNo}</td>
                	<td align="left"  >
                            <input name="partNo${srNo}" type="text" id="Part No" style="width:230px !important" title="${travelcardPartList.contentDesc}" value="${travelcardPartList.contentDesc}"/>
               		 </td>
               		 
                   	 <td align="left"  >
                        <input name="partVendorName${srNo}" type="text" id="PartVendor Name" style="width:110px !important" title="${travelcardPartList.observation}" value="${travelcardPartList.observation}" />

                  	  </td>
                	
                	<td align="left"  >
                   	 <input name="partSerialNo${srNo}" type="text" id="PartSerial No" style="width:280px !important" title="${travelcardPartList.status}" value="${travelcardPartList.status}" />
                	</td>
               		</tr>
		    </c:if>
                  <c:set var="srNo" value="${srNo+1}"/>
                </c:forEach>
             <tr align="right">
                    <td  colspan="4" >
                        <input name="ListSize" id="ListSize" type="hidden" value="${srNo}"/>
                        <input name="input" type="button" value="Next" onclick="validateForm();"/></td>
                </tr>  
            </tbody>
        </table></td>
</tr>
</table>
</div>
    <div id="step2" style="display: none">
   <div class="breadcrumb_container">
    <ul>
        <li class="breadcrumb_link"><html:link action="/initService">SERVICE</html:link> </li>
        <li class="breadcrumb_link">Standard Checks</li>
    </ul>
</div>

<form action="<%=cntxpath%>/installProcess.do?option=manageInstall" method="post" id="StandardChecksForm">
<table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >
    <tr><td>
            <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1">
                <tr align="center" valign="middle" class="formHeader">
                    <!--<td ><label><a href="<%=cntxpath%>/installProcess.do?option=initInstallInfo&vinNo=3" class="formLabel">Installation Information</a></label></td>-->
                    <!--<td ><label><a href="<%=cntxpath%>/installProcess.do?option=initCustomerInformation&vinNo=3" class="formLabel">Customer Information</a></label></td>-->
                    <td class="formSelectedHeader"><label>Standard Checks</label></td>
<!--                    <td><label><a href="#" class="formLabel">Complaint</a></label></td>
                    <td><label><a href="#" class="formLabel">Estimate</a></label></td>
                    <td><label><a href="#" class="formLabel">Actuals</a></label></td>
                    <td><label><a href="#" class="formLabel">Status</a></label></td>-->
                </tr>
            </table>
        </td></tr>
    <div class="message1" id="msg_saveFAILED"></div>
    <tr><td>
            <table width="100%" cellpadding="2" cellspacing="4" class="LiteBorder">
                <tr class="formCatHeading">
                    <td width="4%" align="center"><label>S.No.</label></td>
                    <td align="center" width="90%"><label>Instructions</label></td>
                    <td width="13%"><label>Tick Here</label></td>
                   
                </tr>
                 <c:set var="count" value="1"/>
                <c:forEach items="${serviceForm.contentList}" var="contentList">

                    <c:choose>
                        <c:when test="${empty contentList}">

                            <tr>
                                <td width="3%" align="center">${count}</td>
                                <td ><label>${contentList.contentDesc}</label><input type="hidden" name="contentId" value="${contentList.contentId}"/></td>
                                <td>
                                    <input type="checkbox" name="${contentList.contentId}checkpoints"/>
                                </td>
                                <td ><input type="text" name="${contentList.contentId}observations" id="Observation" maxlength="100" style="width:200px !important "/></td>
                            </tr>
                        </c:when>
                        <c:otherwise>

                            <tr >
                                <td>${count}</td>
                                <td>
                                     ${contentList.contentDesc}<input type="hidden" name="contentId" value="${contentList.contentId}"/>
                                </td>
                                    <td>
                                    <input type="checkbox" name="${contentList.contentId}checkpoints"/>
                                </td>
<!--                                <td ><input type="text" name="${contentList.contentId}observations" value="${subCList.observations}" id="Observation" maxlength="100"  style="width:200px !important "/></td>-->
                            </tr>
                        </c:otherwise>

                    </c:choose>
                  <c:set var="count" value="${count+1}"/>
                </c:forEach>

<!--                <tr>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                    <td colspan="4" align="right">&nbsp; </td>
                    <td>&nbsp;</td>
                    <td align="right"><input name="input" type="button" value="Save" style="float:none; " onclick="submitForm();"/></td>
                </tr>-->
                
            </table>
              <!--</form>-->
                <table width="100%"  border="0" cellspacing="2">
                    
                    <tr>
                      
                        <td valign="top" width="50%"><table width="100%" border="0" align="center" cellpadding="2" cellspacing="4" class="LiteBorder"  >
                                <tbody>
                                    <tr class="formCatHeading">
                                        <td colspan="2">ACCESSORIES RECEIVED</td>
                                    </tr>
                            <tr>
                                   <td align="left" class="formLabel" style="white-space: nowrap"><label> Drawbar </label></td>
                               
                                <td align="left">
                                    <input type="checkbox" name="assrev1" value="Drawbar"/>
                                </td>
                               
                            </tr>
                                   <tr>
                                   <td align="left" class="formLabel" style="white-space: nowrap"><label> Bumber </label></td>
                               
                                <td align="left">
                                    <input type="checkbox" name="assrev2" value="Bumber"/>
                                </td>
                               
                            </tr>
                                  <tr>
                                   <td align="left" class="formLabel" style="white-space: nowrap"><label> Hook </label></td>
                               
                                <td align="left">
                                    <input type="checkbox" name="assrev3" value="Hook"/>
                                </td>
                               
                            </tr>
                            <tr>
                                   <td align="left" class="formLabel" style="white-space: nowrap"><label> Top Link </label></td>
                               
                                <td align="left">
                                    <input type="checkbox" name="assrev4" value="Top Link"/>
                                </td>
                               
                            </tr>
                            <tr>
                                   <td align="left" class="formLabel" style="white-space: nowrap"><label> Tool Kit </label></td>
                               
                                <td align="left">
                                    <input type="checkbox" name="assrev5" value="Tool Kit"/>
                                </td>
                               
                            </tr>
                            <tr>
                                   <td align="left" class="formLabel" style="white-space: nowrap"><label> Operator manual </label></td>
                               
                                <td align="left">
                                    <input type="checkbox" name="assrev6" value="Operator manual"/>
                                </td>
                               
                            </tr>
                            <tr>
                                   <td align="left" class="formLabel" style="white-space: nowrap"><label> Weights (Front & Rear wheel) if applicable </label></td>
                               
                                <td align="left">
                                    <input type="checkbox" name="assrev7" value="Weights (Front & Rear wheel) if applicable"/>
                                </td>
                               
                            </tr>
                                </tbody>
                            </table></td>
                            <td valign="top" width="50%"><table width="100%" border="0" align="center" cellpadding="2" cellspacing="4" class="LiteBorder"  >
                                <tbody>
                                    <tr class="formCatHeading">
                                        <td colspan="2"> MAJOR APPLICATION</td>
                                    </tr>
                                    <tr>
                                   <td align="left" class="formLabel" style="white-space: nowrap"><label> Cultivation </label></td>
                               
                                <td align="left">
                                    <input type="checkbox" name="majapp1" id="majapp1" value="Cultivation"/>
                                </td>
                               
                            </tr>
                                     <tr>
                                   <td align="left" class="formLabel" style="white-space: nowrap"><label> Rotavator </label></td>
                               
                                <td align="left">
                                    <input type="checkbox" name="majapp2" id="majapp2" value="Rotavator"/>
                                </td>
                               
                            </tr>
                                    <tr>
                                   <td align="left" class="formLabel" style="white-space: nowrap"><label> Haulage </label></td>
                               
                                <td align="left">
                                    <input type="checkbox" name="majapp3" id="majapp3" value="Haulage"/>
                                </td>
                               
                            </tr>
                            <tr>
                                   <td align="left" class="formLabel" style="white-space: nowrap"><label> Genset </label></td>
                               
                                <td align="left">
                                    <input type="checkbox" name="majapp4" id="majapp4" value="Genset"/>
                                </td>
                               
                            </tr>
                            <tr>
                                   <td align="left" class="formLabel" style="white-space: nowrap"><label> Load Dozer </label></td>
                               
                                <td align="left">
                                    <input type="checkbox" name="majapp5" id="majapp5" value="Load Dozer"/>
                                </td>
                               
                            </tr>
                            
                                </tbody>
                            </table></td>
                    </tr>
                    
                    <tr align="right"> 
                        <td  colspan="2" >
                        <input name="input2" type="button" value="Submit" onclick="submitStandarCheckForm();"/></td>
                    </tr>
                </table>
                                
        </td>
    </tr>
    
</table>
<!--    <table width="95%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >
    <tr><td>
            <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1">
                <tr align="center" valign="middle" class="formHeader">
                    <td ><label><a href="<%=cntxpath%>/installProcess.do?option=initInstallInfo&vinNo=3" class="formLabel">Installation Information</a></label></td>
                    <td class="formSelectedHeader"><label>DEALERSHIP DETAILS</label></td>
                    <td><label><a href="<%=cntxpath%>/installProcess.do?option=initStandardChecks4Install&vinNo=3" class="formLabel">Standard Checks</a></label></td>

                </tr>
            </table>
        </td></tr>


    <tr>
    <br>
    <br>
    <div class="message1" id="msg_saveFAILED">${message}</div>
    <td>
        <table  width="90%" border="0" align="center" cellpadding="3" cellspacing="3"  >
            <tbody>
            <form action="<%=cntxpath%>/serviceProcess.do?option=manageCustomerPayeeInformation" method="post" id="CustomerForm" >
                <tr>
                    <td align="left" class="formLabel" style="white-space: nowrap">Dealer Name</td>
                    <td colspan="3" align="left"  >
                     
                            <input name="payeeName" type="text" id="Dealer Name" style="width:350px !important" /></td>
                       

                </tr>
                <tr>
                <td align="left"  style="white-space: nowrap"> <label>Dealer Code</label></td>
                <td align="left"  >
                            <input name="dealerJobCardNo" type="text" id="Dealer Code" size="15" />
                </td>
                <td align="left" style="white-space: nowrap" >
                    <label>Address</label></td>
                <td align="left"  >
                            <input name="payeeVillage" type="text" id="Address" />
                </td>
                </tr>
                <tr>
                    <td align="left"  style="white-space: nowrap">
                        <label>Tehsil</label></td>
                    <td align="left"  >
                                <input name="payeeTehsil" type="text" id="Tehsil" />
                    </td>
                    <td align="left"  style="white-space: nowrap">
                        <label>District</label>
                    </td>
                    <td align="left"  >
                                <input name="payeeDistrict" type="text" id="District" />
                    </td>
                </tr>
                <tr>
                    <td align="left" class="formLabel" style="white-space: nowrap"><label>Pin Code</label></td>
                    <td align="left" >
                                <input name="payeePinCode" type="text" id="Pin Code" maxlength="6"/>
                    </td>
                    <td align="left"  style="white-space: nowrap"><label>Phone No.</label></td>
                    <td align="left"  >
                        <input name="payeeMobilePhone" type="text" id="Phone No." maxlength="10"/>
                    </td>
                </tr>
              
                   
                <tr align="right">
                    
                      <td  colspan="2" >
                          <input name="input1" type="button" value="<< BACK" onclick="goBack();"/>
                      </td>
               
                    <td  colspan="2" >
                        <input name="input2" type="button" value="Submit" onclick="submitStandarCheckForm();"/></td>
                </tr>
            </tbody>
        </table></td>
</tr>
</table>-->
</form>     
</div>
