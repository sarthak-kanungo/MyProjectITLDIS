<%-- 
    Document   : EAMG_show_grps

--%>

<%@page contentType="text/html"%>
<%@ page import="java.sql.Connection,java.io.*,java.sql.*, java.util.*,viewEcat.comEcat.*,com.EAMG.common.EAMG_MethodsUtility" %>
<%@page import="java.sql.ResultSet,java.sql.Statement,java.util.Vector" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String context = request.getContextPath();
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
				String contextPath=request.getContextPath();
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
            session.setAttribute("ECNImplementation", "");
            Connection conn = null;
            Vector grpVec = new Vector();
            try {
                conn = holder.getConnection();
                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery("Select distinct GRP_KIT_NO from CAT_GROUP_KIT_BOM");
                if (rs.next()) {
                    do {
                        grpVec.addElement(rs.getString(1));
                    } while (rs.next());
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //conn.close();
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
        <script type="text/javascript" src="<%=imagesURL%>js/autosuggest1.js"></script>
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <script>
            var custom_array =new Array();
            <%
                    for (int i = 0; i < grpVec.size(); i++) {
            %>
                custom_array[<%=i%>]='<%="" + grpVec.elementAt(i)%>';
            <%}%>
                function open_img_bom()
                {
                    var group_no=document.form1.group_no.value;
                    group_no=TrimAll(group_no);
                    var flag=false;
                    if(group_no=='')
                    {
                        alert('Please Select the <%=PageTemplate.AGGREGATE%> Number.');
                        document.form1.group_no.value='';
                        document.form1.group_no.focus();
                        return;
                    }
                    else
                    {
                        for(var j=0;j<custom_array.length;j++)
                        {
                            if(custom_array[j]==group_no)
                            {
                                flag=true;
                                var sgbm=window.open('<%=context%>/EAMG_ShowGrpBomCustom.do?group_no='+group_no,'mywin','left=0,top=0,width=1015,height=700,toolbar=0');
                                sgbm.focus();
                                break;
                            }
                        }
                        if(flag==false)
                        {
                            alert("<%=PageTemplate.AGGREGATE%> Number '"+group_no+"' does not exist in database.");
                            document.form1.group_no.value='';
                            document.form1.group_no.focus();
                            return false;
                        }

                    }

                }

                function CancelProcess()
                {
                   // var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                    //if(cncl==true)
                    {
                        location.href="<%=context%>/common/EAMG_home_page.jsp";
                    }

                }


        </script>
    </head>
    <body>
        <%
                String tdData="MANAGE "+PageTemplate.AGGREGATE+" >> ADD NEW "+PageTemplate.AGGREGATE+" >> INSERT REF COORDS";
                object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
                heading = "INSERT REF COORDS";
                out.println(object_pageTemplate.tableHeader(heading, width, height));
    %>
        <%
                    if (grpVec.size() == 0) {
        %>


        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%--<tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=PageTemplate.AGGREGATE%>>> Add New <%=PageTemplate.AGGREGATE%> >>Insert Ref Coords  </b></td>
                            </tr>
                        </table></td>
                </tr>--%>
               
                <!--tr>
                <td align="right" class="small"><b>Step 2 OF 3</b></td>
            </tr-->

                <tr>
                    <td valign="top">&nbsp;</td>
                </tr>
                <tr>
                    <td valign="top" class="red" align="center">No <%=PageTemplate.AGGREGATE%> exists in Database.</td>
                </tr>
				</table>


        </div>
        <% } else {%>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%--<tr>
                    <td align="right" class="small"><b>Step 1 OF 3</b></td>
                </tr>
                <tr>
                    <td valign="top">&nbsp;</td>
                </tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=PageTemplate.AGGREGATE%>>> Add New <%=PageTemplate.AGGREGATE%> >>Insert Ref Coords  </b></td>
                            </tr>
                        </table></td>
                </tr>--%>
                <tr>
                    <td align="right" class="small"><b>Step 1 OF 3 </b></td>
                </tr>

                
               <%-- <tr>
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;INSERT REF COORDS  </b></td>
                            </tr>--%>
                            <tr>
                                <td align="center" valign="top" bgcolor="#FFFFFF">
                                    <form name="form1">
                                        <br />
                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">


                                            <tr>
                                                <td width="50%" class="text" align="center">Select <%=PageTemplate.AGGREGATE%> Number :</td>
                                                <td width="50%" colspan="2" align="left">
                                                    <input type="text" style='width:200px;height:20px;' name="group_no" id="grp" value="" />
                                                </td>
                                            </tr>

                                            <tr>
                                                <td colspan="3" align="center">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td  align="right"><label>
                                                        <input type="button" value="Next" onclick="open_img_bom()"  style="width:'65px'"/>

                                                    </label></td>
                                                <td colspan="2" align="left"><label>
                                                        <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />

                                                    </label></td>


                                            </tr>
                                        </table>
                                    </form></td>
                            </tr>
                        <%--</table></td>
                </tr>--%>
            </table>
        </div>

        <script> new actb1('grp', custom_array); </script>
        <%}%>
       <%
                out.println(object_pageTemplate.tableFooter());
    %>
    </body>
</html>

