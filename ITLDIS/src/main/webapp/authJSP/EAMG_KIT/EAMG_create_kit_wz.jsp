<%-- 
    Document   : EAMG_create_kit_wz
     Author     : avinash.pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*,EAMG.Kit.Action.*" %>

<html xmlns="http://www.w3.org/1999/xhtml">
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
                imagesURL = object_pageTemplate.imagesURL();
                String session_id = session.getId();
                String getSession = (String) session.getValue("session_value");
                String contextPath = request.getContextPath();
                if (!session_id.equals(getSession)) {
                    object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                    return;
                }
                String kitImages = object_pageTemplate.kitImageURL();
                String heading = null;
                int width = 659;
                int height = 84;
                String status = "" + session.getAttribute("status");
                Connection conn = null;
                Vector typeVec = new Vector();
                try {
                    EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
                    conn = holder.getConnection();
                    typeVec = methodutil.getCategoryTypes(conn, "KIT");
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
                var kitno=myform.kitno.value;
                kitno=TrimAll(kitno);
                var categoryTypeOthObject=myform.categoryTypeOth;
					 
                if(kitno==""||kitno==null)
                {
                    alert("Please Enter Kit Number.");
                    myform.kitno.value="";
                    myform.kitno.focus();
                    return false;
                }
                else
                {
                                                        
                    var res=isProperComponent(kitno);
                    if(res==false)
                    {
                        alert('Kit Number can not contain any special characters. Allowed Characters are Underscore(_) and Hyphen(-).');
                        myform.kitno.value='';
                        myform.kitno.focus();
                        return false;
                    }
                    var res=checkSpace(kitno);
                    if(res==false)
                    {
                        alert('Kit Number can not contain any Space.');
                        myform.kitno.value='';
                        myform.kitno.focus();
                        return false;
                    }
                             
                    var res=isValidCharacterLength(kitno);
                    if(res==false)
                    {
                        alert('Kit Number can not be greater than 31 characters.');
                        myform.kitno.value='';
                        myform.kitno.focus();
                        return false;
                    }
                }
                           
                var des=myform.desc.value;
                des=TrimAll(des);
                if(des==""||des==null)
                {
                    alert("Please Enter Kit Description.");
                    myform.desc.value="";
                    myform.desc.focus();
                    return false;
                }
                var res=isProperAll(des);
                if(res==false)
                {
                    alert("Kit Description Cannot contain Special Character.");
                    myform.desc.value="";
                    myform.desc.focus();
                    return false;
                        
                }

                var kit_rmk=myform.kit_rmk.value;
                if(kit_rmk!=""){
                    var res=stringOnlyValidation(kit_rmk);
                    if(res==false)
                    {
                        alert('Kit Remarks Cannot contain Special Character.');
                        myform.kit_rmk.value="";
                        myform.kit_rmk.focus();
                        return false;

                    }
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
         
            function compare()
            {
                var img=document.getElementById("kitimg").value;
                if(img=='')
                {
                    alert("Please select Image");
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
                    var temp_kit_no=img.substring(slash_index+1,dot_index);
                    temp_kit_no=temp_kit_no.toLowerCase();
                    var kit_number=document.getElementById('kitno').value;
                    kit_number=TrimAll(kit_number);
                    kit_number=kit_number.toLowerCase();
                    
                    if(kit_number!=temp_kit_no)
                    {
                        alert("Kit Image Name must match with Kit Number.");
                        return false;
                    }
                   
                        
                    return true;
                    
                }
            }
            function CancelProcess()
            {
                //var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                // if(cncl==true)
                parent.content.location="<%=context%>/authJSP/EAMG_KIT/EAMG_create_kit.jsp";

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
            function change_image()
            {
                if(document.myform.change_img[0].checked==true)
                    document.getElementById("kitimg").disabled=false;
                else
                    document.getElementById("kitimg").disabled=true;
            }
        </script>

    </head>

    <body>
        <%
                    String tdData = "MANAGE KIT >> ADD NEW KIT";
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "ADD NEW KIT";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%-- <tr>
                     <td> &nbsp;</td>
                 </tr>
                 <tr>
                     <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                             <tr>
                                 <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Kit>> Add New Kit</b></td>
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
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;ADD NEW KIT</b></td>
                            </tr>--%>
                <tr>
                    <td align="center" valign="top" bgcolor="#FFFFFF">
                        <form name="myform" method="post" action="<%=context%>/EAMG_check_kit_exist.do"  onsubmit="return validate(this);">
                            <!--input type="hidden" name="param" id="param" value="KIT"/-->
                            <br />
                            <%if (!status.equals("notexist")) {%>
                            <div class="red"><%=status%></div>
                            <%}%>
                            <table width="95%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td colspan="2"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                            <tr>
                                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;Enter Kit Details </b></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="40%" height="25" align="left" class="text">&nbsp;Kit Number</td>
                                    <td width="60%" align="left" class="text"><label>
                                            <input type="text" size=15 name="kitno" id="kitno" value="" style="width:'100px'"/>
                                        </label></td>
                                </tr>
                                <tr>
                                    <td height="25" align="left" class="text">&nbsp;Kit Description</td>
                                    <td height="25" align="left" class="text"><label>
                                            <input type="text" size=15 name="desc" value="" style="width:'100px'"/>
                                        </label></td>
                                </tr>

                                <tr>
                                    <td height="25" align="left" class="text">&nbsp;Kit Remarks</td>
                                    <td height="25" align="left" class="text"><label>
                                            <input type="text" size=15 name="kit_rmk" id="kit_rmk" value="" style="width:'100px'" class="text" onblur="stringOnlyValidation(document.myform.kit_rmk);this.value=TrimAll(this.value)" />
                                        </label></td>
                                </tr>

                                <%--<tr>
                                    <td height="25" align="left" class="text">&nbsp;Kit Image Required</td>
                                    <td height="25" align="left" class="text"><label>
                                            <input type="radio" id="change_img" name="change_img" value='yes' onclick="change_image();"/>Yes<input type="radio" id="change_img" name="change_img" value='no' checked onclick="change_image();"/>No
                                        </label></td>
                                </tr>
                                <tr>
                                    <td height="25" align="left" class="text">&nbsp;Browse Image File
                                        <label></label></td>
                                    <td height="25" align="left" class="text"><input type="file" id="kitimg" name="kitimg" value="" disabled/>
                                        <span class="red-for-image"><em>Image Size: 465 x 620 (Jpg)</em></span></td>
                                </tr>--%>
                                <!--<tr>
                                    <td colspan="2"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                            <tr>
                                                <td height="30" align="left" class="blue"><b class="heading">&nbsp;Add Category Classification</b></td>
                                            </tr>
                                        </table></td>
                                </tr>-->

                                <tr>
                                    <td colspan="2">
                                        <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC" bgcolor="#CCCCCC">
                                            <tr>
                                                <td width="20%" height="20" align="center" class="blue"><strong class="heading">Level Description</strong></td>
                                                <td width="44%" height="20"  align="center" class="blue"><strong class="heading">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Level Value</strong></td>
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
                                                <td width="44%" height="35" align="right" bgcolor="#FFFFFF" class="text">
                                                    &nbsp;&nbsp;&nbsp;<select name="categoryType" id="categoryType" style="width:160px" onchange="enableCategoryOther()" class="text">
                                                        <option value="">--Select--</option>
                                                        <%if (typeVec != null && typeVec.size() > 0) {


                                                                        for (int j = 0; j < typeVec.size(); j++) {
                                                        %>
                                                        <option value="<%=typeVec.elementAt(j)%>"><%=typeVec.elementAt(j)%></option>
                                                        <%}


                                                        }

                                                         %>
                                                        <option value="1Oth">OTHER</option>
                                                      
                                                       
                                                    </select>


                                                </td>
                                                <td width="35%" align="left" bgcolor="#FFFFFF"><span class="text">
                                                        <input type="text" name="categoryTypeOth" id="categoryTypeOth" value="" class="text"  disabled="true" style="width:154px" onblur="stringOnlyValidation(document.myform.categoryTypeOth)"/>
                                                    </span>
                                                </td>



                                            </tr>
                                        </table></td>
                                </tr>


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
