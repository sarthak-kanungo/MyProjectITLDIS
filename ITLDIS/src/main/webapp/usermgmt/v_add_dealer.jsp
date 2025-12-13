<%-- 
    Document   : v_add_dealer
    Created on : Dec 25, 2015, 11:15:29 AM
    Author     : Ashutosh.Kumar1
--%>



<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>

<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
    var GB_ROOT_DIR = "${pageContext.request.contextPath}/greybox/";
    var SERVLET_ROOT_DIR = "${pageContext.request.contextPath}/";



    function checkValueSelect(disObject, divId)
    {
        if (disObject.value == 'Other')
        {
            document.getElementById(divId).style.display = "block";
        }
        else
        {
            document.getElementById(divId).style.display = "none";
        }
    }
    function validateForm()
    {

        var elementArr = new Array( 'Dealer Code','Dealer Name', 'Location Code','Location','Address', 'Mobile','Country');     <%--,'District','State','TinNo'--%>
        var strValue = null;
        var strObject = null;

        for (var i = 0; i < elementArr.length; i++)
        {
            strObject = document.getElementById(elementArr[i]);

            if (strObject)
            {
                strValue = document.getElementById(elementArr[i]).value;

                if (checkValue(strObject, strValue) == false)
                    return false;
            }
        }

        //        document.getElementById('submitBtn').disabled = true;
        document.getElementById('form').submit();
    }

    function checkValue(strObject, strValue)
    {
        var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['&\\@]/g;
        var emailExp = /\S+@\S+\.\S+/;
        var characteronlyExp = /^[a-z .A-Z]+$/;
        var characteronlyWithoutSpaceExp = /^[a-zA-Z0-9]+$/;
        var mobileExp = /^[\(\)\s\-\+\d]{10,17}$/;
        var objectId = strObject.id;

        strValue = strValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(strValue);

        if (strValue.length == 0)
        {
            if (strObject.tagName == "SELECT")
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Please select value in " + objectId + " field.";
                //alert("Please select value in " + objectId + " field.");
                return false;
            }
            else
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Please enter value in " + objectId + " field.";
                //alert("Please enter value in " + objectId + " field.");
                return false;
            }
            return false;
        }
        else if (objectId == "User ID")//|| objectId == "Customer Name" || objectId == "Customer Contact Person"
        {

            if (!characteronlyWithoutSpaceExp.test(strValue))
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Space and Special Characters are not allowed in " + objectId + " field .";
                //alert("Space and Special Characters are not allowed in " + objectId + " field .");
                return false;
            }
        }
        else if (objectId == "Dealer Code")//|| objectId == "Customer Name" || objectId == "Customer Contact Person"
        {

            if (!characteronlyWithoutSpaceExp.test(strValue))
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Space and Special Characters are not allowed in " + objectId + " field .";
                //alert("Space and Special Characters are not allowed in " + objectId + " field .");
                return false;
            }
        }
        else if (objectId == "Location Code")//|| objectId == "Customer Name" || objectId == "Customer Contact Person"
        {

            if (!characteronlyWithoutSpaceExp.test(strValue))
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Space and Special Characters are not allowed in " + objectId + " field .";
                //alert("Space and Special Characters are not allowed in " + objectId + " field .");
                return false;
            }
        }

    
        else if (objectId == "Phone No" || objectId == "Mobile" || objectId == "Pin Code")
        {
            if (mobileExp.test(strValue) == false )
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML=""+objectId + " field must containts only numeric value, '+' and '-'.";
                return false;
            }
        }
        else if (objectId == "Re-Type Password")//'', ''
        {
            if (document.getElementById('Password').value != document.getElementById('Re-Type Password').value)
            {
                document.getElementById("msg_saveFAILED").innerHTML="Re-Type Password must match with Password.";
                //alert('Re-Type Password must match with Password.');
                return false;
            }
        }
        else if (temp && objectId != "Email")
        {
            if (strObject.tagName == "INPUT")
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Special Characters are not allowed in " + objectId + " field.";
                //alert("Special Characters are not allowed in " + objectId + " field.");
                return false;
            }
        }
        else if (objectId == "Email")
        {
            if (!emailExp.test(strValue))
            {
                document.getElementById("msg_saveFAILED").innerHTML="Please enter valid Email.";
                //alert("Please enter valid Email.");
                strObject.focus();
                return false;
            }
        }
        else if (objectId == "Country"){
            if(document.getElementById("Country").value=="0"){
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Please enter value in " + objectId + " field.";
                return false;
            }
        }
    }

    function openIframePopup(pageURL, callBackUrl)
    {
        decoGreyboxLinks(pageURL, callBackUrl);
    }
    function cancel(){

        document.getElementById("form").action="<%=cntxpath%>/UserManagement.do?option=initUser";
        document.getElementById("form").submit();
    }
</script>

<link href="${pageContext.request.contextPath}/greybox/gb_styles.css" rel="stylesheet" type="text/css" media="all" />


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href = '<%=cntxpath%>/UserManagement.do?option=initUserInformation'>USER MANAGEMENT</a></li>
            <%-- <li class="breadcrumb_link"><a href = '<%=cntxpath%>/ManageUser.do' >USER MANAGEMENT CONSOLE</a></li>--%>
            <li class="breadcrumb_link"><a href = '<%=cntxpath%>/UserManagement.do?option=initDealer'> MANAGE DEALER</a></li>
            <li class="breadcrumb_link">ADD NEW DEALER</li>

        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED"></div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1>ADD NEW DEALER</h1>


                <form action="UserManagement.do?option=insertDealer" method="POST" id="form" >
                    <%--<input type="hidden" name="option" value="insertDealer"/>--%>
                    <input type="hidden" name="upperLink" value="<a href = '<%=cntxpath%>/UserManagement.do?option=initUserInformation'>USER MANAGEMENT</a> <a href = '<%=cntxpath%>/UserManagement.do?option=initDealer'> MANAGE DEALER</a>@@ADD NEW DEALER"/>
                    <table width=100% border="0" cellpadding="5" cellspacing="5" >

                        <tr>
                            <td valign="top"  bgcolor="#FFFFFF" >
                                <table width="100%" border="0" cellpadding="4" cellspacing="0" >

                                    
                                    <tr height="20">
                                        <td  width="50%" height="15" style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Dealer Code<span class="mandatory">*</span></td>
                                        <td  width="50%" height="15" style="padding-left:10px" colspan="2" bgcolor="#FFFFFF" align="left">
                                            &nbsp;&nbsp;<input type="text" id="Dealer Code" name="dealerCode" class="headingSpas"  style="width:170px;margin:0px!important" />
                                        </td>
                                    </tr>

                                    <tr height="20">
                                        <td  style="padding-left:350px" height="15" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Dealer Name <span class="mandatory">*</span></td>
                                        <td style="padding-left:10px;margin:0px!important" height="15" colspan="2" bgcolor="#FFFFFF" align="left">
                                            &nbsp;&nbsp;<input type="text" name="dealerName" maxlength="15" id="Dealer Name"  class="headingSpas"  style="width:170px;margin:0px!important" />
                                        </td>
                                    </tr>
                                    <tr height="20">
                                        <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Location Code <span class="mandatory">*</span></td>
                                        <td style="padding-left:10px" bgcolor="#FFFFFF" colspan="2" align="left">
                                            <input type="text" name="locationCode"  id="Location Code" class="headingSpas"  maxlength="5" style="width:170px" />
                                        </td>
                                    </tr>
                                    <tr height="20">
                                        <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Location <span class="mandatory">*</span></td>
                                        <td style="padding-left:10px" bgcolor="#FFFFFF" colspan="2" align="left">
                                            <input type="text" name="location"  id="Location" class="headingSpas"  maxlength="50" style="width:170px" />
                                        </td>
                                    </tr>
                                    <%--<tr height="20">
                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Designation <span class="mandatory">*</span></td>
                                        <td style="padding-left:10px" colspan="2" bgcolor="#FFFFFF" align="left">
                                            <input type="text" name="designation"  value="${userForm.designation}" id="Designation" maxlength="50" class="headingSpas"  style="width:170px" />
                                        </td>
                                    </tr>--%>

                                    <tr height="20">
                                        <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Address <span class="mandatory">*</span></td>
                                        <td style="padding-left:10px" bgcolor="#FFFFFF" colspan="2" align="left">
                                            &nbsp;&nbsp;<textarea  name="address1"  id="Address" maxlength="255" class="headingSpas"  style="width:170px;height: 35px" ></textarea>
                                        </td>
                                    </tr>

                                    <tr height="20">
                                        <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Mobile <span class="mandatory">*</span></td>
                                        <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                            <input type="text" name="mobile"   id="Mobile" class="headingSpas" maxlength="13" style="width:170px" />
                                        </td>
                                    </tr>
                                    <tr height="20">
                                        <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">District
                                        <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                            <input type="text" name="district"   id="District" class="headingSpas" maxlength="13" style="width:170px" />
                                        </td>
                                    </tr>
                                    <tr height="20">
                                        <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">State
                                        <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                            <input type="text" name="state"   id="State" class="headingSpas" maxlength="13" style="width:170px" />
                                        </td>
                                    </tr>
                                    <tr height="20">
                                        <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Tin No
                                        <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                            <input type="text" name="tinNo"   id="TinNo" class="headingSpas" maxlength="13" style="width:170px" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="25%" height="25" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:350px">Select Country <span class="mandatory">*</span></td>
                                        <td width="75%" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:10px">
                                            <select name="country" id="Country"  style="width:175px!important;">
                                                <option value="0" >-- Select Here --</option >
                                                <c:forEach items="${userForm.labelList1}" var="countryTemp">
                                                    <option value="${countryTemp.value}" title='${countryTemp.label}'>${countryTemp.label}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>

                                    <tr height="20">
                                        <td colspan="3" style="padding-left:450px" align="left" bgcolor="#FFFFFF">
                                            <input type="button" name="submitBtn" id="submitBtn" class="headingSpas" value="Submit" onclick="validateForm();"/>
                                            <input type="button" name="cancelBtn" id="cancl" class="headingSpas" value="Cancel" onclick="cancel();"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </center>
</div>


