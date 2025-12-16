<%-- 
    Document   : EAMG_create_tool_bom
    Author     : avinash.pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*,EAMG_MethodsUtility_Package.EAMG_MethodUtility3"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*" %>
<html>
    <head>

        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <script language="javascript">
            var unselected_Arr=new Array();
            <%
                        String contextPath = request.getContextPath();
                        response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                        response.setHeader("Expires", "0");
                        response.setHeader("Pragma", "no-cache");
                        Connection conn = null;
                        ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
                        String server_name = (String) session.getValue("server_name");
                        String ecatPath = (String) session.getValue("ecatPATH");
                        String mainURL = (String) session.getValue("mainURL");
                        PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
                        String servletURL = "";
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
                        String tdData = "";
                        String toolno = (String) session.getAttribute("toolno");
                        String option = (String) session.getAttribute("option");
                        //System.out.println(kitno);
                        String comp = "";
                        Vector tempVec = new Vector();
                        Vector allcompVec = new Vector();
                        Vector allselectedcompVec = new Vector();
                        Vector selectedparts = new Vector();
                        Vector totalparts = new Vector();
                        Vector selectedassembly = new Vector();
                        Vector totalassembly = new Vector();
                        Vector selectedTool = new Vector();
                        Vector totalTool = new Vector();
                        ResultSet rs = null;
                        Statement stmt = null;
                        try {
                            conn = holder.getConnection();



                            String query1 = "Select distinct part_no from cat_part where part_type='PRT'";
                            stmt = conn.createStatement();
                            rs = stmt.executeQuery(query1);

                            while (rs.next()) {
                                totalparts.add(rs.getString(1));


                            }
                            rs.close();

                            query1 = "Select distinct COMPONENT from CAT_S_KIT_BOM where KIT_NO='" + toolno + "'  and COMP_TYPE='PRT'";
                            stmt = conn.createStatement();
                            rs = stmt.executeQuery(query1);

                            while (rs.next()) {
                                selectedparts.add(rs.getString(1));


                            }
                            rs.close();

                            /*query1 = "Select COMP_NO from COMP_DETAIL where COMP_TYPE='ASM'";
                            stmt = conn.createStatement();
                            rs = stmt.executeQuery(query1);
                            if (rs != null) {
                            while (rs.next()) {
                            totalassembly.add(new String(rs.getString(1)));

                            }
                            }
                            rs.close();

                            query1 = "Select COMPONENT from S_KIT_BOM where KIT_NO='" + kitno + "' and COMP_TYPE='ASM'";
                            stmt = conn.createStatement();
                            rs = stmt.executeQuery(query1);
                            if (rs != null) {
                            while (rs.next()) {
                            selectedassembly.add(new String(rs.getString(1)));

                            }
                            }
                            rs.close();
                             */

                            query1 = "Select distinct part_no from cat_part where part_type='TOOL'";
                            stmt = conn.createStatement();
                            rs = stmt.executeQuery(query1);

                            while (rs.next()) {
                                comp = rs.getString(1);
                                totalTool.add(comp);
                                if (option.equals("modify")) {
                                    Vector bomVec = new Vector();
                                    bomVec.add(comp);
                                    bomVec = new EAMG_MethodUtility3().getComponentBom(comp, "TOOL", bomVec, conn);
                                    //System.out.println("comp :" + comp+"*******bomVec :"+bomVec);
                                    if (!bomVec.contains(toolno)) {
                                        tempVec.add(comp);
                                    }

                                }

                            }
                            rs.close();
                            //System.out.println("tempVec :"+tempVec);
                            query1 = "Select distinct  COMPONENT  from CAT_S_KIT_BOM where KIT_NO='" + toolno + "' and COMP_TYPE='TOOL'";
                            stmt = conn.createStatement();
                            rs = stmt.executeQuery(query1);
                            while (rs.next()) {
                                selectedTool.add(rs.getString(1).trim());
                            }
                            rs.close();

                            stmt.close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                        if (option.equals("modify")) {
                            totalTool.remove(totalTool);
                        }
                        allcompVec.addAll(totalparts);
                        allcompVec.addAll(totalassembly);
                        allcompVec.addAll(totalTool);

                        allselectedcompVec.addAll(selectedparts);
                        allselectedcompVec.addAll(selectedassembly);
                        allselectedcompVec.addAll(selectedTool);

                        //System.out.println("allcompVec :" + allcompVec);
                        if (option.equals("modify")) {

                            for (int i = 0; i < tempVec.size(); i++) {%>
                                unselected_Arr[<%=i%>]='<%=tempVec.elementAt(i)%>';
            <%}
                        }%>
                            var toolno='<%=toolno%>';

                            function showComponents(comp_type)
                            {
                                document.getElementById("img").style.visibility="visible";
                                document.getElementById("searchcomp").value="";
                                // alert(comp_type);
                                if(comp_type!='')
                                {
                   
                                    var strURL ="<%=contextPath%>/authJSP/EAMG_Tool/EAMG_get_components_with_type.jsp?comp_no=*&comp_type="+comp_type+"&creation=Tool";
                                    //alert(strURL)
                                    xmlHttp = GetXmlHttpObject();
                                    xmlHttp.onreadystatechange = function()
                                    {
                                        stateChanged();
                                    };
                                    xmlHttp.open("POST", strURL, true);
                                    xmlHttp.send(null);
                                }
                
                            }
                            function stateChanged()
                            {
                                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                                {

                                    res = xmlHttp.responseText;
                                    //alert(res);
                                    var tempCompArr = new Array();
                                    tempCompArr=res.split('|');
                                    //alert(tempCompArr.length)
                                    if(tempCompArr.length==1)
                                    {
                                        document.getElementById("img").style.visibility="hidden";
                                        alert('No Components available.');
                                        return false;
                                    }

                                    var complist=document.getElementById("complist");
                                    complist.options.length=0;
                                    var t=0;
                                    for(var i=0;i<tempCompArr.length-1;i++)
                                    {
                                        var comp=tempCompArr[i].split(' ');
                                        if(toolno!=comp[0])
                                        {
                                            complist.options[t]=new Option(tempCompArr[i]);
                                            complist.options[t].value=tempCompArr[i];
                                            complist.options[t].text=comp[0];
                                            t++;
                                        }

                                    }
                                    document.getElementById("img").style.visibility="hidden";
                                    setlengths();
                                }
                                /*res = xmlHttp.responseText;
                                    //alert(res);
                                    var tempCompArr = new Array();
                                    tempCompArr=res.split('|');
                                    //alert(tempCompArr.length)
                                    if(tempCompArr.length==1)
                                    {
                                        document.getElementById("img").style.visibility="hidden";
                                        alert('No Components available.');
                                        return false;
                                    }
                   
                                    var complist=document.getElementById("complist");
                                    complist.options.length=0;
                                    var t=0;
                                    for(var i=0;i<tempCompArr.length-1;i++)
                                    {
                                        var comp=tempCompArr[i].split(' ');
                                        if(kitno!=comp[0])
                                        {
                                            complist.options[t]=new Option(tempCompArr[i]);
                                            complist.options[t].value=tempCompArr[i];
                                            complist.options[t].text=comp[0];
                                            t++;
                                        }
                        
                                    }
                                    //document.getElementById("img").style.visibility="hidden";
                                    setlengths();
                                }*/
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
                                if (navigator.userAgent.indexOf('Mozilla') >= 0)
                                {
                                    objXmlHttp = new XMLHttpRequest();
                                    return objXmlHttp;
                                }
                            }
                            // var total_partArr=new Array();
                            //var total_assArr=new Array();
                            //var total_kitArr=new Array();
            
                            // Compare two options within a list by VALUES
            
                            var len = 0;
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
            
                            function moveDualList( unattachedlist, attachedlist, moveAll )
            
                            {
                
                                // Do nothing if nothing is selected
                
                                if (  ( unattachedlist.selectedIndex == -1 ) && ( moveAll == false )   )
                    
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
                
                                for( var i = 0; i < unattachedlist.options.length; i++ )
                    
                                {
                    
                                    if ( unattachedlist.options[i] != null && ( unattachedlist.options[i].selected == true || moveAll ) )
                        
                                    {
                        
                                        // Statements to perform if option is selected
                        
                                        // Incorporate into new list
                        
                                        newattachedlist[ len ] = new Option( unattachedlist.options[i].text, unattachedlist.options[i].value, unattachedlist.options[i].defaultSelected, unattachedlist.options[i].selected );
                        
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
                                        //attachedlist.options[ j ].value=attachedlist.options[ j ].text;
                                        //alert(attachedlist.options[ j ].text);
                                    }
                    
                                }
                                // Erase source list selected elements
                
                                for( var i = unattachedlist.options.length - 1; i >= 0; i-- )
                    
                                {
                    
                                    if ( unattachedlist.options[i] != null && ( unattachedlist.options[i].selected == true || moveAll ) )
                        
                                    {
                        
                                        // Erase Source
                        
                                        //unattachedlist.options[i].value = "";
                        
                                        //unattachedlist.options[i].text  = "";
                        
                                        //unattachedlist.options[i]       = null;
                        
                                    }
                    
                                }
                                setlengths();
                            } // End of moveDualList()
                            function setlengths()
                            {
                                document.getElementById("totalcomplength").innerHTML=document.getElementById("complist").options.length;
                                document.getElementById("selectedcomplength").innerHTML=document.getElementById("attachedlist").options.length;
                            }
            
            
            
                            function searchelement()
                            {
                                var length=document.getElementById("complist").options.length;
                                var searchtext=document.getElementById("searchcomp").value;
                                searchtext=searchtext.toUpperCase();
                                var searchtextlength=searchtext.length;
                                searchtextlength=searchtextlength;
                
                
                                for(i=0;i<length;i++)
                                {
                                    var text=document.getElementById("complist").options[i].text;
                                    text=text.toUpperCase();
                                    var index= text.indexOf(searchtext.toUpperCase());
                                    if(index!=-1)
                                    {
                                        document.getElementById("complist").options[i].selected=true;
                                
                                    }
                                    else
                                    {
                                        document.getElementById("complist").options[i].selected=false;
                                    }
                                }
                            }
                        
                            function validate_modify()
                            {
                                        
                                var length=document.getElementById("attachedlist").options.length;
                                var len1=unselected_Arr.length;
                                if(length==0)
                                {
                                    alert("Please attach atleast one Part, Tool.");
                                    return false;
                                }
                                if(length>250)
                                {
                                    var res=confirm("You want to attach '"+length+"' Number of Components to Tool '"+toolno+"'. Are you sure?");
                                    if(res==false)
                                    {
                                        return false;
                                    }
                                }
                                else
                                {
                                    var flag=false;
                                    for(i=0;i<length;i++)
                                    {
                                        flag=false;
                                        var val=document.getElementById("attachedlist").options[i].value;
                                        var pos1=val.indexOf(" ");
                                        var value=val.substring(0,pos1);
                                        var type=val.substring(pos1+1);
                                
                                        if(type=="(Part)")
                                        {
                                            document.getElementById("attachedlist").options[i].selected=true;
                                        }
                                        else if(type=="(Assembly)")
                                        {
                                            document.getElementById("attachedlist").options[i].selected=true;
                                        }
                                        else
                                        {
                                            for(j=0;j<len1;j++)
                                            {

                                                //alert("value :"+value+"************unselected_assArr :"+unselected_assArr[j]);
                                                if(value==unselected_Arr[j])
                                                {
                                                    document.getElementById("attachedlist").options[i].selected=true;
                                                    flag=true;
                                                }

                                            }
                                            if(flag==false)
                                            {
                                                alert("Tool '"+value+"' can not be added as it creates Tool BOM error.");
                                                return false;
                                            }
                                        }
                                
                                    }

                                    return true;
                                }
                                                        
                            }
            
                            function validate_create()
                            {
                                        
                                var length=document.getElementById("attachedlist").options.length;
                
                                if(length==0)
                                {
                                    alert("Please attach atleast one Part.");
                                    return false;
                                }
                                if(length>250)
                                {
                                    var res=confirm("You want to attach '"+length+"' Number of Components to Tool '"+toolno+"'. Are you sure?");
                                    if(res==false)
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
                                var x=document.getElementById("attachedlist")

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
                                var x=document.getElementById("attachedlist")

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
                                                    
                            function CancelProcess_create()
                            {
                                var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                                if(cncl==true)
                                {
                                    location.href="<%=contextPath%>/authJSP/EAMG_Tool/EAMG_create_tool.jsp";
                                }

                            }
                            function CancelProcess_modify()
                            {
                                var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                                if(cncl==true)
                                {
                                    location.href="<%=contextPath%>/authJSP/EAMG_Tool/EAMG_tool_modify_comptype.jsp";
                                }

                            }
                            //  End -->
        </script>
        <!--<style type="text/css">
        <!--
        body {
            margin-left: 0px;
            margin-top: 0px;
            margin-right: 0px;
            margin-bottom: 0px;
            background-image: url(<%=imagesURL%>/back.gif);
        }
    </style>-->
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
    </head>
    <%
                if (allcompVec.size() > 0) {%>
    <body onload="showComponents('TOOL');">
        <%} else {%>
    <body>
        <%}%>
        <%
                    if (option.equals("create")) {
                        heading = "CREATE TOOL BOM";
                        tdData = "MANAGE TOOL >> ADD NEW TOOL >> CREATE TOOL BOM";
                    } else {
                        heading = "MODIFY TOOL BOM";
                        tdData = "MANAGE TOOL >> MODIFY TOOL >> MODIFY TOOL BOM";
                    }

                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">

            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%--<tr>
                    <td>&nbsp;</td>
                </tr>
                <%if (option.equals("create")) {%>

                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="20" align="left" class="blue"><b class="path">&nbsp;Manage Tool >> Add New Tool >> Create Tool BOM</b></td>
                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td align="right" class="small">&nbsp;</td>
                </tr>


                <tr>
                    <td valign="top">
                        <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="20" align="left" class="blue"><b class="heading">&nbsp;CREATE TOOL BOM</b></td>
                            </tr>
                            <%} else {%>

                            <tr>
                                <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                        <tr>
                                            <td height="20" align="left" class="blue"><b class="path">&nbsp;Manage Tool >> Modify Tool >> Modify Tool Bom</b></td>
                                        </tr>
                                    </table></td>
                            </tr>
                            <tr>
                                <td align="right" class="small">&nbsp;</td>
                            </tr>


                            <tr>
                                <td valign="top">
                                    <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                        <tr>
                                            <td height="20" align="left" class="blue"><b class="heading">&nbsp;MODIFY TOOL BOM</b></td>

                                        </tr>


                                        <%}%>--%>
                <form name="form1" action="<%=contextPath%>/authJSP/EAMG_Tool/EAMG_add_tool_quantity.jsp" method="post" id="form1">
                    <tr>
                        <td align="center" valign="top" bgcolor="#FFFFFF">
                            <br/>
                            <table width="95%" border="0" cellspacing="1" cellpadding="1">
                                <tr>
                                    <td colspan="3">
                                        <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                            <tr>
                                                <td height="20" align="left" class="blue"><b>&nbsp;<span class="heading">Tool Number: <%=toolno%></span></b></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <%
                                            if (allcompVec.size() > 0) {%>
                                <tr>
                                    <td>
                                        <table width="95%" border="0" cellspacing="1" cellpadding="1">
                                            <tr>
                                                <td width="20%" class="text">&nbsp;Select Component</td>
                                                <td width="80%" colspan="2" align="left">
                                                    <select name="comp_select" id="comp_select" onchange="return showComponents(this.value);" style="width:120px " class="text">
                                                        <option value="TOOL" selected>Tool</option>
                                                        <option value="PRT">Part</option>
                                                        <option value="All">All</option>
                                                    </select>
                                                    <img  id='img' style='visibility:hidden;' border='0' src='<%=imagesURL%>/load.gif'/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="20%" class="text">&nbsp;Search Component</td>
                                                <td width="80%" colspan="2" align="left" class="text"><label>
                                                        <input type="text" name="searchcomp" id="searchcomp"/>
                                                    </label><img style="vertical-align:bottom" id='searchimg' border='0' title="Search" src='<%=imagesURL%>/go.gif' onclick="searchelement();"/>
                                                </td>
                                            </tr>

                                            <tr>
                                                <td colspan="3" align="right">
                                                    <table width="100%" border="0" cellpadding="1" cellspacing="1">
                                                        <tr>
                                                            <td width="43%" align="center" bgcolor="#000000" ><table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                                                    <tr>
                                                                        <td width="43%" height="20" align="center" class="blue"><strong class="heading">Available Components</strong></td>
                                                                    </tr>
                                                                </table></td>
                                                            <td width="7%" bgcolor="#FFFFFF">&nbsp;</td>
                                                            <td width="43%" align="center" bgcolor="#000000"><table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                                                    <tr>
                                                                        <td width="43%" height="20" align="center" class="blue"><strong class="heading">Selected  Components</strong></td>
                                                                    </tr>
                                                                </table></td>
                                                            <td width="7%" bgcolor="#FFFFFF">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td align="right" bgcolor="#FFFFFF" class="text"># <span  id="totalcomplength"></span></td>
                                                            <td bgcolor="#FFFFFF">&nbsp;</td>
                                                            <td align="right" bgcolor="#FFFFFF" class="text"># <span id="selectedcomplength"></span></td>
                                                            <td bgcolor="#FFFFFF">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td rowspan="6" bgcolor="#FFFFFF"><select name="complist" id="complist" size="8" multiple="multiple" style="width:300px ">

                                                                </select></td>
                                                            <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                            <td rowspan="6" bgcolor="#FFFFFF"><select name="attachedlist" id="attachedlist" size="8" multiple="multiple" style="width:300px ">
                                                                    <%for (int i = 0; i < allselectedcompVec.size(); i++) {
                                                                                                                        String val = "" + allselectedcompVec.elementAt(i);
                                                                                                                        int cnt = selectedparts.size();
                                                                                                                        if (i < selectedparts.size()) {%>
                                                                    <option value='<%=val%> (Part)'><%=val%></option>
                                                                    <%} else if (i < (cnt + selectedassembly.size())) {%>
                                                                    <option value='<%=val%> (Assembly)'><%=val%></option>
                                                                    <%} else {%>
                                                                    <option value='<%=val%> (Tool)'><%=val%></option>
                                                                    <%}%>
                                                                    <%}%>

                                                                </select></td>
                                                            <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center" bgcolor="#FFFFFF"><label>
                                                                    <img src="<%=imagesURL%>/next.gif" name="selectone" id="button" style="width:24px"  onclick="moveDualList( document.form1.complist,  document.form1.attachedlist, false );"/>
                                                                </label></td>
                                                            <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td align="center" bgcolor="#FFFFFF"><label>
                                                                    <img src="<%=imagesURL%>/next1.gif" name="selectall" id="button2" style="width:24px"  onclick="moveDualList( document.form1.complist,  document.form1.attachedlist, true  );"/>
                                                                </label></td>

                                                        </tr>
                                                        <tr>
                                                            <td align="center" bgcolor="#FFFFFF"><label>
                                                                    <img src="<%=imagesURL%>/previous.gif" name="removeone" id="button3" style="width:24px" onclick="javascript:if(attachedlist.selectedIndex!=-1){attachedlist.options[attachedlist.selectedIndex]=null};setlengths();"/>
                                                                </label></td>

                                                        </tr>
                                                        <tr>
                                                            <td align="center" bgcolor="#FFFFFF"><label>
                                                                    <img src="<%=imagesURL%>/previous1.gif" name="removeall" id="button4" style="width:24px"  onclick="javascript:attachedlist.options.length=0;setlengths();"/>
                                                                </label></td>
                                                            <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td>&nbsp;</td>
                                                        </tr>

                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <table width="100%" border="0" cellspacing="2" cellpadding="2">
                                <%if (option.equals("create")) {%>
                                <tr>
                                    <td colspan="2">
                                        <table width="100%">
                                            <tr>

                                                <td width="50%" align="right">
                                                    <input type="submit" name="Next" id="Next" value="Next" style="width:70px;" onClick="return validate_create();"/>
                                                </td>
                                                <td width="50%" align="left">
                                                    <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess_create();" />
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <%} else {%>
                                <tr>
                                    <td colspan="2">
                                        <table width="100%">
                                            <tr>

                                                <td align="right">
                                                    <input type="submit" name="Next" id="Next" value="Next" style="width:70px;" onClick="return validate_modify();"/>
                                                </td>
                                                <td align="left">
                                                    <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess_modify();" />
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>

                                <%}%>
                            </table>
                        </td>
                    </tr>
                    <%} else {%>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td>
                            <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                <tr>
                                    <td height="30" align="center" class="blue"><b>&nbsp;<span class="heading">No Component exists in Database.</span></b></td>
                                </tr>
                            </table>
                        </td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                    <%}%>
                </form>
            </table>

            <%
                        out.println(object_pageTemplate.tableFooter());
            %>
        </div>
    </body>
</html>

