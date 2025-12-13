
<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String contextPath = request.getContextPath();
            String validate_type = (String) request.getAttribute("validate_type");
           
           
%>

    <%if (validate_type.equalsIgnoreCase("sap")) {%>


    <div class="contentmain1">
        <div class="breadcrumb_container">
            <ul class="hText">
                <li class="breadcrumb_link"><a href ='<%=contextPath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
                <li class="breadcrumb_link">UPLOAD SAP PART MASTER</li>
            </ul>
        </div>
        <br/>
        <center>
            <div class="content_right1">
                <div class="con_slidediv2" style="position: relative;width: 100%">
                    <h1 class="hText">UPLOAD SAP PART MASTER</h1>
                    <table cellspacing=1 cellpadding=5 border=0 width=100% >
                        <tr height=30 bgcolor="#FFFFFF">
                            <td align= center class="headingSpas">
                                <table width="700" border="0" cellspacing="1" cellpadding="1">
                                    <%
                                        Vector result = (Vector) request.getAttribute("insertionresult");
                                        Vector message = (Vector) result.get(0);
                                        Vector exists = (Vector) result.get(1);

                                    %>
                                    <tr><td valign="top">
                                            <table align="center" >
                                                <tr>
                                                    <td valign="center" class="red" >
                                                        <%=message.get(0)%>
                                                    </td>
                                                </tr>

                                            </table>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="center"> <font class="red-for-temmplate-link"><a href="javascript:location.href='<%=contextPath%>/masterAction.do?option=initUploadSAPPartMaster'"><%=message.get(1)%></a></font></td>
                                    </tr>


                                    <br>

                                    <% if (exists.size() > 0) {%>
                                    <tr>
                                        <td>
                                            <table align="center" >
                                                <td valign="center" class="headingSpas">
                                                    <%if (exists.size() > 1) {%>
                                                    Following Parts are Already Exist.
                                                    <%} else {%>
										  Following Part is Already Exist.
                                                    <%}%>
                                                </td>
                                            </table>
                                        </td>
                                    </tr>

                                    <tr>
                                        <td align="center">
                                            <div style="overflow:auto; height:250px;width:267px;">
                                                <table width="250" border="0" cellpadding="0" cellspacing="1" class="tableStyle" >
                                                    <tr>
                                                        <td  height="10" align="center" class="headingSpas"><strong>S.No.</strong></td>
                                                        <td  align="left" class="headingSpas"><strong >&nbsp;Part No.</strong></td>
                                                    </tr>
                                                    <%
                                                                                              int ind = 1;
                                                                                              for (int i = 0; i < exists.size(); i++) {%>
                                                    <tr>
                                                        <td align="center"  class="headingSpas"><%=ind%></td>
                                                        <td align="left"  class="headingSpas">&nbsp;<%=exists.elementAt(i)%></td>
                                                    </tr>
                                                    <% ind++;
                                                                          }%>
                                                </table>
                                            </div>
                                        </td>
                                    </tr>
                                    <%

                                        }%>
                                </table>

                            </td>
                        </tr>
                    </table>
                </div>
            </div>
        </center>
    </div>


    <%}%>
