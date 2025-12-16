<%-- 
    Document   : amw_Assign_AMW_vs_OME_Matrix
    Created on : Nov 11, 2011, 4:21:23 PM
    Author     : avinash.pandey
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="dbConnection.amw_ConnectionThread,java.sql.*, java.util.*" %>
<%
    response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
    response.setHeader("Expires", "0");
    response.setHeader("Pragma", "no-cache");
%>
<%
    Connection conn = amw_ConnectionThread.getConnection();
    ResultSet rs;
    Statement stmt = null;
    Vector totalOEMPart = new Vector();
    Vector allAMWPart = new Vector();
    String query1 = "Select distinct(AMW_PART_NO) from AMW_VS_OEM_MATRIX order by AMW_PART_NO";
    stmt = conn.createStatement();
    rs = stmt.executeQuery(query1);
    while (rs.next()) {
        String temp1 = rs.getString(1);
        allAMWPart.add(temp1);
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
        <title>AMW-Ecatalogue</title>
        <link href="/AMW-AuthEcat/css/config.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="/AMW-AuthEcat/js/autosuggest1.js"></script>
        <script type="text/javascript" src="/AMW-AuthEcat/js/validations.js"></script>
        <script language="javascript">
            var custom_array =new Array();
            <%
                for (int i = 0; i < allAMWPart.size(); i++) {
            %>
                custom_array[<%=i%>]='<%="" + allAMWPart.get(i)%>';
            <%}%>
                function moveDualList( omePartlist, attachedlist, moveAll )
                {
                    if((omePartlist.selectedIndex == -1)&&(moveAll == false))
                    {
                        return;
                    }
                    newattachedlist = new Array( attachedlist.options.length );
                    for( len = 0; len < attachedlist.options.length; len++ )
                    {
                        if ( attachedlist.options[ len ] != null )
                        {
                            newattachedlist[ len ] = new Option( attachedlist.options[ len ].text, attachedlist.              options[ len ].value, attachedlist.options[ len ].defaultSelected, attachedlist.options[              len ].selected );
                        }
                    }
                    for( var i = 0; i < omePartlist.options.length; i++ )
                    {
                        if(omePartlist.options[i]!=null&&( omePartlist.options[i].selected== true ||moveAll))
                        {
                            newattachedlist[ len ] = new Option( omePartlist.options[i].text, omePartlist.options[i].value, omePartlist.options[i].defaultSelected, omePartlist.options[i].selected );
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

                    for( var i = omePartlist.options.length - 1; i >= 0; i-- )
                    {
                        if ( omePartlist.options[i] != null && ( omePartlist.options[i].selected == true || moveAll ) )
                        {
                            omePartlist.options[i]= null;
                        }
                    }
                    setlengths();
                }

                function setlengths()
                {
                    document.getElementById("totalOMEPart").innerHTML=document.getElementById("omePartlist").options.length;
                    document.getElementById("selectedOMEPart").innerHTML=document.getElementById("attachedlist").options.length;
                }
                function GetXmlHttpObject()
                {
                    var objXmlHttp = null;
                    if (navigator.userAgent.indexOf('Opera') >= 0)
                    {
                        xmlHttp=new XMLHttpRequest();
                        return xmlHttp;
                    }
                    if (navigator.userAgent.indexOf('MSIE') >= 0)
                    {
                        var strName = 'Msxml2.XMLHTTP';
                        if (navigator.appVersion.indexOf('MSIE 5.5') >= 0)
                        {
                            strName = 'Microsoft.XMLHTTP';
                        }
                        try
                        {
                            objXmlHttp = new ActiveXObject(strName);
                            // objXmlHttp.onreadystatechange = handler;
                            return objXmlHttp;
                        }
                        catch(e)
                        {
                            alert('Your Browser Does Not Support Ajax');
                            return;
                        }
                    }
                    if (navigator.userAgent.indexOf ('Mozilla') >= 0)
                    {
                        objXmlHttp = new XMLHttpRequest();
                        return objXmlHttp;
                    }
                }
                function showAssigedPart()
                {
                    var searchAMWPart=document.getElementById("searchAMWPart").value;
                    //alert(searchAMWPart); 
                    var strURL = "/AMW-AuthEcat/getSelectedOMEPartByAMWPart.do?option=getSelectedOMEPartByAMWPart&searchAMWPart="+searchAMWPart;
                    xmlHttp = GetXmlHttpObject();
                    xmlHttp.onreadystatechange = function()
                    {
                        stateChangedOME();
                    };
                    xmlHttp.open("POST", strURL, true);
                    xmlHttp.send(null);

                }
                function stateChangedOME ()
                {
                    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                    {
                        document.getElementById('tempDivId').innerHTML='';
                        res = xmlHttp.responseText;
                        document.getElementById('tempDivId').innerHTML=res;
                        setlengths();
                    }

                }
                
                function validate()
                {
                    
                    var searchAMWPart=document.getElementById("searchAMWPart").value;
                    if(searchAMWPart==""||searchAMWPart==null)
                    {
                        alert("Enter the AMW Part Number.");
                        document.getElementById("searchAMWPart").value="";
                        document.getElementById("searchAMWPart").focus();
                        return false;
                    }
                    var length=document.getElementById("attachedlist").options.length;
                    if(length==0)
                    {
                        alert("No OME Part are attached. Please Select atleast one OME Part.");
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
                    //if(cncl==true)
                    {
                        parent.right.location="/AMW-AuthEcat/common/amw_home_page.jsp";
                    }

                }

        </script>

    </head>

    <%

        if (allAMWPart.size() < 1) {
    %>
    <body >
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage AMW OME Matrix >> Assign AMW vs OME Matrix </b></td>
                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td  colspan="3" align="left"><html:errors/> </td>
                </tr>
                <tr>
                    <td align="right" class="small">&nbsp;</td>
                </tr>

                <tr>
                    <td valign="top">&nbsp;</td>
                </tr>
                <tr>
                    <td valign="top" class="red" align="center">No Data available in Database.</td>
                </tr>

            </table>
        </div>
        <%} else {%>


        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage AMW OME Matrix >> Assign AMW vs OME Matrix </b></td>
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
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;ASSIGN AMW VS OME MATRIX  </b></td>
                            </tr>
                            <tr>
                                <td align="center" valign="top" bgcolor="#FFFFFF">
                                    <form name="form1" action="/AMW-AuthEcat/amw_AMW_VS_OMEAction.do" method="post">
                                        <input type="hidden" value="assign_AMW_VS_OME" name="option" id="option"/>

                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                                <td width="30%" class="text" align="left">&nbsp;Search AMW Part:
                                                </td>
                                                <td width="70%" align="left"><label>
                                                        <input type="text" size="13"  id="searchAMWPart" name="searchAMWPart" value="" />
                                                    </label>
                                                    <img style="vertical-align:bottom" id='searchimg' border='0' title="Search" src='/AMW-AuthEcat/auth_images/go.gif' onclick="showAssigedPart();"/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="3">&nbsp;</td>
                                            </tr>
                                            <tr  bgcolor=#ffffff ><td colspan="4" class=text align=center><span id="tempDivId" align=center></span></td></tr>
                                        </table>
                                    </form></td>
                            </tr>
                        </table></td>
                </tr>
            </table>
        </div>
        <%}%>
        <script>new actb1('searchAMWPart', custom_array); </script>
    </body>
</html>
