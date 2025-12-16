<%-- 
    Document   : EAMG_attach_alternate
    Author     : Avinash.pandey
--%>

<%@ page import="viewEcat.comEcat.*,java.sql.*, java.util.*" %>
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
            Connection conn = holder.getConnection();
            Vector grpVec = new Vector();
            try {
                ResultSet rs;
                Statement stmt = conn.createStatement();
                rs = stmt.executeQuery("Select DISTINCT GRP_KIT_NO from CAT_GROUP_KIT_DETAIL");
                if (rs.next()) {
                    do {
                        grpVec.addElement(rs.getString(1));
                    } while (rs.next());
                }
                rs.close();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                // conn.close();
            }
//System.out.println("grpVec:"+grpVec);

%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
         <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=imagesURL%>js/autosuggest1.js"></script>
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <script language="javascript"> 
            var custom_array =new Array();
            <%
                        for (int i = 0; i < grpVec.size(); i++) {
            %>
                custom_array[<%=i%>]='<%="" + grpVec.elementAt(i)%>';
            <%}%>
                var compno='';
                
                function showComponents(comp_type)
                {
                            
                            
                    var grpno=document.getElementById("grp").value;
                    if(grpno=='')
                    {
                        alert('Please Enter Group Number.');
                        return;
                    }
                    if(comp_type=='')
                    {
                        var complist=document.getElementById("complist");
                        complist.options.length=0;
                        document.getElementById("attachedlist").options.length=0;
                        setlengths();
                        return;
                    }
                    //document.getElementById("img").style.visibility="visible";
                    document.getElementById("searchcomp").value="";
                    // alert(comp_type);
                    
                    if(comp_type!='')
                    {
                        var strURL ="<%=context%>/authJSP/EAMG_Group/EAMG_get_first_level_comps.jsp?grpno="+grpno+"&comp_type="+comp_type+"&selecttype=comptype";
                        // alert(strURL)
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
                        var complist=document.getElementById("compno_select");
                        complist.options.length=0;
                        var t=0;
                        complist.options[t]=new Option();
                        complist.options[t].value="";
                        complist.options[t].text="--Select--";
                        if(tempCompArr.length==1)
                        {
                            comptype='Part';
                            //document.getElementById("img").style.visibility="hidden";
                            alert("No Component exist as "+comptype+" in Group "+document.getElementById("grp").value+".");
                            //document.getElementById("comp_select").selectedIndex=0;
                            document.getElementById("compno_select").selectedIndex=0;
                            document.getElementById("complist").options.length=0;
                            document.getElementById("attachedlist").options.length=0;
                            setlengths();
                            return false;
                        }

                    
                        t++;
                        for(var i=0;i<tempCompArr.length-1;i++)
                        {
                            complist.options[t]=new Option(tempCompArr[i]);
                            complist.options[t].value=tempCompArr[i];
                            complist.options[t].text=tempCompArr[i];
                            t++;
                              
                        }
                        //document.getElementById("img").style.visibility="hidden";
                    
                    }
                }
                function showComponents1(comp)
                {
                    if(comp=='')
                    {
                        var complist=document.getElementById("complist");
                        complist.options.length=0;
                        document.getElementById("attachedlist").options.length=0;
                        setlengths();
                        return;
                    }
                    document.getElementById("attachedlist").options.length=0;
                    var grpno=document.getElementById("grp").value;
                    var comp_type='PRT';
                    compno=comp;
                    document.getElementById("img1").style.visibility="visible";
                    document.getElementById("searchcomp").value="";
                    // alert(comp_type);
                    if(comp_type!='')
                    {
                   
                        var strURL ="<%=context%>/authJSP/EAMG_Group/EAMG_get_components_for_alternate.jsp?grpno="+grpno+"&comp_type="+comp_type+"&compno="+comp;
                        //alert(strURL)
                        xmlHttp = GetXmlHttpObject();
                        xmlHttp.onreadystatechange = function()
                        {
                            stateChanged1();
                        };
                        xmlHttp.open("POST", strURL, true);
                        xmlHttp.send(null);
                    }
                
                }
                function stateChanged1()
                {
                    if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                    {
                        res = xmlHttp.responseText;
                        //window.prompt("jk",res);
                        var compArr = new Array();
                        compArr=res.split('&');
                        var alternateCompArr = new Array();
                        alternateCompArr=compArr[0].split('|');
                        var selectedCompArr = new Array();
                        selectedCompArr=compArr[1].split('|');
                        //alert(tempCompArr.length)
                    
                        if(compArr.length==1)
                        {
                            //document.getElementById("img").style.visibility="hidden";
                            setlengths();
                            alert('No Components available in database.');
                            return false;
                        }
                        var complist=document.getElementById("complist");
                        complist.options.length=0;
                        var t=0;
                        var attachedcomplist=document.getElementById("attachedlist");
                        for(var i=0;i<alternateCompArr.length-1;i++)
                        {
                            var comp=alternateCompArr[i].split(' ');
                            attachedcomplist.options[t]=new Option(alternateCompArr[i]);
                            attachedcomplist.options[t].value=alternateCompArr[i];
                            attachedcomplist.options[t].text=comp[0];
                            t++;
                       
                        }
                        t=0;
                        for(var i=0;i<selectedCompArr.length-1;i++)
                        {
                            var comp=selectedCompArr[i].split(' ');
                            if(compno!=comp[0])
                            {
                                complist.options[t]=new Option(selectedCompArr[i]);
                                complist.options[t].value=selectedCompArr[i];
                                complist.options[t].text=comp[0];
                                t++;
                            }
                        }
                      
                    
                        document.getElementById("img1").style.visibility="hidden";
                        setlengths();
                    }
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

                            unattachedlist.options[i].value = "";

                            unattachedlist.options[i].text  = "";

                            unattachedlist.options[i]       = null;

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
                function validate_group()
                {
                    var group_no=document.getElementById("grp").value;
                    group_no=TrimAll(group_no);
                    if(group_no=='')
                    {
                        alert('Please Select Group Number.');
                        document.getElementById("grp").focus();
                        return false;
                    }
                    else
                    {
                        var flag=false;
                        for(var j=0;j<custom_array.length;j++)
                        {
                            if(custom_array[j]==group_no)
                            {
                                flag=true;
                                break;
                            }
                        }
                        if(flag==false)
                        {
                            alert("Group Number '"+group_no+"' does not exist in database.");
                            document.getElementById("grp").value='';
                            document.getElementById("grp").focus();
                            //document.getElementById("comp_select").selectedIndex=0;
                            document.getElementById("compno_select").options[0]=new Option();
                            document.getElementById("compno_select").options[0].value="";
                            document.getElementById("compno_select").options[0].text="--Select--";
                            document.getElementById("compno_select").selectedIndex=0;
                            document.getElementById("complist").options.length=0;
                            document.getElementById("attachedlist").options.length=0;
                            return false;
                        }
                    }
                    //                            if(document.getElementById("comp_select").selectedIndex==0)
                    //                            {
                    //                                alert("Please Select Component Type for Group "+group_no+".");
                    //                                document.getElementById("comp_select").focus();
                    //                                return false;
                    //                            }
                    if(document.getElementById("compno_select").selectedIndex==0)
                    {
                        alert("Please Select Component Number for Group "+group_no+".");
                        document.getElementById("compno_select").focus();
                        return false;
                    }
                    var length=document.getElementById("attachedlist").options.length;
                    if(length==0)
                    {
                        alert("Please Select atleast one alternate for Component "+document.getElementById("compno_select").value+".");
                        return false;
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
    <%
                String tdData = "MANAGE  TABLE >> ATTACH ALTERNATES TO " + PageTemplate.GROUP.toUpperCase();
                object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
    %>
    <%
                heading = "ATTACH ALTERNATES TO GROUP";
                out.println(object_pageTemplate.tableHeader(heading, width, height));
    %>
    <body onload="setlengths();">
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <%--<tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Group >> Attach Alternates to Group </b></td>
                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td align="right" class="small">&nbsp;</td>
                </tr>--%>
               
               <%-- <tr>
                    <td valign="top">
                        <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;ATTACH ALTERNATES TO GROUP</b></td>
                            </tr>--%>
                            <tr>
                                <td align="center" valign="top" bgcolor="#FFFFFF">
                                    <form name="form1" action="<%=context%>/EAMG_CreateAlternates.do" method="post"  id="form1" >
                                        <br />
                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">


                                            <tr>
                                                <td>
                                                    <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                                        <tr>
                                                            <td width="40%" class="text">&nbsp;Select Group Number :</td>
                                                            <td width="60%" colspan="2" align="left"><label>
                                                                    <input type="text" style='width:200px;height:20px;' name="group_number" id="grp" value=""/>
                                                                    <input type="button"  name="showCompBtn" id="showCompBtn" value="Show Component" onclick="showComponents('PRT');"/>
                                                                </label></td>
                                                        </tr>

                                                        <tr>
                                                            <td width="40%" class="text">&nbsp;Select Component of Group :</td>
                                                            <td width="60%" colspan="2" align="left">
                                                              <select name="compno_select" id="compno_select" onchange="return showComponents1(this.value);">
                                                                    <option value="">--Select--</option>
                                                                </select>
                                                                <img  id='img1' style='visibility:hidden;' border='0' src='<%=imagesURL%>/load.gif'/>
                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td width="40%" class="text">&nbsp;Search Component</td>
                                                            <td width="60%" colspan="2" align="left"><label>
                                                                    <input type="text" name="searchcomp" id="searchcomp"/>
                                                                </label><img style="vertical-align:bottom" id='searchimg' border='0' title="Search" src='<%=imagesURL%>/go.gif' onclick="searchelement();"/></td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="3">&nbsp;</td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="3" align="right"><table width="100%" border="0" cellpadding="1" cellspacing="1">
                                                                    <tr>
                                                                        <td width="43%" align="center" bgcolor="#000000" ><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                                                                                <tr>
                                                                                    <td width="43%" height="25" align="center" class="blue"><strong class="heading">Available Components</strong></td>
                                                                                </tr>
                                                                            </table></td>
                                                                        <td width="7%" bgcolor="#FFFFFF">&nbsp;</td>
                                                                        <td width="43%" align="center" bgcolor="#000000"><table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                                                                                <tr>
                                                                                    <td width="43%" height="25" align="center" class="blue"><strong class="heading">Selected Alternates</strong></td>
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
                                                                        <td rowspan="6" bgcolor="#FFFFFF"><select name="complist" id="complist" size="12" multiple="multiple" style="width:300px">

                                                                            </select></td>
                                                                        <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                                        <td rowspan="6" bgcolor="#FFFFFF"><select name="attachedlist" id="attachedlist" size="12" multiple="multiple" style="width:300px">
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
                                                                        <td>&nbsp;</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="center" bgcolor="#FFFFFF"><label>
                                                                                <img src="<%=imagesURL%>/previous.gif" name="removeone" id="button3" style="width:24px"  onclick="moveDualList(document.form1.attachedlist, document.form1.complist,  false );"/>
                                                                            </label></td>

                                                                    </tr>
                                                                    <tr>
                                                                        <td align="center" bgcolor="#FFFFFF"><label>
                                                                                <img src="<%=imagesURL%>/previous1.gif" name="removeall" id="button4" style="width:24px" onclick="moveDualList(document.form1.attachedlist, document.form1.complist,  true  );"/>
                                                                            </label></td>
                                                                        <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                                        <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                                                                    </tr>
                                                                    <tr>
                                                                        <td bgcolor="#FFFFFF">&nbsp;</td>
                                                                        <td bgcolor="#FFFFFF">&nbsp;</td>
                                                                        <td bgcolor="#FFFFFF">&nbsp;</td>
                                                                        <td bgcolor="#FFFFFF">&nbsp;</td>
                                                                    </tr>
                                                                </table>

                                                            </td>
                                                        </tr>
                                                        <tr>
                                                            <td colspan="3">
                                                                <table width="100%">
                                                                    <tr>

                                                                        <td width="50%" align="right">
                                                                            <input type="submit" name="Next" id="Next" value="Submit" style="width:70px;" onClick="return validate_group();"/>
                                                                        </td>
                                                                        <td width="50%" align="left">
                                                                            <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />
                                                                        </td>
                                                                    </tr>
                                                                </table>
                                                            </td>
                                                        </tr>
                                                    </table>
                                                </td>
                                            </tr>
                                        </table>
                                    </form>
                                </td>
                            </tr>
                       <%-- </table>
                    </td>
                </tr>--%>
            </table>
        </div>
        <script> new actb1('grp', custom_array,''); </script>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>

