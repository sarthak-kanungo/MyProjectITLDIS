<%-- 
    Document   : EAMG_get_engineModel_list
    Created on : Feb 28, 2012, 3:25:39 PM
    Author     : Avinash.Pandey
--%>

<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="EAMG.Group.DAO.EAMGGroupDAO_R,java.sql.Connection,java.sql.Connection,java.io.*,java.sql.*, java.util.*,viewEcat.comEcat.*,com.EAMG.common.EAMG_MethodsUtility,org.apache.log4j.Logger,EAMG.Model.DAO.*"%>
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
            String engineModel = object_pageTemplate.ENGINE_MODEL;

%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><%=UtilityMapkeys1.tile%></title>
<link href="<%=imagesURL%>css/config.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=imagesURL%>js/validations.js"></script>
<%
            Connection conn = null;
            try {
                String product = request.getParameter("selectValue");
                conn = holder.getConnection();
                EAMG_ModelDAO dao = new EAMG_ModelDAO();
                ArrayList<String> compEngineSeriesArrr = new ArrayList<String>();
                compEngineSeriesArrr = dao.getEngineModelByEngineSeries(product, conn);
                Vector applicationTypeVec = dao.getApplicationType(conn);

%>
<%if (compEngineSeriesArrr.size() > 0) {
                    if (compEngineSeriesArrr != null) {%>
<table width="650" border="0" cellpadding="2" cellspacing="0"  bgcolor="#CCCCCC">
    <tr>
        <td height="25" width="152" align="left" bgcolor="#FFFFFF" class="text"><%=engineModel%></td>
        <td height="25" width="140" align="left" bgcolor="#FFFFFF" class="text">
            <select name="engineModel"  style="width:110px;" id="engineModel" class="text" onchange="showOtherModel()">
                <option value='0'>--Select--</option>

                <%
                                        for (int i = 0; i < compEngineSeriesArrr.size(); i++) {
                                            String engineModelVal = ((String) compEngineSeriesArrr.get(i));
                %>
                <option value="<%=engineModelVal%>"><%=engineModelVal%></option>
                <%}%>

                 <option value="1Oth">OTHER</option>
            </select>
        </td>
           <td align="center" width="120" bgcolor="#FFFFFF">
           <input type="text" name="engineModelOth" id="engineModelOth" style="display:none;" value="" onblur="stringOnlyValidation(document.form1.engineModelOth)"/>
          </td>

          <td width="120" height="25" align="right" bgcolor="#FFFFFF" class="text" style="display:none;" id="engineModelBrowseId">Browse Image File</td>
         <td width="120" height="25" align="left" bgcolor="#FFFFFF" class="text"><input type=file id="engineModelImageFileOth" name="engineModelImageFileOth" value='' style="display:none;"></td>
       </tr>

    <tr>
        <td height="25" width="152" align="left" bgcolor="#FFFFFF" class="text">Type</td>
        <td height="25" width="140" align="left" bgcolor="#FFFFFF" class="text">
            <select name="applicationType"  style="width:110px" id="applicationType" class="text" onchange="showOther()">
                <option value='0'>--Select--</option>
                <%if (applicationTypeVec.size() > 0) {
                                            if (applicationTypeVec != null) {

                                                for (int i = 0; i < applicationTypeVec.size(); i++) {
                                                    String applicationType = ((String) applicationTypeVec.elementAt(i));
                %>
                <option value="<%=applicationType%>"><%=applicationType%></option>
                <%}%>
                <option value="1Oth">OTHER</option>

            </select>
        </td>
        <td id="applicationTypeId" width="120" style="display:none;"  align="left" bgcolor="#FFFFFF" >
           <input type="text" name="applicationTypeOth" id="applicationTypeOth" value="" onblur="stringOnlyValidation(document.form1.applicationTypeOth)"/>
         </td>
        <td align="center" colspan="3" bgcolor="#FFFFFF"><span class="text" >&nbsp;</span></td>
             
        <% }
          }
        %>


    </tr>
</table>
<% }
} else {
%>


<table width="650" border="0" cellpadding="2" cellspacing="0"  bgcolor="#CCCCCC">
    <tr>
        <td height="25" width="152" align="left" bgcolor="#FFFFFF" class="text"><%=engineModel%></td>
        <td height="25" width="140" align="left" bgcolor="#FFFFFF" class="text">
            <select name="engineModel"  style="width:110px" id="engineModel" class="text" onchange="showOtherModel()">
                <option value='0'>--Select--</option>

                <option value='1Oth'>OTHER</option>

             </select>
        </td>
        <td id="engineTextId" width="120" align="left" bgcolor="#FFFFFF" >
           <input type="text" style="display:none;" name="engineModelOth" id="engineModelOth" value="" onblur="stringOnlyValidation(document.form1.engineModelOth)"/>
          </td>

          <td width="120" height="25" align="right" bgcolor="#FFFFFF" class="text" style="display:none;" id="engineModelBrowseId">Browse Image File</td>
         <td width="120" height="25" align="left" bgcolor="#FFFFFF" class="text"><input type=file id="engineModelImageFileOth" name="engineModelImageFileOth" value='' style="display:none;"></td>
       </tr>

        <tr>
        <td height="25" align="left" bgcolor="#FFFFFF" class="text" width="152" >Type</td>
        <td height="25" align="left" bgcolor="#FFFFFF" class="text" width="140" >
            <select name="applicationType"  style="width:110px" id="applicationType" class="text" onchange="showOther()">
                <option value='0'>--Select--</option>
                <%if (applicationTypeVec.size() > 0) {
                                            if (applicationTypeVec != null) {

                                                for (int i = 0; i < applicationTypeVec.size(); i++) {
                                                    String applicationType = ((String) applicationTypeVec.elementAt(i));
                %>
                <option value="<%=applicationType%>"><%=applicationType%></option>
                <%}%>
                <option value="1Oth">OTHER</option>

            </select>

        </td>
        <td id="applicationTypeId" width="120" style="display:none;"  align="left" bgcolor="#FFFFFF" >
           <input type="text" name="applicationTypeOth" id="applicationTypeOth" value="" onblur="stringOnlyValidation(document.form1.applicationTypeOth)"/>
         </td>
        <td align="center" bgcolor="#FFFFFF" colspan="3"><span class="text">&nbsp;</span></td>
        
        
        
        <% }
                                }
        %>


    </tr>
</table>
<%}
%>
<%} catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                } catch (Exception e) {
                }
            }%>