<%-- 
    Document   : EAMG_modify_kit_details
    Author     : avinash.pandey
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*" %>

<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            Connection conn = null;
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            conn = holder.getConnection();
            String context = request.getContextPath();
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
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }
            EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
            Vector typeVec = methodutil.getCategoryTypes(conn, "KIT");
            String heading = null;
            int width = 659;
            int height = 84;

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <script language="javascript">
            
            <%
                        String comp_type = "";
                        String comp_no = "";
                        String iscomppresent = "";
                        File kit_image = null;
                        try {
                            comp_type = (String) session.getAttribute("comp_type");
                            comp_no = ((String) session.getAttribute("comp_param_list")).toUpperCase();
                            iscomppresent = (String) session.getAttribute("iscomppresent");
                            String type = "";
                            if (comp_type.equals("KIT")) {
                                type = "KIT";
                            }

                            String path = mainURL + "dealer/ecat_print/kit_image/" + comp_no + ".jpg";
                            String kit_image_path = ecatPath + "dealer/ecat_print/kit_image/" + comp_no + ".jpg";
                            kit_image = new File(kit_image_path);

                            conn = holder.getConnection();
                            ResultSet rs = null;
                            Statement stmt = null;
                            stmt = conn.createStatement();
                            String category = "";
                            String kit_type = "";
                            String description = "";
                            String remarks = "";
                            String ref_no = "";
                            rs = stmt.executeQuery("Select part_type,p1,p2,p3,np4 from cat_part where part_no='" + comp_no + "'");
                            if (rs.next()) {
                                kit_type = rs.getString("part_type");
                                description = rs.getString("p1");
                                ref_no = rs.getString("p2");
                                remarks = rs.getString("p3");
                                if (remarks != null) {
                                    remarks = remarks;
                                } else {
                                    remarks = "";
                                }
                                category = rs.getString("np4");
                            }
                            rs.close();
                            stmt.close();
                            if(category==null)
                                category="";
            %>
                              
               
                function validate(myform)
                {
                    var categoryTypeOthObject=myform.categoryTypeOth;

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
                        if(TrimAll(categoryTypeOthObject.value)==" ")
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
                function CancelProcess()
                {
                    // var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                    //  if(cncl==true)
                    parent.content.location="<%=context%>/authJSP/EAMG_KIT/EAMG_modify_Kit_by_options.jsp";

                }

                function compare()
                {
                    var img=document.getElementById("kitimg").value;
                    if(img=='')
                    {
                        alert("Please select Image.");
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
                        var kit_number='<%=comp_no%>';
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
            <%
                                                                 if (kit_image.length() > 0) {%>
                                                                     function loadImage()
                                                                     {
                                                                         var now = new Date();
                                                                         document.myform.img1.src = "<%=path%>?time="+now.getTime();
                                                                         //setTimeout('loadImage()',1000);
                                                                     }
            <%}%>
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
                                                             String tdData = "MANAGE KIT >> MODIFY KIT >> KIT DETAILS";
                                                             object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                                                             heading = "" + type.toUpperCase() + " DETAILS ";
                                                             out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">

                <%if (iscomppresent.equals("notpresent")) {%>
                <%--<tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Kit >> Modify Kit >> Kit Details</b></td>
                            </tr>
                        </table></td>
                </tr>--%>
                <tr>
                    <td align="right" class="small">&nbsp;</td>
                </tr>

                <tr>
                    <td align="center" class="red"><%=type%> '<%=comp_no%>' does not exist in database. </td>
                </tr>
                <tr>  <td class="red-for-temmplate-link" align="center" ><a href="javascript:location.href='<%=context%>/authJSP/EAMG_KIT/EAMG_kit_modify_comptype.jsp';">Try Again</a></td></tr>
                <%} else {%>

                <%--<tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Kit >> Modify Kit >> Kit Details</b></td>
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
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;<%=type.toUpperCase()%> DETAILS </b></td>
                            </tr>--%>
                <tr>
                    <td align="center" valign="top" bgcolor="#FFFFFF">
                        <form name="myform" action="<%=context%>/EAMG_modify_kit.do" onsubmit="return validate(this);" method="post">
                            <br />
                            <table width="95%" border="0" cellspacing="0" cellpadding="0">
                                <tr>
                                    <td width="35%" height="25" align="left" class="text">&nbsp;<%=type%> Number :</td>
                                    <td width="65%" height="25" align="left" class="text"> <%=comp_no%> </td>
                                </tr>

                                <tr>
                                    <td height="25"  align="left"  class="text">&nbsp;<%=type%> Description :</td>
                                    <td align="left" class="text"><input type="text" name="desc" class="text" value="<%=description%>" style="width:'100px'"/></td>

                                </tr>
                                <tr>
                                    <td height="25" align="left" class="text">&nbsp;Kit Remarks</td>
                                    <td height="25" align="left" class="text"><label>
                                            <input type="text" size=15 name="kit_rmk" id="kit_rmk" value="<%=remarks%>" style="width:'100px'" class="text" onblur="stringOnlyValidation(document.myform.kit_rmk);this.value=TrimAll(this.value)" />
                                        </label></td>
                                </tr>
                                <%-- <%
																if (kit_image.length() > 0) {%>
                                 <tr>
                                     <td height="25" align="left" class="text">&nbsp;<%=type%> Image :</td>
                                     <td height="25" align="left" class="text"><label>
                                             <img src='<%=path%>' name="img1" border="1" width="100" height="100"/>
                                         </label></td>
                                 </tr>
                                 <%} else {%>--%>
                                <%-- <tr>
                                     <td height="25" align="left" class="text">&nbsp;<%=type%> Image :</td>
                                     <td height="25" align="left" class="text"><label>
                                             <span class="red-for-img"> No Image.</span>
                                         </label></td>
                                 </tr>
                                 <%}%>
                                 <tr>
                                     <td height="25" align="left" class="text">&nbsp;Change <%=type%> Image :</td>
                                     <td height="25" align="left" class="text"><label>
                                             <input type="radio" id="change_img" name="change_img" value='yes' onclick="change_image();"/>Yes<input type="radio" id="change_img" name="change_img" value='no' checked onclick="change_image();"/>No
                                         </label></td>
                                 </tr>
                                 <tr>
                                     <td height="25" align="left" class="text">&nbsp;Browse Image File :
                                         <label></label></td>
                                     <td height="25" align="left" class="text"><input type="file" id="kitimg" name="kitimg" value="" disabled/>
                                         <span class="red-for-image"><em>Image Size: 150 x 150 (Jpg)</em></span></td>
                                 </tr>--%>


                                <tr>
                                    <td class="text" ></td>
                                </tr>


                                <tr>
                                    <td colspan="2">
                                        <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC" bgcolor="#CCCCCC">
                                            <tr>
                                                <td width="20%" height="20" align="center" class="blue"><strong class="heading">Level Description</strong></td>
                                                <td width="44%" height="20"  align="center" class="blue"><strong class="heading">Level Value&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</strong></td>
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
                                                    &nbsp;&nbsp;&nbsp;<select name="categoryType" id="categoryType" style="width:195px" onchange="enableCategoryOther()" class="text">
                                                        <option value="">--Select--</option>
                                                        <%if (typeVec.size() > 0) {
                                                                if (typeVec != null) {

                                                                    for (int j = 0; j < typeVec.size(); j++) {
                                                                        if (category.equalsIgnoreCase(typeVec.elementAt(j).toString())) {
                                                        %>
                                                        <option selected><%=typeVec.elementAt(j)%></option>
                                                        <%} else {%>
                                                        <option ><%=typeVec.elementAt(j)%></option>
                                                        <%}
                                                                    }
                                                                }
                                                            }%>
                                                        <option value="1Oth">OTHER</option>>
                                                    </select>
                                                </td>
                                                <td width="35%"  align="left" bgcolor="#FFFFFF"><span class="text">

                                                        <input type="text" name="categoryTypeOth" id="categoryTypeOth" value="" class="text"  disabled="true" style="width:154px" onblur="stringOnlyValidation(document.myform.categoryTypeOth)"/>
                                                    </span>
                                                </td>


                                            </tr>
                                        </table></td>
                                </tr>


                                <%--tr>
                                    <td width="40%" align="left" class="text" align="center">MOQ<span class="red-for-img">(Integer)</span></td>
                                    <td width="60%" align="left" class="text">
                                        <input type="text" size=15 name="MOQ" id="MOQ" maxlength="4" value="<%=MOQ%>" style="width:'100px'" /></td>
                                </tr>

                                            <tr>
                                                <td width="40%" align="left" class="text" >QML<span class="red-for-img">(Integer)</span></td>
                                                <td width="60%" align="left" class="text"><input type="text" maxlength="4" name="QML" id="QML" value="<%=QML%>"  style="width:'100px'"/></td>
                                            </tr>

                                            <tr>
                                                <td width="40%" align="left" class="text" >NDP<span class="red-for-img">(Integer)</span></td>
                                                <td width="60%" align="left" class="text"><input type="text" maxlength="4" name="NDP" id="NDP" value="<%=NDP%>"  style="width:'100px'"/></td>
                                            </tr--%>
                                <tr>
                                    <td colspan="2">
                                        <table width="100%">
                                            <tr>

                                                <td width="50%" align="right">
                                                    <input  type="submit" name="Next" id="Next" value="Submit" style="width:70px;"/>
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
                        <%--</td>
                    </tr>
                </table>--%>
                    </td>
                </tr>
            </table>
            <%
                out.println(object_pageTemplate.tableFooter());
            %>
        </div>
        <%
            if (kit_image.length() > 0) {%>
        <script>loadImage();</script>
        <%}%>

    </body>
    <%}
                } catch (Exception e) {
                    e.printStackTrace();
                }
    %>
</html>
