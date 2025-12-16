<%--
    Document   : v_user_list_auto
    Created on : Feb 15, 2014, 10:12:07 AM
    Author     : manish.kishore
--%>
<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
            String userId = (String) session.getAttribute("user_id");
%>
<script type="text/javascript" language="javascript">
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

        var elementArr = new Array('Region Name', 'User Type', 'Company Name', 'User ID', 'Password', 'Re-Type Password', 'Name','Designation', 'Address1', 'Address2', 'City', 'Pin Code', 'State', 'Country', 'Phone No', 'Mobile', 'Email');
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

        document.getElementById('submitBtn').disabled = true;
    }

    function checkValue(strObject, strValue)
    {
        var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['&\\@]/g;
        var emailExp = /\S+@\S+\.\S+/;
        var characteronlyExp = /^[a-z .A-Z]+$/;
        var characteronlyWithoutSpaceExp = /^[a-zA-Z0-9]+$/;
        var objectId = strObject.id;

        strValue = strValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(strValue);

        if (strValue.length == 0)
        {
            if (strObject.tagName == "SELECT")
            {
                strObject.focus();
                //alert("Please select value in " + objectId + " field.");
                document.getElementById("msg_saveFAILED").innerHTML="Please select value in " + objectId + " field.";
                return false;
            }
            else
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Please enter value in " + objectId + " field.";
               // alert("Please enter value in " + objectId + " field.");
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
               // alert("Space and Special Characters are not allowed in " + objectId + " field .");
                return false;
            }
        }

        else if (objectId == "Designation" || objectId == "Name")//|| objectId == "Customer Name" || objectId == "Customer Contact Person"
        {

            if (!characteronlyExp.test(strValue))
            {
                strObject.focus();
                 document.getElementById("msg_saveFAILED").innerHTML="Numbers and Special Characters are not allowed in " + objectId + " field .";
                //alert("Numbers and Special Characters are not allowed in " + objectId + " field .");
                return false;
            }
        }
        else if (objectId == "Phone No" || objectId == "Mobile" || objectId == "Pin Code")
        {
            if (validnumber(strObject) == false)
            {
                strObject.focus();
                //alert(objectId + " field should be Number.");
                return false;
            }
            else if ((objectId == "Phone No" || objectId == "Mobile") && strObject.value.length != 10) //|| objectId == "Contact Number"
            {
                strObject.focus();
                 document.getElementById("msg_saveFAILED").innerHTML=""+objectId + " field must be 10 characters.";
                //alert(objectId + " field can not be greater than or less than 10 characters.");
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
    }
     function cancel(){

        document.getElementById("form").action="<%=cntxpath%>/login.do?option=homePage";
        document.getElementById("form").submit();
    }
</script>
<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href = "<%=cntxpath%>/login.do?option=homePage">HOME</a></li>
             <li class="breadcrumb_link">MODIFY PROFILE</li>

        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED"></div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1>MODIFY PROFILE</h1>
                <form action="UserManagement.do" method="POST"  id="form" onsubmit="return validateForm();">
                    <input type="hidden" name="option" value="updateUserProfile"/>
                    <input type="hidden" name="upperLink" value="<a href = '<%=cntxpath%>/login.do?option=homePage'>HOME</a>MODIFY PROFILE"/>
                    <table width="100%" border="0" cellpadding="4" cellspacing="0" >
                        <tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">User-ID</td>
                            <td style="padding-left:10px" colspan="2" bgcolor="#FFFFFF" align="left">
                                ${userForm.userId}<input type="hidden" name="userId" value="${userForm.userId}"/>
                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Region Name <span class="mandatory">*</span></td>
                            <td style="padding-left:10px"  bgcolor="#FFFFFF" align="left">

                                <select name="regionId" id="Region Name" class="headingSpas" style="width:177px" onchange="checkValueSelect(this, 'regionSpan');">
                                    <option value="">--Select Here--</option>
                                    <c:forEach items="${userForm.labelList}" var="regionTemp">
                                        <c:choose>
                                            <c:when test="${userForm.regionId eq regionTemp.value}">
                                                <option selected value="${regionTemp.value}">${regionTemp.label}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${regionTemp.value}">${regionTemp.label}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <option value="Other">Other</option>
                                </select>
                            </td>
                            <td>
                                <span id="regionSpan" style="display:none;"><input type="text" class="headingSpas" name="regionIdOther"  maxlength="100"  id="Other Region" style="width:170px"/></span>
                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">User Type <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" colspan="2" bgcolor="#FFFFFF" align="left">
                                <select name="userTypeId" id="User Type" class="headingSpas" style="width:177px">
                                    <option value="">--Select Here--</option>
                                    <c:forEach items="${userForm.labelList1}" var="userTypeTemp">
                                        <c:choose>
                                            <c:when test="${userForm.userTypeId eq userTypeTemp.value}">
                                                <option selected value="${userTypeTemp.value}">${userTypeTemp.label}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option  value="${userTypeTemp.value}">${userTypeTemp.label}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>

                        <tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Company Name <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" colspan="2" bgcolor="#FFFFFF" align="left">
                                <input type="text" name="companyName"  value="${userForm.companyName}"  id="Company Name" class="headingSpas" maxlength="50" style="width:170px" />
                            </td>
                        </tr>

                        <tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Password <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" colspan="2" bgcolor="#FFFFFF" align="left">
                                <input type="password" name="password" maxlength="15" id="Password" value="${userForm.password}" class="headingSpas"  style="width:170px" />
                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Re-Type Password <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF" colspan="2" align="left">
                                <input type="password" name="retypepassword" maxlength="15" id="Re-Type Password" value="${userForm.password}" class="headingSpas"  style="width:170px" />
                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Name <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF" colspan="2" align="left">
                                <input type="text" name="firstName"  id="Name" class="headingSpas" value="${userForm.firstName}" maxlength="50" style="width:170px" />
                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Designation <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" colspan="2" bgcolor="#FFFFFF" align="left">
                                <input type="text" name="designation"  value="${userForm.designation}" id="Designation" maxlength="50" class="headingSpas"  style="width:170px" />
                            </td>
                        </tr>

                        <tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Address1 <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF" colspan="2" align="left">
                                <input type="text" name="address1"  id="Address1" class="headingSpas"  value="${userForm.address1}" maxlength="250" style="width:170px" />
                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Address2 <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF" colspan="2" align="left">
                                <input type="text" name="address2"  id="Address2" class="headingSpas" value="${userForm.address2}" maxlength="250" style="width:170px" />
                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">City <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">

                                <select name="city" id="City" style="width:177px" class="headingSpas" onchange="checkValueSelect(this, 'citySpan');">
                                    <option value="">--Select Here--</option>
                                    <c:forEach items="${userForm.labelList3}" var="cityTemp">
                                        <c:choose>
                                            <c:when test="${userForm.city eq cityTemp.value}">
                                                <option selected value="${cityTemp.value}">${cityTemp.label}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option  value="${cityTemp.value}">${cityTemp.label}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <option value="Other">Other</option>
                                </select>
                            </td>
                            <td>
                                <span id="citySpan" style="display:none;"><input type="text" class="headingSpas" name="cityOther"  maxlength="50" id="Other City" style="width:170px"/></span>
                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Pin Code <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF" colspan="2" align="left">
                                <input type="text" name="pincode"  id="Pin Code" value="${userForm.pincode}" class="headingSpas" maxlength="6" style="width:170px" />
                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">State <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">

                                <select name="state" id="State" style="width:177px" class="headingSpas" onchange="checkValueSelect(this, 'stateSpan');">
                                    <option value="">--Select Here--</option>
                                    <c:forEach items="${userForm.labelList2}" var="stateTemp">
                                        <c:choose>
                                            <c:when test="${userForm.state eq stateTemp.value}">
                                                <option selected value="${stateTemp.value}">${stateTemp.label}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option  value="${stateTemp.value}">${stateTemp.label}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <option value="Other">Other</option>
                                </select>
                            </td>
                            <td>
                                <span id="stateSpan" style="display:none;"><input type="text"  class="headingSpas" name="stateOther" maxlength="50" id="Other State" style="width:170px"/></span>

                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Country <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                <select name="country" id="Country" class="headingSpas" style="width:177px" onchange="checkValueSelect(this, 'countrySpan');">
                                    <option value="">--Select Here--</option>
                                    <c:forEach items="${userForm.labelList4}" var="countryTemp">
                                        <c:choose>
                                            <c:when test="${userForm.country eq countryTemp.value}">
                                                <option selected value="${countryTemp.value}">${countryTemp.label}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option  value="${countryTemp.value}">${countryTemp.label}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                    <option value="Other">Other</option>
                                </select>
                            </td>
                            <td>
                                <span id="countrySpan" style="display:none;"><input type="text" class="headingSpas" name="countryOther" maxlength="50" id="Other Country" style="width:170px"/></span>
                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Phone No <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                <input type="text" name="phoneNo" value="${userForm.phoneNo}" id="Phone No" class="headingSpas" maxlength="10" style="width:170px" />
                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Mobile <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                <input type="text" name="mobile"  value="${userForm.mobile}" id="Mobile" class="headingSpas" maxlength="10" style="width:170px" />
                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Email <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                <input type="text" name="email" value="${userForm.email}" id="Email" class="headingSpas" maxlength="100" style="width:170px" />
                            </td>
                        </tr>

                        <tr height="20">
                            <td colspan="4" style="padding-left:10px" align="center" bgcolor="#FFFFFF">
                                <input type=submit name="submitBtn" id="submitBtn"  value="Submit"/>
                                <input type=button name="cancelBtn" id="cancl"  value="Cancel" onclick="cancel();"/>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </center>
</div>
