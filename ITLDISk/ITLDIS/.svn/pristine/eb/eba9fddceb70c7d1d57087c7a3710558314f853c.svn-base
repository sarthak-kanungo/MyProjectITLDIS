<%--
    Document   : uploadServiceBulletin
    Created on : Jul 3, 2013, 5:10:35 PM
    Author     : satyaprakash.verma
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*,EAMG_Service_Bulletin.EAMG_ServiceBulletinBean"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
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
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
                return;
            }
            String heading = null;
            int width = 970, height = 450;
%>

<%
            try{
            Vector<String> years = new Vector<String>();
            Vector<String> years_type = new Vector<String>();
            EAMG_ServiceBulletinBean bulletinBean = (EAMG_ServiceBulletinBean) request.getAttribute("bulletinBean");
            years = bulletinBean.getYearsVectorList();
            years_type = bulletinBean.getYearsTypeVectorList();
            boolean serviceCheck = false;
            boolean marketingCheck = false;
            boolean partCheck = false;

%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <title><%=UtilityMapkeys1.tile%></title>
        <script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
        <link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
        <link href="<%=imagesURL%>style.css" type="text/css" rel="stylesheet">
    </head>
    <body topmargin="0"  marginheight="0" leftmargin="0"  marginwidth="0">
        <% out.println(object_pageTemplate.tableHeader(PageTemplate.bulletin + "s Available", width, height));%>        

        <table width="920px;" border=0 cellspacing=0 cellpadding=0 bordercolor = #CCCCCC>
            <%if(years.size()>0){%>
            <tr><td><table width="920px" border=0 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC>
                        <tr bgcolor = #003399 class=heading1>
                            <%--<td width = 10% align = center>
                                S. NO.
                            </td>--%>
                            <td width = 20% align = center>
                                YEAR
                            </td>
                            <td width = 40% align = center>
                                SERVICE
                            </td>
                            <%--<td width = 30% align = center>
                                MARKETING
                            </td>--%>
                            <td width = 40% align = center>
                                PARTS
                            </td>
                        </tr>
                    </table>
                </td></tr><tr><td>
                    <div valign=top STYLE=" overflow-X:hidden;overflow-y: scroll; height:430;">
                        <table width="920px;"  border=0 cellspacing=1 cellpadding=3 bordercolor = #CCCCCC>
                            <%
                                        int counter = 1;
                                        String cssClass = "";
                                        for (int i = 0; i < years.size(); i++) {
                                            serviceCheck = false;
                                            marketingCheck = false;
                                            partCheck = false;

                                            for (int j = 0; j < years_type.size(); j = j + 2) {
                                                if (years_type.elementAt(j).equals(years.elementAt(i))) {
                                                    if (years_type.elementAt(j + 1).equals("Service")) {
                                                        serviceCheck = true;
                                                    } else if (years_type.elementAt(j + 1).equals("Marketing")) {
                                                        marketingCheck = true;
                                                    } else if (years_type.elementAt(j + 1).equals("Parts")) {
                                                        partCheck = true;
                                                    }
                                                }
                                            }

                            %>

                            <%
                                                                        if (i % 2 == 0) {
                                                                            cssClass = "links_txt_Alt";
                                                                        } else {
                                                                            cssClass = "links_txt";
                                                                        }
                            %>

                            <tr>
                                <%--<td  width=10% align = center   class="<%=cssClass%>">
                                    <%=counter%>
                                </td>--%>
                                
                                <td  width=20% align = center   class="<%=cssClass%>">
                                    <%=years.elementAt(i)%>
                                </td>
                                <td  width=40% align = center class="<%=cssClass%>">
                                    <%
                                      if (serviceCheck) {
                                    %>
                                    <a href="<%=contextPath%>/serviceBulletin.do?option=viewBulletinDetails&yearOfIssue=<%=years.elementAt(i)%>&type=Service">
                                        <%=years.elementAt(i)%>
                                    </a>
                                    <% } else {%>        
                                       -
                                    <%}%>
                                </td>                                                            
                                <%--<td  width=40% align = center class="<%=cssClass%>">
                                    <%
                                      if (marketingCheck) {
                                    %>
                                    <a href="<%=contextPath%>/serviceBulletin.do?option=viewBulletinDetails&yearOfIssue=<%=years.elementAt(i)%>&type=Marketing">
                                        <%=years.elementAt(i)%>
                                    </a>
                                    <% } else {%>
                                      -
                                    <%}%>
                                </td>--%>
                                <td  width=40% align = center class="<%=cssClass%>">
                                    <%
                                      if (partCheck) {
                                    %>
                                    <a href="<%=contextPath%>/serviceBulletin.do?option=viewBulletinDetails&yearOfIssue=<%=years.elementAt(i)%>&type=Parts">
                                        <%=years.elementAt(i)%>
                                    </a>
                                    <% } else {%>
                                     -
                                    <%}%>
                                </td>       
                            </tr>
                            <%
                                counter++;
                                  }
                            %>

                        </table>
                    </div>
                </td></tr>
            <%}else{%>
            <tr ><td align="center" class="links_txt"><b><font color="red">No Record Exists</font></b>

                </td>
            </tr>
            <%}%>
        </table>

        <%
                    out.println(object_pageTemplate.tableFooter());
        %>
    </body>
</html>
<%}catch(Exception e){e.printStackTrace();}%>

