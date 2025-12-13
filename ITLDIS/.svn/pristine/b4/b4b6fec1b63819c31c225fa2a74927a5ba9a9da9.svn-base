<%@page contentType="text/html"%>
<%@page import="jxl.*,java.io.File" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.ConnectionHolder,viewEcat.comEcat.PageTemplate" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
%>
<%
            String result = "" + request.getAttribute("result");
				String contextPath = request.getContextPath();
            //String grp_cr_status=""+request.getAttribute("grp_cr_status");

            //System.out.println("result is :"+result);

            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            //String imagesURL = session.getAttribute("mainURL").toString();
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath+"/Index", "", imagesURL);
                return;
            }

            String group_xls_file = "" + session.getAttribute("group_xls_file");
            String group_number = "" + session.getAttribute("group_number");
            File grp_xls_file = new File(group_xls_file);
            String context = request.getContextPath();
            String user_id = "" + (String) session.getValue("userCode");

%>


<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
--%>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
    </head>
    <body>


        <center>
            <table width="80%" border="1" cellspacing="0" cellspacing="1" bordercolor="#cccccc">
                <%
                            Workbook workbook1 = Workbook.getWorkbook(grp_xls_file);
                            Sheet sheet = workbook1.getSheet(0);
                            //String group_no=sheet.getCell(1,2).getContents().trim();


                            int sheet_cols = sheet.getColumns();
                            int sheet_rows = sheet.getRows();
                            String data = "";

                            for (int i = 0; i < sheet_rows; i++) {%>
                <tr>
                    <%for (int j = 0; j < sheet_cols; j++) {

                                    if (sheet.getCell(0, i).getContents().trim().equals("end")) {
                                        return;
                                    }
                                    data = sheet.getCell(j, i).getContents().trim();


                                    if (data.equals("")) {
                                        data = "&nbsp;";
                                    } else {

                                        data = data.replaceAll("<", "&lt;");
                                        data = data.replaceAll(">", "&gt;");

                                    }
                                    if (i == 0 || (j == 0 && data.equals("&nbsp;"))) {%>
                    <td align="center" colspan="<%=sheet_cols%>"><font face="Verdana" size=1"><STRONG><%=data%></STRONG></font></td>
                    <%
                                             break;
                                         } else if ((j == 0 && data.equals("GROUP NO")) || (j == 0 && data.equals("DESCRIPTION")) || (j == 0 && data.equals("LEVEL")) || (j == 1 && data.equals("SEQ. NO.")) || (j == 2 && data.equals("ITEM")) || (j == 3 && data.equals("DESCRIPTION")) || (j == 4 && data.equals("TYPE")) || (j == 5 && data.equals("QTY"))) {%>
                    <td align="left"><font face="Verdana" size=1"><STRONG><%=data%></STRONG></font></td>
                    <%
                                         } else {
                    %>
                    <td align="left"><font face="Verdana" size=1"><%=data%></font></td>
                    <%}%>
                    <%}%>
                </tr>
                <%}
                %>
            </table>



        </center>

    </body>
</html>
