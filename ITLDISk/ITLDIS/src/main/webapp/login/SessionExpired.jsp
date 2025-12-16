<%-- 
    Document   : SessionExpired
    Created on : Jan 30, 2014, 11:33:55 AM
    Author     : manish.kishore
--%>


<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
%>
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title>SOLIS-DMS</title>
        <link href="${pageContext.request.contextPath}/css/config.css" rel="stylesheet" type="text/css" />
        
        <style type="text/css">
            
    </style></head>
    
    <body>
        
        <br></br>
         <br></br>
         <br></br>
         <br></br>
         <br></br>
        <br></br>
         <br></br>
         <br></br>
        <center>
            
            <font class="text">Your Session has been expired. </font>
            <a href="${pageContext.request.contextPath}/index.jsp" target="_top" class="red-for-temmplate-link"><font class="text">Please Login Again</font></a>
        </center>
    </body>
</html>
