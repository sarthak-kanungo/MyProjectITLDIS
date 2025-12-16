

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>

<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%> 
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
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
            imagesURL = object_pageTemplate.imagesURL();
            String Group = object_pageTemplate.GROUP;

            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }
            String heading = null;
            int width = 559;
            int height = 84;


        %>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=UtilityMapkeys1.tile%></title>
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />


        <script>


            function GetXmlHttpObject()
            {
                var objXmlHttp = null;
                if (navigator.userAgent.indexOf('Opera') >= 0)
                {
                    xmlHttp = new XMLHttpRequest();
                    return xmlHttp;
                }
                if (navigator.userAgent.indexOf('MSIE') >= 0)
                {
                    var strName = 'Msxml2.XMLHTTP';
                    if (navigator.appVersion.indexOf('MSIE 5.5') >= 0)
                    {
                        strName = 'Microsoft.XMLHTTP';
                    }
                    try
                    {
                        objXmlHttp = new ActiveXObject(strName);
                        // objXmlHttp.onreadystatechange = handler;
                        return objXmlHttp;
                    }
                    catch (e)
                    {
                        alert('Your Browser Does Not Support Ajax');
                        return;
                    }
                }
                if (navigator.userAgent.indexOf('Mozilla') >= 0)
                {
                    objXmlHttp = new XMLHttpRequest();
                    return objXmlHttp;
                }
            }

            function up(obj)
            {
                var current;
                var reverse;
                var current_val;
                var reverse_val;

                if (obj.selectedIndex == -1) {
                    alert("You must select at least one!");
                }
                else {
                    if (obj.options[obj.options.selectedIndex].index > 0)
                    {
                        current = obj.options[obj.options.selectedIndex].text;
                        reverse = obj.options[obj.options[obj.options.selectedIndex].index - 1].text;
                        current_val = obj.options[obj.options.selectedIndex].value;
                        reverse_val = obj.options[obj.options[obj.options.selectedIndex].index - 1].value;

                        obj.options[obj.options.selectedIndex].text = reverse;
                        obj.options[obj.options[obj.options.selectedIndex].index - 1].text = current;

                        obj.options[obj.options.selectedIndex].value = reverse_val;
                        obj.options[obj.options[obj.options.selectedIndex].index - 1].value = current_val;
                        self.focus();
                        obj.options.selectedIndex--;
                    }
                }
            }

            function down(obj)
            {
                var current;
                var next;
                var current_val;
                var next_val;

                if (obj.selectedIndex == -1) {
                    alert("You must select at least one!");
                }
                else {
                    if (obj.options[obj.options.selectedIndex].index != obj.length - 1)
                    {
                        current = obj.options[obj.options.selectedIndex].text;
                        next = obj.options[obj.options[obj.options.selectedIndex].index + 1].text;

                        current_val = obj.options[obj.options.selectedIndex].value;
                        next_val = obj.options[obj.options[obj.options.selectedIndex].index + 1].value;

                        obj.options[obj.options.selectedIndex].text = next;
                        obj.options[obj.options[obj.options.selectedIndex].index + 1].text = current;

                        obj.options[obj.options.selectedIndex].value = next_val;
                        obj.options[obj.options[obj.options.selectedIndex].index + 1].value = current_val;

                        self.focus();
                        obj.options.selectedIndex++;
                    }
                }
            }

            function showEngineModel(disObject)
            {
                var selectValue = document.getElementById('groupname').value;
                if (selectValue =='')
                {
                     //document.getElementById('childDivId').display = 'none';
                     document.getElementById('childDivId').innerHTML = '';
                }
                var strURL = "<%=contextPath%>/authJSP/EAMG_Group/ChangeGroupSequenceAjax.jsp?flag=Group&selectValue=" + selectValue;
                //alert(strURL);
              
               xmlHttp = GetXmlHttpObject();
                xmlHttp.onreadystatechange = function()
                {
                    stateChangedSeqOrder1();
                };
                xmlHttp.open("POST", strURL, true);
                xmlHttp.send(null);
            }

           


            function stateChangedSeqOrder1()
            {
                if (xmlHttp.readyState == 4 || xmlHttp.readyState == "complete")
                {
                    document.getElementById('childDivId').innerHTML = '';
                    res = xmlHttp.responseText;
                    document.getElementById('childDivId').innerHTML = res;

                }
            }

           

            function validateSeq1()
            {

                var len = document.getElementById("assign_Code").options.length;
                if (len == 0)
                {
                   // alert("No <%=Group%> exists.");
                    alert("No component exists.");
                    return false;
                }

                for (var i = 0; i < document.getElementById("assign_Code").options.length; i++) {
                    document.getElementById("assign_Code").options[i].selected = true;
                }
            }



        </script>
        <script></script>
    </head>

    <body>
        <form  method="post" action="<%=contextPath%>/ChangeGroupSequence.do" onsubmit="return validateSeq1()">
            <%
                heading = "CHANGE " + PageTemplate.GROUP.toUpperCase() + " BOM SEQUENCE";
                out.println(object_pageTemplate.tableHeader(heading, width, height));
            %>

            <input type="hidden" name="option" value="updateGroupSequence">
            <div align="center">
                <table width="<%=width%>"    border=0 cellspacing=0 cellpadding=0> <tr><td align="center" style="padding-top: 25px;padding-left: 100px;">
                    <tr><td>
                            <table width="<%=width%>"    border=0 cellspacing=0 cellpadding=0>
                                <tr>
                                    <td height="25" width="100px;" align="left" bgcolor="#FFFFFF" class="text" style="padding-left: 40px;"> <Strong>&nbsp;Select <%=Group%></Strong></td>
                                    <td height="25"  align="left" bgcolor="#FFFFFF" class="text">
                                        <select name="groupname"   id="groupname" class="text" onchange="showEngineModel(this);">
                                            <option value="">--Select Here--</option>
                                            <logic:iterate name="groupSeriesList" id="engineTemp">
                                                <option value="${engineTemp}">${engineTemp}</option>
                                            </logic:iterate>
                                        </select>
                                    </td>
                                </tr>
                                 <%-- <tr>
                                    <td height="25" width="100px;" align="left" bgcolor="#FFFFFF" class="text" style="padding-left: 40px;"><Strong> &nbsp;<%=engineModel%></strong></td>
                                    <td ><span id="statusDivId"  >

                                            <select name="engineModel"  id="engineModel" class="text" >

                                                <option value="">--Select Here--</option>

                                            </select>

                                        </span></td>
                                </tr>--%>
                               </table>
                        </td></tr>
            


                    <tr height=20 bgcolor=#ffffff>
                        <td colspan="2" class="text" align=left>
                            <div id="childDivId">

                            </div>
                        </td>

                    </tr>
                 <%--   <tr height=20 bgcolor=#ffffff >
                        <td colspan="2" align="left" bgcolor="#FFFFFF" class="text">
                            <table>
                                <tr>
                                    <td width="5%"> &nbsp;
                                    </td>

                                    <td class="mandatory" nowrap>


                                    </td>
                                </tr>
                                <tr>
                                    <td width="5%"> &nbsp;
                                    </td>
                                    <td class="mandatory" align="left" nowrap>
                                        <font color="red"></font>
                                    </td>
                                </tr>
                            </table>
                        </td>
                    </tr>--%>
                   </table>
             
                



               <%-- <br /><div align="left"><span class=mandatory >&nbsp;&nbsp;&nbsp;&nbsp;
                        * Mandatory Field</span></div>
            </div>--%>

            <%
                out.println(object_pageTemplate.tableFooter());
            %>  
        </form>
    </body>
</html>
