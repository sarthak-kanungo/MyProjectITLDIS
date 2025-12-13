<%-- 
    Document   : EAMG_get_components_for_alternate
   
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
            String compno = request.getParameter("compno");
            ////System.out.println("compno in jsp :"+compno);

            String comptype = request.getParameter("comp_type");
            //String creation=request.getParameter("creation");
            ////System.out.println("compno in jsp :"+compno);
            ////System.out.println("comptype in jsp :"+comptype);
            StringBuilder compParamstr = new StringBuilder("");
            Connection conn = holder.getConnection();
            Vector alternateCompVec = new Vector();
            ResultSet rs = null;
            Statement stmt = null;
            String query = "";
            int i = 0;
            String comp_type = "";
            String comp_no = "";
            query = "Select ALTERNATE_COMPONENT from CAT_ALTERNATE where GRP_KIT_NO='" + grpno + "' and COMP_TYPE='" + comptype + "' and COMPONENT='" + compno + "'";
            ////System.out.println("query:"+query);

            try {
                stmt = conn.createStatement();
                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    comp_no = rs.getString(1);
                    alternateCompVec.add(comp_no);
                    if (comptype.equals("PRT")) {
                        compParamstr.append(comp_no + " (Part)" + "|");
                    }

                }
                compParamstr.append("&");
                query = "Select TOP 1000 PART_NO from cat_PART  order by PART_NO";

                ////System.out.println("query:"+query);

                rs = stmt.executeQuery(query);
                while (rs.next()) {
                    comp_no = rs.getString(1);
                    if (!alternateCompVec.contains(comp_no)) {
                        compParamstr.append(comp_no + " (Part)" + "|");
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {

                if (stmt != null) {
                    stmt.close();
                }
                //if (conn != null) {
                    //conn.close();
                //}
            }


            ////System.out.println("compParamstr :"+compParamstr);
            response.getWriter().write(compParamstr.toString());
            compParamstr.setLength(0);
            compParamstr = null;

%>
