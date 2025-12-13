
<%--
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
--%>
<%
		 response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
		 response.setHeader("Expires", "0");
		 response.setHeader("Pragma", "no-cache");
		 String server_name = (String) session.getValue("server_name");
		 String ecatPath = (String) session.getValue("ecatPATH");
		 String mainURL = (String) session.getValue("mainURL");
		 String contextPath = request.getContextPath();
		 String modifyFlag = (String) request.getAttribute("modifyFlag");
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
		 int width = 659;
		 int height = 84;
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />


    </head>
    <body>
        <%
					String tdData = "MODIFY PART";
					
					object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
		  <%
                    heading = "MODIFY PART";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>

        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%
							  Vector result = (Vector) request.getAttribute("insertionResult");
							  Vector message = (Vector) result.get(0);
							  Vector exists = (Vector) result.get(1);

                %>
                <tr><td valign="top">
								<table align="center" >
									 <tr>
										  <td valign="center" class="red" >
												<%=message.get(0)%>
										  </td>
									 </tr>

								</table>
                    </td>
                </tr>

                <tr>
                    <td align="center"> <font class="red-for-temmplate-link"><a href="javascript:location.href='<%=contextPath%>/authJSP/EAMG_Part/EAMG_browse_part_excel.jsp'"><%=message.get(1)%></a></font></td>
                </tr>


                <br>

                <%                                      if (exists.size() > 0) {%>
                <tr>
                    <td>
                        <table align="center" >
                            <td valign="center" class="red">
                                <%if (exists.size() > 1) {%>
                                Following Parts are Already Exist.
                                <%} else {%>
										  Following Part is Already Exist.
                                <%}%>
                            </td>
                        </table>
                    </td>
                </tr>

                <tr>
                    <td align="center">
								<div style="overflow:auto; height:250px;width:267px;">
									 <table width="250" border="0" cellpadding="0" cellspacing="1" class="tableStyle" >
										  <tr>
												<td  height="10" align="center" class="tdstyle"><strong>S.No.</strong></td>
												<td  align="left" class="tdstyle"><strong >&nbsp;Part No.</strong></td>
												<%--<td  align="center" class="tdstyle"><strong >Type</strong></td>--%>
										  </tr>
										  <%
																							 int ind = 1;
																							 for (int i = 0; i < exists.size(); i++) {%>
										  <tr>
												<td align="center"  class="tdstyle"><%=ind%></td>
												<td align="left"  class="tdstyle">&nbsp;<%=exists.elementAt(i)%></td>
												<%--<td  align="center"  class="tdstyle"><%=result.elementAt(++i)%></td>--%>
										  </tr>
										  <% ind++;
																							 }%>
									 </table>
								</div>
                    </td>
                </tr>
                <%

							  }%>
            </table>
        </div>
        <%
					out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
