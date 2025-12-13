

<%-- 
    Document   : EAMG_delete_unused_tools
     Author     : avinash.pandey
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*,EAMG_MethodsUtility_Package.EAMG_MethodUtility3"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>

        <%
                    response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                    response.setHeader("Expires", "0");
                    response.setHeader("Pragma", "no-cache");
                    String contextPath = request.getContextPath();
                    String comp_type = request.getParameter("comp_type");
                    Connection conn = null;
                    ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
                    String server_name = (String) session.getValue("server_name");
                    String ecatPath = (String) session.getValue("ecatPATH");
                    String mainURL = (String) session.getValue("mainURL");
                    PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
                    String imagesURL = "";
                    imagesURL = object_pageTemplate.imagesURL();
                    String session_id = session.getId();
                    String getSession = (String) session.getValue("session_value");
                    if (!session_id.equals(getSession)) {
                        object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                        return;
                    }

                    String heading = null;
                    int width = 659;
                    int height = 84;



                    if (comp_type == null) {


                        String tdData = "MANAGE TOOLS/LUBES >> DELETE UNUSED TOOLS/LUBES";
                        object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
                        heading = "DELETE UNUSED TOOLS/LUBES";
                        out.println(object_pageTemplate.tableHeader(heading, width, height));



        %>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script>
            function validate()
            {
                if(document.form1.comp_type.value=="")
                {
                    alert("Please select component type.");
                    return false;
                }

            }
        </script>
    <body>


        <form name="form1"  action="<%=contextPath%>/authJSP/EAMG_Tool/EAMG_delete_unused_tools.jsp" onsubmit="return validate();">

            <table width="85%" border="0" cellspacing="2" cellpadding="2">

                <tr>
                    <td width="6%" height="25" align="center" class="text">
                        &nbsp;
                    </td>
                    <td width="50%" height="25" align="left" class="text">
                        &nbsp;Select Component Type &nbsp;
                    </td>
                    <td width="44%" height="25" align="left" >
                        <select name="comp_type" id="comp_type" class="GroupSelect">
                            <option value="">Select Here</option>
                            <option value="Lubes">Lubes</option>
                            <option value="Tool">Tool</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <td colspan="3" height="25" align="center" class="text">
                        <input type="submit" value="Submit">
                    </td>

                </tr>
            </table></form>





        <%} else {


                                Vector toolslistVec = new Vector();
                                Vector toolsnolistVec = new Vector();
                                Vector unusedtoolslistVec = new Vector();
                                try {
                                    conn = holder.getConnection();
                                    ResultSet rs;
                                    Statement stmt = null;
                                    stmt = conn.createStatement();

                                    String query = "SELECT DISTINCT part_no FROM CAT_Part WHERE part_type='" + comp_type + "' order by part_no";
                                    rs = stmt.executeQuery(query);

                                    while (rs.next()) {
                                        //System.out.println("Part="+rs.getString(1));
                                        toolslistVec.add(rs.getString(1));
                                    }
                                    rs.close();

                                    String query1 = "SELECT DISTINCT component FROM CAT_Group_kit_bom WHERE comp_type='" + comp_type + "'";
                                    rs = stmt.executeQuery(query1);

                                    while (rs.next()) {
                                        toolsnolistVec.add(rs.getString(1));
                                    }
                                    rs.close();

                                    query1 = "SELECT DISTINCT component FROM CAT_s_kit_bom WHERE comp_type='" + comp_type + "'";
                                    rs = stmt.executeQuery(query1);

                                    while (rs.next()) {
                                        toolsnolistVec.add(rs.getString(1));
                                    }
                                    rs.close();

                                    query1 = "SELECT DISTINCT component FROM CAT_ASSY_BOM WHERE comp_type='" + comp_type + "'";
                                    rs = stmt.executeQuery(query1);

                                    while (rs.next()) {
                                        toolsnolistVec.add(rs.getString(1));
                                    }
                                    rs.close();

                                    query1 = "SELECT DISTINCT part_no FROM cat_part_model_matrix";
                                    rs = stmt.executeQuery(query1);

                                    while (rs.next()) {
                                        toolsnolistVec.add(rs.getString(1));
                                    }
                                    rs.close();

                                    for (int i = 0; i < toolslistVec.size(); i++) {
                                        String comp = "" + toolslistVec.elementAt(i);
                                        //System.out.println("comp:"+comp);
                                        //System.out.println(toolslistVec.contains(comp));
                                        if (!toolsnolistVec.contains(comp)) {
                                            unusedtoolslistVec.addElement(comp);
                                        }
                                    }
                                    //System.out.println("unusedtoolslistVec::::"+unusedtoolslistVec);
                                    stmt.close();
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
        %>
    <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
    <script language="javascript" type="text/javascript">
        // Compare two options within a list by VALUES
        var len = 0;
        var l1=0,l2=0;
        function compareOptionValues(a, b)
        
        {
        
            // Radix 10: for numeric values
        
            // Radix 36: for alphanumeric values
        
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
        
        function moveDualList( toolslist, unusedtoollist, moveAll )
        
        {
        
            // Do nothing if nothing is selected
        
            if (  ( toolslist.selectedIndex == -1 ) && ( moveAll == false )   )
        
            {
        
                return;
        
            }
            newunusedtoollist = new Array( unusedtoollist.options.length );
        
        
        
            for( len = 0; len < unusedtoollist.options.length; len++ )
        
            {
        
                if ( unusedtoollist.options[ len ] != null )
        
                {
        
                    newunusedtoollist[ len ] = new Option( unusedtoollist.options[ len ].text, unusedtoollist.options[ len ].value, unusedtoollist.options[ len ].defaultSelected, unusedtoollist.options[ len ].selected );
        
                }
        
            }
        
            for( var i = 0; i < toolslist.options.length; i++ )
        
            {
        
                if ( toolslist.options[i] != null && ( toolslist.options[i].selected == true || moveAll ) )
        
                {
        
                    // Statements to perform if option is selected
        
                    // Incorporate into new list
        
                    newunusedtoollist[ len ] = new Option( toolslist.options[i].text, toolslist.options[i].value, toolslist.options[i].defaultSelected, toolslist.options[i].selected );
        
                    len++;
        
                }
        
            }
            // Sort out the new destination list
        
            // newunusedkitlist.sort( compareOptionValues );   // BY VALUES
        
            // newunusedkitlist.sort( compareOptionText );   // BY TEXT
            // Populate the destination with the items from the new array
        
            for ( var j = 0; j < newunusedtoollist.length; j++ )
        
            {
        
                if ( newunusedtoollist[ j ] != null )
        
                {
        
                    unusedtoollist.options[ j ] = newunusedtoollist[ j ];
                    unusedtoollist.options[ j ].value=unusedtoollist.options[ j ].text;
                    //alert(unusedkitlist.options[ j ].text);
                }
        
            }
            // Erase source list selected elements
        
            for( var i = toolslist.options.length - 1; i >= 0; i-- )
        
            {
        
                if ( toolslist.options[i] != null && ( toolslist.options[i].selected == true || moveAll ) )
        
                {
        
                    // Erase Source
        
                    //toolslist.options[i].value = "";
        
                    //toolslist.options[i].text  = "";
        
                    toolslist.options[i]       = null;
        
                }
        
            }
            setlengths();
        }
        function setlengths()
        {
            document.getElementById("totalgrouplength").innerHTML=document.getElementById("toolslist").options.length;
            document.getElementById("selectedgrouplength").innerHTML=document.getElementById("unusedtoollist").options.length;

        }
        function searchelement()
        {
            var length=document.getElementById("toolslist").options.length;
            var searchtext=document.getElementById("searchtool").value;
            var searchtextlength=searchtext.length;
            searchtextlength=searchtextlength;
            for(i=0;i<length;i++)
            {
                var text=document.getElementById("toolslist").options[i].text;
                text=text.toUpperCase();
                var index= text.indexOf(searchtext.toUpperCase());
                if(index!=-1)
                {
                    document.getElementById("toolslist").options[i].selected=true;

                }
                else
                {
                    document.getElementById("toolslist").options[i].selected=false;
                }
            }
        }
        
        function validate()
        {

            var length=document.getElementById("unusedtoollist").options.length;

            if(length==0)
            {
                alert("No Tools are attached, Please Select atleast one <%=comp_type%> to Delete.");
                return false;
            }
            else
            {
                for(i=0;i<length;i++)
                {
                    document.getElementById("unusedtoollist").options[i].selected=true;
                }
                return true;
            }
        }
        function CancelProcess()
        {
            // var cncl=confirm("Do You Really want to Cancel the Current Process ?");
            // if(cncl==true)
            {
                location.href="<%=contextPath%>/authJSP/EAMG_Tool/EAMG_delete_unused_tools.jsp";
            }
	
        }
                   


    </script>

</head>

<%
                        String tdData = "MANAGE " + "" + comp_type.toUpperCase() + " >> DELETE UNUSED " + "" + comp_type.toUpperCase();
                        object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
                        heading = "DELETE UNUSED " + comp_type.toUpperCase() + "S";
                        out.println(object_pageTemplate.tableHeader(heading, width, height));
%>

<%
                        if (unusedtoolslistVec.size() == 0) {

                            //System.out.println("comp_type"+comp_type);
                            //System.out.println("unusedtoolslistVec"+unusedtoolslistVec);
%>

<body>

    <div align="center">
        <table width="700" border="0" cellspacing="1" cellpadding="1">
            <tr>
                <td>&nbsp;</td>
            </tr>
            <%--<tr>
                <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                        <tr>
                            <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=comp_type%> >> Delete Unused Tools </b></td>
                        </tr>
                    </table></td>
            </tr>


                <tr>
                    <td valign="top">&nbsp;</td>
                </tr>--%>
            <tr>
                <td valign="top" class="red" align="center">No Unused <%=comp_type%> exists in Database.</td>
            </tr>

        </table>
    </div>
    <%} else {%>
<body onload="setlengths();">
    <div align="center">
        <table width="700" border="0" cellspacing="1" cellpadding="1">
            <%--<tr>
                <td>&nbsp;</td>
            </tr>
            <tr>
                <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                        <tr>
                            <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=comp_type%> >> Delete Unused Tools </b></td>
                        </tr>
                    </table></td>
            </tr>
            <tr>
                <td align="right" class="small">&nbsp;</td>
            </tr>

                <tr>
                    <td valign="top">&nbsp;</td>
                </tr>

                <tr>
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;DELETE UNUSED TOOLS </b></td>
                            </tr>--%>
            <tr>
                <td align="center" valign="top" bgcolor="#FFFFFF">
                    <form name="form1" action="<%=contextPath%>/EAMG_deleteunusedtools.do" method="post" name="form1" id="form1"><input type="hidden"  name="comp_type" value="<%=comp_type%>">

                        <table width="95%" border="0" cellspacing="2" cellpadding="2">
                            <tr>
                                <td width="30%" class="text" align="left">&nbsp;Search Unused <%=comp_type%>s:
                                </td>
                                <td width="70%" align="left"><label>
                                        <input type="text" size="13" name="searchtool" id="searchtool" value=""/>
                                    </label><img style="vertical-align:bottom" id='searchimg' border='0' title="Search" src='<%=imagesURL%>/go.gif' onclick="searchelement();"/></td>
                            </tr>

                            <tr>
                                <td colspan="3" align="right"><table width="100%" border="0" cellpadding="1" cellspacing="1">
                                        <tr>
                                            <td width="43%" align="center"><table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                                    <tr>
                                                        <td width="43%" height="25" align="center" class="blue"><strong class="heading">Available Unused <%=comp_type%>s </strong></td>
                                                    </tr>
                                                </table></td>
                                            <td width="7%" bgcolor="#FFFFFF">&nbsp;</td>
                                            <td width="43%" align="center"><table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                                    <tr>
                                                        <td width="43%" height="25" align="center" class="blue"><strong class="heading">Selected <%=comp_type%>s </strong></td>
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
                                            <td rowspan="6" bgcolor="#FFFFFF">
                                                <select name="toolslist" id="toolslist" size="12" multiple="multiple" style="width:300px " class="text">
                                                    <%
                                                                                Iterator i = unusedtoolslistVec.iterator();
                                                                                while (i.hasNext()) {%>
                                                    <option><%=(String) i.next()%></option>
                                                    <%}%>
                                                </select></td>
                                            <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                            <td rowspan="6" bgcolor="#FFFFFF">
                                                <select name="unusedtoollist" id="unusedtoollist" size="12" multiple="multiple" style="width:300px " class="text">
                                                    <%
                                                                                Vector deletedtoolsVec = new Vector();
                                                                                Iterator i1 = deletedtoolsVec.iterator();
                                                                                while (i1.hasNext()) {
                                                                                    String val = (String) i1.next();%>
                                                    <option value='<%=val%>'><%=val%></option>
                                                    <% }%>
                                                </select></td>
                                            <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center" bgcolor="#FFFFFF"><label>

                                                    <img src="<%=imagesURL%>/next.gif" name="selectone" id="button" style="width:24px"  onclick="moveDualList( document.form1.toolslist,  document.form1.unusedtoollist, false );"/>
                                                </label></td>
                                            <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                        </tr>
                                        <tr>
                                            <td align="center" bgcolor="#FFFFFF"><label>
                                                    <img src="<%=imagesURL%>/next1.gif" name="selectall" id="button2" style="width:24px" onclick="moveDualList( document.form1.toolslist,  document.form1.unusedtoollist, true  );"/>
                                                </label></td>

                                        </tr>
                                        <tr>
                                            <td align="center" bgcolor="#FFFFFF"><label>
                                                    <img src="<%=imagesURL%>/previous.gif" name="removeone" id="button3" style="width:24px"  onclick="moveDualList(document.form1.unusedtoollist, document.form1.toolslist,  false );"/>
                                                </label></td>

                                        </tr>
                                        <tr>
                                            <td align="center" bgcolor="#FFFFFF"><label>
                                                    <img src="<%=imagesURL%>/previous1.gif" name="removeall" id="button4" style="width:24px" onclick="moveDualList(document.form1.unusedtoollist, document.form1.toolslist,  true  );"/>
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
            <%-- </table></td>
     </tr>--%>
        </table>
    </div>
    <%}%>

    <%}
                out.println(object_pageTemplate.tableFooter());
    %>
</body>
</html>


