<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="java.sql.Connection,java.io.*,java.sql.*, java.util.*,viewEcat.comEcat.*,com.EAMG.common.EAMG_MethodsUtility" %>
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
            String contextPath = request.getContextPath();
            imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }
            String heading = null;
            int width = 659;
            int height = 0;
%>
<%
            Connection conn = holder.getConnection();
            ResultSet rs;
            Statement stmt = null;
            Vector totalunusedgroups = new Vector();
            Vector selectedunusedgroups = new Vector();
            //Vector allusedgroups = new Vector();

            stmt = conn.createStatement();
            rs = stmt.executeQuery("select distinct gkd.GRP_KIT_NO as unusedGroup,mg.group_no  FROM cat_group_kit_detail gkd left join cat_model_group mg on mg.group_no=gkd.grp_kit_no where mg.group_no is null");

            while (rs.next()) {
                totalunusedgroups.add(rs.getString(1));
            }

            rs.close();
            stmt.close();

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
        <script language="javascript">

            function moveDualList( grouplist, attachedlist, moveAll )
            {
                if((grouplist.selectedIndex == -1)&&(moveAll == false))
                {
                    return;
                }
                newattachedlist = new Array( attachedlist.options.length );
                for( len = 0; len < attachedlist.options.length; len++ )
                {
                    if ( attachedlist.options[ len ] != null )
                    {
                        newattachedlist[ len ] = new Option( attachedlist.options[ len ].text, attachedlist.options[ len ].value, attachedlist.options[ len ].defaultSelected, attachedlist.options[len ].selected );
                    }
                }
                for( var i = 0; i < grouplist.options.length; i++ )
                {
                    if(grouplist.options[i]!=null&&( grouplist.options[i].selected== true ||moveAll))
                    {
                        newattachedlist[ len ] = new Option( grouplist.options[i].text, grouplist.options[i].value, grouplist.options[i].defaultSelected, grouplist.options[i].selected );
                        len++;
                    }
                }

                for(var j = 0; j < newattachedlist.length; j++)
                {
                    if ( newattachedlist[ j ] != null )
                    {
                        attachedlist.options[ j ] = newattachedlist[ j ];
                        attachedlist.options[ j ].value=attachedlist.options[ j ].text;
                    }
                }

                for( var i = grouplist.options.length - 1; i >= 0; i-- )
                {
                    if ( grouplist.options[i] != null && ( grouplist.options[i].selected == true || moveAll ) )
                    {
                        grouplist.options[i]= null;
                    }
                }
                setlengths();
            }

            function setlengths()
            {
                document.getElementById("totalunusedgroups").innerHTML=document.getElementById("grouplist").options.length;
                document.getElementById("selectedunusedgroups").innerHTML=document.getElementById("attachedlist").options.length;
            }

            function searchelement()
            {
                var length=document.getElementById("grouplist").options.length;
                var searchtext=document.getElementById("searchunusedgroup").value;
                var searchtextlength=searchtext.length;
                searchtextlength=searchtextlength;
                for(i=0;i<length;i++)
                {
                    var text=document.getElementById("grouplist").options[i].text;
                    text=text.toUpperCase();
                    var index= text.indexOf(searchtext.toUpperCase());
                    if(index!=-1)
                    {
                        document.getElementById("grouplist").options[i].selected=true;

                    }
                    else
                    {
                        document.getElementById("grouplist").options[i].selected=false;
                    }
                }
            }

            function validate()
            {
                var length=document.getElementById("attachedlist").options.length;
                if(length==0)
                {
                    alert("No <%=PageTemplate.GROUP%>s are attached. Please Select atleast one <%=PageTemplate.GROUP%>.");
                    return false;
                }
                else
                {
                    for(i=0;i<length;i++)
                    {
                        document.getElementById("attachedlist").options[i].selected=true;
                    }
                    return true;
                }
            }

            function CancelProcess()
            {
               // var cncl=confirm("Do You Really want to Cancel the Current Process ?");
               // if(cncl==true)
                {
                    parent.content.location="<%=context%>/common/EAMG_home_page.jsp";
                }

            }

        </script>

    </head>
    <%
                String tdData = "MANAGE  TABLE >> DELETE UNUSED  " + PageTemplate.GROUP.toUpperCase();
                object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
    %>
    <%
                heading = "DELETE UNUSED " + PageTemplate.GROUP.toUpperCase() + "S";
                out.println(object_pageTemplate.tableHeader(heading, width, height));
    %>
    <%

                if (totalunusedgroups.size() < 1) {
    %>
    <body>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <%--<tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Delete Unused <%=PageTemplate.GROUP%>s</b></td>
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
                    <td valign="top" class="red" align="center">No Unused <%=PageTemplate.GROUP%> available in Database.</td>
                </tr>

            </table>
        </div>
        <%} else {%>


    <body onload="setlengths();">
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
               <tr>
                    <td>&nbsp;</td>
                </tr>
                 <%--<tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=PageTemplate.GROUP%> >> Delete Unused <%=PageTemplate.GROUP%>s </b></td>
                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td align="right" class="small">&nbsp;</td>
                </tr>


                <tr>
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;DELETE UNUSED <%=PageTemplate.GROUP.toUpperCase()%>S </b></td>
                            </tr>--%>
                            <tr>
                                <td align="center" valign="top" bgcolor="#FFFFFF">
                                    <form name="form1" action="<%=context%>/EAMG_delete_unused_groups.do" method="post">


                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                                <td width="30%" class="text" align="left">&nbsp;Search Unused <%=PageTemplate.GROUP%>s:
                                                </td>
                                                <td width="70%" align="left"><label>
                                                        <input type="text" size="13"  id="searchunusedgroup" name="searchunusedgroup" value=""/>
                                                    </label>
                                                    <img style="vertical-align:bottom" id='searchimg' border='0' title="Search" src='<%=imagesURL%>/go.gif' onclick="searchelement();"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="3">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td colspan="3" align="right"><table width="100%" border="0" cellpadding="1" cellspacing="1">
                                                        <tr>
                                                            <td width="43%" align="center"><table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                                                    <tr>
                                                                        <td width="43%" height="25" align="center" class="blue"><strong class="heading">Available Unused <%=PageTemplate.GROUP%>s</strong></td>
                                                                    </tr>
                                                                </table></td>
                                                            <td width="7%" bgcolor="#FFFFFF">&nbsp;</td>
                                                            <td width="43%" align="center"><table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                                                    <tr>
                                                                        <td width="43%" height="25" align="center" class="blue"><strong class="heading">Selected <%=PageTemplate.GROUP%>s</strong></td>
                                                                    </tr>
                                                                </table></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" bgcolor="#FFFFFF" class="text"># <span  id="totalunusedgroups"></span></td>
                                                            <td bgcolor="#FFFFFF">&nbsp;</td>
                                                            <td align="right" bgcolor="#FFFFFF" class="text"># <span id="selectedunusedgroups"></span></td>
                                                        </tr>
                                                        <tr valign="top">
                                                            <td rowspan="6" bgcolor="#FFFFFF"><select id="grouplist" name="grouplist" size="12" multiple="multiple" style="width:310px " class="text">
                                                                    <%
                                                                                        Iterator i = totalunusedgroups.iterator();
                                                                                        while (i.hasNext()) {%>
                                                                    <option><%=(String) i.next()%></option>
                                                                    <%}
                                                                    %>
                                                                </select></td>
                                                            <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                            <td rowspan="6" bgcolor="#FFFFFF"><select name="attachedlist" size="12" multiple="multiple" style="width:310px " class="text">
                                                                    <%
                                                                                        Iterator i1 = selectedunusedgroups.iterator();
                                                                                        while (i1.hasNext()) {
                                                                                            String val = (String) i1.next();%>
                                                                    <option><%=val%></option>
                                                                    <%}
                                                                    %>
                                                                </select></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center" bgcolor="#FFFFFF"><label>
                                                                    <img src="<%=imagesURL%>/next.gif" name="selectone" style="width:24px " onclick="moveDualList( document.form1.grouplist,  document.form1.attachedlist, false);" />
                                                                </label></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center" bgcolor="#FFFFFF"><label>
                                                                    <img src="<%=imagesURL%>/next1.gif" name="selectall" style="width:24px " onclick="moveDualList( document.form1.grouplist,  document.form1.attachedlist, true);"/>
                                                                </label></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center" bgcolor="#FFFFFF"><label>
                                                                    <img src="<%=imagesURL%>/previous.gif" name="removeone" style="width:24px " onclick="moveDualList( document.form1.attachedlist,document.form1.grouplist,false);"/>
                                                                </label></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center" bgcolor="#FFFFFF"><label>
                                                                    <img src="<%=imagesURL%>/previous1.gif" name="removeall" style="width:24px " onclick="moveDualList(document.form1.attachedlist,document.form1.grouplist,true);"/>
                                                                </label></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                        </tr>

                                                    </table></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3">
                                                    <table width="100%">
                                                        <tr>

                                                            <td width="50%" align="right">
                                                                <input type="submit" name="Next" id="Next" value="Submit" style="width:70px;" onClick="return validate();"/>
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
                        <%--</table></td>
                </tr>--%>
            </table>
        </div>
        <%}%>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
