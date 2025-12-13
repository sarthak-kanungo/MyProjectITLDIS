
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
    <head>
       <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>ITLDIS</title>
        <link href="/spas/css/config.css" rel="stylesheet" type="text/css" />
    </head>

    <script language="javaScript">
        <!-- Disable
        function disableselect(e){
            return false
        }

        function reEnable(){
            return true
        }

        //if IE4+
        document.onselectstart=new Function ("return false")
        document.oncontextmenu=new Function ("return false")
        //if NS6
        if (window.sidebar){
            document.onmousedown=disableselect
            document.onclick=reEnable
        }
        //-->
    </script>

    <%
                response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                response.setHeader("Expires", "0");
                response.setHeader("Pragma", "no-cache");
                String result = "" + request.getAttribute("result");
                String type = "" + request.getAttribute("type");
                String option = "" + request.getAttribute("option");
                String link = "" + request.getAttribute("link");
                String more = "" + request.getAttribute("more");
    %>
    <body>
        <br/><br/>
      
             <table width=70%  align=center border="0" cellpadding="1" cellspacing="1" bordercolor="#333333" bgcolor=#000000>
            <tr height=25>

                <td  align="center"  class="grey"><span class="heading">
                        &nbsp;<%=result%>
                        <a href="<%=link%>">&nbsp;&nbsp;&nbsp;
                            <%if (type.equals("Success")) {%>
                            <%=more%>...
                            <%} else {%>
                            Try Again...
                            <%}%>
                        </a>
                    </span></td>

            </tr>
         </table>
    </body>
</html>
