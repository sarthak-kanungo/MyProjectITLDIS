<%-- 
    Document   : amw_add_aggregate
    Created on : Nov 11, 2011, 10:03:05 AM
    Author     : avinash.pandey
--%>

<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="amw.utility.*,amw.com.LoginAction.*"%>
<%@ page import="dbConnection.*,java.sql.Connection,java.io.*,amw_com.EAMG.common.*" %>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>
<html >
    <head>
        <%
            String contextPath = request.getContextPath();
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>AMW-Ecatalogue</title>


        <script type="text/javascript" src="<%=contextPath%>/js/validations.js"></script>
        <link href="<%=contextPath%>/css/config.css" rel="stylesheet" type="text/css" />
       
        <script language="javascript">
            
            function validate()
            {
                var aggregate=document.getElementById("aggregateName").value;
                aggregate=TrimAll(aggregate);
                 
                if(aggregate=="")
                {
                    alert("Please Enter Aggregate Name."); 
                    document.getElementById("aggregateName").value="";
                    document.getElementById("aggregateName").focus();
                    return false;
                }
                else{
                    var res=isProperAll(aggregate);
                    if(res==false)
                    {
                        alert('Aggregate Name can not contain any special characters.');
                        document.getElementById("aggregateName").value="";
                        document.getElementById("aggregateName").focus();
                        return false;
                    }
                }
                  
            }
            function CancelProcess()
            {
                //var cncl=confirm("Do You Really want to Cancel the Current Process ?");
               // if(cncl==true)
                {
                    location.href="<%=contextPath%>/common/EAMG_home_page.jsp";
                }
	
            }


        </script>

    </head>
    <body >
        <form action="<%=contextPath%>/amw_CreateAggregate.do" method="post"  name="form1" id="form1" >
            <div align="center">
                <table width="700" border="0" cellspacing="1" cellpadding="1">
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                                <tr>
                                    <td height="25" align="left" class="Lightblue"><b class="path">&nbsp;Manage Aggregate >> Add New Aggregate</b></td>
                                </tr>
                            </table></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>

                    <tr>
                        <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                                <tr>
                                    <td height="25" align="left" class="Lightblue"><b class="heading">&nbsp;ADD NEW AGGREGATE</b></td>
                                </tr>
                                <tr>
                                    <td align="center" valign="top" bgcolor="#FFFFFF">

                                        <br />
                                        <%
                                        if(session.getAttribute("aggregate")!=null){
                                            String aggregateName = (String) session.getAttribute("aggregate");
                                            if (aggregateName.equals("exist")) {%>

                                        <div class="red">Aggregate Name already Exist...</div>

                                        <%}}session.removeAttribute("aggregate");

                                            session.setAttribute("flag", "1");
                                        %>
                                        <br/>
                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                            
                                            <tr>
                                                <td width="30%" height="25" align="left" class="text">&nbsp;Aggregate Number</td>
                                                <td width="70%" align="left" class="text"><label>
                                                        <input type="text" name="aggregateName" id="aggregateName" value="" class="text" style="width: 154px"/>
                                                    </label></td>
                                            </tr>
                                            

                                            <tr>
                                                <td colspan="2">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" align="center"><label>

                                                        <input type="submit" name="Next" id="Next" value="Submit" onClick="return validate();" style="width:70px;"/>
                                                        <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />
                                                    </label></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table></td>
                    </tr>
                </table>
            </div>
        </form>
    </body>
</html>
