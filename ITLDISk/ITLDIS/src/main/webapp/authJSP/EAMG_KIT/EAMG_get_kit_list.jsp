<%-- 
    Document   : amw_get_kit_list
    Created on : Nov 15, 2011, 11:14:28 PM
    Author     : avinash.pandey
--%>
<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.Connection,java.io.*,viewEcat.comEcat.*"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1,EAMG.Model.DAO.*,com.EAMG.common.*" %>
<%@ page import="EAMG.Group.DAO.EAMGGroupDAO_R,java.sql.Connection,org.apache.log4j.Logger"%>
<%
            String context = request.getContextPath();
            response.setHeader("Cache-Control", "no-cache, must-revalidate, max_age=0, no-store");
            response.setHeader("Expires", "0");
            response.setHeader("Pragma", "no-cache");
            ConnectionHolder holder = (ConnectionHolder) session.getValue("servletapp.connection");
            String server_name = (String) session.getValue("server_name");
            String ecatPath = (String) session.getValue("ecatPATH");
            String mainURL = (String) session.getValue("mainURL");
            PageTemplate object_pageTemplate = new PageTemplate(server_name, ecatPath, mainURL);
            String servletURL = "";
            servletURL = object_pageTemplate.servletURL();
            String imagesURL = "";
				String contextPath = request.getContextPath();
            imagesURL = object_pageTemplate.imagesURL();
            String session_id = session.getId();
            String getSession = (String) session.getValue("session_value");
            if (!session_id.equals(getSession)) {
                object_pageTemplate.ShowMsg(new PrintWriter(out), "FAILURE", PageTemplate.sessionExpiredMessage, "YES", "Please Login Again", contextPath+"/Index", "", imagesURL);
                return;
            }
%>
<%
            Connection conn = null;

            try {
                String kitno = request.getParameter("kitno");
                conn = holder.getConnection();
                EAMGGroupDAO_R dao = new EAMGGroupDAO_R(conn);
                String kitParamstr = dao.getKITDetail(kitno);
                response.getWriter().write(kitParamstr);
            } catch (Exception e) {
                e.printStackTrace();

            } 
%>