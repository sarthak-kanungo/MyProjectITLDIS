<%-- 
    Document   : EAMG_action_success

--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="org.apache.struts.action.ActionMapping,userMgmt.action.*,java.util.*" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter" %>
<%
            String smsOrEmail = "" + request.getAttribute("smsOrEmail");
            Vector message_stat = (Vector) request.getAttribute("message_stat");
            Vector user_vec = (Vector) request.getAttribute("user_vec");
            String option = "" + request.getAttribute("option");
            String contextPath = request.getContextPath();
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);
            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String result = "" + request.getAttribute("result");
            String show_message = "" + request.getAttribute("show_message");
            String optionLink = "" + request.getAttribute("optionLink");
            String optionLinkName = "" + request.getAttribute("optionLinkName");
            String optionLinkURL = "" + request.getAttribute("optionLinkURL");
            String target = (String)request.getAttribute("target");
				
int width = 559;
            int height = 0;
%>



<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="<%=imagesURL%>/css/config.css" rel="stylesheet" type="text/css" />
        <style type="text/css">
           
        </style>        
    </head>
    <body  bgcolor="#ffffff">

        <%

String tdData = (String) request.getAttribute("tdData");
					if(tdData!=null)
         object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);


        %>

		    
		  <%
		  
					String heading = (String) request.getAttribute("heading");
					if(heading!=null)
					    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>

        <center>
            <table align="center" width="100%" border=0 bgcolor="#ffffff">
                <tr >
                    <td>
                        &nbsp;
                    </td>
                </tr>
                
                <%if ((show_message != null) && !(show_message.equals("null"))) {%>

					 <tr  valign="top">
                    <td nowrap width="100%"  align="center" class="text">
                       <font class="red">
                            <br>
                            <%=show_message%>
                            <br/><br/>
                        </font>
                    </td>
                </tr>            
                <%}%>
                <%if ((optionLink != null) && !optionLink.equals("null") && optionLink.equals("YES")) {%>
                <tr>
                    <td  align="center"><font class="red-for-temmplate-link">
                        <br>
								<%if(target!=null){%>
								<a href="<%=contextPath + optionLinkURL%>" target="<%=target%>"><%=optionLinkName%></a>
								 <%}else{%>
								 <a href="<%=contextPath + optionLinkURL%>"><%=optionLinkName%></a>
								 <%}%>
								 </font>
                    </td>
                </tr>        
                <%}%>                    
            </table>
        </center>
				 <%
				 if(heading!=null)
					out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
