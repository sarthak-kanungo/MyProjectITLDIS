

<%-- 
    Document   : EAMG_delete_unused_kits
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
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String context = request.getContextPath();
            Connection conn = null;
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            String contextPath = request.getContextPath();
            servletURL = object_pageTemplate.servletURL();
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
%>
<%
            Vector kitslistVec = new Vector();
            Vector kitsnolistVec = new Vector();
            Vector unusedkitslistVec = new Vector();
            try {
                conn = holder.getConnection();
                ResultSet rs;
                Statement stmt = null;
                String query = " SELECT DISTINCT part_no FROM cat_Part WHERE part_type='KIT' order by part_no";
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);

                while (rs.next()) {
                    kitslistVec.add(rs.getString(1));
                }
                rs.close();

                String query1 = "SELECT DISTINCT COMPONENT from CAT_GROUP_KIT_BOM GKB,CAT_PART P where P.PART_NO=GKB.COMPONENT AND P.part_type='KIT' ";
                rs = stmt.executeQuery(query1);

                while (rs.next()) {
                    kitsnolistVec.add(rs.getString(1));
                }
                rs.close();

                              
                query1 = "select COMPONENT from CAT_ASSY_BOM WHERE COMP_TYPE='KIT'";
                rs = stmt.executeQuery(query1);

                while (rs.next()) {
                    kitsnolistVec.add(rs.getString(1));
                }

                rs.close();

                 query1 = "select PART_NO from CAT_PART_MODEL_MATRIX";
                rs = stmt.executeQuery(query1);

                while (rs.next()) {
                    kitsnolistVec.add(rs.getString(1));
                }

                rs.close();

                
                for (int i = 0; i < kitslistVec.size(); i++) {
                    String comp = "" + kitslistVec.elementAt(i);
                    //System.out.println("comp:"+comp);
                    //System.out.println(kitslistVec.contains(comp));
                    if (!kitsnolistVec.contains(comp)) {
                        unusedkitslistVec.addElement(comp);
                    }
                }
                //System.out.println("unusedkitslistVec::::"+unusedkitslistVec);
                stmt.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
%>
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
        
            function moveDualList( kitslist, unusedkitlist, moveAll ) 
        
            {
        
                // Do nothing if nothing is selected
        
                if (  ( kitslist.selectedIndex == -1 ) && ( moveAll == false )   )
        
                {
        
                    return;
        
                }
                newunusedkitlist = new Array( unusedkitlist.options.length );
        
        
        
                for( len = 0; len < unusedkitlist.options.length; len++ ) 
        
                {
        
                    if ( unusedkitlist.options[ len ] != null )
        
                    {
        
                        newunusedkitlist[ len ] = new Option( unusedkitlist.options[ len ].text, unusedkitlist.options[ len ].value, unusedkitlist.options[ len ].defaultSelected, unusedkitlist.options[ len ].selected );
        
                    }
        
                }
        
                for( var i = 0; i < kitslist.options.length; i++ ) 
        
                { 
        
                    if ( kitslist.options[i] != null && ( kitslist.options[i].selected == true || moveAll ) )
        
                    {
        
                        // Statements to perform if option is selected
        
                        // Incorporate into new list
        
                        newunusedkitlist[ len ] = new Option( kitslist.options[i].text, kitslist.options[i].value, kitslist.options[i].defaultSelected, kitslist.options[i].selected );
        
                        len++;
        
                    }
        
                }
                // Sort out the new destination list
        
                // newunusedkitlist.sort( compareOptionValues );   // BY VALUES
        
                // newunusedkitlist.sort( compareOptionText );   // BY TEXT
                // Populate the destination with the items from the new array
        
                for ( var j = 0; j < newunusedkitlist.length; j++ ) 
        
                {
        
                    if ( newunusedkitlist[ j ] != null )
        
                    {
        
                        unusedkitlist.options[ j ] = newunusedkitlist[ j ];
                        unusedkitlist.options[ j ].value=unusedkitlist.options[ j ].text;
                        //alert(unusedkitlist.options[ j ].text);
                    }
        
                }
                // Erase source list selected elements
        
                for( var i = kitslist.options.length - 1; i >= 0; i-- ) 
        
                { 
        
                    if ( kitslist.options[i] != null && ( kitslist.options[i].selected == true || moveAll ) )
        
                    {
        
                        // Erase Source
        
                        //kitslist.options[i].value = "";
        
                        //kitslist.options[i].text  = "";
        
                        kitslist.options[i]       = null;
        
                    }
        
                }
                setlengths();
            }
            function setlengths()
            {
                document.getElementById("totalgrouplength").innerHTML=document.getElementById("kitslist").options.length;
                document.getElementById("selectedgrouplength").innerHTML=document.getElementById("unusedkitlist").options.length;

            }
            function searchelement()
            {
                var length=document.getElementById("kitslist").options.length;
                var searchtext=document.getElementById("searchkit").value;
                var searchtextlength=searchtext.length;
                searchtextlength=searchtextlength;
                for(i=0;i<length;i++)
                {
                    var text=document.getElementById("kitslist").options[i].text;
                    text=text.toUpperCase();
                    var index= text.indexOf(searchtext.toUpperCase());
                    if(index!=-1)
                    {
                        document.getElementById("kitslist").options[i].selected=true;

                    }
                    else
                    {
                        document.getElementById("kitslist").options[i].selected=false;
                    }
                }
            }
        
            function validate()
            {

                var length=document.getElementById("unusedkitlist").options.length;

                if(length==0)
                {
                    alert("No Kits are attached, Please Select atleast one Kit to Delete.");
                    return false;
                }
                else
                {
                    for(i=0;i<length;i++)
                    {
                        document.getElementById("unusedkitlist").options[i].selected=true;
                    }
                    return true;
               }
            }
            function CancelProcess()
            {
                //var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                //if(cncl==true)
                {
                    location.href="<%=context%>/common/EAMG_home_page.jsp";
                }
	
            }
                   


        </script> 

    </head>
    <%
                String tdData = "MANAGE KIT >> DELETE UNUSED KITS";
                object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
    %>
    <%
                heading = "DELETE UNUSED KITS";
                out.println(object_pageTemplate.tableHeader(heading, width, height));
    %>
    <%
                if (unusedkitslistVec.size() == 0) {
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
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Kit >> Delete Unused Kits </b></td>
                            </tr>
                        </table></td>
                </tr>


                <tr>
                    <td valign="top">&nbsp;</td>
                </tr>--%>
                <tr>
                    <td valign="top" class="red" align="center">No Unused Kit exists in Database.</td>
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
                <%-- <tr>
                     <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                             <tr>
                                 <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Kit >> Delete Unused Kits </b></td>
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
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;DELETE UNUSED KITS </b></td>
                            </tr>--%>
                <tr>
                    <td align="center" valign="top" bgcolor="#FFFFFF">
                        <form name="form1" action="<%=context%>/EAMG_deleteunusedkits.do" method="post" name="form1" id="form1">

                            <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                <tr>
                                    <td width="30%" class="text" align="left">&nbsp;Search Unused Kits:
                                    </td>
                                    <td width="70%" align="left"><label>
                                            <input type="text" size="13" name="searchkit" id="searchkit" value=""/>
                                        </label><img style="vertical-align:bottom" id='searchimg' border='0' title="Search" src='<%=imagesURL%>/go.gif' onclick="searchelement();"/></td>
                                </tr>
                                <tr>
                                    <td colspan="3">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td colspan="3" align="right"><table width="100%" border="0" cellpadding="1" cellspacing="1">
                                            <tr>
                                                <td width="43%" align="center"><table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                                        <tr>
                                                            <td width="43%" height="25" align="center" class="blue"><strong class="heading">Available Unused Kits </strong></td>
                                                        </tr>
                                                    </table></td>
                                                <td width="7%" bgcolor="#FFFFFF">&nbsp;</td>
                                                <td width="43%" align="center"><table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0"  bordercolor="#CCCCCC">
                                                        <tr>
                                                            <td width="43%" height="25" align="center" class="blue"><strong class="heading">Selected Kits </strong></td>
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
                                                    <select name="kitslist" id="kitslist" size="12" multiple="multiple" style="width:300px " class="text">
                                                        <%
                                                                            Iterator i = unusedkitslistVec.iterator();
                                                                            while (i.hasNext()) {%>
                                                        <option><%=(String) i.next()%></option>
                                                        <%}%>
                                                    </select></td>
                                                <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                <td rowspan="6" bgcolor="#FFFFFF">
                                                    <select name="unusedkitlist" id="unusedkitlist" size="12" multiple="multiple" style="width:300px " class="text">
                                                       
                                                    </select></td>
                                                <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td align="center" bgcolor="#FFFFFF"><label>

                                                        <img src="<%=imagesURL%>/next.gif" name="selectone" id="button" style="width:24px"  onclick="moveDualList( document.form1.kitslist,  document.form1.unusedkitlist, false );"/>
                                                    </label></td>
                                                <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td align="center" bgcolor="#FFFFFF"><label>
                                                        <img src="<%=imagesURL%>/next1.gif" name="selectall" id="button2" style="width:24px" onclick="moveDualList( document.form1.kitslist,  document.form1.unusedkitlist, true  );"/>
                                                    </label></td>

                                            </tr>
                                            <tr>
                                                <td align="center" bgcolor="#FFFFFF"><label>
                                                        <img src="<%=imagesURL%>/previous.gif" name="removeone" id="button3" style="width:24px"  onclick="moveDualList(document.form1.unusedkitlist, document.form1.kitslist,  false );"/>
                                                    </label></td>

                                            </tr>
                                            <tr>
                                                <td align="center" bgcolor="#FFFFFF"><label>
                                                        <img src="<%=imagesURL%>/previous1.gif" name="removeall" id="button4" style="width:24px" onclick="moveDualList(document.form1.unusedkitlist, document.form1.kitslist,  true  );"/>
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

                                                <td width="50%" align="center">
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


