
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
            String context = request.getContextPath();

            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
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
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%
                     String tdData = "MANAGE KIT >> DELETE UNUSED KITS";
                     object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
       %>
        <%
                    heading = "DELETE UNUSED KITS";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
               <%-- <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue">
                                    <b class="path">&nbsp;Manage Kit >> Delete Unused Kits  </b></td>
                            </tr>
                        </table>
                    </td>
                </tr>--%>
                <!--tr>
                    <td align="right" class="small"><b>Step 2 OF 2 </b></td>
                </tr-->
                <tr>
                    <td valign="top">&nbsp;</td>
                </tr>
                <%
                            String[] deletedarr = (String[]) request.getAttribute("deletedarr");
                %>
                <tr class="red" align="center" valign="top">
                    <td>
                        Following Unused Kits have been deleted Successfully.
                    </td>
                </tr> 
                <tr>
                    <td class="red-for-temmplate-link" align="center" valign="top"><a href="javascript:location.href='authJSP/EAMG_KIT/EAMG_delete_unused_kits.jsp'" >Delete More</a></td>
                </tr> 
                <tr>
                    <td>&nbsp;</td>
                </tr>     
                <tr>  
                    <td align="center">
								<div style="overflow: auto; width:267px;height: 250px;">
                        <table width="250" border="0" cellpadding="1" cellspacing="1" class="tableStyle">
                            <tr>
                                <td  height="25" colspan="2" align="center" class="tdStyle"><strong >List of Deleted Kits</strong></td>
                            </tr> 

                            <%
                                        if (deletedarr != null) {
                                            for (int i = 0; i < deletedarr.length; i++) {
                            %>
                            <tr>
                                <td width="30%" align="center" class="tdStyle"><%=(i + 1)%></td>
                                <td width="30%" align="center" class="tdStyle"><%=deletedarr[i]%></td>

                            </tr>
                            <%}
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