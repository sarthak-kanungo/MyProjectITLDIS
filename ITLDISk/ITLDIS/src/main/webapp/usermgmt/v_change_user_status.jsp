<%--
    Document   : v_change_user_status
    Created on : Feb 15, 2014, 6:03:36 PM
    Author     : manish.kishore
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%String cntxpath = request.getContextPath();%>
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';


    function validate()
    {
        var elementArr = new Array('User ID');
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

        // document.getElementById('submitBtn').disabled = true;
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
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Please select value in " + objectId + " field.";
                return false;
            }
            else
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Please enter value in " + objectId + " field.";
                return false;
            }
            return false;
        }

        else if (temp)
        {
            if (strObject.tagName == "INPUT")
            {
                strObject.focus();
                document.getElementById("msg_saveFAILED").innerHTML="Special Characters are not allowed in " + objectId + " field.";
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


    function getPresentStatusAjax()
    {
        var userId = document.getElementById('User ID');

        var strURL = contextPath + "/UserManagement.do?option=getStatusAjax&userId=" + userId.value + "&tm=" + new Date().getTime();
        var xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            stateChangedUser(xmlHttp);
        }
        xmlHttp.open("POST", strURL, true);
        xmlHttp.send(null);
    }

    function stateChangedUser(xmlHttp)
    {
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
            //alert(xmlHttp.responseText);
            if (xmlHttp.responseText.indexOf("notexists") != -1)
            {
                document.getElementById("msg_saveFAILED").innerHTML="User ID doesn't exist in database.";
                return false;
            }
            else
            {
                var splitArr = xmlHttp.responseText.split("@@##");

                document.getElementById('spanIdName').innerHTML = splitArr[0];

                if (splitArr[1] == "1")
                    document.getElementById('spanPresentStatus').innerHTML = "Active";
                else
                    document.getElementById('spanPresentStatus').innerHTML = "Inactive";
            }
        }
    }
</script>


<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href = '<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
            <li class="breadcrumb_link"><a href = '<%=cntxpath%>/ManageUser.do' >USER MANAGEMENT CONSOLE</a></li>
            <li class="breadcrumb_link">CHANGE USER STATUS</li>
        </ul>
    </div>
    
            <br/>        

    <div class="message" id="msg_saveFAILED"></div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1>CHANGE USER STATUS</h1>

                <form name=form1 method="post" action="${pageContext.request.contextPath}/UserManagement.do" onsubmit="return validate()">
                    <input type="hidden" name="option" value="updateUserStatus"/>
                    <table width=100% border="0" cellpadding="5" cellspacing="1" >
                        <tr>
                            <td width="100%" valign="top" colspan=2 bgcolor="#FFFFFF" >
                                <table width="100%" border="0" cellpadding="4" cellspacing="0" >
                                    <tr height="20">
                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">User ID</td>
                                        <td style="padding-left:10px" colspan="2" bgcolor="#FFFFFF" align="left">
                                            <input type="text" name="userId" maxlength="15" id="User ID" class="headingSpas" style="width:170px" />
                                            &nbsp;
                                            <a href='#'>
                                                <img src='${pageContext.request.contextPath}/images/arrdown.gif' border='0' alt='Get Suggestions' style='width:15px' onclick='validate();
        getSuggestionsUsers("User ID", document.getElementById("img"));'/>
                                                <img alt=""  id='img' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/images/load.gif'/>
                                            </a>

                                            <input type="button" value="Get Status" class="headingSpas" onclick="getPresentStatusAjax()"/>
                                        </td>
                                    </tr>


                                    <tr height="20">
                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">User Name</td>
                                        <td style="padding-left:10px" colspan="2" class="headingSpas" bgcolor="#FFFFFF" align="left">
                                            <span id="spanIdName"></span>
                                        </td>
                                    </tr>

                                    <tr height="20">
                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Present Status</td>
                                        <td style="padding-left:10px" colspan="2" class="headingSpas" bgcolor="#FFFFFF" align="left">
                                            <span id="spanPresentStatus"></span>
                                        </td>
                                    </tr>
                                    <tr height="20">
                                        <td  style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Select New Status</td>
                                        <td style="padding-left:10px" colspan="2" bgcolor="#FFFFFF" align="left">
                                            <select name= status class="headingSpas">
                                                <option value='1'>Active</option>
                                                <option value='0'>Inactive</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr height="20">
                                        <td colspan="3" style="padding-left:10px" align="center" bgcolor="#FFFFFF">
                                            <input type=submit name="submitBtn" class="headingSpas" id="submitBtn" value="Change Status"/>
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

