<%-- 
    Document   : EAMG_get_first_level_comps

--%>

<%@page pageEncoding="UTF-8"%>
<%@ page import="java.sql.*,viewEcat.comEcat.PageTemplate,java.io.PrintWriter,authEcat.UtilityMapkeys1" %>
<%@ page import="java.sql.Connection,java.io.*,java.sql.*, java.util.*,viewEcat.comEcat.*,com.EAMG.common.EAMG_MethodsUtility" %>
<%
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
            String grpno = request.getParameter("grpno");
            String selecttype = request.getParameter("selecttype");
            ////System.out.println("compno in jsp :"+compno);
            if (grpno != null && grpno.equals("*")) {
                grpno = "%";
            }
            String comptype = request.getParameter("comp_type");

            String compParamstr = "";
            Connection conn = holder.getConnection();

            ResultSet rs;
            Statement stmt = null;
            stmt = conn.createStatement();
            String query = "";
            int i = 0;
            String comp_type = "";
            String comp_no = "";
            if (selecttype.equals("grp")) {
                query = "Select distinct COMP_TYPE from CAT_GROUP_KIT_BOM where GRP_KIT_NO='" + grpno + "'";
            } else {
                query = "Select COMPONENT from CAT_GROUP_KIT_BOM where GRP_KIT_NO='" + grpno + "' and COMP_TYPE='PRT'";
            }

            System.out.println("query:"+query);
            stmt = conn.createStatement();
            rs = stmt.executeQuery(query);
            while (rs.next()) {
                comp_no = rs.getString(1);
                compParamstr += comp_no + "|";
            }
            System.out.println("compParamstr :" + compParamstr);
            rs.close();
            stmt.close();

            //conn.close();
            ////System.out.println("compParamstr :"+compParamstr);
            response.getWriter().write(compParamstr);



%>
