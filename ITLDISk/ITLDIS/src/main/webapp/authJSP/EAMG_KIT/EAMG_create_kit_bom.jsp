<%--
    Document   : amw_create_kit_bom
    Created on : Nov 15, 2011, 11:14:28 PM
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
						 String context = request.getContextPath();
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
						 String contextPath = request.getContextPath();
						 if (!session_id.equals(getSession)) {
							  object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
							  return;
						 }

						 String heading = null;
						 int width = 740;
						 int height = 0;

						 String kitno = (String) session.getAttribute("kitno");
						 String option = (String) session.getAttribute("option");
						 //System.out.println(kitno);
						 String comp = "";
						 Vector tempVec = new Vector();
						 Vector allcompVec = new Vector();
						 Vector allselectedcompVec = new Vector();
						 Vector selectedparts = new Vector();
						 Vector totalparts = new Vector();
						 Map<String, String> partMap = new LinkedHashMap();
						 Vector totalkit = new Vector();
						 ResultSet rs = null;
						 Statement stmt = null;
						 String tdData = "";
						 try {
							  conn = holder.getConnection();



							  String query1 = "Select distinct part_no,part_type from cat_part order by part_no";
							  stmt = conn.createStatement();
							  rs = stmt.executeQuery(query1);

							  while (rs.next()) {
									partMap.put(rs.getString(1), rs.getString(2));
							  }
							  rs.close();

							  session.setAttribute("partMap", partMap);


							  query1 = "Select distinct part_no from cat_part where part_type='KIT'";
							  stmt = conn.createStatement();
							  rs = stmt.executeQuery(query1);

							  while (rs.next()) {
									totalparts.add(rs.getString(1));


							  }
							  rs.close();

							  query1 = "Select distinct COMPONENT from CAT_S_KIT_BOM where KIT_NO='" + kitno + "'";
							  stmt = conn.createStatement();
							  rs = stmt.executeQuery(query1);

							  while (rs.next()) {
									selectedparts.add(rs.getString(1));
							  }
							  rs.close();


							  stmt.close();
						 } catch (Exception e) {
							  e.printStackTrace();
						 }

						 if (option.equals("modify")) {
							  totalkit.remove(kitno);
						 }
						 totalparts.removeAll(selectedparts);
						 allcompVec.addAll(totalparts);

						 allselectedcompVec.addAll(selectedparts);

						 //System.out.println("allcompVec :" + allcompVec);
						 if (option.equals("modify")) {

							  for (int i = 0; i < tempVec.size(); i++) {%>
									unselected_Arr[<%=i%>]='<%=tempVec.elementAt(i)%>';
            <%}
						 }%>
							  var kitno='<%=kitno%>';

							  function showComponents(comp_type)
							  {
									document.getElementById("img").style.visibility="visible";
									document.getElementById("searchcomp").value="";
									// alert(comp_type);
									if(comp_type!='')
									{

										 var strURL ="<%=context%>/authJSP/EAMG_KIT/EAMG_get_components_with_type.jsp?comp_type="+comp_type;
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
										 
										 var tempCompArr=res.split('$#$');
										 //alert(tempCompArr.length)
										 if(tempCompArr.length==1)
										 {
											  document.getElementById("img").style.visibility="hidden";
											  alert('No Components available.');
											  return false;
										 }

										 document.getElementById("compList").outerHTML=tempCompArr[0];

            <%-- var complist=document.getElementById("complist");
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

                                                                                 }--%>
                                                                                             document.getElementById("img").style.visibility="hidden";
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

                                                                                         // newattachedlist.sort( userareOptionValues );   // BY VALUES

                                                                                         // newattachedlist.sort( userareOptionText );   // BY TEXT
                                                                                         // Populate the destination with the items from the new array

                                                                                         for ( var j = 0; j < newattachedlist.length; j++ )

                                                                                         {

                                                                                             if ( newattachedlist[ j ] != null )

                                                                                             {
                                                                                                 attachedlist.options[ j ] = newattachedlist[ j ];
                                                                                                 attachedlist.options[ j ].selected=true;
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

                                                                                     function validate_modify()
                                                                                     {

                                                                                         var length=document.getElementById("attachedlist").options.length;
                                                                                         var len1=unselected_Arr.length;
                                                                                         if(length==0)
                                                                                         {
                                                                                             alert("Please attach atleast one Part, Kit.");
                                                                                             return false;
                                                                                         }
																													  else
																													  {
																															for(i=0;i<length;i++)
																															{

																																 document.getElementById("attachedlist").options[i].selected=true;
																															}


																													  }
                                                                                         if(length>250)
                                                                                         {
                                                                                             var res=confirm("You want to attach '"+length+"' Number of Components to Kit '"+kitno+"'. Are you sure?");
                                                                                             if(res==false)
                                                                                             {
                                                                                                 return false;
                                                                                             }
                                                                                         }
            <%--else
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
                                                                                                                 alert("Kit '"+value+"' can not be added as it creates Kit BOM error.");
                                                                                                                 return false;
                                                                                                        }
                                                                                          }

                                                                                 }

                                                                                 return true;
                                                                        }--%>

                                                                            }

                                                                            function validate_create()
                                                                            {

                                                                                var length=document.getElementById("attachedlist").options.length;

                                                                                if(length==0)
                                                                                {
                                                                                    alert("Please attach atleast one Part.");
                                                                                    return false;
                                                                                }
																										  else
																										  {
																												for(i=0;i<length;i++)
                                                                                    {

                                                                                        document.getElementById("attachedlist").options[i].selected=true;
                                                                                    }
																										  }
                                                                                if(length>250)
                                                                                {
                                                                                    var res=confirm("You want to attach '"+length+"' Number of Components to Kit '"+kitno+"'. Are you sure?");
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
                                                                                //var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                                                                                //if(cncl==true)
                                                                                {
                                                                                    location.href="<%=context%>/authJSP/EAMG_KIT/EAMG_create_kit.jsp";
                                                                                }

                                                                            }
                                                                            function CancelProcess_modify()
                                                                            {
                                                                                //var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                                                                                //if(cncl==true)
                                                                                {
                                                                                    location.href="<%=context%>/authJSP/EAMG_KIT/EAMG_modify_Kit_by_options.jsp";
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
    <body >
        <%} else {%>
    <body>
        <%}%>
        <%
					if (option.equals("create")) {
						 heading = "CREATE KIT BOM  FOR KIT \"" + kitno + "\"";
						 tdData = "MANAGE KIT >> ADD NEW KIT >> CREATE KIT BOM";
					} else {
						 heading = "MODIFY KIT BOM FOR KIT \"" + kitno + "\"";
						 tdData = "MANAGE KIT >> MODIFY KIT >> MODIFY KIT BOM";
					}

					object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
					out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>


        <form name="form1" action="<%=context%>/authJSP/EAMG_KIT/EAMG_add_kit_quantity.jsp" method="post" id="form1">
            <div align="center">

                <table width="100%" border="0" cellspacing="1" cellpadding="1">


                    <tr>
                        <td align="center" colspan="2" valign="top" bgcolor="#FFFFFF">

                            <table width="90%" border="0" cellspacing="2" cellpadding="2">

                                <%
													if (allcompVec.size() > 0) {%>
                                <tr>
                                    <td>
                                        <table width="95%" border="0" cellspacing="1" cellpadding="1">
                                            <tr>
                                                <td width="20%" class="text">&nbsp;Select Component</td>
                                                <td width="80%" colspan="2" align="left">
                                                    <select name="comp_select" id="comp_select" onchange="return showComponents(this.value);" style="width:120px " class="text">
                                                        <option value="KIT" selected>KIT</option>
                                                        <option value="PRT" >PART</option>
                                                        <option value="LUBES" >LUBES</option>
                                                        <option value="TOOL" >TOOL</option>
                                                        <option value="All">All</option>
                                                    </select>
                                                    <img  id='img' style='visibility:hidden;' border='0' src='<%=imagesURL%>/load.gif'/>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="20%" class="text">&nbsp;Search Component</td>
                                                <td width="80%" colspan="2" align="left"><label>
                                                        <input type="text" name="searchcomp" id="searchcomp"/>
                                                    </label><img style="vertical-align:bottom" id='searchimg' border='0' title="Search" src='<%=imagesURL%>/go.gif' onclick="searchelement();"/>
                                                </td>
                                            </tr>

                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td></tr>
                    <tr>
                        <td  align="left" width="42px">&nbsp;</td>
                        <td  align="right">
                            <table width="100%" border="0" cellpadding="1" cellspacing="1">
                                <tr>
                                    <td width="43%" align="center"  >
                                        <table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC" bgcolor="#CCCCCC">

                                            <tr>
                                                <td width="43%" height="25" align="center" class="blue"><strong class="heading">Available Components</strong></td>
                                            </tr>

                                        </table>
                                    </td>
                                    <td width="7%" >&nbsp;</td>
                                    <td width="43%" align="center" >
                                        <table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC" bgcolor="#CCCCCC">

                                            <tr>
                                                <td width="43%" height="25" align="center" class="blue"><strong class="heading">Selected Components</strong></td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td width="7%" >&nbsp;</td>
                                </tr>
                                <tr>
                                    <td align="right"  class="links_txt"># <span  id="totalcomplength"><%=totalparts.size()%></span></td>
                                    <td >&nbsp;</td>
                                    <td align="right"  class="links_txt"># <span id="selectedcomplength"><%=allselectedcompVec.size()%></span></td>
                                    <td >&nbsp;</td>
                                </tr>
                                <tr>
                                    <td rowspan="6" ><select class="links_txt" name="compList" id="compList" size="12" multiple="multiple" style="width:300px" ondblclick="moveDualList(document.form1.compList,  document.form1.attachedlist, false);">
                                            <%for (int i = 0; i < totalparts.size(); i++) {%>

                                            <option value='<%=totalparts.get(i)%>'><%=totalparts.get(i)%></option>
                                            <%}%>

                                        </select>
                                    </td>
                                    <td align="center" >&nbsp;</td>
                                    <td rowspan="6" ><select class="links_txt" name="attachedlist" id="attachedlist" size="12" multiple="multiple" style="width:300px"  ondblclick="moveDualList(document.form1.attachedlist, document.form1.compList, false);">
                                            <%for (int i = 0; i < allselectedcompVec.size(); i++) {%>%>

                                            <option value='<%=allselectedcompVec.get(i)%>'><%=allselectedcompVec.get(i)%></option>
                                            <%}%>

                                        </select></td>
                                    <td align="center" >&nbsp;</td>
                                </tr>
                                <tr>
                                    <td align="center" ><label>
                                            <img src="<%=imagesURL%>next.gif" name="selectone" id="button" style="width:24px"  onclick="moveDualList( document.form1.compList,  document.form1.attachedlist, false );"/>
                                        </label>
                                    </td>
                                    <td align="center" >&nbsp;</td>
                                    <td align="center" >&nbsp;</td>
                                </tr>
                                <tr>
                                    <td align="center" ><label>
                                            <img src="<%=imagesURL%>next1.gif" name="selectall" id="button2" style="width:24px"  onclick="moveDualList( document.form1.compList,  document.form1.attachedlist, true  );"/>
                                        </label>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td align="center" >&nbsp;</td>
                                    <td align="center" >&nbsp;</td>
                                </tr>
                                <tr>
                                    <td align="center" ><label>
                                            <img src="<%=imagesURL%>previous.gif" name="removeone" id="button3" style="width:24px"  onclick="moveDualList(document.form1.attachedlist, document.form1.compList,  false );"/>
                                        </label>
                                    </td>
                                    <td>&nbsp;</td>
                                    <td align="center" >&nbsp;</td>
                                    <td align="center" >&nbsp;</td>

                                </tr>
                                <tr>
                                    <td align="center" ><label>
                                            <img src="<%=imagesURL%>previous1.gif" name="removeall" id="button4" style="width:24px" onclick="moveDualList(document.form1.attachedlist, document.form1.compList,  true  );"/>
                                        </label>
                                    </td>
                                    <td align="center" >&nbsp;</td>
                                    <td align="center" >&nbsp;</td>
                                    <td align="center" >&nbsp;</td>
                                </tr>
                                <tr>
                                    <td align="center" >&nbsp;</td>
                                    <td align="center" >&nbsp;</td>
                                    <td align="center" >&nbsp;</td>
                                    <td align="center" >&nbsp;</td>
                                </tr>
                                <tr>
                                    <td >&nbsp;</td>
                                    <td >&nbsp;</td>
                                    <td >&nbsp;</td>
                                    <td >&nbsp;</td>
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
                            </table>
                        </td></tr></table>



            </div></form>

        <%
					out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>

