<%-- 
    Document   : amw_openIE
    Created on : Nov 5, 2011, 6:28:38 PM
    Author     : avinash.pandey
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.io.*,javax.servlet.http.*,javax.servlet.*,authEcat.UtilityMapkeys1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <TITLE><%=UtilityMapkeys1.tile%></TITLE>
    </head>
    <body>
        <%
            String mainURL = (String) session.getAttribute("mainURL");
            String imagesURL = (String) session.getAttribute("imagesURL");
            String url = request.getParameter("url");
            url = url.replaceAll("@@@", "&");

            String os = System.getProperty("os.name");
            if (os.equals("Windows NT")) {
                java.lang.Runtime.getRuntime().exec("\"C:/Program Files/Plus!/Microsoft Internet/IEXPLORE.EXE\"" + url);
            } else {
                java.lang.Runtime.getRuntime().exec("\"C:/Program Files/Internet Explorer/IEXPLORE.EXE\"" + url);
            }
        %>
        <script>
            window.close()
        </script>

    </body>
</html>
