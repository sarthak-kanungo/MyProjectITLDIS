<%--
    Document   : EAMG_adminHome
    Author     : avinash.pandey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.ConnectionHolder,viewEcat.comEcat.PageTemplate" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<!DOCTYPE html>
<%String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String userCode = (String) session.getValue("userCode");
            String date_OR_serial = (String) session.getValue("date_OR_serial");
            String trustedSite = "http://" + server_name + ":" + ecatPATH + "/";

            String contextPath = request.getContextPath();
            //String imagesURL = session.getAttribute("mainURL").toString();
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
                <title><%=UtilityMapkeys1.tile%></title>
    </head>
        <body topmargin="0" leftmargin="0" rightmargin="0">

        <table width="780" height="470" border="0" cellspacing="0" cellpadding="0" style="background-image:url(<%=imagesURL%>/images/Water_Mark1.gif); background-repeat:no;">
            <tr>
                <td >&nbsp;</td>
            </tr>
        </table>
    </body>
</html>
