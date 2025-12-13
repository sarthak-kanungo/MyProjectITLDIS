<%-- 
    Document   : v_modify_customer
    Created on : Feb 21, 2014, 4:01:29 PM
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
<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>
<script language="javascript" src="${pageContext.request.contextPath}/js/autosuggest.js"></script>
<script language="javascript" src="${pageContext.request.contextPath}/js/suggestions.js"></script>
<script type="text/javascript">
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
    function imposeMaxLength(obj) {
        var mlength = obj.getAttribute ? parseInt(obj.getAttribute("maxlength")) : ""
        if (obj.getAttribute && obj.value.length > mlength) {
            //obj.value = obj.value.substring(0, mlength)
            return false;
        }
    }

    function getCustomerDetailsAjax()
    {
        var name = document.getElementById('Customer NameTxt');

        var strURL = contextPath + "/UserManagement.do?option=getCustomerDetAjax&name=" + name.value;
        var xmlHttp = GetXmlHttpObject();
        xmlHttp.onreadystatechange = function()
        {
            stateChangedCustomer(xmlHttp);
        }
        xmlHttp.open("POST", strURL, true);
        xmlHttp.send(null);
    }

    function stateChangedCustomer(xmlHttp)
    {
        if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
        {
            document.getElementById("customerDetailsection").innerHTML = xmlHttp.responseText;
        }
    }
    function checkValueSelect(disObject, divId,txtId)
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
    function validateForm()
    {
        var elementArr = new Array('Customer Name', 'Address', 'Contact Person', 'City','Other City', 'State','Other State','Pin Code','Country','Other Country',  'Email', 'Contact No.', 'Customer Type ID', 'Filled On', 'Filled By');
        var strValue = null;
        var strObject = null;

        for (var i = 0; i < elementArr.length; i++)
        {
            strObject = document.getElementById(elementArr[i]);

            if (strObject && strObject.disabled==false)
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
            //  return false;
        }
        else if (objectId == "Customer Name")
        {
            if (validOlyAlpha(strValue)== false)
            {
                alert("Numbers and Special Characters are not allowed in Customer Name.");
                strObject.value = "";
                strObject.focus();
                return false;
            }
        }
        else if (objectId == "Contact Person")
        {
            if (validOlyAlpha(strValue)== false)
            {
                alert("Numbers and Special Characters are not allowed in Contact Person.");
                strObject.value = "";
                strObject.focus();
                return false;
            }
        }
        else if (objectId == "Other City")
        {
            if (validOlyAlpha(strValue)== false)
            {
                alert("Numbers and Special Characters are not allowed in Other City.");
                strObject.value = "";
                strObject.focus();
                return false;
            }
        }
        else if (objectId == "Other State")
        {
            if (validOlyAlpha(strValue)== false)
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
            if (validOlyAlpha(strValue)== false)
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
            // if (strObject.tagName == "INPUT")
            {
                alert("Special Characters are not allowed in " + objectId + " field.");
                strObject.value = "";
                strObject.focus();
                return false;
            }
        }
    }
    function validOlyAlpha(obj) {
        var regExp =  /^[a-zA-Z ]*$/g
        if (regExp.test(obj)) {
            return true;
        } else {
            return false;
        }
    }
    function validsixdigitnumber(obj) {
        var regExp =  /^[0-9]{6}$/g
        if (regExp.test(obj)) {
            return true;
        } else {
            return false;
        }
    }
    function validtendigitnumber(obj) {
        var regExp =  /^[0-9]{10}$/g
        if (regExp.test(obj)) {
            return true;
        } else {
            return false;
        }
    }
    function findselected(val) {
        
        var cn = document.getElementById('Customer Name');
        var ad = document.getElementById('Address');
        var cp = document.getElementById('Contact Person');
        var ct = document.getElementById('City');
        var st = document.getElementById('State');
        var pn = document.getElementById('Pin Code');
        var em = document.getElementById('Email');
        var con = document.getElementById('Country');
        var cont = document.getElementById('Contact No.');
        var ctId = document.getElementById('Customer Type ID');
        if(val == 'N'){
            alert("Are you sure to In-Activate Customer Details ");
            cn.disabled = true
            ad.disabled = true
            cp.disabled = true
            ct.disabled = true
            st.disabled = true
            pn.disabled = true
            em.disabled = true
            con.disabled = true
            cont.disabled = true
            ctId.disabled = true
        }
        else{
            cn.disabled = false
            ad.disabled = false
            cp.disabled = false
            ct.disabled = false
            st.disabled = false
            pn.disabled = false
            em.disabled = false
            con.disabled = false
            cont.disabled = false
            ctId.disabled = false
        }
    }
</script>


<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='<%=cntxpath%>/masterAction.do?option=initOptions'>MASTERS</a><a HREF='<%=cntxpath%>/UserManagement.do?option=initManageCust'>MANAGE CUSTOMER</a> MODIFY CUSTOMER </li>
        </ul>
    </div>
    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <div class="content_right1">
            <div class="con_slidediv1" style="position: relative;width: 100%">
                <h1>MODIFY CUSTOMER</h1>

                <center>
                    <table width=100%  border="0" cellpadding="0" cellspacing="0">
                      
                        <tr valign="top" height="45" >
                            <td class="tdStyle" nowrap align="left">Customer Name<%--</td>--%>
                                <%--<td style="padding-left:10px" colspan="2" bgcolor="#FFFFFF" align="left">--%>   &nbsp;&nbsp;&nbsp; &nbsp; &nbsp;
                                <input type="text" name="name" maxlength="50" id="Customer NameTxt"  style="width:170px" />
                                &nbsp;
                                <a href='#'><img src='${pageContext.request.contextPath}/images/arrdown.gif' border='0' alt='Get Suggestions' style='width:15px' onclick='getSuggestionCustomerNames("Customer NameTxt", document.getElementById("img"));'/><img alt=""  id='img' style='visibility:hidden;' border='0' src='${pageContext.request.contextPath}/images/load.gif'/>
                                </a>
                                <input type="button" value="Go" class="headingSpas" onclick="getCustomerDetailsAjax()"/>
                            </td>
                        </tr>
                    </table>

                    <center>
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" >
                            <tr><td bgcolor="#FFFFFF">
                                    <table width="70%" border="0" cellpadding="0" cellspacing="0">
                                        <tr height="10"></tr>
                                        <tr><td colspan="">
                                                <span id="customerDetailsection">
                                                </span>
                                            </td></tr>
                                    </table>
                                </td></tr>
                        </table>
                    </center>

                </center>
            </div>
        </div>

    </center>


</div>