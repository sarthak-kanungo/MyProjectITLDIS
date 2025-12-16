<%--
    Document   : EAMG_create_Tool_wz
     Author     : avinash.pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*,EAMG.Tool.Action.*" %>

<html xmlns="http://www.w3.org/1999/xhtml">
    <%
                response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                response.setHeader("Expires", "0");
                response.setHeader("Pragma", "no-cache");
                String contextPath = request.getContextPath();
                ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
                String server_name = (String) session.getValue("server_name");
                String ecatPath = (String) session.getValue("ecatPATH");
                String mainURL = (String) session.getValue("mainURL");
                String comp_type = (String) request.getAttribute("comp_type");

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
                String toolImages = object_pageTemplate.toolImageURL();
                String heading = null;
                int width = 659;
                int height = 84;
                String status = "" + session.getAttribute("status");
                Connection conn = null;
                Vector typeVec = new Vector();
                try {
                    EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
                    conn = holder.getConnection();
                    typeVec = methodutil.getCategoryTypes(conn, comp_type);
                } catch (Exception e) {
                    e.printStackTrace();
                }

    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <script language="javascript">
            function validate(myform)
            {
                var toolno=myform.toolno.value;
                toolno=TrimAll(toolno);
                var categoryTypeOthObject=myform.categoryTypeOth;
                if(toolno==""||toolno==null)
                {
                    alert("Please Enter <%=comp_type%>.");
                    myform.toolno.value="";
                    myform.toolno.focus();
                    return false;
                }
                else
                {

                    var res=isProperComponent(toolno);
                    if(res==false)
                    {
                        alert('<%=comp_type%> Number can not contain any special characters. Allowed Characters are Underscore(_) and Hyphen(-).');
                        myform.toolno.value='';
                        myform.toolno.focus();
                        return false;
                    }
                    var res=checkSpace(toolno);
                    if(res==false)
                    {
                        alert('<%=comp_type%> Number can not contain any Space.');
                        myform.toolno.value='';
                        myform.toolno.focus();
                        return false;
                    }

                    var res=isValidCharacterLength(toolno);
                    if(res==false)
                    {
                        alert('<%=comp_type%> Number can not be greater than 31 characters.');
                        myform.toolno.value='';
                        myform.toolno.focus();
                        return false;
                    }
                }

                var des=myform.desc.value;
                des=TrimAll(des);
                if(des==""||des==null)
                {
                    alert("Please Enter <%=comp_type%> Description.");
                    myform.desc.value="";
                    myform.desc.focus();
                    return false;
                }
                var res=isProperAll(des);
                if(res==false)
                {
                    alert("<%=comp_type%> Description Can not contain Special Character.");
                    myform.desc.value="";
                    myform.desc.focus();
                    return false;

                }
                var tool_rmk=myform.tool_rmk.value;
                tool_rmk=TrimAll(tool_rmk);
                var res=isProperAll(tool_rmk);
                if(tool_rmk!="" && res==false)
                {
                    alert("<%=comp_type%> Remarks Can not contain Special Character.");
                    myform.tool_rmk.value="";
                    myform.tool_rmk.focus();
                    return false;

                }

                if(document.myform.change_img[0].checked==true)
                {
                    var res=compare();
                    if(res==false)
                        return false;
                }

                if(categoryTypeOthObject.disabled==false)
                {
                    if(TrimAll(categoryTypeOthObject.value)=="")
                    {

                        alert('Please Enter Category');
                        categoryTypeOthObject.focus();
                        return false;
                    }
                    else
                    {
                        var res=stringOnlyValidation(categoryTypeOthObject.value);
                        if(res==false)
                        {
                            alert('Category can not contain any special characters.');
                            categoryTypeOthObject.focus();
                            return false;
                        }
                    }
                }


            }
            function enableCategoryOther()
            {
                if(document.getElementById("categoryType").value == '1Oth')
                {
                    document.getElementById("categoryTypeOth").disabled = false
                    document.getElementById("categoryTypeOth").style.background = '#FFFFFF'
                    document.getElementById("categoryTypeOth").focus()
                    return;
                }
                else
                {
                    document.getElementById("categoryTypeOth").value=''
                    document.getElementById("categoryTypeOth").disabled = true
                    return;
                }
            }
            function compare()
            {
                var img=document.getElementById("toolimg").value;
                if(img=='')
                {
                    alert("Please Select Image.");
                    return false;
                }
                else
                {
                    if(img.indexOf(':\\') ==-1 || img.indexOf(':\\')!=1)
                    {
                        alert('Invalid Image. Please Attach Valid .jpg Extension File.');
                        return false;
                    }
                    var dot_index=img.lastIndexOf(".");
                    var slash_index=img.lastIndexOf("\\");
                    var name=img.substring(dot_index+1);
                    name=name.toLowerCase();
                    if(name!="jpg")
                    {
                        alert("Please Select Valid .jpg Extension File.");
                        return false;
                    }
                    var temp_tool_no=img.substring(slash_index+1,dot_index);
                    temp_tool_no=temp_tool_no.toLowerCase();
                    var tool_number=document.getElementById('toolno').value;
                    tool_number=TrimAll(tool_number);
                    tool_number=tool_number.toLowerCase();

                    if(tool_number!=temp_tool_no)
                    {
                        alert("<%=comp_type%> Image Name must match with <%=comp_type%> Number.");
                        return false;
                    }


                    return true;

                }
            }
            function CancelProcess()
            {
                // var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                // if(cncl==true)
                parent.content.location="<%=contextPath%>/authJSP/EAMG_Tool/EAMG_create_tool.jsp";

            }


            function change_image()
            {
                if(document.myform.change_img[0].checked==true)
                    document.getElementById("toolimg").disabled=false;
                else
                    document.getElementById("toolimg").disabled=true;
            }
        </script>

    </head>

    <body>
        <%
                    String tdData = "MANAGE " + "" + comp_type.toUpperCase() + " >> ADD NEW " + "" + comp_type.toUpperCase();
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "ADD NEW " + "" + comp_type.toUpperCase();
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%--<tr>
                    <td> &nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=comp_type%>>> Add New <%=comp_type%></b></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td align="right" class="small">&nbsp;</td>
                </tr>

                <tr>
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;ADD NEW <%=comp_type.toUpperCase()%></b></td>
                            </tr>--%>
                <tr>
                    <td align="center" valign="top" bgcolor="#FFFFFF">
                        <form name="myform" ENCTYPE="multipart/form-data" method="post" action="<%=contextPath%>/EAMG_check_tool_exist.do"  onsubmit="return validate(this);">
                            <input type="hidden" name="comp_type"  value="<%=comp_type%>"/>
                            <br />
                            <%if (!status.equals("notexist")) {%>
                            <div class="red"><%=status%></div>
                            <%}%>
                            <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                <tr>
                                    <td colspan="2"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                            <tr>
                                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;Enter <%=comp_type%> Details </b></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="40%" height="25" align="left" class="text">&nbsp;<%=comp_type%> Number</td>
                                    <td width="60%" align="left" class="text"><label>
                                            <input type="text" size=15 name="toolno" id="toolno" value="" style="width:'100px'"/>
                                        </label></td>
                                </tr>
                                <tr>
                                    <td height="25" align="left" class="text">&nbsp;<%=comp_type%> Description</td>
                                    <td height="25" align="left" class="text"><label>
                                            <input type="text" size=15 name="desc" value="" style="width:'100px'"/>
                                        </label></td>
                                </tr>

                                <tr>
                                    <td height="25" align="left" class="text">&nbsp;<%=comp_type%> Remarks</td>
                                    <td height="25" align="left" class="text"><label>
                                            <input type="text" size=15 name="tool_rmk" id="tool_rmk" value="" style="width:'100px'" class="text" onblur="stringOnlyValidation(document.myform.tool_rmk);this.value=TrimAll(this.value)" />
                                        </label></td>
                                </tr>

                                <tr>
                                    <td height="25" align="left" class="text">&nbsp;<%=comp_type%> Image Required</td>
                                    <td height="25" align="left" class="text"><label>
                                            <input type="radio" id="change_img" name="change_img" value='yes' onclick="change_image();"/>Yes<input type="radio" id="change_img" name="change_img" value='no' checked onclick="change_image();"/>No
                                        </label></td>
                                </tr>
                                <tr>
                                    <td height="25" align="left" class="text">&nbsp;Browse Image File
                                        <label></label></td>
                                    <td height="25" align="left" class="text"><input type="file" id="toolimg" name="toolimg" value="" disabled/>
                                        <span class="red-for-image"><em>Image Size: 250 x 250 (Jpg)</em></span></td>
                                </tr>
                                <!--<tr>
                                    <td colspan="2"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                            <tr>
                                                <td height="30" align="left" class="blue"><b class="heading">&nbsp;Add Category Classification</b></td>
                                            </tr>
                                        </table></td>
                                </tr>-->
                                <tr>
                                    <td colspan="2">&nbsp;</td>
                                </tr>
                                <%if (typeVec.size() == 0) {%>
                                <tr>
                                    <td height="100" align="center" class="red" colspan="2" >No Category Classification Level available in Database.. </td>

                                </tr>
                                <%} else {%>
                                <tr>
                                    <td colspan="2">
                                        <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC" bgcolor="#CCCCCC">
                                            <tr>
                                                <td width="20%" height="20" align="center" class="blue"><strong class="heading">Level Description</strong></td>
                                                <td width="44%" height="20" align="center" class="blue"><strong class="heading">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Level Value</strong></td>
                                                <td width="35%" height="20"  align="center" class="blue"><strong class="heading">&nbsp;</strong></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>

                                <tr>
                                    <td colspan="2">
                                        <table width="100%" border="0" cellpadding="1" cellspacing="1">
                                            <tr>
                                                <td width="20%" height="25" align="left" bgcolor="#FFFFFF" class="text">&nbsp;&nbsp;Category</td>
                                                <td width="44%" height="25" align="right" bgcolor="#FFFFFF" class="text">
                                                    &nbsp;&nbsp;&nbsp;<select name="categoryType" id="categoryType" style="width:158px" onchange="enableCategoryOther()" class="text" value="">
                                                        <option value="">--Select--</option>
                                                        <%if (typeVec.size() > 0) {
                                                                if (typeVec != null) {

                                                                    for (int j = 0; j < typeVec.size(); j++) {
                                                        %>
                                                        <option><%=typeVec.elementAt(j)%></option>
                                                        <%}
                                                        %>
                                                        <option value="1Oth">OTHER</option>
                                                    </select>
                                                </td>
                                                <td width="35%" align="left" bgcolor="#FFFFFF"><span class="text">
                                                        <input type="text" name="categoryTypeOth" id="categoryTypeOth" value="" class="text"  disabled="true" style="width:154px" onblur="stringOnlyValidation(document.myform.categoryTypeOth)"/>
                                                    </span>
                                                </td>
                                                <% }
                                                    }
                                                %>


                                            </tr>
                                        </table></td>
                                </tr>
                                <% }%>

                                <!--
                                <tr>
                                    <td colspan="2">
                                        <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                                            <tr>
                                                <td height="30" align="left" class="blue"><b class="heading">&nbsp;Enter Parameters </b></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>

                                <tr>
                                    <td width="40%" align="left" class="text" >MOQ<span class="red-for-img">(Integer)</span></td>
                                    <td width="60%" align="left" class="text"><input type="text" size=15 name="MOQ" id="MOQ" maxlength="4" value="" style="width:'100px'"/></td>
                                </tr>

                                <tr>
                                    <td width="40%" align="left" class="text" >QML<span class="red-for-img">(Integer)</span></td>
                                    <td width="60%" align="left" class="text"><input type="text" size=15 name="QML" id="QML" value="" maxlength="2" style="width:'100px'"/></td>
                                </tr>

                                <tr>
                                    <td width="40%" align="left" class="text" >NDP<span class="red-for-img">(Integer)</span></td>
                                    <td width="60%" align="left" class="text"><input type="text" size=15 name="NDP" id="NDP" value="" maxlength="2" style="width:'100px'"/></td>
                                </tr>
                                -->
                                <tr>
                                    <td colspan="2">&nbsp;</td>
                                </tr>
                                <tr>
                                    <td colspan="2">
                                        <table width="100%">
                                            <tr>

                                                <td width="50%" align="right">
                                                    <input type="submit" name="Next" id="Next" value="Next" style="width:70px;" />
                                                </td>
                                                <td width="50%" align="left">
                                                    <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                            </table>
                        </form>
                    </td>
                </tr>
            </table>
            <%-- </td>
         </tr>
     </table>--%>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>

    </body>
</html>
