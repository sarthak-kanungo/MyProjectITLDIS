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
            String flag = request.getParameter("flag");
            String user_id = (String) session.getAttribute("user_id");
            if (flag == null) {
                flag = "";
            }
%>
<script type="text/javascript" language="javascript">
    function checkValueSelect(disObject, divId)
    {
        if (disObject.value == 'Other'){
            document.getElementById(divId).style.display = "block";

        }else{
            document.getElementById(divId).style.display = "none";
        }
    }

    function validateForm(){
        
        var elementArr="";
        
        if ('<%=flag%>'!= 'edit') {
            elementArr = new Array('Region Name', 'User Type', 'Company Name', 'User ID', 'Password', 'Re-Type Password', 'Name','Designation', 'Address', 'Address2', 'City', 'Pin Code', 'State', 'Country', 'Phone No', 'Mobile','Dealer Code','Email','status');
        } else {
            elementArr = new Array('Region Name', 'User Type', 'Company Name', 'User ID', 'Name','Designation', 'Address', 'Address2', 'City', 'Pin Code', 'State', 'Country', 'Phone No', 'Mobile','Dealer Code','Email','status');
        }        
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
        document.getElementById('form').submit();
    }

    function checkValue(strObject, strValue)
    {
        var objRegExp = /^(\s*)$/g;
        var objSpecExp = /['&\\@]/g;
        var emailExp = /\S+@\S+\.\S+/;
        var characteronlyExp = /^[a-z .A-Z]+$/;
        var characteronlyWithoutSpaceExp = /^[a-zA-Z0-9]+$/;
        var mobileExp = /^[\(\)\s\-\+\d]{10,20}$/;
        var objectId = strObject.id;
        var regexp=  /^([a-zA-Z0-9]){7,}$/;

        strValue = strValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(strValue);

        if (strValue.length == 0)
        {
            if (strObject.tagName == "SELECT")
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Please select value in " + objectId + " field.";
                // alert("Please select value in " + objectId + " field.");
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
        else if (objectId == "User Type")//|| objectId == "Customer Name" || objectId == "Customer Contact Person"
        {
            if(document.getElementById("User Type").value=="0"){
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Please enter value in " + objectId + " field.";
                return false;
            }
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
        } else if (objectId == "Dealer Code")//|| objectId == "Customer Name" || objectId == "Customer Contact Person"
        {
            if(document.getElementById("Dealer Code").value=="0"){
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Please enter value in " + objectId + " field.";
                return false;
            }
        }

        else if (objectId == "Phone No" || objectId == "Mobile" || objectId == "Pin Code")
        {
            if (mobileExp.test(strValue) == false )
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML=""+objectId + " field must contains numeric value, '+' , '-' and must be more than 10 characters .";
                return false;
            }
        }
        else if(objectId=="Password" ){
            if(!strObject.value.match(regexp)){
                document.getElementById("msg_saveFAILED").innerHTML="Please Enter Valid Password";
                strObject.focus();
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
         else if (objectId == "status"){
            if(document.getElementById("status").value=="0"){
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Please enter value in " + objectId + " field.";
                return false;
            }
        }
    }

    function cancel(){

        document.getElementById("form").action=(<%=flag.equals("edit")%>)?"<%=cntxpath%>/UserManagement.do?option=initUserInformation":"<%=cntxpath%>/UserManagement.do?option=initUser";
        document.getElementById("form").submit();
    }
</script>
<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href = '<%=cntxpath%>/UserManagement.do?option=initUserInformation'>USER MANAGEMENT</a></li>
            <%if (!flag.equals("edit")) {%>
            <li class="breadcrumb_link"><a href = '<%=cntxpath%>/UserManagement.do?option=initUser'> MANAGE USERS</a></li>
            <li class="breadcrumb_link">MODIFY USER</li>
            <%} else {%>
            <li class="breadcrumb_link">EDIT OWN PROFILE</li>
            <%}%>
        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED"></div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <%if (!flag.equals("edit")) {%>
                <h1>MODIFY USER</h1>
                <%} else {%>
                <h1>EDIT OWN PROFILE</h1>
                <%}%>
                <form action="UserManagement.do" method="POST" id="form" >
                    <input type="hidden" name="option" value="updateUser"/>
                    <input type="hidden" name="flag" value="<%=flag%>"/>
                    <%if (!flag.equals("edit")) {%>
                    <input type="hidden" name="upperLink" value="<a href = '<%=cntxpath%>/UserManagement.do?option=initUserInformation'>USER MANAGEMENT</a> <a href = '<%=cntxpath%>/UserManagement.do?option=initUser'> MANAGE USERS</a>@@MODIFY USER"/>
                    <%} else {%>
                    <input type="hidden" name="upperLink" value="<a href = '<%=cntxpath%>/UserManagement.do?option=initUserInformation'>USER MANAGEMENT</a> @@EDIT OWN PROFILE"/>
                    <%}%>
                    <table width="100%" border="0" cellpadding="4" cellspacing="1"  style="padding-top: 5px">
                        <tr height="20">
                            <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">User-ID</td>
                            <td style="padding-left:10px" colspan="2" bgcolor="#FFFFFF" align="left">
                                &nbsp;&nbsp;${userForm.userId}<input type="hidden" name="userId" value="${userForm.userId}"/>
                            </td>
                        </tr>
                        <%-- <tr height="20">
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
                        </tr>--%>
                        <%if (!flag.equals("edit")) {%>
                        <tr>
                            <td width="50%" height="25" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:350px">User Type <span class="mandatory">*</span></td>
                            <td width="50%" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:10px">
                                <select name="userTypeId" id="User Type"  style="width:175px!important;">
                                    <option value="0" >-- Select Here --</option >
                                    <c:forEach items="${userForm.labelList1}" var="userTypeTemp">
                                        <c:choose>
                                            <c:when test="${userForm.userTypeId eq userTypeTemp.value}">
                                                <option selected value="${userTypeTemp.value}" title='${userTypeTemp.label}'>${userTypeTemp.label}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option  value="${userTypeTemp.value}" title='${userTypeTemp.label}'>${userTypeTemp.label}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <%} else {%>
                        <input type="hidden"  name="userTypeId" value="${userForm.userTypeId}" id="User Type" />
                        <%}%>
                        <%if (!flag.equals("edit")) {%>
                        <tr height="20">
                            <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Password <span class="mandatory">*</span></td>
                            <td style="padding-left:8px;margin:0px!important" colspan="2" bgcolor="#FFFFFF" align="left">
                                &nbsp;&nbsp;<input type="password" name="password" maxlength="15" id="Password" value="${userForm.password}" class="headingSpas"  style="width:170px;margin:0px!important" />
                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Re-Type Password <span class="mandatory">*</span></td>
                            <td style="padding-left:8px; margin:0px!important" bgcolor="#FFFFFF" colspan="2" align="left">
                                &nbsp;&nbsp;<input type="password" name="retypepassword" maxlength="15" id="Re-Type Password" value="${userForm.password}" class="headingSpas"  style="width:170px;margin:0px!important" />
                            </td>
                        </tr>
                        <%}%>
                        <tr height="20">
                            <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">User Name <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF" colspan="2" align="left">
                                <input type="text" name="firstName"  id="Name" class="headingSpas" value="${userForm.firstName}" maxlength="50" style="width:170px" />
                            </td>
                        </tr>                      
                        <tr height="20">
                            <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Address <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF" colspan="2" align="left">
                                <input type="text" name="address1"  id="Address" class="headingSpas"  value="${userForm.address1}" maxlength="250" style="width:170px" />
                            </td>
                        </tr>
                        <%--<tr height="20">
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
                        
                        <%--<tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Dealer Code </td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF"  colspan="3" align="left">
                                <input type="text" name="dealerCode" value="${userForm.dealerCode}" id="Dealer Code" class="headingSpas" maxlength="10" style="width:170px" />
                            </td>
                        </tr>--%>
                        <tr height="20">
                            <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Contact No. <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                <input type="text" name="mobile"  value="${userForm.mobile}" id="Mobile" class="headingSpas" maxlength="20" style="width:170px" />
                            </td>
                        </tr>
                        <tr>
                            <td width="50%" height="25" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:350px">Dealer Name <span class="mandatory">*</span></td>
                            <td width="50%" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:10px">
                                <select name="dealerCode" id="Dealer Code"  style="width:175px!important;">
                                    <option value="0" >-- Select Here --</option >
                                    <c:forEach items="${userForm.labelList5}" var="dealerNameTemp">
                                        <c:choose>
                                            <c:when test="${dealerNameTemp.value eq userForm.dealerCode}">
                                                <option value="${dealerNameTemp.value}" selected title='${dealerNameTemp.label}'>${dealerNameTemp.label}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${dealerNameTemp.value}" title='${dealerNameTemp.label}'>${dealerNameTemp.label}</option>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Email <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                <input type="text" name="email" value="${userForm.email}" id="Email" class="headingSpas" maxlength="100" style="width:170px" />
                            </td>
                        </tr>

                        <tr height="20">
                            <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Status <span class="mandatory">*</span></td>
                            <td style="padding-left:10px"  bgcolor="#FFFFFF" align="left">
                                <%if (!flag.equals("edit")) {%>

                                <select name="status" id="status" class="headingSpas" style="width:177px!important;" onchange="checkValueSelect(this, 'regionSpan');">
                                    <%--<option value="0">--Select Here--</option>--%>


                                    <c:choose>
                                        <c:when test="${userForm.status eq 'A'}">
                                            <option selected value="A">Active</option>
                                            <option  value="I">Inactive</option>
                                        </c:when>
                                        <c:otherwise>
                                            <option selected value="I">Inactive</option>
                                            <option value="A">Active</option>
                                        </c:otherwise>
                                    </c:choose>
                                </select>
                                <%} else {%>
                                <c:choose>
                                    <c:when test="${userForm.status eq 'A'}">
                                        &nbsp;&nbsp;Active<input type="hidden"  name="status" value="A" id="status"  />
                                    </c:when>
                                    <c:otherwise>
                                        &nbsp;&nbsp;Inactive<input type="hidden" name="status" value="I" id="status"/>
                                    </c:otherwise>
                                </c:choose>

                                <%}%>
                            </td>
                            <td>
                                <span id="regionSpan" style="display:none;"><input type="text" class="headingSpas" name="regionIdOther"  maxlength="100"  id="Other Region" style="width:170px"/></span>
                            </td>
                        </tr>
                        <tr><td colspan="3" bgcolor="#FFFFFF">&nbsp;</td></tr>


                        <tr height="20">
                            <td colspan="4" style="padding-left:30px" align="center" bgcolor="#FFFFFF">
                                <input type=button name="submitBtn" id="submitBtn"  value="Submit" onclick="validateForm();"/>
                                <input type=button name="cancelBtn" id="cancl"  value="Cancel" onclick="cancel();"/>
                            </td>

                        </tr>
                        <%if (!flag.equals("edit")) {%>
                        <tr height="20">
                            <td colspan="3" bgcolor="#FFFFFF" align="left"><span style="color:red; padding-left:50px; font-size: 11px;"><b> * Password Criteria :</b></span></td>
                        </tr>
                        <tr height="20">
                            <td colspan="3" bgcolor="#FFFFFF" align="left"><span style="color:blue; padding-left:50px; font-size: 11px;">* Length should be greater than 7 character.</span></td>
                        </tr>
                        <tr height="20">
                            <td colspan="3" bgcolor="#FFFFFF" align="left"><span style="color:blue; padding-left:50px; font-size: 11px;">* Must not contains any special character.</span></td>
                        </tr>
                        <%}%>
                    </table>
                </form>
            </div>
        </div>
    </center>
</div>
