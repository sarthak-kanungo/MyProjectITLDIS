
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*,EAMG_MethodsUtility_Package.EAMG_MethodUtility3"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String contextPath = request.getContextPath();
				String comp_type=(String)request.getAttribute("comp_type");

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
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath+"/Index", "", imagesURL);
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
                    String tdData = "MANAGE " +"" + comp_type.toUpperCase() + " >> DELETE UNUSED "+"" + comp_type.toUpperCase();
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
                    heading = "DELETE UNUSED TOOLS";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
               
                <%
                            String[] deletedarr = (String[]) request.getAttribute("deletedarr");
                %>
                <tr class="red" align="center" valign="top">
                    <td>
                        Following Unused <%=comp_type%>s have been deleted Successfully.
                    </td>
                </tr> 
                <tr>
                    <td class="red-for-temmplate-link" align="center" ><a href="javascript:location.href='authJSP/EAMG_Tool/EAMG_delete_unused_tools.jsp'" >Delete More</a></td>
                </tr> 
                <tr>
                    <td>&nbsp;</td>
                </tr>     
                <tr>  
                    <td align="center">   
                        <table width="60%" border="0" cellpadding="1" cellspacing="1" bordercolor="#CCCCCC" bgcolor="#000000">
                            <tr>
                                <td  height="25" colspan="2" align="center" class="blue"><strong class="heading">List of Deleted <%=comp_type%>s</strong></td>
                            </tr> 

                            <%
                                        if (deletedarr != null) {
                                            for (int i = 0; i < deletedarr.length; i++) {
                            %>
                            <tr>
                                <td width="30%" align="center" bgcolor="#FFFFFF" class="text"><%=(i + 1)%></td>
                                <td width="30%" align="center" bgcolor="#FFFFFF" class="text"><%=deletedarr[i]%></td>

                            </tr>
                            <%}
                                        }%>
                        </table>
                    </td>


                </tr>
            </table>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>