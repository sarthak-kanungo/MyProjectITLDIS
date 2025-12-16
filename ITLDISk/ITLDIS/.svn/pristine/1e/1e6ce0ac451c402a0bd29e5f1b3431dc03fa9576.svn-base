<%-- 
    Document   : EAMG_get_components_with_type
    Author     : avinash.pandey
--%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*" %>
<%
		 response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
		 response.setHeader("Expires", "0");
		 response.setHeader("Pragma", "no-cache");
		 Connection conn = null;
		 ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
		 String server_name = (String) session.getValue("server_name");
		 String ecatPath = (String) session.getValue("ecatPATH");
		 String mainURL = (String) session.getValue("mainURL");
		 PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
		 String imagesURL = "";
		 String contextPath = request.getContextPath();
		 imagesURL = object_pageTemplate.imagesURL();
		 String session_id = session.getId();
		 String getSession = (String) session.getValue("session_value");
		 if (!session_id.equals(getSession)) {
			  object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath + "/Index", "", imagesURL);
			  return;
		 }
%>
<%
		 String compno = request.getParameter("comp_no");
		 if (compno != null && compno.equals("*")) {
			  compno = "%";
		 }
		 String comptype = request.getParameter("comp_type");
		 //String comptype = request.getParameter("comp_type");
		 String creation = request.getParameter("creation");
		 ////System.out.println("compno in jsp :"+compno);
		 ////System.out.println("comptype in jsp :"+comptype);
		 String compParamstr = "";
		 conn = holder.getConnection();
		 ResultSet rs;
		 Statement stmt = null;
		 stmt = conn.createStatement();
		 String query = "";
		 int i = 0;
		 String comp_no = "";
		 String comp_type = "";

		 if (comptype.equals("All")) {
			  query = "Select part_no from cat_part order by part_no";
		 } else {
			  query = "Select part_no from cat_part where part_type='" + comptype + "' order by part_no";

		 }
		 ////System.out.println("query:"+query);
		 stmt = conn.createStatement();
		 rs = stmt.executeQuery(query);
		 out.println("<select class=\"links_txt\" name=\"compList\" id=\"compList\" size=\"12\" multiple=\"multiple\" style=\"width:300px\" ondblclick=\"moveDualList(document.form1.compList,  document.form1.attachedlist, false);\">");
		 while (rs.next()) {

			  comp_no = rs.getString(1);
			  out.println("				<option value='" + comp_no + "'>" + comp_no + "</option>");
		 }
		 out.println("                                </select>");
		 out.println("$#$");
		 

		 rs.close();
		 stmt.close();





%>

