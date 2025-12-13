<%@page contentType="text/html"%>
<%@page import="jxl.*,java.io.File" pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="viewEcat.comEcat.ConnectionHolder,java.sql.Connection,java.io.*,com.EAMG.common.*,EAMG.PartDAO_CUD.*" %>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
%>
<%
            String result = "" + session.getAttribute("result");
            String option = "" + request.getAttribute("option");
            String comp_no = "" + session.getAttribute("comp_no");
            String contextPath = request.getContextPath();
            
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
	    String comp_type=(String)request.getAttribute("comp_type");
            servletURL = object_pageTemplate.servletURL();
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

        <script language="javascript" type="text/javascript">
    

       
            function validate_submit(elem)
            {
                if(elem.value=="Yes")
                {
                    document.form1.option.value='continue';
                    if(document.form1.change_img[0].checked==true)
                    {
                        var res=compare();
                        if(res==false)
                            return false;
                    }
                    document.form1.action="<%=contextPath%>/tool_creation.do";
                    document.form1.submit();
                }
                else if(elem.value=="No")
                {
                    var cancel_confirm= window.confirm('Do you really want to abort <%=comp_type%> Creation Process?')
                    if (cancel_confirm)
                    {
                        document.form1.option.value='cancel';
                        document.form1.action="<%=contextPath%>/authJSP/EAMG_Tool/EAMG_create_tool.jsp";
                        document.form1.submit();
                    }
                }
                else
                    return false;
        
            }
            function compare()
            {
                var img=document.getElementById("toolimg").value;
                if(img=='')
                {
                    alert("Image File can not be null.");
                    return false;
                }
                else
                {
                    if(img.indexOf(':\\') ==-1 || img.indexOf(':\\')!=1)
                    {
                        alert('Invalid Image File path. Please Attach Valid .jpg Extension File.');
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
                    var tool_number='<%=comp_no%>';
                    //  kit_number=TrimAll(kit_number);
                    tool_number=tool_number.toLowerCase();
            
                    if(tool_number!=temp_tool_no)
                    {
                        alert("<%=comp_type%> Image Name must match with <%=comp_type%> Number.");
                        return false;
                    }
                    return true;
            
                }
            }
            function change_image()
            {
                if(document.form1.change_img[0].checked==true)
                    document.getElementById("toolimg").disabled=false;
                else
                    document.getElementById("toolimg").disabled=true;
            }
            function open_window()
            {
                var inputwindow=window.open('<%=contextPath%>/authJSP/EAMG_Tool/EAMG_tool_xls_in_html.jsp','inputwindow','left=0,top=0,scrollbars=yes,resizable=1,toolbar=0,menubar=0');
                inputwindow.focus();
            }
        </script>


    </head>
    <body>
         <%
			if(comp_type==null || comp_type.equals(""))
			                  comp_type="Component";


                    String tdData = "MANAGE "+""+comp_type.toUpperCase()+" >> ADD NEW "+""+comp_type.toUpperCase();
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        
         
                    heading = "ADD NEW "+""+comp_type.toUpperCase();
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
       %>


		 
        <div align="center">

            <%
                        if (result.equals("Excel Validation Successful")) {
            %>

            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%--<tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <%if (option.equals("tool_validated")) {%>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=comp_type%> >> Add New <%=comp_type%></b></td>
                                <%}%>
                            </tr>
                        </table></td>
                </tr>
                <tr>
                    <td align="right" class="small"><b>&nbsp;</b></td>
                </tr>
               
                <tr>
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <%if (option.equals("tool_validated")) {%>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;ADD NEW TOOL</b></td>
                                <%}%>

                            </tr>--%>
                            <tr>
                                <td align="center" valign="top" bgcolor="#FFFFFF">
                                    <form name="form1" ENCTYPE="multipart/form-data" method="post">
                                        <input type="hidden" name="comp_type" value="<%=comp_type%>">
                                        <br />
                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                            <%if (option.equals("tool_validated")) {%>
                                            <tr>
                                                <td width="30%" height="25" align="left" class="text"><strong>&nbsp;<%=comp_type%> No :</strong></td>
                                                <td width="70%" height="25" align="left" class="text"><%=comp_no%></td>
                                            </tr>
                                            <tr>
                                                <td height="25" align="left" class="text"><strong>&nbsp;<%=comp_type%> Image Required :</strong></td>
                                                <td height="25" align="left" class="text"><label>
                                                        <input type="radio" id="change_img" name="change_img" value='yes' onclick="change_image();"/>Yes<input type="radio" id="change_img" name="change_img" value='no' checked onclick="change_image();"/>No
                                                    </label></td>
                                            </tr>
                                            <tr>
                                                <td height="25" align="left" class="text"><strong>&nbsp;Browse Image File :</strong>
                                                    <label></label></td>
                                                <td height="25" align="left" class="text"><input type="file" id="toolimg" name="toolimg" value="" disabled/>
                                                    <!--br><span class="red-for-image"><em>Image Size: 150 x 150 (Jpg)</em></span--></td>
                                            </tr>
                                            <%}%>
                                        </table>
                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>

                                                <td width="40%" height="25" align="left" class="text"><strong>&nbsp;Do you want to continue ?</strong></td>
                                                <td width="60%" height="25" align="left" class="text"><input type="button" value="Yes" onclick="validate_submit(this);" style="width:50"/>
                                                    <input type="button" value="No" onclick="validate_submit(this);" style="width:50"/>  
                                                    <input type="hidden" name="option" value=""/></td>
                                            </tr>
                                            <td colspan="3" align="right"><em><a href='#' onclick="javascript:open_window();" ><font color="#990000" size="2">XLS Preview </font></a><span class="red-for-temmplate">(xls).</span></em></td>
                                        </table>
                                    </form></td>
                            </tr>
                     <%--   </table></td>
                </tr>--%>
            </table>

            <%} else {
                //if(option.equals("tool_validated"))
            %>

            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <tr>
                    <td>&nbsp;</td>
                </tr>
                <%--<tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" >
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=comp_type%> >> Add New <%=comp_type%></b></td>
                            </tr>
                        </table></td>
                </tr>--%>
            </table>
            <font class="RED"><%=result%></font><br>

            <font class="red-for-temmplate-link"><a href="javascript:location.href='<%=contextPath%>/authJSP/EAMG_Tool/EAMG_create_tool.jsp';">Try Again</a></font>
            <%}
            %>

        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
