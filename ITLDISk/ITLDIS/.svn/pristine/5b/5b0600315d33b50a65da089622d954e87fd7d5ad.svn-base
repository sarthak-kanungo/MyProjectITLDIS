<%-- 
    Document   : demo
    Created on : Nov 11, 2011, 7:00:56 PM
    Author     : avinash.pandey
--%>

<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %>
<%@ page import="dbConnection.amw_ConnectionThread,java.sql.*, java.util.*" %>
<!DOCTYPE html>
<html>
    <% Vector searchAMWPartresult = (Vector) request.getAttribute("searchAMWPartresult");
        Vector totalOEMPart = (Vector) request.getAttribute("searchOEMPartVec");
        String msg = "NO DATA FOUND";
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
         <title>AMW-Ecatalogue</title>
    </head>
    <body onload="setlengths();">
        <%if (totalOEMPart != null) {

                if (totalOEMPart.size() > 0) {%>
    <tr>
        <td colspan="3" align="right">
            <table width="100%" border="0" cellpadding="1" cellspacing="1">

                <tr>
                    <td width="43%" align="center" bgcolor="#000000" >
                        <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="43%" height="25" align="center" class="blue"><strong class="heading">Available OME Part</strong></td>
                            </tr>
                        </table></td>
                    <td width="7%" bgcolor="#FFFFFF">&nbsp;</td>
                    <td width="43%" align="center" bgcolor="#000000">
                        <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
                            <tr>
                                <td width="43%" height="25" align="center" class="blue"><strong class="heading">Selected OME Part</strong></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td align="right" bgcolor="#FFFFFF" class="text"># <span  id="totalOMEPart"></span></td>
                    <td bgcolor="#FFFFFF">&nbsp;</td>
                    <td align="right" bgcolor="#FFFFFF" class="text"># <span id="selectedOMEPart"></span></td>
                </tr>

                <tr>
                    <td rowspan="6" bgcolor="#FFFFFF"><select id="omePartlist" name="omePartlist" size="12" multiple="multiple" style="width:310px ">
                            <%
                                Iterator i = totalOEMPart.iterator();
                                while (i.hasNext()) {%>
                            <option><%=(String) i.next()%></option>
                            <%}
                            %>
                        </select>
                    </td>
                    <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                    <td rowspan="6" bgcolor="#FFFFFF">
                        <select name="attachedlist" size="12" multiple="multiple" style="width:310px ">
                            <%if (searchAMWPartresult.size() > 0) {%>
                            <% for (int j = 0; j < searchAMWPartresult.size(); j++) {
                                    String omepart = "" + searchAMWPartresult.get(j);
                            %>
                            <option value="<%=omepart%>" > <%= omepart%>  </option >
                            <% }
                                }%>

                    </td>
                </tr>
                <tr>
                    <td align="center" bgcolor="#FFFFFF"><label>
                            <img src="/AMW-AuthEcat/auth_images/next.gif" name="selectone" style="width:24px " onclick="moveDualList( document.form1.omePartlist,  document.form1.attachedlist, false);" />
                        </label></td>
                </tr>
                <tr>
                    <td align="center" bgcolor="#FFFFFF"><label>
                            <img src="/AMW-AuthEcat/auth_images/next1.gif" name="selectall" style="width:24px " onclick="moveDualList( document.form1.omePartlist,  document.form1.attachedlist, true);"/>
                        </label></td>
                </tr>
                <tr>
                    <td align="center" bgcolor="#FFFFFF"><label>
                            <img src="/AMW-AuthEcat/auth_images/previous.gif" name="removeone" style="width:24px " onclick="moveDualList( document.form1.attachedlist,document.form1.omePartlist,false);"/>
                        </label></td>
                </tr>
                <tr>
                    <td align="center" bgcolor="#FFFFFF"><label>
                            <img src="/AMW-AuthEcat/auth_images/previous1.gif" name="removeall" style="width:24px " onclick="moveDualList(document.form1.attachedlist,document.form1.omePartlist,true);"/>
                        </label></td>
                </tr>
                <tr>
                    <td align="center" bgcolor="#FFFFFF">&nbsp;</td>
                </tr>

            </table>
                                
        </td>
    </tr>
    <tr>
        <td colspan="3">
            <table width="100%">
                <tr>

                    <td width="50%" align="right">
                        <input type="submit" name="Next" id="Next" value="Submit" style="width:70px;" onClick="return validate();"/>
                    </td>
                    <td width="50%" align="left">
                        <input type="button" name="Cancel" value="Cancel" onclick="return CancelProcess();" />
                    </td>
                </tr>
            </table>
        </td>
    </tr>
    <%}
   } else {%>
    <span class="red" ><%=msg%>.</span>
    <%}%>
</body>
</html>
