<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="viewEcat.comEcat.ConnectionHolder,java.sql.Connection,java.io.*,com.EAMG.common.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String context = request.getContextPath();
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
				String contextPath = request.getContextPath();
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
<%
            String comp_type = (String) session.getAttribute("comp_type");
%>    
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>
<html:html >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />

        <script type="text/javascript">
           
            function validate()
            {
                var flag=false;
                //alert(document.getElementById('descheckbox').checked);
                if(document.getElementById('descheckbox').checked==false){
                    flag=true;
                }else
                {
                    return true;
                }
                if(document.getElementById('remarkcheckbox').checked==false){
                    flag=true;
                }else
                {
                    return true;
                }
                /*//*if(document.getElementById('serviciablitychecked').checked==false){
                    flag=true;
                }else
                {
                    return true;
                }
                if(document.getElementById('statuschecked').checked==false){
                  
                    flag=true;
                }
                else
                {
                    return true;
                }*/
                if(flag)
                {
                    alert("Please Select atleast one Parameter to Modify.");
                    return false;
                }    
            }
        </script>
    </head>
    <body>
         <%
                    String tdData = "MANAGE PART >> MODIFY PART >> PART DETAILS >> MODIFICATION PARAMETERS";
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "MODIFICATION PARAMETERS";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
               <%-- <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Part >> Modify Part >> Modification Parameters</b></td>
                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td align="right" class="small">&nbsp;></td>
                </tr>


                <tr>
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;MODIFICATION PARAMETERS</b></td>
                            </tr>--%>
                            <tr>
                                <td align="center" valign="top" bgcolor="#FFFFFF">
                                    <form name="myform" method="post"  action="<%=context%>/EAMG_update_comp.do">
                                        <input type="hidden" name="comp_type" id="comp_type" value="<%=comp_type%>"/>
                                        <br />
                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                                <td align="center" class="red">File Validated Successfully.</td>
                                            </tr>
                                        </table>
                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                                <td colspan="3"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                                        <tr>
                                                            <td height="30" align="left" class="blue"><b class="heading">&nbsp;Select Modification Parameters</b></td>
                                                        </tr>
                                                    </table></td>
                                            </tr>
                                            <tr>
                                                <td height="25" align="center" class="text"><label>
                                                        <input type="checkbox" name="descheckbox" id="descheckbox" value="deschecked" />
                                                    </label></td>
                                                <td height="25" align="left" class="text">&nbsp;Part Description</td>

                                            </tr>
                                            <tr>
                                                <td height="25" align="center" class="text"><label>
                                                        <input type="checkbox" name="remarkcheckbox" id="remarkcheckbox" value="remarkchecked" />
                                                    </label></td>
                                                <td height="25" align="left" class="text">&nbsp;Part Remark</td>

                                            </tr>
                                            <tr>
                                                <td height="25" align="center" class="text"><label>
                                                        <input type="checkbox" name="categorycheckbox" id="remarkcheckbox" value="remarkchecked" />
                                                    </label></td>
                                                <td height="25" align="left" class="text">&nbsp;Category Type</td>

                                            </tr>
                                            <tr>
                                                <td height="25" align="center" class="text"><label>
                                                        <input type="checkbox" name="serviciablitycheckbox" id="serviciablitycheckbox" value="serviciablitychecked" />
                                                    </label></td>
                                                <td height="25" align="left" class="text">&nbsp;SERVICIABILITY</td>

                                            </tr>
                                            <%--<tr>
                                                <td height="25" align="center" class="text"><label>
                                                        <input type="checkbox" name="statuscheckbox" id="statuscheckbox" value="statuschecked" />
                                                    </label></td>
                                                <td height="25" align="left" class="text">&nbsp;Part Status</td>

                                            </tr>
                                            --%>

                                            <tr>
                                                <td class="text" ></td>
                                            </tr>

                                            <tr>
                                                <td colspan="3" align="center" class="text"><input type="submit" name="submit" value="Submit" onclick="return validate();"/></td>
                                            </tr>
                                        </table>
                                    </form></td>
                            </tr><%--
                        </table></td>
                </tr>--%>
            </table>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html:html>


