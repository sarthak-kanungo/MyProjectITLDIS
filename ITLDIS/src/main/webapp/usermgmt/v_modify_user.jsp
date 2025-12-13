<%--
    Document   : v_modify_user
    Created on : Feb 14, 2014, 5:23:16 PM
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
</script>


<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>

<script type="text/javascript" language="javascript">


    function validateForm()
    {

        var elementArr = new Array('Region Name', 'User Type', 'Designation', 'Company Name', 'User ID', 'Password', 'Re-Type Password', 'Name', 'Address1', 'Address2', 'City', 'Pin Code', 'State', 'Country', 'Phone No', 'Mobile', 'Email');
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
        if (document.getElementById('submitBtn'))
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
                alert("Please select value in " + objectId + " field.");
                return false;
            }
            else
            {
                strObject.focus();
                alert("Please enter value in " + objectId + " field.");
                return false;
            }
            return false;
        }
        else if (objectId == "User ID")//|| objectId == "Customer Name" || objectId == "Customer Contact Person"
        {

            if (!characteronlyWithoutSpaceExp.test(strValue))
            {
                strObject.focus();
                alert("Space and Special Characters are not allowed in " + objectId + " field .");
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
        else if (objectId == "Designation" || objectId == "Name")//|| objectId == "Customer Name" || objectId == "Customer Contact Person"
        {

            if (!characteronlyExp.test(strValue))
            {
                strObject.focus();
                alert("Numbers and Special Characters are not allowed in " + objectId + " field .");
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
                alert(objectId + " field can not be greater than or less than 10 characters.");
                return false;
            }
        }

        else if (objectId == "Re-Type Password")//'', ''
        {
            if (document.getElementById('Password').value != document.getElementById('Re-Type Password').value)
            {
                alert('Re-Type Password must match with Password.');
                return false;
            }
        }
        else if (temp && objectId != "Email")
        {
            if (strObject.tagName == "INPUT")
            {
                strObject.focus();
                alert("Special Characters are not allowed in " + objectId + " field.");
                return false;
            }
        }
    }
    function GetXmlHttpObject()
    {
        var objXmlHttp = null;
        if (navigator.userAgent.indexOf('Opera') >= 0)
        {
            xmlHttp = new XMLHttpRequest();
            return xmlHttp;
        }
        if (navigator.userAgent.indexOf('MSIE') >= 0)
        {
            var strName = 'Msxml2.XMLHTTP';
            if (navigator.appVersion.indexOf('MSIE 5.5') >= 0)
            {
                strName = 'Microsoft.XMLHTTP';
            }
            try
            {
                objXmlHttp = new ActiveXObject(strName);
                // objXmlHttp.onreadystatechange = handler;
                return objXmlHttp;
            }
            catch (e)
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


    function getUserDetailsAjax()
    {
        var userId = document.getElementById('User ID');

        var strURL = contextPath + "/UserManagement.do?option=getUserAjax&userId=" + userId.value + "&tm=" + new Date().getTime();
        var xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            stateChangedUser(xmlHttp, userId.value);
        }
        xmlHttp.open("POST", strURL, true);
        xmlHttp.send(null);
    }

    function stateChangedUser(xmlHttp, userId)
    {
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
            document.getElementById("userDataDiv").innerHTML = xmlHttp.responseText;
            if (userId == '') {
                document.getElementById("userData_Tr").style.display = 'none';
            } else {
                document.getElementById("userData_Tr").style.display = 'block';
            }
        }
    }

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


</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href = '<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
            <li class="breadcrumb_link"><a href = '<%=cntxpath%>/ManageUser.do' >USER MANAGEMENT CONSOLE</a></li>
            <li class="breadcrumb_link">MODIFY USER</li>

        </ul>
    </div>
    <br/>        

    <div class="message" id="msg_saveFAILED"></div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1>MODIFY USER</h1>

                <form action="UserManagement.do" method="POST" onsubmit="return validateForm();">
                    <input type="hidden" name="option" value="updateUser"/>
                    <input type="hidden" name="upperLink" value="<a href = '<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a> <a href = '<%=cntxpath%>/UserManagement.do?option=initUser'> MANAGE USERS</a>@@MODIFY USER"/>
                    <table width=100% border="0" cellpadding="5" cellspacing="1" >
                        <tr>
                            <td width="100%" valign="top" colspan=2 bgcolor="#FFFFFF" >
                                <table width="100%" border="0" cellpadding="4" cellspacing="0" >
                                    <tr height="20">
                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">User ID</td>
                                        <td style="padding-left:10px" colspan="2" bgcolor="#FFFFFF" align="left">
                                            <input type="text" name="userId" maxlength="15" id="User ID"  style="width:170px" />
                                            &nbsp;
                                            <a href='#'><img src='${pageContext.request.contextPath}/images/arrdown.gif' border='0' alt='Get Suggestions' style='width:15px' onclick='getSuggestionsUsers("User ID", document.getElementById("img"));'/><img alt=""  id='img' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/images/load.gif'/>
                                            </a>

                                            <input type="button" value="Go" onclick="getUserDetailsAjax()"/>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <tr id="userData_Tr" style="display: none">
                            <td width="100%" valign="top" colspan=2 bgcolor="#FFFFFF" >
                                <span id="userDataDiv">


                                </span>
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
        </div>
    </center>
</div>

