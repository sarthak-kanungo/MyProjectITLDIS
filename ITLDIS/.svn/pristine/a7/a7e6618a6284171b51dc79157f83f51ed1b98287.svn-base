<%-- 
    Document   : Create_Model_PDF
    Created on : Mar 5, 2012, 2:36:41 PM
    Author     : manish.kishore
--%>

<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles"%>

<%@page import="authEcat.UtilityMapkeys1,viewEcat.comEcat.PageTemplate,viewEcat.comEcat.ConnectionHolder,java.io.PrintWriter,java.util.ArrayList,java.sql.Statement,java.sql.ResultSet,java.sql.Connection"  contentType="text/html" pageEncoding="UTF-8"%>

<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            String context = request.getContextPath();
            PageTemplate object_pageTemplate = new PageTemplate();
            //String cnt = request.getContextPath();
            String imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");



            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", context+"/Index", "", imagesURL);
                return;
            }
            String heading = null;
            String modelno = null;
            int width = 659;
            int height = 84;
            int count = 1;
            Statement stmt = null;
            ResultSet rs = null;
            Connection con = null;
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            con = holder.getConnection();
            stmt = con.createStatement();


%>
<html>
    <title><%=UtilityMapkeys1.tile%></title>
    <head>
        <link href="<%=imagesURL%>style.css" type="text/css" rel="stylesheet"></head>
    <script language="JavaScript">
        function validate(myform)
        {
            var myform=myform;
            var  ele  =document.myform.model.value;
            if(ele=="")
            {
                alert('Please Select Model Name');
                document.myform.model.focus();
                return false;
            }

        }

        function donotsubmit()
        {
            return false;
        }
    </script>
    <body>

        <form   action="<%=context%>/CreatePDF.do"   method="post"  name="myform" id="myform" enctype="multipart/form-data"  onsubmit="return validate(this);">
            <input type="hidden" name="option"  value="modelPDF"/>
            <%
                        heading = "CREATE MODEL PDF";
                        out.println(object_pageTemplate.tableHeader(heading, width, height));


            %>
            <table width=99% border=0 cellspacing=1 cellpadding=2 bordercolor = #CCCCCC>
                <tr >
                    <td  align = center width = 40%  class=links_txt><strong>
                            Select  Model:</strong>

                    </td>

                    <td width = 60% class=links_txt>


                        <%
                                    rs = stmt.executeQuery("select distinct MODEL_NO from CAT_MODELS  order by MODEL_NO");

                                    while (rs.next()) {
                                        modelno = rs.getString(1);
                                        if (count == 1) {%>
                        <select name="model" class="texarea-simple" id="Model" style="width:165px">
                            <option value="">--Select Model--</option>
                            <%}%>
                            <option value="<%=modelno%>"><%=modelno%></option>
                            <%

                                            count++;
                                        }
                                        rs.close();
                                        stmt.close();

                                        if (count > 1) {
                            %>
                        </select>
                        <%}%>
                    </td>
                </tr>
                <tr >
                    <td  colspan="2"  align = center valign="bottom" width = 100%   class=links_txt>&nbsp;
                    </td>
                </tr>
                <tr>
                    <td  colspan="2"  align = center valign="bottom" width = 100%   class=links_txt>
                        <input type="submit" name="submit" value="Submit" />

                    </td>
                </tr>
            </table>
            <%out.println(object_pageTemplate.tableFooter());%>
        </form>
    </body>
</html>
