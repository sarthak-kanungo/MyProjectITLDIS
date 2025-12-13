<%--
    Document   : v_add_user
    Created on : Feb 12, 2014, 5:35:57 PM
    Author     : manish.kishore
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
        
        var elementArr = new Array('Region Name', 'User Type',  'Company Name', 'User ID', 'Password', 'Re-Type Password', 'Name', 'Address', 'Address2', 'City', 'Pin Code', 'State', 'Country', 'Phone No', 'Mobile','Dealer Code', 'Email');
        var strValue = null;
        var strObject = null;

        for (var i = 0; i < elementArr.length; i++)
        {
            strObject = document.getElementById(elementArr[i]);

            if (strObject){
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
        strValue = strValue.replace(objRegExp, '');
        var temp = objSpecExp.exec(strValue);
        var regexp=  /^([a-zA-Z0-9]){7,}$/;
        if (strValue.length == 0)
        {
            if (strObject.tagName == "SELECT")
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Please select value in " + objectId + " field.";
                //alert("Please select value in " + objectId + " field.");
                return false;
            }else{
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
        }
        else if (objectId == "Dealer Code")//|| objectId == "Customer Name" || objectId == "Customer Contact Person"
        {
            if(document.getElementById("Dealer Code").value=="0"){
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Please enter value in " + objectId + " field.";
                return false;
            }
        }

    <%--else if (objectId == "Designation" || objectId == "Name")//|| objectId == "Customer Name" || objectId == "Customer Contact Person"
    {
            if (!characteronlyExp.test(strValue))
            {
                strObject.focus();
                 document.getElementById("msg_saveFAILED").innerHTML="Numbers and Special Characters are not allowed in " + objectId + " field .";
                //alert("Numbers and Special Characters are not allowed in " + objectId + " field .");
                return false;
            }
        }--%>
    <%-- else if (objectId == "Phone No" || objectId == "Mobile" || objectId == "Pin Code")
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
              document.getElementById("msg_saveFAILED").innerHTML=""+objectId + " field field must be 10 characters.";
             //alert(objectId + " field can not be greater than or less than 10 characters.");
             return false;
         }
     }--%>
             else if (objectId == "Phone No" || objectId == "Mobile" || objectId == "Pin Code")
             {   
                 if (mobileExp.test(strValue) == false )
                 {
                     strObject.focus();
                     document.getElementById("msg_saveFAILED").innerHTML=""+objectId + " field must contains numeric value, '+' , '-' and must be more than 10 characters .";
                     return false;
                 }

             }else if( !document.getElementById('Password').value.match(regexp)){
                 document.getElementById("msg_saveFAILED").innerHTML="Please Enter Valid Password";
                 document.getElementById('Password').focus();
                 return false;
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
            <li class="breadcrumb_link"><a href = '<%=cntxpath%>/UserManagement.do?option=initUser'> MANAGE USERS</a></li>
            <li class="breadcrumb_link">ADD NEW USER</li>
        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1>ADD NEW USER</h1>

                <form action="UserManagement.do?option=insertUser" method="POST" id="form" >
                    <input type="hidden" name="option" value="insertUser"/>
                    <input type="hidden" name="upperLink" value="<a href = '<%=cntxpath%>/UserManagement.do?option=initUserInformation'>USER MANAGEMENT</a> <a href = '<%=cntxpath%>/UserManagement.do?option=initUser'> MANAGE USERS</a>@@ADD NEW USER"/>
                    <table width=100% align="center" border="0" cellpadding="5" cellspacing="5" >
                        <tr>
                            <td valign="top" align="center"  bgcolor="#FFFFFF">
                                <table bgcolor="#eeeeee" width="100%" border="0" cellpadding="4" cellspacing="0"  class="headingSpas" style="float: left">
                                    <tr>
                                        <td width="50%" height="15" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:350px">User Type <span class="mandatory">*</span></td>
                                        <td width="50%" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:0px">
                                            <select name="userTypeId" id="User Type"  style="width:175px!important">
                                                <option value="0" >-- Select Here --</option >
                                                <c:forEach items="${userForm.labelList1}" var="userTypeTemp">
                                                    <option value="${userTypeTemp.value}" title='${userTypeTemp.label}'>${userTypeTemp.label}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>

                                    <tr height="15">
                                        <td  style="padding-left:350px" height="15"  bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">User-ID <span class="mandatory">*</span></td>
                                        <td style="padding-left:0px" height="15"  colspan="3" bgcolor="#FFFFFF" align="left">
                                            <input type="text" name="userId" maxlength="15" id="User ID" class="headingSpas" style="width:170px;height: 20px" />
                                        </td>
                                    </tr>
                                    <tr height="20">
                                        <td  style="padding-left:350px" height="15"  bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Password <span class="mandatory">*</span></td>
                                        <td style="padding-left:5px" colspan="3" bgcolor="#FFFFFF" align="left">
                                            <input type="password" name="password" maxlength="15" id="Password" class="headingSpas"  style="width:170px;height: 20px" />
                                        </td>
                                    </tr>
                                    <tr height="20">
                                        <td  style="padding-left:350px" height="15" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Re-Type Password <span class="mandatory">*</span></td>
                                        <td style="padding-left:5px" bgcolor="#FFFFFF" colspan="3" align="left">
                                            <input type="password" name="retypepassword" maxlength="15" id="Re-Type Password" class="headingSpas"  style="width:170px;height: 20px" />
                                        </td>
                                    </tr>
                                    <tr height="20">
                                        <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">User Name <span class="mandatory">*</span></td>
                                        <td style="padding-left:0px" bgcolor="#FFFFFF" colspan="3" align="left">
                                            <input type="text" name="firstName"  id="Name" class="headingSpas" maxlength="50" style="width:170px;height: 20px" />
                                        </td>
                                    </tr>
                                    <tr height="20">
                                        <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Address <span class="mandatory">*</span></td>
                                        <td style="padding-left:5px" bgcolor="#FFFFFF" colspan="3" align="left">
                                            <textarea type="text" name="address1"  id="Address" class="headingSpas"  maxlength="250" style="width:170px;height: 20px" ></textarea>
                                        </td>
                                    </tr>

                                    <tr height="20">
                                        <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Contact No. <span class="mandatory">*</span></td>
                                        <td style="padding-left:0px" bgcolor="#FFFFFF"  colspan="3" align="left">
                                            <input type="text" name="mobile"  id="Mobile" class="headingSpas" maxlength="13" style="width:170px;height: 20px" />
                                        </td>
                                    </tr>
                                    <tr>
                                        <td width="25%" height="25" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:350px">Dealer Name <span class="mandatory">*</span></td>
                                        <td width="75%" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:0px">
                                            <select name="dealerCode" id="Dealer Code"  style="width:175px !important">
                                                <option value="0">-- Select Here --</option >
                                                <c:forEach items="${userForm.labelList5}" var="dealerNameTemp">
                                                    <option value="${dealerNameTemp.value}" title='${dealerNameTemp.label}'>${dealerNameTemp.label}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr height="20">
                                        <td  style="padding-left:350px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Email <span class="mandatory">*</span></td>
                                        <td style="padding-left:0px" bgcolor="#FFFFFF" colspan="3" align="left">
                                            <input type="text" name="email"  id="Email" class="headingSpas" maxlength="100" style="width:170px;height: 20px" />
                                        </td>
                                    </tr>
                                    <tr><td colspan="3" bgcolor="#FFFFFF">&nbsp;</td></tr>
                                    <tr height="20">
                                        <td colspan="3" style="padding-left:450px" align="left" bgcolor="#FFFFFF">
                                            <input type="button" name="submitBtn" id="submitBtn" class="headingSpas" value="Submit" onclick="validateForm();"/>
                                            <input type="button" name="cancelBtn" id="cancl" class="headingSpas" value="Cancel" onclick="cancel();"/>
                                        </td>
                                    </tr>                                    
                                    <tr height="20">
                                        <td colspan="2" bgcolor="#FFFFFF" align="left"><span style="color:red; padding-left:50px; font-size: 11px;"> <b>* Password Criteria :</b></span></td>
                                    </tr>
                                    <tr height="20">
                                        <td colspan="2" bgcolor="#FFFFFF" align="left"><span style="color:blue; padding-left:50px; font-size: 11px;">* Length should be greater than 7 character.</span></td>
                                    </tr>
                                    <tr height="20">
                                        <td colspan="2" bgcolor="#FFFFFF" align="left"><span style="color:blue; padding-left:50px; font-size: 11px;">* Must not contains any special character.</span></td>
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