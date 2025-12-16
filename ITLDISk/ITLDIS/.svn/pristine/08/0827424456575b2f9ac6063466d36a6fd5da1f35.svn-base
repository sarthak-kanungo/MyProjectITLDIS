<%-- 
    Document   : assingRoles
    Created on : Apr 9, 2014, 4:39:56 PM
    Author     : tarun.lal
--%>

<%@page import="dao.UserManagementDAO"%>
<%@page contentType="text/html" pageEncoding="utf-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" pageEncoding="UTF-8"  />
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>

<c:set var="contextPath" value='<%=request.getContextPath()%>'/>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
//            String cntxpath = request.getContextPath();
%>
<%--////////////////////////////INITIAL PAGE TO SELECT USER TYPE////////////////////////////////--%>
<script language="javascript">
//     var contextPath = '${pageContext.request.contextPath}';
    function validate()
    {
        var user_type=document.getElementById('user_type_desc').value;
        if(user_type=='0')
        {
            document.getElementById("msg_saveFAILED").innerHTML="Please select user type to assign Roles & Responsibility";
            document.getElementById('user_type_desc').focus();
            return false;
        }
        document.getElementById('assignForm').submit();
    }


</script>
<%--////////////////////////////XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX////////////////////////////////--%>
<%--///////////////For Assigning Roles /////////////--%>
<script language = javascript>
    function validateCheck(cbName)
    {
        var statusCheck;
        var posIndx = cbName.indexOf('rd');
        if(posIndx == -1 )
        {
            statusCheck = eval("document.userForm."+cbName).checked;
        }
        else
        {
            statusCheck = true;
        }
        if(!statusCheck)
        {
            var cnt = 0;
            var radCnt = 0;
            var radioName = cbName+'_rd_'+radCnt;
            radCnt++;
            while(eval("document.userForm."+radioName))
            {
                var len = eval("document.userForm."+radioName).length;
                for (var i=0; i<len; i++)
                {
                    eval("document.userForm."+radioName+'['+i+']').checked = false;
                }
                radioName = cbName+'_rd_'+radCnt;
                radCnt++;
            }
            var checkBoxName = cbName+'_'+cnt;
            cnt++;
            while(eval("document.userForm."+checkBoxName))
            {
                eval("document.userForm."+checkBoxName).checked = false;
                validateCheck(checkBoxName);
                var radCnt = 0;
                var radioName = cbName+'_rd_'+radCnt;
                radCnt++;
                while(eval("document.userForm."+radioName))
                {
                    var len = eval("document.userForm."+radioName).length;
                    for (var i=0; i<len; i++)
                    {
                        eval("document.userForm."+radioName+'['+i+']').checked = false;
                    }
                    radioName = cbName+'_rd_'+radCnt;
                    radCnt++;
                }
                checkBoxName = cbName+'_'+cnt;
                cnt++;
            }
        }
        else
        {
            var indx= cbName.lastIndexOf('_');
            if(indx != -1)
            {
                var cbName = cbName.substring(0,indx);
                if(eval("document.userForm."+cbName))
                {
                    eval("document.userForm."+cbName).checked = true;
                    validateCheck(cbName);
                }
                else
                {
                    var radIndx= cbName.lastIndexOf('_');
                    if(radIndx != -1)
                    {
                        cbName = cbName.substring(0,radIndx);
                        eval("document.userForm."+cbName).checked = true;
                        validateCheck(cbName);
                    }
                }
            }
        }
    }
    function findCheckedValues(cbName)
    {
        var statusCheck = false;
        var posIndx = cbName.indexOf('rd');
        if(posIndx == -1 )
        {
            statusCheck = eval("document.userForm."+cbName).checked;
            if(statusCheck)
            {
                document.userForm.checkedValues.value = document.userForm.checkedValues.value + '_' + eval("document.userForm."+cbName).value;

            }
        }
        if(statusCheck)
        {
            var cnt = 0;
            var radCnt = 0;
            var radioName = cbName+'_rd_'+radCnt;
            radCnt++;
            while(eval("document.userForm."+radioName))
            {
                var len = eval("document.userForm."+radioName).length;
                for (var i=0; i<len; i++)
                {
                    if (eval("document.userForm."+radioName+'['+i+']').checked)
                    {
                        document.getElementById('userForm').checkedValues.value = document.getElementById('userForm').checkedValues.value + '_' + eval("document.getElementById('userForm')."+radioName+'['+i+']').value
                    }
                }
                radioName = cbName+'_rd_'+radCnt;
                radCnt++;
            }
            var checkBoxName = cbName+'_'+cnt;
            cnt++;
            while(eval("document.userForm."+checkBoxName))
            {
                if (eval("document.userForm."+checkBoxName).checked)
                {
                    findCheckedValues(checkBoxName);
                }
                checkBoxName = cbName+'_'+cnt;
                cnt++;
            }
        }
    }
    function validate_f1()
    {
        var confrm = confirm('Do You Want To Assign The Selected Roles?')
        if(confrm)
        {
            var noOfcb = document.userForm.noOfcb.value;
            for(i=0;i<noOfcb;i++)
            {
                findCheckedValues('cb_'+i);
            }
            return true;
            document.userForm.submit();
        }
        else{
            return false;
        }
//        document.assignRolestoUser.submit();
    }
</script>

<%--/////////////////////////////////XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX///////////////////////////////--%>
<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul>
            <li class="breadcrumb_link"><a href='${contextPath}/UserManagement.do?option=initUserInformation'><bean:message key="label.common.usermanagement" /></a></li>
            <li class="breadcrumb_link"><bean:message key="label.common.assignRoles" /></li>
        </ul>
    </div>
    <br/>
    <div class="message" id="msg_saveFAILED"></div>
    <center>
        <c:choose>
            <c:when test="${not empty UserTypeList}">
                <%--////////////////////////////INITIAL PAGE TO SELECT USER TYPE////////////////////////////////--%>
                <div class="content_right1">
                    <form action="UserManagement.do" id="assignForm" method="post" >
                        <input id="option" type="hidden" name="option" value="assignRolesResponsibilities"/>
                        <div class="con_slidediv1" style="position: relative;width: 100%">
                            <h1><bean:message key="label.common.assignRoles" /></h1>
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#333333">
                                <tr>
                                    <td height="100" align="center" valign="top" bgcolor="#FFFFFF">
                                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                                <td height="70" valign="top"  style="padding-top:10px">
                                                    <div  align="center">
                                                        <table width="100%" border="0" cellpadding="1" cellspacing="1" bgcolor="#cccccc">
                                                            <tr>
                                                                <td width="25%" height="25" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:10px">Select User Type <span class="mandatory">*</span></td>
                                                                <td width="75%" align="left" bgcolor="#FFFFFF" class="headingSpas" style="padding-left:10px">
                                                                    <select id="user_type_desc" name="user_type_desc" styleId="user_type_desc"  style="width:200px!important;">
                                                                        <option value="0" >-- Select Here --</option >
                                                                        <c:forEach items='${UserTypeList}'  var='labelValue'  varStatus='status'>
                                                                             <option value='${labelValue.value}' title='${labelValue.label}'>${labelValue.label}</option>
                                                                         </c:forEach>
                                                                    </select>

                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td colspan="2" align="center" bgcolor="#FFFFFF" class="headingSpas" style="padding-top:15px">
                                                                    <input type="button" name="Next" id="Next" value=" Next " class="headingSpas" onclick="validate()" />
                                                                </td>
                                                            </tr>
                                                        </table>
                                                        <br />
                                                        <div align="left"><span class="mandatory">* Mandatory Field</span></div></div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </form>
                </div>
                <%--////////////////////////////XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX////////////////////////////////--%>
            </c:when>
            <c:otherwise>
                <%--////////////////////////////Assigning Roles Functionalities////////////////////////////////--%>
                <%@ page import="java.sql.*,java.util.*,java.text.*,dao.masterDAO,dbConnection.dbConnection" %>
                <%
                            Connection conn = null;
                            Vector userFun = null;
                            Vector funcVec = null;
                            Statement stmt = null;
                            String user_type = "";
                            String user_type_id = "";
                            int cbCounter = 0;				// Check Box counter
                            int main_func_id = 0;			// Main Functionality
                            String main_func_desc = "";		// Main Functionality Description
                            boolean checkSubfunc = false;
                            ResultSet rs = null;

                            conn = new dbConnection().getConnection();
                            UserManagementDAO obj = new UserManagementDAO(conn);
                            stmt = conn.createStatement();

                            user_type = "" + request.getAttribute("userType");
                            user_type_id = "" + request.getAttribute("userTypeId");
                            userFun = (Vector) request.getAttribute("userFun");

                            funcVec = new Vector();			// Functionality Vector


                %>
                <div class="content_right1">
                    <html:form styleId="userForm"  action="UserManagement.do" method="post"  >
                        <html:hidden property="option" value="assignRolesToUserType"/>
                        <!--<input type="hidden" id="option" name="option" value="assignRolesToUserType"/>-->
                        <input type="hidden" name="upperLink" value="<a href='${pageContext.request.contextPath}/UserManagement.do?option=initUserInformation'>User Management</a>@@Assign Roles to User Type"/>
                        <div align="center" class="con_slidediv1" style="position: relative;width: 100%">
                            <h1>ASSIGN ROLES TO "<%=user_type.toUpperCase()%>" </h1>
                            <table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#333333">
                                <tr>
                                    <td height="100" align="center" valign="top" bgcolor="#FFFFFF">
                                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                                <td height="70" valign="top"  style="padding-top:10px">
                                                    <div align="center"  style="overflow:auto ; height:300px ; width:100%">
                                                        <table width=95% border=0 align=center cellspacing=1 cellpadding=1 bordercolor=#333333 bgcolor=#cccccc>

                                                            <%
                                                                        rs = stmt.executeQuery("select distinct UM_spas104.MAIN_FUNC_ID,UM_spas102.FUNC_DESC from UM_spas104, UM_spas102  where (UM_spas102.FUNC_ID = UM_spas104.MAIN_FUNC_ID) order by UM_spas104.MAIN_FUNC_ID");
                                                                        if (rs.next()) {
                                                                            do {
                                                                                main_func_id = rs.getInt(1);	// Main Functionality

                                                                                main_func_desc = rs.getString(2);	// Main Functionality Description

                                                                                // Checking If funtionality has already been printed
                                                                                if (!funcVec.contains("" + main_func_id)) {
                                                                                    funcVec.add("" + main_func_id);

                                                                                    String cbName = "cb_" + cbCounter;
                                                                                    cbCounter++;
                                                            %>
                                                            <tr><td height=20 align=left bgcolor="#FFFFFF" class="headingSpas">

                                                                    <%if (userFun.contains("" + main_func_id)) {%>
                                                                    <INPUT TYPE='checkbox' CLASS="headingSpas" NAME='<%=cbName%>' value=<%= main_func_id%> onclick=validateCheck('<%=cbName%>')  checked>
                                                                    <% } else {%>
                                                                    <INPUT TYPE='checkbox' CLASS="headingSpas" NAME='<%=cbName%>' value=<%= main_func_id%> onclick=validateCheck('<%=cbName%>')>
                                                                    <% }
                                                                                                                                                                            out.println(main_func_desc);
                                                                    %>
                                                                </td></tr>
                                                                <%
                                                                                        String frontSpaces = "&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;";
                                                                                        //String frontSpaces = "padding-left:80px";
                                                                                        checkSubfunc = obj.subFuncExists(main_func_id);
                                                                                        if (checkSubfunc) {
                                                                                            obj.subFunctionalities(main_func_id, out, frontSpaces, userFun, cbName, funcVec);
                                                                                        }
                                                                                    }
                                                                                } while (rs.next());
                                                                            }
                                                                            rs.close();
                                                                            stmt.close();
                                                                            conn.close();

                                                                %>
                                                        </table>
                                                    </div>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr height=20>
                                    <td align="center" bgcolor="#FFFFFF" class="headingSpas" style="padding-top:15px">
                                        <input type=hidden name=noOfcb value=<%=cbCounter%> >
                                        <input type=hidden name=checkedValues >
                                        <input type=hidden name=userType value=<%=user_type%> >
                                        <input type=hidden name=userTypeId value=<%=user_type_id%> >
<!--                                        <input id="submit" type="button" styleId="submit"  styleClass="headingSpas" value='Submit' onclick='validate_f1()'/>-->
                                        <html:submit styleId="submit"  styleClass="headingSpas1" value='Submit' onclick='return validate_f1()'/>
                                    </td>
                                </tr>

                            </table>
                        </div>
                    </html:form>
                </div>
                <%--////////////////////////////XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX////////////////////////////////--%>
            </c:otherwise>
        </c:choose>
    </center>
</div>