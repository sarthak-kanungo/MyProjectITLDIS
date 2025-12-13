<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@page import="java.sql.*, java.util.*,com.EAMG.common.*" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="java.sql.*,viewEcat.comEcat.ConnectionHolder,viewEcat.comEcat.PageTemplate" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String context = request.getContextPath();
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
				String contextPath = request.getContextPath();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath+"/Index", "", imagesURL);
                return;
            }
            String heading = null;
            int width = 659;
            int height = 84;
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
        <meta http-equiv="cache-control" content="no-cache">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <%

                    response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
                    response.setHeader("Pragma", "no-cache"); //HTTP 1.0
                    response.setDateHeader("Expires", 0); //prevents caching at the proxy server
                    String group_no = "" + request.getParameter("group_no");
                    String svgPath=object_pageTemplate.svgPATH;
                    String path = mainURL + "dealer/ecat_print/group_jpg/" + group_no + ".jpg";
                    //group_no=group_no.toUpperCase();
                    String svgFilePath=contextPath+"/svg/"+group_no+".svg";
                    //System.out.println("svgFilePath=="+svgFilePath);
            %>
        <script language="javascript">
            function imgcompare()
            {
                var groupno='<%=group_no%>';
                var img=document.getElementById("groupimg").value;
                
                <%--if(img.indexOf(':\\') ==-1 || img.indexOf(':\\')==0)
                {
                    alert('Invalid Image File path. Please Attach Valid .jpg Extension File.');
                    return false;
                }--%>
                var pos1=img.lastIndexOf("\\");
                var pos2=img.lastIndexOf(".");
                var name=img.substring(pos1+1,pos2);
                name=name.toLowerCase();
                groupno=groupno.toLowerCase();
                var ext=img.substring(pos2+1);
                ext=ext.toLowerCase();

                if(ext=="jpg")
                {
                    if(groupno==name)
                    {
                        //document.getElementById("Next").style.visibility='visible';
                        return true;
                    }
                    else
                    {
                        alert("<%=PageTemplate.GROUP%> Image Name should be same as <%=PageTemplate.GROUP%> Number.");
                        // document.getElementById("Next").style.visibility='hidden';
                        return false;
                    }
                }
                else
                {
                    alert("Please Attach Valid .jpg Extension File.");
                    //document.getElementById("Next").style.visibility='hidden';
                    return false;
                }

            }
            function imgcomparesvg()
            {
                var groupno='<%=group_no%>';
                var img=document.getElementById("groupsvgimg").value;
               <%-- if(img.indexOf(':\\') ==-1 || img.indexOf(':\\')==0)
                {
                    alert('Invalid Image File path. Please Attach Valid .jpg Extension File.');
                    return false;
                }--%>
                var pos1=img.lastIndexOf("\\");
                var pos2=img.lastIndexOf(".");
                var name=img.substring(pos1+1,pos2);
                name=name.toLowerCase();
                groupno=groupno.toLowerCase();
                var ext=img.substring(pos2+1);
                ext=ext.toLowerCase();

                if(ext=="svg")
                {
                    if(groupno==name)
                    {
                        //document.getElementById("Next").style.visibility='visible';
                        return true;
                    }
                    else
                    {
                        alert("<%=PageTemplate.GROUP%> SVG File Name should be same as <%=PageTemplate.GROUP%> Number.");
                        // document.getElementById("Next").style.visibility='hidden';
                        return false;
                    }
                }
                else
                {
                    alert("Please Attach Valid .svg Extension File.");
                    //document.getElementById("Next").style.visibility='hidden';
                    return false;
                }

            }

            function validate(myform)
            {
                var desc=myform.desc.value;
                var img=document.getElementById("groupimg").value;
                var svgimg=document.getElementById("groupsvgimg").value;
                desc=TrimAll(desc);
                if(desc==""||desc==null)
                {
                    alert("Enter the <%=PageTemplate.GROUP%> Description.");
                    myform.desc.focus();
                    myform.desc.value="";
                    return false;
                }
                if(document.myform.change_img[0].checked==true)
                {
                    if(img=='')
                    {
                        alert("Please Select <%=PageTemplate.GROUP%> Image.");

                        return false;
                    }
                    else
                    {
                        var result=imgcompare();
                        if (result==false)
                            return result;
                    }
                }
                if(document.myform.change_svg_img[0].checked==true)
                {
                    if(svgimg=='')
                    {
                        alert("Please Select <%=PageTemplate.GROUP%> SVG File.");

                        return false;
                    }
                    else
                    {
                        var result=imgcomparesvg();
                        if (result==false)
                            return result;
                    }
                }
            }

            function change_image()
            {
                if(document.myform.change_img[0].checked==true)
                    document.getElementById("groupimg").disabled=false;
                else
                    document.getElementById("groupimg").disabled=true;
            }

            function change_svg_image()
            {
                if(document.myform.change_svg_img[0].checked==true)
                 {
                    document.getElementById("groupsvgimg").disabled=false;
                 }
                else
                 {
                    document.getElementById("groupsvgimg").disabled=true;
                 }
            }
            //svgimg1
            function loadImage()
            {
                //alert(document.myform.svgimg1.src = "<%=svgFilePath%>?time="+now.getTime());
                var now = new Date()    ;
                document.myform.img1.src = "<%=path%>?time="+now.getTime();
                //document.myform.svgimg1.src = "<%=svgFilePath%>?time="+now.getTime();
                //setTimeout('loadImage()',1000);
            }

            function CancelProcess()
            {
               // var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                //if(cncl==true)
                {
                    parent.content.location="<%=context%>/authJSP/EAMG_Group/EAMG_modify_group.jsp";
                }

            }
        </script>
    </head>
    <body>
         <%
                    String tdData = "MANAGE  TABLE >> MODIFY  "+PageTemplate.GROUP.toUpperCase();
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "MODIFY "+PageTemplate.GROUP.toUpperCase()+"";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%--<tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=PageTemplate.GROUP%>>> Modify <%=PageTemplate.GROUP%></b></td>
                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td align="right" class="small">&nbsp;</td>
                </tr>--%>
               
               <%-- <tr>
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;MODIFY <%=PageTemplate.GROUP.toUpperCase()%></b></td>
                            </tr>--%>
                            <tr>
                                <td align="center" valign="top" bgcolor="#FFFFFF">
                                    <form name="myform" ENCTYPE="multipart/form-data" method="post"  action="<%=context%>/EAMG_ModifyGroupDetailsAction.do" onsubmit="return validate(this);">
                                        <br />
                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">

                                            <tr>
                                                <td width="40%" height="25" align="left" class="text">&nbsp;<%=PageTemplate.GROUP%> Number :</td>
                                                <td width="60%" align="left" class="text"><label><%=group_no%></label></td>
                                            </tr>
                                            <tr>
                                                <%
                                                            Connection conn = null;
                                                            try {

                                                                session.setAttribute("group_no", group_no);
                                                                //System.out.println("group_no:"+group_no);
                                                                EAMG_MethodsUtility methodutil = new EAMG_MethodsUtility();
                                                                conn = holder.getConnection();
                                                                ResultSet rs2 = null;
                                                                Statement stmt3 = null;
                                                                stmt3 = conn.createStatement();
                                                                rs2 = stmt3.executeQuery("Select p1 from CAT_GROUP_KIT_DETAIL where  GRP_KIT_NO='" + group_no + "'");
                                                                if (rs2.next()) {
                                                                    String desc = rs2.getString(1);
                                                %>

                                                <td height="25" align="left" class="text">&nbsp;<%=PageTemplate.GROUP%> Description :</td>
                                                <td height="25" align="left" class="text"><label>
                                                        <input type="text"  name="desc" value="<%=desc%>"/>
                                                    </label></td>
                                                    <%}%>
                                            </tr>
                                            <tr>

                                                <td height="25" align="left" class="text">&nbsp;<%=PageTemplate.GROUP%> Image :</td>
                                                <td height="25" align="left" class="text"><label>
                                                        <img src='<%=path%>' name="img1" border="1" width="100" height="100"/>
                                                    </label></td>


                                            </tr>
                                            <tr>
                                                <td height="25" align="left" class="text">&nbsp;Change <%=PageTemplate.GROUP%> Image :</td>
                                                <td height="25" align="left" class="text"><label>
                                                        <input type="radio" id="change_img" name="change_img" value='yes' onclick="change_image();"/>Yes<input type="radio" id="change_img" name="change_img" value='no' checked onclick="change_image();"/>No
                                                    </label></td>
                                            </tr>
                                            
                                            <tr>
                                                <td height="25" align="left" class="text">&nbsp;Browse Image File :
                                                    <label></label></td>
                                                <td height="25" align="left" class="text"><input type="file" id="groupimg" name="groupimg" value="" disabled/>
                                                    <span class="red-for-image"><em>Image Size: 105 x 105 (jpg)</em></span></td>
                                            </tr>
                                            <tr>

                                                <td height="25" align="left" class="text">&nbsp;<%=PageTemplate.GROUP%> SVG File :</td>
                                                <td height="25" align="left" class="text"><label>
																		  <embed id="imghes" src="<%=contextPath+"/svg/"+group_no.toUpperCase()%>.svg" class="electricalzoom" type="image/svg+xml" height="100" width="100" >
            			                                  </embed>
                                                    
                                                    </label></td>


                                            </tr>
                                            <tr>
                                                <td height="25" align="left" class="text">&nbsp;Change <%=PageTemplate.GROUP%> SVG File :</td>
                                                <td height="25" align="left" class="text"><label>
                                                        <input type="radio" id="change_svg_img" name="change_svg_img" value='svgYes' onclick="change_svg_image();"/>Yes<input type="radio" id="change_svg_img" name="change_svg_img" value='svgNo' checked onclick="change_svg_image();"/>No
                                                    </label></td>
                                            </tr>
                                            <tr>
                                                <td height="25" align="left" class="text">&nbsp;Browse SVG File :
                                                    <label></label></td>
                                                <td height="25" align="left" class="text"><input type="file" id="groupsvgimg" name="groupsvgimg" value="" disabled/>
                                                    <span class="red-for-image"><em>Upload only (svg) File</em></span></td>
                                            </tr>
                                            <tr>
                                                <td colspan="2">&nbsp;</td>
                                            </tr>

                                            <%  } catch (Exception e) {
                                                            e.printStackTrace();
                                                        }
                                            %>
                                            <tr>
                                                <td colspan="2">
                                                    <table width="100%">
                                                        <tr>

                                                            <td align="right">
                                                                <input type="submit" name="Next" id="Next" value="Submit" style="width:70px;"/>
                                                            </td>
                                                            <td align="left">
                                                                <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />
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
        <script>loadImage();</script>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
