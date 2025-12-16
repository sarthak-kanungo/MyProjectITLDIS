
<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
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
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center">   
                        <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage AMW Part And OEM Part >> Add AMW Part And OEM Part</b></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr> 
                    <td>   
                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        
                        <%
                        Vector result=(Vector)session.getAttribute("insertionresult");
                        if(result.size()==0)
                        {
                        %>
                        <table align="center" >
                            <tr>
                                <td valign="center" class="red" >
                                    AMW Part And OEM Part Added Successfully.
                                    
                                </td>            
                            </tr>    
                            
                        </table>  
                    </td>   
                </tr>
                <tr>
                    <td class="red-for-temmplate-link" align="center"><a href="javascript:parent.right.location.href='/AMW-AuthEcat/authJSP/other/amw_AMW_PART_OEM_PART_CREATE.jsp'">Add More</a></td>
                </tr>
                <%
                        }
                         else
                        { %>
                <tr>  
                    <td>   
                        <table align="center" >
                            <td valign="center" class="red">
                                 AMW Part And OEM Part Already Exist.
                                
                            </td>                        
                        </table>  
                    </td>    
                </tr>
                <tr>
                    <td class="red-for-temmplate-link" align="center"><a href="javascript:parent.right.location.href='/AMW-AuthEcat/authJSP/other/amw_AMW_PART_OEM_PART_CREATE.jsp'">Add More</a></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>   
                    <td align="center">   
                        <table width="60%" border="0" cellpadding="1" cellspacing="1" bordercolor="#333333" bgcolor="#000000">
                            <tr>
                                <td  height="25" align="center" class="blue"><strong class="heading">S.No.</strong></td>
                                <td  align="center" class="blue"><strong class="heading">AMW PART No.</strong></td>
                                <td  align="center" class="blue"><strong class="heading">OEM PART NO</strong></td> 
                            </tr>
                            <%
                            int ind=1;
                            for(int i=0;i<result.size();i=i+2)
                            {%>
                            <tr>
                                <td align="center" bgcolor="#FFFFFF" class="text"><%=ind%></td>
                                <td align="center" bgcolor="#FFFFFF" class="text"><%=result.elementAt(i)%></td>						
                                <td  align="center" bgcolor="#FFFFFF" class="text"><%=result.elementAt(i+1)%></td>						 
                            </tr>
                            <% ind++;}%>
                            <tr>
                                <td colspan="2">
                                </td>
                            </tr>
                        </table>  
                    </td>  
                </tr>
                <%
                
                        } %>
                
            </table>
        </div>
    </body>
</html>
