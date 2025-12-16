<%@page contentType="text/html"%>
<%@page import="jxl.*,java.io.File" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.ConnectionHolder,viewEcat.comEcat.PageTemplate" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
%>
<%
            String result = "" + request.getAttribute("result");
            //String grp_cr_status=""+request.getAttribute("grp_cr_status");

            //System.out.println("result is :"+result);

            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String contextPath = request.getContextPath();

            //String imagesURL = session.getAttribute("mainURL").toString();
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String imagesURL = "";
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

            String group_xls_file = "" + session.getAttribute("group_xls_file");
            String group_number = "" + session.getAttribute("group_number");
            File grp_xls_file = new File(group_xls_file);
            String context = request.getContextPath();
            String user_id=""+(String) session.getValue("userCode");

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
         <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />


        <%
                    if (result.equals("Excel Template validated successfully.")) {
        %>
        <script>

            function validate_submit(elem)
            {
                group_no='<%=group_number%>';
                //alert(group_no)
                if(elem.value=="Yes")
                {

                    document.form1.option.value='continue';
                    document.form1.action="<%=context%>/EAMG_create_group_data.do";
                    var result=compare(group_no);
                    
                    var resultSvg;
                    if(result==true)
                     {
                        resultSvg=compareSvg(group_no);
                     }

                     if(result==true && resultSvg==true)
                      {
                        document.form1.submit()
                      }
                        
                }
                else
                {

                    if (window.confirm('Do you really want to abort <%=PageTemplate.GROUP%> Creation Process?'))
                    {
                    document.form1.option.value='cancel';
                        document.form1.action="<%=context%>/EAMG_create_group_data.do";
                        document.form1.submit();
                    }
                    else
                        return false;

                }
                // return true;

            }

            function compare(group_no)
            {
                //alert(group_no);

                var img=document.form1.file.value;
                if(img=='')
                {
                    alert("Please Attach <%=PageTemplate.GROUP%> image.");
                    document.form1.file.focus();
                    return false;
                }
                else
                {
                    var dot_index=img.lastIndexOf(".");
                    var slash_index=img.lastIndexOf("\\");
                    var name=img.substring(dot_index+1);
                    name=name.toLowerCase();

                    if(name!="jpg")
                    {
                        alert("Please Attach Valid .jpg Extension File.");

                        document.form1.file.focus();
                        return false;
                    }
                    var temp_group_no=img.substring(slash_index+1,dot_index);
                    //alert("temp_group_no:"+temp_group_no)
                    temp_group_no=temp_group_no.toLowerCase();
                    group_no=group_no.toLowerCase();
                    if(group_no!=temp_group_no)
                    {
                        alert("<%=PageTemplate.GROUP%> Image Name must match the <%=PageTemplate.GROUP%> Number.");
                        return false;
                    }

                    return true;
                }


            }

            function compareSvg(group_no)
            {
                //alert(group_no);

                var svgFile=document.form1.svgFile.value;
                if(svgFile=='')
                {
                    alert("Please Attach <%=PageTemplate.GROUP%> SVG File.");
                    document.form1.svgFile.focus();
                    return false;
                }
                else
                {
                    var dot_index=svgFile.lastIndexOf(".");
                    var slash_index=svgFile.lastIndexOf("\\");
                    var name=svgFile.substring(dot_index+1);
                    name=name.toLowerCase();

                    if(name!="svg")
                    {
                        alert("Please Attach Valid .svg Extension File.");

                        document.form1.svgFile.focus();
                        return false;
                    }
                    var temp_group_no=svgFile.substring(slash_index+1,dot_index);
                    //alert("temp_group_no:"+temp_group_no)
                    temp_group_no=temp_group_no.toLowerCase();
                    group_no=group_no.toLowerCase();
                    if(group_no!=temp_group_no)
                    {
                        alert("<%=PageTemplate.GROUP%> SVG File Name must match the <%=PageTemplate.GROUP%> Number.");
                        return false;
                    }

                    return true;
                }


            }

            function open_window()
            {
                var inputwindow=window.open('authJSP/EAMG_Group/EAMG_open_grp_xls_data.jsp','inputwindow','left=0,top=0,scrollbars=yes,resizable=1,toolbar=0,menubar=0');
                inputwindow.focus();
            }
        </script>

    </head>

    <%
                }
    %>
    <body>
        <%
                    String tdData = "MANAGE  "+PageTemplate.GROUP.toUpperCase()+" >> ADD  NEW "+PageTemplate.GROUP.toUpperCase();
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <div align="center">
            <%
                    heading = "ADD NEW "+PageTemplate.GROUP+"";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
           %>
            <%
                        if (result.equals("Excel Template validated successfully.")) {

            %>

            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <%--<tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=PageTemplate.GROUP%>>> Add  New <%=PageTemplate.GROUP%></b></td>
                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td align="right" class="small"><b>&nbsp;</b></td>
                </tr>--%>
                
                <tr>
                    <td valign="top">
                        <table width="100%" border="0" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                           <%-- <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;ADD NEW <%=PageTemplate.GROUP.toUpperCase()%></b></td>
                            </tr>--%>
                            <tr>
                                <td align="center" valign="top" bgcolor="#FFFFFF">
                                    <form name="form1" ENCTYPE="multipart/form-data" method="post">
                                        <br />
                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">

                                            <tr>

                                                <td width="15%" height="25" align="left" class="text"><strong>&nbsp;<%=PageTemplate.GROUP%> No :</strong></td>
                                                <td width="85%" height="25" align="left" class="text"><%=group_number%></td>
                                            </tr>
                                            <tr>
                                        </table>
                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                            <td width="40%" height="25" align="left" class="text"><strong>&nbsp;Attach <%=PageTemplate.GROUP%> Image :</strong></td>
                                            <td width="60%" height="25" align="left" class="text"><input type="file" name="file" value="" style="width:225px">&nbsp;&nbsp;<span class="red-for-image"><em>Image Size: 105 x 105 (jpg)</em></span></td>
                                            </tr>
                                            <tr>
                                            <td width="40%" height="25" align="left" class="text"><strong>&nbsp;Attach <%=PageTemplate.GROUP%> SVG File :</strong></td>
                                            <td width="60%" height="25" align="left" class="text"><input type="file" name="svgFile" value="" style="width:225px">&nbsp;&nbsp;<span class="red-for-image"><em>Upload (SVG) File only</em></span></td>
                                            </tr>
                                            <tr>

                                                <td width="40%" height="25" align="left" class="text"><strong>&nbsp;Do you want to continue ?</strong></td>
                                                <td width="60%" height="25" align="left" class="text"><input type="button" value="Yes" onclick="validate_submit(this);" style="width:'50'"/>
                                                    <input type="button" value="No" onclick="validate_submit(this);" style="width:'50'"/>
                                                    <input type="hidden" name="option" value=""/></td>
                                            </tr>

                                            <tr>
                                            <td colspan="3" align="right"><em><a href="#" onclick="javascript:open_window();" ><font color="#990000" size="2">XLS Preview </font></a><span class="red-for-temmplate">(xls).</span></em></td>
                                            </tr>
                                        </table>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
            </table>
            <%} else {%>
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center">

                        <table width="700" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=PageTemplate.GROUP%>>> Add  New <%=PageTemplate.GROUP%></b></td>
                            </tr>
                        </table>
                    </td></tr>
                <tr>
                    <td>&nbsp;</td>
                </tr>

                <tr>
                    <td class="red" align="center"><%=result%></td></tr>

                <tr>
                    <td class="red-for-temmplate-link" align="center"><a href="<%=context%>/EAMG_Create_Group.do">Try Again</a></td></tr>
            </table>

            <%}%>      
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
