<%-- 
    Document   : ChangeFcodeSequenceAjax
    Created on : May 10, 2013, 11:24:01 AM
    Author     : satyaprakash.verma
--%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="EAMG.Group.DAO.EAMGGroupDAO_R,viewEcat.comEcat.PageTemplate,java.sql.Connection,java.sql.Connection,java.io.*,java.sql.*, java.util.*,dbConnection.dbConnection"%>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");

%>
<%            Connection conn = null;
            ArrayList<String> tableSeriesArrr = null;
            try {
                String fcodeValue = request.getParameter("selectValue");
                String flag = request.getParameter("flag");
                conn = new dbConnection().getDbConnection();
                EAMGGroupDAO_R dao = new EAMGGroupDAO_R(conn);

                tableSeriesArrr = dao.getTableSeries(fcodeValue, conn);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                        conn = null;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }

%>
<table width="100%"  border=0 bgcolor="#ffffff"  cellspacing=0 cellpadding=0>

    <tr>
        <td colspan="2" align="center" bgcolor="#FFFFFF" class="text" style="padding-top:20px">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <!--                    <td  align="center" valign="top" width="35%">&nbsp;</td>-->
                    <td colspan="2" align="center" valign="top"   style="padding-left:20px;"><div class="text"><strong>AVAILABLE <%=PageTemplate.GROUP.toUpperCase()%>S</strong></div></td>
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
                                        if (tableSeriesArrr != null) {
                                            for (int i = 0; i < tableSeriesArrr.size(); i+=2) {
                            %>
                            <option value="<%=tableSeriesArrr.get(i)%>"><%=tableSeriesArrr.get(i)%><%="  "%> (<%=tableSeriesArrr.get(i+1)%>)</option>
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
