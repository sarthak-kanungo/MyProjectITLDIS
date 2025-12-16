<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
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
%>
<%
            String[] deletedarr = (String[]) request.getAttribute("deletedarr");
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
                    String tdData = "MANAGE PART >> DELETE UNUSED PARTS";
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "DELETE UNUSED PARTS";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%--<tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Part >> Delete Unused Parts </b></td>
                            </tr>
                        </table></td>

                </tr>--%>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td class="red" align="center">Following Unused Parts have been deleted Successfully.</td>
                </tr>
                <tr>
                    <td align="center" ><font class="red-for-temmplate-link" ><a href="javascript:location.href='<%=contextPath%>/authJSP/EAMG_Part/EAMG_delete_unused_parts.jsp';">Delete More..</a></font></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center">
								<div style="overflow: auto;width:267;height: 250;">
                        <table width="250" border="0" cellpadding="1" cellspacing="1" class="tableStyle">
                            <tr>
                                <td  height="25" colspan="2" align="center" class="tdStyle"><strong>List of Deleted Parts</strong></td>
                            </tr>
                            <tr>
										  <td width="15%" align="center"  class="tdStyle"><strong>S. No.</strong></td>
                                <td width="30%" align="left"  class="tdStyle"><strong>&nbsp;Part</strong></td>
                            </tr>


                            <%
                                        if (deletedarr != null) {
                                            for (int i = 0; i < deletedarr.length; i++) {

                            %>
                            <tr>
                                <td width="15%" align="center"  class="tdStyle"><%=(i + 1)%></td>
                                <td width="30%" align="left"  class="tdStyle">&nbsp;<%=deletedarr[i]%></td>
                            </tr>
                            <% }
                                        }%>


                        </table>
								</div>
                    </td>
                </tr>
            </table>

        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
