<%@page contentType="text/html"%>
<%@page import="java.util.ArrayList,java.sql.Connection" pageEncoding="UTF-8"%>
<%@page import="EAMG_MethodsUtility_Package.EAMG_MethodUtility2"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="java.sql.Connection,java.io.*,java.sql.*, java.util.*,viewEcat.comEcat.*,com.EAMG.common.EAMG_MethodsUtility" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String context = request.getContextPath();
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");


            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);
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
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            Connection conn = null;
            ArrayList grp_data = null;
            try {
                conn = holder.getConnection();
                grp_data = new EAMG_MethodUtility2().grp_in_db(conn);
            } catch (Exception e) {
                e.printStackTrace();
            }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=imagesURL%>js/autosuggest1.js"></script>
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <script type="text/javascript" src="<%=imagesURL%>js/suggestions.js"></script>
        <script type="text/javascript" src="<%=imagesURL%>js/autosuggest.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=imagesURL%>js/autosuggest.css" />
        <script>
            var custom_array =new Array();
            <%
                        for (int i = 0; i < grp_data.size(); i++) {
            %>
                custom_array[<%=i%>]='<%="" + grp_data.get(i)%>';
            <%}%>
                function validate_form()
                {
                    var group=document.form1.group_no.value;
                    group=TrimAll(group);
                    var flag=false;
                    //alert(model)
                    if(group=='')
                    {
                        alert('Please Select the <%=PageTemplate.GROUP%> Number.');
                        document.form1.group_no.value='';
                        document.form1.group_no.focus();
                        return false;
                    }
                    else
                    {
                        for(var j=0;j<custom_array.length;j++)
                        {
                            if(custom_array[j]==group)
                            {
                                flag=true;
                            }
                        }
                        if(flag==false)
                        {
                            alert("<%=PageTemplate.GROUP%> Number '"+group+"' does not exist in database.");
                            document.form1.group_no.value='';
                            document.form1.group_no.focus();
                            return false;
                        }
                        if(document.form1.grp_check[0].checked==true)
                        {
                            document.form1.action='<%=context%>/authJSP/EAMG_Group/EAMG_modify_group_details_wz.jsp';
                        }
                        else if(document.form1.grp_check[1].checked==true)
                        {
                            document.form1.action='<%=context%>/authJSP/EAMG_Group/EAMG_modify_grp_bom_wz.jsp';
                        }

                    }

                    return true;
                }

                function CancelProcess()
                {
                    var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                    if(cncl==true)
                    {
                        parent.content.location="<%=context%>/common/EAMG_home_page.jsp";
                    }

                }
        </script>

    </head>
    <body>
        <%
                    String tdData = "MANAGE  TABLE >> MODIFY  "+PageTemplate.GROUP.toUpperCase();
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "MODIFY "+PageTemplate.GROUP.toUpperCase();
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <%
                        if (grp_data.isEmpty()) {
            %>
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <%--<tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>

                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=PageTemplate.GROUP%> >>  Modify <%=PageTemplate.GROUP%></b></td>

                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td align="right" class="small">&nbsp;</td>
                </tr>

                <tr>
                    <td valign="top">&nbsp;</td>
                </tr>--%>
                <tr>
                    <td valign="top" class="red" align="center">No <%=PageTemplate.GROUP%> exists in Database.</td>
                </tr>
            </table>


            <%} else {%>

            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%--<tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>

                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=PageTemplate.GROUP%> >> Modify <%=PageTemplate.GROUP%></b></td>

                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td align="right" class="small">&nbsp;</td>
                </tr>--%>

                
               <%-- <tr>
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;MODIFY <%=PageTemplate.GROUP.toUpperCase()%></b></td>
                            </tr>--%>
                            <tr>
                                <td align="center" valign="top" bgcolor="#FFFFFF">
                                    <form method="post" name="form1" onsubmit="return validate_form()" action="">
                                        <br />
                                        <input type="hidden" value="group" name="comp_type" id="comp_type"/>
                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                                <td height="40%"  align="left" class="text">Select <%=PageTemplate.GROUP%> Number:</td>
                                                <td width="60%" align="left"> <input type="text" style='width:200px;height:20px;' name="group_no" id="grp" value="" />
                                                &nbsp;
                                                    <a href='#'><img src='<%=imagesURL%>/arrdown.gif' border='0' alt='Get Suggestions' style='width:15px' onclick='getSuggestions("group_no",document.form1.group_no,document.form1.comp_type,document.getElementById("img"));'/><img alt=""  id='img' style='visibility:hidden;' border='0' src='<%=imagesURL%>/load.gif'/>
                                                   </a>
                                                </td>
                                            </tr>
                                        </table>


                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                                <td colspan="3"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                                                        <tr>
                                                            <td height="25" align="left" class="blue"><b class="heading">&nbsp;Select Modification Option:</b></td>
                                                        </tr>
                                                    </table></td>
                                            </tr>
                                            <tr>
                                                <td width="6%" height="25" align="center" class="text"><label>
                                                        <input type="radio" value="grp_details" name="grp_check" checked/>
                                                    </label></td>
                                                <td width="22%" align="left" class="text">&nbsp;<%=PageTemplate.GROUP%> Details</td>
                                                <td width="72%" align="left" class="text"><label></label></td>
                                            </tr>
                                            <tr>
                                                <td height="25" align="center" class="text"><label>
                                                        <input type="radio" value="model_groups" name="grp_check"/>
                                                    </label></td>
                                                <td height="25" align="left" class="text">&nbsp;<%=PageTemplate.GROUP%> BOM</td>
                                                <td height="25" align="left" class="text"><label></label></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3">
                                                    <table width="100%">
                                                        <tr>

                                                            <td align="center">
                                                                <input type="submit" name="Next" id="Next" value="Next" style="width:70px;"/>
                                                            </td>
                                                            <td align="left">
                                                               <%-- <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />--%>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>

                                        </table>

                                    </form></td>
                            </tr>
                       <%-- </table></td>
                </tr>--%>
            </table>

            <% }%>
        </div>
        <script>new actb1('grp', custom_array); </script>
    </body>
    <%
                out.println(object_pageTemplate.tableFooter());
    %>
</html>
