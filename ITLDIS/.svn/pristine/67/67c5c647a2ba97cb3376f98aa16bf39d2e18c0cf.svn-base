<%-- 
    Document   : v_manage_function_ajax
    Created on : May 2, 2014, 2:27:46 PM
    Author     : sreeja.vijayakumaran
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html" %>

<c:set var="contextPath" value="<%=request.getContextPath()%>"/>
<%@ page import="java.sql.*,dao.masterDAO,java.util.*,org.apache.struts.util.LabelValueBean,beans.masterForm" %>


<% response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            List tabledescList = (ArrayList) session.getAttribute("tableinfo");

%>

<table width="80%" border="0" cellspacing="0" cellpadding="0">
    <tr style="height: 25px"><td class="text" colspan="2" bgcolor="#FFFFFF" align="left"><font class="text">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Available Description</font></td></tr>

    <tr bgcolor=#ffffff>
        <td width="90%"  class="text" align=center >
            <select name="tabledata" id="tabledata" class="text"   multiple="multiple" size="10" style="width:250px!important;height: 250px" >
                <%
                            if (tabledescList != null) {
                                if (tabledescList.size() > 0) {
                                    Iterator itr2 = tabledescList.iterator();
                                    while (itr2.hasNext()) {
                                        masterForm mf = (masterForm) itr2.next();
                %>

                <option value="<%=mf.getTabseqid()%>@@<%=mf.getTabledesc()%>"><%=mf.getTabledesc()%> </option>
                <%
                                    }
                                }
                            }
                %>
                <input type="hidden" name="tablename" value="${masterForm.tablename}"/>
            </select>
        </td>
        <td class="text" align=left>
            <table>
                <tr>
                    <td align="center" bgcolor="#FFFFFF" colspan="2"><label>
                            <input type='button' value='&uarr;' style='background-color:#EFEBEF;font:bold 20px Arial;' onClick="up(document.getElementById('tabledata'))" title="UP">
                        </label></td>
                </tr>
                <tr>
                    <td align="center" bgcolor="#FFFFFF" colspan="2"><label>
                            <input type='button' value='&darr;' style='background-color:#EFEBEF;font:bold 20px Arial;' onClick="down(document.getElementById('tabledata'))" title="DOWN">
                        </label></td>
                </tr>
            </table>
        </td>
    </tr>

    <script type="text/javascript">
        setlengths();
    </script>
</table>
