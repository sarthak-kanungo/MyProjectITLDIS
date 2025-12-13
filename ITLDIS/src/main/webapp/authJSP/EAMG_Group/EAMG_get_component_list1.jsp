
<%@page pageEncoding="UTF-8"%>
<%@ page import="EAMG.Group.DAO.EAMGGroupDAO_R,java.sql.Connection,java.sql.Connection,java.io.*,java.sql.*, java.util.*,viewEcat.comEcat.*,com.EAMG.common.EAMG_MethodsUtility,org.apache.log4j.Logger"%>
<%
response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
response.setHeader("Expires", "0");
response.setHeader("Pragma", "no-cache");
ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
%>
 <%
 Connection conn=null;
 
 try {
        //String partno=request.getParameter("compno");
       
        String partno=request.getParameter("part_no");
		 // System.out.println(partno);
        conn = holder.getConnection();
        EAMGGroupDAO_R dao=new EAMGGroupDAO_R(conn);
        String compParamstr=dao.getComponentDetail(partno,"");
        response.getWriter().write(compParamstr);
		  //System.out.println(compParamstr);
         } catch (Exception e) {
            e.printStackTrace();
            
        } finally {
            try {
                
            } catch (Exception e) {
                
            }
        }
%>

