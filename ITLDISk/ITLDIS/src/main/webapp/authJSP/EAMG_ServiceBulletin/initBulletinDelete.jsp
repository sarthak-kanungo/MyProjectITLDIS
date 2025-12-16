<%-- 
    Document   : initBulletinDelete
    Created on : Jul 11, 2013, 2:56:36 PM
    Author     : satyaprakash.verma
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
    <head>
        <%
                    String contextPath = request.getContextPath();
                    response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                    response.setHeader("Expires", "0");
                    response.setHeader("Pragma", "no-cache");
                    ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
                    String server_name = (String) session.getValue("server_name");
                    String ecatPath = (String) session.getValue("ecatPATH");
                    String mainURL = (String) session.getValue("mainURL");
                    PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
                    String servletURL = "";
                    servletURL = object_pageTemplate.servletURL();
                    String imagesURL = "";
                    imagesURL = object_pageTemplate.imagesURL();
                    String session_id = session.getId();
                    String getSession = (String) session.getValue("session_value");
                    if (!session_id.equals(getSession)) {
                        object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                        return;
                    }
                    String heading = null;
                    int width = 559;
                    int height = 0;
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=UtilityMapkeys1.tile%></title>
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <link type="text/css" rel="stylesheet" href="<%=imagesURL%>css/dhtmlgoodies_calendar.css" media="screen" />
        <script type="text/javascript" src="<%=imagesURL%>js/dhtmlgoodies_calendar.js"></script>
        <script type="text/javascript" language="javascript">
            function validateFields()
            {
                var elementArr = null;
                var strValue = null;
                var strObject = null;
                elementArr = new Array('Year of Issue','Bulletin Type');
                for (var i = 0; i < elementArr.length; i++)
                {
                    strObject = document.getElementById(elementArr[i]);
                    strValue = document.getElementById(elementArr[i]).value;
                    if (checkValue(strObject, strValue) == false)
                    {
                        return false;
                    }
                }       	
            }
            function checkValue(strObject, strValue)
            {
                var objRegExp = /^(\s*)$/g;
                var objSpecExp = /['&\\@]/g;

                strValue = strValue.replace(objRegExp, '');
                var temp = objSpecExp.exec(strValue);

                if (strValue.length == 0)
                {
                    if (strObject.tagName == "SELECT")
                    {
                        strObject.focus();
                        alert("Please select value of " + strObject.id + ".");
                        return false;
                    } else if (strObject.tagName == "RADIO") {

                        strObject.focus();
                        alert("Please select value of " + strObject.id + ".");
                    }
                    else
                    {
                        strObject.focus();
                        alert("Please enter value of " + strObject.id + ".");
                        return false;
                    }
                }
                else if (temp)
                {
                    if (strObject.tagName == "INPUT")
                    {
                        strObject.focus();
                        alert("Special Characters are not allowed of " + strObject.id + ".");
                        return false;

                    }

                }

            }
            function validateFieldsDetails()
            {
                var elementArr = null;
                var strValue = null;
                var strObject = null;
                elementArr = new Array('Heading');
                for (var i = 0; i < elementArr.length; i++)
                {
                    strObject = document.getElementById(elementArr[i]);
                    strValue = document.getElementById(elementArr[i]).value;
                    if (checkValue(strObject, strValue) == false)
                    {
                        return false;
                    }
                }

            }
        </script>
    </head>
    <body>

        <%
                    String tdData = "DELETE " + PageTemplate.bulletin.toUpperCase();
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "DELETE " + PageTemplate.bulletin.toUpperCase();
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <c:if test="${empty dataList and flag eq 'initView'}">
            <html:form method="POST" action="serviceBulletin.do" onsubmit="return validateFields();">
                <input type="hidden" name="option" value="deleteViewBulletin">
                <div align="center">
                    <table width="<%=width%>"    border=0 cellspacing=0 cellpadding=0> <tr><td align="center" style="padding-top: 25px;padding-left: 100px;">
                        <tr><td>
                                <table width="<%=width%>"    border=0 cellspacing=0 cellpadding=0>
                                    <tr>
                                        <td height="25" width="25%;" align="left" bgcolor="#FFFFFF" class="text" style="padding-left: 40px;"> <Strong>&nbsp;Year of Issue</Strong></td>
                                        <td height="25" width="75%;" align="left" bgcolor="#FFFFFF" class="text" style="padding-left: 40px;">
                                            <html:select  property="yearOfIssue" styleClass="text" styleId="Year of Issue" style="width:112px;">
                                                <html:options collection="yearList" property="value" labelProperty="label"/>
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="25" width="25%;" align="left" bgcolor="#FFFFFF" class="text" style="padding-left: 40px;"> <Strong>&nbsp;<%=PageTemplate.bulletin%> Type</Strong></td>
                                        <td height="25" width="75%;" align="left" bgcolor="#FFFFFF" class="text" style="padding-left: 40px;">
                                            <html:select property="type" styleClass="text" styleId="Bulletin Type" style="width:auto;">
                                                <html:option value="">--Select Here--</html:option>
                                                <html:option value="Parts">Parts</html:option>
                                                <html:option value="Service">Service</html:option>                                                
                                            </html:select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="25" width="25%;" colspan="2" align="center" bgcolor="#FFFFFF" class="text">
                                            <html:submit value="Submit" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
            </html:form>
        </c:if>
        <c:if test="${not empty dataList and flag eq 'dataView'}">
            <html:form method="POST" action="serviceBulletin.do" onsubmit="return validateFieldsDetails();">
                <input type="hidden" name="option" value="deleteViewBulletinDetails">
                <div align="center">
                    <table width="<%=width%>"    border=0 cellspacing=0 cellpadding=0> <tr><td align="center" style="padding-top: 25px;padding-left: 100px;">
                        <tr><td>
                                <table width="<%=width%>"    border=0 cellspacing=0 cellpadding=0>
                                    <tr>
                                        <td height="25" width="25%;" align="left" bgcolor="#FFFFFF" class="text" style="padding-left: 40px;"> <Strong>&nbsp;Year of Issue</Strong></td>
                                        <td height="25" width="75%;" align="left" bgcolor="#FFFFFF" class="text" style="padding-left: 40px;">${yearOfIssue}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="25" width="25%;" align="left" bgcolor="#FFFFFF" class="text" style="padding-left: 40px;"> <Strong>&nbsp;<%=PageTemplate.bulletin%> Type</Strong></td>
                                        <td height="25" width="75%;" align="left" bgcolor="#FFFFFF" class="text" style="padding-left: 40px;">${type}
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="25" width="25%;" align="left" bgcolor="#FFFFFF" class="text" style="padding-left: 40px;"> <Strong>&nbsp;Heading</Strong></td>
                                        <td height="25" width="75%;" align="left" bgcolor="#FFFFFF" class="text" style="padding-left: 40px;">
                                            <select  name="subject" class="text" id="Heading" style="width:255px;">
                                                <option value="">--Select Here---</option>
                                                <c:forEach items="${dataList}" var="bean">
                                                    <option value="${bean.sbID}@@${bean.formFileName}" title="${bean.subject}">${bean.subject}</option>
                                                </c:forEach>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td height="25" width="25%;" colspan="2" align="center" bgcolor="#FFFFFF" class="text">
                                            <html:submit value="Delete" />
                                        </td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                    </table>
                </div>
            </html:form>
        </c:if>
        <c:if test="${empty dataList and flag eq 'dataView'}">
            <div align="center">
                <table width="<%=width%>"    border=0 cellspacing=0 cellpadding=0> <tr><td align="center" style="padding-top: 25px;padding-left: 100px;">
                    <tr><td align="center" class="links_txt"><b><font color="red">No Record Exists</font></b>

                        </td>
                    </tr>
                </table>
            </div>
        </c:if>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>

    </body>
</html>

