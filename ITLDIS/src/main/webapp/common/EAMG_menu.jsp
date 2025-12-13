<%--
    Document   : EAMG_menu
    Created on : May 9, 2011, 4:09:35 PM
    Author     : nikhil.gothankar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.ConnectionHolder,viewEcat.comEcat.PageTemplate" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="java.util.*" %>


<%
            //ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String trustedSite = "http://" + server_name + ":" + ecatPATH + "/";

            String contextPath = request.getContextPath();
            //String imagesURL = session.getAttribute("mainURL").toString();
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);


            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();

            String modelNo = object_pageTemplate.MODEL_NO;
            String group = object_pageTemplate.GROUP;

            String service = object_pageTemplate.service;
            String bulletin = object_pageTemplate.bulletin;

            String getSession = (String) session.getValue("session_value");
            String session_id = session.getId();



            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }
                 Vector userFunctionalities = new Vector((Vector) session.getValue("userFun"));

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" type="text/css" rel="stylesheet">
        <script  type="text/javascript" src="<%=imagesURL%>js/mnalJS.js" ></script>

        <script language="JavaScript" type="text/JavaScript">
            function show()
            {
                document.getElementById("id1").style.display='block';
            }
            function showMe(objectId)
            {
                hideAll();
                changeObjectVisibility(objectId, 'block');
            }
            function getStyleObject(objectId)
            {
                if(document.getElementById && document.getElementById(objectId))
                {
                    return document.getElementById(objectId).style;
                }
                else if (document.all && document.all(objectId))
                {
                    return document.all(objectId).style;
                }
                else if (document.layers && document.layers[objectId])
                {
                    return document.layers[objectId];
                }else
                {
                    return false;
                }
            }
            function hideAll()
            {
                for(counter = 1; counter <= 9; counter++)
                {
                    changeObjectVisibility('Layer' + counter, 'none');
                }
            }

            function changeObjectVisibility(objectId, newVisibility)
            {
                var styleObject = getStyleObject(objectId);
                if(styleObject)
                {
                    styleObject.display = newVisibility;
                }
            }
        </script>
        <script language=JavaScript type="text/javascript">

            function MM_openBrWindow(theURL,winName,features)
            {
                var isValidPopUpBlocker=detectPopupBlocker()
                if (isValidPopUpBlocker== false)
                {
                    return
                }
                window.open(theURL,winName,features);
            }

            function detectPopupBlocker()
            {
                var myTest = window.open("about:blank","","directories=no,height=100,width=100,menubar=no,resizable=no,scrollbars=no,status=no,titlebar=no,top=0,left=0");
                if (!myTest)
                {
                    alert("Your pop-up blocker is enabled.\nPlease turn-off your pop-up blocker.\nAlso, please add our site <%=trustedSite%> to your trusted sites.");
                    parent.location.href = '<%=contextPath%>/amw_LoginJSP/amw_LogoutPage.jsp'
                    return false;
                }
                else
                {
                    myTest.close();
                    return true;
                }
            }

            function show()
            {
                document.getElementById(id1).style.display='block';

            }
            function showMe(objectId)
            {
                changeObjectVisibility(objectId, 'block');
            }
            function getStyleObject(objectId)
            {
                if(document.getElementById && document.getElementById(objectId))
                {
                    return document.getElementById(objectId).style;
                }
                else if (document.all && document.all(objectId))
                {
                    return document.all(objectId).style;
                }
                else if (document.layers && document.layers[objectId])
                {
                    return document.layers[objectId];
                }else
                {
                    return false;
                }
            }
            function changeObjectVisibility(objectId, newVisibility)
            {
                var styleObject = getStyleObject(objectId);
                if(styleObject.display=='block')
                {
                    styleObject.display = 'none';
                }
                else if(styleObject.display=='none')
                {
                    styleObject.display = 'block';
                }
            }

            function click(e) {
                if (document.all) {
                    if (event.button == 2) {
                        alert('Right Click is Disabled');
                        return false;
                    }
                }
                if (document.layers) {
                    if (e.which == 3) {
                        alert('Right Click is Disabled');
                        return false;
                    }
                }
            }
            if (document.layers) {
                document.captureEvents(Event.MOUSEDOWN);
            }
            document.onmousedown=click;

            if (document.layers) document.captureEvents(Event.KEYPRESS); // needed if you wish to cancel the key
            document.onkeydown = keyhandler;
            function keyhandler(e) {
                var Key = (window.event) ? event.keyCode : e.keyCode;
                if (Key == 93)
                {
                    alert('Right Click is Disabled');
                    return false;
                }
            }

        </script>
    </head>
    <body >


        <table width="198" border="0" align="center" cellpadding="1" cellspacing="2" id="Table_01" style="background-color:#ffffff;">

            <tr valign="top" >
                <td  bgcolor="#236Dae" class="links"  colspan="2" background="<%=imagesURL%>/LeftMenuImages/LeftLinks_02.gif">Admin Module</td>
            </tr>

<%if(userFunctionalities.contains("39"))
    {%>
            <%--<tr>
                <td width="3%" align="left" valign="middle"><img alt="" src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                <td width="97%" class="links_txt"><A  href="<%=contextPath%>/usermgmt/EAMG_info_frame.jsp?option=manage_user"    target="content">Manage User</A></td>
            </tr>
            <tr>
                <td width="3%" align="left" valign="middle"><img alt="" src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                <td width="97%" class="links_txt"><A  href="<%=contextPath%>/usermgmt/EAMG_info_frame.jsp?option=manage_userType"    target="content">Manage User Type</A></td>
            </tr>

            <tr>
                <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                <td width="97%" class="links_txt"><a href="<%=contextPath%>/assignRoleAction.do?option=assignRoles"  target="content">Assign Roles To User Type</a></td>
            </tr>

            <tr>
                <td width="3%"  align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                <td width="97%" nowrap class="links_txt"><A  href="<%=contextPath%>/userMgmt_assignRolesToUser.do?option=initUser"  target="content" >Assign Product to Users</A></td>
            </tr>
            <tr>
                <td width="3%" nowrap align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                <td width="97%" nowrap class="links_txt"><A  href="<%=contextPath%>/userMgmt_assignRolesToUser.do?option=initProductType"  target="content" >Assign Product Type to Users</A></td>
            </tr>--%>
<%
}
            if(userFunctionalities.contains("39")|| userFunctionalities.contains("904") || userFunctionalities.contains("905"))
            {%>

            <tr>
                <td width="3%" align="left" valign="middle"><img alt="" src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                <td width="97%" class="links_txt"><A  href="#" onClick="showMe('Layer1','none');" style="text-decoration: none;" >Manage Part</A></td>
            </tr>
            <tr>
                <td colspan="2">
                    <div id="Layer1" LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 style="display:none;position:static;width:auto; padding: 5px 0 0 0">
                        <table id="Table_01" height="43" border="0" cellpadding="0" cellspacing="0">

                            <tr>
                                <td background="<%=imagesURL%>/LeftMenuImages/LeftLinks_Tab_04.jpg"  style=" width:3px"></td>
                                <td ><table width="100%" border="0" cellspacing="3" cellpadding="3">
                                        <%
                                        if(userFunctionalities.contains("39"))
                                        {%>
                                        <tr>
                                            <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                                            <td width="97%" class="links_txt" nowrap><A  href="<%=contextPath%>/authJSP/EAMG_Part/EAMG_browse_part_excel.jsp"  target="content" style="text-decoration: none;">Add Part</A></td>
                                        </tr>

                                        <tr>
                                            <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0" ></td>
                                            <td width="97%" class="links_txt" nowrap><a href="<%=contextPath%>/authJSP/EAMG_Part/EAMG_comp_modify.jsp"  target='content' style="text-decoration: none;">Modify Part</a></td>
                                        </tr>

                                           <tr>
                                            <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                                            <td width="97%" class="links_txt" nowrap><A  href="<%=contextPath%>/authJSP/EAMG_Part/EAMG_delete_unused_parts.jsp"  target="content" style="text-decoration: none;">Delete Unused Parts</A></td>
                                        </tr>
                                  <%}
                          
                                  if(userFunctionalities.contains("904"))
                                    {%>
                                        <tr>
                                            <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                                            <td width="97%" class="links_txt" nowrap><A  href="<%=contextPath%>/authJSP/EAMG_Part/Upload_Part_vs_Painted.jsp"  target="content" style="text-decoration: none;">Upload Part Vs Paint Code</A></td>
                                        </tr>
                                    <%}if(userFunctionalities.contains("905"))
                                    {%>
                                        <tr>
                                            <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                                            <td width="97%" class="links_txt" nowrap><A  href="<%=contextPath%>/authJSP/EAMG_Part/Upload_Part_vs_Alternate.jsp"  target="content" style="text-decoration: none;">Upload Part Vs Alternate</A></td>
                                        </tr>
                                     <%}%>
                                    </table>
                                </td>
                                <td background="<%=imagesURL%>/LeftMenuImages/LeftLinks_Tab_06.jpg"  style=" width:3px"></td>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
        <%
}
                 if(userFunctionalities.contains("39"))
    {%>

            <tr>
                <td width="3%" align="left" valign="middle"><img alt="" src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                <td width="97%" class="links_txt"><A  href="#" onClick="showMe('Layer2','none');" style="text-decoration: none;" >Manage <%=group%></A></td>
            </tr>
            <tr>
                <td colspan="2">
                    <div id="Layer2" LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 style="display:none;position:static;width:auto; padding: 5px 0 0 0">
                        <table id="Table_01" height="43" border="0" cellpadding="0" cellspacing="0">

                            <tr>
                                <td background="<%=imagesURL%>/LeftMenuImages/LeftLinks_Tab_04.jpg"  style=" width:3px"></td>
                                <td ><table width="100%" border="0" cellspacing="3" cellpadding="3">

                                        <tr>
                                            <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                                            <td width="97%" class="links_txt" nowrap><A  href="<%=contextPath%>/EAMG_Create_Group.do"  target="content" style="text-decoration: none;">Add <%=group%></A></td>
                                        </tr>
                                        <tr>
                                            <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0" ></td>
                                            <td width="97%" class="links_txt" nowrap><a href="<%=contextPath%>/authJSP/EAMG_Group/EAMG_modify_group.jsp"  target='content' style="text-decoration: none;">Modify <%=group%></a></td>
                                        </tr>
                                        <tr>
                                            <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                                            <td width="97%" class="links_txt" nowrap><A  href="<%=contextPath%>/authJSP/EAMG_Group/EAMG_delete_unused_groups.jsp"  target="content" style="text-decoration: none;">Delete Unused <%=group%></A></td>
                                        </tr>

                                        <tr>
                                            <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                                            <td width="97%" class="links_txt" nowrap><A  href="<%=contextPath%>/ChangeGroupSequence.do"  target="content" style="text-decoration: none;">Change <%=group%> Bom Sequence</A></td>
                                        </tr>

                                    </table>
                                </td>
                                <td background="<%=imagesURL%>/LeftMenuImages/LeftLinks_Tab_06.jpg"  style=" width:3px"></td>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="3%" align="left" valign="middle"><img alt="" src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                <td width="97%" class="links_txt"><A  href="#" onClick="showMe('Layer3','none');" style="text-decoration: none;" >Manage <%=modelNo%></A></td>
            </tr>
            <tr>
                <td colspan="2">
                    <div id="Layer3" LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 style="display:none;position:static;width:auto; padding: 5px 0 0 0">
                        <table id="Table_01" height="43" border="0" cellpadding="0" cellspacing="0">

                            <tr>
                                <td background="<%=imagesURL%>/LeftMenuImages/LeftLinks_Tab_04.jpg"  style=" width:3px"></td>
                                <td ><table width="100%" border="0" cellspacing="3" cellpadding="3">

                                        <tr>
                                            <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                                            <td width="97%" class="links_txt" nowrap><A  href="<%=contextPath%>/EAMG_create_model.do"  target="content" style="text-decoration: none;">Add <%=modelNo%></A></td>
                                        </tr>
                                        <tr>
                                            <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0" ></td>
                                            <td width="97%" class="links_txt" nowrap><a href="<%=contextPath%>/authJSP/EAMG_Variant/EAMG_modify_model_bypass.jsp"  target='content' style="text-decoration: none;">Modify <%=modelNo%></a></td>
                                        </tr>
                                        <%--<tr>
														  <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
														  <td width="97%" class="links_txt" nowrap><A  href="<%=contextPath%>/EAMG_Variant_Aggregates.do?option=initVariant"  target="content" style="text-decoration: none;"><%=modelNo%> Vs <%=aggregate%></A></td>
													 </tr>--%>
                                        <tr>
                                            <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                                            <td width="97%" class="links_txt" nowrap><A  href="<%=contextPath%>/authJSP/EAMG_Variant/EAMG_delete_model.jsp"  target="content" style="text-decoration: none;">Delete Unused <%=modelNo%></A></td>
                                        </tr>

                                    </table>
                                </td>
                                <td background="<%=imagesURL%>/LeftMenuImages/LeftLinks_Tab_06.jpg"  style=" width:3px"></td>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="3%" align="left" valign="middle"><img alt="" src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                <td width="97%" class="links_txt"><A  href="#" onClick="showMe('Layer4','none');" style="text-decoration: none;" >Manage Kit</A></td>
            </tr>
            <tr>
                <td colspan="2">
                    <div id="Layer4" LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 style="display:none;position:static;width:auto; padding: 5px 0 0 0">
                        <table id="Table_01" height="43" border="0" cellpadding="0" cellspacing="0">

                            <tr>
                                <td background="<%=imagesURL%>/LeftMenuImages/LeftLinks_Tab_04.jpg"  style=" width:3px"></td>
                                <td ><table width="100%" border="0" cellspacing="3" cellpadding="3">

                                        <tr>
                                            <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                                            <td width="97%" class="links_txt" nowrap><A  href="<%=contextPath%>/authJSP/EAMG_KIT/EAMG_create_kit.jsp"  target="content" style="text-decoration: none;">Add New Kit</A></td>
                                        </tr>
                                        <tr>
                                            <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0" ></td>
                                            <td width="97%" class="links_txt" nowrap><a href="<%=contextPath%>/authJSP/EAMG_KIT/EAMG_kit_modify_comptype.jsp"  target='content' style="text-decoration: none;">Modify Kit</a></td>
                                        </tr>
                                        <tr>
                                            <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                                            <td width="97%" class="links_txt" nowrap><A  href="<%=contextPath%>/authJSP/EAMG_KIT/EAMG_delete_unused_kits.jsp"  target="content" style="text-decoration: none;">Delete Unused Kit</A></td>
                                        </tr>

                                    </table>
                                </td>
                                <td background="<%=imagesURL%>/LeftMenuImages/LeftLinks_Tab_06.jpg"  style=" width:3px"></td>
                            </tr>


                        </table>
                    </div>
                </td>
            </tr>
            <tr>
                <td width="3%" align="left" valign="middle"><img alt="" src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                <td width="97%" class="links_txt"><A  href="#" onClick="showMe('Layer5','none');" style="text-decoration: none;" >Manage Tool/Lube</A></td>
            </tr>
            <tr>
                <td colspan="2">
                    <div id="Layer5" LEFTMARGIN=0 TOPMARGIN=0 MARGINWIDTH=0 MARGINHEIGHT=0 style="display:none;position:static;width:auto; padding: 5px 0 0 0">
                        <table id="Table_01" height="43" border="0" cellpadding="0" cellspacing="0">

                            <tr>
                                <td background="<%=imagesURL%>/LeftMenuImages/LeftLinks_Tab_04.jpg"  style=" width:3px"></td>
                                <td ><table width="100%" border="0" cellspacing="3" cellpadding="3">

                                        <tr>
                                            <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                                            <td width="97%" class="links_txt" nowrap><A  href="<%=contextPath%>/authJSP/EAMG_Tool/EAMG_create_tool.jsp"  target="content" style="text-decoration: none;">Add New Tool/Lube</A></td>
                                        </tr>
                                        <tr>
                                            <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0" ></td>
                                            <td width="97%" class="links_txt" nowrap><a href="<%=contextPath%>/authJSP/EAMG_Tool/EAMG_tool_modify_comptype.jsp"  target='content' style="text-decoration: none;">Modify Tool/Lube</a></td>
                                        </tr>
                                        <tr>
                                            <td width="3%" align="left" valign="middle"><img src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                                            <td width="97%" class="links_txt" nowrap><A  href="<%=contextPath%>/authJSP/EAMG_Tool/EAMG_delete_unused_tools.jsp"  target="content" style="text-decoration: none;">Delete Unused Tool/Lube</A></td>
                                        </tr>

                                    </table>
                                </td>
                                <td background="<%=imagesURL%>/LeftMenuImages/LeftLinks_Tab_06.jpg"  style=" width:3px"></td>
                            </tr>
                        </table>
                    </div>
                </td>
            </tr>

            <tr>
                <td width="3%" align="left" valign="middle"><img alt="" src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                <td width="97%" class="links_txt"><A  href="<%=contextPath%>/UploadECN.do?option=init"  style="text-decoration: none;" target="content">Implement ECN</A></td>
            </tr>

            <%--<tr>
                <td width="3%" align="left" valign="middle"><img alt="" src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                <td width="97%" class="links_txt"><A  href="<%=contextPath%>/Assign_ComponentToModelAction.do?option=initModel"    target="content">Attach Component to Model</A></td>
            </tr>
            <tr>
                <td width="3%" align="left" valign="middle"><img alt="" src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                <td width="97%" class="links_txt"><A  href="<%=contextPath%>/authJSP/other/EAMG_Upload_TSN_matrix.jsp"    target="content">Upload TSN Matrix</A></td>
            </tr>--%>
            <tr>
                <td width="3%" align="left" valign="middle"><img alt="" src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                <td width="97%" class="links_txt"><A  href="<%=contextPath%>/ECNReportDisp.do"    target="content">ECN Report</A></td>
            </tr>
            <tr>
                <td width="3%" align="left" valign="middle"><img alt="" src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                <td width="97%" class="links_txt"><A  href="<%=contextPath%>/changeFCodeTableSequence.do?option=initFCodeSequence"    target="content">Change <%=modelNo%> <%=group%> Sequence</A></td>
            </tr>

              <%-- <tr>
                <td width="3%" align="left" valign="middle"><img alt="" src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                <td width="97%" class="links_txt"><A  href="<%=contextPath%>/authJSP/other/UsageDetails.jsp"  target="content">Usage Details</A></td>
            </tr>--%>

 <%}

                                  if(userFunctionalities.contains("917"))
                                    {%>

           <tr>
                <td width="3%" align="left" valign="middle"><img alt="" src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                <td width="97%" class="links_txt"><A  href="<%=contextPath%>/serviceBulletin.do?option=initSBUpdate"  target="content">Upload <%=bulletin%></A></td>
            </tr>

            <tr>
                <td width="3%" align="left" valign="middle"><img alt="" src="<%=imagesURL%>/LeftMenuImages/bullet.jpg"   border="0"></td>
                <td width="97%" class="links_txt"><A  href="<%=contextPath%>/serviceBulletin.do?option=initSBDelete"  target="content">Delete <%=bulletin%></A></td>
            </tr>

            <%}%>
          
               
        </table>
    </body>
</html>
