<%-- 
    Document   : amw_create_kit_wz_success
    Created on : Nov 15, 2011, 11:14:28 PM
    Author     : avinash.pandey
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*,EAMG.Kit.Action.*" %>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>
<%
            String kitno = (String) session.getAttribute("kitno");
            String option = (String) session.getAttribute("option");
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String context = request.getContextPath();
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String imagesURL = "";
				String contextPath = request.getContextPath();
            imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath+"/Index", "", imagesURL);
                return;
            }
            String heading = null;
            int width = 659;
            int height = 84;
            String tdData="";

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%
                    if (option.equals("create")) {
                        heading = "ADD NEW KIT";
                        tdData = "MANAGE KIT >> ADD NEW KIT";
                    } else {
                        heading = "MODIFY KIT";
                        tdData = "MANAGE KIT >> MODIFY KIT";
                    }

                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);

                    out.println(object_pageTemplate.tableHeader(heading, width, height));

              
                    
                   

            %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <%if (option.equals("create")) {%>

                <%--<tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Kit >> Add New Kit </b></td>
                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>--%>
                <tr>
                    <td class="red" align="center">Kit '<%=kitno%>' Added successfully.</td>
                </tr>
                <tr>
                    <td class="red-for-temmplate-link" align="center"><a href="javascript:location.href='<%=context%>/authJSP/EAMG_KIT/EAMG_create_kit.jsp'">Add More</a></td>
                </tr>

                <%} else {%>

               <%-- <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Kit >> Modify Kit</b></td>
                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>--%>
                <tr>
                    <td class="red" align="center">Kit '<%=kitno%>' Modified successfully.</td>
                </tr>
                <tr>
                    <td class="red-for-temmplate-link" align="center"><a href="javascript:location.href='<%=context%>/authJSP/EAMG_KIT/EAMG_kit_modify_comptype.jsp'" >Modify More</a></td>
                </tr>                

                <%}%>
            </table>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
