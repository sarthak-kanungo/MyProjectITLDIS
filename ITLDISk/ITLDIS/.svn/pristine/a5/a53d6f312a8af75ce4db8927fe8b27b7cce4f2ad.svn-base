<%-- 
    Author     : Avinash.Pandey
--%>
<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, java.util.*,spare.com.LoginDAO.spare_connectionDAO,spare.generatePI.action.spare_viewPartDAO" %>
<%@page import="com.util.pageTemplateTMIC,java.io.*,java.util.*,spare.generatePI.action.spare_viewPartActionForm"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
        response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
        response.setHeader("Expires", "0");
        response.setHeader("Pragma", "no-cache");
        Connection conn = null;
%>
<%
        try {
            conn = new dbConnection().getConnection();
            String partno = request.getParameter("partno");
            String comptype = request.getParameter("comptype");
            String compParamstr = null;
            spare_viewPartDAO partDAO = new spare_viewPartDAO(conn);

            compParamstr = partDAO.getComponentDetail(partno, "PART");
            
            response.getWriter().write(compParamstr);

            conn.commit();
            conn.close();
            conn = null;
        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            if (conn != null) {
                conn.close();
                conn = null;
            }
        }

%>

%>