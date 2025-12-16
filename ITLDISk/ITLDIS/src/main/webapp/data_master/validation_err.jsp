<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String contextPath = request.getContextPath();
            String validate_type = (String) request.getAttribute("validate_type");
            String heading = null;

%>

<% if (validate_type.equals("sap")) {
                heading = "UPLOAD SAP PART MASTER";
            } else if (validate_type.equals("pricelist")) {
                heading = "UPLOAD PRICE LIST";
            }
%>
<div class="contentmain1">
    <div class="breadcrumb_container">
        <ul class="hText">
            <li class="breadcrumb_link"><a href ='<%=contextPath%>/masterAction.do?option=initOptions'>MASTERS</a></li>
            <li class="breadcrumb_link"><%=heading%></li>

        </ul>
    </div>
    <br/>
    <center>
        <div class="content_right1">
            <div class="con_slidediv2" style="position: relative;width: 100%">
                <h1 class="hText"><%=heading%></h1>
                <div align="center">
                    <%
                                String err = (String) request.getAttribute("xls_validate_result");
                                int pos1 = err.lastIndexOf("@");
                                int pos2 = err.lastIndexOf("#");
                                int row = Integer.parseInt(err.substring(0, pos1 - 1)) + 1;
                                int column = Integer.parseInt(err.substring(pos1 + 1, pos2 - 1)) + 1;
                                String error = err.substring(pos2 + 1);
                                String error2 = "";
                    %>
                    <table width="700" border="0" cellspacing="1" cellpadding="1">

                        <%
                                    if (validate_type.equals("modify")) {
                                        error2 = "Hence Part Modification Process aborted. Attach valid Excel to complete the Process successfully.";

                        %>
                        <%} else if (validate_type.equals("pricelist")) {
                                                        error2 = "Hence Pricelist  Process aborted. Attach valid Excel to complete the Process successfully.";

                                                    } else {
                                                        error2 = "Hence Part Creation Process aborted. Attach valid Excel to complete the Process successfully.";
                        %>
                        <%}%>


                        <tr>
                            <td>&nbsp;</td>
                        </tr>
                        <tr>
                            <td class="red" align="center">
                                There is an error at Column <%=column%>, Row <%=row%>. <%=error%><br><%=error2%></td>
                        </tr>

                        <%if (validate_type.equals("sap")) {%>
                        <tr>  <td align="center"> <font class="red-for-temmplate-link"><a href="javascript:location.href='<%=contextPath%>/masterAction.do?option=initUploadSAPPartMaster';">Try Again</a></font></td></tr>
                        <%} else if (validate_type.equals("pricelist")) {
                        %>
                        <tr>  <td align="center"> <font class="red-for-temmplate-link"><a href="javascript:location.href='<%=contextPath%>/masterAction.do?option=initPricelistExcelMaster';">Try Again</a></font></td></tr>
                        <%}%>


                    </table>
                </div>
            </div>
        </div>
    </center>
</div>
