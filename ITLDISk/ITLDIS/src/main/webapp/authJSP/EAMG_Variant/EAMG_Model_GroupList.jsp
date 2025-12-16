
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.ConnectionHolder,viewEcat.comEcat.PageTemplate" %>
<%@ page import="org.apache.log4j.Logger"%>
<%
		 response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
		 response.setHeader("Expires", "0");
		 response.setHeader("Pragma", "no-cache");
		 ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
		 //String server_name = (String) session.getValue("server_name");
		 //String ecatPATH = (String) session.getValue("ecatPATH");
		 //String mainURL = (String) session.getValue("mainURL");

		 String model_no=request.getParameter("model_no");
%>
<%
		 Connection conn = null;

		 try {
			  conn = holder.getConnection();
			  Statement st = conn.createStatement();
			  StringBuilder data = new StringBuilder("<listdata>");
			  ResultSet rs = st.executeQuery("Select distinct MODEL_NO from CAT_MODEL_GROUP where model_no like '%"+model_no+"%'");

			  while (rs.next()) {

					data.append(rs.getString(1) + "|");

			  }
			  data.append("</listdata>");
			  rs.close();
			  st.close();
			  response.getWriter().write(data.toString());
		 } catch (Exception e) {
			  e.printStackTrace();

		 }
%>