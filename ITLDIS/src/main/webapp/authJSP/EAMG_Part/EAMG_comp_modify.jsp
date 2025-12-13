<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="viewEcat.comEcat.ConnectionHolder,java.sql.Connection,java.io.*,com.EAMG.common.*,EAMG.PartDAO_CUD.*" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%
            response.setHeader("Cache-Control", "no-cache"); //HTTP 1.1
            response.setHeader("Pragma", "no-cache"); //HTTP 1.0
            response.setDateHeader("Expires", 0); //prevents caching at the proxy server
            Connection conn = null;
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            String context = request.getContextPath();
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
				String contextPath = request.getContextPath();
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
            String iscomppresent = "";
            try {
                conn = holder.getConnection();

                ResultSet rs;
                Statement stmt = null;
                stmt = conn.createStatement();
                String query = "Select * from CAT_Part where part_type='PRT'";
                rs = stmt.executeQuery(query);
                if (rs.next()) {
                    iscomppresent = "present";
                }
                rs.close();
                stmt.close();

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                //conn.close();
            }%>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>


<html:html >
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript" src="<%=imagesURL%>js/suggestions.js"></script>
        <script type="text/javascript" src="<%=imagesURL%>js/autosuggest.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=imagesURL%>js/autosuggest.css" />
        <script type="text/javascript">
            function validate(myform)
            {
                // var type=document.getElementById("comp_type").options[ind].value;
                //var type_txt=document.getElementById("comp_type").options[ind].text;
                var myform=myform;
                if(document.myform.radio2.checked==true)
                {
                    var xlsfile=document.myform.file.value;
                    if(xlsfile=="")
                    {
                        alert('Please Browse excel file to modify Part.');
                        return false;
                    }
                    else
                    {
                        if(xlsfile.indexOf(':\\') ==-1 || xlsfile.indexOf(':\\')==0)
                        {
                            alert('Invalid Excel File. Please Browse only .xls file.');
                            return false;
                        }
                        var pos2=xlsfile.lastIndexOf(".");
                        var name=xlsfile.substring(pos2+1);
                        if(name=="xls")
                        {
                            document.myform.modify_option.value="excel";

                        }
                        else
                        {
                            alert("Invalid Excel File. Please Browse only .xls file.");
                            return false;
                        }
                    }
                }
                else
                {
                    var compno=document.getElementById('comp_param_list').value;
                    if(compno=='')
                    {
                        alert('Please Enter the Part Number.');
                        document.getElementById('comp_param_list').focus();
                        return false;
                    }
                    else
                    {
                        document.myform.modify_option.value="wizard";
                    }
                    

                }
            }
            function enableExcelBrowse()
            {

                document.myform.file.disabled=false;
                document.myform.radio1.checked=false;
                document.myform.radio2.checked=true;
            }
            function disableExcelBrowse()
            {

                document.myform.file.disabled=true;
                document.myform.radio1.checked=true;
                document.myform.radio2.checked=false;
            }
            function create_template()
            {
                var type=document.getElementById("comp_type").value;
                var url="EAMG_create_template_with_data.jsp?comp_type="+type+"";
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
                var cncl=confirm("Do You Really want to Cancel the Current Process ?");
                if(cncl==true)
                    parent.content.location="<%=context%>/common/EAMG_home_page.jsp";

            }


        </script>
    </head>
    <body>
        <%
                    String tdData = "MANAGE PART >> MODIFY PART";
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "MODIFY PART";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">
            <%
                        if (iscomppresent.equals("")) {
            %>

           <table width="700" border="0" cellspacing="1" cellpadding="1">
                   <%-- <tr>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                <tr>
                                    <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Part >> Modify Part</b></td>
                                </tr>
                            </table></td>
                    </tr>
                    <tr>
                        <td align="right" class="small">&nbsp;</td>
                    </tr>


                    <tr>
                        <td valign="top">
                            <table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCCC">
                                <tr>
                                    <td height="25" align="left" class="blue"><b class="heading">&nbsp;MODIFY PART</b></td>
                                </tr>
--%>
                        <tr>
                            <td valign="top" class="red" height="30" align="center">No Part exists in Database.</td>
                    <%--</tr>

					 
                      </table>

                     </td>--%>
                    </tr>

                </table>




                <%} else {%>

                <table width="700" border="0" cellspacing="1" cellpadding="1">
                    <%--<tr>
                        <td>&nbsp;</td>
                    </tr>
                    <tr>
                        <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                <tr>
                                    <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage Part >> Modify Part</b></td>
                                </tr>
                            </table></td>
                    </tr>
                    <tr>
                        <td align="right" class="small">&nbsp;</td>
                    </tr>

                    
                    <tr>
                        <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCCC">
                                <tr>
                                    <td height="25" align="left" class="blue"><b class="heading">&nbsp;MODIFY PART</b></td>
                                </tr>--%>
                                <tr>
                                    <td align="center" valign="top" bgcolor="#FFFFFF">
                                        <form name="myform" method="post" enctype="multipart/form-data"  action="<%=context%>/EAMG_modify_comp_by_excel.do" onsubmit="return validate(this);">
                                            <br />
                                            <input type="hidden" name="modify_option" value="wizard"/>
                                            <input type="hidden" value="PRT" name="comp_type" id="comp_type"/>
                                            <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                                <tr>
                                                    <td colspan="3"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                                            <tr>
                                                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;Select Criteria</b></td>
                                                            </tr>
                                                        </table></td>
                                                </tr>
                                                <tr>
                                                    <td  width="6%" align="center" class="text"><label>
                                                            <input type="radio" checked name="radio1" value="" onClick="disableExcelBrowse();" />
                                                        </label></td>
                                                    <td  width="34%" align="left" class="text">&nbsp;Part Number </td>
                                                    <td  valign="middle" width="60%" align="left" class="text"><input type="text" id="comp_param_list" name='comp_param_list' style='width:200px;height:20px;'  />&nbsp;
                                                        <a href='#'><img src='<%=imagesURL%>/arrdown.gif' border='0' alt='Get Suggestions' style='width:15px' onclick='getSuggestions("comp_param_list",document.myform.comp_param_list,document.myform.comp_type,document.getElementById("img"));'/><img alt=""  id='img' style='visibility:hidden;' border='0' src='<%=imagesURL%>/load.gif'/>
                                                        </a>

                                                    </td>
                                                </tr>
                                                <tr>
                                                    <td height="25" align="center" class="text"><label>
                                                            <input type="radio" name="radio2" onClick="enableExcelBrowse();"/>
                                                        </label></td>
                                                    <td height="25" align="left" class="text">&nbsp;Browse Input File</td>
                                                    <td height="25" align="left" class="text"><input type="file" name="file" value="" disabled="disabled" style="width:268px"/></td>
                                                </tr>

                                                <tr>
                                                    <td class="text" ></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3" align="right"><em><a href="#" onclick="javascript:create_template();"  class="red-for-temmplate-link">* Click Here To Download Template with data</a><span class="red-for-temmplate">(xls).</span></em></td>
                                                </tr>
                                                <tr>
                                                    <td colspan="3">
                                                        <table width="100%">
                                                            <tr>

                                                                <td width="50%" align="right">
                                                                    <input type="submit" name="Next" id="Next" value="Next" style="width:70px;"/>
                                                                </td>
                                                                <td width="50%" align="left">
                                                                    <%--<input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />--%>
                                                                </td>
                                                            </tr>
                                                        </table>
                                                    </td>
                                                </tr>

                                            </table>
                                        </form></td>
                                </tr>
                           <%-- </table>
        </td>
                    </tr>
--%>
                </table>

                <%}%>
        </div>
        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html:html >

