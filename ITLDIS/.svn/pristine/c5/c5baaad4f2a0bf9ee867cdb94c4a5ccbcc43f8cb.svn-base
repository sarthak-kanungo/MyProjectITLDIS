
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="java.sql.Connection,java.io.*,java.sql.*, java.util.*,viewEcat.comEcat.*,com.EAMG.common.EAMG_MethodsUtility" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String contextPath = request.getContextPath();
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
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
            String result = "" + request.getAttribute("result");
            String comp_type = "" + request.getAttribute("comp_type");
            //String grp_cr_status=""+request.getAttribute("grp_cr_status");
            //System.out.println("i am in output page");
            //System.out.println("result is :"+result);
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
                    String tdData = "";
                    if (comp_type.equals("group")) {
                        tdData = "MANAGE " + PageTemplate.GROUP.toUpperCase() + " >> ADD NEW " + PageTemplate.GROUP.toUpperCase();
                    } else if (comp_type.equals("part")) {
                        tdData = "MANAGE " + PageTemplate.GROUP.toUpperCase() + " >> ADD NEW PART";
                    } else if (comp_type.equals("assembly")) {
                        tdData = "MANAGE " + PageTemplate.GROUP.toUpperCase() + " >> ADD NEW ASSEMBLY";
                    } else if (comp_type.equals("kit")) {
                        tdData = "OTHER >> ADD NEW KIT";
                    } else if (comp_type.equals("tool")) {
                        tdData = "OTHER >> ADD NEW " + comp_type.toUpperCase();
                    }

                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = comp_type.toUpperCase() + " DETAILS";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <%--<tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <%if (comp_type.equals("group")) {%>
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Group>> Add New Group</b></td>
                            </tr>
                            <%}%>
                            <%if (comp_type.equals("part")) {%>
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Group>> Add New Part</b></td>
                            </tr>
                            <%}%>
                            <%if (comp_type.equals("assembly")) {%>
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Group>> Add New Assembly</b></td>
                            </tr>
                            <%}%>
                            <%if (comp_type.equals("kit")) {%>
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Other >> Add New Kit</b></td>
                            </tr>
                            <%}%>
                            <%if (comp_type.equals("tool")) {%>
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Other >> Add New <%=comp_type%></b></td>
                            </tr>
                            <%}%>
                        </table></td>
                </tr>--%>
            </table>
            <center>
                <font class="red" style="align:'center'"><%=result%></font></center>

        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
