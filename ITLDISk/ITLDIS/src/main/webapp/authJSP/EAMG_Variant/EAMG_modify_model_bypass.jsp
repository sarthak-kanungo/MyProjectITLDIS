<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%--
The taglib directive below imports the JSTL library. If you uncomment it,
you must also add the JSTL library to the project. The Add Library... action
on Libraries node in Projects view can be used to add the JSTL 1.1 library.
--%>
<%--
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%> 
--%>
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
		 String modelNo = object_pageTemplate.MODEL_NO;
		 String engineModel = object_pageTemplate.ENGINE_MODEL;
		 String group = object_pageTemplate.GROUP;
		 String modelImages = object_pageTemplate.modelImages;

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
<%

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <title><%=UtilityMapkeys1.tile%></title>
        <script type="text/javascript" src="<%=imagesURL%>js/suggestions.js"></script>
        <script type="text/javascript" src="<%=imagesURL%>js/autosuggest.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=imagesURL%>js/autosuggest.css" />
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <link type="text/css" rel="stylesheet" href="<%=imagesURL%>css/dhtmlgoodies_calendar.css" media="screen" />
        <script type="text/javascript" src="<%=imagesURL%>js/dhtmlgoodies_calendar.js"></script>
        <style type="text/css">
            <!--
            body {
                margin-left: 0px;
                margin-top: 0px;
                margin-right: 0px;
                margin-bottom: 0px;
                background-image: url(<%=contextPath%>/backBlue.gif);
            }
            -->
        </style>
		  <script language="javascript" type="text/javascript">
           
				function validate_form()
				{
					
					 var model=document.form1.model_no.value;
					 var objRegExp = /^(\s*)$/g;
					 model=model.replace(objRegExp, '');
					 //alert(model)
					
					 if(model=='')
					 {
						  alert('Please Select the <%=modelNo%> Number.');
						  return false;
					 }
                
					 if(document.form1.model_check[0].checked==true)
					 {
						  document.form1.action='<%=contextPath%>/authJSP/EAMG_Variant/EAMG_modify_model_details.jsp';
					 }
					 else  if(document.form1.model_check[1].checked==true)
					 {
						  document.form1.action='<%=contextPath%>/temp_model.do?'+model;
					 }
					 else  if(document.form1.model_check[2].checked==true)
					 {
						  if(document.getElementById("modelStatusId").value=='0')
						  {
								alert('Please Select the <%=modelNo%> Status.');
								document.getElementById("modelStatusId").focus();
								return false;
						  }
						  document.form1.action='<%=contextPath%>/temp_model.do?'+model;
					 }

					 //alert(document.form1.action);
					 return true;
				}
        
				function CancelProcess()
				{
					 var cncl=confirm("Do You Really want to Cancel the Current Process ?");
					 if(cncl==true)
					 {
						  location.href="<%=contextPath%>/common/EAMG_home_page.jsp";
					 }
	
				}

				function showSatus()
				{
					 //alert(document.getElementById("modelCheckBoxId").checked);
					 if(document.form1.model_check[0].checked==true)
					 {
						  document.getElementById("statusDisId").style.display='none';
					 }else if(document.form1.model_check[1].checked==true)
					 {
						  document.getElementById("statusDisId").style.display='none';
					 }else if(document.form1.model_check[2].checked==true)
					 {
						  document.getElementById("statusDisId").style.display='';
					 }
				}
        </script>

        <script>
            String.prototype.trim = function ()
            {
                return this.replace(/^\s*/, "").replace(/\s*$/, "");
            }

            var http = getHTTPObject(); // We create the XMLHTTPRequest Object
            function getHTTPObject()
            {
                var xmlhttp;
                if (window.XMLHttpRequest)
                {
                    xmlhttp = new XMLHttpRequest();
                }
                else if (window.ActiveXObject)
                {
                    xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
                }
                return xmlhttp;
            }
            function getStatus()
            {
                var model_no = document.getElementById("model_no").value;
                http.open("GET", "EAMG_get_model_status.jsp?model_no="+model_no, true);
                http.onreadystatechange = handleHttpResponse_get;
                http.send(null);
            }

            var http = getHTTPObject();
            function handleHttpResponse_get()
            {
                var counter1 = 0;
                var compare = 0;
                if (http.readyState == 4)
                {
                    if (http.status == 200)
                    {

                        var results = http.responseText.split("~~");
                        var element1 = document.getElementById('modelStatusId');
                        

                        element1.options[0] = new Option("Select","0",false,false) ;
                        compare = results.length;

                        var dis_dtls1;

                        while (counter1+1 < compare)
                        {
                            dis_dtls1= (results[counter1]).split("##");
                            element1.value=dis_dtls1[1];
                            counter1 = counter1 + 1;
                        }
                        if(element1.value=='COMPLETE')
                        {
									 element1.options[1].selected=true;
                        }else if(element1.value=='INCOMPLETE')
                        {
                            element1.options[2].selected=true;
                        }else if(element1.value=='0')
                        {
									 element1.options[0].selected=true;
                        }
                        counter1 = 0;
                        results=null;
                    }
                    else
                    {
                        alert("Data Not Found")
                    }
                }
            }
        </script>
    </head>
    <body><br/>
        <%
					String tdData = "MANAGE " + modelNo.toUpperCase() + " >> MODIFY " + modelNo.toUpperCase();
					object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);

					heading = "MODIFY " + modelNo.toUpperCase();
					out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center">






            <table width="700" border="0" cellspacing="1" cellpadding="1">

                <%--   <tr>
                      <td align="center"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                              <tr>
                                  <td height="25" align="left" class="blue"><b class="path">&nbsp;Manage <%=modelNo%> >> Modify <%=modelNo%></b></td>
                              </tr>
                          </table></td>
                  </tr>
                  <tr>
                      <td align="right" class="small">&nbsp;</td>
                  </tr>--%>

                <%--<tr>
                    <td valign="top"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                            <tr>
                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;MODIFY <%=modelNo.toUpperCase()%></b></td>
                            </tr>--%>
                <tr>
                    <td align="center" valign="top" bgcolor="#FFFFFF">
                        <form  method="post" name="form1" onSubmit="return validate_form();" action="">

                            <br />
                            <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                <tr>
                                    <td width="40%" class="text" align="left">Select <%=modelNo%> Number:</td>
                                    <td width="60%"  class="text" align="left">


													 <input type="text" id="model_no" name='model_no' style='width:200px;height:20px;'  />&nbsp;
													 <a href='#'><img src='<%=imagesURL%>/arrdown.gif' border='0' alt='Get Suggestions' style='width:15px' onclick='getSuggestionsModel("model_no",document.form1.model_no,0,document.getElementById("img"));'/><img alt=""  id='img' style='visibility:hidden;' border='0' src='<%=imagesURL%>/load.gif'/>
													 </a>
												</td>
                                </tr>
                            </table>
                            <table width="95%" border="0" cellspacing="2" cellpadding="2">
                                <tr>
                                    <td colspan="3"><table width="100%" border="1" cellpadding="0" cellspacing="0" bordercolor="#CCCCCC">
                                            <tr>
                                                <td height="25" align="left" class="blue"><b class="heading">&nbsp;Select Modification Option</b></td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="6%" height="25" align="center" class="text"><label>
                                            <input type="radio"  value="model_details" name="model_check" checked onClick="showSatus();"/>
                                        </label></td>
                                    <td width="22%" align="left" class="text">&nbsp;<%=modelNo%> Details</td>
                                    <td width="72%" align="left" class="text"><label></label></td>
                                </tr>
                                <tr>
                                    <td height="25" align="center" class="text"><label>
                                            <input type="radio" value="model_groups" name="model_check" onClick="showSatus();" />
                                        </label></td>
                                    <td height="25" align="left" class="text">&nbsp;<%=modelNo%> <%=group%></td>
                                    <td height="25" align="left" class="text"><label></label></td>
                                </tr>

                                <tr>
                                    <td height="25" align="center" class="text"><label>
                                            <input type="radio" value="model_status" name="model_check" onClick="showSatus();"/>
                                        </label></td>
                                    <td height="25" align="left" class="text">&nbsp;Change <%=modelNo%> Status</td>
                                    <td height="25" align="left" class="text"><label></label></td>
                                </tr>
                                <tr id="statusDisId" style="display:none">

                                    <td colspan="3">
                                        <table width="60%">
                                            <tr>
                                                <td height="25" align="left" class="text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Select <%=modelNo%> Status</td>
                                                <td height="25"  align="center" class="text">
                                                    <select name="modelStatus" id="modelStatusId" class="text">

                                                        <option value="0" selected>--Select--</option>
                                                        <option value="COMPLETE">COMPLETE</option>
                                                        <option value="INCOMPLETE">INCOMPLETE</option>
                                                    </select>
                                                </td>
                                            </tr>
                                        </table>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3">
                                        <table width="100%">
                                            <tr>

                                                <td align="center">
                                                    <input type="submit" name="Next" id="Next" value="Next" style="width:70px;" />
                                                </td>
                                                <td align="left">
                                                    <%--<input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />--%>
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
        <%
					out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
