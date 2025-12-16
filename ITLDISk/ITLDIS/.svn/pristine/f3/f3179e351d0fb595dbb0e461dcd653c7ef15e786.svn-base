<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<html>
    <%
			  String contextPath = request.getContextPath();
			  response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
			  response.setHeader("Expires", "0");
			  response.setHeader("Pragma", "no-cache");

			  //System.out.println("set comp_type in session:PRT");
			  String server_name = (String) session.getValue("server_name");
			  String ecatPath = (String) session.getValue("ecatPATH");
			  String mainURL = (String) session.getValue("mainURL");

			  PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
			  String imagesURL = "";
			  imagesURL = object_pageTemplate.imagesURL();
			  String session_id = session.getId();
			  String getSession = (String) session.getValue("session_value");
			  if (!session_id.equals(getSession)) {
					object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
					return;
			  }
			  String heading = null;
			  int width = 659;
			  int height = 84;

    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />



    </head>
    <body>
        <%
					String tdData = (String) request.getAttribute("tdData");
					object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
					heading = (String) request.getAttribute("heading");
					out.println(object_pageTemplate.tableHeader(heading, width, height));
					int counter = 0;
        %>

		  <table width="700px" border="0" cellspacing="1" cellpadding="1">
				<tr valign="top">
					 <td width="100%" align="center" valign="top" bgcolor="#FFFFFF">
						  <br />
						  <table width="100%" border="0" cellspacing="2" cellpadding="2">

								<tr>
									 <td nowrap width="100%"  align="center" class="text">
                                <font class="red">${requestScope.result}</font>
									 </td>
								</tr>
								<tr>
									 <td align="center"> <font class="red-for-temmplate-link"><a href="${requestScope.optionURL}">${requestScope.optionLink}</a></font></td>
								</tr>
						  </table>
						  <br>

						  <logic:notEmpty name="ecnFormBean" property="existList">
								<div align="center"><font class="red">${requestScope.existMessage}</font></div><br>
								<table width="60%" border="0" cellspacing="1" cellpadding="2" class="tableStyle">
									 <tr>
										  <td align="center" class="tdStyle"><strong>S. No</strong></td>
										  <td align="center" class="tdStyle"><strong><%=PageTemplate.GROUP%></strong></td>
									 </tr>
									 <logic:iterate id="element" name="ecnFormBean" property="existList">
										  <tr>
												<td align="center" class="tdStyle"><%=++counter%></td>
												<td align="center" class="tdStyle"> ${element}</td>
										  </tr>
									 </logic:iterate>
								</table>
						  </logic:notEmpty>

					 </td></tr>
		  </table>

        <%
					out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>

