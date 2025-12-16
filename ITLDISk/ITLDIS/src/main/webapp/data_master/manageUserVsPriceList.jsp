<%-- 
    Document   : manageUserVsPriceList
    Created on : Nov 19, 2015, 11:19:54 AM
    Author     : mahendra.rawat
--%>


<%@taglib prefix="html" uri="http://struts.apache.org/tags-html"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@taglib uri="http://jsptags.com/tags/navigation/pager" prefix="pg" %>
<%@ page import="java.util.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String cntxpath = request.getContextPath();
%>

<script type="text/javascript" language="javascript">
    var contextPath = '${pageContext.request.contextPath}';
</script>

<script language="JavaScript" type="text/javascript">
    
    function submitForm()
    {
            if( document.getElementById('userTypeId').value==null || document.getElementById('userTypeId').value==''){
                document.getElementById("msg_saveFAILED").innerHTML="Please Select User Type";
                return false;
            }
            if( document.getElementById('userId').value==null || document.getElementById('userId').value==''){
                document.getElementById("msg_saveFAILED").innerHTML="Please Select User";
                return false;
            }

            if( document.getElementById('priceId').value==null || document.getElementById('priceId').value==''){
                document.getElementById("msg_saveFAILED").innerHTML="Please Select Price";
                return false;
            }
            document.getElementById("manageUserType").submit();

        }

      

        function selectUserType(obj,nameObj,nameobj2)
        {
            var objjCode = obj.value;
            var strURL="<%=cntxpath%>/data_master/getUserTypeDetails.jsp?objjCode=" + objjCode+"&nameObj="+nameObj;
            xmlHttp=new XMLHttpRequest();
            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    res = xmlHttp.responseText;
                    document.getElementById(nameObj).innerHTML =res;
                }
            }
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);

            ///////////////////

            strURL="<%=cntxpath%>/data_master/getUserTypeDetails.jsp?objjCode=" + objjCode+"&nameObj="+nameobj2;
            xmlHttp1=new XMLHttpRequest();
            xmlHttp1.onreadystatechange = function()
            {
                if (xmlHttp1.readyState == 4 || xmlHttp1.readyState == "complete")
                {
                    res = xmlHttp1.responseText;
                    document.getElementById(nameobj2).innerHTML =res;
                }
            }
            xmlHttp1.open("POST", strURL, true);
            xmlHttp1.send(null);
        }

        function selectUser1(obj,spain2,userType)
        {
            var objjCode = obj.value;
            var strURL="<%=cntxpath%>/data_master/UserPrice.jsp?objjCode=" + objjCode+"&userType="+userType+"&spain2="+spain2;
            xmlHttp=new XMLHttpRequest();
            xmlHttp.onreadystatechange = function()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    res = xmlHttp.responseText;
                    document.getElementById('spain2').innerHTML =res;
                }
            }
            xmlHttp.open("POST", strURL, true);
            xmlHttp.send(null);

        }
   
</script>



<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href ='<%=cntxpath%>/UserManagement.do?option=initUserInformation'>USER MANAGEMENT</a></li>
            <li class="breadcrumb_link">MANAGE USER Vs PRICE LIST</li>

        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED">${successmsg}</div>

    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1>MANAGE USER Vs PRICE LIST</h1>
                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <tr bgcolor="#FFFFFF">
                        <td width=100% valign="top" >
                            <form action="masterAction.do?option=saveOrUpdateManageUserType" method="post" id="manageUserType" onsubmit="return false;" >
                                <input type="hidden" name="upperLink" value="<a href='<%=cntxpath%>/UserManagement.do?option=initUserInformation'>USER MANAGEMENT </a>"/>
                                <table width=100% border="0" cellpadding="0" cellspacing="0" >
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0" >
                                                <tr height="30">
                                                    <td style="padding-left:350px" width="40%" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left" >Select User Type</td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                        <select name='userType' id='userTypeId' class="headingSpas" style='width:180px !important' onchange="selectUserType(this,'spain1','spain2');">
                                                            <option value='' >--Select Here--</option>
                                                            <c:forEach items='${userTypeList}'  var='userType'  varStatus='status'>
                                                                <option value='${userType.value}' title="${userType.label}" label="${userType.label}">${userType.label}</option>
                                                            </c:forEach>
                                                        </select>
                                                    </td>
                                                </tr>

                                                <tr height="30">
                                                    <td style="padding-left:350px" width="40%" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">Select User</td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                        <span id="spain1">
                                                            <select name='user' id='userId' class="headingSpas" style='width:180px !important' onchange="selectUser1(this,'spain2',document.getElementById('userTypeId').value)">
                                                                <option value='' >--Select Here--</option>
                                                                <c:forEach items='${userList}'  var='user'  varStatus='status'>
                                                                    <option value='${user.value}' title="${user.value}" label="${user.label}[ ${user.value} ]">'${user.label} [ ${user.value} ]'</option>
                                                                </c:forEach>
                                                            </select>
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr height="30">
                                                    <td style="padding-left:350px" width="40%"bgcolor="#FFFFFF" class="headingSpas" nowrap align="left" >Select Price</td>
                                                    <td style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                        <span id="spain2">
                                                            <select name='price' id='priceId' class="headingSpas" style='width:180px !important'>
                                                                <option value='' >--Select Here--</option>
                                                                <c:forEach items='${priceList}'  var='price'  varStatus='status'>
                                                                    <option value='${price.value}' title="${price.label}" label="${price.label}">${price.label}</option>
                                                                </c:forEach>
                                                            </select>
                                                        </span>
                                                    </td>
                                                </tr>

                                                <tr height="20">
                                                    <td colspan="2" style="padding-left:509px;padding-top: 5px" align="left" bgcolor="#FFFFFF">
                                                        <input type='button' name="add" id="submitId" value="Submit" class="headingSpas" onclick="submitForm()"/>
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
            </div>
        </div>
    </center>
</div>

