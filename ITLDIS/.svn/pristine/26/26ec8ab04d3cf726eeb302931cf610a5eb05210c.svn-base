<%-- 
    Document   : get_engineModel_list
    Created on : Feb 28, 2012, 3:25:39 PM
    Author     : Avinash.Pandey
--%>

<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="EAMG.Group.DAO.EAMGGroupDAO_R,java.sql.Connection,java.sql.Connection,java.io.*,java.sql.*, java.util.*,viewEcat.comEcat.*,org.apache.log4j.Logger"%>
<%
    response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
    response.setHeader("Expires", "0");
    response.setHeader("Pragma", "no-cache");
    ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
    String server_name = (String) session.getValue("server_name");
    String ecatPath = (String) session.getValue("ecatPATH");
    String mainURL = (String) session.getValue("mainURL");
    PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);

    String imagesURL = "";
    imagesURL = object_pageTemplate.imagesURL();

%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=UtilityMapkeys1.tile%></title>
<link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
<%
    Connection conn = null;

    String product = request.getParameter("selectValue");

    String flag = request.getParameter("flag");
    conn = holder.getConnection();
    EAMGGroupDAO_R dao = new EAMGGroupDAO_R(conn);
    ArrayList<String> compEngineSeriesArrr = new ArrayList<String>();


    compEngineSeriesArrr = dao.getGroupComponentSeries(product, conn);


    //Vector applicationTypeVec = dao.getApplicationType(conn);

%>


<table width="100%"  border=0 bgcolor="#ffffff"  cellspacing=0 cellpadding=0>

    <tr>
        <td colspan="2" align="center" bgcolor="#FFFFFF" class="text" style="padding-top:20px">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <!--                    <td  align="center" valign="top" width="35%">&nbsp;</td>-->
                    <td colspan="2" align="center" valign="top"   style="padding-left:20px;"><div class="text"><strong>AVAILABLE COMPONENTS</strong></div></td>
                    <td width="41%" align="right"  class="text">&nbsp;</td>
                    <td  align="center" valign="top" width="35%">&nbsp;&nbsp;&nbsp;</td>
                </tr>
                <tr>
                    <td width="41%" align="right"  class="text">&nbsp;</td>
                </tr>
                <tr align=center>
                    <!--                    <td  align=left valign="top" class="text">&nbsp; </td>-->
                    <td colspan="2" align="left" valign="top" class="text" style="padding-left:60px;">
                        <select id="assign_Code" name="assign_Code"  style="width:254px" class="text"  size="12" multiple>

                            <%
                                if (compEngineSeriesArrr != null) {
                                    for (int i = 0; i < compEngineSeriesArrr.size(); i += 2) {
                            %>
                            <option value="<%=compEngineSeriesArrr.get(i + 1)%>"><%=compEngineSeriesArrr.get(i)%></option>
                            <%}
                                }%>
                        </select>
                    </td>
                    <td class="text" align=left>
                        <table>
                            <tr>
                                <td align="center" bgcolor="#FFFFFF" colspan="2"><label>
                                        <input type='button' value='&uarr;' style='background-color:#EFEBEF;font:bold 20px Arial;' onClick="up(document.getElementById('assign_Code'))" title="UP">
                                    </label></td>
                            </tr>
                            <tr>
                                <td align="center" bgcolor="#FFFFFF" colspan="2"><label>
                                        <input type='button' value='&darr;' style='background-color:#EFEBEF;font:bold 20px Arial;' onClick="down(document.getElementById('assign_Code'))" title="DOWN">
                                    </label></td>
                            </tr>
                        </table>
                    </td>
                    <td  align=left valign="top" class="text">&nbsp;&nbsp;&nbsp; </td>
                </tr>

            </table>
        </td>
    </tr>
    <tr>
        <td width="41%" align="right" bgcolor="#FFFFFF" class="text">&nbsp;</td>
    </tr>
    <tr>
        <td colspan="2" align="left" bgcolor="#FFFFFF" class="text" style="padding-top:15px;padding-left:200px; ">
            <input type="submit" name="button" value="Submit" /></td>
    </tr>
</table>






