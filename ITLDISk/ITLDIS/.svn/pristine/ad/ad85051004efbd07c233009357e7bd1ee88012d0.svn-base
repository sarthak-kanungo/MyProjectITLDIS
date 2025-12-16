<%-- 
    Document   : amw_AMW_OME_Martrix_success
    Created on : Nov 14, 2011, 4:09:55 PM
    Author     : avinash.pandey
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%String result = "" + request.getAttribute("result");
    String show_message = "" + request.getAttribute("show_message");

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AMW-Ecatalogue</title>
        <link href="/AMW-AuthEcat/css/config.css" rel="stylesheet" type="text/css" />

    </head>
    <body>
        <div align="center">
            <%if ((show_message != null) && !(show_message.equals("null"))) {%>
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center">   
                        <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage AMW OME Matrix >> Assign AMW vs OME Matrix </b></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr> 
                    <td>   
                <tr>
                    <td>&nbsp;</td>
                </tr>
                
                <table align="center" >
                    <tr>
                        <td valign="center" class="red" >
                            <%=show_message%>

                        </td>            
                    </tr>    

                </table> 

                </td>   
                </tr>
                <tr>
                    <td class="red-for-temmplate-link" align="center"><a href="javascript:parent.right.location.href='/AMW-AuthEcat/authJSP/other/amw_Assign_AMW_vs_OME_Matrix.jsp'">Assign More</a></td>
                </tr>
                <%}%>

            </table>
        </div>
    </body>
</html>