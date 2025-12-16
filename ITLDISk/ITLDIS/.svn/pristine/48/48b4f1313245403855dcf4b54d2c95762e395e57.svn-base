<%-- 
    Document   : EAMG_get_model_status
    Created on : Nov 22, 2012, 7:06:15 PM
    Author     : satyaprakash.verma
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*" %>

<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>

         <%
                    String contextPath = request.getContextPath();
                    response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
                    response.setHeader("Expires", "0");
                    response.setHeader("Pragma", "no-cache");
                    ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
                    
         %>
    <%!
        Statement stmt=null;
        ResultSet rs=null;
        Connection conn=null;
    %>
    <%
         conn=holder.getConnection();
    %>
        <%
            String model_no=request.getParameter("model_no");
            String temp_str="";
            String temp_result="";
            String result="";
            String temp_id="";

          if(model_no != null)
          {

                
              try{

                   stmt=conn.createStatement();
                   String query="SELECT MODEL_NO,STATUS FROM CAT_MODELS WHERE MODEL_NO='"+model_no+"'";
                   rs = stmt.executeQuery(query);
                   if(rs.next())
                   {
                        temp_str = rs.getString(1);
                        temp_id= rs.getString(2);
                        temp_result=temp_str + "##"+ temp_id + "~~" ;
                        result = result + temp_result;
                   }else{
                         temp_result=0 + "##"+0+"~~" ;
                         result = result + temp_result;
                       }
                    response.getWriter().write(result);
                    result = null;
                    if(stmt!=null)stmt.close();
                    if(rs!=null)rs.close();

                 }catch(Exception e){}
          }else
          {
	    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
          }

         %>
