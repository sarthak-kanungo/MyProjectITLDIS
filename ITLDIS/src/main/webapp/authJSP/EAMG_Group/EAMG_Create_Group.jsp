<%-- 
    Document   : EAMG_Create_Group

--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>

<html>
    <%
                response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                response.setHeader("Expires", "0");
                response.setHeader("Pragma", "no-cache");
                String context = request.getContextPath();
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
                    object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                    return;
                }
                String heading = null;
                int width = 659;
                int height = 84;
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />

        <script type="text/javascript" language="javascript">
            var flag=true;

            function check_method_select()
            {
                if (document.form1.type2.checked==true)
                    document.form1.action="<%=context%>/authJSP/EAMG_Group/EAMG_group_creation_wz.jsp";
                else if(document.form1.type3.checked==true)
                {
                    var comp=compare();
                    if(comp==true)
                    {
                        document.form1.action="<%=context%>/EAMG_create_group1.do";
                    }
                    else
                    {
                        return false;
                    }
                }

            }
            function compare()
            {
                var img=document.form1.file.value;
                if(img=="")
                {
                    alert("Please Browse Excel file.");
                    flag=false;
                }
                else
                {
                    if(img.indexOf(':\\') ==-1 || img.indexOf(':\\')==0)
                    {
                        alert('Invalid Excel File. Please Browse only Excel file.');
                        return false;
                    }
                    var pos2=img.lastIndexOf(".");
                    var name=img.substring(pos2+1);
                    if(name=="xls")
                    {
                        flag=true;

                    }
                    else
                    {
                        alert("Invalid Excel File. Please Browse only Excel file.");
                        flag=false;

                    }

                }

                return flag;

            }


            function enableNonProe()
            {
                document.getElementById('nonpro').style.visibility='visible';
                document.form1.proe.checked=false;
                //document.form1.type2.checked=false;
            }
            function enableProE()
            {
                document.form1.file.disabled=true;
                document.form1.type2.checked=false;
                document.form1.type3.checked=false;
            }
            function enableExcelBrowse()
            {

                document.form1.file.disabled=false;
                document.form1.type2.checked=false;
            }
            function grpWiz()
            {
                document.form1.file.disabled=true;
                document.form1.type3.checked=false;
            }

            function create_template()
            {
                  
                var url='authJSP/EAMG_Group/EAMG_create_template_save.jsp?comp_type=GRP';
                //alert(url);
                if(document.getElementById("downloadDiv") == null)
                {
                    downloadDiv = document.createElement("div");
                    document.getElementsByTagName("body")[0].appendChild(downloadDiv);
                    downloadDiv.style.width = "0px";
                    downloadDiv.style.height = "0px";
                    downloadDiv.id = "downloadDiv";
                }
                else
                    downloadDiv = document.getElementById("downloadDiv");
                if(document.getElementById("downloadFrame") == null)
                {
                    downloadFrame = document.createElement("iframe");
                    downloadFrame.id = "downloadFrame";
                    downloadFrame.src = url;
                    downloadFrame.scrolling = "no";
                    downloadFrame.style.width = "0px";
                    downloadFrame.style.height = "0px";
                    downloadDiv.innerHTML = downloadFrame.outerHTML;
                }
                else
                {
                    downloadFrame = document.getElementById("downloadFrame");
                    downloadFrame.src = url;
                }
                //alert(downloadFrame.src);
            }

            function CancelProcess()
            {
                var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                if(cncl==true)
                {
                    parent.content.location="<%=context%>/common/EAMG_home_page.jsp";
                }

            }



        </script>
    </head>
    <%
                String tdData ="MANAGE "+ PageTemplate.GROUP.toUpperCase()+" >> ADD  NEW "+PageTemplate.GROUP.toUpperCase();
                object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
    %>
    <%
                heading = "ADD NEW " + PageTemplate.GROUP.toUpperCase() + "";
                out.println(object_pageTemplate.tableHeader(heading, width, height));
    %>
    <body>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%--<tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=PageTemplate.GROUP%> >> Add  New <%=PageTemplate.GROUP%></b></td>
                            </tr>
                        </table></td>
                </tr>--%>
                <tr>
                    <td align="right" class="small"><b>Step 1 OF 3</b></td>
                </tr>

                <%--<tr>
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;ADD NEW <%=PageTemplate.GROUP.toUpperCase()%></b></td>
                            </tr>--%>
                            <tr>
                                <td align="center" valign="top" bgcolor="#FFFFFF">
                                    <form  method="post" ENCTYPE="multipart/form-data" name="form1" action="">

                                        <br />
                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                                <td colspan="3"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#cccccc">
                                                        <tr>
                                                            <td height="25" align="left" class="blue"><b class="heading">&nbsp;Select Criteria</b></td>
                                                        </tr>
                                                    </table></td>
                                            </tr>

                                            <tr>
                                                <td height="25" align="center" class="text"><label>
                                                        <input type="radio" checked name="type2" value="" onClick="grpWiz();" />
                                                    </label></td>
                                                <td height="25" align="left" class="text">&nbsp;Wizard</td>
                                                <td height="25" align="left" class="text"><label></label></td>
                                            </tr>
                                            <tr>
                                                <td height="25" align="center" class="text">
                                                    <label>
                                                        <input type="radio" name="type3"  onClick="enableExcelBrowse();"/>
                                                    </label></td>

                                                <td height="25" align="left" class="text">&nbsp;Browse Input File</td>
                                                <td height="25" align="left" class="text"><input type="file" name="file" value="" style="width:275px"/></td>
                                            </tr>

                                            <tr>
                                                <td colspan="3" align="right"><em><a href="#" onclick="javascript:create_template();" class="red-for-temmplate-link">* Click Here To Download Template </a><span class="red-for-temmplate">(xls).</span></em></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3" align="center"><label>

                                                        <input type="submit" name="Next" id="Next" value="Next" style="width:70px;" onclick="return check_method_select();"/>
                                                        <%--<input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />--%>
                                                    </label></td>
                                            </tr>

                                        </table>
                                    </form></td>
                            </tr>
                        <%--</table></td>
                </tr>--%>
            </table>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
