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
      <script type="text/javascript" language="javascript">

        function validate()
         {
                var fromDate=document.form1.fromDate.value;
                var toDate=document.form1.toDate.value;
                if(fromDate=='')
                    {
                        alert("Please Select From Date.");
                        document.form1.fromDate.focus();
                        return false;
                    }
                if(toDate=='')
                    {
                        alert("Please Select To Date.");
                        document.form1.toDate.focus();
                        return false;
                    }

            var d1 = new Date(fromDate)
            var d2 = new Date(toDate)
            if(d1>d2)
             {
                alert("Please Select To Date Greater than From Date.");
                document.form1.toDate.focus();
                return false;
             }
         }
      </script>
    </head>
    <body>
        <%
					String tdData = "MANAGE ECN >> ECN REPORT";
					object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
					heading = "ECN REPORT";
					out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center" width="600">
            <table width="600" border="0" cellspacing="1" cellpadding="1">
                <tr valign="top">
                    <td align="center" valign="top" bgcolor="#FFFFFF" style="padding-left: 100px;">
                        <form name="form1"  method="post"  onsubmit="return validate()" action="<%=contextPath%>/ECNReport.do">
                            <br />
                            <table width="95%" border="0" cellspacing="2" cellpadding="2">                              
                               <tr>
                                   <td width="29%" height="25" align="left" class="text">From</td>
                                   <td width="65%" height="25" align="left" class="text">
                                   <input type="text" name="fromDate" value=""   readonly="readonly" id="fromDateID">
                                   <img alt="calendar" id="effectiveDateCalImg" src='<%=imagesURL%>/showCalendar.jpg' width=20 height=20 border=0 onclick="displayCalendar(document.getElementById('fromDateID'),'dd/mm/yyyy',this);" />													 </td>
                                 </tr>
                                 
                                <tr>
                                    <td width="29%" height="25" align="left" class="text">To</td>
                                    <td width="65%" height="25" align="left" class="text">
                                    <input type="text" name="toDate" value=""   readonly="readonly" id="toDateID">
                                   <img alt="calendar" id="effectiveDateCalImg" src='<%=imagesURL%>/showCalendar.jpg' width=20 height=20 border=0 onclick="displayCalendar(document.getElementById('toDateID'),'dd/mm/yyyy',this);" />													 </td>
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

