<%-- 
    Author     : Avinash.Pandey
--%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.ConnectionHolder,viewEcat.comEcat.PageTemplate" %>
<%@ page import="EAMG.Group.DAO.EAMGGroupDAO_R,org.apache.log4j.Logger"%>
<%
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            //String server_name = (String) session.getValue("server_name");
            //String ecatPATH = (String) session.getValue("ecatPATH");
            //String mainURL = (String) session.getValue("mainURL");
%>
<%
            Connection conn = null;

            try {
                //String partno = request.getParameter("compno");
                String partno = request.getParameter("part_no");
                String comptype = request.getParameter("comptype");
                conn = holder.getConnection();
                EAMGGroupDAO_R dao = new EAMGGroupDAO_R(conn);
					 String compParamstr=null;

					 if(!comptype.equals("group"))
                 compParamstr = dao.getComponentDetail(partno,comptype);
					 else
						compParamstr = dao.getGroupDetail(partno,comptype);

                response.getWriter().write(compParamstr);
            } catch (Exception e) {
                e.printStackTrace();

            } 
%>