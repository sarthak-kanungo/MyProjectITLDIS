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
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
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
            var regexp=  /^([a-zA-Z0-9]){7,}$/;
        if( document.getElementById('oldPassword').value==null || document.getElementById('oldPassword').value==''){
            document.getElementById("msg_saveFAILED").innerHTML="Please Enter Old Password";
            return false;
        }
        if( document.getElementById('newPassword').value==null || document.getElementById('newPassword').value==''){
            document.getElementById("msg_saveFAILED").innerHTML="Please Enter New Password";
            document.getElementById('newPassword').focus();
            return false;
        }

        if( document.getElementById('retypePassw').value==null || document.getElementById('retypePassw').value==''){
            document.getElementById("msg_saveFAILED").innerHTML="Please  Retype Password";
            document.getElementById('retypePassw').focus();
            return false;
        }
        if( !document.getElementById('newPassword').value.match(regexp)){
            document.getElementById("msg_saveFAILED").innerHTML="Please Enter Valid Password";
            document.getElementById('newPassword').focus();
            return false;
        }
        if(document.getElementById('retypePassw').value!=document.getElementById('newPassword').value){
            document.getElementById("msg_saveFAILED").innerHTML="Value in Retype Password field Should be Same";
            document.getElementById('retypePassw').focus();
            return false;
        }
        document.getElementById("changePasswordID").submit();

    }

</script>



<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href ='<%=cntxpath%>/UserManagement.do?option=initUserInformation'><bean:message key="label.common.usermanagement" /></a></li>
            <li class="breadcrumb_link"><bean:message key="label.common.usermanagement.changePassword" /></li>

        </ul>
    </div>
    <br/>

    <div class="message" id="msg_saveFAILED">${successmsg}</div>

    <center>

        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1><bean:message key="label.common.usermanagement.changePassword" /></h1>
                <table cellspacing=1 cellpadding=5 border=0 width=100% >
                    <tr bgcolor="#FFFFFF">
                        <td width=100% valign="top" >
                            <form action="UserManagement.do?option=changePassword" method="post" id="changePasswordID" onsubmit="return false;">
                                <table width=100% border="0" cellpadding="0" cellspacing="0" >
                                    <tr>
                                        <td width="100%" valign="top">
                                            <table width="100%" border="0" cellpadding="0" cellspacing="0" >
                                               
                                                   
                                                <tr height="30">
                                                    <td width="10%" style="padding-right:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="right"><bean:message key="label.common.usermanagement.oldPassword" /><span style="color:red;">*</span></td>
                                                    <td width="20%" style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                        <input type="password" name='oldPassword' id='oldPassword' class="headingSpas" style='width:150px !important' />
                                                    </td>
                                                </tr>

                                                <tr height="30">
                                                    <td width="20%" style="padding-right:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="right"><bean:message key="label.common.usermanagement.newPassword" /><span style="color:red;">*</span></td>
                                                    <td width="20%" style="padding-left:10px; position:relative" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                        <span id="spain1">
                                                            <input type="password" name='newPassword' id='newPassword' class="headingSpas" style='width:150px !important'/>
                                                        </span>
                                                    </td>
                                                </tr>
                                                <tr height="30">
                                                    <td width="20%" style="padding-right:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="right"><bean:message key="label.common.usermanagement.reTypePassword" /><span style="color:red;">*</span></td>
                                                    <td width="20%" style="padding-left:10px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="left">
                                                        <span id="spain2">
                                                            <input type="password" name='retypePassw' id='retypePassw' class="headingSpas" style='width:150px !important'/>
                                                        </span>
                                                    </td>
                                                </tr>

                                                <tr height="20">
                                                    <td style="padding-right:50px" bgcolor="#FFFFFF" class="headingSpas" nowrap align="right"/>
                                                    <td  style="padding-left:10px;padding-bottom: 5px" align="left" bgcolor="#FFFFFF">
                                                        <input type='button' name="add" id="submitId" value="Submit" class="headingSpas" onclick="submitForm()"/>
                                                    </td>
                                                </tr>
                                                <tr height="20">
                                                    <td colspan="2" align="left"><span style="color:red; padding-left:50px; font-size: 11px;">*Password Criteria.</span></td>
                                                </tr>
                                                <tr height="20">
                                                    <td colspan="2" align="left"><span style="color:blue; padding-left:50px; font-size: 11px;">*Length should be greater than 7 character.</span></td>
                                                </tr>
                                                <tr height="20">
                                                    <td colspan="2" align="left"><span style="color:blue; padding-left:50px; font-size: 11px;">*Must not contains any special character.</span></td>

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
