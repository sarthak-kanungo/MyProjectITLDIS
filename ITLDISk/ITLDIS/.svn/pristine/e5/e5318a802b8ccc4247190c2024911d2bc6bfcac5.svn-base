<%-- 
    Document   : manageUserHierarchy
    Created on : Dec 30, 2015, 12:22:08 PM
    Author     : prashant.kumar
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<html>
    <%
                String contextPath = request.getContextPath();
                response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                response.setHeader("Expires", "0");
                response.setHeader("Pragma", "no-cache");
                session.removeAttribute("ECNImplementation");
                session.setAttribute("comp_type", "PRT");
                String mainURL = (String) session.getValue("mainURL");
                String cntxpath = request.getContextPath();
    %>
    <script language="javascript">

        function validateExcel()
        {
            var filePath= document.getElementById("excelFile").value;
            if(filePath == '')
            {
                alert('Please Browse Excel  File.');
                return false;
            }
            else if(filePath.indexOf('.xls') != (filePath.length-4) && filePath.indexOf('.xlsx') != (filePath.length-5))
            {
                alert('Invalid Excel File. Please Browse only .xls/.xlsx file.');
                return false;
            }
            document.form1.action="<%=cntxpath%>/masterAction.do?option=manageUserHierarchyExcel";
            document.form1.submit();
        }

    </script>

    <div class="contentmain1">
        <div class="breadcrumb_container">
            <ul class="hText">
                <li class="breadcrumb_link"><a href ='<%=contextPath%>/UserManagement.do?option=initUserInformation'>USER MANAGEMENT</a></li>
                <li class="breadcrumb_link">MANAGE USER HIERARCHY</li>
            </ul>
        </div>
        <br/>
        <center>
            <div class="content_right1">
                <div class="con_slidediv2" style="position: relative;width: 100%">
                    <h1 class="hText">MANAGE USER HIERARCHY</h1>
                    <table cellspacing=1 cellpadding=5 border=0 width=100% >
                        <tr height=30 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <form name="form1" enctype="multipart/form-data" method="post">
                                    <br />
                                    <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                        <tr>
                                            <td width="29%" style="padding-left: 150px" height="25" align="left" class="headingSpas">&nbsp;Browse Input File&nbsp;:</td>
                                            <td width="65%" height="25" align="left" class="headingSpas">
                                                <input type=file id="excelFile" name=excelFile value='' size=20 style="width:275px"></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" align="right" style="padding-right: 20px" ><em><a href="<%=mainURL%>dealer/templates/manageUserHierarchy_Template.xls" class="red-for-temmplate-link" style="font-size: 12px" >* Click Here To Download Template </a><span class="red-for-temmplate">(xls).</span></em></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2" align="center">
                                                <label>
                                                    <input type="submit" name="Next" id="Next" value="Submit" class="headingSpas" style="width:70px;" onclick="return validateExcel();"/>
                                                </label>
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


