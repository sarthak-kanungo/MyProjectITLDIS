
<%@page import="authEcat.UtilityMapkeys1,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,java.util.ArrayList"  contentType="text/html" pageEncoding="UTF-8"%>

<%--
    Document   : AssignProduct_User
    Created on : Feb 23, 2012, 11:33:50 AM
    Author     : manish.kishore
--%>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");



            PageTemplate object_pageTemplate = new PageTemplate();
            String cnt = request.getContextPath();
            String imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String contextPath = request.getContextPath();
            String getSession = (String) session.getValue("session_value");



            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }

            ArrayList<String> compList = (ArrayList) request.getAttribute("compList");
            ArrayList<String> modelList = (ArrayList) request.getAttribute("modelList");
            String heading = null;
            int width = 740;
            int height = 0;


%>

<html>
    <title><%=UtilityMapkeys1.tile%></title>
    <head>

        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" language="javascript">


            var len = 0;


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


            function showValues()
            {
                var strURL=null;
                var inputValue=null;
                var engineModel=document.form1.model_no;
                // if(engineModel.value!='Select')
                {
                    inputValue=engineModel.value;

                    strURL="<%=cnt%>/Assign_ComponentToModelAction.do?option=ajax&model_no="+inputValue+"&tm="+new Date().getTime();
                    var xmlHttp = GetXmlHttpObject();
                    xmlHttp.onreadystatechange = function()
                    {
                        stateChangedModel(xmlHttp);

                    };
                    xmlHttp.open("GET", strURL, true);
                    xmlHttp.send(null);
                    //  return false;
                }}


            function stateChangedModel(xmlHttp)
            {
                var response=null;
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    response=xmlHttp.responseText;
                    var splitArr=response.split('$#$');
                    //        document.getElementById('unAssignedUsers').innerHTML='';
                    //        document.getElementById('assignedUsers').innerHTML='';
                    document.getElementById('compList').outerHTML=splitArr[0];//attachedlist
                    document.getElementById('attachedlist').outerHTML=splitArr[1];
                    setlengths();
                }
            }

            function userareOptionValues(a, b)

            {

                // Radix 10: for numeric values

                // Radix 36: for alphanumeric values

                var sA = parseInt( a.value, 36 );

                var sB = parseInt( b.value, 36 );

                return sA - sB;

            }
            // Compare two options within a list by TEXT

            function userareOptionText(a, b)

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
                document.getElementById("totalcomplength").innerHTML=document.getElementById("compList").options.length;
                document.getElementById("selectedcomplength").innerHTML=document.getElementById("attachedlist").options.length;
            }

            function validateForm()
            {
                var productObj=document.getElementById("Product");

            <%-- var countOption=0;
                       for(var p=0;p<productObj.options.length;p++)
                       {
                               if(productObj.options[p].selected==true)
                                        countOption++;
                       }--%>
                               if(productObj.value=="Select")
                               {
                                   alert("Please Select <%=PageTemplate.ENGINE_MODEL%>");
                                   return false;
                               }

                               var length=document.getElementById("attachedlist").options.length;
                               if(length==0)
                               {
                                   alert("Please Select atleast one Component.");
                                   return false;
                               }
                               else
                               {
                                   var attchedList=document.getElementById("attachedlist");

                                   for(var p=0;p<attchedList.options.length;p++)
                                   {
                                       attchedList.options[p].selected=true;

                                   }
                               }

                           }


                           function CancelProcess()
                           {
                               // var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                               // if(cncl==true)
                               {
                                   parent.content.location="#";
                               }

                           }

        </script>

    </head>
    <body onload="setlengths();">
        <form name="form1" action="<%=cnt%>/Assign_ComponentToModelAction.do"  method="post"  id="form1" >

            <%
                        heading = "Assign Component To Model";
                        out.println(object_pageTemplate.tableHeader(heading, width, height));
            %>

            <table width=90% border=0 height=340 align="center" valign="top" cellspacing=1 cellpadding=4 bordercolor = #CCCCCC>
                <tbody>
                    <tr valign=top>
                        <td  class="links_txt">&nbsp;</td>
                        <td  class="links_txt">&nbsp;</td>
                    </tr>
                    <tr valign=top>


                        <td  class="links_txt">&nbsp;Select <%=PageTemplate.ENGINE_MODEL%> :</td>
                        <td class="links_txt"><select class="links_txt"  name="model_no" id="Product"  style="width:300px;" onchange="showValues();">
                                <%--<option selected value="">Select <%=PageTemplate.ENGINE_SERIES%></option>--%>
                                <option value="Select">Select Here</option>
                                <%for (int i = 0; i < modelList.size(); i++) {%>
                                <option value="<%=modelList.get(i)%>"><%=modelList.get(i)%></option>
                                <%}%>

                            </select><input type="hidden" name="option" value="insertValues"></td>
                    </tr>

                    <tr>
                        <td colspan="2" align="right">
                            <table width="100%" border="0" cellpadding="1" cellspacing="1">
                                <tr>
                                    <td width="43%" align="center"  >
                                        <table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">

                                            <tr>
                                                <td width="43%" height="25" align="center" class="blue"><strong class="heading">Available Components</strong></td>
                                            </tr>

                                        </table>
                                    </td>
                                    <td width="7%" >&nbsp;</td>
                                    <td width="43%" align="center" >
                                        <table width="100%" height="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">

                                            <tr>
                                                <td width="43%" height="25" align="center" class="blue"><strong class="heading">Selected Components</strong></td>
                                            </tr>
                                        </table>
                                    </td>
                                    <td width="7%" >&nbsp;</td>
                                </tr>
                                <tr>
                                    <td align="right"  class="links_txt"># <span  id="totalcomplength"><%=compList.size()%></span></td>
                                    <td >&nbsp;</td>
                                    <td align="right"  class="links_txt"># <span id="selectedcomplength"></span></td>
                                    <td >&nbsp;</td>
                                </tr>
                                <tr>
                                    <td rowspan="6" ><select class="links_txt" name="compList" id="compList" size="12" multiple="multiple" style="width:300px" ondblclick="moveDualList(document.form1.compList,  document.form1.attachedlist, false);">

                                            <%for (int i = 0; i < compList.size(); i++) {%>
                                            <option value="<%=compList.get(i)%>"><%=compList.get(i)%></option>
                                            <%}%>

                                        </select>
                                    </td>
                                    <td align="center" >&nbsp;</td>
                                    <td rowspan="6" ><select class="links_txt" name="attachedlist" id="attachedlist" size="12" multiple="multiple" style="width:300px"  ondblclick="moveDualList(document.form1.attachedlist, document.form1.compList, false);">
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
                                    <td colspan="4">
                                        <table width="100%">
                                            <tr>

                                                <td colspan="2"  align="center">
                                                    <input type="submit" name="Next" id="Next" value="Submit" style="width:70px;" onClick="return validateForm();"/>
                                                </td>
                                                <%--          <td width="50%" align="left">
																				  <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />
																			 </td>--%>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>

                </tbody>
            </table>
        </form>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>

