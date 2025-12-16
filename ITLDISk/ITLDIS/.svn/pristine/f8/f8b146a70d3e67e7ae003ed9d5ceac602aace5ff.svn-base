<%@page contentType="text/html" isErrorPage="true"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
		 response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
		 response.setHeader("Expires", "0");
		 response.setHeader("Pragma", "no-cache");
%>
<html>
    <head>
		  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style  type="text/css" >

				.red{
					 font-weight: bold;
					 font-size: 11px;
					 font-family: Verdana, Arial, Helvetica, sans-serif;
					 color: #990000;
				}
		  </style>
    </head>
    <body>
		  <div align="center"><br><br><br><br><br><br><br><br>
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr><td valign="top">
								<table align="center" >
									 <tr>
										  <td valign="center" class="red" >
												${requestScope.result}
										  </td>
									 </tr>
								</table>
                    </td>
                </tr>
				</table>
		  </div>

    </body>
</html>
