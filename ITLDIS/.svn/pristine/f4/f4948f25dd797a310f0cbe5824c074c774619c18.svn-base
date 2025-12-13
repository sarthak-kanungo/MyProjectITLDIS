<%@page import="java.util.Calendar"%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

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

        java.util.Calendar cal = java.util.Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title><%=UtilityMapkeys1.tile%></title>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <script type="text/javascript">
            function validate()
            {
             //alert();
                var monthObj = document.getElementById('Month');
                var month = monthObj.value;
                var yearObj = document.getElementById('Year');
                var year = yearObj.value;
                if (month == '')
                {
                    alert('Please select Month.');
                    monthObj.focus();
                    return false;
                }
                if (year == "")
                {
                    alert('Please select Year.');
                    yearObj.focus();
                    return false;
                }
                document.getElementById('Submit').disabled = true;
            }

        </script>


    </head>
    <body>
        <%
            String tdData = "USAGE DETAILS";
            object_pageTemplate.pageLink(new PrintWriter(out), width, height, tdData);
        %>
        <%
            heading = "USAGE DETAILS";
            out.println(object_pageTemplate.tableHeader(heading, width, height));
        %>
        <div align="center" width="600"><font class="red"><html:errors/></font>
            <table width="600" border="0" cellspacing="1" cellpadding="1">
                <tr valign="top">
                    <td align="center" valign="top" bgcolor="#FFFFFF" style="padding-left: 100px;">
                        <form name="form1"  method="post"  onsubmit="return validate()" action="<%=contextPath%>/UsageDetails.do">
                            <br />
                            <table width="95%" border="0" cellspacing="2" cellpadding="2">

                                <tr>
                                    <td width="29%" height="25" align="left" class="text"><b>Select Month</b></td>
                                    <td width="65%" height="25" align="left" class="text">
                                        <select  name="month"  id="Month" class="GroupSelect">
                                            <option value="">--Select--</option>
                                            <option value="0">January</option>
                                            <option value="1">February</option>
                                            <option value="2">March</option>
                                            <option value="3">April</option>
                                            <option value="4">May</option>
                                            <option value="5">June</option>
                                            <option value="6">July</option>
                                            <option value="7">August</option>
                                            <option value="8">September</option>
                                            <option value="9">October</option>
                                            <option value="10">November</option>
                                            <option value="11">December</option>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td width="29%" height="25" align="left" class="text"><b>Select Year</b></td>
                                    <td width="65%" height="25" align="left" class="text">
                                        <select  name="year"  id="Year" class="GroupSelect" >
                                            <option value="">--Select--</option>
                                            <%for (int ye = 2012; ye <= year; ye++) {%>
                                            <option value="<%=ye%>"><%=ye%></option>
                                            <%}%>
                                        </select>
                                    </td>
                                </tr>
                                <tr>
                                    <td colspan="3" align="center" style="padding-right:200px;"><label>
                                            <input type="submit" id="Submit" value="Submit" style="width:70px;" />
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

