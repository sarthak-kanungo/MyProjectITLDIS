<%-- 
    Document   : EAMG_modify_Kit_by_options
    Author     : avinash.pandey
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*" %>


<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            Connection conn = null;
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            conn = holder.getConnection();
            String contextPath = request.getContextPath();
            String comp_type=(String)request.getAttribute("comp_type");

            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
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
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=imagesURL%>js/suggestions.js"></script>
        <script type="text/javascript" src="<%=imagesURL%>js/autosuggest.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=imagesURL%>js/autosuggest.css" />
        <script language="javascript"> 
            <%
                        String context = request.getContextPath();
                        String comp_no = (String) session.getAttribute("comp_param_list");
                        //String comp_type = "" + session.getAttribute("comp_type");
                        String iscomppresent = (String) session.getAttribute("iscomppresent");
                        String type = "";
                        if (comp_type.equals("TOOL")) {
                            type = "TOOL";
                        }
            %>
                function check_method_select()
                {
                    if(document.form1.comp_detail.checked==true){
                        document.form1.action="<%=context%>/authJSP/EAMG_Tool/EAMG_modify_tool_details.jsp";
                    }else{
                        document.form1.action="<%=context%>/authJSP/EAMG_Tool/EAMG_create_tool_bom.jsp";
                    }
                    return true;
                }
                function compdet()
                {
                    document.form1.comp_detail.checked=true;
                    document.form1.comp_bom.checked=false;
                }
                function compbom()
                {
                    document.form1.comp_detail.checked=false;
                    document.form1.comp_bom.checked=true;
                } 
            
        
                function CancelProcess()
                {
                   // var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                    if(cncl==true)
                    {
                        location.href="<%=context%>/authJSP/EAMG_Tool/EAMG_tool_modify_comptype.jsp";
                    }
                }
        </script>



    </head>
    <body>
           <%
                        String tdData = "MANAGE "+""+comp_type.toUpperCase()+" >> MODIFY "+""+comp_type.toUpperCase();
                        object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
                        heading = "MODIFY TOOL";
                        out.println(object_pageTemplate.tableHeader(heading, width, height));
            %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%--<tr>
                    <td>&nbsp;</td>
                </tr>--%>
                <%if (iscomppresent.equals("notpresent")) {%>
                <%--<tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=comp_type%> >> Modify <%=comp_type%></b></td>
                            </tr>
                        </table></td>
                </tr>--%>
                <tr>
                    <td align="right" class="small">&nbsp;</td>
                </tr>

                <tr>
                    <td align="center" class="red"><%=type%> '<%=comp_no%>' does not exist in database. </td>
                </tr>
                <tr>  <td class="red-for-temmplate-link" align="center" ><a href="javascript:location.href='<%=context%>/authJSP/EAMG_Tool/EAMG_tool_modify_comptype.jsp';">Try Again</a></td></tr>
                <%} else {%>
               <%-- <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=comp_type%> >> Modify <%=comp_type%> </b></td>
                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td align="right" class="small"></td>
                </tr>
                
                <tr>
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;MODIFY TOOL</b></td>
                            </tr>--%>
                            <tr>
                                <td align="center" valign="top" bgcolor="#FFFFFF">
                                    <form  method="post" name="form1" onsubmit="return check_method_select();" action="">

                                        <br >
                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                                <td colspan="3">
                                                    <table width="100%" border="0" cellspacing="2" cellpadding="2">
                                                        <tr>
                                                            <td width="30%" align="left" class="text">&nbsp;<%=comp_type%> Number :</td>
                                                            <td width="50%" align="left" class="text"><%=comp_no%></td>
                                                            <td width="20%" align="right" class="small">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td  height="25"  align="left" class="text">&nbsp;Component Type:</td>
                                                            <td  height="25" align="left" class="text"><%=type%></td>
                                                            <td height="25" align="right" class="small">&nbsp;</td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>


                                            <tr>
                                                <td colspan="3"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                                        <tr>
                                                            <td height="30" align="left" class="blue"><b class="heading">&nbsp;Select Criteria</b></td>
                                                        </tr>
                                                    </table></td>
                                            </tr>

                                            <tr>
                                                <td width="10%" align="center" class="text"><label>
                                                        <input type="radio" checked name="comp_detail" value="" onClick="compdet();" />
                                                    </label></td>
                                                <td width="40%" align="left" class="text"><%=type%> Detail</td>
                                                <td width="50%" align="left" class="text"><label></label></td>
                                            </tr>
                                            <tr>
                                                <td height="25" align="center" class="text"><label>
                                                        <input type="radio" name="comp_bom"  onClick="compbom();"/>
                                                    </label></td>
                                                <td height="25" align="left" class="text"><%=type%> Bom</td>
                                            </tr>
                                            <tr>
                                                <td colspan="3">
                                                    <table width="100%">
                                                        <tr>

                                                            <td width="50%" align="right">
                                                                <input type="submit" name="Next" id="Next" value="Next" style="width:70px;" />
                                                            </td>
                                                            <td width="50%" align="left">
                                                                <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </form></td>
                            </tr>
                        <<%--/table></td>
                </tr>--%>
                <%}%>
            </table>
        </div>
            <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>