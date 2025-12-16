<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="java.sql.*,viewEcat.comEcat.ConnectionHolder" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<html>
    <%
                String contextPath = request.getContextPath();
                response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                response.setHeader("Expires", "0");
                response.setHeader("Pragma", "no-cache");
                session.removeAttribute("ECNImplementation");
                session.setAttribute("comp_type", "PRT");
                //System.out.println("set comp_type in session:PRT");
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


        <script language="javascript">
            function check_method_select()
            {        
                if(document.form1.radio4.checked==true)
                {
            
                    document.form1.action="<%=contextPath%>/EAMG_PartAction.do";
                }
                else
                {
                    if(document.form1.radio3.checked==true)           
                        document.form1.action="<%=contextPath%>/add_part.do";
               
                }
                return true;
            }      
            
            
            
            
            function validateExcel()
            {
        
                if(document.form1.radio3.checked==true)
                {
                    var filePath= document.getElementById("excelFile").value;
                    if(filePath == '')
                    {
                        alert('Please Browse Excel  File.');
                        return false;
                    }
                    <%--else if(filePath.indexOf(':\\') ==-1 || filePath.indexOf(':\\')==0)
                    {
                        alert('Invalid Excel File. Please Browse only .xls file.');
                        return false;
                    }--%>
                    else if(filePath.indexOf('.xls') != (filePath.length-4))
                    {
                        alert('Invalid Excel File. Please Browse only .xls file.');
                        return false;
                    }
                    return true;
                }
            }
        
            function create_template()
            {
        
                var url='EAMG_create_template_save.jsp?comp_type=PRT';
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
            function enableExcelBrowse()
            {
                document.form1.radio4.checked=false;
                document.form1.excelFile.disabled=false;
            }
         
            function partWiz()
            {
                document.form1.radio3.checked=false;
                document.form1.excelFile.disabled=true;
        
        
            }
     
            function CancelProcess()
            {
                var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                if(cncl==true)
                    parent.content.location="<%=contextPath%>/common/EAMG_home_page.jsp";

            }

        </script>

    </head>
    <body>
        <%
                    String tdData = "MANAGE PART >> ADD NEW PART";
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "ADD NEW PART";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center" width="700">
            <table width="700" border="0" cellspacing="1" cellpadding="1">                                        
                <tr>
                    <td align="center" valign="top" bgcolor="#FFFFFF">
                        <form name="form1" onsubmit="return check_method_select();" ENCTYPE="multipart/form-data" method="post">
                            <br />
                            <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                <tr>
                                    <td colspan="3"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                            <tr>
                                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;Select Criteria</b></td>
                                            </tr>
                                        </table></td>
                                </tr>
                                <tr>
                                    <td width="6%" height="25" align="center" class="text"><label>
                                            <input type="radio" checked name="radio4" id="radio4" value="radio4" onclick="partWiz();"/> </label>
                                    </td>
                                    <td width="19%" height="25" align="left" class="text">&nbsp;Wizard</td>
                                    <td width="75%" height="25" align="left" class="text"></td>
                                </tr>
                                <tr>
                                    <td width="6%" height="25" align="center" class="text"><label>
                                            <input type="radio" name="radio3" id="radio3" value="radio3" onclick="enableExcelBrowse();" />
                                        </label></td>
                                    <td width="29%" height="25" align="left" class="text">&nbsp;Browse Input File</td>
                                    <td width="65%" height="25" align="left" class="text"><input type=file id="excelFile" name=excelFile value='' size=20 style="width:275px" disabled ></td>
                                </tr>


                                <tr>
                                    <td colspan="3" align="right"><em><a href="#" onclick="javascript:create_template();"  class="red-for-temmplate-link">* Click Here To Download Template </a><span class="red-for-temmplate">(xls).</span></em></td>
                                </tr>
                                <tr>
                                    <td colspan="3" align="center"><label>

                                            <input type="submit" name="Next" id="Next" value="Next" style="width:70px;" onclick="return validateExcel();"/>
                                            <%--<input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />--%>
                                        </label></td>
                                </tr>

                            </table>
                        </form>
                    </td>
                </tr>

            </table>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>

