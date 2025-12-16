<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%
response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
response.setHeader("Expires", "0");
response.setHeader("Pragma", "no-cache");
%>
<%
   String result=""+request.getAttribute("result");
   String comp_type=""+request.getAttribute("comp_type");
   //String grp_cr_status=""+request.getAttribute("grp_cr_status");
   //System.out.println("i am in output page");
      //System.out.println("result is :"+result);
      %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AMW-Ecatalogue</title>
         <link href="/AMW-AuthEcat/css/config.css" rel="stylesheet" type="text/css" />
      </head>
    <body>
        <div align="center">
        <table width="700" border="0" cellspacing="1" cellpadding="1">
            <tr>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                        <%if(comp_type.equals("AMW_PART_OEM_PART")){%>
                        <tr>
                            <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage AMW Part And OEM Part >> Add AMW Part And OEM Part</b></td>
                        </tr>
                        <%}%>
                        
                        
                </table></td>
        </tr></table> 
    
    <br>
 

    <center>
        <font class="red" style="align:'center'"><%=result%></font>
        
        </div>
    </body>
</html>
