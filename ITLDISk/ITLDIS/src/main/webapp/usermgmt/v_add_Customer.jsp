<%-- 
    Document   : v_add_Customer
    Created on : Feb 21, 2014, 11:17:31 AM
    Author     : sreeja.vijayakumaran
--%>

<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>


<c:if test="${not empty userForm.goLink}">
    <html>
        <head>

        </c:if>

        <script type="text/javascript">

            function imposeMaxLength(obj) {
                var mlength = obj.getAttribute ? parseInt(obj.getAttribute("maxlength")) : ""
                if (obj.getAttribute && obj.value.length > mlength) {
                    //obj.value = obj.value.substring(0, mlength)
                    return false;
                }
            }

            function validateForm()
            {
                var elementArr = new Array('Customer Name', 'Address', 'Contact Person', 'City', 'Other City', 'State', 'Other State', 'Pin Code', 'Country', 'Other Country', 'Email', 'Contact No.', 'Customer Type ID', 'Filled On', 'Filled By');
                var strValue = null;
                var strObject = null;

                for (var i = 0; i < elementArr.length; i++)
                {
                    strObject = document.getElementById(elementArr[i]);

                    if (strObject && strObject.disabled == false)
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
                var objectId = strObject.id;
                strValue = strValue.replace(objRegExp, '');
                var temp = objSpecExp.exec(strValue);

                if (strValue.length == 0)
                {
                    if (strObject.tagName == "SELECT")
                    {
                        try
                        {
                            strObject.focus();
                            alert("Please select value in " + objectId + " field.");
                            return false;
                        } catch (e) {
                        }
                    }
                    else
                    {
                        try
                        {
                            strObject.focus();
                            alert("Please enter value in " + objectId + " field.");
                            return false;
                        } catch (e) {
                        }
                    }
                    // return false;
                }
                else if (objectId == "Customer Name")
                {
                    if (validOlyAlpha(strValue) == false)
                    {
                        alert("Numbers and Special Characters are not allowed in Customer Name.");
                        strObject.value = "";
                        strObject.focus();
                        return false;
                    }
                }
                else if (objectId == "Contact Person")
                {
                    if (validOlyAlpha(strValue) == false)
                    {
                        alert("Numbers and Special Characters are not allowed in Contact Person.");
                        strObject.value = "";
                        strObject.focus();
                        return false;
                    }
                }
                else if (objectId == "Other City")
                {
                    if (validOlyAlpha(strValue) == false)
                    {
                        alert("Numbers and Special Characters are not allowed in Other City.");
                        strObject.value = "";
                        strObject.focus();
                        return false;
                    }
                }
                else if (objectId == "Other State")
                {
                    if (validOlyAlpha(strValue) == false)
                    {
                        alert("Numbers and Special Characters are not allowed in Other State.");
                        strObject.value = "";
                        strObject.focus();
                        return false;
                    }
                }

                else if (objectId == "Email")
                {
                    if (!emailExp.test(strValue))
                    {
                        alert("Please enter valid Email.");
                        strObject.focus();
                        return false;
                    }
                }
                else if (objectId == "Pin Code")
                {
                    if (validsixdigitnumber(strValue) == false)
                    {
                        alert("Pin Code Should Be Numeric and Should be 6 digits");
                        strObject.focus();
                        return false;
                    }
                }
                else if (objectId == "Contact No.")
                {
                    if (validtendigitnumber(strValue) == false)
                    {
                        alert("Contact No. Should Be Numeric and Should be 10 digits");
                        strObject.focus();
                        return false;
                    }
                }
                else if (objectId == "Other Country")
                {
                    if (validOlyAlpha(strValue) == false)
                    {
                        alert("Numbers and Special Characters are not allowed in Other Country.");
                        strObject.value = "";
                        strObject.focus();
                        return false;
                    }
                }
                else if (objectId == "Address")
                {
                    if (imposeMaxLength(strObject) == false)
                    {
                        strObject.focus();
                        alert(objectId + " field length should not be greater than 255 characters.");
                        return false;
                    }
                }
                else if (temp && objectId != "Email")
                {
                    //  if (strObject.tagName == "INPUT" || strObject.tagName=="textarea")
                    {
                        alert("Special Characters are not allowed in " + objectId + " field.");
                        strObject.focus();
                        strObject.value = "";
                        return false;
                    }
                }
            }
            function validOlyAlpha(obj) {
                var regExp = /^[a-zA-Z ]*$/g
                if (regExp.test(obj)) {
                    return true;
                } else {
                    return false;
                }
            }
            function validtendigitnumber(obj) {
                var regExp = /^[0-9]{10}$/g
                if (regExp.test(obj)) {
                    return true;
                } else {
                    return false;
                }
            }
            function validsixdigitnumber(obj) {
                var regExp = /^[0-9]{6}$/g
                if (regExp.test(obj)) {
                    return true;
                } else {
                    return false;
                }
            }
            function checkValueSelect(disObject, divId, txtId)
            {
                if (disObject.value == 'Other')
                {
                    document.getElementById(divId).style.display = "block";
                    document.getElementById(txtId).disabled = false;
                }
                else
                {
                    document.getElementById(divId).style.display = "none";
                    document.getElementById(txtId).disabled = true;
                }
            }

            function doUnload()
            {
                // alert();
                // window.parent.location.href = 'MachineCommissioning.do?option=addDispatch';


            }
        </script>

        <c:if test="${not empty userForm.goLink}">
            <link href="${pageContext.request.contextPath}/css/style.css" rel="stylesheet" type="text/css">
            <link href="${pageContext.request.contextPath}/css/config.css" rel="stylesheet" type="text/css">
        </head>

        <body>
        </c:if>

        <c:if test="${empty userForm.goLink}">

            <div class="breadcrumb_container">
                <ul>
                    <li class="breadcrumb_link"><a href = '<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a> <a HREF='<%=cntxpath%>/UserManagement.do?option=initManageCust'>MANAGE CUSTOMER</a>   </li>
                    <li class="breadcrumb_link">ADD CUSTOMER</li>
                </ul>
            </div>
            <div class="message" id="msg_saveFAILED"></div>
            <br/>

            <%--<table width=70% align=center border="0" cellpadding="0" cellspacing="0">
                <tr height=25>
                    <td align="left" class="heading">&nbsp;
                        <a HREF='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a> >> <a HREF='<%=cntxpath%>/UserManagement.do?option=initManageCust'>MANAGE CUSTOMER</a>  >> ADD CUSTOMER 
                    </td>
                </tr>
                <tr height=25 bgcolor="#FFFFFF" style="border:none;" >
                    <td align= center id="msg_saveFAILED" colspan=3 class="headingSpas">
                        <font color="red"> </font>
                    </td>
                </tr>
            </table>--%>
        </c:if>

        <center>
            <div class="content_right1">
                <div class="con_slidediv1" style="position: relative;width: 100%">
                    <h1>ADD CUSTOMER</h1>
                    <table  width=100% border="0" cellpadding="0" cellspacing="0" ckass="tableStyle">
                        <tr >
                            <td >
                                <form action="UserManagement.do?option=insertcustomer" method="POST" onsubmit="return validateForm();">
                                    <input type="hidden" name="upperLink" value="<a href = '<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a> <a HREF='<%=cntxpath%>/UserManagement.do?option=initManageCust'>MANAGE CUSTOMER</a>@@ADD CUSTOMER"/>
                                    <table width=100% border="0" cellpadding="0" cellspacing="1" class="tableStyle">
                                        <%-- <tr  bgcolor="silver" height="20">
                                             <td align= center style="padding-left: 5px" colspan=2 class="heading">
                                                 ADD CUSTOMER
                                             </td>
                                         </tr>--%>
                                        <tr>
                                            <td width="100%" valign="top" colspan=2 bgcolor="#FFFFFF" >
                                                <table width="100%" border="0" cellpadding="4" cellspacing="0" align="center">
                                                    <tr height="20">
                                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap  align="left">Customer Name:</td>
                                                        <td style="padding-left:10px"  bgcolor="#FFFFFF" align="left" colspan="2">
                                                            <c:if test="${not empty userForm.goLink}">
                                                                <input type='hidden' name='goLink' value='${userForm.goLink}'/>
                                                            </c:if>
                                                            <input type="text" name="name"  id="Customer Name" maxlength="100" class="headingSpas"  style="width:170px" />
                                                        </td>
                                                    </tr>
                                                    <tr height="20">
                                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Address:</td>
                                                        <td style="padding-left:10px"  bgcolor="#FFFFFF" align="left" colspan="2">
                                                            <textarea name="address1"  id="Address" maxlength="255" class="headingSpas"  style="width:170px;height: 35px" ></textarea>
                                                        </td>

                                                    </tr>
                                                    <tr height="20">

                                                        <td  style="padding-left:10px;padding-right:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Contact Person:&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                        <td style="padding-left:10px" bgcolor="#FFFFFF" colspan="" align="left" colspan="2">
                                                            <input type="text" name="firstName"  id="Contact Person" class="headingSpas" maxlength="100" style="width:170px" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">City:</td>
                                                        <td style="padding-left:10px" bgcolor="#FFFFFF" align="left" width="" >
                                                            <select name="city" id="City" style="width:177px" class="headingSpas" onchange="checkValueSelect(this, 'citySpan', 'Other City');">
                                                                <option value="">--Select Here--</option>
                                                                <c:forEach items="${userForm.labelList}" var="cityTemp">
                                                                    <option value="${cityTemp.value}">${cityTemp.label}</option>
                                                                </c:forEach>
                                                                <option value="Other">Other</option>
                                                            </select>
                                                        </td>
                                                        <td width="50%" align="left">
                                                            <span id="citySpan" style="display:none;"><input type="text" disabled="true" name="cityOther"  maxlength="20" id="Other City" style="width:170px" align="left" class="headingSpas"/></span>
                                                        </td>
                                                    </tr>
                                                    <tr height="20">
                                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">State:</td>
                                                        <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                                            <select name="state" id="State" style="width:177px" class="headingSpas" onchange="checkValueSelect(this, 'stateSpan', 'Other State');">
                                                                <option value="">--Select Here--</option>
                                                                <c:forEach items="${userForm.labelList1}" var="stateTemp">
                                                                    <option value="${stateTemp.value}">${stateTemp.label}</option>
                                                                </c:forEach>
                                                                <option value="Other">Other</option>
                                                            </select>
                                                        </td>
                                                        <td align="left">
                                                            <span id="stateSpan" style="display:none;"><input type="text" name="stateOther" disabled="true" maxlength="30" id="Other State" style="width:170px" align="left" class="headingSpas"/></span>

                                                        </td>
                                                    </tr>
                                                    <tr height="20">
                                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Pin Code:</td>
                                                        <td style="padding-left:10px" bgcolor="#FFFFFF" colspan="2" align="left">
                                                            <input type="text" name="pincode"  id="Pin Code" class="headingSpas" maxlength="6" style="width:170px" />
                                                        </td>
                                                    </tr>
                                                    <tr height="20">
                                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Country:</td>
                                                        <td style="padding-left:10px" bgcolor="#FFFFFF" align="left">
                                                            <select name="country" id="Country" style="width:177px" class="headingSpas"  onchange="checkValueSelect(this, 'countrySpan', 'Other Country');">
                                                                <option value="">--Select Here--</option>
                                                                <c:forEach items="${userForm.labelList2}" var="countryTemp">
                                                                    <option value="${countryTemp.value}">${countryTemp.label}</option>
                                                                </c:forEach>
                                                                <option value="Other">Other</option>
                                                            </select>
                                                        </td>
                                                        <td align="left">
                                                            <span id="countrySpan" style="display:none;"><input type="text" name="countryOther" disabled="true" maxlength="20" id="Other Country" style="width:170px" align="left" class="headingSpas"/></span>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Email:</td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                                                        <td style="padding-left:10px" bgcolor="#FFFFFF" align="left" colspan="2">
                                                            <input type="text" name="email"  id="Email" class="headingSpas" maxlength="50" style="width:170px" />
                                                        </td>
                                                    </tr>

                                                    <%--<tr height="20">
                                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Contact Person</td>
                                                        <td style="padding-left:10px" bgcolor="#FFFFFF" colspan="2" align="left">
                                                            <input type="text" name="firstName"  id="Contact Person" class="headingSpas" maxlength="100" style="width:170px" />
                                                        </td>
                                                    </tr>--%>
                                                    <tr height="20">
                                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Contact No.:</td>
                                                        <td style="padding-left:10px" bgcolor="#FFFFFF" colspan="" align="left">
                                                            <input type="text" name="mobile"  id="Contact No." class="headingSpas" maxlength="10" style="width:170px" />
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Customer Type:
                                                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
                                                        <td style="padding-left:10px" bgcolor="#FFFFFF"  align="left" colspan="2">
                                                            <select name="userTypeId" id="Customer Type ID" class="headingSpas" style="width:177px">
                                                                <option value="">--Select Here--</option>
                                                                <c:forEach items="${userForm.labelList3}" var="custtypeidTemp">
                                                                    <option value="${custtypeidTemp.value}">${custtypeidTemp.label}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                    <%-- <tr height="20">
                                                         <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Email</td>
                                                         <td style="padding-left:10px" bgcolor="#FFFFFF" align="left" colspan="2">
                                                             <input type="text" name="email"  id="Email" class="headingSpas" maxlength="50" style="width:170px" />
                                                         </td>
                                                     </tr>--%>
                                                    <%-- <tr height="20">
                                                         <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Customer Type ID</td>
                                                         <td style="padding-left:10px" bgcolor="#FFFFFF"  align="left" colspan="2">
                                                             <select name="userTypeId" id="Customer Type ID" class="headingSpas" style="width:177px">
                                                                 <option value="">--Select Here--</option>
                                                                 <c:forEach items="${userForm.labelList3}" var="custtypeidTemp">
                                                                     <option value="${custtypeidTemp.value}">${custtypeidTemp.label}</option>
                                                                 </c:forEach>
                                                             </select>
                                                         </td>
                                                     </tr>--%>
                                                    <tr height="20">
                                                        <td colspan="3" style="padding-left:10px" align="center" bgcolor="#FFFFFF">
                                                            <input type=submit name="submitBtn" id="submitBtn" value="Submit"/>
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>
                                    </table>
                                </form>
                            </td>
                        </tr>
                    </table>
                </div></div>
        </center>

        <c:if test="${not empty userForm.goLink}">
        </body>
    </html>
</c:if>
