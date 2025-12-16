<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
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
            String heading = null;
            int width = 659;
            int height = 84;
            String tdData = "";
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%
                    if (comp_type.equals("group")) {
                        tdData = "MANAGE GROUP >> ADD NEW GROUP";
                        heading = "MODIFY PART";
                    }

                    if (comp_type.equals("part")) {

                        tdData = "MANAGE PART >> ADD NEW PART";
                        heading = "MODIFY PART";

                    }
                    if (comp_type.equals("sap")) {

                        tdData = "MANAGE PART >> UPLOAD SAP PART MASTER";
                        heading = "UPLOAD SAP PART";

                    }
                    if (comp_type.equals("pricelist")) {

                        tdData = "MANAGE PART >> UPLOAD PRICE LIST";
                        heading = "UPLOAD PRICE LIST";

                    }
                    if (comp_type.equals("paintedPartList")) {

                        tdData = "MANAGE PART >> UPLOAD PART VS PAINT CODE MASTER";
                        heading = "UPLOAD PART VS PAINT CODE MASTER";

                    }
                    if (comp_type.equals("alternatePartList")) {

                        tdData = "MANAGE PART >> UPLOAD PART VS ALTERNATE MASTER";
                        heading = "UPLOAD PART VS ALTERNATE MASTER";

                    }

                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <br>


            <center>
                <font class="red" style="align:'center'"><%=result%></font>
            </center>

        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
