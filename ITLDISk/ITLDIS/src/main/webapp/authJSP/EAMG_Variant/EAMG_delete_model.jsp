<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String contextPath = request.getContextPath();
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String modelNo = object_pageTemplate.MODEL_NO;
            String engineModel = object_pageTemplate.ENGINE_MODEL;

            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }
            String heading = null;
            int width = 659;
            int height = 84;
%>
<%
            Vector allusedmodels = new Vector();
            Connection conn = null;
            try {
                conn = holder.getConnection();
                ResultSet rs;
                Statement stmt = null;

                String query = "SELECT CAT_MODELS.MODEL_NO FROM CAT_MODELS WHERE (((CAT_MODELS.MODEL_NO) Not In (SELECT MODEL_NO FROM CAT_MODEL_GROUP)))";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    String temp1 = rs.getString(1);
                    allusedmodels.add(temp1);
                }

                rs.close();
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }


%> 
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>


<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <script>
    
            function confirmDelete()
            {
                var model_param2=document.getElementById("model_param2").value;
                model_param2=TrimAll(model_param2);
                if(model_param2=="0")
                {
                    alert("Please Select <%=modelNo%> Number.");
                    document.getElementById("model_param2").focus();
                    return false;
                }
                var r=confirm("Do you really want to delete <%=modelNo%> ? ")
                if(r==true)
                {
                    //alert("Yes");
                    return true;
                }
                else
                {
                    // alert("No")
                    return false;
                }
            }
    
            function CancelProcess()
            {
                var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                if(cncl==true)
                {
                    location.href="<%=contextPath%>/common/EAMG_home_page.jsp";
                }

            }



        </script>

    </head>
    <body>
        <%
                    String tdData = "MANAGE " + modelNo.toUpperCase() + " >> DELETE UNUSED " + modelNo.toUpperCase();
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "DELETE UNUSED " + modelNo.toUpperCase() + "";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>

        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%--<tr>
                 <td>&nbsp;</td>
                 </tr>--%>
                <%
                            try {
                                if (allusedmodels.isEmpty()) {
                %>



                <%--<tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=modelNo%> >> Delete Unused <%=modelNo%></b></td>
                            </tr>
                        </table></td>
                </tr>--%>
                <tr>
                    <td valign="top">&nbsp;</td>
                </tr>
                <tr>
                    <td valign="top" class="red" align="center">No Unused <%=modelNo%> available in Database.</td>
                </tr>


                <%} else {%>

                <%--<tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=modelNo%> >> Delete Unused <%=modelNo%></b></td>
                            </tr>
                        </table></td>
                </tr>--%>
                <%-- <tr>
                     <td align="right" class="small"><b>&nbsp;</b></td>
                 </tr>

                 <tr>
                     <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                             <tr>
                                 <td height="25" align="left" class="blue"><b class="heading">&nbsp;DELETE UNUSED <%=modelNo.toUpperCase()%></b></td>
                             </tr>--%>
                <tr>
                    <td align="center" valign="top" bgcolor="#FFFFFF">
                        <form  name="form1" action="<%=contextPath%>/delete_model.do" method="post">
                            <br />
                            <table width="95%" border="0" cellspacing="2" cellpadding="2">

                                <tr>
                                    <td width="40%" height="25" align="left" class="text">&nbsp;Select <%=modelNo%> Number: </td>
                                    <td width="60%" height="25" align="left" class="text">
                                        <select name="model_param2"  id="model_param2" multiple size="12" style="width:200px" >

                                            <option value="0" selected>--Select--</option>
                                            <%

                                                                    for (int i = 0; i < allusedmodels.size(); i++) {
                                                                        String value = allusedmodels.get(i).toString();
                                            %>
                                            <option value="<%=value%>"><%=value%></option>
                                            <%
                                                                    }
                                            %>
                                        </select></td>
                                </tr>

                                <tr>
                                    <td colspan="2" align="right" class="red-for-temmplate-link">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td  align="center"><label>
                                            <input type=submit value="Submit" name="Submit"   onclick="return confirmDelete();" />
                                        </label></td>
                                    <td  align="left"><label>
                                            <%--<input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />--%>
                                        </label></td>
                                </tr>
                            </table>
                        </form>
                    </td>
                </tr>
                <%--</table></td>
        </tr>
                --%>

                <%}%>
            </table>
        </div>
        <%
                               out.println(object_pageTemplate.tableFooter());
        %>
    </body>

    <%} catch (Exception e) {
                    e.printStackTrace();
                }%>
</html>







