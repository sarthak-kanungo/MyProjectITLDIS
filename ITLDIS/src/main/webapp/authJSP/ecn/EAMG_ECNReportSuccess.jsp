<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="viewEcat.comEcat.PageTemplate,java.io.PrintWriter,java.io.File,authEcat.UtilityMapkeys1" %>
<html>
    <%
                String contextPath = request.getContextPath();
                response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                response.setHeader("Expires", "0");
                response.setHeader("Pragma", "no-cache");
                String message=(String)request.getAttribute("meaasge");
                //System.out.println("set comp_type in session:PRT");
                String server_name = (String) session.getValue("server_name");
                String ecatPath = (String) session.getValue("ecatPATH");
                String mainURL = (String) session.getValue("mainURL");
                String userCode = (String) session.getValue("userCode");
                String ecatPATH = (String) session.getValue("ecatPATH");
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
                int width = 600;
                int height = 84;

                String saveLocation = mainURL + "dealer/excels/" + userCode + "/";
                File source_file = new File(saveLocation + "ECNReportExcel.xls");
                source_file.delete();
                String fileDownloadPath = saveLocation+ "ECNReportExcel.zip";
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />      
    </head>
    <body >
        <%
                    String tdData = "MANAGE ECN >> ECN REPORT";
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "ECN REPORT";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center" width="600">
            <table width="600" border="0" cellspacing="1" cellpadding="1">
                <tr valign="top">
                    <td align="left" valign="top" bgcolor="#FFFFFF" style="padding-left: 100px;">
                        <form name="form1"  method="post">
                            <br />
                            <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                <tr><td class="red" align="center">
                                    <%if(message.equals("SUCCESS")){%>
                                     ECN Data Exported in Excel Successfully.<br>
                                     <br>
                                      <a href=<%=fileDownloadPath%>>Click here to Download</a>
                                      <br><br>
                                      <font class="red-for-temmplate-link" ><a href="javascript:location.href='ECNReportDisp.do'">Export More</a></font>
                                     <%}%>
                                     
                                    <%if(message.equals("FAILURE")){%>
                                     ECN Data can not be Exported. Please Contact Administrator.<br>
                                     <br>                 
                                      <font class="red-for-temmplate-link" ><a href="javascript:location.href='ECNReportDisp.do'">Try Again</a></font>
                                     <%}%>
                                     </td>
                                </tr>
                               <tr>
                            </table>
                        </form>
                    </td>
                </tr>

            </table>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>

