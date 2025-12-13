<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="jxl.*,java.io.File" %>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*" %>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
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
            imagesURL = object_pageTemplate.imagesURL();
%>
<%
            String filepath = "" + session.getAttribute("filepath");
            //System.out.println("filepath:"+filepath);
%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />

        <script>
            function validate_submit()
            {
                window.close();
            }
        </script>



    </head>
    <body>

        <center>


            <%

                        Workbook wb = Workbook.getWorkbook(new File(filepath));
                        ////System.out.println("xlsfile is : "+xlsfile);
                        //Workbook wb = Workbook.getWorkbook(nameOfXls);
                        Sheet sheet = wb.getSheet(0);

                        int columns = sheet.getColumns();
                        int rows = sheet.getRows();
            %>


            <form name="form1" method="post" action="<%=context%>/create_asm.do">
                <table width="100%" border="1" cellspacing="0" cellspacing="1" bordercolor="#DCE0F0">
                    <tr><td colspan="<%=columns%>" align="left"><font face="Arial" size=2" color="#0033AB">Excel Data Uploaded:
                            </font>



                            <input type="button" value="Close" onclick="validate_submit();"/>  
                            <input type="hidden" name="option" value=""/>



                        </td></tr>
                        <%
                                String data = "";
                                for (int i = 0; i < rows; i++) {%>
                    <tr>
                        <%for (int j = 0; j < columns; j++) {

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
                        <td colspan="<%=columns%>"><font face="Arial" size=2"><STRONG><%=data%></STRONG></font></td>
                        <%
                                                    break;
                                                } else if ((j == 0 && data.equals("Asembly No")) || (j == 0 && data.equals("DESCRIPTION")) || (j == 0 && data.equals("Level")) || (j == 1 && data.equals("Item")) || (j == 2 && data.equals("Description")) || (j == 3 && data.equals("Type")) || (j == 4 && data.equals("Qty"))) {%>
                        <td align="left"><font face="Arial" size=2"><STRONG><%=data%></STRONG></font></td>
                        <%
                                                } else {
                        %>
                        <td align="left"><font face="Arial" size=2"><%=data%></font></td>
                        <%}%>         
                        <%}%>
                    </tr>
                    <%}
                    %>
                </table>
            </form>



        </center>


    </body>
</html>
