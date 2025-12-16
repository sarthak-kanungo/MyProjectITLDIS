<%--
    Document   : EAMG_group_creation_wz
    Created on : Nov 3, 2011, 12:38:20 PM
    Author     : manish.kishore
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>


<%
            String contextPath = request.getContextPath();

            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String context = request.getContextPath();
            String server_name = (String) session.getValue("server_name");
            String ecatPATH = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");

            //String imagesURL = session.getAttribute("mainURL").toString();
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPATH, mainURL);

            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }


            String heading = null;
            int width = 659;
            int height = 84;
%>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
    <title>V4-Ecatalogue</title>
    <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
    <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
    <script type="text/javascript" language="javascript">

        function validate(myform)
        {
            var grpno=myform.groupname.value;
            grpno=TrimAll(grpno);
            if(grpno==""||grpno==null)
            {
                alert("Enter the <%=PageTemplate.GROUP%> Number.");
                myform.groupname.value='';
                myform.groupname.focus();
                return false;
            }
            else
            {
                var res=isProperComponent(grpno);
                if(res==false)
                {
                    alert('<%=PageTemplate.GROUP%> Number can not contain any special characters. Allowed Characters are Underscore(_) and Hyphen(-).');
                    myform.groupname.value='';
                    myform.groupname.focus();
                    return false;
                }

                var res=checkSpace(grpno);
                if(res==false)
                {
                    alert('<%=PageTemplate.GROUP%> Number can not contain any Space.');
                    myform.groupname.value='';
                    myform.groupname.focus();
                    return false;
                }

                var res=isValidCharacterLength(grpno);
                if(res==false)
                {
                    alert('<%=PageTemplate.GROUP%> Number can not be greater than 31 characters.');
                    myform.groupname.value='';
                    myform.groupname.focus();
                    return false;
                }
            }

            var des=myform.groupdesc.value;
            des=TrimAll(des);
            if(des==""||des==null)
            {
                alert("Enter <%=PageTemplate.GROUP%> Description.");
                myform.groupdesc.value='';
                myform.groupdesc.focus();

                return false;
            }
            res=isProperAll(des);
            if(res==false)
            {
                alert('<%=PageTemplate.GROUP%> Description can not contain any special characters.');
                myform.groupdesc.value='';
                myform.groupdesc.focus();
                return false;
            }

            var result=compare();
            if(result==false)
                return result;

            var result1=compareSvgFile();
            if(result1==false)
                return result1;

            var com=myform.comp.value;
            com=TrimAll(com);
            if(com=='')
            {
                alert("Enter the Number of Components to be attached in <%=PageTemplate.GROUP%>. ");
                myform.comp.value="";
                myform.comp.focus();
                return false;
            }
            if(com==0)
            {
                alert("Number of Components can not be 0.");
                myform.comp.value="";
                myform.comp.focus();
                return false;
            }
            var res=isProperAll(com);
            if(res==false)
            {
                alert("No Special Symbols allowed. Please Enter only Integer Value for Number of Components.");
                myform.comp.value="";
                myform.comp.focus();
                return false;
            }
            var res=checkQuantity(com);
            if(res==false)
            {
                alert("No Special Symbols allowed. Please Enter only Integer Value for Number of Components.");
                myform.comp.value="";
                myform.comp.focus();
                return false;
            }
            if(com>250)
            {
                var res=confirm("Do you want to add '"+com+"' Number of Components. Are you sure?");
                if(res==false)
                {
                    myform.comp.value="";
                    myform.comp.focus();
                    return false;
                }
            }
            if(compare())
                return true;
            else
                return false;
        }



        function compare()
        {
            var img=document.EAMG_GroupCreationByWzActionForm.importimagefile.value;
            if(img=='')
            {
                alert("Image File can not be Blank.");
                return false;
            }
            else
            {
               <%-- if(img.indexOf(':\\') ==-1 || img.indexOf(':\\')==0)
                {
                    alert('Invalid Image File path. Please Attach Valid .jpg Extension File.');
                    return false;
                }--%>
                var dot_index=img.lastIndexOf(".");
                var slash_index=img.lastIndexOf("\\");
                var name=img.substring(dot_index+1);
                name=name.toLowerCase();
                if(name!="jpg")
                {
                    alert("Please Select Valid .jpg Extension File.");
                    return false;
                }
                var temp_group_no=img.substring(slash_index+1,dot_index);
                temp_group_no=temp_group_no.toLowerCase();
                var group_number=document.EAMG_GroupCreationByWzActionForm.groupname.value;
                group_number=TrimAll(group_number);
                group_number=group_number.toLowerCase();

                if(group_number!=temp_group_no)
                {
                    alert("<%=PageTemplate.GROUP%> Image Name must match the <%=PageTemplate.GROUP%> Number.");
                    document.EAMG_GroupCreationByWzActionForm.importimagefile.value="";
                    return false;
                }
                return true;
            }
        }


        function compareSvgFile()
        {
            var importSvgFile=document.EAMG_GroupCreationByWzActionForm.importSvgFile.value;
            if(importSvgFile=='')
            {
                alert("SVG File can not be Blank.");
                return false;
            }
            else
            {
                <%--if(importSvgFile.indexOf(':\\') ==-1 || importSvgFile.indexOf(':\\')==0)
                {
                    alert('Invalid Image File path. Please Attach Valid .jpg Extension File.');
                    return false;
                }--%>
                var dot_index=importSvgFile.lastIndexOf(".");
                var slash_index=importSvgFile.lastIndexOf("\\");
                var name=importSvgFile.substring(dot_index+1);
                name=name.toLowerCase();
                if(name!="svg")
                {
                    alert("Please Select Valid .svg Extension File.");
                    return false;
                }
                var temp_group_no=importSvgFile.substring(slash_index+1,dot_index);
                temp_group_no=temp_group_no.toLowerCase();
                var group_number=document.EAMG_GroupCreationByWzActionForm.groupname.value;
                group_number=TrimAll(group_number);
                group_number=group_number.toLowerCase();

                if(group_number!=temp_group_no)
                {
                    alert("<%=PageTemplate.GROUP%> SVG File Name must match the <%=PageTemplate.GROUP%> Number.");
                    document.EAMG_GroupCreationByWzActionForm.importSvgFile.value="";
                    return false;
                }
                return true;
            }
        }

        function CancelProcess()
        {
           // var cncl=confirm("Do You Really want to Cancel the Current Process ?");
            //if(cncl==true)
            {
                parent.content.location="<%=context%>/authJSP/EAMG_Group/EAMG_Create_Group.jsp";
            }

        }

    </script>

</head>

<body>
    <%
                String tdData = "MANAGE " + PageTemplate.GROUP.toUpperCase() + " >> ADD  NEW " + PageTemplate.GROUP.toUpperCase();
                object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
    %>
    <%
                heading = "ADD NEW " + PageTemplate.GROUP.toUpperCase() + "";
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
                            <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=PageTemplate.GROUP%>>> Add New <%=PageTemplate.GROUP%></b></td>
                        </tr>
                    </table></td>
            </tr>--%>
            <tr>
                <td align="right" class="small"><b>Step 2 OF 3</b></td>
            </tr>
            <%--<tr>
                <td>&nbsp;</td>
            </tr>
             <tr>
                <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                        <%--<tr>
                            <td height="25" align="left" class="blue"><b class="heading">&nbsp;ADD NEW <%=PageTemplate.GROUP.toUpperCase()%></b></td>
                        </tr>--%>
                        <tr>
                            <td align="center" valign="top" bgcolor="#FFFFFF">
                                <html:form styleId="EAMG_EAMG_GroupCreationByWzActionForm" method="post" enctype="multipart/form-data" action="/EAMG_GroupCreationByWzAction.do" onsubmit="return validate(this);">
                                    <br/>
                                    <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                        <tr>
                                            <td colspan="2"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                                                    <tr>
                                                        <td height="25" align="left" class="blue"><b class="heading">&nbsp;Enter  <%=PageTemplate.GROUP%> Details </b></td>
                                                    </tr>
                                                </table></td>
                                        </tr>
                                        <tr>
                                            <td width="40%" height="25" align="left" class="text">&nbsp;<%=PageTemplate.GROUP%> Number</td>
                                            <td width="60%" align="left" class="text"><label>
                                                    <html:text property="groupname" styleId="groupno" value=""/>
                                                </label></td>
                                        </tr>
                                        <tr>
                                            <td height="25" align="left" class="text">&nbsp;<%=PageTemplate.GROUP%> Description</td>
                                            <td height="25" align="left" class="text"><label>
                                                    <html:text  property="groupdesc"/>
                                                </label></td>
                                        </tr>
                                        <tr>
                                            <td height="25" align="left" class="text">&nbsp;Browse Image File
                                                <label></label></td>
                                            <td height="25" align="left" class="text"><input type="file" style="width:240px" size="40" id="Import File" name="importimagefile" value='' accept="text/*"/>
                                                <span class="red-for-image"><em>Image Size: 105 x 105 (jpg)</em></span></td>
                                        </tr>
                                        <tr>
                                            <td height="25" align="left" class="text">&nbsp;Browse SVG File
                                                <label></label></td>
                                            <td height="25" align="left" class="text"><input type="file" style="width:240px" size="40" id="ImportSvgFile" name="importSvgFile" value='' accept="text/*"/>
                                                <span class="red-for-image"><em>Upload only (Svg) File</em></span></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">&nbsp;</td>
                                        </tr>

                                        <tr>
                                            <td colspan="2"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                                                    <tr>
                                                        <td height="25" align="left" class="blue"><b class="heading">&nbsp;Enter Number of Component  </b></td>
                                                    </tr>
                                                </table></td>
                                        </tr>
                                        <tr>
                                            <td height="25" align="left" class="text">&nbsp;No. of Component</td>
                                            <td height="25" align="left" class="text"><label>
                                                    <html:text property="comp"/>
                                                </label></td>
                                        </tr>
                                        <tr>
                                            <td colspan="2">
                                                <table width="100%">
                                                    <tr>
                                                        <td width="50%" align="right">
                                                            <input type="submit"  name="Next" id="Next" value="Next" style="width:70px;" />
                                                        </td>
                                                        <td width="50%" align="left">
                                                            <input type="button"  name="Cancel" value="Cancel" onclick="return CancelProcess();" />
                                                        </td>
                                                    </tr>
                                                </table>
                                            </td>
                                        </tr>

                                    </table>
                                </html:form></td>
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
