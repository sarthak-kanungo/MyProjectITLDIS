<%-- 
    Author     : Avinash.Pandey
--%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*" %>

<html>
    <head>

        <%@page contentType="text/html"%>

        <%
                    response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                    response.setHeader("Expires", "0");
                    response.setHeader("Pragma", "no-cache");
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
                    String modelNo = object_pageTemplate.MODEL_NO;
                    String group = object_pageTemplate.GROUP;

                    String session_id = session.getId();
                    String getSession = (String) session.getValue("session_value");
                    if (!session_id.equals(getSession)) {
                        object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                        return;
                    }
                    String heading = null;
                    int width = 659;
                    int height = 84;
                    String modelno = (String) session.getAttribute("modelno");
                    EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
                    EAMG_ModelDAO dao = new EAMG_ModelDAO();
                    Vector selectedgroups = new Vector();
                    Vector totalgroups = new Vector();
                    Connection conn = null;
                    try {
                        conn = holder.getConnection();

                        boolean reslt = methodutil.isModelPresent(modelno,conn);

                        if(reslt)
                        {
                            totalgroups = methodutil.getCompleteGrps(conn);
                            selectedgroups = dao.getGrupNoByModel_group(modelno, conn);
                            Vector tempgroups = new Vector();
                            if (totalgroups.size() > 0) {
                                for (int i = 0; i < totalgroups.size(); i++) {
                                    if (!selectedgroups.contains(totalgroups.elementAt(i))) {
                                        tempgroups.add(totalgroups.elementAt(i));
                                    }
                                }
                            }
                            totalgroups.removeAllElements();
                            totalgroups = tempgroups;
                        }
                        String flag = "" + session.getAttribute("flag");
                        String caller = "" + session.getAttribute("caller");
                        if (caller == null) {
                            caller = "";
                        }

                        String tdData = "";
        %>

        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=UtilityMapkeys1.tile%></title>
        <script language="javascript">
            // Compare two options within a list by VALUES
            var len = 0;
            var l1=0,l2=0;
            function compareOptionValues(a, b)
            {
                var sA = parseInt( a.value, 36 );
                var sB = parseInt( b.value, 36 );
                return sA - sB;
            }
            // Compare two options within a list by TEXT
            function compareOptionText(a, b)
            {
                // Radix 10: for numeric values
                // Radix 36: for alphanumeric values
                var sA = parseInt( a.text, 36 );
                var sB = parseInt( b.text, 36 );
                return sA - sB;
            }
            // Dual list move function
            function moveDualList( grouplist, attachedlist, moveAll )
            {
                // Do nothing if nothing is selected

                if (  ( grouplist.selectedIndex == -1 ) && ( moveAll == false )   )
                {
                    return;
                }
                newattachedlist = new Array( attachedlist.options.length );
                for( len = 0; len < attachedlist.options.length; len++ )
                {
                    if ( attachedlist.options[ len ] != null )
                    {
                        newattachedlist[ len ] = new Option( attachedlist.options[ len ].text, attachedlist.options[ len].value, attachedlist.options[ len ].defaultSelected, attachedlist.options[ len ].selected );
                    }
                }
                for( var i = 0; i < grouplist.options.length; i++ )
                {
                    if ( grouplist.options[i] != null && ( grouplist.options[i].selected == true || moveAll ) )
                    {
                        // Statements to perform if option is selected
                        // Incorporate into new list
                        newattachedlist[ len ] = new Option( grouplist.options[i].text, grouplist.options[i].value, grouplist.options[i].defaultSelected, grouplist.options[i].selected );
                        len++;
                    }
                }
                // Sort out the new destination list
                // newattachedlist.sort( compareOptionValues );   // BY VALUES
                // newattachedlist.sort( compareOptionText );   // BY TEXT
                // Populate the destination with the items from the new array
                for ( var j = 0; j < newattachedlist.length; j++ )
                {
                    if ( newattachedlist[ j ] != null )
                    {
                        attachedlist.options[ j ] = newattachedlist[ j ];
                        attachedlist.options[ j ].value=attachedlist.options[ j ].text;
                        //alert(attachedlist.options[ j ].text);
                    }

                }
                // Erase source list selected elements
                for( var i = grouplist.options.length - 1; i >= 0; i-- )
                {
                    if ( grouplist.options[i] != null && ( grouplist.options[i].selected == true || moveAll ) )
                    {
                        // Erase Source
                        //grouplist.options[i].value = "";
                        //grouplist.options[i].text  = "";
                        grouplist.options[i]       = null;
                    }
                }
                setlengths();
            } // End of moveDualList()
            function setlengths()
            {
                document.getElementById("totalgrouplength").innerHTML=document.getElementById("grouplist").options.length;
                document.getElementById("selectedgrouplength").innerHTML=document.getElementById("attachedlist").options.length;

            }
            function searchelementGroup()
            {
                var length=document.getElementById("grouplist").options.length;
                var searchtext=document.getElementById("searchgroup").value;
                searchtext=searchtext.toUpperCase();
                var searchtextlength=searchtext.length;
                searchtextlength=searchtextlength;
                //alert(searchtextlength)
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
                donotsubmit();
                var length=document.getElementById("attachedlist").options.length;
                if(length==0)
                {
                    var cnf = confirm("No <%=group%> are attached. Do you want to remove all <%=group%>.");
                    if(cnf)
                    {
                        document.form1.action="<%=contextPath%>/EAMG_attachgroup.do";
                        document.form1.submit();
                        return true;
                    }
                    else
                    {
                        return false;
                    }

                    
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
            function validate1()
            {
                // alert("hello")
                var x=document.getElementById("attachedlist");

                var z=x.selectedIndex

                var zz
                zz=x.options[z-1].text
                x.options[z-1].text=x.options[z].text
                x.selectedIndex=z-1
                x.options[z].text=zz
                zz=x.options[z].value
                x.options[z].value=x.options[z-1].value
                x.options[z-1].value=zz

            }

            function validate2()
            {
                // alert("hello")
                var x=document.getElementById("attachedlist");

                var z=x.selectedIndex

                var zz
                zz=x.options[z+1].text
                x.options[z+1].text=x.options[z].text
                x.selectedIndex=z+1
                x.options[z].text=zz
                zz=x.options[z].value
                x.options[z].value=x.options[z+1].value
                x.options[z+1].value=zz

            }
            function CancelProcess()
            {
                //var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                //if(cncl==true)
                {
            <%if (flag.equals("add_model")) {%>
                        location.href="<%=contextPath%>/EAMG_create_model.do";
            <%} else {%>
                        location.href="<%=contextPath%>/authJSP/EAMG_Variant/EAMG_modify_model_bypass.jsp";
            <%}%>
                    }

                }
                //  End -->
                function donotsubmit()
                {
                    return false;
                }
        </script>

    </head>
    <%if (totalgroups.size() > 0 || selectedgroups.size() > 0) {%>
    <body onload="setlengths();">
        <%} else {%>
    <body>
        <%}%>
        <%
                                if (flag.equals("add_model")) {
                                    tdData = "MANAGE " + modelNo.toUpperCase() + " >> ADD " + modelNo.toUpperCase() + " >> ATTACH " + group.toUpperCase() + " TO " + modelNo.toUpperCase();
                                } else {
                                    tdData = "MANAGE " + modelNo.toUpperCase() + " >> MODIFY " + modelNo.toUpperCase() + " >> ATTACH " + group.toUpperCase() + " TO " + modelNo.toUpperCase();
                                }
                                object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <% heading = "ATTACH " + group.toUpperCase() + " TO " + modelNo.toUpperCase() + "";
                                out.println(object_pageTemplate.tableHeader(heading, width, height));%>
        <div align="center">

            <table width="700" border="0" cellspacing="1" cellpadding="1">


                <%--<tr>
                    <td>&nbsp;</td>
                </tr>--%>
                <% if(!reslt)
                        {%>
                         <tr>
                    <td align="center" class="red"><%=modelNo%> does not exist.</td>
                </tr>
                  <%}
                 else{%>
                     <%if (flag.equals("add_model")) {%>
                    <%--<tr>
                        <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                <tr>
                                    <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=modelNo%> >> Add  <%=modelNo%> >> Attach <%=group%> To <%=modelNo%></b></td>
                                </tr>
                            </table></td>
                    </tr>--%>
                    <tr>
                        <td align="right" class="small"><b>Step 2 OF 3</b></td>
                    </tr>

                    <%if (!caller.equals("home")) {%>
                    <tr>
                        <td align="center" class="red"><%=modelNo%> Added Successfully.</td>
                    </tr>
                    <%}
                    } else {%>
                        <%--<tr>
                        <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                <tr>
                                    <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=modelNo%> >> Modify <%=modelNo%> >> Attach <%=group%> To <%=modelNo%></b></td>
                                </tr>
                            </table></td>
                    </tr>
                    <tr>
                        <td align="right" class="small">&nbsp;</td>
                    </tr>--%>


                <%    }
                     if (totalgroups.size() > 0 || selectedgroups.size() > 0) {%>

                <%--<tr>
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="20" align="left" class="blue"><b class="heading">&nbsp;ATTACH <%=group.toUpperCase()%> TO <%=modelNo.toUpperCase()%></b></td>
                            </tr>--%>
                <tr>
                    <td align="center" valign="top" bgcolor="#FFFFFF">
                        <form name="form1" action="authJSP/EAMG_Variant/EAMG_display_groups.jsp" method="post" name="form1" id="form1" onsubmit="return validate();">
                        <!--<form name="form1" action="<%--=contextPath--%>/amw_display_groups.do" method="post" name="form1" id="form1">-->
                            <br />
                            <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                <tr>
                                    <td colspan="3"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                            <tr>
                                                <td height="25" align="left" class="blue"><b>&nbsp;<span class="heading"><%=modelNo%> No: <%=modelno%></span></b></td>
                                            </tr>
                                        </table></td>
                                </tr>

                                <tr>
                                    <td width="19%" class="text">&nbsp;Search <%=group%></td>
                                    <td  colspan="2" align="left"><label>
                                            <input type="text" name="searchgroup" id="searchgroup" /> &nbsp;&nbsp;&nbsp;<input type="button" value="Search" onclick="searchelementGroup();"/>
                                        </label></td>
                                </tr>
                                <tr>
                                    <td colspan="3" align="right"><table width="100%" border="0" cellpadding="1" cellspacing="1">
                                            <tr>
                                                <td width="43%" align="center"><table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                                        <tr>
                                                            <td width="43%" height="25" align="center" class="blue"><strong class="heading">Available <%=group%></strong></td>
                                                        </tr>
                                                    </table></td>
                                                <td width="7%" bgcolor="#FFFFFF">&nbsp;</td>
                                                <td width="43%" align="center"><table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                                        <tr>
                                                            <td width="43%" height="25" align="center" class="blue"><strong class="heading">Selected  <%=group%></strong></td>
                                                        </tr>
                                                    </table></td>
                                                <td width="7%" bgcolor="#FFFFFF">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td align="right" bgcolor="#FFFFFF" class="text"># <span  id="totalgrouplength"></span></td>
                                                <td bgcolor="#FFFFFF">&nbsp;</td>
                                                <td align="right" bgcolor="#FFFFFF" class="text"># <span id="selectedgrouplength"></span></td>
                                                <td bgcolor="#FFFFFF">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td rowspan="6" bgcolor="#FFFFFF"><select name="grouplist" id="grouplist" size="12" multiple="multiple" style="width:300px " class="text">
                                                        <%
                                                                                                                Iterator i = totalgroups.iterator();
                                                                                                                while (i.hasNext()) {%>
                                                        <option><%=(String) i.next()%></option>
                                                        <%}
                                                        %>
                                                    </select></td>
                                                <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                <td rowspan="6" bgcolor="#FFFFFF">
                                                    <select name="attachedlist" id="attachedlist" size="12" multiple="multiple" style="width:300px " class="text">
                                                        <%
                                                                                                                Iterator i1 = selectedgroups.iterator();
                                                                                                                while (i1.hasNext()) {
                                                                                                                    String val = (String) i1.next();%>
                                                        <option><%=val%></option>
                                                        <%}
                                                        %>
                                                    </select></td>
                                                <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td align="center" bgcolor="#FFFFFF"><label>
                                                        <img src="<%=imagesURL%>/next.gif" name="selectone" id="button" style="width:24px"  onclick="moveDualList( document.form1.grouplist,  document.form1.attachedlist, false );"/>
                                                    </label></td>
                                                <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td align="center" bgcolor="#FFFFFF"><label>
                                                        <img src="<%=imagesURL%>/next1.gif" name="selectall" id="button2" style="width:24px"  onclick="moveDualList( document.form1.grouplist,  document.form1.attachedlist, true  );"/>
                                                    </label></td>
                                                <td align="center" bgcolor="#FFFFFF"><label>
                                                        <!--<img src="<%=imagesURL%>/top.gif" name="button5" id="button5" style="width:24px" onclick="validate1();"/>-->
                                                    </label></td>
                                            </tr>
                                            <tr>
                                                <td align="center" bgcolor="#FFFFFF"><label>
                                                        <img src="<%=imagesURL%>/previous.gif" name="removeone" id="button3" style="width:24px" onclick="moveDualList(document.form1.attachedlist, document.form1.grouplist,  false );"/>
                                                    </label></td>
                                                <td align="center" bgcolor="#FFFFFF"><label>
                                                        <!--<img src="<%=imagesURL%>/bottom.gif" name="button6" id="button6" style="width:24px"  onclick="validate2();"/>-->
                                                    </label></td>
                                            </tr>
                                            <tr>
                                                <td align="center" bgcolor="#FFFFFF"><label>
                                                        <img src="<%=imagesURL%>/previous1.gif" name="removeall" id="button4" style="width:24px"  onclick="moveDualList(document.form1.attachedlist, document.form1.grouplist,  true  );"/>
                                                    </label></td>
                                                <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                            </tr>

                                        </table></td>
                                </tr>
                                <tr>
                                    <td colspan="3">
                                        <table width="100%">
                                            <tr>

                                                <td width="50%" align="right">
                                                    <input type="submit" name="Next" id="Next" value="Next" style="width:70px;" onClick="return validate();"/>
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
                <%} else {%>
                <%-- <tr>
                     <td>&nbsp;</td>
                 </tr>
                 <tr><td>
                         <table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#333333">
                             <tr>
                                 <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                         <tr>
                                             <td height="25" align="left" class="blue"><b class="heading">&nbsp;ATTACH <%=group.toUpperCase()%> TO <%=modelNo.toUpperCase()%></b></td>
                                         </tr>--%>

                <tr>
                    <td>
                        <table width="100%" border="0" cellspacing="2" cellpadding="2">
                            <tr><td>&nbsp;</td></tr>
                            <tr>
                                <td colspan="3" align="center"><table width="60%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                        <tr>
                                            <td height="30" align="center" class="grey"><b>&nbsp;<span class="heading">No Complete <%=group%> available in Database.</span></b></td>
                                        </tr>

                                    </table></td>
                            </tr>
                            <tr><td>&nbsp;</td></tr>
                        </table>
                    </td>
                </tr>
                <%--</table>
            </td></tr>
        </table>
        </td></tr>--%>
                <%}%>

                <%}%>
            </table>
        </div>
        <%
                                out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
<%
            } catch (Exception e) {
                e.printStackTrace();
            }
%>