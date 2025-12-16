<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="viewEcat.comEcat.ConnectionHolder,java.sql.Connection,java.io.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            Connection conn = null;
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
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
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }
            String heading = null;
            int width = 659;
            int height = 84;
%>
<%

            Vector unusedpartlist = new Vector();
            Vector partlist = new Vector();
            Vector selectedlistvec = new Vector();



            try {
                ResultSet rs;
                Statement stmt = null;
                conn = holder.getConnection();
                String query1 = "select part_no from cat_PART where part_type='PRT'";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query1);
                while (rs.next()) {
                    partlist.add(rs.getString(1).trim());
                }
                rs.close();
                String query2 = "select COMPONENT from CAT_GROUP_KIT_BOM where comp_type='PRT'";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query2);
                while (rs.next()) {
                    selectedlistvec.add(rs.getString(1));
                }

                rs.close();

                String query4 = "select COMPONENT from CAT_s_kit_bom where comp_type='PRT'";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query4);

                while (rs.next()) {
                    selectedlistvec.add(rs.getString(1));
                }
                rs.close();
                query4 = "select COMPONENT from CAT_ASSY_BOM where comp_type='PRT'";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query4);

                while (rs.next()) {
                    selectedlistvec.add(rs.getString(1));
                }
                rs.close();
                query4 = "select PART_NO from CAT_PART_MODEL_MATRIX";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query4);

                while (rs.next()) {
                    selectedlistvec.add(rs.getString(1));
                }
                rs.close();


                //System.out.println("selectedlistvec:"+selectedlistvec);
                //System.out.println("partlist :"+partlist);


                for (int i = 0; i < partlist.size(); i++) {
                    //System.out.println("selectedlistvec=="+selectedlistvec.size()+"==partlist=="+partlist);
                    String comp = "" + partlist.elementAt(i);
                    ////System.out.println("comp:"+comp);
                    ////System.out.println(selectedlistvec.contains(comp));

                    if (!selectedlistvec.contains(comp)) {
                        unusedpartlist.addElement(comp);

                    }

                }
                ////System.out.println("unusedpartlist :"+unusedpartlist);
                stmt.close();
                //conn.close();
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
        <script language="javascript">
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
        
            function moveDualList( partlist, unusedpartslist, moveAll ) 
        
            {
        
                // Do nothing if nothing is selected
        
                if (  ( partlist.selectedIndex == -1 ) && ( moveAll == false )   )
        
                {
        
                    return;
        
                }
                newunusedpartslist = new Array( unusedpartslist.options.length );
        
        
        
                for( len = 0; len < unusedpartslist.options.length; len++ ) 
        
                {
        
                    if ( unusedpartslist.options[ len ] != null )
        
                    {
        
                        newunusedpartslist[ len ] = new Option( unusedpartslist.options[ len ].text, unusedpartslist.options[ len ].value, unusedpartslist.options[ len ].defaultSelected, unusedpartslist.options[ len ].selected );
        
                    }
        
                }
        
                for( var i = 0; i < partlist.options.length; i++ ) 
        
                { 
        
                    if ( partlist.options[i] != null && ( partlist.options[i].selected == true || moveAll ) )
        
                    {
        
                        // Statements to perform if option is selected
        
                        // Incorporate into new list
        
                        newunusedpartslist[ len ] = new Option( partlist.options[i].text, partlist.options[i].value, partlist.options[i].defaultSelected, partlist.options[i].selected );
        
                        len++;
        
                    }
        
                }
                // Sort out the new destination list
        
                // newunusedpartslist.sort( compareOptionValues );   // BY VALUES
        
                // newunusedpartslist.sort( compareOptionText );   // BY TEXT
                // Populate the destination with the items from the new array
        
                for ( var j = 0; j < newunusedpartslist.length; j++ ) 
        
                {
        
                    if ( newunusedpartslist[ j ] != null )
        
                    {
        
                        unusedpartslist.options[ j ] = newunusedpartslist[ j ];
                        unusedpartslist.options[ j ].value=unusedpartslist.options[ j ].text;
                        //alert(unusedpartslist.options[ j ].text);
                    }
        
                }
                // Erase source list selected elements
        
                for( var i = partlist.options.length - 1; i >= 0; i-- ) 
        
                { 
        
                    if ( partlist.options[i] != null && ( partlist.options[i].selected == true || moveAll ) )
        
                    {
        
                        // Erase Source
        
                        //partlist.options[i].value = "";
        
                        //partlist.options[i].text  = "";
        
                        partlist.options[i]       = null;
        
                    }
        
                }
                setlengths();
            }
            function setlengths()
            {
                document.getElementById("totalunusedpartslength").innerHTML=document.getElementById("partlist").options.length;
                document.getElementById("selectedunusedpartslength").innerHTML=document.getElementById("unusedpartslist").options.length;

            }
            function searchelement()
            {
                var length=document.getElementById("partlist").options.length;
                var searchtext=document.getElementById("searchunusedparts").value;
                var searchtextlength=searchtext.length;
                searchtextlength=searchtextlength;
        
        
                for(i=0;i<length;i++)
                {
                    var text=document.getElementById("partlist").options[i].text;
                    text=text.toUpperCase();
                    var index= text.indexOf(searchtext.toUpperCase());
                    if(index!=-1)
                    {
                        document.getElementById("partlist").options[i].selected=true;

                    }
                    else
                    {
                        document.getElementById("partlist").options[i].selected=false;
                    }
                }
            }
            function validate()
            {

                var length=document.getElementById("unusedpartslist").options.length;

                if(length==0)
                {
                    alert("No Parts are attached, Please Select atleast one Part to Delete.");
                    return false;
                }
                else
                {
                    for(i=0;i<length;i++)
                    {
                        document.getElementById("unusedpartslist").options[i].selected=true;

                    }
                    return true;
                }

            }
                  
            function CancelProcess()
            {
                var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                if(cncl==true)
                {
                    location.href="<%=context%>/common/EAMG_home_page.jsp";
                }
	
            }


        </script>
    </head>

    <%
                String tdData = "MANAGE PART >> DELETE UNUSED PARTS";
                object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
    %>
    <%
                heading = "DELETE UNUSED PARTS";
                out.println(object_pageTemplate.tableHeader(heading, width, height));
    %>




    <%

                if (unusedpartlist.size() < 1) {
    %>
    <body>

        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%--<tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Part >> Delete Unused Parts</b></td>
                            </tr>
                        </table></td>
                </tr>--%>
                <tr>
                    <td align="right" class="small">&nbsp;</td>
                </tr>

                <tr>
                    <td valign="top" class="red" align="center">No Unused Part available in Database.</td>
                </tr>

            </table>
        </div>
        <%} else {%>

    <body onload="setlengths();">

        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">

                <%--<tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Part >> Delete Unused Parts</b></td>
                            </tr>
                        </table></td>
                </tr>--%>
                <tr>
                    <td align="right" class="small">&nbsp;</td>
                </tr>

               <%-- <tr>
                    <td valign="top">
								<table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                           <%-- <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;DELETE UNUSED PARTS</b></td>
                            </tr>--%>
                            <tr>
                                <td align="center" valign="top" bgcolor="#FFFFFF">
                                    <form name="form1" action="<%=context%>/EAMG_deleteunusedparts.do" method="post">
                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                                <td width="30%" class="text" align="left">&nbsp;Search Unused Parts:
                                                </td>

                                                <td width="70%" align="left"><label>
                                                        <input type="text" size="13" id="searchunusedparts" name="searchunusedparts" value=""  style="width:125px"/>
                                                    </label>
                                                    <img style="vertical-align:bottom" id='searchimg' border='0' title="Search" src='<%=imagesURL%>/go.gif' onclick="searchelement();"/>
                                                </td>

                                            </tr>

                                            <tr>
                                                <td colspan="3" align="right"><table width="100%" border="0" cellpadding="1" cellspacing="1">
                                                        <tr>
                                                            <td width="43%" align="center"><table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                                                    <tr>
                                                                        <td width="43%" height="25" align="center" class="blue"><strong class="heading">Available Unused Parts</strong></td>
                                                                    </tr>
                                                                </table></td>
                                                            <td width="7%" bgcolor="#FFFFFF">&nbsp;</td>
                                                            <td width="43%" align="center"><table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                                                    <tr>
                                                                        <td width="43%" height="25" align="center" class="blue"><strong class="heading">Selected Parts</strong></td>
                                                                    </tr>
                                                                </table></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" bgcolor="#FFFFFF" class="text"># <span  id="totalunusedpartslength"></span></td>
                                                            <td bgcolor="#FFFFFF">&nbsp;</td>
                                                            <td align="right" bgcolor="#FFFFFF" class="text"># <span id="selectedunusedpartslength"></span></td>
                                                        </tr>
                                                        <tr>
                                                            <td rowspan="6" bgcolor="#FFFFFF">
                                                                <select name="partlist" size="12" multiple="multiple" style="width:300px;" class="text">
                                                                    <%
                                                                                        Iterator i = unusedpartlist.iterator();
                                                                                        while (i.hasNext()) {%>
                                                                    <option><%=(String) i.next()%></option>
                                                                    <%}
                                                                    %> 
                                                                </select></td>
                                                            <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                            <td rowspan="6" bgcolor="#FFFFFF">
                                                                <select name="unusedpartslist" size="12" multiple="multiple" style="width:300px " class="text">
                                                                    <%
                                                                                        Vector deletedVec = new Vector();
                                                                                        Iterator i1 = deletedVec.iterator();
                                                                                        while (i1.hasNext()) {
                                                                                            String val = (String) i1.next();
                                                                    %>
                                                                    <option value='<%=val%>'><%=val%></option>
                                                                    <%}
                                                                    %>

                                                                </select></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center" bgcolor="#FFFFFF"><label>                            
                                                                    <img src="<%=imagesURL%>/next.gif" name="selectone" style="width:24px " onclick="moveDualList( document.form1.partlist,  document.form1.unusedpartslist, false );" />
                                                                </label></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center" bgcolor="#FFFFFF"><label>
                                                                    <img src="<%=imagesURL%>/next1.gif" name="selectall" style="width:24px " onclick="moveDualList( document.form1.partlist,  document.form1.unusedpartslist, true  );"/>
                                                                </label></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center" bgcolor="#FFFFFF"><label>
                                                                    <img src="<%=imagesURL%>/previous.gif" name="removeone" style="width:24px " onclick="moveDualList(document.form1.unusedpartslist, document.form1.partlist,  false );"/>
                                                                </label></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center" bgcolor="#FFFFFF"><label>
                                                                    <img src="<%=imagesURL%>/previous1.gif" name="removeall" style="width:24px " onclick="moveDualList(document.form1.unusedpartslist, document.form1.partlist,  true  );"/>
                                                                </label></td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                        </tr>

                                                    </table></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td colspan="3">
                                                    <table width="100%">
                                                        <tr>

                                                            <td width="50%" align="right">
                                                                <input type="submit" name="Next" id="Next" value="Submit" style="width:70px;" onClick="return validate();"/>
                                                            </td>
                                                            <td width="50%" align="left">
                                                                <%--<input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />--%>
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
