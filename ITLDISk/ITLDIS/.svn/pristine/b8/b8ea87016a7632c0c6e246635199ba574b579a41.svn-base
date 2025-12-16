<%-- 
    Document   : EAMG_create_tool_wz_success
    Created on : Nov 15, 2011, 11:14:28 PM
    Author     : avinash.pandey
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*,EAMG.Kit.Action.*" %>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>
<%
            String toolno = (String) session.getAttribute("toolno");
            String option = (String) session.getAttribute("option");
            String result = (String)request.getAttribute("result");//request.setAttribute("result","Success");
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String contextPath = request.getContextPath();
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String imagesURL = "";
				String comp_type=(String)request.getAttribute("comp_type");
            imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath+"/Index", "", imagesURL);
                return;
            }
            String heading = null;
            int width = 659;
            int height = 84;
            String tdData="";

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <%
                    if (option.equals("create")) {
                        heading = "ADD NEW "+comp_type.toUpperCase();
                        tdData = "MANAGE "+""+comp_type.toUpperCase()+" >> ADD NEW "+""+comp_type.toUpperCase();
                    } else {
                        heading = "MODIFY "+comp_type.toUpperCase();
                        tdData = "MANAGE "+""+comp_type.toUpperCase()+" >> MODIFY "+""+comp_type.toUpperCase();
                    }


                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <%if (option.equals("create")) {%>

               <%-- <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=comp_type%> >> Add New <%=comp_type%> </b></td>
                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>--%>

					 <%if(result!=null && result.equals("Success")){%>
                <tr>
                    <td class="red" align="center"><%=comp_type%> '<%=toolno%>' Added successfully.</td>
                </tr>
                <tr>
                    <td class="red-for-temmplate-link" align="center"><a href="javascript:location.href='<%=contextPath%>/authJSP/EAMG_Tool/EAMG_create_tool.jsp'">Add More</a></td>
                </tr>

					 <%} else if(result!=null && result.equals("exist")){%>


                <tr>
                    <td class="red" align="center">'<%=toolno%>' already exist in database.</td>
                </tr>
                <tr>
                    <td class="red-for-temmplate-link" align="center"><a href="javascript:location.href='<%=contextPath%>/authJSP/EAMG_Tool/EAMG_create_tool.jsp'">Try Again</a></td>
                </tr>

					 <%}else{%>
					 <tr>
                    <td class="red" align="center"><%=comp_type%> has not been Added,Please contact System Administrator.</td>
                </tr>
					  <%}%>

                
                
                <%} else {%>

                
                <tr>
                    <td class="red" align="center"><%=comp_type%> '<%=toolno%>' Modified successfully.</td>
                </tr>
                <tr>
                    <td class="red-for-temmplate-link" align="center"><a href="javascript:location.href='<%=contextPath%>/authJSP/EAMG_Tool/EAMG_tool_modify_comptype.jsp'" >Modify More</a></td>
                </tr>

                <%}%>
            </table>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
