<%-- 
    Document   : v_dealer_detail_ajax
    Created on : Dec 25, 2015, 11:49:03 AM
    Author     : Ashutosh.Kumar1
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

        var elementArr = new Array( 'Dealer Code','Dealer Name', 'Location Code','Location','Address1', 'Mobile','Country');            <%--,'District','State','TinNo'--%>
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

    function cancel(){

        document.getElementById("form").action=(<%=flag.equals("edit")%>)?"<%=cntxpath%>/UserManagement.do?option=initUserInformation":"<%=cntxpath%>/UserManagement.do?option=initDealer";
        document.getElementById("form").submit();
    }
</script>
<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href = '<%=cntxpath%>/UserManagement.do?option=initUserInformation'>USER MANAGEMENT</a></li>
            <%if (!flag.equals("edit")) {%>
            <li class="breadcrumb_link"><a href = '<%=cntxpath%>/UserManagement.do?option=initDealer'> MANAGE DEALER</a></li>
            <li class="breadcrumb_link">MODIFY DEALER</li>
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
                <h1>MODIFY DEALER</h1>
                <%} else {%>
                <h1>EDIT OWN PROFILE</h1>
                <%}%>
                <form action="UserManagement.do" method="POST" id="form" >
                    <input type="hidden" name="option" value="updateDealer"/>
                    <input type="hidden" name="flag" value="<%=flag%>"/>
                    <%if (!flag.equals("edit")) {%>
                    <input type="hidden" name="upperLink" value="<a href = '<%=cntxpath%>/UserManagement.do?option=initUserInformation'>USER MANAGEMENT</a> <a href = '<%=cntxpath%>/UserManagement.do?option=initDealer'> MANAGE DEALER</a>@@MODIFY DEALER"/>
                    <%} else {%>
                    <input type="hidden" name="upperLink" value="<a href = '<%=cntxpath%>/UserManagement.do?option=initUserInformation'>USER MANAGEMENT</a> @@EDIT OWN PROFILE"/>
                    <%}%>
                    <table width="100%" border="0" cellpadding="4" cellspacing="0" style="padding-top: 5px" >
                        <tr height="20">
                            <td  style="padding-left:350px" width="50%" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Dealer Code</td>
                            <td style="padding-left:10px" width="50%" colspan="2" bgcolor="#FFFFFF" align="left">
                                &nbsp;&nbsp;${userForm.dealerCode}<input type="hidden" id="Dealer Code" name="dealerCode" value="${userForm.dealerCode}"/>
                            </td>
                        </tr>

                        <%-- <%if (!flag.equals("edit")) {%>
                         <tr>
                             <td width="25%" height="25" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:10px">Select User Type <span class="mandatory">*</span></td>
                             <td width="75%" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:10px">
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
                         <%}%>--%>

                        <%--<tr height="20">
                            <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Company Name <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" colspan="2" bgcolor="#FFFFFF" align="left">
                                <input type="text" name="companyName"  value="${userForm.companyName}"  id="Company Name" class="headingSpas" maxlength="50" style="width:170px" />
                            </td>
                        </tr>--%>
                        <%if (!flag.equals("edit")) {%>
                        <tr height="20">
                            <td  style="padding-left:350px" width="50%" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Dealer Name <span class="mandatory">*</span></td>
                            <td style="padding-left:10px;margin:0px!important" width="50%" colspan="2" bgcolor="#FFFFFF" align="left">
                                &nbsp;&nbsp;<input type="text" name="dealerName" maxlength="15" id="Dealer Name" value="${userForm.dealerName}" class="headingSpas"  style="width:170px;margin:0px!important" />
                            </td>
                        </tr>
                        <%-- <tr height="20">
                             <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Re-Type Password <span class="mandatory">*</span></td>
                             <td style="padding-left:10px; margin:0px!important" bgcolor="#FFFFFF" colspan="2" align="left">
                                 &nbsp;&nbsp;<input type="password" name="retypepassword" maxlength="15" id="Re-Type Password" value="${userForm.password}" class="headingSpas"  style="width:170px;margin:0px!important" />
                             </td>
                         </tr>--%>
                        <%}%>
                        <tr height="20">
                            <td  style="padding-left:350px" bgcolor="#FFFFFF" width="50%" class="headingSpas" nowrap align="left">Location Code <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF" width="50%" colspan="2" align="left">
                                <input type="text" name="locationCode"  id="Location Code" class="headingSpas" value="${userForm.locationCode}" maxlength="50" style="width:170px" />
                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:350px" bgcolor="#FFFFFF" width="50%" class="headingSpas" nowrap align="left">Location <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF" width="50%" colspan="2" align="left">
                                <input type="text" name="location"  id="Location" class="headingSpas" value="${userForm.location}" maxlength="50" style="width:170px" />
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
                                &nbsp;&nbsp;<textarea  name="address1"  id="Address1" class="headingSpas"   maxlength="250" style="width:170px;height: 35px" >${userForm.address1}</textarea>
                            </td>
                        </tr>

                        <tr height="20">
                            <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Mobile <span class="mandatory">*</span></td>
                            <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                <input type="text" name="mobile"  value="${userForm.mobile}" id="Mobile" class="headingSpas" maxlength="13" style="width:170px" />
                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">District 
                            <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                <input type="text" name="district" value="${userForm.district}"  id="District" class="headingSpas" maxlength="13" style="width:170px" />
                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">State 
                            <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                <input type="text" name="state" value="${userForm.state}"  id="State" class="headingSpas" maxlength="13" style="width:170px" />
                            </td>
                        </tr>
                        <tr height="20">
                            <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Tin No
                            <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                <input type="text" name="tinNo" value="${userForm.tinNo}"  id="TinNo" class="headingSpas" maxlength="13" style="width:170px" />
                            </td>
                        </tr>


                        <tr height="20">
                            <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Status <span class="mandatory">*</span></td>
                            <td style="padding-left:10px"  bgcolor="#FFFFFF" align="left">
                                <%if (!flag.equals("edit")) {%>

                                <select name="status" id="Status" class="headingSpas" style="width:177px" onchange="checkValueSelect(this, 'regionSpan');">
                                    <%--<option value="">--Select Here--</option>--%>


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
                        <tr>
                            <td width="25%" height="25" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:350px">Select Country <span class="mandatory">*</span></td>
                            <td width="75%" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:10px">
                                <select name="country" id="Country"  style="width:175px!important;">
                                    <option value="0" >-- Select Here --</option >
                                    <c:forEach items="${userForm.labelList1}" var="countryTemp">                                        
                                        <c:choose>
                                            <c:when test="${countryTemp.value eq userForm.country}">
                                                <option selected value="${countryTemp.value}" selected title='${countryTemp.label}'>${countryTemp.label}</option>
                                            </c:when>
                                            <c:otherwise>
                                                <option value="${countryTemp.value}" title='${countryTemp.label}'>${countryTemp.label}</option>
                                            </c:otherwise>                                          
                                        </c:choose>
                                    </c:forEach>
                                </select>
                            </td>
                        </tr>
                        <tr><td colspan="3" bgcolor="#FFFFFF">&nbsp;</td></tr>

                        <tr height="20">
                            <td colspan="4" style="padding-left:30px" align="center" bgcolor="#FFFFFF">
                                <input type=button name="submitBtn" id="submitBtn"  value="Submit" onclick="validateForm();"/>
                                <input type=button name="cancelBtn" id="cancl"  value="Cancel" onclick="cancel();"/>
                            </td>

                        </tr>

                    </table>
                </form>
            </div>
        </div>
    </center>
</div>

