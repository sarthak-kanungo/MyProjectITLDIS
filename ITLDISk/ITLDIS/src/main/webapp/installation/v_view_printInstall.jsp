<%--
    Document   : v_install_information
    Created on : May 26, 2014, 12:28:42 PM
    Author     : megha.pandya
--%>


<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ page import="java.util.*,java.text.SimpleDateFormat,java.io.File" %>

<c:set var="uplodINSPHotoPAth" value="${serviceform.uploadins}" scope="request" />
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
Vector userFunctionalities = (Vector) session.getAttribute("userFun");
            Calendar cal = Calendar.getInstance();
            cal.setTime(new Date());
            int month = cal.get(Calendar.MONTH) + 1;
            int year = cal.get(Calendar.YEAR);
            int day = cal.get(Calendar.DATE);

             String value = (String) pageContext.getRequest().getAttribute("uplodINSPHotoPAth");
             System.out.println("  value is >> "+value);


%>

<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest_5.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/commonValidations.js"></script>
<script src="${pageContext.request.contextPath}/js/base64.js"></script>
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
    function changeImage()
    {
       // alert();
        var filePath= document.getElementById("UploadImage").value;
        if(filePath == '')
        {
            alert('Please Browse Image.');
            return false;
        }
        
             if(filePath != ""){
                 filePath = filePath.toLowerCase();
                 if(filePath.indexOf(".jpeg")!=-1){
                    filePath = (filePath.substr(0, filePath.indexOf(".jpeg"))+".jpg");
                 }



                <%--if((filePath.indexOf(':\\') ==-1 || filePath.indexOf(':\\')==0))
                {
                    alert(invalidbrowsefile_validation_msg);
                    return false;
                }--%>
                else if(filePath.indexOf(".jpg") != (filePath.length-4))
                {
                    alert(onlyjpgbrowsefile_validation_msg);
                    return false;
                }
            }
        

    // document.form1.action="<%=cntxpath%>/installProcess.do?option=updateInsImage&insNo="+insNo;
    // alert(" insNo> "+insNo);
     document.form1.submit();
       // document.getElementById("step1").style.display='';
    }
      function checkValue(strObject, strValue)
    {
        var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['&\@\!\~\`\$\%\=\{\}\|\:\;\<\>\?\+\"\^]/g;
        var objSpecExpDes = /['*\@\!\~\`\$\%\=\|\:\;\<\>\?\+\"\^]/g;
        var ck_code = /^[a-zA-Z]+$/;
        strValue = strValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(strValue);
        var tempD= objSpecExpDes.exec(strValue);

        if (strValue.length == 0)
        {
            strObject.focus();
            document.getElementById("msg_saveFAILED").style.display = "";
            if (strObject.tagName == "INPUT")
            {
                document.getElementById("msg_saveFAILED").innerHTML = "Please Enter Value in " + strObject.id + " Field";
            } else {
                document.getElementById("msg_saveFAILED").innerHTML = "Please Select Value in " + strObject.id + " Field";
            }
            window.scrollTo(0, 0);//go to top of page
            return false;

        }

}
    function submitStandarCheckForm()
    {
        /*var flag=0;
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
         */


        var x="false";
        var r=confirm("Are You want To Submit The Installation?");
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

    function validateForm()
    {
        

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










function openImage(imageName)
        {
           
            var theURL=imageName;//"/viewSvgimage.jsp?imageName="+imageName;
            var winName="Uploaded_image"
            var features="scrollbars=yes,width=550,height=550";
            var isValidPopUpBlocker=detectPopupBlocker()
            if (isValidPopUpBlocker== false)
            {
                return
            }

            var win=window.open(theURL,winName,features);
            win.focus();
        }
        function detectPopupBlocker()
        {
            var myTest = window.open("about:blank","","directories=no,height=100,width=100,menubar=no,resizable=no,scrollbars=no,status=no,titlebar=no,top=0,location=no");
          
            if (!myTest)
            {
                alert("Your Pop-up Blocker is Enabled. Please Add our site  to your trusted sites.");
                parent.location.href = 'Logout'
                return false;
            }
            else
            {
                myTest.close();
                return true;
            }
        }
  function printINS()
   {
       var vinNo=Base64.encode('${serviceform.vinNo}');
      var insNo=Base64.encode('${serviceform.insNo}');
      var dealerCode=Base64.encode('${serviceform.dealercode}');
      var strURL = "${pageContext.request.contextPath}/installProcess.do?option=initInstallInfoPrint&vinNo="+vinNo+"&insNo="+insNo+"&dealerCode="+dealerCode+"&printINS="+Base64.encode('true');
      window.open(strURL,'iji','width=1000,height=950, resizable=yes,scrollbars=yes, status=0,titlebar=0,toolbar=0, addressbar=0');
   }
</script>


<%--<form action="<%=cntxpath%>/installProcess.do?option=manageInstall" method="post" id="StandardChecksForm" enctype="multipart/form-data">--%>
    <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/installProcess.do?option=initViewAllInstallList'><bean:message key="label.common.Service" /></a>@@<bean:message key="label.common.message" />"/>
    <div id="step1">
        <div class="breadcrumb_container">
            <ul>

                <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link> </li>
                <li class="breadcrumb_link"><a href='<%=cntxpath%>/installProcess.do?option=init_viewDetails'><bean:message key="label.common.viewinstallation" /></a>${serviceform.insNo}</li>
            </ul>
        </div>
        <div class="message" id="msg_saveFAILED">${message}</div>
        <center>
            <div class="content_right1">
                <div class="con_slidediv2" style="position: relative;width: 100%">
                    <h1><label><b><bean:message key="label.common.installationforChassisno" /></b> ${serviceform.vinNo}</label></h1>

                    <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" class="LiteBorder" >


                        <tr><td>
                                <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="0" >

                                    <tr bgcolor="#eeeeee" height="20">
                                        <td  class="headingSpas" align="left" ><label><B><bean:message key="label.common.vehicleinfo" /></B></label></td>
                                        <td align="right" style="padding-right:7px ">
                                            <%--<input type="button" name="print" id="Print" value="Print" onclick="printINS();"/>--%>
                                             <a href="#"><img src='<%=cntxpath%>/image/print-icon.png' alt='Get Suggestions' name="img1" border='0' title="print"  onclick="printINS();"/></a>
                                        </td>
                                    </tr>
                                </table>
                            </td></tr>

                        <tr><td><%--<form action="<%=cntxpath%>/installProcess.do?option=manageInstall" method="post" id="StandardChecksForm" enctype="multipart/form-data">--%>
                                <!--<form action="<%=cntxpath%>/installProcess.do?option=initStandardChecks4Install" method="post" id="installationform" >-->
                                <table width="100%" border="0" align="center" cellpadding="1" cellspacing="5"  >

                                    <tr>
                                        <td width="15%" align="right"><span class="headingSpas">
                                                <label><bean:message key="label.common.Chassisno" />:</label></span></td>
                                                <td width="15%" align="left"  ><span>${serviceform.vinNo}</span>
                                           <%-- <input name="vinNo" type="text" id="VIN No." maxlength="50" value="${serviceform.vinNo}" readonly  style="background-color:#E6E4E4;"/>--%>
                                            <input name="vinid" type="hidden"  maxlength="50" value="${serviceform.vinid}" readonly />
                                        </td>
                                        <td align="right"><span class="headingSpas">
                                                <label><bean:message key="label.common.engineno" />:</label>
                                            </span></td>
                                            <td align="left" ><span>${serviceform.engineNumber}</span>
                                           <%-- <input name="engineNumber" type="text" id="Engine Number" value="${serviceform.engineNumber}" readonly="true" style="background-color:#E6E4E4;"/>--%>
                                        </td>

                                        <td align="right"><span class="headingSpas">
                                                <label><bean:message key="label.common.Modelfamily" />:</label>
                                            </span></td>
                                            <td align="left" ><span>${serviceform.modelFamily}</span>
                                           <%-- <input name="modelfamily" type="text" id="Model Family" value="${serviceform.modelFamily}" readonly="true" style="background-color:#E6E4E4;"/>--%>
                                        </td>

                                    </tr>
                                    <tr>

                                        <td align="right"><span class="headingSpas">
                                                <label><bean:message key="label.common.dealerinvoiceno" />:</label>
                                            </span></td>
                                            <td align="left" > <span>${serviceform.dealerinvoiceno}</span>
                                             <%-- <input name="dealerinvoiceno" type="text" id="Dealer Invoice No" value="${serviceform.dealerinvoiceno}" readonly="true" style="background-color:#E6E4E4;"/> --%>
                                        </td>

                                        <td align="right" style="" ><span class="headingSpas">
                                                <label><bean:message key="label.common.dealerinvoicedate" />:</label>
                                            </span></td>
                                            <td align="left" id="saledatetd"><span>${serviceform.saleDate}</span>
                                            <%--<input name="saleDate" type="text" class="datepicker" id="Sale Date" value="${serviceform.saleDate}" disabled="disabled" style="background-color:#E6E4E4;"/>--%>
                                        </td>
                                        <td align="right"><span class="headingSpas">
                                                <label><bean:message key="label.common.installationnoanddate" />:</label>
                                            </span>
                                        </td>
                                        <td align="left" style="white-space: nowrap"><span>${serviceform.insNo}&nbsp; [${serviceform.insDate}]</span>
                                            <%--<input name="delieveryDate" type="text" class="datepicker" id="Delivery Date" readonly size="15" value="${serviceform.delieveryDate}" />--%>
                                        </td>
                                    </tr>


                                    <tr>

                                        <td width="14%" align="right" style="" ><div align="right"> <span class="headingSpas">
                                                    <label><bean:message key="label.common.installer" />&nbsp; <bean:message key="label.common.name" />:</label>
                                                </span></div></td>
                                                <td width="12%" align="left"><span>${serviceform.repname} </span>
                                                  <%--  <c:forEach items='${mechanicList}'  var='labelValue'  varStatus='status'>
                                                    ${labelValue.label}
                                                </c:forEach>--%>
                                            <%--<select name="repname" id="Representative Name" style="width:165px !important">
                                                <option value="">--select--</option>
                                                <c:forEach items='${mechanicList}'  var='labelValue'  varStatus='status'>
                                                    <option value='${labelValue.value}' title='${labelValue.label}'>${labelValue.label}</option>
                                                </c:forEach>
                                            </select>--%>
                                        </td>
                                        <td align="right"><span class="headingSpas">
                                                <label><bean:message key="label.common.visit" />&nbsp;<bean:message key="label.common.date" />: </label>
                                            </span></td>
                                            <td align="left" style="white-space: nowrap"><span>${serviceform.visitDate}</span>
                                            <%--<input name="jobCardDate" type="text" class="datepicker" id="Visit Date" size="15" readonly value="${serviceform.jobCardDate}" />--%>

                                        </td>



                                        <td align="left" style="" id="HMRLABEL"><div align="right"> <span class="headingSpas">
                                                    <label><bean:message key="label.common.hrm" />:</label>
                                                </span></div></td>
                                                <td align="left" id="HMRVALUE"><span>${serviceform.hmr}</span>
                                            <%--<input name="hmr" type="text" id="HMR"  value="${serviceform.hmr}" maxlength="7" />--%>
                                        </td>



                                    </tr>
                                    <tr>


                                        <td align="right"><span class="headingSpas">
                                                <label><bean:message key="label.common.Registrationno" /> : </label>
                                            </span></td>
                                            <td align="left" ><span>${serviceform.registrationNo}</span>
                                           <%-- <input name="registrationNo" type="text" id="Registration No."  value="${serviceform.registrationNo}" readonly="true" style="background-color:#E6E4E4;"/>--%>
                                        </td>
                                        <td align="right" style="white-space: nowrap"><span class="headingSpas">
                                                <label><bean:message key="label.common.Servicebookletno" />:</label>
                                            </span>
                                        </td>
                                        <td align="left" ><span>${serviceform.serviceBookletNo}</span>
                                           <%-- <input name="serviceBookletNo" type="text" id="Service Booklet No."  maxlength="40" value="${serviceform.serviceBookletNo}" />--%>
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
                                            <td   align="right" width="15%" class="headingSpas" style="white-space: nowrap"><bean:message key="label.common.CustomerNameCaps" />:</td>
                                            <td  align="left" width="15%"><span>${serviceform.customerName}</span>
                                            </td>
                                            <td  align="right" width="15%" class="headingSpas" style="white-space: nowrap" >
                                                <label> <bean:message key="label.common.mobilephone" />:</label></td>
                                            <td   align="left" width="15%" ><span>${serviceform.mobilePhone}</span>
                                            </td>
                                            <td   align="right" rowspan="8" class="headingSpas" style="white-space: nowrap" colspan="2" >

                                                  <%
                                                              // String path= request.getAttribute("insPhotoPath")==null?"":request.getAttribute("insPhotoPath").toString();
                                                              String s1 = getServletContext().getRealPath("/") + "/DOCUMENTS/INSTALLATION/" + value;
                                                              File f1 = new File(s1);
                                                              if (f1.exists()) {
                                                  %>

                                                  <img src="<%=cntxpath%>/DOCUMENTS/INSTALLATION/${serviceform.uploadins}" alt="admin" height="250" width="250"/>
                                                  <% }else{%>
                                                  <img src="<%=cntxpath%>/images/image-not-found1.gif" height="250" width="250" alt="" />
                                                  <% }%>

                                            </td>
                                        </tr>
                                        <tr>
                                            <td   align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label> <bean:message key="label.common.talukatahsil" />:</label></td>

                                            <td  align="left" ><span>${serviceform.tehsil}</span>
                                            </td>
                                            <td  align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label> <bean:message key="label.common.district" />: </label></td>
                                            <td   align="left" ><span>${serviceform.district}</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td   align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label>  <bean:message key="label.common.village" />: </label></td>
                                            <td   align="left" ><span>${serviceform.village}</span>
                                            </td>
                                            <td   align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label> <bean:message key="label.common.pincode" />: </label></td>
                                            <td   align="left" ><span>${serviceform.pinCode}</span></td>
                                        </tr>
                                        <tr>
                                            <td   align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label> <bean:message key="label.common.state" />: </label></td>
                                            <td  align="left" ><span>${serviceform.state}</span>
                                            </td>
                                            <td   align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label>  <bean:message key="label.common.sizeoflandhold" />: </label></td>

                                            <td   align="left" ><span>${serviceform.sizeoflandhold}</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label> <bean:message key="label.common.occupation" />: </label></td>
                                            <td align="left" ><span>${serviceform.occupation}</span>
                                            </td>
                                            <td align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label> <bean:message key="label.common.nooffamilymembers" />: </label></td>

                                            <td align="left" ><span>${serviceform.familyMembers}</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label> <bean:message key="label.common.nameofbank" />: </label>
                                            </td>
                                            <td align="left" ><span>${serviceform.bankName}</span>
                                            </td>
                                            <td align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label>   <bean:message key="label.common.modeofpayment" />: </label>
                                            </td>
                                            <td align="left" ><span>${serviceform.paymentMode}</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td  align="right"  class="headingSpas" style="white-space: nowrap" >
                                                <label><bean:message key="label.common.maincrop" />: </label></td>
                                            <td  align="left" ><span>${serviceform.maincrops}</span>
                                            </td>
                                            <td align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label>  <bean:message key="label.common.relationwithcustomer" />: </label></td>
                                            <td align="left" >
                                                <span>${serviceform.relationshipwithcust}</span>
                                            </td>
                                        </tr>
                                        <tr>
                                            <td align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label> <bean:message key="label.common.contactname" />: </label></td>
                                            <td align="left" >
                                                <span>${serviceform.contactname}</span>
                                            </td>
                                            <td align="right" class="headingSpas" style="white-space: nowrap" >
                                                <label> <bean:message key="label.common.contactno" />: </label></td>
                                            <td align="left" >
                                                <span>${serviceform.contactno}</span>
                                            </td>
                                        </tr>
                                        <%if (userFunctionalities.contains("606")) {%>
                                        <tr>
                                            <td align="right" class="headingSpas" style="white-space: nowrap" colspan="1">
                                                <label><bean:message key="label.common.otherTractorDetail" />: </label></td>
                                            <td align="left" colspan="3"  ><span>${serviceform.othertractorDetail}</span>
                                            </td>
                                           
                                            <td align="right" colspan="2" class="headingSpas" style="white-space: nowrap" colspan="1">
                                                <label style="float:left;"><%--<bean:message key="label.common.otherTractorDetail" />:--%> Change Image</label>

                                             <form action="<%=cntxpath%>/installProcess.do?option=updateInsImage" name="form1" id="form1" method="post" onsubmit="return false;" ENCTYPE="multipart/form-data">
                                                <input type="file" name="uploadphoto" id="UploadImage" style="width: 220px; float:left; margin:0 0 0 15px;">
                                                <input type="hidden" name="insNo" value="${serviceform.insNo}">
                                            </td>
                                            
                                        </tr>
                                        <tr>
                                            <td colspan="6" align="right"> <input type="button" name="Update"  value="Update" onclick="changeImage()"></td>
                                        </tr>
                                         </form>
                                        <tr height="4">
                                            <td align="right" colspan="6" height="4" style="padding-right: 10px"><label style="color: red;font-size:10px " ><B>( <bean:message key="label.common.maxfilesize" /> )</B></label>
                                            </td>
                                        </tr>

                                        <%}%>










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
                                            <td width="10%" align="center" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><bean:message key="label.common.sno" /></B></td>
                                            <td width="30%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><bean:message key="label.common.partname" /></B></td>
                                            <td width="30%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><bean:message key="label.common.partvendorname" /></B></td>
                                            <td width="20%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><bean:message key="label.common.partSerialNo" /> </B></td>
                                        </tr>
                                        <c:set var="srNo" value="1"/>
                                        <c:set var="tsrNo" value="5"/>
                                        <c:set var="totallist" value="0"/>
                                        <c:forEach items="${travelcardPartList}" var="travelcardPartList">
                                            <c:if test="${!empty travelcardPartList}">
                                                <tr bgcolor="#FFFFFF" height="20">
                                            <input type="hidden" name="actiontakens"  value="${srNo}"/>
                                            <td align="center">${srNo}</td>
                                            <td align="left" nowrap  style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;padding-top: 2px;padding-bottom: 2px" ><span>${travelcardPartList.contentDesc}</span>
                                              <%--  <input name="partNo${srNo}" type="text" id="Part No${srNo}" style="width:300px ; background-color:#E6E4E4;" title="${travelcardPartList.contentDesc}" value="${travelcardPartList.contentDesc}" readonly="true" maxlength="20"/>--%>
                                            </td>

                                            <td align="left" nowrap  style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;padding-top: 2px;padding-bottom: 2px" ><span>${travelcardPartList.observation}</span>
                                               <%-- <input name="partVendorName${srNo}" type="text" id="PartVendor Name${srNo}" style="width:300px !important" title="" value="${travelcardPartList.observation}" maxlength="40"/>--%>

                                            </td>

                                            <td align="left"  nowrap  style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;padding-top: 2px;padding-bottom: 2px"><span>${travelcardPartList.serialno}</span>
                                               <%-- <input name="partSerialNo${srNo}" type="text" id="PartSerial No${srNo}" style="width:250px !important" title="" value="${travelcardPartList.serialno}" maxlength="40"/>--%>
                                            </td>
                                            </tr>
                                         <c:set var="srNo" value="${srNo+1}"/>
                                         <c:set var="totallist" value="${totallist+1}"/>
                            </c:if>

                        </c:forEach>
                        </tbody>

                        

                      <%--  <tr bgcolor="#FFFFFF" align="center" height="40">
                            <td  colspan="4" >
                                <input name="ListSize" id="ListSize" type="hidden" value="${srNo}"/>
                                <input name="input" type="button" value="Next" onclick="return validateForm();"/>
                            </td>
                        </tr>--%>

                    </table></td>

                  


    <div id="step2" style="display: none">
        <div class="breadcrumb_container">
            <ul>
                <li class="breadcrumb_link"><html:link action="/initService"><bean:message key="label.common.Service" /></html:link> </li>
                <li class="breadcrumb_link"><bean:message key="label.common.installationprint" /></li>
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
                                    <tr bgcolor="#FFFFFF" height="20"  class="headingSpas">
                                        <td width="10%" align="center"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><%--<bean:message key="label.common.sno" />--%>#</label></B></td>
                                        <td align="left" width="80%"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><bean:message key="label.common.instructions" /></label></B></td>
                                      <%--  <td width="10%"  style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><bean:message key="label.common.tickhere" /></label></B></td>--%>

                                    </tr>
                                    <c:set var="count" value="1"/>
                                    <c:forEach items="${serviceForm.contentList}" var="contentList">

                                        <c:choose>
                                            <c:when test="${empty contentList}">

                                                <tr bgcolor="#FFFFFF" height="20">
                                                    <td width="3%" align="center">${rcount}</td>
                                                    <td ><label>${contentList.contenvicetDesc}</label><input type="hidden" name="contentId" value="${contentList.contentId}"/></td>
                                                    <td align="center">
                                                        <input type="checkbox" name="${contentList.contentId}checkpoints"/>
                                                    </td>
                                                    <td ><input type="text" name="${contentList.contentId}observations" id="Observation" maxlength="100" style="width:200px !important "/></td>
                                                </tr>
                                            </c:when>
                                            <c:otherwise>

                                                <tr bgcolor="#FFFFFF" height="20" >
                                                    <td width="3%" align="center"><%--${count} ${contentList.status}--%>
                                                        <c:if test="${contentList.status eq 'Y'}">
                                                            <img alt="" src="${pageContext.request.contextPath}/images/success.gif" style="width: 15px;height:15px"/>
                                                        </c:if>
                                                        <c:if test="${contentList.status eq 'N'}">
                                                           <img alt="" src="${pageContext.request.contextPath}/images/failure.gif" style="width: 15px;height: 15px"/>
                                                        </c:if>

                                                    </td>
                                                    <td align="left" style="padding-left:3px;padding-right:3px;">
                                                        ${contentList.contentDesc}<input type="hidden" name="contentId" value="${contentList.contentId}"/>
                                                    </td>
                                                   <%-- <td align="center">
                                                        <input type="checkbox" name="${contentList.contentId}checkpoints"/>
                                                    </td>--%>
                    <!--                                <td ><input type="text" name="${contentList.contentId}observations" value="${subCList.observations}" id="Observation" maxlength="100"  style="width:200px !important "/></td>-->
                                                </tr>
                                            </c:otherwise>

                                        </c:choose>
                                        <c:set var="count" value="${count+1}"/>
                                    </c:forEach>

                                </table>
                                <!--</form>-->
                                <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc>

                                    <tr bgcolor="#eeeeee" height="20">

                                        <td valign="top" width="50%"><table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc >
                                                <tbody>
                                                    <tr bgcolor="#eeeeee" height="25">
                                                        <td class="headingSpas" align="left" colspan="2" style="padding-left:5px"><B><bean:message key="label.common.accessoriesrecieved" /></B></td>
                                                    </tr>
                                                    <c:set  var="sNo" value="1"></c:set>


                                                    <c:set var="temp" value="${fn:split(serviceform.accessories, ',')}"/>
                                                    <c:forEach var="i" begin="0" end="${fn:length(temp)-1}">
 
                                                        <input type="hidden" name="accessories" value="${i}"/>
                                                        <tr bgcolor="#FFFFFF" height="20" >
                                                            <td align="left" class="headingSpas" style="padding-left:5px;white-space: nowrap" ><label> ${temp[i]} </label></td>
                                                           <%-- <td align="center" >
                                                                <input type="checkbox" name="assrev${i}" id="assrev${i}" value="${temp[i]}"/>
                                                            </td>--%>
                                                        </tr>
                                                    
                                                    <c:set var="sNo" value="${sNo+1}"/>
                                                </c:forEach>
                                           
                                                </tbody>
                                            </table>
                                        </td>
                                        <td valign="top" width="50%">
                                            <table width=100%  border=0 cellspacing=1 cellpadding=1 bordercolor=#cccccc bgcolor=#cccccc  >
                                                <tbody>
                                                    <tr bgcolor="#eeeeee" height="25">
                                                        <td colspan="2" class="headingSpas" align="left" style="padding-left:5px"><B> <bean:message key="label.common.major" /> &nbsp;<bean:message key="label.common.application" /></B></td>
                                                    </tr>
                                                    <c:set  var="sNo" value="1"></c:set>
                                                  <c:set var="temp" value="${fn:split(serviceform.majorApplications, ',')}"/>
                                                    <c:forEach var="i" begin="0" end="${fn:length(temp)-1}">
                                                         <input type="hidden" name="majorApp" value="${i}"/>
                                                        <tr bgcolor="#FFFFFF" height="20" >
                                                            <td align="left" class="headingSpas" style="padding-left:5px;white-space: nowrap"><label> ${temp[i]} </label></td>
                                                           <%-- <td align="center">
                                                                <input type="checkbox" name="majapp${i}" id="majapp${i}" value="${temp[i]}"/>
                                                            </td>--%>
                                                        </tr>
                                                    
                                                    <c:set var="sNo" value="${sNo+1}"/>
                                                </c:forEach>
                                                </tbody>
                                            </table></td>
                                    </tr>

                                    <tr>
                                    <table width="100%"><tr>
                                            <td align="left" width="10%"><span class="headingSpas">
                                                    <label><B><bean:message key="label.common.anyobservation" /></B></label>
                                                </span></td>
                                                <td align="left"  width="80%"><span>${serviceform.remarks}</span>
                                               <%-- <textarea name="remarks" rows="4" id="Any Observation"  onblur="if(!CommentsMaxLength(this, 450)){this.value=this.value.substring(0, 450);}">${serviceform.compVerbatim[count]}</textarea>--%>
                                            </td>
                                        </tr>

                                    </table>
                                     </tr>


                             <c:if test="${!empty enquiriesList}">

                                     <tr><td>
                                <table width="100%"  border="0" align="center" cellpadding="5" cellspacing="1" bordercolor=#cccccc bgcolor=#cccccc>
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
                                            <td width="10%" align="center" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><bean:message key="label.common.sno" /></B></td>
                                            <td width="20%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><bean:message key="label.common.CustomerName" /></label></B></td>
                                            <td width="20%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><bean:message key="label.common.fathername" /></label></B></td>
                                            <td width="20%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><bean:message key="label.common.village" /> </label></B></td>
                                            <td width="15%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><bean:message key="label.common.contactno" /> </label></B></td>
                                            <td width="15%" align="left" style="padding-left:3px;FONT-SIZE: 11px;font-weight:bold;padding-right:3px;"><B><label><bean:message key="label.common.relationwithcustomer" /></label> </B></td>
                                        </tr>
                                        <c:set var="srNo" value="1"/>
                                        <c:set var="tsrNo" value="5"/>
                                        <c:set var="totallist" value="0"/>





                                <c:forEach items="${enquiriesList}" var="enquiriesList">
                                  
                                 <tr bgcolor="#FFFFFF" height="20">
                                <td align="center" width="10%">${srNo}</td>
                                <td align="left" nowrap width="20%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;padding-top: 2px;padding-bottom: 2px" >
                                   <span>${enquiriesList.custName}</span>
                                </td>

                                <td align="left" nowrap width="20%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;padding-top: 2px;padding-bottom: 2px" >
                                   <span>${enquiriesList.fatherName}</span>
                                </td>
                              
                                <td align="left"  nowrap width="20%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;padding-top: 2px;padding-bottom: 2px">
                                    <span>${enquiriesList.villagename}</span>
                                </td>
                                 <td align="left"  nowrap width="15%" style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;padding-top: 2px;padding-bottom: 2px">
                                   <span>${enquiriesList.mobilePh}</span>
                                </td>
                                <td align="left"  nowrap width="15%"  style="padding-left:3px;FONT-SIZE: 11px;padding-right:3px;padding-top: 2px;padding-bottom: 2px">
                                      <span>${enquiriesList.relationwithcustomer}</span>
                                </td>
                                </tr>
                                <c:set var="srNo" value="${srNo+1}"/>
                                <c:set var="totallist" value="${totallist+1}"/>
                                
                            </c:forEach>




                        </tbody>



                    </table></td>
                    </tr>
</c:if>

                        <tr bgcolor="#FFFFFF" height="40" align="center">

                            <td colspan="2">
                              <%--  <input name="input1" type="button" value="<< BACK" onclick="goBack();"/> &nbsp;&nbsp;&nbsp;--%>
                                <%--<input name="input2" type="button" value="Submit" onclick="submitStandarCheckForm();"/>--%>
                            </td>
                        </tr>


                    </table>



                    </td>
                    </tr>
  </table>
                </div>
            </div>
        </center>
    </div>
                    </table>

                </div>
            </div>

        </center>
    </div>

<%--</form>--%>