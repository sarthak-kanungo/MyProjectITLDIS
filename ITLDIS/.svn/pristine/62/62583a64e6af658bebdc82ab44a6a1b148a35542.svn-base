<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%@ page import="viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
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
                String imagesURL = "";
                imagesURL = object_pageTemplate.imagesURL();
                String session_id = session.getId();
                String getSession = (String) session.getValue("session_value");
                if (!session_id.equals(getSession)) {
                    object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                    return;
                }
                String heading = null;
                int width = 600;
                int height = 84;

    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <link type="text/css" rel="stylesheet" href="<%=imagesURL%>css/dhtmlgoodies_calendar.css" media="screen" />
        <script type="text/javascript" src="<%=imagesURL%>js/dhtmlgoodies_calendar.js"></script>
        <script type="text/javascript" src="<%=imagesURL%>js/suggestions.js"></script>
        <script type="text/javascript" src="<%=imagesURL%>js/autosuggest.js"></script>
        <link rel="stylesheet" type="text/css" href="<%=imagesURL%>js/autosuggest.css" />
        <script type="text/javascript" language="javascript">

            function validate()
            {

                var changeType=document.getElementById('Change Type').value;
                var elementArr;

                if(changeType!="Deleted")
                    elementArr=new Array('<%=PageTemplate.MODEL_NO%>','Change Type','Status','Effective Date','Old Part No','New Part No');
                else
                    elementArr=new Array('<%=PageTemplate.MODEL_NO%>','Change Type','Status','Effective Date','Old Part No');

                var strValue=null;
                var strObject=null;


                for(var i=0;i<elementArr.length;i++)
                {
                    var objRegExp = /^(\s*)$/;
                    strObject=document.getElementById(elementArr[i]);
                    strValue =document.getElementById(elementArr[i]).value;
                    strValue.replace(objRegExp, '');
                    if(strValue.length == 0)
                    {
                        if(strObject.tagName=="INPUT")
                        {
                            alert("Please Enter "+elementArr[i]);
                            strObject.focus();
                            return false;
                        }
                        else if(strObject.tagName=="SELECT")
                        {
                            alert("Please select "+elementArr[i]);
                            return false;

                        }
                        return false;
                    }

                }

                var str=document.getElementById('ecnNoId').value;
                var ecn_no =/^[A-Za-z0-9-]{1,15}$/;
                if(str=='')
                {
                    alert("Please Enter ECN No");
                    document.getElementById('ecnNoId').focus();
                    return false;
                }

                if(!ecn_no.test(str)){
                    alert('Please Enter ECN No Alphanumeric Only');
                    document.getElementById('ecnNoId').value='';
                    document.getElementById('ecnNoId').focus();
                    return false;

                }
                
            }

        </script>


    </head>
    <body>
        <%
                    String tdData = "MANAGE ECN >> UPLOAD ECN";
                    object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
                    heading = "IMPLEMENT ECN";
                    out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center" width="600"><font class="red"><html:errors/></font>
            <table width="600" border="0" cellspacing="1" cellpadding="1">
                <tr valign="top">
                    <td align="center" valign="top" bgcolor="#FFFFFF" style="padding-left: 100px;">
                        <form name="form1"  method="post"  onsubmit="return validate()" action="<%=contextPath%>/UploadECN.do"><input type="hidden" value="implement" name="option">
                            <br />
                            <table width="95%" border="0" cellspacing="2" cellpadding="2">

                                <tr>
                                    <td width="40%" height="25" align="left" class="text"><%=PageTemplate.MODEL_NO%></td>
                                    <td width="60%" height="25" align="left" class="text">
                                        <select  name="modelNo"  id="<%=PageTemplate.MODEL_NO%>" class="GroupSelect" style="width:120px;">
                                            <option value="">Select Here</option>
                                            <logic:iterate id="element" name="ecnFormBean" property="modelList">
                                                <option value="${element}"> ${element} </option>
                                            </logic:iterate>
                                        </select>

                                    </td>
                                </tr>
                                <tr>
                                    <td height="25" align="left" class="text">Select Change Type</td>
                                    <td height="25" align="left" class="text">

                                        <select id="Change Type" name="changeType" class="GroupSelect" style="width:120px;">
                                            <option value="">Select Here</option>
                                            <option value="Replaced By">Replaced By</option>
                                            <option value="Deleted">Deleted</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td height="25" align="left" class="text">Select Interchangeability Status</td>
                                    <td height="25" align="left" class="text">

                                        <select id="Status" name="status" class="GroupSelect" style="width:120px;">
                                            <option value="">Select Here</option>
                                             <option value="-">Not Available</option>
                                          <!--  <option value="Interchangeable">Interchangeable</option>
                                            <option value="Replaced By">Replaced By</option>
                                            <option value="Deleted">Deleted</option>
                                          -->
                                           <option value="Yes">Yes</option>
                                           <option value="No">No</option>

                                        </select>

                                    </td>
                                </tr>

                                <tr>
                                    <td height="25" align="left" class="text">Effective Date</td>
                                    <td height="25" align="left" class="text">
                                        <input type="text" name="effectiveDate" value=""   readonly="readonly" id="Effective Date">
                                        <img alt="calendar" id="effectiveDateCalImg" src='<%=imagesURL%>/showCalendar.jpg' width=20 height=20 border=0 onclick="displayCalendar(document.getElementById('Effective Date'),'dd/mm/yyyy',this);" />
                                    </td>
                                </tr>
                                <tr>

                                <tr>
                                <tr>
                                    <td width="29%" height="25" align="left" class="text">Effective TSN</td>
                                    <td width="65%" height="25" align="left" class="text">
                                        <input type="text" name="effectiveTSN" value="">
                                    </td>
                                </tr>

                                <td height="25" align="left" class="text">Old Part No</td>
                                <td height="25" align="left" class="text"><input type="hidden" name="comp_type" value="PRT">

                                    <input type="text" name="oldPartNo" id="Old Part No" value=""><a href='#'><img src='<%=imagesURL%>/arrdown.gif' border='0' alt='Get Suggestions' style='width:15px' onclick='getSuggestions("Old Part No",document.form1.oldPartNo,document.form1.comp_type,document.getElementById("img"));'/><img alt=""  id='img' style='visibility:hidden;' border='0' src='<%=imagesURL%>/load.gif'/>
                                    </a>

                                </td>
                                </tr>
                                <tr>
                                    <td height="25" align="left" class="text">New Part No</td>
                                    <td height="25" align="left" class="text">

                                        <input type="text" name="newPartNo" id="New Part No" value=""><a href='#'><img src='<%=imagesURL%>/arrdown.gif' border='0' alt='Get Suggestions' style='width:15px' onclick='getSuggestions("New Part No",document.form1.newPartNo,document.form1.comp_type,document.getElementById("img"));'/><img alt=""  id='img' style='visibility:hidden;' border='0' src='<%=imagesURL%>/load.gif'/>
                                        </a>

                                    </td>
                                </tr>
                                <tr>
                                    <td height="40" align="left" class="text">ECN No</td>
                                    <td height="40" align="left" class="text">
                                        <input type="text" name="ecnNo" id="ecnNoId" value="">
                                    </td>
                                </tr>


                                <tr>
                                    <td colspan="3" align="center" style="padding-right:200px;"><label>

                                            <input type="submit" name="Next" id="Next" value="Submit" style="width:70px;" />
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

