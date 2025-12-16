<%-- 
    Document   : amw_AddVehicle_Variant
    Created on : Nov 11, 2011, 4:26:37 PM
    Author     : manish.kishore
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>

<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>AMW-Ecatalogue</title>
        <link href="/AMW-AuthEcat/css/config.css" rel="stylesheet" type="text/css" />
        <%
                    response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                    response.setHeader("Expires", "0");
                    response.setHeader("Pragma", "no-cache");
                    String contextPath = request.getContextPath();
                    ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
                    String server_name = (String) session.getValue("server_name");
                    String ecatPath = (String) session.getValue("ecatPATH");
                    String mainURL = (String) session.getValue("mainURL");
                    PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
                    String servletURL = "";
                    servletURL = object_pageTemplate.servletURL();
                    String imagesURL = "";
                    imagesURL = object_pageTemplate.imagesURL();
                    String heading = null;
                    int width = 659;
                    int height = 84;
        %>
        <script type="text/javascript" language="javascript">
            var flag=true;

            function compare()
            {
                var img=document.form1.importexcelfile.value;
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
            function create_template()
            {
                  
                var url='<%=contextPath%>/authJSP/amw_Group/amw_create_template_save.jsp?comp_type=vehicle';
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
            }
            function CancelProcess()
            {
                //var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                //if(cncl==true)
                {
                    parent.right.location="/AMW-AuthEcat/common/amw_home_page.jsp";
                }

            }

        </script>
    </head>
    <body>
        <%
                    String tdData ="MANAGE VARIANT >> ADD  VEHICLE";
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "ADD VEHICLE";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <table width="700" border="0" cellspacing="1" cellpadding="1">
                <%--<tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                            <tr>
                                <td height="25" align="left" class="Lightblue"><b class="path">&nbsp;Manage Variant>> Add  Vehicle</b></td>
                            </tr>
                        </table></td>
                </tr>

                <tr>
                    <td>&nbsp;</td>
                </tr>
                <tr>
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                            <tr>
                                <td height="25" align="left" class="Lightblue"><b class="heading">&nbsp;Add  Vehicle</b></td>
                            </tr>--%>
                            <tr>
                                <td align="center" valign="top" bgcolor="#FFFFFF">
                                    <form  method="post" ENCTYPE="multipart/form-data" name="form1" action="/AMW-AuthEcat/amw_addVehicle.do">

                                        <br/>
                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>

                                                <td height="25" align="left" class="text">&nbsp;Browse Input File</td>
                                                <td height="25" align="left" class="text"><input type="file" name="importexcelfile" value=""/></td>
                                            </tr>

                                            <tr>
                                                <td colspan="3" align="right"><em><a href="#" onclick="create_template();" class="red-for-temmplate-link">* Click Here To Download Template </a><span class="red-for-temmplate">(xls).</span></em></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3" align="center"><label>

                                                        <input type="submit" name="Next" id="Next" value="Next" style="width:70px;" onclick="return compare();"/>
                                                        <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />
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
