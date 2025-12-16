<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>
<%
response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
response.setHeader("Expires", "0");
response.setHeader("Pragma", "no-cache");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AMW-Ecatalogue</title>
        <link href="/AMW-AuthEcat/css/config.css" rel="stylesheet" type="text/css" />       
    </head>
    <body>
    <div align="center"> 
        <%
        String err=(String)request.getAttribute("xls_validate_result");
        //System.out.println("ia m in validation err:"+err);
        String validate_type=(String)request.getAttribute("validate_type");
        //System.out.println("validation:"+validate_type);
        int pos1=err.lastIndexOf("@"); 
        int pos2=err.lastIndexOf("#");
        int row=Integer.parseInt(err.substring(0,pos1-1))+1;
        int column=Integer.parseInt(err.substring(pos1+1,pos2-1))+1;
        String error=err.substring(pos2+1);
        String error2="";
        %>
          <table width="700" border="0" cellspacing="1" cellpadding="1">
        <tr>
            <td>&nbsp;</td>
        </tr>
        <tr>
            <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                    <tr>
                        <%
                        if(validate_type.equals("modify"))
                        {
                            error2="Hence Part Modification Process aborted. Attach valid Excel to complete the Process successfully.";
                            
                        %>
                         <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Part >> Modify Part </b></td>
                        <%}else{
                            error2="Hence AMW Part And OEM Part Creation Process aborted. Attach valid Excel to complete the Process successfully.";
                            %>
                            <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage AMW Part And OEM Part >> Add AMW Part And OEM Part</b></td>
                        <%}%>
                       
                    </tr>
            </table></td>
            </tr>
            <tr>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td class="red" align="center">
            There is an error at Column <%=column%>, Row <%=row%>. <%=error%><br><%=error2%></td>
        </tr>          
            
            <%
            if(validate_type.equals("modify"))
            {
            %>
             <tr>  <td class="red-for-temmplate-link" align="center" ><a href="javascript:parent.right.location.href='/AMW-AuthEcat/authJSP/other/amw_AMW_PART_OEM_PART_CREATE.jsp';">Try Again</a></td></tr>
            <%}
            
            else
            {
            %>
             <tr>  <td  class="red-for-temmplate-link" align="center" ><a href="javascript:parent.right.location.href='/AMW-AuthEcat/authJSP/other/amw_AMW_PART_OEM_PART_CREATE.jsp';">Try Again</a></td></tr>
            <%}%>
           
        
        </table>
    </div>
    </body>
</html>
