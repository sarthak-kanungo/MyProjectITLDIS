<%-- 
    Document   : amw_AMW_PART_OME_PART_CREATE
    Created on : Nov 11, 2011, 12:21:20 PM
    Author     : avinash.pandey
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="amw.utility.*,amw.com.LoginAction.*"%>
<%@ page import="dbConnection.*,java.sql.Connection,java.io.*,amw_com.EAMG.common.*" %>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>
<html >
    <head>
        <%
            String contextPath = request.getContextPath();
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
        %>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title>AMW-Ecatalogue</title>


        <script type="text/javascript" src="<%=contextPath%>/js/validations.js"></script>
        <link href="<%=contextPath%>/css/config.css" rel="stylesheet" type="text/css" />
        <script language="javascript">
            
        
            function validateExcel()
            {
                var filePath= document.getElementById("excelFile").value;
                if(filePath == '')
                {
                    alert('Please Browse Excel File.');
                    return false;
                }
                else if(filePath.indexOf(':\\') ==-1 || filePath.indexOf(':\\')==0)
                {
                    alert('Invalid Excel File. Please Browse only Excel file.');
                    return false;
                }
                else if(filePath.indexOf('.xls') != (filePath.length-4))
                {
                    alert('Invalid Excel File. Please Browse only Excel file.');
                    return false;
                }
                return true;
            }
            
            function create_template()
            {
                var type=document.getElementById("comp_type").value;
                var url="amw_create_template_save.jsp?comp_type="+type+"";
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
            function check_method_select()
            {        
                document.form1.action="<%=contextPath%>/amw_CreateAMWPart_OMEPart.do";
                return true;
            }   
            function CancelProcess()
            {
                //var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                //if(cncl==true)
                {
                    parent.right.location="<%=contextPath%>/common/amw_home_page.jsp";
                }
	
            }


        </script>

    </head>
    <body >
        <form name="form1" onsubmit="return check_method_select();" ENCTYPE="multipart/form-data" method="post">
            <input type="hidden" value="AMW_PART_OME_PART" name="comp_type" id="comp_type"/>
            <div align="center">
                <table width="700" border="0" cellspacing="1" cellpadding="1">
                    <tr>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                                <tr>
                                    <td height="25" align="left" class="Lightblue"><b class="path">&nbsp;Manage AMW Part And OME Part >> Add AMW Part And OME Part</b></td>
                                </tr>
                            </table></td>
                    </tr>
                    <tr>
                        <td>&nbsp;</td>
                    </tr>

                    <tr>
                        <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#333333">
                                <tr>
                                    <td height="25" align="left" class="Lightblue"><b class="heading">&nbsp;Add AMW Part And OME Part</b></td>
                                </tr>
                                <tr>
                                    <td align="center" valign="top" bgcolor="#FFFFFF">

                                        <br />
                                        <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                            <tr>
                                                <td height="25" align="center" class="text">&nbsp;</td>
                                                <td height="25" align="left" class="text">&nbsp;Browse Input File</td>
                                                <td height="25" align="left" class="text">
                                                    <input type="file" type=file id="excelFile" name=excelFile value='' size=20 /></td>
                                            </tr>

                                            <tr>
                                                <td class="text" ></td>
                                            </tr>
                                            <tr>
                                                <td colspan="3" align="right"><em><a href="#" onclick="javascript:create_template();"  class="red-for-temmplate-link">* Click Here To Download Template</a><span class="red-for-temmplate">(xls).</span></em></td>
                                            </tr>

                                            <tr>
                                                <td colspan="3" align="center"><label>

                                                        <input type="submit" name="Next" id="Next" value="Next" style="width:70px;" onclick="return validateExcel();"/>
                                                        <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />
                                                    </label></td>
                                            </tr> 
                                        </table>
                                    </td>
                                </tr>
                            </table></td>
                    </tr>
                </table>
            </div>
        </form>
    </body>
</html>
